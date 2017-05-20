<%@ page language="java"
         contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>滑动到底部加载下一页内容</title>
    <link href="/styles/pullToRefresh.css" rel="stylesheet" type="text/css"/>
    <script src="/scripts/login/js/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <%--<script src="/scripts/zepto.min.js" type="text/javascript" charset="utf-8"></script>--%>
    <script type="text/javascript" src="/scripts/iscroll.js"></script>
    <script type="text/javascript" src="/scripts/pullToRefresh.js"></script>
    <script type="text/javascript" src="/scripts/main.js"></script>
    <style>
        table {
            width: 100%;
            padding: 0 15px;
            background: #fff;
            border-collapse: collapse;
        }

        table td {
            padding: 6px 0;
            width: 33%;
            border-bottom: 1px solid #e1e1e1;
        }

        tr td:nth-child(2) {
            text-align: center;
        }

        tr td img {
            width: 24px;
            vertical-align: middle;
        }

        tr td:last-child {
            text-align: right;
        }

        td > div:first-child {
            /*margin-bottom: -6px;*/
        }

        td > div:last-child {
            color: #9C9C9C;
        }
    </style>
</head>


<body>

<input type="hidden" name="pageNo" id="pageNo" value="1"/>
<div id="wrapper">
    <table class="white">

    </table>
</div>
</body>
<script>
    $.ajax({
        type: 'POST',
        url: "/pagingquery",
        data: {
            businessType: "xialajiazai",
            page: "1",
            rows: "3"
        },
        async: false,
        success: function (json) {
            var data = JSON.parse(json);
            if (data == null) {

            } else {
                var content = "";
                if (data.rows.length == 0) { //如果数据的条数为空,显示空内容
                    return "";
                }else{
                    for (var i = 0; i < data.length; i++) {
                        content = content
                            + '<tr>'
                            + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                            + '</tr>';
                    }
                    $("#wrapper").append(content);
                }

            }
        },
        error: function () {
            alert("查询数据出错啦，请刷新再试");
        }
    });
    var pageNum = 1;
    refresher.init({
        id:"wrapper",
        pullDownAction:Refresh,
        pullUpAction:Load
    });
    function Refresh() {
        $.ajax({
            type: 'POST',
            url: "/pagingquery",
            data: {
                businessType: "xialajiazai",
                page: "1",
                rows: "3"
            },
            async: false,
            success: function (json) {
                document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";
                document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";
                var data = JSON.parse(json);
                if (data == null) {

                } else {
                    var content = "";
                        if (data.rows.length == 0) { //如果数据的条数为空,显示空内容
                            return "";
                        }else{
                            for (var i = 0; i < data.length; i++) {
                                content = content
                                    + '<tr>'
                                    + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                                    + '</tr>';
                            }
                            $("#wrapper").children().remove();
                            $("#wrapper").append(content);
                        }

                }
                setTimeout(function () {
                    wrapper.refresh();
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";
                },1000);

            },
            error: function () {
                alert("查询数据出错啦，请刷新再试");
            }
        });
    }
    function Load() {
        pageNum++;
        $.ajax({
            type: 'POST',
            url: "/pagingquery",
            data: {
                businessType: "xialajiazai",
                page: ""+pageNum+"",
                rows: "3"
            },
            async: false,
            success: function (json) {
                document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";
                document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='css/ok.png'/>刷新成功";
                var data = JSON.parse(json);
                if (data == null) {

                } else {
                    var content = "";
                        if (data.rows.length == 0) { //如果数据的条数为空,显示空内容
                            return "";
                        }else{

                        for (var i = 0; i < data.length; i++) {
                            content = content
                                + '<tr>'
                                + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                                + '</tr>';
                        }
                        $("#wrapper").append(content);
                    }
                }
                setTimeout(function () {
                    wrapper.refresh();
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";
                },1000);

            },
            error: function () {
                alert("到最低数据了");
            }
        });
//        setTimeout(function () {
//            var el, li, i;
//            el =document.querySelector("#wrapper ul");
//            for (i=0; i<2; i++) {
//                li = document.createElement('li');
//                li.innerHTML="<img src='img/game8.png'><div class='game-info'><h1>华仔超神战记</h1><p>9万次下载     89.18M</p><p>秒杀虚拟摇杆，砸烂手机键盘</p></div><button>下载</button>"
//                el.appendChild(li, el.childNodes[0]);
//            }
//            wrapper.refresh();
//        },2000);
    }









//    $(function () {
//        query('01');//第一次加载
//    });
//    function query(type) {
//        //TODO 查询数据
//        alert(type);
//        $.ajax({
//            type: 'POST',
//            url: "/pagingquery",
//            data: {
//                businessType: "xialajiazai",
//                page: $("#pageNo").val(),
//                rows: "3"
//            },
//            cache: false,
//            success: function (json) {
//                var data = JSON.parse(json);
//                loading = true;
//                console.log(data.rows);
//                alert(data.rows.length);
//                if (data == null) {
//
//                    $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
//                } else {
//                    var content = "";
//                    if (type == "00") {
//                        if (data.rows.length == 0) { //如果数据的条数为空,显示空内容
//                            $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
//                            return "";
//                        }
//                        for (var i = 0; i < data.length; i++) {
//                            content = content
//                                + '<tr>'
//                                + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
//                                + '</tr>';
//                        }
//                        $("#wrapper").append(content);
//                    } else {
//
//                        for (var i = 0; i < data.rows.length; i++) {
//
//                            content = content
//                                + '<tr>'
//                                + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
//                                + '</tr>';
//                        }
//                        $("#wrapper").html(content);
//                    }
//                }
//            },
//            error: function () {
//                loading = true;
//                $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
//                alert("查询数据出错啦，请刷新再试");
//            }
//        });
//    }
//    var loading = false;
//    Zepto(function ($) {
//        $(window).scroll(function () {
//            if (($(window).scrollTop() + $(window).height() > $(document).height() - 10) && loading) {
//                loading = false;
//                $("#pageNo").val(parseInt($("#pageNo").val()) + 1);
//                query("00");
//            }
//        });
//    })
//
//    var ua = navigator.userAgent.toLowerCase();
//    if (/android/.test(ua)) {
//        $('.date>div>img:last').css({"margin-left": "-25px"});
//    }
</script>
</html>