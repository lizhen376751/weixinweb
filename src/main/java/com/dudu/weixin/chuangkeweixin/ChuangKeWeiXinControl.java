package com.dudu.weixin.chuangkeweixin;

import com.dudu.weixin.service.LogInLogService;
import com.dudu.weixin.shopweiixin.service.ShopXiaoFeiJiLuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 店管家所有的页面跳转
 * Created by lizhen on 2017/5/2.
 */

@Controller
@RequestMapping("/")
public class ChuangKeWeiXinControl {
    /**
     * 日志打印
     */
    private static Logger logprint = LoggerFactory.getLogger(ChuangKeWeiXinControl.class);
    /**
     * 引入消费记录服务
     */
    @Autowired
    private ShopXiaoFeiJiLuService shopXiaoFeiJiLu;
    /**
     * session
     */
    @Autowired
    private HttpSession httpSession;
    /**
     * 登录服务
     */
    @Autowired
    private LogInLogService logInLogService;

    /**
     * 所有店管家微信菜单的页面的跳转
     *
     * @param request 请求
     * @param model   模版参数
     * @return 字符串路径
     */
    @RequestMapping(value = "chuangkeweixinMenu", method = RequestMethod.GET)
    public String shopWeiXinMenuPageJump(HttpServletRequest request, Model model) {
        String openId = (String) httpSession.getAttribute("openId");
        String lmcode = request.getParameter("lmcode");
        String flagStr = lmcode.split("_")[1]; //页面跳转判断
        String shopcode = lmcode.split("_")[0]; //联盟code
        if ("paymentDetails".equals(flagStr)) {
            return "/ahi/AHIxiangqing.jsp"; //收付明细
        } else if ("storeSign".equals(flagStr)) {
            return "/shopxiaofeijilu/xiaofeijilu.jsp"; //门店签收
        } else if ("deliveryRecord".equals(flagStr)) {
            return "/shopbaoyangtixing/baoYangList.jsp"; //发货记录
        } else if ("buyMore".equals(flagStr)) {
            return "/shoppersonCenter/shoppersonalCenter.jsp"; //我要进货
        } else if ("microClass".equals(flagStr)) { //微课堂
            return "redirect:http://www.duduchewang.cn/weixincore_cn/zy.jsp?shopcode=" + shopcode + "&openid=" + openId;
        } else if ("microMarketing".equals(flagStr)) { //微营销
            return "redirect:http://www.duduchewang.cn/weixincore_cn/evaluateList.jsp?shopcode=" + shopcode + "&openId=" + openId;
        } else if ("microVideo".equals(flagStr)) {  //微视频
            return "/ahi/AHIxiangqing.jsp";
        } else if ("pullTies".equals(flagStr)) { //拉关系
            return "/shopxiaofeijilu/xiaofeijilu.jsp";
        } else if ("turfUp".equals(flagStr)) { //抢地盘
            return "/shopbaoyangtixing/baoYangList.jsp";
        } else if ("informationList".equals(flagStr)) { //信息榜
            return "/shoppersonCenter/shoppersonalCenter.jsp";
        } else if ("grabAMission".equals(flagStr)) { //抢任务
            return "redirect:http://www.duduchewang.cn/weixincore_cn/zy.jsp?shopcode=" + shopcode + "&openid=" + openId;
        } else if ("myEquipment".equals(flagStr)) { //我的装备
            return "redirect:http://www.duduchewang.cn/weixincore_cn/evaluateList.jsp?shopcode=" + shopcode + "&openId=" + openId;
        } else if ("myTerritory".equals(flagStr)) { //我的地盘
            return "redirect:http://www.duduchewang.cn/weixincore_cn/zy.jsp?shopcode=" + shopcode + "&openid=" + openId;
        } else if ("personCenter".equals(flagStr)) { //个人中心
            return "redirect:http://www.duduchewang.cn/weixincore_cn/evaluateList.jsp?shopcode=" + shopcode + "&openId=" + openId;
        }
        return null;
    }



}
