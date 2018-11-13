$(document).ready(function() {
	var company =[];
	var group = [];
	var dept =[];
	$.ajax({
		type : "GET",
		url : "/service/getallcompany",
		success : function(data) {
			company = data;		
		},
		error : function(e){
			alert("System error, Please try again!")
			console.log("ERROR : ", e);
		}
	});
	$.ajax({
		type : "GET",
		url : "/service/getallgroup",
		success : function(data) {
			group = data;
		},
		error : function(e){
			alert("System error, Please try again!")
			console.log("ERROR : ", e);
		}
	});
	$.ajax({
		type : "GET",
		url : "/service/getalldept",
		success : function(data) {
			dept = data;
		},
		error : function(e){
			alert("System error, Please try again!")
			console.log("ERROR : ", e);
		}
	});
	getData();
	
	function getData(){
		$.ajax({
			type : "GET",
			url : '/service/getallaccount',
			success : function(data) {
				$("tbody#account").empty();
				$.each(data, function(key, val){
					row='<tr id="'+val.userid+'">\
							<td>'+ val.username +'</td>\
							<td>'+ val.fullname +'</td>\
							<td>'+ val.role.rolename +'</td>\
							<td>\
								<button class="btn btn-info edit" data-toggle="modal" data-target="#editModal">Edit</button>\
								<button class="btn btn-info reset">Reset Password</button>\
								<button class="btn btn-info delete">Delete</button>\
							</td>\
						</tr>';
					$("tbody#account").append(row);
				})
			},
			error : function(e) {
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
	}
	
	//reset password-------------------------------------------------------------------------
	$("body").off("click", ".reset").on("click", ".reset", function(){
		$.ajax({
			type : "GET",
			url : '/service/resetpass/' + $(this).closest("tr").attr("id"),
			success : function(data){
				console.log(data);
			},
			error : function(e) {
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
	});
	
	//delete user-----------------------------------------------------------------------------
	$("body").off("click", ".delete").on("click", ".delete", function(){
		row = $(this).closest("tr");
		$.ajax({
			type : "GET",
			url : '/service/deleteaccount/' + $(this).closest("tr").attr("id"),
			success : function(data){
				row.remove();
				console.log(data);
			},
			error : function(e) {
				alert("System error, Please try again!")
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
		if($("#new-pass").val()!= $("#pass-confirm").val()){
			alert("Confirm password does not match New password!");
		}else{
			if($("#new-pass").val().length<8){
				alert("Password must contains atleast 8 characters!")
			}else{
				$.ajax({
					type : "POST",
					async: false,
					contentType : "application/json",
					url : '/service/changepassword',
					data : JSON.stringify(password),
					accept: 'text/plain',
					success : function(data){
						alert(data);
						console.log(data);
					},
					error : function(e) {
						alert("System error, Please try again!")
						console.log("ERROR : ", e);
					}
				});
			}
		}
		
	});
	//add admin----------------------------------------------------------------------------
	$("body").off("click", "#add-admin").on("click", "#add-admin", function(){
	    	$("#new-acc").empty();
	    	$("#new-acc").attr("data-role","1")
	    	$("#new-acc").append('<table>\
		         		<tr>\
		         			<td><label>Username: </label></td>\
		         			<td><input type="text" id="username"></td>\
		         		</tr>\
		         		<tr>\
		         			<td><label>Full name: </label></td>\
		         			<td><input type="text" id="fullname"></td>\
		         		</tr>\
		         	</table>');
	    })
	 //add user----------------------------------------------------------------------------
	 $("body").off("click", "#add-user").on("click", "#add-user", function(){
		 var grp;
	    	$("#new-acc").empty();
	    	$("#new-acc").attr("data-role","2")
	    	row = '<table>\
	         		<tr>\
	     			<td><label>Username: </label></td>\
	     			<td><input type="text" id="username"></td>\
	     		</tr>\
	     		<tr>\
	     			<td><label>Full name: </label></td>\
	     			<td><input type="text" id="fullname"></td>\
	     		</tr>\
	     	</table>\
	     	<table>\
	     		<thead>\
	     			<tr>\
	     				<th>ROLE</th>\
	     				<th>COMPANY</th>\
	     				<th>GROUP</th>\
	     				<th>DEPT</th>\
	    				<th></th>\
	     			</tr>\
	     		</thead>\
	     		<tbody id="add-role" class="acc">\
	         	</tbody>\
	     	</table>\
	     	<button type="button" class="btn btn-default" id="add-row" data-role-id="1">Add Row</button>';
	    	$("#new-acc").append(row);
	    	$("#add-row").trigger("click");
	  });
	  //add role-------------------------------------------------------------------------------------
	  $("body").off("click", "#add-row").on("click", "#add-row", function(){
		  var grp=0;
		  row='<tr>\
   			<td>\
				<select class="role">\
					<option value="1">Reviewer</option>\
					<option value="2">Reporter</option>\
   				</select>\
   			</td>\
   			<td>\
   				<select class="company">';
			$.each(company, function(key, val){
				row+='<option value="'+val.companyid+'">'+val.companyname+'</option>';
			});
		   					
		  row+=	'</select>\
		   			</td>\
		   			<td>\
		   				<select class="group">';
			  $.each(group, function(key, val){
				  if(val.company.companyid==company[0].companyid){
	   	    		  row+='<option value="'+val.groupid+'">'+val.groupcode+'</option>';
	   	    		  if(grp==0) grp = val.groupid;
	   	    	  }
			  });
		   			row+='	</select>\
		   			</td>\
		   			<td>\
		   				<select class="dept" disabled>';
		   			$.each(dept, function(key, val){
			   	    	 if(val.group.groupid==grp)
			   	    		row+='<option value="'+val.deptid+'">'+val.deptname+'</option>';
					  });
		   			row+='</select>\
		   			</td>\
		   			<td>\
		   				<button type="button" class="btn btn-default del-row" data-role-id="1">Delete</button>\
		   			</td>\
				</tr>';
		  $("tbody.acc").append(row);
		 
	  });
	 		
	  $("body").on("change",".role", function(){
		  if($(this).val()==1){
			  $(this).closest("tr").find(".dept").attr('disabled','disabled');
		  }else{
			  $(this).closest("tr").find(".dept").removeAttr('disabled');
		  }
	  })
	  //get group by company----------------------------------------------------------------------------
	  $("body").on("change", "td .company", function(){
		  var comp = $(this).closest("td");
		  comp.next().find(".group").empty();
		  $.each(group, function(key, val){
			  if(val.company.companyid==comp.find(".company").val())
				comp.next().find(".group").append('<option value="'+val.groupid+'">'+val.groupcode+'</option>');
		  });
	  });
	  //get dept by group----------------------------------------------------------------------------------
	  $("body").on("change", "td .group", function(){
		  var group = $(this).closest("td");
		  group.next().find(".dept").empty();
		  $.each(dept, function(key, val){
			  if(val.group.groupid == group.find(".group").val())
				group.next().find(".dept").append('<option value="'+val.deptid+'">'+val.deptname+'</option>');
		  });
	  });
	  //save account----------------------------------------------------------------------------------------
	  $("body").off("click", "#add-account").on("click", "#add-account", function(){
		  if($("#new-acc").attr("data-role") == 2){
			  roles = [];
				$("#add-role tr").each(function(){
					role = {
							role : $(this).find(".role").val(),
							comp : $(this).find(".company").val(),
							group : $(this).find(".group").val(),
							dept : $(this).find(".dept").val(),
					};
					roles.push(role);
				});	  
				acc = {
						username: $("#username").val(),
						fullname: $("#fullname").val(),
						role : roles
				};
				$.ajax({
		    		type : "POST",
		    		url : "/service/saveuser",
		    		contentType : "application/json",
		    		data: JSON.stringify(acc),
					accept: 'text/plain',
					success : function(data){
						if(data.indexOf("Successfully")!=-1){
							console.log(data);
							getData();
						}else {
							alert(data);
						}
						
					},
					error: function(e){
						alert("System error, Please try again!")
						console.log("ERROR : ", e);
					}
		    	});
	  	}else{
	  		acc = {
					username: $("#username").val(),
					fullname: $("#fullname").val(),
					role : []
			};
	  		$.ajax({
	    		type : "POST",
	    		url : "/service/saveadmin",
	    		contentType : "application/json",
	    		data: JSON.stringify(acc),
				accept: 'text/plain',
				success : function(data){
					if(data.indexOf("Successfully")!=-1){
						console.log(data);
						getData();
					}else {
						alert(data);
					}
				},
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
	    	});
	  	}
	  });
	//delete role--------------------------------------------------------------------------------------
	$("body").off("click", ".del-row").on("click", ".del-row", function(){
		$(this).closest("tr").remove();
	});

	//edit acc-----------------------------------------------------------------------------------------
	$("body").off("click", ".edit").on("click", ".edit", function(){
		var userid = $(this).closest("tr").attr("id");
		$("#edit-acc").empty();
		if($(this).closest("td").prev().text()=="ROLE_USER"){
			$("#editModal").attr("data-role","2");
			$.ajax({
				type: "GET",
				url : "/service/getuser/"+ userid,
				success : function(data){
					$("#edit-acc").attr("data-role","2")
			    	row = '<table>\
			         		<tr>\
			     			<td><label>Username: </label></td>\
			     			<td><input type="text" id="username-edit" value="'+data[0].user.username+'" disabled></td>\
			     		</tr>\
			     		<tr>\
			     			<td><label>Full name: </label></td>\
			     			<td><input type="text" id="fullname-edit" value="'+data[0].user.fullname+'"></td>\
			     		</tr>\
			     	</table>\
			     	<table>\
			     		<thead>\
			     			<tr>\
			     				<th>ROLE</th>\
			     				<th>COMPANY</th>\
			     				<th>GROUP</th>\
			     				<th>DEPT</th>\
			    				<th></th>\
			     			</tr>\
			     		</thead>\
			     		<tbody id="edit-role" class="acc">';
					$.each(data, function(key, val){
						row+= '<tr>\
				   			<td>\
							<select class="role">\
								<option value="1">Reviewer</option>';
						if(val.sysrole.roleid==2)
								row+='<option value="2" selected>Reporter</option>';
						else row+='<option value="2">Reporter</option>';
			   			row+=	'</select>\
			   			</td>\
			   			<td>\
			   				<select class="company">';
						$.each(company, function(key1, val1){
							if(val.dept!=null){
								if(val1.companyid == val.dept.group.company.companyid){
									row+='<option value="'+val1.companyid+'" selected>'+val1.companyname+'</option>';
								}else{
									row+='<option value="'+val1.companyid+'">'+val1.companyname+'</option>';
								}
							}else{
								if(val1.companyid == val.group.company.companyid){
									row+='<option value="'+val1.companyid+'" selected>'+val1.companyname+'</option>';
								}else{
									row+='<option value="'+val1.companyid+'">'+val1.companyname+'</option>';
								}
							}
						});
					   					
					  row+=	'</select>\
					   			</td>\
					   			<td>\
					   				<select class="group">';
						  $.each(group, function(ke1y, val1){
							  if(val.dept!=null){
									if(val1.groupid == val.dept.group.groupid){
										row+='<option value="'+val1.groupid+'" selected>'+val1.groupcode+'</option>';
									}else if(val1.company.companyid==val.dept.group.company.companyid){
										row+='<option value="'+val1.groupid+'">'+val1.groupcode+'</option>';
									}
								}else{
									if(val1.groupid == val.group.groupid){
										row+='<option value="'+val1.groupid+'" selected>'+val1.groupcode+'</option>';
									}else if(val1.company.companyid==val.group.company.companyid){
										row+='<option value="'+val1.groupid+'">'+val1.groupcode+'</option>';
									}
								}
						  });
					   			row+='	</select>\
					   			</td>\
					   			<td>';
					   			if(val.dept!=null){
					   				row+= '<select class="dept">';
					   				$.each(dept, function(key1, val1){
										if(val1.deptid == val.dept.deptid){
											row+='<option value="'+val1.deptid+'" selected>'+val1.deptname+'</option>';
										}else if(val1.group.groupid==val.dept.group.groupid){
											row+='<option value="'+val1.deptid+'">'+val1.deptname+'</option>';
										}
								});
					   			}else{
					   				row+= '<select class="dept" disabled>';
					   				$.each(dept, function(key1, val1){
					   					if(val1.group.groupid==val.group.groupid){
											row+='<option value="'+val1.deptid+'">'+val1.deptname+'</option>';
										}
					   				});
					   			}
					   			
					   			row+='</select>\
					   			</td>\
					   			<td>\
					   				<button type="button" class="btn btn-default del-row" data-role-id="1">Delete</button>\
					   			</td>\
							</tr>';
					});
			         row+=	'</tbody>\
			     	</table>\
			     	<button type="button" class="btn btn-default" id="add-row" data-role-id="1">Add Row</button>';
			    	$("#edit-acc").append(row);
				},
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
			})
			
		}else{
			$("#editModal").attr("data-role","1");
			$.ajax({
				type: "GET",
				url: "/service/getadmin/"+ userid,
				success: function(data){
				row = '<table>\
         		<tr>\
     			<td><label>Username: </label></td>\
     			<td><input type="text" id="username-edit" value="'+data.username+'" disabled></td>\
     			</tr>\
     			<tr>\
     			<td><label>Full name: </label></td>\
     			<td><input type="text" id="fullname-edit" value="'+data.fullname+'"></td>\
     			</tr>\
     			</table>';
				$("#edit-acc").append(row);
				},
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
			});
			
		}
	});
	
	//save account edited
	 $("body").off("click", "#edit-acc-btn").on("click", "#edit-acc-btn", function(){
		  if($("#editModal").attr("data-role") == 2){
			  roles = [];
				$("#edit-role tr").each(function(){
					role = {
							role : $(this).find(".role").val(),
							comp : $(this).find(".company").val(),
							group : $(this).find(".group").val(),
							dept : $(this).find(".dept").val(),
					};
					roles.push(role);
				});	  
				acc = {
						username: $("#username-edit").val(),
						fullname: $("#fullname-edit").val(),
						role : roles
				};
				$.ajax({
		    		type : "POST",
		    		url : "/service/edituser",
		    		contentType : "application/json",
		    		data: JSON.stringify(acc),
					accept: 'text/plain',
					success : function(data){
						if(data.indexOf("Successfully")!=-1){				
							$("#edit-close").trigger("click");
							alert(data);
							getData();
						}else {
							alert(data);
						}
					},
					error: function(e){
						console.log("ERROR : ", e);
					}
		    	});
	  	}else{
	  		acc = {
					username: $("#username-edit").val(),
					fullname: $("#fullname-edit").val(),
					role : []
			};
	  		$.ajax({
	    		type : "POST",
	    		url : "/service/editadmin",
	    		contentType : "application/json",
	    		data: JSON.stringify(acc),
				accept: 'text/plain',
				success : function(data){
					if(data.indexOf("Successfully")!=-1){
						console.log(data);
						getData();
					}else {
						alert(data);
					}
				},
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
	    	});
	  	}
	  });
})