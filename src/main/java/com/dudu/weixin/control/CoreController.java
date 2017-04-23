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
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * 配置微信的url点击提交之后,与服务器的交互
 * 后期微信所有的接受消息,都会进入这个路径
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
     * post方法用于接收微信服务端消息
     *
     * @param request  请求
     * @param response 回应请求
     */
    @RequestMapping(value = "/urlconfig", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("这是post方法！");
        try {
            InputStream inputStream = request.getInputStream();
            //TODO 调用微信消息处理的接口
//            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
//                EventDispatcher.processEvent(map); //进入事件处理
//            }else{
//                MsgDispatcher.processMessage(map); //进入消息处理
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
