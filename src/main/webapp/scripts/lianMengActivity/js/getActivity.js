$(document).ready(function () {
	//ªÒ»°ids
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
		url     : contextPathStr+'/servlet/getCommonAjax', 
		data    : {
			fromflag   : "getLianMengActivity",
			ids   : ids
		},
		success : function(jsonData){
			var json = JSON.parse(jsonData);
			h2.text(json.data.title);
			txt.append(json.data.details)
		}
	});
//	appenging()
})