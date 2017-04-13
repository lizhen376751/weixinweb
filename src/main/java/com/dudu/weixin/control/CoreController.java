package com.dudu.weixin.control;


import com.dudu.weixin.util.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 配置微信的url点击提交之后,与服务器的交互
 * lizhen
 */
@Controller
public class CoreController extends HttpServlet {

    /**
     * @param request  请求
     * @param response 返回
     * @throws ServletException 异常
     * @throws IOException      io异常
     */
    @RequestMapping(value = "/urlconfig", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("===========进入===============");
/**
 *微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
 */
        String signature = request.getParameter("signature");
/**
 *时间戳
 */
        String timestamp = request.getParameter("timestamp");
/**
 *随机数
 */
        String nonce = request.getParameter("nonce");
/**
 *随机字符串
 */
        String echostr = request.getParameter("echostr");
/**
 *店铺代码
 */
        String lmcode = request.getParameter("lmcode");

        PrintWriter out = response.getWriter();
/**
 *通过检验signature对请求进行校验。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
 */
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

/**
 * @param request  请求
 * @param response 返回数据
 * @throws ServletException 异常
 * @throws IOException  io异常
 */
//public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
//request.setCharacterEncoding("UTF-8");
//response.setCharacterEncoding("UTF-8");
////code
//// ���ú���ҵ���������Ϣ��������Ϣ
//
//// ��Ӧ��Ϣ
//PrintWriter out = response.getWriter();
//
//out.print(respMessage);
//out.close();
//}

}
