$(document).ready(function () {
	//��ȡids
	function getvl(name) {
        var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
        if(reg.test(location.href)) return decodeURI(RegExp.$2.replace(/\+/g, " "));
        return "";
		};
	var ids = getvl("ids")	
	var h2 = $("h2");
	var txt = $(".txt");
	var shopcode = encodeURIComponent($("#shopcode").val());
	var contextPathStr = $("#contextPathStr").val();
	
	$.ajax({ 
		type    : 'POST',
		url     : '/getCommonAjax',
		data    : {
			fromflag   : "getLianMengActivity",
			ids   : ids
		},
		success : function(jsonData){
			var json = JSON.parse(jsonData);
			h2.text(json.title);
			txt.append(json.details)
		}
	});
//	appenging()
})