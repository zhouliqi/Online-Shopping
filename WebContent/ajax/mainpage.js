/**
 * 当用户未登陆时,将用户按钮隐藏
 */
$(document).ready(function() {

	/**
	 * 判断用户是否登录
	 */
	$.ajax({
		url: "/Shopping/getsession",
		type: "POST",
		//data: {"status": 200},
		dataType: "text",
		success: function(result) {
			var obj = $.parseJSON(result.toString());
			if (obj.success == "true") {
				$("em").hide();
				$("#login").hide();
				$("#register").hide();
				$("#user").show();
				$("#vip").show();
				$("#vip").text("你好!  "+ obj.userName);
			} else if (obj.success == "false"){
				
			}
		},
		error: function() {
			$("#myModalLabel").text("出现错误");
			$('#myModal').modal();
		}
	});
	
	/*
	$("#setting").click(function() {
		$.ajax({
			url: "/Shopping/getsession",
			type: "POST",
			dataType: "text",
			success: function() {
				var obj = $.parseJSON(result.toString());
				if (obj.success == "true") {
					window.location.href = "/Shopping/userpage.html";
				} else if (obj.success == "false") {
					window.location.href = "/Shopping/login.html";
				}
			},
			error: function() {
				alert("出错啦")
			}
		});
	});
	*/
	
});