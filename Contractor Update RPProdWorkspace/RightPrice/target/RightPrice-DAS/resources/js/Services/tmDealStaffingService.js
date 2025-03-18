app.service('tmDealStaffingService', function() {

	var _this= this;
	this.createYearHeader = function($scope,headerData){
		$scope.yearHeader.push({
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader1  : ""
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader2  : ""
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader3  : ""
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader4  : ""
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader5  : ""
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader6  : ""
		},{				
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader7  : ""
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader8  : ""
				
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader9  : ""
				
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader10  : ""
		},{
			"year":headerData.monthYearHeader1 ? headerData.monthYearHeader11  : ""
		
				
		});
		
		
		angular.forEach($scope.yearHeader, function(key,value) {
			{
				if($scope.yearHeader[value].year)
				{
					$scope.yearHeaderlen = $scope.yearHeaderlen+1;
				}
			}			
			});
		
		
	}
	
	this.createMonthHeader = function($scope,headerData){
		$scope.monthHeader = [];
		$scope.monthHeader.push({
			"month":headerData.staffingFirstMonth ? headerData.staffingFirstMonth  : ""
		},{
			"month":headerData.staffingSecondMonth ? headerData.staffingSecondMonth  : ""
		},{
			"month":headerData.staffingThirdMonth ? headerData.staffingThirdMonth  : ""
		},{
			"month":headerData.staffingFourthMonth ? headerData.staffingFourthMonth  : ""
		},{
			"month":headerData.staffingFifthMonth ? headerData.staffingFifthMonth  : ""
		},{
			"month":headerData.staffingSixthMonth ? headerData.staffingSixthMonth  : ""
		},{
			"month":headerData.staffingSeventhMonth ? headerData.staffingSeventhMonth  : ""
		},{
			"month":headerData.staffingEighthMonth ? headerData.staffingEighthMonth  : ""
		},{
			"month":headerData.staffingNinthMonth ? headerData.staffingNinthMonth  : ""
		},{
			"month":headerData.staffingTenthMonth ? headerData.staffingTenthMonth  : ""
		},{
			"month":headerData.staffingEleventhMonth ? headerData.staffingEleventhMonth  : ""
		},{
			"month":headerData.staffingTwelthMonth ? headerData.staffingTwelthMonth  : ""
		});
	}
	
	this.setOnsiteDeputedData = function($scope){
		angular.forEach($scope.data, function (value, key) {
    		if(value.visaId == 2 && value.sumTotalType == 0 && value.isContractor == 0 )
    		{
    			$scope.fpDealStaffing.onsiteDeputedLabel = value.countryVisaLabelDescription;
    			$scope.onsiteDeputed.push(value);
    			
    		}
    		else if(value.visaId == 1 && value.sumTotalType == 0 && value.isContractor == 1)
    		{
    			$scope.onsiteContractor.push(value);
    			$scope.onsiteLocal_Contractor.push(value);
    			$scope.onsiteGross.push(value);
    			$scope.staffingGross.push(value);
    		}
    		else if(value.visaId == 1 && value.sumTotalType == 0  && value.isContractor == 0)
    		{
    			$scope.fpDealStaffing.onsiteLocalLabel = value.countryVisaLabelDescription;
    			$scope.onsiteLocal.push(value);
    			$scope.onsiteLocal_Contractor.push(value);
    			
    		}
    		else if(value.visaId == 3 && value.sumTotalType == 0)
    		{
    			$scope.fpDealStaffing.onsiteShortTermLabel = value.countryVisaLabelDescription;
    			$scope.onsiteShortTerm.push(value);
    			
    		}    		
    		else if(value.sumTotalType == 2 && value.isContractor == 0)
    		{
    			$scope.fpDealStaffing.onsiteTotalLabel = "Total Onsite"; //value.countryVisaLabelDescription; //
    			$scope.onsiteTotal.push(value);
    			$scope.onsiteGross.push(value);
    			$scope.staffingGross.push(value);
    		}
    		else if(value.visaId == 2 && value.sumTotalType == 1){
    			$scope.onsiteDeputedSumTotal.push(value);
    		}
    		else if(value.visaId == 1 && value.sumTotalType == 1)
    		{
    			$scope.onsiteLocal_ContrSumTotal.push(value);
    		}
    		/*else if(value.visaId == 1 && value.sumTotalType == 1)
    		{
    			$scope.onsiteLocalSumTotal.push(value);
    		}*/
    		else if(value.visaId == 3 && value.sumTotalType == 1){
    			$scope.onsiteShortTermSumTotal.push(value);
    		}
    		else if(value.sumTotalType == 3)
    		{
    			$scope.onsiteSumTotal.push(value);
    			$scope.onsiteGrossSumTotal.push(value);
    			
    			
	    				/*if($scope.onsiteSumTotal.length==1 )
	    				{
	    					$scope.totaOnsiteValuePer =$scope.onsiteSumTotal[1].yearlyTotal*100 ; 
	    				}
	    				if($scope.onsiteSumTotal.length==2 )
	    				{
	    					$scope.totaOnsiteValuePer =$scope.onsiteSumTotal[2].yearlyTotal*100 ; 
	    				}
	    				if($scope.onsiteSumTotal.length==3 )
	    				{
	    					$scope.totaOnsiteValuePer =$scope.onsiteSumTotal[3].yearlyTotal*100 ; 
	    				}
	    				if($scope.onsiteSumTotal.length==4 )
	    				{
	    					$scope.totaOnsiteValuePer =$scope.onsiteSumTotal[4].yearlyTotal*100 ; 
	    				}
	    				if($scope.onsiteSumTotal.length==5 )
	    				{
	    					$scope.totaOnsiteValuePer =$scope.onsiteSumTotal[5].yearlyTotal*100 ; 
	    				}*/
    				
    			
    		}
    		
    			
    		
    		/*else if(value.visaId == 1 && value.sumTotalType == 1 && value.isContractor == 1){	
    			$scope.onsiteSumTotalContractor.push(value);
    		}*/
    		/*else if(value.visaId == 0 && value.sumTotalType == 2){
    			$scope.onsiteTotal.push(value);
    		}*/
    		
    		//off shore 
    		else if(value.visaId == 4 && value.sumTotalType == 0 && value.isContractor == 1){
    			//$scope.fpDealStaffing.offshoreTotalLabel = value.countryVisaLabelDescription;
    			$scope.offshoreTotalContr.push(value);
    			$scope.offshore_Contractor.push(value);
    			//$scope.staffingGross.push(value);
    		}
    		else if(value.visaId == 4 && value.sumTotalType == 0 && value.isContractor == 0)
    		{
    			$scope.fpDealStaffing.offshoreTotalLabel = value.countryVisaLabelDescription;
    			$scope.offshoreTotal.push(value);
    			$scope.offshore_Contractor.push(value);
    			//$scope.staffingGross.push(value);
    			
    		}
    		else if(value.visaId == 4 && value.sumTotalType == 1){
    		//	$scope.offshoreSumTotal.push(value);
    			//$scope.offshoreSumTotalContr.push(value);
    			$scope.offshore_ContrSumTotal.push(value);
    			$scope.staffingGross.push(value);
    		}
    		//total staffing
    		/*else if(value.visaId == 4 && value.sumTotalType == 8){*/
    		
    		else if(value.sumTotalType == 5 && value.isContractor == 0){
    			$scope.totalStaffing.push(value);
    			//$scope.staffingGross.push(value);
    			//$scope.staffingGrossSumTotal.push(value);
    			
    		}
    		else if(value.sumTotalType == 5 && value.isContractor == 1){
    			$scope.totalStaffingContr.push(value);
    			//$scope.staffingGross.push(value);
    			//$scope.staffingGrossSumTotal.push(value);
    		}
    		//total staffing sub total
    		/*else if(value.visaId == 4 && value.sumTotalType == 9){*/
    		else if(value.sumTotalType == 6){
    			$scope.totalSumTotalStaffing.push(value);
    			//$scope.totalSumTotalStaffing1.push(value);
    			$scope.staffingGrossSumTotal.push(value);
    		}
    		else if(value.visaId == 0 && value.sumTotalType == 1){
    		}
    		
    	});
		
		
		/*angular.forEach($scope.onsiteLocal,function (value, key) {
			
			if(value.monthYearHeader == value.staffingDetailsHeader.monthYearHeader1)
				{
					$scope.onsiteLocalAllYears.push({
						"monthYearHeader" :value.monthYearHeader,
						"yearlyTotal" :value.yearlyTotal,
						"staffingFirstMonthCount" :value.yearlyTotal,
						"staffingSecondMonthCount" :null,
						"staffingThirdMonthCount" :null,
						"staffingFourthMonthCount" :null,
						"customerRole":value.customerRole,
						"masterRoleShortDescription":value.masterRoles.masterRoleShortDescription,
						"bandGrade":value.masterRoles.bandGrade
		
					});
				}
			if(value.monthYearHeader == value.staffingDetailsHeader.monthYearHeader2)
			{
				$scope.onsiteLocalAllYears.push({
					"monthYearHeader" :value.monthYearHeader,
					"yearlyTotal" :value.yearlyTotal,
					"staffingFirstMonthCount" :null,
					"staffingSecondMonthCount" :value.yearlyTotal,
					"staffingThirdMonthCount" :null,
					"staffingFourthMonthCount" :null,
					"customerRole":value.customerRole,
					"masterRoleShortDescription":value.masterRoles.masterRoleShortDescription,
					"bandGrade":value.masterRoles.bandGrade
	
				});
			}
			if(value.monthYearHeader == value.staffingDetailsHeader.monthYearHeader3)
			{
				$scope.onsiteLocalAllYears.push({
					"monthYearHeader" :value.monthYearHeader,
					"yearlyTotal" :value.yearlyTotal,
					"staffingFirstMonthCount" :null,
					"staffingSecondMonthCount" :null,
					"staffingThirdMonthCount" :value.yearlyTotal,	
					"staffingFourthMonthCount" :null,
					"customerRole":value.customerRole,
					"masterRoleShortDescription":value.masterRoles.masterRoleShortDescription,
					"bandGrade":value.masterRoles.bandGrade
	
				});
			}
			
			if(value.monthYearHeader == value.staffingDetailsHeader.monthYearHeader4)
			{
				$scope.onsiteLocalAllYears.push({
					"monthYearHeader" :value.monthYearHeader,
					"yearlyTotal" :value.yearlyTotal,
					"staffingFirstMonthCount" :null,
					"staffingSecondMonthCount" :null,
					"staffingThirdMonthCount" :null,
					"staffingFourthMonthCount" :value.yearlyTotal,								
					"customerRole":value.customerRole,
					"masterRoleShortDescription":value.masterRoles.masterRoleShortDescription,
					"bandGrade":value.masterRoles.bandGrade
	
				});
			}
				
				
	});*/
		
		
	}
	
	this.getRowCalculationObject = function($scope,objectName,index){
		if(objectName === "onsiteDeputed"){
			console.log($scope.onsiteDeputed[index]);			
			return $scope.onsiteDeputed[index];
		}else if(objectName === "onsiteLocal"){
			console.log($scope.onsiteLocal[index]);
			return $scope.onsiteLocal[index];
		}else if(objectName === "onsiteShortTerm")
		{
			console.log($scope.onsiteShortTerm[index]);			
			return $scope.onsiteShortTerm[index];
		}
		else if(objectName === "onsiteTotal"){
			console.log($scope.onsiteTotal[index]);
			return $scope.onsiteTotal[index];
		}
		else if(objectName === "onsiteContractor")
		{
			console.log($scope.onsiteContractor[index]);
			return $scope.onsiteContractor[index];
		}
		else if(objectName === "offshoreTotal"){
			console.log($scope.offshoreTotal[index]);
			return $scope.offshoreTotal[index];
		}
		else if(objectName === "offshoreTotalContr"){			
			return $scope.offshoreTotalContr[index];
		}
		else if(objectName === "totalStaffingContr"){
		
			return $scope.totalStaffingContr[index];
		}
		else if(objectName === "totalStaffing"){
			console.log($scope.totalStaffing[index]);
			return $scope.totalStaffing[index];
		}
	};
	
	this.calculateRowTotal = function (object){
		
		
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
		
		
		
		/*if(object.monthYearHeader=='Mar-18/Feb-19')
		{
		object.yearlyTotal1 = parseFloat(object.staffingFirstMonthCount ? object.staffingFirstMonthCount:0.0 ) 
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
		
		$scope.onsiteLocalAllYears.push({
			
			"yearlyTotal":yearlyTotal1

		});
		
		
			
		//$scope.onsiteLocal.push(yearlyTotal1);
		}
		if(object.monthYearHeader=='Mar-19/Feb-20')
		{
		object.yearlyTotal2 = parseFloat(object.staffingFirstMonthCount ? object.staffingFirstMonthCount:0.0 ) 
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
		//$scope.onsiteLocal.push(yearlyTotal2);
		$scope.onsiteLocalAllYears.push({
					
					"yearlyTotal":value.yearlyTotal2
		
				});
			
		}
		if(object.monthYearHeader=='Mar-20/Feb-21')
		{
		object.yearlyTotal3 = parseFloat(object.staffingFirstMonthCount ? object.staffingFirstMonthCount:0.0 ) 
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
		
		$scope.onsiteLocalAllYears.push({
			
			"yearlyTotal":yearlyTotal1

		});
		
		$scope.onsiteLocal.push(yearlyTotal3);
			
		}
		if(object.monthYearHeader=='Mar-21/Feb-22')
		{
		object.yearlyTotal4 = parseFloat(object.staffingFirstMonthCount ? object.staffingFirstMonthCount:0.0 ) 
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
		
		$scope.onsiteLocal.push(yearlyTotal4);
		
		}*/
		
		
		
	};
	
	this.getYearHeaderArray = function($scope,objectName){
		if(objectName === "onsiteDeputed"){
			return $scope.onsiteDeputed;
		}else if(objectName === "onsiteLocal"){
			return $scope.onsiteLocal;
		}else if(objectName === "onsiteShortTerm"){
			return $scope.onsiteShortTerm;
		}else if(objectName === "onsiteTotal"){
			return $scope.onsiteTotal;
		}
		else if(objectName === "onsiteLocal_Contractor"){
			return $scope.onsiteLocal_Contractor;
		}
		else if(objectName === "offshore_Contractor"){
			return $scope.offshore_Contractor;
		}
		else if(objectName === "onsiteGross"){
			return $scope.onsiteGross;
		}
		else if(objectName === "staffingGross"){
			return $scope.staffingGross;
		}
		else if(objectName === "onsiteContractor"){
			return $scope.onsiteContractor;
		}else if(objectName === "offshoreTotal"){
			return $scope.offshoreTotal;
		}
		else if(objectName === "offshoreTotalContr")
		{	
			return $scope.offshoreTotalContr;
		}
		else if(objectName === "totalStaffingContr"){
			return $scope.totalStaffingContr;
		}
		else if(objectName === "totalStaffing"){
			return $scope.totalStaffing;
		}
	};
	
	this.getSumTotalArray = function($scope,objectName){
		if(objectName === "onsiteDeputedSumTotal"){
			return $scope.onsiteDeputedSumTotal;
		}
		else if(objectName === "onsiteLocal_ContrSumTotal"){
			return $scope.onsiteLocal_ContrSumTotal;
		}
		else if(objectName === "offshore_ContrSumTotal"){
			return $scope.offshore_ContrSumTotal;
		}
		else if(objectName === "onsiteGrossSumTotal"){
			return $scope.onsiteGrossSumTotal;
		}
		else if(objectName === "staffingGrossSumTotal"){
			return $scope.staffingGrossSumTotal;
		}
		else if(objectName === "onsiteLocalSumTotal"){
			return $scope.onsiteLocalSumTotal;
		}else if(objectName === "onsiteShortTermSumTotal"){
			return $scope.onsiteShortTermSumTotal;
		}else if(objectName === "onsiteSumTotal"){
			return $scope.onsiteSumTotal;
		}
		else if(objectName === "onsiteSumTotalContractor"){
			return $scope.onsiteSumTotalContractor;
		}
		else if(objectName === "offshoreSumTotalContr"){
			return $scope.offshoreSumTotalContr;
		}
		else if(objectName === "offshoreSumTotal"){
			return $scope.offshoreSumTotal;
		}else if(objectName === "totalSumTotalStaffing"){
			return $scope.totalSumTotalStaffing;
		}
	};
	
	this.getSumTotalObject = function($scope,objectSumTotalName){
		var subTotalArray = this.getSumTotalArray($scope,objectSumTotalName);
		var sumTotalObj={};
		angular.forEach(subTotalArray, function (value, key) { 
			if(value.monthYearHeader == $scope.currentMonthYearHeader){
				sumTotalObj =  value;
    		};
		});
		return sumTotalObj;
	};
	
	this.resetSumTotalObjectValue = function(sumTotalObject,objectMonthCount){
		switch (objectMonthCount) {
	        case 'staffingFirstMonthCount':
	        	sumTotalObject.staffingFirstMonthCount = 0;
	            break;
	        case 'staffingSecondMonthCount':
	        	sumTotalObject.staffingSecondMonthCount = 0;
	            break;
	        case 'staffingThirdMonthCount':
            	sumTotalObject.staffingThirdMonthCount = 0;
                break;
            case 'staffingFourthMonthCount':
            	sumTotalObject.staffingFourthMonthCount = 0;
                break;
            case 'staffingFifthMonthCount':
            	sumTotalObject.staffingFifthMonthCount = 0;
                break;
            case 'staffingSixthMonthCount':
            	sumTotalObject.staffingSixthMonthCount = 0;
                break;
            case 'staffingSeventhMonthCount':
            	sumTotalObject.staffingSeventhMonthCount = 0;
                break;
            case 'staffingEighthMonthCount':
            	sumTotalObject.staffingEighthMonthCount = 0;
                break;
            case 'staffingNinthMonthCount':
            	sumTotalObject.staffingNinthMonthCount = 0;
                break;
            case 'staffingTenthMonthCount':
            	sumTotalObject.staffingTenthMonthCount = 0;
                break;
            case 'staffingEleventhMonthCount':
            	sumTotalObject.staffingEleventhMonthCount = 0;
                break;
            case 'staffingTwelthMonthCount':
            	sumTotalObject.staffingTwelthMonthCount = 0;
                break;
		}
		return sumTotalObject;
	};
	
	this.calculateSubTotal = function($scope,yearArray,objectMonthCount,sumTotalObject){
		angular.forEach(yearArray, function (value, key) {
    		if(value.monthYearHeader == $scope.currentMonthYearHeader){
	    		
    			switch (objectMonthCount) {
	                case 'staffingFirstMonthCount':
	                	sumTotalObject.staffingFirstMonthCount += parseFloat(value.staffingFirstMonthCount);
	                    break;
	                case 'staffingSecondMonthCount':
	                	sumTotalObject.staffingSecondMonthCount += parseFloat(value.staffingSecondMonthCount);
	                    break;
	                case 'staffingThirdMonthCount':
	                	sumTotalObject.staffingThirdMonthCount += parseFloat(value.staffingThirdMonthCount);
	                    break;
	                case 'staffingFourthMonthCount':
	                	sumTotalObject.staffingFourthMonthCount += parseFloat(value.staffingFourthMonthCount);
	                    break;
	                case 'staffingFifthMonthCount':
	                	sumTotalObject.staffingFifthMonthCount += parseFloat(value.staffingFifthMonthCount);
	                    break;
	                case 'staffingSixthMonthCount':
	                	sumTotalObject.staffingSixthMonthCount += parseFloat(value.staffingSixthMonthCount);
	                    break;
	                case 'staffingSeventhMonthCount':
	                	sumTotalObject.staffingSeventhMonthCount += parseFloat(value.staffingSeventhMonthCount);
	                    break;
	                case 'staffingEighthMonthCount':
	                	sumTotalObject.staffingEighthMonthCount += parseFloat(value.staffingEighthMonthCount);
	                    break;
	                case 'staffingNinthMonthCount':
	                	sumTotalObject.staffingNinthMonthCount += parseFloat(value.staffingNinthMonthCount);
	                    break;
	                case 'staffingTenthMonthCount':
	                	sumTotalObject.staffingTenthMonthCount += parseFloat(value.staffingTenthMonthCount);
	                    break;
	                case 'staffingEleventhMonthCount':
	                	sumTotalObject.staffingEleventhMonthCount += parseFloat(value.staffingEleventhMonthCount);
	                    break;
	                case 'staffingTwelthMonthCount':
	                	sumTotalObject.staffingTwelthMonthCount += parseFloat(value.staffingTwelthMonthCount);
	                    break;
	    		}
    			
    			//calculate sub total row total
    			_this.calculateRowTotal(sumTotalObject);
    		};
    	});
	};
});