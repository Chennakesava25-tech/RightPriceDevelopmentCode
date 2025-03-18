//var app = angular.module('RightPrice', ['ngMessages']);
		app.controller("MasterCountryForexController", ['$scope','$location','$anchorScroll','$http','$window','$filter','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,$filter,WebServiceFactory, $index){
			var contextPath = $window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));
			 $scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.countryData=[];
			 $scope.syntelFacilityCostOfUS = 0;
			 $scope.exchangeRateOfUS = 0;
			 $scope.visalabel = [];
			 $scope.isRequired=false;
			 $scope.isDisabled=false;
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            };
             $scope.ShowHideAdd = function () {
                $scope.AddHidden = $scope.AddHidden ? false : true;
                $scope.onSubmit= false;	
        		$scope.addCountryForm.countryModelName=null;
        		$scope.addCountryForm.currencyNameModel=null;
        		$scope.addCountryForm.rpCurrencyIdModel=null;
        		$scope.addCountryForm.exchangeRateModel=null
        		$scope.addCountryForm.facilityCostModel=null;
        		$scope.addCountryForm.countryCodeModelName=null;
        		$scope.addCountryForm.isOffshoreSiteModel=false;
        		$scope.addCountryForm.addOnlyManRCDeal = false;
            };
             $scope.ShowHideUpdate = function () {
                $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
                $scope.update=false;
        		$scope.updateCountryForm.ddlCountryModel=null;
        		$scope.updateCountryForm.ddlCurrencyNameModel=null;
        		$scope.updateCountryForm.exchangeRateUpdateModel=null;
        		$scope.updateCountryForm.facilityCostUpdateModel=null;
        		$scope.updateCountryForm.countryCodeModelName=null;
        		$scope.updateCountryForm.txtCurrencyNameModel=null;
        		$scope.updateCountryForm.rpCurrencyIdModel=null;
        		$scope.updateCountryForm.isOffshoreSiteModel=false;
        		$scope.addCountryForm.addOnlyManRCDeal = false;
            };
             $scope.ShowHideUpload = function () {
                $scope.UploadHidden = $scope.UploadHidden ? false : true;
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
	
	var getCurrency = function(response) {
		console.log("getCurrency");
  		console.log(response);
	    $scope.currency = response.data;
	};
	WebServiceFactory.getCurrency().then(getCurrency);
	
	var getCountryDetail = function(response) 
	{
		console.log("get country data");
  		console.log(response);
  		$scope.countryData = response.data;
  		
  		$scope.syntelFacilityCostOfUS = $scope.countryData[0].syntelFacilityCost;
  		$scope.exchangeRateOfUS = $scope.countryData[0].exchangeRate;
  	/*	for(i=0;i<response.data.length;i++)
		{
			if(response.data[i].isOnlyManRCDeal == 1)
				$scope.countryData[i].isOnlyManRCDeal = true;
				//$scope.data.isOnlyManRCDeal = true;
			else
				$scope.countryData[i].isOnlyManRCDeal = false;
		    
		}
  		*/
		for(i=0;i<response.data.length;i++)
		{
			var num = parseFloat(response.data[i].exchangeRate);						    
		    var lowAll = num.toFixed(2);
		    $scope.countryData[i].exchangeRate = lowAll;
		    
		    num = parseFloat(response.data[i].syntelFacilityCost);						    
		    var medAll = num.toFixed(2);
		    $scope.countryData[i].syntelFacilityCost = medAll;
		    
		    $scope.countryData[i].countryName = $scope.countryData[i].countryName.toUpperCase();
		}
	};
	WebServiceFactory.getCountryDetail().then(getCountryDetail);
	
	var getCountryData = function(response) {
		console.log("get country data");
  		console.log(response);
  		$scope.countryDetails = response.data;
  		
  		for(i=0;i<response.data.length;i++)
		{
  			$scope.countryDetails[i].isdis=true;
			if($scope.countryDetails[i].countryId == 1)
			{
	  		$scope.countryDetails[i].isdis=false;
	  		}

			if(response.data[i].isOnlyManRCDeal == 1)
				$scope.countryDetails[i].isOnlyManRCDeal = true;
				//$scope.data.isOnlyManRCDeal = true;
			else
				$scope.countryDetails[i].isOnlyManRCDeal = false;
			
			if(response.data[i].isActive == 1)
				$scope.countryDetails[i].isActive = true;
				//$scope.data.isOnlyManRCDeal = true;
			else
				$scope.countryDetails[i].isActive = false;
		    
			var num = parseFloat(response.data[i].exchangeRate);						    
			var lowAll = num.toFixed(2);
			$scope.countryDetails[i].exchangeRate = lowAll;
			
			num = parseFloat(response.data[i].syntelFacilityCost);						    
			var medAll = num.toFixed(2);
			$scope.countryDetails[i].syntelFacilityCost = medAll;
			
			$scope.countryDetails[i].countryName = $scope.countryDetails[i].countryName.toUpperCase();
		}
  		
	};
	WebServiceFactory.getCountryForexData().then(getCountryData);

	$scope.onSubmit= false;	
	$scope.save = function(addCountryForm){
		$scope.onSubmit= true;
		if(addCountryForm.$valid){
			$scope.onSubmit= false;	
			$scope.addCountryDetails();
		}
	};
	
	$scope.addCalcuPremium = function(exchangeRate){
		if(exchangeRate!=0){
			$scope.addCountryForm.facilityCostModel = $filter('number')($scope.syntelFacilityCostOfUS*($scope.exchangeRateOfUS/exchangeRate),2);
		}else{
			$scope.addCountryForm.facilityCostModel=0;
		}
	}
	
	$scope.calUpdatePremium = function(facilityArray,exchangeRate,index){
		if(facilityArray.countryName != "USA") {
			if(exchangeRate!=0){
				$scope.countryDetails[index].syntelFacilityCost = $filter('number')($scope.syntelFacilityCostOfUS*($scope.exchangeRateOfUS/exchangeRate),2);
		}else{
			$scope.countryDetails[index].syntelFacilityCost=0;
		}
			
		}
	}
	
	$scope.calUpdateSyntelPremium = function(){
		for (var i = 0;i<$scope.countryDetails.length; i++)
		{
			$scope.syntelFacilityCostOfUS = $scope.countryDetails[0].syntelFacilityCost;
			$scope.exchangeRateOfUS = $scope.countryDetails[0].exchangeRate;
			$scope.exchangeRateData = $scope.countryDetails[i].exchangeRate;
			if($scope.countryDetails[i].countryName != "USA") 
			{
				$scope.countryDetails[i].syntelFacilityCost = $filter('number')($scope.syntelFacilityCostOfUS*($scope.exchangeRateOfUS/$scope.exchangeRateData),2);
			}
		}
	}
	
	$scope.addCountryDetails = function()
	{
		$scope.isOffShoreSite;
		$scope.isOnlyManRCDeal;
		$scope.visa;		
		$scope.isOnlyManRCDeal = $scope.addCountryForm.addOnlyManRCDeal ? 1 :0;
		/*if($scope.addCountryForm.addOnlyManRCDeal){
			$scope.isOnlyManRCDeal = 1;
		}
		else{
			$scope.isOnlyManRCDeal = 0;
		}*/
		/*angular.forEach($scope.visa , function(value,key) 
				{
					
					angular.forEach($scope.addCountryForm.addVisaType , function(value1,key1) 
							{
								$scope.keynumber=parseInt(key1);
								if($scope.keynumber==$scope.visa[key].id)
									{
										$scope.visa[key].isStaffing = 1;
									}
								
							});
		});*/
		
		/*angular.forEach($scope.visa , function(value,key) 
		{
			
			
			
			if($scope.visa[key].visaLabels != "")
			{
				$scope.visalabel.push
				({
					
					"appCode": "RP",
					"codeType": "Visa_Type",
					"codeName": $scope.visa[key].visaId,
					"description": $scope.visa[key].visaLabels,
					"isStaffing":$scope.visa[key].isStaffing
					
				})
			}
		});*/
		
		console.log("$scope.visalabel");
		console.log($scope.visalabel);
		
		var markers = {
		"countryName": $scope.addCountryForm.countryModelName.toUpperCase(),
		"countryShortName": $filter('uppercase')($scope.addCountryForm.countryCodeModelName),
		"currencyCode": $filter('uppercase')($scope.addCountryForm.currencyNameModel),
		"exchangeRate":$scope.addCountryForm.exchangeRateModel,
		"currencyId":$scope.addCountryForm.rpCurrencyIdModel,
		"syntelFacilityCost":$scope.addCountryForm.facilityCostModel,
		"isOffshoreSite": $scope.isOffShoreSite,
		"isOnlyManRCDeal": $scope.isOnlyManRCDeal
		/*"visaTypeArray":$scope.visalabel,
		"visaLabelArray":$scope.visalabel*/
		};
		console.log("Add Counrty Marker..................");
		console.log(markers);
		var addCountryDetails = function(response) 
		{
			if(response.status == 200)
			{
				BootstrapDialog.show
				({
					title : 'Country Details',
					type : BootstrapDialog.TYPE_PRIMARY,
					message : 'Data inserted SucessFully',
					closable : false,
					buttons : 
					[{
						label : 'OK',
						action : function(dialogRef) 
						{
							dialogRef.close();
							location.reload();
						}
					}]
    	        });
	        }
			else if(response.status == 203)
	        {
				BootstrapDialog.show
				({
					title : 'Country Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : response.data,
					closable : false,
					buttons : 
					[{
						label : 'OK',
						action : function(dialogRef) 
						{
							dialogRef.close();
							location.reload();
						}
    	        	}]
    	        }); 	
	        }
			else
			{
				BootstrapDialog.show
				({
					title : 'Country Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'Currently We are facing technical issues, please try again later.',
					closable : false,
					buttons : 
					[{
						label : 'OK',
						action : function(dialogRef) 	
						{
							dialogRef.close();
							location.reload();
    	        		}
    	        	}]
    	        });
	        }
		}
			
		console.log("marker");
		console.log(markers);
    	
		WebServiceFactory.addCountryDetails(markers).then(addCountryDetails);
	}
	
	$scope.update=false;
	$scope.updateData = function(updateCountryForm){
		$scope.update= true;
		if(updateCountryForm.$valid){
			$scope.onSubmit= false;	
			$scope.updateCountryDetail();
		}
	};	
	
	$scope.upload=false;
	$scope.uploadData = function(uploadCountryForm){
		$scope.upload= true;
		if(uploadCountryForm.$valid){
			$scope.onSubmit= false;	
			$scope.uploadFileToUrl();
		}
	};	
	
	$scope.updateCountryDetail = function(){
		var markers = [];
		for(var i=0;i<$scope.countryDetails.length;i++){
			$scope.updateIsOnlyManRCDeal;
			if($scope.countryDetails[i].isOnlyManRCDeal)
				$scope.updateIsOnlyManRCDeal = 1
			else
				$scope.updateIsOnlyManRCDeal = 0;
			
			$scope.updateIsActive;
			if($scope.countryDetails[i].isActive)
				$scope.updateIsActive = 1
			else
				$scope.updateIsActive = 0;
			
			var syntelFacilityCost = $scope.countryDetails[i].syntelFacilityCost.replace(",","");
			
			markers.push({ 	
				"countryId": $scope.countryDetails[i].countryId,
				"countryName":$scope.countryDetails[i].countryName,
				"currencyCode":  $filter('uppercase')($scope.countryDetails[i].currencyCode),
				"currencyId":$scope.updateCountryForm.rpCurrencyIdModel,
				"exchangeRate":$scope.countryDetails[i].exchangeRate,
				"isOnlyManRCDeal":$scope.updateIsOnlyManRCDeal,
				"syntelFacilityCost":syntelFacilityCost,
				"isActive": $scope.updateIsActive
				/*"countryId": $scope.countryData[i].countryId,
				"countryShortName": $filter('uppercase')($scope.updateCountryForm.countryCodeModelName),
				"currencyCode":  $filter('uppercase')($scope.updateCountryForm.txtCurrencyNameModel),
				"currencyId":$scope.updateCountryForm.rpCurrencyIdModel,
				"exchangeRate":$scope.updateCountryForm.exchangeRateUpdateModel,
				"isOffshoreSite": $scope.isOffShoreSite*/
			});
		}
		console.log("chk data");
		console.log($scope.countryData);
		var updateCountryDetail = function(response) {
			console.log(response);
			if(response.status == 200) {
				BootstrapDialog.show({
					title : 'Country Details',
					type : BootstrapDialog.TYPE_PRIMARY,
					message : 'Data Updated SucessFully',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							location.reload();
						}
					}]
				});
			}
			else {
				BootstrapDialog.show({
				title : 'Country Details',
				type : BootstrapDialog.TYPE_DANGER,
				message : "Currently We are facing technical issues, please try again later.",
				closable : false,
				buttons : [ {
					label : 'OK',
					action : function(dialogRef) {
						dialogRef.close();
						location.reload();
						}
					} ]
				});
			}
			
				
   	   }
		WebServiceFactory.updateCountryDetail(markers).then(updateCountryDetail);
	};
	
	$scope.getMasterCommonCostExcel = function() {
		window.location= contextPath+"/RightPrice-DAS/getCountryForexExcel"
	};
	
	$scope.clear = function(){
		$scope.update=false;
		$scope.updateCountryForm.ddlCountryModel=null;
		$scope.updateCountryForm.ddlCurrencyNameModel=null;
		$scope.updateCountryForm.exchangeRateUpdateModel=null;
		$scope.updateCountryForm.facilityCostUpdateModel=null;
		$scope.updateCountryForm.countryCodeModelName=null;
		$scope.updateCountryForm.txtCurrencyNameModel=null;
		$scope.updateCountryForm.rpCurrencyIdModel=null;
		$scope.updateCountryForm.isOffshoreSiteModel=false;
	};
	
	$scope.putCountryDetail = function(){
		$scope.updateCountryForm.countryCodeModelName = $scope.updateCountryForm.ddlCountryModel.countryShortName;
		$scope.updateCountryForm.txtCurrencyNameModel = $scope.updateCountryForm.ddlCountryModel.currencyCode;
		$scope.updateCountryForm.ddlCurrencyNameModel = $scope.updateCountryForm.ddlCountryModel.currencyId;
		$scope.updateCountryForm.exchangeRateUpdateModel = $scope.updateCountryForm.ddlCountryModel.exchangeRate;
		$scope.updateCountryForm.facilityCostUpdateModel = $scope.updateCountryForm.ddlCountryModel.syntelFacilityCost;
		$scope.updateCountryForm.rpCurrencyIdModel = $scope.updateCountryForm.ddlCountryModel.currencyId;
		if($scope.updateCountryForm.ddlCountryModel.isOffshoreSite == 1){
			$scope.updateCountryForm.isOffshoreSiteModel = true;
		}
		else{
			$scope.updateCountryForm.isOffshoreSiteModel = false;
		}
	};
	
	// upload function
	
	  $scope.uploadFileToUrl = function(){
	    	//alert("uploadFileToUrl");
	    	//console.log("called uploadPOExcel");
	    	var fileTest = $("#fuUploadFilename");	
	    	//console.log(fileTest.length);
	    	var name = "Template";
	    	var tempID = 1;	
	    	
		    		console.log("fileTest");
					var file = $('input[name="fuUploadFilename"]').get(0).files[0];
					console.dir(file);
					var formData = new FormData();
					formData.append('file', file);
					formData.append('name', name);
					formData.append('TempId',tempID);	
			    	console.log(file); 
			    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadCountry";
		            $http.post(uploadUrl, formData, {
		                transformRequest: angular.identity,
		                headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
		            }).
				    then(function(data) {
				    	console.log("data.data[0]");
				    	console.log(data);
				    	var obj = JSON.stringify(data);
						var json = JSON.parse(obj);
						var customMessage = data.data;
				    	if(data.status == 200)	
						{
							BootstrapDialog.show({
								title : 'Country Forex Entry',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : customMessage,
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										location.reload();
									}
								} ]
							});	
						} else{
								BootstrapDialog.show({
								title : 'Country Forex Entry',
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
	        
	    	/*var getVisaDataLabel = function(response) {
				console.log("visa");
				console.log(response.data);
				$scope.visa = response.data;
			};
			WebServiceFactory.getVisaDataLabel().then(getVisaDataLabel);*/
	        $scope.visa;
	    	var getVisaTypeDetails = function(response) {
				console.log("visa");
				console.log(response.data);
				$scope.visa = response.data;
				
				
				angular.forEach($scope.visa , function(value,key) {
					$scope.visa[key].visaLabels = "";
					$scope.visa[key].isRequired = false;
					$scope.visa[key].isDisabled=true;
					
				});
			};
			WebServiceFactory.getVisaTypeDetails().then(getVisaTypeDetails);
			
			 $scope.LabelRqd=function(visaArray,addvisaType,addLabelType,visaName,index){
	                $scope.visaArray=visaArray;
			       // console.log($scope.visaArray);
					var chkBox=addvisaType;
					if (chkBox !=false){
						if(visaArray.visaLabels == "") {
							$scope.visaArray.isRequired=true;
							$scope.visaArray.isDisabled=false;
							
						}
					}
					else{
						
						$scope.visaArray.isRequired=false;
						$scope.visaArray.isDisabled=true;
					}
				}
			 
			 $scope.cancel = function(){
					$scope.onSubmit= false;	
					$scope.addCountryForm.countryModelName=null;
					$scope.addCountryForm.currencyNameModel=null;
					$scope.addCountryForm.rpCurrencyIdModel=null;
					$scope.addCountryForm.exchangeRateModel=null
					$scope.addCountryForm.facilityCostModel=null;
					$scope.addCountryForm.countryCodeModelName=null;
					$scope.addCountryForm.isOffshoreSiteModel=false;
					$scope.addCountryForm.addOnlyManRCDeal = false;
					$scope.addCountryForm.addVisaType=null;
					angular.forEach($scope.visa , function(value,key) 
							{
                             	$scope.visa[key].isRequired=false;
								$scope.visa[key].isDisabled=true;
							});
					
			 };
				
	        
			 
	        
}]);