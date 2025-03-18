//var app = angular.module('RightPriceApp', ['ngMessages']);
              app.controller("FPDealCreationContractualTermsController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,$index) {
            	  
            	 

							$scope.moveTop = function() {
								$location.hash('PageHeading');
								$anchorScroll();
							};
							$scope.moveBottom = function() {
								$location.hash('includedFooter');
								$anchorScroll();
							};
							$scope.Prev = function() {
								window.location = 'FPDealCreationRateCardAndProjectDetails';
							};
							$scope.Next = function() {
								window.location = 'FPDealCreationApplicationDetails     ';
							};
							
							
							$scope.onSave = false;
							$scope.contractualterms = {};
							$scope.isChargeablePercentDisable = false;
							$scope.isChargeablePercentDisable1 = false;
							$scope.numberRegex = /^(0|[1-9]\d*)(\.\d+)?$/;
							$scope.effortRegex = /^(0|[1-9]\d*)?$/;
							

								$scope.onSaveClick = function(frmDealId) {
								$scope.onSave = true;
								if (frmDealId.$valid) {
									$scope.onSave = false;
										$scope.saveContractualTerms();
								}
							};
							$scope.onCancelClick = function(frmDealId) {
								window.location = "FPDealCreationContractualTerms";
							};
							
							 var getDealDetails = function(response) {
							  		console.log(response);
							  		$scope.dealDetails = response.data;
							  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
							  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
							  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
							  		var date = new Date(dealEndDate.substring(0,10));
							  		console.log("End Date is......... " + date);
							  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
							  		$scope.dealDetails[0].dealEndDate = dateENd;
									var dealStartDate = $scope.dealDetails[0].dealStartDate;
									var date = new Date(dealStartDate.substring(0,10));
									var dateStart = $filter('date')(date,'dd/MM/yyyy');
									$scope.dealDetails[0].dealStartDate = dateStart;
									$scope.DealDuration = Math.abs(dateENd - dateStart);
							  		console.log($scope.dealDetails[0].percentageClose);
							  		console.log($scope.dealDetails[0].currencyId);
							  		console.log($scope.dealDetails[0].dealDuration);
							  		console.log($scope.dealDetails[0].stageId);
								};

							WebServiceFactory.getDealDetails().then(getDealDetails);
							
							    $scope.versionId = 22;
							var getContractualTerms = function(response){
								if(response.data){
									$scope.contractualterms = response.data; 
//									$scope.versionId = $scope.contractualterms[0].rpDealVersionId;
									$scope.contractualterms.contractualtermsquea1model = $scope.contractualterms[0].isTransitionToProvided;
									$scope.contractualterms.ContractualTermsQueB1Model = $scope.contractualterms[0].isWarrantyProvided;
									$scope.contractualterms.contractualtermsquea2model = $scope.contractualterms[0].isTransitionChargeable;
									$scope.contractualterms.contractualtermsqueb2model = $scope.contractualterms[0].isWarrantyChargeable;
									$scope.checkFlag();
									$scope.contractualterms.contractualtermsquea3model = $scope.contractualterms[0].chargeableTransitionPercent;
									$scope.contractualterms.contractualtermsqueb3model = $scope.contractualterms[0].chargeableWarrantyPercent;
									$scope.contractualterms.contractualtermsquea4model = $scope.contractualterms[0].totalTransitionEfforts;
									$scope.contractualterms.contractualtermsqueb4model = $scope.contractualterms[0].totalWarrantyEfforts;
								}
								};
								WebServiceFactory.getContractualTerms($scope.versionId).then(getContractualTerms);
							    
							$scope.checkFlag = function(action,action1) {
								$scope.action = $scope.contractualterms.contractualtermsquea2model;
								$scope.action1 = $scope.contractualterms.contractualtermsqueb2model;
								if ($scope.action == 1) {
									$scope.isChargeablePercentDisable = false;
								} else if ($scope.action == 0) {
									$scope.isChargeablePercentDisable = true;
									$scope.contractualterms.contractualtermsquea3model ="";
								}
								if ($scope.action1 == 1) {
									$scope.isChargeablePercentDisable1 = false;
								} else if ($scope.action1 == 0) {
									$scope.isChargeablePercentDisable1 = true;
									$scope.contractualterms.contractualtermsqueb3model ="";
								}
							};
							
							$scope.saveContractualTerms = function(){
									var markers = {
											"rpDealVersionId":$scope.versionId,
											"isTransitionToProvided":$scope.contractualterms.contractualtermsquea1model,
											"isTransitionChargeable":$scope.contractualterms.contractualtermsquea2model,
											"chargeableTransitionPercent":$scope.contractualterms.contractualtermsquea3model,
											"totalTransitionEfforts":$scope.contractualterms.contractualtermsquea4model,
											"isWarrantyProvided":$scope.contractualterms.ContractualTermsQueB1Model,
											"isWarrantyChargeable":$scope.contractualterms.contractualtermsqueb2model,
											"chargeableWarrantyPercent":$scope.contractualterms.contractualtermsqueb3model,
											"totalWarrantyEfforts":$scope.contractualterms.contractualtermsqueb4model	
									}
									console.log(markers);
									var saveContractualTerms = function(response) {
							   	        BootstrapDialog.show({
							   	        	title : 'Contractual Terms',
							   	        	type : BootstrapDialog.TYPE_PRIMARY,
							   	        	message : 'Saved SucessFully',
							   	        	closable : false,
							   	        	buttons : [{
							   	        		label : 'OK',
							   	        		action : function(dialogRef) {
							   	        			dialogRef.close();
							   	        			window.location = "FPDealCreationContractualTerms";
							   	        		}
							   	        	}]
							   	        });
									}
									WebServiceFactory.saveContractualTerms(markers).then(saveContractualTerms);
							};

						} ]);