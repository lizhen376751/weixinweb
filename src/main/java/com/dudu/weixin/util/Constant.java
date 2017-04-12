package com.dudu.weixin.util;

/**
 * 常量类,所有的常量均存放于此
 * Created by lizhen on 2017/3/15.
 */
public final class Constant {
    /**
     * 构造器
     */
    private Constant() {
    }
    /**
     * 微信开发者的appid
     */
    public static final String APPID = "wxd4e76e01e4a6e3b7";
    /**
     * 微信开发者的appSecret
     */
    public static final String APPSERECT = "dd1e044b9208d43a5a31238e5ee053c7";
    /**
     * 微信的url
     */
    public static final String URL = "83d94aa0.ngrok.io";
    /**
     * 主路径
     */
    public static final String LUJING = "oauthLoginServlet";
    /**
     * 联盟code
     */
    public static final String LMCODE = "CS000";
    /**
     * 开头路径
     */
    public static final String COMMONURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
            + APPID + "&redirect_uri=http%3A%2F%2F" + URL + "%2F" + LUJING + "?lmcode=" + LMCODE;



}
