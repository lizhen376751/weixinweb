Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};
$(window).load(function () {
    setTimeout(function () {
        $("#loading").hide();
        // $(".center_zhis ").show();
        // $(".conter_main").show()
    }, 1000);
});


$(document).ready(function () {
    //时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date = y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }

    //条形码的样式
    var options = {
        format: "CODE128",
        displayValue: false,
        height: 100,
        // background:"#31a3ff",
        lineColor: "#000"//条形码颜色
    };
    //元素添加函数
    function obj_append(name, class_one, obj_big, label, txt, src, attr1, attr2, attr3) {
        var name = $("<" + label + "/>");
        name.addClass(class_one);
        if (txt) {
            name.text(txt)
        }
        obj_big.append(name);
        if (src) {
            name[0].src = src;
        }
        if (attr1 && attr2 && attr3) {
            name.attr({
                card_id: attr1,
                item_code: attr2,
                type_flg: attr3
            })
        }
    };

    //获取body元素
    var body = $("body");
    //向html中添加数据
    function appenging(arr) {
        //console.log(arr)
        if (arr != null && arr.length > 0) {
            for (var i = 0; i < arr.length; i++) {
                obj_append("div1", "lianMeng", body, "div", "");
                //卡的头部
                obj_append("div2", "box", $($(".lianMeng")[i]), "div", "");
                //卡的名字
                obj_append("div3", "card_name font_3", $($(".box")[i]), "div", arr[i].cardName);
                //卡的详情
                obj_append("div4", "card_content", $($(".box")[i]), "div", "");
                //条形码
                obj_append("div5", "card_barcode", $($(".card_content")[i]), "div", "");
                obj_append("div6", "barcode_img", $($(".card_barcode")[i]), "img", "");
                //生成条形码
                $($('.card_content .card_barcode .barcode_img')[i]).JsBarcode(arr[i].cardNumber, options);//jQuery
                //卡号
                obj_append("div7", "card_number font_4", $($(".card_content")[i]), "div", "");
                obj_append("div8", "", $($(".card_number")[i]), "p", "您的卡号为：");
                obj_append("div9", "number", $($(".card_number")[i]), "div", "");
                obj_append("div10", "first", $($(".number")[i]), "span", arr[i].cardNumber);
                //卡内剩余
                obj_append("div11", "card_surplus", $($(".lianMeng")[i]), "div", "");
                obj_append("div12", "font_4", $($(".card_surplus")[i]), "p", "卡内剩余");
                //ul及li
                obj_append("div13", "font_4", $($(".card_surplus")[i]), "ul", "");
                obj_append("div14", "biaoTou", $($("ul")[i]), "li", "");
                //第一部分
                obj_append("div16", "width_2 border_1 height", $($(".biaoTou")[i]), "span", "项目");
                obj_append("div17", "width_2 margin_2 border_1 height", $($(".biaoTou")[i]), "span", "有效期");
                obj_append("div18", "width_2 margin_2 border_1 height", $($(".biaoTou")[i]), "span", "次数");
                var s = 0;
                for (var j = 0; j < arr[i].leftMx.length; j++) {
                    var yxrqDateTime = arr[i].leftMx[j].effectiveDate;
                    var dates = dateFormat(yxrqDateTime);
                    var cc = dates.replace(/-/ig, "/");
                    var news = new Date();
                    var e = news.getFullYear();
                    var f = news.getMonth() + 1;
                    var g = news.getDate();
                    var h = e + "/" + f + "/" + g;
                    // console.log(h);
                    var newDate = new Date(h);
                    var oldDate = new Date(cc);
                    if (arr[i].leftMx[j].currentNum != 0 && arr[i].leftMx[j].currentNum != "") {
                        if (oldDate >= newDate) {
                            obj_append("div14", "border_2", $($("ul")[i]), "li", "");
                            s++;
                            var li = $($("ul")[i]).find("li");
                            //obj_append("div15","width_1",$(li[s]),"span",s);
                            obj_append("div16", "width_2", $(li[s]), "span", arr[i].leftMx[j].spName);
                            obj_append("div17", "width_2 margin_2", $(li[s]), "span", dates);
                            obj_append("div18", "width_2 margin_2", $(li[s]), "span", arr[i].leftMx[j].currentNum);
                            obj_append("div15", "width_1 margin_1", $(li[s]), "img", "", "/files/lianMengKa/img/erweima.png", arr[i].leftMx[j].cardId, arr[i].leftMx[j].itemCode, arr[i].leftMx[j].typeFlg);
                        }
                    }
                }
                //------------------------------------------------------------------------判断当前联盟卡里是否还有项目
                var cars_lis = $($("ul")[i]).find("li");
                if (cars_lis.length == 1) {
                    cars_lis.parents(".lianMeng").hide();
                }
                //添加明细
                obj_append("div13", "cardiv", $($(".card_surplus")[i]), "div", "");
                obj_append("div19", "card_detailed font_2", $($(".card_surplus .cardiv")[i]), "a", "明细");
                $($("a")[i]).attr("href", "/oauthLoginServlet?flagStr=lianMengDetails&cardName=" + arr[i].cardName + "&cardNo=" + arr[i].cardNumber)


                //添加每一张卡与卡之间的分界条
                obj_append("div20", "fjt", $($(".lianMeng")[i]), "span");

                //obj_append("div21","card_detailed  card_jihuo font_3",$($(".card_surplus")[i]),"a","开单激活");
                //$($("a")[i]).attr("href","/lmInternalJump?business=selfbilling")


            }
        } else {
            alert("当前车辆无联盟卡信息！");
        }


    }


    var CarId = $("#CarId").val();
    var contextPathStr = $("#contextPathStr").val();
    $.ajax({
        type: 'POST',
        url: '/getCommonAjax',
        data: {
            fromflag: "queryLmkInfoList",
            CarId: CarId
        },
        success: function (jsonData) {
            var json = JSON.parse(jsonData);
            appenging(json);
            console.log(json)
            //添加激活卡
            if (json != null && json.length > 0) {
                for (var i = 0; i < json.length; i++) {
                    var acardId = json[i].cardId;
                    var acardNumber = json[i].cardNumber;
                    $.ajax({
                        type: 'POST',
                        url: '/getCommonAjax?fromflag=selfbilling&cardNo=' + json[i].cardId,
                        async: false,
                        data: {},
                        success: function (jsonData) {
                            var arr2 = JSON.parse(jsonData);
                            console.log(arr2)
                            if (arr2.statusEnum == "BLANK") {
                                // alert(arr2.statusEnum)
                                //允许开单
                                var htmllll = '&nbsp;&nbsp;&nbsp;&nbsp;<a class="card_detailed font_2" href="/lmInternalJump?business=selfbilling&cardId=' + json[i].cardId + '&cardNumber='+json[i].cardNumber+'">自助激活</a>'
                                $(".card_surplus").eq(i).find(".cardiv").append(htmllll)

                            } else if(arr2.statusEnum == "REJECT" || arr2.statusEnum == "CREATE"){
                                //允许开单
                                var htmllll = '&nbsp;&nbsp;&nbsp;&nbsp;<a class="card_detailed font_2" href="/lmInternalJump?business=selfbilling&cardId=' + json[i].cardId + '&cardNumber='+json[i].cardNumber+'">自助激活</a>'
                                $(".card_surplus").eq(i).find(".cardiv").append(htmllll)
                            }else{

                            }

                        }
                    });
                }
            }
            var imgs = $(".card_surplus ul li img");
            var zheZhaoCeng = $(".zheZhaoCeng");
            var erWeiMa = $(".erWeiMa");
            imgs.on("click", function () {
                var card_ids = $(this).attr("card_id");
                var item_codes = $(this).attr("item_code");
                var type_flgs = $(this).attr("type_flg");
                zheZhaoCeng.css("display", "block");
                zheZhaoCeng.bind("touchmove", function (e) {
                    e.preventDefault(); //遮罩层出现后禁止body滑动
                });

                $.ajax({
                    type: "POST",
                    url: '/getCommonAjax',
                    data: {
                        fromflag: "getXmkQRCode",
                        card_id: card_ids,
                        item_code: item_codes,
                        type_flg: type_flgs
                    },
                    success: function (jsonData) {
                        erWeiMa.children().remove();
                        var json = JSON.parse(jsonData);
                        erWeiMa.qrcode(json);
                        erWeiMa.children().css({
                            width: "100%",
                            height: "100%"
                        })
                    }
                });
            })
            zheZhaoCeng.on("click", function () {
                $(this).css({
                    display: "none"
                })
                erWeiMa.children().remove()
            })
        }
    });


})