/**
 * @author Nikola
 */

function loadUsers() {
	$.get("user", function(data) {
		console.log(data);
		jQuery.each(data, function(i, val) {

			var td1 = $('<td>', {
				html : val.userUsername
			});

			var td2 = $('<td>', {
				html : val.userName
			});

			var td3 = $('<td>', {
				html : val.userSurname
			});

			var td4 = $('<td>', {
				html : "Administrator"
			});
			if (val.userType == "D") {
				td4[0].innerHTML = "Doctor";
			}

			var a = $('<a>', {
				href : "edit-user.html?id=" + val.userId,
				html : "Edit"
			});

			var td5 = $('<td>', {
				html : a[0].outerHTML
			});

			var del = $('<button>', {
				id : val.userId,
				html : "Delete",
				onclick : "deleteUser(this);"
			});
			var td6 = $('<td>', {
				html : del[0].outerHTML
			});

			var tr = $('<tr>', {
				html : td1[0].outerHTML + td2[0].outerHTML + td3[0].outerHTML
						+ td4[0].outerHTML + td5[0].outerHTML
						+ td6[0].outerHTML
			});

			$("#users-table tbody").append(tr);
		});
	})
}

function deleteUser(e) {

	$.ajax({
		type : 'DELETE',
		url : 'user/' + e.id,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.alert("User deleted successfully.");
			$(e).closest('tr').remove();
		},
		fail : function(data) {
			console.log(data);
			window.alert("Fail!");
		},
		error : function(data) {
			console.log(data);
			window.alert("Error!");
		}
	})
}
