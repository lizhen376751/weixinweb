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
    var lmcode = $("#lmcode").val(); //-------------------------------------------------------------获取lmcode
    var car_num = $(".car_num");  //-------------------------------------------------------------获取用户输入的车牌号
    var password_num = $(".password_num");   //---------------------------------------------------获取用户输入的账号密码
    var login = $(".login"); //------------------------------------------------------------------获取登录按钮
    var register = $(".register");   //----------------------------------------------------------获取注册按钮
    var tc_ceng = $(".tcc");  //-------------------------------------------------获取弹出层
    var l_box = $(".l_box"); //-------------------------------------------------获取已注册，没有密码框
    var b_box = $(".b_box");  //------------------------------------------------获取已注册，有密码框
    var close = $(".close"); //---------------------------------------------------获取遮罩层中红色关闭按钮
    var moblie_num = $(".mobile_num"); //-----------------------------------------获取遮罩层中用户注册，且没密码的手机号码标签
    var determine = $(".l_qdan") //----------------------------------------------获取遮罩层中用户注册，且没密码的确定按钮
    var sign_in = $(".l_qdl");  //-----------------------------------------------获取遮罩层中用户注册，且有密码的去注册按钮
    var b = ""; //-----------------------------------------------没有密码的情况下获取的手机号码
    var ty = $(".ty"); //---------------------------------------------------------------------获取统一弹出层
    var tyt = $(".tyt") //--------------------------------------------------------------------获取统一弹出层的确定
    var llt = $(".ty .l_tt") //---------------------------------------------------------------获取修改提示内容框
    var car_tsk = $(".car_tsk") //------------------------------------------------------------获取车牌号提示框
    var password_tsk = $(".pasword_tsk") //-----------------------------------------------------获取密码提示框
    //------------------------------------------------------------------------JS控制的css样式
    car_num.on("focus", function () {
        $(this).css({
            "border": "0.03rem #6cd2fd solid",
            "lineHeight": "1.05rem",
        })
        $(this).toggleClass("input_bg")
    })
    car_num.on("blur", function () {
        $(this).css({
            "border": "none",
            "lineHeight": "1.1rem",
        });
        $(this).toggleClass("input_bg")
    })

    password_num.on("focus", function () {
        $(this).css({
            "border": "0.03rem #6cd2fd solid",
            "lineHeight": "1.05rem",
        })
        $(this).toggleClass("input_bg")
    })
    password_num.on("blur", function () {
        $(this).css({
            "border": "none",
            "lineHeight": "1.1rem",
        });
        $(this).toggleClass("input_bg")
    })

    //----------------------------------------------------------判断车牌号是否注册函数
    // var back = "false";  //--------------------------------------记录状态值
    function car_judge() {
        var car_value = car_num.val();
        if (car_value == "") {
            car_tsk.show();
            car_tsk.text("请输入您的车牌号");
            // tc_ceng.show();
            // ty.show();
            // tyt.show();
            // llt.text("请输入车牌号~");
            // alert("请输入车牌号~");
            // var those = document.getElementsByClassName("car_num")[0];
            // those.select()
        }
    }

    car_num.on("keyup",function(){
        var values = $(this).val();
        var car_see = car_tsk.css("display");
        if(values != ""){
            car_tsk.hide();
        }else if(car_see == "block"){
            car_tsk.hide();
        }
    });
    password_num.on("keyup",function(){
        var values = $(this).val();
        if(values != ""){
            password_tsk.hide();
        }
    });
    //--------------------------------------------------------------------------点击账户密码进行判断
    password_num.on("focus", function () {
        car_judge();
    });


    //----------------------------------------------------------------------------------遮罩层中红色关闭按钮点击
    close.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
    })


    //----------------------------------------------------------------------------------点击登录按钮进行判断
    login.on("click", function () {
        car_judge();
        var car_value = car_num.val();
        var see = car_tsk.css("display");
//		console.log(see)
        if (car_value != "" && see == "none") {
            // back = "false";
            var password_value = password_num.val();
            if (password_value == "") {
                password_tsk.show();
                password_tsk.text("请输入您的密码");
                // tc_ceng.show();
                // ty.show();
                // tyt.show();
                // llt.text("请输入您的账户密码~");
                // alert("请输入您的账户密码~");
            } else {
                //------------------------------------------------------------------------------ajax登录判断
                $.ajax({
                    type: 'POST',
                    url: '/shopAjax',            //望后台传输数据的地址
                    data: {
                        businessType: "login",
                        platenumber: car_value,
                        password: password_value
                    },
                    success: function (jsonData) {
                        var jsonData = JSON.parse(jsonData);
                        if (jsonData == "0") {
                            tc_ceng.show();
                            ty.show();
                            tyt.show();
                        }else if (jsonData == "1"){
                            window.location.href = "/shopweixinServlet?serviceType=shoppersoncenter";
                        }else if (jsonData == "2"){
                            window.location.href = "/shopweixinServlet?serviceType=login";  //网络错误
                        }


                    }
                });
            }
        }

    })
    //--------------------------------------------------------------------------------------点击注册按钮跳转
    register.on("click", function () {
        window.location.href = "/shopweixinServlet?serviceType=register";
    });

    //------------------------------------------------------------------------------------获取遮罩层中用户注册，且没密码的确定按钮的点击
    determine.on("click", function (e) {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
        $.ajax({                //---------------------------------------------------------------ajax确定发送短息
            type: 'GET',
            url: '/oauthLoginServlet',
            data: {
                flagStr: "suresms",
                platenumber: car_num.val(),
                mobilephone: b
            },
            success: function (jsonData) {
                var backdata = JSON.parse(jsonData);
            }
        });
        e.stopPropagation();
        return false;
    })

    //------------------------------------------------------------------------------------获取遮罩层中用户没有注册，去登录的点击
    sign_in.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
		// window.location.href = "/oauthLoginServlet?flagStr=register";
    });
    //--------------------------------------------------------------------------------------统一弹出层
    tyt.on("click",function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
    })
})
