<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page language="java" import="com.weixin.login.autoLogin"%>
<%@ page language="java" import="java.util.ArrayList,java.util.HashMap,javax.servlet.http.HttpSession,java.text.DecimalFormat"%>
<%@ page language="java" import="cn.duduchewang.weixin.common.GetBuyRecord"%>
<%

	String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
	String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
	String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
	
	System.out.println("=============xiaoFeiList.jsp=== CarId:|"+CarId+"|");
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		autoLogin autoLogin = new autoLogin();
		CarId = autoLogin.judgeOpenId(strOpenId, shopcode);
		session.setAttribute("DUDUCHEWANG_CarId", CarId);
		System.out.println("=============xiaoFeiList.jsp=== �Զ���½��  CarId:|"+CarId+"|");
	}
	
	if (CarId == null || "null".equals(CarId) || "".equals(CarId)) {
		System.out.println("=============xiaoFeiList.jsp=== �Զ���½�� CarId���ǿգ�����ת��login.jsp   shopcode:"+shopcode+"|strOpenId:"+strOpenId+"|");
		response.sendRedirect("../login.jsp?shopcode=" + shopcode + "&strOpenId=" + strOpenId + "");
	}



String top = (String)request.getParameter("top");
if(top==null){ top="1"; }

GetBuyRecord GetBuyRecord = new GetBuyRecord();
ArrayList listDJ = GetBuyRecord.getServiceListByLmcodeAndCarNo(shopcode,CarId,top);
DecimalFormat form = new DecimalFormat("0.00");
%>
<html>			
<head>
    <!-- <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" /> -->
    <!-- <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" />  -->
    <title>���Ѽ�¼</title>
    <meta name="keywords" content="keyword ..." />
    <meta name="Description" content="description ..." />
    <!--<link href="favicon.ico" rel="shortcut icon" />-->
    <!-- <link href="../css/global.css" rel="stylesheet" type="text/css" /> -->
    <link rel="stylesheet" type="text/css" href="../css/weix.css"/>
	<link rel="stylesheet" type="text/css" href="../css/xiaoFeiJiLu.css"/>
    <script type="text/javascript" src="../js1/iscroll.js"></script>
</head>
<body>
<div class="secretarylist1">
<div id="wrapper" style="height:100%;overflow:auto;">
			<div id="scroller">
				<div id="pullDown" style="width:100%;text-align:center;font-size:32px">
					<span class="pullDownIcon"></span><span class="pullDownLabel">����ˢ��...</span>
				</div>
				
<%
	if(listDJ.size()>5){ %>
	<ul id="thelist"><%
	}else{ %>
	<ul id="thelist" style="height:100%;">
	<% }
	
	
	
	if(listDJ.size()>0){
	for(int j=0;j<listDJ.size();j++){
		HashMap hmDJ=(HashMap)listDJ.get(j);
		String kprq = (String)hmDJ.get("kprq");
		String wxpingzheng = (String)hmDJ.get("wxpingzheng");
		String GongLiShu = (String)hmDJ.get("GongLiShu");
		GongLiShu = GongLiShu==null||"null".equals(GongLiShu)||"".equals(GongLiShu) ? "0" : GongLiShu;
		String yingshou = (String)hmDJ.get("yingshou");
		String zhifufangshi = (String)hmDJ.get("zhifufangshi");
		String shishou = (String)hmDJ.get("shishou");
		String shishou2 = (String)hmDJ.get("shishou2");
		String shopcodeTrue = (String)hmDJ.get("shopcode");
		String zhifufangshi_jine = (String)hmDJ.get("zhifufangshi_jine");
		String shopName = hmDJ.get("shopName").toString();
		String evaluateFlag = hmDJ.get("evaluateFlag").toString();
		

		if(shishou == null || "null".equals(shishou) || "".equals(shishou)) shishou = "0";
		if(shishou2 == null || "null".equals(shishou2) || "".equals(shishou2)) shishou2 = "0";

		float fshishou = Float.parseFloat(shishou);
		float fshishou2 = Float.parseFloat(shishou2);
		/* String xmInfo = GetBuyRecord.getItemRecord(shopcodeTrue,wxpingzheng);
		String spInfo = GetBuyRecord.getGoodsRecord(shopcodeTrue,wxpingzheng);
		String strRecord = xmInfo+spInfo; */
		String strZhiFuName = GetBuyRecord.getZhiFuFangShi(zhifufangshi)+"("+zhifufangshi_jine+")";
		
		ArrayList xmAndSpList = GetBuyRecord.getItemAndSpList(shopcodeTrue,wxpingzheng);
		
		String jiShiName = GetBuyRecord.getSalerName(shopcodeTrue, GetBuyRecord.getWeiXiuYuanCodesStr(shopcodeTrue, wxpingzheng));
		
%>
<li>
	<div class="detail_1">
		<div class="top_1 font_2">
			<div class="left_11">���ڣ�<%=kprq%></div>
			<div class="right_11">��������<%=GongLiShu%>km</div>
		</div><!--top����-->
		<div class="middle_1 font_1">
			<div class="left_1">������Ŀ��</div>
			<div class="right_1">
				<%
					for(int i=0;i<xmAndSpList.size();i++) {
						HashMap hm = (HashMap)xmAndSpList.get(i);
						String itemName = (String)hm.get("itemName");
						String sl = (String)hm.get("sl");
						sl = sl==null||Double.parseDouble(sl)==0||"".equals(sl) ? "" : "("+sl+")";
				%>
				<div><span><%=i+1%></span><p><%=itemName+sl %></p></div>
				<%}%>
			</div>
			<div style="float: left;margin-top: 29px;">
				<span style="margin-left: 26px;">ʩ����ʦ��</span>
				<span><%=jiShiName %></span>
			</div>
		</div><!--middle����-->
		<div class="bottom_1 font_1">
			<div>
				<span style="margin-left: 26px;">ʩ�����̣�</span>
				<span><%=shopName%></span>
			</div>
			<div>
				<span style="margin-left: 26px;">���ʽ��</span>
				<span class="red"><%=strZhiFuName%></span>
				<span style="margin-left: 60px;">����Ӧ�գ�</span>
				<span class="red">��<%=yingshou%></span>
			</div>
			<div>
				<span style="margin-left: 26px;">����ʵ�գ�</span>
				<span class="red">��<%=form.format(fshishou+fshishou2)%></span>
			</div>
		</div><!--bottom����-->
		<!--���۰�ť-->
		<% if("1".equals(evaluateFlag)){ %>
		<div class="yipingjia font_1">������</div>
		<% }else{ %>
		<div class="pingjia font_1">ȥ����</div>
		<% } %>
	</div>
</li>
<%}
}else{%>
<li>
 <div style="width:100%;height:100%;margin-top:6%;" align="center"><font size="6">�����Ѽ�¼��Ϣ��</font></div>
</li>
<% } %>
</ul>
        <div id="pullUp" style="width:100%;text-align:center;font-size:32px">
			<span class="pullUpIcon"></span><span class="pullUpLabel">����ˢ��...</span>
		</div>
	</div>
</div>
</div>
<form name="form1" method="post" action="xiaoFeiList.jsp">
<input type="hidden"  name="shopcode" value="<%=shopcode%>">
<input type="hidden"  name="strOpenId" value="<%=strOpenId%>">
<input type="hidden"  name="top" id="page1">
</form>
</body>
<script type="text/javascript">
	var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset, generatedCount = 0;

	/**
	 * ��ʼ��iScroll�ؼ�
	 */
	function loaded() {
		pullDownEl = document.getElementById('pullDown');
		pullDownOffset = pullDownEl.offsetHeight;
		pullUpEl = document.getElementById('pullUp');
		pullUpOffset = pullUpEl.offsetHeight;
		myScroll = new iScroll(
				'wrapper',
				{
					scrollbarClass : 'myScrollbar', /* ��Ҫ��ʽ */
					useTransition : false,
					topOffset : pullDownOffset,
					onRefresh : function() {
						if (pullDownEl.className.match('loading')) {
							pullDownEl.className = '';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '����ˢ��...';
						} else if (pullUpEl.className.match('loading')) {
							pullUpEl.className = '';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '�������ظ���...';
						}
					},
					onScrollMove : function() {
						if (this.y > 5 && !pullDownEl.className.match('flip')) {
							pullDownEl.className = 'flip';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '���ֿ�ʼ����...';
							this.minScrollY = 0;
						} else if (this.y < 5
								&& pullDownEl.className.match('flip')) {
							pullDownEl.className = '';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '����ˢ��...';
							this.minScrollY = -pullDownOffset;
						} else if (this.y < (this.maxScrollY - 5)
								&& !pullUpEl.className.match('flip')) {
							pullUpEl.className = 'flip';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '���ֿ�ʼ����...';
							this.maxScrollY = this.maxScrollY;
						} else if (this.y > (this.maxScrollY + 5)
								&& pullUpEl.className.match('flip')) {
							pullUpEl.className = '';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '�������ظ���...';
							this.maxScrollY = pullUpOffset;
						}
					},
					onScrollEnd : function() {
						if (pullDownEl.className.match('flip')) {
							pullDownEl.className = 'loading';
							pullDownEl.querySelector('.pullDownLabel').innerHTML = '������...';
							pullDownAction(); // Execute custom function (ajax call?)
						} else if (pullUpEl.className.match('flip')) {
							pullUpEl.className = 'loading';
							pullUpEl.querySelector('.pullUpLabel').innerHTML = '������...';
							pullUpAction();
						}
					}
				});
		setTimeout(function() {
			document.getElementById('wrapper').style.left = '0';
		}, 800);
	}
	//��ʼ����iScroll�ؼ� 
	document.addEventListener('touchmove', function(e) {e.preventDefault();}, false);
	document.addEventListener('DOMContentLoaded', loaded, false);
	//׼�������� 
	//��Ӧ��ִ���� 
	function pullDownAction() { // ����ˢ��
		window.location.reload();
	}
	//��ʼ��ҳ��Ϊ2
	function pullUpAction() { //�������ظ���
		var i = <%=top%>;
		//window.location.reload();
		var page3 = i + 1; // ÿ����һ��ҳ���һ�� ���ͱ�����һҳ��һҳ��
		addCondition(page3);
		// window.location.reload();
		// ����ajax ��2����ȥ���ߺ�̨������һ�κ�̨Ҫ��һҳ���ݣ���Ȼ ������崫ʲô���ø���̨��ϣ�
		myScroll.refresh();// <-- Simulate network congestion, remove setTimeout from production!
	}
	//��������
	function addCondition(page3) {
		document.getElementById("page1").value = page3;
		document.form1.submit();

	}
</script>
</html>
