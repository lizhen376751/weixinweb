<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java" import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java" import="com.weixin.action.MmallAction,com.vangdo.training.Action.WeiChatAction"%> 
<%@ page language="java" import="com.weixin.login.autoLogin,com.vangdo.weixin.common.Html2Text"%>
<%
String url="http://shop.duduchewang.com/upload/";

String shopcode =(String)session.getAttribute("DUDUCHEWANG_shopcode"); 
String strOpenId   =(String)session.getAttribute("DUDUCHEWANG_OpenId");
String CarId = (String)session.getAttribute("DUDUCHEWANG_CarId");

if(CarId == null || "null".equals(CarId) || "".equals(CarId)) {
	autoLogin autoLogin = new autoLogin();
	CarId = autoLogin.judgeOpenId(strOpenId,shopcode);
	session.setAttribute("DUDUCHEWANG_CarId", CarId);
}

if(CarId == null || "null".equals(CarId) || "".equals(CarId)) {
	response.sendRedirect("login.jsp?shopcode="+shopcode+"&strOpenId="+strOpenId+"");
}
	
	String id="";//ticketMx表id
 	String ticketid="";
 	String ticketcode="";
 	String usedflg="";
 	String useddate="";
 	String usedchepai="";
 	String usedShopcode="";
 	//String shopcode="";//ticket 表中shopcode
 	String itemname="";
 	String enddate="";
 	String smallimg="";
 	String begindate="";
 	String UserId="";
 	int ks=0;
 	 String key=(String)request.getParameter("key");
 	 MmallAction MmallAction= new MmallAction();
%>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" /> 
    <title>电子优惠券</title>
    <meta name="keywords" content="keyword ..." />
    <meta name="Description" content="description ..." />
    <!--<link href="favicon.ico" rel="shortcut icon" />-->
    <link href="css/global.css" rel="stylesheet" type="text/css" />
       <script language="JavaScript">
   function select(){
   var k=document.getElementById("service").value;
   this.location="electroniccoupon.jsp?shopcode=<%=shopcode%>&key="+k;
   }
   </script>
    <style> 
.select {width:50%; height:24px; background:none; }
</style> 
</head>
<body>
<%
	if(CarId == null || "null".equals(CarId) || "".equals(CarId)) {
%>
    <center><a style="font-size:30px">对不起，请先登陆!</a></center>
    	
<% }else {
%>
 <select class="select" id="service" style="width:640;height:80; font-size:40px;" onchange="select();">
              <option value="0" <%if("0".equals(key)){%>selected<%}%>> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp未消费</option>
              <option value="1" <%if("1".equals(key)){%>selected<%}%>> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp已消费</option>
              <option value="2" <%if("2".equals(key)){%>selected<%}%>> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp已过期</option>
            </select>
<div class="aboutcontent2">
<div class="discount">

<%  	
 ArrayList list2=MmallAction.khId2(CarId,shopcode);
	for(int j=0;j<list2.size();j++){
	HashMap m2=(HashMap)list2.get(j);
	UserId=(String)m2.get("ID");
	if(key==null||"".equals(key)||"null".equals(key)){
		key="0";
	}
	ArrayList list=MmallAction.userticket(UserId,key);
	
	for(int i=0;i<list.size();i++){
		HashMap map=(HashMap)list.get(i);
		id=(String)map.get("id");
		ticketid=(String)map.get("ticketid");
		ticketcode=(String)map.get("ticketcode");
		usedflg=(String)map.get("usedflg");
		useddate=(String)map.get("useddate");
		usedchepai=(String)map.get("usedchepai");
		usedShopcode=(String)map.get("usedShopcode");
		//HashMap h=MmallAction.ticketShow(ticketid);

		itemname=(String)map.get("itemname");
		enddate=(String)map.get("enddate");
		smallimg=(String)map.get("smallimg");
		begindate=(String)map.get("begindate");
		HashMap sn=MmallAction.shopName(shopcode);
		String shopName=(String)sn.get("ShopName");
		ks=ks+2;
		
	%>	
 	<ul>	
<li onClick="javascript:window.location='electronicinfo.jsp?id=<%=id%>&shopcode=<%=shopcode %>'">
<div class="img">
<% 
if(smallimg==null||"null".equals(smallimg)||"".equals(smallimg)){
%>
    <img src="image/nonepic.png" alt="" title="" />
<%}else{

%>
<img src="<%=url%>/<%=shopcode%>/shopimg/<%=smallimg%>" alt="" title="" />
<% }%>

</div>
  <div class="info">
  <dl>
   <dt><a><%=itemname%></a></dt>
   <dd><%=shopName%></dd>
  </dl>
  <div class="price">
  <%
  if(usedflg.equals("0")){
  %>
   <a>未使用!</a>
  <% }else{
  %>
  <a style=" color:red">已使用!</a>
  <%}
   %>
  <b>有效期：<%=begindate%>至<%=enddate%>
  </b>
  </div>
  </div>
   <a href="electronicinfo.jsp?id=<%=id%>&shopcode=<%=shopcode %>" class="servicebtn"></a>
</li>
</ul>
<% 	
	}
}
if(ks==0){
%>
<center><a style="font-size:30px">对不起，您当前没有优惠券!</a></center>
<% 
}
}

 %>

</div>
</div>
<script language="javascript" type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.iosslider.js"></script>
<script language="javascript" type="text/javascript" src="js/default.js"></script>
</body>
</html>
