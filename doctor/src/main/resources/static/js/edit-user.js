/**
 * @author Nikola
 */

var id;

function loadUser() {
	id = getUrlParameter("id")
	$.get("user/" + id, function(data) {
		$("#username").val(data.userUsername);
		$("#name").val(data.userName);
		$("#surname").val(data.userSurname);
		if (data.userType == "A") {
			$("#admin")[0].checked = true;
		}
		$("#password").val(data.userPassword);
	});
}

function editUser() {
	var type = "D";
	if ($("#admin")[0].checked) {
		type = "A";
	}

	var edit = JSON.stringify({
		"userId" : id,
		"userName" : $("#name").val(),
		"userSurname" : $("#surname").val(),
		"userUsername" : $("#username").val(),
		"userType" : type,
		"userPassword" : $("#password").val()
	});
	console.log(edit);

	$.ajax({
		type : 'PUT',
		url : 'user',
		data : edit,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success!");
			window.alert("Update successful.");
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