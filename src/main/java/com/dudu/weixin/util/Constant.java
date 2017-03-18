package com.dudu.weixin.util;

import org.springframework.stereotype.Service;

/**
 * 常量类
 * Created by Administrator on 2017/3/15.
 */
@Service
public class Constant {

    private String weixinBaseUrl = "www.duduchewang.cn";
    private String path = "weixincore_cn";
    private Long serialVersionUID = -1847238807216447030L;
    private String Common_LuJing = "weixincore";
    private String ELB_URL = "wx.elubon.com";

    public  String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    public String getCURRENCY_FEN_REGEX() {
        return CURRENCY_FEN_REGEX;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;

    }

    public String getWeixinBaseUrl() {
        return weixinBaseUrl;
    }

    public String getPath() {
        return path;
    }



    public String getCommon_LuJing() {
        return Common_LuJing;
    }

    public String getELB_URL() {
        return ELB_URL;
    }


}
