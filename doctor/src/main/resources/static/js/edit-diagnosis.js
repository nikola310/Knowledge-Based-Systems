/**
 * @author Nikola
 */

var id;

function loadDiagnosis() {
	$.get("disease", function(data) {
		jQuery.each(data, function(i, val) {
			
			var box = $('<input>', {
				type: "radio",
				name: "radio-dis",
				id: "dis-" + val.diseaseId,
				value: "dis-" + val.diseaseId
			});
			
			var lbl = $('<label/>', {
				html: box[0].outerHTML + val.diseaseName
			});
			
			var div = $('<div/>', {
				class: "radio",
				html: lbl[0].outerHTML
			});
			
			$("#diseases-container").append(div[0].outerHTML);
		});
		
		loadTitle();
	});

}

function loadTitle() {
	id = getUrlParameter("id");
	$.get("diagnosis/" + id, function(data) {
		$("#title-diagnosis").val(
				"Change disease from current (" + data.diseaseName + ")");
		
		if(data.diagnosisActive){
			$("#diag-yes").prop('checked', true);
		}else{
			$("#diag-no").prop('checked', true);
		}
		
		$("#dis-" + data.diseaseId).prop('checked', true);
	});
}

function editDiag() {
	var val = $("input:radio[name ='radio-dis']:checked").val();
	var diseaseId = val.split("-")[1];
	var active = $("#diag-yes").is(':checked');
	
	var edit = JSON.stringify({
		"diagnosisId" : id,
		"diseaseId" : diseaseId,
		"diagnosisActive" : active
	});
	console.log(edit);

	$.ajax({
		type : 'PUT',
		url : 'diagnosis',
		data : edit,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Update successful.");
			window.location.replace("doctor.html");
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