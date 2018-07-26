/**
 * @author Nikola
 */

var id;

function loadSym() {
	id = getUrlParameter("id")
	$.get("symptom/" + id, function(data) {
		$("#desc").val(data.symDesc);
	});
}

function editSym() {

	var edit = JSON.stringify({
		"symId" : id,
		"symDesc" : $("#desc").val()
	});
	console.log(edit);

	$.ajax({
		type : 'PUT',
		url : 'symptom',
		data : edit,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success!");
			window.alert("Update successful.");
		},
		fail : function(data) {
			console.log(data);
			window.alert("Bad credentials!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Error!");
		}
	});

	return false;
}