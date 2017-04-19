package com.dudu.weixin.service;

import com.dudu.soa.ahi.module.ResultTotalAHIPoint;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.weixin.mould.PesrsonCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 个人中心页面的整和
 * Created by lizhen on 2017/4/18.
 */
@Service
public class PersoncenterService {
    /**
     * 获取用户信息服务
     */
    @Autowired
    private WxCustomerService wxCustomerService;
    /**
     * 联盟卡包
     */
    @Autowired
    private LianMengKaService lianMengKa;
    /**
     * ahi
     */
    @Autowired
    private AHIService ahiService;
    /**
     * 引入车辆品牌车系型号的服务
     */
    @Autowired
    private CarTypeService carTypeService;

    /**
     * 获取个人中心页面数据
     *
     * @param platenumber 车牌号
     * @param lmcode      联盟编码
     * @return 个人中心的整体页面数据
     */
    public PesrsonCenter getPersonCenter(String platenumber, String lmcode) {
        PesrsonCenter pesrsonCenter = new PesrsonCenter();
        //查询联盟客户信息
        WxCustomer wxCustomer = wxCustomerService.getWxCustomer(platenumber, lmcode);
        pesrsonCenter.setId(wxCustomer.getId());
        pesrsonCenter.setCarBrand(wxCustomer.getCarBrand().toString());
        pesrsonCenter.setCarHaopai(wxCustomer.getCarHaopai());
        pesrsonCenter.setCarModel(wxCustomer.getCarModel().toString());
        pesrsonCenter.setCarSeries(wxCustomer.getCarSeries().toString());
        pesrsonCenter.setCurrentmileage(wxCustomer.getCurrentmileage());

        //查询联盟卡列表
        List<LianmengkaXmLeftResultModule> lianmengkaXmLeftResultModules = lianMengKa.queryLmkInfo(lmcode, platenumber);
        pesrsonCenter.setLianmengkaXmLeftResultModules(lianmengkaXmLeftResultModules);
        //查询ahi分数
        List<ResultTotalAHIPoint> resultTotalAHIPoints = ahiService.queryAllPointByPlateNumber(platenumber);
        if (resultTotalAHIPoints.size() > 0) {
            pesrsonCenter.setPoint(resultTotalAHIPoints.get(0).getPoint());

        }
        return pesrsonCenter;
    }

    /**
     *
     * @param request 请求数据
     * @param businessType 业务类型
     * @param platenumber 车牌号
     * @param lmcode 联盟code
     * @return 对象
     */
    public Object personcenter(HttpServletRequest request, String businessType, String platenumber, String lmcode) {
        switch (businessType) {
            //查询个人中心的主页面
            case "personcenter":
                return this.getPersonCenter(platenumber, lmcode);
            //查询车辆品牌车型车系
            case "carType":
                return carTypeService.queryAllCar(request.getParameter("type"), Integer.parseInt(request.getParameter("num")));
            //修改车辆品牌车型车系
            case "updateCarType":
                wxCustomerService.updateCustomer(
                        new WxCustomer()
                                .setBrandCode(lmcode)
                                .setCarHaopai(platenumber)
                                .setCarModel(Integer.parseInt(request.getParameter("CarModel")))
                                .setCarBrand(Integer.parseInt(request.getParameter("CarBrand")))
                                .setCarSeries(Integer.parseInt(request.getParameter("CarSeries"))));
                //修改当前里程
            case "currentmileage":
                wxCustomerService.updateCustomer(
                        new WxCustomer()
                                .setBrandCode(lmcode)
                                .setCarHaopai(platenumber)
                                .setCurrentmileage(request.getParameter("Currentmileage")));
            default:
                return null;
        }
    }

}
