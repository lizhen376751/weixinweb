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

    //获取消费列表
    public ArrayList getServiceListByLmcodeAndCarNo(String lmCode, String carNo, String top) {
        ArrayList list = new ArrayList();
        int num = Integer.valueOf(top) * 10;
        System.out.println(" getServiceListByLmcodeAndCarNo 下拉==top:" + top + "|");
        //TODO 后期调用接口
        return list;
    }

    //获取支付方式
    public String getZhiFuFangShi(String strId) throws Exception {
        String strName = "";
        //TODO 后期调用接口
        return strName;
    }

    //获取项目列表
    public ArrayList getItemAndSpList(String shopcode, String wxpingzheng) {
        ArrayList list = new ArrayList();
        //TODO 后期调用接口
        return list;
    }

    /**
     * 根据shopcode、员工id获取用户名称(获取技师的名字)
     *
     * @param shopcode code
     * @return
     * @throws Exception
     */
    public String getSalerName(String shopcode, String code) throws Exception {
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




    public ArrayList listRecord(String shopcode, String CarHaoPai, String top) {
        ArrayList list = new ArrayList();
        int num = Integer.valueOf(top) * 10;
        System.out.println("下拉==" + top);
        //TODO 调用接口
        return list;
    }

    public String getItemRecord(String shopcode, String wxpingzheng) {
        String strRecord = "";
        ArrayList list = new ArrayList();
        //TODO 后期调用接口
        return strRecord;
    }

    public String getGoodsRecord(String shopcode, String wxpingzheng) {
        String strRecord = "";
        ArrayList list = new ArrayList();
        //TODO 后期调用接口
        return strRecord;
    }


    public String getShopMoneyFlg(String shopcode) throws Exception {
        String strFlg = "";
        //TODO 后期调用接口
        return strFlg;
    }


    /**
     * 根据店铺编码、单据编号获取商品明细及项目明细的维修员编码字符串
     *
     * @param shopcode
     * @param wxpingzheng
     * @return
     * @throws Exception
     */
    public String getWeiXiuYuanCodesStr(String shopcode, String wxpingzheng) {
        String rtnStr = "";
        //TODO 后期调用接口
        return rtnStr == null ? "" : rtnStr;
    }


}
