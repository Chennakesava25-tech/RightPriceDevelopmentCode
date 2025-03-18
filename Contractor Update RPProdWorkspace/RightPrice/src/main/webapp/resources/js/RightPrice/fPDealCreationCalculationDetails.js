//var app = angular.module('RightPrice', ['ui.filters', 'ngStorage']);
app.controller("FPDealCreationCostSummaryController",['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory','fpDealCostInputsService','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory,fpDealCostInputsService,$localStorage, $sessionStorage,$index){
	var contextPath = "/RightPrice-DAS";
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
		        window.location='FPDealCreationWhatIfApplicationwise';
		    };
		    $scope.Next = function()
		    {   
		        window.location='FPDealCreationCostSummary';
		    }; 
	
		    $scope.isDataAvailable = true;
		    $scope.firstYearData = false;
		    $scope.isDomestic = true;
		    $scope.isH1 = true;
		    $scope.isB1 = true;
		    $scope.isOffshore = true;
		    $scope.isSkillBay = true;
		    $scope.isCPC = true;
		    $scope.isDirectCost = true;
		    $scope.relocationHeadCount = true;
		    $scope.offshoreShiftWorking = true;
		    $scope.isFPDealGFT = false;
		    $scope.isFPDealRiskManagers = false;
		    $scope.isDownloadEnabled=true;
		    $scope.isDevelopment=false;
		    //colspan for before year header
		    $scope.colspan = 12;
		    $scope.calculationData = [];
		    //Year header list
		    $scope.yearHeader=[];
		    
		    //Month header list
		    $scope.monthHeader=[];
		    
		    var userType = sessionStorage.getItem('userType');
			 console.log("The Fp Manual Summary Details...... "+userType);
			 if(userType == 'GFT') {
				 $scope.isFPDealGFT = true;
			}
			 else if(userType == 'RiskManagers') {
					$scope.isFPDealRiskManagers = true;
				}

	var rpVrsId = $localStorage.rpDealVersionId;	
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
		$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	//arvind
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

	WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails);
	
	
	var getVersionData =function(response)
	{
		console.log("Version Data");
		console.log(response);
		$scope.versionDetails = response.data;
		if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId==1)
			{
				$scope.versionDetails[0].dealStatus='Open';
			}
		else
			{
				$scope.versionDetails[0].dealStatus='Close';
			}
		
		if($scope.versionDetails[0].fpProjectTypeId==1)
			{
				$scope.versionDetails[0].projectType='Development - Fixed Price';
				$scope.masterRole=true;
			}
		else if($scope.versionDetails[0].fpProjectTypeId==2)
			{
				$scope.versionDetails[0].projectType='Development - Manage Capacity';
				$scope.masterRole=false;
			}
		else if($scope.versionDetails[0].fpProjectTypeId==3)
			{
				$scope.versionDetails[0].projectType='Maintenance - Fixed Price';
				$scope.masterRole=true;
			}
		else
			{
				$scope.versionDetails[0].projectType='Maintenance - Manage Capacity';
				$scope.masterRole=false;
			}

		$localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
		if(userType == 'Delivery') {
		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<6)
			{
			BootstrapDialog.show({
				title : 'FP Deal Creation -Calulation Details',
				type : BootstrapDialog.TYPE_DANGER,
				message : 'Data is not saved at previous screen.',
				closable : false,
				buttons : [ {
					label : 'OK',
					action : function(dialogRef) {
						dialogRef.close();
						//$window.location.reload();
						 if($localStorage.pageTracker==5)
						{
							window.location="FPDealCreationWhatIfApplicationwise";
						}
						else if($localStorage.pageTracker==4)
						{
							window.location="FPDealCreationCostInputs";
						}
						else if($localStorage.pageTracker==3)
							{
								window.location="FPDealCreationRoleSelection";
							}
						else if($localStorage.pageTracker==2)
							{
							window.location="FPDealCreationRoleSelection";
							}
						else if($localStorage.pageTracker==1)
						{
							window.location="FPDealCreationRateCardAndProjectDetails";
						}
						else
							{
								window.location="FPDealCreationDetails";
							}
						
					}
				} ]
			});
		}
		}
		if($scope.versionDetails[0].fpProjectTypeId==1 || $scope.versionDetails[0].fpProjectTypeId==2){
			    			$scope.isDevelopment=true;	
			    		}
			    		else{
			    			$scope.isDevelopment=false;
			    		}
		
	};
	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
	
	   // get the Deal Tower details 
    var getDealTower = function(response) 
	{	console.log("fp Deal Tower data ..............");
		console.log(response);
		$scope.towerdetails = response.data;
	
	};
	WebServiceFactory.getDealTower($localStorage.rpDealVersionId).then(getDealTower);
		
	 $scope.getTowerCountryCity = function (fpdTowervalue) {
	    	$scope.searchRoleDetailsResult=[];
	    	angular.forEach($scope.towerdetails, function(value, key) {
				 if(fpdTowervalue.towerName == $scope.towerdetails[key].towerName)
					 {
					 	
					 	countryId=$scope.towerdetails[key].countryId
					 	cityId=$scope.towerdetails[key].cityId;
					 	dealAutoTowerId=$scope.towerdetails[key].dealautoTowerId;
					 	
					 }
				});
	    	
	    	var getCountryDetail = function(response) {
	    		console.log("Deal version ID : " + $localStorage.rpDealVersionId);
	    		console.log("get country data");
		  		console.log(response);
		  		$scope.country = response.data;
		  		angular.forEach($scope.country, function (value, key) {
		  			if($scope.country[key].countryId == countryId)
		  				{
		  					$scope.frmDealCalculation.ddlCountryModel=$scope.country[key].countryName;
		  				}
	               });
		  		
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
	    	 var getCities = function(response) {
	     		$scope.city = response.data;
	     		console.log("City Names are...........");
	     		console.log($scope.city);
	     		angular.forEach($scope.city, function(value, key) {
					 if($scope.city[key].cityId == cityId)
						 {
						 	$scope.frmDealCalculation.ddlCityModel=$scope.city[key].cityName;
						 	categoryId=$scope.city[key].categorizationId;
						 	
						 	var getCitycategorization = function(response)
						 	{
						 		console.log("City Category data....");
						 		console.log(response);
						 		$scope.getCityCat=response.data;
						 		angular.forEach($scope.getCityCat, function(value, key) {
						 		if($scope.getCityCat[key].codeName==categoryId)
						 			{
						 			$scope.frmDealCalculation.dealRoleDetailsCategorizationModel=$scope.getCityCat[key].description;
						 			}
						 		});
						 	};
							WebServiceFactory.getCitycategorization().then(getCitycategorization);
						 
						 }
					});
	     	
	     	};
	     	WebServiceFactory.getCities(countryId).then(getCities);
	    }
	 
	 var temp = 0;
	 $scope.getFpDealCalculationData = function(frmCalculationDetails) {
		 $scope.isDownloadEnabled=true;
		 $scope.onSearch= true;
				//$scope.downloadBtn = false;
				if(frmCalculationDetails.$valid){
					$scope.onSearch= false;
					console.log("ffffffffffffffffffrm");
					console.log(frmCalculationDetails);
					console.log(frmCalculationDetails.ddlTowerModel.cityId);
					console.log(frmCalculationDetails.ddlTowerModel.dealTowerId);
					var towerId = frmCalculationDetails.ddlTowerModel.dealTowerId;
					//put city id and tower id as input
					var getCalculationDetails = function(response) {
						console.log(frmCalculationDetails);
						console.log("Response is:");
						console.log(response)
						var j=0;
						for(var i = 0;i<response.data.length;i++){
							if(response.data[i].costCode == 0 && response.data[i].sectionId == 51)
							{
								continue;
							}
							else
							{
								$scope.calculationData [j] = response.data[i];
								j++;
							}
							
						}
						
						
						
						var search="_";
						var replacement= " ";
						console.log(response.data[0].staffingFirstMonthCount)
						if($scope.calculationData.length != 0) {
							var header = response.data[0].staffingDetailsHeader;
							$scope.colspan = 2;
							
							//create year header
							if(temp==0) //a
							{
								fpDealCostInputsService.createYearHeader($scope,header);
								temp=1;
								$scope.isDownloadEnabled=false;
							}
							
						} else {
							$scope.isDownloadEnabled=true;
							 BootstrapDialog.show({
					   	        	title : 'FP Deal Creation - Calculation Details',
					   	        	type : BootstrapDialog.TYPE_DANGER,
					   	        	message : 'No Data Found',
					   	        	closable : false,
					   	        	buttons : [{
					   	        		label : 'OK',
					   	        		action : function(dialogRef) {
					   	        			dialogRef.close();
					   	        		}
					   	        	}]
					   	        });
						}
						//set visa id 2 data(Onsite Deputed)
						//fpDealCostInputsService.setOnsiteDeputedData($scope);
						
					};
					WebServiceFactory.getFpDealCalculationData(towerId,$localStorage.rpDealVersionId).then(getCalculationDetails);
				}
	 }
	 
	 //Set Year Header
	    $scope.setYearHeader = function(yearHeader){
	    	//alert('setYearHeader');
	    	console.log(yearHeader);
	    	$scope.currentMonthYearHeader = yearHeader.year;
	    	var flag=true;
	    	if($scope.currentMonthYearHeader =='Summary')
	    	{
	    		$scope.monthHeader = [];
	    		$scope.isSummaryFlag = true;
	    		$scope.summaryFlag = ["5"];
	    		angular.forEach($scope.calculationData, function (value, key) {
					if(value.monthYearHeader == 'Summary'){
						flag = false;
					}
	    		});
	    	} else {
	    		$scope.summaryFlag = [];
	    		angular.forEach($scope.calculationData, function (value, key) {
	    			if(flag){
	    				if(value.monthYearHeader == $scope.currentMonthYearHeader){
	    					//create month header for year header
	    					fpDealCostInputsService.createMonthHeader($scope,value.staffingDetailsHeader);
	    					flag = false;
	    				}
	    			}
	    			
	    		});
	    	}
	    };
	    
	    var getWhatIfCalculationData=function(response)
		{
			$scope.whatIfCalDetails = response.data;
			$scope.noOfYears=$scope.whatIfCalDetails[0].noOfYears;
		};
		WebServiceFactory.getWhatIfCalculationData(rpVrsId).then(getWhatIfCalculationData);
	
	
		   $scope.download = function(frmDealCalculation){
	        	var crmDealId = $localStorage.DealModel
	        
			   
			   var towerId = frmDealCalculation.ddlTowerModel.dealTowerId;
				   window.location= contextPath+"/RightPrice-DAS/downloadFPdealCalculationExcel/"+towerId+"/"+$localStorage.rpDealVersionId+"/"+crmDealId;   
			
			   
			   //WebServiceFactory.downloadMyDashboardExcel($scope.dashboardArray,$scope.rateCardStatus);
		    } 
	
}]);