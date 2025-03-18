app.controller("RateCardCreationGMRateController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
	$scope.marginCalculationMarker = [];
	$scope.yearDetails = [];
	var contextPath = "/RightPrice-DAS";
	$scope.isContractorRole = false;
	$scope.isUploaded = false;
	$scope.isAutomatic = false;
	$scope.isManual = false;
	$scope.isHybrid = false;
	$scope.manualRCData = 0;
	$scope.isManualRCID = 0;
	$scope.isMarginDisabled = false;
	$scope.yearDetailHide = false;
	$scope.isExist = false;
	$scope.tableIT = false;
	$scope.tableKPO = false;
	var isOffShoreCheck = null;
	$scope.isMarginHide = false;
	$scope.manualsidebar=false;
	$scope.autosidebar=false;
	$scope.hybridsidebar=false;
	var cityId = 0; 
	var userType = sessionStorage.getItem('userType');
		 console.log("The USer Value from the Session is........ "+ userType);
		 
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
				$scope.isManualRCID = $sessionStorage.manualRcID
				if($scope.isManualRCType == "A") {
					$scope.isAutomatic = true;
				} else if($scope.isManualRCType == "M") {
					$scope.isManual = true;
				} else if($scope.isManualRCType == "H") {
					$scope.isHybrid = true;
				}
				
			 if($scope.isAutomatic == true || $scope.isHybrid == true ||$scope.isManual == true) {
					window.location ='RateCardCreationSummary';
				}	
			};
	    $scope.Prev = function()
	    {   	
	    	$scope.persistantDataArray = $sessionStorage.roleSelectionArray; 
    		for(var i =0; i<$scope.persistantDataArray.length; i++){
    			if($scope.persistantDataArray[i].syntelRoleName == "Contractor"){
    				$scope.isContractorRole = true;
    			} 
    		}	
    		
    		$scope.isManualRCType = $sessionStorage.isManualRCType;
			if($scope.isManualRCType == "A") {
				$scope.isAutomatic = true;
			} else if($scope.isManualRCType == "M") {
				$scope.isManual = true;
			} else if($scope.isManualRCType == "H") {
				$scope.isHybrid = true;
			}
    		
			if($scope.isContractorRole == true && $scope.isManual == false) {
				window.location='RateCardContractorRole';
			} else if($scope.isAutomatic == true || $scope.isHybrid == true){
				window.location='RateCardCreationRoleUtilizationAndRates';
			} else if($scope.isManual == true) {
				window.location='RateCardCreationRoleSelection';
			}
	    };
	
	if($localStorage.rcId != 0 && $localStorage.rcId != undefined) {
		var getRateCardInfo = function(response) {
	  		$scope.rateCardInfo = response.data;
	  		console.log($scope.rateCardInfo);
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
	  		var currencyId = $scope.rateCardInfo[0].consolidatedRcCurrencyId;
	  		var statusId = $scope.rateCardInfo[0].currentApprovalStatus;
	  		$scope.onsiteHours = $scope.rateCardInfo[0].onsiteHoursPerDay;
	  		var offShoreHours = $scope.rateCardInfo[0].offshoreHoursPerDay;
	  		$scope.volumeDiscount = $scope.rateCardInfo[0].volumeDiscount;
	  		$scope.iqnCost = $scope.rateCardInfo[0].iqnCharges;
	  		$scope.cpcCost = $scope.rateCardInfo[0].cpcCharges;
	  		isOffShoreCheck = $scope.rateCardInfo[0].rateCardLocations[0].isOffShore;
	  		cityId = $scope.rateCardInfo[0].rateCardLocations[0].cityId;
	  		//$scope.currencyName = [];
	  		$scope.isItKpo = $scope.rateCardInfo[0].isItKpo;
	  		$sessionStorage.isManualRCType = $scope.rateCardInfo[0].isManualRc;
	  		
	  		if($scope.countryData != undefined) {
	  			angular.forEach($scope.countryData,function(value,key) {
	  				if($scope.countryData[key].currencyId == currencyId) {
	  					$scope.currencyName = $scope.countryData[key].currencyCode;
	  				}
	  			});
	  		}
	  		
	  /*		switch (currencyId) {
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
			case 12 : 
				$scope.currencyName = "PLN";
				break;
			default : 
	  			break;
			}*/
	  		
	  		// set the Status 
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
	  		
	  		if(userType == "GFT" && statusId == 7) {
	  			$scope.isDisabled=false;
	  		} else if(statusId != 1) {
	  			$scope.isDisabled=true;
	  		}
	  		
	  		if($scope.rateCardInfo[0].isItKpo == 1)
		    {
		    	$scope.tableIT = true;
		    	}
		    else
		    {
		    	$scope.tableKPO = true;
		    	}
	  		
	  		if($scope.isItKpo == 0 || $scope.isItKpo == 2)
	  		{
	  			$scope.getCalculationDetails(cityId);
	  		}
	  		
	  		
	  		if($sessionStorage.isManualRCType == "M")
			{
				$scope.manualsidebar=true;
				$scope.autosidebar=false;
				$scope.hybridsidebar=false;
			}
		else if ($sessionStorage.isManualRCType == "H")
			{
				$scope.hybridsidebar=true;
				$scope.manualsidebar=false;
				$scope.autosidebar=false;
			}
		else {
				$scope.autosidebar=true;
				$scope.hybridsidebar=false;
				$scope.manualsidebar=false;
			}
	  		
	  		$scope.getOnLoadDataRateCardGMRateDetails();
		};
		WebServiceFactory.getRateCardInfo($localStorage.rcId).then(getRateCardInfo);
		}
		else {
		 BootstrapDialog.show({
				title : 'Rate Card Details - Rate Card PM Details',
				type : BootstrapDialog.TYPE_DANGER,
				message : 'Please Create New Rate Card or Select Existing One.',
				closable : false,
				buttons : [ {
					label : 'OK',
					action : function(dialogRef) {
						dialogRef.close();
						window.location="RateCardCreationDetails";
					}
				} ]
			});
		}
	
	  var getCountryDetail = function(response) {
			$scope.countryData = response.data;
			console.log("$scope.countryData");
			console.log($scope.countryData);
		}
		WebServiceFactory.getCountryDetail().then(getCountryDetail);
	
		// getting the details of the Country on the basis of Country
		var getRateCardManualUploadCountry = function(response) {
			$scope.country = response.data;
		    console.log($scope.country.length);
		    $localStorage.firstCountry=$scope.country[0].countryId;
		    if($scope.country.length!=0){	    
		    if($scope.country.length == 1){
		    	$scope.getCity($scope.country[0].countryId);
		    	}
		    }else{
		    	BootstrapDialog.show({
		        	title : 'Rate Card Details - Rate Card PM Details',
		        	type : BootstrapDialog.TYPE_DANGER,
		        	message : 'Rate card Data is not submitted at previous pages.',
		        	closable : false,
		        	buttons : [{
		        		label : 'OK',
		        		action : function(dialogRef) {
		        			dialogRef.close();
		        		}
		        	}]
		        });
		    }
		};
	    WebServiceFactory.getRateCardManualUploadCountry($localStorage.rcId).then(getRateCardManualUploadCountry);
	    
	    
	    //Web Service to get City
	    $scope.getCity = function(countryId){
	    $scope.yearDetails = [];
	    $scope.versionAttachment = [];
	    $scope.manualRCData = 0;
	    /*var	countryId = $scope.frmRateCardGMCal.country.countryId;*/
	    	var getRateCardUploadCity = function(response) {
	    	    $scope.city = response.data;
	    	    console.log("City is");
	    	    console.log($scope.city);
	    	    $localStorage.firstCity=$scope.city[0].cityId;
	    	    $scope.frmRateCardGMCal.ddlCityModel=$scope.city[0].cityId;
	    	    console.log($scope.country);
	    	    if($scope.city != undefined) {
	    	    	$scope.cityAttachement($scope.city[0].cityId,$localStorage.rcId);
	    	    }
	    	    /*if(isOffShoreCheck == "Y"){
		  		
		  		}*/
	    	    if($scope.country.length == 1 && $scope.city.length == 1){
	    	    	$scope.frmRateCardGMCal.ddlCountryModel = $scope.country[0].countryId;
	    	    	$scope.frmRateCardGMCal.ddlCityModel = $scope.city[0].cityId
	    	    	$scope.getCalculationDetails($scope.frmRateCardGMCal.ddlCityModel)
	    	    }
	    	    
	    	    angular.forEach($scope.exchangeRate, function(value,key){
	    	    	if($scope.exchangeRate[key].countryId == countryId) {
	    	    		$scope.newExchangeRate = $scope.exchangeRate[key].currencyCode;
	    	    		console.log("Current Currency Code is........" + $scope.newExchangeRate)
	    	    	}
	    	    });
	    	    
	    	    
	    	    	
	    	    /*if($scope.isExist == false) {
    	    			$scope.country.push ({
    	    				countryId : 0,
    	    				countryName : "offshore"
    	    			});
	    	    }*/
	    	    
//	    	    if()
	    	};
	    	WebServiceFactory.getRateCardUploadCity($localStorage.rcId,countryId).then(getRateCardUploadCity);
	    };
	    
	    // calculating the margin
	    $scope.calculateMargin = function(index,blendedRate,blendedCost,year) {
	    	var marginBeforDiscountVal = (blendedRate-blendedCost)/blendedRate;
	    	console.log(marginBeforDiscountVal);
	    	var gmBeforeDiscount = (Math.round(marginBeforDiscountVal * 100)/100)*100;
	    	$scope.yearDetails[index].gmBeforeDiscount = $filter('number')(gmBeforeDiscount,2);
	    	
	    };
	    
	    $scope.calculateMarginAfterDisc = function(index,blendedRate,blendedCost) {
	    	var discountBlendedRate = blendedRate -(blendedRate * $scope.volumeDiscount/100);
	    	var finalBlendedCost = blendedCost + ( blendedRate * $scope.cpcCost/100) + ( blendedRate * $scope.iqnCost/100);
	    	var gmAfterDiscount =  ( discountBlendedRate - finalBlendedCost )/discountBlendedRate; 
	    	var roundedMarginAfterDisc = (Math.round(gmAfterDiscount * 100)/100)*100;
	    	$scope.yearDetails[index].gmAfterDiscount = $filter('number')(roundedMarginAfterDisc,2);
	    };
	    
	    
	    $scope.uploadData = function(frmManualUpload,frmRateCardGMCal){
			//alert("uploadData called");
	    	$scope.onUplaod= true;
			if(frmManualUpload.$valid && frmRateCardGMCal.$valid){
				$scope.onUplaod= false;
				//alert("inside valid");
				console.log("checking upload");
				$scope.uploadmanualRCFile();
			}
		};
		
		$scope.searchData = function(frmRateCardGMCal) {
			$scope.onSearch = true;
			if(frmRateCardGMCal.$valid){
				$scope.onSearch= false;
				//alert("inside valid");
				$scope.yearDetails = [];
				$scope.getCalculationDetails(frmRateCardGMCal.ddlCityModel);
			}
		};
		
	    // getting the rate card Details based on the RC_ID
	  
	    $scope.getCalculationDetails = function (cityId) {	    	
	    	var getCalculationDetails = function(response) {
	    		console.log("get Calculation detail");
	    		console.log(response);
	    		$scope.isMarginHide = true;
	    		var rcStartDate  = $scope.rateCardInfo[0].rcStartDate;
	    		console.log($scope.yearDetails);
	    		var startDateYear = parseInt(rcStartDate.substring(6,10));
	    		var rcEndDate  = $scope.rateCardInfo[0].rcEndDate;
	    		var endDateYear = rcEndDate.substring(6,10);
	    		var difference = endDateYear - startDateYear;
	    		$scope.isOffShoreCityId = $sessionStorage.isOffShoreCity;
	   	   		  if(cityId == $scope.isOffShoreCityId) {
	   	   			  $scope.isOffshoreDisable = true;
	   	   		  } else {
	   	   			  $scope.isOffshoreDisable = false;
	   	   		  }
	    		if(response.data != "") {
	    			$scope.yearDetails = response.data;
	    			$scope.baseYear = $scope.yearDetails[0].rcYear;
	    		}
	    		if(response.data  != "") {
	    			for(var i = 0;i<$scope.yearDetails.length;i++) {
	    			if(response.data[i].rcYear == startDateYear) {
	    				$scope.isExists = true; 
	    				break;
	    			} else {
	    				$scope.isExists = false;
	    			  }
	    			}
	    			
	    			if($scope.isExists == false) {
	    				$scope.yearDetails.push ({
	    					rcCardYoyId : response.data[0].rcCardYoyId-1,
	    					rcYear : parseInt(startDateYear),
	    					onsiteRateHour:0,
	    					onsiteCostHour:0,
	    					offshoreRateHour:0,
	    					offshoreCostHour:0,
	    					gmBeforeDiscount : 0,
	    					gmAfterDiscount : 0,
	    					onsiteMasterBlendedRate : 0,
	    					onsiteMasterBlendedCost : 0,
	    					offshoreMasterBlendedRate : 0,
	    					offshoreMasterBlendedCost : 0 
	    				})
	    			}
	    		}
	    		
		  		 if($scope.yearDetails.length == 0 && difference == 0) {
		  			 $scope.yearDetails.push ({
		 	  				rcYear : parseInt(startDateYear),
		 	  				onsiteRateHour:0,
		 	  				onsiteCostHour:0,
		 	  				offshoreRateHour:0,
	    					offshoreCostHour:0,
		 	  				gmBeforeDiscount : 0,
		 	  				gmAfterDiscount : 0,
		 	  				onsiteMasterBlendedRate : 0,
		 	  				onsiteMasterBlendedCost : 0,
		 	  				offshoreMasterBlendedRate : 0,
	    					offshoreMasterBlendedCost : 0
			  			 })
		  		 } else if($scope.yearDetails.length == 0 && difference == 1) {
		  			 $scope.yearDetails.push ({
		 	  				rcYear : parseInt(startDateYear),
		 	  				onsiteRateHour:0,
		 	  				onsiteCostHour:0,
		 	  				offshoreRateHour:0,
	    					offshoreCostHour:0,
		 	  				gmBeforeDiscount : 0,
		 	  				gmAfterDiscount : 0,
		 	  				onsiteMasterBlendedRate : 0,
		 	  				onsiteMasterBlendedCost : 0,
		 	  				offshoreMasterBlendedRate : 0,
	    					offshoreMasterBlendedCost : 0
			  			 })
		  		 }
		  		 
	    		angular.forEach($scope.yearDetails, function(value,key) {
	    			if($scope.yearDetails[key].rcYear == undefined) {
	    				$scope.yearDetails[key].rcYear = $scope.yearDetails[key].yoyIncYear;
	    				$scope.baseYear = $scope.yearDetails[0].rcYear;
	    			}
	    			if($scope.yearDetails[key].onsiteRateHour == undefined) {
	    				$scope.yearDetails[key].onsiteRateHour = 0
	    			}
	    			if($scope.yearDetails[key].onsiteCostHour == undefined) {
	    				$scope.yearDetails[key].onsiteCostHour = 0
	    			} else if($scope.yearDetails[key].onsiteCostHour != 0) {
	    				if($scope.isItKpo == 1)
	    		  		{
	    					$scope.calculateMargin(key,$scope.yearDetails[key].onsiteRateHour,$scope.yearDetails[key].onsiteCostHour);
		    				$scope.calculateMarginAfterDisc(key,$scope.yearDetails[key].onsiteRateHour,$scope.yearDetails[key].onsiteCostHour);
	    		  		}
	    				
	    			}
	    			
	    			if($scope.yearDetails[key].offshoreRateHour == undefined) {
	    				$scope.yearDetails[key].offshoreRateHour = 0;
	    			}
	    			
	    			if($scope.yearDetails[key].offshoreCostHour == undefined) {
	    				$scope.yearDetails[key].offshoreCostHour = 0;
	    			}
	    			
	    			if($scope.yearDetails[key].manualUploadId != undefined) {
	    				$scope.manualRCData = $scope.yearDetails[key].manualUploadId; 
	    				$sessionStorage.manualRcID = $scope.manualRCData;
            			if($scope.manualRCData > 0) {
            			  $("#manualAttachementFile").show();
            			  $("#manualAttachementFile").text("Download");
            			} else {
            				$("#manualAttachementFile").hide();
            			}
	    			} else {
        				$("#manualAttachementFile").hide();
        			}
	    			
	    			if($scope.yearDetails[key].onsiteMasterBlendedRate == undefined) {
	    				$scope.yearDetails[key].onsiteMasterBlendedRate = 0;
	    			} 
	    			if($scope.yearDetails[key].onsiteMasterBlendedCost == undefined) {
	    				$scope.yearDetails[key].onsiteMasterBlendedCost = 0;
	    			}
	    			
	    			if($scope.yearDetails[key].offshoreMasterBlendedRate == undefined) {
	    				$scope.yearDetails[key].offshoreMasterBlendedRate = 0;
	    			}
	    			
	    			if($scope.yearDetails[key].offshoreMasterBlendedCost == undefined) {
	    				$scope.yearDetails[key].offshoreMasterBlendedCost = 0;
	    			}
	    			
	    			
	    			if($scope.yearDetails[key].gmBeforeDiscount == undefined) {
	    				$scope.yearDetails[key].gmBeforeDiscount = 0;
	    			} 
	    			if($scope.yearDetails[key].gmAfterDiscount == undefined) {
	    				$scope.yearDetails[key].gmAfterDiscount = 0;
	    			}
	    			
 	    		});
	    		
	    		
	    		$scope.yearDetails = $filter('orderBy')($scope.yearDetails, 'rcYear');
	    		console.log("The Array after sorting.... ");
	    		console.log($scope.yearDetails);
	    		
	    		angular.forEach($scope.yearDetails,function(value,key) {
	    			$scope.baseYear = $scope.yearDetails[0].rcYear;
	    			$scope.yearDetails[key].yearDetailHide = false;
	    			if($scope.baseYear == $scope.yearDetails[key].rcYear) {
	    				$scope.yearDetails[key].yearDetailHide = true;
	    			}
	    		});
	    		
	    		$scope.cityAttachement(cityId,$localStorage.rcId);
	    	};
	    	WebServiceFactory.getCalculationDetails(cityId,$localStorage.rcId).then(getCalculationDetails);
	    };
	    	    
	   // Inserting the details
	   $scope.saveMarginDetails = function() {
		  $scope.countryId  = $scope.frmRateCardGMCal.ddlCountryModel;
		   $scope.cityId = $scope.frmRateCardGMCal.ddlCityModel;
		   if($scope.yearDetails.length != 0 ) 
		   {			   
			   for(var i=0;i<$scope.yearDetails.length;i++) 
			   {
				   /*console.log("----------------------"+$scope.onsiteUtilization);
				   if($scope.onsiteUtilization !=0 )
				   {
					   //check the condition wher Onsite Utilization not equal to 0
					   if($scope.yearDetails[i].onsiteRateHour!=0) 
					   {
						   $scope.marginCalculationMarker.push ({
							   "rcId" : $localStorage.rcId,
							   "rcYear" : $scope.yearDetails[i].rcYear,
							   "rcCountryId" : $scope.countryId,
							   "rcCityId" :  $scope.cityId,
							   "onsiteRateHour" : $scope.yearDetails[i].onsiteRateHour,
							   "onsiteCostHour" : $scope.yearDetails[i].onsiteCostHour,
							   "offshoreRateHour" : $scope.yearDetails[i].offshoreRateHour,
							   "offshoreCostHour" : $scope.yearDetails[i].offshoreCostHour,
							   "onsiteMasterBlendedRate" : $scope.yearDetails[i].onsiteMasterBlendedRate,
							   "onsiteMasterBlendedCost" : $scope.yearDetails[i].onsiteMasterBlendedCost,
							   "offshoreMasterBlendedRate" : $scope.yearDetails[i].offshoreMasterBlendedRate,
							   "offshoreMasterBlendedCost" : $scope.yearDetails[i].offshoreMasterBlendedCost,
							   "gmBeforeDiscount" : $scope.yearDetails[i].gmBeforeDiscount,
							   "gmAfterDiscount" : $scope.yearDetails[i].gmAfterDiscount,
						   });
					   }
					   
				   }
				   else
				   {
					   $scope.marginCalculationMarker.push ({
						   "rcId" : $localStorage.rcId,
						   "rcYear" : $scope.yearDetails[i].rcYear,
						   "rcCountryId" : $scope.countryId,
						   "rcCityId" :  $scope.cityId,
						   "onsiteRateHour" : $scope.yearDetails[i].onsiteRateHour,
						   "onsiteCostHour" : $scope.yearDetails[i].onsiteCostHour,
						   "offshoreRateHour" : $scope.yearDetails[i].offshoreRateHour,
						   "offshoreCostHour" : $scope.yearDetails[i].offshoreCostHour,
						   "onsiteMasterBlendedRate" : $scope.yearDetails[i].onsiteMasterBlendedRate,
						   "onsiteMasterBlendedCost" : $scope.yearDetails[i].onsiteMasterBlendedCost,
						   "offshoreMasterBlendedRate" : $scope.yearDetails[i].offshoreMasterBlendedRate,
						   "offshoreMasterBlendedCost" : $scope.yearDetails[i].offshoreMasterBlendedCost,
						   "gmBeforeDiscount" : $scope.yearDetails[i].gmBeforeDiscount,
						   "gmAfterDiscount" : $scope.yearDetails[i].gmAfterDiscount,
					   });   
				   }*/
				   
				   $scope.marginCalculationMarker.push ({
					   "rcId" : $localStorage.rcId,
					   "rcYear" : $scope.yearDetails[i].rcYear,
					   "rcCountryId" : $scope.countryId,
					   "rcCityId" :  $scope.cityId,
					   "onsiteRateHour" : $scope.yearDetails[i].onsiteRateHour,
					   "onsiteCostHour" : $scope.yearDetails[i].onsiteCostHour,
					   "offshoreRateHour" : $scope.yearDetails[i].offshoreRateHour,
					   "offshoreCostHour" : $scope.yearDetails[i].offshoreCostHour,
					   "onsiteMasterBlendedRate" : $scope.yearDetails[i].onsiteMasterBlendedRate,
					   "onsiteMasterBlendedCost" : $scope.yearDetails[i].onsiteMasterBlendedCost,
					   "offshoreMasterBlendedRate" : $scope.yearDetails[i].offshoreMasterBlendedRate,
					   "offshoreMasterBlendedCost" : $scope.yearDetails[i].offshoreMasterBlendedCost,
					   "gmBeforeDiscount" : $scope.yearDetails[i].gmBeforeDiscount,
					   "gmAfterDiscount" : $scope.yearDetails[i].gmAfterDiscount,
				   });				  
			   }
		    	$http({
					 method: 'POST',
					 url: contextPath+"/RightPrice-DAS/saveCalculatedMargin",
					 dataType: 'json',
					 data: angular.toJson($scope.marginCalculationMarker),  
		            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    	}).
		    	then(function(data) 
				{
		    		console.log("Inserted Successfully");
		    		if(data.status == 200)
		    		{
		    			var message = data.data;
		    			BootstrapDialog.show(
						{
							title : 'Rate Card Details - Rate Card PM Details',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : message,
							closable : false,
							buttons : [{
								label : 'OK',
								action : function(dialogRef) 
								{
									dialogRef.close();
									window.location = "RateCardCreationGMRateDetails";
								}
		   	        		}]
		    			});
		    		}
		    		else if(data.status == 204)
		    		{
		    			var message = data.data;
		    			BootstrapDialog.show(
						{
							title : 'Rate Card Details - Rate Card PM Details',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : message,
							closable : false,
							buttons : [{
								label : 'OK',
								action : function(dialogRef) 
								{
									dialogRef.close();
									window.location = "RateCardCreationGMRateDetails";
								}
		   	        		}]
		    			});
		    		}
		    		else 
		    		{		    			
		    			message = data.data;
		    			BootstrapDialog.show(
						{
		    	        	title : 'Rate Card Details - Rate Card PM Details',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : 'Currently facing technical issue',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        		}
		    	        	}]
		    	        });
		    		}
		    	},function (data) {
					$scope.displayres = data;
				    $scope.answer = 'Posting data was unsuccessful.';
				});
		   }
		   else 
		   {
			   BootstrapDialog.show(
			   {
   	        		title : 'Rate Card Details - Rate Card PM Details',
   	        		type : BootstrapDialog.TYPE_DANGER,
   	        		message : "Please Fill the details and save the details.",
   	        		closable : false,
   	        		buttons : [{
	   	        		label : 'OK',
	   	        		action : function(dialogRef) {
	   	        			dialogRef.close();
	   	        		}
   	        		}]
   	        	});
		   }
	 };
		 
		 //Uploading file for Manual Rate card
		 $scope.uploadmanualRCFile = function(){
		    	var fileTest = $("#fuUploadFilename");	
		    	var name = "Template";
		    	var tempID = 1;	
				var file = $('input[name="fuUploadFilename"]').get(0).files[0];
				var formData = new FormData();
				formData.append('file', file);
				formData.append('name', name);
				formData.append('TempId',tempID);
				formData.append('rpDealVersionId', $localStorage.rcId);
				formData.append('dealAutoTowerId',$scope.frmRateCardGMCal.ddlCityModel);
				formData.append('docType',0);
				formData.append('countryId', $scope.frmRateCardGMCal.ddlCountryModel);
		    	console.log("main method");
		    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadManualRCFile";
	            $http.post(uploadUrl, formData, {
	                transformRequest: angular.identity,
	                headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
	            }).
			    then(function(data) {
			    	var obj = JSON.stringify(data);
					var json = JSON.parse(obj);
					var customMessage = data.data;
			    	if(data.status == 200)	
					{
						BootstrapDialog.show({
							title : 'Rate Card Details - Rate Card PM Details',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : customMessage,
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(
										dialogRef) {
									dialogRef.close();
									window.location = "RateCardCreationGMRateDetails";
								}
							} ]
						});	
					} 
			    	else if(data.status == 205){
							BootstrapDialog.show({
							title : 'Rate Card Details - Rate Card PM Details',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Currently We are facing technical issues, please try again later.",
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								}
							} ]
						});
					}
					else {
						BootstrapDialog.show({
						title : 'Rate Card Details - Rate Card PM Details',
						type : BootstrapDialog.TYPE_DANGER,
						message : customMessage,
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
				}
			    },function(data) {
			        $scope.displayres = data.data;
			        $scope.answer = 'Posting data was unsuccessful.';
			});
		 };
		 
		  // Download the Existing File
         $scope.downloadFile = function(objectId){
     		WebServiceFactory.downloadFileFormID(objectId);
     	};
     	
     	 //Web Service to get ExchangeRate
     		var getExchangeRate = function(response) {
     			var a=$scope.currencyName;
     			var countryModel=$scope.frmRateCardGMCal.ddlCountryModel;
     			$scope.exchangeRate = response.data;
     			console.log("$scope.exchangeRate");
     			console.log($scope.exchangeRate);
     	}
     WebServiceFactory.getExchangeRate($localStorage.rcId).then(getExchangeRate);

    $scope.cityAttachement = function(cityId,rcId) {
    	
    	var cityAttachement =function(response)
    	{
    		console.log("Attachment Data");
    		console.log(response);
    		$scope.versionAttachment=response.data;
    		if($scope.versionAttachment!=undefined)
    		{
    			
    			$filter('orderBy')($scope.versionAttachment, 'createdOn');
    			angular.forEach($scope.versionAttachment, function(value, key) {
    				angular.forEach($scope.towerdetails, function(value2, key2) {
    					if($scope.versionAttachment[key].dealAutoTowerId==$scope.towerdetails[key2].dealautoTowerId)
    					{
    						$scope.versionAttachment[key].towerName=value2.towerName;
    					}
    				});
    			});
    			
    			angular.forEach($scope.versionAttachment, function(value, key) {
    					if($scope.versionAttachment[key].docType != undefined) {
    						var strMain =$scope.versionAttachment[key].createdOn;
    						var arrSplit = [];
    						arrSplit = strMain.split(" ");
    						$scope.versionAttachment[key].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
    					}
    			});
    			
    		}
    		
    	};
    	WebServiceFactory.cityAttachement(cityId,$localStorage.rcId).then(cityAttachement);
    }
    
	$scope.downloadFileWithFileName = function(dealAttachmentId){
		WebServiceFactory.downloadFileWithFileName(dealAttachmentId);
	};
	
	$scope.deleteManualFile = function(attachementId){
		var deleteManualFile = function(response) {
			$scope.deleteResponse = response;
			
			if($scope.deleteResponse.status == 200){
				$scope.message = $scope.deleteResponse.data;
    	        BootstrapDialog.show({
    	        	title : 'Rate Card Creation - PM Rate Details',
    	        	type : BootstrapDialog.TYPE_PRIMARY,
    	        	message : $scope.message,
    	        	closable : false,
    	        	buttons : [{
    	        		label : 'OK',
    	        		action : function(dialogRef) {
    	        			dialogRef.close();
    	        			window.location = "RateCardCreationGMRateDetails";
    	        		}
    	        	}]
    	        });
    		}else{
    			$scope.message = $scope.deleteResponse.data;
    			BootstrapDialog.show({
    	        	title : 'Rate Card Creation - PM Rate Details',
    	        	type : BootstrapDialog.TYPE_DANGER,
    	        	message : $scope.message,
    	        	closable : false,
    	        	buttons : [{
    	        		label : 'OK',
    	        		action : function(dialogRef) {
    	        			dialogRef.close();
    	        		}
    	        	}]
    	        });
    		}
		}
		WebServiceFactory.deleteManualFile(attachementId).then(deleteManualFile);
	};
     
	
	$scope.getOnLoadDataRateCardGMRateDetails = function()
	{
		$scope.getCity($localStorage.firstCountry);
		$scope.frmRateCardGMCal.ddlCountryModel=$localStorage.firstCountry;
		$scope.frmRateCardGMCal.ddlCityModel=$localStorage.firstCity;
		$scope.getCalculationDetails($localStorage.firstCity);
		
	};
	/*	$scope.getGMRate = function(user){
			$scope.getCalculationDetails(frmRateCardGMCal.ddlCityModel);
			$scope.getCalculationDetails = function(cityId){
				var getCalculationDetails = function(response){
		//alert("get summary");
		console.log("get summary");
		console.log(response);
		$scope.roleGMRateDetail = response.data;
		console.log("summaryDetails");
		if(response.status == 204 && rcId!=0){
			//alert("No data found");
			BootstrapDialog.show({
		    	title : 'Rate Card Creation - Summary',
		    	type : BootstrapDialog.TYPE_DANGER,
		    	message : 'No data found',
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
		$scope.user = user;
		
		};
		
		WebServiceFactory.getCalculationDetails($scope.frmRateCardGMCal.ddlCityModel,$localStorage.rcId).then(getCalculationDetails);
		}
			};*/
}]);
