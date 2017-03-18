


$(function () {
/*	
//	--------------预约时间段json：
var date1=[
    {
        yuYueTime: "07:00-07:59"
    },
    {
        yuYueTime: "08:00-09:59"
    },
    {
        yuYueTime: "10:00-14:59"
    },
    {
        yuYueTime: "15:00-18:59"
    }
]
//-------------预约项目json
var date2=[
    {
        itemId: '1',
        itemName: '洗车'
    },
    {
        itemId: '2',
        itemName: '打蜡'
    },
    {
        itemId: '3',
        itemName: '保养'
    }
]

//	初始化时间
for(var i=0;i<date1.length;i++){
 	$("#advance_time").append("<option value='"+date1[i].yuYueTime+"'>"+date1[i].yuYueTime+"</option>");
};
//	初始化项目
for(var j=0;j<date2.length;j++){
 	$("#xmselect").append("<option value='"+date2[j].itemId+"'>"+date2[j].itemName+"</option>");
};
*/


var shopcode=$("#shopcode").val();//联盟编码
var shopcode2=$("#shopcode2").val();//店铺编码
var contextPathStr = $("#contextPathStr").val();

//获取时间段
$.ajax({
	type:"POST",
	url:contextPathStr+'/servlet/getCommonAjax',
	data:{
		fromflag   : "queryReserveTimeConfig",
		shopcode   : shopcode2
	},
	success : function(jsonData){
		var json = JSON.parse(jsonData);
		for(var i=0;i<json.data.length;i++){
	     	$("#advance_time").append("<option value='"+json.data[i].timeConfig+"'>"+json.data[i].timeConfig+"</option>");
		};
	}
});

//获取项目列表
$.ajax({
	type:"POST",
	url:contextPathStr+'/servlet/getCommonAjax',
	data:{
		fromflag   : "queryReserveItemList",
		shopcode   : shopcode2,
		shopcode_lm: shopcode
	},
	success : function(jsonData){
		var json = JSON.parse(jsonData);
		
		for(var j=0;j<json.data.length;j++){
		 	$("#xmselect").append("<option value='"+json.data[j].itemId+"'>"+json.data[j].itemName+"</option>");
		};
	}
});


	
	
    
    

	
	//	初始化当前日期
  	function show(){
      var mydate = new Date();
      var str = "" + mydate.getFullYear() + "-";
      str += (mydate.getMonth()+1) + "-";
      str += mydate.getDate() ;
      return str;
    }
    $("#appDate").val(show());


    //	日期插件
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
		height:80,//每一栏的高度
        width:300,//每一栏的宽度
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
        startYear: currYear - 10, //开始年份
        endYear: currYear + 10 //结束年份
	};

  	$("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
    
    
    //提交函数
	$("#sub").click(function(){  
	    	var dateval=$("#appDate").val(); //日期
	    	var advance_time=$("#advance_time").val(); //时间段
	    	var selval= $("#xmselect option:selected").html();//项目名称
	    	var xmId=$("#xmselect").val(); //时间段
	    	var notes=$("#notes").val(); //备注
	    	var carHaoPai=$("#CarId").val(); //备注
	    	
	    	if(dateval==""||dateval==null){
	    		alert("请选择时间")
	    	}else if(selval==""||selval==null){
	    		alert("请选项目")
	    	}else{
	    		//提交预约
	    		$.ajax({
	    			type:"POST",
	    			url:contextPathStr+'/servlet/getCommonAjax',
	    			data:{
	    				fromflag   : "addReserve",
	    				shopcode   : shopcode2,			//操作店铺编码
	    				reserveShopCode : shopcode2,	//预约店铺编码
	    				reserveDate: dateval,			//预约日期
	    				reserveTime: advance_time,		//预约时间段
	    				reserveNote: encodeURIComponent(notes),				//预约备注
	    				itemId	   : xmId,				//预约项目id
	    				carHaoPai  : encodeURIComponent(carHaoPai)			//车牌号码
	    			},
	    			success : function(jsonData){
	    				var json = JSON.parse(jsonData);
	    				if(json.id *1 >0){
	    					alert("预约成功！");
	    					window.close();
	    				}else{
	    					alert("您不是该店铺的客户，不能预约，请到店或电话联系店铺！");
	    					window.close();
	    				}
	    			}
	    		});
	    	}
	})
});
        

