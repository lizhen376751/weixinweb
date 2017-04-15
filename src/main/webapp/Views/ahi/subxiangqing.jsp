<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=GBK" language="java"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>AHI</title>
		<link rel="stylesheet" type="text/css" href="/styles/weix.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/ahi/ahi.css"/>
		<link rel="stylesheet" type="text/css" href="/styles/ahi/swiper.min.css"/>
		<script type="text/javascript" src="/scripts/ahi/jquery-1.12.1.min.js"></script>
		<script src="/scripts/ahi/swiper.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/scripts/ahi/index.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">

        var plateNumber = decodeURI(decodeURI('<%=request.getParameter("plateNumber")%>'));
        var ids = <%=request.getParameter("id")%>;
        $(document).ready(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:'checkAction.jsp',
                data:{'actions':'queryCarPointOne','plateNumber':encodeURI(plateNumber)},
                success:function(data){
                    data = eval('('+data+')');
                    if(data.length>0){
                        for(var i = 0; i < data.length; i++){
                            $.ajax({
                                type:"post",
                                async:false,
                                dataType:"text",
                                url:'checkAction.jsp',
                                data:{'actions':'queryCarPointTwo','plateNumber':encodeURI(plateNumber),'id':data[i].id,'ratio':'100'},
                                success:function(sdata) {
                                    sdata = eval('(' + sdata + ')');
                                    if (sdata.length > 0) {
                                        data[i]["result"] = sdata;
                                    }
                                }
                            });
                        }
                        showData(data);
                    }else{
                        alert("δ��ѯ������ "+plateNumber+" �÷����ݣ�");
                    }
                }
            });
        });

        function showData(jsonp){
            if(jsonp.length>0){
                for(var i=0;i<jsonp.length;i++){

                    //���ϵͳ
                    var subhtml="";
                    var translate=jsonp[i].result;

                    for(var aa=0;aa<translate.length;aa++){
                        var sumq = 0;  //��¼������
                        var html_2="";//��Ӿ������
                        var subDescribeList=translate[aa].resultQuestionDescribeList;
                        for(bb=0;bb<subDescribeList.length;bb++){
                            if(subDescribeList[bb].point<34&&subDescribeList[bb].point>0){
                                sumq++;
                            }

                            //var resulthtml="";  //�ж�Ϊ��⣬����,�ϲ

                            var inspectionDetailedDescription = subDescribeList[bb];//ȡ�ø���������Ϣ

                            if(subDescribeList[bb].point==0){     //���ݼ������ж�
                                html_2+="<div class='lianghao'>"+
                                    "<span class='subxitong title_color font_1'>"+subDescribeList[bb].name+"</span>"+
                                    "<span class='radius radiuscoloe_1'></span>"+
                                    "<span class='subright title_color font_1'>δ���</span>"+
                                    "</div>	"+
                                    "<hr class='sub_solid' />";  //<!--����-->
                            }else if(subDescribeList[bb].point<34){

                                html_2+="<a href='thirlyIndex.jsp?inspectionDetailedDescription="+encodeURI(encodeURI(JSON.stringify(inspectionDetailedDescription)))+"'>"+
                                    "<div class='lianghao'>"+
                                    "<span class='subxitong title_color font_1'>"+subDescribeList[bb].name+"</span>"+
                                    "<span class='radius radiuscoloe_2'></span>"+
                                    "<span class='subright title_color font_1 '><div class='bgjc'>�ϲ�</div></a></span>"+
                                    "</div>"+
                                    "</a>"+
                                    "<hr class='sub_solid' />";  //<!--����-->
                            }else{
                                html_2+="<div class='lianghao'>"+
                                    "<span class='subxitong title_color font_1'>"+subDescribeList[bb].name+"</span>"+
                                    "<span class='radius radiuscoloe_3'></span>"+
                                    "<span class='subright title_color font_1'>����</span>"+
                                    "</div>	"+
                                    "<hr class='sub_solid' />";  //<!--����-->

                            };



							/*  html_2+="<div class='lianghao'>"+
							 "<span class='subxitong title_color font_1'>"+subDescribeList[bb].name+"</span>"+
							 resulthtml+
							 "</div>	"+
							 "<hr class='sub_solid' />"; */  //<!--����-->
                        };

                        //���������
                        var html_1="";
                        if(sumq==0){
                            html_1+="<span class='liangh font_0'>״̬����</span>";
                        }else{
                            html_1+="<span class='jingbao font_0'>"+sumq+"������</span>";
                            // $("#jsubnumlie_li"+translate[aa].id+"").show();   //����������  Ĭ��Ϊչ��

                        }

                        subhtml+="<li class='xqburder_li numlie_li' onClick='Togglezs(this);'>"+
                            "<span class='xitong numxitong font_1'>"+
                            "<img class='subicon_img' src='"+(translate[aa].logoPath?translate[aa].logoPath+"?x-oss-process=image/resize,m_fixed,h_76,w_76":"/files/ahi/icon/icon_4.png")+"'/>"+
                            "<span class='subicon_font'>"+translate[aa].name+"</span>"+
                            "</span>"+
                            html_1+   //����or״̬����
                            "<span class='submiaos font_1'  >չ��<img class='zk_img' src='/files/ahi/zk.png' /> </span>"+
                            "</li>"+
                            "<li class='subnumlie_li' id='jsubnumlie_li"+translate[aa].id+"' style='display:none'>"+
                            html_2+
                            "</li>"    //��Ӷ���չ��ҳ��
                    }



                    $(".swiper-wrapper").append("<div class='swiper-slide'>"
                        +"<div class='center_1'>"
                        +"<div class='num'>"
                        +"<p class='banner_img'><img src='"+(jsonp[i].logoPath?jsonp[i].logoPath+"?x-oss-process=image/resize,m_fixed,h_264,w_230":"/files/ahi/icon/icon_4.png")+"'/></p>"
                        +"<p class='num_font'>"+jsonp[i].point+"</p>"
                        +"</div>"
                        +"</div>"
                        +"<div style='width:100%'  class='bor_div'>"
                        +"<ul class='lie_li'>"
                        + subhtml
                        +"</ul>"
                        +"</div>"
                        +"</div>" );



                }

            }


            //����������  Ĭ��Ϊչ��

            $(".jingbao").each(function(){
                var t=$(this).val();
                var z= /^[0-9]*$/;   //�ж��Ƿ��������
                if(t.indexOf(z)){
                    $(this).parent().next().show();   //����������  Ĭ��Ϊչ��
                };

            });

            //swiper  ���
            var swiper = new Swiper('.swiper-container', {
                pagination: '.swiper-pagination',
                nextButton: '.swiper-button-next',
                prevButton: '.swiper-button-prev',
                slidesPerView: 1,
                paginationClickable: true,
                spaceBetween: 30,
                loop: true
            });

            //����swiperĬ����ʾ�ڼ�ҳ����
            if(jsonp.length>0){
                for(var ii=0;ii<jsonp.length;ii++){
                    if(ids==jsonp[ii].id){
                        //$('.swiper-pagination span').eq(cc).trigger('click');
                        swiper.slideTo(ii+1, 0, false);   //swiper  Ĭ����ʾ�ڼ�ҳ
                        //��һ��������ָ���л���ĳһҳ��������
                        //�ڶ����������л��ٶȣ�
                        //�����������ǲ���ֵ����ʾ�Ƿ�Ҫ����onSlideChange�ص�������


                    }
                }
            } //����swiperĬ����ʾ�ڼ�ҳ��������

        }

		</script>

	<body>

	<!--<p class="goback" onclick="javascript:history.back(-1);"><img src="../img/fanhui.png"></p>-->
	<!-- Swiper -->
	<div class="swiper-container">
		<div class="swiper-button-next"></div>  <!--��һҳ��ť-->
		<div class="swiper-button-prev"></div>  <!--��һҳ��ť-->
		<div class="swiper-wrapper" >
		</div>

	</div>
	</body>
</html>

