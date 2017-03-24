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
import com.dudu.soa.customerCenter.customer.module.CustomerDemandParam;
import com.dudu.soa.customerCenter.customer.module.CustomerDemandReturn;
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
    public String baoXianTiJiao(HttpServletRequest request, BaoXianKaiDan baoXianKaiDan, HttpSession httpSession) {
        //保险开单
        String customerId = request.getParameter("customerId");
        if(null!=customerId&& !"".equals(customerId) ){
            int customerId1 = Integer.parseInt(customerId);
            baoXianKaiDan.setCustomerId(customerId1);
        }

       //获取系统时间
        //String kaiDanDate = request.getParameter("aaaaa");
        String assistant = request.getParameter("serviceConsultant");
        if(null!=assistant&& !"".equals(assistant) ){
            baoXianKaiDan.setAssistant(assistant);
        }
        String shopcode_lm = request.getParameter("unionHeadquarters");
        if(null!=assistant&& !"".equals(assistant) ){
            baoXianKaiDan.setShopcode_lm(shopcode_lm);
        }
        String xingShiZhengImg = request.getParameter("driving_1");
        baoXianKaiDan.setXingShiZhengImg(xingShiZhengImg);
        System.out.println("======身份证图片========"+xingShiZhengImg);
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
        if(insurancetypeIds!=null && insurancetypeIds.length>0) {
            for (int i = 0; i < insurancetypeIds.length; i++) {
                BaoXianKaiDanXiangQing baoXianKaiDanXiangQing = new BaoXianKaiDanXiangQing();
                int insurancetypeId = Integer.parseInt(insurancetypeIds[i]);
                baoXianKaiDanXiangQing.setInsurancetypeId(insurancetypeId);
                String buJiMianPeiTypes = request.getParameter("bjmp_" + insurancetypeIds[i]);
                if(null!=buJiMianPeiTypes&& !"".equals(buJiMianPeiTypes) ){
                    int buJiMianPeiType = Integer.parseInt(buJiMianPeiTypes);
                    baoXianKaiDanXiangQing.setBuJiMianPeiType(buJiMianPeiType);
                }
                String dictionaryIds = request.getParameter("pcxe_" + insurancetypeIds[i]);
                if(null!=dictionaryIds&& !"".equals(dictionaryIds) ){
                    int dictionaryId = Integer.parseInt(dictionaryIds);
                    baoXianKaiDanXiangQing.setDictionaryId(dictionaryId);
                }
                String baoZhangRenShus = request.getParameter("bzrs_" + insurancetypeIds[i]);
                if(null!=baoZhangRenShus&& !"".equals(baoZhangRenShus) ){
                    int baoZhangRenShu = Integer.parseInt(baoZhangRenShus);
                    baoXianKaiDanXiangQing.setBaoZhangRenShu(baoZhangRenShu);
                }

                //String kaiDanId=request.getParameter("pcxe_3");
                //String price=request.getParameter("pcxe_3");

                baoXianKaiDanXiangQingList.add(baoXianKaiDanXiangQing);

            }
            baoXianKaiDan.setBaoXianKaiDanXiangQing(baoXianKaiDanXiangQingList);
        }

        //保险公司
        String[] insuranceIds =request.getParameterValues("xianzhong");
        List<BaoXianKaiDanGongSi> baoXianKaiDanGongSi1 = new ArrayList<BaoXianKaiDanGongSi>();
        if(insuranceIds!=null && insuranceIds.length>0) {
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

    public List<ServiceAdvisor> queryFuWuGuWen(String shopCode) {
        Employee employee = new Employee();
        employee.setShopCode(shopCode);
        List<ServiceAdvisor>  list= apiBaseDataEmployee.getServiceAdvisor(employee);
        return list;
    }

    public List<ShopQueryFruit> queryLianMengZB() {
        ShopParam shopParam=new ShopParam();
        shopParam.setType("1");
        return apiShopIntf.queryShopInfo(shopParam);
    }

    public List<CustomerInfo>  queryCheLiangXinXi(String parameter,String xinxi_shopcode) {
        CustomerInfoParam customerInfoParam=new CustomerInfoParam();
        customerInfoParam.setPlateNumber(parameter);
        customerInfoParam.setShopCode(xinxi_shopcode);
        List<CustomerInfo> customerInfos = apiCustomerInfo.queryCustomerList(customerInfoParam);
        return customerInfos;
    }

}
