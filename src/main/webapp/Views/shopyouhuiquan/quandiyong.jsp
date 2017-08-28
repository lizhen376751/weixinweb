<%@ page import="com.dudu.soa.basedata.shopinfo.module.ShopInfo" %>
<%@ page import="com.dudu.soa.weixindubbo.electroniccoupon.module.WeiXinCouponInfo" %>
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
	<script>
		$(function(){

            //时间戳转换成日期格式
            function dateFormat(val) {
                var now = new Date(val),
                    y = now.getFullYear(),
                    m = now.getMonth() + 1,
                    d = now.getDate();
                var date = y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
                return date.substr(0, 11);
            }




            // 优惠券返回详情
            var wxWeiXinCouponInfo="${wxWeiXinCouponInfo}";
            console.log(wxWeiXinCouponInfo);
            var diXiaoJinE="${wxWeiXinCouponInfo.diXiaoJinE}"   //抵消金额
            var couponStartTime=dateFormat("${wxWeiXinCouponInfo.couponStartTime}");  // 领取优惠券有效日期
			var couponEndTime=dateFormat("${wxWeiXinCouponInfo.couponEndTime}");   // 领取优惠券失效日期
            var details="${wxWeiXinCouponInfo.details}"    // 使用详情
            var couponName="${wxWeiXinCouponInfo.couponName}"    //  优惠券名称
            var couponCode="${wxWeiXinCouponInfo.couponCode}"    // 优惠券编码

            //生成二维码

            $(".erWeiMa ,.erWeiMa2").qrcode(couponCode);
            $(".erWeiMa ,.erWeiMa2").children().css({
                width: "100%",
                height: "100%"
            })


            console.log(couponStartTime);
            $("#diXiaoJinE").text(diXiaoJinE);
            $("#couponStartTime").text("有效期"+couponStartTime+"至"+couponEndTime);
            $("#details").text("使用说明:"+details);
            $("#couponName").text(couponName);



            // 店铺信息返回详情
				var shopInfo = "${shopInfo}";
               //  console.log(shopInfo);
				var shopname="${shopInfo.shopName}"   //店铺名称
                var address="${shopInfo.address}"   //店铺地址
                var tel="${shopInfo.tel}"   //店铺电话

				$("#shopname").text(shopname);
                $("#address").text("地址："+address);
                $("#tel").text("联系电话："+tel);
        })
	</script>
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
     	 			<span class="font_5" id="couponName"></span>
     	 			<span class="wermimn"><img onclick="fangda(this.src)" src="/files/shopyouhuiquan/erwm.png"/></span>
     	 		</li>
     	 	</ul>
     	 	<p class="title3" id="diXiaoJinE"></p>
     	 	<div class="shiydiv" onclick="fangdazhis()">立即使用</div>
     	 	<div class="font_3 divfont" id="couponStartTime"></div>
     	 </div>
     	 <p class="font_3 shuom" id="details"></p>
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
 	 			<span id="shopname"></span>
 	 			<span class="shopimn"><img src="/files/shopyouhuiquan/gongsi.png"/></span>
 	 		</li>
     	 </ul>
     	 <ul class="titleshop font_3">
 	 		<li >
 	 			<span id="tel"></span>
 	 			<span class="shopimn"><img src="/files/shopyouhuiquan/phon.png"/></span>
 	 		</li>
     	 </ul>
     	 <ul class="titleshop font_3">
 	 		<li >
 	 			<span id="address"></span>
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