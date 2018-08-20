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
		
		jQuery.each(lista, function(i, val) {
			
			var box = $('<input>', {
				type: "checkbox",
				value: val.medicineName,
				id: "med-" + val.medicineId,
				class: ".checkbox-circle"
			});
			
			$("#med-container").append(box[0].outerHTML + " " + val.medicineName + '<br/>');
			
		});
		
	});
}

function addMeds(){
	kids = $("#med-container").children(":input");
	
	meds = [];
	
	jQuery.each(kids, function(i, val){
		
		if(val.checked){
			
			// var Med = {
				// "patientId" : parseInt(id),
				// "medicineId" : parseInt(val.id.split("-")[1])
			// };Med
			
			meds.push(parseInt(val.id.split("-")[1]));

		}
	});
	
	$.ajax({
		type : 'POST',
		url : 'therapy/check',
		data : JSON.stringify(meds),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			if(data.ingredients.length == 0 && data.medicine.length == 0){
				createTherapy(meds);
			}else{
				toprint = "";
				toprint2 = "";
				if(data.ingredients.length > 0){
					toprint = "Patient is allergic to ingredients: ";
					data.ingredients.forEach(function(item, index, array) {
						if(index == 0){
							toprint += " " + item;
						}else{
							toprint += ", " + item;
						}
					});
				}
				
				if(data.medicine.length > 0){
					toprint2 = "Patient is allergic to medicine: ";
					data.medicine.forEach(function(item, index, array) {
						if(index == 0){
							toprint2 += " " + item;
						}else{
							toprint2 += ", " + item;
						}
					});
				}
				
				window.alert(toprint + "\n" + toprint2);
			}
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

function createTherapy(meds){
	toSend = [];
	jQuery.each(meds, function(i, val) {
		
		dto = {
				"medicineId" : val,
				"patientId" : parseInt(id),
				"therapyDate" : new Date().getTime()
		}
		toSend.push(dto);
	});
	
	$.ajax({
		type : 'POST',
		url : 'therapy/all',
		data : JSON.stringify(toSend),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Success!");
			window.location.replace("doctor.html");
		},
		fail : function(data) {
			window.alert("Fail!");
		},
		error : function(data) {
			window.alert("Error!");
		}
	});
}