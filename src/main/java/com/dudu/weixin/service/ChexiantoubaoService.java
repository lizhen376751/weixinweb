package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.baoxian.base.api.APIBaoXianType;
import com.dudu.soa.baoxian.base.module.BaoXianGongSi;
import com.dudu.soa.baoxian.base.module.BaoXianPCZiDian;
import com.dudu.soa.baoxian.base.module.BaoXianType;
import com.dudu.soa.baoxian.kaidan.api.APIBaoXainKaiDan;
import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDan;
import com.dudu.soa.baoxian.kaidan.module.BaoXianList;
import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.baoxian.kaidan.module.CustomerModel;
import com.dudu.soa.baoxian.kaidan.module.InsuranceBill;
import com.dudu.soa.baoxian.kaidan.module.InsuranceCompany;
import com.dudu.soa.baoxian.kaidan.module.InsuranceInfo;
import com.dudu.soa.baoxian.kaidan.module.InsuranceType;
import com.dudu.soa.basedata.employee.api.ApiBaseDataEmployee;
import com.dudu.soa.basedata.employee.module.Employee;
import com.dudu.soa.basedata.employee.module.ServiceAdvisor;
import com.dudu.soa.customercenter.customer.api.ApiCustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfo;
import com.dudu.soa.customercenter.customer.module.CustomerInfoParam;
import com.dudu.soa.lmbasedata.basedata.shop.api.ApiShopIntf;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopParam;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopQueryFruit;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 * 车险投保
 */
@Service
public class ChexiantoubaoService {
    /**
     * 引入保险开单api
     */
    @Reference(version = "1.0", owner = "zhangyuanlei")
    private APIBaoXainKaiDan aPIBaoXainKaiDan;
    /**
     * 引入保险类型api
     */
    @Reference(version = "1.0")
    private APIBaoXianType aPIBaoXianType;
    /**
     * 获取联盟总部信息
     */
    @Reference(version = "0.0.1", owner = "lichengjia")
    private ApiShopIntf apiShopIntf;
    /**
     * 获取服务顾问接口
     */
    @Reference(version = "1.0", owner = "zhush")
    private ApiBaseDataEmployee apiBaseDataEmployee;
    /**
     * 获取客户信息
     */
    @Reference(version = "0.0.1", timeout = 3000)
    private ApiCustomerInfo apiCustomerInfo;


    /**
     * 获取保险公司接口
     *
     * @return 保险公司列表
     */
    public List<BaoXianGongSi> baoxianGongSi() {
        List<BaoXianGongSi> baoXianGongSis = aPIBaoXianType.selectInsuranceCompany();
        return baoXianGongSis;
    }

    /**
     * 获取保险类型接口
     *
     * @return 保险类型列表
     */
    public List<BaoXianType> baoXianTypes() {
        BaoXianType baoXianType = new BaoXianType();
        BaoXianPCZiDian baoXianPCZiDian = new BaoXianPCZiDian();
        List<BaoXianType> baoXianTypes = aPIBaoXianType.selectBaoXianShuJu();
        return baoXianTypes;
    }

    /**
     * @param request 获取值
     * @return Integer
     * @throws ParseException 异常
     */
    public Integer baoXianTiJiao(HttpServletRequest request) throws ParseException {
        InsuranceBill insuranceBill = new InsuranceBill();
        //获取店铺编码
        String shopCode = request.getParameter("mineShopCode");
        if (null != shopCode && !"".equals(shopCode)) {
            insuranceBill.setShopCode(shopCode);
        }
        //获取店铺联盟编码
        String shopCodeLm = request.getParameter("unionHeadquarters");
        if (null != shopCodeLm && !"".equals(shopCodeLm)) {
            insuranceBill.setShopCodeLm(shopCodeLm);
        }
        //获取服务顾问
        String serviceConsultant = request.getParameter("serviceConsultant");
        if (null != serviceConsultant && !"".equals(serviceConsultant)) {
            int serviceConsultantId = Integer.parseInt(serviceConsultant);
            insuranceBill.setServiceConsultantId(serviceConsultantId);
        }

        //获取行驶证图片1
        String drivingLicenseImg1 = request.getParameter("driving_1");
        if (null != drivingLicenseImg1 && !"".equals(drivingLicenseImg1)) {
            insuranceBill.setDrivingLicenseImg1(drivingLicenseImg1);
        }
        //获取行驶证图片2
        String drivingLicenseImg2 = request.getParameter("driving_2");
        if (null != drivingLicenseImg2 && !"".equals(drivingLicenseImg2)) {
            insuranceBill.setDrivingLicenseImg2(drivingLicenseImg2);
        }
        //获取身份证图片1
        String idCardImg1 = request.getParameter("filepath_1");
        if (null != idCardImg1 && !"".equals(idCardImg1)) {
            insuranceBill.setIdCardImg1(idCardImg1);
        }
        //获取身份证图片2
        String idCardImg2 = request.getParameter("filepath_2");
        if (null != idCardImg2 && !"".equals(idCardImg2)) {
            insuranceBill.setIdCardImg2(idCardImg2);
        }
        CustomerModel cm = new CustomerModel();
        //获取车牌号
        String plateNumber = request.getParameter("car_number");
        if (null != plateNumber && !"".equals(plateNumber)) {
            cm.setPlateNumber(plateNumber);
        }
        //获取客户id
        String customerId1 = request.getParameter("customerId");
        if (null != customerId1 && !"".equals(customerId1)) {
            int customerId = Integer.parseInt(customerId1);
            cm.setId(customerId);

        }

        //获取客户手机号
        String customerMobile = request.getParameter("phone_number");
        if (null != customerMobile && !"".equals(customerMobile)) {
            cm.setCustomerMobile(customerMobile);
        }
        //获取客户姓名
        String customerName = request.getParameter("your_name");
        if (null != customerName && !"".equals(customerName)) {
            cm.setCustomerName(customerName);
        }
        //获取注册日期
        String registrationDate = request.getParameter("registration_date");
        if (null != registrationDate && !"".equals(registrationDate)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = simpleDateFormat.parse(registrationDate);
            cm.setCreateTime(parse);
        }
        //设置客户的联盟编码
        if (null != shopCodeLm && !"".equals(shopCodeLm)) {
            cm.setShopCodeLm(shopCodeLm);
        }
        insuranceBill.setCustomerModel(cm);
        List<InsuranceCompany> companyList = new ArrayList<>();
        //获取保险公司
        String[] insuranceIds = request.getParameterValues("xianzhong");
        if (insuranceIds != null && insuranceIds.length > 0) {
            for (int i = 0; i < insuranceIds.length; i++) {
                InsuranceCompany ic = new InsuranceCompany();
                int companyId = Integer.parseInt(insuranceIds[i]);
                ic.setCompanyId(companyId);
                companyList.add(ic);
            }
            insuranceBill.setCompanyList(companyList);
        }
        insuranceBill.setCompanyList(companyList);
        List<InsuranceType> typeList = new ArrayList<>();
        //获取保险类型
        String[] insuranceTypeIds = request.getParameterValues("chexian");
        if (insuranceTypeIds != null && insuranceTypeIds.length > 0) {
            for (int i = 0; i < insuranceTypeIds.length; i++) {
                InsuranceType it = new InsuranceType();
                //获取类型ID
                int insuranceTypeId = Integer.parseInt(insuranceTypeIds[i]);
                it.setInsuranceTypeId(insuranceTypeId);
                String exclusions = request.getParameter("bjmp_" + insuranceTypeIds[i]);
                if (null != exclusions && !"".equals(exclusions)) {
                    int exclusion = Integer.parseInt(exclusions);
                    it.setExclusion(exclusion);
                }
                String compensationLimits = request.getParameter("pcxe_" + insuranceTypeIds[i]);
                if (null != compensationLimits && !"".equals(compensationLimits)) {
                    int compensationLimit = Integer.parseInt(compensationLimits);
                    it.setCompensationLimit(compensationLimit);
                }
                String guaranteeNumbers = request.getParameter("bzrs_" + insuranceTypeIds[i]);
                if (null != guaranteeNumbers && !"".equals(guaranteeNumbers)) {
                    int guaranteeNumber = Integer.parseInt(guaranteeNumbers);
                    it.setGuaranteeNumber(guaranteeNumber);

                }
                typeList.add(it);
            }
            insuranceBill.setTypeList(typeList);
        }
        Integer integer = aPIBaoXainKaiDan.addInsuranceOrder(insuranceBill);
        return integer;
    }

    /**
     * 查询保险
     *
     * @param baoXianParamList 查询条件
     * @return List<BaoXianList>
     */
    public List<BaoXianList> queryInsurance(BaoXianParamList baoXianParamList) {
        List<BaoXianList> baoXianLists = aPIBaoXainKaiDan.queryInsuranceList(baoXianParamList);

        return baoXianLists;
    }

    /**
     * 查询服务顾问
     *
     * @param shopCode 店铺编码
     * @return 服务顾问列表
     */
    public List<ServiceAdvisor> queryFuWuGuWen(String shopCode) {
        Employee employee = new Employee();
        employee.setShopCode(shopCode);
        List<ServiceAdvisor> list = apiBaseDataEmployee.getServiceAdvisor(employee);
        return list;
    }

    /**
     * 查询联盟总部
     *
     * @return 联盟总部列表
     */
    public List<ShopQueryFruit> queryLianMengZB() {
        ShopParam shopParam = new ShopParam();
        shopParam.setType("1");
        return apiShopIntf.queryShopInfo(shopParam);
    }


    /**
     * 查询车辆信息
     *
     * @param parameter     车牌号
     * @param xinxishopcode 信息的商店编码
     * @return 用户车辆信息
     */
    public List<CustomerInfo> queryCheLiangXinXi(String parameter, String xinxishopcode) {
        CustomerInfoParam customerInfoParam = new CustomerInfoParam();
        customerInfoParam.setPlateNumber(parameter);
        customerInfoParam.setShopCode(xinxishopcode);
        List<CustomerInfo> customerInfos = apiCustomerInfo.queryCustomerList(customerInfoParam);
        if (customerInfos == null || customerInfos.size() == 0) {
            return null;
        }
        return customerInfos;
    }

    /**
     * 获取保险公司列表展示
     *
     * @param httpSession 域信息
     * @return 保险公司信息列表
     */
    public List<BaoXianList> insuranceCompanyList(HttpSession httpSession) {
        //获取车牌号信息
        String carId = (String) httpSession.getAttribute("DUDUCHEWANG_CarId");
        //获取用户信息
        Integer customerId = (Integer) httpSession.getAttribute("customerId");
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        baoXianParamList.setCarId(carId);
        baoXianParamList.setCustomerId(customerId);
        List<BaoXianList> baoXianLists = aPIBaoXainKaiDan.queryInsuranceList(baoXianParamList);
        return baoXianLists;
    }

    /**
     * 根据保险公司获取保险信息列表
     *
     * @param request 获取域信息
     * @return 保险信息列表
     */
    public List<InsuranceInfo> insuranceInfoByCompany(HttpServletRequest request) {
        //获取订单编号
        String orderNumb = request.getParameter("orderNumb");
        //获取公司id
        String companyids = request.getParameter("companyid");
        int companyid = Integer.parseInt(companyids);
        InsuranceInfo insuranceInfo = new InsuranceInfo();
        insuranceInfo.setOrderNumb(orderNumb);
        insuranceInfo.setCompanyid(companyid);
        List<InsuranceInfo> insuranceInfos = aPIBaoXainKaiDan.queryInsuranceInfoByCompany(insuranceInfo);
        return insuranceInfos;
    }

    /**
     * 获取客户信息
     *
     * @param httpSession 域数据
     * @param request     域数据
     * @return 客户信息
     */
    public CustomerInfo customerInfoById(HttpSession httpSession, HttpServletRequest request) {
        //获取订单编号
        String orderNumbs = request.getParameter("orderNumb");
        long orderNumb = Long.parseLong(orderNumbs);
        //根据订单编号查询customerid
        BaoXianKaiDan baoXianKaiDan = new BaoXianKaiDan();
        baoXianKaiDan.setOrderNumb(orderNumb);
        BaoXianKaiDan baoXianKaiDan1 = aPIBaoXainKaiDan.queryCustomerIdByOrderNumb(baoXianKaiDan);
        Integer customerId = baoXianKaiDan1.getCustomerId();
        CustomerInfoParam customerInfoParam = new CustomerInfoParam();
        customerInfoParam.setId(customerId);
        CustomerInfo customerById = apiCustomerInfo.getCustomerById(customerInfoParam);
        return customerById;
    }
}
