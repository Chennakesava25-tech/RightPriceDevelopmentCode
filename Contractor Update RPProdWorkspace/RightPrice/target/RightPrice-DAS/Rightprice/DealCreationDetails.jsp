
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
									ng-model="frmDeal.customerNameModel" ng-options=" cvi.customerId as cvi.customer.customerName for cvi in customer| orderBy:'customer.customerName'" required>
									<option value="" selected >Please select</option>
									<!-- <option value="1">Amex</option>
									<option value="2">Humana</option> -->
								</Select>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlName.$error">
								     	<em class="error help-block has-error" ng-message="required">Please select Customer Name</em>
								</div>
							</div>
							<!-- <label class="control-label col-sm-3 textAlignRight "> </label>
							<div class="col-sm-4">
								<button save-click="frmDeal" type="button" class="btn btn-primary btnSpace" id="btnAddNewVersion" ng-click="getVersionName(frmDeal)">Copy As New Version</button>
							</div> -->
						</div>
						<div class="row marginBottom5px">						
							<label class="control-label col-sm-2 textAlignRight ">Deal</label>
							{{crmDealId.crmDealDetailCust}}
							<div class="col-sm-3 tooltip1">
								<Select id="ddlDeal" name="ddlDeal" class="form-control" ng-options="cvi.crmDealId as cvi.crmDealId for cvi in crmDealDetailCust| orderBy:'crmDealId'"  ng-class="{true: 'ng-border'} [(update && frmDeal.ddlDeal.$invalid)]"
									placeholder="Please select" name="ddlDeal"
									required ng-model="frmDeal.DealModel" ng-change="clearFiles();getVersion(frmDeal.DealModel)">
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
				<div class="modal-dialog">
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
									placeholder="Please select" name="ddlVersion" ng-change="searchFile(frmDeal.versionModel);putVersion(frmDeal.versionModel)" 
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
							<label class="control-label col-sm-3 textAlignRight ">Billing Currency
								 </label>
							<div class="col-sm-3">
								<input name="txtDealPriority" type="text" class="form-control" ng-model="frmDeal.currencyModel"
									id="txtDealPriority">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Deal
								Start Date<br>(dd/mm/yyyy) </label>
							<div class="col-sm-3">
								<input type="text" class="form-control" ng-model="frmDeal.dealStartDateModel"
									 id="txtDealStartDate"name="txtDealStartDate">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Deal
								End Date<br>(dd/mm/yyyy) </label>
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
								<input name="txtCurrency" type="text"  ng-model="frmDeal.stageModel"
									class="form-control" id="txtCurrency">
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
				
											
					 <div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Onsite Hrs Per Day</label>
							<div class="col-sm-3" >
								<select name="txtOnsiteHrs" type="text" class="form-control" ng-options="oi.hourId as oi.name for oi in onshoreHours" ng-model="frmDeal.onsiteHoursModel" ng-class="{true: 'ng-border'} [(update && frmDeal.txtOnsiteHrs.$invalid)]"
									placeholder="Please select" id="txtOnsiteHrs"  required>
									<option value="" selected disabled>Please select</option>
									</select>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtOnsiteHrs.$error">
								     	<em class="error help-block has-error" ng-message="required">Please enter Onsite Hrs per day</em>
								     
								</div>	
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Offshore Hrs Per Day</label>
							<div class="col-sm-3">
								<select name="txtOffshoreHrs" type="text" class="form-control" ng-options="offshoreHour.hourId as offshoreHour.name for offshoreHour in offshoreHours" ng-model="frmDeal.offShoreHoursModel" ng-class="{true: 'ng-border'} [(update && frmDeal.txtOffshoreHrs.$invalid)]"
									placeholder="Please select" id="txtOffshoreHrs"  required>
									<option value="" selected disabled>Please select</option>
								</select>
								<!-- <div class="error-messages" ng-if="update" ng-messages="frmDeal.txtOffshoreHrs.$error">
								     	<em class="error help-block has-error" ng-message="required">Please enter Offshore Hrs(Client) per day</em>
								</div>	 -->
							</div>
						</div>	
						
						 <div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">No of Working Days</label>
							<div class="col-sm-3" >
								<select name="txtWrkDays" type="text" class="form-control" ng-options="wd.dayId as wd.name for wd in workingDays" ng-model="frmDeal.workingDaysModel" ng-class="{true: 'ng-border'} [(update && frmDeal.txtWrkDays.$invalid)]"
									placeholder="Please select" id="txtWrkDays"  required>
									<option value="" selected disabled>Please select</option>
									</select>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtWrkDays.$error">
								     	<em class="error help-block has-error" ng-message="required">Please enter Working days per month</em>
								     
								</div>	
							</div>
						</div>	
						
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
							class="control-label col-sm-3 textAlignRight required-Field">Project Type</label>
						<div class="col-sm-3" ng-disabled="viewmode">
							<Select id="ddlFPType" class="form-control" ng-options="fpt.id as fpt.name for fpt in fpType" ng-change="checkfpType(frmDeal.fpTypeModel)" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlFPType.$invalid)]"
								placeholder="Please select" name="ddlFPType"
								ng-model="frmDeal.fpTypeModel" required >
								<option value="" selected >Please select</option>
								<!-- <option value="1">Development</option>
								<option value="2">Mentanance</option> -->
							</Select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlFPType.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select FP Type</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Whether The Deal is<br> New Or Renewal?
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
						<label
							class="control-label col-sm-3 textAlignRight required-Field">Whether
							the Project<br> is Agile Based?
						</label>
						<div class="col-sm-3">
							<select id="ddlAgileBased" class="form-control" ng-options="iab.id as iab.name for iab in isAgileBased" ng-class="{true: 'ng-border'} [(update && frmDeal.ddlNewOrRenewal.$invalid)]"
								placeholder="Please select" name="ddlAgileBased" ng-model="frmDeal.isAgileBasedModel" required>
								<option value="" selected >Please select</option>
								<!-- <option value="1">Yes</option>
								<option value="2">No</option> -->
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlAgileBased.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select the Project is Agile</em>
							</div>
						</div>
					</div>
					
					<div class="row marginBottom5px" ng-if="Renewal==true">
						<label class="control-label col-sm-2 textAlignRight ">Old Deal Id</label>
							{{crmDealId.crmDealDetail}}
							<div class="col-sm-3">
							
								<Select id="ddlDeal" name="ddlDeal" class="form-control" ng-options="cvi.crmDealId as cvi.crmDealId for cvi in crmOldDealDetails"  ng-class="{true: 'ng-border'} [(update && frmDeal.ddlDeal.$invalid)]"
									placeholder="Please select" name="ddlDeal"
									required ng-model="frmDeal.oldDealModel" ng-change="getOldDescription(frmDeal.oldDealModel)">
									<option value="" selected>Please select</option></Select>
									<!-- <option value="1">Deal 1</option>
									<option value="2">Deal 2</option> -->
								
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlDeal.$error">
								     	<em class="error help-block has-error" ng-message="required">Please select Deal Id</em>
								</div>
							</div>
							<div class="col-sm-1" >
									<a style="cursor:pointer" ng-hide="isHide" ng-click="showDealDetails(crmDealId.crmOldDealDetail)"><strong><u>View</u></strong></a>
							</div>
							<!-- <label class="control-label col-sm-2 textAlignRight required-Field">Old Deal Id</label>
							<div class="col-sm-3">
								<input name="txtOldDealId" type="text" class="form-control" id="txtOldDealId " ng-model="frmDeal.oldDealIdModel" required
								ng-class="{true: 'ng-border'} [(isDealRenewal && frmDeal.txtOldDealId.$invalid)]"><a ng-href=""><strong>View</strong></a>
								<div class="error-messages" ng-if="isDealRenewal" ng-messages="frmDeal.txtOldDealId.$error">
						     		<em class="error help-block has-error" ng-message="required">Please provide Old Deal Id</em>
								</div>
								
							</div> -->
							<label class="control-label col-sm-2 textAlignRight ">Old Description</label>
							<div class="col-sm-3">
								<input name="txtOldProjectId" type="text" class="form-control" id="txtOldProjectId"  ng-model="frmDeal.descriptionOldModel" disabled>
							</div> 
						</div>
						
						<div class="row marginBottom5px" ng-if="Renewal==true">
							<label class="control-label col-sm-2 textAlignRight required-Field" placeholder="Old Project Id ">Old Project Id</label>
							<div class="col-sm-3">
								<input name="txtProjectId" type="text" class="form-control" id="txtProjectId" ng-model="frmDeal.projectIdModel" ng-pattern="/^[0-9]*$/" required
								ng-class="{true: 'ng-border'} [(isDealRenewal && frmDeal.txtProjectId.$invalid)]"  >
								<div class="error-messages" ng-if="isDealRenewal" ng-messages="frmDeal.txtProjectId.$error">
						     		<em class="error help-block has-error" ng-message="required">Please provide Old Project Id</em>
									<em class="error help-block has-error" ng-message="pattern">Project id is invalid! Enter only Integers</em> 
								</div>
							</div>
						</div>
						
						
					<!-- <div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Onsite
							Facility</label>
						<div class="col-sm-3">
							<select id="ddlOnsiteFacility" class="form-control" ng-options="iof.id as iof.name for iof in isSyntelOnsiteFacilityUsed" 
								ng-class="{true: 'ng-border'} [(update && frmDeal.ddlOnsiteFacility.$invalid)]"
								placeholder="Please select" name="ddlOnsiteFacility" ng-model="frmDeal.onsiteFacilityModel" required>
								<option value="" selected >Please select</option>
								<option value="1">Client</option>
								<option value="2">Syntel</option>
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlOnsiteFacility.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select Onsite Facility</em>
							</div>
						</div>
						<label
							class="control-label col-sm-3 textAlignRight required-Field">Is
							Bizops Involved</label>
						<div class="col-sm-3">
							<input type="checkbox" name="cbxIsBizopsInvolved" ng-model="frmDeal.isBizopsModel"
								id="cbxIsBizopsInvolved" class="margingRightChkBx">
						</div>
					</div> -->
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Risk
							Category </label>
						<div class="col-sm-3">
							<Select id="ddlRiskCategory" class="form-control"  ng-options="rc.id as rc.name for rc in riskCategoryId"
								ng-class="{true: 'ng-border'} [(update && frmDeal.ddlRiskCategory.$invalid)]"
								placeholder="Please select" name="ddlRiskCategory" ng-model="frmDeal.riskCategoryModel" required>
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
                           <!--  <select id="ddlIndustry" class="form-control" placeholder="Please select" name="ddlIndustry" disabled
                               		ng-model="frmDeal.ddlIndustryModel" required ng-options="it.id as it.name for it in industryType">
								<option value="" selected >Please select</option>
								<option value="1">IT</option>
								<option value="2">KPO</option>
							</select>
							 -->
							<input name="ddlIndustry" type="text" class="form-control" id="ddlIndustry" ng-model="frmDeal.ddlIndustryModel" required
								disabled>
							
						</div>
						
						<label class="control-label col-sm-3 textAlignRight ">RBU
								Type </label>
							<div class="col-sm-3">
								<input name="txtrbuType " type="text" class="form-control" ng-model="frmDeal.rbuTypeModel" disabled
									id="txtrbuType">
							</div>
								
                        <!-- <label class="control-label col-sm-3 textAlignRight required-Field">LOB </label>
                        <div class="col-sm-3">
                       		<select id="ddlLOB" class="form-control" placeholder="Please select" name="ddlLOB"
                				ng-model="frmDeal.ddlLOBModel" required ng-options="mlob.lobId as mlob.lobCode for mlob in masterLob"
                				ng-class="{true: 'ng-border'} [(update && frmDeal.ddlLOB.$invalid)]">
							<option value="" selected >Please select</option>
							<option value="1">GEN</option>
							<option value="2">SOG</option>
							<option value="3">Test</option>
							<option value="4">SHPT</option>
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlLOB.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select LOB</em>
							</div>
						</div>	 -->
						
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-2 textAlignRight ">Penalty Percent</label>
						<div class="col-sm-3">
							<input name="txtPenaltyPercent" type="text" class="form-control" ng-model="frmDeal.penaltyPercent"
									id="txtPenaltyPercent" required>
						</div>
					</div>
					<div class="row marginBottom5px">
						<!-- <label
							class="control-label col-sm-2 textAlignRight required-Field">Is Manual Deal </label>
						<div class="col-sm-3">
							<input type="checkbox" name="cbxIsManualDeal" ng-model="frmDeal.isManualDeal"
								id="cbxIsManualDeal" class="margingRightChkBx">
						</div> -->
						<label class="control-label col-sm-2 textAlignRight required-Field">Manual Deal</label>
						<div class="col-sm-3">
							<select id="fdmanualdeal" class="form-control" ng-options="md.id as md.name for md in selectManualDeal" 
							 ng-class="{true: 'ng-border'} [(update && frmDeal.fdmanualdeal.$invalid)]"
							 ng-change="checkfpManualDealType(frmDeal.fdmanualdealModel)"
							 placeholder="Please select" name="fdmanualdeal" ng-model="frmDeal.fdmanualdealModel" required>
								<option value="" selected >Please select</option>
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.fdmanualdeal.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select whether the deal is manual or not</em>
							</div>
						</div>
					</div>
					
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
														ng-model="frmDeal.solutionModel" ng-options="nr.id as nr.name for nr in dealTMsolution"  required>
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
												ng-model="frmDeal.synergyModel" ng-options="nr.id as nr.name for nr in dealTMSynergy"  required>
												<option value="" selected >Please select</option>										
									</Select>
											<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlsynergy.$error">
										     		<em class="error help-block has-error" ng-message="required">Please select</em>
											</div>
									</div>
						</div>
					<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight">Volume
								Discount %</label>
							<div class="col-sm-3">
								<input name="txtDealVolumeDiscount" type="text"
									class="form-control" id="txtDealVolumeDiscount"
									ng-model="frmDeal.volumediscount"
									ng-disabled="isFirstTimePageLoaded"
									ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" readonly>
								<div class="error-messages" ng-if="saved"
									ng-messages="frmDeal.txtDealVolumeDiscount.$error">
									<em class="error help-block has-error" ng-message="pattern">Please
										enter numbers with maximum of two decimal.</em>
								</div>
							</div>
						</div>
						<div class="row marginBottom5px">
							<label
								class="control-label col-sm-2 textAlignRight required-Field">Do
								you want to update<br> the volume discount %?
							</label>
							<div class="col-sm-3">
								<select id="ddlVolumeUpdate" class="form-control"
									ng-options="nr.id as nr.name for nr in volumeArray"
									ng-class="{true: 'ng-border'} [(update && frmDeal.ddlVolumeUpdate.$invalid)]"
									placeholder="Please select" name="ddlVolumeUpdate"
									ng-model="frmDeal.ddlVolumeUpdateModel" required
									ng-change="setvolume(frmDeal.ddlVolumeUpdateModel)">
									<option value="" selected>Please select</option>
								</select>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.ddlVolumeUpdate.$error">
										     		<em class="error help-block has-error" ng-message="required">Please select VolumeUpdate </em>
											</div>
							</div>

							<label
								class="control-label col-sm-3 textAlignRight required-Field"
								ng-hide="hidevolumetxt">New Volume Discount  %</label>
							<div class="col-sm-3">
								<input name="txtUpdatevolume" type="text" class="form-control"
									id="txtUpdatevolume" ng-hide="hidevolumetxt"
									ng-required="isUpdate" ng-model="frmDeal.txtUpdatevolumeModel"
									ng-class="{true: 'ng-border'} [(update && frmDeal.txtUpdatevolume.$invalid)]"
									ng-maxlength="50" ng-pattern="numberRegex">

								<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtUpdatevolume.$error">
										     		<em class="error help-block has-error" ng-message="required">Please select OffshoreRiskpercenctage </em>
											</div>
							</div>



						</div>
							<div class="row marginBottom5px">
							<label
								class="control-label col-sm-2 textAlignRight required-Field">FX
								Risk% for Offshore </label>
							<div class="col-sm-3">
							<select name="dlOffshoreRisk" type="text" class="form-control"
									ng-options="os.riskid as os.name for os in OffshoreRiskperc"
									ng-model="frmDeal.txtOffshoreRiskModel"
									ng-class="{true: 'ng-border'} [(saved && frmDeal.dlOffshoreRisk.$invalid)]"
									placeholder="Please select" id="ddlOffshoreRisk" required>
									<option value="" selected disabled>Please select</option>
								</select>
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.dlOffshoreRisk.$error">
										     		<em class="error help-block has-error" ng-message="required">Please select OffshoreRiskpercenctage </em>
											</div>

								</div>
						</div>
						</div>
						<div class="row marginBottom5px">
							<label
								class="control-label col-sm-2 textAlignRight required-Field">Contingent Risk% </label>
							<div class="col-sm-3">
								<input name="txtContingentRisk" type="text" class="form-control"
									id="txtContingentRisk" 
									ng-model="frmDeal.txtContingentRiskModel"
									required ng-class="{true: 'ng-border'} [(update && frmDeal.txtContingentRisk.$invalid)]"
									ng-maxlength="50"
									ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" >
									
									<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtContingentRisk.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the ContingentRisk</em>
						     	<em	class="error help-block has-error" ng-message="pattern">Please enter only numeric value no special character allowed.</em>

						     	
							</div>
							</div>
							<label
								class="control-label col-sm-3 textAlignRight required-Field">SLA Risk%</label>
							<div class="col-sm-3">

								<input name="txtSLARisk" type="text" class="form-control"
									id="txtSLARisk" 
									ng-model="frmDeal.txtSLARiskModel"
									required ng-class="{true: 'ng-border'} [(update && frmDeal.txtSLARisk.$invalid)]"
									ng-maxlength="50" 
									ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/">
									
								<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtSLARisk.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the SLARisk</em>
						     	<em class="error help-block has-error" ng-message="pattern">Please enter only numeric value no special character allowed</em>

						     	
							</div>
							</div>


						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle"
					ng-click="ShowHideUploadQuest()">
						
					<div class="row ">
						<label class="control-label col-sm-10 ">Risk Based Questionnaire</label> <label class="DownArrowColor col-sm-2  textAlignRight">
							&#9660;</label>
					</div>
				</div>
				<div class="panel-body" ng-hide="UploadHiddenQuest">
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
							class="margingRightChkBx" ng-change="checkSynbotQues(frmDeal.riskAns9Model)">
					</div>
					<input type="hidden" id="txtVersionName" name="txtVersionName" ng-model="frmDeal.VersionNameModel">
					<div class="divEmptyThrice"></div>
					<div class="divEmptyThrice"></div>
					
					<div ng-if="synbotquestions == true">
					<div class="panel-heading panelHeadingStyle">
					<div class="row " >
						<label class="control-label col-sm-10 ">Synbots/Automation Based Questionnaire</label>
					</div>
					</div>
					<div class="divEmptyThrice"></div>
					<div class="divEmptyThrice"></div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">1)
							What is the Customer name?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">2)
							Description and Scope of the Project?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns1" id="txtRiskSynAns1" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns1Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns1.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtSynRiskAns1.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns2" id="txtRiskSynAns2" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns2Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns2.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns2.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">3)
							 What is the project plan? Will there be phases?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">4)
							Please provide the description of each of the phases and nature of work?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns3" id="txtRiskSynAns3" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns3Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns3.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns3.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns4" id="txtRiskSynAns4" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns4Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns4.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns4.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">5)
							If the project is a POC, what opportunities will this lead to?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">6)
							What is the duration of the project (phase wise, if applicable)?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns5" id="txtRiskSynAns5" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns5Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns5.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns5.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns6" id="txtRiskSynAns6" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns6Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns6.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns6.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">7)
							What is the type of project: Fixed Price / T&M / Fixed Capacity (phase wise, if applicable)?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">8)
							Are there any savings ($ / FTE) guarantee as part of the contract (or even verbal agreement)? Please elaborate</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns7" id="txtRiskSynAns7" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns7Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns7.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns7.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns8" id="txtRiskSynAns8" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns8Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns8.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns8.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">9)
							Which Commercial Model does it fall under?</label>
					</div>
					<div class="row marginBottom5px">
					<div class="row marginBottom5px">
						
						<label class="control-label col-sm-4 textAlignRight required-Field">Model 1 : Lift Out powered by process automation</label>
						
						<div class="col-sm-2">
							<select id="txtRiskSynAns9a" class="form-control" ng-options="md.id as md.name for md in selectManualDeal" ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns9a.$invalid)]"
								placeholder="Please select" name="txtRiskSynAns9a" ng-model="frmDeal.riskSynAns9aModel" required>
								<option value="" selected >Please select</option>
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns9a.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select</em>
							</div>
						</div>
						
					</div>
					<div class="row marginBottom5px">
						
						<label class="control-label col-sm-4 textAlignRight required-Field">Model 2: Business Process Automation as a Service / Automation CoE (IT Project)</label>
						<div class="col-sm-2">
							<select id="txtRiskSynAns9b" class="form-control" ng-options="md.id as md.name for md in selectManualDeal" ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns9b.$invalid)]"
								placeholder="Please select" name="txtRiskSynAns9b" ng-model="frmDeal.riskSynAns9bModel" required>
								<option value="" selected >Please select</option>
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns9b.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select </em>
							</div>
						</div>
						<label
							class="control-label col-sm-6 textAlignLeft required-Field">10)
							How do we deal with SyntBots on termination of project / contract / phase?</label>
					</div>
					<div class="row marginBottom5px">
					
						<label class="control-label col-sm-4 textAlignRight required-Field">Any other variant</label>
						<div class="col-sm-2">
							<select id="txtRiskSynAns9c" class="form-control" ng-options="md.id as md.name for md in selectManualDeal" ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns9c.$invalid)]"
								placeholder="Please select" name="txtRiskSynAns9c" ng-model="frmDeal.riskSynAns9cModel" required>
								<option value="" selected >Please select</option>
							</select>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns9c.$error">
						     	<em class="error help-block has-error" ng-message="required">Please select</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns10" id="txtRiskSynAns10" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns10Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns10.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns10.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
						<div class="col-sm-6">
						<label class="control-label col-sm-8 textAlignLeft required-Field">If any other variant, please describe below:</label>
							<textarea style="resize: none" name="txtRiskSynAns9d" id="txtRiskSynAns9d" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns9dModel" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns9d.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns9d.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						
					</div>
					
					
					
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignLeft required-Field">11)
						Risks
							</label><label class="control-label col-sm-3 textAlignLeft required-Field">
						Suggested Risk Mitigation steps
							</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">12)
							Will we charge for Implementation? How much?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-3">
							<textarea style="resize: none" name="txtRiskSynAns11a" id="txtRiskSynAns11a" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns11aModel" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns11a.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns11a.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-3">
							<textarea style="resize: none" name="txtRiskSynAns11b" id="txtRiskSynAns11b" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns11bModel" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns11b.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns11b.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns12" id="txtRiskSynAns12" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns12Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns12.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns12.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">13)
							Will we charge for SyntBots Support & Maintenance? How much?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">14)
							Commercials (phase wise, if applicable)?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns13" id="txtRiskSynAns13" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns13Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns13.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns13.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns14" id="txtRiskSynAns14" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns14Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns14.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns14.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">15)
							Any success criteria / milestones / conditions to enable invoicing? is the Customer name?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">16)
							What is the role of Syntbots in this project? Please explain in detail?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns15" id="txtRiskSynAns15" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns15Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns15.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns15.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns16" id="txtRiskSynAns16" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns16Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns16.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns16.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">17)
							Do whenever we sell our service... is SyntBots a significant focus of our marketing efforts?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">18)
							Do the rights to use SyntBots remain with Syntel or are transferred to customer as part of service offering?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns17" id="txtRiskSynAns17" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns17Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns17.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns17.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns18" id="txtRiskSynAns18" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns18Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns18.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns18.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">19)
							Is there an agreed upon transition during termination? How will it be charged to the customer?</label> <label
							class="control-label col-sm-6 textAlignLeft required-Field">20)
							Is there a separate costing available for migrate and manage? Please share the costingDescription and Scope of the Project?</label>
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns19" id=""txtRiskSynAns19"" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns19Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns19.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns19.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns20" id="txtRiskSynAns20" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns20Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns20.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns20.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-6 textAlignLeft required-Field">21)
							Any other details (not covered above):</label> 
					</div>
					<div class="row marginBottom5px">
						<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskSynAns21" id="txtRiskSynAns21" class="form-control " rows="2"
								ng-model="frmDeal.riskSynAns21Model" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskSynAns21.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskSynAns21.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
						</div>
					</div>
					<button class="btn btn-info btnCenter"  type="button" ng-click="exportToExcelAutoQue('#tableToExportAutoQue')">
      										     <img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
				
				</div>
			</div>
		</div>
	</div>
	<div class="panel-group" ng-if="showdevquest==true">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle"
					ng-click="ShowHideUploadDevQuest()">
						
					<div class="row ">
						<label class="control-label col-sm-10 ">Development Questionnaire</label> <label class="DownArrowColor col-sm-2  textAlignRight">
							&#9660;</label>
					</div>
				</div>
				<div class="panel-body" ng-hide="UploadHiddenDevQuest">
				<div ng-repeat="que in quest" ng-if="que.categoryId==1">
				<label class="control-label col-sm-6 required-Field">{{que.questionaireDesc}}</label>
				<div class="col-sm-6">
						<textarea style="resize: none" name="txtRiskAnsdev" id="txtRiskAnsdev" class="form-control " rows="2"
								ng-model="que.devanswers" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAnsdev.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAnsdev.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
							<div class="divEmptyThrice"></div>
				</div>
                </div>
                <div style="display:none">
	                                    <div class="col-sm-12">
											<div class="table-responsive" id="tableToExportDevelopmentQuestionnaire" ng-repeat="que in quest" ng-if="que.categoryId==1">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblOverAllSummary" border="1">
													<tbody id="tBodyOverAllSummary" >												
														<tr>
															<th><label>{{que.questionaireDesc}}</label></th>
															<td><textarea style="resize: none" name="txtRiskAnsdev" id="txtRiskAnsdev" class="form-control " rows="2"
								                                 ng-model="que.devanswers" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAnsdev.$invalid)]">
							                                     </textarea>
							                                 </td>												
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
    
							<div class="divEmptyThrice"></div>
				<div class="divEmptyThrice"></div>
				<div class="panel-body" ng-hide="uploadHide">
						<div class="col-sm-10 "></div>
						<label
							class="control-label col-sm-6 textAlignLeft ">Upload
							File name</label>
						<div class="col-sm-3 ">
							<input type="file" class="form-control" name="fuDevFilename"
								id="fuDevFilename" ng-model="frmDeal.fuDevFilenameModel"
								check-file-size="frmDeal.fuDevFilenameModel"
								valid-file-rate-card
								ng-class="{true: 'ng-border'}[(upload) && frmDeal.fuDevFilename.$invalid]">
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnUpload" ng-click="uploadDevData(frmDeal);"
								ng-disabled="uploadBtnDisable">Upload</button>
						</div>
						</div>
									<fieldset ng-disabled="IsDisabled" ng-hide="devAttachementData.length==0">
					<table 
						class="	table clsTable table-striped table-bordered table-hover table-condensed "
						border="0" id="PolicyReportTbl">
						<thead>
							<tr>
								<th width="60%">File Name</th>
								<th width="15%">Date Of Upload [dd/mm/yyyy]</th>
								<th width="15%">Action</th>

							</tr>
						</thead>
						<tbody id="tBody">
							<tr id="Devattachements"
								ng-repeat="row in devAttachementData">
								<td width="60%"><a href="#" id="Attach_Download"
									class="control-label  textAlignLeft"
									ng-click="downloadFile(row.objectid);"><label>{{row.originalfilename}}</label></a></td>
								<td width="25%"><label>{{row.createdOn}}</label></td>
								<td width="15%"><a href="#" id="Attach_Delete"
									class="control-label  textAlignLeft"
									ng-click="deleteFile(row.objectid);"><label>Delete</label></a>
								</td>

							</tr>
							<tr>
							</tr>


						</tbody>
					</table>
				</fieldset>
				<div class="divEmptyThrice"></div>
				<button class="btn btn-info btnCenter"  type="button" ng-click="exportToExcelDevelopmentQuestionnaire('#tableToExportDeveQue')">
      										     <img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
				
					<div class="divEmptyThrice"></div>
			</div>
		</div>
	</div>

	<div class="panel-group" ng-if="showmainquest==true">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle"
					ng-click="ShowHideUploadMainQuest()">
						
					<div class="row ">
						<label class="control-label col-sm-10 ">Maintenance Questionnaire</label> <label class="DownArrowColor col-sm-2  textAlignRight">
							&#9660;</label>
					</div>
				</div>
				<div class="panel-body" ng-hide="UploadHiddenMainQuest">
				<div ng-repeat="que in quest" ng-if="que.categoryId==2">
				<label class="control-label col-sm-6 required-Field">{{que.questionaireDesc}}</label>
				<div class="col-sm-6">
							<textarea style="resize: none" name="txtRiskAnsmain" id="txtRiskAnsmain" class="form-control " rows="2"
								ng-model="que.mainanswers" required ng-class="{true: 'ng-border'} [(update && frmDeal.txtRiskAnsmain.$invalid)]">
							</textarea>
							<div class="error-messages" ng-if="update" ng-messages="frmDeal.txtRiskAnsmain.$error">
						     	<em class="error help-block has-error" ng-message="required">Please provide the answer</em>
							</div>
							<div class="divEmptyThrice"></div>
				</div>
				</div>
				<div ng-hide="maintainHide">
				<div class="panel-body">
						<div class="col-sm-10 "></div>
						<label
							class="control-label col-sm-6 textAlignLeft ">Upload
							File name</label>
						<div class="col-sm-3 ">
							<input type="file" class="form-control" name="fuMainFilename"
								id="fuMainFilename" ng-model="frmDeal.fuMainFilenameModel"
								check-file-size="frmDeal.fuMainFilenameModel"
								valid-file-rate-card
								ng-class="{true: 'ng-border'}[(upload) && frmDeal.fuMainFilename.$invalid]">
						</div>
						<div class="col-sm-1">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnUpload" ng-click="checkFile(frmDeal);uploadMainData(frmDeal);"
								ng-disabled="uploadBtnDisable">Upload</button>
						</div>
						</div>
						<fieldset ng-disabled="IsDisabled" ng-hide="mainAttachementData.length==0" >
					<table 
						class="	table clsTable table-striped table-bordered table-hover table-condensed "
						border="0" id="PolicyReportTbl">
						<thead>
							<tr>
								<th width="60%">File Name</th>
								<th width="15%">Date Of Upload [dd/mm/yyyy]</th>
								<th width="15%">Action</th>

							</tr>
						</thead>
						<tbody id="tBody">
							<tr id="mainattachements"
								ng-repeat="row in mainAttachementData">
								<td width="60%"><a href="#" id="Attach_Download"
									class="control-label  textAlignLeft"
									ng-click="downloadFile(row.objectid);"><label>{{row.originalfilename}}</label></a></td>
								<td width="25%"><label>{{row.createdOn}}</label></td>
								<td width="15%"><a href="#" id="Attach_Delete"
									class="control-label  textAlignLeft"
									ng-click="deleteFile(row.objectid, row.category);"><label>Delete</label></a>
								</td>

							</tr>
							<tr>
							</tr>


						</tbody>
					</table>
				</fieldset>
				</div>
					<div class="divEmptyThrice"></div>
					<button class="btn btn-info btnCenter"  type="button" ng-click="exportToExcelMainQue('#tableToExportMenQue')">
      										    <img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
				<div class="divEmptyThrice"></div>
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
						<label class="control-label col-sm-10 ">Add Attachment </label> <label
							class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
					</div>
				</div>
				<div class="panel-body" ng-hide="AttachmentHidden">
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
								id="btnUpload" ng-click="uploadAttachmentData(frmDeal);"
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
										<th width="25%">Date Of Upload [dd/mm/yyyy]</th>
										<th width="15%">Action</th>

									</tr>
								</thead>
								<tbody id="tBody">
									<tr id="Manualattachements" ng-repeat="row in AttachementData ">
										<td width="60%"><a href="#" id="Attach_Download"
											class="control-label  textAlignLeft"
											ng-click="downloadFile(row.objectid);"><label>{{row.originalfilename}}</label></a></td>
										<td width="25%"><label>{{row.createdOn}}</label></td>
										<td width="15%"><a href="#" id="Attach_Delete"
											class="control-label  textAlignLeft"
											ng-click="deleteFile(row.objectid);"><label>Delete</label></a>
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
	    <div class="divEmptyThrice"></div>
	  		<div class="col-sm-12">
	 			 <div class="table-responsive" id="tableToExportAutoQue" style="display: none;">
	 				 <table  border="1">
	  					<tbody  >
	 				 		<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">1) What is the Customer name?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns1Model}}</td>
	   						</tr>
	   						<tr>
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">2) Description and Scope of the Project?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns2Model}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">3) What is the project plan? Will there be phases?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns3Model}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">4) Please provide the description of each of the phases and nature of work?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns4Model}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">5) If the project is a POC, what opportunities will this lead to?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns5Model}}</td>
	   						</tr>

	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">6) What is the duration of the project (phase wise, if applicable)?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns6Model}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">7) What is the type of project: Fixed Price / T&M / Fixed Capacity (phase wise, if applicable)?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns7Model}}</td>
	   						</tr>
	   						<tr>
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">8) Are there any savings ($ / FTE) guarantee as part of the contract (or even verbal agreement)? Please elaborate</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns8Model}}</td>
	   						</tr>
	   						<tr >
	  						 <th colspan="2">
	 	 							<div style="text-align: center;"><label style="text-align: center;">9) Which Commercial Model does it fall under?</label></div></th>

	 	 					</tr>
	 	 					<tr>
	 	 					     <th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">Model 1 : Lift Out powered by process automation</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns9aModel}}</td>
	   						</tr>
	   						<tr>
	 	 					     <th>
	   						        <div style="text-align: left;"><label style="text-align: justify;">Model 2: Business Process Automation as a Service / Automation CoE (IT Project)</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns9bModel}}</td>
	  						</tr>
	  						<tr>
	 	 					     <th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">Any other variant</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns9cModel}}</td>
	   						</tr>
	   						<tr>
	 	 					     <th>
	   						        <div style="text-align: left;"><label style="text-align: justify;">If any other variant, please describe below:</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns9dModel}}</td>
	  						</tr>
	  						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">10) How do we deal with SyntBots on termination of project / contract / phase?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns10Model}}</td>
	   						</tr>
	   						<tr>
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">11) Risks</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns11aModel}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">Suggested Risk Mitigation steps</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns11bModel}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">12) Will we charge for Implementation? How much?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns12Model}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">13) Will we charge for SyntBots Support & Maintenance? How much?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns13Model}}</td>
	   						</tr>

	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">14) Commercials (phase wise, if applicable)?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns14Model}}</td>
	   						</tr>
	   						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">15) Any success criteria / milestones / conditions to enable invoicing? is the Customer name?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns15Model}}</td>
	   						</tr>
	   						<tr>
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">16) What is the role of Syntbots in this project? Please explain in detail?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns16Model}}</td>
	   						</tr>
	   					   <tr>
	 	 					     <th>
	   						        <div style="text-align: left;"><label style="text-align: justify;">17) Do whenever we sell our service... is SyntBots a significant focus of our marketing efforts?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns17Model}}</td>
	  						</tr>
	  						<tr>
	 	 					     <th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">18) Do the rights to use SyntBots remain with Syntel or are transferred to customer as part of service offering?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns18Model}}</td>
	   						</tr>
	   						<tr>
	 	 					     <th>
	   						        <div style="text-align: left;"><label style="text-align: justify;">19) Is there an agreed upon transition during termination? How will it be charged to the customer?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns19Model}}</td>
	  						</tr>
	  						<tr >
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">20) Is there a separate costing available for migrate and manage? Please share the costingDescription and Scope of the Project?</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns20Model}}</td>
	   						</tr>
	   						<tr>
	  							<th>
	 	 							<div style="text-align: left;"><label style="text-align: justify;">21) Any other details (not covered above):</label></div></th>
	  									<td style="text-align: justify;">{{frmDeal.riskSynAns21Model}}</td>
	   						</tr>
	  				 </tbody>
	  			</table>
	   		</div>
	   </div>
	   
	   <div class="divEmptyThrice"></div>
	  		<div class="col-sm-12">
	 			 <div class="table-responsive" id="tableToExportMenQue" style="display: none;">
	 				 <table  border="1">
	  					<tbody  >
	 				 		<tr ng-repeat="que in quest" ng-if="que.categoryId==2">
	  							<th>
	 	 							<div style="text-align: justify;"><label style="text-align: left;">{{que.questionaireDesc}}</label></div>
	 	 						</th>
	  							<td style="text-align: right;">{{que.mainanswers}}</td>
	   						</tr>
	  				 </tbody>
	  			</table>
	   		</div>
	   </div>
<div class="divEmptyThrice"></div>
	  		<div class="col-sm-12">
	 			 <div class="table-responsive" id="tableToExportDeveQue" style="display: none;">
	 				 <table  border="1">
	  					<tbody  >
	 				 		<tr ng-repeat="que in quest" ng-if="que.categoryId==1">
	  							<th>
	 	 							<div style="text-align: justify;"><label style="text-align: left;">{{que.questionaireDesc}}</label></div></th>
	  									<td style="text-align: right;">{{que.devanswers}}</td>
	   						</tr>
	  				 </tbody>
	  			</table>
	   		</div>
	   </div>
<div class="row text-center">
	<div class="col-sm-12">	
	<fieldset ng-disabled="isDisabled">
<!-- 		<button save-click="frmDeal" type="button"  class="btn btn-primary btnSpace" id="btnSaveCurrentVersion" ng-click="checkUpdateDealData(frmDeal)" ng-disabled="isVersionAvailable">Save</button>
 -->		<!-- <button save-click="frmDeal" type="button" class="btn btn-primary btnSpace" id="btnAddNewVersion" ng-disabled="isCurrStatus" ng-click="getVersionName(frmDeal)">Copy As New Version</button> -->
	</fieldset><br>
		<button save-click="frmDeal" type="button" class="btn btn-primary btnSpace" id="btnAddNewVersion"  ng-click="getVersionName(frmDeal)" ng-disabled="isReadytoSubmit">Save As New Version</button>
		<button save-click="frmDeal" type="button"  class="btn btn-primary btnSpace" id="btnSaveCurrentVersion" ng-click="checkUpdateDealData(frmDeal)" ng-disabled="isVersionAvailable">Save</button>
		<!-- <button type="button" class="btn btn-danger btnSpace"  id="btnDetailsCancel" ng-click="Cancel()">Cancel</button> -->
		<button type="button" class="btn btn-info" id="btnNext" ng-click="Next()">Next</button>
	</div>
</div>


	   