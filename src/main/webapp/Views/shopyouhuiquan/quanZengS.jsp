<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>电子优惠券</title>
     <link rel="stylesheet" type="text/css" href="/styles/shopyouhuiquan/wxlm.css"/>
     <link rel="stylesheet" type="text/css" href="/styles/shopyouhuiquan/quanlingqu.css"/>
    <!--引入JS-->
    <script  type="text/javascript" charset="utf-8" src="/scripts/jquery-1.12.1.min.js"></script>
    <script  type="text/javascript" charset="utf-8" src="/scripts/layout.js"></script>
    <script src="/scripts/shopyouhuiquan/jquery.qrcode.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/shopyouhuiquan/quanZengS.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	 <div id="loading">
       <img src="/files/shopyouhuiquan/loading2.gif"  alt="loading.." />
     </div>
     <div class="zhezhao">		
		<div class="bianKuang zsimg">
				<div class="erWeiMa">
					
				</div>
		</div>
		<div class="zhis">
			<img  src="/files/shopyouhuiquan/ZHIS.png"/>
		</div>	
		<ul class="zhisUl">
			<li class="font_6">1.点击右上角按钮</li>
			<li class="font_6">2.选择发送给好友</li>
		</ul>
	 </div>
     <div class="wrap">
     	 <div class="top_titleZS">
     	 	<ul class="title2 font_3">
     	 		<li >
     	 			<span class="font_5">18.8元精致洗车</span>
     	 			<span class="wermimn"><img onclick="fangda(this.src)" src="/files/shopyouhuiquan/erwm.png"/></span>
     	 		</li>
     	 	</ul>
     	 	<p class="title3">￥59.2</p>
     	 	<div class="shiydiv" onclick="fangdazhis()">赠送给好友</div>
     	 	<div class="font_3 divfont">有效期  2017年8月22日  至  2017年9月21日</div>
     	 </div>
     	 <p class="font_3 shuom">使用说明：仅限本店；周一至周日 9:00 — 21:00使用</p>
     	 <div  class="bagimg"></div>
     	 <!--<ul class="title4 font_6">
 	 		<li >
 	 			<span >查看详情</span>
 	 			<span style="float: right;">></span>
 	 		</li>
     	 </ul>-->
     	 
     	 <!--店铺信息-->
     	 <ul class="titleshop2 font_3">
 	 		<li >
 	 			<span >北京经典汽车服务公司</span>
 	 			<span class="shopimn"><img src="/files/shopyouhuiquan/gongsi.png"/></span>
 	 		</li>
     	 </ul>
     	 <ul class="titleshop font_3">
 	 		<li >
 	 			<span >联系电话：1880888888</span>
 	 			<span class="shopimn"><img src="/files/shopyouhuiquan/phon.png"/></span>
 	 		</li>
     	 </ul>
     	 <ul class="titleshop font_3">
 	 		<li >
 	 			<span >地址：北京市海淀区知春路888号</span>
 	 			<span class="shopimn"><img style="width: 0.55rem;" src="/files/shopyouhuiquan/address.png"/></span>
 	 		</li>
     	 </ul>
     </div>
      <!--不能领取-->
	<div class="n-card">
		<img src="/files/shopyouhuiquan/n-project.png" class="n-img"/>
		<p class="n-text font_6">不可以重复领取改券哦！</p>  
		<!--优惠券已被领取完，<br/>
                      你貌似错过了一个亿......-->
	</div>
</body>
</html>