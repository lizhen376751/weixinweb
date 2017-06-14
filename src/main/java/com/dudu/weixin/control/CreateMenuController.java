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

/**
 * 创建菜单的控制器
 * Created by lizhen on 2017/5/4.
 */
@Controller
public class CreateMenuController {
    /**
     * logprint 日志打印
     */
    private static Logger logprint = LoggerFactory.getLogger(CreateMenuController.class);
    /**
     * 引入创建菜单的服务
     */
    @Autowired
    private CreateMenuService createMenuService;

    /**
     * 生成菜单
     *
     * @param code      店铺编码
     * @param type      菜单的类型
     * @param appid     appid
     * @param appSecret sercert
     * @param url       服务器地址
     * @param yuming    服务器域名
     */
    @ResponseBody
    @RequestMapping(value = "createMenu/{code}/{type}/{appid}/{appSecret}/{url}/{yuming}", method = RequestMethod.GET)
    public String duduchewangceshipingtai(@PathVariable("code") String code, @PathVariable("type") String type,
                                        @PathVariable("appid") String appid, @PathVariable("appSecret") String appSecret,
                                        @PathVariable("url") String url, @PathVariable("yuming") String yuming) {
        url = url + yuming;
        String menu = createMenuService.createMenu(type, code, appid, appSecret, url);
        logprint.info("生成菜单====================" + menu);
        return menu;
    }
}
