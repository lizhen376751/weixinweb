package com.dudu.weixin.util;

import com.dudu.soa.weixindubbo.weixin.http.module.menu.Button;
import com.dudu.soa.weixindubbo.weixin.http.module.menu.CommonButton;
import com.dudu.soa.weixindubbo.weixin.http.module.menu.ComplexButton;
import com.dudu.soa.weixindubbo.weixin.http.module.menu.Menu;

/**
 * 根据不同的公众号配置不同的菜单
 * Created by lizhen on 2017/5/2.
 */

public class MenuUtil {
    /**
     * 联盟微信的菜单
     */
    private Menu lianMengMenu;
    /**
     * 个人测试的菜单
     */
    private Menu geRenCeshiMenu;
    /**
     * 店管家微信菜单
     */
    private Menu shopMenu;
    /**
     * 易璐邦菜单微信
     */
    private Menu yiLuBangmenu;

    /**
     * MenuUtil(根据不同的公众号配置不同的菜单) 字符串形式
     *
     * @return MenuUtil(根据不同的公众号配置不同的菜单)字符串
     */
    @Override
    public String toString() {
        return "lianMengMenu:" + lianMengMenu + ",geRenCeshiMenu:" + geRenCeshiMenu + ",shopMenu:" + shopMenu + ",yiLuBangmenu:" + yiLuBangmenu;
    }

    /**
     * @param appid 微信的appid
     * @param code  联盟编码或者店铺编码
     * @return lianMengMenu 联盟微信的菜单
     */
    public Menu getLianMengMenu(String appid, String code) {
        String commonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + appid + "&redirect_uri=http://wx.pre.duduchewang.cn/oauthLoginServlet?lmcode=" + code;
        CommonButton btn11 = new CommonButton();
        btn11.setName("联盟卡包");
        btn11.setType("view");
        btn11.setUrl(commonUrl + "_lmkInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("AHI指数");
        btn12.setType("view");
        btn12.setUrl(commonUrl + "_AHIInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

//        CommonButton btn13 = new CommonButton();
//        btn13.setName("车辆保养");
//        btn13.setType("view");
//        btn13.setUrl(Constant.commonUrl + "_baoYangList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//
//        CommonButton btn14 = new CommonButton();
//        btn14.setName("消费记录");
//        btn14.setType("view");
//        btn14.setUrl(Constant.commonUrl + "_xiaoFeiList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn15 = new CommonButton();
        btn15.setName("个人中心");
        btn15.setType("view");
        btn15.setUrl(commonUrl + "_personalCenter" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn21 = new CommonButton();
        btn21.setName("联盟介绍");
        btn21.setType("view");
        btn21.setUrl(commonUrl + "_lianMengJieShao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//
//        CommonButton btn22 = new CommonButton();
//        btn22.setName("服务导航");
//        btn22.setType("view");
//        btn22.setUrl(Constant.commonUrl + "_daoHang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//
//        CommonButton btn23 = new CommonButton();
//        btn23.setName("我的预约");
//        btn23.setType("view");
//        btn23.setUrl(Constant.commonUrl + "_daoHang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn24 = new CommonButton();
        btn24.setName("车险投保");
        btn24.setType("view");
        btn24.setUrl(commonUrl + "_cheXianTouBao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn31 = new CommonButton();
        btn31.setName("养车百科");
        btn31.setType("view");
        btn31.setUrl(commonUrl + "_YCInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn32 = new CommonButton();
        btn32.setName("优惠促销");
        btn32.setType("view");
        btn32.setUrl(commonUrl + "_lianMengActivity" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

//        CommonButton btn33 = new CommonButton();
//        btn33.setName("车友会");
//        btn33.setType("view");
//        btn33.setUrl(Constant.commonUrl + "_lianMengActivity" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//

        CommonButton btn34 = new CommonButton();
        btn34.setName("退出账号");
        btn34.setType("view");
        btn34.setUrl(commonUrl + "_logout" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("服务");
        mainBtn2.setSubbutton(new CommonButton[]{btn21, btn24});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("活动");
        mainBtn3.setSubbutton(new CommonButton[]{btn31, btn32, btn34});
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }

    /**
     * 设置 联盟微信的菜单
     *
     * @param lianMengMenu 联盟微信的菜单
     * @return 返回 MenuUtil(根据不同的公众号配置不同的菜单)
     */
    public MenuUtil setLianMengMenu(Menu lianMengMenu) {
        this.lianMengMenu = lianMengMenu;
        return this;
    }

    /**
     * @param appid 微信的appid
     * @param code  联盟编码或者店铺编码
     * @return geRenCeshiMenu 个人测试的菜单
     */
    public Menu getGeRenCeshiMenu(String appid, String code) {
        String commonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + appid + "&redirect_uri=http://lizhen12.tunnel.2bdata.com/shopweixinMenuServlet?shopcode=" + code;

        CommonButton btn11 = new CommonButton();
        btn11.setName("消费记录");
        btn11.setType("view");
        btn11.setUrl(commonUrl + "_xiaofeijilu" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("AHI指数");
        btn12.setType("view");
        btn12.setUrl(commonUrl + "_AHIInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn13 = new CommonButton();
        btn13.setName("保养提醒");
        btn13.setType("view");
        btn13.setUrl(commonUrl + "_BaoYangTiXing" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn14 = new CommonButton();
        btn14.setName("个人中心");
        btn14.setType("view");
        btn14.setUrl(commonUrl + "_shoppersoncenter" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn15 = new CommonButton();
        btn15.setName("下拉加载");
        btn15.setType("view");
        btn15.setUrl(commonUrl + "_xialajiazai" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn21 = new CommonButton();
        btn21.setName("服务导航");
        btn21.setType("view");
        btn21.setUrl(commonUrl + "_fuwudaohang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn13, btn14, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("服务导航");
        mainBtn2.setSubbutton(new CommonButton[]{btn21});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2});
        return menu;
    }

    /**
     * 设置 个人测试的菜单
     *
     * @param geRenCeshiMenu 个人测试的菜单
     * @return 返回 MenuUtil(根据不同的公众号配置不同的菜单)
     */
    public MenuUtil setGeRenCeshiMenu(Menu geRenCeshiMenu) {
        this.geRenCeshiMenu = geRenCeshiMenu;
        return this;
    }

    /**
     * @param appid 微信的appid
     * @param code  联盟编码或者店铺编码
     * @return geRenCeshiMenu 店管家微信菜单
     */
    public Menu getShopMenu(String appid, String code) {
        return this.shopMenu;
    }

    /**
     * 设置 店管家微信菜单
     *
     * @param shopMenu 店管家微信菜单
     * @return 返回 MenuUtil(根据不同的公众号配置不同的菜单)
     */
    public MenuUtil setShopMenu(Menu shopMenu) {
        this.shopMenu = shopMenu;
        return this;
    }


    /**
     * 获取 易璐邦菜单微信
     *
     * @param appid 微信的appid
     * @param code  联盟编码或者店铺编码
     * @return yiLuBangmenu 易璐邦菜单微信
     */
    public Menu getYiLuBangmenu(String appid, String code) {
        String commonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=wx91ee3b29615c49c7&redirect_uri=http://wx.pre.duduchewang.cn/oauthLoginServlet?lmcode=FL000";

        CommonButton btn11 = new CommonButton();
        btn11.setName("联盟卡包");
        btn11.setType("view");
        btn11.setUrl(commonUrl + "_lmkInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("AHI指数");
        btn12.setType("view");
        btn12.setUrl(commonUrl + "_AHIInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

//        CommonButton btn13 = new CommonButton();
//        btn13.setName("车辆保养");
//        btn13.setType("view");
//        btn13.setUrl(Constant.commonUrl + "_baoYangList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//
//        CommonButton btn14 = new CommonButton();
//        btn14.setName("消费记录");
//        btn14.setType("view");
//        btn14.setUrl(Constant.commonUrl + "_xiaoFeiList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
        CommonButton btn16 = new CommonButton();
        btn16.setName("服务客服");
        btn16.setType("view");
        btn16.setUrl("http://kefu6.kuaishang.cn/bs/im.htm?cas=56463___619761&fi=58696&ism=1");

        CommonButton btn15 = new CommonButton();
        btn15.setName("个人中心");
        btn15.setType("view");
        btn15.setUrl(commonUrl + "_personalCenter" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn21 = new CommonButton();
        btn21.setName("联盟介绍");
        btn21.setType("view");
        btn21.setUrl(commonUrl + "_lianMengJieShao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//
//        CommonButton btn22 = new CommonButton();
//        btn22.setName("服务导航");
//        btn22.setType("view");
//        btn22.setUrl(Constant.commonUrl + "_daoHang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//
//        CommonButton btn23 = new CommonButton();
//        btn23.setName("我的预约");
//        btn23.setType("view");
//        btn23.setUrl(Constant.commonUrl + "_daoHang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn24 = new CommonButton();
        btn24.setName("车险投保");
        btn24.setType("view");
        btn24.setUrl(commonUrl + "_cheXianTouBao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn25 = new CommonButton();
        btn25.setName("服务导航");
        btn25.setType("view");
        btn25.setUrl("http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?shopcode=FL000"
                + "&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4");

        CommonButton btn26 = new CommonButton();
        btn26.setName("一键洗车");
        btn26.setType("view");
        btn26.setUrl("http://wx.duduchewang.cn/weixincore/daoHang/service/daohangindex.jsp?shopcode=FL000"
                + "&shopType_appoint=xc&openid=oSsYXwMun4NrZE8b_OQi6kMaPyg4");
        CommonButton btn27 = new CommonButton();
        btn27.setName("一键救援");
        btn27.setType("view");
        btn27.setUrl("http://wx-c.auto-sos.net/?sos_cid=791&sos_sig=5rHgU1");

        CommonButton btn31 = new CommonButton();
        btn31.setName("养车百科");
        btn31.setType("view");
        btn31.setUrl(commonUrl + "_YCInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn32 = new CommonButton();
        btn32.setName("优惠促销");
        btn32.setType("view");
        btn32.setUrl(commonUrl + "_lianMengActivity" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


//        CommonButton btn35 = new CommonButton();
//        btn35.setName("服务导航");
//        btn35.setType("view");
//        btn35.setUrl("http://4ec43db5.ngrok.io/fuwudaohang");

        CommonButton btn34 = new CommonButton();
        btn34.setName("退出账号");
        btn34.setType("view");
        btn34.setUrl(commonUrl + "_logout" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn16, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("服务");
        mainBtn2.setSubbutton(new CommonButton[]{btn21, btn25, btn26, btn27});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("活动");
        mainBtn3.setSubbutton(new CommonButton[]{btn31, btn32, btn34});
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }

    /**
     * 设置 易璐邦菜单微信
     *
     * @param yiLuBangmenu 易璐邦菜单微信
     * @return 返回 MenuUtil(根据不同的公众号配置不同的菜单)
     */

    public MenuUtil setYiLuBangmenu(Menu yiLuBangmenu) {
        this.yiLuBangmenu = yiLuBangmenu;
        return this;
    }
}
