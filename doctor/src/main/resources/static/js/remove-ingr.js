/**
 * @author Nikola
 */

var id;

function loadSyms() {
	id = getUrlParameter("id");
	$.get("medicine/" + id, function(data) {
		$("#medicine-name").text(
				"Remove ingredients from medicine \"" + data.medicineName
						+ "\"");
	});

	$.get("/ingr-med/med/" + id, function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $("<td/>", {
				html : val.ingredientName
			});

			var del = $("<button/>", {
				html : "Delete",
				id : val.imId,
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
		url : 'ingr-med/' + e.id,
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