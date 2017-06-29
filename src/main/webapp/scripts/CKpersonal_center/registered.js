$(function(){
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
	$(".file").on("change",function(){
		var srcs = getObjectURL(this.files[0]);   //获取路径
        $(this).next(".sc").hide()
        $(this).nextAll(".file_img").attr("src",srcs).show();
        $(this).nextAll("p").hide()
	})
})
