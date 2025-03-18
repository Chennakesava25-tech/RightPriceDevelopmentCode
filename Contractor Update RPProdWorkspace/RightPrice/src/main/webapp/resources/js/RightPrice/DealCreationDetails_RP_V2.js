"use strict";

		app.controller("DealCreationDetailsController", ['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory,$localStorage, $sessionStorage,$index){
			var contextPath = "/RightPrice-DAS";
			$scope.AttachementData=[];
			$scope.attachmentFileData = [];
			$scope.versionDetail = [];
			$scope.UploadHidden = true; 
			$scope.isDisabled = false; // disabled the field for deal status=close
			$scope.isVersionAvailable = true;
			$scope.isCopyDisabled=true;
			$scope.isDealApproveOrPendngAPRVL = true;
			var newFor=0;
			$scope.AttachmentHidden = true; 
			var userType = sessionStorage.getItem('userType');
			$scope.frmDeal = {};
			$scope.crmDealDetailArrays = [];
			
			
			 
			$scope.ShowHideUpload = function () {
			     $scope.UploadHidden = $scope.UploadHidden ? false : true;
			 };
			 $scope.ShowHideAttachment = function () {
			     $scope.AttachmentHidden = $scope.AttachmentHidden ? false : true;
			 };
			 
			// $window.onload=emptyField;
			 		
			 var temp;	
			$window.onload = function() 
			{	
				
				if(sessionStorage.getItem('dashboardData') == 1){
				var CheckUserAccess = function(resp){
				
					if(resp.status == 205)	
					{

						BootstrapDialog.show({
				        	title : 'T&M Deal Creation',
				        	type : BootstrapDialog.TYPE_DANGER,
				        	message : 'You do not have access to the required Customer.<br> Kindly raise a ticket in PISA Portal or send an email to rightprice2.0_support@eviden.com. ',
				        	closable : false,
				        	buttons : [{
				        		label : 'OK',
				        		action : function(dialogRef) {
				        			dialogRef.close();
									sessionStorage.removeItem('accountId');
									sessionStorage.removeItem('crmDealId');
									sessionStorage.removeItem('dashboardData');
				        			window.close();
				        		}
				        	}]
				        });
					} 
					else{
					$scope.frmDeal.customerNameModel = parseInt(sessionStorage.getItem('accountId'));
					//$scope.frmDeal.DealModel = sessionStorage.getItem('crmDealId');

					var getDealsFrmCrmStages = function(response) {			
						    $scope.crmDealDetail = response.data;
						    console.log("Deal Id's");
						    console.log($scope.crmDealDetail);
						    $sessionStorage.crmDealDetaild_data = $scope.crmDealDetail;
						    var custmIdforVertical=$scope.crmDealDetail[0].customerId;
						    $sessionStorage.rpCustomerId = customerId;
						    $scope.getCustomerVerticalFP(custmIdforVertical);
						    $scope.getVersion(sessionStorage.getItem('crmDealId'));
						   
					};
					WebServiceFactory.getDealsFrmCrmStages($scope.frmDeal.customerNameModel,2).then(getDealsFrmCrmStages);

					}
				}
				WebServiceFactory.CheckUserAccess(parseInt(sessionStorage.getItem('accountId'))).then(CheckUserAccess)
				
			}
			
			else{
				temp=0;
				var crmDealId = $localStorage.crmDealId;
				var customerId= $localStorage.customerId;
				
				
					temp=10;
					//$scope.getPrevDataDeal_crmDeal();
					$scope.putDealData();
					$scope.searchFile(crmDealId);
					//$scope.getVersiondata(crmDealId);
					sessionStorage.removeItem('accountId');
					sessionStorage.removeItem('crmDealId');
					sessionStorage.removeItem('dashboardData');
				
				//$scope.onloadCoun_City();
			}			
			};

			
			$scope.createVersionFromDB = function(){

				$scope.update= true;

				if(($localStorage.accountId != null) && ($localStorage.crmDealId  != null))
				{
				
					$scope.update= false;
					//check if user has access to the customer to create version
					if(userType == 'Delivery') {
						$scope.isCopyDisabled = false;
						$scope.isVersionAvailable = false;
						BootstrapDialog.show({
							title: 'Version Name',
							type : BootstrapDialog.TYPE_PRIMARY,
							// closable: false,
							message: $('<input type="text" class="form-control" ng-model="txtVersion" id="txtVersion" placeholder="Please enter version here">'),
							buttons: [{
								label: 'Continue',
								cssClass: 'btn-primary',
								action: function(dialogRef) {
									var approverId = angular.element(document.getElementById('txtVersion'));
									var version = approverId[0].value;
									if ($.trim(version) == '') {
										return false;
									}
									else
									{
										$scope.frmDeal.VersionNameModel = version;
										$scope.$apply();
										
										
										if($scope.frmDeal.VersionNameModel.length > 700)
										{
											var commentLen = $scope.frmDeal.VersionNameModel.length;
											var SkipChars = commentLen - 700;					
											$scope.frmDeal.VersionNameModel = $scope.frmDeal.VersionNameModel.substring(SkipChars, commentLen);
											$scope.$apply();
										}
									}
									dialogRef.close();
									$scope.insertDealData();
								}
							}, {
								label: 'Close',
								cssClass: 'btn-primary',
								action: function(dialog) {
									dialog.close();
								}
							}]
						});
					}
					else {
						BootstrapDialog.show({
				        	title : 'T&M Deal Creation',
				        	type : BootstrapDialog.TYPE_DANGER,
				        	message : 'You are Not authorized to create an version.',
				        	closable : false,
				        	buttons : [{
				        		label : 'OK',
				        		action : function(dialogRef) {
				        			dialogRef.close();
				        			$localStorage.crmDealId = $scope.frmDeal.versionModel;
				        		//	window.location = "MasterCountryForex";
				        		}
				        	}]
				        });
					}
				}
					else
					{
						BootstrapDialog.show({
					        title: '',
					        type : BootstrapDialog.TYPE_PRIMARY,
					      
					        message: $('<input type="text" class="form-control" ng-model="txtVersion" id="txtVersion" placeholder="Please select customer w.r.t. Deal id">'),
					        buttons: [ {
							            label: 'Close',
							            cssClass: 'btn-primary',
							            action: function(dialog) {
						                dialog.close();
						            }
						        }]
							});
					}
				
			}
			
			
			$scope.Next = function()
		    {
				
				
				
				
				if(($localStorage.CurrentApprovalStatus == null || $localStorage.CurrentApprovalStatus == 1 ||
						$localStorage.CurrentApprovalStatus == 4 ) && $localStorage.pageTracker==0)
					{	BootstrapDialog.show({
						title : 'T&M Deal Creation - Details',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please fill the details and save to proceed ahead",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(
									dialogRef) {
								dialogRef.close();
							}
						} ]
					});	
						
					}
				else{
					$sessionStorage.Industry_ID= $scope.frmDeal.ddlIndustryModel;
					$localStorage.crmDealId = $scope.frmDeal.DealModel;
					$sessionStorage.crmDealId = $scope.frmDeal.DealModel;
					$localStorage.local_Industry_ID = $scope.frmDeal.ddlIndustryModel;
					window.location='TMDealCreationRateCardAndProjectDetails';
				}
				
		        
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
				$scope.crmDealDetaild_data=null;
				$scope.frmDeal.DealModel = null;
				$scope.versionDetail = null;
				$scope.frmDeal.versionModel = null;
				$scope.frmDeal.txtOldDealId = null;
				$scope.frmDeal.txtProjectId = null;
			}
			
			
			
		 /*$scope.onloadCoun_City = function()
			{
				var get_onloadCoun_City = function(response)
				{
					$scope.cityList=[];
					if(response.data[0] != undefined) {
						$scope.cityList.push({
							"countryId":response.data[0].countryId,
							"countryName": response.data[0].countryName,
							"cityId":response.data[0].cityId,
							"cityName":response.data[0].cityName,
							"utilization": response.data[0].utilization
						});
						$sessionStorage.citiId2staffing=response.data[0].cityId;
						//$scope.cityList=response.data;
						$sessionStorage.CityList=$scope.cityList;
					}
				}
				WebServiceFactory.get_onloadCoun_City($localStorage.crmDealId).then(get_onloadCoun_City);					
			};
			*/
			
			
$scope.dealTypeModel = "T & M";

var manualDealAttachment, attachment1, attachment2;
//$scope.dealType = [{ name: "Fixed Price", id: 1 }, { name: "T & M", id: 2 }];
$scope.fpType = [{ name: "Development", id: 1 }, { name: "Maintenance", id: 2 }];
$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }];
$scope.isAgileBased = [{ name: "Yes", id: 1 }, { name: "No", id: 0 }];
$scope.isSyntelOnsiteFacilityUsed = [{ name: "Client", id: 1 }, { name: "Syntel", id: 0 }];
$scope.riskCategoryId = [{ name: "A", id: 1 }, { name: "B", id: 2 }, { name: "C", id: 3 }, { name: "D", id: 4 }];
$scope.industryType = [{ name: "IT", id: 1 }, { name: "KPO", id: 0 }, { name: "JV KPO", id: 2 }];

$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
$scope.dealTMSynergy  = [{ name: "Cross sell", id: "Cross_sell" }, { name: "Normal", id: "Normal" }];



	if(userType == 'GFT' || userType == 'CEO' || userType == 'CDO'|| userType=='RiskManagers' 
		|| userType == 'Audit'|| userType == 'LEVELl1User' || userType == 'LEVELl2User')
	{
			var getGFTCustomer = function(response) {
				console.log("customer response");
		 		//console.log(response.data);
				$scope.customer = response.data;
				console.log("customer::::::::::::::::::");
				console.log(response.data);
				$sessionStorage.customerData = response.data;
			};
			WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
		}
	else if(userType == 'BHU' || userType == 'DUH')
	{
			//Local storage data
		$scope.verticalId="";
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
					}
				});
			}
		};
		WebServiceFactory.getVerticalApproverData().then(getVerticalApproverData);
			/*var getCustomerByVerticalGroupId = function(response) {				
				$scope.customer = response.data;
				$sessionStorage.customerData = response.data;
				
			};
			WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);*/
		}
	else
 	{
 		var getCustomerForUser = function(response) { 			
 			$scope.customer = response.data;
 			$sessionStorage.customerData = response.data;
 			
 		};
 		WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
 	}

 var getCountryList = function(response) {		
		    $scope.country = response.data;
		    console.log(response);
		    console.log($scope.country);
		};		
 WebServiceFactory.getCountryList().then(getCountryList);
 
 
 var getLobDetails = function(response) {
		console.log(response);
		$scope.masterLob = response.data;
		
};
WebServiceFactory.getLobDetails().then(getLobDetails);
 
 //get deals on customer change
 $scope.getDealDetails = function(customerId){	
	 $scope.clearData();
	 var dealTypeForTM=2;
	 if(customerId != undefined) 
	 {
		 var getDealsFrmCrmStages = function(response) {			
		    $scope.crmDealDetail = response.data;
		    console.log("Deal Id's");
		    console.log($scope.crmDealDetail);
		    $sessionStorage.crmDealDetaild_data = $scope.crmDealDetail;
		    var custmIdforVertical=$scope.crmDealDetail[0].customerId;
		    $sessionStorage.rpCustomerId = customerId;
		    $scope.getCustomerVerticalFP(custmIdforVertical);
		    
		    /*angular.forEach($sessionStorage.customerData, function(value,key) {
				if($sessionStorage.customerData[key].customerId == customerId) {
					$sessionStorage.verticalId = $sessionStorage.customerData[key].verticalId;
					$scope.customerVerticalMapId=$sessionStorage.customerData[key].customerVerticalMapId;

				} 
			 });*/
		    
		    
		   
		};
		WebServiceFactory.getDealsFrmCrmStages(customerId,dealTypeForTM).then(getDealsFrmCrmStages);
		
		
		
		
		var getDealsForCust = function(response) {
			 console.log("Deal Id's");
			 //alert("frm stages");
			 console.log(response);
			 $scope.crmDealDetailCust = response.data;
			 
		 };
		 WebServiceFactory.getDealsForCust(customerId,dealTypeForTM).then(getDealsForCust);
	 }
};


//get All deals on customer change
 $scope.getAllDealDetails = function(customerId){	
	 
	 $scope.clearData();
	 var dealTypeForFM=1;
	 if(customerId != undefined) 
	 {
		 var getAllDealsFrmCrmStages = function(response) {			
		    $scope.crmDealDetail = response.data;
		    console.log("Deal Id's");
		    console.log($scope.crmDealDetail);
		    $sessionStorage.crmDealDetaild_data = $scope.crmDealDetail;
		    var custmIdforVertical=$scope.crmDealDetail[0].customerId;
		    $sessionStorage.rpCustomerId = customerId;
		    $scope.getCustomerVerticalFP(custmIdforVertical);
		    $scope.searchFile(crmDealId);

		    
		    /*angular.forEach($sessionStorage.customerData, function(value,key) {
				if($sessionStorage.customerData[key].customerId == customerId) {
					$sessionStorage.verticalId = $sessionStorage.customerData[key].verticalId;
					$scope.customerVerticalMapId=$sessionStorage.customerData[key].customerVerticalMapId;

				} 
			 });*/
		    
		    
		   
		};
		WebServiceFactory.getAllDealsFrmCrmStages(customerId,dealTypeForFM).then(getAllDealsFrmCrmStages);
		
		
		
		
		var getAllDealsForCust = function(response) {
			 console.log("Deal Id's");
			 //alert("frm stages");
			 console.log(response);
			 $scope.crmDealDetailCust = response.data;
			 
		 };
		 WebServiceFactory.getAllDealsForCust(customerId,dealTypeForFM).then(getAllDealsForCust);
	 }
};


//get deals on customer change a
$scope.getCities = function(countryId){	
	
	if(countryId != undefined)
	{
	 var getCityList = function(response) {			
		    $scope.city = response.data;		    
		    angular.forEach($scope.city, function(value, key) {
				 if($scope.city[key].cityId == $sessionStorage.ssnCity_id)
					 {
					 	//$scope.frmDeal.cityNameModel=$scope.city[key].cityName;
					 	$scope.frmDeal.cityNameModel=$scope.city[key].cityId;
					 
					 }
				});
		    
		}
	WebServiceFactory.getCityData(countryId).then(getCityList); //WebServiceFactory.getCity(countryId).then(getCityList);
	
	}	
}













//put version detail on change of version and get risk answers
$scope.putVersion = function(crmDealId){	
	temp=0;
	console.log("inside putVersion---"+crmDealId);	
	$scope.clearData();

	$localStorage.crmDealId = crmDealId;
	$scope.onloadCoun_City();
	if(crmDealId!=undefined)
	{
		var getRiskAnswers = function(response) {
			
			console.log("get answers");
			console.log(response);
			$scope.getRiskAnswers=response.data;
			if(response.status == 200){
				
				$scope.frmDeal.riskAns1Model = response.data[0].riskAnswer;
				$scope.frmDeal.riskAns2Model = response.data[1].riskAnswer;
				$scope.frmDeal.riskAns3Model = response.data[2].riskAnswer;
				$scope.frmDeal.riskAns4Model = response.data[3].riskAnswer;
				$scope.frmDeal.riskAns5Model = response.data[4].riskAnswer;
				$scope.frmDeal.riskAns6Model = response.data[5].riskAnswer;
				$scope.frmDeal.riskAns7Model = response.data[6].riskAnswer;
				$scope.frmDeal.riskAns8Model = response.data[7].riskAnswer;
				
				if(response.data[8].riskAnswer == "true"){
					$scope.frmDeal.riskAns9Model = true;
				}
				else
				{
					$scope.frmDeal.riskAns9Model = false;
				}
			}
			
		};
		WebServiceFactory.getRiskAnswers(crmDealId).then(getRiskAnswers);
		
		var getLatestVersionData=function(response)
		{	
			$scope.latestVersionData=response.data;
			$scope.frmDeal.oldDealIdModel = $scope.latestVersionData[0][17];	    	
	    	$scope.frmDeal.projectIdModel = $scope.latestVersionData[0][19];
	    	

	    	if($scope.latestVersionData[0][35]!=null||undefined){
				$scope.frmDeal.securitymodel=$scope.latestVersionData[0][35];
			}
			else{
				$scope.frmDeal.securitymodel="";
			}
			if($scope.latestVersionData[0][34]!=null||undefined){
				$scope.frmDeal.solutionModel=$scope.latestVersionData[0][34];
			}
			else{
				$scope.frmDeal.solutionModel="";
			}
			if($scope.latestVersionData[0][36]!=null||undefined){
				$scope.frmDeal.synbotsModel=$scope.latestVersionData[0][36];
			}
			else{
				$scope.frmDeal.synbotsModel="";
			}
			if($scope.latestVersionData[0][33]!=null||undefined){
				
				$scope.frmDeal.synergyModel=$scope.latestVersionData[0][33].trim();
			}
			else{
				$scope.frmDeal.synergyModel="";
			}
		};	
		WebServiceFactory.getPrevDataDeal_Version(crmDealId).then(getLatestVersionData);
		
	}
			
	$scope.getContry_City(crmDealId);	

	//a
	var selectedRPVersionId = crmDealId;
	var rpVersionLen=0;
	rpVersionLen = $scope.versionDetail.length;
	//alert('crmdlLen='+crmdlLen);
	  var arr = [];
	  var y=0;
	  for(var x=0; x<rpVersionLen ;x++)
	  {
		  var rpVID= $scope.versionDetail[x].crmDealId;		
		  arr.push(rpVID);
		
		  if(selectedRPVersionId == arr[x])
			  {		  
			  	 y = arr.indexOf(arr[x]);	
			  	 
			  if(($scope.versionDetail[y].currentApprovalStatus == null || $scope.versionDetail[y].currentApprovalStatus == 1 || $scope.versionDetail[y].currentApprovalStatus == 4) && userType == 'Delivery')
				{			
			   	  $scope.isVersionAvailable = false;  
			   	  $scope.isCopyDisabled = false;  
				}
				else
				{
					$scope.isVersionAvailable = true;
					$scope.isCopyDisabled=true;
				}
			  	 
			  	 break;
			  }			  
		 
	  }
	//end a	
	  
	if($scope.frmDeal.versionModel != undefined)
	{
		if($scope.versionDetail[y].fpProjectTypeId== 1)
		{ 
			$scope.frmDeal.fpTypeModel = $scope.fpType[0].id;
		}
	else
		{
			$scope.frmDeal.fpTypeModel = $scope.fpType[1].id;
		}
	
		if($scope.versionDetail[y].isNewDeal== 1) // if($scope.versionDetail[y].newDeal== true)
		{  
			$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[0].id;	
			
			$scope.hideOldDealId = true;		
    		$scope.hideOldProjectlId = true;
		}
	else{
			$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[1].id;			
			$scope.findOldDealId();
			$scope.hideOldDealId = false;	
			$scope.hideOldProjectlId = false;
		}
	
	if($scope.frmDeal.versionModel.agileProject == true)
		{
			$scope.frmDeal.isAgileBasedModel = $scope.isAgileBased[0].id;
		}
	else
		{
			$scope.frmDeal.isAgileBasedModel = $scope.isAgileBased[1].id;
		}
	if(temp!=10)
		{
		
			if($scope.versionDetail[0].syntelOnsieFacilityUsed == true)
			{  
				$scope.frmDeal.onsiteFacilityModel = $scope.isSyntelOnsiteFacilityUsed[0].id;
			}
			else
			{
				$scope.frmDeal.onsiteFacilityModel = $scope.isSyntelOnsiteFacilityUsed[1].id;
			}
			$scope.frmDeal.isBizopsModel = $scope.versionDetail[0].bizOpsInvolved;//$scope.frmDeal.versionModel.bizOpsInvolved;
			$scope.frmDeal.riskCategoryModel =$scope.versionDetail[0].riskCategoryId; // $scope.frmDeal.versionModel.riskCategoryId;
			$scope.frmDeal.isIncludeInHeadCount = $scope.versionDetail[0].hcIncluded;// $scope.frmDeal.versionModel.hcIncluded;
			$scope.frmDeal.onsiteHoursModel =$scope.versionDetail[0].onsiteHours; //$scope.frmDeal.versionModel.onsiteHours;
			$scope.frmDeal.offShoreHoursModel = $scope.versionDetail[0].offShoreHours;//$scope.frmDeal.versionModel.offShoreHours;
			//$scope.frmDeal.ddlLOBModel = $scope.versionDetail[0].projectLobId;//$scope.frmDeal.versionModel.projectLobId;
			$scope.frmDeal.penaltyPercent = $scope.versionDetail[0].penaltyPercentage; //$scope.frmDeal.versionModel.penaltyPercentage;
		
		
			
			if($scope.versionDetail[0].isManualDeal == 1)
			{ 
				$scope.frmDeal.isManualDeal = true;
			}
			else
			{
				$scope.frmDeal.isManualDeal = false;
			}
		}

	  var selectedRPVId = crmDealId;
	  var rpverLen = 0;
	  rpverLen = $scope.versionDetail.length;	
	  var arr = [];
	  var b=0;
	  for(var a=0; a<rpverLen ;a++)
	  {  
		  var tempVid= $scope.versionDetail[a].crmDealId;		
	 	  arr.push(tempVid);		
		  if(selectedRPVId == arr[a])
			  {		  
			  	 b = arr.indexOf(arr[a]);
			  	 break;
			  }
	  }	  
	   $scope.searchFile(crmDealId);
		if(temp != 10)
		{
		 $scope.putDealData();
		 $scope.searchFile(crmDealId);
		}	
	}
	
	
}

$scope.putDealData = function()
	{
	
	
	var putDealData = function(response){
	var selectedValueCRM = $localStorage.DealModel;
	var crmdlLen=0;
	$scope.crmDealDetailArrays= response.data;
	//alert($localStorage.DealModel);
	$scope.frmDeal.DealModel=$localStorage.DealModel;
	crmdlLen = $scope.crmDealDetailArrays.length;	
	var arr = [];
	var j=0;
	/*for(var i=0; i<crmdlLen ;i++)
	  {
		  var crmdlId= $scope.crmDealDetailArrays[i].crmDealId;		
		  arr.push(crmdlId);
		 	  
		  if(selectedValueCRM == arr[i])
		  {		  
		  	 j = arr.indexOf(arr[i]);
		  
		  	 break;
		  }	
	  }	*/
	$scope.frmDeal.customerNameModel=$scope.crmDealDetailArrays[0].customerId;
	$scope.getAllDealDetails($scope.crmDealDetailArrays[0].customerId);
	
		$scope.frmDeal.DealModel = $scope.crmDealDetailArrays[0].dealId;
	$scope.frmDeal.descriptionModel= $scope.crmDealDetailArrays[0].dealDescription;		
		$scope.frmDeal.salesSpocModel =  $scope.crmDealDetailArrays[0].salesSpoc; 
		
		    	//$scope.putDealData($scope.frmDeal.DealModel);
		    	
		$scope.frmDeal.dealStatusModel = $scope.crmDealDetailArrays[0].dealStatus;
		
		
		var dateStart =  $scope.crmDealDetailArrays[0].dealStartDate; //$scope.frmDeal.DealModel.dealStartDate ;	
		var date = new Date(dateStart.substring(0,10));
		$scope.frmDeal.dealStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');	
		$localStorage.startDate=dateStart; 
		var dateEnd = $scope.crmDealDetailArrays[0].dealEndDate;//$scope.frmDeal.DealModel.dealEndDate ;		
		var date = new Date(dateEnd.substring(0,10));
		$scope.frmDeal.dealEndDateModel = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy');

		console.log($scope.calApplicableYears());
	//	$scope.frmDeal.durationModel = 
		$scope.frmDeal.countryNameModel = $scope.crmDealDetailArrays[0].country;
		$scope.getCities($scope.frmDeal.countryNameModel);
		$scope.frmDeal.cityNameModel =  parseInt($scope.crmDealDetailArrays[0].city);
		console.log($scope.frmDeal.cityNameModel);
		$scope.frmDeal.currencyModel = $scope.crmDealDetailArrays[0].currency;//$scope.frmDeal.DealModel.currency.currencyCode;
	//	$scope.frmDeal.stageModel =$scope.crmDealDetailArrays[0].stage.codeName;// $scope.frmDeal.DealModel.stage.description;
		$scope.frmDeal.dealTCVModel = $scope.crmDealDetailArrays[0].dealTCV; //$scope.frmDeal.DealModel.revenue;
		
		$scope.frmDeal.ddlIndustryModel = $scope.crmDealDetailArrays[0].industry;
		$scope.frmDeal.customerDescription = $scope.crmDealDetailArrays[0].customerDescription;
		$scope.frmDeal.expectedonsiteresource = $scope.crmDealDetailArrays[0].onsitePercentage;
		$scope.frmDeal.expectedoffshoreresource = $scope.crmDealDetailArrays[0].offshorePercentage;
		$scope.frmDeal.revenueModel = $scope.crmDealDetailArrays[0].revenue;
		$scope.frmDeal.costModel = $scope.crmDealDetailArrays[0].cost;
		$scope.frmDeal.marginPercentage = $scope.crmDealDetailArrays[0].grossMarginPercentage;
		$scope.frmDeal.AgileCostModel = $scope.crmDealDetailArrays[0].projectSpecificSGAIncludingAgileCost;
		$scope.frmDeal.OmPercentage = $scope.crmDealDetailArrays[0].omPercentage;
		$scope.frmDeal.VolumeDiscountModel = $scope.crmDealDetailArrays[0].volumeDiscountPercentage;
		$scope.frmDeal.PenaltyModel = $scope.crmDealDetailArrays[0].penaltyPercentage;
		$scope.frmDeal.omPercentageDiscountModel = $scope.crmDealDetailArrays[0].omPercentageAfterVolumeDiscount;
		switch ($scope.frmDeal.ddlIndustryModel) {
                   case "1":
                   $scope.frmDeal.ddlIndustryModel = "IT";
                   break;
                   case "0":
                   $scope.frmDeal.ddlIndustryModel = "KPO";
                   break;
                   case "2":
                   $scope.frmDeal.ddlIndustryModel = "JV KPO";
                   break;
                   default:
                    $scope.frmDeal.ddlIndustryModel = "Others";
}
switch ($scope.frmDeal.dealStatusModel) {
	               case 0:
                   $scope.frmDeal.dealStatusModel = "Open";
                   break;
                   case 1:
                   $scope.frmDeal.dealStatusModel = "Won";
                   break;
                   case 2:
                   $scope.frmDeal.dealStatusModel = "Lost";
                   break;
                   default:
                    $scope.frmDeal.dealStatusModel = "Others";
}
		//$scope.getFileData($scope.crmDealDetailArrays[j].crmDealId);
		 //$scope.frmDeal.DealModel.industry;		
		var dealStatus=  $scope.crmDealDetailArrays[0].dealStatus.description; //$scope.frmDeal.DealModel.dealStatus.description;
		
		$scope.dealStartDateModel=$scope.crmDealDetailArrays[0].dealStartDate;

		$scope.dealEndDateModel=$scope.crmDealDetailArrays[0].dealEndDate;
		$scope.durationModel=$scope.crmDealDetailArrays[0].duration;
		
		$scope.searchFile($localStorage.DealModel);

		
	}
	WebServiceFactory.getDealDetailsFor_Selection($localStorage.DealModel).then(putDealData)
	
};


$scope.saveDealData = function(frmDeal){
	console.log('inside save deal data details.......................')
	var date= $scope.frmDeal.dealStartDateModel; 	
	var d=new Date(date.split("/").reverse().join("-"));
	var dd=d.getDate();
	var mm=d.getMonth()+1;
	var yy=d.getFullYear();
	var deal_start_dt=yy+"/"+mm+"/"+dd;	
	var date2= $scope.frmDeal.dealEndDateModel; 	
	var d=new Date(date2.split("/").reverse().join("-"));
	var dd=d.getDate();
	var mm=d.getMonth()+1;
	var yy=d.getFullYear();
	var deal_end_dt=yy+"/"+mm+"/"+dd;
	
	var markers = {
			"crmDealId":$scope.frmDeal.DealModel,                       //.crmDealId,
			"customerId" : $scope.frmDeal.customerNameModel,            //customerId,
			"customerDescription":$scope.frmDeal.frmDeal.customerDescription,
			"dealDescription":$scope.frmDeal.frmDeal.descriptionModel,
			
			"dealStartDate":deal_start_dt,
			"dealEndDate":deal_end_dt,
			"dealTcv":$scope.frmDeal.dealTCVModel,
			
			"duration":$scope.frmDeal.durationModel,
			"currency":$scope.frmDeal.currencyModel,
		
			"dealStatus":$scope.frmDeal.dealStatusModel,
			"salesSpoc":$scope.frmDeal.salesSpocModel,
			
			
			"industry":$scope.frmDeal.ddlIndustryModel,	
			
									
			"country": $scope.frmDeal.countryNameModel,
			"city":$scope.frmDeal.cityNameModel,
			
			
			"onsitePercentage": $scope.frmDeal.expectedonsiteresource,
			"offshorePercentage":$scope.frmDeal.expectedoffshoreresource,
			
			
			
			"revenue": $scope.frmDeal.revenueModel,
			"directCost":$scope.frmDeal.frmDeal.costModel,
			
			
			
			"grossMarginPercentage": $scope.frmDeal.marginPercentage,
			"projectSpecificSGAIncludingAgileCost":$scope.frmDeal.frmDeal.AgileCostModel,
			
			
			"omPercentage": $scope.frmDeal.OmPercentage,
			"volumeDiscountPercentage":$scope.frmDeal.VolumeDiscountModel,
			
			"penaltyPercentage": $scope.frmDeal.PenaltyModel,
			"omPercentageAfterVolumeDiscount":$scope.omPercentageDiscountModel,
			
	};

	var formData = new FormData();
	for (var i = 0; i < fileTest.length; i++) {
		var fileName = "file"+i.toString();
		console.log("fileName : "  + fileName);
		var file = $('input[type="file"]').get(i).files[0];
		if(file){
			formData.append('file',file,file.name);
			formData.append('name',name[i]);
			
		}
	}
	
	
	
	formData.append('jsonData', angular.toJson(markers));
	var saveDealData = function(response) {
		console.log("save deal data response"+response.data);
	
		if(response.status == 200){
			BootstrapDialog.show({
	        	title : 'Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Data inserted SucessFully',
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
	        	title : 'Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Currently we are facing technical issue. Please try again later.',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			window.location.reload();
	        			
	        		}
	        	}]
	        });
		}
	}
	WebServiceFactory.saveDealData(formData).then(saveDealData);
	
	/*$scope.insertDlCurrUTI();
	$scope.insertDlLoc();	*/
}

$scope.clearData = function(){
	$scope.frmDeal.descriptionModel="";
	$scope.frmDeal.salesSpocModel ="";
	$scope.frmDeal.dealStatusModel = "";
	$scope.frmDeal.dealPriorityModel = "";
	
	$scope.frmDeal.dealStartDateModel = "";
	$scope.frmDeal.dealEndDateModel = "";
	$scope.frmDeal.durationModel = "";
	$scope.frmDeal.currencyModel = "";
	$scope.frmDeal.percentageCloseModel = "";
	$scope.frmDeal.stageModel = "";
	$scope.frmDeal.dealTCVModel = "";
	$scope.frmDeal.customerDescription="";
	$scope.frmDeal.penaltyPercent = "";
	$scope.frmDeal.isManualDeal = false;
	$scope.frmDeal.expectedonsiteresource="";
	$scope.frmDeal.expectedoffshoreresource="";
	$scope.frmDeal.revenueModel="";
	$scope.frmDeal.costModel="";
	$scope.frmDeal.marginPercentage="";
	$scope.frmDeal.AgileCostModel="";
	$scope.frmDeal.OmPercentage="";
	$scope.frmDeal.VolumeDiscountModel="";
	$scope.frmDeal.PenaltyModel="";
	$scope.frmDeal.omPercentageDiscountModel="";
	/*$("#manualDealAttachment").hide();
	$("#attachment1").hide();
	$("#attachment2").hide();*/
	$scope.AttachementData=[];
	
	$scope.frmDeal.onsiteHoursModel = "";
	$scope.frmDeal.offShoreHoursModel = "";
	
//	$scope.frmDeal.dealTypeModel = null;
	$scope.frmDeal.fpTypeModel = null;
	
	$scope.frmDeal.countryNameModel = null;
	$scope.frmDeal.cityNameModel = null;
	
	$scope.frmDeal.dealNewOrRenewalModel = null;
	$scope.frmDeal.isAgileBasedModel = null;
	$scope.frmDeal.onsiteFacilityModel = null;
	$scope.frmDeal.riskCategoryModel = null;
	$scope.frmDeal.ddlIndustryModel = null;
	$scope.frmDeal.ddlLOBModel = null;
	
//	$scope.dealType = [{ name: "Fixed Price", id: 1 }, { name: "T & M", id: 2 }];
	$scope.fpType = [{ name: "Development", id: 1 }, { name: "Maintenance", id: 2 }];
	$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }];
	$scope.isAgileBased = [{ name: "Yes", id: 1 }, { name: "No", id: 0 }];
	$scope.isSyntelOnsiteFacilityUsed = [{ name: "Client", id: 1 }, { name: "Syntel", id: 0 }];
	$scope.riskCategoryId = [{ name: "A", id: 1 }, { name: "B", id: 2 }, { name: "C", id: 3 }, { name: "D", id: 4 }];
	$scope.industryType = [{ name: "IT", id: 1 }, { name: "KPO", id: 0 },{ name: "JV KPO", id: 2 }];
	$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
	$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
	$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
	$scope.dealTMSynergy  = [{ name: "Cross sell", id: "Cross_sell" }, { name: "Normal", id: "Normal" }];

	var getLobDetails = function(response) {
		console.log(response);
		$scope.masterLob = response.data;
   };
   WebServiceFactory.getLobDetails().then(getLobDetails);
	
	$scope.frmDeal.oldDealIdModel = "";
	$scope.frmDeal.projectIdModel = "";
	$scope.frmDeal.isBizopsModel = false;
	$scope.frmDeal.isIncludeInHeadCount = false;
	
	$scope.frmDeal.riskAns1Model = "";
	$scope.frmDeal.riskAns2Model = "";
	$scope.frmDeal.riskAns3Model = "";
	$scope.frmDeal.riskAns4Model = "";
	$scope.frmDeal.riskAns5Model = "";
	$scope.frmDeal.riskAns6Model = "";
	$scope.frmDeal.riskAns7Model = "";
	$scope.frmDeal.riskAns8Model = "";
	$scope.frmDeal.riskAns9Model = false;
	$scope.frmDeal.synergyModel = null;
	$scope.frmDeal.solutionModel = null;
	$scope.frmDeal.securitymodel = null;
	$scope.frmDeal.synbotsModel = null;
}






$scope.update=false;
$scope.checkUpdateDealData = function(frmDeal)
{
	
	$scope.updateDealData();
	
}

$scope.updateDealData = function(){
	
	console.log('inside save deal data details.......................')
	var date= $scope.frmDeal.dealStartDateModel; 	
	var d=new Date(date.split("/").reverse().join("-"));
	var dd=d.getDate();
	var mm=d.getMonth()+1;
	var yy=d.getFullYear();
	var deal_start_dt=yy+"/"+mm+"/"+dd;	
	var date2= $scope.frmDeal.dealEndDateModel; 	
	var d=new Date(date2.split("/").reverse().join("-"));
	var dd=d.getDate();
	var mm=d.getMonth()+1;
	var yy=d.getFullYear();
	var deal_end_dt=yy+"/"+mm+"/"+dd;
	
	var dealStatus=$scope.frmDeal.dealStatusModel;
	var industry=$scope.frmDeal.ddlIndustryModel;
	
	switch (dealStatus) {
  case "Won":
    dealStatus = 1;
    break;
  case "Lost":
    dealStatus = 2;
    break;
    case "Open":
    dealStatus = 0;
    break;
    default:
    dealStatus = 0;
}


	switch (industry) {
	case "KPO":
	industry = 0;
	break;
	case "IT":
	industry = 1;
	break;
	case "JV KPO":
	industry = 2;
	break;
	default:
	industry = 1;
	}



	var markers = {
			"dealId":$scope.frmDeal.DealModel,                       //.crmDealId,
			"customerId" : $scope.frmDeal.customerNameModel,            //customerId,
			"customerDescription":$scope.frmDeal.customerDescription,
			"dealDescription":$scope.frmDeal.descriptionModel,
			
			"dealStartDate":deal_start_dt,
			"dealEndDate":deal_end_dt,
			"dealTCV":$scope.frmDeal.dealTCVModel,
			
			"duration":$scope.frmDeal.durationModel,
			"currency":$scope.frmDeal.currencyModel,
		
			"dealStatus":dealStatus,
			
			
			"salesSpoc":$scope.frmDeal.salesSpocModel,
			
			
			"industry":industry,	
			
									
			"country": $scope.frmDeal.countryNameModel,
			"city":$scope.frmDeal.cityNameModel,
			
			
			"onsitePercentage": $scope.frmDeal.expectedonsiteresource,
			"offshorePercentage":$scope.frmDeal.expectedoffshoreresource,
			
			
			
			"revenue": $scope.frmDeal.revenueModel,
			"cost":$scope.frmDeal.costModel,
			
			
			
			"grossMarginPercentage": $scope.frmDeal.marginPercentage,
			"projectSpecificSGAIncludingAgileCost":$scope.frmDeal.AgileCostModel,
			
			
			"omPercentage": $scope.frmDeal.OmPercentage,
			"volumeDiscountPercentage":$scope.frmDeal.VolumeDiscountModel,
			
			"penaltyPercentage": $scope.frmDeal.PenaltyModel,
			"omPercentageAfterVolumeDiscount":$scope.frmDeal.omPercentageDiscountModel,
			
	};

	var formData = new FormData();
	/*for (var i = 0; i < fileTest.length; i++) {
		var fileName = "file"+i.toString();
		console.log("fileName : "  + fileName);
		var file = $('input[type="file"]').get(i).files[0];
		if(file){
			formData.append('file',file,file.name);
			formData.append('name',name[i]);
			
		}
	}*/
	
	console.log(markers);
	
	
	formData.append('jsonData', angular.toJson(markers));
	var saveDealData = function(response) {
		console.log("update deal data response"+response.data);
console.log(response.data);	
		if(response.status == 200){
			
			$localStorage.DealModel= response.data;
			/*alert($localStorage.DealModel);*/
			BootstrapDialog.show({
	        	title : 'Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Data inserted SucessFully',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        		window.location.reload();
	        $scope.putDealData();

	        		}
	        	}]
	        });
		}
		else{
			BootstrapDialog.show({
	        	title : 'Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Currently we are facing technical issue. Please try again later.',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			window.location.reload();
	        			
	        			
	        		}
	        	}]
	        });
		}
		console.log(formData)
	}
	WebServiceFactory.saveDealData(markers).then(saveDealData);
	
	/*$scope.insertDlCurrUTI();
	$scope.insertDlLoc();	*/
}




$scope.insertDlCurrUTI = function()
{
	
	if($localStorage.crmDealId != undefined && $scope.frmDeal.countryNameModel != undefined) 
		{
			var insertDlCurrUTI = function(response) 
			{	
				
			}
		
			var markers2 = 
			{
				//"dealVersionId":$scope.frmDeal.versionModel, //.crmDealId,
				"dealVersionId":$localStorage.crmDealId,
				"baseCountryId":$scope.frmDeal.countryNameModel
			};
			var formData2 = new FormData();
			formData2.append('jsonData', angular.toJson(markers2));
			WebServiceFactory.insertDlCurrUTI(formData2).then(insertDlCurrUTI);
		
		}
	
}

$scope.insertDlLoc = function()
{
	if( $localStorage.crmDealId != undefined && $scope.frmDeal.countryNameModel != undefined)
	{
			var insertDlLoc = function(response)
			{
			}				
			var markers3 = 
			{
				//"dealVersionId":$scope.frmDeal.versionModel, //.crmDealId,
				"dealVersionId":$localStorage.crmDealId,
				"countryId":$scope.frmDeal.countryNameModel,
				"cityId":$scope.frmDeal.cityNameModel
			};
			var formData3 = new FormData();
			formData3.append('jsonData', angular.toJson(markers3));
			WebServiceFactory.insertDlLoc(formData3).then(insertDlLoc);
		
	}
	
	
}



$scope.calApplicableYears = function(){    
	
   
            var startdateVal =  $scope.frmDeal.dealStartDateModel; 
            var enddateVal = $scope.frmDeal.dealEndDateModel; 
            var date1 = new Date($scope.formatString(startdateVal));
            var date2 = new Date($scope.formatString(enddateVal));
            var timeDiff = Math.abs(date2.getTime() - date1.getTime());
            
            $scope.dayDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));
           console.log($scope.dayDifference);
            $scope.frmDeal.durationModel = Math.round((($scope.dayDifference/365)*100))/100;
}

$scope.formatString = function(format) {
  var pieces = format.split('/'),
      year   = parseInt(pieces[2]), 
      month  = parseInt(pieces[1]), 
      day    = parseInt(pieces[0]),
      date   = new Date(year, month - 1, day);

  return date;
}

$scope.setValidation = function(RenewalID){	
	
	if(RenewalID!=undefined && RenewalID == $scope.dealNewOrRenewal[1].id)
	{
		
		$scope.isDealRenewal = true;
	}
	else
	{
		
		$scope.isDealRenewal = false;
	}	
	if(RenewalID == 1) 
	{		
		$scope.hideOldDealId = true;
		$scope.hideOldProjectlId = true;
	}
	else 
	{
		$scope.hideOldDealId = false;
		$scope.hideOldProjectlId  = false;
	}	
	
	$scope.findOldDealId();	
}

$scope.findOldDealId =function()
{
	var customerIdForOlddeal= $sessionStorage.rpCustomerId;	
	var current_DealModel = $localStorage.DealModel
	var getOldDealIdList = function(response) {						
			console.log(response);
		    $scope.oldDealId = response.data;			  
		};
	WebServiceFactory.getOldDealDetails (customerIdForOlddeal,$localStorage.startDate).then(getOldDealIdList);
}

$scope.getCustomerVerticalFP = function(custmIdforVertical) {
	var getCustomerVerticalFP = function(response) {
		console.log("Vertical details");
		$scope.currentCustVertical = response.data;
		if($scope.currentCustVertical!= undefined) {
			$scope.verticalId = $scope.currentCustVertical[0].verticalId;
			$scope.customerVerticalMapId=$scope.currentCustVertical[0].customerVerticalMapId;
			$sessionStorage.custmVerticalMapId=$scope.customerVerticalMapId;
			$sessionStorage.verticalId = $scope.verticalId; 
		}
		console.log($scope.currentCustVertical);
	};
	WebServiceFactory.getCustomerVerticalFP(custmIdforVertical).then(getCustomerVerticalFP);
};


var markers = {
		"versionId" : $localStorage.crmDealId
		};
/*var getTMAttachementData = function(response) {
	$scope.AttachementData=response.data;	
	};
	WebServiceFactory.getTMAttachementData(markers).then(getTMAttachementData);*/
	
	$scope.deleteFile = function(objectid,category){
		WebServiceFactory.changeActiveStatus(objectid);
		$window.location.reload();
	};		
	
	$scope.deleteDealFile = function(objectid, rcId) {
				WebServiceFactory.deleteMasterRCAttachment(objectid);
				for(var i = 0; i < $scope.MDAttachementData.length;i++)
					{
					if($scope.MDAttachementData[i].dealAttachmentId == objectid)
						{
						$scope.MDAttachementData.splice(i,1);						
						}
					}
				if($scope.MDAttachementData.length == 0){
					 $scope.tableHide = true;
				}

					
				
			};
			
			
	
	
	 $scope.uploadData = function(frmRPUpload){
			//alert("uploadData called");
     	  $scope.onUpload= true;
			/*if(PreSalesUploadForm.$valid){
				$scope.onUpload= false;
				//alert("inside valid");
				
			}*/
			
			$scope.uploadDealsExcel();
		};
		
		 //Uploading file for Deals
		 $scope.uploadDealsExcel = function(){
		// alert("uploadDealexcel data called");
		    	var filename = $("#fuAttachFilename");	
		    	var dealId=$scope.frmDeal.crm
		    	console.log(file);
		    	var name = "Template";
		    	var tempID = 1;	
				var file = $('input[name="fuUploadFilename"]').get(0).files[0];
				var formData = new FormData();
				formData.append('file', file);
				formData.append('name', name);
				formData.append('TempId',tempID);
				formData.append('fileDescription',$scope.frmRPUpload.fuUploadFilenameModel);
		    	console.log(file);
		    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadDealsFile";
	            $http.post(uploadUrl, formData, {
	                transformRequest: angular.identity,
	                headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
	            }).
			    then(function(data) {
			    	var obj = JSON.stringify(data);
					var json = JSON.parse(obj);
					var customMessage = data.data;
			    	if(data.status == 200)	
					{
			    		$sessionStorage.id = data.data;
			    		modal({
							type: 'primary', //Type of Modal Box (alert | confirm | prompt | success | warning | error | info | inverted | primary)
							title: 'Message', //Modal Title
							text: "File Uploaded Successfully.", //Modal HTML Content
							size: 'normal', //Modal Size (normal | large | small)
							buttons: [{
								text: 'OK', //Button Text
								val: 'ok', //Button Value
								eKey: true, //Enter Keypress
								addClass: 'btn-light-blue', //Button Classes (btn-large | btn-small | btn-green | btn-light-green | btn-purple | btn-orange | btn-pink | btn-turquoise | btn-blue | btn-light-blue | btn-light-red | btn-red | btn-yellow | btn-white | btn-black | btn-rounded | btn-circle | btn-square | btn-disabled)
								onClick: function(dialog) {						
									dialog.close();
									window.location = "DealCreationDetails_RP";
								}
							}, ],
							autoclose: false, //Auto Close Modal Box?				
							closeClick: false, //Close Modal on click near the box
							closable: true //If Modal is closable
						});						    			
					} 
			    	else if(data.status == 205){
			    		modal({
							type: 'error', //Type of Modal Box (alert | confirm | prompt | success | warning | error | info | inverted | primary)
							title: 'Message', //Modal Title
							text: 'Currently We are facing technical issues, please try again later.', //Modal HTML Content
							size: 'normal', //Modal Size (normal | large | small)
							buttons: [{
								text: 'OK', //Button Text
								val: 'ok', //Button Value
								eKey: true, //Enter Keypress
								 //addClass: 'btn-light-blue', //Button Classes (btn-large | btn-small | btn-green | btn-light-green | btn-purple | btn-orange | btn-pink | btn-turquoise | btn-blue | btn-light-blue | btn-light-red | btn-red | btn-yellow | btn-white | btn-black | btn-rounded | btn-circle | btn-square | btn-disabled) 
								onClick: function(dialog) {						
									dialog.close();
									 window.location="DealCreationDetails_RP";
								}
							}, ],
							autoclose: false, //Auto Close Modal Box?				
							closeClick: false, //Close Modal on click near the box
							closable: true //If Modal is closable
						});
					}
					else {
						modal({
							type: 'error', //Type of Modal Box (alert | confirm | prompt | success | warning | error | info | inverted | primary)
							title: 'Message', //Modal Title
							text: customMessage, //Modal HTML Content
							size: 'normal', //Modal Size (normal | large | small)
							buttons: [{
								text: 'OK', //Button Text
								val: 'ok', //Button Value
								eKey: true, //Enter Keypress
								 //addClass: 'btn-light-blue', //Button Classes (btn-large | btn-small | btn-green | btn-light-green | btn-purple | btn-orange | btn-pink | btn-turquoise | btn-blue | btn-light-blue | btn-light-red | btn-red | btn-yellow | btn-white | btn-black | btn-rounded | btn-circle | btn-square | btn-disabled) 
								onClick: function(dialog) {						
									dialog.close();
									 window.location="DealCreationDetails_RP";
								}
							}, ],
							autoclose: false, //Auto Close Modal Box?				
							closeClick: false, //Close Modal on click near the box
							closable: true //If Modal is closable
						});
					}
			    },function(data) {
			        $scope.displayres = data.data;
			        $scope.answer = 'Posting data was unsuccessful.';
			});
		 };
		 
			 //Uploading file for Manual Rate card
		  $scope.getupdatePreSalesExcel= function(){
          	var  getupdatePreSalesExcel = function(response) {
               		$scope.PreSalesExcel = response.data;
               		console.log("Total Data");
               		console.log(response.data);
               		
    		 };
               		
					WebServiceFactory.getupdatePreSalesExcel().then(getupdatePreSalesExcel);
          };  
			 
		 $scope.redirectView = function() {
			 
			 modal({
					 type: 'error', //Type of Modal Box (alert | confirm | prompt | success | warning | error | info | inverted | primary)
					 title: 'Message', //Modal Title
					 text: 'Are you sure you want to Cancel the changes?', //Modal HTML Content
					 size: 'normal', //Modal Size (normal | large | small)
					 buttons: [{
						 text: 'Yes', //Button Text
						 val: 'Yes', //Button Value
						 eKey: true, //Enter Keypress
						 addClass: 'btn-light-blue', //Button Classes (btn-large | btn-small | btn-green | btn-light-green | btn-purple | btn-orange | btn-pink | btn-turquoise | btn-blue | btn-light-blue | btn-light-red | btn-red | btn-yellow | btn-white | btn-black | btn-rounded | btn-circle | btn-square | btn-disabled)
						 onClick: function(dialog) {						
							 dialog.close();
							 window.location = "DealCreationDetails_RP_V2";
						 }
					 },
					 {
						 text: 'No', //Button Text
						 val: 'No', //Button Value
						 eKey: true, //Enter Keypress
						 addClass: 'btn-light-blue', //Button Classes (btn-large | btn-small | btn-green | btn-light-green | btn-purple | btn-orange | btn-pink | btn-turquoise | btn-blue | btn-light-blue | btn-light-red | btn-red | btn-yellow | btn-white | btn-black | btn-rounded | btn-circle | btn-square | btn-disabled)
						 onClick: function(dialog) {						
							 dialog.close();
						 }
					 }, ],
				 });
			 }
		 
	
	$scope.uploadTMAttachmentData = function(frmDeal){		
	//alert("in ulpload");
		var file = $('input[name="fuAttachFilename"]').get(0).files[0]
		console.log(file);
		console.log(file.name)
		var dealId = $scope.frmDeal.crmDealId;
			$scope.upload= false;
			if(file != undefined ) {
				$scope.uploadTMFileData(file);
			} else {
				BootstrapDialog.show({
					title : 'TM Deal Creation - Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Please upload File and Click on Update Button",
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(
								dialogRef) {
							dialogRef.close();
						}
					} ]
				});	
			}
	}	
	
	$scope.uploadTMFileData=function(file){
	
	var dealId=$scope.frmDeal.DealModel;
	var filename=file.name;
	
	console.log("in ulpload2");
	console.log(dealId);
	console.log(filename);
	console.log(file);
		var formData = new FormData();
		formData.append('file', file);
		formData.append('filename',filename);
		formData.append('dealId',dealId);
		
		var uploadUrl = contextPath+"/RightPrice-DAS/uploadDealsFile";
	    $http.post(uploadUrl, formData, {
	        transformRequest: angular.identity,
	        headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
	    }).
	    then(function(data) {	    	
	    	var obj = JSON.stringify(data);
			var json = JSON.parse(obj);
			
			console.log(json);
			var customMessage = data.data;
			
	    	if(data.status == 200)	
			{
				BootstrapDialog.show({
								title : 'File Uploaded Successfully',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : customMessage,
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										$scope.searchFile($scope.frmDeal.DealModel)
										$scope.frmDeal.fuAttachFilenameModel = undefined;
									}
								} ]
							});	
			
			} 
	    	else if(data.status == 205){
					BootstrapDialog.show({
					title : 'TM Deal Creation - Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : "File Upload Failed.",
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
						}
					} ]
				});	
			}
			else {
				BootstrapDialog.show({
				title : 'TM Deal Creation - Details',
				type : BootstrapDialog.TYPE_DANGER,
				message : customMessage,
				closable : false,
				buttons : [ {
					label : 'OK',
					action : function(dialogRef) {
						dialogRef.close();
					}
				} ]
			});
			}
	    },function(data) {
	        $scope.displayres = data.data;
	        $scope.answer = 'Posting data was unsuccessful.';
	});
	

	}
	
	
	$scope.searchFile = function(crmDealId)
	{
		angular.element("input[type='file']").val(null);
		console.log("inside File Search---"+crmDealId);

		var markers = {
				"crmDealId" : crmDealId
				};
		$http({
		    method: 'POST',
		    url: contextPath+'/RightPrice-DAS/getFileAttachementData',
		    dataType: 'json',
		    data: JSON.stringify(markers),  
	     headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    }).
			    then(function(data) {
			    console.log('inside response')
			    console.log(data.data)
			    	if(data.data.length!=0){
			    		$scope.tableHide=false;
						$scope.tempo=[];
						$scope.tempo=data.data;						
						var l,j,k,m;
						$scope.attachmentTableHide=true;
						$scope.uploadTableHide=true;
						$scope.mainTableHide=true;
						$scope.devTableHide=true;
						
						for(var i=0,j=0,k=0,l=0,m=0;i<$scope.tempo.length;i++)
						{
							if($scope.tempo[i].dealId==crmDealId)
							{
							console.log('inside condition check');
								$scope.AttachementData[k] = $scope.tempo[i];
								var strMain = $scope.AttachementData[k].createdOn;
							    var arrSplit = [];
							    arrSplit = strMain.split(" ");
							    $scope.AttachementData[k].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
							k++;
							$scope.attachmentTableHide=false;
							}
						}
					}
					else
					{
						$scope.tableHide=true;
					}			    	
			    	
			    });		
	}

$scope.downloadFile = function(dealAttachmentId){
	//alert('hi'+dealAttachmentId);
	WebServiceFactory.downloadDealFileWithFileName(dealAttachmentId);
};



$scope.downloadFileWithFileName = function(dealAttachmentId){
	//alert('hi'+objectId);
	WebServiceFactory.downloadFileWithFileName(dealAttachmentId);
};

$scope.deleteDealFile = function(dealAttachmentId) {

console.log('inside delete method')
//console.log(crmDealId);
				WebServiceFactory.deleteDealFile(dealAttachmentId);
				for(var i = 0; i < $scope.AttachementData.length;i++)
					{
					if($scope.AttachementData[i].dealAttachmentId == dealAttachmentId)
						{
						$scope.AttachementData.splice(i,1);						
						}
					}
				if($scope.AttachementData.length == 0){
					 $scope.tableHide = true;
				}

					
				
			};
var getDealDetails = function(response) {
	if($localStorage.DealModel != undefined && $localStorage.crmDealId != undefined )
		{
			console.log(response);
			$scope.dealDetails = response.data;
			if($scope.dealDetails != undefined && $localStorage.crmDealId != undefined )
			{
				var statusId = $scope.dealDetails[0].currentApprovalStatus;			          
			       if(statusId != 1 && statusId != 4) 
			      	{
			    	  $scope.isVersionAvailable = true;  
			       	  $scope.isCopyDisabled = true; 
					 }		     
					else
					{
						$scope.isVersionAvailable = false;
						$scope.isCopyDisabled=false;
					}		
					/*console.log($scope.dealDetails);
					console.log($scope.dealDetails[0].dealId);
					console.log($scope.dealDetails[0].customerId);
					console.log($scope.dealDetails[0].dealStartDate);
					console.log($scope.dealDetails[0].dealEndDate);
					console.log($scope.dealDetails[0].dealDescription);
					console.log($scope.dealDetails[0].dealStatus)
					console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
					console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");*/
					$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
/*					$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";		
*/					var dealEndDate  = $scope.dealDetails[0].dealEndDate;
					var date = new Date(dealEndDate.substring(0,10));
					console.log("End Date is......... " + date);
					var dateENd = $filter('date')(date,'dd/MM/yyyy');
					$scope.dealDetails[0].dealEndDate = dateENd;
					
					var dealStartDate = $scope.dealDetails[0].dealStartDate;
					var date = new Date(dealStartDate.substring(0,10));
					var dateStart = $filter('date')(date,'dd/MM/yyyy');
					$scope.dealDetails[0].dealStartDate = dateStart;		
					$scope.dealDetails[0].rpVersionId = $localStorage.crmDealId;	
					$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;		
				    var startDay = new Date(dateStart);
				     var endDay = new Date(dateENd);
				     var millisecondsPerDay = 1000 * 60 * 60 * 24;
				
				     var millisBetween =  endDay.getTime()-startDay.getTime() 
				     var days = millisBetween / millisecondsPerDay;
				     $scope.dealDetails[0].DealDuration = Math.floor(days);  
				     
				    
			
			}
		
			WebServiceFactory.getDealDetails($localStorage.DealModel,$localStorage.crmDealId).then(getDealDetails);//WebServiceFactory.getDealDetails($sessionStorage.crmDealId).then(getDealDetails);   	
		}		
		
		else
		{
		
		}
		
};

$scope.currentUser = function(user)
{
	if (user.length>10){
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
	if($sessionStorage.currentUser == null) {
		$sessionStorage.currentUser = $scope.user;
	}
}
				
$scope.getDUHCustomerVerticalMapping = function(verticalId) {
	var getDUHCustomerVerticalMapping = function(response) {
		$scope.customer = response.data;
		$scope.customerVerticalMapping = response.data;
	}
	WebServiceFactory.getDUHCustomerVerticalMapping(verticalId).then(getDUHCustomerVerticalMapping);
};

$scope.getDealSFInfo = function(dealId)
{
	var PutDealSFData = function(response){
		console.log(response.data)
		$scope.SFDealData = response.data;
		$scope.frmDeal.sfAccntId = $scope.SFDealData[0].strSFAccountId
		$scope.frmDeal.sfAccntNm = $scope.SFDealData[0].strSFAccountName
		$scope.frmDeal.sfIrisId = $scope.SFDealData[0].strSFIrisAccountId
		$scope.frmDeal.sfIrisAccNm = $scope.SFDealData[0].strSFIrisAccountName
		$scope.frmDeal.sfIrisCode = $scope.SFDealData[0].strSFIrisCode
	}
	WebServiceFactory.getFPDealSFInfo(dealId).then(PutDealSFData)
}


$scope.calculateExpectedOffshore = function(){
     		var offShorePercentage = (100 - $scope.frmDeal.expectedonsiteresource)
     		$scope.frmDeal.expectedoffshoreresource= offShorePercentage;  
     		var onsitePercentage = ($scope.frmDeal.expectedonsiteresource)
     		$scope.frmDeal.expectedonsiteresource = onsitePercentage; 
     		$scope.offShoreUtilizationModel = offShorePercentage;
     		
     		
     	 }
     	
     	 

$scope.getDealDetailsFor_Selection = function(crmDealId)
{
//alert(crmDealId);


console.log("inside deal selection");
$localStorage.DealModel= crmDealId; // storing session value for next page
										// by arvind
	$scope.clearData();
	
		if(crmDealId!=undefined)
	{		
		var getDealDetailsFor_Selection  = function(response)  //service will be call from this line
		{		
			if($scope.frmDeal.DealModel != undefined)
			{		
				
				$sessionStorage.crmDealId = $scope.frmDeal.DealModel; //$scope.frmDeal.DealModel.crmDealId;
			}
			else
			{			
				$sessionStorage.crmDealId=crmDealId;
			
			}			
			$scope.frmDeal.DealModel= response.data;
		console.log("inside deal id fetching");
		console.log(response.data);
		
		$scope.crmDealDetailArrays= response.data;
var crmdlLen=0;
	crmdlLen = $scope.crmDealDetailArrays.length;	
	var arr = [];
	var j=0;
	
	$scope.frmDeal.DealModel= $scope.crmDealDetailArrays[0].crmDealId;
	var id = $scope.crmDealDetailArrays.crmDealId;	
console.log($scope.crmDealDetailArrays[0]);


if($scope.crmDealDetailArrays[0].dealId != undefined){
	
	
	$scope.frmDeal.DealModel = $scope.crmDealDetailArrays[0].dealId;
	$scope.frmDeal.descriptionModel= $scope.crmDealDetailArrays[0].dealDescription;		
		$scope.frmDeal.salesSpocModel =  $scope.crmDealDetailArrays[0].salesSpoc; 
		
		    	//$scope.putDealData($scope.frmDeal.DealModel);
		    	
		$scope.frmDeal.dealStatusModel = $scope.crmDealDetailArrays[0].dealStatus;
		
		
		var dateStart =  $scope.crmDealDetailArrays[0].dealStartDate; //$scope.frmDeal.DealModel.dealStartDate ;	
		var date = new Date(dateStart.substring(0,10));
		$scope.frmDeal.dealStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');	
		$localStorage.startDate=dateStart; 
		var dateEnd = $scope.crmDealDetailArrays[0].dealEndDate;//$scope.frmDeal.DealModel.dealEndDate ;		
		var date = new Date(dateEnd.substring(0,10));
		$scope.frmDeal.dealEndDateModel = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy');

		$scope.calApplicableYears();
	
		$scope.frmDeal.countryNameModel = $scope.crmDealDetailArrays[0].country;
		$scope.getCities($scope.frmDeal.countryNameModel);
		$scope.frmDeal.cityNameModel =  parseInt($scope.crmDealDetailArrays[0].city);
		
		$scope.frmDeal.currencyModel = $scope.crmDealDetailArrays[0].currency;//$scope.frmDeal.DealModel.currency.currencyCode;
	//	$scope.frmDeal.stageModel =$scope.crmDealDetailArrays[0].stage.codeName;// $scope.frmDeal.DealModel.stage.description;
		$scope.frmDeal.dealTCVModel = $scope.crmDealDetailArrays[0].dealTCV; //$scope.frmDeal.DealModel.revenue;
		
		$scope.frmDeal.ddlIndustryModel = $scope.crmDealDetailArrays[0].industry;
		$scope.frmDeal.customerDescription = $scope.crmDealDetailArrays[0].customerDescription;
		$scope.frmDeal.expectedonsiteresource = $scope.crmDealDetailArrays[0].onsitePercentage;
		$scope.frmDeal.expectedoffshoreresource = $scope.crmDealDetailArrays[0].offshorePercentage;
		$scope.frmDeal.revenueModel = $scope.crmDealDetailArrays[0].revenue;
		$scope.frmDeal.costModel = $scope.crmDealDetailArrays[0].cost;
		$scope.frmDeal.marginPercentage = $scope.crmDealDetailArrays[0].grossMarginPercentage;
		$scope.frmDeal.AgileCostModel = $scope.crmDealDetailArrays[0].projectSpecificSGAIncludingAgileCost;
		$scope.frmDeal.OmPercentage = $scope.crmDealDetailArrays[0].omPercentage;
		$scope.frmDeal.VolumeDiscountModel = $scope.crmDealDetailArrays[0].volumeDiscountPercentage;
		$scope.frmDeal.PenaltyModel = $scope.crmDealDetailArrays[0].penaltyPercentage;
		$scope.frmDeal.omPercentageDiscountModel = $scope.crmDealDetailArrays[0].omPercentageAfterVolumeDiscount;
		switch ($scope.frmDeal.ddlIndustryModel) {
                   case 1:
                   $scope.frmDeal.ddlIndustryModel = "IT";
                   break;
                   case 0:
                   $scope.frmDeal.ddlIndustryModel = "KPO";
                   break;
                   case 2:
                   $scope.frmDeal.ddlIndustryModel = "JV KPO";
                   break;
                   default:
                    $scope.frmDeal.ddlIndustryModel = "Others";
}
switch ($scope.frmDeal.dealStatusModel) {
	               case 0:
                   $scope.frmDeal.dealStatusModel = "Open";
                   break;
                   case 1:
                   $scope.frmDeal.dealStatusModel = "Won";
                   break;
                   case 2:
                   $scope.frmDeal.dealStatusModel = "Lost";
                   break;
                   default:
                    $scope.frmDeal.dealStatusModel = "Others";
}
		//$scope.getFileData($scope.crmDealDetailArrays[j].crmDealId);
		 //$scope.frmDeal.DealModel.industry;		
		var dealStatus=  $scope.crmDealDetailArrays[0].dealStatus.description; //$scope.frmDeal.DealModel.dealStatus.description;
		
		$scope.dealStartDateModel=$scope.crmDealDetailArrays[0].dealStartDate;

		$scope.dealEndDateModel=$scope.crmDealDetailArrays[0].dealEndDate;
		$scope.durationModel=$scope.crmDealDetailArrays[0].duration;
		$scope.searchFile(crmDealId);
}
		else
		{
		$scope.frmDeal.descriptionModel= $scope.crmDealDetailArrays[j].dealDescription;		
		$scope.frmDeal.salesSpocModel =  $scope.crmDealDetailArrays[j].salesSpoc; 
		    	//$scope.putDealData($scope.frmDeal.DealModel);
		    	
		$scope.frmDeal.dealStatusModel = $scope.crmDealDetailArrays[j].dealStatus.description;
		
		
		var dateStart =  $scope.crmDealDetailArrays[j].dealStartDate; //$scope.frmDeal.DealModel.dealStartDate ;	
		var date = new Date(dateStart.substring(0,10));
		$scope.frmDeal.dealStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');	
		$localStorage.startDate=dateStart; 
		var dateEnd = $scope.crmDealDetailArrays[j].dealEndDate;//$scope.frmDeal.DealModel.dealEndDate ;		
		var date = new Date(dateEnd.substring(0,10));
		$scope.frmDeal.dealEndDateModel = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy');

		$scope.calApplicableYears();
		$scope.frmDeal.currencyModel = $scope.crmDealDetailArrays[j].currency.currencyCode;//$scope.frmDeal.DealModel.currency.currencyCode;
//		$scope.frmDeal.stageModel =$scope.crmDealDetailArrays[j].stage.codeName;// $scope.frmDeal.DealModel.stage.description;
		$scope.frmDeal.dealTCVModel = $scope.crmDealDetailArrays[j].revenue; //$scope.frmDeal.DealModel.revenue;
		
		$scope.frmDeal.ddlIndustryModel = $scope.crmDealDetailArrays[j].industry;
		$scope.frmDeal.customerDescription = $scope.crmDealDetailArrays[j].customerDescription;
		$scope.frmDeal.expectedonsiteresource = $scope.crmDealDetailArrays[j].onsitePercentage;
		$scope.frmDeal.expectedoffshoreresource = $scope.crmDealDetailArrays[j].offshorePercentage;
		$scope.frmDeal.revenueModel = $scope.crmDealDetailArrays[j].dealTCV;
		$scope.frmDeal.costModel = $scope.crmDealDetailArrays[j].cost;
		$scope.frmDeal.marginPercentage = $scope.crmDealDetailArrays[j].grossMarginPercentage;
		$scope.frmDeal.AgileCostModel = $scope.crmDealDetailArrays[j].projectSpecificSGAIncludingAgileCost;
		$scope.frmDeal.OmPercentage = $scope.crmDealDetailArrays[j].omPercentage;
		$scope.frmDeal.VolumeDiscountModel = $scope.crmDealDetailArrays[j].volumeDiscountPercentage;
		$scope.frmDeal.PenaltyModel = $scope.crmDealDetailArrays[j].penaltyPercentage;
		$scope.frmDeal.omPercentageDiscountModel = $scope.crmDealDetailArrays[j].omPercentageAfterVolumeDiscount;
		switch ($scope.frmDeal.ddlIndustryModel) {
                   case 1:
                   $scope.frmDeal.ddlIndustryModel = "IT";
                   break;
                   case 0:
                   $scope.frmDeal.ddlIndustryModel = "KPO";
                   break;
                   case 2:
                   $scope.frmDeal.ddlIndustryModel = "JV KPO";
                   break;
                   default:
                    $scope.frmDeal.ddlIndustryModel = "Others";
}
		//$scope.getFileData($scope.crmDealDetailArrays[j].crmDealId);
		 //$scope.frmDeal.DealModel.industry;		
		var dealStatus=  $scope.crmDealDetailArrays[j].dealStatus.description; //$scope.frmDeal.DealModel.dealStatus.description;
		
		$scope.dealStart=$scope.crmDealDetailArrays[j].dealStartDate;

		$scope.dealEnd=$scope.crmDealDetailArrays[j].dealEndDate;
		 }   
		};
		WebServiceFactory.getDealDetailsFor_Selection(crmDealId).then(getDealDetailsFor_Selection);
	}
}


$scope.Closedeal=function(frmDeal){
	 window.location= contextPath+"/welcome";

};


////////////////////////saved deal/////////////////////////////////

/*$scope.getPrevDataDeal_crmDeal = function(dealId){
	$scope.clearData();		
	var dealId=$localStorage.dealId
	
	var getPrevDataDeal_VersionDetails = function(response) 
	{	
		console.log(response);			
		$scope.PrevDataDeal_Version = response.data;
		console.log("get Version Details ");
		console.log($scope.PrevDataDeal_Version);
		 if(response.status == 204)
	    {
	    	$scope.putDealData();
	    	
	    }
	    else
	    {	    	
	    	$scope.industryType = [{ name: "IT", id: 1 }, { name: "KPO", id: 0 },{name: "JV KPO", id: 2 }];
	    	
	    	$scope.frmDeal.customerNameModel =  $scope.PrevDataDeal_Version[0][15]; // 50022;
	    	$sessionStorage.customerId = $scope.frmDeal.customerNameModel; 
	    	$scope.getDealDetails($scope.frmDeal.customerNameModel);
	    	
		    	
	    	$scope.frmDeal.DealModel = $scope.PrevDataDeal_Version[0][1];
	    	
	        		
	    	
	    	
	    	$scope.frmDeal.descriptionModel = $scope.PrevDataDeal_Version[0][4];
	    	
	    	$scope.frmDeal.dealStatusModel =  $scope.PrevDataDeal_Version[0][26]; //'Close';
	    	
	    	$scope.frmDeal.salesSpocModel = $scope.PrevDataDeal_Version[0][5];
	    	$scope.frmDeal.dealTCVModel = $scope.PrevDataDeal_Version[0][6];
	    	$scope.frmDeal.currencyModel = $scope.PrevDataDeal_Version[0][7];    	
	    	
	    	var dateStart =  $scope.PrevDataDeal_Version[0][8];	
	    	var date = new Date(dateStart.substring(0,10));	    	
	    	$scope.frmDeal.dealStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');
	    	$localStorage.startDate=dateStart; 
	    	var end_date=   $scope.PrevDataDeal_Version[0][9];
	    	var date= new Date(end_date.substring(0,10));	    	
	    	$scope.frmDeal.dealEndDateModel =  $filter('date')(end_date.substring(0,10),'dd/MM/yyyy');
	    	temp=0;	    	
	    	$scope.frmDeal.stageModel = $scope.PrevDataDeal_Version[0][32];
	    	$scope.frmDeal.onsiteHoursModel = $scope.PrevDataDeal_Version[0][10];	    	
	    	$scope.frmDeal.offShoreHoursModel = $scope.PrevDataDeal_Version[0][11];
	    	
	    	$scope.frmDeal.isBizopsModel = $scope.PrevDataDeal_Version[0][12];
	    		    	
	    	//$scope.frmDeal.ddlIndustryModel = $scope.PrevDataDeal_Version[0][13];
	    	$scope.frmDeal.isManualDeal = $scope.PrevDataDeal_Version[0][14]; 

	    	
	    	$scope.frmDeal.penaltyPercent = $scope.PrevDataDeal_Version[0][16];
	    	$scope.frmDeal.oldDealIdModel = $scope.PrevDataDeal_Version[0][17];
	    	$scope.frmDeal.percentageCloseModel = $scope.PrevDataDeal_Version[0][18];
	    	$scope.frmDeal.projectIdModel = $scope.PrevDataDeal_Version[0][19];
	    	
	    	$scope.frmDeal.riskCategoryModel = $scope.PrevDataDeal_Version[0][20];
	    	$scope.frmDeal.ddlIndustryModel = $scope.PrevDataDeal_Version[0][21];
	    	//alert('== IT[0][21]='+ $scope.PrevDataDeal_Version[0][21]+'   ddlLOBModel[0][22]='+$scope.PrevDataDeal_Version[0][22]);
	    	 var getLobDetails = function(response) {
	    			console.log(response);
	    			$scope.masterLob = response.data;
	    			
	    	};
	    	WebServiceFactory.getLobDetails().then(getLobDetails);
	    	//$scope.frmDeal.ddlLOBModel = $scope.PrevDataDeal_Version[0][22];
	    	
	    	
	    	$scope.frmDeal.dealPriorityModel = $scope.PrevDataDeal_Version[0][25];
	    	
	    	
	    	$scope.currentApprovalStatus = $scope.PrevDataDeal_Version[0][37];
	    	
	    	
	    	
		    
	    	  	
	    	
	    	temp=0;
	    	
	    	
	    	
	    	
	    	var selectedcrmDealId = dealId;
	    	var crmDealLen=0;
	    	
	    	crmDealLen = $scope.PrevDataDeal_Version.length; 
	    	
	    	  var arr = [];
	    	  var y=0;
	    	  for(var x=0; x<crmDealLen ;x++)
	    	  {
	    		 
	    		  var rpVID= $scope.PrevDataDeal_Version[x].crmDealLen;	
	    		  arr.push(rpVID);
	    		  
	    		  if(selectedcrmDealId == arr[x])
	    			  {		  
	    			  	 y = arr.indexOf(arr[x]);
	    			  
	    			  	 break;
	    			  }	    		 
	    	  };
	    		 	
	    	
	    	$scope.frmDeal.versionModel =dealId	    	
	    	
	    	$scope.calApplicableYears();
	    	
	    	$scope.getContry_City($localStorage.dealId);	
	    	
	    	
	    }
	};
	WebServiceFactory.getPrevDataDeal_crmDealId($localStorage.dealId).then(getPrevDataDeal_VersionDetails);
}
*/




}]);


//$scope.getDealDetailsFor_Selection = function(crmDealId){	
//	$localStorage.DealModel= crmDealId; // storing session value for next page
//										// by arvind
//	$scope.clearData();
//	if(crmDealId!=undefined)
//	{		
//		var getDealDetailsFor_Selection = function(response)  //service will be call from this line
//		{		
//			if($scope.frmDeal.DealModel != undefined)
//			{		
//				
//				$sessionStorage.crmDealId = $scope.frmDeal.DealModel;
//			}
//			else
//			{			
//				$sessionStorage.crmDealId=crmDealId;
//			
//			}			
//	         $scope.frmDeal.DealModel= response.data;
//			
//		    	console.log("inside deal id fetching method");
//		    	console.log(response.date);
//		    	$scope.putDealData();
//		    
//		};
//		WebServiceFactory.getDealDetailsFor_Selection(crmDealId).then(getDealDetailsFor_Selection);
//	}
//}



		
/*$scope.saveDealData=function(frmDeal){
	$scope.saved = true;
	//Need to add session


 
    	   console.log("Inside Else...");
    	   var lanId = $scope.frmRateCard.deliveryspocname;
			   var spocName  = $.grep($scope.empName, function (empName) {
               return empName.lanId == lanId;
           })[0].employeeName;
    	   $scope.getCustomerVerticalId($scope.frmRateCard.customer);
    	   var marker={
    			   "rcId": $scope.frmRateCard.rateCardId,
    			   "customerVerticalMapId": $sessionStorage.customerVerticalMapId,
    			   "rcName": $scope.frmRateCard.ratecardname,
    			   "rcStartDate":$scope.frmRateCard.startdate,
    			   "rcEndDate":$scope.frmRateCard.enddate,
    			   "expectedRCEndDate":$scope.frmRateCard.expectedenddate,
    			   "tvc":$scope.frmRateCard.tcv,
    			   "volumeDiscount":$scope.frmRateCard.volumediscount,
    			   "isItKpo":$scope.frmRateCard.industry,
    			   "applicableYears":$scope.frmRateCard.applicableyears,
    			   "applicableMonths":$scope.frmRateCard.applicableMonths,
    			   "expectedOnsiteResourcePercentage":$scope.frmRateCard.expectedonsiteresource,
    			   "expectedOffshoreResourcePercentage":$scope.frmRateCard.expectedoffshoreresource,
    			   "deliverySpocName":$scope.frmRateCard.deliveryspocname,
    			   "salesSpocName":$scope.frmRateCard.salesspocname,
    			   "consolidatedRcCurrencyId":$scope.frmRateCard.currency,
    			   "rcProgressStatusId":1,
    			   "currentApprovalStatus":1,
    			   "onsiteHoursPerDay":onsiteHourPerDay,
    			   "offshoreHoursPerDay":offShorehourPerDay,
    			   "isRenewal":$scope.frmRateCard.renewal,
    			   "renewalRCId":$scope.frmRateCard.rcIdName,
    			   "isManualRc":$scope.rateCardType,
    			   "iqnCharges":$scope.frmRateCard.iqnCharges,
    			   "cpcCharges":$scope.frmRateCard.cpcCharges,
    			   "statusIndicator":"DRAFT",
    			   "manualRCId":manualRC,
    			   "verticalId":$sessionStorage.verticalId,
    			   "customerId":$scope.frmRateCard.customer,
    			   "yOYIncrementPercents":	$scope.yoyDetails,
    			   "regionWiseUtilization":$scope.regionWiseUtilization,
    			   "rateCardLocations":$scope.locDetails,
    			   "spocName":spocName,
    			   "discFlag":$scope.frmRateCard.ddlVolumeUpdateModel
    			   
    	   };
       
		var saveRateCard=function(response){
			//console.log("data");
			//console.log(response);
			//console.log(response.status);
			 if( response.status == 200){
				 $localStorage.rcId=response.data; 
				  BootstrapDialog.show({
							title : 'Rate Card Creation',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'Rate card created successfully.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									 window.location="RateCardCreationDetails";
								}
							} ]
						});
				  }

		};
		WebServiceFactory.saveRateCard(marker).then(saveRateCard);
	
};*/
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