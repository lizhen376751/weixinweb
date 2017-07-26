<%@ page language="java" pageEncoding="UTF-8"%>
<%--<%--%>
<%--HttpSession Session = request.getSession();--%>
<%--String plateNumber = (String)Session.getAttribute("plateNumber");--%>
<%--<%String shopcode = (String)request.getSession().getAttribute("shopcode");%>--%>
<%--%>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>个人中心</title>
		<!--引入CSS-->
		<link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
		<%--<link rel="stylesheet" type="text/css" href="/styles/personCenter/car-brand.css"/>--%>
		<link rel="stylesheet" type="text/css" href="/styles/shoppersonCenter/grzx.css"/>
		<!--引入JS-->
		<%--<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>--%>
		<%--<script src="/scripts/personCenter/JsBarcode.all.min.js" type="text/javascript" charset="utf-8"></script>--%>
		<%--<script src="/scripts/personCenter/car-brand.js" type="text/javascript" charset="utf-8"></script>--%>
		<%--<script src="/scripts/shoppersonCenter/grzx.js" type="text/javascript" charset="utf-8"></script>--%>
	</head>
	<body>
		<%--网页加载效果--%>
		<div id="loading">
			<img src="/files/loading2.gif"  alt="loading.." />
		</div>
		<%--<input type="hidden" value="<%=shopcode%>" class="shopCode">--%>
		<!--顶部导航部分-->
		<div class="nav">
			<div class="n-top">
				<div class="n-content">
					<img src="/files/shoppersonCenter/grzx/head.png" class="n-img n-head"/>
					<div class="nt-text car_num">鲁A20323</div>
				</div>
			</div> <!--n-top结束-->
			<div class="n-bottom">
				<div class="n-carnum">
					<img src="/files/shoppersonCenter/grzx/cp.png" class="nl-logo"/>
					<div class="nb-text">阿斯顿·马丁</div>
					<img src="/files/shoppersonCenter/grzx/bj.png" class="nr-bj"/>
				</div>
				<div class="n-kilometre">
					<img src="/files/shoppersonCenter/grzx/gl.png" class="nl-logo"/>
					<div class="xs">
						<div class="nb-num">20000km</div>
						<img src="/files/shoppersonCenter/grzx/bj.png" class="nr-bj"/>
					</div>
					<div class="xg">
						<input type="text" id="gl_num" value="" />
						<div class="n-btn">
							<img src="/files/shoppersonCenter/grzx/ture.png" class="n-image"/>
							<img src="/files/shoppersonCenter/grzx/dd.png" class="n-dd"/>
						</div>
					</div>
				</div>
				<span class="n-line"></span>
			</div>
		</div>  <!--导航栏结束-->
		<!--内容部分-->
		<div class="content">
			<ul>
				<li class="jkzs">
					<img src="/files/shoppersonCenter/grzx/jkzs.png" class="c-logo"/>
					<div class="cl-text">健康指数</div>
					<img src="/files/shoppersonCenter/grzx/jt.png" class="c-jt"/>
					<div class="cr-text jkzs_num">66分</div>
				</li>
				<li class="bytx">
					<img src="/files/shoppersonCenter/grzx/bytx.png" class="c-logo"/>
					<div class="cl-text">保养提醒</div>
					<img src="/files/shoppersonCenter/grzx/jt.png" class="c-jt"/>
					<div class="cr-text bytx_num">20000km</div>
				</li>
				<li class="ct-margin xmk">
					<img src="/files/shoppersonCenter/grzx/xmk.png" class="c-logo"/>
					<div class="cl-text">项目卡</div>
					<img src="/files/shoppersonCenter/grzx/jt.png" class="c-jt"/>
					<div class="cr-text"></div>
				</li>
				<li class="czk">
					<img src="/files/shoppersonCenter/grzx/czk.png" class="c-logo"/>
					<div class="cl-text">充值卡</div>
					<img src="/files/shoppersonCenter/grzx/jt.png" class="c-jt"/>
					<div class="cr-text"></div>
				</li>
				<li class="ct-margin djq">
					<img src="/files/shoppersonCenter/grzx/djq.png" class="c-logo"/>
					<div class="cl-text">代金券</div>
					<img src="/files/shoppersonCenter/grzx/jt.png" class="c-jt"/>
					<div class="cr-text djq_num">剩余2张</div>
				</li>
				<li class="ct-margin">
					<img src="/files/shoppersonCenter/grzx/hydj.png" class="c-logo"/>
					<div class="cl-text">会员等级</div>
					<img src="/files/shoppersonCenter/grzx/jt.png" class="c-jt hide"/>
					<div class="cr-text hydj_num">5级</div>
				</li>
				<li>
					<img src="/files/shoppersonCenter/grzx/hyjf.png" class="c-logo"/>
					<div class="cl-text">会员积分</div>
					<img src="/files/shoppersonCenter/grzx/jt.png" class="c-jt hide"/>
					<div class="cr-text hyjf_num">500分</div>
				</li>
				<li class="ct-margin">
					<div class="l-t">
						<p class="ll-t">赠送金额：</p>
						<p class="lr-t zsje">￥500</p>
					</div>
					<div class="l-t">
						<p class="ll-t">定金金额：</p>
						<p class="lr-t djje">￥500</p>
					</div>
				</li>
			</ul>
		</div>
		<!--退出登录按钮-->
		<div class="b-btn">退出登录</div>
		<!-------------------------------------------------------------------------------------------------------修改车辆品牌的遮罩层-->
		<div class="car_brand">
			<div id="item-container">
				<ul></ul>
			</div>
		</div>
		<!-------------------------------------------------------------------------------------------------------修改当前里程的遮罩层------>
		<%--<div class="mileage">--%>
			<%--<div class="holdder">--%>
				<%--<input type="text" value="" placeholder="请输入里程数" class="font_2"/>--%>
				<%--<div class="qx font_2 color_3">取消</div>   <!----------------------------------------------取消按钮-->--%>
				<%--<div class="qd font_2 color_3">确定</div>   <!----------------------------------------------确定按钮-->--%>
			<%--</div>--%>
		<%--</div>--%>
		<!-------------------------------------------------------------------------------------------------------二层车系信息------>
		<div class="second_carList">
			<!--------------------------------------------------------------------------------------------------------提示车牌信息-->
			<div class="second_cpxx"></div>
			<ul>
				<%--<li>阿斯顿-马丁阿斯顿-马丁阿斯顿-马丁</li>--%>
			</ul>
			<!--------------------------------------------------------------------------------------------------------车系页面的底部按钮-->
			<div class="second_btn">
				<div class="second_back">返回</div>
			</div>
		</div>
		<!-------------------------------------------------------------------------------------------------------二层车系信息------>
		<div class="third_carList">
			<!--------------------------------------------------------------------------------------------------------提示车牌信息-->
			<div class="third_cpxx"></div>
			<ul>
				<%--<li>阿斯顿-马丁阿斯顿-马丁阿斯顿-马丁</li>--%>
			</ul>
			<!--------------------------------------------------------------------------------------------------------车系页面的底部按钮-->
			<div class="third_btn">
				<div class="third_back">返回</div>
			</div>
		</div>
	</body>
</html>
