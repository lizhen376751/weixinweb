<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <title>联盟活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
	<link rel="stylesheet" type="text/css" href="/styles/lianMengActivity/css/yangCheXinXi.css"/>
	<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
	<div class="box">
			<!--养车信息-->
			<div class="card_name font_3">
				联盟活动
			</div>	
		</div>
  </body>
  <script src="/scripts/lianMengActivity/js/lianMengActivity.js" type="text/javascript" charset="utf-8"></script>
  <input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>" >
  <input type="hidden" id="contextPathStr" name="contextPathStr" value="<%=request.getContextPath() %>" >
</html>
