<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String lmcode = request.getParameter("lmcode");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>登录</title>
		<link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/login/css/login.css"/>
		
		<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script  type="text/javascript"  src="/scripts/main.js"></script>
		<script src="/scripts/login/js/login.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
	<%--网页加载效果--%>
	<div id="loading">
		<img src="/files/loading.gif"  alt="loading.." />
	</div>
	<input name="lmcode" type="hidden" value="<%=lmcode%>">
		<!---------------------------------------------------------------------------------头部logo-->
		<div class="logo">
			<img src="/files/login/img/login/logo.png"/>
		</div>
		<!---------------------------------------------------------------------------------用户输入部分-->
		<div class="main_txt">
			<!-------------------------------------------------------------------------------车牌号提示框-->
			<span class="car_tsk font_4"></span>
			<!-----------------------------------------------------------------------------车牌号-->
			<input type="text" placeholder="请输入您的车牌号" class="car_num font_3 input_bg"/>
			<!---------------------------------------------------------------------------------密码提示框-->
			<span class="pasword_tsk font_4"></span>
			<!-----------------------------------------------------------------------------账号密码-->
			<input type="password" placeholder="请输入您的账号密码" class="password_num margin_top_1 font_3 input_bg"/>
			<!-----------------------------------------------------------------------------忘记密码-->
			<a href="#" class="forget_psw font_4">忘记密码</a>
		</div>
		<!--------------------------------------------------------------------------------登录和注册按钮-->
		<div class="bottom_button font_5">
			<div class="button_left">
				<div class="login">登录</div>
			</div>
			<div class="button_right">
				<div class="register">注册</div>
			</div>
		</div>
		<!-------------------------------------------------------------------------------弹出层-->
		<div class="tcc">
			<!-------------------------------------------------------------------------  用户已注册，且没有密码-->
			<div class="l_box">
				<img src="/files/login/img/login/error.png" class="close"/>
				<!--<span class="l_tt font_3">该车牌号已注册</span>-->
				<div class="l_dd font_1 color_6">
					<p class="l_p">我们将发送您的账户密码到</p>
					<p class="mobile_num">156****1885</p>
					<p>您注册账户的手机上</p>
					<p class="color_7 l_bt">请点击确定按钮</p>
					<p class="font_4 l_qx color_8">取消请关闭窗口</p>
				</div>
				<!-------------------------------------------------------------------------------确定按钮-->
				<div class="l_qdan font_1 color_3">确定</div>
			</div>
			
			<!----------------------------------------------------------------------------用户已注册，有密码-->
			<div class="b_box">
				<img src="/files/login/img/login/error.png" class="close"/>
				<span class="l_tt font_3">该车牌号未注册</span>
				<span class="l_tj font_3">请注册</span>
				<span class="l_tq font_1 color_8">取消请关闭窗口</span>
				<div class="l_qdl font_1 color_3">去注册</div>
			</div>
			<!----------------------------------------------------------------------------统一提示框-->
			<div class="ty">
				<%--<img src="/files/login/img/login/error.png" class="close"/>--%>
				<span class="l_tt font_3">车牌号或密码输入错误</span>
				<span class="l_tts font_3">请重新输入!</span>
				<!--<span class="l_tj font_3">请注册</span>-->
				<!--<span class="l_tq font_1 color_8">取消请关闭窗口</span>-->
				<div class="tyt font_1 color_3">确定</div>
			</div>
		</div>

	</body>
</html>
