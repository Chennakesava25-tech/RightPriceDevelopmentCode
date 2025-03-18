				
				<div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                           <ng-form name="addTowerDtForm" id="addTowerDtForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Tower Details</label>
										<div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor"> &#9660;</a>
										</div>
									</div>
                                </div>
                                <div class="panel-body" >
                                	<div class="row marginBottom5px">
                                		<div class="col-sm-3"></div>
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Tower Name</label>
                                        <div class="col-sm-3">
                                        	<input name="txtTowerName" type="text" class="form-control" id="txtTowerName"ng-model="addTowerDtForm.txtTowerNameModel" 
                                        	ng-required = "onAdd == true" ng-class="{true: 'ng-border'} [(onAdd && addTowerDtForm.txtTowerName.$invalid)]">
											<div class="error-messages" ng-if="onAdd" ng-messages="addTowerDtForm.txtTowerName.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Tower Name</em>
											</div>
                                        </div>
                                        <div class="col-sm-1 text-center">		
											<button type="button" class="btn btn-info btnSpace" id="btnTowerAdd" ng-click="addTower(addTowerDtForm);">Add</button>	
										</div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row marginBottom5px">
                                    	<div class="col-sm-1"></div>
                                    	<div class="col-sm-12">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover"
													id="tblTowerDetails"  ng-show="(towerDetails).length">
													<thead>
														<tr>
															<th width=3%>Sr. No.</th>
															<th width=25%>Tower Name</th>
															<th width=25%>Tower Description</th>
															<th width=15%>Country</th>
															<th width=10%>Currency</th>
															<th width=15%>City</th>
															<th width=7%>City Classification </th>
															<th width=12%>Transition Periods In Months </th>
															<th>Action</th>
														</tr>
													</thead>
                                                      <tbody id="tBodyTowerDetails" >
														<tr id="{{'towerDetails_'+($index+1)}}" ng-repeat="towerDetail in towerDetails">
															<td>{{$index+1}}</td>
															<td><input name="{{'txtTowerName'+'_'+($index+1)}}" ng-model="towerDetail.towerName"  type="text" 
														class="form-control" id="{{'txtTowerName'+'_'+($index+1)}}" disabled/></td>
														
														<td> <input name="{{'txttTowerDescription'+'_'+($index+1)}}" ng-model="towerDetail.towerDescription"  type="text" 
														class="form-control" id="{{'txttTowerDescription'+'_'+($index+1)}}" ng-required = "towerDetails.length"
														 ng-class="{true: 'ng-border'} [( onSave && addTowerDtForm['txttTowerDescription'+'_'+($index+1)].$invalid)]" >
													     <div class="error-messages" ng-if="onSave" ng-messages="addTowerDtForm['txttTowerDescription'+'_'+($index+1)].$error">
												        	<em class="error help-block has-error" ng-message="required">Please enter Tower Description</em>
														 </div>
														</td>
												       <td> <select id="{{'ddlCountryName'+'_'+($index+1)}}" class="form-control" placeholder="Please select" name="{{'ddlCountryName'+'_'+($index+1)}}"" ng-change = "getCities(towerDetail.countryId,$index,towerDetail,towerDetail.cityId);"
			                                    		ng-model="towerDetail.countryId" ng-disabled="disableInputs"
			                                    		 ng-class="{true: 'ng-border'} [( onSave && addTowerDtForm['ddlCountryName'+'_'+($index+1)].$invalid)]"  ng-required = "towerDetails.length"
			                                    		ng-options="country.countryId as country.countryName for country in towerDetail.country" >
													      <option value="" selected disabled>Please select</option>
												      </select>
												            <div class="error-messages" ng-if="onSave" ng-messages="addTowerDtForm['ddlCountryName'+'_'+($index+1)].$error">
										        	          <em class="error help-block has-error" ng-message="required">Please select Country</em>
												          </div>
												      </td>
													<td> <input name="{{'txtCurrency'+'_'+($index+1)}}" ng-model="towerDetail.currency"  type="text" 
														class="form-control" id="{{'txtCurrency'+'_'+($index+1)}}" disabled/>
													</td>
													</td> 
													  <td>
													   <select id="{{'ddlCityName'+'_'+($index+1)}}" class="form-control" placeholder="Please select" name="{{'ddlCityName'+'_'+($index+1)}}"
			                                    		ng-model="towerDetail.cityId" ng-disabled="disableInputs"
			                                    		ng-class="{true: 'ng-border'} [( onSave && addTowerDtForm['ddlCityName'+'_'+($index+1)].$invalid)]"  ng-required = "towerDetails.length"
			                                    		ng-options="city.cityId as city.cityName for city in towerDetail.city |orderBy:'cityName' " ng-change = "getDescription(towerDetail.cityId,$index,towerDetail,towerDetail.countryId);"  >
													    <option value="" selected disabled>Please select</option>
												      </select>
									        	          <div class="error-messages" ng-if="onSave" ng-messages="addTowerDtForm['ddlCityName'+'_'+($index+1)].$error"">
										        	          <em class="error help-block has-error" ng-message="required">Please select City</em>
												          </div>
											            </td> 
											            <td><input name="{{'txtCityClassification'+'_'+($index+1)}}" ng-model="towerDetail.description"  type="text" 
														class="form-control" id="{{'txtCityClassificatione'+'_'+($index+1)}}" disabled />
														</td>
														<td>
														<input name="{{'txttransitionMonth'+'_'+($index+1)}}" ng-model="towerDetail.transitionMonth" ng-required = "towerDetails.length"
															type="text" class="form-control" value="{{$index+1}}" ng-pattern= "/^[0-9]+(\.[0-9]{1,2})?$/" 
															ng-class="{true: 'ng-border'}[onSave && addTowerDtForm.{{'txttransitionMonth'+'_'+($index+1)}}.$invalid]"
															checkltransitionmonth>
														<div class="error-messages" ng-if="onSave" ng-messages="addTowerDtForm['txttransitionMonth'+'_'+($index+1)].$error">
					        								<em class="error help-block has-error" ng-message="pattern">Please enter number upto 2 decimal.</em>
					        								<em class="error help-block has-error" ng-message="checkltransitionmonth">Can not be greater than 12.</em>
					        								<em class="error help-block has-error" ng-message="required">Please enter Transition Period</em>
					        							</div>
														</td>
														
														<td><button type="button"id="{{'btnRemove'+'_'+($index+1)}}"  ng-click="removeTowerDetail(addTowerDtForm,$index);">Remove</button></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                                </div>
                            </div>
                        </ng-form>
                        </div>
                    </div>
                </div>
	            <div class="row" ng-hide="isRateCardDetails">
                    <div class="col-sm-12">
                        <div class="panel-group">
                          <ng-form name="addRCDtForm" id="addTowerDtForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideRateCardDetails()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Rate Card Details</label>
										<div class="col-sm-2 textAlignRight">
											<label class="DownArrowColor textAlignRight"> &#9660;</label>
										</div>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "RateCardDetailsHidden"> 
                                  <div class="row marginBottom5px">
                                   <label class="control-label col-sm-3 textAlignRight required-Field">Tower Name</label>
			                            <div class="col-sm-2 ">		
			                            	<select id="ddlTowerName" class="form-control" placeholder="Please select" name="ddlTowerName"
	                                           		ng-model="addRCDtForm.towerName" ng-options="rc.dealTowerId as rc.dealTowerName for rc in towerData" ng-change = "getDealRate(addRCDtForm.towerName,towerData);"
	                                           		ng-disabled="disableInputs" required
	                                           		ng-class="{true: 'ng-border'} [(onAddRow && addRCDtForm.ddlTowerName.$invalid)]">
													<option value="" disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if="onAddRow" ng-messages="addRCDtForm.ddlTowerName.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Tower Name</em>
											</div>
										</div>		
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Rate Card Name</label>
			                            <div class="col-sm-3 ">			                           
			                            	<select id="ddlAddrateCardName" class="form-control" placeholder="Please select" name="ddlrateCardName"
	                                           		ng-model="addRCDtForm.rateCard_Name" ng-options="rc as rc.rcName for rc in rateCardName" 
	                                           		ng-disabled="disableInputs" required
	                                           		ng-change="getData(addRCDtForm.rateCard_Name)";
	                                           		addRCDtForm.rateCard_Name="getData(rcId.rateCardName,rcEndDate.rateCardName);"
	                                           		ng-class="{true: 'ng-border'} [(onAddRow && addRCDtForm.ddlrateCardName.$invalid)]">
													<option value="" disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if="onAddRow" ng-messages="addRCDtForm.ddlrateCardName.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Rate Card Name</em>
											</div>
										</div>								
										<div class="col-sm-2 textAlignRight">		
										   <button type="button" class="btn btn-info btnSpace" id="btnDetailsAdd"
										    ng-click="addRow(addRCDtForm.towerName,addRCDtForm.rateCard_Name,addRCDtForm)" ng-disabled="disableInputs" ">Add RateCard</button>
										</div>
									</div>                                
                                	<div class="divEmptyThrice"></div>	
                                    <div class="row">
                                    <div class="col-sm-3"></div>
	                                    <div class="col-sm-12 ">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails">
													<thead>
														<tr>
															<th  width=3% >Sr. No.</th>
															<th width=25%>Tower Name</th>
															<th width=10%>Rate Card Id</th>
															<th width=25%>Rate Card Name</th>
															<th width=15%>Start Date <br>(dd/mm/yyyy)</br></th>
															<th width=15%>End Date<br>(dd/mm/yyyy)</br></th>
															<th width=7%>Action</th>
														</tr>
													</thead>
													<tbody id="tBodyRateCardDetails" >
													
													
													<tr id="{{'rateCard'+'_'+($index+1)}}" ng-repeat="rateCard in rateCardDetails">													
														<td>{{$index+1}}</td>
														
														<td><input name="{{'txtTowerName'+'_'+($index+1)}}" ng-model="rateCard.dealTowerName"  type="text" ng-required = "rateCardDetails.length"
														class="form-control" id="{{'txtTowerName'+'_'+($index+1)}}" disabled ></td>														
														
														<td>
															<a style="cursor:pointer" id="{{'txtRateCardId'+'_'+($index+1)}}"  name="{{'txtRateCardId'+'_'+($index+1)}}" ng-model="rateCard.rcId"  
															type="text" ng-required = "rateCardDetails.length"  class="form-control" ng-click="showRCDetails(rateCard.rcId)">{{rateCard.rcId}}</a> 
														 </td>
														
														<td><input name="{{'txtRateCardName'+'_'+($index+1)}}" ng-model="rateCard.rcName"  type="text" ng-required = "rateCardDetails.length"
														class="form-control" id="{{'txtRateCardName'+'_'+($index+1)}}" disabled></td>
														
														<td><input name="{{'txtRateCardstartdate'+'_'+($index+1)}}" ng-model="rateCard.startdate"  type="text" ng-required = "rateCardDetails.length"
														class="form-control" id="{{'txtRateCardstartdate'+'_'+($index+1)}}" disabled></td>
														
														<td><input name="{{'txtRateCardenddate'+'_'+($index+1)}}" ng-model="rateCard.enddate"  type="text" ng-required = "rateCardDetails.length"
														class="form-control" id="{{'txtRateCardenddate'+'_'+($index+1)}}" disabled></td>
																			
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
                             </ng-form>
                            </div>
                        </div>
                   </div>
                 </ng-form>
                   <div class="col-sm-5"></div>
                    <div>		
						<button type="button" class="btn btn-primary btnSpace" id="btnDealSave" ng-disabled="isCurrStatus" ng-click="save(addTowerDtForm,addRCDtForm);">Save</button>	
<!-- 						<button type="button" class="btn btn-danger btnSpace" id="btnTowerDealCancel" ng-click="cancel()">Cancel</button>
 -->						<button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
						<button type="button" class="btn btn-info" id="btnnext" ng-click="Next()">Next</button>
				  </div>
				  </div>
