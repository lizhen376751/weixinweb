package com.dudu.weixin.shopweiixin.controller;

import com.dudu.weixin.shopweiixin.service.ShopBaoYangTiXingService;
import com.dudu.weixin.shopweiixin.service.ShopPersonCenterService;
import com.dudu.weixin.shopweiixin.service.ShopShiGongBuZhouService;
import com.dudu.weixin.shopweiixin.service.ShopWeixinLoginService;
import com.dudu.weixin.shopweiixin.service.ShopXiaoFeiJiLuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 所有的店管家微信的Ajax入口
 * Created by lizhen on 2017/5/2.
 */
@Controller
@RequestMapping("/")
public class ShopWeiXinAjax {
    /**
     * logprint 日志打印
     */
    private static Logger logprint = LoggerFactory.getLogger(ShopWeiXinAjax.class);
    /**
     * 引入登录的服务
     */
    @Autowired
    private ShopWeixinLoginService shopWeixinLogin;
    /**
     * 引入session
     */
    @Autowired
    private HttpSession httpSession;
    /**
     * 引入消费记录服务
     */
    @Autowired
    private ShopXiaoFeiJiLuService shopXiaoFeiJiLu;
    /**
     * 引入保养提醒的服务
     */
    @Autowired
    private ShopBaoYangTiXingService shopBaoYangTiXing;
    /**
     * 查看施工步骤
     */
    @Autowired
    private ShopShiGongBuZhouService shopShiGongBuZhou;
    /**
     * 个人中心服务
     */
    @Autowired
    private ShopPersonCenterService shopPersonCenterService;

    /**
     * @param request 请求
     * @return 对象
     */
    @ResponseBody
    @RequestMapping(value = "shopAjax", method = RequestMethod.POST)
    public Object commonAjax(HttpServletRequest request) {
        String shopcode = (String) httpSession.getAttribute("shopcode");
        String plateNumber = (String) httpSession.getAttribute("plateNumber");
        String businessType = request.getParameter("businessType");
        if ("login".equals(businessType)) { //登录页面
            return shopWeixinLogin.checklogin(shopcode, request);
        } else if ("xiaofeijilu".equals(businessType)) { //消费记录页面
            return shopXiaoFeiJiLu.queryXiaoFeiJiLu(shopcode, plateNumber);
        } else if ("shigongbuzhou".equals(businessType)) { //施工步骤
            return shopShiGongBuZhou.queryListShiGongBuZhou(request);
        } else if ("biaozhunliucheng".equals(businessType)) { //标准流程查看
            return shopShiGongBuZhou.queryProjectProcess(request);
        } else if ("baoYangTiXingList".equals(businessType)) { //保养提醒页面
            return shopBaoYangTiXing.queryBaoYangTiXing(shopcode, plateNumber);
        } else if ("shoppersoncenter".equals(businessType)) { //个人中心
            return shopPersonCenterService.personcenter(request, plateNumber, shopcode);
        }
        return null;
    }
}
