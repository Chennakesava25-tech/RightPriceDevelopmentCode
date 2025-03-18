<script>
$(document).ready(function () {
	   //var $a = $('#tblStaffing a');
	 $('#tblStaffing').on('click', 'a', function() {
		 $('#tblStaffing a').removeClass('active');
	     $(this).addClass('active'); 
	 }); 
}); 
</script>      
             <div class="row">
         	 	<div class="col-sm-12">
               		<div class="panel-group">
                    	<div class="panel panel-info ">
                        	<div class="panel-heading panelHeadingStyle" ng-click="ShowHideDealStaffingDetails()">
                            	<div class="row ">
									<label class="control-label col-sm-10 ">Staffing Details</label>
									<div class="col-sm-2 textAlignRight">
										<label class="DownArrowColor textAlignRight"> &#9660;</label>
									</div>
								</div>
                         	</div>
                           <!-- 	<div class="panel-body" ng-hide = "DealStaffingDetailsHidden"> -->
                           	<div class="panel-body" >
                           		<div class="divEmptyThrice"></div>
                           		<div class="row marginBottom5px"  >
									<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                       <div class="col-sm-3">
                                         <!--  <select id="ddlDealStaffingDetailsCountry" class="form-control" placeholder="Please select" name="ddlDealStaffingDetailsCountry"
                                          		ng-model="frmStaffing.dealStaffingDetailsCountryModel" 
                                          		
                                          		ng-init=" frmStaffing.dealStaffingDetailsCountryModel = chkBoxSelectedCountryList[0]"  
                                          		ng-options="cn as cn.countryName for cn in chkBoxSelectedCountryList | limitTo: 1"
                                          		required>
                                          		ng-blur="getCities(frmStaffing.dealStaffingDetailsCountryModel.countryId)"
                                          		 <option value="cn" ng-if="false">
											<option value="-1" selected disabled>Please select</option>
											 ng-init=" frmStaffing.dealStaffingDetailsCountryModel = chkBoxSelectedCountryList[0]"
										</select> -->
										
										<select id="ddlDealStaffingDetailsCountry" class="form-control" placeholder="Please select" name="ddlDealStaffingDetailsCountry"
                                          		ng-model="frmStaffing.dealStaffingDetailsCountryModel" 
                                          		
                                          		ng-init=" frmStaffing.dealStaffingDetailsCountryModel = cityList[0]"  
                                          		ng-options="ci as ci.countryName for ci in cityList"
                                          		required>
                                          	<!-- 	ng-blur="getCities(frmStaffing.dealStaffingDetailsCountryModel.countryId)" -->
                                          	<!-- 	 <option value="cn" ng-if="false"> -->
											<!-- <option value="-1" selected disabled>Please select</option> -->
											 <!-- ng-init=" frmStaffing.dealStaffingDetailsCountryModel = chkBoxSelectedCountryList[0]" -->
										</select>
                                       </div>
                                       <label class="control-label col-sm-3 textAlignRight required-Field">City</label>
                                       <div class="col-sm-3">
                                          <select id="ddlDealStaffingDetailsCity" class="form-control" placeholder="Please select" name="ddlDealStaffingDetailsCity"
                                          		ng-model="frmStaffing.dealStaffingDetailsCityModel" 
                                          		ng-options="ci as ci.cityName for ci in cityList"
                                          		ng-init=" frmStaffing.dealStaffingDetailsCityModel = cityList[0]"  
                                          		required>
											<!-- <option value="" selected disabled>Please select</option> -->
											<!-- ng-options="ci as ci.cityName for ci in cityArray" -->
										</select>
                                       </div>
								</div>
								<div class="row marginBottom5px" style="visibility:hidden">
									<label class="control-label col-sm-2 textAlignRight required-Field">Tower</label>
                                       <div class="col-sm-3">
                                          <select id="ddlDealStaffingDetailsTower" class="form-control" placeholder="Please select" name="ddlDealStaffingDetailsTower"
                                          		ng-model="frmStaffing.dealStaffingDetailsTowerModel"
                                          		 ng-init="frmStaffing.dealStaffingDetailsTowerModel=tower[0]" 
                                          		ng-options="ci as ci.towerName for ci in tower | limitTo: 1"
                                          		
                                          		required>
											<!-- <option value="" selected disabled>Please select</option> -->
										</select>
                                	</div>
								</div>
							
								<div class="row text-center">
									<div class="col-sm-12">
										<button type="button" class="btn btn-primary btnSpace"  id="btnSearch" ng-click="getTMDealStaffingData(frmStaffing)" ng-disabled="disabledflag1">Search</button>		
									</div>
								</div>
								<div class="divEmptyThrice"></div>
						  		 <div class="row">
						  		 <p style="color: red">
									<b>
									Please update staffing for Local/Deputed(Landed), Short term(Business visa) & Offshore locations
									</b>
								</p>
									<div class="col-sm-12">
										<div class="table-responsive">					                        
										<table id="tblStaffing" class="table tblDealCre table-borderless table-condensed">
					                             <thead>
					                              	<tr class="">
					                          			<th  class="thWidth8Per thBorder" >Master Skill</th>
					                                    <th class="thWidth8Per thBorder"  >Customer Role</th>
					                                    <th class="thWidth8Per thBorder"> Syntel Band Grade</th>
					                                    <th class="thWidth8Per thBorder">GCM Level</th>
					                                     <th class="thWidth8Per thBorder">Billing Rate</th>					                                    
					                                    <!-- <th class="padLeft colRightSection" colspan="{{colspan}}"><a class="btn btn-default ancBtnWidthSum" href="#" tabindex="1">Summary</a></th>
					                                    <th class="colRightSection" colspan="2" ng-repeat="year in yearHeader"><a class="btn btn-default" href="#" tabindex="{{tabArray[$index]}}" ng-click="setYearHeader(year)" ng-show="year.year">{{year.year | limitTo:(year.year.length - (year.year.indexOf(","))-1)}}</a></th> -->
					                                    <th class="colRightSection" colspan="16">
				                                    		<!-- <a class="btn btn-default ancBtnWidthSum" href="#"></a> -->
				                                    		<!-- <a class="btn btn-default ancBtnWidthSum" href="#">Summary1</a>
				                                    		<a class="btn btn-default ancBtnWidthSum" href="#">Summary2</a>
				                                    		<a class="btn btn-default ancBtnWidthSum" href="#">Summary3</a> -->
				                                    		
					                                    	<a ng-repeat="year in yearHeader" class="btn btn-default" href="#"  style="margin-right:5px;" ng-click="setYearHeader(year)" 
					                                    		ng-show="year.year">{{year.year | limitTo:(year.year.length - (year.year.indexOf(","))-1)}}</a>	 
				                                    	</th>
					                              	</tr>	                              
					                              	<!--  <tr ng-if="monthHeader.length > 0 &&  summaryFlag.length==0">
					                          			<td colspan="5"></td>	                                    
					                                    <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td>
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr> -->
				                                    <tr ng-if="yearHeaderlen == 2 &&  summaryFlag.length==1">
					                          			<td colspan="5"></td>                                    
					                                 
					                                   <td class="thWidth4Per">YR-1</td>	
					                                   <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td><td></td> <td></td>					                                  
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="yearHeaderlen == 3 &&  summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                   <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td><td></td> 					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                       <tr ng-if="yearHeaderlen == 4 &&  summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>	
					                                     <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="yearHeaderlen == 5 &&  summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>
					                                      <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>								                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="yearHeaderlen == 6 &&  summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                         <td></td><td></td> <td></td><td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                     <tr ng-if="yearHeaderlen  == 7 && summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                        <td class="thWidth4Per">YR-6</td>
					                                         <td></td> <td></td><td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                     <tr ng-if="yearHeaderlen  == 8 && summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                       <td  class="thWidth4Per">YR-6</td>	
					                                       <td  class="thWidth4Per">YR-7</td>	
					                                         <td></td><td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                     <tr ng-if="yearHeaderlen  == 9 && summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                        <td  class="thWidth4Per">YR-6</td>	
					                                        <td  class="thWidth4Per">YR-7</td>
					                                         <td  class="thWidth4Per">YR-8</td>
					                                        <td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                     <tr ng-if="yearHeaderlen  == 10 && summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>
					                                       <td  class="thWidth4Per">YR-6</td>	
					                                        <td  class="thWidth4Per">YR-7</td>
					                                         <td  class="thWidth4Per">YR-8</td>	
					                                          <td  class="thWidth4Per">YR-9</td>	
					                                         <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                    <tr ng-if="yearHeaderlen  == 11 && summaryFlag.length==1">
					                          			<td colspan="5"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                       <td  class="thWidth4Per">YR-6</td>	
					                                        <td  class="thWidth4Per">YR-7</td>
					                                         <td  class="thWidth4Per">YR-8</td>	
					                                          <td  class="thWidth4Per">YR-9</td>
					                                           <td  class="thWidth4Per">YR-10</td>
					                                        <td></td>		  	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                       			</thead>
					                              <tbody> 
					                              <tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='onsiteContractor.length > 0'>
								                                    <td colspan="5" style="color:#ffffff"><strong>Onsite - Local(Contractor)</strong></td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
								                                    <td class="thWidth10Per" style="color:#ffffff">Total</td>
							                                 </tr>	
					                              	
					                              	<tr class="sectionHeading" ng-if="summaryFlag.length==1" ng-show='onsiteContractor.length > 0'>
					                          			<td colspan="18"><strong>Onsite - Local(Contractor)</strong></td>	                                    
					                              	</tr>
					                              </tbody>
					                           <!--   <tbody id="countTableRow1">
					                              	<tr ng-repeat ="data  in onsiteLocal"  ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == true" >
					                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEleventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTwelthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                           		</tbody> -->
					                           <tbody>
					                              	<!-- <tr  ng-repeat ="data  in onsiteContractor" ng-if="currentMonthYearHeader == data.monthYearHeader  && data.isContractor == 1 && summaryFlag.length ==0 ">
					                          			<td  class="thWidth8Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"  ><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFirstMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');  calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFirstMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFirstMonthCount',data.staffingFirstMonthCount); calculateContractorTotalStaffing(data.masterRoleId,'staffingFirstMonthCount',data.staffingFirstMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount'); 
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingFirstMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSecondMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSecondMonthCount',data.staffingSecondMonthCount);calculateContractorTotalStaffing(data.masterRoleId,'staffingSecondMonthCount',data.staffingSecondMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount'); 
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingSecondMonthCount,onsiteContractor,$index,'staffingSecondMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingThirdMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingThirdMonthCount',data.staffingThirdMonthCount);  calculateContractorTotalStaffing(data.masterRoleId,'staffingThirdMonthCount',data.staffingThirdMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingThirdMonthCount,onsiteContractor,$index,'staffingThirdMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFourthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFourthMonthCount', data.staffingFourthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingFourthMonthCount', data.staffingFourthMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFourthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingFourthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFifthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFifthMonthCount', data.staffingFifthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingFifthMonthCount', data.staffingFifthMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFifthMonthCount','totalSumTotalStaffing');'staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingFifthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSixthMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSixthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSixthMonthCount', data.staffingSixthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingSixthMonthCount', data.staffingSixthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSixthMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount'); 
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSeventhMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSeventhMonthCount', data.staffingSeventhMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingSeventhMonthCount', data.staffingSeventhMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingEighthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingEighthMonthCount', data.staffingEighthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingEighthMonthCount', data.staffingEighthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingEighthMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingNinthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingNinthMonthCount', data.staffingNinthMonthCount ) ;calculateContractorTotalStaffing(data.masterRoleId,'staffingNinthMonthCount', data.staffingNinthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingNinthMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingTenthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingTenthMonthCount', data.staffingTenthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingTenthMonthCount', data.staffingTenthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTenthMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount'); 
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEleventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEleventhMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingEleventhMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingEleventhMonthCount', data.staffingEleventhMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingEleventhMonthCount', data.staffingEleventhMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEleventhMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingEleventhMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTwelthMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTwelthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingTwelthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingTwelthMonthCount', data.staffingTwelthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingTwelthMonthCount', data.staffingTwelthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount'); 
					                                    getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingTwelthMonthCount');"></td>
					                              		<td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	
					                              	<tr ng-repeat="data  in onsiteContractor"
														ng-if="currentMonthYearHeader == data.monthYearHeader  && data.isContractor == 1 && summaryFlag.length ==0" >
														<td class="thWidth22Per colSection"><input type="text"
															class="form-control"
															ng-model="data.masterRoles.masterRoleShortDescription"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															class="form-control" ng-model="data.customerRole" disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															class="form-control" ng-model="data.masterRoles.bandGrade"
															disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															class="form-control" ng-model="data.masterRoles.gcmCODE"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															class="form-control" ng-model="data.stfaffingBilling_Rate"
															disabled></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFirstMonthCount != null"
															ng-model="data.staffingFirstMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingFirstMonthCount,'staffingFirstMonthCount');calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFirstMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');  calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFirstMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFirstMonthCount',data.staffingFirstMonthCount); calculateContractorTotalStaffing(data.masterRoleId,'staffingFirstMonthCount',data.staffingFirstMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount'); 
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFirstMonthCount,onsiteContractor,$index,'staffingFirstMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSecondMonthCount != null"
															ng-model="data.staffingSecondMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingSecondMonthCount,'staffingSecondMonthCount');calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSecondMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSecondMonthCount',data.staffingSecondMonthCount);calculateContractorTotalStaffing(data.masterRoleId,'staffingSecondMonthCount',data.staffingSecondMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount'); 
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingSecondMonthCount,onsiteContractor,$index,'staffingSecondMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingThirdMonthCount != null"
															ng-model="data.staffingThirdMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingThirdMonthCount,'staffingThirdMonthCount');calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingThirdMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingThirdMonthCount',data.staffingThirdMonthCount);  calculateContractorTotalStaffing(data.masterRoleId,'staffingThirdMonthCount',data.staffingThirdMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingThirdMonthCount,onsiteContractor,$index,'staffingThirdMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFourthMonthCount != null"
															ng-model="data.staffingFourthMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingFourthMonthCount,'staffingFourthMonthCount');calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFourthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFourthMonthCount', data.staffingFourthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingFourthMonthCount', data.staffingFourthMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFourthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFourthMonthCount,onsiteContractor,$index,'staffingFourthMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFifthMonthCount != null"
															ng-model="data.staffingFifthMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingFifthMonthCount,'staffingFifthMonthCount');calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFifthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFifthMonthCount', data.staffingFifthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingFifthMonthCount', data.staffingFifthMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFifthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingFifthMonthCount,onsiteContractor,$index,'staffingFifthMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSixthMonthCount != null"
															ng-model="data.staffingSixthMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingSixthMonthCount,'staffingSixthMonthCount');calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSixthMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSixthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSixthMonthCount', data.staffingSixthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingSixthMonthCount', data.staffingSixthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSixthMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingSixthMonthCount,onsiteContractor,$index,'staffingSixthMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSeventhMonthCount != null"
															ng-model="data.staffingSeventhMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingSeventhMonthCount,'staffingSeventhMonthCount');calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSeventhMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSeventhMonthCount', data.staffingSeventhMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingSeventhMonthCount', data.staffingSeventhMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingSeventhMonthCount,onsiteContractor,$index,'staffingSeventhMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEighthMonthCount != null"
															ng-model="data.staffingEighthMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingEighthMonthCount,'staffingEighthMonthCount');calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingEighthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingEighthMonthCount', data.staffingEighthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingEighthMonthCount', data.staffingEighthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingEighthMonthCount,onsiteContractor,$index,'staffingEighthMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingNinthMonthCount != null"
															ng-model="data.staffingNinthMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingNinthMonthCount,'staffingNinthMonthCount'); calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingNinthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingNinthMonthCount', data.staffingNinthMonthCount ) ;calculateContractorTotalStaffing(data.masterRoleId,'staffingNinthMonthCount', data.staffingNinthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingNinthMonthCount,onsiteContractor,$index,'staffingNinthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTenthMonthCount != null"
															ng-model="data.staffingTenthMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingTenthMonthCount,'staffingTenthMonthCount'); calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingTenthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingTenthMonthCount', data.staffingTenthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingTenthMonthCount', data.staffingTenthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTenthMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingTenthMonthCount,onsiteContractor,$index,'staffingTenthMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEleventhMonthCount != null"
															ng-model="data.staffingEleventhMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingEleventhMonthCount,'staffingEleventhMonthCount');calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEleventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEleventhMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingEleventhMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingEleventhMonthCount', data.staffingEleventhMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingEleventhMonthCount', data.staffingEleventhMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEleventhMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingEleventhMonthCount,onsiteContractor,$index,'staffingEleventhMonthCount'); "></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTwelthMonthCount != null"
															ng-model="data.staffingTwelthMonthCount"
															ng-blur="setZero(onsiteContractor,$index,data.staffingTwelthMonthCount,'staffingTwelthMonthCount');calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTwelthMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTwelthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingTwelthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingTwelthMonthCount', data.staffingTwelthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingTwelthMonthCount', data.staffingTwelthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');
															getOtherData('onsiteContractor','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteSumTotalContractor','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',data.masterRoleId,data.staffingTwelthMonthCount,onsiteContractor,$index,'staffingTwelthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control" ng-model="data.yearlyTotal"
															disabled></td>
													</tr>
					                              	<tr  ng-repeat ="data  in onsiteContractor" ng-if="currentMonthYearHeader == data.monthYearHeader  && data.isContractor == 1 && summaryFlag.length ==1 ">
					                          			<td  class="thWidth8Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"  ><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFirstMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');  calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFirstMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFirstMonthCount',data.staffingFirstMonthCount); calculateContractorTotalStaffing(data.masterRoleId,'staffingFirstMonthCount',data.staffingFirstMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSecondMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSecondMonthCount',data.staffingSecondMonthCount);calculateContractorTotalStaffing(data.masterRoleId,'staffingSecondMonthCount',data.staffingSecondMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingThirdMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingThirdMonthCount',data.staffingThirdMonthCount);  calculateContractorTotalStaffing(data.masterRoleId,'staffingThirdMonthCount',data.staffingThirdMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFourthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFourthMonthCount', data.staffingFourthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingFourthMonthCount', data.staffingFourthMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFourthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingFifthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingFifthMonthCount', data.staffingFifthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingFifthMonthCount', data.staffingFifthMonthCount );  calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFifthMonthCount','totalSumTotalStaffing');'staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSixthMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSixthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSixthMonthCount', data.staffingSixthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingSixthMonthCount', data.staffingSixthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSixthMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingSeventhMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingSeventhMonthCount', data.staffingSeventhMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingSeventhMonthCount', data.staffingSeventhMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingEighthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingEighthMonthCount', data.staffingEighthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingEighthMonthCount', data.staffingEighthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingNinthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingNinthMonthCount', data.staffingNinthMonthCount ) ;calculateContractorTotalStaffing(data.masterRoleId,'staffingNinthMonthCount', data.staffingNinthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('onsiteContractor',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateSubTotal('onsiteContractor','onsiteSumTotalContractor','staffingTenthMonthCount');calculateContractorTotalOnsite(data.masterRoleId,'staffingTenthMonthCount', data.staffingTenthMonthCount );calculateContractorTotalStaffing(data.masterRoleId,'staffingTenthMonthCount', data.staffingTenthMonthCount ); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTenthMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per" ></td>
														<td class="colRightSection thWidth4Per" ></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                         <!--   <tbody>
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteSumTotalContractor" ng-if="currentMonthYearHeader == data.monthYearHeader" >
					                          			<td colspan="5"><strong>1 .SUB TOTAL</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody> -->
				                              	<tbody>
				                              		<tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='onsiteLocal.length > 0'>
								                                   <td colspan="5" style="color:#ffffff" ><strong>{{fpDealStaffing.onsiteLocalLabel}}</strong></td><td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
								                                  <td class="thWidth10Per" style="color:#ffffff">Total</td>
							                                 </tr>	
					                              	<tr class="sectionHeading" ng-if="summaryFlag.length==1">
					                          			<td colspan="18"><strong>{{fpDealStaffing.onsiteLocalLabel}}</strong></td>	                                    
					                              	</tr>
								                          
					                          	</tbody>	
					                          	
					                          
				                                                       	
				                           		<!-- <tbody id="countTableRow2"> -->
				                           		<!-- <tbody>
					                              	<tr ng-repeat ="data  in onsiteLocal" ng-if="currentMonthYearHeader == data.monthYearHeader " >
					                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEleventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTwelthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                           		</tbody> -->
				                           		<tbody>
					                              	<!-- <tr ng-repeat ="data  in onsiteLocal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0 && summaryFlag.length ==0">
					                          			<td class="thWidth8Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection" ><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFirstMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');   getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');  "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSecondMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingThirdMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount');   getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFourthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFifthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSixthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSeventhMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEighthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingNinthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTenthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEleventhMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEleventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEleventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTwelthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTwelthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTwelthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.yearlyTotal != null" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	
					                              	<tr  ng-repeat ="data  in onsiteLocal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0 && summaryFlag.length ==0">
					                          			<td id="{{'ddlDescription'+'_'+($index+1)}}" class="thWidth22Per colSection" ><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td id="{{'ddlCustomerRole'+'_'+($index+1)}}" class="thWidth8Per colSection"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td id="{{'ddlBandGrade'+'_'+($index+1)}}" class="thWidth15Per colSection"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td id="{{'ddlBillingRate'+'_'+($index+1)}}" class="thWidth8Per colSection"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td id="{{'ddlstaffingFirstMonthCount'+'_'+($index+1)}}"  class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount"   ng-blur="setZero(onsiteLocal,$index,data.staffingFirstMonthCount,'staffingFirstMonthCount'); calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFirstMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');   getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingFirstMonthCount'); "></td>
					                                    <td id="{{'ddlstaffingSecondMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="setZero(onsiteLocal,$index,data.staffingSecondMonthCount,'staffingSecondMonthCount');calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSecondMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingSecondMonthCount');"></td>
					                                    <td id="{{'ddlstaffingThirdMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount"   ng-blur="setZero(onsiteLocal,$index,data.staffingThirdMonthCount,'staffingThirdMonthCount'); calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingThirdMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount');   getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingThirdMonthCount');"></td>
					                                    <td id="{{'ddlstaffingFourthMonthCount'+'_'+($index+1)}}"  class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="setZero(onsiteLocal,$index,data.staffingFourthMonthCount,'staffingFourthMonthCount');calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFourthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingFourthMonthCount');"></td>
					                                    <td id="{{'ddlstaffingFifthMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount"   ng-blur="setZero(onsiteLocal,$index,data.staffingFifthMonthCount,'staffingFifthMonthCount'); calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFifthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingFifthMonthCount');"></td>
					                                    <td id="{{'ddlstaffingSixthMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount"   ng-blur="setZero(onsiteLocal,$index,data.staffingSixthMonthCount,'staffingSixthMonthCount');calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSixthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingSixthMonthCount');"></td>
					                                    <td id="{{'ddlstaffingSeventhMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="setZero(onsiteLocal,$index,data.staffingSeventhMonthCount,'staffingSeventhMonthCount');calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSeventhMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingSeventhMonthCount');"></td>
					                                    <td id="{{'ddlstaffingEightMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="setZero(onsiteLocal,$index,data.staffingEighthMonthCount,'staffingEighthMonthCount');calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEighthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingEighthMonthCount');"></td>
					                                    <td id="{{'ddlstaffingNineMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount"   ng-blur="setZero(onsiteLocal,$index,data.staffingNinthMonthCount,'staffingNinthMonthCount'); calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingNinthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingNinthMonthCount');"></td>
					                                    <td id="{{'ddlstaffingTenthMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount"   ng-blur="setZero(onsiteLocal,$index,data.staffingTenthMonthCount,'staffingTenthMonthCount'); calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTenthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingTenthMonthCount');"></td>
					                                    <td id="{{'ddlstaffingElevenMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="setZero(onsiteLocal,$index,data.staffingEleventhMonthCount,'staffingEleventhMonthCount');calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEleventhMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEleventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEleventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingEleventhMonthCount');"></td>
					                                    <td id="{{'ddlstaffingTwelthMonthCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input ng-disabled="domesticDisabled" type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="setZero(onsiteLocal,$index,data.staffingTwelthMonthCount,'staffingTwelthMonthCount'); calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTwelthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTwelthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTwelthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount'); getOtherData('onsiteLocal','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteLocalSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteLocal,$index,'staffingTwelthMonthCount');"></td>
					                                    <td id="{{'ddlstaffingTotalCount'+'_'+($index+1)}}" class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                           		
					                              	<tr ng-repeat ="data  in onsiteLocal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0 && summaryFlag.length==1">
					                          			<td class="thWidth8Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.gcmCODE"	disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null"  ng-model="data.staffingFirstMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFirstMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');   getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');  " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSecondMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingThirdMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount');   getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFourthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount');getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingFifthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSixthMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingSeventhMonthCount');  calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteLocal',$index);  getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingEighthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingNinthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount"   ng-blur="calculateRowTotal('onsiteLocal',$index); getLocal_Contr('onsiteLocal_Contractor','onsiteLocal_ContrSumTotal',$index,'staffingTenthMonthCount'); calculateSubTotal('onsiteLocal','onsiteLocalSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"></td>
					                                    <td class="colRightSection thWidth4Per"></td>
					                                    <td class="colRightSection thWidth4Per">
					                                    <input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                           		</tbody>
				                              	<!-- <tbody>
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteLocalSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == false" >
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteLocalSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader " >
					                          			<td colspan="5"><strong>2. SUB TOTAL</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody> -->
				                              	<tbody>
					                              	<!-- <tr class="rowSubTotal" ng-repeat="data  in onsiteLocalSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == false" > -->
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteLocal_ContrSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==0" >
					                          			<td colspan="5"><strong>1. SUB TOTAL(Local/Deputed (Landed))</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteLocal_ContrSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==1" >
					                          			<td colspan="5"><strong>1. SUB TOTAL(Local/Deputed (Landed))</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                 	</td><td class="thWidth4Per"></td><td class="thWidth4Per"></td>
					                                     <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                              	<!-- <tbody>
				                              	<tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='onsiteDeputed.length > 0' >
								                                    <td colspan="5" style="color:#ffffff"><strong>{{fpDealStaffing.onsiteDeputedLabel}}</strong></td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
								                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
							                                 </tr>	
					                              	<tr class="sectionHeading" ng-if="summaryFlag.length==1">
					                              	
					                          			<td colspan="18"><strong>{{fpDealStaffing.onsiteDeputedLabel}}</strong></td>	                                    
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
				                              		
					                              	<tr ng-repeat ="data  in onsiteDeputed" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==0" >
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled" type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingFirstMonthCount,'staffingFirstMonthCount'); calculateRowTotal('onsiteDeputed',$index);  calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');   calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingFirstMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingSecondMonthCount,'staffingSecondMonthCount');calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount'); getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingSecondMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingThirdMonthCount,'staffingThirdMonthCount'); calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingThirdMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingFourthMonthCount,'staffingFourthMonthCount');calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingFourthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingFifthMonthCount,'staffingFifthMonthCount'); calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingFifthMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingSixthMonthCount,'staffingSixthMonthCount');calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingSeventhMonthCount,'staffingSeventhMonthCount');calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingSeventhMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingEighthMonthCount,'staffingEighthMonthCount');calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingNinthMonthCount,'staffingNinthMonthCount'); calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingTenthMonthCount,'staffingTenthMonthCount');calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingEleventhMonthCount,'staffingEleventhMonthCount');calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingEleventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEleventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEleventhMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingEleventhMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input ng-disabled="deputedDisabled"  type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="setZero(onsiteDeputed,$index,data.staffingTwelthMonthCount,'staffingTwelthMonthCount'); calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingTwelthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTwelthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTwelthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount'); getOtherData('onsiteDeputed','onsiteLocal_Contractor','onsiteLocal_ContrSumTotal','onsiteDeputedSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteDeputed,$index,'staffingTwelthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	<tr ng-repeat ="data  in onsiteDeputed" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==1 " >
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE"	disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);  calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');   calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('onsiteDeputed',$index);calculateSubTotal('onsiteDeputed','onsiteDeputedSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount'); calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"></td> <td class="colRightSection thWidth4Per"></td> 
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                           		</tbody>
				                              	<tbody>	                             
				                              		<tr class="rowSubTotal" ng-repeat="data  in onsiteDeputedSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==0 " ng-show='onsiteDeputed.length > 0'>	                          			
					                                    <td colspan="5"><strong>2. SUB TOTAL(Long term H1)</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteDeputedSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==1 " >	                          			
					                                    <td colspan="5"><strong>2. SUB TOTAL(Long term H1)</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                   <td class="thWidth4Per"></td><td class="thWidth4Per"></td>
					                                   <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                               -->	
				                               <tbody>
				                              	<tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='onsiteShortTerm.length > 0' >
								                                    <td colspan="5" style="color:#ffffff"> <strong>{{fpDealStaffing.onsiteShortTermLabel}}</strong> </td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
								                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
							                                 </tr>	
					                              	<tr class="sectionHeading" ng-if="summaryFlag.length==1" >
					                          			<td colspan="18"><span class="text-white"><strong>{{fpDealStaffing.onsiteShortTermLabel}}</strong></span></td>	                                    
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
					                              	<!-- <tr ng-repeat ="data  in onsiteShortTerm" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==0" >
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index); calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount'); "></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingEleventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEleventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingTwelthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTwelthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	<tr ng-repeat="data  in onsiteShortTerm"
														ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==0">
														<td class="thWidth22Per colSection"><input type="text"
															class="form-control"
															ng-disabled="shortTermDisabled" 
															ng-model="data.masterRoles.masterRoleShortDescription"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															ng-disabled="shortTermDisabled" 
															class="form-control" ng-model="data.customerRole" disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															ng-disabled="shortTermDisabled" 
															class="form-control" ng-model="data.masterRoles.bandGrade"
															disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															class="form-control" ng-model="data.masterRoles.gcmCODE"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															class="form-control" ng-model="data.stfaffingBilling_Rate"
															ng-disabled="shortTermDisabled" 
															disabled></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFirstMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingFirstMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingFirstMonthCount,'staffingFirstMonthCount'); calculateRowTotal('onsiteShortTerm',$index); calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingFirstMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSecondMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingSecondMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingSecondMonthCount,'staffingSecondMonthCount');calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingSecondMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingThirdMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingThirdMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingThirdMonthCount,'staffingThirdMonthCount'); calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingThirdMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFourthMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingFourthMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingFourthMonthCount,'staffingFourthMonthCount');calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingFourthMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFifthMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingFifthMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingFifthMonthCount,'staffingFifthMonthCount'); calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingFifthMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSixthMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingSixthMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingSixthMonthCount,'staffingSixthMonthCount'); calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingSixthMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSeventhMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingSeventhMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingSeventhMonthCount,'staffingSeventhMonthCount');calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingSeventhMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEighthMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingEighthMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingEighthMonthCount,'staffingEighthMonthCount');calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingEighthMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingNinthMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingNinthMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingNinthMonthCount,'staffingNinthMonthCount');calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingNinthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTenthMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingTenthMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingTenthMonthCount,'staffingTenthMonthCount'); calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingTenthMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEleventhMonthCount != null"
															ng-model="data.staffingEleventhMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingEleventhMonthCount,'staffingEleventhMonthCount');calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingEleventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEleventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingEleventhMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTwelthMonthCount != null"
															ng-disabled="shortTermDisabled" 
															ng-model="data.staffingTwelthMonthCount"
															ng-blur="setZero(onsiteShortTerm,$index,data.staffingTwelthMonthCount,'staffingTwelthMonthCount');calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingTwelthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTwelthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');
															getOtherData('onsiteShortTerm','onsiteShortTermSumTotal','onsiteLocal_ContrSumTotal','onsiteShortTermSumTotal','onsiteTotal','onsiteSumTotal','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',onsiteShortTerm,$index,'staffingTwelthMonthCount');" ></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control" ng-model="data.yearlyTotal"
															disabled></td>
													</tr>
					                              	
					                              	<tr ng-repeat ="data  in onsiteShortTerm" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==1 " >
					                          			<td class="thWidth22Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.gcmCODE"	disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index); calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFirstMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFirstMonthCount');  getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFirstMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing');  getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount'); " disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSecondMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSecondMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingThirdMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingThirdMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFourthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFourthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingFifthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingFifthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');" disabled></td>
					                                   	<td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSixthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSixthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingSeventhMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingSeventhMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingEighthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingEighthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingNinthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingNinthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('onsiteShortTerm',$index);calculateSubTotal('onsiteShortTerm','onsiteShortTermSumTotal','staffingTenthMonthCount');getTotalOnsite('onsiteTotal','onsiteSumTotal',$index,'staffingTenthMonthCount'); getOnsiteGross('onsiteGross','onsiteGrossSumTotal',$index,'staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"></td> <td class="colRightSection thWidth4Per"></td> 
					                                   <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	
				                              	</tbody>
				                              	<tbody>
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteShortTermSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==0" ng-show='onsiteShortTerm.length > 0' >	                          			
					                                    <td colspan="5"><strong>2 .SUB TOTAL(Short term (Business visa))</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteShortTermSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==1 " >	                          			
					                                    <td colspan="5"><strong>2 .SUB TOTAL(Short term (Business visa))</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                 	<td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                 	<td class="thWidth4Per"></td><td class="thWidth4Per"></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
												</tbody>
				                              	<tbody>
				                              	<tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show ='onsiteContractor.length > 0 || onsiteTotal.length > 0'>
								                                    <td colspan="5" style="color:#ffffff"><!-- Total Onsite --> {{fpDealStaffing.onsiteTotalLabel}}</strong></td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
								                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
							                                 </tr>	
					                              	<tr class="sectionHeading" ng-if=" summaryFlag.length==1">
					                              		<td colspan="18"><span class="text-white"><strong><!-- Total Onsite --> {{fpDealStaffing.onsiteTotalLabel}}</strong></span></td>
				                              		</tr>
				                              	</tbody>
				                              	<tbody>
				                              		<tr  ng-repeat ="data  in onsiteContractor" ng-if="currentMonthYearHeader == data.monthYearHeader  && data.isContractor == 1 ">
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	<tr ng-repeat ="data  in onsiteTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0 && summaryFlag.length==0">
					                          			<td class="thWidth22Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	
					                              	
					                              	<tr ng-repeat ="data  in onsiteTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0 && summaryFlag.length==1">
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"></td> <td class="colRightSection thWidth4Per"></td> 
					                                   <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==0" >	                          			
					                                    <td colspan="5"><strong>3. SUB TOTAL (1 + 2 ) Onsite</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              		<tr class="rowSubTotal" ng-repeat="data  in onsiteSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==1" >	                          			
					                                    <td colspan="5"><strong>4. SUB TOTAL (1 +2 ) Onsite</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"></td><td class="thWidth4Per"></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                            </tbody>
					                            <!-- <tbody>
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteGrossSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader" >	                          			
					                                    <td colspan="5"><strong>5. SUB TOTAL(onsiteGrossSumTotal)</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                            
				                              	</tbody> -->
				                              	<tbody>
					                              	<tr bgcolor="black">
					                              		<td colspan="20"></td>	
					                              	</tr>
				                              	</tbody>
				                              	
				                              		<tbody>
				                              		<tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='offshoreTotalContr.length > 0'>
					                                    <td colspan="5"  style="color:#ffffff" ><strong>OffShore Contractor </strong></td  style="color:#ffffff" >	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
					                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
							                         </tr>	
					                              	<tr class="sectionHeading" ng-if="summaryFlag.length==1" ng-show='offshoreTotalContr.length > 0' >
					                              		<td colspan="18"><span class="text-white"><strong>OffShore Contractor </strong></span></td>
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
					                              	<!-- <tr ng-repeat ="data  in offshoreTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 1" >					                              		
					                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFirstMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing')"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	<!-- <tr ng-repeat ="data  in offshoreTotalContr" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==0 && data.isContractor == 1" ng-show='offshoreTotalContr.length > 0'>					                              		
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('offshoreTotalContr',$index); getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFirstMonthCount');  calculateSubTotal('offshoreTotalContr','offshoreSumTotalContr','staffingFirstMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFirstMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSecondMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSecondMonthCount'); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSecondMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingThirdMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingThirdMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFourthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFourthMonthCount'); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFourthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFifthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFifthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFifthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSixthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSixthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSixthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSeventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSeventhMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSeventhMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEighthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEighthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEighthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingNinthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingNinthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingNinthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTenthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTenthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTenthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEleventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEleventhMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEleventhMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTwelthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTwelthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTwelthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	<tr ng-repeat="data  in offshoreTotalContr"
														ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 1 && summaryFlag.length==0 ">
														<td class="thWidth22Per colSection"><input type="text"
															class="form-control"
															ng-model="data.masterRoles.masterRoleShortDescription"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															class="form-control" ng-model="data.customerRole" disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															class="form-control" ng-model="data.masterRoles.bandGrade"
															disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															class="form-control" ng-model="data.masterRoles.gcmCODE"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															class="form-control" ng-model="data.stfaffingBilling_Rate"
															disabled></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFirstMonthCount != null"
															ng-model="data.staffingFirstMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingFirstMonthCount,'staffingFirstMonthCount');calculateRowTotal('offshoreTotalContr',$index); getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFirstMonthCount');  calculateSubTotal('offshoreTotalContr','offshoreSumTotalContr','staffingFirstMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFirstMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingFirstMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSecondMonthCount != null"
															ng-model="data.staffingSecondMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingSecondMonthCount,'staffingSecondMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSecondMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSecondMonthCount'); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSecondMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingSecondMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingThirdMonthCount != null"
															ng-model="data.staffingThirdMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingThirdMonthCount,'staffingThirdMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingThirdMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingThirdMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingThirdMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFourthMonthCount != null"
															ng-model="data.staffingFourthMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingFourthMonthCount,'staffingFourthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFourthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFourthMonthCount'); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFourthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingFourthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFifthMonthCount != null"
															ng-model="data.staffingFifthMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingFifthMonthCount,'staffingFifthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFifthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFifthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFifthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingFifthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSixthMonthCount != null"
															ng-model="data.staffingSixthMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingSixthMonthCount,'staffingSixthMonthCount'); calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSixthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSixthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSixthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingSixthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSeventhMonthCount != null"
															ng-model="data.staffingSeventhMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingSeventhMonthCount,'staffingSeventhMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSeventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSeventhMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSeventhMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingSeventhMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEighthMonthCount != null"
															ng-model="data.staffingEighthMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingEighthMonthCount,'staffingEighthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEighthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEighthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEighthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingEighthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingNinthMonthCount != null"
															ng-model="data.staffingNinthMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingNinthMonthCount,'staffingNinthMonthCount'); calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingNinthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingNinthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingNinthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingNinthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTenthMonthCount != null"
															ng-model="data.staffingTenthMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingTenthMonthCount,'staffingTenthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTenthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTenthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTenthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingTenthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEleventhMonthCount != null"
															ng-model="data.staffingEleventhMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingEleventhMonthCount,'staffingEleventhMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEleventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEleventhMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEleventhMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingEleventhMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTwelthMonthCount != null"
															ng-model="data.staffingTwelthMonthCount"
															ng-blur="setZero(offshoreTotalContr,$index,data.staffingTwelthMonthCount,'staffingTwelthMonthCount'); calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTwelthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTwelthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTwelthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');
															getOtherData('offshoreTotalContr','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','offshoreSumTotalContr','totalStaffingContr','totalSumTotalStaffing','onsiteGross','onsiteGrossSumTotal','staffingGross','staffingGrossSumTotal',offshoreTotalContr,$index,'staffingTwelthMonthCount');"></td>
															
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control" ng-model="data.yearlyTotal"
															disabled></td>
													</tr>
					                              	<tr ng-repeat ="data  in offshoreTotalContr" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==1 && data.isContractor == 1" ng-show='offshoreTotalContr.length > 0'>					                              		
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
														<td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.gcmCODE"	disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('offshoreTotalContr',$index); getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFirstMonthCount');  calculateSubTotal('offshoreTotalContr','offshoreSumTotalContr','staffingFirstMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFirstMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSecondMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSecondMonthCount'); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSecondMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingThirdMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingThirdMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFourthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFourthMonthCount'); calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFourthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFifthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFifthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingFifthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSixthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSixthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSixthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSeventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSeventhMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingSeventhMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEighthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEighthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingEighthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingNinthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingNinthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingNinthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTenthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTenthMonthCount');calculateTotalStaffingContr('totalStaffingContr',$index,'staffingTenthMonthCount','totalSumTotalStaffing');getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"></td>
														<td class="colRightSection thWidth4Per"></td>
					                                     <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                              	
				                              	<tbody>
				                              	<tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='offshoreTotal.length > 0'>
								                                    <td colspan="5" style="color:#ffffff"><strong>{{fpDealStaffing.offshoreTotalLabel}}</strong></td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
								                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
							                                 </tr>	
					                              	<tr class="sectionHeading" ng-if="summaryFlag.length==1" ng-show='offshoreTotal.length > 0'>
					                              		<td colspan="18"><span class="text-white"><strong>{{fpDealStaffing.offshoreTotalLabel}}</strong></span></td>
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
					                              	<!-- <tr ng-repeat ="data  in offshoreTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0  && summaryFlag.length==0" ng-show='offshoreTotal.length > 0'>					                              		
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index); getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFirstMonthCount'); calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFirstMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSecondMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFourthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFifthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSixthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSeventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEighthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingNinthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTenthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEleventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTwelthMonthCount'); calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	<tr ng-repeat="data  in offshoreTotal"
														ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0 && summaryFlag.length==0">
														<td class="thWidth22Per colSection"><input type="text"
															class="form-control"
															ng-model="data.masterRoles.masterRoleShortDescription"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															class="form-control" ng-model="data.customerRole" disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															class="form-control" ng-model="data.masterRoles.bandGrade"
															disabled></td>
														<td class="thWidth15Per colSection"><input type="text"
															class="form-control" ng-model="data.masterRoles.gcmCODE"
															disabled></td>
														<td class="thWidth8Per colSection"><input type="text"
															class="form-control" ng-model="data.stfaffingBilling_Rate"
															disabled></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFirstMonthCount != null"
															ng-model="data.staffingFirstMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingFirstMonthCount,'staffingFirstMonthCount'); calculateRowTotal('offshoreTotal',$index); getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFirstMonthCount'); calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFirstMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingFirstMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSecondMonthCount != null"
															ng-model="data.staffingSecondMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingSecondMonthCount,'staffingSecondMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSecondMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingSecondMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingThirdMonthCount != null"
															ng-model="data.staffingThirdMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingThirdMonthCount,'staffingThirdMonthCount'); calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingThirdMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFourthMonthCount != null"
															ng-model="data.staffingFourthMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingFourthMonthCount,'staffingFourthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFourthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingFourthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingFifthMonthCount != null"
															ng-model="data.staffingFifthMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingFifthMonthCount,'staffingFifthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFifthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingFifthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSixthMonthCount != null"
															ng-model="data.staffingSixthMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingSixthMonthCount,'staffingSixthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSixthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingSixthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingSeventhMonthCount != null"
															ng-model="data.staffingSeventhMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingSeventhMonthCount,'staffingSeventhMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSeventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingSeventhMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEighthMonthCount != null"
															ng-model="data.staffingEighthMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingEighthMonthCount,'staffingEighthMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEighthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingEighthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingNinthMonthCount != null"
															ng-model="data.staffingNinthMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingNinthMonthCount,'staffingNinthMonthCount'); calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingNinthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingNinthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTenthMonthCount != null"
															ng-model="data.staffingTenthMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingTenthMonthCount,'staffingTenthMonthCount'); calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTenthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingTenthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingEleventhMonthCount != null"
															ng-model="data.staffingEleventhMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingEleventhMonthCount,'staffingEleventhMonthCount');calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEleventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEleventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEleventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEleventhMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingEleventhMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control"
															ng-if="data.staffingTwelthMonthCount != null"
															ng-model="data.staffingTwelthMonthCount"
															ng-blur="setZero(offshoreTotal,$index,data.staffingTwelthMonthCount,'staffingTwelthMonthCount'); calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTwelthMonthCount'); calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTwelthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTwelthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTwelthMonthCount');
															getOtherData('offshoreTotal','offshore_Contractor','offshore_ContrSumTotal','offshoreSumTotal','offshoreSumTotalContr','offshoreSumTotalContr','onsiteGross','onsiteGrossSumTotal','totalStaffing','totalSumTotalStaffing','staffingGross','staffingGrossSumTotal',offshoreTotal,$index,'staffingTwelthMonthCount');"></td>
														<td class="colRightSection thWidth4Per"><input
															type="text" class="form-control" ng-model="data.yearlyTotal"
															disabled></td>
													</tr>
					                              	<tr ng-repeat ="data  in offshoreTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0  && summaryFlag.length==1" >					                              		
					                          			<td class="thWidth22Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.gcmCODE"	disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index); getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFirstMonthCount'); calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFirstMonthCount');  calculateTotalStaffing('totalStaffing',$index,'staffingFirstMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFirstMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSecondMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSecondMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSecondMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSecondMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingThirdMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingThirdMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingThirdMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingThirdMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFourthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFourthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFourthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFourthMonthCount');" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingFifthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingFifthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingFifthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingFifthMonthCount');" disabled></td>
					                                  	<td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSixthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSixthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSixthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSixthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingSeventhMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingSeventhMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingSeventhMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingSeventhMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingEighthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingEighthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingEighthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingEighthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingNinthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingNinthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingNinthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingNinthMonthCount');"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal('offshoreTotal',$index);getLocal_Contr('offshore_Contractor','offshore_ContrSumTotal',$index,'staffingTenthMonthCount');calculateSubTotal('offshoreTotal','offshoreSumTotal','staffingTenthMonthCount');calculateTotalStaffing('totalStaffing',$index,'staffingTenthMonthCount','totalSumTotalStaffing'); getStaffingGross('staffingGross','staffingGrossSumTotal',$index,'staffingTenthMonthCount');"></td> 
					                                    <td class="colRightSection thWidth4Per"></td> <td class="colRightSection thWidth4Per"></td>
					                                  	<td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
					                              		<!-- <tr class="rowSubTotal" ng-repeat="data  in offshoreSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader" >	                          			
					                                    <td colspan="5"><strong>6. SUB TOTAL</strong></td>	 
					                                    <td colspan="5"><strong>6. OffShore(Including Contractor) TOTAL</strong></td>	                                   
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	
					                              	<tr class="rowSubTotal" ng-repeat="data  in offshore_ContrSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==0" >         			

					                                    <td colspan="5"><strong>4. OffShore(Including Contractor) TOTAL</strong></td>	                                   
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	<tr class="rowSubTotal" ng-repeat="data  in offshore_ContrSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==1" >         			

					                                    <td colspan="5"><strong>4. OffShore(Including Contractor) TOTAL</strong></td>	                                   
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"></td><td class="thWidth4Per"></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
				                              	<tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='onsiteLocal.length > 0 && offshoreTotal.length==0'>
					                                    <td colspan="5" style="color:#ffffff"><strong>Total Staffing</strong></td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
					                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
				                                 </tr>
				                                 
				                                 <tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='onsiteLocal.length == 0 && offshoreTotal.length > 0'>
					                                    <td colspan="5" style="color:#ffffff"><strong>Total Staffing</strong></td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
					                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
				                                 </tr>	
				                                  <tr  class="" ng-if="monthHeader.length > 0 &&  summaryFlag.length==0" bgcolor="#000000" ng-show='offshoreTotal.length > 0 && onsiteLocal.length > 0 '>
					                                    <td colspan="5" style="color:#ffffff"><strong>Total Staffing</strong></td>	<td class="thWidth4Per" ng-repeat="month in monthHeader" style="color:#ffffff" >{{month.month}}</td>
					                                    <td class="thWidth4Per" style="color:#ffffff">Total</td>
				                                 </tr>	
				                                 
					                              	<tr class="sectionHeading" ng-if="summaryFlag.length==1">
					                              		<td colspan="18"><span class="text-white"><strong>Total Staffing</strong></span></td>
				                              		</tr>
				                              	</tbody>
				                              	<tbody>
				                              		<tr ng-repeat ="data  in totalStaffingContr" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 1  && summaryFlag.length==0" ng-show='totalStaffingContr.length > 0'>
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text"	class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	<tr ng-repeat ="data  in totalStaffingContr" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 1  && summaryFlag.length==1" >
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.gcmCODE"	disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td> 
					                                    <td class="colRightSection thWidth4Per"></td> <td class="colRightSection thWidth4Per"></td> 
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              		
					                              	<tr ng-repeat ="data  in totalStaffing" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0  && summaryFlag.length==0" >
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.gcmCODE"	disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	<tr ng-repeat ="data  in totalStaffing" ng-if="currentMonthYearHeader == data.monthYearHeader && data.isContractor == 0  && summaryFlag.length==1" >
					                          			<td class="thWidth22Per colSection" ><input type="text" class="form-control" ng-model="data.masterRoles.masterRoleShortDescription" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.customerRole" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.bandGrade" disabled></td>
					                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" ng-model="data.masterRoles.gcmCODE" disabled></td>
					                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" ng-model="data.stfaffingBilling_Rate" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                  	<td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td> 
					                                    <</td> <td class="colRightSection thWidth4Per"></td> <td class="colRightSection thWidth4Per"></td>
					                                  	<td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
				                              	</tbody>
				                              	<tbody>
												    <tr class="rowSubTotal" ng-repeat="data  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == data.monthYearHeader  && summaryFlag.length==0" >	                          			
					                                    <td colspan="5"><strong>5. Gross Staffing Total (3+4)</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	
					                              	<tr class="rowSubTotal" ng-repeat="data  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==1" >	                          			
					                                    <td colspan="5"><strong>5. Gross Staffing Total (3+4)</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                 	<td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"></td><td class="thWidth4Per"></td>
					                                   <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	
					                              	<!--  <tr class="rowSubTotal" ng-repeat="data  in totalSumTotalStaffing1" ng-if="currentMonthYearHeader == data.monthYearHeader" >	                          			
					                                    <td colspan="5"><strong>7. SUB TOTAL</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                              	
					                              <!-- 	<tr class="rowSubTotal" ng-repeat="data  in staffingGrossSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader" >	                          			
					                                    <td colspan="5"><strong>7.1. SUB TOTAL</strong></td>	                                    
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFirstMonthCount != null" ng-model="data.staffingFirstMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSecondMonthCount != null" ng-model="data.staffingSecondMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingThirdMonthCount != null" ng-model="data.staffingThirdMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFourthMonthCount != null" ng-model="data.staffingFourthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingFifthMonthCount != null" ng-model="data.staffingFifthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSixthMonthCount != null" ng-model="data.staffingSixthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingSeventhMonthCount != null" ng-model="data.staffingSeventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingEighthMonthCount != null" ng-model="data.staffingEighthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingNinthMonthCount != null" ng-model="data.staffingNinthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTenthMonthCount != null" ng-model="data.staffingTenthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control"  ng-if="data.staffingEleventhMonthCount != null" ng-model="data.staffingEleventhMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-if="data.staffingTwelthMonthCount != null" ng-model="data.staffingTwelthMonthCount" disabled></td>
					                                    <td class="thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr> -->
					                            </tbody>  
					                            <tbody>
					                            	<tr class="rowSubTotal" ng-repeat="data  in onsiteSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==0" >
					                              	  <td colspan="5"><strong>Onsite(%)/Year</strong></td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingFirstMonthCount*100/datat.staffingFirstMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>	 
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingSecondMonthCount*100/datat.staffingSecondMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              		
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingThirdMonthCount*100/datat.staffingThirdMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingFourthMonthCount*100/datat.staffingFourthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingFifthMonthCount*100/datat.staffingFifthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingSixthMonthCount*100/datat.staffingSixthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingSeventhMonthCount*100/datat.staffingSeventhMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingEighthMonthCount*100/datat.staffingEighthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingNinthMonthCount*100/datat.staffingNinthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingTenthMonthCount*100/datat.staffingTenthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	   <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingEleventhMonthCount*100/datat.staffingEleventhMonthCount | number : 2" disabled></td> 
							                              	</tr>							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingTwelthMonthCount*100/datat.staffingTwelthMonthCount | number : 2" disabled></td> 
							                              	</tr>							                              	
						                              	  </table>
					                              	  </td>
					                              	  
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==0" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.yearlyTotal*100/datat.yearlyTotal | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	   </td>
					                              	   
					                              	 </tr>   
					                            </tbody> 
					                            
					                            <tbody> 	
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==1" >
					                              	 <td colspan="5"><strong>Onsite(%)/Year</strong></td>	 
					                              		<td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingFirstMonthCount*100/datat.staffingFirstMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingSecondMonthCount*100/datat.staffingSecondMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingThirdMonthCount*100/datat.staffingThirdMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingFourthMonthCount*100/datat.staffingFourthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingFifthMonthCount*100/datat.staffingFifthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingSixthMonthCount*100/datat.staffingSixthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingSeventhMonthCount*100/datat.staffingSeventhMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingEighthMonthCount*100/datat.staffingEighthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingNinthMonthCount*100/datat.staffingNinthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.staffingTenthMonthCount*100/datat.staffingTenthMonthCount | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  
					                                 </td><td class="thWidth4Per"></td><td class="thWidth4Per"></td>
					                              	  
					                              	  <td width="5%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 					                              	 	
							                              	 <td width="5%" ><input type="text" class="form-control" ng-model="data.yearlyTotal*100/datat.yearlyTotal | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  
					                              	</tr>
					                              	
				                              	</tbody>  
					                            
					                            <!-- <tbody> 	
					                              	<tr class="rowSubTotal" ng-repeat="data  in onsiteSumTotal" ng-if="currentMonthYearHeader == data.monthYearHeader && summaryFlag.length==1" >
					                              		<td width="20%">
						                              	  <table  class="table tblDealCre table-borderless table-condensed">
						                              	  	<tr  ng-repeat="datat  in totalSumTotalStaffing" ng-if="currentMonthYearHeader == datat.monthYearHeader && summaryFlag.length==1" >
						                              	  	 <td width="20%" class="thWidth60Per" >Total Onsite % </td>							                              	 	
							                              	 <td width="20%" ><input type="text" class="form-control" ng-model="data.yearlyTotal*100/datat.yearlyTotal | number : 2" disabled></td> 
							                              	</tr>
							                              	
						                              	  </table>
					                              	  </td>
					                              	  
					                              	</tr>
					                              	
				                              	</tbody>   -->
				                              	                            	
					                     	</table>
										</div>
										<!-- fgdg -->
										<div class="divEmptyThrice"></div>
										<!-- <div class="row">
											<div class="col-sm-12">
												<div class="panel-group">
													<div class="panel panel-info ">
													<ng-form name="dealCreationAddContractorRoleForm" id="dealCreationAddContractorRoleForm" >
														<div class="panel-heading panelHeadingStyle">
															<div class="row ">
																<label class="control-label col-sm-10 ">Add Contractor
																	Role</label>
																<div class="col-sm-2 textAlignRight">
																					<a href="#" class="DownArrowColor" ng-click="ShowHideAddContractor()"> &#9660;</a>
																				</div>
															</div>
														</div>
														<div class="panel-body">
															<div class="row marginBottom5px">
																<div class="col-sm-12">
																	<div class="table-responsive  ">
																		<table class="table clsTable table-striped table-bordered table-hover  " id="tblAddContractor">
																			<thead>
																				<tr>
																					<th>Sr.No.</th>																				
																					<th>Customer Role</th>																				
																					<th>Onsite Rate</th>																					
																					<th>Onsite Cost/Hr</th>	
																					<th>Offshore Rate</th>
																					<th>Offshore Cost/Hr</th>
																					 <th>Onsite Rate increment%</th>
																					 <th>Onsite Cost increment%</th>
																					 <th>Offshore Rate increment%</th>
																					 <th>Offshore Cost increment%</th>
																					<th>Remove</th>
																				</tr>
																			</thead>
																			<tbody id="tBodyAddContractor">
																				<tr id="{{'traddContactorRole'+'_'+($index+1)}}" ng-repeat="row in staffingContactorRoleList">
																					<td><input name="{{'txtSrNo'+'_'+($index+1)}}" ng-model="row.itemSrNo"  type="text" class="form-control" id="{{'txtSrNo'+'_'+($index+1)}}" value="{{$index+1}}" disabled></td>
																					<td><input name="{{'txtcontactorRole'+'_'+($index+1)}}" type="text" class="form-control" value="{{$index+1}}" id="{{'txtSrNo'+'_'+($index+1)}}" disabled></td>
																					
																					<td><input name="{{'txtcontactorRole'+'_'+($index+1)}}" ng-model="row.customerRole" type="text" class="form-control" id="txtcontactorRole" disabled></td>
																					
																					<td><input name="{{'txtonsiteRate'+'_'+($index+1)}}" ng-model="row.onsiteRate" type="text" class="form-control" id="txtonsiteRate"></td>
																					
																					<td><input name="{{'txtonsiteCost'+'_'+($index+1)}}" ng-model="row.onsiteCost" type="text" class="form-control" id="txtonsiteCost"></td>
																					
																					<td><input name="{{'txtoffshoreRate'+'_'+($index+1)}}" ng-model="row.offshoreRate" type="text" class="form-control" id="txtoffshoreRate"></td>
																					<td><input name="{{'txtoffshoreCost'+'_'+($index+1)}}" ng-model="row.offshoreCost" type="text" class="form-control" id="txtoffshoreCost"></td>
																					
																					<td><input name="{{'txtonsiteRatePer'+'_'+($index+1)}}" ng-model="row.onsiteRateIncrementPercent" type="text" class="form-control" id="txtonsiteRatePer"></td>
																					<td><input name="{{'txtonsiteCostPer'+'_'+($index+1)}}" ng-model="row.onsiteCostIncrementPercent" type="text" class="form-control" id="txtonsiteCostPer"></td>
																					<td><input name="{{'txtoffshoreRatePer'+'_'+($index+1)}}" ng-model="row.offshoreRateIncrementPercent" type="text" class="form-control" id="txtoffshoreRatePer"></td>
																					<td><input name="{{'txtoffshoreCostPer'+'_'+($index+1)}}" ng-model="row.offshoreCostIncrementPercent" type="text" class="form-control" id="txtoffshoreCostPer"></td>
																					
																					<td><input name="{{'txtcomments'+'_'+($index+1)}}" ng-model="row.comments" type="text" class="form-control" id="txtcomments"></td>
																					<td><button type="button" id="{{'btnAddContractorRemoveRow_'+'_'+($index+1)}}" ng-click="removeClick($index+1,row)">Remove</button></td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																</div>
															</div>
															<div class="divEmptyThrice"></div>															
														</div>
														</ng-form>
													</div>
												</div>
											</div>
										</div> -->
										
										<!-- sdfsd -->
									</div>
								</div>
								<div class="divEmptyThrice"></div>
                                <div class="row text-center">
									<div class="col-sm-12">	
										<!-- <button type="button" class="btn btn-primary btnSpace" id="btnCalculate" ng-click="save()" ng-disabled="isVersionAvailable">Save</button> -->
										<!-- <button type="button" class="btn btn-danger btnSpace" id="btnCancel">Cancel</button> -->	
									</div>
								</div>
								<div class="divEmptyThrice"></div>
                                   
                               </div>
                           </div>
                       </div>
                   </div>
               	</div>
	          	<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Upload Deal Staffing Excel</label>
										<div class="col-sm-2 textAlignRight">
											<label class="DownArrowColor textAlignRight"> &#9660;</label>
										</div>
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "UploadHidden">
		                             <div class="row marginBottom5px">
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File name</label>
			                             <div class="col-sm-3 ">
			                            	<input type="file" class="form-control" name="fuUploadFilename"
												id="fuUploadFilename" ng-model="RateCard.fuUploadFilenameModel" required
												ng-class="{true: 'ng-border'} [(upload && RateCard.fuUploadFilename.$invalid)]" 
												 >
												<div class="error-messages" ng-if="upload" ng-messages="RateCard.fuUploadFilename.$error">
													<em class="error help-block has-error" ng-message="checkfilesize">File size is not valid for uploading!</em> 
														<em class="error help-block has-error" ng-message="required">Please upload file</em>
														 <em class="error help-block has-error" ng-message="extension">File	format is not valid for uploading!</em>
												</div>
										</div> 
										
										<label class="control-label col-sm-3 textAlignRight">Excel sheet</label>
			                            <div class="col-sm-3 ">
											<input type="button" class="btn btn-primary" value="Download Template" id="btnUploadonExcelSheet" 
												name="btnUploadonExcelSheet">
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnUploadUpload" ng-click="updateStaffingAttachment(RateCard);">Upload</button>						
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
										<div class="col-sm-2"></div>
										<div class="col-sm-8">
											<div class="table-responsive">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblUploadDocuments">
													<thead>
														<tr>
															<th class="firstColLeftAlign">Deal Staffing</th>
															<th colspan="2">Action</th>
														</tr>
													</thead>
													<tbody id="tBodyUploadDocuments">
														<tr ng-repeat="version in versionAttachment|orderBy:'-updatedOn'"> 
															<td class="firstColLeftAlign">{{version.fileName}}</td>
															<td><a href="#" id="Attach_Download"
																class="control-label  textAlignLeft"
																ng-click="downloadFileWithFileName(version.dealAttachmentId);">Download</a>
															</td>
															<td><button type="button" id="btnUploadDocumentsRemoveRow1" ng-click="deleteManualFile(version.dealAttachmentId);" >Remove</button></td>
														</tr>														
													</tbody>
												</table>
											</div>
										</div>
									</div>									
	                           	</div>
	                       	</div>  
						</div>
			       	</div>
				</div>
				<div class="divEmptyThrice"></div>
                <div class="row text-center">
					<div class="col-sm-12">					
						<button type="button" class="btn btn-info btnSpace"id="btnClientPrev" ng-click="Prev()">Prev</button>
						<button type="button" class="btn btn-danger" id="btnIdClear" ng-click="clear();">Clear</button>
						<button type="button" class="btn btn-primary btnSpace" id="btnCalculate" ng-click="save()" ng-disabled="isVersionAvailable">Save</button>
						<button class="btn btn-primary btnSpace" type="button" ng-click="exportToExcel()">
      										     <img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
						<button type="button" class="btn btn-info" id="btnClientNext"ng-click="Next()">Next</button>
					</div>
				</div>
	      	