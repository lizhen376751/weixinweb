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

    public  String getShopName(String shopcode){
        String strShopName = "";

        return strShopName;
    }

    public  String getShopToken(String shopcode){
        String strShopName = "";
        return strShopName;
    }

    public static String getShopAppid(String shopcode) {
        String strShopName = "";
        return strShopName;
    }

    public  String getShopAppSecret(String shopcode) {
        String strShopName = "";
        return strShopName;
    }

    public ArrayList listLianSuo(String shopcode)  {
        ArrayList list = new ArrayList();
        return list;
    }

    public ArrayList<HashMap<String,String>> queryShopCodeListByLmCode(String lmcode, String shopType, String orderType)  {
        ArrayList list = new ArrayList();
        return list;
    }

    public HashMap dispShop(String shopcode)  {
        return null;
    }

    public  HashMap dispShopWelcome(String shopcode)  {
        return null;
    }
}
