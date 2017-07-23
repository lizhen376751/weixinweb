package com.dudu.weixin.service;

import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.baoxian.kaidan.module.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangchengtai on 2017/4/13.
 */
@Service
public class CheXianService {
    /**
     * 车险投保
     */
    @Autowired
    private ChexiantoubaoService chexiantoubaoService;

    /**
     * 获取保险列表
     * @param request 请求
     * @return List<Insurance> 保险列表
     */
    public List<Insurance> queryBaoXianList(HttpServletRequest request) {
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        String shopCode = request.getParameter("shopCode");
        String carNumber = request.getParameter("carNumber");
        if (null != shopCode && !"".equals(shopCode)) {
            List<String> list = new ArrayList<>();
            list.add(shopCode);
            baoXianParamList.setShopCode(list);
        }
        if (null != carNumber && !"".equals(carNumber)) {
            baoXianParamList.setCarId(carNumber);
        }
        List<Insurance>  list = chexiantoubaoService.queryAppInsurance(baoXianParamList);
        /*List<Insurance> list = new ArrayList<>();
        for (int i = 0; i < baoXianLists.size(); i++) {
            if (i == 0) {
                BaoXianList bs = baoXianLists.get(0);
                Insurance is = new Insurance();
                is.setShopCode(bs.getShopCode());
                is.setOrderNumb(bs.getOrderNumb());
                is.setKaiDanDate(bs.getKaiDanDate());
                is.setFuKuanFlag(bs.getFuKuanFlag());
                is.setAssistant(bs.getAssistant());
                is.setCarId(bs.getCarId());
                is.setCustomerId(bs.getCustomerId());
                is.setShopcodelm(bs.getShopcodelm());
                List<InsuranceCompanyDetails> list1 = new ArrayList<>();
                InsuranceCompanyDetails icd = new InsuranceCompanyDetails();
                icd.setTotalPrices(bs.getTotalPrices());
                icd.setInsurancename(bs.getInsurancename());
                icd.setBaoJiaState(bs.getBaoJiaState());
                icd.setCompanyid(bs.getCompanyid());
                list1.add(icd);
                is.setList(list1);
                list.add(is);
            } else {
                if (baoXianLists.get(i).getOrderNumb().equals(list.get(list.size() - 1).getOrderNumb())) {
                    InsuranceCompanyDetails is = new InsuranceCompanyDetails();
                    is.setCompanyid(baoXianLists.get(i).getCompanyid());
                    is.setBaoJiaState(baoXianLists.get(i).getBaoJiaState());
                    is.setInsurancename(baoXianLists.get(i).getInsurancename());
                    is.setTotalPrices(baoXianLists.get(i).getTotalPrices());
                    list.get(list.size() - 1).getList().add(is);
                } else {
                    Insurance ic = new Insurance();
                    ic.setShopcodelm(baoXianLists.get(i).getShopcodelm());
                    ic.setCustomerId(baoXianLists.get(i).getCustomerId());
                    ic.setCarId(baoXianLists.get(i).getCarId());
                    ic.setAssistant(baoXianLists.get(i).getAssistant());
                    ic.setFuKuanFlag(baoXianLists.get(i).getFuKuanFlag());
                    ic.setKaiDanDate(baoXianLists.get(i).getKaiDanDate());
                    ic.setOrderNumb(baoXianLists.get(i).getOrderNumb());
                    ic.setShopCode(baoXianLists.get(i).getShopCode());
                    List<InsuranceCompanyDetails> list1 = new ArrayList<>();
                    InsuranceCompanyDetails icd = new InsuranceCompanyDetails();
                    icd.setTotalPrices(baoXianLists.get(i).getTotalPrices());
                    icd.setInsurancename(baoXianLists.get(i).getInsurancename());
                    icd.setBaoJiaState(baoXianLists.get(i).getBaoJiaState());
                    icd.setCompanyid(baoXianLists.get(i).getCompanyid());
                    list1.add(icd);
                    ic.setList(list1);
                    list.add(ic);
                }
            }
        }*/

        return list;
    }

}
