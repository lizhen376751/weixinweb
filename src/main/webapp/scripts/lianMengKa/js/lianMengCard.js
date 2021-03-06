Date.prototype.format = function(format) {
       var date = {
              "M+": this.getMonth() + 1,
              "d+": this.getDate(),
              "h+": this.getHours(),
              "m+": this.getMinutes(),
              "s+": this.getSeconds(),
              "q+": Math.floor((this.getMonth() + 3) / 3),
              "S+": this.getMilliseconds()
       };
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
       }
       for (var k in date) {
              if (new RegExp("(" + k + ")").test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
              }
       }
       return format;
}



$(document).ready(function () {
	//条形码的样式
	var options = {
        format:"CODE128",
        displayValue:false,
        height:100,
        background:"#31a3ff", 
		lineColor:"#ffffff"//条形码颜色
   };
    //元素添加函数
	function obj_append (name,class_one,obj_big,label,txt,src,attr1,attr2,attr3) {
    	var name = $("<"+label+"/>");
    	name.addClass(class_one);
    	if (txt) {
    		name.text(txt)
    	}
    	obj_big.append(name);
    	if (src) {
    		name[0].src = src;
    	}
    	if (attr1 && attr2 && attr3) {
    		name.attr({
    			card_id:attr1,
    			item_code:attr2,
    			type_flg:attr3
    		})
    	}
    };
    
    //获取body元素
    var body = $("body");
	//向html中添加数据
	function appenging (arr) {
		if(arr!=null && arr.length>0){
			for (var i = 0; i < arr.length;i++) {
				obj_append("div1","lianMeng",body,"div","");
				//卡的头部
				obj_append("div2","box",$($(".lianMeng")[i]),"div","");
				//卡的名字
				obj_append("div3","card_name font_3",$($(".box")[i]),"div",arr[i].card_name);
				//卡的详情
				obj_append("div4","card_content",$($(".box")[i]),"div","");
				//条形码
				obj_append("div5","card_barcode",$($(".card_content")[i]),"div","");
				obj_append("div6","barcode_img",$($(".card_barcode")[i]),"img","");
				//生成条形码
				$('.card_content .card_barcode .barcode_img').JsBarcode(arr[i].card_number,options);//jQuery
				//卡号
				obj_append("div7","card_number font_4",$($(".card_content")[i]),"div","");
				obj_append("div8","",$($(".card_number")[i]),"p","您的卡号为：");
				obj_append("div9","number",$($(".card_number")[i]),"div","");
				obj_append("div10","first",$($(".number")[i]),"span",arr[i].card_number);
				//卡内剩余
				obj_append("div11","card_surplus",$($(".lianMeng")[i]),"div","");
				obj_append("div12","font_4",$($(".card_surplus")[i]),"p","卡内剩余");
				//ul及li
				obj_append("div13","font_4",$($(".card_surplus")[i]),"ul","");
				obj_append("div14","biaoTou",$($("ul")[i]),"li","");
				//第一部分
				obj_append("div16","width_2 border_1 height",$($(".biaoTou")[i]),"span","项目");
				obj_append("div17","width_2 margin_2 border_1 height",$($(".biaoTou")[i]),"span","有效期");
				obj_append("div18","width_2 margin_2 border_1 height",$($(".biaoTou")[i]),"span","次数");
				for (var j = 0;j < arr[i].leftMx.length;j++) {
					var s = j + 1;
					
					var yxrqDateTime = arr[i].leftMx[j].effective_date;
					var newDate = new Date();
					newDate.setTime(yxrqDateTime);
					var yxrqStr = newDate.format('yyyy-MM-dd');
					
					obj_append("div14","border_2",$($("ul")[i]),"li","");
					var li = $($("ul")[i]).find("li")
					obj_append("div16","width_2",$(li[s]),"span",arr[i].leftMx[j].spname);
					obj_append("div17","width_2 margin_2",$(li[s]),"span",arr[i].leftMx[j].effective_date);
					obj_append("div18","width_2 margin_2",$(li[s]),"span",arr[i].leftMx[j].current_num);
					obj_append("div15","width_1 margin_1",$(li[s]),"img","","../img/erweima.png",arr[i].leftMx[j].card_id,arr[i].leftMx[j].item_code,arr[i].leftMx[j].type_flg);
				}
				//添加明细
				obj_append("div19","card_detailed font_3",$($(".card_surplus")[i]),"a","明细");
				$($("a")[i]).attr("href","lianMengDetails.jsp?cardName="+arr[i].card_name+"&cardNo="+arr[i].card_number)
			}
		}else{
			alert("当前车辆无联盟卡信息！");
		}
		
		
	}
		
		
	/*
	var jsonp =[{"car_haopai":"测试车牌"
	,"card_id":70
	,"card_name":"2000贵宾卡"
	,"card_number":"199009"
	,"customer_mobile":""
	,"customer_name":"测试名称"
	,"leftMx":[
	    {"card_id":70,"current_num":8.0,"effective_date":1516350499373,"item_code":"CSXM0001000100010006","spname":"232","type_flg":1}
	   ,{"card_id":70,"current_num":10.0,"effective_date":1516350499373,"item_code":"CS0001000200030001","spname":"空气锤","type_flg":2}
	]},
	{"car_haopai":"测试车牌"
	,"card_id":70
	,"card_name":"2000贵宾卡"
	,"card_number":"199009"
	,"customer_mobile":""
	,"customer_name":"测试名称"
	,"leftMx":[
	    {"card_id":70,"current_num":8.0,"effective_date":1516350499373,"item_code":"CSXM0001000100010006","spname":"232","type_flg":1}
	   ,{"card_id":70,"current_num":10.0,"effective_date":1516350499373,"item_code":"CS0001000200030001","spname":"空气锤","type_flg":2}
	]}]
	*/
	var shopcode = encodeURIComponent($("#shopcode").val());
	var CarId = encodeURIComponent($("#CarId").val());
	var contextPathStr = $("#contextPathStr").val();
	$.ajax({ 
		type    : 'POST',
		url     : contextPathStr+'/servlet/getCommonAjax', 
		data    : {
			fromflag   : "queryLmkInfoList",
			shopcode   : shopcode,
			CarId      : CarId
		},
		success : function(jsonData){
			var json = JSON.parse(jsonData);
			appenging(json.data);
			var imgs = $(".card_surplus ul li img");
			var zheZhaoCeng = $(".zheZhaoCeng");
			var erWeiMa = $(".erWeiMa");
			imgs.on("click",function(){
				var card_ids = $(this).attr("card_id");
				var item_codes = $(this).attr("item_code");
				var type_flgs = $(this).attr("type_flg");
				zheZhaoCeng.css("display","block");
				zheZhaoCeng.bind("touchmove",function(e){ 
					e.preventDefault(); //遮罩层出现后禁止body滑动
				});
				
				$.ajax({
					type:"POST",
					url:contextPathStr+'/servlet/getCommonAjax',
					data:{
						fromflag   : "getXmkQRCode",
						card_id    :card_ids,
						item_code  :item_codes,
						type_flg   :type_flgs
					},
					success : function(jsonData){
						var json = JSON.parse(jsonData);
						erWeiMa.qrcode(json.data);
						erWeiMa.children().css({
							width:"100%",
							height:"100%"
						})
					}
				});
			})
			zheZhaoCeng.on("click",function(){
				$(this).css({
					display:"none"
				})
				erWeiMa.children().remove()
			})
		}
	}); 
	
	
		
})