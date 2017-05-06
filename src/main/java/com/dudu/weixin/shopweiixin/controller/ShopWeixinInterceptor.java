package com.dudu.weixin.shopweiixin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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
        //参数中获取shopcode
        String strWxShopcode = request.getParameter("shopcode");
        String flagStr = strWxShopcode.split("_")[1]; //页面跳转判断
        String shopcode = strWxShopcode.split("_")[0]; //店铺code

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
        }
        //页面进来将openid和nickname,shopcode存入session
        session.setAttribute("openId", openId);
        session.setAttribute("nickname", nickname);
        session.setAttribute("shopcode", shopcode);
        Object plateNumber1 = session.getAttribute("plateNumber");
        if ("".equals(plateNumber1) || null == plateNumber1) {
            request.getRequestDispatcher("/Views/shoplogin/shoplogin.jsp?shopcode=" + shopcode).forward(request, response);
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
