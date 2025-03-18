"use strict";

		app.controller("TMDealCreationDetailsController", ['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory,$localStorage, $sessionStorage,$index){
			var contextPath = "/RightPrice-DAS";
			$scope.AttachementData=[];
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
			
			 
			$scope.ShowHideUpload = function () {
			     $scope.UploadHidden = $scope.UploadHidden ? false : true;
			 };
			 $scope.ShowHideAttachment = function () {
			     $scope.AttachmentHidden = $scope.AttachmentHidden ? false : true;
			 };
			 		
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
					$scope.frmDeal.DealModel = sessionStorage.getItem('crmDealId');

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
				var rpDealVersionId = $localStorage.rpDealVersionId;
				var customerId= $sessionStorage.customerId;
				if(typeof rpDealVersionId != 'number') 
				{
					rpDealVersionId = parseInt(rpDealVersionId);
				}
			
				if( typeof rpDealVersionId == 'number' && !isNaN(rpDealVersionId)) // &&   !isNaN(parseFloat("rpDealVersionId"))
				{
					temp=10;
					$scope.getPrevDataDeal_Version(rpDealVersionId);
					$scope.putDealData();
					$scope.searchFile(rpDealVersionId);
					$scope.getVersiondata(rpDealVersionId);
					sessionStorage.removeItem('accountId');
					sessionStorage.removeItem('crmDealId');
					sessionStorage.removeItem('dashboardData');
				}
				$scope.onloadCoun_City();
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
				        			$localStorage.rpDealVersionId = $scope.frmDeal.versionModel;
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
			
			
			
		 $scope.onloadCoun_City = function()
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
				WebServiceFactory.get_onloadCoun_City($localStorage.rpDealVersionId).then(get_onloadCoun_City);					
			};
			
			
			
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

//get version based on deal change
$scope.getVersion = function(crmDealId){	
	$localStorage.DealModel= crmDealId; // storing session value for next page
										// by arvind
	
	$scope.clearData();
	if(crmDealId!=undefined)
	{		
		var getVersionDetailsTM = function(response)  //service will be call from this line
		{		
			if($scope.frmDeal.DealModel != undefined)
			{		
				
				$sessionStorage.crmDealId = $scope.frmDeal.DealModel; //$scope.frmDeal.DealModel.crmDealId;
			}
			else
			{			
				$sessionStorage.crmDealId=crmDealId;
			
			}			
			$scope.versionDetail = response.data;		
		
			var lenOfVid =$scope.versionDetail.length;
			$sessionStorage.NoOfVersion=lenOfVid;
			//document.getElementById("ddlVersion").options.length;		
			if(lenOfVid <= 0)
				{
					$scope.getVersionName(frmDeal);				
				}
			
			if(userType == 'Delivery') {
				 $scope.isVersionAvailable = false;  
			   	  $scope.isCopyDisabled = false;  
			}
			
		    if(response.status == 204){
		    	//$scope.isVersionAvailable = true;	
		    	
		    	$scope.clearData();
		    	//alert('148 getVersion response.status == 204')
		    	$scope.putDealData();
		    }
		};
		WebServiceFactory.getVersionDetailsTM(crmDealId).then(getVersionDetailsTM);
	}
}


$scope.getPrevDataDeal_Version = function(versionId){
	$scope.clearData();		
	var rpversonID=$localStorage.rpDealVersionId	
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
	    	$scope.fpType = [{ name: "Development", id: 1 }, { name: "Maintenance", id: 2 }];
	    	$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }];
	    	$scope.isAgileBased = [{ name: "Yes", id: 1 }, { name: "No", id: 0 }];
	    	$scope.isSyntelOnsiteFacilityUsed = [{ name: "Client", id: 1 }, { name: "Syntel", id: 0 }];
	    	$scope.riskCategoryId = [{ name: "A", id: 1 }, { name: "B", id: 2 }, { name: "C", id: 3 }, { name: "D", id: 4 }];
	    	$scope.industryType = [{ name: "IT", id: 1 }, { name: "KPO", id: 0 },{name: "JV KPO", id: 2 }];
	    	$scope.dealPriorityId = [{ name: "VHIGH", id: 1 }, { name: "HIGH", id: 2 }, { name: "MEDIUM", id: 3 }, { name: "LOW", id: 4 }];
	    	
	    	
	    	$scope.frmDeal.customerNameModel =  $scope.PrevDataDeal_Version[0][15]; // 50022;
	    	$sessionStorage.rpCustomerId = $scope.frmDeal.customerNameModel; 
	    	$scope.getDealDetails($scope.frmDeal.customerNameModel);
	    	$scope.frmDeal.rbuTypeModel=$scope.PrevDataDeal_Version[0][38];
		    	
	    	$scope.frmDeal.DealModel = $scope.PrevDataDeal_Version[0][1];
	    	$scope.getVersion($scope.frmDeal.DealModel);
	        		
	    	$scope.frmDeal.versionModel = $localStorage.rpDealVersionId;	    	
	    	//$scope.putVersion($scope.frmDeal.versionModel); /replaced and added in last 	    	
	    	$scope.PrevDataDeal_Version[0][2];
	    	
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

	    	if(	$scope.frmDeal.isManualDeal == 1)
	    		{
	    		$scope.frmDeal.isManualDeal = true;
	    		}
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
	    	
	    	//$scope.frmDeal.isAgileBasedModel = $scope.PrevDataDeal_Version[0][23];
	    	if($scope.PrevDataDeal_Version[0][23]=='true')
	    		{
	    		$scope.frmDeal.isAgileBasedModel=1;
	    		}
	    	else
	    		{
	    		$scope.frmDeal.isAgileBasedModel=0;
	    		}
	    	
	    	//$scope.frmDeal.onsiteFacilityModel = $scope.PrevDataDeal_Version[0][24];
	    	if($scope.PrevDataDeal_Version[0][24]=='true')
    		{
	    		$scope.frmDeal.onsiteFacilityModel=1;
    		}
    	else
    		{
    			$scope.frmDeal.onsiteFacilityModel=0;
    		}
	    	$scope.frmDeal.dealPriorityModel = $scope.PrevDataDeal_Version[0][25];
	    	
	    	$scope.frmDeal.fuAttachment_1Model = $scope.PrevDataDeal_Version[0][27];
	    	$scope.frmDeal.fuAttachment_2Model = $scope.PrevDataDeal_Version[0][28];
	    	$scope.frmDeal.fuUploadManualDealModel = $scope.PrevDataDeal_Version[0][29];
	    	$scope.currentApprovalStatus = $scope.PrevDataDeal_Version[0][37];
	    	
	    	if($scope.frmDeal.fuAttachment_1Model > 0)
			{
	    		$("#attachment1").show();
	    		$("#attachment1").text("Download:"+$scope.frmDeal.fuAttachment_1Model);
			}
	    	if($scope.frmDeal.fuAttachment_2Model > 0)
			{
	    		$("#attachment2").show();
	    		$("#attachment2").text("Download:"+$scope.frmDeal.fuAttachment_2Model);
			}
	    	if($scope.frmDeal.fuUploadManualDealModel > 0)
			{
	    		$("#manualDealAttachment").show();
	    		$("#manualDealAttachment").text("Download:"+$scope.frmDeal.fuUploadManualDealModel);
			}
	    	
		    
	    	if($scope.frmDeal.dealPriorityModel==1)
	    		{
	    		$scope.frmDeal.dealPriorityModel='VHIGH'
	    		}
	    	else if($scope.frmDeal.dealPriorityModel==2)
	    		{
	    		$scope.frmDeal.dealPriorityModel='HIGH'
	    		}
	    	else if($scope.frmDeal.dealPriorityModel==3)
	    		{
	    		$scope.frmDeal.dealPriorityModel='MEDIUM'
	    		}
	    	else
	    		{
	    		$scope.frmDeal.dealPriorityModel='LOW'
	    		}	    	
	    	
	    	temp=0;
	    	
	    	var rpDealVersionId=$localStorage.rpDealVersionId
	   
	    	if(rpDealVersionId!=undefined){
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
	    		WebServiceFactory.getRiskAnswers(rpDealVersionId).then(getRiskAnswers);
	    	}
	    	
	    	
	    	var selectedRPVersionId = rpDealVersionId;
	    	var rpVersionLen=0;
	    	
	    	rpVersionLen = $scope.PrevDataDeal_Version.length; 
	    	
	    	  var arr = [];
	    	  var y=0;
	    	  for(var x=0; x<rpVersionLen ;x++)
	    	  {
	    		 
	    		  var rpVID= $scope.PrevDataDeal_Version[x].rpDealVersionId;	
	    		  arr.push(rpVID);
	    		  
	    		  if(selectedRPVersionId == arr[x])
	    			  {		  
	    			  	 y = arr.indexOf(arr[x]);
	    			  
	    			  	 break;
	    			  }	    		 
	    	  };
	    		 	
	    	
	    	$scope.frmDeal.versionModel =rpDealVersionId	    	
	    	if($scope.frmDeal.versionModel != undefined)
	    	{	    	
		    	if($scope.PrevDataDeal_Version[0][30] == 1)
		    	{
		    		$scope.frmDeal.fpTypeModel = $scope.fpType[0].id;
		    	}
		    	else
		    	{
		    		$scope.frmDeal.fpTypeModel = $scope.fpType[1].id;
		    	}
		    	if($scope.PrevDataDeal_Version[0][31] == 1) //if($scope.PrevDataDeal_Version[y].newDeal== true)
		    	{ 
		    	
		    		$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[0].id;
		    		$scope.hideOldDealId = true;
		    		$scope.hideOldProjectlId = true;
		    		
		    		
		    	}
		    	else
		    	{
		    		$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[1].id;
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
		    	$scope.findOldDealId();
	    	}	    		    	
	    
	    	$scope.calApplicableYears();
	    	
	    	$scope.getContry_City($localStorage.rpDealVersionId);	
	    	
	    	var dealStatus =$scope.frmDeal.dealStatusModel
	    	if(dealStatus=='Close')
	    	{
	    		$scope.isDisabled = true;
	    		if($scope.currentApprovalStatus ==3 ||$scope.currentApprovalStatus ==2)
	    		{
	    			$scope.isVersionAvailable = true;  
				   	  $scope.isCopyDisabled = true;
	    		}
	    	}
	    }
	};
	WebServiceFactory.getPrevDataDeal_Version($localStorage.rpDealVersionId).then(getPrevDataDeal_VersionDetails);
	
}

$scope.rpversonID = $localStorage.rpDealVersionId
$scope.getContry_City = function(rpversonID)
{
	if($localStorage.rpDealVersionId != undefined) 
	 {
		
		var getContry_CityDetails=function(response)
		{
			 if(response.data.length>0) 
				 {
				 	$scope.contry_cityArr=response.data;
					$scope.frmDeal.countryNameModel = $scope.contry_cityArr[0][0];
					$sessionStorage.ssnCity_id=$scope.contry_cityArr[0][1];
					$sessionStorage.countryId = $scope.frmDeal.countryNameModel;
					$scope.getCities($scope.frmDeal.countryNameModel);	
				 }				
		};	
		WebServiceFactory.getContry_City($localStorage.rpDealVersionId).then(getContry_CityDetails);
		
	 }
	
}



$scope.getVersiondata = function(rpDealVersionId)
{
	var getVersionData = function(response) {
		console.log("get versionDetails Data..");
		console.log(response);
		$scope.getVersionData=response.data;
		
		if($scope.getVersionData[0].currentApprovalStatus == 3 ||  $scope.getVersionData[0].currentApprovalStatus == 2 ){
			$scope.isDealApproveOrPendngAPRVL = true;
		}
		else{
			$scope.isDealApproveOrPendngAPRVL = false;
		}
		
		if($scope.getVersionData!=undefined)
		{
			$localStorage.pageTracker=$scope.getVersionData[0].pageTrackerStatus;
			$localStorage.CurrentApprovalStatus=$scope.getVersionData[0].currentApprovalStatus;
			if($scope.getVersionData[0].cyberSecurity!=null||undefined){
				$scope.frmDeal.securitymodel=$scope.getVersionData[0].cyberSecurity;
			}
			else{
				$scope.frmDeal.securitymodel="";
			}
			if($scope.getVersionData[0].industrySolution!=null||undefined){
				$scope.frmDeal.solutionModel=$scope.getVersionData[0].industrySolution;
			}
			else{
				$scope.frmDeal.solutionModel="";
			}
			if($scope.getVersionData[0].isSynbots!=null||undefined){
				$scope.frmDeal.synbotsModel=$scope.getVersionData[0].isSynbots;
			}
			else{
				$scope.frmDeal.synbotsModel="";
			}
			if($scope.getVersionData[0].synergyType!=null||undefined){
				
				$scope.frmDeal.synergyModel=$scope.getVersionData[0].synergyType.trim();
			}
			else{
				$scope.frmDeal.synergyModel="";
			}
			
		}
	};
	WebServiceFactory.getVersionData(rpDealVersionId).then(getVersionData);
	}



//put version detail on change of version and get risk answers
$scope.putVersion = function(rpDealVersionId){	
	temp=0;
	console.log("inside putVersion---"+rpDealVersionId);	
	$scope.clearData();

	$localStorage.rpDealVersionId = rpDealVersionId;
	$scope.onloadCoun_City();
	if(rpDealVersionId!=undefined)
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
		WebServiceFactory.getRiskAnswers(rpDealVersionId).then(getRiskAnswers);
		
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
		WebServiceFactory.getPrevDataDeal_Version(rpDealVersionId).then(getLatestVersionData);
		
	}
			
	$scope.getContry_City(rpDealVersionId);	

	//a
	var selectedRPVersionId = rpDealVersionId;
	var rpVersionLen=0;
	rpVersionLen = $scope.versionDetail.length;
	//alert('crmdlLen='+crmdlLen);
	  var arr = [];
	  var y=0;
	  for(var x=0; x<rpVersionLen ;x++)
	  {
		  var rpVID= $scope.versionDetail[x].rpDealVersionId;		
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

	  var selectedRPVId = rpDealVersionId;
	  var rpverLen = 0;
	  rpverLen = $scope.versionDetail.length;	
	  var arr = [];
	  var b=0;
	  for(var a=0; a<rpverLen ;a++)
	  {  
		  var tempVid= $scope.versionDetail[a].rpDealVersionId;		
	 	  arr.push(tempVid);		
		  if(selectedRPVId == arr[a])
			  {		  
			  	 b = arr.indexOf(arr[a]);
			  	 break;
			  }
	  }	  
	   $scope.searchFile(rpDealVersionId);
		if(temp != 10)
		{
		 $scope.putDealData();
		 $scope.searchFile(rpDealVersionId);
		}	
	}
	
	
}

$scope.putDealData = function()
	{
	
	var putDealData = function(response){
	var selectedValueCRM = $localStorage.DealModel;
	var crmdlLen=0;
	$scope.crmDealDetailArrays= response.data;

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
		
		$scope.frmDeal.descriptionModel= $scope.crmDealDetailArrays[j].dealDescription;		
		$scope.frmDeal.salesSpocModel =  $scope.crmDealDetailArrays[j].salesSpoc; // $scope.frmDeal.DealModel.salesSpoc;
		$scope.frmDeal.dealStatusModel = $scope.crmDealDetailArrays[j].dealStatus.description;
		$scope.frmDeal.dealPriorityModel =  $scope.crmDealDetailArrays[j].dealPriority.description; //$scope.frmDeal.DealModel.dealPriority.description;
		
	    $scope.frmDeal.rbuTypeModel= $scope.crmDealDetailArrays[j].rbuProfitCenter;
	    console.log("Deal Id's");
	    console.log($scope.crmDealDetailArrays[j].rbuProfitCenter);
		var dateStart =  $scope.crmDealDetailArrays[j].dealStartDate; //$scope.frmDeal.DealModel.dealStartDate ;	
		var date = new Date(dateStart.substring(0,10));
		$scope.frmDeal.dealStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');	
		$localStorage.startDate=dateStart; 
		var dateEnd = $scope.crmDealDetailArrays[j].dealEndDate;//$scope.frmDeal.DealModel.dealEndDate ;		
		var date = new Date(dateEnd.substring(0,10));
		$scope.frmDeal.dealEndDateModel = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy');

		$scope.calApplicableYears();
		$scope.frmDeal.currencyModel = $scope.crmDealDetailArrays[j].currency.currencyCode;//$scope.frmDeal.DealModel.currency.currencyCode;
		$scope.frmDeal.percentageCloseModel = $scope.crmDealDetailArrays[j].percentageClose;//$scope.frmDeal.DealModel.percentageClose;
		$scope.frmDeal.stageModel =$scope.crmDealDetailArrays[j].stage.codeName;// $scope.frmDeal.DealModel.stage.description;
		$scope.frmDeal.dealTCVModel = $scope.crmDealDetailArrays[j].revenue; //$scope.frmDeal.DealModel.revenue;
		$scope.frmDeal.ddlIndustryModel = $scope.crmDealDetailArrays[j].industry; //$scope.frmDeal.DealModel.industry;		
		var dealStatus=  $scope.crmDealDetailArrays[j].dealStatus.description; //$scope.frmDeal.DealModel.dealStatus.description;
		
		$scope.dealStart=$scope.crmDealDetailArrays[j].dealStartDate;

		$scope.dealEnd=$scope.crmDealDetailArrays[j].dealEndDate;

		
		if($scope.currentApprovalStatus ==3 ||$scope.currentApprovalStatus ==2){
			$scope.isVersionAvailable = false;  
		   	  $scope.isCopyDisabled = false;
		}
		if(dealStatus=='Close')
		{
			$scope.isDisabled = true;		
		}
		
	}
	WebServiceFactory.getDealDetailsForTM_Selection($localStorage.DealModel).then(putDealData)
	
};

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
	$scope.frmDeal.rbuTypeModel="";
	$scope.frmDeal.penaltyPercent = "";
	$scope.frmDeal.isManualDeal = false;
	
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

$scope.getVersionName = function(frmDeal){
	$scope.update= true;

	if(($scope.frmDeal.customerNameModel != null) && ($scope.frmDeal.DealModel != null))
	{
	
		$scope.update= false;
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
	        			$localStorage.rpDealVersionId = $scope.frmDeal.versionModel;
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
};

$scope.insertDealData = function(){	
	var fileTest = $("#fuUploadManualDeal,#fuAttachment_1,#fuAttachment_2");
	var name = ["MANUAL_DEAL", "ATTACHMENT_ID1","ATTACHMENT_ID2"];
	var isDealTypeManual = $scope.frmDeal.isManualDeal;
	if(isDealTypeManual == true){
		isDealTypeManual = 1;
	}
	else{
		isDealTypeManual = 0;
	}
	
	for(var i = 0;i<$scope.customer.length;i++) {
		if($scope.customer[i].customerId == $scope.frmDeal.customerNameModel) {
			$scope.verticalId = $scope.customer[i].verticalId;
			console.log("The Veritcal Id is............. "+ $scope.verticalId);
		}
	}

		if($sessionStorage.NoOfVersion == 0)
		 {
		 angular.forEach($scope.crmDealDetail, function(value,key) {
				if($scope.crmDealDetail[key].crmDealId==$sessionStorage.crmDealId)
					{
						if($scope.crmDealDetail[key].industry==1)
					 	{
					 		$scope.frmDeal.ddlIndustryModel=1;
					 	}
					 	else if($scope.crmDealDetail[key].industry==0)
						 {
						 	$scope.frmDeal.ddlIndustryModel=0;
						 } else {
							 $scope.frmDeal.ddlIndustryModel=2;
						 }
						}
			 });
		 	
		 }
	else
		 {
		 	if($scope.frmDeal.ddlIndustryModel=='IT')
		 	{
		 		$scope.frmDeal.ddlIndustryModel=1;
		 	}
		 	else if($scope.frmDeal.ddlIndustryModel=='KPO')
			 {
			 	$scope.frmDeal.ddlIndustryModel=0;
			 } else {
				 $scope.frmDeal.ddlIndustryModel=2;
			 }
		 }
		
	$scope.getCustomerVerticalFP($scope.frmDeal.customerNameModel);
		
	var date= $scope.frmDeal.dealStartDateModel; 	
	var d=new Date(date.split("/").reverse().join("-"));
	var dd=d.getDate();
	var mm=d.getMonth()+1;
	var yy=d.getFullYear();
	var deal_start_dt=$scope.dealStart;
	
	var date2= $scope.frmDeal.dealEndDateModel; 	
	var d=new Date(date2.split("/").reverse().join("-"));
	var dd=d.getDate();
	var mm=d.getMonth()+1;
	var yy=d.getFullYear();
	var deal_end_dt=$scope.dealEnd;
	
	if(typeof $scope.frmDeal.dealNewOrRenewalModel == 'number')
		{
		
		}
	else
		{
		 	$scope.frmDeal.dealNewOrRenewalModel=1;
		 	$scope.frmDeal.projectIdModel=0;
		 	$scope.frmDeal.oldDealIdModel='';
		}
		if($scope.frmDeal.ddlLOBModel == null)
		{
			$scope.frmDeal.ddlLOBModel=1
		}
	
	
	
	var markers = {
			"crmDealId":$scope.frmDeal.DealModel,   //.crmDealId,
			"dealVersion":$scope.frmDeal.VersionNameModel,
			"rpDealVersionId":$scope.frmDeal.versionModel,
			"customerVerticalMapId":$sessionStorage.custmVerticalMapId,
			"currencyId":$scope.frmDeal.DealModel.currencyId,
			"agileProject":$scope.frmDeal.isAgileBasedModel,
			"syntelOnsieFacilityUsed":$scope.frmDeal.onsiteFacilityModel,
			"hcIncluded":$scope.frmDeal.isIncludeInHeadCount,
			"isNewDeal":$scope.frmDeal.dealNewOrRenewalModel,//"newDeal":$scope.frmDeal.dealNewOrRenewalModel,
			"bizOpsInvolved":$scope.frmDeal.isBizopsModel,
			"oldDealId":$scope.frmDeal.oldDealIdModel,
			"projectId":$scope.frmDeal.projectIdModel,
			"dealTypeId":2,
			"fpProjectTypeId":$scope.frmDeal.fpTypeModel,
			"riskCategoryId":$scope.frmDeal.riskCategoryModel,
			"dealProgressStatusId":$scope.frmDeal.DealModel.dealStatusId,
			"projectStartDate":deal_start_dt,
			"projectEndDate":deal_end_dt,
			"dealTcv":$scope.frmDeal.dealTCVModel,
			"onsiteHours":$scope.frmDeal.onsiteHoursModel,
			"offShoreHours":$scope.frmDeal.offShoreHoursModel,
			"projectLobId":null,
			"penaltyPercentage":$scope.frmDeal.penaltyPercent,
			"projectIndustry":$scope.frmDeal.ddlIndustryModel,
			"isManualDeal":isDealTypeManual,
			"isVersionFinalized":"1",
			"currentApprovalStatus":1,
			"pageTrackerStatus":0,
			"verticalId" : $sessionStorage.verticalId,
			"customerId" : $scope.frmDeal.customerNameModel,
			"cyberSecurity": $scope.frmDeal.securitymodel,
			"industrySolution": $scope.frmDeal.solutionModel,
			"isSynbots": $scope.frmDeal.synbotsModel,
			"synergyType":$scope.frmDeal.synergyModel,

			"riskBasedAnswers":[{
		        "questionId":"1",
		        "riskAnswer":$scope.frmDeal.riskAns1Model
		    },
		    {
		        "questionId":"2",
		        "riskAnswer":$scope.frmDeal.riskAns2Model
		    },
		    {
		        "questionId":"3",
		        "riskAnswer":$scope.frmDeal.riskAns3Model
		    },
		    {
		        "questionId":"4",
		        "riskAnswer":$scope.frmDeal.riskAns4Model
		    },
		    {
		        "questionId":"5",
		        "riskAnswer":$scope.frmDeal.riskAns5Model
		    },
		    {
		        "questionId":"6",
		        "riskAnswer":$scope.frmDeal.riskAns6Model
		    },
		    {
		        "questionId":"7",
		        "riskAnswer":$scope.frmDeal.riskAns7Model
		    },
		    {
		        "questionId":"8",
		        "riskAnswer":$scope.frmDeal.riskAns8Model
		    },
		    {
		        "questionId":"9",
		        "riskAnswer":$scope.frmDeal.riskAns9Model
		    }]
	};
	var formData = new FormData();
	for (var i = 0; i < fileTest.length; i++) {
		var fileName = "file"+i.toString();
		console.log("fileName : "  + fileName);
		var file = $('input[type="file"]').get(i).files[0];
		if(file){
			formData.append('file', file);
			formData.append('name', name[i]);
			
		}
	}
	
	formData.append('jsonData', angular.toJson(markers));
	var insertDealData = function(response) {
		if(response.status == 200){
			BootstrapDialog.show({
	        	title : 'Version Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'new version created sucessfully',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			$scope.latestRPversion = response.data;	
	        			
	        			if(typeof latestRPversion != 'number') 
	        			{
	        				$localStorage.rpDealVersionId = parseInt($scope.latestRPversion);
	    				}
	        			else
        				{
        					$localStorage.rpDealVersionId= $scope.latestRPversion;
        				}
	        			
	        			sessionStorage.removeItem('accountId');
						sessionStorage.removeItem('crmDealId');
						sessionStorage.removeItem('dashboardData');
	        			
	        			$scope.insertDlCurrUTI();
	        			$scope.insertDlLoc();
	        			$scope.getVersion($localStorage.DealModel); //calling for reloading updated version id
	        			location.reload ();  //calling for reloading updated version id
	        			//{
	        				/*$scope.frmDeal.customerNameModel =  50022;
	        		    	$scope.getDealDetails($scope.frmDeal.customerNameModel);	        			 
	        		    	$scope.frmDeal.DealModel = $localStorage.DealModel;
	        		    	$scope.getVersion($scope.frmDeal.DealModel);*/
	        				
	        			//};	        	
	        			//$window.onload();
	        		//	window.location = "MasterCountryForex";
	        		}
	        	}]
	        });
			
			
		}
		else{
			BootstrapDialog.show({
	        	title : 'TM Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Currently we are facing technical issue. Please try again later.',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        		//	window.location = "MasterCountryForex";
	        		}
	        	}]
	        });
		}
	}
	
	WebServiceFactory.insertDealData(formData).then(insertDealData);
	
	/*$scope.insertDlCurrUTI();
	$scope.insertDlLoc();*/
}



$scope.update=false;
$scope.checkUpdateDealData = function(frmDeal)
{
	$scope.update= true;	
	
	if(frmDeal.dealNewOrRenewalModel == 1)
	{
		$scope.frmDeal.oldDealIdModel=0;
	    $scope.frmDeal.projectIdModel=0;
	    if(frmDeal.$valid)
		{//chk form validation valid or not
			$scope.update= false;	
			$scope.updateDealData();
		}
		
	}
	else
	{
		/*if(typeof $scope.frmDeal.projectIdModel != 'number' || typeof $scope.frmDeal.oldDealIdModel  != 'number') 
		{*/
			$scope.frmDeal.projectIdModel = parseInt($scope.frmDeal.projectIdModel);
			$scope.frmDeal.oldDealIdModel = parseInt($scope.frmDeal.oldDealIdModel);
		//}
		
		if(typeof $scope.frmDeal.oldDealIdModel == 'number' && typeof $scope.frmDeal.projectIdModel == 'number' &&  $scope.frmDeal.oldDealIdModel > 0 && $scope.frmDeal.projectIdModel >=0 )
		{
			if(frmDeal.$valid)
			{//chk form validation valid or not
				$scope.update= false;	
				$scope.updateDealData();
			}				
		}
		
		else
		{
			BootstrapDialog.show({
	        	title : 'T&M Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Kindly Enter Old Deal Id & old Project Id with proper format',
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

$scope.updateDealData = function(){
	
	var fileTest = $("#fuUploadManualDeal,#fuAttachment_1,#fuAttachment_2");
	var name = ["MANUAL_DEAL", "ATTACHMENT_ID1","ATTACHMENT_ID2"];
	var isDealTypeManual = $scope.frmDeal.isManualDeal;
	if(isDealTypeManual == true){
		isDealTypeManual = 1;
	}
	else{
		isDealTypeManual = 0;
	}
	
	for(var i = 0;i<$scope.customer.length;i++) {
		if($scope.customer[i].customerId == $scope.frmDeal.customerNameModel) {
			$scope.verticalId = $scope.customer[i].verticalId;
			console.log("The Veritcal Id is............. "+ $scope.verticalId);
		}
	}
	
	$scope.getCustomerVerticalFP($scope.frmDeal.customerNameModel);
	
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
			"rpDealVersionId":$scope.frmDeal.versionModel, //.rpDealVersionId,
			"crmDealId":$scope.frmDeal.DealModel, //.crmDealId,
			"customerVerticalMapId":$sessionStorage.custmVerticalMapId,
			"dealVersion":$scope.frmDeal.versionModel, //.dealVersion,
			"currencyId":$scope.frmDeal.DealModel.currencyId,
			"agileProject":$scope.frmDeal.isAgileBasedModel,
			"syntelOnsieFacilityUsed":$scope.frmDeal.onsiteFacilityModel,
			"hcIncluded":$scope.frmDeal.isIncludeInHeadCount,
			"isNewDeal":$scope.frmDeal.dealNewOrRenewalModel, //"newDeal":$scope.frmDeal.dealNewOrRenewalModel,
			"bizOpsInvolved":$scope.frmDeal.isBizopsModel,
			"oldDealId":$scope.frmDeal.oldDealIdModel,
			"projectId":$scope.frmDeal.projectIdModel,
			"dealTypeId":2,//since it is TM price
			"fpProjectTypeId":$scope.frmDeal.fpTypeModel,
			"riskCategoryId":$scope.frmDeal.riskCategoryModel,
			"dealProgressStatusId":$scope.frmDeal.DealModel.dealStatusId,
			"projectStartDate":deal_start_dt,
			"projectEndDate":deal_end_dt,
			"dealTcv":$scope.frmDeal.dealTCVModel,
			"onsiteHours":$scope.frmDeal.onsiteHoursModel,
			"offShoreHours":$scope.frmDeal.offShoreHoursModel,
			"projectLobId":null,
			"projectIndustry":$scope.frmDeal.ddlIndustryModel,
			"isVersionFinalized":"1",
			"penaltyPercentage":$scope.frmDeal.penaltyPercent,							
			"isManualDeal":isDealTypeManual,
			"currentApprovalStatus":1,
			"pageTrackerStatus":1,
			"statusIndicator":"Draft",
			"customerId" : $scope.frmDeal.customerNameModel,
			"verticalId" : $sessionStorage.verticalId,
			"manualDealObjectId":manualDealAttachment,
			"attachment1ObjectId":attachment1,
			"attachment2ObjectId":attachment2,
			"cyberSecurity": $scope.frmDeal.securitymodel,
			"industrySolution": $scope.frmDeal.solutionModel,
			"isSynbots": $scope.frmDeal.synbotsModel,
			"synergyType":$scope.frmDeal.synergyModel,
			
			"riskBasedAnswers":[{
		        "questionId":"1",
		        "riskAnswer":$scope.frmDeal.riskAns1Model,
		    },
		    {
		        "questionId":"2",
		        "riskAnswer":$scope.frmDeal.riskAns2Model,
		    },
		    {
		       "questionId":"3",
		        "riskAnswer":$scope.frmDeal.riskAns3Model,
		    },
		    {
		     "questionId":"4",
		        "riskAnswer":$scope.frmDeal.riskAns4Model,
		    },
		    {
		        "questionId":"5",
		        "riskAnswer":$scope.frmDeal.riskAns5Model,
		    },
		    {
		        "questionId":"6",
		        "riskAnswer":$scope.frmDeal.riskAns6Model,
		    },
		    {
		        "questionId":"7",
		        "riskAnswer":$scope.frmDeal.riskAns7Model,
		    },
		    {
		       "questionId":"8",
		        "riskAnswer":$scope.frmDeal.riskAns8Model,
		    },
		    {
		       "questionId":"9",
		        "riskAnswer":$scope.frmDeal.riskAns9Model,
		    }]
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
	var updateDealData = function(response) {
		console.log("update deal data response"+response.data);
	
		if(response.status == 200){
			BootstrapDialog.show({
	        	title : 'T&M Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Data updated SucessFully',
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
		else{
			BootstrapDialog.show({
	        	title : 'T&M Deal Creation',
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
	WebServiceFactory.updateDealData(formData).then(updateDealData);
	
	$scope.insertDlCurrUTI();
	$scope.insertDlLoc();	
}


$scope.insertDlCurrUTI = function()
{
	
	if($localStorage.rpDealVersionId != undefined && $scope.frmDeal.countryNameModel != undefined) 
		{
			var insertDlCurrUTI = function(response) 
			{	
				
			}
		
			var markers2 = 
			{
				//"dealVersionId":$scope.frmDeal.versionModel, //.rpDealVersionId,
				"dealVersionId":$localStorage.rpDealVersionId,
				"baseCountryId":$scope.frmDeal.countryNameModel
			};
			var formData2 = new FormData();
			formData2.append('jsonData', angular.toJson(markers2));
			WebServiceFactory.insertDlCurrUTI(formData2).then(insertDlCurrUTI);
		
		}
	
}

$scope.insertDlLoc = function()
{
	if( $localStorage.rpDealVersionId != undefined && $scope.frmDeal.countryNameModel != undefined)
	{
			var insertDlLoc = function(response)
			{
			}				
			var markers3 = 
			{
				//"dealVersionId":$scope.frmDeal.versionModel, //.rpDealVersionId,
				"dealVersionId":$localStorage.rpDealVersionId,
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
            var date1 = new Date(startdateVal);
            var date2 = new Date(enddateVal);
            var timeDiff = Math.abs(date2.getTime() - date1.getTime());
            $scope.dayDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));
            $scope.frmDeal.durationModel = Math.round((($scope.dayDifference/365)*100))/100;
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
		"versionId" : $localStorage.rpDealVersionId
		};
var getTMAttachementData = function(response) {
	$scope.AttachementData=response.data;	
	};
	WebServiceFactory.getTMAttachementData(markers).then(getTMAttachementData);
	
	$scope.deleteFile = function(objectid,category){
		WebServiceFactory.changeActiveStatus(objectid);
		$window.location.reload();
	};		
	
	$scope.uploadTMAttachmentData = function(frmDeal){		
		var file = $('input[name="fuAttachFilename"]').get(0).files[0]
			$scope.upload= false;
			if(file != undefined ) {
				var category="A";
				$scope.uploadTMFileData(category,file);
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
	$scope.uploadTMFileData=function(category,file){
		var name = "Template";
		var tempID = 1;	
		var versionId = $scope.frmDeal.versionModel;	
		var category=category;
		var formData = new FormData();
		formData.append('file', file);
		formData.append('name', name);
		formData.append('TempId', tempID);
		formData.append('versionId', $localStorage.rpDealVersionId);
		formData.append('category',category);
		var uploadUrl = contextPath+"/RightPrice-DAS/uploadTMFileData";
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
				BootstrapDialog.show({
					title : 'FP Deal Creation - Details',
					type : BootstrapDialog.TYPE_PRIMARY,
					message : customMessage,
					closable : false,
					buttons : [ {
						label : 'OK',
						action :function(
								dialogRef) {
							dialogRef.close();
						}
					} ]
				});	
				
				$scope.searchFile(versionId);
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
	
	
	$scope.searchFile = function(rpDealVersionId)
	{
		angular.element("input[type='file']").val(null);
		console.log("inside File Search---"+rpDealVersionId);

		var markers = {
				"versionId" : $localStorage.rpDealVersionId
				};
		$http({
		    method: 'POST',
		    url: contextPath+'/RightPrice-DAS/getTMAttachementData',
		    dataType: 'json',
		    data: JSON.stringify(markers),  
	     headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    }).
			    then(function(data) {
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
							if($scope.tempo[i].category=="A")
							{
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

$scope.downloadFile = function(objectId){
	//alert('hi'+objectId);
	WebServiceFactory.downloadFileFormID(objectId);
};

var getDealDetails = function(response) {
	if($localStorage.DealModel != undefined && $localStorage.rpDealVersionId != undefined )
		{
			console.log(response);
			$scope.dealDetails = response.data;
			if($scope.dealDetails != undefined && $localStorage.rpDealVersionId != undefined )
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
					$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	
					$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;		
				    var startDay = new Date(dateStart);
				     var endDay = new Date(dateENd);
				     var millisecondsPerDay = 1000 * 60 * 60 * 24;
				
				     var millisBetween =  endDay.getTime()-startDay.getTime() 
				     var days = millisBetween / millisecondsPerDay;
				     $scope.dealDetails[0].DealDuration = Math.floor(days);  
				     
				    
			
			}
		
			WebServiceFactory.getDealDetails($localStorage.DealModel,$localStorage.rpDealVersionId).then(getDealDetails);//WebServiceFactory.getDealDetails($sessionStorage.crmDealId).then(getDealDetails);   	
		}		
		
		else
		{
		
		}
		
};

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