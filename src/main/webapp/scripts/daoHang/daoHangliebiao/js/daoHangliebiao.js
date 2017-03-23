/**
 * Created by Administrator on 2017/3/22.
 */
$(document).ready(function () {
    var shopcode = encodeURIComponent($("#shopcode").val());
    var contextPathStr = $("#contextPathStr").val();

    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "getLianMengActivity",
            ids   : ids
        },
        success : function(jsonData){
            var json = JSON.parse(jsonData);
            h2.text(json.title);
            txt.append(json.details)
        }
    });
//	appenging()
})
