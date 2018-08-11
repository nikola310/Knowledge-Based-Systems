/**
 * @author Nikola
 */

var id;

function load(){
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-name").text(
				"Select disease from which patient \"" + data.patientName + " " + data.patientSurname + "\" suffers");
	});
	
	$.get("/disease", function(data){
		
		jQuery.each(data, function(i, val) {
			//radio group
			var box = $('<input>', {
				type: "checkbox",
				value: val.medicineName,
				id: "sym-" + val.medicineId,
				class: ".checkbox-circle"
			});
			
			$("#med-container").append(box[0].outerHTML + " " + val.symDesc + '<br/>');
			
		});
		
	});
	
}