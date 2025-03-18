
//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("FPDealCreationRoleSelectionController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 console.log("inside FPDealCreationRoleSelectionController");
		 var contextPath = "/RightPrice-DAS";
		 var countryId=0;
		 var cityId=0;
		 var categoryId=0;
		 var dealAutoTowerId=0;
		 var dealTowerId=0;
		 var dealRCId=0;
		 var skillId=0;
		 var KnowledgeIndex=0;
		 var SkillElementIndex=0;
		 $scope.DisplayRows=false;
		// $scope.masterroleManage=false;
		 $scope.masterroleFixed=false;
		 $scope.masterroleKPO=false;
		 $scope.Roleheader=true;
		 $scope.rcRoleheader=true;
		 $scope.RoleMasterheader=true;
		 $scope.rcRoleMasterheader=true;
		 $scope.onSave=false;
		 $scope.isRCPricing=false;
		 $scope.isFPDealGFT = false;
		 $scope.isFPDealRiskManagers = false;
		 $scope.isDevelopment=false;
		 $scope.exportMastersData=[]
		 $scope.exportRCData =[]
		 	
		 
		 var userType = sessionStorage.getItem('userType');
		 console.log("The USer Value from the Session is........ "+ userType);
		 if(userType == 'GFT') {
			 $scope.isFPDealGFT = true;
		 }
		 else if(userType == 'RiskManagers') {
				$scope.isFPDealRiskManagers = true;
			}
		  $scope.fpdcRoleSelectionFrm={};
			//page load functionality
		  var rpVrsId = $localStorage.rpDealVersionId;
		  var rpDealVersionId = $localStorage.rpDealVersionId;
		 $scope.addContractorRoleHidden = true;
		 
         $scope.showHideAddContractorRole = function () {
        	 $scope.addContractorRoleHidden = $scope.addContractorRoleHidden ? false : true;
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
	   	$scope.Prev = function() 
	    {   
	        window.location='FPDealCreationRateCardAndProjectDetails';
	    };
	    $scope.Next = function()
	    { 
	    	
	    	console.log("Click on next----page 2");
	    	console.log($scope.contryList);
	    	console.log($sessionStorage.CityList);
	    	$sessionStorage.varCheck = 0;
	    	$sessionStorage.ContryList = $scope.contryList;
	    	$sessionStorage.CityList = $sessionStorage.CityList;
	    	$localStorage.rpDealVersionId = $localStorage.rpDealVersionId;
	    	//End
	    	//  window.location='FPDealCreationStaffing';
	    	// window.location='FPDealCreationAddContractorRole'; removing 
	    	window.location = 'FPDealCreationStaffing';
	    	
	    };   
	    
	    $scope.exportToExcel=function(tableId){
			//	$scope.xoSkillName=fpdcRoleSelectionFrm
			var exportHref=WebServiceFactory.tableToExcel(tableId,"FP_Deal_Creation_Role_Selection");
		}
/*	    
	    $scope.exportToExcel = function()
		{
	     if($scope.fpDealRoledetails!="" || $scope.selectededRolesArr.length!=0)
	     	{
	    		window.location= contextPath+"/RightPrice-DAS/downloadFPDealCreationRoleSelectionExcel/"+rpDealVersionId+"/"+dealVersionId+"/"+$localStorage.DealModel+"/"+ rpVrsId+"/"+ dealAutoTowerId;
			 }
	    else
	    	{
	    	 BootstrapDialog.show({
 	        	title : 'Deal Creation - Role Selection',
 	        	type : BootstrapDialog.TYPE_DANGER,
 	        	message : 'Please save data before Exporting To Excel.',
 	        	closable : false,
 	        	buttons : [{
 	        		label : 'OK',
 	        		action : function(dialogRef) {
 	        			dialogRef.close();
 	        		}
 	        	}]
 	        });
	    		
	    		}
		};*/
	    
	    
	    
	    
	    
	    $scope.checkMasterRole = function (typecheck){
	    	if(typecheck)
	    		{
	    		
	    		var getVersionData =function(response)
		    	{
		    		$scope.versionDetails = response.data;
		    	

		    		if($scope.versionDetails[0].projectIndustry == 1)
	    			{
	    			
	    			if($scope.versionDetails[0].fpProjectTypeId == 2 || $scope.versionDetails[0].fpProjectTypeId == 4 )
	    			{
	    				//$scope.masterroleManage = false;
	    				$scope.masterroleFixed = true;
	    				$scope.masterroleKPO=false;
	    				$scope.rcRoleMasterheader=false;
	    			}
	    			else
	    			{
	    				$scope.masterroleFixed = true;
	    				$scope.masterroleKPO=false;
	    				//$scope.masterroleManage = false;
	    				$scope.rcRoleMasterheader=false;
	    			}
	    			}
	    		else 
	    			{
	    				$scope.masterroleKPO=true;
	    				//$scope.masterroleManage = false;
	    				$scope.masterroleFixed = false;
	    			}
	    			
	    		
	    		}
	    	
		    	WebServiceFactory.getVersionData(rpDealVersionId).then(getVersionData);
	    	    }		    	
	    		    	else
	    		    		{
	    		    			$scope.masterroleFixed = false;
	    		    			
	    		    			//$scope.masterroleManage = false;
	    		    			
	    		    			$scope.masterroleKPO=false;
	    		    			
	    		    			$scope.rcRoleMasterheader=true;
	    		    			
	    		    		}
	    			    		
	    }
    	
	    $scope.getTowerCountryCity = function (fpdTowervalue) {
	    	$scope.searchRoleDetailsResult=[];
	    	$scope.selectededRolesArr=[];
	    	$scope.contractorRoleArr=[];
	    	 var dealVersionId=$localStorage.rpDealVersionId; 
	    	angular.forEach($scope.towerdetails, function(value, key) {
				 if(fpdTowervalue == $scope.towerdetails[key].dealTowerId)
					 {
					 	
					 	countryId=$scope.towerdetails[key].countryId
					 	cityId=$scope.towerdetails[key].cityId;
					 	dealAutoTowerId=$scope.towerdetails[key].dealautoTowerId;
					 	dealTowerId=$scope.towerdetails[key].dealTowerId;
					 	
					 }
				});
	    	
	    	
	    	var getCountryDetail = function(response) {
	    		console.log("Deal version ID : " + $localStorage.rpDealVersionId);
	    		console.log("get country data");
		  		console.log(response);
		  		$scope.country = response.data;
		  		angular.forEach($scope.country, function (value, key) {
		  			if($scope.country[key].countryId == countryId)
		  				{
		  					$scope.fpdcRoleSelectionFrm.dealRoleDetailsCountryModel=$scope.country[key].countryName;
		  				}
	               });
		  		
			};
			WebServiceFactory.getCountryDetail().then(getCountryDetail);
			
	    	 var getCities = function(response) {
	     		$scope.city = response.data;
	     		console.log("City Names are...........");
	     		console.log($scope.city);
	     		angular.forEach($scope.city, function(value, key) {
					 if($scope.city[key].cityId == cityId)
						 {
						 	$scope.fpdcRoleSelectionFrm.dealRoleDetailsCityModel=$scope.city[key].cityName;
						 	categoryId=$scope.city[key].categorizationId;
						 	
						 	var getCitycategorization = function(response)
						 	{
						 		console.log("City Category data....");
						 		console.log(response);
						 		$scope.getCityCat=response.data;
						 		angular.forEach($scope.getCityCat, function(value, key) {
						 		if($scope.getCityCat[key].codeName==categoryId)
						 			{
						 			$scope.fpdcRoleSelectionFrm.dealRoleDetailsCategorizationModel=$scope.getCityCat[key].description;
						 			}
						 		});
						 	};
							WebServiceFactory.getCitycategorization().then(getCitycategorization);
						 
						 }
					});
	     	
	     	};
	     	WebServiceFactory.getCities(countryId).then(getCities);
	     	
	     	
	     	var getFpDealRateCard = function(response)
	     	{
	     		console.log("RateCards based on Tower...");
	     		$scope.fpdealratecards = response.data;
	     		console.log($scope.fpdealratecards);
	     		if($scope.fpdealratecards!="")
	     			{
	     				dealRCId=$scope.fpdealratecards[0].rcId;
	     				$scope.fpdcRoleSelectionFrm.rateCardModel=$scope.fpdealratecards[0].rcName;
	     			}
	     		else
	     			{
	     				dealRCId=0;
	     				$scope.fpdcRoleSelectionFrm.rateCardModel=0;
	     			}
	     		
	     		
	     		
			 	
	     	}
	     	WebServiceFactory.getFpDealRateCard(dealTowerId,dealVersionId).then(getFpDealRateCard);
	     	
	    	var getPersistFpDealDetails =function(response)
			{
				console.log("Existing Rate cards.................")
				console.log(response.data);
				
				
				if(response.data.dealContractorRole){
					angular.forEach(response.data.dealContractorRole,function(value,key){
						$scope.contractorRoleArr.push({
							"customerRole":value.customerRole,
							"comments":value.comments,
							"dealAutoTowerId":dealAutoTowerId
							
						});
					})
				};
				if(response.data.persistedDealRoles){
					
					var index=0;
					var j=0,k=0;
					
					angular.forEach(response.data.persistedDealRoles,function(value,key)
					{	
						if(response.data.persistedDealRoles[key].rcId==0)
							{
								$scope.rcRoleMasterheader=false;
							}
						else
							{
								$scope.rcRoleheader=false;
							}
					
					 if(response.data.persistedDealRoles[key].isMasterRole==1)
					 {
						$scope.fpdcRoleSelectionFrm.masterRolechkbox=true;
						if($sessionStorage.industryType == 1)
							{
								if($sessionStorage.fpType==2 || $sessionStorage.fpType==4)
								{
									//$scope.masterroleManage = true;
									$scope.masterroleFixed = true;
									$scope.masterroleKPO=false;
									
								}	
								else
								{
									//$scope.masterroleManage = false;
									$scope.masterroleFixed = true;
									$scope.masterroleKPO=false;
									
								}
							}
						else
							{	$scope.masterroleKPO=true;
								//$scope.masterroleManage = false;
								$scope.masterroleFixed = false;
							}
					}
				
						
						console.log("index old: " + index );
						//$scope.secondTable=response.data;
						 WebServiceFactory.getXOSkillsRoles()
	            		 	.then(function(response){
	            		 		//get XO skills from skill id
	            		 		 $scope.getXOSkillsElementMaster(value.xOSkillIndex,index);
								 index=index+1;
								 console.log("index new : " + index );
								 console.log(value);
								 console.log("-----------------------------------------------------------------------------");
								 
								 //push current data into selectededRolesArr []
								 
								 var gcmVal = [];
								/* if(value.gcmCODE.includes("/")){checkMasterRole
									 gcmVal = value.gcmCODE.replace("/","_")
									 
									 } else{
									 gcmVal = value.gcmCODE;
								 }*/
	            		 		 $scope.selectededRolesArr.push({
	            		 			"rcId":value.rcId,
	            		 			"dealAutoTowerId":value.dealAutoTowerId,
	     							"masterRoleLongDescription":value.masterRoleLongDescription,
	     				    		"masterRoleShortDescription":value.masterRoleShortDescription,
	     				    		"rcRoleId":value.rcRoleId,
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
	     				    		"gcmCODE":value.gcmCODE,
	     				    		"gcmVal":gcmVal
	     						  });
		            		 		if(value.rcId == 0){
		        						//aray selected from master search
		        						$scope.exportMastersData[j] = $scope.selectededRolesArr[index-1];
		        						j++;
		        						
		        					}
		        					else{
		        						//array from ratecard
		        						$scope.exportRCData[k] =$scope.selectededRolesArr[index-1];
		        						k++;
		        					}
		            		 	            		 		
	            		 	},function(err){
	            		 		console.log(err);
	            		 	});
					});
					console.log("---------------------------f-------------------")
					console.log($scope.exportMastersData) 
					console.log($scope.exportRCData)
					
					
				}
				else
				{
					$scope.fpdcRoleSelectionFrm.masterRolechkbox=false;
					//$scope.masterroleManage = false;
					$scope.masterroleFixed=false;
					$scope.rcRoleheader=true;
				}
				console.log("nnnnnnnnnnnnnnnnnnnnnnnnnnnn");
				console.log($scope.selectededRolesArr);
				
				angular.forEach($scope.selectededRolesArr,function(value,key)
						{
							KnowledgeIndex=value.xOKnowledgeIndex;
							angular.forEach($scope.selectededRolesArr[key].xOKnowledge,function(value2,key2)
									{
										if($scope.selectededRolesArr[key].xOKnowledge[key2].knowledgeId==KnowledgeIndex)
											{
												$scope.selectededRolesArr[key].xoKnowledgeName=value2.knowledgeName;
											}
									});
						});
				
			};
			
			WebServiceFactory.getPersistFpDealRoledDetails(dealVersionId,dealTowerId).then(getPersistFpDealDetails);
	    }
	  
	    
	  /*local storage data*/
	    $scope.contryList =$sessionStorage.ContryList;
	    console.log("Coutry list from session---");
	    console.log($scope.contryList);
	    var dealVersionId=$localStorage.rpDealVersionId;    //need to load from local storage
	    
	  /*local scope variables*/
	    	$scope.rateCards=[];
	    	$scope.searchRoleDetailsResult=[];
	    	
	    	$scope.selectededRolesArr=[];
	    	$scope.contractorRoleArr=[];
	    		
	    	var getVersionData =function(response)
	    	{
	    		console.log("Version Data");
	    		console.log(response);
	    		$scope.versionDetails = response.data;
	    		$sessionStorage.fpType =$scope.versionDetails[0].fpProjectTypeId;
	    		$sessionStorage.industryType =$scope.versionDetails[0].projectIndustry;
	    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||  $scope.versionDetails[0].currentApprovalStatus == 4 || $scope.versionDetails[0].currentApprovalStatus == 8 )&& userType == 'Delivery')
		    			{
		    				$scope.isSaveDisabled=false;
		    			}
		    		else
		    			{
		    			    $scope.isSaveDisabled=true;
		    			}
	    		 if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId == 0)
					{
						$scope.versionDetails[0].dealStatus='Open';
					}
					else if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId == 1)
						{
							$scope.versionDetails[0].dealStatus='Won';
						}
					else{
							$scope.versionDetails[0].dealStatus='Lost';
					}
	    		 $localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
	    		 if(userType == 'Delivery') {
	    		 if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
		    				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<2)
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
										window.location="FPDealCreationRateCardAndProjectDetails";
									}
									
									else
										{window.location="FPDealCreationDetails";
										
										}
									

								}
							} ]
						});
		    		}
	    		 }	
	    		if($scope.versionDetails[0].fpProjectTypeId==1)
	    			{
	    				$scope.versionDetails[0].projectType='Development - Fixed Price';
	    			
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==2)
	    			{
	    				$scope.versionDetails[0].projectType='Development - Manage Capacity';
	    				
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==3)
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Fixed Price';
	    				
	    			}
	    		else
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Manage Capacity';
	    				
	    			}
	    		if($scope.versionDetails[0].fpProjectTypeId==1 || $scope.versionDetails[0].fpProjectTypeId==2){
	    			$scope.isDevelopment=true;	
	    		}
	    		else{
	    			$scope.isDevelopment=false;
	    		}	
	    		
	    		
	    		/*$localStorage.pricingType=$scope.versionDetails[0].pricingType;
	    		
	    		if($localStorage.pricingType!=1)
	  			{
	  			  $scope.isRCPricing=true;
	  			}
	  			else{
	  				$scope.isRCPricing=false;
	  			}*/
	    	};
	    	WebServiceFactory.getVersionData(rpDealVersionId).then(getVersionData);
	    	
	    	

	    /*	$scope.checkMasterRole = function (typecheck){
	    		if(typecheck)
	    			{
	    			$scope.masterRolePanel = true;
	    			}
	    		else
	    			$scope.masterRolePanel = false;
	    		
	    	}*/
	    	
	    	
	    	var getDealDetails = function(response) {				
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		console.log("Approver Data")
		  		console.log($scope.dealDetails);
		  		console.log($scope.dealDetails[0].dealId);
		  		console.log($scope.dealDetails[0].customerId);
		  		console.log($scope.dealDetails[0].dealStartDate);
		  		console.log($scope.dealDetails[0].dealEndDate);
		  		console.log($scope.dealDetails[0].dealDescription);
		  		console.log($scope.dealDetails[0].dealStatus)
		  		console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
		  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
		  		
		  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
		  		var date = new Date(dealEndDate.substring(0,10));
		  		console.log("End Date is......... " + date);
		  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.dealDetails[0].dealEndDate = dateENd;
		  		
				console.log("dateEND     ...... "+dateENd)
		  		
				var dealStartDate = $scope.dealDetails[0].dealStartDate;
				var date = new Date(dealStartDate.substring(0,10));
				var dateStart = $filter('date')(date,'dd/MM/yyyy');
				$scope.dealDetails[0].dealStartDate = dateStart;
				// alert('hi 120');
				$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	//arvind
				$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;
				
					
				 var startDay = new Date(dateStart);
                  var endDay = new Date(dateENd);
                  var millisecondsPerDay = 1000 * 60 * 60 * 24;

                  var millisBetween =  endDay.getTime()-startDay.getTime() 
                  var days = millisBetween / millisecondsPerDay;
                  $scope.dealDetails[0].DealDuration = Math.floor(days);
				
	            
		  		console.log($scope.dealDetails[0].percentageClose);
		  		console.log($scope.dealDetails[0].currencyId);
		  		console.log($scope.dealDetails[0].dealDuration);
		  		console.log($scope.dealDetails[0].stageId);
			};

			WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails);
	    	
	    
	  /*custom functions*/
	    	//2.function to get Rate Card Details
			$scope.getFpRateCardDetails = function(rcId)
			{	$scope.Roleheader=false;
				$scope.searchRoleDetailsResult=[];
				var getRateCardDetails = function(response)
				{
					$scope.getRateCardDetails=response.data;
					
					$scope.fpdcRoleSelectionFrm.searchModel=null;
					console.log("Rate Card Details after selection");
					console.log(response.data);
					
					
					
					console.log(dealRCId);
					 
					
					if($scope.getRateCardDetails.length>0)
						{
						var getXOSkillsRoles = function(response)
						{
							console.log("MAster XO Skill");
							console.log(response.data);
							$scope.getXoSkill=response.data;
							
							angular.forEach($scope.getRateCardDetails, function(value,key) {
						    	angular.forEach(response.data, function(value2, key2) {
						    	    	if($scope.getRateCardDetails[key].xOSkillIndex == response.data[key2].skillId) {
						    	    		$scope.getRateCardDetails[key].XOSkillName=value2.skillName;
						    	    		skillId=response.data[key2].skillId;
						    	    		var getXOSkillElementRoles = function(response)
											{
												console.log(" MAster XOSkill Elements");
												$scope.getXoSkillElement=response.data;
												console.log($scope.getXoSkillElement);
												
											if($scope.getRateCardDetails[key].xOSkillElementIndex!=null)
												{
													angular.forEach($scope.getXoSkillElement, function(value3, key3) {
														if($scope.getRateCardDetails[key].xOSkillElementIndex == $scope.getXoSkillElement[key3].skillElementId)
														{
															$scope.getRateCardDetails[key].XOSkillElementName=value3.elementName;
															skillElementName=value3.elementName;
															skillElementId=$scope.getXoSkillElement[key3].skillElementId;
															$scope.addSearchedResultRowInArray(value);
															console.log("Addsearcharray");
															console.log(value);
															
															/*  angular.forEach(response.data, function(value, key) {
														  $scope.addSearchedResultRowInArrayChange(value);
										 				});*/
															
															
														}
													});
													}
											else
												{
													$scope.getRateCardDetails[key].XOSkillElementName=null;
													skillElementName=null;
													skillElementId=null;
													$scope.addSearchedResultRowInArray(value);
													console.log("Addsearcharray");
													console.log(value);
												}
											
												
											};
										
										  WebServiceFactory.getXOSkillElementRoles(skillId).then(getXOSkillElementRoles);
										
						    	    	}
						    	     });
						    	
									 
					 				
						     });
							
							
							
						};
					
						WebServiceFactory.getXOSkillsRoles().then(getXOSkillsRoles);
						
						/*angular.forEach($scope.getRateCardDetails, function(value, key) {
							dealRCId=$scope.getRateCardDetails[key].rcId;
							skillId=$scope.getRateCardDetails[key].xOSkillIndex;
							KnowledgeIndex=$scope.getRateCardDetails[key].xOKnowledgeIndex;
							SkillElementIndex=$scope.getRateCardDetails[key].xOSkillElementIndex;

			    	    		
			    	     });*/
						
						
										
						var getKnowledgeNameRole = function(response)
						{
							console.log("Knowledge Based Elements");
							console.log(response.data);
							$scope.knowledge=response.data;
							angular.forEach($scope.getRateCardDetails, function(value,key) {
						    	angular.forEach(response.data, function(value2, key2) {
						    	    	if($scope.getRateCardDetails[key].xOKnowledgeIndex == response.data[key2].knowledgeId) {
						    	    		$scope.getRateCardDetails[key].XOKnowledgeName=value2.knowledgeName;
						    	    	}
						    	     });
						     });
							
						};
					
						WebServiceFactory.getKnowledgeNameRole().then(getKnowledgeNameRole);
						
						
						console.log("Rate Card Details after adding Skill names ");	
						console.log($scope.getRateCardDetails);
				}
				else
					{$scope.Roleheader=true;
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
			
			//3.common function to add searched role to searchRoleDetailsResult[]
		    $scope.addSearchedResultRowInArray = function(value){
		    	//console.log(value);
		    	
		    	 $scope.searchRoleDetailsResult.push({
		    		 "syntelRoleId":value.syntelRoleId,
		    		 "syntelRoleName":value.syntelRoleName,
		    		 "clientRole":value.clientRole,
		    		 "comments":value.comments,
		    		 "bandGrade":value.bandGrade,
		    		 "roleId":value.roleId,
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
		    		 "xoSkillName":value.XOSkillName,
		    		 "xoSkillElementName":value.XOSkillElementName,
		    		 "xoKnowledgwIndex":value.xOKnowledgeIndex,
		    		 "xoKnowledgeName":value.XOKnowledgeName,
		    		 "xoSkillIndex":value.xOSkillIndex,
		    		 "xoSkillElementIndex":value.xOSkillElementIndex,
		    		 "gcmCODE":value.gcmCODE
		    	 })
		    }
		    
		    $scope.addSearchedResultRowInArrayChange = function(value){
		    	//console.log(value);
		    	 $scope.searchRoleDetailsResultChange.push({
		    		 "syntelRoleId":value.syntelRoleId,
		    		 "syntelRoleName":value.syntelRoleName,
		    		 "clientRole":value.clientRole,
		    		 "comments":value.comments,
		    		 "bandGrade":value.bandGrade,
		    		 "roleId":0,
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
		    		 "chkBoxStatusChange":false,
		    		 "xoSkillName":value.XOSkillName,
		    		 "xoSkillElementName":value.XOSkillElementName,
		    		 "xoKnowledgwIndex":value.xOKnowledgeIndex,
		    		 "xoKnowledgeName":value.XOKnowledgeName,
		    		 "xoSkillIndex":value.xOSkillIndex,
		    		 "xoSkillElementIndex":value.xOSkillElementIndex,
		    		 "gcmCODE":value.gcmCODE
		    	 })
		    }
			
		    //4. function to get data on search click 
		    $scope.searchRateCardData = function()
			{
		    	
				var searchRoles = $scope.fpdcRoleSelectionFrm.searchModel;
				console.log( $scope.fpdcRoleSelectionFrm.searchModel);
				if(searchRoles!=null)
				{	
				var searchRateCardData = function(response)
				{
					$scope.RoleMasterheader=false;
				  /*$scope.fpdcRoleSelectionFrm.searchModel=null;*/
				  console.log("Available Roles");
				  console.log(response);
				  $scope.searchRoleDetailsResultChange=[];
				  //console.log(response.data);
				  
				  //console.log( $scope.searchRoleDetailsResult);
				  angular.forEach(response.data, function(value, key) {
	 					//console.log(value);
	 					//console.log("Added");
					  $scope.addSearchedResultRowInArrayChange(value);
					});
				  //console.log( $scope.searchRoleDetailsResult);
				  
				  
				  if($scope.fpdcRoleSelectionFrm.searchModel != null) {
					if($scope.searchRoleDetailsResultChange.length==0)
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
				  } 
				  
			  }
				WebServiceFactory.searchRateCardData(searchRoles).then(searchRateCardData);
				
			}
				
				else
					{
					BootstrapDialog.show({
                        title : 'Role Selection',
                        type : BootstrapDialog.TYPE_DANGER,
                        message : 'Please Enter search details.',
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
		    
		    $scope.addCheckedItemList = [];
		    $scope.addCheckedItemListChange = [];
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
	            
	            $scope.addChkItem = function (emp,status,index) {
	                if (status==true) {
	                	$scope.addCheckedItemListChange(index,status,emp.masterRoleShortDescription);
	                } else {
	                	for(var i =0; i<$scope.checkedItems.length; i++){
	                		if($scope.checkedItems[i].shortDesc == emp.masterRoleShortDescription) {
	                			$scope.splicedArray = $scope.checkedItems.splice(i,1);
	                	}
	        		  }
	                }
	            };
	            
	            
	            $scope.addCheckedItemListChange = function (index,chkBoxStatusChange,shortDesc) {
					$scope.checkedItems.push ({
						"index" : index,
						"chkBoxStatusChange" :chkBoxStatusChange,
						"shortDesc" : shortDesc
						});
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
						title : 'Deal Creation - Roles Creation',
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
		    
		    $scope.addAllCheckedItemsChange = function(data,selectAllChckbxstatusChange) {
				
				if(data.length != 0 && selectAllChckbxstatusChange == true) {
					for(var i=0;i<data.length;i++) {
						data[i].chkBoxStatusChange = true;
						$scope.addCheckedItemList(i,data[i].chkBoxStatus,data[i].masterRoleShortDescription);
					}
				}else if(selectAllChckbxstatusChange == false ){
					for(var i=0;i<data.length;i++) {
					data[i].chkBoxStatusChange = false;
                	$scope.splicedArray = $scope.checkedItems.splice(i,1);
					}
				} else {
					$scope.selectAllCheckBoxChange = false;
					BootstrapDialog.show({
						title : 'Deal Creation - Roles Creation',
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
		    
		    
		    //6. add selected roles on click of add button
		    $scope.addSelectedRoles = function(){
		    	
		    if($scope.checkedItems.length != 0) {
		    	console.log("Inside Add role");
		    	$scope.rcRoleheader=false;
		    	//console.log( $scope.searchRoleDetailsResult);
				 angular.forEach($scope.searchRoleDetailsResult, function(value) {
					  if(value.chkBoxStatus){
						  //console.log(value);
						  // to uncheck the selected checkbox
						  value.chkBoxStatus=false;
						  //load xo skills based on subpractice id
						  WebServiceFactory.getXOSkillsRoles()
	            		 	.then(function(response){
	            		 		//push current data into selectededRolesArr []
	            		 		$scope.getXOSkillsElementMasternew(value.xoSkillIndex);
	            		 		console.log("Inside Add");
	            		 		console.log(dealRCId);
	            		 		console.log(dealAutoTowerId);
	            		 		//alert("the Index value is................ "+ $scope.index);
								 var gcmVal = [];
								 /*if(value.gcmCODE.includes("/")){
									 gcmVal = value.gcmCODE.replace("/","_")
									 } else{
									 gcmVal = value.gcmCODE;
								 }*/
	            		 		
	            		 		
	            		 		$scope.selectededRolesArr.push({
	            		 			"rcId":dealRCId,
	            		 			"dealAutoTowerId":dealTowerId,
	     							"masterRoleLongDescription":value.masterRoleLongDescription,
	     				    		"masterRoleShortDescription":value.masterRoleShortDescription,
	     				    		"rcRoleId":value.roleId,
	     				    		"masterRoleId":value.masterRoleId,
	     				    		"syntelRoleId":value.syntelRoleId,
	     				    		"syntelRoleName":value.syntelRoleName,
	     				    		"bandGrade":value.bandGrade,
	     				    		"proficiencyLevelDescription":value.proficiencyLevelDescription,
	     				    		"proficiencyLevelId":value.proficiencyLevelId,
	     				    		"xOSkill":response.data,
	     				    		"xOSkillIndex":value.xoSkillIndex,
	     				    		"xOKnowledge":angular.copy($scope.knowledge_name),
	     				    		"xOKnowledgeIndex":value.xoKnowledgwIndex,
	     				    		"clientRole":value.clientRole,
	     				    		"comments":value.comments,
	     				    		"xoSkillName":value.xoSkillName,
	     				    		"xOSkillElementIndex":value.xoSkillElementIndex,
	     				    		"xoSkillElementName":value.xoSkillElementName,
	     				    		"xoKnowledgeName":value.xoKnowledgeName,
	     				    		"gcmCODE":value.gcmCODE,
	     				    		"gcmVal":gcmVal
	     						  });
	            		 	},function(err){
	            		 		console.log(err);
	            		 	});
					  }
					  
	 			});
				 console.log("Roles added are......");
				  console.log( $scope.selectededRolesArr);
				  
				  angular.forEach($scope.checkedItems, function(value,key,index) {
						if(value.chkBoxStatus){
							value.chkBoxStatus=false;
							$scope.splicedArray = $scope.checkedItems.splice(value.chkBoxStatus,1);
						}
					});
				  
				  $scope.checkedItems =[];
		    }
		    	
		    	else
		    		{
		    		 BootstrapDialog.show({
		    	        	title : 'Deal Creation - Role Selection',
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
		   
		    $scope.addSelectedRolesChange = function(){
		    	 $scope.rcRoleMasterheader=false;
		    	 
		    	 if($scope.checkedItems.length != 0) {
		    	//console.log( $scope.searchRoleDetailsResult);
				  angular.forEach($scope.searchRoleDetailsResultChange, function(value, key,index) {
					  if(value.chkBoxStatusChange){
						  //console.log(value);
						  // to uncheck the selected checkbox
						  value.chkBoxStatusChange=false;
						  //load xo skills based on subpractice id
						  WebServiceFactory.getXOSkillFromSubPracticeId(value.subPracticeId)
	            		 	.then(function(response){
	            		 		$scope.getXOSkillsElementMasternew(skillId);

	            		 		 //alert("the Index value is................ "+ $scope.index);
								 var gcmVal = [];
							/*	 if(value.gcmCODE.includes("/")){
									 gcmVal = value.gcmCODE.replace("/","_")
									 
									 } else{
									 gcmVal = value.gcmCODE;
								 }*/
	            		 		

	            		 		
	            		 		$scope.selectededRolesArr.push({
	            		 			"rcId":0,
	            		 			"dealAutoTowerId":dealTowerId,
	     							"masterRoleLongDescription":value.masterRoleLongDescription,
	     				    		"masterRoleShortDescription":value.masterRoleShortDescription,
	     				    		"rcRoleId":value.roleId,
	     				    		"masterRoleId":value.masterRoleId,
	     				    		"syntelRoleId":value.syntelRoleId,
	     				    		"syntelRoleName":value.syntelRoleName,
	     				    		"bandGrade":value.bandGrade,
	     				    		"proficiencyLevelDescription":value.proficiencyLevelDescription,
	     				    		"proficiencyLevelId":value.proficiencyLevelId,
	     				    		"xOSkill":response.data,
	     				    		"xOSkillIndex":value.xoSkillIndex,
	     				    		"xOSkillElement":value.xoSkillElementIndex,
	     				    		"xOKnowledge":angular.copy($scope.knowledge_name),
	     				    		"xOKnowledgeIndex":value.xoKnowledgwIndex,
	     				    		"xOSkillElementIndex":value.xoSkillElementIndex,
	     				    		"clientRole":value.clientRole,
	     				    		"comments":value.comments,
	     				    		"xoSkillName":value.xoSkillName,
	     				    		"xoSkillElementName":value.xoSkillElementName,
	     				    		"xoKnowledgeName":value.xoKnowledgeName,
	     				    		"gcmCODE":value.gcmCODE,
	     				    		'gcmVal':gcmVal
	     						  });
	            		 	},function(err){
	            		 		console.log(err);
	            		 	});
					  }
					  //console.log( $scope.selectededRolesArr);
	 			});
				  
				  angular.forEach($scope.checkedItems, function(value,key,index) {
						if(value.chkBoxStatusChange){
							value.chkBoxStatusChange=false;
							$scope.splicedArray = $scope.checkedItems.splice(value.chkBoxStatusChange,1);
						}
					});
				  
				  $scope.checkedItems =[];
				  $scope.selectAllCheckBoxChange = false;
		    	 }
		    	 else
		    		{
		    		 BootstrapDialog.show({
		    	        	title : 'Deal Creation - Role Selection',
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
		    
		    
		  //7.function to getting X0 skill Element master 
			$scope.getXOSkillsElementMaster = function(skillId,index)
			{
				var getXOSkillsElementMaster = function(response)
				{
					console.log("Skill Elements");
					$scope.selectededRolesArr[index].xOSkillElement=response.data;
				};
			
			WebServiceFactory.getXOSkillsElementMaster(skillId).then(getXOSkillsElementMaster);
			};
			
			$scope.getXOSkillsElementMasternew = function(skillId)
			{
				var getXOSkillsElementMasternew = function(response)
				{
					angular.forEach($scope.selectededRolesArr,function(value,key){
						if($scope.selectededRolesArr[key].xOSkillIndex==response.data[0].skillId)
							{
									$scope.selectededRolesArr[key].xOSkillElement=response.data;
							}
						
							});
					console.log("Skill Elements added");
					console.log($scope.selectededRolesArr);
					
				};
			
			WebServiceFactory.getXOSkillsElementMaster(skillId).then(getXOSkillsElementMasternew);
			};
			
		//8.delete rate card row
		    $scope.deleteRow = function(index){
				 $scope.selectededRolesArr.splice(index, 1);
			};
			
		//9.add Contractor Role Row
			$scope.addContractorRoleRow = function(){
				$scope.contractorRoleArr.push({
					"customerRole":$scope.fpdcRoleSelectionFrm.CustomerRoleModel,
					"comments":$scope.fpdcRoleSelectionFrm.CustomerCmtModel,
					"dealAutoTowerId":dealTowerId
				});
				/*$scope.fpdcRoleSelectionFrm.CustomerRoleModel=null;
				$scope.fpdcRoleSelectionFrm.CustomerCmtModel=null;*/
			};
		
		//10.remove Contractor Role
			$scope.removeCustomerRole=function(index){
				$scope.contractorRoleArr.splice(index,1);
			};
			
			
		
		$scope.save = function(fpdcRoleSelectionFrm){
			$scope.onSave=true;
			/*$scope.saveOrUpdateFpDealRole(fpdcRoleSelectionFrm);*/
			if(fpdcRoleSelectionFrm.$valid)
			{
				$scope.onSave= false;
				$scope.saveOrUpdateFpDealRole(fpdcRoleSelectionFrm);
				
				
			}
			
				
		
		}
		
		
		$scope.saveOrUpdateFpDealRole=function(fpdcRoleSelectionFrm)
		{
			
			if($scope.fpDealRoledetails!="" || $scope.selectededRolesArr.length!=0)
			{
				console.log("Deal version ID : " + $localStorage.rpDealVersionId);
				console.log("selectededRolesArr");
				console.log($scope.selectededRolesArr);
				var checkBoxVal = $scope.fpdcRoleSelectionFrm.masterRolechkbox;
				if(checkBoxVal==true)
				{
					$scope.checkBoxVal=1;
				}
				else
				{
					$scope.checkBoxVal=0;
				}
				var marker = {
						"dealVersionId":dealVersionId,
						"dealAutoTowerId":dealTowerId,//We are saving Tower_Id in Auto_Tower_Id column
						"isMasterRole":$scope.checkBoxVal,
						"dealRoles" : $scope.selectededRolesArr,
						"dealContractorRole" : $scope.contractorRoleArr
				};
				console.log(marker);
				var saveFPDealRoleSelectionAndContractorRole = function(response){
					
					if( response.status == 200){
						BootstrapDialog.show({
							title : 'Deal Roles Creation',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'DealRoles created/updated successfully.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									$window.location.reload();
									//window.location="RateCardCreationAddContractorRole";
								}
							} ]
						});
					}
					
				};
				WebServiceFactory.saveFPDealRoleSelectionAndContractorRole(marker).then(saveFPDealRoleSelectionAndContractorRole);
			}
			else
			{
				
				BootstrapDialog.show({
					title : 'Deal Creation - Role Selection',
					type : BootstrapDialog.TYPE_DANGER,
					message : 'No roles selected for Tower. Please select atleast 1 role to Save.',
					closable : false,
					buttons : [{
						label : 'OK',
						action : function(dialogRef) {
							dialogRef.close();
						}
					}]
				});
			}
		}
	
				
	
			
			var getDealTower = function(response) 
			{
				console.log("fp Deal Tower data ..............");
				console.log(response);
				$scope.towerdetails = response.data;
				if($scope.towerdetails!=undefined)
					{
					dealVersionId=$scope.towerdetails[0].dealVersionId;
					dealTowerId= $scope.towerdetails[0].dealTowerId;
					$scope.fpdcRoleSelectionFrm.dealRoleDetailsTowerModel=$scope.towerdetails[0].dealTowerId;
				 	$scope.getTowerCountryCity(dealTowerId);
					}
			
			};
			WebServiceFactory.getDealTower(dealVersionId).then(getDealTower);
			
			var getFpDealRoleDetails = function(response) 
			{
				console.log("fp Deal Role data ..............");
				console.log(response);
				$scope.fpDealRoledetails = response.data;
				
			};
			WebServiceFactory.getFpDealRoleDetails(dealVersionId).then(getFpDealRoleDetails);
			
			
			//5.function for getting Knowledge name
			var getKnowledgeName = function(response)
			{
				$scope.knowledge_name = response.data;
			};
			
			WebServiceFactory.getKnowledgeName().then(getKnowledgeName);

			$scope.myorder=function(rateCard){
				$scope.forder=rateCard;
			}
			
			$scope.myorder=function(currentRole){
				$scope.forder=currentRole;
			}
	
	}]);