/**
 * @author Nikola
 */

var id;

function load() {
	id = getUrlParameter("report")
	if (id === "1") {
		getChronicReport(id);
	} else if (id === "2") {
		getAddictsReport(id);
	} else if (id === "3") {
		getImmunityReport(id);
	} else {
		console.log("Error");
	}
}

function getChronicReport(id) {
	$.get("report/chronic", function(data) {
		if (data.length == 0) {

			var paragraf = $("<p/>", {
				html : "There are no patients with chronic diseases"
			});

			$("#result-container")[0].innerHTML = paragraf[0].outerHTML;
		} else {

			var th1 = $("<th/>", {
				html : "Name"
			});
			var th2 = $("<th/>", {
				html : "Surname"
			});
			var trh = $("<tr/>", {
				html : th1[0].outerHTML + th2[0].outerHTML
			});
			var th3 = $("<th/>", {
				html: "Patients with chronic diseases"
			});
			var trh1 = $("<tr/>", {
				html : th3[0].outerHTML
			});
			var thead = $("<thead/>", {
				html : trh1[0].outerHTML + trh[0].outerHTML
			});
			var tbody = $("<tbody/>");
			var table = $("<table/>", {
				id : "result-table",
				html : thead[0].outerHTML + tbody[0].outerHTML,
				class : "table-striped"
			});

			$(table).appendTo("#result-container");
			jQuery.each(data, function(i, val) {

				var td1 = $("<td/>", {
					html : val.patientName
				});

				var td2 = $("<td/>", {
					html : val.patientSurname
				});

				var tr = $("<tr/>", {
					html : td1[0].outerHTML + td2[0].outerHTML
				});

				$("#result-table tbody").append(tr);
			});
		}

	});
}

function getAddictsReport(id) {

}

function getImmunityReport(id) {

}