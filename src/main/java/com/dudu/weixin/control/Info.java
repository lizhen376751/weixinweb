package com.dudu.weixin.control;

import com.dudu.weixin.service.LianmengIntroducedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/8/29.
 */

@Controller
@RequestMapping(value = "/")
public class Info {
    
    @Autowired
    private LianmengIntroducedService lianmengIntroducedService;

//    @RequestMapping(value = "returnString", method = RequestMethod.GET)
//    public String returnString() {
//        return "/lianMengKa/lianMengCard/homePage";
//    }
//
//    @RequestMapping(value = "success", produces = {"text/plain;charset=UTF-8"})
//    public String hello() {
//        System.out.println("==================您还!");
//        return "hello";
//    }

    //    @RequestMapping(value = "returnSuccess" )
//    public String returnSuccess() {
//        return  "redirect:/aa";
//        //return "redirect:/cc";//转发到内部controller
//        //return  "forward:/success";//转发到Controller,不能用
//        //return "hello";//跳转内部的静态资源.
//        // return  "redirect:https://www.hao123.com/?tn=97977680_hao_pg";//重定向到外部资源
//    }
    //带参数的
    @RequestMapping(value = "cc")
    public String cc(Model data) {
        data.addAttribute("name", "冯光祥,您好!");
        System.out.println("==================cc!");
        return "redirect:/aa";
    }

    // @ResponseBody//一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，
    // 加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。
    // 比如异步获取json数据，加上@responsebody后，会直接返回json数据。
    @RequestMapping(value = "hao12")
    public String aa(Model model) {
        model.addAttribute("name", "冯光祥,您好!");
        System.out.println("==================aa!");
        return "/hello";
    }

    //测试使用===================================================
    //联盟介绍
    @RequestMapping(value = "success1", method = RequestMethod.GET)
    public String introduced1() {
        return "/lianMengIntroduced/jsp/getIntroduced";
    }

//    @ResponseBody
//    @RequestMapping(value = "getCommonAjax", method = RequestMethod.POST)
//    public Introduced introduced2() {
//        String shopCode = "CS000";
//        return lianmengIntroducedService.queryEntry(shopCode);
//    }

    //联盟卡包=====================================================
    @RequestMapping(value = "success2", method = RequestMethod.GET)
    public String lmkInfo() {
        return "/hello";
    }

//    @ResponseBody
//    @RequestMapping(value = "queryLmkInfoList", method = RequestMethod.POST)
//    public String queryLmkInfoList() {
//     return "uuuu";
//    }

}