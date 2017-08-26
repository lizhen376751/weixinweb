package com.dudu.weixin.shopweiixin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.weixindubbo.electroniccoupon.api.ApiElectronicCoupon;
import com.dudu.soa.weixindubbo.electroniccoupon.module.CouponCountResult;
import com.dudu.soa.weixindubbo.electroniccoupon.module.ElectronicCouponParam;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
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
     * 冯广祥
     * 跳转优惠券的数量页面
     *
     * @param request 请求
     * @param model   模板
     * @return 返回页面
     */
    @RequestMapping(value = "/numcoupon", method = {RequestMethod.GET, RequestMethod.POST})
    public String hhh(HttpServletRequest request, Model model) {
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
        model.addAttribute("openid", openid);
        model.addAttribute("shopCode", shopCode);
        model.addAttribute("userNum", result.getUserNum());
        model.addAttribute("forwardNum", result.getForwardNum());
        model.addAttribute("id", id);
        model.addAttribute("identifying", identifying);
        //分享连接取消掉
        //获取卡的类型
        //查询根据openid,shopcode.模板id,查询相应的数量
        return "/shopyouhuiquan/quanindex.jsp";
    }
//
//
//    //2.列表的优惠券查看详情页面
//    @RequestMapping(value = "coupondetails", method = RequestMethod.POST)
//    public String pp(HttpServletRequest request, Model model) {
//        //request获取业务标识 use(使用),give(赠送),get(获取)
//
//        //1.查看详情接口.根据openid,shopcode,卡券id(不传的话查多种卡),查询,只返回一条
//        //2.店管家的需要查询店铺信息
//        //3.需要写一个实体类  详情的实体类,店铺信息的,本人或者赠送,领取 的标识
//        //4.根据不同情况if跳转不同的页面
//        //5.立即使用时,需要屏蔽连接发送以及分享
//        model.addAttribute("详情信息", "详情信息");
//        return "优惠券立即使用详情页面";
////        return "优惠券赠送给朋友的详情页面";
////        return "优惠券立即领取的详情页面";
//    }
//
//    //3.点击立即使用
//    @ResponseBody
//    @RequestMapping(value = "immediateUse", method = RequestMethod.POST)
//    public void oo(HttpServletRequest request) {
//        //立即使用,判断该客户有没有客户id,如果有的话,弹出二维码来,并且提示到店即可使用,如果没有提示注册
//    }
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
