var methodType = "POST";

	
$(document).ready(function(){
	
	$('#messageBox').keypress(function (e) {
		var key = e.which;
		if(key == 13)  // the enter key code
			{
			$("#addstudentButton").click();
			return false;  
			}
	});  
	
	$("#getstudentButton").click(function() {
		console.log("Clicked get student button");
		
		var aid = $("#idBox").val();
		var combinedurl = "http://localhost:8080/student/";
		combinedurl += aid;
		
		$.ajax({url: combinedurl}).then(function(data) {
			console.log(data);   		
    			
    		var item0 = data.id;
			var item1 = data.first_name;
	   		var item2 = data.last_name;
	   		var item3 = data.sat;
	   		var item4 = data.gpa;
			var item5 = data.major_id;		
		
   			$("#idBox").text(item0);
			$("#getstudentButton").text(item0);
			$("#fnameBox").text(item1);
			$("#lnameBox").text(item2);
			$("#satBox").text(item3);
			$("#gpaBox").text(item4);
			$("#majoridBox").text(item5);
    		
		});
	});
	
	$("#updatestudentButton").click(function() {
		console.log("Clicked update student button");
				
		var combinedurl = "http://localhost:8080/updatestudent/"
		var aid = $("#idBox").val() + "/";
		var afname = $("#fnameBox").val() + "/";
		var alname = $("#lnameBox").val() + "/";
		var asat = $("#satBox").val() + "/";
		var agpa = $("#gpaBox").val() + "/";
		var amajorid = $("#majoridBox").val();
		
		combinedurl = combinedurl + aid + afname + alname + asat + agpa + amajorid;
		
		$.ajax({url: combinedurl}).then(function(result) {
    	console.log(result);});
		
	});	
		
});	
