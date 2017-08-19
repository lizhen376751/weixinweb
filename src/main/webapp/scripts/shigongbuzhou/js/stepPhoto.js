(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize = Math.floor(100 * (clientWidth / 1080)) + 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);

$(function () {

    var cardNumber = $("#cardNumber").val();
    var cardId = $("#cardId").val();
    var wxpingzheng = $("#wxpingzheng").val();
    var xunumber = $("#xunumber").val();
    var car_num = $(".car_num")//------------------------------------------------获取车牌号里的内容
    var project_name = $(".project_name");//-----------------------------------获取项目的名称
    var ul = $("ul");//-----------------------------------------------------获取步骤容器
    var btn = $(".btn");//---------------------------------------------------获取标准流程按钮
    var plateNumber = $("#plateNumber").val()//----------------------------获取车牌号
    //页面发送请求且查看施工步骤
    //alert(cardNumber)
    //alert(cardId)
    $.ajax({
        type: 'POST',
        url: '/getCommonAjax?fromflag=twobilling&cardId='+cardId+'&cardNumber='+cardNumber,
        async: false,
        data: {
        },
        success: function (jsonData) {
            var arr2 = JSON.parse(jsonData);
            var arr = arr2.edbWorkComplateMessageList;
            console.log(arr2);
            var title = '';//单个项目中的title
            //写出相应的车牌号码和项目名称
            title+="<div class='title font_1'>" +
                "<div class='title_left'>"+
                "<span class='step_color1'>车牌号：</span>"+
                // "<span class='step_color2 car_num'>"+plateNumber+"</span>"+
                "</div>"+
                "<div class='title_right'>"+
                "<span class='step_color2 project_name'>"+arr[0].projectName+"</span>"+
                "<span class='step_color1'>项目：</span>"+
                "</div>"+
                "</div>"+
                "<ul >"

            var itemCode = ""; //记录数据中的itemCode
            var shopCodeLm = "";//记录数据中的shopCodeLm
            for(var j = 0; j < arr.length; j++){
                itemCode = arr[j].itemCode;
                shopCodeLm = arr[j].shopCodeLm;
                if(arr[j].commonImages.length != 0){
                    var srcs = arr[j].commonImages[0].fileUrl;
                }else{
                    // var srcs = arr2[j].commonImages[0].fileUrl;
                    srcs = "";
                }
                title+= "<li>" +
                    "<div class='photo'>" +
                    "<input type='file' name='driving ' class='filepath'/>"+
                    "<img src=' "+srcs+" ' class='images' onclick='filefun(this)'/>" +
                    "<div class='imgs'>"+
                    "<img src='' class='img2 xing_shi'/>"+
                    "</div>"+
                    "<input type='hidden'  class='itemMxId' value="+arr[j].itemMxId+"/>"+
                    "<input type='hidden'  class='projectStepId' value="+arr[j].projectStepId+"/>"+
                    "<input type='hidden'  class='orderCode' value="+arr2.resultBillsDetail.wxpingzheng+"/>"+
                    "</div>" +
                    "<div style='display: none;' class='filebag font_3'>"+
                    "<ul>"+
                    "<li><input  type='button'  onclick='das(this,1)'  value='上传视频'></li>"+
                    "<li><input  type='button' onclick='das(this,2)' value='上传图片'></li>"+
                    "</ul>"+
                    "</div>"+
                    "<span class='step_num font_4 color_3'>"+arr[j].stepNumber+"</span>" +
                    "<p class='step_name font_3'>"+arr[j].projectStepName+"</p>" +
                    "</li>";

            }
            title+="</ul>"+
                "<div class='btn  font_3 color_3' >激活</div>"
            //     "<span class='fgx'></span>"



            $(".wrap").append(title);


            var images = $(".images"); //--------------------------------------获取页面中的每一步的图片
            images.on("click", function () {
                var src = $(this).attr("src");
                $(".box").show();
                $(".img").attr("src", src);
                $(".box").bind("touchmove", function (e) {
                    e.preventDefault(); //遮罩层出现后禁止body滑动
                });
                var boxH = $(".box").height();
                var imgw = $(".img").width();
                var imgH = $(".img").height();
                var imgTop = (boxH - imgH)/200
                if(imgH > imgw && boxH > imgH){
                    console.log(1)
                    $(".img").css({
                        margin: imgTop+"rem auto 0rem"
                    })
                }else if(boxH <= imgH){
                    console.log(2)
                    $(".img").css({
                        margin: "0rem auto",
                        height: "100vh"
                    })
                }
            })
        }


    });



    // $(".box").on("click", function () {
    //     $(this).hide()
    // });
    // $(".img").on("click", function () {
    //     $(".box").hide()
    // });
    //-------------------------------------------------动态添加每一条步骤

    //--------------------------------------------------点击标准流程跳转的页面



    $(".filepath").on("change",function() {
        var that = this;
        var photoExt=this.value.substr(this.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
        var tp =".jpg,.gif,.bmp,.JPG,.GIF,.BMP,.ico,.png,.mp4,.MP4";
        var rs=tp.indexOf(photoExt);
        var onself = this;
        if(this.value == ""){
            return false
        }else if(rs < 0){            //如果返回的结果大于或等于0，说明包含允许上传的文件类型
            alert("您选择的上传文件不是有效的图片文件！");
            return false;
        }else{
            //var shopcode=mineShopCode;
            var i = $(this).index()+1;
            var srcd =this.files[0];
            var projectStepId = $(this).nextAll(".projectStepId").val();
            var itemMxId = $(this).nextAll(".itemMxId").val();
            var orderCode=$(this).nextAll(".orderCode").val();
            var shopCode ="YLB0002";
            var paramMap = {
                "orderCode":orderCode,
                "shopCode" :"YLB0002",
                "itemMxId" :itemMxId,
                "projectStepId" :projectStepId,
                "workType" : "shigong"
            }
            console.log(this.files[0]);
            $.ajax({
                type    : 'GET',
                url     : '/ossconfig/YLB0002/8',
                data    : {},
                success:function(jsondata){
                    var json = JSON.parse(jsondata);
                    Duducreds=json;
                    // console.log(json)
                    new applyTokenDoNew(srcd,paramMap);
                },
                error:function(data){

                }

            });
            // console.log(srcd)
            //setTimeout(function() { new applyTokenDoNew(srcd,DuduOssCallbackVarData1);},2000);
            $(this).nextAll(".images").hide();   //this指的是input
            $(this).nextAll("p").hide();
            $(this).nextAll(".imgs").show();  //fireBUg查看第二次换图片不起做用
            // $(this).nextAll('.close').show();   //this指的是input
            var state = false;
            var imgage = $(this).nextAll(".imgs").children(".img2");
            function a(){
                // console.log(srcs)
                if(srcs == "" && state == false){
                    setTimeout(function() { a();},2000);
                }else{
                    console.log(srcs);
                    imgage.attr("src",srcs);
                    // $(onself).nextAll(".tupian").val(projectId);
                    srcs = "";
                    state = true;
                }
            }
            a();

        }

        // var srcs = getObjectURL(this.files[0]);   //获取路径
        // if(srcs){
        //     $(this).nextAll(".imgs").children(".img2")[0].src = srcs;
        //     $(this).nextAll(".img1").hide();   //this指的是input
        //     $(this).nextAll("p").hide();
        //     $(this).nextAll(".imgs").show();  //fireBUg查看第二次换图片不起做用
        //     $(this).nextAll('.close').show();   //this指的是input
        //     $(this).nextAll(".imgs").children(".img2").attr("src",srcs);    //this指的是input
        //     $(this).val('');    //必须制空
        // }


    })



    $(".btn").on("click", function () {

        var nn = $(".img2");
        var mm =0;
        for(var aa=0;aa<nn.length;aa++){
            if(nn.eq(aa).attr("src")==""||nn.eq(aa).attr("src")==null){
                mm=1;
                // alert("请等图片或者视频上传中.....")
            }else{

            }
        }
        if(mm!=0){
            alert("请等图片上传中.....")
        }else{
            window.location.href = "lmInternalJump?business=lmkInfo";
        }
    });

})

//显示选择图片或者视频
function uuid(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    radix = radix || chars.length;

    if (len) {
        // Compact form
        for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
    } else {
        // rfc4122, version 4 form
        var r;

        // rfc4122 requires these characters
        uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
        uuid[14] = '4';

        // Fill in random data.  At i==19 set the high bits of clock sequence as
        // per rfc4122, sec. 4.1.5
        for (i = 0; i < 36; i++) {
            if (!uuid[i]) {
                r = 0 | Math.random()*16;
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
    }
    return uuid.join('');
}

function filefun(obj){

    $(obj).parent().next().show();



}
//显示选择图片或者视频
function das(obj,elem){
    $(".filebag").hide();
    if(elem==1){     //视屏
        $(obj).parent().parent().parent().prev().find(".filepath").attr({accept:"video/*",capture:"camcorder"});
        $(obj).parent().parent().parent().prev().find(".filepath").click();
    }else{
        $(obj).parent().parent().parent().prev().find(".filepath").attr({accept:"image/*",capture:"camera"});
        $(obj).parent().parent().parent().prev().find(".filepath").click();
    }
}

//上传阿里












