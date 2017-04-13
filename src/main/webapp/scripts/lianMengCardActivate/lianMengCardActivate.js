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
    var card_num = $(".car_num");  //-------------------------------------------------------------获取用户输入的车牌号
    var activate_num = $(".password_num");   //---------------------------------------------------获取用户输入的账号密码
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
    //------------------------------------------------------------------------JS控制的css样式
    card_num.on("focus", function () {
        $(this).css({
            "border": "3px #6cd2fd solid",
            "boxShadow": "none",
            "lineHeight": "1.05rem",
        })
    })
    card_num.on("blur", function () {
        $(this).css({
            "border": "1px rgba(255,255,255,0.35) solid",
            "boxShadow": "0px 0px 3px rgba(0,0,0,0.35) inset",
            "lineHeight": "1.1rem",
        })
    })

    activate_num.on("focus", function () {
        $(this).css({
            "border": "3px #6cd2fd solid",
            "boxShadow": "none",
            "lineHeight": "1.05rem",
        })
    })
    activate_num.on("blur", function () {
        $(this).css({
            "border": "1px rgba(255,255,255,0.35) solid",
            "boxShadow": "0px 0px 3px rgba(0,0,0,0.35) inset",
            "lineHeight": "1.1rem",
        })
    })


    //----------------------------------------------------------------------------激活按钮点击判断
    activate.on("click", function () {
        var card_value = card_num.val();
        var activate_value = activate_num.val();
        if (card_value == "" || card_value.length > 20) {
            tc_ceng.show();
            ty.show();
            tyt.show();
            llt.text("联盟卡号有误，请重新输入~");
            // alert("联盟卡号有误，请重新输入~")
        } else if (activate_value == "" || activate_value.length > 10) {
            tc_ceng.show();
            ty.show();
            tyt.show();
            llt.text("输入激活码有误，请重新输入~");
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
                    if (backdata=="0"){
                        tc_ceng.show();
                        l_box.show();
                        // setTimeout(function () {
                        //     tc_ceng.hide();
                        //     l_box.hide();
                        //     b_box.hide();
                        //     card_num.val("");
                        //     activate_num.val("")
                        // }, 3000);
                    }else if (backdata=="1"){
                        tc_ceng.show();
                        ty.show();
                        tyt.show();
                        llt.text("该卡号已经激活!");
                        // alert("该卡号已经激活!");
                    }else if(backdata=="2"){
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
        b_box.hide()
    })
    //------------------------------------------------------------------------------------联盟卡激活失败后的确定按钮
    determine.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        card_num.val("");
        activate_num.val("")
    })
    //------------------------------------------------------------------------------------联盟卡激活成功后的确定按钮
    sign_in.on("click", function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        //----------------------------------------------------------------------------------激活成功后跳转的页面
        window.location.href = "/oauthLoginServlet?flagStr=personalCenter";
    })
    tyt.on("click",function () {
        tc_ceng.hide();
        l_box.hide();
        b_box.hide();
        ty.hide();
    })

})




