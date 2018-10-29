$(document).ready(function() {
	var bl =[];
	var wb = [];
	var add = 0;
	
	getData();
	
	function getData(){
		
		$.ajax({
			type : "GET",
			url : "/service/getallbline",
			success : function(data) {
				bl = data;		
			},
			error : function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
		$.ajax({
			type : "GET",
			url : "/service/getallwb",
			success : function(data) {
				wb = data;
			},
			error : function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
		$("#budget").empty();
		$.ajax({
			type: "GET",
			url : "/service/getbgs",
			success : function(data){
				$.each(data, function(key, val){
					var bline = 0;
					blinerow = 0;
					table = "";
					if(val.wblist.length==0){
						table+='<tr><td >'+val.bline.blname+'</td>';
						table+='<td ><button class="btn btn-info del-bl" data-bl-id="'+val.bline.blid+'">Delete</button>\
									<button type="button" class="btn btn-info btn-lg edit-bl" data-bl-id="'+val.bline.blid+'"\
											data-toggle="modal" data-target="#editCompModal">\
											<span class="glyphicon glyphicon-edit"></span>\
									</button>\
								</td>';
						table+='<td></td><td></td><td></td><td></td></tr>';
						$("#budget").append(table);
					}
					else{
						$.each(val.wblist, function(key1, val1){
							if(val1.bglist.length==0) blinerow+=1;
							else blinerow+=val1.bglist.length;
						})
						$.each(val.wblist, function(key1, val1){
							if(val1.bglist.length==0){
								if(bline == 0){
								table+='<tr><td rowspan="'+blinerow+'">'+val.bline.blname+'</td>';
									table+='<td rowspan="'+blinerow+'"><button class="btn btn-info del-bl" data-bl-id="'+val.bline.blid+'">Delete</button>\
												<button type="button" class="btn btn-info btn-lg edit-bl" data-bl-id="'+val.bline.blid+'"\
														data-toggle="modal" data-target="#editBLModal">\
														<span class="glyphicon glyphicon-edit"></span>\
												</button>\
											</td>';
								}
								table+='<td>'+val1.wb.wbname+'</td>';
								table+='<td><button class="btn btn-info del-wb" data-wb-id="'+val1.wb.wbid+'">Delete</button>\
											<button type="button" class="btn btn-info btn-lg edit-wb" data-wb-id="'+val1.wb.wbid+'"\
													data-toggle="modal" data-target="#editWBModal">\
													<span class="glyphicon glyphicon-edit"></span>\
											</button>\
										</td>';
								table+='<td></td><td></td></tr>';
								bline++;
							}else{
								var wb = 0;
								$.each(val1.bglist, function(key2, val2){
									table+='<tr>';
									if(bline == 0){
										table+='<td rowspan="'+blinerow+'">'+val.bline.blname+'</td>';
										table+='<td rowspan="'+blinerow+'"><button class="btn btn-info del-bl" data-bl-id="'+val.bline.blid+'">Delete</button>\
													<button type="button" class="btn btn-info btn-lg edit-bl" data-bl-id="'+val.bline.blid+'"\
															data-toggle="modal" data-target="#editBLModal">\
															<span class="glyphicon glyphicon-edit"></span>\
													</button>\
												</td>';
									}
									if(wb == 0){
										table+='<td rowspan="'+val1.bglist.length+'">'+val1.wb.wbname+'</td>';
										table+='<td rowspan="'+val1.bglist.length+'"><button class="btn btn-info del-wb" data-wb-id="'+val1.wb.wbid+'">Delete</button>\
													<button type="button" class="btn btn-info btn-lg edit-wb" data-wb-id="'+val1.wb.wbid+'"\
															data-toggle="modal" data-target="#editWBModal">\
															<span class="glyphicon glyphicon-edit"></span>\
													</button>\
												</td>';
									}
									table+='<td>'+val2.bgname+'</td>';
									table+='<td><button class="btn btn-info del-bg" data-bg-id="'+val2.bgid+'">Delete</button>\
												<button type="button" class="btn btn-info btn-lg edit-bg" data-bg-id="'+val2.bgid+'"\
														data-toggle="modal" data-target="#editBGModal">\
														<span class="glyphicon glyphicon-edit"></span>\
												</button>\
										</td>';
									table+='</tr>';
									wb++;
									bline++;
								})		
							}
						})
						$("#budget").append(table);
					}
				});
			},
			error : function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		})
	}
	//add bline, wb, bg--------------------------------------------------------------------------------------------------
	$("body").off("click", "#add-bl").on("click", "#add-bl", function(){
		$("#new-budget").empty();
		add = 1;
		$("#new-budget").append('<label>Budget Line Code: </label>\
								<input type="text" name="" id="add-bl-blcode"><br>\
								<label>Budget Line Name: </label>\
								<input type="text" name="" id="add-bl-blname">');
	})
	$("body").off("click", "#add-wb").on("click", "#add-wb", function(){
		$("#new-budget").empty();
		add = 2;
		data = '<label>Budget Line: </label><select id="add-wb-blid">';
		$.each(bl, function(key, val){
			data+='<option value="'+val.blid+'">'+val.blname+'</option>';
		})
		data+='</select><br>';
		$("#new-budget").append(data);
		$("#new-budget").append('<label>WB Code: </label>\
								<input type="text" name="" id="add-wb-wbcode"><br>\
								<label>WB Name: </label>\
								<input type="text" name="" id="add-wb-wbname">');
		
	})
	$("body").off("click", "#add-bg").on("click", "#add-bg", function(){
		$("#new-budget").empty();
		add = 3;
		data = '<label>Budget Line: </label><select id="add-bg-blid">';
		$.each(bl, function(key, val){
			data+='<option value="'+val.blid+'">'+val.blname+'</option>';
		})
		data+='</select><br>';
		data+='<label>WB :</label><select id="add-bg-wbid">';
		$.each(wb, function(key, val){
			if(val.bline.blid==bl[0].blid)
				data+='<option value="'+val.wbid+'">'+val.wbname+'</option>';
		})
		data+='</select><br>';
		$("#new-budget").append(data);
		$("#new-budget").append('<label>BG Code: </label>\
								<input type="text" name="" id="add-bg-bgcode"><br>\
								<label>BG Name: </label>\
								<input type="text" name="" id="add-bg-bgname">');
		
		$("#add-bg-blid").on("change", function(){
			data='';
			$.each(wb, function(key, val){
				if(val.bline.blid==$("#add-bg-blid").val())
					data+='<option value="'+val.wbid+'">'+val.wbname+'</option>';
			})
			$("#add-bg-wbid").empty();
			$("#add-bg-wbid").append(data);
		})
	})
	
	$("body").off("click", "#add-btn").on("click", "#add-btn", function(){
		var url = "";
		var postdata;
		if(add==1){
			url = "/service/addbline";
			postdata = [$("#add-bl-blcode").val(), $("#add-bl-blname").val()];
		}else if(add==2){
			url = "/service/addwb";
			postdata = [$("#add-wb-blid").val(), $("#add-wb-wbcode").val(), $("#add-wb-wbname").val()];
		}else{
			url = "/service/addbg";
			postdata = [$("#add-bg-wbid").val(), $("#add-bg-bgcode").val(), $("#add-bg-bgname").val()];
		}
		$.ajax({
			type: "POST",
			url : url,
			contentType : "application/json",
    		data: JSON.stringify(postdata),
			accept: 'text/plain',
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
	
	//edit bline, wb, bg--------------------------------------------------------------------------------------------------
	$("body").off("click", ".edit-bl").on("click", ".edit-bl", function(){
		var blid = $(this).attr("data-bl-id");
		$.each(bl, function(key, val){
			if(val.blid == blid){
				$("#edit-BL-blcode").val(val.blcode);
				$("#edit-BL-blname").val(val.blname);
			}
		})
		$("#edit-BL-btn").click(function(){
			$.ajax({
				type: "POST",
				url : "/service/editbline",
				contentType : "application/json",
	    		data: JSON.stringify([blid, $("#edit-BL-blcode").val(), $("#edit-BL-blname").val()]),
				accept: 'text/plain',
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
	$("body").off("click", ".edit-wb").on("click", ".edit-wb", function(){
		wbid = $(this).attr("data-wb-id")
		$.each(wb, function(key, val){
			if(val.wbid == wbid){
				$("#edit-WB-wbcode").val(val.wbcode);
				$("#edit-WB-wbname").val(val.wbname);
			}
		})
		
		$("#edit-WB-btn").click(function(){
			$.ajax({
				type: "POST",
				url : "/service/editwb",
				contentType : "application/json",
	    		data: JSON.stringify([wbid, $("#edit-WB-wbcode").val(), $("#edit-WB-wbname").val()]),
				accept: 'text/plain',
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
	$("body").off("click", ".edit-bg").on("click", ".edit-bg", function(){
		bgid = $(this).attr("data-bg-id")
		$.ajax({
			type : "GET",
			url : "/service/getbg/" +bgid,
			success: function(data){
				$("#edit-BG-bgcode").val(data.bgcode);
				$("#edit-BG-bgname").val(data.bgname);
				$("#edit-BG-btn").click(function(){
					$.ajax({
						type: "POST",
						url : "/service/editbg",
						contentType : "application/json",
			    		data: JSON.stringify([bgid, $("#edit-BG-bgcode").val(), $("#edit-BG-bgname").val()]),
						accept: 'text/plain',
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
			},
			error: function(e){
				console.log("ERROR : ", e);
			}
		})
		
	
	})
	
	//delete bline, wb, bg------------------------------------------------------------------------------------------------
	$("body").off("click", ".del-bl").on("click", ".del-bl", function(){
		$.ajax({
			type : "GET",
			url: "/service/delbl/" +$(this).attr("data-bl-id"),
			success: function(data){
				$("#budget").empty();
				console.log(data);
				getData();
			},
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
	})
	$("body").off("click", ".del-wb").on("click", ".del-wb", function(){
		$.ajax({
			type : "GET",
			url: "/service/delwb/" +$(this).attr("data-wb-id"),
			success: function(data){
				console.log(data);
				getData();
			},
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
	})
	$("body").off("click", ".del-bg").on("click", ".del-bg", function(){
		$.ajax({
			type : "GET",
			url: "/service/delbg/" +$(this).attr("data-bg-id"),
			success: function(data){
				console.log(data);
				getData();
			},
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		});
	})
})