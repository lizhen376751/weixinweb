package com.dudu.weixin.shopweiixin.service;

import com.dudu.soa.ahi.module.ResultTotalAHIPoint;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.customercenter.customer.module.UpdateCustomerInfoParam;
import com.dudu.weixin.service.AHIService;
import com.dudu.weixin.service.CarTypeService;
import com.dudu.weixin.shopweiixin.mould.ShopPersonCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 店管家微信个人中心
 * Created by lzihen on 2017/5/12.
 */
@Service
public class ShopPersonCenterService {
    /**
     * 引入店管家的客户中心服务
     */
    @Autowired
    private ShopCustomInfoService shopCustomInfo;
    /**
     * 引入ahi查询的服务
     */
    @Autowired
    private AHIService ahiService;

    /**
     * 引入车辆品牌车系型号的服务
     */
    @Autowired
    private CarTypeService carTypeService;

    /**
     * 所有关于店管家个人中心的统一路径
     * @param request     请求数据
     * @param plateNumber 车牌号
     * @param shopcode    店铺编码
     * @return 对象
     */
    public Object personcenter(HttpServletRequest request, String plateNumber, String shopcode) {
        String servicetype = request.getParameter("servicetype"); //业务类型
        switch (servicetype) {
            //查询个人中心的主页面
            case "personcenter":
                return this.getShopPersonCenter(shopcode, plateNumber);
            //查询车辆品牌车型车系
            case "carType":
                return carTypeService.queryAllCar(request.getParameter("type"), Integer.parseInt(request.getParameter("num")));
            //修改车辆品牌车型车系
            case "updateCarType":
                return shopCustomInfo.updateCustomerInfo(
                        new UpdateCustomerInfoParam()
                                .setId(Integer.parseInt(request.getParameter("id")))
                                .setMainShopCode(shopcode)
                                .setPlateNumber(plateNumber)
                                .setCarBrandId(Integer.parseInt(request.getParameter("carBrandId")))
                                .setCarModelId(Integer.parseInt(request.getParameter("carModelId")))
                                .setCarSeriesId(Integer.parseInt(request.getParameter("carSeriesId")))
                );

            //修改当前里程
            case "currentmileage":
                return shopCustomInfo.updateCustomerInfo(
                        new UpdateCustomerInfoParam()
                                .setId(Integer.parseInt(request.getParameter("id")))
                                .setMainShopCode(shopcode)
                                .setPlateNumber(plateNumber)
                                .setKilo(request.getParameter("kilo"))
                );
            default:
                return null;
        }
    }

    /**
     * 获取店管家微信的个人中心数据
     *
     * @param shopcode    店铺编码
     * @param plateNumber 车牌号
     * @return 个人中心的实体类
     */
    public ShopPersonCenter getShopPersonCenter(String shopcode, String plateNumber) {
        ShopPersonCenter shopPersonCenter = new ShopPersonCenter();
        CustomerInfo customerInfo = shopCustomInfo.queryCustomerList(shopcode, plateNumber);
        if (customerInfo != null) {
            shopPersonCenter.setPlateNumber(customerInfo.getPlateNumber());
            shopPersonCenter.setCarBrand(customerInfo.getCarBrand());
            shopPersonCenter.setCarModel(customerInfo.getCarModel());
            shopPersonCenter.setCarSeries(customerInfo.getCarSeries());
            shopPersonCenter.setCurrentmileage(customerInfo.getKilo());
            shopPersonCenter.setId(customerInfo.getId());
            shopPersonCenter.setMaintenanceReminder(customerInfo.getKilo());
        }
        //查询ahi分数
        List<ResultTotalAHIPoint> resultTotalAHIPoints = ahiService.queryAllPointByPlateNumber(plateNumber);
        if (resultTotalAHIPoints.size() > 0) {
            shopPersonCenter.setPoint(resultTotalAHIPoints.get(0).getPoint());
        }
        return shopPersonCenter;
    }

}
