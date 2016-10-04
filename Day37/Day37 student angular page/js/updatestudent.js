var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope, $http) {
	var self = this;
	$scope.onlyNumbers = /^\d+$/;
	$scope.onlyAlpha = /^[a-zA-Z\s]*$/;
	
// Initial test data	
	$scope.data = {"id":100,"first_name":"Eric","last_name":"Ephram","sat":1200,"gpa":3.0,"major_id":1};
	$scope.aid = $scope.data.id;
	$scope.afirst_name = $scope.data.first_name;
	$scope.alast_name = $scope.data.last_name;
	$scope.asat = $scope.data.sat;
	$scope.agpa = $scope.data.gpa;
	$scope.amajor_id = $scope.data.major_id;
// end initial test data
	
	self.getStudent = function() {
		$scope.getStudent();
	};
	
	self.updateStudent = function() {
		$scope.updateStudent();
	};
	
	$scope.getStudent = function () {
	var urlget = "http://localhost:8080/student/" + $scope.aid;
	console.log(urlget);
    $http({
		method : "GET",
		url : urlget
		})
		.then(function mySuccess(result) {
			console.log(result);
			$scope.aid = result.data.id;
			$scope.afirst_name = result.data.first_name;
			$scope.alast_name = result.data.last_name;
			$scope.asat = result.data.sat;
			$scope.agpa = result.data.gpa;
			$scope.amajor_id = result.data.major_id;
		}, 
		function myError(result) {
			console.log(result.statusText);
		})
		; 
    }; // End getStudent
	
	
	$scope.updateStudent = function () {
	var urlupdate = "http://localhost:8080/updatestudent/" + 
					$scope.aid + "/" +
					$scope.afirst_name + "/" +
					$scope.alast_name + "/" +
					$scope.asat + "/" +
					$scope.agpa + "/" +
					$scope.amajor_id ;
	
	console.log(urlupdate);
    $http({
		method : "GET",
		url : urlupdate
		})
		.then(function mySuccess(result) {
			console.log(result);
		}, 
		function myError(result) {
			console.log(result.statusText);
		})
		; 
    }; // End updateStudent
	
	
});