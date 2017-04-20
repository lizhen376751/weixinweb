<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>联盟卡激活</title>
		<link rel="stylesheet" type="text/css" href="/styles/lianMengCardActivate/wxlm.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/lianMengCardActivate/lianMengCardActivate.css"/>
		
		<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/lianMengCardActivate/lianMengCardActivate.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<!---------------------------------------------------------------------------------头部logo-->
		<div class="logo">
			<img src="/files/lianMengCardActivate/logo.png"/>
		</div>
		<!---------------------------------------------------------------------------------用户输入部分-->
		<div class="main_txt">
			<!-------------------------------------------------------------------------------联盟卡号提示框-->
			<span class="car_tsk font_4"></span>
			<!-----------------------------------------------------------------------------车牌号-->
			<input type="text" placeholder="请输入您的联盟卡号" class="car_num font_3 input_bg"/>
			<!---------------------------------------------------------------------------------激活码提示框-->
			<span class="pasword_tsk font_4"></span>
			<!-----------------------------------------------------------------------------账号密码-->
			<input type="text" placeholder="请输联盟卡的激活码" class="password_num margin_top_1 font_3 input_bg"/>
		</div>
		<!--------------------------------------------------------------------------------激活按钮-->
		<div class="bottom_button font_5">
			<div class="activate">激活</div>
		</div>
		<!-------------------------------------------------------------------------------弹出层-->
		<div class="tcc">
			<!-------------------------------------------------------------------------  用户已注册，且没有密码-->
			<div class="l_box">
				<%--<img src="/files/lianMengCardActivate/error.png" class="close"/>--%>
				<!--<span class="l_tt font_3">您输入的联盟卡号或激活码有误，请重新输入~</span>-->
				<div class="l_dd font_1 color_6">
					<p class="l_p">联盟卡或激活码错误</p>
					<!--<p class="mobile_num">156****1885</p>-->
					<p>请重新输入~</p>
					<p class="color_7 l_bt">请点击确定按钮</p>
					<%--<p class="font_4 l_qx color_8">取消请关闭窗口</p>--%>
				</div>
				<!-------------------------------------------------------------------------------确定按钮-->
				<div class="l_qdan font_1 color_3">确定</div>
			</div>
			
			<!----------------------------------------------------------------------------用户已注册，有密码-->
			<div class="b_box">
				<%--<img src="/files/lianMengCardActivate/error.png" class="close"/>--%>
				<span class="l_tt font_5">联盟卡激活成功</span>
				<!--<span class="l_tj font_3">请注册</span>-->
				<!--<span class="l_tq font_1 color_8">取消请关闭窗口</span>-->
				<div class="l_qdl font_1 color_3">确定</div>
			</div>


			<!----------------------------------------------------------------------------统一提示框-->
			<div class="ty">
				<%--<img src="/files/lianMengCardActivate/error.png" class="close"/>--%>
				<span class="l_tt font_3"></span>
				<!--<span class="l_tj font_3">请注册</span>-->
				<!--<span class="l_tq font_1 color_8">取消请关闭窗口</span>-->
				<div class="tyt font_1 color_3">确定</div>
			</div>
		</div>
	</body>
</html>
