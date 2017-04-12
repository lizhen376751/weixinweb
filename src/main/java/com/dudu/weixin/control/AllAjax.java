package com.dudu.weixin.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.baoxian.kaidan.module.BaoXianList;
import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.baoxian.kaidan.module.Insurance;
import com.dudu.soa.baoxian.kaidan.module.InsuranceCompanyDetails;
import com.dudu.soa.dududata.oss.api.ApiDuduDataOssSecretConfigIntf;
import com.dudu.soa.dududata.oss.module.OssSecretConfig;
import com.dudu.soa.dududata.oss.module.param.OssSecretConfigParam;
import com.dudu.soa.lmbasedata.basedata.shop.module.ShopQueryFruit;
import com.dudu.soa.lmk.operate.module.LianmengKaResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmCustResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;
import com.dudu.soa.lmk.wxcustomer.module.WxCustomer;
import com.dudu.weixin.service.AHIService;
import com.dudu.weixin.service.AutoLoginService;
import com.dudu.weixin.service.BaoYangTiXingService;
import com.dudu.weixin.service.BuyRecordService;
import com.dudu.weixin.service.ChexiantoubaoService;
import com.dudu.weixin.service.LianMengActivityService;
import com.dudu.weixin.service.LianMengKaService;
import com.dudu.weixin.service.LianmengIntroducedService;
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
     * 引入图片上传的接口
     */
    @Reference(version = "1.0", owner = "miaohao")
    private ApiDuduDataOssSecretConfigIntf ossSecretConfigIntf;

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
            String login = autoLoginService.login(platenumber, lmcode, password, openId);
            return login;
        }
        //登录,注册,填写车牌号后发送请求
        if ("checkInfo".equals(fromflag)) {
            System.out.println("注册进入=========" + platenumber + "," + lmcode);
            platenumber = request.getParameter("platenumber"); //车牌号
            String s = autoLoginService.checkInfo(platenumber, lmcode);
            return s;
        }
        //注册,如果已经有该用户,但是没有密码,获取手机号发送密码短信
        if ("getmobiePhone".equals(fromflag)) {
            System.out.println("注册进入=========" + platenumber + "," + lmcode);
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
            String register = autoLoginService.register(platenumber, lmcode, password, openId, mobilephone, verificationCode);
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
            return chexiantoubaoService.baoxianGongSi();
        }
        //车险投保()
        if ("baoXianTypes".equals(fromflag)) {
            return chexiantoubaoService.baoXianTypes();
        }
        //AHI
        String plateNumber = request.getParameter("plateNumber");
        if ("queryAllPointByPlateNumber".equals(fromflag)) {
            return ahiService.queryAllPointByPlateNumber(plateNumber);
        }
        //AHI
        if ("queryCarPointOne".equals(fromflag)) {
            logprint.debug("ahi" + plateNumber);
            return ahiService.queryCarPointOne(plateNumber);
        }
        //AHI
        if ("queryCarPointTwo".equals(fromflag)) {
            String ratio = request.getParameter("ratio");
            String id = request.getParameter("id");
            return ahiService.queryCarPointTwo(plateNumber, id, ratio);
        }
        //保养提醒
        if ("baoYangList".equals(fromflag)) {
            String top = request.getParameter("top");
            ArrayList baoYangListByLmcodeAndCarNo = baoYangTiXingService.getBaoYangListByLmcodeAndCarNo(lmcode, platenumber, top);
            System.out.println("保养提醒进入======");
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
            List<ShopQueryFruit> shopQueryFruits = chexiantoubaoService.queryLianMengZB();
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
     * @param shopCode 店铺编码
     * @return List<BaoXianList>
     */
    @ResponseBody
    @RequestMapping(value = "findInsurance/{shopCode}", method = RequestMethod.GET)
    public List<Insurance> queryInsurance(@PathVariable("shopCode") String shopCode) {
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        if (null != shopCode && !"".equals(shopCode)) {
            List<String> list = new ArrayList<>();
            list.add(shopCode);
            baoXianParamList.setShopCode(list);
        }
        List<BaoXianList> baoXianLists = chexiantoubaoService.queryInsurance(baoXianParamList);
        List<Insurance> list = new ArrayList<>();
        String orderNun = "";
        for (int i = 0; i < baoXianLists.size(); i++) {
            if (i == 0) {
                BaoXianList bs = baoXianLists.get(0);
                Insurance is = new Insurance();
                is.setShopCode(bs.getShopCode());
                is.setOrderNumb(bs.getOrderNumb());
                orderNun = bs.getOrderNumb();
                is.setKaiDanDate(bs.getKaiDanDate());
                is.setFuKuanFlag(bs.getFuKuanFlag());
                is.setAssistant(bs.getAssistant());
                is.setCarId(bs.getCarId());
                is.setCustomerId(bs.getCustomerId());
                is.setShopcodelm(bs.getShopcodelm());
                List<InsuranceCompanyDetails> list1 = new ArrayList<>();
                InsuranceCompanyDetails icd = new InsuranceCompanyDetails();
                icd.setTotalPrices(bs.getTotalPrices());
                icd.setInsurancename(bs.getInsurancename());
                icd.setBaoJiaState(bs.getBaoJiaState());
                icd.setCompanyid(bs.getCompanyid());
                list1.add(icd);
                is.setList(list1);
                list.add(is);
            } else {
                if (baoXianLists.get(i).getOrderNumb().equals(list.get(list.size() - 1))) {
                    InsuranceCompanyDetails is = new InsuranceCompanyDetails();
                    is.setCompanyid(baoXianLists.get(i).getCompanyid());
                    is.setBaoJiaState(baoXianLists.get(i).getBaoJiaState());
                    is.setInsurancename(baoXianLists.get(i).getInsurancename());
                    is.setTotalPrices(baoXianLists.get(i).getTotalPrices());
                    list.get(list.size() - 1).getList().add(is);
                } else {
                    Insurance ic = new Insurance();
                    ic.setShopcodelm(baoXianLists.get(i).getShopcodelm());
                    ic.setCustomerId(baoXianLists.get(i).getCustomerId());
                    ic.setCarId(baoXianLists.get(i).getCarId());
                    ic.setAssistant(baoXianLists.get(i).getAssistant());
                    ic.setFuKuanFlag(baoXianLists.get(i).getFuKuanFlag());
                    ic.setKaiDanDate(baoXianLists.get(i).getKaiDanDate());
                    ic.setOrderNumb(baoXianLists.get(i).getOrderNumb());
                    ic.setShopCode(baoXianLists.get(i).getShopCode());
                    List<InsuranceCompanyDetails> list1 = new ArrayList<>();
                    InsuranceCompanyDetails icd = new InsuranceCompanyDetails();
                    icd.setTotalPrices(baoXianLists.get(i).getTotalPrices());
                    icd.setInsurancename(baoXianLists.get(i).getInsurancename());
                    icd.setBaoJiaState(baoXianLists.get(i).getBaoJiaState());
                    icd.setCompanyid(baoXianLists.get(i).getCompanyid());
                    list1.add(icd);
                    ic.setList(list1);
                    list.add(ic);
                }
            }
        }
        return list;
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
