
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
    <link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/shoppersonCenter/projectCardMX.css"/>
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<%--网页加载效果--%>
<div id="loading">
    <img src="/files/loading2.gif"  alt="loading.." />
</div>
<div class="box">
    <ul class="font_1">
        <li class="biaoTou">
            <span class="titles width1">项目名称</span>
            <span class="titles width2">日期</span>
            <span class="titles width3">次数</span>
        </li>

    </ul>
    <div class="back" id="fanhui">返回</div>
</div>
</body>
<input type="hidden" id="cardNumb" name="cardNumb" value="<%=cardNumb %>" >
<input type="hidden" id="shopCode" name="shopCode" value="<%=shopCode %>" >
<input type="hidden" id="customerId" name="customerId" value="<%=cusomerId %>" >
<input type="hidden" id="plateNumber" name="plateNumber" value="<%=plateNumber%>">
<script src="/scripts/shoppersonCenter/projectCardMX.js" type="text/javascript" charset="utf-8"></script>
</html>