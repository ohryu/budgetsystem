	$(document).ready(function() {
		$("body").off("click", "#refresh").on("click", "#refresh", function(){
	    	var confirm = window.confirm("Are you sure want to refresh?");
	    	if(confirm == true){
				$.ajax({
					type : "GET",
					url : "/admin/refresh",
					success : function(data){
						alert(data);
					},
					error : function(e) {
						alert("System error, please try again!");
						console.log("ERROR : ", e);
					}
				})
			}
	    })
	})