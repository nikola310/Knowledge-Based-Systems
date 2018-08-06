/**
 * @author Nikola
 */

var id;

function loadSymptoms(){
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-name").text(
				"Choose symptoms for patient \"" + data.patientName + data.patientSurname + "\"");
	});
	
	$.get("symptom", function(lista){
		jQuery.each(data, function(i, val) {
			
			var box = $('<input>', {
				type: "checkbox",
				value: val.symDesc,
				id: "sym-" + val.symId,
				class: ".checkbox-circle"
			});
			
			$("#sym-container").append(box[0].outerHTML + " " + val.symDesc + '<br/>');
			
		});
	});
	
}

function processSymptoms(){
	kids = $("#sym-container").children(":input");
	
	syms = [];
	
	jQuery.each(kids, function(i, val){
		
		if(val.checked){
			
			var symDis = {
				"diseaseId" : parseInt(id),
				"symptomId" : parseInt(val.id.split("-")[1])
			};
			
			syms.push(symDis);

		}
	});
	
	$.ajax({
		type : 'POST',
		url : 'diagnosis/',
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