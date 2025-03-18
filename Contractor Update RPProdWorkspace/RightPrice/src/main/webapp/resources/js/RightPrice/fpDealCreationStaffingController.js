﻿﻿//var app = angular.module('FPDealCreationStaffingApp', []);

app.controller("FPDealCreationStaffingController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','fpDealStaffingService','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,fpDealStaffingService, $localStorage, $sessionStorage, $index) {
    var contextPath = "/RightPrice-DAS";
	$scope.fpDealStaffing = {};
	$scope.frmStaffing={};
	$scope.colors = [];
	$scope.tempTransTowerid=1;
	$scope.transTemp = 1;
	$scope.showTransValue=0;
	$scope.yearHeaderlen=0;
	$scope.displaySumonSerch=0;
	$scope.rpVrsId = $localStorage.rpDealVersionId;		
	$scope.DealStaffingDetailsHidden = true;
	 $scope.UploadHidden = true;
	 $scope.isFPDealGFT = false;
	 $scope.isFPDealRiskManagers = false;
	 $scope.isSaveDisabled=false;
	 $scope.isRCPricing=false;
	 $scope.isDownloadEnabled=true;
	 $scope.domesticDisabled=true;
	 $scope.deputedDisabled=true;
	 $scope.shortTermDisabled=true;
	 $scope.offshoreDisabled=false;
	 $scope.dealAutoTowerIDVal = 0;
	 var contextPath = "/RightPrice-DAS";
	 $scope.towerdetails = [];
     $scope.ShowHideDealStaffingDetails = function () {
        $scope.DealStaffingDetailsHidden = $scope.DealStaffingDetailsHidden ? false : true;
    };
	$scope.ShowHideUpload = function () {
        $scope.UploadHidden = $scope.UploadHidden ? false : true;
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
	$scope.Prev = function() 
    {   
        //window.location='FPDealCreationAddContractorRole'; removed
		window.location = 'FPDealCreationRoleSelection';
    };
    $scope.Next = function()
    {   
        window.location='FPDealCreationCostInputs';
    };
    //colspan for before year header
    $scope.colspan = 14;
    
    //Year header list
    $scope.yearHeader=[];
    
    //Month header list
    $scope.monthHeader=[];
    
    //tab array
    $scope.tabArray=[2,3,4,5,6];
    
    //onsite local staffing
    $scope.onsiteLocal = [];
    $scope.onsiteLocalSumTotal = [];
    $scope.onsiteLocal_Contractor = []
    $scope.offshore_Contractor = []
    $scope.onsiteGross = []
    $scope.staffingGross = []
    $scope.onsiteLocal_ContrSumTotal = [];
    $scope.offshore_ContrSumTotal = []
    $scope.onsiteGrossSumTotal = [];
    $scope.staffingGrossSumTotal = [];
    //onsite deputed staffing
    $scope.onsiteDeputed = [];
    $scope.onsiteDeputedSumTotal = [];

    //onsite short term staffing
    $scope.onsiteShortTerm= [];
    $scope.onsiteShortTermSumTotal = [];
    
    //onsite sub total
    $scope.onsiteTotal = [];
    $scope.onsiteSumTotal = [];
    
    //onsite contractor
    $scope.onsiteContractor=[];
    $scope.onsiteSumTotalContractor=[];
    
    //offshore sub total
    $scope.offshoreTotal = [];
    $scope.offshoreSumTotal = [];
    $scope.offshoreSumTotalContr = [];
    $scope.offshoreTotalContr = [];
    //total staffing
    $scope.totalStaffing = [];
    $scope.totalStaffingContr = [];
    $scope.totalSumTotalStaffing = [];
    $scope.copyRowUPtoNonZero =[];
    $scope.isDevelopment=false;
    
    var userType = sessionStorage.getItem('userType');
	 console.log("The USer Value from the Session is........ "+ userType);
	 if(userType == 'GFT') {
		 $scope.isFPDealGFT = true;
	 }
    
    var vartemp=0;
	$window.onload = function() 
	{
		$scope.uploadBtnDisable=true;
		if($sessionStorage.varCheck>0)
			{
			if($sessionStorage.staffing_header.monthYearHeader6== 'Summary')  
				{
					vartemp=1;
					$scope.getFpDealStaffingData($sessionStorage.staffing_header);
					$scope.setYearHeaderFinal('Summary');			
				}
			}
	}; 
    
    
    
    
    
    
    //$scope.totalSumTotalStaffing1 = [];
    // added on 27th Feb 2018
    /*$scope.contryList =$sessionStorage.ContryList;
    console.log("Coutry list from session---");
    console.log($scope.contryList);
    console.log($sessionStorage.CityList);*/
  /*  $scope.chkBoxSelectedCountryList = $scope.contryList;
    $scope.cityList = $sessionStorage.CityList;*/
   
  /*  $scope.getCities = function(countryId){
    	 var city = [];
    	angular.forEach($scope.cityList, function (value, key) {
    		if(value.countryId == countryId ){
    			city.push(value);
    		}
    	});
    	$scope.cityArray = city;
    };
    
    var getTowerDetails = function(response){
    	console.log("Tower Called");
    	console.log(response.data);
    	$scope.tower = response.data;
    }
    WebServiceFactory.getTowerDetails($localStorage.rpDealVersionId).then(getTowerDetails);*/
    
    //6sept2018
    var getDealTower = function(response) 
	{	console.log("fp Deal Tower data ..............");
		console.log(response);
		$scope.towerdetails = response.data;
		var getVersionAttachment =function(response)
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
    						
    						var strMain =$scope.versionAttachment[key].createdOn;
    						var arrSplit = [];
    						arrSplit = strMain.split(" ");
    						$scope.versionAttachment[key].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
    			});
    			
         }
    		
    	};
    	WebServiceFactory.getVersionAttachment($localStorage.rpDealVersionId).then(getVersionAttachment);
    	
		
	
	};
	WebServiceFactory.getDealTower($localStorage.rpDealVersionId).then(getDealTower);
    
    
    $scope.getTowerCountryCity = function (fpdTowervalue) {
    	$scope.uploadBtnDisable=false;
    	$scope.searchRoleDetailsResult=[];
    	angular.forEach($scope.towerdetails, function(value, key) {
			 if(fpdTowervalue.towerName == $scope.towerdetails[key].towerName)
				 {
				 	
				 	countryId=$scope.towerdetails[key].countryId
				 	cityId=$scope.towerdetails[key].cityId;
				 	dealAutoTowerId=$scope.towerdetails[key].dealautoTowerId;
				 	$scope.dealAutoTowerIDVal = $scope.towerdetails[key].dealautoTowerId;
				 	$sessionStorage.tower_Id_Contr=$scope.towerdetails[key].dealTowerId;
				 	$scope.tempTransTowerid=$scope.towerdetails[key].dealTowerId;
				 	
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
	  					$scope.frmStaffing.dealStaffingDetailsCountryModel=$scope.country[key].countryName;
	  				}
               });
	  	
	  		
	  	    	var getVisaTypesbyCountry = function(response) 
	  			{
	  			 	console.log("Visa type");
	  			 	console.log(response.data);
	  				$scope.visaTypeDetails = response.data;
	  				angular.forEach($scope.visaTypeDetails, function (value, key) {
	  		  			if($scope.visaTypeDetails[key].visaTypeId == 1 && $scope.visaTypeDetails[key].isStaffing == 1)
	  		  				{
	  		  					$scope.domesticDisabled=false;
	  		  					$scope.domstaffing='Domestic';
		  		  				
	  		  				}
	  		  			
		  		  		if($scope.visaTypeDetails[key].visaTypeId == 2 && $scope.visaTypeDetails[key].isStaffing == 1)
			  				{
			  					$scope.deputedDisabled=false;
			  					$scope.depstaffing='Deputed';
			  					
			  				}
	  		  		
			  		  	if($scope.visaTypeDetails[key].visaTypeId == 3 && $scope.visaTypeDetails[key].isStaffing == 1)
			  				{
					  		  
			  					$scope.shortTermDisabled=false;
			  					$scope.shrtstaffing='ShortTerm';
			  				}
	  		  	
	  	               });
	  				
	  				
	  				
	  			};
	  			WebServiceFactory.getVisaTypes(countryId).then(getVisaTypesbyCountry);	
	  	    
		};
		WebServiceFactory.getCountryDetail().then(getCountryDetail);
		
    	 var getCities = function(response) {
     		$scope.city = response.data;
     		console.log("City Names are...........");
     		console.log($scope.city);
     		angular.forEach($scope.city, function(value, key) {
				 if($scope.city[key].cityId == cityId)
					 {
					 	$scope.frmStaffing.dealStaffingDetailsCityModel=$scope.city[key].cityName;
					 
					 }
				});
     	
     	};
     	WebServiceFactory.getCities(countryId).then(getCities);
     	
     	//below newly added for contact table data wrt to tower id 1,2,..
     	 var getStaffingcontractorRoleList = function(response) {
 			console.log("getStaffingcontractorRoleList");
 			console.log(response);
 			$scope.staffingContactorRoleList = response.data;
 		};
 		WebServiceFactory.getStaffingcontractorRoleList($scope.rpVrsId,$sessionStorage.tower_Id_Contr).then(getStaffingcontractorRoleList);
    }
    
    
    
    
    //end 6sept2018
    //Ends
    //API Call to get Staffing Data
    //view stsffing contractor data
   /* var viewcontractorRoleList = function(response) {
		console.log("getcontractorRoleList");
		console.log(response);
		$scope.addContactorRole = response.data;
	};
	WebServiceFactory.viewcontractorRoleList().then(viewcontractorRoleList);*/
	/* var getStaffingcontractorRoleList = function(response) {
			console.log("getStaffingcontractorRoleList");
			console.log(response);
			$scope.staffingContactorRoleList = response.data;
		};
		WebServiceFactory.getStaffingcontractorRoleList($scope.rpVrsId,$sessionStorage.tower_Id_Contr).then(getStaffingcontractorRoleList);*/
		//WebServiceFactory.getStaffingcontractorRoleList($scope.rpVrsId,frm.dealStaffingDetailsTowerModel.dealTowerId).then(getStaffingcontractorRoleList);
    //end contractor 
		
    	var getVersionData =function(response)
    	{
    		console.log("Version Data");
    		console.log(response);
    		$scope.versionDetails = response.data;
    		$localStorage.currentApprovalStatus=$scope.versionDetails[0].currentApprovalStatus;
    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||  $scope.versionDetails[0].currentApprovalStatus == 4 || $scope.versionDetails[0].currentApprovalStatus == 8 )&& userType == 'Delivery')
			{
				$scope.isSaveDisabled=false;
			}
		else
			{
			    $scope.isSaveDisabled=true;
			}
    		
    		if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId == 0)
			{
				$scope.versionDetails[0].dealStatus='Open';
			}
			else if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId == 1)
				{
					$scope.versionDetails[0].dealStatus='Won';
				}
			else{
					$scope.versionDetails[0].dealStatus='Lost';
			}
    		
    		$localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
    		 if(userType == 'Delivery') {
    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
    				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<3)
    			{
    			BootstrapDialog.show({
					title : 'FP Deal Creation - Staffing Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Data is not saved at previous screen.',
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							//$window.location.reload();
							if($localStorage.pageTracker==2)
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
    		
    		if($scope.versionDetails[0].fpProjectTypeId==1 || $scope.versionDetails[0].fpProjectTypeId==2){
    			$scope.isDevelopment=true;	
    		}
    		else{
    			$scope.isDevelopment=false;
    		}
    		
    		/*$localStorage.pricingType=$scope.versionDetails[0].pricingType;
    		
  		  if($localStorage.pricingType!=1)
  			{
  			  $scope.isRCPricing=true;
  			}
  			else{
  				$scope.isRCPricing=false;
  			}*/
    			
    		
    	};
    	WebServiceFactory.getVersionData($localStorage.rpDealVersionId).then(getVersionData);
   
    	var temp=0;  //a
    	$scope.disabledflag1=false;
    	$scope.getFpDealStaffingData = function(frm){
    		$scope.disabledflag1=true;
    		$scope.autoSummaryDisplay=1;
    		
    		$scope.clearData();
    	$sessionStorage.frmModel=frm;
    	
    	//put city id and tower id as input
    	var getRateCardInfo = function(response) {
    		console.log(frm);
    		console.log("Response is:");
    		console.log(response)

    		$scope.data=response.data;
    		if($scope.data!=null){
    			 $scope.isDownloadEnabled=false;	
    		}else{
    		 $scope.isDownloadEnabled=true;
    		}
    		$scope.disabledflag1=false;
    		//var testContractor=$scope.data[0].isContractor;
    		var header = response.data[0].staffingDetailsHeader;
    		$sessionStorage.staffing_header=response.data[0].staffingDetailsHeader;
    		$scope.colspan = 3;
    		
    		
    		$scope.yearHeaderlen=0;
    		
    		//create year header
    		/*if(temp==0 ) //a
    			{*/
	    			fpDealStaffingService.createYearHeader($scope,header);
	    				temp=1;
	    				
	    				/*var filtered = $scope.yearHeader.filter(function (el) {
	    					  return el != null;
	    					});

	    					console.log($scope.filtered);*/
	    				
	    				
	    				fpDealStaffingService.setOnsiteDeputedData($scope);
    			/*}*/
    		
    		
    		/*if(frm.dealStaffingDetailsTowerModel.dealTowerId==2) //a
			{
    			 $scope.onsiteLocal = [];
    			  $scope.monthHeader=[];
    			  $scope.yearHeader=[];
    			fpDealStaffingService.createYearHeader($scope,header);
    				
    				fpDealStaffingService.setOnsiteDeputedData($scope);
			}*/
    		
    		
    		$scope.autoSummaryData();
    		 if($sessionStorage.varCheck==0)
			 {
    			$sessionStorage.varCheck=1;
			 	$window.onload();			 	
			 }
    		
    		 $scope.displaySumonSerch=1;
    		 $scope.setYearHeader();
    		//set visa id 2 data(Onsite Deputed)
    		//fpDealStaffingService.setOnsiteDeputedData($scope);
    		
    	};
    	if(vartemp==1)
		{
    		$scope.findTotalTransMonth($sessionStorage.staffing_header.dealTowerId,$localStorage.rpDealVersionId);
    		
		WebServiceFactory.getFpDealStaffingData($localStorage.rpDealVersionId,$sessionStorage.staffing_header.cityId,$sessionStorage.staffing_header.dealTowerId).then(getRateCardInfo);
		vartemp=0;
		}
	else
		{
		$scope.findTotalTransMonth(frm.dealStaffingDetailsTowerModel.dealTowerId,$localStorage.rpDealVersionId);
		//WebServiceFactory.getTMDealStaffingData(frm.dealStaffingDetailsCityModel.cityId,frm.dealStaffingDetailsTowerModel.dealTowerId).then(getRateCardInfo);
		WebServiceFactory.getFpDealStaffingData($localStorage.rpDealVersionId,frm.dealStaffingDetailsTowerModel.cityId,frm.dealStaffingDetailsTowerModel.dealTowerId).then(getRateCardInfo);
		
		}
    	
    	//WebServiceFactory.getFpDealStaffingData(frm.dealStaffingDetailsCityModel.cityId,frm.dealStaffingDetailsTowerModel.dealTowerId).then(getRateCardInfo);
    	//WebServiceFactory.getFpDealStaffingData(frm.dealStaffingDetailsTowerModel.cityId,frm.dealStaffingDetailsTowerModel.dealautoTowerId).then(getRateCardInfo);
    	//WebServiceFactory.getFpDealStaffingData(frm.dealStaffingDetailsTowerModel.cityId,frm.dealStaffingDetailsTowerModel.dealTowerId).then(getRateCardInfo);
    	// $scope.setYearHeader();
    };
    
    $scope.findTotalTransMonth = function(tempTransTowerid,rpDealVersionId)
    {
    	var findTotalTrans = function(response) {
        	$scope.data=response.data;    	
        	$sessionStorage.totalTransMonth=$scope.data[0].transitionMonth;
        	if($scope.transTemp==1)
        		{
	        		for(var i=0;i< $sessionStorage.totalTransMonth ;i++)
	    			{				
	            		$scope.colors.push('blue');
	    			}
        		}
        	
        };
        WebServiceFactory.findTotalTransMonth($scope.tempTransTowerid,$localStorage.rpDealVersionId).then(findTotalTrans);
    }
    
    
    //Set Year Header
    $scope.anyChange=10;
    $scope.setYearHeaderFinal = function(yearHeader){
    	$scope.currentMonthYearHeader = yearHeader;  // yearHeader.year;		    	
    	var flag=true;
	    	if($scope.anyChange==1000)
			{
			  //$scope.totalArray = [];
			 //   $scope.marker=[];
	    		if($localStorage.currentApprovalStatus == 1 || $localStorage.currentApprovalStatus == 4)
	    			{
	    				$scope.save();
	    			}
				
			}    	
		$scope.summaryFlag = ["5"];    		
		angular.forEach($scope.data, function (value, key) {
    		if(flag){
	    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
	    			//create month header for year header
	    			fpDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
	    			flag = false;
	    			
	    		}
    		}
	});
    	
    };
    
    $scope.setYearHeader = function(yearHeader){
    	
    	//alert('setYearHeader');
    	console.log(yearHeader);
    	
    	/*if($scope.autoSummaryDisplay==1)
		{
    		autoSummaryDisplay=0;
    		//$scope.currentMonthYearHeader=='Summary' ;
    		angular.forEach($scope.data, function (value, key) {
	    		if(flag){
		    		if(value.monthYearHeader == 'Summary'){
		    			//create month header for year header
		    			fpDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
		    			//flag = false;
		    			
		    		}
	    		}
	    	});
		}*/
    	if($scope.displaySumonSerch>0)
    		{
    			$scope.displaySumonSerch=0;
    			$scope.currentMonthYearHeader = 'Summary';
    		}
    	else
    		{
    			$scope.currentMonthYearHeader = yearHeader.year;
    		}
    			
    	
    	var flag=true;	    	
    	
    	if($scope.currentMonthYearHeader=='Summary')
    	{
    		if($scope.anyChange==1000)
    			{
	    			if($localStorage.currentApprovalStatus == 1 || $localStorage.currentApprovalStatus == 4)
	    			{
	    				$scope.save();
	    			}
    			}
    	
    		$scope.summaryFlag = ["5"];
    		
    		angular.forEach($scope.data, function (value, key) {
	    		if(flag){
		    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
		    			//create month header for year header
		    			fpDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
		    			flag = false;
		    			
		    		}
	    		}
	    	});
    		
    	}
    	
    	else
    	{
    		$scope.summaryFlag = [];
    		
    		angular.forEach($scope.data, function (value, key) {
	    		if(flag){
		    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
		    			//create month header for year header
		    			fpDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
		    			flag = false;
		    			
		    		}
	    		}
	    	});
    		
    		}
		if($scope.data[0].monthYearHeader != yearHeader.year)
		{
			$scope.transTemp = 0;
			$scope.colors = [];
		}
    	else
		{
			$scope.colors = [];
			$scope.transTemp = 1;
			$scope.showTransValue =$sessionStorage.totalTransMonth;
			for(var i=0;i<$sessionStorage.totalTransMonth;i++)
			{				
        		$scope.colors.push('green');
			}
		}
    	
    	
    };
    
    
    /*$scope.setYearHeader = function(yearHeader){
    	//alert('setYearHeader');
    	console.log(yearHeader);
    	$scope.currentMonthYearHeader = yearHeader.year;
    	var flag=true;
    	angular.forEach($scope.data, function (value, key) {
    		if(flag){
	    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
	    			//create month header for year header
	    			fpDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
	    			flag = false;
	    			
	    		}
    		}
    	});
    };*/
    
       
    //calculate row total
    $scope.calculateRowTotal = function(onsiteDeputed,index){
    	$scope.anyChange=1000;
    	//get object of array
    	var object = fpDealStaffingService.getRowCalculationObject($scope,onsiteDeputed,index);
    	
    	//auto calculate row total
    	fpDealStaffingService.calculateRowTotal(object);
    };
    
    //calculate Sub Total
    $scope.calculateSubTotal = function(objectName,objectSumTotalName,objectMonthCount){
    	
    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
    	
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,objectMonthCount);
    	
    			
    	//calculate sub total row total
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,objectMonthCount,sumTotalObject);
    };
    
    
    $scope.setZero = function(object,index,model,rowname)
    {
    	console.log("Value entered"+model);
    	if(model=="")
    		{
    		angular.forEach(object, function(value1,key1) {
    			if(key1==index)
    				{
    					if(rowname =="staffingFirstMonthCount")
    						{
    							object[key1].staffingFirstMonthCount=0;
    						}
    					if(rowname =="staffingSecondMonthCount")
						{
							object[key1].staffingSecondMonthCount=0;
						}
    					if(rowname =="staffingThirdMonthCount")
						{
							object[key1].staffingThirdMonthCount=0;
						}
    					if(rowname =="staffingFourthMonthCount")
						{
							object[key1].staffingFourthMonthCount=0;
						}
    					if(rowname =="staffingFifthMonthCount")
						{
							object[key1].staffingFifthMonthCount=0;
						}
    					
    					if(rowname =="staffingSixthMonthCount")
						{
							object[key1].staffingSixthMonthCount=0;
						}
    					
    					if(rowname =="staffingSeventhMonthCount")
						{
							object[key1].staffingSeventhMonthCount=0;
						}
    					
    					if(rowname =="staffingEighthMonthCount")
						{
							object[key1].staffingEighthMonthCount=0;
						}
    					
    					if(rowname =="staffingNinthMonthCount")
						{
							object[key1].staffingNinthMonthCount=0;
						}
    					
    					if(rowname =="staffingTenthMonthCount")
						{
							object[key1].staffingTenthMonthCount=0;
						}
    					
    					if(rowname =="staffingEleventhMonthCount")
						{
							object[key1].staffingEleventhMonthCount=0;
						}
    					
    					if(rowname =="staffingTwelthMonthCount")
						{
							object[key1].staffingTwelthMonthCount=0;
						}
    					
    				}
    			
    		});
    		}
    }
    
    
    $scope.getOtherData = function(data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,object,index,rowname)
    {
    	fpDealStaffingService.calculateOnChangeStaffing($scope,data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,object,index,rowname);
    };
    
    //calculate Sub Total arvind for onsite local & onsite contractor
/*    $scope.calculateSubTotalLoc_Contr = function(objectName,objectSumTotalName,objectMonthCount){    	
    	
    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
    	
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,objectMonthCount);
    	    			
    	//calculate sub total row total
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,objectMonthCount,sumTotalObject);
    };*/
    
  //end calculate Sub Total arvind for onsitelocal & onsitecontractor  
    
    //total onsite
    $scope.getTotalOnsite = function(objectName,objectSumTotalName,index,rowName){
    	 /*var countTableRow1	=  $('#countTableRow1').children('tr').length;
    	 var countTableRow2	=  $('#countTableRow2').children('tr').length;    
    	alert('countTable1Row='+countTableRow1+'  countTableRow2= '+countTableRow2);*/
    	var totalOnsiteObject = fpDealStaffingService.getRowCalculationObject($scope,objectName,index);
    	totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])
    	//+ parseFloat($scope.onsiteContractor[index][rowName])
		+ parseFloat($scope.onsiteDeputed[index][rowName])
		+ parseFloat($scope.onsiteShortTerm[index][rowName]);
    	
    	//alert('parseFloat($scope.onsiteDeputed[index][rowName])='+parseFloat($scope.onsiteDeputed[index][rowName])+' parseFloat($scope.onsiteShortTerm[index][rowName])='+parseFloat($scope.onsiteShortTerm[index][rowName]))
    	/*if(!parseFloat($scope.onsiteDeputed[index][rowName]) || !parseFloat($scope.onsiteShortTerm[index][rowName]))
    		{
	    		totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])
	    		+ parseFloat($scope.onsiteDeputed[index][rowName])
	    		+ parseFloat($scope.onsiteShortTerm[index][rowName]);
    		}
    	else
    		{*/
    		//	totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])    		
	    	//	+ parseFloat($scope.onsiteDeputed[index][rowName]);
	    		
    		//}
  
    	
    	/*angular.forEach($scope.data, function (value, key) {
    		console.log("Visa Id ....."+value.visaId);
    		if(value.visaId == 2 && value.sumTotalType == 0 && value.isContractor == 0 )
    		{
    			index = index+12;
    			totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName]) 
    			+ parseFloat($scope.onsiteDeputed[index][rowName])
    			+ parseFloat($scope.onsiteShortTerm[index][rowName]);    			
    			index=0;
    		}
    		else
    		{
    			totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName]) 
    			+ parseFloat($scope.onsiteDeputed[index][rowName])
    			+ parseFloat($scope.onsiteShortTerm[index][rowName]);
    		}
    	});
    	console.log("Inside the scope");
    	console.log($scope.data[0].visaId); */    	
    	
    	//role wise total    	
    	
    	//role year wise total
    	fpDealStaffingService.calculateRowTotal(totalOnsiteObject);   //row total
    	
    	
    	//year wise total
    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
    	
    	
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross 
    	
    	//calculate sub total row total
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
    	 
    
    	
    };
    //arvind
    $scope.getLocal_Contr = function(objectName,objectSumTotalName,index,rowName)
    {
    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross
    	
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
    };
    $scope.getOnsiteGross = function(objectName,objectSumTotalName,index,rowName)
    {
    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross    	
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
    };
    
   
    $scope.getStaffingGross = function(objectName,objectSumTotalName,index,rowName)
    {
    	 var sumTotalObject =0;
    	
   	//offshore_ContrSumTotal onsiteSumTotal
    	/*console.log("Total Staffing");
    	console.log($scope.onsiteSumTotal[0].staffingFirstMonthCount);
    	console.log("Total Staffing contractor");
    	console.log($scope.offshore_ContrSumTotal[0].staffingFirstMonthCount);
    	
    	angular.forEach($scope.onsiteSumTotal, function (value, key) {
    		
    		angular.forEach($scope.offshore_ContrSumTotal, function(value2,key2) {
    				
    			if($scope.onsiteSumTotal[key].staffingFirstMonthCount != 0 && $scope.offshore_ContrSumTotal[key2].staffingFirstMonthCount  != 0) {
    				sumTotalObject += parseFloat($scope.onsiteSumTotal[key].staffingFirstMonthCount) + parseFloat ($scope.offshore_ContrSumTotal[key2].staffingFirstMonthCount);
    				console.log("sumTotalObject=="+sumTotalObject);
    				console.log($scope.totalSumTotalStaffing); 
    				$scope.totalSumTotalStaffing[index].staffingFirstMonthCount  = sumTotalObject;
    			} 
    			console.log("The Sum Total Object is..");
				console.log(sumTotalObject);
    		});
    	});*/
    	
    	sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross
    	
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
    };
    //end
    //calculate total onsite for contractor
    $scope.calculateContractorTotalOnsite = function(masterRoleId,rowName,rowValue){
    	var totalOnsiteObject = {};
    	angular.forEach($scope.onsiteTotal, function (value, key) {
    		if(value.masterRoleId == masterRoleId ){
    			totalOnsiteObject = value
    		}
    	});
    	totalOnsiteObject[rowName] = parseFloat(rowValue);
    	
    	fpDealStaffingService.calculateRowTotal(totalOnsiteObject);
    };
    
    //calculate total staffing for onsite and offshore
    $scope.calculateTotalStaffing = function(objectName,index,rowName,sumTotalArray){
    	console.log("calculateTotalStaffing ----");
    	console.log(objectName);
    	console.log(index);
    	console.log(rowName);
    	console.log(sumTotalArray);
//    	console.log($scope);
    	var totalStaffingObject = fpDealStaffingService.getRowCalculationObject($scope,objectName,index);
//    	console.log(totalStaffingObject);
//    	
//    	//role wise total
    	totalStaffingObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])
    	+ parseFloat($scope.onsiteDeputed[index][rowName])
    	+ parseFloat($scope.onsiteShortTerm[index][rowName]) 
    	+ parseFloat($scope.offshoreTotal[index][rowName]);
    	
    	//row total calculation
    	fpDealStaffingService.calculateRowTotal(totalStaffingObject);
    	
    	//calculate sum total for total staffing
    	
    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,sumTotalArray);
    	
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);
    	
    	//calculate sub total row total
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
    	
    };
    
    //arvind
    $scope.calculateTotalStaffingContr = function(objectName,index,rowName,sumTotalArray){
    	console.log("calculateTotalStaffing ----");
    	console.log(objectName);
    	console.log(index);
    	console.log(rowName);
    	console.log(sumTotalArray);
//    	console.log($scope);
    	var totalStaffingObject = fpDealStaffingService.getRowCalculationObject($scope,objectName,index);
//    	console.log(totalStaffingObject);
//    	
//    	//role wise total
    	totalStaffingObject[rowName] = parseFloat($scope.onsiteContractor[index][rowName])
    	+ parseFloat($scope.offshoreTotalContr[index][rowName]);    	
    	
    	//row total calculation
    	fpDealStaffingService.calculateRowTotal(totalStaffingObject);
    	
    	//calculate sum total for total staffing
    	
    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,sumTotalArray);
    	
    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
    	
    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);
    	
    	//calculate sub total row total
    	fpDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
    	
    };
    //end arvind
    
  //calculate total staffing for contractor
    $scope.calculateContractorTotalStaffing = function(masterRoleId,rowName,rowValue){
    	var totalStaffingObject = {};
    	angular.forEach($scope.totalStaffing, function (value, key) {
    		if(value.masterRoleId == masterRoleId ){
    			totalStaffingObject = value
    		}
    	});
    	totalStaffingObject[rowName] = parseFloat(rowValue);
    	
    	fpDealStaffingService.calculateRowTotal(totalStaffingObject);
    };
    
    
    $scope.autoSummaryData= function()
    { 
		var flag=true;
		angular.forEach($scope.data, function (value, key) {
    		if(flag){
	    		if(value.monthYearHeader == 'Jan-18/Dec-18'){
	    			//create month header for year header
	    			fpDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
	    			flag = false;	    			
	    		}
    		}
    	});	  		
	
    };
    
    $scope.totalArray = [];
    $scope.marker=[];
    //Save Data
    $scope.save = function(){
    	$scope.totalArray.push($scope.onsiteLocal);
    	$scope.totalArray.push($scope.onsiteContractor);
    	$scope.totalArray.push($scope.onsiteLocal_ContrSumTotal);
    	//$scope.totalArray.push($scope.onsiteLocalSumTotal);
    	$scope.totalArray.push($scope.onsiteDeputed);
    	$scope.totalArray.push($scope.onsiteDeputedSumTotal);
    	
    	$scope.totalArray.push($scope.onsiteShortTerm);
    	$scope.totalArray.push($scope.onsiteShortTermSumTotal);
    	
    	$scope.totalArray.push($scope.onsiteTotal);
    	$scope.totalArray.push($scope.onsiteSumTotal);
    	
    	$scope.totalArray.push($scope.offshoreTotalContr)
    	$scope.totalArray.push($scope.offshoreTotal);
    	$scope.totalArray.push($scope.totalStaffing);
    	
    	$scope.totalArray.push($scope.totalStaffingContr);
    	
    	$scope.totalArray.push($scope.offshore_ContrSumTotal);
    	$scope.totalArray.push($scope.totalSumTotalStaffing);
    
    	
    	//$scope.totalArray.push($scope.onsiteGrossSumTotal);
    	//$scope.totalArray.push($scope.staffingGrossSumTotal);  
    
    	//$scope.totalArray.push($scope.onsiteSumTotalContractor);  	
    	
    	//$scope.totalArray.push($scope.offshoreSumTotal);
    	//$scope.totalArray.push($scope.offshoreSumTotalContr);
    	
    	
    	
    	
    	
    	
    	console.log("========Save Data========");
    	console.log($scope.totalArray);
    	
    	angular.forEach($scope.totalArray, function (value, key) {
    		
    		angular.forEach($scope.totalArray[key], function (value1, key1) {
    			
    			if(value1.monthYearHeader=='Summary')
				{
				console.log("========if Summary========");
				}
			else
				{
					$scope.marker.push(value1);
				}
    			
    			
        	});
    	});
    	
    	//deleting extra flag objects
		angular.forEach($scope.marker, function (value, key) {
    		delete $scope.marker[key].masterRoles;
    		delete $scope.marker[key].staffingDetailsHeader;
    	});
		
		
		var updateFpDealStaffingDetails = function(response) {
			
    		console.log(response);
    		if(response.status == 200) 
    		{
    			$scope.updateStaffingContractorRole(); //call another method for aad staffing contractor role
    		} 
    		else {
    			BootstrapDialog.show({
                    title : 'Fixed Price Deal Staffing',
                    type : BootstrapDialog.TYPE_DANGER,
                    message : "Currently facing techinical issue.",
                    closable : false,
                    buttons : [{
                           label : 'OK',
                           action : function(dialogRef) {
                                 dialogRef.close();
                                /* $window.location.reload();*/
                                 
                           }
                    }]
              });
    		}
    		
    	};
    	
    	
		WebServiceFactory.updateFpDealStaffingDetails($scope.marker).then(updateFpDealStaffingDetails);
    	console.log($scope.marker);
    };
    
    //
    //$scope.marker2=[];
    $scope.updateStaffingContractorRole = function(){
    	
    	 $scope.marker={		
    			 			"stfContrRoleList":$scope.staffingContactorRoleList
    	 			    };
		var updateStaffingContractorRole = function(response) {
			
    	console.log($scope.marker);
		console.log(angular.toJson($scope.marker));
		BootstrapDialog.show({
            title : 'Fixed Price Deal Staffing',
            type : BootstrapDialog.TYPE_PRIMARY,
            message : "Fixed Deal Staffing Updated Successfully.",
            closable : false,
            buttons : [{
                   label : 'OK',
                   action : function(dialogRef) {
                         dialogRef.close();
                        $window.location.reload();
                         
                   }
            }]
      });
			
		};
		
		WebServiceFactory.updateStaffingContractorRole($scope.marker).then(updateStaffingContractorRole);
		
    };
    
    $scope.clearData = function(){
    	
    	 	$scope.yearHeader=[];    	    
    	    //Month header list
    	    $scope.monthHeader=[];
    	    
    	    $scope.onsiteLocal = [];
    	    $scope.onsiteLocalSumTotal = [];
    	    $scope.onsiteLocal_Contractor = []
    	    $scope.offshore_Contractor = []
    	    $scope.onsiteGross = []
    	    $scope.staffingGross = []
    	    $scope.onsiteLocal_ContrSumTotal = [];
    	    $scope.offshore_ContrSumTotal = []
    	    $scope.onsiteGrossSumTotal = [];
    	    $scope.staffingGrossSumTotal = [];
    	    //onsite deputed staffing
    	    $scope.onsiteDeputed = [];
    	    $scope.onsiteDeputedSumTotal = [];

    	    //onsite short term staffing
    	    $scope.onsiteShortTerm= [];
    	    $scope.onsiteShortTermSumTotal = [];
    	    
    	    //onsite sub total
    	    $scope.onsiteTotal = [];
    	    $scope.onsiteSumTotal = [];
    	    
    	    //onsite contractor
    	    $scope.onsiteContractor=[];
    	    $scope.onsiteSumTotalContractor=[];
    	    
    	    //offshore sub total
    	    $scope.offshoreTotal = [];
    	    $scope.offshoreSumTotal = [];
    	    $scope.offshoreSumTotalContr = [];
    	    $scope.offshoreTotalContr = [];
    	    //total staffing
    	    $scope.totalStaffing = [];
    	    $scope.totalStaffingContr = [];
    	    $scope.totalSumTotalStaffing = [];
    	    $scope.colors = [];
    	
    }
    
    
    
    
   /* $scope.clear = function(){    	
	 	$scope.yearHeader=[];    	    
	    //Month header list
	    $scope.monthHeader=[];	  
    	
    	data.staffingFirstMonthCount=0;
	    $scope.onsiteLocal = [0];
	    $scope.onsiteLocalSumTotal = [0];
	    $scope.onsiteLocal_Contractor = [0];
	    $scope.offshore_Contractor = [0];
	    $scope.onsiteGross = []
	    $scope.staffingGross = []
	    $scope.onsiteLocal_ContrSumTotal = [];
	    $scope.offshore_ContrSumTotal = []
	    $scope.onsiteGrossSumTotal = [];
	    $scope.staffingGrossSumTotal = [];
	    //onsite deputed staffing
	    $scope.onsiteDeputed = [];
	    $scope.onsiteDeputedSumTotal = [];
	    //onsite short term staffing
	    $scope.onsiteShortTerm= [];
	    $scope.onsiteShortTermSumTotal = [];	    
	    //onsite sub total
	    $scope.onsiteTotal = [];
	    $scope.onsiteSumTotal = [];	    
	    //onsite contractor
	    $scope.onsiteContractor=[];
	    $scope.onsiteSumTotalContractor=[];	    
	    //offshore sub total
	    $scope.offshoreTotal = [];
	    $scope.offshoreSumTotal = [];
	    $scope.offshoreSumTotalContr = [];
	    $scope.offshoreTotalContr = [];
	    //total staffing
	    $scope.totalStaffing = [];
	    $scope.totalStaffingContr = [];
	    $scope.totalSumTotalStaffing = [];   
	
};*/
    
    $scope.clear=function()
	{
		
		angular.forEach($scope.data, function (value, key) {
			
			if(typeof $scope.data[key].staffingFirstMonthCount != 'number' ){}					
			else
			{
				$scope.data[key].staffingFirstMonthCount=0;
			}
			
			if(typeof $scope.data[key].staffingSecondMonthCount != 'number' ){}					
			else
			{
				$scope.data[key].staffingSecondMonthCount=0;
			}
			
			if(typeof $scope.data[key].staffingThirdMonthCount != 'number' ){}					
			else
			{
				$scope.data[key].staffingThirdMonthCount=0;
			}
			
			if(typeof $scope.data[key].staffingFourthMonthCount != 'number' ){}					
			else
			{
				$scope.data[key].staffingFourthMonthCount=0;
			}
			if(typeof $scope.data[key].staffingFifthMonthCount != 'number' ){}					
			else
			{
				$scope.data[key].staffingFifthMonthCount=0;
			}
			
			if(typeof $scope.data[key].staffingSixthMonthCount != 'number' ){}					
			else
			{
				$scope.data[key].staffingSixthMonthCount=0;
			}
			
			if(typeof $scope.data[key].staffingSeventhMonthCount != 'number' ){}					
			else
			{
				$scope.data[key].staffingSeventhMonthCount=0;
			}
							
			if(typeof $scope.data[key].staffingEighthMonthCount != 'number' ){}				
			else
			{ 
				$scope.data[key].staffingEighthMonthCount=0; 
			}
			
			if(typeof $scope.data[key].staffingNinthMonthCount != 'number' ){}				
			else
			{ 
				$scope.data[key].staffingNinthMonthCount=0; 
			}
			
			if(typeof $scope.data[key].staffingTenthMonthCount != 'number' ){}				
			else
			{ 
				$scope.data[key].staffingTenthMonthCount=0; 
			}
			
			if(typeof $scope.data[key].staffingEleventhMonthCount != 'number' ){}				
			else
			{
				$scope.data[key].staffingEleventhMonthCount=0;
			}
			
			if(typeof $scope.data[key].staffingTwelthMonthCount != 'number' ){}				
			else
			{
				$scope.data[key].staffingTwelthMonthCount=0;
			}
			$scope.data[key].yearlyTotal = 0;	    		
    		
		}); 
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

	WebServiceFactory.getDealDetails($localStorage.DealModel,$scope.rpVrsId).then(getDealDetails);
	
	// download Pricing Template
	$scope.downloadPricingTemplate = function() {	
		var dealId = $scope.dealDetails[0].dealId;
		var versionId = $localStorage.rpDealVersionId
		window.location= contextPath+"/RightPrice-DAS/downloadPricingTemplateExcel/"+dealId+"/"+versionId
	};
	
	//Anmol_Singh
	
	 $scope.exportToExcel = function()
		{
		     // alert($localStorage.rpDealVersionId+"/"+$sessionStorage.staffing_header.cityId+"/"+ $sessionStorage.staffing_header.dealTowerId+"/"+$scope.dealDetails[0].dealId);
	    		window.location= contextPath+"/RightPrice-DAS/downloadFPDealCreationStaffingExcel/"+$localStorage.rpDealVersionId+"/"+$sessionStorage.staffing_header.cityId+"/"+ $sessionStorage.staffing_header.dealTowerId+"/"+$scope.dealDetails[0].dealId;
	    		
		};
		
		// upload functionality in Staffing Page
		$scope.updateStaffingAttachment=function(frmDeal){

			//alert("uploadFileToUrl");
			//console.log("called uploadPOExcel");
			var fileTest = $("#fuAttachFilename");	
			//console.log(fileTest.length);
			var noOfTowers=$sessionStorage.towerCount;
			var name = "Template";
			var tempID = 1;	
			var rpDealVersionId = $localStorage.rpDealVersionId;
			var dealAutoTowerId = $scope.dealAutoTowerIDVal;
			var docType =frmDeal.sheetTypeModel;
			var file = $('input[name="fuAttachFilename"]').get(0).files[0];
			//console.dir(file);
			var formData = new FormData();
			formData.append('file', file);
			formData.append('name', name);
			formData.append('TempId', tempID);
			formData.append('rpDealVersionId', rpDealVersionId);
			formData.append('dealAutoTowerId',dealAutoTowerId);
			//console.log(file);
			var uploadUrl = contextPath+"/RightPrice-DAS/uploadStaffingExcel";
		    $http.post(uploadUrl, formData, {
		        transformRequest: angular.identity,
		        headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
		    }).
		    then(function(data) {
		    	//console.log("data.data[0]");
		    	//console.log(data);
		    	var obj = JSON.stringify(data);
				var json = JSON.parse(obj);
				var customMessage = data.data;
		    	if(data.status == 200)	
				{
					BootstrapDialog.show({
						title : 'FP Deal Creation - Staffing.',
						type : BootstrapDialog.TYPE_PRIMARY,
						message :  customMessage,
						closable : false,
						buttons : [ {
							label : 'OK',
							action :function(
									dialogRef) {
								dialogRef.close();
								window.location="FPDealCreationStaffing";
							}
						} ]
					});
					angular.element("input[type='file']").val(null);
					$scope.searchFile(versionId);
				} 
		    	else if(data.status == 205){
						BootstrapDialog.show({
						title : 'FP Deal Creation - Staffing.',
						type : BootstrapDialog.TYPE_DANGER,
						message : "File Upload Failed.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location="FPDealCreationStaffing";
							}
						} ]
					});
				}
				else {
					BootstrapDialog.show({
					title : 'FP Deal Creation - Staffing.',
					type : BootstrapDialog.TYPE_DANGER,
					message :  customMessage,
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
		
		// download functionality from FTP
		$scope.downloadFileWithFileName = function(dealAttachmentId){
			WebServiceFactory.downloadFileWithFileName(dealAttachmentId);
		};
		
		$scope.deleteManualFile = function(attachementId){
			var deleteManualFile = function(response) {
				$scope.deleteResponse = response;
				
				if($scope.deleteResponse.status == 200){
					$scope.message = $scope.deleteResponse.data;
	    	        BootstrapDialog.show({
	    	        	title : 'FP Deal Creation - Staffing',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : $scope.message,
	    	        	closable : false,
	    	        	buttons : [{
	    	        		label : 'OK',
	    	        		action : function(dialogRef) {
	    	        			dialogRef.close();
	    	        			window.location = "FPDealCreationStaffing";
	    	        		}
	    	        	}]
	    	        });
				}else{
	    			$scope.message = $scope.deleteResponse.data;
	    			BootstrapDialog.show({
	    	        	title : 'FP Deal Creation - Staffing',
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
		
		$scope.getRolesExcel = function() 
		{			
			if($scope.dealAutoTowerIDVal > 0)				
			{
				var autoDealTowerId = $scope.dealAutoTowerIDVal;
				var towerId = $sessionStorage.staffing_header.dealTowerId;
				var towerName  = $scope.frmStaffing.dealStaffingDetailsTowerModel.towerName;
				var country =$scope.frmStaffing.dealStaffingDetailsCountryModel;
				var city = $scope.frmStaffing.dealStaffingDetailsCityModel;
				var dealVersionId = $localStorage.rpDealVersionId;
				var currencyNm = $scope.dealDetails[0].currency;
				var dealVersion = $scope.dealDetails[0].dealVersion;
				//alert("TowerID : "+ $sessionStorage.staffing_header.dealTowerId + "AutoDealTowerId : " + $scope.dealAutoTowerIDVal + " VersionId: " + $localStorage.rpDealVersionId);
				window.location= contextPath+"/RightPrice-DAS/downloadRolesExcel/"+towerId+"/"+autoDealTowerId+"/"+dealVersionId+"/"+towerName+"/"+currencyNm+"/"+country+"/"+city+"/"+dealVersion;
			}
			else
			{
				alert("Please select tower in Staffing details section and click on search");
			}
			
			
		};
}]);