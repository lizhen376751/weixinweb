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


$(document).ready(function(){
    var shopCode = $("#shopCode").val();
    var body = $("body");   //---------------------------------------------------------------获取body元素




    //------------------------------------------------------------------------------------动态添加每一条单据
    function addBills (arr){
        var html = "";
        for (var i = 0;i < arr.length;i++) {
            var dates = dateFormat(arr[i].kaiDanDate);
            var lis = "";
            var num = 0;
            for (j = 0;j < arr[i].list.length;j++) {
                if (arr[i].list[j].baoJiaState != 0) {
                    lis += '<li>'+
                        '<span class="color_9">'+arr[i].list[j].insurancename+'：</span>'+
                        '<span class="price">¥'+arr[i].list[j].totalPrices+'</span>'+
                        '<span class="quote color_10">已报价</span>'+
                        '<span class="detail color_3">详情</span>'+
                        '</li>';
                    num += 1;
                }else{
                    lis += '<li>'+
                        '<span class="color_9">'+arr[i].list[j].insurancename+'：</span>'+
                        '<span class="price">¥0.0</span>'+
                        '<span class="quote color_10">报价中</span>'+
                        '<span class="detail color_3" ddbh="'+arr[i].orderNumb+'" bxgs="'+arr[i].list[j].companyid+'" carId="'+arr[i].carId+'" shopCode="'+arr[i].shopCode+'" shopLm="'+arr[i].shopcodelm+'">详情</span>'+
                        '</li>';
                }
            }
            html += '<div class="bills">'+
                '<div class="bills_header">'+
                '<div class="header_main font_1">'+
                '<ul>'+
                '<li class="width_1"><span class="car_num">车牌号：</span><span>'+arr[i].carId+'</span></li>'+
                '<li class="width_2"><span>订单日期：</span><span>'+dates+'</span></li>'+
                '<li class="width_1"><span>订单编号：</span><span class="order_num">'+arr[i].orderNumb+'</span></li>'+
                '<li class="width_2 color_10"><span>共'+num+'家保险公司完成报价</span></li>'+
                '</ul>'+
                '</div>'+
                '</div>'+
                '<div class="bills_company font_1">'+
                '<ul>'+lis+'</ul>'+
                '<span class="isolation"></span>'+
                '</div>'+
                '</div> ';
        }
        body.append(html);
    }


    //时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }
    //------------------------------------------------------------------ajax请求数据

    $.ajax({
        type    : 'get',
        url     : '/findInsurance/'+shopCode,
        // data :{
        //     shopCode :shopCode
        // },
        success:function(jsondata){
            var json = JSON.parse(jsondata);
            // add_service(json,quarters);
            addBills(json);
            console.log(json);
            var detail = $(".detail");    //--------------------------------------------------------获取详情按钮
            //--------------------------------------------------------------------------------------点击详情按钮跳转
            detail.on("click",function(){
                // alert("该功能暂未开通~");
                var ddxq = $(this).attr("ddbh");
                var bxgs = $(this).attr("bxgs");
                var carId = $(this).attr("carId");
                var shopCode = $(this).attr("shopCode");
                var shopLm = $(this).attr("shopLm");
                window.location.href = "/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs;
            });
        },
        error:function(eee){
            alert("失败");
        }
    });
















})




