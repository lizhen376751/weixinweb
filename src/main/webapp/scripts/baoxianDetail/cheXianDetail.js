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
//---------------------------------------------------------------------------------页面加载完在执行
$(document).ready(function () {
    var basic = $(".basic") //-----------------------------------------------------获取基本信息按钮
    var coverage = $(".coverage") //-----------------------------------------------获取险种信息按钮
    var bxgs = $(".bxgs"); //--------------------------------------------------------获取修改保险公司框
    var quote = $(".quote");//--------------------------------------------------------获取是否报价框
    var bfhj = $(".bfhj"); //-------------------------------------------------------获取保费合计框
    var syx = $(".syx"); //--------------------------------------------------------------获取商业险框
    var jqx = $(".jqx"); //---------------------------------------------------------------获取交强险框
    var xm = $(".xm"); //---------------------------------------------------------------获取修改姓名框
    var cph = $(".cph"); //--------------------------------------------------------------获取修改车牌号框
    var sjh = $(".sjh"); //-------------------------------------------------------------获取修改手机号框
    var clsbdm = $(".clsbdm"); //--------------------------------------------------------获取识别代号框
    var fdjxh = $(".fdjxh"); //------------------------------------------------------------获取发动机号框
    var syxz = $(".syxz");//-------------------------------------------------------------获取使用性质框
    var zcrq = $(".zcrq");//----------------------------------------------------------------获取注册日期框
    var coverage_infor_ul = $(".coverage_infor ul");//---------------------------------------------获取险种信息的ul
    //获取上个页面传过来的参数
    var bxdqdate = $("#bxdqdate").val(); //-----保险到期时间(联盟设置的保险公司给的到期时间)
    var bjstate = $("#bjstate").val();  //------报价状态
    var fkstate = $("#fkstate").val();  //------付款状态
    if (bjstate == 0) {
        quote.text("报价中");
        $(".tb_btn").hide();
    } else if (bjstate == 1){
        quote.text("报价成功");
        $(".tb_btn").show();
    }else {
        quote.text("已投保");
        $(".tb_btn").hide();
    }

    var plateNumber = getvl("carId");   //------------------------------------------------------------获取的网址上的车牌号
    var shopCode = getvl("shopCode");		//------------------------------------------------------------获取的网址上的shopcode
    var shopCodeLm = getvl("shopcodelm");//------------------------------------------------------------获取的网址上的联盟code
    var orderNumb = getvl("orderNumb");//------------------------------------------------------------获取的网址上的订单编号
    var companyId = getvl("companyid");//------------------------------------------------------------获取的网址上的保险公司id
    var totalPrice = "";//----------------------------------------------------------------------------记录投保价格
    console.log('/findClientInsurance/' + shopCode + '/' + shopCodeLm + '/' + companyId + '/' + orderNumb + '/' + plateNumber);
    //-----------------------------------------------------------------------------------------------------------请求页面加载数据
    var tbstate = false;
    $.ajax({
        type: 'get',
        url: '/findClientInsurance/' + shopCode + '/' + shopCodeLm + '/' + companyId + '/' + orderNumb + '/' + decodeURI(plateNumber),
        async: false,
        success: function (jsondata) {
            var json = JSON.parse(jsondata);
            console.log(json);
            bxgs.text(json.insuranceCompanyName);
            totalPrice = json.totalPrice;
            bfhj.text("¥" + json.totalPrice);
            if (json.insuranceCompanyHaveChosen == null) {
                tbstate = true;
            } else {
                tbstate = false;
                $(".tb_btn").css("background", "#c3c6c8")  //投保按钮变为灰色
            }
            if (json.customerModel != null && json.customerModel != "" && json.customerModel != undefined) {
                if (json.customerModel.customerName != null && json.customerModel.customerName != "" && json.customerModel.customerName != undefined) {
                    xm.text(json.customerModel.customerName);
                }
                ;
                if (json.customerModel.plateNumber != null && json.customerModel.plateNumber != "" && json.customerModel.plateNumber != undefined) {
                    cph.text(json.customerModel.plateNumber);
                }
                ;
                if (json.customerModel.customerMobile != null && json.customerModel.customerMobile != "" && json.customerModel.customerMobile != undefined) {
                    sjh.text(json.customerModel.customerMobile);
                }
                ;
                if (json.customerModel.createTime != null && json.customerModel.createTime != "" && json.customerModel.createTime != undefined) {
                    var datas = dateFormat(json.customerModel.createTime)
                    zcrq.text(datas);
                }
                if (json.customerModel.vehicleIdentificationCode != null && json.customerModel.vehicleIdentificationCode != "" && json.customerModel.vehicleIdentificationCode != undefined) {
                    clsbdm.text(json.customerModel.vehicleIdentificationCode);
                }
                ;
                if (json.customerModel.engineNumber != null && json.customerModel.engineNumber != "" && json.customerModel.engineNumber != undefined) {
                    fdjxh.text(json.customerModel.engineNumber);
                }
                if (json.customerModel.operationType != null && json.customerModel.operationType != "" && json.customerModel.operationType != undefined) {
                    syxz.text(json.customerModel.operationType);
                }
                if (fkstate == 1) {
                    if (bxdqdate != null && bxdqdate != "" && bxdqdate != undefined) {
                        syx.text(bxdqdate);
                    }
                    if (bxdqdate != null && bxdqdate != "" && bxdqdate != undefined) {
                        jqx.text(bxdqdate);
                    }
                }
            }

            if (json.list) {
                add_xzxx(json.list);
            }


        },
        error: function (eee) {

        }
    });

    //-----------------------------------------------------------------------------------------------点击基本信息
    basic.on("click", function () {
        coverage.removeClass("color_7").addClass("color_9");
        $(this).removeClass("color_9").addClass("color_7");
    });
    //-----------------------------------------------------------------------------------------------点击险种详情
    coverage.on("click", function () {
        basic.removeClass("color_7").addClass("color_9");
        $(this).removeClass("color_9").addClass("color_7");
    })

    //-----------------------------------------------------------------------------实现滑动效果
    var mySwiper = new Swiper('.swiper-container', {
        prevButton: '.basic',
        nextButton: '.coverage',
        //控制翻页所调用的函数
        onSlideChangeEnd: function (swiper) {
            if (swiper.activeIndex == 0) {
                coverage.removeClass("color_7").addClass("color_9");
                basic.removeClass("color_9").addClass("color_7");
            } else {
                basic.removeClass("color_7").addClass("color_9");
                coverage.removeClass("color_9").addClass("color_7");
            }
        }
    })

    //时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date = y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }

    //---------------------------------------------------------------------------时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date = y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }

    //-------------------------------------------------------------------------------------截取网址传过来的变量
    function getvl(name) {
        var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
        if (reg.test(location.href)) return decodeURI(RegExp.$2.replace(/\+/g, " "));
        return "";
    };

//	-------------------------------------------------------------------------------------------添加险种信息
    function add_xzxx(arr) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].insuranceAmount == null) {
                var span = '<span class="be">0</span>'
            } else {
                var span = '<span class="be">' + arr[i].insuranceAmount + '</span>'
            }
            if (arr[i].exclusion == 1) {
                var htmls = "";
                htmls += '<li class="color_9">' +
                    '<span class="xz">' + arr[i].insuranceTypeName + '</span>' +
                    '<div>' + span +
                    '<span class="mp">是</span>' +
                    '<span>' + arr[i].insuranceCost + '</span>' +
                    '</div>' +
                    '</li>';
            } else {
                var htmls = "";
                htmls += '<li class="color_9">' +
                    '<span class="xz">' + arr[i].insuranceTypeName + '</span>' +
                    '<div>' + span +
                    '<span class="mp">否</span>' +
                    '<span>' + arr[i].insuranceCost + '</span>' +
                    '</div>' +
                    '</li>';
            }
            coverage_infor_ul.append(htmls);
        }
    }

//	----------------------------------------------------------投保按钮提交
    $(".tb_btn").on("click", function () {
        if (tbstate) {
            $.ajax({
                type: 'post',
                url: '/getCommonAjax',
                data: {
                    fromflag: "toubao",
                    shopCode: shopCode,
                    shopCodeLm: shopCodeLm,
                    orderNumb: orderNumb,
                    companyId: companyId,
                    totalPrice: totalPrice
                },
                async: false,
                success: function (jsondata) {
                    var json = JSON.parse(jsondata);
                    console.log(json);
                    if (jsondata == 1) {
                        alert("投保成功")
                        window.location.href = "/queryInsurance";
                    } else {
                        alert("投保失败，请重试");
                    }


                },
                error: function (eee) {

                }
            });
        } else {
            //  alert("您已投保，请勿重复投保！")
        }

    })

})





















