package com.dudu.weixin.shopweiixin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.basedata.shopinfo.api.ApiBaseDataShopInfo;
import com.dudu.soa.basedata.shopinfo.module.ShopInfo;
import com.dudu.soa.basedata.shopinfo.module.ShopInfoParam;
import com.dudu.soa.weixindubbo.electroniccoupon.api.ApiElectronicCoupon;
import com.dudu.soa.weixindubbo.electroniccoupon.module.CouponCountResult;
import com.dudu.soa.weixindubbo.electroniccoupon.module.ElectronicCouponParam;
import com.dudu.soa.weixindubbo.electroniccoupon.module.WeiXinCouponInfo;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 电子优惠券相关页面跳转以及ajax请求
 * Created by lizhen on 2017/8/25.
 */
@Controller
public class ShopCouponController {
    /**
     * 日志打印
     */
    private static Logger logger = LoggerFactory.getLogger(ShopCouponController.class);
    /**
     * 微信相关接口
     */
    @Reference
    private ApiAllWeiXiRequest apiAllWeiXiRequest;
    /**
     * session
     */
    @Autowired
    private HttpSession httpSession;
    /**
     * 电子优惠券接口
     */
    @Reference
    private ApiElectronicCoupon apiElectronicCoupon;

    /**
     * 店铺信息接口
     */
    @Reference
    private ApiBaseDataShopInfo apiBaseDataShopInfo;

    /**
     * 冯广祥
     * 跳转优惠券的数量页面
     *
     * @param request 请求
     * @param model   模板
     * @return 返回页面
     */
    @RequestMapping(value = "/numcoupon", method = {RequestMethod.GET, RequestMethod.POST})
    public String numCoupon(HttpServletRequest request, Model model) {
        //调用接口查询可用电子优惠券的数量,以及可转发的数量
        String openid = request.getParameter("openid"); //微信openid
        String shopCode = request.getParameter("shopCode"); // 店铺编码
        String id = request.getParameter("id"); //优惠券唯一标识ID
        String identifying = request.getParameter("identifying"); //标识   单一：only  更多：more
        httpSession.setAttribute("shopcode", shopCode); // 存储店铺编码 到session
        ElectronicCouponParam electronicCouponParam = new ElectronicCouponParam();
        electronicCouponParam.setOpenId(openid)
                .setShopCode(shopCode);
        if ("only".equals(identifying)) { // 如果是微信模板消息推送点击进入  需通过优惠券ID查询数据
            electronicCouponParam.setCouponId(Integer.getInteger(id));
        }
        CouponCountResult result = apiElectronicCoupon.getWeiXinConponCount(electronicCouponParam);
        logger.info("==============查询数量=============" + result.toString());
        if (null != result) {
            model.addAttribute("openid", openid);
            model.addAttribute("shopCode", shopCode);
            model.addAttribute("userNum", result.getUserNum()); // 可使用数量
            model.addAttribute("forwardNum", result.getForwardNum()); // 可转发数量
            model.addAttribute("id", id);
            model.addAttribute("identifying", identifying);
        }
        //分享连接取消掉
        //获取卡的类型
        //查询根据openid,shopcode.模板id,查询相应的数量
        return "/shopyouhuiquan/quanindex.jsp";
    }

    /**
     * 列表的优惠券查看详情页面
     *
     * @param request 请求
     * @param model   模板
     * @return 返回页面
     */
    @RequestMapping(value = "/coupondetails", method = RequestMethod.GET)
    public String couponDetails(HttpServletRequest request, Model model) {
        logger.info("=========================================跳转成功");
        String businessLogo = request.getParameter("businessLogo");  //获取业务标识 use(使用),give(赠送),get(获取领取)
        String identifying = request.getParameter("identifying");  //标识   单一：only  更多：more
        String id = request.getParameter("id"); // 券的ID
        String shopCode = request.getParameter("shopCode"); // 店铺编码
        String openid = request.getParameter("openid"); //微信openid
        Integer state = 0;
        if ("use".equals(businessLogo)) {
            state = 1;
        } else if ("give".equals(businessLogo)) {
            state = 0;
        }
        ElectronicCouponParam param = new ElectronicCouponParam();
        param.setCouponId(Integer.getInteger(id))
                .setShopCode(shopCode)
                .setOpenId(openid)
                .setCouponFlag(state); // 0：可转发  1：可使用
        WeiXinCouponInfo wxWeiXinCouponInfo = apiElectronicCoupon.getWXElectronicCouponInfo(param);
        logger.debug("=============查询微信优惠券详情===============" + wxWeiXinCouponInfo.toString());
        ShopInfoParam shopInfoParam = new ShopInfoParam();
        shopInfoParam.setShopCode(shopCode);
        ShopInfo shopInfo = apiBaseDataShopInfo.getByCode(shopInfoParam);
        logger.debug("=============查询店铺信息 返回===============" + shopInfo.toString());
        model.addAttribute("wxWeiXinCouponInfo", wxWeiXinCouponInfo); // 优惠券返回详情
        model.addAttribute("shopInfo", shopInfo); // 店铺信息返回详情


       /* model.addAttribute("openid", openid); // 微信openid
        model.addAttribute("shopCode", shopCode); // 店铺编码
        model.addAttribute("diXiaoJinE", wxWeiXinCouponInfo.getDiXiaoJinE()); // 可抵消金额
        model.addAttribute("forwardNum", wxWeiXinCouponInfo.getAnotherJinE()); // 另付费
        model.addAttribute("couponStartTime", wxWeiXinCouponInfo.getCouponStartTime()); // 领取优惠券有效日期
        model.addAttribute("couponEndTime", wxWeiXinCouponInfo.getCouponEndTime()); // 领取优惠券失效日期
        model.addAttribute("couponCode", wxWeiXinCouponInfo.getCouponCode()); // 优惠券编码
        model.addAttribute("details", wxWeiXinCouponInfo.getDetails()); // 使用详情
        model.addAttribute("lingOpenid", wxWeiXinCouponInfo.getOpenId()); //  领取人openid
        model.addAttribute("couponState", wxWeiXinCouponInfo.getCouponState()); //  优惠券状态   0：已失效   1：已领取   2：未领取
        model.addAttribute("couponFlag", wxWeiXinCouponInfo.getCouponFlag()); // 优惠券标识(1可使用,0:可转发)
        model.addAttribute("couponName", wxWeiXinCouponInfo.getCouponName()); // 优惠券名称
*/

        //5.立即使用时,需要屏蔽连接发送以及分享
        model.addAttribute("详情信息", "详情信息");
        if ("use".equals(businessLogo)) { // 使用页面
            return "/shopyouhuiquan/quandiyong.jsp";
        } else if ("give".equals(businessLogo)) { //赠送页面
            return "/shopyouhuiquan/quanZengS.jsp";
        } else if ("get".equals(businessLogo)) { //领取页面
            return "/shopyouhuiquan/quanlingqu.jsp";
        }
        return null;
    }

    /**
     * 点击立即使用
     *
     * @param request 相应
     * @return 返回客户ID
     *//*
  /*  @RequestMapping(value = "immediateUse", method = RequestMethod.POST)
    public String oo(HttpServletRequest request) {
        //立即使用,判断该客户有没有客户id,如果有的话,弹出二维码来,并且提示到店即可使用,如果没有提示注册
    }*/
//
//    //4.点击赠送给朋友,这边不需要调用请求
//    public void dd() {
//        //提示分享的步骤
//        //连接需要获取当前域名,然后拼凑一些东西比如:shopCode,优惠券code
//        //分享成功之后,需要提示分享成功
//    }
//
//    //4.点击立即领取
//    @ResponseBody
//    @RequestMapping(value = "shareConnection", method = RequestMethod.POST)
//    public String ee(HttpServletRequest request) {
//        String shopCode = request.getParameter("shopCode");
//        //调用接口 根据优惠券code判断是否领取?
//        //返回二维码或者false,
//        //已经领取,前端判断,提示该券已领取
//        //没有领取的生成二维码,以及领取的步骤
//        Ticket ticket = new Ticket();
//        ticket.setShopCode(shopCode).setSceneStr("");
//        Ticket ticket1 = apiAllWeiXiRequest.getTicket(ticket);
//        if (null != ticket1) {
//            String url = ticket1.getUrl();
//            return url;
//        }
//        return null;
//    }
//
//    //7.关注事件进行回复,需要判断该客户已经领取张数限制,如果超过了也去发送消息,提示该种卡券请勿重复领取
//    //如果没有超过限制,发送消息,返回步骤1
//
//
//
//    //8.跳转页面,同时返回数据点击可使用数量,查询列表(多种卡,由微信菜单或者个人中心跳至此页面)
//    @RequestMapping(value = "listcoupon", method = {RequestMethod.POST, RequestMethod.GET})
//    public String ss(HttpServletRequest request, Model model) {
//        //查询优惠券的列表页面
//        String openid = request.getParameter("openid");
//        String shopCode = request.getParameter("shopCode");
//        model.addAttribute("num", "1");
//        return "返回查询出列表数据页面";
//    }

    //9.个人中心,微信菜单的电子优惠券
}
