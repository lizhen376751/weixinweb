$(function(){
	var kc = $(".kc");//获取课程按钮
	var xxz = $(".xxz");//获取学习中按钮
	var ywc = $(".ywc");//获取已完成按钮
	var wdsc = $(".wdsc");//获取我的收藏按钮
	
	
	
	//-----------------------------------------------------------------------------实现滑动效果
	var mySwiper = new Swiper('.swiper-container',{
		pagination:".swiper-pagination",
		paginationClickable:true,
		//控制翻页所调用的函数
        onSlideChangeEnd:function(swiper){
        	switch (swiper.activeIndex){
        		case 0:kc.addClass("border_B");xxz.removeClass("border_B");ywc.removeClass("border_B");wdsc.removeClass("border_B");
        			break;
    			case 1:xxz.addClass("border_B");kc.removeClass("border_B");ywc.removeClass("border_B");wdsc.removeClass("border_B");
        			break;
    			case 2:ywc.addClass("border_B");xxz.removeClass("border_B");kc.removeClass("border_B");wdsc.removeClass("border_B");
        			break;
    			case 3:wdsc.addClass("border_B");xxz.removeClass("border_B");ywc.removeClass("border_B");kc.removeClass("border_B");
        			break;
        		default:
        			break;
        	}
        	
        }
	})
	//按钮控制显示第几页
	kc.on("click",function(){
		$(".swiper-pagination span").eq(0).trigger("click")
	})
	xxz.on("click",function(){
		$(".swiper-pagination span").eq(1).trigger("click")
	})
	ywc.on("click",function(){
		$(".swiper-pagination span").eq(2).trigger("click")
	})
	wdsc.on("click",function(){
		$(".swiper-pagination span").eq(3).trigger("click")
	})
})
