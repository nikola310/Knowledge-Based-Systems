/**
 * @author Nikola
 */

function login() {
	var log = JSON.stringify({
		"username" : $("#username").val(),
		"password" : $("#password").val()
	});

	$.ajax({
		type : 'post',
		url : 'login',
		data : log,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success!");
			if (data.type == "A") {
				window.location.replace("admin.html");
			} else if (data.type == "D") {
				window.location.replace("doctor.html");
			} else {
				window.location.replace("index.html");
			}
		},
		fail : function(data) {
			console.log(data);
			window.alert("Bad credentials!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Bad credentials!");
		}
	});

	return false;
}