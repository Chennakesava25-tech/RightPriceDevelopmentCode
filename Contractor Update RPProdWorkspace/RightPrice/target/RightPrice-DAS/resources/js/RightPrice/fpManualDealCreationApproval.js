//var app = angular.module('RightPrice', []);
app.controller("FPCreationManualDealApprovalController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$sessionStorage','$localStorage','$filter', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$sessionStorage,$localStorage,$filter, $index){
			  
	$scope.stake = 0;
			$scope.Prev = function() {
				window.location = 'FPDealCreationManualDealSummary';
			};
			$scope.Next = function() {
				window.location = '';
			};
			
			var contextPath = "/RightPrice-DAS";
			var verticalId=0;
			
			var userType = sessionStorage.getItem('userType');
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
				
				
				
				
				
				var getVersionDetails = function(response)  //service will be call from this line
					{
						
						console.log(" All Version details based on Deal");
						console.log(response);
						$scope.dealverResult = response.data;
					};
					WebServiceFactory.getVersionDetails($localStorage.DealModel).then(getVersionDetails);
					
			
	var getVersionData =function(response)
	    	{
	    		console.log("Version Data");
	    		console.log(response);
	    		$scope.versionDetails = response.data;
	    		$sessionStorage.rbuName = $scope.versionDetails[0].dealcrmstagesdata2.rbuProfitCenter;
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
		  			$scope.commentBox = true;
	    			
	    			$scope.isDisabled = false;
	    		}
		  		else
		  			{
		  			$scope.commentBox = false;
		  			
		  			$scope.isDisabled = true;
		  			}
	    		
	    	};
	    	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);


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
	    			var level9= $scope.approverInfo[0].level9ApproverId.description;	
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
	    					$scope.approverLevels[key].name = "RiskManagers";//$scope.approverNameDetails[0].RiskManagers;
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
	    				if(value.level == "Level 1"){
	    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[2].leaderName;
	    				}
	    				if(value.level == "Level 2"){
	    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[3].leaderName;
	    				}
	    				if(value.level == "JV CEO"){
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
	    						$scope.isDisabled = false;
	    					}
	    					if(currentApprovalLevel == 1) {
	    						$scope.approverLevels[0].status = $scope.versionDetails[0].currentApprovalStatus;
	    						
	    						if($scope.approverLevels[0].status == 2) {
	    							$scope.approverLevels[0].status = "Pending Approval";
	    							$scope.isDisabled = true;
	    						}
	    						if($scope.approverLevels[0].status == "Pending Approval"){
	    							
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
	    						$scope.isDisabled = false;
	    					}
	    				}
	    				if($scope.versionDetails[0].currentApprovalStatus == "Recycled") {
	    					$scope.versionDetails[0].currentApprovalStatus = "Draft"
	    						for(var i=0;i<approverLevelLength;i++) {
//	    					console.log("Inside the for loop");
	    							$scope.approverLevels[i].status = "";
	    							$scope.isDisabled = false;
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
	      					$scope.approverLevels[key].name ="RiskManagers"; //$scope.approverNameDetails[0].RiskManagers;
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
	      				if(value.level == "Level 1"){
	    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[2].leaderName;
	    				}
	    				if(value.level == "Level 2"){
	    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[3].leaderName;
	    				}
	    				if(value.level == "JV CEO"){
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
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							for(var i=1;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[1].status = "Approved";
	      						}
	      						
	      					}
	      					else if(currentApprovalLevel == 3) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							for(var i=2;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
	      						}
	      						else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						}else {
	      							$scope.approverLevels[2].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 4) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							for(var i=3;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[3].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 5) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							for(var i=4;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[4].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 6) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							for(var i=5;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[5].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 7) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[6].status = "Pending Approval";
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[6].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 8) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[7].status = "Pending Approval";
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[7].status = "Approved";
	      						}
	      					}
	      					else if(currentApprovalLevel == 9) {
	      						for(var i=0;i<currentApprovalLevel-1;i++) {
	      							$scope.approverLevels[i].status = "Approved";
	      							$scope.isDisabled = true;
	      						}
	      						if($scope.versionDetails[0].currentApprovalStatus == 2) {
	      							$scope.approverLevels[8].status = "Pending Approval";
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 4){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Not Submitted";
	    	      						$scope.isDisabled = false;
	    	      					}
	      						}else if($scope.versionDetails[0].currentApprovalStatus == 3){
	      							for(var i=0;i<approverLevelLength;i++) {
	    	      						$scope.approverLevels[i].status = "Approved";
	    	      						$scope.isDisabled = true;
	    	      					}
	      						} else {
	      							$scope.approverLevels[8].status = "Approved";
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
	      						$scope.isDisabled = false;
	      					}
	      				}
	      				if($scope.versionDetails[0].currentApprovalStatus == "Recycled") {
	      					$scope.versionDetails[0].currentApprovalStatus = "Draft"
	      						for(var i=0;i<approverLevelLength;i++) {
//	      					console.log("Inside the for loop");
	      							$scope.approverLevels[i].status = "";
	      						}
	      				}
	      			});
	    		}
	    		 WebServiceFactory.getFPMDApproverNames($sessionStorage.verticalID,rpVrsId,rbuName).then(getFPMDApproverNames);
	    	}
	    	
	    	
	    	$scope.updateFPMDApprovalStatus = function (response) {
				
				angular.forEach($scope.approverLevels, function(value, key) {
					
					if(value.level == "DD"){
						$scope.approverLevels[key].empID = $scope.approverNameDetails[2];
					}
					else if(value.level == "Delivery Head"){
						$scope.approverLevels[key].empID = $scope.approverNameDetails[0].deliveryHeadId;
					}
					else if(value.level == "RiskManagers"){
						$scope.approverLevels[key].empID = "RiskManagers";//$scope.approverNameDetails[0].RiskManagersPersonId;
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
					else 	if(value.level == "Level 1"){
    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[2].leaderLanID;
    				}
					else if(value.level == "Level 2"){
    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[3].leaderLanID;
    				}
					else if(value.level == "JV CEO"){
    					$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[4].leaderLanID;
    				}
					
				});
				
				var rpDealVersionId = rpVrsId;
				
				var empId = $scope.approverLevels[1].empID;
				
				var currentApproval = $scope.approverLevels[0].level;
				
				if(currentApproval == "Delivery Head") {
					currentApproval = "Delivery Head";
				}
				console.log("The current Approval indicator is....... "+ currentApproval)

				var currentApprovelLevel = $scope.versionDetails[0].currentApprovalLevel+1;
				$scope.versionDetails[0].currentApprovalStatus = 1;
				var currentApproverStatus = $scope.versionDetails[0].currentApprovalStatus+1;
				var currentapproverComments=$scope.versionDetails[0].approverComments;
				marker = {
						"rpDealVersionId":rpDealVersionId,
						"currentApprovalStatus":currentApproverStatus,
						"currentApproverId":empId,
						"currentApprovalLevel":2,
						"approverComments":currentapproverComments,
						"statusIndicator":currentApproval
				};
//				console.log("Markers are");
//				console.log(marker);
				$http({
					 method: 'POST',
					 url: contextPath+"/RightPrice-DAS/updateFPMDApprovalData",
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
		    	        			window.location = "FPManualDealCreationApproval";
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