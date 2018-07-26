/**
 * @author Nikola
 */

function loadUsers() {
	$.get("user", function(data) {
		console.log(data);
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.userUsername
			});

			var td2 = $('<td>', {
				html : val.userName
			});

			var td3 = $('<td>', {
				html : val.userSurname
			});

			var td4 = $('<td>', {
				html : "Administrator"
			});
			if (val.userType == "D") {
				td4[0].innerHTML = "Doctor";
			}

			var a = $('<a>', {
				href : "edit-user.html?id=" + val.userId,
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

			$("#users-table tbody").append(tr);
		});
	})
}

function deleteUser(e) {

	$.ajax({
		type : 'DELETE',
		url : 'user/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("User deleted successfully.");
			$(e).closest('tr').remove();
		},
		fail : function(data) {
			console.log(data);
			window.alert("Fail!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Error!");
		}
	})
}

function loadMeds() {
	$.get("medicine", function(data) {
		console.log(data);
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.medicineName
			});

			var td2 = $('<td>', {
				html : "Antibiotic"
			});
			if (val.medicineType == "P") {
				td4[0].innerHTML = "Painkiller";
			} else if (val.medicineType == "O") {
				td4[0].innerHTML = "Other";
			}

			var a = $('<a>', {
				href : "edit-med.html?id=" + val.medicineId,
				html : "Edit"
			});

			var td3 = $('<td>', {
				html : a[0].outerHTML
			});

			var del = $('<button>', {
				id : val.medicineId,
				html : "Delete",
				onclick : "deleteMed(this);"
			});
			var td4 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML
			});

			$("#meds-table tbody").append(tr);
		});
	});
}

function deleteMed(e) {

	$.ajax({
		type : 'DELETE',
		url : 'medicine/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Medicine deleted successfully.");
			$(e).closest('tr').remove();
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
}

function loadSymptoms() {
	$.get("symptom", function(data) {
		console.log(data);
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.symDesc
			});

			var a = $('<a>', {
				href : "edit-symptom.html?id=" + val.symId,
				html : "Edit"
			});

			var td2 = $('<td>', {
				html : a[0].outerHTML
			});

			var del = $('<button>', {
				id : val.symId,
				html : "Delete",
				onclick : "deleteSym(this);"
			});
			var td3 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
			});

			$("#sym-table tbody").append(tr);
		});
	});
}

function deleteSym(e) {

	$.ajax({
		type : 'DELETE',
		url : 'symptom/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Symptom deleted successfully.");
			$(e).closest('tr').remove();
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
}

function loadDiseases() {
	$.get("disease", function(data) {
		console.log(data);
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.diseaseName
			});

			var a = $('<a>', {
				href : "edit-disease.html?id=" + val.diseaseId,
				html : "Edit"
			});

			var td2 = $('<td>', {
				html : a[0].outerHTML
			});

			var del = $('<button>', {
				id : val.diseaseId,
				html : "Delete",
				onclick : "deleteDisease(this);"
			});
			var td3 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
			});

			$("#disease-table tbody").append(tr);
		});
	});
}

function deleteDisease(e) {

	$.ajax({
		type : 'DELETE',
		url : 'disease/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Disease deleted successfully.");
			$(e).closest('tr').remove();
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
}