app.controller("PreSalesViewController",['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$index){
			$(window).load(function(){
			     $('.loader').fadeOut();
			});
			
			$scope.numberRegex = /^(0|[1-9]\d*)(\.\d+)?$/;
			var contextPath = "/RightPrice-DAS";
			$scope.roleUpdateDetails = [];
			 $scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.onSave = false;
			 $scope.onUpdate = false;
			 $scope.onSearch = false;
			 $scope.onViewSearch = false;
			 $scope.downloadBtn = true;
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
                /*$scope.onSearch = false;
				$scope.viewMasterRoleForm.viewPracticeModel=null;
				$scope.viewMasterRoleForm.viewSubPracticeModel=null;*/
            };
           
            $scope.getPreSalesData = function(){
            	var getPreSalesData = function(response) {
            		console.log(response.data);
					$scope.PreSalesViewRequestData = response.data;
				};
				WebServiceFactory.getPreSalesData().then(getPreSalesData);
            };
			
			
            $scope.viewRequest = function(row) {
            	$sessionStorage.salesId = row.salesId;
            	window.location = "PreSalesUpdate";
            };
            
            $scope.updateRequest = function(row) {
            	$sessionStorage.salesId = row.salesId;
            	window.location = "PreSalesUpdate";
            }
            
            $scope.exportToExcel = function(tableId) {
        		
        		var exportHref=WebServiceFactory.downloadExcel(tableId,'PreSales_Draft_Report');
        	  
        	};
           
}]);