package com.dudu.weixin.control;

import com.dudu.soa.lmk.operate.module.LianmengkaXmCustResultModule;
import com.dudu.soa.lmk.operate.module.LianmengkaXmLeftResultModule;
import com.dudu.weixin.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/")
public class ABCDceshiAjax {
    private static final Logger logger = LoggerFactory.getLogger(ABCDceshiAjax.class);

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

    @ResponseBody
    @RequestMapping(value = "getCommonAjax", method = RequestMethod.POST)
    public Object commonAjax(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "fromflag", required = false) String fromflag,
                             @RequestParam(name = "shopcode", required = false) String shopcode,
                             @RequestParam(name = "CarId", required = false) String CarId,
                             @RequestParam(name = "cardNo", required = false) String cardNo
    ) {
        logger.debug("ajax进入===============" + fromflag);
        response.setCharacterEncoding("UTF-8");
        Object obj = null;
        fromflag = encodingUrl(fromflag);
        shopcode = encodingUrl(shopcode);
        String carHaoPai = CarId;
        /*
         * 联盟卡
		 */


        //联盟卡主页信息列表
        //TODO 后期需要添加判断
        if ("queryLmkInfoList".equals(fromflag)) {
            logger.info("联盟卡主页信息列表"+shopcode+carHaoPai);
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
//            obj = lianMengKa.getXmkCardInfo(shopcode, cardNo, carHaoPai);
        }
		/*
		 * 养车信息
		 */

        //获取养车信息列表信息
        if ("queryYangCheInfo".equals(fromflag)) {
            logger.debug("养车信息列表");
            return yangCheInfoService.queryInfoList("CS000");
        }
        //养车信息详情
        if ("getYangCheInfo".equals(fromflag)) {
            logger.debug("养车信息");
            String ids = request.getParameter("ids");
            String id = encodingUrl(ids);
            return yangCheInfoService.getInfo(Integer.parseInt(id));
        }

		/*
		 * 联盟活动
		 */

        //获取联盟活动信息
        if ("queryLMActivity".equals(fromflag)) {

            logger.debug("获取联盟活动信息");
            return lianMengActivityService.queryInfoList("CS000");
        }

        //单查联盟活动
        if ("getLianMengActivity".equals(fromflag)) {
            logger.debug("单查联盟活动");
            String ids = request.getParameter("ids");
            String id = encodingUrl(ids);
            return lianMengActivityService.getInfo(Integer.parseInt(id));
        }

        //联盟介绍
        if ("getIntroduced".equals(fromflag)) {
            logger.debug("联盟介绍");
            return lianmengIntroducedService.queryEntry("CS000");
        }
        //车险投保(保险公司)

        if ("baoxianGongSi".equals(fromflag)) {
            logger.debug("保险公司");
            return chexiantoubaoService.baoxianGongSi();
        }
        //车险投保()
        if ("baoXianTypes".equals(fromflag)) {
            logger.debug("保险类型");
            return chexiantoubaoService.baoXianTypes();
        }

        //AHI
        String plateNumber = request.getParameter("plateNumber");
        String strOpenId = request.getParameter("strOpenId");
        if ("queryAllPointByPlateNumber".equals(fromflag)) {
            logger.debug("ahi" + plateNumber);
            return ahiService.queryAllPointByPlateNumber(plateNumber);
        }
        //AHI
        if ("queryCarPointOne".equals(fromflag)) {
            logger.debug("ahi" + plateNumber);
            return ahiService.queryCarPointOne(plateNumber);
        }
        //AHI
        if ("queryCarPointTwo".equals(fromflag)) {
            logger.debug("ahi" + plateNumber);
            String ratio = request.getParameter("ratio");
            String id = request.getParameter("id");
            return ahiService.queryCarPointTwo(plateNumber, id, ratio);
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
