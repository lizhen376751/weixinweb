<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=GBK" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>车辆健康指数</title>
    <link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/ahi/ahi.css"/>
    <script type="text/javascript" src="/scripts/ahi/jquery-1.12.1.min.js"></script>
    <script src="/scripts/ahi/index.js" type="text/javascript" charset="utf-8"></script>
    <script  type="text/javascript"  href="/scripts/main.js"></script>
</head>

<script>
    var ids = <%=request.getParameter("id")%>;
    $(function () {
        var jsonp = eval('('+decodeURI(decodeURI('<%=request.getParameter("inspectionDetailedDescription")%>'))+')');

        $("#radiusblue").html("<span class='radiusjb' ></span>&nbsp;&nbsp;" + jsonp.name + "&nbsp;&nbsp;<span class='radiusjb' ></span>"); //名字
        if(jsonp.processPicture){
            $("#photoimg").append("<img class='xq_img' onclick='fangda(this.src)' src='" + jsonp.processPicture + "'/>");//添加图片
        }else{
            $("#photoimg").append("无");//添加图片
        }


        var resultlength = jsonp.inspectionDetailedDescription;
        for (var i = 0; i < resultlength.length; i++) {
            $("#subPoint").append(
                "<li class='Thirly_li mstop'>" +
                "<span class='subxitong_1 font_1'><img  class='Thirly_liimg' src='/files/ahi/4.png'/></span>" +
                "<span class='submiaos_1 font_1'>" + resultlength[i].description + "</span>" +
                "</li>" +
                "<li class='Thirly_li'>" +
                "<span class='subxitong_1 font_1'><img  class='Thirly_liimg' src='/files/ahi/5.png'/></span>" +
                "<span class='submiaos_1 font_1'>" + resultlength[i].influence + "</span>" +
                "</li>" +
                "<li class='Thirly_li'>" +
                "<span class='subxitong_1 font_1'><img  class='Thirly_liimg' src='/files/ahi/6.png'/></span>" +
                "<span class='submiaos_1 font_1'>" + resultlength[i].suggest + "</span>" +
                "</li>" +
                "<hr class='sub_solid' />");
        }
        ;


    });
    function  goback(){
        window.location.href = "/oauthLoginServlet?flagStr=AHIInfoxiangqing&id="+ids;

    }
</script>

<body>
<%--网页加载效果--%>
  <div id="loading">
        <img src="/files/loading.gif"  alt="loading.." />
  </div>
  <div class="contermain" >
        <p class="goback" onclick="goback()"><img src="/files/fanhui.png"></p>
        <div class="zhezhao">
            <img class="zsimg" src=""/>
        </div>

        <header>
            <div class="radiusblue" id="radiusblue">
            </div>
        </header>

        <ul id="subPoint">
        </ul>

        <div class="Thirly_li" style="margin-top:50px;">
            <span class="subxitong_1 font_1"><img class="Thirly_liimg" src="/files/ahi/7.png"/></span>
            <span class="submiaos_1 font_1" id="photoimg">
            </span>
        </div>
   </div>
</body>
</html>
