/**
 * Created by Administrator on 2017/3/23.
 */
$(document).ready(function () {
    var top = $("#top").val();
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "getServiceListByLmcodeAndCarNo",
            top : top
        },
        success:function(jsondata){
            var json = JSON.parse(jsonData);


        }
    });
}