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


$(document).ready(function () {
    var card_num = $(".car_num");  //-------------------------------------------------------------获取用户输入的联盟卡号
    var activate_num = $(".password_num");   //---------------------------------------------------获取用户输入的激活码
    var activate = $(".activate")  //-------------------------------------------------------------获取激活按钮
    var tc_ceng = $(".tcc");  //-------------------------------------------------获取弹出层
    var l_box = $(".l_box"); //-------------------------------------------------获取已注册，没有密码框
    var b_box = $(".b_box");  //------------------------------------------------获取已注册，有密码框
    var close = $(".close"); //---------------------------------------------------获取遮罩层中红色关闭按钮
    var determine = $(".l_qdan") //----------------------------------------------获取遮罩层中卡激活，没有激活的确定按钮
    var sign_in = $(".l_qdl");  //-----------------------------------------------获取遮罩层中卡激活，且有密码的去登陆按钮
    var ty = $(".ty"); //---------------------------------------------------------------------获取统一弹出层
    var tyt = $(".tyt") //--------------------------------------------------------------------获取统一弹出层的确定
    var llt = $(".ty .l_tt") //---------------------------------------------------------------获取修改提示内容框
    var car_tsk = $(".car_tsk") //------------------------------------------------------------获取联盟卡号提示框
    var password_tsk = $(".pasword_tsk") //-----------------------------------------------------获取激活码提示框
    var sys_1 = $(".sys_1"); //------------------------------------------------------------------获取联盟卡号的扫一扫
    var sys_2 = $(".sys_2");//-------------------------------------------------------------------获取激活码的扫一扫

    var flag = true; //-------------------------------------------------------------------------定义扫一扫状态值
    //-------------------------------------------------------------提前获取参数
    var url = location.href.split('#')[0];
    //---------------------------------------------------------------------------------获取授权参数
    $.ajax({
        url: "/getCommonAjax2",
        type: 'POST',
        data: {
            url: url,
            fromflag: "jssdk"
        },
        success: function (data) {
            console.log(data);
            wx.config({
                debug: false,//true为开启调试模式，数据会alert出来，false为关闭调试模式
                appId: data.appId, //公众号的唯一标示，必填
                timestamp: data.timestamp, //生成签名的时间戳，必填
                nonceStr: data.nonceStr,   //生成签名的随机串，必填
                signature: data.signature,  // 签名，必填
                jsApiList: ['scanQRCode']    //--------------------------------- // 所有要调用的 API 都要加到这个列表中
            });
        },
        dataType: 'json',
        async: false
    });
    sys_1.on("click", function (e) {
        if(flag){
            flag = false;
            wx_sys(card_num);
        }
        // wx_sys(card_num);
        e.stopPropagation();
        // return false;
    });
    sys_2.on("click", function (e) {
        if(flag){
            flag = false;
            wx_sys(activate_num);
        }
        e.stopPropagation();
        // return false;
    });


    //-------------------------------------------------------------------------------------------------扫一扫功能函数，扫出的结果放到该形参中
    function wx_sys(label) {
        //    ----------------------------------------------------------------------------------------------------扫一扫接口调用
        wx.scanQRCode({
            needResult: 1, //-------------------------默认为0，扫描结果由微信处理，1则是自己处理结果
            desc: 'scanQRCode desc',
            success: function (res) {
                console.log(res);
                var url = res.resultStr;
                if (url.indexOf(",") >= 0) {
                    var tempArray = url.split(',');
                    var tempNum = tempArray[1];
                    label.val(tempNum);
                } else {
                    label.val(url);
                }
                flag = true;
            }
        });
    }

    //------------------------------------------------------------------------JS控制的css样式
    card_num.on("focus", function () {
        $(this).css({
            "border": "0.03rem #6cd2fd solid",
            "lineHeight": "1.05rem",
        })
        $(this).toggleClass("input_bg")
    })
    card_num.on("blur", function () {
        $(this).css({
            "border": "none",
            "lineHeight": "1.1rem",
        });
        $(this).toggleClass("input_bg")
    })

    activate_num.on("focus", function () {
        $(this).css({
            "border": "0.03rem #6cd2fd solid",
            "lineHeight": "1.05rem",
        })
        $(this).toggleClass("input_bg")
    })
    activate_num.on("blur", function () {
        $(this).css({
            "border": "none",
            "lineHeight": "1.1rem",
        });
        $(this).toggleClass("input_bg")
    })
    card_num.on("keyup", function () {
        if ($(this).val().length != 0) {
            car_tsk.hide();
        }
    });
    activate_num.on("keyup", function () {
        if ($(this).val().length != 0) {
            password_tsk.hide();
        }
    })

    //----------------------------------------------------------------------------激活按钮点击判断
    activate.on("click", function () {
        var card_value = card_num.val();
        var activate_value = activate_num.val();
        if (card_value == "") {
            car_tsk.show();
            car_tsk.text("请输入联盟卡号");
            // tc_ceng.show();
            // ty.show();
            // tyt.show();
            // llt.text("联盟卡号有误，请重新输入~");
            // alert("联盟卡号有误，请重新输入~")
        } else if (activate_value == "") {
            password_tsk.show();
            password_tsk.text("请输入激活码");
        } else if (card_value.length > 20) {
            tc_ceng.show();
            l_box.show();
        } else if (activate_value.length > 10) {
            tc_ceng.show();
            l_box.show();
            // tc_ceng.show();
            // ty.show();
            // tyt.show();
            // llt.text("输入激活码有误，请重新输入~");
            // alert("输入激活码有误，请重新输入~")
        } else {
            $.ajax({
                type: 'POST',
                url: '/getCommonAjax2',
                data: {
                    fromflag: "lianMengCardActivate",
                    cardnum: card_value,
                    activecode: activate_value
                },
                success: function (jsondata) {
                    console.log(jsondata);
                    var backdata = JSON.parse(jsondata);
                    if (backdata == "0") {
                        tc_ceng.show();
                        l_box.show();
                        // setTimeout(function () {
                        //     tc_ceng.hide();
                        //     l_box.hide();
                        //     b_box.hide();
                        //     card_num.val("");
                        //     activate_num.val("")
                        // }, 3000);
                    } else if (backdata == "1") {
                        tc_ceng.show();
                        ty.show();
                        tyt.show();
                        llt.text("该卡号已经激活!");
                        // alert("该卡号已经激活!");
                    } else if (backdata == "2") {
                        tc_ceng.show();
                        b_box.show();
                    }
                }
            });
        }

    })

    //----------------------------------------------------------------------------------遮罩层中红色关闭按钮点击
    close.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
    })
    //------------------------------------------------------------------------------------联盟卡激活失败后的确定按钮
    determine.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
        // card_num.val("");
        // activate_num.val("")
    })
    //------------------------------------------------------------------------------------联盟卡激活成功后的确定按钮
    sign_in.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
        //----------------------------------------------------------------------------------激活成功后跳转的页面
        window.location.href = "/oauthLoginServlet?flagStr=personalCenter";
    })
    tyt.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
    })

})




