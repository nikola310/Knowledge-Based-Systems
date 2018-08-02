/**
 * @author Nikola
 */

var id;

function loadIngredient() {
	id = getUrlParameter("id");
	$.get("ingredient/" + id, function(data) {
		$("#name").val(data.ingredientName);
	});
}

function editIngredient() {

	var edit = JSON.stringify({
		"ingredientId" : id,
		"ingredientName" : $("#name").val(),
	});

	$.ajax({
		type : 'PUT',
		url : 'medicine',
		data : edit,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("Update successful.");
			window.location.replace("admin.html");
		},
		fail : function(data) {
			console.log(data);
			window.alert("Bad credentials!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Error!");
		}
	});

	return false;
}