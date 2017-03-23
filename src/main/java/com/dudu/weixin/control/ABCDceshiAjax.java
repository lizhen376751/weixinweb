package com.dudu.weixin.control;

import com.dudu.soa.lmbasedata.basedata.shop.module.ShopQueryFruit;
import com.dudu.soa.lmk.operate.module.LianmengKaResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmCustResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;
import com.dudu.weixin.service.AHIService;
import com.dudu.weixin.service.BaoYangTiXingService;
import com.dudu.weixin.service.BuyRecordService;
import com.dudu.weixin.service.ChexiantoubaoService;
import com.dudu.weixin.service.LianMengActivityService;
import com.dudu.weixin.service.LianMengKaService;
import com.dudu.weixin.service.LianmengIntroducedService;
import com.dudu.weixin.service.ShopInfoService;
import com.dudu.weixin.service.YangCheInfoService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/")
public class ABCDceshiAjax {
    private static final Logger logger = LoggerFactory.getLogger(ABCDceshiAjax.class);
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private LianmengIntroducedService lianmengIntroducedService;//联盟介绍
    @Autowired
    private LianMengActivityService lianMengActivityService;//联盟活动
    @Autowired
    private LianMengKaService lianMengKa;//联盟卡包
    @Autowired
    private YangCheInfoService yangCheInfoService;//养车信息
    @Autowired
    private ChexiantoubaoService chexiantoubaoService;//车险投保
    @Autowired
    private AHIService ahiService;//ahi
    @Autowired
    private BaoYangTiXingService baoYangTiXingService;//保养提醒
    @Autowired
    private ShopInfoService shopInfoService;//服务导航之获取店铺信息
    @Autowired
    private BuyRecordService buyRecordService;//消费记录
    @ResponseBody
    @RequestMapping(value = "getCommonAjax", method = RequestMethod.POST)
    public Object commonAjax(HttpServletRequest request, HttpServletResponse response, HttpSession HttpSession,
                             @RequestParam(name = "fromflag", required = false) String fromflag,
                             @RequestParam(name = "CarId", required = false) String CarId,
                             @RequestParam(name = "cardNo", required = false) String cardNo, Model model
    ) {
        response.setCharacterEncoding("UTF-8");
        String carId = (String) httpSession.getAttribute("DUDUCHEWANG_CarId");
        String openId = (String) httpSession.getAttribute("DUDUCHEWANG_OpenId");
        String shopcode = (String) httpSession.getAttribute("DUDUCHEWANG_shopcode");
        Object obj = null;
        fromflag = encodingUrl(fromflag);
        shopcode = encodingUrl(shopcode);
        String carHaoPai = CarId;
        //联盟卡主页信息列表
        if ("queryLmkInfoList".equals(fromflag)) {
            List<LianmengkaXmLeftResultModule> lianmengkaXmLeftResultModules = lianMengKa.queryLmkInfo(shopcode, carHaoPai);
            return lianmengkaXmLeftResultModules;
        }
        //联盟卡消费明细页面
        if ("queryLmkXiaoFeiMX".equals(fromflag)) {
            List<LianmengkaXmCustResultModule> lianmengkaXmCustResultModules = lianMengKa.queryLmkXiaoFeiMX(shopcode, cardNo, carHaoPai);
            return lianmengkaXmCustResultModules;
        }
        //获取联盟卡发卡店铺名称
        if ("getXmkCardInfo".equals(fromflag)) {
            List<LianmengKaResultModule> xmkCardInfo = lianMengKa.getXmkCardInfo(shopcode, cardNo, carHaoPai);
            return xmkCardInfo;
        }
        //获取联盟卡二维码
        if ("getXmkQRCode".equals(fromflag)) {
            String card_id = encodingUrl(request.getParameter("card_id"));
            String item_code = encodingUrl(request.getParameter("item_code"));
            String type_flg = encodingUrl(request.getParameter("type_flg"));
            String xmkQRCode = lianMengKa.getXmkQRCode(card_id, item_code, type_flg);
            return xmkQRCode;
        }
        //获取养车信息列表信息
        if ("queryYangCheInfo".equals(fromflag)) {
            return yangCheInfoService.queryInfoList(shopcode);
        }
        //养车信息详情
        if ("getYangCheInfo".equals(fromflag)) {
            String ids = request.getParameter("ids");
            String id = encodingUrl(ids);
            return yangCheInfoService.getInfo(Integer.parseInt(id));
        }
        //获取联盟活动信息
        if ("queryLMActivity".equals(fromflag)) {
            return lianMengActivityService.queryInfoList(shopcode);
        }
        //单查联盟活动
        if ("getLianMengActivity".equals(fromflag)) {
            String ids = request.getParameter("ids");
            String id = encodingUrl(ids);
            return lianMengActivityService.getInfo(Integer.parseInt(id));
        }
        //联盟介绍
        if ("getIntroduced".equals(fromflag)) {
            return lianmengIntroducedService.queryEntry(shopcode);
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
            logger.debug("ahi" + plateNumber);
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
            ArrayList baoYangListByLmcodeAndCarNo = baoYangTiXingService.getBaoYangListByLmcodeAndCarNo(shopcode, carId, top);
            return baoYangListByLmcodeAndCarNo;

        }
        //消费记录
        if ("baoYangList".equals(fromflag)) {
            String top = request.getParameter("top");
            ArrayList serviceListByLmcodeAndCarNo = buyRecordService.getServiceListByLmcodeAndCarNo(shopcode,carId,top);
            return serviceListByLmcodeAndCarNo;
        }
        //添加服务顾问
        if ("fuwuguwen".equals(fromflag)) {
            //String  guwen_shopcode = (String) HttpSession.getAttribute("DUDUCHEWANG_shopcode");
            String guwen_shopcode = "0533001";
            return chexiantoubaoService.queryFuWuGuWen(guwen_shopcode);
        }
        //联盟总部
        if ("lianmeng".equals(fromflag)) {
            List<ShopQueryFruit> shopQueryFruits = chexiantoubaoService.queryLianMengZB();
            return shopQueryFruits;
        }
        //车辆信息
        if ("xinxi".equals(fromflag)) {
            //String  xinxi_shopcode = (String) HttpSession.getAttribute("DUDUCHEWANG_shopcode");
            String parameter = request.getParameter("car_number");
            String xinxi_shopcode = "0533001";
            return chexiantoubaoService.queryCheLiangXinXi(parameter, xinxi_shopcode);
        }
        //服务导航
        if ("queryShopCodeListByLmCode".equals(fromflag)) {
            String shopType_search = request.getParameter("shopType_search");
            String orderType_search = request.getParameter("orderType_search");
            return shopInfoService.queryShopCodeListByLmCode(shopcode,shopType_search,orderType_search);
        }

        return obj;
    }

    //将传过来的参数去除空格,并统一字符集
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
