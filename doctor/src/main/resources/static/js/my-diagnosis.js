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
			
			var radio = $('<input>', {
				type: "radio",
				name: "optradio",
				value: val.diseaseCode,
				id: "dis-" + val.diseaseId,
			});
			radio.prop('checked', true);
			
			var lbl = $('<label/>', {
				html: radio[0].outerHTML + val.diseaseName
			});
			
			var div = $('<div/>', {
				class: "radio",
				html: lbl[0].outerHTML
			});
			
			$("#dis-container").append(div[0].outerHTML);
		});
		
	});
	
}

function setDiagnosis() {
	var code = $('input[name=optradio]:checked', '#disease-form').val();
	
	if(code == undefined || code == null){
		window.alert("Please select disease.");
		return false;
	}

	// contentType : "application/json; charset=utf-8",
	$.ajax({
		type : 'POST',
		url : 'diagnosis/process/mine',
		data : { "patientId" : parseInt(id), "diseaseCode" : code },
		dataType : "json",
		success : function(data) {
			window.alert("Success!");
			window.location.replace("meds.html");
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