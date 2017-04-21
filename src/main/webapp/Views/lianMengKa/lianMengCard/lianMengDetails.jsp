
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	String cardNo=request.getParameter("cardNo");
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>联盟卡包</title>
		<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/lianMengKa/css/lianMengDetail.css"/>
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
						<span class="width_3 height border_1 margin_1">店铺</span>
						<span class="width_2 height border_1 margin_1">日期</span>
						<span class="width_1 height border_1 margin_1">次数</span>
					</li>

				</ul>
			</div>
			<a class="card_detailed font_3" href="/oauthLoginServlet?flagStr=lmkInfo">
				返回
			</a>
		</div>
	</body>
	<input type="hidden" id="cardNo" name="cardNo" value="<%=cardNo %>" >
	<script src="/scripts/lianMengKa/js/lianMengDetail.js" type="text/javascript" charset="utf-8"></script>
</html>
