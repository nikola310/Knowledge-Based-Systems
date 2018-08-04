/**
 * @author Nikola
 */

var id;

function load() {
	id = getUrlParameter("id");
	$.get("patient/" + id, function(data) {
		$("#patient-info").text(
				"Add ingredients that cause allergic reaction to patient \"" + data.patientName + " " + data.patientSurname + "\"");
	});
	
	$.get("ingredient", function(lista){
		
		$.get("allergy/patient/" + id, function(data){
			if(data.length == 0){
				loadIngredients(lista);
			}else{
			
			indeksi = [];
			for(i = 0; i < data.length; i++){
				for(j = 0; j < lista.length; j++){
					if(data[i].ingredientId === lista[j].ingredientId){
						indeksi.push(j);
					}
				}
			}
			
			Array.sort(indeksi);
			
			for(i = indeksi.length - 1; i >= 0; i--){
				lista.splice(i, 1);
			}
			
			loadIngredients(lista);
			
			}
		});
		
	});
		
}

function loadIngredients(data){
	jQuery.each(data, function(i, val) {
		
		var box = $('<input>', {
			type: "checkbox",
			value: val.ingredientName,
			id: "ingr-" + val.ingredientId,
			class: ".checkbox-circle"
		});
		
		$("#ingr-container").append(box[0].outerHTML + " " + val.ingredientName + '<br/>');
		
	});
		
}

function addIngredients(){
	kids = $("#ingr-container").children(":input");
	
	allergies = [];
	
	jQuery.each(kids, function(i, val){
		
		if(val.checked){
			
			var allergy = {
				"patientId" : parseInt(id),
				"ingredientId" : parseInt(val.id.split("-")[1])
			};

			allergies.push(allergy);
		}
	});
	
	$.ajax({
		type : 'POST',
		url : 'allergy/all',
		data : JSON.stringify(allergies),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Ingredients added successfully.");
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