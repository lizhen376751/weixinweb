package com.dudu.weixin.control;

/**
 * Created by Administrator on 2017/3/15.
 * 原servlet的OAuthLoginServlet
 */
//@Controller
//@RequestMapping(value = "/")
public class OauthLoginControl {
//    @Autowired
//    private  HttpSession httpSession;
//    @Autowired
//    private ShopInfoService shopInfoService;
//    @Autowired
//    private Constant constant;
//
//    @RequestMapping(value = "oauthLoginServlet",method = RequestMethod.GET)
//    public String doGet(@RequestParam(name = "code") String code,
//                      @RequestParam(name = "shopcode") String shopcode) {
//         // 用户同意授权后，能获取到code
//       // 用户同意授权
//        String ID="";
//
//        String flagStr = shopcode.split("_")[1];
//        shopcode=shopcode.split("_")[0];
//
//        String WXAppId = shopInfoService.getShopAppid(shopcode);
//        String WXAppSecret = shopInfoService.getShopAppSecret(shopcode);
//
//        // 获取网页授权access_token
//        WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(WXAppId, WXAppSecret, code);
//        String openId = weixinOauth2Token.getOpenId();
//
//        httpSession.setAttribute("DUDUCHEWANG_OpenId",openId);
//        httpSession.setAttribute("DUDUCHEWANG_shopcode", shopcode);
//
//        String urlStr=constant.getWeixinBaseUrl();
//        String luJingStr=constant.getCommon_LuJing();
//        if("FL000".equals(shopcode)){
//            urlStr=constant.getELB_URL();
//            luJingStr=constant.getELB_LuJing();
//        }
//
//
//        //判断点击菜单，进入不同页面
//        if("lmkInfo".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/lianMengKa/lianMengCard/homePage.jsp";
//        }else if("daoHang".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/daoHang/service/daohangindex.jsp?shopcode="+shopcode+"&openid="+openId+'"';
//        }else if("logout".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/logout.jsp";
//        }else if("AHIInfo".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/ahi/AHIxiangqing.jsp";
//
//        }else if("xiaoFeiList".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/xiaoFeiJiLu/xiaoFeiList.jsp";
//
//        }else if("baoYangList".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/baoYangTiXing/baoYangList.jsp";
//        }
//        else if("lianMengJieShao".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/lianMengIntroduced/jsp/getIntroduced.jsp";
//
//        }
//        else if("YCInfo".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/yangCheInfo/jsp/yangCheXinXi.jsp";
//
//        }else if("lianMengActivity".equals(flagStr)){
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/lianMengActivity/jsp/lianMengActivity.jsp";
//
//        }
//        else{
//            return "redirect:http://"+urlStr+"/"+luJingStr+"/lianMengKa/lianMengCard/homePage.jsp";
//        }
//
//
//    }
}
