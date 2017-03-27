package com.dudu.weixin.control;

import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDan;
import com.dudu.weixin.service.ChexiantoubaoService;
import com.dudu.weixin.service.LoginActionNewService;
import com.dudu.weixin.service.ShopInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/")
public class ABCDceshiAllController {
    private static final Logger logger = LoggerFactory.getLogger(ABCDceshiAjax.class);

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private LoginActionNewService loginActionNewService;//登录服务
    @Autowired
    private ChexiantoubaoService chexiantoubaoService;//车险投保
    //登录页面
    @RequestMapping(value = "login", method = RequestMethod.POST)
    protected String login(HttpServletRequest request, HttpServletResponse response) {
        boolean flg = loginActionNewService.login(request);
        if (flg) {
            return "/index";
        } else {
            return "/login/login";
        }
    }
    //点击菜单后进入
    @RequestMapping(value = "oauthLoginServlet", method = RequestMethod.GET)
    public String oauthLogin(HttpServletRequest request,
                             @RequestParam(name = "code", required = false) String code,
                             @RequestParam(name = "flagStr", required = false) String flagStr, Model model) {
        logger.info("菜单进入================================" + flagStr);
        String carId = (String) httpSession.getAttribute("DUDUCHEWANG_CarId");
        String openId = (String) httpSession.getAttribute("DUDUCHEWANG_OpenId");
        String shopcode = (String) httpSession.getAttribute("DUDUCHEWANG_shopcode");
        //判断点击菜单，进入不同页面
        if ("lmkInfo".equals(flagStr)) {
            return "/lianMengKa/lianMengCard/homePage";//联盟卡包
        } else if ("lianMengDetails".equals(flagStr)) {
            logger.info("联盟卡详情");
            String cardName = request.getParameter("cardName");
            String cardNo = request.getParameter("cardNo");
            model.addAttribute("cardName", cardName);
            model.addAttribute("cardNo", cardNo);
            model.addAttribute("shopcode", shopcode);
            return "/lianMengKa/lianMengCard/lianMengDetails"; //联盟卡明细
        } else if ("daoHang".equals(flagStr)) {
          return "/daoHang/daoHangliebiao/service/daohangindex?shopcode=" + shopcode + "&openid=" + openId + '"';//服务导航
        } else if ("AHIInfo".equals(flagStr)) {
            return "/ahi/AHIxiangqing";//AHI指数
        } else if ("AHIInfoxiangqing".equals(flagStr)) {
            String plateNumber = request.getParameter("plateNumber");
            String id = request.getParameter("id");
            model.addAttribute("plateNumber", plateNumber);
            model.addAttribute("id", id);
            logger.info("ahi详情页面!" + plateNumber + id);
            return "/ahi/subxiangqing";//详情
        } else if ("xiaoFeiList".equals(flagStr)) {
            return "/xiaoFeiJiLu/xiaoFeiList";//消费记录
        } else if ("baoYangList".equals(flagStr)) {
            return "/baoYangTiXing/baoYangList";//保养提醒
        } else if ("lianMengJieShao".equals(flagStr)) {
            return "/lianMengIntroduced/jsp/getIntroduced";//联盟介绍
        } else if ("YCInfo".equals(flagStr)) {
            return "/yangCheInfo/jsp/yangCheXinXi";//养车信息
        } else if ("getYangChe".equals(flagStr)) {
            String ids = request.getParameter("ids");
            model.addAttribute("ids", ids);
            return "/yangCheInfo/jsp/getYangChe";//养车信息详情
        } else if ("lianMengActivity".equals(flagStr)) {
            return "/lianMengActivity/jsp/lianMengActivity";//联盟活动
        } else if ("getLianMeng".equals(flagStr)) {
            String ids = request.getParameter("ids");
            model.addAttribute("ids", ids);
            return "/lianMengActivity/jsp/getLianMeng";//联盟活动详情
        } else if ("cheXianTouBao".equals(flagStr)) {
            return "/baoxian/cheXianTouBao/cheXianTouBao";//车险投保
        }else if ("logout".equals(flagStr)) {
            httpSession.setAttribute("DUDUCHEWANG_CarId", null);
            return "/login/logout";//退出登录
        }
        return "/index";
    }



    @ResponseBody
    @RequestMapping(value = "baoxiantijiao", method = RequestMethod.POST)
    public String baoXianTiJiao(HttpServletRequest request,BaoXianKaiDan baoXianKaiDan,HttpSession httpSession) {
        chexiantoubaoService.baoXianTiJiao(request,baoXianKaiDan,httpSession);
        return "";
    }


}
