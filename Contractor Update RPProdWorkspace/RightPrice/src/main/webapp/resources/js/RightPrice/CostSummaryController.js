// 		var app = angular.module('FPDealCreationCostSummaryApp', []);			
app.controller("CostSummaryController",['$scope','$location','$anchorScroll','$http','$window','$localStorage','$sessionStorage','WebServiceFactory','$filter', function($scope,$location,$anchorScroll,$http,$window,$localStorage,$sessionStorage,WebServiceFactory,$filter,$index) {
	 var dealVersionId;
	 var dealId;
	 $scope.costData=[];
	 $scope.costDataBuild=[];
	 var marker=[];
	 $scope.totalHide=true;
	 $scope.saved=false;	
	 $scope.hideTransition=true;
	 $scope.whatifRevenueType=true;
	 $scope.isSaveDisabled=false;
	 $scope.isFPDealGFT = false;
	 $scope.isRCPricing=false;
		$scope.isDevelopment=false;
	 $scope.isDownloadEnabled=false;
	 $sessionStorage.DealModel=$localStorage.DealModel;
	 $scope.whatifGmType=true;
	 var contextPath = "/RightPrice-DAS";
	 console.log("the New Deal Version Id......")
	 console.log($sessionStorage.rpDealVId);
	 var userType = sessionStorage.getItem('userType');
	 console.log("The Fp Manual Summary Details...... "+userType);
	 if(userType == 'GFT') {
		 $scope.isFPDealGFT = true;
	}
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
		if(userType == 'GFT') {
			window.location='FPDealCreationCalculationDetails';
		} else {
			window.location='FPDealCreationWhatIfApplicationwise';
		}
    };
    $scope.Next = function()
    {   
        window.location='FpPricingDetails';
    }; 
    
    $scope.download = function(costBreakUp){
    	if($scope.costDataBuild!=null){
    		$scope.isDownloadEnabled=false;
    	}else{
    		$scope.isDownloadEnabled=true;
    	}
		var deald = 	$localStorage.DealModel;
		var dealVersionId= $localStorage.rpDealVersionId;
		
		window.location= contextPath+"/RightPrice-DAS/downloadCostBreakupExcel/"+deald+"/"+dealVersionId
	
    }
    $scope.save=function(costBreakUp){
    	$scope.saved=true;
    	if(costBreakUp.$valid){
    		$scope.saved=false;	
    	} 
    	$scope.savedata($scope.costDataBuild);
    }
    
    $scope.calculateRowTotal = function (object,index){
		object.yearTotal = parseFloat(object.transistion ? object.transistion:0.0 ) 
		+ parseFloat(object.year_1 ? object.year_1:0.0) 
		+ parseFloat(object.year_2 ? object.year_2:0.0) 
		+ parseFloat(object.year_3 ? object.year_3:0.0) 
		+ parseFloat(object.year_4 ? object.year_4:0.0) 
		+ parseFloat(object.year_5 ? object.year_5:0.0) 
		+ parseFloat(object.year_6 ? object.year_6:0.0) 
		+ parseFloat(object.year_7 ? object.year_7:0.0) 
		+ parseFloat(object.year_8 ? object.year_8:0.0)
		+ parseFloat(object.year_9 ? object.year_9 :0.0) 
		+ parseFloat(object.year_10 ? object.year_10 : 0.0);
		
		if(object.costCode==101)
			{
				
				if(object.transistion!=null && object.transistion!=0)
				{
					$scope.costDataBuild[index+1].transistion =  ((object.transistion - $scope.costDataBuild[index-3].transistion)/object.transistion)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].transistion=0;
				}
			
				if(object.year_1!=null && object.year_1!=0)
				{
					$scope.costDataBuild[index+1].year_1 =  ((object.year_1 - $scope.costDataBuild[index-3].year_1)/object.year_1)*100;
				}
				else
					{
					$scope.costDataBuild[index+1].year_1=0;
					}
				if(object.year_2!=null && object.year_2!=0)
				{
					$scope.costDataBuild[index+1].year_2 =  ((object.year_2 - $scope.costDataBuild[index-3].year_2)/object.year_2)*100;
				}
				else
					{
						$scope.costDataBuild[index+1].year_2=0;
					}
				if(object.year_3!=null && object.year_3!=0)
				{
					$scope.costDataBuild[index+1].year_3 =  ((object.year_3 - $scope.costDataBuild[index-3].year_3)/object.year_3)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_3=0;
				}
				if(object.year_4!=null && object.year_4!=0)
				{
					$scope.costDataBuild[index+1].year_4 =  ((object.year_4 - $scope.costDataBuild[index-3].year_4)/object.year_4)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_4=0;
				}
				if(object.year_5!=null && object.year_5!=0)
				{
					$scope.costDataBuild[index+1].year_5 =  ((object.year_5 - $scope.costDataBuild[index-3].year_5)/object.year_5)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_5=0;
				}
				if(object.year_6!=null && object.year_6!=0)
				{
					$scope.costDataBuild[index+1].year_6 =  ((object.year_6 - $scope.costDataBuild[index-3].year_6)/object.year_6)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_6=0;
				}
				if(object.year_7!=null && object.year_7!=0)
				{
					$scope.costDataBuild[index+1].year_7 =  ((object.year_7 - $scope.costDataBuild[index-3].year_7)/object.year_7)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_7=0;
				}
				if(object.year_8!=null && object.year_8!=0)
				{
					$scope.costDataBuild[index+1].year_8 =  ((object.year_8 - $scope.costDataBuild[index-3].year_8)/object.year_8)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_8=0;
				}
				if(object.year_9!=null && object.year_9!=0)
				{
					$scope.costDataBuild[index+1].year_9 =  ((object.year_9 - $scope.costDataBuild[index-3].year_9)/object.year_9)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_9=0;
				}
				if(object.year_10!=null && object.year_10!=0)
				{
					$scope.costDataBuild[index+1].year_10 =  ((object.year_10 - $scope.costDataBuild[index-3].year_10)/object.year_10)*100;
				}
				else
				{
					$scope.costDataBuild[index+1].year_10=0;
				}
			
				$scope.costDataBuild[index+3].yearTotal = $scope.costDataBuild[index+2].yearTotal - object.yearTotal;
				$scope.costDataBuild[index+1].yearTotal=(($scope.costDataBuild[index].yearTotal-$scope.costDataBuild[index-3].yearTotal)/$scope.costDataBuild[index].yearTotal)*100;
			}
		else 
			{
			if(object.transistion!=null && object.transistion!=0)
			{
				$scope.costDataBuild[index-1].transistion = $scope.costDataBuild[index-4].transistion/(1-(object.transistion/100));
				var tranrev=$scope.costDataBuild[index-1].transistion;
			}
			else
			{
				$scope.costDataBuild[index-1].transistion=0;
				var tranrev=0;
			}
			if(object.year_1!=null && object.year_1!=0)
			{
				$scope.costDataBuild[index-1].year_1 = $scope.costDataBuild[index-4].year_1/(1-(object.year_1/100));
				var year1rev=$scope.costDataBuild[index-1].year_1;
			}
			else
				{
					$scope.costDataBuild[index-1].year_1=0;
					var year1rev=0;
				}
			if(object.year_2!=null && object.year_2!=0)
			{
				$scope.costDataBuild[index-1].year_2 = $scope.costDataBuild[index-4].year_2/(1-(object.year_2/100));
				var year2rev=$scope.costDataBuild[index-1].year_2;
			}
			else
				{
					$scope.costDataBuild[index-1].year_2=0;
					var year2rev=0;
				}
			if(object.year_3!=null && object.year_3!=0)
			{
				$scope.costDataBuild[index-1].year_3 = $scope.costDataBuild[index-4].year_3/(1-(object.year_3/100));
				var year3rev=$scope.costDataBuild[index-1].year_3;
			}
			else
			{
				$scope.costDataBuild[index-1].year_3=0;
				var year3rev=0;
			}
			if(object.year_4!=null && object.year_4!=0)
			{
				$scope.costDataBuild[index-1].year_4 = $scope.costDataBuild[index-4].year_4/(1-(object.year_4/100));
				var year4rev=$scope.costDataBuild[index-1].year_4;
			}
			else
			{
				$scope.costDataBuild[index-1].year_4=0;
				var year4rev=0;
			}
			if(object.year_5!=null && object.year_5!=0)
			{
				$scope.costDataBuild[index-1].year_5 = $scope.costDataBuild[index-4].year_5/(1-(object.year_5/100));
				var year5rev=$scope.costDataBuild[index-1].year_5;
			}
			else
			{
				$scope.costDataBuild[index-1].year_5=0;
				var year5rev=0;
			}
			if(object.year_6!=null && object.year_6!=0)
			{
				$scope.costDataBuild[index-1].year_6 = $scope.costDataBuild[index-4].year_6/(1-(object.year_6/100));
				var year6rev=$scope.costDataBuild[index-1].year_6;
			}
			else
			{
				$scope.costDataBuild[index-1].year_6=0;
				var year6rev=0;
			}
			if(object.year_7!=null && object.year_7!=0)
			{
				$scope.costDataBuild[index-1].year_7 = $scope.costDataBuild[index-4].year_7/(1-(object.year_7/100));
				var year7rev=$scope.costDataBuild[index-1].year_7;
			}
			else
			{
				$scope.costDataBuild[index-1].year_7=0;
				var year7rev=0;
			}
			if(object.year_8!=null && object.year_8!=0)
			{
				$scope.costDataBuild[index-1].year_8 = $scope.costDataBuild[index-4].year_8/(1-(object.year_8/100));
				var year8rev=$scope.costDataBuild[index-1].year_8;
			}
			else
			{
				$scope.costDataBuild[index-1].year_8=0;
				var year8rev=0;
			}
			if(object.year_9!=null && object.year_9!=0)
			{
				$scope.costDataBuild[index-1].year_9 = $scope.costDataBuild[index-4].year_9/(1-(object.year_9/100));
				var year9rev=$scope.costDataBuild[index-1].year_9;
			}
			else
			{
				$scope.costDataBuild[index-1].year_9=0;
				var year9rev=0;
			}
			if(object.year_10!=null && object.year_10!=0)
			{
				$scope.costDataBuild[index-1].year_10 = $scope.costDataBuild[index-4].year_10/(1-(object.year_10/100));
				var year10rev=$scope.costDataBuild[index-1].year_10;
			}
			else
			{
				$scope.costDataBuild[index-1].year_10=0;
				var year10rev=0;
			}
			
			$scope.costDataBuild[index-1].yearTotal=tranrev+year1rev+year2rev+year3rev+year4rev+year5rev+year6rev+year7rev+year8rev+year9rev+year10rev;
			$scope.costDataBuild[index+2].yearTotal = $scope.costDataBuild[index+1].yearTotal - $scope.costDataBuild[index-1].yearTotal;
			$scope.costDataBuild[index].yearTotal=(($scope.costDataBuild[index-1].yearTotal-$scope.costDataBuild[index-4].yearTotal)/$scope.costDataBuild[index-1].yearTotal)*100;
			}
		
			
	};
    
     $scope.calculas=function(costDataBuild,tranvalue){

    	var transition=parseInt($scope.costDataBuild.TransitionModel);
    	var year1 =  parseInt($scope.costDataBuild.year1Model);
    	var year2 = parseInt($scope.costDataBuild.year2Model);
    	var year3 = parseInt($scope.costDataBuild.year3Model);
    	var year4 = parseInt($scope.costDataBuild.year4Model);
    	var year5 = parseInt($scope.costDataBuild.year5Model);
    	var year6 = parseInt($scope.costDataBuild.year6Model);
    	var year7 = parseInt($scope.costDataBuild.year7Model);
    	var year8 = parseInt($scope.costDataBuild.year8Model);
    	var year9 = parseInt($scope.costDataBuild.year9Model);
    	var year10 = parseInt($scope.costDataBuild.year10Model);
    	
    	
  	   if(costDataBuild.txtyear2==undefined)
  	  {
  		 $scope.costDataBuild.year1Model=totalYear-transition;
  	  }
  	  else if(costDataBuild.txtyear3==undefined)
  	  {
  		$scope.costDataBuild.year2Model=totalYear-(transition+year1);
  	  }
  	  else if(costDataBuild.txtyear4==undefined)
  	  {
  		$scope.costDataBuild.year3Model=totalYear-(transition+year1+year2);  
  	  }
  	  else if(costDataBuild.txtyear5==undefined)
  	  {
  		$scope.costDataBuild.year4Model=totalYear-(transition+year1+year2+year3);
  	  }
  	  else if(costDataBuild.txtyear6==undefined)
  	  {
  		$scope.costDataBuild.year5Model=totalYear-(transition+year1+year2+year3+year4);
  	  }
  	  else if(costDataBuild.txtyear7==undefined)
  	  {
  		$scope.costDataBuild.year6Model=totalYear-(transition+year1+year2+year3+year4+year5);
  	  }
  	  else if(costDataBuild.txtyear8==undefined)
  	  {
  		$scope.costDataBuild.year7Model=totalYear-(transition+year1+year2+year3+year4+year5+year6);
  	  }
  	  else if(costDataBuild.txtyear9==undefined)
  	  {
  		$scope.costDataBuild.year8Model=totalYear-(transition+year1+year2+year3+year4+year5+year6+year7);
  	  }
  	  else if(costDataBuild.txtyear10==undefined)
  	  {
  		$scope.costDataBuild.year9Model=totalYear-(transition+year1+year2+year3+year4+year5+year6+year7+year8);
  	  }
  	  else{
  		$scope.costDataBuild.year10Model=totalYear-(transition+year1+year2+year3+year4+year5+year6+year7+year8+year9);
  		  
  	  }
  	  
  }
    
  /*  $scope.savedata=function(costDataBuild)
    {
    	var updateBillingSchedule = function(response) {
    		var markers=$scope.costDataBuild
    		
        }
    	WebServiceFactory.updateBillingSchedule(markers,$sessionStorage.dealId,$localStorage.rpDealVersionId).then(updateBillingSchedule);
    }
    */
    
    
    $scope.savedata = function(costDataBuild) {
    	
    	var marker=$scope.costDataBuild
    	console.log("The marker is........... ");
    	console.log(marker);
    	
    	$http({
			 method: 'POST',
			 url:  contextPath+"/RightPrice-DAS/updateCostBreakupData",
			 dataType: 'json',
			 data: angular.toJson(marker),  
           headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
	   	}).
	   	then(function(data) {
   			console.log("added");
   	        $scope.answer = 'Data has been updated successfully';
   	        BootstrapDialog.show({
   	        	title : 'Fp Deal Creation Details',
   	        	type : BootstrapDialog.TYPE_PRIMARY,
   	        	message : 'Data Updated SucessFully',
   	        	closable : false,
   	        	buttons : [{
   	        		label : 'OK',
   	        		action : function(dialogRef) {
   	        			dialogRef.close();
   	        			window.location = "FPDealCreationCostSummary";
   	        		}
   	        	}]
   	        });
		},function (data) {
			$scope.displayres = data;
		    $scope.answer = 'Posting data was unsuccessful.';
		});
    	
    };
    
    /*$scope.savedata=function(costBreakUp){
    	var totalModelvalue=0;
    	var transition,yearTotal;
    	var year1,year2,year3,year4,year5,year6,year7,year8,year9,year10;
    	
    	if($scope.costBreakUp.TransitionModel!=null || $scope.costBreakUp.TransitionModel!=undefined ){
    		transition=parseInt($scope.costBreakUp.TransitionModel);
    		totalModelvalue+=parseInt($scope.costBreakUp.TransitionModel);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	if($scope.costBreakUp.year1Model!=null || $scope.costBreakUp.year1Model!=undefined ){
    		year1=parseInt($scope.costBreakUp.year1Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year1Model);
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year2Model!=null || $scope.costBreakUp.year2Model!=undefined ){
    		year2=parseInt($scope.costBreakUp.year2Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year2Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year3Model!=null || $scope.costBreakUp.year3Model!=undefined ){
    		year3=parseInt($scope.costBreakUp.year3Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year3Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year4Model!=null || $scope.costBreakUp.year4Model!=undefined ){
    		year4=parseInt($scope.costBreakUp.year4Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year4Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year5Model!=null || $scope.costBreakUp.year5Model!=undefined ){
    		year5=parseInt($scope.costBreakUp.year5Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year5Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year6Model!=null || $scope.costBreakUp.year6Model!=undefined ){
    		year6=parseInt($scope.costBreakUp.year6Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year6Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year7Model!=null || $scope.costBreakUp.year7Model!=undefined ){
    		year7=parseInt($scope.costBreakUp.year7Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year7Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year8Model!=null || $scope.costBreakUp.year8Model!=undefined ){
    		year8=parseInt($scope.costBreakUp.year8Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year8Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year9Model!=null || $scope.costBreakUp.year9Model!=undefined ){
    		year9=parseInt($scope.costBreakUp.year9Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year9Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.year10Model!=null || $scope.costBreakUp.year10Model!=undefined ){
    		year10=parseInt($scope.costBreakUp.year10Model);
    		totalModelvalue+=parseInt($scope.costBreakUp.year10Model);	
    	}else{
    		totalModelvalue+=0;    		
    	}
    	
    	if($scope.costBreakUp.yearTotalModel!=null || $scope.costBreakUp.yearTotalModel!=undefined ){
    		yearTotal=parseInt($scope.costBreakUp.yearTotalModel);
    		$scope.costBreakUp.yearTotalModel=parseInt($scope.costBreakUp.yearTotalModel);	
    	}else{
    		$scope.costBreakUp.yearTotalModel=0;    		
    	}
    	
    	if($scope.costBreakUp.yearTotalModel==totalModelvalue){
    		
    		var update=function(response){

                BootstrapDialog.show({
               title : 'FP Deal Creation - Cost Summary',
               type : BootstrapDialog.TYPE_PRIMARY,
               message : "Data updated Successfully",
               closable : false,
               buttons : [{
                      label : 'OK',
                      action : function(dialogRef) {
                            dialogRef.close();
                            
                      }
               }]
         });
        	
    			
    		}
    		
    		var markers={
    				 "transistion":transition,
    				 "yearTotal":yearTotal,
    		    	 "year_1":year1,
    		    	 "year_2":year2,
    		    	 "year_3":year3, 
    		    	 "year_4":year4,
    		    	 "year_5":year5,
    		    	 "year_6":year6,
    		    	 "year_7":year7,
    		    	 "year_8":year8,
    		    	 "year_9":year9,
    		    	 "year_10":year10
    		};
    		
    		WebServiceFactory.updateBillingSchedule(markers,$sessionStorage.dealId,$localStorage.rpDealVersionId).then(update);
    	
    	}
    	else{
            BootstrapDialog.show({
           title : 'FP Deal Creation - Cost Summary',
           type : BootstrapDialog.TYPE_DANGER,
           message : "Years value and Total value should be same",
           closable : false,
           buttons : [{
                  label : 'OK',
                  action : function(dialogRef) {
                        dialogRef.close();
                        
                  }
           }]
     });
    	}
    }*/
   
    if($sessionStorage.rpVrsId!=undefined)
    	{
    		$localStorage.rpDealVersionId= $sessionStorage.rpVrsId;
    	}
    else
    	{
    		var rpVrsId = $localStorage.rpDealVersionId;	
    		dealVersionId=$localStorage.rpDealVersionId;	
    	}
    
    var getDealDetails = function(response) {				
  		console.log(response);
  		$scope.dealDetails = response.data;
  		console.log("Approver Data")
  		console.log($scope.dealDetails);
  		console.log("hey there ===="+$scope.dealDetails[0].dealId);
  		dealId=$scope.dealDetails[0].dealId;
  		$scope.currency=$scope.dealDetails[0].currency;
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
		$scope.dealDetails[0].rpVersionId = $localStorage.rpDealVersionId;	//arvind
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
	
	
	 
	
	$scope.exportToExcel = function(tableId) {
		
		var exportHref=WebServiceFactory.downloadExcel(tableId,'FP-Deal Creation');
	};
	
	var getVersionData =function(response)
	{
		console.log("Version Data");
		console.log(response);
		$scope.versionDetails = response.data;
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
		$localStorage.pageTracker=$scope.versionDetails[0].pageTrackerStatus;
		$localStorage.currencyID=$scope.versionDetails[0].currencyId;
		if(userType == 'Delivery') {
		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
				$scope.versionDetails[0].currentApprovalStatus == 4 ) && $localStorage.pageTracker<6)
			{
			BootstrapDialog.show({
				title : 'FP Deal Creation - Cost Summary',
				type : BootstrapDialog.TYPE_DANGER,
				message : 'Data is not saved at previous screen.',
				closable : false,
				buttons : [ {
					label : 'OK',
					action : function(dialogRef) {
						dialogRef.close();
						//$window.location.reload();
						
						
							if($localStorage.pageTracker==5)
							{
								if(userType == 'GFT') {
									window.location='FPDealCreationCalculationDetails';
								} else {
									window.location='FPDealCreationWhatIfApplicationwise';
								}
							}
							else if($localStorage.pageTracker==4)
							{
								window.location="FPDealCreationCostInputs";
							}
							else if($localStorage.pageTracker==3)
								{
									window.location="FPDealCreationStaffing";
								}
							else if($localStorage.pageTracker==2)
								{
								window.location="FPDealCreationRoleSelection";
								}
							else if($localStorage.pageTracker==1)
							{
							window.location="FPDealCreationRateCardAndProjectDetails";
							}
							else
								{
									window.location="FPDealCreationDetails";
								}
						
						
						
					}
				} ]
			});
		}
		}
		if(($scope.versionDetails[0].currentApprovalStatus == null || $scope.versionDetails[0].currentApprovalStatus == 1 ||
				$scope.versionDetails[0].currentApprovalStatus == 4 || $scope.versionDetails[0].currentApprovalStatus == 8 ) && userType == 'Delivery')
		{
  			 $scope.isSaveDisabled=false;
		}
  		else
  			{
  			 $scope.isSaveDisabled=true;
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
		
		
		var getFpToExchangeRates = function(response) 
		{
			console.log("fp EXCHANGE RATES  ..............");
			console.log(response);
			$scope.exchangedetails = response.data;
		
		};
		WebServiceFactory.getFpToExchangeRates(rpVrsId,$localStorage.currencyID).then(getFpToExchangeRates);
	};
	WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
	
	
	 $scope.getTowerCountryCity = function (fpdTowervalue) {
	    	$scope.searchRoleDetailsResult=[];
	    	$scope.selectededRolesArr=[];
	    	$scope.contractorRoleArr=[];
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
		  					$scope.fpdcRoleSelectionFrm.dealRoleDetailsCountryModel=$scope.country[key].countryName;
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
						 	$scope.fpdcRoleSelectionFrm.dealRoleDetailsCityModel=$scope.city[key].cityName;
						 	categoryId=$scope.city[key].categorizationId;
						 	
						 	var getCitycategorization = function(response)
						 	{
						 		console.log("City Category data....");
						 		console.log(response);
						 		$scope.getCityCat=response.data;
						 		angular.forEach($scope.getCityCat, function(value, key) {
						 		if($scope.getCityCat[key].codeName==categoryId)
						 			{
						 			$scope.fpdcRoleSelectionFrm.dealRoleDetailsCategorizationModel=$scope.getCityCat[key].description;
						 			}
						 		});
						 	};
							WebServiceFactory.getCitycategorization().then(getCitycategorization);
						 
						 }
					});
	     	
	     	};
	     	WebServiceFactory.getCities(countryId).then(getCities);
	     	
	     	
	     	var getFpDealRateCard = function(response)
	     	{
	     		console.log("RateCards based on Tower...");
	     		$scope.fpdealratecards = response.data;
	     		console.log($scope.fpdealratecards);
	     		dealRCId=$scope.fpdealratecards[0].rcId;
	     		
	     		$scope.fpdcRoleSelectionFrm.rateCardModel=$scope.fpdealratecards[0].rcName;
	     		
			 	
	     	}
	     	WebServiceFactory.getFpDealRateCard(dealAutoTowerId).then(getFpDealRateCard);
	      }
	
	
	 var getDealTower = function(response) 
		{
			console.log("fp Deal Tower data ..............");
			console.log(response);
			$scope.towerdetails = response.data;
		
		};
		WebServiceFactory.getDealTower(dealVersionId).then(getDealTower);
		
		$scope.yearsData=[];
		var getYearsNumber=function(response){
			console.log("What IF Year Count Data..............");
			console.log(response);
			$sessionStorage.WhatifType=response.data[0].revenueOrMarginFlag;

			 for(var i=1;i<=response.data[0].noOfYears;i++)
			 {
				 $scope.yearsData[i]=1;
			 }
		}
		WebServiceFactory.getYearCount(dealVersionId).then(getYearsNumber);
		
		  var getCostData=function(response){
			 
			  $scope.costDetails=response.data;
			  if($scope.costDetails!=""){
			  console.log("here is my data");  
			  console.log($scope.costDetails);
			  $scope.costDataBuild=angular.copy($scope.costDetails[0]);
			/*  $sessionStorage.whatIfRevenue=$scope.costDataBuild[16].yearTotal;*/
			  console.log("here is my data after copy");
			  console.log($scope.costDataBuild);
			  $scope.dummyCost=$scope.costDetails[0];
			  
			  angular.forEach($scope.costDataBuild,function(value,key)
					  {
				  		if($scope.costDataBuild[key].costCode==51)
				  			{
				  					$scope.costDataBuild.splice(key, 1);
				  			}
					  });
			  
			  
				angular.forEach($scope.costDataBuild,function(value,key)
						 {
					 
					 if($scope.costDataBuild[key].costCode==$sessionStorage.WhatifType)
					 {
						 $scope.costDataBuild[key].whatifRevenueType=false;
					 }
					 else
						 {
						 $scope.costDataBuild[key].whatifRevenueType=true;
						 }
				});
			  for(i=0;i<$scope.dummyCost.length;i++){
				  
			  if($scope.dummyCost[i].costCode!=101){
				  $scope.costData[i]=$scope.dummyCost[i];				  
			  }
			  else{
			  $scope.costBreakUp.yearTotalModel=$scope.dummyCost[i].yearTotal;
				if($scope.dummyCost[i].year_1!=null && $scope.dummyCost[i].year_1!=0){
					 $scope.costBreakUp.year1Model=	 $scope.dummyCost[i].year_1; 
				}
				if($scope.dummyCost[i].year_2!=null && $scope.dummyCost[i].year_2!=0){
					$scope.costBreakUp.year2Model=	 $scope.dummyCost[i].year_2;			  
				}
				if($scope.dummyCost[i].year_3!=null && $scope.dummyCost[i].year_3!=0){
					$scope.costBreakUp.year3Model=	 $scope.dummyCost[i].year_3;
				}
				if($scope.dummyCost[i].year_4!=null && $scope.dummyCost[i].year_4!=0){
					$scope.costBreakUp.year4Model=	 $scope.dummyCost[i].year_4;
				}
				if($scope.dummyCost[i].year_5!=null && $scope.dummyCost[i].year_5!=0){
					$scope.costBreakUp.year5Model=	 $scope.dummyCost[i].year_5;
				}
				if($scope.dummyCost[i].year_6!=null && $scope.dummyCost[i].year_6!=0){
					$scope.costBreakUp.year6Model=	 $scope.dummyCost[i].year_6;
				}
				if($scope.dummyCost[i].year_7!=null && $scope.dummyCost[i].year_7!=0){
					$scope.costBreakUp.year7Model=	 $scope.dummyCost[i].year_7;
				}
				if($scope.dummyCost[i].year_8!=null && $scope.dummyCost[i].year_8!=0){
					$scope.costBreakUp.year8Model=	 $scope.dummyCost[i].year_8;
				}
				if($scope.dummyCost[i].year_9!=null && $scope.dummyCost[i].year_9!=0){
					$scope.costBreakUp.year9Model=	 $scope.dummyCost[i].year_9;
				}
				if($scope.dummyCost[i].year_10!=null && $scope.dummyCost[i].year_10!=0){
					$scope.costBreakUp.year10Model=	 $scope.dummyCost[i].year_10;
				}
				if($scope.dummyCost[i].transistion!=null)
				{
					$scope.costBreakUp.TransitionModel=	 $scope.dummyCost[i].transistion;
					$scope.costDataBuild.TransitionModel=	 $scope.dummyCost[i].transistion;
				}
			  
			  	}
			  }

			  $scope.fixedDeal=$scope.costDetails[1];
			  if( $scope.fixedDeal[0].transitionMonth!=null && $scope.fixedDeal[0].transitionMonth!=0)
				  {
				  $scope.hideTransition=false; 		  
				  }
			  else
				  {
				  	$scope.hideTransition=true; 
				  }
			  
			  if($scope.yearsData[1]!=null){
				  $scope.totalHide=false;
			  }
			  else
				  {
				  $scope.totalHide=true;
				  }
			  }
			  
		  }
			WebServiceFactory.getCostData($sessionStorage.DealModel,rpVrsId).then(getCostData);
		  
		}]);