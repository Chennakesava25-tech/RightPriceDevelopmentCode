//var app = angular	.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("ResourceForcastController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 console.log("inside ResourceForcastController");
		 var contextPath = "/RightPrice-DAS";	
		 $scope.customer=[];
		 $scope.dealData=[];
	
		//======================= Data onload starts
		 
			 //WebServiceFactory.getCustomer().then(getCustomer);
		 
	window.onload=function(){
		console.log(sessionStorage);
		 var userType=sessionStorage.userType;
		 
	    	BootstrapDialog.show({
	        	title : 'Resource Forecast',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Kindly visit DeMS Portal for Resource Forecasting.',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			//window.location = "RateCardCreationDetails";
	        		}
	        	}]
	        });

		 /*
	 		 	console.log("customer response");
	 		 	//console.log(response.data);
			   $scope.customer = response.data;
			   console.log("customer::::::::::::::::::");
			   console.log(response.data);
			 */

/*			 if(userType == 'GFT' || userType == 'CEO' || userType == 'CDO' || userType == 'RiskManagers'){
			 		var getGFTCustomer = function(response) {
			 			  $scope.custom = response.data;
			 			 console.log("custom Array :" )
			 			 console.log($scope.custom)
			 			 $scope.customer=[]
			 			 for(i=0;i<$scope.custom.length;i++){
			 				$scope.customer.push({
			 					"customerId" : $scope.custom[i].customer.customerId,
			 					"customerName" : $scope.custom[i].customer.customerName
			 				});
			 			 }
			 			 console.log("customer Array :" )
			 			 console.log($scope.customer) 
			 			 $scope.forecastData();
			 			$scope.dealFunction();
					 };
				 WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
			 	
				}*/
/*				else  if(userType == 'BUH' || userType == 'DUH')
				{
					
					$scope.verticalId="";
					var getVerticalApproverData = function(response) 
					{
						$scope.verticalDetails = response.data;
						if($scope.verticalDetails!=undefined);
						{
							angular.forEach($scope.verticalDetails, function(value,key) {
								if($scope.user != null) {
									if($scope.user == value.deliveryHeadId || $scope.user == value.buHeadId) {
										$scope.verticalId=value.verticalId;
										$scope.getDUHCustomerVerticalMapping($scope.verticalId);
									}
								} else {
									if($sessionStorage.currentUser == value.deliveryHeadId || $sessionStorage.currentUser == value.buHeadId) {
										$scope.verticalId=value.verticalId;
										$scope.getDUHCustomerVerticalMapping($scope.verticalId);
									}
								}
							});
						}
					};
					WebServiceFactory.getVerticalApproverData().then(getVerticalApproverData);
					var getCustomerByVerticalGroupId = function(response) 
					{
						$scope.custom = response.data;
						console.log("custom Array :" )
			 			 console.log($scope.custom)
			 			  $scope.customer=[]
			 			 for(i=0;i<$scope.custom.length;i++){
				 				$scope.customer.push({
				 					"customerId" : $scope.custom[i].customer.customerId,
				 					"customerName" : $scope.custom[i].customer.customerName
				 				});
				 			 }
				 			 console.log("customer Array :" )
				 			 console.log($scope.customer) 
				 			$scope.dealFunction();
				 			 $scope.forecastData();
					}
						WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);
			 	}*/
/*				else if(userType == 'User' || userType == 'Delivery')
				{
					var getCustomerForUser = function(response) 
					{
						$scope.custom = response.data;
						console.log("custom Array :" )
			 			 console.log($scope.custom)
			 			 
			 			 $scope.customer=[]
			 			 for(i=0;i<$scope.custom.length;i++){
				 				$scope.customer.push({
				 					"customerId" : $scope.custom[i].customer.customerId,
				 					"customerName" : $scope.custom[i].customer.customerName
				 				});
				 			 }
				 			 console.log("customer Array :" )
				 			 console.log($scope.customer) 
				 			 $scope.dealFunction();
				 			 $scope.forecastData();
					}
						WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
				}
*/
			 
			 
	};
		$scope.dealFunction=function(){
		 var getDealData = function(response){
			 console.log("Deal Data ....");
			 $scope.deal=response.data;
			 $scope.dealData=[];
			 var i=0;
			 angular.forEach($scope.deal,function(value,key){
					angular.forEach($scope.customer,function(value1,key1){
						if($scope.customer[key1].customerId == $scope.deal[key].customerId) {
							 $scope.dealData[i] = $scope.deal[key];
							i++
						}
					}); 
				 });
			 
			  console.log("Deal data ::::::::::::::::::");
			  console.log($scope.dealData);
		 }
		 WebServiceFactory.getDealData().then(getDealData);
		 };
		 //======================= Data onload ends
		 
		 $scope.searchForecast=function(frmForecast){
			 if(frmForecast.$valid){
				 $scope.forecastData();
			 }
		 }
		 
		 $scope.clear=function(){
			 $scope.frmForecast.customerModel=null;
			 $scope.frmForecast.detailId=null;
		 }
		 
		 $scope.forecastData=function(){
			 var customerId;
			 var cRMDealId;
			 
			 if($scope.frmForecast.customerModel!=undefined)
				 {
				 customerId = $scope.frmForecast.customerModel.customerId;
				 }
			 else{
				 customerId=0;
			 	}
			 if($scope.frmForecast.detailId!=undefined){
				 cRMDealId = $scope.frmForecast.detailId.crmDealId;
			 }
			 else
			 {
				 cRMDealId=0;				 
			 }
				 
	
			 var markers=
			 {
					 "customerId":customerId,
					 "cRMDealId":cRMDealId
			 };
			 var forecastData = function(response) {
				 $scope.forcastInf = response.data;
				 $scope.forcastInfo=[];
				 var i=0;
				 angular.forEach($scope.customer,function(value,key){
					angular.forEach($scope.forcastInf,function(value1,key1){
						if($scope.customer[key].customerId == $scope.forcastInf[key1].customerId) {
							$scope.forcastInfo[i] = $scope.forcastInf[key1];
							i++
						}
					}); 
				 });
				 
				 console.log($scope.forcastInfo); 
				 
				 for(var i=0;i<$scope.forcastInfo.length;i++)
				 {
				 if($scope.forcastInfo[i].industry=="1")
				 {
					 $scope.forcastInfo[i].industry="IT";
				 }	
				 else
				 {
					 $scope.forcastInfo[i].industry="KPO";
				 }

				 if($scope.forcastInfo[i].deal_Type_Id=="1")
				 {
					 $scope.forcastInfo[i].deal_Type_Id="FP";
				 }	
				 else
				 {
					 $scope.forcastInfo[i].deal_Type_Id="T&M";
				 }

				 
				 var strMain = $scope.forcastInfo[i].deal_Start_Date;
				 var arrSplit = [];
			     arrSplit = strMain.split(" ");
			     $scope.forcastInfo[i].deal_Start_Date = $filter('date')(arrSplit[0],'dd/MM/yyyy');
			     strMain = $scope.forcastInfo[i].deal_End_Date;
				 arrSplit = [];
			     arrSplit = strMain.split(" ");
			     $scope.forcastInfo[i].deal_End_Date = $filter('date')(arrSplit[0],'dd/MM/yyyy');

			     
				 }
				 
				}
			 WebServiceFactory.forecastData(markers).then(forecastData);
			 $scope.passData=function(row){
//				 $sessionStorage.rfData=[];
//				 $sessionStorage.rfData=row;
				 $localStorage.rfData =row ;
				 console.log("row data ::: ");
				 console.log(row);
				 	//	$scope.RFDetail
				 }
		 }
		 
		 $scope.currentUser = function(user)
		 {
		 	$scope.user = user;
		 	if($sessionStorage.currentUser == null) {
		 		$sessionStorage.currentUser = $scope.user;
		 	}
		 }
		 				
		 $scope.getDUHCustomerVerticalMapping = function(verticalId) {
		 	var getDUHCustomerVerticalMapping = function(response) {
		 		$scope.custom = response.data;
		 		$scope.customerVerticalMapping = response.data;
		 		for(i=0;i<$scope.custom.length;i++){
	 				$scope.customer.push({
	 					"customerId" : $scope.custom[i].customer.customerId,
	 					"customerName" : $scope.custom[i].customer.customerName
	 				});
	 			 }
	 			 console.log("customer Array :" )
	 			 console.log($scope.customer) 
	 			$scope.dealFunction();
	 			 $scope.forecastData();
		 	}
		 	WebServiceFactory.getDUHCustomerVerticalMapping(verticalId).then(getDUHCustomerVerticalMapping);
		 };
		 
	}]);