//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("RateCardCreationController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 console.log("inside RateCardCreationController");
		 var contextPath = "/RightPrice-DAS";	
		 $scope.frmRateCard={};
		 $scope.saved = true;
		 $scope.upload= false;
		 $scope.cityAdd = false; 
		 
		 $scope.isFirstTimePageLoaded = true;	
		 $scope.rateCardHidden = true;
		 $scope.uploadBtnDisable = false;
		 
		 $scope.numberRegex = /^(0|[1-9]\d{0,1})(\.\d{1,2})?$/;
		 var userType = sessionStorage.getItem('userType');
		 console.log("The USer Value from the Session is........ "+ userType);
		 $scope.city = [];
		 $scope.hundPerOnsite=false;
		 $scope.hundPerOffshore=false;
		 $scope.notmandatonsave = false;
		 $scope.cityListNew = [];
		$scope.regionWiseUtilizationDetails=[];
		$scope.country=[]; 
		
		  
		 
	 	 $scope.isRateCardIDdisabled = false;
	 	 $scope.actionType = [{ name: "Add", id: 1 }, { name: "Edit", id: 2 }];
	 	 
	 	 
	 	  $scope.resetFormDataOnCustomerChange=function(){
     		// $scope.frmRateCard.action="Please select";
     		$localStorage.tempAction = null;
     		$scope.frmRateCard.action = 1;
     		 $scope.resetFormDataOnActionChange();
     	 };
	 	 
	 	  $scope.resetFormDataOnActionChange = function(){
     		//alert("reset form");
     		//reset Rate Card Details
     			if($scope.frmRateCard.action==1 )
	     		{
	     		    $scope.saved = false;
					$scope.isRateCardIDdisabled = true;
					$scope.MDAttachementData=[];
					$scope.rateCardHidden = true;
					$scope.getCustomerVerticalFP($scope.frmRateCard.customer);
					//$scope.isAddDisabled = false;
					/*$scope.isFirstTimePageLoaded = false*/
					$scope.frmRateCard.ratecardname =null;
	            		$scope.frmRateCard.startdate =null;
	            		$scope.frmRateCard.enddate =null;
	            		$scope.frmRateCard.blendedRate=null;
	            		$scope.frmRateCard.currency =null;
	            		$scope.frmRateCard.countryNameModel=null;
	            		$scope.frmRateCard.cityNameModel=$scope.null;
	            		$scope.frmRateCard.applicableMonths=null;
	            		$scope.frmRateCard.industry =null;
	            		$scope.frmRateCard.customerDesc=null;
	            		$scope.frmRateCard.tcv =null;
	            		$scope.frmRateCard.blendedCost=null;
	            		$scope.frmRateCard.marginBeforeDiscount=null;
	            		$scope.frmRateCard.marginafterdiscount=null;
	            		$scope.frmRateCard.marginDiscount=null;
	            		$scope.frmRateCard.expectedonsiteresource =null;
	            		$scope.frmRateCard.expectedoffshoreresource =null;
	            		$scope.getRateCardsNew($scope.rateCardDetails.customerId);
						//$scope.frmRateCard.customer = ;
						$scope.frmRateCard.countryNameModel =null;
						$scope.getCities(parseInt($scope.rateCardDetails.countryId));
						$scope.frmRateCard.cityNameModel = null;
						$scope.frmRateCard.rateCardId = null;
						//$scope.getCustomerVerticalId($scope.frmRateCard.customer);
			            //$scope.getFileData(rateCardId);
	            		
	       	
					
	     		}
     			else if($scope.frmRateCard.action == 2  ){
     				$scope.isRateCardIDdisabled = false;
     				//$scope.isAddDisabled = false;
     			}
     			
	     				
     	 };
     	 
	 	 
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
		 			
		 			$scope.isRateCardType = false;
		 			$scope.isNextbtnDisable = false;
		 			
		 			
		 			$scope.resetFormDataOnActionChange();
		 			
		 			$scope.isUpdate=false;
		 			
		 		 }
		 		 else {
		 			 //edit action
		 			 
		 			 $scope.isRateCardIDdisabled = false;
		 			 $scope.isFirstTimePageLoaded = false;
		 			
		 			 $scope.uploadBtnDisable = false;
		 			 if($sessionStorage.customerVerticalMapId != undefined) {
		 				 $scope.getRateCardsNew($sessionStorage.customerVerticalMapId);
		 			 } 
		 		 } 
	 	 
	 	 };
	 	 
	 	 
	 	 
	 	 
	     $scope.yearDiference=0;
     	
     	 $scope.uploadPanelHide = true;
     	 $scope.btnDisable = false;
     	 $scope.isDisabled = true;
     	 
     	 $scope.isKpoSelected = false;
     	 $scope.autosidebar=false;
     	
     	 $scope.MDAttachementData=[];
     	 var rateCardStatus="";
     	 
     	 
     	
     	$scope.isOffshoreCityList = [];
     	$sessionStorage.currentUser = null;
     	$sessionStorage.verticalId = 0;
     	
     
     	 // region wise utilization calculation	
     	 
	 				
     	 
     	 //auto calculate expected offshore %
     	$scope.calculateExpectedOffshore = function(){
     		var offShorePercentage = $filter('number')(100 - $scope.frmRateCard.expectedonsiteresource,2)
     		$scope.frmRateCard.expectedoffshoreresource = offShorePercentage;  
     		var onsitePercentage = $filter('number')($scope.frmRateCard.expectedonsiteresource,2)
     		$scope.frmRateCard.expectedonsiteresource = onsitePercentage; 
     		$scope.offShoreUtilizationModel = offShorePercentage;
     		if($scope.frmRateCard.expectedoffshoreresource  == 100) {
     			
     	     	$scope.hundPerOnsite=true;
     	     	$scope.hundPerOffshore=false;
     	     	$scope.frmRateCard.offshorehoursperday =$scope.offsiteHoursPerDay;
     			if($scope.frmRateCard.industry == 1) {
     				$scope.isRateCardType = true;
     			}
     		} else if($scope.frmRateCard.expectedonsiteresource  == 100)
     			{
     			
     			$scope.isRateCardType = false;
     			$scope.hundPerOffshore=true;
     			$scope.hundPerOnsite=false;
     			
     			}
     		else
     		{
     			$scope.hundPerOnsite=false;
     			$scope.hundPerOffshore=false;
     			
     			$scope.isRateCardType = false;
     			
     		}
     	 }
     	 
     	 
     	 
     	 //reset form data on change of customer
     	
     	 //append default hours value based on customer
     	     	 
     	 
     	 
	 	 
            $scope.getCities = function(countryId){
               
            	if(countryId != undefined) {
            		var getCities = function(response) {
            			$scope.city = response.data;
            			if($scope.city != undefined) {
            				$sessionStorage.cityArray = $scope.city;
            				console.log("cityArray Details is...... ");
            				console.log($sessionStorage.cityArray);
            				$sessionStorage.cityListNew=$sessionStorage.cityArray;
            			}
            		};
            		WebServiceFactory.getCities(countryId).then(getCities);
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
			
		
			
			
			
			
			
			
			
			
			
			//save rate card
			$scope.saveRateCardNew=function(frmRateCard){
				$scope.saved = true;
				 var sdate = $scope.frmRateCard.startdate;
                var datearray = sdate.split("/");

                var newdate = datearray[1] + '/' + datearray[0] + '/' + datearray[2];
                var edate = $scope.frmRateCard.enddate;
                var datearray = edate.split("/");

                var newenddate = datearray[1] + '/' + datearray[0] + '/' + datearray[2];
				
				  var marker={
	            			   "rcId": $scope.frmRateCard.rateCardId,
	            			   "customerId":$scope.frmRateCard.customer,
	            			   "rcName": $scope.frmRateCard.ratecardname,
	            			   "rcStartDate":newdate,
	            			   "rcEndDate":newenddate,
	            			   "tvc":$scope.frmRateCard.tcv,
	            			   "isItKpo":$scope.frmRateCard.industry,
	            			   "baseCountryName":$scope.frmRateCard.countryNameModel,
	            			   "baseCityName":$scope.frmRateCard.cityNameModel,
	            			   "countryId":$scope.frmRateCard.countryNameModel,
	            			   "cityId":$scope.frmRateCard.cityNameModel,
	            			   "applicableMonths":$scope.frmRateCard.applicableMonths,
	            			   "expectedOnsiteResourcePercentage":$scope.frmRateCard.expectedonsiteresource,
	            			   "expectedOffshoreResourcePercentage":$scope.frmRateCard.expectedoffshoreresource,
	            			   "customerdesc":$scope.frmRateCard.customerDesc,
	            			   "blendedRate":$scope.frmRateCard.blendedRate,
	            			   "blendedCost":$scope.frmRateCard.blendedCost,
	            			   "marginbeforediscount":$scope.frmRateCard.marginBeforeDiscount,
	            			   "marginafterdiscount":$scope.frmRateCard.marginafterdiscount,
	            			   "discount":$scope.frmRateCard.marginDiscount,
	            			   "consolidatedRcCurrencyId":$scope.frmRateCard.currency,
	            			   
	            			   
	            			   
	            			   
	            			   
			}	//Need to add session
				
				//console.log(frmRateCard.$valid);
				//console.log(frmRateCard);
				//console.log($scope.frmRateCard.$error);
				//console.log($scope.frmRateCard.$error.required);
				
				
	            var saveRateCardNew=function(response){
					console.log("data");
						console.log(response);
						console.log(response.data);
						 if( response.status == 200){
							 $localStorage.rcId=response.data; 
							 $scope.frmRateCard.rateCardId=response.data; 
							  BootstrapDialog.show({
										title : 'Rate Card Creation',
										type : BootstrapDialog.TYPE_PRIMARY,
										message : 'Rate card created successfully.',
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(dialogRef) {
												dialogRef.close();
												 window.location="RateCardCreationDetails_New";
											}
										} ]
									});
							  }
	
					};
					WebServiceFactory.saveRateCardNew(marker).then(saveRateCardNew);
				
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
                      $scope.getRateCardDetailsFromRCIdGFT($localStorage.rcId);						 
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
							 $scope.getRateCardDetailsFromRCIdGFT($localStorage.rcId);
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
						 $scope.getRateCardDetailsFromRCIdGFT($localStorage.rcId);
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
						 $scope.getRateCardDetailsFromRCIdGFT($localStorage.rcId);
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
		  		console.log(response);
				$scope.country = response.data;
		  		angular.forEach($scope.country, function (value, key) {
	                   $scope.regionWiseUtilizationDetails.push({
	                	   "countryId": value.countryId, 
	                	   "countryName": value.countryName, 
	                	   "currencyId": value.currencyId, 
	                	   "currencyCode": value.currencyCode,
	                	   "utilization":0,
	                	   "rateCardProcessingType": value.isOnlyManRCDeal,
	                	   "selectChkBox":false
	            	   });
	               });
		  		$scope.setDisabledCntry ();
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			 
			$scope.getCurrentCustomerUserDetails = function(verticalId) {
				var getCurrentCustomerUserDetails = function(response) {
					console.log("Delivery spoc details");
					console.log(response.data);
					$scope.frmRateCard.deliveryhead=response.data[0].duh;
					$scope.frmRateCard.buhead=response.data[0].buh;
					$scope.frmRateCard.vertical=response.data[0].verticalName;
					$localStorage.currentUserVerticalId=response.data[0].verticalId;
					
				};
				WebServiceFactory.getCurrentCustomerUserDetails(verticalId).then(getCurrentCustomerUserDetails);
			} 
			
			
			
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
	        			
	        			
	        		
	        		if($scope.rateCardHidden == false) {
	        			$scope.getRateCardsBasedOnIndustry($sessionStorage.customerVerticalMapId,$scope.frmRateCard.industry);
	        		}
	        	}
	        
	        	
	        	$scope.getRateCardsBasedOnIndustry = function(customerVerticalId,industry) {
	        		console.log("customerVerticalId" + customerVerticalId);
	        		console.log("Industry"+industry);
	        		var getRateCardsBasedOnIndustry = function(response) {
	        			$scope.rateCardOnIndustry = response.data;
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
							$sessionStorage.verticalId = $scope.verticalId;
							 $scope.getCurrentCustomerUserDetails($sessionStorage.verticalId);
						}
						console.log($scope.currentCustVertical);
					};
					WebServiceFactory.getCustomerVerticalFP(custmIdforVertical).then(getCustomerVerticalFP);
				};
				
		$scope.getRateCardDetailsFromRCIdGFT=function(rateCardId){
            	//alert(rateCardId);
            	
            	if(rateCardId){
            		//get data for rate card id iff rate card id is !undefined
            		$scope.searchFile(rateCardId);
            		
            		$localStorage.rcId=rateCardId;
            		//$scope.onloadCoun_CityNew();
	            	var rateCardDetails=function(response){
	            	console.log("save data");
	            		console.log(response.data);
	            		
	            		/*$scope.frmRateCard.action = $localStorage.tempAction;
						if($scope.frmRateCard.action == 1){
							$scope.frmRateCard.action = 2;
						}*/
						//$scope.checkFlag($scope.frmRateCard.action);
	            		$scope.rateCardDetails= response.data[0];
	            		

	            	  if($scope.rateCardDetails != undefined){
	            		
	            		$scope.frmRateCard.action=2;
	                   $scope.frmRateCard.ratecardname =$scope.rateCardDetails.rcName;
	            		$scope.frmRateCard.startdate =$filter('date')(new Date($scope.rateCardDetails.rcStartDate.substring(0,10)),'dd/MM/yyyy');
	            		$scope.frmRateCard.enddate =$filter('date')(new Date($scope.rateCardDetails.rcEndDate.substring(0,10)),'dd/MM/yyyy');
	            		$scope.frmRateCard.blendedRate=$scope.rateCardDetails.blendedRate;
	            		$scope.frmRateCard.currency =$scope.rateCardDetails.consolidatedRcCurrencyId;
	            		$scope.frmRateCard.countryNameModel=$scope.rateCardDetails.baseCountryName;
	            		$scope.frmRateCard.cityNameModel=$scope.rateCardDetails.baseCityName;
	            		$scope.frmRateCard.applicableMonths=$scope.rateCardDetails.applicableMonths;
	            		$scope.frmRateCard.industry =$scope.rateCardDetails.isItKpo.toString();
	            		$scope.frmRateCard.customerDesc=$scope.rateCardDetails.customerdesc;
	            		$scope.frmRateCard.tcv =$scope.rateCardDetails.tvc;
	            		$scope.frmRateCard.blendedCost=$scope.rateCardDetails.blendedCost;
	            		$scope.frmRateCard.marginBeforeDiscount=$scope.rateCardDetails.marginbeforediscount;
	            		$scope.frmRateCard.marginafterdiscount=$scope.rateCardDetails.marginafterdiscount;
	            		$scope.frmRateCard.marginDiscount=$scope.rateCardDetails.discount;
	            		$scope.frmRateCard.expectedonsiteresource =$scope.rateCardDetails.expectedOnsiteResourcePercentage;
	            		$scope.frmRateCard.expectedoffshoreresource =$scope.rateCardDetails.expectedOffshoreResourcePercentage;
	            		$scope.getRateCardsNew($scope.rateCardDetails.customerId);
						$scope.frmRateCard.customer = $scope.rateCardDetails.customerId;
						$scope.frmRateCard.countryNameModel =parseInt($scope.rateCardDetails.countryId);
						$scope.getCities(parseInt($scope.rateCardDetails.countryId));
						$scope.frmRateCard.cityNameModel = parseInt($scope.rateCardDetails.cityId);
						$scope.frmRateCard.rateCardId = $scope.rateCardDetails.rcId;
						//$scope.getCustomerVerticalId($scope.frmRateCard.customer);
			            //$scope.getFileData(rateCardId);
	            		
	       	
	 }
	 else{
	 $scope.frmRateCard.action=1;
	 }
					};
	            	WebServiceFactory.getRateCardDetailsFromRCIdGFT(rateCardId).then(rateCardDetails);
	            	
            	}
            };
            		
	$scope.getRateCardsNew = function(customerVerticalMappingId){
	//alert(customerVerticalMappingId);
                var getRateCards = function(response) {
                //	console.log("status : "+response.status);
                	if( response.status == 205){
						  $scope.rateCardId=$localStorage.rcId;
						   /*$scope.countryId=$localStorage.baseCountryName;
						  $scope.cityId=$localStorage.baseCityName;*/
						  
						  }
                	else{
                	     //alert("hi");
                		$scope.rateCardId=response.data;
                	}
                   /* $scope.city = response.data;
                    console.log("City Names are...........");
                	console.log($scope.city);*/
                };
                WebServiceFactory.getRateCardsNew(customerVerticalMappingId).then(getRateCards);
            };			
				 
				 
		$scope.uploadDataNew = function(frmRateCard){
				//alert("uploadData called");
				$scope.upload= true;
				
				if(frmRateCard.$valid){
					$scope.upload= false;
					//alert("inside valid");
					if($scope.frmRateCardManualUploadNew.fuUploadFilenameModel != true) {
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
									window.location = "RateCardCreationDetails_New";
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
			    	console.log(fileTest);
			    	var name = "Template";
			    	var tempID = 1;	
			    	var category="R";
			    	var rcId= $scope.frmRateCard.rateCardId;
		    		//console.log("fileTest");
					var file = $('input[name="fuUploadFilename"]').get(0).files[0];
					console.log(file);
					console.log(file.name);
					var filename=file.name;
					var formData = new FormData();
					formData.append('file', file);
					formData.append('name', filename);
					
					formData.append('rcId',rcId);
					//formData.append('category',category);
					//console.log(file);
			    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadRCFileNew";
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
											title : 'File Uploaded Successfully',
											type : BootstrapDialog.TYPE_PRIMARY,
											message : customMessage,
											closable : false,
											buttons : [ {
											label : 'OK',
											action : function(
											dialogRef) {
											dialogRef.close();
											$scope.searchFile(rcId);
											$scope.frmRateCard.fuAttachFilenameModel = undefined;
											}
											} ]
											});
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
				 
				 
		
			 
			
			
			
			  $scope.searchFile = function(rcId)
				{
				  console.log(rcId);
					angular.element("input[type='file']").val(null);
					console.log("inside File Search---"+rcId);
                   $scope.MDAttachementData=[];
					var markers = {
								"rcId" : rcId
							};
					$http({
					    method: 'POST',
					    url: contextPath+'/RightPrice-DAS/getMasterAttachementRC',
					    dataType: 'json',
					    data: JSON.stringify(markers),  
				     headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					    }).
						    then(function(data) {
						    	if(data.data.length!=0){
									$scope.tableHide=false;
									$scope.temp=[];
									$scope.temp=data.data;
									
									console.log("temp");
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
									$scope.tableHide=true;
								}
						    	
						    	
						    });
					
				}
			  
	            // Download the Existing File
	            $scope.downloadFile = function(objectId){
	        		WebServiceFactory.downloadFileWithFileName(objectId);
	        	};

             $scope.downloadFileRC = function(id){
               //  alert(id);
                    console.log(id);
	        		WebServiceFactory.downloadFileWithFileNameRC(id);
	        	};

				$scope.deleteRCFile = function(AttachmentId) {

					console.log('inside delete method')
					console.log(AttachmentId);
					WebServiceFactory.deleteRCFile(AttachmentId);
					for(var i = 0; i < $scope.MDAttachementData.length;i++)
					{
					if($scope.MDAttachementData[i].id == AttachmentId)
					{
					$scope.MDAttachementData.splice(i,1);
					}
					}
					if($scope.MDAttachementData.length == 0){
					$scope.tableHide = true;
					}


};
                $scope.cancelRateCard=function(frmRateCard){
				 window.location= contextPath+"/welcome";
			
			};
			
			
			
		  	
	}]);			
	
	