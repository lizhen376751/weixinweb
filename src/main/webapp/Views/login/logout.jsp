<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page language="java" %>

<%
    String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
    String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
    String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");

//	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
//		autoLogin autoLogin = new autoLogin();
//		CarId = autoLogin.judgeOpenId(strOpenId, shopcode);
//		session.setAttribute("DUDUCHEWANG_CarId", CarId);
//
//	}
//	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
//		System.out.println("============= �Զ���½�� CarId���ǿգ�����ת��login.jsp   shopcode:"+shopcode+"|strOpenId:"+strOpenId+"|");
//		response.sendRedirect("login.jsp?shopcode=" + shopcode + "&strOpenId=" + strOpenId + "");
//	}
%>
<html>
<head>
    <script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/scripts/login/checkLogin.js" type="text/javascript" charset="utf-8"></script>
    <title>�˳��˺�</title>
    <script language="JavaScript">
        if (confirm("��ȷ��Ҫע����")) {
            window.location.href = "oauthLoginServlet?flagStr=loginOut";
        } else {
            window.close();
        }
    </script>
</head>
<body>
<input type="hidden" id="shopcode" name="shopcode" value="<%=shopcode %>">
<input type="hidden" id="strOpenId" name="strOpenId" value="<%=strOpenId %>">
<input type="hidden" id="CarId" name="CarId" value="<%=CarId %>">
</body>
</html>
