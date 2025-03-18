//var app = angular.module('TMDealCreationRoleSelectionApp', ['ngMessages','ngStorage']);
app.controller("TMDealCreationRoleSelectionController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
	console.log("inside RateCardCreationController");

	$scope.frmDealProjectDetails={};
	$scope.isSubmitDisable = false;
	//$scope.masterroleContract=false;
	$scope.masterroleMaster=false;
	$scope.masterroleKPO=false;
	$localStorage.IsAtos=0;
	var userType = sessionStorage.getItem('userType');
	//	$scope.currentRpVersionId = $localStorage.rpDealVersionId;
	var rpVrsId = $localStorage.rpDealVersionId

	$scope.currentcrmDealId = $sessionStorage.crmDealId;			
	var abc=$localStorage.DealModel;

	$scope.fpdcRoleSelectionFrm={};
	//page load functionality
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
		window.location='TMDealCreationRateCardAndProjectDetails';
	};
	$scope.Next = function()
	{ 
		$sessionStorage.varCheck = 0;
		$sessionStorage.sele2stfng = 0;
		console.log("Click on next----page 1");
		console.log($scope.contryList);
		$sessionStorage.ContryList = $scope.contryList;		   
		$sessionStorage.CityList = $sessionStorage.CityList;
		$sessionStorage.citiId2staffing = $sessionStorage.CityList[0].cityId;
		$sessionStorage.rpDealVersionId = $localStorage.rpDealVersionId;
		$sessionStorage.dealTowerId = $sessionStorage.dealTower_Id;
		//End
		window.location='TMDealCreationStaffing';
	};  


	/*function Main($scope) {
	    	  $scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
	    	}*/

	//arvind
	$window.onload =function()
	{
		/*$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';*/
		//alert('hello window load');
		//var rcId = $sessionStorage.rcId;
		//alert('hello rcId'+rcId);
	}
	//end

	/*local storage data*/
	//Start Added by khyati
	console.log("page 2");
	$scope.contryList = $sessionStorage.ContryList;
	console.log($scope.contryList);
	//End
	var dealVersionId=$localStorage.rpDealVersionId;    //need to load from local storage


	var getVersionData = function(response) {
		console.log("get versionDetails Data..");
		console.log(response);
		$scope.getVersionData=response.data;
		
		if($scope.getVersionData!=undefined)
		{
			$localStorage.pageTracker=$scope.getVersionData[0].pageTrackerStatus;
			$localStorage.CurrentApprovalStatus=$scope.getVersionData[0].currentApprovalStatus;
			if(userType == 'Delivery')
				{
				if(($localStorage.CurrentApprovalStatus == null || $localStorage.CurrentApprovalStatus == 1 ||
						$localStorage.CurrentApprovalStatus == 4 ) && $localStorage.pageTracker<2)
					{	BootstrapDialog.show({
						title : 'T&M Deal Creation - Role Selection',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please save on the previous screen to proceed ahead",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(
									dialogRef) {
								dialogRef.close();
								if($localStorage.pageTracker==1)
									{
										window.location="TMDealCreationRateCardAndProjectDetails";
									}
								else
									{
										window.location="TMDealCreationDetails";
									}
								
							}
						} ]
					});	
						
					}
				
			}
			
				}
			
		
	};
	WebServiceFactory.getVersionData(dealVersionId).then(getVersionData);

	if(userType == 'GFT' || userType == 'CEO' || userType == 'CDO'|| userType=='RiskManagers')
	{
		var getGFTCustomer = function(response) {
			console.log("customer response");
			//console.log(response.data);
			$scope.customer = response.data;
			console.log("customer::::::::::::::::::");
			console.log(response.data);
			$sessionStorage.customerData = response.data;
		};
		WebServiceFactory.getGFTCustomer().then(getGFTCustomer);
	}
	else if(userType == 'BHU' || userType == 'DUH')
	{
		//Local storage data
		$scope.verticalId="";
		var getVerticalApproverData = function(response) 
		{
			$scope.verticalDetails = response.data;
			if($scope.verticalDetails!=undefined);
			{
				angular.forEach($scope.verticalDetails, function(value,key) {
					if($scope.user != null) {
						if($scope.user == value.deliveryHeadId || $scope.user == value.buHeadId) {
							$scope.verticalId=value.verticalId;
							$scope.getDUHCustomerVerticalMapping($scope.verticalId);
						}
					} else {
						if($sessionStorage.currentUser == value.deliveryHeadId || $sessionStorage.currentUser == value.buHeadId) {
							$scope.verticalId=value.verticalId;
							$scope.getDUHCustomerVerticalMapping($scope.verticalId);
						}
					}
				});
			}
		};
		WebServiceFactory.getVerticalApproverData().then(getVerticalApproverData);
		/*var getCustomerByVerticalGroupId = function(response) {				
					$scope.customer = response.data;
					$sessionStorage.customerData = response.data;

				};
				WebServiceFactory.getCustomerByVerticalGroupId().then(getCustomerByVerticalGroupId);*/
	}
	else
	{
		var getCustomerForUser = function(response) { 			
			$scope.customer = response.data;
			console.log("customer::::::::::::::::::");
			console.log(response.data);
			$sessionStorage.customerData = response.data;

		};
		WebServiceFactory.getCustomerForUser().then(getCustomerForUser);
	}









	/*local scope variables*/
	$scope.rateCards=[];
	$scope.searchRoleDetailsResult=[];
	$scope.selectededRolesArr=[];
	$scope.contractorRoleArr=[]; 

	var getDealDetails = function(response) {
		console.log("Deal Details...");

		console.log(response);
		$scope.dealDetails = response.data;
		$sessionStorage.clientRcId=$scope.dealDetails[0].clientRcId;

		var statusId = $scope.dealDetails[0].currentApprovalStatus;			          
		if((statusId == null || statusId == 1 || statusId == 4) && userType == 'Delivery')
		{			
			$scope.isSubmitDisable = false;
		}
		else
		{
			$scope.isSubmitDisable = true;
		}

		
	

		/*console.log("Approver Data")
			  		console.log($scope.dealDetails);
			  		console.log($scope.dealDetails[0].dealId);
			  		console.log($scope.dealDetails[0].customerId)
			  		console.log($scope.dealDetails[0].dealStartDate);
			  		console.log($scope.dealDetails[0].dealEndDate);
			  		console.log($scope.dealDetails[0].dealDescription);
			  		console.log($scope.dealDetails[0].dealStatus)
			  		console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
			  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");*/
		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType==1)? "Development" : "Maintenance";
		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId==1)? "Fixed Price" :"T & M";
		$sessionStorage.industryType=$scope.dealDetails[0].projectIndustry;

		/*if($localStorage.IsAtos!=1)
			  			{
				  			if($sessionStorage.industryType==0)
				  			{
				  				$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
				  				$scope.masterroleContract=false;
				  				$scope.masterroleKPO=true;
				  			}
				  		else
				  			{
				  				$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
				  				$scope.masterroleContract=true;
				  				$scope.masterroleKPO=false;
				  				$scope.masterroleMaster=false;
				  			}

			  			}
			  		else
			  			{
			  			if($sessionStorage.industryType==0)
			  			{
			  				$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
			  				$scope.masterroleContract=false;
			  				$scope.masterroleKPO=true;
			  				$scope.masterroleMaster=false;
			  			}
			  		else
			  			{
			  				$scope.fpdcRoleSelectionFrm.searchModel = '';
			  				$scope.masterroleMaster=true;
			  				$scope.masterroleContract=false;
			  				$scope.masterroleKPO=false;
			  			}
			  			}*/



		/*if ($sessionStorage.industryType == 0) {
			$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
			$scope.masterroleContract = false;
			$scope.masterroleKPO = true;
		} else {
			$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
			$scope.masterroleContract = true;
			$scope.masterroleKPO = false;
			$scope.masterroleMaster = false;
		}*/




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

	WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails);//WebServiceFactory.getDealDetails($sessionStorage.crmDealId).then(getDealDetails);   	

	/*custom functions*/
	//2.function to get Rate Card Details
	var tempCopy=0; //a
	var rcId_value=0;
	/*$scope.getRateCardDetails = function(rcId)
			{
				//alert('rcId=='+rcId);
				tempCopy=1; //a
				rcId_value=rcId;
				var getRateCardDetails = function(response,rateCard)
				{
					$scope.searchRoleDetailsResult=[];
					//$scope.fpdcRoleSelectionFrm.searchModel=null;

					if($sessionStorage.industryType==0)
		  			{
		  				$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
		  				$scope.masterroleContract=false;
		  				$scope.masterroleKPO=true;
		  			}
		  		else
		  			{
		  				$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
		  				$scope.masterroleContract=true;
		  				$scope.masterroleKPO=false;
		  			}

					console.log(response.data);
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
			};*/



	$scope.getTandMRateCardDetails = function(rcId)
	{	$scope.Roleheader=false;
	$scope.searchRoleDetailsResult=[];
	var getRateCardDetails = function(response)
	{
		$scope.getRateCardDetails=response.data;

		$scope.fpdcRoleSelectionFrm.searchModel=null;
		console.log("Rate Card Details after selection");
		console.log(response.data);



		//console.log(dealRCId);


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

	//4. function to get data on search click 
	/*    $scope.searchRateCardData = function()
			{
		    	tempCopy=0; 				
		    	$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
		    	if($sessionStorage.industryType==0)
	  			{
	  				$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
	  				$scope.masterroleContract=false;
	  				$scope.masterroleKPO=true;
	  			}
	  		else
	  			{
	  				$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
	  				$scope.masterroleContract=true;
	  				$scope.masterroleKPO=false;
	  			}
		    	var searchRoles = $scope.fpdcRoleSelectionFrm.searchModel; 
				console.log( $scope.fpdcRoleSelectionFrm.searchModel);

				var searchRateCardData = function(response)
				{
				  $scope.fpdcRoleSelectionFrm.rateCardModel=null;
				  $scope.searchRoleDetailsResult=[];
				  //console.log(response.data);

				  //console.log( $scope.searchRoleDetailsResult);
				  angular.forEach(response.data, function(value, key) {
	 					//console.log(value);
	 					//console.log("Added");
					  $scope.addSearchedResultRowInArray(value);
	 				});
				  //console.log( $scope.searchRoleDetailsResult);


				  if($scope.fpdcRoleSelectionFrm.searchModel != null) {
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
				  } else {
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

			  }
				WebServiceFactory.searchRateCardData(searchRoles).then(searchRateCardData);
		    };*/



	/*$scope.searchRateCardData = function()
	{	
		$scope.searchRoleDetailsResult=[];
		$scope.searchRoleDetailsResultChange=[];
		tempCopy=0; 				
		//$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
		if($localStorage.IsAtos!=1)
			{
				if($sessionStorage.industryType==0)
				{
					$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
					$scope.masterroleMaster=false;
					$scope.masterroleKPO=true;
				}
				else
				{
					$scope.fpdcRoleSelectionFrm.searchModel = '';
					$scope.masterroleMaster=true;
					$scope.masterroleKPO=false;
				}
			}
		else
		{
		if($sessionStorage.industryType==0)
		{
			$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
			//$scope.masterroleContract=false;
			$scope.masterroleKPO=true;
			$scope.masterroleMaster=false;
		}
	else
		{
			$scope.fpdcRoleSelectionFrm.searchModel = '';
			$scope.masterroleMaster=true;
			//$scope.masterroleContract=false;
			$scope.masterroleKPO=false;
		}
		}

		var searchRoles = $scope.fpdcRoleSelectionFrm.searchModel; 
		console.log( $scope.fpdcRoleSelectionFrm.searchModel);

		var searchRateCardData = function(response)
		{

			var searchRoles = $scope.fpdcRoleSelectionFrm.searchModel;
			console.log( $scope.fpdcRoleSelectionFrm.searchModel);
			if(searchRoles!=null)
			{	
				var searchRateCardData = function(response)
				{
					$scope.RoleMasterheader=false;
					$scope.fpdcRoleSelectionFrm.searchModel=null;
					console.log("Available Roles");
					console.log(response);
					$scope.searchRoleDetailsResultChange=[];
					//console.log(response.data);

					//console.log( $scope.searchRoleDetailsResult);
					angular.forEach(response.data, function(value, key) {
						//console.log(value);
						//console.log("Added");
						$scope.addSearchedResultRowInArray(value);
					});
					//console.log( $scope.searchRoleDetailsResult);


					if($scope.fpdcRoleSelectionFrm.searchModel != null) {
						if($scope.searchRoleDetailsResult.length==0)
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
		}
		WebServiceFactory.searchRateCardData(searchRoles).then(searchRateCardData);
	};*/


	$scope.searchRateCardData = function(data)
	{	
		//alert(data);
		if(data.length !=0){
			//alert(data.length);
		$scope.searchRoleDetailsResult=[]; 
		$scope.searchRoleDetailsResultChange=[];
		tempCopy=0; 				
		//$scope.fpdcRoleSelectionFrm.searchModel = 'contractor';
		/*if($localStorage.IsAtos!=1)
			{
				if($sessionStorage.industryType==0)
				{
					$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
					$scope.masterroleMaster=false;
					$scope.masterroleKPO=true;
				}
				else
				{
					$scope.fpdcRoleSelectionFrm.searchModel = '';
					$scope.masterroleMaster=true;
					$scope.masterroleKPO=false;
				}
			}
		else
		{
		if($sessionStorage.industryType==0)
		{
			$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
			//$scope.masterroleContract=false;
			$scope.masterroleKPO=true;
			$scope.masterroleMaster=false;
		}
	else
		{
			$scope.fpdcRoleSelectionFrm.searchModel = '';
			$scope.masterroleMaster=true;
			//$scope.masterroleContract=false;
			$scope.masterroleKPO=false;
		}
		}
*/
		var searchRoles = $scope.fpdcRoleSelectionFrm.searchModel; 
		console.log( $scope.fpdcRoleSelectionFrm.searchModel);

		var searchRateCardData = function(response)
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
						$scope.addSearchedResultRowInArray(value);
					});
					//console.log( $scope.searchRoleDetailsResult);


					if($scope.fpdcRoleSelectionFrm.searchModel != null) {
						if($scope.searchRoleDetailsResult.length==0)
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




	//
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
	//







	//6. add selected roles on click of add button
	$scope.addSelectedRoles = function(){
		if(tempCopy==1)
		{
			//alert ('Copy Roles from another Rate Card='+$scope.searchRoleDetailsResult[0].masterRoleId);
			//alert(rcId_value);
			angular.forEach($scope.searchRoleDetailsResult, function(value, key,index) {
				if(value.chkBoxStatus){
					//console.log(value);
					// to uncheck the selected checkbox
					value.chkBoxStatus=false;
					//load xo skills based on subpractice id
					//WebServiceFactory.getXOSkillFromSubPracticeId(value.subPracticeId)

					//WebServiceFactory.getKnowledgeName_saved(value.masterRoleId,rcId_value).then(getKnowledgeName2)

					WebServiceFactory.getXOSkillFromSubPracticeId2(value.subPracticeId,value.masterRoleId,rcId_value)
					.then(function(response){
						if(value.xoSkillIndex!=undefined)
						{
							$scope.getXOSkillsElementMasternew(value.xoSkillIndex);
						}
						else
						{
							value.xoSkillElementIndex=null
						}

						//push current data into selectededRolesArr []
						/*var getKnowledgeName2 = function(response)
											{
												$scope.knowledge_name2 = response.data;
												$scope.knowledge_name =$scope.knowledge_name2;
											};*/	
						//WebServiceFactory.getKnowledgeName_saved(value.masterRoleId,rcId_value).then(getKnowledgeName2);
						
						
						var gcmVal = [];
						/*if(value.gcmCODE != null ||value.gcmCODE != undefined){
						 if(value.gcmCODE.includes("/")){
							 gcmVal = value.gcmCODE.replace("/","_")
							 
							 } else{
							 gcmVal = value.gcmCODE;
						 }
						}*/
						$scope.selectededRolesArr.push({
							"rcId":0,
							"masterRoleLongDescription":value.masterRoleLongDescription,
							"masterRoleShortDescription":value.masterRoleShortDescription,
							"masterRoleId":value.masterRoleId,
							"syntelRoleId":value.syntelRoleId,
							"syntelRoleName":value.syntelRoleName,
							"bandGrade":value.bandGrade,
							"proficiencyLevelDescription":value.proficiencyLevelDescription,
							"proficiencyLevelId":value.proficiencyLevelId,
							"xOSkill":response.data,
							"xOSkillIndex":value.xoSkillIndex,
							"xOSkillElement":[],
							"xOSkillElementIndex":value.xoSkillElementIndex,
							"xOKnowledge":angular.copy($scope.knowledge_name), //"xOKnowledge":angular.copy($scope.knowledge_name2),  
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
				//console.log( $scope.selectededRolesArr);
			});			
			//tempCopy=0;
			console.log( $scope.selectededRolesArr);
		}
		else
		{

			// alert ('search');
			//console.log( $scope.searchRoleDetailsResult);
			angular.forEach($scope.searchRoleDetailsResult, function(value, key,index) {
				if(value.chkBoxStatus){
					//console.log(value);
					// to uncheck the selected checkbox
					value.chkBoxStatus=false;
					//load xo skills based on subpractice id
					WebServiceFactory.getXOSkillFromSubPracticeId(value.subPracticeId)
					.then(function(response){
						//push current data into selectededRolesArr []
						if(value.xoSkillIndex!=undefined)
						{
							$scope.getXOSkillsElementMasternew(value.xoSkillIndex);
						}
						else
						{
							value.xoSkillElementIndex=null
						}
						
						
						var gcmVal = [];
						/*  if(value.gcmCODE.includes("/")){
							 gcmVal = value.gcmCODE.replace("/","_")
							 
							 } else{
							 gcmVal = value.gcmCODE;
						 }*/
						$scope.selectededRolesArr.push({
							"rcId":0,
							"masterRoleLongDescription":value.masterRoleLongDescription,
							"masterRoleShortDescription":value.masterRoleShortDescription,
							"masterRoleId":value.masterRoleId,
							"syntelRoleId":value.syntelRoleId,
							"syntelRoleName":value.syntelRoleName,
							"bandGrade":value.bandGrade,
							"proficiencyLevelDescription":value.proficiencyLevelDescription,
							"proficiencyLevelId":value.proficiencyLevelId,
							"xOSkill":response.data,
							"xOSkillIndex":value.xoSkillIndex,
							"xOSkillElement":[],
							"xOSkillElementIndex":value.xoSkillElementIndex,
							"xOKnowledge":angular.copy($scope.knowledge_name), //"xOKnowledge":angular.copy($scope.knowledge_name2),  
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
				//console.log( $scope.selectededRolesArr);
				console.log( $scope.selectededRolesArr);
			});				 

		} 

	};



	//7.function to getting X0 skill Element master 
	$scope.getXOSkillsElementMaster = function(skillId,index)
	{
		var getXOSkillsElementMaster = function(response)
		{
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
	/*$scope.addContractorRoleRow = function(){
		$scope.contractorRoleArr.push({
			"customerRole":$scope.fpdcRoleSelectionFrm.CustomerRoleModel,
			"comments":$scope.fpdcRoleSelectionFrm.CustomerCmtModel
		});
		$scope.fpdcRoleSelectionFrm.CustomerRoleModel=null;
		$scope.fpdcRoleSelectionFrm.CustomerCmtModel=null;
	};*/

	//10.remove Contractor Role
	$scope.removeCustomerRole=function(index){
		$scope.contractorRoleArr.splice(index,1);
	};

	//11.Save or update deal roles
	$scope.saveOrUpdateDealRole = function(fpdcRoleSelectionFrm){
		console.log("Deal version ID : " + $localStorage.rpDealVersionId);
		console.log("selectededRolesArr");
		console.log($scope.selectededRolesArr);
		var marker = {
				"dealVersionId":dealVersionId,
				"dealRoles" : 	$scope.selectededRolesArr,
				"dealContractorRole" : $scope.contractorRoleArr
		};
		console.log(marker);
		var saveTMDCRoleSelectionAndContractorRole = function(response){

			if( response.status == 200){
				BootstrapDialog.show({
					title : 'Rate Card Roles Creation',
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
		WebServiceFactory.saveTMDCRoleSelectionAndContractorRole(marker).then(saveTMDCRoleSelectionAndContractorRole);
	}
	/*on load WS call*/

	//1.function to getRateCard
	//alert('rpVrsId='+rpVrsId);
	var getRateCardDeal = function(response) 
	{
		console.log("rate card data ..............");
		console.log(response);
		$scope.rateCards = response.data;
		
		for (var i = 0, len = $scope.rateCards.length; i < len; i++) {
			  if ($scope.rateCards[i].isAtos == 1) {
			    console.log('IS Atos RC'); 
			    $localStorage.IsAtos=1;
			    break;
			  }
		}
		
		
		
		if($localStorage.IsAtos!=1)
			{
  			if($sessionStorage.industryType==0)
  			{
  				$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
  				$scope.masterroleMaster=false;
  				$scope.masterroleKPO=true;
  			}
  		else
  			{
  				$scope.fpdcRoleSelectionFrm.searchModel = '';
  				//$scope.masterroleContract=true;
  				$scope.masterroleKPO=false;
  				$scope.masterroleMaster=true;
  			}

			}
		else
			{
			if($sessionStorage.industryType==0)
			{
				$scope.fpdcRoleSelectionFrm.searchModel = 'KPO';
				//$scope.masterroleContract=false;
				$scope.masterroleKPO=true;
				$scope.masterroleMaster=false;
			}
		else
			{
				$scope.fpdcRoleSelectionFrm.searchModel = '';
				$scope.masterroleMaster=true;
				//$scope.masterroleContract=false;
				$scope.masterroleKPO=false;
			}
			}
	};
	WebServiceFactory.getRateCardDeal(rpVrsId).then(getRateCardDeal);

	//5.function for getting Knowledge name
	var getKnowledgeName = function(response)
	{
		$scope.knowledge_name = response.data;
	};

	WebServiceFactory.getKnowledgeName().then(getKnowledgeName);

	//f() to get auto knwledge name

	/*var getKnowledgeName2 = function(response)
			{
				$scope.knowledge_name2 = response.data;
			};

			WebServiceFactory.getKnowledgeName_saved(masterRoleId,rcId_value).then(getKnowledgeName2);*/

	//12.load previous data on page load
	var getPersistDealDetails =function(response)
	{
		console.log("Existing Rate cards.................")
		console.log(response.data);
		if(response.data.dealContractorRole){
			angular.forEach(response.data.dealContractorRole,function(value,key){
				$scope.contractorRoleArr.push({
					"customerRole":value.customerRole,
					"comments":value.comments
				});
			})
		};
		if(response.data.persistedDealRoles){
			var index=0;
			angular.forEach(response.data.persistedDealRoles,function(value,key)
					{
				console.log("index : " + index );
				//$scope.secondTable=response.data;
				WebServiceFactory.getXOSkillFromSubPracticeId(value.subPracticeId)
				.then(function(response){
					//get XO skills from skill id
					if(value.xOSkillIndex)
					{
						$scope.getXOSkillsElementMasternew(value.xOSkillIndex);
					}
					else{
						value.xOSkillElementIndex=null
					}

					index=index+1;
					console.log("index : " + index );
					console.log(value);
					console.log("-----------------------------------------------------------------------------");
					//push current data into selectededRolesArr []
					var gcmVal = [];
					 /*if(value.gcmCODE.includes("/")){
						 gcmVal = value.gcmCODE.replace("/","_")
						 
						 } else{
						 gcmVal = value.gcmCODE;
					 }*/
					
					$scope.selectededRolesArr.push({
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
						"gcmCODE":value.gcmCODE,
						"gcmVal":gcmVal
					});


				},function(err){	
					console.log(err);
				});


					});
		}
		console.log("nnnnnnnnnnnnnnnnnnnnnnnnnnnn");
		console.log($scope.selectededRolesArr);
	};

	WebServiceFactory.getPersistDealRoledDetails(dealVersionId).then(getPersistDealDetails);

	/*var getVersionData =function(response)
	    	{
	    		console.log("Version Data");
	    		console.log(response);
	    		$scope.versionDetails = response.data;
	    		$sessionStorage.isNewDeal=$scope.versionDetails[0].isNewDeal;
	    		if($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||  $scope.versionDetails[0].currentApprovalStatus == 4)
    			{
	    		if($scope.isStaffingComplete==1)
	    			{
	    				$scope.isSaveDisabled=false;	
	    			}
	    		else
	    			{
	    				$scope.isSaveDisabled=true;	
	    			}

    			}
    		else
    			{
    			    $scope.isSaveDisabled=true;
    			}
	    		if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId==1)
	    			{
	    				$scope.versionDetails[0].dealStatus='Open';
	    			}
	    		else
	    			{
	    				$scope.versionDetails[0].dealStatus='Close';
	    			}

	    		if($scope.versionDetails[0].fpProjectTypeId==1)
	    			{
	    				$scope.versionDetails[0].projectType='Development - Fixed Price';
	    				$scope.masterRole=true;
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==2)
	    			{
	    				$scope.versionDetails[0].projectType='Development - Manage Capacity';
	    				$scope.masterRole=false;
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==3)
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Fixed Price';
	    				$scope.masterRole=true;
	    			}
	    		else
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Manage Capacity';
	    				$scope.masterRole=false;
	    			}

	    		if($scope.versionDetails[0].currentApproverId == userType) {
                	$scope.GFTApprover = true;	
                }

	    		$scope.whatifDeal.approverCommentModel=$scope.versionDetails[0].approverComments;
	    		 $sessionStorage.verticalID = $scope.versionDetails[0].verticalId;
	 	 	 	$scope.getVerticalDetails($sessionStorage.verticalID);
	    		$scope.getFPDealSummaryDetails($scope.user);
	    	};
	    	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);*/


	$scope.exportToExcel=function(tableId){
		//	$scope.xoSkillName=fpdcRoleSelectionFrm
		var exportHref=WebServiceFactory.tableToExcel(tableId,"T&MDeal_Creation_Role_Selection");
	}
	$scope.myorder=function(rateCard){
		$scope.forder=rateCard;
	}
	
	$scope.myorder=function(currentRole){
		$scope.forder=currentRole;
	}

}]);