//var app = angular.module('RightPrice', ['ngMessages']);
//app.controller("MasterRightPriceRateController", [
//		'$scope','$location','$anchorScroll','$http','$window','WebServiceFactory',
//		function($scope, $location, $anchorScroll, $http, $window,WebServiceFactory, $index) {
//			var contextPath = "/RightPrice-DAS";
//			
//			
//			
//	}]);

/*var app = angular.module('RightPriceApp', ['ngMessages']);*/
app.controller("MasterRightPriceRateController", [
	'$scope','$location','$anchorScroll','$http','$window','WebServiceFactory',
	function($scope, $location, $anchorScroll, $http, $window,WebServiceFactory, $index) {
		var contextPath = "/RightPrice-DAS";
	 $scope.isDownload = true;	
	 $scope.ViewHidden = true;
	 $scope.AddHidden = true;
	 $scope.UpdateHidden = true;
	 $scope.UploadHidden = true;
	 $scope.onViewSearch = false;
	 $scope.onSaveSearch = false;
	 $scope.onAddSave = false;
	 $scope.onUpdateSearch = false;
	 $scope.onUpdateClick = false;
	 $scope.upload=false;
	 $scope.countryNameModelete;
	 
     $scope.ShowHideView = function () {
        $scope.ViewHidden = $scope.ViewHidden ? false : true ;
    };
     $scope.ShowHideAdd = function () {
        $scope.AddHidden = $scope.AddHidden ? false : true;
    };
     $scope.ShowHideUpdate = function () {
        $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
    };
     $scope.ShowHideUpload = function () {
        $scope.UploadHidden = $scope.UploadHidden ? false : true;
    };
    $scope.redirect = function(){
	  window.location = "MasterRightPriceAudit.jsp";
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
	
	$scope.clearSearchArray = function()
	{
		$scope.masterRateArray = [];
	};
	var getCountryDetail = function(response) {
		$scope.country = response.data;
		console.log("Coutry Details");
		console.log($scope.country);
	};
	WebServiceFactory.getCountryDetail().then(getCountryDetail);
	
	var getAddRoles = function(response)
	{
		console.log("get role");
		console.log(response.data);
		$scope.masterRole = response.data;
	};
	WebServiceFactory.getAddRoles().then(getAddRoles);
	
	$scope.getCurrencyCode = function(countryId) 
	{
		for(i=0; i<$scope.country.length;i++)
		{
			if(countryId == $scope.country[i].countryId)
			{
				$scope.viewRateForm.currency = $scope.country[i].currencyCode;
				$scope.countryNameModelete = $scope.country[i].countryName;
				break;
			}
		}
		
	};
	
	$scope.setMasterRole = function(masterRoleId) 
	{
		$scope.masterRateArray = [];
		for(i=0; i<$scope.masterRole.length;i++)
		{
			if(masterRoleId == $scope.masterRole[i].masterRoleId)
			{
				$scope.addRateForm.ddlMasterRole = masterRoleId;				
				break;
			}
		}
		
	};
	
	$scope.setMasterRoleCode = function(masterRoleId) 
	{
		$scope.masterRateArray = [];
		for(i=0; i<$scope.masterRole.length;i++)
		{
			if(masterRoleId == $scope.masterRole[i].masterRoleId)
			{
				$scope.addRateForm.ddlAddRoleCodeModel = masterRoleId;
				break;
			}
		}
		
	};
	
	$scope.setUpdateMasterRole = function(masterRoleId) 
	{
		for(i=0; i<$scope.masterRateRoles.length;i++)
		{
			if(masterRoleId == $scope.masterRateRoles[i].masterRoleId)
			{
				$scope.updateRateForm.ddlRateModel = masterRoleId;				
				break;
			}
		}
		
	};
	
	$scope.setUpdateMasterRoleCode = function(masterRoleId) 
	{
		for(i=0; i<$scope.masterRateRoles.length;i++)
		{
			if(masterRoleId == $scope.masterRateRoles[i].masterRoleId)
			{
				$scope.updateRateForm.ddlUpdateSearchRoleCodeModel = masterRoleId;
				break;
			}
		}
		
	};
		
	
		
	$scope.getMasterRateRoles = function(countryId)
	{
		var getMasterRateRoles = function(response)
		{
			console.log(response.data);
			/*$scope.masterRole = response.data;
			console.log("$scope.masterRole");
			console.log($scope.masterRole);*/
			$scope.masterRateRoles = response.data;
		};
		WebServiceFactory.getMasterRateRoles(countryId).then(getMasterRateRoles);
	};
	

	var currentYr = new Date().getFullYear();
    var range = [];
    range.push((currentYr-1)+"-"+(currentYr));
    for (var i = 0; i < 4; i++) {
    	range.push((currentYr + i)+"-"+(currentYr + i + 1));
    }
    $scope.years = range;
    
    $scope.onViewMasterRate = function(isValid){
    	
    	$scope.onViewSearch = true;
		if(isValid == true){
		var markers = {
				"countryId": $scope.viewRateForm.countryModel,
				"transactionYear":($scope.viewRateForm.ddlSearchYearModel).split('-')[0]
		};
		console.log(markers);
		var onViewMasterRate = function(response) {
			console.log("response.data-------------+onViewMasterRate");
			console.log(response.data);
			if(response.data != ""){
			$scope.rateViews = response.data;
			
			for(i=0;i<response.data.length;i++)
			{
				var num = parseFloat(response.data[i].rateLow);						    
			    var low = num.toFixed(2);
			    $scope.rateViews[i].rateLow = low;
			    
			    num = parseFloat(response.data[i].rateMedium);						    
			    var med = num.toFixed(2);
			    $scope.rateViews[i].rateMedium = med;
			    
			    num = parseFloat(response.data[i].rateHigh);						    
			    var high = num.toFixed(2);
			    $scope.rateViews[i].rateHigh = high;
			    
			    num = parseFloat(response.data[i].rateVHigh);						    
			    var vHigh = num.toFixed(2);
			    $scope.rateViews[i].rateVHigh = vHigh;
			    
			    num = parseFloat(response.data[i].rateOffshore);						    
			    var rateOffshore = num.toFixed(2);
			    $scope.rateViews[i].rateOffshore = rateOffshore;
			}
			$scope.isDownload = false;
			}else{
				$scope.rateViews = [];
				$scope.isDownload = true;
				BootstrapDialog.show({
    	        	title : 'Master - Right Price Rate',
    	        	type : BootstrapDialog.TYPE_PRIMARY,
    	        	message : ' Rates does not exists for the selected Country and Year.',
    	        	closable : false,
    	        	buttons : [{
    	        		label : 'OK',
    	        		action : function(dialogRef) {
    	        			dialogRef.close();
    	        			$window.location.reload();
    	        		}
    	        	}]
    	        });
			}
		}
	    WebServiceFactory.onViewMasterRate(markers).then(onViewMasterRate);
		}
	};
	
	$scope.onMasterRateSearch = function(isValid){
		$scope.onSaveSearch = true;
		if(isValid ==  true){
		var cntryId = $scope.addRateForm.ddlAddCountryModel;
		var roleid = $scope.addRateForm.ddlMasterRole;
		var year = ($scope.addRateForm.ddlSearchYearModel).split('-')[0];
		
			var markers = {
					"countryId": cntryId,
					"masterRoleId": roleid,
					"transactionYear":year
			};
			var onMasterRateSearch = function(response) {
				if(response.data == "AlreadyAdded"){
					$scope.answer = 'Data has already been added.';
	    	        BootstrapDialog.show({
	    	        	title : 'Master - Right Price Rate',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : 'Rate for the selected Country, Master Role and Year already exists.',
	    	        	closable : false,
	    	        	buttons : [{
	    	        		label : 'OK',
	    	        		action : function(dialogRef) {
	    	        			dialogRef.close();
//	    	        			$window.location.reload();
	    	        		}
	    	        	}]
	    	        });
				}else{
					$scope.masterRateArray=[];
					$scope.masterRateArray.push({});
				}
			}
		    WebServiceFactory.onMasterRateSearch(markers).then(onMasterRateSearch);
		}
    };
    
    $scope.onAddMasterRate = function(frm){
		$scope.onAddSave = true;
		if(frm == true)
		{
			var markers = [];
			console.log("on save click");
			console.log($scope.masterRateArray);
			console.log($scope.masterRateArray.length);
			var cntryId = $scope.addRateForm.ddlAddCountryModel;
			var roleid = $scope.addRateForm.ddlMasterRole;
			var year = ($scope.addRateForm.ddlSearchYearModel).split('-')[0];
		
			var markers = {
					"countryId": cntryId,
					"masterRoleId": roleid,
					"transactionYear":year,
					"rateLow":$scope.masterRateArray[0].lowRate,
					"rateMedium":$scope.masterRateArray[0].mediumRate,
					"rateHigh":$scope.masterRateArray[0].highRate,
					"rateVHigh":$scope.masterRateArray[0].vhighRate,
					"rateOffshore":$scope.masterRateArray[0].offShoreRate
			};
		
			console.log(markers);
		
			var onAddMasterRate = function(response) 
			{
    	        $scope.answer = 'Data has been added successfully';
    	        BootstrapDialog.show({
    	        	title : 'Master - Right Price Rate',
    	        	type : BootstrapDialog.TYPE_PRIMARY,
    	        	message : 'Rate for the selected Country, Role and Transaction Year added sucessFully.',
    	        	closable : false,
    	        	buttons : [{
    	        		label : 'OK',
    	        		action : function(dialogRef) {
    	        			dialogRef.close();
    	        			$window.location.reload();
    	        		}
    	        	}]
    	        });
			};
			WebServiceFactory.onAddMasterRate(markers).then(onAddMasterRate);
		}
	};
    
    $scope.onUpdateMasterRateSearch = function(isValid){
    	$scope.onUpdateSearch = true;
    	if(isValid ==  true){
		var cntryId = $scope.updateRateForm.ddlUpdateCountryModel;
		var roleid = $scope.updateRateForm.ddlRateModel;
		var year = ($scope.updateRateForm.ddlSearchYearModel).split('-')[0];
			var markers = {
				"countryId": cntryId,
				"masterRoleId": roleid,
				"transactionYear":year
			};

			var onUpdateMasterRateSearch = function(response) {
				console.log("response.data is ------------------------------");
				console.log(response.data);
				$scope.updateMasterRate = response.data;
				if(response.data != ""){
					$scope.isSelected = true;
					var num = parseFloat(response.data[0].rateLow);						    
				    var low = num.toFixed(2);
					$scope.updateRateForm.updateLowRate = low;
					
					num = parseFloat(response.data[0].rateMedium);						    
				    var rateMedium = num.toFixed(2);
					$scope.updateRateForm.updateMediumRate = rateMedium;
					
					num = parseFloat(response.data[0].rateHigh);						    
				    var rateHigh = num.toFixed(2);
					$scope.updateRateForm.updateHighRate = rateHigh;
					
					num = parseFloat(response.data[0].rateVHigh);						    
				    var rateVHigh = num.toFixed(2);
					$scope.updateRateForm.updateVHighRate = rateVHigh;
					
					num = parseFloat(response.data[0].rateOffshore);						    
				    var rateOffshore = num.toFixed(2);
					$scope.updateRateForm.updateOffShore = rateOffshore;
				}else{
					 $scope.answer = 'Data Doesnot exists';
		    	        BootstrapDialog.show({
		    	        	title : 'Master - Right Price Rate',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Master Rate for the selected Country,Role and Year does not exists.',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
//		    	        			$window.location.reload();
		    	        		}
		    	        	}]
		    	        });
				}
			}
		    WebServiceFactory.onUpdateMasterRateSearch(markers).then(onUpdateMasterRateSearch);
    	}
	};
	
	$scope.onUpdateMasterRate = function(isValid){
		$scope.onUpdateClick = true;
		if(isValid)
		{
			$scope.onUpdateClick = true;
			var markers = [];
			console.log("on update click");
			var cntryId = $scope.updateRateForm.ddlUpdateCountryModel;
			var roleid = $scope.updateRateForm.ddlRateModel;
			var year = ($scope.updateRateForm.ddlSearchYearModel).split('-')[0];
			
	//		
			var markers = {
					"countryId": cntryId,
					"masterRoleId": roleid,
					"transactionYear":year,
					"rateLow":$scope.updateRateForm.updateLowRate,
					"rateMedium":$scope.updateRateForm.updateMediumRate,
					"rateHigh":$scope.updateRateForm.updateHighRate,
					"rateVHigh":$scope.updateRateForm.updateVHighRate,
					"rateOffshore":$scope.updateRateForm.updateOffShore
			};
		
		
			console.log(markers);
		
			var onUpdateMasterRate = function(response) {
				$scope.answer = 'Data has been updated successfully';
				BootstrapDialog.show({
					title : 'Master - Right Price Rate',
					type : BootstrapDialog.TYPE_PRIMARY,
					message : 'Rate for the selected Country, Role and Transaction Year updated sucessFully.',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
							$window.location.reload();
						}
					}]
				});
			};
			WebServiceFactory.onUpdateMasterRate(markers).then(onUpdateMasterRate);
		}
	};
	
	$scope.onCancelClick = function(){
		//$window.location.reload();
		$scope.masterRateArray = [];
		$scope.addRateForm.ddlAddCountryModel = null;
		$scope.addRateForm.ddlSearchYearModel = null;
		$scope.addRateForm.ddlAddRoleCodeModel = null;
		$scope.addRateForm.ddlMasterRole = null;
		$scope.onSaveSearch = false
		$scope.onAddSave = false;
	}
	
	$scope.getMasterRPRateExcel = function() {				 
		var cntryId = $scope.viewRateForm.countryModel;
		var countryName  = $.grep($scope.country, function (country) {
                 return country.countryId == cntryId;
             })[0].countryName;
		
		var year = parseInt(($scope.viewRateForm.ddlSearchYearModel).split('-')[0]);
		console.log("cntryId: " + cntryId);		
		console.log("year: " + ($scope.viewRateForm.ddlSearchYearModel).split('-')[0] );
		window.location= contextPath+"/downloadMasterRPRateExcel/"+cntryId+"/"+year+"/"+countryName
	};
	
	$scope.uploadData = function(uploadRPRate){
		$scope.upload= true;
		if(uploadRPRate.$valid){
			$scope.upload= false;
			$scope.uploadFileToUrl();
		}
	};
	$scope.exportToExcel=function(tableId){ // ex: '#my-table'
    	//alert('Clicked');
        var exportHref=WebServiceFactory.tableToExcel(tableId,'Master_RightPrice_Rate');
    }
	
	// Upload File
	 $scope.uploadFileToUrl = function()
	 {
		 console.log("called uploadMasterRPRateExcel");
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
		 var uploadUrl = contextPath+"/uploadMasterRPRate";
		 $http.post(uploadUrl, formData, 
		 {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
		 }).
		    then(function(data) 
    		{
		    	console.log("data.data[0]");
		    	console.log(data);
		    	var obj = JSON.stringify(data);
				var json = JSON.parse(obj);
				var customMessage = data.data;
		    	if(data.status == 200)	
				{
					BootstrapDialog.show
					({
						title : 'Master Right Price Rate',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : customMessage,
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
		    	else if(data.status == 205)
		    	{
	    			BootstrapDialog.show
	    			({
						title : 'Master Right Price Rate Entry',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Currently We are facing technical issues, please try again later.",
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
						title : 'Master Right Price Rate(s) Entry',
						type : BootstrapDialog.TYPE_DANGER,
						message : customMessage,
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
    		},function(data) 
    		{
    			$scope.displayres = data.data;
				$scope.answer = 'Posting data was unsuccessful.';
			});
	 };
	
}]);
