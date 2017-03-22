/**
 * Created by Administrator on 2017/3/22.
 */
$(document).ready(function(){
    $.ajax({
        type    : 'POST',
        url     : '/getCommonAjax',
        data    : {
            fromflag   : "baoYangList"
        },
        success:function(jsondata){
            alert(jsondata);
            var json = JSON.parse(jsondata);
            var html="";
            if(json.length>0){
                if(json.length>5){
                      $("#thelist").attr("style","");
                }
                for(var ii=0;ii<json.length;ii++){
                    var flaghtml="";
                    if(json[ii].CustomerDemand_Level==1){
                        flaghtml+="<div class='by_width font_1 by_bgcolorfjj'> 状<i  style='visibility: hidden'>保养</i>态：非常紧急</div>";
                    }else if(json[ii].CustomerDemand_Level==2){
                        flaghtml+="<div class='by_width font_1 by_bgcolorjj'> 状<i  style='visibility: hidden'>保养</i>态：紧急</div>";
                    }else{
                        flaghtml+="<div class='by_width font_1 by_bgcolorpt'> 状<i  style='visibility: hidden'>保养</i>态：普通</div>";
                    }
                    html+="<li>"+
                        "<div class='by_center by_top'>"+
                        "<span class='by_date font_2'>"+json[ii].CustomerDemand_DateBegin +"</span>"+
                        "<span class='by_name font_2'>&nbsp;&nbsp;"+json[ii].shopName+"</span>"+
                        "</div>"+
                        "<div class='by_center font_1 by_cenulcolor'> 保养类型："+json[ii].model_name1+"/"+json[ii].model_name2+"</div>"+
                        "<div class='by_center font_1 by_cenulcolor'> 详<i style='visibility: hidden'>保养</i>情："+json[ii].customerDemand_Memo +"</div>"+
                        "<div class='by_center font_1 by_cenulcolor'> 状<i  style='visibility: hidden'>保养</i>态："+flaghtml +" </div>"+
                        "</li>"
                }

            }else{
                html+="<li>"+
                          "<div style='width:100%;height:100%;margin-top:6%;' align='center'><font size='6'>无保养提醒信息！</font> </div>"+
                      "</li>"
            }
            $("#thelist").append(html)
            console.log(json);
        },
        error:function(){
        }
    });
});