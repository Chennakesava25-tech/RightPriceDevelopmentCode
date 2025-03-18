	/*var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);*/
		app.controller("TMDealCreationRateCardAndProjectDetailsController",['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage','$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,$localStorage,$sessionStorage,$index) {
			
			//model initialization 
			$scope.frmDealProjectDetails={};
			$scope.rateCardName=[];
		    $scope.isSubmitDisable = false;
			$scope.currentRpVersionId = $localStorage.rpDealVersionId;
			$scope.currentcrmDealId = $sessionStorage.crmDealId;			
			var abc=$localStorage.DealModel;
			var customerId= $sessionStorage.rpCustomerId;
			var deal_Id= $sessionStorage.crmDealId;
			$scope.cityID = $sessionStorage.ssnCity_id;
			console.log("The City Id..." +$scope.cityID);
			$scope.countryId = $sessionStorage.countryId;
			console.log("The Country Id...." + $scope.countryId);
			var userType = sessionStorage.getItem('userType');
			
			var rpVrsId = $localStorage.rpDealVersionId
			
			$scope.RateCardDetailsHidden = true;
			 $scope.RegionWiseHidden = true;
			 $scope.AddLocationHidden = true;
			 $scope.TowerDetailsHidden = true;
			 $scope.ApplicationDetailsHidden = true; 
			 var contextPath = "/RightPrice-DAS";
             $scope.ShowHideRateCardDetails = function () {
                $scope.RateCardDetailsHidden = $scope.RateCardDetailsHidden ? false : true;
            };
            $scope.ShowHideRegionWise = function () {
			     $scope.RegionWiseHidden = $scope.RegionWiseHidden ? false : true;
			 };
			 $scope.ShowHideAddLocation = function () {
			     $scope.AddLocationHidden = $scope.AddLocationHidden ? false : true;
			 };
			 $scope.ShowHideTowerDetails = function () {
               $scope.TowerDetailsHidden = $scope.TowerDetailsHidden ? false : true;
           };
             $scope.ShowHideApplicationDetails = function () {
                $scope.ApplicationDetailsHidden = $scope.ApplicationDetailsHidden ? false : true;
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
				$localStorage.rpDealVersionId=$localStorage.rpDealVersionId;
		        window.location='TMDealCreationDetails';
		    }; 
		     $scope.Next = function()
		    {
		    	
		     	$sessionStorage.ContryList = $scope.chkBoxSelectedCountryList;
		     	$sessionStorage.CityList = $scope.locationDetails;
		     	$localStorage.rpDealVersionId = $localStorage.rpDealVersionId;
		     	
			   
			    //$sessionStorage.ContryList = $scope.chkBoxSelectedCountryList;
			    //End
		        window.location='TMDealCreationRoleSelection';
		    };  
		    
		    
		 // alert('crmDealId='+customerId);
		    
		   /* var getRateCardName = function(response) {
		    	//alert('55 getRateCardName'+$sessionStorage.crmDealId);
				//console.log("ratecardName")
				$scope.rateCardName = response.data;
				console.log($scope.rateCardName );
			};
			WebServiceFactory.getRateCardName($sessionStorage.rpCustomerId,$sessionStorage.crmDealId,$scope.cityID,$scope.countryId,$scope.Industry_ID).then(getRateCardName);
		    */
			//getting data into ratecard table 17th Jan2018
			 var rpversionId= $localStorage.rpDealVersionId;	
			 //hide because of overrriding
			 
				 //end hide because of overrriding
				//
		    
			
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
			  		console.log($scope.dealDetails[0].fpType==1? "Development" : "Maintenance");
			  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");
			  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType==1)? "Development" : "Maintenance";
			  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
			  		
			  		
			  		var statusId = $scope.dealDetails[0].currentApprovalStatus;			          
			    	  if((statusId == null || statusId == 1 || statusId == 4) && userType == 'Delivery')
						{			
	                	  $scope.isSubmitDisable = false;
						}
						else
						{
		                	  $scope.isSubmitDisable = true;
						}
			  		
			  		
			  		
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
			  		console.log($scope.dealDetails[0].currencyId);
			  		console.log($scope.dealDetails[0].dealDuration);
			  		console.log($scope.dealDetails[0].stageId);
			  		$localStorage.dealDetails_Industry_ID = $scope.dealDetails[0].projectIndustry;
				};				
				WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails);//WebServiceFactory.getDealDetails($sessionStorage.crmDealId).then(getDealDetails);
				
				
				if($sessionStorage.Industry_ID != undefined) 
				{
					$scope.Industry_ID = $sessionStorage.Industry_ID;
				}
				else if($localStorage.local_Industry_ID  != undefined) 
				{
					$scope.Industry_ID = $localStorage.local_Industry_ID;
				}
				else if($localStorage.dealDetails_Industry_ID != undefined)
				{		
					$scope.Industry_ID = $localStorage.dealDetails_Industry_ID ;					
				}
				else
				{
					//$scope.Industry_ID=1;			
				}
				
				 var getRateCardName = function(response) {
					 if(response.data != undefined && response.data != null && response.data.length!=0)
						 {
						 	$scope.rateCardName = response.data;
							console.log($scope.rateCardName );
						 }
				    	//alert('55 getRateCardName'+$sessionStorage.crmDealId);
						//console.log("ratecardName")
						
					};
					WebServiceFactory.getRateCardName($sessionStorage.rpCustomerId,$sessionStorage.crmDealId,$scope.cityID,$scope.countryId,$scope.Industry_ID).then(getRateCardName);
				    	
				
			
			//21st dec added by arvind get country region data
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
	    	 $scope.totalUtilization=0;
	    	 $scope.rateCardDetails=[]; //arvind
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
		     		
		     	 };
		     	 
	    	$scope.getCities = function(countryId){
	        	var getCities = function(response) {
	        		$scope.city = response.data;
	        		console.log("City Names are...........");
	        		console.log($scope.city);
	        	};
	        	WebServiceFactory.getCities(countryId).then(getCities);
	        };
	        
	  /*      //arvind
	        $scope.getCountry = function(rcId){
	        	//alert('onchange');
	        	var getCountry = function(response) {
	        		$scope.country = response.data;
	        		console.log("country Names are1...........@");
	        		console.log($scope.country);
	        		
	        	};
	        	WebServiceFactory.getCountry(rcId).then(getCountry);
	        };
	        //  */
	        
	        $scope.addLocationRow =function(country, city){
				//check for duplicate entry
				var isDuplicateCityEntry=false;
				angular.forEach($scope.locationDetails,function(value, key) {
					if(value.countryId == country.countryId && value.cityId == city.cityId ){
						if(!isDuplicateCityEntry){
							isDuplicateCityEntry=true;
						}
					}
				});
				
				console.log("isDuplicateCityEntry : " + isDuplicateCityEntry);
				if(isDuplicateCityEntry){
					BootstrapDialog.show({
						title : 'Fix Prize Deal Creation',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'Location <strong>'+ city.cityName + '</strong> is already added in location details.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
				}
				else{
					$scope.locationDetails.push({
						"countryId":country.countryId,
						"countryName": country.countryName,
						"cityId":city.cityId,
						"cityName":city.cityName,
						"utilization":0
					});
				}
				
				console.log($scope.locationDetails);
			};
			
			//arvind
				
			 $scope.addRateCardRow =function(rateCard_Name){
				 //alert('rateCard_Name id='+rateCard_Name.rcId);
				
				//check for duplicate entry
					var isDuplicateRateEntry=false;
					angular.forEach($scope.rateCardDetails,function(value, key) {
						if(value.rcId == rateCard_Name.rcId){
							if(!isDuplicateRateEntry){
								isDuplicateRateEntry=true;
							}
						}
					});
					
					console.log("isDuplicateRateEntry : " + isDuplicateRateEntry);
					if(isDuplicateRateEntry){
						BootstrapDialog.show({
							title : 'TM Deal Creation',
							type : BootstrapDialog.TYPE_DANGER,
							message : 'RateCard: <strong> '+ rateCard_Name.rcName+'( '+ rateCard_Name.rcId + '</strong> ) is already added in RateCard details.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								}
							} ]
						});
					}
					else{
						var tempDate1 = rateCard_Name.rcStartDate;
						var date1 = new Date(tempDate1.substring(0,10));
						var RCStartDate = $filter('date')(date1,'dd/MM/yyyy');
						
						var tempDate2 = rateCard_Name.expectedRCEndDate;
						var date2 = new Date(tempDate2.substring(0,10));
						var RCEndDate = $filter('date')(date2,'dd/MM/yyyy');
						
						$scope.rateCardDetails.push({
							"rcId":rateCard_Name.rcId,
							"rcName":rateCard_Name.rcName,							
							"weightage":0,
							"RC_Start_Date":RCStartDate,
							"RC_End_Date":RCEndDate	
						});
					}
					var currentRpVersionId1 = $localStorage.rpDealVersionId;
					//alert('currentRpVersionId=='+currentRpVersionId1);
					$scope.viewRatecardData(currentRpVersionId1);
					console.log($scope.rateCardDetails);
				 
			 };
			$scope.viewRatecardData = function(currentRpVersionId)
			{
				//alert('currentRpVersionId='+currentRpVersionId);
			}
			
			 
			 $scope.removeRateCard=function(index){
					$scope.rateCardDetails.splice(index,1);
				};
			
			//
			$scope.removeLocation=function(index){
				$scope.locationDetails.splice(index,1);
			};
			
			/*$scope.addTowerDetails = function(towerName){
				
				$scope.towerDetails.push({
					"towerName":towerName,
					"towerUtilizationPercentage":""
				});
				$scope.frmDealProjectDetails.txtTowerNameModel = "";
			};*/
			
			//default tower Name
			$scope.towerDetails.push({
				"towerName":"Tower",
				"towerUtilizationPercentage":"100"
			});
			
			
			$scope.removeTowerDetail = function(index){
				$scope.towerDetails.splice(index,1);
			};
				
			$scope.saveTMDealCreationRCAndProjectDetails = function(){
				
				if($scope.rateCardDetails.length > 0)
					{
					$scope.weightage_total=0;
					  for(var x=0; x < $scope.rateCardDetails.length ;x++)
						{
						  $scope.weightage_total = parseFloat($scope.weightage_total)+parseFloat($scope.rateCardDetails[x].weightage);
						}
					  	//alert($scope.weightage_total);
					  	if($scope.weightage_total != 100 || $scope.weightage_total != 100.00)
					  		{
					  		BootstrapDialog.show({
					        	title : 'TM Deal Creation',
					        	type : BootstrapDialog.TYPE_DANGER,
					        	message : 'summation of total weightage can not be greater/less than 100 ',
					        	closable : false,
					        	buttons : [{
					        		label : 'OK',
					        		action : function(dialogRef) {
					        			dialogRef.close();
					        		//	window.location = "MasterCountryForex";
					        		}
					        	}]
					        });
					  		
					  		}
					else
						{
							 $scope.regionWiseUtilization=[];		             
				             angular.forEach($scope.regionWiseUtilizationDetails, function(value,key) {
				             	if(value.selectChkBox){
				             		$scope.regionWiseUtilization.push({
				             			"baseCountryId":value.countryId,
				             			"utilization":value.utilization
				             		});
				             	}
				             });
				             
							var marker={
									"dealVersionId":dealVersionId,
									"dealCurrencyUtilization":$scope.regionWiseUtilization,
									"dealLocation":$scope.locationDetails,
									"dealTower":$scope.towerDetails,
									"dealRateCard":$scope.rateCardDetails
							};
							console.log(marker);
							console.log(angular.toJson(marker));
							
							var saveTMDealCreationRCAndProjectDetails=function(response){
								console.log("data");
								console.log(response);
								console.log(response.status);
								 if( response.status == 200){
									  BootstrapDialog.show({
												title : 'T&M Deal Creation - Rate Card and Project Details',
												type : BootstrapDialog.TYPE_PRIMARY,
												message : 'RateCard Project Details saved successfully.',
												closable : false,
												buttons : [ {
													label : 'OK',
													action : function(dialogRef) {
														dialogRef.close();
														window.location = "TMDealCreationRateCardAndProjectDetails";
														//$window.location.reload();
														// window.location="RateCardCreationRoleSelection";
													}
												} ]
											});
									  } else {
						    				BootstrapDialog.show({
							    	        	title : 'T&M Deal Creation - Rate Card and Project Details',
							    	        	type : BootstrapDialog.TYPE_DANGER,
							    	        	message : 'Currently facing technical issue.',
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
							WebServiceFactory.saveTMDealCreationRCAndProjectDetails(marker).then(saveTMDealCreationRCAndProjectDetails);
						}
				}
				
			};
	    //onload function call
	    	
	    	
	    	var getCountryDetail = function(response) {
	    		console.log("Deal version ID : " + $localStorage.rpDealVersionId);
	    		console.log("get country data");
		  		console.log(response);
		  		$scope.country = response.data;
		  		angular.forEach($scope.country, function (value, key) {
		  			if(value.countryId == $scope.countryId) {
		  				$scope.currencyId = value.currencyId;
		  			}
	               });
		  		
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
			var getVersionData = function(response) {
				console.log("get versionDetails Data..");
				console.log(response);
				$scope.getVersionData=response.data;
				
				if($scope.getVersionData!=undefined)
				{
					$localStorage.pageTracker=$scope.getVersionData[0].pageTrackerStatus;
					$localStorage.CurrentApprovalStatus=$scope.getVersionData[0].currentApprovalStatus;
					if(userType == 'Delivery')
						{
						if(($localStorage.CurrentApprovalStatus == null || $localStorage.CurrentApprovalStatus == 1 ||
								$localStorage.CurrentApprovalStatus == 4 ) && $localStorage.pageTracker<1)
							{	BootstrapDialog.show({
								title : 'T&M Deal Creation - Rate Card and Project Details',
								type : BootstrapDialog.TYPE_DANGER,
								message : "Please save on the previous screen to proceed ahead",
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										window.location="TMDealCreationDetails";
									}
								} ]
							});	
								
							}
						
					}
					
					}
				
			};
			WebServiceFactory.getVersionData($scope.currentRpVersionId).then(getVersionData);
	    	
			//var getFPDealCreationRCAndProjectDetails=function(response){
			var getTMDealCreationRCAndProjectDetails=function(response){
				console.log("getTMDealCreationRCAndProjectDetails");
				console.log(response.data);
				//push tower details
				/*angular.forEach(response.data.dealTower,function(value,key){
					$scope.towerDetails.push({
						"towerName":value.towerName,
						"towerUtilizationPercentage":value.towerUtilizationPercentage
					});
				});*/
				
				
				//push ratecard details
				console.log("The Array Data in the RateCard is in T&M.......... ");
				console.log(response.data.dealRateCard);
				angular.forEach(response.data.dealRateCard,function(value,key){
					
					var tempDate1 = value.rateCrdDetails.rcStartDate;
					var date1 = new Date(tempDate1.substring(0,10));
					var RCStartDate = $filter('date')(date1,'dd/MM/yyyy');
					
					var tempDate2 = value.rateCrdDetails.rcEndDate;
					var date2 = new Date(tempDate2.substring(0,10));
					var RCEndDate = $filter('date')(date2,'dd/MM/yyyy');
					
					$scope.rateCardDetails.push({
						"rcId":value.rateCrdDetails.rcId,
						"rcName":value.rateCrdDetails.rcName,					
						"weightage":value.weightage,
						"RC_Start_Date":RCStartDate,
						"RC_End_Date":RCEndDate						
						//"gmPercentage":value.rateCrdDetails.calculatedGMPercentagePostDiscount
					});
				});
			//	console.log("The data in the Array for RateCard in T&M is......... ");
			//	console.log($scope.rateCardDetails);
			//	$sessionStorage.rateCardDetailsList = $scope.rateCardDetails; 
				
				
				
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

				console.log("the rate Card Location Details is............ ");
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
	    
			//WebServiceFactory.getFPDealCreationRCAndProjectDetails(dealVersionId).then(getFPDealCreationRCAndProjectDetails);
			WebServiceFactory.getTMDealCreationRCAndProjectDetails(dealVersionId).then(getTMDealCreationRCAndProjectDetails);
			
			
			/*$scope.showRateCardDetails = function(rcId, approverId, approvalStatus, approverRoleId){*/
			$scope.showRateCardDetails = function(rcId){
				
				/*console.log("showRateCardDetails");	
				if(approverId=="N/A")
				{
					approverId="";
				}	*/
				//console.log("showRateCardDetails1 "+rcId+" "+approverId+" "+approvalStatus+" "+approverRoleId)	
				$localStorage.rcId = rcId;
				/*$localStorage.rateCardApproverId = approverId;
				$localStorage.rateCardApprovalStatus = approvalStatus;
				$localStorage.rateCardApproverRoleId = approverRoleId;*/	
				
				var browser = window.navigator.appVersion;
				
			    //Workaround to enable the users to download the report in IE.
			    if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
			           (browser.indexOf('MSIE 10') !== -1)) 
			    {
			    	 window.open('RateCardCreationDetails', '$localStorage.rcId');
//			    	 window.open('RightPrice/RateCardCreationDetails', '$localStorage.rcId');
			          /* if(approvalStatus == 2 || approvalStatus == 3){
			                 window.open('RightPrice/RateCardCreationSummary', '$localStorage.rcId');
			           }
			           else{
			                 window.open('RightPrice/RateCardCreationDetails', '$localStorage.rcId');
			           }*/
			    } 
			    else 
			    {
			           /*if(approvalStatus == 2 || approvalStatus == 3)
			           {
			                 window.open('RightPrice/RateCardCreationSummary', '_blank', '$localStorage.rcId');
			           }
			           else{
			                 window.open('RightPrice/RateCardCreationDetails', '_blank','$localStorage.rcId');
			           }*/
			    	 window.open('RateCardCreationDetails', '_blank','$localStorage.rcId');
//			    	 window.open('RightPrice/RateCardCreationDetails', '_blank','$localStorage.rcId');
			    }

			}	
	    
	   
	}]);