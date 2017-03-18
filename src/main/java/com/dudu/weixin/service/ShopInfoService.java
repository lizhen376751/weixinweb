package com.dudu.weixin.service;

import com.dudu.weixin.struct.shop.ShopInfo;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/14.
 */
@Service
public class ShopInfoService {

    public ShopInfo getShopInfoDetail(String shopCode) {
        //Todo 需调用CoreService的processRequest接口
        return null;
    }

    public String getShopName(String shopcode) {
        //Todo GetShopInfo 的getShopName接口
        return null;
    }

    public String getShopAppSecret(String shopcode){
        //TODO GetShopInfo 的getShopAppSecret接口
        return null;
    }

    public String getShopAppid(String shopcode) {
        //Todo GetShopInfo 的getShopAppid接口
        return null;
    }
}
