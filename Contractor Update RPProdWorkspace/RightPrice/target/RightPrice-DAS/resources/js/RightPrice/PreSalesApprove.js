app.controller("PreSalesApproverController",['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$index){
			$(window).load(function(){
			     $('.loader').fadeOut();
			});
			
			 $scope.getPreSalesApprovalData = function(){
	            	var getPreSalesApprovalData = function(response) {
	            		/*var i;
	                 	 for(i=0;i<response.data.length;i++){
	                 		var startDate=response.data[i].startDate.split(" ");
	                 		response.data[i].startDate=startDate[0];
	                 		var endDate=response.data[i].endDate.split(" ");
	                 		response.data[i].endDate=endDate[0];*/
	                 		/*var level1ApprovalDate=response.data[i].level1ApprovalDate.split(" ");
	                 		response.data[i].level1ApprovalDate=level1ApprovalDate[0];
	                 		var level2ApprovalDate=response.data[i].level2ApprovalDate.split(" ");
	                 		response.data[i].level2ApprovalDate=level2ApprovalDate[0];
	                 		console.log(response.data[i].startDate);*/
	                 		$scope.PreSalesApproveRequestData = response.data;
	            		console.log(response.data);
					};
					//WebServiceFactory.getPreSalesDataOnId($sessionStorage.salesId).then(getPreSalesApprovedData);
					WebServiceFactory.getPreSalesApprovalData(2).then(getPreSalesApprovalData);
	            };
	            
	            $scope.approveRequest = function(row) {
	            	$sessionStorage.salesId = row.salesId;
	            	window.location = "PreSalesApproverScreen";
	            };
}]);