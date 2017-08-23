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
$(window).load(function () {
    setTimeout(function () {
        $("#loading").hide();
    }, 1000);
});
$(function () {
    var shopCode = $(".shopCode"); //----------------------------------------------------------店铺编码
    var head = $(".n-head");//------------------------------------------------------------------获取头像框
    var car_num = $(".car_num"); //-------------------------------------------------------------获取车牌号信息框
    var car_name = $(".nb-text");//--------------------------------------------------------------获取车辆信息框
    var car_name_bj = $(".n-carnum .nr-bj ,.nb-text");//-----------------------------------------------------获取车辆信息编辑框
    var kilometre = $(".nb-num");//-------------------------------------------------------------获取公里数信息框
    var kilometre_bj = $(".xs .nr-bj ,.nb-num");//-------------------------------------------------------获取公里数信息编辑框
    var kilometre_sure = $(".n-image");
    var jkzs = $(".jkzs");//--------------------------------------------------------------------获取健康指数li
    var jkzs_num = $(".jkzs_num");//-----------------------------------------------------------获取健康指数分数框
    var bytx = $(".bytx");//--------------------------------------------------------------------获取保养提醒li
    var bytx_num = $(".bytx_num");//-----------------------------------------------------------获取保养提醒公里框
    var xmk = $(".xmk");//---------------------------------------------------------------------获取项目卡li
    var czk = $(".czk");//---------------------------------------------------------------------获取充值卡li
    var djq = $(".djq");//---------------------------------------------------------------------获取代金券li
    var djq_num = $(".djq_num");//-------------------------------------------------------------获取代金券剩余数量框
    var hydj_num = $(".hydj_num");//-----------------------------------------------------------获取会员等级框
    var hyjf_num = $(".hyjf_num");//-----------------------------------------------------------获取会员积分框
    var zsje = $(".zsje");//-------------------------------------------------------------------获取赠送金额框
    var djje = $(".djje");//-------------------------------------------------------------------获取定金金额框
    var quit = $(".b-btn");//-------------------------------------------------------------------获取退出登录按钮

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

    //个人信息请求
    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shoppersoncenter",
            servicetype: "personcenter"
        },
        async: false,
        success: function (jsonData) {
            json = JSON.parse(jsonData);
           // console.log(json);
            car_num.text(json.plateNumber);    //-------------------------------------------------------------动态添加车牌号码
            var cltxt = "";//---------------------------------------------------------------定义车牌信息添加的字符串
            khID = json.id;
            //------------------------------------------------------------------------------------------------车系品牌添加
            if (json.carBrand != null || json.carModel != null || json.carSeries != null) {
                if (json.carBrand == null) {
                    json.carBrand = "";
                }
                if (json.carModel == null) {
                    json.carModel = "";
                }
                if (json.carSeries == null) {
                    json.carSeries = "";
                }
                var cltxt = json.carBrand + json.carSeries + json.carModel;
                car_name.text(cltxt);
            }

            //-----------------------------------------------------------------------------------------------当前里程(保养提醒)判断
            if (json.currentmileage != null && json.currentmileage != "") {
                kilometre.text(json.currentmileage + "km");
            };
            if (json.point != null && json.point != "") {
                jkzs_num.text(json.point);//---------------------------------------------------------------------------------健康指数动态添加
            };

            if (json.levelName != null && json.levelName != "") {
                hydj_num.text(json.levelName);//---------------------------------------------------------------------------------会员等级
            } else {
                hydj_num.text("非会员");
            };
        }
    });
    //--------------------------------------------------------------------------------------------------------------------------个人中心的请求
    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shoppersoncenter",
            servicetype: "personalRightsAndInterests",
            shopCode: shopCode.val(),
            customerId: khID,
            plateNumb: car_num.text()
            /*shopCode: "0533001",
             customerId : 28763,
             plateNumb: "闽A12121"*/
        },
        success: function (jsonData) {
            json = JSON.parse(jsonData);
           //console.log(json)
            //赠送金额剩余
            if (json.giftMoney != null && json.giftMoney != '') {
                zsje.text("￥" + json.giftMoney);
            }
            //当前定金金额
            if (json.deposit != null && json.deposit != "") {
                djje.text("￥" + json.deposit);
            }
            //会员积分
            if (json.memberPoints != null && json.memberPoints != "") {
                hyjf_num.text(json.memberPoints + "分");
            } else {
                hyjf_num.text("0分");
            }
            //特种卡
            // if(json.specialCardAmount != null && json.specialCardAmount != ""){
            //     $(".tzk").text("￥"+json.specialCardAmount)
            // }
            // 代金券
            djq_num.text("剩余" + json.daiJinQuanNumber + "张");
        }
    });
    //--------------------------------------------------------------------------------------------------------------------------车型车系的请求
    var app = app || {};
    app.data = []; //-----------------------------------------------------------------------------------------------------车牌号集合:奥迪
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
                    //  console.log(elItemList.scrollTop);
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
    for (var g = 1; g < 5; g++) {
        $.ajax({
            type: 'POST',
            url: '/pagingquery',
            data: {
                businessType: "shoppersoncenter",
                servicetype: "carType",
                type: "CarBrand",
                num: 0,
                page: "" + g + "",
                rows: "50"
            },
            async: false,
            success: function (jsonData) {
                json = JSON.parse(jsonData);
                //  console.log(json);
                for (var i = 0; i < json.rows.length; i++) {
                    var zifu = json.rows[i].carFirst + json.rows[i].carId + "-" + json.rows[i].carName;
                    app.data.push(zifu);
                }
                // console.log(app.data);
                if (g == 4) {
                    app.main();
                    $(".index-sidebar-container").css("display", "none")
                    var car_brand = $(".car_brand"); //----------------------------------------------------车系品牌遮罩层
                    var item_container_li = $("#item-container ul li") //--------------------------------- 获取每一个车牌号:奥迪
                    //---------------------------------------------------------------------------------------点击出现车辆品牌分类
                    car_name_bj.on("click", function () {
                        car_brand.show();
                        $(".index-sidebar-container").css("display", "block")
                    });
                    //----------------------------------------------------------------------------------------点击获取进一步的信息
                    item_container_li.on("click", function () {
                        car_1 = "";
                        car_1 = $(this).text();
                        var car_id = $(this).attr("ids");
                        // console.log(car_id);
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
                                // console.log(json);
                                second_carList.show();
                                second_cpxx.text(car_1);
                                second_lis.children().remove();
                                for (var i = 0; i < json.rows.length; i++) {
                                    var lis = '<li carId="' + json.rows[i].carId + '">' + json.rows[i].carName + '</li>';
                                    second_lis.append(lis);
                                }
                                var second_ul_li = $(".second_carList ul li");
                                second_ul_li.on("click", function () {
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
                                            //  console.log(json);
                                            third_carList.show();
                                            third_cpxx.text(car_2);
                                            third_lis.children().remove();
                                            for (var i = 0; i < json.rows.length; i++) {
                                                var lis = '<li carId="' + json.rows[i].carId + '">' + json.rows[i].carName + '</li>';
                                                third_lis.append(lis);
                                            }
                                            var third_ul_li = $(".third_carList ul li");
                                            third_ul_li.on("click", function () {
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
                                                        id: khID //客户ID
                                                    },
                                                    async: false,
                                                    success: function (jsonData) {
                                                        car_brand.hide();
                                                        second_carList.hide();
                                                        third_carList.hide();
                                                        $("body").scrollTop(0)
                                                        var clpp_txts = car_1 + car_2 + car_3;
                                                        car_name.text(clpp_txts);  //给车牌赋值
                                                        car_name.css({
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
            }
        });
    }
    //---------------------------------------------------------------------------------------------------------------车系二级页面的返回按钮
    second_back.on("click", function () {
        second_carList.hide();
        $(".index-sidebar-container").css("display", "block")
    });

    //---------------------------------------------------------------------------------------------------------------车系三级页面的返回按钮
    third_back.on("click", function () {
        third_carList.hide();
    });
    //---------------------------------------------------------------------------------------------------------------公里数修改
    kilometre_bj.on("click", function () {
        $(".xs").hide();
        $(".xg").show();
    });
    //---------------------------------------------------------------------------------------------------------------公里数确定修改按钮
    kilometre_sure.on("click", function () {
        var oneself = this;
        var reg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
        var gl_num_val = $("#gl_num").val();
        if (reg.test(gl_num_val)) {
            $(".n-dd").show();
            $(oneself).hide();
            var k = 0;
            var timer = setInterval(function () {
                k = k + 20;
                $(".n-dd")[0].style.transform = 'rotateZ(' + k + 'deg)';
            }, 100);
            //------------------------------------------------------------------------------------------上传里程数数据
            $.ajax({
                type: 'POST',
                url: '/shopAjax',
                data: {
                    businessType: "shoppersoncenter",
                    servicetype: "currentmileage",
                    kilo: gl_num_val, //公里数
                    id: khID //客户ID
                },
                success: function (jsonData) {
                    $(".nb-num").text(gl_num_val + "km");
                    $(".xs").show();
                    $(".xg").hide();
                    $(".n-dd").hide();
                    $(oneself).show();
                    $("#gl_num").val("");
                    clearInterval(timer);
                }
            });  //-------------------------------------------上传结束
        } else {
            alert("请输入正确的里程数~");
            $("#gl_num").val("");
        }


    })
    //车辆健康指数点击按钮
    jkzs.on("click", function () {
        window.location.href = "/shopweixinServlet?serviceType=AHIInfo"
    });
    //保养提醒按钮点击
    bytx.on("click", function () {
        window.location.href = "/shopweixinServlet?serviceType=BaoYangTiXing"
    });
    //项目卡按钮点击
    xmk.on("click", function () {
        window.location.href = "/shopweixinServlet?serviceType=projectCard&customerId="+khID
    });
    //充值卡按钮点击
    czk.on("click", function () {
        window.location.href = "/shopweixinServlet?serviceType=projectCZHICard&customerId="+khID

    });
//退出账号按钮点击
    quit.on("click",function () {
        window.location.href = "/shopweixinServlet?serviceType=signout";
    })

});