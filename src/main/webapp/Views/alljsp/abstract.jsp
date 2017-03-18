<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java" import="java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" import="com.weixin.action.MmallAction"%>
<%
String url="http://shop.duduchewang.com/upload/";

String strShopcode =(String)session.getAttribute("DUDUCHEWANG_shopcode"); 
String strOpenId   =(String)session.getAttribute("DUDUCHEWANG_OpenId");
MmallAction MmallAction= new MmallAction();
HashMap hm= MmallAction.shoplistInfo(strShopcode);
String Address=(String)hm.get("Address");
String Tel= (String)hm.get("Tel");
String Introduction=(String)hm.get("Introduction");
//String ImgIntroduct=(String)hm.get("ImgIntroduct");
HashMap map=MmallAction.shopName(strShopcode);
String ShopName=(String)map.get("ShopName");

if(Introduction.indexOf("/shop/upload/") != -1) {
  Introduction = Introduction.replaceAll("/shop/upload/",url);
} else if(Introduction.indexOf("/upload/") != -1) {
  Introduction = Introduction.replaceAll("/upload/",url);
}
%>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" /> 
    <title><%=ShopName%></title>
    <meta name="keywords" content="keyword ..." />
    <meta name="Description" content="description ..." />
    <!--<link href="favicon.ico" rel="shortcut icon" />-->
    <link href="css/global.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="headerbanner">
		<div id="bannercontent">
			<div id="bannerlist" class="iosSlider">
				<div class="slider">
					<%
						String ImgBig = "";
						String imgI="";
						HashMap map2 = MmallAction.shopImglist(strShopcode);
						String Img = (String) map2.get("ImgBig");
						String ImgIntroduct=(String)map2.get("ImgIntroduct");
						String[] Big = Img.split(";");
						if(ImgIntroduct!=null){
  						String [] ImInt=ImgIntroduct.split(";");
						for (int m = 0; m < Big.length; m++) {
							ImgBig = Big[m];
							imgI=ImInt[m];
					%>
					<div class="item">
						<a href="#"><img
							src="<%=url%>/<%=strShopcode%>/shopimg/<%=ImgBig%>"
							alt="" title="" />
						</a>
						<div class="bg"></div>
						<h4>
							<a href="#"><%=imgI%></a>
						</h4>
					</div>
					<%
						}
						}
					%>
				</div>
				<div class="iosSliderButtons">
					 <%
  for(int m=0;m<Big.length;m++){
  %>
  <span class = "button"></span>
   <% }%>
				</div>
			</div>
		</div>
		
		<div class="aboutmenu">
			<ul>
				<li class="current"><a href="">简介</a>
				</li>
				<li class="s"></li>
				<li><a href="fuwu.jsp?shopcode=<%=strShopcode%>">服务</a>
				</li>
				<li class="s"></li>
				<li><a href="fuwu2.jsp?shopcode=<%=strShopcode%>">商品</a>
				</li>
				<li class="s"></li>
				<li><a href="strength.jsp?shopcode=<%=strShopcode%>">实力</a>
				</li>
				<li class="s"></li>
				<li><a href="evaluate.jsp?shopcode=<%=strShopcode%>">评价</a>
				</li>
			</ul>
		</div>
	</div>
<div class="aboutcontent">
 <div class="banner">
 <%
 String serviceContent="";
 HashMap map3=MmallAction.shopServiceContentlist(strShopcode);
 String content=(String)map3.get("ServiceContent");
 String[] cont=content.split(";");
 for(int k=0;k<cont.length;k++){
 	serviceContent=cont[k];
 	%>
<img src="pic/t<%=serviceContent%>.jpg" alt="" title="" />
 <% }
  %>
 </div>
 <div class="address">
 <p>地址 <%=Address %></p>
  <a href="tel:<%=Tel%>">
 <p>电话 <span><%=Tel%></span></p>
 </a>
 </div>
 <div class="body">
 <p><%=Introduction %></p>
 </div>
</div>
<script language="javascript" type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.iosslider.js"></script>
<script language="javascript" type="text/javascript" src="js/default.js"></script>
</body>
</html>
