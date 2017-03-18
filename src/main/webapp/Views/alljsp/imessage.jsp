<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java"
	import="cn.duduchewang.weixin.common.GetUserMessage,com.weixin.login.autoLogin"%>
<%@ page language="java" import="com.vangdo.weixin.common.Html2Text"%>
<%
	String url = "http://shop.duduchewang.com/upload/";
	Html2Text Html2Text = new Html2Text();

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

	GetUserMessage GetUserMessage = new GetUserMessage();
	ArrayList listMessage = new ArrayList();
	listMessage = GetUserMessage.listMessage(shopcode, CarId);

	String key = (String) request.getParameter("key");
%>

<html lang="en">
<head>
<meta charset="utf-8" />
<meta content="target-densitydpi=320,width=640,user-scalable=no"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<title>我的消息</title>
<meta name="keywords" content="keyword ..." />
<meta name="Description" content="description ..." />
<!--<link href="favicon.ico" rel="shortcut icon" />-->
<link href="css/global.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript">
   function select(){
   var k=document.getElementById("service").value;
   this.location="imessage.jsp?shopcode=<%=shopcode%>&key=" + k;
	}
</script>
<style>
.select {
	width: 50%;
	height: 24px;
	background: none;
}
</style>
<body>
	<%
		if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
	%>
	<center>
		<a style="font-size:30px">对不起，请先登陆!</a>
	</center>

	<%
		} else {
	%>
	<select class="select" id="service"
		style="width:640;height:80; font-size:40px;" onchange="select();">
		<option value="">&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp全部</option>
		<option value="1" <%if ("1".equals(key)) {%> selected <%}%>>&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp已读</option>
		<option value="0" <%if ("0".equals(key)) {%> selected <%}%>>&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp未读</option>
	</select>

	<div class="aboutcontent2">
		<div class="discount">

			<%
				if (listMessage != null && listMessage.size() > 0) {
						for (int i = 0; i < listMessage.size(); i++) {
							HashMap hmMessage = (HashMap) listMessage.get(i);
							String strId = (String) hmMessage.get("id");
							String messageId = (String) hmMessage.get("messageId");
							String lookflg = (String) hmMessage.get("lookflg");

							HashMap hmInfo = GetUserMessage.dispMessage(messageId,
									shopcode);
							String MessageTitle = (String) hmInfo
									.get("MessageTitle");
							String msgListImg = (String) hmInfo.get("msgListImg");
							String CreatedTime = (String) hmInfo.get("CreatedTime");
			%>
			<ul>
				<li
					onClick="javascript:window.location='messageShow.jsp?ID=<%=strId%>&MID=<%=messageId%>&shopcode=<%=shopcode%>'">
					<div class="img">

						<%
							if ("".equals(msgListImg) || msgListImg == null
												|| "null".equals(msgListImg)) {
						%>
						<img src="image/nonepic.png" alt="" title="" />
						<%
							} else {
						%>
						<img src="<%=url%>/<%=shopcode%>/shopimg/<%=msgListImg%>" alt=""
							title="" />
						<%
							}
						%>
					</div>
					<div class="info">
						<dl>
							<dt>
								<a><%=MessageTitle%></a>
							</dt>
						</dl>
						<div class="price">
							<%
								if (lookflg.equals("0")) {
							%>
							<a>未读!</a>
							<%
								} else {
							%>
							<a style=" color:red">已读!</a>
							<%
								}
							%>
							<b><%=CreatedTime.substring(0, 10)%></b>
						</div>
					</div> <a href="messageShow.jsp?ID=<%=strId%>&MID=<%=messageId%>"
					class="servicebtn"></a></li>
			</ul>
			<%
				}
					} else {
			%>
			<center>
				<a style="font-size:30px">对不起，您当前没有消息!</a>
			</center>
			<%
				}
				}
			%>
		</div>
	</div>
	<script language="javascript" type="text/javascript"
		src="js/jquery-1.7.1.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="js/jquery.iosslider.js"></script>
	<script language="javascript" type="text/javascript"
		src="js/default.js"></script>
</body>
</html>
