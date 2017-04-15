<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
 	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");

%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
		<title>车辆健康指数</title>
		<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/ahi/ahi.css"/>
		<script type="text/javascript" src="/scripts/ahi/jquery-1.12.1.min.js"></script>
		<script type="text/javascript" src="/scripts/ahi/chart.meter.js"></script>
		<script type="text/javascript" src="/scripts/ahi/chart.radar.js"></script>

		<script type="text/javascript">

            function xianshi (arr) {
                for (var i = 0;i < arr.length;i++) {
                    //	指示盘插件函数
                    var ctx = document.getElementById('meter').getContext('2d');
                    ctx.fillStyle = "rgba(255,165,0,1)";
                    Meter.setOptions({
                        element: 'meter',
                        centerPoint: {
                            x: 262,
                            y: 262
                        },
                        radius: 262,
                        data: {
                            value: Math.round(arr[i].point),
                            //title: '职场竞争力{t}',
                            //subTitle: '评估时间：2015.07.28',
                            subTitle: '',
                            title: arr[i].name,
                            area: [{
                                min: 0, max: 20, text: '10'
                            },{
                                min: 20, max: 40, text: '30'
                            },{
                                min: 40, max: 60, text: '50'
                            },{
                                min: 60, max: 80, text: '70'
                            },{
                                min: 80, max: 100, text: '90'
                            }]
                        }
                    }).init();
                    //	指示盘插件函数结束

                    var newDate = new Date();
                    newDate.setTime(arr[i].date);
                    var yxrqStr = newDate.format('yyyy-MM-dd');
                    $("#shijian").html(yxrqStr);
                    $("#renyuan").html(arr[i].staff);
                    $("#dianpu").html(arr[i].shopName);
                }
            }



		var plateNumber ='<%=CarId%>';
		$(document).ready(function(){
				$.ajax({
					type:"post",
					dataType:"text",
					url:'checkAction.jsp',
					data:{'actions':'queryAllPointByPlateNumber','plateNumber':encodeURI(plateNumber)},
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
                  url:'checkAction.jsp',
                  data:{'actions':'queryCarPointOne','plateNumber':encodeURI(plateNumber)},
                  success:function(data){
					data = eval('('+data+')');
					if(data.length>0){
//                        var newDate = new Date();
//                        newDate.setTime(data[0].date);
//                        var yxrqStr = newDate.format('yyyy-MM-dd');
//                        $("#shijian").html(yxrqStr);
//                        $("#renyuan").html(data[0].staff);
//                        $("#dianpu").html(data[0].shopName);
                        for(var i=0;i<data.length;i++){
                            var jc_html="";
                            var jc=data[i].point;
                            if(jc==0){       //判断是否检测
                                jc_html+= "<span class='jc_time xt_zhus font_3' style='font-size:42px' >未检测</span>";
                            }else{
                                jc_html+= "<span class='jc_time xt_zhus font_3'>"+data[i].point+"</span>";
                            }
                            $("#subPoint").append("<li class='burder_li'>"
                                +"<a href='#' onclick=toSubxiangqing('"+data[i].id+"','"+data[i].plateNumber+"')>"
                                +"<div class='burder_li_div'>"
                                +"<span class='jc_time left font_2'>"+data[i].name+"</span>"
                                +"<span class='jc_time '><img  class='zhishu_img' src='"+(data[i].logoPath?data[i].logoPath+"?x-oss-process=image/resize,m_fixed,h_125,w_109":"/files/ahi/icon/icon_4.png")+"'/></span>"
                                +"<span class='jc_time right'>"+jc_html
                                +"<img class='back_img' src='/files/ahi/back.png'/></span>"
                                +"</div>"
                                +"</a>"
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
		<div class="center_zhis">
			<div class="biaopan" >
				<div class="ahicanvar">
					<canvas id="meter"   width="524" height="524" class="radialIndicatorContext"></canvas> <!-- 仪表盘层-->
				</div>
				<div class="zahi">
					<div class="zahiimg">
						<img src="/files/ahi/AHI1.png"/>
					</div>
				</div>

				<!--<span class="juti" id="juti"></span>-->
			</div>
			<div class="upjc font_12">
				<span class="jc_time left">
					<ul>
						<li class="show_img">上次检测时间</li>
						<li class="show_cur"><span></span></li>
						<li class="show_list" id="shijian"></li>
					</ul>
				</span>
				<span class="jc_time ">
					<ul>
						<li class="show_shop ">上次检测店铺</li>
						<li class="show_cur"><span></span></li>
						<li class="" id="dianpu"></li>
					</ul>

				</span>
				<span class="jc_time right">
					<ul>
						<li class="show_img">上次检测人员</li>
						<li class="show_cur"><span></span></li>
						<li class="show_list" id="renyuan"></li>
					</ul>
				</span>
			</div>

		</div>
		<div class="conter_main">
			<ul class="lie_li" id="subPoint">

			</ul>
		</div>
	<input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>">
	<input type="hidden" id="strOpenId" name="strOpenId" value="<%=strOpenId %>">
	<input type="hidden" id="CarId" name="CarId" value="<%=CarId %>">
	</body>
</html>
