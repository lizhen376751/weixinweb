

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
    var galleryThumbs = new Swiper('.gallery-thumbs', {
        pagination: '.swiper-pagination',
        spaceBetween: 10,
        centeredSlides: true,
        slidesPerView: 1.5,
        touchRatio: 0.2,
        paginationClickable :true,
        onSlideChangeEnd:function(swiper){    //获取当前swiper的索引值
            alert(swiper.activeIndex)
            funswiper(swiper.activeIndex);
        },
//      slideToClickedSlide: true,
        loop:true
    });
    var customerId=getvl("customerId")    //获取地址栏传参客户ID
    var indexsiper=1;   //swiper默认显示的是第一条项目卡的list
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
            //console.log(json);
            var xmkhtnl="";
            var xmkview="";
            var xmklist=json.projectCardList;
            if(xmklist.length==0||xmklist.length==null){
                $("#wrap").hide();    //项目卡列表隐藏
                $(".n-card").show();    //没有项目卡的提示显示
            }else{
                for(var i=0;i<xmklist.length;i++){
                    xmkhtnl+='<div class="swiper-slide">'+
                                    '<p class="c-title">'+xmklist[i].cardName+'</p>'+
                                    '<p class="c-num">NO·'+xmklist[i].cardNumb+'</p>'+
                              '</div>'
                }
                //获取第一个的项目列表

                for(var j=0;j<xmklist[indexsiper-1].list.length;j++){
                    var time=dateFormat(xmklist[indexsiper-1].list[j].currentTimes)
                    xmkview+='<tr>'+
                        '<td class="td1">'+xmklist[indexsiper-1].list[j].projectName+'</td>'+
                        '<td class="td2">'+xmklist[indexsiper-1].list[j].projectName+'</td>'+
                        '<td class="td3">'+time+'</td>'+
                        ' </tr>'
                }

            }

            $(".swiper-wrapper").append(xmkhtnl);
            $(".listbody").append(xmkview);

        }


    });


   function funswiper(index){
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
                $(".listbody").remove();  //去掉原来的项目卡内容
                var json = JSON.parse(jsonData);
                console.log(json);
                var xmkview="";
                var xmklist=json.projectCardList;
                for(var j=0;j<xmklist[index-1].list.length;j++){
                    var time=dateFormat(xmklist[index-1].list[j].currentTimes)
                    xmkview+='<tr>'+
                                    '<td class="td1">'+xmklist[index-1].list[j].projectName+'</td>'+
                                    '<td class="td2">'+xmklist[index-1].list[j].projectName+'</td>'+
                                    '<td class="td3">'+time+'</td>'+
                              ' </tr>'
                }

                $(".listbody").append(xmkview);

            }


        });
    }
});
