package com.dudu.weixin.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class AutoLoginService {
    //根据OpenId和shopcode查询是否有登记的记录
    public String judgeOpenId(String OpenId,String shopcode) {
        String CarId = "";
        //TODO 后期调用接口
        return CarId;

    }
    //根据shopcode查询店铺名称
    public String getDuduShopName(String code){
        String rtnStr = "";
        //TODO 后期调用接口
        return rtnStr;
    }
}
