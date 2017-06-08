<%@ page language="java" pageEncoding="UTF-8"%>
<%--<%--%>
	<%--HttpSession Session = request.getSession();--%>
	<%--String plateNumber = (String)Session.getAttribute("plateNumber");--%>
<%String shopcode = (String)request.getSession().getAttribute("shopcode");%>
<%--%>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
		<title>个人中心</title>
		<link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/personCenter/car-brand.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/shoppersonCenter/personalCenter.css"/>
		<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/personCenter/JsBarcode.all.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/personCenter/car-brand.js" type="text/javascript" charset="utf-8"></script>
		<%--<script src="/scripts/personCenter/data.js" type="text/javascript" charset="utf-8"></script>--%>
		<%--<script  type="text/javascript" src="/scripts/main.js"></script>--%>
		<script src="/scripts/shoppersonCenter/personalCenter.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<%--网页加载效果--%>
		<div id="loading">
			<img src="/files/loading.gif"  alt="loading.." />
		</div>
		<input type="hidden" value="<%=shopcode%>" class="shopCode">
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
		<!-------------------------------------------------------------------------------卡包-->
		<div class="cards">
			<!-------------------------------------------------------------------项目卡-->
			<ul class="font_1 project_card"><!-------------------------------------如果有卡显示，如果没卡就不显示-->
				<!---------------------------------------------------------------title-->
				<li class="cards_title">
					<img src="/files/shoppersonCenter/XMK.png"/>
				</li>
				<div class="no_card font_4 color_5 no_project_card">您还没有项目卡~</div>
				<div class="xmk">
					<%--<li>--%>
						<%--<span class="cards_left">卡号</span>--%>
						<%--<span class="cards_right">123456789101234567</span>--%>
					<%--</li>--%>
					<%--<li class="xmk">--%>
						<%--<span class="cards_left">名称</span>--%>
						<%--<span class="cards_right">洗车专用</span>--%>
					<%--</li>--%>
				</div>
				<span class="cards_fg"></span>
			</ul>

			<!-------------------------------------------------------------------充值卡-->
			<ul class="font_1 recharge_cards"><!-------------------------------------如果有卡显示，如果没卡就不显示-->
				<!---------------------------------------------------------------title-->
				<li class="cards_title">
					<img src="/files/shoppersonCenter/CZK.png"/>
				</li>
				<div class="no_card font_4 color_5 no_recharge_cards">您还没有充值卡~</div>
				<div class="czk">
					<%--<li>--%>
						<%--<span class="cards_left">卡号</span>--%>
						<%--<span class="cards_right">123456789101234567</span>--%>
					<%--</li>--%>
					<%--<li>--%>
						<%--<span class="cards_left">名称</span>--%>
						<%--<span class="cards_right">300元&nbsp;充值卡</span>--%>
					<%--</li>--%>
				</div>
				<span class="cards_fg"></span>
			</ul>

			<!-------------------------------------------------------------------代金券-->
			<ul class="font_1 vouchers"><!----------------------------------------如果有卡显示，如果没卡就不显示-->
				<!---------------------------------------------------------------title-->
				<li class="cards_title">
					<img src="/files/shoppersonCenter/DJQ.png"/>
				</li>
				<div class="no_card font_4 color_5 no_vouchers">您还没有代金券~</div>
				<div class="djq">
					<%--<li>--%>
						<%--<span class="cards_left">名称</span>--%>
						<%--<span class="cards_right">精洗</span>--%>
					<%--</li>--%>
					<%--<li>--%>
						<%--<span class="cards_left">内容</span>--%>
						<%--<span class="cards_right">可抵金额80.00</span>--%>
					<%--</li>--%>
				</div>
			</ul>

		</div>   <!-------------------------------联盟卡包结束-->
		<!---------------------------------------------------------------------资金金额------------------>
		<div class="zjje font_1">
			<ul>
				<li>
					<span class="zjje_left">赠送金额剩余：</span>
					<span class="zjje_right zsje">￥0</span>
				</li>
				<li>
					<span class="zjje_left">当前定金金额：</span>
					<span class="zjje_right djje">￥0</span>
				</li>
				<li>
					<span class="zjje_left">会员积分：</span>
					<span class="zjje_right hyjf">0</span>
				</li>
				<li>
					<span class="zjje_left">特种卡：</span>
					<span class="zjje_right tzk">￥0</span>
				</li>
			</ul>
		</div>
		<!--------------------------------------------------------------------底部按钮-->
		<div class="button_bar font_1 color_3">
			退出账号
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
