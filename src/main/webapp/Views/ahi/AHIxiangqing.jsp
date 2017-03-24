<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
 	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
	//TODO 登录判断

%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
		<title>AHI</title>
		<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/ahi/radialindicator.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/ahi/ahi.css"/>
		<script type="text/javascript" src="/scripts/ahi/jquery-1.12.1.min.js"></script>
		<script type="text/javascript" src="/scripts/ahi/radialIndicator.js"></script>
		
		<script type="text/javascript">
		
	   function radialIndicatorfun(aa){
			$("#radialIndicatorContext").radialIndicator({
				barWidth : 10,
				roundCorner: true,
				percentage: false ,   //指示器不显示百分号
				radius: 260,    //指示器内部圆的半径
				initValue: aa,	//指示器赤初始值
				barBgColor : "#5ab5ff",
				fontWeight: "100", 
				animate: aa,  
				barColor: "#ffffff" //指示器剩余刻度条的背景颜色
		  })
	   }
	   function xianshi (arr) {
	   		for (var i = 0;i<arr.length;i++) {
			   	radialIndicatorfun(Math.round(arr[i].point))
				$("#juti").text(arr[i].name)
		   }
	   }
			
		
		
		var plateNumber ='<%=CarId%>';
		$(document).ready(function(){
//		    //一开始获取carID
//            $.ajax({
//                type:"post",
//                dataType:"text",
//                url:'checkAction.jsp',
//                data:{ fromflag:'queryAllPointByPlateNumber',
//                    'plateNumber':encodeURI(plateNumber)
//                },
//                success:function(data){
//                    data = eval('('+data+')');
//                    if(data.length>0){
//						/* $("#totalPoint").html(Math.round(data[0].point));
//						 $("#plateNumber").val(data[0].plateNumber); */
//                        xianshi(data)
//                    }else{
//                        alert("未查询到车辆 "+plateNumber+" 得分数据！");
//                    }
//                }
//            });
                    $.ajax({
                        type:"post",
                        dataType:"text",
                        url:'/getCommonAjax',
                        data:{ 'fromflag':'queryAllPointByPlateNumber',
                            'plateNumber':encodeURI(plateNumber)
                        },
                        success:function(data){
                            data = eval('('+data+')');
                            if(data.length>0){
								/* $("#totalPoint").html(Math.round(data[0].point));
								 $("#plateNumber").val(data[0].plateNumber); */
                                xianshi(data)
                            }else{
                                alert("未查询到车辆 "+plateNumber+" 得分数据！");
                            }
                        }
                    });

			　
			　　$.ajax({
				type:"post",
				dataType:"text",
				url:'/getCommonAjax',
				data:{'fromflag':'queryCarPointOne','plateNumber':encodeURI(plateNumber)},
				success:function(data){
					data = eval('('+data+')');
					if(data.length>0){
                        var newDate = new Date();
                        newDate.setTime(data[0].date);
                        var yxrqStr = newDate.format('yyyy-MM-dd');
                        $("#shijian").html(yxrqStr);
                        $("#renyuan").html(data[0].staff);
                        $("#dianpu").html(data[0].shopName);
                        for(var i=0;i<data.length;i++){
                            $("#subPoint").append("<li class='burder_li'>"
                                +"<img class='AHI_biao' src='/files/ahi/biazhi.png'/>"
                                +"<span class='xitong font_2'>"+data[i].name+"：</span>"
                                +"<strong class='xt_zhus'>"+data[i].point+"</strong>"
                                +"<a href='#' onclick=toSubxiangqing('"+data[i].id+"','"+data[i].plateNumber+"')><span class='miaos font_1'>&nbsp;<img src='/files/ahi/miaos.png'/></span></a>"
                                +"</li>");
                        }
					}
				}
		　　});
		}); 
		function toSubxiangqing(id,plateNumber){
			if(null==plateNumber||plateNumber==''){
				plateNumber = $("#plateNumber").val();
			}
			window.location.href='/oauthLoginServlet?flagStr=AHIInfoxiangqing&plateNumber='+plateNumber+'&id='+id;
		}
       	Date.prototype.format = function(format) {
           var date = {
               "M+": this.getMonth() + 1,
               "d+": this.getDate(),
               "h+": this.getHours(),
               "m+": this.getMinutes(),
               "s+": this.getSeconds(),
               "q+": Math.floor((this.getMonth() + 3) / 3),
               "S+": this.getMilliseconds()
           };
           if (/(y+)/i.test(format)) {
               format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
           }
           for (var k in date) {
               if (new RegExp("(" + k + ")").test(format)) {
                   format = format.replace(RegExp.$1, RegExp.$1.length == 1
                       ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
               }
           }
           return format;
       	}
		</script>
	</head>
	<body>
	<input type="hidden" id="plateNumber" value="<%=CarId%>"/>
		<div class="title">

			<p class="title_main">车辆健康指数</p>			
		</div>
		<div class="center_zhis">
			<div id="totalPoint" class="biaopan">
				<div id="radialIndicatorContext" class="radialIndicatorContext"></div>
				<span id="juti" class="juti" style="color:#fff;font-size: 30px;position: relative; top: -180px;"></span>
			</div>
			<a style="display:inline-block" href="#" onclick="toSubxiangqing()"><div class="xiangq font_2">详情</div></a>
		</div>
		<div style="width:100%">
			<ul id="subPoint" class="lie_li">

				
			</ul>
			
		</div>
		<ul class="jc_ul font_0">
			<li>
				<span class="jc_color">上次检测时间：</span>
				<span class="jc_shop" id="shijian"></span>
			</li>
			<li>
				<span class="jc_color">上次检测人员：</span>
				<span class="jc_shop" id="renyuan"></span>
			</li>
			<li>
				<span class="jc_color">上次检测店铺：</span>
				<span class="jc_shop" id="dianpu"></span>
			</li>
		</ul>
	<input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>">
	<input type="hidden" id="strOpenId" name="strOpenId" value="<%=strOpenId %>">
	<input type="hidden" id="CarId" name="CarId" value="<%=CarId %>">
	</body>
</html>
