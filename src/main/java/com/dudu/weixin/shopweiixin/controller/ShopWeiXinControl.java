package com.dudu.weixin.shopweiixin.controller;

import com.dudu.weixin.service.LogInLogService;
import com.dudu.weixin.shopweiixin.service.ShopXiaoFeiJiLuService;
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
 * 店管家所有的页面跳转
 * Created by lizhen on 2017/5/2.
 */

@Controller
@RequestMapping("/")
public class ShopWeiXinControl {
    /**
     * 日志打印
     */
    private static Logger logger = LoggerFactory.getLogger(ShopWeiXinControl.class);
    /**
     * 引入消费记录服务
     */
    @Autowired
    private ShopXiaoFeiJiLuService shopXiaoFeiJiLu;
    /**
     * session
     */
    @Autowired
    private HttpSession httpSession;
    /**
     * 登录服务
     */
    @Autowired
    private LogInLogService logInLogService;

    /**
     * 所有店管家微信菜单的页面的跳转
     *
     * @param request 请求
     * @param model   模版参数
     * @return 字符串路径
     */
    @RequestMapping(value = "shopweixinMenuServlet", method = RequestMethod.GET)
    public String shopWeiXinMenuPageJump(HttpServletRequest request, Model model) {
        String openId = (String) httpSession.getAttribute("openId");
        String strWxShopcode = request.getParameter("shopcode");
        String flagStr = strWxShopcode.split("_")[1]; //页面跳转判断
        String shopcode = strWxShopcode.split("_")[0]; //联盟code
        if ("AHIInfo".equals(flagStr)) {
            return "/ahi/AHIxiangqing.jsp"; //AHI指数
        } else if ("xiaofeijilu".equals(flagStr)) {
            return "/shopxiaofeijilu/xiaofeijilu.jsp"; //消费记录
        } else if ("BaoYangTiXing".equals(flagStr)) {
            return "/shopbaoyangtixing/baoYangList.jsp"; //保养提醒
            // } else if ("shoppersoncenter".equals(flagStr)) {
            //    return "/shoppersonCenter/shoppersonalCenter.jsp"; //个人中心
        } else if ("shoppersoncenter".equals(flagStr)) {
            return "/shoppersonCenter/grzx.jsp"; //个人中心
        } else if ("weixinshangcheng".equals(flagStr)) { //微信商城
            return "redirect:http://www.duduchewang.cn/weixincore_cn/zy.jsp?shopcode=" + shopcode + "&openid=" + openId;
        } else if ("myOrder".equals(flagStr)) { //微信商城里面我的订单
            return "redirect:http://www.duduchewang.cn/weixincore_cn/evaluateList.jsp?shopcode=" + shopcode + "&openId=" + openId;
        }
        return null;
    }

    /**
     * 店管家微信项目内部的跳转
     *
     * @param request 请求
     * @param model   模板
     * @return 字符串的路径跳转
     */
    @RequestMapping(value = "shopweixinServlet", method = {RequestMethod.GET, RequestMethod.POST})
    public String shopWeiXinPageJump(HttpServletRequest request, Model model) {
        String openId = (String) httpSession.getAttribute("openId");
        String plateNumber = (String) httpSession.getAttribute("plateNumber");
        String serviceType = request.getParameter("serviceType");
        if ("login".equals(serviceType)) { //登录页面
            return "/shoplogin/shoplogin.jsp";
        } else if ("register".equals(serviceType)) { //注册页面
            return "/shopregister/register.jsp";
        } else if ("signout".equals(serviceType)) { //退出账号
            logInLogService.deleLogInLog(openId);
            httpSession.setAttribute("plateNumber", null);
            return "/shoplogin/shoplogin.jsp";
        } else if ("shigongbuzhou".equals(serviceType)) { //施工步骤
            model.addAttribute("plateNumber", plateNumber);
            model.addAttribute("shopCode", request.getParameter("shopCode")); //店铺编码
            model.addAttribute("wxpingzheng", request.getParameter("wxpingzheng")); //维修单号
            model.addAttribute("xunumber", request.getParameter("xu_number")); //项目字符串
            return "/shopshigongbuzhou/stepPhoto.jsp";
        } else if ("biaozhunliucheng".equals(serviceType)) { //标准流程查看
            String itemCode = request.getParameter("itemCode"); //项目编码
            String shopCodeLm = request.getParameter("shopCodeLm"); //品牌商编码
            model.addAttribute("itemCode", itemCode);
            model.addAttribute("shopCodeLm", shopCodeLm);
            return "/shopshigongbuzhou/standardProcesses.jsp";
        } else if ("AHIInfo".equals(serviceType)) { //AHI指数
            return "/ahi/AHIxiangqing.jsp";
        } else if ("BaoYangTiXing".equals(serviceType)) { //保养提醒
            return "/shopbaoyangtixing/baoYangList.jsp";
        } else if ("projectCardMX".equals(serviceType)) { //个人中心的项目明细页面
            model.addAttribute("plateNumber", plateNumber);
            model.addAttribute("cardNumb", request.getParameter("cardNumb"));
            model.addAttribute("shopCode", request.getParameter("shopCode"));
            model.addAttribute("customerId", request.getParameter("customerId"));
            return "/shoppersonCenter/projectCardMX.jsp";
        } else if ("projectCZHICard".equals(serviceType)) { //个人中心的充值卡明细页面
            model.addAttribute("plateNumber", plateNumber);
            return "/shoppersonCenter/projectCZHICard.jsp";
        } else if ("rechargeableCardMX".equals(serviceType)) { //个人中心充值卡明细页面
            model.addAttribute("plateNumber", plateNumber);
            model.addAttribute("cardNumb", request.getParameter("cardNumb"));
            model.addAttribute("shopCode", request.getParameter("shopCode"));
            model.addAttribute("customerId", request.getParameter("customerId"));
            return "/shoppersonCenter/rechargeableCardMX.jsp";
        } else if ("shoppersoncenter".equals(serviceType)) { //内部跳转至个人中心页面
            return "/shoppersonCenter/grzx.jsp"; //个人中心
        } else if ("shopEvaluateParam".equals(serviceType)) { //消费记录评价页面
            String sign = request.getParameter("sign");
            model.addAttribute("plateNumber", request.getParameter("plateNumber")); //车牌号
            model.addAttribute("customId", request.getParameter("customId")); //客户id
            model.addAttribute("wxpingzheng", request.getParameter("wxpingzheng")); //维修凭证
            model.addAttribute("shopCode", request.getParameter("shopCode")); //店铺编码
            if ("addPingJia".equals(sign)) { //添加消费评价页面
                return "/shopconsumerEvaluation/consumerEvaluation.jsp";
            } else if ("queryPingJia".equals(sign)) { //查询消费评价页面
                return "/shopconsumerEvaluation/consumerEvaluation1.jsp";
            }
        } else if ("projectCard".equals(serviceType)) { //跳转项目卡页面
            model.addAttribute("customerId", request.getParameter("customerId")); //客户ID
            return "/shoppersonCenter/projectCard.jsp";
        }
        return null;
    }

}
