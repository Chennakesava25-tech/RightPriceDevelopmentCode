//var app = angular.module('RightPriceApp', ['ngMessages']);
		app.controller("MastersLOBController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,$index){
			/*var contextPath = $window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));*/
			 var contextPath = "/RightPrice-DAS";
			 $scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
             $scope.ShowHideView = function () {
             $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            // $scope.dateFormatRegex = /^([0-9]{2}[-/][0-9]{2}[-/][0-9]{4})|([0-9]{8})/;
                //getLobDetails();
            };
             $scope.ShowHideAdd = function () {
                $scope.AddHidden = $scope.AddHidden ? false : true;
                console.log($scope.AddHidden);
            };
             $scope.ShowHideUpdate = function () {
                $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
            };
             $scope.ShowHideUpload = function () {
                $scope.UploadHidden = $scope.UploadHidden ? false : true;
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
			
			var getLobDetails = function(response) {
		  		console.log(response);
		  		$scope.masterLob = response.data;
		  		//$scope.masterLob.isActive= "Active";
		  		$scope.lengthArr = response.data.length
		  		console.log($scope.lengthArr);
		  		for(var i =0; i <$scope.lengthArr; i++ ){
		  			$scope.masterLob[i].isActive = ($scope.masterLob[i].isActive)? "Active":"InActive";
		  		}
			};
			WebServiceFactory.getLobDetails().then(getLobDetails);
			
			
			$scope.getLobExcel = function() {
				 window.location= contextPath+"/RightPrice-DAS/downloadLobExcel"
			};
			
			
			$scope.putLobDetails = function(lobDetails){
				
				$scope.updateMasterLobForm.txtUpdateDescriptionModel = lobDetails.lobDescription;
				var dateStart = lobDetails.lobStartDate ;
				
				var date = new Date(dateStart.substring(0,10));
				
				$scope.updateMasterLobForm.txtUpdateStartDateModel = $filter('date')(dateStart.substring(0,10),'dd/MM/yyyy');
				var dateEnd = lobDetails.lobEndDate ;
				
				$scope.updateMasterLobForm.txtUpdateEndDateModel =$filter('date')(dateEnd.substring(0,10),'dd/MM/yyyy');
				
				if(lobDetails.isActive != "InActive") {
					$scope.updateMasterLobForm.cbxUpdateActiveInactive = true;
				} 
				else {
					$scope.updateMasterLobForm.cbxUpdateActiveInactive = false;
				}
			};
			
			$scope.onSubmit= false;	
			
			$scope.addNew = function(addMasterLobForm){
				$scope.onSubmit= true;
				if(addMasterLobForm.$valid){
					$scope.onSubmit= false;
					$scope.insertLobDetails();
				}
			};	
			
			$scope.updateDetails = function(updateMasterLobForm){
				$scope.onUpdate = true;
				if(updateMasterLobForm.$valid){
				$scope.onUpdate= false;
				$scope.updateLobDetails();
				}
			};	
			
			// inserting the details of LOB
			$scope.insertLobDetails = function(){
				var lobCode = $scope.addMasterLobForm.txtAddNameModel;
				console.log("Name is " +lobCode)
				var lobDescription = $scope.addMasterLobForm.txtAddDescriptionModel;
				console.log("Description is " +lobDescription);
				var lobStartDate = $scope.addMasterLobForm.txtAddStartDateModel;
				console.log("START_DATE is " +lobStartDate);
				var lobEndDate = $scope.addMasterLobForm.txtAddEndDateModel;
				console.log("END_DATE is " +lobEndDate);
				var markers = {
					"lobCode" : lobCode,
					"lobDescription" : lobDescription,
					"lobStartDate" : lobStartDate,
					"lobEndDate" : lobEndDate
				};
				console.log("Markers are");
				console.log(markers);
				$http({
					 method: 'POST',
					 url: contextPath+"/RightPrice-DAS/insertLobDetails",
					 dataType: 'json',
					 data: angular.toJson(markers),  
		            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		    	}).
		    	then(function(data) {
		    		if(data.status == 200){
		    			var message = data.data;
		    	        BootstrapDialog.show({
		    	        	title : 'Masters Lob',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : message,
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MastersLOB";
		    	        		}
		    	        	}]
		    	        });
		    		}else{
		    			 message = data.data;
		    			BootstrapDialog.show({
		    	        	title : 'Masters Lob',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : message,
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MastersLOB";
		    	        		}
		    	        	}]
		    	        });
		    		}
				},function (data) {
					$scope.displayres = data;
				    $scope.answer = 'Posting data was unsuccessful.';
				});
			
			};
			
			// updating the details of LOB
			$scope.updateLobDetails = function(){
				
				var lobCode = $scope.updateMasterLobForm.ddlUpdateNameModel.lobCode;
				console.log("Name is " +lobCode);
				var lobDescription = $scope.updateMasterLobForm.txtUpdateDescriptionModel;
				console.log("Description is " +lobDescription);
				var lobStartDate = $scope.updateMasterLobForm.txtUpdateStartDateModel;
				console.log("START_DATE is " +lobStartDate);
				var lobEndDate = $scope.updateMasterLobForm.txtUpdateEndDateModel;
				console.log("END_DATE is " +lobEndDate);
				var lobId = $scope.updateMasterLobForm.ddlUpdateNameModel.lobId;
				console.log("LOB ID is------- "+lobId);
				var checkboxval = $scope.updateMasterLobForm.cbxUpdateActiveInactive;
				console.log("value of Checkbox is "+ checkboxval);
				var isActive = 0;
				if(checkboxval) {
					isActive = 1;
				} else {
					isActive = 0;
				}
				
				
				var marker = {
						"lobCode" : lobCode,
						"lobDescription" : lobDescription,
						"lobStartDate" : lobStartDate,
						"lobEndDate" : lobEndDate,
						"lobId" : lobId,
						"isActive" : isActive
					};
				console.log(marker);
				$http({
					 method: 'POST',
					 url:  contextPath+"/RightPrice-DAS/updateLobDetails",
					 dataType: 'json',
					 data: angular.toJson(marker),  
		           headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			   	}).
			   	then(function(data) {
		   			console.log("added");
		   	        $scope.answer = 'Data has been updated successfully';
		   	        BootstrapDialog.show({
		   	        	title : 'Master Lob Details',
		   	        	type : BootstrapDialog.TYPE_PRIMARY,
		   	        	message : 'Data Updated SucessFully',
		   	        	closable : false,
		   	        	buttons : [{
		   	        		label : 'OK',
		   	        		action : function(dialogRef) {
		   	        			dialogRef.close();
		   	        			window.location = "MastersLOB";
		   	        		}
		   	        	}]
		   	        });
				},function (data) {
					$scope.displayres = data;
				    $scope.answer = 'Posting data was unsuccessful.';
				});
			};
			
			// clearing the data in insert lob 
			$scope.cancel = function ( ) {
				$scope.onSubmit= false;
				$scope.addMasterLobForm.txtAddNameModel = "";
				$scope.addMasterLobForm.txtAddDescriptionModel = "";
				$scope.addMasterLobForm.txtAddStartDateModel =  "";
				$scope.addMasterLobForm.txtAddEndDateModel = "";
			}
			
			// clearing the details in update 
			$scope.clear = function ( ) {
				$scope.onUpdate= false;
				$scope.updateMasterLobForm.ddlUpdateNameModel = "";
				$scope.updateMasterLobForm.txtUpdateDescriptionModel = "";
				$scope.updateMasterLobForm.txtUpdateStartDateModel =  "";
				$scope.updateMasterLobForm.txtUpdateEndDateModel = "";
				$scope.updateMasterLobForm.cbxUpdateActiveInactive = "";
			}
		
		}]);