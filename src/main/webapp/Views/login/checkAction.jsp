<%@ page contentType="text/html; charset=GBK" language="java" 
		import="com.weixin.action.UserReg,com.weixin.login.loginActionNew,com.weixin.login.loginOut,cn.duduchewang.weixin.common.GetShopInfo"%>
<%@ page errorPage="./error2.jsp"%>

<link rel="stylesheet" href="./css/netstars.css" type="text/css">
<meta http-equiv="Content-Type"  content="text/html; charset=GBK">
<%
String actions = request.getParameter("actions");
String shopcode = request.getParameter("shopcode");
String strOpenId = request.getParameter("strOpenId");
String url=request.getParameter("url");
boolean flg = false;
if(actions != null && actions.equals("regist")) {
  UserReg UserReg= new UserReg();
  flg = UserReg.regist(request);
  if(flg) {
	  String dazhuanpan = request.getParameter("dazhuanpan");

	  if("1".equals(dazhuanpan)){
		  GetShopInfo GetShopInfo = new GetShopInfo();
		  String WXAppId = GetShopInfo.getShopAppid(shopcode);


		out.print("<script language=\"javascript\">\n");
		out.print("<!--\n");
		out.print("window.location.href='https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WXAppId+"&redirect_uri=http%3A%2F%2Fwx.duduchewang.cn%2Fweixincore%2FDaZhuanPanServlet?shopcode="+shopcode+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect'\n");
		out.print("//-->\n");
		out.print("</script>\n");
	  }
	  else {
		out.print("<script language=\"javascript\">\n");
		out.print("<!--\n");
		out.print("window.location.href='jifen.jsp?shopcode="+shopcode+"'\n");
		out.print("//-->\n");
		out.print("</script>\n");
	  }
  }
  else {
		out.print("<script language=\"javascript\">\n");
		out.print("<!--\n");
		out.print("window.location.href='infoShow/registFailInfo.jsp?shopcode="+shopcode+"'\n");
		out.print("//-->\n");
		out.print("</script>\n");
  }
}
else if(actions != null && actions.equals("login")) {
  loginActionNew loginActionNew= new loginActionNew();
  flg = loginActionNew.login(request);
  if(flg) {
		out.print("<script language=\"javascript\">\n");
		out.print("<!--\n");
		out.print("window.location.href='lianMengKa/lianMengCard/homePage.jsp?shopcode="+shopcode+"'\n");
		out.print("//-->\n");
		out.print("</script>\n");
  }
  else {
		out.print("<script language=\"javascript\">\n");
		out.print("<!--\n");
		out.print("window.location.href='infoShow/loginFailInfo.jsp?shopcode="+shopcode+"'\n");
		out.print("//-->\n");
		out.print("</script>\n");
  }
}
else if(actions != null && actions.equals("loginOut")) {
  loginOut loginOut = new loginOut();
  session.invalidate();
  flg = loginOut.delOpenId(request);
  if(flg) {
		out.print("<script language=\"javascript\">\n");
		out.print("<!--\n");
		out.print("window.location.href='login.jsp?shopcode="+shopcode+"&strOpenId="+strOpenId+"'\n");
		out.print("//-->\n");
		out.print("</script>\n");
  }
  else {
		out.print("<script language=\"javascript\">\n");
		out.print("<!--\n");
		out.print("window.location.href='infoShow/delFailInfo.jsp?shopcode="+shopcode+"'\n");
		out.print("//-->\n");
		out.print("</script>\n");
  }
}else if(actions != null && actions.equals("editpassword")) {
	 loginActionNew loginActionNew= new loginActionNew();
	  int con= loginActionNew.editpasswod(request);
	  if(con!=0) {
			out.print("<script language=\"javascript\">\n");
			out.print("alert('修改成功！请重新登录')<!--\n");
			out.print("window.location.href='login.jsp?shopcode="+shopcode+"&strOpenId="+strOpenId+"'\n");
			out.print("//-->\n");
			out.print("</script>\n");
	  }else {
			out.print("<script language=\"javascript\">\n");
			out.print("alert('您填写的车牌号或旧密码不对！')<!--\n");
			out.print("window.location.href='javascript:history.go(-1)'\n");
			out.print("//-->\n");
			out.print("</script>\n");
	  }
}
%>