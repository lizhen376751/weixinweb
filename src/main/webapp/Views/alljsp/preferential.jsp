<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java" import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java" import="com.weixin.action.MmallAction"%>
<%@ page language="java" import="com.weixin.login.autoLogin"%>
<%
String url="http://shop.duduchewang.com/upload/";
String ID=request.getParameter("shopcode");
String ProductName="";
String ProductPrice="";
String ProductOriginalPrice="";
String ProductImgList="";
String ProductContent="";
String ProductID="";
MmallAction MmallAction= new MmallAction();

ArrayList list= MmallAction.TeJialistInfo(ID);
HashMap map=MmallAction.shopName(ID);
String ShopName=(String)map.get("ShopName");
%>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" /> 
    <title><%=ShopName%></title>
    <meta name="keywords" content="keyword ..." />
    <meta name="Description" content="description ..." />
    <!--<link href="favicon.ico" rel="shortcut icon" />-->
    <link href="css/global.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="aboutcontent2">
<div class="discount">
<ul>
<%
	for(int i=0;i<list.size();i++){
		HashMap hm=(HashMap)list.get(i);
		ProductName=(String)hm.get("ProductName");
		ProductPrice=(String)hm.get("ProductPrice");
		ProductOriginalPrice=(String)hm.get("ProductOriginalPrice");
		ProductContent=(String)hm.get("ProductContent");
		ProductID=(String)hm.get("ID");
		ProductImgList=(String)hm.get("ProductImgList");
		%>
<li onClick="javascript:window.location='pruductDescript.jsp?ProductID=<%=ProductID %>'">
  <div class="img">
<%
if(ProductImgList==""){
%>
 <img src="image/nonepic.png" alt="" title="" />
<% }else{
%>
 <img src="<%=url%>/<%=ID%>/shopimg/<%=ProductImgList%>" alt="" title="" />
 <%}%> 
  </div>
  <div class="info">
  <dl>
   <dt><a href="pruductDescript.jsp?ProductID=<%=ProductID %>"><%=ProductName %></a></dt>
   <dd><%=ProductContent %></dd>
  </dl>
  <div class="price">&yen;<%=ProductPrice %><del>&yen;<%=ProductOriginalPrice %></del></div>
  </div>
  <a href="pruductDescript.jsp?ProductID=<%=ProductID %>" class="buybtn"></a>
 </li>
	<%}
 %>
 
</ul>
</div>
</div>
<script language="javascript" type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.iosslider.js"></script>
<script language="javascript" type="text/javascript" src="js/default.js"></script>
</body>
</html>
