package com.dudu.weixin.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/15.
 * 主要用于接受ajax请求
 * 原getCommonAjax
 */
@Controller
@RequestMapping("/servlet")
public class CommonAjaxControl {
    /**
     *
     * @Title:        encodingUrl
     * @Description:
     * @param:        @param str
     * @param:        @return
     * @return:       String
     * @throws
     * @author        ZCD
     * @Date          2015-9-24 上午11:45:16
     */
    //ajax===========================================================================================
//    @ResponseBody
//    @RequestMapping(value = "getCommonAjax", method = RequestMethod.POST)
//    public JSONObject commonAjax(
//            @RequestParam(name = "fromflag") String fromflag,
//            @RequestParam(name = "shopcode") String shopcode,
//            @RequestParam(name = "CarId") String CarId,
//            @RequestParam(name = "cardNo") String cardNo,
//            @RequestParam(name = "ids") String ids
//    ) {
//        JSONObject obj = null;//输出的json对象
//        fromflag = encodingUrl(fromflag);
//        shopcode = encodingUrl(shopcode);
//        String carHaoPai = CarId;
//        /*
//		 * 联盟卡
//		 */
//        LianMengKaService lianMengKa = new LianMengKaService();
//
//        //联盟卡主页信息列表
//        //TODO 后期需要添加判断
//        if ("queryLmkInfoList".equals(fromflag)) {
////            obj = lianMengKa.queryLmkInfo(shopcode, carHaoPai);
//        }
//
//        //联盟卡消费明细页面
//        if ("queryLmkXiaoFeiMX".equals(fromflag)) {
////            obj = lianMengKa.queryLmkXiaoFeiMX(shopcode, cardNo, carHaoPai);
//        }
//
//
//        //获取联盟卡发卡店铺名称
//        if ("getXmkCardInfo".equals(fromflag)) {
////            obj = lianMengKa.getXmkCardInfo(shopcode, cardNo, carHaoPai);
//        }
//		/*
//		 * 养车信息
//		 */
//        YangCheInfoService yangCheInfoService = new YangCheInfoService();
//        //获取养车信息列表信息
//        if ("queryYangCheInfo".equals(fromflag)) {
//            obj = yangCheInfoService.queryInfoList(shopcode);
//        }
//        //养车信息
//        if ("getYangCheInfo".equals(fromflag)) {
//            String id = encodingUrl(ids);
//            obj = yangCheInfoService.getInfo(Integer.parseInt(id));
//        }
//
//		/*
//		 * 联盟活动
//		 */
//        LianMengActivityService lianMengActivityService = new LianMengActivityService();
//        //获取联盟活动信息
//        if ("queryLMActivity".equals(fromflag)) {
//            obj = lianMengActivityService.queryInfoList(shopcode);
//        }
//        //单查联盟活动
//        if ("getLianMengActivity".equals(fromflag)) {
//            String id = encodingUrl(ids);
//            obj = lianMengActivityService.getInfo(Integer.parseInt(id));
//        }
//        //联盟介绍
//        if ("queryEntry".equals(fromflag)) {
//            LianmengIntroducedService lianmengIntroducedService = new LianmengIntroducedService();
////            return lianmengIntroducedService.queryEntry(shopcode);
//        }
//        return obj;
//    }
//
//    //将传过来的参数去除空格,并统一字符集
//    public String encodingUrl(String str) {
//        try {
//            if (str != null) {
//                return java.net.URLDecoder.decode(str, "UTF-8").trim();
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
