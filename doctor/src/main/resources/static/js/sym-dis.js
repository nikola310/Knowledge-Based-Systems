/**
 * @author Nikola
 */

var id;

function load() {
	id = getUrlParameter("id");
	$.get("disease/" + id, function(data) {
		$("#disease-name").text(
				"Add ingredients to disease \"" + data.diseaseName + "\"");
	});
	
	$.get("ingredient", function(data){
		jQuery.each(data, function(i, val) {
			//ingredient-container
			
			var box = $('<input>', {
				html: val.ingredientName,
				id: val.ingredientId,
				class: ".checkbox-circle"
			});
			
			$("#ingredient-container").append(box[0].outerHTML);
			
		}
	});
}
