$(document).ready(function() {

	$("#username").focus(function() {
		var username = $(this).val();
		if(username == this.defaultValue) {
			$(this).val("");
		}
	});

	$("#username").blur(function() {
		var username = $(this).val();
		if(username == "") {
			$(this).val(this.defaultValue);
		}
	});

	$("#password").focus(function() {
		var password = $(this).val();
		if(password == this.defaultValue) {
			$(this).val("");
		}
	});

	$("#password").blur(function() {
		var password = $(this).val();
		if(password == "") {
			$(this).val(this.defaultValue);
		}
	});
	/*登录点击*/
	/*$("#login").click(function() {
		if($("#username").val()!='请输入用户名'&&$("#password").val()!=''){
			
			$.ajax({
			type: "post",
			url: "http://localhost:8100/login",
			data: {
				"username": $("#username").val(),
				"password": $("#password").val()
			},
			async: true,
			beforeSend: function() {
				
			},
			success: function() {

			},
			complete: function() {
				
			},
			error: function() {
				
			}

		    });	
		}
	});*/

});