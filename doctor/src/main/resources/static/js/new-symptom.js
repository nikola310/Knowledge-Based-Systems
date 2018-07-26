/**
 * @author Nikola
 */

function createSym() {
	var sym = JSON.stringify({
		"symDesc" : $("#desc").val()
	});

	$.ajax({
		type : 'POST',
		url : 'symptom',
		data : sym,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success!");
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