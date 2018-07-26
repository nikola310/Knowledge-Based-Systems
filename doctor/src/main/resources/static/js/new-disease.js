/**
 * @author Nikola
 */

function createDisease() {
	var disease = JSON.stringify({
		"diseaseName" : $("#name").val()
	});

	$.ajax({
		type : 'POST',
		url : 'disease',
		data : disease,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Success!");
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