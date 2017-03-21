package com.dudu.weixin.control;

import com.dudu.weixin.service.LianmengIntroducedService;
import com.dudu.weixin.service.ShopInfoService;
import com.dudu.weixin.struct.shop.ShopInfo;
import com.dudu.weixin.util.Constant;
import com.dudu.weixin.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/")
public class ABCDceshiAllController {

    @Autowired
    private Constant constant;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private LianmengIntroducedService lianmengIntroducedService;

    private static final Logger logger = LoggerFactory.getLogger(ABCDceshiAjax.class);

    //点击菜单后进入-------------------------------------------------------
    @RequestMapping(value = "oauthLoginServlet", method = RequestMethod.GET)
    public String oauthLogin(HttpServletRequest request,
                             @RequestParam(name = "code",required=false ) String code,
                             @RequestParam(name = "shopcode",required=false) String shopcode,
                             @RequestParam(name = "flagStr",required=false) String flagStr, Model model) {
        logger.info("菜单进入================================"+flagStr);

        //判断点击菜单，进入不同页面
        if ("lmkInfo".equals(flagStr)) {
            return "/lianMengKa/lianMengCard/homePage";//联盟卡包
        } else if ("daoHang".equals(flagStr)) {
//            return "/daoHang/daoHangliebiao/service/daohangindex.jsp?shopcode=" + shopcode + "&openid=" + openId + '"';//服务导航
        } else if ("logout".equals(flagStr)) {
            return "/logout";//退出及注销账号
        } else if ("AHIInfo".equals(flagStr)) {
            return "/ahi/AHIxiangqing";//AHI指数
        } else if ("AHIInfoxiangqing".equals(flagStr)) {
            logger.info("ahi详情页面!");
            String plateNumber = request.getParameter("plateNumber");
            String id = request.getParameter("id");
            model.addAttribute("plateNumber",plateNumber);
            model.addAttribute("id",id);
//            "/plateNumber="+plateNumber+"&id="+id;//AHI指数
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
            model.addAttribute("ids",ids);
            return "/yangCheInfo/jsp/getYangChe";//养车信息详情

        } else if ("lianMengActivity".equals(flagStr)) {

            return "/lianMengActivity/jsp/lianMengActivity";//联盟活动

        }else if("cheXianTouBao".equals(flagStr)){

            return "/baoxian/cheXianTouBao/cheXianTouBao";//车险投保
        }else {
            return "/allController";
//            return "/lianMengKa/lianMengCard/homePage";//
        }
        return "/allController";

    }


    @ResponseBody
    @RequestMapping(value = "/Notify", method = RequestMethod.GET)
    protected String notify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TODO 未做完需要调用接口,数据库因为涉及到sql语句--- Notify   /userServlet/Notify
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/autoZhuanpanJson")
    protected String autoZhuanpan(
            @RequestParam(name = "shopcode") String shopcode,
            @RequestParam(name = "t") String curtime,
            @RequestParam(name = "curUserID") String curUserID,
            @RequestParam(name = "strOpenId") String strOpenId
    ) {

        //TODO 需要调用接口doGet  ----autoZhuanpanJson  /userServlet/autoZhuanpanJson

        return null;

    }

    @ResponseBody
    @RequestMapping(value = "/tijiaoZhuanpanJson")
    protected String tijiaoZhuanpan(
            @RequestParam(name = "shopcode") String shopcode,
            @RequestParam(name = "t") String curtime,
            @RequestParam(name = "curUserID") String curUserID,
            @RequestParam(name = "strOpenId") String strOpenId)

    {
        //TODO 需要调用接口doGet  ----DaZhuanPanGet/tijiaoZhuanpanJson    /userServlet/tijiaoZhuanpanJson
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "coreServlet", method = RequestMethod.GET)
    public String get(@RequestParam(name = "signature") String signature,
                      @RequestParam(name = "timestamp") String timestamp,
                      @RequestParam(name = "nonce") String nonce,
                      @RequestParam(name = "echostr") String echostr,
                      @RequestParam(name = "shopcode") String shopCode) {

        if (SignUtil.checkSignature(this.getTokenByShopCode(shopCode), signature, timestamp, nonce, shopCode)) {
            return echostr;
        } else {
            //TODO 请求校验失败处理结果返回方式
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(value = "coreServlet", method = RequestMethod.POST)
    public String post(@RequestParam(name = "shopcode") String shopCode) {
        ShopInfo info = shopInfoService.getShopInfoDetail(shopCode);
        return null;
    }


    private String getTokenByShopCode(String shopCode) {

        try {

        } catch (Exception e) {
        }

        //TODO token需要存储到数据库中
        return "duduchewangcar";
    }

}
