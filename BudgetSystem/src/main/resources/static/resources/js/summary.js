$(document).ready(function() {
	$("body").off("click", "#add-budget").on("click", "#add-budget", function(){
		$.ajax({
			type : "GET",
			url : '/service/getwbbybl/' + $('#bg-line').val(),
			success : function(data) {
				tool = 0;
				if($("#all").prop('checked') == true){
					$(".sponsor").each(function (){
						tool++;
						row = '<tr class="budget-row">\
						  			<td class="control-dept" data-dept-id="'+$("#c-dept").attr("data-dept-id")+'">FAD</td>\
						  			<td class="sponsor-dept" data-dept-id="'+$(this).val()+'">'+$(this).attr("name")+'</td>\
						  			<td class="bl">'+$('#bg-line').val()+'</td>\
						  			<td>\
						  				<select class="wb">';
						 $.each(data, function(key, val){
							 row+='<option value="'+val.wbid+'">'+val.wbname+'</option>'
						 }) 					
						 
						 row+=' 		</select>\
						  			</td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="date" name="" size="10"></td>\
						  			<td><input type="date" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="button" name="" value="Delete"></td>';
						if(tool==1) row+='<td class="tool" rowspan="'+$('.sponsor').length+'">\
						  				<input type="radio" name="tool">Utilize historical amount<br>\
						  				<input type="text" name="" placeholder="% add in" size="10"><br>\
						  				<input type="radio" name="tool"> Cost driver <br>\
						  				<input type="text" name="" placeholder="Cost allocation" size="10">\
						  			</td>';				
						row+='</tr>';
						$(".summary table").append(row);
					});
				}else{
					$(".sponsor:checked").each(function(){
						tool++;
						row = '<tr class="budget-row">\
						  			<td class="control-dept" data-dept-id="'+$("#c-dept").attr("data-dept-id")+'">FAD</td>\
						  			<td class="sponsor-dept" data-dept-id="'+$(this).val()+'">'+$(this).attr("name")+'</td>\
						  			<td class="bl">'+$("#bg-line").val()+'</td>\
						  			<td>\
						  				<select class="wb">';
						 $.each(data, function(key, val){
							 row+='<option value="'+val.wbid+'">'+val.wbname+'</option>'
						 }) 					
						 
						 row+=' 		</select>\
						  			</td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="date" name="" size="10"></td>\
						  			<td><input type="date" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="button" name="" value="Delete"></td>';
						if(tool==1) row+='<td class="tool" rowspan="'+$(".sponsor").filter(':checked').length+'">\
						  				<input type="radio" name="tool">Utilize historical amount<br>\
						  				<input type="text" name="" placeholder="% add in" size="10"><br>\
						  				<input type="radio" name="tool"> Cost driver <br>\
						  				<input type="text" name="" placeholder="Cost allocation" size="10">\
						  			</td>';				
						row+='</tr>';
						$(".summary table").append(row);
					});
				}
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});	
	});
	$("body").off("click", "#menu").on("click", "#menu", function(){
		document.getElementById("mySidebar").style.display = "block";
	})
    $("body").off("click", "#close").on("click", "#close", function(){
    	document.getElementById("mySidebar").style.display = "none";
    })
    $("body").off("click", ".dept").on("click", ".dept", function(){
    	$("#c-dept").attr("data-dept-id",$(this).attr("data-dept-id"));
		$("#c-dept").text($(this).text());
    	$.ajax({
    		type : "GET",
			url : '/service/summarybydept/' + $(this).attr("data-dept-id"),
			success : function(data) {
				
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
    	});
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
    $("div a.dept").first().trigger("click");
})