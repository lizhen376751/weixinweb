$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
    }, 1000);
});


 

//点击图片放大按钮
	 function fangda(img_src){
	  	$(".zhezhao").css("display","block");			  	
	  	$("body").css("overflow","hidden");
	  	$("body").bind("touchmove",function(e){ 
				e.preventDefault(); //遮罩层出现后禁止body滑动
			});
	  	$(".zsimg").show();
	  	
	  	$(".zsimg").attr("src",img_src)

	    
	  }
	 function fangdazhis(){
	 	$(".zhezhao").css("display","block");
	 	$("body").bind("touchmove",function(e){ 
				e.preventDefault(); //遮罩层出现后禁止body滑动
			});
	 	$("body").css("overflow","hidden");
	  	$(".zhis").show();
	  	$(".zhisUl").show();
	  	
	
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
  
    $(".erWeiMa ,.erWeiMa2").qrcode("111");
    $(".erWeiMa ,.erWeiMa2").children().css({
        width: "100%",
        height: "100%"
    })
    
	   
	   
	});