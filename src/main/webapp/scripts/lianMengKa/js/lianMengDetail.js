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
    		obj_append("div4","width_3 margin_1 shenglue",$(li[s]),"span",arr[i].cust_shopName);
    		obj_append("div5","width_2 margin_2 shenglue",$(li[s]),"span",arr[i].operate_date);
    		obj_append("div6","width_1 margin_2 shenglue",$(li[s]),"span",arr[i].record);
    	}
    }
    
    
    var shop_name = $(".shop_name");//发卡店铺
    //向html中添加数据
    function appending_shopName (obj) {
    	shop_name.text("发售店铺："+obj.sell_shopName);//发卡店铺
    	
    	var shopLogoImg="<img src='../../image/nonepic.png' style='width:236px;height:236px;border-radius:50%;' />";
    	if(obj.shopListImg!=null && obj.shopListImg!=""){
    		shopLogoImg="<img src='http://shop.duduchewang.com/upload/"+obj.sell_code+"/shopimg/"+obj.shopListImg+"' style='width:236px;height:236px;border-radius:50%;' />";
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
			appending(json.data)
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
			appending_shopName(json)
		}
	});
	
	
	
	
	
	
	
	
	
	 /*
    var jsonp = [
                 {
                     "before_num": 0,
                     "car_haopai": "鲁A01234",
                     "card_number": "199009",
                     "current_num": 10,
                     "cust_code": "淄博市精度汽车美容养护中心",
                     "cust_type": "发卡",
                     "customer_mobile": "18600000000",
                     "customer_name": "测试车辆",
                     "operate_date": "2017-01-24",
                     "record": 10,
                     "sale_orders": "XC20170124006",
                     "spname": "232"
                 },
                 {
                     "before_num": 0,
                     "car_haopai": "鲁A01234",
                     "card_number": "199009",
                     "current_num": 10,
                     "cust_code": "淄博市精度汽车美容养护中心 ",
                     "cust_type": "发卡",
                     "customer_mobile": "18600000000",
                     "customer_name": "测试车辆",
                     "operate_date": "2017-01-24",
                     "record": 10,
                     "sale_orders": "XC20170124006",
                     "spname": "空气锤活塞头sasajk"
                 }
             ]

     var name = {
				     "car_haopai": "鲁A01234",
				     "card_name": "2000贵宾卡",
				     "card_number": "199009",
				     "card_type": "项目卡",
				     "customer_mobile": "18600000000",
				     "customer_name": "测试车辆",
				     "operate_id": 44,
				     "sell_code": "0533001",
				     "sell_date": 1485246499360,
				     "shopcode": "CS000  ",
				     "spbm": "CS0001000600020005"
				}
    */
	
	
	
	
	
	
	
	
   
})