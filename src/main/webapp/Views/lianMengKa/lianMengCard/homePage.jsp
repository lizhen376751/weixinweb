<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.weixin.login.autoLogin"%>
<% 
 	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
	
	System.out.println("=============lianMengCard - homePage.jsp=== CarId:|"+CarId+"|");
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		autoLogin autoLogin = new autoLogin();
		CarId = autoLogin.judgeOpenId(strOpenId, shopcode);
		session.setAttribute("DUDUCHEWANG_CarId", CarId);
		System.out.println("=============lianMengCard - homePage.jsp=== 自动登陆后  CarId:|"+CarId+"|");
	}
	
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		System.out.println("=============lianMengCard - homePage.jsp=== 自动登陆后 CarId还是空，则跳转到login.jsp   shopcode:"+shopcode+"|strOpenId:"+strOpenId+"|");
		response.sendRedirect("../../login.jsp?shopcode=" + shopcode + "&strOpenId=" + strOpenId + "");
	}
 
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache,no-store,must-revaldate"/>
		<meta http-equiv="Pragma" content="no-chche"/>
		<meta http-equiv="expires" content="0"/>
		<!--<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />-->
		<title>联盟卡包</title>
		<link rel="stylesheet" type="text/css" href="../css/weix.css"/>
		<link rel="stylesheet" type="text/css" href="../css/lianMengCard.css"/>
		<link rel="stylesheet" type="text/css" href="../css/lianMengCard.css"/>
		<script src="/scripts/lianMengKa/js/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/lianMengKa/js/JsBarcode.all.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/lianMengKa/js/jquery.qrcode.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="zheZhaoCeng">
			<div class="bianKuang">
				<div class="erWeiMa">
					
				</div>
			</div>
		</div>
	</body>
	<script src="/scripts/lianMengKa/js/lianMengCard.js" type="text/javascript" charset="utf-8"></script>
	<input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>" >
	<input type="hidden" id="strOpenId" name="strOpenId" value="<%=strOpenId %>" >
	<input type="hidden" id="CarId" name="CarId" value="<%=CarId %>" >
	<input type="hidden" id="contextPathStr" name="contextPathStr" value="<%=request.getContextPath() %>" >
	
</html>
