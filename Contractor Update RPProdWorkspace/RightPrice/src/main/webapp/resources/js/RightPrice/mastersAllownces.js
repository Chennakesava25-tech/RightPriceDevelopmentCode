//var app = angular.module('RightPrice', ['ngMessages']);
app.controller("MasterAllowancesController", [
		'$scope','$location','$anchorScroll','$http','$window','WebServiceFactory',
		function($scope, $location, $anchorScroll, $http,$window,WebServiceFactory, $index) {
			var contextPath = "/RightPrice-DAS";
			$scope.SearchHidden = true;
			$scope.AddHidden = true;
			$scope.UpdateHidden = true;
			$scope.UploadHidden = true;
			$scope.onViewSearch = false;
			$scope.onAddSearch = false;
			$scope.onAddSave = false;
			$scope.onUpdateSearch = false;
			$scope.onUpdateAllownce = false;
			$scope.downloadBtn = true;
			$scope.isActiveEnabled = false;
			$scope.upload=false;
			
			
			var currentYr = new Date().getFullYear();
		    var range = [];
		    range.push((currentYr-1)+"-"+(currentYr));
		    for (var i = 0; i < 4; i++) {
		    	range.push((currentYr + i)+"-"+(currentYr + i + 1));
		    }
		    $scope.years = range;
		    
			$scope.ShowHideSearch = function() {
				$scope.SearchHidden = $scope.SearchHidden ? false : true;
			};
			$scope.ShowHideAdd = function() {
				$scope.AddHidden = $scope.AddHidden ? false : true;
			};
			$scope.ShowHideUpdate = function() {
				$scope.UpdateHidden = $scope.UpdateHidden ? false : true;
			};
			$scope.ShowHideUpload = function() {
				$scope.UploadHidden = $scope.UploadHidden ? false : true;
			};
			$scope.moveTop = function() {
				$location.hash('PageHeading');
				$anchorScroll();
			};
			$scope.moveBottom = function() {
				$location.hash('includedFooter');
				$anchorScroll();
			};
			var getCountryDetail = function(response) {
				$scope.country = response.data;
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
			$scope.clearSearchArray = function()
			{
				$scope.allowancesAddArray = [];
			};
			
			var getVisa = function(response) {
				//$scope.allowancesAddArray = [];
				$scope.allowancesViews = [];
				$scope.downloadBtn = true;
				$scope.visa = response.data;
				console.log($scope.visa);
			};
			WebServiceFactory.getVisa().then(getVisa);
			
			$scope.getVisaa = function(countryId){
				var getVisa = function(response) {
					console.log(response.data);
					$scope.visaa = response.data;
				};
				WebServiceFactory.getVisa(countryId).then(getVisa);
			};
			$scope.getVisaForUpdate = function(countryId){
				var getVisa = function(response) {
					console.log(response.data);
					$scope.visaForUpdate = response.data;
				};
				WebServiceFactory.getVisa(countryId).then(getVisa);
			};
			
			$scope.viewAllowances = function(viewAllowancesForm){
				$scope.onViewSearch = true;
				$scope.viewAllowancesForm.ddlSearchYearModelSplit=($scope.viewAllowancesForm.ddlSearchYearModel).split('-')[0];
				if(viewAllowancesForm == true){
				var markers = {
						"countryId": $scope.viewAllowancesForm.countryModel,
						"visaid": $scope.viewAllowancesForm.ddlSearchVisaModel,
						"year":$scope.viewAllowancesForm.ddlSearchYearModelSplit
				};
				var viewAllowances = function(response) {
					console.log("response.data");
					console.log(response.data);
					if(response.data != ""){
					$scope.downloadBtn = false;
					$scope.allowancesViews = response.data;
					
					
					for(var i=0;i<$scope.allowancesViews.length;i++ ){
						var gcmVal;
						if($scope.allowancesViews[i].empDesgmap.gcmCODE != null ||$scope.allowancesViews[i].empDesgmap.gcmCODE != undefined) {
						if($scope.allowancesViews[i].empDesgmap.gcmCODE.toString().includes("/")){
						gcmVal = 	$scope.allowancesViews[i].empDesgmap.gcmCODE.replace('_');
						$scope.allowancesViews[i].empDesgmap.gcmCODE = gcmVal
						}	
						}
					}
					
					for(i=0;i<response.data.length;i++)
					{
						var num = parseFloat(response.data[i].lowallowance);						    
					    var lowAll = num.toFixed(2);
					    $scope.allowancesViews[i].lowallowance = lowAll;
					    
					    num = parseFloat(response.data[i].mediumallowance);						    
					    var medAll = num.toFixed(2);
					    $scope.allowancesViews[i].mediumallowance = medAll;
					    
					    num = parseFloat(response.data[i].highallowance);						    
					    var highAll = num.toFixed(2);
					    $scope.allowancesViews[i].highallowance = highAll;
					    
					    num = parseFloat(response.data[i].veryhighallowance);						    
					    var vHighAll = num.toFixed(2);
					    $scope.allowancesViews[i].veryhighallowance = vHighAll;
					}
					
					}else{
						$scope.allowancesViews = [];
						$scope.downloadBtn = true;
						BootstrapDialog.show({
		    	        	title : 'Master - Allowances',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : ' Allowances does not exists for the selected Country, Visa and Year.',
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
			    WebServiceFactory.viewAllowances(markers).then(viewAllowances);
				}
			};
			$scope.getUpdateAllownces = function(updateAllowancesForm){
				$scope.onUpdateSearch = true;
				if(updateAllowancesForm == true){
				console.log("inside getUpdateAllownces ");
				console.log($scope.updateAllowancesForm.countryModel);
				console.log($scope.updateAllowancesForm.ddlUpdateVisaModel);
				console.log($scope.updateAllowancesForm.ddlUpdateYear);
				$scope.updateAllowancesForm.ddlUpdateYearSplit=($scope.updateAllowancesForm.ddlUpdateYear).split('-')[0];
				var markers = {
						"countryId": $scope.updateAllowancesForm.countryModel,
						"visaid": $scope.updateAllowancesForm.ddlUpdateVisaModel,
						"year":$scope.updateAllowancesForm.ddlUpdateYearSplit
				};
				var viewAllowances = function(response) {
					if(response.data != ""){
						$scope.allowancesUpdateArray = response.data;
						for(i=0;i<response.data.length;i++){
							$scope.allowancesUpdateArray[i].designation = response.data[i].empDesgmap.desgDesc;
							var band = response.data[i].empDesgmap.bandmap.description;
							var grade = response.data[i].empDesgmap.grademap.description;
							var desc = band + '-' +grade;
							$scope.allowancesUpdateArray[i].description = desc;

							var num = parseFloat(response.data[i].lowallowance);						    
						    var lowAll = num.toFixed(2);
						    $scope.allowancesUpdateArray[i].addlowallowance = lowAll;
						    						    
						    num = parseFloat(response.data[i].mediumallowance);						    
						    var medAll = num.toFixed(2);
						    $scope.allowancesUpdateArray[i].addmediumallowance = medAll;
						    						    
						    num = parseFloat(response.data[i].highallowance);						    
						    var highAll = num.toFixed(2);
						    $scope.allowancesUpdateArray[i].addhighallowance = highAll;

						    num = parseFloat(response.data[i].veryhighallowance);						    
						    var vHighAll = num.toFixed(2);
						    $scope.allowancesUpdateArray[i].addveryhighallowance = vHighAll;
						    
							$scope.updateAllowancesForm.UpdateIsActive = true;
						}
						$scope.isActiveEnabled = true;
					}else{
						$scope.allowancesUpdateArray = [];
						$scope.isActiveEnabled = false;
						BootstrapDialog.show({
		    	        	title : 'Master - Allowances',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Allowances does not exists for the selected Country, Visa and Year.',
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
				WebServiceFactory.viewAllowances(markers).then(viewAllowances);
				}
			};
			
			$scope.checkAnnualAllownces = function(checkAnnualAllownces)
			{
				$scope.onAddSearch = true;
				console.log($scope.addAllowancesForm);
				if(checkAnnualAllownces)
				{
					$scope.addAllowancesForm.ddlAddYearModelSplit=($scope.addAllowancesForm.ddlAddYearModel).split('-')[0];
					var cntryId = $scope.addAllowancesForm.countryModel;
					var visaId = $scope.addAllowancesForm.ddlSearchVisaModel;
					var year = $scope.addAllowancesForm.ddlAddYearModelSplit
					if(cntryId != undefined && visaId != undefined)
					{
						var markers = 
						{
							"countryId": cntryId,
							"visaid": visaId,
							"year":year
						};
						var checkAnnualAllownces = function(response) 
						{
							console.log(response);
							console.log("status is :");
							console.log(response.status);
							if(response.data == "AlreadyAdded")
							{
								$scope.allowancesAddArray = [];
								$scope.answer = 'Data has already been added.';
				    	        BootstrapDialog.show({
				    	        	title : 'Master - Allowances',
				    	        	type : BootstrapDialog.TYPE_PRIMARY,
				    	        	message : 'Allowances already exists for the selected Country, Visa and Year.',
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
				    	        		}
				    	        	}]
				    	        });
							}
							else
							{
								$scope.allowancesAddArray = response.data;
								console.log(response.data.length);
								for(i=0;i<response.data.length;i++)
								{
									$scope.allowancesAddArray[i].designation = response.data[i].desgDesc;
									var band = response.data[i].bandmap.description;
									var grade = response.data[i].grademap.description;
									var desc = band + '-' +grade;
									$scope.allowancesAddArray[i].description = desc;
									
								}
								
							}
						}
						WebServiceFactory.checkAnnualAllownces(markers).then(checkAnnualAllownces);
					}
				}
			};
			
			$scope.onSaveClick = function(onSaveClick){
//				alert(onSaveClick);
				$scope.onAddSave = true;
				if(onSaveClick == true){
				if($scope.addAllowancesForm.ddlSearchVisaModel != 3) {
					$scope.saveAnnualAllowances();	
				} else {
					$scope.saveMasterAnnualAllowances();
				}
				}
			};
			
			$scope.saveAnnualAllowances = function(){
				var markers = [];
				console.log($scope.allowancesAddArray);
				if($scope.allowancesAddArray.length == 0){

				}else{
					console.log("inside saveAnnualAllowances");
					console.log($scope.addAllowancesForm);
				for(var i=0;i<$scope.allowancesAddArray.length;i++){
				$scope.addAllowancesForm.ddlAddYearModelSplit=($scope.addAllowancesForm.ddlAddYearModel).split('-')[0];
					markers.push({ 	
						"desgId":$scope.allowancesAddArray[i].desgId,
						"countryId":$scope.addAllowancesForm.countryModel,
						"visaid":$scope.addAllowancesForm.ddlSearchVisaModel,
						"year":$scope.addAllowancesForm.ddlAddYearModelSplit,
						"lowallowance" : $scope.allowancesAddArray[i].addlowallowance,
						"mediumallowance" :	$scope.allowancesAddArray[i].addmediumallowance,
						"highallowance" : $scope.allowancesAddArray[i].addhighallowance,
						"veryhighallowance" : $scope.allowancesAddArray[i].addveryhighallowance
					});
				}
				}
		    	console.log(markers);
				
					var saveAnnualAllowances = function(response) {
		    	        $scope.answer = 'Data has been added successfully';
		    	        BootstrapDialog.show({
		    	        	title : 'Annual Allowances',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Annual Allowances Added Sucessfully for the selected Country, Visa and Year.',
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
					WebServiceFactory.saveAnnualAllowances(markers).then(saveAnnualAllowances);
			};
			
			
			$scope.saveMasterAnnualAllowances = function() {
				var markers = [];
				console.log($scope.allowancesAddArray);
				if($scope.allowancesAddArray.length != 0){
					console.log("inside saveAnnualAllowances");
					console.log($scope.addAllowancesForm);
				for(var i=0;i<$scope.allowancesAddArray.length;i++){
					$scope.addAllowancesForm.ddlAddYearModelSplit=($scope.addAllowancesForm.ddlAddYearModel).split('-')[0];
					markers.push({ 	
						"desgId":$scope.allowancesAddArray[i].desgId,
						"countryId":$scope.addAllowancesForm.countryModel,
						"visaid":$scope.addAllowancesForm.ddlSearchVisaModel,
						"year":$scope.addAllowancesForm.ddlAddYearModelSplit,
						"lowallowance" : $scope.allowancesAddArray[i].addlowallowance,
						"mediumallowance" :	$scope.allowancesAddArray[i].addmediumallowance,
						"highallowance" : $scope.allowancesAddArray[i].addhighallowance,
						"veryhighallowance" : $scope.allowancesAddArray[i].addveryhighallowance
					});
				}
				}
		    	console.log(markers);
				
					var saveMasterAnnualAllowances = function(response) {
		    	        $scope.answer = 'Data has been added successfully';
		    	        BootstrapDialog.show({
		    	        	title : 'Annual Allowances',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Annual Allowances Added Sucessfully for the selected Country, Visa and Year.',
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
					WebServiceFactory.saveMasterAnnualAllowances(markers).then(saveMasterAnnualAllowances);
			};
			
			$scope.onUpdateAnnualAllownceClick = function(updateAllowancesForm){
				$scope.onUpdateAllownce = true;
				if(updateAllowancesForm==true && $scope.updateAllowancesForm.ddlUpdateVisaModel != 3){
				console.log("onUpdateAnnualAllownceClick");
				var markers = [];
				var status = $scope.updateAllowancesForm.UpdateIsActive;
				if(status == false){
					status=0;
				}else{
					status=1;
				}
//				console.log($scope.allowancesUpdateArray);
				for(var i=0;i<$scope.allowancesUpdateArray.length;i++){
					$scope.updateAllowancesForm.ddlUpdateYearSplit=($scope.updateAllowancesForm.ddlUpdateYear).split('-')[0];
					markers.push({ 	
						"desgId":$scope.allowancesUpdateArray[i].desgId,
						"countryId":$scope.updateAllowancesForm.countryModel,
						"visaid":$scope.updateAllowancesForm.ddlUpdateVisaModel,
						"year":$scope.updateAllowancesForm.ddlUpdateYearSplit,
						"lowallowance" : $scope.allowancesUpdateArray[i].addlowallowance,
						"mediumallowance" :	$scope.allowancesUpdateArray[i].addmediumallowance,
						"highallowance" : $scope.allowancesUpdateArray[i].addhighallowance,
						"veryhighallowance" : $scope.allowancesUpdateArray[i].addveryhighallowance,
						"statusModel":status
					});
				}
				
				var updateAnnualAllowances = function(response) {
	    	        $scope.answer = 'Data has been updated successfully';
	    	        BootstrapDialog.show({
	    	        	title : 'Annual Allowances',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : 'Annual Allowances Updated SucessFully',
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
				WebServiceFactory.updateAnnualAllowances(markers).then(updateAnnualAllowances);
				
		    	console.log(markers);
				} else {
					console.log("onUpdateAnnualAllownceClick");
					var markers = [];
					var status = $scope.updateAllowancesForm.UpdateIsActive;
					if(status == false){
						status=0;
					}else{
						status=1;
					}
//					console.log($scope.allowancesUpdateArray);
					for(var i=0;i<$scope.allowancesUpdateArray.length;i++){
						$scope.updateAllowancesForm.ddlUpdateYearSplit=($scope.updateAllowancesForm.ddlUpdateYear).split('-')[0];
						markers.push({ 	
							"desgId":$scope.allowancesUpdateArray[i].desgId,
							"countryId":$scope.updateAllowancesForm.countryModel,
							"visaid":$scope.updateAllowancesForm.ddlUpdateVisaModel,
							"year":$scope.updateAllowancesForm.ddlUpdateYear,
							"lowallowance" : $scope.allowancesUpdateArray[i].addlowallowance,
							"mediumallowance" :	$scope.allowancesUpdateArray[i].addmediumallowance,
							"highallowance" : $scope.allowancesUpdateArray[i].addhighallowance,
							"veryhighallowance" : $scope.allowancesUpdateArray[i].addveryhighallowance,
							"statusModel":status
						});
					}
					
					var updateAnnualAllowances = function(response) {
		    	        $scope.answer = 'Data has been updated successfully';
		    	        BootstrapDialog.show({
		    	        	title : 'Annual Allowances',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Annual Allowances Updated SucessFully',
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
					WebServiceFactory.updateAllowanceShortTerm(markers).then(updateAnnualAllowances);
					
			    	console.log(markers);
				}
			};
			
/*			$scope.getMasterAllowancesExcel = function() {
				var cntryId = $scope.viewAllowancesForm.countryModel;
				var countryName  = $.grep($scope.country, function (country) {
	                 return country.countryId == cntryId;
	             })[0].countryName;
				
				var visaId = $scope.viewAllowancesForm.ddlSearchVisaModel;
				var visaName  = $.grep($scope.visa, function (visa) {
	                 return visa.id == visaId;
	             })[0].description;
				
				var year = $scope.viewAllowancesForm.ddlSearchYearModel
				
				window.location= contextPath+"/RightPrice-DAS/downloadMasterAllowanceExcel/"+cntryId+"/"+visaId+"/"+year+"/"+countryName+"/"+visaName
			};
			*/
			 $scope.exportToExcel=function(tableId){ // ex: '#my-table'
			    	//alert('Clicked');
			        var exportHref=WebServiceFactory.tableToExcel(tableId,'Master_allowence');
			    }
			 
			 
			$scope.onCancelClickAdd = function(){
				$scope.allowancesAddArray = [];
				$scope.addAllowancesForm.countryModel = null;
				$scope.addAllowancesForm.ddlSearchVisaModel = null;
				$scope.addAllowancesForm.ddlAddYearModel = null;
				$scope.onAddSearch = false;
				$scope.onAddSave = false;
			}
			
			$scope.onCancelClickUpdate = function(){
				$scope.allowancesUpdateArray = [];
				$scope.updateAllowancesForm.countryModel = null;
				$scope.updateAllowancesForm.ddlUpdateVisaModel = null;
				$scope.updateAllowancesForm.ddlUpdateYear = null;
				$scope.onUpdateSearch = false;
				$scope.updateAllowancesForm.UpdateIsActive = false;
				$scope.isActiveEnabled = false;
			}
			
			$scope.uploadData = function(uploadAllowance){
				$scope.upload= true;
				if(uploadAllowance.$valid){
					$scope.upload= false;
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
					    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadMasterAllowance";
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
										title : 'Master Allowance(s) Entry',
										type : BootstrapDialog.TYPE_PRIMARY,
										message : customMessage,
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(
													dialogRef) {
												dialogRef.close();
												window.location = "MasterAllowances";
											}
										} ]
									});	
								} 
						    	else if(data.status == 205){
										BootstrapDialog.show({
										title : 'Master Allowance(s) Entry',
										type : BootstrapDialog.TYPE_DANGER,
										message : "Currently We are facing technical issues, please try again later.",
										closable : false,
										buttons : [ {
											label : 'OK',
											action : function(dialogRef) {
												dialogRef.close();
												window.location = "MasterAllowances";
											}
										} ]
									});
								}
								else {
									BootstrapDialog.show({
									title : 'Master Allowance(s) Entry',
									type : BootstrapDialog.TYPE_DANGER,
									message : customMessage,
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											window.location = "MasterAllowances";
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
