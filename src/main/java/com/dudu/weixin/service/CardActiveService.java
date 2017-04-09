package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.api.ApiLianmengkaOperateIntf;
import com.dudu.soa.lmk.operate.module.LianMengKaFaKaSaveModule;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.soa.lmk.wxlmknoactive.api.ApiNoActive;
import com.dudu.soa.lmk.wxlmknoactive.module.NoActive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 联盟卡的激活
 * Created by lizhen on 2017/4/9.
 */
@Service
public class CardActiveService {
    /**
     * 引入联盟卡激活的接口
     */
    @Reference(version = "0.0.1", owner = "lizhen")
    private ApiNoActive apiNoActive;
    /**
     * 引入联盟卡信息接口
     */
    @Reference(version = "0.0.1", owner = "cuiyuqing")
    private ApiLianmengkaOperateIntf apiLianmengkaOperateIntf;
    /**
     * 引入联盟用户信息接口
     */
    @Autowired
    private WxCustomerService wxCustomerService;

    /**
     * @param lmcode      联盟code
     * @param cardnum     卡号
     * @param activecode  激活码
     * @param platenumber 车牌号
     * @return 0代表提示卡号或激活码输入错误 1代表已经激活
     */
    public String active(String lmcode, String cardnum, String activecode, String platenumber) {
        NoActive noActive = new NoActive();
        noActive.setLmcode(lmcode);
        noActive.setCardnum(cardnum);
        noActive.setActivecode(activecode);
        //查询输入卡号和激活码是否存在
        NoActive noActive1 = apiNoActive.getNOActive(noActive);
        //判断卡号和激活码是存在,不存在提示输入错误
        if (noActive1 != null) {
            //判断该卡是否已经激活
            if (noActive1.getIsactive().equals("true") || noActive1.getIsactive() == "true") {
                //已经激活
                return "1";
            } else {
                //没有激活,修改为激活状态
                noActive.setIsactive("true");
                //查询联盟用户id
                WxCustomer wxCustomer = wxCustomerService.getWxCustomer(platenumber, lmcode);
                apiNoActive.updateNOActive(noActive);
                //将此激活的联盟卡保存到激活的库中
                LianMengKaFaKaSaveModule saveModule = new LianMengKaFaKaSaveModule();
                saveModule.setCard_number(noActive1.getCardnum());
                saveModule.setCard_type(2);
                saveModule.setCust_id(wxCustomer.getId());
                saveModule.setProduct_code(noActive1.getCardcode());
                saveModule.setProduct_shopcode(lmcode);
                apiLianmengkaOperateIntf.addLianmengKa(saveModule);
            }
        }
        return "0";
    }
}
