package com.dudu.weixin.shopweiixin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.loginlog.module.LogInLog;
import com.dudu.soa.weixindubbo.shopinfo.api.ApiShopInfo;
import com.dudu.soa.weixindubbo.shopinfo.module.ShopInfo;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.soa.weixindubbo.weixin.http.module.parammodule.WeiXinUserInfo;
import com.dudu.weixin.control.LoginInterceptor;
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
 * 店管家微信的拦截器
 * Created by lizhen on 2017/5/4.
 */

public class ShopWeixinInterceptor implements HandlerInterceptor {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


    /**
     * 引入登录历史的服务
     */
    @Autowired
    private LogInLogService logInLogService;
    /**
     * 引入微信通讯相关接口
     */
    @Reference(version = "1.0")
    private ApiAllWeiXiRequest apiAllWeiXiRequest;
    /**
     * 引入查询店铺信息接口
     */
    @Reference(version = "1.0")
    private ApiShopInfo apishopInfo;

    /**
     * @param request  请求
     * @param response 返回数据
     * @param arg2     对象
     * @return true或者false
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        HttpSession session = request.getSession();
        String shopcode = (String) session.getAttribute("shopcode");

        //参数中获取shopcode
        String strWxShopcode = request.getParameter("shopcode");
        String flagStr = strWxShopcode.split("_")[1]; //页面跳转判断
        String shopcode1 = strWxShopcode.split("_")[0]; //店铺code
        //判断session的shopcode是否为空
        if (null != shopcode && !"".equals(shopcode)) {
            //判断session里面的shopcode与菜单按钮带过来的shopcode是否一致
            if (!shopcode1.equals(shopcode)) {
                //如果不一致,跳转至登录页面
                request.getRequestDispatcher("/Views/shoplogin/shoplogin.jsp?shopcode=" + shopcode).forward(request, response);
            }
        }
        //如果为空的话讲shopcode存入到session里面
        session.setAttribute("shopcode", shopcode1);

        //页面带过来的授权code
        String code = request.getParameter("code");
        //根据shopcode查询店铺信息
        ShopInfo shopInfo = apishopInfo.getShopInfo(shopcode);
        String openId = null;
        String nickname = null;
        //如果不为空获取appid和secret
        if (null != shopInfo) {
            String appid = shopInfo.getwXAppId();
            String xAppSecret = shopInfo.getwXAppSecret();
            //获取openid和微信昵称
            WeiXinUserInfo weiXinUserInfo = apiAllWeiXiRequest.getWeiXinUserInfo(code, appid, xAppSecret);
            openId = weiXinUserInfo.getOpenid();
            nickname = weiXinUserInfo.getNickname();
            session.setAttribute("openId", openId);
            session.setAttribute("nickname", nickname);
        }
        String plateNumber = (String) session.getAttribute("plateNumber");
        //判断session里面有没有plateNumber
        if ("".equals(plateNumber) || null == plateNumber) {
            //去登录记录里面查询是否有登录记录
            if (null != openId && !"".equals(openId)) {
                LogInLog logInLog = logInLogService.getLogInLog(shopcode, openId);
                if (logInLog == null) {
                    //如果为空的话直接跳转至登录页面
                    request.getRequestDispatcher("/Views/shoplogin/shoplogin.jsp?shopcode=" + shopcode).forward(request, response);
                } else {
                    //获取车牌号
                    plateNumber = logInLog.getPlateNumber();
                    //如果有记录,就获取记录并存入到session
                    session.setAttribute("plateNumber", plateNumber);
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
