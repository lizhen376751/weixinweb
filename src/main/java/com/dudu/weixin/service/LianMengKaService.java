package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.api.ApiLianmengkaOperateIntf;
import com.dudu.soa.lmk.operate.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 * 原LianMengKa
 */
@Service
public class LianMengKaService {
    @Autowired
    private CommonTools commonTools;

    @Reference(version = "0.0.1")
    private ApiLianmengkaOperateIntf apiLianmengkaOperateIntf;
    //	查询联盟卡剩余次数  queryXmkLeftCount   查询联盟卡消费明细 queryXiangmukaCustRecords
    public List<LianmengkaXmLeftResultModule> queryLmkInfo(String shopcode, String carHaoPai) {
        //调用queryLmkInfo接口
        List<LianmengkaXmLeftResultModule> results = null;
        try {
            LianmengkaXmLeftQueryModule queryModule = new LianmengkaXmLeftQueryModule();
            queryModule.setBrand_code(shopcode);
            queryModule.setCar_haopai(carHaoPai);
            results = apiLianmengkaOperateIntf.queryXmkLeftCount(queryModule);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;

    }

    public List<LianmengkaXmCustResultModule> queryLmkXiaoFeiMX(String shopcode, String cardNo, String carHaoPai) {

        List<LianmengkaXmCustResultModule> results = null;
        try {
            LianmengkaXmCustQueryModule queryModule = new LianmengkaXmCustQueryModule();
            queryModule.setBrand_code(shopcode);
            queryModule.setCard_number(cardNo);
            queryModule.setCar_haopai(carHaoPai);
            results = apiLianmengkaOperateIntf.queryXiangmukaCustRecords(queryModule);

            //把消费店铺编码 替换为 店铺名称
            for (int i = 0; i < results.size(); i++) {
                LianmengkaXmCustResultModule lianmengkaXmCustResultModule = results.get(i);
                String cust_code = lianmengkaXmCustResultModule.getCust_code();
                String cust_shopName = commonTools.getShopName(cust_code);
                //单纯的查询,把custcode替换成了cust_shopName
                lianmengkaXmCustResultModule.setCust_code(cust_shopName);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<LianmengKaResultModule> getXmkCardInfo(String shopcode, String cardNo, String carHaoPai) {

        List<LianmengKaResultModule> results = null;

            LiangmengKaQueryModule queryModule = new LiangmengKaQueryModule();
            queryModule.setProduct_shopcode(shopcode);//联盟总部编码
            queryModule.setCard_number(cardNo);//卡号
            queryModule.setCar_haopai(carHaoPai);
            queryModule.setCard_type("2");//1充值卡，2项目卡

            results = apiLianmengkaOperateIntf.getLianmengCustomerCardInfos(queryModule);

            if (results != null && results.size() > 0) {
                LianmengKaResultModule lianmengKaResultModule = results.get(0);
                if (lianmengKaResultModule != null) {
                    String sell_code = lianmengKaResultModule.getSell_code().toString();

                    //查询出店铺列表图片
                    String shopListImg = commonTools.getShopListImg(sell_code);
                    //TODO 后期需要把shopListImg放入results结果集,暂时存入Customer_mobile里面
                    lianmengKaResultModule.setCustomer_mobile(shopListImg);
                    //把发卡店铺编码 替换为 店铺名称
                    String sell_shopName = commonTools.getShopName(sell_code);
                    //TODO 后期需要把sell_shopName放入results结果集,暂时存入sell_shopName里面
                    lianmengKaResultModule.setCar_haopai(sell_shopName);
                    System.out.println("+============"+sell_code+shopListImg+sell_shopName);

                }
            }

        return results;
    }

    /**
     * 获取联盟卡二维码
     *
     * @param card_id
     * @param item_code
     * @param type_flg
     * @return
     */
    public String getXmkQRCode(String card_id, String item_code, String type_flg) {

        card_id = card_id == null || "null".equals(card_id) || "".equals(card_id) ? "0" : card_id;
        type_flg = type_flg == null || "null".equals(type_flg) || "".equals(type_flg) ? "0" : type_flg;
        String result = "";
        try {
            LmkCardGenParam queryModule = new LmkCardGenParam();
            queryModule.setCardId(Long.parseLong(card_id));
            queryModule.setItemCode(item_code);
            queryModule.setTypeFlg(Integer.parseInt(type_flg));
            result = apiLianmengkaOperateIntf.getOneCardCode(queryModule);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
