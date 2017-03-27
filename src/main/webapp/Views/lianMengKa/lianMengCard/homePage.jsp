<%@ page import="com.dudu.weixin.service.AutoLoginService" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<% 
 	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");


 
%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Cache-Control" content="no-cache,no-store,must-revaldate"/>
		<meta http-equiv="Pragma" content="no-chche"/>
		<meta http-equiv="expires" content="0"/>
		<title>联盟卡包</title>
		<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/lianMengKa/css/lianMengCard.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/lianMengKa/css/lianMengCard.css"/>
		<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
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
