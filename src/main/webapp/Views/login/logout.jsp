<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java" import="java.util.HashMap,javax.servlet.http.HttpSession"%>

<%
	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");

	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		autoLogin autoLogin = new autoLogin();
		CarId = autoLogin.judgeOpenId(strOpenId, shopcode);
		session.setAttribute("DUDUCHEWANG_CarId", CarId);
		
	}
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		System.out.println("============= �Զ���½�� CarId���ǿգ�����ת��login.jsp   shopcode:"+shopcode+"|strOpenId:"+strOpenId+"|");
		response.sendRedirect("login.jsp?shopcode=" + shopcode + "&strOpenId=" + strOpenId + "");
	}
%>
<html>
<head>
<title>�˳��˺�</title>
<script language="JavaScript">
	if(confirm("��ȷ��Ҫע����")) {
        window.location.href = "oauthLoginServlet?flagStr=loginOut";
	}else{
		window.close();
	}
</script>
</head>
<body>
</body>
</html>
