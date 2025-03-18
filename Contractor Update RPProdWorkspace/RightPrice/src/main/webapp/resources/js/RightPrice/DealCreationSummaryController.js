			app.controller("DealCreationSummaryController",   ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
			 $scope.DealSummaryHidden = true;
			 $scope.ApproverActionHidden = true;
			 $scope.TeamDetailsHidden = true;
			 $scope.showaprovercoment = false;
			  $scope.isSubmitDisable = true;
			  var rpVrsId = $localStorage.rpDealVersionId;
			  $localStorage.DealModel=$localStorage.crmDealId;//commented for session values auto
			  $sessionStorage.diffEqualZero = $sessionStorage.diffEqualZero;
			  $scope.deligateUserArray = $localStorage.deligateUser;
			  console.log("The Deligate user array is.............");
			  console.log($scope.deligateUserArray);
			  $scope.proxyUserId = $sessionStorage.proxyId;
			  $scope.ratecardLenght=0;
			  $localStorage.ratecardCount=0;
			 var userType = sessionStorage.getItem('userType');
			 $scope.GFTApprover = false;
			// $scope.isSubmitDisable = false;
             $scope.ShowHideDealSummary = function () {
                $scope.DealSummaryHidden = $scope.DealSummaryHidden ? false : true;
            };
             $scope.ShowHideTeamDetails = function () {
                $scope.TeamDetailsHidden = $scope.TeamDetailsHidden ? false : true;
            };
            $scope.ShowHideApproverAction = function () {
                $scope.ApproverActionHidden = $scope.ApproverActionHidden ? false : true;
            };
            var rpVrsId = $localStorage.rpDealVersionId;
            $scope.currentDate = new Date();
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
		        //window.location='TMDealCreationUploadEstimationRelatedDocuments';
				window.location='TMDealCreationStaffing';
		    }; 
		     $scope.Next = function()
		    { 
		    	$sessionStorage.diffEqualZero = $sessionStorage.diffEqualZero;
		        window.location='TMDealCreationFinaliseDeal';
		    };
		    var rpVersionId = $localStorage.rpDealVersionId;
		    $scope.rateCardDetailArray = $sessionStorage.rateCardDetailsList;
		    console.log("The array for rate card in the Deal Summary Page is.......... ");
		    console.log($scope.rateCardDetailArray);
		    $scope.memberDetail = [];
		    var dealId = 0;
		    $scope.checkApprover = false;
			
			var getVersionData = function(response) {
				console.log("get versionDetails Data..");
				console.log(response);
				$scope.getVersionData=response.data;
				
				if($scope.getVersionData!=undefined)
				{
					$localStorage.pageTracker=$scope.getVersionData[0].pageTrackerStatus;
					$localStorage.CurrentApprovalStatus=$scope.getVersionData[0].currentApprovalStatus;
					if(userType=='Delivery')
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
			WebServiceFactory.getVersionData(rpVersionId).then(getVersionData);
		    
		    $window.onload = function() 
			{	
		    	/*if($sessionStorage.diffEqualZero != 0) 
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
		    		
				}*/
		    	
		    	//$scope.getRatecardSummaryData();
		    				 			
			};
		    
		   
		    var getGFTMemberDetails = function(response){
				console.log("getGFTMemberDetails");
				console.log(response);
				$scope.memberDetail=response.data;
			}
			WebServiceFactory.getGFTMemberDetails().then(getGFTMemberDetails);
			
		    var getDealDetails = function(response) {
				console.log("Deal Details");
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType==1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId==1)? "Fixed Price" :"T & M";
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
				$scope.dealDetails[0].rpVersionId = rpVersionId;	//arvind
				$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;
				 var startDay = new Date(dateStart);
                  var endDay = new Date(dateENd);
                  var millisecondsPerDay = 1000 * 60 * 60 * 24;
                  var millisBetween =  endDay.getTime()-startDay.getTime() 
                  var days = millisBetween / millisecondsPerDay;
                  $scope.dealDetails[0].DealDuration = Math.floor(days);
				
                  dealId = $scope.dealDetails[0].dealId;
                  $scope.teamDeal.txtDealIdModel = $scope.dealDetails[0].dealId;
                  $scope.teamDeal.txtEstimatedTCVModel = $scope.dealDetails[0].dealTCV;
                  $localStorage.tCVModelValue= $scope.dealDetails[0].dealTCV;
                  $scope.teamDeal.txtEstimatedRevenueModel = $scope.dealDetails[0].estimatedRevenue;                  
                  $scope.currencyName = $scope.dealDetails[0].currency;
                  
                  var statusId = $scope.dealDetails[0].currentApprovalStatus;
                  $sessionStorage.statusIdSSN =statusId;
                 /* if(statusId != 2) {
                	  $scope.isSubmitDisable = true;
                  }*/
                  
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
                  
                  
			};
			WebServiceFactory.getDealDetails($localStorage.DealModel,$localStorage.rpDealVersionId).then(getDealDetails);
			
			
			
			var getRatecardSummaryData = function(response)
			{
				$scope.ratecardSummary = response.data;
				$scope.ratecardLenght = response.data.length;
				$localStorage.ratecardCount = response.data.length;
				var total_weightageXbl_rate=0;
				var total_weightageXbl_cost=0;				
				var i =0;
				for (i = 0; i < $scope.ratecardSummary.length; i++) 
				{ 					
					total_weightageXbl_rate += $scope.ratecardSummary[i].weightageXbl_rate;
					total_weightageXbl_cost += $scope.ratecardSummary[i].weightageXbl_cost;
					$scope.final_GmSum += $scope.ratecardSummary[i].final_GM;
					if($scope.ratecardLenght==1)
					{
						$scope.teamDeal.txtFinalGMPerModel = $scope.ratecardSummary[0].gm_Per;
						$localStorage.txtFinalGMPerModel =  $scope.ratecardSummary[0].gm_Per;
					}
					
				}				
				var temp = ((total_weightageXbl_rate-total_weightageXbl_cost)/total_weightageXbl_rate)*100;	
				
				if($localStorage.ratecardCount == 1)
				{
					$scope.updateRP_Deal_Table();
				}
				
				$scope.getFinal_GM_Per_Sum();
				
					
			}
			WebServiceFactory.getRatecardSummaryData($localStorage.rpDealVersionId).then(getRatecardSummaryData);
			
			
			
			var getStaffingPercentage = function(response)
			{
				$scope.staffingPer = response.data;
				
				var total_staffing =0;
				var total_offshore =0;
				var total_staffing = $scope.staffingPer[0].totalStaffing;
				var total_offshore = $scope.staffingPer[0].totalOffshore;
				var ofshorePer =	(total_offshore/total_staffing)*100;
				var onsitePer =  (100 - ofshorePer);
				
				$scope.calc_total_offshore  = ofshorePer.toFixed(2);
				$scope.calc_total_onsitePer = onsitePer.toFixed(2);
				
				/*var temp = ((total_weightageXbl_rate-total_weightageXbl_cost)/total_weightageXbl_rate)*100;
				$scope.calc_total_GM =temp.toFixed(2);*/
			
			}
			WebServiceFactory.getStaffingPercentage(rpVrsId).then(getStaffingPercentage);
			
			
			
			
			
			var getFinal_GM_Per= function(response)
			{
				$scope.finalGMPer = response.data;				
				
			}			
			WebServiceFactory.getFinal_GM_Per($localStorage.rpDealVersionId).then(getFinal_GM_Per);
			
			$scope.getFinal_GM_Per_Sum = function()
			{
				var getFinal_GM_Per_Sum= function(response)
				{
					$scope.finalGMPer_Sum = response.data;
					$scope.calc_total_GM =$scope.finalGMPer_Sum[0].totalOffshore;
					$localStorage.calc_total_GM_session = $scope.finalGMPer_Sum[0].totalOffshore;			
					
					if($localStorage.ratecardCount >= 2)
					{
						$scope.updateRP_Deal_Table();
					}
				}			
				WebServiceFactory.getFinal_GM_Per_Sum($localStorage.rpDealVersionId).then(getFinal_GM_Per_Sum);			
			}
			
			
			
			$scope.updateRP_Deal_Table = function()
			{
				if($localStorage.ratecardCount==1)
					{						
						if($localStorage.rpDealVersionId != undefined && $localStorage.txtFinalGMPerModel != undefined  && $localStorage.tCVModelValue != undefined )
						{
							WebServiceFactory.updateRP_Deal_Table($localStorage.rpDealVersionId,$localStorage.txtFinalGMPerModel,$localStorage.tCVModelValue);
						}					
					}
				if($localStorage.ratecardCount >=2 )
					{					
						if($localStorage.rpDealVersionId != undefined && $localStorage.calc_total_GM_session != undefined  && $localStorage.tCVModelValue != undefined )
						{
							WebServiceFactory.updateRP_Deal_Table($localStorage.rpDealVersionId,$localStorage.calc_total_GM_session,$localStorage.tCVModelValue);
						}					
					}								
			}
			
			var getGFTMemberDetails = function(response){
				console.log("getGFTMemberDetails");
				console.log(response);
				$scope.memberDetail=response.data;
			}
			WebServiceFactory.getGFTMemberDetails().then(getGFTMemberDetails);
			
			// get the deal details 
			$scope.getDealSummaryData = function(user){
				console.log("The current User is.......... "+ user);
				//alert("inside getRateCardDetails");
				for(var i=0;i<$scope.memberDetail.length;i++){
					if($scope.memberDetail[i].employeeLanId == user && $scope.memberDetail[i].roleType == 6){
						$scope.isValidGFTMember = true;
					}
				}
				var getDealSummaryData = function(response) {
					//alert("get RateCardDetails");
					console.log("get Deal Approver Details");
					console.log(response);
				    $scope.currentDealData = response.data;
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
				    if(response.status == 200){
					    if($scope.currentDealData[0].currentApproverId == user || ($scope.currentDealData[0].currentApproverRoleId!= undefined && $scope.currentDealData[0].currentApproverRoleId == $scope.approverRoleId) || $scope.isValidGFTMember == true){
					    	
					    //	alert("inside if");
					    	if($sessionStorage.statusIdSSN == 2)
					    		{
					    		$scope.isSubmitDisable = false;
					    		}
					    	
					    	$scope.checkApprover = true;
					    	$scope.showaprovercoment = false;
					    	$scope.teamDeal.approverCommentModel =$scope.currentDealData[0].approverComments;
					    	//$scope.frmRateCardCreation.approverCommentModel = $scope.rateCardDetails[0].approverComments;
					    	
					    } else if($scope.proxyUserId != undefined) {
					    	if($scope.currentDealData[0].currentApproverId ==  $scope.proxyUserId ) {
					    		if($sessionStorage.statusIdSSN == 2)
					    		{
					    			$scope.isSubmitDisable = false;
					    		}
					    		
					    		$scope.checkApprover = true;
					    		$scope.showaprovercoment = false;
					    		$scope.teamDeal.approverCommentModel =$scope.currentDealData[0].approverComments;
					    	}   else
						    {
							      $scope.teamDeal.approverCommentModel =$scope.currentDealData[0].approverComments;
							    	if($scope.teamDeal.approverCommentModel =="")
							    		{
							    			$scope.showaprovercoment = false;
							    		}
							    	$scope.showaprovercoment = true;
							    }
					    }
					    else
					    {
					      $scope.teamDeal.approverCommentModel =$scope.currentDealData[0].approverComments;
					    	if($scope.teamDeal.approverCommentModel =="")
					    		{
					    			$scope.showaprovercoment = false;
					    		}
					    	$scope.showaprovercoment = true;
					    }
				    }
				};
				WebServiceFactory.getDealSummaryData($localStorage.DealModel,rpVersionId).then(getDealSummaryData);
			};
			
			$scope.getDealApprovalComments=function(user,selectionTypeAR){
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
				$scope.selectionTypeAR=selectionTypeAR;
				$scope.commentbox= true;
			};
			
		
			$scope.saveApprovalData=function(user){
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
				BootstrapDialog.show({
			           title: 'T&M Deal Creation - Summary',
			           type : BootstrapDialog.TYPE_PRIMARY,
			           message : 'Do you want to Approve?',
			           closable: true,
			           buttons: [{
			               label: 'Yes',
			               cssClass: 'btn-primary',
			               action: function(dialogRef) {
			            	   var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
			                   var comment = angular.element(approverId).val();
			            	   
								if ($.trim(comment) == '') {
									dialogRef.close();
									return false;
								}
								else
								{
									$scope.teamDeal.currentApproverCommentModel = "["+user+"]: " + comment;
									$scope.$apply();
									if($scope.teamDeal.approverCommentModel){
										var commnetBoxTxt = "["+user+"]: " + comment;
										$scope.teamDeal.approverCommentModel += '\n'+ commnetBoxTxt;
										$scope.$apply();
									}
									else{
										$scope.teamDeal.approverCommentModel = "["+user+"]: " + comment;
										$scope.$apply();
									}
									
									if($scope.teamDeal.approverCommentModel.length > 700)
									{
										var commentLen = $scope.teamDeal.approverCommentModel.length;
										var SkipChars = commentLen - 700;					
										$scope.teamDeal.approverCommentModel = $scope.teamDeal.approverCommentModel.substring(SkipChars, commentLen);
										$scope.$apply();
									}
								}
								var markers = {
											"rcOrDealID":$localStorage.DealModel,//need to get while on page load
											"approverId":user,
											"approvalStatus":$scope.teamDeal.ddlApproverAction,
											"approvalComments":$scope.teamDeal.currentApproverCommentModel,
											"approvalDate": $scope.currentDate,
											"isRateCard":0
											};
				var saveApprovalData = function(response) {
					if(response.data == 200){
						$scope.updatedAuditDetails=1;
					}
				}
				WebServiceFactory.saveApprovalData(markers).then(saveApprovalData);
				
				//chk approver role id is null or not
					if($scope.currentDealData[0].level1ApproverRoleId!=null){
						$scope.setRoleId = 1;
					}
					if($scope.currentDealData[0].level2ApproverRoleId!=null){
						$scope.setRoleId = 2;
					}
					if($scope.currentDealData[0].level3ApproverRoleId!=null){
						$scope.setRoleId = 3;
					}
					if($scope.currentDealData[0].level4ApproverRoleId!=null){
						$scope.setRoleId = 4;
					}
					if($scope.currentDealData[0].level5ApproverRoleId!=null){
						$scope.setRoleId = 5;
					}
					if($scope.currentDealData[0].level6ApproverRoleId!=null){
						$scope.setRoleId = 6;
					}
					if($scope.currentDealData[0].level7ApproverRoleId!=null){
						$scope.setRoleId = 7;
					}
				
				//$scope.maxRoleId = $scope.setRoleId;
				
			//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
				
				/*if(($scope.currentDealData[0].currentApprovalLevel+1) == 2){
					$scope.getLanid = $scope.currentDealData[0].level2ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 3){
					$scope.getLanid = $scope.currentDealData[0].level3ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 4){
					$scope.getLanid = $scope.currentDealData[0].level4ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 5){
					$scope.getLanid = $scope.currentDealData[0].level5ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 6){
					$scope.getLanid = $scope.currentDealData[0].level6ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 7){
					$scope.getLanid = $scope.currentDealData[0].level7ApproverRoleId;
				}*/
				
				
				
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
						$scope.lanId = $scope.leadershipDetails[1].leaderLanID;
						$scope.roleId = 7;
					}
					else if($scope.getLanid == 8){
						$scope.lanId = $scope.verticalDetails[0].buHeadId;
						$scope.roleId = 8;
					}
					else if($scope.getLanid == 9){
						$scope.lanId = $scope.leadershipDetails[2].leaderLanID;
						$scope.roleId = 9;
					}
					else if($scope.getLanid == 10 && $scope.leadershipDetails[0].leaderId){
						$scope.lanId = $scope.leadershipDetails[0].leaderLanID;
						$scope.roleId = 10;
					}
					
					/*//if($scope.setRoleId == $scope.getLanid){
					if($scope.getLanid == null){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverAction,
								"currentApproverId":user,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":$scope.currentDealData[0].currentApprovalLevel,
								"currentApproverRoleId":$scope.roleId
						};
					}*/
					
					
					else if($scope.teamDeal.ddlApproverAction == 3){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverAction,
								"currentApproverId":null,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":null,
								"currentApproverRoleId":null,
								"statusIndicator":"Approved"
						};
					}
					
					else if($scope.teamDeal.ddlApproverAction == 4){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverAction,
								"currentApproverId":null,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":null,
								"currentApproverRoleId":null,
								"statusIndicator":"Approved"
						};
					}
					
					var updateApprovalDataForDeal = function(response) {
						$scope.updatedAuditDetails=1;
						if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.teamDeal.ddlApproverAction == 1){
			    	        BootstrapDialog.show({
			    	        	title : 'T&M Deal Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Request Rejected SucessFully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationSummary"
			    	        		}
			    	        	}]
			    	        });
				        }
						else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.teamDeal.ddlApproverAction == 3){
			    	        BootstrapDialog.show({
			    	        	title : 'T&M Deal Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Request Approved SucessFully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationSummary"
			    	        		}
			    	        	}]
			    	        });
				        }
				        else{
				        	BootstrapDialog.show({
			    	        	title : 'T&M Deal Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : "Currently we are facing technical issue. Please try again later.",
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationSummary"
			    	        		}
			    	        	}]
			    	        });
				        }
					}
					WebServiceFactory.updateApprovalDataForDeal(marker).then(updateApprovalDataForDeal);
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
			
			else if($scope.selectionTypeAR==4)
				{

				BootstrapDialog.show({
			           title: 'T&M Deal Creation - Summary',
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
									return false;
								}
								else
								{
									$scope.teamDeal.currentApproverCommentModel = "["+user+"]: " + comment;
									$scope.$apply();
									if($scope.teamDeal.approverCommentModel){
										var commnetBoxTxt = "["+user+"]: " + comment;
										$scope.teamDeal.approverCommentModel += '\n'+ commnetBoxTxt;
										$scope.$apply();
									}
									else{
										$scope.teamDeal.approverCommentModel = "["+user+"]: " + comment;
										$scope.$apply();
									}
									
									if($scope.teamDeal.approverCommentModel.length > 700)
									{
										var commentLen = $scope.teamDeal.approverCommentModel.length;
										var SkipChars = commentLen - 700;					
										$scope.teamDeal.approverCommentModel = $scope.teamDeal.approverCommentModel.substring(SkipChars, commentLen);
										$scope.$apply();
									}
								}
								var markers = {
											"rcOrDealID":$localStorage.DealModel,//need to get while on page load
											"approverId":user,
											"approvalStatus":$scope.teamDeal.ddlApproverAction,
											"approvalComments":$scope.teamDeal.currentApproverCommentModel,
											"approvalDate": $scope.currentDate,
											"isRateCard":0,
											"statusIndicator":"Recycled"
											};
				var saveApprovalData = function(response) {
					if(response.data == 200){
						$scope.updatedAuditDetails=1;
					}
				}
				WebServiceFactory.saveApprovalData(markers).then(saveApprovalData);
				
				//chk approver role id is null or not
					if($scope.currentDealData[0].level1ApproverRoleId!=null){
						$scope.setRoleId = 1;
					}
					if($scope.currentDealData[0].level2ApproverRoleId!=null){
						$scope.setRoleId = 2;
					}
					if($scope.currentDealData[0].level3ApproverRoleId!=null){
						$scope.setRoleId = 3;
					}
					if($scope.currentDealData[0].level4ApproverRoleId!=null){
						$scope.setRoleId = 4;
					}
					if($scope.currentDealData[0].level5ApproverRoleId!=null){
						$scope.setRoleId = 5;
					}
					if($scope.currentDealData[0].level6ApproverRoleId!=null){
						$scope.setRoleId = 6;
					}
					if($scope.currentDealData[0].level7ApproverRoleId!=null){
						$scope.setRoleId = 7;
					}
				
				//$scope.maxRoleId = $scope.setRoleId;
				
			//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
				
				/*if(($scope.currentDealData[0].currentApprovalLevel+1) == 2){
					$scope.getLanid = $scope.currentDealData[0].level2ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 3){
					$scope.getLanid = $scope.currentDealData[0].level3ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 4){
					$scope.getLanid = $scope.currentDealData[0].level4ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 5){
					$scope.getLanid = $scope.currentDealData[0].level5ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 6){
					$scope.getLanid = $scope.currentDealData[0].level6ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 7){
					$scope.getLanid = $scope.currentDealData[0].level7ApproverRoleId;
				}*/
				
				
				
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
						$scope.lanId = $scope.leadershipDetails[1].leaderLanID;
						$scope.roleId = 7;
					}
					else if($scope.getLanid == 8){
						$scope.lanId = $scope.verticalDetails[0].buHeadId;
						$scope.roleId = 8;
					}
					else if($scope.getLanid == 9){
						$scope.lanId = $scope.leadershipDetails[2].leaderLanID;
						$scope.roleId = 9;
					}
					else if($scope.getLanid == 10 && $scope.leadershipDetails[0].leaderId){
						$scope.lanId = $scope.leadershipDetails[0].leaderLanID;
						$scope.roleId = 10;
					}
					
					/*//if($scope.setRoleId == $scope.getLanid){
					if($scope.getLanid == null){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverAction,
								"currentApproverId":user,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":$scope.currentDealData[0].currentApprovalLevel,
								"currentApproverRoleId":$scope.roleId
						};
					}*/
					
					
					else if($scope.teamDeal.ddlApproverAction == 3){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverAction,
								"currentApproverId":null,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":null,
								"currentApproverRoleId":null,
								"statusIndicator":"Recycled"
						};
					}
					
					else if($scope.teamDeal.ddlApproverAction == 4){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverAction,
								"currentApproverId":null,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":null,
								"currentApproverRoleId":null,
								"statusIndicator":"Recycled"
						};
					}
					
					var updateApprovalDataForDeal = function(response) {
						$scope.updatedAuditDetails=1;
						if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.teamDeal.ddlApproverAction == 1){
			    	        BootstrapDialog.show({
			    	        	title : 'T&M Deal Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Request Rejected SucessFully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationSummary"
			    	        		}
			    	        	}]
			    	        });
				        }
						else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.teamDeal.ddlApproverAction == 3){
			    	        BootstrapDialog.show({
			    	        	title : 'T&M Deal Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Request Approved SucessFully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationSummary"
			    	        		}
			    	        	}]
			    	        });
				        }else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.teamDeal.ddlApproverAction == 4){
			    	        BootstrapDialog.show({
			    	        	title : 'T&M Deal Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Request Rejected SucessFully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationSummary"
			    	        		}
			    	        	}]
			    	        });
				        }
				        else{
				        	BootstrapDialog.show({
			    	        	title : 'T&M Deal Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : "Currently we are facing technical issue. Please try again later.",
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "TMDealCreationSummary"
			    	        		}
			    	        	}]
			    	        });
				        }
					}
					WebServiceFactory.updateApprovalDataForDeal(marker).then(updateApprovalDataForDeal);
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
	        	title : 'TM Deal Creation - What If Main',
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
			
			/*$scope.saveApprovalData = function(user){
				//alert("inside save approval data");
				//save approval data in audit table
				var markers = {
						"rcOrDealID":$localStorage.DealModel,//need to get while on page load
						"approverId":user,
						"approvalStatus":$scope.teamDeal.ddlApproverActionModel,
						"approvalComments":$scope.teamDeal.currentApproverCommentModel,
						"approvalDate": $scope.currentDate,
						"isRateCard":0
				};
				var saveApprovalData = function(response) {
					if(response.data == 200){
						$scope.updatedAuditDetails=1;
					}
				}
				WebServiceFactory.saveApprovalData(markers).then(saveApprovalData);
				
				//chk approver role id is null or not
					if($scope.currentDealData[0].level1ApproverRoleId!=null){
						$scope.setRoleId = 1;
					}
					if($scope.currentDealData[0].level2ApproverRoleId!=null){
						$scope.setRoleId = 2;
					}
					if($scope.currentDealData[0].level3ApproverRoleId!=null){
						$scope.setRoleId = 3;
					}
					if($scope.currentDealData[0].level4ApproverRoleId!=null){
						$scope.setRoleId = 4;
					}
					if($scope.currentDealData[0].level5ApproverRoleId!=null){
						$scope.setRoleId = 5;
					}
					if($scope.currentDealData[0].level6ApproverRoleId!=null){
						$scope.setRoleId = 6;
					}
					if($scope.currentDealData[0].level7ApproverRoleId!=null){
						$scope.setRoleId = 7;
					}
				
				//$scope.maxRoleId = $scope.setRoleId;
				
			//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
				
				if(($scope.currentDealData[0].currentApprovalLevel+1) == 2){
					$scope.getLanid = $scope.currentDealData[0].level2ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 3){
					$scope.getLanid = $scope.currentDealData[0].level3ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 4){
					$scope.getLanid = $scope.currentDealData[0].level4ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 5){
					$scope.getLanid = $scope.currentDealData[0].level5ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 6){
					$scope.getLanid = $scope.currentDealData[0].level6ApproverRoleId;
				}
				else if(($scope.currentDealData[0].currentApprovalLevel+1) == 7){
					$scope.getLanid = $scope.currentDealData[0].level7ApproverRoleId;
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
						$scope.lanId = $scope.verticalDetails[0].buHeadId;
						$scope.roleId = 8;
					}
					else if($scope.getLanid == 9){
						$scope.lanId = $scope.leadershipDetails[2].leaderLanID;
						$scope.roleId = 9;
					}
					else if($scope.getLanid == 10 && $scope.leadershipDetails[0].leaderId){
						$scope.lanId = $scope.leadershipDetails[0].leaderLanID;
						$scope.roleId = 10;
					}
					
					//if($scope.setRoleId == $scope.getLanid){
					if($scope.getLanid == null){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverActionModel,
								"currentApproverId":user,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":$scope.currentDealData[0].currentApprovalLevel,
								"currentApproverRoleId":$scope.roleId
						};
					}
					
					
					else if($scope.teamDeal.ddlApproverActionModel == 3){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":2,
								"currentApproverId":$scope.lanId,
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":$scope.currentDealData[0].currentApprovalLevel+1,
								"currentApproverRoleId":$scope.roleId
						};
					}
					
					else if($scope.teamDeal.ddlApproverActionModel == 1){
						var marker = {
								"crmDealId":$localStorage.DealModel,
								"rpDealVersionId":rpVersionId,
								"currentApprovalStatus":$scope.teamDeal.ddlApproverActionModel,
								"currentApproverId":"",
								"approverComments":$scope.teamDeal.approverCommentModel,
								"currentApprovalLevel":"",
								"currentApproverRoleId":""
						};
					}
					
					
					var updateApprovalDataForDeal = function(response) {
						
						if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.teamDeal.ddlApproverActionModel == 1){
			    	        BootstrapDialog.show({
			    	        	title : 'Rate Card Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Request Rejected SucessFully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        		}
			    	        	}]
			    	        });
				        }
						else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.teamDeal.ddlApproverActionModel == 3){
			    	        BootstrapDialog.show({
			    	        	title : 'Rate Card Creation - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Request Approved SucessFully',
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
					WebServiceFactory.updateApprovalDataForDeal(marker).then(updateApprovalDataForDeal);
			};
		    */
			var verticalId = 0;
			var getCurrentUserDetails = function(response) {
				console.log("Current user details");
				verticalId=response.data[0].verticalId;
				$scope.getVerticalDetails(verticalId);
			};
			WebServiceFactory.getCurrentUserDetails().then(getCurrentUserDetails);
			
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
			
		}]);