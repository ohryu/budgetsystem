$(document).ready(function() {
	var company =[];
	var criteria = [];
	var criteria_detail = [];
	var isEditting = 0;
	
	$("#error-btn").trigger("click");
	function getData(){
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
		})
		$.ajax({
			type : "GET",
			url : "/service/getallcriteria",
			success : function(data) {
				criteria = data;	
			},
			error : function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		})
		$.ajax({
			type : "GET",
			url : "/service/getallcriteriadetail",
			success : function(data) {
				criteria_detail = data;
				$.each(criteria_detail, function(key, val){
					crt = 0;
					crtrow = 0;
					$.each(val.criterialist, function(key1, val1){
						if(val1.cdList.length==0) crtrow+=1;
						else crtrow+=val1.cdList.length;
					})
					$.each(val.criterialist, function(key1, val1){
						comp = 0;
						$.each(val1.cdList, function(key2, val2){
							table = "";
							table+='<tr>';
							if(crt==0){
								table+='<td rowspan="'+crtrow+'">'+val.criteria.criterianame+'</td>';
								table+='<td rowspan="'+crtrow+'"><button class="btn btn-info del-criteria" data-criteria-id="'+val.criteria.criteriaid+'" disabled>Delete</button>\
							</td>';
								crt++;
							}
							if(comp ==0){
								table+='<td rowspan="'+val1.cdList.length+'">'+val1.company.companyname+'</td>';
								comp++;
							}
							table+='<td>'+val2.dept.deptname+'</td>';
							table+='<td><input type="number" value="'+val2.amount+'" data-cd-id="'+val2.cdid+'" size="10"></td>';
							$("#table").append(table);
						})
					})
				})
				$("td input").attr('disabled','disabled');
				$("#save-criteria").attr('disabled','disabled');
			},
			error : function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		})
		
	}
	
	getData();
	$("body").off("click", "#add-criteria").on("click", "#add-criteria", function(){
		if($("#add-criteria-name").val().length==0){
			alert("Criteria name must not be blank");
		}else{
			$.ajax({
				type: "POST",
				url : "/service/addcriteria",
				contentType : "application/json",
	    		data: JSON.stringify([$("#add-criteria-name").val()]),
				accept: 'text/plain',
				success : function(data){
					console.log(data);
					$("#table").empty();
					getData();
				},	
				error: function(e){
					alert("System error, Please try again!")
					console.log("ERROR : ", e);
				}
			})
		}
		
	})
	
	$("body").off("click", "#edit-criteria").on("click", "#edit-criteria", function(){
		$("td input").removeAttr('disabled');
		$(".del-criteria").removeAttr('disabled');
		$("#save-criteria").removeAttr('disabled');
		$(this).attr('disabled','disabled');
		isEditting = 1;
	})
	
	$("body").off("click", "#save-criteria").on("click", "#save-criteria", function(){
		amounts = [];
		$("td input").each(function(){
			if($(this).val().length==0){
				amount = [$(this).attr('data-cd-id'), 0];
			}else{
				amount = [$(this).attr('data-cd-id'), $(this).val()];
			}
			amounts.push(amount);
		})
		$.ajax({
			type: "POST",
			url : "/service/savecriteria",
			contentType : "application/json",
    		data: JSON.stringify(amounts),
			accept: 'text/plain',
			success : function(data){
				console.log(data);
				$("#table").empty();
				getData();
				$("#edit-criteria").removeAttr('disabled');
			},	
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		})
	})
	
	$("body").off("click", ".del-criteria").on("click", ".del-criteria", function(){
		criteriaid = $(this).attr('data-criteria-id');
		$.ajax({
			type : "GET",
			url : "/service/delcriteria/"+criteriaid,
			succcess: function(data){
				console.log(data);
				getData();
				$("#edit-criteria").removeAttr('disabled');
			},
			error: function(e){
				alert("System error, Please try again!")
				console.log("ERROR : ", e);
			}
		})
	})
	
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
})