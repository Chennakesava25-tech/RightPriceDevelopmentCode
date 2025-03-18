app.controller("PreSalesMappingController",['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$index){
			$(window).load(function(){
			     $('.loader').fadeOut();
			});
			
			 var getPresalesOpportunityDetails = function(response) {
	            	if(response.data !=null) {
	            		$scope.opportunityArray = response.data;
	            		console.log("The Deal Details");
	            		console.log($scope.opportunityArray);
	            	}
	            }
	        	WebServiceFactory.getPresalesOpportunityDetails().then(getPresalesOpportunityDetails);
            
	       $scope.mapDataOnId = function(oId,wbsNumber) {
	        		var markers={
	         				 "opportunityId" : oId,
	         				 "presalesWBSNumber": wbsNumber
	         		 }
	           	 var mapDataOnId = function(response){ 
	           	   if( response.status == 200){ 
	           		   $scope.filteredItems="";
	           		 BootstrapDialog.show({
							title : 'PreSales Mapping',
							type : BootstrapDialog.PRIMARY,
							message : 'Mapped Successfully',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								window.location="PreSalesWBSNumberMapping";
								}
							} ]
						});
	           	         }else{
	           	        	 BootstrapDialog.show({
	       							title : 'PreSales Mapping',
	       							type : BootstrapDialog.TYPE_DANGER,
	       							message : 'Currrently facing Techincal Issue.',
	       							closable : false,
	       							buttons : [ {
	       								label : 'OK',
	       								action : function(dialogRef) {
	       									dialogRef.close();
	       								window.location="PreSalesWBSNumberMapping";
	       								}
	       							} ]
	       						});
	           	         }
	           	 }
	        	WebServiceFactory.mapDataOnId(markers).then(mapDataOnId);
	        };
}]);