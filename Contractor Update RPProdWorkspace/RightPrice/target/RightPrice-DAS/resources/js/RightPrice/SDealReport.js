//var app = angular.module('MasterCityApp', ['ngMessages']);
		app.controller("SDealReportController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$localStorage,$sessionStorage,$index){
			$scope.vertical = [];
			var contextPath = "/RightPrice-DAS";
//----------------------------------------------------------------------------------------------vertical data
			var verticalDetails=function(response){
				console.log("------------------------------- vertical data")
				console.log(response.data)
				$scope.vertical = response.data;
				var size = $scope.vertical.length + 1;
				
				$scope.vertical.push({
					"verticalName": "All" ,
					"verticalId" : 9999999
				})
			}
			WebServiceFactory.getVertical().then(verticalDetails)
			
			var statusData=function(response){
				console.log("------------------------------- status data")
				console.log(response.data)
				$scope.status = response.data;
			}
			WebServiceFactory.getStatusData().then(statusData)
			
			$scope.exportRateCardData = function(dealSummery){
				var startdate;
				var status;
				var vertical; 
				var verticalName; 
				
				$scope.dealSummery.startdate == undefined ? startdate = null : startdate = $scope.dealSummery.startdate;
				$scope.dealSummery.status == undefined ? status = null : status = $scope.dealSummery.status.description; 
				$scope.dealSummery.vertical == undefined ? vertical = 9999999 : vertical = $scope.dealSummery.vertical.verticalId ;
				$scope.dealSummery.vertical == undefined ? verticalName = null : verticalName = $scope.dealSummery.vertical.verticalName
				
				var sdate =  [];
				if(startdate!=null){
				sdate =  startdate.split("/")
				startdate= sdate[2]+"-"+sdate[1]+"-"+sdate[0]
				}
				
				enddate= null;
			   window.location= contextPath+"/RightPrice-DAS/downloadSDealReportExcel/"+startdate+"/"+enddate+"/"+status+"/"+vertical+"/"+verticalName;
			   
			}
			
		}]);