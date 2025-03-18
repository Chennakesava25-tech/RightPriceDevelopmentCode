//var app = angular.module('RightPrice', []);
app.controller("FPDealCreationAddContractorRoleController", ['$scope','$location','$anchorScroll','$http','$window','$sessionStorage','WebServiceFactory','$filter', function($scope,$location,$anchorScroll,$http,$window,$sessionStorage,WebServiceFactory,$filter, $index){
			  
	$scope.stake = 0;
			$scope.Prev = function() {
				window.location = 'FPDealCreationRoleSelection';
			};
			$scope.Next = function() {
				window.location = 'FPDealCreationStaffing';
			};
			$scope.onCancelClick = function() {
				window.location = "/";
			};
			
			$scope.onAddClick = function(){
				console.log($scope.addContactorRole);
				if($scope.addContactorRole != ""){
					$scope.addContactorRole.push({});
				}else{
						$scope.addContactorRole=[];
						$scope.addContactorRole.push({});
				}
		    };
			$scope.onSaveClick = function() {
				console.log("inside onSaveClick");
					$scope.saveContractorRole();
			};
			
			$scope.saveContractorRole = function(){
				var markers = [];
				console.log($scope.addContactorRole);
				if($scope.addContactorRole.length == 0){

				}else{
				for(var i=0;i<$scope.addContactorRole.length;i++){
					markers.push({ 	
						"contractor_Role_Id" : $scope.addContactorRole[i].contractor_Role_Id,
						"contactorRole" :	$scope.addContactorRole[i].contactorRole,
						"onsiteCost" : $scope.addContactorRole[i].onsiteCost,
						"offshoreCost" : $scope.addContactorRole[i].offshoreCost,
						"onsiteRate" : $scope.addContactorRole[i].onsiteRate,
						"offshoreRate" : $scope.addContactorRole[i].offshoreRate,
						"comments" : $scope.addContactorRole[i].comments
					});
				}
				}
		    	console.log(markers);
				
					var saveContractorRole = function(response) {
		    	        $scope.answer = 'Data has been added successfully';
		    	        BootstrapDialog.show({
		    	        	title : 'ContractorRole',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'ContractorRole Added SucessFully',
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
					WebServiceFactory.saveContractorRole(markers).then(saveContractorRole);
			};
			var viewcontractorRoleList = function(response) {
				console.log("getcontractorRoleList");
				console.log(response);
				$scope.addContactorRole = response.data;
			};
			WebServiceFactory.viewcontractorRoleList().then(viewcontractorRoleList);
			
			$scope.removeClick = function(id,row){
				var index = $scope.addContactorRole.indexOf(row);
				$scope.addContactorRole.splice(index, 1);
			}
			
			
			 var rpVrsId = $sessionStorage.rpDealVersionId;	
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
			
			/*$scope.removeClick = function(id,row){
				console.log($scope.addContactorRole);
				var roleid = $scope.addContactorRole[id-1].contractor_Role_Id;
				console.log(roleid);
				if(roleid == undefined){
					console.log("here"+row);
					var index = $scope.addContactorRole.indexOf(row);
					console.log(index);
					$scope.addContactorRole.splice(index, 1);
				}else{
				var removeClick = function(response) {
					$window.location.reload();
					console.log("Deleted Successfully");
				};
				WebServiceFactory.removeClick(roleid).then(removeClick);
				}
			};*/
		} ]);
		
		
		
		app.directive('blurToCurrency', function($filter){
		  return {
		    scope: {
		      amount  : '='
		    },
		    link: function(scope, el, attrs){
		      el.val($filter('currency')(scope.amount,"",2));
		      
		      el.bind('focus', function(){
		        el.val(scope.amount);
		      });
		      
		      el.bind('input', function(){
		        scope.amount = el.val();
		        scope.$apply();
		      });
		      
		      el.bind('blur', function(){
		        el.val($filter('currency')(scope.amount,"",2));
		      });
		    }
		  };
		});