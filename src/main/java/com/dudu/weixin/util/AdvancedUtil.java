package com.dudu.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.dudu.weixin.mould.WeixinOauth2Token;

/**
 * Created by Administrator on 2017/3/15.
 */
public class AdvancedUtil {

    /**
     * asdlkjasldkjf
     * @param appId 微信appId
     * @param appSecret 微信appSecret
     * @param code 微信code
     * @return  WeixinOauth2Token
     */
    public  WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);

        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getIntValue("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                //System.out.println(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getIntValue("errcode");
                String errorMsg = jsonObject.getString("errmsg");
//                log.error("��ȡ��ҳ��Ȩƾ֤ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }
}
