//var app = angular.module('RightPrice', ['ngMessages']);
		app.controller("MasterAssumptionsParametersController", [
			'$scope','$location','$anchorScroll','$http','$window','WebServiceFactory',
			function($scope, $location, $anchorScroll, $http, $window,WebServiceFactory, $index) {
			 $scope.ViewCommonCostParametersHidden = true;
			 $scope.AddCommonCostParameterHidden = true;
			 $scope.UpdateCommonCostParameterHidden = true;
			 $scope.UploadCommonCostParametersHidden = true;
			 $scope.isSelected = false;
			 $scope.onViewSearch = false;
			 $scope.onSaveSearch = false;
			 $scope.onAddSave = false;
			 $scope.onUpdateSearch = false;
			 $scope.onUpdateClick = false;
			 $scope.isUpdate = true;
			 $scope.downloadBtn = true;
			 $scope.arrAssumptionParam =[];
			 $scope.visaTypeDetails =[];
			 $scope.visaTypeName = true;
			 $scope.VisaDetails = [];
			 $scope.DeductionCategoryDetails = [];
			 $scope.DeducData = [];
			 $scope.DataPresent = true;
			 $scope.makeUpdateAvail = true;
			 $scope.checkedItems = [];
			 
			 var contextPath = "/RightPrice-DAS";
             $scope.ShowHideViewCommonCostParameters = function () {
                $scope.ViewCommonCostParametersHidden = $scope.ViewCommonCostParametersHidden ? false : true ;
             };
             $scope.ShowHideUpdateManuallyCommonCostParameters = function () {
                 $scope.ViewUpdateManuallyCostParametersHidden = $scope.ViewUpdateManuallyCostParametersHidden ? false : true ;
              };
             $scope.ShowHideAddCommonCostParameter = function () {
                $scope.AddCommonCostParameterHidden = $scope.AddCommonCostParameterHidden ? false : true;
             };
             $scope.ShowHideUpdateCommonCostParameter = function () {
                $scope.UpdateCommonCostParameterHidden = $scope.UpdateCommonCostParameterHidden ? false : true;
             };
             $scope.ShowHideUploadCommonCostParameters = function () {
                $scope.UploadCommonCostParametersHidden = $scope.UploadCommonCostParametersHidden ? false : true;
             };
			 $scope.moveTop = function(){
					$location.hash('PageHeading'); 
					$anchorScroll();
			 };
			 $scope.moveBottom = function(){
					$location.hash('includedFooter'); 
					$anchorScroll();
			 }; 
			 
			$scope.getVisaTypes = function(countryId)
			{
				var getVisaTypesbyCountry = function(response) 
				{
				 	console.log("Visa type");
				 	console.log(response.data);
					$scope.visaTypeDetails = response.data;
				};
				WebServiceFactory.getVisaTypes(countryId).then(getVisaTypesbyCountry);	
			}
			 
			
			
			 var getCountryDetail = function(response) {
				 	console.log("Country");
				 	console.log(response.data);
					$scope.country = response.data;
			 };
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
			var getAssumptionsParamList = function(response) {
				console.log("getParamList");
			 	console.log(response.data);
				$scope.parameter = response.data;
			}
			WebServiceFactory.getAssumptionsParamList().then(getAssumptionsParamList);
			

			var currentYr = new Date().getFullYear();
		    var range = [];
		    range.push((currentYr-1)+"-"+(currentYr));
		    for (var i = 0; i < 4; i++) {
		    	range.push((currentYr + i)+"-"+(currentYr + i + 1));
		    }
		    $scope.years = range;
		    
		    $scope.onAssumptionsViewParameterForm = function(isValid){
		    	$scope.onViewSearch = true;
		    	if(isValid){
		    	$scope.downloadBtn = false;
		    	var markers = {
						"countryId": $scope.viewParameterForm.countryModel,
						"year":($scope.viewParameterForm.ddlSearchYearModel).split('-')[0]
				};
		    	console.log(markers);
				var onAssumptionsViewParameterForm = function(response) {
					console.log("response.data-----------");
					console.log(response.data);
					
					if(response.data != ""){
						$scope.commonCostDataTempArray = [];
					 	$scope.commonCostParamViewArray = response.data.commonCostDescData;
					 	console.log("The Assumptions Param View Array is..................  ");
					 	console.log($scope.commonCostParamViewArray);
					 	$scope.commonCostData  =  response.data.commonCostData;
					 	console.log("The Assumptions Data Array is................ ");
					 	console.log($scope.commonCostData);
					 	
					 	angular.forEach($scope.commonCostParamViewArray, function(value1, key1) {
					 		angular.forEach($scope.commonCostData, function(value2, key2) {
					 			console.log("$scope.commonCostData[key2][5]--"+$scope.commonCostData[key2][5]);
					 			if($scope.commonCostData[key2][5] == 1 && $scope.commonCostParamViewArray[key1].codeName == $scope.commonCostData[key2][1]){
					 				$scope.commonCostParamViewArray[key1].domestic = $scope.commonCostData[key2][3];
					 				var num = parseFloat($scope.commonCostParamViewArray[key1].domestic);						    
								    var domesticAll = num.toFixed(2);
								    $scope.commonCostParamViewArray[key1].domestic =domesticAll;
					 				console.log("domestic --- "+$scope.commonCostParamViewArray[key1].domestic);
					 			}
					 			else if($scope.commonCostData[key2][5] == 2 && $scope.commonCostParamViewArray[key1].codeName == $scope.commonCostData[key2][1]){
					 				$scope.commonCostParamViewArray[key1].deputed = $scope.commonCostData[key2][3];
					 				var num = parseFloat($scope.commonCostParamViewArray[key1].deputed);						    
								    var deputedAll = num.toFixed(2);
								    $scope.commonCostParamViewArray[key1].deputed =deputedAll;
					 				console.log("deputed --- "+$scope.commonCostParamViewArray[key1].deputed);
					 			}
					 			else if($scope.commonCostData[key2][5] == 3 && $scope.commonCostParamViewArray[key1].codeName == $scope.commonCostData[key2][1]){
					 				$scope.commonCostParamViewArray[key1].shortTerm = $scope.commonCostData[key2][3];
					 				var num = parseFloat($scope.commonCostParamViewArray[key1].shortTerm);						    
								    var shortTermAll = num.toFixed(2);
								    $scope.commonCostParamViewArray[key1].shortTerm =shortTermAll;
					 				console.log("Short term --- "+$scope.commonCostParamViewArray[key1].shortTerm);
					 			}
					 			else if($scope.commonCostData[key2][5] == 4 && $scope.commonCostParamViewArray[key1].codeName == $scope.commonCostData[key2][1]){
					 				$scope.commonCostParamViewArray[key1].offshore = $scope.commonCostData[key2][3];
					 				var num = parseFloat($scope.commonCostParamViewArray[key1].offshore);						    
								    var offshoreAll = num.toFixed(2);
								    $scope.commonCostParamViewArray[key1].offshore =offshoreAll;
					 				console.log("offshore --- "+$scope.commonCostParamViewArray[key1].offshore);
					 			}
					 		});
					 	});
					}else{
						$scope.commonCostParamViewArray = [];
						$scope.downloadBtn = false;
						BootstrapDialog.show({
		    	        	title : 'Master - Assumption Parameters',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : ' Assumption Parameters for the selected Country and Year does not exists.',
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
			    WebServiceFactory.onAssumptionsViewParameterForm(markers).then(onAssumptionsViewParameterForm);
		    	}
		    };
		    
		    
		    $scope.onAddAPSearch = function(isValid)
			{
		    	$scope.onSaveSearch = true;
		    	if(isValid)
		    	{
		    		var cntryId = $scope.addParameterForm.countryModel;
		    		var visaTypeId = $scope.addParameterForm.visaTypeModel;
		    		var year = ($scope.addParameterForm.addCCPYear).split('-')[0];
		    		
		    		var getArrAssumptionParam = function(response) 
					{
		    			if(response.status == 200)
						{
							$scope.arrAssumptionParam = response.data;
							for(i=0;i<response.data.length;i++)
							{
								var num = parseFloat(response.data[i].parDeduction);						    
							    var parDeduction = num.toFixed(2);
							    $scope.arrAssumptionParam[i].parDeduction = parDeduction;
							}
							console.log("$scope.arrAssumptionParam");
							console.log($scope.arrAssumptionParam);
						}
						else
						{
							$scope.arrAssumptionParam = [];
							BootstrapDialog.show({
			    	        	title : 'Master - Assumption Parameters',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Assumption Parameters for the selected Country, Visa Type and Year does not exists.',
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
					WebServiceFactory.onAddAPSearch(cntryId,visaTypeId,year).then(getArrAssumptionParam);	
		    	}
			}
			 
			
			$scope.onUpdateAPSearch = function(isValid){
				$scope.onUpdateSearch = true;
				if(isValid){
					$scope.isUpdate = false;	
				var cntryId = $scope.updateParameterForm.updateCountryModel;
				var paramId = $scope.updateParameterForm.updateParamName;
				var year = ($scope.updateParameterForm.updateCCPYear).split('-')[0];
				if(cntryId != undefined && paramId != undefined ){
					var markers = {
							"countryId": cntryId,
							"deductionTypeId": paramId,
							"year":year
					};

					var onUpdateAPSearch = function(response) {
						console.log("response.data is ------------------------------");
						console.log(response.data);
						$scope.commonCostData = response.data;
						if(response.data != ""){
							$scope.isSelected = true;
							angular.forEach($scope.commonCostData, function(value2, key2) {
								if($scope.commonCostData[key2].visaTypeId == 1){
									$scope.updateParameterForm.updateDomesticModel = $scope.commonCostData[key2].parDeduction;
								}else if($scope.commonCostData[key2].visaTypeId == 2){
									$scope.updateParameterForm.updateDeputedModel = $scope.commonCostData[key2].parDeduction;
								}else if($scope.commonCostData[key2].visaTypeId == 3){
									$scope.updateParameterForm.updateShortTermModel = $scope.commonCostData[key2].parDeduction;
								}else if($scope.commonCostData[key2].visaTypeId == 4){
									$scope.updateParameterForm.updateOffShoreModel = $scope.commonCostData[key2].parDeduction;
								}
								
								$scope.updateParameterForm.UpdateIsActive = true;
								console.log($scope.updateParameterForm.UpdateIsActive);
							})
						}else{
							 $scope.answer = 'Data has been added successfully';
				    	        BootstrapDialog.show({
				    	        	title : 'Master - Assumption Parameters',
				    	        	type : BootstrapDialog.TYPE_PRIMARY,
				    	        	message : 'Assumption Parameters for the selected Country and Year does not exists.',
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
//				    	        			$window.location.reload();
				    	        		}
				    	        	}]
				    	        });
						}
					}
				    WebServiceFactory.onUpdateAPSearch(markers).then(onUpdateAPSearch);
				}
				}
			};
			
			$scope.GetValue = function (visaTypeDetails) {
                var visaTypeId = $scope.addParameterForm.visaTypeModel;
                var visaTypeName = $.grep($scope.visaTypeDetails, function (visaTypeDetails) {
                    return visaTypeDetails.visaTypeId == visaTypeId;
                })[0].visaLabel;
                
                $scope.visaTypeName = visaTypeName;
            }
			
			
			
			
			$scope.update=false;
			$scope.updateData = function(updateCountryForm){
				$scope.onAddSave= true;
				if($scope.addParameterForm.$valid){
					$scope.onAddSave= false;
					$scope.updateAsumptionParameter();
				}
			};	
			
			$scope.updateAsumptionParameter = function(){
				var markers = [];
				for(var i=0;i<$scope.arrAssumptionParam.length;i++){
					
					markers.push({ 	
						"countryId": $scope.addParameterForm.countryModel,
						"year":($scope.addParameterForm.addCCPYear).split('-')[0],
						"visaTypeId": $scope.addParameterForm.visaTypeModel,
						"deductionTypeId":$scope.arrAssumptionParam[i].deductionTypeId,
						"parDeduction":$scope.arrAssumptionParam[i].parDeduction,
						"isActive":1
					});
				}
				var updateAsumptionParameter = function(response) 
				{
					console.log(response);
					if(response.status == 200) 
					{
						BootstrapDialog.show({
							title : 'Master - Assumption Parameters',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'Assumption Parameters updated successfully.',
							closable : false,
							buttons : [{
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									window.location = "MasterAssumptionsParameters";
								}
							}]
						});
					}
					else 
					{
						BootstrapDialog.show({
						title : 'Master - Assumption Parameters',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Currently We are facing technical issues, please try again later.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location = "MasterAssumptionsParameters";
								}
							} ]
						});
					}
		   	   }
				WebServiceFactory.updateAssumptionsParam(markers).then(updateAsumptionParameter);
			};
			
			$scope.getMasterAssumptionsExcel = function() {
				var cntryId = $scope.viewParameterForm.countryModel;
				var year = ($scope.viewParameterForm.ddlSearchYearModel).split('-')[0];
				
				window.location= contextPath+"/RightPrice-DAS/downloadAssumptionsExcel/"+cntryId+"/"+year
			};
			
			$scope.onCancelClickUpdate = function(){
				$scope.isSelected = false;
				$scope.updateParameterForm.updateCountryModel = null;
				$scope.updateParameterForm.updateCCPYear = null;
				$scope.updateParameterForm.updateParamName = null;
			}
			$scope.onCancelClickAdd = function(){
				$scope.onSaveSearch = false;
				$scope.arrAssumptionParam = [];
				$scope.addParameterForm.countryModel = null;
				$scope.addParameterForm.addCCPYear = null;
				$scope.addParameterForm.visaTypeModel = null;
			}
			
			var getDistDedCatId = function(response){
				console.log("deduction Category ids")
				console.log(response.data[0][1])
				for(var i=0;i<response.data[0].length;i++){
					
					if(response.data[0][i] == 1){
					$scope.DeductionCategoryDetails.push({"DeductionCategoryId":response.data[0][i],"DeductionCategoryName":"Common_Cost_Parameters"})
					}
					else if(response.data[0][i] == 2){
						$scope.DeductionCategoryDetails.push({"DeductionCategoryId":response.data[0][i],"DeductionCategoryName":"Tax_Parameters"})	
					}
					else if(response.data[0][i] == 3){
						$scope.DeductionCategoryDetails.push({"DeductionCategoryId":response.data[0][i],"DeductionCategoryName":"Assumptions"})	
					}
				}
				
				console.log("Visa Details")
				console.log($scope.DeductionCategoryDetails)
				$scope.VisaDetails = response.data[1];
				
			}
			WebServiceFactory.getDistDedCatId().then(getDistDedCatId);
			
			 $scope.onAssumptionsViewUpdatManualParameterForm = function(isValid){
				 
			    	$scope.onViewSearchManual = true;
			    	if(isValid){
			    		var deductionId = parseInt($scope.viewUpdatManualParameterForm.DeductionCategoryIdModel.DeductionCategoryId);
			    		var visaTypeId = parseInt($scope.viewUpdatManualParameterForm.VisaTypeModel)
			    	$scope.downloadBtn = false;
			    	var markers = {
							"countryId": $scope.viewUpdatManualParameterForm.countryUpdatManualModel,
							"year": "2019",
							"deductionCatId":deductionId,
							"visaTypeId":visaTypeId
							
					};
			    	console.log(markers);
					var onAssumptionsViewParameterForm = function(response) {
						console.log("response.data-----------");
						console.log(response.data);
						
						if(response.data != ""){
							

							console.log("response.data for searched criteria-----------");
							console.log(response.data);
							
							if(response.data != ""){
								$scope.DataPresent = false
								$scope.DeducData = response.data;
								
							}else{
								$scope.commonCostParamViewArray = [];
								$scope.downloadBtn = false;
								BootstrapDialog.show({
				    	        	title : 'Master - Assumption Parameters',
				    	        	type : BootstrapDialog.TYPE_PRIMARY,
				    	        	message : ' Assumption Parameters for the selected Country and Year does not exists.',
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
//				    	        			$window.location.reload();
				    	        		}
				    	        	}]
				    	        });
							}
						
							
							
							
							
						}else{
							$scope.commonCostParamViewArray = [];
							$scope.downloadBtn = false;
							BootstrapDialog.show({
			    	        	title : 'Master - Assumption Parameters',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : ' Assumption Parameters for the selected Country and Year does not exists.',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
//			    	        			$window.location.reload();
			    	        		}
			    	        	}]
			    	        });
						}
					}
				    WebServiceFactory.getMasterDeductionDataForm(markers).then(onAssumptionsViewParameterForm);
			    	}
			    };
			    
			
			    $scope.updateManualData = function(updateCountryForm){
					$scope.onUpdateManual= true;
					if($scope.checkedItems.length != 0){
					if($scope.viewUpdatManualParameterForm.$valid){
						$scope.onUpdateManual= false;
						$scope.updateAsumptionParameterManual();
					}
					}
					else{
						BootstrapDialog.show({
							title : 'Master - Assumption Parameter',
							type : BootstrapDialog.TYPE_DANGER,
							message : 'Please select atleast 1 Parameter.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								}
							} ]
						});
					}
				};	
				
				$scope.addChkItem = function (viewUpdatManualParameterForm,status,index) {
	                if (status==true) {
	                	$scope.makeUpdateAvail = false;
	                	$scope.addCheckedItemList(index,status);
	                } else {
	                	for(var i =0; i<$scope.checkedItems.length; i++){
	                		if($scope.checkedItems[i].index == index) {
	                			$scope.splicedArray = $scope.checkedItems.splice(i,1);
	                	}
	        		  }
	                	if($scope.checkedItems.length == 0 ){
	                		$scope.makeUpdateAvail = true;
	                	}
	                }
	            };
	            
	            $scope.addCheckedItemList = function (index,chkBoxStatus) {
					$scope.checkedItems.push ({
						"index" : index						
					});
					
	            	/*$scope.checkedItems.push ({
						 index						
					});*/
				};
			
				$scope.addAllCheckedItems = function(data,selectAllChckbxstatus) {
					
					if(data.length != 0 && selectAllChckbxstatus == true) {
						for(var i=0;i<data.length;i++) {
							data[i].chkBoxStatus = true;
							$scope.addCheckedItemList(i,data[i].chkBoxStatus,data[i].masterRoleShortDescription);
						}
					}else if(selectAllChckbxstatus == false ){
						for(var i=0;i<data.length;i++) {
						data[i].chkBoxStatus = false;
	                	$scope.splicedArray = $scope.checkedItems.splice(i,1);
						}
					} else {
						$scope.selectAllCheckBox = false;
						BootstrapDialog.show({
							title : 'Rate Card Roles Creation',
							type : BootstrapDialog.TYPE_DANGER,
							message : 'Please search atleast 1 role.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								}
							} ]
						});
					}
				}
				
				
				$scope.updateAsumptionParameterManual = function(){
					$scope.updateArray = [];
					var j = 0;
					for(var i=0;i<$scope.DeducData.length;i++){
						for(var k=0;k<$scope.checkedItems.length;k++){
							if( i == $scope.checkedItems[k].index)
								{
								$scope.updateArray[j] = $scope.DeducData[i]
								j++;
								}
						}	
						
					}
					
					console.log("Updated --- array")
					console.log($scope.updateArray)
						
					var markers = [];
					
					
					for(var i=0;i<$scope.updateArray.length;i++){
						//fixed deduction is codeName
						// parDeduction is percent deduction
						
						if($scope.updateArray[i].FixedDeductionModel == null ||
								$scope.updateArray[i].FixedDeductionModel == undefined || $scope.updateArray[i].FixedDeductionModel == "" ){
							
							var fixedDeduc = $scope.updateArray[i].codeName;
						}else {
							var fixedDeduc = parseFloat($scope.updateArray[i].FixedDeductionModel)
						}
						
						if($scope.updateArray[i].PercentDeductionModel == null ||
								$scope.updateArray[i].PercentDeductionModel == undefined || $scope.updateArray[i].PercentDeductionModel == "" ){
							var percentDeduc = $scope.updateArray[i].parDeduction;
						}else {
							var percentDeduc = parseFloat($scope.updateArray[i].PercentDeductionModel);
						}
						
						markers.push({ 	
							"deductionid":$scope.updateArray[i].deductionid,
							"deductionCatId":$scope.updateArray[i].deductionCatId,
							"deductionTypeId":$scope.updateArray[i].deductionTypeId,
							"codeName": fixedDeduc,
							"parDeduction": percentDeduc,
							"countryId":$scope.updateArray[i].countryId,
							"visaTypeId":$scope.updateArray[i].visaTypeId,
							"year":$scope.updateArray[i].year
						});
					}
						console.log(markers)
					var updateAsumptionParameterValue = function(response) 
					{
						console.log(response);
						if(response.status == 200) 
						{
							BootstrapDialog.show({
								title : 'Master - Assumption Parameters',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : 'Assumption Parameters updated successfully.',
								closable : false,
								buttons : [{
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										window.location = "MasterAssumptionsParameters";
									}
								}]
							});
						}
						else 
						{
							BootstrapDialog.show({
							title : 'Master - Assumption Parameters',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Currently We are facing technical issues, please try again later.",
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									window.location = "MasterAssumptionsParameters";
									}
								} ]
							});
						}
			   	   }
					WebServiceFactory.updateAssumptionsParamValue(markers).then(updateAsumptionParameterValue);

					
					
					
					};
		    
		}]);

