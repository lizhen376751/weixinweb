$(document).ready(function () {
	 
//	function appenging (arr) {
//		
//		var html = "<h1 align='center'>"+arr.intro+"</h1>"+"<div>"+arr.content+"</div>";
//		 $("body").append(html);	
//		 
//	}
	var h2 = $("h2");
	var txt = $(".txt");
	var contextPathStr = $("#contextPathStr").val();

	$.ajax({
		type    : 'POST',
		url     : '/getCommonAjax',
		data    : {
			fromflag   : "getIntroduced"
		},
		success : function(jsonData){
			var json = JSON.parse(jsonData);
//			appenging(json)
			if(json !=null){
                h2.text(json.intro);
                txt.append(json.content);
			}else{
                txt.append("当前无内容!");
			}

		}
	});
//	appenging()
})