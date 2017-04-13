package com.dudu.weixin.service;

import com.dudu.soa.baoxian.kaidan.module.BaoXianList;
import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.baoxian.kaidan.module.Insurance;
import com.dudu.soa.baoxian.kaidan.module.InsuranceCompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     *
     * @param shopCode 店铺编码
     */
    public List<Insurance> queryBaoXianList(String shopCode){
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        if (null != shopCode && !"".equals(shopCode)) {
            List<String> list = new ArrayList<>();
            list.add(shopCode);
            baoXianParamList.setShopCode(list);
        }
        List<BaoXianList> baoXianLists = chexiantoubaoService.queryInsurance(baoXianParamList);
        List<Insurance> list = new ArrayList<>();
        String orderNun = "";
        for (int i = 0; i < baoXianLists.size(); i++) {
            if (i == 0) {
                BaoXianList bs = baoXianLists.get(0);
                Insurance is = new Insurance();
                is.setShopCode(bs.getShopCode());
                is.setOrderNumb(bs.getOrderNumb());
                orderNun = bs.getOrderNumb();
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
                if (baoXianLists.get(i).getOrderNumb().equals(list.get(list.size() - 1))) {
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
        }
        System.out.println(list.size() + "***************************************************************************");
        return list;
    }

}