package com.dudu.weixin.control;

import com.alibaba.fastjson.JSONObject;
import com.dudu.soa.baoxian.kaidan.module.BaoXianList;
import com.dudu.soa.baoxian.kaidan.module.BaoXianParamList;
import com.dudu.soa.framework.commons.oauth.module.DuduToken;
import com.dudu.soa.framework.oauth.DuduOauthService;
import com.dudu.weixin.service.ChexiantoubaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 车险投保的先关页面跳转
 * Created by lizhen on 2017/7/4.
 */
@Controller
public class BaoxianController {
    /**
     * 引入token服务
     */
    @Resource
    private DuduOauthService duduOauthService;
    /**
     * 车险投保
     */
    @Autowired
    private ChexiantoubaoService chexiantoubaoService;

    /**
     * 车险投保的在线交流小图标
     *
     * @return 字符串路径
     */
    @RequestMapping(value = "/cheXianOnline", method = RequestMethod.GET)
    public String cheXianOnline() {
        return "redirect:http://kefu6.kuaishang.cn/bs/im.htm?cas=56463___619761&fi=58696&from=9"; //车险投保易璐邦的在线咨询

    }


    /**
     * 仅用于app端页面车险投保
     *
     * @param request  请求
     * @param model    绑定数据
     * @param tokenStr token字符串
     * @return 页面跳转至车险投保页面
     */
    @RequestMapping(value = "/cheXianTouBao", method = RequestMethod.GET)
    public String cheXianTouBao(HttpServletRequest request, Model model, @RequestHeader(value = "token", required = false) String tokenStr) {
        //如果不为空,传进来的就是token,如果为空的话就是写死的token
        tokenStr = tokenStr != null ? tokenStr : "AQAAAITdno+PtJqG3cXdzt3T3ZyNmp6LmquWkprdxc/T3ZqHj42WjJqrlpKa3cXP092SnpaRrJeQj7yQm5rdxd3PyszMz8/"
                + "O3dPdkZaclLGekprdxd0ZYnEZSlYYa2Dd092NkJOatpvdxc3O092Ml5CPvJCbmt3F3c/KzMzPz87d092KjJqNs5Cem7aRtpvdxd3PyszMz8/"
                + "Oz87d092Jmo2MlpCR3cXOgg==";
        DuduToken token = duduOauthService.getDuduToken(tokenStr);
        String mineShopCode = token.getMainShopCode();
        request.setAttribute("mineShopCode", mineShopCode);
        model.addAttribute("mineShopCode", mineShopCode);
        return "/baoxian/cheXianTouBao/cheXianTouBao.jsp"; //车险投保
    }


    /**
     * 提交保险
     *
     * @param request 获取参数
     * @return String
     * @throws ParseException 异常抛出
     */
    @ResponseBody
    @RequestMapping(value = "baoxiantijiao", method = RequestMethod.POST)
    public String baoXianTiJiao(HttpServletRequest request) throws ParseException {
        Integer integer = chexiantoubaoService.baoXianTiJiao(request);
        if (integer > 0) {
            return JSONObject.toJSONString("1");
        } else {
            return JSONObject.toJSONString("0");
        }

    }


    /**
     * @param request 请求
     * @param model   绑定参数
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "queryBaoXian", method = RequestMethod.GET)
    public String queryInsurance(HttpServletRequest request, Model model) {
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        String lmcode = (String) request.getSession().getAttribute("lmcode");
        String plateNumber = (String) request.getSession().getAttribute("plateNumber");
        if (null != lmcode && !"".equals(lmcode)) {
            baoXianParamList.setShopcodelm(lmcode);
        }
        if (null != plateNumber && !"".equals(plateNumber)) {
            baoXianParamList.setCarId(plateNumber);
        }
        List<BaoXianList> baoXianLists = chexiantoubaoService.queryInsurance(baoXianParamList);
        model.addAttribute("list", baoXianLists);
        return "/cheXianList/cheXianList.jsp"; //展示车险列表的页面
    }

    /**
     * 仅用于app端保险列表页面的按钮进入
     *
     * @param request  请求
     * @param model    绑定数据
     * @param tokenStr token字符串
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "/queryInsurance", method = RequestMethod.GET)
    public String queryCarInsurance(HttpServletRequest request, Model model, @RequestHeader(value = "token", required = false) String tokenStr) {
        //如果不为空,传进来的就是token,如果为空的话就是写死的token
        tokenStr = tokenStr != null ? tokenStr : "AQAAAITdno+PtJqG3cXdzt3T3ZyNmp6LmquWkprdxc/T3ZqHj42WjJqrlpKa3cXP092SnpaRrJeQj7yQm5rdxd3PyszMz8/"
                + "O3dPdkZaclLGekprdxd0ZYnEZSlYYa2Dd092NkJOatpvdxc3O092Ml5CPvJCbmt3F3c/KzMzPz87d092KjJqNs5Cem7aRtpvdxd3PyszMz8/"
                + "Oz87d092Jmo2MlpCR3cXOgg==";
        DuduToken token = duduOauthService.getDuduToken(tokenStr);
        String shopCode = token.getShopCode();
        model.addAttribute("shopCode", shopCode);
        //++++++++++++++++++++++++++++++++++++++++
        BaoXianParamList baoXianParamList = new BaoXianParamList();
        if (null != shopCode && !"".equals(shopCode)) {
            List<String> list = new ArrayList<>();
            list.add(shopCode);
            baoXianParamList.setShopCode(list);
        }
        List<BaoXianList> baoXianLists = chexiantoubaoService.queryInsurance(baoXianParamList);
        model.addAttribute("list", baoXianLists);
        return "/cheXianList/cheXianList.jsp"; //展示车险列表的页面

    }

    /**
     * 仅用于app端保险提交之后跳转至列表页面
     *
     * @param request 请求
     * @param model   返回数据
     * @return 页面跳转至车险列表展示页面
     */
    @RequestMapping(value = "/appbaoxianlist", method = RequestMethod.GET)
    public String appbaoxianlist(HttpServletRequest request, Model model) {
        String mineShopCode = request.getParameter("mineShopCode");
        model.addAttribute("shopCode", mineShopCode);
        return "/cheXianList/cheXianList.jsp"; //展示车险列表的页面

    }

    /**
     * 跳转至保险详情页面
     *
     * @param request 请求
     * @param model   返回数据
     * @return 保险详情页面
     */
    @RequestMapping(value = "baoXianDetails")
    public String toInsuranceDetails(HttpServletRequest request, Model model) {
        String companyid = request.getParameter("companyid");
        if (companyid != null) {
            Integer companyId = Integer.parseInt(companyid);
            model.addAttribute("companyId", companyId);
        }
        String shopcodelm = request.getParameter("shopcodelm");
        if (shopcodelm != null && !shopcodelm.equals("")) {
            model.addAttribute("shopCodeLm", shopcodelm);
        }
        String orderNumb = request.getParameter("orderNumb");
        if (orderNumb != null && !orderNumb.equals("")) {
            model.addAttribute("orderNumb", orderNumb);
        }
        String shopCode = request.getParameter("shopCode");
        if (shopCode != null && !shopCode.equals("")) {
            model.addAttribute("shopCode", shopCode);
        }
        String carId = request.getParameter("carId");
        if (carId != null && !carId.equals("")) {
            model.addAttribute("plateNumber", carId);
        }
        return "/baoxianDetail/cheXianDetail.jsp"; //车险详情展示页面
    }
}
