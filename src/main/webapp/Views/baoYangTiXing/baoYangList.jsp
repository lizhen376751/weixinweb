<%@ page contentType="text/html; charset=GBK" language="java" %>

<%@ page language="java"
         import="com.dudu.weixin.service.AutoLoginService,com.dudu.weixin.service.BaoYangTiXingService,java.text.DecimalFormat,java.util.ArrayList" %>
<%@ page language="java" import="java.util.HashMap
								" %>
<%
    String shopcode = (String) session.getAttribute("DUDUCHEWANG_shopcode");
    String strOpenId = (String) session.getAttribute("DUDUCHEWANG_OpenId");
    String CarId = (String) session.getAttribute("DUDUCHEWANG_CarId");
    System.out.println("=============baoYangList.jsp=== CarId:|" + CarId + "|");
    //TODO ��¼�ж�





    String top = (String) request.getParameter("top");
    if (top == null) {
        top = "1";
    }

//    BaoYangTiXingService baoYang = new BaoYangTiXingService();
//    ArrayList listDJ = baoYang.getBaoYangListByLmcodeAndCarNo(shopcode, CarId, top);
    ArrayList listDJ = null;
//    DecimalFormat form = new DecimalFormat("0.00");
%>
<html>
<head>
    <!-- <meta content="target-densitydpi=320,width=640,user-scalable=no" name="viewport" />
    <meta content="yes" name="apple-mobile-web-app-capable" /> 
    <meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
    <meta content="telephone=no" name="format-detection" />  -->
    <meta charset="UTF-8">
    <title>��������</title>
    <meta name="keywords" content="keyword ..."/>
    <meta name="Description" content="description ..."/>

    <link href="/styles/weix.css" rel="stylesheet" type="text/css"/>
    <link href="/styles/shopbaoyangtixing/baoyanglist.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/scripts/jquery-1.12.1.min.js"></script>
    <script type="text/javascript" src="/scripts/baoYangTiXing/iscroll.js"></script>
    <script type="text/javascript" src="/scripts/baoYangTiXing/baoYangList.js"></script>
    <script  type="text/javascript"  src="/scripts/main.js"></script>
</head>
<body>

<%--��ҳ����Ч��--%>
<div id="loading">
    <img src="/files/loading.gif"  alt="loading.." />
</div>
<div class="secretarylist1">
    <div id="wrapper" style="height:100%; overflow:auto; ">
        <div id="scroller">
            <div id="pullDown" style="width:100%;text-align:center;font-size:32px">
                <span class="pullDownIcon"></span><span class="pullDownLabel">����ˢ��...</span>
            </div>
<%--
            <%
                if (listDJ.size() > 5) { %>
            <ul id="thelist" class="margin_auto"><%
	}else{ %>--%>
                <ul id="thelist" class="margin_auto" style="height:100%;">
                    <%--<% }%>--%>


                    <%--<%--%>
                        <%--if (listDJ.size() > 0) {--%>
                            <%--for (int j = 0; j < listDJ.size(); j++) {--%>
                                <%--HashMap hmDJ = (HashMap) listDJ.get(j);--%>

                                <%--String CustomerDemand_DateBegin = (String) hmDJ.get("CustomerDemand_DateBegin");--%>
                                <%--String shopcodetr = (String) hmDJ.get("shopcode");--%>
<%--//                                String shopName = autoLogin.getDuduShopName(shopcodetr);--%>
                                <%--String shopName = "��������";--%>
                                <%--String CustomerDemand_Memo = (String) hmDJ.get("CustomerDemand_Memo");--%>

                                <%--String model_name1 = (String) hmDJ.get("model_name1");--%>
                                <%--String model_name2 = (String) hmDJ.get("model_name2");--%>
                                <%--String xqlx = model_name1 + "/" + model_name2;--%>

                                <%--String CustomerDemand_Level = (String) hmDJ.get("CustomerDemand_Level");--%>
                                <%--String CustomerDemand_LevelName = "";--%>
                                <%--if (CustomerDemand_Level.equals("1")) {--%>
                                    <%--CustomerDemand_LevelName = "<span class='by_bgcolorfjj by_width'>�ǳ�����</span>";--%>
                                <%--}--%>
                                <%--if (CustomerDemand_Level.equals("2")) {--%>
                                    <%--CustomerDemand_LevelName = "<span class='by_bgcolorjj by_width'>����</span>";--%>
                                <%--}--%>
                                <%--if (CustomerDemand_Level.equals("3")) {--%>
                                    <%--CustomerDemand_LevelName = "<span class='by_bgcolorpt by_width'>��ͨ</span>";--%>
                                <%--}--%>

                    <%--%>--%>


                    <%--<li>--%>
                        <%--<div class="by_center by_top"><span class="by_date font_2"><%=CustomerDemand_DateBegin %></span>--%>
                            <%--<span class="by_name font_2">&nbsp;&nbsp;<%=shopName%></span></div>--%>
                        <%--<div class="by_center font_1 by_cenulcolor"> �������ͣ�<%=xqlx%>--%>
                        <%--</div>--%>
                        <%--<div class="by_center font_1 by_cenulcolor"> ��<i--%>
                                <%--style="visibility: hidden">����</i>�飺<%=CustomerDemand_Memo%>--%>
                        <%--</div>--%>
                        <%--<div class="by_center font_1 by_cenulcolor"> ״<i--%>
                                <%--style="visibility: hidden">����</i>̬��<%=CustomerDemand_LevelName %>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<%--%>
                        <%--}--%>
                    <%--} else {--%>
                    <%--%>--%>
                    <%--<li>--%>
                        <%--<div style="width:100%;height:100%;margin-top:6%;" align="center"><font size="6">�ޱ���������Ϣ��</font>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<% } %>--%>
                </ul>
                <div id="pullUp" style="width:100%;text-align:center;font-size:32px">
                    <span class="pullUpIcon"></span><span class="pullUpLabel">����ˢ��...</span>
                </div>
        </div>
    </div>
</div>
<form name="form1" method="post" action="baoYangList.jsp">
    <input type="hidden" name="shopcode" value="<%=shopcode%>">
    <input type="hidden" name="strOpenId" value="<%=strOpenId%>">
    <input type="hidden" name="top" id="page1" value="<%=top%>">
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
                scrollbarClass: 'myScrollbar', /* ��Ҫ��ʽ */
                useTransition: false,
                topOffset: pullDownOffset,
                onRefresh: function () {
                    if (pullDownEl.className.match('loading')) {
                        pullDownEl.className = '';
                        pullDownEl.querySelector('.pullDownLabel').innerHTML = '����ˢ��...';
                    } else if (pullUpEl.className.match('loading')) {
                        pullUpEl.className = '';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '�������ظ���...';
                    }
                },
                onScrollMove: function () {
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
                onScrollEnd: function () {
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
        setTimeout(function () {
            document.getElementById('wrapper').style.left = '0';
        }, 800);
    }
    //��ʼ����iScroll�ؼ�
    document.addEventListener('touchmove', function (e) {
        e.preventDefault();
    }, false);
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
