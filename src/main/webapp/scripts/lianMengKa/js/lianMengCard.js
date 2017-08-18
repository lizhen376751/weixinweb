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
        // background:"#31a3ff",
		lineColor:"#000"//条形码颜色
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
                var htmlemle='';
                var htmlist='';
                var htmlijhuo='';

                for (var j = 0;j < arr[i].leftMx.length;j++) {
                    var yxrqDateTime = arr[i].leftMx[j].effectiveDate;
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
                    if(arr[i].leftMx[j].currentNum != 0 && arr[i].leftMx[j].currentNum != ""){
                        if(oldDate >= newDate){
                            htmlist+='<li class="border_2">' +
									      '<span class="width_2 font_4">'+arr[i].leftMx[j].spName+'</span>'+
                                          '<span class="width_2 font_4 margin_2">'+dates+'</span>'+
                                          '<span class="width_2 font_4 margin_2">'+arr[i].leftMx[j].currentNum+'</span>'+
                                          '<img class="width_1 margin_1"    src="/files/lianMengKa/img/erweima.png" card_id="'+arr[i].leftMx[j].cardId+'"  item_code="'+arr[i].leftMx[j].itemCode+'"  type_flg="'+arr[i].leftMx[j].typeFlg+'">'+

								     '</li>'
                        }
                    }
                }
                //判断是否要开工单
                $.ajax({
                    type: 'POST',
                    url: '/getCommonAjax?fromflag=selfbilling&cardNo='+arr[i].cardId,
                    data: {

                    },
                    success: function (jsonData) {
                        var arr = JSON.parse(jsonData);
                       alert(arr.statusEnum)
                        if(arr.statusEnum=="BLANK"){

                            //允许开单
                            htmlijhuo+='<a class="card_detailed font_3" href="/lmInternalJump?business=selfbilling">开单激活</a>'

                        }

                    }
                });

                htmlemle+='<div class="lianMeng">' +
                               '<div class="box">' +
						          '<div class="card_name font_3">'+arr[i].cardName+'</div>'+
                                  '<div class="card_content">' +
						               '<div class="card_barcode">'+
						                  '<img class="barcode_img jscode_img"    src=" ">'+
					                    '</div>'+
                                        '<div class="card_number font_4">'+
                                               '<p>您的卡号为：</p>'+
												'<div class="number">'+
												   '<span class="first">'+arr[i].cardNumber+'</span>'+
												'</div>'+
									   '</div>'+
                                    '</div>'+
                                  '</div>'+
								  '<div class="card_surplus">'+
								      '<p class="font_4">卡内剩余</p>'+
								      '<ul>'+
						                  '<li class="biaoTou">'+
					                             '<span class="width_2 font_4 border_1 height">项目'+
												 '</span><span class="width_2 font_4 margin_2 border_1 height">有效期'+
					                             '</span><span class="width_2 font_4 margin_2 border_1 height">次数</span>'+
										   '</li>'+
                                             htmlist+
					                  '</ul>'+
						              '<a class="card_detailed font_3" href="/oauthLoginServlet?flagStr=lianMengDetails&cardName="'+arr[i].cardName+'"&cardNo="'+arr[i].cardNumber+'">明细</a>'+
                                       htmlijhuo+
							     '</div>'+
                                  '<span class="fjt"></span>'+    //添加每一张卡与卡之间的分界条
					      '</div>'
                $("body").append(htmlemle);
				$(".jscode_img").JsBarcode(arr[i].cardNumber,options);

                //------------------------------------------------------------------------判断当前联盟卡里是否还有项目
                var cars_lis = $($("ul")[i]).find("li");
                if(cars_lis.length == 1){
                    cars_lis.parents(".lianMeng").hide();
                }
				//添加明细


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