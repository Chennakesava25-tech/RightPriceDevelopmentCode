//var app = angular.module('RightPriceApp', ['ngStorage', 'ngMessages']);
//var app = angular.module('RightPriceApp', ['ngStorage']);

app.controller("RoleUtilizationAndRatesController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage','$filter', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,$localStorage, $sessionStorage,$index) {
	var contextPath = "/RightPrice-DAS";
	$scope.roleUtilizationAndRates = {};
	$scope.RoleUtilizationHidden = true;
	$scope.isOnsiteUtilization = false;
	$scope.isOffshoreUtilization = false;
	$scope.isGermanyRC = false;
	$scope.tablehide=false;
	$scope.uploadBtnDisable = true;
	$scope.isContractorRole = false;
	$scope.isManualRC = false;
	$scope.isOffshoreDisable = false;
	$scope.isOnsitePercentage = false;
	$scope.isOffshorePercentage = false;
	$scope.MDAttachementData=[];
	sessionStorage.dataSaved = null;
	$scope.index=0;
	var userType = sessionStorage.getItem('userType');
	 console.log("The USer Value from the Session is........ "+ userType);
	 
	//$scope.currencyName = [];
	window.onload = function() {
		  var getCountryDetail = function(response) {
				$scope.countryData = response.data;
				console.log("$scope.countryData");
				console.log($scope.countryData);
			}
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
	}
	
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
		
		if($scope.isAutomatic == true && $scope.isContractorRole == true){
			window.location ='RateCardContractorRole';
		} else if($scope.isManual == true || $scope.isHybrid == true){
			window.location='RateCardCreationGMRateDetails';
		} else if($scope.isAutomatic == true) {
			window.location='RateCardCreationSummary';
		}
    }; 
    
    $scope.Prev = function()
    {   	
    	$scope.persistantDataArray = $sessionStorage.roleSelectionArray; 
    		for(var i =0; i<$scope.persistantDataArray.length; i++){
    			if($scope.persistantDataArray[i].syntelRoleName == "Contractor/Other Role"){
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
			} else if($scope.isAutomatic == true && $scope.isContractorRole == true){
				window.location ='RateCardContractorRole';
			} else if($scope.isManual == true || $scope.isHybrid == true || $scope.isAutomatic == true){
				window.location='RateCardCreationRoleSelection';
			}
    };

/*	$scope.exportToExcel=function(tableId)
	{ // ex: '#my-table'
	    
	    var exportHref=WebServiceFactory.tableToExcelRate(tableId,'RateCardUtilization','Rate card id -Year');
	}*/

	$scope.exportToExcel = function(isBillCurrency)
	{
		var cityId = $scope.roleUtilizationAndRates.ddlCityModel;
		var rcId= $localStorage.rcId; 
		var multifier = $scope.multiply;
		var currencyName = $scope.currencyName;
		//window.location= contextPath+"/RightPrice-DAS/downloadUtilizationRoleExcel/"+cityId+"/"+rcId+"/"+isBillCurrency+"/"+ multifier;
		window.location= contextPath+"/RightPrice-DAS/downloadUtilizationRoleExcel/"+cityId+"/"+rcId+"/"+multifier+"/"+ isBillCurrency+"/"+ currencyName;
		
		/*$http({
			 method: 'POST',
			 url: contextPath+"/RightPrice-DAS/downloadUtilizationRoleExcel",
			 dataType: 'json',
			 data: angular.toJson($scope.dataList),  
	       headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		}).
	    then(function(data) 
		{
	    	console.log("data.data[0]");
	    	console.log(data);
	    	var obj = JSON.stringify(data);
			var json = JSON.parse(obj);
			var customMessage = data.data;
	    	if(data.status == 200)	
			{
				//alert("Done");
				
				 const url = window.URL.createObjectURL(new Blob([data.data]));
		            const link = document.createElement('a');
		            link.href = url;
		            link.setAttribute('download', 'test.xls');
		            document.body.appendChild(link);
		            link.click();
		            document.body.removeChild(link);
			} 	    	
			else if(data.status == 205) 
			{
				BootstrapDialog.show(
				{
					title : 'Rate Card Creation Role Utilization And Rates',
					type : BootstrapDialog.TYPE_DANGER,
					message : customMessage,
					closable : false,
					buttons : [
					{
						label : 'OK',
						action : function(dialogRef) 
						{
							dialogRef.close();
							window.location = "RateCardCreationRoleUtilizationAndRates";
						}
					}]
				});
			}
			else 
			{
				BootstrapDialog.show(
				{
					title : 'Rate Card Creation Role Utilization And Rates',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Currently We are facing technical issues, please try again later.",
					closable : false,
					buttons : [
					{
						label : 'OK',
						action : function(dialogRef) 
						{
							dialogRef.close();
							window.location = "RateCardCreationRoleUtilizationAndRates";
						}
					}]
				});
			}
	    },function(data) 
	    {
	        $scope.displayres = data.data;
	        $scope.answer = 'Posting data was unsuccessful.';
	    });*/
	};
	
    $scope.onsiteIncrementPercent=[];
    //$localStorage.rcId = 7;
    // Get the Rate card info using ratecard id
    $scope.isDisabled = false;
    $scope.isSaveDisabled = false;
    if($localStorage.tempAction == 3){
    	$scope.isDisabled = true;
    	$scope.isSaveDisabled = true;
    	$scope.isOnsiteUtilization = true;
    	$scope.isOffshoreUtilization = true;
    }
    else{
    	$scope.isDisabled = false;
    	$scope.isSaveDisabled = false;
    	$scope.isOnsiteUtilization = false;
    	$scope.isOffshoreUtilization = false;
    }
    if($localStorage.rcId != 0 && $localStorage.rcId != undefined) {
		var getRateCardInfo = function(response) {
	  		$scope.rateCardInfo = response.data;
	  		console.log("RC_Details");
	  		console.log($scope.rateCardInfo);
	  		
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
	  			$scope.isOnsitePercentage = true;
	  		}
	  		if($scope.offshoreUtilization == 0){
	  			$scope.isOffshorePercentage = true;
	  		}
	  		$scope.isOffShoreCityList = $sessionStorage.isOffShoreCityList;
	  		console.log("The Off SHore City is......... "+ 209);
	  		console.log($sessionStorage.isOffShoreCityList);
	  		var currencyId = $scope.rateCardInfo[0].consolidatedRcCurrencyId;
	  		var statusId = $scope.rateCardInfo[0].currentApprovalStatus;
	  		$sessionStorage.isManualRCType = $scope.rateCardInfo[0].isManualRc;
	  		
	  		 var getCountryDetail = function(response) {
	  			$scope.countryData = response.data;
	  			console.log("$scope.countryData");
	  			console.log($scope.countryData);
	  			angular.forEach($scope.countryData,function(value,key) {
	  				if($scope.countryData[key].currencyId == currencyId) {
	  					$scope.currencyName = $scope.countryData[key].currencyCode;
	  				}
	  			});
	  		}
	  		WebServiceFactory.getCountryDetail().then(getCountryDetail);
	  		
	  		
	  		
	  		/*switch (currencyId) {
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
	  		
	  	if(statusId == 4 ||  statusId == 1) {
	  		$scope.isDisabled=false;
	  		$scope.isSaveDisabled = false;
		  	}
	  		else 
	  		{
	  		$scope.isDisabled=true;
	  		$scope.isSaveDisabled = true;
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
	    console.log($scope.country.length);
	    console.log($scope.country)
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
           
    
    //Web Service to get ExchangeRate
    var getExchangeRate = function(response) {
    	 var a=$scope.currencyName;
    	 var b=roleUtilizationAndRates.ddlCountryModel;
    	 console.log(b+" Called Exchange rates"+a);
	    $scope.exchangeRate = response.data;
	    console.log("Called Exchange rates"+$scope.exchangeRate.length);
	    console.log($scope.exchangeRate.length);
	    
	};
    WebServiceFactory.getExchangeRate($localStorage.rcId).then(getExchangeRate);
    
    
  //Web Service to get City
    $scope.getCity = function(countryId){
    	var getRateUtilizationCity = function(response) {
    		var b= $scope.getMultiplyFactor(countryId);
    		if(countryId!=undefined){
        		$scope.getDisabledCountry(countryId);
        		}    
    	    $scope.city = response.data;
    	    if($scope.country.length == 1 && $scope.city.length == 1){
    	    	$scope.roleUtilizationAndRates.ddlCountryModel = $scope.country[0].countryId;
    	    	$scope.roleUtilizationAndRates.ddlCityModel = $scope.city[0].cityId;
    	    	$scope.getRateUtilizationData($scope.roleUtilizationAndRates.ddlCityModel);
    	    }
    	   // disable the offshore on the basis of the city
    	};
    	WebServiceFactory.getRateUtilizationCity($localStorage.rcId,countryId).then(getRateUtilizationCity);
    }
    
    $scope.tableData = [];
    
 $scope.getMultiplyFactor = function(countryId){
    	
    	console.log($scope.currencyName+"  ********* "+countryId);
    	console.log( $scope.exchangeRate);
    	$scope.multiply=1;
    	var currentCurrency="";
    	var newCurrency="";
    	$scope.currentCurrencyCode="";
    	$scope.newCurrencyCode="";
    	
    	for(var i=0;i<$scope.exchangeRate.length;i++)
    		{
    		if($scope.exchangeRate[i].countryId==countryId)
    			{
    			
    			currentCurrency=$scope.exchangeRate[i].exchangeRate;
    			$scope.currentCurrencyCode=$scope.exchangeRate[i].currencyCode;
    			console.log(countryId+" :::: Currency::::: "+currentCurrency+" ::::::"+$scope.exchangeRate[i].countryName);
    			}
    		if($scope.exchangeRate[i].currencyCode==$scope.currencyName)
    			{
    			newCurrency=$scope.exchangeRate[i].exchangeRate;
    			$scope.newCurrencyCode=$scope.exchangeRate[i].currencyCode;
    			console.log($scope.currencyName+" :::: NewCurrency::::: "+newCurrency+" ::::::"+$scope.exchangeRate[i].countryName);
    			}
    		}
    	
    	$scope.multiply=currentCurrency/newCurrency;
    	console.log($scope.currentCurrencyCode+" CurrentCurrncy::: "+currentCurrency+"::::: "+$scope.newCurrencyCode+" NewCurrncy::: "+newCurrency+"  :::::::: "+$scope.multiply)
    }
    //add data
    $scope.addFlag = function(){
    	angular.forEach($scope.dataList, function (value, key) {
    		$scope.dataList[key].localFlag = 0;
    		$scope.dataList[key].localUsageFlag = 0;
    		$scope.dataList[key].localProposedFlag = 0;
    		$scope.dataList[key].onsiteUsageFlag = 0;
    		$scope.dataList[key].onsiteProposedFlag = 0;
    		$scope.dataList[key].offshoreUsageFlag = 0;
    		$scope.dataList[key].offshoreProposedFlag = 0;
    		$scope.dataList[key].offshoreGmFlag = 0;
    	});
    };
    
    
    //Web Service to get data for table
    $scope.getRateUtilizationData = function(cityId){
    	$scope.isOffshoreUtilization = false;
    	$scope.isOffshoreDisable = false;
    	var getRateUtilization = function(response) {
    		console.log(response);
    		  /*$scope.isOffShoreCityList = $sessionStorage.isOffShoreCity;*/
    		for(var i=0;i<$scope.isOffShoreCityList.length;i++) {
    			if(cityId == $scope.isOffShoreCityList[i].offshoreCity) {
    				$scope.isOffshoreDisable = true;
    			}
    		}
    		  if($scope.isOffshoreDisable == true) {
  				$scope.isOffshoreUtilization = true;
  			} else {
  				$scope.isOffshoreUtilization = false;
  			}
    	    $scope.yearList = response.data.yearData;
    	    $scope.dataList = response.data.dataList;
    	    $scope.tempYearList = [];
    	    angular.forEach($scope.yearList, function(value, key){
    	    	$scope.tempYearList.push(value[0]);
    	     });
    	   // $scope.yearList = $scope.tempYearList;
    	    
    	    //add flags to json object
    	    $scope.addFlag();
    	    
    	    $scope.getSubTotalArray();
    	    
    	    $scope.setIncrementPercentage(response.data.yearData);
    	    $scope.setIncrementOffShorePercentage(response.data.yearData);
    	    $scope.yearData($scope.tempYearList[0]);
    	    
    	};
    	WebServiceFactory.getRateUtilization(cityId,$localStorage.rcId).then(getRateUtilization);
    };
    
    $scope.subTotalArray=[];
    //function to get sub total array
    $scope.getSubTotalArray = function(){
    	for(var i=0;i<$scope.dataList.length;i++){
    		if($scope.dataList[i].masterRoleId == null){
				var subTotalObject = $scope.dataList.splice(i,1);
				$scope.subTotalArray.push(subTotalObject[0]);
				i--;
    		}
    	}
    }
    
    //function to get year wise data
    $scope.yearData = function(year){
    	//empty array of table on function call
    	$scope.tableDetails=[];
    	
    	//setting current year
    	$scope.currentYear = year;
    	
    	//start date year..
    	// compare the start date with the year then we have to disable the array elements
    	// commented code as per requirement please dont remove it.(Requirement can change any time)
    	/*var startDate = $scope.rateCardInfo[0].rcStartDate;
    	var yearStart   = parseInt(startDate.substring(6,10));
    	console.log(yearStart);
    	if(yearStart < year) {
    		$scope.isOnsiteUtilization = true;
    		$scope.isOffshoreUtilization = true;
    	} else if(yearStart == year) {
    		if($scope.isOffshoreDisable == true) {
    			$scope.isOnsiteUtilization = false;
    			$scope.isOffshoreUtilization = true;
    		} else {
    			$scope.isOnsiteUtilization = false;
    			$scope.isOffshoreUtilization = false;
    		}
    		
    		if($scope.isOnsitePercentage == true) {
    			$scope.isOnsiteUtilization = true;
    			if($scope.isOffshoreDisable == true) {
    				$scope.isOffshoreUtilization = true;
    			}
    		} 
    	} */
    	
    	if($scope.isOnsitePercentage == true) {
			$scope.isOnsiteUtilization = true;
    	} else {
    		$scope.isOnsiteUtilization = false;
    	}
    	
    	if($scope.isOffshorePercentage == true) {
    		$scope.isOffshoreUtilization = true;
    	} else if($scope.isOffshoreUtilization != false){
    		$scope.isOffshoreUtilization = true;
    	} else {
    		$scope.isOffshoreUtilization = false;
    	}
    	
    	
    	
    	//create row for table
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			$scope.createRow($scope.dataList[key]);
    		}
    	});
    	
    	//calculate on site usage sub total
    	$scope.calculateSubTotal(year);
    	
    	//calculate on site master rate sub total
    	$scope.calculateOnsiteMasterRateSubTotal(year);
    	$scope.calculateOnsiteProposedRateSubTotal(year);
    	$scope.getTotalOnsiteGmPercent(year);
    	$scope.calculateDiscountOnLoad(year);
    	
    	$scope.calculateOffshoreGMPercentSubTotal(year);
    	
    };
    $scope.tableDetails=[];
    
    //function to create year wise row
    
    
    $scope.createRow = function(data){
    	if(data.masterRoleId){
    		//for offshore GM percent
    		if($filter('number')(data.offshoreActualGmPercent,2) == 0){
    				$scope.tempOffshoreGmPercent = $filter('number')(data.offshoreMasterRateGmPercent,2);
    		}
    		else{
    				$scope.tempOffshoreGmPercent = $filter('number')(data.offshoreActualGmPercent,2);
    		}
    		
    		//for onsite GM percent
    		if($filter('number')(data.onsiteActualGmPercent,2) == 0){
    				$scope.tempOnsiteGMPercent = $filter('number')(data.onsiteMasterGmPercent,2);
    		}
    		else{
    				$scope.tempOnsiteGMPercent = $filter('number')(data.onsiteActualGmPercent,2);
    		}
    		
    		
    		
    		if($scope.onsiteUtilization == 0){
    			$scope.tempLocalUtilization = 0;
    			$scope.tempDeputedUtilization = 0;
    			$scope.tempOnsiteUsage = 0;
    			$scope.tempOnsiteMasterRate = 0;
    			$scope.tempOnsiteMasterRateGm = 0;
    			$scope.tempOnsiteProposedRate = 0;
    			$scope.tempOnsiteGMPercent = 0;
    			$scope.roleUtilizationAndRates.totalOnsiteMasterRate = 0;
    			$scope.roleUtilizationAndRates.totalOnsiteProposedRate = 0;
    			
    		}
    		else{
    			$scope.tempLocalUtiDescription = data.domesticUsagePercent;
    			$scope.tempDeputedUtilization = data.deputedUsagePercent;
    			$scope.tempOnsiteUsage = data.onsiteUsage;
    			
    			$scope.tempOnsiteMasterRate = $filter('number')(data.onsiteMasterRate,2);
    			$scope.tempOnsiteMasterRateGm = $filter('number')(data.onsiteMasterGmPercent,2);
    			$scope.tempOnsiteProposedRate = data.onsiteProposedClientRate;
    		}
    		
    		if($scope.offshoreUtilization == 0){
    			$scope.tempOffshoreUsage = 0;
    			$scope.roleUtilizationAndRates.totalOffshoreUsage = 0;
    			$scope.tempoffshoreMasterRate = 0;
    			$scope.tempOffshoreMasterRateGm = 0;
    			$scope.tempProposedClientRate = 0;
    			$scope.tempOffshoreGmPercent = 0;
    			$scope.roleUtilizationAndRates.totalOffshoreMasterRate = 0;
    			$scope.roleUtilizationAndRates.totalOffshoreMasterGMPercent = 0;
    			$scope.roleUtilizationAndRates.totalOffshoreProposedRate = 0;
    			$scope.roleUtilizationAndRates.totalOffshoreGMPercent = 0
    		}
    		else{
    			$scope.tempOffshoreUsage = data.offshoreUsage;
    			$scope.tempoffshoreMasterRate = data.offshoreMasterRate;
    			$scope.tempOffshoreMasterRateGm = $filter('number')(data.offshoreMasterRateGmPercent,2);
    			$scope.tempProposedClientRate = data.offshoreProposedClientRate;
    		}
	    	$scope.tableDetails.push({
	    		'MasterRoleId' : data.masterRoles.masterRoleName,
	    		'RoleDesc': data.masterRoles.masterRoleLongDescription,
	    		'RoleDescLong':data.masterRoles.masterRoleLongDescription,
	    		'BandGrade':data.masterRoles.bandGrade,
	    		'gcmCODE':data.masterRoles.gcmCODE,
	    		'CustRole':data.rateCardRoles.clientRole,
	        	'LocalUti': $scope.tempLocalUtiDescription,
	        	'DepuUti'  : $scope.tempDeputedUtilization,
	        	'OnsiteUsa': $scope.tempOnsiteUsage,
	            'OnsiteMasRate': $scope.tempOnsiteMasterRate,
	            'OnsiteMasterRateGM':$scope.calculateGMValueOnload(data,$scope.tempLocalUtiDescription),
	            'OnsitePro': $scope.tempOnsiteProposedRate,
	            'OnsiteDisPremium': $scope.calculateDiscountOnLoad(data.onsiteProposedClientRate,data.onsiteMasterRate),
	            'OnsiteGmPer': $scope.getActualGmPercentOnload(data,data.onsiteProposedClientRate),
	            'OffshoreUsa':$scope.tempOffshoreUsage,
	            'OffshoreMasRate': Math.round($scope.tempoffshoreMasterRate * 100)/100,
	            'OffshoreMasterRateGM': $scope.tempOffshoreMasterRateGm,
	            'OffshorePro': $scope.tempProposedClientRate,
	            'OffshoreDisPremium':$scope.calculateOffShoreDiscountOnLoad(data.offshoreProposedClientRate,data.offshoreMasterRate),
	            'OffshoreGmPer': $scope.getActualGmPercentOffshoreOnload(data,data.offshoreProposedClientRate),
	            'SubPracticeName':data.masterRoles.masterSubPractice.subpracticeName,
	            'PracticeName': data.masterRoles.masterSubPractice.masterPractice.description
	        });
    	}
    };
    
    //calculate sub total master rate sub total
    $scope.calculateOnsiteMasterRateSubTotal = function(year){
    	var totalOnsiteMasterRate = 0;
    	var totalOffshoreMasterRate = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			totalOnsiteMasterRate += ((parseFloat($scope.dataList[key].onsiteMasterRate)*parseFloat($scope.dataList[key].onsiteUsage))/100);
    			totalOffshoreMasterRate += ((parseFloat($scope.dataList[key].offshoreMasterRate)*parseFloat($scope.dataList[key].offshoreUsage))/100);
    		}
    	});
    	if($scope.onsiteUtilization > 0){
    	$scope.roleUtilizationAndRates.totalOnsiteMasterRate = Math.round(totalOnsiteMasterRate * 100)/100;
    	}
    	if($scope.offshoreUtilization > 0){
    	$scope.roleUtilizationAndRates.totalOffshoreMasterRate = Math.round(totalOffshoreMasterRate * 100)/100;
    	}
    	//$scope.roleUtilizationAndRates.totalOnsiteMasterRate = totalOnsiteMasterRate/$scope.tableDetails.length;
    //	$scope.roleUtilizationAndRates.totalOffshoreMasterRate = totalOffshoreMasterRate/$scope.tableDetails.length;
    	
    	//update value of sub total master rate in sub total array
    	//$scope.updateOnsiteMaster(year,$scope.roleUtilizationAndRates.totalOnsiteMasterRate,$scope.roleUtilizationAndRates.totalOffshoreMasterRate);
    };
    
    //calculate proposed client rate on load
    $scope.calculateOnsiteProposedRateSubTotal = function(year){
    	var totalOnsiteProposedRate = 0;
    	var totalOffshoreProposedRate = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			totalOnsiteProposedRate += (parseFloat($scope.dataList[key].onsiteProposedClientRate)*parseFloat($scope.dataList[key].onsiteUsage/100));
    			totalOffshoreProposedRate += (parseFloat($scope.dataList[key].offshoreProposedClientRate)*parseFloat($scope.dataList[key].offshoreUsage/100));
    		}
    	});
    	if($scope.onsiteUtilization > 0){
    	$scope.roleUtilizationAndRates.totalOnsiteProposedRate = Math.round(totalOnsiteProposedRate * 100)/100;
    	}
    	if($scope.offshoreUtilization > 0){
        $scope.roleUtilizationAndRates.totalOffshoreProposedRate = Math.round(totalOffshoreProposedRate * 100)/100;
    	}
    	//$scope.roleUtilizationAndRates.totalOnsiteProposedRate = totalOnsiteProposedRate/$scope.tableDetails.length;
    //	$scope.roleUtilizationAndRates.totalOffshoreProposedRate = totalOffshoreProposedRate/$scope.tableDetails.length;
    	
    	//update data of onsite and offshore of sub total
    	$scope.updateOnsiteProposedSubTotal($scope.roleUtilizationAndRates.totalOnsiteProposedRate);
    	$scope.updateOffshoreProposedSubTotal($scope.roleUtilizationAndRates.totalOffshoreProposedRate );
    	
    	$scope.calculateOnsiteDiscount(year);
    	$scope.calculateOffshoreDiscount(year);
    };
    
    
    $scope.calculateOnsiteDiscount = function(year){
    	var onsiteDiscountTotal = 0;
    	var onsiteMasterRateTotal = 0;
    	var onsiteProposedRate = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			onsiteMasterRateTotal += ((parseFloat($scope.dataList[key].onsiteMasterRate)*parseFloat($scope.dataList[key].onsiteUsage))/100);
    			onsiteProposedRate += (parseFloat($scope.dataList[key].onsiteProposedClientRate)*parseFloat($scope.dataList[key].onsiteUsage/100));
    		}
    	});
    	onsiteDiscountTotal = Math.round(-(((onsiteMasterRateTotal - onsiteProposedRate)/onsiteMasterRateTotal)*100)*100)/100;
    	$scope.roleUtilizationAndRates.totalOnsiteDiscount = onsiteDiscountTotal;
    };
    
    $scope.calculateOffshoreDiscount = function(year){
    	var offshoreDiscountTotal = 0;
    	var offshoreMasterRateTotal = 0;
    	var offshoreProposedRate = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			offshoreMasterRateTotal += ((parseFloat($scope.dataList[key].offshoreMasterRate)*parseFloat($scope.dataList[key].offshoreUsage))/100);
    			offshoreProposedRate += (parseFloat($scope.dataList[key].offshoreProposedClientRate)*parseFloat($scope.dataList[key].offshoreUsage/100));
    		}
    	});
    	offshoreDiscountTotal = Math.round(-(((offshoreMasterRateTotal - offshoreProposedRate)/offshoreMasterRateTotal)*100)*100)/100;
    	$scope.roleUtilizationAndRates.totaloffshoreDiscount = offshoreDiscountTotal;
    };
    
    //update sub total of offshore usage
    $scope.updateOnsiteProposedSubTotal = function(offshoreProposed){
    	angular.forEach($scope.subTotalArray, function (value, key) {
    		if($scope.subTotalArray[key].transactionYear == $scope.currentYear){
    			$scope.subTotalArray[key].onsiteProposedClientRate = offshoreProposed;
    		}
    	});
	};
	
	 //update sub total of offshore usage
    $scope.updateOffshoreProposedSubTotal = function(onsiteProposed){
    	angular.forEach($scope.subTotalArray, function (value, key) {
    		if($scope.subTotalArray[key].transactionYear == $scope.currentYear){
    			$scope.subTotalArray[key].offshoreProposedClientRate = onsiteProposed;
    		}
    	});
	};
	
	/*$scope.calculateOffshoreGmPercentageSubTotal = function(year){
    	var totalOffshoreGmPercentage = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			totalOffshoreGmPercentage += parseFloat($scope.dataList[key].offshoreActualGmPercent);
    		//	if($scope.dataList[key].offshoreActualGmPercent != 0){
    				//tempTotalOnsiteMasterRate +=parseFloat($scope.dataList[key].offshoreActualGmPercent);
    				tempOnsiteCount++;
    			}
    		}
    	});
    	$scope.roleUtilizationAndRates.totalOffshoreGMPercent = totalOffshoreGmPercentage/$scope.tableDetails.length;
	}
    */
    //function to calculate GM%
    $scope.calculateGmOnload = function(revenue,cost){
    	var GM = ((revenue - cost)/revenue) * 100; 
    	return GM;
    };
    
    //function to calculate sub total on load
    $scope.calculateSubTotal = function(year){
    	var totalOnsiteUsage = 0;
    	var totalOffshoreUsage = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			totalOnsiteUsage += parseFloat($scope.dataList[key].onsiteUsage);
    			totalOffshoreUsage += parseFloat($scope.dataList[key].offshoreUsage);
    		}
    	});
    	if($scope.onsiteUtilization>0){
    		var onsiteTotal = $filter('number')(totalOnsiteUsage,2);
    		$scope.roleUtilizationAndRates.totalOnsiteUsage = onsiteTotal;
    	}
    	if($scope.offshoreUtilization>0){
    		var offshoreTotal = $filter('number')(totalOffshoreUsage,2);
    		$scope.roleUtilizationAndRates.totalOffshoreUsage = offshoreTotal;
    	}
    	
    	//update data of onsite and offshore of sub total
    	$scope.updateOnsiteUsageSubTotal(totalOnsiteUsage);
    	$scope.updateOffshoreUsageSubTotal(totalOffshoreUsage);
    	
    };
    
    
  //update sub total of offshore usage
    $scope.updateOnsiteUsageSubTotal = function(offshoreUsage){
    	angular.forEach($scope.subTotalArray, function (value, key) {
    		if($scope.subTotalArray[key].transactionYear == $scope.currentYear){
    			$scope.subTotalArray[key].onsiteUsage = offshoreUsage;
    		}
    	});
	};
    
	 //calculate discount on change
    $scope.calculateDiscount = function(proposedRate,masterRate,index){
    	var loopLimit = $scope.tableDetails.length;
    	var arrayIndex = index;
    	var incrementedProposedRate = parseFloat(proposedRate);
    	console.log("The Incremented value is....... "+incrementedProposedRate);
    	var yearArrayLength = $scope.tempYearList.length;
    	
    	if(loopLimit ==1 && yearArrayLength != 1) {
    		if($scope.currentYear == $scope.tempYearList[0]){
    	    	for(var i=0;i<=loopLimit;i++){
    	    		$scope.dataList[arrayIndex].onsiteProposedClientRate = incrementedProposedRate;
    	    		arrayIndex = arrayIndex+loopLimit;
    	    		var incrementvalue = (incrementedProposedRate*parseFloat($scope.onsiteIncrement[i]))/100.00;
    	    		incrementedProposedRate += incrementvalue;
    	    	}	
        	}
    	}
    	else if($scope.tempYearList.length > 1) {
    	if($scope.currentYear == $scope.tempYearList[0]){
	    	for(var i=0;i<$scope.tempYearList.length;i++){
	    		$scope.dataList[arrayIndex].onsiteProposedClientRate = incrementedProposedRate;
	    		arrayIndex = arrayIndex+loopLimit;
	    		var incrementvalue = (incrementedProposedRate*parseFloat($scope.onsiteIncrement[i]))/100.00;
	    		incrementedProposedRate += incrementvalue;
	    	}	
    	}
    	} else {
    		if($scope.currentYear == $scope.tempYearList[0]){
    	    	for(var i=0;i<loopLimit;i++){
    	    		$scope.dataList[index].onsiteProposedClientRate = incrementedProposedRate;
    	    		arrayIndex = arrayIndex+loopLimit;
    	    		//arrayIndex = arrayIndex+i;
    	    		var incrementvalue = (incrementedProposedRate*parseFloat($scope.onsiteIncrement[i]))/100.00;
    	    		incrementedProposedRate += incrementvalue;
    	    	}	
        	}
    	}
    	//set discount value to table for particular row
    	var proposed = 100 - ((parseFloat(proposedRate) / parseFloat(masterRate)))*100;
    	$scope.tableDetails[index].OnsiteDisPremium = Math.round(-(100 - ((proposedRate / masterRate)) * 100.00)*100)/100;
    };
    
    //calculate discount off shore on change
    $scope.calculateDiscountOffshore = function(proposedRate,masterRate,index){
    	var loopLimit = $scope.tableDetails.length;
    	var arrayIndex = index;
    	var incrementedProposedRate = parseFloat(proposedRate);
    	var yearArrayLength = $scope.tempYearList.length;
    	
    	if(loopLimit ==1 && yearArrayLength != 1) {
    		if($scope.currentYear == $scope.tempYearList[0]){
    			for(var i=0;i<=loopLimit;i++){
    				$scope.dataList[arrayIndex].offshoreProposedClientRate = incrementedProposedRate;
    				arrayIndex = arrayIndex+loopLimit;
    				//arrayIndex = arrayIndex+i;
    				var incrementvalue = (incrementedProposedRate*parseFloat($scope.offshoreIncrement[i]))/100;
    				incrementedProposedRate += incrementvalue;
    				
    			}	
    		}
    	}
    	else if($scope.tempYearList.length > 1) {
    		
    		if($scope.currentYear == $scope.tempYearList[0]){
    			for(var i=0;i<$scope.tempYearList.length;i++){
    				$scope.dataList[arrayIndex].offshoreProposedClientRate = incrementedProposedRate;
    				arrayIndex = arrayIndex+loopLimit;
    				//arrayIndex = arrayIndex+i;
    				var incrementvalue = (incrementedProposedRate*parseFloat($scope.offshoreIncrement[i]))/100;
    				incrementedProposedRate += incrementvalue;
    				
    			}	
    		}
    	} else {
    		if($scope.currentYear == $scope.tempYearList[0]){
    			for(var i=0;i<loopLimit;i++){
    				$scope.dataList[index].offshoreProposedClientRate = incrementedProposedRate;
    				arrayIndex = arrayIndex+loopLimit;
    				//arrayIndex = arrayIndex+i;
    				var incrementvalue = (incrementedProposedRate*parseFloat($scope.offshoreIncrement[i]))/100;
    				incrementedProposedRate += incrementvalue;
    				
    			}	
    		}
    	}
    	//set discount value to table for particular row
    	var proposed = (1-(proposedRate / masterRate))*100.00;
    	$scope.tableDetails[index].OffshoreDisPremium = Math.round($filter('number')(-(1-(proposedRate / masterRate))*100.00,2) * 100)/100;
    };
    
    //calculate discount on load
    $scope.calculateDiscountOnLoad = function(proposedClientRate,masterRate){
    		var result = Math.round(-(100 - ((proposedClientRate / masterRate)) * 100.00)*100)/100;
    		return result;
    };
    
    //calculate discount on load
    $scope.calculateOffShoreDiscountOnLoad = function(proposedClientRate,masterRate){
    		var result = Math.round(-(100 - ((proposedClientRate / masterRate)) * 100.00)*100)/100;
    		return result;
    };
    

    //array for on site and off shore percent
    $scope.onsiteIncrementPercent=[];
    $scope.offshoreIncrementPercent=[];
    $scope.onsiteIncrement = [];
    $scope.offshoreIncrement = [];
    //function to get increment percentage according to year
    $scope.setIncrementPercentage = function(dataArray){
    	angular.forEach($scope.dataList, function (value, key) {
    		var exists = false;
    		
   		if($scope.dataList[key].rateCardYOYIncrement != undefined) {
    		angular.forEach($scope.onsiteIncrementPercent, function(value2,key2){
    			
    			if(angular.equals($scope.dataList[key].transactionYear,$scope.onsiteIncrementPercent[key2].transactionYear)) {
    				exists = true;
    			}
    		});
    		
    		/*if(exists == false && value.id != "") {
    			$scope.onsiteIncrementPercent.push($scope.dataList[key].rateCardYOYIncrement.incrementPercentOnsite,$scope.dataList[key].transactionYear);
    		}*/
    		}
   		
   		if(exists == false && $scope.dataList[key].rateCardYOYIncrement != null) {
			$scope.onsiteIncrementPercent.push({
				"increment" : $scope.dataList[key].rateCardYOYIncrement.incrementPercentOnsite,
				"transactionYear" : $scope.dataList[key].transactionYear
			});
		}
    	});
    	
    	for(var i=0;i<$scope.onsiteIncrementPercent.length;i++) {
    		$scope.onsiteIncrement.push($scope.onsiteIncrementPercent[i].increment);
    	}
    	console.log("The Increment Array is......... ");
    	console.log($scope.onsiteIncrementPercent);
    	console.log("The New Onsite Array is.............");
    	console.log($scope.onsiteIncrement);
    };
    
    //function to get OffShore increment percentage according to year
    $scope.setIncrementOffShorePercentage = function(dataArray) {
    	
    		angular.forEach($scope.dataList, function(value,key) {
    			
    			var offShoreDataExists = false;
    			if($scope.dataList[key].rateCardYOYIncrement != undefined) {
    				angular.forEach($scope.offshoreIncrementPercent, function(value2,key2) {
    					if(angular.equals($scope.dataList[key].transactionYear,$scope.offshoreIncrementPercent[key2].transactionYear)) {
    						offShoreDataExists = true;
    	    			}
    				});
    				
    				/*if(offShoreDataExists == false && value.id != "") {
    	    			$scope.offshoreIncrementPercent.push($scope.dataList[key].rateCardYOYIncrement.incrementPercentOffshore);
    	    		}*/
    			}
    			
    			if(offShoreDataExists == false && $scope.dataList[key].rateCardYOYIncrement != null) {
    				$scope.offshoreIncrementPercent.push({
    					"increment" : $scope.dataList[key].rateCardYOYIncrement.incrementPercentOffshore,
    					"transactionYear" : $scope.dataList[key].transactionYear
    				});
    			}
    		});
    		
    		for(var i=0;i<$scope.offshoreIncrementPercent.length;i++) {
        		$scope.offshoreIncrement.push($scope.offshoreIncrementPercent[i].increment);
        	}
    		console.log("The New offshoreIncrement Array is.............");
        	console.log($scope.offshoreIncrement);
    };
   /* //test function
    $scope.updateLocalUtilization = function(index,value){
    	var dataListIndex = index + ($scope.yearList.indexOf($scope.currentYear) * $scope.yearList.length);
    	$scope.dataList[dataListIndex].domesticUsagePercent = parseFloat(value);
    	$scope.dataList[dataListIndex].deputedUsagePercent = 100.00 - parseFloat(value);
    };*/
    
    //
    $scope.calculateDeputedMargin = function(index,value){
    	//change current year value
    	$scope.tableDetails[index].DepuUti = 100.00 - parseFloat(value);
    	
    	//change status of local deputed
    	var yearIndex = $scope.tempYearList.indexOf($scope.currentYear);
    	
    	if(yearIndex == 0){
    		$scope.dataList[index].localFlag = 1;
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    	}else{
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    		$scope.dataList[yearIndex].localFlag = 1;
    	}
    	
    	$scope.dataList[yearIndex].deputedUsagePercent = 100.00 - parseFloat(value);
    	$scope.dataList[yearIndex].domesticUsagePercent = parseFloat(value);
    	$scope.dataList[yearIndex].localFlag = 1;
    	//calculate other values
    	$scope.calculateGMValue($scope.dataList[yearIndex],value,index,yearIndex);
    	$scope.getTotalOnsiteGmPercent($scope.currentYear);
    	//change next year value
    	for(var i=0;i<$scope.tempYearList.length;i++){
    		if($scope.tempYearList[i] > $scope.currentYear){
    			yearIndex += $scope.tableDetails.length;
    			if($scope.dataList[yearIndex].localFlag == 0){
    				$scope.dataList[yearIndex].deputedUsagePercent =  100.00 - parseFloat(value);
	    			$scope.dataList[yearIndex].domesticUsagePercent =  parseFloat(value);
	    			$scope.calculateGMValue($scope.dataList[yearIndex],value,index,yearIndex);
    			}else{
    				break;
    			}
    		}
    	}
    };
    
    //calculate GM data
    $scope.calculateGMValue = function(object,localPercent,index,yearIndex){
    	console.log("object data in the Calculate GM Value is.... ");
    	console.log(object);
    	var localCost = (object.domesticCost * localPercent)/100.00;
    	var deputedCost = (object.deputedCost * (100.00-localPercent))/100.00;
    	var totalCost = localCost + deputedCost;
    	var masterGm = ((object.domesticRevenue - totalCost)/object.domesticRevenue)*100.00;
    	$scope.dataList[yearIndex].onsiteCost = totalCost;
    	//$scope.tableDetails[index].OnsiteMasterRateGM = masterGm;
    	console.log("the index value is......... "+ index);
    	console.log("the Year Index value is......... "+ yearIndex);
    	$scope.dataList[yearIndex].onsiteMasterGmPercent = Math.round(masterGm * 100)/100;
    };
    
    // calculating the GM Value Onload
    $scope.calculateGMValueOnload = function(yearData,localPercent) {
    	var localCost = (yearData.domesticCost * localPercent)/100.00;
    	var deputedCost = (yearData.deputedCost * (100.00-localPercent))/100.00;
    	var totalCost = localCost + deputedCost;
    	var masterGm = ((yearData.domesticRevenue - totalCost)/yearData.domesticRevenue)*100.00;
		return Math.round(masterGm * 100)/100;
    }
    
    //=================================================== On site data calculation===========================================//
    //calculate on site GM percent
    $scope.calculateOnsiteGMPercent = function(index,proposedRate){
    	
    	//change status of on site GM percent
    	var yearIndex = $scope.tempYearList.indexOf($scope.currentYear);
    	
    	if(yearIndex == 0){
    		//$scope.dataList[index].localProposedFlag = 1;
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    	}else{
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    		//$scope.dataList[yearIndex].localProposedFlag = 1;
    	}
    	
    	//object
    	var object = $scope.dataList[yearIndex];
    	//$scope.tableDetails[index].OnsiteMasterRateGM = Math.round($scope.calculateGMValue(object,))
    	//$scope.tableDetails[index].OnsiteMasterRateGM =  $scope.getMasterGmPercent(object);-----maninder
    	var localPercent = $scope.dataList[yearIndex].domesticUsagePercent;
    	$scope.tableDetails[index].OnsiteMasterRateGM = $scope.calculateGMValueOnload(object,localPercent);
    	$scope.tableDetails[index].OnsiteGmPer = $scope.getActualGmPercent(object,proposedRate);
    	$scope.dataList[yearIndex].onsiteProposedClientRate = $scope.tableDetails[index].OnsitePro;
    	$scope.dataList[yearIndex].onsiteMasterGmPercent = $scope.tableDetails[index].OnsiteMasterRateGM;
    	$scope.dataList[yearIndex].onsiteActualGmPercent = $scope.tableDetails[index].OnsiteGmPer;
    	$scope.dataList[yearIndex].onsiteProposedFlag = 1;
    	//change next year value
    	for(var i=0;i<$scope.tempYearList.length;i++){
    		$scope.calculateOnsiteProposedRateSubTotal($scope.currentYear);
    		if($scope.tempYearList[i] > $scope.currentYear){
    			yearIndex += $scope.tableDetails.length;
    			if($scope.dataList[yearIndex].onsiteProposedFlag == 0){
    		    	object = $scope.dataList[yearIndex];
    		    	$scope.dataList[yearIndex].onsiteMasterGmPercent = $scope.getMasterGmPercent(object);
    		    	$scope.dataList[yearIndex].onsiteActualGmPercent =  $scope.getActualGmPercent(object , proposedRate);
    			}else{
    				break;
    			}
    		}
    	}
    	
    	$scope.getTotalOnsiteGmPercent($scope.currentYear);
    };
    
    //get master GM percent post volume discount
    $scope.getMasterGmPercent = function(object){
    	
    	var masterRateRevenue = object.onsiteMasterRate * object.deputedHour;
    	var masterRateRevenueAfterDiscount = masterRateRevenue * (100.00 - object.volumeDiscount)/100.00; 
    	var localCost = (object.domesticCost * object.domesticUsagePercent)/100.00;
    	var deputedCost = (object.deputedCost * (100.00 - object.deputedUsagePercent))/100.00;
    	var totalCost = localCost + deputedCost;
    	var masterGM = $scope.getGMPercent(masterRateRevenueAfterDiscount,totalCost);
    	
    	return masterGM;
    };
    
    //get actual GM percent post volume discount
    $scope.getActualGmPercent = function(object , proposedRate){
    	var actualRateRevenue = object.deputedHour * proposedRate;
    	//var actualRateRevenueAfterDiscount = actualRateRevenue * (100.00 - object.volumeDiscount)/100.00;---maninder 
    	var localCost = (object.domesticCost * object.domesticUsagePercent)/100.00;
    	var deputedCost = (object.deputedCost * (100.00 - object.domesticUsagePercent))/100.00;
    	var totalCost = localCost + deputedCost;
    	//var actualGM = $scope.getGMPercent(actualRateRevenueAfterDiscount,totalCost); -- maninder
    	var actualGM = $scope.getGMPercent(actualRateRevenue,totalCost);
    	
    	if(actualGM == '-Infinity'  || actualGM == 'Infinity' ) {
    		actualGM = 0.00;
    	}
    	return $filter('number')(actualGM,2);
    }
    
    $scope.getActualGmPercentOnload = function(data,ClientRate) {
    	var actualRateRevenue = data.deputedHour * ClientRate;
    	//var actualRateRevenueAfterDiscount = actualRateRevenue * (100.00 - object.volumeDiscount)/100.00;---maninder 
    	var localCost = (data.domesticCost * data.domesticUsagePercent)/100.00;
    	var deputedCost = (data.deputedCost * (100.00 - data.domesticUsagePercent))/100.00;
    	var totalCost = localCost + deputedCost;
    	var actualGM = $scope.getGMPercent(actualRateRevenue,totalCost);
    	
    	if(actualGM == '-Infinity'  || actualGM == 'Infinity' ) {
    		actualGM = 0;
    	}
    	return $filter('number')(actualGM,2);
    }
    
    $scope.getActualGmPercentOffshoreOnload = function(object , proposedRate){
    	var actualRateRevenue = object.offshoreHours * proposedRate;
    	/*var actualRateRevenueAfterDiscount = actualRateRevenue * (100.00 - object.volumeDiscount)/100.00; */
    	var totalCost = object.offshoreCost;
    	
    	var actualGM = $scope.getGMPercent(actualRateRevenue,totalCost);
    	
    	if(actualGM == '-Infinity'  || actualGM == 'Infinity' ) {
    		actualGM = 0;
    	}
    	
    	return $filter('number')(actualGM,2);
    }
    
    //get sub total onsite GM percent
    /*$scope.getTotalOnsiteGmPercent = function(year){
    	var actualRateRevenue=0;
    	var actualRateRevenueAfterDiscount = 0;
    	var localCost = 0;
    	var deputedCost = 0;
    	var totalCost = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			var object = $scope.dataList[key];
		    	actualRateRevenue += object.deputedHour * object.onsiteProposedClientRate;
		    //	actualRateRevenueAfterDiscount += actualRateRevenue * (100.00 - object.volumeDiscount)/100.00; 
		    	localCost += (object.domesticCost * object.domesticUsagePercent)/100.00;
		    	deputedCost += (object.deputedCost * (100.00 - object.domesticUsagePercent))/100.00;
		    	totalCost += localCost + deputedCost;
    		}
    	});
    	var actualGM = $scope.getGMPercent(actualRateRevenue/$scope.tableDetails.length,totalCost/$scope.tableDetails.length);
    	$scope.roleUtilizationAndRates.totalOnsiteGMPercent = Math.round(actualGM * 100)/100;
    	$scope.updateOnsiteGMSubTotal(actualGM);
    };*/
    
    $scope.getTotalOnsiteGmPercent = function(year){
    	var actualRateRevenue=0;
    	var masterRateRevenue=0;
    	var actualRateRevenueAfterDiscount = 0;
    	var localCost = 0;
    	var deputedCost = 0;
    	var totalCost = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			var object = $scope.dataList[key];
		    	actualRateRevenue += object.deputedHour * object.onsiteProposedClientRate * (object.onsiteUsage/100);
		    	masterRateRevenue += object.deputedHour * object.onsiteMasterRate * (object.onsiteUsage/100);
		    //	actualRateRevenueAfterDiscount += actualRateRevenue * (100.00 - object.volumeDiscount)/100.00; 
		    	localCost += (object.domesticCost * object.domesticUsagePercent * object.onsiteUsage/100)/100.00;
		    	deputedCost += (object.deputedCost * ((100.00 - object.domesticUsagePercent) * object.onsiteUsage/100))/100.00;
    		}
    	});
    	totalCost = localCost + deputedCost;
    	//console.log("actual rev " +actualRateRevenue+"--------"+"total cost "+totalCost);
    	var actualGM = $scope.getGMPercent(actualRateRevenue,totalCost);
    	if(actualGM == '-Infinity'  || actualGM == 'Infinity' ) {
    		actualGM = 0;
    	}
    	var masterGM = $scope.getGMPercent(masterRateRevenue,totalCost);
    	$scope.roleUtilizationAndRates.totalOnsiteGMPercent = Math.round(actualGM * 100)/100;
    	$scope.roleUtilizationAndRates.totalOnsiteMasterGMPercent = Math.round(masterGM * 100)/100;
    	$scope.updateOnsiteGMSubTotal(actualGM, masterGM);
    };
    
  //update sub total GM percent
    $scope.updateOnsiteGMSubTotal = function(gmPercent, masterGM){
    	angular.forEach($scope.subTotalArray, function (value, key) {
    		if($scope.subTotalArray[key].transactionYear == $scope.currentYear){
    			$scope.subTotalArray[key].onsiteActualGmPercent = parseFloat(gmPercent);
    			$scope.subTotalArray[key].onsiteMasterGmPercent = parseFloat(masterGM);
    		}
    	});
    /*	console.log("$scope.subTotalArray");
    	console.log($scope.subTotalArray);*/
	};
    
    //calculateOnsiteUsageSubTotal
    $scope.calculateOnsiteUsageSubTotal = function(index,modelValue){
    	var yearIndex = $scope.getYearIndex(index);
    	$scope.dataList[yearIndex].onsiteUsage = modelValue;
    	$scope.dataList[yearIndex].localUsageFlag = 1;
    	$scope.calculateSubTotal($scope.currentYear);
    /*	var value = $scope.dataList[yearIndex].LocalUti;
    	$scope.calculateGMValue($scope.dataList[yearIndex],value,index,yearIndex);
    	*/
    	
    	
    	
    	/*var totalOnsiteMasterRate = 0;
    	var totalOffshoreMasterRate = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == $scope.currentYear){
    			totalOnsiteMasterRate += ((parseFloat($scope.dataList[key].onsiteMasterRate)*parseFloat($scope.dataList[key].onsiteUsage))/100);
    			totalOffshoreMasterRate += ((parseFloat($scope.dataList[key].offshoreMasterRate)*parseFloat($scope.dataList[key].offshoreUsage))/100);
    		}
    	});
    	$scope.roleUtilizationAndRates.totalOnsiteMasterRate = totalOnsiteMasterRate;
    	$scope.roleUtilizationAndRates.totalOffshoreMasterRate = totalOffshoreMasterRate;*/
    	$scope.calculateOnsiteMasterRateSubTotal($scope.currentYear);
    	$scope.calculateOnsiteProposedRateSubTotal($scope.currentYear);
    	
    	$scope.getTotalOnsiteGmPercent($scope.currentYear);
    	
    	
    	$scope.calculateOnsiteDiscount($scope.currentYear);
    	$scope.calculateOffshoreDiscount($scope.currentYear);
    	
    	
    	//change next year on site usage value
    	for(var i=0;i<$scope.tempYearList.length;i++){
    		if($scope.tempYearList[i] > $scope.currentYear){
    			yearIndex += $scope.tableDetails.length;
    			if($scope.dataList[yearIndex].localUsageFlag == 0){
    				$scope.dataList[yearIndex].onsiteUsage =  modelValue;
    			}else{
    				break;
    			}
    		}
    	}
    	
    /*	var totalOnsiteMasterRate = 0;
    	var totalOffshoreMasterRate = 0;
    	var tempOnsiteCount = 0;
    	var tempTotalOnsiteMasterRate = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			totalOnsiteMasterRate += parseFloat($scope.dataList[key].onsiteMasterRate);
    			totalOffshoreMasterRate += parseFloat($scope.dataList[key].offshoreMasterRate);
    			if($scope.dataList[key].onsiteMasterRateonsiteUsage != 0){
    				tempTotalOnsiteMasterRate +=parseFloat($scope.dataList[key].onsiteMasterRate);
    				tempOnsiteCount++;
    			}
    		}
    	});
    	$scope.roleUtilizationAndRates.totalOnsiteMasterRate = tempTotalOnsiteMasterRate/tempOnsiteCount;*/
    };
    
    //get GM percent
    $scope.getGMPercent = function(revenue,cost){
    	return ((revenue-cost)/revenue)*100.00;
    };
    
    //year index wise
    $scope.getYearIndex = function(index){
    	var yearIndex = $scope.tempYearList.indexOf($scope.currentYear)
    	if(yearIndex == 0){
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    	}else{
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    	}
    	return yearIndex;
    };
    
    $scope.colspanArray=[3,3,3,3,3];
    
    
//============================================================== Off Shore Data Calculation ===================================================//
    
    //function to calculate off shore sub total
    $scope.calculateOffshoreUsageSubTotal = function(index,modelValue){
    	var yearIndex = $scope.getYearIndex(index);
    	$scope.dataList[yearIndex].offshoreUsage = modelValue;
    	$scope.dataList[yearIndex].offshoreUsageFlag = 1;
    	$scope.calculateSubTotal($scope.currentYear);
    	
    	$scope.calculateOnsiteMasterRateSubTotal($scope.currentYear);
    	$scope.calculateOnsiteProposedRateSubTotal($scope.currentYear);
    	$scope.calculateOffshoreGMPercentSubTotal($scope.currentYear);
    	
    	//change next year on site usage value
    	for(var i=0;i<$scope.tempYearList.length;i++){
    		if($scope.tempYearList[i] > $scope.currentYear){
    			yearIndex += $scope.tableDetails.length;
    			if($scope.dataList[yearIndex].offshoreUsageFlag == 0){
    				$scope.dataList[yearIndex].offshoreUsage =  modelValue;
    			}else{
    				break;
    			}
    		}
    	}
    };
    
    //update sub total of off shore usage
    $scope.updateOffshoreUsageSubTotal = function(offshoreUsage){
    	angular.forEach($scope.subTotalArray, function (value, key) {
    		if($scope.subTotalArray[key].transactionYear == $scope.currentYear){
    			$scope.subTotalArray[key].offshoreUsage = offshoreUsage;
    		}
    	});
	};
    
    
    //calculate off shore GM percent
    $scope.calculateOffshoreGMPercent = function(index,proposedRate){
    	
    	//change status of on site GM percent
    	var yearIndex = $scope.tempYearList.indexOf($scope.currentYear);
    	
    	if(yearIndex == 0){
    		//$scope.dataList[index].localProposedFlag = 1;
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    	}else{
    		yearIndex = index + $scope.tableDetails.length * yearIndex;
    		//$scope.dataList[yearIndex].localProposedFlag = 1;
    	}
    	
    	//object
    	var object = $scope.dataList[yearIndex];
    	
    	//$scope.tableDetails[index].OffshoreMasterRateGM =  $scope.getMasterGmPercentOffshore(object);
    	$scope.tableDetails[index].OffshoreGmPer = $scope.getActualGmPercentOffshore(object,proposedRate);
    	
    	//$scope.dataList[yearIndex].offshoreMasterRateGmPercent = $scope.tableDetails[index].OffshoreMasterRateGM;
    	$scope.dataList[yearIndex].offshoreActualGmPercent = $scope.tableDetails[index].OffshoreGmPer;
    	$scope.dataList[yearIndex].offshoreProposedClientRate = $scope.tableDetails[index].OffshorePro;
    	$scope.dataList[yearIndex].offshoreProposedFlag = 1;
    	
    	$scope.calculateOffshoreGMPercentSubTotal($scope.currentYear);
    	
    	//change next year value
    	for(var i=0;i<$scope.tempYearList.length;i++){
    		$scope.calculateOnsiteProposedRateSubTotal($scope.currentYear);
    		if($scope.tempYearList[i] > $scope.currentYear){
    			yearIndex += $scope.tableDetails.length;
    			if($scope.dataList[yearIndex].offshoreProposedFlag == 0){
    		    	object = $scope.dataList[yearIndex];
    		    	//$scope.dataList[yearIndex].offshoreMasterRateGmPercent = $scope.getMasterGmPercentOffshore(object);
    		    	$scope.dataList[yearIndex].offshoreActualGmPercent =  $scope.getActualGmPercentOffshore(object , proposedRate);
    			}else{
    				break;
    			}
    		}
    	}
    	
    };
    
    //calculate GM% sub total
    $scope.calculateOffshoreGMPercentSubTotal = function(year){
    	var actualRateRevenue = 0;
    	var masterRateRevenue = 0;
    	//var actualRateRevenueAfterDiscount = 0;
    	var totalCost = 0;
    	angular.forEach($scope.dataList, function (value, key) {
    		if($scope.dataList[key].transactionYear == year){
    			actualRateRevenue += $scope.dataList[key].offshoreHours * $scope.dataList[key].offshoreProposedClientRate * ($scope.dataList[key].offshoreUsage/100);
    			masterRateRevenue += $scope.dataList[key].offshoreHours * $scope.dataList[key].offshoreMasterRate * ($scope.dataList[key].offshoreUsage/100);
    			//actualRateRevenueAfterDiscount  += actualRateRevenue * (100.00 - $scope.dataList[key].volumeDiscount)/100.00; 
    	    	totalCost += $scope.dataList[key].offshoreCost * ($scope.dataList[key].offshoreUsage/100);
    		}
    	});
    	
    //	$scope.tempTotalOffshoreGMPercentage = $filter('number')($scope.getGMPercent(actualRateRevenue,totalCost),2);
    	if($scope.offshoreUtilization > 0){
    	$scope.roleUtilizationAndRates.totalOffshoreMasterGMPercent = Math.round($scope.getGMPercent(masterRateRevenue,totalCost) * 100)/100;
    	}
    	$scope.tempTotalOffshoreGMPercentage = $scope.getGMPercent(actualRateRevenue,totalCost);
    	if($scope.tempTotalOffshoreGMPercentage == '-Infinity'  || $scope.tempTotalOffshoreGMPercentage == 'Infinity' ) {
    		$scope.tempTotalOffshoreGMPercentage = 0;
    	}
    	if($scope.offshoreUtilization > 0){
    	$scope.roleUtilizationAndRates.totalOffshoreGMPercent =Math.round($scope.tempTotalOffshoreGMPercentage * 100)/100;
    	}
    	
    	//change value of off shore GM percent in sub total array
    	$scope.updateOffshoreGMSubTotal($scope.roleUtilizationAndRates.totalOffshoreGMPercent, $scope.roleUtilizationAndRates.totalOffshoreMasterGMPercent);
    	
    	
    }
    
    //update sub total GM percent
    $scope.updateOffshoreGMSubTotal = function(gmPercent, masterGmPercent){
    	angular.forEach($scope.subTotalArray, function (value, key) {
    		if($scope.subTotalArray[key].transactionYear == $scope.currentYear){
    			$scope.subTotalArray[key].offshoreActualGmPercent = gmPercent;
    			$scope.subTotalArray[key].offshoreMasterRateGmPercent = masterGmPercent;
    		}
    	});
	};
    
    //get off shore master GM percent post volume discount
    $scope.getMasterGmPercentOffshore = function(object){
    	
    	var masterRateRevenue = object.offshoreMasterRate * object.offshoreHours;
    	//var masterRateRevenueAfterDiscount = masterRateRevenue * (100.00 - object.volumeDiscount)/100.00; 
    	var totalCost = (object.offshoreCost * object.offshoreUsage)/100.00;
    	var masterGM = $scope.getGMPercent(masterRateRevenue,totalCost);
    	return masterGM;
    };
    
    //get off shore actual GM percent post volume discount
    $scope.getActualGmPercentOffshore = function(object , proposedRate){
    	var actualRateRevenue = object.offshoreHours * proposedRate;
    	/*var actualRateRevenueAfterDiscount = actualRateRevenue * (100.00 - object.volumeDiscount)/100.00; */
    	var totalCost = object.offshoreCost;
    	
    	var actualGM = $scope.getGMPercent(actualRateRevenue,totalCost);
    	
    	if(actualGM == '-Infinity'  || actualGM == 'Infinity' ) {
    		actualGM = 0;
    	}
    	
    	return $filter('number')(actualGM,2);
    }
    
//============================================================== !Off Shore Data Calculation ===================================================//
    

    
//============================================================== Update Data ===================================================//
    $scope.onSearch= false;
    $scope.onSearchUtilization = function(roleUtilizationAndRates) {
    	$scope.onSearch= true;
    	if(roleUtilizationAndRates.$valid){
			//alert("insert data----------");
			$scope.onSearch= false;
			var cityID = $scope.roleUtilizationAndRates.ddlCityModel;
			$scope.uploadBtnDisable = false;
			$scope.getRateUtilizationData($scope.roleUtilizationAndRates.ddlCityModel);
		}
    }
    
    	
    	$scope.onSave= false;
    	$scope.submit = function(roleUtilizationAndRates){
    		$scope.onSave= true;
    		if(roleUtilizationAndRates.$valid){
    			//alert("insert data----------");
    			$scope.onSave= false;
    			$scope.updateData();
    		}
    }
    	
    $scope.updateData = function(){
		
    	//inserting sub total data
    			angular.forEach($scope.subTotalArray, function (value, key) {
    				$scope.dataList.push($scope.subTotalArray[key]);
    	    	});
    	console.log($scope.subTotalArray);
    			
    	//deleting extra flag objects
		angular.forEach($scope.dataList, function (value, key) {
    		delete $scope.dataList[key].localFlag;
    		delete $scope.dataList[key].localUsageFlag;
    		delete $scope.dataList[key].localProposedFlag;
    		delete $scope.dataList[key].onsiteUsageFlag;
    		delete $scope.dataList[key].onsiteProposedFlag;
    		delete $scope.dataList[key].offshoreUsageFlag;
    		delete $scope.dataList[key].offshoreProposedFlag;
    		delete $scope.dataList[key].masterRoles;
    		delete $scope.dataList[key].rateCardYOYIncrement;
    	});
		
		
		var updateRateUtilizationAndRates = function(response) {
    		console.log(response);
    		if(response.status == 200){
	    		BootstrapDialog.show({
	                title : 'Rate Card Utilization and Rates',
	                type : BootstrapDialog.TYPE_PRIMARY,
	                message : "Rate Card Utilization and Rates Updated Successfully.",
	                closable : false,
	                buttons : [{
	                       label : 'OK',
	                       action : function(dialogRef) {
	                             dialogRef.close();
	                             $window.location.reload();
	                       }
	                }]
	          });
    		}
    		else if(response.status == 205){
    			BootstrapDialog.show({
	                title : 'Rate Card Utilization and Rates',
	                type : BootstrapDialog.TYPE_DANGER,
	                message : "Rate Card not saved at previous pages.",
	                closable : false,
	                buttons : [{
	                       label : 'OK',
	                       action : function(dialogRef) {
	                             dialogRef.close();
	                             $window.location.reload();
	                       }
	                }]
	          });
    		}
    		else if(response.status == 204){
    			BootstrapDialog.show({
	                title : 'Rate Card Utilization and Rates',
	                type : BootstrapDialog.TYPE_DANGER,
	                message : "Rate Card not saved at Page 2.",
	                closable : false,
	                buttons : [{
	                       label : 'OK',
	                       action : function(dialogRef) {
	                             dialogRef.close();
	                             $window.location.reload();
	                       }
	                }]
	          });
    		}
    		else{
    			BootstrapDialog.show({
	                title : 'Rate Card Utilization and Rates',
	                type : BootstrapDialog.TYPE_DANGER,
	                message : "We are facing technical issue. Please try again later.",
	                closable : false,
	                buttons : [{
	                       label : 'OK',
	                       action : function(dialogRef) {
	                             dialogRef.close();
	                             $window.location.reload();
	                       }
	                }]
	          });
    		}
    		
    	};
    	console.log("Data before submit:::::::::::::::::::")
    	console.log($scope.dataList);
    	WebServiceFactory.updateRateUtilizationAndRates($scope.dataList).then(updateRateUtilizationAndRates);
	};
	
	// save the data in the Utilization Page.
	   $scope.saveData = function (roleUtilizationAndRates) {
		   $scope.onSave= true;
				$scope.updateUtilizationData();
				
	   }
	   
	   //function for Updating data in Save
	   $scope.updateUtilizationData = function() {
		  
		  
		   
	    	//inserting sub total data
	    			angular.forEach($scope.subTotalArray, function (value, key) {
	    				$scope.dataList.push($scope.subTotalArray[key]);
	    	    	});
	    	console.log($scope.subTotalArray);
	    			
	    	//deleting extra flag objects
			angular.forEach($scope.dataList, function (value, key) {
	    		delete $scope.dataList[key].localFlag;
	    		delete $scope.dataList[key].localUsageFlag;
	    		delete $scope.dataList[key].localProposedFlag;
	    		delete $scope.dataList[key].onsiteUsageFlag;
	    		delete $scope.dataList[key].onsiteProposedFlag;
	    		delete $scope.dataList[key].offshoreUsageFlag;
	    		delete $scope.dataList[key].offshoreProposedFlag;
	    		delete $scope.dataList[key].masterRoles;
	    		delete $scope.dataList[key].rateCardYOYIncrement;
	    	});
			
			
			var updateUtilizationData = function(response) {
	    		console.log(response);
	    		 if($scope.onsiteUtilization != 0 ) {	
	    			   if($scope.roleUtilizationAndRates.totalOnsiteUsage != 100 || $scope.roleUtilizationAndRates.totalOnsiteUsage!= 100.00)
	    		  		{
	    		  		BootstrapDialog.show({
	    		        	title : 'Onsite Usage',
	    		        	type : BootstrapDialog.TYPE_DANGER,
	    		        	message : 'Total onsite Usage should be 100% ',
	    		        	closable : false,
	    		        	buttons : [{
	    		        		label : 'OK',
	    		        		action : function(dialogRef) {
	    		        			dialogRef.close();
	    		        			window.location = "RateCardCreationRoleUtilizationAndRates";
	    		        		}
	    		        	}]
	    		        });
	    		  		
	    		  		}
	    			   }
	    			    if($scope.offshoreUtilization != 0){
	    				   if($scope.roleUtilizationAndRates.totalOffshoreUsage != 100 || $scope.roleUtilizationAndRates.totalOffshoreUsage!= 100.00)
	    			  		{
	    			  		BootstrapDialog.show({
	    			        	title : 'Offshore usage',
	    			        	type : BootstrapDialog.TYPE_DANGER,
	    			        	message : 'Total Offshore Usage should be 100% ',
	    			        	closable : false,
	    			        	buttons : [{
	    			        		label : 'OK',
	    			        		action : function(dialogRef) {
	    			        			dialogRef.close();
	    			        			window.location = "RateCardCreationRoleUtilizationAndRates";
	    			        		}
	    			        	}]
	    			        });
	    			  		
	    			  		} 
	    				   
	    			   }
	    			   if((($scope.onsiteUtilization != 0 && $scope.roleUtilizationAndRates.totalOnsiteUsage == 100)||($scope.onsiteUtilization == 0 && $scope.roleUtilizationAndRates.totalOnsiteUsage != 100))&&
	    					   (($scope.offshoreUtilization != 0 && $scope.roleUtilizationAndRates.totalOffshoreUsage == 100)||($scope.offshoreUtilization == 0 && $scope.roleUtilizationAndRates.totalOffshoreUsage != 100))  ){
	    		if(response.status == 200){
		    		BootstrapDialog.show({
		                title : 'Rate Card Utilization and Rates',
		                type : BootstrapDialog.TYPE_PRIMARY,
		                message : "Rate Card Utilization and Rates Updated Successfully.",
		                closable : false,
		                buttons : [{
		                       label : 'OK',
		                       action : function(dialogRef) {
		                             dialogRef.close();
		                             $window.location.reload();
		                       }
		                }]
		          });
	    		}
	    		else if(response.status == 204){
	    			BootstrapDialog.show({
		                title : 'Rate Card Utilization and Rates',
		                type : BootstrapDialog.TYPE_DANGER,
		                message : "Rate Card not saved at Page 2.",
		                closable : false,
		                buttons : [{
		                       label : 'OK',
		                       action : function(dialogRef) {
		                             dialogRef.close();
		                             $window.location.reload();
		                       }
		                }]
		          });
	    		}
	    	
	    		else{
	    			BootstrapDialog.show({
		                title : 'Rate Card Utilization and Rates',
		                type : BootstrapDialog.TYPE_DANGER,
		                message : "We are facing technical issue. Please try again later.",
		                closable : false,
		                buttons : [{
		                       label : 'OK',
		                       action : function(dialogRef) {
		                             dialogRef.close();
		                             $window.location.reload();
		                       }
		                }]
		          });
	    		}
	    			   }
	    		
	    	};
	    	console.log("Data before submit:::::::::::::::::::")
	    	console.log($scope.dataList);
	    	WebServiceFactory.updateUtilizationData($scope.dataList).then(updateUtilizationData);
		};
    
//============================================================== !Update Data ===================================================//
		//============================================================== Upload File ===================================================//
		
		   $scope.uploadData = function(frmRCRoleUtilizationUpload,fuUploadFilenameModel){
		    	$scope.onUplaod= true;
				if(frmRCRoleUtilizationUpload.fuUploadFilenameModel != undefined){
					$scope.onUplaod = false;
					if($localStorage.rcId != undefined){
						$scope.onUplaod =false;
					//alert("inside valid");
					$scope.uploadAutomaticRURFile();
					}
				}
			};
			
			
			 //Uploading file for Role Utilization and Rates
			
			 $scope.uploadAutomaticRURFile = function(){
			    	var fileTest = $("#fuUploadFilename");	
			    	var name = "Template";
			    	var tempID = 1;	
					var file = $('input[name="fuUploadFilename"]').get(0).files[0];
					var formData = new FormData();
					formData.append('file', file);
					formData.append('name', name);
					formData.append('TempId',tempID);
					formData.append('rpDealVersionId', $localStorage.rcId);
			    	//console.log(file);
			    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadAutomaticRURFile";
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
								title : 'Rate Card Utilization and Rates',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : customMessage,
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										$scope.searchFile($localStorage.rcId)
										$scope.frmRCRoleUtilizationUpload.fuUploadFilenameModel = undefined;
									}
								} ]
							});	
						} 
				    	else if(data.status == 205){
								BootstrapDialog.show({
								title : 'Rate Card Utilization and Rates',
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
								$scope.frmRCRoleUtilizationUpload.fuUploadFilenameModel = undefined;
						}
						else {
							BootstrapDialog.show({
							title : 'Rate Card Utilization and Rates',
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
							$scope.frmRCRoleUtilizationUpload.fuUploadFilenameModel = undefined;
					}
				    },function(data) {
				        $scope.displayres = data.data;
				        $scope.answer = 'Posting data was unsuccessful.';
				});
			 };
			 
//============================================================== !Upload File ===================================================//
			 
//============================================================== Search File / delete file / download file ===================================================+//	
			 
	            // Download the Existing File
	            $scope.downloadUploadedFile = function(objectId){
	            	alert(objectId)
	        		WebServiceFactory.downloadFileWithFileName(objectId);
	        	};

				$scope.deleteUploadedFile= function(objectid, rcId) {
				WebServiceFactory.deleteMasterRCAttachment(objectid);
				for(var i = 0; i < $scope.MDAttachementData.length;i++)
					{
					if($scope.MDAttachementData[i].dealAttachmentId == objectid)
						{
						$scope.MDAttachementData.splice(i,1);						
						}
					}
				if($scope.MDAttachementData.length == 0){
					 $scope.tableHide = true;
				}
				
			};
			 
			  $scope.searchFile = function(rcId)
				{
				  $scope.MDAttachementData=[];
					angular.element("input[type='file']").val(null);
					console.log("inside File Search---"+rcId);

					var markers = {
								"versionId" : rcId
							};
					$http({
					    method: 'POST',
					    url: contextPath+'/RightPrice-DAS/getRCRoleUtilizationAttachement',
					    dataType: 'json',
					    data: JSON.stringify(markers),  
				     headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					    }).
						    then(function(data) {
						    	if(data.data.length!=0){
						    		$scope.tableHide=true;
									$scope.temp=[];
									$scope.temp=data.data;
									console.log("temp")
									console.log($scope.temp)
									var j;
									for(var i=0,j=0;i<$scope.temp.length;i++)
									{
											$scope.MDAttachementData[j] = $scope.temp[i];
											var strMain = $scope.MDAttachementData[j].createdOn;
										    var arrSplit = [];
										    arrSplit = strMain.split(" ");
										    $scope.MDAttachementData[j].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
											j++;
										
									}
									console.log("$scope.MDAttachementData")
									console.log($scope.MDAttachementData)
							
									
								}
								else{
									$scope.tableHide=false;
								}
						    	
						    	
						    });
					
				}
			  
				// $scope.UploadHidden = true;
				 $scope.ShowHideTeamDetails = function () {
				     $scope.TeamDetailsHidden = $scope.TeamDetailsHidden ? false : true;
				 };
			  
//============================================================== !Search File ===================================================+//
 
				    $scope.getDisabledCountry = function(countryId){
				    	var getDisabledCountryData=function(response){
				    		console.log("Disabled Data-------------------")
				    		console.log(response.data)
				    		if(response.data[0].intIsDisabled == 0){
				    		$scope.isGermanyRC = true;
				        	}
				    		else{
				    		$scope.isGermanyRC = false;
				    	}
				    		
				    		
				    	}
				        WebServiceFactory.getDisabledCountryData(countryId).then(getDisabledCountryData);    	
				    }

				    var getPageTrckrData =function(response)
			    	{
			    		console.log("Version Data");
			    		console.log(response);
			    		$scope.pagetrckDetails = response.data;
				    $localStorage.pageTracker=$scope.pagetrckDetails[0].noOfSubmittedPages;
		    		 if(userType == 'Delivery') {
		    		 if( $localStorage.pageTracker<2)
			    			{
			    			BootstrapDialog.show({
								title : 'FP Deal Creation - Role Selection',
								type : BootstrapDialog.TYPE_DANGER,
								message : 'Data is not saved at previous screen.',
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										//$window.location.reload();
										if($localStorage.pageTracker==1){
											window.location="RateCardCreationRoleSelection";
										}
										
										else
											{window.location="RateCardCreationDetails";
											
											}
										

									}
								} ]
							});
			    		}
		    		 }
			    	};
			    	WebServiceFactory.getPageTrckrData($localStorage.rcId).then(getPageTrckrData);
	 
		
}]);