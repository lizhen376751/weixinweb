<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.weixin.login.autoLogin"%>
<% 
 	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
	
	String cardNo=request.getParameter("cardNo");
	
	System.out.println("=============lianMengCard - lianMengDetails.jsp=== CarId:|"+CarId+"|cardNo:"+cardNo+"|");
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		autoLogin autoLogin = new autoLogin();
		CarId = autoLogin.judgeOpenId(strOpenId, shopcode);
		session.setAttribute("DUDUCHEWANG_CarId", CarId);
		System.out.println("=============lianMengCard - lianMengDetails.jsp=== 自动登陆后  CarId:|"+CarId+"|");
	}
	
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		System.out.println("=============lianMengCard - lianMengDetails.jsp=== 自动登陆后 CarId还是空，则跳转到login.jsp   shopcode:"+shopcode+"|strOpenId:"+strOpenId+"|");
		response.sendRedirect("../../login.jsp?shopcode=" + shopcode + "&strOpenId=" + strOpenId + "");
	}
 
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>联盟卡包</title>
		<link rel="stylesheet" type="text/css" href="../css/weix.css"/>
		<link rel="stylesheet" type="text/css" href="../css/lianMengDetail.css"/>
		<script src="../js/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="box">
			<!--卡的名字-->
			<div class="card_name font_3">
				洗车卡
			</div>
			<!--Logo-->
			<div class="logo">
				<div class="circle">
					<!-- <img src="../img/logo.png"/> -->
				</div>
			</div>
			<!--店铺名称-->
			<div class="shop_name font_2">
				发售店铺：
			</div>
			<!--消费详情-->
			<div class="cost">
				<ul class="font_1">
					<li class="biaoTou">
						<span class="width_2 height border_1">项目</span>
						<span class="width_3 height border_1 margin_1">店铺</span>
						<span class="width_2 height border_1 margin_1">日期</span>
						<span class="width_1 height border_1 margin_1">次数</span>
					</li>
					<!--<li class="border_2">
						<span class="width_2">洗车</span>
						<span class="width_2 margin_1">店铺一</span>
						<span class="width_2 margin_1">2017-02-04</span>
						<span class="width_1 margin_1">+1</span>
					</li>-->
				</ul>
			</div>
			<!--返回按键-->
			<a class="card_detailed font_3" href="homePage.jsp">
				返回
			</a>
		</div>
	</body>
	<input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>" >
	<input type="hidden" id="strOpenId" name="strOpenId" value="<%=strOpenId %>" >
	<input type="hidden" id="CarId" name="CarId" value="<%=CarId %>" >
	<input type="hidden" id="cardNo" name="cardNo" value="<%=cardNo %>" >
	<input type="hidden" id="contextPathStr" name="contextPathStr" value="<%=request.getContextPath() %>" >
	<script src="../js/lianMengDetail.js" type="text/javascript" charset="utf-8"></script>
</html>
