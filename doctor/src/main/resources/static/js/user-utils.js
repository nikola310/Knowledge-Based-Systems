/**
 * @author Nikola
 */

function checkUser(redirect) {
	$.get("login/check", function(data) {
		if (data == true) {
			var a = $('<a/>', {
				class: "nav-link",
				href: "#",
				onclick: "logout();",
				html: "Logout"
			});
			
			var src = $('<script/>', {
				type: "text/javascript",
				src: "js/logout.js"
			});
			
			$('head').append(src[0].outerHTML);
			
			var li = $('<li/>', {
				class: "nav-item",
				html: a[0].outerHTML
			});
			
			$("#navbar-list").append(li);
		} else if (data == false || data == null) {
			if(redirect){
				window.location.replace("index.html");
			}
			
			var a = $('<a/>', {
				class: "nav-link",
				href: "login.html",
				html: "Login"
			});
			
			var li = $('<li/>', {
				class: "nav-item",
				html: a[0].outerHTML
			});
			
			$("#navbar-list").append(li);
		} else {
			console.log("Error!");
		}

	});
}

function checkLogin(){
	$.get("login/check", function(data) {
		if(data){
			window.location.replace("index.html");
		}
	});
}