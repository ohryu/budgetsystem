$(document).ready(function() {
	var group;
	var detail;
	$.ajax({
		type : "GET",
		url : "/service/getallwb",
		success : function(data){
			group = data;
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});
	$.ajax({
		type : "GET",
		url : "/service/getallbg",
		success : function(data){
			detail = data;
		},
		error : function(e) {
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
						  			<td class="control-dept" data-dept-id="'+$("#c-dept").attr("data-dept-id")+'">FAD</td>\
						  			<td class="sponsor-dept" data-dept-id="'+$(this).val()+'">'+$(this).attr("name")+'</td>\
						  			<td class="bl" data-bl-id="'+$('#bg-line').val()+'">'+$('#bg-line option:selected').text()+'</td>\
						  			<td>\
						  				<select class="wb">\
											<option value="NEW">NEW</option>';
									$.each(data, function(key, val){
									  row+='<option value="'+val.wbid+'">'+val.wbname+'</option>'
									}) 					
						 
									row+='</select>\
						  			</td>\
							 		<td><input type="text" class="bg" size="10"></td>\
						  			<td><input disabled type="text" class="code" size="10"></td>\
						  			<td><input type="text" class="bg-amount" size="10"></td>\
						  			<td><input type="date" class="time-allocate" size="10"></td>\
						  			<td><input type="date" class="start-time" size="10"></td>\
						  			<td><input type="text" class="expense" size="10"></td>\
						  			<td><input type="button" class="delete-btn" name="" value="Delete"></td>\
							 		<td><input type="checkbox" class=usetool></td>';		
						row+='</tr>';
						$(".summary table tbody").append(row);
					});
				}else{
					$(".sponsor:checked").each(function(){
						row = '<tr class="budget-row">\
						  			<td class="control-dept" data-dept-id="'+$("#c-dept").attr("data-dept-id")+'">FAD</td>\
						  			<td class="sponsor-dept" data-dept-id="'+$(this).val()+'">'+$(this).attr("name")+'</td>\
						  			<td class="bl" data-bl-id="'+$('#bg-line').val()+'">'+$('#bg-line option:selected').text()+'</td>\
						  			<td>\
						  				<select class="wb">\
					  						<option value="NEW">NEW</option>';
						 			$.each(data, function(key, val){
							 		  row+='<option value="'+val.wbid+'">'+val.wbname+'</option>'
						 			}) 					
						 			row+='</select>\
						  			</td>\
						 			<td><input type="text" class="bg" size="10"></td>\
							  		<td><input disabled type="text" class="code" size="10"></td>\
							  		<td><input type="text" class="bg-amount" size="10"></td>\
							  		<td><input type="date" class="time-allocate" size="10"></td>\
							  		<td><input type="date" class="start-time" size="10"></td>\
							  		<td><input type="text" class="expense" size="10"></td>\
							  		<td><input type="button" class="delete-btn" name="" value="Delete"></td>\
								 	<td><input type="checkbox" class=usetool></td>';		
						row+='</tr>';
						$(".summary table tbody").append(row);
					});
				}
			},
			error : function(e) {
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
    	$("tbody").empty();
    	$("#c-dept").attr("data-dept-id",$(this).attr("data-dept-id"));
		$("#c-dept").text($(this).text());
		//get budget in DB
    	$.ajax({
    		type : "GET",
			url : '/service/summarybydept/' + $(this).attr("data-dept-id"),
			success : function(data) {
				$.each(data, function(key, val){
					row = '<tr class="budget-row" data-bd-id="'+val.bdid+'">\
			  			<td class="control-dept" data-dept-id="'+val.budget.dept.deptid+'">'+val.budget.dept.deptname+'</td>\
			  			<td class="sponsor-dept" data-dept-id="'+val.dept.deptid+'">'+val.dept.deptname+'</td>\
			  			<td class="bl" data-bl-id="'+val.bline.blid+'" data-bl-name='+val.bline.blname+'">'+val.bline.blname+'</td>\
			  			<td><select class="wb">';
					row+='<option value="NEW" selected>NEW</option>';
			  		if(val.bg==null){
			  			$.each(group, function(key1, val1){
							if(val1.bline.blid == val.bline.blid)
								row+='<option value="'+val1.wbid+'">'+val1.wbname+'</option>'
						});
			  		}else{
						$.each(group, function(key1, val1){
							if(val1.bline.blid == val.bline.blid){
								if(val1.wbid == val.bg.wb.wbid){
									row+='<option value="'+val1.wbid+'" selected>'+val1.wbname+'</option>';
								}
								else{
									row+='<option value="'+val1.wbid+'">'+val1.wbname+'</option>'
								}
							}
						});
			  		}
					row+='</select></td>';
			 		row+='<td>';
					if(val.bg==null){
						row+='<input type="text" class="bg" value="'+val.newdetail+'"></td>'
						row+='<td>'+val.budget.dept.deptcode+'-'+val.dept.deptcode+'-NEW</td>';
					}
					else{
						row+='<select class="bg">';
						$.each(detail, function(key1, val1){
							if(val.bg.bgid==val1.bgid){
								row+='<option value="'+val1.bgid+'" selected>'+val1.bgname+'</option>'
							}else{
								row+='<option value="'+val1.bgid+'">'+val1.bgname+'</option>'
							}
						});
						row+='<td>'+val.budget.dept.deptcode+'-'+val.dept.deptcode+'-'+val.bg.wb.wbcode+'-'+val.bg.bgcode+'</td>';
					}
					row+='\
				  		<td><input type="text" class="bg-amount" size="10" value="'+val.amount+'"></td>\
				  		<td><input type="date" class="time-allocate" size="10" value="'+val.allocationtime+'"></td>\
				  		<td><input type="date" class="start-time" size="10" value="'+val.starttime+'"></td>\
				  		<td><input type="text" class="expense" size="10" value="'+val.expense+'"></td>\
				  		<td><input type="button" class="delete-btn" name="" value="Delete"></td>\
					 	<td><input type="checkbox" class=usetool></td>';		
			 		row+='</tr>';
			 		$("tbody").append(row);
				});
				if(($("#sysrole").val()=="REPORTER" && data[0].budget.status!=0) || ($("#sysrole").val()=="REVIEWER" && data[0].budget.status!=1) || ($("#sysrole").val()=="NOT" && data[0].budget.status!=2)){
					$("#add-btn").attr('disabled', 'disabled');
					$("#submit-tool").attr('disabled', 'disabled');
					$("#edit").attr('disabled', 'disabled');
					$("#save").attr('disabled', 'disabled');
					$("#submit").attr('disabled', 'disabled');
					$("#reject").attr('disabled', 'disaled');
				}
				$("tbody tr input, select, #add-btn, #save, #submit-tool").attr('disabled', 'disaled');
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
    	});
    	//get budget line----------------------------------------------------------------------------------------------------------
    	$.ajax({
    		type : "GET",
			url : '/service/budgetlinebydept/' + $(this).attr("data-dept-id"),
			success : function(data) {
				$("#bg-line").empty();
				$.each(data, function(key, val){
					$("#bg-line").append("<option value='"+val.blid+"'>"+val.blname+"</option>");               
	            });
			},
			error : function(e) {
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
						$("#s-dept").append('<input class="sponsor" value="'+val1.deptid+'" name="'+val1.deptname+'" type="checkbox">'+val1.deptname+'&nbsp;&nbsp;');
					});
					$("#s-dept").append('<br>');
	            });
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
    	});
    	$("#close").click();
    });
    
//click first dept-------------------------------------------------------------------------------------------------------------------
    $("div a.dept").first().trigger("click");
    
//delete row-------------------------------------------------------------------------------------------------------------------------
    $("body").off("click", ".delete-btn").on("click", ".delete-btn", function(){
    	 $(this).closest("tr").remove();
    });
    
//get bg by wb-----------------------------------------------------------------------------------------------------------------------
    $("body").on("change", "select.wb", function(){
    	var nexttd = $(this).closest("td").next();
    	if($(this).val()=="NEW"){
    		nexttd.empty();
    		nexttd.append('<input type="text" class="bg" size="10">');
    		nexttd.next().find("input").val($("#c-dept").text()+"-"+$("#s-dept").text+"NEW");
    	}else{
    		$.ajax({
        		type : "GET",
    			url : '/service/bgbywb/' + $(this).val(),
    			success : function(data) {
    				nexttd.empty();
    				dropdown = '<select class="bg">';
    				$.each(data, function(key, val){
    					dropdown+='<option value="'+val.bgid+'">'+val.bgname+'</option>';
    				});
    				dropdown+=('</select>');
    				nexttd.append(dropdown);
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
        	});
    	}
    });
    
//map wb with bg (select bg)-----------------------------------------------------------------------------------------------------------
    $("body").on("change", "select.wb", function(){
    	if($(this).closest("td").prev().val()=="NEW"){
    		$(this).closest("td").next().find("input").val($("#c-dept").text()+"-"+$("#s-dept").text+"NEW");
    	}
    	
    });
    
//save budget---------------------------------------------------------------------------------------------------------------------------
    $("body").off("click", "#save").on("click", "#save", function(){
    	var budgetList = [];
    	$("tbody tr.budget-row").each(function(){
    		var row = $(this);
    		var budget={
    				id : row.attr("data-bd-id"),
    				cdept : $("#c-dept").attr("data-dept-id"),
    				sdept : row.find("td.sponsor-dept").attr("data-dept-id"),
    				bline : row.find("td.bl").attr("data-bl-id"),
    				wb : row.find(".wb").val(),
    				bg : row.find(".bg").val(),
    				amount : row.find(".bg-amount").val(),
    				allocate : row.find(".time-allocate").val(),
    				start : row.find(".start-time").val(),
    				expense : row.find(".expense").val(),
    				role: $("#sysrole").val()
    		}
    		budgetList.push(budget);
    	});
    	$.ajax({
    		type : "POST",
    		url : "/service/savebudget",
    		contentType : "application/json",
    		data: JSON.stringify(budgetList),
			accept: 'text/plain',
			success : function(data){
				console.log(data);
				$("tbody tr input, select, #add-btn, #save, #submit-tool").attr('disabled', 'disaled');
			},
			error: function(e){
				console.log("ERROR : ", e);
			}
    	});
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
				$("tbody tr input, select").attr('disabled', 'disaled');
			},
			error: function(e){
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
				$("#save").attr('disabled', 'disabled');
				$("#submit").attr('disabled', 'disabled');
				$("#reject").attr('disabled', 'disaled');
				$("tbody tr input, select").attr('disabled', 'disaled');
			},
			error: function(e){
				console.log("ERROR : ", e);
			}
    	});
    });
//Edit -----------------------------------------------------------------------------------------------------------------
    $("body").off("click", "#edit").on("click", "#edit", function(){
       	$("tbody tr input, select, #add-btn, #save, #submit-tool").removeAttr('disabled');
    });
})