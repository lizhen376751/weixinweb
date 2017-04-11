package com.dudu.weixin.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.loginlog.module.LogInLog;
import com.dudu.soa.weixindubbo.weixin.api.ApiWeiXindUtil;
import com.dudu.weixin.service.LogInLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/3/24.
 * 登录验证拦截器(如果有登录历史则不用登录)
 */
public class LoginInterceptor implements HandlerInterceptor {
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
     * 引入获取openid的接口
     */
    @Reference(version = "1.0", owner = "lizhen")
    private ApiWeiXindUtil apiWeiXindUtil;

    /**
     * @param request
     * @param response
     * @param arg2
     * @return
     * @throws Exception
     * @Autowired lizhen
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        //无论点击那个页面都要进行拦截
        String lmcode = (String) httpSession.getAttribute("lmcode");
        //获取参数,并将其分解
        String strWxShopcode = request.getParameter("lmcode");
        String flagStr = "";
        //判断session里面有没有lmcode,没有的话将其存入session中
        if (strWxShopcode == null || "" == strWxShopcode || "".equals(strWxShopcode)) {
            flagStr = request.getParameter("flagStr");
        } else {
            flagStr = strWxShopcode.split("_")[1]; //页面跳转判断
            lmcode = strWxShopcode.split("_")[0]; //联盟code
            httpSession.setAttribute("lmcode", lmcode);
        }

        //车牌号
        String platenumber = (String) httpSession.getAttribute("platenumber");
        //微信的openid
        String openId = (String) httpSession.getAttribute("openId");
        //判断session里面有没有openId
        if (null == openId || openId.equals("")) {
            //TODO 暂时写死
            openId = "gggsdsaokkllmsds";
            //TODO 暂时注释掉 获取用户的openid
//            String code = request.getParameter("code");
//            String appId = "";
//            String appSecret = "";
//            WeixinOauth2Token oauth2AccessToken = apiWeiXindUtil.getOauth2AccessToken(appId, appSecret, code);
//            openId = oauth2AccessToken.getOpenId();
            httpSession.setAttribute("openId", openId);
        }

        //个人中心,联盟卡包,保养提醒,ahi指数,施工进度,消费记录需要进行登录判断
        if ("lmkInfo".equals(flagStr) || "AHIInfo".equals(flagStr) || "xiaoFeiList".equals(flagStr) || "personalCenter".equals(flagStr)
                || "baoYangList".equals(flagStr) || "cheXianTouBao".equals(flagStr) || "logout".equals(flagStr)) {
            //如果车牌号为空直接往下执行
            if (platenumber == null || "null".equals(platenumber) || "".equals(platenumber)) {
                //根据openId和lmcode查询是否有登录记录,如果有记录则不用登录
                LogInLog logInLog = logInLogService.getLogInLog(lmcode, openId);
                if (logInLog == null) {
                    //如果没有记录跳转至登录页面
                    request.getRequestDispatcher("/Views/login/login.jsp").forward(request, response);
                } else {
                    //获取车牌号
                    String plateNumber = logInLog.getPlateNumber();
                    //如果有记录,就获取记录并存入到session
                    httpSession.setAttribute("plateNumber", plateNumber);
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