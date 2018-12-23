/**
 * 
 * 密码重置的js代码
 * 
 */

$(document).ready(function() {
	$("#reset").click(function() {
		var userName = $("#userName").val();
		var phone = $("#phone").val();
		
		if (userName == "" || phone == "") {
			$("#myModalLabel").text("请输入你的用户名或手机号码");
			$('#myModal').modal();
		} else {
			$.ajax({
				url: "/Shopping/reset",
				type: "POST",
				data: {"userName": userName, "phone": phone},
				dataType: "text",
				success: function(result){
					var obj = $.parseJSON(result.toString());
					if (obj.success == "true") {
						window.location.href = "/Shopping/resetpwd.html";
					} else if (obj.success == "false"){
						$("#myModalLabel").text(obj.message);
						$('#myModal').modal();
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {     
					alert("You get a error");
				}
			});
		}
	});
});