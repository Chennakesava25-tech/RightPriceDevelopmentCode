				<!-- <div class="row" >
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideRegionWise()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Region-wise Utilization(Onsite)</label>
										<div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideTeamDetails()"> &#9660;</a>
											<label class="DownArrowColor textAlignRight"> &#9660;</label>
										</div>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "RegionWiseHidden" ng-disabled="disableInputs">
									
									<div class="row marginBottom5px">
                                    	<div class="col-sm-1"></div>
                                    	<div class="col-sm-10">
                                    		<div class="table-responsive">
                                    			<table class="table clsTable table-striped table-bordered table-hover">
                                    				<thead>
                                    					<tr>
                                    						<th>select</th>
															<th class="width30">Base Country</th>
															<th class="width30">Currency</th>
															<th class="width30">Utilization</th>
                                    					</tr>
                                    				</thead>
                                    				<tbody id="tBodyRegionWiseLocation">
                                    					
                                    					<tr id="{{'regoinWiseUilization'+'_'+($index+1)}}" ng-repeat="regoinWiseUilization in regionWiseUtilizationDetails">
                                    					    <td><input type="checkbox" name="{{'cbxSelectTbl'+'_'+($index+1)}}" id="{{'cbxSelectTbl'+'_'+($index+1)}}" 
                                    					    ng-change="addUtilization(regoinWiseUilization)"  class="margingRightChkBx" 
                                    					    ng-model="regoinWiseUilization.selectChkBox"  ng-disabled="disableInputs"></td>
                                    						<td><input name="{{'txtCountryName'+'_'+($index+1)}}" ng-model="regoinWiseUilization.countryName"  type="text" 
														class="form-control" id="{{'txtCountryName'+'_'+($index+1)}}" disabled></td>
														
														<td><input name="{{'txtCityName'+'_'+($index+1)}}" ng-model="regoinWiseUilization.currencyCode"  type="text" 
														class="form-control" id="{{'txtCityName'+'_'+($index+1)}}" disabled></td>
														
														<td><input name="{{'txtUtilization'+'_'+($index+1)}}" ng-model="regoinWiseUilization.utilization"  type="text" 
														class="form-control" id="{{'txtUtilization'+'_'+($index+1)}}" ng-change="calculateToatlUtilization()"
														 ng-disabled="disableInputs"></td>
                                    					</tr>
                                   					<tr class="redColor">
														<td></td>
														<td></td>
														<td><strong>TOTAL Utilization</strong></td>
														<td><input name="txtTotalUtilization" ng-model="totalUtilization"  type="text" 
															ng-class="{true: 'ng-border'}[(saved && frmRateCard.txtTotalUtilization.$invalid)]"
															class="form-control" id="txtTotalUtilization" checkhundredpercente disabled>
															<div class="error-messages" ng-if= "saved" ng-messages="frmRateCard.txtTotalUtilization.$error">
																<em class="error help-block has-error" ng-message="checkhundredpercente">Total Utilization should be 100%!</em>
													        </div>	
														</td>
															
													</tr>
                                    				</tbody>
                                    			</table>
                                    		</div>
                                    	</div>
                                    	<div class="col-sm-1"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> -->
				<!--  <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideAddLocation()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Add Location Details(onsite)</label>
										<div class="col-sm-2 textAlignRight">
											<label class="DownArrowColor textAlignRight"> &#9660;</label>
										</div>
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "AddLocationHidden">
		                             <div class="row marginBottom5px">
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
			                            <div class="col-sm-3 ">
			                            	<select id="ddlAddLocationCountry" class="form-control" placeholder="Please select" name="ddlAddLocationCountry"
	                                           		ng-model="frmRateCard.country" ng-options="cn as cn.countryName for cn in chkBoxSelectedCountryList" 
	                                           		ng-change="getCities(frmRateCard.country.countryId)" ng-disabled="disableInputs" required>
													<option value="" disabled>Please select</option>
												</select>
										</div>
										 
										<label class="control-label col-sm-2 textAlignRight required-Field">City</label>
			                         	<div class="col-sm-3 ">   	
			                            	<select id="frmRateCard.ddlAddLocationCity" class="form-control" placeholder="Please select" name="ddlAddLocationCity"
	                                           		ng-model="frmRateCard.city" ng-options="ci as ci.cityName for ci in city" ng-disabled="disableInputs" required >ng-model="ddlAddLocationCityModel" required>
													<option value="" selected disabled>Please select</option>
													<option value="1">Mumbai</option>
													<option value="2">Pune</option>
													<option value="3">Chennai</option>
												</select>
										</div>
										<div class="col-sm-2 textAlignRight">		
										   <button type="button" class="btn btn-info btnSpace" id="btnDetailsAdd"
										    ng-click="addLocationRow(frmRateCard.country,frmRateCard.city)" ng-disabled="disableInputs">Add</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row">
										 <div class="col-sm-1"></div>
	                                    <div class="col-sm-10">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblAddLocation" ng-show="(locationDetails).length">
													<thead>
														<tr>
															<th class="width25per">Country</th>
															<th class="width25per">City</th>
															<th class="width25per">Utilization %</th>
															<th class="width25per">Remove</th>
														</tr>
													</thead>
													<tbody id="tBodyAddLocation" >
													<tr id="{{'locationDetail'+'_'+($index+1)}}" ng-repeat="locationDetail in locationDetails">
														<td><input name="{{'txtCountryName'+'_'+($index+1)}}" ng-model="locationDetail.countryName"  type="text" 
														class="form-control" id="{{'txtCountryName'+'_'+($index+1)}}" disabled></td>
														
														<td><input name="{{'txtCityName'+'_'+($index+1)}}" ng-model="locationDetail.cityName"  type="text" 
														class="form-control" id="{{'txtCityName'+'_'+($index+1)}}" disabled></td>
														
														<td><input name="{{'txtUtilization'+'_'+($index+1)}}" ng-model="locationDetail.utilization"  type="text" 
														class="form-control" id="{{'txtUtilization'+'_'+($index+1)}}" ng-change="checkTotalCountryWiseUtilization()"></td>
														
														<td><button type="button"id="{{'btnRemove'+'_'+($index+1)}}" ng-click="removeLocation($index)">Remove</button></td>
													</tr>
													<tr class="redColor">
														<td><strong>TOTAL Utilization</strong></td>
														<td></td>
														<td><input name="txtTotalUtilization" ng-model="totalUtilization"  type="text" 
															class="form-control" id="txtTotalUtilization" disabled></td>
														<td></td>
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
				</div> -->
				<!-- <fieldset ng-disabled="loading || showLoader"> -->
	            <div class="row" ng-hide="isRateCardDetails">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideRateCardDetails()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Rate Card Details</label>
									</div>
                                </div>
                                <div class="panel-body"> 
                                  <div class="row marginBottom5px">
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Rate Card Name</label>
			                            <div class="col-sm-3 ">			                           
			                            	<select id="ddlAddrateCardName" class="form-control" placeholder="Please select" name="ddlrateCardName"
	                                           		ng-model="frmRateCard.rateCard_Name" ng-options="rc as rc.rcName for rc in rateCardName" 
	                                           		ng-disabled="disableInputs" required>
													<option value="" disabled>Please select</option>
											</select>
										</div>								
										<div class="col-sm-2 textAlignRight">		
										   <button type="button" class="btn btn-info btnSpace" id="btnDetailsAdd"
										    ng-click="addRateCardRow(frmRateCard.rateCard_Name)" ng-disabled="disableInputs">Add RateCard</button>
										</div>
									</div>                                
                                	<div class="divEmptyThrice"></div>
                                    <div class="row marginBottom5px">
                                    	<div class="col-sm-2"></div>
                                    	<div class="col-sm-8">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails">
													<thead>
														<tr>
															<!-- <th style="display:block">Select</th> -->
															<th class="firstColLeftAlign width15per">RateCard Id</th>
															<th class="firstColLeftAlign width30per">Selected Rate Card</th>
<!-- 															<th>Country</th> -->
<!-- 															<th>City</th> -->
															<th class="width10per">Weightage</th>
															<th class="firstColLeftAlign width20per">RC Start Date</th>
															<th class="firstColLeftAlign width20per">RC Expected End Date</th>
															<th>Remove</th>
														</tr>
													</thead>
													<tbody id="tBodyRateCardDetails" >
													
													
													<tr id="{{'rateCard'+'_'+($index+1)}}" ng-repeat="rateCard in rateCardDetails">
																										
														<!-- <td style="display:block"><input type="checkbox" name="{{'cbxSelectRow'+'_'+($index+1)}}" id="{{'cbxSelectRow'+'_'+($index+1)}}" class="margingRightChkBx"> </td> -->
														
														<td><input name="{{'txtRateCardId'+'_'+($index+1)}}" ng-model="rateCard.rcId"
														type="text" style="border-bottom: 3px solid black;color:black;cursor:pointer;"
														class="form-control" id="{{'txtRateCardId'+'_'+($index+1)}}"  ng-click="showRateCardDetails(rateCard.rcId)" readonly="readonly"> </td>	
														
														<td>
														<input name="{{'txtRateCardName'+'_'+($index+1)}}" ng-model="rateCard.rcName"  type="text" 
														class="form-control" id="{{'txtRateCardName'+'_'+($index+1)}}" disabled></td>														
														
														<td><input name="{{'txtWeightage'+'_'+($index+1)}}" ng-model="rateCard.weightage"  type="text" 
														class="form-control" id="{{'txtWeightage'+'_'+($index+1)}}" ng-change="calculateToatlWeightage()"></td>
														
														<td><input name="{{'txtRateCardRC_Start_Date'+'_'+($index+1)}}" ng-model="rateCard.RC_Start_Date"  type="text" 
														class="form-control" id="{{'txtRateCardRC_Start_Date'+'_'+($index+1)}}" disabled></td>	
														
														<td><input name="{{'txtRateCardRC_End_Date'+'_'+($index+1)}}" ng-model="rateCard.RC_End_Date"  type="text" 
														class="form-control" id="{{'txtRateCardRC_End_Date'+'_'+($index+1)}}" disabled></td>	
																											
														<td><button type="button"id="{{'btnRemove'+'_'+($index+1)}}" ng-click="removeRateCard($index)">Remove</button></td>
													</tr>	
																					
													
													</tbody>
												</table>
											</div>
										</div>
									</div>
                                    <div class="divEmptyThrice"></div>
                              
									</div> 
                                </div>
                            </div>
                        </div>
                   </div>
                 <!--  </fieldset> -->
            
	            <!--   <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideTowerDetails()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Tower Details</label>
										<div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor"> &#9660;</a>
										</div>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "TowerDetailsHidden">
                                	<div class="row marginBottom5px">
                                		<div class="col-sm-3"></div>
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Tower Name</label>
                                        <div class="col-sm-3">
                                        	<input name="txtTowerName" type="text" class="form-control" id="txtTowerName"
                                            	   ng-model="frmDealProjectDetails.txtTowerNameModel" required>
                                        </div>
                                        <div class="col-sm-1 text-center">		
											<button type="button" class="btn btn-info btnSpace" id="btnTowerAdd" ng-click="addTowerDetails(frmDealProjectDetails.txtTowerNameModel);">Add</button>	
										</div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row marginBottom5px">
                                    	<div class="col-sm-3"></div>
                                    	<div class="col-sm-6">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblTowerDetails"  ng-show="(towerDetails).length">
													<thead>
														<tr>
															<th class="firstColLeftAlign">Tower Name</th>
															<th>Utilization Percentage</th>
															<th>Remove</th>
														</tr>
													</thead>
													<tbody id="tBodyTowerDetails" >
														<tr id="{{'towerDetails_'+($index+1)}}" ng-repeat="towerDetail in towerDetails">
															<td><input name="{{'txtTowerName'+'_'+($index+1)}}" ng-model="towerDetail.towerName"  type="text" 
														class="form-control" id="{{'txtTowerName'+'_'+($index+1)}}" disabled/></td>
															<td> <input name="{{'txtTowerUtilizationPercentage'+'_'+($index+1)}}" ng-model="towerDetail.towerUtilizationPercentage"  type="text" 
														class="form-control" id="{{'txtTowerUtilizationPercentage'+'_'+($index+1)}}"/></td>
															<td><button type="button"id="{{'btnRemove'+'_'+($index+1)}}" ng-click="removeTowerDetail($index)">Remove</button></td>
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
                </div> -->
                
                
		 			<div class="row text-center">
					<div class="col-sm-12">		
						<button type="button" class="btn btn-primary btnSpace" id="btnDealSave" ng-click="saveTMDealCreationRCAndProjectDetails()" ng-disabled= "isSubmitDisable">Save</button>	
						<!-- <button type="button" class="btn btn-danger btnSpace" id="btnTowerDealCancel">Cancel</button> -->
						<button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
						<button type="button" class="btn btn-info" id="btnnext" ng-click="Next()">Next</button>
					</div>
				</div>
				
				<%-- <div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div> --%>