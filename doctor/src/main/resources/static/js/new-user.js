/**
 * @author Nikola
 */

function createUser() {
	var type = "D";
	if ($("#admin")[0].checked) {
		type = "A";
	}

	var newUser = JSON.stringify({
		"userName" : $("#name").val(),
		"userSurname" : $("#surname").val(),
		"userUsername" : $("#username").val(),
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
			window.location.replace("admin.html");
		},
		fail : function(data) {
			console.log(data);
			window.alert("Fail!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Error!");
		}
	});

	return false;
}