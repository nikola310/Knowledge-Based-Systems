/**
 * @author Nikola
 */

function createIngredient() {

	var ing = JSON.stringify({
		"ingredientName" : $("#name").val()
	});

	$.ajax({
		type : 'POST',
		url : 'ingredient',
		data : ing,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Success!");
			window.location.replace("admin.html");
		},
		fail : function(data) {
			console.log(data);
			window.alert("Fail!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Error!");
		}
	});

	return false;
}