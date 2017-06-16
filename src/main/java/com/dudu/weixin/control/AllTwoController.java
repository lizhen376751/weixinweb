package com.dudu.weixin.control;

import com.dudu.weixin.service.CreateMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 不是微信公众号内部页面跳转
 * Created by lizhen on 2017/4/15.
 */
@Controller
public class AllTwoController {
    /**
     * logprint 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(AllTwoController.class);
    /**
     * 引入创建菜单的服务
     */
    @Autowired
    private CreateMenuService createMenuService;

    /**
     * 关于服务导航的url跳转(后期去掉)
     *
     * @param request  请求
     * @param response 相应
     * @throws Exception 异常
     */
    @RequestMapping(value = "/fuwudaohang", method = RequestMethod.GET)
    public void preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String longitude = request.getParameter("latitude");
        String latitude = request.getParameter("longitude");
        //如果获取地理位置失败,默认一个位置,避免页面出错
        if ("".equals(longitude) || null == longitude) {
            longitude = "121.59543";
        }
        if ("".equals(latitude) || null == latitude) {
            longitude = "29.910757";
        }
        log.info(latitude + "获取的地理位置为:" + longitude);
//        System.out.println("==============" + latitude + "," + longitude);

//         String latitude = "29.910757";
//         String longitude = "121.59543";
//         System.out.println("==============" + latitude + "," + longitude);
//        http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?shopcode=FL000&latitude=29.910757&longitude=121.59543&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4
        response.sendRedirect("http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?"
                + "shopcode=FL000&latitude=" + latitude + "&longitude=" + longitude + "&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4");

    }

    /**
     * 生成菜单
     *
     * @param code      店铺编码
     * @param type      菜单的类型
     * @param appid     appid
     * @param appSecret sercert
     * @param url       服务器地址
     * @param yuming    服务器域名
     * @return 字符串
     */
    @ResponseBody
    @RequestMapping(value = "createMenu/{code}/{type}/{appid}/{appSecret}/{url}/{yuming}", method = RequestMethod.GET)
    public String duduchewangceshipingtai(@PathVariable("code") String code, @PathVariable("type") String type,
                                          @PathVariable("appid") String appid, @PathVariable("appSecret") String appSecret,
                                          @PathVariable("url") String url, @PathVariable("yuming") String yuming) {
        url = url + yuming;
        String menu = createMenuService.createMenu(type, code, appid, appSecret, url);
        log.info("生成菜单====================" + menu);
        return menu;
    }

    /**
     * 同行圈
     *
     * @return 跳转路径
     */
    @RequestMapping(value = "/network", method = RequestMethod.GET)
    public String tonghangquan() {
        return "/weixinfenxiang/network.jsp"; //同行圈
    }

    /**
     * 微信分享
     *
     * @return 跳转路径
     */
    @RequestMapping(value = "/wechatshare", method = RequestMethod.GET)
    public String weixinfenxiang() {
        return "/weixinfenxiang/share.jsp"; //同行圈
    }
}
