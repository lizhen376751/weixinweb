
//判断是展开还是收起
function openindex(obj){
		
	var shouzhitable=$(obj).next();
	var displaymese=shouzhitable.css('display');
	if(displaymese=="none"){
		$(obj).find(".shouzhi_right").html("<img src='/files/CKshouzhimingxi/down.png' />");
		shouzhitable.show();
	}else{
		$(obj).find(".shouzhi_right").html("<img src='/files/CKshouzhimingxi/up.png' />")
		shouzhitable.hide();
	}
	

}
