(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize =Math.floor(100*(clientWidth / 1080))+ 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document,window);


$(document).ready(function(){
	var car_num = $(".car_num");  //-------------------------------------------------------------获取用户输入的车牌号
	var password_num = $(".password_num");   //---------------------------------------------------获取用户输入的账号密码
	var login = $(".login"); //------------------------------------------------------------------获取登录按钮
	var register = $(".register");   //----------------------------------------------------------获取注册按钮
	var tc_ceng = $(".tcc");  //-------------------------------------------------获取弹出层
	var l_box = $(".l_box"); //-------------------------------------------------获取已注册，没有密码框
	var b_box = $(".b_box");  //------------------------------------------------获取已注册，有密码框
	var close = $(".close"); //---------------------------------------------------获取遮罩层中红色关闭按钮
	var moblie_num = $(".mobile_num"); //-----------------------------------------获取遮罩层中用户注册，且没密码的手机号码标签
	var determine = $(".l_qdan") //----------------------------------------------获取遮罩层中用户注册，且没密码的确定按钮
	var sign_in = $(".l_qdl");  //-----------------------------------------------获取遮罩层中用户注册，且有密码的去登陆按钮
	
	
	//------------------------------------------------------------------------JS控制的css样式
	car_num.on("focus",function(){
		$(this).css({
			"border":"3px #6cd2fd solid",
			"lineHeight":"1.05rem",
		})
		$(this).toggleClass("input_bg")
	})
	car_num.on("blur",function(){
		$(this).css({
			"border":"none",
			"lineHeight":"1.1rem",
		});
		$(this).toggleClass("input_bg")
	})
	
	password_num.on("focus",function(){
		$(this).css({
			"border":"3px #6cd2fd solid",
			"lineHeight":"1.05rem",
		})
		$(this).toggleClass("input_bg")
	})
	password_num.on("blur",function(){
		$(this).css({
			"border":"none",
			"lineHeight":"1.1rem",
		});
		$(this).toggleClass("input_bg")
	})
	
	//----------------------------------------------------------判断车牌号是否注册函数
	function car_judge(){
		var car_value = car_num.val();
		if (car_value == "") {
			alert("请输入车牌号~");
			var those = document.getElementsByClassName("car_num")[0];
			those.select()
		} else{
//			$.ajax({
//				type:"get",
//				url:"",
//				async:true
//			});
			var a = 0;
			if (a == 0) {            //----------------------------------------------------------------去注册
				tc_ceng.show();
				b_box.show();
				return false;
			} else if(a == 1){             //----------------------------------------------------------没密码
				tc_ceng.show();
				l_box.show();
				return false;
			}else if(a == 2){             //-----------------------------------------------------------有密码
				return false;
			}
			//------------------------------------------------------------------------手机号字符拼接*号
			var b = "13583488556";
			var reg = b.substr(3,4); 
			var c = b.replace(reg,"****");
			
		}
	}
	
	
	//--------------------------------------------------------------------------点击账户密码进行判断
	password_num.on("focus",function(){
		car_judge();
	});
	
	
	
	
	//----------------------------------------------------------------------------------遮罩层中红色关闭按钮点击
	close.on("click",function(){
		tc_ceng.hide();
		l_box.hide();
        b_box.hide()
	})
	
	
	//----------------------------------------------------------------------------------点击注册按钮进行判断
	login.on("click",function(){
		car_judge();
		var see = tc_ceng.css("display");
//		console.log(see)
		if(see == "none"){
			var password_value = password_num.val();
			if (password_value == "") {
				alert("请设置您的账户密码~")
			} else {
				//------------------------------------------------------------------------------ajax登录判断
	//			$.ajax({
	//				type:"get",
	//				url:"",
	//				async:true
	//			});
			}
		}
		
	})
	//--------------------------------------------------------------------------------------点击注册按钮跳转
	register.on("click",function(){
		window.location.href = "";
	});
	
	//------------------------------------------------------------------------------------获取遮罩层中用户注册，且没密码的确定按钮的点击
	determine.on("click",function(){
		tc_ceng.hide();
		l_box.hide();
        b_box.hide()
//		$.ajax({                //---------------------------------------------------------------ajax确定发送短息 
//			type:"get",
//			url:"",
//			async:true
//		});                
	})
	
	//------------------------------------------------------------------------------------获取遮罩层中用户没有注册，去注册的点击
	sign_in.on("click",function(){
		tc_ceng.hide();
		l_box.hide();
        b_box.hide()
//		window.location.href = "";
	})
})
