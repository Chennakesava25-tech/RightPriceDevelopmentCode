//var app = angular.module('MasterCountryVisaApp', ['ngMessages']);
		app.controller("MasterCountryVisaController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index){
			
		/*var contextPath = $window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));	*/
			
			$scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.isStaffingVal=0;
			 $scope.isStaffingDone=0;
			 $scope.isStaffingUpdVal=0;
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            };
             $scope.ShowHideAdd = function () {
                $scope.AddHidden = $scope.AddHidden ? false : true;
                $scope.onSave= false;
                $scope.AddVisaForm.AddCountryModel=null;
				$scope.AddVisaForm.AddVisaModel=null
				$scope.AddVisaForm.AddVisaLabelModel=null;
            };
             $scope.ShowHideUpdate = function () {
                $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
                $scope.onUpdate=false;
                $scope.UpdateVisaForm.UpdateCountryModel=null;
				$scope.UpdateVisaForm.UpdateVisaModel=null;
				$scope.UpdateVisaForm.UpdateVisaLabelModel=null;
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
			/*country Dropdown--------------*/
			var getCountry = function(response) {
				console.log("country")
				$scope.country = response.data;
			};
			WebServiceFactory.getCountry().then(getCountry);
			
			/*--------------country Dropdown*/
			
			/* visa DropDown--------------------*/
			
			var getVisaTypeDetails = function(response) {
				console.log("visa");
				console.log(response.data);
				$scope.visa = response.data;
			};
			WebServiceFactory.getVisaTypeDetails().then(getVisaTypeDetails);
			
			/*--------------------visa DropDown*/		
			
			/*Search Validation--------------*/
			$scope.onSearch= false;	
			$scope.searchData = function(viewVisaForm){
				$scope.onSearch= true;
				if(viewVisaForm.$valid){
					$scope.onSearch= false;	
					/*alert("validated search");*/
					$scope.viewVisaDetails();
				}
			};	
			/*--------------Search Validation*/
			
			/*Save Validation--------------*/
			$scope.onSave= false;	
			$scope.saveData = function(AddVisaForm){
				$scope.onSave= true;
				if(AddVisaForm.$valid){
					$scope.onSave= false;	
					/*alert("validated add");*/
					$scope.addVisaDetails();
				}
			};	
			/*--------------Save Validation*/
			
			/*update Validation--------------*/
			$scope.onUpdate=false;
			$scope.updateData = function(UpdateVisaForm){
				$scope.onUpdate= true;
				if(UpdateVisaForm.$valid){
					$scope.updateData= false;
					/*alert("validated add");*/
					$scope.updateVisaDetails();
				}
			};	
			/*--------------update Validation*/
			
			/*cancel button..................*/
			
			$scope.cancel = function(){
				$scope.onSave= false;
				$scope.onUpdate=false;
				$scope.AddVisaForm.AddCountryModel=null;
				$scope.AddVisaForm.AddVisaModel=null
				$scope.AddVisaForm.AddVisaLabelModel=null;
				$scope.UpdateVisaForm.UpdateCountryModel=null;
				$scope.UpdateVisaForm.UpdateVisaModel=null;
				$scope.UpdateVisaForm.UpdateVisaLabelModel=null;
			};
			
			/*--------------Cancel Button*/
			
			/* add Visa Details-------------*/
			
			$scope.addVisaDetails = function(){
				
				if($scope.AddVisaForm.addStaffingChecked){
					$scope.isStaffingVal=1;
				}else{
					$scope.isStaffingVal=0;
				}
				
				var marker = {
						"countryId": $scope.AddVisaForm.AddCountryModel.countryId,
						"visaTypeId": $scope.AddVisaForm.AddVisaModel.visaId,
						"visaLabel":$scope.AddVisaForm.AddVisaLabelModel,
						"isStaffing":$scope.isStaffingVal
						
				};
				/*alert(marker);*/
				
	    	        var addVisaDetails = function(response) 
	    	        { 
	    	        	var customMessage = response.data;
	    	        	if(response.status == 200)
	    	        	{	
			    	        BootstrapDialog.show({
			    	        	title : 'Visa Details',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'Data Saved SucessFully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "MasterCountryVisa";
			    	        		}
			    	        	}]
			    	        });
	    	        	}
	    	        	else if(response.status == 203)
	    	    		{	
	    	    	        BootstrapDialog.show({
	    	    	        	title : 'Visa Details',
	    	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	    	        	message : customMessage,
	    	    	        	closable : false,
	    	    	        	buttons : [{
	    	    	        		label : 'OK',
	    	    	        		action : function(dialogRef) {
	    	    	        			dialogRef.close();
	    	    	        			window.location = "MasterCountryVisa";
	    	    	        		}
	    	    	        	}]
	    	    	        });
	    	        	}
	    	        	else{
		    	        	BootstrapDialog.show({
			    	        	title : 'Visa Details',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : 'Currently We are facing technical issues, please try again later.',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "MasterCountryVisa";
			    	        		}
			    	        	}]
			    	        });
		    	        }
	    	        }    
		    	       
	    	        WebServiceFactory.addVisaDetails(marker).then(addVisaDetails);
				}
			$scope.visaAlreadyAdded =function(countryId,addVisaId){
				var getVisaLabel = function(response) {
					console.log("Visa Label");
					console.log(response);
					console.log(response.data);
					$scope.visaLabel = response.data;
					if(response.status == 200){
						BootstrapDialog.show({
		    	        	title : 'Visa Label',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : 'Visa Label already added.',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MasterCountryVisa";
		    	        		}
		    	        	}]
		    	        });
					}
					
				};
				
				WebServiceFactory.getVisaLabel(countryId,addVisaId).then(getVisaLabel);
			};
			$scope.getVisaLabel = function(countryId,visaTypeId){
				if(countryId != undefined && visaTypeId != undefined ){
					var getVisaLabel = function(response) {
						console.log("Visa Label");
						console.log(response);
						console.log(response.data);
						$scope.visaLabel = response.data;
						if($scope.visaLabel!=""){
							$scope.isStaffingDone=$scope.visaLabel[0].isStaffing;
							if($scope.isStaffingDone==1){
								$scope.UpdateVisaForm.updateStaffingChecked=true;
							}
							else{
								$scope.UpdateVisaForm.updateStaffingChecked=false;
							}
						}
						
						
						if(response.status == 200){
						$scope.putVisaLabel($scope.visaLabel[0].visaLabel);
						}
						else{	
				    	        BootstrapDialog.show({
				    	        	title : 'Visa Label',
				    	        	type : BootstrapDialog.TYPE_DANGER,
				    	        	message : 'No record Found',
				    	        	closable : false,
				    	        	buttons : [{
				    	        		label : 'OK',
				    	        		action : function(dialogRef) {
				    	        			dialogRef.close();
				    	        			window.location = "MasterCountryVisa";
				    	        		}
				    	        	}]
				    	        });
		    	        	} 
					};
					
					WebServiceFactory.getVisaLabel(countryId,visaTypeId).then(getVisaLabel);
					/*console.log("Visa Labellllllllllllllllllllllll");*/
				}
			};	

			$scope.putVisaLabel=function(aa){
				/*alert(aa);*/
				$scope.UpdateVisaForm.UpdateVisaLabelModel=aa;
				/*$scope.UpdateVisaForm.UpdateVisaLabelModel=$scope.UpdateVisaForm.UpdateVisaLabelModel.visaLabel;
				console.log($scope.UpdateVisaForm.UpdateVisaLabelModel.visaLabel);*/
			}
			$scope.updateVisaDetails = function(){
				
				if($scope.UpdateVisaForm.updateStaffingChecked){
					$scope.isStaffingUpdVal=1;
				}else{
					$scope.isStaffingUpdVal=0;
				}
				var markers = {
						"countryId": $scope.UpdateVisaForm.UpdateCountryModel.countryId,
						"visaTypeId": $scope.UpdateVisaForm.UpdateVisaModel.visaId,
						"visaLabel":$scope.UpdateVisaForm.UpdateVisaLabelModel,
						"countryVisaLabelId":$scope.visaLabel[0].countryVisaLabelId,
						"isStaffing":$scope.isStaffingUpdVal
							
						};
							var updateVisaDetails = function(response) {
			   	        BootstrapDialog.show({
			   	        	title : 'Visa Details',
			   	        	type : BootstrapDialog.TYPE_PRIMARY,
			   	        	message : 'Visa Data Updated SucessFully',
			   	        	closable : false,
			   	        	buttons : [{
			   	        		label : 'OK',
			   	        		action : function(dialogRef) {
			   	        			dialogRef.close();
			   	        			window.location = "MasterCountryVisa";
			   	        		}
			   	        	}]
			   	        });
			   	        
					}
					WebServiceFactory.updateVisaDetails(markers).then(updateVisaDetails);
				};
				/*-------------Update  Visa Details*/
			
				/*View Visa Details-----------*/
				$scope.viewVisaDetails=function(){
					
					var countryId=$scope.viewVisaForm.ViewCountryModel.countryId;
					
							console.log("country id"+countryId);
							console.log('merkers');
							var markers = {
								"countryId" : countryId
					} ;
					
					console.log(markers);
				
						var viewVisaDetails = function(response) {	
					    	$scope.viewVisaData = response.data;
					    	console.log("Visa Details:")
				    	console.log( response.data);
					    	console.log($scope.viewVisaData.length);
					    	 if(($scope.viewVisaData.length) < 1){
								  BootstrapDialog.show({
											title : 'View Visa Data',
											type : BootstrapDialog.TYPE_DANGER,
											message : 'No Record found.',
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
						WebServiceFactory.viewVisaDetails(markers).then(viewVisaDetails);
					};	
				
				/*-------------Update  Visa Details*/
			
		}]);