<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta charset="UTF-8">
    <title>消费评价查看</title>
    <!--引入css-->
    <link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/shopconsumerEvaluation/consumerEvaluation.css"/>
    <!--引入JS-->
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <!-- oss -->
    <script src="https://www.promisejs.org/polyfills/promise-6.1.0.js"></script>
    <script type="text/javascript" src="/scripts/shopconsumerEvaluation/oss/aliyun-oss-sdk.js"></script>
    <script type="text/javascript" src="/scripts/shopconsumerEvaluation/oss/base64.js"></script>
    <script type="text/javascript" src="/scripts/shopconsumerEvaluation/oss/app.js"></script>
    <script src="/scripts/main.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/shopconsumerEvaluation/consumerEvaluation2.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<input type="hidden" name="plateNumber" value="${plateNumber}" id="plateNumber">
<input type="hidden" name="customId" value="${customId}" id="customId">
<input type="hidden" name="wxpingzheng" value="${wxpingzheng}" id="wxpingzheng">
<input type="hidden" name="shopCode" value="${shopCode}" id="shopCode">
<div class="project">
    <%--<!--每一个项目的评价-->--%>
    <%--<div class="box">--%>
    <%--<div class="box_l">--%>
    <%--<!--项目名称-->--%>
    <%--<div class="project_title font_3">--%>
    <%--<span class="project_title_txt">怡人怡车洗车</span>--%>
    <%--</div>--%>
    <%--<!--项目评价等级-->--%>
    <%--<div class="project_num">--%>
    <%--<span class="font_1 color_8">请给出您的评价</span>--%>
    <%--<ul>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="xc"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="xc"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="xc"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="xc"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="xc"/></li>--%>
    <%--</ul>--%>
    <%--<input type="hidden" class="xc_val" value="0" />--%>
    <%--</div>--%>
    <%--<!--项目评价文字-->--%>
    <%--<div class="project_text">--%>
    <%--<textarea class="font_4" rows="4" maxlength="110" placeholder="评论：本店的服务满足您的期待吗？请评价一下我们的优点和不足的地方吧!（满足15个字才是 好同志哦！）"></textarea>--%>
    <%--<ul>--%>
    <%--<li class="margin_r">--%>
    <%--<input type="file" class="project_file"/>--%>
    <%--<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>--%>
    <%--<img src="" class="file_img"/>--%>
    <%--<input type="hidden" class="uuid" value="" />--%>
    <%--</li>--%>
    <%--<li class="margin_r">--%>
    <%--<input type="file" class="project_file"/>--%>
    <%--<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>--%>
    <%--<img src="" class="file_img"/>--%>
    <%--<input type="hidden" class="uuid" value="" />--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<input type="file" class="project_file"/>--%>
    <%--<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>--%>
    <%--<img src="" class="file_img"/>--%>
    <%--<input type="hidden" class="uuid" value="" />--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%----%>
    <%----%>
    <%--<!--每一个项目的评价-->--%>
    <%--<div class="box">--%>
    <%--<div class="box_l">--%>
    <%--<!--项目名称-->--%>
    <%--<div class="project_title font_3">--%>
    <%--<span class="project_title_txt">怡人怡车打蜡</span>--%>
    <%--</div>--%>
    <%--<!--项目评价等级-->--%>
    <%--<div class="project_num">--%>
    <%--<span class="font_1 color_8">请给出您的评价</span>--%>
    <%--<ul>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dl"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dl"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dl"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dl"/></li>--%>
    <%--<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dl"/></li>--%>
    <%--</ul>--%>
    <%--<input type="hidden" class="dl_val" value="0" />--%>
    <%--</div>--%>
    <%--<!--项目评价文字-->--%>
    <%--<div class="project_text">--%>
    <%--<textarea class="font_4" rows="4" maxlength="110" placeholder="评论：本店的服务满足您的期待吗？请评价一下我们的优点和不足的地方吧!（满足15个字才是 好同志哦！）"></textarea>--%>
    <%--<ul>--%>
    <%--<li class="margin_r">--%>
    <%--<input type="file" class="project_file" accept="image/*"/>--%>
    <%--<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>--%>
    <%--<img src="" class="file_img"/>--%>
    <%--<input type="hidden" class="uuid" value="" />--%>
    <%--</li>--%>
    <%--<li class="margin_r">--%>
    <%--<input type="file" class="project_file"/>--%>
    <%--<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>--%>
    <%--<img src="" class="file_img"/>--%>
    <%--<input type="hidden" class="uuid" value="" />--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<input type="file" class="project_file"/>--%>
    <%--<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>--%>
    <%--<img src="" class="file_img"/>--%>
    <%--<input type="hidden" class="uuid" value="" />--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
</div>

<!--店铺服务评分-->
<div class="shop_num">
    <span class="font_1 color_8">店铺服务评分</span>
    <ul>
        <li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dp"/></li>
        <li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dp"/></li>
        <li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dp"/></li>
        <li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dp"/></li>
        <li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="dp"/></li>
    </ul>
    <input type="hidden" class="dp_val" value="0" />
</div>
<!--提交按钮-->
<div class="btn font_3">
    提交评价
</div>
</body>

</html>
