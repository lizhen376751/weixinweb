$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
    }, 1000);
});




$(function(){
    var openid=$("#openid").val();
    var shopCode=$("#shopCode").val();
    var userNum=$("#userNum").val();
    var forwardNum=$("#forwardNum").val();
    var wyid=$("#wyid").val();
    var identifying=$("#identifying").val();


    //屏蔽掉微信右上方的分享功能
    // lmwxsq();
    // wx.hideMenuItems({
    //     menuList: [
    //         'menuItem:share:appMessage',
    //         'menuItem:share:timeline',
    //         'menuItem:share:qq',
    //         'menuItem:share:weiboApp',
    //         'menuItem:share:facebook',
    //         'menuItem:share:QZone',
    //         'menuItem:favorite',
    //         'menuItem:originPage',
    //         'menuItem:copyUrl',
    //         'menuItem:openWithQQBrowser',
    //         'menuItem:openWithSafari'
    //
    //     ] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
    // });
    //屏蔽掉微信右上方的分享功能结束

    // 点击可用事件
    $(".use_div").click(function(){
        $.ajax({
            type: 'POST',
            url: '/getCommonAjax?fromflag=selfbilling&cardNo=' + json[i].cardId,
            async: false,
            data: {
                openid:openid,
                openid:shopCode,
                openid:userNum,
                openid:forwardNum,
                openid:wyid,
                openid:identifying,

            },
            success: function (jsonData) {
                var arr2 = JSON.parse(jsonData);
                console.log(arr2)
            }
        });
    })

// 点击可分享事件
    $(".zsongnum_div").click(function(){
        $.ajax({
            type: 'POST',
            url: '/getCommonAjax?fromflag=selfbilling&cardNo=' + json[i].cardId,
            async: false,
            data: {},
            success: function (jsonData) {
                var arr2 = JSON.parse(jsonData);
                console.log(arr2)
            }
        });
    })

});
