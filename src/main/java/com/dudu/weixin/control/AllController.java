package com.dudu.weixin.control;

import com.dudu.soa.baoxian.kaidan.module.BaoXianList;
import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.framework.commons.oauth.module.DuduToken;
import com.dudu.soa.framework.oauth.DuduOauthService;
import com.dudu.weixin.service.ChexiantoubaoService;
import com.dudu.weixin.service.LoginActionNewService;
import com.dudu.weixin.service.ShopInfoService;
import com.dudu.weixin.service.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
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
     * 店铺信息
     */
    @Autowired
    private ShopInfoService shopInfoService;
    /**
     * 登录服务
     */
    @Autowired
    private LoginActionNewService loginActionNewService;
    /**
     * 车险投保
     */
    @Autowired
    private ChexiantoubaoService chexiantoubaoService;
    /**
     * 引入验证码发送的服务
     */
    @Autowired
    private ValidateService validateService;
    /**
     * 引入token服务
     */
    @Resource
    private DuduOauthService duduOauthService;

    /**
     * 登录页面
     *
     * @param request  请求
     * @param response 返回
     * @return 路径
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    protected String login(HttpServletRequest request, HttpServletResponse response) {
        boolean flg = loginActionNewService.login(request);
        if (flg) {
            return "/index";
        } else {
            return "/login/login";
        }
    }

    /**
     * 点击菜单后进入
     *
     * @param request 请求
     * @param model   返回数据
     * @return 字符串
     */
    @RequestMapping(value = "oauthLoginServlet", method = RequestMethod.GET)
    public String oauthLogin(HttpServletRequest request,
                             Model model) {
        System.out.println("页面进入=============================");
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
        if (null == lmcode || "".equals(lmcode)) {
            lmcode = (String) httpSession.getAttribute("lmcode");
        }
        String carHaopai = (String) httpSession.getAttribute("carHaopai");
        String openId = (String) httpSession.getAttribute("openId");
        String shopcode = ""; //TODO 暂时为空
        //判断点击菜单，进入不同页面
        if ("lmkInfo".equals(flagStr)) {
            return "/lianMengKa/lianMengCard/homePage"; //联盟卡包
        } else if ("lianMengDetails".equals(flagStr)) {
            logger.info("联盟卡详情");
            String cardName = request.getParameter("cardName");
            String cardNo = request.getParameter("cardNo");
            model.addAttribute("cardName", cardName);
            model.addAttribute("cardNo", cardNo);
            model.addAttribute("shopcode", shopcode);
            return "/lianMengKa/lianMengCard/lianMengDetails"; //联盟卡明细
        } else if ("lianMengCardActivate".equals(flagStr)) {
            logger.info("联盟卡激活");
            return "/lianMengCardActivate/lianMengCardActivate"; //联盟卡激活
        } else if ("daoHang".equals(flagStr)) {
            return "/daoHang/daoHangliebiao/service/daohangindex?shopcode=" + shopcode + "&openid=" + openId + '"'; //服务导航
        } else if ("AHIInfo".equals(flagStr)) {
            return "/ahi/AHIxiangqing"; //AHI指数
        } else if ("AHIInfoxiangqing".equals(flagStr)) {
            String plateNumber = request.getParameter("plateNumber");
            String id = request.getParameter("id");
            model.addAttribute("plateNumber", plateNumber);
            model.addAttribute("id", id);
            logger.info("ahi详情页面!" + plateNumber + id);
            return "/ahi/subxiangqing"; //ahi详情
        } else if ("xiaoFeiList".equals(flagStr)) {
            return "/xiaoFeiJiLu/xiaoFeiList"; //消费记录
        } else if ("baoYangList".equals(flagStr)) {
            return "/baoYangTiXing/baoYangList"; //保养提醒
        } else if ("lianMengJieShao".equals(flagStr)) {
            return "/lianMengIntroduced/jsp/getIntroduced"; //联盟介绍
        } else if ("YCInfo".equals(flagStr)) {
            return "/yangCheInfo/jsp/yangCheXinXi"; //养车信息
        } else if ("getYangChe".equals(flagStr)) {
            String ids = request.getParameter("ids");
            model.addAttribute("ids", ids);
            return "/yangCheInfo/jsp/getYangChe"; //养车信息详情
        } else if ("lianMengActivity".equals(flagStr)) {
            return "/lianMengActivity/jsp/lianMengActivity"; //联盟活动
        } else if ("getLianMeng".equals(flagStr)) {
            String ids = request.getParameter("ids");
            model.addAttribute("ids", ids);
            return "/lianMengActivity/jsp/getLianMeng"; //联盟活动详情
        } else if ("cheXianTouBao".equals(flagStr)) {
            String mineShopCode = request.getParameter("mineShopCode");
            System.out.println(mineShopCode);
            model.addAttribute("mineShopCode", mineShopCode);
            return "/baoxian/cheXianTouBao/cheXianTouBao"; //车险投保
        } else if ("register".equals(flagStr)) {
            return "/register/register"; //注册
        } else if ("suresms".equals(flagStr)) {
            String platenumber = request.getParameter("platenumber");
            String mobilephone = request.getParameter("mobilephone");
            validateService.sendpassWord(platenumber, lmcode, mobilephone);
            return "/login/login"; //已经有该用户,但是没有密码,点击确定发送短信且跳转至登录页面
        } else if ("personalCenter".equals(flagStr)) {
            return "/personCenter/personalCenter"; //个人中心
        } else if ("logout".equals(flagStr)) {
            httpSession.setAttribute("DUDUCHEWANG_CarId", null);
            return "/login/logout"; //退出登录
        } else if ("login".equals(flagStr)) {
            return "/login/login"; //登录页面 (注册时提示已注册,提供跳转至登录的入口)
        }
        return "/baoxian/cheXianTouBao/cheXianTouBao";
    }


    /**
     * 仅用于app端页面跳转
     *
     * @param request  请求
     * @param model    绑定数据
     * @param tokenStr token字符串
     * @return 页面跳转至车险投保页面
     */
    @RequestMapping(value = "/cheXianTouBao", method = RequestMethod.GET)
    public String cheXianTouBao(HttpServletRequest request, Model model, @RequestHeader(value = "token", required = false) String tokenStr) {
        //如果不为空,传进来的就是token,如果为空的话就是写死的token
        tokenStr = tokenStr != null ? tokenStr : "AQAAAITdno+PtJqG3cXdzt3T3ZyNmp6LmquWkprdxc/T3ZqHj42WjJqrlpKa3cXP092SnpaRrJeQj7yQm5rdxd3PyszMz8/"
                + "O3dPdkZaclLGekprdxd0ZYnEZSlYYa2Dd092NkJOatpvdxc3O092Ml5CPvJCbmt3F3c/KzMzPz87d092KjJqNs5Cem7aRtpvdxd3PyszMz8/"
                + "Oz87d092Jmo2MlpCR3cXOgg==";
        DuduToken token = duduOauthService.getDuduToken(tokenStr);
        String mineShopCode = token.getMainShopCode();
        System.out.println("=========" + mineShopCode);
        request.setAttribute("mineShopCode", mineShopCode);
        model.addAttribute("mineShopCode", mineShopCode);
        return "/baoxian/cheXianTouBao/cheXianTouBao"; //车险投保
    }


    /**
     * @param request 作用域获取参数
     * @return String
     */

    @RequestMapping(value = "baoxiantijiao", method = RequestMethod.POST)
    public String baoXianTiJiao(HttpServletRequest request) {
        System.out.println("提交进入");
        Integer integer = chexiantoubaoService.baoXianTiJiao(request);
        return "/baoxian/cheXianTouBao/success"; //提交之后提示成功页面
    }

    /**
     * 查询保险
     * @param request 获取作用域里的值
     * @return  ModelAndView
     */
    @RequestMapping(value = "queryBaoXian", method = RequestMethod.GET)
    public ModelAndView queryInsurance(HttpServletRequest request) {
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        String  lmcode = (String) request.getSession().getAttribute("lmcode");
        String plateNumber = (String) request.getSession().getAttribute("plateNumber");
        if (null != lmcode && !"".equals(lmcode)) {
            baoXianParamList.setShopcodelm(lmcode);
        }
        if (null != plateNumber && !"".equals(plateNumber)) {
            baoXianParamList.setCarId(plateNumber);
        }
        List<BaoXianList> baoXianLists = chexiantoubaoService.queryInsurance(baoXianParamList);
        ModelAndView m = new ModelAndView();
        m.addObject("list", baoXianLists);
        m.setViewName("/cheXianList/cheXianList"); //展示车险列表的页面
        return m;
    }



}
