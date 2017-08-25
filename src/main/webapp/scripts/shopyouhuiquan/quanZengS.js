$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
    }, 1000);
});

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
			  
