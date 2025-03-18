app.controller("PreSalesApproverController",['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$index){
			$(window).load(function(){
			     $('.loader').fadeOut();
			});
            $scope.getPreSalesApprovedData = function(){
            	var getPreSalesApprovedData = function(response) {
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
            		console.log(response.data);
            		$scope.filteredItems = response.data;
				};
				//WebServiceFactory.getPreSalesDataOnId($sessionStorage.salesId).then(getPreSalesApprovedData);
				WebServiceFactory.getPreSalesApprovedData(2).then(getPreSalesApprovedData);
            };
			
            $scope.exportToExcel = function(tableId) {
        		
        		var exportHref=WebServiceFactory.downloadExcel(tableId,'PreSales_Report');
        	  
        	};
        	
       $scope.searchDataOnId = function() {
    	   var markers={
  				 "opportunityId" : $scope.PreSalesReport.opportunityId,
  				 "dealStartDate": $scope.PreSalesReport.startdate,
  				 "dealEndDate":$scope.PreSalesReport.enddate
  		 }
    	 var searchDataOnId = function(response){ 
    	   if( response.status == 208){ 
    		   $scope.filteredItems="";
				  BootstrapDialog.show({
							title : 'PreSales Report',
							type : BootstrapDialog.TYPE_DANGER,
							message : 'No Opportunity Id available for selected Criteria.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								window.location="PreSalesApprovedReport";
								}
							} ]
						});
    	         }else{
    	        	 console.log(response.data);
             		$scope.filteredItems = response.data;
    	         }
    	 }
    	   WebServiceFactory.searchDataOnId(markers).then(searchDataOnId);
       };
			
}]);