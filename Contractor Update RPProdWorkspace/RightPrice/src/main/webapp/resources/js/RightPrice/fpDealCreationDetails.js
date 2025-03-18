"use strict";
//var app = angular.module('RightPrice', ['ngMessages', 'ngStorage']);
		app.controller("CreateDealDetailsController", ['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory,$localStorage, $sessionStorage,$index){
			 var contextPath = "/RightPrice-DAS";		
			$scope.UploadHidden = true; 
			$scope.UploadHiddenQuest=true;
			$scope.AttachmentHidden = true; 
			$scope.UploadHiddenDevQuest=true;
			$scope.UploadHiddenMainQuest= true;
			$scope.showdevquest=false;
			$scope.showmainquest=false;
			$scope.synbotquestions = false;
			$scope.Renewal=false;
			$scope.dealdevans = [];
			$scope.dealmainans= [];
			$scope.AttachementData=[];
			$scope.devAttachementData=[];
			$scope.mainAttachementData=[];
			$scope.MDAttachementData=[];
			$scope.attachmentTableHide=false;
			$scope.uploadTableHide=false;
			$scope.mainTableHide=false;
			$scope.devTableHide=false;
			$scope.alertDev=false;
			$scope.alertMain=false;
			$scope.maintainHide=true;
			$scope.showstaffing=false;
			$scope.fpdealnormal=false;
			$scope.isRCPricing=false;
			$scope.fpdealmanual=false;
			$scope.isFPDealGFT = false;
			$scope.isFPDealRiskManagers = false;
			$scope.isDevelopment=false;
			$scope.dealType = "" ;
			$scope.hidevolumetxt = true;
			 $scope.isUpdate = false;

			
			$scope.viewmode=false;
			var currencyId=0;
			var crmdlLen=0;
			var yearDiff = 0;
		    var monthDiff = 0;
			var customerId=null;
			var dealId=null;
			var crmDealId=null;
			$scope.dealStart = null;
			$scope.dealEnd = null;
			$scope.isCurrStatus=false;
			
			$scope.onshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
			$scope.offshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
			var userType = sessionStorage.getItem('userType');
			 console.log("The USer Value from the Session is........ "+ userType);
			 if(userType == 'GFT') {
				 $scope.isFPDealGFT = true;
			 } 
			 else if(userType == 'RiskManagers') {
					$scope.isFPDealRiskManagers = true;
				}
			 
			
			
			$scope.ShowHideUpload = function () {
			     $scope.UploadHidden = $scope.UploadHidden ? false : true;
			 };
			 
			 $scope.ShowHideUploadQuest = function () {
					 $scope.UploadHiddenQuest = $scope.UploadHiddenQuest ? false : true;
					
			 };
			 
			 
			 $scope.ShowHideAttachment = function () {
			     $scope.AttachmentHidden = $scope.AttachmentHidden ? false : true;
			 };
			$scope.Next = function()
		    {  
				
				
				if(($localStorage.CurrentApprovalStatus == null || $localStorage.CurrentApprovalStatus == 1 ||
						$localStorage.CurrentApprovalStatus == 4 ) && $localStorage.pageTracker==0)
					{	BootstrapDialog.show({
						title : 'FP Deal Creation - Details',
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
					console.log($scope.frmDeal.DealModel);
					$sessionStorage.dealId = $scope.frmDeal.DealModel;
					$localStorage.isManualDealFlag=$scope.frmDeal.fdmanualdealModel;
					$sessionStorage.customerId=$scope.frmDeal.customerNameModel;
			        window.location='FPDealCreationRateCardAndProjectDetails';
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
				$scope.frmDeal.DealModel = null;
				$scope.versionDetail = null;
				$scope.frmDeal.versionModel = null;
				$scope.frmDeal.txtOldDealId = null;
				$scope.frmDeal.txtProjectId = null;
				$scope.frmDeal.fuUploadFilenameModel = null;
                $scope.frmDeal.fuAttachFilenameModel = null;
                $scope.tableHide=true;
			}
			
			$scope.showDealDetails = function(crmDealId){
				var crmDealId=$scope.frmDeal.oldDealModel;
				console.log("showDealDetails");
				console.log(crmDealId);
				$localStorage.DealModel = crmDealId;
				$scope.viewmode = true;
				var browser = window.navigator.appVersion;

	              //Workaround to enable the users to download the report in IE.
	              if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
	                     (browser.indexOf('MSIE 10') !== -1)) 
	              {
	            	  window.open ("FPDealCreationDetails","$localStorage.DealModel");
//	            	  window.open ("RightPrice/FPDealCreationDetails","$localStorage.DealModel");
	              } 
	              else 
	              {
	            	  window.open ("FPDealCreationDetails","$localStorage.DealModel");
//	            	  window.open ("RightPrice/FPDealCreationDetails","$localStorage.DealModel");
	              }
			/*	window.location = "RightPrice/FPDealCreationDetails";*/
			};
			
			var temp;	
			$window.onload = function() 
			{		
				temp=0;
				$scope.clearData();
				var rpDealVersionId = $localStorage.rpDealVersionId;
				console.log("rpDealVersionId "+rpDealVersionId);
				var crmDealId=$localStorage.DealModel;
				var customerId=$sessionStorage.customerId;
				
				if(userType == 'GFT' || userType == 'CEO' || userType == 'CDO' || userType=='RiskManagers' 
					|| userType=='Audit' || userType == 'LEVELl1User' || userType == 'LEVELl2User')
				{
					var getGFTCustomer = function(response) {
						console.log("customer response");
						//console.log(response.data);
						$sessionStorage.customer = response.data;
						console.log("customer::::::::::::::::::");
						console.log(response.data);
					};
					WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
				}
				else if(userType == 'BUH' || userType == 'DUH')
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
						console.log("customer response");
						//console.log(response.data);
						$sessionStorage.customer = response.data;
						console.log("customer::::::::::::::::::");
						console.log(response.data);
					};
					WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);*/
				}
				else
			 	{
			 		var getCustomerForUser = function(response) {
			 			console.log("customer response");
			 			//console.log(response.data);
			 			$sessionStorage.customer = response.data;
			 			console.log("customer::::::::::::::::::");
			 			console.log(response.data);
			 		};
			 		WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
			 	}
				
				
				if(typeof rpDealVersionId != 'number') {
					rpDealVersionId = parseInt(rpDealVersionId);
				}
				if(sessionStorage.getItem('dashboardData') == 1)
				{
					sessionStorage.removeItem('dashboardData')
					var CheckUserAccess = function(resp){
					
						if(resp.status == 205)	
						{
							BootstrapDialog.show({
					        	title : 'FP Deal Creation',
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
			$scope.getDealDetails($scope.frmDeal.customerNameModel)
						
			var getDealsFrmCrmStages = function(response) {
				 console.log("deal Query frm stages");
				 console.log(response);
				 $scope.crmDealDetail = response.data;
				 var custmIdforVertical=$scope.crmDealDetail[0].customerId;
				 $scope.getCustomerVerticalFP(custmIdforVertical);
				 $scope.crmOldDealDetail = angular.copy($scope.crmDealDetail);
				  angular.forEach($scope.crmOldDealDetail, function(value,key) {
					 if($scope.frmDeal.DealModel == $scope.crmOldDealDetail[key].crmDealId) {
						 $scope.crmOldDealDetail.splice(key, 1);
					 }
				 });
				 
				$scope.getVersion(sessionStorage.getItem('crmDealId'))   
		 };
		 WebServiceFactory.getDealsFrmCrmStages($scope.frmDeal.customerNameModel,1).then(getDealsFrmCrmStages);
						}
					}
					WebServiceFactory.CheckUserAccess(parseInt(sessionStorage.getItem('accountId'))).then(CheckUserAccess)
					
				
				}
				else{
				if(typeof rpDealVersionId == 'number')  //if(isNaN(rpDealVersionId)) //typeof num1 == 'number'
				{
					temp=10;			
					/*$scope.getPrevDataDeal_Version(rpDealVersionId);*/	
					$scope.getDealDetails(customerId);
					/*$scope.getVersion(crmDealId);*/
					$scope.putVersion($localStorage.rpDealVersionId);
				}
				}
			};
	
$scope.dealTypeModel = "Fixed Price";	
var rpDealVersionId = $localStorage.rpDealVersionId;

var manualDealAttachment, attachment1, attachment2;

$scope.isVersionAvailable = false;		
$scope.isReadytoSubmit =false;
//$scope.dealType = [{ name: "Fixed Price", id: 1 }, { name: "T & M", id: 2 }];
$scope.fpType = [{ name: "Development - Fixed Price", id: 1 }, { name: "Development - Manage Capacity", id: 2 }, { name: "Maintenance - Fixed Price", id: 3 }, { name: "Maintenance - Manage Capacity", id: 4 }];
$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }, { name: "RFP/RFI", id: 2 }];
$scope.isAgileBased = [{ name: "Yes", id: 1 }, { name: "No", id: 0 }];
$scope.isSyntelOnsiteFacilityUsed = [{ name: "Client", id: 1 }, { name: "Syntel", id: 0 }];
$scope.riskCategoryId = [{ name: "A", id: 1 }, { name: "B", id: 2 }, { name: "C", id: 3 }, { name: "D", id: 4 }];

$scope.selectManualDeal = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
$scope.onshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
$scope.offshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
$scope.workingDays=[{dayId:1, name: 20},{dayId:2, name: 21},{dayId:3, name: 21.67},{dayId:4, name: 22}];

$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
$scope.dealTMSynergy  = [{ name: "Cross sell", id: "Cross_sell" }, { name: "Normal", id: "Normal" }];
$scope.volumeArray = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
$scope.volumeArray = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
$scope.OffshoreRiskperc=[{riskid:1,name:0.25},{riskid:2,name:0.5},{riskid:3,name:0.75},{riskid:4,name:1.0},{riskid:5,name:1.25},{riskid:6,name:1.5},{riskid:7,name:1.75},{riskid:8,name:2.0},
	{riskid:9,name:2.25},{riskid:10,name:2.5},{riskid:11,name:2.75},{riskid:12,name:3.0},{riskid:13,name:3.25},{riskid:14,name:3.5},
	{riskid:15,name:3.75},{riskid:16,name:4.0},{riskid:17,name:4.25},{riskid:18,name:4.5},{riskid:19,name:4.75},{riskid:20,name:5.0}];


$scope.checkfpType = function (fpTypeValue) {
	if(fpTypeValue == 1 || fpTypeValue == 2) {
		$scope.showdevquest=true;
		$scope.showmainquest=false;
		$scope.ShowHideUploadDevQuest = function () {
		     $scope.UploadHiddenDevQuest = $scope.UploadHiddenDevQuest ? false : true;
		 };
	}
	else
		{
		$scope.showdevquest=false;
		$scope.showmainquest=true;
		 $scope.ShowHideUploadMainQuest = function () {
		        $scope.UploadHiddenMainQuest =$scope.UploadHiddenMainQuest ? false : true;
		 };
		}
}

$scope.checkfpManualDealType = function (fpManualDealTypeValue){
	if(fpManualDealTypeValue == 1){
		$scope.fpdealnormal=false;
		$scope.fpdealmanual=true;
	}
	else
		{
		$scope.fpdealnormal=true;
		$scope.fpdealmanual=false;
		}
}


$scope.checkSynbotQues = function (typecheck){
	if(typecheck)
		{
		$scope.synbotquestions = true;
		}
	else
		$scope.synbotquestions = false;
	
}

	if(userType == 'GFT' || userType == 'CEO' || userType == 'CDO' || userType=='RiskManagers' 
		|| userType=='Audit' || userType == 'LEVELl1User' || userType == 'LEVELl2User')
	{
		var getGFTCustomer = function(response) {
			console.log("customer response");
			//console.log(response.data);
			$scope.customer = response.data;
			console.log("customer::::::::::::::::::");
			console.log(response.data);
		};
		WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
	}
	else if(userType == 'BUH' || userType == 'DUH')
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
			console.log("customer response");
			//console.log(response.data);
			$scope.customer = response.data;
			console.log("customer::::::::::::::::::");
			console.log(response.data);
		};
		WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);*/
	}
	else
 	{
 		var getCustomerForUser = function(response) {
 			console.log("customer response");
 			//console.log(response.data);
 			$scope.customer = response.data;
 			console.log("customer::::::::::::::::::");
 			console.log(response.data);
 		};
 		WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
 	}
	
/* var getCustomerByVerticalGroupId = function(response) {
	    $scope.customer = response.data;
	    console.log(response);
	    console.log($scope.customer);
	};
	//API call for getCurrentUser
 WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);*/
 
 var getLobDetails = function(response) {
		console.log(response);
		$scope.masterLob = response.data;
		//$scope.masterLob.isActive= "Active";
		/*$scope.lengthArr = response.data.length
		console.log($scope.lengthArr);
		for(var i =0; i <$scope.lengthArr; i++ ){
			$scope.masterLob[i].isActive = ($scope.masterLob[i].isActive)? "Active":"InActive";
		}*/
};

WebServiceFactory.getLobDetails().then(getLobDetails);


 
var getDealQuest = function(response) {
	console.log("Deal Questions");
    $scope.quest = response.data;
    console.log($scope.quest);
};
WebServiceFactory.getDealQuest().then(getDealQuest);	
var markers = {
"versionId" : $localStorage.rpDealVersionId 
};

var getAttachement = function(response) {
$scope.alertDev=false;
$scope.alertMain=false;
$scope.attachmentTableHide=true;
$scope.uploadTableHide=true;
$scope.mainTableHide=true;
$scope.devTableHide=true;

$scope.frmDeal.dealTCVModel>=1000000?$scope.maintainHide=false:$scope.maintainHide=true;
console.log("=====================================================Attachment Data on Onload");
if(response.data.length!=0){
$scope.temp=[];
$scope.temp=response.data;
var j=0;
var k=0;
var l=0;
var m=0;
for(var i=0;i<$scope.temp.length;i++)
{
if($scope.temp[i].category=="M")
{
	$scope.MDAttachementData[j] = $scope.temp[i];
	var strMain = $scope.MDAttachementData[j].createdOn;
    var arrSplit = [];
    arrSplit = strMain.split(" ");
    $scope.MDAttachementData[j].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
	j++;
	$scope.uploadTableHide=false;
}	
else if($scope.temp[i].category=="A")
{
	$scope.AttachementData[k] = $scope.temp[i];
	var strMain = $scope.AttachementData[k].createdOn;
    var arrSplit = [];
    arrSplit = strMain.split(" ");
    $scope.AttachementData[k].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
k++;
$scope.attachmentTableHide=false;
}
else if($scope.temp[i].category=="FPD")		
{
	$scope.devAttachementData[l] = $scope.temp[i];
	var strMain = $scope.devAttachementData[l].createdOn;
    var arrSplit = [];
    arrSplit = strMain.split(" ");
    $scope.devAttachementData[l].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
l++;
$scope.devTableHide=false;
if($scope.devAttachementData.length>=1){
	$scope.alertDev=true;
}
}
else if($scope.temp[i].category=="FPM"){
	$scope.mainAttachementData[m] = $scope.temp[i];
	var strMain = $scope.mainAttachementData[m].createdOn;
    var arrSplit = [];
    arrSplit = strMain.split(" ");
    $scope.mainAttachementData[m].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
m++;
$scope.mainTableHide=false;
if($scope.mainAttachementData.length>=1){
	$scope.alertMain=true;
}
}
}
console.log("$scope.MDAttachementData onload")
console.log($scope.MDAttachementData)
console.log("$scope.AttachementData onload")
console.log($scope.AttachementData)
console.log("$scope.devAttachementData onload")
console.log($scope.devAttachementData)
console.log("$scope.mainAttachementData onload")
console.log($scope.mainAttachementData)
}
else{
$scope.tableHide=true;
}
};
WebServiceFactory.getAttachement(markers).then(getAttachement);


$scope.exportToExcelDevelopmentQuestionnaire = function(tableId) {
	
	var exportHref=WebServiceFactory.exportToExcelDevelopmentQuestionnaire(tableId,'Deal_Creation_DevQue_Details');
  
};


$scope.exportToExcelMainQue = function(tableId) {
	
	var exportHref=WebServiceFactory.exportToExcelDevelopmentQuestionnaire(tableId,'Deal_Creation_MenQue_Details');
  
};


$scope.exportToExcelAutoQue = function(tableId) {
	
	var exportHref=WebServiceFactory.exportToExcelDevelopmentQuestionnaire(tableId,'Deal_Creation_AutoQue_Details');
  
};


 //get deals on customer change
 $scope.getDealDetails = function(customerId){
	 $scope.clearData();
	 var dealTypeForFM=1;
	 if(customerId != undefined) {
		 var getDealsFrmCrmStages = function(response) {
			 console.log("deal Query frm stages");
			 //alert("frm stages");
			 console.log(response);
			 
			 $scope.crmDealDetail = response.data;
			 var custmIdforVertical=$scope.crmDealDetail[0].customerId;
			 $scope.getCustomerVerticalFP(custmIdforVertical);
			 // Performing Deep Copy in angularjs
			 
			 /*angular.forEach($sessionStorage.customer, function(value,key) {
				if($sessionStorage.customer[key].customerId == customerId) {
					$sessionStorage.verticalId = $sessionStorage.customer[key].verticalId;
					$scope.customerVerticalMapId=$sessionStorage.customer[key].customerVerticalMapId;

				} 
			 });*/
			 
			 $scope.crmOldDealDetail = angular.copy($scope.crmDealDetail);
			 
			 
			  angular.forEach($scope.crmOldDealDetail, function(value,key) {
				 if($scope.frmDeal.DealModel == $scope.crmOldDealDetail[key].crmDealId) {
					 $scope.crmOldDealDetail.splice(key, 1);
				 }
			 });
			 
		 };
		 WebServiceFactory.getDealsFrmCrmStages(customerId,dealTypeForFM).then(getDealsFrmCrmStages);
		 
		 var getDealsForCust = function(response) {
			 console.log("Deal Id's");
			 //alert("frm stages");
			 console.log(response);
			 $scope.crmDealDetailCust = response.data;
			 
		 };
		 WebServiceFactory.getDealsForCust(customerId,dealTypeForFM).then(getDealsForCust);
		 
		 
	 }
};


$scope.getOldDescription = function(crmDealId)
{
	angular.forEach($scope.crmOldDealDetails,function(value,key) {
		if($scope.crmOldDealDetails[key].crmDealId == $scope.frmDeal.oldDealModel) {
			$scope.frmDeal.descriptionOldModel= $scope.crmOldDealDetails[key].dealDescription;
		}
	});
	//$scope.frmDeal.descriptionOldModel= $scope.crmDealDetail[j].dealDescription;
};
	
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

$scope.getVersion = function(crmDealId){
//	alert('crmDealId='+crmDealId);
	$localStorage.DealModel= crmDealId; //storing session value for next page by arvind
	
	//alert(' 127fun para getVersion crmDealId1==='+crmDealId+'\n $localStorage.DealModel getVersion crmDealId1==='+$localStorage.DealModel);
	$scope.clearData();
	if(crmDealId!=undefined){
		
		
	var getVersionDetails = function(response)  //service will be call from this line
	{
		
		if($scope.frmDeal.DealModel != undefined)
		{
			$localStorage.DealModel = $scope.frmDeal.DealModel; //$scope.frmDeal.DealModel.crmDealId;
		}
		else
		{			
			$localStorage.DealModel=crmDealId;		
		}
		console.log("Version details");
		console.log(response);
		
		//$scope.clearData();
		
		$scope.versionDetail = response.data;
		$sessionStorage.customerId=$scope.frmDeal.customerNameModel;	
		if(response.data != ""){
		if($scope.getVersionData[0].cyberSecurity != null || $scope.getVersionData[0].cyberSecurity != undefined ){
			if($scope.getVersionData[0].cyberSecurity.trim()!=""){
				$scope.frmDeal.securitymodel=$scope.getVersionData[0].cyberSecurity;
			}
			else{
				delete $scope.dealTMcyberSecurit;
				$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
				}
			}
			else{
				delete $scope.dealTMcyberSecurit;
				$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
				}
			
			if($scope.getVersionData[0].industrySolution != null || $scope.getVersionData[0].industrySolution != undefined ){
			if($scope.getVersionData[0].industrySolution.trim()!=""){
				$scope.frmDeal.solutionModel=$scope.getVersionData[0].industrySolution;
			}
			else{
				delete $scope.dealTMsolution;
				$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
			}
			}
			else{
				delete $scope.dealTMsolution;
				$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
			}
			
			if($scope.getVersionData[0].isSynbots != null || $scope.getVersionData[0].isSynbots != undefined ){
			if($scope.getVersionData[0].isSynbots.trim()!=""){
				$scope.frmDeal.synbotsModel=$scope.getVersionData[0].isSynbots;
			}
			else{
				delete $scope.dealTMsynboots;
				$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
			}
			}		else{
				delete $scope.dealTMsynboots;
				$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
			}
			
			if($scope.getVersionData[0].synergyType != null || $scope.getVersionData[0].synergyType != undefined ){
			if($scope.getVersionData[0].synergyType.trim()!=""){
				
				$scope.frmDeal.synergyModel=$scope.getVersionData[0].synergyType.trim();
			}
			else{
				delete $scope.dealTMSynergy;
				$scope.dealTMSynergy  = [{ name: "Cross sell", id: "Cross_sell" }, { name: "Normal", id: "Normal" }]
			}
		}		else{
			delete $scope.dealTMsynboots;
			$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
		}
		}
		/*if($scope.getVersionData[0].ddlVolumeUpdateModel != null || $scope.getVersionData[0].ddlVolumeUpdateModel != undefined ){
			if($scope.getVersionData[0].ddlVolumeUpdateModel.trim()!=""){
				$scope.frmDeal.ddlVolumeUpdateModel=$scope.getVersionData[0].ddlVolumeUpdateModel;
			}
			else{
				delete $scope.volumeArray;
				$scope.volumeArray = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
				}
			}
		else{
			delete $scope.volumeArray;
			$scope.volumeArray = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
			}
*/
		angular.forEach($scope.customer,function(value,key){
			
			if($scope.customer[key].customerId==$sessionStorage.customerId)
				{
					$sessionStorage.volumeDiscount=value.customer.volumeDiscount;
					$scope.frmDeal.volumediscount = value.customer.volumeDiscount;
				}
		});
		
		/*if($scope.versionDetail!= "" && $scope.versionDetail!= undefined){
			
			$localStorage.pricingType=$scope.versionDetail[0].pricingType;
			
			if($localStorage.pricingType!=1)
			{
				$scope.isRCPricing=true;
			}
			else{
				$scope.isRCPricing=false;
			}
		}*/
		

		/*var dealStartDate  = $scope.versionDetail[0].dealcrmstagesdata2.dealStartDate;
  		var date = new Date(dealStartDate.substring(0,10));
  		var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
  		$scope.frmDeal.dealStartDateModel = dealStartDD;
	
  		var dealEndDate  = $scope.versionDetail[0].dealcrmstagesdata2.dealEndDate2;
  		var date = new Date(dealEndDate.substring(0,10));
  		var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
  		$scope.frmDeal.dealEndDateModel = dealEndDateDD;*/
  		
  		
  		/*
  		
  		var dealStartDate =  $scope.versionDetail[0].dealcrmstagesdata2.dealStartDate;	
    	var date = new Date(dealStartDate.substring(0,10));	    	
    	$scope.frmDeal.dealStartDateModel = $filter('date')(dealStartDate.substring(0,10),'dd/MM/yyyy');
    	
    	var dealEndDate=   $scope.versionDetail[0].dealcrmstagesdata2.dealEndDate2;
    	var date= new Date(dealEndDate.substring(0,10));	    	
    	$scope.frmDeal.dealEndDateModel =  $filter('date')(dealEndDate.substring(0,10),'dd/MM/yyyy');*/
    	
    	
		/*$scope.versionDetail[0].crmDealId;*/
		$scope.frmDeal.DealModel=$localStorage.DealModel;
		var lenOfVid =$scope.versionDetail.length;
		$sessionStorage.NoOfVersion=lenOfVid;
		console.log("Length");
		console.log(lenOfVid);//document.getElementById("ddlVersion").options.length;	
		//alert('lenght of version='+lenOfVid);
		if(lenOfVid <= 0)
			{
				$scope.getVersionName(frmDeal);				
			}		
	    if(response.status == 204){
	    	$scope.isVersionAvailable = true;	
	    	console.log("4---------------------");
	    	/*$scope.clearData();*/
	    	//alert('148 getVersion response.status == 204')
	    	$scope.putDealData();
	    }
	    
	    if(response.status != 204){
	    	$scope.frmDeal.customerNameModel=$scope.versionDetail[0].dealcrmstagesdata2.customer.customerId;
	    	if($scope.versionDetail[0].fpProjectTypeId==1 || $scope.versionDetail[0].fpProjectTypeId==2){
				$scope.isDevelopment=true;	
			}
			else{
				$scope.isDevelopment=false;
			}
	    	/*if($scope.versionDetail[0].dealcrmstagesdata2.fpType==1)
	    	{
	    		$scope.fpType =[{ name: "Maintenance - Fixed Price", id: 3 }, { name: "Maintenance - Manage Capacity", id: 4 }];
	    	}
	    	else
	    	{
	    		$scope.fpType =[{ name: "Development - Fixed Price", id: 1 }, { name: "Development - Manage Capacity", id: 2 }];
	    	}*/
	    }
	    
	    

	};
	WebServiceFactory.getVersionDetails(crmDealId).then(getVersionDetails);
	
	   $scope.crmOldDealDetail = angular.copy($scope.crmDealDetail);
	 angular.forEach($scope.crmOldDealDetail, function(value,key) {
	    	if($scope.frmDeal.DealModel == $scope.crmOldDealDetail[key].crmDealId) {
	    		 $scope.crmOldDealDetail.splice(key, 1);
	    	}
	    });
	}
	
	$scope.frmDeal.riskDevAnsModel = [];
	$scope.frmDeal.riskMainAnsModel = [];
	
	
}


$scope.putDealData = function(){
	
	//var num = document.getElementById("ddlDeal").value;
	//var selectedValueCRM= num.match(/\d+/g); //.map(Number);	
	var selectedValueCRM = $localStorage.DealModel;
	var crmdlLen=0;
	$scope.crmDealDetailArrays= $scope.crmDealDetail;
	crmdlLen = $scope.crmDealDetailArrays.length;	
	  var arr = [];
	  var j=0;
	  for(var i=0; i<crmdlLen ;i++)
	  {
		  var crmdlId= $scope.crmDealDetailArrays[i].crmDealId;		
		  arr.push(crmdlId);
		 	  
		  if(selectedValueCRM == arr[i])
			  {		  
			  	 j = arr.indexOf(arr[i]);
			  	// alert('inside j='+j);
			  	 break;
			  }			  
		 
	  }
	
	console.log("put deal data");
	console.log( $scope.crmDealDetailArrays[j]);
	//console.log($scope.crmDealDetail);
	//alert('$scope.frmDeal.DealModel.dealDescription=='+$scope.frmDeal.DealModel.descriptionModel);
	$scope.frmDeal.descriptionModel= $scope.crmDealDetailArrays[j].dealDescription;
	//alert('1==putDealData');
	$scope.frmDeal.addressModel=$scope.crmDealDetailArrays[j].industryName;
	//alert('$scope.frmDeal.DealModel.dealDescription=='+$scope.frmDeal.addressModel);
	$scope.frmDeal.salesSpocModel =  $scope.crmDealDetailArrays[j].salesSpoc; // $scope.frmDeal.DealModel.salesSpoc;
	$scope.frmDeal.dealStatusModel = $scope.crmDealDetailArrays[j].dealStatus.description;
	$scope.frmDeal.dealPriorityModel =  $scope.crmDealDetailArrays[j].dealPriority.description; //$scope.frmDeal.DealModel.dealPriority.description;
	 $scope.frmDeal.rbuTypeModel= $scope.crmDealDetailArrays[j].rbuProfitCenter;
	var dateStart =  $scope.crmDealDetailArrays[j].dealStartDate; //$scope.frmDeal.DealModel.dealStartDate ;	
	var date = new Date(dateStart.substring(0,10));
	$scope.frmDeal.dealStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');
	
	var dateEnd = $scope.crmDealDetailArrays[j].dealEndDate;//$scope.frmDeal.DealModel.dealEndDate ;
	//alert('dateEnd='+dateEnd);
	var date = new Date(dateEnd.substring(0,10));
	$scope.frmDeal.dealEndDateModel = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy');
	
	/*if($scope.crmDealDetailArrays[j].fpType==1)
	{
		$scope.fpType =[{ name: "Maintenance - Fixed Price", id: 3 }, { name: "Maintenance - Manage Capacity", id: 4 }];
		$scope.frmDeal.fpTypeModel=3;
	}
	else
	{
		$scope.fpType =[{ name: "Development - Fixed Price", id: 1 }, { name: "Development - Manage Capacity", id: 2 }];
		$scope.frmDeal.fpTypeModel=1;
	}*/
	
	//$scope.frmDeal.ddlLOBModel=1;
	$scope.calApplicableYears();
	$scope.frmDeal.currencyModel = $scope.crmDealDetailArrays[j].currency.currencyCode;//$scope.frmDeal.DealModel.currency.currencyCode;
	$scope.frmDeal.percentageCloseModel = $scope.crmDealDetailArrays[j].percentageClose;//$scope.frmDeal.DealModel.percentageClose;
	$scope.frmDeal.stageModel =$scope.crmDealDetailArrays[j].stage.description;// $scope.frmDeal.DealModel.stage.description;
	$scope.frmDeal.dealTCVModel = $scope.crmDealDetailArrays[j].revenue; //$scope.frmDeal.DealModel.revenue;
	 $scope.frmDeal.rbuTypeModel= $scope.crmDealDetailArrays[j].rbuProfitCenter;
	$scope.frmDeal.ddlIndustryModel = $scope.crmDealDetailArrays[j].industry; //$scope.frmDeal.DealModel.industry;	
	var dealStatus= $scope.crmDealDetailArrays[j].dealStatus.description;
	$scope.frmDeal.workingDaysModel = 1;
	$scope.frmDeal.dealNewOrRenewalModel = 1;
	angular.forEach($scope.crmDealDetailArrays,function(value,key)
			{
				$scope.crmDealDetailArrays[key].onsitehourModel=8;
				$scope.crmDealDetailArrays[key].offshorehourModel=8.5;
				$scope.crmDealDetailArrays[key].fdmanualdealModel="Yes";
			});
	
	for(var i=0;i< $scope.onshoreHours.length;i++) {
		if($scope.crmDealDetailArrays[j].onsitehourModel == $scope.onshoreHours[i].name) {
			$scope.frmDeal.onsiteHoursModel = $scope.onshoreHours[i].hourId;
		}
	}
	
	
	
	for(var i=0;i< $scope.offshoreHours.length;i++) {
		if($scope.crmDealDetailArrays[j].offshorehourModel == $scope.offshoreHours[i].name) {
			$scope.frmDeal.offShoreHoursModel = $scope.offshoreHours[i].hourId;
		}
	}
	
	for(var i=0;i< $scope.selectManualDeal.length;i++) {
		if($scope.crmDealDetailArrays[j].fdmanualdealModel == $scope.selectManualDeal[i].name) {
			$scope.frmDeal.fdmanualdealModel = $scope.selectManualDeal[i].Id;
		}
	}
	
	$scope.dealStart=$scope.crmDealDetailArrays[j].dealStartDate;

	$scope.dealEnd=$scope.crmDealDetailArrays[j].dealEndDate;

	var dealStartDate = $scope.dealStart;	
		var date = new Date(dealStartDate.substring(0,10));
		var dealStartDates = $filter('date')(date,'MM/dd/yyyy');
	$scope.startDateModel = dealStartDates;
	$scope.dealStart=$scope.startDateModel;
	var dealEndDate = $scope.dealEnd;
		var date = new Date(dealEndDate.substring(0,10));
		var dealEndDates = $filter('date')(date,'MM/dd/yyyy');
	$scope.endDateModel = dealEndDates;
	$scope.dealEnd=	$scope.endDateModel;
	if(dealStatus=='Close')
	{
		$scope.isDisabled = true;		
	
	}
	
	
	$scope.frmDeal.riskAns1Model = "";
	$scope.frmDeal.riskAns2Model = "";
	$scope.frmDeal.riskAns3Model = "";
	$scope.frmDeal.riskAns4Model = "";
	$scope.frmDeal.riskAns5Model = "";
	$scope.frmDeal.riskAns6Model = "";
	$scope.frmDeal.riskAns7Model = "";
	$scope.frmDeal.riskAns8Model = "";
	$scope.frmDeal.riskAns9Model = false;
	
	
	
	$scope.frmDeal.riskAns1Model = "";
	$scope.frmDeal.riskAns2Model = "";
	$scope.frmDeal.riskAns3Model = "";
	$scope.frmDeal.riskAns4Model = "";
	$scope.frmDeal.riskAns5Model = "";
	$scope.frmDeal.riskAns6Model = "";
	$scope.frmDeal.riskAns7Model = "";
	$scope.frmDeal.riskAns8Model = "";
	$scope.frmDeal.riskAns9Model = "";
	$scope.frmDeal.riskSynAns1Model = "";
	$scope.frmDeal.riskSynAns2Model = "";
	$scope.frmDeal.riskSynAns3Model = "";
	$scope.frmDeal.riskSynAns4Model = "";
	$scope.frmDeal.riskSynAns5Model = "";
	$scope.frmDeal.riskSynAns6Model = "";
	$scope.frmDeal.riskSynAns7Model = "";
	$scope.frmDeal.riskSynAns8Model = "";
	$scope.frmDeal.riskSynAns9aModel ="";
	$scope.frmDeal.riskSynAns9aModel  = "";
	$scope.frmDeal.riskSynAns9bModel= "";
	$scope.frmDeal.riskSynAns9bModel  = "";
	$scope.frmDeal.riskSynAns9cModel= "";
	$scope.frmDeal.riskSynAns9cModel  = "";
	
	
	$scope.frmDeal.riskSynAns9dModel = "";
	$scope.frmDeal.riskSynAns10Model = "";
	$scope.frmDeal.riskSynAns11aModel = "";
	$scope.frmDeal.riskSynAns11bModel = "";
	$scope.frmDeal.riskSynAns12Model = "";
	$scope.frmDeal.riskSynAns13Model = "";
	$scope.frmDeal.riskSynAns14Model = "";
	$scope.frmDeal.riskSynAns15Model = "";
	$scope.frmDeal.riskSynAns16Model = "";
	$scope.frmDeal.riskSynAns17Model = "";
	$scope.frmDeal.riskSynAns18Model = "";
	$scope.frmDeal.riskSynAns19Model = "";
	$scope.frmDeal.riskSynAns20Model = "";
	$scope.frmDeal.riskSynAns21Model = "";
	//alert("get answers");
	
	
	angular.forEach($scope.quest,function(value,key){
		$scope.quest[key].mainanswers = "";
		$scope.quest[key].devanswers = "";
	});
};

//by arvind-get prev data 

$scope.getPrevDataDeal_Version = function(versionId){  
//alert('162 getPrevDataDeal_Version temp2='+temp);
	/*$scope.clearData();	*/
	
	var getPrevDataDeal_VersionDetails = function(response) {	
		console.log(response);			
		$scope.PrevDataDeal_Version = response.data;
		if($scope.PrevDataDeal_Version[0].fpProjectTypeId==1 || $scope.PrevDataDeal_Version[0].fpProjectTypeId==2){
			$scope.isDevelopment=true;	
		}
		else{
			$scope.isDevelopment=false;
		}
		console.log("THe prev Data is......... ");
		console.log($scope.PrevDataDeal_Version);
	    if(response.status == 204)
	    {
	    	//alert('if parta response.status == 204');
	    	$scope.putDealData();
	    }
	    else
	    {	    	
	    	$scope.fpType =[{ name: "Development - Fixed Price", id: 1 }, { name: "Development - Manage Capacity", id: 2 }, { name: "Maintenance - Fixed Price", id: 3 }, { name: "Maintenance - Manage Capacity", id: 4 }];
	    	$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }, { name: "RFP/RFI", id: 2 }];
	    	$scope.isAgileBased = [{ name: "Yes", id: 1 }, { name: "No", id: 0 }];
	    	$scope.isSyntelOnsiteFacilityUsed = [{ name: "Client", id: 1 }, { name: "Syntel", id: 0 }];
	    	$scope.riskCategoryId = [{ name: "A", id: 1 }, { name: "B", id: 2 }, { name: "C", id: 3 }, { name: "D", id: 4 }];
	    	$scope.selectManualDeal = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
	    	$scope.onshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
			$scope.offshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
			$scope.workingDays=[{dayId:1, name: 20},{dayId:2, name: 21},{dayId:3, name: 21.67},{dayId:4, name: 22}];
			$scope.OffshoreRiskperc=[{riskid:1,name:0.25},{riskid:2,name:0.5},{riskid:3,name:0.75},{riskid:4,name:1.0},{riskid:5,name:1.25},{riskid:6,name:1.5},{riskid:7,name:1.75},{riskid:8,name:2.0},
				{riskid:9,name:2.25},{riskid:10,name:2.5},{riskid:11,name:2.75},{riskid:12,name:3.0},{riskid:13,name:3.25},{riskid:14,name:3.5},
				{riskid:15,name:3.75},{riskid:16,name:4.0},{riskid:17,name:4.25},{riskid:18,name:4.5},{riskid:19,name:4.75},{riskid:1,name:5.0}];
			$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
			$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
			$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
			$scope.dealTMSynergy  = [{ name: "Cross sell", id: "Cross_sell" }, { name: "Normal", id: "Normal" }];
			
	    	$scope.frmDeal.customerNameModel =  $scope.PrevDataDeal_Version[0][15]; // 50022;
	    	$scope.getDealDetails($scope.frmDeal.customerNameModel);
		 // alert('$scope.PrevDataDeal_Version[0][15];=='+$scope.PrevDataDeal_Version[0][15]);	    	
	    	$scope.frmDeal.DealModel = $scope.PrevDataDeal_Version[0][1];
	    //	alert('174--$scope.PrevDataDeal_Version[0][2]'+$scope.PrevDataDeal_Version[0][2]);	    		
	    	$scope.frmDeal.versionModel = $localStorage.rpDealVersionId; 		    	
	    	//$scope.putVersion($scope.frmDeal.versionModel); // replaced by code @ last
	    	
	        	
	    	$scope.PrevDataDeal_Version[0][2];
	    	
	    	$scope.frmDeal.descriptionModel = $scope.PrevDataDeal_Version[0][4];
	    	
	    	$scope.frmDeal.dealStatusModel =  $scope.PrevDataDeal_Version[0][26]; //'Close';
	    	
	    	$scope.frmDeal.salesSpocModel = $scope.PrevDataDeal_Version[0][5];
	    	$scope.frmDeal.dealTCVModel = $scope.PrevDataDeal_Version[0][6];
	    	$scope.frmDeal.currencyModel = $scope.PrevDataDeal_Version[0][7];
	    	 $scope.frmDeal.rbuTypeModel=  $scope.PrevDataDeal_Version[0][38];
	    	
	    	var dateStart =  $scope.PrevDataDeal_Version[0][8];	
	    	var date = new Date(dateStart.substring(0,10));	    	
	    	$scope.frmDeal.dealStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');
	    	
	    	var end_date=   $scope.PrevDataDeal_Version[0][9];
	    	var date= new Date(end_date.substring(0,10));	    	
	    	$scope.frmDeal.dealEndDateModel =  $filter('date')(end_date.substring(0,10),'dd/MM/yyyy');
	    	
	    	temp=0; //note
	    	
	    	$scope.frmDeal.stageModel = $scope.PrevDataDeal_Version[0][10];
	    	$scope.frmDeal.onshoreHours = $scope.PrevDataDeal_Version[0][10];	    	
	    	$scope.frmDeal.offShoreHours = $scope.PrevDataDeal_Version[0][11];
	    	
	    	$scope.frmDeal.isBizopsModel = $scope.PrevDataDeal_Version[0][12];
	    		    	
	    	$scope.frmDeal.ddlIndustryModel = $scope.PrevDataDeal_Version[0][13];
	    	$scope.frmDeal.selectManualDeal = $scope.PrevDataDeal_Version[0][14]; 

	    	
	    	
	    	if($scope.frmDeal.selectManualDeal == 1){
				$scope.frmDeal.fdmanualdealModel = $scope.selectManualDeal[0].id;
			}
			else{
				$scope.frmDeal.fdmanualdealModel = $scope.selectManualDeal[1].id;
			}
			
	    	
	    	
	    	
	    	$scope.frmDeal.penaltyPercent = $scope.PrevDataDeal_Version[0][16];
	    	$scope.frmDeal.oldDealIdModel = $scope.PrevDataDeal_Version[0][17];
	    	$scope.frmDeal.percentageCloseModel = $scope.PrevDataDeal_Version[0][18];
	    	$scope.frmDeal.projectIdModel = $scope.PrevDataDeal_Version[0][19];
	    	
	    	$scope.frmDeal.riskCategoryModel = $scope.PrevDataDeal_Version[0][20];
	    	$scope.frmDeal.ddlIndustryModel = $scope.PrevDataDeal_Version[0][21];
	    	
	    	 var getLobDetails = function(response) {
	    			console.log(response);
	    			$scope.masterLob = response.data;
	    			
	    	};
	    	WebServiceFactory.getLobDetails().then(getLobDetails);
	    	//$scope.frmDeal.ddlLOBModel = $scope.PrevDataDeal_Version[0][22];
	    	
	   
	    	if($scope.PrevDataDeal_Version[0][23]=='true')
	    		{
	    		$scope.frmDeal.isAgileBasedModel=1;
	    		}
	    	else
	    		{
	    		$scope.frmDeal.isAgileBasedModel=0;
	    		}
	    	
	    	
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
	    	//alert(' $scope.frmDeal.fuAttachment_1Model='+$scope.frmDeal.fuAttachment_1Model);
	    	//var file1=$scope.frmDeal.fuAttachment_1Model;    	
	    	$scope.status = $scope.PrevDataDeal_Version[0][26];
	    	console.log("The Status is....... "+ $scope.status);
	    	$scope.frmDeal.onsiteHoursModel = $scope.PrevDataDeal_Version[0][10]; //$scope.frmDeal.versionModel.onsiteHours;
	    	for(var i=0;i< $scope.onshoreHours.length;i++) {
	    		if($scope.frmDeal.onsiteHoursModel == $scope.onshoreHours[i].name) {
	    			$scope.frmDeal.onsiteHoursModel = $scope.onshoreHours[i].hourId;
	    		}
	    	}
	    	
	    	$scope.frmDeal.offShoreHoursModel = $scope.PrevDataDeal_Version[0][11];//$scope.frmDeal.versionModel.offShoreHours;

	    	for(var i=0;i< $scope.offshoreHours.length;i++) {
	    		if($scope.frmDeal.offShoreHoursModel == $scope.offshoreHours[i].name) {
	    			$scope.frmDeal.offShoreHoursModel = $scope.offshoreHours[i].hourId;
	    		}
	    	}
	    	if($scope.versionDetail[0].isManualDeal == 1){ //if($scope.frmDeal.versionModel.isManualDeal == 1){
	    		$scope.frmDeal.fdmanualdealModel = 1;
	    	}
	    	else{
	    		$scope.frmDeal.fdmanualdealModel = 2;
	    	}
	    	
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
	    	
	    	//21th march //putversion() code is below
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
	    				$scope.frmDeal.riskSynAns1Model = response.data[9].riskAnswer;
	    				$scope.frmDeal.riskSynAns2Model = response.data[10].riskAnswer;
	    				$scope.frmDeal.riskSynAns3Model = response.data[11].riskAnswer;
	    				$scope.frmDeal.riskSynAns4Model = response.data[12].riskAnswer;
	    				$scope.frmDeal.riskSynAns5Model = response.data[13].riskAnswer;
	    				$scope.frmDeal.riskSynAns6Model = response.data[14].riskAnswer;
	    				$scope.frmDeal.riskSynAns7Model = response.data[15].riskAnswer;
	    				$scope.frmDeal.riskSynAns8Model = response.data[16].riskAnswer;
	    				if($scope.getRiskAnswers[17].riskAnswer == 1){
	    					$scope.frmDeal.riskSynAns9aModel = $scope.selectManualDeal[0].id;
	    				}
	    				else{
	    					$scope.frmDeal.riskSynAns9aModel  = $scope.selectManualDeal[1].id;
	    				}
	    				
	    				if($scope.getRiskAnswers[18].riskAnswer == 1){
	    					$scope.frmDeal.riskSynAns9bModel= $scope.selectManualDeal[0].id;
	    				}
	    				else{
	    					$scope.frmDeal.riskSynAns9bModel  = $scope.selectManualDeal[1].id;
	    				}
	    				if($scope.getRiskAnswers[19].riskAnswer == 1){
	    					$scope.frmDeal.riskSynAns9cModel= $scope.selectManualDeal[0].id;
	    				}
	    				else{
	    					$scope.frmDeal.riskSynAns9cModel  = $scope.selectManualDeal[1].id;
	    				}
	    				
	    				$scope.frmDeal.riskSynAns9dModel = response.data[20].riskAnswer;
	    				$scope.frmDeal.riskSynAns10Model = response.data[21].riskAnswer;
	    				$scope.frmDeal.riskSynAns11aModel = response.data[22].riskAnswer;
	    				$scope.frmDeal.riskSynAns11bModel = response.data[23].riskAnswer;
	    				$scope.frmDeal.riskSynAns12Model = response.data[24].riskAnswer;
	    				$scope.frmDeal.riskSynAns13Model = response.data[25].riskAnswer;
	    				$scope.frmDeal.riskSynAns14Model = response.data[26].riskAnswer;
	    				$scope.frmDeal.riskSynAns15Model = response.data[27].riskAnswer;
	    				$scope.frmDeal.riskSynAns16Model = response.data[28].riskAnswer;
	    				$scope.frmDeal.riskSynAns17Model = response.data[29].riskAnswer;
	    				$scope.frmDeal.riskSynAns18Model = response.data[30].riskAnswer;
	    				$scope.frmDeal.riskSynAns19Model = response.data[31].riskAnswer;
	    				$scope.frmDeal.riskSynAns20Model = response.data[32].riskAnswer;
	    				$scope.frmDeal.riskSynAns21Model = response.data[33].riskAnswer;
	    				//alert("get answers");
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
	    		
	    		
	    		var getDevMainAnswers = function(response) {	    			
	    			console.log("get dev main answers");
	    			console.log(response);
	    			$scope.getDevMainAnswers=response.data;
	    			if(response.status == 200){
	    				$scope.frmDeal.riskDevAnsModel = response.data[0].answer;
	    				
	    				
	    			}
	    			
	    		};
	    		WebServiceFactory.getDevMainAnswers(rpDealVersionId).then(getDevMainAnswers);
	    		
	    		
	    	/*	angular.forEach($scope.getDevMainAnswers, function(value,key) {
	    	    	if($scope.quest[key].categoryId == 1) {
	    	    		
	    	    		$scope.quest[key].devanswers=$scope.getDevMainAnswers[key].answer;
	    	    	}
	    	     });
	    	 	console.log("Ans:::::::::::::::::::::::::::::::::::::");
	    		console.log($scope.quest);
	    	 	
	    	 angular.forEach($scope.getDevMainAnswers, function(value,key) {
	    	    	if($scope.quest[key].categoryId == 2) {
	    	    		
	    	    		$scope.quest[key].mainanswers=$scope.getDevMainAnswers[key].answer;
	    	    	
	    	    	}
	    	    });
	    	 console.log("Ans:::::::::::::::::::::::::::::::::::::");
	    		console.log($scope.quest);*/

	    	}
	    	
	    	$scope.frmDeal.versionModel =rpDealVersionId	    	
	    	if($scope.frmDeal.versionModel != undefined)
	    	{
	    		console.log("inside putVersion--- 2");
	    		console.log($scope.frmDeal);
	    	
	    	
	    	if($scope.frmDeal.versionModel.fpProjectTypeId == 1){
	    		$scope.frmDeal.fpTypeModel = $scope.fpType[0].id;
	    	}
	    	else{
	    		$scope.frmDeal.fpTypeModel = $scope.fpType[1].id;
	    	}
	    	
	    	if($scope.frmDeal.versionModel.newDeal == true){
	    		$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[0].id;
	    		$scope.dealType = $scope.dealNewOrRenewal[0].name;
	    		$scope.isDealRenewal = false; 
	    	}
	    	else{
	    		$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[1].id;
	    		$scope.isDealRenewal = true; 
	    	}
	    	
	    	if($scope.frmDeal.versionModel.agileProject == true){
	    		$scope.frmDeal.isAgileBasedModel = $scope.isAgileBased[0].id;
	    	}
	    	else{
	    		$scope.frmDeal.isAgileBasedModel = $scope.isAgileBased[1].id;
	    	}
	    	
	    }
	   //end put version code
	    	$scope.calApplicableYears();
	    
	    	
	    	var dealStatus =$scope.frmDeal.dealStatusModel
	    	if(dealStatus=='Close')
	    	{
	    		$scope.isDisabled = true;		
	    	
	    	}
	    }
	};
	WebServiceFactory.getPrevDataDeal_Version(versionId).then(getPrevDataDeal_VersionDetails);
}

var temp;


$scope.currencyId = 0
$scope.putVersion = function(rpDealVersionId){
	$localStorage.rpDealVersionId = rpDealVersionId;
	console.log("Inside put version function");
	if(rpDealVersionId != undefined) {
		var getVersionData = function(response) {
			console.log("get versionDetails Data..");
			console.log(response);
			$scope.getVersionData=response.data;
			
			if($scope.getVersionData!=undefined)
			{
				
				console.log("---------------------------------------------------")
				console.log($scope.getVersionData)
				console.log("---------------------------------------------------")				
/*				$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
				$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
				$scope.dealTMSynergy  = [{ name: "Cross sell", id: "Cross_sell" }, { name: "Normal", id: "Normal" }]*/;

				if($scope.getVersionData[0].cyberSecurity != null || $scope.getVersionData[0].cyberSecurity != undefined ){
					if($scope.getVersionData[0].cyberSecurity.trim()!=""){
						$scope.frmDeal.securitymodel=$scope.getVersionData[0].cyberSecurity;
					}
					else{
						delete $scope.dealTMcyberSecurit;
						$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
						}
					}
					else{
						delete $scope.dealTMcyberSecurit;
						$scope.dealTMcyberSecurity = [{ name: "Yes", id:"Y" }, { name: "No", id: "N" }];
						}
					
					if($scope.getVersionData[0].industrySolution != null || $scope.getVersionData[0].industrySolution != undefined ){
					if($scope.getVersionData[0].industrySolution.trim()!=""){
						$scope.frmDeal.solutionModel=$scope.getVersionData[0].industrySolution;
					}
					else{
						delete $scope.dealTMsolution;
						$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
					}
					}
					else{
						delete $scope.dealTMsolution;
						$scope.dealTMsolution = [{ name: "Yes", id: "Y" }, { name: "No", id: "N" }];
					}
					
					if($scope.getVersionData[0].isSynbots != null || $scope.getVersionData[0].isSynbots != undefined ){
					if($scope.getVersionData[0].isSynbots.trim()!=""){
						$scope.frmDeal.synbotsModel=$scope.getVersionData[0].isSynbots;
					}
					else{
						delete $scope.dealTMsynboots;
						$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
					}
					}		else{
						delete $scope.dealTMsynboots;
						$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
					}
					
					if($scope.getVersionData[0].synergyType != null || $scope.getVersionData[0].synergyType != undefined ){
					if($scope.getVersionData[0].synergyType.trim()!=""){
						
						$scope.frmDeal.synergyModel=$scope.getVersionData[0].synergyType.trim();
					}
					else{
						delete $scope.dealTMSynergy;
						$scope.dealTMSynergy  = [{ name: "Cross sell", id: "Cross_sell" }, { name: "Normal", id: "Normal" }]
					}
				}		else{
					delete $scope.dealTMsynboots;
					$scope.dealTMsynboots = [{ name: "Yes", id: "Y" }, { name: "No", id:"N" }];
				}
					if($scope.getVersionData[0].ddlVolumeUpdateModel != null || $scope.getVersionData[0].ddlVolumeUpdateModel != undefined ){
						if($scope.getVersionData[0].ddlVolumeUpdateModel.trim()!=""){
							$scope.frmDeal.ddlVolumeUpdateModel=$scope.getVersionData[0].ddlVolumeUpdateModel;
						}
						else{
							delete $scope.volumeArray;
							$scope.volumeArray = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
							}
						}
					else{
						delete $scope.volumeArray;
						$scope.volumeArray = [{ name: "Yes", id: 1 }, { name: "No", id: 2 }];
						}

				if(($scope.getVersionData[0].currentApprovalStatus == null ||  $scope.getVersionData[0].currentApprovalStatus == 1 || $scope.getVersionData[0].currentApprovalStatus == 4) && userType == 'Delivery')
					{
						$scope.isCurrStatus=false;
						$scope.isVersionAvailable = false;	
						$scope.isReadytoSubmit=false;
					}
				else
					{
					$scope.isCurrStatus=true;
					$scope.isVersionAvailable = true;	
					$scope.isReadytoSubmit=true;
					}
				
				
				if($scope.getVersionData[0].currentApprovalStatus == 8 && userType == 'Delivery') {
					$scope.isReadytoSubmit=false;
					$scope.isVersionAvailable = false;
				}
				$localStorage.custVrtMapId=$scope.getVersionData[0].customerVerticalMapId;
				$localStorage.VerticalId=$scope.getVersionData[0].verticalId;
				$localStorage.pageTracker=$scope.getVersionData[0].pageTrackerStatus;
				$localStorage.estimatedRevenue=$scope.getVersionData[0].estimatedRevenue;
				$scope.frmDeal.customerNameModel=$scope.getVersionData[0].dealcrmstagesdata2.customer.customerId;
				customerId=$scope.frmDeal.customerNameModel;
				currencyId=$scope.getVersionData[0].dealcrmstagesdata2.currencyId;
				
				crmDealId=$scope.getVersionData[0].crmDealId;
				$scope.getDealDetails(customerId);
				$scope.getVersion(crmDealId);
				var dealTypeId=$scope.getVersionData[0].dealcrmstagesdata2.dealTypeId ;
				var getCountryDetail=function(response)
				{	console.log("Country Details ");
				console.log(response);
				$scope.getCountryDetails=response.data;
				
				angular.forEach($scope.getCountryDetails,function(value,key){
					if($scope.getCountryDetails[key].currencyId==currencyId)
					{
						$scope.getVersionData[0].currencyName=value.currencyCode;
						$scope.frmDeal.currencyModel=$scope.getVersionData[0].currencyName;
						$scope.currencyId = $scope.getCountryDetails[key].currencyId;
					}
				});
				
				}
				WebServiceFactory.getCountryDetail().then(getCountryDetail);
				
				$scope.getNewVersionData=angular.copy($scope.getVersionData);
				console.log("Version data after adding Currency value ");
				console.log($scope.getNewVersionData);
				
				if($scope.getNewVersionData != undefined) {
					$scope.getOldDealDetailsRenewal(customerId,$scope.getVersionData[0].dealcrmstagesdata2.dealStartDate);
					$scope.frmDeal.descriptionModel=$scope.getNewVersionData[0].dealcrmstagesdata2.dealDescription;
					$scope.frmDeal.customerNameModel=$scope.getNewVersionData[0].dealcrmstagesdata2.customer.customerId;
					$scope.frmDeal.DealModel=$scope.getNewVersionData[0].crmDealId;
					$scope.frmDeal.oldDealModel=$scope.getNewVersionData[0].oldDealId;
					$scope.frmDeal.descriptionOldModel=$scope.getNewVersionData[0].oldDealDesc;
					$scope.frmDeal.projectIdModel=$scope.getNewVersionData[0].projectId; 
					$scope.frmDeal.versionModel=$scope.getVersionData[0].rpDealVersionId;
					$scope.frmDeal.currencyModel=$scope.getVersionData[0].currencyName;
					var currencyId=$scope.getVersionData[0].dealcrmstagesdata2.currencyId
					
					if($scope.getVersionData[0].dealcrmstagesdata2.dealStatusId == 0)
					{
						$scope.frmDeal.dealStatusModel='Open';
					}
					else if($scope.getVersionData[0].dealcrmstagesdata2.dealStatusId == 1)
						{
							$scope.frmDeal.dealStatusModel='Won';
						}
					else{
							$scope.frmDeal.dealStatusModel='Lost';
					}
						
					
					$scope.frmDeal.salesSpocModel=$scope.getVersionData[0].dealcrmstagesdata2.salesSpoc;
					$scope.frmDeal.dealTCVModel=$scope.getVersionData[0].dealcrmstagesdata2.revenue;
					 $scope.frmDeal.rbuTypeModel= $scope.getVersionData[0].dealcrmstagesdata2.rbuProfitCenter;
					var dealStartDate  = $scope.getVersionData[0].dealcrmstagesdata2.dealStartDate;
					$scope.dealStart = $scope.getVersionData[0].dealcrmstagesdata2.dealStartDate;
					$scope.dealEnd = $scope.getVersionData[0].dealcrmstagesdata2.dealEndDate2;
					var date = new Date(dealStartDate.substring(0,10));
					var dealStartDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.frmDeal.dealStartDateModel = dealStartDD;
					
					var dealEndDate  = $scope.getVersionData[0].dealcrmstagesdata2.dealEndDate2;
					var date = new Date(dealEndDate.substring(0,10));
					var dealEndDateDD = $filter('date')(date,'dd/MM/yyyy');
					$scope.frmDeal.dealEndDateModel = dealEndDateDD;
					
					
					
					
					var startDateArray =  dealStartDD.split("/");
					var endDateArray = dealEndDateDD.split("/");
					var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
					console.log("Start Date is........... "+ startDate);
					var endDate  = new Date(endDateArray[1] + '/' + endDateArray[0] + '/' + endDateArray[2]);
					console.log("End Date is....... "+ endDate);
					console.log("The Start Date with Time is...... "+ startDate.getTime());
					console.log("The Start Date with Time is...... "+ endDate.getTime());
					var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());   
					var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
					console.log("The Difference in Days is......."+diffDays);
					yearDiff = Math.ceil(diffDays/365);
					console.log("The Year Differene is......... "+ yearDiff);
					monthDiff = yearDiff * 12;
					console.log("The Month Diff is......... "+ monthDiff);
					$scope.frmDeal.durationModel=$scope.getVersionData[0].monthDiff;
					$scope.frmDeal.percentageCloseModel=$scope.getVersionData[0].dealcrmstagesdata2.percentageClose;
					
					for(var i=0;i< $scope.onshoreHours.length;i++) {
						if($scope.getVersionData[0].onsiteHours == $scope.onshoreHours[i].name) {
							$scope.frmDeal.onsiteHoursModel = $scope.onshoreHours[i].hourId;
						}
					}
					
					for(var i=0;i< $scope.offshoreHours.length;i++) {
						if($scope.getVersionData[0].offShoreHours == $scope.offshoreHours[i].name) {
							$scope.frmDeal.offShoreHoursModel = $scope.offshoreHours[i].hourId;
						}
					}
					
					for(var i=0;i< $scope.workingDays.length;i++) {
						if($scope.getVersionData[0].workingDays == $scope.workingDays[i].name) {
							$scope.frmDeal.workingDaysModel = $scope.workingDays[i].dayId;
						}
					}
					for(var i=0;i< $scope.OffshoreRiskperc.length;i++) {
						if($scope.getVersionData[0].fxriskoffshore == $scope.OffshoreRiskperc[i].name) {
							$scope.frmDeal.txtOffshoreRiskModel = $scope.OffshoreRiskperc[i].riskid;
						}
					}
					
					
					for(var i=0;i< $scope.fpType.length;i++) {
						if($scope.getVersionData[0].fpProjectTypeId == $scope.fpType[i].id) {
							$scope.frmDeal.fpTypeModel = $scope.fpType[i].name;
						}
					}
					
					
					if($scope.getVersionData[0].dealcrmstagesdata2.industry == 0) {
							$scope.frmDeal.ddlIndustryModel = 'KPO';
						} else if( $scope.getVersionData[0].dealcrmstagesdata2.industry == 2 ) {
							$scope.frmDeal.ddlIndustryModel = 'KPO JV';
						}
					else
						{
							$scope.frmDeal.ddlIndustryModel = 'IT';
						}
					
					if($scope.getVersionData[0].dealcrmstagesdata2.dealPriorityId==1)
					{
						$scope.frmDeal.dealPriorityModel='VHIGH'
					}
					else if($scope.getVersionData[0].dealcrmstagesdata2.dealPriorityId==2)
					{
						$scope.frmDeal.dealPriorityModel='HIGH'
					}
					else if($scope.getVersionData[0].dealcrmstagesdata2.dealPriorityId==3)
					{
						$scope.frmDeal.dealPriorityModel='MEDIUM'
					}
					else
					{
						$scope.frmDeal.dealPriorityModel='LOW'
					}
					$localStorage.CurrentApprovalStatus=$scope.getVersionData[0].currentApprovalStatus;
					
					if($scope.getVersionData[0].isNewDeal == 1){
						$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[0].id;
						$scope.dealType = $scope.dealNewOrRenewal[0].name; 
						$scope.isDealRenewal = false; 
						$scope.Renewal=false;
					}
					else if($scope.getVersionData[0].isNewDeal == 0){
						$scope.Renewal=true;
						$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[1].id;
						$scope.isDealRenewal = true; 
					}
					else
						{
							
							$scope.frmDeal.dealNewOrRenewalModel = $scope.dealNewOrRenewal[2].id;
							$scope.isDealRenewal = false; 
							$scope.Renewal=false;; 
						}
						
					
					
					if($scope.getVersionData[0].fpProjectTypeId == 1){
						$scope.frmDeal.fpTypeModel = $scope.fpType[0].id;
						$scope.showdevquest=true;
						$scope.showmainquest=false;
						$scope.ShowHideUploadDevQuest = function () {
							$scope.UploadHiddenDevQuest = $scope.UploadHiddenDevQuest ? false : true;
						};
					}
					else if($scope.getVersionData[0].fpProjectTypeId == 2)
					{
						$scope.frmDeal.fpTypeModel = $scope.fpType[1].id;
						$scope.showdevquest=true;
						$scope.showmainquest=false;
						$scope.ShowHideUploadDevQuest = function () {
							$scope.UploadHiddenDevQuest = $scope.UploadHiddenDevQuest ? false : true;
						};
					}
					else if($scope.getVersionData[0].fpProjectTypeId == 3)
					{
						$scope.frmDeal.fpTypeModel = $scope.fpType[2].id;
						$scope.showdevquest=false;
						$scope.showmainquest=true;
						$scope.ShowHideUploadMainQuest = function () {
							$scope.UploadHiddenMainQuest =$scope.UploadHiddenMainQuest ? false : true;
						};
						
					}
					else
					{
						$scope.frmDeal.fpTypeModel = $scope.fpType[3].id;
						$scope.showdevquest=false;
						$scope.showmainquest=true;
						$scope.ShowHideUploadMainQuest = function () {
							$scope.UploadHiddenMainQuest =$scope.UploadHiddenMainQuest ? false : true;
						};
					}
					
					
					if($scope.getVersionData[0].agileProject == true){
						$scope.frmDeal.isAgileBasedModel = $scope.isAgileBased[0].id;
					}
					else{
						$scope.frmDeal.isAgileBasedModel = $scope.isAgileBased[1].id;
					}
					
					
					if($scope.getVersionData[0].riskCategoryId == 1){
						$scope.frmDeal.riskCategoryModel = $scope.riskCategoryId[0].id;
					}
					else if($scope.getVersionData[0].riskCategoryId == 2){
						$scope.frmDeal.riskCategoryModel = $scope.riskCategoryId[1].id;
					}
					else if($scope.getVersionData[0].riskCategoryId == 3)
					{
						$scope.frmDeal.riskCategoryModel = $scope.riskCategoryId[2].id;
					}
					else
					{
						$scope.frmDeal.riskCategoryModel = $scope.riskCategoryId[3].id;
					}
					
					if($scope.getVersionData[0].isManualDeal == 1){
						$scope.frmDeal.fdmanualdealModel = $scope.selectManualDeal[0].id;
						$scope.fpdealnormal=false;
						$scope.fpdealmanual=true;
					}
					else{
						$scope.frmDeal.fdmanualdealModel = $scope.selectManualDeal[1].id;
						$scope.fpdealnormal=true;
						$scope.fpdealmanual=false;
					}
					
					
					$scope.frmDeal.penaltyPercent=$scope.getVersionData[0].penaltyPercentage;
					
					//$scope.frmDeal.ddlLOBModel=$scope.getVersionData[0].projectLobId;
					
				/*	$scope.frmDeal.ddlIndustryModel=$scope.getVersionData[0].dealcrmstagesdata2.industry;*/
					
					
					$scope.crmOldDealDetail = angular.copy($scope.crmDealDetail);
					
					
					angular.forEach($scope.crmOldDealDetail, function(value,key) {
						if($scope.frmDeal.DealModel == $scope.crmOldDealDetail[key].crmDealId) {
							$scope.crmOldDealDetail.splice(key, 1);
						}
					});
					
					
					$scope.frmDeal.stageModel=$scope.getVersionData[0].dealcrmstagesdata2.stageId
					$scope.frmDeal.ddlVolumeUpdateModel=$scope.getVersionData[0].dealdiscFlag;
            		$scope.frmDeal.txtUpdatevolumeModel=$scope.getVersionData[0].volumeDiscountPercent;
            		if($scope.frmDeal.ddlVolumeUpdateModel != undefined || $scope.frmDeal.ddlVolumeUpdateModel != null) {
            			$scope.setvolume($scope.frmDeal.ddlVolumeUpdateModel);
            		}
            		
            		$scope.frmDeal.txtContingentRiskModel=$scope.getVersionData[0].contingentrisk;
            		$scope.frmDeal.txtSLARiskModel=$scope.getVersionData[0].slarisk;	

				}
				
			}
		};
		WebServiceFactory.getVersionData(rpDealVersionId).then(getVersionData);
	}

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
			$scope.frmDeal.riskAns9Model = response.data[8].riskAnswer;
			if(response.data[8].riskAnswer == "true"){
				$scope.frmDeal.riskAns9Model = true;
				$scope.synbotquestions = true;
			}
			else
			{
				$scope.frmDeal.riskAns9Model = false;
				$scope.synbotquestions = false;
			}
			
			$scope.frmDeal.riskSynAns1Model = response.data[9].riskAnswer;
			$scope.frmDeal.riskSynAns2Model = response.data[10].riskAnswer;
			$scope.frmDeal.riskSynAns3Model = response.data[11].riskAnswer;
			$scope.frmDeal.riskSynAns4Model = response.data[12].riskAnswer;
			$scope.frmDeal.riskSynAns5Model = response.data[13].riskAnswer;
			$scope.frmDeal.riskSynAns6Model = response.data[14].riskAnswer;
			$scope.frmDeal.riskSynAns7Model = response.data[15].riskAnswer;
			$scope.frmDeal.riskSynAns8Model = response.data[16].riskAnswer;
			
			
			
			if($scope.getRiskAnswers[17].riskAnswer == 1){
				$scope.frmDeal.riskSynAns9aModel = $scope.selectManualDeal[0].id;
			}
			else{
				$scope.frmDeal.riskSynAns9aModel  = $scope.selectManualDeal[1].id;
			}
			
			if($scope.getRiskAnswers[18].riskAnswer == 1){
				$scope.frmDeal.riskSynAns9bModel= $scope.selectManualDeal[0].id;
			}
			else{
				$scope.frmDeal.riskSynAns9bModel  = $scope.selectManualDeal[1].id;
			}
			if($scope.getRiskAnswers[19].riskAnswer == 1){
				$scope.frmDeal.riskSynAns9cModel= $scope.selectManualDeal[0].id;
			}
			else{
				$scope.frmDeal.riskSynAns9cModel  = $scope.selectManualDeal[1].id;
			}
										
			$scope.frmDeal.riskSynAns9dModel=response.data[20].riskAnswer;
			$scope.frmDeal.riskSynAns10Model = response.data[21].riskAnswer;
			$scope.frmDeal.riskSynAns11aModel = response.data[22].riskAnswer;
			$scope.frmDeal.riskSynAns11bModel = response.data[23].riskAnswer;
			$scope.frmDeal.riskSynAns12Model = response.data[24].riskAnswer;
			$scope.frmDeal.riskSynAns13Model = response.data[25].riskAnswer;
			$scope.frmDeal.riskSynAns14Model = response.data[26].riskAnswer;
			$scope.frmDeal.riskSynAns15Model = response.data[27].riskAnswer;
			$scope.frmDeal.riskSynAns16Model = response.data[28].riskAnswer;
			$scope.frmDeal.riskSynAns17Model = response.data[29].riskAnswer;
			$scope.frmDeal.riskSynAns18Model = response.data[30].riskAnswer;
			$scope.frmDeal.riskSynAns19Model = response.data[31].riskAnswer;
			$scope.frmDeal.riskSynAns20Model = response.data[32].riskAnswer;
			$scope.frmDeal.riskSynAns21Model = response.data[33].riskAnswer;
			//alert("get answers");
			
		}
		
	};
	WebServiceFactory.getRiskAnswers(rpDealVersionId).then(getRiskAnswers);


	var getDevMainAnswers = function(response) {	
		
		console.log("get dev main answers");
		console.log(response);
		$scope.getDevMainAnswers=response.data;
		
		angular.forEach($scope.quest, function(value,key) {
	    	if($scope.quest[key].categoryId == 1) {
	    		angular.forEach($scope.getDevMainAnswers, function(value2, key2) {
	    	    	if($scope.quest[key].questionaireId == $scope.getDevMainAnswers[key2].questionId) {
	    	    		$scope.quest[key].devanswers=$scope.getDevMainAnswers[key2].answer;
	    	    	}
	    	     });
	    	}
	     });
	 	console.log("Ans Dev:::::::::::::::::::::::::::::::::::::");
		console.log($scope.quest);
	 	
		angular.forEach($scope.quest, function(value,key) {
	    	if($scope.quest[key].categoryId == 2) {
	    		angular.forEach($scope.getDevMainAnswers, function(value2, key2) {
	    	    	if($scope.quest[key].questionaireId == $scope.getDevMainAnswers[key2].questionId) {
	    	    		$scope.quest[key].mainanswers=$scope.getDevMainAnswers[key2].answer;
	    	    	}
	    	     });
	    	}
	     });
	 	console.log("Ans Main:::::::::::::::::::::::::::::::::::::");
		console.log($scope.quest);
	 	
		
	};
	WebServiceFactory.getDevMainAnswers(rpDealVersionId).then(getDevMainAnswers);
	}
	
	
}

$scope.clearData = function(){
	$scope.frmDeal.synergyModel = null;
	$scope.frmDeal.solutionModel = null;
	$scope.frmDeal.securitymodel = null;
	$scope.frmDeal.synbotsModel = null;
	 $scope.frmDeal.rbuTypeModel= "";
	$scope.frmDeal.descriptionModel="";
	$scope.frmDeal.salesSpocModel ="";
	$scope.frmDeal.dealStatusModel = "";
	$scope.frmDeal.dealPriorityModel = "";
	$scope.frmDeal.dealStartDateModel = "";
	$scope.frmDeal.dealEndDateModel = "";
	$scope.frmDeal.durationModel = "";
	$scope.frmDeal.currencyModel = "";
	$scope.frmDeal.ddlVolumeUpdateModel=null;
	$scope.frmDeal.txtUpdatevolumeModel=null;

	$scope.frmDeal.percentageCloseModel = "";
	$scope.frmDeal.stageModel = "";
	/*$scope.frmDeal.salesForceModel = "";*/
	$scope.frmDeal.dealTCVModel = "";
	$scope.frmDeal.penaltyPercent = "";
	$scope.frmDeal.isManualDeal = false;
	$scope.frmDeal.txtOffshoreRiskModel=null;
	$scope.frmDeal.txtNearshoreRiskModel=null;
	$scope.frmDeal.txtContingentRiskModel=null;
	$scope.frmDeal.txtSLARiskModel=null;
	
	$("#manualDealAttachment").hide();
	$("#attachment1").hide();
	$("#attachment2").hide();

	
//	$scope.frmDeal.dealTypeModel = null;
	$scope.frmDeal.fpTypeModel = null;
	$scope.frmDeal.dealNewOrRenewalModel = null;
	$scope.frmDeal.isAgileBasedModel = null;
	$scope.frmDeal.onsiteFacilityModel = null;
	$scope.frmDeal.riskCategoryModel = null;
	$scope.frmDeal.ddlIndustryModel = null;
	$scope.frmDeal.ddlLOBModel = null;
	$scope.frmDeal.isManualDealModel = null;
	$scope.frmDeal.onsiteHoursModel = "";
	$scope.frmDeal.offShoreHoursModel = "";
	$scope.frmDeal.versionModel="";
	$scope.frmDeal.oldDealModel="";
	$scope.frmDeal.descriptionOldModel="";
	$scope.frmDeal.workingDaysModel="";
	$scope.isDealRenewal=false;
	$scope.frmDeal.fdmanualdealModel="";
//	$scope.dealType = [{ name: "Fixed Price", id: 1 }, { name: "T & M", id: 2 }];
	$scope.fpType = [{ name: "Development - Fixed Price", id: 1 }, { name: "Development - Manage Capacity", id: 2 }, { name: "Maintenance - Fixed Price", id: 3 }, { name: "Maintenance - Manage Capacity", id: 4 }];
	$scope.dealNewOrRenewal = [{ name: "New", id: 1 }, { name: "Renewal", id: 0 }, { name: "RFP/RFI", id: 2 }];
	$scope.isAgileBased = [{ name: "Yes", id: 1 }, { name: "No", id: 0 }];
	$scope.isSyntelOnsiteFacilityUsed = [{ name: "Client", id: 1 }, { name: "Syntel", id: 0 }];
	$scope.riskCategoryId = [{ name: "A", id: 1 }, { name: "B", id: 2 }, { name: "C", id: 3 }, { name: "D", id: 4 }];
	$scope.onshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
	$scope.offshoreHours = [{hourId : 1, name : 7},{hourId : 2, name : 7.25},{hourId : 3, name : 7.5},{hourId : 4, name : 7.75}, {hourId : 5, name : 8},{hourId:6,name :8.25},{hourId:7,name:8.5},{hourId:8,name:8.75},{hourId : 9, name : 9},{hourId:10,name :9.25},{hourId:11,name:9.5},{hourId:12,name:9.75},{hourId:13,name:10}];
	$scope.workingDays=[{dayId:1, name: 20},{dayId:2, name: 21},{dayId:3, name: 21.67},{dayId:4, name: 22}];
	var getLobDetails = function(response) {
		console.log(response);
		$scope.masterLob = response.data;
   };
   WebServiceFactory.getLobDetails().then(getLobDetails);
	
	$scope.frmDeal.oldDealIdModel = "";
	$scope.frmDeal.projectIdModel = "";
	$scope.frmDeal.isBizopsModel = false;
	$scope.frmDeal.isIncludeInHeadCount = false;
	
	/*$scope.frmDeal.riskAns1Model = "";
	$scope.frmDeal.riskAns2Model = "";
	$scope.frmDeal.riskAns3Model = "";
	$scope.frmDeal.riskAns4Model = "";
	$scope.frmDeal.riskAns5Model = "";
	$scope.frmDeal.riskAns6Model = "";
	$scope.frmDeal.riskAns7Model = "";
	$scope.frmDeal.riskAns8Model = "";
	$scope.frmDeal.riskAns9Model = false;
	
	
	
	$scope.frmDeal.riskAns1Model = "";
	$scope.frmDeal.riskAns2Model = "";
	$scope.frmDeal.riskAns3Model = "";
	$scope.frmDeal.riskAns4Model = "";
	$scope.frmDeal.riskAns5Model = "";
	$scope.frmDeal.riskAns6Model = "";
	$scope.frmDeal.riskAns7Model = "";
	$scope.frmDeal.riskAns8Model = "";
	$scope.frmDeal.riskAns9Model = "";
	$scope.frmDeal.riskSynAns1Model = "";
	$scope.frmDeal.riskSynAns2Model = "";
	$scope.frmDeal.riskSynAns3Model = "";
	$scope.frmDeal.riskSynAns4Model = "";
	$scope.frmDeal.riskSynAns5Model = "";
	$scope.frmDeal.riskSynAns6Model = "";
	$scope.frmDeal.riskSynAns7Model = "";
	$scope.frmDeal.riskSynAns8Model = "";
	$scope.frmDeal.riskSynAns9aModel ="";
	$scope.frmDeal.riskSynAns9aModel  = "";
	$scope.frmDeal.riskSynAns9bModel= "";
	$scope.frmDeal.riskSynAns9bModel  = "";
	$scope.frmDeal.riskSynAns9cModel= "";
	$scope.frmDeal.riskSynAns9cModel  = "";
	
	
	$scope.frmDeal.riskSynAns9dModel = "";
	$scope.frmDeal.riskSynAns10Model = "";
	$scope.frmDeal.riskSynAns11aModel = "";
	$scope.frmDeal.riskSynAns11bModel = "";
	$scope.frmDeal.riskSynAns12Model = "";
	$scope.frmDeal.riskSynAns13Model = "";
	$scope.frmDeal.riskSynAns14Model = "";
	$scope.frmDeal.riskSynAns15Model = "";
	$scope.frmDeal.riskSynAns16Model = "";
	$scope.frmDeal.riskSynAns17Model = "";
	$scope.frmDeal.riskSynAns18Model = "";
	$scope.frmDeal.riskSynAns19Model = "";
	$scope.frmDeal.riskSynAns20Model = "";
	$scope.frmDeal.riskSynAns21Model = "";
	//alert("get answers");
	$scope.frmDeal.riskAns9Model = false;
	$scope.frmDeal.riskAns9Model = false;*/
	
	/*angular.forEach($scope.quest,function(value,key){
		$scope.quest[key].mainanswers = "";
		$scope.quest[key].devanswers = "";
	});*/
	
}

$scope.getVersionName=function(frmDeal){
	$scope.update= true;
	
	 
	 angular.forEach($scope.frmDeal.riskDevAnsModel, function(value,key) {
	    	if($scope.quest[key].categoryId == 1) {
	    		
	    		$scope.quest[key].devanswers=value;
	    	}
	     });
	 	console.log("Ans:::::::::::::::::::::::::::::::::::::");
 		console.log($scope.quest);
	 	
	 angular.forEach($scope.frmDeal.riskMainAnsModel, function(value,key) {
	    	if($scope.quest[key].categoryId == 2) {
	    		
	    		$scope.quest[key].mainanswers=value;
	    	
	    	}
	    });
	//if(frmDeal.$valid){
	if(($scope.frmDeal.customerNameModel != null) && ($scope.frmDeal.DealModel != null))  //if both selected
	{
		$scope.update= false;
		if(userType == 'Delivery') {
			BootstrapDialog.show({
				title: 'Version Name',
				type : BootstrapDialog.TYPE_PRIMARY,
				// closable: false,
				message: $('<input type="text" class="form-control" ng-model="txtVersion" id="txtVersion" placeholder="Please enter version here">'
				),
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
								/*BootstrapDialog.show({
						        	title : 'FP Deal Creation',
						        	type : BootstrapDialog.TYPE_DANGER,
						        	message : 'Please enter version name in the form of V1,V2,V11,V22 till max 3 letters.',
						        	closable : true,
						        	buttons : [{
						        		label : 'OK',
						        		action : function(dialogRef) {
						        			dialogRef.close();
						        			$localStorage.rpDealVersionId = $scope.frmDeal.versionModel;
						        			
						        		}
						        	}]
						        });*/
							}
							
						}
						dialogRef.close();
						$scope.insertFpDealData();
					}
				}, {
					label: 'Close',
					cssClass: 'btn-primary',
					action: function(dialog) {
						dialog.close();
					}
				}]
			});
		} else {
			BootstrapDialog.show({
	        	title : 'FP Deal Creation',
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
	//}
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

$scope.insertFpDealData = function(){
	/*alert("inside insert data");
	alert($scope.frmDeal.VersionNameModel);*/
	var fileTest = $("#fuUploadManualDeal,#fuAttachment_1,#fuAttachment_2");
	var name = ["MANUAL_DEAL", "ATTACHMENT_ID1","ATTACHMENT_ID2"];
	
	var isDealTypeManual = $scope.fdmanualdealModel;
	$localStorage.DealModel=$scope.frmDeal.DealModel;
	 var isDealTypeManual = $scope.frmDeal.fdmanualdealModel;
	 $scope.frmDeal.fpTypeModel = 1;
	 
	 if($scope.frmDeal.fdmanualdealModel != null) {
  	   var isDealTypeManualCheck = $.grep($scope.selectManualDeal, function (md) {
  		                       return md.id == $scope.frmDeal.fdmanualdealModel;
  		                   })[0].name;
     }
	 
	 if($sessionStorage.NoOfVersion == 0)
		 {
		 angular.forEach($scope.crmDealDetail, function(value,key) {
				if($scope.crmDealDetail[key].crmDealId==crmDealId)
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
		 	else if($scope.frmDeal.ddlIndustryModel == 'KPO')
			 {
			 	$scope.frmDeal.ddlIndustryModel=0;
			 } else {
				 $scope.frmDeal.ddlIndustryModel=2;
			 }
		 	
		 }
	 
	 
	 
	if(isDealTypeManualCheck == "Yes"){
		isDealTypeManual = 1;
	}
	else{
		isDealTypeManual = 0;
	}
	/* var onsiteHourPerDay=$scope.frmDeal.onsiteHoursModel;
	 var offShorehourPerDay=$scope.frmDeal.offShoreHoursModel;*/
	
	if($scope.frmDeal.onsiteHoursModel != null){
		 var onsiteHourPerDay = $.grep($scope.onshoreHours, function (oi) {
	  	                       return oi.hourId == $scope.frmDeal.onsiteHoursModel;
	  	                   })[0].name;
		}
		 
		
	if($scope.frmDeal.offShoreHoursModel != null){
		 var offShorehourPerDay = $.grep($scope.offshoreHours, function (offshoreHour) {
	  	                       return offshoreHour.hourId == $scope.frmDeal.offShoreHoursModel;
	  	                   })[0].name;
		}
		
	if($scope.frmDeal.workingDaysModel != null){
		 var workingDay = $.grep($scope.workingDays, function (wrkDays) {
	  	                       return wrkDays.dayId == $scope.frmDeal.workingDaysModel;
	  	                   })[0].name;
		}
	if($scope.frmDeal.txtOffshoreRiskModel != null){
		 var offshoreRisk = $.grep($scope.OffshoreRiskperc, function (ofsRisk) {
	  	                       return ofsRisk.riskid == $scope.frmDeal.txtOffshoreRiskModel;
	  	                   })[0].name;
		}
	
	
		$scope.getCustomerVerticalFP($scope.frmDeal.customerNameModel);
  		/*var dealStartDate = $scope.dealStart;	
  		var date = new Date(dealStartDate.substring(0,10));
  		var dealStartDates = $filter('date')(date,'MM/dd/yyyy');*/
    	$scope.startDateModel = $scope.dealStart;
    	
    	/*var dealEndDate = $scope.dealEnd;
  		var date = new Date(dealEndDate.substring(0,10));
  		var dealEndDates = $filter('date')(date,'MM/dd/yyyy');*/
    	$scope.endDateModel = $scope.dealEnd;
    	// IF ELSE CONDITION FOR VOLUME DISCOUNT
        if($scope.frmDeal.txtUpdatevolumeModel != undefined || $scope.frmDeal.txtUpdatevolumeModel != null) {
        	$sessionStorage.volumeDiscount= $scope.frmDeal.txtUpdatevolumeModel;  
        } 
       
if($scope.frmDeal.fpTypeModel == 1 || $scope.frmDeal.fpTypeModel == 2)
	{
	var markers = {
			"crmDealId":$scope.frmDeal.DealModel, //.crmDealId,
			"dealVersion":$scope.frmDeal.VersionNameModel,
			"rpDealVersionId":$scope.frmDeal.versionModel,
			"customerId":$scope.frmDeal.customerNameModel,
			"customerVerticalMapId":$sessionStorage.custmVerticalMapId,
			"currencyId":$scope.currencyId,
			"volumeDiscountPercent":$sessionStorage.volumeDiscount,
			"agileProject":$scope.frmDeal.isAgileBasedModel,
			"syntelOnsieFacilityUsed":$scope.frmDeal.onsiteFacilityModel,
			"hcIncluded":$scope.frmDeal.isIncludeInHeadCount,
			"isNewDeal":$scope.frmDeal.dealNewOrRenewalModel,
			"bizOpsInvolved":$scope.frmDeal.isBizopsModel,
			"oldDealId":$scope.frmDeal.oldDealModel,
			"oldDealDesc":$scope.frmDeal.descriptionOldModel,
			"workingDays":workingDay,
			"projectId":$scope.frmDeal.projectIdModel,
			"dealTypeId":1,
			"fpProjectTypeId":$scope.frmDeal.fpTypeModel,
			"riskCategoryId":$scope.frmDeal.riskCategoryModel,
			"dealProgressStatusId":$scope.frmDeal.DealModel.dealStatusId,
			"projectStartDate":$scope.startDateModel,
			"projectEndDate":$scope.endDateModel,
			"dealTcv":$scope.frmDeal.dealTCVModel,
			"onsiteHours":onsiteHourPerDay,
			"offShoreHours":offShorehourPerDay,
			"projectLobId":null,
			"penaltyPercentage":$scope.frmDeal.penaltyPercent,
			"projectIndustry":$scope.frmDeal.ddlIndustryModel,
			"isManualDeal":isDealTypeManual,
			"isVersionFinalized":"1",
			"currentApprovalStatus":1,
			"pageTrackerStatus":0,
			"verticalId":$sessionStorage.verticalId,
			"statusIndicator":"Draft",
			"cyberSecurity": $scope.frmDeal.securitymodel,
			"industrySolution": $scope.frmDeal.solutionModel,
			"isSynbots": $scope.frmDeal.synbotsModel,
			"synergyType":$scope.frmDeal.synergyModel,
			"dealdiscFlag":$scope.frmDeal.ddlVolumeUpdateModel,
			"fxriskoffshore":offshoreRisk,
			"fxrisknearshore":$scope.frmDeal.txtNearshoreRiskModel,
			"contingentrisk":$scope.frmDeal.txtContingentRiskModel,
			"slarisk":$scope.frmDeal.txtSLARiskModel,
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
		    },
		    {
		        "questionId":"1004",
		        "riskAnswer":$scope.frmDeal.riskSynAns1Model
		    },
		    {
		        "questionId":"1005",
		        "riskAnswer":$scope.frmDeal.riskSynAns2Model
		    },
		    {
		        "questionId":"1006",
		        "riskAnswer":$scope.frmDeal.riskSynAns3Model
		    },
		    {
		        "questionId":"1007",
		        "riskAnswer":$scope.frmDeal.riskSynAns4Model
		    },
		    {
		        "questionId":"1008",
		        "riskAnswer":$scope.frmDeal.riskSynAns5Model
		    },
		    {
		        "questionId":"1009",
		        "riskAnswer":$scope.frmDeal.riskSynAns6Model
		    },
		    {
		        "questionId":"1010",
		        "riskAnswer":$scope.frmDeal.riskSynAns7Model
		    },
		    {
		        "questionId":"1011",
		        "riskAnswer":$scope.frmDeal.riskSynAns8Model
		    },
		    {
		        "questionId":"1013",
		        "riskAnswer":$scope.frmDeal.riskSynAns9aModel
		    },
		    {
		        "questionId":"1014",
		        "riskAnswer":$scope.frmDeal.riskSynAns9bModel
		    },
		    {
		        "questionId":"1015",
		        "riskAnswer":$scope.frmDeal.riskSynAns9cModel
		    },
		    {
		        "questionId":"1016",
		        "riskAnswer":$scope.frmDeal.riskSynAns9dModel
		    },
		    {
		        "questionId":"1017",
		        "riskAnswer":$scope.frmDeal.riskSynAns10Model
		    },
		    {
		        "questionId":"1018",
		        "riskAnswer":$scope.frmDeal.riskSynAns11aModel
		    },
		    {
		        "questionId":"1019",
		        "riskAnswer":$scope.frmDeal.riskSynAns11bModel
		    },
		    {
		        "questionId":"1020",
		        "riskAnswer":$scope.frmDeal.riskSynAns12Model
		    },
		    {
		        "questionId":"1021",
		        "riskAnswer":$scope.frmDeal.riskSynAns13Model
		    },
		    {
		        "questionId":"1022",
		        "riskAnswer":$scope.frmDeal.riskSynAns14Model
		    },
		    {
		        "questionId":"1023",
		        "riskAnswer":$scope.frmDeal.riskSynAns15Model
		    },
		    {
		        "questionId":"1024",
		        "riskAnswer":$scope.frmDeal.riskSynAns16Model
		    },
		    {
		        "questionId":"1025",
		        "riskAnswer":$scope.frmDeal.riskSynAns17Model
		    },
		    {
		        "questionId":"1026",
		        "riskAnswer":$scope.frmDeal.riskSynAns18Model
		    },
		    {
		        "questionId":"1027",
		        "riskAnswer":$scope.frmDeal.riskSynAns19Model
		    },
		    {
		        "questionId":"1028",
		        "riskAnswer":$scope.frmDeal.riskSynAns20Model
		    },
		    {
		        "questionId":"1029",
		        "riskAnswer":$scope.frmDeal.riskSynAns21Model
		    }],
		    
		    "riskDevMainBasedAnswers":[
				{
				    "questionId":$scope.quest[0].questionaireId,
				    "answer":null
				
				},
				{
				    "questionId":$scope.quest[1].questionaireId,
				    "answer":null
				
				},
				{
				    "questionId":$scope.quest[2].questionaireId,
				    "answer":null
				
				},
				{
				    "questionId":$scope.quest[3].questionaireId,
				    "answer":null
				
				},
		    {
		        "questionId":$scope.quest[4].questionaireId,
		        "answer":$scope.quest[4].devanswers
		    },
		    {
		        "questionId":$scope.quest[5].questionaireId,
		        "answer":$scope.quest[5].devanswers
		    },
		    {
		        "questionId":$scope.quest[6].questionaireId,
		        "answer":$scope.quest[6].devanswers
		    },
		    {
		        "questionId":$scope.quest[7].questionaireId,
		        "answer":$scope.quest[7].devanswers
		    },
		    {
		        "questionId":$scope.quest[8].questionaireId,
		        "answer":$scope.quest[8].devanswers
		    },
		    {
		        "questionId":$scope.quest[9].questionaireId,
		        "answer":$scope.quest[9].devanswers
		    },
		    {
		        "questionId":$scope.quest[10].questionaireId,
		        "answer":$scope.quest[10].devanswers
		    },
		    {
		        "questionId":$scope.quest[11].questionaireId,
		        "answer":$scope.quest[11].devanswers
		    },
		    {
		        "questionId":$scope.quest[12].questionaireId,
		        "answer":$scope.quest[12].devanswers
		    },
		    {
		        "questionId":$scope.quest[13].questionaireId,
		        "answer":$scope.quest[13].devanswers
		    },
		    {
		        "questionId":$scope.quest[14].questionaireId,
		        "answer":$scope.quest[14].devanswers
		    },
		    {
		        "questionId":$scope.quest[15].questionaireId,
		        "answer":$scope.quest[15].devanswers
		    }]
		    
		    
	};
	}

if($scope.frmDeal.fpTypeModel == 3 || $scope.frmDeal.fpTypeModel == 4)
{
var markers = {
		"crmDealId":$scope.frmDeal.DealModel, //.crmDealId,
		"dealVersion":$scope.frmDeal.VersionNameModel,
		"rpDealVersionId":$scope.frmDeal.versionModel,
		"customerId":$scope.frmDeal.customerNameModel,
		"customerVerticalMapId":$sessionStorage.custmVerticalMapId,
		"currencyId":$scope.currencyId,
		"agileProject":$scope.frmDeal.isAgileBasedModel,
		"syntelOnsieFacilityUsed":$scope.frmDeal.onsiteFacilityModel,
		"hcIncluded":$scope.frmDeal.isIncludeInHeadCount,
		"isNewDeal":$scope.frmDeal.dealNewOrRenewalModel,
		"volumeDiscountPercent":$sessionStorage.volumeDiscount,
		"bizOpsInvolved":$scope.frmDeal.isBizopsModel,
		"oldDealId":$scope.frmDeal.oldDealModel,
		"oldDealDesc":$scope.frmDeal.descriptionOldModel,
		"workingDays":workingDay,
		"projectId":$scope.frmDeal.projectIdModel,
		"dealTypeId":1,
		"fpProjectTypeId":$scope.frmDeal.fpTypeModel,
		"riskCategoryId":$scope.frmDeal.riskCategoryModel,
		"dealProgressStatusId":$scope.frmDeal.DealModel.dealStatusId,
		"projectStartDate":$scope.startDateModel,
		"projectEndDate":$scope.endDateModel,
		"dealTcv":$scope.frmDeal.dealTCVModel,
		"onsiteHours":onsiteHourPerDay,
		"offShoreHours":offShorehourPerDay,
		"projectLobId":null,
		"penaltyPercentage":$scope.frmDeal.penaltyPercent,
		"projectIndustry":$scope.frmDeal.ddlIndustryModel,
		"isManualDeal":isDealTypeManual,
		"verticalId":$sessionStorage.verticalId,
		"isVersionFinalized":"1",
		"currentApprovalStatus":1,
		"pageTrackerStatus":0,
		"statusIndicator":"Draft",
		"cyberSecurity": $scope.frmDeal.securitymodel,
		"industrySolution": $scope.frmDeal.solutionModel,
		"isSynbots": $scope.frmDeal.synbotsModel,
		"synergyType":$scope.frmDeal.synergyModel,
		"dealdiscFlag":$scope.frmDeal.ddlVolumeUpdateModel,
		"fxriskoffshore":offshoreRisk,
		"fxrisknearshore":$scope.frmDeal.txtNearshoreRiskModel,
		"contingentrisk":$scope.frmDeal.txtContingentRiskModel,
		"slarisk":$scope.frmDeal.txtSLARiskModel,
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
	    },
	    {
	        "questionId":"1004",
	        "riskAnswer":$scope.frmDeal.riskSynAns1Model
	    },
	    {
	        "questionId":"1005",
	        "riskAnswer":$scope.frmDeal.riskSynAns2Model
	    },
	    {
	        "questionId":"1006",
	        "riskAnswer":$scope.frmDeal.riskSynAns3Model
	    },
	    {
	        "questionId":"1007",
	        "riskAnswer":$scope.frmDeal.riskSynAns4Model
	    },
	    {
	        "questionId":"1008",
	        "riskAnswer":$scope.frmDeal.riskSynAns5Model
	    },
	    {
	        "questionId":"1009",
	        "riskAnswer":$scope.frmDeal.riskSynAns6Model
	    },
	    {
	        "questionId":"1010",
	        "riskAnswer":$scope.frmDeal.riskSynAns7Model
	    },
	    {
	        "questionId":"1011",
	        "riskAnswer":$scope.frmDeal.riskSynAns8Model
	    },
	    {
	        "questionId":"1013",
	        "riskAnswer":$scope.frmDeal.riskSynAns9aModel
	    },
	    {
	        "questionId":"1014",
	        "riskAnswer":$scope.frmDeal.riskSynAns9bModel
	    },
	    {
	        "questionId":"1015",
	        "riskAnswer":$scope.frmDeal.riskSynAns9cModel
	    },
	    {
	        "questionId":"1016",
	        "riskAnswer":$scope.frmDeal.riskSynAns9dModel
	    },
	    {
	        "questionId":"1017",
	        "riskAnswer":$scope.frmDeal.riskSynAns10Model
	    },
	    {
	        "questionId":"1018",
	        "riskAnswer":$scope.frmDeal.riskSynAns11aModel
	    },
	    {
	        "questionId":"1019",
	        "riskAnswer":$scope.frmDeal.riskSynAns11bModel
	    },
	    {
	        "questionId":"1020",
	        "riskAnswer":$scope.frmDeal.riskSynAns12Model
	    },
	    {
	        "questionId":"1021",
	        "riskAnswer":$scope.frmDeal.riskSynAns13Model
	    },
	    {
	        "questionId":"1022",
	        "riskAnswer":$scope.frmDeal.riskSynAns14Model
	    },
	    {
	        "questionId":"1023",
	        "riskAnswer":$scope.frmDeal.riskSynAns15Model
	    },
	    {
	        "questionId":"1024",
	        "riskAnswer":$scope.frmDeal.riskSynAns16Model
	    },
	    {
	        "questionId":"1025",
	        "riskAnswer":$scope.frmDeal.riskSynAns17Model
	    },
	    {
	        "questionId":"1026",
	        "riskAnswer":$scope.frmDeal.riskSynAns18Model
	    },
	    {
	        "questionId":"1027",
	        "riskAnswer":$scope.frmDeal.riskSynAns19Model
	    },
	    {
	        "questionId":"1028",
	        "riskAnswer":$scope.frmDeal.riskSynAns20Model
	    },
	    {
	        "questionId":"1029",
	        "riskAnswer":$scope.frmDeal.riskSynAns21Model
	    }],
	    
	    "riskDevMainBasedAnswers":[{
	        "questionId":$scope.quest[0].questionaireId,
	        "answer":$scope.quest[0].mainanswers

	    },
	    {
	        "questionId":$scope.quest[1].questionaireId,
	        "answer":$scope.quest[1].mainanswers

	    },
	    {
	        "questionId":$scope.quest[2].questionaireId,
	        "answer":$scope.quest[2].mainanswers

	    },
	    {
	        "questionId":$scope.quest[3].questionaireId,
	        "answer":$scope.quest[3].mainanswers

	    },
	    {
	        "questionId":$scope.quest[4].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[5].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[6].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[7].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[8].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[9].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[10].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[11].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[12].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[13].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[14].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[15].questionaireId,
	        "answer":null
	    }
	    ]
	    	    
};
}
	var formData = new FormData();
	for (var i = 0; i < fileTest.length; i++) {
		var fileName = "file"+i.toString();
		console.log("fileName : "  + fileName);
		var file = $('input[type="file"]').get(i).files[0];
		if(file){
			formData.append('file', file,file.name);
			formData.append('name', name[i]);
		}
	}
	/*console.log(" length :" + formData.getAll('file').length);
	console.log(formData.getAll('file'));
	console.log(formData.getAll('name'));*/
	formData.append('jsonData', angular.toJson(markers));
	var insertFpDealData = function(response) {
		if(response.status == 200){
			$localStorage.rpDealVersionId = response.data; 
			BootstrapDialog.show({
	        	title : 'FP version Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'new version created sucessfully',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			$localStorage.rpDealVersionId = response.data; 
	        			$scope.getVersion($localStorage.DealModel); 
	        			
	        			window.location.reload();
	        		
	        		}
	        	}]
	        });
		}
		else{
			BootstrapDialog.show({
	        	title : 'FP Deal Creation',
	        	type : BootstrapDialog.TYPE_DANGER,
	        	message : 'Currently we are facing technical issue. Please try again later.',
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
	WebServiceFactory.insertFpDealData(formData).then(insertFpDealData);
}

$scope.update=false;
$scope.checkUpdateDealData = function(frmDeal){
	/* angular.forEach($scope.frmDeal.riskDevAnsModel, function(value,key) {
	    	if($scope.quest[key].categoryId == 1) {
	    		
	    		$scope.quest[key].devanswers=value;
	    	
	    	}
	    });
	 	console.log("Questns with ans::::::::::");
 		console.log($scope.quest);
	 	
	 angular.forEach($scope.frmDeal.riskMainAnsModel, function(value,key) {
	    	if($scope.quest[key].categoryId == 2) {
	    		
	    		$scope.quest[key].mainanswers=value;
	    	
	    	}
	    });*/
	
	console.log("Quest new array");
	console.log($scope.quest);
	/*console.log("dev ans");
	console.log($scope.dealdevans);
	console.log("main ans");
	console.log($scope.dealmainans);*/
	$scope.update= true;	
	if(frmDeal.$valid){//chk form validation valid or not
		$scope.update= false;	
		$scope.updateFpDealData();
	}
	else
		{
			$scope.UploadHiddenMainQuest=false;
			$scope.UploadHiddenDevQuest=false;
			$scope.UploadHiddenQuest=false;
		}
	
}

$scope.updateFpDealData = function(){
	//alert("update data");
	var fileTest = $("#fuUploadManualDeal,#fuAttachment_1,#fuAttachment_2");
	var name = ["MANUAL_DEAL", "ATTACHMENT_ID1","ATTACHMENT_ID2"];
	$localStorage.DealModel=$scope.frmDeal.DealModel;
	if($scope.frmDeal.onsiteHoursModel != null){
	 var onsiteHourPerDay = $.grep($scope.onshoreHours, function (oi) {
  	                       return oi.hourId == $scope.frmDeal.onsiteHoursModel;
  	                   })[0].name;
	}
	 
	
	if($scope.frmDeal.offShoreHoursModel != null){
	 var offShorehourPerDay = $.grep($scope.offshoreHours, function (offshoreHour) {
  	                       return offshoreHour.hourId == $scope.frmDeal.offShoreHoursModel;
  	                   })[0].name;
	}
	
	
	if($scope.frmDeal.workingDaysModel != null){
		 var workingDay = $.grep($scope.workingDays, function (wrkDays) {
	  	                       return wrkDays.dayId == $scope.frmDeal.workingDaysModel;
	  	                   })[0].name;
		}
	if($scope.frmDeal.txtOffshoreRiskModel != null){
		 var offshoreRisk = $.grep($scope.OffshoreRiskperc, function (ofsRisk) {
	  	                       return ofsRisk.riskid == $scope.frmDeal.txtOffshoreRiskModel;
	  	                   })[0].name;
		}
	
	 var isDealTypeManual = $scope.frmDeal.fdmanualdealModel;
	 if($scope.frmDeal.fdmanualdealModel != null) {
  	   var isDealTypeManualCheck = $.grep($scope.selectManualDeal, function (md) {
  		                       return md.id == $scope.frmDeal.fdmanualdealModel;
  		                   })[0].name;
     }
	 
	if(isDealTypeManualCheck == "Yes"){
		isDealTypeManual = 1;
	}
	else{
		isDealTypeManual = 0;
	}
	
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
	 
	 
	 $scope.getCustomerVerticalFP($scope.frmDeal.customerNameModel);
	/*var isDealTypeManual = $scope.fdmanualdealModel;
	if(isDealTypeManual == true){
		isDealTypeManual = 1;
	}
	else{
		isDealTypeManual = 0;
	}*/
	 // IF ELSE CONDITION FOR VOLUME DISCOUNT
     if($scope.frmDeal.txtUpdatevolumeModel != undefined || $scope.frmDeal.txtUpdatevolumeModel != null) {
    	 $sessionStorage.volumeDiscount= $scope.frmDeal.txtUpdatevolumeModel;  
     } 
     

	
if($scope.frmDeal.fpTypeModel == 1 || $scope.frmDeal.fpTypeModel == 2)
	{
	var markers = {
			 //.rpDealVersionId,
			"crmDealId":$scope.frmDeal.DealModel,  //.crmDealId,
			"customerVerticalMapId":$localStorage.custVrtMapId, //.customerVerticalMapId,
			"dealVersion":$scope.frmDeal.VersionNameModel,
			"rpDealVersionId":$scope.frmDeal.versionModel,
			"currencyId":$scope.currencyId,
			"customerId":$scope.frmDeal.customerNameModel,
			"agileProject":$scope.frmDeal.isAgileBasedModel,
			"syntelOnsieFacilityUsed":$scope.frmDeal.onsiteFacilityModel,
			"hcIncluded":$scope.frmDeal.isIncludeInHeadCount,
			"isNewDeal":$scope.frmDeal.dealNewOrRenewalModel,
			"bizOpsInvolved":$scope.frmDeal.isBizopsModel,
			"oldDealId":$scope.frmDeal.oldDealModel,
			"oldDealDesc":$scope.frmDeal.descriptionOldModel,
			"volumeDiscountPercent":$sessionStorage.volumeDiscount,
			"workingDays":workingDay,
			"estimatedRevenue":$localStorage.estimatedRevenue,
			"projectId":$scope.frmDeal.projectIdModel,
			"dealTypeId":1,//since it is fixed price
			"fpProjectTypeId":$scope.frmDeal.fpTypeModel,
			"riskCategoryId":$scope.frmDeal.riskCategoryModel,
			"dealProgressStatusId":$scope.frmDeal.DealModel.dealStatusId,
			"projectStartDate":$scope.frmDeal.dealStartDateModel,
			"projectEndDate":$scope.frmDeal.dealEndDateModel,
			"dealTcv":$scope.frmDeal.dealTCVModel,
			"onsiteHours":onsiteHourPerDay,
			"offShoreHours":offShorehourPerDay,
			"projectLobId":null,
			"projectIndustry":$scope.frmDeal.ddlIndustryModel,
			"isVersionFinalized":"1",
			"penaltyPercentage":$scope.frmDeal.penaltyPercent,
			"verticalId":$localStorage.VerticalId,
			"isManualDeal":isDealTypeManual,
			"manualDealObjectId":manualDealAttachment,
			"attachment1ObjectId":attachment1,
			"attachment2ObjectId":attachment2,
			"currentApprovalStatus":1,
			"pageTrackerStatus":1,
			"statusIndicator":"Draft",
			"cyberSecurity": $scope.frmDeal.securitymodel,
			"industrySolution": $scope.frmDeal.solutionModel,
			"isSynbots": $scope.frmDeal.synbotsModel,
			"synergyType":$scope.frmDeal.synergyModel,
			"dealdiscFlag":$scope.frmDeal.ddlVolumeUpdateModel,
			"fxriskoffshore":offshoreRisk,
			"fxrisknearshore":$scope.frmDeal.txtNearshoreRiskModel,
			"contingentrisk":$scope.frmDeal.txtContingentRiskModel,
			"slarisk":$scope.frmDeal.txtSLARiskModel,
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
		    },
		    {
		        "questionId":"1004",
		        "riskAnswer":$scope.frmDeal.riskSynAns1Model
		    },
		    {
		        "questionId":"1005",
		        "riskAnswer":$scope.frmDeal.riskSynAns2Model
		    },
		    {
		        "questionId":"1006",
		        "riskAnswer":$scope.frmDeal.riskSynAns3Model
		    },
		    {
		        "questionId":"1007",
		        "riskAnswer":$scope.frmDeal.riskSynAns4Model
		    },
		    {
		        "questionId":"1008",
		        "riskAnswer":$scope.frmDeal.riskSynAns5Model
		    },
		    {
		        "questionId":"1009",
		        "riskAnswer":$scope.frmDeal.riskSynAns6Model
		    },
		    {
		        "questionId":"1010",
		        "riskAnswer":$scope.frmDeal.riskSynAns7Model
		    },
		    {
		        "questionId":"1011",
		        "riskAnswer":$scope.frmDeal.riskSynAns8Model
		    },
		    {
		        "questionId":"1013",
		        "riskAnswer":$scope.frmDeal.riskSynAns9aModel
		    },
		    {
		        "questionId":"1014",
		        "riskAnswer":$scope.frmDeal.riskSynAns9bModel
		    },
		    {
		        "questionId":"1015",
		        "riskAnswer":$scope.frmDeal.riskSynAns9cModel
		    },
		    {
		        "questionId":"1016",
		        "riskAnswer":$scope.frmDeal.riskSynAns9dModel
		    },
		    {
		        "questionId":"1017",
		        "riskAnswer":$scope.frmDeal.riskSynAns10Model
		    },
		    {
		        "questionId":"1018",
		        "riskAnswer":$scope.frmDeal.riskSynAns11aModel
		    },
		    {
		        "questionId":"1019",
		        "riskAnswer":$scope.frmDeal.riskSynAns11bModel
		    },
		    {
		        "questionId":"1020",
		        "riskAnswer":$scope.frmDeal.riskSynAns12Model
		    },
		    {
		        "questionId":"1021",
		        "riskAnswer":$scope.frmDeal.riskSynAns13Model
		    },
		    {
		        "questionId":"1022",
		        "riskAnswer":$scope.frmDeal.riskSynAns14Model
		    },
		    {
		        "questionId":"1023",
		        "riskAnswer":$scope.frmDeal.riskSynAns15Model
		    },
		    {
		        "questionId":"1024",
		        "riskAnswer":$scope.frmDeal.riskSynAns16Model
		    },
		    {
		        "questionId":"1025",
		        "riskAnswer":$scope.frmDeal.riskSynAns17Model
		    },
		    {
		        "questionId":"1026",
		        "riskAnswer":$scope.frmDeal.riskSynAns18Model
		    },
		    {
		        "questionId":"1027",
		        "riskAnswer":$scope.frmDeal.riskSynAns19Model
		    },
		    {
		        "questionId":"1028",
		        "riskAnswer":$scope.frmDeal.riskSynAns20Model
		    },
		    {
		        "questionId":"1029",
		        "riskAnswer":$scope.frmDeal.riskSynAns21Model
		    }],
		    "riskDevMainBasedAnswers":[
		               				{
		            				    "questionId":$scope.quest[0].questionaireId,
		            				    "answer":null
		            				
		            				},
		            				{
		            				    "questionId":$scope.quest[1].questionaireId,
		            				    "answer":null
		            				
		            				},
		            				{
		            				    "questionId":$scope.quest[2].questionaireId,
		            				    "answer":null
		            				
		            				},
		            				{
		            				    "questionId":$scope.quest[3].questionaireId,
		            				    "answer":null
		            				
		            				},
		            		    {
		            		        "questionId":$scope.quest[4].questionaireId,
		            		        "answer":$scope.quest[4].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[5].questionaireId,
		            		        "answer":$scope.quest[5].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[6].questionaireId,
		            		        "answer":$scope.quest[6].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[7].questionaireId,
		            		        "answer":$scope.quest[7].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[8].questionaireId,
		            		        "answer":$scope.quest[8].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[9].questionaireId,
		            		        "answer":$scope.quest[9].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[10].questionaireId,
		            		        "answer":$scope.quest[10].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[11].questionaireId,
		            		        "answer":$scope.quest[11].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[12].questionaireId,
		            		        "answer":$scope.quest[12].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[13].questionaireId,
		            		        "answer":$scope.quest[13].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[14].questionaireId,
		            		        "answer":$scope.quest[14].devanswers
		            		    },
		            		    {
		            		        "questionId":$scope.quest[15].questionaireId,
		            		        "answer":$scope.quest[15].devanswers
		            		    }]
		    
	};
	}

if($scope.frmDeal.fpTypeModel == 3 || $scope.frmDeal.fpTypeModel == 4)
{
	if($scope.frmDeal.txtUpdatevolumeModel != undefined || $scope.frmDeal.txtUpdatevolumeModel != null) {
		$sessionStorage.volumeDiscount= $scope.frmDeal.txtUpdatevolumeModel;  
	     } 

var markers = {
		"crmDealId":$scope.frmDeal.DealModel, //.crmDealId,
		"dealVersion":$scope.frmDeal.VersionNameModel,
		"rpDealVersionId":$scope.frmDeal.versionModel,
		"customerVerticalMapId":$localStorage.custVrtMapId,
		"currencyId":$scope.currencyId,
		"agileProject":$scope.frmDeal.isAgileBasedModel,
		"syntelOnsieFacilityUsed":$scope.frmDeal.onsiteFacilityModel,
		"hcIncluded":$scope.frmDeal.isIncludeInHeadCount,
		"isNewDeal":$scope.frmDeal.dealNewOrRenewalModel,
		"bizOpsInvolved":$scope.frmDeal.isBizopsModel,
		"oldDealId":$scope.frmDeal.oldDealModel,
		"oldDealDesc":$scope.frmDeal.descriptionOldModel,
		"volumeDiscountPercent":$sessionStorage.volumeDiscount,
		"workingDays":workingDay,
		"estimatedRevenue":$localStorage.estimatedRevenue,
		"projectId":$scope.frmDeal.projectIdModel,
		"dealTypeId":1,
		"fpProjectTypeId":$scope.frmDeal.fpTypeModel,
		"riskCategoryId":$scope.frmDeal.riskCategoryModel,
		"dealProgressStatusId":$scope.frmDeal.DealModel.dealStatusId,
		"projectStartDate":$scope.dealStartDate,
		"projectEndDate":$scope.dealEndDate,
		"dealTcv":$scope.frmDeal.dealTCVModel,
		"onsiteHours":onsiteHourPerDay,
		"offShoreHours":offShorehourPerDay,
		"projectLobId":null,
		"penaltyPercentage":$scope.frmDeal.penaltyPercent,
		"projectIndustry":$scope.frmDeal.ddlIndustryModel,
		"isManualDeal":isDealTypeManual,
		"verticalId":$localStorage.VerticalId,
		"isVersionFinalized":"1",
		"currentApprovalStatus":1,
		"pageTrackerStatus":1,
		"statusIndicator":"Draft",
		"customerId":$scope.frmDeal.customerNameModel,
		"cyberSecurity": $scope.frmDeal.securitymodel,
		"industrySolution": $scope.frmDeal.solutionModel,
		"isSynbots": $scope.frmDeal.synbotsModel,
		"synergyType":$scope.frmDeal.synergyModel,
		"dealdiscFlag":$scope.frmDeal.ddlVolumeUpdateModel,
		"fxriskoffshore":offshoreRisk,
		"fxrisknearshore":$scope.frmDeal.txtNearshoreRiskModel,
		"contingentrisk":$scope.frmDeal.txtContingentRiskModel,
		"slarisk":$scope.frmDeal.txtSLARiskModel,
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
	    },
	    {
	        "questionId":"1004",
	        "riskAnswer":$scope.frmDeal.riskSynAns1Model
	    },
	    {
	        "questionId":"1005",
	        "riskAnswer":$scope.frmDeal.riskSynAns2Model
	    },
	    {
	        "questionId":"1006",
	        "riskAnswer":$scope.frmDeal.riskSynAns3Model
	    },
	    {
	        "questionId":"1007",
	        "riskAnswer":$scope.frmDeal.riskSynAns4Model
	    },
	    {
	        "questionId":"1008",
	        "riskAnswer":$scope.frmDeal.riskSynAns5Model
	    },
	    {
	        "questionId":"1009",
	        "riskAnswer":$scope.frmDeal.riskSynAns6Model
	    },
	    {
	        "questionId":"1010",
	        "riskAnswer":$scope.frmDeal.riskSynAns7Model
	    },
	    {
	        "questionId":"1011",
	        "riskAnswer":$scope.frmDeal.riskSynAns8Model
	    },
	    {
	        "questionId":"1013",
	        "riskAnswer":$scope.frmDeal.riskSynAns9aModel
	    },
	    {
	        "questionId":"1014",
	        "riskAnswer":$scope.frmDeal.riskSynAns9bModel
	    },
	    {
	        "questionId":"1015",
	        "riskAnswer":$scope.frmDeal.riskSynAns9cModel
	    },
	    {
	        "questionId":"1016",
	        "riskAnswer":$scope.frmDeal.riskSynAns9dModel
	    },
	    {
	        "questionId":"1017",
	        "riskAnswer":$scope.frmDeal.riskSynAns10Model
	    },
	    {
	        "questionId":"1018",
	        "riskAnswer":$scope.frmDeal.riskSynAns11aModel
	    },
	    {
	        "questionId":"1019",
	        "riskAnswer":$scope.frmDeal.riskSynAns11bModel
	    },
	    {
	        "questionId":"1020",
	        "riskAnswer":$scope.frmDeal.riskSynAns12Model
	    },
	    {
	        "questionId":"1021",
	        "riskAnswer":$scope.frmDeal.riskSynAns13Model
	    },
	    {
	        "questionId":"1022",
	        "riskAnswer":$scope.frmDeal.riskSynAns14Model
	    },
	    {
	        "questionId":"1023",
	        "riskAnswer":$scope.frmDeal.riskSynAns15Model
	    },
	    {
	        "questionId":"1024",
	        "riskAnswer":$scope.frmDeal.riskSynAns16Model
	    },
	    {
	        "questionId":"1025",
	        "riskAnswer":$scope.frmDeal.riskSynAns17Model
	    },
	    {
	        "questionId":"1026",
	        "riskAnswer":$scope.frmDeal.riskSynAns18Model
	    },
	    {
	        "questionId":"1027",
	        "riskAnswer":$scope.frmDeal.riskSynAns19Model
	    },
	    {
	        "questionId":"1028",
	        "riskAnswer":$scope.frmDeal.riskSynAns20Model
	    },
	    {
	        "questionId":"1029",
	        "riskAnswer":$scope.frmDeal.riskSynAns21Model
	    }],
	    "riskDevMainBasedAnswers":[{
	        "questionId":$scope.quest[0].questionaireId,
	        "answer":$scope.quest[0].mainanswers

	    },
	    {
	        "questionId":$scope.quest[1].questionaireId,
	        "answer":$scope.quest[1].mainanswers

	    },
	    {
	        "questionId":$scope.quest[2].questionaireId,
	        "answer":$scope.quest[2].mainanswers

	    },
	    {
	        "questionId":$scope.quest[3].questionaireId,
	        "answer":$scope.quest[3].mainanswers

	    },
	    {
	        "questionId":$scope.quest[4].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[5].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[6].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[7].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[8].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[9].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[10].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[11].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[12].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[13].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[14].questionaireId,
	        "answer":null
	    },
	    {
	        "questionId":$scope.quest[15].questionaireId,
	        "answer":null
	    }
	    ]
	    
	    
};
}
	var formData = new FormData();
	for (var i = 0; i < fileTest.length; i++) {
		var fileName = "file"+i.toString();
		console.log("fileName : "  + fileName);
		var file = $('input[type="file"]').get(i).files[0];
		if(file){
			formData.append('file', file, file.name); //formData.append('file', file);
			formData.append('name', name[i]);
		}
	}
	formData.append('jsonData', angular.toJson(markers));
	var updateFpDealData = function(response) {
		console.log("update deal data response"+response.data);
		//alert("data updated successfully");
		if(response.status == 200){
			BootstrapDialog.show({
	        	title : 'FP Deal Creation',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Data updated SucessFully',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			$localStorage.rpDealVersionId = $scope.frmDeal.versionModel;
	        			$scope.getVersion($localStorage.DealModel); //calling for reloading updated version id
	        			location.reload ()  //calling for reloading updated version id
	        		}
	        	}]
	        });
		}
		else{
			BootstrapDialog.show({
	        	title : 'FP Deal Creation',
	        	type : BootstrapDialog.TYPE_DANGER,
	        	message : 'Currently we are facing technical issue. Please try again later.',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			$localStorage.rpDealVersionId = $scope.frmDeal.versionModel;
	        			
	        		}
	        	}]
	        });
		}
	}
	WebServiceFactory.updateFpDealData(formData).then(updateFpDealData);
}

$scope.calApplicableYears = function(){ 
    
    //alert("inside calApplicableYears");
            var startdateVal =  $scope.frmDeal.dealStartDateModel; 
            var enddateVal = $scope.frmDeal.dealEndDateModel; 
            var date1 = new Date(startdateVal);
            var date2 = new Date(enddateVal);
            var timeDiff = Math.abs(date2.getTime() - date1.getTime());
            $scope.dayDifference = Math.ceil(timeDiff / (1000 * 3600 * 24));
            $scope.frmDeal.durationModel = Math.round((($scope.dayDifference/365)*100))/100;
}

$scope.isDealRenewal = false;
$scope.setValidation = function(RenewalID){
	//alert(RenewalID);
	if(RenewalID!=undefined && RenewalID == $scope.dealNewOrRenewal[1].id){
		//alert("Apply Validation: " + $scope.dealNewOrRenewal[1].name);
		$scope.getOldDealDetailsRenewal(customerId,$scope.dealStart);
		$scope.isDealRenewal = true;
		$scope.Renewal=true;
	}
	else{
		//alert("Do not Apply Validation" + $scope.dealNewOrRenewal[0].name);
		$scope.Renewal=false;
		$scope.isDealRenewal = false;
	}
	
}


$scope.getOldDealDetailsRenewal = function(customerId,startDate)
{
	var getOldDealDetails = function(response)
	{
		console.log("Old Deal Data for Renewal");
		console.log(response);
		$scope.crmOldDealDetails = response.data;
		
		/*if($scope.crmOldDealDetails!=undefined)
			{
				$scope.frmDeal.dealDescription=$scope.crmOldDealDetails[0].dealDescription; 
			}*/
		
	}
	WebServiceFactory.getOldDealDetails(customerId,startDate).then(getOldDealDetails);
};

//================================================================ created by ayyaz to upload file  

$scope.downloadFile = function(objectId){
	WebServiceFactory.downloadFileFormID(objectId);
};

$scope.searchFile = function(rpDealVersionId)
{

	angular.element("input[type='file']").val(null);
	console.log("inside File Search---"+rpDealVersionId);

	var markers = {
				"versionId" : rpDealVersionId
			};
	$http({
	    method: 'POST',
	    url: contextPath+'/RightPrice-DAS/getAttachementData',
	    dataType: 'json',
	    data: JSON.stringify(markers),  
     headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
	    }).
		    then(function(data) {
		    	if(data.data.length!=0){
		    		$scope.tableHide=false;
					$scope.tempo=[];
					$scope.tempo=data.data;
					console.log("tempo")
					console.log($scope.tempo)
					var l,j,k,m;
					$scope.attachmentTableHide=true;
					$scope.uploadTableHide=true;
					$scope.mainTableHide=true;
					$scope.devTableHide=true;
					
					for(var i=0,j=0,k=0,l=0,m=0;i<$scope.tempo.length;i++)
					{
						if($scope.tempo[i].category=="M")
						{
							$scope.MDAttachementData[j] = $scope.tempo[i];
							var strMain = $scope.MDAttachementData[j].createdOn;
						    var arrSplit = [];
						    arrSplit = strMain.split(" ");
						    $scope.MDAttachementData[j].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
							j++;
							$scope.uploadTableHide=false;
						}	
						else if($scope.tempo[i].category=="A")
						{
							$scope.AttachementData[k] = $scope.tempo[i];
							var strMain = $scope.AttachementData[k].createdOn;
						    var arrSplit = [];
						    arrSplit = strMain.split(" ");
						    $scope.AttachementData[k].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
						k++;
						$scope.attachmentTableHide=false;
						}
						else if($scope.tempo[i].category=="FPD")
						{
							$scope.devAttachementData[l] = $scope.tempo[i];
							var strMain = $scope.devAttachementData[l].createdOn;
						    var arrSplit = [];
						    arrSplit = strMain.split(" ");
						    $scope.devAttachementData[l].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
						l++;
						$scope.devTableHide=false;
						if($scope.devAttachementData.length>=1){
							$scope.alertDev=true;
						}
						}
						else if($scope.tempo[i].category=="FPM")		
						{	
						$scope.mainAttachementData[m] = $scope.tempo[i];
						var strMain = $scope.mainAttachementData[m].createdOn;
					    var arrSplit = [];
					    arrSplit = strMain.split(" ");
					    $scope.mainAttachementData[m].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
					    m++;
					    $scope.mainTableHide=false;
					    if($scope.mainAttachementData.length>=1){
							$scope.alertMain=true;
						}
						}
						
					}
					console.log("$scope.MDAttachementData")
					console.log($scope.MDAttachementData)
					console.log("$scope.AttachementData")
					console.log($scope.AttachementData)
					console.log("$scope.devAttachementData")
					console.log($scope.devAttachementData)
					console.log("$scope.mainAttachementData")
					console.log($scope.mainAttachementData)
					
					
				}
				else{
					$scope.tableHide=true;
				}
		    	
		    	
		    });
	
}

$scope.deleteFile = function(objectid,category){
	if(category=="FPD"){
			$scope.alertDev=false;
		}
	if(category=="FPM"){
		$scope.alertMain=false;
	}

	WebServiceFactory.changeActiveStatus(objectid);
	$window.location.reload();
};
$scope.uploadAttachmentData = function(frmDeal){
	
	var file = $('input[name="fuAttachFilename"]').get(0).files[0]
		$scope.upload= false;
		if(file != undefined ) {
			var category="A";
			$scope.updateFileData(category,file);
		} else {
			BootstrapDialog.show({
				title : 'FP Deal Creation - Details',
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

$scope.uploadData = function(frmDeal){
	
	var file = $('input[name="fuUploadFilename"]').get(0).files[0]
		$scope.upload= false;
		if(file != undefined ) {
			var category="M";
			$scope.updateFileData(category,file);
		} else {
			BootstrapDialog.show({
				title : 'FP Deal Creation - Details',
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

$scope.updateFileData=function(category,file){
	var name = "Template";
	var tempID = 1;	
	var versionId = $scope.frmDeal.versionModel;
	console.log("versionId")
	console.log(versionId)
	var category=category;
	var formData = new FormData();
	formData.append('file', file);
	formData.append('name', name);
	formData.append('TempId', tempID);
	formData.append('versionId', versionId);
	formData.append('category',category);
	var uploadUrl = contextPath+"/RightPrice-DAS/uploadMFile";
    $http.post(uploadUrl, formData, {
        transformRequest: angular.identity,
        headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
    }).
    then(function(data) {
    	//console.log("data.data[0]");
    	//console.log(data);
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
				title : 'FP Deal Creation - Details',
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
			title : 'FP Deal Creation - Details',
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

$scope.uploadDevData = function(frmDeal){
	
	var file = $('input[name="fuDevFilename"]').get(0).files[0]
		$scope.upload= false;
		if(file != undefined ) {
			var category="FPD";
			if($scope.alertDev!=true){
				$scope.alertDev=true;
				$scope.updateFileData(category,file);
			
			}else{

				BootstrapDialog.show({
					title : 'FP Deal Creation - Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Please delete previous File to upload new File",
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
			
		} else {
			BootstrapDialog.show({
				title : 'FP Deal Creation - Details',
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

$scope.uploadMainData = function(frmDeal){
	var file = $('input[name="fuMainFilename"]').get(0).files[0]
		$scope.upload= false;
		if(file != undefined ) {
			var category="FPM";
			if($scope.alertMain!=true){
				$scope.alertMain=true;
			$scope.updateFileData(category,file);
			}else{

				BootstrapDialog.show({
					title : 'FP Deal Creation - Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Please delete previous File to upload new",
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
		} else {
			BootstrapDialog.show({
				title : 'FP Deal Creation - Details',
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

$scope.clearFiles = function(){
	$scope.AttachementData=[];
	$scope.devAttachementData=[];
	$scope.mainAttachementData=[];
	$scope.MDAttachementData=[];
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
// ================================================================  ayyaz changes end
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
$scope.setvolume = function(volumeId){	
	
	if(volumeId!=undefined && volumeId == 2)
	{
		
		$scope.isUpdate = false;
	}
	else
	{
		
		$scope.isUpdate = true;
	}	
	if(volumeId == 2) 
	{		
		$scope.hidevolumetxt = true;
	}
	else 
	{
		$scope.hidevolumetxt = false;
	}	
	
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