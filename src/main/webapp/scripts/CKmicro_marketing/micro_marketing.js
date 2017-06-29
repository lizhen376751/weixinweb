$(function(){
	var r_jk = $(".r_jk");//获取人健康按钮
	var x_aq = $(".x_aq");//获取行安全按钮
	var c_ll = $(".c_ll");//获取车靓丽按钮
	
	
	
	
	//-----------------------------------------------------------------------------实现滑动效果
	var mySwiper = new Swiper('.swiper-container',{
		pagination:".swiper-pagination",
		paginationClickable:true,
		//控制翻页所调用的函数
        onSlideChangeEnd:function(swiper){
        	switch (swiper.activeIndex){
        		case 0:r_jk.addClass("border_B");x_aq.removeClass("border_B");c_ll.removeClass("border_B");
        			break;
    			case 1:x_aq.addClass("border_B");r_jk.removeClass("border_B");c_ll.removeClass("border_B");
        			break;
    			case 2:c_ll.addClass("border_B");r_jk.removeClass("border_B");x_aq.removeClass("border_B");
        			break;
        		default:
        			break;
        	}
        	
        }
	})
	//按钮控制显示第几页
	r_jk.on("click",function(){
		$(".swiper-pagination span").eq(0).trigger("click")
	})
	x_aq.on("click",function(){
		$(".swiper-pagination span").eq(1).trigger("click")
	})
	c_ll.on("click",function(){
		$(".swiper-pagination span").eq(2).trigger("click")
	})
})
