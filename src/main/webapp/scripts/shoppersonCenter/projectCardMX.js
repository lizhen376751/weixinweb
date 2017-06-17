$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
        // $(".center_zhis ").show();
        // $(".conter_main").show()
    }, 1000);
});

//时间戳转换成日期格式
function dateFormat(val) {
    var now = new Date(val),
        y = now.getFullYear(),
        m = now.getMonth() + 1,
        d = now.getDate();
    var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
    return date.substr(0, 11);
}
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
            obj_append("div3","width_2 shenglue",$(li[s]),"span",arr[i].projectName);
            obj_append("div5","width_2 margin_2 shenglue",$(li[s]),"span",dateFormat(arr[i].transactionDate));
            obj_append("div6","width_1 margin_2 shenglue",$(li[s]),"span",arr[i].transactionTimes);
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



    var shopCode = $("#shopCode").val();
    var cardNumb = $("#cardNumb").val();
    var customerId = $("#customerId").val();
    var plateNumber = $("#plateNumber").val();

    $.ajax({
        type    : 'POST',
        url     : '/pagingquery',
        data    : {
            businessType   : "shoppersoncenter",
            shopCode   : shopCode,
            customerId : customerId,
            cardNumb   : cardNumb,
            plateNumber: plateNumber,
            servicetype:"projectCardMX"

        },
        success : function(jsonData){

            var json = JSON.parse(jsonData);
            appending(json)
        }
    });

   /* $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "getXmkCardInfo",
            shopcode   : shopCode,
            CarId      : CarId,
            cardNo     : cardNo
        },
        success : function(jsonData){
            var json = JSON.parse(jsonData);
            // appending_shopName(json);
        }
    });*/

})