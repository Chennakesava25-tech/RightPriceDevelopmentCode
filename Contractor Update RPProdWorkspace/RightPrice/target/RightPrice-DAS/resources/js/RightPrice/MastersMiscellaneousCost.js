//var app = angular.module('MiscellaneousCostApp', ['ngMessages']);
	app.controller("MiscellaneousCostController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index){
			//$scope.updateCityForm={};
			$scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.downloadBtn = true;
			 
			 var contextPath = "/RightPrice-DAS";
            $scope.ShowHideView = function () {
               $scope.ViewHidden = $scope.ViewHidden ? false : true ;
           };
            $scope.ShowHideAdd = function () {
               $scope.AddHidden = $scope.AddHidden ? false : true;
           };
            $scope.ShowHideUpdate = function () {
               $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
           };
            $scope.ShowHideUpload = function () {
               $scope.UploadHidden = $scope.UploadHidden ? false : true;
           };
		    $scope.moveTop = function()
			{
				$location.hash('PageHeading'); 
				$anchorScroll();
			};
			$scope.moveBottom = function()
			{
				$location.hash('includedFooter'); 
				$anchorScroll();
			};   
			
			
			$scope.onSearch= false;	
			$scope.searchData = function(searchMiscellaneousCostForm){
				$scope.onSearch= true;
				if(searchMiscellaneousCostForm.$valid){
					$scope.onSearch= false;	
					/*alert("validated search");*/
					$scope.viewMiscellaneousCost();
				}
			};
			
			$scope.onSave= false;	
			$scope.onUpdate=false;
			$scope.updateData = function(updateMiscellaneousCostForm){
				$scope.onUpdate= true;
				if(updateMiscellaneousCostForm.$valid){
					$scope.onUpdate= false;
					$scope.updateMiscellaneousCost();
				}
			}
			
			$scope.viewMiscellaneousCost=function(){
				$scope.downloadBtn = false;
				var countryId=1;
				var cityId=$scope.searchMiscellaneousCostForm.ViewCityModel;
						console.log("country id"+countryId);
						console.log("city id"+cityId);
						console.log('merkers');
						var markers = {
							"countryId" : countryId,
							"cityId" : cityId
				} ;
				
				console.log(markers);
					var viewMiscellaneousCost = function(response) {	
						console.log(response.data);
				    	$scope.viewMiscellaneousCostData = response.data;
				    	for(i=0;i<response.data.length;i++)
						{
							var num = parseFloat(response.data[i].nightShiftCost);						    
						    var nightShiftCost = num.toFixed(2);
						    $scope.viewMiscellaneousCostData[i].nightShiftCost = nightShiftCost;
						    
						    num = parseFloat(response.data[i].nightShiftTransportCost);						    
						    var nightShiftTransportCost = num.toFixed(2);
						    $scope.viewMiscellaneousCostData[i].nightShiftTransportCost = nightShiftTransportCost;
						    
						    num = parseFloat(response.data[i].secondShiftCost);						    
						    var secondShiftCost = num.toFixed(2);
						    $scope.viewMiscellaneousCostData[i].secondShiftCost = secondShiftCost;
						    
						    num = parseFloat(response.data[i].secondShiftTransportCost);						    
						    var secondShiftTransportCost = num.toFixed(2);
						    $scope.viewMiscellaneousCostData[i].secondShiftTransportCost = secondShiftTransportCost;
						    
						}
			    	
				    	console.log($scope.viewMiscellaneousCostData.length);
				    	 if(($scope.viewMiscellaneousCostData.length) < 1){
				    		 $scope.downloadBtn = true;
							  BootstrapDialog.show({
										title : 'Shift Cost Details',
										type : BootstrapDialog.TYPE_DANGER,
										message : 'No Record found.',
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(dialogRef) {
												dialogRef.close();
											}
										} ]
									});
							  }
					}
					WebServiceFactory.viewMiscellaneousCost(markers).then(viewMiscellaneousCost);
				};	
			
/*				$scope.addMiscellaneousCost = function(){
					
					var countryId = $scope.addMiscellaneousCostForm.AddCountryModel.countryId;
					var cityId = $scope.addMiscellaneousCostForm.AddCityModel.cityId;
					var nightShiftCost = $scope.addMiscellaneousCostForm.AddNightShiftCost;
					var secondShiftCost =$scope.addMiscellaneousCostForm.AddSecondShiftCost;
					var nightShiftTransportCost = $scope.addMiscellaneousCostForm.AddNightShiftTransportCost;
					var secondShiftTransportCost = $scope.addMiscellaneousCostForm.AddSecondShiftTransportCost;
					
					var marker = {
							"countryId": countryId,
							"cityId": cityId,
							"nightShiftCost": nightShiftCost,
							"secondShiftCost": secondShiftCost,
							"nightShiftTransportCost":nightShiftTransportCost,
							"secondShiftTransportCost":secondShiftTransportCost
					};
		    	        var addMiscellaneousCost = function(response) { 
		    	        	
		    				var customMessage = response.data;
		    				
		    	        	if(response.status == 200){	
				    	        BootstrapDialog.show({
				    	        	title : 'Shift Cost Details',
				    	        	type : BootstrapDialog.TYPE_PRIMARY,
				    	        	message : 'Data Saved SucessFully',
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
				    	        			window.location = "MastersMiscellaneousCost";
				    	        		}
				    	        	}]
				    	        });
		    	        	}
		    	        	else if(response.status == 203){	
				    	        BootstrapDialog.show({
				    	        	title : 'Shift Cost Details',
				    	        	type : BootstrapDialog.TYPE_PRIMARY,
				    	        	message : customMessage,
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
				    	        			window.location = "MastersMiscellaneousCost";
				    	        		}
				    	        	}]
				    	        });
		    	        	}
		    	        	else 
		    	        	{
								BootstrapDialog.show({
								title : 'Shift Cost Details',
								type : BootstrapDialog.TYPE_DANGER,
								message : "Currently We are facing technical issues, please try again later.",
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										window.location = "MastersMiscellaneousCost";
									}
								} ]
								});
		    	        	}
		    	        	
		    	        }    
			    	       
		    	        WebServiceFactory.addMiscellaneousCost(marker).then(addMiscellaneousCost);
					}*/
					$scope.getUpdateMiscellaneousCost = function(cityId){
						if(cityId != undefined){
							var getUpdateMiscellaneousCost = function(response) {
								console.log("Miscellaneous Cost");
								console.log(response);
								console.log(response.data);
								$scope.miscellaneousCost = response.data;
								if(response.status == 200){
								$scope.putmiscellaneousCost($scope.miscellaneousCost);
								}
								else{	
					    	        BootstrapDialog.show({
					    	        	title : 'Shift Cost Details',
					    	        	type : BootstrapDialog.TYPE_DANGER,
					    	        	message : 'No record Found',
					    	        	closable : false,
					    	        	buttons : [{
					    	        		label : 'OK',
					    	        		action : function(dialogRef) {
					    	        			dialogRef.close();
					    	        			window.location = "MastersMiscellaneousCost";
					    	        		}
					    	        	}]
					    	        });
			    	        	} 
						};
						
						WebServiceFactory.getUpdateMiscellaneousCost(cityId).then(getUpdateMiscellaneousCost);
					}
					
				};	
				$scope.putmiscellaneousCost=function(){
					$scope.updateMiscellaneousCostForm.UpdateNightShiftCost=$scope.miscellaneousCost[0].nightShiftCost;
					$scope.updateMiscellaneousCostForm.UpdateSecondShiftCost=$scope.miscellaneousCost[0].secondShiftCost	;
					$scope.updateMiscellaneousCostForm.UpdateNightShiftTransportCostModel=$scope.miscellaneousCost[0].nightShiftTransportCost;
					$scope.updateMiscellaneousCostForm.UpdateSecondShiftTransportCost=$scope.miscellaneousCost[0].secondShiftTransportCost;
					if($scope.updateMiscellaneousCostForm.UpdateCityModel.isActive == 0){
						$scope.updateMiscellaneousCostForm.IsActive = false;
					}
					else{
						$scope.updateMiscellaneousCostForm.IsActive = true ;
					}
				}
				
				$scope.updateMiscellaneousCost = function(){
					console.log($scope.miscellaneousCost);
					console.log($scope.miscellaneousCost[0].miscellaniousCostId);
					var cityId = $scope.updateMiscellaneousCostForm.UpdateCityModel;
					var nightShiftCost = $scope.updateMiscellaneousCostForm.UpdateNightShiftCost;
					var secondShiftCost =$scope.updateMiscellaneousCostForm.UpdateSecondShiftCost;
					var nightShiftTransportCost = $scope.updateMiscellaneousCostForm.UpdateNightShiftTransportCostModel;
					var secondShiftTransportCost = $scope.updateMiscellaneousCostForm.UpdateSecondShiftTransportCost;
					var isActive = $scope.updateMiscellaneousCostForm.IsActive
					if(isActive == true){
						isActive = 1;
					}else{
						isActive = 0;
					}
					
					var marker = {
									"cityId": cityId,
									"nightShiftCost": nightShiftCost,
									"secondShiftCost": secondShiftCost,
									"nightShiftTransportCost":nightShiftTransportCost,
									"secondShiftTransportCost":secondShiftTransportCost,
									"isActive":isActive,
									"miscellaniousCostId":$scope.miscellaneousCost[0].miscellaniousCostId
									
								};
								var updateMiscellaneousCost = function(response) {
				   	        BootstrapDialog.show({
				   	        	title : 'Shift Cost Details',
				   	        	type : BootstrapDialog.TYPE_PRIMARY,
				   	        	message : 'Shift Cost Cost Updated SucessFully',
				   	        	closable : false,
				   	        	buttons : [{
				   	        		label : 'OK',
				   	        		action : function(dialogRef) {
				   	        			dialogRef.close();
				   	        			window.location = "MastersMiscellaneousCost";
				   	        		}
				   	        	}]
				   	        });
				   	        
						}
						WebServiceFactory.updateMiscellaneousCost(marker).then(updateMiscellaneousCost);
					};
			$scope.cancel = function(){
				$scope.onSave= false;
				$scope.onUpdate=false;
				$scope.addMiscellaneousCostForm.AddCountryModel=null;
				$scope.addMiscellaneousCostForm.AddCityModel=null;
				$scope.addMiscellaneousCostForm.AddNightShiftCost=null
				$scope.addMiscellaneousCostForm.AddSecondShiftCost=null;
				$scope.addMiscellaneousCostForm.AddNightShiftTransportCost=null;
				$scope.addMiscellaneousCostForm.AddSecondShiftTransportCost=null;
				$scope.updateMiscellaneousCostForm.UpdateCountryModel=null;
				$scope.updateMiscellaneousCostForm.UpdateCityModel=null;
				$scope.updateMiscellaneousCostForm.UpdateNightShiftCost=null
				$scope.updateMiscellaneousCostForm.UpdateSecondShiftCost=null;
				$scope.updateMiscellaneousCostForm.UpdateNightShiftTransportCostModel=null;
				$scope.updateMiscellaneousCostForm.UpdateSecondShiftTransportCost=null;
				$scope.updateMiscellaneousCostForm.IsActive=null;
			};
}]);