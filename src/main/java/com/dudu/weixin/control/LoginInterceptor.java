package com.dudu.weixin.control;

import com.dudu.weixin.service.AutoLoginService;
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
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AutoLoginService autoLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // TODO Auto-generated method stub
        String flagStr = request.getParameter("flagStr");
        String carId = (String) httpSession.getAttribute("DUDUCHEWANG_CarId");
        String openId = (String) httpSession.getAttribute("DUDUCHEWANG_OpenId");
        String shopcode = (String) httpSession.getAttribute("DUDUCHEWANG_shopcode");
        System.out.println("拦截器进入================="+flagStr+","+carId+","+openId+","+","+shopcode);
        System.out.println(carId == null || "null".equals(carId) || "".equals(carId));
        if ("lmkInfo".equals(flagStr) || "AHIInfo".equals(flagStr) || "xiaoFeiList".equals(flagStr) ||
                "baoYangList".equals(flagStr) || "cheXianTouBao".equals(flagStr) ||"logout".equals(flagStr)) {
            if (carId == null || "null".equals(carId) || "".equals(carId)) {
                String carhaopai = autoLoginService.judgeOpenId(openId, shopcode);
                if (carhaopai == null || "null".equals(carhaopai) || "".equals(carhaopai)) {
                    request.getRequestDispatcher("/Views/login/login.jsp").forward(request, response);
                }
            }
        }

        //返回true代表继续往下执行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}