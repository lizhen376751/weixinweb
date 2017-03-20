<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=GBK" language="java"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>AHI</title>
		<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/ahi/ahi.css"/>
		<script type="text/javascript" src="/scripts/ahi/jquery-1.12.1.min.js"></script>
		<script src="/scripts/ahi/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		var number = 0;
		var id = '<%=request.getParameter("id")%>';
		var baseDate;
		var plateNumber = decodeURI(decodeURI('<%=request.getParameter("plateNumber")%>'));
		$(document).ready(function(){ 
			　　$.ajax({
				  type:"post",
				  dataType:"text",
                  url:'/getCommonAjax',
                  data:{'fromflag':'queryCarPointOne','plateNumber':plateNumber},
				  success:function(data){
						data = eval('('+data+')');
						if(data.length>0){
							baseDate=data;
							showDate();
						}else{
							alert("未查询到车辆 "+plateNumber+" 得分数据！");
						}
					}
			　　});
		}); 
		
		function showDate(param){
			for(var i=0;i<baseDate.length;i++){
				if(baseDate[i].id==id){
					number = i;
					id='';
				}
			}
			if(param=='up'){
				number = number+1;
				if(number==baseDate.length){
					number = 0;
				}
			}else if(param=='down'){
				number = number-1;
				if((number+1)==0){
					number = baseDate.length-1;
				}
			}
			$("#subClassName").html(baseDate[number].name);
			$("#numFont").html(baseDate[number].point);
			$.ajax({
				type:"post",
				dataType:"text",
				url:'/getCommonAjax',
				data:{'fromflag':'queryCarPointTwo','plateNumber':plateNumber,'id':baseDate[number].id,'ratio':'33'},
				success:function(sdata){
					sdata = eval('('+sdata+')');
					if(sdata.length>0){
						$("#subPoint").html("");
						for(var i=0;i<sdata.length;i++){
							$("#subPoint").append("<li class='burder_li numlie_li'>"
								  	+"<span class='xitong numxitong font_1'>"+sdata[i].name+"</span>"	
								  	+"<span class='jingbao font_0'>"+sdata[i].resultQuestionDescribeList.length+"条隐患</span>"
								  	+"<span class='submiaos font_1' onClick='Togglezs(this);' >展开&nbsp;<img src='/files/ahi/zk.png' /> </span>"
								  	+"</li>"
								  	+"<li id='subnumlie_li_"+sdata[i].id+"' class='subnumlie_li' style='display:none'>"
								  	+"</li>");
							var resultQuestionDescribeList=sdata[i].resultQuestionDescribeList;
							for(var j=0;j<resultQuestionDescribeList.length;j++){
								$("#subnumlie_li_"+sdata[i].id+"").append("<div class='lianghao'>"
									  	+"<span class='subxitong font_1'>"+resultQuestionDescribeList[j].name+"</span>"	
									  	+"<span class='submiaos font_1'><img src='/files/ahi/sun_tis_06.png' />得分较低</span>"
									  	+"</div>"		  	
									  	+"<hr class='sub_solid' id='sub_solid_"+resultQuestionDescribeList[j].id+"'/>");
								var inspectionDetailedDescription = resultQuestionDescribeList[j].inspectionDetailedDescription;
                                if(inspectionDetailedDescription.length>0) {
                                    for (var n = 0; n<inspectionDetailedDescription.length; n++) {
                                        $("#sub_solid_" + resultQuestionDescribeList[j].id + "").after("<div class='lianghao'>"
                                            + "<span class='subxitong font_1'>检测描述：</span>"
                                            + "<span class='submiaos font_1'><img src='/files/ahi/sun_tis_06.png' />" + inspectionDetailedDescription[n].description + "</span>"
                                            + "</div>"
                                            + "<div class='lianghao'>"
                                            + "<span class='subxitong font_1'>造成危害：</span>"
                                            + "<span class='submiaos font_1'>" + inspectionDetailedDescription[n].influence + "</span>"
                                            + "</div>"
                                            + "<div class='lianghao'>"
                                            + "<span class='subxitong font_1'>给予建议：</span>"
                                            + "<span class='submiaos font_1'>" + inspectionDetailedDescription[n].suggest + "</span>"
                                            + "</div>");
                                    }
                                }
							}
						}
					}else{
						alert("未查询到车辆 "+plateNumber+" 得分数据！");
					}
				}
		　　  	});
			
			
		}
		</script>
	</head>
	<body>
		<div class="title">
			<a class="title_img" href="JavaScript:history.go(-1);"><img  src="/files/ahi/fanhui.png"/></a>
			<p class="title_main" >AHI车辆健康指数</p>			
		</div>
		<div class="center_1">
			<div class="num">
				<p id="numFont" class="num_font font_1"></p>
				<a  href="#"><div id="subClassName" class="numxiangqfont centerxiangq font_1"></div></a>
			</div>
			<a class="num_leftbut" href="#" onclick="showDate('up')"><div class="numxiangqfont numxiangq font_1"><<&nbsp;上一项</div></a>
			<a class="num_rightbut" href="#" onclick="showDate('down')"><div class="numxiangqfont numxiangq font_1">下一项&nbsp;>></div></a>
		</div>
		<div style="width:100%">
			<ul id="subPoint" class="lie_li">
			 <!-- <li class="burder_li numlie_li">
			  	<span class="xitong numxitong">空调系统</span>	
			  	<span class="jingbao">2条隐患</span>
			  	<span class="submiaos" onClick="Togglezs(this);" >展开&nbsp;<img src="../img/zk.png" /> </span>
			  </li>
			  <li class="subnumlie_li" style="display:none">
			  	<div class="lianghao">
			  		<span class="subxitong ">空调系统</span>	
			  	    <span class="submiaos"><img src="../img/sun_tis_03.png" />性能良好</span>
			  	</div>			  	
			  	<hr class="sub_solid" />
			  	<div class="lianghao">
			  		<span class="subxitong ">空调风管</span>	
			  	    <span class="submiaos"><img src="../img/sun_tis_06.png" />描述</span>
			  	</div>
			  	<div class="lianghao">
			  		<span class="subxitong ">影响：</span>	
			  	    <span class="submiaos">描述</span>
			  	</div>
			  	<div class="lianghao">
			  		<span class="subxitong ">建议：</span>	
			  	    <span class="submiaos">建议</span>
			  	</div>
			  	<hr class="sub_solid" />
			  </li>
			  <li class="burder_li numlie_li">
			  	<span class="xitong numxitong">空调系统</span>	
			  	<span class="no_jignbao ">状态良好，无隐患</span>
			  	<span class="submiaos" onClick="Togglezs(this);">展开&nbsp;<img src="../img/zk.png" /> </span>
			  </li>
			  <li class="subnumlie_li" style="display:none">
			  	<div class="lianghao">
			  		<span class="subxitong ">空调系统</span>	
			  	    <span class="submiaos"><img src="../img/sun_tis_03.png" />性能良好</span>
			  	</div>			  	
			  	<hr class="sub_solid" />
			  </li>
			  <li class="burder_li numlie_li">
			  	<span class="xitong numxitong">空调系统</span>	
			  	<span class="no_jignbao">状态良好，无隐患</span>
			  	<span class="submiaos" onClick="Togglezs(this);">展开&nbsp;<img src="../img/zk.png" /> </span>
			  </li>
			  <li class="subnumlie_li" style="display:none">
			  	<div class="lianghao">
			  		<span class="subxitong ">空调系统</span>	
			  	    <span class="submiaos"><img src="../img/sun_tis_03.png" />性能良好</span>
			  	</div>			  	
			  	<hr class="sub_solid" />
			  </li>
				 -->
			</ul>
			
		</div>
	</body>
</html>
