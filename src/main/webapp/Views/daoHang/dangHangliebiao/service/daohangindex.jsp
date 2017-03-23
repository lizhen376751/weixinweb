
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java" import="cn.duduchewang.weixin.common.GetShopInfo"%> 

<%
	String strShopcode = request.getParameter("shopcode");
	String strOpenId = (String)request.getAttribute("openid");
	String url="http://shop.duduchewang.com/upload/";
	String shopType_search = request.getParameter("shopType_search");
	shopType_search = shopType_search==null ? "" : shopType_search;
	String orderType_search = request.getParameter("orderType_search");
	orderType_search = orderType_search==null ? "" : orderType_search;

	GetShopInfo GetShopInfo = new GetShopInfo();
	ArrayList<HashMap<String,String>> shopList = GetShopInfo.queryShopCodeListByLmCode(strShopcode,shopType_search,orderType_search);


%>


<html>
	<head>
		<meta charset="UTF-8">
		<title>服务导航</title>
		<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/daoHang/daoHangliebiao/css/server.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/daoHang/daoHangliebiao/css/radialindicator.css"/>
		<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/daoHang/daoHangliebiao/js/daoHangliebiao.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/daoHang/daoHangliebiao/js/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=nOi93QKhPFHG6xfAp94mm1mk"></script>
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
	
		function goBaiduDaohang(ShopZuoBiao) {
				//window.location="http://api.map.baidu.com/direction?origin="+nowLoationsd+"&destination=36.779198,118.02967&mode=driving&region=淄博&output=html&src=duduchewang";
				window.location="http://api.map.baidu.com/direction?origin=&destination="+ShopZuoBiao+"&mode=driving&region=&output=html&src=duduchewang";
		}
	</script>
	
	
	<script type="text/javascript">
		//点击店铺类型，提交
		function searchByType(typeFlag){
			var typeFlagSelectedStr = "";
			if($("#xcSelect").hasClass("server_cenulimg")){
				typeFlagSelectedStr = typeFlagSelectedStr+",xc,";
			}
			if($("#mrSelect").hasClass("server_cenulimg")){
				typeFlagSelectedStr = typeFlagSelectedStr+",mr,";
			}
			if($("#bySelect").hasClass("server_cenulimg")){
				typeFlagSelectedStr = typeFlagSelectedStr+",by,";
			}
			$("#shopType_search").val(typeFlagSelectedStr);
			
			document.billInput.submit();
		}
		
		
		//点击列表图片，跳转到本店商城  (同路径下的zy.jsp)
		function goShopInfo(shopCode){
			window.location="/daoHang/daoHangliebiao/service/zy.jsp?shopcode="+shopCode+"&openid=<%=strOpenId%>";
		}
		
		
		//点击排序，提交
		function searchByOrderType(orderType){
			$("#orderType_search").val(orderType);
			document.billInput.submit();
		}
		
		//添加预约
		function advance(shopCode2,shopName){
			window.location="/daoHang/reserve/service/Advance.jsp?shopcode2="+shopCode2+"&shopName="+shopName;
		}
		
	</script>
	
	
	</head>
	
	<body>
	<form action="daohangindex.jsp" method="post" name="billInput" id="billInput" >
		<!-- <div class="title">
			
			<p class="title_main">导航服务</p>			
		</div> -->
		<div class="server_center">
			<ul class="server_cenul">
				<li><img  class="server_click" onclick="countClickedTimes(this);searchByType('xc')" src="/files/daoHang/daoHangliebiao/img/daohang_1.png"/><span id="xcSelect" <%if(shopType_search.indexOf("xc")>-1){%>class="xuaze server_cenulimg"<%}else{%>class="xuaze"<%}%> ></span><p class="font_1 server_cenulcolor">洗车</p></li>
				<li><img  class="server_click" onclick="countClickedTimes(this);searchByType('mr')" src="/files/daoHang/daoHangliebiao/img/daohang_2.png"/><span id="mrSelect" <%if(shopType_search.indexOf("mr")>-1){%>class="xuaze server_cenulimg"<%}else{%>class="xuaze"<%}%> ></span><p class="font_1  server_cenulcolor">美容</p></li>
				<li><img  class="server_click" onclick="countClickedTimes(this);searchByType('by')" src="/files/daoHang/daoHangliebiao/img/daohang_3.png"/><span id="bySelect" <%if(shopType_search.indexOf("by")>-1){%>class="xuaze server_cenulimg"<%}else{%>class="xuaze"<%}%> ></span><p class="font_1  server_cenulcolor">保养</p></li>
			</ul>
		</div>
		<div class="paixu font_0 server_cenulcolor" >
			<ul>
				<li><a href="#" onclick="searchByOrderType('')" <%if("".equals(orderType_search)){%>class="px_tree server_cenulcolor"<%}else{%>class="server_cenulcolor"<%}%>>默认排序 </a>&nbsp;|</li>
				<li><a href="#" onclick="searchByOrderType('1')" <%if("1".equals(orderType_search)){%>class="px_tree server_cenulcolor"<%}else{%>class="server_cenulcolor"<%}%>>评价得分</a>&nbsp;|</li>
				<li><a href="#" onclick="searchByOrderType('2')" <%if("2".equals(orderType_search)){%>class="px_tree server_cenulcolor"<%}else{%>class="server_cenulcolor"<%}%>>购买人数</a></li>
			</ul>
		</div>
		<div class="server_center2">
		  <%-- 
		  <ul class="server_cenul2">  
		    <li class="server_cenli" >
		    	<ul class="server_cenli_ul">
		    		<li class=" server_li server_cenli_img" onClick="goBaiduDaohang('10.1234,10.123')"><img src="../img/images/daohang_27.png"/></li>
			    	<li class=" server_li" style="margin-left:24px;" onClick="goBaiduDaohang('10.1234,10.123')"> 
			    	  <ul>
			    	  	<li class="font_2" style="height:80px;line-height:80px">朋友圈美车</li>
			    	  	<li>
			    	  		 <img src="../img/daohang_16.png"/>
			    	  		 <img src="../img/daohang_16.png"/>
			    	  		 <img src="../img/daohang_16.png"/>
			    	  		 <img src="../img/daohang_16.png"/>
			    	  		 <img src="../img/daohang_18.png"/>
			    	  		 <span class="font_0 server_font">已有00.00人体验</span>
			    	  	</li>
			    	  	<li style="padding-top:10px;">
			    	  		<img src="../img/daohang_3_03.png"/>
			    	  		<img src="../img/daohang_3_05.png"/>
			    	  		<img src="../img/daohang_3_06.png"/>
			    	  	</li>
			    	  	<li style="height:50px;line-height:50px" class="server_addr server_font">历下区凤凰路与世纪大道交叉口东行300米路北 </li>
			    	  	<li><img src="../img/daohang_23.png"/> <span class="server_addr server_font2">0.0米</span></li>	    	  	
			    	  </ul>
			    	</li>
			    	<li class=" server_li" style="float:right"><a href='tel:<%="18653341292"%>;'  ><img src="../img/daohang_4.png"/></a></li>
		    		
		    	</ul>
		    	
		    </li>
		  </ul> 
		   --%>
		  <%
		  if(shopList!=null && shopList.size()>0){
		  	for(HashMap<String,String> shopCodeHm :shopList){
				String shopCode2 = (String)shopCodeHm.get("shopcode2");
				String lebal = (String)shopCodeHm.get("lebal");
				if(lebal==null) lebal="";
				
				//店铺总分
				String rtatescore = (String)shopCodeHm.get("rtatescore");
				rtatescore = rtatescore==null||"null".equals(rtatescore)||"".equals(rtatescore) ? "0" : rtatescore;
				//消费人数
				String kdCount = (String)shopCodeHm.get("kdCount");
				kdCount = kdCount==null||"null".equals(kdCount)||"".equals(kdCount) ? "0" : kdCount;
				
				HashMap shopHm = GetShopInfo.dispShop(shopCode2);
			  	String ShopName = (String)shopHm.get("ShopName");
				String Lat = (String)shopHm.get("Lat");
				String Lon = (String)shopHm.get("Lon");
				String Address = (String)shopHm.get("Address");
				String ImgListView = (String)shopHm.get("ImgListView");
				String ServiceContent = (String)shopHm.get("ServiceContent");
				String Tel = (String)shopHm.get("Tel");
				
				String ShopZuoBiao1 = Lat+","+Lon;
		  %>
		  
		  
		  <ul class="server_cenul2">  
		    <li class="server_cenli" >
		    	<ul class="server_cenli_ul">
		    		<li class=" server_li server_cenli_img" onClick="goShopInfo('<%=shopCode2%>')">
		    		  <!-- <img src="../img/images/daohang_27.png"/> -->
		    		  <%
					  if(ImgListView == null || "".equals(ImgListView) || "null".equals(ImgListView)){
					  %>
					    <img src="/files/daoHang/daoHangliebiao/img/nonepic.png" alt="" title="" />
					  <%}else{
					   %>
					  <img src="<%=url%>/<%=shopCode2%>/shopimg/<%=ImgListView%>" alt="" title="" />
					  <% }%>
		    		</li>
			    	<li class=" server_li" style="margin-left:24px;" onClick="goBaiduDaohang('<%=ShopZuoBiao1%>')"> 
			    	  <ul>
			    	  	<li class="font_2" style="height:80px;line-height:80px"><%=ShopName %></li>
			    	  	<li>
			    	  		<% 
			    	  			float aa=0;
			    	  			for(float a=Float.parseFloat(rtatescore);a>0;a--){ 
			    	  		%>
			    	  		 	<img src="/files/daoHang/daoHangliebiao/img/daohang_16.png"/>
			    	  		<%  
			    	  				aa++;
			    	  			}
			    	  			
			    	  			for(float b=0;b<5-aa;b++){
			    	  		%>
			    	  			<img src="/files/daoHang/daoHangliebiao/img/daohang_18.png"/>
			    	  		<% 	} %>
			    	  		 <span class="font_0 server_font"><!-- 已有000人体验 --></span>
			    	  	</li>
			    	  	<li style="padding-top:10px;">
			    	  		<% if(lebal.indexOf("洗车")>-1){ %><img src="/files/daoHang/daoHangliebiao/img/daohang_3_03.png"/><% } %>
			    	  		<% if(lebal.indexOf("美容")>-1){ %><img src="/files/daoHang/daoHangliebiao/img/daohang_3_05.png"/><% } %>
			    	  		<% if(lebal.indexOf("保养")>-1){ %><img src="/files/daoHang/daoHangliebiao/img/daohang_3_06.png"/><% } %>
			    	  	</li>
			    	  	<li style="height:50px;line-height:50px" class="server_addr server_font"><%=Address %> </li>
			    	  	<li>
			    	  	   <img src="/files/daoHang/daoHangliebiao/img/daohang_23.png"/>
			    	  	   <span class="server_addr server_font2"><!-- 0.0千米 -->点击自动导航</span>
			    	  	   <span class="server_addr marg_server  server_font2"><%=kdCount%>已人购买</span>
			    	  	</li>	    	  	
			    	  </ul>
			    	</li>
			    	<li class=" server_li" style="float:right">
			    		<% if(Tel!=null && !"".equals(Tel)){ %>
			    		<a href="tel:<%=Tel%>"><img src="/files/daoHang/daoHangliebiao/img/daohang_4.png"/></a>
			    		<div   onclick="advance('<%=shopCode2%>','<%=ShopName %>')" class="yyue font_0">预约</div>
			    		<% } %>
			    	</li>
			    	
			    	
		    		
		    	</ul>
		    	
		    </li>
		  </ul> 
		  <%
		  	}
		  }
		  %>
		</div>
		<input type="hidden" id="shopcode" name="shopcode" value="<%=strShopcode%>">
		<input type="hidden" id="strOpenId" name="strOpenId" value="<%=strOpenId  %>">
		<input type="hidden" id="shopType_search" name="shopType_search" value="<%=shopType_search%>">
		<input type="hidden" id="orderType_search" name="orderType_search" value="<%=orderType_search%>">
	</form>
	</body>
</html>
