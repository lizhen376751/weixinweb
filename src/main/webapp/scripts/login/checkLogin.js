/**
 * Created by Administrator on 2017/3/23.
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
                fromflag   : "getXmkCardInfo",
                shopcode   : shopcode,
                openId     : strOpenId
            },
            success : function(jsonData){
                var json = JSON.parse(jsonData);
                if (json == null || "null".equals(json) || "".equals(json)){
                    //TODO 页面跳转至登录页面
                }
            }
        });
    }

}