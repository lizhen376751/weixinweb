/**
 * Created by Administrator on 2017/3/23.
 */
/**
 * 登录判断
 * 如果没有车号牌,先根据shopcode和strOpenId进行查询
 * 查询结果有的直接免登录,并将车牌号存入到session中.给给于input的CarId赋值
 * 查询结果没有的话跳转至登录页面
 */
$(document).ready(function () {
    var CarId = $("#CarId").val();
    var shopcode = $("#shopcode").val();
    var strOpenId = $("#strOpenId").val();
    if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
        $.ajax({
            type    : 'POST',
            url     : '/getCommonAjax',
            data    : {
                fromflag   : "checkLogin",
                shopcode   : shopcode,
                openId     : strOpenId
            },
            success : function(jsonData){
                var json = JSON.parse(jsonData);
                if (json == null || "null".equals(json) || "".equals(json)){
                    //页面跳转至登录页面
                    response.sendRedirect("login?shopcode=" + shopcode + "&strOpenId=" + strOpenId + "");
                }else{
                    // 将session的CarId赋值为json,并给于input的carID的值
                    session.setAttribute("DUDUCHEWANG_CarId", json);
                    $("#CarId").val("json");
                }
            }
        });
    }

})