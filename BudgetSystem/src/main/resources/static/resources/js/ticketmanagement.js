$(document).ready(function() {
	// VALIDATE ADD
	// USER------------------------------------------------------------------------------------------------
	$('#addModalForm').validate({
		rules:{
			newUsername:{
				required: true,
				email: true,
				maxlength: 50
			},
			newFullName:{
				required: true,
				maxlength: 30
			},
			newPassword:{
				required: true,
				maxlength: 16
			},
			confirmNewPass:{
				required: true,
				equalTo: '#new-password'
			},
			limitNum:{
				required: true,
				digits: true,
				min: 1
			}
		},
		messages:{
			newUsername:{
				required: "Username is required",
				email: "UserName must be email",
				maxlength: "Username must be less than 16 characters"
			},
			newFullName:{
				required: "Name is required",
				maxlength: "Name must be less than 30 characters"
			},
			newPassword:{
				required: "Password is required",
				maxlength: "Password must be less than 16 characters"
			},
			confirmNewPass:{
				required: "Confirm password is required",
				equalTo: "Confirm password did not match"
			},
			limitNum:{
				required: "Limit Number is required",
				digits: "Limit Number must be digit number",
				min: "Limit Number must be greater than 0"
			}
		}		
	})

	
	// CHECK URL IS ticketmanagement OR
	// ticketmanagement?txtSearch=...-----------------------------------------------------------------------------------------------------

	var textsearch = $('#tickets-management').attr("textsearch");
	if(textsearch==null || textsearch.trim()==''){
		// PAGINATION
		// TICKET------------------------------------------------------------------------------------------------------
		paginate(1,$("#ticket").attr("num"));
       
	}else{
		$.ajax({
			type : "POST",
			url : '/search/resultlistuser/' + textsearch,
			success : function(data) {
				$("#tickets-management").empty();
				$.each(data, function(key, val){
					setdatapopup(val);               
	            });
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	}    
	
	//paginate function
	function paginate(start, total){
		 window.pagObj = $('#pagination').twbsPagination({
			 	startPage: start,
	            totalPages: total,
	            visiblePages: 10,
	            onPageClick: function (evt, page) {
	                $("#tickets-management").empty();
	                var url = "/admin/tickets/"+page;
	                $.ajax({
	                    url : url,
	                    success : function(data) {
	                        $.each(data, function(key, val){
	                        	setdatapopup(val);                                                                     
	                        });
	                    }
	                });
	                $("html, body").animate({
	                    scrollTop: $('#tickets-management').offset().top 
	                });
	            }
	        });
	}
	
        
        // PARSE TICKET INFO INTO POPUP
		// EDIT-------------------------------------------------------------------------------------
        $("body").off("click", ".btn-ticket-edit").on("click", ".btn-ticket-edit", function() {
            var info_url = "/admin/ticket/"+$(this).attr("user");
            $.ajax({
                url : info_url,
                success : function(data) {
                    $("#ticket-id").val(data.idUser);
                    $("#ticket-mail").val(data.userName);
                    $("#ticket-name").val(data.fullName);
                    $("#ticket-role").val(data.role.nameRole);
                    $("#ticket-limit").val(data.limitNumber);
                    $("#ticket-borrowed").val(data.borrowedNumber);
                    $("#edit-ticket").attr('value',data.idUser);
                }
            });
        });
        
        // EDIT TICKET
        $("body").off("click", "#edit-ticket").on("click", "#edit-ticket", function(){
        	libuser = {iduser:  $("#ticket-id").val(),
        			   username:  $("#ticket-mail").val(),
        			   fullname: $("#ticket-name").val(),
        			   role: $("#ticket-role").val(),
        			   limit: $("#ticket-limit").val(),
        			   borrowed: $("#ticket-borrowed").val()
        			  };
        	mail = $("#ticket-mail").val().trim();
        	$.ajax({
    			type : "POST",
    			contentType : 'application/json; charset=utf-8',
    			cache:false,
    			url : "/admin/edit/ticket",
    			data : JSON.stringify(libuser),
    			success : function(data) {
    				if(data.indexOf('Successful')!=-1){
						swal("Successful!", data, "success");
						element = $(".active");
						element.removeClass('active');
						element.children().trigger('click');
						$('.swal2-container').click(function(){
							$('html, body').animate({
						        scrollTop: screenTop
						    }, 100);
							$("td.textPosition:contains('" + mail +"')").parent().css("background-color","rgba(0,255,0,0.2)");
						});
					}else{
						swal("Error!", data, "error");
					}
    			},
    			error : function(e) {
    				swal("Error!", "System error! Please try again.", "error");
    				console.log("ERROR : ", e);
    			} 	
    		});
        });
        
        // ADD USER----------------------------------------------------
        $("body").off("click", "#add-user").on("click", "#add-user", function(){
        	var url =  "/admin/add-user";
        	libuser = {
        			   username:  $("#new-username").val(),
        			   fullname: $("#new-fullname").val(),
        			   password: $('#new-password').val(),
        			   role: $("#new-role").val(),
        			   limit: $("#new-limitnum").val(),
        			  };
        	mail = $("#new-username").val().trim();
        	if ($('#addModalForm').valid()==false) {
        		swal("Error!", "Invalid information", "error");
			} else{
		        	$.ajax({
		    			type : "POST",
		    			contentType : 'application/json; charset=utf-8',
		    			url :url,
		    			data : JSON.stringify(libuser),
		    			success : function(data) {
		    				if(data[1].indexOf('Successful')!=-1 && $('#addModalForm').valid()){
								swal("Successful!", data[1], "success");
								$('#pagination').twbsPagination('destroy');
		    					paginate(data[0], data[0]);
		    					$("a.page-link:contains('"+data[0]+"')").parent().addClass("active");	
								$('.swal2-container').off('click').on('click',function(){			
									$("td.textPosition:contains('" + mail +"')").parent().css("background-color","rgba(0,255,0,0.2)");
									$("#new-username").val('');
			        			    $("#new-fullname").val('');
			        			    $('#new-password').val('');
			        			    $('#confirm-password').val('');
			        			    $("#new-limitnum").val('');
								});
							}else{
								swal("Error!", data[1], "error");
							}
		    			},
		    			error : function(e) {
		    				swal("Error!", "System error! Please try again.", "error");
		    				console.log("ERROR : ", e);
		    			} 	
		        	});
			}
        });
        // CLICK CLOSE EVENT----------------------------------------------
        $('#add-close').click(function() {
        						$("#new-username").val('');
		        			    $("#new-fullname").val('');
		        			    $('#new-password').val('');
		        			    $('#confirm-password').val('');
		        			    $("#new-limitnum").val('');
        });
        // PASRE BOROWED BOOK OF TICKET INTO BORROWED
		// POPUP----------------------------------------------------------------------
        $("body").off("click", ".btn-borrowed").on("click", ".btn-borrowed", function() {
        	ajaxBorrowedBook($(this).attr("user"));         
        });
        
        // RETURN
		// BOOK-----------------------------------------------------------------------------------------------------------
        $("body").off("click", ".btn-return").on("click", ".btn-return", function() {
        	var url = "/admin/return/"+$(this).attr("idborrow");
        	$.ajax({
    			type : "GET",
    			url : url ,
    			success : function(data) {
    				if(data.indexOf('Successful')!=-1){
	    				var user = $("#title-and-name").attr("user");		
	    				$(".btn-borrowed[user='" + user +"']").text( $("#title-and-name").attr("numborrowed")-1);
	    				ajaxBorrowedBook(user);
						swal("Successful!", data, "success");
    				}
    				else swal("Error!", data, "error");
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
        });
        
        // SEARCH
		// TICKET---------------------------------------------------------------------------------------------------------
        $('#w-input-searchuser').autocomplete({
    		autoSelectFirst: true,
    		serviceUrl: '/search/user',
    		paramName: "username",
    		delimiter: ",",
    		onSelect: function(suggestion) {
    			getinforuser(suggestion.data);
    			$(".divNextPage").attr('hidden','');
            },
    	   transformResult: function(response) {
    		    	
    		return {      	
    		  suggestions: $.map($.parseJSON(response), function(item) {
    			  var fullName = $.trim(item.fullName);
    			  var userName = $.trim(item.userName);
    		      return { value: fullName + '( Account: ' + userName + ' ) ', data: item.idUser };
    		   })
    		            
    		 };
            }
    	 });
        
        // GET TICKET INFO
		// FUNCTION----------------------------------------------------------------------------------------------
    	function getinforuser(iduser) {
    		$.ajax({
    			type : "GET",
    			dataType : 'json',
    			contentType : "application/json",
    			url : '/search/user/' + iduser,
    			success : function(data) {
    				if (data != null && data.idUser != null) {
    					$("#tickets-management").empty();
    					setdatapopup(data);
    				}
    				
    				 // window.location.href = "home";
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
    	}
    	
    	// SET DATA POPUP EDIT TICKET
		// FUNCTION-----------------------------------------------------------------------------------
    	function setdatapopup(val){
    		var status="";
            	if(val.borrowedNumber>0) status="";
            	else status="disabled";
                $("#tickets-management").append('<tr>\
                                                    <td class="textPosition" style="width:6%;">'+val.idUser+'</td>\
                                                    <td class="textPosition">'+val.userName+'</td>\
                                                    <td class="textPosition">'+val.fullName+'</td>\
                                                    <td class="textPosition">'+val.role.nameRole+'</td>\
                                                    <td class="textPosition" style="width:10%;">'+val.limitNumber+'</td>\
                                                    <td class="textPosition" style="width:13%;">\
                                                        <button type="button" class="btn btn-custom btn-sm btn-borrowed"\
                                                            data-toggle="modal" data-target="#modalview" user="'+val.idUser+'" '+status+'>'+val.borrowedNumber+'\
                                                        </button>\
                                                    </td>\
                                                    <td class="textPosition">\
                                                        <button type="button" class="btn btn-custom btn-sm btn-ticket-edit"\
                                                            data-toggle="modal" data-target="#add" user="'+val.idUser+'">\
                                                            <span class="glyphicon glyphicon-edit"></span> edit\
                                                        </button>\
                                                    </td>\
                                                </tr>');                                                                      
    	}
    	
    	
    	// GET BORROWED BOOK
		// FUNCTION---------------------------------------------------------------------------------------------
    	function ajaxBorrowedBook(iduser){
    		$.ajax({
                url : "/admin/borrowed/"+iduser,
                success : function(data) {
                    $("#ticket-borrowed-book").empty();
                    $("#title-and-name").text("Borroweds Books Of: "+data[0].user.fullName)
                    $("#title-and-name").attr("user",iduser);
                    $("#title-and-name").attr("numborrowed",data.length);
                    $.each(data, function(key, val){
                        $("#ticket-borrowed-book").append('<tr>\
                                                                <td class="textPosition">'+val.isbnBean.isbn+'</td>\
                                                                <td class="textPosition">'+val.isbnBean.book.titleOfBook+'</td>\
                                                                <td class="textPosition">'+val.isbnBean.book.author+'</td>\
                                                                <td class="textPosition">'+val.isbnBean.book.publishYear+'</td>\
                                                                <td class="textPosition">'+val.dateBorrow+'</td>\
                                                                <td class="textPosition">\
                                                                    <button type="button" class="btn btn-custom btn-sm btn-return" data-toggle="modal" data-target="#editModal" idborrow="'+val.idBorrow+'">\
                                                                        <span class="glyphicon glyphicon-erase"></span> Returned\
                                                                    </button>\
                                                                </td>\
                                                            </tr>');
                    })
                }
            });
    	}
    });