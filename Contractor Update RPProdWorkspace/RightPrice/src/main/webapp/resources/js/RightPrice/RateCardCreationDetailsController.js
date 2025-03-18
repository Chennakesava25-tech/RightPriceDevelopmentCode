//var app = angular.module('RateCardCreationApp', ['ngMessages']);
	app.controller("RateCardCreationController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index) {
		 console.log("inside RateCardCreationController");

		 	//Screen interections
		 	// $scope.actionflagVal = 0;
		 	 //for checking action value
		 	 /*var checkFlag = function() {
		 		 $scope.actionflag = $scope.frmRateCard.action;
		 		 if($scope.actionflag == "Add"){
		 			 $scope.actionflagVal = 1;
		 		 }
		 		 else if($scope.actionflag == "Edit"){
		 			 $scope.actionflagVal = 2;
		 		 }
		 		 else if($scope.actionflag == "View"){
		 			 $scope.actionflagVal = 3;
		 		 }

			};
		 	 */
			 $scope.TeamDetailsHidden = true;
			 $scope.AddLocationHidden = true;
			 $scope.ProposedCurrenciesHidden = true
			 $scope.ShowHideTeamDetails = function () {
			     $scope.TeamDetailsHidden = $scope.TeamDetailsHidden ? false : true;
			 };
			 
			 $scope.ShowHideAddLocation = function () {
			     $scope.AddLocationHidden = $scope.AddLocationHidden ? false : true;
			 };
			
			 $scope.ShowHideProposedCurrencies = function () {
			     $scope.ProposedCurrenciesHidden = $scope.ProposedCurrenciesHidden ? false : true;
			 };

			 $scope.moveTop = function(){
				$location.hash('PageHeading'); 
				$anchorScroll();
			 };
				
			$scope.moveBottom = function(){
				$location.hash('includedFooter'); 
				$anchorScroll();
			};
			
			$scope.clicked = function(){   
			    window.location="RateCardCreationRoleSelection";
			}; 
			var getCountryDetail = function(response) {
				console.log("get country data");
		  		console.log(response);
		  		$scope.countryData = response.data;
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);

			var getCurrency = function(response) {
				console.log("getCurrency");
		  		console.log(response);
			    $scope.currency = response.data;
			};
			WebServiceFactory.getCurrency().then(getCurrency);
			
            $scope.getCity = function(countryId){
                var getCity = function(response) {
                    $scope.city = response.data;
                    console.log("City Names are...........");
                    console.log($scope.city);
                };
                WebServiceFactory.getCity().then(getCity);
            }
						
			// services
			var getCustomerByVerticalGroupId = function(response) {
			    $scope.customer = response.data;
			    console.log(response);
			    console.log($scope.customer);
			};
			//API call for getCurrentUser
			WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);
			
			$scope.rcCreationDetails = function(){
				console.log("Called");
				var RATE_CARD_NAME = $scope.frmRateCard.ratecardname;
				console.log("ratecardname"+ RATE_CARD_NAME);
				
				var START_DATE = $scope.frmRateCard.startdate;
				console.log("startdate"+ START_DATE);
				
				var END_DATE = $scope.frmRateCard.enddate;
				console.log("enddate"+ END_DATE);
				
				var EXPECTED_END_DATE =  $scope.frmRateCard.expectedenddate;
				console.log("expectedenddate"+ EXPECTED_END_DATE);
				
				var TCV = $scope.frmRateCard.tcv;
				console.log("tcv"+ TCV);
				
				var VOLUME_DISCOUNT = $scope.frmRateCard.volumediscount;
				console.log("volumediscount"+ VOLUME_DISCOUNT);
				
				var EXPECTED_ONSITE_RESOURCE = $scope.frmRateCard.expectedonsiteresource;
				console.log("expectedonsiteresource"+ EXPECTED_ONSITE_RESOURCE);
				
				var EXPECTED_OFFSHORE_RESOURCE = $scope.frmRateCard.expectedoffshoreresource;
				console.log("expectedoffshoreresource"+ EXPECTED_OFFSHORE_RESOURCE);
				
				var INDUSTRY = $scope.frmRateCard.industry;
				console.log("industry"+ INDUSTRY);
				
				var APPLICABLE_YEARS = 1
				console.log("applicableyears"+ APPLICABLE_YEARS);
				
				var CURRENCY = $scope.frmRateCard.ratecardname;
				console.log("currency"+ CURRENCY);
				var markers={ 	
		    		 	"RATE_CARD_NAME": RATE_CARD_NAME,
		    		 	"START_DATE": START_DATE,
		    		 	"END_DATE": END_DATE,
		    		 	"EXPECTED_END_DATE": EXPECTED_END_DATE,
		    		 	"TCV":TCV,
		    		 	"VOLUME_DISCOUNT": VOLUME_DISCOUNT,
		    		 	"EXPECTED_ONSITE_RESOURCE": EXPECTED_ONSITE_RESOURCE,
		    		 	"EXPECTED_OFFSHORE_RESOURCE": EXPECTED_OFFSHORE_RESOURCE,
		    		 	"INDUSTRY": INDUSTRY,
		    		 	"APPLICABLE_YEARS": APPLICABLE_YEARS,
		    		 	"CURRENCY": CURRENCY
					};
					console.log(markers);
					$http({
                        method: 'POST',
                        url: "/RightPrice-DAS/addRateCardDetail",
                        dataType: 'json',
                        data: angular.toJson(markers),  
                headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
           }).
           then(function(data) {
                  if(data.status == 200){
                         var message = data.data;
                   BootstrapDialog.show({
                         title : 'Rate Card Creation Lob',
                         type : BootstrapDialog.TYPE_PRIMARY,
                         message : message,
                         closable : false,
                         buttons : [{
                                label : 'OK',
                                action : function(dialogRef) {
                                      dialogRef.close();
                                      window.location = "/";
                                }
                         }]
                   });
                  }else{
                          message = data.data;
                         BootstrapDialog.show({
                         title : 'Masters Lob',
                         type : BootstrapDialog.TYPE_DANGER,
                         message : message,
                         closable : false,
                         buttons : [{
                                label : 'OK',
                                action : function(dialogRef) {
                                      dialogRef.close();
                                      window.location = "/";
                                }
                         }]
                   });
                  }
                 },function (data) {
                        $scope.displayres = data;
                     $scope.answer = 'Posting data was unsuccessful.';
                 });

			};
            
			 $scope.calApplicableYears = function(startdate,enddate){
				console.log("dfgdfgdfg")
				startdateVal =  $scope.frmRateCard.startdate;
				enddateVal = $scope.frmRateCard.enddate;
				startdateVal1 =  new Date(startdateVal)
				enddateVal1 = new Date(enddateVal)
				console.log(startdateVal1);
				console.log(enddateVal1)
				/*
				frmRateCard.applicableyears = startdateVal.getDate() - enddateVal.getDate();
				console.log(startdateVal);
				console.log(enddateVal);*/
//				console.log(startdateVal1.getDate());
//				console.log(enddateVal1.getDate());
				
				$scope.diference = Math.abs(startdateVal1.getDate() - enddateVal1.getDate());
				console.log("$scope.diference :"+$scope.diference);
				$scope.frmRateCard.applicableyears = $scope.diference;
				console.log("$scope.applicableyears :"+$scope.diference);
				
				
			}
			
			               
	}]);