(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize =Math.floor(100*(clientWidth / 1080))+ 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document,window);
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
    //向html中添加数据
    function appending (arr) {
        var htmls = "";
        for (var i = 0;i < arr.length;i++) {
            for(var n = 0;n <arr[i].rechargeableCardContentList.length;n++) {
                htmls += '<li class="biaoshen">'+
                    '<span class="detals width1">'+arr[i].rechargeableCardContentList[n].itemOrProductName+'</span>'+
                    '<span class="detals width2">'+dateFormat(arr[i].rechargeableCardContentList[n].transactionTime)+'</span>'+
                    '<span class="detals width3">￥'+arr[i].rechargeableCardContentList[n].transactionAmount+'</span>'+
                    '</li>';
            }
          /*  htmls += '<li class="biaoshen">'+
                '<span class="detals width1">'+arr[i].cardNumb+'</span>'+
                '<span class="detals width2">'+dateFormat(arr[i].transactionTime)+'</span>'+
                '<span class="detals width3">￥'+arr[i].transactionAmount+'</span>'+
                '</li>';*/
        }
        $("ul").append(htmls);
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
            servicetype:"rechargeableCardMX"

        },
        success : function(jsonData){
            var json = JSON.parse(jsonData);
            appending(json)
        }
    });
    $(".back").on("click",function () {
        window.location.href = "/shopweixinServlet?serviceType=projectCZHICard&customerId="+customerId
       // window.location.href="/shopweixinServlet?serviceType=shoppersoncenter"
    })


})