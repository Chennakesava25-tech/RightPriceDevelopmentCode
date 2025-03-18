// 		var app = angular.module('FPDealCreationPFCostInputsApp', []);
		app.controller("FPDealCreationPFCostInputsController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','fpDealCostInputsService','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory,fpDealCostInputsService,$localStorage, $sessionStorage, $index) {
			$scope.selectedTowerCurrency = null;
			$scope.Prev = function() 
		    {   
		        window.location='FPDealCreationStaffing';
		    };
		    $scope.Next = function()
		    {   
			    if($scope.isDevelopment==true)
			    {
			        window.location='FPDealCreationUploadEstimationRelatedDocuments';
			    }
			    else{
			    	 window.location='FPDealCreationWhatIfApplicationwise';
			    }
		    };
		    $scope.city = [];
		    $scope.onlineFacCost = [];
		    $scope.disabled = true;
		    $scope.onlineFacCostMarker = [];
		    $scope.travelRelocationCostMarker = [];
		    $scope.shiftHoursData = [];
		    var contextPath = "/RightPrice-DAS"; 
		    var yearDiff = 0;
		    var monthDiff = 0;
		    $scope.yearOneQty = true;
		    $scope.yearTwoQty = true;
		    $scope.yearThreeQty = true;
		    $scope.yearFourQty = true;
		    $scope.yearFiveQty = true;
		    $scope.yearSixQty = true;
		    $scope.yearSevenQty = true;
		    $scope.isRCPricing=false;
		    $scope.yearEightQty = true;
		    $scope.yearNineQty = true;
		    $scope.yearTenQty = true;
		    $scope.isSummaryFlag = false;
		    $scope.noOfMonths = null;
		    $scope.showstanrates=false;
		    $scope.isFPDealGFT = false;
		    $scope.isFPDealRiskManagers = false;
		    $scope.downloadEnabled= true;
		    $scope.isDevelopment=false;
		    var rpVrsId = $localStorage.rpDealVersionId;
		    
		   // $localStorage.rpDealVersionId = 
		    $scope.costInputsData = [];
		    
		    //colspan for before year header
		    $scope.colspan = 15;
		    
		    //Year header list
		    $scope.yearHeader=[];
		    
		    //Month header list
		    $scope.monthHeader=[];
		    
		    $scope.onSave = false;
		    $scope.onSearch= false;	
		    $scope.costType = [{ name: "Onsite", id: 1 }, { name: "Offshore", id: 2 }, { name: "Project Specific Cost", id: 3 }];
			
			$scope.saveData = function(frmFpCostInputs){
				$scope.onSave= true;
				//$scope.downloadBtn = false;
				if(frmFpCostInputs.$valid){
					$scope.saveOnsiteCostDetails();
				}
			};
			
			$scope.fpcostType = function(costtype)
	    	{
	    		if(costtype==3)
	    			{
	    			$scope.showstanrates=true;
	    			}
	    		else
	    			{
	    			$scope.showstanrates=false;
	    			}
	    	}
			
			var userType = sessionStorage.getItem('userType');
			 console.log("The USer Value from the Session is........ "+ userType);
			 if(userType == 'GFT') {
				 $scope.isFPDealGFT = true;
			 }
			 else if(userType == 'RiskManagers') {
					$scope.isFPDealRiskManagers = true;
				}
		    
			var getDealDetails = function(response) {
				console.log("deal Data ::::")
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
		  		
		  		
		  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
		  		var date = new Date(dealEndDate.substring(0,10));
//		  		console.log("End Date is......... " + date);
		  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.dealDetails[0].dealEndDate = dateENd;
				console.log("dateEND     ...... "+dateENd)
		  		
				var dealStartDate = $scope.dealDetails[0].dealStartDate;
				var date = new Date(dealStartDate.substring(0,10));
				var dateStart = $filter('date')(date,'dd/MM/yyyy');
				$scope.dealDetails[0].dealStartDate = dateStart;
				
				var yearStart   = parseInt(dateStart.substring(6,10));
				var yearEnd   = parseInt(dateENd.substring(6,10));
				console.log(yearEnd - yearStart);
				$scope.DealDuration  = yearEnd - yearStart;
				
				var startDateArray =  dateStart.split("/");
      			var endDateArray = dateENd.split("/");
      			var startDate  = new Date(startDateArray[1] + '/' + startDateArray[0] + '/' + startDateArray[2]);
      			console.log("Start Date is........... "+ startDate);
      			var endDate  = new Date(endDateArray[1] + '/' + endDateArray[0] + '/' + endDateArray[2]);
				console.log("End Date is....... "+ endDate);
				console.log("The Start Date with Time is...... "+ startDate.getTime());
				console.log("The Start Date with Time is...... "+ endDate.getTime());
				var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());   
				 var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
				 console.log("The Difference in Days is......."+diffDays);
				 yearDiff = Math.ceil(diffDays/365);
				 console.log("The Year Differene is......... "+ yearDiff);
				 monthDiff = yearDiff * 12;
				 console.log("The Month Diff is......... "+ monthDiff);
			};
		WebServiceFactory.getDealDetails($localStorage.DealModel,$localStorage.rpDealVersionId).then(getDealDetails); 
		
		  $scope.contryList =$sessionStorage.ContryList;
		    $scope.chkBoxSelectedCountryList = $scope.contryList;
		    $scope.cityList = $sessionStorage.CityList;
		    
		    // get the Deal Tower details 
		    var getDealTower = function(response) 
			{	console.log("fp Deal Tower data ..............");
				console.log(response);
				$scope.towerdetails = response.data;
				if($scope.towerdetails != undefined) {
					for(var i = 0;i<$scope.towerdetails.length;i++) {
						if($scope.towerdetails[i].towerId == $scope.frmFpCostInputs.ddlTowerModel) {
							$scope.frmFpCostInputs.ddlTransactionMonth = $scope.towerdetails[i].transitionMonth;
							$sessionStorage.totalTransMonth  = $scope.frmFpCostInputs.ddlTransactionMonth;
						}
					}
				}
			
			};
			WebServiceFactory.getDealTower($localStorage.rpDealVersionId).then(getDealTower);
		    
		    
		    $scope.getTowerCountryCity = function (fpdTowervalue) {
		    	$scope.searchRoleDetailsResult=[];
		    	angular.forEach($scope.towerdetails, function(value, key) {
					 if(fpdTowervalue.towerName == $scope.towerdetails[key].towerName)
						 {
						 	
						 	countryId=$scope.towerdetails[key].countryId
						 	cityId=$scope.towerdetails[key].cityId;
						 	dealAutoTowerId=$scope.towerdetails[key].dealautoTowerId;
						 	
						 }
					});
		    	
		    	var getCountryDetail = function(response) {
		    		console.log("Deal version ID : " + $localStorage.rpDealVersionId);
		    		console.log("get country data");
			  		console.log(response);
			  		$scope.country = response.data;
			  		angular.forEach($scope.country, function (value, key) {
			  			if($scope.country[key].countryId == countryId)
			  				{
			  					$scope.frmFpCostInputs.ddlCountryModel=$scope.country[key].countryName;
			  					$scope.selectedTowerCurrency = $scope.country[key].currencyCode;
			  				}
		               });
			  		
				};
				WebServiceFactory.getCountryDetail().then(getCountryDetail);
				
		    	 var getCities = function(response) {
		     		$scope.city = response.data;
		     		console.log("City Names are...........");
		     		console.log($scope.city);
		     		angular.forEach($scope.city, function(value, key) {
						 if($scope.city[key].cityId == cityId)
							 {
							 	$scope.frmFpCostInputs.ddlCityModel=$scope.city[key].cityName;
							 	categoryId=$scope.city[key].categorizationId;
							 	var getCitycategorization = function(response)
							 	{
							 		console.log("City Category data....");
							 		console.log(response);
							 		$scope.getCityCat=response.data;
							 		angular.forEach($scope.getCityCat, function(value, key) {
							 		if($scope.getCityCat[key].codeName==categoryId)
							 			{
							 			$scope.frmFpCostInputs.dealRoleDetailsCategorizationModel=$scope.getCityCat[key].description;
							 			}
							 		});
							 	};
								WebServiceFactory.getCitycategorization().then(getCitycategorization);
							 
							 }
						});
		     	
		     	};
		     	WebServiceFactory.getCities(countryId).then(getCities);
		    }
		    
		    var temp = 0;
		    $scope.getFpDealCostInputsData = function(fpCostInputsFrm){
				$scope.onSearch= true;
				$scope.downloadEnabled= true;
				//$scope.downloadBtn = false;
				if(fpCostInputsFrm.$valid){
					$scope.onSearch= false;
					console.log("ffffffffffffffffffrm");
					console.log(fpCostInputsFrm);
					console.log(fpCostInputsFrm.ddlTowerModel.cityId);
					console.log(fpCostInputsFrm.ddlTowerModel.dealTowerId);
					console.log(fpCostInputsFrm.ddlCostTypeModel);
					var towerId = fpCostInputsFrm.ddlTowerModel.dealTowerId;
					var cityId = fpCostInputsFrm.ddlTowerModel.cityId;
					var costType = fpCostInputsFrm.ddlCostTypeModel;
					//put city id and tower id as input
					var getRateCardInfo = function(response) {
						console.log(fpCostInputsFrm);
						console.log("Response is:");
						console.log(response)
						
						$scope.costInputsData=response.data;
						if($scope.costInputsData.length != 0) {
							
							//var testContractor=$scope.data[0].isContractor;
							var header = response.data[0].staffingDetailsHeader;
							$localStorage.firstYear=header.monthYearHeader1;
							$scope.colspan = 3;
							
							//create year header
							if(temp==0) //a
							{
								fpDealCostInputsService.createYearHeader($scope,header);
								$scope.setYearHeaderFinal('Summary');
								temp=1;
								$scope.downloadEnabled= false;
								$scope.isSummaryFlag = true; 
								
							}
							else
								{
								$scope.downloadEnabled= false;
								}
							
						} else {
							$scope.downloadEnabled= true;
							 BootstrapDialog.show({
					   	        	title : 'Master Lob Details',
					   	        	type : BootstrapDialog.TYPE_DANGER,
					   	        	message : 'No Data Found',
					   	        	closable : false,
					   	        	buttons : [{
					   	        		label : 'OK',
					   	        		action : function(dialogRef) {
					   	        			dialogRef.close();
					   	        		}
					   	        	}]
					   	        });
						}
						//set visa id 2 data(Onsite Deputed)
						//fpDealCostInputsService.setOnsiteDeputedData($scope);
						
					};
					WebServiceFactory.getFpDealCostInputsData(towerId,costType,$localStorage.rpDealVersionId).then(getRateCardInfo);
				}
		    };
		    
		    
		    //Set Year Header
		    $scope.setYearHeader = function(yearHeader){
		    	//alert('setYearHeader');
		    	console.log(yearHeader);
		    	var flag=false;
		    	$scope.currentMonthYearHeader = yearHeader.year;
		    	if($scope.currentMonthYearHeader =='Summary')
		    	{
		    		flag=false;
		    		$scope.isSummaryFlag = true;
		    		$scope.summaryFlag = ["5"];
		    		$scope.calculateSummaryRowTotal($scope.costInputsData);
		    		
		    	}
		    	else
		    	{
		    	$scope.summaryFlag = [];
		    	$scope.isSummaryFlag = false;
		    	$scope.count = 1;
		    	angular.forEach($scope.costInputsData, function (value, key) {
		    		flag=true;
		    		if(flag){
			    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
			    			
			    			//create month header for year header
			    			$scope.costInputsData[key].countIndex = $scope.count++;
			    			if($scope.costInputsData[key].indirectCostDescription === "Laptops") {
			    				//$filter('number')(number, fractionSize)
			    				$scope.costInputsData[key].comment = $filter('number')($scope.costInputsData[key].unitCost, 0);
			    			}
			    			if($scope.costInputsData[key].indirectCostDescription == "Data_Cards") {
			    				$scope.costInputsData[key].comment =  $filter('number')($scope.costInputsData[key].unitCost, 0);
			    			}
			    			if($scope.costInputsData[key].indirectCostDescription == "DataCard_Monthly_Recurring") {
			    				$scope.costInputsData[key].comment =  $filter('number')($scope.costInputsData[key].unitCost, 0);
			    			}
			    			if($scope.costInputsData[key].indirectCostDescription == "Mobile_Phones") {
			    				$scope.costInputsData[key].comment =  $filter('number')($scope.costInputsData[key].unitCost, 0);
			    			}
			    			if($scope.costInputsData[key].indirectCostDescription == "Mobile_Phones_Monthly_Recurring") {
			    				$scope.costInputsData[key].comment =  $filter('number')($scope.costInputsData[key].unitCost, 0);
			    			}
			    		
			    			$scope.costInputsData[key].transactionMonth = $scope.noOfYears;
			    			temp = fpDealCostInputsService.createMonthHeader($scope,value.staffingDetailsHeader);
			    			flag = false;
			    		}
		    		}
		    	});
		    			//			+++++
		    	$scope.noOfMonths = $scope.monthHeader[0].number
		    	
	    angular.forEach($scope.costInputsData, function (value, key) {
	    	if(($scope.costInputsData[key].comment == null || $scope.costInputsData[key].comment == undefined || $scope.costInputsData[key].comment == "")){
	    		if($scope.costInputsData[key].indirectCostDescription == "Others1" || $scope.costInputsData[key].indirectCostDescription == "Others2" || $scope.costInputsData[key].indirectCostDescription == "Others3"){
	    				$scope.costInputsData[key].comment = 'Value'
	    		}
	    		else{
	    			$scope.costInputsData[key].comment = 'No. of FTE'
	    		}
	    	   	}
	    	
	    		
	    });
	    		
		    	 $scope.colors = [];
		    	 if($scope.currentMonthYearHeader == $localStorage.firstYear)
		    		 {
			    		 for(var i=0;i<$scope.frmFpCostInputs.ddlTransactionMonth;i++) {
			    			 
			    			 $scope.colors.push('green');
			    		 }
		    		 }
		    	}
		    };
		    
		    
		    $scope.calculateRowTotal = function (object,index){
				object.yearlyTotal = parseFloat(object.staffingFirstMonthCount ? object.staffingFirstMonthCount:0.0 ) 
				+ parseFloat(object.staffingSecondMonthCount ? object.staffingSecondMonthCount:0.0) 
				+ parseFloat(object.staffingThirdMonthCount ? object.staffingThirdMonthCount:0.0) 
				+ parseFloat(object.staffingFourthMonthCount ? object.staffingFourthMonthCount:0.0) 
				+ parseFloat(object.staffingFifthMonthCount ? object.staffingFifthMonthCount:0.0) 
				+ parseFloat(object.staffingSixthMonthCount ? object.staffingSixthMonthCount:0.0) 
				+ parseFloat(object.staffingSeventhMonthCount ? object.staffingSeventhMonthCount:0.0) 
				+ parseFloat(object.staffingEighthMonthCount ? object.staffingEighthMonthCount:0.0) 
				+ parseFloat(object.staffingNinthMonthCount ? object.staffingNinthMonthCount:0.0)
				+ parseFloat(object.staffingTenthMonthCount ? object.staffingTenthMonthCount :0.0) 
				+ parseFloat(object.staffingEleventhMonthCount ? object.staffingEleventhMonthCount  : 0.0) 
				+ parseFloat(object.staffingTwelthMonthCount ? object.staffingTwelthMonthCount:0.0);
				
				
				angular.forEach($scope.costInputsData, function(value,key){
					if(value.monthYearHeader == "Summary") {
						if(object.indirectCostDescription == $scope.costInputsData[key].indirectCostDescription) {
							if(object.staffingYear == $scope.costInputsData[key].staffingYear){
								value.staffingFirstMonthCount=object.yearlyTotal
							} else if(object.staffingYear == $scope.costInputsData[key].staffingYear+1) {
								value.staffingSecondMonthCount=object.yearlyTotal
							} else if(object.staffingYear == $scope.costInputsData[key].staffingYear+2) {
								value.staffingThirdMonthCount=object.yearlyTotal
							} else if(object.staffingYear == $scope.costInputsData[key].staffingYear+3) {
								value.staffingFourthMonthCount=object.yearlyTotal
							} else if(object.staffingYear == $scope.costInputsData[key].staffingYear+4) {
								value.staffingFifthMonthCount=object.yearlyTotal
							} else if(object.staffingYear == $scope.costInputsData[key].staffingYear+5) {
								value.staffingSixthMonthCount=object.yearlyTotal
							}
						}
					} 
				});
			};
			
			$scope.calculateSummaryRowTotal = function(costInputArray) {
				$scope.count = 1;
				for(var i=0;i<$scope.costInputsData.length;i++){
					if($scope.costInputsData[i].monthYearHeader	!= "Summary"){
						var month = [];
						month  = $scope.costInputsData[i].monthYearHeader.split("/")
						var month1Name = [];
						var month2Name = [];
						month1Name  =  month[0].split("-")
						month2Name =   month[1].split("-")
						break;
					}
				}
				
				
				angular.forEach(costInputArray,function(value,key){
					if(value.monthYearHeader == "Summary") {
						$scope.costInputsData[key].countIndex = $scope.count++;
						value.yearlyTotal = parseFloat(value.staffingFirstMonthCount ? value.staffingFirstMonthCount:0.0 ) 
						+ parseFloat(value.staffingSecondMonthCount ? value.staffingSecondMonthCount:0.0) 
						+ parseFloat(value.staffingThirdMonthCount ? value.staffingThirdMonthCount:0.0) 
						+ parseFloat(value.staffingFourthMonthCount ? value.staffingFourthMonthCount:0.0) 
						+ parseFloat(value.staffingFifthMonthCount ? value.staffingFifthMonthCount:0.0) 
						+ parseFloat(value.staffingSixthMonthCount ? value.staffingSixthMonthCount:0.0) 
						+ parseFloat(value.staffingSeventhMonthCount ? value.staffingSeventhMonthCount:0.0) 
						+ parseFloat(value.staffingEighthMonthCount ? value.staffingEighthMonthCount:0.0) 
						+ parseFloat(value.staffingNinthMonthCount ? value.staffingNinthMonthCount:0.0)
						+ parseFloat(value.staffingTenthMonthCount ? value.staffingTenthMonthCount :0.0) 
						+ parseFloat(value.staffingEleventhMonthCount ? value.staffingEleventhMonthCount  : 0.0) 
						+ parseFloat(value.staffingTwelthMonthCount ? value.staffingTwelthMonthCount:0.0);
					}
				});
				console.log("++++++++++")
				console.log($scope.costInputsData[0].staffingDetailsHeader.monthYearHeader1)
			}

			
	    	var getVersionData =function(response)
	    	{
	    		console.log("Version Data");
	    		console.log(response);
	    		$scope.versionDetails = response.data;
	    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
	    				$scope.versionDetails[0].currentApprovalStatus == 4 || $scope.versionDetails[0].currentApprovalStatus == 8 )&& userType == 'Delivery')
    			{
    				$scope.isSaveDisabled=false;
    			}
    		else
    			{
    			    $scope.isSaveDisabled=true;
    			}
	    		
	    		if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId == 0)
				{
					$scope.versionDetails[0].dealStatus='Open';
				}
				else if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId == 1)
					{
						$scope.versionDetails[0].dealStatus='Won';
					}
				else{
						$scope.versionDetails[0].dealStatus='Lost';
				}
	    		
	    		$localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
	    		 if(userType == 'Delivery') {
	    		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
	    				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<4)
	    			{
	    			BootstrapDialog.show({
						title : 'FP Deal Creation - Cost Inputs',
						type : BootstrapDialog.TYPE_DANGER,
						message : 'Data is not saved at previous screen.',
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								//$window.location.reload();
								window.location="FPDealCreationStaffing";
							}
						} ]
					});
	    		}
	    		 }
	    		if($scope.versionDetails[0].fpProjectTypeId==1)
	    			{
	    				$scope.versionDetails[0].projectType='Development - Fixed Price';
	    				$scope.masterRole=true;
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==2)
	    			{
	    				$scope.versionDetails[0].projectType='Development - Manage Capacity';
	    				$scope.masterRole=false;
	    			}
	    		else if($scope.versionDetails[0].fpProjectTypeId==3)
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Fixed Price';
	    				$scope.masterRole=true;
	    			}
	    		else
	    			{
	    				$scope.versionDetails[0].projectType='Maintenance - Manage Capacity';
	    				$scope.masterRole=false;
	    			}
	    		
	    		if($scope.versionDetails[0].fpProjectTypeId==1 || $scope.versionDetails[0].fpProjectTypeId==2){
	    			$scope.isDevelopment=true;	
	    		}
	    		else{
	    			$scope.isDevelopment=false;
	    		}
	    		
	    		/*$localStorage.pricingType=$scope.versionDetails[0].pricingType;

	    		if($localStorage.pricingType!=1)
				{
				  $scope.isRCPricing=true;
				}
				else{
					$scope.isRCPricing=false;
				}*/
	    	};
	    	WebServiceFactory.getVersionData($localStorage.rpDealVersionId).then(getVersionData);
	    	
	    	
	        
	        // saving the Cost inputs data.
	        $scope.saveOnsiteCostDetails = function() {
	        	console.log("Insisde the cost inputs function");
	        	
	        	var marker = $scope.costInputsData;
	        	console.log("The marker is........... ");
	        	console.log(marker);
	        	
	        	$http({
					 method: 'POST',
					 url:  contextPath+"/RightPrice-DAS/updateCostInputData",
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
		   	        			window.location = "FPDealCreationCostInputs";
		   	        		}
		   	        	}]
		   	        });
				},function (data) {
					$scope.displayres = data;
				    $scope.answer = 'Posting data was unsuccessful.';
				});
	        	
	        };
	        
	        $scope.setYearHeaderFinal = function(yearHeader){
	        	$scope.currentMonthYearHeader = yearHeader;  // yearHeader.year;		    	
	        	var flag=true;
	    		$scope.summaryFlag = ["5"];    		
	    		angular.forEach($scope.data, function (value, key) {
	        		if(flag){
	        			if($scope.currentMonthYearHeader =='Summary')
	    		    	{
	    		    		flag=false;
	    		    		$scope.isSummaryFlag = true;
	    		    		$scope.summaryFlag = ["5"];
	    		    		$scope.calculateSummaryRowTotal($scope.costInputsData);
	    		    	}
	        		}
	    	});
	        	
	        };
	        
	        
	        var getWhatIfCalculationData=function(response)
			{
	        	$scope.rpDealVersionId = rpVrsId;
				$scope.whatIfCalDetails = response.data;
				$scope.noOfYears=$scope.whatIfCalDetails[0].noOfYears;
			};
			WebServiceFactory.getWhatIfCalculationData($localStorage.rpDealVersionId).then(getWhatIfCalculationData);
	        

	        $scope.clearData = function() {
	        	$scope.onlineFacCost = [];
		    	 $scope.grandTotal = [];
	        };
	        
	        $scope.download = function(frmFpCostInputs){
	        	$scope.onSearch= true;
				if(frmFpCostInputs.$valid){
					
	        	var towerId=$scope.frmFpCostInputs.ddlTowerModel.dealTowerId;
	        	var costType = $scope.frmFpCostInputs.ddlCostTypeModel; 
	        	var rpVrsId = $localStorage.rpDealVersionId
	        	var crmDealId = $localStorage.DealModel
	        if($scope.noOfYears!=undefined)
	        {	
			window.location= contextPath+"/RightPrice-DAS/downloadFPdealCostInputExcel/"+towerId+"/"+costType+"/"+rpVrsId+"/"+$scope.noOfYears+"/"+crmDealId+"/"+rpVrsId;
	        }
	        else{
	        	window.location= contextPath+"/RightPrice-DAS/downloadFPdealCostInputExcel/"+towerId+"/"+costType+"/"+rpVrsId+"/"+0+"/"+crmDealId+"/"+rpVrsId;
	        }
				}				   
			    }
		}]);
