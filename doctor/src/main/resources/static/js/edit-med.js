/**
 * @author Nikola
 */

var id;

function loadMed() {
	id = getUrlParameter("id");
	$.get("medicine/" + id, function(data) {
		$("#name").val(data.medicineName);
		if (data.medicineType == "A") {
			$("#antibiotic")[0].checked = true;
		} else if (data.medicineType == "P") {
			$("#painkiller")[0].checked = true;
		} else if (data.medicineType == "O") {
			$("#other")[0].checked = true;
		}
	});
}

function editMed() {
	var type = "A";
	if ($("#painkiller")[0].checked) {
		type = "P";
	} else if ($("#other")[0].checked) {
		type = "O";
	}

	var edit = JSON.stringify({
		"medicineId" : id,
		"medicineName" : $("#name").val(),
		"medicineType" : type
	});

	$.ajax({
		type : 'PUT',
		url : 'medicine',
		data : edit,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
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