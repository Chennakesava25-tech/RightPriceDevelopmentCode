//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("RateCardCreationController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 console.log("inside RateCardCreationController");
		 var contextPath = "/RightPrice-DAS";	
		 $scope.frmRateCard={};
		 $scope.saved = false;
		 $scope.upload= false;
		 $scope.cityAdd = false; 
		 $scope.isAddDisabled = false;
		 $scope.isFirstTimePageLoaded = true;	
		 $scope.rateCardHidden = true;
		 $scope.rbuHidden = true;
		 $scope.uploadBtnDisable = false;
		 $scope.utilizationPanelHide = false;
		 $scope.numberRegex = /^(0|[1-9]\d{0,1})(\.\d{1,2})?$/;
		 var userType = sessionStorage.getItem('userType');
		 console.log("The USer Value from the Session is........ "+ userType);
		 $scope.city = [];
		 $scope.hundPerOnsite=false;
		 $scope.hundPerOffshore=false;
		 $scope.notmandatonsave = false;
		 $scope.manualRCData = 0;
		 $scope.showBtnSubmitTOGFt = false;
		 $scope.isNextbtnDisable = false;
		 $scope.showBtnRecycle = false;
		 isDisabledUti=false;
		 $scope.hidevolumetxt = true;
		 $scope.isUpdate = false;
		 if(userType == 'Audit') {
			 $scope.actionType = [{ name: "View", id: 3 }];
		  } else {
			  $scope.actionType = [{ name: "Add", id: 1 }, { name: "Edit", id: 2 }, { name: "View", id: 3 }];
		  }
		
		 
		 $scope.rbutype = [{rbuId : 1, name : "NAO"},{rbuId : 2, name :"CEN"},{rbuId : 3, name :"ROW"},{rbuId : 4, name :"GLD"}, {rbuId : 5, name :"NOR"},{rbuId:6,name :"SOU"},{rbuId:7,name:"GRW"},{rbuId:8,name:"UKI"},{rbuId:9,name:"STS"},{rbuId:10,name:"FNZ"}]

		 $scope.onshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
		 $scope.offshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
	 	 $scope.renewalArray = [{renewalId : 0, name : "No"},{renewalId: 1,name : "Yes"}]; 
	 	 $scope.ManualRateCard = [{rateCardProcessingType : 0, name : "No"},{rateCardProcessingType : 1,name : "Yes"}];
	 	 $scope.RateCardType = [{id : 0, name : "Manual"},{id : 2,name : "Automatic"}];
	 	 $scope.isRateCardIDdisabled = true;
	 	 $scope.yearlyYOYDetails=[];
	 	 $scope.locationDetails=[];
	 	 $scope.regionWiseUtilizationDetails=[];
	 	 $scope.chkBoxSelectedCountryList=[]; //array to display only check box selected countries in country DD in add location details 
	 	 $scope.totalUtilization=0;
	     $scope.yearDiference=0;
     	 $scope.isViewRequest=false;
     	 $scope.uploadPanelHide = true;
     	 $scope.btnDisable = false;
     	 $scope.isDisabled = true;
     	 $scope.isRateCardType = false;
     	 $scope.isKpoSelected = false;
     	 $scope.autosidebar=false;
     	 $scope.hybridsidebar=false;
     	 $scope.manualsidebar=false;
     	 $scope.MDAttachementData=[];
     	 var rateCardStatus="";
     	 $scope.tablehide=true;
     	 $scope.ismanualKpo = false;
     	$sessionStorage.isOffShoreCity = null;
     	$scope.frmRateCard.offshorehoursperday =false;
     	$scope.frmRateCard.onsitehoursperday = false;
     	$scope.isOffshoreCityList = [];
     	$sessionStorage.currentUser = null;
     	$sessionStorage.verticalId = 0;
     	$localStorage.verticalId=0;
     	$scope.volumeArray = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
     	$scope.Offshorerisk = 
     		[{riskid:1,name:0.25},{riskid:2,name:0.5},{riskid:3,name:0.75},{riskid:4,name:1.0},{riskid:5,name:1.25},{riskid:6,name:1.5},{riskid:7,name:1.75},{riskid:8,name:2.0},
     			{riskid:9,name:2.25},{riskid:10,name:2.5},{riskid:11,name:2.75},{riskid:12,name:3.0},{riskid:13,name:3.25},{riskid:14,name:3.5},
     			{riskid:15,name:3.75},{riskid:16,name:4.0},{riskid:17,name:4.25},{riskid:18,name:4.5},{riskid:19,name:4.75},{riskid:20,name:5.0}];
     	 $scope.CountryType = [{rateCardCountryType : 0, name : "Onshore"},{rateCardCountryType : 1,name : "Nearshore"}];
     	$scope.Nearshorerisk = 
     		[{nriskid:1,name:0.25},{nriskid:2,name:0.5},{nriskid:3,name:0.75},{nriskid:4,name:1.0},{nriskid:5,name:1.25},{nriskid:6,name:1.5},{nriskid:7,name:1.75},{nriskid:8,name:2.0},
     			{nriskid:9,name:2.25},{nriskid:10,name:2.5},{nriskid:11,name:2.75},{nriskid:12,name:3.0},{nriskid:13,name:3.25},{nriskid:14,name:3.5},
     			{nriskid:15,name:3.75},{nriskid:16,name:4.0},{nriskid:17,name:4.25},{nriskid:18,name:4.5},{nriskid:19,name:4.75},{nriskid:20,name:5.0}];
     	 // region wise utilization calculation	
     	 $scope.addUtilization = function(selectedCountry){
     		 
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
     				$scope.city= [];
     				selectedCountry.fxrisknearshore=null;
     				selectedCountry.rateCardCountryType=0


     				//$scope.onsiteLocationForm.city = "";
     			//remove country from array of selected country list shown in country DDL
     				angular.forEach($scope.chkBoxSelectedCountryList, function(value, key) {
     					if(value.countryId === selectedCountry.countryId){
     						$scope.chkBoxSelectedCountryList.splice( $scope.chkBoxSelectedCountryList.indexOf(value),1);
     					}
     				});
     				
 				//remove location utilization details from array of location details
     				/*
     				 * if we try to splice current element and if multiple entries are available for the key,
     				 * array content gets change due to index violation issue.
     				 * So instead of splice, rebuild and reassign array with required content.	
     				*/     				
     				var newTempLocationDetailsArr=[];
	 				angular.forEach($scope.locationDetails, function(locDetailsValue, key) {
	 					//console.log(locDetailsValue.countryName + " : " + locDetailsValue.cityName)
	 					if(!(locDetailsValue.countryId === selectedCountry.countryId)){
	 						//if current country is not unchecked, push it into new created array
	 						newTempLocationDetailsArr.push(locDetailsValue);
	 					}
	 				});
	 				//reassign array to old content
	 				$scope.locationDetails=newTempLocationDetailsArr;
     				
     		}
     		//console.log("selectedCountry");
     		//console.log($scope.chkBoxSelectedCountryList);
     		
     	 };
     	 
     	 //auto calculate expected offshore %
     	$scope.calculateExpectedOffshore = function(){
     		var offShorePercentage = $filter('number')(100 - $scope.frmRateCard.expectedonsiteresource,2)
     		$scope.frmRateCard.expectedoffshoreresource = offShorePercentage;  
     		var onsitePercentage = $filter('number')($scope.frmRateCard.expectedonsiteresource,2)
     		$scope.frmRateCard.expectedonsiteresource = onsitePercentage; 
     		$scope.offShoreUtilizationModel = offShorePercentage;
     		if($scope.frmRateCard.expectedoffshoreresource  == 100) {
     			$scope.utilizationPanelHide = true;
     	     	$scope.frmRateCard.onsitehoursperday=true;
     	     	$scope.hundPerOnsite=true;
     	     	$scope.hundPerOffshore=false;
     	     	$scope.frmRateCard.offshorehoursperday =$scope.offsiteHoursPerDay;
     			if($scope.frmRateCard.industry == 1) {
     				$scope.isRateCardType = true;
     			}
     		} else if($scope.frmRateCard.expectedonsiteresource  == 100)
     			{
     			$scope.utilizationPanelHide = false;
     			$scope.isRateCardType = false;
     			$scope.hundPerOffshore=true;
     			$scope.hundPerOnsite=false;
     			$scope.frmRateCard.offshorehoursperday=true;
     			$scope.frmRateCard.onsitehoursperday =$scope.onsiteHoursPerDay;
     			}
     		else
     		{
     			$scope.hundPerOnsite=false;
     			$scope.hundPerOffshore=false;
     			$scope.utilizationPanelHide = false;
     			$scope.isRateCardType = false;
     			$scope.frmRateCard.offshorehoursperday =$scope.offsiteHoursPerDay;
				$scope.frmRateCard.onsitehoursperday =$scope.onsiteHoursPerDay;
     		}
     	 }
     	 
     	 $scope.resetFormDataOnCustomerChange=function(){
     		// $scope.frmRateCard.action="Please select";
     		$localStorage.tempAction = null;
     		$scope.frmRateCard.action = null;
     		 $scope.resetFormDataOnActionChange();
     	 };
     	 
     	 //reset form data on change of customer
     	 $scope.resetFormDataOnActionChange = function(){
     		//alert("reset form");
     		//reset Rate Card Details
     			if($scope.frmRateCard.action==1 || $scope.frmRateCard.action==null)
	     		{
					$scope.isRateCardIDdisabled = true;
					$scope.rateCardHidden = true;
					$scope.rbuHidden= true;
					$scope.getCustomerVerticalFP($scope.frmRateCard.customer);
					//$scope.isAddDisabled = false;
					/*$scope.isFirstTimePageLoaded = false*/
	     		}
     			else if($scope.frmRateCard.action == 2 || $scope.frmRateCard.action == 3 ){
     				$scope.isRateCardIDdisabled = false;
     				if ($scope.frmRateCard.industrymodel !=1 ){
     					$scope.rbuHidden = true;
     				}
     				//$scope.isAddDisabled = false;
     			}
     			
	     		$scope.rateCardId=[];
	     		$scope.frmRateCard.ratecardname="";
	     		$scope.frmRateCard.startdate="";
	     		$scope.frmRateCard.expectedenddate="";
	     		$scope.frmRateCard.enddate="";
	     		$scope.frmRateCard.currency=null;
	     		$scope.frmRateCard.industry="";
	     		$scope.frmRateCard.applicableyears="";
	     		$scope.frmRateCard.tcv="";
	     		$scope.yearDiference=0;
	     		$scope.yearlyYOYDetails=[];
	     		$scope.frmRateCard.expectedonsiteresource="";
	     		$scope.frmRateCard.ContingentRisk="";
	     		$scope.frmRateCard.SLARisk="";
	     		$scope.frmRateCard.expectedoffshoreresource="";
	     		$scope.frmRateCard.onsitehoursperday =null;
        		$scope.frmRateCard.offshorehoursperday =null;
        		$scope.chkBoxSelectedCountryList = [];
        		$scope.frmRateCard.applicableMonths = "";
        		$scope.frmRateCard.renewal = null;
        		$scope.manualRCData = 0;
        		$scope.manualAttachementModel = 0;
        		$scope.VerticalId = 0;
        		$scope.frmRateCard.rbutype="";
        		$scope.frmRateCard.ddlOffshoreRiskModel="";
	     	// reset Team Details
	     		$scope.frmRateCard.deliveryspocname=null;
	     		$scope.frmRateCard.salesspocname=null;
	     			
	     	//Region-wise Utilization
	     			angular.forEach($scope.regionWiseUtilizationDetails, function(item, key) {
        			    	item.selectChkBox = false;
        			        item.utilization=0;
        			        item.rateCardCountryType=0;
        			        item.fxrisknearshore=null;
        			});
	     	//reset location details
	     			$scope.locationDetails=[];
	     			$scope.totalUtilization = 0;
	     			
	     	// table
	     			$scope.tablehide=true;		
     	 };
     	 
     	/* //append default hours value based on customer
     	 $scope.appendDefaultOnsiteOffshoreHours = function(){
     		 $scope.frmRateCard.onsitehoursperday = $scope.tempOnsiteWorkHours;
     		 $scope.frmRateCard.offshorehoursperday = $scope.tempOffshoreWorkHours;
     	 };*/     	 
     	 $scope.getCustomerVerticalId = function(customerId){
     		//console.log("getCustomerVerticalId");
     		angular.forEach($scope.customer, function(value, key){
     		      if(value.customerId == customerId) {
     		    	  $scope.tempCustomerVerticalMapId = value.customerVerticalMapId;
     		    	  $scope.tempOffshoreWorkHours = value.customer.offshoreWorkHours;
     		    	  $scope.tempOnsiteWorkHours = value.customer.onsiteWorkHours;
     		    	  $scope.frmRateCard.cpcCharges = value.customer.cpcCharges;
     		    	  $scope.frmRateCard.volumediscount = value.customer.volumeDiscount;
     		    	  $scope.frmRateCard.iqnCharges = value.customer.ipcCharges;
     		      }
     		   });
     		 var getEmpName = function(response){
         		 $scope.empName = response.data;
         		 console.log("Employee name.....................");
         		 console.log($scope.empName);
         	 }
         	WebServiceFactory.getEmpName(customerId).then(getEmpName);
         	
         	$sessionStorage.customerVerticalMapId = $scope.tempCustomerVerticalMapId;
     	 }
     	 
	 	 $scope.checkFlag = function(action) {
	 		 
	 		console.log("Inside checkFlag rateCard Status "+rateCardStatus+" action "+action);
	 		
	 		$localStorage.tempAction = action;
		 		// console.log($scope.frmRateCard.customer);
		 	/*	if((action==2))
		 		{
		 			console.log("Inside Save Disable");
		 			$scope.btnDisable = true;
		 		}*/
		 		
		 		if(action == 1){
		 			 //add action
		 			$scope.btnDisable = false;
		 			 $scope.isRateCardIDdisabled = true;
		 			$scope.UploadHidden = true;
		 			 //$scope.isAddDisabled = false;
		 			 $scope.isFirstTimePageLoaded = false;
		 			$scope.showBtnSubmitTOGFt = false;
		 			$scope.utilizationPanelHide = false;
		 			$scope.isRateCardType = false;
		 			$scope.isNextbtnDisable = false;
		 			$scope.resetFormDataOnActionChange();
		 			$scope.frmRateCard.ddlVolumeUpdateModel=null;
		 			$scope.frmRateCard.txtUpdatevolumeModel=null;
		 			$scope.hidevolumetxt=true;
		 			$scope.isUpdate=false;
		 			// $scope.appendDefaultOnsiteOffshoreHours();
		 		 }
		 		 else if(action==2){
		 			 //edit action
		 			 $scope.btnDisable = false;
		 			 $scope.isRateCardIDdisabled = false;
		 			 $scope.isFirstTimePageLoaded = false;
		 			 $scope.showBtnSubmitTOGFt = false;
		 			 $scope.uploadBtnDisable = false;
		 			 $scope.rbuHidden= false;

		 			 if($sessionStorage.customerVerticalMapId != undefined) {
		 				 $scope.getRateCards($sessionStorage.customerVerticalMapId);
		 			 } 
		 		 }
		 		 else if(action == 3){
		 			 //view action
		 			// $scope.isViewRequest = true;
		 			 $scope.btnDisable = true;
		 			 $scope.isRateCardIDdisabled = false;
		 			 $scope.isFirstTimePageLoaded = true;
		 			 $scope.showBtnSubmitTOGFt = false;
		 			 $scope.uploadBtnDisable = true;
		 			 if($sessionStorage.customerVerticalMapId != undefined) {
		 				 $scope.getRateCards($sessionStorage.customerVerticalMapId);
		 			 }
		 		 }
			};
			
			$scope.getRateCards = function(customerVerticalMappingId){
                var getRateCards = function(response) {
                //	console.log("status : "+response.status);
                	if( response.status == 205){
						  BootstrapDialog.show({
									title : 'Rate Card Creation',
									type : BootstrapDialog.TYPE_DANGER,
									message : 'No rate cards available for selected Customer.',
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											$window.location.reload();
										}
									} ]
								});
						  }
                	else{
                		$scope.rateCardId=response.data;
                	}
                   /* $scope.city = response.data;
                    console.log("City Names are...........");
                	console.log($scope.city);*/
                };
                WebServiceFactory.getRateCards(customerVerticalMappingId).then(getRateCards);
            };
		 	
            $scope.getCities = function(countryId){
            	if(countryId != undefined) {
            		var getCities = function(response) {
            			$scope.city = response.data;
            			if($scope.city != undefined) {
            				$sessionStorage.cityArray = $scope.city;
            				console.log("cityArray Details is...... ");
            				console.log($sessionStorage.cityArray);
            			}
            		};
            		WebServiceFactory.getCities(countryId).then(getCities);
            	}
            };
            
            $scope.searchFile = function(rcId)
			{
				angular.element("input[type='file']").val(null);
				console.log("inside File Search---"+rcId);

				var markers = {
							"versionId" : rcId
						};
				$http({
				    method: 'POST',
				    url: contextPath+'/RightPrice-DAS/getAttachementData',
				    dataType: 'json',
				    data: JSON.stringify(markers),  
			     headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				    }).
					    then(function(data) {
					    	if(data.data.length!=0){
								$scope.tableHide=false;
								$scope.temp=[];
								$scope.temp=data.data;
								console.log("temp")
								console.log($scope.temp)
								var j;
								for(var i=0,j=0;i<$scope.temp.length;i++)
								{
									if($scope.temp[i].category=="R")
									{
										$scope.MDAttachementData[j] = $scope.temp[i];
										var strMain = $scope.MDAttachementData[j].createdOn;
									    var arrSplit = [];
									    arrSplit = strMain.split(" ");
									    $scope.MDAttachementData[j].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
										j++;
									}	
								}
								console.log("$scope.MDAttachementData")
								console.log($scope.MDAttachementData)
						
								
							}
							else{
								$scope.tableHide=true;
							}
					    	
					    	
					    });
				
			}


            
            $scope.getRateCardDetailsFromRCId=function(rateCardId){
            	
            	if(rateCardId){
            		//get data for rate card id iff rate card id is !undefined
            		$scope.searchFile(rateCardId);
            		$scope.yearlyYOYDetails = [];
            		$scope.locationDetails=[];
            		$localStorage.rcId=rateCardId;
	            	var rateCardDetails=function(response){
	            		console.log(response.data);
	            		
	            		
	            		if($scope.frmRateCard.action==1 || $scope.frmRateCard.action==null)
	    	     		{
	    					$scope.isRateCardIDdisabled = true;
	    					
	    	     		}
	            		else if($scope.frmRateCard.action == 2){
	         				$scope.isRateCardIDdisabled = false;
	         				
	         				//$scope.isAddDisabled = false;
	         			} else if( $scope.frmRateCard.action == 3 ) {
	         				$scope.isRateCardIDdisabled = false;
	         				$scope.ShowHideTeamDetails();
            				$scope.ShowHideAddLocation();
	            			$scope.ShowHideProposedCurrencies();
	            			$scope.ShowHideUpload();
	         			}
	            		$scope.rateCardDetails = response.data[0];
	            		$scope.getCustomerVerticalFP($scope.rateCardDetails.customerId);

	            	  if($scope.rateCardDetails != undefined){
	            		  
	            		for(var i=0;i< $scope.onshoreHours.length;i++) {
	            			if($scope.rateCardDetails.onsiteHoursPerDay == $scope.onshoreHours[i].name) {
	            				$scope.onsiteHoursPerDay = $scope.onshoreHours[i].hourId;
	            			}
	            		}
	            		
	            		for(var i=0;i< $scope.offshoreHours.length;i++) {
	            			if($scope.rateCardDetails.offshoreHoursPerDay == $scope.offshoreHours[i].name) {
	            				$scope.offsiteHoursPerDay = $scope.offshoreHours[i].hourId;
	            			}
	            		}
	            		
	            		$scope.manualRC = $scope.rateCardDetails.isManualRc
	            		
	            		if($scope.rateCardDetails.isManualRc == "M") {
	            			$scope.manualRC = "Manual"
	            		}  else if($scope.rateCardDetails.isManualRc == "A") {
	            			$scope.manualRC = "Automatic"
	            		}
	            		
	            		if($scope.manualRC == "Manual" && userType == "GFT" && $scope.rateCardDetails.currentApprovalStatus ==  7) {
	            			$scope.showBtnRecycle = true;
	            		}
	            		
	            		for(var i=0;i<$scope.RateCardType.length;i++) {
	            			if($scope.manualRC == $scope.RateCardType[i].name) {
	            				$scope.frmRateCard.rateCardType = $scope.RateCardType[i].id;
	            			}
	            		}
	            		$scope.frmRateCard.ratecardname =$scope.rateCardDetails.rcName;
	            		$scope.frmRateCard.startdate =$filter('date')(new Date($scope.rateCardDetails.rcStartDate.substring(0,10)),'dd/MM/yyyy');
	            		$scope.frmRateCard.enddate =$filter('date')(new Date($scope.rateCardDetails.rcEndDate.substring(0,10)),'dd/MM/yyyy');
	            		$scope.frmRateCard.expectedenddate =$filter('date')(new Date($scope.rateCardDetails.expectedRCEndDate.substring(0,10)),'dd/MM/yyyy');
	            		$scope.frmRateCard.currency =$scope.rateCardDetails.consolidatedRcCurrencyId;
	            		$scope.yearDiference =$scope.rateCardDetails.applicableYears;
	            		$scope.frmRateCard.applicableyears=$scope.rateCardDetails.applicableYears;
	            		$scope.frmRateCard.ContingentRisk=$scope.rateCardDetails.contingentRiskPercentage;
	            		$scope.frmRateCard.SLARisk =$scope.rateCardDetails.slaRiskPercentage;
	            		$scope.frmRateCard.applicableMonths=$scope.rateCardDetails.applicableMonths;
	            		$scope.frmRateCard.industry =$scope.rateCardDetails.isItKpo.toString();
	            		$scope.frmRateCard.rbutype = $scope.rateCardDetails.rbutype;
	            		
	                       if($scope.rateCardDetails.isItKpo == 0 || $scope.rateCardDetails.isItKpo == 2) {
	                    	   $scope.rbuHidden = true;
	 		        			
	 		        			var getCurrentCustomerUserDetailskpo = function(response) {
									console.log("Delivery spoc details for KPO");
									console.log(response.data);
									$scope.frmRateCard.deliveryhead=response.data[0].duh;
									$scope.frmRateCard.buhead=response.data[0].buh;
									$scope.frmRateCard.vertical=response.data[0].verticalName;
									$localStorage.currentUserVerticalId=response.data[0].verticalId;
									$scope.verticalId=response.data[0].verticalId;					
								};
								WebServiceFactory.getCurrentCustomerUserDetailskpo($scope.rateCardDetails.isItKpo,$scope.rateCardDetails.verticalName).then(getCurrentCustomerUserDetailskpo);
							} 
	    				 
	 		        		else  {
	 		        			var getCurrentCustomerUserDetails = function(response) {
									console.log("Delivery spoc details");
									console.log(response.data);
									$scope.frmRateCard.deliveryhead=response.data[0].duh;
									$scope.frmRateCard.buhead=response.data[0].buh;
									$scope.frmRateCard.vertical=response.data[0].verticalName;
									$localStorage.currentUserVerticalId=response.data[0].verticalId;
									//$sessionStorage.verticalId=response.data[0].verticalId;	
									
								};
								WebServiceFactory.getCurrentCustomerUserDetails($scope.rateCardDetails.verticalId,$scope.frmRateCard.rbutype).then(getCurrentCustomerUserDetails);
								
	 		        		}
	                       //manglam updated
		  
	            		$scope.getRateCardsBasedOnIndustry($sessionStorage.customerVerticalMapId,$scope.frmRateCard.industry);
	            		$scope.frmRateCard.tcv =$scope.rateCardDetails.tvc;
	            		$scope.frmRateCard.volumediscount =$scope.rateCardDetails.volumeDiscount;
	            		$scope.frmRateCard.expectedonsiteresource =$scope.rateCardDetails.expectedOnsiteResourcePercentage;
	            		$scope.frmRateCard.expectedoffshoreresource =$scope.rateCardDetails.expectedOffshoreResourcePercentage;
	            		$scope.frmRateCard.onsitehoursperday =$scope.onsiteHoursPerDay;
	            		$scope.frmRateCard.offshorehoursperday =$scope.offsiteHoursPerDay;
	            		$scope.frmRateCard.iqnCharges = $scope.rateCardDetails.iqnCharges;
	            		$scope.frmRateCard.cpcCharges = $scope.rateCardDetails.cpcCharges;
	            		$scope.frmRateCard.ddlVolumeUpdateModel=$scope.rateCardDetails.discFlag;
	            		$scope.frmRateCard.txtUpdatevolumeModel=$scope.rateCardDetails.volumeDiscount;
	            		
	            	
	            		if($scope.frmRateCard.ddlVolumeUpdateModel != undefined || $scope.frmRateCard.ddlVolumeUpdateModel != null) {
	            			$scope.setvolume($scope.frmRateCard.ddlVolumeUpdateModel);
	            		}
	            		
	            		
	            		
	            		if($scope.frmRateCard.onsitehoursperday ==undefined)
	            				{
	            					$scope.frmRateCard.offshorehoursperday=false;
	            					$scope.frmRateCard.onsitehoursperday=true;
	            				}
	            			else if($scope.frmRateCard.offshorehoursperday ==undefined)
	            				{
	            					$scope.frmRateCard.offshorehoursperday=true;
	            					$scope.frmRateCard.onsitehoursperday=false;
	            				}
	            			else
	            				{
	            				$scope.frmRateCard.offshorehoursperday =$scope.offsiteHoursPerDay;
	            					$scope.frmRateCard.onsitehoursperday =$scope.onsiteHoursPerDay;
	            				}
	            		if($scope.frmRateCard.expectedoffshoreresource  == 100) {
	             			$scope.utilizationPanelHide = true;
	             			$scope.frmRateCard.offshorehoursperday =$scope.offsiteHoursPerDay;
	             			if($scope.frmRateCard.industry == 1) {
	             				$scope.isRateCardType = true;
	             			}
	             		} else {
	             			$scope.utilizationPanelHide = false;
	             			$scope.isRateCardType = false;
	             			$scope.frmRateCard.onsitehoursperday =$scope.onsiteHoursPerDay;
	             		}
	            		$scope.tempCustomerVerticalId =  $scope.rateCardDetails.customerVerticalMapId;
						$scope.getRateCards($scope.rateCardDetails.customerVerticalMapId);
						$scope.frmRateCard.customer = $scope.rateCardDetails.customerVerticalId.customer.customerId;
						$scope.frmRateCard.rateCardId = $scope.rateCardDetails.rcId;
						$scope.getCustomerVerticalId($scope.frmRateCard.customer);
						
						
						rateCardStatus=$scope.rateCardDetails.currentApprovalStatus;
						$scope.offShoreUtilizationModel = $scope.rateCardDetails.expectedOffshoreResourcePercentage;
						  
						if($scope.frmRateCard.onsitehoursperday == undefined || $scope.frmRateCard.offshorehoursperday == undefined ) {
							$scope.frmRateCard.offshorehoursperday = $scope.tempOffshoreWorkHours;
							$scope.frmRateCard.onsitehoursperday = $scope.tempOnsiteWorkHours ;
						}
						$scope.frmRateCard.action = $localStorage.tempAction;
						if($scope.frmRateCard.action == 1){
							$scope.frmRateCard.action = 2;
						}
						$scope.checkFlag($scope.frmRateCard.action);
						 
	                    //populate YOY increment table
						if($scope.frmRateCard.industry == 0 || $scope.frmRateCard.industry == 2) {
							$scope.yearDiference = 0;
						} else {
							if($scope.rateCardDetails.yOYIncrementPercents.length > 0) {
								
								angular.forEach($scope.rateCardDetails.yOYIncrementPercents, function (value, key) {
									$scope.yearlyYOYDetails.push({
										"year": value.yOYIncYear, 
										"onsite": value.incrementPercentOnsite, 
										"offshore": value.incrementPercentOffshore, 
										"month": value.yOYIncStartMonth.toString(),
										"stepYear":value.stepYear
									});
								});
							} else {
								$scope.yearDiference = 0;
								$scope.yearlyYOYDetails = [];
							}
						}
	            		//update region wise utilization
						if($scope.rateCardDetails.regionWiseUtilization.length != 0) {
							if($scope.frmRateCard.industry == 0 || $scope.frmRateCard.industry == 2) {
								angular.forEach($scope.rateCardDetails.regionWiseUtilization, function (value, key) {
									angular.forEach($scope.regionWiseUtilizationDetails, function(item, key) {
										if(item.countryId === value.baseCountryId)  {
											$scope.ismanualKpo = true;
											item.selectChkBox = true;
											item.utilization=value.utilization;
											
											$scope.chkBoxSelectedCountryList.push({
												"countryId":item.countryId,
												"countryName":item.countryName,
											});
										}
										item.rateCardProcessingType = 1;
										
										});
									});
									} else if($scope.frmRateCard.industry == 1) {
										angular.forEach($scope.rateCardDetails.regionWiseUtilization, function (value, key) {
											angular.forEach($scope.regionWiseUtilizationDetails, function(item, key) {
												if(item.countryId === value.baseCountryId)  {
													item.selectChkBox = true;
													item.utilization=value.utilization;
													item.rateCardProcessingType = value.rateCardProcessingType
													$scope.ismanualKpo = false;
													item.rateCardCountryType = value.rateCardCountryType
													for(var i=0;i< $scope.Nearshorerisk.length;i++) {
														if(value.fxrisknearshore == $scope.Nearshorerisk[i].name) {
															item.fxrisknearshore= $scope.Nearshorerisk[i].nriskid;
														}
													}
													//$scope.frmRateCard.ddlNearshoreRiskModel=value.fxrisknearshore
													$scope.chkBoxSelectedCountryList.push({
														"countryId":item.countryId,
														"countryName":item.countryName,
													});
												}
										});
									});
							}
//	            			        $scope.ManualRateCardArray[key].rateCardProcessingType = item.rateCardProcessingType;
							//push country details in DDL of select country of add location details
						} 
	            		//populate Location table
	            		$scope.frmRateCard.deliveryspocname =$scope.rateCardDetails.deliverySpocName;
	            		$scope.frmRateCard.salesspocname =$scope.rateCardDetails.salesSpocName;
	            		//populate city allocation table
	            		if($scope.rateCardDetails.rateCardLocations.length > 0) {
	            			console.log("City utilization");
	            			console.log($scope.rateCardDetails.rateCardLocations);
	            			angular.forEach($scope.rateCardDetails.rateCardLocations, function (value, key) {
	            				$scope.locationDetails.push({
	            					"countryId":value.city.country.countryId,
	            					"countryName": value.city.country.countryName,
	            					"cityId":value.city.cityId,
	            					"cityName":value.city.cityName,
	            					"cityCategory":value.city.cityCategory.description,
	            					"utilization": value.cityResourceUtilization,
	            					"syntelFacility": value.syntelFacility,
	            					"premiumAmount": value.premiumAmount 
	            				});
	            			});
	            			console.log("rc location Data");
	            			console.log($scope.rateCardDetails.rateCardLocations);
	            		}
	            		
	            		
	            		$scope.calculateToatlUtilization();
	            		$scope.frmRateCard.renewal =  $scope.rateCardDetails.isRenewal;
	            		if($scope.rateCardDetails.isRenewal == 1) {
		            		$scope.rateCardHidden = false;
		            		$scope.frmRateCard.rcIdName = $scope.rateCardDetails.renewalRCId;
		            	}else {
		            		$scope.frmRateCard.rcIdName = null;
		            		$scope.rateCardHidden = true;	
		            	}
	            		
	            	/*	$scope.manualRCData = $scope.rateCardDetails.manualRCId; 
	            			if($scope.manualRCData > 0) {
	            			  $("#manualAttachementFile").show();
	            			  $("#manualAttachementFile").text("Download");
	            			} else {
	            				$("#manualAttachementFile").hide();
	            			}*/
	            			
	            		$scope.isManualType = $scope.rateCardDetails.isManualRc;
	            		$sessionStorage.isManualRCType = $scope.isManualType; 
	            		$scope.currentApprovalStatus = $scope.rateCardDetails.currentApprovalStatus;
	            		if($scope.isManualType == "M" && ($scope.currentApprovalStatus == 1 || $scope.currentApprovalStatus == 4) && userType == 'Delivery' ) {
	            			$scope.showBtnSubmitTOGFt = true;
	            			$scope.isNextbtnDisable = true;
	            		}
	            		if($scope.isManualType == "M" && ($scope.currentApprovalStatus == 1 || $scope.currentApprovalStatus == 4) && userType == 'GFT') {
	            			$scope.btnDisable = true;
	            			$scope.isNextbtnDisable= true;
	            		} 
	            		$scope.isOffshoreCity = 0;
	            		if($scope.rateCardDetails.rateCardLocations.length > 0) {
	            			angular.forEach($scope.rateCardDetails.rateCardLocations, function (value, key) {
	            				if($scope.rateCardDetails.rateCardLocations[key].isOffShore == "N") {
	            					$scope.isOffshoreCity = value.city.cityId;
	            					$scope.isOffshoreCityList.push ({
	            						"offshoreCity" :$scope.isOffshoreCity 
	            					})
	            				}
	            			});
	            			$sessionStorage.isOffShoreCityList = $scope.isOffshoreCityList;
	            		}
	            		
	            		if(userType != "GFT" && $scope.currentApprovalStatus == 7) {
	            			$scope.isNextbtnDisable = true;
	            		}
	            		
	            		if(!(rateCardStatus == 1 || rateCardStatus== 4)) {
	            			$scope.btnDisable = true;
	            		}
	            		if($scope.isManualType == "M" || $scope.isManualType == "H") {
	            			$scope.UploadHidden = false;
	            		} else if($scope.isManualType == "A") {
	            			$scope.UploadHidden = true;
	            		}
	            		
	            		if($scope.isManualType == "M")
	            			{
	            				$scope.manualsidebar=true;
	            				$scope.autosidebar=false;
	            				$scope.hybridsidebar=false;
	            			}
	            		else if ($scope.isManualType == "H")
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
	            		
	            		$scope.frmRateCard.rbutype=$scope.rateCardDetails.rbutype.toString();

	            		//$scope.frmRateCard.ddlOffshoreRiskModel=$scope.rateCardDetails.fxriskoffshore;
	            		for(var i=0;i< $scope.Offshorerisk.length;i++) {
							if($scope.rateCardDetails.fxriskoffshore == $scope.Offshorerisk[i].name) {
								$scope.frmRateCard.ddlOffshoreRiskModel= $scope.Offshorerisk[i].riskid;
							}
						}
					}
					};
	            	WebServiceFactory.getRateCardDetailsFromRCId(rateCardId).then(rateCardDetails);
	            	
            	}
            };
            
      		//calculate applicable years
            $scope.calApplicableYears = function(startdate,enddate){
            	$scope.yearDiference = 0;
				 //if yearDiference>1, then only it will show table
				 if(startdate == undefined ){
					 startdateVal ="";
				 }
				 else if (enddate == undefined ){
					 enddate ="";
					 $scope.yearDiference = 0; 
					 $scope.frmRateCard.applicableyears = 0;
				 }
				 else
				 {
					 startdateVal =  $scope.frmRateCard.startdate;
					 enddateVal = $scope.frmRateCard.enddate;
					 //set expected end date as RC end date
					 $scope.frmRateCard.expectedenddate=enddateVal;
					 var dayStart   = parseInt(startdateVal.substring(0,2));
					 var monthStart  = parseInt(startdateVal.substring(3,5));
					 var yearStart   = parseInt(startdateVal.substring(6,10));
					 var startDate = new Date(yearStart, monthStart-1, dayStart);
					 
					 var dayEnd   = parseInt(enddateVal.substring(0,2));
					 var monthEnd  = parseInt(enddateVal.substring(3,5));
					 var yearEnd   = parseInt(enddateVal.substring(6,10));
					 var endDate = new Date(yearEnd, monthEnd-1, dayEnd);
					 
					 var date2 = new Date(endDate);
					 var date1 = new Date(startDate);
				 	 var endDateYear = date2.getFullYear();
		             var endDateMonth = date2.getMonth();
		             var endDay = date2.getDate();
		             var startDateYear = date1.getFullYear();
		             var startDateMonth = date1.getMonth();
		             var startDay = date1.getDate();
		             var diff = endDateYear - startDateYear;
			             if (startDateMonth > endDateMonth) diff--;
			             else {
			                 if (startDateMonth == endDateMonth) {
			                     if (startDay > endDay) diff--;
			                 }
			             }
			             //var yearDiff = diff + 1;
					 $scope.frmRateCard.applicableyears = 0;
					 $scope.yearDiference = diff;
					 var difference = diff + 1;
					 $scope.frmRateCard.applicableyears = difference;
					 
					 var monthsDifference = (endDateYear - startDateYear) * 12+1;
					 
					 monthsDifference += endDateMonth - startDateMonth;
					 
					 if(endDay < startDay) {
						 monthsDifference --;
					 }
					 $scope.frmRateCard.applicableMonths = monthsDifference;
				 }
				if($scope.yearDiference>0){
					$scope.yearlyYOYDetails=[];
					var date1 = startdateVal.substring(6,10);
					var startYear=date1;
					var startMonth = $scope.frmRateCard.startdate.substring(3, 5).replace(/^0+/, '');
					for(var i=1; i<=$scope.yearDiference; i++){
						$scope.addYOYRow(parseInt(startYear)+i,i,startMonth);
					}
				} else {
					$scope.yearlyYOYDetails=[];
				}
            };
			
			$scope.addYOYRow=function(year,stepYear,startMonth){
				$scope.yearlyYOYDetails.push({
					"year":year,
					"onsite":"",
					"offshore":"",
					"month":startMonth,
					"stepYear":stepYear
				});
			};
			
			$scope.addLocationRow =function(onsiteLocationForm){
				$scope.notmandatonsave = true;
                $scope.cityAdd = true;
                if(onsiteLocationForm.$valid || $scope.onsiteLocationForm.country != null && $scope.onsiteLocationForm.city != null){
                       $scope.cityAdd = false;
                       //check for duplicate entry
                       var isDuplicateCityEntry=false;
                       angular.forEach($scope.locationDetails,function(value, key) {
                              if(value.countryId == $scope.onsiteLocationForm.country.countryId && value.cityId == $scope.onsiteLocationForm.city.cityId ){
                                     if(!isDuplicateCityEntry){
                                            isDuplicateCityEntry=true;
                                     }
                              }
                       });
                       
                       if(isDuplicateCityEntry){
                              BootstrapDialog.show({
                                     title : 'Rate Card Creation',
                                     type : BootstrapDialog.TYPE_DANGER,
                                     message : 'Location <strong>'+ $scope.onsiteLocationForm.city.cityName + '</strong> is already added in location details.',
                                     closable : false,
                                     buttons : [ {
                                            label : 'OK',
                                            action : function(dialogRef) {
                                                   dialogRef.close();
                                            }
                                     } ]
                              });
                       }
                       else if($scope.frmRateCard.industry == 0){
                    	   if($scope.locationDetails.length >=1) {
                    		   BootstrapDialog.show({
                                   title : 'Rate Card Creation',
                                   type : BootstrapDialog.TYPE_DANGER,
                                   message : 'Only one city and one country can be selected for KPO Rate card.',
                                   closable : false,
                                   buttons : [ {
                                          label : 'OK',
                                          action : function(dialogRef) {
                                                 dialogRef.close();
                                          }
                                   } ]
                            });
                    	   } else {
                    		   $scope.locationDetails.push({
                    			   "countryId":$scope.onsiteLocationForm.country.countryId,
                    			   "countryName": $scope.onsiteLocationForm.country.countryName,
                    			   "cityId":$scope.onsiteLocationForm.city.cityId,
                    			   "cityName":$scope.onsiteLocationForm.city.cityName,
                    			   "cityCategoryId":$scope.onsiteLocationForm.city.cityCategory.codeName,
                    			   "cityCategory":$scope.onsiteLocationForm.city.cityCategory.description,
                    			   "utilization":0,
                    			   "syntelFacilty":"",
                    			   "premiumAmount":null
                    		   });
                    	   }
                       } else {
                    	   $scope.locationDetails.push({
                               "countryId":$scope.onsiteLocationForm.country.countryId,
                               "countryName": $scope.onsiteLocationForm.country.countryName,
                               "cityId":$scope.onsiteLocationForm.city.cityId,
                               "cityName":$scope.onsiteLocationForm.city.cityName,
                               "cityCategoryId":$scope.onsiteLocationForm.city.cityCategory.codeName,
                               "cityCategory":$scope.onsiteLocationForm.city.cityCategory.description,
                               "utilization":0,
                               "syntelFacilty":"",
                               "premiumAmount":null
                        });
                       }
                }
                
          }

			
			$scope.removeLocation=function(index){
				$scope.locationDetails.splice(index,1);
				$scope.calculateToatlUtilization();
				$scope.checkTotalUtilization(1);
			};
			
			$scope.calculateToatlUtilization=function(){
				var total=0;
				angular.forEach($scope.regionWiseUtilizationDetails, function (value, key) {
					if(value.selectChkBox){
						total += parseFloat(value.utilization);
						
					}
		    	});
				$scope.totalUtilization = $filter('number')(total,'2');
				
				 
			};
			
			
			$scope.checkTotalUtilization = function(i){
				
				$scope.onsiteLocationForm.utilizationLimitCountryWise = 100;
				$scope.previousSum = 0;
				$scope.onsiteLocationForm.testt = 0;
				
				var countryName;
				var sum = 0;
				for(i=0;i<$scope.locationDetails.length;i++)
				{
					if(i == 0)
						countryName = $scope.locationDetails[i].countryName;
					sum = 0;
					for(j=0;j<$scope.locationDetails.length;j++)
					{
						if($scope.locationDetails[i].countryName == $scope.locationDetails[j].countryName){
							sum = sum + parseInt($scope.locationDetails[j].utilization);
						}
					}
					if(sum != 100 ){
						$scope.onsiteLocationForm.testt = 101;
						break;
					}
				}
			};
			
			//save rate card
			$scope.saveRateCard=function(frmRateCard){
				$scope.saved = true;
				//Need to add session
				if(frmRateCard.industry == 0 || frmRateCard.industry == 2 ){
					frmRateCard.$invalid = false;
					frmRateCard.$valid = true;
					$scope.rbuHidden = true;
				}
				
				//console.log(frmRateCard.$valid);
				//console.log(frmRateCard);
				//console.log($scope.frmRateCard.$error);
				//console.log($scope.frmRateCard.$error.required);
				if(frmRateCard.expectedonsiteresource == 0 && frmRateCard.expectedoffshoreresource == 100) {
					if(frmRateCard.rateCardType != null && frmRateCard.industry == 1) {
						frmRateCard.$invalid = false;
						frmRateCard.$valid = true;
					} else if(frmRateCard.industry == 0 || frmRateCard.industry == 2 ){
						frmRateCard.$invalid = false;
						frmRateCard.$valid = true;
						$scope.rbuHidden = true;
					}
				} 
				
				if(frmRateCard.$valid){
					/* Check if checked country is added in Add onsite locations ==> start*/
					/*$scope.saved = false;*/
					var counter = 0;
					for(i=0;i<$scope.chkBoxSelectedCountryList.length;i++){
						counter = 0;
						for(j=0;j<$scope.locationDetails.length;j++){
							if($scope.chkBoxSelectedCountryList[i].countryName == $scope.locationDetails[j].countryName){
								counter++;	
							}
						}
						if(counter == 0){
							BootstrapDialog.show({
								title : 'Rate Card Creation',
								type : BootstrapDialog.TYPE_DANGER,
								message : 'Please add all the selected Country & City in Onsite Location Details.',
								closable : false,
								buttons : [{
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
									}
								}]
							});
							return;
						}
							
					}
					/* Check if checked country is added in Add onsite locations ==> end*/
					
					
					$scope.saved = false;
					
					//console.log( $scope.yearlyYOYDetails);
					//console.log( $scope.regionWiseUtilizationDetails);
					//console.log( $scope.locationDetails);
					//console.log("length : " +  $scope.yearlyYOYDetails.length);
					
					
					
				   $scope.yoyDetails = [];
	               angular.forEach($scope.yearlyYOYDetails, function (value, key) {
	            	    if ($scope.yearlyYOYDetails[key].offshore == "") {
	            	    	$scope.yearlyYOYDetails[key].offshore = 0;
	            	    } 
	            	    if($scope.yearlyYOYDetails[key].onsite == "") {
	            	    	$scope.yearlyYOYDetails[key].onsite = 0;
	            	    }
	            	   
	                   $scope.yoyDetails.push({
	                	   "yOYIncYear": value.year, 
	                	   "incrementPercentOnsite": value.onsite, 
	                	   "incrementPercentOffshore": value.offshore, 
	                	   "yOYIncStartMonth": value.month,
	                	   "stepYear":value.stepYear
	            	   });
	               });
	               

	               
		          		
	               
	               $scope.regionWiseUtilization=[];
	               
	               


	               angular.forEach($scope.regionWiseUtilizationDetails, function(value,key) {
	            	      
	               	if(value.selectChkBox){
	               		
	               		for(var i=0;i< $scope.Nearshorerisk.length;i++) {
							if(value.fxrisknearshore == $scope.Nearshorerisk[i].nriskid) {
								fxrisknearshore= $scope.Nearshorerisk[i].name;
							}
						}
	               		
	               		
	               		
	               		$scope.regionWiseUtilization.push({
	               			"baseCountryId":value.countryId,
	               			"utilization":value.utilization,
	               			"rateCardProcessingType":value.rateCardProcessingType,
	               			//"rateCardCountryType":value.rateCardCountryType,
	               		//"fxrisknearshore":fxrisknearshore
	               		});
	
	               	}
	               });
	               $localStorage.regionUtilizationArray = $scope.regionWiseUtilization;
	               console.log("Session Storage Utilization Array ");
	               console.log($localStorage.regionUtilizationArray);
	               
	               $scope.locDetails=[];
	               angular.forEach($scope.locationDetails, function (value, key) {
	            	   if(value.syntelFacility == 1) {
	            	   $scope.locDetails.push({
	            		   "cityId": value.cityId, 
	            		   "cityResourceUtilization": value.utilization,
	            		   "countryId":value.countryId,
	            		   "syntelFacility":value.syntelFacility,
	            		   "premiumAmount":value.premiumAmount
	            	   });
	            	   } else {
	            		   $scope.locDetails.push({
		            		   "cityId": value.cityId, 
		            		   "cityResourceUtilization": value.utilization,
		            		   "countryId":value.countryId,
		            		   "syntelFaclity":0,
		            		   "premiumAmount":""
		            	   });
	            	   }
	               });
	               
	               if(frmRateCard.expectedonsiteresource == 0 && frmRateCard.expectedoffshoreresource == 100)
	               {
	            	   var onsiteHourPerDay =0;
	               }
	               else{
	               var onsiteHourPerDay = $.grep($scope.onshoreHours, function (oi) {
	            	                       return oi.hourId == $scope.frmRateCard.onsitehoursperday;
	            	                   })[0].name;
	               //console.log("onsite hours Per day is........ "+ onsiteHourPerDay);
	               }
	               if(frmRateCard.expectedoffshoreresource == 0 && frmRateCard.expectedonsiteresource == 100)
	            	   {
	            	   var offShorehourPerDay=0;
	            	   }
	               else
	            	   {
	               var offShorehourPerDay = $.grep($scope.offshoreHours, function (offshoreHour) {
	            	                       return offshoreHour.hourId == $scope.frmRateCard.offshorehoursperday;
	            	                   })[0].name;
	            	   }
	               if($scope.frmRateCard.rateCardType != null) {
	            	   var rateCardTypeCheck = $.grep($scope.RateCardType, function (rcType) {
	            		                       return rcType.id == $scope.frmRateCard.rateCardType;
	            		                   })[0].name;
	               }
	               	
	               if(rateCardTypeCheck == "Manual") {
	            	   $scope.rateCardType = "M"
	               } else if(rateCardTypeCheck == "Automatic") {
	            	   $scope.rateCardType = "A"
	               }
	               
	               if($scope.manualRCData != null){
	            	   var manualRC = $scope.manualRCData;
	               } else {
	            	   var manualRC = null;
	               }
	               
	               // IF ELSE CONDITION FOR VOLUME DISCOUNT
	               if($scope.frmRateCard.txtUpdatevolumeModel != undefined || $scope.frmRateCard.txtUpdatevolumeModel != null) {
	            	   $scope.frmRateCard.volumediscount= $scope.frmRateCard.txtUpdatevolumeModel  
	               } 
	               if($scope.frmRateCard.ddlOffshoreRiskModel != null){
	          		 var Offshoreriskperc = $.grep($scope.Offshorerisk, function (ofsRisk) {
	          	  	                       return ofsRisk.riskid == $scope.frmRateCard.ddlOffshoreRiskModel;
	          	  	                   })[0].name;
	          		}
	          		
	            
	               
	               if($sessionStorage.customerVerticalMapId != undefined) {
	            	   var lanId = $scope.frmRateCard.deliveryspocname;
	       			   var spocName  = $.grep($scope.empName, function (empName) {
	                       return empName.lanId == lanId;
	                   })[0].employeeName;
	       			
	       			   
	            	   var marker={
	            			   "rcId": $scope.frmRateCard.rateCardId,
	            			   "customerVerticalMapId": $sessionStorage.customerVerticalMapId,
	            			   "rcName": $scope.frmRateCard.ratecardname,
	            			   "rcStartDate":$scope.frmRateCard.startdate,
	            			   "rcEndDate":$scope.frmRateCard.enddate,
	            			   "expectedRCEndDate":$scope.frmRateCard.expectedenddate,
	            			   "tvc":$scope.frmRateCard.tcv,
	            			   "volumeDiscount":$scope.frmRateCard.volumediscount,
	            			   "isItKpo":$scope.frmRateCard.industry,
	            			   "applicableYears":$scope.frmRateCard.applicableyears,
	            			   "applicableMonths":$scope.frmRateCard.applicableMonths,
	            			   "expectedOnsiteResourcePercentage":$scope.frmRateCard.expectedonsiteresource,
	            			   "expectedOffshoreResourcePercentage":$scope.frmRateCard.expectedoffshoreresource,
	            			   "deliverySpocName":$scope.frmRateCard.deliveryspocname,
	            			   "salesSpocName":$scope.frmRateCard.salesspocname,
	            			   "consolidatedRcCurrencyId":$scope.frmRateCard.currency,
	            			   "rcProgressStatusId":1,
	            			   "currentApprovalStatus":1,
	            			   "onsiteHoursPerDay":onsiteHourPerDay,
	            			   "offshoreHoursPerDay":offShorehourPerDay,
	            			   "isRenewal":$scope.frmRateCard.renewal,
	            			   "renewalRCId":$scope.frmRateCard.rcIdName,
	            			   "isManualRc":$scope.rateCardType,
	            			   "iqnCharges":$scope.frmRateCard.iqnCharges,
	            			   "cpcCharges":$scope.frmRateCard.cpcCharges,
	            			   "statusIndicator":"DRAFT",
	            			   "manualRCId":manualRC,
	            			   "verticalId":$scope.verticalId,
	            			   "customerId":$scope.frmRateCard.customer,
	            			   "yOYIncrementPercents":	$scope.yoyDetails,
	            			   "regionWiseUtilization":$scope.regionWiseUtilization,
	            			   "rateCardLocations":$scope.locDetails,
	            			   "spocName":spocName,
	            			   "discFlag":$scope.frmRateCard.ddlVolumeUpdateModel,
	            			   "deliveryhead": $scope.frmRateCard.deliveryhead,
		            			  "buhead":$scope.frmRateCard.buhead,
		            			  "verticalName":$scope.frmRateCard.vertical,
		            			  "rbutype":$scope.frmRateCard.rbutype,
		            			  "fxriskoffshore":Offshoreriskperc,
		            			  "contingentRiskPercentage":$scope.frmRateCard.ContingentRisk,	
		            			  "slaRiskPercentage":$scope.frmRateCard.SLARisk,
		            			 
	            	   };
	               } else {
	            	   console.log("Inside Else...");
	            	   var lanId = $scope.frmRateCard.deliveryspocname;
	       			   var spocName  = $.grep($scope.empName, function (empName) {
	                       return empName.lanId == lanId;
	                   })[0].employeeName;
	       		
	            	   $scope.getCustomerVerticalId($scope.frmRateCard.customer);
	            	   var marker={
	            			   "rcId": $scope.frmRateCard.rateCardId,
	            			   "customerVerticalMapId": $sessionStorage.customerVerticalMapId,
	            			   "rcName": $scope.frmRateCard.ratecardname,
	            			   "rcStartDate":$scope.frmRateCard.startdate,
	            			   "rcEndDate":$scope.frmRateCard.enddate,
	            			   "expectedRCEndDate":$scope.frmRateCard.expectedenddate,
	            			   "tvc":$scope.frmRateCard.tcv,
	            			   "volumeDiscount":$scope.frmRateCard.volumediscount,
	            			   "isItKpo":$scope.frmRateCard.industry,
	            			   "applicableYears":$scope.frmRateCard.applicableyears,
	            			   "applicableMonths":$scope.frmRateCard.applicableMonths,
	            			   "expectedOnsiteResourcePercentage":$scope.frmRateCard.expectedonsiteresource,
	            			   "expectedOffshoreResourcePercentage":$scope.frmRateCard.expectedoffshoreresource,
	            			   "deliverySpocName":$scope.frmRateCard.deliveryspocname,
	            			   "salesSpocName":$scope.frmRateCard.salesspocname,
	            			   "consolidatedRcCurrencyId":$scope.frmRateCard.currency,
	            			   "rcProgressStatusId":1,
	            			   "currentApprovalStatus":1,
	            			   "onsiteHoursPerDay":onsiteHourPerDay,
	            			   "offshoreHoursPerDay":offShorehourPerDay,
	            			   "isRenewal":$scope.frmRateCard.renewal,
	            			   "renewalRCId":$scope.frmRateCard.rcIdName,
	            			   "isManualRc":$scope.rateCardType,
	            			   "iqnCharges":$scope.frmRateCard.iqnCharges,
	            			   "cpcCharges":$scope.frmRateCard.cpcCharges,
	            			   "statusIndicator":"DRAFT",
	            			   "manualRCId":manualRC,
	            			   "verticalId":$scope.verticalId,
	            			   "customerId":$scope.frmRateCard.customer,
	            			   "yOYIncrementPercents":	$scope.yoyDetails,
	            			   "regionWiseUtilization":$scope.regionWiseUtilization,
	            			   "rateCardLocations":$scope.locDetails,
	            			   "spocName":spocName,
	            			   "discFlag":$scope.frmRateCard.ddlVolumeUpdateModel,
	            			  "deliveryhead": $scope.frmRateCard.deliveryhead,
	            			  "buhead":$scope.frmRateCard.buhead,
	            			  
	            			 "rbutype":$scope.frmRateCard.rbutype,
	            			  "verticalName":$scope.frmRateCard.vertical,
	            			  "fxriskoffshore":Offshoreriskperc,
	            			  "contingentRiskPercentage":$scope.frmRateCard.ContingentRisk,	
	            			  "slaRiskPercentage":$scope.frmRateCard.SLARisk,


	            			   
	            	   };
	               }
					var saveRateCard=function(response){
						//console.log("data");
						//console.log(response);
						//console.log(response.status);
						 if( response.status == 200){
							 $localStorage.rcId=response.data; 
							  BootstrapDialog.show({
										title : 'Rate Card Creation',
										type : BootstrapDialog.TYPE_PRIMARY,
										message : 'Rate card created successfully.',
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
	
					};
					WebServiceFactory.saveRateCard(marker).then(saveRateCard);
				}
			};
		 	/*
		 	 * onload function call
		 	 */
			
			 // Local storage data
			//alert("reload rate card" +$localStorage.rcId );
			
		 	$localStorage.currentUserVerticalId=0;
		 	$localStorage.consolidatedRcCurrencyId=0;
		 	$localStorage.tvc=0;
		 	$localStorage.volumeDiscount=0;
		 	
	 	
		 	if(userType == 'GFT' || userType == 'CEO' || userType == 'CDO' 
		 		|| userType == 'Audit' || userType == 'LEVELl1User' || userType == 'LEVELl2User'){
		 		var getGFTCustomer = function(response) {
		 		 	console.log("customer response");
		 		 	//console.log(response.data);
				   $scope.customer = response.data;
				   console.log("customer::::::::::::::::::");
				   console.log(response.data);
				   if($localStorage.rcId>0){
						 $scope.isFirstTimePageLoaded = false;
						 //auto populate data
						 $scope.getRateCardDetailsFromRCId($localStorage.rcId);
					 }else{
						 $localStorage.rcId=0;
					 }
				 };
			 WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
		 	}
		 	else  if(userType == 'BUH' || userType == 'DUH')
		 	{
		 		//Local storage data
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
						if($localStorage.rcId>0){
							 $scope.isFirstTimePageLoaded = false;
							 //auto populate data
							 $scope.getRateCardDetailsFromRCId($localStorage.rcId);
						 }else{
							 $localStorage.rcId=0;
						 }
					};
					WebServiceFactory.getVerticalApproverData().then(getVerticalApproverData);

		 		/*var getCustomerByVerticalGroupId = function(response) {
		 			console.log("customer response");
		 			//console.log(response.data);
		 			$scope.customer = response.data;
		 			console.log("customer::::::::::::::::::");
		 			console.log(response.data);
		 			if($localStorage.rcId>0){
						 $scope.isFirstTimePageLoaded = false;
						 //auto populate data
						 $scope.getRateCardDetailsFromRCId($localStorage.rcId);
					 }else{
						 $localStorage.rcId=0;
					 }
		 		};
		 		WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);*/
		 	}
		 	
		 	else
			{
				var getCustomerForUser = function(response) {
					console.log("getCustomerByVerticalGroupId");
					console.log(response);
					$scope.customer = response.data;
					if($localStorage.rcId>0){
						 $scope.isFirstTimePageLoaded = false;
						 //auto populate data
						 $scope.getRateCardDetailsFromRCId($localStorage.rcId);
					 }else{
						 $localStorage.rcId=0;
					 }
			}
				WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
			}
		 	 
		 	
		 	/*var getCustomerByVerticalGroupId = function(response) {
	 			//console.log("customer response");
	 			//console.log(response.data);
	 			$scope.customer = response.data;
	 			//console.log("customer::::::::::::::::::");
	 			//console.log(response.data);
	 		};
	 		WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);*/
	 		
	 		
			 var getCurrency = function(response) {
				    $scope.currency = response.data;
				};
			WebServiceFactory.getCurrency().then(getCurrency);
			 
			var getCountryDetail = function(response) {
				console.log("get country data");
		  		//console.log(response);
				$scope.country = response.data;
		  		angular.forEach($scope.country, function (value, key) {
	                   $scope.regionWiseUtilizationDetails.push({
	                	   "countryId": value.countryId, 
	                	   "countryName": value.countryName, 
	                	   "currencyId": value.currencyId, 
	                	   "currencyCode": value.currencyCode,
	                	   "utilization":0,
	                	   "rateCardProcessingType": value.isOnlyManRCDeal,
	                	   "selectChkBox":false,
	                	   "rateCardCountryType":0,
	                	   "fxrisknearshore":null
	            	   });
	               });
		  		$scope.setDisabledCntry ();
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
			
		
			
			
			 $scope.TeamDetailsHidden = true;
			 $scope.AddLocationHidden = true;
			 $scope.ProposedCurrenciesHidden = true
			// $scope.UploadHidden = true;
			 $scope.ShowHideTeamDetails = function () {
			     $scope.TeamDetailsHidden = $scope.TeamDetailsHidden ? false : true;
			 };
			 
			 $scope.ShowHideAddLocation = function () {
			     $scope.AddLocationHidden = $scope.AddLocationHidden ? false : true;
			 };
			 $scope.ShowHideProposedCurrencies = function () {
			     $scope.ProposedCurrenciesHidden = $scope.ProposedCurrenciesHidden ? false : true;
			 };
			 $scope.ShowHideUpload = function () {
			     $scope.uploadPanelHide = $scope.uploadPanelHide ? false : true;
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
				$scope.isManualType = $sessionStorage.isManualRCType;
				console.log("The Attachemt table length is............. ");
				if(($scope.isManualType == 'M' || $scope.isManualType == 'H') && $scope.temp == undefined) {
					BootstrapDialog.show({
						title : 'Rate Card Creation',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please Upload the Rate Card checklist.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
				} else {
					window.location="RateCardCreationRoleSelection";
				}
				//window.location="RateCardCreationRoleSelection";
			}; 
			
			$scope.uploadData = function(frmRateCard){
				//alert("uploadData called");
				$scope.upload= true;
				if(frmRateCard.expectedonsiteresource == 0 && frmRateCard.expectedoffshoreresource == 100) {
					frmRateCard.$invalid = false;
					frmRateCard.$pristine = true;
					frmRateCard.$valid = true;
				}
				if(frmRateCard.$valid){
					$scope.upload= false;
					//alert("inside valid");
					if($scope.frmRateCardManualUpload.fuUploadFilenameModel != true) {
						$scope.uploadFileToUrl();
					} else {
						BootstrapDialog.show({
							title : 'Rate Card Creation',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Please upload File and Click on Update Button",
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(
										dialogRef) {
									dialogRef.close();
									window.location = "RateCardCreationDetails";
								}
							} ]
						});	
					}
					} 
			};	
			
			// Upload File
			 $scope.uploadFileToUrl = function(){
			    	//alert("uploadFileToUrl");
			    	//console.log("called uploadPOExcel");
			    	var fileTest = $("#fuUploadFilename");	
			    	//console.log(fileTest.length);
			    	var name = "Template";
			    	var tempID = 1;	
			    	var category="R";
			    	var rcId= $scope.frmRateCard.rateCardId;
		    		//console.log("fileTest");
					var file = $('input[name="fuUploadFilename"]').get(0).files[0];
					//console.dir(file);
					var formData = new FormData();
					formData.append('file', file);
					formData.append('name', name);
					formData.append('TempId',tempID);
					formData.append('rcId',rcId);
					formData.append('category',category);
					//console.log(file);
			    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadRCFile";
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
								title : 'Rate Card Creation',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : customMessage,
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
									}
								} ]
							});	
							$scope.searchFile(rcId);
						} 
				    	else if(data.status == 205){
								BootstrapDialog.show({
								title : 'Rate Card Creation',
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
							title : 'Rate Card Creation',
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
			 // check the synel facility checkbox is clicked or not
			 
			 $scope.checkSyntelFacility = function (index,data) {
				// console.log("Data......");
				// console.log(data);
					 if(data.syntelFacility != 1) {
						 document.getElementById('txtPremiumAmount_'+index).disabled = true;
						 $scope.getSyntelPremium(data.cityId,data,index);
						 data.syntelFacility = 1;
					 } else {
						 data.syntelFacility = null;
						 data.premiumAmount = null;
						 document.getElementById('txtPremiumAmount_'+index).value = "";
						 document.getElementById('txtPremiumAmount_'+index).disabled = true;
					 }
				 }
			 $scope.syntelPremiumVal = [];
			 $scope.getSyntelPremium = function(cityId,dataArray,index){
	            	var getSyntelPremium = function(response) {
	            		$scope.syntelPremiumVal = response.data;
	            		dataArray.premiumAmount = $scope.syntelPremiumVal[0].syntelPremium;
	            	};
	            	WebServiceFactory.getSyntelPremium(cityId).then(getSyntelPremium);
	            };
	            
	            
	            $scope.checkRenewalVal = function (renewalValue) {
	            	if(renewalValue == 1) {
	            		$scope.rateCardHidden = false;
	            		if($sessionStorage.customerVerticalMapId == undefined) {
	            			$scope.getCustomerVerticalId($scope.frmRateCard.customer);
	            		}
	            		
	            		if($scope.frmRateCard.action != 3 && $sessionStorage.customerVerticalMapId != undefined) {
	            			$scope.getRateCardsBasedOnIndustry($sessionStorage.customerVerticalMapId,$scope.frmRateCard.industry);
	            		}
	            	}else {
	            		$scope.frmRateCard.rcIdName = null;
	            		$scope.rateCardHidden = true;	
	            	}
	            }
			  
	            // Download the Existing File
	            $scope.downloadFile = function(objectId){
	        		WebServiceFactory.downloadFileFormID(objectId);
	        	};
	        	
	        	
	        	$scope.updateRateCard = function(rcId) {
	        		if($scope.temp != undefined) {
	        			var updateRateCard = function(response) {
	        				if( response.status == 200){
	        					BootstrapDialog.show({
	        						title : 'Rate Card Creation',
	        						type : BootstrapDialog.TYPE_PRIMARY,
	        						message : 'Rate card successfully sent for GFT Approval.',
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
	        			}
	        		WebServiceFactory.updateRateCard(rcId).then(updateRateCard);
	        	} else {
	        		BootstrapDialog.show({
						title : 'Rate Card Creation',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please Upload the Rate Card checklist.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
	        	}
	        	}
	        	
	        	$scope.deleteFile = function(objectid){
	        		WebServiceFactory.changeActiveStatus(objectid);
	        		 $window.location.reload();
	        	};
	        	
	        	// set the Manual For KPO Users
	        	$scope.setKpoManual = function(industryModel) {
	        		console.log("Inside the KPO function");
	        		console.log($scope.regionWiseUtilizationDetails);
	        		if(industryModel == 0 || industryModel == 2) {
	        			$scope.isRateCardType = false;
	        			$scope.ismanualKpo = true;
	        			$scope.yearDiference = 0;
	        			$scope.TeamDetailsHidden=true;
	        			angular.forEach($scope.regionWiseUtilizationDetails, function(value,key) {
	        				$scope.regionWiseUtilizationDetails[key].rateCardProcessingType = 1;
	        			});
	        		} else if(industryModel == 1){
	        			if($scope.frmRateCard.expectedoffshoreresource == 100) {
	        				$scope.isRateCardType = true;
	        			}
	        			$scope.ismanualKpo = false;
	        			angular.forEach($scope.country, function (item, key1) {
	        				angular.forEach($scope.regionWiseUtilizationDetails, function(value,key) {
	        					if(item.countryId === value.countryId)  {
	        					$scope.regionWiseUtilizationDetails[key].rateCardProcessingType = $scope.country[key1].isOnlyManRCDeal;
	        					}
	        				});
	        			});
	        		}
	        		if($scope.rateCardHidden == false) {
	        			$scope.getRateCardsBasedOnIndustry($sessionStorage.customerVerticalMapId,$scope.frmRateCard.industry);
	        		}
	        	}
	        	
	        	$scope.setDisabledCntry = function() {

	        			if($scope.frmRateCard.expectedoffshoreresource == 100) {
	        				$scope.isRateCardType = true;
	        			}
	        			console.log("+++++++++++++++")
	        			console.log($scope.regionWiseUtilizationDetails)
	        			
	        			angular.forEach($scope.country, function (item, key1) {
	        				angular.forEach($scope.regionWiseUtilizationDetails, function(value,key) {
	        					if(item.countryId == value.countryId)  { 
	        					$scope.regionWiseUtilizationDetails[key].rateCardProcessingType = $scope.country[key1].isOnlyManRCDeal;
	        					if($scope.regionWiseUtilizationDetails[key].rateCardProcessingType == 1 )
	        						{
	        						$scope.regionWiseUtilizationDetails[key].ismanualKpo = true;			
	        						}
	        					}
	        				});
	        			});
	        		
	        		if($scope.rateCardHidden == false) {
	        			$scope.getRateCardsBasedOnIndustry($sessionStorage.customerVerticalMapId,$scope.frmRateCard.industry);
	        		}
	        	}
	        
	        	
	        	$scope.getRateCardsBasedOnIndustry = function(customerVerticalId,industry) {
	        		console.log("customerVerticalId" + customerVerticalId);
	        		console.log("Industry"+industry);
	        		var getRateCardsBasedOnIndustry = function(response) {
	        			$scope.rateCardOnIndustry = response.data;
	        			if(industry == 0 || industry == 2){

	        				console.log('rbuHidden');

	        				$scope.rbuHidden = true;

	        			}
 
	        		};
	        		WebServiceFactory.getRateCardsBasedOnIndustry(customerVerticalId,industry).then(getRateCardsBasedOnIndustry);
	        	}
	        	
	        	$scope.currentUser = function(user)
	        	
	        	{
	        		  if(user.length>10){
	        		var base64key = "QmFyMTIzNDVCYXIxMjM0NQ==";
	    			var parsedBase64Key = CryptoJS.enc.Base64
	    					.parse(base64key);
	    			var ive = CryptoJS.enc.Utf8
	    					.parse('RandomInitVector');
	    			var encrypted = CryptoJS.AES.decrypt(
	    					user, parsedBase64Key, {
	    						iv : ive
	    					});
	    			
	    			var decryptedText = encrypted
	    					.toString(CryptoJS.enc.Utf8);
	    			user=decryptedText;
	        		  }
	        		$scope.user = user;
	        		if($sessionStorage.currentUser == null) {
	        			$sessionStorage.currentUser = $scope.user;
	        		}
	        	}
	        					
				$scope.getDUHCustomerVerticalMapping = function(verticalId) {
					var getDUHCustomerVerticalMapping = function(response) {
						$scope.customer = response.data;
						$scope.customerVerticalMapping = response.data;
					}
					WebServiceFactory.getDUHCustomerVerticalMapping(verticalId).then(getDUHCustomerVerticalMapping);
				};
				
				$scope.getCustomerVerticalFP = function(custmIdforVertical) {
					var getCustomerVerticalFP = function(response) {
						console.log("Vertical details");
						$scope.currentCustVertical = response.data;
						if($scope.currentCustVertical!= undefined) {
							$scope.verticalId = $scope.currentCustVertical[0].verticalId;
							$scope.customerVerticalMapId=$scope.currentCustVertical[0].customerVerticalMapId;
							$sessionStorage.custmVerticalMapId=$scope.customerVerticalMapId;
							$localStorage.verticalId = $scope.verticalId;
							// $scope.getCurrentCustomerUserDetails($sessionStorage.verticalId);
							 //$scope.getCurrentCustomerUserDetailskpo($scope.frmRateCard.industry);
						}
						console.log($scope.currentCustVertical);
					};
					WebServiceFactory.getCustomerVerticalFP(custmIdforVertical).then(getCustomerVerticalFP);
				};
				
				$scope.updateRecycleStatus = function(rcId) {
					BootstrapDialog.show({
				           title: 'Rate Card Creation - Rate Card Details',
				           type : BootstrapDialog.TYPE_PRIMARY,
				           message : 'Do you want to Recycle the Rate Card ?',
				           closable: true,
				           buttons: [{
				               label: 'Yes',
				               cssClass: 'btn-primary',
				               action: function(dialogRef) {
				            	   $scope.updateRCStatus(rcId);
				               }   
				           }, {
					            label: 'No',
					            cssClass: 'btn-primary',
					            action: function(dialog) {
					                dialog.close();
					            }
					            }]
				           });
				};
				
				 $scope.updateRCStatus = function(rcId) {
					 var updateRCStatus = function(response) {
							if( response.status == 200){
	        					BootstrapDialog.show({
	        						title : 'Rate Card Creation',
	        						type : BootstrapDialog.TYPE_PRIMARY,
	        						message : 'Rate card Successfully Recycled.',
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
	        			}
	        		WebServiceFactory.updateRCStatus(rcId).then(updateRCStatus);
				 };
				 
					//$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }];
				 $scope.setvolume = function(volumeId){	
						
						if(volumeId!=undefined && volumeId == 2)
						{
							
							$scope.isUpdate = false;
						}
						else
						{
							
							$scope.isUpdate = true;
						}	
						if(volumeId == 2) 
						{		
							$scope.hidevolumetxt = true;
						}
						else 
						{
							$scope.hidevolumetxt = false;
						}	
						
					}
				

				
		        			/*$scope.getCurrentCustomerUserDetails = function(verticalId) {
		        				
		        				  
		        				 var getCurrentCustomerUserDetails = function(response) {
	    								console.log("Delivery spoc details");
	    								console.log(response.data);
	    								$scope.frmRateCard.deliveryhead=response.data[0].duh;
	    								$scope.frmRateCard.buhead=response.data[0].buh;
	    								$scope.frmRateCard.vertical=response.data[0].verticalName;
	    								$localStorage.currentUserVerticalId=response.data[0].verticalId;
	    								
	    								
	    							};
	    							WebServiceFactory.getCurrentCustomerUserDetails(verticalId).then(getCurrentCustomerUserDetails);
	    							
	     		        		
	     		        		}*/
		        			/*$scope.setteamdatils= function (industryModel){
		     		        		if(industryModel == 0 || industryModel == 2) {
		     		        			
		     		        			var getCurrentCustomerUserDetailskpo = function(response) {
		    								console.log("Delivery spoc details");
		    								console.log(response.data);
		    								$scope.frmRateCard.deliveryhead=response.data[0].duh;
		    								$scope.frmRateCard.buhead=response.data[0].buh;
		    								$scope.frmRateCard.vertical=response.data[0].verticalName;
		    								$localStorage.currentUserVerticalId=response.data[0].verticalId;
		    								$sessionStorage.verticalId=response.data[0].verticalId;							
		    							};
		    							WebServiceFactory.getCurrentCustomerUserDetailskpo($scope.frmRateCard.industry,$scope.frmRateCard.vertical).then(getCurrentCustomerUserDetailskpo);
		    						} 
		        				 
		     		        		else if(industryModel == 1 ){
		     		        			var getCurrentCustomerUserDetails = function(response) {
		    								console.log("Delivery spoc details");
		    								console.log(response.data);
		    								$scope.frmRateCard.deliveryhead=response.data[0].duh;
		    								$scope.frmRateCard.buhead=response.data[0].buh;
		    								$scope.frmRateCard.vertical=response.data[0].verticalName;
		    								$localStorage.currentUserVerticalId=response.data[0].verticalId;
		    								$sessionStorage.verticalId=response.data[0].verticalId;	
		    								
		    							};
		    							WebServiceFactory.getCurrentCustomerUserDetails($localStorage.verticalId).then(getCurrentCustomerUserDetails);
		    							
		     		        		}	
		     		        		else{
		     		        			var getCurrentCustomerUserDetails = function(response) {
		    								console.log("Delivery spoc details");
		    								console.log(response.data);
		    								$scope.frmRateCard.deliveryhead=response.data[0].duh;
		    								$scope.frmRateCard.buhead=response.data[0].buh;
		    								$scope.frmRateCard.vertical=response.data[0].verticalName;
		    								$localStorage.currentUserVerticalId=response.data[0].verticalId;
		    								$sessionStorage.verticalId=response.data[0].verticalId;	
		    								
		    							};
		    							WebServiceFactory.getCurrentCustomerUserDetails($localStorage.verticalId).then(getCurrentCustomerUserDetails);
		    							
		     		        		}	
		        				 
		        			}*/
				 
		        			 $scope.checkRbuDetalis = function(industryModel) { 
		    					 
		     					
		        			       if($scope.frmRateCard.action != 3 && industryModel == 1 ) 
		        			        {
		        			    	   $scope.rbuHidden = false;
		        			        } 	        			            		
		        			            else {	
		        							 $scope.rbuHidden = true;
		        							 }							
		        						 } 
		        					 
		        					 $scope.setRbuDetalis = function(industryModel,rbutype) {
		        						 
		        						 if (industryModel == 1 && rbutype != null) {
		        							 
		        							 var getCurrentCustomerUserDetails = function(response) {
		        								 console.log("Delivery spoc details");
		        								 console.log(response.data);
		        								 $scope.frmRateCard.deliveryhead = response.data[0].duh;
		        								 $scope.frmRateCard.buhead = response.data[0].buh;
		        								 $scope.frmRateCard.vertical = response.data[0].verticalName;
		        								 $localStorage.currentUserVerticalId = response.data[0].verticalId;	
		        								 $scope.verticalId = response.data[0].verticalId;  
		        								 };  
		        								 
		        								 WebServiceFactory.getCurrentCustomerUserDetails($scope.verticalId,$scope.frmRateCard.rbutype)
		        								 .then(getCurrentCustomerUserDetails); 
		        								 }  							
		        						 }  							
		        					 $scope.setteamdatils = function(industryModel) {
		        						 
		        						 if (industryModel == 0 || industryModel == 2) {
		        							 
		        							 var getCurrentCustomerUserDetailsJVkpo = function(response) {
		        								 console.log("Delivery spoc details for KPO");	
		        								 console.log(response.data);
		        								 $scope.frmRateCard.deliveryhead = response.data[0].duh;
		        								 $scope.frmRateCard.buhead = response.data[0].buh;
		        								 $scope.frmRateCard.vertical = response.data[0].verticalName;
		        								 $scope.verticalId = response.data[0].verticalId;
		        								 };							
		        								 
		        								 WebServiceFactory.getCurrentCustomerUserDetailsJVkpo($scope.frmRateCard.industry,$scope.verticalId)
		        								 .then(getCurrentCustomerUserDetailsJVkpo);	
		        								 }  							
		        						 }
		        	   
						
							
				
	}]);