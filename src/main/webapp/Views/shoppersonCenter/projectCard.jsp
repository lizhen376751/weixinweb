<%@ page language="java" pageEncoding="UTF-8"%>

<%String shopcode = (String)request.getSession().getAttribute("shopcode");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>项目卡</title>
		<!--引入CSS-->
		<link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/swiper-3.3.1.min.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/shoppersonCenter/projectCard.css"/>
		<!--引入JS-->
		<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/swiper-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/layout.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/shoppersonCenter/projectCard.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<%--网页加载效果--%>
		<div id="loading">
			<img src="/files/loading2.gif"  alt="loading.." />
		</div>
		<input type="hidden" value="<%=shopcode%>" class="shopCode">
		<div id="wrap" style="display: block;">
			<!--顶部的轮播图-->
			<div class="nav">
				<div class="swiper-container gallery-thumbs swiper-container-horizontal">
			        <div class="swiper-wrapper" style="transform: translate3d(1.84rem, 0px, 0px);transition-duration: 0ms;">
				   <!--<div class="swiper-slide">
			            	<p class="c-title">洗车经济卡</p>
			            	<p class="c-num">NO·203223336</p>
			            </div>
			            <div class="swiper-slide">
			            	<p class="c-title">打蜡经济卡</p>
			            	<p class="c-num">NO·203223333</p>
			            </div>
			            <div class="swiper-slide" >
			            	<p class="c-title">抛光经济卡</p>
			            	<p class="c-num">NO·203223333</p>
			            </div>
			            <div class="swiper-slide" >
			            	<p class="c-title">洗车经济卡</p>
			            	<p class="c-num">NO·203223333</p>
			            </div>
			            <div class="swiper-slide" >
			            	<p class="c-title">修理经济卡</p>
			            	<p class="c-num">NO·203223333</p>
			            </div>-->
					</div>
			        <!--分页器-->
			        <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets"></div>
			    </div>
			</div>  <!--nav结束-->
			<div class="c-content">
				<div class="c-box">
					<table border="0" cellspacing="0" cellpadding="0" class="">
						<thead>
							<tr>
								<td class="td1">项目名称</td>
								<td class="td2">有效日期</td>
								<td class="td3">剩余次数</td>
							</tr>
						</thead>
						<tbody class="listbody">
							<!--<tr>
									<td class="td1">轿车-精洗</td>
									<td class="td2">2017-06-21</td>
									<td class="td3">10</td>
								</tr>-->
						</tbody>
					</table>
					<div class="details">明细</div>
				</div>
			</div>
			<!--返回按钮-->
			<div class="back">
				<div class="b-btn">返回</div>
			</div>
		</div>
		<!--没有项目卡-->
		<div class="n-card" style="display: none;">
			<img src="/files/shoppersonCenter/projectCard/n-project.png" class="n-img"/>
			<p class="n-text">您还没有任何卡~~嘤嘤嘤~~</p>
		</div>
	</body>
</html>
