//	var app = angular.module('RightPriceApp', ['ngMessages']);
		app.controller("MastersVerticalController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index){
			var contextPath = "/RightPrice-DAS";
			$scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.onSave = false;
			 $scope.onUpdate = false;
			 
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
			
			$scope.onSaveClick = function(addMasterVerticalForm) {
				$scope.onSave = true;
				if(addMasterVerticalForm.$valid){
					$scope.onSave = false;
					$scope.addVertical();
				}
			};
			
			$scope.onUpdateClick = function(updateMasterVerticalForm) {
				$scope.onUpdate = true;
				if(updateMasterVerticalForm.$valid){
					$scope.onUpdate = false;
				$scope.updateVertical();
				}
			};
			
			$scope.cancelClickOnAdd = function(addMasterVerticalForm) {
				$scope.onSave = false
				$scope.addmasterverticalform.addverticalmodel=null;
				$scope.addmasterverticalform.adddeliveryheadempidmodel="";
				$scope.addmasterverticalform.addbuhempidmodel="";
				$scope.addmasterverticalform.addRiskManagersempidmodel="";
				$scope.addmasterverticalform.adddeliveryheadnamemodel ="";
	    		$scope.addmasterverticalform.adddeliveryheademailidmodel ="";
	    		$scope.addmasterverticalform.addbuhnamemodel ="";
    			$scope.addmasterverticalform.addbuhemailidmodel ="";
    			$scope.addmasterverticalform.addRiskManagersnamemodel ="";
	    		$scope.addmasterverticalform.addRiskManagersemailidmodel ="";
				
			};
			$scope.cancelClickOnUpdate = function(updateMasterVerticalForm) {
				$scope.onUpdate = false;
				$scope.updatemasterverticalform.updateverticalmodel=null;
				$scope.updatemasterverticalform.updatedeliveryheadempidmodel="";
				$scope.updatemasterverticalform.updatebuhempidmodel="";
				$scope.updatemasterverticalform.updateRiskManagersempidmodel="";
				$scope.updatemasterverticalform.updatedeliveryheadnamemodel="";
	    		$scope.updatemasterverticalform.updatedeliveryheademailidmodel="";
	    		$scope.updatemasterverticalform.updatebuhnamemodel="";
	    		$scope.updatemasterverticalform.updatebuhemailidmodel="";
	    		$scope.updatemasterverticalform.updateRiskManagersnamemodel="";
	    		$scope.updatemasterverticalform.updateRiskManagersemailidmodel="";
				$scope.updatemasterverticalform.cbxUpdateIsActive = false;
				$scope.updatemasterverticalform.cbxUpdateVerticalStatus = false;
			};
			
			var getVertical = function(response){
				$scope.vertical = response.data;
				console.log(response.data);
			};
			WebServiceFactory.getVertical().then(getVertical);
			
			var getVerticalGroupID = function(response){
				$scope.verticalgroup = response.data;
				console.log(response.data)
			};
			WebServiceFactory.getVerticalGroupID().then(getVerticalGroupID);
			
			
			$scope.getVerticalExcel = function() {
				 window.location= contextPath+"/RightPrice-DAS/downloadVerticalExcel"
			};
			
			
			$scope.putverticalDetails = function(verticalDetails){
				$scope.updatemasterverticalform.updatedeliveryheadempidmodel = verticalDetails.deliveryHeadId;
				$scope.updatemasterverticalform.updatedeliveryheadnamemodel = verticalDetails.duh;
				$scope.updatemasterverticalform.updatedeliveryheademailidmodel = verticalDetails.deliveryHeadEmail;
				$scope.updatemasterverticalform.updatebuhempidmodel = verticalDetails.buHeadId;
				$scope.updatemasterverticalform.updatebuhnamemodel = verticalDetails.buh;
				$scope.updatemasterverticalform.updatebuhemailidmodel = verticalDetails.buHeadEmail;
				$scope.updatemasterverticalform.updateRiskManagersempidmodel = verticalDetails.RiskManagersPersonId;
				$scope.updatemasterverticalform.updateRiskManagersnamemodel = verticalDetails.RiskManagers;
				$scope.updatemasterverticalform.updateRiskManagersemailidmodel = verticalDetails.RiskManagersPersonEmail;

				if(verticalDetails.isActive ==1) {
					$scope.updatemasterverticalform.cbxUpdateIsActive = true;
				} 
				else {
					$scope.updatemasterverticalform.cbxUpdateIsActive = false;
				}
				
				if(verticalDetails.verticalStatusFlag ==1) {
					$scope.updatemasterverticalform.cbxUpdateVerticalStatus = true;
				} 
				else {
					$scope.updatemasterverticalform.cbxUpdateVerticalStatus = false;
				}
				
			};
			
			$scope.addVertical = function(){
			var markers = {
					"verticalId":parseInt($scope.addmasterverticalform.addverticalmodel.VerticalGrpId),
					"verticalName":$scope.addmasterverticalform.addverticalmodel.verticalGrpDesc,
					"deliveryHeadId":$scope.addmasterverticalform.adddeliveryheadempidmodel,
					"duh":$scope.addmasterverticalform.adddeliveryheadnamemodel,
					"deliveryHeadEmail":$scope.addmasterverticalform.adddeliveryheademailidmodel,
					"buHeadId":$scope.addmasterverticalform.addbuhempidmodel,
					"buh":$scope.addmasterverticalform.addbuhnamemodel,
					"buHeadEmail":$scope.addmasterverticalform.addbuhemailidmodel,
					"RiskManagersPersonId":$scope.addmasterverticalform.addRiskManagersempidmodel,
					"RiskManagers":$scope.addmasterverticalform.addRiskManagersnamemodel,
					"RiskManagersPersonEmail":$scope.addmasterverticalform.addRiskManagersemailidmodel
			}
			console.log(markers);
			var addVertical = function(response) {
				if(response.status == 200){
					BootstrapDialog.show({
						title : 'Masters Vertical',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'Saved SucessFully',
						closable : false,
						buttons : [{
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location = "MastersVertical";
							}
						}]
					});	
				}
				else if(response.status == 203)
				{
					 BootstrapDialog.show({
							title : 'Masters Vertical',
							type : BootstrapDialog.TYPE_DANGER,
							message : response.data,
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								}
							} ]
						});
				}
				else
				{
					BootstrapDialog.show({
						title : 'Masters Vertical',
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
			}
			WebServiceFactory.addVertical(markers).then(addVertical);
	};
			
			$scope.updateVertical = function(){
				var checkboxvalIsActive = $scope.updatemasterverticalform.cbxUpdateIsActive;
				console.log("value of Checkbox is "+ checkboxvalIsActive);
				var isActive = 0;
				if(checkboxvalIsActive) {
					isActive = 1;
				} else {
					isActive = 0;
				}
				var checkboxvalVerticalStatus = $scope.updatemasterverticalform.cbxUpdateVerticalStatus;
				console.log("value of Checkbox is "+ checkboxvalVerticalStatus);
				var verticalStatusFlag = 0;
				if(checkboxvalVerticalStatus) {
					verticalStatusFlag = 1;
				} else {
					verticalStatusFlag = 0;
				}
				var markers = {
						"verticalId":$scope.updatemasterverticalform.updateverticalmodel.verticalId,
						"verticalName":$scope.updatemasterverticalform.updateverticalmodel.verticalName,
						"deliveryHeadId":$scope.updatemasterverticalform.updatedeliveryheadempidmodel,
						"duh":$scope.updatemasterverticalform.updatedeliveryheadnamemodel,
						"deliveryHeadEmail":$scope.updatemasterverticalform.updatedeliveryheademailidmodel,
						"buHeadId":$scope.updatemasterverticalform.updatebuhempidmodel,
						"buh":$scope.updatemasterverticalform.updatebuhnamemodel,
						"buHeadEmail":$scope.updatemasterverticalform.updatebuhemailidmodel,
						"RiskManagersPersonId":$scope.updatemasterverticalform.updateRiskManagersempidmodel,
						"RiskManagers":$scope.updatemasterverticalform.updateRiskManagersnamemodel,
						"RiskManagersPersonEmail":$scope.updatemasterverticalform.updateRiskManagersemailidmodel,
						"isActive":isActive,
						"verticalStatusFlag":verticalStatusFlag
				}
				console.log(markers);
				var updateVertical = function(response) {
		   	        BootstrapDialog.show({
		   	        	title : 'Masters Vertical',
		   	        	type : BootstrapDialog.TYPE_PRIMARY,
		   	        	message : 'Updated SucessFully',
		   	        	closable : false,
		   	        	buttons : [{
		   	        		label : 'OK',
		   	        		action : function(dialogRef) {
		   	        			dialogRef.close();
		   	        			window.location = "MastersVertical";
		   	        		}
		   	        	}]
		   	        });
				}
				WebServiceFactory.updateVertical(markers).then(updateVertical);
		};
		
		
		$scope.getUserDetailsForUpdate = function (empId) {
			var count =0;
			var addToArray = true;
			var employeeLanId = null;
			var deliveryHeadId = $scope.updatemasterverticalform.updatedeliveryheadempidmodel;
			var buhId = $scope.updatemasterverticalform.updatebuhempidmodel;
			var RiskManagersId = $scope.updatemasterverticalform.updateRiskManagersempidmodel;
			var markers = {
					"employeeLanId" : empId
			};
			
			if(employeeLanId != "") {
			$http({
			    method: 'POST',
			    url: contextPath+"/RightPrice-DAS/getEmpAdIdDetails",
			    dataType: 'json',
			    data: angular.toJson(markers),  
	         headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    }).
			    then(function(data) {
			    	$scope.empDetails = data.data;
			    	console.log( data.data);
			    	console.log($scope.empDetails.length);
			    	 if(($scope.empDetails.length) < 1){
						  BootstrapDialog.show({
									title : 'Masters Vertical',
									type : BootstrapDialog.TYPE_DANGER,
									message : "Please enter valid Employee Lan ID",
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
										}
									} ]
								});
						  if(deliveryHeadId == empId || deliveryHeadId == undefined){
							  $scope.updatemasterverticalform.updatedeliveryheadempidmodel="";
							  $scope.updatemasterverticalform.updatedeliveryheadnamemodel="";
							  $scope.updatemasterverticalform.updatedeliveryheademailidmodel="";
						  }else if(buhId == empId || buhId == undefined){
							  $scope.updatemasterverticalform.updatebuhempidmodel="";
							  $scope.updatemasterverticalform.updatebuhnamemodel="";
							  $scope.updatemasterverticalform.updatebuhemailidmodel="";
						  }else if(RiskManagersId == empId || RiskManagersId == undefined){
							  $scope.updatemasterverticalform.updateRiskManagersempidmodel="";
							  $scope.updatemasterverticalform.updateRiskManagersnamemodel="";
							  $scope.updatemasterverticalform.updateRiskManagersemailidmodel="";
						  }
							 
					} 
			    	 else {
			    		// set the values on the field....
			    		 if(deliveryHeadId == empId) {
			    		 $scope.updatemasterverticalform.updatedeliveryheadnamemodel = $scope.empDetails[0].EMP_NAME;
			    		 $scope.updatemasterverticalform.updatedeliveryheademailidmodel = $scope.empDetails[0].EMAIL;
			    		 } else if(buhId == empId) {
			    			$scope.updatemasterverticalform.updatebuhnamemodel = $scope.empDetails[0].EMP_NAME;
			    			$scope.updatemasterverticalform.updatebuhemailidmodel = $scope.empDetails[0].EMAIL;
			    		 }else if(RiskManagersId == empId){
			    			$scope.updatemasterverticalform.updateRiskManagersnamemodel = $scope.empDetails[0].EMP_NAME;
				    		$scope.updatemasterverticalform.updateRiskManagersemailidmodel = $scope.empDetails[0].EMAIL;
			    		 }  
			    	 }
			    });
		}
		};
			
		$scope.getUserDetailsForAdd = function (empId) {
			var count =0;
			var addToArray = true;
			var employeeLanId = null;
			var deliveryHeadId = $scope.addmasterverticalform.adddeliveryheadempidmodel;
			var buhId = $scope.addmasterverticalform.addbuhempidmodel;
			var RiskManagersId = $scope.addmasterverticalform.addRiskManagersempidmodel;
			var markers = {
					"employeeLanId" : empId
			};
			
			if(employeeLanId != "") {
			$http({
			    method: 'POST',
			    url: contextPath+"/RightPrice-DAS/getEmpAdIdDetails",
			    dataType: 'json',
			    data: angular.toJson(markers),  
	         headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    }).
			    then(function(data) {
			    	$scope.empDetails = data.data;
			    	console.log( data.data);
			    	console.log($scope.empDetails.length);
			    	 if(($scope.empDetails.length) < 1){
						  BootstrapDialog.show({
									title : 'Masters Vertical',
									type : BootstrapDialog.TYPE_DANGER,
									message : "Please enter valid Employee Lan ID",
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
										}
									} ]
								});
						  if(deliveryHeadId == empId || deliveryHeadId == undefined){
							  $scope.addmasterverticalform.adddeliveryheadempidmodel="";
							  $scope.addmasterverticalform.adddeliveryheadnamemodel="";
							  $scope.addmasterverticalform.adddeliveryheademailidmodel="";
						  }else if(buhId == empId || buhId == undefined){
							  $scope.addmasterverticalform.addbuhempidmodel="";
							  $scope.addmasterverticalform.addbuhnamemodel="";
							  $scope.addmasterverticalform.addbuhemailidmodel="";
						  }else if(RiskManagersId == empId || RiskManagersId == undefined){
							  $scope.addmasterverticalform.addRiskManagersempidmodel="";
							  $scope.addmasterverticalform.addRiskManagersnamemodel="";
							  $scope.addmasterverticalform.addRiskManagersemailidmodel="";
						  }
					} 
			    	 else {
			    		 if(deliveryHeadId == empId) {
			    		 $scope.addmasterverticalform.adddeliveryheadnamemodel = $scope.empDetails[0].EMP_NAME;
			    		 $scope.addmasterverticalform.adddeliveryheademailidmodel = $scope.empDetails[0].EMAIL;
			    		 }else if(buhId == empId) {
			    			$scope.addmasterverticalform.addbuhnamemodel = $scope.empDetails[0].EMP_NAME;
			    			$scope.addmasterverticalform.addbuhemailidmodel = $scope.empDetails[0].EMAIL;
			    		 }else if(RiskManagersId == empId){
			    			$scope.addmasterverticalform.addRiskManagersnamemodel = $scope.empDetails[0].EMP_NAME;
				    		$scope.addmasterverticalform.addRiskManagersemailidmodel = $scope.empDetails[0].EMAIL;
			    		 }  
			    	 }
			    });
		}
		};
		/*$scope.compaireVertical = function(vertical){
			var verticalName = vertical.verticalName;
			var verticalLength = $scope.vertical.length;
			for(var i=0;i<verticalLength;i++) {
				if($scope.vertical[i].verticalName == verticalName) {
					 BootstrapDialog.show({
							title : 'Masters Vertical',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Vertical already exist. Please Update the vertical",
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								}
							} ]
						});
					 $scope.addmasterverticalform.addverticalmodel=null;
				}
			}
			
		};*/
		
			   
		}]);