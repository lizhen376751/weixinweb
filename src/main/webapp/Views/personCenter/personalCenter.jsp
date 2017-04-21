<%@ page language="java" pageEncoding="UTF-8"%>
<%--<%--%>
	<%--HttpSession Session = request.getSession();--%>
	<%--String plateNumber = (String)Session.getAttribute("plateNumber");--%>
<%--%>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
		<title>个人中心</title>
		<link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/personCenter/car-brand.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/personCenter/personalCenter.css"/>
		<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/personCenter/JsBarcode.all.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/personCenter/car-brand.js" type="text/javascript" charset="utf-8"></script>
		<%--<script src="/scripts/personCenter/data.js" type="text/javascript" charset="utf-8"></script>--%>
		<script src="/scripts/personCenter/personalCenter.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<!--top-------------------------------------------------车牌号-->
		<div class="top_header">
			<img src="/files/personCenter/top_bg.png" class="top_bg"/>
			<div class="car_name">
				<img src="/files/personCenter/cp.png" class="car_bg"/>
				<span class="car_num"></span>
			</div>
		</div>
		<!-----------------------------------------------------车主主要信息-->
		<div class="box">
			<ul>
				<!--车辆品牌-->
				<li class="clpp">
					<img src="/files/personCenter/clpp.png" class="title_img"/>
					<div class="text_1 font_4 color_1 clpp_txt">请输入车辆品牌</div>
				</li>
				<!--当前里程-->
				<li class="lcs">
					<img src="/files/personCenter/dqlc.png" class="title_img"/>
					<div class="text_1 font_4 lcs_num">请输入当前里程</div>
				</li>
				<!----------------------------------------------------------------------分割条-->
				<div class="split_bar"></div>
				<!--健康指数-->
				<li class="jkzs">
					<img src="/files/personCenter/jkzs.png" class="title_img"/>
					<div class="text_2 font_4 jkzs_txt">未检测</div>     <!--2-->
					<img src="/files/personCenter/back.png" class="arrow"/><!--蓝色箭头-->
					<!-----------------------------------------------------蓝色分割线-->
					<%--<span class="line"></span>   --%>
				</li>
				<!--保养提醒-->
				<li class="bytx">
					<img src="/files/personCenter/bytx.png" class="title_img"/>
					<div class="text_2 font_1 color_1 bytx_txt"><span>1000km</span></div>
					<img src="/files/personCenter/back.png" class="arrow"/><!--蓝色箭头-->
				</li>
				<!----------------------------------------------------------------------分割条-->
				<div class="split_bar"></div>
			</ul>
		</div>
		<!-----------------------------------------------------------------------------------------------车主主要信息结束-->
		<!-------------------------------------------------------------------------------联盟卡包-->
		<div class="cards">
			<img src="/files/personCenter/lmkb.png" class="cards_title"/>
			<div class="no_card font_4 color_5" style="display: none;">您还没有联盟卡~</div>
			<!-------------------------------------------------------------------联盟卡-->
			<ul style="display: block;"><!-------------------------------------------------------------------------如果有卡显示，如果没卡就不显示-->
				<!---------------------------------------------------------------每一种卡-->
				<%--<li>--%>
					<%--<!-----------------------------------------------------------条形码-->--%>
					<%--<img class="bar_code"/>--%>
					<%--<!----------------------------------------------------------条形码的数字-->--%>
					<%--<div class="bar_num font_1 color_4">0562589464631</div>--%>
					<%--<!-----------------------------------------------------------卡的名称-->--%>
					<%--<div class="cards_name font_3 color_3">888至尊卡</div>--%>
				<%--</li>--%>
				<%--<li>--%>
					<%--<!-----------------------------------------------------------条形码-->--%>
					<%--<img class="bar_code"/>--%>
					<%--<!----------------------------------------------------------条形码的数字-->--%>
					<%--<div class="bar_num font_1 color_4">0562589464631</div>--%>
					<%--<!-----------------------------------------------------------卡的名称-->--%>
					<%--<div class="cards_name font_3 color_3">888至尊卡</div>--%>
				<%--</li>--%>
			</ul>
		</div>   <!-------------------------------联盟卡包结束-->
		<!--------------------------------------------------------------------底部按钮-->
		<div class="button_bar font_1 color_3">
			<div class="button_left">  <!------------------------------------------了解详情按钮-->
				<div class="ljxq">了解详情</div>
			</div>   
			<div class="button_right"> <!---------------------------------------------卡激活按钮-->
				<div class="kjh">卡激活</div>
			</div>  
		</div>
		<!-------------------------------------------------------------------------------------------------------修改车辆品牌的遮罩层-->
		<div class="car_brand">
			<div id="item-container">
				<ul></ul>
			</div>
		</div>
		<!-------------------------------------------------------------------------------------------------------修改当前里程的遮罩层------>
		<div class="mileage">
			<div class="holdder">
				<input type="text" value="" placeholder="请输入里程数" class="font_2"/>
				<div class="qx font_2 color_3">取消</div>   <!----------------------------------------------取消按钮-->
				<div class="qd font_2 color_3">确定</div>   <!----------------------------------------------确定按钮-->
			</div>
		</div>
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
