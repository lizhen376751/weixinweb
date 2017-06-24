<%@ page language="java" pageEncoding="utf-8" %>


<html>

</html>

<html>
<head>


<meta content="yes" name="apple-mobile-web-app-capable"/>
<meta content="black" name="apple-mobile-web-app-status-bar-style"/>
<meta content="telephone=no" name="format-detection"/>
<title>嘟嘟车网</title>
<meta name="keywords" content="keyword ..."/>
<meta name="Description" content="description ..."/>
<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
<script language="JavaScript">


    //登录
    function login() {
        window.location.href = "/shopweixinServlet?serviceType=login";
    }
    //创建菜单
    function creatMenu() {
        var regbtns = $(".regbtn");
        var a = '';
        for (var i = 0; i < regbtns.length; i++) {
            a += '/' + $(regbtns[i]).val()
        }
        console.log(a);
        $.ajax({
            type    : 'GET',
            url     : '/createMenu' + a,

            success:function(jsondata){
                var json = JSON.parse(jsondata);
                alert(jsondata);
            },
            error:function(eee){
                 alert("我有点懵,您稍后再试!");
            }
        });
//        window.location.href = "/createMenu" + a;
    }
    $(function () {
        $("#file").on("change",function(){
            var file = this.files[0];
            //判断类型是不是图片
            if(!/image\/\w+/.test(file.type)){
                alert("请确保文件为图像类型");
                return false;
            }
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function(e){
                var base = this.result; //就是base64
                var n = base.indexOf("base64,");
                var base64 = base.substring(n+7);
                $.ajax({
                    type    : 'POST',
                    url     : '/getCommonAjax2',
                    data : {
                        fromflag : 'drivinglicense',
                        bodys : base64
                    },
                    success:function(jsondata){
                        var json = JSON.parse(jsondata);
                        console.log(json);
                        alert("返回的数据为:=="+json)
                    },
                    error:function(eee){
                        alert("我有点懵,您稍后再试!");
                    }
                });
            }
        })
    })

</script>
</head>
<body>

<form name="form1" method="post" action="checkAction.jsp">
    <div class="regform">
        <ul>

            <li class="btnli"><input type="button" class="reg" value="登录" onclick="login()"/></li>
            <li class="btnli">店铺编码:<input name="type" class="regbtn" value=""/></li>
            <li class="btnli">菜单类型:
                <select name="code" class="regbtn">
                    <option value="shop">店管家微信</option>
                    <option value="lianmeng">联盟微信</option>
                    <option value="yilubang">易路邦</option>
                </select>
            </li>
            <li class="btnli">appid:<input name="appid" class="regbtn" value=""/></li>
            <li class="btnli">appsecret:<input name="appSecret" class="regbtn" value=""/></li>
            <li class="btnli">服务器地址:
                <select name="code" class="regbtn">
                    <option value="lizhen.ngrok.">本地测试</option>
                    <option value="lm.wx.dev.duduchewang.">测试环境</option>
                    <option value="wx.pre.duduchewang.">预生产环境</option>
                    <option value="shop">随机环境</option>
                </select>
            </li>
            <li class="btnli">服务器域名:
                <select name="yuming" class="regbtn">
                    <option value="cn">cn</option>
                    <option value="com">com</option>
                    <option value="cc">cc</option>
                </select>

            </li>
            <li class="btnli"><input type="button" class="regbtns" value="创建菜单" onclick="creatMenu()"/></li>
        </ul>
    </div>

    <input type="file" id="file">
</form>
</body>
</html>

