

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
    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shoppersoncenter",
            servicetype: "personalRightsAndInterests",
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
            var czklist=json.rechargeableCardList;
            // if(json.projectCardList.length==0||json.projectCardList.length==null||xmklist==null){
            if(czklist==null){
                $("#wrap").hide();    //项目卡列表隐藏
                $(".n-card").show();    //没有项目卡的提示显示
            }else{
                for(var i=0;i<czklist.length;i++){
                    czkhtnl+='<div class="swiper-slide">'+
                                    '<p class="c-title">'+czklist[i].cardName+'</p>'+
                                    '<p class="c-num">NO·'+czklist[i].cardNumb+'</p>'+
                              '</div>'
                }
                //列表默认显示第一个的
                for(var j=0;j<xmklist[0].list.length;j++){
                    var time=dateFormat(czklist[0].list[j].currentTimes)
                    czkview+='<tr>'+
                        '<td class="td1">'+czklist[0].list[j].projectName+'</td>'+
                        '<td class="td2">'+time+'</td>'+
                        '<td class="td3">'+czklist[0].list[j].currentTimes+'</td>'+
                        ' </tr>'
                }

                $("#listbody").append(czkview);
                $(".swiper-wrapper").append(czkhtnl);
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

   function funswiper(index){
        $.ajax({
            type: 'POST',
            url: '/shopAjax',
            data: {
                businessType: "shoppersoncenter",
                servicetype: "personalRightsAndInterests",
                customerId: customerId

            },
            async: false,
            success: function (jsonData) {
                $(".listbody").remove();  //去掉原来的项目卡内容
                var json = JSON.parse(jsonData);
                var czkview2="";
                var czklist2=json.rechargeableCardList;

                for(var j=0;j<czklist2[index].list.length;j++){
                    var time=dateFormat(czklist2[index].list[j].currentTimes)
                    czkview2+='<tr>'+
                                    '<td class="td1">'+czklist2[index].list[j].projectName+'</td>'+
                                    '<td class="td2">'+time+'</td>'+
                                    '<td class="td3">'+czklist2[index].list[j].currentTimes+'</td>'+
                              ' </tr>'
                }
                $("#listbody").html("");
                $("#listbody").append(czkview2);


            }


        });
    }
});