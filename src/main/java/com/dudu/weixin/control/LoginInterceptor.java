package com.dudu.weixin.control;

import com.dudu.soa.weixindubbo.loginlog.module.LogInLog;
import com.dudu.weixin.service.LogInLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/3/24.
 * 登录验证拦截器
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
     *@Autowired lizhen
     * @param request
     * @param response
     * @param arg2
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        String flagStr = request.getParameter("flagStr");
        String carId = (String) httpSession.getAttribute("DUDUCHEWANG_CarId");
        String openId = (String) httpSession.getAttribute("DUDUCHEWANG_OpenId");
        String shopcode = (String) httpSession.getAttribute("DUDUCHEWANG_shopcode");
        //个人中心,联盟卡包,保养提醒,ahi指数,施工进度,消费记录需要进行登录判断
        if ("lmkInfo".equals(flagStr) || "AHIInfo".equals(flagStr) || "xiaoFeiList".equals(flagStr)
                || "baoYangList".equals(flagStr) || "cheXianTouBao".equals(flagStr) || "logout".equals(flagStr)) {
            //如果车牌号不为空直接往下执行
            if (carId == null || "null".equals(carId) || "".equals(carId)) {
                LogInLog logInLog = logInLogService.getLogInLog(shopcode, openId);
                String plateNumber = logInLog.getPlateNumber();
                //根据openId和shopcode查询是否有登录记录,如果有记录则不用登录
                if (plateNumber == null || "null".equals(plateNumber) || "".equals(plateNumber)) {
                    //如果没有记录跳转至登录页面
                    request.getRequestDispatcher("/Views/login/login.jsp").forward(request, response);
                }
            }
        }
        //返回true代表继续往下执行
        return true;
    }

    /**
     * @Autowired lizhen
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    /**
     * @Autowired lizhen
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }



}