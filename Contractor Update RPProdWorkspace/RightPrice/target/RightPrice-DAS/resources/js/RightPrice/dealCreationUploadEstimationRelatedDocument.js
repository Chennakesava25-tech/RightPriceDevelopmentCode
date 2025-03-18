//var app = angular.module('RightPrice', ['ngMessages']);
app.controller("FPDealCreationUploadDocumentsController", 
		['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$localStorage','$sessionStorage','$filter', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$localStorage,$sessionStorage,$filter, $index){
	var contextPath = "/RightPrice-DAS";
	$scope.onUpload = false;
	$scope.isFPDealGFT = false;
	$scope.isDevelopment=false;
	$scope.isUploadDisabled=false;
	$scope.isRCPricing=false;
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
        window.location='FPDealCreationCostInputs';
    };
    $scope.Next = function()
    {   
        window.location='FPDealCreationWhatIfApplicationwise';
    };
    $scope.docTypes = [{ name: "Proposal Response Doc", id: 1 }, { name: "Effort Estimation Sheet", id: 2 }, { name: "S.O.W", id: 3 }];
    
    $scope.getUploadedDoc = function(rpDealVersionId) {
    	var getUploadedDoc = function(response) {
    		console.log("getUploadedDoc");
    		console.log(response.data);
    		$scope.documents = response.data;
    		for(var i=0;i<$scope.documents.length;i++)
			{
					var strMain = $scope.documents[i].createdOn;
				    var arrSplit = [];
				    arrSplit = strMain.split(" ");
				    $scope.documents[i].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
			}
    	};
    	///WebServiceFactory.getUploadedEADoc($localStorage.DealModel).then(getUploadedDoc);
    	WebServiceFactory.getUploadedDoc($localStorage.rpDealVersionId).then(getUploadedDoc);
    }

    $scope.onUploadClick = function(isValid,id,uploadedfile,cmnt){
    	$scope.onUpload = true;
    	if(isValid){
    		if(cmnt == undefined){
    			cmnt = "";
    		}
    		console.log("uploadedfile"+uploadedfile);
    		if(id != undefined ){
    			$scope.onUpload = false;
    			console.log("DocType:"+id);
    			$scope.uploadEstimationRelatedDoc(id,cmnt);
    		}
    	}
    };
    
    var userType = sessionStorage.getItem('userType');
	 console.log("The USer Value from the Session is........ "+ userType);
	 if(userType == 'GFT') {
		 $scope.isFPDealGFT = true;
	}
	 
    $scope.downloadFile = function(objectId){
		WebServiceFactory.downloadFileFormID(objectId);
	};
	
	$scope.onDeleteClick = function(attachmentId){
		//WebServiceFactory.updateActiveStatus(docId);
		WebServiceFactory.deleteEADoc(attachmentId);
		$window.location.reload();
	};
    
    $scope.uploadEstimationRelatedDoc = function(id,cmnt){
    	console.log("inside insertData");
    	 var fileTest = $("#fuUploadFilename");
    	 var name = [ "UPLOADED_FILE_NAME"];
    	 var marker = {
    			 "doctypeId" : id,
    			 "comments" : cmnt,
    			 "rpid" : $localStorage.rpDealVersionId
    	 };
    	 var formData = new FormData();
 			for (var i = 0; i < fileTest.length; i++) {
 				var fileName = "file"+i.toString();
 				console.log("fileName : "  + fileName);
 				var file = $('input[type="file"]').get(i).files[0];
 				if(file){
 				formData.append('file', file);
 				formData.append('name', name[i]);
 				}
 			}
 	/*	console.log(" length :" + formData.getAll('file').length);
 		console.log(formData.getAll('file'));
 		console.log(formData.getAll('name'));*/
 		formData.append('jsonData', angular.toJson(marker));
 		var uploadEstimationRelatedDoc = function(response) {
 			$scope.answer = 'Data has been added successfully';
	        BootstrapDialog.show({
	        	title : 'FP Deal Creation - Upload Estimation Related Documents',
	        	type : BootstrapDialog.TYPE_PRIMARY,
	        	message : 'Document Uploaded Successfully.',
	        	closable : false,
	        	buttons : [{
	        		label : 'OK',
	        		action : function(dialogRef) {
	        			dialogRef.close();
	        			$window.location.reload();
	        		}
	        	}]
	        });
			console.log("Call complete");
		};
		WebServiceFactory.uploadEstimationRelatedDoc(formData).then(uploadEstimationRelatedDoc);
		console.log("Call complete");
    }
    
    var rpVrsId = $localStorage.rpDealVersionId;	
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
		//alert('hi 66');
		$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	//arvind
		$scope.getUploadedDoc($localStorage.rpDealVersionId);
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
	
	
	
	var getVersionData =function(response)
	{

		console.log("Version Data");
		console.log(response);
		$scope.versionDetails = response.data;
		if($scope.versionDetails[0].fpProjectTypeId==1 || $scope.versionDetails[0].fpProjectTypeId==2){
			$scope.isDevelopment=true;	
		}
		else{
			$scope.isDevelopment=false;
		}
		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
				$scope.versionDetails[0].currentApprovalStatus == 4 || $scope.versionDetails[0].currentApprovalStatus == 8 ) && userType == 'Delivery')
		{	
		
			$scope.isUploadDisabled=false;
			
		}
	else
		{
			$scope.isUploadDisabled=true;
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
				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<5)
			{
			BootstrapDialog.show({
				title : 'FP Deal Creation - Upload Document',
				type : BootstrapDialog.TYPE_DANGER,
				message : 'Data is not saved at previous screen.',
				closable : false,
				buttons : [ {
					label : 'OK',
					action : function(dialogRef) {
						dialogRef.close();
						//$window.location.reload();
						if($localStorage.pageTracker==4)
						{
							window.location="FPDealCreationCostInputs";
						}
						else if($localStorage.pageTracker==3)
							{
								window.location="FPDealCreationStaffing";
							}
						else if($localStorage.pageTracker==2)
							{
							window.location="FPDealCreationRoleSelection";
							}
						else if($localStorage.pageTracker==1)
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
		
		/*$localStorage.pricingType=$scope.versionDetails[0].pricingType;

		if($localStorage.pricingType!=1)
		{
		  $scope.isRCPricing=true;
		}
		else{
			$scope.isRCPricing=false;
		}*/
		
			
		
	};
	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
	
	$scope.uploadClick = function(attachmentForm){
		//alert("uploadData called");
		$scope.onUpload = true;
		if(attachmentForm.$valid){
			$scope.onUpload = false;
			//alert("inside valid");
			if($scope.fuUploadFilename != true) {
				$scope.uploadFileToUrl(attachmentForm);
			} else {
				BootstrapDialog.show({
					title : 'FP Deal Creation - Upload Estimation Related Documents',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Please upload File and Click on Upload Button",
					closable : false,
					buttons : [ {
						label : 'OK',
						action : function(
								dialogRef) {
							dialogRef.close();
							$window.location.reload();
						}
					} ]
				});	
			}
			} 
	};	
	
	// Upload File
	 $scope.uploadFileToUrl = function(attachmentForm){
	    	//alert("uploadFileToUrl");
	    	//console.log("called uploadPOExcel");
	    	var fileTest = $("#fuUploadFilename");	
	    	//console.log(fileTest.length);
	    	var name = "Template";
	    	var versionId =  $localStorage.rpDealVersionId ;	
	    	var category="ERD";
	    	if(attachmentForm.comments!=undefined){
	    	var cmnt= attachmentForm.comments;	
	    	}
	    	else{
	    		var cmnt="";	
	    	}
	    	if(attachmentForm.selectedId==1){
	    		var docType="Proposal Response Doc"
	    	}
	    	else if(attachmentForm.selectedId==2){
	    		var docType="Effort Estimation Sheet";
	    	}
	    	else{
	    		var docType="S.O.W";
	    	}
	    	
    		//console.log("fileTest");
			var file = $('input[name="fuUploadFilename"]').get(0).files[0];
			//console.dir(file);
			var formData = new FormData();
			formData.append('file', file);
			formData.append('name', name);
			formData.append('versionId',versionId);
			formData.append('cmnt',cmnt);
			formData.append('category',category);
			formData.append('docType',docType);
			
			//console.log(file);
	    	var uploadUrl = contextPath+"/RightPrice-DAS/uploadEstimationDealFile";
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
						title : 'FP Deal Creation - Upload Estimation Related Documents',
						type : BootstrapDialog.TYPE_PRIMARY,
						message : 'File Uploaded SuccesFully',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(
									dialogRef) {
								dialogRef.close();
								$window.location.reload();
							}
						} ]
					});	
					$scope.searchFile(rcId);
			
				} 
		    	else if(data.status == 205){
						BootstrapDialog.show({
						title : 'FP Deal Creation - Upload Estimation Related Documents',
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
				else {
					BootstrapDialog.show({
					title : 'FP Deal Creation - Upload Estimation Related Documents',
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
			}
		    },function(data) {
		        $scope.displayres = data.data;
		        $scope.answer = 'Posting data was unsuccessful.';
		});
	 };
    
    
}]);

app.directive('validFile',function(){
	  return {
	    require:'ngModel',
	    link:function(scope,el,attrs,ngModel){
	      el.bind('change',function(){
	        scope.$apply(function(){
	          ngModel.$setViewValue(el.val());
	          ngModel.$render();
	        });
	      });
	    }
	  }
	});

/*app.directive('checkFileSize', function() {
	  return {
	    link: function(scope, elem, attr, ctrl) {
	      function bindEvent(element, type, handler) {
	        if (element.addEventListener) {
	          element.addEventListener(type, handler, false);
	        } else {
	          element.attachEvent('on' + type, handler);
	        }
	      }

	      bindEvent(elem[0], 'change', function() {
	        alert('File size:' + this.files[0].size);
	      });
	    }
	  }
	});*/

app.directive('checkFileSize', function() {
	console.log("valid File Size called");
	  return {
		  require: 'ngModel',
		  scope: {
			  checkFileSize: "="
	       },
	    link: function(scope, element, attributes,ngModel) {
	      function bindEvent(element, type, handler) {
	        if (element.addEventListener) {
	          element.addEventListener(type, handler, false);
	        } else {
	          element.attachEvent('on' + type, handler);
	        }
	      }
	      function validSize(boolean) {
	            ngModel.$setValidity('checkfilesize', boolean);
	      }
	      
	      bindEvent(element[0], 'change', function() {
	    	  if(this.files[0] != undefined){
	    		var size = parseInt(this.files[0].size );
//	    		alert("File size:"+size);
		        if(size>3*1048576){
		        	console.log("inside if");
		        	validSize(false);
		        }else{
		        	console.log("inside else");
		        	validSize(true);
		        	//for over come the lezzy binding use below 
		        	scope.$apply();
		        }
	    	  }
	    	  else{
	    		  validSize(true);
	    	  }
	    	  
	      });
	  
	    }
	  };
	});
