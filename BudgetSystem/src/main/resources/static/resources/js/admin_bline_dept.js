$(document).ready(function() {
	var company =[];
	var group = [];
	var dept =[];
	var bline = [];
	
	
	
	//load page --------------------------------------------------------------------------------------------------------
	getData();
	getInfo();
	function getData(){
		$.ajax({
			type: "GET",
			url: "/service/getbudgetline",
			success: function(data){
				$("#budgetline").empty();
				$.each(data, function(key, val){
					var dept = 0;
					row = '';
					$.each(val.blinelist, function(key1, val1){
						row = '<tr>';
						if(dept == 0){
							row+= '<td rowspan="'+val.blinelist.length+'">'+val.dept.group.company.companyname+'>'+val.dept.group.groupcode+'</td>';
							row+= '<td rowspan="'+val.blinelist.length+'">'+val.dept.deptname+'</td>';
							dept ++;
						}
						row+='<td>'+val1.blname+'</td>';
						row+='<td><button class="btn btn-info delblineofdept" data-dept-id="'+val.dept.deptid+'" data-bline-id="'+val1.blid+'">Delete</button>\
							</td>';
						row+='</tr>';
						$("#budgetline").append(row);
					})
					
				});
			},
			error: function(e){
				console.log("ERROR : ", e);
			}
		})
	}
	
	//get company, group, dept, budgetline----------------------------------------------------------------------------------
	function getInfo(){
		$.ajax({
			type : "GET",
			url : "/service/getallcompany",
			success : function(data) {
				company = data;	
				$.each(company, function(key, val){
					$("#company").append('<option value="'+val.companyid+'">'+val.companyname+'</option>');
				})
				$.ajax({
					type : "GET",
					url : "/service/getallgroup",
					success : function(data) {
						group = data;
						$.each(group, function(key, val){
							if(val.company.companyid==$("#company").val()){
								$("#group").append('<option value="'+val.groupid+'">'+val.groupcode+'</option>');
							}
						});
						$.ajax({
							type : "GET",
							url : "/service/getalldept",
							success : function(data) {
								dept = data;
								$.each(dept, function(key, val){
									if(val.group.groupid==$("#group").val()){
										$("#dept").append('<option value="'+val.deptid+'">'+val.deptname+'</option>');
									}
								})
							},
							error : function(e){
								console.log("ERROR : ", e);
							}
						});
					},
					error : function(e){
						console.log("ERROR : ", e);
					}
				});
			},
			error : function(e){
				console.log("ERROR : ", e);
			}
		});
	
		$.ajax({
			type : "GET",
			url : "/service/getallbline",
			success : function(data) {
				bline = data;
				$.each(bline, function(key, val){
					$(".bline").append('<option value="'+val.blid+'">'+val.blname+'</option>');
				})
			},
			error : function(e){
				console.log("ERROR : ", e);
			}
		});
	}
	
	$("body").off("click", "#addbline").on("click", "#addbline", function(){
		blinerow ='';
		blinerow += '<tr><td><select class="bline">';
		$.each(bline, function(key, val){
			blinerow+= '<option value="'+val.blid+'">'+val.blname+'</option>';
		})
		blinerow+= '</select></td>';
		blinerow+= '<td><button class="btn btn-info del-bline">Delete</button></td></tr>';
		$("#blinelist").append(blinerow);
	})
	
	$("#company").on("change", function(){
		$("#group").empty();
		$.each(group, function(key, val){
			if(val.company.companyid==$("#company").val()){
				$("#group").append('<option value="'+val.groupid+'">'+val.groupcode+'</option>');
			}
		})
		$("#dept").empty();
		$.each(dept, function(key, val){		
			if(val.group.company.companyid==$("#company").val() && val.group.groupid==$("#group").val()){
				$("#dept").append('<option value="'+val.deptid+'">'+val.deptname+'</option>');
			}
		})
	})
	
	$("#group").on("change", function(){
		$("#dept").empty();
		$.each(dept, function(key, val){
			if(val.group.groupid==$("#group").val()){
				$("#dept").append('<option value="'+val.deptid+'">'+val.deptname+'</option>');
			}
		})
	})
	
	$("body").off("click", ".del-bline").on("click", ".del-bline", function(){
		$(this).closest("tr").remove();
	})
	
	$("body").off("click", "#add-btn").on("click", "#add-btn", function(){
		var bline_list = [];
		$(".bline").each(function(){
			bline_list.push($(this).val());
		})
		var blinedept = {
			dept : $("#dept").val(),
			blinelist : bline_list
		}
		$.ajax({
			type: "POST",
			url : "/service/addblinetodept",
			contentType : "application/json",
    		data: JSON.stringify(blinedept),
			accept: 'text/plain',
			success : function(data){
				console.log(data);
				getData();
			},
			error: function(e){
				console.log("ERROR : ", e);
			}
		})
	})
	
	$("body").off("click", ".delblineofdept").on("click", ".delblineofdept", function(){
		$.ajax({
			type: "POST",
			url : "/service/delblineofdept",
			contentType : "application/json",
    		data: JSON.stringify([$(this).attr("data-dept-id"), $(this).attr("data-bline-id")]),
			accept: 'text/plain',
			success : function(data){
				console.log(data);
				getData();
			},
			error: function(e){
				console.log("ERROR : ", e);
			}
		})
	})
})