package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.baoxian.base.api.APIBaoXianType;
import com.dudu.soa.baoxian.base.module.BaoXianGongSi;
import com.dudu.soa.baoxian.base.module.BaoXianPCZiDian;
import com.dudu.soa.baoxian.base.module.BaoXianType;
import com.dudu.soa.baoxian.kaidan.api.APIBaoXainKaiDan;
import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDan;
import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDanGongSi;
import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDanXiangQing;
import com.dudu.soa.basedata.employee.api.ApiBaseDataEmployee;
import com.dudu.soa.basedata.employee.module.Employee;
import com.dudu.soa.basedata.employee.module.ServiceAdvisor;
import com.dudu.soa.customerCenter.customer.api.ApiCustomerDemand;
import com.dudu.soa.customerCenter.customer.api.ApiCustomerInfo;
import com.dudu.soa.customerCenter.customer.module.CustomerInfo;
import com.dudu.soa.customerCenter.customer.module.CustomerInfoParam;
import com.dudu.soa.lmbasedata.basedata.shop.api.ApiShopIntf;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopParam;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopQueryFruit;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private APIBaoXainKaiDan baoXianTypeImpl;
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
    @Reference(version = "0.0.1")
    private ApiCustomerInfo apiCustomerInfo;
    /**
     * 获取客户信息接口
     */
    @Reference(version = "0.0.1")
    private ApiCustomerDemand apiCustomerDemand;

    /**
     * 获取保险公司接口
     * @return
     */
    public  List<BaoXianGongSi>  baoxianGongSi(){
        List<BaoXianGongSi> baoXianGongSis = aPIBaoXianType.selectInsuranceCompany();
        return baoXianGongSis;
    }

    /**
     * 获取保险类型接口
     * @return
     */
    public  List<BaoXianType>  baoXianTypes(){
        BaoXianType baoXianType = new BaoXianType();
        BaoXianPCZiDian baoXianPCZiDian = new BaoXianPCZiDian();
        List<BaoXianType> baoXianTypes = aPIBaoXianType.selectBaoXianShuJu();
        return baoXianTypes;
    }

    /**
     * 保险提交后持久化到数据库
     * @param request 域
     * @param baoXianKaiDan 实体类
     * @param httpSession session域
     * @return
     */
    public String baoXianTiJiao(HttpServletRequest request, BaoXianKaiDan baoXianKaiDan, HttpSession httpSession) {
        //保险开单
        //获取车牌号
        String  carId = (String) httpSession.getAttribute("DUDUCHEWANG_CarId");
        if (null != carId && !"".equals(carId) ){
            baoXianKaiDan.setCarId(carId);
        }
        String customerId = request.getParameter("customerId");
        if (null != customerId && !"".equals(customerId) ){
            int customerId1 = Integer.parseInt(customerId);
            baoXianKaiDan.setCustomerId(customerId1);
        }

       //获取系统时间
        //String kaiDanDate = request.getParameter("aaaaa");
        String assistant = request.getParameter("serviceConsultant");
        if (null != assistant && !"".equals(assistant) ){
            baoXianKaiDan.setAssistant(assistant);
        }
        String shopcode_lm = request.getParameter("unionHeadquarters");
        if (null != assistant && !"".equals(assistant) ){
            baoXianKaiDan.setShopcode_lm(shopcode_lm);
        }
        String xingShiZhengImg = request.getParameter("driving_1");
        baoXianKaiDan.setXingShiZhengImg(xingShiZhengImg);
        //System.out.println("======身份证图片========"+ xingShiZhengImg );
        String xingShiZhengImg2 = request.getParameter("driving_2");
        baoXianKaiDan.setXingShiZhengImg2(xingShiZhengImg2);
        String shenFenZhengImg = request.getParameter("filepath_1");
        baoXianKaiDan.setShenFenZhengImg(shenFenZhengImg);
        String shenFenZhengImg2 = request.getParameter("filepath_2");
        baoXianKaiDan.setShenFenZhengImg2(shenFenZhengImg2);
        //String remarks = request.getParameter("aaaaa");
        String shopCode = (String) httpSession.getAttribute("DUDUCHEWANG_shopcode");
        baoXianKaiDan.setShopCode(shopCode);
        //String totalPrice = request.getParameter("aaaaa");
       // String fuKuanFlag = request.getParameter("aaaaa");
        //String shiShou = request.getParameter("aaaaa");
        //String orderNumb = request.getParameter("aaaaa");


        //开单详情
        String[] insurancetypeIds = request.getParameterValues("chexian");
        //System.out.println("huoqude checian=========="+insurancetypeIds.length);
        List<BaoXianKaiDanXiangQing> baoXianKaiDanXiangQingList  = new ArrayList<BaoXianKaiDanXiangQing>();
        if (insurancetypeIds != null && insurancetypeIds.length> 0) {
            for (int i = 0; i < insurancetypeIds.length; i++) {
                BaoXianKaiDanXiangQing baoXianKaiDanXiangQing = new BaoXianKaiDanXiangQing();
                int insurancetypeId = Integer.parseInt(insurancetypeIds[i]);
                baoXianKaiDanXiangQing.setInsurancetypeId(insurancetypeId);
                String buJiMianPeiTypes = request.getParameter("bjmp_" + insurancetypeIds[i]);
                if (null != buJiMianPeiTypes && !"".equals(buJiMianPeiTypes) ){
                    int buJiMianPeiType = Integer.parseInt(buJiMianPeiTypes);
                    baoXianKaiDanXiangQing.setBuJiMianPeiType(buJiMianPeiType);
                }
                String dictionaryIds = request.getParameter("pcxe_" + insurancetypeIds[i]);
                if (null != dictionaryIds && !"".equals(dictionaryIds) ){
                    int dictionaryId = Integer.parseInt(dictionaryIds);
                    baoXianKaiDanXiangQing.setDictionaryId(dictionaryId);
                }
                String baoZhangRenShus = request.getParameter("bzrs_" + insurancetypeIds[i]);
                if (null != baoZhangRenShus && !"".equals(baoZhangRenShus) ){
                    int baoZhangRenShu = Integer.parseInt(baoZhangRenShus);
                    baoXianKaiDanXiangQing.setBaoZhangRenShu(baoZhangRenShu);
                }
                //获取保险公司id
                String[] insuranceIds = request.getParameterValues("xianzhong");
                List<BaoXianKaiDanGongSi> baoXianKaiDanGongSi1 = new ArrayList<BaoXianKaiDanGongSi>();
                if (insuranceIds != null && insuranceIds.length> 0) {
                    for (int j = 0; j < insuranceIds.length; j++) {
                        int companyId = Integer.parseInt(insuranceIds[j]);
                        BaoXianKaiDanGongSi baoXianKaiDanGongSi= new BaoXianKaiDanGongSi();
                        //baoXianKaiDanXiangQing.setCompanyId(companyId);
                        baoXianKaiDanGongSi.setCompanyId(companyId);
                        baoXianKaiDanGongSi1.add(baoXianKaiDanGongSi);
                    }
                    baoXianKaiDanXiangQing.setCompanyIds(baoXianKaiDanGongSi1);
                }
                //String kaiDanId=request.getParameter("pcxe_3");
                //String price=request.getParameter("pcxe_3");

                baoXianKaiDanXiangQingList.add(baoXianKaiDanXiangQing);

            }
            baoXianKaiDan.setBaoXianKaiDanXiangQing(baoXianKaiDanXiangQingList);
        }

        //保险公司
        String[] insuranceIds = request.getParameterValues("xianzhong");
        List<BaoXianKaiDanGongSi> baoXianKaiDanGongSi1 = new ArrayList<BaoXianKaiDanGongSi>();
        if (insuranceIds != null && insuranceIds.length> 0) {
            for (int i = 0; i < insuranceIds.length; i++) {
                BaoXianKaiDanGongSi baoXianKaiDanGongSi = new BaoXianKaiDanGongSi();
                int companyId = Integer.parseInt(insuranceIds[i]);
                baoXianKaiDanGongSi.setCompanyId(companyId);
                baoXianKaiDanGongSi1.add(baoXianKaiDanGongSi);
            }
            baoXianKaiDan.setBaoXianKaiDanGongSi(baoXianKaiDanGongSi1);
        }
        baoXianTypeImpl.BaoXianKaiDan(baoXianKaiDan);
        return null;
    }

    /**
     * 查询服务顾问
     * @param shopCode 店铺编码
     * @return
     */
    public List<ServiceAdvisor> queryFuWuGuWen(String shopCode) {
        Employee employee = new Employee();
        employee.setShopCode(shopCode);
        List<ServiceAdvisor>  list= apiBaseDataEmployee.getServiceAdvisor(employee);
        return list;
    }

    /**
     * 查询联盟总部
     * @return
     */
    public List<ShopQueryFruit> queryLianMengZB() {
        ShopParam shopParam= new ShopParam();
        shopParam.setType("1");
        return apiShopIntf.queryShopInfo(shopParam);
    }

    /**
     * 查询车辆信息
     * @param parameter 车牌号
     * @param xinxi_shopcode 信息的商店编码
     * @return
     */
    public List<CustomerInfo>  queryCheLiangXinXi(String parameter, String xinxi_shopcode) {
        CustomerInfoParam customerInfoParam= new CustomerInfoParam();
        customerInfoParam.setPlateNumber(parameter);
        customerInfoParam.setShopCode(xinxi_shopcode);
        List<CustomerInfo> customerInfos = apiCustomerInfo.queryCustomerList(customerInfoParam);
        return customerInfos;
    }

}
