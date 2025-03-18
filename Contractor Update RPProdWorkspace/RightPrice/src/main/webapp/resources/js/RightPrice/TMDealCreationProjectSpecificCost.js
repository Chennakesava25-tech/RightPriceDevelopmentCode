"use strict";
app.controller("TMDealCreationPFContractorCostController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		
	 var rpVrsId = $sessionStorage.rpDealVersionId		
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
		$scope.Prev = function()
	    {   
	        window.location='TMDealCreationStaffing';
	    }; 
	     $scope.Next = function()
	    {   
	        window.location='TMDealCreationUploadEstimationRelatedDocuments';
	    };   
	    
	    
	    var getDealDetails = function(response) {
			
	  		console.log(response);
	  		$scope.dealDetails = response.data;
	  		console.log("Approver Data")
	  		console.log($scope.dealDetails);
	  		console.log($scope.dealDetails[0].dealId);
	  		console.log($scope.dealDetails[0].customerId);
	  		console.log($scope.dealDetails[0].dealStartDate);
	  		console.log($scope.dealDetails[0].dealEndDate);
	  		console.log($scope.dealDetails[0].dealDescription);
	  		console.log($scope.dealDetails[0].dealStatus)
	  		console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
	  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");
	  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
	  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
	  		
	  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
	  		var date = new Date(dealEndDate.substring(0,10));
	  		console.log("End Date is......... " + date);
	  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.dealDetails[0].dealEndDate = dateENd;
	  		
			console.log("dateEND     ...... "+dateENd)
	  		
			var dealStartDate = $scope.dealDetails[0].dealStartDate;
			var date = new Date(dealStartDate.substring(0,10));
			var dateStart = $filter('date')(date,'dd/MM/yyyy');
			$scope.dealDetails[0].dealStartDate = dateStart;
			//alert('hi 66');
			$scope.dealDetails[0].rpVersionId = $sessionStorage.rpDealVersionId;	//arvind
			$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;
			
				
			 var startDay = new Date(dateStart);
              var endDay = new Date(dateENd);
              var millisecondsPerDay = 1000 * 60 * 60 * 24;

              var millisBetween =  endDay.getTime()-startDay.getTime() 
              var days = millisBetween / millisecondsPerDay;
              $scope.dealDetails[0].DealDuration = Math.floor(days);
			
            
	  		console.log($scope.dealDetails[0].percentageClose);
	  		console.log($scope.dealDetails[0].currencyId);
	  		console.log($scope.dealDetails[0].dealDuration);
	  		console.log($scope.dealDetails[0].stageId);
		};

		WebServiceFactory.getDealDetails($sessionStorage.DealModel,rpVrsId).then(getDealDetails);
	    
	}]);