var methodType = "POST";
var combinedurl = "http://localhost:8080/deletestudent/"
	
$(document).ready(function(){
	
	$('#messageBox').keypress(function (e) {
		var key = e.which;
		if(key == 13)  // the enter key code
			{
			$("#addstudentButton").click();
			return false;  
			}
	});  
	
	$("#deletestudentButton").click(function() {
		console.log("Clicked delete student button");
				
		var aid = $("#idBox").val();
		
		combinedurl = combinedurl + aid;
		$.ajax({url: combinedurl}).then(function(result) {
    	console.log(result);});
		
	});	
});	
