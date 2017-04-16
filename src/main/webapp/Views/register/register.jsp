<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>注册</title>
	</head>
	<link rel="stylesheet" type="text/css" href="/styles/register/wxlm.css"/>
	<link rel="stylesheet" type="text/css" href="/styles/register/register.css"/>
	<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/register/register.js" type="text/javascript" charset="utf-8"></script>
	<body>
		<!-----------------------------------------------------------------------顶部图片-->
		<div class="top_img">
			<img src="/files/register/hyzc.png" class="hyzc"/>
			<img src="/files/register/xx.png" class="infor"/>
		</div> <!---------------------------------------------------------------顶部结束-->
		<!-----------------------------------------------------------------------------------主要信息-->
		<div class="main">
			<ul>
				<!---------------------------------------------------------------------------车牌号信息-->
				<li>
					<img src="/files/register/car_num.png" class="logo"/>
					<span class="car_tsk font_4">请输入您的车牌号</span>
					<input type="text" placeholder="请输入您的车牌号" class="input_css_1 car_num font_1"/>
					<img src="/files/register/big_line.png" alt="" class="big_line"/>
				</li>
				<!---------------------------------------------------------------------------账户密码-->
				<li class="margin_top_1">
					<img src="/files/register/password.png" class="logo"/>
					<span class="tsk font_4">密码不能包含空格</span>
					<input type="password" placeholder="请设置6-10的密码" class="input_css_1 font_1 count_password"/>
					<img src="/files/register/big_line.png" alt="" class="big_line"/>
					<img src="/files/register/gbmm.png" class="eye"/>
				</li>
				<!---------------------------------------------------------------------------手机号-->
				<li class="margin_top_1">
					<img src="/files/register/phone.png" class="logo"/>
					<span class="phone_tsk font_4">请输入您的手机号码</span>
					<input type="text" placeholder="请输入您的手机号码" class="input_css_1 font_1 count_phone"/>
					<img src="/files/register/big_line.png" alt="" class="big_line"/>
				</li>
				<!-----------------------------------------------------------------------------验证码-->
				<li class="margin_top_1">
					<img src="/files/register/yzm.png" class="logo"/>
					<span class="yzm_tsk font_4">请输入验证码</span>
					<input type="text" placeholder="请输入验证码" class="input_css_2 font_1 verification_code" />
					<img src="/files/register/little_line.png" alt="" class="little_line"/>
					<span class="yzm font_1 color_3">发送验证码</span>
				</li>
			</ul>
		</div>
		<!--------------------------------------------------------------------------------------底部提交按钮-->
		<div class="footer_button">
			<span class="tjxx font_3 color_3">提交信息</span>
		</div>
		<!-------------------------------------------------------------------------------弹出层-->
		<div class="tcc">
			<!-------------------------------------------------------------------------  用户已注册，且没有密码-->
			<div class="l_box">
				<img src="/files/register/error.png" class="close"/>
				<span class="l_tt font_3">该车牌号已注册</span>
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
				<img src="/files/register/error.png" class="close"/>
				<span class="l_tt font_3">该车牌号已注册</span>
				<span class="l_tj font_3">请直接登录</span>
				<span class="l_tq font_1 color_8">取消请关闭窗口</span>
				<div class="l_qdl font_1 color_3">去登录</div>
			</div>
			<!----------------------------------------------------------------------------统一提示框-->
			<div class="ty">
				<img src="/files/register/error.png" class="close"/>
				<span class="l_tt font_3"></span>
				<!--<span class="l_tj font_3">请注册</span>-->
				<!--<span class="l_tq font_1 color_8">取消请关闭窗口</span>-->
				<div class="tyt font_1 color_3">确定</div>
			</div>
		</div>
	</body>
</html>
