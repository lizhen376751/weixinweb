(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize =Math.floor(100*(clientWidth / 1080))+ 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document,window);
$(function(){
    var plateNumber = $("#plateNumber").val();//车牌号
    var customId = $("#customId").val();
    var wxpingzheng = $("#wxpingzheng").val();//维修凭证
    var shopCode = $("#shopCode").val();//店铺编码
    var project = $(".project");//获取评价的容器
    //初始加载页面
    $.ajax({
        type    : 'post',
        url     : '/shopAjax',
        data    : {
            businessType : "shopEvaluateParam",
            plateNumber:plateNumber,
            customId:customId,
            wxpingzheng:wxpingzheng,
            shopCode:shopCode
        },
        success:function(jsondata){
            var json = JSON.parse(jsondata);
                //    是否含有消费商品判断
                if(json.commodityMx.length != 0){
                    for(var j = 0;j < json.commodityMx.length;j++){
                        addPJ(json.commodityMx[j].moduleName,json.commodityMx[j].weixiuyuanSp,json.commodityMx[j].spbm,"COMMODITY");
                    }
                }
                //    是否含有消费项目判断
                if(json.projectMx.length != 0){
                    for(var j = 0;j < json.projectMx.length;j++){
                        addPJ(json.projectMx[j].itemName,json.projectMx[j].weixiuyuan,json.projectMx[j].itemcode,"PROJECT");
                    }
                }
                $(".shop_num").attr("OrderType",json.ordertype);//单据类型
                $(".shop_num").attr("CreateDate",json.kprq);//开票日期
            $(".project_num img").on("click",click_flower);
            $(".shop_num img").on("click",click_flower);
            tpsc();

        },
        error:function(data){

        }

    });
    //动态添加每一个评价模块(商品或项目名称，销售或技师ID，商品或项目编码，商品或项目区分)
    function addPJ(name,ServiceStaff,CommodityCode,ComIdentifica) {
        var classes = uuid(4,16);
        var sp = '<div class="box">'+
            '<div class="box_l" ServiceStaff="'+ServiceStaff+'" CommodityCode="'+CommodityCode+'" ComIdentifica="'+ComIdentifica+'">'+
            '<div class="project_title font_3">'+
            '<span class="project_title_txt">'+name+'</span>'+
            '</div>'+
            '<div class="project_num">'+
            '<span class="font_1 color_8">请给出您的评价</span>'+
            '<ul>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="'+classes+'"/></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="'+classes+'"/></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="'+classes+'"/></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="'+classes+'"/></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" class="'+classes+'"/></li>'+
            '</ul>'+
            '<input type="hidden" class="'+classes+'_val" value="0" />'+
            '</div>'+
            '<div class="project_text">'+
            '<textarea class="font_4" rows="4" maxlength="110" placeholder="评论：本店的服务满足您的期待吗？请评价一下我们的优点和不足的地方吧!（满足15个字才是 好同志哦！）"></textarea>'+
            '<ul>'+
            '<li class="margin_r">'+
            '<input type="file" class="project_file" mxid="'+classes+'"/>'+
            '<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>'+
            '<img src="" class="file_img"/>'+
            '<input type="hidden" class="uuid" value="" />'+
            '<input type="hidden" class="mxid" value="1" />'+
            '</li>'+
            '<li class="margin_r hide">'+
            '<input type="file" class="project_file" mxid="'+classes+'"/>'+
            '<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>'+
            '<img src="" class="file_img"/>'+
            '<input type="hidden" class="uuid" value="" />'+
            '<input type="hidden" class="mxid" value="2" />'+
            '</li>'+
            '<li class="hide">'+
            '<input type="file" class="project_file" mxid="'+classes+'"/>'+
            '<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>'+
            '<img src="" class="file_img"/>'+
            '<input type="hidden" class="uuid" value="" />'+
            '<input type="hidden" class="mxid" value="3" />'+
            '</li>'+
            '</ul>'+
            '</div>'+
            '</div>'+
            '</div>';
        project.append(sp)

    }




	var btn = $(".btn");// 获取提交按钮
	// $(".project_num img").on("click",click_flower);
	// $(".shop_num img").on("click",click_flower);
	//点击选花按钮
	function click_flower () {
		var classN = $(this).attr("class");
//		console.log(classN)
		var imgs = $("."+classN);
		for (var i = 0;i < imgs.length;i++) {
			$(imgs[i]).attr("index",i);
			imgs[i].src = "/files/shopconsumerEvaluation//flower_bg.png"
		}
		var num = $(this).attr("index");
//		console.log(num)
		for (var i = 0;i <= num;i++) {
			imgs[i].src = "/files/shopconsumerEvaluation/flower_gb.png";
		}
		switch (num*1+1){
            case 1:$("."+classN+"_val").val("ONE");
                break;
            case 2:$("."+classN+"_val").val("TWO");
                break;
            case 3:$("."+classN+"_val").val("THRER");
                break;
            case 4:$("."+classN+"_val").val("FOUR");
                break;
            case 5:$("."+classN+"_val").val("FIVE");
                break;
        }
	}
	
	
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
	//图片上传
	function tpsc() {
        $(".project_file").on("change",function(){
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
                //预览本地图片
                // var srcs = getObjectURL(this.files[0]);   //获取路径
                // $(this).next(".add_img").hide()
                // $(this).nextAll(".file_img").attr("src",srcs);
                // $(this).nextAll(".uuid").val(uuid(16,16));

                //预览网络图片
                var onself = this;
                var srcd =this.files[0];  //获取本地图片的文件
                var projectId = uuid(16,16);
                var mxId = $(this).nextAll(".mxid").val();
                var DuduOssCallbackVarData1 = {
                    "shopCode" :shopCode,
                    "orderCode" : projectId,
                    "type" : "2",
                    "mxId":mxId
                }
                $.ajax({
                    type    : 'GET',
                    url     : '/ossconfig/'+shopCode+'/22',
                    data    : {},
                    success:function(jsondata){
                        var json = JSON.parse(jsondata);
                        Duducreds=json;
                        new applyTokenDoNew(srcd,DuduOssCallbackVarData1);
                    },
                    error:function(data){

                    }

                });
                $(this).next(".add_img").hide();
                var state = false;
                function a(){
                    // console.log(srcs)
                    if(srcs == "" && state == false){
                        setTimeout(function() { a();},2000);
                    }else{
                        $(onself).nextAll(".file_img").attr("src",srcs);
                        $(onself).nextAll(".uuid").val(projectId);
                        if($(onself).parent().next()){
                            $(onself).parent().next().show();
                        }
                        srcs = "";
                        state = true;
                    }
                }
                a();

            }

        })
    }

	
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
	//---------------------------------------------------------------------------提交评价
	btn.on("click",function(){
        var dp_val = $(".dp_val").val();
		//数据包
		var data = {
            evaluateMain :{
                shopStarlevel:dp_val,//店铺评价星级
                wxPingZheng:wxpingzheng,//维修凭证
                platenumber:plateNumber,//车牌号
                shopCode:shopCode, //店铺编码
                shopIdentification:"SHOPCODE"
            },
            evaluateBill:{
			    OrderType:$(".shop_num").attr("OrderType"),  //单据类型
                CreateDate:$(".shop_num").attr("CreateDate") //开票日期
            },
            evaluateCommodities:[]
		};
		var projects = $(".box_l");
		for(var j = 0; j < projects.length;j++){
			var obj = {};
			//获取每一个商品或项目的销售或技师ID
            var ServiceStaff = $(projects[j]).attr("ServiceStaff");
            obj.serviceStaff = ServiceStaff;
            //获取每一个商品或项目的编码
            var CommodityCode = $(projects[j]).attr("CommodityCode");
            obj.commodityCode = CommodityCode;
            //获取每一个商品或项目的区分
            var ComIdentifica = $(projects[j]).attr("ComIdentifica");
            obj.comIdentifica = ComIdentifica;
			//获取每一个商品或项目的星级
			var project_xj = $(projects[j]).find(".project_num input").val();
			obj.commodityStarlevel = project_xj;
			//获取用户的评价文字
			var project_pj = $(projects[j]).find(".project_text textarea").val();
			obj.commodityContent = project_pj;
			//获取用户上传图片的uuid
			obj.evaluateImgs = [];
			var projrct_uuid = $(projects[j]).find(".project_text li");
			for(var g = 0;g < projrct_uuid.length;g++){
				var uuid_val = $(projrct_uuid[g]).find(".uuid").val();
                var mxid_val = $(projrct_uuid[g]).find(".mxid").val();
                var images = {
                    orderCode:uuid_val,
                    mxId:mxid_val
                };
				obj.evaluateImgs.push(images);
			};
			data.evaluateCommodities.push(obj);
		}
        var img_val = $(".project_num input");
        var arr = [];
        for(var i = 0; i < img_val.length;i++){
            var val = $(img_val[i]).val();
            arr.push(val);
        }
		var dj_val = $.inArray("0",arr);
		//2不能提交；1可以提交
		if(dj_val == "-1" && dp_val != "0"){
		    var json_data = JSON.stringify(data);
            $.ajax({
                type    : 'POST',
                url     : '/shopAjax?businessType=addEvaluate',
                data    : {
                    aa:json_data,
                },
                success:function(jsondata){
                    // var json = JSON.parse(jsondata);

                },
                error:function(data){

                }

            });
			console.log(data)
		}else{
			alert(2)
            console.log(data)
		}
		console.log($.inArray("0",arr))
		
	})
})
