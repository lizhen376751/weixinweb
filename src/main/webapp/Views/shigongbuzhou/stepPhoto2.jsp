
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>实际流程</title>
    <!--引入css-->
    <link rel="stylesheet" type="text/css" href="/styles/shigongbuzhou/css/wxlm.css"/>
    <!--<link rel="stylesheet" type="text/css" href="../css/lanrenzhijia.css"/>-->
    <link rel="stylesheet" type="text/css" href="/styles/shigongbuzhou/css/stepPhoto.css"/>
    <!--引入JS-->
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/main.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/shigongbuzhou/js/stepPhoto2.js" type="text/javascript" charset="utf-8"></script>

    <!-- oss -->
    <script src="https://www.promisejs.org/polyfills/promise-6.1.0.js"></script>
    <script type="text/javascript" src="/scripts/shigongbuzhou/oss/aliyun-oss-sdk.js"></script>
    <script type="text/javascript" src="/scripts/shigongbuzhou/oss/base64.js"></script>
    <script type="text/javascript" src="/scripts/shigongbuzhou/oss/app.js"></script>

</head>
<body>
<div id="loading">
    <img src="/files/loading2.gif"  alt="loading.." />
</div>
    <input type="hidden" name="cardNumber" id="cardNumber" value=${cardNumber} >
    <input type="hidden" name="cardId" id="cardId" value=${cardId} >
    <%--<input type="hidden" name="wxpingzheng"   id="wxpingzheng" value=${wxpingzheng} />--%>
    <%--<input type="hidden" name="xunumber"  id="xunumber" value=${xunumber} />--%>
<div class="wrap">
    <!--------------------------------title:车牌号和项目-------->
    <!--   <div class="title font_1">
           <div class="title_left">
               <span class="step_color1">车牌号：</span>
               <span class="step_color2 car_num">鲁B00006</span>
           </div>
           <div class="title_right">
               <span class="step_color2 project_name">怡人怡车换油</span>
               <span class="step_color1">项目：</span>
           </div>
       </div>  <!--title结束-->
    <!------------------------------------步骤展示-------->
<!--  <ul>
            <!-----------------每一条步骤-->
    <!--  <li>
               <div class="photo">
                   <img src="/files/shopshigongbuzhou/stepPhoto/300414.jpg" class="images"/>
               </div>
               <span class="step_num font_4 color_3">6</span>
               <p class="step_name font_3">电器元件检查</p>
           </li>
           <li>
               <div class="photo">
                   <img src="/files/shopshigongbuzhou/stepPhoto/123.jpg" class="images"/>
               </div>
               <span class="step_num font_4 color_3">6</span>
               <p class="step_name font_3">电器元件检查</p>
           </li>
       </ul>-->
</div>
<!--图片点击放大效果-->
<!--<div class="box">
    <div class="img_bg">
        <img src="" class="img"/>
    </div>
</div>-->
<!-----------------------------------------------底部标准流程按钮------>
<%--<div class="btn font_3 color_3">标准流程</div>--%>
</body>
</html>

