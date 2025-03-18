//var app = angular.module('MasterSalaryApp', ['ngMessages']);
		app.controller("MasterSalaryController", [
			'$scope','$location','$anchorScroll','$http','$window','WebServiceFactory',
			function($scope, $location, $anchorScroll, $http, $window,WebServiceFactory, $index){
			var contextPath = "/RightPrice-DAS";
			$scope.isDownload = true;
			$scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.onViewSearch = false;
			 $scope.onSearchAdd = false;
			 $scope.onAddSave = false;
			 $scope.onSearchUpdate = false;
			 $scope.onUpdateSave = false;
			 $scope.upload=false;
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
			

			var currentYr = new Date().getFullYear();
		    var range = [];
		    //range.push((currentYr-1)+"-"+(currentYr));
		    for (var i = 0; i < 8; i++) {
		    	range.push((currentYr + i)+"-"+(currentYr + i + 1));
		    }
		    $scope.years = range;
		    console.log("Years")
		    console.log($scope.years);
			
			var getCountryDetail = function(response) {
				$scope.country = response.data;
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
			$scope.getPractice = function(countryId){
				var getPractice = function(response) {
					$scope.practice = response.data;
				};
				WebServiceFactory.getPractice(countryId).then(getPractice);
			};
			$scope.getPracticee = function(countryId){
				var getPractice = function(response) {
					$scope.practicee = response.data;
					$scope.addSalaryArray = [];
				};
				WebServiceFactory.getPractice(countryId).then(getPractice);
			};
			
			$scope.clearSearchArray = function()
			{
				$scope.addSalaryArray = [];
			};
			
			$scope.onCancelClickAdd = function(){
				$scope.addSalaryArray = [];
				$scope.addSalaryForm.addCountryModel = null;
				$scope.addSalaryForm.ddlAddPracticeModel = null;
				$scope.addSalaryForm.ddlAddYearModel = null;
				$scope.onSearchAdd = false
				$scope.onAddSave = false;
			}
			
			$scope.onCancelClickUpdate = function(){
				$scope.updateSalaryArray = [];
				$scope.updateSalaryForm.updateCountryModel = null;
				$scope.updateSalaryForm.updatePracticeModel = null;
				$scope.updateSalaryForm.ddlUpdateYearModel = null;
				$scope.onSearchUpdate = false
				$scope.onUpdateSave = false;
			}
			
			
			$scope.searchData = function(form){
				console.log(form);
				$scope.onViewSearch = true;
				if(form == true){
					$scope.onViewSearch = false;
					$scope.viewSalary();
				}
			};
			
			$scope.viewSalary = function()
			{
				$scope.viewSalaryForm.viewYearModelSplit=($scope.viewSalaryForm.viewYearModel).split('-')[0];
				console.log("inside view salary");
				var markers = {
						"countryId": $scope.viewSalaryForm.viewCountryModel.countryId,
						"practiceId": $scope.viewSalaryForm.viewPracticeModel,
						"year":$scope.viewSalaryForm.viewYearModelSplit
				};
				console.log("markers -------");
				console.log(markers);
				var viewSalary = function(response) {
					console.log("view salary--------");
					console.log(response);
					if(response.data != ""){
						$scope.salaryViews = response.data;
						
						for(var i=0;i<$scope.salaryViews.length;i++ ){
							var gcmVal;
							if($scope.salaryViews[i].empDesg.gcmCODE != null ||  $scope.salaryViews[i].empDesg.gcmCODE != undefined){
							if($scope.salaryViews[i].empDesg.gcmCODE.toString().includes("/")){
							gcmVal = 	$scope.salaryViews[i].empDesg.gcmCODE.replace('_');
							$scope.salaryViews[i].empDesg.gcmCODE = gcmVal
							}	
							}
						}
						
						for(i=0;i<response.data.length;i++)
						{
							var num = parseFloat(response.data[i].annualSalary);						    
						    var annualSal = num.toFixed(2);
						    $scope.salaryViews[i].annualSalary = annualSal;
						}
						$scope.isDownload = false;
					}else{
						$scope.isDownload = true;
						$scope.salaryViews = [];
						$scope.taxParameters = [];
						BootstrapDialog.show({
		    	        	title : 'Master- Salary',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Salary does not exists for the selected Country,Practice and Year.',
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
				WebServiceFactory.viewSalary(markers).then(viewSalary);
			};
			
			$scope.addSalarySearch = function(form)
			{
				$scope.onSearchAdd = true;
				if(form == true)
				{
					$scope.onSearchAdd = false;
					console.log("inside add salary");
					$scope.addSalaryForm.ddlAddYearModelSplit=($scope.addSalaryForm.ddlAddYearModel).split('-')[0];
					var markers = 
					{
						"countryId": $scope.addSalaryForm.addCountryModel.countryId,
						"practiceId": $scope.addSalaryForm.ddlAddPracticeModel,
						"year":$scope.addSalaryForm.ddlAddYearModelSplit
					};
					console.log(markers);
					var addSalarySearch = function(response) 
					{
						console.log(response);
						console.log("status is :");
						console.log(response.status);
						if(response.data == "AlreadyAdded")
						{
							$scope.addSalaryArray = [];
							$scope.answer = 'Data has already been added.';
							BootstrapDialog.show
							({
								title : 'Master - Allowances',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : 'Allowances already exists for the selected Country, Visa and Year.',
								closable : false,
								buttons : 
								[{
									label : 'OK',
									action : function(dialogRef) 
									{
										dialogRef.close();
									}
								}]
							});
						}
						else
						{
							$scope.addSalaryArray = response.data;
							console.log(response.data.length);
							for(i=0;i<response.data.length;i++)
							{
								$scope.addSalaryArray[i].designation = response.data[i].desgDesc;
							}
						}
					}
					WebServiceFactory.addSalarySearch(markers).then(addSalarySearch);
				}
			};
			
			$scope.insertSalary = function(frm){
				$scope.onAddSave = true;
//				alert(frm);
				if(frm == true)
				{
					$scope.onAddSave = false;
					var markers = [];
					console.log("inside insertSalary");
					console.log($scope.addSalaryArray);
					$scope.addSalaryForm.ddlAddYearModelSplit=($scope.addSalaryForm.ddlAddYearModel).split('-')[0];
					if($scope.addSalaryArray.length == 0)
					{

					}
					else
					{
						for(var i=0;i<$scope.addSalaryArray.length;i++)
						{
							markers.push
							({ 	
								"annualSalary":$scope.addSalaryArray[i].annualSalary,
								"countryId": $scope.addSalaryForm.addCountryModel.countryId,
								"practiceId": $scope.addSalaryForm.ddlAddPracticeModel,
								"year":$scope.addSalaryForm.ddlAddYearModelSplit,
								"empDesgId":$scope.addSalaryArray[i].desgId,
							});
						}
					}
					console.log(markers);
				
					var insertSalary = function(response) 
					{
		    	        $scope.answer = 'Data has been added successfully';
		    	        BootstrapDialog.show
		    	        ({
		    	        	title : 'Master Salary',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Annual Salary Added Sucessfully for the selected Country, Visa and Year.',
		    	        	closable : false,
		    	        	buttons : 
	    	        		[{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) 
		    	        		{
		    	        			dialogRef.close();
		    	        			$window.location.reload();
		    	        		}
		    	        	}]
		    	        });
					};
					WebServiceFactory.insertSalary(markers).then(insertSalary);
				}
			};
			
			$scope.updateSalarySearch = function(form){
				$scope.onSearchUpdate = true;
				if(form == true)
				{
					console.log("inside update salary");
					console.log($scope.updateSalaryForm);
					$scope.updateSalaryForm.ddlUpdateYearModelSplit=($scope.updateSalaryForm.ddlUpdateYearModel).split('-')[0];
					var markers = 
					{
						"countryId": $scope.updateSalaryForm.updateCountryModel.countryId,
						"practiceId": $scope.updateSalaryForm.updatePracticeModel,
						"year":$scope.updateSalaryForm.ddlUpdateYearModelSplit
					};
					console.log(markers);
					var updateSalarySearch = function(response) 
					{
						$scope.updateSalaryArray = [];
						console.log(response);
						console.log("status is :");
						console.log(response.status);
						if (response.status == 204) 
						{
							$scope.answer = 'No Data.';
							BootstrapDialog .show
							({
								title : 'Master - Salary',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : 'Salary does not exists for the selected Country, Practice and Year.',
								closable : false,
								buttons : 
								[{
									label : 'OK',
									action : function(dialogRef) 	
									{
										dialogRef.close();
										// $window.location.reload();
									}
								}]
							});
						}
						else 
						{
							$scope.updateSalaryArray = response.data;
							for (i = 0; i < response.data.length; i++) 
							{
								var num = parseFloat(response.data[i].annualSalary);
								var annualSal = num.toFixed(2);
								$scope.updateSalaryArray[i].annualSalary = annualSal;
							}
						}
					}
					WebServiceFactory.updateSalarySearch(markers).then(updateSalarySearch);
				}
			};
			
			$scope.updateSalary = function(frm){
				$scope.onUpdateSave = true;
				if(frm == true){
				console.log("updateSalary--"+$scope.updateSalaryArray.length);
				console.log($scope.updateSalaryForm);/*
				console.log($scope.updateSalaryForm.ddlUpdateYearModel);
				console.log($scope.updateSalaryForm.updatePracticeModel);
				console.log($scope.updateSalaryForm.updateCountryModel.countryId);*/
				var markers = [];
				for(var i=0;i<$scope.updateSalaryArray.length;i++){
					console.log($scope.updateSalaryArray[i]);
					markers.push({ 	
						"empDesgId":$scope.updateSalaryArray[i].empDesgId,
						"salaryid":$scope.updateSalaryArray[i].salaryid,
						"annualSalary":$scope.updateSalaryArray[i].annualSalary,
						"countryId":$scope.updateSalaryForm.updateCountryModel.countryId,
						"practiceId":$scope.updateSalaryForm.updatePracticeModel,
						"year":$scope.updateSalaryForm.ddlUpdateYearModelSplit,
					});
				}
				
				var updateSalary = function(response) {
	    	        $scope.answer = 'Data has been updated successfully';
	    	        BootstrapDialog.show({
	    	        	title : 'Master - Salary',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : 'Salary Updated SucessFully',
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
				WebServiceFactory.updateSalary(markers).then(updateSalary);
				
		    	console.log(markers);
				}
			};
			
			
			$scope.uploadData = function(uploadMasterSal){
				$scope.upload= true;
				if(uploadMasterSal.$valid){
					$scope.upload= false;
					//alert("inside valid");
					$scope.uploadFileToUrl();
				}
			};	
			
			// Upload File
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
					    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadMasterSal";
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
										title : 'Master Salary Entry',
										type : BootstrapDialog.TYPE_PRIMARY,
										message : customMessage,
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(
													dialogRef) {
												dialogRef.close();
												window.location = "MasterSalary";
											}
										} ]
									});	
								} 
						    	else if(data.status == 205){
										BootstrapDialog.show({
										title : 'Master Salary Entry',
										type : BootstrapDialog.TYPE_DANGER,
										message : "Currently We are facing technical issues, please try again later.",
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(dialogRef) {
												dialogRef.close();
												window.location = "MasterSalary";
											}
										} ]
									});
								}
								else {
									BootstrapDialog.show({
									title : 'Master Salary Entry',
									type : BootstrapDialog.TYPE_DANGER,
									message : customMessage,
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											window.location = "MasterSalary";
										}
									} ]
								});
							}
						    },function(data) {
						        $scope.displayres = data.data;
						        $scope.answer = 'Posting data was unsuccessful.';
						});
			 };
			 
			 $scope.getMasterSalExcel = function() {				 
					var cntryId = $scope.viewSalaryForm.viewCountryModel.countryId;
					var countryName  = $.grep($scope.country, function (country) {
		                 return country.countryId == cntryId;
		             })[0].countryName;
					var practiceId = $scope.viewSalaryForm.viewPracticeModel;
					var practiceName  = $.grep($scope.practice, function (practice) {
		                 return practice.practiceId == practiceId;
		             })[0].practiceName;
					
					var year = $scope.viewSalaryForm.viewYearModel;
					console.log("cntryId: " + cntryId);
					console.log("practiceId: " + practiceId);
					console.log("year: " + year);
					window.location= contextPath+"/RightPrice-DAS/downloadMasterSalExcel/"+cntryId+"/"+practiceId+"/"+year+"/"+countryName+"/"+practiceName
				};
				
				$scope.exportToExcel=function(tableId){ // ex: '#my-table'
			    	//alert('Clicked');
			        var exportHref=WebServiceFactory.tableToExcel(tableId,'Master_Salary');
			    }
			 
			 
		}]);