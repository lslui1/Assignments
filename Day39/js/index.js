var app = angular.module("AppMod", ["ngRoute"]);

// Start config
app.config(['$routeProvider', function($routeProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'views/home.view.html'
		})
		.when('/about', {
			templateUrl: 'views/about.view.html'
		})
		.when('/studentlist', {
			templateUrl: 'views/studentlist.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'
		})
		.when('/updatestudent', {
			templateUrl: 'views/updatestudent.html',
		})
		.when('/addstudent', {
			templateUrl: 'views/addstudent.html',
		})
		.otherwise({redirectTo: 'views/home.view.html'});

}]); // end config


app.controller('AppCtrl', function($scope, $http, $location) {
    $http({
        method : "GET",
        url : "http://localhost:8080/students"
    }).then(function mySucces(response) {
		$scope.mydata = response.data;	
    }, function myError(response) {
        $scope.mydata = response.statusText;
    });
	
	var self = this;
		
	self.editStudent = function(student) {
		sessionStorage.setItem("globalId", student.id);
		console.log("global Id is set to: " + sessionStorage.globalId);
		$location.path('/updatestudent');
	};
	
	self.deleteItem = function(student) {
		console.log(student);
		$scope.deleteStudent(student);
	};
	
	self.refresh = function() {
		location.reload();
	};

	$scope.deleteStudent = function (student) {
          /* the $http service allows you to make arbitrary ajax requests.
           * in this case you might also consider using angular-resource and setting up a
           * User $resource. */
		var urlDelete = "http://localhost:8080/deletestudent/" + student.id;
		console.log(urlDelete);
        $http({
			method : "GET",
			url : urlDelete
			})
			.then(function mySucces(response) {
				location.reload();}, 
			function myError(response) {
				console.log(response.statusText);
			}); 
    };
}); // End AppCtrl Controller

// Start Update Form Controller
app.controller('formCtrl', function($scope, $http) {
	var self = this;
	$scope.onlyNumbers = /^\d+$/;
	$scope.onlyAlpha = /^[a-zA-Z\s]*$/;
	
	if (sessionStorage.globalId > 0) {
		var urlget = "http://localhost:8080/student/" + sessionStorage.globalId;
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
		});
		sessionStorage.globalId = 0;
		$("#pageTitle").text(" Update Student Menu ");
	} else {
		$("#pageTitle").text(" Search Student Menu ");
	};
	
	self.getStudent = function() {
		$scope.getStudent();
	};
	
	self.updateStudent = function() {
		$scope.updateStudent();
	};
	
	// Begin getStudent
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
	
	// Begin updateStudent
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
	
}); // End Update Form Controller


// Start Add Form Controller
app.controller('addformCtrl', function($scope, $http) {
	var self = this;
	$scope.onlyNumbers = /^\d+$/;
	$scope.onlyAlpha = /^[a-zA-Z\s]*$/;
	
	self.addStudent = function() {
		$scope.addStudent();
	}
	
	// Begin addStudent
	$scope.addStudent = function () {
	var urladd = "http://localhost:8080/addstudent/" + 
					$scope.afirst_name + "/" +
					$scope.alast_name + "/" +
					$scope.asat + "/" +
					$scope.agpa + "/" +
					$scope.amajor_id ;
	
	console.log(urladd);
    $http({
		method : "GET",
		url : urladd
		})
		.then(function mySuccess(result) {
			console.log(result);
		}, 
		function myError(result) {
			console.log(result.statusText);
		})
		; 
    }; // End addStudent

}); // End Add Form Controller

