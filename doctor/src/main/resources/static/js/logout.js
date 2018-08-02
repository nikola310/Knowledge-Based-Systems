/**
 * @author Nikola
 */

function logout() {

	$.post("logout", function(data) {
		window.location.replace("index.html");
	}).fail(function(response) {
		window.alert("Fail: " + response.responseText);
	}).error(function(response) {
		window.alert("Error: " + response.responseText);
	});

	return false;

}
