/**
 * @author Nikola
 */

function createMed() {
	var type = "A";
	if ($("#painkiller")[0].checked) {
		type = "P";
	} else if ($("#other")[0].checked) {
		type = "O";
	}

	var med = JSON.stringify({
		"medicineName" : $("#name").val(),
		"medicineType" : type
	});

	$.ajax({
		type : 'POST',
		url : 'medicine',
		data : med,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Success!");
			window.alert("Success!");
			window.location.replace("admin.html");
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