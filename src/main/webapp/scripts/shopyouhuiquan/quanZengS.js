$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
    }, 1000);
});
$(function(){

	//在赠送给好友点击赠送按钮是调用的微信端的函数
    lmwxsq();
    wx.onMenuShareAppMessage({
        title: '', // 分享标题
        desc: '', // 分享描述
        link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: '', // 分享图标
        type: '', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    //在赠送给好友点击赠送按钮是调用函数结束

})
//点击图片放大按钮
			 function fangda(img_src){
			  	$(".zhezhao").css("display","block");
			  //	$(".zhezhao").siblings().css("display","none");
			  	
			  	$("body").css("overflow","hidden");
			  	$(".zsimg").show();		
			  	$(".zsimg").attr("src",img_src);
			  	$("body").bind("touchmove",function(e){ 
				e.preventDefault(); //遮罩层出现后禁止body滑动
			});
			    
			  }
			 function fangdazhis(){
			 	$(".zhezhao").css("display","block");
			 	$("body").css("overflow","hidden");
			  	$(".zhis").show();
			  	$(".zhisUl").show();
			  	$("body").bind("touchmove",function(e){ 
				e.preventDefault(); //遮罩层出现后禁止body滑动
			});
			  	
			
			 }
			
			$(function(){
				//图片弹出层
              $(window).scroll(function(){
		            $('.zhezhao').css('top', $(document).scrollTop());
		      });
			  $(".zsimg ,.zhezhao ,.zhis ,.zhisUl ,.zhis img ,.zhisUl li").click(function(){
			  	   // $(".zhezhao").siblings().css("display","block");
			        $(".zhezhao").hide();
			        $(".zhis").hide();
			  	    $(".zhisUl").hide();
			  	    $(".zsimg").hide();
			        $("body").css("overflow","auto");
			   });
			   
			   //生成二维码
  
			    $(".erWeiMa").qrcode("111");
			    $(".erWeiMa").children().css({
			        width: "100%",
			        height: "100%"
			    })
			});


