<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>AHI</title>
    <link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/ahi/ahi.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/ahi/swiper.min.css"/>
    <script type="text/javascript" src="/scripts/ahi/jquery-1.12.1.min.js"></script>
    <script src="/scripts/ahi/swiper.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/ahi/index.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">

        var ids = <%=request.getParameter("id")%>;
        $(document).ready(function () {
            $.ajax({
                type: "post",
                dataType: "text",
                url: 'getCommonAjax',
                data: {'fromflag': 'queryCarPointOne'},
                success: function (data) {
                    data = eval('(' + data + ')');
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            $.ajax({
                                type: "post",
                                async: false,
                                dataType: "text",
                                url: 'getCommonAjax',
                                data: {
                                    'fromflag': 'queryCarPointTwo',
                                    'id': data[i].id,
                                    'ratio': '100'
                                },
                                success: function (sdata) {

                                    sdata = eval('(' + sdata + ')');
                                    if (sdata.length > 0) {
                                        data[i]["result"] = sdata;
                                    }
                                }
                            });
                        }
                        showData(data);
                    } else {
                        alert("未查询到车辆 " + plateNumber + " 得分数据！");
                    }
                }
            });
        });

        function showData(jsonp) {
            if (jsonp.length > 0) {
                for (var i = 0; i < jsonp.length; i++) {

                    //检测系统
                    var subhtml = "";
                    var translate = jsonp[i].result;

                    for (var aa = 0; aa < translate.length; aa++) {
                        var sumq = 0;  //记录隐患数
                        var html_2 = "";//添加具体检测项；
                        var subDescribeList = translate[aa].resultQuestionDescribeList;
                        for (bb = 0; bb < subDescribeList.length; bb++) {
                            if (subDescribeList[bb].point < 34 && subDescribeList[bb].point > 0) {
                                sumq++;
                            }

                            //var resulthtml="";  //判断为检测，良好,较差；

                            var inspectionDetailedDescription = subDescribeList[bb];//取得该项描述信息

                            if (subDescribeList[bb].point == 0) {     //根据检测项的判断
                                html_2 += "<div class='lianghao'>" +
                                    "<span class='subxitong title_color font_1'>" + subDescribeList[bb].name + "</span>" +
                                    "<span class='radius radiuscoloe_1'></span>" +
                                    "<span class='subright title_color font_1'>未检测</span>" +
                                    "</div>	" +
                                    "<hr class='sub_solid' />";  //
                                <!--虚线-->
                            } else if (subDescribeList[bb].point < 34) {
                           // <a href='thirlyIndex.jsp?inspectionDetailedDescription=" + encodeURI(encodeURI(JSON.stringify(inspectionDetailedDescription))) + "'>" +

                                html_2 += "<a  href='/oauthLoginServlet?flagStr=thirlyIndex&inspectionDetailedDescription=" +  encodeURI(encodeURI(JSON.stringify(inspectionDetailedDescription)))+ "'>" +
                                    "<div class='lianghao a_hover'>" +
                                    "<span class='subxitong title_color font_1'>" + subDescribeList[bb].name + "</span>" +
                                    "<span class='radius radiuscoloe_2'></span>" +
                                    "<span class='subright title_color font_1 '><div class='bgjc'>较差</div></a></span>" +
                                    "</div>" +
                                    "</a>" +
                                    "<hr class='sub_solid' />";  //

                                <!--虚线-->
                            } else {
                                html_2 += "<div class='lianghao'>" +
                                    "<span class='subxitong title_color font_1'>" + subDescribeList[bb].name + "</span>" +
                                    "<span class='radius radiuscoloe_3'></span>" +
                                    "<span class='subright title_color font_1'>良好</span>" +
                                    "</div>	" +
                                    "<hr class='sub_solid' />";  //
                                <!--虚线-->

                            }
                            ;


                            /*  html_2+="<div class='lianghao'>"+
                             "<span class='subxitong title_color font_1'>"+subDescribeList[bb].name+"</span>"+
                             resulthtml+
                             "</div>	"+
                             "<hr class='sub_solid' />"; */  //
                            <!--虚线-->
                        }
                        ;

                        //添加隐患；
//                        var html_1 = "";
//                        if (sumq == 0) {
//                            html_1 += "<span class='liangh font_0'>状态良好</span>";
//                        } else {
//                            html_1 += "<span class='jingbao font_0'>" + sumq + "条隐患</span>";
//
//                        }
                        var html_1 = "";
                        if (translate[aa].point == 0) {
                            html_1 += "<span class='liangh font_0'>未检测</span>";
                        } else  if (translate[aa].point < 34 && translate[aa].point >0 ){
                            html_1 += "<span class='jingbao font_0'>" + sumq + "条隐患</span>";

                        }else{
                            html_1 += "<span class='liangh font_0'>状态良好</span>";
                        }

                        subhtml += "<li class='xqburder_li numlie_li' onClick='Togglezs(this);'>" +
                            "<span class='xitong numxitong font_1'>" +
                            "<img class='subicon_img' src='" + (translate[aa].logoPath ? translate[aa].logoPath + "?x-oss-process=image/resize,m_fixed,h_76,w_76" : "/files/ahi/icon/icon_4.png") + "'/>" +
                            "<span class='subicon_font'>" + translate[aa].name + "</span>" +
                            "</span>" +
                            html_1 +   //隐患or状态良好
                            "<span class='submiaos font_1'  >展开<img class='zk_img' src='/files/ahi/zk.png' /> </span>" +
                            "</li>" +
                            "<li class='subnumlie_li' id='jsubnumlie_li" + translate[aa].id + "' style='display:none'>" +
                            html_2 +
                            "</li>"    //添加二级展开页面
                    }


                    $(".swiper-wrapper").append("<div class='swiper-slide'>"
                        + "<div class='center_1'>"
                        + "<div class='num'>"
                        + "<p class='banner_img'><img src='" + (jsonp[i].logoPath ? jsonp[i].logoPath + "?x-oss-process=image/resize,m_fixed,h_264,w_230" : "/files/ahi/icon/icon_4.png") + "'/></p>"
                        + "<p class='num_font'>" + jsonp[i].point + "</p>"
                        + "</div>"
                        + "</div>"
                        + "<div style='width:100%'  class='bor_div'>"
                        + "<ul class='lie_li'>"
                        + subhtml
                        + "</ul>"
                        + "</div>"
                        + "</div>");

                }

            }


            //若是有隐患  默认为展开

            $(".jingbao").each(function () {
                var t = $(this).val();
                var z = /^[0-9]*$/;   //判断是否包含数字
                if (t.indexOf(z)) {
                    $(this).parent().next().show();   //若是有隐患  默认为展开
                    $(this).parent().find(".submiaos").html("收起<img class='zk_img' src='/files/ahi/ss.png' />");
                }
                ;

            });

            //swiper  插件
            var swiper = new Swiper('.swiper-container', {
                pagination: '.swiper-pagination',
                nextButton: '.swiper-button-next',
                prevButton: '.swiper-button-prev',
                slidesPerView: 1,
                paginationClickable: true,
                spaceBetween: 30,
                loop: true
            });

            //设置swiper默认显示第几页函数
            if (jsonp.length > 0) {
                for (var ii = 0; ii < jsonp.length; ii++) {
                    if (ids == jsonp[ii].id) {
                        //$('.swiper-pagination span').eq(cc).trigger('click');
                        swiper.slideTo(ii + 1, 0, false);   //swiper  默认显示第几页
                        //第一个参数是指定切换到某一页的索引；
                        //第二个参数是切换速度；
                        //第三个参数是布尔值，表示是否要触发onSlideChange回调函数；


                    }
                }
            } //设置swiper默认显示第几页函数结束

        }

    </script>

<body>

<!--<p class="goback" onclick="javascript:history.back(-1);"><img src="../img/fanhui.png"></p>-->
<!-- Swiper -->
<div class="swiper-container">
    <div class="swiper-button-next"></div>  <!--下一页按钮-->
    <div class="swiper-button-prev"></div>  <!--上一页按钮-->
    <div class="swiper-wrapper">
    </div>

</div>
</body>
</html>

