/**
 * Created by Administrator on 2017/3/23.
 */

$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
        // $(".center_zhis ").show();
        // $(".conter_main").show()
    }, 1000);
});
$(document).ready(function () {
    var top = $("#top").val();
    $.ajax({
        type    : 'POST',
        url     : '/shopAjax',
        data    : {
            businessType   : "xiaofeijilu",
            top : top
        },
        success:function(jsondata){
            var json = JSON.parse(jsonData);
            alert(jsonData);


        }
    });
})