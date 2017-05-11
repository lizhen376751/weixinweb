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
            console.log(arr);

            }

        });
	//-------------------------------------------------动态添加每一条步骤
	function add_step(arr){
		for(var i = 0;i < arr.length;i++){
			var html = '';
			html += "<li>"+
						"<div class='step_left'>"+
							"<div class='img'>"+
								"<img src='../img/stepPhoto/300414.jpg' class='image'/>"+
							 "</div>"+
							"<span class='step_num font_4 color_3'>1</span>"+
						 "</div>"+
						 "<div class='step_text'>"+
							"<p class='step_title font_1 step_color2'>启动发动机</p>"+
							"<p class='step_detail font_4'>确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间，手刹拉近确认变速箱档位在V/P之间</p>"+
							"<p class='font_4' id='step_note'>注：其他车门玻璃也需要检查也玻璃也需要检查也玻璃也需要检查也玻璃也需要检查也</p>"+
						"</div>"+
					"</li>";
			$("ul").append(html);
		}
	}
})
