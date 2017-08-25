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
    <script src="/scripts/shopyouhuiquan/quanlingqu.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	 <div id="loading">
       <img src="/files/shopyouhuiquan/loading2.gif"  alt="loading.." />
     </div>
     <div class="zhezhao" style="text-align: initial;">		
		<div class="bianKuang zsimg">
				<div class="erWeiMa">
					
				</div>
		</div>
		<ul class="zhisUl" style="margin:35% 2% 0 25%">
			<li class="font_6" style="height: 2rem;">1.点击会员中心注册电子会员卡， 即可显示优惠券数量；</li>
			<li class="font_6" style="height: 2rem;">2.请店铺直接进行抵用。</li>
			
		</ul>
	 </div>
     <div class="wrap">
     	 <div class="top_title">
     	 	<ul class="title2 font_3">
     	 		<li >
     	 			<span class="font_5">18.8元精致洗车</span>
     	 			<span class="wermimn"><img onclick="fangda(this.src)" src="/files/shopyouhuiquan/erwm.png"/></span>
     	 		</li>
     	 	</ul>
     	 	<p class="title3">￥59.2</p>
     	 	<div class="shiydiv" onclick="fangdazhis()">立即使用</div>
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
     	 <!--赠送记录开始-->
     	 <div class="ZSdiv">
     	 	<div class="tablediv">
	     	 	<table class="ZStable">
	     	 		<tr width="100%">
	     	 			<td width="35%"><span class="ZSline"></span></td>
	     	 			<td class=" twotd font_3">领取纪录</td>
	     	 			<td width="35%"><span class="ZSline"></span></td>
	     	 		</tr>
	     	 	</table>
     	    </div>	
     	    <div class="ZSmingx ">
     	    	<span class="ZSsapn ZSsapnimg"><img class="ZStouxiang" src="/files/shopyouhuiquan/5.png"></span>
     	    	<span class="ZSsapn ZSleft">
     	    		<ul class="dfasd">
     	    			<li>
     	    				<span class="font_3">一人一车</span>
     	    				<span class="font_4 ZStime">2017-08-22&nbsp;18:08</span>
     	    			</li>
     	    			<li>
     	    				<span class="font_3 ZStime">18.8元精致洗车</span>
     	    			</li>
     	    		</ul>
     	    	</span>
     	    	<span class="wer font_5">￥59.2</span>
     	    </div>
     	    <div class="ZSmingx ">
     	    	<span class="ZSsapn ZSsapnimg"><img class="ZStouxiang" src="/files/shopyouhuiquan/5.png"></span>
     	    	<span class="ZSsapn ZSleft">
     	    		<ul class="dfasd">
     	    			<li>
     	    				<span class="font_3">一人一车</span>
     	    				<span class="font_4 ZStime">2017-08-22&nbsp;18:08</span>
     	    			</li>
     	    			<li>
     	    				<span class="font_3 ZStime">18.8元精致洗车</span>
     	    			</li>
     	    		</ul>
     	    	</span>
     	    	<span class="wer font_5">￥59.2</span>
     	    </div>
     	    
     	 </div><!--赠送记录结束-->
     	 
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