+//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("RateCardCreationController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		$scope.customer=[];
		$scope.rcId=0;
		var contextPath = "/RightPrice-DAS";
		$scope.uploadBtnDisable=true;
 		var getGFTCustomer = function(response) {
 		 	console.log("customer response");
 		 	//console.log(response.data);
		   $scope.customer = response.data;
		   console.log("customer::::::::::::::::::");
		   console.log(response.data);
		 };
	 WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
		
	 $scope.resetFormDataOnCustomerChange=function(){
  		// $scope.frmRateCard.action="Please select";
  		$localStorage.tempAction = null;
  		$scope.frmRateCard.action = null;
  		 $scope.resetFormDataOnActionChange();
  	 };
	 
 	 //reset form data on change of customer
 	 $scope.resetFormDataOnActionChange = function(){
     		
 		 	$scope.rateCardId=[];
     		$scope.frmRateCard.ratecardname="";
     		$scope.frmRateCard.rateCardId=null
     		$scope.frmRateCard.rateCardId=null
     		$scope.frmRateCard.ddlCountryModel=null;
     		$scope.frmRateCard.ddlCityModel=null;
 	 };
 	 
  	$scope.getRCId=function(customerId){
 		//console.log("getCustomerVerticalId");
 		angular.forEach($scope.customer, function(value, key){
 		      if(value.customerId == customerId) {
 		    	  $scope.tempCustomerVerticalMapId = value.customerVerticalMapId;
 		    	  $scope.tempOffshoreWorkHours = value.customer.offshoreWorkHours;
 		    	  $scope.tempOnsiteWorkHours = value.customer.onsiteWorkHours;
 		    	  $scope.frmRateCard.cpcCharges = value.customer.cpcCharges;
 		    	  $scope.frmRateCard.volumediscount = value.customer.volumeDiscount;
 		    	  $scope.frmRateCard.iqnCharges = value.customer.ipcCharges;
 		    	  $scope.VerticalId = value.verticalId;
 		      }
 		   });
     	$sessionStorage.customerVerticalMapId = $scope.tempCustomerVerticalMapId;
     	console.log("Vertical Id ")
     	console.log($scope.tempCustomerVerticalMapId)
     	$scope.getRateCard($scope.tempCustomerVerticalMapId);
 	 }
  	
  	
  	$scope.getRateCard = function(customerVerticalMappingId){
  		var getRateCards = function(response) {
        //	console.log("status : "+response.status);
        	if( response.status == 205){
        		$scope.frmRateCard.customer=undefined;
        		$scope.versionAttachment=[];
				  BootstrapDialog.show({
							title : 'Rate Card Creation',
							type : BootstrapDialog.TYPE_DANGER,
							message : 'No rate cards available for selected Customer.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									//$window.location.reload();
								}
							} ]
						});
				  }
        	else{
        		console.log("data in RC response");
        		console.log(response.data)
        		$scope.rateCardId=response.data;
        	}
        };
        WebServiceFactory.getRateCardsInfo(customerVerticalMappingId).then(getRateCards);
    };
    
    $scope.getRateCardDetailsFromRCId=function(rateCardId){
    	$scope.frmRateCard.ddlCountryModel=null;
    	$scope.frmRateCard.ddlCityModel=null;
    	$scope.rcId = rateCardId;
    	angular.forEach($scope.rateCardId, function(value, key){
		      if(value.rcId == rateCardId) {    
		    	  $scope.frmRateCard.ratecardname= value.rcName;
		      }
    	  });
        
        //Web Service to get Country
        var getRateUtilizationCountry = function(response) {
        	 $scope.versionAttachment=[];
        	$scope.city=[];
    	    $scope.country = [];
    	  //  $scope.country[0] =response.data[0].city.country;
    	    $scope.country = response.data;
    	    
    	    console.log(" PS :: $scope.country");
    	    console.log($scope.country);
    	    
    	    if(response.data.length!=0){	    	
    	    if(response.data.length == 1){
    	    	$scope.getCity($scope.country[0].countryId);
    	    	}else if(response.data.length > 1){
    	    	var j=0;
    	    	var flag=0;
    	    	$scope.country=[];
    	    	$scope.countries=[];
    	    	$scope.temp=[];
    	    	
    	    	angular.forEach(response.data,function(value2, key2){
    	    		$scope.countries[key2]=response.data[key2].city.country;
	    	    	});
    	    	
    	    	console.log("$scope.countries array");
    	    	console.log($scope.countries);
    	    	var key3=0;
    	    	var flag=0;
    	    	
    	  
    	    	/*    angular.forEach($scope.countries,function(value, key){
    	    	    	angular.forEach($scope.country,function(value2, key2){
   	    	    		if($scope.countries[key].countryId != $scope.country[key2].countryId && flag==0)
   	    	    		{
   	    	    			$scope.country[key3]=$scope.countries[key];
   	    	    			key3++;
   	    	    		}else if($scope.countries[key].countryId == $scope.country[key2].countryId  && flag==0 ){
   	    	    			$scope.country[key3]=$scope.countries[key];
   	    	    			key3++;
   	    	    			flag++;
   	    	    		}
   	    	    	});
   	    	    });
   	    	    */
    	    		console.log("Countiessss.........................")
    	    		 console.log($scope.country)
    	    	}
    	    }else{
    	    	$scope.frmRateCard.rateCardId=undefined;
    	    	$scope.frmRateCard.ratecardname=undefined;
    	    	$scope.versionAttachment=[];
    	    	BootstrapDialog.show({
    	        	title : 'GFT - Upload Rate Card Excel',
    	        	type : BootstrapDialog.TYPE_DANGER,
    	        	message : 'No Country Available selected Rate Card',
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
        WebServiceFactory.getRateGFTCountry($scope.rcId).then(getRateUtilizationCountry);
    }

    
    //Web Service to get City
      $scope.getCity = function(countryId){
    		 $scope.versionAttachment=[];
      	var getRateUtilizationCity = function(response) {
      		//var b= $scope.getMultiplyFactor(countryId);
      		$scope.city=[];
      	    $scope.city = response.data;
      	    if($scope.city!=undefined){
      	    	$scope.uploadBtnDisable=false;
      	     }
      	    if($scope.country.length == 1 && $scope.city.length == 1){
      	    	$scope.frmRateCard.ddlCountryModel = $scope.country[0].countryId;
      	    	$scope.frmRateCard.ddlCityModel = $scope.city[0].cityId;
      	    	if($scope.frmRateCard.ddlCityModel != null || $scope.rcId != null) {
      	    	$scope.cityAttachement($scope.frmRateCard.ddlCityModel,$scope.rcId);
      	    	}
      	    }
      	    else{
      	  $scope.frmRateCard.ddlCityModel = undefined;
      	    }
      	   // disable the offshore on the basis of the city
      	};
      	WebServiceFactory.getRateUtilizationCity($scope.rcId,countryId).then(getRateUtilizationCity);
      }
	 
      $scope.getFileData = function(ddlCityModel){
    	  $scope.versionAttachment=[];
    		  $scope.cityAttachement(ddlCityModel,$scope.rcId);
      }
      
      //================================================= File Upload calling Method..........................................
      
      
      $scope.uploadData = function(frmRateCard){
			$scope.upload= true;
			
			if(frmRateCard.$valid){
				$scope.upload= false;
				if($scope.frmRateCard.fuUploadFilenameModel != true) {
					$scope.uploadFileToUrl();
				} else {
					BootstrapDialog.show({
						title : 'GFT - Upload Rate Card Excel',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please upload File and Click on Update Button",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(
									dialogRef) {
								dialogRef.close();
								window.location = "RCGFTUpload";
							}
						} ]
					});	
				}
				} 
		};	
		
	      //================================================= File Upload define Method..........................................
		
		// Upload File
		 $scope.uploadFileToUrl = function(){
		    	var fileTest = $("#fuUploadFilename");	
		    	var name = "Template";
		    	var tempID = 1;	
				var file = $('input[name="fuUploadFilename"]').get(0).files[0];
				var formData = new FormData();
				formData.append('file', file);
				formData.append('name', name);
				formData.append('TempId',tempID);
				formData.append('rpDealVersionId', $scope.rcId);
				formData.append('dealAutoTowerId',$scope.frmRateCard.ddlCityModel);
				formData.append('docType',0);
				formData.append('countryId', $scope.frmRateCard.ddlCountryModel);
		    	//console.log(file);
		    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadManualFile_GFT";
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
			    		$scope.getFileData($scope.frmRateCard.ddlCityModel);
			    		
						BootstrapDialog.show({
							title : 'GFT - Upload Rate Card Excel',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : customMessage,
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(
										dialogRef) {
									dialogRef.close();
									//window.location = "RCGFTUpload";
								}
							} ]
						});	
					} 
			    	else if(data.status == 205){
							BootstrapDialog.show({
							title : 'GFT - Upload Rate Card Excel',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Currently We are facing technical issues, please try again later.",
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
						title : 'GFT - Upload Rate Card Excel',
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
		 };
		
		 
		    $scope.cityAttachement = function(cityId,rcId) {
		    	
		    	var cityAttachement =function(response)
		    	{
		    		console.log("Attachment Data");
		    		console.log(response);
		    		$scope.versionAttachment=response.data;
		    		if($scope.versionAttachment!=undefined)
		    		{
		    			
		    			$filter('orderBy')($scope.versionAttachment, 'createdOn');
		    			/*angular.forEach($scope.versionAttachment, function(value, key) {
		    				angular.forEach($scope.towerdetails, function(value2, key2) {
		    					if($scope.versionAttachment[key].dealAutoTowerId==$scope.towerdetails[key2].dealautoTowerId)
		    					{
		    						$scope.versionAttachment[key].towerName=value2.towerName;
		    					}
		    				});
		    			});*/
		    			
		    			angular.forEach($scope.versionAttachment, function(value, key) {
		    					if($scope.versionAttachment[key].docType != undefined) {
		    						var strMain =$scope.versionAttachment[key].createdOn;
		    						var arrSplit = [];
		    						arrSplit = strMain.split(" ");
		    						$scope.versionAttachment[key].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
		    					}
		    			});
		    			
		    		}
		    		
		    	};
		    	WebServiceFactory.cityAttachement(cityId,$scope.rcId).then(cityAttachement);
		    }
		    
		    
		    
		    //================================================== code to download and delete file ============================================= 
		    $scope.downloadFile = function(objectId){
	     		WebServiceFactory.downloadFileFormID(objectId);
	     	};
		
	     	
	     	
	     	  
	    	$scope.downloadFileWithFileName = function(dealAttachmentId){
	    		WebServiceFactory.downloadFileWithFileName(dealAttachmentId);
	    	};
	    	
	    	$scope.deleteManualFile = function(attachementId){
	    		var deleteManualFile = function(response) {
	    			$scope.deleteResponse = response;
	    			
	    			if($scope.deleteResponse.status == 200){
	    				$scope.message = $scope.deleteResponse.data;
	        	        BootstrapDialog.show({
	        	        	title : 'Rate Card Creation - PM Rate Details',
	        	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	        	message : $scope.message,
	        	        	closable : false,
	        	        	buttons : [{
	        	        		label : 'OK',
	        	        		action : function(dialogRef) {
	        	        			dialogRef.close();
	        	        			
	        	        		}
	        	        	}]
	        	        });
	        	        $scope.getFileData($scope.frmRateCard.ddlCityModel);
	        		}else{
	        			$scope.message = $scope.deleteResponse.data;
	        			BootstrapDialog.show({
	        	        	title : 'Rate Card Creation - PM Rate Details',
	        	        	type : BootstrapDialog.TYPE_DANGER,
	        	        	message : $scope.message,
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
	    		WebServiceFactory.deleteManualFile(attachementId).then(deleteManualFile);
	    	};
	         
	     	
	     	
	}]);