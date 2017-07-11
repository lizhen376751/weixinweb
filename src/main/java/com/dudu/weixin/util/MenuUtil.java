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
     * 店管家微信菜单
     */
    private Menu shopMenu;
    /**
     * 易璐邦菜单微信
     */
    private Menu yiLuBangmenu;
    /**
     * 创客微信
     */
    private Menu chuangKe;

    /**
     * MenuUtil(根据不同的公众号配置不同的菜单) 字符串形式
     *
     * @return MenuUtil(根据不同的公众号配置不同的菜单)字符串
     */
    @Override
    public String toString() {
        return "lianMengMenu:" + lianMengMenu + ",shopMenu:" + shopMenu + ",yiLuBangmenu:" + yiLuBangmenu;
    }


    /**
     * 通用联盟微信
     *
     * @param appid 微信的appid
     * @param code  联盟编码或者店铺编码
     * @param url   服务器地址
     * @return lianMengMenu 联盟微信的菜单
     */
    public Menu getLianMengMenu(String appid, String code, String url) {
//        wx.pre.duduchewang.cn 预生产
        String commonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + appid + "&redirect_uri=http://" + url + "/oauthLoginServlet?lmcode=" + code;

        CommonButton btn11 = new CommonButton();
        btn11.setName("我的卡包");
        btn11.setType("view");
        btn11.setUrl(commonUrl + "_lmkInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("健康指数");
        btn12.setType("view");
        btn12.setUrl(commonUrl + "_AHIInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn13 = new CommonButton();
        btn13.setName("车险投保");
        btn13.setType("view");
        btn13.setUrl(commonUrl + "_cheXianTouBao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn14 = new CommonButton();
        btn14.setName("个人中心");
        btn14.setType("view");
        btn14.setUrl(commonUrl + "_personalCenter" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn15 = new CommonButton();
        btn15.setName("退出账号");
        btn15.setType("view");
        btn15.setUrl(commonUrl + "_logout" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn21 = new CommonButton();
        btn21.setName("服务导航");
        btn21.setType("view");
        btn21.setUrl(commonUrl + "_fuwudaohang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn22 = new CommonButton();
        btn22.setName("一键洗车");
        btn22.setType("view");
        btn22.setUrl(commonUrl + "_fuwudaohangXC" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn23 = new CommonButton();
        btn23.setName("一键油漆");
        btn23.setType("view");
        btn23.setUrl(commonUrl + "_fuwudaohangYQ" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn24 = new CommonButton();
        btn24.setName("高速路况");
        btn24.setType("view");
        btn24.setUrl("https://gst.u-road.com/gst_wechat/HighSpeedTraffic/expresswayGlance?wxid=gh_f8c30bae42ad");

        CommonButton btn25 = new CommonButton();
        btn25.setName("附近路况");
        btn25.setType("view");
        btn25.setUrl("https://gst.u-road.com/gst_wechat/HighSpeedTraffic/trafficOfNear?wxid=gh_f8c30bae42ad");

        CommonButton btn31 = new CommonButton();
        btn31.setName("养车百科");
        btn31.setType("view");
        btn31.setUrl(commonUrl + "_YCInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn32 = new CommonButton();
        btn32.setName("优惠促销");
        btn32.setType("view");
        btn32.setUrl(commonUrl + "_lianMengActivity" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn33 = new CommonButton();
        btn33.setName("联盟介绍");
        btn33.setType("view");
        btn33.setUrl(commonUrl + "_lianMengJieShao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn34 = new CommonButton();
        btn34.setName("添加卡券");
        btn34.setType("view");
        btn34.setUrl(commonUrl + "_lianMengCardActivate" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn13, btn14, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("服务导航");
        mainBtn2.setSubbutton(new CommonButton[]{btn21, btn22, btn23, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("优惠促销");
        mainBtn3.setSubbutton(new CommonButton[]{btn31, btn32, btn33, btn34});

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
     * 通用店管家微信菜单
     *
     * @param appid 微信的appid
     * @param code  联盟编码或者店铺编码
     * @param url   服务器地址
     * @return geRenCeshiMenu 店管家微信菜单
     */
    public Menu getShopMenu(String appid, String code, String url) {
        String commonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + appid + "&redirect_uri=http://" + url + "/shopweixinMenuServlet?shopcode=" + code;

        CommonButton btn11 = new CommonButton();
        btn11.setName("消费记录");
        btn11.setType("view");
        btn11.setUrl(commonUrl + "_xiaofeijilu" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("健康指数");
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

        CommonButton btn21 = new CommonButton();
        btn21.setName("高速路况");
        btn21.setType("view");
        btn21.setUrl("https://gst.u-road.com/gst_wechat/HighSpeedTraffic/expresswayGlance?wxid=gh_f8c30bae42ad");

        CommonButton btn22 = new CommonButton();
        btn22.setName("违章查询");
        btn22.setType("view");
        btn22.setUrl("http://sd.122.gov.cn/views/inquiry.html?q=j");

        CommonButton btn23 = new CommonButton();
        btn23.setName("附近路况");
        btn23.setType("view");
        btn23.setUrl("https://gst.u-road.com/gst_wechat/HighSpeedTraffic/trafficOfNear?wxid=gh_f8c30bae42ad");

        //高速路况        https://gst.u-road.com/gst_wechat/HighSpeedTraffic/expresswayGlance?wxid=gh_f8c30bae42ad
        //违章查询   http://sd.122.gov.cn/views/inquiry.html?q=j
        //附近路况  https://gst.u-road.com/gst_wechat/HighSpeedTraffic/trafficOfNear?wxid=gh_f8c30bae42ad

        CommonButton btn31 = new CommonButton();
        btn31.setName("微信商城");
        btn31.setType("view");
        btn31.setUrl(commonUrl + "_weixinshangcheng" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn32 = new CommonButton();
        btn32.setName("我的订单");
        btn32.setType("view");
        btn32.setUrl(commonUrl + "_myOrder" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn33 = new CommonButton();
        btn33.setName("最新活动");
        btn33.setType("view");
        btn33.setUrl("http://www.duduchewang.com/weixincore/huoDongInfo.jsp?shopcode=" + code);


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("路况查询");
        mainBtn2.setSubbutton(new CommonButton[]{btn21, btn22, btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("微信商城");
        mainBtn3.setSubbutton(new CommonButton[]{btn31, btn32, btn33});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
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
                + "appid=wxd42683342ffb5ae9&redirect_uri=http://wx.pdu.duduchewang.cn/oauthLoginServlet?lmcode=FL000";

//        1、我的卡包  2、爱车健康指数 3、添加卡券 4、个人中心 5、退出账号

        CommonButton btn11 = new CommonButton();
        btn11.setName("我的卡包");
        btn11.setType("view");
        btn11.setUrl(commonUrl + "_lmkInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("健康指数");
        btn12.setType("view");
        btn12.setUrl(commonUrl + "_AHIInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn13 = new CommonButton();
        btn13.setName("添加卡券");
        btn13.setType("view");
        btn13.setUrl(commonUrl + "_lianMengCardActivate" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn14 = new CommonButton();
        btn14.setName("个人中心");
        btn14.setType("view");
        btn14.setUrl(commonUrl + "_personalCenter" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn15 = new CommonButton();
        btn15.setName("退出账号");
        btn15.setType("view");
        btn15.setUrl(commonUrl + "_logout" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

//        1、在线客服  2、服务导航  3、一键洗车 4、一键车漆  5、一键救援

        CommonButton btn21 = new CommonButton();
        btn21.setName("在线客服");
        btn21.setType("view");
        btn21.setUrl("http://kefu6.kuaishang.cn/bs/im.htm?cas=56463___619761&fi=58696&ism=1");

        CommonButton btn22 = new CommonButton();
        btn22.setName("服务导航");
        btn22.setType("view");
        btn22.setUrl(commonUrl + "_fuwudaohang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn23 = new CommonButton();
        btn23.setName("一键洗车");
        btn23.setType("view");
        btn23.setUrl(commonUrl + "_fuwudaohangXC" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn24 = new CommonButton();
        btn24.setName("一键油漆");
        btn24.setType("view");
        btn24.setUrl(commonUrl + "_fuwudaohangYQ" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn25 = new CommonButton();
        btn25.setName("一键救援");
        btn25.setType("view");
        btn25.setUrl("http://wx-c.auto-sos.net/?sos_cid=791&sos_sig=5rHgU1");

//        1、养车百科  2、优惠促销  3、老工通知 4、老工商城   5、老工分佣

        CommonButton btn31 = new CommonButton();
        btn31.setName("养车百科");
        btn31.setType("view");
        btn31.setUrl(commonUrl + "_YCInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn32 = new CommonButton();
        btn32.setName("优惠促销");
        btn32.setType("view");
        btn32.setUrl(commonUrl + "_lianMengActivity" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn33 = new CommonButton();
        btn33.setName("老工通知");
        btn33.setType("view");
        btn33.setUrl(commonUrl + "_YCInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn34 = new CommonButton();
        btn34.setName("老工商城");
        btn34.setType("view");
        btn34.setUrl("http://301.jnfpsm.com/api/unc2mdcbpb/shop");

        CommonButton btn35 = new CommonButton();
        btn35.setName("老工分佣");
        btn35.setType("view");
        btn35.setUrl("http://301.jnfpsm.com/api/unc2mdcbpb/shop/distribute/join/?wxref=mp.weixin.qq.com");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn13, btn14, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("服务");
        mainBtn2.setSubbutton(new CommonButton[]{btn21, btn22, btn23, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("老工养车");
        mainBtn3.setSubbutton(new CommonButton[]{btn31, btn32, btn34, btn35});
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

    /**
     * 创客微信菜单
     *
     * @param appid 微信的appid
     * @param code  联盟编码或者店铺编码
     * @param url   服务器地址
     * @return lianMengMenu 创客微信的菜单
     */
    public Menu getChuangKe(String appid, String code, String url) {
//        wx.pre.duduchewang.cn 预生产
        String commonUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + appid + "&redirect_uri=http://" + url + "/chuangkeweixinMenu?lmcode=" + code;

        CommonButton btn11 = new CommonButton();
        btn11.setName("收付明细");
        btn11.setType("view");
        btn11.setUrl(commonUrl + "_paymentDetails" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("门店签收");
        btn12.setType("view");
        btn12.setUrl(commonUrl + "_storeSign" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn13 = new CommonButton();
        btn13.setName("发货记录");
        btn13.setType("view");
        btn13.setUrl(commonUrl + "_deliveryRecord" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn14 = new CommonButton();
        btn14.setName("我要进货");
        btn14.setType("view");
        btn14.setUrl(commonUrl + "_buyMore" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn21 = new CommonButton();
        btn21.setName("微课堂");
        btn21.setType("view");
        btn21.setUrl(commonUrl + "_microClass" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn22 = new CommonButton();
        btn22.setName("微营销");
        btn22.setType("view");
        btn22.setUrl(commonUrl + "_microMarketing" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn23 = new CommonButton();
        btn23.setName("创客圈");
        btn23.setType("view");
        btn23.setUrl(commonUrl + "_microVideo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn24 = new CommonButton();
        btn24.setName("拉关系");
        btn24.setType("view");
        btn24.setUrl(commonUrl + "_pullTies" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn25 = new CommonButton();
        btn25.setName("抢地盘");
        btn25.setType("view");
        btn25.setUrl(commonUrl + "_turfUp" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn31 = new CommonButton();
        btn31.setName("信息榜");
        btn31.setType("view");
        btn31.setUrl(commonUrl + "_informationList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn32 = new CommonButton();
        btn32.setName("抢任务");
        btn32.setType("view");
        btn32.setUrl(commonUrl + "_grabAMission" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn33 = new CommonButton();
        btn33.setName("我的礼品");
        btn33.setType("view");
        btn33.setUrl(commonUrl + "_myEquipment" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn34 = new CommonButton();
        btn34.setName("我的地盘");
        btn34.setType("view");
        btn34.setUrl(commonUrl + "_myTerritory" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn35 = new CommonButton();
        btn35.setName("个人中心");
        btn35.setType("view");
        btn35.setUrl(commonUrl + "_personCenter" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("商务中心");
        mainBtn1.setSubbutton(new CommonButton[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("服务中心");
        mainBtn2.setSubbutton(new CommonButton[]{btn21, btn22, btn23, btn24, btn25});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("创客中心");
        mainBtn3.setSubbutton(new CommonButton[]{btn31, btn32, btn33, btn34, btn35});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }

    /**
     * 设置 创客微信的菜单
     *
     * @param chuangKe 联盟微信的菜单
     * @return 返回 MenuUtil(根据不同的公众号配置不同的菜单)
     */
    public MenuUtil setChuangKe(Menu chuangKe) {
        this.chuangKe = chuangKe;
        return this;
    }


}
