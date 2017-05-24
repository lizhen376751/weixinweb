/**
 * Created by Administrator on 2017/5/24 0024.
 */
//微信授权方法
//arr 必须为数组，类似 ['scanQRCode']
function wxsq(arr) {
    $.ajax({
        url: "/getCommonAjax2",
        type: 'POST',
        data: {
            url: location.href.split('#')[0],
            fromflag: "jssdk"
        },
        success: function (data) {
            console.log(data);
            wx.config({
                debug: false,//true为开启调试模式，数据会alert出来，false为关闭调试模式
                appId: data.appId, //公众号的唯一标示，必填
                timestamp: data.timestamp, //生成签名的时间戳，必填
                nonceStr: data.nonceStr,   //生成签名的随机串，必填
                signature: data.signature,  // 签名，必填
                jsApiList: arr    //--------------------------------- // 所有要调用的 API 都要加到这个列表中
            });
        },
        dataType: 'json',
        async: false
    });
}