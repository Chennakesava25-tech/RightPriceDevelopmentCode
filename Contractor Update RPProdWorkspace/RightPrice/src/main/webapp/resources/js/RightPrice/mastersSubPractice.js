//var app = angular.module('RightPrice', ['ngMessages']);
app.controller("MastersSubPracticeController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory, $index){
			$scope.ViewHidden = true;
			$scope.AddHidden = true;
			$scope.UpdateHidden = true;
			$scope.UploadHidden = true;
			$scope.onSave = false;
			$scope.onSearch = false;
			$scope.downloadBtn = true;
			$scope.alphanumaric = /^[a-zA-Z0-9() /_-]*$/;
			var contextPath = "/RightPrice-DAS";
			var getPractice = function(response) {
				$scope.practice = response.data;
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
			$scope.onSaveClick = function(addSubPracticeForm) {
				$scope.onSave = true;
				if(addSubPracticeForm.$valid){
					$scope.onSave= false;
					$scope.addSubPractice();
				}
			}
			$scope.getSubPractice = function(pracId){
				var getSubPractice = function(response) {
				    $scope.subpractice = response.data;
				};
				WebServiceFactory.getSubPractice(pracId).then(getSubPractice);
			}
			$scope.addSubPractice = function(){
				var practiceId = $scope.addSubPracticeForm.practiceModel;
				var subpractice = $scope.addSubPracticeForm.subPracticemodel;
				
				var markers = {
						"practiceId" :	practiceId,
						"subPracticeName" : subpractice
					};
					var addSubPractice = function(response) {
		    	        $scope.answer = 'Data has been added successfully';
		    	        BootstrapDialog.show({
		    	        	title : 'SubPractice',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'SubPractice Added SucessFully',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			$window.location.reload();
		    	        		}
		    	        	}]
		    	        });
					};
					WebServiceFactory.addSubPractice(markers).then(addSubPractice);
			}
			$scope.onCancelClick = function(){
				$window.location.reload();
			}
			//Update SubPractice
			$scope.onUpdateClick = function(updateSubPracticeForm) {
				$scope.onUpdate = true;
				if(updateSubPracticeForm.$valid){
					$scope.onUpdate= false;
					$scope.updateSubPractice();
				}
			}
			$scope.updateSubPractice =function(){
				console.log($scope.updateSubPracticeForm.subpracticeModel);
				var pracid = $scope.updateSubPracticeForm.practiceModel;
				var subpracid = $scope.updateSubPracticeForm.subpracticeModel.subpracticeId;
				var subpracticeName = $scope.updateSubPracticeForm.subpracticeModel.subpracticeName;
				if($scope.updateSubPracticeForm.StatusModel == undefined){
					var status = 0;
				}else if($scope.updateSubPracticeForm.StatusModel == true){
					var status = 1;
				}else if($scope.updateSubPracticeForm.StatusModel == false){
					var status = 0;
				}
				
				var markers = {
						"practiceId" :	pracid,
						"subPracId" : subpracid,
						"activeStatus" : status,
						"subPracticeName" : subpracticeName
					};
				var updateSubPractice = function(response) {
	    	        $scope.answer = 'Data has been updated successfully';
	    	        BootstrapDialog.show({
	    	        	title : 'SubPractice',
	    	        	type : BootstrapDialog.TYPE_PRIMARY,
	    	        	message : 'SubPractice Updated SucessFully',
	    	        	closable : false,
	    	        	buttons : [{
	    	        		label : 'OK',
	    	        		action : function(dialogRef) {
	    	        			dialogRef.close();
	    	        			$window.location.reload();
	    	        		}
	    	        	}]
	    	        });
				};
				WebServiceFactory.updateSubPractice(markers).then(updateSubPractice);
			}
			
			$scope.getSubPracticeExcel = function() {
				var practiceId = $scope.viewSubPracticeForm.practiceModel;
				var practiceName  = $.grep($scope.practice, function (practice) {
	                 return practice.practiceId == practiceId;
	             })[0].practiceName;
				window.location= contextPath+"/RightPrice-DAS/downloadSubPracticeExcelData/"+practiceId+"/"+practiceName
			};
			
			$scope.getStatus = function(){
				var status = $scope.updateSubPracticeForm.subpracticeModel.statusModel;
				if(status == 0){
					$scope.updateSubPracticeForm.StatusModel = false;
				}else if(status == 1){
					$scope.updateSubPracticeForm.StatusModel = true;
				}
			}
		}]);