//var app = angular.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("MastersRateCardController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 var contextPath = "/RightPrice-DAS";
		 $scope.isHide=true;
		 $scope.isTxtRequired=false;
		 $scope.frmRateCard={};
		 $scope.MDAttachementData=[];
		 $scope.tableHide = true;
		 
		 var userType = sessionStorage.getItem('userType');
		// console.log("The USer Value from the Session is........ "+ userType);
     	 $scope.isDisabled = true;
   
     	window.localStorage.clear();
     	
     	if($localStorage.rcId != 0 && $localStorage.rcId != undefined) {
     		$scope.isHide=false;
			var getRateCardInfo = function(response) {
				$scope.rateCardInfo = response.data;
				$localStorage.rcId = $scope.rateCardInfo[0].rcId;
				$scope.frmRateCard.rateCardId = $scope.rateCardInfo[0].rcId;
				$scope.frmRateCard.RateCardRateCardName = $scope.rateCardInfo[0].rcName;
				$scope.frmRateCard.RateCardCustomer = $scope.rateCardInfo[0].customerVerticalMapping.customer.customerName;
				$scope.frmRateCard.RateCardPrvCmnt = $scope.rateCardInfo[0].rccomments;
				var rcStartDate  = $scope.rateCardInfo[0].rcStartDate;
		  		var date = new Date(rcStartDate.substring(0,10));
		  		var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.rateCardInfo[0].rcStartDate = rcStartDD;
		  		$scope.frmRateCard.RateCardStartDate = $scope.rateCardInfo[0].rcStartDate;
		  		var rcEndDate  = $scope.rateCardInfo[0].rcEndDate;
		  		var date = new Date(rcEndDate.substring(0,10));
		  		var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.rateCardInfo[0].rcEndDate = rcEndDateDD;
		  		$scope.frmRateCard.RateCardEndDate = $scope.rateCardInfo[0].rcEndDate;
		  		if($scope.rateCardInfo[0].currentApprovalStatus != null && $scope.rateCardInfo[0].currentApprovalStatus == 3) {
					$scope.frmRateCard.RateCardstatus = "Approved";
				} 
					
				if($scope.rateCardInfo[0].currentApprovalStatus != null && $scope.rateCardInfo[0].currentApprovalStatus == 6){
					$scope.frmRateCard.RateCardstatus = "Expired";
				}
		  		
			};
			WebServiceFactory.getRateCardInfo($localStorage.rcId).then(getRateCardInfo);
		
     	 }
	
 		var getrateCardId= function(response) {
 			
			$scope.rateCardId = response.data;
			console.log($scope.rateCardId);
		};
		WebServiceFactory.getrateCardId().then(getrateCardId);
		
			$scope.saveRateCard=function(frmRateCard){
			$scope.saved=true;

			if($scope.frmRateCard.action != undefined   && $scope.frmRateCard.rateCardId != undefined && $scope.frmRateCard.RateCardCmnt)
			if($scope.frmRateCard.action == 1) {
				$scope.saveMasterRateCardDetails();
			}else{
				if($scope.frmRateCard.expectedenddate != undefined){
					$scope.saveMasterRateCardDetails();
				}
			}
						
		
		};
		
	        $scope.saveMasterRateCardDetails = function(){
	        	$scope.saved = true;
				var actions=$scope.frmRateCard.action;
				var rcId=$scope.frmRateCard.rateCardId;
				var comments=$scope.frmRateCard.RateCardCmnt +"\n"+ $scope.frmRateCard.RateCardPrvCmnt;
				if(actions == 1) {
					var marker={
							"currentApprovalStatus":5,
							"statusIndicator":"DEACTIVATED",
							"rcId":$scope.frmRateCard.rateCardId,
							"rccomments":comments
							
					};
				} else {
					var expectedRCEndDate=$scope.frmRateCard.expectedenddate;
					var marker={
							"expectedRCEndDate":$scope.frmRateCard.expectedenddate,
							"rcId":$scope.frmRateCard.rateCardId,
							"rccomments":comments
					};
				}
				if($scope.frmRateCard.action==1){
				  BootstrapDialog.show({
						title : 'Master Rate Card',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'Do you want to Deactivate Rate Card.',
						closable : false,
						buttons : [ {
							label : 'Yes',
							cssClass : 'btn-primary',
							action : function(dialogRef) {
								dialogRef.close();
								var saveMasterRateCardDetails=function(response){
									 if( response.status == 205){
										 $localStorage.rcId=response.data; 
										  BootstrapDialog.show({
													title : 'Master Rate Card',
													type : BootstrapDialog.TYPE_PRIMARY,
													message : 'Rate card deactivated successfully.',
													closable : false,
													buttons : [ {
														label : 'OK',
														action : function(dialogRef) {
															dialogRef.close();
															 window.location="MasterRateCard";
														}
													} ]
												});
										  }

								};
								WebServiceFactory.saveMasterRateCardDetails(marker).then(saveMasterRateCardDetails);
								
								 
							}
						} ,{
							label : 'No',
							cssClass : 'btn-primary',
							action : function(dialogItself) {
								dialogItself.close();
						}
						}]
					});
					
				}
				else
				{		
					var saveMasterRateCardDetails=function(response){
					 if( response.status == 205){
						 $localStorage.rcId=response.data; 
						  BootstrapDialog.show({
									title : 'Master Rate Card',
									type : BootstrapDialog.TYPE_PRIMARY,
									message : 'Rate card extended successfully.',
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
											 window.location="MasterRateCard";
										}
									} ]
								});
						  }

				};
				WebServiceFactory.saveMasterRateCardDetails(marker).then(saveMasterRateCardDetails);
				}
			};
		
	
			
			$scope.changeAction = function(action){
				
				$scope.isDisabled = action > 0 ? $scope.isDisabled=true:$scope.isDisabled=false; 
				if(action == 0|| action==1)
					$scope.isTxtRequired=true;
				
				
			}
			$scope.cancelRateCard=function(frmRateCard){
				 window.location= contextPath+"/welcome";
			
			};
			
			
			$scope.putRateCardDetails=function(rateCardId){
				$scope.isHide=false;
				angular.forEach($scope.rateCardId, function(value, key) {
					
					if(value.rcId == rateCardId) {
						if($scope.rateCardId[key].rcName != null) {
							$scope.frmRateCard.RateCardRateCardName =$scope.rateCardId[key].rcName;
						}
						
						if($scope.rateCardId[key].customerName != null) {
							$scope.frmRateCard.RateCardCustomer = $scope.rateCardId[key].customerName;
						}
						
						if($scope.rateCardId[key].rcStartDate != null) {
							$scope.frmRateCard.RateCardStartDate = $scope.rateCardId[key].rcStartDate;
							$scope.frmRateCard.RateCardStartDate =$filter('date')(new Date($scope.frmRateCard.RateCardStartDate.substring(0,10)),'dd/MM/yyyy');
						}
						
						if($scope.rateCardId[key].rcEndDate != null) {
							$scope.frmRateCard.RateCardEndDate = $scope.rateCardId[key].rcEndDate;
							$scope.frmRateCard.RateCardEndDate =$filter('date')(new Date($scope.frmRateCard.RateCardEndDate.substring(0,10)),'dd/MM/yyyy');

						}
						
						if($scope.rateCardId[key].expectedRCEndDate != null){
							$scope.frmRateCard.expectedenddate = $scope.rateCardId[key].expectedRCEndDate;
							$scope.frmRateCard.expectedenddate =$filter('date')(new Date($scope.frmRateCard.expectedenddate.substring(0,10)),'dd/MM/yyyy');
						}
						
						if($scope.rateCardId[key].currentStatus != null && $scope.rateCardId[key].currentStatus == 3) {
							$scope.frmRateCard.RateCardstatus = "Approved";
						} 
							
						if($scope.rateCardId[key].currentStatus != null && $scope.rateCardId[key].currentStatus == 6){
							$scope.frmRateCard.RateCardstatus = "Expired";
						}
						
						if($scope.rateCardId[key].rccomments != null){
							$scope.frmRateCard.RateCardPrvCmnt = $scope.rateCardId[key].rccomments;
						}
						
						else if($scope.rateCardId[key].rccomments == null){
							$scope.frmRateCard.RateCardPrvCmnt = "";
						}
						}
				})
					
			};
			
			$scope.showRateCardDetails = function(rcId){
				var rcId=$scope.frmRateCard.rateCardId;
				console.log("showRateCardDetails");
				console.log(rcId);
				$localStorage.rcId = rcId;
				
				var browser = window.navigator.appVersion;

	              //Workaround to enable the users to download the report in IE.
	              if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
	                     (browser.indexOf('MSIE 10') !== -1)) 
	              {
	            	  window.open ("RateCardCreationDetails","$localStorage.rcId");
//	            	  window.open ("RightPrice/RateCardCreationDetails","$localStorage.rcId");
	              } 
	              else 
	              {
	            	  window.open ("RateCardCreationDetails","_blank","$localStorage.rcId");
//	            	  window.open ("RightPrice/RateCardCreationDetails","_blank","$localStorage.rcId");
	              }
			};
			
		    $scope.uploadData = function(frmRateCard,fuUploadFilenameModel){
		    	$scope.onUplaod= true;
		    	$scope.upload =  true;
				if(frmRateCard.fuUploadFilenameModel != undefined){
					$scope.onUplaod= false;
					if($scope.frmRateCard.rateCardId != undefined ){
						$scope.upload=false;
					//alert("inside valid");
					$scope.uploadmanualRCFile();
					}
				}
			};
			
			
			 //Uploading file for Manual Rate card
			 $scope.uploadmanualRCFile = function(){
			    	var fileTest = $("#fuUploadFilename");	
			    	var name = "Template";
			    	var tempID = 1;	
					var file = $('input[name="fuUploadFilename"]').get(0).files[0];
					var formData = new FormData();
					formData.append('file', file);
					formData.append('name', name);
					formData.append('TempId',tempID);
					formData.append('rpDealVersionId', $scope.frmRateCard.rateCardId);
			    	//console.log(file);
			    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadMastersFile";
		            $http.post(uploadUrl, formData, {
		                transformRequest: angular.identity,
		                headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
		            }).
				    then(function(data) {
				    	var obj = JSON.stringify(data);
						var json = JSON.parse(obj);
						var customMessage = data.data;
				    	if(data.status == 200)	
						{
							BootstrapDialog.show({
								title : 'Master - RateCard',
								type : BootstrapDialog.TYPE_PRIMARY,
								message : customMessage,
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										$scope.searchFile($scope.frmRateCard.rateCardId)
										$scope.frmRateCard.fuUploadFilenameModel = undefined;
									}
								} ]
							});	
						} 
				    	else if(data.status == 205){
								BootstrapDialog.show({
								title : 'Master - RateCard',
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
								$scope.frmRateCard.fuUploadFilenameModel = undefined;
						}
						else {
							BootstrapDialog.show({
							title : 'Master - RateCard',
							type : BootstrapDialog.TYPE_DANGER,
							message : customMessage,
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
								}
							} ]
						});
							$scope.frmRateCard.fuUploadFilenameModel = undefined;
					}
				    },function(data) {
				        $scope.displayres = data.data;
				        $scope.answer = 'Posting data was unsuccessful.';
				});
			 };
			 
			  $scope.searchFile = function(rcId)
				{
				  ;
					angular.element("input[type='file']").val(null);
					console.log("inside File Search---"+rcId);

					var markers = {
								"versionId" : rcId
							};
					$http({
					    method: 'POST',
					    url: contextPath+'/RightPrice-DAS/getMasterAttachement',
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
											$scope.MDAttachementData[j] = $scope.temp[i];
											var strMain = $scope.MDAttachementData[j].createdOn;
										    var arrSplit = [];
										    arrSplit = strMain.split(" ");
										    $scope.MDAttachementData[j].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
											j++;
										
									}
									console.log("$scope.MDAttachementData")
									console.log($scope.MDAttachementData)
							
									
								}
								else{
									$scope.tableHide=true;
								}
						    	
						    	
						    });
					
				}
			  
	            // Download the Existing File
	            $scope.downloadFile = function(objectId){
	        		WebServiceFactory.downloadFileWithFileName(objectId);
	        	};


				$scope.deleteFile = function(objectid, rcId) {
				WebServiceFactory.deleteMasterRCAttachment(objectid);
				for(var i = 0; i < $scope.MDAttachementData.length;i++)
					{
					if($scope.MDAttachementData[i].dealAttachmentId == objectid)
						{
						$scope.MDAttachementData.splice(i,1);						
						}
					}
				if($scope.MDAttachementData.length == 0){
					 $scope.tableHide = true;
				}

					
				
			};
			
			
		  
	}]);