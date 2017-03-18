
/**
 * Created by Administrator on 2017/3/16.
 */

$(document).ready(function () {
    $.ajax({
        type    : 'POST',
        url     : '/queryLmkInfoList',
        // data    : {
        //     fromflag   : "queryLmkInfoList",
        //     shopcode   : shopcode,
        //     CarId      : CarId
        // },
        success : function(jsonData){
            var json = JSON.parse(jsonData);
            h1.text(json.age);
            h2.text(json.name);
        }
    });

})
