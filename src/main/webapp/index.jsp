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
        //aHi
        function AHIInfo() {
            window.location.href = "/main/oauthLoginServlet?flagStr=AHIInfo";
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
        </ul>
    </div>


</form>
</body>
</html>


<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<script src="https://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h2>用户登录</h2>--%>

<%--<form ng-app="myApp" ng-controller="validateCtrl"--%>
      <%--name="myForm" novalidate>--%>

    <%--<p>用户名:<br>--%>
        <%--<input type="text" name="user" ng-model="user" required>--%>
        <%--<span style="color:red" ng-show="myForm.user.$dirty && myForm.user.$invalid">--%>
<%--<span ng-show="myForm.user.$error.required">用户名是必须的。</span>--%>
<%--</span>--%>
    <%--</p>--%>

    <%--<p>邮箱:<br>--%>
        <%--<input type="email" name="email" ng-model="email" required>--%>
        <%--<span style="color:red" ng-show="myForm.email.$dirty && myForm.email.$invalid">--%>
<%--<span ng-show="myForm.email.$error.required">邮箱是必须的。</span>--%>
<%--<span ng-show="myForm.email.$error.email">非法的邮箱地址。</span>--%>
<%--</span>--%>
    <%--</p>--%>

    <%--<p>--%>
        <%--<input  ng-click="submit()"--%>
               <%--ng-disabled="myForm.user.$dirty && myForm.user.$invalid ||--%>
<%--myForm.email.$dirty && myForm.email.$invalid">--%>
    <%--</p>--%>


<%--</form>--%>

<%--<script>--%>
    <%--var app = angular.module('myApp');--%>
    <%--app.controller('validateCtrl', function($scope,$http) {--%>
        <%--var user = $scope.user;--%>
        <%--var email = $scope.email;--%>
        <%--$scope.submit = function(){--%>
            <%--$http.get("/main/oauthLoginServlet")--%>
                <%--.success(--%>
                   <%--function($routeProvider){--%>
                            <%--$routeProvider--%>
                                <%--.when('/',{template:'这是首页页面'})--%>
                                <%--.when('/computers',{template:'这是电脑分类页面'})--%>
                                <%--.when('/printers',{template:'这是打印机页面'})--%>
                                <%--.otherwise({redirectTo:'/'});--%>
                        <%--});--%>

        <%--};--%>

    <%--});--%>

<%--</script>--%>


<%--</body>--%>
<%--</html>--%>