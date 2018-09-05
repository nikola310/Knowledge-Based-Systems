/**
 * @author Nikola
 */

var id;

function load() {
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-info").text(
				"Add medicine that cause allergic reaction to patient \"" + data.patientName + " " + data.patientSurname + "\"");
	});
	
	$.get("medicine", function(lista){
		
		$.get("med-allergy/patient/" + id, function(data){
			if(data.length == 0){
				loadMedicine(lista);
			}else{
			
			indeksi = [];
			for(i = 0; i < data.length; i++){
				for(j = 0; j < lista.length; j++){
					if(data[i].medicineId === lista[j].medicineId){
						indeksi.push(j);
					}
				}
			}
			
			Array.sort(indeksi);
			
			for(i = indeksi.length - 1; i >= 0; i--){
				lista.splice(i, 1);
			}
			
			loadMedicine(lista);
			
			}
		});
		
	});
		
}

function loadMedicine(data){
	jQuery.each(data, function(i, val) {
		
		var box = $('<input>', {
			type: "checkbox",
			value: val.medicineName,
			id: "med-" + val.medicineId,
			class: ".checkbox-circle"
		});
		
		$("#med-container").append(box[0].outerHTML + " " + val.medicineName + '<br/>');
		
	});
}

function addMedicine(){
	kids = $("#med-container").children(":input");
	
	allergies = [];
	
	jQuery.each(kids, function(i, val){
		
		if(val.checked){
			
			var allergy = {
				"patientId" : parseInt(id),
				"medicineId" : parseInt(val.id.split("-")[1])
			};

			allergies.push(allergy);
		}
	});
	
	$.ajax({
		type : 'POST',
		url : 'med-allergy/all',
		data : JSON.stringify(allergies),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Medicine added successfully.");
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