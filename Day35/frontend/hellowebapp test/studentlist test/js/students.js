$(document).ready(function() {

	$.ajax({url: "http://localhost:8080/students"}).then(function(data) {
    		console.log(data);
    		
    	for (var i=0; i < data.length; i++) {
    			
    		var item0 = $("<td class='text-left'>" + data[i].id + "</td>");
			var item1 = $("<td class='text-left'>" + data[i].first_name + "</td>");
	   		var item2 = $("<td class='text-left'>" + data[i].last_name + "</td>");
	   		var item3 = $("<td class='text-left'>" + data[i].sat + "</td>");
	   		var item4 = $("<td class='text-left'>" + data[i].gpa + "</td>");
			var item5 = $("<td class='text-left'>" + data[i].major_id + "</td>")
			var item6 = $("<td class='text-left'> <button> Delete </button> <button class = modify-button> Update </button></td>");
				
	   		var tr = $("<tr></tr>");
	   		tr.append( item0 );
	   		tr.append( item1 );
	   		tr.append( item2 );
	   		tr.append( item3 );
	   		tr.append( item4 );
			tr.append( item5 );
			tr.append( item6 );
				
   			$("#studentTable").append( tr );
   		}
    });
	
	
});