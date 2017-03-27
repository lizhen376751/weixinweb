package com.dudu.weixin.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 原GetBuyRecord
 * 消费记录
 */
//TODO 后期合为一个接口(商品列表,一个项目列表,技师名字,支付方式)进行返回
@Service
public class BuyRecordService {
    /**
     * 常量无意义
     */
    private final int num2 = 10;

    /**
     * @param lmCode 联盟code
     * @param carNo  联盟卡号
     * @param top    页数
     * @return list集合
     * @Title 获取消费列表
     */
    public ArrayList getServiceListByLmcodeAndCarNo(String lmCode, String carNo, String top) {
        ArrayList list = new ArrayList();
        int num = Integer.valueOf(top) * num2;
        System.out.println(" getServiceListByLmcodeAndCarNo 下拉==top:" + top + "|");
        //TODO 后期调用接口
        return list;
    }

    /**
     * @param strId 不知道
     * @return
     * @throws Exception
     */
    public String getZhiFuFangShi(String strId) {
        String strName = "";
        //TODO 后期调用接口
        return strName;
    }

    /**
     * @param shopcode    店铺代码
     * @param wxpingzheng wx凭证
     * @return
     * @Title 获取项目列表
     */

    public ArrayList getItemAndSpList(String shopcode, String wxpingzheng) {
        ArrayList list = new ArrayList();
        //TODO 后期调用接口
        return list;
    }

    /**
     * 根据shopcode、员工id获取用户名称(获取技师的名字)
     *
     * @param shopcode code
     * @param code     code
     * @return 字符串
     */
    public String getSalerName(String shopcode, String code) {
        HashMap salerHash = new HashMap();
        String salerName = "";
        String[] codes = null;

        //员工id字符串转为数组
        if (code != null && !"".equals(code)) {
            codes = code.split(",");
        } else {
            return salerName;
        }


        if (codes == null || codes.length == 0) {
            return salerName;
        }

        //数组去重
        List<String> list = new ArrayList<String>();
        list.add(codes[0]);
        for (int i = 1; i < codes.length; i++) {
            if (list.toString().indexOf(codes[i]) == -1) {
                list.add(codes[i]);
            }
        }
        String[] arrRest = (String[]) list.toArray(new String[list.size()]);

        //TODO 后期调用接口
        return salerName;
    }

    /**
     *
     * @param shopcode
     * @param CarHaoPai
     * @param top
     * @return
     */
    public ArrayList listRecord(String shopcode, String CarHaoPai, String top) {
        ArrayList list = new ArrayList();
        int num = Integer.valueOf(top) * 10;
        System.out.println("下拉==" + top);
        //TODO 调用接口
        return list;
    }
    /**
     *
     * @param shopcode
     * @param CarHaoPai
     * @param top
     * @return
     */
    public String getItemRecord(String shopcode, String wxpingzheng) {
        String strRecord = "";
        ArrayList list = new ArrayList();
        //TODO 后期调用接口
        return strRecord;
    }
    /**
     *
     * @param shopcode
     * @param CarHaoPai
     * @param top
     * @return
     */
    public String getGoodsRecord(String shopcode, String wxpingzheng) {
        String strRecord = "";
        ArrayList list = new ArrayList();
        //TODO 后期调用接口
        return strRecord;
    }

    /**
     *
     * @param shopcode shopcode
     * @return
     */
    public String getShopMoneyFlg(String shopcode)  {
        String strFlg = "";
        //TODO 后期调用接口
        return strFlg;
    }


    /**
     * 根据店铺编码、单据编号获取商品明细及项目明细的维修员编码字符串
     *
     * @param shopcode shopcode
     * @param wxpingzheng 微信凭证
     * @return
     */
    public String getWeiXiuYuanCodesStr(String shopcode, String wxpingzheng) {
        String rtnStr = "";
        //TODO 后期调用接口
        return rtnStr == null ? "" : rtnStr;
    }


}
