<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String mineShopCode = request.getParameter("mineShopCode");//用request得到
%>

<!DOCTYPE html>
<html>
<head>
	<meta content="initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<meta charset="UTF-8">
	<title>车险投保</title>
	<link rel="stylesheet" type="text/css" href="/styles/baoxian/css/mobiscroll_002.css"/>
	<link rel="stylesheet" type="text/css" href="/styles/baoxian/css/mobiscroll.css"/>
	<link rel="stylesheet" type="text/css" href="/styles/baoxian/css/mobiscroll_003.css"/>
	<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
	<link rel="stylesheet" type="text/css" href="/styles/baoxian/css/cheXianTouBao.css"/>
	<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/baoxian/js/mobiscroll_002.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/baoxian/js/mobiscroll_004.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/baoxian/js/mobiscroll.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/baoxian/js/mobiscroll_003.js" type="text/javascript" charset="utf-8"></script>
	<script src="/scripts/baoxian/js/mobiscroll_005.js" type="text/javascript" charset="utf-8"></script>

	<!-- oss -->
	<script src="https://www.promisejs.org/polyfills/promise-6.1.0.js"></script>
	<script type="text/javascript" src="/scripts/baoxian/oss/aliyun-oss-sdk.js"></script>
	<script type="text/javascript" src="/scripts/baoxian/oss/base64.js"></script>
	<script type="text/javascript" src="/scripts/baoxian/oss/app.js"></script>
	<%--<link rel="stylesheet" href="/Views/baoxian/cheXianTouBao/oss/bootstrap.min.css" />--%>
	<script src="/scripts/baoxian/js/cheXianTouBao.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<div id="wrap">
	<form action="/baoxiantijiao" method="post" >

		<!--顶部导航-->
		<div class="nav color_1">
			<div class="nav_left font_1">
				<label for="">服务顾问：</label>
				<select name="serviceConsultant">  <!--服务顾问-->
					<%--<option value="包西福">包西福</option>--%>
				</select>
			</div>
			<div class="nav_right font_1">
				<label for="">联盟总部：</label>
				<select name="unionHeadquarters">  <!--联盟总部-->
					<%--<option value="大师全国运营中心">大师全国运营中心</option>--%>
				</select>
			</div>
		</div>  <!--导航结束-->
		<!--主要内容-->
		<div class="content">

			<div class="nav_list">
				<!--头部的信息转换-->
				<div class="titles font_1 color_5">
					<span class="selects">车辆信息</span>
					<span>保险险种</span>
					<span>证件信息</span>
				</div>
				<!--头部信息对应的内容-->
				<div class="list">
					<!--车辆信息的内容-->
					<div class="one show">
						<ul class="car_list font_1 color_2">
							<!--车牌号码-->
							<li>
								<label for="">车牌号码：</label>
								<input type="text" name="car_number" id="car_number" value="" autocomplete="off"/>
								<!--弹出窗-->
								<div class="tishi">
									<%--<div class="xinxi">鲁Z00000</div>--%>

								</div>
							</li>
							<!--隐藏域-->
								<input type="hidden" value="" id="hiddens" name="customerId">
							<!--您的姓名-->
							<li>
								<label for="">您的姓名：</label>
								<input type="text" name="your_name" id="your_name" value="" class="your_name"/>
								<div class="ns">
									<input type="radio" name="sex" value="1" id="nan"/>男
									<input type="radio" name="sex" value="0"  id="nv"/>女
								</div>
							</li>
							<!--手机号码-->
							<li>
								<label for="">手机号码：</label>
								<input type="text" name="phone_number" id="phone_number" value="" class="phone_number"/>
							</li>
							<!--车辆识别代号-->
							<li>
								<label for="">车辆识别代号：</label>
								<input type="text" name="car_model" value="" id="daihao"style="width: 65%;"/>
							</li>
							<!--发动机号-->
							<li>
								<label for="">发动机号码：</label>
								<input type="text" name="engine_number" id="engine_number"value="" style="width: 65%;"/>
							</li>
							<!--车架号-->
							<!--<li>
                                <label for="">车架号：</label>
                                <input type="text" name="frame_number" value="" />
                            </li>-->
							<!--登记日期-->
							<li style="position: relative;">
								<label for="">注册日期：</label>
								<input type="text" name="registration_date" class="registration_date" id="registration_date"value=""/>
								<label for="registration_date"><img src="/files/baoxian/img/calendar.png" style="position: absolute;top:13px;right: 44px;" class="calendar"/></label>
							</li>
							<!--使用性质-->
							<li>
								<label for="">使用性质：</label>
								<select name="property" class="property">
									<option value="0201">家庭自用-非营运</option>
									<option value="0202">机关自用-非营运</option>
									<option value="0203">企业自用-非营运</option>
									<option value="0209">特殊用途-非营运</option>
									<option value="0104">出租客运-营运</option>
									<option value="0105">租赁客运-营运</option>
									<option value="0106">城市公交-营运</option>
									<option value="0107">公路客运-营运</option>
									<option value="0108">营业货运-营运</option>
									<option value="0109">特殊用途-营运</option>
								</select>
								<%--<input type="radio" name="property" value="非营运" checked="checked" style="margin-left: 260px;">非营运--%>
								<%--<input type="radio" name="property" value="营运" class="margin_left1"/>营运--%>
							</li>
						</ul>
					</div>
					<!--保险险种的内容-->
					<div class="two">
						<!--保险公司及险种选择-->
						<div class="insurance_company">
							<p class="color_3 font_1">保险公司及险种选择：</p>
							<div class="font_5 color_2 baoxian">
								<%--<div><input type="checkbox" name="xianzhong" id="wang_wang" value="" /><span>人民保险</span></div>--%>

							</div>
							<span class="xian"></span>
						</div>
						<!--每一条车险-->
						<ul>
							<!--必买险种-->
							<%--<li>--%>
							<!--投保交强险-->
							<%--<input type="checkbox" name="chexian" id="" value="" checked="checked"/><span class="font_1 color_4">投保交强险</span><span class="font_5 color_3">（必买险种）</span><br>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--代缴车船税-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" checked="checked"/><span class="font_1 color_4">代缴车船税</span><span class="font_5 color_3">（必买险种）</span>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>

							<!--车辆损失险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" checked="checked" class="chexian"/><span class="font_1 color_4">车辆损失险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 50px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="car_1" value="是" checked="checked" class="fou"/>是--%>
							<%--<input type="radio" name="car_1" value="否"/>否--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--第三责任险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" checked="checked" class="chexian"/><span class="font_1 color_4">第三责任险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 32px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="thred_1" value="是" checked="checked" class="fou"/>是--%>
							<%--<input type="radio" name="thred_1" value="否"/>否--%>
							<%--<label for="" style="margin-left: 84px;">赔偿限额：</label>--%>
							<%--<select name="third_liability_insurance">--%>
							<%--<option value="5万">5万</option>--%>
							<%--<option value="10万">10万</option>--%>
							<%--<option value="15万">15万</option>--%>
							<%--<option value="20万">20万</option>--%>
							<%--<option value="30万">30万</option>--%>
							<%--<option value="50万">50万</option>--%>
							<%--<option value="100万"selected="selected">100万</option> --%>
							<%--<option value="150万">150万</option>--%>
							<%--<option value="200万">200万</option>--%>
							<%--<option value="250万">250万</option>--%>
							<%--<option value="300万">300万</option>--%>
							<%--</select>--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--车上人员责任险(司机)-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" checked="checked" class="chexian"/><span class="font_1 color_4">车上人员责任险（司机）</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 32px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="driver_1" value="是" checked="checked"class="fou"/>是--%>
							<%--<input type="radio" name="driver_1" value="否" />否--%>
							<%--<label for="" style="margin-left: 84px;">赔偿限额：</label>--%>
							<%--<select name="driver">--%>
							<%--<option value="5千">5千</option>--%>
							<%--<option value="1万" selected="selected">1万</option>--%>
							<%--<option value="2万">2万</option>--%>
							<%--<option value="3万">3万</option>--%>
							<%--<option value="4万">4万</option>--%>
							<%--<option value="5万">5万</option>--%>
							<%--<option value="8万">8万</option>--%>
							<%--<option value="10万">10万</option>--%>
							<%--<option value="15万">15万</option>--%>
							<%--<option value="20万">20万</option>--%>
							<%--<option value="50万">50万</option>--%>
							<%--</select>--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--车上人员责任险(乘客)-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="111" checked="checked" class="chexian"/><span class="font_1 color_4">车上人员责任险（乘客）</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 32px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="passenger_1" value="是" checked="checked"class="fou"/>是--%>
							<%--<input type="radio" name="passenger_1" value="否" />否--%>
							<%--<label for="" style="margin-left: 84px;">赔偿限额：</label>--%>
							<%--<select name="passenger">--%>
							<%--<option value="5千">5千</option>--%>
							<%--<option value="1万" selected="selected">1万</option>--%>
							<%--<option value="2万">2万</option>--%>
							<%--<option value="3万">3万</option>--%>
							<%--<option value="4万">4万</option>--%>
							<%--<option value="5万">5万</option>--%>
							<%--<option value="8万">8万</option>--%>
							<%--<option value="10万">10万</option>--%>
							<%--<option value="15万">15万</option>--%>
							<%--<option value="20万">20万</option>--%>
							<%--<option value="50万">50万</option>--%>
							<%--</select>/座--%>
							<%--<div>--%>
							<%--<label for="" class="people_number">保障人数：</label>--%>
							<%--<input type="text" name="passenger_number" value="4"/>--%>
							<%--</div>--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--车身划痕损失险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" class="chexian"/><span class="font_1 color_4">车身划痕损失险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 32px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="body_scratch_1" value="是" class="fou"/>是--%>
							<%--<input type="radio" name="body_scratch_1" value="否" />否--%>
							<%--<label for="" style="margin-left: 84px;">赔偿限额：</label>--%>
							<%--<select name="body_scratch">--%>
							<%--<option value="2千" selected="selected">2千</option>--%>
							<%--<option value="5千">5千</option>--%>
							<%--<option value="1万">1万</option>--%>
							<%--<option value="2万">2万</option>--%>
							<%--</select>--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--玻璃单独破碎险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" class="chexian"/><span class="font_1 color_4">玻璃单独破碎险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 32px;">--%>
							<%--<!--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="shifou" value="是"/>是--%>
							<%--<input type="radio" name="shifou" value="否"/>否-->--%>
							<%--<label for="">选择类别：</label>--%>
							<%--<select name="broken_glass">--%>
							<%--<option value="国产玻璃" selected="selected">国产玻璃</option>--%>
							<%--<option value="进口玻璃">进口玻璃</option>--%>
							<%--</select>--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--全车盗抢险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" class="chexian"/><span class="font_1 color_4">全车盗抢险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 50px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="steal_1" value="是" class="fou"/>是--%>
							<%--<input type="radio" name="steal_1" value="否" />否--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!-- 自燃损失险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" class="chexian"/><span class="font_1 color_4">自燃损失险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 50px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="fire_1" value="是"class="fou"/>是--%>
							<%--<input type="radio" name="fire_1" value="否" />否--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--发动机涉水损失险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" class="chexian"/><span class="font_1 color_4">发动机涉水损失险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 50px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="engine_1" value="是"class="fou"/>是--%>
							<%--<input type="radio" name="engine_1" value="否" />否--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--机动车损失第三方特约险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" class="chexian"/><span class="font_1 color_4">机动车损失第三方特约险</span>--%>
							<%--<span class="xian" style="margin-top: 32px;"></span>--%>
							<%--</li>--%>
							<!--指定专修厂险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="" value="" class="chexian"/><span class="font_1 color_4">指定专修厂险</span>--%>
							<%--<span class="xian" style="margin-top: 32px;"></span>--%>
							<%--</li>--%>
							<!-- 新增设备损失险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="11" value="11" class="chexian"/><span class="font_1 color_4">新增设备损失险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 50px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="equipment_1" value="1"class="fou"/>是--%>
							<%--<input type="radio" name="equipment_1" value="0" />否--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--车上货物责任险-->
							<%--<li>--%>
							<%--<input type="checkbox" name="chexian" id="22" value="22" class="chexian"/><span class="font_1 color_4">车上货物责任险</span><br>--%>
							<%--<div class="font_5 color_2" style="margin-bottom: 32px;">--%>
							<%--<label for="">不计免赔：</label>--%>
							<%--<input type="radio" name="goods_1" value="1"class="fou"/>是--%>
							<%--<input type="radio" name="goods_1" value="0" />否--%>
							<%--<label for="" style="margin-left: 84px;">赔偿限额：</label>--%>
							<%--<select name="goods">--%>
							<%--<option value="1万" selected="selected">1万</option>--%>
							<%--<option value="2万">2万</option>--%>
							<%--<option value="5万">5万</option>--%>
							<%--<option value="10万">10万</option>--%>
							<%--<option value="20万">20万</option>--%>
							<%--</select>--%>
							<%--</div>--%>
							<%--<span class="xian"></span>--%>
							<%--</li>--%>
							<!--精神损害抚慰金责任险-->
							<%--<li>--%>
								<%--<input type="checkbox" name="chexian_20" id="" value="33" class="chexian"/><span class="font_1 color_4">精神损害抚慰金责任险</span><br>--%>
								<%--<div class="font_5 color_2" style="margin-bottom: 32px;">--%>
									<%--<label for="">不计免赔：</label>--%>
									<%--<input type="radio" name="bjmp_20" value="1"class="fou"/>是--%>
									<%--<input type="radio" name="bjmp_20" value="0" />否--%>
									<%--<label for="" style="margin-left: 84px;">赔偿限额：</label>--%>
									<%--<select name="pcxe_20" disabled="disabled">--%>
										<%--<option value="21" selected="selected">1万</option>--%>
										<%--<option value="22">2万</option>--%>
										<%--<option value="23">3万</option>--%>
										<%--<option value="24">4万</option>--%>
										<%--<option value="25">5万</option>--%>
									<%--</select>--%>
								<%--</div>--%>
								<%--<span class="xian"></span>--%>
							<%--</li>--%>
						</ul>
					</div>
					<!--保险险种的内容-->
					<div class="three">
						<ul>
							<!--行驶证图片-->
							<li>
								<p class="font_1 color_3"><span>1</span>行驶证图片：</p>
								<div class="picture">
									<input type="file" name="" class="filepath" id="file"/>
									<input type="hidden" name="driving_1" value="" class="tupian">
									<img src="/files/baoxian/img/add_picture.png" class="img1"/>
									<div class="imgs">
										<img src="" class="img2 xing_shi"/>
									</div>
									<p class="font_5 color_6" style="width: 100%;text-align: center;margin-top: 57px;">（支持jpg格式，最大限制5M）</p>
								</div>
								<div class="picture">
									<input type="file" name="" class="filepath"/>
									<input type="hidden" name="driving_2" value="" class="tupian">
									<img src="/files/baoxian/img/add_picture.png" class="img1"/>
									<div class="imgs">
										<img src="" class="img2 xing_shi"/>
									</div>
									<p class="font_5 color_6" style="width: 100%;text-align: center;margin-top: 57px;">（支持jpg格式，最大限制5M）</p>
								</div>
							</li>
							<!--身份证图片-->
							<li>
								<p class="font_1 color_3"><span>2</span>身份证图片：</p>
								<div class="picture">
									<input type="file" name="" class="filepath" value=""/>
									<input type="hidden" name="filepath_1" value="" class="tupian">
									<img src="/files/baoxian/img/add_picture.png" class="img1"/>
									<div class="imgs">
										<img src="" class="img2 shen_fen"/>
									</div>
									<p class="font_5 color_6" style="width: 100%;text-align: center;margin-top: 57px;">（支持jpg格式，最大限制5M）</p>
								</div>
								<div class="picture">
									<input type="file" name="" class="filepath" value=""/>
									<input type="hidden" name="filepath_2" value="" class="tupian">
									<img src="/files/baoxian/img/add_picture.png" class="img1"/>
									<div class="imgs">
										<img src="" class="img2 shen_fen"/>
									</div>
									<p class="font_5 color_6" style="width: 100%;text-align: center;margin-top: 57px;">（支持jpg格式，最大限制5M）</p>
								</div>
							</li>
							<!--附件-->
							<!--<li>
                                <p class="font_1 color_3"><span>3</span>上传附件：</p>
                                <div class="picture">
                                    <input type="file" name="filepath" value=""/>
                                    <img src="/files/baoxian/img/add_picture.png" class="img1"/>
                                    <p class="font_5 color_6" style="width: 100%;text-align: center;margin-top: 57px;">（请压缩文件后上传，最大限制20M）</p>
                                </div>
                            </li>-->
						</ul>
						<!--底部按钮-->
						<div class="button">
							<span class="tijiao">提交</span>
							<script type="text/javascript">
                                $(".button").on("click",function(){
                                    $("form").submit()
                                })

							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<input type="hidden" name="mineShopCode" id="mineShopCode" value="<%=mineShopCode%>" class="phone_number"/>
</body>
</html>
