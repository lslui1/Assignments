$(document).ready(function() {

	$.ajax({url: "http://localhost:8080/students"}).then(function(data) {
    		console.log(data);
    		
    	for (var i=0; i < data.length; i++) {
    			
    		var item0 = $("<td>" + data[i].id + "</td>");
			var item1 = $("<td>" + data[i].first_name + "</td>");
	   		var item2 = $("<td>" + data[i].last_name + "</td>");
	   		var item3 = $("<td>" + data[i].sat + "</td>");
	   		var item4 = $("<td>" + data[i].gpa + "</td>");
			var item5 = ("<td> <button> Delete </button> <button class = modify-button> Update </button></td>");
				
	   		var tr = $("<tr></tr>");
	   		tr.append( item0 );
	   		tr.append( item1 );
	   		tr.append( item2 );
	   		tr.append( item3 );
	   		tr.append( item4 );
			tr.append( item5 );
				
   			$("#studentTable").append( tr );
   		}
    });
	
	
});