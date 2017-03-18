<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java"
	import="cn.duduchewang.weixin.common.GetReminders"%>
<%@ page language="java" import="com.weixin.login.autoLogin"%>
<%
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
else {
		GetReminders GetReminders = new GetReminders();
		ArrayList listReminders = new ArrayList();
		listReminders = GetReminders.listReminders(shopcode, CarId);
%>

<html lang="en">
<head>
<meta charset="utf-8" />
<meta content="target-densitydpi=320,width=640,user-scalable=no"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<title>保养提醒</title>
<meta name="keywords" content="keyword ..." />
<meta name="Description" content="description ..." />
<link href="css/global.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="secretarylist2">
		<ul>
			<%
				if (listReminders != null && listReminders.size() > 0) {
						for (int i = 0; i < listReminders.size(); i++) {
							HashMap hm = (HashMap) listReminders.get(i);
							String ModuleName = (String) hm.get("ModuleName");
							String lastdate = (String) hm.get("lastdate");
							String lilunxiaofeidate = (String) hm
									.get("lilunxiaofeidate");
							String fuwutixing = (String) hm.get("fuwutixing");
			%>
			<li>
				<div
					style="line-height:30px; font-size:40px; color:#FF0000; position:absolute; left:15px; top:13px;">
					项目：<%=ModuleName%></div>
				</hr></li>
			<li>
				<div class="date">
					上次消费日期：<%=lastdate.substring(0, 10)%></div></li>
			<li>
				<div class="date">
					建议保养日期：<%=lilunxiaofeidate.substring(0, 10)%></div></li>
			<li>
				<div
					style="line-height:30px; font-size:20px; text-align:left; vertical-align:middle;"><%=fuwutixing%></div>
			</li>
			<%
				}
					} else {
			%>
			<center>
				<a style="font-size:30px">对不起，您当前没有提醒内容！</a>
			</center>
			<%
				}
			%>
		</ul>
	</div>
</body>
</html>
<%
	}
%>