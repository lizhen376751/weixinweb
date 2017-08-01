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
    var plateNumber = $("#plateNumber").val();//车牌号
    var customId = $("#customId").val();
    var wxpingzheng = $("#wxpingzheng").val();//维修凭证
    var shopCode = $("#shopCode").val();//店铺编码
    var project = $(".project");//获取评价的容器
    //初始加载页面
    $.ajax({
        type    : 'post',
        url     : '/shopAjax',
        data    : {
            businessType : "shopEvaluateParam",
            plateNumber:plateNumber,
            customId:customId,
            wxpingzheng:wxpingzheng,
            shopCode:shopCode
        },
        success:function(jsondata){
            var json = JSON.parse(jsondata);
                //    是否含有消费商品判断
                if(json.commodityMx.length != 0){
                    for(var j = 0;j < json.commodityMx.length;j++){
                        addPJ(json.commodityMx[j].moduleName,json.commodityMx[j].weixiuyuanSp,json.commodityMx[j].spbm,"COMMODITY");
                    }
                }
                //    是否含有消费项目判断
                if(json.projectMx.length != 0){
                    for(var j = 0;j < json.projectMx.length;j++){
                        addPJ(json.projectMx[j].itemName,json.projectMx[j].weixiuyuan,json.projectMx[j].itemcode,"PROJECT");
                    }
                }
                $(".shop_num").attr("OrderType",json.ordertype);//单据类型
                $(".shop_num").attr("CreateDate",json.kprq);//开票日期

        },
        error:function(data){

        }

    });
    //动态添加每一个评价模块(商品或项目名称，销售或技师ID，商品或项目编码，商品或项目区分)
    function addPJ(name,ServiceStaff,CommodityCode,ComIdentifica) {
        var sp = '<div class="box">'+
            '<div class="box_l" ServiceStaff="'+ServiceStaff+'" CommodityCode="'+CommodityCode+'" ComIdentifica="'+ComIdentifica+'">'+
            '<div class="project_title font_3">'+
            '<span class="project_title_txt">'+name+'</span>'+
            '</div>'+
            '<div class="project_num">'+
            '<span class="font_1 color_8">您的评价</span>'+
            '<ul>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" /></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" /></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" /></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" /></li>'+
            '<li><img src="/files/shopconsumerEvaluation/flower_bg.png" /></li>'+
            '</ul>'+
            '</div>'+
            '<div class="project_text">'+
            '<textarea class="font_4" rows="4" maxlength="110" placeholder="评论：本店的服务满足您的期待吗？请评价一下我们的优点和不足的地方吧!（满足15个字才是 好同志哦！）"></textarea>'+
            '<ul>'+
            '<li class="margin_r">'+
            '<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>'+
            '<img src="" class="file_img"/>'+
            '<input type="hidden" class="uuid" value="" />'+
            '<input type="hidden" class="mxid" value="1" />'+
            '</li>'+
            '<li class="margin_r hide">'+
            '<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>'+
            '<img src="" class="file_img"/>'+
            '<input type="hidden" class="uuid" value="" />'+
            '<input type="hidden" class="mxid" value="2" />'+
            '</li>'+
            '<li class="hide">'+
            '<img src="/files/shopconsumerEvaluation/add_img.png" class="add_img"/>'+
            '<img src="" class="file_img"/>'+
            '<input type="hidden" class="uuid" value="" />'+
            '<input type="hidden" class="mxid" value="3" />'+
            '</li>'+
            '</ul>'+
            '</div>'+
            '</div>'+
            '</div>';
        project.append(sp)

    }
//获取已评价的内容
    $.ajax({
        type: 'POST',
        url: '/shopAjax?businessType=getEvaluateParam',
        data: {
            businessType: "getEvaluateParam",
        },
        success:function(jsondata){
            var json=JSON.parse(jsondata)
            console.log(json)
        }
    });

})
