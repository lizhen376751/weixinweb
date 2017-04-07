package com.dudu.weixin.control;

import com.dudu.weixin.mould.AccessToken;
import com.dudu.weixin.mould.Button;
import com.dudu.weixin.mould.CommonButton;
import com.dudu.weixin.mould.ComplexButton;
import com.dudu.weixin.mould.Menu;
import com.dudu.weixin.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * �˵���������
 */
public class MenuManager {
    public static final String shopcode = "FL000";
    public static final String appid = "wxd4e76e01e4a6e3b7";
    public static final String appSecret = "dd1e044b9208d43a5a31238e5ee053c7";
    public static final String url = "wx.elubon.com";
    public static final String lujing = "weixincore";
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        System.out.println("菜单创建开始");
        // 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appid, appSecret);
        //System.out.println(at.getToken());
        if (null != at) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果
            if (0 == result) {
                log.info("菜单创建成功！");
                System.out.println("菜单创建成功");
            } else {
                log.info("菜单创建失败，错误码：" + result);
                System.out.println("菜单创建失败" + result);
            }
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */

    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("联盟卡包");
        btn11.setType("view");
        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_lmkInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn12 = new CommonButton();
        btn12.setName("AHI指数");
        btn12.setType("view");
        btn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_AHIInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn13 = new CommonButton();
        btn13.setName("车辆保养");
        btn13.setType("view");
        btn13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_baoYangList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn14 = new CommonButton();
        btn14.setName("消费记录");
        btn14.setType("view");
        btn14.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_xiaoFeiList" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn21 = new CommonButton();
        btn21.setName("联盟介绍");
        btn21.setType("view");
        btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_lianMengJieShao" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn22 = new CommonButton();
        btn22.setName("服务导航");
        btn22.setType("view");
        btn22.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_daoHang" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn31 = new CommonButton();
        btn31.setName("养车信息");
        btn31.setType("view");
        btn31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_YCInfo" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");

        CommonButton btn32 = new CommonButton();
        btn32.setName("联盟活动");
        btn32.setType("view");
        btn32.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_lianMengActivity" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        CommonButton btn33 = new CommonButton();
        btn33.setName("退出账号");
        btn33.setType("view");
        btn33.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=http%3A%2F%2F" + url + "%2F" + lujing + "%2FoauthLoginServlet?shopcode=" + shopcode + "_logout" + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect");


        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("车管家");
        mainBtn1.setSub_button(new CommonButton[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("联盟");
        mainBtn2.setSub_button(new CommonButton[]{btn21, btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("活动");
        mainBtn3.setSub_button(new CommonButton[]{btn31, btn32, btn33});

        /**
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}