$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
        // $(".center_zhis ").show();
        // $(".conter_main").show()
    }, 1000);
});


$(document).ready(function () {
	//元素添加函数
    function obj_append (name,class_one,obj_big,label,txt) {
    	var name = $("<"+label+"/>");
    	name.addClass(class_one);
    	name.text(txt)
    	obj_big.append(name);	
    };
    function getvl(name) {
        var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
        if(reg.test(location.href)) return decodeURI(RegExp.$2.replace(/\+/g, " "));
        return "";
	};
	var cardName = getvl("cardName")//获取卡的名字
    //获取元素
    var card_name = $(".card_name");//卡的名称
    card_name.text(cardName);//卡的名称


    //向html中添加数据
    function appending (arr) {
        //消费详情
        for (var i = 0;i < arr.length;i++) {
            obj_append("div1","border_2",$("ul"),"li","");
            var li = $("ul li");//消费详情
            var s = i + 1;
            obj_append("div3","width_2 shenglue",$(li[s]),"span",arr[i].spname);
            obj_append("div4","width_3 margin_1 shenglue",$(li[s]),"span",arr[i].cust_code);
            obj_append("div5","width_2 margin_2 shenglue",$(li[s]),"span",arr[i].operate_date);
            obj_append("div6","width_1 margin_2 shenglue",$(li[s]),"span",arr[i].record);
        }
    }


    var shop_name = $(".shop_name");//发卡店铺
    //向html中添加数据
    function appending_shopName (obj) {
        shop_name.text("发售店铺："+obj.car_haopai);//发卡店铺
        var shopLogoImg="<img src='/files/lianMengKa/img/nonepic.png' style='width:236px;height:236px;border-radius:50%;' />";
        if(obj.customer_mobile!=null && obj.customer_mobile!=""){
            shopLogoImg="<img src='http://shop.duduchewang.com/upload/"+obj.sell_code+"/shopimg/"+obj.customer_mobile+"' style='width:236px;height:236px;border-radius:50%;' />";
        }
        $(".circle").html(shopLogoImg);
    }



    var shopcode = $("#shopcode").val();
	var CarId = $("#CarId").val();
	var cardNo = $("#cardNo").val();
	var contextPathStr = $("#contextPathStr").val();
	
	$.ajax({ 
		type    : 'POST',
		url     : '/getCommonAjax',
		data    : {
			fromflag   : "queryLmkXiaoFeiMX",
			shopcode   : shopcode,
			CarId      : CarId,
			cardNo     : cardNo
		},
		success : function(jsonData){

			var json = JSON.parse(jsonData);
			appending(json)
		}
	});
	
	$.ajax({ 
		type    : 'POST',
		url     : '/getCommonAjax',
		data    : {
			fromflag   : "getXmkCardInfo",
			shopcode   : shopcode,
			CarId      : CarId,
			cardNo     : cardNo
		},
		success : function(jsonData){
			var json = JSON.parse(jsonData);
			// appending_shopName(json);
		}
	});

})