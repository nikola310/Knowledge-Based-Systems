/**
 * @author Nikola
 */

function login() {
	var regData = JSON.stringify({
		"username" : $("#username").val(),
		"password" : $("#password").val()
	});
	console.log(regData);

	$.ajax({
		type : 'post',
		url : 'login',
		data : regData,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success!");
		},
		fail : function(data) {
			console.log(regData);
			console.log(data);
			window.alert("Bad credentials!");
		},
		error : function(data) {
			console.log(regData);
			console.log(data);
			window.alert("Bad credentials!");
		}
	});
	return false;
}