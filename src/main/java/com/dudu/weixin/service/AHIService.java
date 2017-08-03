package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.ahi.api.ApiAHIPoint;
import com.dudu.soa.ahi.module.AHIPointParam;
import com.dudu.soa.ahi.module.ResultAHIChildClassPoint;
import com.dudu.soa.ahi.module.ResultAHIClassPoint;
import com.dudu.soa.ahi.module.ResultAHISubClassPoint;
import com.dudu.soa.ahi.module.ResultTotalAHIPoint;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 作者姓名  E-mail: email地址
 * @version 创建时间：2017年2月9日 上午8:52:47
 *          类说明
 */
@Service
public class AHIService {
    /**
     *
     */
    @Reference
    private ApiAHIPoint apiAHIPoint;

    /**
     * 获取ahi列表主页面
     *
     * @param plateNumber 车牌号码
     * @return ahi列表
     */
    public List<ResultTotalAHIPoint> queryAllPointByPlateNumber(String plateNumber) {
        String plateNumbers = "";
        try {
            if (null != plateNumber && !"".equals(plateNumber)) {
                plateNumbers = java.net.URLDecoder.decode(plateNumber, "utf-8");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        AHIPointParam point = new AHIPointParam();
        point.setId(0);
        point.setPlateNumber(plateNumbers);
        point.setRatio("100");
        List<ResultTotalAHIPoint> list = apiAHIPoint.queryAllPointByPlateNumber(point);
        return list;
    }

    /**
     * 获取ahi详情
     *
     * @param plateNumber 车牌号码
     * @return ahi列表
     */
    public List<ResultAHIClassPoint> queryCarPointOne(String plateNumber) {
        String plateNumbers = "";
        try {
            plateNumbers = java.net.URLDecoder.decode(plateNumber, "utf-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        AHIPointParam point = new AHIPointParam();
        point.setId(0);
        point.setPlateNumber(plateNumbers);
        point.setRatio("100");
        List<ResultAHIClassPoint> list = apiAHIPoint.queryClassPointOne(point);
        return list;
    }

    /**
     * @param plateNumber 车牌号码
     * @param id          id
     * @param ratio       系数
     * @return ahi指数
     */
    public List<ResultAHIChildClassPoint> queryCarPointTwo(String plateNumber, String id, String ratio) {
        String plateNumbers = "";
        try {
            plateNumbers = java.net.URLDecoder.decode(plateNumber, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        AHIPointParam point = new AHIPointParam();
        point.setId(id);
        point.setPlateNumber(plateNumbers);
        point.setRatio(ratio);
        List<ResultAHIChildClassPoint> list = apiAHIPoint.queryClassPointTwo(point);
        return list;
    }

    /**
     * @param plateNumber 车牌号码
     * @param id          id
     * @return ahi指数
     */
    public List<ResultAHISubClassPoint> querySubclassPoint(String plateNumber, String id) {
        AHIPointParam point = new AHIPointParam();
        point.setId(id);
        point.setPlateNumber(plateNumber);
        point.setRatio("100");
        List<ResultAHISubClassPoint> list = apiAHIPoint.querySubclassPoint(point);
        return list;
    }
}
