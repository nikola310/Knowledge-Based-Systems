/**
 * @author Nikola
 */

var patientId;
var diseaseId;

function loadAllDiseases() {
	$.get("diagnosis/get-current", function(data) {
		patientId = data.patientId;
		diseaseId = data.diseaseId;
		var t = $("#disease-name");
		t[0].innerHTML = "Disease: " + data.disease;
		// .val("Most probable disease: " + data.disease);
		t = $("#patient-name"); // .val(data.patient);
		t[0].innerHTML = "Patient: " + data.patient;
		t = $("#date"); // .val(new Date(data.date).toUTCString());
		t[0].innerHTML = new Date(data.date).toUTCString();
	});
}

function proceed() {
	var diag = JSON.stringify({
		"patientId" : patientId,
		"diseaseId" : diseaseId,
		"diagnosisDate" : new Date($("#date")[0].innerHTML)
	});

	$.ajax({
		type : 'POST',
		url : 'diagnosis',
		data : diag,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Success!");
			window.location.replace("meds.html?id=" + patientId);
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

function myDiagnosis() {
	window.location.replace("my-diagnosis.html?id=" + patientId);
}