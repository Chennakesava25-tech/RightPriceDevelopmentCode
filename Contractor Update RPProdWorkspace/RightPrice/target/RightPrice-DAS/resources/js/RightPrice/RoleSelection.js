//var app = angular.module("RightPriceApp", ['ngStorage']);
		app.controller("RoleSelectionController", ['$scope','$http','$filter','$location','$anchorScroll','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$http,$filter,$location,$anchorScroll,$window,WebServiceFactory,$localStorage, $sessionStorage,$index) {
			var contextPath = "/RightPrice-DAS";
			$scope.roleSelection={};
			$scope.persistantDataArray = [];
			$scope.isContractorRole = false;
			$scope.isModified = false;
			$scope.isManualRC = false;
			$scope.isdataLoaded = false;
			$scope.isAutomatic = false;
			$scope.isManual = false;
			$scope.isHybrid = false;
			$scope.isSaveDisabled=false;
			$scope.manualsidebar=false;
			$scope.autosidebar=false;
			$scope.hybridsidebar=false;
			sessionStorage.dataSaved = null;
			//$scope.currencyName = [];
			$scope.index=0;
			var userType = sessionStorage.getItem('userType');
			 console.log("The USer Value from the Session is........ "+ userType);
			 
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
			
			 $scope.exportToExcel=function(tableId){ // ex: '#my-table'
			    	//alert('Clicked');
			        var exportHref=WebServiceFactory.tableToExcel(tableId,'RateCard_RoleSelection');
			    
			    /*var link = document.createElement('a');
			    if (typeof link.download === 'string') {
			      link.href = exportHref;
			      link.download = "RateCard.xls";

			      //Firefox requires the link to be in the body
			      document.body.appendChild(link);
			      
			      //simulate click
			      link.click();

			      //remove the link when done
			      document.body.removeChild(link);
			    } else {
			      window.open(uri);
			    }*/
			    }
			 
			 
			$scope.Next = function(roleSelectionForm)
		    {   
				
				if($scope.isModified)
				{
					console.log("Selected Roles Array");
					console.log($scope.selectededRolesArr);
					BootstrapDialog.show
					({
						title : 'Rate Card - Role Selection',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'Do you want to save your changes? Click Yes to go back and save, <br>No to discard your changes',
						buttons : [{
							label : 'Yes',cssClass : 'btn-primary',
							action : function(dialogItself) {
								dialogItself.close();
							}
						},
						{
							label : 'No',
							cssClass : 'btn-primary',
							action : function(dialogItself) {
								dialogItself.close();
								$sessionStorage.roleSelectionArray = $scope.persistantDataArray;
								for(var i =0; i<$scope.persistantDataArray.length; i++){
									if($scope.persistantDataArray[i].syntelRoleName == "Contractor"){
										$scope.isContractorRole = true;
									} 
								}	
								
								$scope.isManualRCType = $sessionStorage.isManualRCType;
								if($scope.isManualRCType == "A") {
									$scope.isAutomatic = true;
								} else if($scope.isManualRCType == "M") {
									$scope.isManual = true;
								} else if($scope.isManualRCType == "H") {
									$scope.isHybrid = true;
								}
								
								if($scope.isContractorRole == true && $scope.isManual == false) {
									window.location='RateCardContractorRole';
								} else if($scope.isAutomatic == true || $scope.isHybrid == true){
									window.location ='RateCardCreationRoleUtilizationAndRates';
								} else if($scope.isManual == true ) {
									window.location ='RateCardCreationGMRateDetails';
								} 
							}
						}]
					});
				} else {
					$sessionStorage.roleSelectionArray = $scope.selectededRolesArr;
					for(var i =0; i<$scope.selectededRolesArr.length; i++){
						if($scope.selectededRolesArr[i].syntelRoleName == "Contractor"){
							$scope.isContractorRole = true;
						} 
					}	
					
					$scope.isManualRCType = $sessionStorage.isManualRCType;
					if($scope.isManualRCType == "A") {
						$scope.isAutomatic = true;
					} else if($scope.isManualRCType == "M") {
						$scope.isManual = true;
					} else if($scope.isManualRCType == "H") {
						$scope.isHybrid = true;
					}
					
					if($scope.isContractorRole == true && $scope.isManual == false) {
						window.location='RateCardContractorRole';
					} else if($scope.isAutomatic == true || $scope.isHybrid == true){
						window.location ='RateCardCreationRoleUtilizationAndRates';
					} else if($scope.isManual == true ) {
						window.location ='RateCardCreationGMRateDetails';
					} 
				}
		    }; 
		    $scope.Prev = function()
		    {   
		        window.location='RateCardCreationDetails';
		    }; 
		    $scope.onSave = false;
		    $scope.onSaveClick = function(roleSelectionForm) {
		    	$scope.onSave = true;
				if(roleSelectionForm.$valid){
					$scope.onSave = false;
		    	$scope.saveOrUpdateRole();
				}
			};
		    
		    $scope.cancel = function()
		    {
		    	$scope.firstTable=[];	
		    	$scope.searchRoleDetailsResult=[];
		    	$scope.rateCardModel = null;
		    	$scope.searchModel = "";
		    }
		    
		    $scope.isSaveDisabled = false;
		    if($localStorage.tempAction == 3){
		    	$scope.isSaveDisabled = true;
		    }
		    else{
		    	$scope.isSaveDisabled = false;
		    }
		    
		    $scope.searchedRolesArr=[];
		    $scope.selectededRolesArr=[];
		    $scope.searchRoleDetailsResult=[];
		  //0.get rate card data from previous page on page load 
			
			if($localStorage.rcId != 0 && $localStorage.rcId != undefined) {
				var getRateCardInfo = function(response) {
			  		$scope.rateCardInfo = response.data;
			  		console.log("Session Data.............");
			  		console.log($scope.rateCardInfo);
			  		var rcStartDate  = $scope.rateCardInfo[0].rcStartDate;
			  		var date = new Date(rcStartDate.substring(0,10));
			  		var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.rateCardInfo[0].rcStartDate = rcStartDD;
			  		var rcEndDate  = $scope.rateCardInfo[0].rcEndDate;
			  		var date = new Date(rcEndDate.substring(0,10));
			  		var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.rateCardInfo[0].rcEndDate = rcEndDateDD;
			  		var expectedRCEndDate  = $scope.rateCardInfo[0].expectedRCEndDate;
			  		var date = new Date(expectedRCEndDate.substring(0,10));
			  		var expectedRCEndDateDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.rateCardInfo[0].expectedRCEndDate = expectedRCEndDateDD;
			  		var currencyId = $scope.rateCardInfo[0].consolidatedRcCurrencyId;
			  		var statusId = $scope.rateCardInfo[0].currentApprovalStatus;
			  		$scope.onsiteHours = $scope.rateCardInfo[0].onsiteHoursPerDay;
			  		var offShoreHours = $scope.rateCardInfo[0].offshoreHoursPerDay;
			  		var isManualCheck = $scope.rateCardInfo[0].isManualRc;
			  		$sessionStorage.isManualRCType = $scope.rateCardInfo[0].isManualRc;
			  		
			  		if($scope.countryData != undefined) {
			  			angular.forEach($scope.countryData,function(value,key) {
			  				if($scope.countryData[key].currencyId == currencyId) {
			  					$scope.currencyName = $scope.countryData[key].currencyCode;
			  				}
			  			});
			  		}
			  		
			  		// set the Status 
			  		$scope.status = [];
			  		switch (statusId) {
			  		case 1 : 
			  			$scope.status = "Draft";
			  			break;
					case 2 : 
						$scope.status = "Pending Approval";
						break;
					case 3 : 
						$scope.status = "Approved";
						break;
					case 4 : 
						$scope.status = "Recycled";
						break;
					case 5 : 
						$scope.status = "Deactivated";
						break;
					case 6 : 
						$scope.status = "Expired";
						break;
					case 7 : 
						$scope.status = "Manual with GFT";
						break;
					default : 
			  			break;
			  		}
			  		
			  		// enable the submit button for GFT
			  		if(userType == "GFT" && statusId == 7) {
			  			$scope.isSaveDisabled=false;
			  		}  else if(statusId == 4) {
			  			$scope.isSaveDisabled=false;
			  		} else if(statusId != 1) {
			  			$scope.isSaveDisabled=true;
			  		}
			  		
			  		if($sessionStorage.isManualRCType == "M")
        			{
        				$scope.manualsidebar=true;
        				$scope.autosidebar=false;
        				$scope.hybridsidebar=false;
        			}
        		else if ($sessionStorage.isManualRCType == "H")
        			{
        				$scope.hybridsidebar=true;
        				$scope.manualsidebar=false;
        				$scope.autosidebar=false;
        			}
        		else {
        				$scope.autosidebar=true;
        				$scope.hybridsidebar=false;
        				$scope.manualsidebar=false;
        			}
				};
				WebServiceFactory.getRateCardInfo($localStorage.rcId).then(getRateCardInfo);
			}
			else {
				 BootstrapDialog.show({
						title : 'Rate Card - Role Selection',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'Please Create New Rate Card or Select Existing One.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location="RateCardCreationDetails";
							}
						} ]
					});
			}
			
		    
		    //1.function to getRateCard
			var getRateCard = function(response) 
			{
				$scope.rate_card = response.data;
				console.log("$scope.rate_card");
				console.log($scope.rate_card);
			};
			WebServiceFactory.getRateCard().then(getRateCard);
			
			var getCountryDetail = function(response) {
				$scope.countryData = response.data;
				console.log("$scope.countryData");
				console.log($scope.countryData);
			}
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
			//2.function for getting Knowledge name
			var getKnowledgeName = function(response)
			{
				$scope.knowledge_name = response.data;
			};
			
			WebServiceFactory.getKnowledgeName().then(getKnowledgeName);
		
			//3.function to get Rate Card Details
			$scope.getRateCardDetails = function(rcId)
			{
				var getRateCardDetails = function(response,rateCard)
				{
					$scope.searchRoleDetailsResult=[];
					$scope.searchModel=null;
					  angular.forEach(response.data, function(value, key) {
						  $scope.addSearchedResultRowInArray(value);
		 				});
					if(($scope.searchRoleDetailsResult.length)<1)
						{
						BootstrapDialog.show({
                            title : 'Role Selection',
                            type : BootstrapDialog.TYPE_DANGER,
                            message : 'No Roles available for the selected search criteria.',
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
				WebServiceFactory.getRoleSelectionDetails(rcId).then(getRateCardDetails);
			};
		    
			
			//4. function to get data on search click 
			$scope.searchRateCardData = function(searchRoles)
			{
			if($scope.searchModel != null && $scope.searchModel != "") {
				$scope.selectAllCheckBox = false;
				var searchRoles = $scope.searchModel;
				var searchRateCardData = function(response)
						{
				  $scope.rateCardModel=null;
				  $scope.searchRoleDetailsResult=[];
				  
				  angular.forEach(response.data, function(value, key) {
					  $scope.addSearchedResultRowInArray(value);
	 				});
				  // 
					if(($scope.searchRoleDetailsResult)<1)
						{
						BootstrapDialog.show({
                            title : 'Role Selection',
                            type : BootstrapDialog.TYPE_DANGER,
                            message : 'No Roles available for the selected search criteria.',
                            closable : false,
                            buttons : [ {
                                  label : 'OK',
                                  action : function(dialogRef) {
                                         dialogRef.close();
                                  }
                            } ]
                        });
						}
					console.log("The search role data is........");
					console.log($scope.searchRoleDetailsResult);
				  } 
				WebServiceFactory.searchRateCardData(searchRoles).then(searchRateCardData);
		    }else{
			      BootstrapDialog.show({
	    	        	title : 'Rate Card Details',
	    	        	type : BootstrapDialog.TYPE_DANGER,
	    	        	message : 'Please enter a keyword to search.',
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
		    
		    //common function to add searched role to searchRoleDetailsResult[]
		    $scope.addSearchedResultRowInArray = function(value){
		    	$sessionStorage.dataSaved = 0;
		    	 $scope.searchRoleDetailsResult.push({
		    		 "syntelRoleId":value.syntelRoleId,
		    		 "syntelRoleName":value.syntelRoleName,
		    		 "clientRole":value.clientRole,
		    		 "comments":value.comments,
		    		 "bandGrade":value.bandGrade,
		    		 "masterRoleId":value.masterRoleId,
		    		 "masterRoleLongDescription":value.masterRoleLongDescription,
		    		 "masterRoleShortDescription":value.masterRoleShortDescription,
		    		 "practiceName":value.practiceName,
		    		 "proficiencyLevelDescription":value.proficiencyLevelDescription,
		    		 "proficiencyLevelId":value.proficiencyLevelId,
		    		 "subPracticeId":value.subPracticeId,
		    		 "subPracticeName":value.subPracticeName,
		    		 "x0ProficiencyDescription":value.x0ProficiencyDescription,
		    		 "x0ProficiencyId":value.x0ProficiencyId,
		    		 "chkBoxStatus":false,
		    		 "gcmCODE":value.gcmCODE
		    	 })
		    	  
		    }
		    
		    
		    //5. add selected roles on click of add button
		    
		    $scope.addSelectedRoles = function(){
		    	if(($scope.searchRoleDetailsResult)<1)
				{
				BootstrapDialog.show({
                    title : 'Role Selection',
                    type : BootstrapDialog.TYPE_DANGER,
                    message : 'Please select a Role to Add',
                    closable : false,
                    buttons : [ {
                          label : 'OK',
                          action : function(dialogRef) {
                                 dialogRef.close();
                          }
                    } ]
                });
				} else if($scope.checkedItems.length != 0) {
						$sessionStorage.dataSaved = 0;
						angular.forEach($scope.searchRoleDetailsResult, function(value, key,index) {
							if(value.chkBoxStatus){
								value.chkBoxStatus=false;
								$scope.selectAllCheckBox = false;
								//load xo skills based on subpractice id
								WebServiceFactory.getXOSkillFromSubPracticeId(value.subPracticeId)
								.then(function(response){
									$scope.index = $scope.index+1;
									//push current data into selectededRolesArr []
									$scope.selectededRolesArr.push({
										"rcId":$localStorage.rcId,
										"masterRoleLongDescription":value.masterRoleLongDescription,
										"masterRoleShortDescription":value.masterRoleShortDescription,
										"masterRoleId":value.masterRoleId,
										"syntelRoleId":value.syntelRoleId,
										"syntelRoleName":value.syntelRoleName,
										"bandGrade":value.bandGrade,
										"proficiencyLevelDescription":value.proficiencyLevelDescription,
										"proficiencyLevelId":value.proficiencyLevelId,
										"xOSkill":response.data,
										"xOSkillIndex":null,
										"xOSkillElement":[],
										"xOSkillElementIndex":null,
										"xOKnowledge":angular.copy($scope.knowledge_name),
										"xOKnowledgeIndex": null,
										"clientRole":value.clientRole,
										"comments":value.comments,
										"practiceName":value.practiceName,
										"subPracticeName":value.subPracticeName,
										"rcRowCount": $scope.index,
										"gcmCODE":value.gcmCODE
										
									});
									$scope.isModified = true;
									console.log("Is modified");
									console.log($scope.isModified);
									console.log("Searched Role Details");
									console.log($scope.searchRoleDetailsResult);
									console.log("Selected Role Details");
									console.log($scope.selectededRolesArr);
									
									
								},function(err){
									console.log(err);
								});
							}
						});
						
						angular.forEach($scope.checkedItems, function(value,key,index) {
							if(value.chkBoxStatus){
								value.chkBoxStatus=false;
								$scope.splicedArray = $scope.checkedItems.splice(value.chkBoxStatus,1);
							}
						});
						}  else{
							  BootstrapDialog.show({
				    	        	title : 'Role Selection',
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

		    //delete rate card row
			$scope.deleteRow = function(arrayData,index){
				if(arrayData.roleId != undefined ) {
		    		angular.forEach($scope.selectededRolesArr, function(value,key) {
		    			if($scope.selectededRolesArr[key].roleId == arrayData.roleId) {
		    				$scope.selectededRolesArr.splice(key, 1);
		    				$scope.isModified = true;
		    			}
		    		});
		    	} else {
		    		$scope.selectededRolesArr.splice(index, 1);
		    		$scope.isModified = true;
		    	}
			};
			
			$scope.saveOrUpdateRole = function(){
				var saveOrUpdateRole = function(response){
					if($scope.selectededRolesArr.length > 0) {
						$sessionStorage.dataSaved = 1;
						if( response.status == 200){
							$scope.isModified = false;
							BootstrapDialog.show({
								title : 'Rate Card Roles Creation',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : 'Rate Card Roles created/updated successfully.',
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										$window.location.reload();
									}
								} ]
							});
						}
						else if( response.status == 204){
							BootstrapDialog.show({
								title : 'Rate Card Roles Creation',
								type : BootstrapDialog.TYPE_DANGER,
								message : 'Please submit rate card on page 1.',
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										$window.location.reload();
									}
								} ]
							});
						} else {
                            BootstrapDialog.show({
	                              title : 'Rate Card Roles Creation',
	                              type : BootstrapDialog.TYPE_DANGER,
	                              message : 'Currently we are facing technical issues.',
	                              closable : false,
	                              buttons : [ {
	                                     label : 'OK',
	                                     action : function(dialogRef) {
	                                            dialogRef.close();
	                                            $window.location.reload();
	                                     }
	                              } ]
	                       });
                     }

					}
					 else{
						 BootstrapDialog.show({
								title : 'Rate Card Roles Creation',
								type : BootstrapDialog.TYPE_DANGER,
								message : 'Please add atleast 1 role.',
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(dialogRef) {
										dialogRef.close();
										$window.location.reload();
									}
								} ]
							});
					 }

				};
				WebServiceFactory.saveOrUpdateRole($scope.selectededRolesArr).then(saveOrUpdateRole);
				}
			
               //8.function to getting X0 skill Element master 
				console.log ("role data for xo skills element::::::::::::::::::::::::::::");
				console.log($scope.selectededRolesArr);
				
				$scope.getXOSkillsElementMaster = function(skillId,index,arrayData)
				{
					// new code added 
					if(skillId != null || skillId != undefined){
					var getXOSkillsElementMaster = function(response)
					{
						/*if(arrayData != undefined) {
							angular.forEach($scope.selectededRolesArr, function(value,key) {
								if($scope.selectededRolesArr[key].roleId == arrayData.roleId) {
									$scope.selectededRolesArr[key].xOSkillElement=response.data;
								} else {
									$scope.selectededRolesArr[index].xOSkillElement=response.data;
								}
							});
						} else {
							$scope.selectededRolesArr[index].xOSkillElement=response.data;
						}*/
						
						if($sessionStorage.dataSaved == 0) {
							$scope.selectededRolesArr[index].xOSkillElement=response.data;
						} else if ($sessionStorage.dataSaved == 1) {
							angular.forEach($scope.selectededRolesArr, function(value,key) {
							if($scope.selectededRolesArr[key].roleId == arrayData.roleId) {
								$scope.selectededRolesArr[key].xOSkillElement=response.data;
							}
							});
						}			
					};
				
						WebServiceFactory.getXOSkillsElementMaster(skillId).then(getXOSkillsElementMaster);
					}
				};
				
				//9.function for getting Knowledge name
				var getKnowledgeName = function(response)
				{
					$scope.knowledge_name = response.data;
				};
				
				WebServiceFactory.getKnowledgeName().then(getKnowledgeName);
			
				
				//6.for showing data on below table on page load..........
					var getPersistRateCardDetails =function(response)
					{
						console.log("Getting data from the rate cards");
						$scope.persistantDataArray = response.data;
						console.log(response.data);
						$scope.persistantDataArray = $filter('orderBy')($scope.persistantDataArray, 'rcRowCount');
						var lastIndex = $scope.persistantDataArray.slice(-1).pop().rcRowCount;
						console.log("the last Index is..........");
						$sessionStorage.dataSaved =1;
						console.log(lastIndex);
						if(response.data){
							var index=0;
							angular.forEach(response.data,function(value,key)
							{
								$scope.index = lastIndex;
								console.log("the Array index of ROw count is........... "+ $scope.index);
								 WebServiceFactory.getXOSkillFromSubPracticeId(value.subPracticeId)
			            		 	.then(function(response){
			            		 		//get XO skills from skill id
			            		 		 $scope.getXOSkillsElementMaster(value.xOSkillIndex,index,value);
										 index=index+1;
										
										 //alert("the Index value is................ "+ $scope.index);
										 
										 //push current data into selectededRolesArr []
			            		 		 $scope.selectededRolesArr.push({
			            		 			"roleId":value.roleId, 
			            		 			"rcId":value.rcId,
			     							"masterRoleLongDescription":value.masterRoleLongDescription,
			     				    		"masterRoleShortDescription":value.masterRoleShortDescription,
			     				    		"masterRoleId":value.masterRoleId,
			     				    		"syntelRoleId":value.syntelRoleId,
			     				    		"syntelRoleName":value.syntelRoleName,
			     				    		"bandGrade":value.bandGrade,
			     				    		"proficiencyLevelDescription":value.proficiencyLevelDescription,
			     				    		"proficiencyLevelId":value.proficiencyLevelId,
			     				    		"xOSkill":response.data,
			     				    		"xOSkillIndex":value.xOSkillIndex,
			     				    		"xOSkillElement":[],
			     				    		"xOSkillElementIndex":value.xOSkillElementIndex,
			     				    		"xOKnowledge":angular.copy($scope.knowledge_name),
			     				    		"xOKnowledgeIndex": value.xOKnowledgeIndex,
			     				    		"clientRole":value.clientRole,
			     				    		"comments":value.comments,
			     				    		"practiceName":value.practiceName,
											"subPracticeName":value.subPracticeName,
											"rcRowCount":value.rcRowCount,
											"gcmCODE":value.gcmCODE
			     						  });
			            		 	},function(err){
			            		 		console.log(err);
			            		 	});
							});
						}
					};
					
					WebServiceFactory.getPersistRateCardDetails($localStorage.rcId).then(getPersistRateCardDetails);
					
					$scope.addCheckedItemList = [];
					$scope.checkedItems =[];
					$scope.splicedArray = [];
					// Adding the Checked Items.... 
					$scope.addChkItem = function (emp,status,index) {
			                if (status==true) {
			                	$scope.addCheckedItemList(index,status,emp.masterRoleShortDescription);
			                } else {
			                	for(var i =0; i<$scope.checkedItems.length; i++){
			                		if($scope.checkedItems[i].shortDesc == emp.masterRoleShortDescription) {
			                			$scope.splicedArray = $scope.checkedItems.splice(i,1);
			                	}
			        		  }
			                }
			            };
			            
			            $scope.addCheckedItemList = function (index,chkBoxStatus,shortDesc) {
							$scope.checkedItems.push ({
								"index" : index,
								"chkBoxStatus" :chkBoxStatus,
								"shortDesc" : shortDesc
								});
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
						
						$scope.myorder=function(rateCard){
							$scope.forder=rateCard;
						}
						
						$scope.myorder=function(currentRole){
							$scope.forder=currentRole;
						}
						
						var getPageTrckrData =function(response)
				    	{
				    		console.log("Version Data");
				    		console.log(response);
				    		$scope.pagetrckDetails = response.data;
					    $localStorage.pageTracker=$scope.pagetrckDetails[0].noOfSubmittedPages;
			    		 if(userType == 'Delivery') {
			    		 if( $localStorage.pageTracker<1)
				    			{
				    			BootstrapDialog.show({
									title : 'FP Deal Creation - Role Selection',
									type : BootstrapDialog.TYPE_DANGER,
									message : 'Data is not saved at previous screen.',
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											//$window.location.reload();
											if($localStorage.pageTracker==1){
												window.location="RateCardCreationRoleSelection";
											}
											
											else
												{window.location="RateCardCreationDetails";
												
												}
											

										}
									} ]
								});
				    		}
			    		 }
				    	};
				    	WebServiceFactory.getPageTrckrData($localStorage.rcId).then(getPageTrckrData);
		 
				
		}]); 
		
		