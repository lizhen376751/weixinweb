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
import com.dudu.soa.baoxian.kaidan.module.ClientInsuranceResult;
import com.dudu.soa.baoxian.kaidan.module.ClientInsuranceTypeParam;
import com.dudu.soa.baoxian.kaidan.module.CustomerCompany;
import com.dudu.soa.baoxian.kaidan.module.CustomerModel;
import com.dudu.soa.baoxian.kaidan.module.Insurance;
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
import com.dudu.soa.lmbasedata.basedata.relation.api.ApiRelationIntf;
import com.dudu.soa.lmbasedata.basedata.relation.module.ShopSearchStructureParam;
import com.dudu.soa.lmbasedata.basedata.relation.module.ShopStructureParam;
import com.dudu.soa.lmbasedata.basedata.shop.api.ApiShopIntf;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopParam;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopQueryFruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
     * 打印日志
     */
    private static Logger logger = LoggerFactory.getLogger(ChexiantoubaoService.class);
    /**
     * 引入保险开单api
     */
    @Reference(owner = "zhangyuanlei")
    private APIBaoXainKaiDan aPIBaoXainKaiDan;
    /**
     * 引入保险类型api
     */
    @Reference(timeout = 1200000)
    private APIBaoXianType aPIBaoXianType;
    /**
     * 获取联盟总部信息
     */
    @Reference(owner = "lichengjia")
    private ApiShopIntf apiShopIntf;
    /**
     * 获取服务顾问接口
     */
    @Reference(owner = "zhush")
    private ApiBaseDataEmployee apiBaseDataEmployee;
    /**
     * 获取客户信息
     */
    @Reference(timeout = 3000)
    private ApiCustomerInfo apiCustomerInfo;
    /**
     * 根据店铺查询相关联盟
     */
    @Reference
    private ApiRelationIntf apiRelationIntf;

    /**
     * 获取保险公司接口
     *
     * @param shopCodeLm 联盟编码
     * @return List<BaoXianGongSi> 联盟相关的保险公司
     */
    public List<BaoXianGongSi> baoxianGongSi(String shopCodeLm) {
        List<BaoXianGongSi> baoXianGongSis = aPIBaoXianType.selectInsuranceCompany(shopCodeLm);
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
        logger.info(drivingLicenseImg1 + "身份证图片===============================================");
        if (null != idCardImg2 && !"".equals(idCardImg2)) {
            insuranceBill.setIdCardImg2(idCardImg2);
        }
        CustomerModel cm = new CustomerModel();
        //获取车牌号
        String plateNumber = request.getParameter("car_number");
        if (null != plateNumber && !"".equals(plateNumber)) {
            cm.setPlateNumber(plateNumber);
        }
        //获取车辆品牌id
        String carBrandId = request.getParameter("carBrandId");
        if (null != carBrandId && !"".equals(carBrandId)) {
            Integer integer = Integer.valueOf(carBrandId);
            cm.setVehicleBrand(integer);
        }
        //获取车系id
        String carSeriesId = request.getParameter("carSeriesId");
        if (null != carSeriesId && !"".equals(carSeriesId)) {
            Integer integer = Integer.valueOf(carSeriesId);
            cm.setCarSeries(integer);
        }
        //获取车型id
        String carModelId = request.getParameter("carModelId");
        if (null != carModelId && !"".equals(carModelId)) {
            Integer integer = Integer.valueOf(carModelId);
            cm.setVehicleType(integer);
        }
        //获取客户id
        String customerId1 = request.getParameter("customerId");
        if (null != customerId1 && !"".equals(customerId1)) {
            int customerId = Integer.parseInt(customerId1);
            cm.setId(customerId);
        } else {
            cm.setId(null);
        }
        //获取保险到期日
        String insuranceMaturityDate = request.getParameter("insurance_maturity_date");
        if (null != insuranceMaturityDate && !"".equals(insuranceMaturityDate)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = simpleDateFormat.parse(insuranceMaturityDate);
            cm.setInsuranceMaturityDate(parse);
        }
        //获取客户性别
        String sex = request.getParameter("sex");
        if (null != sex && !"".equals(sex)) {
            Integer integer1 = Integer.valueOf(sex);
            cm.setSex(integer1);
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
        //获取车辆识别代号
        String vehicleIdentificationCode = request.getParameter("car_model");
        if (null != vehicleIdentificationCode && !"".equals(vehicleIdentificationCode)) {
            cm.setVehicleIdentificationCode(vehicleIdentificationCode);
        }
        //获取车辆发动机号
        String engineNumber = request.getParameter("engine_number");
        if (null != engineNumber && !"".equals(engineNumber)) {
            cm.setEngineNumber(engineNumber);
        }
        //获取车辆使用性质
        String operationType = request.getParameter("property");
        if (null != operationType && !"".equals(operationType)) {
            cm.setOperationType(operationType);
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
     * app端查询保险列表
     *
     * @param baoXianParamList 查询条件
     * @return List<BaoXianList>
     */
    public List<Insurance> queryAppInsurance(BaoXianParamList baoXianParamList) {
        return aPIBaoXainKaiDan.queryAppInsuranceOrderList(baoXianParamList);
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
     * 查询店铺相关联盟
     *
     * @param shopCode 店铺编码
     * @return List<ShopQueryFruit> 返回的联盟
     */
    public List<ShopQueryFruit> queryLianMengZB(String shopCode) {
       /* ShopParam shopParam = new ShopParam();
        shopParam.setType("1";

        return apiShopIntf.queryShopInfo(shopParam);*/
        List<ShopQueryFruit> list = new ArrayList<>();
        ShopSearchStructureParam ssp = new ShopSearchStructureParam();
        ssp.setCode(shopCode);
        List<ShopStructureParam> shopStructureParams = apiRelationIntf.queryShopRelation(ssp);
        if (shopStructureParams != null && shopStructureParams.size() > 0) {
            for (ShopStructureParam sp : shopStructureParams) {
                String brandcode = sp.getBrandcode();
                ShopParam shopParam = new ShopParam();
                shopParam.setCode(brandcode);
                ShopQueryFruit shopInfo = apiShopIntf.getShopInfo(shopParam);
                list.add(shopInfo);
            }
        }
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
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

    /**
     * 获取客户端保险详情
     *
     * @param clientInsuranceTypeParam 查询参数
     * @return ClientInsuranceResult  查询结果返回
     */
    public ClientInsuranceResult getClientInsurance(ClientInsuranceTypeParam clientInsuranceTypeParam) {
        ClientInsuranceResult clientInsuranceResult = aPIBaoXainKaiDan.queryClientInsurace(clientInsuranceTypeParam);
        if (clientInsuranceResult != null) {
            return clientInsuranceResult;
        }
        return null;
    }

    /**
     * 更新客户购买保险的保险公司
     *
     * @param request 请求
     * @return Integer 受影响行数
     */
    public Integer updateInsuranceOrderCompany(HttpServletRequest request) {
        CustomerCompany customerCompany = new CustomerCompany();
        String shopCode = request.getParameter("shopCode");
        if (shopCode != null && !shopCode.equals("")) {
            customerCompany.setShopCode(shopCode);
        }
        String shopCodeLm = request.getParameter("shopCodeLm");
        if (shopCodeLm != null && !shopCodeLm.equals("")) {
            customerCompany.setShopCodeLm(shopCodeLm);
        }
        String orderNumb = request.getParameter("orderNumb");
        if (orderNumb != null && !orderNumb.equals("")) {
            customerCompany.setOrderNumb(orderNumb);
        }
        String companyId = request.getParameter("companyId");
        if (companyId != null) {
            customerCompany.setCompanyId(Integer.valueOf(companyId));
        }
        String totalPrice1 = request.getParameter("totalPrice");
        if (totalPrice1 != null) {
            customerCompany.setTotalPrice(new BigDecimal(Integer.valueOf(totalPrice1)));
        }
        Integer integer = aPIBaoXainKaiDan.updateInsuranceOrderCompany(customerCompany);
        if (integer != null) {
            return integer;
        }
        return null;
    }
}
