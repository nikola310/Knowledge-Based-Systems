/**
 * @author Nikola
 */

var id;

function loadDisease() {
	id = getUrlParameter("id");
	$.get("disease/" + id, function(data) {
		$("#name").val(data.diseaseName);
		$("#code").val(data.diseaseCode);
	});
}

function editDisease() {

	var edit = JSON.stringify({
		"diseaseId" : id,
		"diseaseName" : $("#name").val(),
		"diseaseCode" : $("#code").val()
	});

	$.ajax({
		type : 'PUT',
		url : 'disease',
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
			window.alert("Bad credentials!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Error!");
		}
	});

	return false;
}