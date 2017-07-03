<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>怡人怡车智慧门店</title>
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
		<span class="download"> <a href="http://app.dl.gw.duduchewang.cn"> 免费下载</a></span>
	</div>
	<div class="conter">
		<div class="conter_top conter_css">
			<%--<li class="btnli">课程名称:<input type="button" value="${courseName}" ></li>--%>
			<%--<li class="btnli">图片地址:<input type="button" value="${picture}" ></li>--%>
			<%--<li class="btnli">课程简介:<input type="button" value="${courseBrief}"></li>--%>
			我在<span class="font_blue">怡人怡车网络学堂</span><br/>
			学习了课程<span class="font_blue">《${courseName}》</span><br/>
			并通过了测试！
		</div>
		<div class="conter_network">
			<div class="networkdiv">
				<img class="networkimg" src="${picture}">
				<div class="jianjie">${courseBrief}</div>
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