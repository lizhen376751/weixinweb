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
    var body = $("body")   //---------------------------------------------------------------获取body元素
    var detail = $(".detail");    //--------------------------------------------------------获取详情按钮



    //------------------------------------------------------------------------------------动态添加每一条单据
    function addBills (arr){
        var html = "";
        for (var i = 0;i < arr.length;i++) {
            var lis = "";
            for (j = 0;j < arr[i].a.length;j++) {
                lis += '<li>'+
                    '<span class="color_9">人民保险：</span>'+
                    '<span class="price">¥5083.00</span>'+
                    '<span class="quote color_10">已报价</span>'+
                    '<span class="detail color_3">详情</span>'+
                    '</li>'
            }
            html += '<div class="bills">'+
                '<div class="bills_header">'+
                '<div class="header_main font_1">'+
                '<ul>'+
                '<li class="width_1"><span class="car_num">车牌号：</span><span>鲁A00001</span></li>'+
                '<li class="width_2"><span>订单日期：</span><span>2017-01-22</span></li>'+
                '<li class="width_1"><span>订单编号：</span><span class="order_num">KNVB515631854156</span></li>'+
                '<li class="width_2 color_10"><span>共5家保险公司完成报价</span></li>'+
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
    var arr = [{
        a:[1,2,3]
    },{
        a:[1,2,3]
    }]
    addBills(arr);


    //------------------------------------------------------------------ajax请求数据
    // $.ajax({
    //     type    : 'post',
    //     url     : '/queryInsurance',
    //     success:function(jsondata){
    //         var json = JSON.parse(jsondata);
    //         // add_service(json,quarters);
    //         console.log(json);
    //     },
    //     error:function(eee){
    //         alert("失败");
    //     }
    // });














    //--------------------------------------------------------------------------------------点击详情按钮跳转
    detail.on("click",function(){
        alert("该功能暂未开通~")
        // window.location.href = "cheXianDetail.html"
    })

})




