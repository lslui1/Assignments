var currentNumber = 0;
var numThreshold = 8;
var incdecVal = 1;
var randomNum = 1;

$(document).ready(function() {

	$("#decButton").click(function() {
		console.log("Clicked decrement button");
		currentNumber = currentNumber - incdecVal;
		numColor();
		isDivisible();
		hasSeven();
		isZero();
		$("#numBox").val(currentNumber);
	});

	$("#incButton").click(function() {
		console.log("Clicked increment button");
		currentNumber = currentNumber + incdecVal;
		numColor();
		isDivisible();
		hasSeven();
		isZero();
		$("#numBox").val(currentNumber);
	});
  
	$("#resetButton").click(function() {
		console.log("Clicked reset button");
		currentNumber = 0;
		numColor();
		isDivisible();
		hasSeven();
		isZero();
		$("#numBox").val(currentNumber);
	});
  
	$("#setIncDecButton").click(function() {
		console.log("Clicked setIncDec button");
		incdecVal = Number($("#incdecValBox").val());
	});
	
	$("#randButton").click(function() {
	console.log("Clicked randomize button");
	randomize();
	incdecVal = randomNum;
	$("#incdecValBox").val(incdecVal);
	});
	
	function numColor() {
		if (currentNumber > numThreshold) {
			$("#numBox").addClass("isRed");
		} else {
			$("#numBox").removeClass("isRed");
		}
	}
 
	function isDivisible() {
		if (currentNumber % 2 === 0) {
			$("#numBox").addClass("isItalic");
		} else {
			$("#numBox").removeClass("isItalic");
		}		
	}
	
	function hasSeven() {
		var sevenCheck = currentNumber.toString();
		var check = sevenCheck.search("7");	
		if (!(check === -1)) {
			$("#numBox").addClass("isGreen");
		} else {
			$("#numBox").removeClass("isGreen");
		}
	}
	
	function randomize() {
		randomNum = Math.floor(Math.random() * 21) - 10;
		console.log(randomNum);
	}
	
	function isZero() {
		if (currentNumber === 0) {
			$("#zeroInd").text("The number is 0");
		} else {
			$("#zeroInd").text(" ");
		}
	}
});