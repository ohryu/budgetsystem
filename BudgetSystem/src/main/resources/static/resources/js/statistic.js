$(function(){
	
	var today = new Date();
	var charMonth = '';
	if(today.getMonth()==0){
		charMonth = 'January'
	}
	if(today.getMonth()==1){
		charMonth = 'February'
	}
	if(today.getMonth()==2){
		charMonth = 'March'
	}
	if(today.getMonth()==3){
		charMonth = 'April'
	}
	if(today.getMonth()==4){
		charMonth = 'May'
	}
	if(today.getMonth()==5){
		charMonth = 'June'
	}
	if(today.getMonth()==6){
		charMonth = 'July'
	}
	if(today.getMonth()==7){
		charMonth = 'August'
	}
	if(today.getMonth()==8){
		charMonth = 'September'
	}
	if(today.getMonth()==9){
		charMonth = 'October'
	}
	if(today.getMonth()==10){
		charMonth = 'November'
	}
	if(today.getMonth()==11){
		charMonth = 'December'
	}
	
	
	$('#inputYear').val(today.getFullYear());
	$('#inputMonthAndYear').val(charMonth+ " "+ today.getFullYear());
	$('#inputDate').val(today.getDate() + " " + charMonth + ", " + today.getFullYear());
	$('#chooseYear').hide();
	$('#chooseMonth').hide();
	$('#chooseWeek').hide();
	loadWeek();
	
	$('#year').click(function(){
		$('#chooseYear').show();
		$('#chooseMonth').hide();
		$('#chooseWeek').hide();
		loadYear();
	}); 

	$('#inputYear').focusout(loadYear);

	$('#month').click(function(){
		$('#chooseYear').hide();
		$('#chooseMonth').show();
		$('#chooseWeek').hide();
		loadMonth();
	});

	$('#inputMonthAndYear').focusout(loadMonth);
	$('#week').click(function() {
		$('#chooseYear').hide();
		$('#chooseMonth').hide();
		$('#chooseWeek').show();
		loadWeek();
	}) 
	$('#inputDate').focusout(loadWeek);
	$('#yearOnly').calendar({
		  type: 'year'
		});

	$('#monthAndYear').calendar({
		  type: 'month'
		});
	$('#weekOfChoosenDate').calendar({
		  monthFirst: false,
		  type: 'date'
		});
	
	
})




//year part
function loadYear(){
	var thisYear = $('#inputYear').val();
	var dataPoints = [];
	
	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Total Borrowed Books of the Year" 
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "line",
			indexLabelFontColor: "red",
			indexLabel: "{y}",				
			xValueFormatString: "#",
			dataPoints: dataPoints
		}]
	});


	$.ajax({
	    url : "/home/statistic/yearly" ,
	    success : function(data) {
	    	for (var i = 0; i < data.length; i++) {
	    		dataPoints.push({
	    			x: data[i]._year,
	    			y: data[i].borrowed
	    		});
	    	}	    	
	    	chart.render();
	    	
	    	console.log('ok');
	    }
	 });

	
	var dataPoints2 = [];
	var chart2 = new CanvasJS.Chart("chartContainer2", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Top 5 Borrowed Books of "+ thisYear
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "doughnut",
			indexLabelFontColor: "red",
			indexLabel: "{title}: {y} (times)",				
			xValueFormatString: "#",
			dataPoints: dataPoints2
		}]
	});


	$.ajax({
	    url : "/home/statistic/topbook/year/" + thisYear ,
	    success : function(data2) {
	    	for (var i = 0; i < data2.length; i++) {
	    		dataPoints2.push({
	    			title: data2[i].Title,
	    			y: data2[i].total
	    		});
	    	}	    	
	    	chart2.render();
	    	console.log('ok');
	    }
	 });
}



//month part
function loadMonth(){
	var thisYear = $('#inputMonthAndYear').val().slice(-4);
	var charMonth = $('#inputMonthAndYear').val().split(' ', 1);
	var digitMonth = 0;
	//doi thang bang chu thanh thang bang so
	if(charMonth=='January'){
		digitMonth = 1;
	}
	if(charMonth=='February'){
		digitMonth = 2;
	}
	if(charMonth=='March'){
		digitMonth = 3;
	}
	if(charMonth=='April'){
		digitMonth = 4;
	}
	if(charMonth=='May'){
		digitMonth = 5;
	}
	if(charMonth=='June'){
		digitMonth = 6;
	}
	if(charMonth=='July'){
		digitMonth = 7;
	}
	if(charMonth=='August'){
		digitMonth = 8;
	}
	if(charMonth=='September'){
		digitMonth = 9;
	}
	if(charMonth=='October'){
		digitMonth = 10;
	}
	if(charMonth=='November'){
		digitMonth = 11;
	}
	if(charMonth=='December'){
		digitMonth = 12;
	}
	
	var dataPoints = [];

	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Total Borrowed Books of the Month in " + thisYear
		},
		axisY: {
			title: "Borrowed Books (Units)",
			titleFontSize: 24,
			interval: 5
		},
		axisX: {
			interval: 1,
			intervalType: "month",
			minimum: 0,
			maximum: 13,
			labelFormatter: function(e){
				switch(e.value){
				case 1:
					return 'Jan';
					break;
				case 2:
					return 'Feb';
					break;
				case 3:
					return 'Mar';
					break;
				case 4:
					return 'Apr';
					break;
				case 5:
					return 'May';
					break;
				case 6:
					return 'June';
					break;
				case 7:
					return 'July';
					break;
				case 8:
					return 'Aug';
					break;
				case 9:
					return 'Sep';
					break;
				case 10:
					return 'Oct';
					break;
				case 11:
					return 'Nov';
					break;
				case 12:
					return 'Dec';
					break;
				default:
					return '';
				}
				
				
			},
			valueFormatString: "#"
		},
		dataPointMaxWidth: 40,
		data: [{
			type: "column",
			indexLabel: "{y}",		
			indexLabelFontColor: "red",
			xValueFormatString: "#",
			dataPoints: dataPoints
		}]
	});


	$.ajax({
	    url : "/home/statistic/month/" + thisYear,
	    success : function(data) {
	    	for (var i = 0; i < data.length; i++) {
	    		dataPoints.push({
	    			x: data[i]._month,
	    			y: data[i].borrowed
	    		});
	    	}
	    	if(data != ''){
	    		if(data[data.length-1]._month!=12){
		    		dataPoints.push({
		    			x: 12
		    		});
		    	}
		    	if(data[data.length-1]._month!=1){
		    		dataPoints.push({
		    			x: 1
		    		});
		    	}
	    	}
	    	chart.render();
	    	console.log('ok');
	    }
	 });
	
	
	
	var dataPoints2 = [];
	var chart2 = new CanvasJS.Chart("chartContainer2", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Top 5 Borrowed Books of "+ charMonth +" "+ thisYear
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "doughnut",
			indexLabelFontColor: "red",
			indexLabel: "{title}: {y} (times)",				
			xValueFormatString: "#",
			dataPoints: dataPoints2
		}]
	});


	$.ajax({
	    url : "/home/statistic/topbook/month/" + thisYear + "/" + digitMonth ,
	    success : function(data2) {
	    	for (var i = 0; i < data2.length; i++) {
	    		dataPoints2.push({
	    			title: data2[i].Title,
	    			y: data2[i].total
	    		});
	    	}
	    	chart2.render();
	    	console.log('ok');
	    }
	 });
}







//Week part
Date.prototype.getWeek = function() {
	  var jan4th = new Date(this.getFullYear(),0,4);
	  return Math.ceil((((this - jan4th) / 86400000) + jan4th.getDay()+1)/7);	
}
function loadWeek(){
	var today = new Date();
	var year = $('#inputDate').val().slice(-4);
	var charMonth = $('#inputDate').val().substring($('#inputDate').val().indexOf(" ")+1, $('#inputDate').val().indexOf(","));
	var digitMonth = 0;
	//doi thang bang chu thanh thang bang so
	if(charMonth=='January'){
		digitMonth = 0;
	}
	if(charMonth=='February'){
		digitMonth = 1;
	}
	if(charMonth=='March'){
		digitMonth = 2;
	}
	if(charMonth=='April'){
		digitMonth = 3;
	}
	if(charMonth=='May'){
		digitMonth = 4;
	}
	if(charMonth=='June'){
		digitMonth = 5;
	}
	if(charMonth=='July'){
		digitMonth = 6;
	}
	if(charMonth=='August'){
		digitMonth = 7;
	}
	if(charMonth=='September'){
		digitMonth = 8;
	}
	if(charMonth=='October'){
		digitMonth = 9;
	}
	if(charMonth=='November'){
		digitMonth = 10;
	}
	if(charMonth=='December'){
		digitMonth = 11;
	}
	
	var date = $('#inputDate').val().substr(0, $('#inputDate').val().indexOf(' '));
	
	
	var choosenDate = new Date(year, digitMonth, date);
	var weekOfChoosenDate = choosenDate.getWeek();
	
	
	var dataPoints = [];
		
	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Total Borrowed Books of this Week"
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	  
			minimum: 0,
			maximum: 8,
			labelFormatter: function(e){
				switch(e.value){
				case 1:
					return 'Sunday';
					break;
				case 2:
					return 'Monday';
					break;
				case 3:
					return 'Tuesday';
					break;
				case 4:
					return 'Wednesday';
					break;
				case 5:
					return 'Thursday';
					break;
				case 6:
					return 'Friday';
					break;
				case 7:
					return 'Saturday';
					break;
				default:
					return '';
				}
			},
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####",
			
	    },
		data: [{
			type: "column",
			indexLabelFontColor: "red",
			indexLabel: "{y}",				
			
			dataPoints: dataPoints
		}]
	});


	$.ajax({
	    url : "/home/statistic/week/" + year + "/" + weekOfChoosenDate ,
	    success : function(data) {
	    	for (var i = 0; i < data.length; i++) {
	    		if(data[6]==null)
		    		dataPoints.push({
		    			x: data[i]._day,
		    			y: data[i].borrowed
		    		});
	    		
	    	}
	    	if(data != ''){
	    		if(data[data.length-1]._day!=7){
		    		dataPoints.push({
		    			x: 7
		    		});
		    	}
		    	if(data[data.length-1]._day!=1){
		    		dataPoints.push({
		    			x: 1
		    		});
		    	}
	    	}
	    	
	    	
	    	chart.render();
	    	console.log(dataPoints);
	    }
	 });
	
	
	var dataPoints2 = [];
	var chart2 = new CanvasJS.Chart("chartContainer2", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Top 5 Borrowed Books of this week"
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "doughnut",
			indexLabelFontColor: "red",
			indexLabel: "{title}: {y} (times)",				
			xValueFormatString: "#",
			dataPoints: dataPoints2
		}]
	});


	$.ajax({
	    url : "/home/statistic/topbook/week/" + year + "/" + weekOfChoosenDate ,
	    success : function(data2) {
	    	for (var i = 0; i < data2.length; i++) {
	    		dataPoints2.push({
	    			title: data2[i].Title,
	    			y: data2[i].total
	    		});
	    	}
	    	chart2.render();
	    	console.log('ok');
	    }
	 });
}



	
	