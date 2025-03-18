//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("MasterCustomerUpdateController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 console.log("inside MasterCustomerUpdateController");
		 var contextPath = "/RightPrice-DAS";	
		 $scope.isAddDisabled = false;
		 $scope.isFirstTimePageLoaded = true;	
		 $scope.numberRegex = /^(0|[1-9]\d{0,1})(\.\d{1,2})?$/;
		 $scope.onUpdate = false;
		 $scope.onshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
		 $scope.offshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
		 $scope.isViewRequest=false;  
     	 $scope.isActive=1;
     	 
		 $scope.clicked = function(){
			    window.location="MasterCustomerUpdate";
			}; 
			
	        	var getCustomer = function(response){
	        		console.log("Inside customer table");
					$scope.customer = response.data;
					console.log(response.data);
				};
				WebServiceFactory.getCustomer().then(getCustomer);
				
				$scope.putCustomerDetails = function(customerDetails){
					 
					  
	            		for(var i=0;i< $scope.onshoreHours.length;i++) {
	            			if(customerDetails.onsiteWorkHours == $scope.onshoreHours[i].name) {
	            				$scope.onsiteWorkHours = $scope.onshoreHours[i].hourId;
	            			}
	            		}
	            		
	            		for(var i=0;i< $scope.offshoreHours.length;i++) {
	            			if(customerDetails.offshoreWorkHours == $scope.offshoreHours[i].name) {
	            				$scope.offshoreWorkHours = $scope.offshoreHours[i].hourId;
	            			}
	            		}
					
					$scope.MasterCustomer.updateonsiteWorkHoursmodel = $scope.onsiteWorkHours;
					$scope.MasterCustomer.updateoffshoreWorkHoursmodel =$scope.offshoreWorkHours;
					$scope.MasterCustomer.updatecpcChargesmodel = customerDetails.cpcCharges;
					$scope.MasterCustomer.updatecpcChargesmodelFP = customerDetails.cpcChargesFP;
					$scope.MasterCustomer.updateipcChargesmodel = customerDetails.ipcCharges;
					$scope.MasterCustomer.updatevolumeDiscountmodel = customerDetails.volumeDiscount;
					};
					
				$scope.onUpdateClick = function(MasterCustomer) {
						$scope.onUpdate = true;
						if(MasterCustomer.$valid){
							$scope.onUpdate = false;
						$scope.updateCustomer();
						}
					};
					
				$scope.updateCustomer = function(){
						 var onsiteWorkHours = $.grep($scope.onshoreHours, function (oi) {
		            	                       return oi.hourId == $scope.MasterCustomer.updateonsiteWorkHoursmodel;
		            	                   })[0].name;
		               
						 var offshoreWorkHours = $.grep($scope.offshoreHours, function (offshoreHour) {
		            	                       return offshoreHour.hourId == $scope.MasterCustomer.updateoffshoreWorkHoursmodel;
		            	                   })[0].name;
						var markers = {
								
								"customerName":$scope.MasterCustomer.customer.customerName,
								"onsiteWorkHours":onsiteWorkHours,
							
								"offshoreWorkHours":offshoreWorkHours,
								"cpcCharges":$scope.MasterCustomer.updatecpcChargesmodel,
								"ipcCharges":$scope.MasterCustomer.updateipcChargesmodel,
								"volumeDiscount":$scope.MasterCustomer.updatevolumeDiscountmodel,		
								"cpcChargesFP":$scope.MasterCustomer.updatecpcChargesmodelFP
						}
						console.log(markers);
						var updateCustomer = function(response) {
				   	        BootstrapDialog.show({
				   	        	title : 'Master Customer',
				   	        	type : BootstrapDialog.TYPE_PRIMARY,
				   	        	message : 'Updated SucessFully',
				   	        	closable : false,
				   	        	buttons : [{
				   	        		label : 'OK',
				   	        		action : function(dialogRef) {
				   	        			dialogRef.close();
				   	        			window.location = "MasterCustomerUpdate";
				   	        		}
				   	        	}]
				   	        });
						}
					 WebServiceFactory.updateCustomer(markers).then(updateCustomer);
				};	
				
				$scope.cancelClickOnUpdate = function(MasterCustomer) {
					$scope.onUpdate = false;
					$scope.MasterCustomer.customer=null,
					$scope.MasterCustomer.updateonsiteWorkHoursmodel = null;
					$scope.MasterCustomer.updateoffshoreWorkHoursmodel = null;
					$scope.MasterCustomer.updatecpcChargesmodel = "";
					$scope.MasterCustomer.updateipcChargesmodel = "";
					$scope.MasterCustomer.updatevolumeDiscountmodel = "";
					$scope.MasterCustomer.updatecpcChargesmodelFP = "";
				
				};
					
	}]);