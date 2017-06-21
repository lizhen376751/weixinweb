<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%
String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
%>
<html>
  <head>
    
    <title>养车百科</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
	<link rel="stylesheet" type="text/css" href="/styles/yangCheInfo/css/yangCheXinXi.css"/>
	<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
  <%--网页加载效果--%>
  <div id="loading">
	  <img src="/files/loading2.gif"  alt="loading.." />
  </div>
	<div class="box">
			<!--养车信息-->
			<div class="card_name font_3">
				养车信息
			</div>	
		</div>
  </body>
  <script src="/scripts/yangCheInfo/js/yangCheXinXi.js" type="text/javascript" charset="utf-8"></script>
  <input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>" >
  <input type="hidden" id="contextPathStr" name="contextPathStr" value="<%=request.getContextPath() %>" >
</html>
