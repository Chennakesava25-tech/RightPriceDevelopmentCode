//var app = angular.module('MyDashBoardApp', ['ngStorage']);
app.controller("MyDashBoardController", ['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory,$localStorage, $sessionStorage,$index){
	$scope.isMemberExist = false;
	$scope.isLevelApproverDisabled = false;
	$scope.btnDisable = false;
	$scope.isDeleteShow = true;
	$scope.getUserRoles = []
	$scope.getUserRolesData = [];
	$scope.towerCount=[];
	$scope.rateCardData=[];
	$scope.dealData = [];
	$sessionStorage.currentUser = null;
	$scope.verticalId="";
	var userType ="";
	$scope.onloadFlag=0;
	$scope.deligUser  = 0;
	$scope.verticalArray = [];
	$scope.verticalifo = [];
	sessionStorage.setItem('dashboardData', 0 );
	var contextPath = "/RightPrice-DAS";
	var flagTogetVertDataOnce = 0
	
	$scope.sort = function(keyname)
	{
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    }
	
	$scope.currentUser = function(user)
	{
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
		var currentUser = sessionStorage.getItem("proxyArray");
		if(currentUser == null) {
			$sessionStorage.proxyUserId = null;
		}
		else{
			if($sessionStorage.proxyIdVal!=null){
				$sessionStorage.proxyUserId=$sessionStorage.proxyIdVal
			}
		}
				
				
		
		if($sessionStorage.proxyUserId == null || $sessionStorage.proxyUserId == undefined) {
			$scope.user = user;
			$sessionStorage.proxyId = user;
		} else {
			$scope.user = $sessionStorage.proxyUserId;
			user = $sessionStorage.proxyUserId;
			$sessionStorage.proxyId = user;
			//$localStorage.proxyUserId = null;
		}
		//$scope.getDelicgateUserAccess(user);
		if($sessionStorage.currentUser == null) {
			$sessionStorage.currentUser = $scope.user;
		}
		var getUserRoles = function(response)
		{
			$scope.getUserRoles  = response.data;
			console.log("Usre Data...........");
			console.log($scope.getUserRoles);
			if($scope.getUserRoles != undefined) {
				$scope.checkCurrentUserRole($scope.getUserRoles);
			}
		}
		WebServiceFactory.getUserRoles($scope.user).then(getUserRoles);
	}

	$scope.checkCurrentUserRole = function(userRoles) 
	{
		userType = userRoles[0];
		if(userRoles[0] == 'GFT' || userRoles[0] == 'CEO' || userRoles[0] == 'CDO'|| userRoles[0] == 'Audit'
			|| userRoles[0] == 'LEVELl1User' || userRoles[0] == 'LEVELl2User'||userRoles[0] == 'RiskManagers'){
	 		var getGFTCustomer = function(response) {
			   $scope.customer = response.data;
			   $scope.customerVerticalMapping = response.data;
			   if(userRoles[0] == 'GFT' || userRoles[0] == 'Audit'||userRoles[0] == 'RiskManagers'){
				   $scope.getVerticalMemberDetail($scope.user);			   
			   }
			   else if(userRoles[0] == 'CEO' || userRoles[0] == 'CDO' || userRoles[0] == 'LEVELl1User' || userRoles[0] == 'LEVELl2User')
			   {
				   $scope.getLeadershipDetails();  
			   }
			   /*else if(userRoles[0] == 'RiskManagers')
			   {
				   $scope.getRiskManagersMemberDetail($scope.user);  
			   }*/
			 };
		 WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
	 	
		}
		else  if(userRoles[0] == 'BUH' || userRoles[0] == 'DUH' )
		{
			var getVerticalApproverData = function(response) 
			{
				$scope.verticalDetails = response.data;
				console.log("----------------------------check qulity person id -------------");
				console.log($scope.user);
				if($scope.verticalDetails!=undefined);
				{
					angular.forEach($scope.verticalDetails, function(value,key) {
						 if($scope.user != null) {
							if($scope.user == value.deliveryHeadId || $scope.user == value.buHeadId ) {
								$scope.verticalArray.push({
									"verticalId" : value.verticalId
								})
							}
						} else {
							if($sessionStorage.currentUser == value.deliveryHeadId || $sessionStorage.currentUser == value.buHeadId ) {
								$scope.verticalArray.push({
									"verticalId" : value.verticalId
								})
							}
						}
						 
					});
					if($scope.verticalArray != undefined) {
						$scope.getDUHCustomerVerticalMapping($scope.verticalArray);
					}
				}
				
				
			};
			WebServiceFactory.getVerticalApproverData().then(getVerticalApproverData);
		
			$scope.getDUHCustomerVerticalMapping = function(verticalArray) {
				$scope.markers = [];
				for(var i=0;i<verticalArray.length;i++) {
					$scope.markers.push({
							 "verticalId":verticalArray[i].verticalId
					})
				}
				
				/*var marker = {
						 "verticalId":verticalArray
				}*/
			var getDUHCustomerVerticalMapping = function(response) {
				$scope.customer = response.data;
				$scope.customerVerticalMapping = response.data;
				$scope.getSummaryCountBasedOnVerticalId();
				$scope.getDealCountBasedOnVerticalId();
			}
			WebServiceFactory.getDUHCustomerVerticalMapping($scope.markers).then(getDUHCustomerVerticalMapping);
	 	}
	}
		else if(userRoles[0] == 'User' || userRoles[0] == 'Delivery')
		{
			var getCustomerForUser = function(response) 
			{
				$scope.customer = response.data;
				$scope.customerVerticalMapping = response.data;
				$scope.verticalId = $scope.customerVerticalMapping[0].verticalId;
				$scope.getVerticalMemberDetail($scope.user);
			}
				WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
		}
		
	}

	//get leadership data
	$scope.getLeadershipDetails = function(){
		var getLeadershipDetails = function(response) {
		    $scope.leadershipDetails = response.data;
		    if(response.status == 200){
		    $scope.getSummaryCountBasedOnVerticalId();
		    $scope.getDealCountBasedOnVerticalId();
		    }
		};
		WebServiceFactory.getLeadershipDetails().then(getLeadershipDetails);
	}
	
	
	$scope.getVerticalMemberData = function(user, verticalId){
		var getVerticalMemberData = function(response){
			$scope.getVerticalMemberData = response.data;
			console.log("The vertical Member data is............. ");
			console.log($scope.getVerticalMemberData)
			if(response.status == 200){
				if(verticalId == $scope.getVerticalMemberData[0].verticalId && $scope.getVerticalMemberData[0].roleType == 2){
				$scope.isMemberExist = true;
				$scope.rateCardStatus = "Draft";
				$scope.dealStatus = "Draft";
				$scope.getSummaryCountBasedOnVerticalId();
				$scope.getDealCountBasedOnVerticalId();
				$scope.currentRoleId=null;
				$scope.currentStatus=1;
				}
			}
			else{
				$scope.getSummaryCountBasedOnVerticalId();
				$scope.getDealCountBasedOnVerticalId();
			}
		}
		WebServiceFactory.getVerticalMemberData(user).then(getVerticalMemberData);
   }
	
	$scope.getVerticalMemberDetail = function(user){
		//$scope.getVerticalDetails(verticalId);
		var getVerticalMemberData = function(response){
			$scope.getVerticalMemberData = response.data;
			if(response.status == 200){
				if($scope.getVerticalMemberData[0].roleType == 2){
				$scope.isMemberExist = true;
				$scope.rateCardStatus = "Draft";
				$scope.dealStatus = "Draft";
				$scope.getSummaryCountBasedOnVerticalId();
				$scope.getDealCountBasedOnVerticalId();
				}
			}
			else 
			{
				$scope.getSummaryCountBasedOnVerticalId();
				$scope.getDealCountBasedOnVerticalId();
			}
		}
		WebServiceFactory.getVerticalMemberData(user).then(getVerticalMemberData);
   }
	
	/*$scope.getRiskManagersMemberDetail = function(user){
		var getRiskManagersMemberDetail = function(response){
			$scope.getRiskManagersMemberDetail = response.data;
			if(response.status == 200){
				if($scope.getRiskManagersMemberDetail[0].employeeLanId != undefined){
				$scope.isMemberExist = true;
//				$scope.rateCardStatus = "Draft";
//				$scope.dealStatus = "Draft";
				$scope.getSummaryCountBasedOnVerticalId();
				$scope.getDealCountBasedOnVerticalId();
				}
			}
		}
		WebServiceFactory.getRiskManagersMemberDetail(user).then(getRiskManagersMemberDetail);
   }
	*/
	$scope.getSummaryCountBasedOnVerticalId = function(){
			
			var getVerticalData = function(response) 
			{
				$scope.verticalifo = response.data;
				console.log("----------------------------Vertical info by AS5045662  -------------");
				console.log($scope.verticalifo);


				$scope.draftCount = 0;
				$scope.approvedCount  = 0;
				$scope.duhCount = 0;
				$scope.RiskManagersCount = 0;
				$scope.gftCount = 0;
				$scope.cdoCount = 0;
				$scope.buhCount = 0;
				$scope.ceoCount = 0;
				$scope.recycledCount = 0;
				$scope.deactivatedCount = 0;
				$scope.expiredCount = 0;
				$scope.manualGFT=0;
				$scope.manualBUH = 0;
				$scope.manualDUH = 0;
				$scope.manualCEO = 0;
				$scope.manualCDO = 0;
				$scope.countGFT = 0;
				$scope.countLevel1 = 0;
				$scope.countLevel2 = 0;
				$scope.customerMappingArr = [];
				if(userType == 'User'||userType == 'Delivery'){
					$scope.isDeleteShow = false;
					$scope.rateCardStatus = "Draft";
					$scope.statusIndicator = 'Draft';
					$scope.rcStatusIndicator = 'Draft';
				}
				else {
					if(userType == 'DUH'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Pending DH";
						$scope.statusIndicator = 'Delivery Head';
						$scope.rcStatusIndicator = 'Delivery Head';
					}
					else if(userType == 'GFT'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Pending GFT";	
						$scope.statusIndicator = 'GFT';			
						$scope.rcStatusIndicator = 'GFT';
					}	
					else if(userType == 'CDO'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Pending CDO";
						$scope.statusIndicator = 'CDO';
						$scope.rcStatusIndicator = 'CDO';
					}
					else if(userType == 'BUH'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Pending BUH";
						$scope.statusIndicator = 'BU Head';
						$scope.rcStatusIndicator = 'BU Head';
					}
					else if(userType == 'CEO'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Pending CEO";
						$scope.statusIndicator = 'CEO';
						$scope.rcStatusIndicator = 'CEO';
					}
					else if(userType == 'LEVELl1User'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Pending Level 1";
						$scope.statusIndicator = 'Level 1';
						$scope.rcStatusIndicator = 'Level 1';
					}
					else if(userType == 'LEVELl2User'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Pending Level 2";
						$scope.statusIndicator = 'Level 2';
						$scope.rcStatusIndicator = 'Level 2';
					}
					else if(userType == 'Audit'){
						$scope.isDeleteShow = true;
						$scope.rateCardStatus = "Approved";
						$scope.statusIndicator = 'Approved';
						$scope.rcStatusIndicator = 'Approved';
					}
					else if(userType == 'RiskManagers'){
						$scope.rateCardStatus = "Pending RiskManagers";
						$scope.statusIndicator = 'RiskManagers';
						$scope.rcStatusIndicator = 'RiskManagers';
					}
				}
				if($scope.frmDashboard.customerNameModel!=undefined){
					angular.forEach($scope.customerVerticalMapping, function (value, key) {
						if($scope.frmDashboard.customerNameModel.customerId == value.customerId){
				            $scope.customerMappingArr.push({
//				           	"customerVerticalMapId": value.customerVerticalMapId
				            	"customerId" : value.customerId,
				            	"userType":userType,
				            	"statusIndicator":$scope.statusIndicator
				     	   });
						}
			        });
				}
				else if (userType=='GFT'|| userType == 'CDO' || userType == 'CEO' || userType == 'RiskManagers' || userType == 'Audit' || userType == 'LEVELl1User' || userType == 'LEVELl2User'
					&& $scope.frmDashboard.customerNameModel == undefined){
					angular.forEach($scope.customerVerticalMapping, function (value, key) {
			            $scope.customerMappingArr.push({
//			           	"customerVerticalMapId": value.customerVerticalMapId,
			            	"userType":userType,
			            	"statusIndicator":$scope.statusIndicator
			     	   });
			        });
				}
				else if(userType=='BUH'|| userType=='DUH' && $scope.frmDashboard.customerNameModel == undefined)
				{
					angular.forEach($scope.customerVerticalMapping, function (value, key) {
			            $scope.customerMappingArr.push({
//			           	"customerVerticalMapId": value.customerVerticalMapId
			            	"customerId" : value.customerId,
			            	"userType":userType,
			            	"statusIndicator":$scope.statusIndicator
			     	   });
			        });
				}
				else
				{
					angular.forEach($scope.customerVerticalMapping, function (value, key) {
			            $scope.customerMappingArr.push({
//			           	"customerVerticalMapId": value.customerVerticalMapId
			            	"customerId" : value.customerId,
			            	"userType":userType,
			            	"statusIndicator":$scope.statusIndicator
			     	   });
			        });
				}
				
				
				var getSummaryCountBasedOnVerticalId = function(response){
					$scope.countSummary = response.data;
					console.log("RC_DATA.....................................");
					console.log($scope.countSummary);
					for(var i=0;i<$scope.countSummary[1].length;i++)
					{
						var approvalStatus=$scope.countSummary[1][i].currentApprovalStatus;
						/*if($scope.countSummary[1][i].currentApprovalStatus=='1')
						{*/
							if(userType=='GFT'&& $scope.countSummary[1][i].isManualRc=='H' && ($scope.countSummary[1][i].statusIndicator=='Draft' || $scope.countSummary[1][i].statusIndicator=='DRAFT'))
							{
								$scope.draftCount++;
							}
							else if(userType == 'BUH' || userType == 'DUH' || userType == 'CDO' || userType == 'CEO' || userType == 'LEVELl1User' || userType == 'LEVELl2User')
							{
								$scope.draftCount = 0;
							}
							else if(userType == 'Audit') {
								$scope.draftCount = 0;
							}
							else if(userType!='GFT'&& ($scope.countSummary[1][i].statusIndicator=='Draft' || $scope.countSummary[1][i].statusIndicator=='DRAFT'))
							{
								$scope.draftCount++;	
							}		
						/*}*/
												/*
												 * if($scope.countSummary[1][i].currentApprovalStatus=='3') {
												 * $scope.approvedCount++; }
												 * if($scope.countSummary[1][i].currentApprovalStatus=='4') {
												 * $scope.recycledCount++; }
												 * if($scope.countSummary[1][i].currentApprovalStatus=='5') {
												 * $scope.deactivatedCount++; }
												 * if($scope.countSummary[1][i].currentApprovalStatus=='6') {
												 * $scope.expiredCount++; }
												 */
						if($scope.countSummary[1][i].statusIndicator=='GFT')
						{
							$scope.countGFT++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Manual with GFT')
						{
							$scope.manualGFT++;
						}
						/*if($scope.countSummary[1][i].statusIndicator=='BU_Head' || $scope.countSummary[1][i].statusIndicator=='BUH'|| $scope.countSummary[1][i].statusIndicator=='BU Head')*/
						if($scope.countSummary[1][i].statusIndicator=='BU Head')
						{
							$scope.manualBUH++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Delivery Head')
						{
							$scope.manualDUH++;
						}
						if($scope.countSummary[1][i].statusIndicator=='CEO')
						{
							$scope.manualCEO++;
						}
						if($scope.countSummary[1][i].statusIndicator=='CDO')
						{
							$scope.manualCDO++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Level 1')
						{
							$scope.countLevel1++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Level 2')
						{
							$scope.countLevel2++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Approved')
						{
							$scope.approvedCount++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Recycled')
						{
							$scope.recycledCount++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Deactivated'|| $scope.countSummary[1][i].statusIndicator=='DEACTIVATED')
						{
							$scope.deactivatedCount++;
						}
						if($scope.countSummary[1][i].statusIndicator=='Expired')
						{
							$scope.expiredCount++;
						}
					}
					for(var i =0; i<$scope.countSummary[0].length; i++){
						if($scope.countSummary[0][i][0] == 4){
							$scope.duhCount = $scope.countSummary[0][i][1];
						}
						if($scope.countSummary[0][i][0] == 6){
							$scope.gftCount = $scope.countSummary[0][i][1];	
						}
						if($scope.countSummary[0][i][0] == 7){
							$scope.cdoCount = $scope.countSummary[0][i][1];
						}
						if($scope.countSummary[0][i][0] == 8){
							$scope.buhCount = $scope.countSummary[0][i][1];
						}
						if($scope.countSummary[0][i][0] == 10){
							$scope.ceoCount = $scope.countSummary[0][i][1];
						}
						
					}
					for(var i =0; i<$scope.countSummary[3].length; i++)
					{
						if($scope.countSummary[3].length !=0)
						{
							$scope.expiredCount++;
						}
							
					}
					
					if(userType=='GFT' || userType == 'BUH' || userType == 'DUH' || userType == 'CDO' 
						|| userType == 'CEO' || userType == 'Others'|| userType == 'User' ||userType == 'Delivery' 
						||userType == 'RiskManagers'||userType=='Audit'|| userType == 'LEVELl1User' || userType == 'LEVELl2User')
					{
						$scope.gftCount = $scope.countGFT + $scope.manualGFT;
						$scope.duhCount = $scope.manualDUH;
						$scope.cdoCount = $scope.manualCDO;
						$scope.buhCount = $scope.manualBUH;
						$scope.ceoCount = $scope.manualCEO;
					}
					
					
					
					var currentRoleId = $scope.currentRateCardRoleId;
					var currentStatus = $scope.currentRateCardStatus;
					
				//	alert(currentRoleId+"  "+currentStatus);
					
					if($scope.getVerticalMemberData!="" || $scope.leadershipDetails!="" || $scope.verticalDetails!=""){
						$scope.tempRateCardData = $scope.countSummary[1];
						$scope.tempRateCardDataForDelivary = $scope.countSummary[2];
						console.log("Rate Card Data::::::::::::::::::::::::::::::::::::::::::")
						console.log($scope.countSummary);
						if(userType=='CEO'||userType=='CDO' || userType == 'LEVELl1User' || userType == 'LEVELl2User')
						{
							$scope.isLevelApproverDisabled=true;
//							Will Use this code in future
							/*if(($scope.user).toLowerCase() == ($scope.leadershipDetails[0].leaderLanID).toLowerCase() 
									|| ($scope.user).toLowerCase() == ($scope.leadershipDetails[1].leaderLanID).toLowerCase())
							{*/
								



							
							
								for(var i=0;i<$scope.tempRateCardDataForDelivary.length ;i++){

									for(var k=0;k<$scope.verticalifo.length;k++){
								
										if($scope.verticalifo[k].appCode == "RP_Industry"){
										if($scope.tempDealDataForDelivary[i].customer.industryName != 'Null' || $scope.tempDealDataForDelivary[i].customer.industryName != undefined)
										{
											if($scope.verticalifo[k].description == $scope.tempDealDataForDelivary[i].customer.industryName)
											{
												$scope.tempDealDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
											break;
											}
										}else{
											
											$scope.tempDealDataForDelivary[i].IndustryName = null
												break;
										}
										}
											
									}
									
									var rcStartDate  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var date = new Date(rcStartDate.substring(0,10));
									var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDate = rcStartDD;
									
									var rcStartDateExcel  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var rcStartDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDateExcel = rcStartDDEx;
									
									var rcEndDate  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var date = new Date(rcEndDate.substring(0,10));
									var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDate = rcEndDateDD;
									
									var rcEndDateExcel  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var  rcEndDateDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDateExcel =  rcEndDateDDEx;
									
									var expectedRCEndDate  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var date = new Date(expectedRCEndDate.substring(0,10));
									var expectedRCEndDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDate = expectedRCEndDD;
									
									var expectedRCEndDateExcel  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var expectedRCEndDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDateExcel = expectedRCEndDDEx;
									
									var approvalStatus  = $scope.tempRateCardDataForDelivary[i].currentApprovalStatus;
									var statusIndicator = $scope.tempRateCardDataForDelivary[i].statusIndicator;
									
									if(approvalStatus=='2')
									{
										var pendingWith  = $scope.tempRateCardDataForDelivary[i].currentApproverId;
										if(pendingWith==null)
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
											$scope.tempRateCardDataForDelivary[i].updatedOn="-";
											
										}
										else
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId = pendingWith;
											var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var date = new Date(updatedOn.substring(0,10));
											var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
											
											var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
										}
										
										
									}
									/*else if(approvalStatus=='1' || approvalStatus=='4')*/
									else if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
									{
										$scope.isDeleteShow = false;
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var createdOn  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var date = new Date(createdOn.substring(0,10));
										var createdOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOn = createdOnDD;
										
										var createdOnExcel  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var createdOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOnExcel = createdOnEx;
									}
									else
									{
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var date = new Date(updatedOn.substring(0,10));
										var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
										
										var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
									}
									
									if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
									{
										$scope.isDeleteShow = false;
									}
									
								}
								$scope.rateCardData = $scope.tempRateCardDataForDelivary;
								
							}
						/*}*/
						else if(userType=='GFT')
						{
//							Will Use this code in future
							/*if($scope.getVerticalMemberData[0].employeeLanId != undefined)
							{
								if(($scope.user).toLowerCase() == ($scope.getVerticalMemberData[0].employeeLanId).toLowerCase() 
										&& $scope.getVerticalMemberData[0].roleType == 2){*/
									
									for(var i=0;i<$scope.tempRateCardDataForDelivary.length ;i++){
									
										for(var k=0;k<$scope.verticalifo.length;k++){
											
											if($scope.verticalifo[k].appCode == "RP_Industry"){
												if($scope.tempRateCardDataForDelivary[i].customer.industryName != 'Null' || $scope.tempRateCardDataForDelivary[i].customer.industryName != undefined)
												{
													if($scope.verticalifo[k].description == $scope.tempRateCardDataForDelivary[i].customer.industryName)
													{
														$scope.tempRateCardDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
													break;
													}
												}else{
													
													$scope.tempRateCardDataForDelivary[i].IndustryName = null
														break;
												}
												}
											
										}
										
										
										var rcStartDate  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
										var date = new Date(rcStartDate.substring(0,10));
										var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].rcStartDate = rcStartDD;
										
										var rcStartDateExcel  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
										var rcStartDDEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].rcStartDateExcel = rcStartDDEx;
										
										var rcEndDate  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
										var date = new Date(rcEndDate.substring(0,10));
										var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].rcEndDate = rcEndDateDD;
										
										var rcEndDateExcel  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
										var  rcEndDateDDEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].rcEndDateExcel =  rcEndDateDDEx;
										
										var expectedRCEndDate  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
										var date = new Date(expectedRCEndDate.substring(0,10));
										var expectedRCEndDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].expectedRCEndDate = expectedRCEndDD;
										
										var expectedRCEndDateExcel  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
										var expectedRCEndDDEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].expectedRCEndDateExcel = expectedRCEndDDEx;
										
										var approvalStatus  = $scope.tempRateCardDataForDelivary[i].currentApprovalStatus;
										var statusIndicator = $scope.tempRateCardDataForDelivary[i].statusIndicator;
										
										if(approvalStatus=='2')
										{
											var pendingWith  = $scope.tempRateCardDataForDelivary[i].currentApproverId;
											if(pendingWith==null)
											{
												$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
												$scope.tempRateCardDataForDelivary[i].updatedOn="-";
												
											}
											else
											{
												$scope.tempRateCardDataForDelivary[i].currentApproverId = pendingWith;
												var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
												var date = new Date(updatedOn.substring(0,10));
												var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
												$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
												
												var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
												var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
												$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
											}
											
											
										}
										else if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
											||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
											/*if(approvalStatus=='1' || approvalStatus=='4')*/
										{
											$scope.isDeleteShow = false;
											$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
											var createdOn  = $scope.tempRateCardDataForDelivary[i].createdOn;
											var date = new Date(createdOn.substring(0,10));
											var createdOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempRateCardDataForDelivary[i].createdOn = createdOnDD;
											
											var createdOnExcel  = $scope.tempRateCardDataForDelivary[i].createdOn;
											var createdOnEx = $filter('date')(date,'MM/dd/yyyy');
											$scope.tempRateCardDataForDelivary[i].createdOnExcel = createdOnEx;
										}
										else
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
											var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var date = new Date(updatedOn.substring(0,10));
											var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
											
											var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
										}
										
										if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
											||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
										{
											$scope.isDeleteShow = true;
										}
									}
									$scope.rateCardData = $scope.tempRateCardDataForDelivary;
									
//									Will Use this code in future
							/*	}
							}*/
							/*else{
								BootstrapDialog.show({
						        	title : 'My Dashboard - RateCard',
						        	type : BootstrapDialog.TYPE_DANGER,
						        	message : 'Not valid user.',
						        	closable : false,
						        	buttons : [{
						        		label : 'OK',
						        		action : function(dialogRef) {
						        			dialogRef.close();
						        			window.location="/RightPrice-DAS";
						        		}
						        	}]
						        });
							}*/
						}
						if(userType=='BUH'||userType=='DUH')
						{
							$scope.isLevelApproverDisabled=true;
//							Will Use this code in future
							/*if(($scope.user).toLowerCase() == ($scope.verticalDetails[0].buHeadId).toLowerCase()|| ($scope.user).toLowerCase() == ($scope.verticalDetails[0].deliveryHeadId).toLowerCase()
									||($scope.user).toLowerCase() == ($scope.verticalDetails[0].RiskManagersPersonId).toLowerCase())
							{*/
								
								for(var i=0;i<$scope.tempRateCardDataForDelivary.length ;i++){
									
									for(var k=0;k<$scope.verticalifo.length;k++){
										if($scope.verticalifo[k].appCode == "RP_Industry"){
											if($scope.tempRateCardDataForDelivary[i].customer.industryName != 'Null' || $scope.tempRateCardDataForDelivary[i].customer.industryName != undefined)
											{
												if($scope.verticalifo[k].description == $scope.tempRateCardDataForDelivary[i].customer.industryName)
												{
													$scope.tempRateCardDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
												break;
												}
											}else{
												
												$scope.tempRateCardDataForDelivary[i].IndustryName = null
													break;
											}
											}
									}
									
									
									var rcStartDate  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var date = new Date(rcStartDate.substring(0,10));
									var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDate = rcStartDD;
									
									var rcStartDateExcel  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var rcStartDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDateExcel = rcStartDDEx;
									
									var rcEndDate  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var date = new Date(rcEndDate.substring(0,10));
									var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDate = rcEndDateDD;
									
									var rcEndDateExcel  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var  rcEndDateDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDateExcel =  rcEndDateDDEx;
									
									var expectedRCEndDate  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var date = new Date(expectedRCEndDate.substring(0,10));
									var expectedRCEndDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDate = expectedRCEndDD;
									
									var expectedRCEndDateExcel  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var expectedRCEndDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDateExcel = expectedRCEndDDEx;
									
									var approvalStatus  = $scope.tempRateCardDataForDelivary[i].currentApprovalStatus;
									var statusIndicator = $scope.tempRateCardDataForDelivary[i].statusIndicator;
									
									if(approvalStatus=='2')
									{
										var pendingWith  = $scope.tempRateCardDataForDelivary[i].currentApproverId;
										if(pendingWith==null)
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
											$scope.tempRateCardDataForDelivary[i].updatedOn="-";
											
										}
										else
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId = pendingWith;
											var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var date = new Date(updatedOn.substring(0,10));
											var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
											
											var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
										}
										
										
									}
									else if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED") 
										
										/*if(approvalStatus=='1' || approvalStatus=='4')*/
									{
										$scope.isDeleteShow = false;
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var createdOn  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var date = new Date(createdOn.substring(0,10));
										var createdOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOn = createdOnDD;
										
										var createdOnExcel  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var createdOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOnExcel = createdOnEx;
									}
									else
									{
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var date = new Date(updatedOn.substring(0,10));
										var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
										
										var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
									}
									
									if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
									{
										$scope.isDeleteShow = false;
									}
									
								}
								$scope.rateCardData = $scope.tempRateCardDataForDelivary;
								
							/*}*/
						}
						else if(userType =='User'||userType == 'Delivery')
						{
							/*if( $scope.getVerticalMemberData[0].roleType == 2 && currentRoleId == undefined && currentStatus == undefined)*/
							if($scope.getVerticalMemberData[0].roleType == 2)
							{
								for(var i=0;i<$scope.tempRateCardDataForDelivary.length ;i++){
									
									for(var k=0;k<$scope.verticalifo.length;k++){
										if($scope.verticalifo[k].appCode == "RP_Industry"){
											if($scope.tempRateCardDataForDelivary[i].customer.industryName != 'Null' || $scope.tempRateCardDataForDelivary[i].customer.industryName != undefined)
											{
												if($scope.verticalifo[k].description == $scope.tempRateCardDataForDelivary[i].customer.industryName)
												{
													$scope.tempRateCardDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
												break;
												}
											}else{
												
												$scope.tempRateCardDataForDelivary[i].IndustryName = null
													break;
											}
											}
									}
									
									
									var rcStartDate  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var date = new Date(rcStartDate.substring(0,10));
									var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDate = rcStartDD;
									
									var rcStartDateExcel  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var rcStartDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDateExcel = rcStartDDEx;
									
									var rcEndDate  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var date = new Date(rcEndDate.substring(0,10));
									var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDate = rcEndDateDD;
									
									var rcEndDateExcel  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var  rcEndDateDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDateExcel =  rcEndDateDDEx;
									
									var expectedRCEndDate  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var date = new Date(expectedRCEndDate.substring(0,10));
									var expectedRCEndDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDate = expectedRCEndDD;
									
									var expectedRCEndDateExcel  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var expectedRCEndDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDateExcel = expectedRCEndDDEx;
									
									var approvalStatus  = $scope.tempRateCardDataForDelivary[i].currentApprovalStatus;
									var statusIndicator = $scope.tempRateCardDataForDelivary[i].statusIndicator;
									
									if(approvalStatus=='2')
									{
										var pendingWith  = $scope.tempRateCardDataForDelivary[i].currentApproverId;
										if(pendingWith==null)
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
											$scope.tempRateCardDataForDelivary[i].updatedOn="-";
											
										}
										else
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId = pendingWith;
											var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var date = new Date(updatedOn.substring(0,10));
											var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
											
											var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
										}
										
										
									}
									else if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
										/*if(approvalStatus=='1' || approvalStatus=='4')*/
									{
										$scope.isDeleteShow = false;
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var createdOn  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var date = new Date(createdOn.substring(0,10));
										var createdOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOn = createdOnDD;
										
										var createdOnExcel  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var createdOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOnExcel = createdOnEx;
									}
									else
									{
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var date = new Date(updatedOn.substring(0,10));
										var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
										
										var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
									}
									
									if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
									{
										$scope.isDeleteShow = false;
									}
									
								}
								$scope.rateCardData = $scope.tempRateCardDataForDelivary;
							}
						} 	else if(userType =='Audit')
						{
								for(var i=0;i<$scope.tempRateCardDataForDelivary.length ;i++){
									
									for(var k=0;k<$scope.verticalifo.length;k++){
										if($scope.verticalifo[k].appCode == "RP_Industry"){
											if($scope.tempRateCardDataForDelivary[i].customer.industryName != 'Null' || $scope.tempRateCardDataForDelivary[i].customer.industryName != undefined)
											{
												if($scope.verticalifo[k].description == $scope.tempRateCardDataForDelivary[i].customer.industryName)
												{
													$scope.tempRateCardDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
												break;
												}
											}else{
												
												$scope.tempRateCardDataForDelivary[i].IndustryName = null
													break;
											}
											}
									}
									
									var rcStartDate  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var date = new Date(rcStartDate.substring(0,10));
									var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDate = rcStartDD;
									
									var rcStartDateExcel  = $scope.tempRateCardDataForDelivary[i].rcStartDate;
									var rcStartDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcStartDateExcel = rcStartDDEx;
									
									var rcEndDate  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var date = new Date(rcEndDate.substring(0,10));
									var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDate = rcEndDateDD;
									
									var rcEndDateExcel  = $scope.tempRateCardDataForDelivary[i].rcEndDate;
									var  rcEndDateDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].rcEndDateExcel =  rcEndDateDDEx;
									
									var expectedRCEndDate  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var date = new Date(expectedRCEndDate.substring(0,10));
									var expectedRCEndDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDate = expectedRCEndDD;
									
									var expectedRCEndDateExcel  = $scope.tempRateCardDataForDelivary[i].expectedRCEndDate;
									var expectedRCEndDDEx = $filter('date')(date,'MM/dd/yyyy');
									$scope.tempRateCardDataForDelivary[i].expectedRCEndDateExcel = expectedRCEndDDEx;
									
									var approvalStatus  = $scope.tempRateCardDataForDelivary[i].currentApprovalStatus;
									var statusIndicator = $scope.tempRateCardDataForDelivary[i].statusIndicator;
									
									if(approvalStatus=='2')
									{
										var pendingWith  = $scope.tempRateCardDataForDelivary[i].currentApproverId;
										if(pendingWith==null)
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
											$scope.tempRateCardDataForDelivary[i].updatedOn="-";
											
										}
										else
										{
											$scope.tempRateCardDataForDelivary[i].currentApproverId = pendingWith;
											var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var date = new Date(updatedOn.substring(0,10));
											var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
											
											var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
											var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
											$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
										}
										
										
									}
									else if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED") 
										
										/*if(approvalStatus=='1' || approvalStatus=='4')*/
									{
										$scope.isDeleteShow = true;
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var createdOn  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var date = new Date(createdOn.substring(0,10));
										var createdOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOn = createdOnDD;
										
										var createdOnExcel  = $scope.tempRateCardDataForDelivary[i].createdOn;
										var createdOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].createdOnExcel = createdOnEx;
									}
									else
									{
										$scope.tempRateCardDataForDelivary[i].currentApproverId="-";
										var updatedOn  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var date = new Date(updatedOn.substring(0,10));
										var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOn = updatedOnDD;
										
										var updatedOnExcel  = $scope.tempRateCardDataForDelivary[i].updatedOn;
										var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
										$scope.tempRateCardDataForDelivary[i].updatedOnExcel = updatedOnEx;
									}
									
									if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
										||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
									{
										$scope.isDeleteShow = true;
									}
									
								}
								$scope.rateCardData = $scope.tempRateCardDataForDelivary;
						}
					}
				}
				
				WebServiceFactory.getSummaryCountBasedOnVerticalId($scope.customerMappingArr).then(getSummaryCountBasedOnVerticalId);
			};
			WebServiceFactory.getAppCodeData().then(getVerticalData);
			
		
	}

	$scope.getRateCardData = function(roleId,rateCardStatus){
		$scope.onloadFlag=1;
		$scope.rateCardData=[];
		$scope.searchRateCard = "";
		$scope.customerMappingForRateCard = null;
		$scope.rateCardData = null;
		$scope.customerMappingForRateCard = [];
		if(rateCardStatus == 1){
			$scope.isDeleteShow = false;
			$scope.currentRateCardStatus=1;
			$scope.rateCardStatus = "Draft";
			$scope.currentRateCardRoleId=null;
			$scope.statusIndicator = 'Draft';
			$scope.rcStatusIndicator = 'Draft';
		}
		else if(rateCardStatus == 2){
//			$scope.isDeleteShow = true;
			$scope.currentRateCardStatus=2;
			if(roleId == 4){
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Pending DH";
				$scope.currentRateCardRoleId=4;
				$scope.statusIndicator = 'Delivery Head';
				$scope.rcStatusIndicator = 'Delivery Head';
			}
			else if(roleId == 6){
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Pending GFT";	
				$scope.currentRateCardRoleId=6;
				$scope.statusIndicator = 'GFT';	
				$scope.rcStatusIndicator = 'GFT';
			}	
			else if(roleId == 7){
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Pending CDO";
				$scope.currentRateCardRoleId=7;
				$scope.statusIndicator = 'CDO';
				$scope.rcStatusIndicator = 'CDO';
			}
			else if(roleId == 8){
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Pending BUH";
				$scope.currentRateCardRoleId=8;
				$scope.statusIndicator = 'BU Head';
				$scope.rcStatusIndicator = 'BU Head';
			}
			else if(roleId == 10){
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Pending CEO";
				$scope.currentRateCardRoleId=10;
				$scope.statusIndicator = 'CEO';
				$scope.rcStatusIndicator = 'CEO';
			}
			else if(roleId == 11){
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Pending Level 1";
				$scope.currentRateCardRoleId=11;
				$scope.statusIndicator = 'Level 1';
				$scope.rcStatusIndicator = 'Level 1';
			}
			else if(roleId == 12){
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Pending Level 2";
				$scope.currentRateCardRoleId=12;
				$scope.statusIndicator = 'Level 2';
				$scope.rcStatusIndicator = 'Level 2';
			}
		}
		else if(rateCardStatus == 3){
			$scope.isDeleteShow = true;
			$scope.rateCardStatus = "Approved";
			$scope.currentRateCardStatus=3;
			$scope.currentRateCardRoleId=null;
			$scope.statusIndicator = "Approved";
			$scope.rcStatusIndicator = 'Approved';
		}
		else if(rateCardStatus == 4){
			$scope.isDeleteShow = false;
			$scope.rateCardStatus = "Recycled";
			$scope.currentRateCardStatus=4;
			$scope.currentRateCardRoleId=null;
			$scope.statusIndicator = "Recycled";
			$scope.rcStatusIndicator = 'Recycled';
			if(userType == 'Audit') {
				$scope.isDeleteShow = true;
				$scope.rateCardStatus = "Recycled";
				$scope.currentRateCardStatus=4;
				$scope.currentRateCardRoleId=null;
				$scope.statusIndicator = "Recycled";
				$scope.rcStatusIndicator = 'Recycled';
			}
		}
		else if(rateCardStatus == 5){
			$scope.isDeleteShow = true;
			$scope.rateCardStatus = "Deactivated";
			$scope.currentRateCardStatus=5;
			$scope.currentRateCardRoleId=null;
			$scope.statusIndicator = "Deactivated";
			$scope.rcStatusIndicator = 'Deactivated';
		}
		else if(rateCardStatus == 6){
			$scope.isDeleteShow = true;
			$scope.rateCardStatus = "Expired";
			$scope.currentRateCardStatus=6;
			$scope.currentRateCardRoleId=null;
			$scope.statusIndicator = "Expired";
			$scope.rcStatusIndicator = 'Expired';
		}
		
		if($scope.selectedCustomer != undefined){
			angular.forEach($scope.customerVerticalMapping, function (value, key) {
				if($scope.selectedCustomer == value.customerId){
		            $scope.customerMappingForRateCard.push({
		            "customerId" : value.customerId,
		           	"currentApproverRoleId":roleId,
		           	"currentApprovalStatus":rateCardStatus,
		           	"userType":userType,
		           	"statusIndicator":$scope.statusIndicator
		     	   });
				}
	        });
		}
		else
			{
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
		            $scope.customerMappingForRateCard.push({
		            "customerId" : value.customerId,
		           	"currentApproverRoleId":roleId,
		           	"currentApprovalStatus":rateCardStatus,
		           	"userType":userType,
		        	"statusIndicator":$scope.statusIndicator
		     	   });
		        });
			}
		
		var getRateCardData = function(response){
			$scope.rateCardData = null;
			if(response.status == 200){
				$scope.tempRateCardData = response.data;
				for(var i=0;i<$scope.tempRateCardData.length ;i++){
					var rcStartDate  = $scope.tempRateCardData[i].rcStartDate;
			  		var date = new Date(rcStartDate.substring(0,10));
			  		var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.tempRateCardData[i].rcStartDate = rcStartDD;
			  		
			  		var rcStartDateExcel  = $scope.tempRateCardData[i].rcStartDate;
			  		var rcStartDDEx = $filter('date')(date,'MM/dd/yyyy');
			  		$scope.tempRateCardData[i].rcStartDateExcel = rcStartDDEx;
			  		
			  		var rcEndDate  = $scope.tempRateCardData[i].rcEndDate;
			  		var date = new Date(rcEndDate.substring(0,10));
			  		var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.tempRateCardData[i].rcEndDate = rcEndDateDD;
			  		
			  		var rcEndDateExcel  = $scope.tempRateCardData[i].rcEndDate;
			  		var  rcEndDateDDEx = $filter('date')(date,'MM/dd/yyyy');
			  		$scope.tempRateCardData[i].rcEndDateExcel =  rcEndDateDDEx;
			  		
			  		var expectedRCEndDate  = $scope.tempRateCardData[i].expectedRCEndDate;
			  		var date = new Date(expectedRCEndDate.substring(0,10));
			  		var expectedRCEndDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.tempRateCardData[i].expectedRCEndDate = expectedRCEndDD;
			  		
			  		var expectedRCEndDateExcel  = $scope.tempRateCardData[i].expectedRCEndDate;
			  		var expectedRCEndDDEx = $filter('date')(date,'MM/dd/yyyy');
			  		$scope.tempRateCardData[i].expectedRCEndDateExcel = expectedRCEndDDEx;
			  		
			  		var approvalStatus  = $scope.tempRateCardData[i].currentApprovalStatus;
			  		var statusIndicator = $scope.tempRateCardData[i].statusIndicator;
			  		
			  		if(approvalStatus=='2' ||  approvalStatus=='3')
			  		{
			  			var pendingWith  = $scope.tempRateCardData[i].currentApproverId;
			  			if(pendingWith==null)
			  			{
			  				$scope.tempRateCardData[i].currentApproverId="-";
				  			$scope.tempRateCardData[i].updatedOn="-";
			  				
			  			}
			  			else
			  			{
			  				$scope.tempRateCardData[i].currentApproverId = pendingWith;
				  			var updatedOn  = $scope.tempRateCardData[i].updatedOn;
					  		var date = new Date(updatedOn.substring(0,10));
					  		var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
					  		$scope.tempRateCardData[i].updatedOn = updatedOnDD;
					  		
					  		var updatedOnExcel  = $scope.tempRateCardData[i].updatedOn;
					  		var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
					  		$scope.tempRateCardData[i].updatedOnExcel = updatedOnEx;
			  			}
			  			
				  		
			  		}
			  		else if(approvalStatus=='1' || approvalStatus=='4')
			  		{
			  			$scope.isDeleteShow = false;
			  			$scope.tempRateCardData[i].currentApproverId="-";
			  			var createdOn  = $scope.tempRateCardData[i].createdOn;
				  		var date = new Date(createdOn.substring(0,10));
				  		var createdOnDD = $filter('date')(date,'dd/MM/yyyy');
				  		$scope.tempRateCardData[i].createdOn = createdOnDD;
				  		
				  		var createdOnExcel  = $scope.tempRateCardData[i].createdOn;
				  		var createdOnEx = $filter('date')(date,'MM/dd/yyyy');
				  		$scope.tempRateCardData[i].createdOnExcel = createdOnEx;
			  		}
			  		else
			  		{
			  			$scope.tempRateCardData[i].currentApproverId="-";
			  			var updatedOn  = $scope.tempRateCardData[i].updatedOn;
				  		var date = new Date(updatedOn.substring(0,10));
				  		var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
				  		$scope.tempRateCardData[i].updatedOn = updatedOnDD;
				  		
				  		var updatedOnExcel  = $scope.tempRateCardData[i].updatedOn;
				  		var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
				  		$scope.tempRateCardData[i].updatedOnExcel = updatedOnEx;
			  		}if(userType =='GFT'&&(statusIndicator == "Draft" || statusIndicator == "DRAFT" ||statusIndicator == "Recycled" 
			  			|| statusIndicator == "RECYCLED")){
			  			$scope.isDeleteShow = true;
			  		}else if(statusIndicator == "Draft" || statusIndicator == "DRAFT" 
			  			||statusIndicator == "Recycled" || statusIndicator == "RECYCLED")
			  		{
			  			$scope.isDeleteShow = false;
			  		}
				}
				$scope.rateCardData = $scope.tempRateCardData;
			}
		}
		WebServiceFactory.getRateCardData($scope.customerMappingForRateCard).then(getRateCardData);
	}
	
	
	
	$scope.getCustomerBasedRecords = function(customerId){
//		$scope.rateCardData = [];
//		$scope.dealData = [];
		$scope.selectedCustomer =customerId;
		var currentRoleId = $scope.currentRateCardRoleId;
		var currentStatus = $scope.currentRateCardStatus;
		
		$scope.getSummaryCountBasedOnVerticalId();
		$scope.getDealCountBasedOnVerticalId();
	}
	
	$scope.showRateCardDetails = function(rcId, approverId, approvalStatus, approverRoleId){
		if(approverId=="N/A")
		{
			approverId="";
		}
		$localStorage.rcId = rcId;
		$localStorage.rateCardApproverId = approverId;
		$localStorage.rateCardApprovalStatus = approvalStatus;
		$localStorage.rateCardApproverRoleId = approverRoleId;
		
		var browser = window.navigator.appVersion;

        //Workaround to enable the users to download the report in IE.
        if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
               (browser.indexOf('MSIE 10') !== -1)) 
        {
	        	if(userType == 'Audit' && approvalStatus == 3) {
//	        		 window.open('RightPrice/RateCardCreationDetails', '$localStorage.rcId');
	        		window.open('RateCardCreationDetails', '$localStorage.rcId');
	        	}
	              else if(approvalStatus == 2 || approvalStatus == 3){
//                     window.open('RightPrice/RateCardCreationSummary','$localStorage.rcId');
                     window.open('RateCardCreationSummary','$localStorage.rcId');
                     
               } 
               else{
//                     window.open('RightPrice/RateCardCreationDetails', '$localStorage.rcId');
            	   window.open('RateCardCreationDetails', '$localStorage.rcId');
               }
        } 
        else 
        {
        	if(userType == 'Audit' && approvalStatus == 3) {
       		 window.open('RateCardCreationDetails', '$localStorage.rcId');
//       		 window.open('RightPrice/RateCardCreationDetails', '$localStorage.rcId');
       	}
        	else 	 if(approvalStatus == 2 || approvalStatus == 3)
               {
                     window.open('RateCardCreationSummary', '_blank', '$localStorage.rcId');
               }
               else{
                     window.open('RateCardCreationDetails', '_blank','$localStorage.rcId');
               }
        }

	}	
	
	$scope.getDataOnSearch = function(rateCardID)
	{
		$scope.rateCardData = [];
		$scope.rateCardIDOnSearch=rateCardID;
		var getDataOnSearch = function(response){
		$scope.rateCardData = null;
			if(response.status == 200){
				$scope.tempRateCardData = response.data;
				
				if($scope.tempRateCardData[0].statusIndicator == 'Draft'||$scope.tempRateCardData[0].statusIndicator == 'DRAFT')
				{
					$scope.rateCardStatus = "Draft";
				}
				else if($scope.tempRateCardData[0].statusIndicator == 'GFT')
				{
					$scope.rateCardStatus = "Pending GFT";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'CEO')
				{
					$scope.rateCardStatus = "Pending CEO";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'Level 1')
				{
					$scope.rateCardStatus = "Pending Level 1";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'Level 2')
				{
					$scope.rateCardStatus = "Pending Level 2";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'CDO')
				{
					$scope.rateCardStatus = "Pending CDO";
				}
				
				/*else if($scope.tempRateCardData[0].statusIndicator == 'BUH' ||$scope.tempRateCardData[0].statusIndicator == 'BU_Head' ||$scope.tempRateCardData[0].statusIndicator == 'BU Head')*/
				else if($scope.tempRateCardData[0].statusIndicator == 'BU Head')
				{
					$scope.rateCardStatus = "Pending BUH";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'Delivery Head')
				{
					$scope.rateCardStatus = "Pending DH";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'RiskManagers')
				{
					$scope.rateCardStatus = "Pending RiskManagers";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'Approved'||$scope.tempRateCardData[0].statusIndicator == 'APPROVED')
				{
					$scope.rateCardStatus = "Approved";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'Deactivated'||$scope.tempRateCardData[0].statusIndicator == 'DEACTIVATED')
				{
					$scope.rateCardStatus = "Deactivated";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'Recycled' || $scope.tempRateCardData[0].statusIndicator == 'RECYCLED')
				{
					$scope.rateCardStatus = "Recycled";
				}
				
				else if($scope.tempRateCardData[0].statusIndicator == 'Expired' || $scope.tempRateCardData[0].statusIndicator == 'EXPIRED')
				{
					$scope.rateCardStatus = "Expired";
				}
				else if($scope.tempRateCardData[0].statusIndicator == 'Pending Approval')
				{
					$scope.rateCardStatus = "Pending Approval";
				}
				var rcStartDate  = $scope.tempRateCardData[0].rcStartDate;
				var date = new Date(rcStartDate.substring(0,10));
				var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
				$scope.tempRateCardData[0].rcStartDate = rcStartDD;
				
				var rcStartDateExcel  = $scope.tempRateCardData[0].rcStartDate;
				var rcStartDDEx = $filter('date')(date,'MM/dd/yyyy');
				$scope.tempRateCardData[0].rcStartDateExcel = rcStartDDEx;
				
				var rcEndDate  = $scope.tempRateCardData[0].rcEndDate;
				var date = new Date(rcEndDate.substring(0,10));
				var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
				$scope.tempRateCardData[0].rcEndDate = rcEndDateDD;
				
				var rcEndDateExcel  = $scope.tempRateCardData[0].rcEndDate;
				var  rcEndDateDDEx = $filter('date')(date,'MM/dd/yyyy');
				$scope.tempRateCardData[0].rcEndDateExcel =  rcEndDateDDEx;
				
				var expectedRCEndDate  = $scope.tempRateCardData[0].expectedRCEndDate;
				var date = new Date(expectedRCEndDate.substring(0,10));
				var expectedRCEndDD = $filter('date')(date,'dd/MM/yyyy');
				$scope.tempRateCardData[0].expectedRCEndDate = expectedRCEndDD;
				
				var expectedRCEndDateExcel  = $scope.tempRateCardData[0].expectedRCEndDate;
				var expectedRCEndDDEx = $filter('date')(date,'MM/dd/yyyy');
				$scope.tempRateCardData[0].expectedRCEndDateExcel = expectedRCEndDDEx;
				
				var approvalStatus  = $scope.tempRateCardData[0].currentApprovalStatus;
				if(approvalStatus=='2')
				{
					var pendingWith  = $scope.tempRateCardData[0].currentApproverId;
					if(pendingWith==null)
					{
						$scope.tempRateCardData[0].currentApproverId="-";
						$scope.tempRateCardData[0].updatedOn="-";
						
					}
					else
					{
						$scope.tempRateCardData[0].currentApproverId = pendingWith;
						var updatedOn  = $scope.tempRateCardData[0].updatedOn;
						var date = new Date(updatedOn.substring(0,10));
						var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.tempRateCardData[0].updatedOn = updatedOnDD;
						
						var updatedOnExcel  = $scope.tempRateCardData[0].updatedOn;
				  		var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
				  		$scope.tempRateCardData[0].updatedOnExcel = updatedOnEx;
					}
					
					
				}
				else if(approvalStatus=='1' || approvalStatus=='4')
				{
					$scope.tempRateCardData[0].currentApproverId="-";
					var createdOn  = $scope.tempRateCardData[0].createdOn;
					var date = new Date(createdOn.substring(0,10));
					var createdOnDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempRateCardData[0].createdOn = createdOnDD;
					
					var createdOnExcel  = $scope.tempRateCardData[0].createdOn;
			  		var createdOnEx = $filter('date')(date,'MM/dd/yyyy');
			  		$scope.tempRateCardData[0].createdOnExcel = createdOnEx;
				}
				else
				{
					$scope.tempRateCardData[0].currentApproverId="-";
					var updatedOn  = $scope.tempRateCardData[0].updatedOn;
					var date = new Date(updatedOn.substring(0,10));
					var updatedOnDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempRateCardData[0].updatedOn = updatedOnDD;
					
					var updatedOnExcel  = $scope.tempRateCardData[0].updatedOn;
			  		var updatedOnEx = $filter('date')(date,'MM/dd/yyyy');
			  		$scope.tempRateCardData[0].updatedOnExcel = updatedOnEx;
				}
			}else{
				BootstrapDialog.show({
					title : 'My Dashboard',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Please Enter valid Rate Card ID.',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							window.location="MyDashBoard";
						}
					}]
				});
			}

			$scope.rateCardData = $scope.tempRateCardData;
			}	
	WebServiceFactory.getDataOnSearch(rateCardID).then(getDataOnSearch);
	}
	 
	$scope.deleteRateCard = function (dltRcId)
	{
		BootstrapDialog.show(
		{
			title : 'My Dashboard',
			type : BootstrapDialog.TYPE_PRIMARY,
			message : 'Do you want to Delete Rate Card.',
			closable : false,
			buttons : [
			{
				label : 'Yes',
				cssClass : 'btn-primary',
				action : function(dialogRef) 
				{
					dialogRef.close();
					BootstrapDialog.show(
					{
						title : 'My Dashboard',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'Rate card Deleted successfully.',
						closable : false,
						buttons : [
						{
							label : 'OK',
							action : function(dialogRef) 
							{
								dialogRef.close();
								window.location="MyDashBoard";
							}
						} ]
					});
					WebServiceFactory.deleteRateCard(dltRcId).then(deleteRateCard);
				}
			},
			{
				label : 'No',
				cssClass : 'btn-primary',
				action : function(dialogItself) 
				{
					dialogItself.close();
				}
			}]
		});
	}
	
	$scope.getDealCountBasedOnVerticalId = function(){
	
		var getVerticalData = function(response) 
		{
			$scope.verticalifo = response.data;
			console.log("----------------------------Vertical info by AS5045662  -------------");
			console.log($scope.verticalifo);
		

			$scope.dealDraftCount = 0;
			$scope.dealApprovedCount  = 0;
			$scope.dealDuhCount = 0;
			$scope.dealRiskManagersCount = 0;
			$scope.dealGftCount = 0;
			$scope.dealCdoCount = 0;
			$scope.dealBuhCount = 0;
			$scope.dealCeoCount = 0;
			$scope.dealLevel1Count = 0;
			$scope.dealLevel2Count = 0;
			$scope.dealRejectedCount = 0;
			$scope.dealExpiredCount = 0;
			$scope.dealDeactivatedCount = 0;
			$scope.dealRecycledCount=0;
			$scope.dealManualGFT=0;
			$scope.dealCountGFT = 0;
			$scope.dealWonCount = 0;
			$scope.dealLostCount = 0;
			$scope.customerMappingInDeal = [];
			if(userType == 'User'||userType == 'Delivery'){
				$scope.isDealDeleteShow  = false;
				$scope.currentDealStatus = 1;
				$scope.dealStatus = "Draft";
				$scope.statusIndicator = "Draft";
				$scope.dealStatusIndicator = 'Draft';
			}
			else if(userType == 'Audit'){
				$scope.isDealDeleteShow = true;
				$scope.currentDealStatus = 3;
				$scope.dealStatus = "Approved";
				$scope.statusIndicator = "Approved";	
				$scope.dealStatusIndicator = 'Approved';
			}
			else {
				$scope.isDealDeleteShow = true;
				$scope.currentDealStatus = 2;
				if(userType == 'DUH'){
					$scope.dealStatus = "Pending DH";
					$scope.statusIndicator = 'Delivery Head';
					$scope.dealStatusIndicator = 'Delivery Head';
				}
				else if(userType == 'GFT'){
					$scope.dealStatus = "Pending GFT";	
					$scope.statusIndicator = "GFT";	
					$scope.dealStatusIndicator = 'GFT';
				}	
				else if(userType == 'CDO'){
					$scope.dealStatus = "Pending CDO";
					$scope.statusIndicator = 'CDO';
					$scope.dealStatusIndicator = 'CDO';
				}
				else if(userType == 'BUH'){
					$scope.dealStatus = "Pending BUH";
					$scope.statusIndicator = 'BU Head';
					$scope.dealStatusIndicator = 'BU Head';
				}
				else if(userType == 'CEO'){
					$scope.dealStatus = "Pending CEO";
					$scope.statusIndicator = 'CEO';
					$scope.dealStatusIndicator = 'CEO';
				}
				else if(userType == 'LEVELl1User'){
					$scope.dealStatus = "Pending Level 1";
					$scope.statusIndicator = 'Level 1';
					$scope.dealStatusIndicator = 'Level 1';
				}
				else if(userType == 'LEVELl2User'){
					$scope.dealStatus = "Pending Level 2";
					$scope.statusIndicator = 'Level 2';
					$scope.dealStatusIndicator = 'Level 2';
				}
				else if(userType == 'RiskManagers'){
					$scope.dealStatus = "Pending RiskManagers";
					$scope.statusIndicator = "RiskManagers";	
					$scope.dealStatusIndicator = 'RiskManagers';
				} 
			}
			if($scope.frmDashboard.customerNameModel!=undefined){
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
					if($scope.frmDashboard.customerNameModel.customerId == value.customerId){
			            $scope.customerMappingInDeal.push({
			            	"customerId" : value.customerId,
			            	"currentApprovalStatus":$scope.currentDealStatus,
			            	"userType":userType,
			            	"statusIndicator":$scope.statusIndicator
			     	   });
					}
		        });
			}
			else if (userType=='GFT'|| userType == 'CDO' || userType == 'CEO' 
				|| userType == 'Audit'|| userType == 'LEVELl1User' || userType == 'LEVELl2User' || userType == 'RiskManagers' 
				&& $scope.frmDashboard.customerNameModel == undefined){
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
		            $scope.customerMappingInDeal.push({
		            	"currentApprovalStatus":$scope.currentDealStatus,
		            	"userType":userType,
		            	"statusIndicator":$scope.statusIndicator
		     	   });
		        });
			}
			else if(userType=='BUH'|| userType=='DUH'
				&& $scope.frmDashboard.customerNameModel == undefined)
			{
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
		            $scope.customerMappingInDeal.push({
		            	"customerId" : value.customerId,
		            	"currentApprovalStatus":$scope.currentDealStatus,
		            	"userType":userType,
		            	"statusIndicator":$scope.statusIndicator
		     	   });
		        });
			}
			else
			{
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
		            $scope.customerMappingInDeal.push({
		            	"customerId" : value.customerId,
		            	"currentApprovalStatus":$scope.currentDealStatus,
		            	"userType":userType,
		            	"statusIndicator":$scope.statusIndicator
		     	   });
		        });
			}
			var getDealCountBasedOnVerticalId = function(response){
				$scope.dealCountSummary = response.data;
				console.log("$scope.tempRateCardData.....................................");
				console.log($scope.dealCountSummary);
					for(var i=0;i<$scope.dealCountSummary[1].length;i++)
					{
						
						var approvalStatus=$scope.dealCountSummary[1][i].currentApprovalStatus;
						if($scope.dealCountSummary[1][i].statusIndicator=='Draft' && $scope.dealCountSummary[1][i].currentApprovalStatus ==1)
						{
							if(userType=='GFT' || userType=='Audit' || userType == 'RiskManagers' )
							{
								$scope.btnDisable = true;
								$scope.dealDraftCount = 0;
							}
							else if(userType == 'BUH' || userType == 'DUH' || userType == 'CDO' || userType == 'CEO' || userType == 'LEVELl1User' || userType == 'LEVELl2User')
							{
								$scope.btnDisable = true;
								$scope.dealDraftCount = 0;
							}
							else if(userType!='GFT' || userType!='RiskManagers' )
							{
								$scope.dealDraftCount++;	
							}		
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='GFT' && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealCountGFT++;
						}
						
						/*if(($scope.dealCountSummary[1][i].statusIndicator=='BUH' || $scope.dealCountSummary[1][i].statusIndicator=='BU_Head' || $scope.dealCountSummary[1][i].statusIndicator=='BU Head') && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)*/
						if($scope.dealCountSummary[1][i].statusIndicator=='BU Head' && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealBuhCount++;
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='Delivery Head'  && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealDuhCount++;

						}
						if($scope.dealCountSummary[1][i].statusIndicator=='CEO' && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealCeoCount++;
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='CDO' && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealCdoCount++;
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='Level 1' && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealLevel1Count++;
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='Level 2' && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealLevel2Count++;
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='Approved' && $scope.dealCountSummary[1][i].currentApprovalStatus ==3)
						{
							$scope.dealApprovedCount++;
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='Recycled' && $scope.dealCountSummary[1][i].currentApprovalStatus ==4)
						{
							$scope.dealRejectedCount++;
						}
						if($scope.dealCountSummary[1][i].statusIndicator=='RiskManagers'  && $scope.dealCountSummary[1][i].currentApprovalStatus ==2)
						{
							$scope.dealRiskManagersCount++;

						}
						
						if($scope.dealCountSummary[1][i].currentApprovalStatus ==5)
						{
							$scope.dealDeactivatedCount++;

						}
						
						if($scope.dealCountSummary[1][i].dealcrmstagesdata2.dealStatusId==1)
						{
							$scope.dealWonCount++;

						}
						
						if($scope.dealCountSummary[1][i].dealcrmstagesdata2.dealStatusId==2)
						{
							$scope.dealLostCount++;

						}
					}
					console.log($scope.dealDeactivatedCount);
					for(var i=0;i<$scope.dealCountSummary[3].length;i++)
					{
						if($scope.dealCountSummary[3].length !=0)
						{
							$scope.dealExpiredCount++;
						}
					}
					
					if(userType=='GFT' || userType == 'BUH' || userType == 'DUH' || userType == 'CDO' || userType == 'CEO' 
						|| userType == 'Others'|| userType == 'User'||userType == 'Delivery' || userType == 'RiskManagers' 
						|| userType=='Audit' || userType == 'LEVELl1User' || userType == 'LEVELl2User')
					{
						$scope.dealGftCount = $scope.dealCountGFT;
					}
					
					var currentRoleId = $scope.currentRateCardRoleId;
					var currentStatus = $scope.currentRateCardStatus;
					
					if($scope.getVerticalMemberData!="" || $scope.leadershipDetails!="" || $scope.verticalDetails!=""){
						$scope.tempDealData = $scope.dealCountSummary[2];
						$scope.tempDealDataForDelivary = $scope.dealCountSummary[2];
						if(userType=='CEO'||userType=='CDO' || userType == 'LEVELl1User' || userType == 'LEVELl2User')
						{
							$scope.isLevelApproverDisabled=true;
//							Will Use this code in future
						/*	if(($scope.user).toLowerCase() == ($scope.leadershipDetails[0].leaderLanID).toLowerCase() 
									|| ($scope.user).toLowerCase() == ($scope.leadershipDetails[1].leaderLanID).toLowerCase())
							{*/
								
								for(var i=0;i<$scope.tempDealDataForDelivary.length ;i++)
								{
									for(var k=0;k<$scope.verticalifo.length;k++){
										if($scope.verticalifo[k].appCode == "RP_Industry"){
											if($scope.tempDealDataForDelivary[i].customer.industryName != 'Null' || $scope.tempDealDataForDelivary[i].customer.industryName != undefined)
											{
												if($scope.verticalifo[k].description == $scope.tempDealDataForDelivary[i].customer.industryName)
												{
													$scope.tempDealDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
												break;
												}
											}else{
												
												$scope.tempDealDataForDelivary[i].IndustryName = null
													break;
											}
											}
									}
									
									var dealStartDate  = $scope.tempDealDataForDelivary[i].dealStartDate;
									var date = new Date(dealStartDate.substring(0,10));
									var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempDealDataForDelivary[i].dealStartDate = dealStartDD;
									
									var dealEndDate  = $scope.tempDealDataForDelivary[i].dealEndDate;
									var date = new Date(dealEndDate.substring(0,10));
									var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempDealDataForDelivary[i].dealEndDate = dealEndDateDD;
									
									var approvalStatus  = $scope.tempDealDataForDelivary[i].currentApprovalStatus;
									var statusIndicator = $scope.tempDealDataForDelivary[i].statusIndicator;
									
									if($scope.statusIndicator == 'Draft' || $scope.statusIndicator =='Recycled')
									{
										$scope.isDealDeleteShow = false;
										$scope.tempDealDataForDelivary[i].currentApproverId="-";
										var createdDate  = $scope.tempDealDataForDelivary[i].createdDate;
										var date = new Date(createdDate.substring(0,10));
										var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].createdDate = dealcreatedOnDD;
									}
									else
									{
										$scope.isDealDeleteShow = true;
										$scope.tempDealDataForDelivary[i].currentApproverId="-";
										var updatedDate  = $scope.tempDealDataForDelivary[i].updatedDate;
										var date = new Date(updatedDate.substring(0,10));
										var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].updatedDate = dealupdatedOnDD;
									}
									
									if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
										||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
									{
										$scope.isDealDeleteShow = false;
									}
									
								}
								angular.forEach($scope.tempDealDataForDelivary,function(value,key){
									angular.forEach($scope.towerdata,function(value1,key1){
										if($scope.tempDealDataForDelivary[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
											$scope.towerCount.push({
												"towerId" : $scope.towerdata[key1].dealAutoTowerId
											});
										}
										$scope.towerLength = $scope.towerCount.length;
										$scope.tempDealDataForDelivary[key].towerCount = $scope.towerLength;
										});
									$scope.towerCount=[];
									});
								
								$scope.dealData = $scope.tempDealDataForDelivary;
								angular.forEach($scope.dealData, function(value,key) {
									if($scope.dealData[key].isNewDeal == 2) {
										var isNewDeal = value.isNewDeal;
										$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
									}
								});
								console.log("$scope.dealData");
								console.log($scope.dealData);
							/*}*/
						}
						else if(userType=='GFT')
						{
//							Will Use this code in future
							/*if($scope.getVerticalMemberData[0].employeeLanId != undefined)
							{
								if(($scope.user).toLowerCase() == ($scope.getVerticalMemberData[0].employeeLanId).toLowerCase() 
										&& $scope.getVerticalMemberData[0].roleType == 2)
								{*/
									
									for(var i=0;i<$scope.tempDealDataForDelivary.length ;i++)
									{
										for(var k=0;k<$scope.verticalifo.length;k++){
											if($scope.verticalifo[k].appCode == "RP_Industry"){
												if($scope.tempDealDataForDelivary[i].customer.industryName != 'Null' || $scope.tempDealDataForDelivary[i].customer.industryName != undefined)
												{
													if($scope.verticalifo[k].description == $scope.tempDealDataForDelivary[i].customer.industryName)
													{
														$scope.tempDealDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
													break;
													}
												}else{
													
													$scope.tempDealDataForDelivary[i].IndustryName = null
														break;
												}
												}
										}
										var dealStartDate  = $scope.tempDealDataForDelivary[i].dealStartDate;
										var date = new Date(dealStartDate.substring(0,10));
										var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].dealStartDate = dealStartDD;
										
										var dealEndDate  = $scope.tempDealDataForDelivary[i].dealEndDate;
										var date = new Date(dealEndDate.substring(0,10));
										var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].dealEndDate = dealEndDateDD;
										
										var approvalStatus  = $scope.tempDealDataForDelivary[i].currentApprovalStatus;
										var statusIndicator = $scope.tempDealDataForDelivary[i].statusIndicator;
										
										if($scope.statusIndicator == 'Draft' || $scope.statusIndicator=='Recycled')
										{
											$scope.isDealDeleteShow = false;
											$scope.tempDealDataForDelivary[i].currentApproverId="-";
											var createdDate  = $scope.tempDealDataForDelivary[i].createdDate;
											var date = new Date(createdDate.substring(0,10));
											var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempDealDataForDelivary[i].createdDate = dealcreatedOnDD;
										}
										else
										{
											$scope.isDealDeleteShow = true;
											$scope.tempDealDataForDelivary[i].currentApproverId="-";
											var updatedDate  = $scope.tempDealDataForDelivary[i].updatedDate;
											var date = new Date(updatedDate.substring(0,10));
											var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempDealDataForDelivary[i].updatedDate = dealupdatedOnDD;
										}
										
										if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
											||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
										{
											$scope.isDealDeleteShow = false;
										}
										
									}
									
											angular.forEach($scope.tempDealDataForDelivary,function(value,key){
												angular.forEach($scope.towerdata,function(value1,key1){
													if($scope.tempDealDataForDelivary[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
														$scope.towerCount.push({
															"towerId" : $scope.towerdata[key1].dealAutoTowerId
														});
													}
													$scope.towerLength = $scope.towerCount.length;
													$scope.tempDealDataForDelivary[key].towerCount = $scope.towerLength;
													});
												$scope.towerCount=[];
												});
									$scope.dealData = $scope.tempDealDataForDelivary;
									angular.forEach($scope.dealData, function(value,key) {
										if($scope.dealData[key].isNewDeal == 2) {
											var isNewDeal = value.isNewDeal;
											$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
										}
									});
//									Will Use this code in future
							/*	}
							}
							else{
								BootstrapDialog.show({
									title : 'My Dashboard - RateCard',
									type : BootstrapDialog.TYPE_DANGER,
									message : 'Not valid user.',
									closable : false,
									buttons : [{
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											window.location="/RightPrice-DAS";
										}
									}]
								});
							}*/
						}
						
						else if(userType=='BUH'||userType=='DUH' || userType == 'RiskManagers')
						{
							$scope.isLevelApproverDisabled=true;
//							Will Use this code in future
							/*if(($scope.user).toLowerCase() == ($scope.verticalDetails[0].buHeadId).toLowerCase()|| ($scope.user).toLowerCase() == ($scope.verticalDetails[0].deliveryHeadId).toLowerCase()
									||($scope.user).toLowerCase() == ($scope.verticalDetails[0].RiskManagersPersonId).toLowerCase())
							{*/
									for(var i=0;i<$scope.tempDealDataForDelivary.length ;i++){
										
										for(var k=0;k<$scope.verticalifo.length;k++){
											if($scope.verticalifo[k].appCode == "RP_Industry"){
												if($scope.tempDealDataForDelivary[i].customer.industryName != 'Null' || $scope.tempDealDataForDelivary[i].customer.industryName != undefined)
												{
													if($scope.verticalifo[k].description == $scope.tempDealDataForDelivary[i].customer.industryName)
													{
														$scope.tempDealDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
													break;
													}
												}else{
													
													$scope.tempDealDataForDelivary[i].IndustryName = null
														break;
												}
												}
										}
										var dealStartDate  = $scope.tempDealDataForDelivary[i].dealStartDate;
										var date = new Date(dealStartDate.substring(0,10));
										var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].dealStartDate = dealStartDD;
										
										var dealEndDate  = $scope.tempDealDataForDelivary[i].dealEndDate;
										var date = new Date(dealEndDate.substring(0,10));
										var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].dealEndDate = dealEndDateDD;
										
										var approvalStatus  = $scope.tempDealDataForDelivary[i].currentApprovalStatus;
										var statusIndicator = $scope.tempDealDataForDelivary[i].statusIndicator;
										
										
										if($scope.statusIndicator == 'Draft' || $scope.statusIndicator=='Recycled')
										{
											$scope.isDealDeleteShow = false;
											$scope.tempDealDataForDelivary[i].currentApproverId="-";
											var createdDate  = $scope.tempDealDataForDelivary[i].createdDate;
											var date = new Date(createdDate.substring(0,10));
											var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempDealDataForDelivary[i].createdDate = dealcreatedOnDD;
										}
										else
										{
											$scope.isDealDeleteShow = true;
											$scope.tempDealDataForDelivary[i].currentApproverId="-";
											var updatedDate  = $scope.tempDealDataForDelivary[i].updatedDate;
											var date = new Date(updatedDate.substring(0,10));
											var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempDealDataForDelivary[i].updatedDate = dealupdatedOnDD;
										}
										
										if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
											||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
										{
											$scope.isDealDeleteShow = false;
										}
										
									}
									angular.forEach($scope.tempDealDataForDelivary,function(value,key){
										angular.forEach($scope.towerdata,function(value1,key1){
											if($scope.tempDealDataForDelivary[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
												$scope.towerCount.push({
													"towerId" : $scope.towerdata[key1].dealAutoTowerId
												});
											}
											$scope.towerLength = $scope.towerCount.length;
											$scope.tempDealDataForDelivary[key].towerCount = $scope.towerLength;
											});
										$scope.towerCount=[];
										});
									$scope.dealData = $scope.tempDealDataForDelivary;
									angular.forEach($scope.dealData, function(value,key) {
										if($scope.dealData[key].isNewDeal == 2) {
											var isNewDeal = value.isNewDeal;
											$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
										}
									});
							/*}*/
						} else if (userType == 'Audit') {

//							Will Use this code in future
							/*if($scope.getVerticalMemberData[0].employeeLanId != undefined)
							{
								if(($scope.user).toLowerCase() == ($scope.getVerticalMemberData[0].employeeLanId).toLowerCase() 
										&& $scope.getVerticalMemberData[0].roleType == 2)
								{*/
									
									for(var i=0;i<$scope.tempDealDataForDelivary.length ;i++)
									{
										for(var k=0;k<$scope.verticalifo.length;k++){
											if($scope.verticalifo[k].appCode == "RP_Industry"){
												if($scope.tempDealDataForDelivary[i].customer.industryName != 'Null' || $scope.tempDealDataForDelivary[i].customer.industryName != undefined)
												{
													if($scope.verticalifo[k].description == $scope.tempDealDataForDelivary[i].customer.industryName)
													{
														$scope.tempDealDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
													break;
													}
												}else{
													
													$scope.tempDealDataForDelivary[i].IndustryName = null
														break;
												}
												}
										}
										var dealStartDate  = $scope.tempDealDataForDelivary[i].dealStartDate;
										var date = new Date(dealStartDate.substring(0,10));
										var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].dealStartDate = dealStartDD;
										
										var dealEndDate  = $scope.tempDealDataForDelivary[i].dealEndDate;
										var date = new Date(dealEndDate.substring(0,10));
										var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealDataForDelivary[i].dealEndDate = dealEndDateDD;
										
										var approvalStatus  = $scope.tempDealDataForDelivary[i].currentApprovalStatus;
										var statusIndicator = $scope.tempDealDataForDelivary[i].statusIndicator;
										
										if($scope.statusIndicator == 'Draft' || $scope.statusIndicator=='Recycled')
										{
											$scope.isDealDeleteShow = true;
											$scope.tempDealDataForDelivary[i].currentApproverId="-";
											var createdDate  = $scope.tempDealDataForDelivary[i].createdDate;
											var date = new Date(createdDate.substring(0,10));
											var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempDealDataForDelivary[i].createdDate = dealcreatedOnDD;
										}
										else
										{
											$scope.isDealDeleteShow = true;
											$scope.tempDealDataForDelivary[i].currentApproverId="-";
											var updatedDate  = $scope.tempDealDataForDelivary[i].updatedDate;
											var date = new Date(updatedDate.substring(0,10));
											var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
											$scope.tempDealDataForDelivary[i].updatedDate = dealupdatedOnDD;
										}
										
										if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
											||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
										{
											$scope.isDealDeleteShow = true;
										}
										
									}
									
											angular.forEach($scope.tempDealDataForDelivary,function(value,key){
												angular.forEach($scope.towerdata,function(value1,key1){
													if($scope.tempDealDataForDelivary[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
														$scope.towerCount.push({
															"towerId" : $scope.towerdata[key1].dealAutoTowerId
														});
													}
													$scope.towerLength = $scope.towerCount.length;
													$scope.tempDealDataForDelivary[key].towerCount = $scope.towerLength;
													});
												$scope.towerCount=[];
												});
									$scope.dealData = $scope.tempDealDataForDelivary;
									angular.forEach($scope.dealData, function(value,key) {
										if($scope.dealData[key].isNewDeal == 2) {
											var isNewDeal = value.isNewDeal;
											$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
										}
									});
//									Will Use this code in future
							/*	}
							}
							else{
								BootstrapDialog.show({
									title : 'My Dashboard - RateCard',
									type : BootstrapDialog.TYPE_DANGER,
									message : 'Not valid user.',
									closable : false,
									buttons : [{
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											window.location="/RightPrice-DAS";
										}
									}]
								});
							}*/
						}
						else if(userType =='User'||userType == 'Delivery')
						{
							/*if( $scope.getVerticalMemberData[0].roleType == 2 && currentRoleId == undefined && currentStatus == undefined)*/
							if($scope.getVerticalMemberData[0].roleType == 2)
							{
								
								for(var i=0;i<$scope.tempDealData.length;i++){
								
									for(var k=0;k<$scope.verticalifo.length;k++){
										if($scope.verticalifo[k].appCode == "RP_Industry"){
											if($scope.tempDealDataForDelivary[i].customer.industryName != 'Null' || $scope.tempDealDataForDelivary[i].customer.industryName != undefined)
											{
												if($scope.verticalifo[k].description == $scope.tempDealDataForDelivary[i].customer.industryName)
												{
													$scope.tempDealDataForDelivary[i].IndustryName = $scope.verticalifo[k].codeType;
												break;
												}
											}else{
												
												$scope.tempDealDataForDelivary[i].IndustryName = null
													break;
											}
											}
									}
									
									var dealStartDate  = $scope.tempDealData[i].dealStartDate;
									var date = new Date(dealStartDate.substring(0,10));
									var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempDealData[i].dealStartDate = dealStartDD;
									
									var dealEndDate  = $scope.tempDealData[i].dealEndDate;
									var date = new Date(dealEndDate.substring(0,10));
									var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
									$scope.tempDealData[i].dealEndDate = dealEndDateDD;
									
									var approvalStatus  = $scope.tempDealData[i].currentApprovalStatus;
									var statusIndicator = $scope.tempDealData[i].statusIndicator;
									
									if($scope.statusIndicator == 'Draft' || $scope.statusIndicator=='Recycled')
									{
										$scope.isDealDeleteShow = false;
										$scope.tempDealData[i].currentApproverId="-";
										var createdDate  = $scope.tempDealData[i].createdDate;
										var date = new Date(createdDate.substring(0,10));
										var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealData[i].createdDate = dealcreatedOnDD;
									}
									else
									{
										$scope.isDealDeleteShow = true;
										$scope.tempDealData[i].currentApproverId="-";
										var updatedDate  = $scope.tempDealData[i].updatedDate;
										var date = new Date(updatedDate.substring(0,10));
										var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
										$scope.tempDealData[i].updatedDate = dealupdatedOnDD;
									}
									
									if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
										||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
									{
										$scope.isDealDeleteShow = false;
									}
									
								}
								angular.forEach($scope.tempDealData,function(value,key){
									angular.forEach($scope.towerdata,function(value1,key1){
										if($scope.tempDealData[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
											$scope.towerCount.push({
												"towerId" : $scope.towerdata[key1].dealAutoTowerId
											});
										}
										$scope.towerLength = $scope.towerCount.length;
										$scope.tempDealData[key].towerCount = $scope.towerLength;
										});
									$scope.towerCount=[];
									});
								$scope.dealData = $scope.tempDealData;
								
								angular.forEach($scope.dealData, function(value,key) {
									if($scope.dealData[key].isNewDeal == 2) {
										var isNewDeal = value.isNewDeal;
										$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
									}
								});
							}
						}
					}
				}
				
				WebServiceFactory.getDealCountBasedOnVerticalId($scope.customerMappingInDeal).then(getDealCountBasedOnVerticalId);
		
			
		};
		WebServiceFactory.getAppCodeData().then(getVerticalData);
	
		
	}
	
	$scope.getDashboardDealData = function(roleId,dealStatus){
			$scope.searchDeal = "";
			$scope.dealData = [];
			$scope.customerMappingForDealDetail = null;
			$scope.customerMappingForDealDetail = [];
			if(dealStatus == 1){
				$scope.isDealDeleteShow = false;
				$scope.currentDealStatus=1;
				$scope.dealStatus = "Draft";
				$scope.currentDealRoleId=null;
				$scope.currentDealStatus = 1;
				$scope.statusIndicator = 'Draft';
				$scope.dealStatusIndicator = 'Draft';
				
			}
			else if(dealStatus == 2){
				$scope.isDealDeleteShow = true;
				$scope.currentDealStatus = 2;
				$scope.currentRateCardStatus=2;
				if(roleId == 4){
					$scope.dealStatus = "Pending DH";
					$scope.currentDealRoleId=4;
					$scope.statusIndicator = 'Delivery Head';
					$scope.dealStatusIndicator = 'Delivery Head';
				}
				else if(roleId == 5){
					$scope.dealStatus = "Pending RiskManagers";
					$scope.currentDealRoleId=5;
					$scope.statusIndicator = 'RiskManagers';
					$scope.dealStatusIndicator = 'RiskManagers';
				}
				else if(roleId == 6){
					$scope.dealStatus = "Pending GFT";	
					$scope.currentDealRoleId=6;
					$scope.statusIndicator = 'GFT';	
					$scope.dealStatusIndicator = 'GFT';
				}
				else if(roleId == 7){
					$scope.dealStatus = "Pending CDO";
					$scope.currentDealRoleId=7;
					$scope.statusIndicator = 'CDO';	
					$scope.dealStatusIndicator = 'CDO';
				}
				else if(roleId == 8){
					$scope.dealStatus = "Pending BUH";
					$scope.currentDealRoleId=8;
					$scope.statusIndicator = 'BU Head';	
					$scope.dealStatusIndicator = 'BU Head';
				}
				else if(roleId == 10){
					$scope.dealStatus = "Pending CEO";
					$scope.currentDealRoleId=10;
					$scope.statusIndicator = 'CEO';	
					$scope.dealStatusIndicator = 'CEO';
				}
				else if(roleId == 11){
					$scope.dealStatus = "Pending Level 1";
					$scope.currentDealRoleId=11;
					$scope.statusIndicator = 'LeveL 1';
					$scope.dealStatusIndicator = 'Level 1';
				}
				else if(roleId == 12){
					$scope.dealStatus = "Pending Level 2";
					$scope.currentDealRoleId=12;
					$scope.statusIndicator = 'Level 2';
					$scope.dealStatusIndicator = 'Level 2';
				}
			}
			else if(dealStatus == 3){
				$scope.isDealDeleteShow = true;
				$scope.currentDealStatus = 3;
				$scope.dealStatus = "Approved";
				$scope.currentRateCardStatus=3;
				$scope.currentDealRoleId=null;
				$scope.statusIndicator = 'Approved';
				$scope.dealStatusIndicator = 'Approved';
			}
			else if(dealStatus == 4){
				$scope.isDealDeleteShow = false;
				$scope.currentDealStatus = 4;
				$scope.dealStatus = "Recycled";
				$scope.currentRateCardStatus=4;
				$scope.currentDealRoleId=null;
				$scope.statusIndicator = 'Recycled';
				$scope.dealStatusIndicator = 'Recycled';
			}
			else if(dealStatus == 5){
				$scope.isDealDeleteShow = true;
				$scope.currentDealStatus = 5;
				$scope.dealStatus = "Deactivated";
				$scope.currentRateCardStatus=5;
				$scope.currentDealRoleId=null;
				$scope.statusIndicator = 'Deactivated';
				$scope.dealStatusIndicator = 'Deactivated';	
			}
			else if(dealStatus == 6){
				$scope.isDealDeleteShow = true;
				$scope.currentDealStatus = 6;
				$scope.dealStatus = "Expired";
				$scope.currentRateCardStatus=6;
				$scope.currentDealRoleId=null;
				$scope.statusIndicator = 'Expired';	
				$scope.dealStatusIndicator = 'Expired';	
			}
			var dealStatus = $scope.currentDealStatus;
			
			
				
			if($scope.selectedCustomer != undefined){
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
					if($scope.selectedCustomer == value.customerId){
			            $scope.customerMappingForDealDetail.push({
			            "customerId" : value.customerId,
			           	"currentApproverRoleId":roleId,
			           	"currentApprovalStatus":dealStatus,
			        	"userType":userType,
			           	"statusIndicator":$scope.statusIndicator
			     	   });
					}
		        });
			}
			else{
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
		            $scope.customerMappingForDealDetail.push({
		            "customerId" : value.customerId,
		           	"currentApproverRoleId":roleId,
		           	"currentApprovalStatus":dealStatus,
		        	"userType":userType,
		           	"statusIndicator":$scope.statusIndicator
		     	   });
		        });
			}
			
			var getDashboardDealData = function(response){
				$scope.dealData = null;
				console.log("getDealData");
				console.log(response);
				$scope.tempDealData = response.data;
				console.log("$scope.tempDealData......................");
				console.log($scope.tempDealData);
				if(response.status == 200){
				for(var i=0;i<$scope.tempDealData.length ;i++){
					var dealStartDate  = $scope.tempDealData[i].dealStartDate;
					var date = new Date(dealStartDate.substring(0,10));
					var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempDealData[i].dealStartDate = dealStartDD;
					
					var dealEndDate  = $scope.tempDealData[i].dealEndDate;
					var date = new Date(dealEndDate.substring(0,10));
					var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempDealData[i].dealEndDate = dealEndDateDD;
												
					var approvalStatus  = $scope.tempDealData[i].currentApprovalStatus;
					var statusIndicator = $scope.tempDealData[i].statusIndicator;
					
					 if($scope.statusIndicator == 'Draft' || $scope.statusIndicator =='Recycled')
					{
						$scope.isDealDeleteShow = false;
						$scope.tempDealData[i].currentApproverId="-";
						var createdDate  = $scope.tempDealData[i].createdDate;
						var date = new Date(createdDate.substring(0,10));
						var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.tempDealData[i].createdDate = dealcreatedOnDD;
					}
					 else if (approvalStatus = 5)
					 {
						 $scope.isDealDeleteShow = true;
							$scope.tempDealData[i].currentApproverId="-";
							var updatedDate  = $scope.tempDealData[i].updatedDate;
							var date = new Date(updatedDate.substring(0,10));
							var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
							$scope.tempDealData[i].updatedDate = dealupdatedOnDD;
							
							$scope.tempDealData[i].currentApproverId="-";
							var createdDate  = $scope.tempDealData[i].createdDate;
							var date = new Date(createdDate.substring(0,10));
							var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
							$scope.tempDealData[i].createdDate = dealcreatedOnDD;
						 
					 }
					else
					{
						$scope.isDealDeleteShow = true;
						$scope.tempDealData[i].currentApproverId="-";
						var updatedDate  = $scope.tempDealData[i].updatedDate;
						var date = new Date(updatedDate.substring(0,10));
						var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.tempDealData[i].updatedDate = dealupdatedOnDD;
					}
					
					if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
						||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
					{
						$scope.isDealDeleteShow = false;
					}
					
				}
				angular.forEach($scope.tempDealData,function(value,key){
					angular.forEach($scope.towerdata,function(value1,key1){
						if($scope.tempDealData[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
							$scope.towerCount.push({
								"towerId" : $scope.towerdata[key1].dealAutoTowerId
							});
						}
						$scope.towerLength = $scope.towerCount.length;
						$scope.tempDealData[key].towerCount = $scope.towerLength;
						});
					$scope.towerCount=[];
					});
				$scope.dealData = $scope.tempDealData;
				angular.forEach($scope.dealData, function(value,key) {
					if($scope.dealData[key].isNewDeal == 2) {
						var isNewDeal = value.isNewDeal;
						$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
					}
				});
				}
			}
			WebServiceFactory.getDashboardDealData($scope.customerMappingForDealDetail).then(getDashboardDealData);
		}
	
	$scope.getDealDataOnSearch = function(crmDealID)
	{
		$scope.dealData = [];
		var dealStatusIdicator ="";
		var getDealDataOnSearch = function(response){
		$scope.dealData = null;
		console.log("getRateCardData");
		console.log(response);
			if(response.status == 200){
				$scope.tempDealData = response.data;
				
				if($scope.tempDealData[0].statusIndicator == 'Draft'||$scope.tempDealData[0].statusIndicator == 'DRAFT')
				{
					$scope.dealStatus = "Draft";
				}
				else if($scope.tempDealData[0].statusIndicator == 'GFT')
				{
					$scope.dealStatus = "Pending GFT";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'CEO')
				{
					$scope.dealStatus = "Pending CEO";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'Level 1')
				{
					$scope.dealStatus = "Pending Level 1";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'RiskManagers')
				{
					$scope.dealStatus = "Pending RiskManagers";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'Level 2')
				{
					$scope.dealStatus = "Pending Level 2";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'CDO')
				{
					$scope.dealStatus = "Pending CDO";
				}
				
				/*else if($scope.tempDealData[0].statusIndicator == 'BUH' ||$scope.tempDealData[0].statusIndicator == 'BU_Head' ||$scope.tempDealData[0].statusIndicator == 'BU Head')*/
				else if($scope.tempDealData[0].statusIndicator == 'BU Head')
				{
					$scope.dealStatus = "Pending BUH";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'Delivery Head')
				{
					$scope.dealStatus = "Pending DH";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'Approved'||$scope.tempDealData[0].statusIndicator == 'APPROVED')
				{
					$scope.dealStatus = "Approved";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'Deactivated'||$scope.tempDealData[0].statusIndicator == 'DEACTIVATED')
				{
					$scope.dealStatus = "Deactivated";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'Recycled' || $scope.tempDealData[0].statusIndicator == 'RECYCLED')
				{
					$scope.dealStatus = "Recycled";
				}
				
				else if($scope.tempDealData[0].statusIndicator == 'Expired' || $scope.tempDealData[0].statusIndicator == 'EXPIRED')
				{
					$scope.dealStatus = "Expired";
				}
				else if($scope.tempDealData[0].statusIndicator == 'Pending Approval')
				{
					$scope.dealStatus = "Pending Approval";
				}
				dealStatusIdicator = $scope.dealStatus;
				for(var i=0;i<$scope.tempDealData.length ;i++){
				var dealStartDate  = $scope.tempDealData[i].dealStartDate;
				var date = new Date(dealStartDate.substring(0,10));
				var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
				$scope.tempDealData[i].dealStartDate = dealStartDD;
				
				var dealEndDate  = $scope.tempDealData[i].dealEndDate;
				var date = new Date(dealEndDate.substring(0,10));
				var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
				$scope.tempDealData[i].dealEndDate = dealEndDateDD;
											
				var approvalStatus  = $scope.tempDealData[i].currentApprovalStatus;
				var statusIndicator = $scope.tempDealData[i].statusIndicator;
				
				 if($scope.statusIndicator == 'Draft' || $scope.statusIndicator =='Recycled')
				{
					$scope.isDealDeleteShow = false;
					$scope.tempDealData[i].currentApproverId="-";
					var createdDate  = $scope.tempDealData[i].createdDate;
					var date = new Date(createdDate.substring(0,10));
					var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempDealData[i].createdDate = dealcreatedOnDD;
				}
				else
				{
					$scope.isDealDeleteShow = true;
					$scope.tempDealData[i].currentApproverId="-";
					var updatedDate  = $scope.tempDealData[i].updatedDate;
					var date = new Date(updatedDate.substring(0,10));
					var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempDealData[i].updatedDate = dealupdatedOnDD;
				}
				
				if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
					||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
				{
					$scope.isDealDeleteShow = false;
				}
			}
		}
			else if(response.status == 204)
			{
				BootstrapDialog.show({
					title : 'My Dashboard',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'The Deal has not been migrated from Sales Force to Right Price application <br> (OR) <br> Serach deal  along with Item number (For Ex:: Deal Id_10) ',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							window.location="MyDashBoard";
						}
					}]
				});
			}
			else if(response.status == 205)
			{
				BootstrapDialog.show({
					title : 'My Dashboard',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'The Deal has not been migrated to Right Price.<br>Please folow the <b>conditions present in RightPrice Home Page.</b></br> Raise PISA Ticket path Home ---->IT ---->Specialist Systems ---> <br>RightPrice<b>(RightPrice - Questions: How to…? - It is possible to..?)</b></br> ',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							window.location="MyDashBoard";
						}
					}]
				});
			}			
			
			else if(response.status == 202)
			{
				console.log("data in 202 ----")
				console.log(response.data)

				BootstrapDialog.show({
			           title: 'My Dashboard',
			           type : BootstrapDialog.TYPE_PRIMARY,
			           message : 'A version has not been created for this Deal. Do you want to create a new version? ',
			           closable: true,
			           buttons: [{
			               label: 'Yes',
			               cssClass: 'btn-primary',
			               action: function(dialogRef) {
			            	   sessionStorage.setItem('dashboardData', 1 );
			            	   sessionStorage.setItem('crmDealId',response.data[0].crmDealId);
			            	   sessionStorage.setItem('accountId',response.data[0].customerId);
			            	   if(response.data[0].dealTypeId == 2){
			    					window.open('TMDealCreationDetails', '_blank','$sessionStorage.crmDealId','$sessionStorage.accountId');
			            	   }else{
			            			window.open('FPDealCreationDetails','$sessionStorage.crmDealId','$sessionStorage.accountId');
			            	   }
			            	   //$scope.showDealDataFP(response.data[0].crmDealId,rpVersionDealIdFP, approverIdFP, approvalStatusFP,manualTypeFP)
			            	   dialogRef.close();
			            	   window.location="MyDashBoard";
			               }
			           }, {
				            label: 'No',
				            cssClass: 'btn-primary',
				            action: function(dialog) {
				            	   	dialog.close();
				            	    window.location="MyDashBoard";
				            }
				        }]
					});
			}	

			angular.forEach($scope.tempDealData,function(value,key){
				angular.forEach($scope.towerdata,function(value1,key1){
					if($scope.tempDealData[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
						$scope.towerCount.push({
							"towerId" : $scope.towerdata[key1].dealAutoTowerId
						});
					}
					$scope.towerLength = $scope.towerCount.length;
					$scope.tempDealData[key].towerCount = $scope.towerLength;
					});
				$scope.towerCount=[];
				});
			$scope.dealData = $scope.tempDealData;
			angular.forEach($scope.dealData, function(value,key) {
				if($scope.dealData[key].isNewDeal == 2) {
					var isNewDeal = value.isNewDeal;
					$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
				}
			});
			}	
	WebServiceFactory.getDealDataOnSearch(crmDealID).then(getDealDataOnSearch);
	}
	
	$scope.deleteDealVersion = function (dltDealVersionId)
	{
		
		BootstrapDialog.show(
		{
			title : 'My Dashboard',
			type : BootstrapDialog.TYPE_PRIMARY,
			message : 'Do you want to Delete Deal Version.',
			closable : false,
			buttons : [
			{
				label : 'Yes',
				cssClass : 'btn-primary',
				action : function(dialogRef) 
				{
					dialogRef.close();
					$scope.deleteVersion(dltDealVersionId);
				}
			},
			{
				label : 'No',
				cssClass : 'btn-primary',
				action : function(dialogItself) 
				{
					dialogItself.close();
				}
			}]
		});
	}	
	
	$scope.deleteVersion = function (dltDealVersionId){

		var deletedResp = function(resp){
			
			/*BootstrapDialog.show(
					{
						title : 'My Dashboard',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'Deal Version Deleted successfully.',
						closable : false,
						buttons : [
						{
							label : 'OK',
							action : function(dialogRef) 
							{
								dialogRef.close();
								window.location="MyDashBoard";
							}
						} ]
					});*/
			
			if(resp.status == 200)	
			{
				BootstrapDialog.show({
					title : 'My Dashboard',
					type : BootstrapDialog.TYPE_PRIMARY,
					message : 'Deal dersion deleted successfully.',
					closable : false,
					buttons : [ {
						label : 'OK',
						action :function(
								dialogRef) {
							dialogRef.close();
							window.location="MyDashBoard";
						}
					} ]
				});	
			} 
	    	else if(resp.status == 205){
					BootstrapDialog.show({
					title : 'My Dashboard',
					type : BootstrapDialog.TYPE_DANGER,
					message : "You are not authorized to delete the version.",
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
		WebServiceFactory.deleteDealVersion(dltDealVersionId).then(deletedResp);
		
	}
	
	// getting the FP Tower Details on load
	var getFpDealTowerData = function(response) 
	{
		console.log("fp Deal Tower data ..............");
		console.log(response);
		$scope.towerdata = response.data;
	};
	WebServiceFactory.getFpDealTowerData().then(getFpDealTowerData);
	

		
	$scope.showDealDetails = function(crmDealId,rpVersionDealId, approverId, approvalStatus, dealType ,manualType)
	{
		var crmDealIdTM = null;
		var rpVersionDealIdTM = null;
		var approverIdTM = null;
		var approvalStatusTM = null;
		
		var crmDealIdFP = null;
		var rpVersionDealIdFP = null; 
		var approverIdFP = null;
		var approvalStatusFP = null;
		var manualTypeFP = null;
		
		if(dealType == 1)
		{
			crmDealIdFP = crmDealId;
			rpVersionDealIdFP = rpVersionDealId; 
			approverIdFP = approverId;
			approvalStatusFP = approvalStatus;
			manualTypeFP=manualType;
			
			$scope.showDealDataFP(crmDealIdFP,rpVersionDealIdFP, approverIdFP, approvalStatusFP,manualTypeFP);
		}
		else if(dealType == 2)
		{
			crmDealIdTM = crmDealId;
			rpVersionDealIdTM = rpVersionDealId;
			approverIdTM = approverId;
			approvalStatusTM = approvalStatus;
			
			$scope.showDealDataTM(crmDealIdTM,rpVersionDealIdTM, approverIdTM, approvalStatusTM);
		}
	
	}

	$scope.showDealDataTM = function(crmDealIdTM,rpVersionDealIdTM, approverIdTM, approvalStatusTM)
	{
		$localStorage.crmDealId = null;
		$localStorage.rpDealVersionId = null;
		$localStorage.dealApproverId = null;
		$localStorage.dealApprovalStatus = null;
		if($localStorage.crmDealId == null && $localStorage.rpDealVersionId == null && $localStorage.dealApproverId == null && $localStorage.dealApprovalStatus == null)
		{
			$localStorage.crmDealId = crmDealIdTM;
			$localStorage.rpDealVersionId = rpVersionDealIdTM;
			$localStorage.dealApproverId = approverIdTM;
			$localStorage.dealApprovalStatus = approvalStatusTM;	
			
			var browser = window.navigator.appVersion;
			
			if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
					(browser.indexOf('MSIE 10') !== -1)) 
			{
				if(approvalStatusTM == 2 || approvalStatusTM == 3)
				{
					window.open('TMDealCreationSummary', '$localStorage.rpDealVersionId');
				}
				else
				{
					window.open('TMDealCreationDetails', '$localStorage.rpDealVersionId');
				}
			}
			else
			{
				if(approvalStatusTM == 2 || approvalStatusTM == 3)
				{
					window.open('TMDealCreationSummary', '_blank', '$localStorage.rpDealVersionId','$localStorage.crmDealId');
				}
				else
				{
					window.open('TMDealCreationDetails', '_blank', '$localStorage.rpDealVersionId','$localStorage.crmDealId');
				}
			}
		}
		
	}

	$scope.showDealDataFP = function(crmDealIdFP,rpVersionDealIdFP, approverIdFP, approvalStatusFP,manualTypeFP)
	{
		$localStorage.DealModel = null;
		$localStorage.rpDealVersionId = null;
		$localStorage.dealApproverId = null;
		$localStorage.dealApprovalStatus = null;
		$localStorage.dealmanualType = null;
		if($localStorage.DealModel == null && $localStorage.rpDealVersionId == null && $localStorage.dealApproverId == null && $localStorage.dealmanualType == null)
		{
			$localStorage.DealModel = crmDealIdFP;
			$localStorage.rpDealVersionId = rpVersionDealIdFP;
			$localStorage.dealApproverId = approverIdFP;
			$localStorage.dealApprovalStatus = approvalStatusFP;	
			$localStorage.dealmanualType = manualTypeFP;
			var browser = window.navigator.appVersion;
		
			if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
					(browser.indexOf('MSIE 10') !== -1)) 
			{
				if(approvalStatusFP == 2 || approvalStatusFP == 3)
				{
					if(manualTypeFP == 0)
						{
							window.open('FPDealCreationWhatIfApplicationwise', '$localStorage.rpDealVersionId');
						}
					else if(manualTypeFP == 1){
							window.open('FPDealCreationManualDealSummary', '$localStorage.rpDealVersionId');
					}
				}
				else
				{
					window.open('FPDealCreationDetails', '$localStorage.rpDealVersionId');
				}
			}
			else
			{
				if(approvalStatusFP == 2 || approvalStatusFP == 3)
				{	if(manualTypeFP == 0)
					{
						window.open('FPDealCreationWhatIfApplicationwise', '_blank', '$localStorage.rpDealVersionId','$localStorage.DealModel');
					}
					else if(manualTypeFP == 1){
						window.open('FPDealCreationManualDealSummary', '$localStorage.rpDealVersionId');
					}
				}
				else
				{
					window.open('FPDealCreationDetails', '_blank', '$localStorage.rpDealVersionId','$localStorage.DealModel');
				}
			}
		}
			
	}
	
	   $scope.download = function(frmDashboard){
		   if($scope.status!=undefined || $scope.status!=null ){
			   $scope.statusIndicator=$scope.status;
		   }
		   
		   var customerId=0;
		   if($scope.frmDashboard.customerNameModel!=undefined || $scope.frmDashboard.customerNameModel!=null)
		   {
			   customerId=$scope.frmDashboard.customerNameModel.customerId
		   }
		   $scope.customerArray=[]
		   console.log("Tower Data ::: ");
		   console.log($scope.towerdata);
		   var rateCardID=0;
		   var dealID=0;
		   if($scope.frmDashboard.searchRateCard!=undefined || $scope.frmDashboard.searchRateCard!=null){
			   rateCardID=$scope.frmDashboard.searchRateCard;
		   }
		   if($scope.frmDashboard.searchDeal!=undefined || $scope.frmDashboard.searchDeal!=null){
			   dealID=$scope.frmDashboard.searchDeal;
		   }
		   if($scope.dealStatusValue==undefined || $scope.dealStatusValue == null ){
			   $scope.dealStatusValue =0;
		   }
		   window.location= contextPath+"/RightPrice-DAS/downloadMyDashboardExcel/"+userType+"/"+$scope.rcStatusIndicator+"/"+$scope.dealStatusIndicator+"/"+$scope.rateCardStatus+"/"+	$scope.dealStatus+"/"+customerId+"/"+$scope.onloadFlag+"/"+rateCardID+"/"+dealID+"/"+$scope.currentDealStatus+"/"+$scope.dealStatusValue;
		   //WebServiceFactory.downloadMyDashboardExcel($scope.dashboardArray,$scope.rateCardStatus);
		
	    }
	   
	   $scope.dealStatusValue = null; 
	   // get the deal details of the Lost and Won status.
	   $scope.getDealStatusDetails = function(dealStatusValue) {
		   $scope.customerMappingForDealDetail = null;
		   $scope.customerMappingForDealDetail = [];
		   if(dealStatusValue == 1)
		   {
			   $scope.dealStatus = "Won";
			   $scope.dealStatusValue= dealStatusValue;
		   }
		   else if(dealStatusValue == 2)
		   {
			   $scope.dealStatus = "Lost";
			   $scope.dealStatusValue= dealStatusValue;
		   }
		   if($scope.selectedCustomer != undefined){
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
					if($scope.selectedCustomer == value.customerId){
			            $scope.customerMappingForDealDetail.push({
			            "customerId" : value.customerId,
			        	"userType":userType,
			        	"dealStatusIdWL":dealStatusValue
			     	   });
					}
		        });
			}
			else{
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
		            $scope.customerMappingForDealDetail.push({
		            "customerId" : value.customerId,
		        	"userType":userType,
		        	"dealStatusIdWL":dealStatusValue
		     	   });
		        });
			}
		   var getDealStatusDetails = function(response) {
			   $scope.dealStatusDetails = response.data;
					for(var i=0;i<$scope.dealStatusDetails.length ;i++){
						$scope.isDealDeleteShow = true;
						var dealStartDate  = $scope.dealStatusDetails[i].dealStartDate;
						var date = new Date(dealStartDate.substring(0,10));
						var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.dealStatusDetails[i].dealStartDate = dealStartDD;
						
						var dealEndDate  = $scope.dealStatusDetails[i].dealEndDate;
						var date = new Date(dealEndDate.substring(0,10));
						var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.dealStatusDetails[i].dealEndDate = dealEndDateDD;
						
						var createdDate  = $scope.dealStatusDetails[i].createdDate;
						var date = new Date(createdDate.substring(0,10));
						var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.dealStatusDetails[i].createdDate = dealcreatedOnDD;
							
						var updatedDate  = $scope.dealStatusDetails[i].updatedDate;
						var date = new Date(updatedDate.substring(0,10));
						var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.dealStatusDetails[i].updatedDate = dealupdatedOnDD;
						}
					angular.forEach($scope.dealStatusDetails,function(value,key){
						angular.forEach($scope.towerdata,function(value1,key1){
							if($scope.dealStatusDetails[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
								$scope.towerCount.push({
									"towerId" : $scope.towerdata[key1].dealAutoTowerId
								});
							}
							$scope.towerLength = $scope.towerCount.length;
							$scope.dealStatusDetails[key].towerCount = $scope.towerLength;
							});
						$scope.towerCount=[];
						});
					$scope.dealData = $scope.dealStatusDetails;
					angular.forEach($scope.dealData, function(value,key) {
						if($scope.dealData[key].isNewDeal == 2) {
							var isNewDeal = value.isNewDeal;
							$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
						}
					});
		   };
		   WebServiceFactory.getDealStatusDetails($scope.customerMappingForDealDetail).then(getDealStatusDetails);
	   };
	   
	   window.onfocus = function(){
		   sessionStorage.setItem('dashboardData', 0 );
	   }
	   // getting the vertical based on the current user
	   
			/*$scope.verticalId="";
			$scope.getVerticalApproverData = function() {
				var getVerticalApproverData = function(response) 
				{
					$scope.verticalDetails = response.data;
					if($scope.verticalDetails!=undefined);
					{
						angular.forEach($scope.verticalDetails, function(value,key) {
							if($scope.user != null) {
								if($scope.user == value.deliveryHeadId || $scope.user == value.buHeadId) {
									$scope.verticalId=value.verticalId;
									$scope.getDUHCustomerVerticalMapping($scope.verticalId);
								}
							} else {
								if($sessionStorage.currentUser == value.deliveryHeadId || $sessionStorage.currentUser == value.buHeadId) {
									$scope.verticalId=value.verticalId;
									$scope.getDUHCustomerVerticalMapping($scope.verticalId);
								}
						});
					}
					$scope.getVerticalMemberData($scope.user, $scope.verticalId);
				};
				WebServiceFactory.getVerticalApproverData().then(getVerticalApproverData);
			};
			
			$scope.getDUHCustomerVerticalMapping = function(verticalId) {
				var getDUHCustomerVerticalMapping = function(response) {
					$scope.customer = response.data;
					$scope.customerVerticalMapping = response.data;
				}
				WebServiceFactory.getDUHCustomerVerticalMapping(verticalId).then(getDUHCustomerVerticalMapping);
			};
	*/
	
	  /* $scope.getDelicgateUserAccess = function(user) {
			var getDelicgateUserAccess = function (response) { 
				if(response.data != undefined) {
					$scope.deligateUser = response.data;
					$localStorage.deligateUser = $scope.deligateUser;
					console.log("the Deligtate User array is........ ");
					console.log($localStorage.deligateUser);
					console.log("the deligateUserAccess............ " + $scope.deligateUser)
				}
			}
			WebServiceFactory.getDelicgateUserAccess(user).then(getDelicgateUserAccess);
		}
	   
	   $scope.getDashboardDealData = function(roleId,dealStatusId){
			$scope.searchDeal = "";
			$scope.dealData = [];
			$scope.customerMappingForDealDetail = null;
			$scope.customerMappingForDealDetail = [];
			
			$scope.dealStatus = "Won";
			$scope.currentDealRoleId=4;
			$scope.statusIndicator = null;
			var dealStatus = $scope.currentDealStatus;
			
			
				
			if($scope.selectedCustomer != undefined){
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
					if($scope.selectedCustomer == value.customerId){
			            $scope.customerMappingForDealDetail.push({
			            "customerId" : value.customerId,
			           	"currentApproverRoleId":roleId,
			           	"currentApprovalStatus":dealStatus,
			        	"userType":userType,
			           	"statusIndicator":$scope.statusIndicator
			     	   });
					}
		        });
			}
			else{
				angular.forEach($scope.customerVerticalMapping, function (value, key) {
		            $scope.customerMappingForDealDetail.push({
		            "customerId" : value.customerId,
		           	"currentApproverRoleId":roleId,
		           	"currentApprovalStatus":dealStatus,
		        	"userType":userType,
		           	"statusIndicator":$scope.statusIndicator
		     	   });
		        });
			}
			
			var getDashboardDealData = function(response){
				$scope.dealData = null;
				console.log("getDealData");
				console.log(response);
				$scope.tempDealData = response.data;
				console.log("$scope.tempDealData......................");
				console.log($scope.tempDealData);
				if(response.status == 200){
				for(var i=0;i<$scope.tempDealData.length ;i++){
					var dealStartDate  = $scope.tempDealData[i].dealStartDate;
					var date = new Date(dealStartDate.substring(0,10));
					var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempDealData[i].dealStartDate = dealStartDD;
					
					var dealEndDate  = $scope.tempDealData[i].dealEndDate;
					var date = new Date(dealEndDate.substring(0,10));
					var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.tempDealData[i].dealEndDate = dealEndDateDD;
												
					var approvalStatus  = $scope.tempDealData[i].currentApprovalStatus;
					var statusIndicator = $scope.tempDealData[i].statusIndicator;
					
					 if($scope.statusIndicator == 'Draft' || $scope.statusIndicator =='Recycled')
					{
						$scope.isDealDeleteShow = false;
						$scope.tempDealData[i].currentApproverId="-";
						var createdDate  = $scope.tempDealData[i].createdDate;
						var date = new Date(createdDate.substring(0,10));
						var dealcreatedOnDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.tempDealData[i].createdDate = dealcreatedOnDD;
					}
					else
					{
						$scope.isDealDeleteShow = true;
						$scope.tempDealData[i].currentApproverId="-";
						var updatedDate  = $scope.tempDealData[i].updatedDate;
						var date = new Date(updatedDate.substring(0,10));
						var dealupdatedOnDD = $filter('date')(date,'dd/MM/yyyy');
						$scope.tempDealData[i].updatedDate = dealupdatedOnDD;
					}
					
					if($scope.statusIndicator == "Draft" || $scope.statusIndicator == "DRAFT" 
						||$scope.statusIndicator == "Recycled" || $scope.statusIndicator == "RECYCLED")
					{
						$scope.isDealDeleteShow = false;
					}
					
				}
				angular.forEach($scope.tempDealData,function(value,key){
					angular.forEach($scope.towerdata,function(value1,key1){
						if($scope.tempDealData[key].rpDealVersionId == $scope.towerdata[key1].dealVersionId){
							$scope.towerCount.push({
								"towerId" : $scope.towerdata[key1].dealAutoTowerId
							});
						}
						$scope.towerLength = $scope.towerCount.length;
						$scope.tempDealData[key].towerCount = $scope.towerLength;
						});
					$scope.towerCount=[];
					});
				$scope.dealData = $scope.tempDealData;
				angular.forEach($scope.dealData, function(value,key) {
					if($scope.dealData[key].isNewDeal == 2) {
						var isNewDeal = value.isNewDeal;
						$scope.dealData =  $filter('orderBy')($scope.dealData, 'isNewDeal');
					}
				});
				}
			}
			WebServiceFactory.getDashboardDealData($scope.customerMappingForDealDetail).then(getDashboardDealData);
		}*/
}]);