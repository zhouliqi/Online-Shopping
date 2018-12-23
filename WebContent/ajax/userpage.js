/**
 * 
 */
$(document).ready(function() {
	
	/**
	 * 判断用户界面是否已经登录,如果为登录。则跳转到登录界面
	 */
	$.ajax({
		url: "/Shopping/getsession",
		type: "POST",
		dataType: "text",
		success: function(result) {
			var obj = $.parseJSON(result.toString());
			if (obj.success == "true") {
				$("#info").text(obj.userName);
			} else if (obj.success == "false"){
				window.location.href = "/Shopping/login.html";
			}
		},
		error: function() {
			$("#myModalLabel").text("出现错误");
			$('#myModal').modal();
		}
	});
	
	/**
	 * 点击主页是返回主页面
	 */
	$("#main").click(function() {
		window.location.href = "/Shopping/debug.html";
	});
	
	// 用户界面中退出登录时销毁Session
	$("#logout").click(function() {
		$.ajax({
			url: "/Shopping/destroysession",
			type: "POST",
			data: {"status": "ok"},
			dataType: "text",
			success: function(result) {
				var obj = $.parseJSON(result.toString());
				if (obj.success == "true") {
					window.location.href = "/Shopping/login.html";
					$("em").show();
					$("#login").show();
					$("#register").show();
					$("#user").hide();
					$("#vip").hide();
				} else if (obj.success == "false"){
					
				}
			},
			error: function() {
				$("#myModalLabel").text("出错啦!");
				$('#myModal').modal();
			}
		});
	});
	
	/**
	 * 我的账户页面
	 */
	$("#list-1").click(function() {
		$("#account").fadeIn();
		$("#account").fadeIn("slow");
		$("#account").fadeIn(3000);
		$("#account").removeClass("hidden")
		$("#pwd").addClass("hidden");
	});
	
	/**
	 * 定向到订单页面
	 */
	$("#list-2").click(function() {
		$("#myModalLabel").text("订单");
		$('#myModal').modal();
		//window.location.href = "#";
	});
	
	/**
	 * 密码管理页面
	 */
	$("#list-3").click(function() {
		$("#pwd").fadeIn();
		$("#pwd").fadeIn("slow");
		$("#pwd").fadeIn(3000);
		$("#pwd").removeClass("hidden");
		$("#account").addClass("hidden")
	});
	
	$("#list-4").click(function() {
		$("#myModalLabel").text("地址");
		$('#myModal').modal();
	});
	
	/**
	 * 修改信息的功能
	 */
	$("#account_save").click(function() {
		var nickname = $("#nickname").val();
		var realname = $("#realname").val();
		if (nickname == "" && realname == "") {
			$("#myModalLabel").text("您未填入要修改的信息");
			$('#myModal').modal();
		} else {
			$.ajax({
				url: "/Shopping/modifyaccount",
				type: "POST",
				data: {"nickname": nickname, "realname": realname},
				dataType: "text",
				success: function(result) {
					var obj = $.parseJSON(result.toString());
					if (obj.success == "true") {
						//window.location.href = "/Shopping/userpage.html";
						if ($("#tips").css("display") == "none" ) {
							$("#tips").show();
						}
						// 提示信息出现五秒后消失
						setTimeout(function(){ $("#tips").css("display", "none"); }, 5000);
					} else if (obj.success == "false") {
						$("#myModalLabel").text(obj.message);
						$('#myModal').modal();
					}
				},
				error: function() {
					alert("error");
				}
			});
		}
		
	});
	
	/**
	 * 修改密码的功能
	 */
	$("#password_save").click(function() {
		var current_password = $("#current_password").val();
		var new_password = $("#new_password").val();
		var ensure_password = $("#ensure_password").val();
		if (current_password == "") {
			$("#myModalLabel").text("请输入当前密码");
			$('#myModal').modal();
		} else if (new_password == "") {
			$("#myModalLabel").text("请输入新密码");
			$('#myModal').modal();
		} else if (new_password.length < 5) {
			$("#myModalLabel").text("您的新密码长度应该大于等于6位");
			$('#myModal').modal();
		} else if (!(new_password == ensure_password)) {
			$("#myModalLabel").text("你要更改的密码不一致,请重新输入");
			$('#myModal').modal();
		} else {
			$.ajax({
				url: "/Shopping/moddiypassword",
				type: "POST",
				data: {"current_password" : current_password, "new_password": new_password},
				dataType: "text",
				success: function(result) {
					var obj = $.parseJSON(result.toString());
					if (obj.success == "true") {
						window.location.href = "/Shopping/userpage.html";
					} else if (obj.success == "false") {
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