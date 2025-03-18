
<div class="row">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info">
				<div class="panel-heading panelHeadingStyle ">
					<div class="row ">
						<label class="control-label col-sm-10 ">FP Deal Project
							Specific Cost</label>
					</div>
				</div>
				<div class="panel-body">
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Country</label>
						<div class="col-sm-3">
							<select id="ddlCountry" class="form-control"
								placeholder="Please select" name="ddlCountry"
								ng-model="ddlCountryModel" required>
								<option value="" selected disabled>Please select</option>
								<option value="1">US</option>
								<option value="2">UK</option>
							</select>
						</div>
						<label
							class="control-label col-sm-3 textAlignRight required-Field">City</label>
						<div class="col-sm-3">
							<select id="ddlCity" class="form-control"
								placeholder="Please select" name="ddlCity"
								ng-model="ddlCityModel" required>
								<option value="" selected disabled>Please select</option>
								<option value="1">Mumbai</option>
								<option value="2">Pune</option>
								<option value="3">Chennai</option>
							</select>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Tower</label>
						<div class="col-sm-3">
							<select id="ddlTower" class="form-control"
								placeholder="Please select" name="ddlTower"
								ng-model="ddlTowerModel" required>
								<option value="" selected disabled>Please select</option>
								<option value="1">Tower 1</option>
								<option value="2">Tower 2</option>
								<option value="3">Tower 3</option>
							</select>
						</div>
					</div>
					<div class="divEmptyThrice"></div>
					<div class="row marginBottom5px">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<div class="table-responsive">
								<table
									class="table clsTable table-striped table-bordered table-hover table-condensed "
									id="tblCostTabel1">
									<tbody id="tBodyCostTabel1">
										<tr>
											<th class="width60per">Penalty %</th>
											<td class="width40per">10%</td>
										</tr>
										<tr>
											<th class="width60per">Onsite Facility Cost %</th>
											<td class="width40per">20%</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="divEmptyThrice"></div>
					<div class="row marginBottom5px">
						<div class="col-sm-12">
							<div class="table-responsive">
								<table
									 class="table tblDealCre table-borderless table-condensed" id="tblProjectCost">
									<thead>
										<tr>
											<th class="tdWidth5Per thBorder">Sr.No.</th>
											<th class="width12per thBorder">Particulars</th>
											<th class="width12per thBorder">Rate</th>
											<th class="padLeft colRightSection" colspan="3"><a class="btn btn-default ancBtnWidthSum" href="#" tabindex="1">Summary</a></th>											<th class="colRightSection" colspan="2"><a id="anc2017" class="btn btn-default" href="#" tabindex="2" >2017</a></th>
		                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="3">2018</a></th>
		                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="4">2019</a></th>
		                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="5">2020</a></th>
		                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="6">2021</a></th>
										</tr>
					                    
									</thead>
									<tbody> 
		                              	<tr class="sectionHeading">
		                          			<td colspan="17"><strong>Project Specific Cost</strong></td>	                                    
		                              	</tr>
                                   	 	<tr class="bgBlackColor">
		                          			<td colspan="3"></td>	                                    
		                                    <td class="width5per">Jan</td>
		                                    <td class="width5per">Feb</td>
		                                    <td class="width5per">Mar</td>
		                                    <td class="width5per">Apr</td>
		                                    <td class="width5per">May</td>
		                                    <td class="width5per">Jun</td>
		                                    <td class="width5per">Jul</td>
		                                    <td class="width5per">Aug</td>
		                                    <td class="width5per">Sep</td>
		                                    <td class="width5per">Oct</td>
		                                    <td class="width5per">Nov</td>
		                                    <td class="width5per">Dec</td>
		                                    <td class="thWidth4Per">Total</td>
                                    	</tr> 
                                    </tbody>  
									<tbody id="tBodyProjectCost">
										<tr>
											<td class="colSection">1</td>
											<td class="colSection">Laptops </td>
											<td class="colSection"><input type="text"
												class="form-control" id="txtProjectCostRateRow1"
												name="txtProjectCostRateRow1" value="950"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtProjectCostJanRow1"
												name="txtProjectCostJanRow1" value="2.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtProjectCostFebRow1"
												name="txtProjectCostFebRow1"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostMarRow1"
												name="txtProjectCostMarRow1"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostAprRow1"
												name="txtProjectCostAprRow1" value="2.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostMayRow1"
												name="txtProjectCostMayRow1" ></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostJunRow1"
												name="txtProjectCostJunRow1" value="1.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostJulRow1"
												name="txtProjectCostJulRow1"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostAugRow1"
												name="txtProjectCostAugRow1"  value="2.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostSepRow1"
												name="txtProjectCostSepRow1"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostOctRow1"
												name="txtProjectCostOctRow1"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostNovRow1"
												name="txtProjectCostNovRow1"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostDecRow1"
												name="txtProjectCostDecRow1" value="1.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostTotalRow1"
												name="txtProjectCostTotalRow1" value="7600.00"></td>
										</tr>
										<tr>
											<td class="colSection">2</td>
											<td class="colSection">Data Cards</td>
											<td class="colSection"><input type="text"
												class="form-control" id="txtProjectCostRateRow2"
												name="txtProjectCostRateRow2" value="2.56"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtProjectCostJanRow2"
												name="txtProjectCostJanRow2" ></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtProjectCostFebRow2"
												name="txtProjectCostFebRow2" value="1.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostMarRow2"
												name="txtProjectCostMarRow2" value="2.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostAprRow2"
												name="txtProjectCostAprRow2"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostMayRow2"
												name="txtProjectCostMayRow2"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostJunRow2"
												name="txtProjectCostJunRow2"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostJulRow2"
												name="txtProjectCostJulRow2" value="1.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostAugRow2"
												name="txtProjectCostAugRow2"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostSepRow2"
												name="txtProjectCostSepRow2"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostOctRow2"
												name="txtProjectCostOctRow2"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostNovRow2"
												name="txtProjectCostNovRow2" value="2.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostDecRow2"
												name="txtProjectCostDecRow2"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostTotalRow2"
												name="txtProjectCostTotalRow2" value="15.36"></td>
										</tr>
										<tr>
											<td class="colSection">3</td>
											<td class="colSection">Data Card monthly Recurring </td>
											<td class="colSection"><input type="text"
												class="form-control" id="txtProjectCostRateRow3"
												name="txtProjectCostRateRow3" value="7.36"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtProjectCostJanRow3"
												name="txtProjectCostJanRow3"  value="1.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtProjectCostFebRow3"
												name="txtProjectCostFebRow3"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostMarRow3"
												name="txtProjectCostMarRow3"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostAprRow3"
												name="txtProjectCostAprRow3" value="2.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostMayRow3"
												name="txtProjectCostMayRow3"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostJunRow3"
												name="txtProjectCostJunRow3"  value="3.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostJulRow3"
												name="txtProjectCostJulRow3"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostAugRow3"
												name="txtProjectCostAugRow3"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostSepRow3"
												name="txtProjectCostSepRow3"  value="1.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostOctRow3"
												name="txtProjectCostOctRow3"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostNovRow3"
												name="txtProjectCostNovRow3" value="2.00"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostDecRow3"
												name="txtProjectCostDecRow3"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtProjectCostTotalRow3"
												name="txtProjectCostTotalRow3" value="66.24"></td>
										</tr>
									</tbody>
									<tbody> 
		                              	<tr class="sectionHeading">
		                          			<td colspan="17"><strong>Total Project Specific Cost and Onsite Facility Cost</strong></td>	                                    
		                              	</tr>
                                   	 	<tr class="bgBlackColor">
		                          			<td colspan="3"></td>	                                    
		                                    <td class="width5per">Jan</td>
		                                    <td class="width5per">Feb</td>
		                                    <td class="width5per">Mar</td>
		                                    <td class="width5per">Apr</td>
		                                    <td class="width5per">May</td>
		                                    <td class="width5per">Jun</td>
		                                    <td class="width5per">Jul</td>
		                                    <td class="width5per">Aug</td>
		                                    <td class="width5per">Sep</td>
		                                    <td class="width5per">Oct</td>
		                                    <td class="width5per">Nov</td>
		                                    <td class="width5per">Dec</td>
		                                    <td class="thWidth4Per">Total</td>
                                    	</tr> 
                                    </tbody>  
                                    <tbody id="tBodyProject&OnsiteCost">
										<tr>
											<td class="colSection">1</td>
											<td colspan="2" class="colSection">Project Specific Cost</td>
											<!-- <td class="colSection"><input type="text"
												class="form-control" id="txtRateRow1" name="txtRateRow1"
												ng-model="txtRateRow1Model" required></td> -->
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtJanRow1"
												name="txtJanRow1" value="4810.24"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control colSection" id="txtFebRow1"
												name="txtFebRow1" value="1202.56"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtMarRow1" name="txtMarRow1">
											</td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtAprRow1" name="txtAprRow1">
											</td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtMayRow1" name="txtMayRow1"
												value="1202.56"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtJunRow1" name="txtJunRow1">
											</td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtJulRow1" name="txtJulRow1">
											</td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtAugRow1" name="txtAugRow1">
											</td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtSepRow1" name="txtSepRow1"
												value="1202.56"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtOctRow1" name="txtOctRow1">
											</td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtNovRow1" name="txtNovRow1">
											</td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtDecRow1" name="txtDecRow1"
												value="1202.56"></td>
											<td class="colRightSection width5per"><input type="text"
												class="form-control" id="txtTotalRow1" name="txtTotalRow1"
												value="9620.48"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="divEmptyThrice"></div>
					<div class="row text-center">
						<div class="col-sm-12">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnSave">Save</button>
							<button type="button" class="btn btn-danger btnSpace"
								id="btnCancel">Cancel</button>
							<button type="button" class="btn btn-info btnSpace"
								id="btnClientPrev" ng-click="Prev()">Prev</button>
							<button type="button" class="btn btn-info" id="btnClientNext"
								 ng-click="Next()">Next</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
