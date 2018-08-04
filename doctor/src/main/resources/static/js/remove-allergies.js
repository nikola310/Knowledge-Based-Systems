/**
 * @author Nikola
 */

var id;

function loadIngredients() {
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-name").text(
				"Remove ingredients that cause allergic reaction to patient \""
						+ data.patientName + " " + data.patientSurname + "\"");
	});

	$.get("/allergy/patient/" + id, function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $("<td/>", {
				html : val.ingredientName
			});

			var del = $("<button/>", {
				html : "Delete",
				id : val.allergyId,
				onclick : "deleteIngredient(this);"
			});

			var td2 = $("<td/>", {
				html : del[0].outerHTML
			});

			var tr = $("<tr/>", {
				html : td1[0].outerHTML + td2[0].outerHTML
			})

			$("#ingr-table tbody").append(tr);

		});
	});

}

function deleteIngredient(e) {
	$.ajax({
		type : 'DELETE',
		url : 'allergy/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Ingredient removed successfully.");
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