package com.dudu.weixin.service;


import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CoreService {
//    /**
//     *
//     * @param request
//     * @return
//     */
//    public static String processRequest(HttpServletRequest request) {
//        String respMessage = null;
//        try {
//            String strWxShopcode = request.getParameter("shopcode");
//            HashMap hmShop = GetShopInfo.dispShopWelcome(strWxShopcode);
//            String strWxShopName = (String) hmShop.get("ShopName");
//            String WelcomeImg = (String) hmShop.get("WelcomeImg");
//            String WelcomeTxt = (String) hmShop.get("WelcomeTxt");
//            String WXAppId = (String) hmShop.get("WXAppId");
//            String tiaozhuanurl = (String) hmShop.get("url");
//            if (WelcomeTxt == null || "null".equals(WelcomeTxt)
//                    || "".equals(WelcomeTxt))
//                WelcomeTxt = "����ȷ����GPS��λ���񡣵���������΢��վ��";
//
//            // Ĭ�Ϸ��ص��ı���Ϣ����
//
//            // xml�������
//            Map<String, String> requestMap = MessageUtil.parseXml(request);
//            // ���ͷ��ʺţ�open_id��
//            String fromUserName = requestMap.get("FromUserName");
//            // �����ʺ�
//            String toUserName = requestMap.get("ToUserName");
//            // ��Ϣ����
//            // �û��ظ�������
//            String content = requestMap.get("Content");
//
//            String msgType = requestMap.get("MsgType");
//            // �ظ��ı���Ϣ
//
//
//            NewsMessage newsMessage = new NewsMessage();
//            newsMessage.setToUserName(fromUserName);
//            newsMessage.setFromUserName(toUserName);
//            newsMessage.setCreateTime(new Date().getTime());
//            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//            newsMessage.setFuncFlag(0);
//
//            // �ı���Ϣ
//            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
//                //System.out.println("�Ҳ������µ�");
//                /*TextMessage textMessage = new TextMessage();
//                textMessage.setToUserName(fromUserName);
//				textMessage.setFromUserName(toUserName);
//				textMessage.setCreateTime(new Date().getTime());
//				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//				textMessage.setFuncFlag(0);
//				textMessage.setContent("�𾴵Ŀͻ����ã���ӭ��ʹ�����ǵ�΢��ƽ̨���Ժ����ǽ���������ϵ��"+strWxShopName);
//
//				// ��ͼ����Ϣ����ת����xml�ַ���
//				respMessage = MessageUtil.textMessageToXml(textMessage);*/
//                String strPicUrl = "";
//                if (WelcomeImg != null && !"null".equals(WelcomeImg)
//                        && !"".equals(WelcomeImg)) {
//                    strPicUrl = "http://shop.duduchewang.com/upload/"
//                            + strWxShopcode.toUpperCase() + "/shopimg/" + WelcomeImg;
//                }
//                try {
//                    URL url = new URL(strPicUrl);
//                    URLConnection uc = url.openConnection();
//                    InputStream in = uc.getInputStream();
//                } catch (Exception e) {
//                    strPicUrl = "http://wx.duduchewang.cn/images/banweixin.jpg";
//                }
//                if (tiaozhuanurl == null && tiaozhuanurl.equals("null")
//                        && "".equals(tiaozhuanurl)) {
//                    tiaozhuanurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
//                            + WXAppId
//                            + "&redirect_uri=http%3A%2F%2Fwx.duduchewang.cn%2Fweixincore%2FShangChengServlet?shopcode="
//                            + strWxShopcode
//                            + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
//
//                }
//                List<Article> articleList = new ArrayList<Article>();
//                Article article = new Article();
//                article.setTitle(strWxShopName);
//                article.setDescription(WelcomeTxt);
//                article.setPicUrl(strPicUrl);
//                article.setUrl(tiaozhuanurl);
//                articleList.add(article);
//                // ����ͼ����Ϣ����
//                newsMessage.setArticleCount(articleList.size());
//                // ����ͼ����Ϣ������ͼ�ļ���
//                newsMessage.setArticles(articleList);
//                // ��ͼ����Ϣ����ת����xml�ַ���
//                respMessage = MessageUtil.newsMessageToXml(newsMessage);
//            }
//
//            // ͼƬ��Ϣ
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//				/*System.out.println("----------------���ԣ��յ�ͼƬ��Ϣ��"+System.currentTimeMillis());*/
//
//                String resultMsg = "лл���Ա����֧�֣�";
//
//                TextMessage textMessage = new TextMessage();
//                textMessage.setToUserName(fromUserName);//�ͻ�openid
//                textMessage.setFromUserName(toUserName);//���͵Ĺ��ں�
//                textMessage.setCreateTime(new Date().getTime());
//                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//                textMessage.setFuncFlag(0);
//                textMessage.setContent(resultMsg);
//
//                // ���ı���Ϣ����ת����xml�ַ���
//                respMessage = MessageUtil.textMessageToXml(textMessage);
//
//            }
//            // ����λ����Ϣ
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
//
//            }
//            // ������Ϣ
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
//            }
//            // ��Ƶ��Ϣ
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
//            }
//            // �¼�����
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
//                // �¼�����
//                String eventType = requestMap.get("Event");
//                // ����
//                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
//                    String strPicUrl = "";
//                    if (WelcomeImg != null && !"null".equals(WelcomeImg)
//                            && !"".equals(WelcomeImg)) {
//                        strPicUrl = "http://shop.duduchewang.com/upload/"
//                                + strWxShopcode.toUpperCase() + "/shopimg/" + WelcomeImg;
//                    }
//                    try {
//                        URL url = new URL(strPicUrl);
//                        URLConnection uc = url.openConnection();
//                        InputStream in = uc.getInputStream();
//                    } catch (Exception e) {
//                        strPicUrl = "http://wx.duduchewang.cn/images/banweixin.jpg";
//                    }
//                    if (tiaozhuanurl == null && tiaozhuanurl.equals("null")
//                            && "".equals(tiaozhuanurl)) {
//                        tiaozhuanurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
//                                + WXAppId
//                                + "&redirect_uri=http%3A%2F%2Fwx.duduchewang.cn%2Fweixincore%2FShangChengServlet?shopcode="
//                                + strWxShopcode
//                                + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
//
//                    }
//                    List<Article> articleList = new ArrayList<Article>();
//                    Article article = new Article();
//                    article.setTitle(strWxShopName);
//                    article.setDescription(WelcomeTxt);
//                    article.setPicUrl(strPicUrl);
//                    article.setUrl(tiaozhuanurl);
//                    articleList.add(article);
//                    // ����ͼ����Ϣ����
//                    newsMessage.setArticleCount(articleList.size());
//                    // ����ͼ����Ϣ������ͼ�ļ���
//                    newsMessage.setArticles(articleList);
//                    // ��ͼ����Ϣ����ת����xml�ַ���
//                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
//
//                }
//                // ��ȡ���͵�λ��
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
//
//                    // ����
//                    String Latitude = requestMap.get("Latitude");
//                    // γ��
//                    String Longitude = requestMap.get("Longitude");
//                    Coordinate coordinate = WeixinUtil.getbaidugps(Latitude,
//                            Longitude);
//                    String La = coordinate.getLatitude();
//                    String Lo = coordinate.getLongitude();
//                    Location location = WeixinUtil.getLocationToken(Latitude,
//                            Longitude);
//                    String City = location.getCity();
//                    String formatted_address = location.getFormatted_address();
//                    WeiChatAction.Save(City, fromUserName, formatted_address,
//                            La, Lo);
//                }
//
//                // ȡ������
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
//                    // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
//                }
//                // �Զ���˵�����¼�
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
//                    // �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ
//                    String eventKey = requestMap.get("EventKey");
//
//                    if (eventKey.equals("31")) {
//                        List<Article> articleList = new ArrayList<Article>();
//                        Article article = new Article();
//                        article.setTitle(strWxShopName);
//                        article.setDescription("�𾴵ĳ������ã��������ı���������ֲ���ָ�ϡ���");
//                        article.setPicUrl("http://wx.duduchewang.cn/weixincore/image/weixinHelp.jpg");
//                        article.setUrl("http://wx.duduchewang.cn/weixincore/help.jsp");
//                        articleList.add(article);
//                        // ����ͼ����Ϣ����
//                        newsMessage.setArticleCount(articleList.size());
//                        // ����ͼ����Ϣ������ͼ�ļ���
//                        newsMessage.setArticles(articleList);
//                        // ��ͼ����Ϣ����ת����xml�ַ���
//                        respMessage = MessageUtil.newsMessageToXml(newsMessage);
//                    }
//
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return respMessage;
//
//    }

}