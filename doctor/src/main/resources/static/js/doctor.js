/**
 * @author Nikola
 */

function loadPatients(){
	$.get("user", function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.patientName
			});

			var td2 = $('<td>', {
				html : val.patientSurname
			});

			var td3 = $('<td>', {
				html : val.patientBloodPressure
			});

			var td4 = $('<td>', {
				html : val.patientTemperature
			});

			var a = $('<a>', {
				href : "edit-user.html?id=" + val.patientId,
				html : "Edit"
			});

			var td5 = $('<td>', {
				html : a[0].outerHTML
			});

			var del = $('<button>', {
				id : val.userId,
				html : "Delete",
				onclick : "deleteUser(this);"
			});
			var td6 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML + td5[0].outerHTML
						+ td6[0].outerHTML
			});

			$("#patients-table tbody").append(tr);
		});
	});
}

function loadMyDiagnoses(){
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
				html : new Date(val.propsDeadline)
				.toUTCString()val.patientTemperature
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