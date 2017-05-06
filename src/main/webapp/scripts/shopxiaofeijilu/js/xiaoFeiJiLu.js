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
                var html = "";
                // for (var i = 0; i < arr.length;i++) {
                //         var cards = ""
                //        if(arr[i].purchaseProductList.length!=0){   //判断是否存在消费的商品
                //            for (var j = 0; j < arr[i].purchaseProductList.length; j++) {   //遍历商品
                //                cards += "<span class='txt'>" + arr[i].purchaseProductList[j].productName + "</span>" +
                //                    "<span class='punctuation'>；</span>";
                //            }
                //        }
                //         if(arr[i].purchaseProjectList.length!=0){   //判断是否存在消费的项目
                //             for (var h = 0; h < arr[i].purchaseProjectList.length; h++) {   //遍历商品
                //                 cards += "<span class='txt'>" + arr[i].purchaseProjectList[h].projectName + "</span>" +
                //                     "<span class='punctuation'>；</span>";
                //             }
                //         }
                //
                //
                //        if(arr[i].zhifufangshiSecond==null||arr[i].zhifufangshiSecond==""&& arr[i].zhifufangshi==null||arr[i].zhifufangshi==""){   //判断付款方式是否都为空
                //            html2+="未付款";
                //        }else{
                //            if(arr[i].zhifufangshiSecond!=null||arr[i].zhifufangshiSecond!=""){  //第二种收款方式
                //                htmlzhifufangshiSecond +=  "'+arr[i].zhifufangshiSecond+'(<span class='red'>" + arr[i].zhifufangshiBJinE + "</span>)"
                //            }else{
                //
                //            }
                //            if(arr[i].zhifufangshi!=null||arr[i].zhifufangshi!=""){   //第一种收款方式
                //                htmlzhifufangshi +=  "<span>" + arr[i].zhifufangshi + "</span>（<span class='red'>" + arr[i].zhifufangshiJinE + "</span>）单据应收<span class='red'>" + arr[i].cast2 + "</span>"
                //            }
                //            html2+=htmlzhifufangshiSecond+htmlzhifufangshi;
                //        }
                //
                //         html += "<div class='box'>" +
                //             "<div class='top font_2 blue'>" +
                //             "<div class='timer'>" + arr[i].kprq +
                //             "</div>" +
                //             "<div class='number'>公里数：" + arr[i].gongLiShu +
                //             "</div>" +
                //             "</div>" +
                //             "<div class='middle'>" +
                //             "<div class='left font_1 blue'>消费：</div>" +
                //             "<div class='right font_1'>" + cards + "</div>" +
                //             "</div>" +
                //             "<div class='bottom font_1'>" +
                //             "<div class='left blue'>付款方式：</div>" +
                //             "<div class='right'>" +html2+
                //             "</div>" +
                //             "</div>" +
                //             "</div>"
                //
                // }
                for (var i = 0; i < arr.length; i++) {
                    var html2 = "";
                    var cards = ""   //消费的内容
                    var htmlzhifufangshi = "";  //第一种收款方式
                    var htmlzhifufangshiSecond = "";//第二种收款方式

                    if (arr[i].purchaseProductList.length != 0) {   //判断是否存在消费的商品
                        var sdd = arr[i].purchaseProductSTR.split(',');
                        cards += "<div class='left font_1 blue'>消费商品：</div>" +
                            "<div class='right font_1'>" ;
                        for (var p = 0; p < sdd.length; p++) {
                            cards += "<span class='txt'>" + sdd[p] + ";<br/> </span>"
                            // "<span class='punctuation'>；</span>"+
                        }
                        cards += "</div>";
                        // cards += "<div class='left font_1 blue'>消费商品：</div>" +
                        //     "<div class='right font_1'>" +
                        //     "<span class='txt'>" + arr[i].purchaseProductSTR + ";</span>" +
                        //     // "<span class='punctuation'>；</span>"+
                        //     "</div>";

                    }
                    if (arr[i].purchaseProjectSTR.length != 0) {   //判断是否存在消费的项目
                        var sbb = arr[i].purchaseProjectSTR.split(',');

                        // cards += "<div class='left font_1 blue'>消费项目：</div>" +
                        //     "<div class='right font_1'>" +
                        //     "<span class='txt'>" + arr[i].purchaseProjectSTR + ";</span>" +
                        //     // "<span class='punctuation'>；</span>"+
                        //     "</div>";
                        cards += "<div class='left font_1 blue'>消费项目：</div>" +
                            "<div class='right font_1'>"
                        for (var p = 0; p < sbb.length; p++) {

                            cards += "<span class='txt'>" + sbb[p] + ";<br/> </span>"
                            // "<span class='punctuation'>；</span>"+
                        }
                        cards += "</div>";


                    }


                    if ((arr[i].zhifufangshiSecond == null) && (arr[i].zhifufangshi == null)) {   //判断付款方式是否都为空
                        html2 += "未付款";
                    } else {

                        if (arr[i].zhifufangshi != null) {   //第一种收款方式
                            htmlzhifufangshi += "<span>" + arr[i].zhifufangshi + "</span>（<span class='red'>" + arr[i].zhifufangshiJinE + "</span>）"
                        }

                        if (arr[i].zhifufangshiSecond != null) {  //第二种收款方式
                            htmlzhifufangshiSecond += arr[i].zhifufangshiSecond + "(<span class='red'>" + arr[i].zhifufangshiBJinE + "</span>)"
                        } else {

                        }

                        html2 += htmlzhifufangshiSecond + htmlzhifufangshi;
                    }

                    html += "<div class='box'>" +
                        "<div class='top font_2 blue'>" +
                        "<div class='timer'>" + arr[i].kprq +
                        "</div>" +
                        "<div class='number'>公里数：" + arr[i].gongLiShu +
                        "</div>" +
                        "</div>" +
                        "<div class='middle'>" +
                        cards +                    //添加消费内容
                        "</div>" +
                        "<div class='bottom font_1'>" +
                        "<div class='left blue'>付款方式：</div>" +
                        "<div class='right'>" + html2 +
                        "</div>" +
                        "</div>" +
                        "</div>"

                }
                $("body").append(html);
                deletes()

            }  // addLabel函数结束


        });
    })

    function addLabel(arr) {
// 		var html = "";
//
// 		for (var i = 0; i < arr.length;i++) {
// 			if (arr[i].project == "项目类") {
// 				var cards = "";
// 				//遍历所有的cards的内容
// 				for (var j = 0;j < arr[i].kardName.length;j++) {
// 					cards += "<span class='txt'>"+arr[i].kardName[j]+"</span>"+
// 						"<span class='punctuation'>；</span>";
// 				}
// 				html += "<div class='box'>"+
// 							"<div class='top font_2 blue'>"+
// 								"<div class='timer'>"+arr[i].timer+
// 								"</div>"+
// 								"<div class='number'>公里数："+arr[i].num+
// 								"</div>"+
// 							"</div>"+
// 							"<div class='middle'>"+
// 								"<div class='left font_1 blue'>消费项目：</div>"+
// 								"<div class='right font_1'>"+cards+"</div>"+
// 							"</div>"+
// 							"<div class='bottom font_1'>"+
// 								"<div class='left blue'>付款方式：</div>"+
// 								"<div class='right'>"+
// 									"<span>"+arr[i].mode+"</span>（<span class='red'>"+arr[i].cast1+"</span>）单据应收<span class='red'>"+arr[i].cast2+"</span>"+
// 								"</div>"+
// 							"</div>"+
// 						"</div>"
// 			} else{
// 				var cards = "";
// 				//遍历所有的cards的内容
// 				for (var j = 0;j < arr[i].kardName.length;j++) {
// 					cards += "<span class='txt'>"+arr[i].kardName[j]+"</span>"+
// 						"<span class='punctuation'>；</span>";
// 				}
// 				html += "<div class='box'>"+
// 							"<div class='top font_2 blue'>"+
// 								"<div class='timer'>"+arr[i].timer+
// 								"</div>"+
// 								"<div class='number'>公里数："+arr[i].num+
// 								"</div>"+
// 							"</div>"+
// 							"<div class='middle'>"+
// 								"<div class='left font_1 blue'>消费商品：</div>"+
// 								"<div class='right font_1'>"+cards+"</div>"+
// 							"</div>"+
// 							"<div class='bottom font_1'>"+
// 								"<div class='left blue'>付款方式：</div>"+
// 								"<div class='right'>"+
// 									"<span>"+arr[i].mode+"</span>（<span class='red'>"+arr[i].cast1+"</span>）单据应收<span class='red'>"+arr[i].cast2+"</span>"+
// 								"</div>"+
// 							"</div>"+
// 						"</div>"
// 			}
// 		}
// 		$("body").append(html);
// 		deletes()
// //		var projects = $(".middle .center");
// //		for (var i = 0;i < projects.length;i++) {
// //			if (projects[i].innerText == "项目类") {
// //				$(projects[i]).addClass("yellow")
// //			} else{
// //				$(projects[i]).addClass("green")
// //			}
// //		}
// 	}  // addLabel函数结束
        addLabel(arr)
    }
})