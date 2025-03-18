app.controller("PreSalesApproverController",['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$index){
			$(window).load(function(){
			     $('.loader').fadeOut();
			});
			
			$scope.numberRegex = /^(0|[1-9]\d*)(\.\d+)?$/;
			var contextPath = "/RightPrice-DAS";
            $scope.frmApprovePreSales={};
            $scope.getPreSalesDataOnId = function(){
            	var getPreSalesDataOnId = function(response) {
            		console.log(response.data);
					$scope.frmApprovePreSales.opportunityNumber = response.data[0].salesId;
					$scope.frmApprovePreSales.opportunityId = response.data[0].opportunityId;
					$scope.frmApprovePreSales.opportunityDescription = response.data[0].opportunityDescription;
					if(response.data[0].dealStatus == 0 ) {
						$scope.frmApprovePreSales.dealStatus ="Open";
					} else if(response.data[0].dealStatus == 1) {
						$scope.frmApprovePreSales.dealStatus = "Won";
					} else if(response.data[0].dealStatus ==2) {
						$scope.frmApprovePreSales.dealStatus = "Lost";
					} 
					if(response.data[0].dealType == 2 ) {
						$scope.frmApprovePreSales.dealType ="T&M";
					} else if(response.data[0].dealType == 1) {
						$scope.frmApprovePreSales.dealType = "FP";
					}
					$scope.frmApprovePreSales.dealStartDate = response.data[0].dealStartDate;
					$scope.frmApprovePreSales.dealEndDate = response.data[0].dealEndDate;
					$scope.frmApprovePreSales.customer = response.data[0].customer_Name;
					$scope.frmApprovePreSales.financeCustomer = response.data[0].financeCustomer;
					$scope.frmApprovePreSales.irisCode = response.data[0].iRISCode;
					$scope.frmApprovePreSales.vertical = response.data[0].vertical;
					$scope.frmApprovePreSales.gbuModel = response.data[0].gBU;
					$scope.frmApprovePreSales.preSalesWBSNumber = response.data[0].presalesWBSNumber;
					$scope.frmApprovePreSales.currencyCode = response.data[0].dealCurrencyCode;
					$scope.frmApprovePreSales.totalTCV = response.data[0].totalTCV;
					//$scope.frmApprovePreSales.approvedTCV = response.data[0].approvedTCV;
					//$scope.frmApprovePreSales.totalBidBudget = response.data[0].totalBIDBudget;
					//$scope.frmApprovePreSales.totalApprovedBudget = response.data[0].totalApprovedBudget;
					$scope.frmApprovePreSales.bnpsTCV = response.data[0].bPSTCV;
					$scope.frmApprovePreSales.bnpsBidBudget = response.data[0].bPSBIDBudget;
					$scope.frmApprovePreSales.bnpsBudgetEuro = response.data[0].bPSBIDBudgetINEuro;
					
					$scope.frmApprovePreSales.bnpsBPSTCVEuro = response.data[0].bPSTCVINEuro;
					$scope.frmApprovePreSales.percentOfBpsTCV = response.data[0].percentageofBPSTCVonTotalTCV;
					$scope.frmApprovePreSales.preSalesCostWBS = response.data[0].preSalesCostAgainstWBS;
					$scope.frmApprovePreSales.preSalesCostinEuro = response.data[0].preSalesCostAgainstWBSInEuro;
					$scope.frmApprovePreSales.balance = response.data[0].balance;
					$scope.frmApprovePreSales.balanceInEuro = response.data[0].balanceInEuro;
					$scope.frmApprovePreSales.currentApproverCommentModel = response.data[0].comments;
					$scope.frmApprovePreSales.startdate = response.data[0].bidStartDate;
					$scope.frmApprovePreSales.enddate = response.data[0].bidEndDate;
				};
				WebServiceFactory.getPreSalesDataOnId($sessionStorage.salesId).then(getPreSalesDataOnId);
            };
            
            
			
}]);