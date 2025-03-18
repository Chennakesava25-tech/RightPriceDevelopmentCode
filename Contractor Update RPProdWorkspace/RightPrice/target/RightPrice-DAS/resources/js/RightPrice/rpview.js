//var app = angular.module('MyDashBoardApp', ['ngStorage']);
app.controller("RPviewController", ['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory,$localStorage, $sessionStorage,$index){
	$scope.isMemberExist = false;
	$scope.isLevelApproverDisabled = false;
	$scope.btnDisable = false;
	$scope.isDeleteShow = true;
	$scope.getUserRoles = []
	$scope.getUserRolesData = [];
	$scope.towerCount=[];
	$scope.rateCardData=[];
	
	$sessionStorage.currentUser = null;
	$scope.verticalId="";
	var userType ="";
	$scope.onloadFlag=0;
	$scope.deligUser  = 0;
	$scope.verticalArray = [];
	$scope.verticalifo = [];
	
	$scope.viewdealData = [];
	$scope.viewrcData = [];
	
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
				
		$scope.getCustomerBasedRecordsfordeals(0);
		$scope.getCustomerBasedRecords(0);		
		
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
			|| userRoles[0] == 'LEVELl1User' || userRoles[0] == 'LEVELl2User'){
	 		var getGFTCustomer = function(response) {
			   $scope.customer = response.data;
			 //  $scope.customerVerticalMapping = response.data;
			  /* if(userRoles[0] == 'GFT' || userRoles[0] == 'Audit'){
				   $scope.getVerticalMemberDetail($scope.user);			   
			   }
			   else if(userRoles[0] == 'CEO' || userRoles[0] == 'CDO' || userRoles[0] == 'LEVELl1User' || userRoles[0] == 'LEVELl2User')
			   {
				   $scope.getLeadershipDetails();  
			   }
			   else if(userRoles[0] == 'RiskManagers')
			   {
				   $scope.getRiskManagersMemberDetail($scope.user);  
			   }*/
			 };
		 WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
	 	
		}
		else  if(userRoles[0] == 'BUH' || userRoles[0] == 'DUH' || userRoles[0] == 'RiskManagers')
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
							if($scope.user == value.deliveryHeadId || $scope.user == value.buHeadId ||  $scope.user == value.RiskManagersPersonId) {
								$scope.verticalArray.push({
									"verticalId" : value.verticalId
								})
							}
						} else {
							if($sessionStorage.currentUser == value.deliveryHeadId || $sessionStorage.currentUser == value.buHeadId || $sessionStorage.currentUser == value.RiskManagersPersonId) {
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
	
	
	

	$scope.getCustomerBasedRecordsfordeals = function(customerId){
//		$scope.rateCardData = [];
//		$scope.dealData = [];
		
		var  getviewdealdetails = function(response) 
		{
			
		$scope.selectedCustomer =response.data;
		console.log("RPView");
		console.log(response.data);
		
		$scope.viewdealData= response.data;

		 
if(response.data!=null && response.data!=''){
		crmdlLen = $scope.viewdealData.length;    
		var arr = [];
		var j=0;

		for(var i=0; i<crmdlLen ;i++){
				var dateStart =  $scope.viewdealData[i].dealStartDate; //$scope.frmDeal.DealModel.dealStartDate ;    
			    var date = new Date(dateStart.substring(0,10));
			    $scope.viewdealData[i].dealStartDate = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');  
			    
			    
			    var dateEnd = $scope.viewdealData[i].dealEndDate;//$scope.frmDeal.DealModel.dealEndDate ;        
			    var date = new Date(dateEnd.substring(0,10));
			    $scope.viewdealData[i].dealEndDate = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy'); 
		}

		    $scope.frmDashboard.dealId= $scope.viewdealData[j].dealId; 
		    $scope.frmDashboard.customer_Name= $scope.viewdealData[j].customer_Name; 
		    
		   
		    $scope.frmDashboard.industryName= $scope.viewdealData[j].industryName;
		    $scope.frmDashboard.industry= $scope.viewdealData[j].industry;
		    
		    $scope.frmDashboard.currency_Code= $scope.viewdealData[j].currency_Code; 
		    $scope.frmDashboard.dealTCV= $scope.viewdealData[j].dealTCV; 
		    $scope.frmDashboard.country_Name= $scope.viewdealData[j].country_Name; 
		    $scope.frmDashboard.city_Name= $scope.viewdealData[j].city_Name; 
		}else if(response.data==''){
		
		}else{
			$scope.getCustomerBasedRecordsfordeals(0);
		}	    
		   
		
		   
		    
	//	var currentRoleId = $scope.currentRateCardRoleId;
	//	var currentStatus = $scope.currentRateCardStatus;
		
		/*$scope.getSummaryCountBasedOnVerticalId();
		$scope.getDealCountBasedOnVerticalId();*/
		}
		WebServiceFactory.getviewdealdetails(customerId).then(getviewdealdetails);
		
	}
	

	
	//--------------Rcdata-------------------------
	
	$scope.getCustomerBasedRecords = function(customerId){
//		$scope.rateCardData = [];
//		$scope.dealData = [];
		
		/*if($scope.frmDashboard.customerNameModel!=undefined || $scope.frmDashboard.customerNameModel!=null){
			angular.forEach($scope.customerVerticalMapping, function (value, key) {
				if($scope.frmDashboard.customerNameModel.customerId == value.customerId){
		            $scope.customerMappingInDeal.push({
		            	"customerId" : value.customerId,
		            	
		     	   });
				}
	        });
		}*/
		
		var  getviewrcdetails = function(response) 
		{
			
		$scope.selectedCustomer =response.data;
		console.log("rcdataView");
		console.log(response.data);
		
		$scope.viewrcData= response.data;

		 
		if(response.data!=null && response.data!=''){
		crmdlLen = $scope.viewrcData.length;    
		var arr = [];
		var j=0;

		for(var i=0; i<crmdlLen ;i++)
		  {
			
			var dateStart =  $scope.viewrcData[i].rcStartDate; //$scope.frmDeal.DealModel.dealStartDate ;
		    var date = new Date(dateStart.substring(0,10));
		    $scope.viewrcData[i].rcStartDate = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');  
		    
		    var dateEnd = $scope.viewrcData[i].rcEndDate;//$scope.frmDeal.DealModel.dealEndDate ;        
		    var date = new Date(dateEnd.substring(0,10));
		    $scope.viewrcData[i].rcEndDate = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy'); 
		  
		     /* var rcId= $scope.viewrcData[i].rcId;        
		      arr.push(rcId);
		           
		      if(selectedValueCRM == arr[i])
		      {  
			    j = arr.indexOf(arr[i]);
		      
		           break;
		      }*/    
		  } 
		    $scope.frmDashboard.rcId= $scope.viewrcData[j].rcId; 
		    $scope.frmDashboard.customer_Name= $scope.viewrcData[j].customer_Name; 
		    $scope.frmDashboard.rcName= $scope.viewrcData[j].rcName;  
		    
		   
		    
		    
		    $scope.frmDashboard.currency_Code= $scope.viewrcData[j].currency_Code; 
		    $scope.frmDashboard.tvc= $scope.viewrcData[j].tvc; 
		    $scope.frmDashboard.country_Name= $scope.viewrcData[j].country_Name; 
		    $scope.frmDashboard.city_Name= $scope.viewrcData[j].city_Name; 
		    
		    $scope.frmDashboard.industryName= $scope.viewrcData[j].industryName;
		    $scope.frmDashboard.isItKpo= $scope.viewrcData[j].isItKpo;
		    
		}else if(response.data==''){
		
		}else{
			$scope.getCustomerBasedRecords(0);
		}  
		
		 
		    
	//	var currentRoleId = $scope.currentRateCardRoleId;
	//	var currentStatus = $scope.currentRateCardStatus;
		
		/*$scope.getSummaryCountBasedOnVerticalId();
		$scope.getDealCountBasedOnVerticalId();*/
		}
		WebServiceFactory.getviewrcdetails(customerId).then(getviewrcdetails);
		
	}
	

	$scope.showRateCardDetails_rc = function(rcId){
		
		$localStorage.rcId = rcId;
	
		
		var browser = window.navigator.appVersion;

        
		window.open('RateCardCreationDetails_New', '_blank','$localStorage.rcId');

	}
	
	$scope.showdealDetails_d = function(crmDealId)
	{
		$localStorage.DealModel = null;
		
		if($localStorage.DealModel == null)
		{
			$localStorage.DealModel=crmDealId ;
				
			
			var browser = window.navigator.appVersion;
			
			
				

				
					window.open('DealCreationDetails_RP', '_blank', '$localStorage.DealModel');
			
		
		}
	}
			  /*$scope. = function()
				{
					sessionStorage.setItem('dashboardData', 1 );
			 	   sessionStorage.setItem('crmDealId',response.data[0].crmDealId);
			 	   sessionStorage.setItem('accountId',response.data[0].customerId);
					//$localStorage.crmDealId = dealId;
					alert($localStorage.crmDealId);
					var browser = window.navigator.appVersion;

					
						window.open('DealCreationDetails_RP', '_blank','$sessionStorage.crmDealId','$sessionStorage.accountId');
						
				}*/
	$scope.getDataOnSearchview = function(rcId)
	{
		$scope.rateCardData = [];
		$scope.rateCardIDOnSearch=rcId;
		var getDataOnSearchview = function(response){
			
		$scope.rateCardData = null;
			if(response.status == 200){
				console.log(response.data);
				$scope.tempRateCardData = response.data;
				//alert($scope.tempRateCardData);
				console.log($scope.tempRateCardData);
				for(var i=0;i<$scope.tempRateCardData.length ;i++){
				var dateStart =  $scope.tempRateCardData[i].rcStartDate; //$scope.frmDeal.DealModel.dealStartDate ;
			    var date = new Date(dateStart.substring(0,10));
			    $scope.tempRateCardData[i].rcStartDate = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');  
			    
			    var dateEnd = $scope.tempRateCardData[i].rcEndDate;//$scope.frmDeal.DealModel.dealEndDate ;        
			    var date = new Date(dateEnd.substring(0,10));
			    $scope.tempRateCardData[i].rcEndDate = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy'); 
			  
			     /* var rcId= $scope.viewrcData[i].rcId;        
			      arr.push(rcId);
			           
			      if(selectedValueCRM == arr[i])
			      {  
				    j = arr.indexOf(arr[i]);
			      
			           break;
			      }*/    
			  
			    $scope.frmDashboard.rcId= $scope.tempRateCardData[i].rcId; 
			    $scope.frmDashboard.customer_Name= $scope.tempRateCardData[i].customer_Name; 
			    $scope.frmDashboard.rcName= $scope.tempRateCardData[i].rcName;  
			    
			   
			    $scope.frmDashboard.industryName= $scope.tempRateCardData[i].industryName;
			    $scope.frmDashboard.isItKpo= $scope.tempRateCardData[i].isItKpo;
			    
			    $scope.frmDashboard.currency_Code= $scope.tempRateCardData[i].currency_Code; 
			    $scope.frmDashboard.tvc= $scope.tempRateCardData[i].tvc; 
			    $scope.frmDashboard.country_Name= $scope.tempRateCardData[i].country_Name; 
			    $scope.frmDashboard.city_Name= $scope.tempRateCardData[i].city_Name; 
			    
				}
			
			}else{
				BootstrapDialog.show({
					title : 'RPviewDetails',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Please Enter valid Rate Card ID.',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							window.location="RPviewDetails";
						}
					}]
				});
			}

			$scope.viewrcData = $scope.tempRateCardData;
			console.log($scope.viewrcData);
			}	
	WebServiceFactory.getDataOnSearchview(rcId).then(getDataOnSearchview);
	}
	 
	$scope.getDealDataOnSearchview = function(dealId)
	{
		
		
		var getDealDataOnSearchview = function(response){
			
		
			if(response.status == 200){
				console.log(response.data);
				$scope.tempDealData = response.data;
				//alert($scope.tempRateCardData);
				console.log($scope.tempDealData);
				for(var i=0;i<$scope.tempDealData.length ;i++){
					var dateStart =  $scope.tempDealData[i].dealStartDate; //$scope.frmDeal.DealModel.dealStartDate ;    
				    var date = new Date(dateStart.substring(0,10));
				    $scope.tempDealData[i].dealStartDate = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');  
				    
				    
				    var dateEnd = $scope.tempDealData[i].dealEndDate;//$scope.frmDeal.DealModel.dealEndDate ;        
				    var date = new Date(dateEnd.substring(0,10));
				    $scope.tempDealData[i].dealEndDate = $filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy'); 
			

			    $scope.frmDashboard.dealId= $scope.tempDealData[i].dealId; 
			    $scope.frmDashboard.customer_Name= $scope.tempDealData[i].customer_Name; 
			    
			   
			    
			    $scope.frmDashboard.industryName= $scope.tempDealData[i].industryName;
			    $scope.frmDashboard.industry= $scope.tempDealData[i].industry;
			    
			    $scope.frmDashboard.currency_Code= $scope.tempDealData[i].currency_Code; 
			    $scope.frmDashboard.dealTCV= $scope.tempDealData[i].dealTCV; 
			    $scope.frmDashboard.country_Name= $scope.tempDealData[i].country_Name; 
			    $scope.frmDashboard.city_Name= $scope.tempDealData[i].city_Name;
			    
				}
			
			}else{
				BootstrapDialog.show({
					title : 'RPviewDetails',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Please Enter valid Deal ID.',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							window.location="RPviewDetails";
						}
					}]
				});
			}

			$scope.viewdealData = $scope.tempDealData;
			console.log($scope.viewdealData);
			}	
	WebServiceFactory.getDealDataOnSearchview(dealId).then(getDealDataOnSearchview);
	}
		    
	
	
}]);