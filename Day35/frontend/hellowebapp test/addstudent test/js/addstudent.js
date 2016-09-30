var methodType = "POST";
var combinedurl = "http://localhost:8080/addstudent/"
	
$(document).ready(function(){
	
	$('#messageBox').keypress(function (e) {
		var key = e.which;
		if(key == 13)  // the enter key code
			{
			$("#addstudentButton").click();
			return false;  
			}
	});  
	
	$("#addstudentButton").click(function() {
		console.log("Clicked add student button");
				
		var afname = $("#fnameBox").val() + "/";
		var alname = $("#lnameBox").val() + "/";
		var asat = $("#satBox").val() + "/";
		var agpa = $("#gpaBox").val() + "/";
		var amajorid = $("#majoridBox").val();
		
		combinedurl = combinedurl + afname + alname + asat + agpa + amajorid;
		$.ajax({url: combinedurl}).then(function(result) {
    	console.log(result);});
		
	});	
});	
