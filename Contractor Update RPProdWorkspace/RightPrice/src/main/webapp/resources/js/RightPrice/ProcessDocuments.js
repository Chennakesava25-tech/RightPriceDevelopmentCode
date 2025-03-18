//var app = angular.module('RightPrice', ['ngMessages']);
app.controller("processDocumentController", [
		'$scope','$location','$anchorScroll','$http','$window','WebServiceFactory',
		function($scope, $location, $anchorScroll, $http,$window,WebServiceFactory, $index) {
			var contextPath = "/RightPrice-DAS";
			$scope.SearchHidden = true;
			$scope.AddHidden = true;
			$scope.UpdateHidden = true;
			$scope.UploadHidden = true;
			$scope.onViewSearch = false;
			$scope.onAddSearch = false;
			$scope.onAddSave = false;
			$scope.onUpdateSearch = false;
			$scope.onUpdateAllownce = false;
			$scope.downloadBtn = true;
			$scope.isActiveEnabled = false;
			$scope.upload=false;
			$scope.VideoHidden=true;
			
			var currentYr = new Date().getFullYear();
		    var range = [];
		    range.push(currentYr-1);
		    for (var i = 0; i < 4; i++) {
		    	range.push(currentYr + i);
		    }
		    $scope.years = range;
		    
			$scope.ShowHideSearch = function() {
				$scope.SearchHidden = $scope.SearchHidden ? false : true;
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
			$scope.ShowHideVideo = function() {
				$scope.VideoHidden = $scope.VideoHidden ? false : true;
				};
			$scope.moveTop = function() {
				$location.hash('PageHeading');
				$anchorScroll();
			};
			$scope.moveBottom = function() {
				$location.hash('includedFooter');
				$anchorScroll();
			};
		
			$scope.downloadVideos = function(type)
			{
				alert("downloding");
			var downloadVideos = function(response)
			{
			console.log(response);
			
			if((response.data)>1)
			{
			BootstrapDialog.show({
			title : 'Process Documents',
			type : BootstrapDialog.TYPE_PRIMARY,
			message : 'Downloaded successfully',
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
			WebServiceFactory.downloadVideos(type).then(downloadVideos);
			};
			
			
	}]);
