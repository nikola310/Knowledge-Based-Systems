/**
 * @author Nikola
 */

var id;

function loadSyms() {
	id = getUrlParameter("id");
	$.get("disease/" + id, function(data) {
		$("#disease-name").text(
				"Remove symptoms from disease \"" + data.diseaseName + "\"");
	});

	$.get("/sym-disease/dis/" + id, function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $("<td/>", {
				html : val.symptomDesc
			});

			var del = $("<button/>", {
				html : "Delete",
				id : val.sdId,
				onclick : "deleteSym(this);"
			});

			var td2 = $("<td/>", {
				html : del[0].outerHTML
			});

			var tr = $("<tr/>", {
				html : td1[0].outerHTML + td2[0].outerHTML
			})

			$("#sym-table tbody").append(tr);

		});
	});

}

function deleteSym(e) {
	$.ajax({
		type : 'DELETE',
		url : 'sym-disease/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Symptom removed successfully.");
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