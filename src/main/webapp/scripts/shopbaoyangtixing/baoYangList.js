/**
 * Created by Administrator on 2017/3/22.
 */
$(document).ready(function () {
    //时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }
    var top = $("#page1").val();
    console.log(top);
    $(function () {
        query('01');//第一次加载
    });
    function query(type) {
        //TODO 查询数据
        alert(type);
        $.ajax({
            type: 'POST',
            url: "/pagingquery",
            data: {
                businessType: "xialajiazai",
                page: $("#pageNo").val(),
                rows: "3"
            },
            cache: false,
            success: function (json) {
                var json = JSON.parse(jsondata);
                console.log(json);
                var html = "";
                if (json.rows.length > 0) {
                    if (json.rows.length > 5) {
                        $("#thelist").attr("style", "");
                    }
                    for (var ii = 0; ii < json.rows.length; ii++) {
                        var flaghtml = "";
                        if (json.rows[ii].customerDemandLevel == 1) {
                            flaghtml += "<span class='by_bgcolorfjj by_width'>非常紧急</span>";
                        } else if (json.rows[ii].customerDemandLevel == 2) {
                            flaghtml += "<span class='by_bgcolorjj by_width'>紧急</span>";
                        } else {
                            flaghtml += "<span class='by_bgcolorpt by_width'>普通</span>";
                        }
                        var data = dateFormat(json.rows[ii].customerDemandDateBegin)
                        html += "<li>" +
                            "<div class='by_center by_top'>" +
                            "<span class='by_date font_2'>" + data + "</span>" +
                            "<span class='by_name font_2'>&nbsp;&nbsp;" + json.rows[ii].updateUserName + "</span>" +
                            "</div>" +
                            "<div class='middle by_cenulcolor'>"+
                            "<div class='left font_1 blue'>保养类型：</div>"+
                            "<div class='right font_1'>"+
                            "<span class='txt'>"+json.rows[ii].demandTypeName+"</span>"+
                            "</div>"+
                            "</div>"+
                            "<div class='middle by_cenulcolor'>"+
                            "<div class='left font_1 blue'>详<i style='visibility: hidden'>保养</i>情：</div>"+
                            "<div class='right font_1'>"+
                            "<span class='txt'>"+json.rows[ii].customerDemandMemo+"</span>"+
                            "</div>"+
                            "</div>"+
                            "<div class='by_center font_1 by_cenulcolor' style='margin-top: 13px;margin-bottom: 43px;'> 状<i  style='visibility: hidden'>保养</i>态：" + flaghtml + " </div>" +
                            "</li>"
                    }

                } else {
                    html += "<li>" +
                        "<div style='width:100%;height:100%;margin-top:6%;' align='center'><font size='6'>无保养提醒信息！</font> </div>" +
                        "</li>"
                }
                $("#thelist").append(html)
                console.log(json);
            },
            error: function () {
                loading = true;
                $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
                alert("查询数据出错啦，请刷新再试");
            }
        });
    }
    var loading = false;
    Zepto(function ($) {
        $(window).scroll(function () {
            if (($(window).scrollTop() + $(window).height() > $(document).height() - 10) && loading) {
                loading = false;
                $("#pageNo").val(parseInt($("#pageNo").val()) + 1);
                query("00");
            }
        });
    })

    var ua = navigator.userAgent.toLowerCase();
    if (/android/.test(ua)) {
        $('.date>div>img:last').css({"margin-left": "-25px"});
    }
//TODO top暂时传值为1
//     $.ajax({
//         type: 'POST',
//         url: '/shopAjax',
//         data: {
//             businessType: "baoYangTiXingList",
//             top: top
//         },
//         success: function (jsondata) {
//             var json = JSON.parse(jsondata);
//             console.log(json);
//             var html = "";
//             if (json.length > 0) {
//                 if (json.length > 5) {
//                     $("#thelist").attr("style", "");
//                 }
//                 for (var ii = 0; ii < json.length; ii++) {
//                     var flaghtml = "";
//                     if (json[ii].customerDemandLevel == 1) {
//                         flaghtml += "<span class='by_bgcolorfjj by_width'>非常紧急</span>";
//                     } else if (json[ii].customerDemandLevel == 2) {
//                         flaghtml += "<span class='by_bgcolorjj by_width'>紧急</span>";
//                     } else {
//                         flaghtml += "<span class='by_bgcolorpt by_width'>普通</span>";
//                     }
//                     var data = dateFormat(json[ii].customerDemandDateBegin)
//                     html += "<li>" +
//                         "<div class='by_center by_top'>" +
//                             "<span class='by_date font_2'>" + data + "</span>" +
//                             "<span class='by_name font_2'>&nbsp;&nbsp;" + json[ii].updateUserName + "</span>" +
//                         "</div>" +
//                         "<div class='middle by_cenulcolor'>"+
//                             "<div class='left font_1 blue'>保养类型：</div>"+
//                             "<div class='right font_1'>"+
//                                 "<span class='txt'>"+json[ii].demandTypeName+"</span>"+
//                             "</div>"+
//                         "</div>"+
//                         "<div class='middle by_cenulcolor'>"+
//                             "<div class='left font_1 blue'>详<i style='visibility: hidden'>保养</i>情：</div>"+
//                             "<div class='right font_1'>"+
//                                 "<span class='txt'>"+json[ii].customerDemandMemo+"</span>"+
//                             "</div>"+
//                         "</div>"+
//                         "<div class='by_center font_1 by_cenulcolor' style='margin-top: 13px;margin-bottom: 43px;'> 状<i  style='visibility: hidden'>保养</i>态：" + flaghtml + " </div>" +
//                         "</li>"
//                 }
//
//             } else {
//                 html += "<li>" +
//                     "<div style='width:100%;height:100%;margin-top:6%;' align='center'><font size='6'>无保养提醒信息！</font> </div>" +
//                     "</li>"
//             }
//             $("#thelist").append(html)
//             console.log(json);
//         },
//         error: function () {
//         }
//     });
});