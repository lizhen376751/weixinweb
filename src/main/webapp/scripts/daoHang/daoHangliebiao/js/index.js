
$(document).ready(function () {

    var h2 = $("h2");
    var txt = $(".txt");
    var shopcode = encodeURIComponent($("#shopcode").val());
    var contextPathStr = $("#contextPathStr").val();
    //
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "queryShopCodeListByLMCode",
            shopcode   : shopcode
        },
        success : function(jsonData){
            var json = JSON.parse(jsonData);
//			appenging(json)
            h2.text(json.intro);
            txt.append(json.content);
        }
    });
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "queryShopCodeListByLMCode",
            shopcode   : shopcode
        },
        success : function(jsonData){
            var json = JSON.parse(jsonData);
//			appenging(json)
            h2.text(json.intro);
            txt.append(json.content);
        }
    });
//	appenging()
})
	
	function Togglezs(obj){
		
		var dis=$(obj).parent().next().css('display');
		if(dis=='none'){
			$(obj).html("收起&nbsp;<img src='../img/ss.png' />");
			
		}else{
            $(obj).html("展开&nbsp;<img src='../img/zk.png' />")
		}
		$(obj).parent().next().slideToggle("slow");
	};
	
//	指示盘插件函数

   function radialIndicatorfun(){
// 	    radialIndicator.defaults.radius = 300; //指示器内部圆的半径
// 	    radialIndicator.defaults.initValue = 80; //指示器赤初始值
//		radialIndicator.defaults.barWidth = 5; // 指示器刻度条的宽度
//		radialIndicator.defaults.barBgColor = "#5ab5ff"; //指示器剩余刻度条的背景颜色
//		radialIndicator.defaults.barColor = "#ffffff"; //指示渊声巷背景颜色
//		radialIndicator.defaults.roundCorner = false; //边缘圆角
//		
//		var radialObj = radialIndicator("#radialIndicatorContext");
//		var curProgress = radialObj.value(); // 获取刻度值
//		radialObj.value(90); // 设置刻度值
		$("#radialIndicatorContext").radialIndicator({
			
			barWidth : 10,
			roundCorner: true,
			percentage: false ,   //指示器不显示百分号
			radius: 300,    //指示器内部圆的半径
			initValue: 80,	//指示器赤初始值
			barBgColor : "#5ab5ff",
			fontFamily: '微软雅黑',
			barColor: "#ffffff" //指示器剩余刻度条的背景颜色
			
	    })
   }
   
//   var flag=true;
// 	function countClickedTimes(obj){  
// 		if(flag==true){
// 			$(obj).next().css("display","");
// 			flag=false;
// 		}else{
// 			$(obj).next().css("display","none");
// 			flag=true;
// 		}
//	    
//	    
//	   }


// 	服务导航选择项的控制
  function countClickedTimes(obj){
	 $(obj).next().toggleClass("server_cenulimg");
	 }