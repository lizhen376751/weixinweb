package com.dudu.weixin.service;

import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.baoxian.kaidan.module.Insurance;
import com.dudu.weixin.mould.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        if (StringUtils.hasText(baoJiaState)) {
            baoXianParamList.setBaoJiaState(Integer.valueOf(baoJiaState));
        }
        if (StringUtils.hasText(paymentStatus)) {
            //当付款状态为1的时候,订单状态为2
            if (Integer.valueOf(paymentStatus) == 1) {
                baoXianParamList.setBaoJiaState(2);
                baoXianParamList.setPaymentStatus(1);
            } else {
                baoXianParamList.setPaymentStatus(Integer.valueOf(paymentStatus));
            }
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
