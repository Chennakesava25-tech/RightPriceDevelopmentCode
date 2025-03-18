//var app = angular.module('MasterCityApp', ['ngMessages']);
		app.controller("MasterCityController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index){
			//$scope.updateCityForm={};
			//var contextPath = $window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));
			 var contextPath = "/RightPrice-DAS";
			$scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            };
             $scope.ShowHideAdd = function () {
            	 $scope.AddHidden = $scope.AddHidden ? false : true;
            	 $scope.onSave= false;
            	 $scope.addCityForm.AddCountryModel=null;
            	 $scope.addCityForm.AddCityModel=null;
            	 $scope.addCityForm.AddCityCategorizationModel=null
         		 $scope.addCityForm.AddColaValueModel=null;
            };
             $scope.ShowHideUpdate = function () {
                $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
                $scope.update=false;
                $scope.updateCityForm.UpdateCountryModel=null;
        		$scope.updateCityForm.UpdateCityModel=null;
        		$scope.updateCityForm.UpdateCityCategorizationModel=null;
        		$scope.updateCityForm.UpdateColaValueModel=null;
        		$scope.updateCityForm.IsActive=null;
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
	
	var getCitycategorization = function(response) {
		console.log("citycategorization");
		console.log(response.data);
		$scope.citycategorization = response.data;
	};
	WebServiceFactory.getCitycategorization().then(getCitycategorization);		
			
			
	var getCountry = function(response) {
		console.log("country")
		$scope.country = response.data;
	};
	WebServiceFactory.getCountry().then(getCountry);
	
	$scope.getCity = function(countryId){
		if($scope.country !=undefined) {
			var getCity = function(response) {
				console.log("city");
				console.log(response.data);
				$scope.city = response.data;
				angular.forEach($scope.city,function(value,key) {
				$scope.city[key].cityName = $scope.city[key].cityName.substr(0,1).toUpperCase()+$scope.city[key].cityName.substring(1).toLowerCase();
				});
				
			};
		}
		if($scope.country !=undefined) {
			WebServiceFactory.getCity(countryId).then(getCity);
		}
	};	
		
	$scope.onSearch= false;	
	$scope.searchData = function(searchCityForm){
		$scope.onSearch= true;
		//$scope.downloadBtn = false;
		if(searchCityForm.$valid){
			$scope.onSearch= false;
			$scope.downloadBtn = false;
			/*alert("validated search");*/
			$scope.getReportData();
		}
	};
	
	
	$scope.downloadBtn = true;
	$scope.disableDownload= function(){
		$scope.downloadBtn = true;
	};
	
	$scope.onChangeValue=function(){
		if($scope.updateCityForm.UpdateCityCategorizationModel!=1){
			$scope.updateCityForm.UpdateColaValueModel=0;
		}
		else{
			
			$scope.updateCityForm.UpdateColaValueModel=$scope.updateCityForm.UpdateCityModel.colaValue;	
		}
		
	}
	
	$scope.onChangeValueForAddCity=function(){
		if($scope.addCityForm.AddCityCategorizationModel!=1){
			$scope.addCityForm.AddColaValueModel=0;
		}
		else{
			
			$scope.addCityForm.AddColaValueModel="";	
		}
		
	}
	
	
	$scope.putCityDetails=function(){
		$scope.updateCityForm.UpdateCityCategorizationModel=$scope.updateCityForm.UpdateCityModel.categorizationId;	
		$scope.updateCityForm.UpdateColaValueModel=$scope.updateCityForm.UpdateCityModel.colaValue;	
		$scope.updateCityForm.IsActive = $scope.updateCityForm.UpdateCityModel.isActive;
		/*console.log("inside ---------------------- ");*/
		console.log($scope.updateCityForm.UpdateCityModel.isActive);
		
		if($scope.updateCityForm.IsActive == 1 || updateCityForm.IsActive == true){
			$scope.updateCityForm.IsActive = true;
		}
		else{
			$scope.updateCityForm.IsActive = false;
		}
	}
	
	$scope.onSave= false;	
	$scope.addNew = function(addCityForm){
		$scope.onSave= true;
		if(addCityForm.$valid){
			$scope.onSave= false;	
			/*alert("validated add");*/
			$scope.addCityDetails();
		}
	};		
	
	/*$scope.cancel = function(){
		$scope.addCityForm.AddCityModel=null;
		$scope.addCityForm.AddCountryModel=null;
		$scope.addCityForm.AddCityCategorizationModel=null;
		$scope.addCityForm.AddColaValueModel=null;
		$scope.updateCityForm.UpdateCountryModel=null;
		$scope.updateCityForm.UpdateCityModel=null;
		$scope.updateCityForm.UpdateCityCategorizationModel=null;
		$scope.updateCityForm.UpdateColaValueModel=null;
		$scope.updateCityForm.cbxUpdateIsActiveModel=null;
	};*/
	
	$scope.update=false;
	$scope.updateData = function(updateCityForm){
		$scope.update= true;
		if(updateCityForm.$valid){
			$scope.updateData= false;
			$scope.updateCityDetails();
		}
		
				    	 
	};	
	$scope.getReportData=function(){
		
		if($scope.searchCityForm.ViewCountryModel != undefined) {
			//$scope.downloadBtn = false;
			var countryId=$scope.searchCityForm.ViewCountryModel.countryId;
		}
		if($scope.searchCityForm.ViewCityModel==undefined){
			var cityId=0;
				}
		else{
				var cityId=$scope.searchCityForm.ViewCityModel.cityId;
			}
				console.log("country id"+countryId);
				console.log("city id"+cityId);
				console.log('merkers');
				var markers = {
					"countryId" : countryId,
					"cityId" : cityId
		} ;
		
		console.log(markers);
		$http({
		    method: 'POST',
		    url: contextPath+'/RightPrice-DAS/getSearchCity',
		    dataType: 'json',
		    data: angular.toJson(markers),  
         headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    }).
		    then(function(data) {
		    	
		    	$scope.viewDataReport = data.data;
	    	console.log( data.data);
		    	console.log($scope.viewDataReport.length);
		    	 if(($scope.viewDataReport.length) < 1){
					  BootstrapDialog.show({
								title : 'View Cities Data',
								type : BootstrapDialog.TYPE_DANGER,
								message : 'Data Not Avialble.',
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
									}
								} ]
							});
					  }

		    });
	};
	
	$scope.addCityDetails = function(){
		
			var marker = {
					"countryId": $scope.addCityForm.AddCountryModel.countryId,
					/*"currencyCode": $scope.currency[$scope.addCountryForm.currencyNameModel-1].currencyName,*/
					"cityName":  $scope.addCityForm.AddCityModel.substr(0,1).toUpperCase() + $scope.addCityForm.AddCityModel.substring(1).toLowerCase(),
					"categorizationId":$scope.addCityForm.AddCityCategorizationModel,
					"colaValue":$scope.addCityForm.AddColaValueModel,
					
		};
			console.log(marker);
			$http({
				 method: 'POST',
				 url: contextPath+"/RightPrice-DAS/addCityDetails",
				 dataType: 'json',
				 data: angular.toJson(marker),  
				 headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
	    	}).
	    	then(function(response) 
			{
	    		var customMessage = response.data;
	    		
	    		if(response.status == 200){	
	    	        BootstrapDialog.show({
	    	        	title : 'City Details',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : 'Data Saved SucessFully',
	    	        	closable : false,
	    	        	buttons : [{
	    	        		label : 'OK',
	    	        		action : function(dialogRef) {
	    	        			dialogRef.close();
	    	        			window.location = "MasterCity";
	    	        		}
	    	        	}]
	    	        });
	        	}
	    		else if(response.status == 203)
	    		{	
	    	        BootstrapDialog.show({
	    	        	title : 'City Details',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : customMessage,
	    	        	closable : false,
	    	        	buttons : [{
	    	        		label : 'OK',
	    	        		action : function(dialogRef) {
	    	        			dialogRef.close();
	    	        			window.location = "MasterCity";
	    	        		}
	    	        	}]
	    	        });
	        	}
	        	else 
	        	{
					BootstrapDialog.show({
					title : 'City Details',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Currently We are facing technical issues, please try again later.",
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							window.location = "MasterCity";
						}
					} ]
					});
	        	}
			},function (data) {
				$scope.displayres = data;
			    $scope.answer = 'Posting data was unsuccessful.';
			});
	
	}
	
	$scope.updateCityDetails = function(){
		
		console.log($scope.updateCityForm.UpdateCityModel);
		//$scope.activeStatus;
		if($scope.updateCityForm.IsActive == true||updateCityForm.IsActive == 1){
			$scope.updateCityForm.IsActive =  1;
		}
		else{
			$scope.updateCityForm.IsActive =  0;
		}
			var marker1 = {
					"countryId": $scope.updateCityForm.UpdateCountryModel.countryId,
					"cityId":$scope.updateCityForm.UpdateCityModel.cityId,
					"cityName": $scope.updateCityForm.UpdateCityModel.cityName,
					"categorizationId":$scope.updateCityForm.UpdateCityCategorizationModel,
					"colaValue":$scope.updateCityForm.UpdateColaValueModel,
					"isActive":$scope.updateCityForm.IsActive
			};
			console.log(marker1);
			$http({
				 method: 'POST',
				 url: contextPath+"/RightPrice-DAS/updateCityDetails",
				 dataType: 'json',
				 data: angular.toJson(marker1),  
	            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
	    	}).
	    	then(function(data) {
	    			console.log("added");
	    	        $scope.answer = 'Data has been updated successfully';
	    	        BootstrapDialog.show({
	    	        	title : 'City Details',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : 'Data Updated SucessFully',
	    	        	closable : false,
	    	        	buttons : [{
	    	        		label : 'OK',
	    	        		action : function(dialogRef) {
	    	        			dialogRef.close();
	    	        			window.location = "MasterCity";
	    	        		}
	    	        	}]
	    	        });
			},function (data) {
				$scope.displayres = data;
			    $scope.answer = 'Posting data was unsuccessful.';
			});
	};
	
		$scope.getCityExcelData = function() {
			var countryId = $scope.searchCityForm.ViewCountryModel.countryId;
			var countryName  = $.grep($scope.country, function (country) {
                return country.countryId == countryId;
            })[0].countryName;
			
			var cityName = "";
			if($scope.searchCityForm.ViewCityModel == undefined)
			{
				var cityId=0;
				cityName = "-";
			}
			else
			{
				var cityId=$scope.searchCityForm.ViewCityModel.cityId;
				cityName  = $.grep($scope.city, function (city) {
	                return city.cityId == cityId;
	            })[0].cityName;
			}
			window.location= contextPath+"/RightPrice-DAS/downloadCityExcel/"+countryId+"/"+cityId+"/"+countryName+"/"+cityName
		};
		
	$scope.cancel = function(){
		$scope.onSave= false;
		$scope.update=false;
		$scope.addCityForm.AddCountryModel=null;
		$scope.addCityForm.AddCityModel=null;
		$scope.addCityForm.AddCityCategorizationModel=null
		$scope.addCityForm.AddColaValueModel=null;
		$scope.updateCityForm.UpdateCountryModel=null;
		$scope.updateCityForm.UpdateCityModel=null;
		$scope.updateCityForm.UpdateCityCategorizationModel=null;
		$scope.updateCityForm.UpdateColaValueModel=null;
		$scope.updateCityForm.IsActive=null;
	};
	
	$scope.upload=false;
	$scope.uploadData = function(uploadCityForm){
		$scope.upload= true;
		if(uploadCityForm.$valid){
			$scope.onSubmit= false;	
			$scope.uploadFileToUrl();
		}
	};	
	// upload City function
	
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
			    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadCity";
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
								title : 'Master City Entry',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : customMessage,
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										window.location = "MasterCity";
									}
								} ]
							});	
						} else{
								BootstrapDialog.show({
								title : 'Master City Entry',
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
	
}]);