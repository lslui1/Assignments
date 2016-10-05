var app = angular.module("AppMod", ["ngRoute"]);

// Start config
app.config(['$routeProvider', function($routeProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'views/home.view.html'
		})
		.when('/about', {
			template: "<h1>ABOUT TIY University</h1><p>There is nothing to know about TIYU. It doesn't actually exist. Go take a red pill.</p>"
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


app.controller('AppCtrl', function($scope, $http) {
    $http({
        method : "GET",
        url : "http://localhost:8080/students"
    }).then(function mySucces(response) {
		$scope.mydata = response.data;		
    }, function myError(response) {
        $scope.mydata = response.statusText;
    });
	
	var self = this;
		
	self.editItem = function(student) {
		console.log(student);
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
	
	/* Initial test data	
	$scope.data = {"id":100,"first_name":"Eric","last_name":"Ephram","sat":1200,"gpa":3.0,"major_id":1};
	$scope.aid = $scope.data.id;
	$scope.afirst_name = $scope.data.first_name;
	$scope.alast_name = $scope.data.last_name;
	$scope.asat = $scope.data.sat;
	$scope.agpa = $scope.data.gpa;
	$scope.amajor_id = $scope.data.major_id;
	   end initial test data */
	
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
	
	// Initial test data	
	$scope.data = {"id":100,"first_name":"Terry","last_name":"Merrick","sat":1200,"gpa":3.0,"major_id":1};
	$scope.afirst_name = $scope.data.first_name;
	$scope.alast_name = $scope.data.last_name;
	$scope.asat = $scope.data.sat;
	$scope.agpa = $scope.data.gpa;
	$scope.amajor_id = $scope.data.major_id;
	// end initial test data
	
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

