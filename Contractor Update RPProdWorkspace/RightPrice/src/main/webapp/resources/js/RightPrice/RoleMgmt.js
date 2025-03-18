//	var app = angular.module('RightPriceApp', ['ngMessages']);
		app.controller("RoleMgmtController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','WebAPIService', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,WebAPIService,$index){
			var contextPath = "/RightPrice-DAS";
			
			   	$scope.roles=[];
				$scope.role=[];
				
			   	$scope.empDetails=[];
			   	$scope.selectedAvailItems = [];
				$scope.selectedSelectedItemList = [];
				$scope.selectedItemList = [];
				$scope.availableItemList = [];
				$scope.accessControlData = [];
				$scope.saveDisable = true;
				
				
				
			$scope.getUserDetails = function (empId) {
				var count =0;
				var addToArray = true;
				var employeeLanId = null;
				var lanId = $scope.rpAccessControlForm.empidmodel;
				$scope.roles=[];
				
				$scope.selectedItemList = [];
				$scope.rpAccessControlForm.verticalnamemodel="";
				$scope.rpAccessControlForm.employeenamemodel="";
				$scope.saveDisable = false;
				var markers = {
						"employeeLanId" : empId
				};
				
				if(employeeLanId != "") {
				$http({
				    method: 'POST',
				    url: contextPath+"/RightPrice-DAS/getEmpRoleDetails",
				    dataType: 'json',
				    data: angular.toJson(markers),  
		         headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				    }).
				    then(function(data) {
				    	
				    	$scope.empDetails = data.data;
				    	
				
				    	console.log( data.data);
				    	console.log($scope.empDetails.length);
				    	function findUnique(arr, predicate) {
				    		var found = {};
				    		arr.forEach(d => {
				    		found[predicate(d)] = d;
				    		});
				    		return Object.keys(found).map(key => found[key]);
				    		}
				    	
				    	var result = findUnique($scope.empDetails, d => d.mstroleid);
				    	console.log(result);
				    	$scope.empDetails=result;
				    	//console.log("roles"+$scope.empDetails);
				    	 if(($scope.empDetails.length) < 1){
				    		 $scope.saveDisable = true;
							  BootstrapDialog.show({
										title : 'RP - Access Control',
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
							  if(lanId != empId || lanId == undefined){
								  $scope.rpAccessControlForm.verticalnamemodel="";
							  }
						} 
				    	 else {
				    		 if(lanId == empId) {
				    			 console.log($scope.empDetails);
				    			 for(var i =0; i<$scope.empDetails.length;i++){
				    				
					    				 
				    		 $scope.rpAccessControlForm.verticalnamemodel = $scope.empDetails[i].VERTICAL_GROUP_DESCR;
				    		 
				    		 $scope.rpAccessControlForm.employeenamemodel = $scope.empDetails[i].EMP_NAME;
				    		 
				    				 
				    				 }
				    		 for(var i =0; i<$scope.empDetails.length;i++){
				    			 if($scope.empDetails[i].requesterFlag == 0){
				    				 
				    				 $scope.roles.push($scope.empDetails[i]);
				    			 }
				    		 }
				    		 for(var i =0; i<$scope.empDetails.length;i++){
				    			 if($scope.empDetails[i].requesterFlag == 1){
				    				 $scope.selectedItemList.push($scope.empDetails[i]);
				    			 }
				    		 }
				    		 for(var i=0; i<$scope.roles.length;i++){
				    			 for(var j=0; j<$scope.selectedItemList.length; j++){
				    				 if($scope.selectedItemList[j].role.rolename ==$scope.roles[i].rolename){
				    					 $scope.roles.splice(i,1);
				    				 }
				    			 }
				    		 }
				    		 console.log("role Array............");
				    		 console.log($scope.roles);
				    		 console.log("selected items:::::::::::::::::::::::::::");
				    		 console.log($scope.selectedItemList);
				    		 }
				    	 }
				    });
			}
			};
			
			$scope.btnRight = function(){
				
				WebAPIService.btnRight($scope);
				
			};
			
			$scope.disableButton = function() {
				$scope.isDisabled = true;
				}
			
			$scope.btnLeft = function(){
				WebAPIService.btnLeft($scope);
			};
			
			$scope.remove = function(id){
				WebAPIService.remove(id,$scope);
		    };
		    
		    $scope.addRProleData = function(){ 	
		    	if($scope.empDetails[0].USERID==undefined)
		    	{
		    		if($scope.selectedItemList.length != 0){
				    	angular.forEach($scope.selectedItemList, function (value, key) {
				    		$scope.accessControlData.push ({
				    				"roleassignmentsubcat":$scope.rpAccessControlForm.empidmodel,
				    				
				    				"mstroleid":$scope.selectedItemList[key].mstroleid,
				    				"rolename":$scope.selectedItemList[key].role.rolename
				    		});
				    	});
			    	}
		    	}
		    	else if($scope.empDetails[0].USERID!=undefined)
		    	{
		    	if($scope.selectedItemList.length != 0){
			    	angular.forEach($scope.selectedItemList, function (value, key) {
			    		$scope.accessControlData.push ({
//			    				"lanId":$scope.rpAccessControlForm.empidmodel,
			    				"roleassignmentsubcat":$scope.empDetails[0].USERID,
			    				
			    				
			    				"mstroleid":$scope.selectedItemList[key].mstroleid,
			    				"rolename":$scope.selectedItemList[key].role.rolename,
			    				"employeeName":$scope.rpAccessControlForm.employeenamemodel
			    		});
			    	});
		    	}
		    }
		    	else{
		    		console.log("Inside else of addRProleData ............");
		    		$scope.accessControlData.push ({
	    				"roleassignmentsubcat":$scope.rpAccessControlForm.empidmodel,
	    				"mstroleid":0	
	    		    });
		    		console.log("Inside else of addRPAccessControlData ............ "+$scope.accessControlData.lenght);
		    	}
		    		var markers={
							"rpRoleAccess":$scope.accessControlData,
						
					};
		    		console.log("Ankit here:::::::::::::::::::::::::::::::::::")
		    		console.log(markers);
		    	var addRProleData = function(response) {
		    		if(response.status == 200){		    			
		    			BootstrapDialog.show({
		    				title : 'Right Price Role Access ',
		    				type : BootstrapDialog.TYPE_PRIMARY,
		    				message : 'Saved SucessFully',
		    				closable : false,
		    				buttons : [{
		    					label : 'OK',
		    					action : function(dialogRef) {
		    						dialogRef.close();
		    						window.location = "RoleMngmt";
		    					}
		    				}]
		    			});
		    		}else{
		    			BootstrapDialog.show({
		    				title : 'Right Price Access Control',
		    				type : BootstrapDialog.TYPE_DANGER,
		    				message : 'Currently We are facing technical issues, please try again later.',
		    				closable : false,
		    				buttons : [{
		    					label : 'OK',
		    					action : function(dialogRef) {
		    						dialogRef.close();
		    						window.location = "RoleMngmt";
		    					}
		    				}]
		    			});
		    		}
				}
				WebServiceFactory.addRProleData(markers).then(addRProleData);
		    };
		    
		    $scope.clickCancle = function(rpAccessControlForm){
		    	$scope.roles=[];
				$scope.selectedItemList = [];
		    	$scope.rpAccessControlForm.empidmodel="";
		    	$scope.rpAccessControlForm.verticalnamemodel="";
				$scope.rpAccessControlForm.employeenamemodel="";
		    }
		    
		}]);