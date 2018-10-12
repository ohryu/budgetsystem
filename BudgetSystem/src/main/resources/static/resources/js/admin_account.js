$(document).ready(function() {
	$.ajax({
		type : "GET",
		url : '/service/getallaccount',
		success : function(data) {
			$.each(data, function(key, val){
				row='<tr id="'+val.userid+'">\
						<td>'+ val.username +'</td>\
						<td>'+ val.fullname +'</td>\
						<td>'+ val.role.rolename +'</td>\
						<td>\
							<button class="btn btn-info edit">Edit</button>\
							<button class="btn btn-info reset">Reset Password</button>\
							<button class="btn btn-info delete">Delete</button>\
						</td>\
					</tr>';
				$("tbody#account").append(row);
			})
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
	
	//reset password-------------------------------------------------------------------------
	$("body").off("click", ".reset").on("click", ".reset", function(){
		$.ajax({
			type : "GET",
			url : '/service/resetpass/' + $(this).closest("tr").attr("id"),
			success : function(data){
				console.log(data);
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	});
	
	//delete user-----------------------------------------------------------------------------
	$("body").off("click", ".delete").on("click", ".delete", function(){
		$.ajax({
			type : "GET",
			url : '/service/deleteaccount/' + $(this).closest("tr").attr("id"),
			success : function(data){
				console.log(data);
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	});
	
	//change pass-----------------------------------------------------------------------------
	$("body").off("click", "#change-pass").on("click", "#change-pass", function(){
		var password = {
				oldpass: $("#current-pass").val(),
				newpass: $("#new-pass").val()
		};
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : '/service/changepassword',
			data : JSON.stringify(password),
			accept: 'text/plain',
			success : function(data){
				console.log(data);
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	});
})