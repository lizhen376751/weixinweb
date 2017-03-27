package com.dudu.weixin.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class AutoLoginService {
    /**
     * @Autowired
     * @param openId 微信openId
     * @param shopcode shopcode
     * @return 返回字符串true或者false
     */
    //根据OpenId和shopcode查询是否有登记的记录
    public String judgeOpenId(String openId, String shopcode) {
        String carId = "";
        //TODO 后期调用接口
        return carId;

    }

    /**
     * @Autowired
     * @param code shopcode
     * @return 店铺名称
     */
    //根据shopcode查询店铺名称
    public String getDuduShopName(String code) {
        String rtnStr = "";
        //TODO 后期调用接口
        return rtnStr;
    }
}
