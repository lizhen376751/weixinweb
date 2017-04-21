(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize =Math.floor(100*(clientWidth / 1080))+ 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document,window);
//---------------------------------------------------------------------------------页面加载完在执行
$(document).ready(function(){
	var basic = $(".basic") //-----------------------------------------------------获取基本信息按钮
	var coverage = $(".coverage") //-----------------------------------------------获取险种信息按钮
	
	basic.on("click",function(){
		coverage.removeClass("color_7").addClass("color_9");
		$(this).removeClass("color_9").addClass("color_7");
	})
	coverage.on("click",function(){
		basic.removeClass("color_7").addClass("color_9");
		$(this).removeClass("color_9").addClass("color_7");
	})
	
	//-----------------------------------------------------------------------------实现滑动效果
	var mySwiper = new Swiper('.swiper-container',{
		prevButton:'.basic',
		nextButton:'.coverage',
		//控制翻页所调用的函数
        onSlideChangeEnd:function(swiper){
        	if(swiper.activeIndex == 0){
        		coverage.removeClass("color_7").addClass("color_9");
				basic.removeClass("color_9").addClass("color_7");
        	}else{
        		basic.removeClass("color_7").addClass("color_9");
					coverage.removeClass("color_9").addClass("color_7");
        	}
        }
	})
	
	//---------------------------------------------------------------------------时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }
	
	
})





















