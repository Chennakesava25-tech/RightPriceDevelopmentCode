//var app = angular.module('RateCardSummaryApp', [,'ngStorage']);
		app.controller("RateCardSummaryController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage,$index) {
			 $scope.ApproverActionHidden = true;
			 var contextPath = "/RightPrice-DAS";
			 $scope.isValidGFTMember = false;
			 $scope.isDisabled = true;
			 $scope.yearWiseSummaryOnPageLoad = true;
			 $scope.tableDisplay = false;
			 $scope.yoytableDisplay=false;
			 $scope.multiCountryTable = false;
			 var userType = sessionStorage.getItem('userType');
			 console.log("The USer Value from the Session is........ "+ userType);
			 $scope.proxyUserId = $sessionStorage.proxyId;
			 var volumeDiscount = 0;
			 $scope.rateCardProcessingType =[];
			 $scope.trimmedValuefx =[];
			 $scope.tableIT = false;
			 $scope.tableKPO = false;
			 $scope.isItKpo = 0;
			 $scope.GFTApprover = false;
			 $scope.commentbox= false;
			 $scope.approvalcomment = false;
			 $scope.showaprovercoment =false;
			 $scope.rainbowApprovalPanel = true;
			 $scope.isApprovalType = false;
			 $scope.currencyuti =[];
			 $scope.memberDetail =[];
			 $scope.isDisable=false;
			 $sessionStorage.rainbowLevelFlag = null;
			 $sessionStorage.IsRainbowFlag = null;
			 $scope.rainbowApprovalLevel = [{id: 5,name: "Level 1"}, { id: 6,name: "Level 2" }];
			 $scope.rainbowApprovalFlag = [{id: 1,name: "Yes"}, { id: 2,name: "No" }];
			 $scope.allVerticalArray = []
			
			 //get vertical id from session
			// $scope.verticalId=2;
			 
		 var verticalId = $sessionStorage.verticalId;
			 
			 $scope.approverRoleId = $sessionStorage.rateCardApproverRoleId;
	
	var getGFTMemberDetails = function(response){
		console.log("getGFTMemberDetails");
		console.log(response);
		$scope.memberDetail=response.data;
	}
	WebServiceFactory.getGFTMemberDetails().then(getGFTMemberDetails);
			 
			 var rcId=$localStorage.rcId;
			// var rcId=9;
			 if(rcId == 0 || rcId == undefined){
			    //	alert("No data found for rc id 0");
			    	BootstrapDialog.show({
	    	        	title : 'Rate Card Creation - Summary',
	    	        	type : BootstrapDialog.TYPE_DANGER,
	    	        	message : 'Please Create New Rate Card or Select Existing One.',
	    	        	closable : false,
	    	        	buttons : [{
	    	        		label : 'OK',
	    	        		action : function(dialogRef) {
	    	        			dialogRef.close();
	    	        			//window.location = "RateCardCreationDetails";
	    	        		}
	    	        	}]
	    	        });
			    }  else {
					var getRateCardInfo = function(response) {
						console.log("get RateCardInfo");
				  		console.log(response);
				  		$scope.rateCardInfo = response.data;
				  		$scope.currencyName = $scope.rateCardInfo[0].currency;
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
				  		$sessionStorage.volumeDiscount = $scope.rateCardInfo[0].volumeDiscount;
				  		$sessionStorage.IsAtos=$scope.rateCardInfo[0].isAtos;
				  		$sessionStorage.avgVolumeDiscount = $scope.rateCardInfo[0].volumeDiscount;
				  		var approvalId = $scope.rateCardInfo[0].currentApproverId;
				  		$scope.isManualCheck = $scope.rateCardInfo[0].isManualRc;
				  		$scope.createdBy = $scope.rateCardInfo[0].createdBy
				  		//$scope.currencyName = [];
				  		isItKpo=$scope.rateCardInfo[0].isItKpo;
				  		
				  		if($scope.countryData != undefined) {
				  			angular.forEach($scope.countryData,function(value,key) {
				  				if($scope.countryData[key].currencyId == currencyId) {
				  					$scope.currencyName = $scope.countryData[key].currencyCode;
				  				}
				  			});
				  		}
				  		
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
                        
                        if(approvalId == userType) {
                        	$scope.GFTApprover = true;	
                        }
                        if(approvalId==$scope.user)
        		    			{
                        			$scope.showaprovercoment = false;
        		    				}
                        else
        		    			{
                        		$scope.showaprovercoment = true;
        		    			}
                        
                        
                      
                        
                        if($scope.isManualCheck == "M")
            			{
            				$scope.manualsidebar=true;
            				$scope.autosidebar=false;
            				$scope.hybridsidebar=false;
            			}
            		else if ($scope.isManualCheck == "H")
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
                        $sessionStorage.fxriskoffshore = $scope.rateCardInfo[0].fxriskoffshore; 
                        $sessionStorage.contingentRiskPercentage = $scope.rateCardInfo[0].contingentRiskPercentage;
                        $sessionStorage.slaRiskPercentage = $scope.rateCardInfo[0].slaRiskPercentage;
                        
                        
                          
                        
					};
					WebServiceFactory.getRateCardInfo(rcId).then(getRateCardInfo);
					
					var getPageTrackerData = function(response){
						$scope.pageTrackerData = response.data;
						console.log("page tracker data");
						console.log($scope.pageTrackerData);
						if(response.status == 200){
							
						if($scope.pageTrackerData[0].noOfSubmittedPages != $scope.pageTrackerData[0].noOfPages){
							BootstrapDialog.show({
			    	        	title : 'Rate Card Creation - Summary',
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
						}
						else{
							BootstrapDialog.show({
			    	        	title : 'Rate Card Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : 'Rate card not submitted at previous pages.',
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
					WebServiceFactory.getPageTrackerData(rcId).then(getPageTrackerData);	
					}
			 $scope.checkApprover = true;
			 $scope.currentDate = new Date();
			 
             $scope.ShowHideApproverAction = function () {
                $scope.ApproverActionHidden = $scope.ApproverActionHidden ? false : true;
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
				  $scope.isManualRCType = $sessionStorage.isManualRCType;
					if($scope.isManualRCType == "A") {
						$scope.isAutomatic = true;
					} else if($scope.isManualRCType == "M") {
						$scope.isManual = true;
					} else if($scope.isManualRCType == "H") {
						$scope.isHybrid = true;
					}
					
					if($scope.isAutomatic == true) {
						window.location ='RateCardCreationRoleUtilizationAndRates';
					} else if ($scope.isHybrid == true) {
						window.location='RateCardCreationGMRateDetails';
					} 
					else if($scope.isManual == true){
						window.location='RateCardCreationGMRateDetails';
					} 		
		    }; 
		     $scope.Next = function()
		    {   
		        window.location='RateCardCreationFinaliseRateCard';
		    };   
		    
		  //Web Service to get Country
		    $scope.gmDifference =0;  
			var getRateUtilizationCountry = function(response) {
			$scope.country = response.data;
			};
			WebServiceFactory.getRateUtilizationCountry(rcId).then(getRateUtilizationCountry);		    
		    
		    $scope.dealType = [{ name: "Fixed Price", id: 1 }, { name: "T & M", id: 2 }];
		    
		    
		    $scope.getSummaryDetails = function(user){
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
		    	 var getSummaryCurrencyUti = function(response) {
					 console.log(response);
				    $scope.currencyuti = response.data;
				    
				    if($scope.currencyuti!=undefined)
				    	{
				    	
				    	console.log("Data from curreny utilization :"+$scope.currencyuti.length);
				    	angular.forEach($scope.currencyuti, function(value,key) {
				    		if($scope.currencyuti[key].rateCardProcessingType==1)
				    		{
				    			$scope.rateCardProcessingType.push($scope.currencyuti[key].baseCountryId);
				    		}
				    		
				    	});
				    	console.log($scope.rateCardProcessingType);
				    	
				    	if(response.status == 204 && rcId!=0){
				    		//alert("No data found");
				    		BootstrapDialog.show({
				    			title : 'Rate Card Creation - Summary',
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
				    }
				    else
				    	{
				    		getSummaryCurrencyUti(rcId)
				    	}
				};
			    WebServiceFactory.getSummaryCurrencyUti(rcId).then(getSummaryCurrencyUti);
			    
			    var getRateCardLocation = function(response) {
		    	$scope.currencyutilocation = response.data;
		    		
		    	if($scope.currencyutilocation!=undefined)
		    		{
		    		console.log("$scope.currencyutilocation::::::::::::::::::::::::::::::::");
		    		console.log($scope.currencyutilocation);
		    		/*$scope.summarylocation = response.data;*/
		    		//alert("get summary");
		    		console.log("get location details");
		    		console.log(response); 
		    		 console.log("Data from ratecard location :"+$scope.currencyutilocation.length);
		    		 $scope.locationlength=$scope.currencyutilocation.length;
		    		 $scope.user = user;
		    		if($scope.currencyuti.length!=0)
		    			{
		    			if($scope.currencyuti.length == 1) {
		    				 angular.forEach($scope.currencyuti, function(value,key) {
		    					 if($scope.currencyuti[key].rateCardProcessingType == 0 && $scope.locationlength <=1)
		    					 {
		    						 $scope.tableDisplay = true;
		    						 $scope.multiCountryTable = false;
		    					 }
		    					 else{
		    						 $scope.tableDisplay = false;
		    						 $scope.multiCountryTable = true;
		    					 }
		    				 });
		    			 } 
		    			 else{
		    				 $scope.tableDisplay = false;
		    				 $scope.multiCountryTable = true;
		    			 }
		    			}
		    		else
		    			{
		    				WebServiceFactory.getSummaryCurrencyUti(rcId).then(getSummaryCurrencyUti);
		    			}
		    			 
		    	 	 }
		    	else
		    		{
		    			$scope.getSummaryDetails(user);
		    		}
		    		
			    };
		    	WebServiceFactory.getRateCardLocation(rcId).then(getRateCardLocation);
		    	
		    
			    
		    	var getYoyIncrement = function(response) {
		    		//alert("get summary");
		    		console.log("get yoy increment");
		    		console.log(response);
		    	    $scope.yoyIncrement = response.data;
		    	    $scope.yearCount=$scope.yoyIncrement.length+1;
		    	    console.log($scope.yearCount);
		    	    console.log("yoyIncrement");
		    	    
		    	    if($scope.yoyIncrement.length >= 1) {
				    	$scope.yoytableDisplay = true;
				    } 
		    	  
		    	   $scope.user = user;
		    	    $scope.getRateCardDetail(rcId,user);
		    	    $scope.getVerticalDetails(verticalId);
		    	};

		    	WebServiceFactory.getYoyIncrement(rcId).then(getYoyIncrement);
		    	
		    var getSummary = function(response) {
			//alert("get summary");
			console.log("get summary new details");
			console.log(response);
		    $scope.summaryDetail = response.data;
		 /*   $scope.avgVolumeDiscount = $sessionStorage.volumeDiscount;*/
		    for(var i=0;i< $scope.summaryDetail.length;i++) {
				
		    if( $scope.summaryDetail[i].location== "Offshore"){
		    	$sessionStorage.offshoreBC=$scope.summaryDetail[i].averageBC;
		    	
		    }
		    else if($scope.summaryDetail[i].location=="Consolidated"){
		    	$sessionStorage.overallBC=$scope.summaryDetail[i].averageBC;
		    	$sessionStorage.overallBR=$scope.summaryDetail[i].averageBR;
		    }
		    }
		    var FXRiskOffshoreabs=$sessionStorage.offshoreBC*$sessionStorage.fxriskoffshore/100;
		    var Contingencyriskabs=$sessionStorage.overallBC*$sessionStorage.contingentRiskPercentage/100;
		    var SLARiskabs=$sessionStorage.overallBR*$sessionStorage.slaRiskPercentage/100;
		    $sessionStorage.TotalRisk=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;
		    
		    
		    
		    
		    angular.forEach($scope.summaryDetail, function(value,key) {
		    	$scope.summaryDetail[key].volumeDiscount = $sessionStorage.volumeDiscount;
		    	$scope.summaryDetail[key].avgVolumeDiscount = $sessionStorage.volumeDiscount;
		    	$scope.summaryDetail[key].TotalRisk = $sessionStorage.TotalRisk ;
		    	$scope.summaryDetail[key].avgTotalRisk = $sessionStorage.TotalRisk ;
		    	 angular.forEach($scope.rateCardProcessingType, function(value,keytype) {
		    		if($scope.summaryDetail[key].countryId == $scope.rateCardProcessingType[keytype])
		    			{
		    				$scope.summaryDetail[key].rateCardProcessingType = 'M';
		    
		    			}
		    
		    	 });
		    });
		    
		    if($scope.currencyuti.length!=0)
			{
			if($scope.currencyuti.length == 1) {
				 angular.forEach($scope.currencyuti, function(value,key) {
					 if($scope.currencyuti[key].rateCardProcessingType == 0 && $scope.locationlength <=1)
					 {
						 $scope.tableDisplay = true;
						 $scope.multiCountryTable = false;
					 }
					 else{
						 $scope.tableDisplay = false;
						 $scope.multiCountryTable = true;
					 }
				 });
			 } 
			 else{
				 $scope.tableDisplay = false;
				 $scope.multiCountryTable = true;
			 }
			}
		else
			{
				WebServiceFactory.getSummaryCurrencyUti(rcId).then(getSummaryCurrencyUti);
			}
		    
		    if($scope.isItKpo == 1)
		    angular.forEach($scope.summaryDetail, function(value,key) {
		    	if($scope.summaryDetail[key].utilizationMix == 0 )
		    	{
		    		$scope.summaryDetail.splice(key,1);
		    	}
		    });
		    
		    console.log("summaryDetails");
		    if($sessionStorage.IsAtos!=1){
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
		    }
		   $scope.user = user;
		    $scope.getRateCardDetail(rcId,user);
		    $scope.getVerticalDetails(verticalId);
		};
		
	WebServiceFactory.getSummary(rcId).then(getSummary);
	
    var getFxRateComment = function(response) {
		//alert("get summary");
		console.log("get fx_rate data");
		console.log(response);
	    $scope.summaryDetailsmultifx = response.data;
	    angular.forEach($scope.summaryDetailsmultifx, function(value,key){ 
	    	            var fxValue  = value; 
	    	            var trimmedValue = fxValue.slice(0,-2); 	    	           
	    	$scope.trimmedValuefx.push(trimmedValue);
	    });
	    
	  /*  if(response.status == 204 && rcId!=0){
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
	    }*/
	   
	};
WebServiceFactory.getFxRateComment(rcId).then(getFxRateComment);

/*var getSummaryOld = function(response) {
	//alert("get summary");
	console.log("get summary old");
	console.log(response);
    $scope.summaryDetailOld = response.data;
    console.log("summaryDetailOld");
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
    $scope.getRateCardDetail(rcId,user);
    $scope.getVerticalDetails(verticalId);
};
WebServiceFactory.getSummaryOld(rcId).then(getSummaryOld);*/
		
	}	   
	
    var getCountryDetail = function(response) {
		$scope.countryData = response.data;
		console.log("$scope.countryData");
		console.log($scope.countryData);
	}
	WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
	$scope.getRateCardDetail = function(rcId,user){
		//alert("inside getRateCardDetails");
		$scope.getDelicgateUserAccess(user);
		for(var i=0;i<$scope.memberDetail.length;i++){
			if($scope.memberDetail[i].employeeLanId == user && $scope.memberDetail[i].roleType == 6){
				$scope.isValidGFTMember = true;
			}
		}
		var getRateCardDetail = function(response) {
			//alert("get RateCardDetails");
			console.log("get RateCardDetails");
			console.log(response);
		    $scope.rateCardDetails = response.data;
		    
		    $sessionStorage.verticalId = $scope.rateCardDetails[0].customerVerticalId.verticalId;
		    if($scope.rateCardDetails[0].isItKpo == 1)
		    {
		    	$scope.tableIT = true;
		    	}
		    else
		    {
		    	$scope.tableKPO = true;
		    	}
		    if(response.status == 200){
		    	/*
		    	if(user != null) {
		    		var  getDelicgateUserAccess = function(reponse) {
		    			$scope.deligateUser = response.data;
		    		};
		    		WebServiceFactory.getDelicgateUserAccess(user).then(getDelicgateUserAccess);
		    	}*/
			    if($scope.rateCardDetails[0].currentApproverId == user || ($scope.rateCardDetails[0].currentApproverRoleId!= undefined && $scope.rateCardDetails[0].currentApproverRoleId == $scope.approverRoleId) || $scope.isValidGFTMember == true){
			    //	alert("inside if");
			    	$scope.checkApprover = false;
			    	$scope.frmRateCardCreation.approverCommentModel = $scope.rateCardDetails[0].approverComments;
			    }else if($scope.proxyUserId != 0) {
			    	if($scope.rateCardDetails[0].currentApproverId == $scope.proxyUserId || ($scope.rateCardDetails[0].currentApproverRoleId!= undefined && $scope.rateCardDetails[0].currentApproverRoleId == $scope.approverRoleId) || $scope.isValidGFTMember == true){
			    		//	alert("inside if");
			    		$scope.checkApprover = false;
			    		$scope.frmRateCardCreation.approverCommentModel = $scope.rateCardDetails[0].approverComments;
			    	} else if($scope.GFTApprover == true) {
				    	$scope.checkApprover = false;
				    	$scope.frmRateCardCreation.approverCommentModel = $scope.rateCardDetails[0].approverComments;
				    }
				    else {
				    	$scope.frmRateCardCreation.approverCommentModel = $scope.rateCardDetails[0].approverComments;
				    }
			    } 
			    else if($scope.GFTApprover == true) {
			    	$scope.checkApprover = false;
			    	$scope.frmRateCardCreation.approverCommentModel = $scope.rateCardDetails[0].approverComments;
			    }
			    else
			      	$scope.frmRateCardCreation.approverCommentModel = $scope.rateCardDetails[0].approverComments;
			  
		    }
		    
		};
		WebServiceFactory.getRateCardDetail(rcId).then(getRateCardDetail);
	}
	
	$scope.getDelicgateUserAccess = function(user) {
		var getDelicgateUserAccess = function (response) {
			if(response.data != undefined) {
				$scope.deligateUser = response.data;
			}
		}
		WebServiceFactory.getDelicgateUserAccess(user).then(getDelicgateUserAccess);
	}
	
	
$scope.exportToExcelSummary = function(tableId) {
		
		var exportHref=WebServiceFactory.exportToExcelSummary(tableId,'RateCard_Details_Summary');
	  
	};
	
	//get data from mst_rp_vertical table
	$scope.getVerticalDetails = function(verticalId){
		var getVerticalDetails = function(response) {
		//	alert("inside getVerticalDetails");
			console.log("getVerticalDetails");
			console.log(response);
		    $scope.verticalDetails = response.data;
		};
		WebServiceFactory.getVerticalDetails(verticalId).then(getVerticalDetails);
	}
	
	var getLeadershipDetails = function(response) {
		//alert("inside getLeadershipDetails");
		console.log("getLeadershipDetails");
		console.log(response);
	    $scope.leadershipDetails = response.data;
	};
	WebServiceFactory.getLeadershipDetails().then(getLeadershipDetails);
	
	$scope.getApprovalComments=function(user){
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
		$scope.commentbox= true;
		
		if($scope.frmRateCardCreation.approvalStatus == 3 && userType == 'GFT') {
			$scope.rainbowApprovalPanel = false;
		} else {
			$scope.rainbowApprovalPanel = true;
		}
	};
	
	$scope.saveApprovalData = function(user,selectionType,cmntTxt){
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
		console.log(selectionType);
		console.log(cmntTxt);

		var getVerticalDeta = function(resp){
			console.log("------------------------ all vertical data here --------------------")
			console.log(resp.data)
			$scope.allVerticalArray = resp.data;
		}
		WebServiceFactory.getVertical().then(getVerticalDeta)
		
		var vertical_Id = $sessionStorage.verticalId
		
		var offPer=$scope.rateCardDetails[0].expectedOffshoreResourcePercentage;
		var onsitePer=$scope.rateCardDetails[0].expectedOnsiteResourcePercentage;
		var gmPer=$scope.rateCardDetails[0].calculatedGMPercentage;
		var RPgmPer=$scope.rateCardDetails[0].calculatedRPGMPercentagePostDiscount;
		
		//alert("inside save approval data");
		//save approval data in audit table
		if(userType == 'GFT') {
			if($scope.frmRateCardCreation.rainbowApprovalRequired == undefined) {
				$scope.frmRateCardCreation.rainbowApprovalLevel = $scope.rateCardDetails[0].approvalFlagLevel;
				$sessionStorage.IsRainbowFlag = $scope.rateCardDetails[0].rainbowApprovalFlag;
			} else {
				$sessionStorage.raibowApprovalLevel = $scope.frmRateCardCreation.rainbowApprovalLevel;
			}
		} else {
			$sessionStorage.raibowApprovalLevel= $scope.rateCardDetails[0].rainbowApprovalFlag;
		}
		if(selectionType == 3)
		{
			BootstrapDialog.show({
	           title: 'Rate Card Creation - Summary',
	           type : BootstrapDialog.TYPE_PRIMARY,
	           message : '<b>You are approving the rate card with below details:</b> -<br /></br>Onsite/Offshore Mix  -  '+onsitePer+'% /'+ offPer+'%  <br />PM%  -  '+gmPer +'%<br />Right Price PM%  -  '+RPgmPer+'%<br /></br>Do you want to Approve?',
	           closable: true,
	           buttons: [{
	               label: 'Yes',
	               cssClass: 'btn-primary',
	               action: function(dialogRef) {
	            	   if($sessionStorage.rainbowLevelFlag==1 && $sessionStorage.raibowApprovalLevel !=undefined){
	            		   var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
	            		   var comment = angular.element(approverId).val();
	            		   if ($.trim(comment) == '') {
	            			   dialogRef.close();
	            			   BootstrapDialog.show({
	            				   title : 'Rate Card Creation - Summary',
	            				   type : BootstrapDialog.TYPE_DANGER,
	            				   message : "Please enter comments.",
	            				   closable : false,
	            				   buttons : [{
	            					   label : 'OK',
	            					   action : function(dialogRef) {
	            						   dialogRef.close();
	            					   }
	            				   }]
	            			   });
	            			   
	            			   
	            			   return false;
	            			   
	            		   }
	            		   else
	            		   {
	            			   $scope.frmRateCardCreation.currentApproverCommentModel = "["+user+"]: " + comment;
	            			   $scope.$apply();
	            			   if($scope.frmRateCardCreation.approverCommentModel){
	            				   var commnetBoxTxt = "["+user+"]: " + comment;
	            				   $scope.frmRateCardCreation.approverCommentModel += '\n'+ commnetBoxTxt;
	            				   $scope.$apply();
	            			   }
	            			   else{
	            				   $scope.frmRateCardCreation.approverCommentModel = "["+user+"]: " + comment;
	            				   $scope.$apply();
	            			   }
	            			   
	            			   if($scope.frmRateCardCreation.approverCommentModel.length > 700)
	            			   {
	            				   var commentLen = $scope.frmRateCardCreation.approverCommentModel.length;
	            				   var SkipChars = commentLen - 700;					
	            				   $scope.frmRateCardCreation.approverCommentModel = $scope.frmRateCardCreation.approverCommentModel.substring(SkipChars, commentLen);
	            				   $scope.$apply();
	            			   }
	            		   }
	            		   
	            		   var markers = {
	            				   "rcOrDealID":$localStorage.rcId,//need to get while on page load
	            				   "approverId":user,
	            				   "approvalStatus":$scope.frmRateCardCreation.approvalStatus,
	            				   "approvalComments":$scope.frmRateCardCreation.currentApproverCommentModel,
	            				   "approvalDate": $scope.currentDate,
	            				   "isRateCard":1
	            		   };
	            		   var saveApprovalData = function(response) {
	            			   if(response.data == 200){
	            				   $scope.updatedAuditDetails=1;
	            			   }
	            		   }
	            		   WebServiceFactory.saveApprovalData(markers).then(saveApprovalData);
	            		   
	            		   
	            		   //chk approver role id is null or not
	            		   if($scope.rateCardDetails[0].level1ApproverRoleId!=null){
	            			   $scope.setRoleId = 1;
	            		   }
	            		   if($scope.rateCardDetails[0].level2ApproverRoleId!=null){
	            			   $scope.setRoleId = 2;
	            		   }
	            		   if($scope.rateCardDetails[0].level3ApproverRoleId!=null){
	            			   $scope.setRoleId = 3;
	            		   }
	            		   if($scope.rateCardDetails[0].level4ApproverRoleId!=null){
	            			   $scope.setRoleId = 4;
	            		   }
	            		   if($scope.rateCardDetails[0].level5ApproverRoleId!=null){
	            			   $scope.setRoleId = 5;
	            		   }
	            		   if($scope.rateCardDetails[0].level6ApproverRoleId!=null){
	            			   $scope.setRoleId = 6;
	            		   }
	            		   if($scope.rateCardDetails[0].level7ApproverRoleId!=null){
	            			   $scope.setRoleId = 7;
	            		   }
	            		   if($scope.rateCardDetails[0].level8ApproverRoleId!=null){
	            			   $scope.setRoleId = 8;
	            		   }
	            		   if($scope.rateCardDetails[0].level9ApproverRoleId!=null){
	            			   $scope.setRoleId = 9;
	            		   }
	            		   
	            		   
	            		   if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 2){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level2ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level2ApproverId.description;
	            			   }
	            			   
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 3){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level3ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level3ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 4){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level4ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level4ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 5){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level5ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level5ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 6){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level6ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level6ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 7){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level7ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level7ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 8){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level8ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level8ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 9){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level9ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level9ApproverId.description;
	            			   }
	            		   }
	            		   
	            		   
	            		   
	            		   //get lan id to update approver id in rate card details
	            		   if($scope.getLanid == 1){
	            			   $scope.lanId = "lanid1";
	            		   }
	            		   else if($scope.getLanid == 2){
	            			   $scope.lanId = "lanid2";
	            		   }
	            		   else if($scope.getLanid == 3){
	            			   $scope.lanId = "duh";
	            		   }
	            		   else if($scope.getLanid == 4){
	            			   $scope.lanId = $scope.verticalDetails[0].deliveryHeadId;
	            			   $scope.roleId = 4;
	            		   }
	            		   else if($scope.getLanid == 5){
	            			   $scope.lanId = $scope.verticalDetails[0].RiskManagersPersonId;
	            			   $scope.roleId = 5;
	            		   }
	            		   else if($scope.getLanid == 6){
	            			   $scope.lanId = "GFT";
	            			   $scope.roleId = 6;
	            		   }
	            		   else if($scope.getLanid == 7){
	            			   $scope.lanId = $scope.leadershipDetails[1].leaderLanID;
	            			   $scope.roleId = 7;
	            		   }
	            		   else if($scope.getLanid == 8){

	            			   
	           
	            				 for(var i=0;i<$scope.allVerticalArray.length;i++)
	            				 {
	            					   					 
	            					 if( vertical_Id == $scope.allVerticalArray[i].verticalId){
	            						 $scope.lanId = $scope.allVerticalArray[i].buHeadId;
	            					 }
	            				 }

	            			   
	            			  /* $scope.lanId = $scope.verticalDetails[0].buHeadId;*/
	            			   $scope.roleId = 8;
	            		   
	            		   }
	            		   else if($scope.getLanid == 9){
	            			   $scope.lanId = $scope.leadershipDetails[1].leaderLanID;
	            			   $scope.roleId = 9;
	            		   }
	            		   else if($scope.getLanid == 10 && $scope.leadershipDetails[0].leaderId){
	            			   $scope.lanId = $scope.leadershipDetails[0].leaderLanID;
	            			   $scope.roleId = 10;
	            		   }
	            		   else if($scope.getLanid == 11 && $scope.leadershipDetails[0].leaderId){
	            			   $scope.lanId = $scope.leadershipDetails[2].leaderLanID;
	            			   $scope.roleId = 11;
	            		   }
	            		   else if($scope.getLanid == 12 && $scope.leadershipDetails[0].leaderId){
	            			   $scope.lanId = $scope.leadershipDetails[3].leaderLanID;
	            			   $scope.roleId = 12;
	            		   }
	            		   else if($scope.getLanid == 13 && $scope.leadershipDetails[0].leaderId){
								$scope.lanId = $scope.leadershipDetails[4].leaderLanID;
								$scope.roleId = 13;
								$scope.statusIndicator = 'BUH'
							}
	            		   
	            		   //if($scope.setRoleId == $scope.getLanid){
	            		   if($scope.getLanid == null){
	            			   var marker = {
	            					   "rcId":rcId,
	            					   "currentApprovalStatus":$scope.frmRateCardCreation.approvalStatus,
	            					   "currentApproverId":null,
	            					   "approverComments":$scope.frmRateCardCreation.approverCommentModel,
	            					   "currentApprovalLevel":$scope.rateCardDetails[0].currentApprovalLevel,
	            					   "currentApproverRoleId":$scope.roleId,
	            					   "isManualRc": $scope.isManualCheck,
	            					   "createdBy": $scope.createdBy,
	              					   "approvalFlagLevel" :$scope.frmRateCardCreation.rainbowApprovalLevel,
	              					   "rainbowApprovalFlag" : $sessionStorage.IsRainbowFlag,
	              					   "userType" : userType
	            			   };
	            		   }
	            		   
	            		   
	            		   else if($scope.frmRateCardCreation.approvalStatus == 3){
	            			   
	            			   
	            			   var marker = {
	            					   "rcId":rcId,
	            					   "currentApprovalStatus":2,
	            					   "currentApproverId":$scope.lanId,
	            					   "approverComments":$scope.frmRateCardCreation.approverCommentModel,
	            					   "currentApprovalLevel":$scope.rateCardDetails[0].currentApprovalLevel+1,
	            					   "currentApproverRoleId":$scope.roleId,
	            					   "isManualRc": $scope.isManualCheck,
	            					   "createdBy": $scope.createdBy,
	              					   "approvalFlagLevel" :$scope.frmRateCardCreation.rainbowApprovalLevel,
	              					  "rainbowApprovalFlag" : $sessionStorage.IsRainbowFlag,
	            					   "statusIndicator" : $scope.statusIndicator,
	            					   "userType" : userType
	            			   };
	            			   
	            		   }
	            		   
	            		   else if($scope.frmRateCardCreation.approvalStatus == 4){
	            			   var marker = {
	            					   "rcId":rcId,
	            					   "currentApprovalStatus":$scope.frmRateCardCreation.approvalStatus,
	            					   "currentApproverId":"",
	            					   "approverComments":$scope.frmRateCardCreation.approverCommentModel,
	            					   "currentApprovalLevel":"",
	            					   "currentApproverRoleId":"",
	            					   "isManualRc": $scope.isManualCheck,
	            					   "createdBy": $scope.createdBy,
	            					   "userType" : userType
	            			   };
	            		   }
	            		   
	            		   
	            		   var updateApprovalDataForRateCard = function(response) {
	            			   $scope.updatedAuditDetails=1;
	            			   if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.frmRateCardCreation.approvalStatus == 4){
	            				   BootstrapDialog.show({
	            					   title : 'Rate Card Creation - Summary',
	            					   type : BootstrapDialog.TYPE_DANGER,
	            					   message : 'Request Rejected SucessFully',
	            					   closable : false,
	            					   buttons : [{
	            						   label : 'OK',
	            						   action : function(dialogRef) {
	            							   dialogRef.close();
	            							   window.location = "RateCardCreationSummary";
	            						   }
	            					   }]
	            				   });
	            				   $scope.approvalcomment = true;
	            			   }
	            			   else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.frmRateCardCreation.approvalStatus == 3){
	            				   BootstrapDialog.show({
	            					   title : 'Rate Card Creation - Summary',
	            					   type : BootstrapDialog.TYPE_PRIMARY,
	            					   message : 'Request Approved SucessFully',
	            					   closable : false,
	            					   buttons : [{
	            						   label : 'OK',
	            						   action : function(dialogRef) {
	            							   dialogRef.close();
	            							   window.location = "RateCardCreationSummary";
	            						   }
	            					   }]
	            				   });
	            				   $scope.approvalcomment = true;
	            			   }
	            			   else{
	            				   BootstrapDialog.show({
	            					   title : 'Rate Card Creation - Summary',
	            					   type : BootstrapDialog.TYPE_DANGER,
	            					   message : "Currently we are facing technical issue. Please try again later.",
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
	            		   WebServiceFactory.updateApprovalDataForRateCard(marker).then(updateApprovalDataForRateCard);
	            		   dialogRef.close();
	            		   $scope.isDisable=true;
	            	   } 
	            	   else if($sessionStorage.rainbowLevelFlag==1 && $sessionStorage.raibowApprovalLevel ==undefined)
	            		   {
	            		   dialogRef.close();
	            		   BootstrapDialog.show({
        					   title : 'Rate Card Creation - Summary',
        					   type : BootstrapDialog.TYPE_DANGER,
        					   message : "Please select Rainbow Approval Level.",
        					   closable : false,
        					   buttons : [{
        						   label : 'OK',
        						   action : function(dialogRef) {
        							   dialogRef.close();
        						   }
        					   }]
        				   });
	            		   }
	            	   else{

	            		   var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
	            		   var comment = angular.element(approverId).val();
	            		   if ($.trim(comment) == '') {
	            			   dialogRef.close();
	            			   BootstrapDialog.show({
	            				   title : 'Rate Card Creation - Summary',
	            				   type : BootstrapDialog.TYPE_DANGER,
	            				   message : "Please enter comments.",
	            				   closable : false,
	            				   buttons : [{
	            					   label : 'OK',
	            					   action : function(dialogRef) {
	            						   dialogRef.close();
	            					   }
	            				   }]
	            			   });
	            			   
	            			   
	            			   return false;
	            			   
	            		   }
	            		   else
	            		   {
	            			   $scope.frmRateCardCreation.currentApproverCommentModel = "["+user+"]: " + comment;
	            			   $scope.$apply();
	            			   if($scope.frmRateCardCreation.approverCommentModel){
	            				   var commnetBoxTxt = "["+user+"]: " + comment;
	            				   $scope.frmRateCardCreation.approverCommentModel += '\n'+ commnetBoxTxt;
	            				   $scope.$apply();
	            			   }
	            			   else{
	            				   $scope.frmRateCardCreation.approverCommentModel = "["+user+"]: " + comment;
	            				   $scope.$apply();
	            			   }
	            			   
	            			   if($scope.frmRateCardCreation.approverCommentModel.length > 700)
	            			   {
	            				   var commentLen = $scope.frmRateCardCreation.approverCommentModel.length;
	            				   var SkipChars = commentLen - 700;					
	            				   $scope.frmRateCardCreation.approverCommentModel = $scope.frmRateCardCreation.approverCommentModel.substring(SkipChars, commentLen);
	            				   $scope.$apply();
	            			   }
	            		   }
	            		   
	            		   var markers = {
	            				   "rcOrDealID":$localStorage.rcId,//need to get while on page load
	            				   "approverId":user,
	            				   "approvalStatus":$scope.frmRateCardCreation.approvalStatus,
	            				   "approvalComments":$scope.frmRateCardCreation.currentApproverCommentModel,
	            				   "approvalDate": $scope.currentDate,
	            				   "isRateCard":1
	            		   };
	            		   var saveApprovalData = function(response) {
	            			   if(response.data == 200){
	            				   $scope.updatedAuditDetails=1;
	            			   }
	            		   }
	            		   WebServiceFactory.saveApprovalData(markers).then(saveApprovalData);
	            		   
	            		   
	            		   //chk approver role id is null or not
	            		   if($scope.rateCardDetails[0].level1ApproverRoleId!=null){
	            			   $scope.setRoleId = 1;
	            		   }
	            		   if($scope.rateCardDetails[0].level2ApproverRoleId!=null){
	            			   $scope.setRoleId = 2;
	            		   }
	            		   if($scope.rateCardDetails[0].level3ApproverRoleId!=null){
	            			   $scope.setRoleId = 3;
	            		   }
	            		   if($scope.rateCardDetails[0].level4ApproverRoleId!=null){
	            			   $scope.setRoleId = 4;
	            		   }
	            		   if($scope.rateCardDetails[0].level5ApproverRoleId!=null){
	            			   $scope.setRoleId = 5;
	            		   }
	            		   if($scope.rateCardDetails[0].level6ApproverRoleId!=null){
	            			   $scope.setRoleId = 6;
	            		   }
	            		   if($scope.rateCardDetails[0].level7ApproverRoleId!=null){
	            			   $scope.setRoleId = 7;
	            		   }
	            		   if($scope.rateCardDetails[0].level8ApproverRoleId!=null){
	            			   $scope.setRoleId = 8;
	            		   }
	            		   if($scope.rateCardDetails[0].level9ApproverRoleId!=null){
	            			   $scope.setRoleId = 9;
	            		   }
	            		   
	            		   
	            		   if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 2){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level2ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level2ApproverId.description;
	            			   }
	            			   
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 3){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level3ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level3ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 4){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level4ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level4ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 5){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level5ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level5ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 6){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level6ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level6ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 7){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level7ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level7ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 8){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level8ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level8ApproverId.description;
	            			   }
	            		   }
	            		   else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 9){
	            			   $scope.getLanid = $scope.rateCardDetails[0].level9ApproverRoleId;
	            			   if($scope.getLanid!=null){
	            				   $scope.statusIndicator = $scope.rateCardDetails[0].level9ApproverId.description;
	            			   }
	            		   }
	            		   
	            		   
	            		   
	            		   //get lan id to update approver id in rate card details
	            		   if($scope.getLanid == 1){
	            			   $scope.lanId = "lanid1";
	            		   }
	            		   else if($scope.getLanid == 2){
	            			   $scope.lanId = "lanid2";
	            		   }
	            		   else if($scope.getLanid == 3){
	            			   $scope.lanId = "duh";
	            		   }
	            		   else if($scope.getLanid == 4){
	            			   $scope.lanId = $scope.verticalDetails[0].deliveryHeadId;
	            			   $scope.roleId = 4;
	            		   }
	            		   else if($scope.getLanid == 5){
	            			   $scope.lanId = $scope.verticalDetails[0].RiskManagersPersonId;
	            			   $scope.roleId = 5;
	            		   }
	            		   else if($scope.getLanid == 6){
	            			   $scope.lanId = "GFT";
	            			   $scope.roleId = 6;
	            		   }
	            		   else if($scope.getLanid == 7){
	            			   $scope.lanId = $scope.leadershipDetails[1].leaderLanID;
	            			   $scope.roleId = 7;
	            		   }
	            		   else if($scope.getLanid == 8){

	            			   
	            	           
	            				 for(var i=0;i<$scope.allVerticalArray.length;i++)
	            				 {
	            					   					 
	            					 if( vertical_Id == $scope.allVerticalArray[i].verticalId){
	            						 $scope.lanId = $scope.allVerticalArray[i].buHeadId;
	            					 }
	            				 }

	            			   
	            			  /* $scope.lanId = $scope.verticalDetails[0].buHeadId;*/
	            			   $scope.roleId = 8;
	            		   
	            		   }
	            		   else if($scope.getLanid == 9){
	            			   $scope.lanId = $scope.leadershipDetails[1].leaderLanID;
	            			   $scope.roleId = 9;
	            		   }
	            		   else if($scope.getLanid == 10 && $scope.leadershipDetails[0].leaderId){
	            			   $scope.lanId = $scope.leadershipDetails[0].leaderLanID;
	            			   $scope.roleId = 10;
	            		   }
	            		   else if($scope.getLanid == 11 && $scope.leadershipDetails[0].leaderId){
	            			   $scope.lanId = $scope.leadershipDetails[2].leaderLanID;
	            			   $scope.roleId = 11;
	            		   }
	            		   else if($scope.getLanid == 12 && $scope.leadershipDetails[0].leaderId){
	            			   $scope.lanId = $scope.leadershipDetails[3].leaderLanID;
	            			   $scope.roleId = 12;
	            		   } 
	            		   else if($scope.getLanid == 13 && $scope.leadershipDetails[0].leaderId){
								$scope.lanId = $scope.leadershipDetails[4].leaderLanID;
								$scope.roleId = 13;
								$scope.statusIndicator = 'BUH'
							}
	            		   
	            		   //if($scope.setRoleId == $scope.getLanid){
	            		   if($scope.getLanid == null){
	            			   var marker = {
	            					   "rcId":rcId,
	            					   "currentApprovalStatus":$scope.frmRateCardCreation.approvalStatus,
	            					   "currentApproverId":null,
	            					   "approverComments":$scope.frmRateCardCreation.approverCommentModel,
	            					   "currentApprovalLevel":$scope.rateCardDetails[0].currentApprovalLevel,
	            					   "currentApproverRoleId":$scope.roleId,
	            					   "isManualRc": $scope.isManualCheck,
	            					   "createdBy": $scope.createdBy,
	            					   "rainbowApprovalFlag" : $sessionStorage.IsRainbowFlag,
	              					   "approvalFlagLevel" :$scope.frmRateCardCreation.rainbowApprovalLevel,
	              					   "userType" : userType
	            			   };
	            		   }
	            		   
	            		   
	            		   else if($scope.frmRateCardCreation.approvalStatus == 3){
	            			   
	            			   
	            			   var marker = {
	            					   "rcId":rcId,
	            					   "currentApprovalStatus":2,
	            					   "currentApproverId":$scope.lanId,
	            					   "approverComments":$scope.frmRateCardCreation.approverCommentModel,
	            					   "currentApprovalLevel":$scope.rateCardDetails[0].currentApprovalLevel+1,
	            					   "currentApproverRoleId":$scope.roleId,
	            					   "isManualRc": $scope.isManualCheck,
	            					   "createdBy": $scope.createdBy,
	            					   "rainbowApprovalFlag" : $sessionStorage.IsRainbowFlag,
	              					   "approvalFlagLevel" :$scope.frmRateCardCreation.rainbowApprovalLevel,
	            					   "statusIndicator" : $scope.statusIndicator,
	            					   "userType" : userType
	            			   };
	            			   
	            		   }
	            		   
	            		   else if($scope.frmRateCardCreation.approvalStatus == 4){
	            			   var marker = {
	            					   "rcId":rcId,
	            					   "currentApprovalStatus":$scope.frmRateCardCreation.approvalStatus,
	            					   "currentApproverId":"",
	            					   "approverComments":$scope.frmRateCardCreation.approverCommentModel,
	            					   "currentApprovalLevel":"",
	            					   "currentApproverRoleId":"",
	            					   "isManualRc": $scope.isManualCheck,
	            					   "createdBy": $scope.createdBy,
	            					   "userType" : userType
	            			   };
	            		   }
	            		   
	            		   
	            		   var updateApprovalDataForRateCard = function(response) {
	            			   $scope.updatedAuditDetails=1;
	            			   if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.frmRateCardCreation.approvalStatus == 4){
	            				   BootstrapDialog.show({
	            					   title : 'Rate Card Creation - Summary',
	            					   type : BootstrapDialog.TYPE_DANGER,
	            					   message : 'Request Rejected SucessFully',
	            					   closable : false,
	            					   buttons : [{
	            						   label : 'OK',
	            						   action : function(dialogRef) {
	            							   dialogRef.close();
	            							   window.location = "RateCardCreationSummary";
	            						   }
	            					   }]
	            				   });
	            				   $scope.approvalcomment = true;
	            			   }
	            			   else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.frmRateCardCreation.approvalStatus == 3){
	            				   BootstrapDialog.show({
	            					   title : 'Rate Card Creation - Summary',
	            					   type : BootstrapDialog.TYPE_PRIMARY,
	            					   message : 'Request Approved SucessFully',
	            					   closable : false,
	            					   buttons : [{
	            						   label : 'OK',
	            						   action : function(dialogRef) {
	            							   dialogRef.close();
	            							   window.location = "RateCardCreationSummary";
	            						   }
	            					   }]
	            				   });
	            				   $scope.approvalcomment = true;
	            			   }
	            			   else{
	            				   
	            				   BootstrapDialog.show({
	            					   title : 'Rate Card Creation - Summary',
	            					   type : BootstrapDialog.TYPE_DANGER,
	            					   message : "Currently we are facing technical issue. Please try again later.",
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
	            		   WebServiceFactory.updateApprovalDataForRateCard(marker).then(updateApprovalDataForRateCard);
	            		   dialogRef.close();
	            		   $scope.isDisable=true;
	            	   
	            	   }
	            	   }
	           }, {
		            label: 'No',
		            cssClass: 'btn-primary',
		            action: function(dialog) {
		                dialog.close();
		            }
		            }]
			});
	} else if(selectionType == 4) {

		BootstrapDialog.show({
           title: 'Rate Card Creation - Summary',
           type : BootstrapDialog.TYPE_PRIMARY,
           message : 'Do you want to Reject?',
           closable: true,
           buttons: [{
               label: 'Yes',
               cssClass: 'btn-primary',
               action: function(dialogRef) {
            	   var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
                   var comment = angular.element(approverId).val();
            	   
					if ($.trim(comment) == '') {
						dialogRef.close();
			        	BootstrapDialog.show({
		    	        	title : 'Rate Card Creation - Summary',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : "Please enter comments.",
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        		}
		    	        	}]
		    	        });
			        	
						return false;
					}
					else
					{
						$scope.frmRateCardCreation.currentApproverCommentModel = "["+user+"]: " + comment;
						$scope.$apply();
						if($scope.frmRateCardCreation.approverCommentModel){
							var commnetBoxTxt = "["+user+"]: " + comment;
							$scope.frmRateCardCreation.approverCommentModel += '\n'+ commnetBoxTxt;
							$scope.$apply();
						}
						else{
							$scope.frmRateCardCreation.approverCommentModel = "["+user+"]: " + comment;
							$scope.$apply();
						}
						
						if($scope.frmRateCardCreation.approverCommentModel.length > 700)
						{
							var commentLen = $scope.frmRateCardCreation.approverCommentModel.length;
							var SkipChars = commentLen - 700;					
							$scope.frmRateCardCreation.approverCommentModel = $scope.frmRateCardCreation.approverCommentModel.substring(SkipChars, commentLen);
							$scope.$apply();
						}
					}
					
					var markers = {
							"rcOrDealID":$localStorage.rcId,//need to get while on page load
							"approverId":user,
							"approvalStatus":$scope.frmRateCardCreation.approvalStatus,
							"approvalComments":$scope.frmRateCardCreation.currentApproverCommentModel,
							"approvalDate": $scope.currentDate,
							"isRateCard":1
					};
					var saveApprovalData = function(response) {
						if(response.data == 200){
							$scope.updatedAuditDetails=1;
						}
					}
					WebServiceFactory.saveApprovalData(markers).then(saveApprovalData);
					
					
					//chk approver role id is null or not
						if($scope.rateCardDetails[0].level1ApproverRoleId!=null){
							$scope.setRoleId = 1;
						}
						if($scope.rateCardDetails[0].level2ApproverRoleId!=null){
							$scope.setRoleId = 2;
						}
						if($scope.rateCardDetails[0].level3ApproverRoleId!=null){
							$scope.setRoleId = 3;
						}
						if($scope.rateCardDetails[0].level4ApproverRoleId!=null){
							$scope.setRoleId = 4;
						}
						if($scope.rateCardDetails[0].level5ApproverRoleId!=null){
							$scope.setRoleId = 5;
						}
						if($scope.rateCardDetails[0].level6ApproverRoleId!=null){
							$scope.setRoleId = 6;
						}
						if($scope.rateCardDetails[0].level7ApproverRoleId!=null){
							$scope.setRoleId = 7;
						}
						if($scope.rateCardDetails[0].level8ApproverRoleId!=null){
							$scope.setRoleId = 8;
						}
						if($scope.rateCardDetails[0].level9ApproverRoleId!=null){
							$scope.setRoleId = 9;
						}
					
					//$scope.maxRoleId = $scope.setRoleId;
					
				//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
					
					if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 2){
						$scope.getLanid = $scope.rateCardDetails[0].level2ApproverRoleId;
					}
					else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 3){
						$scope.getLanid = $scope.rateCardDetails[0].level3ApproverRoleId;
					}
					else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 4){
						$scope.getLanid = $scope.rateCardDetails[0].level4ApproverRoleId;
					}
					else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 5){
						$scope.getLanid = $scope.rateCardDetails[0].level5ApproverRoleId;
					}
					else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 6){
						$scope.getLanid = $scope.rateCardDetails[0].level6ApproverRoleId;
					}
					else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 7){
						$scope.getLanid = $scope.rateCardDetails[0].level7ApproverRoleId;
					}
					else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 8){
						$scope.getLanid = $scope.rateCardDetails[0].level8ApproverRoleId;
					}
					else if(($scope.rateCardDetails[0].currentApprovalLevel+1) == 9){
						$scope.getLanid = $scope.rateCardDetails[0].level9ApproverRoleId;
					}
					
					
					
					//get lan id to update approver id in rate card details
						if($scope.getLanid == 1){
							$scope.lanId = "lanid1";
						}
						else if($scope.getLanid == 2){
							$scope.lanId = "lanid2";
						}
						else if($scope.getLanid == 3){
							$scope.lanId = "duh";
						}
						else if($scope.getLanid == 4){
							$scope.lanId = $scope.verticalDetails[0].deliveryHeadId;
							$scope.roleId = 4;
						}
						else if($scope.getLanid == 5){
							$scope.lanId = $scope.verticalDetails[0].RiskManagersPersonId;
							$scope.roleId = 5;
						}
						else if($scope.getLanid == 6){
							$scope.lanId = "GFT";
							$scope.roleId = 6;
						}
						else if($scope.getLanid == 7){
							$scope.lanId = $scope.leadershipDetails[1].leaderLanID;
							$scope.roleId = 7;
						}
						else if($scope.getLanid == 8){

	            			   
					           
           				 for(var i=0;i<$scope.allVerticalArray.length;i++)
           				 {
           					   					 
           					 if( vertical_Id == $scope.allVerticalArray[i].verticalId){
           						 $scope.lanId = $scope.allVerticalArray[i].buHeadId;
           					 }
           				 }

           			   
           			  /* $scope.lanId = $scope.verticalDetails[0].buHeadId;*/
           			   $scope.roleId = 8;
           		   
           		   }
						else if($scope.getLanid == 9){
							$scope.lanId = $scope.leadershipDetails[1].leaderLanID;
							$scope.roleId = 9;
						}
						else if($scope.getLanid == 10 && $scope.leadershipDetails[0].leaderId){
							$scope.lanId = $scope.leadershipDetails[0].leaderLanID;
							$scope.roleId = 10;
						}
						else if($scope.getLanid == 11 && $scope.leadershipDetails[0].leaderId){
							$scope.lanId = $scope.leadershipDetails[2].leaderLanID;
							$scope.roleId = 11;
						}
						else if($scope.getLanid == 12 && $scope.leadershipDetails[0].leaderId){
							$scope.lanId = $scope.leadershipDetails[3].leaderLanID;
							$scope.roleId = 12;
						}
						else if($scope.getLanid == 13 && $scope.leadershipDetails[0].leaderId){
							$scope.lanId = $scope.leadershipDetails[4].leaderLanID;
							$scope.roleId = 13;
							$scope.statusIndicator = 'BUH'
						}
						
						if($scope.frmRateCardCreation.approvalStatus == 4){
							var marker = {
									"rcId":rcId,
									"currentApprovalStatus":$scope.frmRateCardCreation.approvalStatus,
									"currentApproverId":"",
									"approverComments":$scope.frmRateCardCreation.approverCommentModel,
									"currentApprovalLevel":"",
									"currentApproverRoleId":"",
									"isManualRc": $scope.isManualCheck,
									"createdBy": $scope.createdBy,
									"userType" : userType
							};
						}
						
						
						var updateApprovalDataForRateCard = function(response) {
							$scope.updatedAuditDetails=1;
							if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.frmRateCardCreation.approvalStatus == 4){
				    	        BootstrapDialog.show({
				    	        	title : 'Rate Card Creation - Summary',
				    	        	type : BootstrapDialog.TYPE_DANGER,
				    	        	message : 'Request Rejected SucessFully',
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
				    	        			window.location = "RateCardCreationSummary";
				    	        		}
				    	        	}]
				    	        });
				    	        $scope.approvalcomment = true;
					        }
							else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.frmRateCardCreation.approvalStatus == 3){
				    	        BootstrapDialog.show({
				    	        	title : 'Rate Card Creation - Summary',
				    	        	type : BootstrapDialog.TYPE_PRIMARY,
				    	        	message : 'Request Approved SucessFully',
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
				    	        			window.location = "RateCardCreationSummary";
				    	        		}
				    	        	}]
				    	        });
				    	        $scope.approvalcomment = true;
					        }
					        else{
					        	BootstrapDialog.show({
				    	        	title : 'Rate Card Creation - Summary',
				    	        	type : BootstrapDialog.TYPE_DANGER,
				    	        	message : "Currently we are facing technical issue. Please try again later.",
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
						WebServiceFactory.updateApprovalDataForRateCard(marker).then(updateApprovalDataForRateCard);
						dialogRef.close();
						$scope.isDisable=true;
				} 
           }, {
	            label: 'No',
	            cssClass: 'btn-primary',
	            action: function(dialog) {
	                dialog.close();
	            }
	            }]
		});
	}
		else
			{
			BootstrapDialog.show({
	        	title : 'Rate Card Creation - Summary',
	        	type : BootstrapDialog.TYPE_DANGER,
	        	message : "Please select Approver Action.",
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
	
	var getCountryDetail = function(response) {
		console.log("get country data");
  		console.log(response);
  		$scope.countryData = response.data;
	};
	WebServiceFactory.getCountryDetail().then(getCountryDetail);
	
	$scope.checkDropdown = function(){
		for(var i=0;i<$scope.countryData.length;i++){
    		if($scope.countryData[i].countryId == $scope.frmRateCardCreation.ddlCountryModel){
    			$scope.currencyName = $scope.countryData[i].currencyCode;
    		}
    	}
		if($scope.frmRateCardCreation.ddlCountryModel != null){
			 $scope.yearWiseSummaryOnPageLoad = false;
		}
		else{
			$scope.yearWiseSummaryOnPageLoad = true;
			$scope.currencyName = $scope.rateCardInfo[0].currency;
		}
	}
	
	$scope.checkApprovalLevel = function(selectedVal) {
		if(selectedVal == 1) {
			$scope.isApprovalType = true;
			$sessionStorage.IsRainbowFlag=1;
			$sessionStorage.rainbowLevelFlag =1;
		} else if(selectedVal == 2){
			$scope.frmRateCardCreation.rainbowApprovalLevel =null;
			$scope.isApprovalType = false;
			$sessionStorage.IsRainbowFlag=2;
			$sessionStorage.rainbowLevelFlag =2;
			
		}
	}
	
	//manglam updated
	var markers = {	
			 "versionId" : $localStorage.rcId							
			 };					
			 var getPLAttachementData = function(response)  {						
			  $scope.AttachementData=response.data;        						
			  };						
			  WebServiceFactory.getPLAttachementData(markers).then(getPLAttachementData);
			  
			  $scope.deleteFile = function(objectid,category) {
				  if(userType == 'Delivery')			
				  {				 
			     $scope.uploadBtnDisable = true;  			
				  }			
				  else {
			  WebServiceFactory.changeActiveStatus(objectid);
			  $window.location.reload();						
			  }; }      
			  
			  
			  if(userType == 'Delivery')			
			  {				 
		       $scope.uploadBtnDisable = true;  			
			  }			
			  else {
			  $scope.uploadAttachmentPLData = function(frmRateCardCreation){
			  var file = $('input[name="fuAttachFilename"]').get(0).files[0]
			  $scope.upload= false;
			  if(file != undefined ) {
			  
			  $scope.uploadPLFileData(file);
			  } 
			  else {
				 BootstrapDialog.show({										
				 title : 'Rate Card Creation - Summary',
				 type : BootstrapDialog.TYPE_DANGER,
				 message : "Please upload File and Click on Update Button",
				 closable : false,
				 buttons : [ {
					 label : 'OK',
					 action : function(	
							 dialogRef) {	
						  dialogRef.close();
						  window.location="RateCardCreationSummary";	      
					 }
						
				  } ]									
				 });        								
				 }						
				} }

			  $scope.uploadPLFileData=function(file){
				  var name = "Template";
				  var tempID = 1; 
				  var rcId=$localStorage.rcId; 
				  
				  var formData = new FormData();
				  formData.append('file', file);
				  formData.append('name', name);
				  formData.append('TempId', tempID);
				  formData.append('versionId', $localStorage.rcId);
				  
				  var uploadUrl = contextPath+"/RightPrice-DAS/uploadPLFileData";
				  $http.post(uploadUrl, formData, {	
					  transformRequest: angular.identity,
					  headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
					  }).then(function(data) {	 
						  var obj = JSON.stringify(data);
						  var json = JSON.parse(obj);
						  var customMessage = data.data;
						  
						  if(data.status == 200)      
						  {									
							  BootstrapDialog.show({
								  title : 'Rate Card Creation - Summary',
								  type : BootstrapDialog.TYPE_PRIMARY,
								  message : customMessage,
								  closable : false,
								  buttons : [ {
									  label : 'OK',
									  action :function(
											  dialogRef) {
										  dialogRef.close();
										  window.location="RateCardCreationSummary";
										  }										
								  } ]									
							  });        																		
							  $scope.searchFile(rcId);
							  } 						    	
						  else if(data.status == 205){
							  BootstrapDialog.show({
								  title : 'Rate Card Creation - Summary',
								  type : BootstrapDialog.TYPE_DANGER,
								  message : "File Upload Failed.",
								  closable : false,
								  buttons : [ {
									  label : 'OK',
									  action : function(dialogRef) {
										  dialogRef.close();
										  window.location="RateCardCreationSummary";
										  }										
								  } ]									
							  });        								
							  }								
						  else {	
							  BootstrapDialog.show({
								  title : 'Rate Card Creation - Summary',
								  type : BootstrapDialog.TYPE_DANGER,
								  message : customMessage,
								  closable : false,	
								  buttons : [ {
									  label : 'OK',
									  action : function(dialogRef) {
										  dialogRef.close();
										  window.location="RateCardCreationSummary";
										  }									
								  } ]								
							  });								
							  }						    
						  },function(data) {
							  $scope.displayres = data.data;
							  $scope.answer = 'Posting data was unsuccessful.';	
							  });						
				  }
			  
			  $scope.searchFile = function(rcId)
			  {							
				  angular.element("input[type='file']").val(null);
				  console.log("inside File Search---"+rcId);
				  
				  var markers = {
						  "versionId" : $localStorage.rcId
						  };							
				  $http({							    
					  method: 'POST',
					  url: contextPath+'/RightPrice-DAS/getPLAttachementData',
					  dataType: 'json',
					  data: JSON.stringify(markers),
					  headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					  }).
					  then(function(data) {});
				  }  					
			  $scope.downloadFile = function(objectId){
				  // alert('hi'+objectId);
				  WebServiceFactory.downloadFileFormID(objectId);
            }; 

    
	
}]);
		app.filter('headerFilter', function () {
			 return function (input) {
			      return input.replace(/,/g, ' - ');
			  };
			});
		app.filter('placeholder', [function () {
		    return function (text, placeholder) {
		        if (angular.isFunction(text)) text = text();
		        return text.trim() || placeholder;
		    };
		}]);