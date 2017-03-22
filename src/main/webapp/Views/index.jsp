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
            window.location.href = "/oauthLoginServlet?flagStr=lianMengJieShao";
        }
        function YCInfo() {
            window.location.href = "/oauthLoginServlet?flagStr=YCInfo";
        }
        function queryLMActivity() {
            window.location.href = "/oauthLoginServlet?flagStr=lianMengActivity";
        }
        function cheXianTouBao() {
            window.location.href = "/oauthLoginServlet?flagStr=cheXianTouBao";
        }
        //aHi
        function AHIInfo() {
            window.location.href = "/oauthLoginServlet?flagStr=AHIInfo";
        }
        //联盟卡包
        function lmkInfo() {
            window.location.href = "/oauthLoginServlet?flagStr=lmkInfo";
        }
        //保养提醒
        function baoYangList() {
            window.location.href = "/oauthLoginServlet?flagStr=baoYangList";
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
            <li class="btnli"><input type="button" class="regbtn" value="Ahi" onclick="AHIInfo()"/></li>
            <li class="btnli"><input type="button" class="regbtn" value="联盟卡包" onclick="lmkInfo()"/></li>
            <li class="btnli"><input type="button" class="regbtn" value="保养提醒" onclick="baoYangList()"/></li>
        </ul>
    </div>


</form>
</body>
</html>

