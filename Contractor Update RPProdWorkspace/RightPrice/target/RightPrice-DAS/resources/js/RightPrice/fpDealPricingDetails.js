"use strict";
//var app = angular.module('RightPrice', ['ngMessages', 'ngStorage']);
		app.controller("FPDealPricingDetailsController", ['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory,$localStorage, $sessionStorage,$index){
			 var contextPath = "/RightPrice-DAS";		
			$scope.UploadHidden = true; 
			$scope.UploadHiddenQuest=true;
			$scope.AttachmentHidden = true; 
			$scope.UploadHiddenDevQuest=true;
			$scope.UploadHiddenMainQuest= true;
			$scope.showdevquest=false;
			$scope.showmainquest=false;
			$scope.synbotquestions = false;
			$scope.Renewal=false;
			$scope.dealdevans = [];
			$scope.dealmainans= [];
			$scope.AttachementData=[];
			$scope.devAttachementData=[];
			$scope.mainAttachementData=[];
			$scope.MDAttachementData=[];
			$scope.attachmentTableHide=false;
			$scope.uploadTableHide=false;
			$scope.mainTableHide=false;
			$scope.devTableHide=false;
			$scope.alertDev=false;
			$scope.alertMain=false;
			$scope.maintainHide=true;
			$scope.showstaffing=false;
			$scope.fpdealnormal=false;
			$scope.fpdealmanual=false;
			$scope.isFPDealGFT = false;
			 $scope.isFPDealRiskManagers = false;
			$scope.isDevelopment=false;
			$scope.OldDealShow=true;
			var dealRCId = 0;
			var userType = sessionStorage.getItem('userType');
			$scope.viewmode=false;
			var currencyId=0;
			var crmdlLen=0;
			var yearDiff = 0;
		    var monthDiff = 0;
			var customerId=null;
			var dealId=null;
			var crmDealId=null;
			$scope.dealStart = null;
			$scope.dealEnd = null;
			$scope.isCurrStatus=false;
			$scope.omPer;
			$scope.spoc="";
			$scope.lobid;
			$scope.isCurrStatus=false;
			$scope.excelArray=[];
			$scope.revenue;
			$scope.omAfterVol;
			$scope.localHC;
			$scope.deputedHc;
			$scope.offshorHC;
			$scope.subconOffshoreHC;
			$scope.subconOnsiteHC;
			$scope.totalHC;
			$scope.onsitePer;
			$scope.onsitePerc;
			$scope.OffshoreB2Per;
			$scope.OffshoreB2Ap1;
			$scope.oldDealId;
			$scope.pid;
			$scope.oldMatrics;
			$scope.getVersionData;
			$scope.OldDealDetails; 
			$scope.rateCardIds;
			$scope.onsiteHrs;
			$scope.offshoreHrs;
			$scope.excelArray=[];
			var offshoreB2Ap1 = "";
			$scope.totalHrs=0,
			$scope.onsiteHrsPm2=0,
			$scope.offshoreHrsPm2=0;
			$scope.masterLob=[];
			$scope.verticalName;
			$scope.userName="";
			$scope.oldSoldMargin;
			$scope.downloadExcel = [];
			
			$scope.onshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
			$scope.offshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
			var userType = sessionStorage.getItem('userType');
			 console.log("The USer Value from the Session is........ "+ userType);
			 if(userType == 'GFT') {
				 $scope.isFPDealGFT = true;
			 } 
			 else if(userType == 'RiskManagers') {
					$scope.isFPDealRiskManagers = true;
				}
			 
			
			
			$scope.ShowHideUpload = function () {
			     $scope.UploadHidden = $scope.UploadHidden ? false : true;
			 };
			 
			 $scope.ShowHideUploadQuest = function () {
					 $scope.UploadHiddenQuest = $scope.UploadHiddenQuest ? false : true;
					
			 };
			 
			 
			 $scope.ShowHideAttachment = function () {
			     $scope.AttachmentHidden = $scope.AttachmentHidden ? false : true;
			 };
			$scope.Next = function()
		    {  
				window.location='FPDealCreationFinalizeDeal';
		    }; 
		    
		    $scope.Prev = function() 
		    {   
		    	window.location='FPDealCreationCostSummary';
				
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
			$scope.Cancel = function()
			{
				$scope.update = false;
				$scope.clearData();
				$scope.frmDeal.customerNameModel = null;
				$scope.crmDealDetail = null;
				$scope.frmDeal.DealModel = null;
				$scope.versionDetail = null;
				$scope.frmDeal.versionModel = null;
				$scope.frmDeal.txtOldDealId = null;
				$scope.frmDeal.txtProjectId = null;
				$scope.frmDeal.fuUploadFilenameModel = null;
                $scope.frmDeal.fuAttachFilenameModel = null;
                $scope.tableHide=true;
			}
			
			$scope.showDealDetails = function(crmDealId){
				var crmDealId=$scope.frmDeal.oldDealModel;
				console.log("showDealDetails");
				console.log(crmDealId);
				$localStorage.DealModel = crmDealId;
				$scope.viewmode = true;
				var browser = window.navigator.appVersion;

	              //Workaround to enable the users to download the report in IE.
	              if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
	                     (browser.indexOf('MSIE 10') !== -1)) 
	              {
	            	  window.open ("FPDealCreationDetails","$localStorage.DealModel");
//	            	  window.open ("RightPrice/FPDealCreationDetails","$localStorage.DealModel");
	              } 
	              else 
	              {
	            	  window.open ("FPDealCreationDetails","$localStorage.DealModel");
//	            	  window.open ("RightPrice/FPDealCreationDetails","$localStorage.DealModel");
	              }
			/*	window.location = "RightPrice/FPDealCreationDetails";*/
			};
			
			var temp;	
			$window.onload = function() 
			{		
				temp=0;
				 var getLobDetails = function(response) {
						$scope.masterLob = response.data;
						console.log($scope.masterLob );
						
				};
				WebServiceFactory.getLobDetails().then(getLobDetails);
				
				var rpDealVersionId = $localStorage.rpDealVersionId;
				console.log("rpDealVersionId "+rpDealVersionId);
				var crmDealId=$localStorage.DealModel;
				var customerId=$sessionStorage.customerId;
				$scope.frmDeal.dealVersionModel=rpDealVersionId;

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
				  		console.log($scope.dealDetails[0].dealStatus);
				  		$sessionStorage.dealTCV=$scope.dealDetails[0].dealTCV;
				  		$localStorage.penaltyPer=$scope.dealDetails[0].penaltyPercentage;
				  		$sessionStorage.deal=$scope.dealDetails[0].deal;
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
				  		$scope.currency=$scope.dealDetails[0].currency;
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
				  		
				  		$scope.currancyName = $scope.dealDetails[0].currency; 
				  		
					};

					WebServiceFactory.getDealDetails($localStorage.DealModel,rpDealVersionId).then(getDealDetails);
				
					var getPrevDataDeal = function(response){
						console.log(response.data);
						var dealinfo = response.data[0];
						$scope.onsiteHrs = dealinfo[10];
						$scope.offshoreHrs = dealinfo[11];
						$scope.isNewDeal = dealinfo[31] ;
						if(dealinfo[31]==0){
							$scope.isNewDeal = "Renewal"
						}
						if(dealinfo[31]==1){
							$scope.isNewDeal = "New"
						}
						if(dealinfo[31]==2){
							$scope.isNewDeal = "RFP/RFI"
						}
						//$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }, { name: "RFP/RFI", id: 2 }];
					}
					WebServiceFactory.getPrevDataDeal_Version(rpDealVersionId).then(getPrevDataDeal)
					
					var getFpDealRateCard = function(response)
			     	{
			     		console.log("RateCards based on Version ID");
			     		$scope.fpdealratecards = response.data;
			     		console.log($scope.fpdealratecards);
			     		if($scope.fpdealratecards!="")
			     			{
			     				/*dealRCId=$scope.fpdealratecards[0].rcId;
			     				$scope.fpdcRoleSelectionFrm.rateCardModel=$scope.fpdealratecards[0].rcName;*/
			     				$scope.rateCardIds = "";
			     				for(var i=0;i<$scope.fpdealratecards.length;i++)
			     				{
			     				if(i==($scope.fpdealratecards.length-1)){
			     					$scope.rateCardIds = $scope.rateCardIds + $scope.fpdealratecards[i].rcId
			     				}else{
			     					$scope.rateCardIds = $scope.rateCardIds+ $scope.fpdealratecards[i].rcId +" ; "
			     				}	
			     				}
			     				
			     			}
			     		else
			     			{
			     				dealRCId=0;
			     				//$scope.fpdcRoleSelectionFrm.rateCardModel=0;
			     			}
			     	}
			     	WebServiceFactory.getFpDealRateCardOnVersion(rpDealVersionId).then(getFpDealRateCard);
				$scope.getDealVersionDetails(rpDealVersionId);
				$scope.getWhatIfEffortMonthlyData(rpDealVersionId);
				$scope.getSubContractorData(rpDealVersionId);
				$scope.getPricingWhatIfCalculationData(rpDealVersionId);
				
				
				if(userType == 'GFT' || userType == 'CEO' || userType == 'CDO'||userType == 'RiskManagers')
				{
					var getGFTCustomer = function(response) {
						console.log("customer response");
						//console.log(response.data);
						$sessionStorage.customer = response.data;
						console.log("customer::::::::::::::::::");
						console.log(response.data);
					};
					WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
				}
				else if(userType == 'BHU' || userType == 'DUH')
				{
					//Local storage data
					var getCustomerByVerticalGroupId = function(response) {
						console.log("customer response");
						//console.log(response.data);
						$sessionStorage.customer = response.data;
						console.log("customer::::::::::::::::::");
						console.log(response.data);
					};
					WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);
				}
				else
			 	{
			 		var getCustomerForUser = function(response) {
			 			console.log("customer response");
			 			//console.log(response.data);
			 			$sessionStorage.customer = response.data;
			 			console.log("customer::::::::::::::::::");
			 			console.log(response.data);
			 		};
			 		WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
			 	}
				if(typeof rpDealVersionId != 'number') {
					rpDealVersionId = parseInt(rpDealVersionId);
				}
				if(typeof rpDealVersionId == 'number')  //if(isNaN(rpDealVersionId)) //typeof num1 == 'number'
				{
					temp=10;			
					
					
				}
				var lanId = $sessionStorage.currentUser
				var userDetails = function(response){
				$scope.excelArray.push ({
						"userName" : response.data
				});
				}
				WebServiceFactory.getUserName(lanId).then(userDetails)
				 			
			};
			
			


			

$scope.getDealVersionDetails = function(VersionId){
	console.log(VersionId);
	
	if(VersionId != undefined)
		{
		var getVersionData = function(response) {
			console.log("get versionDetails Data..");
			console.log(response);
			$scope.getVersionData=response.data;	
			$scope.versionDetails=response.data;
			if($scope.getVersionData != undefined && $scope.getVersionData.length != 0)
				{
				$scope.lobid=$scope.getVersionData[0].projectLobId;
				$scope.spoc=$scope.getVersionData[0].dealcrmstagesdata2.salesSpoc;
				$scope.frmDeal.descriptionModel=$scope.getVersionData[0].dealVersion;
				$scope.frmDeal.customerModel=$scope.getVersionData[0].dealcrmstagesdata2.customer.customerName;
				$scope.isProjectType=$scope.getVersionData[0].fpProjectTypeId;
				$scope.frmDeal.isDealIdModel=$scope.getVersionData[0].crmDealId;
				$localStorage.verticalId=$scope.getVersionData[0].verticalId;
				
				var verticalData = function(response){
					$scope.excelArray[0].verticalName = response.data[0].verticalName
				}
				WebServiceFactory.getVerticalDetails($localStorage.verticalId).then(verticalData)
				
				$localStorage.isNewOrRenewal=$scope.getVersionData[0].isNewDeal;
				var RenewalId=$scope.getVersionData[0].oldDealId;
				if($localStorage.isNewOrRenewal == 0)
					{
						$scope.OldDealShow=false;
						$scope.getOldDealDet(RenewalId);
					}
				else{
						$scope.OldDealShow=true;
					}
				
				if($scope.isProjectType == 1)
					{
						$scope.frmDeal.isProjectTypeModel='Development - Fixed Price';
					}
				else if($scope.isProjectType == 2)
					{
						$scope.frmDeal.isProjectTypeModel='Development - Manage Capacity';
					}
				else if($scope.isProjectType == 3)
					{
						$scope.frmDeal.isProjectTypeModel='Maintenance - Fixed Price';
					}
				else{
					$scope.frmDeal.isProjectTypeModel='Maintenance - Manage Capacity';
					}
				var currencyId=$scope.getVersionData[0].dealcrmstagesdata2.currencyId;
				var getCountryDetail=function(response)
				{	
				console.log("Country Details");
				console.log(response);
				$scope.getCountryDetails=response.data;
				angular.forEach($scope.getCountryDetails,function(value,key){
					if($scope.getCountryDetails[key].currencyId==currencyId)
					{
						$scope.getVersionData[0].currencyName=value.currencyCode;
						$scope.frmDeal.currencyModel=$scope.getVersionData[0].currencyName;
						$scope.currencyId = $scope.getCountryDetails[key].currencyId;
						

					}
				});
				
				}
				WebServiceFactory.getCountryDetail().then(getCountryDetail);
				
				$scope.revenue=$scope.getVersionData[0].estimatedRevenue;
				$scope.frmDeal.revenueModel=$scope.getVersionData[0].estimatedRevenue;
				$scope.frmDeal.directcostModel=$scope.getVersionData[0].directCost;
				$scope.frmDeal.volumeDiscountModel=$scope.getVersionData[0].volumeDiscountPercent;
				$scope.frmDeal.projectSGAModel=$scope.getVersionData[0].projectSpecificCost;
				$scope.frmDeal.grossMarginModel=$scope.getVersionData[0].gmPercentage *100;
				$scope.frmDeal.gmOMPer=$scope.getVersionData[0].gmAfterProjectSpecificCost *100;
				$scope.frmDeal.gmOMVD=$scope.getVersionData[0].gmAfterVolumeDiscount *100;
				$scope.frmDeal.onsiteHrsPerDayModel=$scope.getVersionData[0].onsiteHours * $scope.getVersionData[0].workingDays;
				$scope.frmDeal.offshoreHrsPerDayModel=$scope.getVersionData[0].offShoreHours * $scope.getVersionData[0].workingDays;
				$scope.onsiteHrsPm2=$scope.frmDeal.onsiteHrsPerDayModel;
				$scope.offshoreHrsPm2=$scope.frmDeal.offshoreHrsPerDayModel;
				var dealStartDate  = $scope.getVersionData[0].dealcrmstagesdata2.dealStartDate;
				$scope.dealStart = $scope.getVersionData[0].dealcrmstagesdata2.dealStartDate;
				$scope.dealEnd = $scope.getVersionData[0].dealcrmstagesdata2.dealEndDate2;
				
				var date = new Date(dealStartDate.substring(0,10));
				$scope.dealStartDD = $filter('date')(date,'dd/MM/yyyy');
				$scope.frmDeal.dealStartDateModel = $scope.dealStartDD;
				
				var dealEndDate  = $scope.getVersionData[0].dealcrmstagesdata2.dealEndDate2;
				var date = new Date(dealEndDate.substring(0,10));
				$scope.dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
				$scope.frmDeal.dealEndDateModel = $scope.dealEndDateDD;
				$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }, { name: "RFP/RFI", id: 2 }];

				$scope.isNewOrRenewal=$scope.getVersionData[0].isNewDeal;
				if($scope.isNewOrRenewal == 0)
					{
						$scope.frmDeal.isNewRenewalModel='Renewal';
					}
				else if($scope.isNewOrRenewal == 1)
					{
						$scope.frmDeal.isNewRenewalModel='New';
					}
				else{
						$scope.frmDeal.isNewRenewalModel='RFP-RFI';
				}
				/*$scope.isManualType=$scope.getVersionData[0].isManualDeal;
				if($scope.isManualType == 0 )
					{
						$scope.fpdealnormal=true;
						$scope.fpdealmanual=false;
					}
				else{
						$scope.fpdealnormal=false;
						$scope.fpdealmanual=true;
				}
			*/
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
	    				$scope.proj_Type = "FPD"
	 	    			$scope.capacityBased = "Fixed Price"	
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==2)
	    			{
	    				$scope.versionDetails[0].projectType='Development - Manage Capacity';
	    				$scope.masterRole=false;
	    				$scope.proj_Type = "FPD"
	 	    			$scope.capacityBased = "Manage Capacity"
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==3)
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Fixed Price';
	    				$scope.masterRole=true;
	    				$scope.proj_Type = "FPM"
	 	    			$scope.capacityBased = "Fixed Price"
	    			}
	    		else
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Manage Capacity';
	    				$scope.masterRole=false;
	    				$scope.proj_Type = "FPM"
	 	    			$scope.capacityBased = "Manage Capacity"
	    			}
				}
			else{
				BootstrapDialog.show({
		        	title : 'FP Deal Creation',
		        	type : BootstrapDialog.TYPE_DANGER,
		        	message : 'The version you entered does not exists.',
		        	closable : false,
		        	buttons : [{
		        		label : 'OK',
		        		action : function(dialogRef) {
		        			dialogRef.close();
		        			$localStorage.rpDealVersionId = $scope.frmDeal.versionModel;
		        		//	window.location = "MasterCountryForex";
		        		}
		        	}]
		        });
			}
			//----------------------------------------------------------------calculations for excel by AS5045662 -------------------------------------------------
			
						var gm=0;
						var om=0
						var omav=0
						var totalHc = 0
						var offshoreB2 = 0;
						var onHrs2 = 0;
						var offHrs2 = 0;
						var onsiteTcv2;
				  		var offshoreTcv2;
				  		
						var directCost = parseInt($scope.versionDetails[0].directCost==undefined || $scope.versionDetails[0].directCost==null ? 0 : $scope.versionDetails[0].directCost);
						var sgaCost = parseInt($scope.getVersionData[0].projectSpecificCost == undefined || $scope.getVersionData[0].projectSpecificCost == null ? 0 : $scope.getVersionData[0].projectSpecificCost);
						var volumeDiscount = parseInt($scope.getVersionData[0].volumeDiscountPercent);
						
						 $scope.revenue == 0 ? gm = 0 : gm = ((($scope.revenue-directCost)/$scope.revenue)*100);
						 $scope.revenue == 0 ? om = 0 : om = ((($scope.revenue-(directCost+sgaCost))/$scope.revenue)*100);
						($scope.revenue* (1-volumeDiscount)) == 0 ? omav = 0 : omav=((($scope.revenue *(1-volumeDiscount)-(directCost + sgaCost))/($scope.revenue* (1-volumeDiscount)))*100);
						//omav=(($scope.revenue *(1-volumeDiscount)-(directCost+sgaCost))/($scope.revenue *(1-volumeDiscount)))
								
				  		if($scope.localHC==undefined || $scope.localHC==null )
				  		{
				  			$scope.localHC = 0;
				  		}
				  		if($scope.deputedHc==undefined ||$scope.deputedHc==null )
				  		{
				  			$scope.deputedHc = 0;
				  		}
				  		if($scope.offshorHC==undefined || $scope.offshorHC==null )
				  		{
				  			$scope.offshorHC = 0;
				  		}
				  		if($scope.subConOnsiteHC==undefined || $scope.subConOnsiteHC==null )
				  		{
				  			$scope.subConOnsiteHC = 0;
				  		}
				  		if($scope.subConOffshoreHC==undefined || $scope.subConOffshoreHC==null )
				  		{
				  			$scope.subConOffshoreHC = 0;
							}
							if($scope.onsitePer==undefined || $scope.onsitePer==null )
				  		{
				  			$scope.onsitePer = 0;
				  		}
				 		if($scope.subconOnsiteHC==undefined || $scope.subconOnsiteHC==null )
				  		{
				 			$scope.subconOnsiteHC = 0;
				  		}
				  		if($scope.subconOffshoreHC==undefined || $scope.subconOffshoreHC==null )
				  		{
				  			$scope.subconOffshoreHC = 0;
				  		}
				  		totalHc = 	$scope.localHC + $scope.deputedHc + $scope.offshorHC + $scope.subconOnsiteHC + $scope.subconOffshoreHC;
				  		
				  		if($scope.frmDeal.onsiteHrsPerDayModel == undefined || $scope.frmDeal.onsiteHrsPerDayModel == null ) 
				  		{
			  			var onsiteHrsPm2 = 0;
			  			}else{
			  			onsiteHrsPm2 = $scope.frmDeal.onsiteHrsPerDayModel;
			  			}	
			  			if( $scope.frmDeal.offshoreHrsPerDayModel == undefined || $scope.frmDeal.offshoreHrsPerDayModel == null ) 
			  			{
			  				offshoreHrsPm2 = 0;
			  			}else{
			  			offshoreHrsPm2 = $scope.frmDeal.offshoreHrsPerDayModel ;
			  			}
			  			if($scope.frmDeal.onsiteTCVmodel == undefined || $scope.frmDeal.onsiteTCVmodel == null )
			  			{
						onsiteTcv2 = 0;
			  			} else{
						onsiteTcv2 = $scope.frmDeal.onsiteTCVmodel;
			  			}
			  			if($scope.frmDeal.offshoreTCVModel == undefined || $scope.frmDeal.offshoreTCVModel == null )
			  			{
						offshoreTcv2 =  0;
			  			} else{
						offshoreTcv2 = $scope.frmDeal.offshoreTCVModel
			  			}
			  			
			  			
				  		//Offshore B2
				  		totalHc==0 ? offshoreB2 = 0 : offshoreB2 = ((($scope.localHC+$scope.deputedHc+$scope.subconOnsiteHC)/totalHc)*100)   
				  			//offshoreB2 = ($scope.localHC+$scope.deputedHc+$scope.subconOnsiteHC)
				  			var addDesc2;
				  			var onsiteHrsPm2; 
				  			var offshoreHrsPm2;
				  			var onsiteHrs2 = (onsiteHrsPm2 *($scope.localHC + $scope.subconOnsiteHC + $scope.deputedHc)); 
							var offhrs2 = (offshoreHrsPm2 * ($scope.offshorHC + $scope.subconOffshoreHC));
				  			var onsiteHrs1 = (+($scope.localHC+$scope.deputedHc+$scope.subconOnsiteHC)* (21.67*$scope.onsiteHrs));
				  			var offhrs1 = (+($scope.offshorHC+$scope.subconOffshoreHC)*(21.67*$scope.offshoreHrs));
				  			var onsiteHrsPm1 = (21.67*$scope.onsiteHrs);
				  			var offshoreHrsPm1=(21.67*$scope.offshoreHrs);
				  			var offshoreTcv1;
				  			var onsiteTcv1;
				  			var addDesc1;
							$scope.frmDeal.discountPricingModel == undefined ||  $scope.frmDeal.discountPricingModel == null ? addDesc2  = 0 : addDesc2  = $scope.frmDeal.discountPricingModel ;
							(onsiteTcv2 * onsiteHrs1 / onsiteHrs2) == 0 || onsiteHrs2 == 0  ? onsiteTcv1 = 0 : onsiteTcv1 = (onsiteTcv2 * onsiteHrs1 / onsiteHrs2);  
							(offshoreTcv2 * offhrs1 / offhrs2) == 0 || offhrs2 == 0 ? offshoreTcv1 = 0 : offshoreTcv1 = (offshoreTcv2 * offhrs1 / offhrs2) 
							var totalTcv1 =   (onsiteTcv1+offshoreTcv1);
						  	var lobCode;
									
							totalTcv1 == 0 ? addDesc1 = 0 : addDesc1 = (( $scope.revenue - totalTcv1 ) / totalTcv1);     
							
							angular.forEach($scope.masterLob,function(value,key){ 
							if($scope.masterLob[key].lobId == $scope.lobid){
									lobCode = $scope.masterLob[key].lobCode;
							}
							})
							
							
						var d = new Date(),
					     minutes = d.getMinutes().toString().length == 1 ? '0'+d.getMinutes() : d.getMinutes(),
					     hours = d.getHours().toString().length == 1 ? '0'+d.getHours() : d.getHours(),
					     ampm = d.getHours() >= 12 ? 'pm' : 'am',
					     months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
					     days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
					     
					     var startdate=[],enddate=[];
					     startdate = $scope.dealStartDD.split("/")
					     enddate = $scope.dealEndDateDD.split("/")
					     $scope.localHC = $scope.frmDeal.deallocalHCModel; 
					     $scope.deputedHc = $scope.frmDeal.dealDeputedHCModel;
					     $scope.offshorHC = $scope.frmDeal.dealOffshoreHCModel;
						 angular.forEach($scope.getCountryDetails,function(value,key){
						 if($scope.getCountryDetails[key].currencyId==$localStorage.currencyId)
						 {
							 $scope.currancyName=$scope.getVersionData[0].currencyName;
						 }
						 });
					     
						 $scope.revenue = $scope.revenue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
						 directCost = directCost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
						 sgaCost= sgaCost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
						 
					     var date = d.getDate()+'-'+months[d.getMonth()]+'-'+d.getFullYear();
						$scope.excelArray.push({
							'date':date,
							'proj_Type':$scope.proj_Type,
					  		'dealId': $scope.versionDetails[0].crmDealId,
					  		'blngCurrency':$scope.currancyName,
					  		'customerName':$scope.versionDetails[0].dealcrmstagesdata2.customer.customerName,
					  		'projDescription':$scope.versionDetails[0].dealcrmstagesdata2.dealDescription,
					  		'versionName':$scope.versionDetails[0].dealVersion,
					  		'capacityBased':$scope.capacityBased,
					  		'revenue':$scope.revenue,
					  		'cost':directCost,
					  		'gm':gm,
					  		'sgaCost':sgaCost,
					  		'om':om,
					  		'volumeDisc':volumeDiscount,
							'omAfterVolume':omav,
					  		'startDt':startdate[1]+"/"+startdate[0]+"/"+startdate[2], 
					  		'endDt': enddate[1]+"/"+enddate[0]+"/"+enddate[2],
					  		'localHc':$scope.localHC,   
					  		'deputedHc':$scope.deputedHc,
					  		'offshoreHC':$scope.offshorHC, 
					  		'subConOnsiteHC': $scope.subconOnsiteHC, 
					  		'subConOffshoreHC':$scope.subconOffshoreHC,
					  		'totalHc':totalHc,
							'onsitePer':$scope.onsitePerc,
							'offshoreB2':offshoreB2,
					  		'ratecardId':$scope.rateCardIds,
					  		'onsiteHrsPm1':onsiteHrsPm1,
					  		'offshoreHrsPm1':offshoreHrsPm1,
					  		'onsiteHrs1':onsiteHrs1,
					  		'offhrs1':offhrs1,
					  		'totalHrs1':((($scope.localHC+$scope.deputedHc+$scope.subconOnsiteHC)*onsiteHrsPm1)+(($scope.offshorHC+$scope.subconOffshoreHC)*offshoreHrsPm1)),
					  		'isNewDeal':$scope.isNewDeal,
					  		'onsiteHrsPm2':onsiteHrsPm2,
					  		'offshoreHrsPm2':offshoreHrsPm2,
					  		'onsiteHrs2': onsiteHrs2, 
							'offhrs2': offhrs2,
							'totalHrs2': ((onsiteHrsPm2 *($scope.localHC + $scope.subconOnsiteHC + $scope.deputedHc))+ (offshoreHrsPm2 * ($scope.offshorHC + $scope.subconOffshoreHC))),
							'onsiteTcv2':onsiteTcv2,
			  				'offshoreTcv2': offshoreTcv2, 	
			  				'totalTcv2':onsiteTcv2 + offshoreTcv2,
			  				'addDesc2' :addDesc2,
			  				'onsiteTcv1':onsiteTcv1,
			  				'totalTcv1': (onsiteTcv1 + offshoreTcv1), 
			  				'requestorName':$scope.spoc,
			  				'lobCode':lobCode,
			  				'oldPid':null,
			  				'oldDealId': null,
			  				'totalHrs':$scope.totalHrs,
							'onsiteHrsPm2':$scope.onsiteHrsPm2,
							'offshoreHrsPm2':$scope.offshoreHrsPm2,
							'offshoreB2Ap1':$scope.offshoreB2Ap1,
							'addDesc1':addDesc1,
							'offshoreTcv1':offshoreTcv1,
							'verticalName':$scope.verticalName,
							'userName':$scope.userName,
							'comment':null,
							'oldSoldMargin':$scope.oldSoldMargin 
						});
								
				  		console.log("excelArray &&&&&&&&&&&&&&&&&&&&&&&&&&")
				  		console.log($scope.excelArray)
				  		
				  		
				  		
				  		$scope.downloadExcel = $scope.excelArray;
				  		
			//----------------------------------------------------------------calculations for excel by AS5045662 ends here -------------------------------------------------
		}
		WebServiceFactory.getVersionData(VersionId).then(getVersionData);
		
		}
	
	
	$scope.getOldDealDet = function(RenewalId)
	{
		var getOldDealDetailsPricing = function(response)
		{
			$scope.OldDealDetails = response.data;
			console.log("Old Deal Details")
			console.log($scope.OldDealDetails);
			$scope.frmDeal.oldDealIdModel=$scope.OldDealDetails[0].crmDealId;
			$scope.frmDeal.oldDealDescriptionModel=$scope.OldDealDetails[0].dealDescription;
			$scope.oldDealId = $scope.OldDealDetails[0].crmDealId;
			$scope.oldSoldMargin = $scope.OldDealDetails[0].gm;
			$scope.excelArray[0].oldSoldMargin =$scope.OldDealDetails[0].gm; 
			var olddealEndDate  = $scope.OldDealDetails[0].dealEndDate;
	  		var date = new Date(olddealEndDate.substring(0,10));
	  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.OldDealDetails[0].dealEndDate = dateENd;
	  		$scope.frmDeal.oldDealEndModel=$scope.OldDealDetails[0].dealEndDate;
	  		
			var olddealStartDate = $scope.OldDealDetails[0].dealStartDate;
			var date = new Date(olddealStartDate.substring(0,10));
			var dateStart = $filter('date')(date,'dd/MM/yyyy');
			$scope.OldDealDetails[0].dealStartDate = dateStart;
			$scope.frmDeal.oldDealStartModel=$scope.OldDealDetails[0].dealStartDate;
			
			$scope.frmDeal.oldDealVersionModel=$scope.OldDealDetails[0].versionId;
			$scope.frmDeal.oldDealVersionDescModel=$scope.OldDealDetails[0].versionDesc;
			$scope.frmDeal.oldDealRevModel=$scope.OldDealDetails[0].revenue;
			$scope.onsitePer=$scope.OldDealDetails[0].onsitePer;
			$scope.offshorePer=$scope.OldDealDetails[0].offshorePer;
			if(($scope.onsitePer != null) || ($scope.offshorePer != null))
				{
					$scope.frmDeal.OldDealOnsiteOffshoreModel=$scope.onsitePer+ ":" +$scope.offshorePer;
				}
			else
				{
					$scope.frmDeal.OldDealOnsiteOffshoreModel=0+ ":" +0;
				}
			
			 
				
				if(($scope.onsitePer != null) && ($scope.offshorePer != null))
				{
					$scope.OldDealOnsiteOffshoreModelexl=$scope.onsitePer+ "/" +$scope.offshorePer;
				}
				else if(($scope.onsitePer != null) && ($scope.offshorePer == null) )
				{
					$scope.OldDealOnsiteOffshoreModelexl=$scope.onsitePer;
				}
				else if (($scope.onsitePer == null) && ($scope.offshorePer != null) )
				{
					$scope.OldDealOnsiteOffshoreModelexl=0+"/" +$scope.offshorePer;
				}
				else
				{
				$scope.OldDealOnsiteOffshoreModelexl=0+ "/" +0;
				}
			
			$scope.frmDeal.oldDealOMPerVDModel=$scope.OldDealDetails[0].omAfterVD;
			$scope.frmDeal.oldDealB2PerModel=$scope.OldDealDetails[0].b2Percentage;
			$scope.frmDeal.OldDealB2AP1Model=$scope.OldDealDetails[0].b2aP1Percentage;

			$scope.excelArray[0].oldMatricsB2 = $scope.OldDealDetails[0].b2Percentage;
			$scope.excelArray[0].oldMatricsB2ap1 = $scope.OldDealDetails[0].b2aP1Percentage;
			$scope.excelArray[0].oldMatricsOnsitePer = $scope.OldDealDetails[0].onsitePer;
			
			
			/*="B2: " + $scope.OldDealDetails[0].b2Percentage +"%, AP1: "+$scope.OldDealDetails[0].b2aP1Percentage
			+"%, Onsite: "+$scope.OldDealDetails[0].onsitePer+"%";*/
			$scope.excelArray[0].oldPid = $scope.OldDealDetails[0].projectId;
			$scope.excelArray[0].oldDealId=$scope.OldDealDetails[0].crmDealId;
		}
		WebServiceFactory.getOldDealDetailsPricing(RenewalId).then(getOldDealDetailsPricing);
	}
	
	var getVertical = function(response){
		$scope.vertical = response.data;
		console.log("Vertical Data")
		console.log(response.data);
		angular.forEach($scope.vertical,function(value,key){
			if($scope.vertical[key].verticalId==$localStorage.verticalId)
			{
				
				$scope.frmDeal.verticalNameModel=$scope.vertical[key].verticalName;

			}
		});
		$localStorage.verticalId
	};
	WebServiceFactory.getVertical().then(getVertical);
	
	$scope.getWhatIfEffortMonthlyData = function(VersionId)
	{
		var getWhatIfEffortData = function(response) {
			console.log("What if Effort Data");
			console.log(response);
			$scope.whatIfEffortDetails = response.data;
			$scope.frmDeal.dealDeputedHCModel=0;
			angular.forEach($scope.whatIfEffortDetails,function(value,key){
				if($scope.whatIfEffortDetails[key].locationDescription=='Local Efforts')
				{
					$scope.frmDeal.deallocalHCModel=value.effortsTotal;
					$scope.localHC=$scope.whatIfEffortDetails[0].effortsTotal;
				}
				else if($scope.whatIfEffortDetails[key].locationDescription=='Short Term deputed Efforts' || $scope.whatIfEffortDetails[key].locationDescription=='Long Term Deputed Efforts')
				{
					$scope.frmDeal.dealDeputedHCModel=$scope.frmDeal.dealDeputedHCModel+value.effortsTotal;
					$scope.deputedHc = $scope.frmDeal.dealDeputedHCModel;
				}
				else if($scope.whatIfEffortDetails[key].locationDescription=='Offshore Efforts')
				{
					$scope.frmDeal.dealOffshoreHCModel=value.effortsTotal;
					$scope.offshorHC = $scope.frmDeal.dealOffshoreHCModel;
				}
				else if($scope.whatIfEffortDetails[key].locationDescription=='Total Efforts')
				{
					$scope.frmDeal.dealTotalHCModel=value.effortsTotal;
				}
				else if($scope.whatIfEffortDetails[key].locationDescription=='Local/Onsite %')
				{
					$scope.frmDeal.dealOnsitePerModel=value.effortsTotal;
					$scope.onsitePerc=value.effortsTotal*100;
				}
				else if($scope.whatIfEffortDetails[key].locationDescription=='Offshore%')
				{
					$scope.frmDeal.dealOffshorePerModel=value.effortsTotal;
				}
				else if($scope.whatIfEffortDetails[key].locationDescription=='Offshore CH %')
				{
					$scope.frmDeal.dealOffshoreB2Ap1PerModel=value.effortsTotal * 100;
					$scope.offshoreB2Ap1 = $scope.frmDeal.dealOffshoreB2Ap1PerModel;
				}
				else if($scope.whatIfEffortDetails[key].locationDescription=='Offshore AC %')
				{
					$scope.frmDeal.OffshoreB2PerModel=value.effortsTotal * 100;
				}
				
			});
		
		}
		WebServiceFactory.getWhatIfEffortData(VersionId).then(getWhatIfEffortData);
	}
	
	
	$scope.getSubContractorData = function(VersionId)
	{
		var getStaffingSubContractorPricing = function(response) {
			console.log("Pricing Subcontractor Data");
			console.log(response);
			$scope.pricingSubContractorDetails = response.data;
			$scope.frmDeal.dealSubcontractorOnsiteHCModel=$scope.pricingSubContractorDetails[0];
			$scope.frmDeal.dealSubcontractorOffshoreModel=$scope.pricingSubContractorDetails[1];
			$scope.subconOnsiteHC = $scope.frmDeal.dealSubcontractorOnsiteHCModel; 
			$scope.subconOffshoreHC = $scope.frmDeal.dealSubcontractorOffshoreModel; 
			}
		WebServiceFactory.getStaffingSubContractorPricing(VersionId).then(getStaffingSubContractorPricing);
	}
	
	$scope.getPricingWhatIfCalculationData = function(VersionId)
	{
		var getWhatIfCalculationData = function(response) {
			console.log("What if Data");
			console.log(response);
			$scope.WhatIfDetails = response.data;
			$scope.frmDeal.onsiteHoursModel=$scope.WhatIfDetails[0].onsiteTotalHours;
			$scope.frmDeal.OffshoreHoursModel=$scope.WhatIfDetails[0].offshoreTotalHours;
			$scope.frmDeal.totalHoursModel=$scope.WhatIfDetails[0].totalHours;

		}
		WebServiceFactory.getWhatIfCalculationData(VersionId).then(getWhatIfCalculationData);
	}
	
	
	};
	
$scope.exportToExcel = function(tableId) {
		
		var exportHref=WebServiceFactory.downloadExcel(tableId,'FP-Deal Creation - Pricing Details');
	};
	

}]);
			
//directive for form invalid focus
app.directive('saveClick', function () {
    return {
        restrict: 'A',
        scope: {
            form: "=saveClick"
        },
        link: function (scope, elem) {
        	
            // set up event handler on the form element
            elem.on('click', function () {
            	var formId = angular.element(document.getElementById('frmDeal'));
                // find the first invalid element
                var firstInvalid = formId[0].querySelector('.ng-invalid');
                // if we find one, set focus
                if (firstInvalid) {
                    firstInvalid.focus();
                }	                
            });
        }
    };
});		