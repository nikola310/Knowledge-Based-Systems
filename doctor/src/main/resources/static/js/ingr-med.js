/**
 * @author Nikola
 */

var id;

function load() {
	id = getUrlParameter("id");
	$.get("medicine/" + id, function(data) {
		$("#medicine-name").text(
				"Add ingredients to medicine \"" + data.medicineName + "\"");
	});
	
	$.get("ingredient", function(lista){
		console.log(lista.length);
		
		$.get("ingr-med/med/" + id, function(data){
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
	
	jQuery.each(kids, function(i, val){
		
		if(val.checked){
			
			var ingrMed = JSON.stringify({
				"medicineId" : id,
				"ingredientId" : val.id.split("-")[1]
			});

			$.ajax({
				type : 'POST',
				url : 'ingr-med',
				data : ingrMed,
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					window.alert("Ingredients added successfully.");
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