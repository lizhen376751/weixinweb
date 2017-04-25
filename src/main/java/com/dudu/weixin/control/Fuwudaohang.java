package com.dudu.weixin.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 关于服务导航的url跳转(后期去掉)
 * Created by lizhen on 2017/4/15.
 */
@Controller
public class Fuwudaohang {
    /**
     * @param request  请求
     * @param response 相应
     * @throws Exception 异常
     */
    @RequestMapping(value = "/fuwudaohang", method = RequestMethod.GET)
    public void preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.sendRedirect("http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?shopcode=CS000&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4");

//        request.getRequestDispatcher("http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?shopcode=CS000&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4"
//        ).forward(request, response);
    }
}
