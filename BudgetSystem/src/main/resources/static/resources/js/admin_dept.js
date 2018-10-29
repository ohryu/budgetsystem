$(document).ready(function() {
	var companydata =[];
	var groupdata = [];
	var add = 1;
	
	getData();
	
	function getData(){
		
		$.ajax({
			type : "GET",
			url : "/service/getallcompany",
			success : function(data) {
				companydata = data;		
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
				groupdata = data;
			},
			error : function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
		$("#dept").empty();
		$.ajax({
			type: "GET",
			url : "/service/getdepts",
			success : function(data){
				$.each(data, function(key, val){
					var comp = 0;
					comprow = 0;
					table = "";
					if(val.grouplist.length==0){
						table+='<tr><td >'+val.company.companyname+'</td>';
						table+='<td ><button class="btn btn-info del-comp" data-comp-id="'+val.company.companyid+'">Delete</button>\
									<button type="button" class="btn btn-info btn-lg edit-comp" data-comp-id="'+val.company.companyid+'"\
											data-toggle="modal" data-target="#editCompModal">\
											<span class="glyphicon glyphicon-edit"></span>\
									</button>\
								</td>';
						table+='<td></td><td></td><td></td><td></td><td></td><td></td></tr>';
						$("#dept").append(table);
					}
					else{
						$.each(val.grouplist, function(key1, val1){
							if(val1.deptlist.length==0) comprow+=1;
							else comprow+=val1.deptlist.length;
						})
						$.each(val.grouplist, function(key1, val1){
							if(val1.deptlist.length==0){
								if(comp == 0){
									table+='<tr><td rowspan="'+comprow+'">'+val.company.companyname+'</td>';
									table+='<td rowspan="'+comprow+'"><button class="btn btn-info del-comp" data-comp-id="'+val.company.companyid+'">Delete</button>\
												<button type="button" class="btn btn-info btn-lg edit-comp" data-comp-id="'+val.company.companyid+'"\
														data-toggle="modal" data-target="#editCompModal">\
														<span class="glyphicon glyphicon-edit"></span>\
												</button>\
											</td>';
								}
								table+='<td>'+val1.group.groupcode+'</td>';
								table+='<td><button class="btn btn-info del-group" data-group-id="'+val1.group.groupid+'">Delete</button>\
											<button type="button" class="btn btn-info btn-lg edit-group" data-group-id="'+val1.group.groupid+'"\
													data-toggle="modal" data-target="#editGroupModal">\
													<span class="glyphicon glyphicon-edit"></span>\
											</button>\
										</td>';
								table+='<td></td><td></td><td></td><td></td></tr>';
								comp++;
							}else{
								var group = 0;
								$.each(val1.deptlist, function(key2, val2){
									table+='<tr>';
									if(comp == 0){
										table+='<td rowspan="'+comprow+'">'+val.company.companyname+'</td>';
										table+='<td rowspan="'+comprow+'"><button class="btn btn-info del-comp" data-comp-id="'+val.company.companyid+'">Delete</button>\
													<button type="button" class="btn btn-info btn-lg edit-comp" data-comp-id="'+val.company.companyid+'"\
															data-toggle="modal" data-target="#editCompModal">\
															<span class="glyphicon glyphicon-edit"></span>\
													</button>\
												</td>';
									}
									if(group == 0){
										table+='<td rowspan="'+val1.deptlist.length+'">'+val1.group.groupcode+'</td>';
										table+='<td rowspan="'+val1.deptlist.length+'"><button class="btn btn-info del-group" data-group-id="'+val1.group.groupid+'">Delete</button>\
													<button type="button" class="btn btn-info btn-lg edit-group" data-group-id="'+val1.group.groupid+'"\
															data-toggle="modal" data-target="#editGroupModal">\
															<span class="glyphicon glyphicon-edit"></span>\
													</button>\
												</td>';
									}
									table+='<td>'+val2.deptname+'</td>';
									table+='<td>'+val2.control+'</td>';
									table+='<td>'+val2.sponsor+'</td>';
									table+='<td><button class="btn btn-info del-dept" data-dept-id="'+val2.deptid+'">Delete</button>\
												<button type="button" class="btn btn-info btn-lg edit-dept" data-dept-id="'+val2.deptid+'"\
														data-toggle="modal" data-target="#editDeptModal">\
														<span class="glyphicon glyphicon-edit"></span>\
												</button>\
										</td>';
									table+='</tr>';
									group++;
									comp++;
								})		
							}
						})
						$("#dept").append(table);
					}
				});
			},
			error : function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		})
	}
	
	$("body").off("click", ".edit-comp").on("click", ".edit-comp", function(){
		comp = $(this);
		$.ajax({
			type: "GET",
			url: "/service/getcomp/" + $(this).attr("data-comp-id"),
			success: function(data){
				$("#edit-comp-compname").val(data.companyname);
				$("#edit-comp-btn").click(function(){
					$.ajax({
						type: "POST",
						url : "/service/editcomp",
						contentType : "application/json",
			    		data: JSON.stringify([$("#edit-comp-compname").val(), comp.attr("data-comp-id")]),
						accept: 'text/plain',
						success : function(data){
							if(data.indexOf("Successfully")>-1)
								getData();
							else alert(data);
						},
						error: function(e){
							alert("System error, Please try again!")
							console.log("ERROR : ", e);
						}
					})
				});
			},
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR: ",e);
			}
		})
	})
	$("body").off("click", ".edit-group").on("click", ".edit-group", function(){
		group = $(this);
		$.ajax({
			type: "GET",
			url: "/service/getgroup/" + $(this).attr("data-group-id"),
			success: function(data){
				$("#edit-group-groupcode").val(data.groupcode);
				$("#edit-group-btn").click(function(){
					$.ajax({
						type: "POST",
						url : "/service/editgroup",
						contentType : "application/json",
			    		data: JSON.stringify([$("#edit-group-groupcode").val(), group.attr("data-group-id")]),
						accept: 'text/plain',
						success : function(data){
							if(data.indexOf("Successfully")>-1)
								getData();
							else alert(data);
						},
						error: function(e){
							alert("System error, Please try again!")
							console.log("ERROR : ", e);
						}
					})
				});
			},
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR: ",e);
			}
		})
	})
	$("body").off("click", ".edit-dept").on("click", ".edit-dept", function(){
		dept= $(this);
		$.ajax({
			type: "GET",
			url: "/service/getdept/" + $(this).attr("data-dept-id"),
			success: function(data){
				$("#edit-dept-deptname").val(data.deptname);
				$("#edit-dept-deptcode").val(data.deptcode);
				if(data.control==true){
					$("#edit-dept-control").prop("checked",true);
				}
				if(data.sponsor==true){
					$("#edit-dept-sponsor").prop("checked",true);
				}
				$("#edit-dept-btn").click(function(){
					var control = "false";
					var sponsor = "false";
					if($("#edit-dept-control").is(":checked")) control = 'true'; 
					if($("#edit-dept-sponsor").is(":checked")) sponsor = 'true';
					$.ajax({
						type: "POST",
						url : "/service/editdept",
						contentType : "application/json",
			    		data: JSON.stringify([$("#edit-dept-deptcode").val(), $("#edit-dept-deptname").val(), dept.attr("data-dept-id"), control, sponsor]),
						accept: 'text/plain',
						success : function(data){
							if(data.indexOf("Successfully")>-1){
								getData();
							}
							else alert(data);
						},	
						error: function(e){
							alert("System error, Please try again!")
							console.log("ERROR : ", e);
						}
					})
				});
			},
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR: ",e);
			}
		})
	})
	$("body").off("click", "#add-comp").on("click", "#add-comp", function(){
		$("#new-dept").empty();
		add = 1;
		$("#new-dept").append('<label>Company Name: </label><input type="text" id="add-comp-name">');
	})
	$("body").off("click", "#add-group").on("click", "#add-group", function(){
		add = 2;
		data='<label>Company: </label>';
		data+='<select id="add-group-comp">';
				$.each(companydata, function(key, val){
					data+='<option value="'+val.companyid+'">'+val.companyname+'</option>';
				})
		data+='</select><br>';
		$("#new-dept").empty();
		$("#new-dept").append(data);
		$("#new-dept").append('<label>Group Code: </label><input type="text" id="add-group-code">');
	})
	$("body").off("click", "#add-dept").on("click", "#add-dept", function(){
		add = 3;
		data='<label>Company: </label>';
		data+='<select id="add-dept-comp">';
		$.each(companydata, function(key, val){
			data+='<option value="'+val.companyid+'">'+val.companyname+'</option>';
		});
		data+='</select><br><label>Group: </label><select id="add-dept-group">';
		$.each(groupdata, function(key, val){
			if(val.company.companyid == companydata[0].companyid){
				data+='<option value="'+val.groupid+'">'+val.groupcode+'</option>';
			}
		});
		data+='</select><br>';
		$("#new-dept").empty();
		$("#new-dept").append(data);
		$("#new-dept").append('<label>Dept Name: </label><input type="text" id="add-dept-name"><br>');
		$("#new-dept").append('<label>Dept Code: </label><input type="text" id="add-dept-code"><br>');
		$("#new-dept").append('<input type="checkbox" id="add-dept-control">Control Dept<br><input type="checkbox" id="add-dept-sponsor">Sponsor Dept');
		$("#add-dept-comp").on("change", function(){
			$("#add-dept-group").empty();
			$.each(groupdata, function(key, val){
				if(val.company.companyid == $("#add-dept-comp").val()){
					$("#add-dept-group").append('<option value="'+val.groupid+'">'+val.groupcode+'</option>');
				}
			});
		});
	})
		$("body").off("click", "#add-btn").on("click", "#add-btn", function(){
			if(add == 1){
				$.ajax({
					type: "POST",
					url : "/service/addcomp",
					contentType : "application/json",
		    		data: JSON.stringify([$("#add-comp-name").val()]),
					accept: 'text/plain',
					success : function(data){
						console.log(data);
						if(data.indexOf("Successfully")>-1)
							getData();
						else alert(data);
					},	
					error: function(e){
						alert("System error, Please try again!")
						console.log("ERROR : ", e);
					}
				})
			}else if(add == 2){
				$.ajax({
					type: "POST",
					url : "/service/addgroup",
					contentType : "application/json",
		    		data: JSON.stringify([$("#add-group-comp").val(), $("#add-group-code").val()]),
					accept: 'text/plain',
					success : function(data){
						console.log(data);
						if(data.indexOf("Successfully")>-1)
							getData();
						else alert(data);
					},	
					error: function(e){
						alert("System error, Please try again!")
						console.log("ERROR : ", e);
					}
				})
			}else{
				var control = "false";
				var sponsor = "false";
				if($("#add-dept-control").is(":checked")) control = "true"; 
				if($("#add-dept-sponsor").is(":checked")) sponsor = "true";
				$.ajax({
							type: "POST",
							url : "/service/adddept",
							contentType : "application/json",
				    		data: JSON.stringify([$("#add-dept-group").val(), $("#add-dept-code").val(), $("#add-dept-name").val(), control, sponsor]),
							accept: 'text/plain',
							success : function(data){
								if(data.indexOf("Successfully")>-1)
									getData();
								else alert(data);
							},	
							error: function(e){
								alert("System error, Please try again!")
								console.log("ERROR : ", e);
							}
				});
			}
		})
		
		$("body").off("click", ".del-comp").on("click", ".del-comp", function(){
			$.ajax({
				type: "GET",
				url : "/service/delcompany/" +$(this).attr("data-comp-id"),
				success : function(data){
					console.log(data);
					getData();
				},	
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
			})
		})
		$("body").off("click", ".del-group").on("click", ".del-group", function(){
			$.ajax({
				type: "GET",
				url : "/service/delgroup/" +$(this).attr("data-group-id"),
				success : function(data){
					console.log(data);
					getData();
				},	
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
			})
		})
		$("body").off("click", ".del-dept").on("click", ".del-dept", function(){
			$.ajax({
				type: "GET",
				url : "/service/deldept/" +$(this).attr("data-dept-id"),
				success : function(data){
					console.log(data);
					getData();
				},	
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
			})
		})
})