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

			var td3 = $('<td>', {
				html : val.patientTemperature
			});

			var td4 = $('<td>', {
				html : val.patientBloodPressure
			});

			var a = $('<a>', {
				href : "edit-patient.html?id=" + val.patientId,
				html : "Edit"
			});

			var td5 = $('<td>', {
				html : a[0].outerHTML
			});

			var a2 = $('<a>', {
				href : "allergies.html?id=" + val.patientId,
				html : "Add allergies"
			});

			var td6 = $('<td>', {
				html : a2[0].outerHTML
			});

			var a3 = $('<a>', {
				href : "remove-allergies.html?id=" + val.patientId,
				html : "Remove allergies"
			});

			var td7 = $('<td>', {
				html : a3[0].outerHTML
			});

			var del = $('<button>', {
				id : val.userId,
				html : "Delete",
				onclick : "deletePatient(this);"
			});
			var td8 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML + td5[0].outerHTML
						+ td6[0].outerHTML + td7[0].outerHTML
						+ td8[0].outerHTML
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
				html : val.disease
			});

			var td3 = $('<td>', {
				html : new Date(val.date).toUTCString()
			});

			var td4 = $('<td>', {
				html : new Date(val.propsDeadline).toUTCString()
			});

			var a = $('<a>', {
				href : "edit-diagnosis.html?id=" + val.diagnosisId,
				html : "Edit"
			});

			var td5 = $('<td>', {
				html : a[0].outerHTML
			});

			var del = $('<button>', {
				id : val.userId,
				html : "Delete",
				onclick : "deleteDiagnosis(this);"
			});
			var td6 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML + td5[0].outerHTML
						+ td6[0].outerHTML
			});

			$("#diagnoses-table tbody").append(tr);
		});
	});
}