app.controller("PreSalesAddController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {		
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
			 $scope.salesId  = null; 
             $scope.ShowHideView = function () {
             $scope.ViewHidden = $scope.ViewHidden ? false : true ;
             $scope.preSalesPopupHide = true;
             $scope.btnSaveDisable = false;
             $scope.btnSubmitDisable = false;
            };
            
         /*   $scope.calculateBidBudget = function() {
            	$scope.preSalesPopupHide = false;
            	console.log("the function called");
            }*/
            
            $scope.calculateBidBudget = function () {
                console.log("clicked here...");
                var dlgElem = angular.element("#popupModal");
                if (dlgElem) {
                   dlgElem.modal("show");
                }
                $scope.ExpenseCost = [{ name: "Employee Cost", id: 1 }, { name: "Travel Cost", id: 2 }, { name: "Purchase of Software", id: 3 }, { name: "Consulting Service", id: 4 },{ name: "R&D Expense", id: 5 },{ name: "Others", id: 6 },{ name: "Others 1", id: 7},{ name: "Others 2", id: 8}];
                $scope.expcost = $scope.ExpenseCost;
             };
         	$window.onload = function() 
			{
         		 if($sessionStorage.id != null || $sessionStorage.id != undefined) {
            		 $scope.putOpportunityDealData($sessionStorage.id);
            		$scope.frmPreSales.opportunityId=parseInt($sessionStorage.id);
            	 }
			}
			 $scope.frmRateCard={};
			 
			 $scope.addGrandData = function() {
	            	$scope.frmPreSales.bnpsBidBudget=$scope.grandAmount;
	            }
			 
			 $scope.people = [
            	 ];

             $scope.addPerson = function(amount,grandAmount,expArray){
            	 if(grandAmount==undefined){
            		 grandAmount=0;
                 }
            	 
            	 angular.forEach(expArray, function(value,key) {
            		
            		 if(expArray[key].id == $scope.natureOfExps) {
            			 $scope.natureOfExps = expArray[key].name;
            		 }
            	 });
            	 var person = {
            		 natureOfExps: $scope.natureOfExps,
            		 expAmount: $scope.expAmount
            	 };
            	 $scope.people.push(person);
            	 amount=parseInt(amount,10);
            	 grandAmount=grandAmount+amount;
            	 $scope.grandAmount=grandAmount;
            };

            $scope.removePerson = function(index,amount,grandAmount){
            	 $scope.people.splice(index, 1);
            	 amount=parseInt(amount,10);
            	 grandAmount=grandAmount-amount;
            	 $scope.grandAmount=grandAmount;
            }; 
            
            var getOpportunityDetails = function(response) {
            	if(response.data !=null) {
            		$scope.opportunityArray = response.data;
            		console.log("The Deal Details");
            		console.log($scope.opportunityArray);
            	}
            }
        	WebServiceFactory.getOpportunityDetails().then(getOpportunityDetails);
        	
        	$scope.putOpportunityDealData = function(opportunityId) {
        		$scope.opportunityData = [];
        		//$scope.frmPreSales.$setPristine();
        		
        		$scope.clearData();
        		var foundExchangeRate = true;
        		var putOpportunityDealData = function(response) {
        			if(response.data != undefined) {
        				$scope.opportunityData = response.data;
        				console.log("The data");
        				console.log($scope.opportunityData);
        				$scope.dealData = $scope.opportunityData[0];
        				if($scope.dealData.existingAlready == false) {
        					$scope.salesId  = null; 
        					$scope.frmPreSales.opportunityDescription = $scope.dealData.dealDescription;
            				$scope.frmPreSales.dealType=$scope.dealData.dealTypeId;
            				if($scope.frmPreSales.dealType == 2) {
            					$scope.frmPreSales.dealType = "T&M"
            				} else if($scope.frmPreSales.dealType == 1) {
            					$scope.frmPreSales.dealType = "FP"
            				}
            				$scope.frmPreSales.dealStatus = $scope.dealData.dealStatusId
            				if($scope.frmPreSales.dealStatus == 0) {
            					$scope.frmPreSales.dealStatus = "Open";
            				} else if($scope.frmPreSales.dealStatus == 1) {
            					$scope.frmPreSales.dealStatus = "Won";
            				} else if($scope.frmPreSales.dealStatus ==2) {
            					$scope.frmPreSales.dealStatus = "Lost";
            				}
            				$scope.verticalId = $scope.dealData.verticalId;
            				$scope.frmPreSales.dealStartDate = $filter('date')(new Date($scope.dealData.dealStartDate.substring(0,10)),'dd/MM/yyyy');
            				$scope.frmPreSales.dealEndDate = $filter('date')(new Date($scope.dealData.dealEndDate2.substring(0,10)),'dd/MM/yyyy');
            				$scope.frmPreSales.customer = $scope.dealData.customer.customerName;
            				$scope.frmPreSales.financeCustomer = $scope.dealData.customer.financeName;
            				$scope.frmPreSales.irisCode = $scope.dealData.customer.irisCode;
            				$scope.frmPreSales.vertical =   $scope.dealData.verticalName;
            				$scope.frmPreSales.gbuModel  = $scope.dealData.customer.gbu;
            				var currencyId = $scope.dealData.currencyId;
            				angular.forEach($scope.currencyCodeArray, function(value,key) {
            					if($scope.currencyCodeArray[key].codeName ==  currencyId) {
            						$scope.currencyId = currencyId;
            						$scope.frmPreSales.currencyCode = $scope.currencyCodeArray[key].description;
            					}
            				});
            				
            				if(foundExchangeRate) {
            					angular.forEach($scope.country , function(value,key) {
            						if($scope.country[key].currencyId == currencyId) {
            							$scope.dealExchangeRate = $scope.country[key].exchangeRate;
            							foundExchangeRate = false;
            						}
            					});
            					
            					console.log("The Deal Exchange Rate");
            					console.log($scope.dealExchangeRate);
            				}
            				$scope.frmPreSales.totalTCV = $scope.dealData.revenue;
            				$scope.frmPreSales.totalTCV = $scope.dealData.revenue;
            				
            				$scope.btnSaveDisable = false;
        					$scope.btnSubmitDisable = false;
            				
            				$scope.frmPreSales.bnpsBPSTCVEuro = "";
            				
            				/*angular.forEach($scope.opportunityArray, function(value,key) {
            					if(opportunityId == value.crmDealId) {
            						$scope.frmPreSales.opportunityId = key;
            					}
            				});*/
        				} else {
        					$scope.salesId  = $scope.dealData.salesId;
        					$scope.frmPreSales.opportunityDescription = $scope.dealData.opportunityDescription;
            				$scope.frmPreSales.dealType=$scope.dealData.dealType;
            				if($scope.frmPreSales.dealType == 2) {
            					$scope.frmPreSales.dealType = "T&M"
            				} else if($scope.frmPreSales.dealType == 1) {
            					$scope.frmPreSales.dealType = "FP"
            				}
            				$scope.frmPreSales.dealStatus = $scope.dealData.dealStatus
            				if($scope.frmPreSales.dealStatus == 0) {
            					$scope.frmPreSales.dealStatus = "Open";
            				} else if($scope.frmPreSales.dealStatus == 1) {
            					$scope.frmPreSales.dealStatus = "Won";
            				} else if($scope.frmPreSales.dealStatus ==2) {
            					$scope.frmPreSales.dealStatus = "Lost";
            				}
            				
            				$scope.frmPreSales.dealStartDate =$scope.dealData.dealStartDate;
            				$scope.frmPreSales.dealEndDate = $scope.dealData.dealEndDate;
            				$scope.frmPreSales.customer = $scope.dealData.customerName;
            				$scope.frmPreSales.financeCustomer = $scope.dealData.financeCustomer;
            				$scope.frmPreSales.irisCode = $scope.dealData.iRISCode;
            				$scope.frmPreSales.vertical =   $scope.dealData.vertical;
            				$scope.frmPreSales.gbuModel  = $scope.dealData.gBU;
            				$scope.frmPreSales.currencyCode = $scope.dealData.dealCurrencyCode;
            				$scope.frmPreSales.preSalesWBSNumber = $scope.dealData.presalesWBSNumber;
            				$scope.verticalId = $scope.dealData.verticalId;
            				var currencyId =  $scope.dealData.dealCurrencyCode;
            				angular.forEach($scope.currencyCodeArray, function(value,key) {
            					if($scope.currencyCodeArray[key].codeName ==  currencyId) {
            						$scope.currencyId = currencyId;
            						$scope.frmPreSales.currencyCode = $scope.currencyCodeArray[key].description;
            					}
            				});
            				
            				if(foundExchangeRate) {
            					angular.forEach($scope.country , function(value,key) {
            						if($scope.country[key].currencyId == currencyId) {
            							$scope.dealExchangeRate = $scope.country[key].exchangeRate;
            							foundExchangeRate = false;
            						}
            					});
            					
            					console.log("The Deal Exchange Rate");
            					console.log($scope.dealExchangeRate);
            				}
            				$scope.frmPreSales.totalTCV = $scope.dealData.totalTCV;
            				$scope.frmPreSales.totalApprovedBudget = $scope.dealData.totalApprovedBudget;
            				$scope.frmPreSales.bnpsBidBudget = $scope.dealData.bPSBIDBudget;
            				$scope.frmPreSales.totalBidBudget = $scope.dealData.totalBIDBudget;
            				$scope.frmPreSales.bnpsTCV= $scope.dealData.bPSTCV;
            				$scope.frmPreSales.bnpsBudgetEuro = $scope.dealData.bPSBIDBudgetINEuro;
            				$scope.frmPreSales.bnpsBPSTCVEuro = $scope.dealData.bPSTCVINEuro;
            				$scope.frmPreSales.bnpsBudgetEuro = $scope.dealData.bPSBIDBudgetINEuro;
            				$scope.frmPreSales.percentOfBpsTCV =  $scope.dealData.percentageofBPSTCVonTotalTCV;
            				$scope.frmPreSales.preSalesCostWBS = $scope.dealData.preSalesCostAgainstWBS;
            				$scope.frmPreSales.preSalesCostinEuro= $scope.dealData.preSalesCostAgainstWBSInEuro;
            				$scope.frmPreSales.balance = $scope.dealData.balance;
            				$scope.frmPreSales.balanceInEuro = $scope.dealData.balanceInEuro;
            				$scope.frmPreSales.currentApproverCommentModel = $scope.dealData.comments;
            				/*$scope.frmPreSales.totalTCV = $scope.dealData.revenue;*/
            				$scope.frmPreSales.startdate =$scope.dealData.bidStartDate
            				$scope.frmPreSales.Enddate = $scope.dealData.bidEndDate
            				
            				if($scope.dealData.status ==2) {
            					$scope.btnSaveDisable = true;
            					$scope.btnSubmitDisable = true;
            				}
            				
            				/*angular.forEach($scope.opportunityArray, function(value,key) {
            					if(opportunityId == value.crmDealId) {
            						$scope.frmPreSales.opportunityId = key;
            					}
            				});*/
            				
        				}
        			
        			}
        		}
        		WebServiceFactory.putOpportunityDealData(opportunityId).then(putOpportunityDealData);
        	};
        	
        	
        	var getCurrencyCode = function(response) {
        		if(response.data != undefined) {
        			$scope.currencyCodeArray = response.data;	
        			console.log("currency");
        			console.log($scope.currencyCodeArray);
        		}
        	}
        	WebServiceFactory.getCurrencyCode().then(getCurrencyCode);
        	
        	var getCountryDetail = function(response) {
        		var getEurCurrency = true;
        		if(response.data !=undefined) {
        			$scope.country = response.data;	
        			console.log("Country");
        			console.log($scope.country);
        			angular.forEach($scope.country,function(value,key) {
        				if(getEurCurrency) {
        					if($scope.country[key].currencyCode == 'EUR') {
        						$scope.euroExchangeRate = $scope.country[key].exchangeRate;
        						getEurCurrency = false;
        					}
        				}
        			});
        			console.log("exhange rate")
					console.log($scope.euroExchangeRate);
        		}
        	}
        	WebServiceFactory.getCountryDetail().then(getCountryDetail);
        	
        	$scope.calculateEuroTCV = function(bidValue) {
        		var calculatedVal = 0;
        		console.log("The Bid Value is.........");
        		console.log(bidValue);
        		var calculatedExchangeRate = $scope.euroExchangeRate/$scope.dealExchangeRate;
        		console.log("The consolidated Exchange");
        		console.log(calculatedExchangeRate);
        		calculatedVal = bidValue*calculatedExchangeRate;
        		$scope.frmPreSales.bnpsBPSTCVEuro = calculatedVal;
        	}
        	
        	$scope.calculatePercentageOfTCV = function (value,totalTCV) {
        		var percentageOfTCV = 0;
        		percentageOfTCV = value/totalTCV;
        		$scope.frmPreSales.percentOfBpsTCV =$filter('number')(percentageOfTCV,2);;
        	}
        	
        	$scope.saveBudgetDetails = function(frmPreSales) {
        		
        	if($scope.frmPreSales.dealType == "T&M") {
				$scope.frmPreSales.dealType = 2
			} else if($scope.frmPreSales.dealType == "FP") {
				$scope.frmPreSales.dealType = 1
			}
        		
        	if($scope.frmPreSales.dealStatus == "Open" ) {
				$scope.frmPreSales.dealStatus = 0;
			} else if($scope.frmPreSales.dealStatus == "Won") {
				$scope.frmPreSales.dealStatus = 1;
			} else if($scope.frmPreSales.dealStatus =="Lost") {
				$scope.frmPreSales.dealStatus = 2;
			}
        	
        		$scope.saved = true;
        		 var markers={
        				 "salesId" : $scope.salesId,
        				 "opportunityId" : frmPreSales.opportunityId,
        				 "opportunityDescription" : frmPreSales.opportunityDescription,
        				 "dealType":frmPreSales.dealType,
        				 "dealStatus":frmPreSales.dealStatus,
        				 "dealStartDate":frmPreSales.dealStartDate,
        				 "dealEndDate":frmPreSales.dealEndDate,
        				 "customerName":frmPreSales.customer,
        				 "financeCustomer":frmPreSales.financeCustomer,
        				 "iRISCode":frmPreSales.irisCode,
        				 "vertical":frmPreSales.vertical,
        				 "gBU":frmPreSales.gbuModel,
        				 "presalesWBSNumber":frmPreSales.preSalesWBSNumber,
        				 "dealCurrencyCode":$scope.currencyId,
        				"totalTCV":frmPreSales.totalTCV,
        				/*"approvedTCV":frmPreSales.totalTCV,
        				"totalBIDBudget":frmPreSales.totalBidBudget,
        				"totalApprovedBudget":frmPreSales.totalApprovedBudget,*/
        				"bPSTCV":frmPreSales.bnpsTCV,
        				"bPSBIDBudget":frmPreSales.bnpsBidBudget,
        				"bPSBIDBudgetINEuro":frmPreSales.bnpsBudgetEuro,
        				"bPSTCVINEuro":parseFloat(frmPreSales.bnpsBPSTCVEuro),
        				"percentageofBPSTCVonTotalTCV":frmPreSales.percentOfBpsTCV,
        				"preSalesCostAgainstWBS":frmPreSales.preSalesCostWBS,
        				"preSalesCostAgainstWBSInEuro":frmPreSales.preSalesCostinEuro,
        				"balance":frmPreSales.balance,
        				"balanceInEuro":frmPreSales.balanceInEuro,
        				"comments":frmPreSales.currentApproverCommentModel,
        				"status":1,
        				"statusIndicator": "Draft",
        				"bidStartDate":frmPreSales.startdate,
           			   	"bidEndDate":frmPreSales.Enddate,
        				"verticalId" : $scope.verticalId
        		 }
        		 var saveBudgetDetails=function(response){
						 if( response.status == 200){
							 $sessionStorage.id = response.data;
							  BootstrapDialog.show({
										title : 'Pre Sales Entry',
										type : BootstrapDialog.TYPE_PRIMARY,
										message : 'Pre Sales Data Saved Successfully.',
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(dialogRef) {
												dialogRef.close();
												window.location="PreSalesPortal";
											}
										} ]
									});
							  } else {
								  BootstrapDialog.show({
										title : 'Pre Sales Entry',
										type : BootstrapDialog.TYPE_Danger,
										message : 'Currently Facing Technical Issue.',
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(dialogRef) {
												dialogRef.close();
												 window.location="PreSalesPortal";
											}
										} ]
									});
							  }
					};
			WebServiceFactory.saveBudgetDetails(markers).then(saveBudgetDetails);
        	};
        	
        	$scope.submitBudgetDetails = function(frmPreSales) {
        		
        		$scope.preSubmitError = frmPreSales.$invalid;
        		if(frmPreSales.$invalid){
        			return;
        		}
        		
        	//	$scope.checkWBSNumber(frmPreSales.preSalesWBSNumber);
        		
        		BootstrapDialog.show({
     	           title: 'Pre Sales Entry',
     	           type : BootstrapDialog.TYPE_PRIMARY,
     	           message : 'Do you want to Submit the Budget Details?',
     	           closable: true,
     	           buttons: [{
     	               label: 'Yes',
     	               cssClass: 'btn-primary',
     	               action: function(dialogRef) {
     	            	   if($scope.frmPreSales.dealType == "T&M") {
     	            		   $scope.frmPreSales.dealType = 2
     	            	   } else if($scope.frmPreSales.dealType == "FP") {
     	            		   $scope.frmPreSales.dealType = 1
     	            	   }
     	            	   
     	            	   if($scope.frmPreSales.dealStatus == "Open" ) {
     	            		   $scope.frmPreSales.dealStatus = 0;
     	            	   } else if($scope.frmPreSales.dealStatus == "Won") {
     	            		   $scope.frmPreSales.dealStatus = 1;
     	            	   } else if($scope.frmPreSales.dealStatus =="Lost") {
     	            		   $scope.frmPreSales.dealStatus = 2;
     	            	   }
     	            	   
     	            	   $scope.submitted = true;
     	            	   var markers={
     	            			   "salesId" : $scope.salesId,
     	            			   "opportunityId" : frmPreSales.opportunityId,
     	            			   "opportunityDescription" : frmPreSales.opportunityDescription,
     	            			   "dealType":frmPreSales.dealType,
     	            			   "dealStatus":frmPreSales.dealStatus,
     	            			   "dealStartDate":frmPreSales.dealStartDate,
     	            			   "dealEndDate":frmPreSales.dealEndDate,
     	            			   "customerName":frmPreSales.customer,
     	            			   "financeCustomer":frmPreSales.financeCustomer,
     	            			   "iRISCode":frmPreSales.irisCode,
     	            			   "vertical":frmPreSales.vertical,
     	            			   "gBU":frmPreSales.gbuModel,
     	            			   "presalesWBSNumber":frmPreSales.preSalesWBSNumber,
     	            			   "dealCurrencyCode":$scope.currencyId,
     	            			   "totalTCV":frmPreSales.totalTCV,
     	            			   "approvedTCV":frmPreSales.totalTCV,
     	            			   "totalBIDBudget":frmPreSales.totalBidBudget,
     	            			   "totalApprovedBudget":frmPreSales.totalApprovedBudget,
     	            			   "bPSTCV":frmPreSales.bnpsTCV,
     	            			   "bPSBIDBudget":frmPreSales.bnpsBidBudget,
     	            			   "bPSBIDBudgetINEuro":frmPreSales.bnpsBudgetEuro,
     	            			   "bPSTCVINEuro":parseFloat(frmPreSales.bnpsBPSTCVEuro),
     	            			   "percentageofBPSTCVonTotalTCV":frmPreSales.percentOfBpsTCV,
     	            			   "preSalesCostAgainstWBS":frmPreSales.preSalesCostWBS,
     	            			   "preSalesCostAgainstWBSInEuro":frmPreSales.preSalesCostinEuro,
     	            			   "balance":frmPreSales.balance,
     	            			   "balanceInEuro":frmPreSales.balanceInEuro,
     	            			   "comments":frmPreSales.currentApproverCommentModel,
     	            			   "status":2,
     	            			   "statusIndicator": "Level_1",
     	            			   "bidStartDate":frmPreSales.startdate,
     	            			   "bidEndDate":frmPreSales.Enddate,
     	            			   "verticalId" : $scope.verticalId
     	            	   }
     	            	   var submitBudgetDetails = function(response){
     	            		   if( response.status == 200){
     	            			   $sessionStorage.id = response.data;
     	            			   BootstrapDialog.show({
     	            				   title : 'Pre Sales Entry',
     	            				   type : BootstrapDialog.TYPE_PRIMARY,
     	            				   message : 'Pre Sales Data submitted Successfully.',
     	            				   closable : false,
     	            				   buttons : [ {
     	            					   label : 'OK',
     	            					   action : function(dialogRef) {
     	            						   dialogRef.close();
     	            						   window.location="PreSalesPortal";
     	            					   }
     	            				   } ]
     	            			   });
     	            		   } else {
     	            			   BootstrapDialog.show({
     	            				   title : 'Pre Sales Entry',
     	            				   type : BootstrapDialog.TYPE_Danger,
     	            				   message : 'Currently Facing Technical Issue.',
     	            				   closable : false,
     	            				   buttons : [ {
     	            					   label : 'OK',
     	            					   action : function(dialogRef) {
     	            						   dialogRef.close();
     	            						   window.location="PreSalesPortal";
     	            					   }
     	            				   } ]
     	            			   });
     	            		   }
     	            	   };
     	            	  WebServiceFactory. submitBudgetDetails(markers).then(submitBudgetDetails);
     	               }	
     	          }, {
  		            label: 'No',
  		            cssClass: 'btn-primary',
  		            action: function(dialog) {
  		                dialog.close();
  		            }
  		            }]
  			});
            	};
            	
        	
        	$scope.clearData = function() {
        		$scope.frmPreSales.opportunityDescription = null;
        		$scope.frmPreSales.dealType = null;
        		$scope.frmPreSales.dealStatus =null;
        		$scope.frmPreSales.dealStartDate = null;
        		$scope.frmPreSales.dealEndDate = null;
        		$scope.frmPreSales.customer = null;
        		$scope.frmPreSales.financeCustomer = null;
        		$scope.frmPreSales.irisCode = null;
        		$scope.frmPreSales.vertical = null;
        		$scope.frmPreSales.gbuModel = null;
        		$scope.frmPreSales.preSalesWBSNumber = null;
        		$scope.frmPreSales.totalTCV = null;
        		$scope.frmPreSales.totalBidBudget = null;
        		$scope.frmPreSales.totalApprovedBudget  = null;
        		$scope.frmPreSales.bnpsTCV = null;
        		$scope.frmPreSales.bnpsBidBudget = null;
        		$scope.frmPreSales.bnpsBPSTCVEuro = null;
        		$scope.frmPreSales.balance = null;
        		$scope.frmPreSales.balanceInEuro = null;
        		$scope.frmPreSales.currentApproverCommentModel = null;
        	}
        	
        	/*$scope.checkWBSNumber = function (wbsNumber){
        		
        		$scope.checkWBSNumber = function(response) {
        			$scope.wbsNumberValue = response.data;
        		}
        		WebServiceFactory.checkWBSNumber(wbsNumber).then(checkWBSNumber);	
        	}*/
}]);