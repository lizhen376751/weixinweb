$(document).ready(function () {
    // var arr = [{
    // 	timer:"2017-2-21",
    // 	num:"106km",
    // 	project:"商品类",
    // 	kardName:["某某把套","某某车贴","某某车贴","某某车贴","某某车贴","某某车贴","某某车贴","某某车贴","某某车贴","某某车贴"],
    // 	mode:"现金",
    // 	cast1:"¥1482.00",
    // 	cast2:"¥1482.00"
    // },{
    // 	timer:"2017-2-21",
    // 	num:"106km",
    // 	project:"商品类",
    // 	kardName:["奥嘉隔热UII"],
    // 	mode:"支付宝",
    // 	cast1:"¥3560.00",
    // 	cast2:"¥3560.00"
    // },{
    // 	timer:"2017-2-21",
    // 	num:"120km",
    // 	project:"项目类",
    // 	kardName:["洗车卡255"],
    // 	mode:"现金",
    // 	cast1:"¥800.00",
    // 	cast2:"¥800.00"
    // }];
    var ul = $("ul");//获取ul容器
    //出去逗号的span
    function deletes() {
        var box = $(".box");
        for (var i = 0; i < box.length; i++) {
            var punctuation = $(box[i]).find(".punctuation");
            $(punctuation[punctuation.length - 1]).remove()
        }
    }

    //动态添加每一条消费记录

    $(document).ready(function () {
        var top = $("#top").val();
        $.ajax({
            type: 'POST',
            url: '/shopAjax',
            data: {
                businessType: "xiaofeijilu",
                top: top
            },
            success: function (jsonData) {
                var arr = JSON.parse(jsonData);
                console.log(arr);
                //alert(jsonData);
                addLabel(arr);
                var pJ = $(".pJ");//获取去评价按钮
                var sgbz = $(".sgbz");//获取施工步骤按钮
                sgbz.on("click",function () {
                    var xu_number = $(this).attr("xm_num");
                    var shopCode = $(this).attr("shopCode");
                    var wxpingzheng = $(this).attr("wxpingzheng");
                    window.location.href="/shopweixinServlet?serviceType=shigongbuzhou&xu_number="+xu_number+"&shopCode="+shopCode+"&wxpingzheng="+wxpingzheng;

                })
            }  // addLabel函数结束


        });
    })

    function addLabel(arr) {
        for(var i = 0;i < arr.length;i++){
            var xfxm = "";  //记录消费项目
            var xm_num = "";//记录该项目的数据
            var xfsp = "";  //记录消费商品
            var fkfs = "";//记录付款方式
            if (arr[i].purchaseProjectList.length != 0) {   //判断是否存在消费的项目
                var xm = "";
                for(var j = 0;j < arr[i].purchaseProjectList.length;j++){
                    var s = j + 1;
                    xm += '<div><span>'+s+'</span><p>'+arr[i].purchaseProjectList[j].projectName+'</p></div>';
                    xm_num += arr[i].purchaseProjectList[j].projectCode + "-" + arr[i].purchaseProjectList[j].projectId + "_";
                }
                xfxm = '<div class="middle_1 font_1">'+
                            '<div class="left_1">消费项目：</div>'+
                            '<div class="right_1">'+xm+'</div>'+
                        '</div>'
            }
            if (arr[i].purchaseProductList.length != 0) {   //判断是否存在消费的商品
                var sp = "";
                for(var h = 0;j < arr[i].purchaseProductList.length.length;h++){
                    w = h + 1;
                    sp += '<div><span>'+w+'</span><p>'+arr[i].purchaseProductList.length[h].projectName+'</p></div>'
                }
                xfsp = '<div class="middle_1 font_1">'+
                    '<div class="left_1">消费商品：</div>'+
                    '<div class="right_1">'+sp+'</div>'+
                    '</div>'

            }
            var htmlzhifufangshi = "";//第一种收款方式
            var htmlzhifufangshiSecond = "";//第二种收款方式
            if ((arr[i].zhifufangshiSecond == null) && (arr[i].zhifufangshi == null)) {   //判断付款方式是否都为空
                fkfs += "未付款";
            } else {

                if (arr[i].zhifufangshi != null) {   //第一种收款方式
                    htmlzhifufangshi = "<span>" + arr[i].zhifufangshi + "</span>（<span class='red'>￥" + arr[i].zhifufangshiJinE + "</span>）"
                }

                if (arr[i].zhifufangshiSecond != null) {  //第二种收款方式
                    htmlzhifufangshiSecond = arr[i].zhifufangshiSecond + "(<span class='red'>￥" + arr[i].zhifufangshiBJinE + "</span>)"
                } else {

                }

                fkfs += htmlzhifufangshiSecond + htmlzhifufangshi;
            }
            var html = '<li>'+
                         '<div class="detail_1">'+
                             '<div class="top_1 font_2">'+
                                '<div class="left_11">日期：'+arr[i].kprq+ '</div>'+
                                '<div class="right_11">公里数：'+arr[i].gongLiShu+ 'km</div>'+
                             '</div>'+xfsp+xfxm+
                            '<div class="bottom_1 font_1">'+
                                '<div>'+
                                     '<span style="margin-left: 26px;">施工店铺：</span>'+
                                     '<span>'+arr[i].shopName+'</span>'+
                                 '</div>'+
                                '<div>'+
                                    '<span style="margin-left: 26px;">付款方式：</span>'+fkfs+
                                '</div>'+
                            '</div>'+
                        '<div class="btn">'+
                            '<div class="pingjia font_1">去评价</div>'+
                             '<div class="sgbz font_1" xm_num = "'+xm_num+'" shopCode="'+arr[i].shopCode+'" wxpingzheng="'+arr[i].wxpingzheng+'">施工步骤</div>'+
                         '</div>'+
                    '</div>'+
                '</li>';
            ul.append(html)
        }
    }


})