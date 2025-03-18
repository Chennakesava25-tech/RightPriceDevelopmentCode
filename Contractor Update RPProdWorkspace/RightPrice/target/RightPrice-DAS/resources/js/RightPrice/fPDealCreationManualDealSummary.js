
//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("FPDealCreationManualDealSummaryController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 console.log("inside FPDealCreationManualDealSummaryController");
		 var contextPath = "/RightPrice-DAS";	
		 var countryId=0;
		 var cityId=0;
		 var dealAutoTowerId=0;
		 var skillId=0;
		 var KnowledgeIndex=0;
		 var SkillElementIndex=0;
		 $scope.DisplayRows=false;
		 $scope.isSubmitToGFtHide = false;
		 $scope.isReadyToSubmitHide = true;
		 $scope.GFTApprover = false;
		 $scope.isFPDealRiskManagers = false;
	/*	 $scope.iscomment=true;*/
		 $scope.isApprovercomment=true;
		 $scope.isSaveHide = true;
		 var verticalID=0;
		 $scope.isRecycleBtn=false;
		 $scope.statusIndicator = null;
		 $scope.rainbowApprovalPanel = true;
		 var userType = sessionStorage.getItem('userType');
		 $scope.proxyUserId = $sessionStorage.proxyId;
		 console.log("the proxy Id from the Dashboard is............ ");
		 console.log($scope.proxyUserId);
		 console.log("The Fp Manual Summary Details...... "+userType);
		 $scope.rainbowApprovalLevel = [{id: 5,name: "Level 1"}, { id: 6,name: "Level 2" }];
		 $scope.sheetType = [{ name: "Costing", id: 1 }, { name: "Staffing", id: 2 }, { name: "Summary", id: 3 }, { name: "Other", id: 4 }];

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
	    	//Start Added by khyati
	    	console.log("Click on next page");
	    	console.log($scope.contryList);
	    	console.log($sessionStorage.CityList);
	    	$sessionStorage.ContryList = $scope.contryList;
	    	$sessionStorage.CityList = $sessionStorage.CityList;
	    	$localStorage.rpDealVersionId = $localStorage.rpDealVersionId;
	    	//End
	    	//  window.location='FPDealCreationStaffing';
	    	// window.location='FPDealCreationAddContractorRole'; removing 
	    	window.location = 'FPManualDealCreationApproval';
	    	
	    };   
	    
	   
	    $scope.getTowerCountryCity = function (fpdTowervalue) {
	    	$scope.searchRoleDetailsResult=[];
	    	$scope.selectededRolesArr=[];
	    	$scope.contractorRoleArr=[];
	    	angular.forEach($scope.towerdetails, function(value, key) {
				 if(fpdTowervalue.towerName == $scope.towerdetails[key].towerName)
					 {
					 	
					 	countryId=$scope.towerdetails[key].countryId
					 	cityId=$scope.towerdetails[key].cityId;
					 	dealAutoTowerId=$scope.towerdetails[key].dealautoTowerId;
					 	$localStorage.dealAutoTowerId=dealAutoTowerId;
					 	
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
						 
						 }
					});
	     	
	     	};
	     	WebServiceFactory.getCities(countryId).then(getCities);
	     	
	        $scope.searchFile = function(dealAutoTowerId,rpDealVersionId)
			{
				angular.element("input[type='file']").val(null);
				console.log("inside File Search---"+rpDealVersionId);

				var markers = {
							"rpDealVersionId" : rpDealVersionId,
							"dealAutoTowerId" : dealAutoTowerId
						};
				$http({
				    method: 'POST',
				    url: contextPath+'/RightPrice-DAS/getAttachmentData',
				    dataType: 'json',
				    data: JSON.stringify(markers),  
			     headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				    }).
					    then(function(data) {
					    	if(data.data.length!=0){
								$scope.tableHide=false;
								$scope.temp=[];
								$scope.temp=data.data;
								console.log("temp")
								console.log($scope.temp)
								var j;
								for(var i=0,j=0;i<$scope.temp.length;i++)
								{
									if($scope.temp[i])
									{
										$scope.MDAttachementData[j] = $scope.temp[i];
										var strMain = $scope.MDAttachementData[j].createdOn;
									    var arrSplit = [];
									    arrSplit = strMain.split(" ");
									    $scope.MDAttachementData[j].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
										j++;
									}	
								}
								console.log("$scope.MDAttachementData")
								console.log($scope.MDAttachementData)
						
								
							}
							else{
								$scope.tableHide=true;
							}
					    	
					    	
					    });
				
			}
	    }
	  
	    
	  
	  /*local storage data*/
	    $scope.contryList =$sessionStorage.ContryList;
	    console.log("Coutry list from session---");
	    console.log($scope.contryList);
	    var dealVersionId=$localStorage.rpDealVersionId;    //need to load from local storage
	    
		
	    	var getVersionData =function(response)
	    	{
	    		console.log("Version Data");
	    		console.log(response);
	    		$scope.versionDetails = response.data;
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
	    		
	    		$localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
	    		$localStorage.projectIndustry=$scope.versionDetails[0].projectIndustry;
	    		if(userType == 'Delivery') {
	    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
	    				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<2)
	    			{
	    			BootstrapDialog.show({
						title : 'FP Deal Creation - Manual Deal Summary',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'Data is not saved at previous screen.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								//$window.location.reload();
								if($localStorage.pageTracker==1)
									{
									window.location="FPDealCreationRateCardAndProjectDetails";
									}
								else
									{
									window.location="FPDealCreationDetails";
									}
								
							}
						} ]
					});
	    			}
	    		}
	    		$scope.fpdcRoleSelectionFrm.fpDealManualOnsitePer=$scope.versionDetails[0].expectedOnsitePercentage;
	    		$scope.fpdcRoleSelectionFrm.fpDealManualOffshorePer=$scope.versionDetails[0].expectedOffshorePercentage;
	    		$scope.fpdcRoleSelectionFrm.fpDealManualRevenue=$scope.versionDetails[0].estimatedRevenue;
	    		$scope.fpdcRoleSelectionFrm.fpDealDirectCostper=$scope.versionDetails[0].directCost;
	    		$scope.fpdcRoleSelectionFrm.fpDealManualGrossMargin=$scope.versionDetails[0].gmPercentage*100;
	    		$scope.fpdcRoleSelectionFrm.fpDealManualProSpecCost=$scope.versionDetails[0].projectSpecificCost;
	    		$scope.fpdcRoleSelectionFrm.fpDealManualProSpecAgileCost=$scope.versionDetails[0].projectSpecificCostPlusAgile;
	    		$scope.fpdcRoleSelectionFrm.fpManualDealGMperAfterPSC=$scope.versionDetails[0].gmAfterProjectSpecificCost*100;
	    		$scope.fpdcRoleSelectionFrm.fpManualDealVolDisc=$scope.versionDetails[0].volumeDiscountPercent;
	    		$scope.fpdcRoleSelectionFrm.fpManualDealPen=$scope.versionDetails[0].penaltyPercentage;
	    		$scope.fpdcRoleSelectionFrm.ManualDealGmAfterVolDis=$scope.versionDetails[0].gmAfterVolumeDiscount*100;
	    		
	    		$scope.fpdcRoleSelectionFrm.approverCommentModel=$scope.versionDetails[0].approverComments;
	    		
	    		$scope.fpdcRoleSelectionFrm.fpManualDealProjectmargin=$scope.versionDetails[0].projectMargin;	
	    		$scope.fpdcRoleSelectionFrm.ManualDealPMinclrisk=$scope.versionDetails[0].pminclrisk*100;	 
	    		$scope.fpdcRoleSelectionFrm.fpManualDealProjectmargininclRiskandVD=$scope.versionDetails[0].pminclRiskVD*100;


	    		
	    		if($scope.versionDetails[0].currentApproverId == userType) {
                	$scope.GFTApprover = true;	
                }
                /*if($scope.versionDetails[0].currentApproverId==$scope.user)
		    			{
                			$scope.isApprovercomment = false;
		    				}
                else
		    			{
                		$scope.isApprovercomment = true;
		    			}*/
	    		
	    		
	    		
	    		 
	    			if(userType == "GFT") {
	    				$scope.isSubmitToGFtHide = true;	
	    			}
	    		
	    			else if($scope.versionDetails[0].currentApprovalStatus == 1 || $scope.versionDetails[0].currentApprovalStatus == 4)
	    			{
	    				$scope.isSubmitToGFtHide = false;
	    			}
	    			else
	    			{
	    				$scope.isSubmitToGFtHide = true;		
	    		 }
	    		  
	 	 	 $sessionStorage.verticalID = $scope.versionDetails[0].verticalId;
	 	 	$scope.getVerticalDetails($sessionStorage.verticalID);
	 	 	 $scope.getManualDealSummaryDetails($scope.user);
	 	 	
	    	};
	    	WebServiceFactory.getVersionData(rpDealVersionId).then(getVersionData);
	    	
	    	
	    	
	    	 $scope.getManualDealSummaryDetails = function(user) {
	    		  if(user.length>10){
	    			var base64key = "QmFyMTIzNDVCYXIxMjM0NQ==";
	    			var parsedBase64Key = CryptoJS.enc.Base64
	    					.parse(base64key);
	    			var ive = CryptoJS.enc.Utf8
	    					.parse('RandomInitVector');
	    			var encrypted = CryptoJS.AES.decrypt(
	    					user, parsedBase64Key, {
	    						iv : ive
	    					});
	    			
	    			var decryptedText = encrypted
	    					.toString(CryptoJS.enc.Utf8);
	    			user=decryptedText;
	    		  }
	    		 $scope.getDelicgateUserAccess(user);
	 	 		 $scope.user=user;
	 	 		 if($scope.versionDetails!=undefined)
	 	 		 { 
	 	 			 if($scope.versionDetails[0] != undefined) {
	 	 			 if($scope.versionDetails[0].currentApproverId== $scope.user)
	 	 			 {
	 	 				 $scope.isApprovercomment=true;
	 	 			 }
	 	 			else if($scope.proxyUserId!=undefined){
	 	 				if($scope.proxyUserId != null) {
	 	 					if($scope.versionDetails[0].currentApproverId == $scope.proxyUserId){
	 	 						$scope.isOldApprovercomment=false;
				    		 $scope.isApprovercomment=true;
				    	}  else if($scope.GFTApprover == true) {
				    		$scope.isOldApprovercomment=false;
		 	 				 $scope.isApprovercomment=true;
		 	 			 }	
		 	 			 else {
		 	 				$scope.isOldApprovercomment=true;
		 	 				 $scope.isApprovercomment=false;
		 	 			 }
	 	 				}
	 	 				else if($scope.GFTApprover == true) {
	 	 					$scope.isOldApprovercomment=false;
		 	 				 $scope.isApprovercomment=true;
		 	 			 }	
		 	 			 else {
		 	 				$scope.isOldApprovercomment=true;
		 	 				 $scope.isApprovercomment=false;
		 	 			 }
				    } 
	 	 			 else if($scope.GFTApprover == true) {
	 	 				$scope.isOldApprovercomment=false;
	 	 				 $scope.isApprovercomment=true;
	 	 			 }	
	 	 			 else {
	 	 				$scope.isOldApprovercomment=true;
	 	 				 $scope.isApprovercomment=false;
	 	 			 }
	 	 		 }
	    	 }
	 	 	};
	    	 
	    	 $scope.getDelicgateUserAccess = function(user) {
	    			var getDelicgateUserAccess = function (response) {
	    				if(response.data != undefined) {
	    					$scope.deligateUser = response.data;
	    				}
	    			}
	    			WebServiceFactory.getDelicgateUserAccess(user).then(getDelicgateUserAccess);
	    		};
	    	
	    	
	    	

	    	$scope.checkMasterRole = function (typecheck){
	    		if(typecheck)
	    			{
	    			$scope.masterRolePanel = true;
	    			}
	    		else
	    			$scope.masterRolePanel = false;
	    		
	    	}
	    	
	    	
	    	var getDealDetails = function(response) {				
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		console.log("Approver Data")
		  		console.log($scope.dealDetails);
		  		$scope.currency=$scope.dealDetails[0].currency;
		  		console.log($scope.currency);
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
	    	
	  	
			
			$scope.uploadAttachmentData = function(frmDeal){
				
				var file = $('input[name="fuAttachFilename"]').get(0).files[0]
					$scope.upload= false;
					if(file != undefined ) {
						$scope.updateAttachFileData(frmDeal);
					} else {
						BootstrapDialog.show({
							title : 'FP Deal Creation - Manual Deal Summary',
							type : BootstrapDialog.TYPE_DANGER,
							message : "Please upload File and Click on Upload Button",
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(
										dialogRef) {
									dialogRef.close();
								}
							} ]
						});	
					}
			}
			
			
			$scope.updateAttachFileData=function(frmDeal){

				//alert("uploadFileToUrl");
				//console.log("called uploadPOExcel");
				var fileTest = $("#fuAttachFilename");	
				//console.log(fileTest.length);
				var noOfTowers=$sessionStorage.towerCount;
				var name = "Template";
				var tempID = 1;	
				var rpDealVersionId = $localStorage.rpDealVersionId;
				var dealAutoTowerId = $localStorage.dealAutoTowerId;
				var docType =frmDeal.sheetTypeModel;
				var file = $('input[name="fuAttachFilename"]').get(0).files[0];
				//console.dir(file);
				var formData = new FormData();
				formData.append('file', file);
				formData.append('name', name);
				formData.append('TempId', tempID);
				formData.append('rpDealVersionId', rpDealVersionId);
				formData.append('dealAutoTowerId',dealAutoTowerId);
				formData.append('docType',docType);
				formData.append('noOfTowers',noOfTowers);
				//console.log(file);
				var uploadUrl = contextPath+"/RightPrice-DAS/uploadMSFile";
			    $http.post(uploadUrl, formData, {
			        transformRequest: angular.identity,
			        headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
			    }).
			    then(function(data) {
			    	//console.log("data.data[0]");
			    	//console.log(data);
			    	var obj = JSON.stringify(data);
					var json = JSON.parse(obj);
					var customMessage = data.data;
			    	if(data.status == 200)	
					{
						BootstrapDialog.show({
							title : 'FP Deal Creation - Manual Deal Summary.',
							type : BootstrapDialog.TYPE_PRIMARY,
							message :  "File Upload Successfully.",
							closable : false,
							buttons : [ {
								label : 'OK',
								action :function(
										dialogRef) {
									dialogRef.close();
									window.location="FPDealCreationManualDealSummary";
								}
							} ]
						});
						angular.element("input[type='file']").val(null);
						$scope.searchFile(versionId);
					} 
			    	else if(data.status == 205){
							BootstrapDialog.show({
							title : 'FP Deal Creation - Manual Deal Summary.',
							type : BootstrapDialog.TYPE_DANGER,
							message : "File Upload Failed.",
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									window.location="FPDealCreationManualDealSummary";
								}
							} ]
						});
					}
			    	else if(data.status == 208)	
						{
							BootstrapDialog.show({
								title : 'FP Deal Creation - Manual Deal Summary.',
								type : BootstrapDialog.TYPE_DANGER,
								message :  "File Upload Failed as Revenue should be in between 0.995% to 1.005% Range for TCV.",
								closable : false,
								buttons : [ {
									label : 'OK',
									action :function(
											dialogRef) {
										dialogRef.close();
										window.location="FPDealCreationManualDealSummary";
									}
								} ]
							});
							angular.element("input[type='file']").val(null);
							$scope.searchFile(versionId);
						} 
					else {
						BootstrapDialog.show({
						title : 'FP Deal Creation - Manual Deal Summary.',
						type : BootstrapDialog.TYPE_DANGER,
						message :  "File Upload Failed",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
				
				}
			    
			    },function(data) {
			        $scope.displayres = data.data;
			        $scope.answer = 'Posting data was unsuccessful.';
			});
			}
			
			
			
			$scope.downloadFileWithFileName = function(dealAttachmentId){
				WebServiceFactory.downloadFileWithFileName(dealAttachmentId);
			};
			
			
			$scope.deleteFile = function(dealAttachmentId){
				WebServiceFactory.changeFpActiveStatus(dealAttachmentId);
				 $window.location.reload();
			};
			
			var getDealTower = function(response) 
			{
				console.log("fp Deal Tower data ..............");
				console.log(response);
				$scope.towerdetails = response.data;
				$sessionStorage.towerCount=$scope.towerdetails.length;
				var towerCnt=$sessionStorage.towerCount;
				console.log("TowerCount:"+towerCnt);
				
			/*	if($localStorage.projectIndustry==1)
					{
						if(towerCnt>1)
							{
							 $scope.sheetType = [{ name: "Costing", id: 1 },{ name: "Summary", id: 3 }];
							}
						else{
							 $scope.sheetType = [{ name: "Costing", id: 1 }];
						} 
				}
				else
					{
					 $scope.sheetType = [{ name: "Summary", id: 3 }];
					}*/
				var getVersionAttachment =function(response)
		    	{
		    		console.log("Attachment Data");
		    		console.log(response);
		    		$scope.versionAttachment=response.data;
		    		if($scope.versionAttachment!=undefined)
		    			{
		    			
		    			$filter('orderBy')($scope.versionAttachment, 'createdOn');
		    			angular.forEach($scope.versionAttachment, function(value, key) {
		    				angular.forEach($scope.towerdetails, function(value2, key2) {
		    					if($scope.versionAttachment[key].dealAutoTowerId==$scope.towerdetails[key2].dealautoTowerId)
		    					{
		    						$scope.versionAttachment[key].towerName=value2.towerName;
		    					}
		    				});
		    			});
		    			
		    			angular.forEach($scope.versionAttachment, function(value, key) {
		    				for(var i=0;i< $scope.sheetType.length;i++) {
		    					if($scope.versionAttachment[key].docType == $scope.sheetType[i].id) {
		    						
		    						$scope.versionAttachment[key].docTypeName = $scope.sheetType[i].name;
		    						var strMain =$scope.versionAttachment[key].createdOn;
		    						var arrSplit = [];
		    						arrSplit = strMain.split(" ");
		    						$scope.versionAttachment[key].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
		    					}
		    				}
		    			});
		    			
                 }
		    		
		    	};
		    	WebServiceFactory.getVersionAttachment(rpDealVersionId).then(getVersionAttachment);
		    	
			};
			WebServiceFactory.getDealTower(dealVersionId).then(getDealTower);
			
			
			
			$scope.updateManualDeal = function () {
				var getVersionData =function(response)
    			    	{
    						console.log("Resouce Forcasting data");
    						console.log(response);
/*    						if(response.data.length < 1)
    							{
    							BootstrapDialog.show({
    								title : 'FP Manual Deal Creation - Finalise FPDeal',
    								type : BootstrapDialog.TYPE_DANGER,
    								message : "Resource Forcasting is not done",
    								closable : false,
    								buttons : [{
    									label : 'OK',
    									action : function(dialogRef) {
    										dialogRef.close();
    									}
    								}]
    							 });
    							}*/
    						//else{

    							if($scope.versionAttachment.length > 0) {
    								var dealversionId = rpVrsId;
    								var updateManualDeal = function(response) {
    									$scope.manualDealResponse = response.data;
    									console.log("Manual Approval data");
    									console.log($scope.manualDealResponse);
    									if(response.status == 200) {
    										BootstrapDialog.show({
    											title : 'FP Deal Creation - Manual Deal Summary',
    											type : BootstrapDialog.TYPE_PRIMARY,
    											message : 'Deal Version successfully sent for GFT Approval.',
    											closable : false,
    											buttons : [ {
    												label : 'OK',
    												action : function(dialogRef) {
    													dialogRef.close();
    													window.location.reload();
    												}
    											} ]
    										});
    									}
    								}
    								WebServiceFactory.updateManualDeal(dealVersionId).then(updateManualDeal);
    							}else {
    								BootstrapDialog.show({
    				    	        	title : 'Finalize Manual Deal - Summary',
    				    	        	type : BootstrapDialog.TYPE_DANGER,
    				    	        	message : 'Please insert Manual Deal File.',
    				    	        	closable : false,
    				    	        	buttons : [{
    				    	        		label : 'OK',
    				    	        		action : function(dialogRef) {
    				    	        			dialogRef.close();
    				    	        			window.location.reload();
    				    	        		}
    				    	        	}]
    				    	        });
    							}
    							
    						//}
    						
    			    };
    			WebServiceFactory.getOldResourceData($localStorage.DealModel).then(getVersionData);
    		}
			
			var getLeadershipDetails = function(response) {
				//alert("inside getLeadershipDetails");
				console.log("getLeadershipDetails");
				console.log(response);
			    $scope.leaderApproverNameDetails = response.data;
			};
			WebServiceFactory.getLeadershipDetails().then(getLeadershipDetails);
			
			$scope.getVerticalDetails = function(verticalId){
				var getVerticalDetails = function(response) {
				//	alert("inside getVerticalDetails");
					console.log("getVerticalDetails");
					console.log(response);
				    $scope.verticalDetails = response.data;
				};
				WebServiceFactory.getVerticalDetails(verticalId).then(getVerticalDetails);
			}
			
			 $scope.getApprovalComments=function(user){
				  if(user.length>10){
				 var base64key = "QmFyMTIzNDVCYXIxMjM0NQ==";
					var parsedBase64Key = CryptoJS.enc.Base64
							.parse(base64key);
					var ive = CryptoJS.enc.Utf8
							.parse('RandomInitVector');
					var encrypted = CryptoJS.AES.decrypt(
							user, parsedBase64Key, {
								iv : ive
							});
					
					var decryptedText = encrypted
							.toString(CryptoJS.enc.Utf8);
					user=decryptedText;
				  }
					$scope.commentbox= true;
					
					if($scope.fpdcRoleSelectionFrm.approvalStatus == 3 && userType == 'GFT') {
 	 					$scope.rainbowApprovalPanel = false;
 	 				} else {
 	 					$scope.rainbowApprovalPanel = true;
 	 				}
				};
				
				   $scope.checkApprovalLevel = function(selectedVal) {
						if(selectedVal == 1) {
							$scope.isApprovalType = true;
							$sessionStorage.rainbowLevelFlag = 1;
						} else if(selectedVal == 2){
							$scope.fpdcRoleSelectionFrm.rainbowApprovalLevel=null;
							$scope.isApprovalType = false;
							$sessionStorage.rainbowLevelFlag = 0;
						}
					}
				
				
				
				$scope.updateRecycle = function (response) {
					$scope.isDisabled = true;
					var rpDealVersionId = rpVrsId;
//					console.log("rc ID is..........." +rcId);
					
				
//					console.log("Emp ID .............. "+empId);
					
					var currentApprovelLevel = $scope.versionDetails[0].currentApprovalLevel;
					
					/*var gftComment = angular.element(document.getElementById('txtComment'));

	                var comment = angular.element(gftComment).val();
	                $scope.versionDetails[0].approverComments=[$scope.versionDetails[0].approverComments] + "\n" + "[GFT]:" +[comment];
	                var approverComments =$scope.versionDetails[0].approverComments;*/
//					console.log("currentApproverLevel is................. "+ currentApprovelLevel);
					/*$scope.versionDetails[0].status = 1;
					var currentApproverStatus = $scope.versionDetails[0].status+1;//console.log("currentApproverStatus is...................."+ currentApproverStatus);
*/					marker = {
							"rpDealVersionId":rpDealVersionId,
							"currentApprovalStatus":4,
							"currentApproverId":null,
							"currentApprovalLevel":currentApprovelLevel
							/*"approverComments":approverComments*/
					};
//					console.log("Markers are");
//					console.log(marker);
					$http({
						 method: 'POST',
						 url: contextPath+"/RightPrice-DAS/updateFPMDApprovalData",
						 dataType: 'json',
						 data: angular.toJson(marker),  
			            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			    	}).
			    	then(function(data) {
			    		if(data.status == 200){
			    			var message = data.data;
			    	        BootstrapDialog.show({
			    	        	title : 'Finalize Manual Deal - Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : 'RateCard Recycled Successfully',
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "FPDealCreationManualDealSummary";
			    	        		}
			    	        	}]
			    	        });
			    		}else{
			    			 message = data.data;
			    			BootstrapDialog.show({
			    	        	title : 'Finalize Manual Deal - Summary',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : message,
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        		}
			    	        	}]
			    	        });
			    		}
				});
				}
		    		
				
			$scope.saveFPMDApprovalData = function(user,selectionType){
					$scope.selectionTypeModel=selectionType;
					  if(user.length>10){
					var base64key = "QmFyMTIzNDVCYXIxMjM0NQ==";
					var parsedBase64Key = CryptoJS.enc.Base64
							.parse(base64key);
					var ive = CryptoJS.enc.Utf8
							.parse('RandomInitVector');
					var encrypted = CryptoJS.AES.decrypt(
							user, parsedBase64Key, {
								iv : ive
							});
					
					var decryptedText = encrypted
							.toString(CryptoJS.enc.Utf8);
					user=decryptedText;
					  }
					//alert("inside save approval data");
					//save approval data in audit table
					
					if(userType == 'GFT') {
						$sessionStorage.fpraibowApprovalLevel = $scope.fpdcRoleSelectionFrm.rainbowApprovalLevel;
					} else {
						$sessionStorage.fpraibowApprovalLevel= $scope.versionDetails[0].rainbowApprovalFlag;
					}
				
				if($scope.selectionTypeModel==3)
					{
				if($scope.fpdcRoleSelectionFrm.currentApproverCommentModel!="" && $scope.fpdcRoleSelectionFrm.currentApproverCommentModel!=undefined )
					{
					BootstrapDialog.show({
						title: 'Finalize Manual Deal - Summary',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'Do you want to Approve ?',
						closable: true,
						buttons: [{
							label: 'Yes',
							cssClass: 'btn-primary',
							action: function(dialogRef) {
								var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
								var comment = angular.element(approverId).val();
								
								if ($.trim(comment) == '') {
									return false;
								}
								else
								{
									$scope.fpdcRoleSelectionFrm.currentApproverCommentModel = "["+user+"]: " + comment;
									$scope.$apply();
									if($scope.fpdcRoleSelectionFrm.approverCommentModel){
										var commnetBoxTxt = "["+user+"]: " + comment;
										$scope.fpdcRoleSelectionFrm.approverCommentModel += '\n'+ commnetBoxTxt;
										$scope.$apply();
									}
									else{
										$scope.fpdcRoleSelectionFrm.approverCommentModel = "["+user+"]: " + comment;
										$scope.$apply();
									}
									
									if($scope.fpdcRoleSelectionFrm.approverCommentModel.length > 700)
									{
										var commentLen = $scope.fpdcRoleSelectionFrm.approverCommentModel.length;
										var SkipChars = commentLen - 700;					
										$scope.fpdcRoleSelectionFrm.approverCommentModel = $scope.fpdcRoleSelectionFrm.approverCommentModel.substring(SkipChars, commentLen);
										$scope.$apply();
									}
								}
								
								var markers = {
										"rpDealVersionId":$localStorage.rpDealVersionId,//need to get while on page load
										"approverId":user,
										"approvalStatus":$scope.fpdcRoleSelectionFrm.approvalStatus,
										"approvalComments":$scope.fpdcRoleSelectionFrm.currentApproverCommentModel,
										"approvalDate": $scope.currentDate,
										
								};
								
								/*var saveFPMDApprovalData = function(response) {
										if(response.data == 200){
											$scope.updatedAuditDetails=1;
										}
									}
									WebServiceFactory.saveFPMDApprovalData(markers).then(saveFPMDApprovalData);*/
								
								
								//chk approver role id is null or not
								if($scope.versionDetails[0].level1ApproverRoleId!=null){
									$scope.setRoleId = 1;
								}
								if($scope.versionDetails[0].level2ApproverRoleId!=null){
									$scope.setRoleId = 2;
								}
								if($scope.versionDetails[0].level3ApproverRoleId!=null){
									$scope.setRoleId = 3;
								}
								if($scope.versionDetails[0].level4ApproverRoleId!=null){
									$scope.setRoleId = 4;
								}
								if($scope.versionDetails[0].level5ApproverRoleId!=null){
									$scope.setRoleId = 5;
								}
								if($scope.versionDetails[0].level6ApproverRoleId!=null){
									$scope.setRoleId = 6;
								}
								if($scope.versionDetails[0].level7ApproverRoleId!=null){
									$scope.setRoleId = 7;
								}
								if($scope.versionDetails[0].level8ApproverRoleId!=null){
									$scope.setRoleId = 8;
								}
								if($scope.versionDetails[0].level9ApproverRoleId!=null){
									$scope.setRoleId = 9;
								}
								
								//$scope.maxRoleId = $scope.setRoleId;
								
								//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
								
								if(($scope.versionDetails[0].currentApprovalLevel+1) == 2){
									$scope.getLanid = $scope.versionDetails[0].level2ApproverRoleId;
									$scope.statusIndicator = $scope.versionDetails[0].level2ApproverId.description;
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 3){
									$scope.getLanid = $scope.versionDetails[0].level3ApproverRoleId;
									if($scope.versionDetails[0].level3ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level3ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 4){
									$scope.getLanid = $scope.versionDetails[0].level4ApproverRoleId;
									if($scope.versionDetails[0].level4ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level4ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 5){
									$scope.getLanid = $scope.versionDetails[0].level5ApproverRoleId;
									if($scope.versionDetails[0].level5ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level5ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									}
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 6){
									$scope.getLanid = $scope.versionDetails[0].level6ApproverRoleId;
									if($scope.versionDetails[0].level6ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level6ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									}
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 7){
									$scope.getLanid = $scope.versionDetails[0].level7ApproverRoleId;
									if($scope.versionDetails[0].level7ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level7ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 8){
									$scope.getLanid = $scope.versionDetails[0].level8ApproverRoleId;
									if($scope.versionDetails[0].level8ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level8ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 9){
									$scope.getLanid = $scope.versionDetails[0].level9ApproverRoleId;
									if($scope.versionDetails[0].level9ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level9ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								
								
								
								//get lan id to update approver id in rate card details
								if($scope.getLanid == 1){
									$scope.lanId = "lanid1";
								}
								else if($scope.getLanid == 2){
									$scope.lanId = "lanid2";
								}
								else if($scope.getLanid == 3){
									$scope.lanId = "duh";
								}
								else if($scope.getLanid == 4){
									$scope.lanId = $scope.verticalDetails[0].deliveryHeadId;
									$scope.roleId = 4;
								}
								else if($scope.getLanid == 5){
									$scope.lanId ="RiskManagers"; 
										//$scope.verticalDetails[0].RiskManagersPersonId;
									$scope.roleId = 5;
								}
								else if($scope.getLanid == 6){
									$scope.lanId = "GFT";
									$scope.roleId = 6;
								}
								else if($scope.getLanid == 7){
									$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
									$scope.roleId = 7;
								}
								else if($scope.getLanid == 8){
									$scope.lanId = $scope.verticalDetails[0].buHeadId;
									$scope.roleId = 8;
								}
								else if($scope.getLanid == 9){
									$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
									$scope.roleId = 9;
								}
								else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
									$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
									$scope.roleId = 10;
								}
								else if($scope.getLanid == 11 && $scope.leaderApproverNameDetails[0].leaderId){
										$scope.lanId = $scope.leaderApproverNameDetails[2].leaderLanID;
										$scope.roleId = 11;
									}
									else if($scope.getLanid == 12 && $scope.leaderApproverNameDetails[0].leaderId){
										$scope.lanId = $scope.leaderApproverNameDetails[3].leaderLanID;
										$scope.roleId = 12;
									}
									else if($scope.getLanid == 13 && $scope.leaderApproverNameDetails[0].leaderId){
										$scope.lanId = $scope.leaderApproverNameDetails[4].leaderLanID;
										$scope.roleId = 13;
										$scope.statusIndicator = "BU Head"; 
									}
								
								//if($scope.setRoleId == $scope.getLanid){
								if($scope.getLanid == null){
									var marker = {
											"rpDealVersionId":$localStorage.rpDealVersionId,
											"currentApprovalStatus":$scope.fpdcRoleSelectionFrm.approvalStatus,
											"currentApproverId":null,
											"approverComments":$scope.fpdcRoleSelectionFrm.approverCommentModel,
											"currentApprovalLevel":$scope.versionDetails[0].currentApprovalLevel,
											"statusIndicator":$scope.statusIndicator,
											"currentApproverRoleId":$scope.roleId,
											"isManualRc": $scope.isManualCheck,
											"createdBy": $scope.createdBy,
											"raibowApprovalLevel" : $sessionStorage.fpraibowApprovalLevel,
											"approvalFlag" : $scope.fpdcRoleSelectionFrm.rainbowApprovalLevel
									};
								}
								
								
								else if($scope.fpdcRoleSelectionFrm.approvalStatus == 3){
									
									
									var marker = {
											"rpDealVersionId":$localStorage.rpDealVersionId,
											"currentApprovalStatus":2,
											"currentApproverId":$scope.lanId,
											"approverComments":$scope.fpdcRoleSelectionFrm.approverCommentModel,
											"currentApprovalLevel":$scope.versionDetails[0].currentApprovalLevel+1,
											"currentApproverRoleId":$scope.roleId,
											"isManualRc": $scope.isManualCheck,
											"statusIndicator":$scope.statusIndicator,
											"createdBy": $scope.createdBy,
											"raibowApprovalLevel" : $sessionStorage.fpraibowApprovalLevel,
											"approvalFlag" : $scope.fpdcRoleSelectionFrm.rainbowApprovalLevel
									};
									
								}
								
								else if($scope.fpdcRoleSelectionFrm.approvalStatus == 4){
									$scope.statusIndicator = "Recycled"
										var marker = {
											"rpDealVersionId":$localStorage.rpDealVersionId,
											"currentApprovalStatus":$scope.fpdcRoleSelectionFrm.approvalStatus,
											"currentApproverId":"",
											"approverComments":$scope.fpdcRoleSelectionFrm.approverCommentModel,
											"currentApprovalLevel":"",
											"currentApproverRoleId":"",
											"isManualRc": $scope.isManualCheck,
											"statusIndicator":$scope.statusIndicator,
											"createdBy": $scope.createdBy
									};
								}
								
								
								var updateApprovalDataForFPMD = function(response) {
									$scope.updatedAuditDetails=1;
									if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.fpdcRoleSelectionFrm.approvalStatus == 4){
										BootstrapDialog.show({
											title : 'Finalize Manual Deal - Summary',
											type : BootstrapDialog.TYPE_DANGER,
											message : 'Request Rejected SucessFully',
											closable : false,
											buttons : [{
												label : 'OK',
												action : function(dialogRef) {
													dialogRef.close();
													window.location = "FPDealCreationManualDealSummary";
												}
											}]
										});
										$scope.approvalcomment = true;
									}
									else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.fpdcRoleSelectionFrm.approvalStatus == 3){
										BootstrapDialog.show({
											title : 'Finalize Manual Deal - Summary',
											type : BootstrapDialog.TYPE_PRIMARY,
											message : 'Request Approved SucessFully',
											closable : false,
											buttons : [{
												label : 'OK',
												action : function(dialogRef) {
													dialogRef.close();
													window.location = "FPDealCreationManualDealSummary";
												}
											}]
										});
										$scope.approvalcomment = true;
									}
									else{
										BootstrapDialog.show({
											title : 'Finalize Manual Deal - Summary',
											type : BootstrapDialog.TYPE_DANGER,
											message : "Currently we are facing technical issue. Please try again later.",
											closable : false,
											buttons : [{
												label : 'OK',
												action : function(dialogRef) {
													dialogRef.close();
													window.location.reload();
												}
											}]
										});
										
									}
								}
								WebServiceFactory.updateApprovalDataForFPMD(marker).then(updateApprovalDataForFPMD);
								dialogRef.close();
							}
						}, {
							label: 'No',
							cssClass: 'btn-primary',
							action: function(dialog) {
								dialog.close();
							}
						}]
					});
					}
				else
					{
					BootstrapDialog.show({
						title : 'Finalize Manual Deal - Summary',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please enter comments in the comment section.",
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
				else if($scope.selectionTypeModel==4)
					{
					
					if($scope.fpdcRoleSelectionFrm.currentApproverCommentModel!="" && $scope.fpdcRoleSelectionFrm.currentApproverCommentModel!=undefined )

					 {
						BootstrapDialog.show({
						title: 'Finalize Manual Deal - Summary',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'Do you want to Reject ?',
						closable: true,
						buttons: [{
							label: 'Yes',
							cssClass: 'btn-primary',
							action: function(dialogRef) {
								var approverId = angular.element(document.getElementById('txtCurrentApproverComment'));
								var comment = angular.element(approverId).val();
								
								if ($.trim(comment) == '') {
									return false;
								}
								else
								{
									$scope.fpdcRoleSelectionFrm.currentApproverCommentModel = "["+user+"]: " + comment;
									$scope.$apply();
									if($scope.fpdcRoleSelectionFrm.approverCommentModel){
										var commnetBoxTxt = "["+user+"]: " + comment;
										$scope.fpdcRoleSelectionFrm.approverCommentModel += '\n'+ commnetBoxTxt;
										$scope.$apply();
									}
									else{
										$scope.fpdcRoleSelectionFrm.approverCommentModel = "["+user+"]: " + comment;
										$scope.$apply();
									}
									
									if($scope.fpdcRoleSelectionFrm.approverCommentModel.length > 700)
									{
										var commentLen = $scope.fpdcRoleSelectionFrm.approverCommentModel.length;
										var SkipChars = commentLen - 700;					
										$scope.fpdcRoleSelectionFrm.approverCommentModel = $scope.fpdcRoleSelectionFrm.approverCommentModel.substring(SkipChars, commentLen);
										$scope.$apply();
									}
								}
								
								var markers = {
										"rpDealVersionId":$localStorage.rpDealVersionId,//need to get while on page load
										"approverId":user,
										"approvalStatus":$scope.fpdcRoleSelectionFrm.approvalStatus,
										"approvalComments":$scope.fpdcRoleSelectionFrm.currentApproverCommentModel,
										"approvalDate": $scope.currentDate,
										
								};
								
								/*var saveFPMDApprovalData = function(response) {
										if(response.data == 200){
											$scope.updatedAuditDetails=1;
										}
									}
									WebServiceFactory.saveFPMDApprovalData(markers).then(saveFPMDApprovalData);*/
								
								
								//chk approver role id is null or not
								if($scope.versionDetails[0].level1ApproverRoleId!=null){
									$scope.setRoleId = 1;
								}
								if($scope.versionDetails[0].level2ApproverRoleId!=null){
									$scope.setRoleId = 2;
								}
								if($scope.versionDetails[0].level3ApproverRoleId!=null){
									$scope.setRoleId = 3;
								}
								if($scope.versionDetails[0].level4ApproverRoleId!=null){
									$scope.setRoleId = 4;
								}
								if($scope.versionDetails[0].level5ApproverRoleId!=null){
									$scope.setRoleId = 5;
								}
								if($scope.versionDetails[0].level6ApproverRoleId!=null){
									$scope.setRoleId = 6;
								}
								if($scope.versionDetails[0].level7ApproverRoleId!=null){
									$scope.setRoleId = 7;
								}
								
								//$scope.maxRoleId = $scope.setRoleId;
								
								//	$scope.setLanIdForRoleId = $scope.maxRoleId - $scope.rateCardDetails.currentApproverId;
								
								if(($scope.versionDetails[0].currentApprovalLevel+1) == 2){
									$scope.getLanid = $scope.versionDetails[0].level2ApproverRoleId;
									$scope.statusIndicator = $scope.versionDetails[0].level2ApproverId.description;
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 3){
									$scope.getLanid = $scope.versionDetails[0].level3ApproverRoleId;
									if($scope.versionDetails[0].level3ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level3ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 4){
									$scope.getLanid = $scope.versionDetails[0].level4ApproverRoleId;
									if($scope.versionDetails[0].level4ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level4ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 5){
									$scope.getLanid = $scope.versionDetails[0].level5ApproverRoleId;
									if($scope.versionDetails[0].level5ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level5ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									}
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 6){
									$scope.getLanid = $scope.versionDetails[0].level6ApproverRoleId;
									if($scope.versionDetails[0].level6ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level6ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									}
								}
								else if(($scope.versionDetails[0].currentApprovalLevel+1) == 7){
									$scope.getLanid = $scope.versionDetails[0].level7ApproverRoleId;
									if($scope.versionDetails[0].level7ApproverId != undefined) {
										$scope.statusIndicator = $scope.versionDetails[0].level7ApproverId.description;
									} else {
										$scope.statusIndicator = "Approved"
									} 
								}
								
								
								
								//get lan id to update approver id in rate card details
								if($scope.getLanid == 1){
									$scope.lanId = "lanid1";
								}
								else if($scope.getLanid == 2){
									$scope.lanId = "lanid2";
								}
								else if($scope.getLanid == 3){
									$scope.lanId = "duh";
								}
								else if($scope.getLanid == 4){
									$scope.lanId = $scope.verticalDetails[0].deliveryHeadId;
									$scope.roleId = 4;
								}
								else if($scope.getLanid == 5){
									$scope.lanId = "RiskManagers"; //$scope.verticalDetails[0].RiskManagersPersonId;
									$scope.roleId = 5;
								}
								else if($scope.getLanid == 6){
									$scope.lanId = "GFT";
									$scope.roleId = 6;
								}
								else if($scope.getLanid == 7){
									$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
									$scope.roleId = 7;
								}
								else if($scope.getLanid == 8){
									$scope.lanId = $scope.verticalDetails[0].buHeadId;
									$scope.roleId = 8;
								}
								else if($scope.getLanid == 9){
									$scope.lanId = $scope.leaderApproverNameDetails[1].leaderLanID;
									$scope.roleId = 9;
								}
								else if($scope.getLanid == 10 && $scope.leaderApproverNameDetails[0].leaderId){
									$scope.lanId = $scope.leaderApproverNameDetails[0].leaderLanID;
									$scope.roleId = 10;
								}
								
								//if($scope.setRoleId == $scope.getLanid){
								if($scope.getLanid == null){
									var marker = {
											"rpDealVersionId":$localStorage.rpDealVersionId,
											"currentApprovalStatus":$scope.fpdcRoleSelectionFrm.approvalStatus,
											"currentApproverId":null,
											"approverComments":$scope.fpdcRoleSelectionFrm.approverCommentModel,
											"currentApprovalLevel":$scope.versionDetails[0].currentApprovalLevel,
											"statusIndicator":$scope.statusIndicator,
											"currentApproverRoleId":$scope.roleId,
											"isManualRc": $scope.isManualCheck,
											"createdBy": $scope.createdBy
									};
								}
								
								
								else if($scope.fpdcRoleSelectionFrm.approvalStatus == 3){
									
									
									var marker = {
											"rpDealVersionId":$localStorage.rpDealVersionId,
											"currentApprovalStatus":2,
											"currentApproverId":$scope.lanId,
											"approverComments":$scope.fpdcRoleSelectionFrm.approverCommentModel,
											"currentApprovalLevel":$scope.versionDetails[0].currentApprovalLevel+1,
											"currentApproverRoleId":$scope.roleId,
											"isManualRc": $scope.isManualCheck,
											"statusIndicator":$scope.statusIndicator,
											"createdBy": $scope.createdBy
									};
									
								}
								
								else if($scope.fpdcRoleSelectionFrm.approvalStatus == 4){
									$scope.statusIndicator = "Recycled"
										var marker = {
											"rpDealVersionId":$localStorage.rpDealVersionId,
											"currentApprovalStatus":$scope.fpdcRoleSelectionFrm.approvalStatus,
											"currentApproverId":"",
											"approverComments":$scope.fpdcRoleSelectionFrm.approverCommentModel,
											"currentApprovalLevel":"",
											"currentApproverRoleId":"",
											"isManualRc": $scope.isManualCheck,
											"statusIndicator":$scope.statusIndicator,
											"createdBy": $scope.createdBy
									};
								}
								
								
								var updateApprovalDataForFPMD = function(response) {
									$scope.updatedAuditDetails=1;
									if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.fpdcRoleSelectionFrm.approvalStatus == 4){
										BootstrapDialog.show({
											title : 'Finalize Manual Deal - Summary',
											type : BootstrapDialog.TYPE_DANGER,
											message : 'Request Rejected SucessFully',
											closable : false,
											buttons : [{
												label : 'OK',
												action : function(dialogRef) {
													dialogRef.close();
													window.location = "FPDealCreationManualDealSummary";
												}
											}]
										});
										$scope.approvalcomment = true;
									}
									else if(response.data == 200 && $scope.updatedAuditDetails==1 && $scope.fpdcRoleSelectionFrm.approvalStatus == 3){
										BootstrapDialog.show({
											title : 'Finalize Manual Deal - Summary',
											type : BootstrapDialog.TYPE_PRIMARY,
											message : 'Request Approved SucessFully',
											closable : false,
											buttons : [{
												label : 'OK',
												action : function(dialogRef) {
													dialogRef.close();
													window.location = "FPDealCreationManualDealSummary";
												}
											}]
										});
										$scope.approvalcomment = true;
									}
									else{
										BootstrapDialog.show({
											title : 'Finalize Manual Deal - Summary',
											type : BootstrapDialog.TYPE_DANGER,
											message : "Currently we are facing technical issue. Please try again later.",
											closable : false,
											buttons : [{
												label : 'OK',
												action : function(dialogRef) {
													dialogRef.close();
													window.location.reload();

												}
											}]
										});
										
									}
								}
								WebServiceFactory.updateApprovalDataForFPMD(marker).then(updateApprovalDataForFPMD);
								dialogRef.close();
							}
						}, {
							label: 'No',
							cssClass: 'btn-primary',
							action: function(dialog) {
								dialog.close();
							}
						}]
					});
					}
					else
					{
					BootstrapDialog.show({
						title : 'Finalize Manual Deal - Summary',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Please enter comments in the comment section.",
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
				else{
					BootstrapDialog.show({
			        	title : 'Finalize Manual Deal - Summary',
			        	type : BootstrapDialog.TYPE_DANGER,
			        	message : "Please select Approver Action.",
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
		    	
				
				$scope.deleteManualFile = function(attachementId){
					var deleteManualFile = function(response) {
						$scope.deleteResponse = response;
						
						if($scope.deleteResponse.status == 200){
							$scope.message = $scope.deleteResponse.data;
			    	        BootstrapDialog.show({
			    	        	title : 'FP Deal Creation - Manual Deal Summary',
			    	        	type : BootstrapDialog.TYPE_PRIMARY,
			    	        	message : $scope.message,
			    	        	closable : false,
			    	        	buttons : [{
			    	        		label : 'OK',
			    	        		action : function(dialogRef) {
			    	        			dialogRef.close();
			    	        			window.location = "FPDealCreationManualDealSummary";
			    	        		}
			    	        	}]
			    	        });
			    	        $scope.fpdcRoleSelectionFrm.fpDealManualOnsitePer="";
				    		$scope.fpdcRoleSelectionFrm.fpDealManualOffshorePer="";
				    		$scope.fpdcRoleSelectionFrm.fpDealManualRevenue="";
				    		$scope.fpdcRoleSelectionFrm.fpDealDirectCostper="";
				    		$scope.fpdcRoleSelectionFrm.fpDealManualGrossMargin="";
				    		$scope.fpdcRoleSelectionFrm.fpDealManualProSpecCost="";
				    		$scope.fpdcRoleSelectionFrm.fpDealManualProSpecAgileCost="";
				    		$scope.fpdcRoleSelectionFrm.fpManualDealGMperAfterPSC="";
				    		$scope.fpdcRoleSelectionFrm.fpManualDealVolDisc="";
				    		$scope.fpdcRoleSelectionFrm.fpManualDealPen="";
				    		$scope.fpdcRoleSelectionFrm.ManualDealGmAfterVolDis="";
			    	        
						
						}else{
			    			$scope.message = $scope.deleteResponse.data;
			    			BootstrapDialog.show({
			    	        	title : 'FP Deal Creation - Manual Deal Summary',
			    	        	type : BootstrapDialog.TYPE_DANGER,
			    	        	message : $scope.message,
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
					WebServiceFactory.deleteManualFile(attachementId).then(deleteManualFile);
				};
				
				//manglam updated
				
				var markers = {	
						 "versionId" : $localStorage.rpDealVersionId							
						 };					
						 var getPLAttachementData = function(response)  {						
						  $scope.AttachementData=response.data;        						
						  };						
						  WebServiceFactory.getPLAttachementData(markers).then(getPLAttachementData);
						  
						  $scope.deleteFile = function(objectid,category) {
							  if(userType == 'Delivery')					
							  {					
								  $scope.deleteBtnDisable=true;  					
								  }					
							  else {
						  WebServiceFactory.changeActiveStatus(objectid);
						  $window.location.reload();						
						  };  }     
						  
						  
						  if(userType == 'Delivery')			
						  {				 
					   $scope.uploadplBtnDisable = true;  			
						  }			
						  else {
	
						  $scope.uploadAttachmentPLData = function(frmDeal){
						  var file = $('input[name="PLAttachFilename"]').get(0).files[0]
						  $scope.upload= false;
						  if(file != undefined ) {
						  
						  $scope.uploadPLFileData(file);
						  } 
						  else {
							 BootstrapDialog.show({										
							 title : 'FP Deal Creation - Manual Deal Summary',
							 type : BootstrapDialog.TYPE_DANGER,
							 message : "Please upload File and Click on Update Button",
							 closable : false,
							 buttons : [ {
								 label : 'OK',
								 action : function(	
										 dialogRef) {	
									  dialogRef.close();
									  window.location="FPDealCreationManualDealSummary";	      
								 }
									
							  } ]									
							 });        								
							 }						
							} }

						  $scope.uploadPLFileData=function(file){
							  var name = "Template";
							  var tempID = 1; 
							  var versionId = $localStorage.rpDealVersionId; 
							  
							  var formData = new FormData();
							  formData.append('file', file);
							  formData.append('name', name);
							  formData.append('TempId', tempID);
							  formData.append('versionId', $localStorage.rpDealVersionId);
							  
							  var uploadUrl = contextPath+"/RightPrice-DAS/uploadPLFileData";
							  $http.post(uploadUrl, formData, {	
								  transformRequest: angular.identity,
								  headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
								  }).then(function(data) {	 
									  var obj = JSON.stringify(data);
									  var json = JSON.parse(obj);
									  var customMessage = data.data;
									  
									  if(data.status == 200)      
									  {									
										  BootstrapDialog.show({
											  title : 'FP Deal Creation - Manual Deal Summary',
											  type : BootstrapDialog.TYPE_PRIMARY,
											  message : customMessage,
											  closable : false,
											  buttons : [ {
												  label : 'OK',
												  action :function(
														  dialogRef) {
													  dialogRef.close();
													  window.location="FPDealCreationManualDealSummary";
													  }										
											  } ]									
										  });        																		
										  $scope.searchFile(versionId);
										  } 						    	
									  else if(data.status == 205){
										  BootstrapDialog.show({
											  title : 'FP Deal Creation - Manual Deal Summary',
											  type : BootstrapDialog.TYPE_DANGER,
											  message : "File Upload Failed.",
											  closable : false,
											  buttons : [ {
												  label : 'OK',
												  action : function(dialogRef) {
													  dialogRef.close();
													  window.location="FPDealCreationManualDealSummary";
													  }										
											  } ]									
										  });        								
										  }								
									  else {	
										  BootstrapDialog.show({
											  title : 'FP Deal Creation - Manual Deal Summary',
											  type : BootstrapDialog.TYPE_DANGER,
											  message : customMessage,
											  closable : false,	
											  buttons : [ {
												  label : 'OK',
												  action : function(dialogRef) {
													  dialogRef.close();
													  window.location="FPDealCreationManualDealSummary";
													  }									
											  } ]								
										  });								
										  }						    
									  },function(data) {
										  $scope.displayres = data.data;
										  $scope.answer = 'Posting data was unsuccessful.';	
										  });						
							  }
						  
						  $scope.searchFile = function(rpDealVersionId)
						  {							
							  angular.element("input[type='file']").val(null);
							  console.log("inside File Search---"+rpDealVersionId);
							  
							  var markers = {
									  "versionId" : $localStorage.rpDealVersionId
									  };							
							  $http({							    
								  method: 'POST',
								  url: contextPath+'/RightPrice-DAS/getPLAttachementData',
								  dataType: 'json',
								  data: JSON.stringify(markers),
								  headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
								  }).
								  then(function(data) {});
							  }  					
						  $scope.downloadFile = function(objectId){
							  // alert('hi'+objectId);
							  WebServiceFactory.downloadFileFormID(objectId);
		                  }; 
				
				
	}]);