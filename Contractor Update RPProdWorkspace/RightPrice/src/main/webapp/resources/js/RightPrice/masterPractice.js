//var app = angular.module('RightPrice', ['ngMessages']);
app.controller("MastersPracticeController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory, $index){
			$scope.ViewHidden = true;
			$scope.AddHidden = true;
			$scope.UpdateHidden = true;
			$scope.UploadHidden = true;
			$scope.onSave = false;
			$scope.onSearch = false;
			$scope.downloadBtn = true;
			 $scope.practiceData=[];
			$scope.alphanumaric = /^[a-zA-Z0-9() /_-]*$/;
			var contextPath = "/RightPrice-DAS";

			$scope.getPractice1= function(){
				$scope.ischange=true;
				$scope.updatePracticeForm.IsActive = true
			}
			
			var getPractice = function(response) {
				console.log("practice data");
				console.log(response);
				$scope.practiceData = response.data;
			};
			WebServiceFactory.getPractice().then(getPractice);
			
			$scope.ShowHideView = function() {
				$scope.ViewHidden = $scope.ViewHidden ? false : true;
			};
			$scope.ShowHideAdd = function() {
				$scope.AddHidden = $scope.AddHidden ? false : true;
			};
			$scope.ShowHideUpdate = function() {
				$scope.UpdateHidden = $scope.UpdateHidden ? false : true;
			};
			$scope.ShowHideUpload = function() {
				$scope.UploadHidden = $scope.UploadHidden ? false : true;
			};
			$scope.moveTop = function() {
				$location.hash('PageHeading');
				$anchorScroll();
			};
			$scope.moveBottom = function() {
				$location.hash('includedFooter');
				$anchorScroll();
			};
			$scope.onSearchClick = function(viewSubPracticeForm) {
				$scope.onSearch = true;
				$scope.downloadBtn = true;
				
				if($scope.viewSubPracticeForm.practiceModel != undefined){
					$scope.onSearch= false;	
					$scope.downloadBtn = false;
					$scope.viewSubPracticeData($scope.viewSubPracticeForm.practiceModel);
				}
			};
			$scope.viewSubPracticeData = function(practiceId){
				var viewSubPracticeData = function(response) {
					$scope.subpracView = response.data;
				};
				WebServiceFactory.viewSubPracticeData(practiceId).then(viewSubPracticeData);
			};

			$scope.onSubmit= false;	
			$scope.save = function(addPracticeForm){
				$scope.onSubmit= true;
				if(addPracticeForm.$valid){
					$scope.onSubmit= false;	
					$scope.addPractice();
				}
			};
	

			$scope.onCancel = function(){
				$scope.onSubmit= false;	
				$scope.addPracticeForm.practiceModelName=null;
			}
			


			$scope.addPractice = function()
			{
				var markers = {
				"description":$scope.addPracticeForm.practiceModelName
				
				};
				console.log("Add  Marker..................");
				console.log(markers);
				var addPracticeDetails = function(response) 
				{
					if(response.status == 200)
					{
						BootstrapDialog.show
						({
							title : 'Practice Details',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'Data inserted SucessFully',
							closable : false,
							buttons : 
							[{
								label : 'OK',
								action : function(dialogRef) 
								{
									dialogRef.close();
									window.location = "MastersPractice";
								}
							}]
		    	        });
			        }
					else if(response.status == 203)
			        {
						BootstrapDialog.show
						({
							title : 'Practice Details',
							type : BootstrapDialog.TYPE_DANGER,
							message : response.data,
							closable : false,
							buttons : 
							[{
								label : 'OK',
								action : function(dialogRef) 
								{
									dialogRef.close();
									window.location = "MastersPractice";
								}
		    	        	}]
		    	        }); 	
			        }
					else
					{
						BootstrapDialog.show
						({
							title : 'Practice Details',
							type : BootstrapDialog.TYPE_DANGER,
							message : 'Currently We are facing technical issues, please try again later.',
							closable : false,
							buttons : 
							[{
								label : 'OK',
								action : function(dialogRef) 	
								{
									dialogRef.close();
									window.location = "MastersPractice";
		    	        		}
		    	        	}]
		    	        });
			        }
				}
					
				console.log("marker");
				console.log(markers);
		    	
				WebServiceFactory.addPracticeDetails(markers).then(addPracticeDetails);
			}
			
/*			$scope.onUpdateClick = function(updatePracticeForm) {
				
				if(updateSubPracticeForm.$valid){
					$scope.onUpdate= false;
					//$scope.updateSubPractice();
				}
			}*/
			
			$scope.onUpdate=false;
			$scope.onUpdateClick = function(updatePracticeForm){
				$scope.onUpdate = true;
				if(updatePracticeForm.$valid){
					$scope.onUpdate= false;
					$scope.updatePracticeDetails();
				}
				
						    	 
			};
			
			
			$scope.updatePracticeDetails = function(){
				//var markers = [];
				if($scope.updatePracticeForm.IsActive == true||updatePracticeForm.IsActive == 1){
					$scope.updatePracticeForm.IsActive =  1;
				}
				else{
					$scope.updatePracticeForm.IsActive =  0;
				}
				
				var markers = {
						"practiceId": $scope.updatePracticeForm.practiceModel,
						"description":$scope.updatePracticeForm.UpdatepracticeModel,
						"isActive":$scope.updatePracticeForm.IsActive
				};
				console.log("chk data");
				console.log(markers);
				
				
				var updatePracticeDetails = function(response) {
					console.log(response);
					if(response.status == 200) {
						BootstrapDialog.show({
							title : 'Practice Details',
							type : BootstrapDialog.TYPE_PRIMARY,
							message : 'Data Updated SucessFully',
							closable : false,
							buttons : [{
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									window.location = "MastersPractice";
								}
							}]
						});
					}
					else if(response.status == 203)
			        {
						BootstrapDialog.show
						({
							title : 'Practice Details',
							type : BootstrapDialog.TYPE_DANGER,
							message : response.data,
							closable : false,
							buttons : 
							[{
								label : 'OK',
								action : function(dialogRef) 
								{
									dialogRef.close();
									window.location = "MastersPractice";
								}
		    	        	}]
		    	        }); 	
			        }
					else {
						BootstrapDialog.show({
						title : 'Practice Details',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Currently We are facing technical issues, please try again later.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location = "MastersPractice";
								}
							} ]
						});
					}
					
						
		   	   }
				WebServiceFactory.updatePracticeDetails(markers).then(updatePracticeDetails);
			};
			

		}]);