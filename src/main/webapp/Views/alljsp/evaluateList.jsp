<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java" import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java" import="cn.duduchewang.weixin.common.GetOrderList,com.vangdo.weixin.common.Html2Text,com.weixin.login.autoLogin"%>
<%
String url="http://shop.duduchewang.com/upload/";
Html2Text Html2Text = new Html2Text();

String shopcode =(String)session.getAttribute("DUDUCHEWANG_shopcode"); 
String strOpenId   =(String)session.getAttribute("DUDUCHEWANG_OpenId");

String CarId = (String)session.getAttribute("DUDUCHEWANG_CarId");

if(CarId == null || "null".equals(CarId) || "".equals(CarId)) {
	//String OpenId = (String)request.getAttribute("OpenI");
	autoLogin autoLogin = new autoLogin();
	CarId = autoLogin.judgeOpenId(strOpenId,shopcode);
	session.setAttribute("DUDUCHEWANG_CarId", CarId);
}

if(CarId == null || "null".equals(CarId) || "".equals(CarId)) {
	response.sendRedirect("login.jsp?shopcode="+shopcode+"");
}
else {
GetOrderList GetOrderList = new GetOrderList();
ArrayList listOrder = new ArrayList();
listOrder = GetOrderList.orderlistInfo(CarId,shopcode);
%>
<html>
<head>
    <meta charset="GBK" />
    <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" /> 
    <title>订单列表</title>
    <meta name="keywords" content="keyword ..." />
    <meta name="Description" content="description ..." />
    <!--<link href="favicon.ico" rel="shortcut icon" />-->
    <link href="css/global.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="aboutcontent2">
<div class="discount">
<%
if(listOrder != null && listOrder.size() > 0) {
	for(int i=0;i<listOrder.size();i++) {
		HashMap hmOrder = (HashMap)listOrder.get(i);
		String orderId = (String)hmOrder.get("ID");
		String UserId = (String)hmOrder.get("UserId");
		String ProductId = (String)hmOrder.get("ProductId");
		String ProductPrice = (String)hmOrder.get("ProductPrice");
		String ProductNum = (String)hmOrder.get("ProductNum");
		String State=(String)hmOrder.get("state");
		
		HashMap hmProduct = GetOrderList.getProductInfo(ProductId,shopcode);
		String ProductName = (String)hmProduct.get("ProductName");
		String ProductContent = (String)hmProduct.get("ProductContent");
		String ProductImgList = (String)hmProduct.get("ProductImgList");

%>
<ul>	
<li onClick="javascript:window.location='grade.jsp?s=<%=shopcode%>&u=<%=UserId%>&p=<%=ProductId%>&id=<%=orderId %>&state=<%=State%>'">
  <div class="img"><img src="<%=url%>/<%=shopcode%>/shopimg/<%=ProductImgList%>" alt="" title="" /></div>
  <div class="info">
  <dl>
   <dt><a href="#">
   <%
   	if(State.equals("0")){
   		%>
   		<%=ProductName%>
   		<%
   	}else{
   		%>	<del><%=ProductName%></del><%
   	}
   %>
   </a></dt>
   <dd><%=ProductContent%></dd>
  </dl>
  <div class="price">&yen;<%=ProductPrice%> 数量：<%=ProductNum%> </div>
  </div>
  <a href="grade.jsp?s=<%=shopcode%>&u=<%=UserId%>&p=<%=ProductId%>&state=<%=State%>&id=<%=orderId%>" class="servicebtn"></a>
</li>
</ul>

<%
}
} else {
%>
<center><a style="font-size:30px"><br><br>对不起，您当前没有订单信息!</a></center>
<%}%>

</div>
</div>
<script language="javascript" type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.iosslider.js"></script>
<script language="javascript" type="text/javascript" src="js/default.js"></script>
</body>
</html>
<%}%>