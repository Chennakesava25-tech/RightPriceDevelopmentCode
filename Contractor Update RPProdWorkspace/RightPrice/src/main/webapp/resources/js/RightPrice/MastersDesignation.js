//var app = angular.module('RightPriceApp', ['ngMessages']);
		app.controller("MastersDesignationController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index) {
			 $scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.onSave = false;
			 $scope.onUpdate = false;
			 $scope.characterRegex = /^[a-zA-Z!@#$&()`.+, /"-]*$/;
			 $scope.numberRegex = /^(0|[1-9]\d{0,1})(\.\d{1,2})?$/;
			 var contextPath = "/RightPrice-DAS";
			 
			 
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            };
             $scope.ShowHideAdd = function () {
                $scope.AddHidden = $scope.AddHidden ? false : true;
                $scope.onSave = false
                $scope.addMasterDesignationForm.adddesignationmodel="";
				$scope.addMasterDesignationForm.addbandmodel=null;
				$scope.addMasterDesignationForm.addgrademodel=null;
				$scope.addMasterDesignationForm.addminimumexperiencemodel="";
				$scope.addMasterDesignationForm.addmaximumexperiencemodel="";
            };
             $scope.ShowHideUpdate = function () {
                $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
                $scope.onUpdate = false;
				$scope.updateMasterDesignationForm.updatedesignationmodel=null;
				$scope.updateMasterDesignationForm.updatebandmodel=null;
				$scope.updateMasterDesignationForm.updategrademodel=null;
				$scope.updateMasterDesignationForm.updateminimumexperiencemodel="";
				$scope.updateMasterDesignationForm.updatemaximumexperiencemodel="";
				$scope.updateMasterDesignationForm.cbxUpdateActiveInactive = false;
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
			 
			$scope.getMasterDesigExcel = function() {
				 window.location= contextPath+"/RightPrice-DAS/downloadDesignationExcel"
			};
				$scope.onSaveClick = function(addMasterDesignationForm) {
					$scope.onSave = true;
					if(addMasterDesignationForm.$valid){
						$scope.onSave = false;
					$scope.addDesignation();
					}
				};
				
				$scope.onUpdateClick = function(updateMasterDesignationForm) {
					$scope.onUpdate = true;
					if(updateMasterDesignationForm.$valid){
						$scope.onUpdate = false;
					$scope.updateDesignation();
					}
				};
				
				$scope.cancelClickOnAddPanal = function(addMasterDesignationForm) {
					$scope.onSave = false
					$scope.addMasterDesignationForm.adddesignationmodel="";
					$scope.addMasterDesignationForm.addbandmodel=null;
					$scope.addMasterDesignationForm.addgrademodel=null;
					$scope.addMasterDesignationForm.addminimumexperiencemodel="";
					$scope.addMasterDesignationForm.addmaximumexperiencemodel="";
					
				};
				$scope.cancelClickOnUpdatePanal = function(updateMasterDesignationForm) {
					$scope.onUpdate = false;
					$scope.updateMasterDesignationForm.updatedesignationmodel=null;
					$scope.updateMasterDesignationForm.updatebandmodel=null;
					$scope.updateMasterDesignationForm.updategrademodel=null;
					$scope.updateMasterDesignationForm.updateminimumexperiencemodel="";
					$scope.updateMasterDesignationForm.updatemaximumexperiencemodel="";
					$scope.updateMasterDesignationForm.cbxUpdateActiveInactive = false;
				};
								
			var getDesignation = function(response){
				$scope.designation = response.data;
				var num = 0;
				for(i=0;i<response.data.length;i++)
				{
					if(response.data[i].maximumExperienceYears != null)
					{
						num = parseFloat(response.data[i].maximumExperienceYears);						    
						var maxExp = num.toFixed(2);
						$scope.designation[i].maximumExperienceYears = maxExp;
					}
				    if(response.data[i].minimumExperienceYears != null)
				    {
				    	num = parseFloat(response.data[i].minimumExperienceYears);						    
				    	var minExp = num.toFixed(2);
				    	$scope.designation[i].minimumExperienceYears = minExp;
				    }
				}
				
				console.log(response.data);
			};
			WebServiceFactory.getDesignation().then(getDesignation);
			
			var getBand = function(response){
				$scope.band = response.data;
			};
			WebServiceFactory.getBand().then(getBand);
			
			var getGrade = function(response){
				$scope.grade = response.data;
			};
			WebServiceFactory.getGrade().then(getGrade);
			
			$scope.putdesignationDetails = function(designationDetails){
				$scope.updateMasterDesignationForm.updatebandmodel = designationDetails.band.codeName;
				$scope.updateMasterDesignationForm.updategrademodel = designationDetails.grade.codeName;
				$scope.updateMasterDesignationForm.updateminimumexperiencemodel = designationDetails.minimumExperienceYears;
				$scope.updateMasterDesignationForm.updatemaximumexperiencemodel = designationDetails.maximumExperienceYears;
				
				if(designationDetails.isActive != "InActive") {
					$scope.updateMasterDesignationForm.cbxUpdateActiveInactive = true;
				} 
				else {
					$scope.updateMasterDesignationForm.cbxUpdateActiveInactive = false;
				}
			};
			
			$scope.addDesignation = function(){
				var markers = {
						"empDesignationDescription":$scope.addMasterDesignationForm.adddesignationmodel,
						"empBandId":$scope.addMasterDesignationForm.addbandmodel,
						"empGradeId":$scope.addMasterDesignationForm.addgrademodel,
						"minimumExperienceYears":$scope.addMasterDesignationForm.addminimumexperiencemodel,
						"maximumExperienceYears":$scope.addMasterDesignationForm.addmaximumexperiencemodel							
				}
				console.log(markers);
				var addDesignation = function(response) 
				{
					
					var customMessage = response.data;
		    		
		    		if(response.status == 200){	
		    	        BootstrapDialog.show({
		    	        	title : 'Employee Designation',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Data Saved SucessFully',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MastersDesignation";
		    	        		}
		    	        	}]
		    	        });
		        	}
		    		else if(response.status == 203)
		    		{	
		    	        BootstrapDialog.show({
		    	        	title : 'Employee Designation',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : customMessage,
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MastersDesignation";
		    	        		}
		    	        	}]
		    	        });
		        	}
		        	else 
		        	{
						BootstrapDialog.show({
						title : 'Employee Designation',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Currently We are facing technical issues, please try again later.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location = "MastersDesignation";
							}
						} ]
						});
		        	}
				}
				WebServiceFactory.addDesignation(markers).then(addDesignation);
		};
		
		$scope.updateDesignation = function(){
			var checkboxval = $scope.updateMasterDesignationForm.cbxUpdateActiveInactive;
			console.log("value of Checkbox is "+ checkboxval);
			var isActive = 0;
			if(checkboxval) {
				isActive = 1;
			} else {
				isActive = 0;
			}
			var markers = {
					
					"empDesignationId":$scope.updateMasterDesignationForm.updatedesignationmodel.empDesignationId,
					"empBandId":$scope.updateMasterDesignationForm.updatebandmodel,
					"empGradeId":$scope.updateMasterDesignationForm.updategrademodel,
					"minimumExperienceYears":$scope.updateMasterDesignationForm.updateminimumexperiencemodel,
					"maximumExperienceYears":$scope.updateMasterDesignationForm.updatemaximumexperiencemodel,
					"isActive" : isActive
			}
			console.log(markers);
			var updateDesignation = function(response) {
	   	        BootstrapDialog.show({
	   	        	title : 'Employee Designation',
	   	        	type : BootstrapDialog.TYPE_PRIMARY,
	   	        	message : 'Updated SucessFully',
	   	        	closable : false,
	   	        	buttons : [{
	   	        		label : 'OK',
	   	        		action : function(dialogRef) {
	   	        			dialogRef.close();
	   	        			window.location = "MastersDesignation";
	   	        		}
	   	        	}]
	   	        });
			}
			WebServiceFactory.updateDesignation(markers).then(updateDesignation);
	};
		
	$scope.downloadexcelreport = function(){
		var desiganitionDetail = function(response){
			$scope.empdesignation = response.data;	
		}
		WebServiceFactory.downloadexcelreport().then(downloadexcelreport);
		};
		
		$scope.exportToExcel=function(tableId){ // ex: '#my-table'
	    	//alert('Clicked');
	        var exportHref=WebServiceFactory.tableToExcel(tableId,'Master_Designation');
	    }
	 
}]);