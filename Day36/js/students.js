
var app = angular.module('MyApp', []);
app.controller('myCtrl', function($scope, $http) {
    $http({
        method : "GET",
        url : "http://localhost:8080/students"
    }).then(function mySucces(response) {
        
		for( var i in response.data ) {
                   response.data[i].vis = true;
		}
		$scope.mydata = response.data;
		
    }, function myError(response) {
        $scope.mydata = response.statusText;
    });
	
	$scope.visible = true;
	
	var self = this;
		
	self.editItem = function(student) {
		console.log(student);
	};
	
	self.deleteItem = function(student) {
		console.log(student);
		$scope.deleteStudent(student);
	};
	
	self.hideRow = function(student) {
		student.vis = false;
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
			})
			; 
    };
		
});











