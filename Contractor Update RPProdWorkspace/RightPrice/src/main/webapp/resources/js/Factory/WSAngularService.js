app.service('WebAPIService', function(WebServiceFactory) {


this.btnRight = function($scope){
		angular.forEach($scope.selectedAvailItems, function (value, key) {
	        this.push(value);
	    }, $scope.selectedItemList);
	      angular.forEach($scope.selectedAvailItems, function (value, key) {
	        for (var i = $scope.customes.length - 1; i >= 0; i--) {
	            if ($scope.customes[i] == value) {
	            	$scope.customes.splice(i, 1);
	            }
	        }
	    });
	    $scope.selectedAvailItems = [];
	    console.log("AvailItems....................");
	    console.log($scope.selectedItemList);
	};
	
	this.btnLeft = function($scope){
       	angular.forEach($scope.selectedSelectedItemList, function (value, key) {
			var index = ($scope.selectedItemList.indexOf($scope.selectedSelectedItemList[key]));
			if(index >= 0){
				$scope.remove(index);
			}
       		this.push(value);
        }, $scope.customes);
         angular.forEach($scope.selectedSelectedItemList, function (value, key) {
            for (var i = $scope.selectedItemList.length - 1; i >= 0; i--) {
                if ($scope.selectedItemList[i] == value) {
                    $scope.selectedItemList.splice(i, 1);
                }
            }
        });
        $scope.selectedSelectedItemList = []; 
	};
	
	
	//service to remove item on click delete
	this.remove = function(id,$scope){
		var splicedItem =  $scope.selectedItemList.splice(id, 1);
		angular.forEach($scope.customes, function (value, key) {
			if($scope.customes[key].customer.customerId == splicedItem[0].customerId ){
				$scope.customes.splice(key, 1);
			}
		});
	};
});