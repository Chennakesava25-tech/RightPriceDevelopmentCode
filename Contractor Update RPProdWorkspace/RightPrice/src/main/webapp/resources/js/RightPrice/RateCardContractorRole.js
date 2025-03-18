var app = angular.module('RightPriceApp', ['ngStorage', 'ngMessages']);
//var app = angular.module('RightPriceApp', ['ngStorage']);

app.controller("RPContractorRole", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage','$filter', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,$localStorage, $sessionStorage,$index) {
	$scope.roleUtilizationAndRates = {};
	$scope.RoleUtilizationHidden = true;
	$scope.isOnsiteUtilization = false;
	$scope.isOffshoreUtilization = false;
    $scope.ShowHideRoleUtilization = function () {
        $scope.RoleUtilizationHidden = $scope.RoleUtilizationHidden ? false : true;
    };
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
	$scope.Next = function()
    {   
		$scope.isManualRCType = $sessionStorage.isManualRCType;
		if($scope.isManualRCType == "A") {
			$scope.isAutomatic = true;
		} else if($scope.isManualRCType == "M") {
			$scope.isManual = true;
		} else if($scope.isManualRCType == "H") {
			$scope.isHybrid = true;
		}

		if($scope.isManual == true){
			window.location ='RateCardCreationGMRateDetails';
		} else if($scope.isHybrid == true || $scope.isAutomatic == true){
			window.location='RateCardCreationRoleUtilizationAndRates';
		} 
    }; 
    $scope.Prev = function()
    {   
        window.location='RateCardCreationRoleSelection';
    };

    $scope.contractordetails=[];
    //$localStorage.rcId = 7;
    // Get the Rate card info using ratecard id
    $scope.isDisabled = false;
    if($localStorage.tempAction == 3){
    	$scope.isDisabled = true;
    	$scope.isOnsiteUtilization = true;
    	$scope.isOffshoreUtilization = true;
    }
    else{
    	$scope.isDisabled = false;
    	$scope.isOnsiteUtilization = false;
    	$scope.isOffshoreUtilization = false;
    }
    if($localStorage.rcId != 0 && $localStorage.rcId != undefined) {
		var getRateCardInfo = function(response) {
	  		$scope.rateCardInfo = response.data;
	  		
	  		$scope.onsiteUtilization = $scope.rateCardInfo[0].expectedOnsiteResourcePercentage;
	  		//alert($scope.onsiteUtilization);
	  		
	  		$scope.offshoreUtilization = $scope.rateCardInfo[0].expectedOffshoreResourcePercentage;
	  		//alert($scope.offshoreUtilization);
	  		
	  		var rcStartDate  = $scope.rateCardInfo[0].rcStartDate;
	  		var date = new Date(rcStartDate.substring(0,10));
	  		var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.rateCardInfo[0].rcStartDate = rcStartDD;
	  		
	  		var rcEndDate  = $scope.rateCardInfo[0].rcEndDate;
	  		var date = new Date(rcEndDate.substring(0,10));
	  		var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.rateCardInfo[0].rcEndDate = rcEndDateDD;
	  		
	  		var expectedRCEndDate  = $scope.rateCardInfo[0].expectedRCEndDate;
	  		var date = new Date(expectedRCEndDate.substring(0,10));
	  		var expectedRCEndDateDD = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.rateCardInfo[0].expectedRCEndDate = expectedRCEndDateDD;
	  		
	  		if($scope.onsiteUtilization == 0){
	  			$scope.isOnsiteUtilization = true;
	  		}
	  		if($scope.offshoreUtilization == 0){
	  			$scope.isOffshoreUtilization = true;
	  		}
	  		
	  		var currencyId = $scope.rateCardInfo[0].consolidatedRcCurrencyId;
	  		var statusId = $scope.rateCardInfo[0].currentApprovalStatus;
	  		//$scope.currencyName = [];
	  		
	  		switch (currencyId) {
	  		case 1 : 
	  			$scope.currencyName = "USD";
	  			break;
	  		case 2 : 
	  			$scope.currencyName = "INR";
	  			break;
	  		case 3 : 
	  			$scope.currencyName = "CAD";
	  			break;
	  		case 4 : 
	  			$scope.currencyName = "EUR";
	  			break;
	  		case 5 : 
	  			$scope.currencyName = "GBP";
	  			break;
	  		case 6 : 
	  			$scope.currencyName = "AUD";
	  			break;
			case 7 : 
				$scope.currencyName = "Common";
				break;
			default : 
	  			break;
			}
	  		
	  	// set the Status 
	  		$scope.status = [];
	  		switch (statusId) {
	  		case 1 : 
	  			$scope.status = "Draft";
	  			break;
			case 2 : 
				$scope.status = "Pending Approval";
				break;
			case 3 : 
				$scope.status = "Approved";
				break;
			case 4 : 
				$scope.status = "Recycled";
				break;
			case 5 : 
				$scope.status = "Deactivated";
				break;
			case 6 : 
				$scope.status = "Expired";
				break;
			case 7 : 
				$scope.status = "Manual with GFT";
				break;
			default : 
	  			break;
	  		}
	  		
	  		if(statusId!=1)
	  		{
	  			$scope.isDisabled=true;
	  		}
		};
		WebServiceFactory.getRateCardInfo($localStorage.rcId).then(getRateCardInfo);
		}
		else {
			 BootstrapDialog.show({
					title : 'Rate Card - Finalize Rate Card',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Please Create New Rate Card or Select Existing One.',
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							//$window.location.reload();
							window.location="RateCardCreationDetails";
						}
					} ]
				});
		}
    //--------------------------------------//---------------------------------------------------//
    
      
    //Web Service to get Country
    var getRateUtilizationCountry = function(response) {
	    $scope.country = response.data;
	    if($scope.country.length!=0){	    	
	    if($scope.country.length == 1){
	    	$scope.getCity($scope.country[0].countryId);
	    	}
	    }else{
	    	BootstrapDialog.show({
	        	title : 'Rate Card Utilization and Rates',
	        	type : BootstrapDialog.TYPE_DANGER,
	        	message : 'Rate card Data is not submitted at previous pages.',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			//window.location = "RateCardCreationDetails";
	        		}
	        	}]
	        });
	    }
	};
    WebServiceFactory.getRateUtilizationCountry($localStorage.rcId).then(getRateUtilizationCountry);
           
  //Web Service to get City
    $scope.getCity = function(countryId){
    	var getRateUtilizationCity = function(response) {
    	    $scope.city = response.data;
    	    if($scope.country.length == 1 && $scope.city.length == 1){
    	    	$scope.roleUtilizationAndRates.ddlCountryModel = $scope.country[0].countryId;
    	    	$scope.roleUtilizationAndRates.ddlCityModel = $scope.city[0].cityId;
    	    	$scope.getRateUtilizationData($scope.roleUtilizationAndRates.ddlCityModel);
    	    }
    	};
    	WebServiceFactory.getRateUtilizationCity($localStorage.rcId,countryId).then(getRateUtilizationCity);
    }
    
    $scope.tableData = [];
    //Web Service to get data for table
    $scope.getRateUtilizationData = function(cityId){
    	var getRateUtilization = function(response) {
    		console.log(response);
    	    $scope.yearList = response.data.yearData;
    	    $scope.dataList = response.data.dataList;
    	    $scope.tempYearList = [];
    	    angular.forEach($scope.yearList, function(value, key){
    	    	$scope.tempYearList.push(value[0]);
    	     });  
    	    $scope.yearData($scope.tempYearList[0]);
    	};
    	WebServiceFactory.getRateUtilization(cityId,$localStorage.rcId).then(getRateUtilization);
    	
    	var getContractor = function(response){
			$scope.contractordetails = response.data;
		};
		WebServiceFactory.getContractor(cityId,$localStorage.rcId).then(getContractor);
    };
    
    $scope.yearData = function(year){
    	$scope.tableDetails=[];
    	$scope.currentYear = year;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			angular.forEach($scope.contractordetails, function(value1,key1) {
    				if(value.rcId == value1.rcId && value.rateCardRoles.clientRole == value1.rcCustRole) {
    					$scope.dataList[key].onsiteRateHour = value1.onsiteRateHour;
    					$scope.dataList[key].onsiteCostHour = value1.onsiteCostHour;
    					$scope.dataList[key].offshoreRateHour = value1.offshoreRateHour;
    					$scope.dataList[key].offshoreCostHour = value1.offshoreCostHour;
    				}
    			})
    			$scope.createRow($scope.dataList[key]); 
    		}
    	});
    };
    $scope.tableDetails=[];
    //function to create year wise row
    $scope.createRow = function(data){
    	if(data.masterRoleId==1){
    		
    		if(data.onsiteRateHour == null && data.onsiteCostHour == null){
    			$scope.tempOnsiteMasterRate = 0;
    			$scope.tempOnsiteMasterRateGm = 0;
    		}
    		else{
    			$scope.tempOnsiteMasterRate = data.onsiteRateHour;
    			$scope.tempOnsiteMasterRateGm = data.onsiteCostHour;
    		}
    		if(data.offshoreRateHour == null && data.offshoreCostHour == null){
    			$scope.tempoffshoreMasterRate = 0;
    			$scope.tempOffshoreMasterRateGm = 0;
    		}
    		else{
    			$scope.tempoffshoreMasterRate = data.offshoreRateHour;
    			$scope.tempOffshoreMasterRateGm = data.offshoreCostHour;
    		}
	    	$scope.tableDetails.push({
	    		'RoleDesc': data.masterRoles.masterRoleLongDescription,
	    		'RoleDescLong':data.masterRoles.masterRoleLongDescription,
	    		'BandGrade':data.masterRoles.bandGrade,
	    		'CustRole':data.rateCardRoles.clientRole,
	    		"rcId":$localStorage.rcId,
	    		"rcRoleId": data.rcRoleId,
				"rcYear":data.rcYear,
				"rcCountryId":data.rcCountryId,
				"rcCityId":data.rcCityId,
				"rcCustRole":data.rateCardRoles.clientRole,
				"masterRoleId":data.masterRoles.masterRoleId,
	            'onsiteRateHour': $scope.tempOnsiteMasterRate,
	            'onsiteCostHour':$scope.tempOnsiteMasterRateGm,
	            'offshoreRateHour': $scope.tempoffshoreMasterRate,
	            'offshoreCostHour': $scope.tempOffshoreMasterRateGm,
	        });
    	}
    };
    $scope.insertContractorRole = function(){
    	var markers={
    			"rpContractorRoles":$scope.tableDetails
    	}
		console.log(markers);	
		var insertContractorRole = function(response) {
   	        BootstrapDialog.show({
   	        	title : 'Contractor/OtherRoles Role',
   	        	type : BootstrapDialog.TYPE_PRIMARY,
   	        	message : 'Saved SucessFully',
   	        	closable : false,
   	        	buttons : [{
   	        		label : 'OK',
   	        		action : function(dialogRef) {
   	        			dialogRef.close();
   	        			window.location = "RateCardContractorRole";
   	        		}
   	        	}]
   	        });
		}
		WebServiceFactory.insertContractorRole(markers).then(insertContractorRole);
    };
}]);





