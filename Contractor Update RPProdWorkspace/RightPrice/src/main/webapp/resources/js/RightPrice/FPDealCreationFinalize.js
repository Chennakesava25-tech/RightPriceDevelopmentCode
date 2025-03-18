//var app = angular.module('RightPrice', []);
app.controller("FPDealCreationFinalizeController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage','$localStorage','$filter', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$localStorage,$filter, $index){
			  
	$scope.stake = 0;
			$scope.Prev = function() {
				window.location = 'FpPricingDetails';
			};
			$scope.Next = function() {
				window.location = '';
			};
			$scope.isFinalDisabled=true;
			var contextPath = "/RightPrice-DAS";
			var verticalId=0;
			$scope.rfcount=0;
			$scope.selectedObj = [];
			$scope.selectedRFPObj =[];
			$scope.isFinalListDisabled=false;
			$scope.isReadyToSubmitData=false;
			$scope.isFPDealGFT = false;
			$scope.isFPDealRiskManagers = false;
			$scope.isDevelopment=false;
			$scope.selectedObjchckbox = {};
			$scope.dealRF=false;
			$scope.isRCPricing=false;
			$scope.dealnotRF=false;
			 var userType = sessionStorage.getItem('userType');
			 console.log("The Fp Manual Summary Details...... "+userType);
			 if(userType == 'GFT') {
				 $scope.isFPDealGFT = true;
			}
			 else if(userType == 'RiskManagers') {
					$scope.isFPDealRiskManagers = true;
				}
			$scope.approverLevels = [];
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
			  		$scope.currency=$scope.dealDetails[0].currency;
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
	    		$sessionStorage.isNewDeal=$scope.versionDetails[0].isNewDeal;
	    		$sessionStorage.crmDealId=$scope.versionDetails[0].crmDealId;
	    		$sessionStorage.curAprvlstatus=$scope.versionDetails[0].currentApprovalStatus;
	    		$sessionStorage.rbuName = $scope.versionDetails[0].dealcrmstagesdata2.rbuProfitCenter;

	    		if($sessionStorage.isNewDeal!=2)
	    			{
	    				$scope.dealnotRF=true;
	    				$scope.dealRF=false;
	    			}
	    		else
	    			{
	    				$scope.dealRF=true;
	    				$scope.dealnotRF=false;
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
	    		$sessionStorage.verticalID = $scope.versionDetails[0].verticalId;
	    		
	    		if(userType == "GFT" && ($scope.versionDetails[0].currentApprovalStatus == 7 || $scope.versionDetails[0].currentApprovalStatus == 4)) {
	    			
	    			$scope.isDisabled = false;
	    		}
		  		else
		  			{
		  				$scope.isDisabled = true;
		  			}
	    		/*$localStorage.pricingType=$scope.versionDetails[0].pricingType;

	    		if($localStorage.pricingType!=1)
	    		{
	    		  $scope.isRCPricing=true;
	    		}
	    		else{
	    			$scope.isRCPricing=false;
	    		}*/
	    		
	    		if($scope.versionDetails[0].fpProjectTypeId==1 || $scope.versionDetails[0].fpProjectTypeId==2){
	    			$scope.isDevelopment=true;	
	    		}
	    		else{
	    			$scope.isDevelopment=false;
	    		}
	    		
/*	    		if($sessionStorage.curAprvlstatus == 8)
	    			{
	    			
	    			var getVersionData =function(response)
	    			    	{
	    						console.log("Resouce Forcasting data");
	    						console.log(response);
	    						if(response.data.length < 1)
	    							{
	    							BootstrapDialog.show({
	    								title : 'FP Manual Deal Creation - Finalise FPDeal',
	    								type : BootstrapDialog.TYPE_PRIMARY,
	    								message : "Kindly visit DeMS Portal for Resource Forecasting",
	    								closable : false,
	    								buttons : [{
	    									label : 'OK',
	    									action : function(dialogRef) {
	    										dialogRef.close();
	    									}
	    								}]
	    							 });
	    							$scope.rfcount=1;
	    							$scope.isDisabled=true;
	    							}
	    						else{
	    							$scope.isDisabled=false;
	    						}
	    						
	    			    	};
	    			    WebServiceFactory.getOldResourceData($sessionStorage.crmDealId).then(getVersionData);
	    			    	
	    			}*/
	    		
	    	};
	    	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);

	    	var getVersionDetails = function(response)  //service will be call from this line
	    	{
	    		console.log("Version details based on Customer ");
	    		console.log(response);
	    		
	    		$scope.custDetails=response.data;
	    		if($scope.custDetails.length>1)
	    			{
	    			angular.forEach($scope.custDetails,function(value,key){
	    				if($scope.custDetails[key].currentApprovalStatus!=3)
	    				{
	    					$scope.finalApprovalbtn();
	    				}
	    			});
	    			}
	    		else
	    			{
	    				$scope.isFinalDisabled=false;
	    			}
	    			
	    	};
	    	WebServiceFactory.getVersionDetails($sessionStorage.crmDealId).then(getVersionDetails);
	    	
	    	
	    	$scope.finalApprovalbtn=function()
	    	{
	    		$scope.isFinalDisabled=false;
	    	};

			/*$scope.removeClick = function(id,row){
				console.log($scope.addContactorRole);
				var roleid = $scope.addContactorRole[id-1].contractor_Role_Id;
				console.log(roleid);
				if(roleid == undefined){
					console.log("here"+row);
					var index = $scope.addContactorRole.indexOf(row);
					console.log(index);
					$scope.addContactorRole.splice(index, 1);
				}else{
				var removeClick = function(response) {
					$window.location.reload();
					console.log("Deleted Successfully");
				};
				WebServiceFactory.removeClick(roleid).then(removeClick);
				}
			};*/
	    	
	    	
	    	var getLeadershipDetails = function(response) {
				//alert("inside getLeadershipDetails");
				console.log("getLeadershipDetails");
				console.log(response);
			    $scope.leaderApproverNameDetails = response.data;
			};
			WebServiceFactory.getLeadershipDetails().then(getLeadershipDetails);
			
	    	var getFPManualDealApproverInfo = function(response) {
	    		$scope.approverInfo = response.data;
	    		console.log("Approval Design Info");
	    		console.log(response.data);
	    		if($scope.approverInfo[0].level1ApproverId != null) {
	    	  		var level1 = $scope.approverInfo[0].level1ApproverId.description;
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level1
	    	  		});
	    		}
	    		if($scope.approverInfo[0].level2ApproverId != null) {
	    	  		var level2 = $scope.approverInfo[0].level2ApproverId.description;
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level2
	    	  		});
	    		}
	    		if($scope.approverInfo[0].level3ApproverId != null) {
	    	  		var level3 = $scope.approverInfo[0].level3ApproverId.description;
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level3
	    	  		});
	    		}
	    		if($scope.approverInfo[0].level4ApproverId != null) {
	    	  		var level4 = $scope.approverInfo[0].level4ApproverId.description;
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level4
	    	  		});
	    		}
	    		if($scope.approverInfo[0].level5ApproverId != null) {
	    	  		var level5 = $scope.approverInfo[0].level5ApproverId.description;
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level5
	    	  		});
	    		}
	    		if($scope.approverInfo[0].level6ApproverId != null) {
	    	  		var level6 = $scope.approverInfo[0].level6ApproverId.description;
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level6
	    	  		});
	    		console.log("approverLevels is..........");
	    		}
	    		if($scope.approverInfo[0].level7ApproverId != null) {
	    			var level7 = $scope.approverInfo[0].level7ApproverId.description;	
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level7
	    	  		});
	    		}
	    		if($scope.approverInfo[0].level8ApproverId != null) {
	    			var level8 = $scope.approverInfo[0].level8ApproverId.description;	
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level8
	    	  		});
	    		}
	    		if($scope.approverInfo[0].level9ApproverId != null) {
	    			var level9 = $scope.approverInfo[0].level9ApproverId.description;	
	    	  		$scope.approverLevels.push ({
	    	  			'level' : level9
	    	  		});
	    		}
	    		// Iterating the Array to fetch the Names based on Designation 
	    		if($scope.approverNameDetails != undefined) {
	    		angular.forEach($scope.approverLevels, function(value, key) {
	    			
	    				
	    				if(value.level == "DD"){
	    					$scope.approverLevels[key].name = $scope.approverNameDetails[1];
	    				}
	    				if(value.level == "Delivery Head"){
	    					$scope.approverLevels[key].name = $scope.approverNameDetails[0].duh;
	    				}
	    				if(value.level == "RiskManagers"){
	    					$scope.approverLevels[key].name ="RiskManagers" ;//$scope.approverNameDetails[0].RiskManagers;
	    				}
	    				if(value.level == "GFT"){
	    					$scope.approverLevels[key].name = "GFT";
	    				}
	    				
	    				if(value.level == "CDO"){
	    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[1].leaderName;
	    				}
	    				if(value.level == "BU Head"){
	    					$scope.approverLevels[key].name = $scope.approverNameDetails[0].buh;
	    				}
	    				
	    				if(value.level == "COO"){
	    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[1].leaderName;
	    				}
	    				
	    				if(value.level == "CEO"){
	    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[0].leaderName;
	    				}
	    				else if(value.level == "Level 1"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[2].leaderName;
		  				} 
						else if(value.level == "Level 2"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[3].leaderName;
		  				}
						else if(value.level == "JV CEO"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[4].leaderName;
		  				}
	    				
	    				var approverLevelLength = $scope.approverLevels.length;
	    				if($scope.versionDetails[0] != undefined) {
	    					var currentApprovalLevel =  $scope.versionDetails[0].currentApprovalLevel;
	    				} else {
	    					var currentApprovalLevel =  $localStorage.currentApprovalLevel;
	    				}

	    				if( $scope.versionDetails[0].currentApprovalStatus != "Draft") {
	    					
	    					if(currentApprovalLevel == null) { 	
	    						$scope.versionDetails[0].currentApprovalStatus = "Draft";
	    						if($scope.rfcount==1)
	    							{
	    							$scope.isDisabled = true;
	    							}
	    						else{
	    							$scope.isDisabled = false;
	    						}
	    						
	    					}
	    					if(currentApprovalLevel == 1) {
	    						$scope.approverLevels[0].status = $scope.versionDetails[0].currentApprovalStatus;
	    						
	    						if($scope.approverLevels[0].status == 2) {
	    							$scope.approverLevels[0].status = "Pending Approval";
	    							$scope.isDisabled = true;
	    						}
	    						
	    						for(var i=1;i<$scope.approverLevels.length;i++) {
	  								$scope.approverLevels[i].status = "Pending Approval";
	  							}
	    					}
	    					else if(currentApprovalLevel == 2) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[1].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == 3) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[2].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == 4) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[3].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == 5) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[4].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == 6) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[5].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == 7) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[6].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == 8) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[7].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == 9) {
	    						for(var i=0;i<currentApprovalLevel-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[8].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    					else if(currentApprovalLevel == approverLevelLength) {
	    						for(var i=0;i<=approverLevelLength-1;i++) {
	    							$scope.approverLevels[i].status = "Approved";
	    							$scope.isDisabled = true;
	    						}
	    						$scope.approverLevels[key].status = $scope.versionDetails[0].currentApprovalStatus;
	    					}
	    				}
	    				else if($scope.versionDetails[0].currentApprovalStatus == "Draft"){
	    					for(var i=0;i<approverLevelLength;i++) {
	    						$scope.approverLevels[i].status = "Not Submitted";
	    						if($scope.rfcount==1)
    							{
    							$scope.isDisabled = true;
    							}
    						else{
    							$scope.isDisabled = false;
    						}
	    						
	    					}
	    					
	    				}
	    				
	    				if($scope.versionDetails[0].currentApprovalStatus == "Recycled") {
	    					$scope.versionDetails[0].currentApprovalStatus = "Draft"
	    						for(var i=0;i<approverLevelLength;i++) {
//	    					console.log("Inside the for loop");
	    							$scope.approverLevels[i].status = "";
	    							if($scope.rfcount==1)
	    							{
	    							$scope.isDisabled = true;
	    							}
	    						else{
	    							$scope.isDisabled = false;
	    						}
	    							
	    						}
	    				}
	    			});
	    	  }	else {
	    		  $scope.getFPMDApproverNames($sessionStorage.verticalID,rpVrsId,$sessionStorage.rbuName);
	    	  }
	    	};
	    	WebServiceFactory.getFPManualDealApproverInfo(rpVrsId).then(getFPManualDealApproverInfo);
	    	
	    	
	    	

	    	$scope.getFPMDApproverNames = function (verticalId,rpVrsId,rbuName) {
	    		 var getFPMDApproverNames = function(response){
	    			$scope.approverNames = [];
	    			$scope.approverNameDetails = response.data;
	    			console.log("Approver Names");
	    			console.log($scope.approverNameDetails);
	    			angular.forEach($scope.approverLevels, function(value, key) {
	      				
	      				if(value.level == "DD"){
	      					$scope.approverLevels[key].name = $scope.approverNameDetails[1];
	      				}
	      				if(value.level == "Delivery Head"){
	      					$scope.approverLevels[key].name = $scope.approverNameDetails[0].duh;
	      				}
	      				if(value.level == "RiskManagers"){
	      					$scope.approverLevels[key].name ="RiskManagers";// $scope.approverNameDetails[0].RiskManagers;
	      				}
	      				if(value.level == "GFT"){
	      					$scope.approverLevels[key].name = "GFT";
	      				}
	      				
	      				if(value.level == "CDO"){
	      					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[1].leaderName;
	      				}
	      				if(value.level == "BU Head"){
	      					$scope.approverLevels[key].name = $scope.approverNameDetails[0].buh;
	      				}
	      				
	      				if(value.level == "COO"){
	      					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[1].leaderName;
	      				}
	      				
	      				if(value.level == "CEO"){
	      					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[0].leaderName;
	      				}
	      				
	      				else if(value.level == "Level 1"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[2].leaderName;
		  				} 
						else if(value.level == "Level 2"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[3].leaderName;
		  				}
						else if(value.level == "JV CEO"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[4].leaderName;
		  				}
	      				
	      				var approverLevelLength = $scope.approverLevels.length;
	      				if($scope.versionDetails[0] != undefined) {
	      					var currentApprovalLevel =  $scope.versionDetails[0].currentApprovalLevel;
	      				} else {
	      					var currentApprovalLevel =  $localStorage.currentApprovalLevel;
	      				}

	      				if( $scope.versionDetails[0].currentApprovalStatus != "Draft") {
	      					
	      					if(currentApprovalLevel == null) { 	
	      						$scope.versionDetails[0].currentApprovalStatus = "Draft";
	      						if($scope.rfcount==1)
    							{
    							$scope.isDisabled = true;
    							}
    						else{
    							$scope.isDisabled = false;
    						}
	      					}
	      					if(currentApprovalLevel == 1) {
	      						$scope.approverLevels[0].status = $scope.versionDetails[0].currentApprovalStatus;
	      						if($scope.approverLevels[0].status == 2) {
	    							$scope.approverLevels[0].status = "Pending Approval";
	    							$scope.isDisabled = true;
	    						}
	    						
	    						for(var i=1;i<$scope.approverLevels.length;i++) {
	  								$scope.approverLevels[i].status = "Pending Approval";
	  							}
	      					}
	      					else if(currentApprovalLevel == 2) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						for(var i=1;i<$scope.approverLevels.length;i++) {
	  								$scope.approverLevels[i].status = "Pending Approval";
	  							}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[1].status = "Pending Approval";
	      						} else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						if($scope.rfcount==1)
		    							{
		    							$scope.isDisabled = true;
		    							}
		    						else{
		    							$scope.isDisabled = false;
		    						}
	    	      					
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[i].status = "Approved";
	      						}
	      						
	      					}
	      					else if(currentApprovalLevel == 3) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						for(var i=2;i<$scope.approverLevels.length;i++) {
	  								$scope.approverLevels[i].status = "Pending Approval";
	  							}
	      						
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[2].status = "Pending Approval";
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						if($scope.rfcount==1)
		    							{
		    							$scope.isDisabled = true;
		    							}
		    						else{
		    							$scope.isDisabled = false;
		    						}
	    	      						
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						}  else {
	      							$scope.approverLevels[i].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 4) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						for(var i=3;i<$scope.approverLevels.length;i++) {
	  								$scope.approverLevels[i].status = "Pending Approval";
	  							}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[3].status = "Pending Approval";
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						if($scope.rfcount==1)
		    							{
		    							$scope.isDisabled = true;
		    							}
		    						else{
		    							$scope.isDisabled = false;
		    						}
	    	      					
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						}  else {
	      							$scope.approverLevels[3].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 5) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						for(var i=4;i<$scope.approverLevels.length;i++) {
	  								$scope.approverLevels[i].status = "Pending Approval";
	  							}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[4].status = "Pending Approval";
	      						} else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						if($scope.rfcount==1)
		    							{
		    							$scope.isDisabled = true;
		    							}
		    						else{
		    							$scope.isDisabled = false;
		    						}
	    	      						
	    	      					}
	      						} else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						}else {
	      							$scope.approverLevels[i].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 6) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[5].status = "Pending Approval";
	      						} else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						if($scope.rfcount==1)
		    							{
		    							$scope.isDisabled = true;
		    							}
		    						else{
		    							$scope.isDisabled = false;
		    						}
	    	      					
	    	      					}
	      						} else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						}else {
	      							$scope.approverLevels[i].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 7) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[6].status = "Pending Approval";
	      						} else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						if($scope.rfcount==1)
		    							{
		    							$scope.isDisabled = true;
		    							}
		    						else{
		    							$scope.isDisabled = false;
		    						}
	    	      						
	    	      					}
	      						} else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						}else {
	      							$scope.approverLevels[i].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == approverLevelLength) {
	      						for(var i=0;i<=approverLevelLength;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      					}
	      				}
	      				else if($scope.versionDetails[0].currentApprovalStatus == "Draft"){
	      					for(var i=0;i<approverLevelLength;i++) {
	      						$scope.approverLevels[i].status = "Not Submitted";
	      						if($scope.rfcount==1)
    							{
    							$scope.isDisabled = true;
    							}
    						else{
    							$scope.isDisabled = false;
    						}
	      						
	      					}
	      				}
	      				
	      				if($scope.versionDetails[0].currentApprovalStatus == "Recycled") {
	      					$scope.versionDetails[0].currentApprovalStatus = "Draft"
	      						for(var i=0;i<approverLevelLength;i++) {
//	      					console.log("Inside the for loop");
	      							$scope.approverLevels[i].status = "";
	      							if($scope.rfcount==1)
	    							{
	    							$scope.isDisabled = true;
	    							}
	    						else{
	    							$scope.isDisabled = false;
	    						}
	      						}
	      				}
	      			});
	    		}
	    		 WebServiceFactory.getFPMDApproverNames($sessionStorage.verticalID,rpVrsId,rbuName).then(getFPMDApproverNames);
	    	}
	    	
	    	
	    	
	    	
	    	var getFpVersionsReadyToSubmit = function(response)  //service will be call from this line
			{
				
				console.log("Versions which are Ready to Submit");
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
					$scope.isReadyToSubmitData=true;
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
				else
					{
						$scope.isReadyToSubmitData=false;
					}
				
				
				
				
				
				
			};
		WebServiceFactory.getFpVersionsReadyToSubmit($localStorage.DealModel).then(getFpVersionsReadyToSubmit);
		
		
		var getFpRfpRfiFinalVersion = function(response)  //service will be call from this line
		{
			console.log("Final Versions to be approved ");
			console.log(response);
			$scope.dealverFinalResult = response.data;
			
			angular.forEach($scope.dealverFinalResult,function(value,key)
					{	
				rpVersionId=$scope.dealverFinalResult[key].rpDealVersionId;
						var getWhatIfCalculationData=function(response)
							{
							$scope.whatIfCalDetails = response.data;
							console.log("What if Calculation Data");
							$scope.dealverFinalResult[key].noOfTowers=$scope.whatIfCalDetails[0].noOfTowers;
							};
				    	WebServiceFactory.getWhatIfCalculationData(rpVersionId).then(getWhatIfCalculationData);
				    	
				    	
				    	var grossMarginRes=$scope.dealverFinalResult[key].estimatedRevenue - $scope.dealverFinalResult[key].directCost;
				    	$scope.dealverFinalResult[key].operatingMargin = grossMarginRes - $scope.dealverFinalResult[key].projectSpecificCost;
					});
			
			if($sessionStorage.isNewDeal==2)
			{
				if($scope.dealverFinalResult!=undefined && $scope.dealverFinalResult!="")
				{
					$scope.isFinalListDisabled=true;
					
				}
			else
				{
					$scope.isFinalListDisabled=false;
					
				}
			}
			
			};
		WebServiceFactory.getFpRfpRfiFinalVersion($localStorage.DealModel).then(getFpRfpRfiFinalVersion);
		
		
		
		if($scope.dealverFinalResult!="" || $scope.dealverFinalResult!=undefined)
		{
		
		angular.forEach($scope.dealverFinalResult,function(value,key)
				{
			if($scope.dealverFinalResult[key].level1ApproverId!=null)
			{
				if($scope.dealverFinalResult[key].level2ApproverId!=null)
				{
					if($scope.dealverFinalResult[key].level3ApproverId!=null)
					{
						if($scope.dealverFinalResult[key].level4ApproverId!=null)
						{
							if($scope.dealverFinalResult[key].level5ApproverId!=null)
							{
								if($scope.dealverFinalResult[key].level6ApproverId!=null)
								{
									if($scope.dealverFinalResult[key].level7ApproverId!=null)
									{
										$scope.dealverFinalResult[key].finalApproval=$scope.dealverFinalResult[key].level7ApproverId.description;
									}
									else
									{
										$scope.dealverFinalResult[key].finalApproval=$scope.dealverFinalResult[key].level6ApproverId.description;
									}
								}
								else
								{
									$scope.dealverFinalResult[key].finalApproval=$scope.dealverFinalResult[key].level5ApproverId.description;
								}
							}
							else
							{
								$scope.dealverFinalResult[key].finalApproval=$scope.dealverFinalResult[key].level4ApproverId.description;
							}
						}
						else
						{
							$scope.dealverFinalResult[key].finalApproval=$scope.dealverFinalResult[key].level3ApproverId.description;
						}
					}
					else
					{
						$scope.dealverFinalResult[key].finalApproval=$scope.dealverFinalResult[key].level2ApproverId.description;
					}
				}
				else
				{
					$scope.dealverFinalResult[key].finalApproval=$scope.dealverFinalResult[key].level1ApproverId.description;
				}
			}
			else
			{
				$scope.dealverFinalResult[key].finalApproval="No Approval yet";
			}
			
				});
		}
		
		$scope.sendForApproval = function(data) {
			BootstrapDialog.show({
		           title: 'FP Manual Deal Creation - Finalise FPDeal',
		           type : BootstrapDialog.TYPE_PRIMARY,
		           message : 'Do you want to Send For Approval?',
		           closable: true,
		           buttons: [{
		               label: 'Yes',
		               cssClass: 'btn-primary',
		               action: function(dialogRef) {
		            	   $scope.checkCustomerMappingInFin(data);
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
		
		
		$scope.checkCustomerMappingInFin = function(data){
			var callNextMethod = function(resp){
				if(resp.status == 200){
					
					BootstrapDialog.show({
				           title: 'FP Deal Creation - Finalise FPDeal',
				           type : BootstrapDialog.TYPE_PRIMARY,
				           message :
				        	   	' Selected Deal ID of Salesforce Customer is not having mapping in PeopleSoft customer Id. '
				        	   + 'please contact syntel_billing@eviden.com team for salesforce customer id mapping to Peoplesoft customer id, ' 
				        	   + 'If this deal id <b>'+ resp.data[0].strOpportunityID + '</b> require to create new Project id in PeopleSoft System',
				           closable: true,
				           buttons: [{
				               label: 'Ok',
				               cssClass: 'btn-primary',
				               action: function(dialogRef) {
				            	   $scope.updateFpApproverStatus(data);
				            	   dialogRef.close();
								} 
				           }]
						});	
			}
			else{
				$scope.updateFpApproverStatus(data);
			}
		}
			WebServiceFactory.checkCustomerMappingInFin($sessionStorage.crmDealId).then(callNextMethod)
		}
			

		
	    	$scope.updateFpApproverStatus = function (data) {
	    	if($scope.selectedObj.length!=0)
	    	  {
	    		if($scope.fpDealFinalize.approverCommentModel!="" && $scope.fpDealFinalize.approverCommentModel!=undefined )
				{
	    		console.log("RpVersion to Send for Aproval")
	    		console.log($scope.selectedObj);
	    		var gftComment = angular.element(document.getElementById('txtComment'));
                var comment = angular.element(gftComment).val();
                $scope.versionDetails[0].approverComments=  [$scope.versionDetails[0].approverComments] + "\n" + [comment];
                var currentapproverComments=$scope.versionDetails[0].approverComments;
                
                angular.forEach($scope.selectedObj, function(value, key) {
                	$scope.selectedObj[key].approverComments=currentapproverComments
				});
				
                
				if(data!=undefined)
					{
					angular.forEach($scope.approverLevels, function(value, key) {
						
						if(value.level == "DD"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[2];
						}
						else if(value.level == "Delivery Head"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[0].deliveryHeadId;
						}
						else if(value.level == "RiskManagers"){
							$scope.approverLevels[key].empID = "Qulity";//$scope.approverNameDetails[0].RiskManagersPersonId;
						}
						else if(value.level == "GFT"){
							$scope.approverLevels[key].empID = "GFT";
						}
						else if(value.level == "CDO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[1].leaderLanID;
						}
						else if(value.level == "BU Head"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[0].buHeadId;
						}
						else if(value.level == "COO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[1].leaderLanID;
						}
						else if(value.level == "CEO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[0].leaderLanID;
						}		
						else if(value.level == "Level 1"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[2].leaderLanID;
		  				} 
						else if(value.level == "Level 2"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[3].leaderLanID;
		  				}
						else if(value.level == "JV CEO"){
		  					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[4].leaderLanID;
		  				}
						
					});
					
					var rpDealVersionId = data;
					
					var empId = $scope.approverLevels[0].empID;
					
					var currentApproval = $scope.approverLevels[0].level;
					
					if(currentApproval == "Delivery Head") {
						currentApproval = "Delivery Head";
					}
					console.log("The current Approval indicator is....... "+ currentApproval)
					
					var currentApprovelLevel = $scope.versionDetails[0].currentApprovalLevel+1;
					$scope.versionDetails[0].currentApprovalStatus = 1;
					var currentApproverStatus = $scope.versionDetails[0].currentApprovalStatus+1;
					
					
					
					
					marker = {
							"rpDealVersionId":rpDealVersionId,
							"currentApprovalStatus":currentApproverStatus,
							"currentApproverId":empId,
							"currentApprovalLevel":1,
							"approverComments":currentapproverComments,
							"crmDealId":$localStorage.DealModel,
							"statusIndicator":currentApproval
					};
//				console.log("Markers are");
//				console.log(marker);
					$http({
						method: 'POST',
						url: contextPath+"/RightPrice-DAS/updateFPApprovalStatus",
						dataType: 'json',
						data: angular.toJson(marker),  
						headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					}).
					then(function(data) {
						if(data.status == 200){
							var message = data.data;
							BootstrapDialog.show({
								title : 'FP Manual Deal Creation - Finalise FPDeal',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : message,
								closable : false,
								buttons : [{
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										window.location = "FPDealCreationDetails";
									}
								}]
							});
						}else{
							message = data.data;
							BootstrapDialog.show({
								title : 'FP Manual Deal Creation - Finalise FPDeal',
								type : BootstrapDialog.TYPE_DANGER,
								message : message,
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
					
					
					}
				else
					{
					
					$http({
						method: 'POST',
						url: contextPath+"/RightPrice-DAS/updateFPApprovalRFPStatus",
						dataType: 'json',
						data: angular.toJson($scope.selectedObj),  
						headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					}).
					then(function(data) {
						if(data.status == 200){
							var message = data.data;
							BootstrapDialog.show({
								title : 'FP Manual Deal Creation - Finalise FPDeal',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : message,
								closable : false,
								buttons : [{
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										window.location = "FPDealCreationDetails";
									}
								}]
							});
						}else{
							message = data.data;
							BootstrapDialog.show({
								title : 'FP Manual Deal Creation - Finalise FPDeal',
								type : BootstrapDialog.TYPE_DANGER,
								message : message,
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
					
					}
				}
	    		else
	    			{
	    			BootstrapDialog.show({
						title : 'FP Deal Creation - Finalise FPDeal',
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
					title : 'FP Deal Creation - Finalise FPDeal',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Please select atleast one version to send for Approval.",
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
			
			$scope.updateFpFinalApprovedStatus = function (data) {
				
				
						var rpDealVersionId=data;
					
						marker = {
								"rpDealVersionId":rpDealVersionId,
								"crmDealId":$localStorage.DealModel
								
						};
						
						$http({
							method: 'POST',
							url: contextPath+"/RightPrice-DAS/updateFPRpiRpfFinalApprovalStatus",
							dataType: 'json',
							data: angular.toJson(marker),  
							headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
						}).
						then(function(data) {
							if(data.status == 200){
								var message = data.data;
								BootstrapDialog.show({
									title : 'FP Manual Deal Creation - Finalise FPDeal',
									type : BootstrapDialog.TYPE_PRIMARY,
									message : message,
									closable : false,
									buttons : [{
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											window.location = "FPDealCreationDetails";
										}
									}]
								});
								
							}else{
								message = data.data;
								BootstrapDialog.show({
									title : 'FP Manual Deal Creation - Finalise FPDeal',
									type : BootstrapDialog.TYPE_DANGER,
									message : message,
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
						
						$scope.isFinalDisabled=false;
						
					
				
			};
			
			
			$scope.addVersionData  = function(data) {
				
				angular.forEach($scope.approverLevels, function(value, key) {
					
					if(value.level == "DD"){
						$scope.approverLevels[key].empID = $scope.approverNameDetails[2];
					}
					else if(value.level == "Delivery Head"){
						$scope.approverLevels[key].empID = $scope.approverNameDetails[0].deliveryHeadId;
					}
					else if(value.level == "RiskManagers"){
						$scope.approverLevels[key].empID ="RiskManagers";// $scope.approverNameDetails[0].RiskManagersPersonId;
					}
					else if(value.level == "GFT"){
						$scope.approverLevels[key].empID = "GFT";
					}
					else if(value.level == "CDO"){
						$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[1].leaderLanID;
					}
					else if(value.level == "BU Head"){
						$scope.approverLevels[key].empID = $scope.approverNameDetails[0].buHeadId;
					}
					else if(value.level == "COO"){
						$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[1].leaderLanID;
					}
					else if(value.level == "CEO"){
						$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[0].leaderLanID;
					}		
					else if(value.level == "Level 1"){
	  					$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[2].leaderLanID;
	  				} 
					else if(value.level == "Level 2"){
	  					$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[3].leaderLanID;
	  				}
					else if(value.level == "JV CEO"){
	  					$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[4].leaderLanID;
	  				}
				});
				
				var empId = $scope.approverLevels[0].empID;
				
				var currentApproval = $scope.approverLevels[0].level;
				
				if(currentApproval == "Delivery Head") {
					currentApproval = "Delivery Head";
				}
				console.log("The current Approval indicator is....... "+ currentApproval)
				
				var currentApprovelLevel = data.currentApprovalLevel+1;
				data.currentApprovalStatus = 1;
				var currentApproverStatus = data.currentApprovalStatus+1;
				var currentapproverComments=data.approverComments;
				$scope.selectedObj.push ({
					"rpDealVersionId" : data.rpDealVersionId,
					"currentApprovalStatus":currentApproverStatus,
					"currentApproverId":empId,
					"currentApprovalLevel":1,
					"crmDealId":$localStorage.DealModel,
					"statusIndicator":currentApproval,
					"level1ApproverRoleId":data.level1ApproverRoleId,
					"level2ApproverRoleId":data.level2ApproverRoleId,
					"level3ApproverRoleId":data.level3ApproverRoleId,
					"level4ApproverRoleId":data.level4ApproverRoleId,
					"level5ApproverRoleId":data.level5ApproverRoleId,
					"level6ApproverRoleId":data.level6ApproverRoleId,
					"level7ApproverRoleId":data.level7ApproverRoleId,
					"level8ApproverRoleId":data.level8ApproverRoleId,
					"level9ApproverRoleId":data.level9ApproverRoleId
				});
				
				console.log($scope.selectedObj);
			}
			 
	    	
		} ]);
		


		
		app.directive('blurToCurrency', function($filter){
		  return {
		    scope: {
		      amount  : '='
		    },
		    link: function(scope, el, attrs){
		      el.val($filter('currency')(scope.amount,"",2));
		      
		      el.bind('focus', function(){
		        el.val(scope.amount);
		      });
		      
		      el.bind('input', function(){
		        scope.amount = el.val();
		        scope.$apply();
		      });
		      
		      el.bind('blur', function(){
		        el.val($filter('currency')(scope.amount,"",2));
		      });
		    }
		  };
		});