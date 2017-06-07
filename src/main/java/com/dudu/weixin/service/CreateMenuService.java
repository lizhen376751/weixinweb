package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.soa.weixindubbo.weixin.http.module.menu.Menu;
import com.dudu.weixin.util.MenuUtil;
import org.springframework.stereotype.Service;

/**
 * 创建菜单
 * Created by lizhen on 2017/5/2.
 */
@Service
public class CreateMenuService {
    /**
     * 引入菜单创建的接口
     */
    @Reference(version = "1.0", timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;

    /**
     * @param type      菜单的创建类型
     * @param code      店铺编码或者联盟编码
     * @param appid     微信的appid
     * @param appSecret 微信的sercret
     * @return 创建成功或者失败
     */
    public boolean createMenu(String type, String code, String appid, String appSecret) {
        MenuUtil menuUtil = new MenuUtil();
        Menu menu = null;
        switch (type) {
            case "geren":
                menu = menuUtil.getGeRenCeshiMenu(appid, code);
                break;
            case "lianmeng":
                menu = menuUtil.getLianMengMenu(appid, code);
                break;
            case "shop":
                menu = menuUtil.getShopMenu(appid, code);
                break;
            case "yilubang":
                menu = menuUtil.getYiLuBangmenu(appid, code);
                break;
            case "yangchengtai":
                menu = menuUtil.getYangchengtai(appid, code);
                break;
            default:
                menu = null;
        }
        boolean ss = apiAllWeiXiRequest.createMenu(menu, appid, appSecret);
        return ss;
    }

}
