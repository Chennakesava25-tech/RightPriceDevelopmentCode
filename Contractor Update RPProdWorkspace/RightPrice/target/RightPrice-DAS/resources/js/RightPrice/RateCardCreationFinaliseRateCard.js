//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
app.filter('trusted', ['$sce', function ($sce) {
    return function(url) {
        return $sce.trustAsResourceUrl(url);
    };
}]);
app.controller("MapRolesToClientRoleController", ['$scope','$http','$filter','$location','$anchorScroll','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$http,$filter,$location,$anchorScroll,$window,WebServiceFactory,$localStorage, $sessionStorage,$index) {
     $scope.Prev = function()
    {   
        window.location='RateCardCreationSummary';
    }; 
    
    var contextPath = "/RightPrice-DAS";
    $scope.approverLevels = [];
    $scope.status = [];
    $localStorage.assumtion_id = 1;
    $scope.isRecycleBtn = false;
    var userType = sessionStorage.getItem('userType');
    $scope.commentBox = false;
    $scope.isDisabled = false;
    $scope.RainbowLevelDD=false;
    $scope.ApprovalLevelFlag = $sessionStorage.raibowApprovalLevel;
    $scope.rainbowApprovalLevel = [{id: 5,name: "Level 1"}, { id: 6,name: "Level 2" }];
    $scope.rainbowApprovalFlag = [{id: 1,name: "Yes"}, { id: 2,name: "No" }];
    $scope.rainbowMatrixPanelHide = true;
    if($sessionStorage.raibowApprovalLevel == null || $sessionStorage.raibowApprovalLevel == undefined ) {
    	$scope.ApprovalLevelFlag = 0;
    }
    
    if($localStorage.rcId != 0 && $localStorage.rcId != undefined) {
		var getRateCardInfo = function(response) {
	  		console.log(response);
	  		$scope.rateCardInfo = response.data;
	  		console.log("Master Data");
	  		console.log($scope.rateCardInfo);
	  		console.log("Start Date of the RC is");
	  		console.log($localStorage.rcId);
	  		
	  		console.log("RBU...")
	  		$sessionStorage.rbutype = $scope.rateCardInfo[0].rbutype ;
	  		console.log($scope.rateCardInfo[0].rbutype); //manglam updated
	  		
	  		var rcStartDate  = $scope.rateCardInfo[0].rcStartDate;
	  		var date = new Date(rcStartDate.substring(0,10));
	  		//console.log("Rc Start Date is......... " + date);
	  		var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.rateCardInfo[0].rcStartDate = rcStartDD;
	  		
	  		var rcEndDate  = $scope.rateCardInfo[0].rcEndDate;
	  		var date = new Date(rcEndDate.substring(0,10));
	  		//console.log("Rc End Date is......... " + date);
	  		var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.rateCardInfo[0].rcEndDate = rcEndDateDD;
	  		
	  		var expectedRCEndDate  = $scope.rateCardInfo[0].expectedRCEndDate;
	  		var date = new Date(expectedRCEndDate.substring(0,10));
	  		var expectedRCEndDateDD = $filter('date')(date,'dd/MM/yyyy');
	  		$scope.rateCardInfo[0].expectedRCEndDate = expectedRCEndDateDD;
	  		
	  		var currencyId = $scope.rateCardInfo[0].consolidatedRcCurrencyId;
	  		var statusId = $scope.rateCardInfo[0].currentApprovalStatus;
	  		$scope.customerVerticalID = $scope.rateCardInfo[0].customerVerticalMapping.verticalId;
	  		var approvalId = $scope.rateCardInfo[0].currentApproverId;
	  		//$scope.currencyName = [];
	  		$scope.currentApprovalStatus = $scope.rateCardInfo[0].currentApprovalStatus;
	  		$localStorage.currentApprovalLevel = $scope.rateCardInfo[0].currentApprovalLevel;
	  		$sessionStorage.isManualType = $scope.rateCardInfo[0].isManualRc;
	  		
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
	  		
	  		switch (statusId) {
	  		case 1 : 
	  			$scope.status = "Draft";
	  			angular.forEach($scope.rateCardInfo,function (value, key) {
	  				$scope.rateCardInfo[key].currentStatus = $scope.status;
	  			});
	  			break;
			case 2 : 
				$scope.status = "Pending Approval";
				angular.forEach($scope.rateCardInfo,function (value, key) {
	  				$scope.rateCardInfo[key].currentStatus = $scope.status;
	  			});
				break;
			case 3 : 
				$scope.status = "Approved";
				angular.forEach($scope.rateCardInfo,function (value, key) {
	  				$scope.rateCardInfo[key].currentStatus = $scope.status;
	  			});
				break;
			case 4 : 
				$scope.status = "Recycled";
				angular.forEach($scope.rateCardInfo,function (value, key) {
	  				$scope.rateCardInfo[key].currentStatus = $scope.status;
	  			});
				break;
			case 5 : 
				$scope.status = "Deactivated";
				angular.forEach($scope.rateCardInfo,function (value, key) {
	  				$scope.rateCardInfo[key].currentStatus = $scope.status;
	  			});
				break;
			case 6 : 
				$scope.status = "Expired";
				angular.forEach($scope.rateCardInfo,function (value, key) {
	  				$scope.rateCardInfo[key].currentStatus = $scope.status;
	  			});
				break;
			case 7 : 
				$scope.status = "Manual with GFT";
				angular.forEach($scope.rateCardInfo,function (value, key) {
	  				$scope.rateCardInfo[key].currentStatus = $scope.status;
	  			});
				break;
			default : 
	  			break;
	  		}
	  		
	  		if(userType == "GFT" && (statusId == 7||statusId == 4)) {
	  			$scope.commentBox = true;
    			$scope.isRecycleBtn = true;
    			$scope.isDisabled = false;
    		}
	  		else
	  			{
	  			$scope.commentBox = false;
	  			$scope.isRecycleBtn = false;
	  			$scope.isDisabled = true;
	  			}
	  		
	  		if($sessionStorage.isManualType == "M")
			{
				$scope.manualsidebar=true;
				$scope.autosidebar=false;
				$scope.hybridsidebar=false;
			}
		else if ($sessionStorage.isManualType == "H")
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
	  		$scope.rainbowFlagLevel=$scope.rateCardInfo[0].rainbowApprovalFlag;
	  		if($scope.rainbowFlagLevel!=null)
	  			{
		  			if($scope.rainbowFlagLevel==1)
		  			{
		  				$scope.frmRateCardCreationFin.rainbowApprovalRequired=$scope.rainbowApprovalFlag[0].id;
		  				$scope.approvalFlagLevel=$scope.rateCardInfo[0].approvalFlagLevel;
		  		  		if($scope.approvalFlagLevel!=null)
		  	  			{
		  		  			$scope.RainbowLevelDD=true;
		  		  			if($scope.approvalFlagLevel==5)
		  		  			{
		  		  				$scope.frmRateCardCreationFin.rainbowApprovalLevel=$scope.rainbowApprovalLevel[0].id;
		  		  			}
		  		  		else
		  		  			{
		  		  				$scope.frmRateCardCreationFin.rainbowApprovalLevel=$scope.rainbowApprovalLevel[1].id;
		  		  			}
		  	  			}
		  			}
		  		else
		  			{
		  				$scope.frmRateCardCreationFin.rainbowApprovalRequired=$scope.rainbowApprovalFlag[1].id;
		  			}
	  			}
	  		
	  		if($scope.rateCardInfo[0].isManualRc == 'A' || $scope.rateCardInfo[0].isManualRc == 'H' ) {
	  			if(userType == 'Delivery') {
	  				$scope.rainbowMatrixPanelHide = false;
	  			} else {
	  				$scope.rainbowMatrixPanelHide = true;
	  			}
	  		} else if($scope.rateCardInfo[0].isManualRc == 'M') {
	  			if(userType == 'Delivery') {
	  				$scope.rainbowMatrixPanelHide = false;
	  			} else if(userType == 'GFT' && $scope.currentApprovalStatus == 7) {
	  				$scope.rainbowMatrixPanelHide = false;
	  			}
	  		}
	  		
	  		
	  		
		};
		WebServiceFactory.getRateCardInfo($localStorage.rcId).then(getRateCardInfo);
		
		  var getCountryDetail = function(response) {
				$scope.countryData = response.data;
				console.log("$scope.countryData");
				console.log($scope.countryData);
			}
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
		
		var getPageTrackerData = function(response){
			$scope.pageTrackerData = response.data;
			/*console.log("page tracker data");
			console.log($scope.pageTrackerData);*/
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
		WebServiceFactory.getPageTrackerData($localStorage.rcId).then(getPageTrackerData);	
		
		  var getRateCardAssumtion = function(response) {
				
				console.log("get ratecard assumption");
				console.log(response);
			    $scope.ratecardAssumption = response.data;
			    console.log($scope.ratecardAssumption);
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
		WebServiceFactory.getRateCardAssumtion($localStorage.assumtion_id).then(getRateCardAssumtion);
		
		}
		else {
			 BootstrapDialog.show({
					title : 'Rate Card - Finalize Rate Card',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Please Create New Rate Card or Select Existing One.',
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							//$window.location.reload();
							window.location="RateCardCreationDetails";
						}
					} ]
				});
		}
	
	// getting the approver designation 
    $window.onload = function() {
    	var getApproverInfo = function(response) {
	  		$scope.approverProcInfo = response.data;
	  		if($scope.approverProcInfo != undefined ) {
	  			
	  			$scope.getApproverData($localStorage.rcId);
	  		}
	  		
		};
		WebServiceFactory.getApproverInfo($localStorage.rcId).then(getApproverInfo);
		
    }
	  

		
		// Getting the approver name using vertical Id
			var getApproverName = function(response) {
				
				$scope.approverNames = [];
				$scope.approverNameDetails = response.data;
//			 	console.log("The Data after approver name details is.......... ");
//			 	console.log($scope.approverNameDetails);
//			 	console.log("Approver Name Details");
//			 	console.log($scope.approverNameDetails);
			};
			
			
			WebServiceFactory.getApproverName($localStorage.rcId).then(getApproverName);
			
		
			// Getting the data from leader ship table
			var marker = [];
			 var getLeaderApproverName = function(response) {
			  		$scope.leaderApproverNameDetails = response.data;
//			  		console.log("Leader Approver name Details");
//			  		console.log($scope.leaderApproverNameDetails);
				};
				WebServiceFactory.getLeaderApproverName().then(getLeaderApproverName);
				
			// updating the approval level 
				/*$scope.isDisabled = false;*/
				$scope.updateApprovalStatus = function (response) {
					if($scope.pageTrackerData != ""){
						if($scope.pageTrackerData[0].noOfSubmittedPages == $scope.pageTrackerData[0].noOfPages){
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
					
					var rcId = $scope.rateCardInfo[0].rcId;
					
					var empId = $scope.approverLevels[0].empID;
					
					var currentApproval = $scope.approverLevels[0].level;
					
					if(currentApproval == "delivery_Head") {
						currentApproval = "DUH";
					}
					console.log("The current Approval indicator is....... "+ currentApproval)

					var currentApprovelLevel = $scope.rateCardInfo[0].currentApprovalLevel +1;
					
					var gftComment = angular.element(document.getElementById('txtComment'));
	                var comment = angular.element(gftComment).val();
	                if($scope.rateCardInfo[0].isManualRc == 'A' || $scope.rateCardInfo[0].isManualRc == 'H') {
	                	if($scope.rateCardInfo[0].approverComments != null) {
	                		var approverComments =$scope.rateCardInfo[0].approverComments;
	                	} else {
	                		$scope.rateCardInfo[0].approverComments="";
	                		var approverComments =$scope.rateCardInfo[0].approverComments;
	                	}
	                	$scope.rateCardInfo[0].status = 1;
						var currentApproverStatus = $scope.rateCardInfo[0].status+1;
						marker = {
								"rcId":rcId,
								"currentApprovalStatus":currentApproverStatus,
								"currentApproverId":empId,
								"currentApprovalLevel":currentApprovelLevel,
								"approverComments":approverComments,
								"statusIndicator":currentApproval,
								"rainbowApprovalFlag" : $sessionStorage.IsRainbowFlag,
         					   "approvalFlagLevel" :$scope.frmRateCardCreationFin.rainbowApprovalLevel,
								"isManualRc": $scope.rateCardInfo[0].isManualRc,
								"userType":userType
								
						};
	                } else {
	                	$scope.rateCardInfo[0].approverComments=[$scope.rateCardInfo[0].approverComments] + "\n" + "[GFT]:" +[comment];
	                	var approverComments =$scope.rateCardInfo[0].approverComments;
	                	$scope.rateCardInfo[0].status = 1;
						var currentApproverStatus = $scope.rateCardInfo[0].status+1;
						marker = {
								"rcId":rcId,
								"currentApprovalStatus":currentApproverStatus,
								"currentApproverId":empId,
								"currentApprovalLevel":currentApprovelLevel,
								"approverComments":approverComments,
								"statusIndicator":currentApproval,
								"rainbowApprovalFlag" :$sessionStorage.IsRainbowFlag,
								"approvalFlagLevel" :$scope.frmRateCardCreationFin.rainbowApprovalLevel,
								"isManualRc": $scope.rateCardInfo[0].isManualRc,
								"userType":userType
						};
	                }
					$http({
						 method: 'POST',
						 url: contextPath+"/RightPrice-DAS/updateApprovalData",
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
			    	        			window.location = "RateCardCreationFinaliseRateCard";
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
				};
					}
					else{
						BootstrapDialog.show({
		    	        	title : 'RateCard Creation - Finalise RateCard',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : 'Please save Rate card at previous pages.',
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
				
				$scope.updateRecycle = function (response) {
					$scope.isDisabled = true;
					var rcId = $scope.rateCardInfo[0].rcId;
//					console.log("rc ID is..........." +rcId);
					
					var empId = $scope.approverLevels[0].empID;
//					console.log("Emp ID .............. "+empId);
					
					var currentApprovelLevel = $scope.rateCardInfo[0].currentApprovalLevel;
					
					var gftComment = angular.element(document.getElementById('txtComment'));

	                var comment = angular.element(gftComment).val();
	                if($scope.rateCardInfo[0].isManualRc == 'A' || $scope.rateCardInfo[0].isManualRc == 'H') {
	                	$scope.rateCardInfo[0].approverComments="";
	                	var approverComments =$scope.rateCardInfo[0].approverComments;
	                	marker = {
								"rcId":rcId,
								"currentApprovalStatus":4,
								"currentApproverId":empId,
								"currentApprovalLevel":currentApprovelLevel,
								"approverComments":approverComments
						};
	                } else {
	                	$scope.rateCardInfo[0].approverComments=[$scope.rateCardInfo[0].approverComments] + "\n" + "[GFT]:" +[comment];
	                	var approverComments =$scope.rateCardInfo[0].approverComments;
	                	marker = {
								"rcId":rcId,
								"currentApprovalStatus":4,
								"currentApproverId":empId,
								"currentApprovalLevel":currentApprovelLevel,
								"approverComments":approverComments
						};
	                }
//					console.log("currentApproverLevel is................. "+ currentApprovelLevel);
					$scope.rateCardInfo[0].status = 1;
					var currentApproverStatus = $scope.rateCardInfo[0].status+1;//console.log("currentApproverStatus is...................."+ currentApproverStatus);
//					console.log("Markers are");
//					console.log(marker);
					$http({
						 method: 'POST',
						 url: contextPath+"/RightPrice-DAS/updateApprovalData",
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
			    	        	message : 'RateCard Recycled Successfully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "RateCardCreationFinaliseRateCard";
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

				$scope.getApproverNames = function (rcId,rbutype) {
					 var getApproverNames = function(response){
						$scope.approverNames = [];
						console.log(response.data);
						$scope.approverNameDetails = response.data;
						angular.forEach($scope.approverLevels, function(value, key) {
							
			  				
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
			  				
			  			/*	if(value.level == "JV CEO") {
			  					value.level = "CEO";
			  				}*/
			  				var approverLevelLength = $scope.approverLevels.length;
			  				if($scope.rateCardInfo[0] != undefined) {
			  					var currentApprovalLevel =  $scope.rateCardInfo[0].currentApprovalLevel;
			  					var currentApprovalStatus = $scope.rateCardInfo[0].currentApprovalStatus;
			  				} else {
			  					var currentApprovalLevel =  $localStorage.currentApprovalLevel;
			  				}

			  				if( $scope.rateCardInfo[0].currentStatus != "Draft") {
			  					
			  					if(currentApprovalLevel == null) { 	
			  						$scope.rateCardInfo[0].currentStatus = "Draft";
			  					}
			  					if(currentApprovalLevel == 1 && currentApprovalStatus == 2) {
			  						$scope.approverLevels[0].status = $scope.rateCardInfo[0].currentStatus;
			  						if($scope.approverLevels[0].status == "Pending Approval"){
			  							for(var i=1;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
			  							$scope.isDisabled = true;
			  						}
			  					}
			  					else if(currentApprovalLevel == 2 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=1;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}
			  					else if(currentApprovalLevel == 3 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=2;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}
			  					else if(currentApprovalLevel == 4 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=3;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}
			  					else if(currentApprovalLevel == 5 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=4;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}
			  					else if(currentApprovalLevel == 6 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=5;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}
			  					else if(currentApprovalLevel == 7 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=6;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}	else if(currentApprovalLevel == 8 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=7;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}
			  					else if(currentApprovalLevel == 9 && currentApprovalStatus == 2) {
			  						for(var i=0;i<currentApprovalLevel-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						for(var i=8;i<$scope.approverLevels.length;i++) {
		  								$scope.approverLevels[i].status = "Pending Approval";
		  							}
			  					}
			  					else if(currentApprovalLevel == approverLevelLength) {
			  						for(var i=0;i<=approverLevelLength-1;i++) {
			  							$scope.approverLevels[i].status = "Approved";
			  							$scope.isDisabled = true;
			  						}
			  						$scope.approverLevels[key].status = $scope.rateCardInfo[0].currentStatus;
			  					}
			  				}
			  				else if($scope.rateCardInfo[0].currentStatus == "Draft"){
			  					for(var i=0;i<approverLevelLength;i++) {
			  						$scope.approverLevels[i].status = "Not Submitted";
			  						$scope.isDisabled = false;
			  					}
			  				}
			  				if($scope.rateCardInfo[0].currentStatus == "Recycled") {
			  					$scope.rateCardInfo[0].currentStatus = "Draft"
			  						for(var i=0;i<approverLevelLength;i++) {
//			  					console.log("Inside the for loop");
			  							$scope.approverLevels[i].status = "";
			  						}
			  				}
			  			});
					}
					 WebServiceFactory.getApproverNames(rcId,rbutype).then(getApproverNames);
				};
				
				$scope.checkApprovalLevel = function(RainbowValue)
				{
					if(RainbowValue==1)
					{
						$scope.RainbowLevelDD=true;
						$sessionStorage.IsRainbowFlag=1;
					}
					else{
							$scope.RainbowLevelDD=false;
							$sessionStorage.IsRainbowFlag=2;
					}
				}
				// on click of the send for approval
				$scope.sendForApproval = function() {
					console.log("Approval Level"+$scope.frmRateCardCreationFin.rainbowApprovalLevel);
					BootstrapDialog.show({
				           title: 'Rate Card Creation - Finalise RateCard',
				           type : BootstrapDialog.TYPE_PRIMARY,
				           message : 'Do you want to Send For Approval?',
				           closable: true,
				           buttons: [{
				               label: 'Yes',
				               cssClass: 'btn-primary',
				               action: function(dialogRef) {
				            	   
				            	  /* if($scope.frmRateCardCreationFin.rainbowApprovalRequired!=undefined)
				            		   {
				            		   
				            		   console.log($scope.frmRateCardCreationFin.rainbowApprovalRequired);
				            		   if($scope.frmRateCardCreationFin.rainbowApprovalRequired==1)
				            			   {
				            			   	if($scope.frmRateCardCreationFin.rainbowApprovalLevel!=undefined)
				            				   {
				            			   		$scope.updateApprovalStatus();
								            	   dialogRef.close();
				            				   }
				            			   	else{
				            			   	 BootstrapDialog.show({
							       					title : 'Rate Card - Finalize Rate Card',
							       					type : BootstrapDialog.TYPE_DANGER,
							       					message : 'Please select Approval Level',
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
				            		   else{
					            			   $scope.updateApprovalStatus();
							            	   dialogRef.close();
				            		   }
					            		   
					            		  }*/
/*//				            	   else{
//				            		   BootstrapDialog.show({
//				       					title : 'Rate Card - Finalize Rate Card',
//				       					type : BootstrapDialog.TYPE_DANGER,
//				       					message : 'Please select whether you want Rainbow Approval or not.',
//				       					closable : false,
//				       					buttons : [ {
//				       						label : 'OK',
//				       						action : function(dialogRef) {
//				       							dialogRef.close();
//				       							
//				       							
//				       							
//				       						}
//				       					} ]
//				       				});
//				            	   }
*/				            	  $scope.updateApprovalStatus();
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
				
				$scope.getApproverData = function(rcId) {
					var getApproverData = function(response) {
						$scope.approverInfo = response.data;
						//$scope.approverInfo	 = $scope.rateCardInfo 
						console.log("the Finalize approval data is .................. ");
						console.log($scope.approverInfo);
						if($scope.approverInfo[0].codeApplication != null) {
					  		var level1 = $scope.approverInfo[0].codeApplication.description;
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
				  					$scope.approverLevels[key].name = $scope.approverNameDetails[0].RiskManagers;
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
				  				/*if(value.level == "JV CEO") {
				  					value.level = "CEO";
				  				}*/
				  				
				  				var approverLevelLength = $scope.approverLevels.length;
				  				if($scope.rateCardInfo[0] != undefined) {
				  					var currentApprovalLevel =  $scope.rateCardInfo[0].currentApprovalLevel;
				  					var currentApprovalStatus = $scope.rateCardInfo[0].currentApprovalStatus;
				  				} else {
				  					var currentApprovalLevel =  $localStorage.currentApprovalLevel;
				  				}

				  				if( $scope.rateCardInfo[0].currentStatus != "Draft") {
				  					
				  					if(currentApprovalLevel == null) { 	
				  						$scope.rateCardInfo[0].currentStatus = "Draft";
				  						$scope.isDisabled = false;
				  					}
				  					if(currentApprovalLevel == 1 && currentApprovalStatus == 2) {
				  						$scope.approverLevels[0].status = $scope.rateCardInfo[0].currentStatus;
				  						if($scope.approverLevels[0].status == "Pending Approval"){
				  							for(var i=1;i<$scope.approverLevels.length;i++) {
				  								$scope.approverLevels[i].status = "Pending Approval";
				  							}
				  							$scope.isDisabled = true;
				  						}
				  					}
				  					else if(currentApprovalLevel == 2 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=1;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == 3 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=2;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == 4 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=3;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == 5 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=4;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == 6 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=5;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == 7 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=6;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == 8 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=7;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == 9 && currentApprovalStatus == 2) {
				  						for(var i=0;i<currentApprovalLevel-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  						for(var i=8;i<$scope.approverLevels.length;i++) {
			  								$scope.approverLevels[i].status = "Pending Approval";
			  							}
				  					}
				  					else if(currentApprovalLevel == approverLevelLength) {
				  						for(var i=0;i<=approverLevelLength-1;i++) {
				  							$scope.approverLevels[i].status = "Approved";
				  							$scope.isDisabled = true;
				  						}
				  					}
				  					else if($scope.rateCardInfo[0].currentStatus == "Draft"){
					  					for(var i=0;i<approverLevelLength;i++) {
					  						$scope.approverLevels[i].status = "Not Submitted";
					  						$scope.isDisabled = false;
					  					}
					  				}
				  				}
				  				else if($scope.rateCardInfo[0].currentStatus == "Draft"){
				  					for(var i=0;i<approverLevelLength;i++) {
				  						$scope.approverLevels[i].status = "Not Submitted";
				  						$scope.isDisabled = false;
				  					}
				  				}
				  				if($scope.rateCardInfo[0].currentStatus == "Recycled") {
				  					$scope.rateCardInfo[0].currentStatus = "Draft"
				  						for(var i=0;i<approverLevelLength;i++) {
//				  					console.log("Inside the for loop");
				  							$scope.approverLevels[i].status = "";
				  						}
				  				}
				  		});
				  		console.log("The approver level");
				  		console.log($scope.approverLevels);
				  	  }	else {
				  		  $scope.getApproverNames($localStorage.rcId,$sessionStorage.rbutype);
				  	  }
					}
					 WebServiceFactory.getApproverData(rcId).then(getApproverData);
				}
				
				//on click of Recycled Button
				$scope.recycledClick = function() {
					BootstrapDialog.show({
				           title: 'Rate Card Creation - Finalise RateCard',
				           type : BootstrapDialog.TYPE_PRIMARY,
				           message : 'Do you want to Reject the rate card?',
				           closable: true,
				           buttons: [{
				               label: 'Yes',
				               cssClass: 'btn-primary',
				               action: function(dialogRef) {
				            	   $scope.updateRecycle();
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
				
				$scope.showMatrix = function() {
					   BootstrapDialog.show({
        				   title : 'Rate Card Creation - Finalise Rate Card',
        				   type : BootstrapDialog.TYPE_DANGER,
        				   message : $('#tblViewVerticalDiv').html(),
        				   closable : false,
        				   buttons : [{
        					   label : 'OK',
        					   action : function(dialogRef) {
        						   dialogRef.close();
        					   }
        				   }]
        			   });
				}

}]);
app.filter('headerFilter', function () {
	 return function (input) {
	      return input.replace(/,/g, ' - ');
	  };
	});