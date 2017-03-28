package com.dudu.weixin.service;

import org.springframework.stereotype.Service;

/**
 * 主要用与获取店铺名字/图片/连锁主店铺名字
 */
//TODO 需调用CommonTools的接口


@Service
public class CommonToolsService {
    /**
     * @param shopcode
     * @return
     */
//获取店管家名称
    public String getShopName(String shopcode) {

        return "北京经典汽车服务有限公司";
    }

    //获取图片
    public String getShopListImg(String shopcode) {
        return null;
    }


    /**
     * 获取连锁主店
     *
     * @return
     * @throws Exception
     */
    public String getMainShopcode(String shopcode) {
        return null;
    }


}
