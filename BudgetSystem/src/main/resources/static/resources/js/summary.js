$(document).ready(function() {
	var group;
	var detail;
	var redirect = 1;
	var isEditting = 0;
	$.ajax({
		type : "GET",
		url : "/service/getallwb",
		success : function(data){
			group = data;
		},
		error : function(e) {
			alert("System error, please try again!");
			console.log("ERROR : ", e);
		}
	});
	$.ajax({
		type : "GET",
		url : "/service/getallbg",
		success : function(data){
			detail = data;
			//click first dept-------------------------------------------------------------------------------------------------------------------
		    $("div a.dept").first().trigger("click");
		},
		error : function(e) {
			alert("System error, please try again!");
			console.log("ERROR : ", e);
		}
	});
	
//reporter does not have reject btn and admin not have submit btn-----------------------------------------------------------------------
	if($("#sysrole").val()=="REPORTER"){
		$("#reject").hide();
	}
	if($("#sysrole").val()=="NOT"){
		$("#submit").hide();
	}
	
//add budget----------------------------------------------------------------------------------------------------------------------------
	$("body").off("click", "#add-budget").on("click", "#add-budget", function(){
		//get wb
		$.ajax({
			type : "GET",
			url : '/service/getwbbybl/' + $('#bg-line').val(),
			success : function(data) {
				if($("#all").prop('checked') == true){
					$(".sponsor").each(function (){
						row = '<tr class="budget-row">\
						  			<td class="control-dept" data-dept-id="'+$("#c-dept").attr("data-dept-id")+'" data-dept-code="'+$("#c-dept").attr("data-dept-code")+'">'+$("#c-dept").text()+'</td>\
						  			<td class="sponsor-dept" data-dept-id="'+$(this).val()+'" data-dept-code="'+$(this).attr("data-dept-code")+'">'+$(this).attr("name")+'</td>\
						  			<td class="bl" data-bl-id="'+$('#bg-line').val()+'">'+$('#bg-line option:selected').text()+'</td>\
						  			<td>\
						  				<select class="wb">\
											<option value="NEW">NEW</option>';
									$.each(data, function(key, val){
									  row+='<option value="'+val.wbid+'" data-wb-code="'+val.wbcode+'">'+val.wbname+'</option>';
									}) 					
						 
									row+='</select>\
						  			</td>\
							 		<td><input type="text" class="bg"></td>\
						  			<td class="code">NEW</td>\
						  			<td><input type="text" class="bg-amount num" size="10"></td>\
						  			<td><input type="number" class="time-allocate" value="12"></td>\
						  			<td><input type="date" class="start-time" size="10" value="2019-01-01"></td>\
						  			<td><input type="text" class="expense num" size="10"></td>\
						  			<td><input type="button" class="btn btn-info delete-btn" name="" value="Delete">\
						 				<input type="button" class="btn btn-info add-detail-btn" name="" value="Add Detail">\
									</td>\
							 		<td><input type="checkbox" class=usetool></td>';		
						row+='</tr>';
						$(".summary table tbody").append(row);
					});
				}else{
					$(".sponsor:checked").each(function(){
						row = '<tr class="budget-row">\
						  			<td class="control-dept" data-dept-id="'+$("#c-dept").attr("data-dept-id")+'" data-dept-code="'+$("#c-dept").attr("data-dept-code")+'">'+$("#c-dept").text()+'</td>\
						  			<td class="sponsor-dept" data-dept-id="'+$(this).val()+'" data-dept-code="'+$(this).attr("data-dept-code")+'">'+$(this).attr("name")+'</td>\
						  			<td class="bl" data-bl-id="'+$('#bg-line').val()+'">'+$('#bg-line option:selected').text()+'</td>\
						  			<td>\
						  				<select class="wb">\
					  						<option value="NEW">NEW</option>';
						 			$.each(data, function(key, val){
							 		  row+='<option value="'+val.wbid+'" data-wb-code="'+val.wbcode+'">'+val.wbname+'</option>'
						 			}) 					
						 			row+='</select>\
						  			</td>\
						 			<td><input type="text" class="bg"></td>\
							  		<td class="code">NEW</td>\
							  		<td><input type="text" class="bg-amount num" size="10"></td>\
							  		<td><input type="number" class="time-allocate" value="12"></td>\
							  		<td><input type="date" class="start-time" size="10" value="2019-01-01"></td>\
							  		<td><input type="text" class="expense num" size="10"></td>\
							  		<td><input type="button" class="btn btn-info delete-btn" name="" value="Delete">\
						 				<input type="button" class="btn btn-info add-detail-btn" name="" value="Add Detail">\
						 			</td>\
								 	<td><input type="checkbox" class=usetool></td>';		
						row+='</tr>';
						$(".summary table tbody").append(row);
					});
				}
				$(".num").autoNumeric('init', {mDec: '0'});
			},
			error : function(e) {
				alert("System error, please try again!");
				console.log("ERROR : ", e);
			}
		});	
	});
	
//side bar---------------------------------------------------------------------------------------------------------
	$("body").off("click", "#menu").on("click", "#menu", function(){
		document.getElementById("mySidebar").style.display = "block";
	});
    $("body").off("click", "#close").on("click", "#close", function(){
    	document.getElementById("mySidebar").style.display = "none";
    });
    
//select dept-------------------------------------------------------------------------------------------------------
    $("body").off("click", ".dept").on("click", ".dept", function(){
    	if(redirect!=0){
	    	$("tbody").empty();
	    	$("#c-dept").attr("data-dept-id",$(this).attr("data-dept-id"));
	    	$("#c-dept").attr("data-dept-code",$(this).attr("data-dept-code"));
			$("#c-dept").text($(this).text());
			//get budget in DB
	    	$.ajax({
	    		type : "GET",
				url : '/service/summarybydept/' + $(this).attr("data-dept-id"),
				success : function(data) {
					$.each(data, function(key, val){
						var wbid;
						row = '<tr class="budget-row" data-bd-id="'+val.bd.bdid+'">\
				  			<td class="control-dept" data-dept-id="'+val.bd.budget.dept.deptid+'" data-dept-code="'+val.bd.budget.dept.deptcode+'">'+val.bd.budget.dept.deptname+'</td>\
				  			<td class="sponsor-dept" data-dept-id="'+val.bd.dept.deptid+'" data-dept-code="'+val.bd.dept.deptcode+'">'+val.bd.dept.deptname+'</td>\
				  			<td class="bl" data-bl-id="'+val.bd.bline.blid+'" data-bl-name='+val.bd.bline.blname+'">'+val.bd.bline.blname+'</td>\
				  			<td><select class="wb">';
						row+='<option value="NEW">NEW</option>';
				  		if(val.bd.bg==null){
				  			$.each(group, function(key1, val1){
								if(val1.bline.blid == val.bd.bline.blid)
									row+='<option value="'+val1.wbid+'" data-wb-code="'+val1.wbcode+'">'+val1.wbname+'</option>'
							});
				  		}else{
							$.each(group, function(key1, val1){
								if(val1.bline.blid == val.bd.bline.blid){
									if(val1.wbid == val.bd.bg.wb.wbid){
										row+='<option value="'+val1.wbid+'" data-wb-code="'+val1.wbcode+'" selected>'+val1.wbname+'</option>';
										wbid = val1.wbid;
									}
									else{
										row+='<option value="'+val1.wbid+'">'+val1.wbname+'</option>'
									}
								}
							});
				  		}
						row+='</select></td>';
				 		row+='<td>';
						if(val.bd.bg==null){
							row+='<input type="text" class="bg" value="'+val.bd.newdetail+'"></td>'
							row+='<td class="code">NEW</td>';
						}
						else{
							row+='<select class="bg" width=10>';
							$.each(detail, function(key1, val1){
								if(val1.wb.wbid==wbid){
									if(val.bd.bg.bgid==val1.bgid){
										row+='<option value="'+val1.bgid+'" data-bg-code="'+val1.bgcode+'" selected>'+val1.bgname+'</option>'
									}else{
										row+='<option value="'+val1.bgid+'" data-bg-code="'+val1.bgcode+'">'+val1.bgname+'</option>'
									}
								}
							});
							row+='<td class="code">'+val.bd.budget.dept.deptcode+'-'+val.bd.dept.deptcode+'-'+val.bd.bg.wb.wbcode+'-'+val.bd.bg.bgcode+'</td>';
						}
						row+='\
					  		<td><input type="text" class="bg-amount num" size="10" value="'+val.bd.amount+'"></td>\
					  		<td><input type="number" class="time-allocate" value="'+val.bd.allocationtime+'"></td>\
					  		<td><input type="date" class="start-time" size="10" value="'+val.bd.starttime+'"></td>\
					  		<td><input type="text" class="expense num" size="10" value="'+val.bd.expense+'"></td>\
					  		<td><input type="button" class="btn btn-info delete-btn" name="" value="Delete">\
					  			<input type="button" class="btn btn-info add-detail-btn" name="" value="Add Detail">\
					  		</td>\
						 	<td><input type="checkbox" class=usetool></td>';		
				 		row+='</tr>';
				 		$.each(val.detailList, function(key1, val1){
				 			row += '<tr class="more-detail" data-mdt-id="'+val1.mdtid+'">\
							 			<td colspan=4></td><td>\
											<input type="text" class="mdt-detail" value="'+val1.detail+'">\
										</td><td></td>\
										<td><input type="text" class="mdt-amount num" value="'+val1.amount+'" size="10">\</td>\
										<td colspan=3></td><td><input type="button" class="btn btn-info mdt-remove" value="Delete"></td>\
				 					</tr>';
				 		})
				 		$("tbody").append(row);	
					});
					if(data.length==0){
						$("#edit").removeAttr('disabled');
						$("#add-btn").attr('disabled', 'disabled');
						$("#submit-tool").attr('disabled', 'disabled');
						$("#save").attr('disabled', 'disabled');
						$("#submit").attr('disabled', 'disabled');
						$("#reject").attr('disabled', 'disaled');
						$("#import").attr('disabled', 'disaled');
					}else if(($("#sysrole").val()=="REPORTER" && data[0].bd.budget.status!=0) || ($("#sysrole").val()=="REVIEWER" && data[0].bd.budget.status!=1) || ($("#sysrole").val()=="NOT" && data[0].bd.budget.status!=2)){
						$("#add-btn").attr('disabled', 'disabled');
						$("#submit-tool").attr('disabled', 'disabled');
						$("#submit").attr('disabled', 'disabled');
						$("#edit").attr('disabled', 'disabled');
						$("#save").attr('disabled', 'disabled');
						$("#import").attr('disabled', 'disaled');
						$("#reject").attr('disabled','disabled');
					}else if(($("#sysrole").val()=="REPORTER" && data[0].bd.budget.status==0) || ($("#sysrole").val()=="REVIEWER" && data[0].bd.budget.status==1) || ($("#sysrole").val()=="NOT" && data[0].bd.budget.status==2)){
						$("#edit").removeAttr('disabled');
						$("#submit").removeAttr('disabled');
						$("#add-btn").attr('disabled', 'disabled');
						$("#submit-tool").attr('disabled', 'disabled');
						$("#save").attr('disabled', 'disabled');
						$("#reject").removeAttr('disabled');
						$("#import").attr('disabled', 'disaled');
					}
					$(".num").autoNumeric('init', {mDec: '0'});
					$("tbody tr input, select, #add-btn, #save, #submit-tool").attr('disabled', 'disaled');
				},
				error : function(e) {
					alert("System error, please try again!");
					console.log("ERROR : ", e);
				}
	    	});
	    	
	    	//add child-detail------------------------------------------------------------------------------------------------
	    	$("body").off("click", ".add-detail-btn").on("click", ".add-detail-btn",function(){
	 			row1 = $(this).closest("tr");
	 			row1.find(".usetool").attr('disabled','disabled');
	 			
	 			var newRow = $("<tr>")
	 			newRow.attr("class","more-detail");
	 			newRow.attr("data-mdt-id","0");
	 			newRow.css("background-color","white");
		 		newRow.append('<td colspan=4></td><td>\
		 							<input type="text" class="mdt-detail" value="No detail">\
		 						</td><td></td>\
		 						<td><input type="text" class="mdt-amount num" data-mdt-id="0" value="0" size="10">\</td>\
		 						<td colspan=3></td><td><button class="btn btn-info mdt-remove">Delete</button></td>');
	 			row1.after(newRow);
	 			$(".num").autoNumeric('init', {mDec: '0'});
	 		})
	 		
	 		$("body").off("click", ".mdt-remove").on("click", ".mdt-remove",function(){
	 			bgrow = $(this).closest("tr").prevAll("tr.budget-row").first();
	 			$(this).closest("tr").remove();
	 			total = 0;
	 			if(bgrow.nextUntil(".budget-row").length == 0){
	 				bgrow.find(".usetool").removeAttr("disabled");
	 			}else{
		 			bgrow.nextUntil(".budget-row").each(function(key, row1){
		 				total += Number(row1.children[3].children[0].value);
		 			})
	 			}
	 			bgrow.find(".bg-amount").val(total);
	 			$(".num").autoNumeric('init', {mDec: '0'});
	 			expense = bgrow.find(".expense");
	 	    	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	 	    	var firstDate = new Date($("#submit-tool").attr("data-date"));
	 	    	var secondDate = new Date(bgrow.find(".start-time").val());
	
	 	    	var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
	 	    	if($.isNumeric(diffDays)){
	 	    		expense.val(parseInt((diffDays/30)*(Number(bgrow.find(".bg-amount").val().replace(/,/g,""))/bgrow.find(".time-allocate").val()))).change();
	 	    		expense.trigger("focusin");
	 	    		expense.trigger("focusout");
	 	    	}
	 		})
	 		
	 		$("body").on("focusout", ".mdt-amount", function(){
	 			bgrow = $(this).closest("tr").prevAll("tr.budget-row").first();
	 			total = 0;
	 			bgrow.nextUntil(".budget-row").each(function(key, row1){
	 				total = total + Number(row1.children[3].children[0].value.replace(/,/g,""));
	 			})
	 			bgrow.find(".bg-amount").val(total);
	 			bgrow.find(".bg-amount").trigger("focusin");
	 			bgrow.find(".bg-amount").trigger("focusout");
	 			expense = bgrow.find(".expense");
	 	    	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	 	    	var firstDate = new Date($("#submit-tool").attr("data-date"));
	 	    	var secondDate = new Date(bgrow.find(".start-time").val());
	
	 	    	var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
	 	    	if($.isNumeric(diffDays)){
	 	    		expense.val(parseInt((diffDays/30)*(Number(bgrow.find(".bg-amount").val().replace(/,/g,""))/bgrow.find(".time-allocate").val()))).change();
	 	    		expense.trigger("focusin");
	 	    		expense.trigger("focusout");
	 	    	}
	 	    	
	 		})
	 		
	    	//get budget line----------------------------------------------------------------------------------------------------------
	    	$.ajax({
	    		type : "GET",
				url : '/service/budgetlinebydept/' + $(this).attr("data-dept-id"),
				success : function(data) {
					$("#bg-line").empty();
					$.each(data, function(key, val){
						$("#bg-line").append("<option value='"+val.bline.blid+"'>"+val.bline.blname+"</option>");               
		            });
				},
				error : function(e) {
					alert("System error, please try again!");
					console.log("ERROR : ", e);
				}
	    	});
	    	//get sponsor--------------------------------------------------------------------------------------------------------------
	    	$.ajax({
	    		type : "GET",
				url : '/service/sponsorbydept/' + $(this).attr("data-dept-id"),
				success : function(data) {
					$("#s-dept").empty();
					$("#s-dept").append('<label>All:&nbsp;&nbsp;</label>');
					$("#s-dept").append('<input id="all" type="checkbox">ALL<br>');
					$.each(data, function(key, val){
						$("#s-dept").append("<label>"+val[0].group.groupcode+":&nbsp;&nbsp;</label>");
						$.each(val , function(key1, val1){
							$("#s-dept").append('<input class="sponsor" value="'+val1.deptid+'" data-dept-code="'+val1.deptcode+'" type="checkbox" name="'+val1.deptname+'">'+val1.deptname+'&nbsp;&nbsp;');
						});
						$("#s-dept").append('<br>');
		            });
				},
				error : function(e) {
					alert("System error, please try again!");
					console.log("ERROR : ", e);
				}
	    	});
	    	$("#close").click();
    	}
    });
    
//delete row-------------------------------------------------------------------------------------------------------------------------
    $("body").off("click", ".delete-btn").on("click", ".delete-btn", function(){
    	$(this).closest("tr").nextUntil(".budget-row").remove(); 
    	$(this).closest("tr").remove();
    });
    
//get bg by wb-----------------------------------------------------------------------------------------------------------------------
    $("body").on("change paste keyup", "select.wb", function(){
    	var nexttd = $(this).closest("td").next();
    	var row = $(this).closest("tr");
    	if($(this).val()=="NEW"){
    		nexttd.empty();
    		nexttd.append('<input type="text" class="bg" size="10">');
    		row.find(".code").empty();
    		row.find(".code").html("NEW");
    	}else{
    		$.ajax({
        		type : "GET",
    			url : '/service/bgbywb/' + $(this).val(),
    			success : function(data) {
    				nexttd.empty();
    				dropdown = '<select class="bg">';
    				$.each(data, function(key, val){
    					dropdown+='<option value="'+val.bgid+'" data-bg-code="'+val.bgcode+'">'+val.bgname+'</option>';
    				});
    				dropdown+=('</select>');
    				nexttd.append(dropdown);
    				code="";
    				code+=row.find(".control-dept").attr("data-dept-code")+"-";
    	    		code+=row.find(".sponsor-dept").attr("data-dept-code")+"-";
    	    		code+=row.find(".wb option:selected").attr("data-wb-code")+"-";
    	    		code+=row.find(".bg option:selected").attr("data-bg-code");
    	    		row.find(".code").empty();
    	    		row.find(".code").html(code);
    			},
    			error : function(e) {
    				alert("System error, please try again!");
    				console.log("ERROR : ", e);
    			}
        	});
    	}
    });
    
//save budget---------------------------------------------------------------------------------------------------------------------------
    $("body").off("click", "#save").on("click", "#save", function(){
    	var budgetList = [];
    	$("tbody tr.budget-row").each(function(){
    		var row = $(this);
    		var moredetail = [];
    		var mdtList = row.nextUntil("tr.budget-row");
    		mdtList.each(function(key1,row1){
    			mdetail=[
    					row1.attributes[1].value,
    					row1.children[1].children[0].value,
    					row1.children[3].children[0].value.replace(/,/g,"")
    			];
    			moredetail.push(mdetail);
    		})
    		var budget={
    				id : row.attr("data-bd-id"),
    				cdept : $("#c-dept").attr("data-dept-id"),
    				sdept : row.find("td.sponsor-dept").attr("data-dept-id"),
    				bline : row.find("td.bl").attr("data-bl-id"),
    				wb : row.find(".wb").val(),
    				bg : row.find(".bg").val(),
    				amount : Number(row.find(".bg-amount").val().replace(/,/g,"")),
    				allocate : row.find(".time-allocate").val(),
    				start : row.find(".start-time").val(),
    				expense :  Number(row.find(".expense").val().replace(/,/g,"")),
    				role: $("#sysrole").val(),
    				moredetail : moredetail
    		}
    		budgetList.push(budget);
    	});
    	var blank = 0;
    	$('.summary tbody input[type="text"]').each(function(){
    		if($(this).val().length==0){
    			blank++;
    		}
    	})
    	if(blank > 0){
    		alert("Can not leave fields blank!")
    	}else{
    		$.ajax({
        		type : "POST",
        		url : "/service/savebudget",
        		contentType : "application/json",
        		data: JSON.stringify(budgetList),
    			accept: 'text/plain',
    			success : function(data){
    				console.log(data);
    				$(".dept").each(function(){
    					if($(this).attr("data-dept-id")==$("#c-dept").attr("data-dept-id")){
    						isEditting=0;
    						$(this).trigger("click");
    						return false; 
    					}
    					
    				})
    			},
    			error: function(e){
    				alert("System error, please try again!");
    				console.log("ERROR : ", e);
    			}
        	});
    	}
    	
    });
    
//submit budget----------------------------------------------------------------------------------------------------------
    $("body").off("click", "#submit").on("click", "#submit", function(){
    	$("#save").trigger("click");
    	var budget={
				cdept : $("#c-dept").attr("data-dept-id"),
				role: $("#sysrole").val()
    	};
    	$.ajax({
    		type : "POST",
    		url : "/service/submitbudget",
    		contentType : "application/json",
    		data: JSON.stringify(budget),
			accept: 'text/plain',
			success : function(data){
				console.log(data);
				$("#add-btn").attr('disabled', 'disabled');
				$("#submit-tool").attr('disabled', 'disabled');
				$("#edit").attr('disabled', 'disabled');
				$("#save").attr('disabled', 'disabled');
				$("#submit").attr('disabled', 'disabled');
				$("#reject").attr('disabled', 'disaled');
				$("#import").attr('disabled', 'disaled');
				$("tbody tr input, select").attr('disabled', 'disaled');
			},
			error: function(e){
				alert("System error, please try again!");
				console.log("ERROR : ", e);
			}
    	});
    });
    
 //Reject budget----------------------------------------------------------------------------------------------------------
    $("body").off("click", "#reject").on("click", "#reject", function(){
    	var budget={
				cdept : $("#c-dept").attr("data-dept-id"),
				role: $("#sysrole").val()
    	};
    	$.ajax({
    		type : "POST",
    		url : "/service/rejectbudget",
    		contentType : "application/json",
    		data: JSON.stringify(budget),
			accept: 'text/plain',
			success : function(data){
				console.log(data);
				$("#add-btn").attr('disabled', 'disabled');
				$("#submit-tool").attr('disabled', 'disabled');
				$("#edit").attr('disabled', 'disabled');
				$("#import").attr('disabled', 'disaled');
				$("#save").attr('disabled', 'disabled');
				$("#submit").attr('disabled', 'disabled');
				$("#reject").attr('disabled', 'disaled');
				$("tbody tr input, select").attr('disabled', 'disaled');
			},
			error: function(e){
				alert("System error, please try again!");
				console.log("ERROR : ", e);
			}
    	});
    });
//Edit -----------------------------------------------------------------------------------------------------------------
    $("body").off("click", "#edit").on("click", "#edit", function(){
       	$("tbody tr input[type='text'], tbody tr input[type='button'], tbody tr input[type='number'], tbody tr input[type='date'], select, #add-btn, #save, #submit-tool").removeAttr('disabled');
       	$("tbody tr.budget-row").each(function(){
       		if($(this).next().attr("class")!="more-detail"){
       			$(this).find(".usetool").removeAttr("disabled");
       		}
       	})
       	$("#edit").attr('disabled', 'disabled');
       	$("#submit").attr('disabled', 'disabled');
       	$("#reject").attr('disabled', 'disaled');
       	$("#import").removeAttr('disabled');
       	isEditting=1;
    });
    
//Utilize historical amount---------------------------------------------------------------------------------------------
    $("body").off("click", "#submit-tool").on("click", "#submit-tool", function(){
    	if($("#UHA").is(':checked')){
	    	var inputs = [];
	    	var count = 0;
    		$(".usetool:checked").each(function(){
    			row = $(this).closest("tr");
    			input = [];		
    			input.push(row.find(".sponsor-dept").attr("data-dept-id"));
    			input.push(row.find(".bl").attr("data-bl-id"));
    			input.push($("#percent-add-in").val());
    			inputs.push(input);
    		})
    		$.ajax({
    				type : "POST",
		    		url : "/service/hatool",
		    		contentType : "application/json",
		    		data: JSON.stringify(inputs),
					accept: 'text/plain',
					success : function(data){
						$(".usetool:checked").each(function(){
			    			row = $(this).closest("tr");
			    			row.find(".bg-amount").val(data[count]);
			    			row.find(".bg-amount").trigger("focusin");
			    			row.find(".bg-amount").trigger("focusout");
			    			count++;
			    			
			    			expense = row.find(".expense");
				 	    	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
				 	    	var firstDate = new Date($("#submit-tool").attr("data-date"));
				 	    	var secondDate = new Date(row.find(".start-time").val());
				
				 	    	var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
				 	    	if($.isNumeric(diffDays)){
				 	    		expense.val(parseInt((diffDays/30)*(Number(row.find(".bg-amount").val().replace(/,/g,""))/row.find(".time-allocate").val())));
				 	    		expense.trigger("focusin");
				 	    		expense.trigger("focusout");
				 	    	}
						})
						$(".usetool:checked").prop("checked", false);
					},
					error: function(e){
						alert("System error, please try again!");
						console.log("Error: ", e);
					}
    		})
    	}else{
    		var inputs = [];
	    	var count = 0;
    		$(".usetool:checked").each(function(){
    			row = $(this).closest("tr");
    			input = [];		
    			input.push(row.find(".sponsor-dept").attr("data-dept-id"));
    			input.push(row.find(".bl").attr("data-bl-id"));
    			input.push($("#criteria").val());
    			input.push($("#cost-allocation").val().replace(/,/g,""));
    			inputs.push(input);
    		})
    		$.ajax({
    				type : "POST",
		    		url : "/service/hatool",
		    		contentType : "application/json",
		    		data: JSON.stringify(inputs),
					accept: 'text/plain',
					success : function(data){
						$(".usetool:checked").each(function(){
			    			row = $(this).closest("tr");
			    			row.find(".bg-amount").val(data[count]);
			    			row.find(".bg-amount").trigger("focusin");
			    			row.find(".bg-amount").trigger("focusout");
			    			count++;
			    			
			    			expense = row.find(".expense");
				 	    	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
				 	    	var firstDate = new Date($("#submit-tool").attr("data-date"));
				 	    	var secondDate = new Date(row.find(".start-time").val());
				
				 	    	var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
				 	    	if($.isNumeric(diffDays)){
				 	    		expense.val(parseInt((diffDays/30)*(Number(row.find(".bg-amount").val().replace(/,/g,""))/row.find(".time-allocate").val())));
				 	    		expense.trigger("focusin");
				 	    		expense.trigger("focusout");
				 	    	}
						})
						$(".usetool:checked").prop("checked", false);
					},
					error: function(e){
						alert("System error, please try again!");
						console.log("Error: ", e);
					}
    		})
    	}
    	$(".num").autoNumeric('init', {mDec: '0'});
    })
    
    $("body").off("click", "tr.budget-row td.bl").on("click", "tr.budget-row td.bl", function(){
    	$(this).closest("tr").nextUntil("tr.budget-row").toggle("fast", function() {});
    })
    
    //Calculate Expense when bg-amount change----------------------------------------------------------------------------------------------------
    $("body").on("change", ".bg-amount, .time-allocate, .start-time", function(){
    	expense = $(this).closest("tr").find(".expense");
    	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
    	var firstDate = new Date($("#submit-tool").attr("data-date"));
    	var secondDate = new Date($(this).closest("tr").find(".start-time").val());

    	var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay)));
    	if($.isNumeric(diffDays)){
    		expense.val(parseInt((diffDays/30)*(Number($(this).closest("tr").find(".bg-amount").val().replace(/,/g,""))/$(this).closest("tr").find(".time-allocate").val())));
    		expense.trigger("focusin");
    		expense.trigger("focusout");
    	}
    })
    
    //Gen Code-------------------------------------------------------------------------------------------------------------
     $("body").on("change", ".bg", function(){
    	 var code = "";
    	 if($(this).val()=="NEW"){
    		 code+="NEW";
    	 }else{
    		 code+=$(this).closest("tr").find(".control-dept").attr("data-dept-code")+"-";
    		 code+=$(this).closest("tr").find(".sponsor-dept").attr("data-dept-code")+"-";
    		 code+=$(this).closest("tr").find(".wb option:selected").attr("data-wb-code")+"-";
    		 code+=$(this).closest("tr").find(".bg option:selected").attr("data-bg-code"); 
    	 }
    	 $(this).closest("tr").find(".code").empty();
		 $(this).closest("tr").find(".code").html(code);
     })
     $(".w3-sidebar").off("click", "div>div>a.dept").on("click", "div>div>a.dept", function(){
    	if($(this).attr("class")!="dropdown-toggle" || $(this).attr("class")!="btn-lg"){
	    	if(isEditting==1){
	    		var confirm = window.confirm("Data haven't saved. Are you sure want to leave?");
	    		if(confirm == true){
	    			redirect=1;
	    			isEditting=0;
	    			return true;
	    		}else{
	    			isEditting=1;
	    			redirect=0;
	    			return false;
	    		}
	    	}
    	}
     })
     //warning-------------------------------------------------------------------------------------------------------------
     $("body").off("click", "li>a").on("click", "li>a", function(){
    	if($(this).attr("class")!="dropdown-toggle"){
	    	if(isEditting==1){
	    		var confirm = window.confirm("Data haven't saved. Are you sure want to leave?");
	    		if(confirm == true){
	    			redirect=1;
	    			isEditting=0;
	    			return true;
	    		}else{
	    			isEditting=1;
	    			redirect=0;
	    			return false;
	    		}
	    	}
    	}
     })
     
     $("#cost-allocation").autoNumeric('init', {mDec: '0'});
    
})