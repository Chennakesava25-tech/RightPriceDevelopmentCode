// 		var app = angular.module('DealCreationUploadDocumentsApp', []);
		app.controller("TMDealCreationUploadDocumentsController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
			 var rpVrsId = $sessionStorage.rpDealVersionId		
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
		        //window.location='TMDealCreationProjectSpecificCost';
				window.location='TMDealCreationStaffing';
		    }; 
		     $scope.Next = function()
		    {   
		        window.location='TMDealCreationSummary';
		    }; 
		    
		    $window.onload = function()
		    {
		    	
		    }
		    $scope.docTypes = [{ name: "Proposal Response Doc", id: 1 }, { name: "Effort Estimation Sheet", id: 2 }];
		    
		    var getUploadedDoc = function(response) {
				console.log("getUploadedDoc");
				console.log(response);
				$scope.documents = response.data;
			};
			WebServiceFactory.getUploadedDoc().then(getUploadedDoc);

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
		    
		    $scope.downloadFile = function(objectId){
				WebServiceFactory.downloadFileFormID(objectId);
			};
			
			$scope.onDeleteClick = function(docId){
				WebServiceFactory.updateActiveStatus(docId);
				$window.location.reload();
			};
		    
		    $scope.uploadEstimationRelatedDoc = function(id,cmnt){
		    	console.log("inside insertData");
		    	 var fileTest = $("#fuUploadFilename");
		    	 var name = [ "UPLOADED_FILE_NAME"];
		    	 var marker = {
		    			 "doctypeId" : id,
		    			 "comments" : cmnt
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
		 		console.log(" length :" + formData.getAll('file').length);
		 		console.log(formData.getAll('file'));
		 		console.log(formData.getAll('name'));
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
				
				$scope.dealDetails[0].rpVersionId = $sessionStorage.rpDealVersionId;	//arvind
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

			WebServiceFactory.getDealDetails($sessionStorage.DealModel,rpVrsId).then(getDealDetails); 
		    
		    
		    
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
//			    		alert("File size:"+size);
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
