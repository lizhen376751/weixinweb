package com.dudu.weixin.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 原BaoYangTiXing
 */
@Service
public class BaoYangTiXingService {
    /**
     * 常量的替换
     */
    private final int num2 = 10;

    /**
     * 根据联盟总部编码和车牌号获取车辆保养提醒信息（客户需求信息）
     *
     * @param lmCode 联盟code
     * @param carNo  联盟卡号
     * @param top    页数
     * @return 返回一个集合
     * @throws Exception
     */
    public ArrayList getBaoYangListByLmcodeAndCarNo(String lmCode, String carNo, String top) {
        ArrayList list = new ArrayList();
        return list;
    }
}
