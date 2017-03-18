$(document).ready(function () {
	
	
 function add_label (arr) {
	
		$(".detail_1").append("<div class='top_1 font_2'>"+
						"<div class='left_11'>"+arr.time+"</div>"+													
						"<div class='right_11'>公里数："+arr.gongli+"</div>"+
							
					"</div>"+
					"<div class='middle_1 font_1'>"+
						"<div class='left_2' id='xm'>"+
						  "<span style='margin-left: 26px;'>施工店铺：</span>"+
						"</div>"+
						"<div style='float: left;margin-top: 29px;'>"+
							"<span style='margin-left: 26px;'>施工店铺：</span>"+
							"<span>"+arr.address+"</span>"+
						"</div>"+
					"</div>"+
                    "<div class='bottom_2 font_1'>"+						
						"<div>"+
							"<span style='margin-left: 26px;'>施工技师：</span>"+
							"<span>"+arr.name+"</span>"+
						"</div>"+
						"<div>"+
							"<span style='margin-left: 26px;'>付款方式：</span>"+
							"<span class='red'>"+arr.fukuang+"</span>"+
							"<span style='margin-left: 60px;'>单据应收：</span>"+
							"<span class='red'>￥"+arr.money+"</span>"+
						"</div>"+
						
					"</div>"
		); 
		 
		 for(var a=0;a<arr.xingm.length;a++){
		 	$("#xm").append("<spanclass='right_2'>"+
							  "<span><span class='bnum'>"+(a+1)+"</span><span>"+arr.xingm[a]+"</span></span>"+							
						    "</span>"
		      )
		 }
		
	}
	
	var arr = {

	             time: "2017-2-25",
	           gongli: "2638km",
	            xingm:["洗车","打蜡","美容"],
	          address: "北京经典汽车服务有限公司",
	             name: "荆兵",
	          fukuang: "现金（￥140.00）",
	            money:"140.00"

	}
	add_label(arr);
	
	//提交函数
    $("#sub").click(function(){
    	var img_len=$(".xj ").find("img").length;
    	var j=0
    	for(var i=0;i<img_len;i++){   		
    		if($(".xj ").find("img").eq(i).attr("src").indexOf("daohang_16.png")>-1){
		        j++;
		    }
    	}
    	
    	if(j==0){
    		alert("您还没有评分，请选择评分")
    	}else{
    		//提交表单
    	}
    })
	//控制星级评价
	var stars = $(".xj li img");
	stars.on("click",click)
	function click () {
		for (var i = 0;i < stars.length;i++) {
			stars[i].index = i;
			stars[i].src = "../img/daohang_18.png"
		}
		var num = this.index;
		for (var i = 0;i <= num;i++) {
			stars[i].src = "../img/daohang_16.png";
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
    
    $(".filepath").on("change",function() {
        var srcs = getObjectURL(this.files[0]);   //获取路径
        $(this).nextAll(".img2").eq(0).src = srcs;
        $(this).nextAll(".img1").hide();   //this指的是input
        $(this).nextAll("p").hide();
        $(this).nextAll(".img2").show();  //fireBUg查看第二次换图片不起做用
        $(this).nextAll('.close').show();   //this指的是input
        $(this).nextAll(".img2").attr("src",srcs);    //this指的是input
        $(this).val('');    //必须制空
//      $(".close").on("click",function() {
//          $(this).hide();     //this指的是span
//          $(this).nextAll(".img2").hide();
//          $(this).nextAll(".img1").show();
//      })
    });
    
    
   
	
	
});
