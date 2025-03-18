
<div class="row">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Deal Creation -
							Details</label>
					</div>
				</div>
				<div class="panel-body">
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Name
								of the Customer</label>
							<div class="col-sm-3">
							
								<Select id="ddlName" class="form-control"
									placeholder="Please select" name="ddlName" ng-change="getDealDetails(frmDeal.customerNameModel)" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlName.$invalid)]"
									ng-model="frmDeal.customerNameModel" ng-options=" cvi.customerId as cvi.customer.customerName for cvi in customer | orderBy:'customer.customerName'" required>
									<option value="" selected >Please select</option>
									<!-- <option value="1">Amex</option>
									<option value="2">Humana</option> -->
								</Select>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlName.$error">
								     	<em class="error help-block has-error" ng-message="required">Please select Customer Name</em>
								</div>
							</div>
							<label class="control-label col-sm-3 textAlignRight "> </label>
							<div class="col-sm-4">
								<!-- <button save-click="frmDeal" type="button" class="btn btn-primary btnSpace" id="btnAddNewVersion" ng-click="getVersionName(frmDeal)">Copy As New Version</button> -->
							</div>
						</div>
						<div class="row marginBottom5px">						
							<label class="control-label col-sm-2 textAlignRight ">Deal</label>
							{{crmDealId.crmDealDetailCust}}
							<div class="col-sm-3 tooltip1">
							
								<Select id="ddlDeal" name="ddlDeal" class="form-control" ng-options="cvi.crmDealId as cvi.crmDealId for cvi in crmDealDetailCust | orderBy:'crmDealId'"  ng-class="{true: 'ng-border'} [(update && frmDeal.ddlDeal.$invalid)]"
									placeholder="Please select" name="ddlDeal"
									required ng-model="frmDeal.DealModel" ng-change="getVersion(frmDeal.DealModel)">
									<option value="" selected>Please select</option>
									<!-- <option value="1">Deal 1</option>
									<option value="2">Deal 2</option> -->
								</Select>
								<span class="tooltiptext"> Note: Legacy MS CRM deals migrated to salesforce have deal ID series prefixed with "4440". So such deals can be searched with last 6 digits. </span>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlDeal.$error">
								     	<em class="error help-block has-error" ng-message="required">Please select Deal</em>
								</div>
							</div>
							
							<div class="col-sm-1" >
								<a style="cursor:pointer" data-toggle="modal" ng-click ="getDealSFInfo(frmDeal.DealModel)" ng-if="frmDeal.DealModel != null" data-target="#modalRCFinalize" ><strong><u>i</u></strong></a>
							</div>
							
			<div id="modalRCFinalize" class="modal fade" role="dialog">
				<div class="modal-dialog" >
					<div class="modal-content panel-heading panelHeadingStyle">
						<div class="modal-header bootstrap-dialog-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title" style="text-align-last: center"> Sales Force Details</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-sm-12">
									<div class="row marginBottom5px">
												<label class="control-label col-sm-5 textAlignRight ">SF Account ID
												</label>
												<div class="col-sm-5">
													<input type="text" class="form-control"	ng-model="frmDeal.sfAccntId"
														id="txtsfAccntId" name="txtsfAccntId" disabled>
												</div>
										</div>
										<div class="row marginBottom5px">
												<label class="control-label col-sm-5 textAlignRight ">SF Account Name
												</label>
												<div class="col-sm-5">
													<input type="text" class="form-control"	ng-model="frmDeal.sfAccntNm"
														id="txtsfAccntNm" name="txtsfAccntNm" disabled>
												</div>
										</div>
										<div class="row marginBottom5px">
												<label class="control-label col-sm-5 textAlignRight ">SF IRIS Account ID
												</label>
												<div class="col-sm-5">
													<input type="text" class="form-control"	ng-model="frmDeal.sfIrisId"
														id="txtsfIrisId" name="txtsfIrisId" disabled>
												</div>
										</div>
										<div class="row marginBottom5px">
												<label class="control-label col-sm-5 textAlignRight ">SF IRIS Account Name  
												</label>
												<div class="col-sm-5">
													<input type="text" class="form-control"	ng-model="frmDeal.sfIrisAccNm"
														id="txtsfIrisAccNm" name="txtsfIrisAccNm" disabled>
												</div>
										</div>
										<div class="row marginBottom5px">
												<label class="control-label col-sm-5 textAlignRight ">IRIS code
												</label>
												<div class="col-sm-5">
													<input type="text" class="form-control"	ng-model="frmDeal.sfIrisCode"
														id="txtDealStartDate" name="txtDealStartDate" disabled>
												</div>
										</div>
											</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
							
							<label class="control-label col-sm-2 textAlignRight ">Version</label>
							
							
							<div class="col-sm-3"> <%-- vd.dealVersion as vd.dealVersion for vd in versionDetail   vd as vd.dealVersion for vd in versionDetail --%>
								<Select id="ddlVersion" class="form-control" ng-options="vd.rpDealVersionId as vd.dealVersion for vd in versionDetail| orderBy:'dealVersion'" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlVersion.$invalid)]"
									placeholder="Please select" name="ddlVersion" ng-change="putVersion(frmDeal.versionModel)" 
									ng-model="frmDeal.versionModel" required>
									<option value="" selected >Please select</option>
									<!-- <option value="1">Version 1</option>
									<option value="2">Version 2</option> -->
								</Select>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlVersion.$error">
								     	<em class="error help-block has-error" ng-message="required">Please select Version</em>
								</div>
							</div>
							
							
							
						</div>
						<fieldset ng-disabled="isDisabled">
						<fieldset disabled>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Address
							</label>
							<div class="col-sm-3">
								<input name="txtAddress " type="text" class="form-control"
									id="txtAddress">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Deal
								Description </label>
							<div class="col-sm-3">
								<input name="txtDealDescription" type="text" ng-model="frmDeal.descriptionModel"
									class="form-control" id="txtDealDescription">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Deal
								Status</label>
							<div class="col-sm-3">
								<input name="txtDealStatus" type="text"  class="form-control" ng-model="frmDeal.dealStatusModel"
									id="txtDealStatus">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Sales
								Spoc </label>
							<div class="col-sm-3">
								<input name="txtSalesSpoc " type="text" class="form-control" ng-model="frmDeal.salesSpocModel"
									id="txtSalesSpoc">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Deal
								TCV</label>
							<div class="col-sm-3">
								<input name="txtDealTCV" type="text" class="form-control" ng-model="frmDeal.dealTCVModel"
									id="txtDealTCV">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Currency
								 </label>
							<div class="col-sm-3">
								<input name="txtDealPriority" type="text" class="form-control" ng-model="frmDeal.currencyModel"
									id="txtDealPriority">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Deal
								Start Date(DD/MM/YYYY)</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" ng-model="frmDeal.dealStartDateModel"
									 id="txtDealStartDate"name="txtDealStartDate">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Deal
								End Date(DD/MM/YYYY)</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" ng-model="frmDeal.dealEndDateModel"
									id="txtDealEndDate" name="txtDealEndDate" >
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Duration
							</label>
							<div class="col-sm-3">
							<input name="txtStage" type="text" class="form-control" ng-model="frmDeal.durationModel"
									id="txtStage">
								
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Stage
							</label>
							<div class="col-sm-3">
								<input name="txtCurrency" type="text" class="form-control"  ng-model="frmDeal.stageModel"
									id="txtCurrency">
							</div>
						</div>
						
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Percentage
								Close</label>
							<div class="col-sm-3">
								<input name="txtPercentageClose" type="text" ng-model="frmDeal.percentageCloseModel"
									class="form-control" id="txtPercentageClose"  disabled>
							</div>
							
							<label class="control-label col-sm-3 textAlignRight ">Priority</label>
							<div class="col-sm-3">
								<input name="txtCurrency" type="text" class="form-control" id="txtCurrency"  ng-model="frmDeal.dealPriorityModel" >
								<!-- <option value="" selected >Please select</option>
												<option value="1">India</option>
												<option value="2">US</option>
												<option value="3">UK</option>
												</select> -->
							</div>
						</div>
						
					</fieldset>	
					<!-- <div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Onsite Hrs per day</label>
							<div class="col-sm-3" >
								<input name="txtOnsiteHrs" type="text" class="form-control" ng-model="frmDeal.onsiteHoursModel" ng-class="{true: 'ng-border'} [(update && frmDeal.txtOnsiteHrs.$invalid)]"
									id="txtOnsiteHrs" ng-pattern="/^[0-9]*$/"  ng-maxlength ="5" required>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtOnsiteHrs.$error">
								     	<em class="error help-block has-error" ng-message="required">Please enter Onsite Hrs per day</em>
								     	<em class="error help-block has-error" ng-message="pattern">Please enter number only</em>
								     	<em class="error help-block has-error" ng-message="maxlength">Onsite Hrs per day can't exceed 10 digits</em>
								</div>	
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Offshore Hrs(Client) per day</label>
							<div class="col-sm-3">
								<input name="txtOffshoreHrs" type="text" class="form-control" ng-model="frmDeal.offShoreHoursModel" ng-class="{true: 'ng-border'} [(update && frmDeal.txtOffshoreHrs.$invalid)]"
									id="txtOffshoreHrs" ng-pattern="/^[0-9]*$/"  ng-maxlength ="5" required>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtOffshoreHrs.$error">
								     	<em class="error help-block has-error" ng-message="required">Please enter Offshore Hrs(Client) per day</em>
								     	<em class="error help-block has-error" ng-message="pattern">Please enter number only</em>
								     	<em class="error help-block has-error" ng-message="maxlength">Offshore Hrs(Client) per day can't exceed 10 digits</em>
								</div>	
							</div>
						</div>	 -->
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Deal Type</label>
						<div class="col-sm-3">
						{{frmDeal.dealTypeModel}}
							<input name="txtDealType" type="text" class="form-control" ng-model="dealTypeModel"
									id="txtDealType" disabled/>
							<!-- <select name="ddlDealType" class="form-control" ng-model="frmDeal.dealTypeModel"
								placeholder="Please select" id="ddlDealType"  ng-options="dt.id as dt.name for dt in dealType" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlDealType.$invalid)]"
								ng-model="frmDeal.dealTypeModel" required>
								<option value="" selected >Please select</option> -->
								<!-- <option value="1">Fixed Price</option>
								<option value="2">T & M</option> 
							</select>-->
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlDealType.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select Deal Type</em>
							</div>
						</div>
						<label
							class="control-label col-sm-3 textAlignRight required-Field">Project
							Type </label>
						<div class="col-sm-3">
							<Select id="ddlFPType" class="form-control" ng-options="fpt.id as fpt.name for fpt in fpType" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlFPType.$invalid)]"
								placeholder="Please select" name="ddlFPType"
								ng-model="frmDeal.fpTypeModel" required>
								<option value="" selected >Please select</option>
								<!-- <option value="1">Development</option>
								<option value="2">Mentanance</option> -->
							</Select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlFPType.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select FP Type</em>
							</div>
						</div>
					</div>
					
					<div class="row marginBottom5px" >						
						<label class="control-label col-sm-2 textAlignRight required-Field" >Country
							</label>
						<!-- ng-options="rc as rc.rcName for rc in rateCardName" "con.countryId as con.country.countryName for con in country"-->
						<!-- "rate.rcId as rate.rcName for rate in rateCards"	 -->						
						<div class="col-sm-3">							
							<Select id="dlcountry" class="form-control"
										placeholder="Please select" name="dlcountry" ng-change="getCities(frmDeal.countryNameModel)" ng-class="{true: 'ng-border'} [(update && frmDeal.dlcountry.$invalid)]"
										ng-model="frmDeal.countryNameModel" ng-options="con.countryId as con.countryName for con in country| orderBy:'countryName'" required>
										<option value="" selected >Please select</option>										
							</Select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlcountry.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select Country</em>
								</div>
						</div>
						<label class="control-label col-sm-3 textAlignRight required-Field ">City
							</label>
						<div class="col-sm-3">							
							<Select id="dlcity" class="form-control"
										placeholder="Please select" name="dlcity"  ng-class="{true: 'ng-border'} [(update && frmDeal.dlcity.$invalid)]"
										ng-model="frmDeal.cityNameModel" ng-options="cit.cityId as cit.cityName for cit in city| orderBy:'cityName'"  required>
										<option value="" selected >Please select</option>
										
							</Select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlcity.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select City</em>
								</div>
						</div>
					</div>
					
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Whether
							The Deal is<br> New Or Renewal?
						</label>
						<div class="col-sm-3">
							<select id="ddlNewOrRenewal" class="form-control" ng-options="nr.id as nr.name for nr in dealNewOrRenewal" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlNewOrRenewal.$invalid)]"
								placeholder="Please select" name="ddlNewOrRenewal" ng-model="frmDeal.dealNewOrRenewalModel" required
								ng-change="setValidation(frmDeal.dealNewOrRenewalModel)">
								<option value="" selected >Please select</option>
								<!-- <option value="1">New</option>
								<option value="2">Renewal</option> -->
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlNewOrRenewal.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select the Deal type</em>
							</div>
						</div>
						
						<label class="control-label col-sm-3 textAlignRight required-Field" ng-hide="hideOldDealId">Old Deal Id</label>
							<div class="col-sm-3" >
								<!-- <input name="txtOldDealId" type="text" class="form-control" id="txtOldDealId " ng-model="frmDeal.oldDealIdModel" required
								ng-hide="hideOldDealId" 
								ng-class="{true: 'ng-border'} [(isDealRenewal && frmDeal.txtOldDealId.$invalid)]"> -->
								<!-- cvi.crmDealId as cvi.crmDealId for cvi in crmDealDetail -->
								<Select id="txtOldDealId" class="form-control"
										placeholder="Please select" name="txtOldDealId" ng-class="{true: 'ng-border'} [( isDealRenewal && frmDeal.txtOldDealId.$invalid )]"
										ng-hide="hideOldDealId"
										ng-model="frmDeal.oldDealIdModel" ng-options="odi.crmDealId as odi.crmDealId for odi in oldDealId  " ng-required>
										<option value="" selected >Please select</option>										
								</Select>
								
								<div class="error-messages" ng-if="isDealRenewal" ng-messages="frmDeal.txtOldDealId.$error">
						     		<em class="error help-block has-error" ng-message="required">Please provide Old Deal Id</em>
								</div>
							</div>
						
											
						
					</div>
					
					
					
					<div class="row marginBottom5px">
					
					
					<label style="visibility:hidden"
							class="control-label col-sm-2 textAlignRight required-Field">Whether
							the Project<br> is Agile Based?
						</label>
						<div class="col-sm-3" style="visibility:hidden">
							<select id="ddlAgileBased" class="form-control" ng-options="iab.id as iab.name for iab in isAgileBased" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlNewOrRenewal.$invalid)]"
								placeholder="Please select" name="ddlAgileBased" ng-model="frmDeal.isAgileBasedModel" >
								<option value="" selected >Please select</option>
								<!-- <option value="1">Yes</option>
								<option value="2">No</option> -->
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlAgileBased.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select the Project is Agile</em>
							</div>
						</div>
					
							
							<label class="control-label col-sm-3 textAlignRight required-Field" ng-hide="hideOldProjectlId" >Old Project Id</label>
							<div class="col-sm-3" ng-hide="hideOldProjectlId" >
								<input name="txtProjectId" type="text" class="form-control" id="txtProjectId" ng-model="frmDeal.projectIdModel" 
								ng-class="{true: 'ng-border'} [(isDealRenewal && frmDeal.txtProjectId.$invalid)]" ng-required>
								
								<div class="error-messages" ng-if="isDealRenewal" ng-messages="frmDeal.txtProjectId.$error">
						     		<em class="error help-block has-error" ng-message="required">Please provide Old Project Id</em>
								</div>
								
							</div>
						</div>
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field" style="visibility:hidden">Onsite
							Facility</label>
						<div class="col-sm-3" style="visibility:hidden">
							<select id="ddlOnsiteFacility" class="form-control" ng-options="iof.id as iof.name for iof in isSyntelOnsiteFacilityUsed" 
								ng-class="{true: 'ng-border'} [(update && frmDeal.ddlOnsiteFacility.$invalid)]"
								placeholder="Please select" name="ddlOnsiteFacility" ng-model="frmDeal.onsiteFacilityModel" required>
								<option value="" selected >Please select</option>
								<!-- <option value="1">Client</option>
								<option value="2">Syntel</option> -->
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlOnsiteFacility.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select Onsite Facility</em>
							</div>
						</div>
						<label style="visibility:hidden"
							class="control-label col-sm-3 textAlignRight required-Field">Is
							Bizops Involved</label>
						<div class="col-sm-3" style="visibility:hidden">
							<input type="checkbox" name="cbxIsBizopsInvolved" ng-model="frmDeal.isBizopsModel"
								id="cbxIsBizopsInvolved" class="margingRightChkBx">
						</div>
					</div>
					<div class="row marginBottom5px">
						<label style="visibility:hidden"
							class="control-label col-sm-2 textAlignRight required-Field">Risk
							Category </label>
						<div class="col-sm-3" style="visibility:hidden">
							<Select id="ddlRiskCategory" class="form-control"  ng-options="rc.id as rc.name for rc in riskCategoryId"
								ng-class="{true: 'ng-border'} [(update && frmDeal.ddlRiskCategory.$invalid)]"
								placeholder="Please select" name="ddlRiskCategory" ng-model="frmDeal.riskCategoryModel" >
								<option value="" selected >Please select</option>
								<!-- <option value="1">A</option>
								<option value="2">B</option>
								<option value="3">C</option>
								<option value="4">D</option> -->
							</Select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlRiskCategory.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select Risk Category</em>
							</div>
						</div>
						<label
							class="control-label col-sm-3 textAlignRight">Include 
							in Head Count Estimations? </label>
						<div class="col-sm-3">
							<input type="checkbox" name="cbxHeadCountEstimations" ng-model="frmDeal.isIncludeInHeadCount"
								id="cbxHeadCountEstimations" class="margingRightChkBx">
						</div>
					</div>
					<div class="row marginBottom5px">
                      	<label class="control-label col-sm-2 textAlignRight required-Field">Industry</label>
                       	<div class="col-sm-3">
                            <select id="ddlIndustry" class="form-control" placeholder="Please select" name="ddlIndustry" disabled
                               		ng-model="frmDeal.ddlIndustryModel" ng-required ng-options="it.id as it.name for it in industryType">
								<option value="" selected >Please select</option>
								<!-- <option value="1">IT</option>
								<option value="2">KPO</option> -->
							</select>
						</div>	
                        <!-- <label class="control-label col-sm-3 textAlignRight required-Field">LOB </label>
                        <div class="col-sm-3">
                       		<select id="ddlLOB" class="form-control" placeholder="Please select" name="ddlLOB"
                       			ng-class="{true: 'ng-border'} [(update && frmDeal.ddlLOB.$invalid)]"
                				ng-model="frmDeal.ddlLOBModel" ng-options="mlob.lobId as mlob.lobCode for mlob in masterLob| orderBy:'lobCode'" ng-required >
								<option value="" selected >Please select</option>
							
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlLOB.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select LOB</em>
							</div>
						</div> -->	
						<label class="control-label col-sm-3 textAlignRight ">RBU
								Type </label>
							<div class="col-sm-3">
								<input name="txtrbuType " type="text" class="form-control" ng-model="frmDeal.rbuTypeModel" disabled
									id="txtrbuType">
							</div>
						
						
						
						
					</div>
					<!-- <div class="row marginBottom5px">
						<label class="control-label col-sm-2 textAlignRight ">Penalty Percent</label>
						<div class="col-sm-3">
							<input name="txtPenaltyPercent" type="text" class="form-control" ng-model="frmDeal.penaltyPercent"
									id="txtPenaltyPercent" required>
						</div>
					</div> -->
					<!-- <div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Is Manual Deal </label>
						<div class="col-sm-3">
							<input type="checkbox" name="cbxIsManualDeal" ng-model="frmDeal.isManualDeal"
								id="cbxIsManualDeal" class="margingRightChkBx">
						</div>
					</div> -->
					<!-- <div class="divEmptyThrice"></div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-10 "><h5>
								<b>Risk Based Questionniare</b>
							</h5></label>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">1)
							Is the project delivery SLA based?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">2)
							What is the maximum Service Level Credit (SLC) / Penalty?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns1" id="txtRiskAns1" class="form-control " rows="2"
								ng-model="frmDeal.riskAns1Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns1.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns1.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns2" id="txtRiskAns2" class="form-control " rows="2"
								ng-model="frmDeal.riskAns2Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns2.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns2.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">3)What
							is the frequency of the SLC / Penalty (e.g. Calculations done
							Monthly, Quarterly, Yearly)? Please elaborate </label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">4)
							Has the SLC / Penalty already been agreed with the customer? </label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns3" id="txtRiskAns3" class="form-control" rows="2"
								ng-model="frmDeal.riskAns3Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns3.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns3.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns4" id="txtRiskAns4" class="form-control" rows="2"
								ng-model="frmDeal.riskAns4Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns4.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns4.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">5)
							Is there provision for an Earn Back of SLC / Penalty? </label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">6)
							Describe briefly the SLC / Penalty and Earn Back being
							proposed/agreed </label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns5" id="txtRiskAns5" class="form-control " rows="2"
								ng-model="frmDeal.riskAns5Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns5.$invalid)]"> 
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns5.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns6" id="txtRiskAns6" class="form-control " rows="2"
								ng-model="frmDeal.riskAns6Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns6.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns6.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">7)
							What is the probability of the Service Level Credit / Penalty
							having to be paid by Syntel?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">8)
							What is the proposed savings to customer?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns7" id="txtRiskAns7" class="form-control " rows="2"
								ng-model="frmDeal.riskAns7Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns7.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns7.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAns8" id="txtRiskAns8" class="form-control " rows="2"
								ng-model="frmDeal.riskAns8Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAns8.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAns8.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-5 textAlignLeft required-Field">9)
							Does the Project involve any automation / use of SyntBots?</label> <input
							type="checkbox" name="cbxRiskAns9" id="cbxRiskAns9" ng-model="frmDeal.riskAns9Model"
							class="margingRightChkBx">
					</div> -->
					<input type="hidden" id="txtVersionName" name="txtVersionName" ng-model="frmDeal.VersionNameModel">
					<div class="divEmptyThrice"></div>
					<div class="divEmptyThrice"></div>
					
										
					<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight required-Field">Synbots </label>
									<div class="col-sm-3">							
										<Select id="dlsynbots" class="form-control"
													placeholder="Please select" name="dlsynbots" ng-class="{true: 'ng-border'} [(update && frmDeal.dlsynbots.$invalid)]"
													ng-model="frmDeal.synbotsModel" ng-options="nr.id as nr.name for nr in dealTMsynboots" required>
													<option value="" selected >Please select</option>										
										</Select>
												<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlsynbots.$error">
											     		<em class="error help-block has-error" ng-message="required">Please select</em>
												</div>
									</div>
							<label class="control-label col-sm-3 textAlignRight required-Field">Cyber Security</label>
									<div class="col-sm-3">							
										<Select id="dlSecurity" class="form-control"
													placeholder="Please select" name="dlSecurity" ng-class="{true: 'ng-border'} [(update && frmDeal.dlSecurity.$invalid)]"
													ng-model="frmDeal.securitymodel" ng-options="nd.id as nd.name for nd in dealTMcyberSecurity" required>
													<option value="" selected >Please select</option>										
										</Select>
											<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlSecurity.$error">
										     		<em class="error help-block has-error" ng-message="required">Please select</em>
											</div>
									</div>
						</div>
						<div class="row marginBottom5px">
								<label class="control-label col-sm-2 textAlignRight required-Field">Industry Solution </label>
										<div class="col-sm-3">							
											<Select id="dlsolution" class="form-control"
														placeholder="Please select" name="dlsolution" ng-class="{true: 'ng-border'} [(update && frmDeal.dlsolution.$invalid)]"
														ng-model="frmDeal.solutionModel" ng-options="nr.id as nr.name for nr in dealTMsolution" required>
														<option value="" selected >Please select</option>										
											</Select>
													<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlsolution.$error">
												     		<em class="error help-block has-error" ng-message="required">Please select </em>
													</div>
										</div>
							<label class="control-label col-sm-3 textAlignRight required-Field">Synergy</label>
									<div class="col-sm-3">							
									<Select id="dlsynergy" class="form-control"
												placeholder="Please select" name="dlsynergy" ng-class="{true: 'ng-border'} [(update && frmDeal.dlsynergy.$invalid)]"
												ng-model="frmDeal.synergyModel" ng-options="nr.id as nr.name for nr in dealTMSynergy" required >
												<option value="" selected >Please select</option>										
									</Select>
											<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlsynergy.$error">
										     		<em class="error help-block has-error" ng-message="required">Please select</em>
											</div>
									</div>
						</div>
					
					<div class="divEmptyThrice"></div>
					<div class="divEmptyThrice"></div>
					
					
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<!-- <div class="panel-heading panelHeadingStyle"
					ng-click="ShowHideUpload()">
						
					<div class="row ">
						<label class="control-label col-sm-10 ">Upload Manual Deal</label> <label class="DownArrowColor col-sm-2  textAlignRight">
							&#9660;</label>
					</div>
				</div> -->
				<div class="panel-body" ng-hide="UploadHidden">
				<fieldset ng-disabled="isDisabled">
					<div class="row">
						<div class="col-sm-2 "></div>
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Upload
							File name</label>
						<div class="col-sm-3 ">
							<input type="file" class="form-control" name="fuUploadManualDeal"
								id="fuUploadManualDeal" ng-model="frmDeal.fuUploadManualDealModel"
							    >
						</div>
						<div class="col-sm-1 divPaddingLeftZero">
										<div class="divDownloadImg">
												<a href="#" id="MANUAL_DEAL"
													class="anchorTrancColor"
													ng-click="downloadFile(frmDeal.fuUploadManualDealModel);"> <span id="manualDealAttachment"></span>
												</a>
										 </div>
						</div>
						<div class="col-sm-1">
							<!-- <button type="button" class="btn btn-primary btnSpace" 
								id="btnUpload" >Upload</button> -->
						</div>
					</div>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle"
					ng-click="ShowHideAttachment()">
					<div class="row ">
						<label class="control-label col-sm-10 ">Add Attachment </label> <label class="DownArrowColor col-sm-2  textAlignRight">
							&#9660;</label>
					</div>
				</div>
				<div class="panel-body" ng-hide="AttachmentHidden">
				
				<!-- <fieldset ng-disabled="isDisabled">
					<div class="row marginBottom5px">
					<div class="col-sm-2 "></div>
						<label class="control-label col-sm-2 textAlignRight required-Field">Attachment 1</label>
						<div class="col-sm-3 ">
							<input type="file" class="form-control" name="fuAttachment_1"
								id="fuAttachment_1" ng-model="frmDeal.fuAttachment_1Model"
							    >
						</div>
						<div class="col-sm-1 divPaddingLeftZero">
										<div class="divDownloadImg">
												<a href="#" id="ATTACHMENT_ID1"
													class="anchorTrancColor"
													ng-click="downloadFile(frmDeal.fuAttachment_1Model);"> <span id="attachment1"></span>
												</a>
										 </div>
						</div>
					
						
					</div>
					<div class="row marginBottom5px">
					<div class="col-sm-2 "></div>
						<label class="control-label col-sm-2 textAlignRight required-Field">Attachment 2</label>
						<div class="col-sm-3 ">
							<input type="file" class="form-control" name="fuAttachment_2"
								id="fuAttachment_2" ng-model="frmDeal.fuAttachment_2Model"
							    >
						</div>
						<div class="col-sm-1 divPaddingLeftZero">
										<div class="divDownloadImg">
												<a href="#" id="ATTACHMENT_ID2"
													class="anchorTrancColor"
													ng-click="downloadFile(frmDeal.fuAttachment_2Model);"> <span id="attachment2"></span>
												</a>
										 </div>
						</div>
					</div>
				</fieldset> -->
				
				
				<div class="panel-body" ng-hide="AttachmentHide">
						<div class="col-sm-2 "></div>
						<label
							class="control-label col-sm-2 textAlignRight required-Field">
							Attachment </label>
						<div class="col-sm-3 ">
							<input type="file" class="form-control" name="fuAttachFilename"
								id="fuAttachFilename" ng-model="frmDeal.fuAttachFilenameModel"
								check-file-size="frmDeal.fuAttachFilenameModel"
								valid-file-rate-card
								ng-class="{true: 'ng-border'}[(upload) && frmDeal.fuAttachFilename.$invalid]">
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnUpload" ng-click="uploadTMAttachmentData(frmDeal);"
								ng-disabled="uploadBtnDisable">Upload</button>
						</div>
						</div>
				
				
				
				<fieldset ng-disabled="IsDisabled" ng-hide="AttachementData.length==0">
							<table 
								class="	table clsTable table-striped table-bordered table-hover table-condensed "
								border="0" id="PolicyReportTbl">
								<thead>
									<tr>
										<th width="60%">File Name</th>
										<th width="25%">Date Of Upload [yyyy/mm/dd]</th>
										<th width="15%">Action</th>

									</tr>
								</thead>
								<tbody id="tBody">
									<tr id="add_attachment" ng-repeat="attachdata in AttachementData ">
										<td width="60%"><a href="#" id="attach_Download"
											class="control-label  textAlignLeft"
											ng-click="downloadFile(attachdata.objectid);"><label>{{attachdata.originalfilename}}</label></a></td>
										<td width="25%"><label>{{attachdata.createdOn}}</label></td>
										<td width="15%"><a href="#" id="Attach_Delete"
											class="control-label  textAlignLeft"
											ng-click="deleteFile(attachdata.objectid);"><label>Delete</label></a>
										</td>

									</tr>
									<tr>
									</tr>
								</tbody>
							</table>
						</fieldset>
				
				
				
				
				</div>
				
				
				
				
				
				
			</div>
		</div>
	</div>
</div>
<div class="row text-center">
	<div class="col-sm-12">	
	<fieldset ng-disabled="isDisabled">
		<button save-click="frmDeal" type="button" class="btn btn-primary btnSpace" id="btnAddNewVersion" ng-click="getVersionName(frmDeal)" ng-disabled = "isCopyDisabled || isDealApproveOrPendngAPRVL">Copy As New Version</button>
					
		<button save-click="frmDeal" type="button"  class="btn btn-primary btnSpace" id="btnSaveCurrentVersion" ng-click="checkUpdateDealData(frmDeal)" ng-disabled = "isVersionAvailable || isDealApproveOrPendngAPRVL">Save</button>
		<!-- <button save-click="frmDeal" type="button" class="btn btn-primary btnSpace" id="btnAddNewVersion" ng-click="getVersionName(frmDeal)">Copy As New Version</button> -->
	
		<!-- <button type="button" class="btn btn-danger btnSpace"  id="btnDetailsCancel" ng-click="Cancel()">Cancel</button> -->
		<button type="button" class="btn btn-info" id="btnNext" ng-click="Next()">Next</button>
	</fieldset>
	</div>
</div>