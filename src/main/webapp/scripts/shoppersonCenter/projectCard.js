

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
            servicetype: "queryUserProjectCardList",
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
            var xmkhtnl="";
            var xmkview="";
            var  xmkmingxi="";//项目卡明细跳转
          //  var xmklist=json.projectCardList;
            // if(json.projectCardList.length==0||json.projectCardList.length==null||xmklist==null){
            if(json==null){
                $("#wrap").hide();    //项目卡列表隐藏
                $(".n-card").show();    //没有项目卡的提示显示
            }else{
                for(var i=0;i<json.length;i++){
                    xmkhtnl+='<div class="swiper-slide">'+
                                    '<p class="c-title">'+json[i].cardName+'</p>'+
                                    '<p class="c-num">NO·'+json[i].cardNumb+'</p>'+
                              '</div>'

                }
                //列表默认显示第一个的
                for(var j=0;j<json[0].list.length;j++){
                    var time=dateFormat(json[0].list[j].validDate)
                    xmkview+='<tr>'+
                                '<td class="td1">'+json[0].list[j].projectName+'</td>'+
                                '<td class="td2">'+time+'</td>'+
                                '<td class="td3">'+json[0].list[j].currentTimes+'</td>'+
                            ' </tr>'
                }
                xmkmingxi+='<div class="details" id="details" onclick="tCardMX('+json[0].cardNumb+',\''+shopCode.val()+'\','+customerId+')">明细</div>'   //默认显示第一张项目卡的明细

                $("#listbody").append(xmkview);
                $(".swiper-wrapper").append(xmkhtnl);
                $(".listtable").after(xmkmingxi);

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
                servicetype: "queryUserProjectCardList",
                customerId: customerId

            },
            async: false,
            success: function (jsonData) {
                $(".listbody").remove();  //去掉原来的项目卡内容
                var json = JSON.parse(jsonData);
                var xmkview2="";
                var xmkmingxi2="";    //项目明细跳转
                //var xmklist2=json.projectCardList;

                xmkmingxi2+='<div class="details" id="details" onclick="tCardMX('+json[index].cardNumb+',\''+shopCode.val()+'\','+customerId+')">明细</div>'

                for(var j=0;j<json[index].list.length;j++){
                    var time=dateFormat(json[index].list[j].validDate)
                    xmkview2+='<tr>'+
                                    '<td class="td1">'+json[index].list[j].projectName+'</td>'+
                                    '<td class="td2">'+time+'</td>'+
                                    '<td class="td3">'+json[index].list[j].currentTimes+'</td>'+
                              ' </tr>'
                }
                $("#listbody").html("");
                $("#listbody").append(xmkview2);
                $("#details").remove();
                $(".listtable").after(xmkmingxi2);


            }


        });
    }
//返回键按钮  返回到个人中心页面
    $(".b-btn").click(function(){
        window.location.href = "shopweixinMenuServlet?shopcode="+shopCode.val()+"_shoppersoncenter";
    })
});


//项目卡明细跳转
function tCardMX(a,b,c) {
    window.location.href="/shopweixinServlet?serviceType=projectCardMX&cardNumb="+a+"&shopCode="+b+"&customerId="+c;
};
