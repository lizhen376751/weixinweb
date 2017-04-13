package com.dudu.weixin.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.lmk.api.ApiLianmengkaOperateIntf;
import com.dudu.soa.lmk.operate.module.LianMengKaFaKaSaveModule;
import com.dudu.soa.lmk.operate.module.LiangmengKaQueryModule;
import com.dudu.soa.lmk.operate.module.LianmengKaResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmCustQueryModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmCustResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftQueryModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;
import com.dudu.soa.lmk.operate.module.LmkCardGenParam;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.soa.lmk.wxlmknoactive.api.ApiNoActive;
import com.dudu.soa.lmk.wxlmknoactive.module.NoActive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联盟卡相关服务
 * Created by lizhen on 2017/3/15.
 * 原LianMengKa
 */
@Service
public class LianMengKaService {
    /**
     * 引入联盟卡激活的接口
     */
    @Reference(version = "1.0", owner = "lizhen")
    private ApiNoActive apiNoActive;
    /**
     * 引入联盟用户信息接口
     */
    @Autowired
    private WxCustomerService wxCustomerService;
    /**
     *
     */
    @Autowired
    private CommonToolsService commonTools;
    /**
     * 引入联盟卡信息接口
     */
    @Reference(version = "0.0.1")
    private ApiLianmengkaOperateIntf apiLianmengkaOperateIntf;


    /**
     * 联盟卡的激活
     *
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
            if (noActive1.getIsactive().equals("1") || noActive1.getIsactive() == "1") {
                //已经激活
                return "1";
            } else {
                //没有激活,修改为激活状态
                noActive.setIsactive("1");
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
                //激活成功
                return "2";
            }
        }
        return "0";
    }

    /**
     * 查询联盟卡剩余次数  queryXmkLeftCount   查询联盟卡消费明细 queryXiangmukaCustRecords
     *
     * @param shopcode  shopcode
     * @param carHaoPai 车牌号码
     * @return 联盟卡列表
     */

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

    /**
     * @param shopcode  shopcode
     * @param cardNo    联盟卡号
     * @param carHaoPai 车牌号
     * @return 联盟卡列表
     */
    public List<LianmengkaXmCustResultModule> queryLmkXiaoFeiMX(String shopcode, String cardNo, String carHaoPai) {

        List<LianmengkaXmCustResultModule> results = null;
        try {
            LianmengkaXmCustQueryModule queryModule = new LianmengkaXmCustQueryModule();
            queryModule.setBrand_code(shopcode);
            queryModule.setCard_number(cardNo);
            queryModule.setCar_haopai(carHaoPai);
            results = apiLianmengkaOperateIntf.queryXiangmukaCustRecords(queryModule);
            if (results == null || results.size() == 0) {
                return null;
            } else {
                //把消费店铺编码 替换为 店铺名称
                for (int i = 0; i < results.size(); i++) {
                    LianmengkaXmCustResultModule lianmengkaXmCustResultModule = results.get(i);
                    String custcode = lianmengkaXmCustResultModule.getCust_code();
                    String custshopName = commonTools.getShopName(custcode);
                    //单纯的查询,把custcode替换成了cust_shopName
                    lianmengkaXmCustResultModule.setCust_code(custshopName);

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * @param shopcode  shopcode
     * @param cardNo    联盟卡号
     * @param carHaoPai 车牌号
     * @return 联盟卡列表
     */
    public List<LianmengKaResultModule> getXmkCardInfo(String shopcode, String cardNo, String carHaoPai) {
        System.out.println(shopcode + "," + cardNo + "," + carHaoPai);
        List<LianmengKaResultModule> results = null;
        LiangmengKaQueryModule queryModule = new LiangmengKaQueryModule();
        queryModule.setProduct_shopcode(shopcode); //联盟总部编码
        queryModule.setCard_number(cardNo); //卡号
        queryModule.setCar_haopai(carHaoPai);
        queryModule.setCard_type("2"); //1充值卡，2项目卡

        results = apiLianmengkaOperateIntf.getLianmengCustomerCardInfos(queryModule);

        if (results != null && results.size() > 0) {
            LianmengKaResultModule lianmengKaResultModule = results.get(0);
            if (lianmengKaResultModule != null) {
                String sellcode = lianmengKaResultModule.getSell_code().toString();

                //查询出店铺列表图片
                String shopListImg = commonTools.getShopListImg(sellcode);
                //TODO 后期需要把shopListImg放入results结果集,暂时存入Customer_mobile里面
                lianmengKaResultModule.setCustomer_mobile(shopListImg);
                //把发卡店铺编码 替换为 店铺名称
                String sellshopName = commonTools.getShopName(sellcode);
                //TODO 后期需要把sell_shopName放入results结果集,暂时存入sell_shopName里面
                lianmengKaResultModule.setCar_haopai(sellshopName);
                System.out.println("+============" + sellcode + shopListImg + sellshopName);

            }
        }

        return results;
    }

    /**
     * 获取联盟卡二维码
     *
     * @param cardid   卡的id
     * @param itemcode 项目编号
     * @param typeflg  卡标识 1:项目 2:商品
     * @return 字符串
     */
    public String getXmkQRCode(String cardid, String itemcode, String typeflg) {

        cardid = cardid == null || "null".equals(cardid) || "".equals(cardid) ? "0" : cardid;
        typeflg = typeflg == null || "null".equals(typeflg) || "".equals(typeflg) ? "0" : typeflg;
        String result = "";
        try {
            LmkCardGenParam queryModule = new LmkCardGenParam();
            queryModule.setCardId(Long.parseLong(cardid));
            queryModule.setItemCode(itemcode);
            queryModule.setTypeFlg(Integer.parseInt(typeflg));
            result = apiLianmengkaOperateIntf.getOneCardCode(queryModule);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
