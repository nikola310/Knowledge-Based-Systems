/**
 * @author Nikola
 */

var id;

function loadSymptoms(){
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-name").text(
				"Choose symptoms for patient \"" + data.patientName + " " +  data.patientSurname + "\"");
	});
	
	$.get("symptom", function(lista){
		jQuery.each(lista, function(i, val) {
			
			var box = $('<input>', {
				type: "checkbox",
				value: val.symDesc,
				id: "sym-" + val.symId + "-" + val.symCode,
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
				"symptomId" : parseInt(val.id.split("-")[1]),
				"symCode" : val.id.split("-")[2]
			};
			
			syms.push(symDis);

		}
	});
	
	var toSend = JSON.stringify({
		"symptoms" : syms,
		"patientId" : id
	});
	
	$.ajax({
		type : 'POST',
		url : 'diagnosis/process',
		data : toSend,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log(data);
			window.location.replace("process-diagnosis.html");
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