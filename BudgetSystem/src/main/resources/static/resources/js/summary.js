$(document).ready(function() {
	$("body").off("click", "#add-budget").on("click", "#add-budget", function(){
		$.ajax({
			type : "GET",
			url : '/admin/getwbbybl/' + "3",
			success : function(data) {
				tool = 0
				if($("#all").prop('checked') == true){
					$(".sponsor").each(function (){
						tool++;
						row = '<tr class="budget-row">\
						  			<td>FAD</td>\
						  			<td>POS</td>\
						  			<td>Taxi</td>\
						  			<td>\
						  				<select>\
						  					<option>NEW</option>\
						  					<option>sda</option>\
						  					<option>dcs</option>\
						  				</select>\
						  			</td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="date" name="" size="10"></td>\
						  			<td><input type="date" name="" size="10"></td>\
						  			<td><input type="text" name="" size="10"></td>\
						  			<td><input type="button" name="" value="Delete"></td>';
						if(tool==1) row+='<td class="tool" rowspan="'+data.length+'">\
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
						$(".summary table").append('<tr class="budget-row">\
									  			<td>FAD</td>\
									  			<td>POS</td>\
									  			<td>Taxi</td>\
									  			<td>\
									  				<select>\
									  					<option>NEW</option>\
									  					<option>sda</option>\
									  					<option>dcs</option>\
									  				</select>\
									  			</td>\
									  			<td><input type="text" name="" size="10"></td>\
									  			<td><input type="text" name="" size="10"></td>\
									  			<td><input type="text" name="" size="10"></td>\
									  			<td><input type="date" name="" size="10"></td>\
									  			<td><input type="date" name="" size="10"></td>\
									  			<td><input type="text" name="" size="10"></td>\
									  			<td><input type="button" name="" value="Delete"></td>\
									  			<td rowspan="2">\
									  				<input type="radio" name="tool">Utilize historical amount<br>\
									  				<input type="text" name="" placeholder="% add in" size="10"><br>\
									  				<input type="radio" name="tool"> Cost driver <br>\
									  				<input type="text" name="" placeholder="Cost allocation" size="10">\
									  			</td>\
									  		</tr>');
					});
				}
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});	
	});
	$("#menu").click(function(){
		document.getElementById("mySidebar").style.display = "block";
	})
    $("#close").click(function(){
    	document.getElementById("mySidebar").style.display = "none";
    })
    
})