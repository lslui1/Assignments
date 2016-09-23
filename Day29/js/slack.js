var mytoken = null;
var channelName = null;
var channelID = null;
var myInterval = null;

$(document).ready(function(){
	
	mytoken = getSlackToken();
	var methodType = "GET";
	var url = "https://slack.com/api/channels.list"

	
	$.ajax(url, {data: {token: mytoken}}, {method: methodType}, {success: setStatusMsg()}).then(function(result) {
		console.log(result);
		
		for (var i=0; i < result.channels.length; i++) {

			var item_name = result.channels[i].name;
		
			var radio_home = document.getElementById("leftBox");
			var list_button = makeRadioButton("channelButtons", item_name, item_name);
			radio_home.appendChild(list_button);
			$("#leftBox").append("<br>");
		}
		
	});
	
	$('#messageBox').keypress(function (e) {
		var key = e.which;
		if(key == 13)  // the enter key code
			{
			$("#postMessage").click();
			return false;  
			}
	});  
	
	$("#postMessage").click(function() {
		console.log("Clicked post message button");

		var theMsg = $("#messageBox").val();
		$("#messageBox").val(" ");
		console.log(theMsg);
		
		var methodType = "POST";
		channelName = $("input[type='radio'][name='channelButtons']:checked").val();
		console.log(channelName);
		var url = "https://slack.com/api/chat.postMessage";

		$.ajax(url, 
		{data: {token: mytoken, as_user: true, channel: channelName, text: theMsg}}, 
		{method: methodType} 
		).then(function(result) {
			console.log(result);
			if (result.ok === true) {
				successfulPost(result);
				channelID = result.channel;
				getChatHistory();
			} else {
				unsuccessfulPost(result);
			}
		});
		
	});	
});	

function makeRadioButton(name, value, text) {

    var label = document.createElement("label");
    var radio = document.createElement("input");
    radio.type = "radio";
    radio.name = name;
    radio.value = value;

    label.appendChild(radio);

    label.appendChild(document.createTextNode(text));
    return label;
  }
  
function setStatusMsg() {
	$("#responseArea").text("Status: we are alive!").show();
}

function unsuccessfulPost(result) {
	if (result.ok === false) {
		$("#responseArea").text("Error: " + result.error).show();
	}
}

function getChatHistory() {
	var url = "https://slack.com/api/channels.history";
	var methodType = "GET";

	$.ajax(url, 
		{data: {token: mytoken, as_user: true, channel: channelID, inclusive: 1, count: 15}}, 
		{method: methodType} 
		).then(function(result) {
			console.log(result);
			if (result.ok === true) {
				printToChat(result);
				window.clearInterval(myInterval);
				myInterval = window.setInterval(function(){
							getChatHistory();
							}, 10000);
			}
		});
}

function printToChat(result) {
	
	$("#chatBox").text('').show();
	
	for (var i=0; i < result.messages.length; i++) {
		var timestamp = result.messages[i].ts;
		var newDate = new Date(timestamp * 1000);
		
		var cHour = newDate.getHours();
		var cMin = newDate.getMinutes();
		var cSec = newDate.getSeconds();
		if (cHour < 10) cHour = '0' + cHour;
		if (cMin < 10) cMin = '0' + cMin;
		if (cSec < 10) cSec = '0' + cSec;
		var cTime = cHour + ":" + cMin + ":" + cSec;
		var user_name = result.messages[i].user;
		var user_text = result.messages[i].text;
		var aString = cTime + " : " + user_name + " : " + user_text;
		console.log(aString);
				
		$("#chatBox").append(aString + "\n");
	}

}


function successfulPost(result) {
	var now = new Date(Date.now());
	$("#responseArea").text("Message sent successfully to " + result.channel + " at " + now).show();
}