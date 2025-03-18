"use strict";
app.controller("TMDealCreationStaffingController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','tmDealStaffingService', '$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,tmDealStaffingService, $localStorage, $sessionStorage, $index) {
	  var contextPath = "/RightPrice-DAS";		 
		$scope.fpDealStaffing = {};
		  $scope.isVersionAvailable = false;
		var rpVrsId = $localStorage.rpDealVersionId;	
		$scope.yearHeaderlen=0;
			$scope.DealStaffingDetailsHidden = true;
			 $scope.UploadHidden = true;
				$scope.upload = false;
				var userType = sessionStorage.getItem('userType');
				var countryId=0;
				 $scope.domesticDisabled=true;
				 $scope.deputedDisabled=true;
				 $scope.shortTermDisabled=true;
				 $scope.offshoreDisabled=false;
			 //Start Added by arvind
             $scope.ShowHideDealStaffingDetails = function () {
                $scope.DealStaffingDetailsHidden = $scope.DealStaffingDetailsHidden ? false : true;
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
					
			
			$scope.Prev = function()
		    {   
		        window.location='TMDealCreationRoleSelection';
		    }; 
		     $scope.Next = function()
		    { 
		    	 $localStorage.rpDealVersionId = $localStorage.rpDealVersionId;
		    	 $sessionStorage.diffEqualZero = $sessionStorage.diffEqualZero;
		       // window.location='TMDealCreationProjectSpecificCost';
		    	 //window.location='TMDealCreationUploadEstimationRelatedDocuments';
		    	 window.location='TMDealCreationSummary';
		    }; 		  
		    
		    //colspan for before year header
		    $scope.colspan = 14;
		    
		    //Year header list
		    $scope.yearHeader=[];
		    
		    //Month header list
		    $scope.monthHeader=[];
		    
		    //tab array
		    $scope.tabArray=[2,3,4,5,6];
		    
		    //onsite local staffing
		    $scope.onsiteLocal = [];
		    $scope.onsiteLocalSumTotal = [];
		    $scope.onsiteLocal_Contractor = []
		    $scope.offshore_Contractor = []
		    $scope.onsiteGross = []
		    $scope.staffingGross = []
		    $scope.onsiteLocal_ContrSumTotal = [];
		    $scope.offshore_ContrSumTotal = []
		    $scope.onsiteGrossSumTotal = [];
		    $scope.staffingGrossSumTotal = [];
		    //onsite deputed staffing
		    $scope.onsiteDeputed = [];
		    $scope.onsiteDeputedSumTotal = [];

		    //onsite short term staffing
		    $scope.onsiteShortTerm= [];
		    $scope.onsiteShortTermSumTotal = [];
		    
		    //onsite sub total
		    $scope.onsiteTotal = [];
		    $scope.onsiteSumTotal = [];
		    
		    //onsite contractor
		    $scope.onsiteContractor=[];
		    $scope.onsiteSumTotalContractor=[];
		    
		    //offshore sub total
		    $scope.offshoreTotal = [];
		    $scope.offshoreSumTotal = [];
		    $scope.offshoreSumTotalContr = [];
		    $scope.offshoreTotalContr = [];
		    //total staffing
		    $scope.totalStaffing = [];
		    $scope.totalStaffingContr = [];
		    $scope.totalSumTotalStaffing = [];
		    
		    $scope.onsiteLocalAllYears=[]; 
		    $scope.contryList = $sessionStorage.ContryList;
		    //console.log($scope.dealTowerId);*/
		    $scope.chkBoxSelectedCountryList = $scope.contryList;
		   // $scope.tower = $sessionStorage.dealTower_Id;
		    $scope.cityList = $sessionStorage.CityList;
		    
		   /*   $scope.onloadCoun_City = function()
			{
				var get_onloadCoun_City = function(response)
				{
					$scope.cityList=[];
					$scope.cityList.push({
	    				"countryId":response.data[0].countryId,
						"countryName": response.data[0].countryName,
						"cityId":response.data[0].cityId,
						"cityName":response.data[0].cityName,
						"utilization": response.data[0].utilization
	    			});
					$sessionStorage.citiId2staffing=response.data[0].cityId;
					//$scope.cityList=response.data;
					
				}
				WebServiceFactory.get_onloadCoun_City($localStorage.rpDealVersionId).then(get_onloadCoun_City);					
			};*/
		    
		    var rpVrsId = $localStorage.rpDealVersionId		
		    /*var countryID=$scope.chkBoxSelectedCountryList[0].countryId;		    
		    $scope.getCities = function(countryID){	    		    
			  //  $scope.getCities = function(countryId){
			    	 var city = [];
			    	angular.forEach($scope.cityList, function (value, key) {
			    		if(value.countryId == countryId ){
			    			city.push(value);
			    		}
			    	});
			    	$scope.cityArray = city;
			    }; */
		
		    		var getContry_CityDetails=function(response)
		    		{
		    			console.log("Country Details");
		    			console.log(response.data);
		    			 if(response.data.length>0) 
		    				 {
		    				 	$scope.contry_cityArr=response.data;
		    				 	countryId= $scope.contry_cityArr[0][0];
		    					$scope.countryName = $scope.contry_cityArr[0][2];
		    					
		    					var getVisaTypesbyCountry = function(response) 
		    		  			{
		    		  			 	console.log("Visa type");
		    		  			 	console.log(response.data);
		    		  				$scope.visaTypeDetails = response.data;
		    		  				angular.forEach($scope.visaTypeDetails, function (value, key) {
		    		  		  			if($scope.visaTypeDetails[key].visaTypeId == 1 && $scope.visaTypeDetails[key].isStaffing == 1)
		    		  		  				{
		    		  		  					$scope.domesticDisabled=false;
		    		  		  					$scope.domstaffing='Domestic';
		    			  		  				
		    		  		  				}
		    		  		  			
		    			  		  		if($scope.visaTypeDetails[key].visaTypeId == 2 && $scope.visaTypeDetails[key].isStaffing == 1)
		    				  				{
		    				  					$scope.deputedDisabled=false;
		    				  					$scope.depstaffing='Deputed';
		    				  					
		    				  				}
		    		  		  		
		    				  		  	if($scope.visaTypeDetails[key].visaTypeId == 3 && $scope.visaTypeDetails[key].isStaffing == 1)
		    				  				{
		    						  		  
		    				  					$scope.shortTermDisabled=false;
		    				  					$scope.shrtstaffing='ShortTerm';
		    				  				}
		    		  		  	
		    		  	               });
		    		  				
		    		  				
		    		  				
		    		  			};
		    		  			WebServiceFactory.getVisaTypes(countryId).then(getVisaTypesbyCountry);
		    					
		    				 }				
		    		};	
		    		WebServiceFactory.getContry_City(rpVrsId).then(getContry_CityDetails);
		    		
		    	 
		    	
		    
		    $scope.downloadFileWithFileName = function(dealAttachmentId){
				WebServiceFactory.downloadFileWithFileName(dealAttachmentId);
			};
			
			var getVersionData = function(response) {
				console.log("get versionDetails Data..");
				console.log(response);
				$scope.getVersionData=response.data;
				
				if($scope.getVersionData!=undefined)
				{
					$localStorage.pageTracker=$scope.getVersionData[0].pageTrackerStatus;
					$localStorage.CurrentApprovalStatus=$scope.getVersionData[0].currentApprovalStatus;
					if(userType == 'Delivery')
						{
						if(($localStorage.CurrentApprovalStatus == null || $localStorage.CurrentApprovalStatus == 1 ||
								$localStorage.CurrentApprovalStatus == 4 ) && $localStorage.pageTracker<3)
							{	BootstrapDialog.show({
								title : 'T&M Deal Creation - Staffing',
								type : BootstrapDialog.TYPE_DANGER,
								message : "Please save on the previous screen to proceed ahead",
								closable : false,
								buttons : [ {
									label : 'OK',
									action : function(
											dialogRef) {
										dialogRef.close();
										if($localStorage.pageTracker==2)
										{
											window.location="TMDealCreationRoleSelection";
										}
										else if($localStorage.pageTracker==1)
										{
											window.location="TMDealCreationRateCardAndProjectDetails";
										}
									else
										{
											window.location="TMDealCreationDetails";
										}
										
									}
								} ]
							});	
								
							}
						
					}
						}
					
				
				
			};
			WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
		    
			var getVersionAttachment =function(response)
	    	{
	    		console.log("Attachment Data");
	    		console.log(response);
	    		$scope.versionAttachment=response.data;
	    		if($scope.versionAttachment!=undefined)
	    			{
	    			
	    			$filter('orderBy')($scope.versionAttachment, 'createdOn');
	    			angular.forEach($scope.versionAttachment, function(value, key) {
	    				angular.forEach($scope.towerdetails, function(value2, key2) {
	    					if($scope.versionAttachment[key].dealAutoTowerId==$scope.towerdetails[key2].dealautoTowerId)
	    					{
	    						$scope.versionAttachment[key].towerName=value2.towerName;
	    					}
	    				});
	    			});
	    			
	    			angular.forEach($scope.versionAttachment, function(value, key) {
	    						
	    						var strMain =$scope.versionAttachment[key].createdOn;
	    						var arrSplit = [];
	    						arrSplit = strMain.split(" ");
	    						$scope.versionAttachment[key].createdOn = $filter('date')(arrSplit[0],'dd/MM/yyyy');
	    			});
	    			
	         }
	    	};
	    	WebServiceFactory.getAttachment($localStorage.rpDealVersionId,$sessionStorage.citiId2staffing).then(getVersionAttachment);
		   
	    	
	    	
			    var getTowerDetails = function(response){
			    	console.log("Tower Called");
			    	console.log(response.data);
			    	$scope.tower = response.data;
			    	 $scope.towerIdBySeesion = $scope.tower[0].dealTowerId;	
			    	/*if($scope.tower.length==1)
			    		{
			    		$scope.frmStaffing.dealStaffingDetailsTowerModel = $scope.tower[0].dealTowerId; //$scope.tower; 
			    			
			    		}*/
			    };			  
			    WebServiceFactory.getTowerDetails($localStorage.rpDealVersionId).then(getTowerDetails);
			    //End
			   
				 //$scope.towerIdBySeesion = $localStorage.dealTowerId;
			   
				//  $scope.cityIdBySession = $sessionStorage.CityList[0].cityId;
				 //$scope.cityIdBySession = $sessionStorage.city_Id;
			    
			 
			    
			    
			    
				var vartemp=0;	
				
				$window.onload = function() 
				{
					
					//WebServiceFactory.getTMDealStaffingData($sessionStorage.staffing_header.cityId,$sessionStorage.rpDealVersionId).then(getRateCardInfo);
					//$scope.getTMDealStaffingData($sessionStorage.ssnCity_id,$sessionStorage.rpDealVersionId);
					if($sessionStorage.sele2stfng==0)
						{
						 	$sessionStorage.sele2stfng=1
							$scope.getTMDealStaffingData();
							
						}
					
					if($sessionStorage.varCheck>0)
						{
						
							/*if(vartemp!=0)
							{*/
								/*temp=0;
								var frm = $sessionStorage.frm;	
								$scope.getFpDealStaffingData(frm);*/					
								//$scope.getFpDealStaffingData($sessionStorage.city_Id,$scope.towerIdBySeesion);
								//$scope.getFpDealStaffingData( $scope.cityIdBySession,$scope.towerIdBySeesion);
						//if(typeof $sessionStorage.staffing_header== 'number')
						/*if($sessionStorage.staffing_header.monthYearHeader6== 'Summary')  
							{*/
								vartemp=1;
								$scope.getTMDealStaffingData($sessionStorage.staffing_header);
								 $scope.setYearHeaderFinal('Summary');
							
							/*}*/
								
							//}
						}
				
					
				 
				}; 
			    
			    var getStaffingcontractorRoleList = function(response) {
					console.log("getStaffingcontractorRoleList");
					console.log(response);
					$scope.staffingContactorRoleList = response.data;
				};
				WebServiceFactory.getStaffingcontractorRoleList().then(getStaffingcontractorRoleList);
		    //end contractor 
		    
				$scope.autoDisplayData = function()
				{
					$scope.frmStaffing.dealStaffingDetailsCountryModel =  $sessionStorage.ContryList[0].countryName;
					$scope.countryName=$sessionStorage.ContryList[0].countryName;
				};
				
				
		   // var temp=0;  //a
				$scope.disabledflag1=false;	
		    $scope.getTMDealStaffingData = function(frm){
		    	$scope.disabledflag1=true;
		    	$scope.clearData();
		    	
		    	$sessionStorage.frmModel=frm;
		    	//$sessionStorage.city_Id= 2;//frm.dealStaffingDetailsCityModel.cityId;
		    	//$sessionStorage.dealTower_Id= frm.dealStaffingDetailsTowerModel.dealTowerId;
		    	//put city id and tower id as input
		    	//frm.dealStaffingDetailsCityModel.cityId=1;
		    	//frm.dealStaffingDetailsTowerModel.dealTowerId=9669;
		    	var getRateCardInfo = function(response) {
		    		/*console.log(frm);*/
		    		console.log("Response is:");
		    		console.log(response)
		    		$scope.data=response.data;
		    		$scope.disabledflag1=false;
		    		//var testContractor=$scope.data[0].isContractor;
		    		var header = response.data[0].staffingDetailsHeader;
		    		$sessionStorage.staffing_header=response.data[0].staffingDetailsHeader;
		    		$scope.colspan = 3;
		    		//create year header
		    		
		    		$scope.yearHeaderlen=0;
		    		
		    		
		    		/*if(temp==0) //a
		    			{*/
		    			tmDealStaffingService.createYearHeader($scope,header);
		    				//temp=1;
		    			//}	
				    		//set visa id 2 data(Onsite Deputed)
				    		tmDealStaffingService.setOnsiteDeputedData($scope);
		    		//	}
		    		
		    		 $scope.autoSummaryData();
		    		 //run should only 1 times after selection pages
		    		 if($sessionStorage.varCheck==0)
		    			 {
		    			 $sessionStorage.varCheck=1;
		    			 	$window.onload();
		    			 	
		    			 }
		    		
		    	};
		    	if(vartemp==1)
		    		{
		    		//WebServiceFactory.getTMDealStaffingData($sessionStorage.staffing_header.cityId,$sessionStorage.staffing_header.dealTowerId).then(getRateCardInfo);
		    		WebServiceFactory.getTMDealStaffingData($sessionStorage.citiId2staffing,$localStorage.rpDealVersionId).then(getRateCardInfo);
		    		vartemp=0;
		    		}
		    	else
		    		{
		    		//WebServiceFactory.getTMDealStaffingData(frm.dealStaffingDetailsCityModel.cityId,frm.dealStaffingDetailsTowerModel.dealTowerId).then(getRateCardInfo);
		    		WebServiceFactory.getTMDealStaffingData($sessionStorage.citiId2staffing,$localStorage.rpDealVersionId).then(getRateCardInfo);
		    		}
		    	
		    	//WebServiceFactory.getFpDealStaffingData($sessionStorage.city_Id,$sessionStorage.dealTower_Id).then(getRateCardInfo);
		    	
		    };
		    
		    	/*$scope.getFpDealStaffingData2 = function(){
		    	console.log("ffffffffffffffffffrm");
		    	console.log(frm);
		    	console.log(frm.dealStaffingDetailsCityModel.cityId);
		    	console.log(frm.dealStaffingDetailsTowerModel.dealTowerId);
		    	
		    	$sessionStorage.city_Id= frm.dealStaffingDetailsCityModel.cityId;
		    	$sessionStorage.dealTower_Id= frm.dealStaffingDetailsTowerModel.dealTowerId;
		    	//put city id and tower id as input
		    	//frm.dealStaffingDetailsCityModel.cityId=1;
		    	//frm.dealStaffingDetailsTowerModel.dealTowerId=9669;
		    	var getRateCardInfo = function(response) {
		    		console.log(frm);
		    		console.log("Response is:");
		    		console.log(response)

		    		$scope.data=response.data;
		    		//var testContractor=$scope.data[0].isContractor;
		    		var header = response.data[0].staffingDetailsHeader;
		    		$scope.colspan = 3;
		    		
		    		//create year header
		    		if(temp==0) //a
		    			{
		    			tmDealStaffingService.createYearHeader($scope,header);
		    				temp=1;
		    			//}
		    		
		    			}
		    		//set visa id 2 data(Onsite Deputed)
		    		tmDealStaffingService.setOnsiteDeputedData($scope);
		    			//}
		    		
		    	};
		    	WebServiceFactory.getFpDealStaffingData(frm.dealStaffingDetailsCityModel.cityId,frm.dealStaffingDetailsTowerModel.dealTowerId).then(getRateCardInfo);
		    	WebServiceFactory.getFpDealStaffingData($sessionStorage.city_Id,$sessionStorage.dealTower_Id).then(getRateCardInfo);
		    };*/
		    
		    //Set Year Header
		    $scope.anyChange=10;
		    $scope.setYearHeaderFinal = function(yearHeader){
		    	$scope.currentMonthYearHeader = yearHeader;  // yearHeader.year;		    	
		    	var flag=true;
			    	if($scope.anyChange==1000)
	    			{
	    			  //$scope.totalArray = [];
	    			 //   $scope.marker=[];
			    		
			    		if($localStorage.currentApprovalStatus == 1 || $localStorage.currentApprovalStatus == 4)
			    			{
			    				$scope.save();
			    			}
	    				
	    			}    	
	    		$scope.summaryFlag = ["5"];    		
	    		angular.forEach($scope.data, function (value, key) {
		    		if(flag){
			    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
			    			//create month header for year header
			    			tmDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
			    			flag = false;
			    			
			    		}
		    		}
	    	});
		    	
		    };
		    $scope.setYearHeader = function(yearHeader){
		    	//alert('setYearHeader');
		    	console.log(yearHeader);
		    	$scope.currentMonthYearHeader = yearHeader.year;		    	
		    	var flag=true;	    	
		    	
		    	if($scope.currentMonthYearHeader=='Summary')
		    	{
		    		if($scope.anyChange==1000)
		    			{
		    			if($localStorage.currentApprovalStatus == 1 || $localStorage.currentApprovalStatus == 4)
		    			{
		    				$scope.save();
		    			}
		    			}
		    	
		    		$scope.summaryFlag = ["5"];
		    		
		    		angular.forEach($scope.data, function (value, key) {
			    		if(flag){
				    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
				    			//create month header for year header
				    			tmDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
				    			flag = false;
				    			
				    		}
			    		}
			    	});
		    		
		    	}
		    	else
		    	{
		    		$scope.summaryFlag = [];
		    		
		    		angular.forEach($scope.data, function (value, key) {
			    		if(flag){
				    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
				    			//create month header for year header
				    			tmDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
				    			flag = false;
				    			
				    		}
			    		}
			    	});
		    		
		    		}
		    	
		    };
		    
		    //calculate row total
		    $scope.calculateRowTotal = function(onsiteDeputed,index){
		    	$scope.anyChange=1000;
		    	//get object of array
		    	var object = tmDealStaffingService.getRowCalculationObject($scope,onsiteDeputed,index);
		    	
		    	//auto calculate row total
		    	tmDealStaffingService.calculateRowTotal(object);
		    };
		    
		    //calculate Sub Total
		    $scope.calculateSubTotal = function(objectName,objectSumTotalName,objectMonthCount){
		    	
		    	var sumTotalObject = tmDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
		    	
		    	var yearArray = tmDealStaffingService.getYearHeaderArray($scope,objectName);
		    	
		    	sumTotalObject = tmDealStaffingService.resetSumTotalObjectValue(sumTotalObject,objectMonthCount);
		    	
		    			
		    	//calculate sub total row total
		    	tmDealStaffingService.calculateSubTotal($scope,yearArray,objectMonthCount,sumTotalObject);
		    };
		    
		    
		    
		    //start repeated data
		    $scope.setZero = function(object,index,model,rowname)
		    {
		    	console.log("Value entered"+model);
		    	if(model=="")
		    		{
		    		angular.forEach(object, function(value1,key1) {
		    			if(key1==index)
		    				{
		    					if(rowname =="staffingFirstMonthCount")
		    						{
		    							object[key1].staffingFirstMonthCount=0;
		    						}
		    					if(rowname =="staffingSecondMonthCount")
								{
									object[key1].staffingSecondMonthCount=0;
								}
		    					if(rowname =="staffingThirdMonthCount")
								{
									object[key1].staffingThirdMonthCount=0;
								}
		    					if(rowname =="staffingFourthMonthCount")
								{
									object[key1].staffingFourthMonthCount=0;
								}
		    					if(rowname =="staffingFifthMonthCount")
								{
									object[key1].staffingFifthMonthCount=0;
								}
		    					
		    					if(rowname =="staffingSixthMonthCount")
								{
									object[key1].staffingSixthMonthCount=0;
								}
		    					
		    					if(rowname =="staffingSeventhMonthCount")
								{
									object[key1].staffingSeventhMonthCount=0;
								}
		    					
		    					if(rowname =="staffingEighthMonthCount")
								{
									object[key1].staffingEighthMonthCount=0;
								}
		    					
		    					if(rowname =="staffingNinthMonthCount")
								{
									object[key1].staffingNinthMonthCount=0;
								}
		    					
		    					if(rowname =="staffingTenthMonthCount")
								{
									object[key1].staffingTenthMonthCount=0;
								}
		    					
		    					if(rowname =="staffingEleventhMonthCount")
								{
									object[key1].staffingEleventhMonthCount=0;
								}
		    					
		    					if(rowname =="staffingTwelthMonthCount")
								{
									object[key1].staffingTwelthMonthCount=0;
								}
		    					
		    				}
		    			
		    		});
		    		}
		    }
		    
		    $scope.getOtherData = function(data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,object,index,rowname)
		    {
		    	
		    	if(rowname=='staffingFirstMonthCount')
		    		{
		    		if(object[index].staffingSecondMonthCount!=undefined)
					{
		    		if(object[index].staffingSecondMonthCount==object[index].staffingThirdMonthCount)
		    			{
		    				object[index].staffingSecondMonthCount=object[index].staffingFirstMonthCount;
		    				
		    				if(object[index].staffingThirdMonthCount==object[index].staffingFourthMonthCount)
		        			{	
		    					object[index].staffingThirdMonthCount=object[index].staffingFirstMonthCount;
		        				
		        				if(object[index].staffingFourthMonthCount==object[index].staffingFifthMonthCount)
		            			{
		            				object[index].staffingFourthMonthCount=object[index].staffingFirstMonthCount;
		            				if(object[index].staffingFifthMonthCount==object[index].staffingSixthMonthCount)
		            				{
		            					object[index].staffingFifthMonthCount=object[index].staffingFirstMonthCount;
		            	    			
		            					if(object[index].staffingSixthMonthCount==object[index].staffingSeventhMonthCount)
		            					{
		            						object[index].staffingSixthMonthCount=object[index].staffingFirstMonthCount;
		            						if(object[index].staffingSeventhMonthCount==object[index].staffingEighthMonthCount)
		            						{
		            			    			object[index].staffingSeventhMonthCount=object[index].staffingFirstMonthCount;
		            			    			if(object[index].staffingEighthMonthCount==object[index].staffingNinthMonthCount)
		                						{
		                			    			object[index].staffingEighthMonthCount=object[index].staffingFirstMonthCount;
		                			    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
		                    						{
		                    			    			object[index].staffingNinthMonthCount=object[index].staffingFirstMonthCount;
		                    			    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
		                        						{
		                        			    			object[index].staffingTenthMonthCount=object[index].staffingFirstMonthCount;
		                        			    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		                            						{
		                            			    			object[index].staffingEleventhMonthCount=object[index].staffingFirstMonthCount;
		                            			    			object[index].staffingTwelthMonthCount=object[index].staffingFirstMonthCount;
		                            						}
		                            			    		
		                        			    			else
		                            						{
		                            							object[index].staffingEleventhMonthCount=object[index].staffingFirstMonthCount;
		                            						}
		                            					}
		                            					
		                    			    			else
		                        						{
		                        							object[index].staffingTenthMonthCount=object[index].staffingFirstMonthCount;
		                        						}
		                        					}
		                        					
		                			    			else
		                    						{
		                    							object[index].staffingNinthMonthCount=object[index].staffingFirstMonthCount;
		                    						}
		                    					}
		                    					
		            			    			else
		                						{
		                							object[index].staffingEighthMonthCount=object[index].staffingFirstMonthCount;
		                						}
		                					}
		                					
		            						else
		            						{
		            							object[index].staffingSeventhMonthCount=object[index].staffingFirstMonthCount;
		            						}
		            					}
		            					
		            					else
		            					{
		            						object[index].staffingSixthMonthCount=object[index].staffingFirstMonthCount;
		            					}
		            				}
		            				
		            				else
		            				{
		            					object[index].staffingFifthMonthCount=object[index].staffingFirstMonthCount;
		            				}
		            			}
		            			
		        				else
		            			{
		            				object[index].staffingFourthMonthCount=object[index].staffingFirstMonthCount;
		            			}
		            		}
		            		
		    				else
		        			{
		        				object[index].staffingThirdMonthCount=object[index].staffingFirstMonthCount;
		        			}
		        		}
		        		
		    		else
					{
						object[index].staffingSecondMonthCount=object[index].staffingFirstMonthCount;
					}
		    	
					}
		    	}
		    	else if(rowname=='staffingSecondMonthCount')
		    		{
		    		if(object[index].staffingThirdMonthCount!=null)
					{
					 if(object[index].staffingThirdMonthCount==object[index].staffingFourthMonthCount)
		    			{	
							object[index].staffingThirdMonthCount=object[index].staffingSecondMonthCount;
		    				
		    				if(object[index].staffingFourthMonthCount==object[index].staffingFifthMonthCount)
		        			{
		        				object[index].staffingFourthMonthCount=object[index].staffingSecondMonthCount;
		        				if(object[index].staffingFifthMonthCount==object[index].staffingSixthMonthCount)
		        				{
		        					object[index].staffingFifthMonthCount=object[index].staffingSecondMonthCount;
		        	    			
		        					if(object[index].staffingSixthMonthCount==object[index].staffingSeventhMonthCount)
		        					{
		        						object[index].staffingSixthMonthCount=object[index].staffingSecondMonthCount;
		        						if(object[index].staffingSeventhMonthCount==object[index].staffingEighthMonthCount)
		        						{
		        			    			object[index].staffingSeventhMonthCount=object[index].staffingSecondMonthCount;
		        			    			if(object[index].staffingEighthMonthCount==object[index].staffingNinthMonthCount)
		            						{
		            			    			object[index].staffingEighthMonthCount=object[index].staffingSecondMonthCount;
		            			    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
		                						{
		                			    			object[index].staffingNinthMonthCount=object[index].staffingSecondMonthCount;
		                			    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
		                    						{
		                    			    			object[index].staffingTenthMonthCount=object[index].staffingSecondMonthCount;
		                    			    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		                        						{
		                        			    			object[index].staffingEleventhMonthCount=object[index].staffingSecondMonthCount;
		                        			    			object[index].staffingTwelthMonthCount=object[index].staffingSecondMonthCount;
		                        						}
		                        			    		
		                    			    			else
		                        						{
		                        							object[index].staffingEleventhMonthCount=object[index].staffingSecondMonthCount;
		                        						}
		                        					}
		                        					
		                			    			else
		                    						{
		                    							object[index].staffingTenthMonthCount=object[index].staffingSecondMonthCount;
		                    						}
		                    					}
		                    					
		            			    			else
		                						{
		                							object[index].staffingNinthMonthCount=object[index].staffingSecondMonthCount;
		                						}
		                					}
		                					
		        			    			else
		            						{
		            							object[index].staffingEighthMonthCount=object[index].staffingSecondMonthCount;
		            						}
		            					}
		            					
		        						else
		        						{
		        							object[index].staffingSeventhMonthCount=object[index].staffingSecondMonthCount;
		        						}
		        					}
		        					
		        					else
		        					{
		        						object[index].staffingSixthMonthCount=object[index].staffingSecondMonthCount;
		        					}
		        				}
		        				
		        				else
		        				{
		        					object[index].staffingFifthMonthCount=object[index].staffingSecondMonthCount;
		        				}
		        			}
		        			
		    				else
		        			{
		        				object[index].staffingFourthMonthCount=object[index].staffingSecondMonthCount;
		        			}
		        		}
		        		
						else
		    			{
		    				object[index].staffingThirdMonthCount=object[index].staffingSecondMonthCount;
		    			}
					}
		 
		    		}
		    	else if(rowname=='staffingThirdMonthCount')
				{
		    		if(object[index].staffingFourthMonthCount!=null)
					{
						if(object[index].staffingFourthMonthCount==object[index].staffingFifthMonthCount)
		    			{
		    				object[index].staffingFourthMonthCount=object[index].staffingThirdMonthCount;
		    				if(object[index].staffingFifthMonthCount==object[index].staffingSixthMonthCount)
		    				{
		    					object[index].staffingFifthMonthCount=object[index].staffingThirdMonthCount;
		    	    			
		    					if(object[index].staffingSixthMonthCount==object[index].staffingSeventhMonthCount)
		    					{
		    						object[index].staffingSixthMonthCount=object[index].staffingThirdMonthCount;
		    						if(object[index].staffingSeventhMonthCount==object[index].staffingEighthMonthCount)
		    						{
		    			    			object[index].staffingSeventhMonthCount=object[index].staffingThirdMonthCount;
		    			    			if(object[index].staffingEighthMonthCount==object[index].staffingNinthMonthCount)
		        						{
		        			    			object[index].staffingEighthMonthCount=object[index].staffingThirdMonthCount;
		        			    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
		            						{
		            			    			object[index].staffingNinthMonthCount=object[index].staffingThirdMonthCount;
		            			    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
		                						{
		                			    			object[index].staffingTenthMonthCount=object[index].staffingThirdMonthCount;
		                			    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		                    						{
		                    			    			object[index].staffingEleventhMonthCount=object[index].staffingThirdMonthCount;
		                    			    			object[index].staffingTwelthMonthCount=object[index].staffingThirdMonthCount;
		                    						}
		                    			    		
		                			    			else
		                    						{
		                    							object[index].staffingEleventhMonthCount=object[index].staffingThirdMonthCount;
		                    						}
		                    					}
		                    					
		            			    			else
		                						{
		                							object[index].staffingTenthMonthCount=object[index].staffingThirdMonthCount;
		                						}
		                					}
		                					
		        			    			else
		            						{
		            							object[index].staffingNinthMonthCount=object[index].staffingThirdMonthCount;
		            						}
		            					}
		            					
		    			    			else
		        						{
		        							object[index].staffingEighthMonthCount=object[index].staffingThirdMonthCount;
		        						}
		        					}
		        					
		    						else
		    						{
		    							object[index].staffingSeventhMonthCount=object[index].staffingThirdMonthCount;
		    						}
		    					}
		    					
		    					else
		    					{
		    						object[index].staffingSixthMonthCount=object[index].staffingThirdMonthCount;
		    					}
		    				}
		    				
		    				else
		    				{
		    					object[index].staffingFifthMonthCount=object[index].staffingThirdMonthCount;
		    				}
		    			}
		    			
						else
		    			{
		    				object[index].staffingFourthMonthCount=object[index].staffingThirdMonthCount;
		    			}
					}

				}
		    	else if(rowname=='staffingFourthMonthCount')
				{
		    		if(object[index].staffingFifthMonthCount!=null)
		    		{
					if(object[index].staffingFifthMonthCount==object[index].staffingSixthMonthCount)
							{
							object[index].staffingFifthMonthCount=object[index].staffingFourthMonthCount;
			    			
							if(object[index].staffingSixthMonthCount==object[index].staffingSeventhMonthCount)
							{
								object[index].staffingSixthMonthCount=object[index].staffingFourthMonthCount;
								if(object[index].staffingSeventhMonthCount==object[index].staffingEighthMonthCount)
								{
					    			object[index].staffingSeventhMonthCount=object[index].staffingFourthMonthCount;
					    			if(object[index].staffingEighthMonthCount==object[index].staffingNinthMonthCount)
		    						{
		    			    			object[index].staffingEighthMonthCount=object[index].staffingFourthMonthCount;
		    			    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
		        						{
		        			    			object[index].staffingNinthMonthCount=object[index].staffingFourthMonthCount;
		        			    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
		            						{
		            			    			object[index].staffingTenthMonthCount=object[index].staffingFourthMonthCount;
		            			    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		                						{
		                			    			object[index].staffingEleventhMonthCount=object[index].staffingFourthMonthCount;
		                			    			object[index].staffingTwelthMonthCount=object[index].staffingFourthMonthCount;
		                						}
		                			    		
		            			    			else
		                						{
		                							object[index].staffingEleventhMonthCount=object[index].staffingFourthMonthCount;
		                						}
		                					}
		                					
		        			    			else
		            						{
		            							object[index].staffingTenthMonthCount=object[index].staffingFourthMonthCount;
		            						}
		            					}
		            					
		    			    			else
		        						{
		        							object[index].staffingNinthMonthCount=object[index].staffingFourthMonthCount;
		        						}
		        					}
		        					
					    			else
		    						{
		    							object[index].staffingEighthMonthCount=object[index].staffingFourthMonthCount;
		    						}
		    					}
		    					
								else
								{
									object[index].staffingSeventhMonthCount=object[index].staffingFourthMonthCount;
								}
							}
							
							else
							{
								object[index].staffingSixthMonthCount=object[index].staffingFourthMonthCount;
							}
						}
						
						else
						{
							object[index].staffingFifthMonthCount=object[index].staffingFourthMonthCount;
						}
		    		}
				
				}
		    	else if(rowname=='staffingFifthMonthCount')
				{
		    		if(object[index].staffingSixthMonthCount!=null)
					{
		    			if(object[index].staffingSixthMonthCount==object[index].staffingSeventhMonthCount)
						{
							object[index].staffingSixthMonthCount=object[index].staffingFifthMonthCount;
							if(object[index].staffingSeventhMonthCount==object[index].staffingEighthMonthCount)
							{
				    			object[index].staffingSeventhMonthCount=object[index].staffingFifthMonthCount;
				    			if(object[index].staffingEighthMonthCount==object[index].staffingNinthMonthCount)
								{
					    			object[index].staffingEighthMonthCount=object[index].staffingFifthMonthCount;
					    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
		    						{
		    			    			object[index].staffingNinthMonthCount=object[index].staffingFifthMonthCount;
		    			    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
		        						{
		        			    			object[index].staffingTenthMonthCount=object[index].staffingFifthMonthCount;
		        			    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		            						{
		            			    			object[index].staffingEleventhMonthCount=object[index].staffingFifthMonthCount;
		            			    			object[index].staffingTwelthMonthCount=object[index].staffingFifthMonthCount;
		            						}
		            			    		
		        			    			else
		            						{
		            							object[index].staffingEleventhMonthCount=object[index].staffingFifthMonthCount;
		            						}
		            					}
		            					
		    			    			else
		        						{
		        							object[index].staffingTenthMonthCount=object[index].staffingFifthMonthCount;
		        						}
		        					}
		        					
					    			else
		    						{
		    							object[index].staffingNinthMonthCount=object[index].staffingFifthMonthCount;
		    						}
		    					}
		    					
				    			else
								{
									object[index].staffingEighthMonthCount=object[index].staffingFifthMonthCount;
								}
							}
							
							else
							{
								object[index].staffingSeventhMonthCount=object[index].staffingFifthMonthCount;
							}
						}
						
						else
						{
							object[index].staffingSixthMonthCount=object[index].staffingFifthMonthCount;
						}
					}
				}
		    	
		    	else if(rowname=='staffingSixthMonthCount')
		    		{
		    		if(object[index].staffingSeventhMonthCount!=null)
					{	
		    			if(object[index].staffingSeventhMonthCount==object[index].staffingEighthMonthCount)
							{
				    			object[index].staffingSeventhMonthCount=object[index].staffingSixthMonthCount;
				    			if(object[index].staffingEighthMonthCount==object[index].staffingNinthMonthCount)
								{
					    			object[index].staffingEighthMonthCount=object[index].staffingSixthMonthCount;
					    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
		    						{
		    			    			object[index].staffingNinthMonthCount=object[index].staffingSixthMonthCount;
		    			    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
		        						{
		        			    			object[index].staffingTenthMonthCount=object[index].staffingSixthMonthCount;
		        			    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		            						{
		            			    			object[index].staffingEleventhMonthCount=object[index].staffingSixthMonthCount;
		            			    			object[index].staffingTwelthMonthCount=object[index].staffingSixthMonthCount;
		            						}
		            			    		
		        			    			else
		            						{
		            							object[index].staffingEleventhMonthCount=object[index].staffingSixthMonthCount;
		            						}
		            					}
		            					
		    			    			else
		        						{
		        							object[index].staffingTenthMonthCount=object[index].staffingSixthMonthCount;
		        						}
		        					}
		        					
					    			else
		    						{
		    							object[index].staffingNinthMonthCount=object[index].staffingSixthMonthCount;
		    						}
		    					}
		    					
				    			else
								{
									object[index].staffingEighthMonthCount=object[index].staffingSixthMonthCount;
								}
							}
							
							else
							{
								object[index].staffingSeventhMonthCount=object[index].staffingSixthMonthCount;
							}
						}
		    		}
		    	else if(rowname=='staffingSeventhMonthCount')
		    		{
		    		
					
		    		if(object[index].staffingEighthMonthCount!=null)
					{
		    			if(object[index].staffingEighthMonthCount==object[index].staffingNinthMonthCount)
					
							{
				    			object[index].staffingEighthMonthCount=object[index].staffingSeventhMonthCount;
				    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
								{
					    			object[index].staffingNinthMonthCount=object[index].staffingSeventhMonthCount;
					    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
		    						{
		    			    			object[index].staffingTenthMonthCount=object[index].staffingSeventhMonthCount;
		    			    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		        						{
		        			    			object[index].staffingEleventhMonthCount=object[index].staffingSeventhMonthCount;
		        			    			object[index].staffingTwelthMonthCount=object[index].staffingSeventhMonthCount;
		        						}
		        			    		
		    			    			else
		        						{
		        							object[index].staffingEleventhMonthCount=object[index].staffingSeventhMonthCount;
		        						}
		        					}
		        					
					    			else
		    						{
		    							object[index].staffingTenthMonthCount=object[index].staffingSeventhMonthCount;
		    						}
		    					}
		    					
				    			else
								{
									object[index].staffingNinthMonthCount=object[index].staffingSeventhMonthCount;
								}
							}
							
			    			else
							{
								object[index].staffingEighthMonthCount=object[index].staffingSeventhMonthCount;
							}
						}
		    		}
		    	else if(rowname=='staffingEighthMonthCount')
				{
		    		
		    		if(object[index].staffingNinthMonthCount!=null)
					{
		    			if(object[index].staffingNinthMonthCount==object[index].staffingTenthMonthCount)
							{
				    			object[index].staffingNinthMonthCount=object[index].staffingEighthMonthCount;
				    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
								{
					    			object[index].staffingTenthMonthCount=object[index].staffingEighthMonthCount;
					    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
		    						{
		    			    			object[index].staffingEleventhMonthCount=object[index].staffingEighthMonthCount;
		    			    			object[index].staffingTwelthMonthCount=object[index].staffingEighthMonthCount;
		    						}
		    			    		
					    			else
		    						{
		    							object[index].staffingEleventhMonthCount=object[index].staffingEighthMonthCount;
		    						}
		    					}
		    					
				    			else
								{
									object[index].staffingTenthMonthCount=object[index].staffingEighthMonthCount;
								}
							}
							
			    			else
							{
								object[index].staffingNinthMonthCount=object[index].staffingEighthMonthCount;
							}
					}
				
				}
		    	else if(rowname=='staffingNinthMonthCount')
				{
		    		
		    		if(object[index].staffingTenthMonthCount!=null)
					{
		    			if(object[index].staffingTenthMonthCount==object[index].staffingEleventhMonthCount)
					
							{
				    			object[index].staffingTenthMonthCount=object[index].staffingNinthMonthCount;
				    			if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
								{
					    			object[index].staffingEleventhMonthCount=object[index].staffingNinthMonthCount;
					    			object[index].staffingTwelthMonthCount=object[index].staffingNinthMonthCount;
								}
					    		
				    			else
								{
									object[index].staffingEleventhMonthCount=object[index].staffingNinthMonthCount;
								}
							}
							
			    			else
							{
								object[index].staffingTenthMonthCount=object[index].staffingNinthMonthCount;
							}
					}
			
				}
		    	else if(rowname=='staffingTenthMonthCount' )
				{
		    	
					if(object[index].staffingEleventhMonthCount !=null)
						{
						if(object[index].staffingEleventhMonthCount==object[index].staffingTwelthMonthCount)
						{
							object[index].staffingEleventhMonthCount=object[index].staffingTenthMonthCount;
							object[index].staffingTwelthMonthCount=object[index].staffingTenthMonthCount;
						}
						
						else
						{
							object[index].staffingEleventhMonthCount=object[index].staffingTenthMonthCount;
						}
					}
					
			
				
				}
		    	else if(rowname=='staffingEleventhMonthCount')
				{
		    		
		    		if(object[index].staffingTwelthMonthCount !=null)
					{
		    			object[index].staffingTwelthMonthCount=object[index].staffingEleventhMonthCount;
					}
					
					
					console.log(object[index].staffingTwelthMonthCount);
				}
		    	else if(rowname=='staffingTwelthMonthCount' )
				{
		    		if(object[index].staffingTwelthMonthCount !=null)
					{
		    			object[index].staffingTwelthMonthCount=object[index].staffingTwelthMonthCount;
					}
		    		
				}
		    	
		    	
		    	
		    	//Calculations on value change
		    	if(data1=="onsiteContractor")
				{
		    	if(rowname=='staffingFirstMonthCount')
		    	{
		    		$scope.calculateContractorTotalOnsite(data11,'staffingSecondMonthCount',data12);
		    		$scope.calculateContractorTotalStaffing(data11,'staffingSecondMonthCount',data12);
		    	}	
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingThirdMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingThirdMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
		    	{
		    		$scope.calculateContractorTotalOnsite(data11,'staffingFourthMonthCount',data12);
		    		$scope.calculateContractorTotalStaffing(data11,'staffingFourthMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingFifthMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingFifthMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingSixthMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingSixthMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingSeventhMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingSeventhMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		| rowname=='staffingSeventhMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingEighthMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingEighthMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingNinthMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingNinthMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingTenthMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingTenthMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
		    		|| rowname=='staffingTenthMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingEleventhMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingEleventhMonthCount',data12);
		    	}
		    	if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
		    		|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
		    	{
					$scope.calculateContractorTotalOnsite(data11,'staffingTwelthMonthCount',data12);
					$scope.calculateContractorTotalStaffing(data11,'staffingTwelthMonthCount',data12);
		    	}
			
			}
			if(data1=="onsiteContractor" || data1=="offshoreTotalContr")
			{
				
				if(rowname=='staffingFirstMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingSecondMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingThirdMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingFourthMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingFifthMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingSixthMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingSeventhMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		| rowname=='staffingSeventhMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingEighthMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingNinthMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingTenthMonthCount',data8);
		    	}
				
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
		    		|| rowname=='staffingTenthMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingEleventhMonthCount',data8);
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
		    		|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
		    	{
					$scope.calculateTotalStaffingContr(data7,index,'staffingTwelthMonthCount',data8);
		    	}
			}
			
			
			if(data1=="onsiteContractor" || data1=="onsiteLocal" || data1=="onsiteDeputed" || data1=="onsiteShortTerm")
				{
					if(rowname=='staffingFirstMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingSecondMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingThirdMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingFourthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingFifthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingSixthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingSeventhMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		| rowname=='staffingSeventhMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingEighthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingNinthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingTenthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
			    		|| rowname=='staffingTenthMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingEleventhMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
			    		|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
			    	{
						$scope.getOnsiteGross(data7,data8,index,'staffingTwelthMonthCount');
			    	}
				}
			
			
			
			if(data1=="onsiteContractor" || data1=="onsiteLocal" || data1=="offshoreTotal" || data1=="offshoreTotalContr")
				{
					if(rowname=='staffingFirstMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingSecondMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingThirdMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingFourthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingFifthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingSixthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingSeventhMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		| rowname=='staffingSeventhMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingEighthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingNinthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingTenthMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
			    		|| rowname=='staffingTenthMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingEleventhMonthCount');
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
			    		|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
			    	{
						$scope.getLocal_Contr(data2,data3,index,'staffingTwelthMonthCount');
			    	}
					
				}
			
			if(data1=="onsiteLocal" || data1=="onsiteDeputed" || data1=="offshoreTotal" || data1=="onsiteShortTerm")
				{
					if(rowname=='staffingFirstMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingSecondMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingThirdMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingFourthMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingFifthMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingSixthMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingSeventhMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		| rowname=='staffingSeventhMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingEighthMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingNinthMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingTenthMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
			    		|| rowname=='staffingTenthMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingEleventhMonthCount',data10);
			    	}
					if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
			    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
			    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
			    		|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
			    	{
						$scope.calculateTotalStaffing(data9,index,'staffingTwelthMonthCount',data10);
			    	}
					
				}
			
			
			if(data1=="onsiteLocal" || data1=="onsiteDeputed" || data1=="onsiteShortTerm")
			{
				if(rowname=='staffingFirstMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingSecondMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingThirdMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingFourthMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingFifthMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingSixthMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingSeventhMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		| rowname=='staffingSeventhMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingEighthMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingNinthMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingTenthMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
		    		|| rowname=='staffingTenthMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingEleventhMonthCount');
		    	}
				if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
		    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
		    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
		    		|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
		    	{
					$scope.getTotalOnsite(data5,data6,index,'staffingTwelthMonthCount');
		    	}
			}

			

			if(rowname=='staffingFirstMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingSecondMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingSecondMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingThirdMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingThirdMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingFourthMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingFourthMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingFifthMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingFifthMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingSixthMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingSixthMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingSeventhMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingSeventhMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
				| rowname=='staffingSeventhMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingEighthMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingEighthMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
				|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingNinthMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingNinthMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
				|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingTenthMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingTenthMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
				|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
				|| rowname=='staffingTenthMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingEleventhMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingEleventhMonthCount');
			}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
				|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
				|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
				|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
			{
				$scope.calculateSubTotal(data1,data4,'staffingTwelthMonthCount');
				$scope.getStaffingGross(data11,data12,index,'staffingTwelthMonthCount');
			}
			
			//Will run every instance of on change
			$scope.calculateRowTotal(data1,index);	
		     	
		    };
		    
		    //end repeated data
		    
		    
		    
		    //calculate Sub Total arvind for onsite local & onsite contractor
		/*    $scope.calculateSubTotalLoc_Contr = function(objectName,objectSumTotalName,objectMonthCount){    	
		    	
		    	var sumTotalObject = fpDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
		    	
		    	var yearArray = fpDealStaffingService.getYearHeaderArray($scope,objectName);
		    	
		    	sumTotalObject = fpDealStaffingService.resetSumTotalObjectValue(sumTotalObject,objectMonthCount);
		    	    			
		    	//calculate sub total row total
		    	fpDealStaffingService.calculateSubTotal($scope,yearArray,objectMonthCount,sumTotalObject);
		    };*/
		    
		  //end calculate Sub Total arvind for onsitelocal & onsitecontractor  
		    
		    //total onsite
		    $scope.getTotalOnsite = function(objectName,objectSumTotalName,index,rowName){
		    	 /*var countTableRow1	=  $('#countTableRow1').children('tr').length;
		    	 var countTableRow2	=  $('#countTableRow2').children('tr').length;    
		    	alert('countTable1Row='+countTableRow1+'  countTableRow2= '+countTableRow2);*/
		    	var totalOnsiteObject = tmDealStaffingService.getRowCalculationObject($scope,objectName,index);
		    	totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])
		    	//+ parseFloat($scope.onsiteContractor[index][rowName])
				+ parseFloat($scope.onsiteDeputed[index][rowName])
				+ parseFloat($scope.onsiteShortTerm[index][rowName]);
		    	
		    	//alert('parseFloat($scope.onsiteDeputed[index][rowName])='+parseFloat($scope.onsiteDeputed[index][rowName])+' parseFloat($scope.onsiteShortTerm[index][rowName])='+parseFloat($scope.onsiteShortTerm[index][rowName]))
		    	/*if(!parseFloat($scope.onsiteDeputed[index][rowName]) || !parseFloat($scope.onsiteShortTerm[index][rowName]))
		    		{
			    		totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])
			    		+ parseFloat($scope.onsiteDeputed[index][rowName])
			    		+ parseFloat($scope.onsiteShortTerm[index][rowName]);
		    		}
		    	else
		    		{*/
		    		//	totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])    		
			    	//	+ parseFloat($scope.onsiteDeputed[index][rowName]);
			    		
		    		//}
		  
		    	
		    	/*angular.forEach($scope.data, function (value, key) {
		    		console.log("Visa Id ....."+value.visaId);
		    		if(value.visaId == 2 && value.sumTotalType == 0 && value.isContractor == 0 )
		    		{
		    			index = index+12;
		    			totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName]) 
		    			+ parseFloat($scope.onsiteDeputed[index][rowName])
		    			+ parseFloat($scope.onsiteShortTerm[index][rowName]);    			
		    			index=0;
		    		}
		    		else
		    		{
		    			totalOnsiteObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName]) 
		    			+ parseFloat($scope.onsiteDeputed[index][rowName])
		    			+ parseFloat($scope.onsiteShortTerm[index][rowName]);
		    		}
		    	});
		    	console.log("Inside the scope");
		    	console.log($scope.data[0].visaId); */    	
		    	
		    	//role wise total    	
		    	
		    	//role year wise total
		    	tmDealStaffingService.calculateRowTotal(totalOnsiteObject);   //row total
		    	
		    	
		    	//year wise total
		    	var sumTotalObject = tmDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
		    	
		    	
		    	var yearArray = tmDealStaffingService.getYearHeaderArray($scope,objectName);
		    	
		    	sumTotalObject = tmDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross 
		    	
		    	//calculate sub total row total
		    	tmDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
		    	 
		    
		    	
		    };
		    //arvind
		    $scope.getLocal_Contr = function(objectName,objectSumTotalName,index,rowName)
		    {
		    	var sumTotalObject = tmDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
		    	var yearArray = tmDealStaffingService.getYearHeaderArray($scope,objectName);
		    	sumTotalObject = tmDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross
		    	
		    	tmDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
		    };
		    $scope.getOnsiteGross = function(objectName,objectSumTotalName,index,rowName)
		    {
		    	var sumTotalObject = tmDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
		    	var yearArray = tmDealStaffingService.getYearHeaderArray($scope,objectName);
		    	sumTotalObject = tmDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross    	
		    	tmDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
		    };
		    
		   
		    $scope.getStaffingGross = function(objectName,objectSumTotalName,index,rowName)
		    {
		    	 var sumTotalObject =0;
		    	
		   	//offshore_ContrSumTotal onsiteSumTotal
		    	/*console.log("Total Staffing");
		    	console.log($scope.onsiteSumTotal[0].staffingFirstMonthCount);
		    	console.log("Total Staffing contractor");
		    	console.log($scope.offshore_ContrSumTotal[0].staffingFirstMonthCount);
		    	
		    	angular.forEach($scope.onsiteSumTotal, function (value, key) {
		    		
		    		angular.forEach($scope.offshore_ContrSumTotal, function(value2,key2) {
		    				
		    			if($scope.onsiteSumTotal[key].staffingFirstMonthCount != 0 && $scope.offshore_ContrSumTotal[key2].staffingFirstMonthCount  != 0) {
		    				sumTotalObject += parseFloat($scope.onsiteSumTotal[key].staffingFirstMonthCount) + parseFloat ($scope.offshore_ContrSumTotal[key2].staffingFirstMonthCount);
		    				console.log("sumTotalObject=="+sumTotalObject);
		    				console.log($scope.totalSumTotalStaffing); 
		    				$scope.totalSumTotalStaffing[index].staffingFirstMonthCount  = sumTotalObject;
		    			} 
		    			console.log("The Sum Total Object is..");
						console.log(sumTotalObject);
		    		});
		    	});*/
		    	
		    	sumTotalObject = tmDealStaffingService.getSumTotalObject($scope,objectSumTotalName);
		    	var yearArray = tmDealStaffingService.getYearHeaderArray($scope,objectName);
		    	sumTotalObject = tmDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);  //reset gross
		    	
		    	tmDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
		    };
		    //end
		    //calculate total onsite for contractor
		    $scope.calculateContractorTotalOnsite = function(masterRoleId,rowName,rowValue){
		    	var totalOnsiteObject = {};
		    	angular.forEach($scope.onsiteTotal, function (value, key) {
		    		if(value.masterRoleId == masterRoleId ){
		    			totalOnsiteObject = value
		    		}
		    	});
		    	totalOnsiteObject[rowName] = parseFloat(rowValue);
		    	
		    	tmDealStaffingService.calculateRowTotal(totalOnsiteObject);
		    };
		    
		    //calculate total staffing for onsite and offshore
		    $scope.calculateTotalStaffing = function(objectName,index,rowName,sumTotalArray){
		    	console.log("calculateTotalStaffing ----");
		    	console.log(objectName);
		    	console.log(index);
		    	console.log(rowName);
		    	console.log(sumTotalArray);
//		    	console.log($scope);
		    	var totalStaffingObject = tmDealStaffingService.getRowCalculationObject($scope,objectName,index);
//		    	console.log(totalStaffingObject);
//		    	
		    	if(!$scope.offshoreTotal[index])
		    		{
		    		//role wise total
				    	totalStaffingObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])
				    	+ parseFloat($scope.onsiteDeputed[index][rowName])
				    	+ parseFloat($scope.onsiteShortTerm[index][rowName]); 
		    		}
		    	else if(!$scope.onsiteLocal[index])
		    		{
		    		totalStaffingObject[rowName] = parseFloat($scope.offshoreTotal[index][rowName]);	
		    			
		    		}
		    	else
		    		{
		    		//role wise total
				    	totalStaffingObject[rowName] = parseFloat($scope.onsiteLocal[index][rowName])
				    	+ parseFloat($scope.onsiteDeputed[index][rowName])
				    	+ parseFloat($scope.onsiteShortTerm[index][rowName]) 
				    	+ parseFloat($scope.offshoreTotal[index][rowName]);		    		
		    		}
		    	
//		    	
		    	
		    	//row total calculation
		    	tmDealStaffingService.calculateRowTotal(totalStaffingObject);
		    	
		    	//calculate sum total for total staffing
		    	
		    	var sumTotalObject = tmDealStaffingService.getSumTotalObject($scope,sumTotalArray);
		    	
		    	var yearArray = tmDealStaffingService.getYearHeaderArray($scope,objectName);
		    	
		    	sumTotalObject = tmDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);
		    	
		    	//calculate sub total row total
		    	tmDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
		    	
		    };
		    
		    //arvind
		    $scope.calculateTotalStaffingContr = function(objectName,index,rowName,sumTotalArray){
		    	console.log("calculateTotalStaffing ----");
		    	console.log(objectName);
		    	console.log(index);
		    	console.log(rowName);
		    	console.log(sumTotalArray);
//		    	console.log($scope);
		    	var totalStaffingObject = tmDealStaffingService.getRowCalculationObject($scope,objectName,index);
//		    	console.log(totalStaffingObject);
//		    	
//		    	//role wise total
		    	totalStaffingObject[rowName] = parseFloat($scope.onsiteContractor[index][rowName])
		    	+ parseFloat($scope.offshoreTotalContr[index][rowName]);    	
		    	
		    	//row total calculation
		    	tmDealStaffingService.calculateRowTotal(totalStaffingObject);
		    	
		    	//calculate sum total for total staffing
		    	
		    	var sumTotalObject = tmDealStaffingService.getSumTotalObject($scope,sumTotalArray);
		    	
		    	var yearArray = tmDealStaffingService.getYearHeaderArray($scope,objectName);
		    	
		    	sumTotalObject = tmDealStaffingService.resetSumTotalObjectValue(sumTotalObject,rowName);
		    	
		    	//calculate sub total row total
		    	tmDealStaffingService.calculateSubTotal($scope,yearArray,rowName,sumTotalObject);
		    	
		    };
		    //end arvind
		    
		  //calculate total staffing for contractor
		    $scope.calculateContractorTotalStaffing = function(masterRoleId,rowName,rowValue){
		    	var totalStaffingObject = {};
		    	angular.forEach($scope.totalStaffing, function (value, key) {
		    		if(value.masterRoleId == masterRoleId ){
		    			totalStaffingObject = value
		    		}
		    	});
		    	totalStaffingObject[rowName] = parseFloat(rowValue);
		    	
		    	tmDealStaffingService.calculateRowTotal(totalStaffingObject);
		    };
		    
		    
		    $scope.autoSummaryData= function()
		    {
		    	
		    	//display summary data after search operation
	    		var flag=true;
	    		angular.forEach($scope.data, function (value, key) {
		    		if(flag){
			    		if(value.monthYearHeader == 'Jan-18/Jan-19'){
			    			//create month header for year header
			    			tmDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
			    			flag = false;
			    			
			    		}
		    		}
		    	});		    		
	    		//
		    };
		    
		    $scope.totalArray = [];
		    $scope.marker=[];
		    //Save Data
		    $scope.save = function(){
		    	$scope.totalArray.push($scope.onsiteLocal);
		    	$scope.totalArray.push($scope.onsiteContractor);
		    	$scope.totalArray.push($scope.onsiteLocal_ContrSumTotal);
		    	//$scope.totalArray.push($scope.onsiteLocalSumTotal);
		    	$scope.totalArray.push($scope.onsiteDeputed);
		    	$scope.totalArray.push($scope.onsiteDeputedSumTotal);
		    	
		    	$scope.totalArray.push($scope.onsiteShortTerm);
		    	$scope.totalArray.push($scope.onsiteShortTermSumTotal);
		    	
		    	$scope.totalArray.push($scope.onsiteTotal);
		    	$scope.totalArray.push($scope.onsiteSumTotal);
		    	
		    	$scope.totalArray.push($scope.offshoreTotalContr)
		    	$scope.totalArray.push($scope.offshoreTotal);
		    	$scope.totalArray.push($scope.totalStaffing);
		    	
		    	$scope.totalArray.push($scope.totalStaffingContr);
		    	
		    	$scope.totalArray.push($scope.offshore_ContrSumTotal);
		    	$scope.totalArray.push($scope.totalSumTotalStaffing);
		    
		    	
		    	//$scope.totalArray.push($scope.onsiteGrossSumTotal);
		    	//$scope.totalArray.push($scope.staffingGrossSumTotal);  
		    
		    	//$scope.totalArray.push($scope.onsiteSumTotalContractor);  	
		    	
		    	//$scope.totalArray.push($scope.offshoreSumTotal);
		    	//$scope.totalArray.push($scope.offshoreSumTotalContr);
		    	
		    	
		    	
		    	
		    	
		    	
		    	console.log("========Save Data========");
		    	console.log($scope.totalArray);
		    	
		    	angular.forEach($scope.totalArray, function (value, key) {
		    		
		    		angular.forEach($scope.totalArray[key], function (value1, key1) {
		    			if(value1.monthYearHeader=='Summary')
		    				{
		    				console.log("========if Summary========");
		    				}
		    			else
		    				{
		    					$scope.marker.push(value1);
		    				}
		    			
		        	});
		    	});
		    	
		    	//deleting extra flag objects
				angular.forEach($scope.marker, function (value, key) {
		    		delete $scope.marker[key].masterRoles;
		    		delete $scope.marker[key].staffingDetailsHeader;
		    	});
				
				
				var updateTMDealStaffingDetails = function(response) {
					
		    		console.log(response);
		    		if(response.status == 200) 
		    		{
		    			$scope.updateStaffingContractorRole(); //call another method for aad staffing contractor role
		    		} 
		    		else {
		    			BootstrapDialog.show({
		                    title : 'T&M Deal Staffing',
		                    type : BootstrapDialog.TYPE_DANGER,
		                    message : "Currently facing techinical issue.",
		                    closable : false,
		                    buttons : [{
		                           label : 'OK',
		                           action : function(dialogRef) {
		                                 dialogRef.close();
		                                /* $window.location.reload();*/
		                                 
		                           }
		                    }]
		              });
		    		}
		    		
		    	};
		    	
		    	
				WebServiceFactory.updateTMDealStaffingDetails($scope.marker).then(updateTMDealStaffingDetails);
		    	console.log($scope.marker);
		    };
		    
		    //
		    //$scope.marker2=[];
		    $scope.updateStaffingContractorRole = function(){
		    	
		    	 $scope.marker={		
		    			 			"stfContrRoleList":$scope.staffingContactorRoleList
		    	 			    };
				var updateStaffingContractorRole = function(response) {
					
		    	console.log($scope.marker);
				console.log(angular.toJson($scope.marker));
				if($scope.currentMonthYearHeader=='Summary')
				{					
			         $window.location.reload();
			         vartemp=1;
			         //tmDealStaffingService.createYearHeader($scope,$sessionStorage.staffing_header);
			         //temp=0;
			    	//$scope.getPrevSummaryData(); //9669,1
					//$scope.getStaffingcontractorRoleList();
			    	//$scope.getFpDealStaffingData($sessionStorage.frmModel);
			    
			    	//$scope.setYearHeader(Summary);
			    	//tmDealStaffingService.createMonthHeader($scope,value.staffingDetailsHeader);
			        
			        
			               
				}
				else
					{
						BootstrapDialog.show({
				            title : 'T&M Deal Staffing',
				            type : BootstrapDialog.TYPE_PRIMARY,
				            message : "T&M Deal Staffing Updated Successfully.",
				            closable : false,
				            buttons : [{
				                   label : 'OK',
				                   action : function(dialogRef) {
				                         dialogRef.close();
				                        $window.location.reload();
				                         
				                   }
				            }]
				      });										
					}				
				};
				
				WebServiceFactory.updateStaffingContractorRole($scope.marker).then(updateStaffingContractorRole);
				
		    };
			    
		    
		    $scope.clearData = function(){
		    	
	    	 	$scope.yearHeader=[];    	    
	    	    //Month header list
	    	    $scope.monthHeader=[];
	    	    
	    	    $scope.onsiteLocal = [];
	    	    $scope.onsiteLocalSumTotal = [];
	    	    $scope.onsiteLocal_Contractor = []
	    	    $scope.offshore_Contractor = []
	    	    $scope.onsiteGross = []
	    	    $scope.staffingGross = []
	    	    $scope.onsiteLocal_ContrSumTotal = [];
	    	    $scope.offshore_ContrSumTotal = []
	    	    $scope.onsiteGrossSumTotal = [];
	    	    $scope.staffingGrossSumTotal = [];
	    	    //onsite deputed staffing
	    	    $scope.onsiteDeputed = [];
	    	    $scope.onsiteDeputedSumTotal = [];

	    	    //onsite short term staffing
	    	    $scope.onsiteShortTerm= [];
	    	    $scope.onsiteShortTermSumTotal = [];
	    	    
	    	    //onsite sub total
	    	    $scope.onsiteTotal = [];
	    	    $scope.onsiteSumTotal = [];
	    	    
	    	    //onsite contractor
	    	    $scope.onsiteContractor=[];
	    	    $scope.onsiteSumTotalContractor=[];
	    	    
	    	    //offshore sub total
	    	    $scope.offshoreTotal = [];
	    	    $scope.offshoreSumTotal = [];
	    	    $scope.offshoreSumTotalContr = [];
	    	    $scope.offshoreTotalContr = [];
	    	    //total staffing
	    	    $scope.totalStaffing = [];
	    	    $scope.totalStaffingContr = [];
	    	    $scope.totalSumTotalStaffing = [];
	    	    $scope.colors = [];
	    	
	    }
		    
		$scope.clear=function()
		{
			
			angular.forEach($scope.data, function (value, key) {
				
				if(typeof $scope.data[key].staffingFirstMonthCount != 'number' ){}					
				else
				{
					$scope.data[key].staffingFirstMonthCount=0;
				}
				
				if(typeof $scope.data[key].staffingSecondMonthCount != 'number' ){}					
				else
				{
					$scope.data[key].staffingSecondMonthCount=0;
				}
				
				if(typeof $scope.data[key].staffingThirdMonthCount != 'number' ){}					
				else
				{
					$scope.data[key].staffingThirdMonthCount=0;
				}
				
				if(typeof $scope.data[key].staffingFourthMonthCount != 'number' ){}					
				else
				{
					$scope.data[key].staffingFourthMonthCount=0;
				}
				if(typeof $scope.data[key].staffingFifthMonthCount != 'number' ){}					
				else
				{
					$scope.data[key].staffingFifthMonthCount=0;
				}
				
				if(typeof $scope.data[key].staffingSixthMonthCount != 'number' ){}					
				else
				{
					$scope.data[key].staffingSixthMonthCount=0;
				}
				
				if(typeof $scope.data[key].staffingSeventhMonthCount != 'number' ){}					
				else
				{
					$scope.data[key].staffingSeventhMonthCount=0;
				}
								
				if(typeof $scope.data[key].staffingEighthMonthCount != 'number' ){}				
				else
				{ 
					$scope.data[key].staffingEighthMonthCount=0; 
				}
				
				if(typeof $scope.data[key].staffingNinthMonthCount != 'number' ){}				
				else
				{ 
					$scope.data[key].staffingNinthMonthCount=0; 
				}
				
				if(typeof $scope.data[key].staffingTenthMonthCount != 'number' ){}				
				else
				{ 
					$scope.data[key].staffingTenthMonthCount=0; 
				}
				
				if(typeof $scope.data[key].staffingEleventhMonthCount != 'number' ){}				
				else
				{
					$scope.data[key].staffingEleventhMonthCount=0;
				}
				
				if(typeof $scope.data[key].staffingTwelthMonthCount != 'number' ){}				
				else
				{
					$scope.data[key].staffingTwelthMonthCount=0;
				}
				$scope.data[key].yearlyTotal = 0;	    		
	    		
			}); 
		};
		    
		
		
		var getStaffingDone = function(response)
		{
			$scope.tempArr = response.data;
			$scope.diffEqualZero=$scope.tempArr[0].tempId;
			$sessionStorage.diffEqualZero = $scope.diffEqualZero;
			
			
		};
		WebServiceFactory.getStaffingDone(rpVrsId).then(getStaffingDone);
		
		
		    var getDealDetails = function(response) {
				console.log("Version Details New");
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		$localStorage.currentApprovalStatus=$scope.dealDetails[0].currentApprovalStatus;	
		  		var statusId = $scope.dealDetails[0].currentApprovalStatus;			          
		          if((statusId == null || statusId == 1 || statusId == 4) && userType == 'Delivery')
					{			
		        	  	$scope.isVersionAvailable = false;
					}
					else
					{
						  $scope.isVersionAvailable = true;
					}
		  		/*console.log("Approver Data")
		  		console.log($scope.dealDetails);
		  		console.log($scope.dealDetails[0].dealId);
		  		console.log($scope.dealDetails[0].customerId);
		  		console.log($scope.dealDetails[0].dealStartDate);
		  		console.log($scope.dealDetails[0].dealEndDate);
		  		console.log($scope.dealDetails[0].dealDescription);
		  		console.log($scope.dealDetails[0].dealStatus)
		  		console.log($scope.dealDetails[0].fpType==1? "Development" : "Maintenance");
		  		console.log(($scope.dealDetails[0].dealTypeId==1)? "Fixed Price" :"T & M");*/
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType==1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId==1)? "Fixed Price" :"T & M";
		  		
		  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
		  		var date = new Date(dealEndDate.substring(0,10));
		  		console.log("End Date is......... " + date);
		  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.dealDetails[0].dealEndDate = dateENd;
		  		
				var dealStartDate = $scope.dealDetails[0].dealStartDate;
				var date = new Date(dealStartDate.substring(0,10));
				var dateStart = $filter('date')(date,'dd/MM/yyyy');
				$scope.dealDetails[0].dealStartDate = dateStart;
				//alert('hi 66');
				$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	//arvind


				var countryCityDetail = function (res){
					$scope.cityList = [];
					$sessionStorage.citiId2staffing = res.data[0].city.cityId;
					$scope.cityList.push({
						"cityId": res.data[0].city.cityId,
					    "cityName":  res.data[0].city.cityName,
					    "countryId":  res.data[0].countryDetails.countryId,
					    "countryName":res.data[0].countryDetails.countryName, 
					    "utilization": res.data[0].utilization
					});
					console.log("----------FFFFFFFFFFF")
					console.log($scope.cityList)
					 
					
				}
				WebServiceFactory.getCountryCityBasedOnDealVersion($localStorage.rpDealVersionId).then(countryCityDetail)
				
				
				
				
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
			WebServiceFactory.getDealDetails($localStorage.DealModel,rpVrsId).then(getDealDetails);
		    
			$scope.exportToExcel = function()
			{
				//alert($sessionStorage.rpDealVersionId+"/"+$sessionStorage.staffing_header.cityId+"/"+ $scope.towerIdBySeesion+"/"+$scope.dealDetails[0].dealId+"/"+$localStorage.DealModel);
				window.location= contextPath+"/RightPrice-DAS/downloadTMDealCreationStaffingExcel/"+$localStorage.rpDealVersionId+"/"+$sessionStorage.staffing_header.cityId+"/"+$scope.towerIdBySeesion+"/"+$scope.dealDetails[0].dealId+"/"+$localStorage.DealModel;
		    		
			};
		    
			
			
			// upload functionality in Staffing Page
			$scope.updateStaffingAttachment=function(RateCard){
				//alert("uploadFileToUrl");
				//console.log("called uploadPOExcel");
				var fileTest = $("#fuUploadFilename");	
				//console.log(fileTest.length);
				var noOfTowers=$sessionStorage.towerCount;
				var name = "Template";
				var tempID = 1;	
				var rpDealVersionId = $localStorage.rpDealVersionId;
				if($sessionStorage.citiId2staffing!=null)
				{
					var cityId = $sessionStorage.citiId2staffing;	
				}
				else{
				var cityId = 0;
				}
				var docType =RateCard.sheetTypeModel;
				var file = $('input[name="fuUploadFilename"]').get(0).files[0];
				//console.dir(file);
				var formData = new FormData();
				formData.append('file', file);
				formData.append('name', name);
				formData.append('TempId', tempID);
				formData.append('rpDealVersionId', rpDealVersionId);
				formData.append('cityId',cityId);
				//console.log(file);
				var uploadUrl = contextPath+"/RightPrice-DAS/uploadTMStaffingExcel";
			    $http.post(uploadUrl, formData, {
			        transformRequest: angular.identity,
			        headers: {'Content-Type': undefined ,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')},
			    }).
			    then(function(data) {
			    	//console.log("data.data[0]");
			    	//console.log(data);
			    	var obj = JSON.stringify(data);
					var json = JSON.parse(obj);
					var customMessage = data.data;
			    	if(data.status == 200)	
					{
						BootstrapDialog.show({
							title : 'T&M Deal Creation - Staffing.',
							type : BootstrapDialog.TYPE_PRIMARY,
							message :  "File Upload Successfully.",
							closable : false,
							buttons : [ {
								label : 'OK',
								action :function(
										dialogRef) {
									dialogRef.close();
									window.location="TMDealCreationStaffing";
								}
							} ]
						});
						angular.element("input[type='file']").val(null);
						$scope.searchFile(versionId);
					} 
			    	else if(data.status == 205){
							BootstrapDialog.show({
							title : 'T&M Deal Creation - Staffing.',
							type : BootstrapDialog.TYPE_DANGER,
							message : "File Upload Failed.",
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									window.location="TMDealCreationStaffing";
								}
							} ]
						});
					}
					else {
						BootstrapDialog.show({
						title : 'T&M Deal Creation - Staffing.',
						type : BootstrapDialog.TYPE_DANGER,
						message :  "File Upload Failed",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
							}
						} ]
					});
				
				}
			    
			    },function(data) {
			        $scope.displayres = data.data;
			        $scope.answer = 'Posting data was unsuccessful.';
			});
				
			};
			
			$scope.deleteManualFile = function(attachementId){
				var deleteManualFile = function(response) {
					$scope.deleteResponse = response;
					
					if($scope.deleteResponse.status == 200){
						$scope.message = $scope.deleteResponse.data;
		    	        BootstrapDialog.show({
		    	        	title : 'T&M Deal Creation - Staffing',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : $scope.message,
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "TMDealCreationStaffing";
		    	        		}
		    	        	}]
		    	        });
					}else{
		    			$scope.message = $scope.deleteResponse.data;
		    			BootstrapDialog.show({
		    	        	title : 'T&M Deal Creation - Staffing',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : $scope.message,
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        		}
		    	        	}]
		    	        });
		    		}
				}
				WebServiceFactory.deleteManualFile(attachementId).then(deleteManualFile);
			};
			
		}]);