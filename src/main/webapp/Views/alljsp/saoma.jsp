<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,
	javax.servlet.http.HttpSession"%>
<%@ page language="java"
	import="com.weixin.action.MmallAction,
	org.xwd.weixin.util.PastUtil,java.util.Map,
	com.vangdo.weixin.common.FormatUtil,
	cn.duduchewang.weixin.common.GetShopInfo,
	cn.duduchewang.weixin.common.weixinpay,
	cn.duduchewang.weixin.common.util.TenpayUtil"%>
<%
	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode"); //���̱���
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");//openid
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");//���ƺ�
	String appId = "";
	String timestamp = "";
	String nonceStr = "";
	String signature = "";
	//�ж��Ƿ��¼
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		response.sendRedirect("login.jsp?shopcode=" + shopcode+ "&strOpenId=" + strOpenId + "");
	} else {
		PastUtil pastutil = new PastUtil();
		Map jsonStr = pastutil.getParam(shopcode, request);
		if (jsonStr != null && jsonStr.size() > 0) {
			timestamp = jsonStr.get("timestamp").toString();
			nonceStr = jsonStr.get("nonceStr").toString();
			signature = jsonStr.get("signature").toString();
			appId=jsonStr.get("appid").toString();
		}
	}
%>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta content="target-densitydpi=320,width=640,user-scalable=no"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<title>ɨһɨ</title>
<meta name="keywords" content="keyword ..." />
<meta name="Description" content="description ..." />
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	wx.config({
	    debug: false, 
	    appId: '<%=appId%>', 
	    timestamp:'<%=timestamp%>', 
	    nonceStr: '<%=nonceStr%>', 
	    signature: '<%=signature%>',
		jsApiList : ['scanQRCode']
	});
	wx.ready(function() {
		wx.scanQRCode({
			needResult : 1, // Ĭ��Ϊ0��ɨ������΢�Ŵ���1��ֱ�ӷ���ɨ������
			scanType : [ "qrCode", "barCode" ], // ����ָ��ɨ��ά�뻹��һά�룬Ĭ�϶��߶���
			success : function(res) {
				var result = res.resultStr; // ��needResult Ϊ 1 ʱ��ɨ�뷵�صĽ��
				window.location.href = result;
			}
		});
	});
</script>
</head>
<body>
</body>
</html>

