app.service('fpDealCostInputsService', function() {
	
	var _this= this
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
		})
	}
	
	
	this.createMonthHeader = function($scope,headerData){
		$scope.monthHeader = []
		
		var i=0
		 headerData.staffingFirstMonth ? i++ : i	
		 headerData.staffingSecondMonth ? i++ : i
		 headerData.staffingThirdMonth ? i++ : i
		 headerData.staffingFourthMonth ? i++ : i
		 headerData.staffingFifthMonth ? i++ : i
		 headerData.staffingSixthMonth ? i++ : i
		 headerData.staffingSeventhMonth ? i++ : i
		 headerData.staffingEighthMonth ? i++ : i
		 headerData.staffingNinthMonth ? i++ : i
		 headerData.staffingTenthMonth ? i++ : i
		 headerData.staffingEleventhMonth ? i++ : i
		 headerData.staffingTwelthMonth ? i++ : i

		
		$scope.monthHeader.push({
			"month":headerData.staffingFirstMonth ? headerData.staffingFirstMonth  : "",
			"number":i		
		},{
			"month":headerData.staffingSecondMonth ? headerData.staffingSecondMonth : ""  
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
		})
	}
})
