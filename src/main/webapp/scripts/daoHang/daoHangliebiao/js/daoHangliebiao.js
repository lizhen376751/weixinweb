/**
 * Created by Administrator on 2017/3/22.
 */
$(document).ready(function () {
    var shopcode = encodeURIComponent($("#shopcode").val());
    var shopType_search = $("#shopType_search").val();
    var orderType_search = $("#orderType_search").val();

    // GetShopInfo GetShopInfo = new GetShopInfo();
    // ArrayList<HashMap<String,String>> shopList = GetShopInfo.queryShopCodeListByLmCode(shopcode,shopType_search,orderType_search);

    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "queryShopCodeListByLmCode",
            shopcode   : shopcode,
            shopType_search : shopType_search,
            orderType_search : orderType_search,
        },
        success : function(jsonData){
            var json = JSON.parse(jsonData);
            h2.text(json.title);
            txt.append(json.details)
        }
    });
//	appenging()
})
