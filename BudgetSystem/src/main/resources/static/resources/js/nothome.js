$(function () {	
              
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
        
       
    
    });

