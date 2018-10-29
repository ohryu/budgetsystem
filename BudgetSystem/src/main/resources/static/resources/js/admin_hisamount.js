$(document).ready(function() {
	function getData(companyid){
		var bline = [];
		var hisamount = [];
		$.ajax({
			url : "/service/gethisbl/"+companyid,
			success : function(data){
				bline = data;
			},
			error : function(e){
				console.log("Error : ", e);
			}
		})
		$.ajax({
			type : "GET",
			url : "/service/gethisamount/"+companyid,
			success : function(data){
				hisamount = data;
				tbody='<tbody>';
				thead = '<thead>\
							<tr>\
								<td>Budget Name</td>\
								<td>Code</td>';
				$.each(hisamount, function(key1, val1){
					if(val1.wbcode==bline[0])
					thead +='<td>'+val1.sponsor+'</td>';
				})
				thead+='</tr></thead>';
				$.each(bline, function(key,val){
					tbody+='<tr>';
					col = 0;
					$.each(hisamount, function(key1, val1){
						if(val1.wbcode==val && col==0){
							tbody+='<td>'+val1.wbname+'</td>';
							tbody+='<td>'+val1.wbcode+'</td>';
							col++;
						}
						if(val1.wbcode==val){
							tbody+='<td>'+parseInt(val1.amount).toLocaleString()+'</td>';
						}
					})
					tbody+='</tr>';
				})
				tbody+='</tbody>';
				$("table").append(thead);
				$("table").append(tbody);
			},
			error : function(e){
				console.log("Error : ", e);
			}
		})
		
	}
	getData($("#company").val());
	
	$("#company").on("change", function(){
		$("table").empty();
		getData($("#company").val());
	})
})