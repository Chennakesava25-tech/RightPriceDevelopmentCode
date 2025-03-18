//var app = angular.module('MasterCityApp', ['ngMessages']);
		app.controller("ActiveRateCardController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index){
		$scope.AtosRCData = [];
		$scope.AtosRC= [];

			var getActiveRCData = function(response){
				$scope.activeRC = response.data;
				console.log("----------------");
				console.log($scope.activeRC)
			}
			WebServiceFactory.getActiveRCData().then(getActiveRCData);
			
			$scope.exportRateCardData=function(Master,tableId){
					var exportHref=WebServiceFactory.DownloadRFExcel(tableId,'Rate_Card_Details');
			}
			
			
		}]);