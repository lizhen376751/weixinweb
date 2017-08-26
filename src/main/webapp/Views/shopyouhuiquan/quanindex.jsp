<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String openid = (String)request.getAttribute("openid");  //微信openid
	String shopCode = (String)request.getAttribute("shopCode"); // 店铺编码
	String userNum = (String)request.getAttribute("userNum");  //本人可使用数目
	String forwardNum = (String)request.getAttribute("forwardNum");  //可分享数目
	String id = (String)request.getAttribute("id");  //优惠券唯一标识ID
	String identifying = (String)request.getAttribute("identifying");  //标识   单一：only  更多：more
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>电子优惠券</title>
     <link rel="stylesheet" type="text/css" href="/styles/shopyouhuiquan/wxlm.css"/>
     <link rel="stylesheet" type="text/css" href="/styles/shopyouhuiquan/quanindex.css"/>
    <!--引入JS-->
    <script  type="text/javascript" charset="utf-8" src="/scripts/jquery-1.12.1.min.js"></script>
    <script  type="text/javascript" charset="utf-8" src="/scripts/layout.js"></script>
	<script src="/scripts/wxsq.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/shopyouhuiquan/quanindex.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	 <div id="loading">
       <img src="/files/shopyouhuiquan/loading2.gif"  alt="loading.." />
     </div>
     <div class="wrap">
     	<ul>
     		<li class="use_li">
     			<div class="use_div">
     				<span class="usenum font_3"><%=userNum%></span>
     			</div>
     			<span class="font_6 miaoshu"> 本人可使用数量</span>
     		</li>
     		<li class="line"></li>
     		<li class="zs_li">
     			<div class="zsongnum_div">
     				<span class="usenum font_3"><%=forwardNum%></span>
     			</div>
     			<span class="font_6 miaoshu"> 可赠送好友数量</span>
     		</li>
     	</ul>
     </div>
	 <input name="openid" id="openid" type="hidden" value="<%=openid%>">
	 <input name="shopCode" id="shopCode" type="hidden" value="<%=shopCode%>">
	 <input name="userNum" id="userNum" type="hidden" value="<%=userNum%>">
	 <input name="forwardNum" id="forwardNum" type="hidden" value="<%=forwardNum%>">
	 <input name="id" id="wyid" type="hidden" value="<%=id%>">
	 <input name="identifying" id="identifying" type="hidden" value="<%=identifying%>">

</body>
</html>