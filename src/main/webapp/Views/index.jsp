<%@ page language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta content="telephone=no" name="format-detection"/>
    <title>嘟嘟车网</title>
    <meta name="keywords" content="keyword ..."/>
    <meta name="Description" content="description ..."/>
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script language="JavaScript">


        //登录
        function login() {
            window.location.href = "/shopweixinServlet?serviceType=login";
        }
        //aHi
        function AHIInfo() {
            window.location.href = "/ahi";
        }
        //ahi详情
        function AHIxiangqing() {
            window.location.href = "/AHIInfoxiangqing";
        }
        //创建菜单
        function creatMenu() {
            var regbtns = $(".regbtn");
            var a = '';
            for (var i = 0; i < regbtns.length; i++) {
                a += '/' + $(regbtns[i]).val()
            }
            console.log(a)
            window.location.href = "/createMenu" + a;
        }

    </script>
</head>
<body>

<form name="form1" method="post" action="checkAction.jsp">
    <div class="regform">
        <ul>

            <li class="btnli"><input type="button" class="reg" value="登录" onclick="login()"/></li>
            <li class="btnli">店铺编码:<input name="code" class="regbtn" value=""/></li>
            <li class="btnli">菜单类型:<input name="type" class="regbtn" value=""/></li>
            <li class="btnli">appid:<input name="appid" class="regbtn" value=""/></li>
            <li class="btnli">appsecret:<input name="appSecret" class="regbtn" value=""/></li>
            <li class="btnli">服务器地址:<input name="url" class="regbtn" value=""/></li>
            <li class="btnli">服务器域名:<input name="yuming" class="regbtn" value=""/></li>
            <li class="btnli"><input type="button" class="regbtns" value="创建菜单" onclick="creatMenu()"/></li>
        </ul>
    </div>


</form>
</body>
</html>

