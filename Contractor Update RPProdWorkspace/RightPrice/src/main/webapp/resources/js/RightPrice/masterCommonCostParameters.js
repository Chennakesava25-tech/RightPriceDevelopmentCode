//var app = angular.module('RightPrice', ['ngMessages']);
		app.controller("MasterCommonCostParametersController", [
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
			 $scope.visaTypeDetails =[];
			 $scope.arrCCParam = [];
			 
			 var contextPath = "/RightPrice-DAS";
             $scope.ShowHideViewCommonCostParameters = function () {
                $scope.ViewCommonCostParametersHidden = $scope.ViewCommonCostParametersHidden ? false : true ;
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
			 var getCountryDetail = function(response) {
				 	console.log("Country");
				 	console.log(response.data);
					$scope.country = response.data;
			 };
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
			var getParamList = function(response) {
				console.log("getParamList");
			 	console.log(response.data);
				$scope.parameter = response.data;
			}
			WebServiceFactory.getParamList().then(getParamList);
			
			var currentYr = new Date().getFullYear();
		    var range = [];
		    range.push((currentYr-1)+"-"+(currentYr));
		    for (var i = 0; i < 4; i++) {
		    	range.push((currentYr + i)+"-"+(currentYr + i + 1));
		    }
		    $scope.years = range;
		    
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
		    
		    $scope.onViewParameterForm = function(isValid){
		    	$scope.onViewSearch = true;
		    	if(isValid){
		    	$scope.downloadBtn = false;
		    	$scope.viewParameterForm.ddlSearchYearModelSplit=($scope.viewParameterForm.ddlSearchYearModel).split('-')[0];
		    	var markers = {
						"countryId": $scope.viewParameterForm.countryModel,
						"year":$scope.viewParameterForm.ddlSearchYearModelSplit
				};
		    	console.log(markers);
				var onViewParameterForm = function(response) {
					console.log("response.data-----------");
					console.log(response.data);
					
					if(response.data != ""){
						$scope.commonCostDataTempArray = [];
					 	$scope.commonCostParamViewArray = response.data.commonCostDescData;
					 	console.log("The Common Cost Param View Array is..................  ");
					 	$scope.commonCostData  =  response.data.commonCostData;
					 	console.log("The Common Cost Data Array is................ ");
					 	console.log($scope.commonCostData);
					 	console.log($scope.commonCostParamViewArray);
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
		    	        	title : 'Master - Common Cost Parameters',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : ' Common Cost Parameters for the selected Country and Year does not exists.',
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
			    WebServiceFactory.onViewParameterForm(markers).then(onViewParameterForm);
		    	}
		    };
		    
		    $scope.onAddCCPSearch = function(isValid){
		    	$scope.onSaveSearch = true;
		    	if(isValid){
				var cntryId = $scope.addParameterForm.countryModel;
				var paramId = $scope.addParameterForm.addParamName;
				var year = ($scope.addParameterForm.addCCPYear).split('-')[0];
				if(cntryId != undefined && paramId != undefined ){
					var markers = {
							"countryId": cntryId,
							"deductionTypeId": paramId,
							"year":year
					};
					var onAddCCPSearch = function(response) {
						//$scope.onViewSearch = false;
						if(response.data == "AlreadyAdded"){
							$scope.answer = 'Data has already been added.';
			    	        BootstrapDialog.show({
			    	        	title : 'Master - Common Cost Parameters',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Common Cost Parameters for the selected Country, Parameter and Year already exists.',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
//			    	        			$window.location.reload();
			    	        		}
			    	        	}]
			    	        });
						}else{
							$scope.addCommonCostParameterArray=[];
							$scope.addCommonCostParameterArray.push({});
						}
					}
				    WebServiceFactory.onAddCCPSearch(markers).then(onAddCCPSearch);
				}
		    	}
			};
			
			$scope.onUpdateCCPSearch = function(isValid)
			{
				$scope.onUpdateSearch = true;
		    	if(isValid)
		    	{ 	$scope.updateParameterForm.updateCCPYearSplit=($scope.updateParameterForm.updateCCPYear).split('-')[0];
		    		$scope.onUpdateSearch = false;
		    		var cntryId = $scope.updateParameterForm.updateCountryModel;
		    		var visaTypeId = $scope.updateParameterForm.visaTypeModel;
		    		var year = $scope.updateParameterForm.updateCCPYearSplit
		    		
		    		var getArrCCParam = function(response) 
					{
		    			if(response.status == 200)
						{
							$scope.arrCCParam = response.data;
							for(i=0;i<response.data.length;i++)
							{
								var num = parseFloat(response.data[i].parDeduction);						    
							    var parDeduction = num.toFixed(2);
							    $scope.arrCCParam[i].parDeduction = parDeduction;
							}
							console.log("$scope.arrCCParam");
							console.log($scope.arrCCParam);
						}
						else
						{
							$scope.arrCCParam = [];
							BootstrapDialog.show({
			    	        	title : 'Master - Common Cost Parameters',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Common Cost Parameters for the selected Country, Visa Type and Year does not exists.',
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
					};
					WebServiceFactory.onUpdateCCPSearch(cntryId,visaTypeId,year).then(getArrCCParam);
		    	}
			}
			
			
			$scope.updateData = function(isValid){
				$scope.onUpdateClick = true;
				if(isValid){
					$scope.updateCCParameter();
				}
			};
			
			$scope.updateCCParameter = function(){
				var markers = [];
				for(var i=0;i<$scope.arrCCParam.length;i++){
					$scope.updateParameterForm.updateCCPYearSplit==($scope.updateParameterForm.updateCCPYear).split('-')[0];
					markers.push({ 	
						"countryId": $scope.updateParameterForm.updateCountryModel,
						"year":$scope.updateParameterForm.updateCCPYearSplit,
						"visaTypeId": $scope.updateParameterForm.visaTypeModel,
						"deductionTypeId":$scope.arrCCParam[i].deductionTypeId,
						"parDeduction":$scope.arrCCParam[i].parDeduction,
						"isActive":1
					});
				}
				var updateCCParameter = function(response) 
				{
					console.log(response);
					if(response.status == 200) 
					{
						BootstrapDialog.show({
							title : 'Master - Common Cost Parameters',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'Common Cost Parameters updated successfully.',
							closable : false,
							buttons : [{
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									window.location = "MasterCommonCostParameters";
								}
							}]
						});
					}
					else 
					{
						BootstrapDialog.show({
						title : 'Master - Common Cost Parameters',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Currently We are facing technical issues, please try again later.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location = "MasterCommonCostParameters";
								}
							} ]
						});
					}
		   	   }
				WebServiceFactory.updateCommonCostParam(markers).then(updateCCParameter);
			};
			
			$scope.getMasterCommonCostExcel = function() {
				var cntryId = $scope.viewParameterForm.countryModel;
				var year = ($scope.viewParameterForm.ddlSearchYearModel).split('-')[0];
				
				window.location= contextPath+"/RightPrice-DAS/downloadMasterCommonCostExcel/"+cntryId+"/"+year
			};
			
			$scope.onCancelClickUpdate = function(){
				$scope.isSelected = false;
				$scope.updateParameterForm.updateCountryModel = null;
				$scope.updateParameterForm.updateCCPYear = null;
				$scope.updateParameterForm.visaTypeModel = null;
				$scope.arrCCParam = [];
			}
		    
		}]);