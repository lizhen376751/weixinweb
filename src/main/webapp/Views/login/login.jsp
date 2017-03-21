       <%@ page contentType="text/html; charset=GBK" language="java"%>

<%

 String strOpenId = request.getParameter("strOpenId");
 String shopcode = request.getParameter("shopcode");

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" /> 
    <title>车主登陆</title>
    <meta name="keywords" content="keyword ..." />
    <meta name="Description" content="description ..." />

    <link href="/styles/login/global.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript">
    //表单输入检查
	function login()
	{
	if (document.form1.CarId.value=="")
  	{
  		alert("请输入车牌号！");
  		document.form1.CarId.focus();
  		return false;
  	}
  	if (document.form1.Phone.value=="")
  	{
  		alert("请输入密码！");
  		document.form1.Phone.focus();
  		return false;
  	}
 	 document.form1.submit();
	}

	function regist(){
	window.location.href = "regist.jsp?dddopenid=<%=strOpenId%>&shopcode=<%=shopcode%>";
	}
	function edit(){
		window.location.href = "editpassword.jsp?dddopenid=<%=strOpenId%>&shopcode=<%=shopcode%>";
		}
    </script>
</head>
<body>


<div class="regtitle"><img src="/files/login/reg_title.jpg" alt="" title="" /><h4>车主登陆</h4></div>
<form name="form1" method="post" action="/oauthLoginServlet?flagStr=login" onSubmit="return checkform();">
<div class="regform">
 <ul>
  <li><label class="label">车牌号码</label><input type="text" style="font-size:24px;vertical-align:middle;background-color:#fff; height:40px; width:400px; border:1px solid #ccc;" name="CarId"/></li>
  <li><label class="label">登陆密码</label><input type="text" style="font-size:24px;vertical-align:middle;background-color:#fff; height:40px; width:400px; border:1px solid #ccc;" name="Phone" /></li>
  <li class="btnli"><input type="button" class="regbtn" value="登陆" onclick="login()"/></li>
<!--   
  <li class="btnli"><input type="button" class="regbtn" value="注册" onclick="regist()"/></li>
  <li class="btnli"><input type="button" class="regbtn" value="修改密码" onclick="edit()"/></li> 
-->
 </ul>
</div>
<input name="actions" type="hidden" value="login">
<input name="OpenId" type="hidden" value="<%=strOpenId%>">
<input name="shopcode" type="hidden" value="<%=shopcode%>">
</form>
</body>
</html>
