<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.weixin.login.autoLogin"%>
<% 
 	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
	
	System.out.println("=============reserve - Advance.jsp=== CarId:|"+CarId+"|");
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		autoLogin autoLogin = new autoLogin();
		CarId = autoLogin.judgeOpenId(strOpenId, shopcode);
		session.setAttribute("DUDUCHEWANG_CarId", CarId);
		System.out.println("=============reserve - Advance.jsp=== 自动登陆后  CarId:|"+CarId+"|");
	}
	
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		System.out.println("=============reserve - Advance.jsp=== 自动登陆后 CarId还是空，则跳转到login.jsp   shopcode:"+shopcode+"|strOpenId:"+strOpenId+"|");
		response.sendRedirect("../../login.jsp?shopcode=" + shopcode + "&strOpenId=" + strOpenId + "");
	}

 	//预约店铺编码
 	String shopcode2 = request.getParameter("shopcode2");
 	String shopName = request.getParameter("shopName");
 	shopName = java.net.URLDecoder.decode(shopName, "UTF-8");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>预约</title>
		<link rel="stylesheet" type="text/css" href="../css/weix.css"/>
		<link rel="stylesheet" type="text/css" href="../css/Advance.css"/>
		<script src="../js/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/Advance.js" type="text/javascript" charset="utf-8"></script>
				<!--日历插件-->
		<link rel="stylesheet" type="text/css" href="../js/Mobiscroll/css/mobiscroll.css"/>
		<link rel="stylesheet" type="text/css" href="../js/Mobiscroll/css/mobiscroll_002.css"/>
		<link rel="stylesheet" type="text/css" href="../js/Mobiscroll/css/mobiscroll_003.css"/>	    
	    <script src="../js/Mobiscroll/js/mobiscroll_002.js"></script>
	    <script src="../js/Mobiscroll/js/mobiscroll_003.js"></script>
	    <script src="../js/Mobiscroll/js/mobiscroll_004.js"></script>
	    <script src="../js/Mobiscroll/js/mobiscroll_005.js"></script>
	    <script src="../js/Mobiscroll/js/mobiscroll.js"></script>
		<script  type="text/javascript"  src="/scripts/main.js"></script>
	</head>
	<body>
		<%--网页加载效果--%>
		<div id="loading">
			<img src="/files/loading.gif"  alt="loading.." />
		</div>
		<div  class="Advance_conter">
			<ul class="Advance_conter_list font_2">
				<li >店铺名称：<%=shopName %></li>
				<li class="chantime">
					<span >时间：</span>
					<input value=""  name="appDate" class="font_11"  id="appDate" type="text">
					<label for="appDate">
						<img style="width: 50px;margin-left: -90px;position: relative;top: 10px;" src="../img/calendar.png"/>
					</label>
					<select  class="font_11" id="advance_time">
						
					</select>
				</li>
				<li>
					<span >项目：</span>
					<select id="xmselect" class="xmselect font_11" >
						     <option value=""></option>			
				   </select>
				</li>
			</ul>
			<div class="beizhu font_2">
					<span >备注：</span>
					<textarea id="notes" class="font_11"></textarea>
			</div>
			<!--底部提交预约-->
		   <div class="sub" id="sub">
				提交预约
		   </div>
		</div>
		<input type="hidden" id="CarId" name="CarId" value="<%=CarId %>" />
		<input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>" />
		<input type="hidden" id="shopcode2" name="shopcode2" value="<%=shopcode2 %>" />
		<input type="hidden" id="contextPathStr" name="contextPathStr" value="<%=request.getContextPath() %>" >
	</body>
</html>