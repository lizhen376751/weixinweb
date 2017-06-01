package com.dudu.weixin.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.baoxian.kaidan.module.ClientInsuranceResult;
import com.dudu.soa.baoxian.kaidan.module.ClientInsuranceTypeParam;
import com.dudu.soa.baoxian.kaidan.module.Insurance;
import com.dudu.soa.dududata.oss.api.ApiDuduDataOssSecretConfigIntf;
import com.dudu.soa.dududata.oss.module.OssSecretConfig;
import com.dudu.soa.dududata.oss.module.param.OssSecretConfigParam;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopQueryFruit;
import com.dudu.soa.lmk.operate.module.LianmengKaResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmCustResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.soa.weixindubbo.weixin.weixinconfig.api.ApiWeiXinConfig;
import com.dudu.soa.weixindubbo.weixin.weixinconfig.module.WeiXinConfig;
import com.dudu.weixin.service.AHIService;
import com.dudu.weixin.service.AutoLoginService;
import com.dudu.weixin.service.BaoYangTiXingService;
import com.dudu.weixin.service.BuyRecordService;
import com.dudu.weixin.service.CheXianService;
import com.dudu.weixin.service.ChexiantoubaoService;
import com.dudu.weixin.service.LianMengActivityService;
import com.dudu.weixin.service.LianMengKaService;
import com.dudu.weixin.service.LianmengIntroducedService;
import com.dudu.weixin.service.PersoncenterService;
import com.dudu.weixin.service.ShopInfoService;
import com.dudu.weixin.service.ValidateService;
import com.dudu.weixin.service.WxCustomerService;
import com.dudu.weixin.service.YangCheInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 所有的ajax请求
 */
@Controller
@RequestMapping("/")
public class AllAjax {
    /**
     * logprint 日志打印
     */
    private static Logger logprint = LoggerFactory.getLogger(AllAjax.class);
    /**
     * session
     */
    @Autowired
    private HttpSession httpSession;
    /**
     * 联盟介绍
     */
    @Autowired
    private LianmengIntroducedService lianmengIntroducedService;
    /**
     * 联盟活动
     */
    @Autowired
    private LianMengActivityService lianMengActivityService;
    /**
     * 联盟卡包
     */
    @Autowired
    private LianMengKaService lianMengKa;
    /**
     * 养车信息
     */
    @Autowired
    private YangCheInfoService yangCheInfoService;
    /**
     * 车险投保
     */
    @Autowired
    private ChexiantoubaoService chexiantoubaoService;
    /**
     * 车险投保列表页面
     */
    @Autowired
    private CheXianService cheXianService;
    /**
     * ahi
     */
    @Autowired
    private AHIService ahiService;
    /**
     * 保养提醒
     */
    @Autowired
    private BaoYangTiXingService baoYangTiXingService;
    /**
     * 服务导航之获取店铺信息
     */
    @Autowired
    private ShopInfoService shopInfoService;
    /**
     * 消费记录
     */
    @Autowired
    private BuyRecordService buyRecordService;
    /**
     * 引入登录注册服务
     */
    @Autowired
    private AutoLoginService autoLoginService;
    /**
     * 获取用户信息
     */
    @Autowired
    private WxCustomerService wxCustomerService;
    /**
     * 引入验证码或者密码短信发送的服务
     */
    @Autowired
    private ValidateService validateService;
    /**
     * 引入个人中心的服务
     */
    @Autowired
    private PersoncenterService personcenterService;
    /**
     * 引入图片上传的接口
     */
    @Reference(version = "1.0", owner = "miaohao")
    private ApiDuduDataOssSecretConfigIntf ossSecretConfigIntf;
    /**
     * 引入微信通讯相关的接口
     */
    @Reference(version = "1.0", timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;
    /**
     * 引入微信配置的相关接口
     */
    @Reference(version = "1.0")
    private ApiWeiXinConfig weiXinConfig;

    /**
     * 每个方法的行数有限制所以分成2个方法
     *
     * @param request  请求
     * @param response 返回
     * @param fromflag 路径参数
     * @param cardNo   联盟卡号
     * @param model    返回数据
     * @return Object 返回路径
     */
    @ResponseBody
    @RequestMapping(value = "getCommonAjax2", method = RequestMethod.POST)
    public Object commonAjax2(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "fromflag", required = false) String fromflag,
                              @RequestParam(name = "cardNo", required = false) String cardNo, Model model
    ) {

        String platenumber = (String) httpSession.getAttribute("plateNumber"); //车牌号码
        String openId = (String) httpSession.getAttribute("openId"); //openid
        String lmcode = (String) httpSession.getAttribute("lmcode"); //联盟code
        String nickname = (String) httpSession.getAttribute("nickname"); //微信昵称
        //前端js获取签名
        if ("jssdk".equals(fromflag)) {
            //从参数中获取前端传过来的url
            String url = request.getParameter("url");
            //获取appid和app
            WeiXinConfig config = weiXinConfig.getWeiXinConfig(lmcode);
            //获取签名
            HashMap<String, String> stringStringHashMap = apiAllWeiXiRequest.jsSDKSign(config.getAppid(), config.getAppserect(), url);
            return stringStringHashMap;
        }
        //个人中心
        if ("personcenter".equals(fromflag)) {
            String businessType = request.getParameter("businessType");
            Object personcenter = personcenterService.personcenter(request, businessType, platenumber, lmcode);
            return personcenter;
        }

        //联盟卡激活
        if ("lianMengCardActivate".equals(fromflag)) {
            String cardnum = request.getParameter("cardnum"); //卡号
            String activecode = request.getParameter("activecode"); //激活码
            String active = lianMengKa.active(lmcode, cardnum, activecode, platenumber);
            return active;
        }
        //点击登录按钮
        if ("checklogin".equals(fromflag)) {
            String password = request.getParameter("password"); //密码
            platenumber = request.getParameter("platenumber"); //车牌号
            if (lmcode == null || ("").equals(lmcode)) {
                lmcode = request.getParameter("lmcode"); //联盟code
                httpSession.setAttribute("lmcode", lmcode); //没有的话将session存入
            }
            String login = autoLoginService.login(platenumber.toUpperCase(), lmcode, password, openId, nickname);
            return login;
        }
        //登录,注册,填写车牌号后发送请求
        if ("checkInfo".equals(fromflag)) {
            if (platenumber == null || ("").equals(platenumber)) {
                platenumber = request.getParameter("platenumber"); //车牌号
                httpSession.setAttribute("platenumber", platenumber); //没有的话将session存入
            }
            if (lmcode == null || ("").equals(lmcode)) {
                lmcode = request.getParameter("lmcode"); //联盟code
                httpSession.setAttribute("lmcode", lmcode); //没有的话将session存入
            }
            String s = autoLoginService.checkInfo(platenumber, lmcode);
            return s;
        }
        //注册,如果已经有该用户,但是没有密码,获取手机号发送密码短信
        if ("getmobiePhone".equals(fromflag)) {
            platenumber = request.getParameter("platenumber");
            WxCustomer wxCustomer = wxCustomerService.getWxCustomer(platenumber, lmcode);
            return wxCustomer.getCustomerMobile();
        }
        //用户注册时,进行发送验证码
        if ("addvalidate".equals(fromflag)) {
            String mobilephone = request.getParameter("mobilephone");
            platenumber = request.getParameter("platenumber"); //车牌号
            validateService.addvalidate(platenumber, lmcode, mobilephone);
        }
        //用户注册时,点击提交
        if ("register".equals(fromflag)) {
            platenumber = request.getParameter("platenumber"); //车牌号
            String mobilephone = request.getParameter("mobilephone");
            String password = request.getParameter("password");
            String verificationCode = request.getParameter("verificationCode");
            String register = autoLoginService.register(platenumber.toUpperCase(), lmcode, password, openId, mobilephone, verificationCode, nickname);
            return register;
        }
        return null;
    }

    /**
     * @param request  请求
     * @param response 返回
     * @param fromflag 路径参数
     * @param cardNo   车牌号
     * @param model    返回数据
     * @return Object 返回路径
     */
    @ResponseBody
    @RequestMapping(value = "getCommonAjax", method = RequestMethod.POST)
    public Object commonAjax(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "fromflag", required = false) String fromflag,
                             @RequestParam(name = "cardNo", required = false) String cardNo, Model model
    ) {
        response.setCharacterEncoding("UTF-8");
        String platenumber = (String) httpSession.getAttribute("plateNumber"); //车牌号
        String openId = (String) httpSession.getAttribute("openId"); //微信的openid
        String lmcode = (String) httpSession.getAttribute("lmcode"); //联盟code
        Object obj = null;
        fromflag = encodingUrl(fromflag);


        //联盟卡主页信息列表
        if ("queryLmkInfoList".equals(fromflag)) {
            List<LianmengkaXmLeftResultModule> lianmengkaXmLeftResultModules = lianMengKa.queryLmkInfo(lmcode, platenumber);
            return lianmengkaXmLeftResultModules;
        }
        //联盟卡消费明细页面
        if ("queryLmkXiaoFeiMX".equals(fromflag)) {
            List<LianmengkaXmCustResultModule> lianmengkaXmCustResultModules = lianMengKa.queryLmkXiaoFeiMX(lmcode, cardNo, platenumber);
            return lianmengkaXmCustResultModules;
        }
        //获取联盟卡发卡店铺名称
        if ("getXmkCardInfo".equals(fromflag)) {
            List<LianmengKaResultModule> xmkCardInfo = lianMengKa.getXmkCardInfo(lmcode, cardNo, platenumber);
            return xmkCardInfo;
        }
        //获取联盟卡二维码
        if ("getXmkQRCode".equals(fromflag)) {

            String cardid = encodingUrl(request.getParameter("card_id"));
            String itemcode = encodingUrl(request.getParameter("item_code"));
            String typeflg = encodingUrl(request.getParameter("type_flg"));
            String xmkQRCode = lianMengKa.getXmkQRCode(cardid, itemcode, typeflg);
            return xmkQRCode;
        }
        //获取养车信息列表信息
        if ("queryYangCheInfo".equals(fromflag)) {
            return yangCheInfoService.queryInfoList(lmcode);
        }
        //养车信息详情
        if ("getYangCheInfo".equals(fromflag)) {
            String ids = request.getParameter("ids");
            String id = encodingUrl(ids);
            return yangCheInfoService.getInfo(Integer.parseInt(id));
        }
        //客户确定购买保险的保险公司
        if ("toubao".equals(fromflag)) {
            return chexiantoubaoService.updateInsuranceOrderCompany(request);
        }
        //获取联盟活动信息
        if ("queryLMActivity".equals(fromflag)) {
            return lianMengActivityService.queryInfoList(lmcode);
        }
        //单查联盟活动
        if ("getLianMengActivity".equals(fromflag)) {
            String ids = request.getParameter("ids");
            String id = encodingUrl(ids);
            return lianMengActivityService.getInfo(Integer.parseInt(id));
        }
        //联盟介绍
        if ("getIntroduced".equals(fromflag)) {
            return lianmengIntroducedService.queryEntry(lmcode);
        }
        //车险投保(保险公司)
        if ("baoxianGongSi".equals(fromflag)) {
            String shopCodeLm = request.getParameter("shopCodeLm");
            return chexiantoubaoService.baoxianGongSi(shopCodeLm);
        }
        //车险投保()
        if ("baoXianTypes".equals(fromflag)) {
            return chexiantoubaoService.baoXianTypes();
        }
        //AHI列表
        if ("queryAllPointByPlateNumber".equals(fromflag)) {
            return ahiService.queryAllPointByPlateNumber(platenumber);
        }
        //AHI详情
        if ("queryCarPointOne".equals(fromflag)) {
            return ahiService.queryCarPointOne(platenumber);
        }
        //AHI
        if ("queryCarPointTwo".equals(fromflag)) {
            String ratio = request.getParameter("ratio");
            String id = request.getParameter("id");
            return ahiService.queryCarPointTwo(platenumber, id, ratio);
        }
        //AHI请求三级页面将数据穿过去
        if ("queryCarPointTwo".equals(fromflag)) {
            String ratio = request.getParameter("ratio");
            String id = request.getParameter("id");
            return ahiService.queryCarPointTwo(platenumber, id, ratio);
        }
        //保养提醒
        if ("baoYangList".equals(fromflag)) {
            String top = request.getParameter("top");
            ArrayList baoYangListByLmcodeAndCarNo = baoYangTiXingService.getBaoYangListByLmcodeAndCarNo(lmcode, platenumber, top);
            return baoYangListByLmcodeAndCarNo;

        }
        //消费记录
        if ("baoYangList".equals(fromflag)) {
            String top = request.getParameter("top");
            ArrayList serviceListByLmcodeAndCarNo = buyRecordService.getServiceListByLmcodeAndCarNo(lmcode, platenumber, top);
            return serviceListByLmcodeAndCarNo;
        }
        //添加服务顾问
        if ("fuwuguwen".equals(fromflag)) {
            String guwenshopcode = request.getParameter("mineShopCode");
            return chexiantoubaoService.queryFuWuGuWen(guwenshopcode);
        }
        //联盟总部
        if ("lianmeng".equals(fromflag)) {
            String shopCode = request.getParameter("mineShopCode");
            List<ShopQueryFruit> shopQueryFruits = chexiantoubaoService.queryLianMengZB(shopCode);
            return shopQueryFruits;
        }
        //车辆信息
        if ("xinxi".equals(fromflag)) {
            String parameter = request.getParameter("car_number");
            String xinxishopcode = request.getParameter("mineShopCode");
            return chexiantoubaoService.queryCheLiangXinXi(parameter, xinxishopcode);
        }
        //服务导航
        if ("queryShopCodeListByLmCode".equals(fromflag)) {
            String shopTypesearch = request.getParameter("shopType_search");
            String orderTypesearch = request.getParameter("orderType_search");
            return shopInfoService.queryShopCodeListByLmCode(lmcode, shopTypesearch, orderTypesearch);
        }

        return obj;
    }

    /**
     * 图片上传
     *
     * @param businessConfigId 业务id
     * @param shopCode         店管家代码
     * @return OssSecretConfig上传权限
     */
    @ResponseBody
    @RequestMapping(value = "ossconfig/{shopCode}/{businessConfigId}", method = RequestMethod.GET)
    public OssSecretConfig getConfig(@PathVariable("businessConfigId") Integer businessConfigId, @PathVariable("shopCode") String shopCode) {
        return ossSecretConfigIntf.getOssSecretConfig(new OssSecretConfigParam().setShopCode(shopCode).setBusinessConfigId(businessConfigId));
    }

    /**
     * 获取保险列表
     * @param request 参数
     * @return List<Insurance> 保险列表
     */
    @ResponseBody
    @RequestMapping(value = "findInsurance", method = RequestMethod.POST)
    public List<Insurance> queryInsurance(HttpServletRequest request) {
        List<Insurance> list = cheXianService.queryBaoXianList(request);
        return list;
    }

    /**
     * @param shopCode    店铺编码
     * @param shopCodeLm  联盟编码
     * @param companyId   保险公司ID
     * @param orderNumb   订单单号
     * @param plateNumber 车牌号
     * @return ClientInsuranceResult 结果返回
     */

    @ResponseBody
    @RequestMapping(value = "findClientInsurance/{shopCode}/{shopCodeLm}/{companyId}/{orderNumb}/{plateNumber}", method = RequestMethod.GET)
    public ClientInsuranceResult queryClientInsurance(@PathVariable("shopCode") String shopCode, @PathVariable("shopCodeLm") String shopCodeLm,
                                                      @PathVariable("companyId") Integer companyId, @PathVariable("orderNumb") String orderNumb,
                                                      @PathVariable("plateNumber") String plateNumber) {
        ClientInsuranceTypeParam ctp = new ClientInsuranceTypeParam();
        if (companyId != null) {
            ctp.setCompanyId(companyId);
        }
        if (shopCodeLm != null && !shopCodeLm.equals("")) {
            ctp.setShopCodeLm(shopCodeLm);
        }
        if (shopCode != null && !shopCode.equals("")) {
            ctp.setShopCode(shopCode);
        }
        if (orderNumb != null && !orderNumb.equals("")) {
            ctp.setOrderNumb(orderNumb);
        }
        if (plateNumber != null && !plateNumber.equals("")) {
            ctp.setPlateNumber(plateNumber);
        }
        ClientInsuranceResult clientInsurance = chexiantoubaoService.getClientInsurance(ctp);
        if (clientInsurance != null) {
            return clientInsurance;
        }
        return null;
    }


    /**
     * @param str 传进需要解析的字符串
     * @return String返回字符串
     */
    public String encodingUrl(String str) {
        try {
            if (str != null) {
                return java.net.URLDecoder.decode(str, "UTF-8").trim();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
