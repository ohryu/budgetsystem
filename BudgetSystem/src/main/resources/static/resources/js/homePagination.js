$(document).ready(function() {
	function valid(){
		a = $(".price").each(function(){
			if($(this).text().indexOf("Unavailable")!=-1) $(this).css("background-color","rgba(255,0,0,0.5)");
			else $(this).css("background-color","rgba(0,255,0,0.5)");
		});
	}
	
    valid();   
	
	//CHECK URL IS home OR home?txtSearch=...-----------------------------------------------------------------------------------------------------

	var textsearch = $('.galary').attr("textsearch");
	if(textsearch==null || textsearch.trim()==''){
		//PAGINATE--------------------------------------------------------------------------------------------------------------
		var paginate_time = 0;
		var total = $(".galary").attr("num");    
		window.pagObj = $('#pagination').twbsPagination({
            totalPages: total,
            visiblePages: 10,
            onPageClick: function (evt, page) {
                $(".galary").empty();
                var url = "/home/page/"+page;
                $.ajax({
                    url : url,
                    success : function(data) {
                        $.each(data, function(key, val){
                        	setdatawhensearchbook(val);               
                        });
                        valid();
                    }
                });
                if(paginate_time==1)
	                $("html, body").animate({
	    	            scrollTop: $('.lineDown').offset().top 
	    	        });
                else paginate_time=1;
            }
        });
	}else{
		$.ajax({
			type : "POST",
			url : '/search/resultlistbook/' + textsearch,
			success : function(data) {
				$(".galary").empty();
				$(".latestcars").empty();
                $(".maincontent-area").empty();
				$.each(data, function(key, val){
	            	setdatawhensearchbook(val);               
	            });
				valid();
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	}
	
        
        
        //SHOW BOOK'S INFO IN POP UP--------------------------------------------------------------------------------------------
        $("body").on("click", ".btn-info", function() {
            var info_url = "./home/book/"+$(this).attr("isbn");
            $.ajax({
                url : info_url,
                success : function(data) {
                	setdatatomodal(data);
                }
            });
        });
        
        //SEARCH BOOK-----------------------------------------------------------------------------------------------------------
        $('#w-input-search').autocomplete({
    		autoSelectFirst: true,
    		serviceUrl: '/search/book',
    		paramName: "titlebook",
    		delimiter: ",",
    		onSelect: function(suggestion) {
    			checkvalidateisbn(suggestion.data);
            },
    	   transformResult: function(response) {
    		    	
    		return {      	
    		  suggestions: $.map($.parseJSON(response), function(item) {
    			  var titleOfBook = $.trim(item.book.titleOfBook);
    			  var author = $.trim(item.book.author);
    		      return { value: titleOfBook + ' ( Author: ' + author + ' ) ', data: item.isbn };
    		   })
    		            
    		 };
            }
    	 });
        
        //BORROW BOOK-----------------------------------------------------------------------------------------------------------
        $("body").on("click", ".btn-borrow", function() {
        	var isbn = $(this).attr("isbn");
        	$.ajax({
    			type : "GET",
    			url : '/home/borrow/' + isbn,
    			success : function(data) {
    					if(data[1].indexOf("Successful")!=-1){
    						swal("Successful!", data[1], "success");
    						if(data[0]=="0"){
    							$(".price[isbn='" + isbn +"']").text("Status: Unavailable");
    							$(".price[isbn='" + isbn +"']").css("background-color","rgba(255,0,0,0.5)");
    							$(".btn-borrow[isbn='"+ isbn +"']").attr("disabled", true);
    						}
    					}
    					else{
    						swal("Error!", data[1], "error");
    					}
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
        });
        
        //SHOW BORROWED BOOK
        $('body').off('click', '#menu-borrowed').on('click', '#menu-borrowed', function(){
        	$.ajax({
        		type : "GET",
        		url : "/home/borrowed-books",
        		success : function(data){
        			$('#ticket-borrowed-book').empty();
        			$.each(data , function(key, val){
        				$('#ticket-borrowed-book').append('<tr>\
																<td>'+val.isbnBean.isbn+'</td>\
																<td>'+val.isbnBean.book.titleOfBook+'</td>\
																<td>'+val.isbnBean.book.author+'</td>\
																<td>'+val.isbnBean.book.publishYear+'</td>\
																<td>'+val.dateBorrow+'</td>\
															</tr>');       													
        			});
        		}
        	});
        });
        
        //SCROLL TO CONTACT DIV
        $("body").on('click', '#menu-contact', function() {
            $('html, body').animate({
                scrollTop: $(".contact").offset().top
            }, 1000);
        });
        
        //CHECK ISBN------------------------------------------------------------------------------------------------------------
    	function checkvalidateisbn(isbn) {
    		$.ajax({
    			type : "GET",
    			url : '/bookmanagement/checkisbn/' + isbn,
    			success : function(data) {
    				setdatatomodal(data);
    				$("#mytest").click();
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
    	}
    	
    	//FUNCTION TO SHOW BOOK'S INFO------------------------------------------------------------------------------------------ 
    	function setdatatomodal(data){
    		if (data != null && data.isbn != null) {
				if(data.totalBook<=data.numberBooksBorrowed){
					$("button#addBook").attr('disabled','');
				}else{
					$("button#addBook").removeAttr('disabled');
				}
			    $("#book-image").attr("src",data.book.image);
			    $("#book-isbn").text(data.isbn);
			    $("#book-title").text(data.book.titleOfBook);
			    $("#book-author").text(data.book.author);
			    $("#book-publish-year").text(data.book.publishYear);
			    $("#book-description").text(data.book.shortDescription);
			    $("#addBook").attr('isbn',data.isbn);
			}
    	}
    	
    	//FUNCTION TO SHOW BOOK lIST-------------------------------------------------------------------------------------------- 
    	function setdatawhensearchbook(val){
    		var status = "Unavailable";
        	var notvailable = "disabled";
        	if(val.totalBook>val.numberBooksBorrowed){
        		status = "Available";
        		notvailable = "";
        	}
            $(".galary").append("<div class='product-carousel well well-sm itemBook'>\
									<div class='single-product'>\
										<div class='product-f-image'>\
											<div class='txthover'>\
												<img class='setImgListBook' src='"+val.book.image+"'>\
												<div class='txtcontent'>\
													<div class='stars'>\
														<div class='glyphicon glyphicon-star'></div>\
														<div class='glyphicon glyphicon-star'></div>\
														<div class='glyphicon glyphicon-star'></div>\
													</div>\
													<div class='simpletxt'>\
														<p class='name'>Title: "+val.book.titleOfBook+"</p>\
														<p>ISBN: "+val.isbn+"</p>\
														<p>Author: "+val.book.author+"</p>\
														<p class='price' isbn='"+val.isbn+"'>Status: "+status+"</p>\
														<p>Year publish: "+val.book.publishYear+"<p/>\
														<button class='btn btn-info' data-toggle='modal' data-target='#addModal' isbn='"+val.isbn+"' >READ MORE</button>\
														<button data-toggle='modal' data-target='#addModal' id='mytest' style='display: none;'></button>\
														<button class='btn btn-success btn-borrow' isbn='"+val.isbn+"' "+notvailable+">BORROW</button>\
													</div>\
													<div class='stars2'>\
														<div class='glyphicon glyphicon-star'></div>\
														<div class='glyphicon glyphicon-star'></div>\
														<div class='glyphicon glyphicon-star'></div>\
													</div>\
												</div>\
											</div>\
										</div>\
									</div>\
								</div>");  
    	}
    	
    	
    	function loadbeforeloadpage(){
    		alert("key press");
    		var titlebook = $('#w-input-search').val();
    		$.ajax({
    			type : "POST",
    			url : '/search/resultlistbook/' + titlebook,
    			success : function(data) {
    				$(".galary").empty();
    				setdatawhensearchbook(data);
    			},
    			error : function(e) {
    				console.log("ERROR : ", e);
    			}
    		});
    	}
//    	$('#w-input-search').bind("enterKey",function(e){
//    		alert("key press");
//    		var titlebook = $('#w-input-search').val();
//    		$.ajax({
//    			type : "POST",
//    			url : '/search/resultlistbook/' + titlebook,
//    			success : function(data) {
//    				$(".galary").empty();
//    				setdatawhensearchbook(data);
//    			},
//    			error : function(e) {
//    				console.log("ERROR : ", e);
//    			}
//    		});
//    		});
//    		$('#w-input-search').keyup(function(e){
//    		    if(e.keyCode == 13)
//    		    {
//    		        $(this).trigger("enterKey");
//    		    }
//    		});
    	
    
    });

