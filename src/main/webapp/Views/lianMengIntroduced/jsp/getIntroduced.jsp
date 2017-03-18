<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>联盟介绍</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="/styles/lianMengIntroduced/css/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/lianMengIntroduced/css/yangCheXinXi.css"/>
		<script src="/scripts/lianMengIntroduced/js/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>


  </head>
  
  <body>
    <h2></h2>
    <div class="txt"></div>
  </body>
<script src="/scripts/lianMengIntroduced/js/getIntroduced.js" type="text/javascript" charset="utf-8"></script>
  <input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>" >
  <input type="hidden" id="contextPathStr" name="contextPathStr" value="<%=request.getContextPath() %>" >
</html>
