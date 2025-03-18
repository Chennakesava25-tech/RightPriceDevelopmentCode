//var app = angular.module('MasterCityApp', ['ngMessages']);
		app.controller("MasterAtosRCController", ['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$localStorage,$sessionStorage,$index){
		$scope.AtosRCData = [];
		$scope.AtosRC= [];
		$scope.country =[];
		$scope.isEndReq=false;
		$scope.saved = false;
		
		$scope.isEndReq = function(){
			$scope.isEndReq = true;
		}
	//==============================================================Get Country Details	
		var getCountryDetail = function(response) {
			console.log("get country data");
			$scope.country = response.data;
			console.log($scope.country);
			$scope.country.push({
				countryId: 99999,
				countryName: 'All',
				countryShortName: "All",	
				createdBy: null,
				createdDate: null,
				currencyCode: null,
				currencyId: null,
				exchangeRate: null,
				isActive: 1,
				isOffshoreSite: null,
				isOnlyManRCDeal: null,
				syntelFacilityCost: null,
				updatedBy: null,
				updatedDate: null,
				visaTypeArray: null,
			})
		};
		WebServiceFactory.getCountryDetail().then(getCountryDetail);

		//==============================================================Get Atos RC Details		
			var getAtosRcData1 = function(response){
				console.log(response.data)
				$scope.AtosRC =response.data;
				
				angular.forEach($scope.AtosRC,function(value,key){
					$scope.AtosRC[key].rcId = $scope.AtosRC[key].rcId;
					var startDate = []
					var sdateArray = []
					var endDate = []
					var edateArray = []
					
					startDate =	$scope.AtosRC[key].rcStartDate.split(" ")
					sdateArray = startDate[0].split("-")
					
					$scope.AtosRC[key].rcStartDate = startDate[0] 
					$scope.AtosRC[key].rcStartDate1 = sdateArray[1]+"/"+sdateArray[2]+"/"+sdateArray[0]; 
					
					endDate =	$scope.AtosRC[key].rcEndDate.split(" ")
					edateArray = endDate[0].split("-")
					$scope.AtosRC[key].rcEndDate = endDate[0] 
					$scope.AtosRC[key].rcEndDate1 =edateArray[1]+"/"+edateArray[2]+"/"+edateArray[0];
					
					$scope.AtosRC[key].rcStartDate = $scope.AtosRC[key].rcStartDate;
					$scope.AtosRC[key].rcEndDate = $scope.AtosRC[key].rcEndDate;
				});
				$scope.newData = [];
				var k = 0;
				for(var i=0;i<$scope.AtosRC.length;i++){
					for(var j=0;j<$scope.AtosRC.length;j++){
						if($scope.AtosRC[i].rcId == $scope.AtosRC[j].rcId)
							if($scope.AtosRC[i].loc == undefined){
						$scope.AtosRC[i].loc =  $scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
							}
							else{
							$scope.AtosRC[i].loc =  $scope.AtosRC[i].loc + "\n" +$scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
							}
							
						
					}	
				}
				
				var l=0;
				
				for(var i=0;i<$scope.AtosRC.length;i++){
					$scope.AtosRC[i].loc1 = [];
					for(var j=0;j<$scope.AtosRC.length;j++){
						if($scope.AtosRC[i].rcId == $scope.AtosRC[j].rcId)
							if($scope.AtosRC[i].loc1 == undefined){
							 $scope.AtosRC[i].loc1[l] =  $scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
							 l++;
							}
							else{
						 	 $scope.AtosRC[i].loc1[l] =  $scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
							 l++;
							}
					}	
					l=0;
				}
				
				
				
				var flags = [], output = [], l = $scope.AtosRC.length, i;
				for(var i=0; i<l; i++) {
				    if( flags[$scope.AtosRC[i].rcId]) continue;
				    flags[$scope.AtosRC[i].rcId] = true;
				   $scope.newData[k] = $scope.AtosRC[i] 
				k++;
			}
				console.log("-------------")
				console.log($scope.newData)
				$scope.AtosRC = [];

				$scope.AtosRC = $scope.newData;
				
			}
			WebServiceFactory.getAtosRcData().then(getAtosRcData1);
			
			$scope.exportRateCardData=function(Master,tableId){
					var exportHref=WebServiceFactory.DownloadRFExcel(tableId,'Rate_Card_Details');
			}
			
			$scope.showAtosRateCardDetails = function(rcId, approverId, approvalStatus, approverRoleId){
				if(approverId=="N/A")
				{
					approverId="";
				}
				$localStorage.rcId = rcId;
				$localStorage.rateCardApproverId = approverId;
				$localStorage.rateCardApprovalStatus = approvalStatus;
				$localStorage.rateCardApproverRoleId = approverRoleId;
				
				var browser = window.navigator.appVersion;

		        //Workaround to enable the users to download the report in IE.
		        if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
		               (browser.indexOf('MSIE 10') !== -1)) 
		        {
			        	 if(approvalStatus == 2 || approvalStatus == 3){
		                     window.open('RateCardCreationSummary','$localStorage.rcId');
//		                     window.open('RightPrice/RateCardCreationSummary','$localStorage.rcId');
		               } 
		               else{
		                     window.open('RateCardCreationDetails', '$localStorage.rcId');
//		                     window.open('RightPrice/RateCardCreationDetails', '$localStorage.rcId');
		               }
		        } 
		        else 
		        {
		        	if(approvalStatus == 2 || approvalStatus == 3)
		               {
		                     window.open('RateCardCreationSummary', '_blank', '$localStorage.rcId');
//		                     window.open('RightPrice/RateCardCreationSummary', '_blank', '$localStorage.rcId');
		               }
		               else{
		                     window.open('RateCardCreationDetails', '_blank','$localStorage.rcId');
//		                     window.open('RightPrice/RateCardCreationDetails', '_blank','$localStorage.rcId');
		               }
		        }

			}	
			$scope.searchData = function(Master){
				$scope.saved = true;
				if(Master.$valid){
					$scope.saved = false;
					$scope.searchDat(Master);
				}
			}
			
	        $scope.searchDat = function(Master){
	        
				var actions;
				var enddate;
				var startdate;
				var country ;
			//===================================================if action  is null or undefined cant set to 0 as 0 is  for approved ====================
				//=================================================== action = 5 for all ====================
				$scope.Master.RateCardAction == undefined ?  actions = 5: actions = Number($scope.Master.RateCardAction);
				$scope.Master.enddate == undefined ? enddate = null : enddate = $scope.Master.enddate
				$scope.Master.startdate == undefined ? startdate = null : startdate = $scope.Master.startdate	
				$scope.Master.country == undefined ? country = 0 :  country = $scope.Master.country.countryId;
			//===================================================if country is null or undefined search for the all countries====================
	
				country == 99999 ? country = 0 : country ;
				var sttemp = [];
				var ettemp = [];
				if(startdate != null ){
					sttemp = startdate.split("/")
					startdate = sttemp[2]+"-"+sttemp[1]+"-"+sttemp[0];
				}
				
				if(enddate != null ){
					ettemp = enddate.split("/")
					enddate = ettemp[2]+"-"+ettemp[1]+"-"+ettemp[0];
				}
			/*	startdate != null ?  startdate = startdate.split("/").join("-") : startdate ;
				
				enddate != null ? enddate = enddate.split("/").join("-") : enddate ;
				*/
				console.log(startdate);
				console.log(enddate);
				console.log(actions);
				console.log(country);
				
				var getFLTRDAtosRcData = function(response){
					 if( response.status == 205){
						 $localStorage.rcId=response.data; 
						  BootstrapDialog.show({
									title : 'Atos Rate Card',
									type : BootstrapDialog.TYPE_DANGER,
									message : 'No rate cards available for selected Criteria.',
									closable : false,
									buttons : [ {
										label : 'OK',
										action : function(dialogRef) {
											dialogRef.close();
										window.location="MasterAtosRC";
										}
									} ]
								});
						  $scope.AtosRC = [];	  
					 }
					 else{
						 if(response.data != undefined){
						 $scope.AtosRC =response.data;
						 
						 angular.forEach($scope.AtosRC,function(value,key){
								$scope.AtosRC[key].rcId = $scope.AtosRC[key].rcId;
								var startDate = []
								var sdateArray = []
								var endDate = []
								var edateArray = []
								
								startDate =	$scope.AtosRC[key].rcStartDate.split(" ")
								sdateArray = startDate[0].split("-")
								
								$scope.AtosRC[key].rcStartDate = startDate[0] 
								$scope.AtosRC[key].rcStartDate1 = sdateArray[1]+"/"+sdateArray[2]+"/"+sdateArray[0]; 
								
								endDate =	$scope.AtosRC[key].rcEndDate.split(" ")
								edateArray = endDate[0].split("-")
								$scope.AtosRC[key].rcEndDate = endDate[0] 
								$scope.AtosRC[key].rcEndDate1 =edateArray[1]+"/"+edateArray[2]+"/"+edateArray[0];
								
								$scope.AtosRC[key].rcStartDate = $scope.AtosRC[key].rcStartDate;
								$scope.AtosRC[key].rcEndDate = $scope.AtosRC[key].rcEndDate;
							});
						 
						 
						 $scope.newData = [];
							var k = 0;
							for(var i=0;i<$scope.AtosRC.length;i++){
								for(var j=0;j<$scope.AtosRC.length;j++){
									if($scope.AtosRC[i].rcId == $scope.AtosRC[j].rcId)
										if($scope.AtosRC[i].loc == undefined){
									$scope.AtosRC[i].loc =  $scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
										}
										else{
										$scope.AtosRC[i].loc =  $scope.AtosRC[i].loc + "\n" +$scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
										}
										
									
								}	
							}
							
							var l=0;
							
							for(var i=0;i<$scope.AtosRC.length;i++){
								$scope.AtosRC[i].loc1 = [];
								for(var j=0;j<$scope.AtosRC.length;j++){
									if($scope.AtosRC[i].rcId == $scope.AtosRC[j].rcId)
										if($scope.AtosRC[i].loc1 == undefined){
										 $scope.AtosRC[i].loc1[l] =  $scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
										 l++;
										}
										else{
									 	 $scope.AtosRC[i].loc1[l] =  $scope.AtosRC[j].countryName + "-" + $scope.AtosRC[j].cityName;
										 l++;
										}
								}	
								l=0;
							}
							
							
							var flags = [], output = [], l = $scope.AtosRC.length, i;
							for(var i=0; i<l; i++) {
							    if( flags[$scope.AtosRC[i].rcId]) continue;
							    flags[$scope.AtosRC[i].rcId] = true;
							   $scope.newData[k] = $scope.AtosRC[i] 
							k++;
						}
							
							$scope.AtosRC = [];

							$scope.AtosRC = $scope.newData;
						 }
					 }
				}
				WebServiceFactory.getFileredAtosRcData(country,startdate,enddate,actions).then(getFLTRDAtosRcData);

			};
			
			$scope.clear = function(Master){
				
				$scope.Master.RateCardAction=null;
				$scope.Master.enddate=null;
				$scope.Master.startdate=null;
				$scope.Master.country=null;
				
		};
	
	//========================================================== To Download File ==============================================
		
	    $scope.downloadFile = function(objectId){
	    	if(objectId == null){
				  BootstrapDialog.show({
						title : 'Atos Rate Card',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'No Attachment Found.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
	    	}
	    	else{
     		WebServiceFactory.downloadFileFormID(objectId);
	    	}
     	};
	
		}]);