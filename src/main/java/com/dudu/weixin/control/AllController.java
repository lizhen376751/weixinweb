package com.dudu.weixin.control;

import com.alibaba.fastjson.JSONObject;
import com.dudu.soa.baoxian.kaidan.module.BaoXianList;
import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.framework.commons.oauth.module.DuduToken;
import com.dudu.soa.framework.oauth.DuduOauthService;
import com.dudu.weixin.service.ChexiantoubaoService;
import com.dudu.weixin.service.LogInLogService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
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
    private LogInLogService logInLogService;
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
     * 嘟嘟车网测试平台网页授权回调页面
     *
     * @param request  请求
     * @param response 返回
     * @return 路径
     */
    @RequestMapping(value = "MP_verify_xtfw75328NsMZ6bb.txt", method = RequestMethod.GET)
    public String duduchewangceshipingtai(HttpServletRequest request, HttpServletResponse response) {
        return "/MP_verify_xtfw75328NsMZ6bb.txt";
    }

    /**
     * 一路帮网页授权回调页面
     *
     * @param request  请求
     * @param response 返回
     * @return 路径
     */
    @RequestMapping(value = "MP_verify_LfkUMEM3uc7s4hpv.txt", method = RequestMethod.GET)
    public String yilubang(HttpServletRequest request, HttpServletResponse response) {
        return "/MP_verify_LfkUMEM3uc7s4hpv.txt";
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
        String carHaopai = (String) httpSession.getAttribute("plateNumber");
        String openId = (String) httpSession.getAttribute("openId");
        String shopcode = ""; //TODO 暂时为空
        //判断点击菜单，进入不同页面
        if ("lmkInfo".equals(flagStr)) {
            return "/lianMengKa/lianMengCard/homePage.jsp"; //联盟卡包
        } else if ("lianMengDetails".equals(flagStr)) {
            logger.info("联盟卡详情");
            String cardName = request.getParameter("cardName");
            String cardNo = request.getParameter("cardNo");
            model.addAttribute("cardName", cardName);
            model.addAttribute("cardNo", cardNo);
            model.addAttribute("shopcode", shopcode);
            return "/lianMengKa/lianMengCard/lianMengDetails.jsp"; //联盟卡明细
        } else if ("lianMengCardActivate".equals(flagStr)) {
            logger.info("联盟卡激活");
            return "/lianMengCardActivate/lianMengCardActivate.jsp"; //联盟卡激活
        } else if ("daoHang".equals(flagStr)) {
            return "/daoHang/daoHangliebiao/service/daohangindex.jsp?shopcode=" + shopcode + "&openid=" + openId + '"'; //服务导航
        } else if ("AHIInfo".equals(flagStr)) {
            return "/ahi/AHIxiangqing.jsp"; //AHI指数
        } else if ("AHIInfoxiangqing".equals(flagStr)) {
            String id = request.getParameter("id");
            model.addAttribute("id", id);
            return "/ahi/subxiangqing.jsp"; //ahi详情
        } else if ("thirlyIndex".equals(flagStr)) {
            String inspectionDetailedDescription = request.getParameter("inspectionDetailedDescription");
            model.addAttribute("inspectionDetailedDescription", inspectionDetailedDescription);
            return "/ahi/thirlyIndex.jsp"; //ahi三级页面
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
        }
        return "/login/login.jsp?lmcode=" + lmcode;
    }


    /**
     * 仅用于app端页面车险投保
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
        request.setAttribute("mineShopCode", mineShopCode);
        model.addAttribute("mineShopCode", mineShopCode);
        return "/baoxian/cheXianTouBao/cheXianTouBao.jsp"; //车险投保
    }


    /**
     * 提交保险
     *
     * @param request 获取参数
     * @return String
     * @throws ParseException 异常抛出
     */
    @ResponseBody
    @RequestMapping(value = "baoxiantijiao", method = RequestMethod.POST)
    public String baoXianTiJiao(HttpServletRequest request) throws ParseException {
        Integer integer = chexiantoubaoService.baoXianTiJiao(request);
        if (integer > 0) {
            return JSONObject.toJSONString("1");
        } else {
            return JSONObject.toJSONString("0");
        }

    }


    /**
     * @param request 请求
     * @param model   绑定参数
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "queryBaoXian", method = RequestMethod.GET)
    public String queryInsurance(HttpServletRequest request, Model model) {
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        String lmcode = (String) request.getSession().getAttribute("lmcode");
        String plateNumber = (String) request.getSession().getAttribute("plateNumber");
        if (null != lmcode && !"".equals(lmcode)) {
            baoXianParamList.setShopcodelm(lmcode);
        }
        if (null != plateNumber && !"".equals(plateNumber)) {
            baoXianParamList.setCarId(plateNumber);
        }
        List<BaoXianList> baoXianLists = chexiantoubaoService.queryInsurance(baoXianParamList);
        model.addAttribute("list", baoXianLists);
        return "/cheXianList/cheXianList.jsp"; //展示车险列表的页面
    }

    /**
     * 仅用于app端保险列表页面的按钮进入
     *
     * @param request  请求
     * @param model    绑定数据
     * @param tokenStr token字符串
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "/queryInsurance", method = RequestMethod.GET)
    public String queryCarInsurance(HttpServletRequest request, Model model, @RequestHeader(value = "token", required = false) String tokenStr) {
        //如果不为空,传进来的就是token,如果为空的话就是写死的token
        tokenStr = tokenStr != null ? tokenStr : "AQAAAITdno+PtJqG3cXdzt3T3ZyNmp6LmquWkprdxc/T3ZqHj42WjJqrlpKa3cXP092SnpaRrJeQj7yQm5rdxd3PyszMz8/"
                + "O3dPdkZaclLGekprdxd0ZYnEZSlYYa2Dd092NkJOatpvdxc3O092Ml5CPvJCbmt3F3c/KzMzPz87d092KjJqNs5Cem7aRtpvdxd3PyszMz8/"
                + "Oz87d092Jmo2MlpCR3cXOgg==";
        DuduToken token = duduOauthService.getDuduToken(tokenStr);
        String shopCode = token.getShopCode();
        model.addAttribute("shopCode", shopCode);
        //++++++++++++++++++++++++++++++++++++++++
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        if (null != shopCode && !"".equals(shopCode)) {
            List<String> list = new ArrayList<>();
            list.add(shopCode);
            baoXianParamList.setShopCode(list);
        }
        List<BaoXianList> baoXianLists = chexiantoubaoService.queryInsurance(baoXianParamList);
        model.addAttribute("list", baoXianLists);
        return "/cheXianList/cheXianList.jsp"; //展示车险列表的页面

    }

    /**
     * 仅用于app端保险提交之后跳转至列表页面
     *
     * @param request 请求
     * @param model   返回数据
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "/appbaoxianlist", method = RequestMethod.GET)
    public String appbaoxianlist(HttpServletRequest request, Model model) {
        String mineShopCode = request.getParameter("mineShopCode");
        model.addAttribute("shopCode", mineShopCode);
        return "/cheXianList/cheXianList.jsp"; //展示车险列表的页面

    }
//-----------------------------------------------------------------------ahi

    /**
     * 仅用于ahi测试作用
     *
     * @param request 请求
     * @param model   返回数据
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "ahi", method = RequestMethod.GET)
    public String ahi(HttpServletRequest request, Model model) {
        return "/ahi/AHIxiangqing.jsp"; //AHI指数

    }

    /**
     * 仅用于ahi测试作用
     *
     * @param request 请求
     * @param model   返回数据
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "AHIInfoxiangqing", method = RequestMethod.GET)
    public String aHIInfoxiangqing(HttpServletRequest request, Model model) {
        return "/ahi/subxiangqing.jsp"; //ahi详情

    }
    //-----------------------------------------------------------------------ahi

}
