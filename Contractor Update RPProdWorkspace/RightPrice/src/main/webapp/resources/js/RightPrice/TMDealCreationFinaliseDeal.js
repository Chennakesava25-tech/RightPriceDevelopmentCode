app.controller("MapRolesToClientRoleController", ['$scope','$http','$window','$localStorage','$sessionStorage','WebServiceFactory','$filter', function($scope,$http,$window,$localStorage,$sessionStorage,WebServiceFactory,$filter,$index) {
			 $scope.Prev = function()
		    {   
		        window.location='TMDealCreationSummary';
		    }; 
		   
		    var contextPath = "/RightPrice-DAS";
		    var rpVrsId = $localStorage.rpDealVersionId;	
		    $scope.approverLevels = [];
		    $scope.isSubmitDisable = false;
		    $scope.isAppRej = false;
		    $scope.flag = true;
		    $scope.isRecycleBtnDisable = false;
		    $scope.saved = false;
		    var userType = sessionStorage.getItem('userType');
		    $sessionStorage.diffEqualZero=$sessionStorage.diffEqualZero;
		    
		    console.log("The RP Deal Version Id is........ "+ rpVrsId);
		    var getDealDetails = function(response) {	
		    	console.log("Deal Details");
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		 
		  		console.log($scope.dealDetails[0].currentApprovalLevel);
		  		/*console.log($scope.dealDetails[0].dealId);
		  		console.log($scope.dealDetails[0].customerId);
		  		console.log($scope.dealDetails[0].dealStartDate);
		  		console.log($scope.dealDetails[0].dealEndDate);
		  		console.log($scope.dealDetails[0].dealDescription);
		  		console.log($scope.dealDetails[0].dealStatus)
		  		console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
		  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");*/
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType==1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId==1)? "Fixed Price" :"T & M";
		  		
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
				$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	//arvind
				$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;	
				 var startDay = new Date(dateStart);
		          var endDay = new Date(dateENd);
		          var millisecondsPerDay = 1000 * 60 * 60 * 24;
		          var millisBetween =  endDay.getTime()-startDay.getTime() 
		          var days = millisBetween / millisecondsPerDay;
		          $scope.dealDetails[0].DealDuration = Math.floor(days);	

		          var statusId = $scope.dealDetails[0].currentApprovalStatus;
		          
		          if((statusId == 1 || statusId == 4) && userType == 'Delivery' ) {
                	  $scope.isSubmitDisable = false;
                	  $scope.isAppRej = false;
                  } else {
                	  $scope.isSubmitDisable = true;
                	  $scope.isAppRej = true;
                  }
		          if(statusId == 1 ) {
		        	  $scope.flag = false;
                	  
                  }
		          
		          if(statusId != 1 || statusId == 3) {
		        	  $scope.isRecycleBtnDisable = true;
		          }
		          
		          
		          switch (statusId) {
			  		case 1 : 
			  			$scope.status = "Draft";
			  			angular.forEach($scope.dealDetails,function (value, key) {
			  				$scope.dealDetails[key].currentStatus = $scope.status;
			  			});
			  			break;
					case 2 : 
						$scope.status = "Pending Approval";
						angular.forEach($scope.dealDetails,function (value, key) {
			  				$scope.dealDetails[key].currentStatus = $scope.status;
			  			});
						break;
					case 3 : 
						$scope.status = "Approved";
						angular.forEach($scope.dealDetails,function (value, key) {
			  				$scope.dealDetails[key].currentStatus = $scope.status;
			  			});
						break;
					case 4 : 
						$scope.status = "Recycled";
						angular.forEach($scope.dealDetails,function (value, key) {
			  				$scope.dealDetails[key].currentStatus = $scope.status;
			  			});
						break;
					case 5 : 
						$scope.status = "Deactivated";
						angular.forEach($scope.dealDetails,function (value, key) {
			  				$scope.dealDetails[key].currentStatus = $scope.status;
			  			});
						break;
					case 6 : 
						$scope.status = "Expired";
						angular.forEach($scope.dealDetails,function (value, key) {
			  				$scope.dealDetails[key].currentStatus = $scope.status;
			  			});
						break;
					case 7 : 
						$scope.status = "Manual with GFT";
						angular.forEach($scope.dealDetails,function (value, key) {
			  				$scope.dealDetails[key].currentStatus = $scope.status;
			  			});
						break;
					default : 
			  			break;
			  		}
		  		/*console.log($scope.dealDetails[0].percentageClose);
		  		console.log($scope.dealDetails[0].currencyId);
		  		console.log($scope.dealDetails[0].dealDuration);
		  		console.log($scope.dealDetails[0].stageId);*/
			};
			WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails);
			
			
		/*	// get the deal details 
			$scope.getTMFinalizeDealComment = function(){*/
				
				var getTMFinalizeDealComment = function(response) 
				{
				    $scope.comment = response.data;
				    $scope.teamDeal.approverCommentModel=$scope.comment[0].approverComments;
				    
				};
				
				WebServiceFactory.getTMFinalizeDealComment(rpVrsId).then(getTMFinalizeDealComment);
		/*	}
			*/
				var getVersionData = function(response) {
					console.log("get versionDetails Data..");
					console.log(response);
					$scope.getVersionData=response.data;
		    		$sessionStorage.rbuName = $scope.getVersionData[0].dealcrmstagesdata2.rbuProfitCenter;

					
					if($scope.getVersionData!=undefined)
					{
						$localStorage.pageTracker=$scope.getVersionData[0].pageTrackerStatus;
						$localStorage.CurrentApprovalStatus=$scope.getVersionData[0].currentApprovalStatus;
						if(userType == 'Delivery')
						{
						if(($localStorage.CurrentApprovalStatus == null || $localStorage.CurrentApprovalStatus == 1 ||
								$localStorage.CurrentApprovalStatus == 4 ) && $localStorage.pageTracker<4)
							{	BootstrapDialog.show({
								title : 'T&M Deal Creation - Summary',
								type : BootstrapDialog.TYPE_DANGER,
								message : "Please save on the previous screen to proceed ahead",
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										if($localStorage.pageTracker==3)
										{
											window.location="TMDealCreationStaffing";
										}
										else if($localStorage.pageTracker==2)
										{
											window.location="TMDealCreationRoleSelection";
										}
										else if($localStorage.pageTracker==1)
										{
											window.location="TMDealCreationRateCardAndProjectDetails";
										}
									else
										{
											window.location="TMDealCreationDetails";
										}
									}
								} ]
							});	
								
							}
						}
					}
					
					
				};
				WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
			
			
			//get the deal approval details using the deal version Id
			   var getTMDealApproverInfo = function(response) {
					 $scope.dealApproverData = response.data;					 
					 
					 if($scope.dealApproverData[0].level1ApprovalId != null) {
						 var level1 = $scope.dealApproverData[0].level1ApprovalId.description;
						 $scope.teamDeal.lvl = $scope.dealApproverData[0].level1ApprovalId.description;
						 if($scope.dealApproverData[0].currentApprovalStatus==2)
							 {
							 $scope.teamDeal.status = "pending approval";
							 }
						 else if ($scope.dealApproverData[0].currentApprovalStatus==3)
							 {
							 $scope.teamDeal.status = "Approved";
							 }
						 else 
						 {
							 $scope.teamDeal.status = "Not Submitted";
						 }
						 
						 if(level1=='Delivery Head')
							 {
							 level1='Delivery Head'
						  		$scope.approverLevels.push ({
						  			'level' : level1
						  		});
							 }
						 else
							 {
							 $scope.approverLevels.push ({
						  			'level' : level1
						  		});
							 }
					  		
					 }

					 /*if($scope.dealApproverData[0].level2ApprovalId != null) {
						 var level2 = $scope.dealApproverData[0].level2ApprovalId.description;
						 if(level2=='Delivery Head')
						 {
							 level2='Delivery Head'
							 $scope.approverLevels.push ({
								 'level' : level2
							 }); 
						 }
						 else
						 {
							 $scope.approverLevels.push ({
						  			'level' : level2
						  		});
						 }
						 
					 }*/
					 
					 /*if($scope.dealApproverData[0].level3ApprovalId != null) {
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
					 }*/
			 };
			 WebServiceFactory.getTMDealApproverInfo(rpVrsId).then(getTMDealApproverInfo);
			
			// Getting the approver name using vertical Id
				var getDealApproverName = function(response) {
				 	$scope.approverNameDetails = response.data;
				 	console.log("The Data after approver name details is.......... ");
				 	console.log($scope.approverNameDetails);
				 	console.log("Approver Name Details");
				 	console.log($scope.approverNameDetails);
				 	
				 	//$scope.frmdDeal.apvrLevel =  $scope.approverNameDetails[0].duh; 
				 	$scope.teamDeal.buh = $scope.approverNameDetails[0].duh; 
				 	
				 	 /*angular.forEach($scope.approverLevels, function(value,key){
						 if(value.level == "DD"){
				  				$scope.approverLevels[key].name = $scope.approverNameDetails[1];
				  			}
				  			if(value.level == "Delivery Head"){
				  				$scope.approverLevels[key].name = $scope.approverNameDetails[0].duh;
				  			}
				  			if(value.level == "RiskManagers"){
				  				$scope.approverLevels[key].name = $scope.approverNameDetails[0].RiskManagers;
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
						 	console.log("Rate Card Details..............................");
						 	console.log($scope.dealDetails[0].currentApprovalLevel);
						 	
						 	console.log("Approval Current Level........... ");
						 	console.log(currentApprovalLevel);
						 	console.log("Lenth of the array of approver levels");
						 	console.log($scope.approverLevels);
				  			
				  			if( $scope.dealDetails[key].currentStatus != "Draft" && $scope.dealDetails[key].currentStatus != "Recycled") {
				  			
				  			if(currentApprovalLevel == null && $scope.dealDetails[0].currentApprovalStatus != 3) { 	
				  				$scope.dealDetails[key].currentStatus = "Draft";
				  				 angular.forEach($scope.approverLevels, function(value,key){
				  					 $scope.approverLevels[key].status = "Not Submitted";
				  				 });
				  			} else if(currentApprovalLevel == null && $scope.dealDetails[0].currentApprovalStatus == 3) {
				  				$scope.dealDetails[key].currentStatus = "Approved";
				  				 angular.forEach($scope.approverLevels, function(value,key){
				  					 $scope.approverLevels[key].status = "Approved";
				  				 });
				  			}
				  			if(currentApprovalLevel == 1) {
				  				$scope.approverLevels[0].status = $scope.dealDetails[0].currentStatus;
				  					if($scope.approverLevels[0].status == "Pending Approval"){
				  						$scope.isDisabled = true;
				  					}
				  				}
				  			else if(currentApprovalLevel == 2) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				//$scope.approverLevels[1].status = $scope.dealDetails[0].currentStatus;
			  				}
				  			else if(currentApprovalLevel == 3) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  			//	$scope.approverLevels[2].status = $scope.dealDetails[0].currentStatus;
			  				}
				  			else if(currentApprovalLevel == 4) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				//$scope.approverLevels[3].status = $scope.dealDetails[0].currentStatus;
			  				}
				  			else if(currentApprovalLevel == 5) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  			//	$scope.approverLevels[4].status = $scope.dealDetails[0].currentStatus;
			  				}
				  			else if(currentApprovalLevel == 6) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  				//$scope.approverLevels[5].status = $scope.dealDetails[0].currentStatus;
			  				}
				  			else if(currentApprovalLevel == 7) {
				  				for(var i=0;i<currentApprovalLevel-1;i++) {
				  					$scope.approverLevels[i].status = "Approved";
				  					$scope.isDisabled = true;
				  				}
				  			//	$scope.approverLevels[6].status = $scope.dealDetails[0].currentStatus;
			  				}
			  				else if(currentApprovalLevel == approverLevelLength) {
			  					for(var i=0;i<=approverLevelLength-1;i++) {
			  						$scope.approverLevels[i].status = "Approved";
			  						$scope.isDisabled = true;
			  					}
			  					//$scope.approverLevels[key].status = $scope.dealDetails[0].currentStatus;
			  				  }
				  			}
				  			else if($scope.dealDetails[key].currentStatus == "Draft"){
				  				for(var i=0;i<approverLevelLength;i++) {
			  						$scope.approverLevels[i].status = "Not Submitted";
				  				}
			  					
				  			}
				  			if($scope.dealDetails[0].currentStatus == "Recycled") {
				  				$scope.dealDetails[0].currentStatus = "Draft"
				  				for(var i=0;i<approverLevelLength;i++) {
				  					console.log("Inside the for loop");
			  						$scope.approverLevels[i].status = "Not Submitted";
			  					}
				  			}
					 });*/
				};
				WebServiceFactory.getDealApproverName(rpVrsId,$sessionStorage.rbuName).then(getDealApproverName);

				// Getting the data from leader ship table
				var marker = [];
				 var getLeaderApproverName = function(response) {
				  		$scope.leaderApproverNameDetails = response.data;
				  		
				  		
				  		
				  		console.log("Leader Approver name Details");
				  		console.log($scope.leaderApproverNameDetails);
				};
				WebServiceFactory.getLeaderApproverName().then(getLeaderApproverName);
		
				
				$scope.updateDealValidation=function(teamDeal)
				{
					var getOldResourceData =function(response)
			    	{
						console.log("Resouce Forcasting data");
						console.log(response);
/*						if(response.data.length < 1)
							{
							BootstrapDialog.show({
								title : 'T&M Deal Creation - Finalise Deal',
								type : BootstrapDialog.TYPE_DANGER,
								message : "Resource Forcasting is not done",
								closable : false,
								buttons : [{
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
									}
								}]
							 });
							}*/
						//else{
						if($sessionStorage.diffEqualZero != 0) 
						{		    		
			    			BootstrapDialog.show({
			    	        	title : 'T&M Staffing not done for all Years',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Kindly enter all years data to proceed further',
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
							if(response.data!=null){
								if(response.data==1){
									BootstrapDialog.show({
					    	        	title : 'T&M Deal ',
					    	        	type : BootstrapDialog.TYPE_PRIMARY,
					    	        	message : 'T&M Deal Already Submitted',
					    	        	closable : false,
					    	        	buttons : [{
					    	        		label : 'OK',
					    	        		action : function(dialogRef) {
					    	        			dialogRef.close();
					    	        		}
					    	        	}]
					    	        });
								}
								if(response.data==0){
								$scope.saved = true;
								if(teamDeal.$invalid == true)
								{
								
									$scope.saved = true;
								}
								else
								{
									$scope.updateApprovalStatus();
								}
							}
							}	//}
							}
			    };
			    WebServiceFactory.getOldResourceData($sessionStorage.crmDealId).then(getOldResourceData);
				
								
				}
				
				$scope.recycleDealValidation=function(teamDeal){
					$scope.saved = true;
					if(teamDeal.$invalid == true)
						{
						
						$scope.saved = true;
						}
					else
						{
						$scope.updateRecycle();
						
						}
					}
				
				
				$scope.updateApprovalStatus = function (response) {
					
					BootstrapDialog.show({
				           title: 'T&M Deal Creation - Finalize Deal',
				           type : BootstrapDialog.TYPE_PRIMARY,
				           message : 'Do you want to send for Approval',
				           closable: true,
				           buttons: [{
				               label: 'Yes',
				               cssClass: 'btn-primary',
				               action: function(dialogRef) {
				            	   console.log(" send for approval");
									
				    			var callNextMethod = function(resp){
				    				if(resp.status == 200){
				    					
				    					BootstrapDialog.show({
				    				           title: 'T&M Deal Creation - Finalise Deal',
				    				           type : BootstrapDialog.TYPE_PRIMARY,
				    				           message :
				    				        	   	' Selected Deal ID of Salesforce Customer ID is not having mapping in PeopleSoft customer Id. '
				    				        	   + 'please contact syntel_billing@eviden.com team for salesforce customer id mapping to Peoplesoft customer id, ' 
				    				        	   + 'If this deal id '+ resp.data[0].strOpportunityID + ' require to create new Project id in PeopleSoft System',
				    				           closable: true,
				    				           buttons: [{
				    				               label: 'Ok',
				    				               cssClass: 'btn-primary',
				    				               action: function(dialogRef) {
				    				            	   angular.forEach($scope.approverLevels, function(value, key) {
				   										
				  										 if(value.level == "Delivery Head"){
				  											$scope.approverLevels[key].empID = $scope.approverNameDetails[0].deliveryHeadId;
				  										}
				  										
				  									});
				  									

				  									var rpDealVersionId = rpVrsId;
				  									
				  									var empId = $scope.approverLevels[0].empID;
				  									
				  									var currentApproval = $scope.approverLevels[0].level;
				  									
				  									if(currentApproval == "Delivery Head") {
				  										currentApproval = "Delivery Head";
				  									}
				  									console.log("The current Approval indicator is....... "+ currentApproval)

				  									var currentApprovelLevel = $scope.dealApproverData[0].currentApprovalLevel +1;
				  									
				  									var gftComment = angular.element(document.getElementById('txtComment'));
				  					                var comment = angular.element(gftComment).val();
				  					                $scope.dealApproverData[0].approverComments=  [$scope.dealApproverData[0].approverComments] + "\n" + "" + [comment];
				  					                var approverComments =$scope.dealApproverData[0].approverComments;
				  					                
				  					                if($scope.dealDetails[0].currentApprovalStatus != 4) {
				  					                	var currentApproverStatus = $scope.dealDetails[0].currentApprovalStatus+1;
				  					                } else {
				  					                	var currentApproverStatus = $scope.dealDetails[0].currentApprovalStatus-2;
				  					                }
				  					                marker = {
				  											"rpDealVersionId":rpVrsId,
				  											"currentApprovalStatus":currentApproverStatus,
				  											"currentApproverId":empId,
				  											"currentApprovalLevel":currentApprovelLevel,
				  											"approverComments":approverComments,
				  											"statusIndicator":currentApproval
				  									};
				  				
				  					                $http({
				  										 method: 'POST',
				  										 url: contextPath+"/RightPrice-DAS/updateApprovalDealData",
				  										 dataType: 'json',
				  										 data: angular.toJson(marker),  
				  							            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				  							    	}).
				  							    	then(function(data) {
				  							    		if(data.status == 200){
				  							    			var message = data.data;
				  							    	        BootstrapDialog.show({
				  							    	        	title : 'Deal Creation - Finalise Deal',
				  							    	        	type : BootstrapDialog.TYPE_PRIMARY,
				  							    	        	message : 'Deal Sent for Approval successfully',
				  							    	        	closable : false,
				  							    	        	buttons : [{
				  							    	        		label : 'OK',
				  							    	        		action : function(dialogRef) {
				  							    	        			dialogRef.close();
				  							    	        			window.location = "TMDealCreationFinaliseDeal";
				  							    	        		}
				  							    	        	}]
				  							    	        });
				  							    		}else{
				  							    			 message = data.data;
				  							    			BootstrapDialog.show({
				  							    	        	title : 'Deal Creation - Finalise Deal',
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
				    				            	   dialogRef.close();
				    								} 
				    				           }]
				    						});	
				    			}
				    				else{
				    					angular.forEach($scope.approverLevels, function(value, key) {
											
											 if(value.level == "Delivery Head"){
												$scope.approverLevels[key].empID = $scope.approverNameDetails[0].deliveryHeadId;
											}
											
										});
										

										var rpDealVersionId = rpVrsId;
										
										var empId = $scope.approverLevels[0].empID;
										
										var currentApproval = $scope.approverLevels[0].level;
										
										if(currentApproval == "Delivery Head") {
											currentApproval = "Delivery Head";
										}
										console.log("The current Approval indicator is....... "+ currentApproval)

										var currentApprovelLevel = $scope.dealApproverData[0].currentApprovalLevel +1;
										
										var gftComment = angular.element(document.getElementById('txtComment'));
						                var comment = angular.element(gftComment).val();
						                $scope.dealApproverData[0].approverComments=  [$scope.dealApproverData[0].approverComments] + "\n" + "" + [comment];
						                var approverComments =$scope.dealApproverData[0].approverComments;
						                
						                if($scope.dealDetails[0].currentApprovalStatus != 4) {
						                	var currentApproverStatus = $scope.dealDetails[0].currentApprovalStatus+1;
						                } else {
						                	var currentApproverStatus = $scope.dealDetails[0].currentApprovalStatus-2;
						                }
						                marker = {
												"rpDealVersionId":rpVrsId,
												"currentApprovalStatus":currentApproverStatus,
												"currentApproverId":empId,
												"currentApprovalLevel":currentApprovelLevel,
												"approverComments":approverComments,
												"statusIndicator":currentApproval
										};
					
						                $http({
											 method: 'POST',
											 url: contextPath+"/RightPrice-DAS/updateApprovalDealData",
											 dataType: 'json',
											 data: angular.toJson(marker),  
								            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
								    	}).
								    	then(function(data) {
								    		if(data.status == 200){
								    			var message = data.data;
								    	        BootstrapDialog.show({
								    	        	title : 'Deal Creation - Finalise Deal',
								    	        	type : BootstrapDialog.TYPE_PRIMARY,
								    	        	message : 'Deal Sent for Approval successfully',
								    	        	closable : false,
								    	        	buttons : [{
								    	        		label : 'OK',
								    	        		action : function(dialogRef) {
								    	        			dialogRef.close();
								    	        			window.location = "TMDealCreationFinaliseDeal";
								    	        		}
								    	        	}]
								    	        });
								    		}else{
								    			 message = data.data;
								    			BootstrapDialog.show({
								    	        	title : 'Deal Creation - Finalise Deal',
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
				    			WebServiceFactory.checkCustomerMappingInFin($sessionStorage.crmDealId).then(callNextMethod)
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
				
				/*$scope.updateApprovalStatus = function (response) {
					console.log("Into send for approval");
					
					angular.forEach($scope.approverLevels, function(value, key) {
						
						if(value.level == "DD"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[2];
						}
						else if(value.level == "Delivery Head"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[0].deliveryHeadId;
						}
						else if(value.level == "RiskManagers"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[0].RiskManagersPersonId;
						}
						else if(value.level == "GFT"){
							$scope.approverLevels[key].empID = "GFT";
						}
						else if(value.level == "CDO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[1].leaderLanID;
						}
						else if(value.level == "BU_Head"){
							$scope.approverLevels[key].empID = $scope.approverNameDetails[0].buHeadId;
						}
						else if(value.level == "COO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[1].leaderLanID;
						}
						else if(value.level == "CEO"){
							$scope.approverLevels[key].empID = $scope.leaderApproverNameDetails[0].leaderLanID;
						}		
						
					});
					
					var rpDealVersionId = rpVrsId;
					
					var empId = $scope.approverLevels[0].empID;
					
					var currentApproval = $scope.approverLevels[0].level;
					
					if(currentApproval == "Delivery Head") {
						currentApproval = "DUH";
					}
					console.log("The current Approval indicator is....... "+ currentApproval)

					var currentApprovelLevel = $scope.dealApproverData[0].currentApprovalLevel +1;
					
					var gftComment = angular.element(document.getElementById('txtComment'));
	                var comment = angular.element(gftComment).val();
	                $scope.dealApproverData[0].approverComments=  [$scope.dealApproverData[0].approverComments] + "\n" + "" + [comment];
	                var approverComments =$scope.dealApproverData[0].approverComments;
	                
	                if($scope.dealDetails[0].currentApprovalStatus != 4) {
	                	var currentApproverStatus = $scope.dealDetails[0].currentApprovalStatus+1;
	                } else {
	                	var currentApproverStatus = $scope.dealDetails[0].currentApprovalStatus-2;
	                }
					marker = {
							"rpDealVersionId":rpVrsId,
							"currentApprovalStatus":currentApproverStatus,
							"currentApproverId":empId,
							"currentApprovalLevel":currentApprovelLevel,
							"approverComments":approverComments,
							"statusIndicator":currentApproval
					};
//					console.log("Markers are");
//					console.log(marker);
					$http({
						 method: 'POST',
						 url: contextPath+"/RightPrice-DAS/updateApprovalDealData",
						 dataType: 'json',
						 data: angular.toJson(marker),  
			            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    	}).
			    	then(function(data) {
			    		if(data.status == 200){
			    			var message = data.data;
			    	        BootstrapDialog.show({
			    	        	title : 'Deal Creation - Finalise Deal',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Deal Sent for Approval successfully',
			    	        	closable : true,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationFinaliseDeal";
			    	        		}
			    	        	}]
			    	        });
			    		}else{
			    			 message = data.data;
			    			BootstrapDialog.show({
			    	        	title : 'Deal Creation - Finalise Deal',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : message,
			    	        	closable : true,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        		}
			    	        	}]
			    	        });
			    		}
				});
				
				}*/	
				
				$scope.updateRecycle = function (response) {
					BootstrapDialog.show({
				           title: 'T&M Deal Creation - Finalize Deal',
				           type : BootstrapDialog.TYPE_PRIMARY,
				           message : 'Do you want to Reject.',
				           closable: true,
				           buttons: [{
				               label: 'OK',
				               cssClass: 'btn-primary',
				               action: function(dialogRef) {
				            	   $scope.isDisabled = true;
									var rpDealVersionId = rpVrsId;
//									console.log("rc ID is..........." +rcId);
									
									var empId = $scope.approverLevels[0].empID;
//									console.log("Emp ID .............. "+empId);
									
									var currentApprovelLevel = $scope.dealApproverData[0].currentApprovalLevel;
									
									var gftComment = angular.element(document.getElementById('txtComment'));

					                var comment = angular.element(gftComment).val();
					                $scope.dealApproverData[0].approverComments=[$scope.dealApproverData[0].approverComments] + "\n" + "[GFT]:" +[comment];
					                var approverComments =$scope.dealApproverData[0].approverComments;
//									console.log("currentApproverLevel is................. "+ currentApprovelLevel);
									marker = {
											"rpDealVersionId":rpVrsId,
											"currentApprovalStatus":4,
											"currentApproverId":empId,
											"currentApprovalLevel":null,
											"approverComments":approverComments
									};
//									console.log("Markers are");
//									console.log(marker);
									$http({
										 method: 'POST',
										 url: contextPath+"/RightPrice-DAS/updateApprovalDealData",
										 dataType: 'json',
										 data: angular.toJson(marker),  
							            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
							    	}).
							    	then(function(data) {
							    		if(data.status == 200){
							    			var message = data.data;
							    	        BootstrapDialog.show({
							    	        	title : 'Deal Creation - Finalise Deal',
							    	        	type : BootstrapDialog.TYPE_PRIMARY,
							    	        	message : 'Deal Recycled Successfully',
							    	        	closable : false,
							    	        	buttons : [{
							    	        		label : 'OK',
							    	        		action : function(dialogRef) {
							    	        			dialogRef.close();
							    	        			window.location = "TMDealCreationFinaliseDeal";
							    	        		}
							    	        	}]
							    	        });
							    		}else{
							    			 message = data.data;
							    			BootstrapDialog.show({
							    	        	title : 'Deal Creation - Finalise Deal',
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
									dialogRef.close();
								}
							}]
						});
					
				}
				
				
				
				
				
				
				/*$scope.updateRecycle = function (response) {
					$scope.isDisabled = true;
					var rpDealVersionId = rpVrsId;
//					console.log("rc ID is..........." +rcId);
					
					var empId = $scope.approverLevels[0].empID;
//					console.log("Emp ID .............. "+empId);
					
					var currentApprovelLevel = $scope.dealApproverData[0].currentApprovalLevel;
					
					var gftComment = angular.element(document.getElementById('txtComment'));

	                var comment = angular.element(gftComment).val();
	                $scope.dealApproverData[0].approverComments=[$scope.dealApproverData[0].approverComments] + "\n" + "[GFT]:" +[comment];
	                var approverComments =$scope.dealApproverData[0].approverComments;
//					console.log("currentApproverLevel is................. "+ currentApprovelLevel);
					marker = {
							"rpDealVersionId":rpVrsId,
							"currentApprovalStatus":4,
							"currentApproverId":empId,
							"currentApprovalLevel":null,
							"approverComments":approverComments
					};
//					console.log("Markers are");
//					console.log(marker);
					$http({
						 method: 'POST',
						 url: contextPath+"/RightPrice-DAS/updateApprovalDealData",
						 dataType: 'json',
						 data: angular.toJson(marker),  
			            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    	}).
			    	then(function(data) {
			    		if(data.status == 200){
			    			var message = data.data;
			    	        BootstrapDialog.show({
			    	        	title : 'Deal Creation - Finalise Deal',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Deal Recycled Successfully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationFinaliseDeal";
			    	        		}
			    	        	}]
			    	        });
			    		}else{
			    			 message = data.data;
			    			BootstrapDialog.show({
			    	        	title : 'Deal Creation - Finalise Deal',
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
				}*/

			
		}]);