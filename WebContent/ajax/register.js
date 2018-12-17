/**
 * 如果注册的信息有误，则向span写入提示信息
 */
$(document).ready(function(){
	$("#register").click(function(){
		var user = $("#user").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var checkPassword = $("#checkPassword").val();
		var phone = $("#phone").val();
		var address = $("#address").val();
		
		//var url = "localhost:9999/Shopping/register";
		$.ajax({
			url: "/Shopping/register",
			type: "POST",
			data: {"user": user, "name": name, "password": password, "checkPassword": checkPassword, "phone": phone, "address": address},
			dataType: "text",
			
			success: function(result) {
				
				//alert(result)
				var data = result.replace("Welcome", "");
				data = data.toString();
				var obj = $.parseJSON(data);
				//alert(obj.message);
				
				/*
			    var obj = $.parseJSON(result);
			    console.log(obj);
			    alert(obj);*/
				
				if (obj.success == "true") {
					window.location.href = "/Shopping/login.html";
				} else if (obj.success == "false") {
					if ($("#tips").css("display") == "none" ) {
						$("#tips").show();
					}
					$("#massage").text(obj.message);
				} else {
					alert("No");
				}
			},
			/*error: function() {
				alert(arguments[1]);
			}*/
			error: function (XMLHttpRequest, textStatus, errorThrown) {     
				alert(errorThrown);
			}
		});
		//window.location.href = "/Shopping/login.html";
		/*if ($("#tips").css("display") == "none" ) {
			$("#tips").show();
		}
		$("#tips").text(name+ " " + user + " 电话:" + phone);*/
		
	});
	
	$("#phone").blur(function(){
		var regex = /[\d]{11}/;
		if (!regex.test($("#phone").val())) {
			//alert("电话号码输入有误");
			
			//$('#phone').modal("电话号码输入有误");
			$("#myModalLabel").text("您的号码输入有误");
			$('#myModal').modal();
		}
	});

	
});