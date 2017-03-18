<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java"
	import="com.vangdo.training.Action.WeiChatAction,
	com.weixin.action.MmallAction,
	com.weixin.login.autoLogin,
	cn.duduchewang.weixin.common.GetProductClass,
	cn.duduchewang.weixin.common.GetShopInfo"%>
<%
	String strShopcode =request.getParameter("shopcode");//(String)session.getAttribute("DUDUCHEWANG_shopcode"); 
	String strOpenId   =request.getParameter("openid");//(String)session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		//String OpenId = (String)request.getAttribute("OpenI");
		autoLogin autoLogin = new autoLogin();
		CarId = autoLogin.judgeOpenId(strOpenId, strShopcode);
		session.setAttribute("DUDUCHEWANG_CarId", CarId);
	}

	GetShopInfo GetShopInfo = new GetShopInfo();
	ArrayList listLianSuo = new ArrayList();
	listLianSuo = GetShopInfo.listLianSuo(strShopcode);

	WeiChatAction WeiChatAction = new WeiChatAction();
	HashMap h = WeiChatAction.WeiChatUserformatted_address(strOpenId);
	String formatted_address = (String) h.get("formatted_address");

	MmallAction MmallAction = new MmallAction();
	String ImgBig = "";
	String imgI = "";
	HashMap map2 = MmallAction.shopImglist(strShopcode);
	String Img = (String) map2.get("ImgBig");
	String ImgIntroduct = (String) map2.get("ImgIntroduct");
	String ShopName = (String) map2.get("ShopName");
	String ShopLat = (String) map2.get("Lat");
	String ShopLon = (String) map2.get("Lon");
	String ShopTel = (String) map2.get("Tel");
	String[] Big = Img.split(";");

	String ShopZuoBiao = ShopLat + "," + ShopLon;

	ArrayList list = new ArrayList();
	ArrayList list1 = new ArrayList();
	GetProductClass class1 = new GetProductClass();
	list = class1.gethot(strShopcode);
	list1 = class1.getanniu(strShopcode);
%>
<html lang="en">
<head>
<meta name="viewport"
	content="maximum-scale=5.0,minimum-scale=1.0,user-scalable=yes">
<meta http-equiv="Content-Type" content="text/html;" charset="utf-8" />
<meta name="keywords" content="汽车销售,汽车修理,美容装饰,钣金烤漆,汽车保养" />
<meta name="description"
	content="汽车服务连锁，是一家集汽车销售、汽车修理、美容装饰、钣金烤漆为一体的一站式汽车服务中心。公司本着以客户为尚，用爱做服务的理念，竭力打造中国汽车养护行业第一品牌，做中国汽车后市场的百年企业。     " />
<title>首页</title>

<link href="Mall/css/mobile_common.css" rel="stylesheet" type="text/css" />
<link href="Mall/css/mobile_style.css" rel="stylesheet" type="text/css" />
<link href="Mall/css/mobile_style_dev.css" rel="stylesheet"
	type="text/css" />
<link href="Mall/css/global.css" rel="stylesheet" type="text/css" />
<script src='Mall/js/jquery.js' type='text/javascript'></script>
<script src="Mall/js/main.js"></script>
<script src="Mall/js/mobile_common.js"></script>
<script src="Mall/js/mobile.js"></script>
<!--百度地图获取当前坐标-->

<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=nOi93QKhPFHG6xfAp94mm1mk"></script>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">

	var nowLoationsd;
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			//alert('您的位置：'+r.point.lng+','+r.point.lat);
			nowLoationsd = r.point.lat+','+r.point.lng;
			
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)39.990848,116.36018
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)36.779198,118.02967
	//var ddsdd="ddbb";

	function goBaiduDaohang() {
		<%if (listLianSuo != null && listLianSuo.size() > 0) {%>
			window.location="SotreListDaohang.jsp?shopcode=<%=strShopcode%>&OpenI=<%=strOpenId%>";
		<%} else {%>
			if(nowLoationsd==null || typeof(nowLoationsd) == undefined || nowLoationsd=="undefined") {
				var ddsddd = "<%=formatted_address%>";
				if(ddsddd==null) {
					nowLoationsd = "";
				}
				else {
					nowLoationsd = ddsddd;
				}
			}
			window.location="http://api.map.baidu.com/direction?origin=&destination=<%=ShopZuoBiao%>&mode=driving&region=&output=html&src=duduchewang";
<%}%>
	}
</script>
</head>
<body id="indexPage">


	<!-- Copyright � 2005. Spidersoft Ltd -->
	<style>
A.applink:hover {
	border: 2px dotted #DCE6F4;
	padding: 2px;
	background-color: #ffff00;
	color: green;
	text-decoration: none
}

A.applink {
	border: 2px dotted #DCE6F4;
	padding: 2px;
	color: #2F5BFF;
	background: transparent;
	text-decoration: none
}

A.info {
	color: #2F5BFF;
	background: transparent;
	text-decoration: none
}

A.info:hover {
	color: green;
	background: transparent;
	text-decoration: underline
}

.grid_ebb859c7a465117f88b3cfd9d84be9c0 td {
	background-color: #FFFFFF;
	border-radius: px;
}

.focus span {
	width: 10px;
	height: 10px;
	margin-right: 10px;
	border-radius: 50%;
	background: #666;
	font-size: 0
}

.focus span.current {
	background: #fff
}

a.bottom_menu_main .mtitle {
	line-height: 25px !important;
}

ul.bottom_menu {
	position: fixed;
	left: 0;
	bottom: 0px;
	width: 100%;
	z-index: 99999999
}

ul.bottom_menu>li {
	float: left;
}

ul.bottom_menu a {
	display: block;
	cursor: pointer;
	font-size: 12px;
}

ul.bottom_menu .float .mpic,ul.bottom_menu .float .mtitle {
	float: left
}
</style>
	<!-- /Copyright � 2005. Spidersoft Ltd -->

	<div id="main">
		<div id="top"></div>
		<div id="mainmenu" style="height:1px;"></div>
		<div id="container" style="width:100%;height:100%;background:none;">
				<div id="9b9236c6d892bd929385745701615190_slider" class="slider" style="height: 200px;">
					<ul>
						<%
							String url = "http://shop.duduchewang.com/upload/";
							if (ImgIntroduct != null) {
								String[] ImInt = ImgIntroduct.split(";");
								for (int m = 0; m < Big.length; m++) {
									ImgBig = Big[m];
									imgI = ImInt[m];
						%>
						<li><img src="<%=url%>/<%=strShopcode%>/shopimg/<%=ImgBig%>">
						</li>
						<%
							}
							}
						%>

					</ul>
				</div>
				<script type="text/javascript" src="Mall/js/yxMobileSlider.js"></script>
				<script>
					$("#9b9236c6d892bd929385745701615190_slider")
							.yxMobileSlider({
								width : '100%',
								height : '200',
								during : '3000'
							});
				</script>
			<!-- 按钮 -->
				<div class="grid grid_t">
					<table class="gridTable grid_ebb859c7a465117f88b3cfd9d84be9c0"
						cellspacing="3">
						<tr>
							<%
								if (list1 != null && list1.size() > 0) {
									for (int e = 0; e < list1.size(); e++) {
										HashMap hm = (HashMap) list1.get(e);
										String name = hm.get("name").toString();
										String leibie = hm.get("leibie").toString();
										String tubiao = hm.get("tubiao").toString();
										String url1 = "http://shop.duduchewang.com/upload/tubiao/"
												+ tubiao + ".png";
										if (e % 4 == 0) {
							%>
						</tr>
						<tr>
							<%
								}
							%>

							<td style=""><div class="grid_big" id="<%=e%>">
									<a
										href="shopService.jsp?shopcode=<%=strShopcode%>&ProductClass=<%=leibie%>">
										<div class="grid_icon">
											<img src=<%=url1%> style="width:55px;height:55px;" />
										</div>
										<div class="grid_name" style="color:#000000;width: 70px; height: 20px;overflow: hidden; text-overflow:ellipsis"><%=name%></div> </a>
								</div>
							</td>

							<%
								}
								}
							%>
						</tr>
					</table>
				</div>
				<div id="wheather_style" align="center"
					style="height:40px;line-height:40px;overflow:hidden; background-color:#E0E0E0;  background-repeat:repeat; ">
					<font color="#FF4040" size="3px">热门服务</font>
				</div>

				<div class="aboutcontent2" style="width:100%;">
					<div class="discount">
						<ul>

							<%
								if (list != null && list.size() > 0) {
									for (int i = 0; i < list.size(); i++) {
										HashMap hm = (HashMap) list.get(i);
										String ProductImgList = hm.get("ProductImgList").toString();
										String ProductName = hm.get("ProductName").toString();
										String ProductPrice = (String) hm.get("ProductPrice");
										String ProductOriginalPrice = (String) hm
												.get("ProductOriginalPrice");
										String ProductContent = (String) hm.get("ProductContent");
										String ProductID = (String) hm.get("ID");
							%>
							<li
								onClick="javascript:window.location='pruductDescript.jsp?ProductID=<%=ProductID%>'">

								<div class="img">
									<%
										if (ProductImgList == "") {
									%>
									<img src="image/nonepic.png" alt="" title="" />
									<%
										} else {
									%>
									<img
										src="<%=url%>/<%=strShopcode%>/shopimg/<%=ProductImgList%>"
										alt="" title="" />
									<%
										}
									%>

								</div>
								<div class="info">
									<dl>
										<dt style=" width: 100%; height:20px;overflow: hidden; text-overflow:ellipsis" >
											<a href="pruductDescript.jsp?ProductID=<%=ProductID%>"><%=ProductName%></a>
										</dt>
										<dd style=" width: 100%; height:20px;overflow: hidden; text-overflow:ellipsis" >
											<%=ProductContent%></dd>
									</dl>
									<div class="price">
										&yen;<%=ProductPrice%><del>
											&yen;<%=ProductOriginalPrice%></del>
									</div>
								</div> <a href="pruductDescript.jsp?ProductID=<%=ProductID%>"
								class="servicebtn"></a>
							</li>
							<%
								}
								} else {
							%>
							<center>
								<a style="font-size:30px"><br> <br>对不起，您当前没有内容！<br>
									<br> </a>
							</center>

							<%
								}
							%>
						</ul>
					</div>
				<!-- </div> -->
			</div>
		</div>
	</div>
	<!-- footer开始 -->
	<ul class="bottom_menu"
		style="background-color:#EDEDED;opacity:0.9;height:60px;">
		<li style="width:25%;">
			<ul class="bottom_menu_sub"
				style="background-color:#FFFFFF;position: absolute;width:25%;">

			</ul> <a href="tel:<%=ShopTel%>" class="bottom_menu_main "
			style="color:#666666;padding-top:5px;">
				<div align="center" class="mpic">
					<img border="0" src="pic/e1.png" style="width:30px">
				</div>
				<div align="center" class="mtitle">电话直拨</div> </a>
		</li>
		<li style="width:25%;">
			<ul class="bottom_menu_sub"
				style="background-color:#FFFFFF;position: absolute;width:25%;;"></ul>
			<a href="#" onClick="goBaiduDaohang()" class="bottom_menu_main "
			style="color:#666666;padding-top:5px;"><div align="center"
					class="mpic">
					<img border="0" src="pic/e2.png" style="width:22px">
				</div>
				<div align="center" class="mtitle">门店导航</div> </a>
		</li>
		<li style="width:25%;">
			<ul class="bottom_menu_sub"
				style="background-color:#FFFFFF;position: absolute;width:25%;;"></ul>
			<a href="notice.jsp?shopcode=<%=strShopcode%>"
			class="bottom_menu_main " style="color:#666666;padding-top:5px;"><div
					align="center" class="mpic">
					<img border="0" src="pic/e3.png" style="width:30px">
				</div>
				<div align="center" class="mtitle">通知公告</div> </a>
		</li>
		<li style="width:25%;">
			<ul class="bottom_menu_sub"
				style="background-color:#FFFFFF;position: absolute;width:25%;;"></ul>
			<a href="abstract.jsp?shopcode=<%=strShopcode%>"
			class="bottom_menu_main " style="color:#666666;padding-top:5px;"><div
					align="center" class="mpic">
					<img border="0" src="pic/e4.png" style="width:35px">
				</div>
				<div align="center" class="mtitle">关于我们</div> </a>
		</li>
	</ul>



	<script>
		$(function() {
			$(".showSubMenu").click(function() {
				$(this).parent().find(".bottom_menu_sub").toggle()
			})
		})
	</script>
	<script type="text/javascript">
		var bottomHeight = 60;
		$("#indexPage #main").css("margin-bottom", bottomHeight + "px");
		$("#defaultPage #main").css("margin-bottom", bottomHeight + "px");
	</script>
</body>
</html>
