/**
 * 用户退出登录时,主页恢复未登录的状态
 */

$(document).ready(function() {
	
	$("#logout").click(function() {
		$.ajax({
			url: "/Shopping/destroysession",
			type: "POST",
			data: {"status": "ok"},
			dataType: "text",
			success: function(result) {
				var obj = $.parseJSON(result.toString());
				if (obj.success == "true") {
					window.location.href = "/Shopping/debug.html";
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
});