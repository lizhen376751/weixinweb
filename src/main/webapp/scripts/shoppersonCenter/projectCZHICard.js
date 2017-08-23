
$(window).load(function () {
    setTimeout(function () {
        $("#loading").hide();
    }, 1000);
});
$(function(){

    var shopCode = $(".shopCode"); //----------------------------------------------------------店铺编码

    function getvl(name) {
        var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
        if(reg.test(location.href)) return decodeURI(RegExp.$2.replace(/\+/g, " "));
        return "";
    };

    var customerId=getvl("customerId")    //获取地址栏传参客户ID
    var plateNumber = $("#plateNumber").val();
    var indexsiper=0;   //swiper默认显示的是第一条项目卡的list
    //请求多少张项目卡的请求
    //转化时间戳
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }

    //上面轮播所有充值卡的请求
    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shoppersoncenter",
            servicetype: "queryUserRechargeableCardList",
            customerId: customerId
            // plateNumb: car_num.text()
            /*shopCode: "0533001",
             customerId : 28763,
             plateNumb: "闽A12121"*/
        },
        async: false,
        success: function (jsonData) {
           var json = JSON.parse(jsonData);
             console.log(json);
            var czkhtnl="";
            var czkview="";
            var  czkmingxi="";//充值卡明细跳转
          //  var czklist=json.rechargeableCardList;
            // if(json.projectCardList.length==0||json.projectCardList.length==null||xmklist==null){
            if(json==null||json.length==0||json.length==""){
                $("#wrap").hide();    //项目卡列表隐藏
                $(".n-card").show();    //没有项目卡的提示显示
            }else{
                for(var i=0;i<json.length;i++){
                    czkhtnl+='<div class="swiper-slide">'+
                                    '<p class="c-zong c-title">'+json[i].cardName+'</p>'+
                                    '<p class="c-zong c-num">NO·'+json[i].cardNumb+'</p>'+
                                    '<p class=" c-zong c-money">剩余金额:'+json[i].residualAmount+'</p>'+
                                    '<p class="c-zong  c-time">'+dateFormat(json[i].validDate)+'&nbsp;&nbsp;</p>'+
                              '</div>'
                }
                //列表默认显示第一个的明细列表
                $.ajax({
                    type    : 'POST',
                    url     : '/pagingquery',
                    data    : {
                        businessType   : "shoppersoncenter",
                        shopCode   : shopCode.val(),
                        customerId : customerId,
                        cardNumb   : json[0].cardNumb,
                        plateNumber: plateNumber,
                        servicetype:"rechargeableCardMX"

                    },
                    success : function(jsonData){
                        var arr = JSON.parse(jsonData);
                        //console.log(arr)
                        for (var i = 0;i < arr.length;i++) {
                            for(var n = 0;n <arr[i].rechargeableCardContentList.length;n++) {
                                czkview += '<tr>'+
                                    '<td class="td1">'+arr[i].rechargeableCardContentList[n].itemOrProductName+'</td>'+
                                    '<td class="td2">'+dateFormat(arr[i].rechargeableCardContentList[n].transactionTime)+'</td>'+
                                    '<td class="td3">￥'+arr[i].rechargeableCardContentList[n].transactionAmount+'</td>'+
                                    ' </tr>';
                            }

                        }
                        $("#listbody").append(czkview);
                    }
                });


               // czkmingxi+='<div class="details" id="details" onclick="changeCardCZ('+czklist[0].cardNumb+',\''+shopCode.val()+'\','+customerId+')">明细</div>'   //默认显示第一张项目卡的明细


                $(".swiper-wrapper").append(czkhtnl);
               // $(".listtable").after(czkmingxi);
            }



        }


    });
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        slidesPerView: 1.5,
        centeredSlides: true,
        paginationClickable: true,
        onSlideChangeEnd:function(swiper){    //获取当前swiper的索引值
            //alert(swiper.activeIndex)
            funswiper(swiper.activeIndex);
        },
        spaceBetween: 30

//      slideToClickedSlide: true,

    });
//轮播的时候下面的列表动态变化函数
   function funswiper(index){
    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shoppersoncenter",
            servicetype: "queryUserRechargeableCardList",
            customerId: customerId

        },
        async: false,
        success: function (jsonData) {
            $(".listbody").remove();  //去掉原来的项目卡内容
            var json = JSON.parse(jsonData);
            var czkview2="";
            //var czklist2=json.rechargeableCardList;
            //轮播滑动时调用相应的详情请求
            $.ajax({
                type    : 'POST',
                url     : '/pagingquery',
                data    : {
                    businessType   : "shoppersoncenter",
                    shopCode   : shopCode.val(),
                    customerId : customerId,
                    cardNumb   : json[index].cardNumb,
                    plateNumber: plateNumber,
                    servicetype:"rechargeableCardMX"

                },
                success : function(jsonData){
                    var arr = JSON.parse(jsonData);
                   // console.log(arr)
                    for (var i = 0;i < arr.length;i++) {
                        for(var n = 0;n <arr[i].rechargeableCardContentList.length;n++) {
                            czkview2 += '<tr>'+
                                '<td class="td1">'+arr[i].rechargeableCardContentList[n].itemOrProductName+'</td>'+
                                '<td class="td2">'+dateFormat(arr[i].rechargeableCardContentList[n].transactionTime)+'</td>'+
                                '<td class="td3">￥'+arr[i].rechargeableCardContentList[n].transactionAmount+'</td>'+
                                ' </tr>';
                        }

                    }
                    $("#listbody").html("");
                    $("#listbody").append(czkview2);
                }
            });
           // cakmingxi2+='<div class="details" id="details" onclick="changeCardCZ('+czklist2[index].cardNumb+',\''+shopCode.val()+'\','+customerId+')">明细</div>'
           // $("#details").remove();
          //  $(".listtable").after(cakmingxi2);

        }


    });

}
//返回键按钮  返回到个人中心页面
$(".b-btn").click(function(){
    window.location.href = "shopweixinMenuServlet?shopcode="+shopCode.val()+"_shoppersoncenter";      //"/shopweixinMenuServlet?flagStr=shoppersoncenter&shopcode=" + shopCode.val()
})
});

//充值卡跳转页面
// function changeCardCZ(a,b,c) {
//     window.location.href="/shopweixinServlet?serviceType=rechargeableCardMX&cardNumb="+a+"&shopCode="+b+"&customerId="+c;
// }
