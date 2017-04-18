package com.dudu.weixin.service;

import com.dudu.soa.ahi.module.ResultTotalAHIPoint;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.weixin.mould.PesrsonCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     *获取个人中心页面数据
     * @param platenumber 车牌号
     * @param lmcode 联盟编码
     * @return
     */
    public PesrsonCenter getPersonCenter(String platenumber, String lmcode) {
        PesrsonCenter pesrsonCenter = new PesrsonCenter();
        //查询联盟客户信息
        WxCustomer wxCustomer = wxCustomerService.getWxCustomer(platenumber, lmcode);
        pesrsonCenter.setId(wxCustomer.getId());
        pesrsonCenter.setCarBrand(wxCustomer.getCarBrand());
        pesrsonCenter.setCarHaopai(wxCustomer.getCarHaopai());
        pesrsonCenter.setCarModel(wxCustomer.getCarModel());
        pesrsonCenter.setCarSeries(wxCustomer.getCarSeries());
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

}
