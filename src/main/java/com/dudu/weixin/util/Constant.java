package com.dudu.weixin.util;

import org.springframework.stereotype.Service;

/**
 * 常量类
 * Created by Administrator on 2017/3/15.
 */
@Service
public class Constant {
    /**
     *
     */
    private String weixinBaseUrl = "www.duduchewang.cn";
    /**
     *
     */
    private String path = "weixincore_cn";
    /**
     *
     */
    private final Long serialVersionUID = -1847238807216447030L;
    /**
     *
     */
    private String commonLuJing = "weixincore";
    /**
     *
     */
    private String eLBURL = "wx.elubon.com";
    /**
     *
     */
    private  String cURRENCYFENREGEX = "\\-?[0-9]+";

    /**
     *
     * @return cURRENCYFENREGEX
     */
    public String getCURRENCYFENREGEX() {
        return cURRENCYFENREGEX;
    }

    /**
     *
     * @return serialVersionUID
     */
    public long getSerialVersionUID() {
        return serialVersionUID;

    }

    /**
     *
     * @return weixinBaseUrl
     */
    public String getWeixinBaseUrl() {
        return weixinBaseUrl;
    }

    /**
     *
     * @return path
     */
    public String getPath() {
        return path;
    }


    /**
     *
     * @return commonLuJing
     */
    public String getcommonLuJing() {
        return commonLuJing;
    }

    /**
     *
     * @return eLBURL
     */
    public String geteLBURL() {
        return eLBURL;
    }


}
