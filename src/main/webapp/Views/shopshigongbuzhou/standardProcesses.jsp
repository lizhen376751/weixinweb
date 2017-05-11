<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=4,user-scalable=no"/>
    <title>标准流程</title>
    <!--引入css-->
    <link rel="stylesheet" type="text/css" href="/styles/wxlm.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/shopshigongbuzhou/css/standardProcesses.css"/>
    <!--引入JS-->
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/shopshigongbuzhou/js/standardProcesses.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<input type="hidden" name="itemCode" id="itemCode" value=${itemCode} >
<input type="hidden" name="shopCodeLm"   id="shopCodeLm" value=${shopCodeLm} />
<!------------------------------------------------title部分-->
<div class="title font_1">
    <span class="step_color1">项目：</span>
    <span class="step_color2">怡人怡车换油</span>
</div>
<!------------------------------------------------------标准步骤-->
<ul>
    <li>
        <!--设置背景-->
        <div class="step_left">
            <div class="img">
                <img src="/files/shopshigongbuzhou/stepPhoto/300414.jpg" class="image"/>
            </div>
            <span class="step_num font_4 color_3">1</span>
        </div>
        <div class="step_text">
            <p class="step_title font_1 step_color2">启动发动机</p>
            <p class="step_detail font_4">确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间</p>
            <p class="font_4" id="step_note">注：其他车门玻璃也需要检查也玻璃也需要检查也玻璃也需要检查也玻璃也需要检查也</p>
        </div>
    </li>
    <li>
        <!--设置背景-->
        <div class="step_left">
            <div class="img">
                <img src="/files/shopshigongbuzhou/stepPhoto/300414.jpg" class="image"/>
            </div>
            <span class="step_num font_4 color_3">2</span>
        </div>
        <div class="step_text">
            <p class="step_title font_1 step_color2">启动发动机</p>
            <p class="step_detail font_4">确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间</p>
            <p class="font_4" id="step_note">注：其他车门玻璃也需要检查</p>
        </div>
    </li>

</ul>
</body>
</html>

