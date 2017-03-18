$(document).ready(function(){
    var v = 1;
	$('.titles span').click(function() {
        var i = $(this).index();//下标第一种写法
        //var i = $('tit').index(this);//下标第二种写法
        $(this).addClass('selects').siblings().removeClass('selects');
        $('.list .one,.two,.three').eq(i).show().siblings().hide();
        //保险险种页面的ajax

        if (i == 1 && v == 1) {
        	$.ajax({
                type    : 'POST',
                url     : '/getCommonAjax',
                data    : {
                    fromflag   : "baoXianTypes"

                },

        		success:function(jsondata){
                	var json = JSON.parse(jsondata);
                    add_insurance (json);
                    panduan()
                    console.log(json);
                    v=0;
        		},
        		error:function(){
        			
        		}
        	});
            $.ajax({
                type    : 'POST',
                url     : '/getCommonAjax',
                data    : {
                    fromflag   : "baoxianGongSi"

                },

                success:function(jsondata){
                    // alert(jsondata);
                    var json = JSON.parse(jsondata);
                    add_company(json)
                    console.log(json);
                },
                error:function(){

                }
            });
        }
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
    		html += '<option value="'+arr[i]+'">'+arr[i]+'</option>';
    	}
    	big_lable.append(html);	
    }
    
    
 	//根据车牌号码显示信息
 	var car_number = $("#car_number");
    car_number.on("keyup",function(){
		if($(this).val().length >= 3){
			alert(1111)
		}
    })

   
                                                                              //保险险种部分
    //添加保险公司
//  var ab = [1,2,1,2,2]
    function add_company (arr) {
    	var baoxian_company = $(".list .two .insurance_company .baoxian")
    	var html = "";
    	for (var i = 0; i < arr.length;i++) {
    		html += "<div><input type='checkbox' name='xianzhong' id='wang_wang' value='' /><span>"+arr[i].insuranceName+"</span></div>"
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
    company_css()                                                                         
                                                                              
                                                                              
     //布局每一条险种
     //模拟数据
     var arr = [{
     	id:0,
     	buJiMianPeiFlag:0,
     	baozhangrenshuFlag:0,
     	peichangeduFlag:0,
     	typeName:"投保交强险",
     	typeFlag:1
     },{
     	id:1,
     	buJiMianPeiFlag:1,
     	baozhangrenshuFlag:0,
     	peichangeduFlag:0,
     	typeName:"车辆损失险测试",
     	typeFlag:1
     },{
     	id:2,
     	buJiMianPeiFlag:1,
     	baozhangrenshuFlag:0,
     	peichangeduFlag:0,
     	typeName:"车辆损失险测试",
     	typeFlag:1
     },{
     	id:3,
     	buJiMianPeiFlag:1,
     	baozhangrenshuFlag:0,
     	peichangeduFlag:0,
     	typeName:"车辆损失险测试",
     	typeFlag:0
     },{buJiMianPeiFlag:1,
		dictionary:[{baoxianid:2,id:1,value:"5万",dictionaryDef:1},
	  				{baoxianid:2,id:2,value:"10万",dictionaryDef:0},
	  				{baoxianid:2,id:3,value:"15万",dictionaryDef:0},
	  				{baoxianid:2,id:4,value:"20万",dictionaryDef:0},
	  				{baoxianid:2,id:5,value:"30万",dictionaryDef:0},
	  				{baoxianid:2,id:6,value:"50万",dictionaryDef:0},
	  				{baoxianid:2,id:7,value:"100万",dictionaryDef:0},
	  				{baoxianid:2,id:8,value:"150万",dictionaryDef:0},
	  				{baoxianid:2,id:9,value:"200万",dictionaryDef:0},
	  				{baoxianid:2,id:10,value:"250万",dictionaryDef:0},
	  				{baoxianid:2,id:11,value:"300万",dictionaryDef:0}
				],
		baozhangrenshuFlag:0,
		peichangeduFlag:1,
		typeName:"第三者责任险00",
		id:4,
		typeFlag:1
	},{buJiMianPeiFlag:1,
		dictionary:[{baoxianid:2,id:1,value:"5万",dictionaryDef:1},
	  				{baoxianid:2,id:2,value:"10万",dictionaryDef:0},
	  				{baoxianid:2,id:3,value:"15万",dictionaryDef:0},
	  				{baoxianid:2,id:4,value:"20万",dictionaryDef:0},
	  				{baoxianid:2,id:5,value:"30万",dictionaryDef:0},
	  				{baoxianid:2,id:6,value:"50万",dictionaryDef:0},
	  				{baoxianid:2,id:7,value:"100万",dictionaryDef:0},
	  				{baoxianid:2,id:8,value:"150万",dictionaryDef:0},
	  				{baoxianid:2,id:9,value:"200万",dictionaryDef:0},
	  				{baoxianid:2,id:10,value:"250万",dictionaryDef:0},
	  				{baoxianid:2,id:11,value:"300万",dictionaryDef:0}
				],
		baozhangrenshuFlag:1,
		baozhangrenshu:4,
		peichangeduFlag:1,
		typeName:"第三者责任险01",
		id:5,
		typeFlag:1
	}];
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
     				typeFlag += "<input type='checkbox' name='chexian' id='' value='' checked='checked'/>"+
			     				"<span class='font_1 color_4'>"+arr[i].typeName+"</span>"+
			     				"<span class='font_5 color_3'>（必买险种）</span>"
     			} else{
     				typeFlag += "<input type='checkbox' name='chexian' id='' value='' checked='checked' class='chexian'/><span class='font_1 color_4'>"+arr[i].typeName+"</span>";
     			}
     			
     			//不计免赔拼接
	     		if (arr[i].buJiMianPeiFlag == 1) {
	     			buJiMianPeiFlag +=   "<label for=''>不计免赔：</label>"+
		       	 						"<input type='radio' name='"+arr[i].id+"' value='是'class='fou' checked='checked'/>是"+
		       	 						"<input type='radio' name='"+arr[i].id+"' value='否' />否"
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].value+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].value+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' style='margin-left: 84px;'>赔偿限额：</label>"+
                            "<select name='"+arr[i].id+"'>"+options+"</select>"
                    }
	     		}else{
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].value+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].value+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' >赔偿限额：</label>"+
                            "<select name='"+arr[i].id+"'>"+options+"</select>"
                    }
				}
     		}else{
     			typeFlag += "<input type='checkbox' name='chexian' id='' value='' class='chexian'/><span class='font_1 color_4'>"+arr[i].typeName+"</span>";
     			//不计免赔拼接
	     		if (arr[i].buJiMianPeiFlag == 1) {
	     			buJiMianPeiFlag +=   "<label for=''>不计免赔：</label>"+
		       	 						"<input type='radio' name='"+arr[i].id+"' value='是'class='fou'/>是"+
		       	 						"<input type='radio' name='"+arr[i].id+"' value='否' />否"
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].value+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].value+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' style='margin-left: 84px;'>赔偿限额：</label>"+
                            "<select name='"+arr[i].id+"'>"+options+"</select>"
                    }
	     		}else{
                    //赔偿额度拼接
                    if (arr[i].peichangeduFlag == 1) {
                        var options = "";
                        for (var j = 0;j < arr[i].dictionary.length;j++) {
                            if (arr[i].dictionary[j].dictionaryDef == 1) {
                                options += "<option value='"+arr[i].dictionary[j].value+"' selected='selected'>"+arr[i].dictionary[j].value+"</option>"
                            }else{
                                options += "<option value='"+arr[i].dictionary[j].value+"'>"+arr[i].dictionary[j].value+"</option>"
                            }

                        }
                        peichangeduFlag += "<label for='' >赔偿限额：</label>"+
                            "<select name='"+arr[i].id+"'>"+options+"</select>"
                    }
				}
     		}
     		
     		

     		
     		//保障人数的拼接
     		if (arr[i].baozhangrenshuFlag == 1) {
     			baozhang_num += "<div>"+
	       	 						"<label for='' class='people_number'>保障人数：</label>"+
	       	 						"<input type='text' name='passenger_number' value='"+arr[i].baozhangrenshu+"'/>"+
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
     // add_insurance(arr)
     
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

    
    
    
 	                                                                          //证件信息部分
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
    
    $(".filepath").on("change",function() {
        var srcs = getObjectURL(this.files[0]);   //获取路径
        $(this).nextAll(".imgs").children(".img2")[0].src = srcs;
        $(this).nextAll(".img1").hide();   //this指的是input
        $(this).nextAll("p").hide();
        $(this).nextAll(".imgs").show();  //fireBUg查看第二次换图片不起做用
        $(this).nextAll('.close').show();   //this指的是input
        $(this).nextAll(".imgs").children(".img2").attr("src",srcs);    //this指的是input
        $(this).val('');    //必须制空
//      $(".close").on("click",function() {
//          $(this).hide();     //this指的是span
//          $(this).nextAll(".img2").hide();
//          $(this).nextAll(".img1").show();
//      })
    })
    
    //提交按钮前的判断
    $(".tijiao").on("click",function(){
    	//获取车辆信息页中的信息
    	var car_number = $("#car_number").val();
    	var your_name = $("#your_name").val();
    	var phone_number = $("#phone_number").val();
    	var daihao = $("#daihao").val();
    	var engine_number = $("#engine_number").val();
    	var registration_date = $("#registration_date").val();
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


    	if (car_number != "") {
    		if (your_name != "") {
    			if (phone_number != "") {
    				if (daihao != "") {
    					if (engine_number != "") {
    						if (registration_date != "") {
    							if (xianzhong == true) {
						    		if (chexian == true) {
						    			if (input_file_driving != "" && input_file_driving_1 != "") {
											if (input_file_filepath != "" && input_file_filepath_1 != "") {

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
    							alert("请输入注册日期");
    							return false;
    						}
    					} else{
    						alert("请输入发动机号码");
    						return false;
    					}
    				} else{
    					alert("请填写车辆识别代号");
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
    
   
    
    
    
    
    
    
    
    
})