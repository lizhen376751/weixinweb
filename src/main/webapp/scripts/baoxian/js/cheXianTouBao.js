
$(document).ready(function(){
    var mineShopCode = $("#mineShopCode").val();
    console.log(mineShopCode);
    if(mineShopCode == "null" ){
        $(".nav").hide();
    }else{
    }
    $('.titles span').click(function() {
        var i = $(this).index();//下标第一种写法
        //var i = $('tit').index(this);//下标第二种写法
        $(this).addClass('selects').siblings().removeClass('selects');
        $('.list .one,.two,.three').eq(i).show().siblings().hide();
    });

    //车辆信息部分
    //日期控件部分
    function all_day () {
        var currYear = (new Date()).getFullYear();
        var opt={};
        opt.date = {preset : 'date'};
        opt.datetime = {preset : 'datetime'};
        opt.time = {preset : 'time'};
        opt.default = {
            theme: 'android-ics light', //皮肤样式
            display: 'modal', //显示方式
            mode: 'scroller', //日期选择模式
            dateFormat: 'yyyy-mm-dd',
            lang: 'zh',
            height:80,//每一栏的高度
            width:300,//每一栏的宽度
            showNow: true,
            nowText: "今天",
            startYear: currYear - 10, //开始年份
            endYear: currYear + 10 //结束年份
        };
        var optDateTime = $.extend(opt['datetime'], opt['default']);
        var optTime = $.extend(opt['time'], opt['default']);
        $("#registration_date").mobiscroll($.extend(opt['date'], opt['default']));
    }
    all_day()
    //	初始化当前日期
    //function show(){
    //    var mydate = new Date();
    //    var str = "" + mydate.getFullYear() + "-";
    //    str += (mydate.getMonth()+1) + "-";
    //    str += mydate.getDate() ;
    //    return str;
    //  }
    //  $("#registration_date").val(show());

    //动态添加服务顾问及联盟总部
    var service = $(".nav .nav_left select");  //服务顾问
    var quarters = $(".nav .nav_right select");  //联盟总部
    function add_service (arr,big_lable) {
        var html = "";
        for (var i = 0;i < arr.length;i++) {
            html += '<option value="'+arr[i].code+'">'+arr[i].name+'</option>';
        }
        big_lable.append(html);
    }
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag : "lianmeng"
        },
        success:function(jsondata){
            var json = JSON.parse(jsondata);
            // add_insurance (json);
            // panduan()
            add_service(json,quarters)
            //console.log(json);
        },
        error:function(eee){
            // alert("失败");
        }
    });

    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "fuwuguwen",
            mineShopCode : mineShopCode
        },
        success:function(jsondata){
            var json = JSON.parse(jsondata);
            add_service(json,service)
            //console.log(json);
        },
        error:function(eee){
            // alert("失败");
        }
    });
    //根据车牌号码显示信息
    var car_number = $("#car_number");//车牌号
    var tishi = $(".tishi");//模糊搜索出来的车牌号
    //动态添加车牌信息
    function add_tishi(arr){
        var xinxi = $(".xinxi");
        xinxi.remove()
        var html = "";
        for(var i = 0;i<arr.length;i++){
            html += "<div class='xinxi'>"+arr[i].plateNumber+"</div>"
        }
        tishi.append(html)
    }
    var state = true;
    car_number.on("keyup",function(){
        var val = $(this).val();
        if(val.length >= 3){
            if(state){
                state = false;
                $.ajax({
                    type : 'POST',
                    url  : '/getCommonAjax',
                    data : {
                        fromflag : "xinxi",
                        car_number: val,
                        mineShopCode : mineShopCode
                    },
                    success:function(jsondata){
                        state = true;
                        var json = JSON.parse(jsondata);
                        //console.log(json);
                        if(json[0]){
                            tishi.css("display","block");
                            add_tishi(json);
                            add_information(json)
                        }
                    },
                    error:function(eee){
                        // alert("失败");
                    }
                });
            }

        }else{
            tishi.css("display","none");
        }

    })
    $("body").on("click",function(){
        tishi.css("display","none");
    })
    //时间戳转换成日期格式
    function dateFormat(val) {
        var now = new Date(val),
            y = now.getFullYear(),
            m = now.getMonth() + 1,
            d = now.getDate();
        var date=y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        return date.substr(0, 11);
    }
    //根据车牌号自动补全用户信息
    function add_information(arr) {
        var tishi = $(".tishi");
        //获取隐藏域
        var hidden = $("#hiddens");
        //获取车牌信息的弹出框
        var xinxi = $(".xinxi");
        //获取车辆信息页中的信息
        var car_number = $("#car_number");//车牌号
        var your_name = $("#your_name"); //用户名
        var sex_nan = $("#nan");//性别男
        var sex_nv = $("#nv"); // 性别女
        var phone_number = $("#phone_number");//手机号
        var daihao = $("#daihao");//车辆代号
        var engine_number = $("#engine_number");//发动机号码
        var registration_date = $("#registration_date");//注册日期
        var property = $(".property") //使用性质
        xinxi.on("click",function(){
            var i = $(this).index();
            car_number.val(arr[i].plateNumber);
            your_name.val(arr[i].customerName);
            phone_number.val(arr[i].mobilePhone);
            daihao.val(arr[i].frameNumber);
            engine_number.val(arr[i].engineNumber);
            var date_time = dateFormat(arr[i].createTime)
            registration_date.val(date_time);
            if(arr[i].sex == 1){
                sex_nan.attr("checked",true)
            }else{
                sex_nv.attr("checked",true)
            }
            if(arr[i].licenseFlag != null && arr[i].licenseFlag != ""){
                switch (arr[i].licenseFlag)
                {
                    case "0201":     //家庭自用-非营运
                        property.val("0201");
                        break;
                    case "0202":     //机关自用-非营运
                        property.val("0202");
                        break;
                    case "0203":     //企业自用-非营运
                        property.val("0203");
                        break;
                    case "0209":     //特殊用途-非营运
                        property.val("0201");
                        break;
                    case "0104":     //出租客运-营运
                        property.val("0104");
                        break;
                    case "0105":     //租赁客运-营运
                        property.val("0105");
                        break;
                    case "0106":    //城市公交-营运
                        property.val("0106");
                        break;
                    case "0107":    //公路客运-营运
                        property.val("0107");
                        break;
                    case "0108":    //营业货运-营运
                        property.val("0108");
                        break;
                    case "0109":    //特殊用途-营运
                        property.val("0109");
                        break;
                }
            }else{
                property.val("0201");
            };
            hidden.val(arr[i].id);
            tishi.css("display","none");
        })
    }

    //保险险种部分
    //添加保险公司
    function add_company (arr) {
        var baoxian_company = $(".list .two .insurance_company .baoxian")
        var html = "";
        for (var i = 0; i < arr.length;i++) {
            html += "<div><input type='checkbox' name='xianzhong' id='wang_wang' value='"+arr[i].id+"' /><span>"+arr[i].insuranceName+"</span></div>"
        }
        baoxian_company.append(html);
    }
//  add_company(ab)          添加保险公司就调用这个函数                                                               

    //保险公司的布局
    function  company_css() {
        var company = $(".list .two .insurance_company .baoxian div");
        for (var i = 0; i < company.length;i++) {
            if (i % 3 != 0) {
                $(company[i]).addClass("margin_left2");
            }
        }
    }



    //添加及布局每一条险种
    function add_insurance (arr) {
        var ul =  $(".list .two ul");
        for (var i = 0;i < arr.length;i++) {
            var html = "";
            var typeFlag = "";   //每一条险种的名字
            var buJiMianPeiFlag = "";  //不计免赔
            var peichangeduFlag = "";  //赔偿额度
            var baozhang_num = "";     //保障人数
            //是否默认选中
            if (arr[i].typeFlag == 1) {
                if (arr[i].typeName == "投保交强险" || arr[i].typeName == "代缴车船税") {
                    typeFlag += "<input type='checkbox' name='chexian' id='' value='"+arr[i].id+"' checked='checked'/>"+
                        "<span class='font_1 color_4'>"+arr[i].typeName+"</span>"+
                        "<span class='font_5 color_3'>（必买险种）</span>"
                } else{
                    typeFlag += "<input type='checkbox' name='chexian' id='' value='"+arr[i].id+"' checked='checked' class='chexian'/><span class='font_1 color_4'>"+arr[i].typeName+"</span>";
                }

                //不计免赔拼接
                if (arr[i].buJiMianPeiFlag == 1) {
                    buJiMianPeiFlag +=   "<label for=''>不计免赔：</label>"+
                        "<input type='radio' name='bjmp_"+arr[i].id+"' value='1'class='fou' checked='checked'/>是"+
                        "<input type='radio' name='bjmp_"+arr[i].id+"' value='0' />否"
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' style='margin-left: 84px;'>赔偿限额：</label>"+
                            "<select name='pcxe_"+arr[i].id+"'>"+options+"</select>"
                    }
                }else{
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' >赔偿限额：</label>"+
                            "<select name='pcxe_"+arr[i].id+"'>"+options+"</select>"
                    }
                }
            }else{
                typeFlag += "<input type='checkbox' name='chexian' id='' value='"+arr[i].id+"' class='chexian'/><span class='font_1 color_4'>"+arr[i].typeName+"</span>";
                //不计免赔拼接
                if (arr[i].buJiMianPeiFlag == 1) {
                    buJiMianPeiFlag +=   "<label for=''>不计免赔：</label>"+
                        "<input type='radio' name='bjmp_"+arr[i].id+"' value='1'class='fou'/>是"+
                        "<input type='radio' name='bjmp_"+arr[i].id+"' value='0' />否"
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' style='margin-left: 84px;'>赔偿限额：</label>"+
                            "<select name='pcxe_"+arr[i].id+"'>"+options+"</select>"
                    }
                }else{
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].zidianid+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' >赔偿限额：</label>"+
                            "<select name='pcxe_"+arr[i].id+"'>"+options+"</select>"
                    }
                }
            }
            //保障人数的拼接
            if (arr[i].baozhangrenshuFlag == 1) {
                baozhang_num += "<div>"+
                    "<label for='' class='people_number'>保障人数：</label>"+
                    "<input type='text' name='bzrs_"+arr[i].id+"' value='"+arr[i].baozhangrenshu+"'/>"+
                    "</div>"
            }
            html += "<li>"+typeFlag+
                "<div class='font_5 color_2' style='margin-bottom: 32px;'>"+
                buJiMianPeiFlag+peichangeduFlag+baozhang_num+
                "</div>"+
                "<span class='xian'></span>"+
                "</li>"
            ul.append(html)

        }
    }

    //整个判断
    function panduan() {
        //每一条车险的选中判断
        var chexian = $(".chexian");
        function vall () {
            chexian.each(function(){
                var trs = $(this).is(':checked');
                if (trs != true) {
                    $(this).nextAll().children("select").val("");
                    $(this).nextAll().children("input[type='radio']").attr("disabled","disabled");
                    $(this).nextAll().children("select").val("").attr("disabled","disabled");
                    $(this).nextAll().children("div").children("input[type='text']").attr("disabled","disabled");
                }
            })
        }
        chexian.each(function(){
            var aa = $(this).nextAll().children("select").val();
            $(this).on("change",function () {
                var checked = $(this).is(':checked');
                var selects = $(this).nextAll().children("select").val();
                var ss = selects;
                if (checked != true) {
                    $(this).nextAll().children("input[type='radio']").attr("checked",false);
                    $(this).nextAll().children("input[type='radio']").attr("disabled","disabled");
                    $(this).nextAll().children("select").val("").attr("disabled","disabled");
                    $(this).nextAll().children("div").children("input[type='text']").attr("disabled","disabled");
                }else{
                    $(this).nextAll().children("input[type='radio']").removeAttr("disabled");
                    if ($(this).nextAll().children(".fou")[0]) {
                        $(this).nextAll().children(".fou")[0].checked = true;
                    }

                    $(this).nextAll().children("select").val(aa).removeAttr("disabled");
                    $(this).nextAll().children("div").children("input[type='text']").removeAttr("disabled");
                }
            })
        })
        //是否选中来判断select框是否可用
        var radios = $(".list .two ul li input[type='radio']");
        radios.each(function(){
            var v = $(this).nextAll("select").val();
            $(this).on("change",function(){
                var vv = $(this).val();
                if (vv == "是") {
                    $(this).nextAll("select").val(v).removeAttr("disabled");
                    $(this).nextAll("div").children("input[type='text']").removeAttr("disabled");
                } else{
                    $(this).nextAll("select").val("").attr("disabled","disabled");
                    $(this).nextAll("div").children("input[type='text']").attr("disabled","disabled");
                }
            })
        })

        vall()
    }
    //保险公司页面的ajax
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "baoxianGongSi"
        },
        success:function(jsondata){
            var json = JSON.parse(jsondata);
            add_company(json);
            company_css();
            //console.log(json);
        },
        error:function(eee){
            // alert("失败")
        }
    });
    //保险险种部分的ajax
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "baoXianTypes"
        },
        success:function(jsondata){
            var json = JSON.parse(jsondata);
            add_insurance (json);
            panduan();
            //console.log(json);
        },
        error:function(eee){
            // alert("失败")
        }
    });
    //证件信息部分
    //唯一标识符uuid
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
    //请求JSON
    // function requestJosn(appServer) {
    //     $.ajax({
    //         type    : 'GET',
    //         url     : 'http://asl.dev.duduchewang.cn/oss/ossconfig/cs00001/18',
    //         data    : {
    //             fromflag   : "baoXianTypes"
    //         },
    //         success:function(jsondata){
    //             // var json = JSON.parse(jsondata);
    //             Duducreds=jsondata;
    //             console.log(jsondata);
    //         },
    //         error:function(){
    //
    //         }
    //     });
    // }

    //判断文件类型  照片大小
    function getImageSize(obj){
        //图片预览
        //setImagePreview();
        if(obj.value==null || obj.value=='' || obj.value==undefined){
            alert("请选择对应的图片！");
            return false;
        }
        photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
        var tp =".jpg,.gif,.bmp,.JPG,.GIF,.BMP,.ico,.png";
        var rs=tp.indexOf(photoExt);
        //如果返回的结果大于或等于0，说明包含允许上传的文件类型
        if(rs>=0){
            //return true;
        }else{
            alert("您选择的上传文件不是有效的图片文件！");
            return false;
        }
        var fileSize = 0;
        var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
        if (isIE && !obj.files) {
            var filePath = obj.value;
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile (filePath);
            fileSize = file.Size;
        }else {
            fileSize = obj.files[0].size;
        }
        fileSize=Math.round(fileSize/1024*100)/100; //单位为KB
        if(fileSize>=5000){
    //  if(fileSize>=10){
            alert("照片最大尺寸为5000KB，请重新上传!");
            return false;
        }
        return true;
    }
    //oss上传调用
    //obj=document.getElementById('file').files[0];
    function uplodlm(DuduOssCallbackVarData1) {
        // new applyTokenDoNew(obj,DuduOssCallbackVarData1);
        applyTokenDo(DuduOssCallbackVarData1);
    }

    //上传图片 待优化
    // appServer = "http://asl.dev.duduchewang.cn/oss/ossconfig/cs00001/18";
    //获得请求的json业务 待优化





    //点击图片上传并预览
    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file)
        } else if (window.URL != undefined) {
            url = window.URL.createObjectURL(file)
        } else if (window.webkitURL != undefined) {
            url = window.webkitURL.createObjectURL(file)
        }
        return url
    };
   // appServer = "http://asl.dev.duduchewang.cn/oss/ossconfig/cs00001/18";

    $(".filepath").on("change",function() {
        var photoExt=this.value.substr(this.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
        var tp =".jpg,.gif,.bmp,.JPG,.GIF,.BMP,.ico,.png";
        var rs=tp.indexOf(photoExt);
        var onself = this;
        if(this.value == ""){
           return false
        }else if(rs < 0){            //如果返回的结果大于或等于0，说明包含允许上传的文件类型
            alert("您选择的上传文件不是有效的图片文件！");
            return false;
        }else{
            var shopcode=mineShopCode;
            var i = $(this).index()+1;
            var srcd =this.files[0];
            var projectId = uuid(16,16);
            var DuduOssCallbackVarData1 = {
                "shopCode" :mineShopCode,
                "orderCode" : projectId,
                "imageType" : ""+i+""
            }
            console.log(this.files[0]);
            $.ajax({
                type    : 'GET',
                url     : '/ossconfig/'+mineShopCode+'/18',
                data    : {},
                success:function(jsondata){
                    var json = JSON.parse(jsondata);
                    Duducreds=json;
                    // console.log(json)
                    new applyTokenDoNew(srcd,DuduOssCallbackVarData1);
                },
                error:function(data){

                }

            });
            // console.log(srcd)
            //setTimeout(function() { new applyTokenDoNew(srcd,DuduOssCallbackVarData1);},2000);
            $(this).nextAll(".img1").hide();   //this指的是input
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
                    $(onself).nextAll(".tupian").val(srcs);
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

    //提交按钮前的判断
    $(".tijiao").on("click",function(){
        var abc = $("form").serialize();
        console.log(abc);
        //获取车辆信息页中的信息
        var car_number = $("#car_number").val();
        var your_name = $("#your_name").val();
        var phone_number = $("#phone_number").val();
        // var daihao = $("#daihao").val();            //-------------车辆识别代号
        // var engine_number = $("#engine_number").val();   //-------------------发动机号
        // var registration_date = $("#registration_date").val(); //----------------------注册日期
        //获取保险信息页中的信息
        var xianzhong = $('.list .two .insurance_company .baoxian input[name="xianzhong"]').is(":checked");
        var chexian = $('.list .two input[name="chexian"]').is(":checked");

        //获取证件信息页中的信息
        var input_file_driving = $(".three ul li .picture .xing_shi").eq(0).attr("src");    //行驶证图片
        var input_file_driving_1 = $(".three ul li .picture .xing_shi").eq(1).attr("src");  //行驶证图片
        var input_file_filepath = $(".three ul li .picture .shen_fen").eq(0).attr("src");  //身份证图片
        var input_file_filepath_1 = $(".three ul li .picture .shen_fen").eq(1).attr("src");  //身份证图片
//  	console.log(input_file_driving);
//  	console.log(input_file_driving_1);
//  	console.log(input_file_filepath);
        //判断是否为空
        if (car_number != "") {
        	if (your_name != "") {
        		if (phone_number != "") {
                    if (xianzhong == true) {
                        if (chexian == true) {
                            if (input_file_driving != "" && input_file_driving_1 != "") {
                                if (input_file_filepath != "" && input_file_filepath_1 != "") {
                                    // $("form").submit();
                                    var abc = $("form").serialize();
                                    $.ajax({
                                        type:'POST',
                                            url:"/baoxiantijiao",
                                            data:abc,
                                            success:function (data) {
                                                var json = JSON.parse(data);
                                                if(json == '"1"'){
                                                    if(mineShopCode != "null" ){
                                                        window.location.href = "/appbaoxianlist?mineShopCode="+mineShopCode; //app页面跳转
                                                    }else{
                                                        window.location.href = "/queryBaoXian";//微信页面
                                                    }

                                                }else{
                                                    alert("提交失败，请重新提交！")
                                                }
                                            },
                                            error:function(data){
                                                alert(data)
                                            }
                                    })
                                } else{
                                    alert("请上传身份证图片");
                                    return false;
                                }
                            } else{
                                alert("请上传行驶证照片");
                                return false;
                            }
                        } else{
                            alert("请选险种");
                            return false;
                        }
                    } else{
                        alert("请选择保险公司");
                        return false;
                    }
        		} else{
        			alert("请填写您的手机号码");
        			return false;
        		}
        	} else{
        		alert("请填写您的姓名");
        		return false;
        	}
        } else{
        	alert("请填写车牌号码");
        	return false;
        }
    })
});