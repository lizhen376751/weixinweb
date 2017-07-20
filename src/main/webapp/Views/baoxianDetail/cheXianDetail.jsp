<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>投保详情</title>
    <link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/baoxianDetail/swiper-3.3.1.min.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/baoxianDetail/cheXianDetail.css"/>

    <script src="/scripts/baoxianDetail/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/baoxianDetail/swiper-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script  type="text/javascript"  src="/scripts/main.js"></script>
    <script src="/scripts/baoxianDetail/cheXianDetail.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<%--网页加载效果--%>
<div id="loading">
    <img src="/files/loading2.gif"  alt="loading.." />
</div>
<div class="wrap">
    <!-----------------------------------------------------------------------------------头部信息，包括是否报价-->
    <div class="header_main font_3">
        <ul>
            <!----------------------------------------------------------------------------保险公司-->
            <li>
                <span class="color_11">保险公司：</span>
                <span class="color_9 bxgs">人民保险</span>
                <span class="color_7 quote">报价成功</span>
            </li>
            <!----------------------------------------------------------------------------保费合计-->
            <li>
                <span class="color_11">保费合计：</span>
                <span class="color_12 bfhj">¥5083.4</span>
            </li>
            <!-----------------------------------------------------------------------------商业险生效日期-->
            <li>
                <span class="color_11">商业险生效日期：</span>
                <span class="color_9 syx"></span>
            </li>
            <!-----------------------------------------------------------------------------交强险生效日期-->
            <li>
                <span class="color_11">交强险生效日期：</span>
                <span class="color_9 jqx"></span>
            </li>
        </ul>
    </div>     <!----------------------------------------------------------------------------头部信息结束-->
    <!----------------------------------------------------------------------------------------基本信息和险种信息-->
    <div class="infor">
        <div class="infor_til font_5">
            <div class="basic color_7">基本信息</div>
            <div class="coverage color_9">险种信息</div>
            <img src="/files/baoxianDetail/partition_bg.png"/>
        </div>
        <!---------------------------------------------------------------------------------滑动部分-->
        <div class="swiper-container font_3">
            <div class="swiper-wrapper">
                <!-------------------------------------------------------------------------基本信息-->
                <div class="swiper-slide basic_infor">
                    <ul>
                        <!-----------------姓名-->
                        <li>
                            <span class="color_11">姓名：</span>
                            <span class="color_9 xm"></span>
                        </li>
                        <!-----------------车牌号-->
                        <li>
                            <span class="color_11">车牌号：</span>
                            <span class="color_9 cph"></span>
                        </li>
                        <!-----------------手机号码-->
                        <li>
                            <span class="color_11">手机号码：</span>
                            <span class="color_9 sjh"></span>
                        </li>
                        <!-----------------车辆识别码-->
                        <li>
                            <span class="color_11">车辆识别码：</span>
                            <span class="color_9 clsbdm"></span>
                        </li>
                        <!-----------------发动机型号-->
                        <li>
                            <span class="color_11">发动机号：</span>
                            <span class="color_9 fdjxh"></span>
                        </li>
                        <!-----------------使用性质-->
                        <li>
                            <span class="color_11">使用性质：</span>
                            <span class="color_9 syxz"></span>
                        </li>
                        <!-----------------注册日期-->
                        <li>
                            <span class="color_11">注册日期：</span>
                            <span class="color_9 zcrq"></span>
                        </li>

                    </ul>
                </div>
                <!-------------------------------------------------------------------------险种信息-->
                <div class="swiper-slide coverage_infor font_3" >
                    <ul style="margin: 0rem 0rem 1.23rem 0rem">
                        <!---------------------------------------------------------------表头-->
                        <li class="color_7">
                            <span class="bxlx">保险类型</span>
                            <div>
                                <span class="be">保额</span>
                                <span class="mp">免赔</span>
                                <span>保费</span>
                            </div>
                        </li>
                        <!--------------------------------------------------------------内容-->
                        <%--<li class="color_9">--%>
                            <%--<span class="xz">车辆损失险</span>--%>
                            <%--<div>--%>
                                <%--<span class="be">325万</span>--%>
                                <%--<span class="mp">是</span>--%>
                                <%--<span>325万</span>--%>
                            <%--</div>--%>
                        <%--</li>--%>

                        <%--<li class="color_9">--%>
                            <%--<span class="xz">第三者责任险</span>--%>
                            <%--<div>--%>
                                <%--<span class="be">325万</span>--%>
                                <%--<span class="mp">无</span>--%>
                                <%--<span>325万</span>--%>
                            <%--</div>--%>
                        <%--</li>--%>

                        <%--<li class="color_9">--%>
                            <%--<span class="xz">车上责任险（司机）</span>--%>
                            <%--<div>--%>
                                <%--<span class="be">325万</span>--%>
                                <%--<span class="mp">否</span>--%>
                                <%--<span>325万</span>--%>
                            <%--</div>--%>
                        <%--</li>--%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="tb_btn font_2">投保</div>
</div>
</body>
</html>

