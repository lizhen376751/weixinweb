$(function(){
    var shopCode = $("#shopCode").val();
    var body = $("body");   //---------------------------------------------------------------获取body元素
    var kf_logo = $(".kf"); //-----------------------------------------huoqu
    kf_logo.on("click",function (e) {
        window.location.href = "/cheXianOnline";
        e.stopPropagation();
    });
    var ylb = shopCode.substring(0,3);
    if(ylb != "YLB"){
        kf_logo.hide();
    };
    //时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }
    //------------------------------------------------------------------------------------动态添加每一条订单状态和付款状态的数据
    function selectdate (arr,nameid){
        var html = "";
        for (var i = 0;i < arr.length;i++) {
            var dates = dateFormat(arr[i].kaiDanDate);
            var lis = "";
            var num = 0;
            for (j = 0;j < arr[i].list.length;j++) {
                if (arr[i].list[j].baoJiaState == 1) {
                    lis += '<li>'+
                        '<span class="color_9">'+arr[i].list[j].insurancename+'：</span>'+
                        '<span class="price">¥'+arr[i].list[j].totalPrices+'</span>'+
                        '<span class="quote color_10">已报价</span>'+
                        '<span class="detail color_3" ddbh="'+arr[i].orderNumb+'" bxgs="'+arr[i].list[j].companyid+'" carId="'+arr[i].carId+'" shopCode="'+arr[i].shopCode+'" shopLm="'+arr[i].shopcodelm+'">详情</span>'+
                        '</li>';
                    num += 1;
                }else if(arr[i].list[j].baoJiaState == 2){
                    if(arr[i].fuKuanFlag==1){
                        lis += '<li>'+
                            '<span class="color_9">'+arr[i].list[j].insurancename+'：</span>'+
                            '<span class="price">¥'+arr[i].list[j].totalPrices+'</span>'+
                            '<span class="quote color_10">已付款</span>'+
                            '<span class="detail color_3" ddbh="'+arr[i].orderNumb+'" bxgs="'+arr[i].list[j].companyid+'" carId="'+arr[i].carId+'" shopCode="'+arr[i].shopCode+'" shopLm="'+arr[i].shopcodelm+'">详情</span>'+
                            '</li>';
                    }else{
                        lis += '<li>'+
                            '<span class="color_9">'+arr[i].list[j].insurancename+'：</span>'+
                            '<span class="price">¥'+arr[i].list[j].totalPrices+'</span>'+
                            '<span class="quote color_10">已投保</span>'+
                            '<span class="detail color_3" ddbh="'+arr[i].orderNumb+'" bxgs="'+arr[i].list[j].companyid+'" carId="'+arr[i].carId+'" shopCode="'+arr[i].shopCode+'" shopLm="'+arr[i].shopcodelm+'">详情</span>'+
                            '</li>';
                    }

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
        $("#"+nameid).append(html);
    }
    //订单请求数据分页
    var page2 = 1; //记录当前加载的页数
    var add_num2 = 0;//记录加载的次数
     refresher.init({
        id:"wrapper2",
        able:"#thelist2",
        pullDownAction:Refresh2,
        pullUpAction:Load2
    });

    //付款状态数据分页
    var page3 = 1; //记录当前加载的页数
    var add_num3 = 0;//记录加载的次数
    refresher.init({
        id:"wrapper3",
        able:"#thelist3",
        pullDownAction:Refresh3,
        pullUpAction:Load3
    });
    // var refresher3= {
    //     init: {
    //         id: "wrapper3",
    //         able: "#thelist3",
    //         pullDownAction: Refresh3,
    //         pullUpAction: Load3
    //     }
    // };


    //订单状态请求  2
    $("#selectdingdan").change(function(){
        $("#wrapper").hide();
        $("#wrapper3").hide();
        $("#wrapper2").show();

        var selectthis=$(this).val();
        $.ajax({
            type: 'POST',
            url: '/findInsurance',
            data: {
                shopCode: shopCode,
                baoJiaState: selectthis,
                page: "1",
                rows: "15"
            },
            success: function (jsondata) {
                var json = JSON.parse(jsondata);
               // console.log(json);
                $("#thelist2").children().remove();
                selectdate (json.rows,"thelist2");
                //数据添加完成后开始调用加载插件
                wrapper.refresh();
                document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";
                $(".pullUpIcon").css("opacity","1");
                refresher.info.loadingLable = "加载中...";
                refresher.info.pullUpLable = "上拉加载更多"
                refresher.info.pullingUpLable = "释放加载更多";
                page_num2(add_num2)
                //调用加载插件结束
                var detail = $(".detail");    //--------------------------------------------------------获取详情按钮
                //--------------------------------------------------------------------------------------点击详情按钮跳转
                detail.on("click",function(){
                    // alert("该功能暂未开通~");
                    var ddxq = $(this).attr("ddbh");
                    var bxgs = $(this).attr("bxgs");
                    var carId = $(this).attr("carId");
                    var shopCode = $(this).attr("shopCode");
                    var shopLm = $(this).attr("shopLm");
                    // console.log(ddxq);
                    // console.log("/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs)
                    window.location.href = "/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs;
                });
            },
            error:function(eee){
                alert("失败");
            }
        })
    })
    //付款状态请求3
    $("#selectfakuan").change(function(){
        $("#wrapper").hide();
        $("#wrapper2").hide();
        $("#wrapper3").show();
        var selectfukuan=$(this).val();
        $.ajax({
            type: 'POST',
            url: '/findInsurance',
            data: {
                shopCode: shopCode,
                paymentStatus: selectfukuan,
                page: "1",
                rows: "15"
            },
            success: function (jsondata) {
                var json = JSON.parse(jsondata);
                if(json.records % json.pageSize == 0){   //判断一共能请求刷新的次数
                    add_num3 = parseInt(json.records/json.pageSize);
                }else{
                    add_num3 = parseInt(json.records/json.pageSize) + 1;
                }
                if(json.records == 0){
                    $(".pullUp").hide()
                }
                $("#thelist3").children().remove();
                selectdate (json.rows,"thelist3");
                //数据添加完成后开始调用加载插件
                wrapper3.refresh();
                document.getElementById("wrapper3").querySelector(".pullDownLabel").innerHTML="";
                $(".pullUpIcon").css("opacity","1");
                refresher.info.loadingLable = "加载中...";
                refresher.info.pullUpLable = "上拉加载更多"
                refresher.info.pullingUpLable = "释放加载更多";
                page_num3(add_num3)
                //调用加载插件结束
                var detail = $(".detail");    //--------------------------------------------------------获取详情按钮
                //--------------------------------------------------------------------------------------点击详情按钮跳转
                detail.on("click",function(){
                    // alert("该功能暂未开通~");
                    var ddxq = $(this).attr("ddbh");
                    var bxgs = $(this).attr("bxgs");
                    var carId = $(this).attr("carId");
                    var shopCode = $(this).attr("shopCode");
                    var shopLm = $(this).attr("shopLm");
                    // console.log(ddxq);
                    // console.log("/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs)
                    window.location.href = "/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs;
                });
            },
            error:function(eee){
                alert("失败");
            }
        })
    })

    //下拉刷新函数
    function Refresh2() {
       var selectthis2="";
        if($(':focus').length==0) {
        }
        else{
              //弹出焦点元素的name
            selectthis2= $(":focus").val();
        }
        setTimeout(function () {
            $.ajax({
                type: 'POST',
                url  : '/findInsurance',
                data :{
                    shopCode :shopCode,
                    baoJiaState: selectthis2,
                    page: "1",
                    rows: "15"
                },
                async: false,
                success: function (jsondata) {
                    document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";
                    page2 = 1;
                    var json = JSON.parse(jsondata);
                    $("#thelist2").children().remove();
                    selectdate (json.rows,"thelist2");     //请求出的数据添加进入页面
                    wrapper.refresh();
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";
                    $(".pullUpIcon").css("opacity","1");
                    refresher.info.loadingLable = "加载中...";
                    refresher.info.pullUpLable = "上拉加载更多"
                    refresher.info.pullingUpLable = "释放加载更多";
                    page_num2(add_num2);
                    var detail = $(".detail");    //--------------------------------------------------------获取详情按钮
                    //--------------------------------------------------------------------------------------点击详情按钮跳转
                    detail.on("click",function(){
                        // alert("该功能暂未开通~");
                        var ddxq = $(this).attr("ddbh");
                        var bxgs = $(this).attr("bxgs");
                        var carId = $(this).attr("carId");
                        var shopCode = $(this).attr("shopCode");
                        var shopLm = $(this).attr("shopLm");
                        // console.log(ddxq);
                        // console.log("/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs)
                        window.location.href = "/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs;
                    });
                },
                error: function () {
                    alert("查询数据出错啦，请刷新再试");
                }
            });
        },1000)
    }
    //---------------------------------------------------上拉加载函数
    function Load2() {

        setTimeout(function () {
            page2++;
            if(page2 <= add_num2){
                $.ajax({
                    type: 'POST',
                    url   : '/findInsurance',
                    data :{
                        shopCode :shopCode,
                        baoJiaState: selectthis2,
                        page: ""+page2+"",
                        rows: "15"
                    },
                    async: false,
                    success: function (json) {
                        var json = JSON.parse(json);
                        selectdate (json.rows,"thelist2");    //请求出的数据添加进入页面
                        page_num2(add_num2)//必须添加
                        var detail = $(".detail");    //--------------------------------------------------------获取详情按钮
                        //--------------------------------------------------------------------------------------点击详情按钮跳转
                        detail.on("click",function(){
                            // alert("该功能暂未开通~");
                            var ddxq = $(this).attr("ddbh");
                            var bxgs = $(this).attr("bxgs");
                            var carId = $(this).attr("carId");
                            var shopCode = $(this).attr("shopCode");
                            var shopLm = $(this).attr("shopLm");
                            // console.log(ddxq);
                            // console.log("/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs)
                            window.location.href = "/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs;
                        });
                    },
                    error: function () {
                        alert("查询数据出错啦，请刷新再试");
                    }
                });
            }
            wrapper.refresh();
        },1000)
    }

    //下拉刷新函数
    function Refresh3() {
        var selectfukuan3="";
        if($(':focus').length==0) {
        }
        else{
            //弹出焦点元素的name
            selectfukuan3= $(":focus").val();
        }
        setTimeout(function () {
            $.ajax({
                type: 'POST',
                url  : '/findInsurance',
                paymentStatus: selectfukuan3,
                data :{
                    shopCode :shopCode,
                    page: "1",
                    rows: "15"
                },
                async: false,
                success: function (jsondata) {
                    document.getElementById("wrapper3").querySelector(".pullDownIcon").style.display="none";
                    document.getElementById("wrapper3").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";
                    page3 = 1;
                    var json = JSON.parse(jsondata);
                    $("#thelist3").children().remove();
                    selectdate (json.rows,"thelist3");    //请求出的数据添加进入页面
                    wrapper3.refresh();
                    document.getElementById("wrapper3").querySelector(".pullDownLabel").innerHTML="";
                    $(".pullUpIcon").css("opacity","1");
                    refresher.info.loadingLable = "加载中...";
                    refresher.info.pullUpLable = "上拉加载更多"
                    refresher.info.pullingUpLable = "释放加载更多";
                    page_num3(add_num3);
                    var detail = $(".detail");    //--------------------------------------------------------获取详情按钮
                    //--------------------------------------------------------------------------------------点击详情按钮跳转
                    detail.on("click",function(){
                        // alert("该功能暂未开通~");
                        var ddxq = $(this).attr("ddbh");
                        var bxgs = $(this).attr("bxgs");
                        var carId = $(this).attr("carId");
                        var shopCode = $(this).attr("shopCode");
                        var shopLm = $(this).attr("shopLm");
                        // console.log(ddxq);
                        // console.log("/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs)
                        window.location.href = "/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs;
                    });
                },
                error: function () {
                    alert("查询数据出错啦，请刷新再试");
                }
            });
        },1000)
    }
    //---------------------------------------------------上拉加载函数
    function Load3() {
        setTimeout(function () {
            page3++;
            if(page3 <= add_num3){
                $.ajax({
                    type: 'POST',
                    url   : '/findInsurance',
                    data :{
                        shopCode :shopCode,
                        paymentStatus: selectfukuan3,
                        page: ""+page3+"",
                        rows: "15"
                    },
                    async: false,
                    success: function (json) {
                        var json = JSON.parse(json);
                        selectdate (json.rows,"thelist3");   //请求出的数据添加进入页面
                        page_num3(add_num3)//必须添加
                        var detail = $(".detail");    //--------------------------------------------------------获取详情按钮
                        //--------------------------------------------------------------------------------------点击详情按钮跳转
                        detail.on("click",function(){
                            // alert("该功能暂未开通~");
                            var ddxq = $(this).attr("ddbh");
                            var bxgs = $(this).attr("bxgs");
                            var carId = $(this).attr("carId");
                            var shopCode = $(this).attr("shopCode");
                            var shopLm = $(this).attr("shopLm");
                            // console.log(ddxq);
                            // console.log("/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs)
                            window.location.href = "/baoXianDetails?carId="+carId+"&shopCode="+shopCode+"&shopcodelm="+shopLm+"&orderNumb="+ddxq+"&companyid="+bxgs;
                        });
                    },
                    error: function () {
                        alert("查询数据出错啦，请刷新再试");
                    }
                });
            }
            wrapper.refresh();
        },1000)
    }

    function page_num2(add_num2) {
        if(page2 == add_num2){
            $(".pullUpIcon").css("opacity","0");
            $(".pullUpLabel").text("已经到了最底部了！");
            refresher.info.loadingLable = "已经到了最底部了!";
            refresher.info.pullUpLable = "已经到了最底部了!"
            refresher.info.pullingUpLable = "已经到了最底部了!"
        }
    }
    function page_num3(add_num3) {
        if(page3 == add_num3){
            $(".pullUpIcon").css("opacity","0");
            $(".pullUpLabel").text("已经到了最底部了！");
            refresher.info.loadingLable = "已经到了最底部了!";
            refresher.info.pullUpLable = "已经到了最底部了!"
            refresher.info.pullingUpLable = "已经到了最底部了!"
        }
    }

})


