<%@ page language="java" pageEncoding="gb2312"%>
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession"%>
<%@ page language="java"
	import="com.weixin.action.MmallAction,com.vangdo.training.Action.WeiChatAction"%>
<%@ page import="org.xwd.course.pojo.SNSUserInfo"%>
<%@ page language="java"
	import="com.weixin.login.autoLogin,com.vangdo.weixin.common.Html2Text"%>
<%
	String url = "http://shop.duduchewang.com/upload/";
	String strShopcode =(String)session.getAttribute("DUDUCHEWANG_shopcode"); 
	String OpenId   =(String)session.getAttribute("DUDUCHEWANG_OpenId");
%>
<html>
<head>
<meta charset="utf-8" />
<meta content="target-densitydpi=320,width=640,user-scalable=no"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<title>收藏列表</title>
<meta name="keywords" content="keyword ..." />
<meta name="Description" content="description ..." />
<link href="css/global.css" rel="stylesheet" type="text/css" />


</head>
<body>
	<%
		String ShopName = "";
		String Address = "";
		String ID = "";
		String ImgListView = "";
		String postalcode = "";
		String ServiceContent = "";
		MmallAction MmallAction = new MmallAction();
		WeiChatAction WeiChatAction = new WeiChatAction();
		autoLogin autoLogin = new autoLogin();
		String CarId = "";
		HttpSession xsession = request.getSession();
		CarId = (String) xsession.getAttribute("CarId");
		if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
			CarId = autoLogin.judgeOpenId(strShopcode,OpenId);
		}
	%>
	<div class="shoplist">
		<ul>
			<%
				if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
			%>
			<center>
				<a style="font-size:30px">对不起，请先登陆!</a>
			</center>

			<%
				} else {

					HashMap h = MmallAction.carId(CarId,strShopcode);
					//收藏表，获取用户ID
					String UserId = (String) h.get("ID");
					ArrayList ShopIdlist = MmallAction.collectstorelist(UserId);//通过userId 去collect表查询数据。

					if (ShopIdlist.size() == 0) {
			%>
			<center>
				<a style="font-size:30px">对不起，你还没有收藏商铺!</a>
			</center>
			<%
				} else {
						for (int k = 0; k < ShopIdlist.size(); k++) {
							HashMap hash = (HashMap) ShopIdlist.get(k);
							postalcode = (String) hash.get("ShopId");
							ArrayList list = MmallAction
									.collectStorelistInfo(postalcode);

							for (int i = 0; i < list.size(); i++) {
								HashMap hm = (HashMap) list.get(i);
								ShopName = (String) hm.get("ShopName");
								Address = (String) hm.get("Address");
								ID = (String) hm.get("ID");
								ImgListView = (String) hm.get("ImgListView");
								ServiceContent = (String) hm.get("ServiceContent");
			%>
			<li onClick="javascript:window.location='abstract.jsp?ID=<%=ID%>'">
				<div class="img">
					<%
						if (ImgListView == "") {
					%>
					<img src="image/nonepic.png" alt="" title="" />
					<%
						} else {
					%>
					<img src="<%=url%>/<%=ID%>/shopimg/<%=ImgListView%>" alt=""
						title="" />
					<%
						}
					%>
				</div>
				<div class="info">
					<h4>
						<a href="abstract.jsp?ID=<%=ID%>"><%=ShopName%></a>
					</h4>
					<p class="p1">
						地址：<%=Address%></p>
					<p class="p2">
						<%
							String m[] = ServiceContent.split(";");
											for (int s = 0; s < m.length; s++) {
												String p = m[s];
												if (p.endsWith("1")) {
						%>
						精品系列
						<%
							} else if (p.equals("2")) {
						%>
						清洗美容
						<%
							} else if (p.equals("3")) {
						%>
						装饰改装
						<%
							} else if (p.equals("4")) {
						%>
						保养换油
						<%
							} else if (p.equals("5")) {
						%>
						钣金油漆
						<%
							} else if (p.equals("6")) {
						%>
						底盘轮胎
						<%
							} else if (p.equals("7")) {
						%>
						汽车救援
						<%
							}
											}
						%>

					</p>
				</div>
				<div class="clear"></div></li>
			<%
				}
						}
					}
				}
			%>


		</ul>
	</div>
</body>
</html>
