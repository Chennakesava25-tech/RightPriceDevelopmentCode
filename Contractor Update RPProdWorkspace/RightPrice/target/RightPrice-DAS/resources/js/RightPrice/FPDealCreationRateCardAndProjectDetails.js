//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("FPDealCreationRateCardAndProjectDetailsController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
	 
		//model initialization 
		$scope.addTowerDtForm={};
	//	$scope.currentRpVersionId = $localStorage.rpDealVersionId;
	//	$scope.currentcrmDealId = $localStorage.DealModel;			
	//	var abc=$sessionStorage.DealModel;
		$scope.isFPDealGFT = false;
		$scope.isFPDealRiskManagers = false;
		var rpVrsId = $localStorage.rpDealVersionId;
		var  customerId= $sessionStorage.customerId; 
		var deal_Id= $localStorage.DealModel;
		var userType = sessionStorage.getItem('userType');
		 console.log("The USer Value from the Session is........ "+ userType);
		 if(userType == 'GFT') {
			 $scope.isFPDealGFT = true;
		 }
		 else if(userType == 'RiskManagers') {
				$scope.isFPDealRiskManagers = true;
			}
		//getting customerId from prev page
	//	alert('$sessionStorage.DealModel= '+$sessionStorage.DealModel+' $localStorage.rpDealVersionId='+$localStorage.rpDealVersionId+' $localStorage.DealModel='+$localStorage.DealModel);
	 $scope.RegionWiseHidden = true;
	 $scope.AddLocationHidden = true;
	 $scope.TowerDetailsHidden = true;
	 $scope.AddContractorRoleHidden = true; 
	 $scope.RateCardDetailsHidden = false;
	 $scope.isCurrStatus=false;
	 $scope.fpdealnormal=false;
	 $scope.fpdealmanual=false;
	 $scope.isDevelopment=false;
	// $scope.isRateCardDetails=true; //for hidden
	 $scope.isRateCardDetails=false; 
	 $scope.dealTower = [];
	 $scope.dealFPTower = [];
	 $scope.dealFPRateCard = [];
	 $scope.rateCardData = [];
	 
	 $scope.ShowHideRegionWise = function () {
	     $scope.RegionWiseHidden = $scope.RegionWiseHidden ? false : true;
	 };
	 $scope.ShowHideAddLocation = function () {
	     $scope.AddLocationHidden = $scope.AddLocationHidden ? false : true;
	 };
     $scope.ShowHideRateCardDetails = function () {
        $scope.RateCardDetailsHidden = $scope.RateCardDetailsHidden ? false : true;
    };
    $scope.ShowHideTowerDetails = function () {
        $scope.TowerDetailsHidden = $scope.TowerDetailsHidden ? false : true;
    };
	$scope.Prev = function() 
    {   
        window.location='FPDealCreationDetails';
    };
    $scope.Next = function()
    {  
    	//Start Added by khyati
    	console.log("Click on next----page 1"+$localStorage.rpDealVersionId);
    	console.log($scope.chkBoxSelectedCountryList);
    	console.log($scope.locationDetails);
    	$sessionStorage.ContryList = $scope.chkBoxSelectedCountryList;
    	$sessionStorage.CityList = $scope.locationDetails;
    	$localStorage.rpDealVersionId = $localStorage.rpDealVersionId;
    	console.log($sessionStorage.ContryList);
    	//End
		if($sessionStorage.manualDeal==1)
	
                   {

                    window.location='FPDealCreationManualDealSummary';

                   }

           else{
        window.location='FPDealCreationRoleSelection';
		}

	/*	var rateCardIds = "" ;
		for(i=0;i<$scope.rateCardDetails.length;i++)
		{
		if(i==($scope.rateCardDetails.length-1)){
			rateCardIds = rateCardIds + $scope.rateCardDetails[i].rcId
		}else{
			rateCardIds = rateCardIds+ $scope.rateCardDetails[i].rcId +"; "
		}	
		}
		$sessionStorage.RateCard=rateCardIds;*/
    };  

    /*  //show and add ratecard
    var getRateCardName = function(response) {
    	//alert('55 getRateCardName'+$localStorage.DealModel);
		//console.log("ratecardName")
		$scope.rateCardName = response.data;
		console.log($scope.rateCardName );
	};
	WebServiceFactory.getRateCardName(customerId).then(getRateCardName);*/
	
	//getting data into ratecard table 17th Jan2018
	 var rpversionId= $localStorage.rpDealVersionId;	
	 //a no need to add below line 
	 /* var showRateCardTable1 = function(response) {
		 // alert('hi rpversionId='+rpversionId);
			$scope.tableData = response.data;	
			console.log("$scope.rateCardDetails---------------------------------------");			
			$scope.rateCardDetails=[];			
			angular.forEach($scope.tableData, function (value, key) {
    			console.log(value);
    			$scope.rateCardDetails.push({    				
					"rcName": value.rateCrdDetails.rcName,
					"rcId":	value.rateCrdDetails.rcId
    			});
    		});			
			console.log("Reate Card Details from tale Data.........")
			console.log($scope.rateCardDetails);
			//$scope.rateCardDetails[0].rcName=rcName;
		};		
		WebServiceFactory.showRateCardTable1(rpversionId).then(showRateCardTable1);*/
		
    //end Ratecard
    
    
//    alert($localStorage.DealModel);
   /* var getDealDetails = function(response) {
  		console.log(response);
  		$scope.dealDetails = response.data;
  		console.log("Approver Data")
  		console.log($scope.dealDetails);
  		console.log($scope.dealDetails[0].dealId);
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
		
		$scope.DealDuration = Math.abs(dateENd - dateStart);
		
        
  		console.log($scope.dealDetails[0].percentageClose);
  		console.log($scope.dealDetails[0].currencyId);
  		console.log($scope.dealDetails[0].dealDuration);
  		console.log($scope.dealDetails[0].stageId);
	};

WebServiceFactory.getDealDetails($localStorage.DealModel).then(getDealDetails);*/
    //arvind for info. table
    var getDealDetails = function(response) {
		
  		console.log(response);
  		$scope.dealDetails = response.data;
  		console.log("Service Data");
  		console.log(response.data);
  		console.log("Approver Data");
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
  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
  		$scope.dealDetails[0].dealEndDate = dateENd;
  		
  		
		var dealStartDate = $scope.dealDetails[0].dealStartDate;
		var date = new Date(dealStartDate.substring(0,10));
		var dateStart = $filter('date')(date,'dd/MM/yyyy');
		$scope.dealDetails[0].dealStartDate = dateStart;
		
		$sessionStorage.rpDealStartDate = $scope.dealDetails[0].dealStartDate; 
		
		// alert('hi 120');
		$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	//arvind
		$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;
		
			
		 var startDay = new Date(dateStart);
          var endDay = new Date(dateENd);
          var millisecondsPerDay = 1000 * 60 * 60 * 24;

          var millisBetween =  endDay.getTime()-startDay.getTime() 
          var days = millisBetween / millisecondsPerDay;
          $scope.dealDetails[0].DealDuration = Math.floor(days);
		
        
  		console.log($scope.dealDetails[0].percentageClose);
  		console.log("The currency Id is........... ");
  		console.log($scope.dealDetails[0].currencyId);
  		console.log($scope.dealDetails[0].dealDuration);
  		console.log($scope.dealDetails[0].stageId);
  		
	};
	
	WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails)
    // 
    
    		
	    	var getVersionData =function(response)
	    	{
	    		console.log("Version Data");
	    		console.log(response);
	    		$scope.versionDetails = response.data;
				
	    		
	    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||  $scope.versionDetails[0].currentApprovalStatus == 4 || $scope.versionDetails[0].currentApprovalStatus == 8 )&& userType == 'Delivery')
	    			{
	    				$scope.isCurrStatus=false;
	    			}
	    		else
	    			{
	    			    $scope.isCurrStatus=true;
	    			}
	    		
	    		$localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
	    		if(userType == 'Delivery') {
	    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
	    				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<1)
	    			{
	    			BootstrapDialog.show({
						title : 'FP Deal Creation - Rate Card and Project Details',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'Data is not saved at previous screen.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								//$window.location.reload();
								window.location="FPDealCreationDetails";
							}
						} ]
					});
	    			}
	    		}
				 $sessionStorage.manualDeal=$scope.versionDetails[0].isManualDeal;
				
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
	    		
	    		if($scope.versionDetails[0].isManualDeal == 1) {
	    			$sessionStorage.isManualDealFlag = $scope.versionDetails[0].isManualDeal;
                    $scope.isRateCardDetails = true;
                    $scope.fpdealnormal=false;
        			$scope.fpdealmanual=true;

                     } else {
                       $sessionStorage.isManualDealFlag = $scope.versionDetails[0].isManualDeal;
                       $scope.isRateCardDetails = false;
                       $scope.fpdealnormal=true;
           				$scope.fpdealmanual=false;

                        }
	    		if($scope.versionDetails[0].fpProjectTypeId==1 || $scope.versionDetails[0].fpProjectTypeId==2){
	    			$scope.isDevelopment=true;	
	    		}
	    		else{
	    			$scope.isDevelopment=false;
	    		}
	    		
	    	};
	    	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
    
	    	
	    	$scope.showRCDetails = function(rcId){
				
				console.log("showRateCardDetails");
				console.log(rcId);
				$localStorage.rcId = rcId;
				
				var browser = window.navigator.appVersion;

	              //Workaround to enable the users to download the report in IE.
	              if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
	                     (browser.indexOf('MSIE 10') !== -1)) 
	              {
	            	  window.open ("RateCardCreationDetails","$localStorage.rcId");
//	            	  window.open ("RightPrice/RateCardCreationDetails","$localStorage.rcId");
	              } 
	              else 
	              {
	            	  window.open ("RateCardCreationDetails","_blank","$localStorage.rcId");
//	            	  window.open ("RightPrice/RateCardCreationDetails","_blank","$localStorage.rcId");
	              }
			};
	    	
    /*...[PA5027293] */
    
    //data to-from local storage
    var dealVersionId=$localStorage.rpDealVersionId;    //get it from local storage
    $scope.txtTowerNameModel="";
    //scope variable initialization
    	$scope.country=[];
    	$scope.regionWiseUtilizationDetails=[];
    	$scope.chkBoxSelectedCountryList=[];
    	$scope.locationDetails=[];
    	$scope.city=[];
    	$scope.towerDetails=[];
    	$scope.rateCardDetails=[];
    	 $scope.totalUtilization=0;
    	
    //custom functions
    	
    	$scope.calculateToatlUtilization=function(){
			var total=0;
			angular.forEach($scope.regionWiseUtilizationDetails, function (value, key) {
				if(value.selectChkBox){
					total += parseInt(value.utilization);
				}
	    	});
			$scope.totalUtilization = total;
		};
		
		 /*$scope.addUtilization = function(selectedCountry){
     		 
	     		var indexOfSelectedCountry = $scope.regionWiseUtilizationDetails.indexOf(selectedCountry);
	     		//console.log(selectedCountry);
	     		if(selectedCountry.selectChkBox) {
	     			//check box selected 
	     				$scope.calculateToatlUtilization();
	     				
	     			//add check box selected country in Array
		     			$scope.chkBoxSelectedCountryList.push({
		     				"countryId":selectedCountry.countryId,
		     				"countryName":selectedCountry.countryName
		     			});	
	     		} else {
	     			//check box ! selected 
	     				selectedCountry.utilization=0;
	     				$scope.calculateToatlUtilization();
	     				
	     			//remove country from array of selected country list shown in country DDL
	     				angular.forEach($scope.chkBoxSelectedCountryList, function(value, key) {
	     					if(value.countryId === selectedCountry.countryId){
	     						$scope.chkBoxSelectedCountryList.splice( $scope.chkBoxSelectedCountryList.indexOf(value),1);
	     					}
	     				});
	     				
	 				//remove location utilization details from array of location details
	     				
	     				 * if we try to splice current element and if multiple entries are available for the key,
	     				 * array content gets change due to index violation issue.
	     				 * So instead of splice, rebuild and reassign array with required content.	
	     				     				
	     				var newTempLocationDetailsArr=[];
		 				angular.forEach($scope.locationDetails, function(locDetailsValue, key) {
		 					console.log(locDetailsValue.countryName + " : " + locDetailsValue.cityName)
		 					if(!(locDetailsValue.countryId === selectedCountry.countryId)){
		 						//if current country is not unchecked, push it into new created array
		 						newTempLocationDetailsArr.push(locDetailsValue);
		 					}
		 				});
		 				//reassign array to old content
		 				$scope.locationDetails=newTempLocationDetailsArr;
	     				
	     		}
	     		console.log("selectedCountry");
	     		console.log($scope.chkBoxSelectedCountryList);
	     		
	     	 };*/
	     	 $scope.flag=0;
    	    $scope.getCities = function(countryId,index,towerDetail,cityId){
    		$scope.towerDetails;
    		console.log("THe country Id sis........... ");
    		console.log(countryId);
    		console.log(cityId);
    		angular.forEach($scope.country,function(value,key) {
    			if(countryId == $scope.country[key].countryId) {
    				$scope.currency = $scope.country[key].currencyCode;
    				
    			}
    		});
    		
    		angular.forEach($scope.towerDetails, function(value,key){
    			if($scope.towerDetails[key].countryId == countryId) {
    				$scope.towerDetails[key].currency = $scope.currency;
    				
    			}
    		});
    		
    		
    		
    		console.log("The Tower Details after City is..........");
    		console.log($scope.towerDetails);
    		console.log($scope.rateCardDetails);
    		
        	var getCities = function(response) {
        		$scope.city = response.data;
        		console.log("City Names are...........");
        		console.log($scope.city);
        		angular.forEach($scope.towerDetails,function(value,key){
        			if(index == key && $scope.towerDetails[key].countryId == countryId ) {
        				$scope.towerDetails[key].city = angular.copy($scope.city);
        			}
        		});
        	};
        	WebServiceFactory.getCities(countryId).then(getCities);
        	 $scope.flag=1;
        	 if ($scope.flag==1){
        		 
        		 angular.forEach($scope.towerDetails,function(value,key){
        			 if(index == key ){
        				 $scope.towerDetails[key].description="";
        			 }
        			 
         		});
        		 
         	}
        };
        

        
        $scope.getDescription = function(cityId,index,towerDetail){
        	console.log(cityId);
        	console.log(towerDetail);
        	console.log(index);
        			if(cityId!=undefined){
        			var getDescription = function(response) {
                		$scope.desc = response.data;
                		console.log("Description is...........");
                		console.log($scope.desc);
                		/*console.log($scope.desc.cityCategory.description);*/
                		angular.forEach($scope.desc,function(value,key){
            				if($scope.desc[key].cityId==cityId)
            					{
            					$scope.description=$scope.desc[key].cityCategory.description;
            					$scope.categoryId = $scope.desc[key].categorizationId;
            					$scope.towerDetails[index].description=$scope.description;
            					$scope.towerDetails[index].cityCategoryId= $scope.categoryId;
            					}
            			
            			});
                	};
        	
                	WebServiceFactory.getDescription(cityId).then(getDescription);
        			}
        			 angular.forEach($scope.towerDetails,function(value,key){
            			 if(index == key ){
            				 $scope.towerDetails[key].description="";
            			 }
            			 
             		});
        };
        
      
    
        
		//add rate card rows
		 $scope.addRateCardRow =function(rateCard_Name,towerName,addRCDtForm,addDetails){
					angular.forEach($scope.towerData,function(value,key){
						if($scope.towerData[key].dealTowerId == $scope.towerName) {
							$scope.towerDetailName = $scope.towerData[key].dealTowerName;
							
						}
					})
					
					angular.forEach($scope.addDetails,function(value,key){
							$scope.rcIds = $scope.addDetails[key].rcId;
							$scope.startdate = $scope.addDetails[key].startdate;
							$scope.endDate = $scope.addDetails[key].endDate;
					})
					
					$scope.rateCardDetails.push({
						"dealVersionId" : $localStorage.rpDealVersionId,
						"rcId":$scope.rcIds,
						"rcName":$scope.rateCard_Name.rcName,
						"towerId": $scope.towerName,
						"dealTowerName" : $scope.towerDetailName,
						"startdate":$scope.startdate,
						"enddate":$scope.endDate,
					});
				
				var currentRpVersionId1 = $localStorage.rpDealVersionId;
				//alert('currentRpVersionId=='+currentRpVersionId1);
				$scope.viewRatecardData(currentRpVersionId1);
				console.log("check here......==========================.....");
				console.log($scope.rateCardDetails);
			
			 
		 };
		 $scope.viewRatecardData = function(currentRpVersionId)
			{
				//alert('currentRpVersionId='+currentRpVersionId);
			}
		
		//remove ratecard data
		 $scope.removeRateCard=function(index){
				$scope.rateCardDetails.splice(index,1);
				
			};
			
		$scope.removeLocation=function(index){
			$scope.locationDetails.splice(index,1);
		};
		
		    $scope.index = 0;
     		$scope.addTowerDetails = function(addTowerDtForm){ 
     		//$scope.onSave= true;		
    		$scope.temp=0;
			$scope.addTowerDtForm.txtTowerNameModel=addTowerDtForm.txtTowerNameModel;
			for(i=0;i<$scope.towerDetails.length;i++){
			if($scope.addTowerDtForm.txtTowerNameModel==$scope.towerDetails[i].towerName){
			$scope.temp=1;
			break
			}
			}
			if($scope.temp==0){
			if($scope.addTowerDtForm.txtTowerNameModel != undefined ) {
				if($scope.towerDetails != undefined) {
					$scope.index = $scope.towerDetails.length+1;
				}
				$scope.towerDetails.push({
					"rpDealVersionId" : $localStorage.rpDealVersionId,
					"towerName":$scope.addTowerDtForm.txtTowerNameModel,
					"country":$scope.country,
					"currency": null,
					"dealTowerId" : $scope.index
				});
				console.log("The Tower Details........");
				console.log($scope.towerDetails);
				$scope.towerData.push ({
					"rpDealVersionId" : $localStorage.rpDealVersionId,
					"dealTowerName":$scope.addTowerDtForm.txtTowerNameModel,
					"country":$scope.country,
					"currency": null,
					"dealTowerId" : $scope.index
				});
				console.log("The Deal Tower Data is........ ");
				console.log($scope.towerData);
			} 
			addTowerDtForm.txtTowerNameModel=null;
			}
			else {
				addTowerDtForm.txtTowerNameModel=null
				 BootstrapDialog.show({
						title : 'FP Deal Creation - Rate Card and Project Details',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'Tower Name Already Exist.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								//$window.location.reload();
								// window.location="RateCardCreationRoleSelection";
					  }
					} ]
				});
			}
		};
		
		$scope.removed=false;
		$scope.removeTowerDetail = function(addTowerDtForm,index){
			var myindex = index;
			var length=$scope.rateCardDetails.length;
			for(var i = length; i--;){
				if($scope.rateCardDetails[i].dealTowerName==$scope.towerDetails[index].towerName && length!=0){
					$scope.rateCardDetails.splice(i,1)
				}
			}
			$scope.towerDetails.splice(index,1);
			$scope.towerDetails;
			$scope.towerData.splice(index,1);
			//console.log($scope.rateCardDetails);
		};
		
		$scope.onSave= false;	
		$scope.save = function(addTowerDtForm,addRCDtForm){
		$scope.addTowerDtForm.txtTowerNameModel=addTowerDtForm.txtTowerNameModel;
		/*$scope.towerDesc=towerDetail.towerDescription;
		$scope.country=towerDetail.countryId;
		$scope.city=towerDetail.cityId;*/
			$scope.onSave= true;
			if(addTowerDtForm.$valid||addRCDtForm.$valid){
				$scope.onSave= false;	
				$scope.saveFPDealCreationRCAndProjectDetails(addTowerDtForm,addRCDtForm);
			}
		};
		
			
		$scope.saveFPDealCreationRCAndProjectDetails = function(addTowerDtForm,addRCDtForm){
			$scope.onSave= false;
			angular.forEach($scope.towerDetails, function(value,key){
				if($scope.towerDetails.length ==1 ) {
					$scope.towerDetails[key].dealTowerId =1;
				}
				$scope.dealFPTower.push ({
					"rpDealVersionId" : $scope.towerDetails[key].rpDealVersionId,
					"dealTowerName" : $scope.towerDetails[key].towerName,
					"dealTowerDescription" : $scope.towerDetails[key].towerDescription,
					"countryId" : $scope.towerDetails[key].countryId,
					"cityId" : $scope.towerDetails[key].cityId,
					"dealTowerId" : $scope.towerDetails[key].dealTowerId,
					"description":$scope.towerDetails[key].description,
					"transitionMonth":$scope.towerDetails[key].transitionMonth
				});
			});
			
			angular.forEach($scope.rateCardDetails, function(value,key) {
				$scope.dealFPRateCard.push ({
					"dealRCId" : $scope.rateCardDetails[key].rcId,
					"dealVersionId" : $scope.rateCardDetails[key].dealVersionId,
					"dealTowerId" : $scope.rateCardDetails[key].towerId
				});
			});
            
			if($scope.dealFPTower.length > 0) {
				
				var marker={
						"dealFPRateCard" : $scope.dealFPRateCard,
						"dealFPTower":$scope.dealFPTower,
				};
				console.log(marker);
				console.log(angular.toJson(marker));
				
				var saveFPDealCreationRCAndProjectDetails=function(response){
					console.log("data");
					console.log(response);
					console.log(response.status);
					if( response.status == 200){
						BootstrapDialog.show({
							title : 'FP Deal Creation - Rate Card and Project Details',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'Tower Details created successfully.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									//$window.location.reload();
									window.location="FPDealCreationRateCardAndProjectDetails";
								}
							} ]
						});
					}
					
				};
				WebServiceFactory.saveFPDealCreationRCAndProjectDetails(marker).then(saveFPDealCreationRCAndProjectDetails);
			} else {
				BootstrapDialog.show({
					title : 'FP Deal Creation - Rate Card and Project Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Please Add One Tower',
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							window.location="FPDealCreationRateCardAndProjectDetails";
						}
					} ]
				});
			}
		};
    //onload function call
    	


    	
		
		$scope.towerData=[]
		var getFPDealCreationRCAndProjectDetails=function(response){
			console.log("data");
			console.log(response.data);
			//push tower details
			angular.forEach(response.data.dealFPTower,function(value,key){
				$scope.getCities(value.countryId,key);
				$scope.getDescription(value.cityId,key);
				$scope.towerDetails.push({
					"country": $scope.country,
					"countryId" : value.countryId,
					"towerName":value.dealTowerName,
					"towerDescription":value.dealTowerDescription,
					"city": $scope.city,
					"cityId": value.cityId,
					"rpDealVersionId":value.rpDealVersionId,
					"dealTowerId":value.dealTowerId,
					"description":value.description,
					"transitionMonth":value.transitionMonth,
				});
				
				$scope.towerData.push ({
					"dealTowerName":value.dealTowerName,
					"dealTowerId" : value.dealTowerId
				});
				
				angular.forEach($scope.towerDetails, function(value1,key1){
	    			if($scope.towerDetails[key1].countryId == value.countryId) {
	    				$scope.towerDetails[key1].currency = $scope.currency;
	    			}
	    		});
				
				
			});
			
			/*angular.forEach($scope.towerDetails,function(value,key){
				angular.forEach(value.country, function(value,key){
					if(value.country[key].countryId == value.countryId) {
						$scope.towerDetails[key].currency = value.country[key].countryCode;
					}
				});
			})*/
			
			//push ratecard details
			console.log("THe RateCard data is.......... ");
			console.log(response.data);
			angular.forEach(response.data.dealFPRateCard,function(value,key){
					if(value.isActive != 0) {
						$scope.rateCardDetails.push({
							"rcId":value.dealRCId,
							"dealVersionId":value.dealVersionId,
							"towerId":value.dealTowerId
						});
					}
					
					/*if($scope.rateCardDetails != undefined) {
						$scope.index = $scope.rateCardDetails.length+1;
					}
					
					angular.forEach($scope.rateCardDetails,function(value,key){
						if($scope.rateCardDetails[key].towerId == undefined) {
							$scope.rateCardDetails[key].towerId = $scope.index;
						}
				
					});*/
			});
			
			//$scope.rateCardNameList = $localStorage.rateCardData;
			
			angular.forEach($scope.rateCardDetails, function(value,key){
				$scope.getDealRateCardDetails(value.rcId);
			});
			
			angular.forEach($scope.rateCardDetails,function(value,key){
				angular.forEach($scope.towerData,function(value1,key1){
					if($scope.towerData[key1].dealTowerId == $scope.rateCardDetails[key].towerId) {
						$scope.rateCardDetails[key].dealTowerName = $scope.towerData[key1].dealTowerName; 
					}
				});
			});
			
			//push details in dealCurrencyUtilization
			angular.forEach(response.data.dealCurrencyUtilization, function (value, key) {
    			//iterate through each array obj and update if country Id matches
    			//set checkbox property to true
    			angular.forEach($scope.regionWiseUtilizationDetails, function(item, key) {
    			    if(item.countryId === value.baseCountryId)  {
    			       console.log("updating country : " +item.countryName);
    			    	item.selectChkBox = true;
    			        item.utilization=value.utilization;
    			        
    			        //push country details in DDL of select country of add location details
    			        $scope.chkBoxSelectedCountryList.push({
    	     				"countryId":item.countryId,
    	     				"countryName":item.countryName
    	     			});
    			    }
    			});
            });
			
			//push location details
			angular.forEach(response.data.dealLocation, function (value, key) {
    			console.log(value);
    			$scope.locationDetails.push({
    				"countryId":value.city.country.countryId,
					"countryName": value.city.country.countryName,
					"cityId":value.city.cityId,
					"cityName":value.city.cityName,
					"utilization": value.utilization
    			});
    		});
			
			$scope.calculateToatlUtilization();
		};
    
		WebServiceFactory.getFPDealCreationRCAndProjectDetails(dealVersionId).then(getFPDealCreationRCAndProjectDetails);
		
	/*	// Getting the rC details based on the Customer
		$scope.getRCDetails = function(towerArray,towerName) {
			console.log("Inside the RC details..... ");
			console.log(towerArray.dealTowerId);
			var towerId = towerArray.dealTowerId;
			$scope.cityId = towerArray.cityId;
			console.log("The city id is............. "+$scope.cityId);
			var startDate = $sessionStorage.rpDealStartDate;
			var  customerId= $sessionStorage.rpCustomerId;
			var getRCDetails = function(response) {
				$scope.rateCardData = response.data;
			};
			WebServiceFactory.getRCDetails($scope.cityId,customerId,startDate).then(getRCDetails);
		}*/
		$scope.getDealRate = function(towerArray,towerName) {
			if($scope.dealDetails != undefined) {
				$scope.dealStartDate = $scope.dealDetails[0].dealStartDate;
			}
			if($scope.versionDetails != undefined) {
				$scope.customerId = $scope.versionDetails[0].customerId;
				$scope.currencyId = $scope.versionDetails[0].currencyId;
				$scope.industryType = $scope.versionDetails[0].projectIndustry;
			}
			
			angular.forEach($scope.towerDetails,function(value,key){
				if(value.dealTowerId === towerArray) {
					$scope.countryId = value.countryId;
					$scope.cityCategory = value.cityCategoryId;
				}
			});
		  var getDealRateCardName = function(response) {
		    	//alert('55 getRateCardName'+$localStorage.DealModel);
				//console.log("ratecardName")
			  	if(response.data != undefined){
			  		$scope.rateCardName = response.data;
			  	}
			  	if($localStorage.rateCardData == undefined) {
			  		$localStorage.rateCardData = $scope.rateCardName;  
			  	}
			  	console.log("the rateCardName is.......................");
				console.log($scope.rateCardName );
			};
			WebServiceFactory.getDealRateCardName($scope.customerId,deal_Id,$scope.currencyId,$scope.countryId,$scope.cityCategory,$scope.industryType).then(getDealRateCardName);
		}
		
		$scope.cancel = function(){
			$scope.onSave= false;
			$scope.onAdd= false;
			$scope.onAddRow= false;
			
	 };

		 $scope.addRow = function(towerName,rateCard_Name,addRCDtForm){
				$scope.rateCard_Name=addRCDtForm.rateCard_Name;
				$scope.towerName=addRCDtForm.towerName;
				 $scope.onAddRow=true;
					if($scope.rateCard_Name!=undefined && $scope.rateCard_Name!= null && $scope.towerName!=undefined && $scope.towerName!= null){
						$scope.onAddRow= false;	
						$scope.addRateCardRow(rateCard_Name,towerName,addRCDtForm,$scope.addDetails);
					}
				};
				
				$scope.onAdd= false;	
				$scope.addTower = function(addTowerDtForm){
					$scope.addTowerDtForm.txtTowerNameModel=addTowerDtForm.txtTowerNameModel;
					$scope.onAdd= true;
					if($scope.addTowerDtForm.txtTowerNameModel!=undefined ||$scope.addTowerDtForm.txtTowerNameModel!=null){
						$scope.onAdd= false;
						$scope.addTowerDetails(addTowerDtForm);
					}
				};
				
				$scope.addDetails=[];
				$scope.getData=function(rc){
					console.log(rc);
					$scope.rcIds=rc.rcId;
					$scope.startdate=rc.rcStartDate;
					$scope.endDate=rc.rcEndDate;
					
					var rcEDate  = $scope.startdate;
			  		var date = new Date(rcEDate.substring(0,10));
			  		var dateStrt = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.startdate = dateStrt;
			  		
			  		
			  		var rcEDate  = $scope.endDate;
			  		var date = new Date(rcEDate.substring(0,10));
			  		var dateEnd = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.endDate = dateEnd;
					
					$scope.addDetails.push({
	    				"rcId":$scope.rcIds,
						"startdate": $scope.startdate,
						"endDate":$scope.endDate,
						
	    			});
				}
				
				$scope.getDealRateCardDetails = function(rcId) {
					var getDealRateCardDetails = function(response) {
						$scope.rateCardData = response.data;
						angular.forEach($scope.rateCardDetails, function(value,key) {
							angular.forEach($scope.rateCardData, function(value1,key1) {
								if($scope.rateCardData[key1].rcId == $scope.rateCardDetails[key].rcId){
									$scope.rateCardDetails[key].rcName = $scope.rateCardData[key1].rcName; 
									$scope.rateCardDetails[key].rcID = $scope.rateCardData[key1].rcId;
									var rcEDate  = $scope.rateCardData[key1].rcStartDate;
									var date = new Date(rcEDate.substring(0,10));
									console.log("Start Date is......... " + date);
									var dateStrt = $filter('date')(date,'dd/MM/yyyy');
									$scope.startdate = dateStrt;
									$scope.rateCardDetails[key].startdate = $scope.startdate ;
									var rcEDate  = $scope.rateCardData[key1].rcEndDate;
									var date = new Date(rcEDate.substring(0,10));
									console.log("End Date is......... " + date);
									var dateEnd = $filter('date')(date,'dd/MM/yyyy');
									$scope.endDate = dateEnd;
									$scope.rateCardDetails[key].enddate = $scope.endDate;
									
								}
								
							});
						});
					};
					WebServiceFactory.getDealRateCardDetails(rcId).then(getDealRateCardDetails);
				}
				
				
					var getCountryData = function(response) {
			    		console.log("Deal version ID : " + $localStorage.rpDealVersionId);
			    		console.log("get country data");
			    		console.log(response);
			    		$scope.country = response.data;
			    		
			    	};
			    	WebServiceFactory.getCountryData($localStorage.isManualDealFlag).then(getCountryData);
			
				
				
}]);