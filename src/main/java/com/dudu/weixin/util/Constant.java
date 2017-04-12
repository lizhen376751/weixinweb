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
    public static final String APPID = "wxf0af72edbe855d28";
    /**
     * 微信开发者的appSecret
     */
    public static final String APPSERECT = "fa12f20abeabc7c8ca3ebe777ceb2229";
    /**
     * 微信开发者的token
     */
    public static final String TOKEN = "duduchewang";
    /**
     * 微信的url
     */
    public static final String URL = "lm.wx.dev.duduchewang.cn";
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
