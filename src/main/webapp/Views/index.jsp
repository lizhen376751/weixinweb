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
        //联盟介绍
        function lianMengJieShao() {
            window.location.href = "/oauthLoginServlet?lmcode=CS000_lianMengJieShao";
        }
        //养车信息
        function YCInfo() {
            window.location.href = "/oauthLoginServlet?lmcode=CS000_YCInfo";
        }
        //联盟活动
        function queryLMActivity() {
            window.location.href = "/oauthLoginServlet?lmcode=CS000_lianMengActivity";
        }
        //车险投保
        function cheXianTouBao() {
            window.location.href = "/oauthLoginServlet?lmcode=CS000_cheXianTouBao";
        }
        //aHi
        function AHIInfo() {
            window.location.href = "/oauthLoginServlet?lmcode=CS000_AHIInfo";
        }
        //联盟卡包
        function lmkInfo() {
            window.location.href = "/oauthLoginServlet?lmcode=CS000_lmkInfo";
        }
        //保养提醒
        function baoYangList() {
            //TODO 后期需要调用服务
            window.location.href = "/oauthLoginServlet?lmcode=CS000_baoYangList";
        }
        //服务导航
        function daohangindex() {
            //TODO 暂时未做好...
            window.location.href = "/oauthLoginServlet?lmcode=CS000_daohangindex";
        }
        //消费记录
        function xiaoFeiList() {
            //TODO 暂时未做好...
            window.location.href = "/oauthLoginServlet?lmcode=CS000_xiaoFeiList";
        }
        //注销
        function logout() {
            //TODO 暂时未做好...
            window.location.href = "/oauthLoginServlet?lmcode=CS000_logout";
        }

        //注册
        function register() {

            window.location.href = "/oauthLoginServlet?lmcode=CS000_register";
        }
        //个人中心
        function personalCenter() {

            window.location.href = "/oauthLoginServlet?lmcode=CS000_personalCenter";
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
            <li class="btnli"><input type="button" class="regbtn" value="服务导航" onclick="daohangindex()"/></li>
            <li class="btnli"><input type="button" class="regbtn" value="消费记录" onclick="xiaoFeiList()"/></li>
            <li class="btnli"><input type="button" class="regbtn" value="注册" onclick="register()"/></li>
            <li class="btnli"><input type="button" class="regbtn" value="个人中心" onclick="personalCenter()"/></li>
            <li class="btnli"><input type="button" class="regbtn" value="退出登录" onclick="logout()"/></li>
        </ul>
    </div>


</form>
</body>
</html>

