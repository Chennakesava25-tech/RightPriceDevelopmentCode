//	var app = angular.module('RightPriceApp', ['ngMessages']);
		app.controller("AccessControlController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','WebAPIService', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,WebAPIService,$index){
			var contextPath = "/RightPrice-DAS";
			
			   	$scope.customes=[];
				$scope.customer=[];
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
				$scope.customes=[];
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
				    		 $scope.rpAccessControlForm.verticalnamemodel = $scope.empDetails[0].VERTICAL_GROUP_DESCR;
				    		 $scope.rpAccessControlForm.employeenamemodel = $scope.empDetails[0].EMP_NAME;
				    		 for(var i =1; i<$scope.empDetails.length;i++){
				    			 if($scope.empDetails[i].requesterFlag == 0){
				    				 $scope.customes.push($scope.empDetails[i]);
				    			 }
				    		 }
				    		 for(var i =1; i<$scope.empDetails.length;i++){
				    			 if($scope.empDetails[i].requesterFlag == 1){
				    				 $scope.selectedItemList.push($scope.empDetails[i]);
				    			 }
				    		 }
				    		 for(var i=0; i<$scope.customes.length;i++){
				    			 for(var j=0; j<$scope.selectedItemList.length; j++){
				    				 if($scope.selectedItemList[j].customer.customerName ==$scope.customes[i].customer.customerName){
				    					 $scope.customes.splice(i,1);
				    				 }
				    			 }
				    		 }
				    		 console.log("Customer Array............");
				    		 console.log($scope.customes);
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
			
			$scope.btnLeft = function(){
				WebAPIService.btnLeft($scope);
			};
			
			$scope.remove = function(id){
				WebAPIService.remove(id,$scope);
		    };
		    
		    $scope.addRPAccessControlData = function(){ 	
		    	if($scope.empDetails[0].USERID==undefined)
		    	{
		    		if($scope.selectedItemList.length != 0){
				    	angular.forEach($scope.selectedItemList, function (value, key) {
				    		$scope.accessControlData.push ({
				    				"lanId":$scope.rpAccessControlForm.empidmodel,
				    				"customerVerticalMapId":$scope.selectedItemList[key].customerVerticalMapId,
				    				"customerId":$scope.selectedItemList[key].customerId,
				    				"customerName":$scope.selectedItemList[key].customer.customerName
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
			    				"lanId":$scope.empDetails[0].USERID,
			    				"customerVerticalMapId":$scope.selectedItemList[key].customerVerticalMapId,
			    				"verticalId":$scope.empDetails[0].VERTICAL_GROUP_ID,
			    				"verticalName":$scope.rpAccessControlForm.verticalnamemodel,
			    				"customerId":$scope.selectedItemList[key].customerId,
			    				"customerName":$scope.selectedItemList[key].customer.customerName,
			    				"employeeName":$scope.rpAccessControlForm.employeenamemodel
			    		});
			    	});
		    	}
		    }
		    	else{
		    		console.log("Inside else of addRPAccessControlData ............");
		    		$scope.accessControlData.push ({
	    				"lanId":$scope.rpAccessControlForm.empidmodel,
	    				"customerId":0	
	    		    });
		    		console.log("Inside else of addRPAccessControlData ............ "+$scope.accessControlData.lenght);
		    	}
		    		var markers={
							"rpAccessControls":$scope.accessControlData,
					};
		    		console.log("Ankit here:::::::::::::::::::::::::::::::::::")
		    		console.log(markers);
		    	var addRPAccessControlData = function(response) {
		    		if(response.status == 200){		    			
		    			BootstrapDialog.show({
		    				title : 'Right Price Access Control',
		    				type : BootstrapDialog.TYPE_PRIMARY,
		    				message : 'Saved SucessFully',
		    				closable : false,
		    				buttons : [{
		    					label : 'OK',
		    					action : function(dialogRef) {
		    						dialogRef.close();
		    						window.location = "RPAccessControl";
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
		    						window.location = "RPAccessControl";
		    					}
		    				}]
		    			});
		    		}
				}
				WebServiceFactory.addRPAccessControlData(markers).then(addRPAccessControlData);
		    };
		    
		    $scope.clickCancle = function(rpAccessControlForm){
		    	$scope.customes=[];
				$scope.selectedItemList = [];
		    	$scope.rpAccessControlForm.empidmodel="";
		    	$scope.rpAccessControlForm.verticalnamemodel="";
				$scope.rpAccessControlForm.employeenamemodel="";
		    }
		    
		}]);