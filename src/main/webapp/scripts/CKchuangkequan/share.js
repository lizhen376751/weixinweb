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


//点击图片放大按钮
			 function fangda(img_src){
			  	$(".zhezhao").css("display","block");
			  	$(".goback").hide();
			    $("body").css("overflow","hidden");
			  	
//			  	$("body").bind("touchmove",function(e){ 
//						e.preventDefault(); //遮罩层出现后禁止body滑动
//					});
			  	
			  	  $(".zsimg").attr("src",img_src)
                   var cenheight=($(".zhezhao").height()-$(".zsimg").height())/2;
				   	var uu=Math.floor(cenheight)
				  	if(uu>0){
				  		$(".zsimg").css("margin-top",uu);
				  	} 
			    
			  }
			
			$(function(){
				//图片弹出层
	            $(window).scroll(function(){
			            $('.zhezhao').css('top', $(document).scrollTop());
			      });
				  $(".zsimg ,.zhezhao").click(function(){
				  	   // $(".zhezhao").siblings().css("display","block");
				  	   $(".goback").show();
				        $(".zhezhao").css("display","none");
				        $("body").css("overflow","auto");

				   });
			});
			
			
			
//	动态获取数据部分
$(function(){
//	var data=[
//	        {
//					id:1,
//					name:"李荣浩",
//					Relationnum:[{
//						name:"李荣浩",
//						touxiang:"img/img.png",
//						time:46,
//						type:"洗车",
//						img:[ 
//						      "img/img.png",
//						      "img/img.png",
//						      "img/img.png",
//						      "img/img.png"
//						
//						],
//						zan:222,
//						pinglun:333
//					}]
//					
//					
//				},
//      ]



//  if(data.length>0){
//  	for(var i=0;i<data.length;i++){   		
//  		$(".font_name").html(data[i].name);  //获取在同行圈分享的名字
//  		$("#head_img").attr("src",data[i].Relationnum[0].touxiang); //获取在头像
//  		$("#li_name").html(data[i].Relationnum[0].name); //获取名字
//  		$("#li_time").html(data[i].Relationnum[0].time+"分钟前"); //获取发表时间
//  		$("#li_type").html("&nbsp;&nbsp;"+data[i].Relationnum[0].type); //获取类型
//  		$("#zan").html(data[i].Relationnum[0].zan); //获取点赞数量
//  		$("#ping").html(data[i].Relationnum[0].pinglun); //获取评论条数
//  		
//  		var ii=data[i].Relationnum[0].img;
//  		if(ii.length>0){   			
//  			var html_img="";
//  			for(var j=0;j<ii.length;j++){
//  				
//  				html_img+="<li><img src='"+ii[j]+"' onclick='fangda(this.src)'/></li>"
//  			}
//  			$("#miaoshu").append(html_img);
//
//  			
//  		}
//  	}
//  	 
//  }
	
	
	var qw = $(".qw");//获取圈文按钮
	var wd = $(".wd");//获取我的按钮
	//-----------------------------------------------------------------------------实现滑动效果
	var mySwiper = new Swiper('.swiper-container',{
		pagination:".swiper-pagination",
		paginationClickable:true,
		//控制翻页所调用的函数
        onSlideChangeEnd:function(swiper){
        	switch (swiper.activeIndex){
        		case 0:qw.addClass("border_B");wd.removeClass("border_B");
        			break;
    			case 1:wd.addClass("border_B");qw.removeClass("border_B");
        			break;
        		default:
        			break;
        	}
        	
        }
	})
	//按钮控制显示第几页
	qw.on("click",function(){
		$(".swiper-pagination span").eq(0).trigger("click")
	})
	wd.on("click",function(){
		$(".swiper-pagination span").eq(1).trigger("click")
	})
})
