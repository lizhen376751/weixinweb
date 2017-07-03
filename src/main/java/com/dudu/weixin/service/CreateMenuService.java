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
    @Reference(timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;

    /**
     * @param type      菜单的创建类型
     * @param code      店铺编码或者联盟编码
     * @param appid     微信的appid
     * @param appSecret 微信的sercret
     * @param url       服务器地址
     * @return 创建成功或者失败
     */
    public String createMenu(String type, String code, String appid, String appSecret, String url) {
        MenuUtil menuUtil = new MenuUtil();
        Menu menu = null;
        switch (type) {
            case "lianmeng": //联盟的
                menu = menuUtil.getLianMengMenu(appid, code, url);
                break;
            case "shop": //店管家
                menu = menuUtil.getShopMenu(appid, code, url);
                break;
            case "yilubang": //易璐邦
                menu = menuUtil.getYiLuBangmenu(appid, code);
                break;
            case "chuangke": //创客
                menu = menuUtil.getChuangKe(appid, code, url);
                break;
            default:
                menu = null;
        }
        String menu1 = apiAllWeiXiRequest.createMenu(menu, appid, appSecret);
        return menu1;
    }

}
