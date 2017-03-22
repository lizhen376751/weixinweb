package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.baoxian.base.api.APIBaoXianType;
import com.dudu.soa.baoxian.base.module.BaoXianGongSi;
import com.dudu.soa.baoxian.base.module.BaoXianPCZiDian;
import com.dudu.soa.baoxian.base.module.BaoXianType;
import com.dudu.soa.baoxian.kaidan.api.APIBaoXainKaiDan;
import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDan;
import com.dudu.soa.basedata.employee.api.ApiBaseDataEmployee;
import com.dudu.soa.basedata.employee.module.Employee;
import com.dudu.soa.basedata.employee.module.ServiceAdvisor;
import com.dudu.soa.customerCenter.customer.api.ApiCustomerDemand;
import com.dudu.soa.customerCenter.customer.api.ApiCustomerInfo;
import com.dudu.soa.customerCenter.customer.module.CustomerDemandParam;
import com.dudu.soa.customerCenter.customer.module.CustomerDemandReturn;
import com.dudu.soa.lmbasedata.basedata.shop.api.ApiShopIntf;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopParam;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopQueryFruit;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 * 车险投保
 */
@Service
public class ChexiantoubaoService {
    @Reference(version = "1.0",owner = "zhangyuanlei")
    private APIBaoXainKaiDan baoXianTypeImpl;
   @Reference(version = "1.0")
   private APIBaoXianType aPIBaoXianType;
    @Reference(version = "0.0.1",owner = "lichengjia")
    private ApiShopIntf apiShopIntf;
    @Reference(version = "1.0",owner = "zhush")
    private ApiBaseDataEmployee apiBaseDataEmployee;
    @Reference(version = "0.0.1")
    private ApiCustomerInfo apiCustomerInfo;
    @Reference(version = "0.0.1")
    private ApiCustomerDemand apiCustomerDemand;

    public  List<BaoXianGongSi>  baoxianGongSi(){
        List<BaoXianGongSi> baoXianGongSis = aPIBaoXianType.selectInsuranceCompany();
        return baoXianGongSis;
    }
    public  List<BaoXianType>  baoXianTypes(){
        BaoXianType baoXianType = new BaoXianType();
        BaoXianPCZiDian baoXianPCZiDian = new BaoXianPCZiDian();
        List<BaoXianType> baoXianTypes = aPIBaoXianType.selectBaoXianShuJu();
        return baoXianTypes;
    }
    public String baoXianTiJiao(HttpServletRequest request, BaoXianKaiDan baoXianKaiDan) {
        baoXianTypeImpl.BaoXianKaiDan(baoXianKaiDan);
        return null;
    }

    public List<ServiceAdvisor> queryFuWuGuWen(String shopCode) {
        Employee employee = new Employee();
        employee.setShopCode(shopCode);
        List<ServiceAdvisor>  list= apiBaseDataEmployee.getServiceAdvisor(employee);
        return list;
    }

    public List<ShopQueryFruit> queryLianMengZB(ShopParam shopParam) {

        return apiShopIntf.queryShopInfo(shopParam);
    }

    public List<CustomerDemandReturn> queryCheLiangXinXi(CustomerDemandParam customerDemandParam) {
        //CustomerInfoParam customerInfoParam=new CustomerInfoParam();
        //List<CustomerInfo> customerInfos = apiCustomerInfo.queryCustomerList(customerInfoParam);
        String plateNumber = customerDemandParam.getPlateNumber();
        System.out.println("==========="+plateNumber);

        List<CustomerDemandReturn> customerDemandReturns = apiCustomerDemand.queryDemandList(customerDemandParam);
        System.out.println("======查询的结果是======"+customerDemandReturns);
        return customerDemandReturns;
    }

}
