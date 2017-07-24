

    //------------------------------------------------------------------------------------动态添加每一条订单状态和付款状态的数据
    function selectdate (arr){
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
        $("#thelist").append(html);
    }


    //订单状态请求  2
    $("#selectdingdan").change(function(){
        //数据分页
        var page2 = 1; //记录当前加载的页数
        var add_num2 = 0;//记录加载的次数
        refresher.init({
            id:"wrapper",
            able:"#thelist",
            pullDownAction:Refresh2,
            pullUpAction:Load2
        });

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
                $("#thelist").children().remove();
                selectdate (json);
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
        alert(111)
        //数据分页
        var page3 = 1; //记录当前加载的页数
        var add_num3 = 0;//记录加载的次数
        refresher.init({
            id:"wrapper",
            able:"#thelist",
            pullDownAction:Refresh3,
            pullUpAction:Load3
        });

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
                var json = JSON.parse(jsondata);
                if(json.records % json.pageSize == 0){   //判断一共能请求刷新的次数
                    add_num3 = parseInt(json.records/json.pageSize);
                }else{
                    add_num3 = parseInt(json.records/json.pageSize) + 1;
                }
                if(json.records == 0){
                    $(".pullUp").hide()
                }
                $("#thelist").children().remove();
                selectdate (json);
                //数据添加完成后开始调用加载插件
                wrapper.refresh();
                document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";
                $(".pullUpIcon").css("opacity","1");
                refresher.info.loadingLable = "加载中...";
                refresher.info.pullUpLable = "上拉加载更多"
                refresher.info.pullingUpLable = "释放加载更多";
                page_num(add_num)
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
        setTimeout(function () {
            $.ajax({
                type: 'POST',
                url  : '/findInsurance',
                data :{
                    shopCode :shopCode,
                    page: "1",
                    rows: "15"
                },
                async: false,
                success: function (jsondata) {
                    document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";
                    page2 = 1;
                    var json = JSON.parse(jsondata);
                    $("#thelist").children().remove();
                    selectdate(json);     //请求出的数据添加进入页面
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
                        page: ""+page2+"",
                        rows: "15"
                    },
                    async: false,
                    success: function (json) {
                        var json = JSON.parse(json);
                        selectdate(json);     //请求出的数据添加进入页面
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
        setTimeout(function () {
            $.ajax({
                type: 'POST',
                url  : '/findInsurance',
                data :{
                    shopCode :shopCode,
                    page: "1",
                    rows: "15"
                },
                async: false,
                success: function (jsondata) {
                    document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";
                    page2 = 1;
                    var json = JSON.parse(jsondata);
                    $("#thelist").children().remove();
                    selectdate(json);     //请求出的数据添加进入页面
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
    function Load3() {
        setTimeout(function () {
            page2++;
            if(page2 <= add_num2){
                $.ajax({
                    type: 'POST',
                    url   : '/findInsurance',
                    data :{
                        shopCode :shopCode,
                        page: ""+page2+"",
                        rows: "15"
                    },
                    async: false,
                    success: function (json) {
                        var json = JSON.parse(json);
                        selectdate(json);     //请求出的数据添加进入页面
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

    function page_num2(add_num2) {
        if(page2 == add_num2){
            $(".pullUpIcon").css("opacity","0");
            console.log($(".pullUpIcon"))
            $(".pullUpLabel").text("已经到了最底部了！");
            refresher.info.loadingLable = "已经到了最底部了!";
            refresher.info.pullUpLable = "已经到了最底部了!"
            refresher.info.pullingUpLable = "已经到了最底部了!"
        }
    }
    function page_num3(add_num3) {
        if(page3 == add_num3){
            $(".pullUpIcon").css("opacity","0");
            console.log($(".pullUpIcon"))
            $(".pullUpLabel").text("已经到了最底部了！");
            refresher.info.loadingLable = "已经到了最底部了!";
            refresher.info.pullUpLable = "已经到了最底部了!"
            refresher.info.pullingUpLable = "已经到了最底部了!"
        }
    }





