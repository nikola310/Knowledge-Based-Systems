/**
 * @author Nikola
 */

function createSym() {
	var sym = JSON.stringify({
		"symDesc" : $("#desc").val(),
		"symCode" : $("#code").val()
	});

	$.ajax({
		type : 'POST',
		url : 'symptom',
		data : sym,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Success!");
			window.location.replace("admin.html");
		},
		fail : function(data) {
			window.alert("Fail!");
		},
		error : function(data) {
			window.alert("Error!");
		}
	});

	return false;
}