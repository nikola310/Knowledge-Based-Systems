/**
 * @author Nikola
 */

var id;

function loadPatient() {
	id = getUrlParameter("id")
	$.get("patient/" + id, function(data) {
		$("#name").val(data.patientName);
		$("#surname").val(data.patientSurname);
	});
}

function editPatient() {

	var edit = JSON.stringify({
		"patientId" : id,
		"patientName" : $("#name").val(),
		"patientSurname" : $("#surname").val(),
	});

	$.ajax({
		type : 'PUT',
		url : 'patient',
		data : edit,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Update successful.");
			window.location.replace("doctor.html");
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