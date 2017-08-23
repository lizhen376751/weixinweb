package com.dudu.weixin.shopweiixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.ahi.module.ResultTotalAHIPoint;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.customercenter.customer.module.UpdateCustomerInfoParam;
import com.dudu.soa.finace.userequity.api.ApiUserEquity;
import com.dudu.soa.finace.userequity.module.EquityParam;
import com.dudu.soa.finace.userequity.module.InviolableRights;
import com.dudu.soa.finace.userequity.module.ProjectCard;
import com.dudu.soa.finace.userequity.module.ProjectCardMX;
import com.dudu.soa.finace.userequity.module.RechargeableCard;
import com.dudu.soa.finace.userequity.module.RechargeableCardMX;
import com.dudu.weixin.service.AHIService;
import com.dudu.weixin.service.CarTypeService;
import com.dudu.weixin.shopweiixin.mould.ShopPersonCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 店管家微信个人中心
 * Created by lzihen on 2017/5/12.
 */
@Service
public class ShopPersonCenterService {

    /**
     * 引入session
     */
    @Autowired
    private HttpSession httpSession;
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
     * 引入查询个人权益的服务
     */
    @Reference
    private ApiUserEquity apiUserEquity;

    /**
     * 引入车辆品牌车系型号的服务
     */
    @Autowired
    private CarTypeService carTypeService;

    /**
     * 所有关于店管家个人中心的统一路径
     *
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
            //查询个人财务信息
            case "getCheGuanJiaFinance":
                return this.getCheGuanJiaFinance(request);
            //查询个人权益
          /*  case "personalRightsAndInterests":
                return this.getShopPersonalRightsAndInterests(request);*/
            //查询项目卡
            case "queryUserProjectCardList":
                return this.queryUserProjectCardList(request);
            //查询项目卡明细
            case "projectCardMX":
                return this.queryProjectCardMX(request);
            //查询充值卡
            case "queryUserRechargeableCardList":
                return this.queryUserRechargeableCardList(request);
            //查询充值卡明细
            case "rechargeableCardMX":
                return this.queryRechargeableCardMX(request);
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
            shopPersonCenter.setPlateNumber(customerInfo.getPlateNumber().toUpperCase())
                    .setCarBrand(customerInfo.getCarBrand())
                    .setCarModel(customerInfo.getCarModel())
                    .setCarSeries(customerInfo.getCarSeries())
                    .setCurrentmileage(customerInfo.getKilo())
                    .setId(customerInfo.getId())
                    .setLevelName(customerInfo.getLevelName())
                    .setMaintenanceReminder(customerInfo.getKilo());
        }
        //查询ahi分数
        List<ResultTotalAHIPoint> resultTotalAHIPoints = ahiService.queryAllPointByPlateNumber(plateNumber);
        if (resultTotalAHIPoints.size() > 0) {
            shopPersonCenter.setPoint(resultTotalAHIPoints.get(0).getPoint());
        }
        return shopPersonCenter;
    }

    /**
     * 获取用户个人财务信息
     *
     * @param request 请求
     * @return InviolableRights 用户个人权益
     */
    public InviolableRights getCheGuanJiaFinance(HttpServletRequest request) {
        EquityParam param = new EquityParam();
        String shopCode = (String) httpSession.getAttribute("shopcode");
        Integer customerId = Integer.parseInt(request.getParameter("customerId"));
        String plateNumb = (String) httpSession.getAttribute("plateNumber");
        param.setShopCode(shopCode)
                .setCustomerId(customerId)
                .setPlateNumb(plateNumb);
        InviolableRights inviolableRights = apiUserEquity.getCheGuanJiaFinance(param);
        if (null != inviolableRights && !"".equals(inviolableRights)) {
            return inviolableRights;
        }
        return null;
    }

    /**
     * 获取用户个人权益
     *
     * @param request 请求
     * @return InviolableRights 用户个人权益
     */
    /*public InviolableRights getShopPersonalRightsAndInterests(HttpServletRequest request) {
        EquityParam equityParam = new EquityParam();
        String shopCode = (String) httpSession.getAttribute("shopcode");
        String customerId = request.getParameter("customerId");
        String plateNumb = (String) httpSession.getAttribute("plateNumber");
        if (null != shopCode && !"".equals(shopCode) && null != customerId && !"".equals(customerId) && null != plateNumb && !"".equals(plateNumb)) {
            Integer keHuId = Integer.parseInt(customerId);
            equityParam.setShopCode(shopCode);
            equityParam.setCustomerId(keHuId);
            equityParam.setPlateNumb(plateNumb);
        }
        InviolableRights inviolableRights = apiUserEquity.getInviolableRights(equityParam);
        if (null != inviolableRights && !"".equals(inviolableRights)) {
            return inviolableRights;
        }
        return null;
    }*/

    /**
     * 获取项目卡
     *
     * @param request 请求
     * @return List<ProjectCardMX> 项目卡集合
     */
    public List<ProjectCard> queryUserProjectCardList(HttpServletRequest request) {
        String shopCode = (String) httpSession.getAttribute("shopcode");
        String customerId = request.getParameter("customerId");
        EquityParam param = new EquityParam();
        param.setShopCode("0533001")
                .setCustomerId(29628);
        List<ProjectCard> projectCardMXES = apiUserEquity.queryUserProjectCardList(param);
        if (null != projectCardMXES && projectCardMXES.size() > 0) {
            return projectCardMXES;
        }
        return null;
    }

    /**
     * 获取项目卡明细
     *
     * @param request 请求
     * @return List<ProjectCardMX> 项目卡明细集合
     */
    public List<ProjectCardMX> queryProjectCardMX(HttpServletRequest request) {
        String shopCode = request.getParameter("shopCode");
        String id = request.getParameter("customerId");
        Integer customerId = Integer.parseInt(id);
        String cardNumb = request.getParameter("cardNumb");
        String plateNumber = request.getParameter("plateNumber");
        EquityParam equityParam = new EquityParam();
        equityParam.setCardNumb(cardNumb);
        equityParam.setCustomerId(customerId);
        equityParam.setShopCode(shopCode);
        equityParam.setPlateNumb(plateNumber);
        List<ProjectCardMX> projectCardMXES = apiUserEquity.queryUserProjectCardMX(equityParam);
        if (null != projectCardMXES && projectCardMXES.size() > 0) {
            return projectCardMXES;
        }
        return null;
    }

    /**
     * 获取充值卡
     *
     * @param request 请求
     * @return List<RechargeableCardMX> 充值卡明细集合
     */
    public List<RechargeableCard> queryUserRechargeableCardList(HttpServletRequest request) {
        String shopCode = (String) httpSession.getAttribute("shopcode");
        Integer customerId = Integer.parseInt(request.getParameter("customerId"));
        EquityParam param = new EquityParam();
        param.setCustomerId(customerId)
                .setShopCode(shopCode);
        List<RechargeableCard> result = apiUserEquity.queryUserRechargeableCardList(param);
        if (null != result && result.size() > 0) {
            return result;
        }
        return null;
    }

    /**
     * 获取充值卡明细
     *
     * @param request 请求
     * @return List<RechargeableCardMX> 充值卡明细集合
     */
    public List<RechargeableCardMX> queryRechargeableCardMX(HttpServletRequest request) {
        String shopCode = request.getParameter("shopCode");
        String id = request.getParameter("customerId");
        Integer customerId = Integer.parseInt(id);
        String cardNumb = request.getParameter("cardNumb");
        String plateNumber = request.getParameter("plateNumber");
        EquityParam equityParam = new EquityParam();
        equityParam.setCardNumb(cardNumb);
        equityParam.setCustomerId(customerId);
        equityParam.setShopCode(shopCode);
        equityParam.setPlateNumb(plateNumber);
        List<RechargeableCardMX> rechargeableCardMXES = apiUserEquity.queryUserRechargeableCardMX(equityParam);
        if (null != rechargeableCardMXES && rechargeableCardMXES.size() > 0) {
            return rechargeableCardMXES;
        }
        return null;
    }
}
