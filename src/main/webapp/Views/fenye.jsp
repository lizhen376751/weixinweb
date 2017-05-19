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
    <link href="/styles/shopbaoyangtixing/dropload.css" rel="stylesheet" type="text/css"/>
    <script src="/scripts/login/js/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/dropload.js" type="text/javascript" charset="utf-8"></script>
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
<div class="white">
    <table id="wrapper">

    </table>
</div>
</body>
<script>
    var counter = 0;
    // 每页展示4个
    var num = 4;
    var pageStart = 0,pageEnd = 0;

    // dropload
    $('.content').dropload({
        scrollArea : window,
        domUp : {
            domClass   : 'dropload-up',
            domRefresh : '<div class="dropload-refresh">↓下拉刷新-自定义内容</div>',
            domUpdate  : '<div class="dropload-update">↑释放更新-自定义内容</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中-自定义内容...</div>'
        },
        domDown : {
            domClass   : 'dropload-down',
            domRefresh : '<div class="dropload-refresh">↑上拉加载更多-自定义内容</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中-自定义内容...</div>',
            domNoData  : '<div class="dropload-noData">暂无数据-自定义内容</div>'
        },
        loadUpFn : function(me){
            $.ajax({
                type: 'POST',
                url: "/pagingquery",
                data: {
                    businessType: "xialajiazai",
                    page: ""+counter+"",
                    rows: ""+num+""
                },
                cache: false,
                success: function(data){
                    var data = JSON.parse(json);
                    loading = true;
                    console.log(data.rows);
                    alert(data.rows.length);
                    if (data == null) {

                        $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
                    } else {
                        var content = "";
                        if (type == "00") {
                            if (data.rows.length == 0) { //如果数据的条数为空,显示空内容
                                $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
                                return "";
                            }
                            for (var i = 0; i < data.length; i++) {
                                content = content
                                    + '<tr>'
                                    + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                                    + '</tr>';
                            }
                            $("#wrapper").append(content);
                        } else {

                            for (var i = 0; i < data.rows.length; i++) {

                                content = content
                                    + '<tr>'
                                    + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                                    + '</tr>';
                            }

                        }
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $("#wrapper").html(content);
                        // 每次数据加载完，必须重置
                        me.resetload();
                        // 重置索引值，重新拼接more.json数据
                        counter = 0;
                        // 解锁
                        me.unlock();
                        me.noData(false);
                    },1000);
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        },
        loadDownFn : function(me){
            counter++;
            pageEnd = num * counter;
            pageStart = pageEnd - num;
            $.ajax({
                type: 'POST',
                url: "/pagingquery",
                data: {
                    businessType: "xialajiazai",
                    page: ""+counter+"",
                    rows: ""+num+""
                },
                cache: false,
                success: function(data){
                    var data = JSON.parse(json);
                    loading = true;
                    console.log(data.rows);
                    alert(data.rows.length);
                    if (data == null) {

                        $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
                    } else {
                        var content = "";
                        if (type == "00") {
                            if (data.rows.length == 0) { //如果数据的条数为空,显示空内容
                                $("#pageNo").val(parseInt($("#pageNo").val()) - 1);
                                return "";
                            }
                            for (var i = 0; i < data.length; i++) {
                                content = content
                                    + '<tr>'
                                    + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                                    + '</tr>';
                            }
                            $("#wrapper").append(content);
                        } else {

                            for (var i = 0; i < data.rows.length; i++) {

                                content = content
                                    + '<tr>'
                                    + '<td><div>' + data.rows[i].carHaoPai + '</div><div>' +data.rows[i].carHaoPai + '</div></td>'
                                    + '</tr>';
                            }

                        }
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $("#wrapper").html(content);
                        // 每次数据加载完，必须重置
                        me.resetload();
                        // 重置索引值，重新拼接more.json数据
                        counter = 0;
                        // 解锁
                        me.unlock();
                        me.noData(false);
                    },1000);
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        },
        threshold : 50
    });







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