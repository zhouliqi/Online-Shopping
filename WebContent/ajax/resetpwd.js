/**
 * 为用户设置新密码
 */

$(document).ready(function() {
	$("#ensure").click(function() {
		var userName = $("#userName").val()
		var password = $("#password").val()
		var checkPassword = $("#checkPassword").val()
		
		if (password == "" || checkPassword == "") {
			$("#myModalLabel").text("请输入密码");
			$('#myModal').modal();
		} else if (password.length < 6 || password.length > 20) {
			$("#myModalLabel").text("请输入6-20位的密码");
			$('#myModal').modal();
		} else if (!(password == checkPassword)) {
			$("#myModalLabel").text("您输入的密码不一致,请重新输入");
			$('#myModal').modal();
		} else {
			$.ajax({
				url: "/Shopping/resetpwd",
				type: "POST",
				data: {"userName": userName, "password": password},
				dataType: "text",
				success: function(result) {
					var obj = $.parseJSON(result.toString());
					if (obj.success == "true") {
						window.location.href = "/Shopping/login.html";
					} else if (obj.success == "false"){
						$("#myModalLabel").text(obj.message);
						$('#myModal').modal();
					}
				},
				error: function() {
					alert("修改失败")
				}
			});
		}
	});
	
});