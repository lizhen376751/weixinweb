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
};
$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
        // $(".center_zhis ").show();
        // $(".conter_main").show()
    }, 1000);
});



$(document).ready(function () {
    //时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }
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
                var s = 0;
				for (var j = 0;j < arr[i].leftMx.length;j++) {
					var yxrqDateTime = arr[i].leftMx[j].effective_date;
                    var dates = dateFormat(yxrqDateTime);
                    var cc = dates.replace(/-/ig,"/");
					var news = new Date();
                    var e = news.getFullYear();
                    var f = news.getMonth()+1;
                    var g = news.getDate();
                    var h = e + "/" + f + "/" +g;
                    // console.log(h);
                    var newDate = new Date(h);
                    var oldDate = new Date(cc);
					if(arr[i].leftMx[j].current_num != 0 && arr[i].leftMx[j].current_num != ""){
                        if(oldDate >= newDate){
                            obj_append("div14","border_2",$($("ul")[i]),"li","");
                            s++;
                            var li = $($("ul")[i]).find("li");
                            //obj_append("div15","width_1",$(li[s]),"span",s);
                            obj_append("div16","width_2",$(li[s]),"span",arr[i].leftMx[j].spname);
                            obj_append("div17","width_2 margin_2",$(li[s]),"span",dates);
                            obj_append("div18","width_2 margin_2",$(li[s]),"span",arr[i].leftMx[j].current_num);
                            obj_append("div15","width_1 margin_1",$(li[s]),"img","","/files/lianMengKa/img/erweima.png",arr[i].leftMx[j].card_id,arr[i].leftMx[j].item_code,arr[i].leftMx[j].type_flg);
                        }
					}
				}
                //------------------------------------------------------------------------判断当前联盟卡里是否还有项目
                var cars_lis = $($("ul")[i]).find("li");
                if(cars_lis.length == 1){
                    cars_lis.parents(".lianMeng").hide();
                }
				//添加明细
				obj_append("div19","card_detailed font_3",$($(".card_surplus")[i]),"a","明细");
				$($("a")[i]).attr("href","/oauthLoginServlet?flagStr=lianMengDetails&cardName="+arr[i].card_name+"&cardNo="+arr[i].card_number)
			}
		}else{
			alert("当前车辆无联盟卡信息！");
		}
		
		
	}
		

	var CarId =$("#CarId").val();
	var contextPathStr = $("#contextPathStr").val();
	$.ajax({ 
		type    : 'POST',
		url     : '/getCommonAjax',
		data    : {
			fromflag   : "queryLmkInfoList",
			CarId      : CarId
		},
		success : function(jsonData){
			var json = JSON.parse(jsonData);
			appenging(json);
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
					url:'/getCommonAjax',
					data:{
						fromflag   : "getXmkQRCode",
						card_id    :card_ids,
						item_code  :item_codes,
						type_flg   :type_flgs
					},
					success : function(jsonData){
                        erWeiMa.children().remove();
						var json = JSON.parse(jsonData);
						erWeiMa.qrcode(json);
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