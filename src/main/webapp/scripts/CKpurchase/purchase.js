$(function(){
	var btn_l = $(".btn_l")//获取换货按钮
	var hh = $(".hh")//获取换货显示的部分
	var btn_r = $(".btn_r")//获取进货按钮
	var jh = $(".jh")//获取进货显示的页面
	btn_l.on("click",function(){
		btn_l.removeClass("border").addClass("bacground");
		btn_r.removeClass("bacground").addClass("border");
		hh.show();
		jh.hide();
	});
	btn_r.on("click",function(){
		btn_r.removeClass("border").addClass("bacground");
		btn_l.removeClass("bacground").addClass("border");
		jh.show();
		hh.hide();
	})
})
