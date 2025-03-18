app.controller("atosDataPrePrivacyController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,$index){
	var contextPath = $window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));
	$scope.isSubmitDisabled = true;
	$scope.isSelectedCheck = function(selected){
		if(selected==true){
			$scope.isSubmitDisabled = false;		
		}
		else{
			$scope.isSubmitDisabled = true;
		}
	}
	
	$scope.authenticate = function(){
		var getCategorydetail = function(response) {
			window.location = "MyDashBoard";    
			};
		WebServiceFactory.setSubmitEnabled().then(getCategorydetail);
		
	}
	

}]);  