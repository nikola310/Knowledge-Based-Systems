/**
 * @author Nikola
 */

function createPatient() {

	var newPatient = JSON.stringify({
		"patientName" : $("#name").val(),
		"patientSurname" : $("#surname").val(),
		"patientBloodPressure" : parseInt($("#pressure").val()),
		"patientTemperature" : parseFloat($("#temperature").val())
	});

	$.ajax({
		type : 'POST',
		url : 'patient',
		data : newPatient,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.location.replace("doctor.html");
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