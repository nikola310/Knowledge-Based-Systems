/**
 * @author Nikola
 */

function loadPatients() {
	$.get("patient", function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.patientName
			});

			var td2 = $('<td>', {
				html : val.patientSurname
			});

			var a = $('<a>', {
				href : "edit-patient.html?id=" + val.patientId,
				html : "Edit"
			});

			var td3 = $('<td>', {
				html : a[0].outerHTML
			});

			var a2 = $('<a>', {
				href : "allergies.html?id=" + val.patientId,
				html : "Add allergies"
			});

			var td4 = $('<td>', {
				html : a2[0].outerHTML
			});

			var a3 = $('<a>', {
				href : "remove-allergies.html?id=" + val.patientId,
				html : "Remove allergies"
			});

			var td5 = $('<td>', {
				html : a3[0].outerHTML
			});

			var a4 = $('<a>', {
				href : "med-allergies.html?id=" + val.patientId,
				html : "Add (medicine) allergies"
			});

			var td6 = $('<td>', {
				html : a4[0].outerHTML
			});

			var a5 = $('<a>', {
				href : "remove-med-aller.html?id=" + val.patientId,
				html : "Remove (medicine) allergies"
			});

			var td7 = $('<td>', {
				html : a5[0].outerHTML
			});

			var a6 = $('<a>', {
				href : "new-diagnosis.html?id=" + val.patientId,
				html : "Add diagnosis"
			});

			var td8 = $('<td>', {
				html : a6[0].outerHTML
			});

			var del = $('<button>', {
				id : val.patientId,
				html : "Delete",
				onclick : "deletePatient(this);"
			});
			var td9 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML + td5[0].outerHTML
						+ td6[0].outerHTML + td7[0].outerHTML
						+ td8[0].outerHTML + td9[0].outerHTML
			});

			$("#patients-table tbody").append(tr);
		});
	});
}

function deletePatient(e) {

	$.ajax({
		type : 'DELETE',
		url : 'patient/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Patient deleted successfully.");
			$(e).closest('tr').remove();
		},
		fail : function(data) {
			window.alert("Fail!");
		},
		error : function(data) {
			window.alert("Error!");
		}
	});
}

function loadMyDiagnoses() {
	$.get("diagnosis/mine", function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.patient
			});

			var td2 = $('<td>', {
				html : new Date(val.date).toUTCString()
			});

			var td3 = $('<td>', {
				html : val.disease
			});

			var a = $('<a>', {
				href : "edit-diagnosis.html?id=" + val.diagnosisId,
				html : "Edit"
			});

			var td4 = $('<td>', {
				html : a[0].outerHTML
			});

			var del = $('<button>', {
				id : val.diagnosisId,
				html : "Delete",
				onclick : "deleteDiagnosis(this);"
			});
			var td5 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML + td5[0].outerHTML
			});

			$("#diagnoses-table tbody").append(tr);
		});
	});
}

function loadDiseases() {
	$.get("disease", function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.diseaseName
			});

			var a = $('<a>', {
				href : "show-disease.html?id=" + val.diseaseId,
				html : "See symptoms"
			});

			var td2 = $('<td>', {
				html : a[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML
			});

			$("#disease-table tbody").append(tr);
		});
	});
}

function deleteDiagnosis(e) {
	$.ajax({
		type : 'DELETE',
		url : 'diagnosis/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Diagnosis deleted successfully.");
			$(e).closest('tr').remove();
		},
		fail : function(data) {
			window.alert("Fail!");
		},
		error : function(data) {
			window.alert("Error!");
		}
	});
}

function loadTherapies() {
	$.get("/therapy/mine", function(data) {
		jQuery.each(data, function(i, val) {
			var td1 = $('<td>', {
				html : val.patientName
			});

			var td2 = $('<td>', {
				html : val.diseaseName
			});

			var td3 = $('<td>', {
				html : val.medicineName
			});

			var td4 = $('<td>', {
				html : new Date(val.therapyDate).toUTCString()
			});

			/*
			 * var a = $('<a>', { href : "edit-diagnosis.html?id=" +
			 * val.diagnosisId, html : "Edit" });
			 * 
			 * var td4 = $('<td>', { html : a[0].outerHTML });
			 */

			var del = $('<button>', {
				id : val.therapyId,
				html : "Delete",
				onclick : "deleteTherapy(this);"
			});
			var td5 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML + td5[0].outerHTML
			});

			$("#therapy-table tbody").append(tr);
		});
	});
}

function deleteTherapy(e) {
	$.ajax({
		type : 'DELETE',
		url : 'therapy/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Therapy deleted successfully.");
			$(e).closest('tr').remove();
		},
		fail : function(data) {
			window.alert("Fail!");
		},
		error : function(data) {
			window.alert("Error!");
		}
	});
}