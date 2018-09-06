/**
 * @author Nikola
 */

function loadSyms() {
	var id = getUrlParameter("id");
	$.get("disease/" + id, function(data) {
		$("#disease-name").text(
				"Symptoms for disease \"" + data.diseaseName + "\"");
	});

	$.get("/sym-disease/dis/" + id, function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $("<td/>", {
				html : val.symptomDesc
			});

			var spec = "Regular";
			if (val.sdSpecific) {
				spec = "Specific";
			}
			var td2 = $("<td/>", {
				html : spec
			});

			var tr = $("<tr/>", {
				html : td1[0].outerHTML + td2[0].outerHTML
			})

			$("#sym-table tbody").append(tr);

		});
	});

}