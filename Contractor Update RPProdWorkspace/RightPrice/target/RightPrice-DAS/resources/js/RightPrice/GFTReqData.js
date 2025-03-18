//var app = angular.module('MasterCityApp', ['ngMessages']);
		app.controller("MasterAtosRCController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$localStorage,$sessionStorage,$index){
			$scope.dataCaptured = true;
			$scope.DealData = [];
			var getDealDetailsForGFT = function(response) {
				console.log("get country data");
				$scope.DealData = response.data;
				console.log($scope.DealData);
				$scope.dataCaptured = false;	
			};
			WebServiceFactory.getDealDetailsForGFT().then(getDealDetailsForGFT);

			$scope.exportRateCardData=function(Master,tableId){
				var exportHref=WebServiceFactory.DownloadRFExcel(tableId,'Deal_Details');
		}
			
		}]);