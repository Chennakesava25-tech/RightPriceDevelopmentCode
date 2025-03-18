app.controller("FPDealCreationWhatIfTowerwiseController", ['$scope','$location','$anchorScroll','$http','$window','$localStorage','$sessionStorage','WebServiceFactory','$filter', function($scope,$location,$anchorScroll,$http,$window,$localStorage,$sessionStorage,WebServiceFactory,$filter,$index) {
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
				if($scope.isDevelopment==true)
				{
		        window.location='FPDealCreationUploadEstimationRelatedDocuments';
				}
				else
				{
					 window.location='FPDealCreationCostInputs';
					
				}
		    };
		    
		   
		     $scope.Next = function()
		    {   
		    	if(userType == 'GFT') {
		    		window.location='FPDealCreationCalculationDetails';
		    	} else {
		    		window.location='FPDealCreationCostSummary';
		    	}
		    }; 	
		    
		    $scope.checkApprovalLevel = function(selectedVal) {
				if(selectedVal == 1) {
					$scope.isApprovalType = true;
					$sessionStorage.rainbowLevelFlag = 1;
				} else if(selectedVal == 2){
					$scope.whatifDeal.rainbowApprovalLevel=null;
					$scope.isApprovalType = false;
					$sessionStorage.rainbowLevelFlag = 0;
				}
			}
		    
		    
		    $scope.isDevelopment=false;
		    $scope.RedToSubmitSave=0;
		    $scope.isApprovercomment=false;
		    $scope.isOldApprovercomment=false;
		    $scope.istranChargeable=true;
		    $scope.iswarrTotalEffortsType=true;
		    $scope.iswarConsiderCostType=true;
			 $scope.iswarrchargeable=true;
		    $scope.noOfYears=0;
		    $scope.isRCPricing=false;
		    $scope.isReadyToSubmit=false;
		   
		    $scope.isSaveDisabled=false;
		    var contextPath = "/RightPrice-DAS";
		    $scope.selectedObj = [];
			$scope.selectedRFPObj =[];
		    $scope.onSave= false;
		    $scope.isSave= false;
		    $scope.Approver=false;
		    $scope.EnterValue=true;
		    $scope.isFPDealGFT = false;
		    $scope.isFPDealRiskManagers = false;
		    $scope.rainbowApprovalPanel = true;
		    var userType = sessionStorage.getItem('userType');
			 console.log("The Fp Manual Summary Details...... "+userType);
			 if(userType == 'GFT') {
				 $scope.isFPDealGFT = true;
			}
			 else if(userType == 'RiskManagers') {
					$scope.isFPDealRiskManagers = true;
				}
			 /*if(userType == 'GFT') {
				 $scope.isFPDealGFT = true;
			} else if(userType == 'RiskManagers') {
				$scope.isFPDealRiskManagers = true;
			}*/
			 $scope.commentbox= false;
		    $scope.inputTypeModel=0;
		    console.log("Redirected Version Id");
		    console.log($localStorage.rpDealVersion);
		    console.log("Existing Version ID")
		    console.log($localStorage.rpDealVersionId);
		    console.log("Deal ID after Redirecting");
		    console.log($localStorage.DealModel);
		   	var rpVrsId = $localStorage.rpDealVersionId;	
		   	$scope.deligateUserArray = $localStorage.deligateUser;
			  console.log("The Deligate user array is.............");
			  console.log($scope.deligateUserArray);
			 $scope.proxyUserId = $sessionStorage.proxyId;
			 console.log("the proxy Id from the Dashboard is............ ");
			 console.log($scope.proxyUserId);
			 $scope.rainbowApprovalLevel = [{id: 5,name: "Level 1"}, { id: 6,name: "Level 2" }];
			
				
			 
			 
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
		  		
		  		
			};

			WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails);
			
			
		
		
			
			var getFpVersionsWhatIfApproval = function(response)  //service will be call from this line
			{
				
				console.log("Versions which are Ready for Approval");
				console.log(response);
				$scope.dealverResult = response.data;
				
				
				angular.forEach($scope.dealverResult,function(value,key)
						{	
					rpVersionId=$scope.dealverResult[key].rpDealVersionId;
							var getWhatIfCalculationData=function(response)
								{
								$scope.whatIfCalDetails = response.data;
								console.log("What if Calculation Data");
								$scope.dealverResult[key].noOfTowers=$scope.whatIfCalDetails[0].noOfTowers;
								};
					    	WebServiceFactory.getWhatIfCalculationData(rpVersionId).then(getWhatIfCalculationData);
					    	
					      	var grossMargin=$scope.dealverResult[key].estimatedRevenue - $scope.dealverResult[key].directCost;
					    	$scope.dealverResult[key].operatingMargin = grossMargin - $scope.dealverResult[key].projectSpecificCost;
						});
				
				if($scope.dealverResult.length>=1)
				{
				
				angular.forEach($scope.dealverResult,function(value,key)
						{
					if($scope.dealverResult[key].level1ApproverId!=null)
					{
						if($scope.dealverResult[key].level2ApproverId!=null)
						{
							if($scope.dealverResult[key].level3ApproverId!=null)
							{
								if($scope.dealverResult[key].level4ApproverId!=null)
								{
									if($scope.dealverResult[key].level5ApproverId!=null)
									{
										if($scope.dealverResult[key].level6ApproverId!=null)
										{
											if($scope.dealverResult[key].level7ApproverId!=null)
											{
												$scope.dealverResult[key].finalApproval=$scope.dealverResult[key].level7ApproverId.description;
											}
											else
											{
												$scope.dealverResult[key].finalApproval=$scope.dealverResult[key].level6ApproverId.description;
											}
										}
										else
										{
											$scope.dealverResult[key].finalApproval=$scope.dealverResult[key].level5ApproverId.description;
										}
									}
									else
									{
										$scope.dealverResult[key].finalApproval=$scope.dealverResult[key].level4ApproverId.description;
									}
								}
								else
								{
									$scope.dealverResult[key].finalApproval=$scope.dealverResult[key].level3ApproverId.description;
								}
							}
							else
							{
								$scope.dealverResult[key].finalApproval=$scope.dealverResult[key].level2ApproverId.description;
							}
						}
						else
						{
							$scope.dealverResult[key].finalApproval=$scope.dealverResult[key].level1ApproverId.description;
						}
					}
					else
					{
						$scope.dealverResult[key].finalApproval="No Approval yet";
					}
					
						});
				}
				
				
				
			};
		WebServiceFactory.getFpVersionsWhatIfApproval($localStorage.DealModel).then(getFpVersionsWhatIfApproval);
			
			$scope.selectInputType = [{ name: "Project Margin %", id: 1 }, { name: "REVENUE", id: 2 }];
			
			$scope.selectTranCostType = [{ name: "Chargeable", id: 1 }, { name: "Free of Cost", id: 2 },{ name: "N/A", id: 3 }];
		
			$scope.selectTranConsiderCostType=[{ name: "Yes", id: 1 }, { name: "N/A", id: 2 }];
			
			$scope.selectWarConsiderCostType=[{ name: "Yes", id: 1 }, { name: "No", id: 2 },{ name: "N/A", id: 3 }];
				
			$scope.selectWarCostType=[{ name: "Chargeable", id: 1 }, { name: "Free of Cost", id: 2 },{ name: "N/A", id: 3 }];
			
			$scope.selectWarEffortsConsiderCostType=[{ name: "Yes", id: 1 }, { name: "No", id: 2 },{ name: "N/A", id: 3 }];
			
	    	var getVersionData =function(response)
	    	{
	    		console.log("Version Data");
	    		console.log(response);
	    		$scope.versionDetails = response.data;
	    		$sessionStorage.verticalID = $scope.versionDetails[0].verticalId;
	    		$scope.currentApproverId=$scope.versionDetails[0].currentApproverId;
	    		 if(userType!='RiskManagers' && userType != 'GFT'){
		    			if($scope.currentApproverId == $scope.user) {
							$scope.Approver = true;
							 $scope.getFPDealSummaryDetails($scope.user);
		    			}
		    			else{
		    				$scope.Approver = false;	
		    				 $scope.getFPDealSummaryDetails($scope.user);
		    			}
		    		}else if(userType=='GFT')
		    		{
		    			if($scope.currentApproverId == 'GFT') {
			    			$scope.Approver = true;
							$scope.getFPDealSummaryDetails($scope.user);
						}
		    			else{
		    				$scope.Approver = false;	
		    				 $scope.getFPDealSummaryDetails($scope.user);
		    			}
		    		}
		    		else{
		    			if($scope.currentApproverId == 'RiskManagers') {
		    				$scope.Approver = true;
							$scope.getFPDealSummaryDetails($scope.user);
						}
		    			/*var getRiskManagersMemberDetail=function(response)
		    	    	{
		    			 $scope.getRiskManagersMemberDetail = response.data;
						 $scope.RiskManagersVerticalId=$scope.getRiskManagersMemberDetail[0].verticalId;
						 if($scope.getRiskManagersMemberDetail!=undefined){
							 if($scope.RiskManagersVerticalId == $sessionStorage.verticalID){
									$scope.Approver = true;
								}
					    		else{
					    				$scope.Approver = false;	
					    			}
					    	}
						 $scope.getFPDealSummaryDetails($scope.user);
						 };
		    	    	WebServiceFactory.getRiskManagersMemberDetail($scope.user).then(getRiskManagersMemberDetail);
		    			}*/
		    			else{
		    				$scope.Approver = false;	
		    				 $scope.getFPDealSummaryDetails($scope.user);
		    			}
		    		}	
		    			/*var getRiskManagersMemberDetail = function(response){
							$scope.getRiskManagersMemberDetail = response.data;
							 $scope.RiskManagersVerticalId=$scope.getRiskManagersMemberDetail[0].verticalId;
							 if($scope.getRiskManagersMemberDetail!=undefined){
								 if($scope.RiskManagersVerticalId == $sessionStorage.verticalID){
										$scope.Approver = true;
									}
						    		else{
						    				$scope.Approver = false;	
						    			}
						    	}
							 }
						WebServiceFactory.getRiskManagersMemberDetail($scope.user).then(getRiskManagersMemberDetail)*/
		    		
	 				
	    		$sessionStorage.isNewDeal=$scope.versionDetails[0].isNewDeal;
	    		$sessionStorage.fpProjectTypeId=$scope.versionDetails[0].fpProjectTypeId;
	    		$sessionStorage.currentApprovalStatusId=$scope.versionDetails[0].currentApprovalStatus;
	    		$localStorage.currencyId=$scope.versionDetails[0].currencyId;
	    		
	    		var getFpToExchangeRates = function(response) 
				{
					console.log("fp EXCHANGE RATES  ..............");
					console.log(response);
					$scope.exchangedetails = response.data;
					
					
				
				};
				WebServiceFactory.getFpToExchangeRates(rpVrsId,$localStorage.currencyId).then(getFpToExchangeRates);
	    		
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
	    		
	    		
	    		$localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
	    		if(userType == 'Delivery') {/*
	    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
	    				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<5)
	    			{
	    			BootstrapDialog.show({
						title : 'FP Deal Creation -What If',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'Data is not saved at previous screen.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								//$window.location.reload();
								
								if($localStorage.pageTracker==4)
								{
									window.location="FPDealCreationCostInputs";
								}
								else if($localStorage.pageTracker==3)
									{
										window.location="FPDealCreationStaffing";
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
	    		*/}
	    		
	    		
	    		
	    	
			/*if(userType == 'RiskManagers'){
				
			}
			else if($scope.versionDetails[0].currentApproverId == userType) {
						$scope.Approver = true;
	    			}
	    			else{
	    				$scope.Approver = false;	
	    			}
				
			}*/
				
	    		$scope.whatifDeal.approverCommentModel=$scope.versionDetails[0].approverComments;
	    		 $sessionStorage.verticalID = $scope.versionDetails[0].verticalId;
	 	 	 	$scope.getVerticalDetails($sessionStorage.verticalID);
	    		
	    		$scope.getWhatIfCalDetails(rpVrsId);
	    		

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
	    		/*if($scope.versionDetails[0].currentApprovalStatus ==2){
	    			$scope.saveWhatIfData();
	    		}*/
	    		
	    	};
	    	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
	    	
	    	
	    	
	    	
	    	var getWhatIfData=function(response)
	    	{
	    		console.log("What if Data");
	    		console.log(response);
	    		$scope.whatIfDetails = response.data;
	    		$scope.whatIfDetailForExcel = response.data;
	    		$sessionStorage.projectSGAWhat=$scope.whatIfDetails[1].userInput
	    		
	    		angular.forEach($scope.whatIfDetails,function(value,key){
  					var rev=$scope.whatIfDetails[0].userInputValue;
  					$scope.whatIfDetails[key].diff=(($sessionStorage.msaRoles -$scope.whatIfDetails[0].userInputValue)/$sessionStorage.msaRoles)*100;
  				});
	    		
	    		console.log($scope.whatIfDetails);
	    	};
	    	WebServiceFactory.getWhatIfData(rpVrsId).then(getWhatIfData);
	    	
	    	var getWhatIfDataExcel=function(response)
	    	{
	    		console.log("What if Data");
	    		$scope.getWhatIfDataExcel = response.data;
	    		console.log($scope.getWhatIfDataExcel);
	    	
	    		
	    	};
	    	WebServiceFactory.getWhatIfDataExcel(rpVrsId).then(getWhatIfDataExcel);
	    	
	    	var getWhatIfEffortData=function(response)
	    	{
	    		console.log("What if Effort Data");
	    		console.log(response);
	    		$scope.whatIfEffortDetails = response.data;
	    		
	    		
	    	};
	    	WebServiceFactory.getWhatIfEffortData(rpVrsId).then(getWhatIfEffortData);
	    	

	    	var getFpDealStaffingDataWhatIf=function(response)
	    	{	$scope.StaffingHeaderData=[];
	    		console.log("Staffing Headers");
	    		console.log(response);
	    		$scope.StaffingHeaderData = response.data[0];
	    		/*angular.forEach($scope.StaffingHeaderData,function(value,key){
	    			
	    			if (!key==0) {
	    		        $scope.StaffingHeaderData.splice(key, 1);
	    		    }
  				});*/
	    		
	    		
	    	};
	    	WebServiceFactory.getFpDealStaffingDataWhatIf(rpVrsId).then(getFpDealStaffingDataWhatIf);
	    	
	    	$scope.getWhatIfCalDetails =function(rpVrsId)
	    	{
	    	var getWhatIfCalculationData=function(response)
			{
				
				$scope.whatIfCalDetails = response.data;
				console.log("What if Calculation Data");
				console.log($scope.whatIfCalDetails);
				$sessionStorage.CurrentCPC=$scope.whatIfCalDetails[0].currentCPC;
				$sessionStorage.CostWithoutCTC=$scope.whatIfCalDetails[0].costWithoutCPC;
				$sessionStorage.buMargin=$scope.whatIfCalDetails[0].buMargin;
				$sessionStorage.ceoMargin=$scope.whatIfCalDetails[0].ceoMargin;
				$sessionStorage.totalHours=$scope.whatIfCalDetails[0].totalHours;
				$sessionStorage.onsiteTotalHours=$scope.whatIfCalDetails[0].onsiteTotalHours;
				$sessionStorage.offshoreTotalHours=$scope.whatIfCalDetails[0].offshoreTotalHours;
				$scope.noOfYears=$scope.whatIfCalDetails[0].noOfYears;
				$sessionStorage.noOfTransistionMonth=$scope.whatIfCalDetails[0].noOfTransistionMonth;
				$scope.isStaffingComplete=$scope.whatIfCalDetails[0].isStaffingComplete;
				$scope.whatifDeal.masterRoles=$scope.whatIfCalDetails[0].masterRevenue;
				$scope.whatifDeal.rateCardRoles=$scope.whatIfCalDetails[0].rateCardRevenue;
				$scope.whatifDeal.contractorRoles=$scope.whatIfCalDetails[0].contractorRevenue;
				$sessionStorage.msaRoles=$scope.whatIfCalDetails[0].msaRevenue;
				$localStorage.projectSGACal=$scope.whatIfCalDetails[0].projectSGA;
				$sessionStorage.totaloffshoredirectcost=$scope.whatIfCalDetails[0].totaloffshoredirectcost;
				$sessionStorage.slarisk=$scope.whatIfCalDetails[0].slarisk;
				$sessionStorage.totalriskamount=$scope.whatIfCalDetails[0].totalriskamount;
				$sessionStorage.projectmarginperinclRisk=$scope.whatIfCalDetails[0].projectmarginperinclRisk;
				$sessionStorage.pminclRiskandVD=$scope.whatIfCalDetails[0].pminclRiskandVD;
				$localStorage.projectSGACal=$scope.whatIfCalDetails[0].projectSGA;
				$sessionStorage.fxriskoffshore=$scope.whatIfCalDetails[0].fxriskoffshore;
				$sessionStorage.contingentrisk=$scope.whatIfCalDetails[0].contingentrisk;
				/*if($scope.isStaffingComplete==1)
					{
						$scope.isSaveDisabled=false;
						$scope.isReadyToSubmit=false;
						*/
				
				if(($sessionStorage.currentApprovalStatusId == null ||  $sessionStorage.currentApprovalStatusId == 1 || $sessionStorage.currentApprovalStatusId == 4) && userType == 'Delivery')
				{
					$scope.isSaveDisabled=false;
					$scope.isReadyToSubmit=false;
				}
			else
				{
					$scope.isSaveDisabled=true;
					$scope.isReadyToSubmit=true;
				}
						
						
				/*	}
				else
					{
					
					BootstrapDialog.show({
	    	            title : 'Fixed Price What If',
	    	            type : BootstrapDialog.TYPE_DANGER,
	    	            message : "Please fill data for all the Years in Staffing screen.",
	    	            closable : false,
	    	            buttons : [{
	    	                   label : 'OK',
	    	                   action : function(dialogRef) {
	    	                         dialogRef.close();
	    	                           
	    	                   }
	    	            }]
	    	      });
					
					$scope.isSaveDisabled=true;
					$scope.isReadyToSubmit=true;
				}*/
				
			};
			WebServiceFactory.getWhatIfCalculationData(rpVrsId).then(getWhatIfCalculationData);
	    	}		
			
			var getWhatIfContractData=function(response)
			{
				$scope.selectTranCostType = [{ name: "Chargeable", id: 1 }, { name: "Free of Cost", id: 2 },{ name: "N/A", id: 3 }];
			
				$scope.selectTranConsiderCostType=[{ name: "Yes", id: 1 },{ name: "N/A", id: 2 }];
				
				$scope.selectWarConsiderCostType=[{ name: "Yes", id: 1 }, { name: "No", id: 2 },{ name: "N/A", id: 3 }];
					
				$scope.selectWarCostType=[{ name: "Chargeable", id: 1 }, { name: "Free of Cost", id: 2 },{ name: "N/A", id: 3 }];
				
				$scope.selectWarEffortsConsiderCostType=[{ name: "Yes", id: 1 }, { name: "No", id: 2 },{ name: "N/A", id: 3 }];
				
				$scope.whatIfContractDetails = response.data;
				console.log("What if Contract Data");
				console.log($scope.whatIfContractDetails);
				
			if($scope.whatIfContractDetails!="")	
			{	
				
				$scope.whatifDeal.tranChargeableModel=$scope.whatIfContractDetails[0].chargeableTransitionPercent;
				if($scope.whatifDeal.tranChargeableModel>0)
					{
						$scope.istranChargeable=false;
					}
				else
					{
						$scope.istranChargeable=true;
					}
				
				$scope.whatifDeal.warrchargeableModel=$scope.whatIfContractDetails[0].chargeableWarrantyPercent;
			
				$scope.whatifDeal.warrTotalEffortsTypeModel=$scope.whatIfContractDetails[0].totalWarrantyEfforts;
				
				for(var i=0;i< $scope.selectTranCostType.length;i++) {
					if($scope.whatIfContractDetails[0].isTransitionChargeable == $scope.selectTranCostType[i].id) {
						$scope.whatifDeal.tranCostTypeModel = $scope.selectTranCostType[i].id;
					}
				}
				
				if($sessionStorage.noOfTransistionMonth>0)
					{
						$scope.whatifDeal.tranConsiderCostTypeModel=1;
						$scope.whatifDeal.tranTotalEffortsModel=$sessionStorage.noOfTransistionMonth;
					}
				else
					{
						$scope.whatifDeal.tranConsiderCostTypeModel=2;
						$scope.whatifDeal.tranTotalEffortsModel=0;
					}
				
				
				/*
				for(var i=0;i< $scope.selectTranConsiderCostType.length;i++) {
					if($scope.whatIfContractDetails[0].isEffortsConsideredForCosting == $scope.selectTranConsiderCostType[i].id) {
						$scope.whatifDeal.tranConsiderCostTypeModel = $scope.selectTranConsiderCostType[i].id;
					}
				}*/
				
				
				for(var i=0;i< $scope.selectWarConsiderCostType.length;i++) {
					if($scope.whatIfContractDetails[0].isWarrantyProvided == $scope.selectWarConsiderCostType[i].id) {
						$scope.whatifDeal.warProvidedModel = $scope.selectWarConsiderCostType[i].id;
					}
				}
				
					if($scope.whatifDeal.warProvidedModel==1)
					{
						$scope.iswarConsiderCostType=false;
					}
				else
					{
						$scope.iswarConsiderCostType=true;
					}
				
				
				for(var i=0;i< $scope.selectWarCostType.length;i++) {
					if($scope.whatIfContractDetails[0].isWarrantyChargeable == $scope.selectWarCostType[i].id) {
						$scope.whatifDeal.warConsiderCostTypeModel = $scope.selectWarCostType[i].id;
					}
				}
				
					if($scope.whatifDeal.warConsiderCostTypeModel==1)
					{
						$scope.iswarrchargeable=false;
					}
				else
					{
						$scope.iswarrchargeable=true;
					}
				
				for(var i=0;i< $scope.selectWarEffortsConsiderCostType.length;i++) {
					if($scope.whatIfContractDetails[0].isWarrentyEffortsConsideredForCosting == $scope.selectWarEffortsConsiderCostType[i].id) {
						$scope.whatifDeal.warrcostTypeModel = $scope.selectWarEffortsConsiderCostType[i].id;
					}
				}
				
				if($scope.whatifDeal.warrcostTypeModel)
					{
						$scope.iswarrTotalEffortsType=false;
					}
				else
					{
						$scope.iswarrTotalEffortsType=true;
					}
				
				
				
			}
			else
				{
				if($sessionStorage.noOfTransistionMonth>0)
				{
					$scope.whatifDeal.tranConsiderCostTypeModel=1;
					$scope.whatifDeal.tranTotalEffortsModel=$sessionStorage.noOfTransistionMonth;
				}
				else
				{
					$scope.whatifDeal.tranConsiderCostTypeModel=2;
					$scope.whatifDeal.tranTotalEffortsModel=0;
				}
				
				}
				
	    		};
			WebServiceFactory.getWhatIfContractData(rpVrsId).then(getWhatIfContractData);
			
	    	$scope.checkfpInputType = function (inputTypeModel){
	    		// should call function
	    		
	    		$scope.EnterValue=false;
	    		$scope.getWhatIfData(rpVrsId);
	  	    	$scope.inputTypeModel = inputTypeModel;
	    		$sessionStorage.directCost = $scope.whatIfDetails[2].userInputValue;
	    		$sessionStorage.grossMarginPer = $scope.whatIfDetails[0].userInputValue;
	    		$sessionStorage.grossMargin = $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue;
	    	}
	    	
	    	$scope.checktranCostType= function (inputtranCostTypeModel)
	    	{
	    		if(inputtranCostTypeModel==1)
	    			{
	    			 $scope.istranChargeable=false;
	    			}
	    		else{
	    			 $scope.whatifDeal.tranChargeableModel=0;
	    			 $scope.istranChargeable=true;
	    		}
	    	}
	    	
	    	$scope.checkwarrcostType= function (inputwarrcostTypeModel)
	    	{
	    		if(inputwarrcostTypeModel==1)
    			{
    			 $scope.iswarrTotalEffortsType=false;
    			}
    		else{
    			 $scope.whatifDeal.warrTotalEffortsTypeModel=0;
    			 $scope.iswarrTotalEffortsType=true;
    			}
	    	}
	    	
	    	$scope.checkwarProvidedType= function (inputwarProvidedTypeModel)
	    	{
	    		if(inputwarProvidedTypeModel==1)
    			{
	    			$scope.iswarConsiderCostType=false;
    			}
    		else{
    			 $scope.whatifDeal.warConsiderCostTypeModel=3;
    			 $scope.whatifDeal.warrchargeableModel=0;
    			 $scope.iswarConsiderCostType=true;
    			 $scope.iswarrchargeable=true;
    			}
	    		
	    	}
	    	
	    	$scope.checkwarConsiderCostType= function (inputwarConsiderCostTypeModel)
	    	{
	    		if(inputwarConsiderCostTypeModel==1)
    			{
	    			$scope.iswarrchargeable=false;
    			}
    		else{
    			
    			 $scope.whatifDeal.warrchargeableModel=0;
    			 $scope.iswarrchargeable=true;
    			}
	    	}
	    	
	    	$scope.submitChange = function (value){
	    		
	    		$scope.getWhatIfCalculationData(rpVrsId);
	    		console.log($scope.whatIfCalDetails);
	    		
	    		if(value!=undefined && $scope.inputTypeModel!=undefined)
	    		{
	    			if($scope.inputTypeModel==1)
	    				{	
	    					$sessionStorage.selectionValue=102;
		    				$sessionStorage.gmpervalue=value/100;
/*		    				$scope.revenue=$sessionStorage.directCost/(1-$sessionStorage.gmpervalue);
*/		    				
		    				/*$scope.revenue=(($sessionStorage.CostWithoutCTC+$localStorage.projectSGACal ) /((1 - $scope.whatIfDetails[7].userInputValue) - $localStorage.penaltyPer/100 - $sessionStorage.CurrentCPC - ((1 - $scope.whatIfDetails[7].userInputValue) * $sessionStorage.gmpervalue)))
		    				set @BUH_Revenue = ((@direct_cost_without_CPC+@project_SGA_without_Penalty ) /((1 - @volume_discount/100) - @Penalty_Percent/100 - @CPC_cost_percent - ((1 - @volume_discount/100) * @BUH_Margin)))
		    				$scope.revenueBUH=$sessionStorage.directCost/(1-$scope.whatIfDetails[0].buhThresholdValue);
		    				
		    				$sessionStorage.totalCPC=$scope.revenue * ($sessionStorage.CurrentCPC);
	  	    				
	  	    				$sessionStorage.directCost=$sessionStorage.CostWithoutCTC + $sessionStorage.totalCPC;
		    				$scope.revenueCEO=$sessionStorage.directCost/(1-$scope.whatIfDetails[0].ceoApprovalValue);
		    				console.log("Revenue Calculated from PM% :"+ $scope.revenue);
		    				
		    				//UserInput
		    				$scope.whatIfDetails[0].userInputValue=$sessionStorage.gmpervalue;
		    				$scope.whatIfDetails[1].userInputValue=$scope.revenue;
		    				$scope.whatIfDetails[2].userInputValue=$sessionStorage.directCost;
		    				
		    				var PenaltyToAdd=($scope.whatIfDetails[1].userInputValue  * ($localStorage.penaltyPer/100)) + $localStorage.projectSGACal ;
	  	    				$scope.whatIfDetails[4].userInputValue= PenaltyToAdd;
		    				$scope.revenuePostDiscount=$scope.whatIfDetails[1].userInputValue * (1-($scope.whatIfDetails[7].userInputValue));
		    				$scope.whatIfDetails[3].userInputValue=$scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue;
		    				$scope.whatIfDetails[5].userInputValue=$scope.whatIfDetails[3].userInputValue - $scope.whatIfDetails[4].userInputValue;
		    				$scope.whatIfDetails[6].userInputValue=(($scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue - $scope.whatIfDetails[4].userInputValue)/$scope.whatIfDetails[1].userInputValue);
		    				$scope.whatIfDetails[7].userInputValue=$scope.whatIfDetails[5].userInputValue/$scope.whatIfDetails[1].userInputValue;
	  	    				$scope.whatIfDetails[8].userInputValue=($scope.revenuePostDiscount - $scope.whatIfDetails[2].userInputValue - $scope.whatIfDetails[4].userInputValue)/$scope.revenuePostDiscount;
		    				$scope.whatIfDetails[9].userInputValue=$scope.whatIfDetails[1].userInputValue/$sessionStorage.totalHours;
	  	    				//$scope.whatIfDetails[11].userInputValue=$scope.whatIfDetails[1].userInputValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);
	  	    				//$scope.whatIfDetails[10].userInputValue=$scope.whatIfDetails[11].userInputValue * 3;
		    				var FXRiskOffshoreabs=$sessionStorage.totaloffshoredirectcost*$sessionStorage.fxriskoffshore;
	  	    				var Contingencyriskabs=$scope.whatIfDetails[2].userInputValue*$sessionStorage.contingentrisk;
	  	    				var SLARiskabs=$scope.whatIfDetails[1].userInputValue*$sessionStorage.slarisk
	  	    				$scope.whatIfDetails[10].userInputValue=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;
	  	    				$scope.whatIfDetails[11].userInputValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue)/$scope.whatIfDetails[1].userInputValue;
	  	    				$scope.whatIfDetails[12].userInputValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue))/($scope.whatIfDetails[1].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue));
	  	    				*/
	  	    				
		    				$scope.revenue=(($sessionStorage.CostWithoutCTC+$localStorage.projectSGACal ) /((1 - $scope.whatIfDetails[6].userInputValue) - $localStorage.penaltyPer/100 - $sessionStorage.CurrentCPC - ((1 - $scope.whatIfDetails[6].userInputValue) * $sessionStorage.gmpervalue)))
		    				/*	set @BUH_Revenue = ((@direct_cost_without_CPC+@project_SGA_without_Penalty ) /((1 - @volume_discount/100) - @Penalty_Percent/100 - @CPC_cost_percent - ((1 - @volume_discount/100) * @BUH_Margin)))*/
		    					$scope.revenueBUH=$sessionStorage.directCost/(1-$scope.whatIfDetails[0].buhThresholdValue);		    						    				
		    					$sessionStorage.totalCPC=$scope.revenue * ($sessionStorage.CurrentCPC);
		    					$sessionStorage.directCost=$sessionStorage.CostWithoutCTC + $sessionStorage.totalCPC;

		    					$scope.revenueCEO=$sessionStorage.directCost/(1-$scope.whatIfDetails[0].ceoApprovalValue);
		    						    				console.log("Revenue Calculated from PM% :"+ $scope.revenue);
		    											//UserInput		    		
		    											//$scope.whatIfDetails[0].userInputValue=$sessionStorage.gmpervalue;
		    						    				$scope.whatIfDetails[0].userInputValue=$scope.revenue;	
		    											$scope.whatIfDetails[1].userInputValue=$sessionStorage.directCost;	
		    											var PenaltyToAdd=($scope.whatIfDetails[0].userInputValue  * ($localStorage.penaltyPer/100)) + $localStorage.projectSGACal ;
		    					  	    				$scope.whatIfDetails[2].userInputValue= PenaltyToAdd;	
		    											$scope.revenuePostDiscount=$scope.whatIfDetails[0].userInputValue * (1-($scope.whatIfDetails[6].userInputValue));
		    						    				$scope.whatIfDetails[3].userInputValue=$scope.whatIfDetails[0].userInputValue - $scope.whatIfDetails[1].userInputValue;	
		    											//$scope.whatIfDetails[5].userInputValue=$scope.whatIfDetails[3].userInputValue - $scope.whatIfDetails[2].userInputValue;
		    						    				$scope.whatIfDetails[4].userInputValue=(($scope.whatIfDetails[0].userInputValue - $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue)/$scope.whatIfDetails[0].userInputValue);		 
		    											/*$scope.whatIfDetails[6].userInputValue=$scope.whatIfDetails[5].userInputValue/$scope.whatIfDetails[0].userInputValue;
		    											*/	  	    				
		    											//$scope.whatIfDetails[8].userInputValue=($scope.revenuePostDiscount - $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue)/$scope.revenuePostDiscount;	
		    									 		$scope.whatIfDetails[8].userInputValue=$scope.whatIfDetails[0].userInputValue/$sessionStorage.totalHours;
		    					  	    				//$scope.whatIfDetails[5].userInputValue=$scope.whatIfDetails[0].userInputValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);	  	   
		    											//$scope.whatIfDetails[10].userInputValue=$scope.whatIfDetails[5].userInputValue * 3;
		    						    				var FXRiskOffshoreabs=$sessionStorage.totaloffshoredirectcost*$sessionStorage.fxriskoffshore;
		    					  	    				var Contingencyriskabs=$scope.whatIfDetails[1].userInputValue*$sessionStorage.contingentrisk;
		    					  	    				var SLARiskabs=$scope.whatIfDetails[0].userInputValue*$sessionStorage.slarisk	
		    											var TotalRisk=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;	
		    											$scope.whatIfDetails[5].userInputValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk)/$scope.whatIfDetails[0].userInputValue;
		    					  	    				$scope.whatIfDetails[7].userInputValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue))/($scope.whatIfDetails[0].userInputValue-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue));	  	    					  	    				
		    					  	    				var GMaftervolumediscount =($scope.revenuePostDiscount - $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue)/$scope.revenuePostDiscount;


	  	    			
	  	    				
	  	    				
	  	    				/*//BUH
	  	    				$scope.whatIfDetails[1].buhThresholdValue=$scope.revenueBUH;
	  	    				$scope.revenueBUHPostDiscount=$scope.whatIfDetails[1].buhThresholdValue * (1-($scope.whatIfDetails[7].buhThresholdValue));
	  	    				$scope.whatIfDetails[3].buhThresholdValue=$scope.whatIfDetails[1].buhThresholdValue - $scope.whatIfDetails[2].buhThresholdValue;
		    				$scope.whatIfDetails[5].buhThresholdValue=$scope.whatIfDetails[3].buhThresholdValue - $scope.whatIfDetails[4].buhThresholdValue;
		    				$scope.whatIfDetails[6].buhThresholdValue=(($scope.whatIfDetails[1].buhThresholdValue - $scope.whatIfDetails[2].buhThresholdValue - $scope.whatIfDetails[4].buhThresholdValue)/$scope.whatIfDetails[1].buhThresholdValue);
	  	    				$scope.whatIfDetails[8].buhThresholdValue=($scope.revenueBUHPostDiscount - $scope.whatIfDetails[2].buhThresholdValue - $scope.whatIfDetails[4].buhThresholdValue)/$scope.revenueBUHPostDiscount;
	  	    				$scope.whatIfDetails[9].buhThresholdValue=$scope.whatIfDetails[1].buhThresholdValue/$sessionStorage.totalHours;
	  	    				$scope.whatIfDetails[11].buhThresholdValue=$scope.whatIfDetails[1].buhThresholdValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);
	  	    				$scope.whatIfDetails[10].buhThresholdValue=$scope.whatIfDetails[11].buhThresholdValue * 3;
	  	    				
	  	    				//CEO
	  	    				$scope.whatIfDetails[1].ceoApprovalValue=$scope.revenueCEO;
	  	    				$scope.revenueCEOPostDiscount=$scope.whatIfDetails[1].ceoApprovalValue * (1-($scope.whatIfDetails[7].ceoApprovalValue));
		    				$scope.whatIfDetails[3].ceoApprovalValue=$scope.whatIfDetails[1].ceoApprovalValue - $scope.whatIfDetails[2].ceoApprovalValue;
		    				$scope.whatIfDetails[5].ceoApprovalValue=$scope.whatIfDetails[3].ceoApprovalValue - $scope.whatIfDetails[4].ceoApprovalValue;
		    				$scope.whatIfDetails[6].ceoApprovalValue=(($scope.whatIfDetails[1].ceoApprovalValue - $scope.whatIfDetails[2].ceoApprovalValue - $scope.whatIfDetails[4].ceoApprovalValue)/$scope.whatIfDetails[1].ceoApprovalValue);
	  	    				$scope.whatIfDetails[8].ceoApprovalValue=($scope.revenueCEOPostDiscount - $scope.whatIfDetails[2].ceoApprovalValue - $scope.whatIfDetails[4].ceoApprovalValue)/$scope.revenueCEOPostDiscount;
	  	    				$scope.whatIfDetails[9].ceoApprovalValue=$scope.whatIfDetails[1].ceoApprovalValue/$sessionStorage.totalHours;
	  	    				$scope.whatIfDetails[11].ceoApprovalValue=$scope.whatIfDetails[1].ceoApprovalValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);
	  	    				$scope.whatIfDetails[10].ceoApprovalValue=$scope.whatIfDetails[11].ceoApprovalValue * 3;*/
	  	    				$scope.whatIfDetails[5].buhThresholdValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk)/$scope.whatIfDetails[0].userInputValue;
	  	    				$scope.whatIfDetails[7].buhThresholdValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue))/($scope.whatIfDetails[0].userInputValue-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue));
	  	    				$scope.whatIfDetails[5].ceoApprovalValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk)/$scope.whatIfDetails[0].userInputValue;
	  	    				$scope.whatIfDetails[12].ceoApprovalValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue))/($scope.whatIfDetails[0].userInputValue-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue));
	  	    				$scope.whatIfDetails[5].globalRateCardValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRiske)/$scope.whatIfDetails[0].userInputValue;
	  	    				$scope.whatIfDetails[7].globalRateCardValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue))/($scope.whatIfDetails[0].userInputValue-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue));
	  	    				
	  	    				console.log($scope.whatIfDetails);
		    			
	    				}
	    		      
	    			else if($scope.inputTypeModel==2)
	    				{	
	    					$sessionStorage.selectionValue=101;
	  	    				$sessionStorage.revvalue=value;
	  	    				$sessionStorage.totalCPC=$sessionStorage.revvalue * ($sessionStorage.CurrentCPC);
	  	    				
	  	    				$sessionStorage.directCost=$sessionStorage.CostWithoutCTC + $sessionStorage.totalCPC;
	  	    				
	  	    				
	  	    				//Revenue
	  	    				/*$scope.whatIfDetails[1].userInputValue=$sessionStorage.revvalue;
	  	    				var PenaltyToAdd=($scope.whatIfDetails[1].userInputValue  * ($localStorage.penaltyPer/100)) + $localStorage.projectSGACal ;
	  	    				$scope.whatIfDetails[4].userInputValue= PenaltyToAdd;
	  	    				$scope.whatIfDetails[2].userInputValue=$sessionStorage.directCost;
	  	    				
	  	    				$scope.whatIfDetails[3].userInputValue=$sessionStorage.revvalue - $sessionStorage.directCost;
	  	    				$sessionStorage.grossMarginPer=$scope.whatIfDetails[3].userInputValue/$sessionStorage.revvalue;
	  	    				
	  	    				$scope.whatIfDetails[5].userInputValue=$scope.whatIfDetails[3].userInputValue-$scope.whatIfDetails[4].userInputValue;
	  	    				$scope.whatIfDetails[6].userInputValue=($scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue - $scope.whatIfDetails[4].userInputValue)/$scope.whatIfDetails[1].userInputValue;
	  	    				$scope.revenuePostDiscount=$scope.whatIfDetails[1].userInputValue * (1-($scope.whatIfDetails[7].userInputValue));
	  	    				$scope.whatIfDetails[8].userInputValue=($scope.revenuePostDiscount - $scope.whatIfDetails[2].userInputValue - $scope.whatIfDetails[4].userInputValue)/$scope.revenuePostDiscount;
	  	    				$scope.whatIfDetails[0].userInputValue=$scope.whatIfDetails[8].userInputValue$sessionStorage.grossMarginPer;
	  	    				$scope.whatIfDetails[9].userInputValue=$scope.whatIfDetails[1].userInputValue/$sessionStorage.totalHours;
	  	    				//$scope.whatIfDetails[11].userInputValue=$scope.whatIfDetails[1].userInputValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);
	  	    				//$scope.whatIfDetails[10].userInputValue=$scope.whatIfDetails[11].userInputValue * 3;
	  	    				var FXRiskOffshoreabs=$sessionStorage.totaloffshoredirectcost*$sessionStorage.fxriskoffshore;
	  	    				var Contingencyriskabs=$scope.whatIfDetails[2].userInputValue*$sessionStorage.contingentrisk;
	  	    				var SLARiskabs=$scope.whatIfDetails[1].userInputValue*$sessionStorage.slarisk
	  	    				$scope.whatIfDetails[10].userInputValue=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;
	  	    				$scope.whatIfDetails[11].userInputValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue)/$scope.whatIfDetails[1].userInputValue;
	  	    				$scope.whatIfDetails[12].userInputValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue))/($scope.whatIfDetails[1].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue));
	  	    				*/
	  	    				$scope.whatIfDetails[0].userInputValue=$sessionStorage.revvalue;

	  	    				
	  	    				var PenaltyToAdd=($scope.whatIfDetails[0].userInputValue  * ($localStorage.penaltyPer/100)) + $localStorage.projectSGACal ;	
	  	    				$scope.whatIfDetails[2].userInputValue= PenaltyToAdd;
	  	    				$scope.whatIfDetails[1].userInputValue=$sessionStorage.directCost;	
	  	    				$scope.whatIfDetails[3].userInputValue=$sessionStorage.revvalue - $sessionStorage.directCost;
	  	    				$sessionStorage.grossMarginPer=$scope.whatIfDetails[3].userInputValue/$sessionStorage.revvalue;	
	  	    				//$scope.whatIfDetails[5].userInputValue=$scope.whatIfDetails[3].userInputValue-$scope.whatIfDetails[2].userInputValue;
	  	    				$scope.whatIfDetails[4].userInputValue=($scope.whatIfDetails[0].userInputValue - $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue)/$scope.whatIfDetails[0].userInputValue;
	  	    				$scope.revenuePostDiscount=$scope.whatIfDetails[0].userInputValue * (1-($scope.whatIfDetails[6].userInputValue));
	  	    				var GMaftervolumediscount =($scope.revenuePostDiscount - $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue)/$scope.revenuePostDiscount;
	  	    				//$scope.whatIfDetails[8].userInputValue=($scope.revenuePostDiscount - $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue)/$scope.revenuePostDiscount;	  	    	
	  	    				//$scope.whatIfDetails[0].userInputValue=$scope.whatIfDetails[8].userInputValue/*$sessionStorage.grossMarginPer*/;
	  	    				$scope.whatIfDetails[8].userInputValue=$scope.whatIfDetails[0].userInputValue/$sessionStorage.totalHours;	
	  	    				//$scope.whatIfDetails[5].userInputValue=$scope.whatIfDetails[0].userInputValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);
	  	    				//$scope.whatIfDetails[10].userInputValue=$scope.whatIfDetails[5].userInputValue * 3;	  	  
	  	    				var FXRiskOffshoreabs=$sessionStorage.totaloffshoredirectcost*$sessionStorage.fxriskoffshore;
	  	    				var Contingencyriskabs=$scope.whatIfDetails[1].userInputValue*$sessionStorage.contingentrisk;
	  	    				var SLARiskabs=$scope.whatIfDetails[0].userInputValue*$sessionStorage.slarisk;
	  	    				var TotalRisk=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;
	  	    				$scope.whatIfDetails[5].userInputValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk)/$scope.whatIfDetails[0].userInputValue;	
	  	    				$scope.whatIfDetails[7].userInputValue=($scope.whatIfDetails[0].userInputValue-$scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-TotalRisk-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue))/($scope.whatIfDetails[0].userInputValue-($scope.whatIfDetails[0].userInputValue*$scope.whatIfDetails[6].userInputValue));	  	    				


	  	    				
	  	    				
	  	    				
	  	    				/*//buh 
	  	    				$scope.whatIfDetails[2].buhThresholdValue=$scope.whatIfDetails[2].userInputValue;
	  	    				$sessionStorage.totalCost= $scope.whatIfDetails[4].buhThresholdValue + $scope.whatIfDetails[2].buhThresholdValue;
	  	    				$sessionStorage.finalRev=$sessionStorage.totalCost/(1-(($sessionStorage.buMargin/100)));
	  	    				$scope.whatIfDetails[1].buhThresholdValue = $sessionStorage.finalRev;
	  	    				$scope.whatIfDetails[5].buhThresholdValue = $sessionStorage.finalRev - $sessionStorage.totalCost;
	  	    				$scope.whatIfDetails[3].buhThresholdValue = $scope.whatIfDetails[5].buhThresholdValue + $scope.whatIfDetails[4].buhThresholdValue;
	  	    				$sessionStorage.buhgrossMarginPer=$scope.whatIfDetails[3].buhThresholdValue/$scope.whatIfDetails[1].buhThresholdValue;
	  	    				//$scope.whatIfDetails[6].buhThresholdValue=($scope.whatIfDetails[1].buhThresholdValue - $scope.whatIfDetails[2].buhThresholdValue - $scope.whatIfDetails[4].buhThresholdValue)/$scope.whatIfDetails[1].buhThresholdValue;
	  	    				$scope.whatIfDetails[0].buhThresholdValue=($sessionStorage.buhgrossMarginPer);
	  	    				$scope.revenueBUHPostDiscount=$scope.whatIfDetails[1].buhThresholdValue * (1-($scope.whatIfDetails[7].buhThresholdValue));
	  	    				$scope.whatIfDetails[8].buhThresholdValue=($scope.revenueBUHPostDiscount - $scope.whatIfDetails[2].buhThresholdValue - $scope.whatIfDetails[4].buhThresholdValue)/$scope.revenueBUHPostDiscount;
	  	    				$scope.whatIfDetails[9].buhThresholdValue=$scope.whatIfDetails[1].buhThresholdValue/$sessionStorage.totalHours;
	  	    				$scope.whatIfDetails[11].buhThresholdValue=$scope.whatIfDetails[1].buhThresholdValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);
	  	    				$scope.whatIfDetails[10].buhThresholdValue=$scope.whatIfDetails[11].buhThresholdValue * 3;
	  	    				
	  	    				
	  	    				//ceo
	  	    				$scope.whatIfDetails[2].ceoApprovalValue=$scope.whatIfDetails[2].userInputValue;
	  	    				$sessionStorage.totalCDOCost= $scope.whatIfDetails[4].ceoApprovalValue + $scope.whatIfDetails[2].ceoApprovalValue;
	  	    				$sessionStorage.finalCDORev=$sessionStorage.totalCost/(1-($sessionStorage.ceoMargin/100));
	  	    				$scope.whatIfDetails[1].ceoApprovalValue = $sessionStorage.finalCDORev;
	  	    				$scope.whatIfDetails[5].ceoApprovalValue = $sessionStorage.finalCDORev - $sessionStorage.totalCDOCost;
	  	    				$scope.whatIfDetails[3].ceoApprovalValue = $scope.whatIfDetails[5].ceoApprovalValue + $scope.whatIfDetails[4].ceoApprovalValue;
	  	    				$sessionStorage.ceogrossMarginPer=$scope.whatIfDetails[3].ceoApprovalValue/$scope.whatIfDetails[1].ceoApprovalValue;
	  	    				//$scope.whatIfDetails[6].ceoApprovalValue=($scope.whatIfDetails[1].ceoApprovalValue - $scope.whatIfDetails[2].ceoApprovalValue - $scope.whatIfDetails[4].ceoApprovalValue)/$scope.whatIfDetails[1].ceoApprovalValue;
	  	    				$scope.whatIfDetails[0].ceoApprovalValue=($sessionStorage.ceogrossMarginPer);
	  	    				$scope.revenueCEOPostDiscount=$scope.whatIfDetails[1].ceoApprovalValue * (1-($scope.whatIfDetails[7].ceoApprovalValue));
	  	    				$scope.whatIfDetails[8].ceoApprovalValue=($scope.revenueCEOPostDiscount - $scope.whatIfDetails[2].ceoApprovalValue - $scope.whatIfDetails[4].ceoApprovalValue)/$scope.revenueCEOPostDiscount;
	  	    				$scope.whatIfDetails[9].ceoApprovalValue=$scope.whatIfDetails[1].ceoApprovalValue/$sessionStorage.totalHours;
	  	    				$scope.whatIfDetails[11].ceoApprovalValue=$scope.whatIfDetails[1].ceoApprovalValue/(($sessionStorage.onsiteTotalHours * 3)+$sessionStorage.offshoreTotalHours);
	  	    				$scope.whatIfDetails[10].ceoApprovalValue=$scope.whatIfDetails[11].ceoApprovalValue * 3;*/
	  	    				/*$scope.whatIfDetails[10].buhThresholdValue=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;
	  	    				$scope.whatIfDetails[10].ceoApprovalValue=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;
	  	    				$scope.whatIfDetails[10].globalRateCardValue=FXRiskOffshoreabs+Contingencyriskabs+SLARiskabs;*/
	  	    				
	  	    				//$scope.whatIfDetails[11].buhThresholdValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue)/$scope.whatIfDetails[1].userInputValue;
	  	    				//$scope.whatIfDetails[12].buhThresholdValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue))/($scope.whatIfDetails[1].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue));
	  	    				//$scope.whatIfDetails[11].ceoApprovalValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue)/$scope.whatIfDetails[1].userInputValue;
	  	    				//$scope.whatIfDetails[12].ceoApprovalValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue))/($scope.whatIfDetails[1].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue));
	  	    				//$scope.whatIfDetails[11].globalRateCardValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue)/$scope.whatIfDetails[1].userInputValue;
	  	    				//$scope.whatIfDetails[12].globalRateCardValue=($scope.whatIfDetails[1].userInputValue-$scope.whatIfDetails[2].userInputValue-$scope.whatIfDetails[4].userInputValue-$scope.whatIfDetails[10].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue))/($scope.whatIfDetails[1].userInputValue-($scope.whatIfDetails[1].userInputValue*$scope.whatIfDetails[7].userInputValue));
	  	    				
	  	    				
	  	    				console.log($scope.whatIfDetails);
	  	    				
	  	    				angular.forEach($scope.whatIfDetails,function(value,key){
	  	    					var rev=$scope.whatIfDetails[1].userInputValue;
	  	    					$scope.whatIfDetails[key].dispre=$scope.whatIfDetails[1].userInputValue - $sessionStorage.msaRoles
	  	    					$scope.whatIfDetails[key].diff=($scope.whatIfDetails[key].dispre / $sessionStorage.msaRoles)*100;
	  	    				});
	  	    				console.log("After adding diff");
	  	    				console.log($scope.whatIfDetails);
	  	    				
	  	    				}
	  	    			
	    		}
	    		else if($scope.inputTypeModel==0 || $scope.inputTypeModel==undefined )
				{
				BootstrapDialog.show({
    	            title : 'Fixed Price What If',
    	            type : BootstrapDialog.TYPE_DANGER,
    	            message : "Please Select Input Type.",
    	            closable : false,
    	            buttons : [{
    	                   label : 'OK',
    	                   action : function(dialogRef) {
    	                         dialogRef.close();
    	                        
    	                         
    	                   }
    	            }]
    	      });
				}
	    		else 
	    			{
	    			BootstrapDialog.show({
	    	            title : 'Fixed Price What If',
	    	            type : BootstrapDialog.TYPE_DANGER,
	    	            message : "Please Enter Value for Calculations.",
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
	    	
	    	$scope.save = function(whatifDeal){
	    		$scope.onSave= true;
	    		$scope.isSave= true;
	    			if(whatifDeal.tranChargeable.$valid==true && whatifDeal.tranConsiderCostType.$valid==true && whatifDeal.tranCostType.$valid==true &&
	    					whatifDeal.tranTotalEfforts.$valid==true && whatifDeal.warConsiderCostType.$valid==true 
	    					&& whatifDeal.warProvided.$valid==true && whatifDeal.warrTotalEffortsType.$valid==true && whatifDeal.warrchargeable.$valid==true
	    					  && whatifDeal.warrcostType.$valid==true )
	    					{
	    						$scope.onSave= false;	
	    						if($scope.isStaffingComplete==1)
	    							{
	    								$scope.saveWhatIfData();
	    							}
	    						else{
	    							BootstrapDialog.show({
	    			    	            title : 'Fixed Price What If',
	    			    	            type : BootstrapDialog.TYPE_DANGER,
	    			    	            message : "Please fill data for all the Years in Staffing screen..",
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
	    		};
	    		
	   
	    	    $scope.saveWhatIfData = function(){
	    	    	if($scope.whatIfCalDetails==undefined){
	    	    	$scope.getWhatIfCalculationData(rpVrsId);
	    	    }
	    	    	$scope.whatIfContractorTerms=[];
	    	    	$scope.whatIfRpDealData=[];
	    	    	$scope.whatIfCostBreakupData=[];
	    		    console.log($scope.whatIfDetails);
	    		    
	    		  
	    		    	$scope.whatIfRpDealData.push({
	    		    		
	    		    		"estimatedRevenue":$scope.whatIfDetails[0].userInputValue,
	    		    		"directCost":$scope.whatIfDetails[1].userInputValue,
	    		    		"gmPercentage":$scope.whatIfDetails[7].userInputValue,
	    		    		"gmAfterProjectSpecificCost":$scope.whatIfDetails[4].userInputValue,
	    		    		//:$scope.whatIfDetails[8].userInputValue,
	    		    		"gmAfterVolumeDiscount":($scope.revenuePostDiscount - $scope.whatIfDetails[1].userInputValue - $scope.whatIfDetails[2].userInputValue)/$scope.revenuePostDiscount,	
	    		    		"projectMargin":$scope.whatIfDetails[3].userInputValue,
	    		    		"projectMarginPercentage":$scope.whatIfDetails[4].userInputValue,
	    		    		"pminclrisk":$scope.whatIfDetails[5].userInputValue,
	    		    		"pminclRiskVD":$scope.whatIfDetails[7].userInputValue,
	    		    		"rpDealVersionId":rpVrsId
	    		    	});
	    		    	
	    		    
	    		    	
	    		    	//$scope.whatIfCalDetails[0].currentRevenue=$sessionStorage.revvalue;
	    		    	//$scope.whatIfCalDetails[0].revenueOrMarginFlag=$sessionStorage.selectionValue;
	    		    	
	    		    	$scope.getWhatIfCalculationData(rpVrsId);
	    		    	console.log($scope.whatIfCalDetails);
	    		    	
	    		    	$scope.whatIfCalDetails[0].currentRevenue=$sessionStorage.revvalue;
	    		    	$scope.whatIfCalDetails[0].revenueOrMarginFlag=$sessionStorage.selectionValue;
	    		    	
	    		    	if($scope.whatifDeal.tranCostTypeModel != null){
	    		    		var isTransitionChargeable = $.grep($scope.selectTranCostType, function (cstType) {
	    		    			                    return cstType.id == $scope.whatifDeal.tranCostTypeModel;
	    		    			                })[0].id;
	    		    	}
	    		    	
	    		    	if($scope.whatifDeal.tranConsiderCostTypeModel != null){
	    		    		var isEffortsConsideredForCosting = $.grep($scope.selectTranConsiderCostType, function (cstType) {
	    		    			                    return cstType.id == $scope.whatifDeal.tranConsiderCostTypeModel;
	    		    			                })[0].id;
	    		    	}
	    		    	
	    		    	
	    		    	if($scope.whatifDeal.warProvidedModel != null){
	    		    		var isWarrantyProvided = $.grep($scope.selectWarConsiderCostType, function (warcstType) {
	    		    			                    return warcstType.id == $scope.whatifDeal.warProvidedModel;
	    		    			                })[0].id;
	    		    	}
	    		    	
	    		    	
	    		    	if($scope.whatifDeal.warConsiderCostTypeModel != null){
	    		    		var isWarrantyChargeable = $.grep($scope.selectWarCostType, function (warcstType) {
	    		    			                    return warcstType.id == $scope.whatifDeal.warConsiderCostTypeModel;
	    		    			                })[0].id;
	    		    	}
	    		    	
	    		    	if($scope.whatifDeal.warrcostTypeModel != null){
	    		    		var isWarrentyEffortsConsideredForCosting = $.grep($scope.selectWarEffortsConsiderCostType, function (warcstType) {
	    		    			                    return warcstType.id == $scope.whatifDeal.warrcostTypeModel;
	    		    			                })[0].id;
	    		    	}
	    		    	
	    		    	
	    		    	
	    		    	$scope.whatIfContractorTerms.push({
	    		    		"isTransitionChargeable" : isTransitionChargeable,
	    		    		"chargeableTransitionPercent":$scope.whatifDeal.tranChargeableModel,
	    		    		"isEffortsConsideredForCosting":isEffortsConsideredForCosting,
	    		    		"totalTransitionEfforts": $scope.whatifDeal.tranTotalEffortsModel,
	    		    		"isWarrantyProvided" : isWarrantyProvided,
	    		    		"isWarrantyChargeable":isWarrantyChargeable,
	    		    		"chargeableWarrantyPercent":$scope.whatifDeal.warrchargeableModel,
	    		    		"isWarrentyEffortsConsideredForCosting":isWarrentyEffortsConsideredForCosting,
	    		    		"totalWarrantyEfforts":$scope.whatifDeal.warrTotalEffortsTypeModel,
	    		    		"rpDealVersionId":rpVrsId
	    		    		
	    		    	});
	    		    	
	    		    	$scope.whatIfCostBreakupData.push({
	    		    		"yearTotal":$scope.whatIfDetails[1].userInputValue,
	    		    		"versionId":rpVrsId
	    		    	});
	    		    	
	    		    	var marker={
	    		    			"whatIfDetails" : $scope.whatIfDetails,
	    		    			"whatIfCalDetails":$scope.whatIfCalDetails,
	    		    			"whatIfContractorTerms":$scope.whatIfContractorTerms,
	    		    			"whatIfRpDealData":$scope.whatIfRpDealData,
	    		    			"whatIfCostBreakupData":$scope.whatIfCostBreakupData
	    		    	};
	    		    	console.log(marker);
	    		    	
	    		    	var updateFpDealWhatIfDetails = function(response) {
	    		    		
	    		    		console.log(response);
	    		    		if(response.status == 200) 
	    		    		{
	    		    			if($scope.RedToSubmitSave==1)
	    		    				{
	    		    				BootstrapDialog.show({
		    		    				title : 'Fixed Price What If',
		    		    				type : BootstrapDialog.TYPE_PRIMARY,
		    		    				message : "Fixed Deal What If details saved and submitted successfully.",
		    		    				closable : false,
		    		    				buttons : [{
		    		    					label : 'OK',
		    		    					action : function(dialogRef) {
		    		    						dialogRef.close();
		    		    						$scope.getUpdatedApprovalMatrix(rpVrsId);
		    		    						$window.location.reload();
		    		    						
		    		    					}
		    		    				}]
		    		    			});
	    		    				}
	    		    			else{
	    		    				if($sessionStorage.currentApprovalStatusId==1){
	    		    				BootstrapDialog.show({
	    		    					title : 'Fixed Price What If',
	    		    					type : BootstrapDialog.TYPE_PRIMARY,
	    		    					message : "Fixed Deal What If Details Updated Successfully.",
	    		    					closable : false,
	    		    					buttons : [{
	    		    						label : 'OK',
	    		    						action : function(dialogRef) {
	    		    							dialogRef.close();
	    		    							//$window.location.reload();
	    		    							
	    		    						}
	    		    					}]
	    		    				});
	    		    				}
	    		    			}
	    		    			
	    		    		} 
	    		    		else {
	    		    			if($sessionStorage.currentApprovalStatusId==1){
	    		    			BootstrapDialog.show({
	    		    				title : 'Fixed Price What If',
	    		    				type : BootstrapDialog.TYPE_DANGER,
	    		    				message : "Currently facing technical issue.",
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
	    		    	WebServiceFactory.updateFpDealWhatIfDetails(marker).then(updateFpDealWhatIfDetails);
	    		    	console.log($scope.marker);
	    		   
	    	   };
	    	   
	    	   
	    	   
	    	   var getLeadershipDetails = function(response) {
					//alert("inside getLeadershipDetails");
					console.log("getLeadershipDetails");
					console.log(response);
				    $scope.leaderApproverNameDetails = response.data;
				};
				WebServiceFactory.getLeadershipDetails().then(getLeadershipDetails);
				
				$scope.getVerticalDetails = function(verticalId){
					var getVerticalDetails = function(response) {
					//	alert("inside getVerticalDetails");
						console.log("getVerticalDetails");
						console.log(response);
					    $scope.verticalDetails = response.data;
					};
					WebServiceFactory.getVerticalDetails(verticalId).then(getVerticalDetails);
				}
				
	    	   
	    	   
	    	   $scope.getFPDealSummaryDetails = function(user) {
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
		 	 		 $scope.user=user;
		 	 		 if($scope.versionDetails!=undefined)
		 	 		 { 

		 	 			 if($scope.versionDetails[0] != undefined) {
		 	 			 if($scope.versionDetails[0].currentApproverId == $scope.user)
		 	 			 {
		 	 				$scope.isOldApprovercomment=true;
		 	 				 $scope.isApprovercomment=true;
		 	 			 }	else if($scope.proxyUserId !=undefined){
		 	 				 if($scope.proxyUserId != null) {
		 	 					 if($scope.versionDetails[0].currentApproverId == $scope.proxyUserId){
		 	 						 $scope.isApprovercomment=true;
		 	 					 }  else if($scope.Approver == true) {
		 	 						 $scope.isOldApprovercomment=false;
		 	 						 $scope.isApprovercomment=true;
		 	 					 }	
		 	 					 else if($scope.Approver == true) {
		 		 	 				$scope.isOldApprovercomment=false;
		 		 	 				 $scope.isApprovercomment=true;
		 		 	 			 }	
		 		 	 			 else {
		 		 	 				$scope.isOldApprovercomment=true;
		 		 	 				 $scope.isApprovercomment=false;
		 		 	 			 }
		 	 				 } 
		 	 				 else if($scope.Approver == true) {
			 	 				$scope.isOldApprovercomment=false;
			 	 				 $scope.isApprovercomment=true;
			 	 			 }	
			 	 			 else {
			 	 				$scope.isOldApprovercomment=true;
			 	 				 $scope.isApprovercomment=false;
			 	 			 }
		 	 			 }
		 	 			 else if($scope.Approver == true) {
		 	 				$scope.isOldApprovercomment=false;
		 	 				 $scope.isApprovercomment=true;
		 	 			 }	
		 	 			 else {
		 	 				$scope.isOldApprovercomment=true;
		 	 				 $scope.isApprovercomment=false;
		 	 			 }
		 	 		 }
		    	 }
		 	 	}
	    	   
	    		$scope.addVersionData  = function(data) {
					
					
					$scope.selectedObj.push ({
						"rpDealVersionId" : data.rpDealVersionId
						});
					console.log("Checked data");
					console.log($scope.selectedObj);
				}
	    	   
	    	   $scope.getApprovalComments=function(user,selectionTypeAR){
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
	    		   	$scope.selectionTypeAR=selectionTypeAR;
					$scope.commentbox= true;
					
					if($scope.whatifDeal.approvalStatus == 3 && userType == 'GFT') {
 	 					$scope.rainbowApprovalPanel = false;
 	 				} else {
 	 					$scope.rainbowApprovalPanel = true;
 	 				}
				};
				
				$scope.submitApproverData  = function(user) {
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
				if($scope.selectionTypeAR==3)	
					{
					
					if(userType == 'GFT') {
						$sessionStorage.fpraibowApprovalLevel = $scope.whatifDeal.rainbowApprovalLevel;
					} else {
						$sessionStorage.fpraibowApprovalLevel= $scope.versionDetails[0].rainbowApprovalFlag;
					}
					
					if($scope.selectedObj.length!=0)
					{
					if($scope.whatifDeal.currentApproverCommentModel!="" && $scope.whatifDeal.currentApproverCommentModel!=undefined )
						{
						WebServiceFactory.getWhatIfEffortData(rpVrsId).then(getWhatIfEffortData);
  	    				if($sessionStorage.fpProjectTypeId == 1 || $sessionStorage.fpProjectTypeId== 2)
  		    			{
  		    				angular.forEach($scope.whatIfEffortDetails,function(value,key)
  		    						{
  		    						if($scope.whatIfEffortDetails[key].locationDescription == 'Offshore CH %')
  		    							{
  		    								if($scope.whatIfEffortDetails[key].effortsTotal < 0.15)
  		    									{
  		    									BootstrapDialog.show({
  		    										title: 'FP Deal Creation - What If Main',
  		    										type : BootstrapDialog.TYPE_DANGER,
  		    										message : 'Offshore Campus hire mandate of 15% have not met for Development Project <br> Do you want to proceed with Approval?',
  		    										closable: true,
  		    										buttons: [{
  		    											label: 'Yes',
  		    											cssClass: 'btn-primary',
  		    											action: function(dialogRef) {
  		    												
  		    												if($sessionStorage.rainbowLevelFlag==1 && $sessionStorage.fpraibowApprovalLevel!=undefined)
  		    													{
  		    													
  		    													var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
  		    													var comment = angular.element(approverId).val();
  		    													
  		    													if ($.trim(comment) == '') {
  		    														return false;
  		    													}
  		    													else
  		    													{
  		    														$scope.whatifDeal.currentApproverCommentModel = "["+user+"]: " + comment;
  		    														$scope.$apply();
  		    														
  		    														
  		    														
  		    														if($scope.whatifDeal.approverCommentModel){
  		    															var commnetBoxTxt = "["+user+"]: " + comment;
  		    															$scope.whatifDeal.approverCommentModel += '\n'+ commnetBoxTxt;
  		    															$scope.$apply();
  		    														}
  		    														else{
  		    															$scope.whatifDeal.approverCommentModel = "["+user+"]: " + comment;
  		    															$scope.$apply();
  		    														}
  		    														
  		    														if($scope.whatifDeal.approverCommentModel.length > 700)
  		    														{
  		    															var commentLen = $scope.whatifDeal.approverCommentModel.length;
  		    															var SkipChars = commentLen - 700;					
  		    															$scope.whatifDeal.approverCommentModel = $scope.whatifDeal.approverCommentModel.substring(SkipChars, commentLen);
  		    															$scope.$apply();
  		    														}
  		    													}
  		    													
  		    													angular.forEach($scope.selectedObj,function(value,key)
  		    															{
  		    														$scope.selectedObj[key].approverId=user;
  		    														$scope.selectedObj[key].approvalStatus=$scope.whatifDeal.approvalStatus;
  		    														$scope.selectedObj[key].approvalComments=$scope.whatifDeal.currentApproverCommentModel;
  		    														$scope.selectedObj[key].approvalDate=$scope.currentDate;
  		    															});
  		    													
  		    													if($scope.versionDetails[0].level1ApproverRoleId!=null){
  		    														$scope.setRoleId = 1;
  		    													}
  		    													if($scope.versionDetails[0].level2ApproverRoleId!=null){
  		    														$scope.setRoleId = 2;
  		    													}
  		    													if($scope.versionDetails[0].level3ApproverRoleId!=null){
  		    														$scope.setRoleId = 3;
  		    													}
  		    													if($scope.versionDetails[0].level4ApproverRoleId!=null){
  		    														$scope.setRoleId = 4;
  		    													}
  		    													if($scope.versionDetails[0].level5ApproverRoleId!=null){
  		    														$scope.setRoleId = 5;
  		    													}
  		    													if($scope.versionDetails[0].level6ApproverRoleId!=null){
  		    														$scope.setRoleId = 6;
  		    													}
  		    													if($scope.versionDetails[0].level7ApproverRoleId!=null){
  		    														$scope.setRoleId = 7;
  		    													}
  		    													if($scope.versionDetails[0].level8ApproverRoleId!=null){
  		    														$scope.setRoleId = 8;
  		    													}
  		    													if($scope.versionDetails[0].level9ApproverRoleId!=null){
  		    														$scope.setRoleId = 9;
  		    													}
  		    													
  		    													//$scope.maxRoleId = $scope.setRoleId;
  		    													
  		    													//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
  		    													
  		    													if(($scope.versionDetails[0].currentApprovalLevel+1) == 2){
  		    														$scope.getLanid = $scope.versionDetails[0].level2ApproverRoleId;
  		    														$scope.statusIndicator = $scope.versionDetails[0].level2ApproverId.description;
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 3){
  		    														$scope.getLanid = $scope.versionDetails[0].level3ApproverRoleId;
  		    														if($scope.versionDetails[0].level3ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level3ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 4){
  		    														$scope.getLanid = $scope.versionDetails[0].level4ApproverRoleId;
  		    														if($scope.versionDetails[0].level4ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level4ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 5){
  		    														$scope.getLanid = $scope.versionDetails[0].level5ApproverRoleId;
  		    														if($scope.versionDetails[0].level5ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level5ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														}
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 6){
  		    														$scope.getLanid = $scope.versionDetails[0].level6ApproverRoleId;
  		    														if($scope.versionDetails[0].level6ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level6ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														}
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 7){
  		    														$scope.getLanid = $scope.versionDetails[0].level7ApproverRoleId;
  		    														if($scope.versionDetails[0].level7ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level7ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 8){
  		    														$scope.getLanid = $scope.versionDetails[0].level8ApproverRoleId;
  		    														if($scope.versionDetails[0].level8ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level8ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 9){
  		    														$scope.getLanid = $scope.versionDetails[0].level9ApproverRoleId;
  		    														if($scope.versionDetails[0].level9ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level9ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
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
  		    														$scope.lanId = "RiskManagers";
  		    														$scope.roleId = 5;
  		    													}
  		    													else if($scope.getLanid == 6){
  		    														$scope.lanId = "GFT";
  		    														$scope.roleId = 6;
  		    													}
  		    													else if($scope.getLanid == 7){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  		    														$scope.roleId = 7;
  		    													}
  		    													else if($scope.getLanid == 8){
  		    														$scope.lanId = $scope.verticalDetails[0].buHeadId;
  		    														$scope.roleId = 8;
  		    													}
  		    													else if($scope.getLanid == 9){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  		    														$scope.roleId = 9;
  		    													}
  		    													else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
  		    														$scope.roleId = 10;
  		    													}
  		    													else if($scope.getLanid == 11 && $scope.leadershipDetails[0].leaderId){
  		    														$scope.lanId = $scope.leadershipDetails[2].leaderLanID;
  		    														$scope.roleId = 11;
  		    													}
  		    													else if($scope.getLanid == 12 && $scope.leaderApproverNameDetails[0].leaderId){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[3].leaderLanID;
  		    														$scope.roleId = 12;
  		    													}
  		    													else if($scope.getLanid == 13 && $scope.leaderApproverNameDetails[0].leaderId){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[4].leaderLanID;
  		    														$scope.roleId = 13;
  		    														$scope.statusIndicator = 'BU Head';
  		    													}
  		    													
  		    													//if($scope.setRoleId == $scope.getLanid){
  		    													/*	if($scope.getLanid == null){
  		    														
  		    														angular.forEach($scope.selectedObj,function(value,key)
  		    																{
  		    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  		    																	$scope.selectedObj[key].currentApproverId=null;
  		    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  		    																	$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
  		    																	$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  		    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  		    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  		    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
  		    																	
  		    																});
  		    														
  		    													}
  		    													
  		    													
  		    													else*/ if($scope.whatifDeal.approvalStatus == 3){
  		    														
  		    														if($scope.getLanid == null){
  		    															
  		    															angular.forEach($scope.selectedObj,function(value,key)
  		    																	{
  		    																$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  		    																$scope.selectedObj[key].currentApproverId=null;
  		    																$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  		    																$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
  		    																$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  		    																$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  		    																$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  		    																$scope.selectedObj[key].createdBy=$scope.createdBy;
  		    																$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
  		    																$scope.selectedObj[key].approvalFlag = $scope.whatifDeal.rainbowApprovalLevel;
  		    																	});
  		    														} else {
  		    															angular.forEach($scope.selectedObj,function(value,key)
  		    																	{
  		    																$scope.selectedObj[key].currentApprovalStatus=2;
  		    																$scope.selectedObj[key].currentApproverId=$scope.lanId;
  		    																$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  		    																$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel+1;
  		    																$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  		    																$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  		    																$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  		    																$scope.selectedObj[key].createdBy=$scope.createdBy;
  		    																$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
  		    																$scope.selectedObj[key].approvalFlag = $scope.whatifDeal.rainbowApprovalLevel;
  		    																	});
  		    														}	
  		    													}
  		    													
  		    													else if($scope.whatifDeal.approvalStatus == 4){
  		    														$scope.statusIndicator = "Recycled";
  		    														angular.forEach($scope.selectedObj,function(value,key)
  		    																{
  		    															$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  		    															$scope.selectedObj[key].currentApproverId="";
  		    															$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  		    															$scope.selectedObj[key].currentApprovalLevel="";
  		    															$scope.selectedObj[key].currentApproverRoleId="";
  		    															$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  		    															$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  		    															$scope.selectedObj[key].createdBy=$scope.createdBy;
  		    															
  		    																});
  		    														
  		    													}
  		    													
  		    													$scope.updatedAuditDetails=1;
  		    													
  		    													$http({
  		    														method: 'POST',
  		    														url: contextPath+"/RightPrice-DAS/updateFPApprover",
  		    														dataType: 'json',
  		    														data: angular.toJson($scope.selectedObj),  
  		    														headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
  		    													}).
  		    													then(function(data) {
  		    														
  		    														if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
  		    															BootstrapDialog.show({
  		    																title : 'FP Deal Creation - What If Main',
  		    																type : BootstrapDialog.TYPE_DANGER,
  		    																message : 'Request Rejected SucessFully',
  		    																closable : false,
  		    																buttons : [{
  		    																	label : 'OK',
  		    																	action : function(dialogRef) {
  		    																		dialogRef.close();
  		    																		window.location = "FPDealCreationWhatIfApplicationwise";
  		    																	}
  		    																}]
  		    															});
  		    															$scope.approvalcomment = true;
  		    														}
  		    														else if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
  		    															BootstrapDialog.show({
  		    																title : 'FP Deal Creation - What If Main',
  		    																type : BootstrapDialog.TYPE_PRIMARY,
  		    																message : 'Request Approved SucessFully',
  		    																closable : false,
  		    																buttons : [{
  		    																	label : 'OK',
  		    																	action : function(dialogRef) {
  		    																		dialogRef.close();
  		    																		window.location = "FPDealCreationWhatIfApplicationwise";
  		    																	}
  		    																}]
  		    															});
  		    															$scope.approvalcomment = true;
  		    														}
  		    														else{
  		    															BootstrapDialog.show({
  		    																title : 'FP Deal Creation - What If Main',
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
  		    														
  		    													});
  		    													
  		    													
  		    													/*var submitApproverData = function(response) {
  		    														$scope.updatedAuditDetails=1;
  		    														if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
  		    															BootstrapDialog.show({
  		    																title : 'FP Deal Creation - What If Main',
  		    																type : BootstrapDialog.TYPE_DANGER,
  		    																message : 'Request Rejected SucessFully',
  		    																closable : false,
  		    																buttons : [{
  		    																	label : 'OK',
  		    																	action : function(dialogRef) {
  		    																		dialogRef.close();
  		    																		window.location = "FPDealCreationWhatIfApplicationwise";
  		    																	}
  		    																}]
  		    															});
  		    															$scope.approvalcomment = true;
  		    														}
  		    														else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
  		    															BootstrapDialog.show({
  		    																title : 'FP Deal Creation - What If Main',
  		    																type : BootstrapDialog.TYPE_PRIMARY,
  		    																message : 'Request Approved SucessFully',
  		    																closable : false,
  		    																buttons : [{
  		    																	label : 'OK',
  		    																	action : function(dialogRef) {
  		    																		dialogRef.close();
  		    																		window.location = "FPDealCreationWhatIfApplicationwise";
  		    																	}
  		    																}]
  		    															});
  		    															$scope.approvalcomment = true;
  		    														}
  		    														else{
  		    															BootstrapDialog.show({
  		    																title : 'FP Deal Creation - What If Main',
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
  		    													WebServiceFactory.submitApproverData($scope.selectedObj).then(submitApproverData);*/
  		    													dialogRef.close();
  		    													
  		    													
  		    													}
  		    											else if($sessionStorage.rainbowLevelFlag==1 && $sessionStorage.fpraibowApprovalLevel==undefined)
  		    						            		   {
  		    						            		   dialogRef.close();
  		    						            		   BootstrapDialog.show({
  		    					        					   title : 'FP Deal Creation What If Details',
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
		    														return false;
		    													}
		    													else
		    													{
		    														$scope.whatifDeal.currentApproverCommentModel = "["+user+"]: " + comment;
		    														$scope.$apply();
		    														
		    														
		    														
		    														if($scope.whatifDeal.approverCommentModel){
		    															var commnetBoxTxt = "["+user+"]: " + comment;
		    															$scope.whatifDeal.approverCommentModel += '\n'+ commnetBoxTxt;
		    															$scope.$apply();
		    														}
		    														else{
		    															$scope.whatifDeal.approverCommentModel = "["+user+"]: " + comment;
		    															$scope.$apply();
		    														}
		    														
		    														if($scope.whatifDeal.approverCommentModel.length > 700)
		    														{
		    															var commentLen = $scope.whatifDeal.approverCommentModel.length;
		    															var SkipChars = commentLen - 700;					
		    															$scope.whatifDeal.approverCommentModel = $scope.whatifDeal.approverCommentModel.substring(SkipChars, commentLen);
		    															$scope.$apply();
		    														}
		    													}
		    													
		    													angular.forEach($scope.selectedObj,function(value,key)
		    															{
		    														$scope.selectedObj[key].approverId=user;
		    														$scope.selectedObj[key].approvalStatus=$scope.whatifDeal.approvalStatus;
		    														$scope.selectedObj[key].approvalComments=$scope.whatifDeal.currentApproverCommentModel;
		    														$scope.selectedObj[key].approvalDate=$scope.currentDate;
		    															});
		    													
		    													if($scope.versionDetails[0].level1ApproverRoleId!=null){
		    														$scope.setRoleId = 1;
		    													}
		    													if($scope.versionDetails[0].level2ApproverRoleId!=null){
		    														$scope.setRoleId = 2;
		    													}
		    													if($scope.versionDetails[0].level3ApproverRoleId!=null){
		    														$scope.setRoleId = 3;
		    													}
		    													if($scope.versionDetails[0].level4ApproverRoleId!=null){
		    														$scope.setRoleId = 4;
		    													}
		    													if($scope.versionDetails[0].level5ApproverRoleId!=null){
		    														$scope.setRoleId = 5;
		    													}
		    													if($scope.versionDetails[0].level6ApproverRoleId!=null){
		    														$scope.setRoleId = 6;
		    													}
		    													if($scope.versionDetails[0].level7ApproverRoleId!=null){
		    														$scope.setRoleId = 7;
		    													}
		    													if($scope.versionDetails[0].level8ApproverRoleId!=null){
		    														$scope.setRoleId = 8;
		    													}
		    													if($scope.versionDetails[0].level9ApproverRoleId!=null){
		    														$scope.setRoleId = 9;
		    													}
		    													
		    													//$scope.maxRoleId = $scope.setRoleId;
		    													
		    													//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
		    													
		    													if(($scope.versionDetails[0].currentApprovalLevel+1) == 2){
		    														$scope.getLanid = $scope.versionDetails[0].level2ApproverRoleId;
		    														$scope.statusIndicator = $scope.versionDetails[0].level2ApproverId.description;
		    													}
		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 3){
		    														$scope.getLanid = $scope.versionDetails[0].level3ApproverRoleId;
		    														if($scope.versionDetails[0].level3ApproverId != undefined) {
		    															$scope.statusIndicator = $scope.versionDetails[0].level3ApproverId.description;
		    														} else {
		    															$scope.statusIndicator = "Approved"
		    														} 
		    													}
		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 4){
		    														$scope.getLanid = $scope.versionDetails[0].level4ApproverRoleId;
		    														if($scope.versionDetails[0].level4ApproverId != undefined) {
		    															$scope.statusIndicator = $scope.versionDetails[0].level4ApproverId.description;
		    														} else {
		    															$scope.statusIndicator = "Approved"
		    														} 
		    													}
		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 5){
		    														$scope.getLanid = $scope.versionDetails[0].level5ApproverRoleId;
		    														if($scope.versionDetails[0].level5ApproverId != undefined) {
		    															$scope.statusIndicator = $scope.versionDetails[0].level5ApproverId.description;
		    														} else {
		    															$scope.statusIndicator = "Approved"
		    														}
		    													}
		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 6){
		    														$scope.getLanid = $scope.versionDetails[0].level6ApproverRoleId;
		    														if($scope.versionDetails[0].level6ApproverId != undefined) {
		    															$scope.statusIndicator = $scope.versionDetails[0].level6ApproverId.description;
		    														} else {
		    															$scope.statusIndicator = "Approved"
		    														}
		    													}
		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 7){
		    														$scope.getLanid = $scope.versionDetails[0].level7ApproverRoleId;
		    														if($scope.versionDetails[0].level7ApproverId != undefined) {
		    															$scope.statusIndicator = $scope.versionDetails[0].level7ApproverId.description;
		    														} else {
		    															$scope.statusIndicator = "Approved"
		    														} 
		    													}
		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 8){
		    														$scope.getLanid = $scope.versionDetails[0].level8ApproverRoleId;
		    														if($scope.versionDetails[0].level8ApproverId != undefined) {
		    															$scope.statusIndicator = $scope.versionDetails[0].level8ApproverId.description;
		    														} else {
		    															$scope.statusIndicator = "Approved"
		    														} 
		    													}
		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 9){
		    														$scope.getLanid = $scope.versionDetails[0].level9ApproverRoleId;
		    														if($scope.versionDetails[0].level9ApproverId != undefined) {
		    															$scope.statusIndicator = $scope.versionDetails[0].level9ApproverId.description;
		    														} else {
		    															$scope.statusIndicator = "Approved"
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
		    														$scope.lanId = "RiskManagers";
		    														$scope.roleId = 5;
		    													}
		    													else if($scope.getLanid == 6){
		    														$scope.lanId = "GFT";
		    														$scope.roleId = 6;
		    													}
		    													else if($scope.getLanid == 7){
		    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
		    														$scope.roleId = 7;
		    													}
		    													else if($scope.getLanid == 8){
		    														$scope.lanId = $scope.verticalDetails[0].buHeadId;
		    														$scope.roleId = 8;
		    													}
		    													else if($scope.getLanid == 9){
		    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
		    														$scope.roleId = 9;
		    													}
		    													else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
		    														$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
		    														$scope.roleId = 10;
		    													}
		    													else if($scope.getLanid == 11 && $scope.leadershipDetails[0].leaderId){
		    														$scope.lanId = $scope.leadershipDetails[2].leaderLanID;
		    														$scope.roleId = 11;
		    													}
		    													else if($scope.getLanid == 12 && $scope.leaderApproverNameDetails[0].leaderId){
		    														$scope.lanId = $scope.leaderApproverNameDetails[3].leaderLanID;
		    														$scope.roleId = 12;
		    													}
		    													else if($scope.getLanid == 13 && $scope.leaderApproverNameDetails[0].leaderId){
		    														$scope.lanId = $scope.leaderApproverNameDetails[4].leaderLanID;
		    														$scope.roleId = 13;
		    														$scope.statusIndicator = 'BU Head';
		    													}
		    													
		    													//if($scope.setRoleId == $scope.getLanid){
		    													/*	if($scope.getLanid == null){
		    														
		    														angular.forEach($scope.selectedObj,function(value,key)
		    																{
		    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
		    																	$scope.selectedObj[key].currentApproverId=null;
		    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
		    																	$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
		    																	$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
		    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
		    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
		    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
		    																	
		    																});
		    														
		    													}
		    													
		    													
		    													else*/ if($scope.whatifDeal.approvalStatus == 3){
		    														
		    														if($scope.getLanid == null){
		    															
		    															angular.forEach($scope.selectedObj,function(value,key)
		    																	{
		    																$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
		    																$scope.selectedObj[key].currentApproverId=null;
		    																$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
		    																$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
		    																$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
		    																$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
		    																$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
		    																$scope.selectedObj[key].createdBy=$scope.createdBy;
		    																$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
		    																$scope.selectedObj[key].approvalFlag = $scope.whatifDeal.rainbowApprovalLevel;
		    																	});
		    														} else {
		    															angular.forEach($scope.selectedObj,function(value,key)
		    																	{
		    																$scope.selectedObj[key].currentApprovalStatus=2;
		    																$scope.selectedObj[key].currentApproverId=$scope.lanId;
		    																$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
		    																$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel+1;
		    																$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
		    																$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
		    																$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
		    																$scope.selectedObj[key].createdBy=$scope.createdBy;
		    																$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
		    																$scope.selectedObj[key].approvalFlag = $scope.whatifDeal.rainbowApprovalLevel;
		    																	});
		    														}	
		    													}
		    													
		    													else if($scope.whatifDeal.approvalStatus == 4){
		    														$scope.statusIndicator = "Recycled";
		    														angular.forEach($scope.selectedObj,function(value,key)
		    																{
		    															$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
		    															$scope.selectedObj[key].currentApproverId="";
		    															$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
		    															$scope.selectedObj[key].currentApprovalLevel="";
		    															$scope.selectedObj[key].currentApproverRoleId="";
		    															$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
		    															$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
		    															$scope.selectedObj[key].createdBy=$scope.createdBy;
		    															
		    																});
		    														
		    													}
		    													
		    													$scope.updatedAuditDetails=1;
		    													
		    													$http({
		    														method: 'POST',
		    														url: contextPath+"/RightPrice-DAS/updateFPApprover",
		    														dataType: 'json',
		    														data: angular.toJson($scope.selectedObj),  
		    														headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    													}).
		    													then(function(data) {
		    														
		    														if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
		    															BootstrapDialog.show({
		    																title : 'FP Deal Creation - What If Main',
		    																type : BootstrapDialog.TYPE_DANGER,
		    																message : 'Request Rejected SucessFully',
		    																closable : false,
		    																buttons : [{
		    																	label : 'OK',
		    																	action : function(dialogRef) {
		    																		dialogRef.close();
		    																		window.location = "FPDealCreationWhatIfApplicationwise";
		    																	}
		    																}]
		    															});
		    															$scope.approvalcomment = true;
		    														}
		    														else if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
		    															BootstrapDialog.show({
		    																title : 'FP Deal Creation - What If Main',
		    																type : BootstrapDialog.TYPE_PRIMARY,
		    																message : 'Request Approved SucessFully',
		    																closable : false,
		    																buttons : [{
		    																	label : 'OK',
		    																	action : function(dialogRef) {
		    																		dialogRef.close();
		    																		window.location = "FPDealCreationWhatIfApplicationwise";
		    																	}
		    																}]
		    															});
		    															$scope.approvalcomment = true;
		    														}
		    														else{
		    															BootstrapDialog.show({
		    																title : 'FP Deal Creation - What If Main',
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
		    														
		    													});
		    													
		    													
		    													/*var submitApproverData = function(response) {
		    														$scope.updatedAuditDetails=1;
		    														if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
		    															BootstrapDialog.show({
		    																title : 'FP Deal Creation - What If Main',
		    																type : BootstrapDialog.TYPE_DANGER,
		    																message : 'Request Rejected SucessFully',
		    																closable : false,
		    																buttons : [{
		    																	label : 'OK',
		    																	action : function(dialogRef) {
		    																		dialogRef.close();
		    																		window.location = "FPDealCreationWhatIfApplicationwise";
		    																	}
		    																}]
		    															});
		    															$scope.approvalcomment = true;
		    														}
		    														else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
		    															BootstrapDialog.show({
		    																title : 'FP Deal Creation - What If Main',
		    																type : BootstrapDialog.TYPE_PRIMARY,
		    																message : 'Request Approved SucessFully',
		    																closable : false,
		    																buttons : [{
		    																	label : 'OK',
		    																	action : function(dialogRef) {
		    																		dialogRef.close();
		    																		window.location = "FPDealCreationWhatIfApplicationwise";
		    																	}
		    																}]
		    															});
		    															$scope.approvalcomment = true;
		    														}
		    														else{
		    															BootstrapDialog.show({
		    																title : 'FP Deal Creation - What If Main',
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
		    													WebServiceFactory.submitApproverData($scope.selectedObj).then(submitApproverData);*/
		    													dialogRef.close();
		    													
		    													
		    													
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
  		    								}
  		    								else{
  		    									BootstrapDialog.show({
  		    										title: 'FP Deal Creation - What If Main',
  		    										type : BootstrapDialog.TYPE_PRIMARY,
  		    										message : 'Do you want to Approve ?',
  		    										closable: true,
  		    										buttons: [{
  		    											label: 'Yes',
  		    											cssClass: 'btn-primary',
  		    											action: function(dialogRef) {
  		    													var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
  		    													var comment = angular.element(approverId).val();
  		    													
  		    													if ($.trim(comment) == '') {
  		    														return false;
  		    													}
  		    													else
  		    													{
  		    														$scope.whatifDeal.currentApproverCommentModel = "["+user+"]: " + comment;
  		    														$scope.$apply();
  		    														
  		    														
  		    													
  		    														if($scope.whatifDeal.approverCommentModel){
  		    															var commnetBoxTxt = "["+user+"]: " + comment;
  		    															$scope.whatifDeal.approverCommentModel += '\n'+ commnetBoxTxt;
  		    															$scope.$apply();
  		    														}
  		    														else{
  		    															$scope.whatifDeal.approverCommentModel = "["+user+"]: " + comment;
  		    															$scope.$apply();
  		    														}
  		    														
  		    														if($scope.whatifDeal.approverCommentModel.length > 700)
  		    														{
  		    															var commentLen = $scope.whatifDeal.approverCommentModel.length;
  		    															var SkipChars = commentLen - 700;					
  		    															$scope.whatifDeal.approverCommentModel = $scope.whatifDeal.approverCommentModel.substring(SkipChars, commentLen);
  		    															$scope.$apply();
  		    														}
  		    													}
  		    													
  		    													angular.forEach($scope.selectedObj,function(value,key)
  		    															{
  		    																$scope.selectedObj[key].approverId=user;
  		    																$scope.selectedObj[key].approvalStatus=$scope.whatifDeal.approvalStatus;
  		    																$scope.selectedObj[key].approvalComments=$scope.whatifDeal.currentApproverCommentModel;
  		    																$scope.selectedObj[key].approvalDate=$scope.currentDate;
  		    															});
  		    													
  		    													if($scope.versionDetails[0].level1ApproverRoleId!=null){
  		    														$scope.setRoleId = 1;
  		    													}
  		    													if($scope.versionDetails[0].level2ApproverRoleId!=null){
  		    														$scope.setRoleId = 2;
  		    													}
  		    													if($scope.versionDetails[0].level3ApproverRoleId!=null){
  		    														$scope.setRoleId = 3;
  		    													}
  		    													if($scope.versionDetails[0].level4ApproverRoleId!=null){
  		    														$scope.setRoleId = 4;
  		    													}
  		    													if($scope.versionDetails[0].level5ApproverRoleId!=null){
  		    														$scope.setRoleId = 5;
  		    													}
  		    													if($scope.versionDetails[0].level6ApproverRoleId!=null){
  		    														$scope.setRoleId = 6;
  		    													}
  		    													if($scope.versionDetails[0].level7ApproverRoleId!=null){
  		    														$scope.setRoleId = 7;
  		    													}
  		    													if($scope.versionDetails[0].level8ApproverRoleId!=null){
  		    														$scope.setRoleId = 8;
  		    													}
  		    													if($scope.versionDetails[0].level9ApproverRoleId!=null){
  		    														$scope.setRoleId = 9;
  		    													}
  		    													
  		    													//$scope.maxRoleId = $scope.setRoleId;
  		    													
  		    													//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
  		    													
  		    													if(($scope.versionDetails[0].currentApprovalLevel+1) == 2){
  		    														$scope.getLanid = $scope.versionDetails[0].level2ApproverRoleId;
  		    														$scope.statusIndicator = $scope.versionDetails[0].level2ApproverId.description;
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 3){
  		    														$scope.getLanid = $scope.versionDetails[0].level3ApproverRoleId;
  		    														if($scope.versionDetails[0].level3ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level3ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 4){
  		    														$scope.getLanid = $scope.versionDetails[0].level4ApproverRoleId;
  		    														if($scope.versionDetails[0].level4ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level4ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 5){
  		    														$scope.getLanid = $scope.versionDetails[0].level5ApproverRoleId;
  		    														if($scope.versionDetails[0].level5ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level5ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														}
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 6){
  		    														$scope.getLanid = $scope.versionDetails[0].level6ApproverRoleId;
  		    														if($scope.versionDetails[0].level6ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level6ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														}
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 7){
  		    														$scope.getLanid = $scope.versionDetails[0].level7ApproverRoleId;
  		    														if($scope.versionDetails[0].level7ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level7ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 8){
  		    														$scope.getLanid = $scope.versionDetails[0].level8ApproverRoleId;
  		    														if($scope.versionDetails[0].level8ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level8ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
  		    														} 
  		    													}
  		    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 9){
  		    														$scope.getLanid = $scope.versionDetails[0].level9ApproverRoleId;
  		    														if($scope.versionDetails[0].level9ApproverId != undefined) {
  		    															$scope.statusIndicator = $scope.versionDetails[0].level9ApproverId.description;
  		    														} else {
  		    															$scope.statusIndicator = "Approved"
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
  		    														$scope.lanId = "RiskManagers";
  		    														$scope.roleId = 5;
  		    													}
  		    													else if($scope.getLanid == 6){
  		    														$scope.lanId = "GFT";
  		    														$scope.roleId = 6;
  		    													}
  		    													else if($scope.getLanid == 7){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  		    														$scope.roleId = 7;
  		    													}
  		    													else if($scope.getLanid == 8){
  		    														$scope.lanId = $scope.verticalDetails[0].buHeadId;
  		    														$scope.roleId = 8;
  		    													}
  		    													else if($scope.getLanid == 9){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  		    														$scope.roleId = 9;
  		    													}
  		    													else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
  		    														$scope.roleId = 10;
  		    													}
  		    													else if($scope.getLanid == 11 && $scope.leaderApproverNameDetails[0].leaderId){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[2].leaderLanID;
  		    														$scope.roleId = 11;
  		    													}
  		    													else if($scope.getLanid == 12 && $scope.leaderApproverNameDetails[0].leaderId){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[3].leaderLanID;
  		    														$scope.roleId = 12;
  		    													}
  		    													else if($scope.getLanid == 13 && $scope.leaderApproverNameDetails[0].leaderId){
  		    														$scope.lanId = $scope.leaderApproverNameDetails[4].leaderLanID;
  		    														$scope.roleId = 13;
  		    														$scope.statusIndicator = 'BU Head';
  		    													}
  		    													
  		    													//if($scope.setRoleId == $scope.getLanid){
  		    												 if($scope.whatifDeal.approvalStatus == 3){
  		    													 
  		    													if($scope.getLanid == null){
	  		    														
	  		    														angular.forEach($scope.selectedObj,function(value,key)
	  		    																{
	  		    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
	  		    																	$scope.selectedObj[key].currentApproverId=null;
	  		    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
	  		    																	$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
	  		    																	$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
	  		    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
	  		    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
	  		    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
	  		    																	$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
	  		    																	
	  		    																});
		    														} else {
		    															angular.forEach($scope.selectedObj,function(value,key)
		    																	{
		    																$scope.selectedObj[key].currentApprovalStatus=2;
		    																$scope.selectedObj[key].currentApproverId=$scope.lanId;
		    																$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
		    																$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel+1;
		    																$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
		    																$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
		    																$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
		    																$scope.selectedObj[key].createdBy=$scope.createdBy;
		    																$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
 		    																$scope.selectedObj[key].approvalFlag = $scope.whatifDeal.rainbowApprovalLevel;
		    															});
		    															
		    														}
  		    														
  		    														
  		    													}
  		    													
  		    													else if($scope.whatifDeal.approvalStatus == 4){
  		    														$scope.statusIndicator = "Recycled";
  		    														angular.forEach($scope.selectedObj,function(value,key)
  		    																{
  		    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  		    																	$scope.selectedObj[key].currentApproverId="";
  		    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  		    																	$scope.selectedObj[key].currentApprovalLevel="";
  		    																	$scope.selectedObj[key].currentApproverRoleId="";
  		    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  		    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  		    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
  		    																	
  		    																});
  		    														
  		    													}
  		    													
  		    													$scope.updatedAuditDetails=1;
  		    													
  		    														$http({
  		    															method: 'POST',
  		    															url: contextPath+"/RightPrice-DAS/updateFPApprover",
  		    															dataType: 'json',
  		    															data: angular.toJson($scope.selectedObj),  
  		    															headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
  		    														}).
  		    														then(function(data) {
  		    															
  		    															if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
  		    																BootstrapDialog.show({
  		    																	title : 'FP Deal Creation - What If Main',
  		    																	type : BootstrapDialog.TYPE_DANGER,
  		    																	message : 'Request Rejected SucessFully',
  		    																	closable : false,
  		    																	buttons : [{
  		    																		label : 'OK',
  		    																		action : function(dialogRef) {
  		    																			dialogRef.close();
  		    																			window.location = "FPDealCreationWhatIfApplicationwise";
  		    																		}
  		    																	}]
  		    																});
  		    																$scope.approvalcomment = true;
  		    															}
  		    															else if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
  		    																BootstrapDialog.show({
  		    																	title : 'FP Deal Creation - What If Main',
  		    																	type : BootstrapDialog.TYPE_PRIMARY,
  		    																	message : 'Request Approved SucessFully',
  		    																	closable : false,
  		    																	buttons : [{
  		    																		label : 'OK',
  		    																		action : function(dialogRef) {
  		    																			dialogRef.close();
  		    																			window.location = "FPDealCreationWhatIfApplicationwise";
  		    																		}
  		    																	}]
  		    																});
  		    																$scope.approvalcomment = true;
  		    															}
  		    															else{
  		    																BootstrapDialog.show({
  		    																	title : 'FP Deal Creation - What If Main',
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
  		    														
  		    														});
  		    													
  		    													
  		    													
  		    													dialogRef.close();
  		    													
  		    											
  		    												
  		    											}
  		    										}, {
  		    											label: 'No',
  		    											cssClass: 'btn-primary',
  		    											action: function(dialog) {
  		    												dialog.close();
  		    											}
  		    										}]
  		    									});}
  		    								
  		    							}
  		    						});
  		    			}
  		    		else if($sessionStorage.fpProjectTypeId == 3 ||  $sessionStorage.fpProjectTypeId == 4)
  		    			{

  	    				angular.forEach($scope.whatIfEffortDetails,function(value,key)
  	    						{
  	    						if($scope.whatIfEffortDetails[key].locationDescription == 'Offshore CH %')
  	    							{
  	    								if($scope.whatIfEffortDetails[key].effortsTotal < 0.20)
  	    									{
  	    									BootstrapDialog.show({
  	    										title: 'FP Deal Creation - What If Main',
  	    										type : BootstrapDialog.TYPE_DANGER,
  	    										message : 'Offshore Campus hire mandate of 20% have not met for Maintenance Project <br> Do you want to proceed with Approval ?',
  	    										closable: true,
  	    										buttons: [{
  	    											label: 'Yes',
  	    											cssClass: 'btn-primary',
  	    											action: function(dialogRef) {
  	    													var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
  	    													var comment = angular.element(approverId).val();
  	    													
  	    													if ($.trim(comment) == '') {
  	    														return false;
  	    													}
  	    													else
  	    													{
  	    														$scope.whatifDeal.currentApproverCommentModel = "["+user+"]: " + comment;
  	    														$scope.$apply();
  	    														
  	    														
  	    													
  	    														if($scope.whatifDeal.approverCommentModel){
  	    															var commnetBoxTxt = "["+user+"]: " + comment;
  	    															$scope.whatifDeal.approverCommentModel += '\n'+ commnetBoxTxt;
  	    															$scope.$apply();
  	    														}
  	    														else{
  	    															$scope.whatifDeal.approverCommentModel = "["+user+"]: " + comment;
  	    															$scope.$apply();
  	    														}
  	    														
  	    														if($scope.whatifDeal.approverCommentModel.length > 700)
  	    														{
  	    															var commentLen = $scope.whatifDeal.approverCommentModel.length;
  	    															var SkipChars = commentLen - 700;					
  	    															$scope.whatifDeal.approverCommentModel = $scope.whatifDeal.approverCommentModel.substring(SkipChars, commentLen);
  	    															$scope.$apply();
  	    														}
  	    													}
  	    													
  	    													angular.forEach($scope.selectedObj,function(value,key)
  	    															{
  	    																$scope.selectedObj[key].approverId=user;
  	    																$scope.selectedObj[key].approvalStatus=$scope.whatifDeal.approvalStatus;
  	    																$scope.selectedObj[key].approvalComments=$scope.whatifDeal.currentApproverCommentModel;
  	    																$scope.selectedObj[key].approvalDate=$scope.currentDate;
  	    															});
  	    													
  	    													if($scope.versionDetails[0].level1ApproverRoleId!=null){
  	    														$scope.setRoleId = 1;
  	    													}
  	    													if($scope.versionDetails[0].level2ApproverRoleId!=null){
  	    														$scope.setRoleId = 2;
  	    													}
  	    													if($scope.versionDetails[0].level3ApproverRoleId!=null){
  	    														$scope.setRoleId = 3;
  	    													}
  	    													if($scope.versionDetails[0].level4ApproverRoleId!=null){
  	    														$scope.setRoleId = 4;
  	    													}
  	    													if($scope.versionDetails[0].level5ApproverRoleId!=null){
  	    														$scope.setRoleId = 5;
  	    													}
  	    													if($scope.versionDetails[0].level6ApproverRoleId!=null){
  	    														$scope.setRoleId = 6;
  	    													}
  	    													if($scope.versionDetails[0].level7ApproverRoleId!=null){
  	    														$scope.setRoleId = 7;
  	    													}
  	    													if($scope.versionDetails[0].level8ApproverRoleId!=null){
  	    														$scope.setRoleId = 8;
  	    													}
  	    													if($scope.versionDetails[0].level9ApproverRoleId!=null){
  	    														$scope.setRoleId = 9;
  	    													}
  	    													
  	    													
  	    													//$scope.maxRoleId = $scope.setRoleId;
  	    													
  	    													//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
  	    													
  	    													if(($scope.versionDetails[0].currentApprovalLevel+1) == 2){
  	    														$scope.getLanid = $scope.versionDetails[0].level2ApproverRoleId;
  	    														$scope.statusIndicator = $scope.versionDetails[0].level2ApproverId.description;
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 3){
  	    														$scope.getLanid = $scope.versionDetails[0].level3ApproverRoleId;
  	    														if($scope.versionDetails[0].level3ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level3ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 4){
  	    														$scope.getLanid = $scope.versionDetails[0].level4ApproverRoleId;
  	    														if($scope.versionDetails[0].level4ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level4ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 5){
  	    														$scope.getLanid = $scope.versionDetails[0].level5ApproverRoleId;
  	    														if($scope.versionDetails[0].level5ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level5ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														}
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 6){
  	    														$scope.getLanid = $scope.versionDetails[0].level6ApproverRoleId;
  	    														if($scope.versionDetails[0].level6ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level6ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														}
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 7){
  	    														$scope.getLanid = $scope.versionDetails[0].level7ApproverRoleId;
  	    														if($scope.versionDetails[0].level7ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level7ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 8){
  	    														$scope.getLanid = $scope.versionDetails[0].level8ApproverRoleId;
  	    														if($scope.versionDetails[0].level8ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level8ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 9){
  	    														$scope.getLanid = $scope.versionDetails[0].level8ApproverRoleId;
  	    														if($scope.versionDetails[0].level8ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level8ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
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
  	    														$scope.lanId = "RiskManagers";//$scope.verticalDetails[0].RiskManagersPersonId;
  	    														$scope.roleId = 5;
  	    													}
  	    													else if($scope.getLanid == 6){
  	    														$scope.lanId = "GFT";
  	    														$scope.roleId = 6;
  	    													}
  	    													else if($scope.getLanid == 7){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  	    														$scope.roleId = 7;
  	    													}
  	    													else if($scope.getLanid == 8){
  	    														$scope.lanId = $scope.verticalDetails[0].buHeadId;
  	    														$scope.roleId = 8;
  	    													}
  	    													else if($scope.getLanid == 9){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  	    														$scope.roleId = 9;
  	    													}
  	    													else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
  	    														$scope.roleId = 10;
  	    													}
  	    													else if($scope.getLanid == 11 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[2].leaderLanID;
  	    														$scope.roleId = 11;
  	    													}
  	    													else if($scope.getLanid == 12 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[3].leaderLanID;
  	    														$scope.roleId = 12;
  	    													}
  	    													else if($scope.getLanid == 13 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[4].leaderLanID;
  	    														$scope.roleId = 13;
  	    														$scope.statusIndicator = 'BU Head';
  	    													}
  	    													
  	    													//if($scope.setRoleId == $scope.getLanid){
  	    												/*	if($scope.getLanid == null){
  	    														
  	    														angular.forEach($scope.selectedObj,function(value,key)
  	    																{
  	    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  	    																	$scope.selectedObj[key].currentApproverId=null;
  	    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  	    																	$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
  	    																	$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  	    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  	    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  	    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
  	    																	
  	    																});
  	    														
  	    													}
  	    													
  	    													
  	    													else*/ if($scope.whatifDeal.approvalStatus == 3){
  	    														if($scope.getLanid == null){
  	  	    														
  	  	    														angular.forEach($scope.selectedObj,function(value,key)
  	  	    																{
  	  	    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  	  	    																	$scope.selectedObj[key].currentApproverId=null;
  	  	    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  	  	    																	$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
  	  	    																	$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  	  	    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  	  	    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  	  	    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
  	  	    																	$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
  	  	    																});
  	  	    														
  	  	    													} else {
  	  	    														angular.forEach($scope.selectedObj,function(value,key)
  	  	    																{
  	  	    															$scope.selectedObj[key].currentApprovalStatus=2;
  	  	    															$scope.selectedObj[key].currentApproverId=$scope.lanId;
  	  	    															$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  	  	    															$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel+1;
  	  	    															$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  	  	    															$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  	  	    															$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  	  	    															$scope.selectedObj[key].createdBy=$scope.createdBy;
  	  	    															$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
    																    $scope.selectedObj[key].approvalFlag = $scope.whatifDeal.rainbowApprovalLevel;
  	  	    																});
  	  	    													}
  	    													}
  	    													
  	    													else if($scope.whatifDeal.approvalStatus == 4){
  	    														$scope.statusIndicator = "Recycled";
  	    														angular.forEach($scope.selectedObj,function(value,key)
  	    																{
  	    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  	    																	$scope.selectedObj[key].currentApproverId="";
  	    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  	    																	$scope.selectedObj[key].currentApprovalLevel="";
  	    																	$scope.selectedObj[key].currentApproverRoleId="";
  	    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  	    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  	    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
  	    																	
  	    																});
  	    														
  	    													}
  	    													
  	    													$scope.updatedAuditDetails=1;
  	    													
  	    														$http({
  	    															method: 'POST',
  	    															url: contextPath+"/RightPrice-DAS/updateFPApprover",
  	    															dataType: 'json',
  	    															data: angular.toJson($scope.selectedObj),  
  	    															headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
  	    														}).
  	    														then(function(data) {
  	    															
  	    															if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
  	    																BootstrapDialog.show({
  	    																	title : 'FP Deal Creation - What If Main',
  	    																	type : BootstrapDialog.TYPE_DANGER,
  	    																	message : 'Request Rejected SucessFully',
  	    																	closable : false,
  	    																	buttons : [{
  	    																		label : 'OK',
  	    																		action : function(dialogRef) {
  	    																			dialogRef.close();
  	    																			window.location = "FPDealCreationWhatIfApplicationwise";
  	    																		}
  	    																	}]
  	    																});
  	    																$scope.approvalcomment = true;
  	    															}
  	    															else if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
  	    																BootstrapDialog.show({
  	    																	title : 'FP Deal Creation - What If Main',
  	    																	type : BootstrapDialog.TYPE_PRIMARY,
  	    																	message : 'Request Approved SucessFully',
  	    																	closable : false,
  	    																	buttons : [{
  	    																		label : 'OK',
  	    																		action : function(dialogRef) {
  	    																			dialogRef.close();
  	    																			window.location = "FPDealCreationWhatIfApplicationwise";
  	    																		}
  	    																	}]
  	    																});
  	    																$scope.approvalcomment = true;
  	    															}
  	    															else{
  	    																BootstrapDialog.show({
  	    																	title : 'FP Deal Creation - What If Main',
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
  	    														
  	    														});
  	    													
  	    													
  	    													/*var submitApproverData = function(response) {
  	    														$scope.updatedAuditDetails=1;
  	    														if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
  	    															BootstrapDialog.show({
  	    																title : 'FP Deal Creation - What If Main',
  	    																type : BootstrapDialog.TYPE_DANGER,
  	    																message : 'Request Rejected SucessFully',
  	    																closable : false,
  	    																buttons : [{
  	    																	label : 'OK',
  	    																	action : function(dialogRef) {
  	    																		dialogRef.close();
  	    																		window.location = "FPDealCreationWhatIfApplicationwise";
  	    																	}
  	    																}]
  	    															});
  	    															$scope.approvalcomment = true;
  	    														}
  	    														else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
  	    															BootstrapDialog.show({
  	    																title : 'FP Deal Creation - What If Main',
  	    																type : BootstrapDialog.TYPE_PRIMARY,
  	    																message : 'Request Approved SucessFully',
  	    																closable : false,
  	    																buttons : [{
  	    																	label : 'OK',
  	    																	action : function(dialogRef) {
  	    																		dialogRef.close();
  	    																		window.location = "FPDealCreationWhatIfApplicationwise";
  	    																	}
  	    																}]
  	    															});
  	    															$scope.approvalcomment = true;
  	    														}
  	    														else{
  	    															BootstrapDialog.show({
  	    																title : 'FP Deal Creation - What If Main',
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
  	    													WebServiceFactory.submitApproverData($scope.selectedObj).then(submitApproverData);*/
  	    													dialogRef.close();
  	    													
  	    											
  	    												
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
  	    										title: 'FP Deal Creation - What If Main',
  	    										type : BootstrapDialog.TYPE_PRIMARY,
  	    										message : 'Do you want to Approve ?',
  	    										closable: true,
  	    										buttons: [{
  	    											label: 'Yes',
  	    											cssClass: 'btn-primary',
  	    											action: function(dialogRef) {
  	    													var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
  	    													var comment = angular.element(approverId).val();
  	    													
  	    													if ($.trim(comment) == '') {
  	    														return false;
  	    													}
  	    													else
  	    													{
  	    														$scope.whatifDeal.currentApproverCommentModel = "["+user+"]: " + comment;
  	    														$scope.$apply();
  	    														
  	    														
  	    													
  	    														if($scope.whatifDeal.approverCommentModel){
  	    															var commnetBoxTxt = "["+user+"]: " + comment;
  	    															$scope.whatifDeal.approverCommentModel += '\n'+ commnetBoxTxt;
  	    															$scope.$apply();
  	    														}
  	    														else{
  	    															$scope.whatifDeal.approverCommentModel = "["+user+"]: " + comment;
  	    															$scope.$apply();
  	    														}
  	    														
  	    														if($scope.whatifDeal.approverCommentModel.length > 700)
  	    														{
  	    															var commentLen = $scope.whatifDeal.approverCommentModel.length;
  	    															var SkipChars = commentLen - 700;					
  	    															$scope.whatifDeal.approverCommentModel = $scope.whatifDeal.approverCommentModel.substring(SkipChars, commentLen);
  	    															$scope.$apply();
  	    														}
  	    													}
  	    													
  	    													angular.forEach($scope.selectedObj,function(value,key)
  	    															{
  	    																$scope.selectedObj[key].approverId=user;
  	    																$scope.selectedObj[key].approvalStatus=$scope.whatifDeal.approvalStatus;
  	    																$scope.selectedObj[key].approvalComments=$scope.whatifDeal.currentApproverCommentModel;
  	    																$scope.selectedObj[key].approvalDate=$scope.currentDate;
  	    															});
  	    													
  	    													if($scope.versionDetails[0].level1ApproverRoleId!=null){
  	    														$scope.setRoleId = 1;
  	    													}
  	    													if($scope.versionDetails[0].level2ApproverRoleId!=null){
  	    														$scope.setRoleId = 2;
  	    													}
  	    													if($scope.versionDetails[0].level3ApproverRoleId!=null){
  	    														$scope.setRoleId = 3;
  	    													}
  	    													if($scope.versionDetails[0].level4ApproverRoleId!=null){
  	    														$scope.setRoleId = 4;
  	    													}
  	    													if($scope.versionDetails[0].level5ApproverRoleId!=null){
  	    														$scope.setRoleId = 5;
  	    													}
  	    													if($scope.versionDetails[0].level6ApproverRoleId!=null){
  	    														$scope.setRoleId = 6;
  	    													}
  	    													if($scope.versionDetails[0].level7ApproverRoleId!=null){
  	    														$scope.setRoleId = 7;
  	    													}
  	    													if($scope.versionDetails[0].level8ApproverRoleId!=null){
  	    														$scope.setRoleId = 8;
  	    													}
  	    													if($scope.versionDetails[0].level9ApproverRoleId!=null){
  	    														$scope.setRoleId = 9;
  	    													}
  	    													
  	    													
  	    													//$scope.maxRoleId = $scope.setRoleId;
  	    													
  	    													//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
  	    													
  	    													if(($scope.versionDetails[0].currentApprovalLevel+1) == 2){
  	    														$scope.getLanid = $scope.versionDetails[0].level2ApproverRoleId;
  	    														$scope.statusIndicator = $scope.versionDetails[0].level2ApproverId.description;
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 3){
  	    														$scope.getLanid = $scope.versionDetails[0].level3ApproverRoleId;
  	    														if($scope.versionDetails[0].level3ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level3ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 4){
  	    														$scope.getLanid = $scope.versionDetails[0].level4ApproverRoleId;
  	    														if($scope.versionDetails[0].level4ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level4ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 5){
  	    														$scope.getLanid = $scope.versionDetails[0].level5ApproverRoleId;
  	    														if($scope.versionDetails[0].level5ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level5ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														}
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 6){
  	    														$scope.getLanid = $scope.versionDetails[0].level6ApproverRoleId;
  	    														if($scope.versionDetails[0].level6ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level6ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														}
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 7){
  	    														$scope.getLanid = $scope.versionDetails[0].level7ApproverRoleId;
  	    														if($scope.versionDetails[0].level7ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level7ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 8){
  	    														$scope.getLanid = $scope.versionDetails[0].level8ApproverRoleId;
  	    														if($scope.versionDetails[0].level8ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level8ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
  	    														} 
  	    													}
  	    													else if(($scope.versionDetails[0].currentApprovalLevel+1) == 9){
  	    														$scope.getLanid = $scope.versionDetails[0].level9ApproverRoleId;
  	    														if($scope.versionDetails[0].level9ApproverId != undefined) {
  	    															$scope.statusIndicator = $scope.versionDetails[0].level9ApproverId.description;
  	    														} else {
  	    															$scope.statusIndicator = "Approved"
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
  	    														$scope.lanId = "RiskManagers";//$scope.verticalDetails[0].RiskManagersPersonId;
  	    														$scope.roleId = 5;
  	    													}
  	    													else if($scope.getLanid == 6){
  	    														$scope.lanId = "GFT";
  	    														$scope.roleId = 6;
  	    													}
  	    													else if($scope.getLanid == 7){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  	    														$scope.roleId = 7;
  	    													}
  	    													else if($scope.getLanid == 8){
  	    														$scope.lanId = $scope.verticalDetails[0].buHeadId;
  	    														$scope.roleId = 8;
  	    													}
  	    													else if($scope.getLanid == 9){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
  	    														$scope.roleId = 9;
  	    													}
  	    												  else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
  	    														$scope.roleId = 10;
  	    													}
  	    													else if($scope.getLanid == 11 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[2].leaderLanID;
  	    														$scope.roleId = 11;
  	    													}
  	    													else if($scope.getLanid == 12 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[3].leaderLanID;
  	    														$scope.roleId = 12;
  	    													}
  	    													else if($scope.getLanid == 13 && $scope.leaderApproverNameDetails[0].leaderId){
  	    														$scope.lanId = $scope.leaderApproverNameDetails[4].leaderLanID;
  	    														$scope.roleId = 13;
  	    														$scope.statusIndicator = 'BU Head';
  	    													}
  	    													
  	    													//if($scope.setRoleId == $scope.getLanid){
  	    													if($scope.whatifDeal.approvalStatus == 3){
  	    														if($scope.getLanid == null){
  	  	    														
  	  	    														angular.forEach($scope.selectedObj,function(value,key)
  	  	    																{
  	  	    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  	  	    																	$scope.selectedObj[key].currentApproverId=null;
  	  	    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  	  	    																	$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel;
  	  	    																	$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  	  	    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  	  	    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  	  	    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
  	  	    																	$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
  	  	    																});
  	  	    														
  	  	    													} else {
  	  	    														angular.forEach($scope.selectedObj,function(value,key)
  	  	    																{
  	  	    															$scope.selectedObj[key].currentApprovalStatus=2;
  	  	    															$scope.selectedObj[key].currentApproverId=$scope.lanId;
  	  	    															$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  	  	    															$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel+1;
  	  	    															$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
  	  	    															$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  	  	    															$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  	  	    															$scope.selectedObj[key].createdBy=$scope.createdBy;
  	  	    															$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
	    																$scope.selectedObj[key].approvalFlag = $scope.whatifDeal.rainbowApprovalLevel;
  	  	    																});
  	  	    													}
  	    													}
  	    													
  	    													else if($scope.whatifDeal.approvalStatus == 4){
  	    														$scope.statusIndicator = "Recycled";
  	    														angular.forEach($scope.selectedObj,function(value,key)
  	    																{
  	    																	$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
  	    																	$scope.selectedObj[key].currentApproverId="";
  	    																	$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
  	    																	$scope.selectedObj[key].currentApprovalLevel="";
  	    																	$scope.selectedObj[key].currentApproverRoleId="";
  	    																	$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
  	    																	$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
  	    																	$scope.selectedObj[key].createdBy=$scope.createdBy;
  	    																	
  	    																});
  	    														
  	    													}
  	    													
  	    													$scope.updatedAuditDetails=1;
  	    													
  	    														$http({
  	    															method: 'POST',
  	    															url: contextPath+"/RightPrice-DAS/updateFPApprover",
  	    															dataType: 'json',
  	    															data: angular.toJson($scope.selectedObj),  
  	    															headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
  	    														}).
  	    														then(function(data) {
  	    															
  	    															if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
  	    																BootstrapDialog.show({
  	    																	title : 'FP Deal Creation - What If Main',
  	    																	type : BootstrapDialog.TYPE_DANGER,
  	    																	message : 'Request Rejected SucessFully',
  	    																	closable : false,
  	    																	buttons : [{
  	    																		label : 'OK',
  	    																		action : function(dialogRef) {
  	    																			dialogRef.close();
  	    																			window.location = "FPDealCreationWhatIfApplicationwise";
  	    																		}
  	    																	}]
  	    																});
  	    																$scope.approvalcomment = true;
  	    															}
  	    															else if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
  	    																BootstrapDialog.show({
  	    																	title : 'FP Deal Creation - What If Main',
  	    																	type : BootstrapDialog.TYPE_PRIMARY,
  	    																	message : 'Request Approved SucessFully',
  	    																	closable : false,
  	    																	buttons : [{
  	    																		label : 'OK',
  	    																		action : function(dialogRef) {
  	    																			dialogRef.close();
  	    																			window.location = "FPDealCreationWhatIfApplicationwise";
  	    																		}
  	    																	}]
  	    																});
  	    																$scope.approvalcomment = true;
  	    															}
  	    															else{
  	    																BootstrapDialog.show({
  	    																	title : 'FP Deal Creation - What If Main',
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
  	    														
  	    														});
  	    													
  	    													
  	    													/*var submitApproverData = function(response) {
  	    														$scope.updatedAuditDetails=1;
  	    														if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
  	    															BootstrapDialog.show({
  	    																title : 'FP Deal Creation - What If Main',
  	    																type : BootstrapDialog.TYPE_DANGER,
  	    																message : 'Request Rejected SucessFully',
  	    																closable : false,
  	    																buttons : [{
  	    																	label : 'OK',
  	    																	action : function(dialogRef) {
  	    																		dialogRef.close();
  	    																		window.location = "FPDealCreationWhatIfApplicationwise";
  	    																	}
  	    																}]
  	    															});
  	    															$scope.approvalcomment = true;
  	    														}
  	    														else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
  	    															BootstrapDialog.show({
  	    																title : 'FP Deal Creation - What If Main',
  	    																type : BootstrapDialog.TYPE_PRIMARY,
  	    																message : 'Request Approved SucessFully',
  	    																closable : false,
  	    																buttons : [{
  	    																	label : 'OK',
  	    																	action : function(dialogRef) {
  	    																		dialogRef.close();
  	    																		window.location = "FPDealCreationWhatIfApplicationwise";
  	    																	}
  	    																}]
  	    															});
  	    															$scope.approvalcomment = true;
  	    														}
  	    														else{
  	    															BootstrapDialog.show({
  	    																title : 'FP Deal Creation - What If Main',
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
  	    													WebServiceFactory.submitApproverData($scope.selectedObj).then(submitApproverData);*/
  	    													dialogRef.close();
  	    													
  	    											
  	    												
  	    											}
  	    										}, {
  	    											label: 'No',
  	    											cssClass: 'btn-primary',
  	    											action: function(dialog) {
  	    												dialog.close();
  	    											}
  	    										}]
  	    									});}
  	    								
  	    							}
  	    						});
  	    			
  		    			
  		    			}
  	    				
  	    	
						}
					
					else
						{
						BootstrapDialog.show({
							title : 'FP Deal Creation - What If Main',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Please enter comments in the comment section.",
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
					BootstrapDialog.show({
						title : 'FP Deal Creation - What If Main',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please select atleast one version to Approve",
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
				else if($scope.selectionTypeAR==4)
					{
					if($scope.selectedObj.length!=0)
					{
					if($scope.whatifDeal.currentApproverCommentModel!="" && $scope.whatifDeal.currentApproverCommentModel!=undefined )
						{
						BootstrapDialog.show({
							title: 'FP Deal Creation - What If Main',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'Do you want to Reject ?',
							closable: true,
							buttons: [{
								label: 'Yes',
								cssClass: 'btn-primary',
								action: function(dialogRef) {
										var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
										var comment = angular.element(approverId).val();
										
										if ($.trim(comment) == '') {
											return false;
										}
										else
										{
											$scope.whatifDeal.currentApproverCommentModel = "["+user+"]: " + comment;
											$scope.$apply();
											
											
										
											if($scope.whatifDeal.approverCommentModel){
												var commnetBoxTxt = "["+user+"]: " + comment;
												$scope.whatifDeal.approverCommentModel += '\n'+ commnetBoxTxt;
												$scope.$apply();
											}
											else{
												$scope.whatifDeal.approverCommentModel = "["+user+"]: " + comment;
												$scope.$apply();
											}
											
											if($scope.whatifDeal.approverCommentModel.length > 700)
											{
												var commentLen = $scope.whatifDeal.approverCommentModel.length;
												var SkipChars = commentLen - 700;					
												$scope.whatifDeal.approverCommentModel = $scope.whatifDeal.approverCommentModel.substring(SkipChars, commentLen);
												$scope.$apply();
											}
										}
										
										angular.forEach($scope.selectedObj,function(value,key)
												{
													$scope.selectedObj[key].approverId=user;
													$scope.selectedObj[key].approvalStatus=$scope.whatifDeal.approvalStatus;
													$scope.selectedObj[key].approvalComments=$scope.whatifDeal.currentApproverCommentModel;
													$scope.selectedObj[key].approvalDate=$scope.currentDate;
												});
										
										if($scope.versionDetails[0].level1ApproverRoleId!=null){
											$scope.setRoleId = 1;
										}
										if($scope.versionDetails[0].level2ApproverRoleId!=null){
											$scope.setRoleId = 2;
										}
										if($scope.versionDetails[0].level3ApproverRoleId!=null){
											$scope.setRoleId = 3;
										}
										if($scope.versionDetails[0].level4ApproverRoleId!=null){
											$scope.setRoleId = 4;
										}
										if($scope.versionDetails[0].level5ApproverRoleId!=null){
											$scope.setRoleId = 5;
										}
										if($scope.versionDetails[0].level6ApproverRoleId!=null){
											$scope.setRoleId = 6;
										}
										if($scope.versionDetails[0].level7ApproverRoleId!=null){
											$scope.setRoleId = 7;
										}
										if($scope.versionDetails[0].level8ApproverRoleId!=null){
											$scope.setRoleId = 8;
										}
										if($scope.versionDetails[0].level9ApproverRoleId!=null){
											$scope.setRoleId = 9;
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
											$scope.lanId = "RiskManagers";
											$scope.roleId = 5;
										}
										else if($scope.getLanid == 6){
											$scope.lanId = "GFT";
											$scope.roleId = 6;
										}
										else if($scope.getLanid == 7){
											$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
											$scope.roleId = 7;
										}
										else if($scope.getLanid == 8){
											$scope.lanId = $scope.verticalDetails[0].buHeadId;
											$scope.roleId = 8;
										}
										else if($scope.getLanid == 9){
											$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
											$scope.roleId = 9;
										}
										else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
												$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
												$scope.roleId = 10;
											}
											else if($scope.getLanid == 11 && $scope.leaderApproverNameDetails[0].leaderId){
												$scope.lanId = $scope.leaderApproverNameDetails[2].leaderLanID;
												$scope.roleId = 11;
											}
											else if($scope.getLanid == 12 && $scope.leaderApproverNameDetails[0].leaderId){
												$scope.lanId = $scope.leaderApproverNameDetails[3].leaderLanID;
												$scope.roleId = 12;
											}
											else if($scope.getLanid == 13 && $scope.leaderApproverNameDetails[0].leaderId){
												$scope.lanId = $scope.leaderApproverNameDetails[4].leaderLanID;
												$scope.roleId = 13;
												$scope.statusIndicator = 'BU Head';
											}
										
									 if($scope.whatifDeal.approvalStatus == 3){
											
											angular.forEach($scope.selectedObj,function(value,key)
													{
														$scope.selectedObj[key].currentApprovalStatus=2;
														$scope.selectedObj[key].currentApproverId=$scope.lanId;
														$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
														$scope.selectedObj[key].currentApprovalLevel=$scope.versionDetails[0].currentApprovalLevel+1;
														$scope.selectedObj[key].currentApproverRoleId=$scope.roleId;
														$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
														$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
														$scope.selectedObj[key].createdBy=$scope.createdBy;
														$scope.selectedObj[key].raibowApprovalLevel = $sessionStorage.fpraibowApprovalLevel;
														
													});
										}
										
										else if($scope.whatifDeal.approvalStatus == 4){
											$scope.statusIndicator = "Recycled";
											angular.forEach($scope.selectedObj,function(value,key)
													{
														$scope.selectedObj[key].currentApprovalStatus=$scope.whatifDeal.approvalStatus;
														$scope.selectedObj[key].currentApproverId="";
														$scope.selectedObj[key].approverComments=$scope.whatifDeal.approverCommentModel;
														$scope.selectedObj[key].currentApprovalLevel="";
														$scope.selectedObj[key].currentApproverRoleId="";
														$scope.selectedObj[key].statusIndicator=$scope.statusIndicator;
														$scope.selectedObj[key].isManualRc=$scope.isManualCheck;
														$scope.selectedObj[key].createdBy=$scope.createdBy;
														
													});
											
										}
										
										$scope.updatedAuditDetails=1;
										
											$http({
												method: 'POST',
												url: contextPath+"/RightPrice-DAS/updateFPApprover",
												dataType: 'json',
												data: angular.toJson($scope.selectedObj),  
												headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
											}).
											then(function(data) {
												
												if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 4){
													BootstrapDialog.show({
														title : 'FP Deal Creation - What If Main',
														type : BootstrapDialog.TYPE_DANGER,
														message : 'Request Rejected SucessFully',
														closable : false,
														buttons : [{
															label : 'OK',
															action : function(dialogRef) {
																dialogRef.close();
																window.location = "FPDealCreationWhatIfApplicationwise";
															}
														}]
													});
													$scope.approvalcomment = true;
												}
												else if(data.status == 200 && $scope.updatedAuditDetails==1 && $scope.whatifDeal.approvalStatus == 3){
													BootstrapDialog.show({
														title : 'FP Deal Creation - What If Main',
														type : BootstrapDialog.TYPE_PRIMARY,
														message : 'Request Approved SucessFully',
														closable : false,
														buttons : [{
															label : 'OK',
															action : function(dialogRef) {
																dialogRef.close();
																window.location = "FPDealCreationWhatIfApplicationwise";
															}
														}]
													});
													$scope.approvalcomment = true;
												}
												else{
													BootstrapDialog.show({
														title : 'FP Deal Creation - What If Main',
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
											
											});
										
									
										dialogRef.close();
										
								
									
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
							title : 'FP Deal Creation - What If Main',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Please enter comments in the comment section.",
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
					BootstrapDialog.show({
						title : 'FP Deal Creation - What If Main',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please select atleast one version to Approve",
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
					BootstrapDialog.show({
			        	title : 'FP Deal Creation - What If Main',
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
					
					
					}
				
				$scope.getWhatIfData = function(rpVersionId) {
					var getWhatIfData=function(response)
					{
						
						$scope.whatIfDetails = response.data;
						console.log("What if Data Inside on change");
						console.log($scope.whatIfDetails);
						
			    		if($scope.inputTypeModel == 1){
			    			$scope.whatifDeal.userInputValue=$scope.whatIfDetails[0].userInputValue;
			    			$sessionStorage.gmpervalue=$scope.whatifDeal.userInputValue;
			    		}
			    		else
			    			{
			    				$scope.whatifDeal.userInputValue=$scope.whatIfDetails[0].userInputValue;
			    				$sessionStorage.revvalue=$scope.whatifDeal.userInputValue;
			    			}
			    		
			    		
			    		
					};
					WebServiceFactory.getWhatIfData(rpVersionId).then(getWhatIfData);
					
				}
				
				
				$scope.getWhatIfCalculationData = function(rpVersionId) {
					var getWhatIfCalculationData=function(response)
					{
						
						$scope.whatIfCalDetails = response.data;
						console.log("What if Calculation Data");
						console.log($scope.whatIfCalDetails);
						$sessionStorage.CurrentCPC=$scope.whatIfCalDetails[0].currentCPC;
						$sessionStorage.CostWithoutCTC=$scope.whatIfCalDetails[0].costWithoutCPC;
						$sessionStorage.buMargin=$scope.whatIfCalDetails[0].buMargin;
						$sessionStorage.ceoMargin=$scope.whatIfCalDetails[0].ceoMargin;
						

						
			    		};
					WebServiceFactory.getWhatIfCalculationData(rpVersionId).then(getWhatIfCalculationData);
					
				}
				
				
				
				
				
				
				
				$scope.getApprovalMatrixWhatIf = function (whatifDeal) {
					if($scope.isSave==false){  
						                
						//alert("click on save button");  
						BootstrapDialog.show({
	    					title : 'Fixed Price What If',
	    					type : BootstrapDialog.TYPE_DANGER,
	    					message : "Please click on save button",
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
					
					
					if($scope.isStaffingComplete==1)
					{
						if($scope.whatIfDetails[0].userInputValue !=0)
							{
							var revPerCheck=$scope.whatIfDetails[0].userInputValue/$sessionStorage.dealTCV;
							  if($sessionStorage.isNewDeal!=2)
			  	    			{
			  	    			if((revPerCheck >= 0.995) && (revPerCheck <= 1.005))
			  	    				{
			  	    				WebServiceFactory.getWhatIfEffortData(rpVrsId).then(getWhatIfEffortData);
			  	    				if($sessionStorage.fpProjectTypeId == 1 || $sessionStorage.fpProjectTypeId== 2)
			  		    			{
			  		    				angular.forEach($scope.whatIfEffortDetails,function(value,key)
			  		    						{
			  		    						if($scope.whatIfEffortDetails[key].locationDescription == 'Offshore CH %')
			  		    							{
			  		    								if($scope.whatIfEffortDetails[key].effortsTotal < 0.15)
			  		    									{
			  		    									BootstrapDialog.show({
			  		    					    	            title : 'Campure Hire Mandate',
			  		    					    	            type : BootstrapDialog.TYPE_DANGER,
			  		    					    	            message : "Offshore Campus hire mandate of 15% have not met for Development Project <br> Do you want to proceed to submit?",
			  		    					    	            closable : false,
			  		    					    	            buttons : [{
			  		    					    	                   label : 'Yes',
			  		    					    	                   action : function(dialogRef) {
			  		    					    	                	   dialogRef.close();
			  		    							  	    				$scope.RedToSubmitSave=1;
			  		    							  	    				$scope.save(whatifDeal);
			  		    							  	    				var getApprovalMatrixWhatIf = function(response) {
			  		    							  	    					$scope.ApprovalMatrixWhatIfresponse = response.data;
			  		    							  	    					console.log("FpDeal Approval data");
			  		    							  	    					console.log($scope.ApprovalMatrixWhatIfresponse);
			  		    							  	    					
			  		    							  	    				}
			  		    							  	    				WebServiceFactory.getApprovalMatrixWhatIf(rpVrsId).then(getApprovalMatrixWhatIf);
			  		    					    	                      }
			  		    					    	            },
			  		    					    	            {
			  		    					    	                   label : 'No',
			  		    					    	                   action : function(dialogRef) {
			  		    					    	                		window.location='FPDealCreationStaffing';
			  		    					    	                           
			  		    					    	            }
			  		    					    	            }]
			  		    					    	      });
			  		    								}
			  		    								else{
			  		    									$scope.RedToSubmitSave=1;
		    							  	    				$scope.save(whatifDeal);
		    							  	    				var getApprovalMatrixWhatIf = function(response) {
		    							  	    					$scope.ApprovalMatrixWhatIfresponse = response.data;
		    							  	    					console.log("FpDeal Approval data");
		    							  	    					console.log($scope.ApprovalMatrixWhatIfresponse);
		    							  	    					
		    							  	    				}
		    							  	    				WebServiceFactory.getApprovalMatrixWhatIf(rpVrsId).then(getApprovalMatrixWhatIf);
			  		    								}
			  		    								
			  		    							}
			  		    						});
			  		    			}
			  		    		else if($sessionStorage.fpProjectTypeId == 3 ||  $sessionStorage.fpProjectTypeId == 4)
			  		    			{

			  	    				angular.forEach($scope.whatIfEffortDetails,function(value,key)
			  	    						{
			  	    						if($scope.whatIfEffortDetails[key].locationDescription == 'Offshore CH %')
			  	    							{
			  	    								if($scope.whatIfEffortDetails[key].effortsTotal < 0.20)
			  	    									{
			  	    									BootstrapDialog.show({
			  	    					    	            title : 'Campure Hire Mandate',
			  	    					    	            type : BootstrapDialog.TYPE_DANGER,
			  	    					    	            message : "Offshore Campus hire mandate of 20% have not met for Maintenance Project <br> Do you want to proceed to submit ?",
			  	    					    	            closable : false,
			  	    					    	            buttons : [{
			  	 					    	                   label : 'Yes',
			  	 					    	                   action : function(dialogRef) {
			  	 					    	                	   dialogRef.close();
			  	 					    	                		$scope.RedToSubmitSave=1;
			  								  	    				$scope.save(whatifDeal);
			  								  	    				var getApprovalMatrixWhatIf = function(response) {
			  								  	    					$scope.ApprovalMatrixWhatIfresponse = response.data;
			  								  	    					console.log("FpDeal Approval data");
			  								  	    					console.log($scope.ApprovalMatrixWhatIfresponse);
			  								  	    					
			  								  	    				}
			  								  	    				WebServiceFactory.getApprovalMatrixWhatIf(rpVrsId).then(getApprovalMatrixWhatIf);
			  	 					    	                           
			  	 					    	                   }
			  	 					    	            },
			  	 					    	            {
			  	 					    	                   label : 'No',
			  	 					    	                   action : function(dialogRef) {
			  	 					    	                		window.location='FPDealCreationStaffing';
			  	 					    	                           
			  	 					    	                   }
			  	 					    	            }]
			  	    					    	      });
			  	    								}
			  	    								else
			  	    									{
			  	    									$scope.RedToSubmitSave=1;
	    							  	    				$scope.save(whatifDeal);
	    							  	    				var getApprovalMatrixWhatIf = function(response) {
	    							  	    					$scope.ApprovalMatrixWhatIfresponse = response.data;
	    							  	    					console.log("FpDeal Approval data");
	    							  	    					console.log($scope.ApprovalMatrixWhatIfresponse);
	    							  	    					
	    							  	    				}
	    							  	    				WebServiceFactory.getApprovalMatrixWhatIf(rpVrsId).then(getApprovalMatrixWhatIf);
			  	    									}
			  	    								
			  	    							}
			  	    						});
			  	    			
			  		    			
			  		    			}
			  	    				
			  	    				}
			  	    			else{
			  	    				BootstrapDialog.show({
				  	      	            title : 'Fixed Price What If',
				  	      	            type : BootstrapDialog.TYPE_DANGER,
				  	      	            message : "Revenue should be in between 0.995% to 1.005% Range for TCV",
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
								  $scope.RedToSubmitSave=1;
								  $scope.save(whatifDeal);
								  var getApprovalMatrixWhatIf = function(response) {
			  	    					$scope.ApprovalMatrixWhatIfresponse = response.data;
			  	    					console.log("FpDeal Approval data");
			  	    					console.log($scope.ApprovalMatrixWhatIfresponse);
			  	    					/*if(response.status == 200) {
			  	    						$scope.isOldApprovercomment=true;
			  	    						BootstrapDialog.show({
			    						title : 'FP Deal Creation - FP Deal Summary',
			    						type : BootstrapDialog.TYPE_PRIMARY,
			    						message : 'Deal Version is submitted successfully.',
			    						closable : false,
			    						buttons : [ {
			    							label : 'OK',
			    							action : function(dialogRef) {
			    								dialogRef.close();
			    								window.location="FPDealCreationWhatIfApplicationwise";
			    							}
			    						} ]
			    					});
								}*/
							}
							WebServiceFactory.getApprovalMatrixWhatIf(rpVrsId).then(getApprovalMatrixWhatIf);
			  	    		
						 }
						}
						else{
							BootstrapDialog.show({
			    	            title : 'Fixed Price What If',
			    	            type : BootstrapDialog.TYPE_DANGER,
			    	            message : "Please Enter Value for Final Price by selecting the Input Type.",
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
					
					BootstrapDialog.show({
	    	            title : 'Fixed Price What If',
	    	            type : BootstrapDialog.TYPE_DANGER,
	    	            message : "Please fill data for all the Years in Staffing screen.",
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
				}	
				$scope.getUpdatedApprovalMatrix = function(versionId) {
					var getUpdatedApprovalMatrix = function(response) {
						$scope.approverMatrixStatus = reponse.data;
					}
					WebServiceFactory.getUpdatedApprovalMatrix(rpVrsId).then(getUpdatedApprovalMatrix);
				}
				
				 $scope.exportToExcel=function(tableId){
				        var exportHref=WebServiceFactory.tableToExcel(tableId,'FPDeal_What_If_Application_wise');
				    }
				 
				 //manglam updated
				 var markers = {	
						 "versionId" : $localStorage.rpDealVersionId							
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
						  $scope.uploadAttachmentPLData = function(whatifDeal){
						  var file = $('input[name="fuAttachFilename"]').get(0).files[0]
						  $scope.upload= false;
						  if(file != undefined ) {
						  
						  $scope.uploadPLFileData(file);
						  } 
						  else {
							 BootstrapDialog.show({										
							 title : 'Fixed Price What If',
							 type : BootstrapDialog.TYPE_DANGER,
							 message : "Please upload File and Click on Upload Button",
							 closable : false,
							 buttons : [ {
								 label : 'OK',
								 action : function(	
										 dialogRef) {	
									  dialogRef.close();
									  window.location="FPDealCreationWhatIfApplicationwise";	      
								 }
									
							  } ]									
							 });        								
							 }						
							} }

						  $scope.uploadPLFileData=function(file){
							  var name = "Template";
							  var tempID = 1; 
							  var versionId = $localStorage.rpDealVersionId; 
							  
							  var formData = new FormData();
							  formData.append('file', file);
							  formData.append('name', name);
							  formData.append('TempId', tempID);
							  formData.append('versionId', $localStorage.rpDealVersionId);
							  
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
											  title : 'Fixed Price What If',
											  type : BootstrapDialog.TYPE_PRIMARY,
											  message : customMessage,
											  closable : false,
											  buttons : [ {
												  label : 'OK',
												  action :function(
														  dialogRef) {
													  dialogRef.close();
													  window.location="FPDealCreationWhatIfApplicationwise";
													  }										
											  } ]									
										  });        																		
										  $scope.searchFile(versionId);
										  } 						    	
									  else if(data.status == 205){
										  BootstrapDialog.show({
											  title : 'Fixed Price What If',
											  type : BootstrapDialog.TYPE_DANGER,
											  message : "File Upload Failed.",
											  closable : false,
											  buttons : [ {
												  label : 'OK',
												  action : function(dialogRef) {
													  dialogRef.close();
													  window.location="FPDealCreationWhatIfApplicationwise";
													  }										
											  } ]									
										  });        								
										  }								
									  else {	
										  BootstrapDialog.show({
											  title : 'Fixed Price What If',
											  type : BootstrapDialog.TYPE_DANGER,
											  message : customMessage,
											  closable : false,	
											  buttons : [ {
												  label : 'OK',
												  action : function(dialogRef) {
													  dialogRef.close();
													  window.location="FPDealCreationWhatIfApplicationwise";
													  }									
											  } ]								
										  });								
										  }						    
									  },function(data) {
										  $scope.displayres = data.data;
										  $scope.answer = 'Posting data was unsuccessful.';	
										  });						
							  }
						  
						  $scope.searchFile = function(rpDealVersionId)
						  {							
							  angular.element("input[type='file']").val(null);
							  console.log("inside File Search---"+rpDealVersionId);
							  
							  var markers = {
									  "versionId" : $localStorage.rpDealVersionId
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