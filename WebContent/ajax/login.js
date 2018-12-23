/**
 * 当用户已登录时,隐藏登录注册;显示用户按钮
 */


$(document).ready(function(){
	$("#login").click(function(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		
		if ((userName == "") || password == "") {
			$("#myModalLabel").text("请输入你的用户名或密码");
			$('#myModal').modal();
		} else {
			
			$.ajax({
				url: "/Shopping/login",
				type: "POST",
				data: {"userName": userName, "password": password},
				dataType: "text",
				success: function(result){
					var obj = $.parseJSON(result.toString());
					if (obj.success == "true") {
						window.location.href = "/Shopping/debug.html";
					} else if (obj.success == "false"){
						$("#myModalLabel").text(obj.message);
						$('#myModal').modal();
					}
				},
				error: function() {
					$("#myModalLabel").text("出错啦!");
					$('#myModal').modal();
				}
			});
		}
	});
	
	
});