package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;



import com.dudu.soa.weixindubbo.shopinfo.api.ApiShopInfo;
import com.dudu.soa.weixindubbo.shopinfo.module.ShopInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/14.
 * 服务导航信息     店铺信息
 * 原GetShopInfo
 */
//TODO  后期全部封装接口
@Service
public class ShopInfoService {
    /**
     * 引入shopInfo接口
     *
     * @param shopcode
     * @return
     */
    @Reference
    private ApiShopInfo apiShopInfo;

    /**
     * 根据shopcode查询百度map库shopInfo的信息
     *
     * @param shopcode 店铺编码
     * @return 店铺信息实体
     */
    public ShopInfo getShopInfo(String shopcode) {
        ShopInfo shopInfo = apiShopInfo.getShopInfo(shopcode);
        return shopInfo;
    }


    /**
     * @param lmcode    联盟code
     * @param shopType  店铺类型
     * @param orderType 排序
     * @return 联盟店铺集合
     */
    public ArrayList<HashMap<String, String>> queryShopCodeListByLmCode(String lmcode, String shopType, String orderType) {
        ArrayList list = new ArrayList();
        return list;
    }

}
