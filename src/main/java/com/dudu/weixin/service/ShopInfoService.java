package com.dudu.weixin.service;

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
     *
     * @param shopcode
     * @return
     */
    public  String getShopName(String shopcode){
        String strShopName = "";

        return strShopName;
    }

    /**
     *
     * @param shopcode
     * @return
     */
    public  String getShopToken(String shopcode){
        String strShopName = "";
        return strShopName;
    }

    /**
     *
     * @param shopcode
     * @return
     */
    public static String getShopAppid(String shopcode) {
        String strShopName = "";
        return strShopName;
    }

    /**
     *
     * @param shopcode
     * @return
     */
    public  String getShopAppSecret(String shopcode) {
        String strShopName = "";
        return strShopName;
    }

    /**
     *
     * @param shopcode
     * @return
     */
    public ArrayList listLianSuo(String shopcode)  {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     *
     * @param lmcode
     * @param shopType
     * @param orderType
     * @return
     */
    public ArrayList<HashMap<String,String>> queryShopCodeListByLmCode(String lmcode, String shopType, String orderType)  {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     *
     * @param shopcode
     * @return
     */
    public HashMap dispShop(String shopcode)  {
        return null;
    }

    /**
     *
     * @param shopcode
     * @return
     */
    public  HashMap dispShopWelcome(String shopcode)  {
        return null;
    }
}
