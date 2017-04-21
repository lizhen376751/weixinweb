


function Togglezs(obj){

    var dis=$(obj).next().css('display');
    if(dis=='none'){
        $(obj).find(".submiaos").html("收起<img class='zk_img' src='/files/ahi/ss.png' />");

    }else{
        $(obj).find(".submiaos").html("展开<img class='zk_img' src='/files/ahi/zk.png' />")
    }
    $(obj).next().slideToggle("slow");
};



// 	服务导航选择项的控制
function countClickedTimes(obj){
    $(obj).next().toggleClass("server_cenulimg");
}


//点击图片放大按钮
function fangda(img_src){
    $(".zhezhao").css("display","block");
    $(".goback").hide();

    $("body").css("overflow","hidden");

    $(".zsimg").attr("src",img_src)


}

$(function(){
    //图片弹出层
    $(window).scroll(function(){
        $('.zhezhao').css('top', $(document).scrollTop());
    });
    $(".zsimg ,.zhezhao").click(function(){
        // $(".zhezhao").siblings().css("display","block");
        $(".zhezhao").css("display","none");
        $(".goback").show();
        $("body").css("overflow","auto");
    });
});



// 	服务导航选择项的控制
  function countClickedTimes(obj){
	 $(obj).next().toggleClass("server_cenulimg");
	 }