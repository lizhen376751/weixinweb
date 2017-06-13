package com.dudu.weixin.control;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.loginlog.module.LogInLog;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.soa.weixindubbo.weixin.http.module.parammodule.WeiXinUserInfo;
import com.dudu.soa.weixindubbo.weixin.weixinconfig.api.ApiWeiXinConfig;
import com.dudu.soa.weixindubbo.weixin.weixinconfig.module.WeiXinConfig;
import com.dudu.weixin.service.LogInLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lizhen on 2017/3/24.
 * 登录验证拦截器(如果有登录历史则不用登录)
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * session
     */
    @Autowired
    private HttpSession httpSession;
    /**
     * 引入登录历史的服务
     */
    @Autowired
    private LogInLogService logInLogService;

    /**
     * 引入获取appid和sercert的方法
     */
    @Reference(owner = "lizhen")
    private ApiWeiXinConfig apiWeiXinConfig;
    /**
     * 引入微信通讯相关接口
     */
    @Reference(timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;

    /**
     * @param request  请求
     * @param response 返回数据
     * @param arg2     对象
     * @return true或者false
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        //无论点击那个页面都要进行拦截
        String lmcode = (String) httpSession.getAttribute("lmcode");
        //防止同一部手机,更换公众号session缓存的问题
        String lmcode2 = "";
        //获取参数,并将其分解
        String strWxShopcode = request.getParameter("lmcode");
        String flagStr = "";
        String openId = "";
        //判断session里面有没有lmcode,没有的话将其存入session中
        if (strWxShopcode == null || "" == strWxShopcode || "".equals(strWxShopcode)) {
            flagStr = request.getParameter("flagStr");
        } else {
            flagStr = strWxShopcode.split("_")[1]; //页面跳转判断
            lmcode2 = strWxShopcode.split("_")[0]; //联盟code
            if (null != lmcode && !"".equals(lmcode)) {
                if (!lmcode.equals(lmcode2)) {
                    //判断session里面的联盟code和页面登录进来的联盟code是否相同,如果不相同说明同一个手机关注了两个联盟
                    request.getRequestDispatcher("/Views/login/login.jsp?lmcode=" + lmcode2).forward(request, response);
                }
            }
            lmcode = lmcode2;
            httpSession.setAttribute("lmcode", lmcode);
        }
        //微信的openid
        openId = (String) httpSession.getAttribute("openId");
        //判断session里面有没有openId
        if (null == openId || openId.equals("")) {
            String code = request.getParameter("code");
            WeiXinConfig weiXinConfig = apiWeiXinConfig.getWeiXinConfig(lmcode);
            //获取微信用户的基本信息
            WeiXinUserInfo weiXinUserInfo = apiAllWeiXiRequest.getWeiXinUserInfo(code, weiXinConfig.getAppid(), weiXinConfig.getAppserect());
            log.info("获取微信用户信息=====================" + weiXinUserInfo.toString());
            openId = weiXinUserInfo.getOpenid();
            //获取微信用户的别名
            String nickname = weiXinUserInfo.getNickname();
            httpSession.setAttribute("openId", openId);
            httpSession.setAttribute("nickname", nickname);
        }
        //车牌号
        String platenumber = (String) httpSession.getAttribute("plateNumber");
        //个人中心,联盟卡包,保养提醒,ahi指数,施工进度,消费记录需要进行登录判断
        if ("lmkInfo".equals(flagStr) || "AHIInfo".equals(flagStr) || "xiaoFeiList".equals(flagStr) || "personalCenter".equals(flagStr)
                || "baoYangList".equals(flagStr) || "cheXianTouBao".equals(flagStr) || "logout".equals(flagStr) || "AHIInfoxiangqing".equals(flagStr)) {
            //如果车牌号为空直接往下执行
            if (platenumber == null || "null".equals(platenumber) || "".equals(platenumber)) {
                if (null != openId && !"".equals(openId)) {
                    //根据openId和lmcode查询是否有登录记录,如果有记录则不用登录
                    LogInLog logInLog = logInLogService.getLogInLog(lmcode, openId);
                    if (logInLog == null) {
                        //如果没有记录跳转至登录页面
                        request.getRequestDispatcher("/Views/login/login.jsp?lmcode=" + lmcode).forward(request, response);
                    } else {
                        //获取车牌号
                        String plateNumber = logInLog.getPlateNumber();
                        //如果有记录,就获取记录并存入到session
                        httpSession.setAttribute("plateNumber", plateNumber);
                    }
                }

            }
        }
        //返回true代表继续往下执行
        return true;
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     * @Autowired lizhen
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     * @Autowired lizhen
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }


}