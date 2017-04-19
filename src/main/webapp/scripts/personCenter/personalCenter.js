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
    var clpp = $(".clpp")  //------------------------------------------------------------------车辆品牌
	var car_num = $(".car_num"); //-------------------------------------------------------------获取车牌号信息框
	var clpp_txt = $(".clpp_txt"); //----------------------------------------------------------获取车辆品牌的信息框
	var lcs_num = $(".lcs_num"); //-------------------------------------------------------------获取里程数信息框
	var jkzs_txt = $(".jkzs_txt"); // ------------------------------------------------------------------获取健康指数提示数字-62
	var jkzs = $(".jkzs"); //--------------------------------------------------------------------获取健康指数整个框
    var bytx_txt = $(".bytx_txt"); // ------------------------------------------------------------------获取保养提醒提示数字-1000km
    var bytx = $(".bytx"); //--------------------------------------------------------------------获取保养提醒整个框
	var uls = $(".cards ul");//--------------------------------------------------------------------------------------------获取联盟卡的ul
	var no_card = $(".no_card");//---------------------------------------------------------------------------------当没有联盟卡时 联盟卡部分的显示


    var ljxq = $(".ljxq");//--------------------------------------------------------------------获取了解详情按钮

    var car_brand = $(".car_brand"); //----------------------------------------------------车系品牌遮罩层
    var item_container_li = $("#item-container ul li") //--------------------------------- 获取每一个车牌号


    $.ajax({
        type: 'POST',
        url: '/getCommonAjax2',
        data: {
            fromflag: "personcenter",
            businessType :"personcenter"            },
        async: false,
        success: function (jsonData) {
            json = JSON.parse(jsonData);
            console.log(json);
            car_num.text(json.carHaopai);    //-------------------------------------------------------------动态添加车牌号码

            //------------------------------------------------------------------------------------------------车系品牌添加
            if(json.carBrand != null || json.carModel != null || json.carSeries != null){
                var cltxt = json.carBrand + json.carModel + json.carSeries;
                clpp_txt.text(cltxt);
                clpp_txt.css({
                    color:"#6c6c6c"
                });
            }
            //-----------------------------------------------------------------------------------------------当前里程(保养提醒)判断
            if(json.currentmileage != null && json.currentmileage != ""){
                lcs_num.text(json.currentmileage);
                lcs_num.css({
                    color:"#6c6c6c"
                });
                lcs_num.removeClass("font_4").addClass("font_1")
                bytx_txt.text(json.currentmileage);
                bytx_txt.css({
                    color:"#6c6c6c"
                })
            };
            if(json.point != null && json.point != ""){
                jkzs_txt.text(json.point);//---------------------------------------------------------------------------------健康指数动态添加
                jkzs_txt.css({
                    color:"#f64a88",
                })
                jkzs_txt.removeClass("font_4").addClass("font_2")
			};
            //----------------------------------------------------------------------------------------------------------判断是否有联盟卡
			if(json.lianmengkaXmLeftResultModules && json.lianmengkaXmLeftResultModules.length != 0){
                add_lmCards(json.lianmengkaXmLeftResultModules);
			}else{
				no_card.show();
                uls.hide();
			}



        }

    });
	//条形码的样式


	// JsBarcode(".bar_code","0562589464631",options);

    function add_lmCards(arr) {
        var options = {
            format:"CODE128",
            displayValue:false,
            height:100,
            background:"#fff",
            lineColor:"#4c4c4c"//条形码颜色
        };
		for(var i = 0; i < arr.length;i++){
			var htmls = '';
			htmls += '<li>'+
                '<img class="bar_code bar_code'+i+'"/>'+
                '<div class="bar_num font_1 color_4">'+arr[i].card_number+'</div>'+
                '<div class="cards_name font_3 color_3">'+arr[i].card_name+'</div>'+
            '</li>';
            uls.append(htmls);
            JsBarcode(".bar_code"+i,arr[i].card_number,options);
		}
    }









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
//	--------------------------------------------------------------------------------卡激活跳转
	$(".kjh").on("click",function () {
        window.location.href ="/oauthLoginServlet?flagStr=lianMengCardActivate"
    })

    ljxq.on("click",function () {
		window.location.href = "/oauthLoginServlet?flagStr=lmkInfo"
    })
	
})