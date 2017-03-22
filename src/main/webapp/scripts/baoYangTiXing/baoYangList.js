/**
 * Created by Administrator on 2017/3/22.
 */
$(document).ready(function(){
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "baoYangList"
        },
        success:function(jsondata){
            alert(jsondata);
            var json = JSON.parse(jsondata);
            alert(json);
            console.log(json);
        },
        error:function(){
        }
    });
});