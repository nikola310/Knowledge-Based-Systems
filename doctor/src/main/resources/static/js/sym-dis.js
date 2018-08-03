/**
 * @author Nikola
 */

var id;

function load() {
	id = getUrlParameter("id");
	$.get("disease/" + id, function(data) {
		$("#disease-name").text(
				"Add symptoms to disease \"" + data.diseaseName + "\"");
	});
	
	$.get("symptom", function(lista){
		console.log(lista.length);
		
		$.get("sym-disease/dis/" + id, function(data){
			if(data.length == 0){
				loadSymptoms(lista);
			}else{
			
			indeksi = [];
			for(i = 0; i < data.length; i++){
				for(j = 0; j < lista.length; j++){
					if(data[i].symptomId === lista[j].symId){
						indeksi.push(j);
					}
				}
			}
			
			Array.sort(indeksi);
			
			for(i = indeksi.length - 1; i >= 0; i--){
				lista.splice(i, 1);
			}
			
			loadSymptoms(lista);
			
			}
		});
		
	});
}

function loadSymptoms(data){
	jQuery.each(data, function(i, val) {
		
		var box = $('<input>', {
			type: "checkbox",
			value: val.symDesc,
			id: "sym-" + val.symId,
			class: ".checkbox-circle"
		});
		
		$("#sym-container").append(box[0].outerHTML + " " + val.symDesc + '<br/>');
		
	});
		
}

function addSymptoms(){
	kids = $("#sym-container").children(":input");
	
	jQuery.each(kids, function(i, val){
		
		if(val.checked){
			
			var symDis = JSON.stringify({
				"diseaseId" : id,
				"symptomId" : val.id.split("-")[1]
			});

			$.ajax({
				type : 'POST',
				url : 'sym-disease',
				data : symDis,
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
		}
	});
	
	return false;
}