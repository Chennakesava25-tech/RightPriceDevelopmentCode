//var app = angular.module('RightPriceApp', []);
		app.controller("MasterDeliveryTeamController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index){
			var contextPath = $window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/")); 
			$scope.ViewHidden = true;
			 $scope.TeamMembersHidden = true;
			 $scope.DealocateTeamMembersHidden = true;
			 $scope.RiskManagersMembersHidden = true;
			 $scope.DeallocateQMHidden = true;
			 $scope.downloadBtn = true;
			 $scope.onAdd = false;
			 $scope.onRiskManagersAdd = false;
			 $scope.onUpdateSearch = false;
			 $scope.onRiskManagersDeallocate = false;
			 
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            };
             $scope.ShowHideTeamMembers = function () {
                $scope.TeamMembersHidden = $scope.TeamMembersHidden ? false : true;
            };
            $scope.ShowHideDeallocateQM = function () {
                $scope.DeallocateQMHidden = $scope.DeallocateQMHidden ? false : true;
            };
             $scope.ShowHideRiskManagersMembers = function () {
                $scope.RiskManagersMembersHidden = $scope.RiskManagersMembersHidden ? false : true;
            };
            $scope.ShowHideDealocateTeamMembers = function () {
                $scope.DealocateTeamMembersHidden = $scope.DealocateTeamMembersHidden ? false : true;
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
			
			var getVertical = function(response){
				$scope.vertical = response.data;
				console.log("get verticcal");
				console.log(response.data);
			};			
			WebServiceFactory.getVertical().then(getVertical);
			
			$scope.getCustomers = function(verticalId) 
			{
				var getCustomers = function(response)
				{
					console.log("get Customers");
					console.log(response.data);
					$scope.customers = response.data;
				};
			WebServiceFactory.getCustomers(verticalId).then(getCustomers);
			};
			
						
			$scope.onSearch= false;	
			$scope.searchData = function(deliveryTeamForm){
				$scope.onSearch= true;
				
				if(deliveryTeamForm.$valid){
					var isViewSearch = true;
					$scope.onSearch= false;	
					$scope.getVerticalReportData($scope.deliveryTeamForm.ddlViewVerticalModel.verticalId,isViewSearch);
				}
			};
			
			
			$scope.getVerticalMemberExcelData = function() {
				var verticalId = $scope.deliveryTeamForm.ddlViewVerticalModel.verticalId;
				
				window.location= contextPath+"/RightPrice-DAS/downloadViewVerticalExcel/"+verticalId
			};
			
			
			$scope.getVerticalReportData = function (verticalId,isViewSearch) {
				$scope.downloadBtn = false;
				
				var markers = {
					"verticalId" : verticalId 
				};
				$http({
				    method: 'POST',
				    url: contextPath+'/RightPrice-DAS/getVerticalData',
				    dataType: 'json',
				    data: angular.toJson(markers),  
		         headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				    }).
				    then(function(data) {
				    	if(isViewSearch)
			    		{
				    		$scope.viewVerticalData = data.data;
					    	console.log( data.data);
					    	$scope.empdetails = [];
					    	console.log($scope.viewVerticalData.length);
					    	var arrayLength = $scope.viewVerticalData.length;
					    	console.log("Length of the viewVerticalArray is........... " +arrayLength);
					    	if(($scope.viewVerticalData.length) < 1)
					    	{
				    			BootstrapDialog.show({ 
							  		title : 'View Vertical Team',
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
				    	else
			    		{
				    		$scope.viewUpdateVerticalData = data.data;
					    	console.log( data.data);
					    	$scope.empdetails = [];
					    	console.log($scope.viewUpdateVerticalData.length);
					    	var arrayLength = $scope.viewUpdateVerticalData.length;
					    	console.log("Length of the viewDeallocateVerticalArray is........... " +arrayLength);
					    	if(($scope.viewUpdateVerticalData.length) < 1)
					    	{
				    			BootstrapDialog.show({ 
							  		title : 'View Vertical Team',
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
				    	
				    });
			};
			
			$scope.selectedEmp = [];
			$scope.checkedItems = [];
			$scope.deallocatedItems = [];
			$scope.deallocatedQMItems = [];
			var splicedArray = [];
			$scope.verticalMarker=[];
			$scope.selectedRiskManagersEmp = [];
			$scope.RiskManagersMarker = [];
			$scope.RiskManagersCheckedItems = [];
			
			$scope.addEmpDetails = function (EMP_ID,EMP_NAME,EMP_DESC,USERID) {
				$scope.selectedEmp.push ({
				"EMP_ID" :EMP_ID,
				"EMP_NAME" : EMP_NAME,
				"EMP_DESC" : EMP_DESC,
				"USERID" : USERID
				});
			};
			
			$scope.addCheckedItemList = function (index,EMP_ID,EMP_NAME,EMP_DESC,USERID) {
				$scope.checkedItems.push ({
					"index" : index,
					"EMP_ID" :EMP_ID,
					"EMP_NAME" : EMP_NAME,
					"EMP_DESC" : EMP_DESC,
					"USERID" : USERID
					});
				console.log("Checked Item List is.......... ");
				console.log($scope.checkedItems);
			};
			
			$scope.deallocateCheckedItemList = function (index,EMP_ID,EMP_NAME,EMP_DESC,USERID) {
				$scope.deallocatedItems.push ({
					"index" : index,
					"EMP_ID" :EMP_ID,
					"EMP_NAME" : EMP_NAME,
					"EMP_DESC" : EMP_DESC,
					"USERID" : USERID
					});
				console.log("deallocatedItems Item List is.......... ");
				console.log($scope.deallocatedItems);
			};
			
			$scope.deallocateQMCheckedItemList = function (index,EMP_ID,EMP_NAME,EMP_DESC,USERID) {
				$scope.deallocatedQMItems.push ({
					"index" : index,
					"EMP_ID" :EMP_ID,
					"EMP_NAME" : EMP_NAME,
					"EMP_DESC" : EMP_DESC,
					"USERID" : USERID
					});
				console.log("deallocatedQAItems Item List is.......... ");
				console.log($scope.deallocatedQMItems);
			};
			
			$scope.addRiskManagersCheckedList = function (EMP_ID,EMP_NAME,EMP_DESC,USERID) {
				$scope.RiskManagersCheckedItems.push ({
					"EMP_ID" :EMP_ID,
					"EMP_NAME" : EMP_NAME,
					"EMP_DESC" : EMP_DESC,
					"USERID" : USERID
					});
				console.log("Checked Item List is.......... ");
				console.log($scope.RiskManagersCheckedItems);
			};
			
			// Function to add the selected employee for RiskManagers 
			$scope.addRiskManagersEmpDetails = function (EMP_ID,EMP_NAME,EMP_DESC,USERID) {
				$scope.selectedRiskManagersEmp.push ({
					"EMP_ID" :EMP_ID,
					"EMP_NAME" : EMP_NAME,
					"EMP_DESC" : EMP_DESC,
					"USERID" : USERID
					});
			};
			
			$scope.getUserDetails = function (addTeamDetailForm) {
				$scope.onAdd = true;
				if(addTeamDetailForm.$valid)
				{
					$scope.onAdd = false;
					
					var count =0;
					var addToArray = true;
					if($scope.addTeamDetailForm.txtTeamMembersEmpIdModel != null) 
					{
						var employeeId = $scope.addTeamDetailForm.txtTeamMembersEmpIdModel;
					}
					console.log("the Employee Id is.......... " + employeeId);
					
					var markers = {
							"employeeId" : employeeId
					};
					
					if(employeeId != "") 
					{
						$http(
						{
						    method: 'POST',
						    url: contextPath+'/RightPrice-DAS/getEmpDetails',
						    dataType: 'json',
						    data: angular.toJson(markers),  
				         headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					    }).
					    then(function(data) 
			    		{
					    	$scope.empDetails = data.data;
					    	console.log( data.data);
					    	console.log($scope.empDetails.length);
					    	var message = data.data;
					    	if(($scope.empDetails.length) < 1)
					    	{
								  BootstrapDialog.show(
								  {
									title : 'Master Delivery Team',
									type : BootstrapDialog.TYPE_DANGER,
									message : "Please enter valid Employee ID",
									closable : false,
									buttons : [{
										label : 'OK',
										action : function(dialogRef) 
										{
											dialogRef.close();
										}
									}]
								  });
								  $scope.addTeamDetailForm.txtTeamMembersEmpIdModel = "";
							} 
				    		else if(data.status != 203)
					    	{
				    			var empArrayLength = $scope.selectedEmp.length;
					    		console.log("Selected EMP Details....... ");
					    		var empData = $scope.empDetails[0];
					    		console.log($scope.selectedEmp);
					    		var empDetailsLength = $scope.empDetails.length;
					    		if(empArrayLength <= 0) 
					    		{
					    			$scope.addEmpDetails(empData.EMP_ID,empData.EMP_NAME,empData.EMP_DESC,empData.USERID);
					    		}
					    		else if(empArrayLength > 0) 
					    		{
					    			console.log(empData.EMP_ID);
					    			for(i=0;i<empArrayLength;i++) 
					    			{
					    				if($scope.selectedEmp[i].EMP_ID === empData.EMP_ID) 
				    			 		{
					    					addToArray = false;
						    				console.log("The  value Exists.......... "+addToArray)
						    				BootstrapDialog.show(
				    						{
				    							title : 'Master Delivery Team',
												type : BootstrapDialog.TYPE_DANGER,
												message : "Employee already searched, please enter a different employee Id",
												closable : false,
												buttons : [ {
													label : 'OK',
													action : function(dialogRef) {
														dialogRef.close();
													}
												} ]
				    						 });
						    				 $scope.addTeamDetailForm.txtTeamMembersEmpIdModel = "";
				    			 		 }
					    			 }
						    		 if(addToArray)
						    		 {
							    		$scope.addEmpDetails(empData.EMP_ID,empData.EMP_NAME,empData.EMP_DESC,empData.USERID);
							    	 }
					    		 }
					    	 }
					    	 else 
					    	 {
					    		 var message = data.data;
					    		 BootstrapDialog.show(
			    				 {
									title : 'Master Delivery Team',
									type : BootstrapDialog.TYPE_DANGER,
									message : message,
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
										}
									} ]
								});
					    	 }
					    });
					}
					else 
					{
						BootstrapDialog.show({
							title : 'Master Delivery Team',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Please Enter Employee Id.",
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
			};
			
			$scope.getTeamMemberUserDetails = function (updateTeamDetailForm) {
				$scope.onUpdateSearch = true;
				if(updateTeamDetailForm.$valid)
				{
					var isViewSearch = false;
					$scope.onUpdateSearch = false;
					$scope.getVerticalReportData(updateTeamDetailForm.ddlUpdateTeamMembersVerticalModel,isViewSearch);
				}
			};
			
			$scope.getQMUserDetails = function (deallocateRiskManagersMemberForm) {
				$scope.onRiskManagersDeallocate = true;
				if(deallocateRiskManagersMemberForm.$valid)
				{					
					$scope.onRiskManagersDeallocate = false;
					$scope.getQMVerticalReportData(deallocateRiskManagersMemberForm.ddlDeallocateQMVerticalModel);
				}
			};
			
			$scope.getQMVerticalReportData = function (verticalId) 
			{				
				var markers = {
					"verticalId" : verticalId 
				};
				$http(
				{
				    method: 'POST',
				    url: contextPath+'/RightPrice-DAS/getQMVerticalData',
				    dataType: 'json',
				    data: angular.toJson(markers),  
				    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    }).
			    then(function(data) 
	    		{
			    	$scope.viewQMVerticalData = data.data;
			    	console.log( data.data);
			    	$scope.empdetails = [];
			    	console.log($scope.viewQMVerticalData.length);
			    	var arrayLength = $scope.viewQMVerticalData.length;
			    	console.log("Length of the viewQMVerticalData is........... " +arrayLength);
			    	if(($scope.viewQMVerticalData.length) < 1)
			    	{
			    		BootstrapDialog.show({ 
			    			title : 'View Vertical Team',
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
			    });
			};
			
			
			 $scope.addChkItem = function (emp,status,index) {
		    	   console.log("Emp Details......."+emp);
		    	   console.log("The Index of the Check Box is..... "+index);
		    	   console.log(status);
	                if (status==true) {
	                	$scope.addCheckedItemList(index,emp.EMP_ID,emp.EMP_NAME,emp.EMP_DESC,emp.USERID);
	                } else {
	                	for(var i =0; i<$scope.checkedItems.length; i++){
	                		if($scope.checkedItems[i].EMP_ID == emp.EMP_ID) {
	        					splicedArray = $scope.checkedItems.splice(i,1);
	                	}
	        		  }
	                }
	            };
	            
	            $scope.deallocateChkItem = function (emp,status,index) {
			    	   console.log("Deallocated Emp Details......."+emp);
			    	   console.log("The Index of the Check Box is..... "+index);
			    	   console.log(status);
		                if (status==true) {
		                	$scope.deallocateCheckedItemList(index,emp.EMP_ID,emp.EMP_NAME,emp.EMP_DESC,emp.USERID);
		                } else {
		                	for(var i =0; i<$scope.deallocatedItems.length; i++){
		                		if($scope.deallocatedItems[i].EMP_ID == emp.EMP_ID) {
		        					splicedArray = $scope.deallocatedItems.splice(i,1);
		                	}
		        		  }
		                }
		            };
	            
	            $scope.deallocateQMChkItem = function (emp,status,index) 
	            {
	            	console.log("Deallocated QM Emp Details......."+emp);
	            	console.log("The Index of the Check Box is..... "+index);
	            	console.log(status);
	            	if (status==true) 
	            	{
	            		$scope.deallocateQMCheckedItemList(index,emp.EMP_ID,emp.EMP_NAME,emp.EMP_DESC,emp.USERID);
	                } 
	            	else 
	                {
	            		for(var i =0; i<$scope.deallocatedQMItems.length; i++)
            			{
	            			if($scope.deallocatedQMItems[i].EMP_ID == emp.EMP_ID) 
	            			{
	            				splicedArray = $scope.deallocatedQMItems.splice(i,1);
		                	}
            			}
                	}
	            };
	            
	            // add the emp Details while checking the checkbox in the list.
	            $scope.addChkRiskManagersItem = function (emp,status,index) {
			    	   console.log("Emp Details......."+emp);
			    	   console.log("The Index of the Check Box is..... "+index);
			    	   console.log(status);
		                if (status==true) {
		                	$scope.addRiskManagersCheckedList(emp.EMP_ID,emp.EMP_NAME,emp.EMP_DESC,emp.USERID);
		                } else {
		                	for(var i =0; i<$scope.RiskManagersCheckedItems.length; i++){
		                		if($scope.RiskManagersCheckedItems[i].EMP_ID == emp.EMP_ID) {
		        					splicedArray = $scope.RiskManagersCheckedItems.splice(i,1);
		                	}
		        		  }
		                }
		            };
	            
			$scope.saveEmpDetails = function () {

				console.log("$scope.checkBoxItems");
				console.log($scope.checkedItems);
				console.log("Verticl Id........... ");
				console.log($scope.addTeamDetailForm.ddlTeamMembersVertical.verticalId);
				console.log($scope.addTeamDetailForm.ddlTeamMembersVertical.verticalName);
				var checkedItemLength = $scope.checkedItems.length;
				if(checkedItemLength != 0) {
				for(var i=0;i<$scope.checkedItems.length;i++)
				{
					$scope.verticalMarker.push({ 	
				        "customerId" : $scope.addTeamDetailForm.ddlCustomersModel,
				        "verticalId" : $scope.addTeamDetailForm.ddlTeamMembersVerticalModel,
						"employeeLanId":$scope.checkedItems[i].USERID,
						"roleType" : 2,
					});
				}	
		    	$http({
					 method: 'POST',
					 url: contextPath+"/RightPrice-DAS/saveVerticalMemebers",
					 dataType: 'json',
					 data: angular.toJson($scope.verticalMarker),  
		            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    	}).
		    	then(function(data) {
		    			console.log("Inserted Successfully");
		    			if(data.status == 200){
		    			var message = data.data;
		    	        BootstrapDialog.show({
		    	        	title : 'Master - Delivery Team',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Data saved SucessFully',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MasterDeliveryTeam";
		    	        		}
		    	        	}]
		    	        });
		    			} else {
		    				message = data.data;
		    				BootstrapDialog.show({
			    	        	title : 'Master - Delivery Team',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : message,
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			//window.location = "MasterDeliveryTeam";
			    	        		}
			    	        	}]
			    	        });
		    			}
				},function (data) {
					$scope.displayres = data;
				    $scope.answer = 'Posting data was unsuccessful.';
				});
		    	
				} else {
					
					BootstrapDialog.show({
	    	        	title : 'Master - Delivery Team',
	    	        	type : BootstrapDialog.TYPE_DANGER,
	    	        	message : 'No records selected. Please select atleast 1 record to Insert.',
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
			
			$scope.deallocateEmp = function () {

				console.log("Emp(s) selected for deallocation");
				console.log($scope.deallocatedItems);
				console.log("Verticl Id........... ");
				console.log($scope.updateTeamDetailForm.ddlUpdateTeamMembersVerticalModel);
				console.log($scope.updateTeamDetailForm.ddlUpdateTeamMembersVerticalModel.verticalName);
				var checkedItemLength = $scope.deallocatedItems.length;
				if(checkedItemLength != 0) {
				for(var i=0;i<$scope.deallocatedItems.length;i++)
				{
					$scope.verticalMarker.push({ 	
				        /*"customerId" : $scope.addTeamDetailForm.ddlCustomersModel,*/
				        "verticalId" : $scope.updateTeamDetailForm.ddlUpdateTeamMembersVerticalModel,
						"employeeLanId":$scope.deallocatedItems[i].USERID,
						"roleType" : 2,
					});
				}	
		    	$http({
					 method: 'POST',
					 url: contextPath+"/RightPrice-DAS/deallocateVerticalMemebers",
					 dataType: 'json',
					 data: angular.toJson($scope.verticalMarker),  
		            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    	}).
		    	then(function(data) {
		    			console.log("Inserted Successfully");
		    			if(data.status == 200){
		    			var message = data.data;
		    	        BootstrapDialog.show({
		    	        	title : 'Master - Delivery Team',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Data saved SucessFully',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MasterDeliveryTeam";
		    	        		}
		    	        	}]
		    	        });
		    			} else {
		    				message = data.data;
		    				BootstrapDialog.show({
			    	        	title : 'Master - Delivery Team',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : message,
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			//window.location = "MasterDeliveryTeam";
			    	        		}
			    	        	}]
			    	        });
		    			}
				},function (data) {
					$scope.displayres = data;
				    $scope.answer = 'Posting data was unsuccessful.';
				});
		    	
				} else {
					
					BootstrapDialog.show({
	    	        	title : 'Master - Delivery Team',
	    	        	type : BootstrapDialog.TYPE_DANGER,
	    	        	message : 'No records selected. Please select atleast 1 record to deallocate.',
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
			
			// ----

			$scope.deallocateQMEmp = function () 
			{
				console.log("QMEmp(s) selected for deallocation");
				console.log($scope.deallocatedQMItems);
				console.log("Verticl Id........... ");
				console.log($scope.deallocateRiskManagersMemberForm.ddlDeallocateQMVerticalModel);
				var checkedItemLength = $scope.deallocatedQMItems.length;
				if(checkedItemLength != 0) 
				{
					for(var i=0;i<$scope.deallocatedQMItems.length;i++)
					{
						$scope.verticalMarker.push(
						{ 	
							/*"customerId" : $scope.addTeamDetailForm.ddlCustomersModel,*/
							"verticalId" : $scope.deallocateRiskManagersMemberForm.ddlDeallocateQMVerticalModel,
							"employeeLanId":$scope.deallocatedQMItems[i].USERID							
						});
					}	
			    	$http({
						 method: 'POST',
						 url: contextPath+"/RightPrice-DAS/deallocateVerticalQM",
						 dataType: 'json',
						 data: angular.toJson($scope.verticalMarker),  
			            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    	}).
			    	then(function(data) 
	    			{
		    			console.log("Inserted Successfully");
		    			if(data.status == 200)
		    			{
		    				var message = data.data;
		    				BootstrapDialog.show({
		    					title : 'Master - Delivery Team',
		    					type : BootstrapDialog.TYPE_PRIMARY,
		    					message : 'Data saved SucessFully',
		    					closable : false,
		    					buttons : [{
		    						label : 'OK',
		    						action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MasterDeliveryTeam";
		    						}
		    					}]
		    				});
		    			} 
		    			else 
		    			{
		    				message = data.data;
		    				BootstrapDialog.show({
			    	        	title : 'Master - Delivery Team',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : message,
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			//window.location = "MasterDeliveryTeam";
			    	        		}
			    	        	}]
			    	        });
		    			}
	    			},function (data) 
	    			{
						$scope.displayres = data;
					    $scope.answer = 'Posting data was unsuccessful.';
					});
	    	
				}
				else 
				{
					BootstrapDialog.show({
						title : 'Master - Delivery Team',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'No records selected. Please select atleast 1 record to deallocate.',
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
			
		
			
			// ----
			
			
			// get the RiskManagers User Details 
			$scope.getRiskManagersUserDetails = function (addRiskManagersMemberForm) {
				$scope.onRiskManagersAdd = true;
				if(addRiskManagersMemberForm.$valid)
				{
					$scope.onRiskManagersAdd = false;
					var count =0;
					var addToArray = true;
					var employeeId = $scope.addRiskManagersMemberForm.txtRiskManagersMembersEmpIdModel;
					var verticalId = $scope.addRiskManagersMemberForm.ddlRiskManagersMembersVerticalModel;
					console.log("the Employee Id is.......... " + employeeId);
					console.log("The Vertical Id is................. " +verticalId);
					var markers = {
							"employeeId" : employeeId,
							"verticalId" : verticalId
					};
					
					if(employeeId != "") 
					{
						$http({
							method: 'POST',
							url: contextPath+'/RightPrice-DAS/getRiskManagersEmpDetails',
							dataType: 'json',
							data: angular.toJson(markers),  
							headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					    }).
					    then(function(data) 
			    		{
					    	$scope.RiskManagersEmpDetails = data.data;
					    	console.log( data.data);
					    	console.log($scope.RiskManagersEmpDetails.length);
					    	var message = data.data;
					    	if(($scope.RiskManagersEmpDetails.length) < 1)
					    	{
					    		BootstrapDialog.show({
									title : 'Master Delivery Team',
									type : BootstrapDialog.TYPE_DANGER,
									message : "Please enter valid Employee ID",
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
										}
									} ]
								});
					    		$scope.addRiskManagersMemberForm.txtRiskManagersMembersEmpIdModel = "";
							} 
					    	else if(data.status != 203)
					    	{
					    		var empArrayLength = $scope.selectedRiskManagersEmp.length;
					    		console.log("Selected EMP Details....... ");
					    		var empData = $scope.RiskManagersEmpDetails[0];
					    		console.log($scope.selectedRiskManagersEmp);
					    		var empDetailsLength = $scope.RiskManagersEmpDetails.length;
					    		if(empArrayLength <= 0) 
					    		{
					    			$scope.addRiskManagersEmpDetails(empData.EMP_ID,empData.EMP_NAME,empData.EMP_DESC,empData.USERID);
					    		}
					    		else if(empArrayLength > 0) 
					    		{
					    			console.log(empData.EMP_ID);
					    			for(i=0;i<empArrayLength;i++) 
					    			{
					    				if($scope.selectedRiskManagersEmp[i].EMP_ID === empData.EMP_ID) 
					    				{
					    					addToArray = false;
					    					console.log("The  value Exists.......... "+addToArray)
					    					BootstrapDialog.show({
					    						title : 'Master Delivery Team',
					    						type : BootstrapDialog.TYPE_DANGER,
					    						message : "Employee already searched, please enter a different employee Id",
					    						closable : false,
					    						buttons : [ {
					    							label : 'OK',
					    							action : function(dialogRef) {
					    								dialogRef.close();
														}
													} ]
												});
						    				 $scope.addRiskManagersMemberForm.txtRiskManagersMembersEmpIdModel = "";
					    			 	}
					    			}
					    			if(addToArray)
					    			{
					    				$scope.addRiskManagersEmpDetails(empData.EMP_ID,empData.EMP_NAME,empData.EMP_DESC,empData.USERID);
					    			}
					    		}
					    	} 
				    		else 
				    		{
				    			BootstrapDialog.show({
				    				title : 'Master Delivery Team',
				    				type : BootstrapDialog.TYPE_DANGER,
				    				message : message,
				    				closable : false,
				    				buttons : [ {
				    					label : 'OK',
				    					action : function(dialogRef) {
				    						dialogRef.close();
										}
									} ]
				    		 	});
				    	 	}
					    	 	
					    });
					}
					else 
					{
						BootstrapDialog.show({
							title : 'Master Delivery Team',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Please Enter Employee Id.",
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
			};
			
		     
			$scope.saveRiskManagersEmpDetails = function (emp) {

				console.log("$scope.checkBoxItems");
				console.log($scope.RiskManagersCheckedItems);
				console.log("Verticl Id........... ");
				console.log($scope.addRiskManagersMemberForm.ddlRiskManagersMembersVerticalModel);
				//console.log($scope.addRiskManagersMemberForm.txtRiskManagersMembersEmpIdModel.verticalName);
				
				var checkedRiskManagersItemLength = $scope.RiskManagersCheckedItems.length;
				if(checkedRiskManagersItemLength != 0) {
				for(var i=0;i< $scope.RiskManagersCheckedItems.length;i++)
				{
					$scope.RiskManagersMarker.push({ 	
				        "customerId" : $scope.addRiskManagersMemberForm.ddlRiskManagersCustomersModel,    
				        "verticalId" : $scope.addRiskManagersMemberForm.ddlRiskManagersMembersVerticalModel,
						"employeeLanId":$scope.RiskManagersCheckedItems[i].USERID,
						"roleType" : 2,
					});
				}	
		    	$http({
					 method: 'POST',
					 url: contextPath+"/RightPrice-DAS/saveRiskManagersVerticalMembers",
					 dataType: 'json',
					 data: angular.toJson($scope.RiskManagersMarker),  
		            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    	}).
		    	then(function(data) {
		    			console.log("Inserted Successfully");
		    			if(data.status == 200){
		    			var message = data.data;
		    	        BootstrapDialog.show({
		    	        	title : 'Master - Delivery Team',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Data saved SucessFully',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MasterDeliveryTeam";
		    	        		}
		    	        	}]
		    	        });
		    			} else {
		    				message = data.data;
		    				BootstrapDialog.show({
			    	        	title : 'Master - Delivery Team',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : message,
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			//window.location = "MasterDeliveryTeam";
			    	        		}
			    	        	}]
			    	        });
		    			}
				},function (data) {
					$scope.displayres = data;
				    $scope.answer = 'Posting data was unsuccessful.';
				});
		    	
				} else {
					
					BootstrapDialog.show({
	    	        	title : 'Master - Delivery Team',
	    	        	type : BootstrapDialog.TYPE_DANGER,
	    	        	message : 'No records selected. Please select atleast 1 record to Insert.',
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
			
			$scope.clearForm = function (form) {
				$scope.addTeamDetailForm.ddlTeamMembersVertical = "";
				$scope.addTeamDetailForm.txtTeamMembersEmpIdModel = "";
				$scope.addTeamDetailForm.tblTeamMembers = [];
				$scope.selectedEmp = []; 
				$scope.checkedItems = [];
				};
				
			$scope.clearDeallocateTMForm = function(updateTeamDetailForm)
			{				
				$scope.updateTeamDetailForm.ddlUpdateTeamMembersVerticalModel = null;
				$scope.viewUpdateVerticalData = [];
				$scope.selectedEmp = []; 
				$scope.checkedItems = [];
			}
			
			$scope.clearDeallocateQMForm = function(deallocateRiskManagersMemberForm)
			{				
				$scope.deallocateRiskManagersMemberForm.ddlDeallocateQMVerticalModel = null;
				$scope.viewQMVerticalData = [];
				$scope.selectedEmp = []; 
				$scope.checkedItems = [];
			}
				
		}]);