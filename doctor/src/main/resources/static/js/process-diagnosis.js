/**
 * @author Nikola
 */

function loadAllDiseases() {
	$.get("disease", function(data) {
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html: val.diseaseName
			});

			var btn = $('<button>', {
				html: Select disease
			});
			
			var td2 = $('<td>', {
				html: btn[0].outerHTML
			});
			
			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML
			});

			$("#disease-table tbody").append(tr);
		});
	});
}