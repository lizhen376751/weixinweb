package com.dudu.weixin.control;

import com.dudu.weixin.service.LogInLogService;
import com.dudu.weixin.service.ValidateService;
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
 * 所有微信菜单入口
 * Created by lizhen on 2017/3/17.
 */
@Controller
@RequestMapping("/")
public class AllController {
    /**
     * 日志打印
     */
    private static Logger logger = LoggerFactory.getLogger(AllController.class);
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
     * 引入验证码发送的服务
     */
    @Autowired
    private ValidateService validateService;


    /**
     * 点击菜单后进入
     *
     * @param request 请求
     * @param model   返回数据
     * @return 字符串
     */
    @RequestMapping(value = "oauthLoginServlet", method = RequestMethod.GET)
    public String oauthLogin(HttpServletRequest request, Model model) {

        //获取参数,并将其分解
        String strWxShopcode = request.getParameter("lmcode");
        String flagStr = "";
        String lmcode = "";
        if (strWxShopcode == null || "" == strWxShopcode || "".equals(strWxShopcode)) {
            flagStr = request.getParameter("flagStr");
        } else {
            flagStr = strWxShopcode.split("_")[1]; //页面跳转判断
            lmcode = strWxShopcode.split("_")[0]; //联盟code

        }
        if (null == lmcode || "".equals(lmcode) || "null".equals(lmcode)) {
            lmcode = (String) httpSession.getAttribute("lmcode");
        }
        String carHaopai = (String) httpSession.getAttribute("plateNumber");
        String openId = (String) httpSession.getAttribute("openId");
        //判断点击菜单，进入不同页面
        if ("lmkInfo".equals(flagStr)) {
            return "/lianMengKa/lianMengCard/homePage.jsp"; //联盟卡包
        } else if ("lianMengDetails".equals(flagStr)) {
            logger.info("联盟卡详情");
            String cardName = request.getParameter("cardName");
            String cardNo = request.getParameter("cardNo");
            model.addAttribute("cardName", cardName);
            model.addAttribute("cardNo", cardNo);
            return "/lianMengKa/lianMengCard/lianMengDetails.jsp"; //联盟卡明细
        } else if ("lianMengCardActivate".equals(flagStr)) {
            logger.info("联盟卡激活");
            return "/lianMengCardActivate/lianMengCardActivate.jsp"; //联盟卡激活
        } else if ("daoHang".equals(flagStr)) {
            return "/daoHang/daoHangliebiao/service/daohangindex.jsp?shopcode=" + lmcode + "&openid=" + openId + '"'; //服务导航
        } else if ("AHIInfo".equals(flagStr)) {
            return "/ahi/AHIxiangqing.jsp"; //AHI指数
        } else if ("xiaoFeiList".equals(flagStr)) {
            return "/xiaoFeiJiLu/xiaoFeiList.jsp"; //消费记录
        } else if ("baoYangList".equals(flagStr)) {
            return "/baoYangTiXing/baoYangList.jsp"; //保养提醒
        } else if ("lianMengJieShao".equals(flagStr)) {
            return "/lianMengIntroduced/jsp/getIntroduced.jsp"; //联盟介绍
        } else if ("YCInfo".equals(flagStr)) {
            return "/yangCheInfo/jsp/yangCheXinXi.jsp"; //养车信息
        } else if ("getYangChe".equals(flagStr)) {
            String ids = request.getParameter("ids");
            model.addAttribute("ids", ids);
            return "/yangCheInfo/jsp/getYangChe.jsp"; //养车信息详情
        } else if ("lianMengActivity".equals(flagStr)) {
            return "/lianMengActivity/jsp/lianMengActivity.jsp"; //联盟活动
        } else if ("getLianMeng".equals(flagStr)) {
            String ids = request.getParameter("ids");
            model.addAttribute("ids", ids);
            return "/lianMengActivity/jsp/getLianMeng.jsp"; //联盟活动详情
        } else if ("cheXianTouBao".equals(flagStr)) {
            String mineShopCode = request.getParameter("mineShopCode");
            model.addAttribute("mineShopCode", mineShopCode);
            return "/baoxian/cheXianTouBao/cheXianTouBao.jsp"; //车险投保
        } else if ("cheXianOnline".equals(flagStr)) {
            return "redirect:http://kefu6.kuaishang.cn/bs/im.htm?cas=56463___619761&fi=58696&from=9"; //车险投保易璐邦的在线咨询
        } else if ("register".equals(flagStr)) {
            return "/register/register.jsp"; //注册
        } else if ("suresms".equals(flagStr)) {
            String platenumber = request.getParameter("platenumber");
            String mobilephone = request.getParameter("mobilephone");
            validateService.sendpassWord(platenumber, lmcode, mobilephone);
            return "/login/login.jsp"; //已经有该用户,但是没有密码,点击确定发送短信且跳转至登录页面
        } else if ("personalCenter".equals(flagStr)) {
            model.addAttribute("carHaopai", carHaopai);
            model.addAttribute("lmcode", lmcode);
            return "/personCenter/personalCenter.jsp"; //个人中心
        } else if ("logout".equals(flagStr)) {
            logInLogService.deleLogInLog(openId);
            httpSession.setAttribute("plateNumber", null);
            return "/login/login.jsp?lmcode=" + lmcode; //退出登录
        } else if ("login".equals(flagStr)) {
            return "/login/login.jsp?lmcode=" + lmcode; //登录页面 (注册时提示已注册,提供跳转至登录的入口)
        } else if ("baoxianlist".equals(flagStr)) {
            return "/cheXianList/cheXianList.jsp"; //车险报价
        } else if ("fuwudaohang".equals(flagStr)) {
            model.addAttribute("shopType_search", "");
            model.addAttribute("lmcode", lmcode);
            return "/fuwudaohang.jsp"; //服务导航全部店铺
        } else if ("fuwudaohangXC".equals(flagStr)) {
            model.addAttribute("shopType_search", ",洗车,");
            model.addAttribute("lmcode", lmcode);
            return "/fuwudaohang.jsp"; //服务导航一键洗车
        } else if ("fuwudaohangYQ".equals(flagStr)) {
            model.addAttribute("shopType_search", ",油漆,");
            model.addAttribute("lmcode", lmcode);
            return "/fuwudaohang.jsp"; //服务导航一键油漆
        }
        return "/login/login.jsp?lmcode=" + lmcode;
    }

    /**
     * 联盟微信内部页面的相关跳转
     *
     * @param request 请求
     * @param model   返回页面的数据
     * @return 路径
     */
    @RequestMapping(value = "lmInternalJump", method = RequestMethod.GET)
    public String lmInternalJump(HttpServletRequest request, Model model) {
        String business = request.getParameter("business");
        if ("AHIInfoxiangqing".equals(business)) {
            String id = request.getParameter("id");
            model.addAttribute("id", id);
            return "/ahi/subxiangqing.jsp"; //ahi详情
        } else if ("thirlyIndex".equals(business)) {
            String inspectionDetailedDescription = request.getParameter("inspectionDetailedDescription");
            model.addAttribute("inspectionDetailedDescription", inspectionDetailedDescription);
            return "/ahi/thirlyIndex.jsp"; //ahi三级页面
        } else {
            return "/login/login.jsp";
        }

    }


}
