package com.dudu.weixin.third.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.third.api.ApiThird;
import com.dudu.soa.weixindubbo.thirdmessage.module.CustomerText;
import com.dudu.soa.weixindubbo.thirdmessage.module.TextContent;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.weixin.third.aes.AesException;
import com.dudu.weixin.third.aes.WXBizMsgCrypt;
import com.dudu.weixin.util.ThirdUtil;
import com.dudu.weixin.util.XMLUtil;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 第三方开发平台
 * Created by lizhen on 2017/7/20.
 */
@Controller
public class ThirdService {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(ThirdService.class);
//====================================================================================================

    /**
     * 引入消息处理接口
     */
    @Reference(timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;

    /**
     * 第三方开发接口
     */
    @Reference(timeout = 300000)
    private ApiThird apiThird;

    /**
     * 每十分钟推送 授权事件接收url
     *
     * @param request  请求
     * @param response 相应
     * @throws IOException       异常
     * @throws AesException      异常
     * @throws DocumentException 异常
     */
    @RequestMapping(value = "/event/authorize")
    public void acceptAuthorizeEvent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AesException, DocumentException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
        log.info("微信第三方平台---------微信推送Ticket消息10分钟一次-----------" + df.format(new Date()));
        //在第三方平台创建审核通过后，微信服务器会向其“授权事件接收URL”每隔10分钟定时推送component_verify_ticket。
        // 第三方平台方在收到ticket推送后也需进行解密（详细请见【消息加解密接入指引】），接收到后必须直接返回字符串success。
        processAuthorizeEvent(request);
        output(response, "success"); // 工具类：回复微信服务器"文本消息"
    }

    /**
     * 工具类：回复微信服务器"文本消息"
     *
     * @param response     相应
     * @param returnvaleue 相应信息的返回
     */
    public void output(HttpServletResponse response, String returnvaleue) {
        try {
            PrintWriter pw = response.getWriter();
            pw.write(returnvaleue);
            System.out.println("****************returnvaleue***************=" + returnvaleue);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理十分钟推送过来的授权事件的
     *
     * @param request 请求
     * @throws IOException       异常
     * @throws AesException      异常
     * @throws DocumentException 异常
     */
    public void processAuthorizeEvent(HttpServletRequest request) throws IOException, DocumentException, AesException {
        //Token微信开放平台上，服务方设置的接收消息的校验token
        String nonce = request.getParameter("nonce"); //URL上原有参数,随机数
        String timestamp = request.getParameter("timestamp"); //URL上原有参数,时间戳
        String signature = request.getParameter("signature");
        String msgSignature = request.getParameter("msg_signature"); //前文描述密文消息体

        if (!StringUtils.isNotBlank(msgSignature)) {
            return; // 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息
        }
        boolean isValid = apiThird.checkSignature(ThirdUtil.TOKEN, signature, timestamp, nonce);
        if (isValid) {
            StringBuilder sb = new StringBuilder();
            BufferedReader in = request.getReader();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            String xml = sb.toString();
            log.info("第三方平台全网发布-----------------------原始 Xml=" + xml);
            String encodingAesKey = ""; //TODO 调用dubbo的工具类 第三方平台组件加密密钥
            // TODO 后期调用接口获取appid
//            String appId = getAuthorizerAppidFromXml(xml); // 获取授权的Appid 此时加密的xml数据中ToUserName是非加密的，解析xml获取即可
//            log.info("第三方平台全网发布-------appid------getAuthorizerAppidFromXml(xml)-------appId=" + appId);
            WXBizMsgCrypt pc = new WXBizMsgCrypt(ThirdUtil.TOKEN, ThirdUtil.ENDCODINGAESKEY, ThirdUtil.APPID);
            //解密
            xml = pc.decryptMsg(msgSignature, timestamp, nonce, xml);
            log.info("第三方平台全网发布-----------------------解密后 Xml=" + xml);
            //TODO 调用redis接口解析
//            processAuthorizationEvent(xml); //在解密后的xml中获取ticket 并保存Ticket
        }
    }


//==========授权及回调处理=========================================================================

    /**
     * 引导页 一键授权功能
     *
     * @param request  请求
     * @param response 相应
     * @throws IOException 网络异常
     */
    @RequestMapping(value = "/goAuthor")
    public void goAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //网页的一个按钮点击之后直接进行跳转至这个页面,然后客户进行授权
        String preAuthCode = ""; //TODO 调用redis接口获取第三方的预授权码
        String redirectUri = ""; //授权后的回调url
        // TODO 获取预授权码和获取第三方平台的token
        String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=" + ThirdUtil.APPID + "&pre_auth_code=" + preAuthCode + "&redirect_uri=" + redirectUri;
        response.sendRedirect(url);
    }

    /**
     * 授权后的回调ur处理
     *
     * @param request  请求
     * @param response 相应
     */
    @RequestMapping(value = "/authorCallback")
    public void authorCallback(HttpServletRequest request, HttpServletResponse response) {
        String authCode = request.getParameter("auth_code"); //授权码
        String expiresIn = request.getParameter("expires_in"); //有效期
        // TODO 1.调用redis接口 获取第三方开发平台的token
        // TODO 调用redis接口 获取公众号凭据和授权信息接口(保存或者刷新)


    }


//===========全网发布检测===================================================================================================


    /**
     * 事件以及文本消息接受url
     *
     * @param request  请求
     * @param response 相应
     * @throws IOException       网络异常
     * @throws AesException      加密或者解密异常
     * @throws DocumentException 解析xml
     */
    @RequestMapping(value = "{appid}/callback")
    public void acceptMessageAndEvent(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {
        String msgSignature = request.getParameter("msg_signature");
        //LogUtil.info(第三方平台全网发布-------------{appid}/callback-----------验证开始。。。。msg_signature=+msgSignature);
        if (!StringUtils.isNotBlank(msgSignature)) {
            return; // 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader in = request.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        String xml = sb.toString();
        checkWeixinAllNetworkCheck(request, response, xml);
    }


    /**
     * @param request  请求
     * @param response 相应
     * @param xml      发送过来的加密xml
     * @throws AesException      加解密异常
     * @throws DocumentException xml解析
     */
    public void checkWeixinAllNetworkCheck(HttpServletRequest request, HttpServletResponse response, String xml) throws AesException, DocumentException {
        Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String msgSignature = request.getParameter("msg_signature");
        WXBizMsgCrypt pc = new WXBizMsgCrypt(ThirdUtil.TOKEN, ThirdUtil.ENDCODINGAESKEY, ThirdUtil.APPID);
        //解密
        xml = pc.decryptMsg(msgSignature, timestamp, nonce, xml);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(xml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String receivemessage = apiAllWeiXiRequest.receivemessage(map);
        if ("".equals(receivemessage) || null == receivemessage) {
            output(response, receivemessage);
            map.put("test", "businessType");
            receivemessage = apiAllWeiXiRequest.receivemessage(map);
            Document doc = DocumentHelper.parseText(receivemessage);
            Element rootElt = doc.getRootElement();
            String msgType = rootElt.elementText("MsgType");
            String toUserName = rootElt.elementText("ToUserName");
            String fromUserName = rootElt.elementText("FromUserName");
            String content = rootElt.elementText("Content");

            CustomerText customerText = new CustomerText();
            customerText.setTouser(toUserName);
            customerText.setMsgtype(msgType);
            TextContent textContent = new TextContent();
            textContent.setContent(content);
            customerText.setText(textContent);
            //TODO 通过Redis获取第三方的token
            String kefuxiaoxi = apiAllWeiXiRequest.customerSmsSend("token", customerText);
        } else {
            WXBizMsgCrypt pc1 = new WXBizMsgCrypt(ThirdUtil.TOKEN, ThirdUtil.ENDCODINGAESKEY, ThirdUtil.APPID);
            receivemessage = pc1.encryptMsg(receivemessage, createTime.toString(), "easemob");
            output(response, receivemessage);
        }


    }
}
