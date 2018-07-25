/**
 * @author Nikola
 */

function createUser() {
	var type = "D";
	if ($("#admin")[0].checked) {
		type = "A";
	}

	var newUser = JSON.stringify({
		"userName" : $("#username").val(),
		"userSurname" : $("#name").val(),
		"userUsername" : $("#surname").val(),
		"userType" : type,
		"userPassword" : $("#password").val()
	});

	$.ajax({
		type : 'post',
		url : 'user',
		data : newUser,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success!");
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