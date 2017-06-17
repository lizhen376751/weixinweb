
<%@ page language="java" pageEncoding="UTF-8"%>

<%
    String cardNumb=request.getParameter("cardNumb");
    String shopCode=request.getParameter("shopCode");
    String cusomerId=request.getParameter("customerId");
    String plateNumber = request.getParameter("plateNumber");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>项目卡</title>
    <link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/shoppersonCenter/projectCardMX.css"/>
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<%--网页加载效果--%>
<div id="loading">
    <img src="/files/loading.gif"  alt="loading.." />
</div>
<div class="box">

    <div class="card_name font_3">
        洗车卡
    </div>

    <%--<div class="logo">--%>
    <%--<div class="circle">--%>

    <%--</div>--%>
    <%--</div>--%>
    <!--店铺名称-->
    <%--<div class="shop_name font_2">--%>
    <%--发售店铺：--%>
    <%--</div>--%>
    <!--消费详情-->
    <div class="cost">
        <ul class="font_1">
            <li class="biaoTou">
                <span class="width_2 height border_1">项目</span>
                <span class="width_2 height border_1 margin_1">日期</span>
                <span class="width_1 height border_1 margin_1">次数</span>
            </li>

        </ul>
    </div>
   <%-- <a class="card_detailed font_3" href="/shopweixinServlet?serviceType=shoppersonCenter">
        返回
    </a>--%>
</div>
</body>
<input type="hidden" id="cardNumb" name="cardNumb" value="<%=cardNumb %>" >
<input type="hidden" id="shopCode" name="shopCode" value="<%=shopCode %>" >
<input type="hidden" id="customerId" name="customerId" value="<%=cusomerId %>" >
<input type="hidden" id="plateNumber" name="plateNumber" value="<%=plateNumber%>">
<script src="/scripts/shoppersonCenter/projectCardMX.js" type="text/javascript" charset="utf-8"></script>
</html>