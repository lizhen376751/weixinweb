(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize = Math.floor(100 * (clientWidth / 1080)) + 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);
$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
    }, 1000);
});
$(document).ready(function () {
    var clpp = $(".clpp")  //------------------------------------------------------------------车辆品牌
    var car_num = $(".car_num"); //-------------------------------------------------------------获取车牌号信息框
    var clpp_txt = $(".clpp_txt"); //----------------------------------------------------------获取车辆品牌的信息框
    //----------------------------------------------------------------获取里程数部分元素
    var lcs = $(".lcs")     //--------------------------------------------------------里程数元素
    var lcs_num = $(".lcs_num"); //-------------------------------------------------------------获取里程数信息框
    var mileage = $(".mileage")   //--------------------------------------------------修改里程数的遮罩层元素
    var mileage_num = $(".mileage .holdder input")  //--------------------------------修改里程数中遮罩层里的input
    var qx = $(".mileage .holdder .qx");    //---------------------------------------- 修改里程数中遮罩层里的取消按钮
    var qd = $('.mileage .holdder .qd');    //---------------------------------------- 修改里程数中遮罩层里的确定按钮
    var jkzs_txt = $(".jkzs_txt"); // ----------------------------------------------------获取健康指数提示数字-62
    var jkzs = $(".jkzs"); //--------------------------------------------------------------------获取健康指数整个框
    var bytx_txt = $(".bytx_txt"); // ----------------------------------------------------获取保养提醒提示数字-1000km
    var bytx = $(".bytx"); //--------------------------------------------------------------------获取保养提醒整个框
    var project_card = $(".project_card");//------------------------------------获取项目卡的ul
    var recharge_cards = $(".recharge_cards");//-------------------------------获取充值卡的ul
    var vouchers = $(".vouchers");//-------------------------------------------获取代金券的ul
    var no_project_card = $(".no_project_card");//------------------------------------获取项目卡的ul中没有项目卡的div
    var no_recharge_cards = $(".no_recharge_cards");//-------------------------------获取充值卡的ul中没有充值卡的div
    var no_vouchers = $(".no_vouchers");//-------------------------------------------获取代金券的ul中没有代金券的div

    var zsje = $(".zsje");//-----------------------------------------------------------获取赠送金额
    var djje = $(".djje");//-----------------------------------------------------------获取定金金额
    var hyjf = $(".hyjf");//-----------------------------------------------------------获取会员积分
    var tzk = $(".tzk");//------------------------------------------------------------获取特种卡

    var button_bar = $(".button_bar");//-----------------------------------------------获取退出账号按钮
    var second_carList = $(".second_carList");//----------------------------------------------------------------车系页面
    var second_cpxx = $(".second_cpxx"); //-----------------------------------------------------------------车系页面的车牌提示
    var second_lis = $(".second_carList ul");//----------------------------------------------------------车系页面的每一条数据
    var second_back = $(".second_back"); //------------------------------------------------------------------车系页面的返回按钮

    var third_carList = $(".third_carList");//------------------------------------------------------------------车系页面
    var third_cpxx = $(".third_cpxx"); //--------------------------------------------------------------------车系页面的车牌提示
    var third_lis = $(".third_carList ul");//--------------------------------------------------------------车系页面的每一条数据
    var third_back = $(".third_back"); //---------------------------------------------------------------车系页面的返回按钮


    var car_1 = ""; //---------------------------------------------------------------------------------记录车牌类型第一次数据
    var car_2 = ""; //------------------------------------------------------------------------------------记录车牌类型第二次数据
    var car_3 = ""; //----------------------------------------------------------------------------------记录车牌类型第三次数据
    //-------------------------------------------------------------------------------------页面加载需求的数据的请求:车牌/里程数/AHI
    var khID = "";//记录客户ID
    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shoppersoncenter",
            servicetype : "personcenter"
        },
        async: false,
        success: function (jsonData) {
            json = JSON.parse(jsonData);
            console.log(json);
            car_num.text(json.plateNumber);    //-------------------------------------------------------------动态添加车牌号码
            var cltxt = "";//---------------------------------------------------------------定义车牌信息添加的字符串
            khID = json.id;
            //------------------------------------------------------------------------------------------------车系品牌添加
            if (json.carBrand != null || json.carModel != null || json.carSeries != null) {
                if(json.carBrand == null){
                    json.carBrand = "";
                }
                if(json.carModel == null){
                    json.carModel = "";
                }
                if(json.carSeries == null){
                    json.carSeries = "";
                }
                var cltxt = json.carBrand + json.carSeries + json.carModel;
                clpp_txt.text(cltxt);
                clpp_txt.css({
                    color: "#6c6c6c"
                });
            }

            //-----------------------------------------------------------------------------------------------当前里程(保养提醒)判断
            if (json.currentmileage != null && json.currentmileage != "") {
                lcs_num.text(json.currentmileage+"km");
                lcs_num.css({
                    color: "#6c6c6c"
                });
                lcs_num.removeClass("font_4").addClass("font_1")
                bytx_txt.text(json.currentmileage+"km");
                bytx_txt.css({
                    color: "#6c6c6c"
                });
                bytx_txt.removeClass("font_4").addClass("font_1")
            }
            ;
            if (json.point != null && json.point != "") {
                jkzs_txt.text(json.point);//---------------------------------------------------------------------------------健康指数动态添加
                jkzs_txt.css({
                    color: "#f64a88",
                })
                jkzs_txt.removeClass("font_4").addClass("font_2")
            }
            ;
            //----------------------------------------------------------------------------------------------------------判断是否有联盟卡
            // if (json.lianmengkaXmLeftResultModules && json.lianmengkaXmLeftResultModules.length != 0) {
            //     add_lmCards(json.lianmengkaXmLeftResultModules);
            // } else {
            //     no_card.show();
            //     uls.hide();
            // }


        }

    });
    //--------------------------------------------------------------------------------------------------------------------------车型车系的请求
    var app = app || {};
    app.data = []; //-----------------------------------------------------------------------------------------------------车牌号集合:奥迪
    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shoppersoncenter",
            servicetype: "carType",
            type: "CarBrand",
            num: 0
        },
        success: function (jsonData) {
            json = JSON.parse(jsonData);
            console.log(json);
            for (var i = 0; i < json.rows.length; i++) {
                var zifu = json.rows[i].carFirst + json.rows[i].carId + "-" + json.rows[i].carName;
                app.data.push(zifu);
            }
            console.log(app.data);
            app.main();
            $(".index-sidebar-container").css("display","none")
            var car_brand = $(".car_brand"); //----------------------------------------------------车系品牌遮罩层
            var item_container_li = $("#item-container ul li") //--------------------------------- 获取每一个车牌号:奥迪
            //---------------------------------------------------------------------------------------点击出现车辆品牌分类
            clpp.on("click", function () {
                car_brand.show();
                $(".index-sidebar-container").css("display", "block")
            });
            //----------------------------------------------------------------------------------------点击获取进一步的信息
            item_container_li.on("click", function () {
                car_1 = "";
                car_1 = $(this).text();
                var car_id = $(this).attr("ids");
                console.log(car_id);
                // car_brand.hide();
                $(".index-sidebar-container").css("display", "none");

                // clpp_txt.text(car_1);
                // clpp_txt.css({
                //     color: "#6c6c6c"
                // });
                //--------------------------------------------------------------------------------------------二级车系信息的数据请求
                $.ajax({
                    type: 'POST',
                    url: '/shopAjax',
                    data: {
                        businessType: "shoppersoncenter",
                        servicetype: "carType",
                        type: "CarSeries",
                        num: car_id //品牌编码
                    },
                    success: function (jsonData) {
                        json = JSON.parse(jsonData);
                        console.log(json);
                        second_carList.show();
                        second_cpxx.text(car_1);
                        second_lis.children().remove();
                        for(var i = 0;i < json.length;i++){
                            var lis = '<li carId="'+json[i].carId+'">'+json[i].carName+'</li>';
                            second_lis.append(lis);
                        }
                        var second_ul_li = $(".second_carList ul li");
                        second_ul_li.on("click",function () {
                            car_2 = "";
                            car_2 = $(this).text();
                            // second_carList.hide();
                            var carId = $(this).attr("carId");//获取车系的编码
                            //---------------------------------------------------------------------------------------------------------------------------三级车辆型号数据请求
                            $.ajax({
                                type: 'POST',
                                url: '/shopAjax',
                                data: {
                                    businessType: "shoppersoncenter",
                                    servicetype: "carType",
                                    type: "CarModel",
                                    num: carId //车系编码
                                },
                                success: function (jsonData) {
                                    json = JSON.parse(jsonData);
                                    console.log(json);
                                    third_carList.show();
                                    third_cpxx.text(car_2);
                                    third_lis.children().remove();
                                    for(var i = 0;i < json.length;i++){
                                        var lis = '<li carId="'+json[i].carId+'">'+json[i].carName+'</li>';
                                        third_lis.append(lis);
                                    }
                                    var third_ul_li = $(".third_carList ul li");
                                    third_ul_li.on("click",function () {
                                        car_3 = "";
                                        car_3 = $(this).text();
                                        var carIds = $(this).attr("carId");
                                        //------------------------------------------------------------------------------------------------------------------车辆信息全部数据上传
                                        $.ajax({
                                            type: 'POST',
                                            url: '/shopAjax',
                                            data: {
                                                businessType: "shoppersoncenter",
                                                servicetype: "updateCarType",
                                                carModelId: carIds, //车型编码
                                                carSeriesId: carId,//车系编码
                                                carBrandId: car_id,//品牌编码
                                                id:khID //客户ID
                                            },
                                            async: false,
                                            success: function (jsonData) {
                                                car_brand.hide();
                                                second_carList.hide();
                                                third_carList.hide();
                                                $("body").scrollTop(0)
                                                var clpp_txts = car_1 + car_2 + car_3;
                                                clpp_txt.text(clpp_txts);
                                                clpp_txt.css({
                                                    color: "#6c6c6c"
                                                });

                                            }
                                        });

                                    })
                                }
                            });

                        })
                    }
                });
            });
        }
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

    // JsBarcode(".bar_code","0562589464631",options);
    //----------------------------------------------------------------------------------------动态添加联盟卡的方法
    function add_lmCards(arr) {
        // //条形码的样式
        // var options = {
        //     format: "CODE128",
        //     displayValue: false,
        //     height: 100,
        //     background: "#fff",
        //     lineColor: "#4c4c4c"//条形码颜色
        // };
        for (var i = 0; i < arr.length; i++) {
            var falge = false;
            for (var j = 0;j < arr[i].leftMx.length;j++) {
                var yxrqDateTime = arr[i].leftMx[j].effective_date;
                var dates = dateFormat(yxrqDateTime);
                var cc = dates.replace(/-/ig,"/");
                var news = new Date();
                var e = news.getFullYear();
                var f = news.getMonth()+1;
                var g = news.getDate();
                var h = e + "/" + f + "/" +g;
                // console.log(h);
                var newDate = new Date(h);
                var oldDate = new Date(cc);
                if(arr[i].leftMx[j].current_num != 0 && arr[i].leftMx[j].current_num != ""){
                    if(oldDate >= newDate){
                    falge = true;
                    }
                }
            }
            if(falge){
                var htmls = '';
                htmls += '<li>' +
                    '<img class="bar_code bar_code' + i + '"/>' +
                    '<div class="bar_num font_1 color_4">' + arr[i].card_number + '</div>' +
                    '<div class="cards_name font_3 color_3">' + arr[i].card_name + '</div>' +
                    '</li>';
                uls.append(htmls);
                JsBarcode(".bar_code" + i, arr[i].card_number, options);
            }
        }
    }


    //----------------------------------------------------------------获取车辆品牌部分元素
    app.ItemList = function (data) {
        var list = [];
        var map = {};
        var html;
        html = data.map(function (item) {
            var i = item.lastIndexOf('-');
            var en = item.slice(0, i);  // Angola
            var cn = item.slice(i + 1); // 安哥拉
            var ch = en[0]; // A
            var num = en.substring(1);
            if (map[ch]) {
                return '<li class="car_list" ids="' + num + '">' + cn + '</li>'
            } else {
                map[ch] = true;
                return '<li data-cw="' + ch + '" class="ch">' + ch + '</li><li data-ch="' + ch + '" class="car_list" ids="' + num + '">' + cn + '</li>'
            }

        }).join('');
        var elItemList = document.querySelector('#item-container ul');
        var a = document.querySelector('#item-container');
        elItemList.innerHTML = html;
        return {
            gotoChar: function (ch) {
                if (ch === '*') {
                    console.log(elItemList.scrollTop);
                    a.scrollTop = 0;
                } else if (ch === '#') {
                    a.scrollTop = elItemList.scrollHeight;
                } else {
                    var target = elItemList.querySelector('[data-cw="' + ch + '"]');//检索的目标
                    if (target) {
                        target.scrollIntoView();
                    }
                }
            }
        }
    };
    app.main = function () {
        var itemList = app.ItemList(app.data);
        new IndexSidebar().on('charChange', itemList.gotoChar);

    };
    // app.main();


    // $(".index-sidebar-container").css("display","none")//------------------------------默认隐藏


    jkzs.on("click", function () {
        window.location.href = "/shopweixinServlet?serviceType=AHIInfo";
    });


    //-----------------------------当前里程用户修改输入事件
    lcs.on("click", function (e) {
        mileage.show();
        mileage_num.val("");
        e.preventDefault();
    });
    //----------------------------------------------------------------------取消点击-里程数
    qx.on("click", function () {
        mileage.hide();
        mileage_num.val("")
    });
    //----------------------------------------------------------------------确定点击-里程数
    qd.on("click", function () {
        var reg = /^[1-9]\d*$|^0$/; // 判断是否为数字的正则
        var gls_val = mileage_num.val();  //-------------------------------------------获取的用户输入的公里数的数字
        if (reg.test(gls_val) == true) {
            //------------------------------------------------------------------------------------------上传里程数数据
            $.ajax({
                type: 'POST',
                url: '/shopAjax',
                data: {
                    businessType: "shoppersoncenter",
                    servicetype: "currentmileage",
                    kilo: gls_val, //公里数
                    id:khID //客户ID
                },
                success: function (jsonData) {
                    var txt = gls_val + "km";
                    lcs_num.text(txt);
                    lcs_num.css({
                        color: "#6c6c6c"
                    });
                    lcs_num.removeClass("font_4").addClass("font_1");
                    mileage.hide()
                }
            });  //-------------------------------------------上传结束

        } else {
            alert("请输入正确的里程数~");
            mileage_num.val("")
        }

    })
    //---------------------------------------------------------------------------输入框的聚焦和失焦
    mileage_num.on("focus", function () {
        $(this).css({
            "border": "1px #58b5ff solid"
        })
    }).on("blur", function () {
        $(this).css({
            "border": "1px #B3B3B3 solid"
        })
    });
    //---------------------------------------------------------------------------------------------------------------车系二级页面的返回按钮
    second_back.on("click",function () {
        second_carList.hide();
        $(".index-sidebar-container").css("display","block")
    });
    // second_carList.on("touchmove",function(e){
    //     e.preventDefault(); //遮罩层出现后禁止body滑动
    //     return false
    // });
    //---------------------------------------------------------------------------------------------------------------车系三级页面的返回按钮
    third_back.on("click",function () {
        third_carList.hide();
    });
    // third_carList.on("touchmove",function(e){
    //     e.preventDefault(); //遮罩层出现后禁止body滑动
    //     return false
    // });
})