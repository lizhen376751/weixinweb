<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>怡人怡车</title>
    <link rel="stylesheet" type="text/css" href="/styles/weixinfenxiang/css/share.css"/>
	<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/main.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/weixinfenxiang/js/share.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<%--网页加载效果--%>
<div id="loading">
	<img src="/files/loading2.gif"  alt="loading.." />
</div>
	<div class="zhezhao">		
		   <img class="zsimg" src=""/>
    </div>
	<%--<center class="top_title">怡人怡车</center>--%>
	<div class="logo">
		<img class="log_img" src="/files/weixinfenxiang/img/LOGO.png">
		<span class="download"><a href="http://app.dl.gw.duduchewang.cn"> 免费下载</a></span>
	</div>
	<div class="conter">
		<%--<li class="btnli">创建者:<input type="button" value="${createName}" ></li>--%>
		<%--<li class="btnli">头像:<input type="button" value="${headImg}" ></li>--%>
		<%--<li class="btnli">文章类型:<input type="button" value="${workTypeName}"></li>--%>
		<%--<li class="btnli">时间:<input type="button" value="${interval}" ></li>--%>
		<%--<li class="btnli">内容:<input type="button" value="${details}" ></li>--%>
		<%--<li class="btnli">评论数:<input type="button" value="${commentNum}"></li>--%>
		<%--<li class="btnli">点赞数:<input type="button" value="${likeNum}" ></li>--%>
		<%--<li class="btnli">文章图片集合:<input type="button" value="${imgs}" ></li>--%>

		<div class="conter_top conter_css">
			我分享的<br/>
			<span class="font_blue font_name" id="font_name">${createName}</span>在
			<span class="font_blue">怡人怡车同行圈</span><br/>
			的一条动态！
		</div>
		<div class="conter_seconed conter_css">
			<div class="seconed_p">
				<div class="head_img" style="float:left">
					<img  id="head_img" src="${headImg}"/>
				</div>
				<ul class="seconed_div" style="float:left">
						<li class="li_name" id="li_name">${createName} </li>
						<li class="li_time" >
							<font id="li_time">${interval}</font>
						    <font class="li_leibie" id="li_type">${workTypeName}</font>
						</li>
				</ul>
			</div>
		
			<p class="p_miaos">
				${details}
			</p>
			<ul class="ul_miaos" id="miaoshu">
				<c:forEach items="${imgs}" varStatus="i" var="item" >
					<li><img src="${item.imgURL}" onclick="fangda(this.src)"/>
				</c:forEach>
				<!--<li><img src="img/img.png" onclick="fangda(this.src)"/></li>
				<li><img src="img/img.png" onclick="fangda(this.src)"/></li>
				<li><img src="img/img.png" onclick="fangda(this.src)"/></li>
				<li><img src="img/img.png" onclick="fangda(this.src)"/></li>-->
			</ul>
			<hr class="borderhr" />
			<div class="plun">
				<span><img class="zan" src="/files/weixinfenxiang/img/zan.png"><font class="li_leibie" id="zan">${likeNum}</font></span>
				<span><img class="ping" src="/files/weixinfenxiang/img/ping.png"><font class="li_leibie" id="ping">${commentNum}</font></span>
			</div>
			
		
		</div>
		<div class="conter_third ">
			<ul class="conter_miaos">
				<li style="float:left;width: 60%;">
					<p class="p1" > 
						<img  src="/files/weixinfenxiang/img/111.png">
						<span class="font1">长按识别二维码</span>
					</p>
					<p class="p2">关注怡人怡车</p>
					<p class="p3">
						怡人怡车智慧门店管理平台，为汽车服务门店提供智能化管理的整体解决方案
					</p>
				</li>
				<li class="p4" style="float:right">
					<img  src="/files/weixinfenxiang/img/wei.png"/>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>