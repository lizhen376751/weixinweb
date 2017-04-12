<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>订单列表</title>
		<link rel="stylesheet" type="text/css" href="/styles/cheXianList/wxlm.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/cheXianList/cheXianList.css"/>
		
		<script src="/scripts/cheXianList/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/cheXianList/cheXianList.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<!--------------------------------------------------------------------------------每一条单据-->
		<div class="bills">
			<!----------------------------------------------------------------------------每一条单据的标头，包括车牌号，单据编号-->
			<div class="bills_header">
				<div class="header_main font_1">
					<ul>
						<!-----------------------------------------------------------------车牌号-->
						<li class="width_1"><span class="car_num">车牌号：</span><span>鲁A00001</span></li>
						<!-----------------------------------------------------------------订单日期-->
						<li class="width_2"><span>订单日期：</span><span>2017-01-22</span></li>
						<!-----------------------------------------------------------------订单编号-->
						<li class="width_1"><span>订单编号：</span><span class="order_num">KNVB515631854156</span></li>
						<!-----------------------------------------------------------------共几家完成报价-->
						<li class="width_2 color_10"><span>共5家保险公司完成报价</span></li>
					</ul>
				</div>
			</div>
			<!----------------------------------------------------------------------------保险公司详情-->
			<div class="bills_company font_1">
				<ul>
					<!---------------------------------------------------------------------每一个保险公司的情况-->
					<li>
						<span class="color_9">人民保险：</span>
						<span class="price">¥5083.00</span>
						<span class="quote color_10">已报价</span>
						<span class="detail color_3">详情</span>
					</li>
					<!---------------------------------------------------------------------每一个保险公司的情况-->
					<li>
						<span class="color_9">人民保险：</span>
						<span class="price">¥5083.00</span>
						<span class="quote color_10">已报价</span>
						<span class="detail color_3">详情</span>
					</li>
					<!---------------------------------------------------------------------每一个保险公司的情况-->
					<li>
						<span class="color_9">人民保险：</span>
						<span class="price">¥0.0</span>
						<span class="quote color_10">报价中</span>
						<span class="detail color_3">详情</span>
					</li>
				</ul>
				<!-------------------------------------------------------------------------隔离层-->
				<span class="isolation"></span>
			</div>  <!---------------------------------------------------------------------------------------保险公司结束-->
		</div>   <!----------------------------------------------------------------------每一条单据结束-->
		
		
		<!--------------------------------------------------------------------------------每一条单据-->
		<div class="bills">
			<!----------------------------------------------------------------------------每一条单据的标头，包括车牌号，单据编号-->
			<div class="bills_header">
				<div class="header_main font_1">
					<ul>
						<!-----------------------------------------------------------------车牌号-->
						<li class="width_1"><span class="car_num">车牌号：</span><span>鲁A00001</span></li>
						<!-----------------------------------------------------------------订单日期-->
						<li class="width_2"><span>订单日期：</span><span>2017-01-22</span></li>
						<!-----------------------------------------------------------------订单编号-->
						<li class="width_1"><span>订单编号：</span><span class="order_num">KNVB515631854156</span></li>
						<!-----------------------------------------------------------------共几家完成报价-->
						<li class="width_2 color_10"><span>共5家保险公司完成报价</span></li>
					</ul>
				</div>
			</div>
			<!----------------------------------------------------------------------------保险公司详情-->
			<div class="bills_company font_1">
				<ul>
					<!---------------------------------------------------------------------每一个保险公司的情况-->
					<li>
						<span class="color_9">人民保险：</span>
						<span class="price">¥5083.00</span>
						<span class="quote color_10">已报价</span>
						<span class="detail color_3">详情</span>
					</li>
					<!---------------------------------------------------------------------每一个保险公司的情况-->
					<li>
						<span class="color_9">人民保险：</span>
						<span class="price">¥5083.00</span>
						<span class="quote color_10">已报价</span>
						<span class="detail color_3">详情</span>
					</li>
					<!---------------------------------------------------------------------每一个保险公司的情况-->
					<li>
						<span class="color_9">人民保险：</span>
						<span class="price">¥0.0</span>
						<span class="quote color_10">报价中</span>
						<span class="detail color_3">详情</span>
					</li>
				</ul>
			</div>  <!---------------------------------------------------------------------------------------保险公司结束-->
		</div>   <!----------------------------------------------------------------------每一条单据结束-->
	</body>
</html>