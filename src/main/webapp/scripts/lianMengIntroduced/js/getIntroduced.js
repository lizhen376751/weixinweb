$(document).ready(function () {
	 
//	function appenging (arr) {
//
//		var html = "<h1 align='center'>"+arr.intro+"</h1>"+"<div>"+arr.content+"</div>";
//		 $("body").append(html);
//
//	}
	var h2 = $("h2");
	var txt = $(".txt");
	var shopcode = encodeURIComponent($("#shopcode").val());
	var contextPathStr = $("#contextPathStr").val();
	
	$.ajax({ 
		type    : 'POST',
		url     : '/getCommonAjax',
		data    : {
			fromflag   : "getIntroduced",
			shopcode   :  shopcode
		},
		success : function(jsonData){
			alert(jsonData);
			var json = JSON.parse(jsonData);
			// appenging(json)
			h2.text(json.intro);
			txt.append(json.content);
		}
	});
//	appenging()
})