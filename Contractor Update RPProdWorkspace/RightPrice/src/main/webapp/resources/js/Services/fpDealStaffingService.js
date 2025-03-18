app.service('fpDealStaffingService', function() {
	
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
	
	this.calculateOnChangeStaffing = function($scope,data1,data2,data3,data4,data5,data6,data7,data8,data9,data10,data11,data12,object,index,rowname){
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
	
	
	if(data1=="onsiteContractor" || data1=="onsiteLocal" || data1=="onsiteDeputed" || data1=="onsiteShortTerm" )
		{
			if(rowname=='staffingFirstMonthCount')
	    	{	
				if(data1=="onsiteContractor")
					{
						$scope.getOnsiteGross(data5,data6,index,'staffingSecondMonthCount');
					}
				else
					{
						$scope.getOnsiteGross(data7,data8,index,'staffingSecondMonthCount');
					}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingThirdMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingThirdMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingFourthMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingFourthMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingFifthMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingFifthMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingSixthMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingSixthMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingSeventhMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingSeventhMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
	    		| rowname=='staffingSeventhMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingEighthMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingEighthMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
	    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingNinthMonthCount');
				}
			else
				{
				 $scope.getOnsiteGross(data7,data8,index,'staffingNinthMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
	    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingTenthMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingTenthMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
	    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
	    		|| rowname=='staffingTenthMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingEleventhMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingEleventhMonthCount');
				}
	    	}
			if(rowname=='staffingFirstMonthCount' || rowname=='staffingSecondMonthCount' ||rowname=='staffingThirdMonthCount'
	    		|| rowname=='staffingFourthMonthCount'||rowname=='staffingFifthMonthCount'||rowname=='staffingSixthMonthCount'
	    		|| rowname=='staffingSeventhMonthCount'|| rowname=='staffingEighthMonthCount' || rowname=='staffingNinthMonthCount'
	    		|| rowname=='staffingTenthMonthCount'|| rowname=='staffingEleventhMonthCount')
	    	{
				if(data1=="onsiteContractor")
				{
					$scope.getOnsiteGross(data5,data6,index,'staffingTwelthMonthCount');
				}
			else
				{
					$scope.getOnsiteGross(data7,data8,index,'staffingTwelthMonthCount');
				}
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
	
	if(data1=="onsiteLocal" || data1=="onsiteDeputed" || data1=="onsiteShortTerm" || data1=="offshoreTotal")
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
	
	
	if(data1=="onsiteLocal" || data1=="onsiteDeputed" || data1=="onsiteShortTerm" )
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

	

	if(rowname=='staffingFirstMonthCount' )
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
});