$(document).ready(function () {
    var ul = $("ul");//获取ul容器
    //出去逗号的span
    function deletes() {
        var box = $(".box");
        for (var i = 0; i < box.length; i++) {
            var punctuation = $(box[i]).find(".punctuation");
            $(punctuation[punctuation.length - 1]).remove()
        }
    }
    var page = 1; //记录当前加载的页数
    var add_num = 0;//记录加载的次数
    refresher.init({
        id:"wrapper",
        able:"ul",
        pullDownAction:Refresh,
        pullUpAction:Load
    });
    //动态添加每一条消费记录
        $.ajax({
            type: 'POST',
            url: '/pagingquery',
            data: {
                businessType: "xiaofeijilu",
                page: "1",
                rows: "15"
            },
            success: function (jsonData) {
                var arr = JSON.parse(jsonData);
                console.log(arr);
                if(arr.records % arr.pageSize == 0){
                    add_num = parseInt(arr.records/arr.pageSize);
                }else{
                    add_num = parseInt(arr.records/arr.pageSize) + 1;
                }
                if(arr.records == 0){
                    $(".pullUp").hide()
                }
                console.log(add_num)
                addLabel(arr.rows);
                page_num(add_num)
            }  // addLabel函数结束


        });
    //下拉刷新函数
    function Refresh() {
        setTimeout(function () {
            $.ajax({
                type: 'POST',
                url: "/pagingquery",
                data: {
                    businessType: "xiaofeijilu",
                    page: "1",
                    rows: "15"
                },
                async: false,
                success: function (jsondata) {
                    document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";
                    page = 1;
                    var json = JSON.parse(jsondata);
                    console.log(json)
                    ul.children().remove();
                    addLabel(json.rows);
                    wrapper.refresh();
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";
                    $(".pullUpIcon").css("opacity","1");
                    refresher.info.loadingLable = "加载中...";
                    refresher.info.pullUpLable = "上拉加载更多"
                    refresher.info.pullingUpLable = "释放加载更多";
                    page_num(add_num)
                },
                error: function () {
                    alert("查询数据出错啦，请刷新再试");
                }
            });
        },1000)
    }
    //---------------------------------------------------上拉加载函数
    function Load() {
        setTimeout(function () {
            page++;
            if(page <= add_num){
                $.ajax({
                    type: 'POST',
                    url: "/pagingquery",
                    data: {
                        businessType: "xiaofeijilu",
                        page: ""+page+"",
                        rows: "15"
                    },
                    async: false,
                    success: function (json) {
                        var json = JSON.parse(json);
                        addLabel(json.rows);
                        page_num(add_num)//必须添加
                    },
                    error: function () {
                        alert("查询数据出错啦，请刷新再试");
                    }
                });
            }
            wrapper.refresh();
        },1000)
    }

    function page_num(add_num) {
        if(page == add_num){
            $(".pullUpIcon").css("opacity","0");
            console.log($(".pullUpIcon"))
            $(".pullUpLabel").text("已经到了最底部了！");
            refresher.info.loadingLable = "已经到了最底部了!";
            refresher.info.pullUpLable = "已经到了最底部了!"
            refresher.info.pullingUpLable = "已经到了最底部了!"
        }
    }
    function addLabel(arr) {
        for(var i = 0;i < arr.length;i++){
            var xfxm = "";  //记录消费项目
            var xm_num = "";//记录该项目的数据
            var xfsp = "";  //记录消费商品
            var fkfs = "";//记录付款方式
            var sgbz = "";
            if (arr[i].resultPurchaseHistory.purchaseProjectList.length != 0) {   //判断是否存在消费的项目
                var xm = "";
                for(var j = 0;j < arr[i].resultPurchaseHistory.purchaseProjectList.length;j++){
                    var s = j + 1;
                    xm += '<div><span>'+s+'</span><p>'+arr[i].resultPurchaseHistory.purchaseProjectList[j].projectName+'</p></div>';
                    xm_num += arr[i].resultPurchaseHistory.purchaseProjectList[j].projectCode + "-" + arr[i].resultPurchaseHistory.purchaseProjectList[j].projectId + "_";
                }
                xfxm = '<div class="middle_1 font_1">'+
                            '<div class="left_1">消费项目：</div>'+
                            '<div class="right_1">'+xm+'</div>'+
                        '</div>'
                sgbz = '<div class="sgbz font_1" sgbz="sgbz" xm_num = "'+xm_num+'" shopCode="'+arr[i].resultPurchaseHistory.shopCode+'" wxpingzheng="'+arr[i].resultPurchaseHistory.wxpingzheng+'">施工步骤</div>';

            }
            if (arr[i].resultPurchaseHistory.purchaseProductList.length != 0) {   //判断是否存在消费的商品
                var sp = "";
                for(var h = 0;h < arr[i].resultPurchaseHistory.purchaseProductList.length;h++){
                    w = h + 1;
                    sp += '<div><span>'+w+'</span><p>'+arr[i].resultPurchaseHistory.purchaseProductList[h].productName+'</p></div>'
                }
                xfsp = '<div class="middle_1 font_1">'+
                    '<div class="left_1">消费商品：</div>'+
                    '<div class="right_1">'+sp+'</div>'+
                    '</div>'

            }
            var htmlzhifufangshi = "";//第一种收款方式
            var htmlzhifufangshiSecond = "";//第二种收款方式
            if ((arr[i].resultPurchaseHistory.zhifufangshiSecond == null) && (arr[i].resultPurchaseHistory.zhifufangshi == null)) {   //判断付款方式是否都为空
                fkfs += "未付款";
            } else {

                if (arr[i].resultPurchaseHistory.zhifufangshi != null) {   //第一种收款方式
                    htmlzhifufangshi = "<span>" + arr[i].resultPurchaseHistory.zhifufangshi + "</span>（<span class='red'>￥" + arr[i].resultPurchaseHistory.zhifufangshiJinE + "</span>）"
                }

                if (arr[i].resultPurchaseHistory.zhifufangshiSecond != null) {  //第二种收款方式
                    htmlzhifufangshiSecond = arr[i].resultPurchaseHistory.zhifufangshiSecond + "(<span class='red'>￥" + arr[i].resultPurchaseHistory.zhifufangshiBJinE + "</span>)"
                } else {

                }

                fkfs += htmlzhifufangshiSecond + htmlzhifufangshi;
            }
            //判断是否评价
            var pingjia = "";
            if(arr[i].isevaluate){
                pingjia = '<div class="yupingjia font_1" yupingjia="yupingjia" shopCode="'+arr[i].resultPurchaseHistory.shopCode+'" wxpingzheng="'+arr[i].resultPurchaseHistory.wxpingzheng+'" customId="'+arr[i].customId+'" plateNumber="'+arr[i].plateNumber+'">已评价</div>'
            }else{
                pingjia = '<div class="pingjia font_1" pingjia="pingjia" shopCode="'+arr[i].resultPurchaseHistory.shopCode+'" wxpingzheng="'+arr[i].resultPurchaseHistory.wxpingzheng+'" customId="'+arr[i].customId+'" plateNumber="'+arr[i].plateNumber+'">去评价</div>'
            }
            //判断公里数是否为null
            var gls = "";
            if(arr[i].resultPurchaseHistory.gongLiShu == null || arr[i].resultPurchaseHistory.gongLiShu == "" || arr[i].resultPurchaseHistory.gongLiShu == "null"){
                gls = 0;
            }else{
                gls =  arr[i].resultPurchaseHistory.gongLiShu;
            }
            var html = '<li>'+
                         '<div class="detail_1">'+
                             '<div class="top_1 font_2">'+
                                '<div class="left_11">日期：'+arr[i].resultPurchaseHistory.kprq+ '</div>'+
                                '<div class="right_11">公里数：'+gls+ 'km</div>'+
                             '</div>'+xfsp+xfxm+
                            '<div class="bottom_1 font_1">'+
                                '<div>'+
                                     '<span style="margin-left: 26px;">施工店铺：</span>'+
                                     '<span>'+arr[i].resultPurchaseHistory.shopName+'</span>'+
                                 '</div>'+
                                '<div>'+
                                    '<span style="margin-left: 26px;">付款方式：</span>'+fkfs+
                                '</div>'+
                            '</div>'+
                        '<div class="btn">'+ sgbz +
                         '</div>'+
                    '</div>'+
                '</li>';
            ul.append(html)
        }
    }
//    点击施工步骤进行跳转
    ul.on("click",function (e) {
        var ev = e || window.event;
        var target = ev.target || ev.srcElement;
        //施工步骤按钮
        if($(target).attr("sgbz") == "sgbz") {
            var xu_number = $(target).attr("xm_num");
            var shopCode = $(target).attr("shopCode");
            var wxpingzheng = $(target).attr("wxpingzheng");
            if(xu_number != null && xu_number != ""){
                window.location.href="/shopweixinServlet?serviceType=shigongbuzhou&xu_number="+xu_number+"&shopCode="+shopCode+"&wxpingzheng="+wxpingzheng;
            }else{
                alert("商品消费暂无施工步骤")
            }

        }
        //去评价按钮
        if($(target).attr("pingjia") == "pingjia") {
            var plateNumber = $(target).attr("plateNumber");
            var customId = $(target).attr("customId");
            var shopCode = $(target).attr("shopCode");
            var wxpingzheng = $(target).attr("wxpingzheng");
            window.location.href="/shopweixinServlet?serviceType=shopEvaluateParam&customId="+customId+"&shopCode="+shopCode+"&wxpingzheng="+wxpingzheng+"&plateNumber="+plateNumber;
        }
        //已评价按钮
        if($(target).attr("yupingjia") == "yupingjia") {
            var plateNumber = $(target).attr("plateNumber");
            var customId = $(target).attr("customId");
            var shopCode = $(target).attr("shopCode");
            var wxpingzheng = $(target).attr("wxpingzheng");
            window.location.href="/shopweixinServlet?serviceType=shopEvaluateParam&customId="+customId+"&shopCode="+shopCode+"&wxpingzheng="+wxpingzheng+"&plateNumber="+plateNumber;
        }
    })

})