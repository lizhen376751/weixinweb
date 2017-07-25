package com.dudu.weixin.service;

import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.baoxian.kaidan.module.Insurance;
import com.dudu.weixin.mould.PageResult;
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
     *
     * @param request 请求
     * @return List<Insurance> 保险列表
     */
    public PageResult queryBaoXianList(HttpServletRequest request) {
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        String shopCode = request.getParameter("shopCode");
        String carNumber = request.getParameter("carNumber");
        String paymentStatus = request.getParameter("paymentStatus");
        String baoJiaState = request.getParameter("baoJiaState");

        if (null != paymentStatus && !"".equals(paymentStatus) && null != baoJiaState && !"".equals(baoJiaState)) {
            int fukuanState = Integer.parseInt(paymentStatus);
            int dingDanState = Integer.parseInt(baoJiaState);
            if (fukuanState == 1) {
                dingDanState = 2;
            }
            baoXianParamList.setBaoJiaState(dingDanState);
            baoXianParamList.setPaymentStatus(fukuanState);
        }
        if (null != shopCode && !"".equals(shopCode)) {
            List<String> list = new ArrayList<>();
            list.add(shopCode);
            baoXianParamList.setShopCode(list);
        }
        if (null != carNumber && !"".equals(carNumber)) {
            baoXianParamList.setCarId(carNumber);
        }

        List<Insurance> list = chexiantoubaoService.queryAppInsurance(baoXianParamList);

        return new PageResult<Insurance>(list);
    }


}
