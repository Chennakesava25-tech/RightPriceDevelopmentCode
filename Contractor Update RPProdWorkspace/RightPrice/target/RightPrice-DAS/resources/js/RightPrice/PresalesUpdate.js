app.controller("PreSalesUpdateController",['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$index){
			$(window).load(function(){
			     $('.loader').fadeOut();
			});
			
			$scope.numberRegex = /^(0|[1-9]\d*)(\.\d+)?$/;
			var contextPath = "/RightPrice-DAS";
            $scope.frmUpdatePreSales={};
            $scope.getPreSalesDataOnId = function(){
            	var getPreSalesDataOnId = function(response) {
            		console.log(response.data);
					$scope.frmUpdatePreSales.opportunityNumber = response.data[0].salesId;
					$scope.frmUpdatePreSales.opportunityId = response.data[0].opportunityId;
					$scope.frmUpdatePreSales.opportunityDescription = response.data[0].opportunityDescription;
					if(response.data[0].dealStatus == 0 ) {
						$scope.frmUpdatePreSales.dealStatus ="Open";
					} else if(response.data[0].dealStatus == 1) {
						$scope.frmUpdatePreSales.dealStatus = "Won";
					} else if(response.data[0].dealStatus ==2) {
						$scope.frmUpdatePreSales.dealStatus = "Lost";
					} 
					if(response.data[0].dealType == 2 ) {
						$scope.frmUpdatePreSales.dealType ="T&M";
					} else if(response.data[0].dealType == 1) {
						$scope.frmUpdatePreSales.dealType = "FP";
					}
					$scope.frmUpdatePreSales.dealStartDate = response.data[0].dealStartDate;
					$scope.frmUpdatePreSales.dealEndDate = response.data[0].dealEndDate;
					$scope.frmUpdatePreSales.customer = response.data[0].customer_Name;
					$scope.frmUpdatePreSales.financeCustomer = response.data[0].financeCustomer;
					$scope.frmUpdatePreSales.irisCode = response.data[0].iRISCode;
					$scope.frmUpdatePreSales.vertical = response.data[0].vertical;
					$scope.frmUpdatePreSales.gbuModel = response.data[0].gBU;
					$scope.frmUpdatePreSales.preSalesWBSNumber = response.data[0].presalesWBSNumber;
					$scope.frmUpdatePreSales.currencyCode = response.data[0].dealCurrencyCode;
					$scope.frmUpdatePreSales.totalTCV = response.data[0].totalTCV;
					/*$scope.frmUpdatePreSales.approvedTCV = response.data[0].approvedTCV;
					$scope.frmUpdatePreSales.totalBidBudget = response.data[0].totalBIDBudget;
					$scope.frmUpdatePreSales.totalApprovedBudget = response.data[0].totalApprovedBudget;*/
					$scope.frmUpdatePreSales.bnpsTCV = response.data[0].bPSTCV;
					$scope.frmUpdatePreSales.bnpsBidBudget = response.data[0].bPSBIDBudget;
					$scope.frmUpdatePreSales.bnpsBudgetEuro = response.data[0].bPSBIDBudgetINEuro;
					
					$scope.frmUpdatePreSales.bnpsBPSTCVEuro = response.data[0].bPSTCVINEuro;
					$scope.frmUpdatePreSales.percentOfBpsTCV = response.data[0].percentageofBPSTCVonTotalTCV;
					$scope.frmUpdatePreSales.preSalesCostWBS = response.data[0].preSalesCostAgainstWBS;
					$scope.frmUpdatePreSales.preSalesCostinEuro = response.data[0].preSalesCostAgainstWBSInEuro;
					$scope.frmUpdatePreSales.balance = response.data[0].balance;
					$scope.frmUpdatePreSales.balanceInEuro = response.data[0].balanceInEuro;
					$scope.frmUpdatePreSales.currentApproverCommentModel = response.data[0].comments;
					$scope.frmUpdatePreSales.startdate = response.data[0].bidStartDate;
					$scope.frmUpdatePreSales.enddate = response.data[0].bidEndDate;
					$scope.verticalId = response.data[0].verticalId;
					console.log(response.data[0].verticalId)
				};
				WebServiceFactory.getPreSalesDataOnId($sessionStorage.salesId).then(getPreSalesDataOnId);
            };
            
            $scope.approveRequest = function() {
            	window.location = "PreSalesApproverScreen";
            }
			
            $scope.calculateBidBudget = function () {
                console.log("clicked here...");
                var dlgElem = angular.element("#popupModal");
                if (dlgElem) {
                   dlgElem.modal("show");
                }
                $scope.ExpenseCost = [{ name: "Employee Cost", id: 1 }, { name: "Travel Cost", id: 2 }, { name: "Purchase of Software", id: 3 }, { name: "Consulting Service", id: 4 },{ name: "R&D Expense", id: 5 },{ name: "Others", id: 6 },{ name: "Others 1", id: 7},{ name: "Others 2", id: 8}];
                $scope.expcost = $scope.ExpenseCost;
             };
             
             $scope.addGrandData = function() {
	            	$scope.frmUpdatePreSales.bnpsBidBudget=$scope.grandAmount;
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
            
         $scope.saveBudgetDetails = function(frmUpdatePreSales) {
     		
         	if($scope.frmUpdatePreSales.dealType == "T&M") {
 				$scope.frmUpdatePreSales.dealType = 2
 			} else if($scope.frmUpdatePreSales.dealType == "FP") {
 				$scope.frmUpdatePreSales.dealType = 1
 			}
         		
         	if($scope.frmUpdatePreSales.dealStatus == "Open" ) {
 				$scope.frmUpdatePreSales.dealStatus = 0;
 			} else if($scope.frmUpdatePreSales.dealStatus == "Won") {
 				$scope.frmUpdatePreSales.dealStatus = 1;
 			} else if($scope.frmUpdatePreSales.dealStatus =="Lost") {
 				$scope.frmUpdatePreSales.dealStatus = 2;
 			}
         	
         		 var markers={
         				 "salesId" : frmUpdatePreSales.opportunityNumber,
         				 "opportunityId" : frmUpdatePreSales.opportunityId,
         				 "opportunityDescription" : frmUpdatePreSales.opportunityDescription,
         				 "dealType":frmUpdatePreSales.dealType,
         				 "dealStatus":frmUpdatePreSales.dealStatus,
         				 "dealStartDate":frmUpdatePreSales.dealStartDate,
         				 "dealEndDate":frmUpdatePreSales.dealEndDate,
         				 "customerName":frmUpdatePreSales.customer,
         				 "financeCustomer":frmUpdatePreSales.financeCustomer,
         				 "iRISCode":frmUpdatePreSales.irisCode,
         				 "vertical":frmUpdatePreSales.vertical,
         				 "gBU":frmUpdatePreSales.gbuModel,
         				 "presalesWBSNumber":frmUpdatePreSales.preSalesWBSNumber,
         				 "dealCurrencyCode":$scope.currencyId,
         				"totalTCV":frmUpdatePreSales.totalTCV,
         				/*"approvedTCV":frmUpdatePreSales.totalTCV,
         				"totalBIDBudget":frmUpdatePreSales.totalBidBudget,
         				"totalApprovedBudget":frmUpdatePreSales.totalApprovedBudget,*/
         				"bPSTCV":frmUpdatePreSales.bnpsTCV,
         				"bPSBIDBudget":frmUpdatePreSales.bnpsBidBudget,
         				"bPSBIDBudgetINEuro":frmUpdatePreSales.bnpsBudgetEuro,
         				"bPSTCVINEuro":parseFloat(frmUpdatePreSales.bnpsBPSTCVEuro),
         				"percentageofBPSTCVonTotalTCV":frmUpdatePreSales.percentOfBpsTCV,
         				"preSalesCostAgainstWBS":frmUpdatePreSales.preSalesCostWBS,
         				"preSalesCostAgainstWBSInEuro":frmUpdatePreSales.preSalesCostinEuro,
         				"balance":frmUpdatePreSales.balance,
         				"balanceInEuro":frmUpdatePreSales.balanceInEuro,
         				"comments":frmUpdatePreSales.currentApproverCommentModel,
         				"status":1,
         				"statusIndicator": "Draft",
         				"bidStartDate":frmUpdatePreSales.startdate,
           			   	"bidEndDate":frmUpdatePreSales.enddate,
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
 												window.location="PreSalesView";
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
 												 window.location="PreSalesView";
 											}
 										} ]
 									});
 							  }
 					};
 			WebServiceFactory.saveBudgetDetails(markers).then(saveBudgetDetails);
         	};
         	
         	$scope.submitBudgetDetails = function(frmUpdatePreSales) {
        		
        		BootstrapDialog.show({
     	           title: 'Pre Sales Entry',
     	           type : BootstrapDialog.TYPE_PRIMARY,
     	           message : 'Do you want to Submit the Budget Details?',
     	           closable: true,
     	           buttons: [{
     	               label: 'Yes',
     	               cssClass: 'btn-primary',
     	               action: function(dialogRef) {
     	            	   if($scope.frmUpdatePreSales.dealType == "T&M") {
     	            		   $scope.frmUpdatePreSales.dealType = 2
     	            	   } else if($scope.frmUpdatePreSales.dealType == "FP") {
     	            		   $scope.frmUpdatePreSales.dealType = 1
     	            	   }
     	            	   
     	            	   if($scope.frmUpdatePreSales.dealStatus == "Open" ) {
     	            		   $scope.frmUpdatePreSales.dealStatus = 0;
     	            	   } else if($scope.frmUpdatePreSales.dealStatus == "Won") {
     	            		   $scope.frmUpdatePreSales.dealStatus = 1;
     	            	   } else if($scope.frmUpdatePreSales.dealStatus =="Lost") {
     	            		   $scope.frmUpdatePreSales.dealStatus = 2;
     	            	   }
     	            	   
     	            	   $scope.submitted = true;
     	            	   var markers={
     	            			   "salesId" : frmUpdatePreSales.opportunityNumber,
     	            			   "opportunityId" : frmUpdatePreSales.opportunityId,
     	            			   "opportunityDescription" : frmUpdatePreSales.opportunityDescription,
     	            			   "dealType":frmUpdatePreSales.dealType,
     	            			   "dealStatus":frmUpdatePreSales.dealStatus,
     	            			   "dealStartDate":frmUpdatePreSales.dealStartDate,
     	            			   "dealEndDate":frmUpdatePreSales.dealEndDate,
     	            			   "customerName":frmUpdatePreSales.customer,
     	            			   "financeCustomer":frmUpdatePreSales.financeCustomer,
     	            			   "iRISCode":frmUpdatePreSales.irisCode,
     	            			   "vertical":frmUpdatePreSales.vertical,
     	            			   "gBU":frmUpdatePreSales.gbuModel,
     	            			   "presalesWBSNumber":frmUpdatePreSales.preSalesWBSNumber,
     	            			   "dealCurrencyCode":$scope.currencyId,
     	            			   "totalTCV":frmUpdatePreSales.totalTCV,
     	            			   /*"approvedTCV":frmUpdatePreSales.totalTCV,
     	            			   "totalBIDBudget":frmUpdatePreSales.totalBidBudget,
     	            			   "totalApprovedBudget":frmUpdatePreSales.totalApprovedBudget,*/
     	            			   "bPSTCV":frmUpdatePreSales.bnpsTCV,
     	            			   "bPSBIDBudget":frmUpdatePreSales.bnpsBidBudget,
     	            			   "bPSBIDBudgetINEuro":frmUpdatePreSales.bnpsBudgetEuro,
     	            			   "bPSTCVINEuro":parseFloat(frmUpdatePreSales.bnpsBPSTCVEuro),
     	            			   "percentageofBPSTCVonTotalTCV":frmUpdatePreSales.percentOfBpsTCV,
     	            			   "preSalesCostAgainstWBS":frmUpdatePreSales.preSalesCostWBS,
     	            			   "preSalesCostAgainstWBSInEuro":frmUpdatePreSales.preSalesCostinEuro,
     	            			   "balance":frmUpdatePreSales.balance,
     	            			   "balanceInEuro":frmUpdatePreSales.balanceInEuro,
     	            			   "comments":frmUpdatePreSales.currentApproverCommentModel,
     	            			   "status":2,
     	            			   "statusIndicator": "Level_1",
     	            			  "bidStartDate":frmUpdatePreSales.startdate,
     	            			  "bidEndDate":frmUpdatePreSales.enddate,
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
     	            						   window.location="PreSalesView";
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
        		$scope.frmUpdatePreSales.opportunityDescription = null;
        		$scope.frmUpdatePreSales.dealType = null;
        		$scope.frmUpdatePreSales.dealStatus =null;
        		$scope.frmUpdatePreSales.dealStartDate = null;
        		$scope.frmUpdatePreSales.dealEndDate = null;
        		$scope.frmUpdatePreSales.customer = null;
        		$scope.frmUpdatePreSales.financeCustomer = null;
        		$scope.frmUpdatePreSales.irisCode = null;
        		$scope.frmUpdatePreSales.vertical = null;
        		$scope.frmUpdatePreSales.gbuModel = null;
        		$scope.frmUpdatePreSales.preSalesWBSNumber = null;
        		$scope.frmUpdatePreSales.totalTCV = null;
        		$scope.frmUpdatePreSales.totalBidBudget = null;
        		$scope.frmUpdatePreSales.totalApprovedBudget  = null;
        		$scope.frmUpdatePreSales.bnpsTCV = null;
        		$scope.frmUpdatePreSales.bnpsBidBudget = null;
        		$scope.frmUpdatePreSales.bnpsBPSTCVEuro = null;
        		$scope.frmUpdatePreSales.balance = null;
        		$scope.frmUpdatePreSales.balanceInEuro = null;
        		$scope.frmUpdatePreSales.currentApproverCommentModel = null;
        		$scope.frmUpdatePreSales.opportunityNumber = null;
        		$scope.frmUpdatePreSales.opportunityId = null;
        	}
			
}]);