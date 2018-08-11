/**
 * @author Nikola
 */

var id;

function load() {
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-name").text(
				"Select medicine for patient \"" + data.patientName + " " + data.patientSurname + "\"");
	});
	
	$.get("medicine", function(lista){
		
		jQuery.each(data, function(i, val) {
			
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

function addMeds(){
	kids = $("#med-container").children(":input");
	
	meds = [];
	
	jQuery.each(kids, function(i, val){
		
		if(val.checked){
			
			var Med = {
				"patientId" : parseInt(id),
				"medicineId" : parseInt(val.id.split("-")[1])
			};
			
			meds.push(Med);

		}
	});
	
	$.ajax({
		type : 'POST',
		url : 'therapy/check',
		data : JSON.stringify(syms),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Symptoms added successfully.");
			window.location.replace("admin.html");
		},
		fail : function(data) {
			window.alert("Fail!");
		},
		error : function(data) {
			window.alert("Error!");
		}
	});
	
	return false;
}