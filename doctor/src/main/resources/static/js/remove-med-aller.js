/**
 * @author Nikola
 */

var id;

function loadMedicine() {
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-name").text(
				"Remove medicine that cause allergic reaction to patient \""
						+ data.patientName + " " + data.patientSurname + "\"");
	});

	$.get("/med-allergy/patient/" + id, function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $("<td/>", {
				html : val.medicine
			});

			var del = $("<button/>", {
				html : "Delete",
				id : val.medId,
				onclick : "deleteMedicine(this);"
			});

			var td2 = $("<td/>", {
				html : del[0].outerHTML
			});

			var tr = $("<tr/>", {
				html : td1[0].outerHTML + td2[0].outerHTML
			})

			$("#med-table tbody").append(tr);

		});
	});

}

function deleteMedicine(e) {
	$.ajax({
		type : 'DELETE',
		url : 'med-allergy/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Medicine removed successfully.");
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