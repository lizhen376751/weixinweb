<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
<meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport"/>
<meta content="yes" name="apple-mobile-web-app-capable"/>
<meta content="black" name="apple-mobile-web-app-status-bar-style"/>
<meta content="telephone=no" name="format-detection"/>
<title>嘟嘟车网</title>
<meta name="keywords" content="keyword ..."/>
<meta name="Description" content="description ..."/>
<script language="JavaScript">
//表单输入检查
function lianMengJieShao() {
window.location.href = "/main/oauthLoginServlet?flagStr=lianMengJieShao";
}
function YCInfo() {
window.location.href = "/main/oauthLoginServlet?flagStr=YCInfo";
}
function queryLMActivity() {
window.location.href = "/main/oauthLoginServlet?flagStr=lianMengActivity";
}
function cheXianTouBao() {
window.location.href = "/main/oauthLoginServlet?flagStr=cheXianTouBao";
}
</script>
</head>
<body>

<form name="form1" method="post" action="checkAction.jsp">
<div class="regform">
<ul>
<li class="btnli"><input type="button" class="regbtn" value="联盟介绍" onclick="lianMengJieShao()"/></li>
<li class="btnli"><input type="button" class="regbtn" value="养车信息" onclick="YCInfo()"/></li>
<li class="btnli"><input type="button" class="regbtn" value="联盟活动" onclick="queryLMActivity()"/></li>
<li class="btnli"><input type="button" class="regbtn" value="保险" onclick="cheXianTouBao()"/></li>
</ul>
</div>


</form>
</body>
</html>
