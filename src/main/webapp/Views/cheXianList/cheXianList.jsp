<%@ page language="java" pageEncoding="utf-8" %>
<%
	String shopCode = (String) request.getAttribute("shopCode");//用request得到
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>订单列表</title>
		<link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
		<link  rel="stylesheet" type="text/css" href="/styles/pullToRefresh.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/cheXianList/cheXianList.css"/>

		
		<script src="/scripts/cheXianList/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="/scripts/iscroll.js"></script>
		<script type="text/javascript" src="/scripts/pullToRefresh.js"></script>
		<script  type="text/javascript"  src="/scripts/main.js"></script>
		<script src="/scripts/cheXianList/cheXianList1.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
	<%--网页加载效果--%>
	<div id="loading">
		<img src="/files/loading2.gif"  alt="loading.." />
	</div>
	<div id="wrapper">

		<div class="kfk">
			<img src="/files/cheXianList/kf.png" alt="" class="kf"/>
		</div>
		<div class="ss">
			<input type="text" class="ss_val font_1" placeholder="请输入车牌号"/>
			<span class="font_1 ss_btn color_3">搜索</span>
		</div>
		<ul id="thelist" class="margin_auto" >
		<!--------------------------------------------------------------------------------每一条单据-->
		<%--<div class="bills">--%>
			<%--<!----------------------------------------------------------------------------每一条单据的标头，包括车牌号，单据编号-->--%>
			<%--<div class="bills_header">--%>
				<%--<div class="header_main font_1">--%>
					<%--<ul>--%>
						<%--<!-----------------------------------------------------------------车牌号-->--%>
						<%--<li class="width_1"><span class="car_num">车牌号：</span><span>鲁A00001</span></li>--%>
						<%--<!-----------------------------------------------------------------订单日期-->--%>
						<%--<li class="width_2"><span>订单日期：</span><span>2017-01-22</span></li>--%>
						<%--<!-----------------------------------------------------------------订单编号-->--%>
						<%--<li class="width_1"><span>订单编号：</span><span class="order_num">KNVB515631854156</span></li>--%>
						<%--<!-----------------------------------------------------------------共几家完成报价-->--%>
						<%--<li class="width_2 color_10"><span>共5家保险公司完成报价</span></li>--%>
					<%--</ul>--%>
				<%--</div>--%>
			<%--</div>--%>
			<%--<!----------------------------------------------------------------------------保险公司详情-->--%>
			<%--<div class="bills_company font_1">--%>
				<%--<ul>--%>
					<%--<!---------------------------------------------------------------------每一个保险公司的情况-->--%>
					<%--<li>--%>
						<%--<span class="color_9">人民保险：</span>--%>
						<%--<span class="price">¥5083.00</span>--%>
						<%--<span class="quote color_10">已报价</span>--%>
						<%--<span class="detail color_3">详情</span>--%>
					<%--</li>--%>
					<%--<!---------------------------------------------------------------------每一个保险公司的情况-->--%>
					<%--<li>--%>
						<%--<span class="color_9">人民保险：</span>--%>
						<%--<span class="price">¥5083.00</span>--%>
						<%--<span class="quote color_10">已报价</span>--%>
						<%--<span class="detail color_3">详情</span>--%>
					<%--</li>--%>
					<%--<!---------------------------------------------------------------------每一个保险公司的情况-->--%>
					<%--<li>--%>
						<%--<span class="color_9">人民保险：</span>--%>
						<%--<span class="price">¥0.0</span>--%>
						<%--<span class="quote color_10">报价中</span>--%>
						<%--<span class="detail color_3">详情</span>--%>
					<%--</li>--%>
				<%--</ul>--%>
				<%--<!-------------------------------------------------------------------------隔离层-->--%>
				<%--<span class="isolation"></span>--%>
			<%--</div>  <!---------------------------------------------------------------------------------------保险公司结束-->--%>
		<%--</div>   <!----------------------------------------------------------------------每一条单据结束-->--%>
		</ul>
	  </div>
	  <input type="hidden" name="shopCode" id="shopCode" value="<%=shopCode%>" />
	</body>
</html>
