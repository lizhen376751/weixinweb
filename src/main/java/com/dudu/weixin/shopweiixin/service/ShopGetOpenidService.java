package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.shopinfo.api.ApiShopInfo;
import com.dudu.soa.weixindubbo.shopinfo.module.ShopInfo;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.soa.weixindubbo.weixin.http.module.parammodule.WeiXinUserInfo;
import com.dudu.soa.weixindubbo.weixin.weixinconfig.api.ApiWeiXinConfig;
import com.dudu.soa.weixindubbo.weixin.weixinconfig.module.WeiXinConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 获取用户的openid
 * Created by lizhen on 2017/8/22.
 */
@Service
public class ShopGetOpenidService {
    /**
     * logprint 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(ShopGetOpenidService.class);
    /**
     * 引入查询店铺信息接口
     */
    @Reference
    private ApiShopInfo apishopInfo;
    /**
     * 引入微信通讯相关接口
     */
    @Reference(timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;

    /**
     * 引入获取appid和sercert的方法
     */
    @Reference(owner = "lizhen")
    private ApiWeiXinConfig apiWeiXinConfig;

    /**
     * 获取微信的用户信息
     *
     * @param code     微信带过来的code
     * @param shopcode 店管家编码
     * @param lmcode   联盟code
     * @return 微信的用户信息
     */
    public WeiXinUserInfo getOpenid(String code, String shopcode, String lmcode) {
        log.debug("页面内部跳转code" + code);
        String appid = "";
        String xAppSecret = "";
        if (null != shopcode && "".equals(shopcode) && !"null".equals(shopcode)) {
            ShopInfo shopInfo = apishopInfo.getShopInfo(shopcode);
            log.debug("查询百度map的店管家信息=" + shopInfo.toString());
            if (null != shopInfo) {
                appid = shopInfo.getwXAppId();
                xAppSecret = shopInfo.getwXAppSecret();
            }
        } else if (null != lmcode && "".equals(lmcode) && !"null".equals(lmcode)) {
            WeiXinConfig weiXinConfig = apiWeiXinConfig.getWeiXinConfig(lmcode);
            if (null != weiXinConfig) {
                appid = weiXinConfig.getAppid();
                xAppSecret = weiXinConfig.getAppserect();
            }
        }
        //获取微信用户的基本信息
        WeiXinUserInfo weiXinUserInfo = apiAllWeiXiRequest.getWeiXinUserInfo(code, appid, xAppSecret);
        log.info("获取微信用户信息=====================" + weiXinUserInfo.toString());
        //如果不为空获取appid和secret
        return weiXinUserInfo;
    }
}
