package com.dudu.weixin.control;

import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDan;
import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDanGongSi;
import com.dudu.soa.baoxian.kaidan.module.BaoXianKaiDanXiangQing;
import com.dudu.weixin.service.ChexiantoubaoService;
import com.dudu.weixin.service.LianmengIntroducedService;
import com.dudu.weixin.service.LoginActionNewService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/")
public class ABCDceshiAllController {
    private static final Logger logger = LoggerFactory.getLogger(ABCDceshiAjax.class);
    @Autowired
    private Constant constant;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private LianmengIntroducedService lianmengIntroducedService;
    @Autowired
    private LoginActionNewService loginActionNewService;
    @Autowired
    private ChexiantoubaoService chexiantoubaoService;//车险投保
    //登录页面
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
        } else if ("logout".equals(flagStr)) {
            return "/logout";//退出及注销账号
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
        }
        return "/index";
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

    @ResponseBody
    @RequestMapping(value = "baoxiantijiao", method = RequestMethod.POST)
    public String baoXianTiJiao(HttpServletRequest request,BaoXianKaiDan baoXianKaiDan) {
        //保险开单
       /* String customerId = request.getParameter("aaaaa");
        String kaiDanDate = request.getParameter("aaaaa");
        String assistant = request.getParameter("aaaaa");
        String shopcode_lm = request.getParameter("unionHeadquarters");
        String xingShiZhengImg = request.getParameter("aaaaa");
        String xingShiZhengImg2 = request.getParameter("aaaaa");
        String shenFenZhengImg = request.getParameter("aaaaa");
        String shenFenZhengImg2 = request.getParameter("aaaaa");
        String remarks = request.getParameter("aaaaa");
        String shopCode = request.getParameter("aaaaa");
        String totalPrice = request.getParameter("aaaaa");
        String fuKuanFlag = request.getParameter("aaaaa");
        String shiShou = request.getParameter("aaaaa");
        String orderNumb = request.getParameter("aaaaa");*/


        //开单详情
        String[] insurancetypeIds = request.getParameterValues("chexian");
        List<BaoXianKaiDanXiangQing> baoxiankaidanxiangqing  = new ArrayList<BaoXianKaiDanXiangQing>();
        if(insurancetypeIds!=null && insurancetypeIds.length>0) {
            for (int i = 0; i < insurancetypeIds.length; i++) {
                BaoXianKaiDanXiangQing baoXianKaiDanXiangQing = new BaoXianKaiDanXiangQing();
                int insurancetypeId = Integer.parseInt(insurancetypeIds[i]);
                baoXianKaiDanXiangQing.setInsurancetypeId(insurancetypeId);
                String buJiMianPeiTypes = request.getParameter("bjmp_" + insurancetypeIds[i]);
                if(null!=buJiMianPeiTypes&& !"".equals(buJiMianPeiTypes) ){
                    int buJiMianPeiType = Integer.parseInt(buJiMianPeiTypes);
                    baoXianKaiDanXiangQing.setBuJiMianPeiType(buJiMianPeiType);
                }
                String dictionaryIds = request.getParameter("pcxe_" + insurancetypeIds[i]);
                if(null!=dictionaryIds&& !"".equals(dictionaryIds) ){
                    int dictionaryId = Integer.parseInt(dictionaryIds);
                    baoXianKaiDanXiangQing.setBuJiMianPeiType(dictionaryId);
                }
                String baoZhangRenShus = request.getParameter("bzrs_" + insurancetypeIds[i]);
                if(null!=baoZhangRenShus&& !"".equals(baoZhangRenShus) ){
                    int baoZhangRenShu = Integer.parseInt(baoZhangRenShus);
                    baoXianKaiDanXiangQing.setBuJiMianPeiType(baoZhangRenShu);
                }

                //String kaiDanId=request.getParameter("pcxe_3");
                //String price=request.getParameter("pcxe_3");

                baoxiankaidanxiangqing.add(baoXianKaiDanXiangQing);

            }
            baoXianKaiDan.setBaoXianKaiDanXiangQing(baoxiankaidanxiangqing);
        }

        //保险公司
        String[] insuranceIds =request.getParameterValues("xianzhong");
        List<BaoXianKaiDanGongSi> baoXianKaiDanGongSi1 = new ArrayList<BaoXianKaiDanGongSi>();
        if(insuranceIds!=null && insuranceIds.length>0) {
            for (int i = 0; i < insuranceIds.length; i++) {
                BaoXianKaiDanGongSi baoXianKaiDanGongSi = new BaoXianKaiDanGongSi();
                int companyId = Integer.parseInt(insuranceIds[i]);
                baoXianKaiDanGongSi.setCompanyId(companyId);
                baoXianKaiDanGongSi1.add(baoXianKaiDanGongSi);
            }
            baoXianKaiDan.setBaoXianKaiDanGongSi(baoXianKaiDanGongSi1);
        }

        chexiantoubaoService.baoXianTiJiao(request,baoXianKaiDan);
        return "";
    }


    private String getTokenByShopCode(String shopCode) {
        //TODO token需要存储到数据库中
        return "duduchewangcar";
    }

}
