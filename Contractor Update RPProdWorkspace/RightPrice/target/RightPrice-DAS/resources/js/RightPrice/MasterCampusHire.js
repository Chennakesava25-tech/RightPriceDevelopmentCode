//var app = angular.module('RightPriceApp', ['ngMessages']);
app.controller("MasterCampusHireController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index) {
	var contextPath = "/RightPrice-DAS";
	$scope.onSave = false;
	 $scope.mastercampushire = {};
	 $scope.numberRegex = /^(0|[1-9]\d*)(\.\d{1,2})?$/;
	 
		$scope.onSaveClick = function(frmMasterID) {
			$scope.onSave = true;
			if(frmMasterID.$valid){
				$scope.onSave= false;
				$scope.updateThresholdPercent();
			}
		};
		
		$scope.onCancelClick = function(frmMasterID) 
		{
			window.location = "/RightPrice-DAS/welcome";			
		};
		
	 var getCampusHireThresholdPercent = function(response) {
			$scope.campushire = response.data;
			$scope.mastercampushire.development = $scope.campushire[0].thresholdPercent;
			$scope.mastercampushire.maintenance = $scope.campushire[1].thresholdPercent;
			$scope.mastercampushire.timeandmeterial = $scope.campushire[2].thresholdPercent;
		};
		WebServiceFactory.getCampusHireThresholdPercent().then(getCampusHireThresholdPercent);
		 
		$scope.updateThresholdPercent = function(){
			if($scope.mastercampushire.development == $scope.campushire[0].thresholdPercent && $scope.mastercampushire.maintenance == $scope.campushire[1].thresholdPercent && $scope.mastercampushire.timeandmeterial == $scope.campushire[2].thresholdPercent){
				BootstrapDialog.show({
	   	        	title : 'Campus Hire',
	   	        	type : BootstrapDialog.TYPE_DANGER,
	   	        	message : 'Please Update the Data',
	   	        	closable : false,
	   	        	buttons : [{
	   	        		label : 'OK',
	   	        		action : function(dialogRef) {
	   	        			dialogRef.close();
	   	        			window.location = "MasterCampusHire";
	   	        		}
	   	        	}]
	   	        });
			}else{
					var markers = [
							{
								"dealType" : "Fixed Price - Development",
								"thresholdPercent" : $scope.mastercampushire.development,
							},
							{
								"dealType" : "Fixed Price - Maintenance",
								"thresholdPercent" : $scope.mastercampushire.maintenance,
							},
							{
								"dealType" : "Time & Material",
								"thresholdPercent" : $scope.mastercampushire.timeandmeterial,
							} ];
				
				var updateThresholdPercent = function(response) {
		   	        BootstrapDialog.show({
		   	        	title : 'Campus Hire',
		   	        	type : BootstrapDialog.TYPE_PRIMARY,
		   	        	message : 'Data Updated SucessFully',
		   	        	closable : false,
		   	        	buttons : [{
		   	        		label : 'OK',
		   	        		action : function(dialogRef) {
		   	        			dialogRef.close();
		   	        			window.location = "MasterCampusHire";
		   	        		}
		   	        	}]
		   	        });
		   	   }
				WebServiceFactory.updateThresholdPercent(markers).then(updateThresholdPercent);
			}
		};
	
		}]);