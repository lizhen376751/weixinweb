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
        *{
            margin: 0;
            padding:0;
            -webkit-tap-highlight-color:rgba(0,0,0,0);
            -webkit-text-size-adjust:none;
        }
        html{
            font-size:10px;
        }
        body{
            background-color: #f5f5f5;
            font-size: 1.2em;
        }
        table {
            width: 100%;
            padding: 0 15px;
            background: #fff;
            border-collapse: collapse;
        }

        table td {
            padding: 6px 0;
            width: 33%;
            height: 200px;
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
    var page = 1; //记录当前加载的页数
    var add_num = 0;//记录加载的次数
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
            console.log(data.rows);
            if (data != null && data.rows != null) {
                var content = "";
                for (var i = 0; i < data.rows.length; i++) {
                    content = content
                        + '<tr>'
                        + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                        + '</tr>';
                }
                $(".white").append(content);
            }
        },
        error: function () {
            alert("查询数据出错啦，请刷新再试");
        }
    });
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
                page = 1;
                var data = JSON.parse(json);
                console.log(data.rows);
                if (data != null && data.rows != null) {
                    document.getElementById("wrapper").querySelector(".pullDownIcon").style.display="none";
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="<img src='/files/ok.png'/>刷新成功";
                    var content = "";
                    for (var i = 0; i < data.rows.length; i++) {
                        content = content
                            + '<tr>'
                            + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                            + '</tr>';
                    }
                    $(".white").children().remove();
                    $(".white").append(content);
                    wrapper.refresh();
                    document.getElementById("wrapper").querySelector(".pullDownLabel").innerHTML="";
                }
            },
            error: function () {
                alert("查询数据出错啦，请刷新再试");
            }
        });
    }
    function Load() {
        page++;
        if(page <= 3){
            $.ajax({
                type: 'POST',
                url: "/pagingquery",
                data: {
                    businessType: "xialajiazai",
                    page: ""+page+"",
                    rows: "3"
                },
                async: false,
                success: function (json) {
                    var data = JSON.parse(json);
                    console.log(data.rows);
                    if (data != null && data.rows != null) {
                        var content = "";
                        for (var i = 0; i < data.rows.length; i++) {
                            content = content
                                + '<tr>'
                                + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                                + '</tr>';
                        }
                        $(".white").append(content);
                        page_num()
                    }
                },
                error: function () {
                    alert("查询数据出错啦，请刷新再试");
                }
            });
        }
        wrapper.refresh();
        $(".pullUpIcon").show();
        refresher.info.loadingLable = "加载中...";
        refresher.info.pullUpLable = "上拉加载更多"
        refresher.info.pullingUpLable = "释放加载更多"
    }
    function page_num() {
        if(page == 3){
            $(".pullUpIcon").hide();
            $(".pullUpLabel").text("已经到了最底部了！");
            refresher.info.loadingLable = "已经到了最底部了!";
            refresher.info.pullUpLable = "已经到了最底部了!"
            refresher.info.pullingUpLable = "已经到了最底部了!"
        }
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