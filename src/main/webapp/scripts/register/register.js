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
    var car_num = $(".car_num")  //-----------------------------------------------获取车牌号输入框
    var count_password = $(".count_password"); //----------------------------------获取账户密码输入框
    var count_phone = $(".count_phone");  //-----------------------------------获取用户手机号
    var eye = $(".eye");  //----------------------------------------------------获取页面上的密码处的眼睛
    var verification_code = $(".verification_code");  //------------------------获取验证码输入框
    var yzm = $(".yzm");  //-------------------------------------------------------获取发送验证码按钮
    var tjxx = $(".tjxx") //----------------------------------------------------------获取提交信息按钮
    var tc_ceng = $(".tcc");  //-------------------------------------------------获取弹出层
    var l_box = $(".l_box"); //-------------------------------------------------获取已注册，没有密码框
    var b_box = $(".b_box");  //------------------------------------------------获取已注册，有密码框
    var close = $(".close"); //---------------------------------------------------获取遮罩层中红色关闭按钮
    var moblie_num = $(".mobile_num"); //-----------------------------------------获取遮罩层中用户注册，且没密码的手机号码标签
    var determine = $(".l_qdan") //----------------------------------------------获取遮罩层中用户注册，且没密码的确定按钮
    var sign_in = $(".l_qdl");  //-----------------------------------------------获取遮罩层中用户注册，且有密码的去登陆按钮

    //----------------------------密码改为明文或暗文
    var eye_state = true;  //---------------------------记录眼睛的开关状态
    eye.on("click", function () {
        if (eye_state == true) {
            this.src = "/files/register/xsmm.png";
            count_password.attr("type", "text")
            eye_state = false;
        } else {
            this.src = "/files/register/gbmm.png";
            count_password.attr("type", "password")
            eye_state = true
        }
    })
    //----------------------------------------------------------判断车牌号是否注册函数
    var b = "";

    function car_judge() {
        var car_value = car_num.val();
        if (car_value == "") {
            alert("请输入车牌号~")
        } else {

            $.ajax({
                type: 'POST',
                url: '/getCommonAjax2',
                data: {
                    fromflag: "checkInfo",
                    lmcode: "CS000", //TODO 暂时写死
                    platenumber: car_value
                },
                success: function (jsonData) {
                    var jsonData = JSON.parse(jsonData);
                    if (jsonData == "0") {
                        return false;
                    } else if (jsonData == "1") {             //----------------------------------------------------------没密码

                        $.ajax({
                            type: 'POST',
                            url: '/getCommonAjax2',
                            data: {
                                fromflag: "getmobiePhone",
                                lmcode: "CS000", //TODO 暂时写死
                                platenumber: car_value
                            },
                            success: function (jsonData) {
                                b = JSON.parse(jsonData);
                                var reg = b.substr(3, 4);
                                var c = b.replace(reg, "****");
                                moblie_num.text(c);
                                tc_ceng.show();
                                l_box.show()
                            }

                        });


                    } else if (jsonData == "2") {             //-----------------------------------------------------------有密码
                        tc_ceng.show();
                        b_box.show()
                    }
                }

            });

            //------------------------------------------------------------------------手机号字符拼接*号


        }
    }

    //------------------------------------------------------------------------------------点击验证码进行倒计时函数
    function count_down(values) {
        var s = 60;
        var timer = setInterval(function () {
            var txt = s + "s";
            yzm.text(txt);
            s -= 1;
            if (s == -2) {
                clearInterval(timer)
                yzm.text("发送验证码");
                yzm_start = true;
            }
        }, 1000)
    }

    //--------------------------------------------------------------------------点击账户密码进行判断
    count_password.on("focus", function () {
        car_judge()
    })
    //-------------------------------------------------------------------------点击输入手机号码
    count_phone.on("focus", function () {
        car_judge()
    });
    //--------------------------------------------------------------------------输入手机号进行正则判断
    count_phone.on("keyup", function () {
        if ($(this).val().length >= 11) {
            var regs = /^1(3|4|5|7|8)[0-9]\d{8}$/;
            if (regs.test($(this).val())) {
                return false;
            } else {
                alert("输入手机号码有误，请重新输入！");
            }
        }
    })
    //-------------------------------------------------------------------------点击输入验证码进行判断
    verification_code.on("focus", function () {
        car_judge()
    })
    //-------------------------------------------------------------------------点击发送验证码进行判断
    var yzm_start = true;  //------------------------------------------------------定义当前点击状态是否可以点击
    yzm.on("click", function () {
        if (yzm_start == true) {
            yzm_start = false;
            car_judge();
            var password_value = count_password.val();
            var phone_value = count_phone.val();
            var platenumber = car_num.val();
            if (password_value == "") {
                alert("请设置您的账户密码~")
            } else if (phone_value == "") {
                alert("请输入您的手机号码~")
            } else {
                //-----------------------------------------------------请求发送验证码
                count_down(phone_value);
                $.ajax({
                    type: 'POST',
                    url: '/getCommonAjax2',
                    data: {
                        fromflag: "addvalidate",
                        lmcode: "CS000", //TODO 暂时写死
                        platenumber: platenumber,
                        mobilephone: phone_value
                    },
                    success: function (jsonData) {

                    }

                });


            }
        }

    })
    //------------------------------------------------------------------------------------提交信息的判断
    tjxx.on("click", function () {
        car_judge();
        var password_value = count_password.val();
        var phone_value = count_phone.val();
        var verification_value = verification_code.val();
        var platenumber = car_num.val();
        if (password_value == "") {
            alert("请设置您的账户密码~")
        } else if (phone_value == "") {
            alert("请输入您的手机号码~")
        } else if (verification_value == "") {
            alert("请输入验证码~")
        } else {
            //----------------------------------------------------------------------------ajax提交信息
            $.ajax({
                type: 'POST',
                url: '/getCommonAjax2',
                data: {
                    fromflag: "register",
                    platenumber: platenumber,
                    lmcode: "CS000", //TODO 暂时写死
                    password: password_value,
                    openid: "owQtWt8L6RVxj_cTUaPyH27RWdbA",//TODO 暂时写死
                    mobilephone: phone_value,
                    verificationCode: verification_value
                },
                success: function (jsonData) {
                    var backdata = jsonData;
                    if(backdata == "3"){
                        window.location.href = "";
                    }else if(backdata == "4"){
                        alert("输入验证码有误，请重新输入！")
                    }
                }

            });
        }
    })
    //----------------------------------------------------------------------------------遮罩层中红色关闭按钮点击
    close.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide()
    })
    //------------------------------------------------------------------------------------获取遮罩层中用户注册，且没密码的确定按钮的点击
    determine.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide()
        window.location.href = "/oauthLoginServlet?flagStr=suresms&platenumber=" + car_num.val() + "&lmcode=CS000&mobilephone=" + b;
    })

    //------------------------------------------------------------------------------------获取遮罩层中用户注册，且有密码的确定去登陆的点击
    sign_in.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide()
//		window.location.href = "";
    })


})



























































