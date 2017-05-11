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

$(function () {

    var shopCode = $("#shopCode").val();
    var wxpingzheng = $("#wxpingzheng").val();
    var xunumber = $("#xunumber").val();
    var car_num = $(".car_num")//------------------------------------------------获取车牌号里的内容
    var project_name = $(".project_name");//-----------------------------------获取项目的名称
    var ul = $("ul");//-----------------------------------------------------获取步骤容器
    var btn = $(".btn");//---------------------------------------------------获取标准流程按钮
    var images = $(".images"); //--------------------------------------获取页面中的每一步的图片

    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "shigongbuzhou",
            shopCode: shopCode,
            wxpingzheng: wxpingzheng,
            xunumber: xunumber
        },
        success: function (jsonData) {
            var arr = JSON.parse(jsonData);
            console.log(arr);
              var title = '';//单个项目中的title
              for (var i = 0; i < arr.length; i++) {        //循环的项目个数
                 var arr2 = arr[i];
                  //循环每个项目并写出相应的车牌号码和项目名称
                      title+="<div class='title font_1'>" +
                          "<div class='title_left'>"+
                          "<span class='step_color1'>车牌号：</span>"+
                          "<span class='step_color2 car_num'></span>"+
                          "</div>"+
                          "<div class='title_right'>"+
                          "<span class='step_color2 project_name'>"+arr2[0].projectName+"</span>"+
                          "<span class='step_color1'>项目：</span>"+
                          "</div>"+
                          "</div>"+
                          "<ul >"

                  //var html = '';     //循环每个项目的内容
                for(var j = 0; j < arr2.length; j++){
                    title+= "<li>" +
                        "<div class='photo'>" +
                        "<img src=' "+arr2[j].images[0].fileUrl+" ' class='images'/>" +
                        "</div>" +
                        "<span class='step_num font_4 color_3'>"+arr2[j].stepNumber+"</span>" +
                        "<p class='step_name font_3'>"+arr2[j].projectStepName+"</p>" +
                        "</li>";

                }
                  title+="</ul>"+
                          "<div id='btn_idv' class='btn  font_3 color_3'>标准流程</div>"


            }
            $(".wrap").append(title);
            $("#btn_idv").on("click", function () {
                //TODO 获取参数进行替换
                window.location.href = "/shopweixinServlet?serviceType=biaozhunliucheng&itemCode=CSXM0001000600010002&shopCodeLm=CS000";
            })
        }


    });

    images.on("click", function () {
        var src = $(this).attr("src");
        $(".box").show();
        $(".img").attr("src", src);
        $(".box").bind("touchmove", function (e) {
            e.preventDefault(); //遮罩层出现后禁止body滑动
        });
    })
    $(".box").on("click", function () {
        $(this).hide()
    });
    $(".img").on("click", function () {
        $(".box").hide()
    });
    //-------------------------------------------------动态添加每一条步骤

    //--------------------------------------------------点击标准流程跳转的页面

})
$(function(){

})










