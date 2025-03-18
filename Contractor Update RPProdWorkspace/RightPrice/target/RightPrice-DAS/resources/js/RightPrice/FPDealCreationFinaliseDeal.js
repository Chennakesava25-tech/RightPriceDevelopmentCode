app.controller("FPDealCreationFinalizeDealController", ['$scope','$http','$window','$sessionStorage','WebServiceFactory','$filter', function($scope,$http,$window,$sessionStorage,WebServiceFactory,$filter,$index) {
			 $scope.Prev = function()
		    {   
		        window.location='FPDealCreationYearlySummary';
		    }; 
		    $scope.Next = function() 
		    {   
		        window.location='FPDealCreationSummary';
		    };
		    
		    var contextPath = "/RightPrice-DAS";
		    var rpVrsId = $sessionStorage.rpDealVersionId;	
		    $scope.approverLevels = [];
		    console.log("The RP Deal Version Id is........ "+ rpVrsId);
		    var getDealDetails = function(response) {				
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		/*console.log("Approver Data")
		  		console.log($scope.dealDetails);
		  		console.log($scope.dealDetails[0].dealId);
		  		console.log($scope.dealDetails[0].customerId);
		  		console.log($scope.dealDetails[0].dealStartDate);
		  		console.log($scope.dealDetails[0].dealEndDate);
		  		console.log($scope.dealDetails[0].dealDescription);
		  		console.log($scope.dealDetails[0].dealStatus)
		  		console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
		  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");*/
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
		  		
		  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
		  		var date = new Date(dealEndDate.substring(0,10));
		  		//console.log("End Date is......... " + date);
		  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.dealDetails[0].dealEndDate = dateENd;		  		
				//console.log("dateEND     ...... "+dateENd)		  		
				var dealStartDate = $scope.dealDetails[0].dealStartDate;
				var date = new Date(dealStartDate.substring(0,10));
				var dateStart = $filter('date')(date,'dd/MM/yyyy');
				$scope.dealDetails[0].dealStartDate = dateStart;
				//alert('hi 66');
				$scope.dealDetails[0].rpVersionId = $sessionStorage.rpDealVersionId;	//arvind
				$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;	
				 var startDay = new Date(dateStart);
		          var endDay = new Date(dateENd);
		          var millisecondsPerDay = 1000 * 60 * 60 * 24;
		          var millisBetween =  endDay.getTime()-startDay.getTime() 
		          var days = millisBetween / millisecondsPerDay;
		          $scope.dealDetails[0].DealDuration = Math.floor(days);	
		  		/*console.log($scope.dealDetails[0].percentageClose);
		  		console.log($scope.dealDetails[0].currencyId);
		  		console.log($scope.dealDetails[0].dealDuration);
		  		console.log($scope.dealDetails[0].stageId);*/
			};
			WebServiceFactory.getDealDetails($sessionStorage.DealModel,rpVrsId).then(getDealDetails);
			
			
			// Getting the approver name using vertical Id
				var getApproverName = function(response) {
				 	$scope.approverNameDetails = response.data;
				 	/*console.log("The Data after approver name details is.......... ");
				 	console.log($scope.approverNameDetails);
				 	console.log("Approver Name Details");
				 	console.log($scope.approverNameDetails);*/
				 	
				 	 angular.forEach($scope.approverLevels, function(value,key){
						 if(value.level == "DD"){
				  				$scope.approverLevels[key].name = $scope.approverNameDetails[1];
				  			}
				  			if(value.level == "DuH"){
				  				$scope.approverLevels[key].name = $scope.approverNameDetails[0].duh;
				  			}
				  			if(value.level == "RiskManagers"){
				  				$scope.approverLevels[key].name = "RiskManagers";//$scope.approverNameDetails[0].RiskManagers;
				  			}
				  			if(value.level == "GFT"){
				  				$scope.approverLevels[key].name = "Santosh Mishra";
				  			}
				  			
				  			if(value.level == "CDO"){
				  				$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[1].leaderName;
				  			}
				  			if(value.level == "BuH"){
				  				$scope.approverLevels[key].name = $scope.approverNameDetails[0].buh;
				  			}
				  			
				  			if(value.level == "COO"){
				  				$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[2].leaderName;
				  			}
				  			 
				  			if(value.level == "CEO"){
				  				$scope.approverLevels[key].name = $scope.leaderApproverNameDetails[0].leaderName;
				  			}
				  			
				  			var approverLevelLength = $scope.approverLevels.length;
				  			var currentApprovalLevel =  $scope.dealDetails[0].currentApprovalLevel;
						 	/*console.log("Rate Card Details..............................");
						 	console.log($scope.dealDetails[0].currentApprovalLevel);*/
						 	
						 	/*console.log("Approval Current Level........... ");
						 	console.log(currentApprovalLevel);
						 	console.log("Lenth of the array of approver levels");
						 	console.log($scope.approverLevels);
				  			*/
				  			if( $scope.dealDetails[0].status != "Draft") {
				  			
				  			if(currentApprovalLevel == null) { 	
				  				$scope.dealDetails[0].status = "Draft";
				  			}
				  			if(currentApprovalLevel == 1) {
				  				$scope.approverLevels[0].status = $scope.dealApproverData[0].currentApprovalStatus;
				  					if($scope.approverLevels[0].status == "Pending Approval"){
				  						$scope.isDisabled = true;
				  					}
				  				}
				  			else if(currentApprovalLevel == 2) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				$scope.approverLevels[1].status = $scope.dealApproverData[0].currentApprovalStatus;
			  				}
				  			else if(currentApprovalLevel == 3) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				$scope.approverLevels[2].status = $scope.dealApproverData[0].currentApprovalStatus;
			  				}
				  			else if(currentApprovalLevel == 4) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				$scope.approverLevels[3].status = $scope.dealApproverData[0].currentApprovalStatus;
			  				}
				  			else if(currentApprovalLevel == 5) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				$scope.approverLevels[4].status = $scope.dealApproverData[0].currentApprovalStatus;
			  				}
				  			else if(currentApprovalLevel == 6) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				$scope.approverLevels[5].status = $scope.dealApproverData[0].currentApprovalStatus;
			  				}
				  			else if(currentApprovalLevel == 7) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				$scope.approverLevels[6].status = $scope.dealApproverData[0].currentApprovalStatus;
			  				}
			  				else if(currentApprovalLevel == approverLevelLength) {
			  					for(var i=0;i<=approverLevelLength-1;i++) {
			  						$scope.approverLevels[i].status = "Approved";
			  						$scope.isDisabled = true;
			  					}
			  					$scope.approverLevels[key].status = $scope.dealApproverData[0].currentApprovalStatus;
			  				  }
				  			}
				  			else if($scope.dealDetails[0].status == "Draft"){
				  				for(var i=0;i<approverLevelLength;i++) {
			  						$scope.approverLevels[i].status = "Not Submitted";
			  					}
				  			}
				  			if($scope.dealDetails[0].status == "Rejected") {
				  				$scope.dealDetails[0].status = "Draft"
				  				for(var i=0;i<approverLevelLength;i++) {
				  					console.log("Inside the for loop");
			  						$scope.approverLevels[i].status = "";
			  					}
				  			}
					 });
				};
				WebServiceFactory.getApproverName().then(getApproverName);

				// Getting the data from leader ship table
				var marker = [];
				 var getLeaderApproverName = function(response) {
				  		$scope.leaderApproverNameDetails = response.data;
				  		console.log("Leader Approver name Details");
				  		console.log($scope.leaderApproverNameDetails);
				};
				WebServiceFactory.getLeaderApproverName().then(getLeaderApproverName);
					
			// get the deal approval details using the deal version Id
			var getDealApprovalDetails = function(response) {
					 $scope.dealApproverData = response.data;
					 console.log("The Deal Approver data is...... ");
					 console.log($scope.dealApproverData);
					 
					 if($scope.dealApproverData[0].level1ApprovalId != null) {
					  		var level1 = $scope.dealApproverData[0].level1ApprovalId.description;
					  		$scope.approverLevels.push ({
					  			'level' : level1
					  		});
					 }

					 if($scope.dealApproverData[0].level2ApprovalId != null) {
						 var level2 = $scope.dealApproverData[0].level2ApprovalId.description;
						 $scope.approverLevels.push ({
							 'level' : level2
						 });
					 }
					 
					 if($scope.dealApproverData[0].level3ApprovalId != null) {
						 var level3 = $scope.dealApproverData[0].level3ApprovalId.description;
						 $scope.approverLevels.push ({
							 'level' : level3
						 });
					 }
					 if($scope.dealApproverData[0].level4ApprovalId != null) {
						 var level4 = $scope.dealApproverData[0].level4ApprovalId.description;
						 $scope.approverLevels.push ({
							 'level' : level4
						 });
					 }
					 if($scope.dealApproverData[0].level5ApprovalId != null) {
						 var level5 = $scope.dealApproverData[0].level5ApprovalId.description;
						 $scope.approverLevels.push ({
							 'level' : level5
						 });
					 }
					 if($scope.dealApproverData[0].level6ApprovalId != null) {
						 var level6 = $scope.dealApproverData[0].level6ApprovalId.description;
						 $scope.approverLevels.push ({
							 'level' : level6
						 });
					 }
					 if($scope.dealApproverData[0].level7ApprovalId != null ) {
						 var level7 = $scope.dealApproverData[0].level7ApprovalId.description;
						 $scope.approverLevels.push ({
							 'level' : level7
						 });
					 }
			 };
			 WebServiceFactory.getDealApprovalDetails(rpVrsId).then(getDealApprovalDetails);
			
			 
			 $scope.updateApprovalStatus = function (response) {
					
					angular.forEach($scope.approverLevels, function(value, key) {
						
						if(value.level == "DD"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[2];
						}
						else if(value.level == "DuH"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[0].deliveryHeadId;
						}
						else if(value.level == "RiskManagers"){
							$scope.approverLevels[key].empID ="RiskManagers";// $scope.approverNameDetails[0].RiskManagersPersonId;
						}
						else if(value.level == "GFT"){
							$scope.approverLevels[key].empID = "Santosh Mishra";
						}
						else if(value.level == "CDO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[1].leaderLanID;
						}
						else if(value.level == "BuH"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[0].buHeadId;
						}
						else if(value.level == "COO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[2].leaderLanID;
						}
						else if(value.level == "CEO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[0].leaderLanID;
						}		
						
					});
					console.log("Approver Level Details");
					console.log($scope.approverLevels);
					
					var rpDealVersionId = rpVrsId;
					console.log("rc ID is..........." +rpDealVersionId);
					
					var empId = $scope.approverLevels[0].empID;
					console.log("Emp ID .............. "+empId);
					
					var currentApprovelLevel = $scope.dealApproverData[0].currentApprovalLevel +1;
					console.log("currentApproverLevel is................. "+ currentApprovelLevel);
					//$scope.dealDetails[0].status = 1;
					var currentApproverStatus = $scope.dealApproverData[0].dealcrmstagesdata2.dealStatusId + 1;
					console.log("currentApproverStatus is...................."+ currentApproverStatus);
					marker = {
							"rpDealVersionId":rpDealVersionId,
							"currentApprovalStatus":currentApproverStatus,
							"currentApproverId":empId,
							"currentApprovalLevel":currentApprovelLevel
					};
					console.log("Markers are");
					console.log(marker);
					$http({
						 method: 'POST',
						 url: contextPath+"/RightPrice-DAS/updateDealApprovalData",
						 dataType: 'json',
						 data: angular.toJson(marker),  
			            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    	}).
			    	then(function(data) {
			    		if(data.status == 200){
			    			var message = data.data;
			    	        BootstrapDialog.show({
			    	        	title : 'RateCard Creation - Finalise RateCard',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : message,
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "FPDealCreationFinaliseDeal";
			    	        		}
			    	        	}]
			    	        });
			    		}else{
			    			 message = data.data;
			    			BootstrapDialog.show({
			    	        	title : 'RateCard Creation - Finalise RateCard',
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
			
		}]);