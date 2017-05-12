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
    var itemCode = $("#itemCode").val();
    var shopCodeLm = $("#shopCodeLm").val();

    $.ajax({
        type: 'POST',
        url: '/shopAjax',
        data: {
            businessType: "biaozhunliucheng",
            itemCode: itemCode,
            shopCodeLm: shopCodeLm
        },
        success: function (jsonData) {
            var arr = JSON.parse(jsonData);
            $(".xm_name").text(arr[0].itemName)
            console.log(arr);
            add_step(arr)
            }

        });
	//-------------------------------------------------动态添加每一条步骤
	function add_step(arr){
		for(var i = 0;i < arr.length;i++){
			var html = '';
			if(arr[i].imageUrl != null && arr[i].imageUrl != "" && arr[i].imageUrl != undefined){
                var srcs = "<img src='"+arr[i].imageUrl+"' class='image'/>"
            }else {
                var srcs = "";
            }
            if(arr[i].notice != null && arr[i].notice != "" && arr[i].notice != undefined){
                var notice = "<p class='font_4' id='step_note'>注："+arr[i].notice+"</p>"
            }else{
                var notice = "";
            }
			html += "<li>"+
						"<div class='step_left'>"+
							"<div class='img'>"+ srcs +
							 "</div>"+
							"<span class='step_num font_4 color_3'>"+arr[i].stepNumber+"</span>"+
						 "</div>"+
						 "<div class='step_text'>"+
							"<p class='step_title font_1 step_color2'>"+arr[i].stepDescription+"</p>"+
							"<p class='step_detail font_4'>"+arr[i].des+"</p>"+ notice +
						"</div>"+
					"</li>";
			$("ul").append(html);
		}
	}
})
