/**
 * 如果注册的信息有误，则向span写入提示信息
 */

$(document).ready(function(){
	$("#register").click(function(){
		var userName = $("#userName").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var checkPassword = $("#checkPassword").val();
		var phone = $("#phone").val();
		var address = $("#address").val();
		
		if (userName == "" || name == "" || password == "" || address == "") {
			$("#myModalLabel").text("你的信息还未填写完整");
			$('#myModal').modal();
		} else {
			$.ajax({
				url: "/Shopping/register",
				type: "POST",
				data: {"userName": userName, "name": name, "password": password, "checkPassword": checkPassword, "phone": phone, "address": address},
				dataType: "text",
				success: function(result) {
					var obj = $.parseJSON(result.toString());
					if (obj.success == "true") {
						window.location.href = "/Shopping/login.html";
					} else if (obj.success == "false") {
						if ($("#tips").css("display") == "none" ) {
							$("#tips").show();
						}
						$("#massage").text(obj.message); // 写入出错信息
						// 提示信息出现五秒后消失
						setTimeout(function(){ $("#tips").css("display", "none"); }, 5000);
					} else {
						alert("error");
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {     
					alert(errorThrown);
				}
			});
		}
		
	});
	
/**
 * 提示信息
 */
	$("#password").blur(function(){
		if ($("#password").val().length < 6) {
			$("#myModalLabel").text("您输入的密码长度不足6位,请重新输入");
			$('#myModal').modal();
		}
	});
	
	$("#phone").blur(function(){
		var regex = /[\d]{11}/;
		if (!regex.test($("#phone").val())) {
			$("#myModalLabel").text("请输入11位手机号码");
			$('#myModal').modal();
		}
	});
	
	$("#address").blur(function(){
		if (regex.test($("#address").val()) == "") {
			$("#myModalLabel").text("请填写相应的地址");
			$('#myModal').modal();
		}
	});
	
	
});