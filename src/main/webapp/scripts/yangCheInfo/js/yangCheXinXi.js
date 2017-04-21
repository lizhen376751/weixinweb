
$(window).load(function(){
    setTimeout(function () {
        $("#loading").hide();
        // $(".center_zhis ").show();
        // $(".conter_main").show()
    }, 1000);
});


$(document).ready(function () {
	var abc = [];
	function appenging (arr) {
		var html = "";
		for (var i = 0; i < arr.length;i++) {
			 html += "<div class='new'>"+
			 "<div class='new_box'>"+
					"<div class='new_left'>"+
						"<div class='img_1'>"+
							"<img src='"+arr[i].imgURL+"'/>"+
						"</div><div class='img_2'>"+
							"<img src='"+arr[i].imgURL+"'/>"+
						"</div>"+
						"<div class='txt font_4'>"+arr[i].createDate+
						"</div>"+
					"</div>"+
					"<div class='new_right'>"+
						"<h2>"+arr[i].title+"</h2>"+
						"<p>"+arr[i].info+"</p>"+
					"</div>"+
				"</div>"+
			"</div>";
			 abc.push(arr[i].id);
		}
		 $("body").append(html);	
	}
	
	function tiaoZhuan () {
		var new_box = $(".new_box");
		for (var i = 0;i < new_box.length;i++) {
			new_box[i].i = i;
			new_box[i].onclick = function () {
				window.location.href="/oauthLoginServlet?flagStr=getYangChe&ids="+abc[this.i];
			}
		}
	}
//	function img_s () {
//		var img = $("img");
//		for (var i = 0;i < img.length;i++) {
//			if (img[i].attributes[0].value == ""||img[i].attributes[0].value==undefined||img[i].attributes[0].value==null) {
//				img[i].src = "../img/weijiazai.png";
//			}
//		}
//	}
	var shopcode = encodeURIComponent($("#shopcode").val());
	var contextPathStr = $("#contextPathStr").val();
	
	$.ajax({ 
		type    : 'POST',
		url     : '/getCommonAjax',
		data    : {
			fromflag   : "queryYangCheInfo"
		},
		success : function(jsonData){
			var json = JSON.parse(jsonData);
			var card_name = $(".card_name");
				if (json.length <= 0) {
				card_name.text("当前无养车信息!");
			} else{	
				appenging(json);
				tiaoZhuan();
			}
		}
	});
	//alert(json.data[0].imgURL);
//	appenging()
})
