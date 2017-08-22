package com.dudu.weixin.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dudu.soa.thq.app.mine.peercircle.api.ApiPeerCircle;
import com.dudu.soa.thq.app.mine.peercircle.module.PeerCircleList;
import com.dudu.soa.trainingclassroom.api.ApiCourseTrain;
import com.dudu.soa.trainingclassroom.module.Course;
import com.dudu.soa.weixindubbo.weixin.http.api.ApiAllWeiXiRequest;
import com.dudu.soa.weixindubbo.weixin.http.module.parammodule.AccessToken;
import com.dudu.weixin.service.CreateMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

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
     * 引入同行圈接口
     */
    @Reference
    private ApiPeerCircle apiPeerCircle;

    /**
     * 网络课堂
     */
    @Reference
    private ApiCourseTrain apiCourseTrain;

    /**
     * 引入微信通讯相关接口
     */
    @Reference(timeout = 300000)
    private ApiAllWeiXiRequest apiAllWeiXiRequest;
    /**
     * session
     */
    @Autowired
    private HttpSession httpSession;



    /**
     * 微信内部跳转测试(没用)
     * @param request 请求
     * @return 跳转页面
     */
    @RequestMapping(value = "/testjump", method = {RequestMethod.GET, RequestMethod.POST})
    public void testjump(HttpServletRequest request) {
        String code = request.getParameter("code");
        log.debug("页面内部跳转code" + code);
    }

    /**
     * 微信网页授权回调页面
     *
     * @param request     请求
     * @param response    返回
     * @param wenjianming 文件名字
     * @return 路径
     */
    @ResponseBody
    @RequestMapping(value = "/MP_verify_{wenjianming}.txt", method = RequestMethod.GET)
    public Object duduchewangCheGuanjia(@PathVariable String wenjianming, HttpServletRequest request, HttpServletResponse response) {
        return wenjianming;
    }

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
        String shopTypesearch = request.getParameter("shopType_search"); //获取服务导航所要选择的标签
        String shopCode = request.getParameter("shopCode");
        //对中文进行编码
        shopTypesearch = URLEncoder.encode(shopTypesearch, "utf-8");
        //如果获取地理位置失败,默认一个位置,避免页面出错
        if ("".equals(longitude) || null == longitude) {
            longitude = "121.59543";
        }
        if ("".equals(latitude) || null == latitude) {
            longitude = "29.910757";
        }
        log.info(latitude + "获取的地理位置为:" + longitude);
        response.sendRedirect("http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?"
                + "shopcode=" + shopCode + "&latitude=" + latitude + "&longitude=" + longitude + "&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4&shopType_search=" + shopTypesearch);

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
     * 微信分享培训课堂
     *
     * @param id    课程id
     * @param model 页面
     * @return 路径
     */
    @RequestMapping(value = "/network/{id}", method = RequestMethod.GET)
    public String tonghangquan(@PathVariable("id") String id, Model model) {
        Course course = apiCourseTrain.weChatCourse(Integer.parseInt(id));
        model.addAttribute("courseName", course.getCourseName()); //课程名称
        model.addAttribute("picture", course.getPicture()); //图片地址
        model.addAttribute("courseBrief", course.getCourseBrief()); //课程简介
        return "/weixinfenxiang/network.jsp"; //培训课堂
    }


    /**
     * 微信分享同行圈
     *
     * @param id    同行圈id
     * @param model 页面
     * @return 路径
     */
    @RequestMapping(value = "/wechatshare/{id}", method = RequestMethod.GET)
    public String weixinfenxiang(@PathVariable("id") String id, Model model) {
        PeerCircleList peerCircleList = apiPeerCircle.weChatQuery(Integer.parseInt(id));
        model.addAttribute("createName", peerCircleList.getCreateName()); //创建者
        model.addAttribute("headImg", peerCircleList.getHeadImg()); //头像
        model.addAttribute("workTypeName", peerCircleList.getWorkTypeName()); //文章类型
        model.addAttribute("interval", peerCircleList.getInterval()); //时间
        model.addAttribute("details", peerCircleList.getDetails()); //内容
        model.addAttribute("imgs", peerCircleList.getImgs()); //文章图片集合
        model.addAttribute("likeNum", peerCircleList.getLikeNum()); //点赞数
        model.addAttribute("commentNum", peerCircleList.getCommentNum()); //评论数
        return "/weixinfenxiang/share.jsp"; //微信分享
    }

    /**
     * @param request 请求
     * @return token的实体类
     */
    @ResponseBody
    @RequestMapping(value = "/getToken", method = {RequestMethod.GET, RequestMethod.POST})
    public Object tonghangquan(HttpServletRequest request) {
        String appid = request.getParameter("appid");
        String appsecret = request.getParameter("appsecret");
        AccessToken tokengetTicket = apiAllWeiXiRequest.getTokengetTicket(appid, appsecret);
        return tokengetTicket; //获取的开发者的token
    }

    /**
     * ahi页面
     *
     * @param request 请求
     * @return 路径字符串
     */
    @RequestMapping(value = "/ahiTiXing", method = {RequestMethod.GET, RequestMethod.POST})
    public String ahi(HttpServletRequest request) {
        String plateNumber = request.getParameter("plateNumber");
        httpSession.setAttribute("plateNumber", plateNumber);
        return "/ahi/AHIxiangqing.jsp";
    }


}
