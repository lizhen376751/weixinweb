<%@ page contentType="text/html; charset=GBK" language="java" %>

<%@ page language="java"
         import="com.dudu.weixin.service.AutoLoginService,com.dudu.weixin.service.BaoYangTiXingService,java.text.DecimalFormat,java.util.ArrayList" %>
<%@ page language="java" import="java.util.HashMap
								" %>
<%
    String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
    String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
    String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
    System.out.println("=============baoYangList.jsp=== CarId:|" + CarId + "|");
    //TODO 登录判断





    String top = (String) request.getParameter("top");
    if (top == null) {
        top = "1";
    }

//    BaoYangTiXingService baoYang = new BaoYangTiXingService();
//    ArrayList listDJ = baoYang.getBaoYangListByLmcodeAndCarNo(shopcode, CarId, top);
    ArrayList listDJ = null;
//    DecimalFormat form = new DecimalFormat("0.00");
%>
<html>
<head>
    <!-- <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" />  -->
    <meta charset="UTF-8">
    <title>保养提醒</title>
    <meta name="keywords" content="keyword ..."/>
    <meta name="Description" content="description ..."/>

    <link href="/styles/weix.css" rel="stylesheet" type="text/css"/>
    <link href="/styles/shopbaoyangtixing/baoyanglist.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/scripts/jquery-1.12.1.min.js"></script>
    <script type="text/javascript" src="/scripts/baoYangTiXing/iscroll.js"></script>
    <script type="text/javascript" src="/scripts/baoYangTiXing/baoYangList.js"></script>
    <script  type="text/javascript"  src="/scripts/main.js"></script>
</head>
<body>

<%--网页加载效果--%>
<div id="loading">
    <img src="/files/loading.gif"  alt="loading.." />
</div>
<div class="secretarylist1">
    <div id="wrapper" style="height:100%; overflow:auto; ">
        <div id="scroller">
            <div id="pullDown" style="width:100%;text-align:center;font-size:32px">
                <span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新...</span>
            </div>
<%--
            <%
                if (listDJ.size() > 5) { %>
            <ul id="thelist" class="margin_auto"><%
	}else{ %>--%>
                <ul id="thelist" class="margin_auto" style="height:100%;">
                    <%--<% }%>--%>


                    <%--<%--%>
                        <%--if (listDJ.size() > 0) {--%>
                            <%--for (int j = 0; j < listDJ.size(); j++) {--%>
                                <%--HashMap hmDJ = (HashMap) listDJ.get(j);--%>

                                <%--String CustomerDemand_DateBegin = (String) hmDJ.get("CustomerDemand_DateBegin");--%>
                                <%--String shopcodetr = (String) hmDJ.get("shopcode");--%>
<%--//                                String shopName = autoLogin.getDuduShopName(shopcodetr);--%>
                                <%--String shopName = "北京经典";--%>
                                <%--String CustomerDemand_Memo = (String) hmDJ.get("CustomerDemand_Memo");--%>

                                <%--String model_name1 = (String) hmDJ.get("model_name1");--%>
                                <%--String model_name2 = (String) hmDJ.get("model_name2");--%>
                                <%--String xqlx = model_name1 + "/" + model_name2;--%>

                                <%--String CustomerDemand_Level = (String) hmDJ.get("CustomerDemand_Level");--%>
                                <%--String CustomerDemand_LevelName = "";--%>
                                <%--if (CustomerDemand_Level.equals("1")) {--%>
                                    <%--CustomerDemand_LevelName = "<span class='by_bgcolorfjj by_width'>非常紧急</span>";--%>
                                <%--}--%>
                                <%--if (CustomerDemand_Level.equals("2")) {--%>
                                    <%--CustomerDemand_LevelName = "<span class='by_bgcolorjj by_width'>紧急</span>";--%>
                                <%--}--%>
                                <%--if (CustomerDemand_Level.equals("3")) {--%>
                                    <%--CustomerDemand_LevelName = "<span class='by_bgcolorpt by_width'>普通</span>";--%>
                                <%--}--%>

                    <%--%>--%>


                    <%--<li>--%>
                        <%--<div class="by_center by_top"><span class="by_date font_2"><%=CustomerDemand_DateBegin %></span>--%>
                            <%--<span class="by_name font_2">&nbsp;&nbsp;<%=shopName%></span></div>--%>
                        <%--<div class="by_center font_1 by_cenulcolor"> 保养类型：<%=xqlx%>--%>
                        <%--</div>--%>
                        <%--<div class="by_center font_1 by_cenulcolor"> 详<i--%>
                                <%--style="visibility: hidden">保养</i>情：<%=CustomerDemand_Memo%>--%>
                        <%--</div>--%>
                        <%--<div class="by_center font_1 by_cenulcolor"> 状<i--%>
                                <%--style="visibility: hidden">保养</i>态：<%=CustomerDemand_LevelName %>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<%--%>
                        <%--}--%>
                    <%--} else {--%>
                    <%--%>--%>
                    <%--<li>--%>
                        <%--<div style="width:100%;height:100%;margin-top:6%;" align="center"><font size="6">无保养提醒信息！</font>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<% } %>--%>
                </ul>
                <div id="pullUp" style="width:100%;text-align:center;font-size:32px">
                    <span class="pullUpIcon"></span><span class="pullUpLabel">上拉刷新...</span>
                </div>
        </div>
    </div>
</div>
<form name="form1" method="post" action="baoYangList.jsp">
    <input type="hidden" name="shopcode" value="<%=shopcode%>">
    <input type="hidden" name="strOpenId" value="<%=strOpenId%>">
    <input type="hidden" name="top" id="page1" value="<%=top%>">
</form>
</body>

<script type="text/javascript">
    var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;

    /**
     * 初始化iScroll控件
     */
    function loaded() {
        pullDownEl = document.getElementById('pullDown');
        pullDownOffset = pullDownEl.offsetHeight;
        pullUpEl = document.getElementById('pullUp');
        pullUpOffset = pullUpEl.offsetHeight;
        myScroll = new iScroll(
            'wrapper',
            {
                scrollbarClass: 'myScrollbar', /* 重要样式 */
                useTransition: false,
                topOffset: pullDownOffset,
                onRefresh: function () {
                    if (pullDownEl.className.match('loading')) {
                        pullDownEl.className = '';
                        pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
                    } else if (pullUpEl.className.match('loading')) {
                        pullUpEl.className = '';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
                    }
                },
                onScrollMove: function () {
                    if (this.y > 5 && !pullDownEl.className.match('flip')) {
                        pullDownEl.className = 'flip';
                        pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
                        this.minScrollY = 0;
                    } else if (this.y < 5
                        && pullDownEl.className.match('flip')) {
                        pullDownEl.className = '';
                        pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
                        this.minScrollY = -pullDownOffset;
                    } else if (this.y < (this.maxScrollY - 5)
                        && !pullUpEl.className.match('flip')) {
                        pullUpEl.className = 'flip';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
                        this.maxScrollY = this.maxScrollY;
                    } else if (this.y > (this.maxScrollY + 5)
                        && pullUpEl.className.match('flip')) {
                        pullUpEl.className = '';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
                        this.maxScrollY = pullUpOffset;
                    }
                },
                onScrollEnd: function () {
                    if (pullDownEl.className.match('flip')) {
                        pullDownEl.className = 'loading';
                        pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';
                        pullDownAction(); // Execute custom function (ajax call?)
                    } else if (pullUpEl.className.match('flip')) {
                        pullUpEl.className = 'loading';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';
                        pullUpAction();
                    }
                }
            });
        setTimeout(function () {
            document.getElementById('wrapper').style.left = '0';
        }, 800);
    }
    //初始化绑定iScroll控件
    document.addEventListener('touchmove', function (e) {
        e.preventDefault();
    }, false);
    document.addEventListener('DOMContentLoaded', loaded, false);
    //准备就绪后
    //就应该执行了
    function pullDownAction() { // 下拉刷新
        window.location.reload();
    }
    //初始化页码为2
    function pullUpAction() { //上拉加载更多
        var i = <%=top%>;
        //window.location.reload();
        var page3 = i + 1; // 每上拉一次页码加一次 （就比如下一页下一页）
        addCondition(page3);
        // window.location.reload();
        // 运行ajax 把2传过去告诉后台我上拉一次后台要加一页数据（当然 这个具体传什么还得跟后台配合）
        myScroll.refresh();// <-- Simulate network congestion, remove setTimeout from production!
    }
    //增加条件
    function addCondition(page3) {
        document.getElementById("page1").value = page3;
        document.form1.submit();

    }
</script>
</html>
