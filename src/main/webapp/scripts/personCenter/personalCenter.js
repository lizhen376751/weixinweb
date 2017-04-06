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

$(document).ready(function(){
	//条形码的样式
	var options = {
        format:"CODE128",
        displayValue:false,
        height:100,
        background:"#fff", 
		lineColor:"#4c4c4c"//条形码颜色
   };

	JsBarcode(".bar_code","0562589464631",options);
	
	//----------------------------------------------------------------获取车辆品牌部分元素
	app.ItemList = function (data) {
		var list = [];
		var map = {};
		var html;
		html = data.map(function (item) {
			var i = item.lastIndexOf(' ');
			var en = item.slice(0, i);
			var cn = item.slice(i + 1);
			var ch = en[0];
			//console.log(map[ch])
			if (map[ch]) {
				return '<li class="car_list">'+cn+'</li>'
			} else {
				map[ch] = true;
				return '<li data-cw="'+ch+'" class="ch">'+ch+'</li><li data-ch="'+ch+'" class="car_list">'+cn+'</li>'
			}
			
		}).join('');
		var elItemList = document.querySelector('#item-container ul');
		var a = document.querySelector('#item-container');
		elItemList.innerHTML = html;
		return {
			gotoChar: function (ch) {
				if (ch === '*') {
					console.log(elItemList.scrollTop)
					a.scrollTop = 0;
				} else if (ch === '#') {
					a.scrollTop = elItemList.scrollHeight;
				} else {
					var target = elItemList.querySelector('[data-cw="'+ch+'"]');//检索的目标
					if (target) {
						target.scrollIntoView();
					}
				}
			}
		}
	}
	app.main = function () {
		var itemList = app.ItemList(app.data);
		new IndexSidebar().on('charChange', itemList.gotoChar);
		
	}
	app.main()
	var clpp = $(".clpp")  //------------------------------------------------------------------车辆品牌
	var clpp_txt = $(".clpp_txt"); //-----------------------------------------------------------奥迪A6
	var car_brand = $(".car_brand"); //----------------------------------------------------遮罩层
	var item_container_li = $("#item-container ul li") //--------------------------------- 获取每一个车牌号
	
	$(".index-sidebar-container").css("display","none")//------------------------------默认隐藏
	clpp.on("click",function(){
		car_brand.show()
		$(".index-sidebar-container").css("display","block")
	})
	item_container_li.on("click",function(){
		car_brand.hide();
		$(".index-sidebar-container").css("display","none")
	})
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//----------------------------------------------------------------获取里程数部分元素
	var lcs = $(".lcs")     //--------------------------------------------------------里程数元素
	var lcs_num = $(".lcs .lcs_num")  //----------------------------------------------显示的里程数
	var mileage = $(".mileage")   //--------------------------------------------------修改里程数的遮罩层元素
	var mileage_num = $(".mileage .holdder input")  //--------------------------------修改里程数中遮罩层里的input
	var qx = $(".mileage .holdder .qx");    //---------------------------------------- 修改里程数中遮罩层里的取消按钮
	var qd = $('.mileage .holdder .qd');    //---------------------------------------- 修改里程数中遮罩层里的确定按钮
	
	
	
	//-----------------------------用户修改输入事件
   	lcs.on("click",function(e){
   		mileage.show();
   		mileage_num.val("");
   		e.preventDefault();
   	})
   	//----------------------------------------------------------------------取消点击
   	qx.on("click",function(){
   		mileage.hide();
   		mileage_num.val("")
   	})
   	//----------------------------------------------------------------------确定点击
	qd.on("click",function(){
		var reg = /^[1-9]\d*$|^0$/; // 判断是否为数字的正则
		if(reg.test(mileage_num.val()) == true){
			var txt = mileage_num.val()+"km";
			lcs_num.text(txt);
			mileage.hide()
		}else{
			
			alert("请输入正确的里程数~");
			mileage_num.val("")
		}
		
	})
    //---------------------------------------------------------------------------输入框的聚焦和失焦
	mileage_num.on("focus",function(){
		$(this).css({
			"border":"1px #58b5ff solid"
		})
	}).on("blur",function(){
		$(this).css({
			"border":"1px #B3B3B3 solid"
		})
	})
	
	
	
	
})