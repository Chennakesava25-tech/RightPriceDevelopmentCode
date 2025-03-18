<div class="row">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Upload Manual Deal</label>
						<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideSearch()"> &#9660;</a>
										</div> -->
					</div>
				</div>
				<div class="panel-body">

					<div class="row marginBottom5px">
						<div class="row marginBottom5px">
							<label
								class="control-label col-sm-1 textAlignRight required-Field">Tower</label>
							<div class="col-sm-2">
								<select id="ddlDealRoleDetailsTower" class="form-control"
									placeholder="Please select" name="ddlDealRoleDetailsTower"
									ng-model="fpdcRoleSelectionFrm.dealRoleDetailsTowerModel"
									ng-options="to as to.towerName for to in towerdetails"
									ng-change="getTowerCountryCity(fpdcRoleSelectionFrm.dealRoleDetailsTowerModel)"
									required>
									<option value="" selected disabled>Please select</option>
								</select>
							</div>
							<label
								class="control-label col-sm-2 textAlignRight required-Field">Country</label>
							<div class="col-sm-2">
								<input id="ddlDealRoleDetailsCountry" class="form-control"
									placeholder="Country" name="ddlDealRoleDetailsCountry"
									ng-model="fpdcRoleSelectionFrm.dealRoleDetailsCountryModel"
									disabled> </input>
							</div>
							<label
								class="control-label col-sm-2 textAlignRight required-Field">City</label>
							<div class="col-sm-2">
								<input id="ddlDealRoleDetailsCity" class="form-control"
									placeholder="City" name="ddlDealRoleDetailsCity"
									ng-model="fpdcRoleSelectionFrm.dealRoleDetailsCityModel"
									disabled> </input>
							</div>
						</div>
					</div>


					<div class="panel-body" ng-hide="AttachmentHidden">
						<div class="panel-body" ng-hide="AttachmentHide">
							<div class="row marginBottom5px">
								<label
									class="control-label col-sm-2 textAlignRight required-Field">Attachment</label>
								<div class="col-sm-3 ">
									<input type="file" class="form-control" name="fuAttachFilename"
										id="fuAttachFilename" ng-model="frmDeal.fuAttachFilenameModel"
										ng-class="{true: 'ng-border'}[(upload) && frmDeal.fuAttachFilename.$invalid]">
								</div>



								<label
									class="control-label col-sm-2 textAlignRight required-Field">Document
									Type</label>
								<div class="col-sm-2">
									<Select id="ddlSheetType" class="form-control"
										ng-options="st.id as st.name for st in sheetType"
										ng-class="{true: 'ng-border'} [(update && frmDeal.ddlSheetType.$invalid)]"
										placeholder="Please select" name="ddlSheetType"
										ng-model="frmDeal.sheetTypeModel" required>
										<option value="" selected>Please select</option>

									</Select>
									<div class="error-messages" ng-if="update"
										ng-messages="frmDeal.ddlSheetType.$error">
										<em class="error help-block has-error" ng-message="required">Please
											select Sheet Type</em>
									</div>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-primary btnSpace"
										id="btnUpload" ng-click="uploadAttachmentData(frmDeal);"
										ng-disabled="uploadBtnDisable">Upload</button>
								</div>
							</div>
						</div>
						<div class="divEmptyThrice"></div>
						<fieldset ng-disabled="IsDisabled" ng-hide="tableHide">
							<table
								class="	table clsTable table-striped table-bordered table-hover table-condensed "
								border="0" id="PolicyReportTbl">
								<thead>
									<tr>
										<th width="20%">Tower Name</th>
										<th width="40%">File Name</th>
										<th width="10%">Date Of Upload (dd/mm/yyyy)</th>
										<th width="8%">Document Type</th>
										<th width="5%">Action</th>
									</tr>
								</thead>
								<tbody id="tBody">
									<tr id="Manualattachements"
										ng-repeat="row in versionAttachment|orderBy:'-updatedOn'">
										<td width="15%"><label>{{row.towerName}}</label></td>
										<!-- <td width="30%"><a href="ftp://10.128.10.231/Rightprice/Ratecard/{{row.fileName}}" id="Attach_Download"
											class="control-label  textAlignLeft"><label>{{row.fileName}}</label></a></td> -->
										<td width="30%"><a href="#" id="Attach_Download"
											class="control-label  textAlignLeft"
											ng-click="downloadFileWithFileName(row.dealAttachmentId);"><label>{{row.fileName}}</label></a></td>
										<td width="20%"><label>{{row.createdOn}}</label></td>
										<td width="15%"><label>{{row.docTypeName}}</label></td>
										<td width="15%"><a href="#" id="Attach_Delete"
											class="control-label  textAlignLeft"
											ng-click="deleteManualFile(row.dealAttachmentId);"><label>Delete</label></a>
										</td>
									</tr>
									<tr>
									</tr>
								</tbody>
							</table>
						</fieldset>
					</div>

					<div class="divEmpty"></div>

					<div class="divEmptyThrice"></div>

				</div>
			</div>
		</div>
	</div>

	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Overall Deal
							Summary</label>
						<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideSearch()"> &#9660;</a>
										</div> -->
					</div>
				</div>
				<div class="panel-body">

					<div>
						<label><i><font color="red">Note: All rates are
									in {{currency}}</font></i></label>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignRight ">Onsite
							%</label>
						<div class="col-sm-2">
							<input id="ddlDealManualOnsiteper"
								class="form-control textAlignRight" placeholder=""
								name="ddlDealManualOnsiteper"
								ng-model="fpdcRoleSelectionFrm.fpDealManualOnsitePer" disabled></input>
						</div>
						<label class="control-label col-sm-3 textAlignRight ">Offshore
							%</label>
						<div class="col-sm-2">
							<input id="ddlDealManualOffshoreper"
								class="form-control textAlignRight" placeholder=""
								name="ddlDealManualOffshoreper"
								ng-model="fpdcRoleSelectionFrm.fpDealManualOffshorePer" disabled>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignRight ">Revenue</label>
						<div class="col-sm-2">
							<input id="ddlDealManualRevenue"
								class="form-control textAlignRight" placeholder=""
								name="ddlDealManualRevenue"
								ng-model="fpdcRoleSelectionFrm.fpDealManualRevenue" disabled></input>
						</div>
						<label class="control-label col-sm-3 textAlignRight ">Direct
							Cost</label>
						<div class="col-sm-2">
							<input id="ddlDealDirectCostper"
								class="form-control textAlignRight" placeholder=""
								name="ddlDealDirectCostper"
								ng-model="fpdcRoleSelectionFrm.fpDealDirectCostper" disabled>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignRight ">Project
							Margin %</label>
						<div class="col-sm-2">
							<input input id="ddlDealManualGrossMargin"
								class="form-control textAlignRight" placeholder=""
								name="ddlDealManualGrossMargin"
								ng-model="fpdcRoleSelectionFrm.fpDealManualGrossMargin | number:2"
								disabled></input>
						</div>


						<label class="control-label col-sm-3 textAlignRight ">Project
							Specific including Agile Cost </label>
						<div class="col-sm-2">
							<input id="ddlDealManualProSpecAgileCost"
								class="form-control textAlignRight" placeholder=""
								name="ddlDealManualProSpecAgileCost"
								ng-model="fpdcRoleSelectionFrm.fpDealManualProSpecAgileCost "
								disabled></input>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignRight ">PM(%)
							after Project specific Cost</label>
						<div class="col-sm-2">
							<input id="ddlManualDealGMperAfterPSC"
								class="form-control textAlignRight" placeholder=""
								name="ddlManualDealGMperAfterPSC"
								ng-model="fpdcRoleSelectionFrm.fpManualDealGMperAfterPSC | number:2"
								disabled>
						</div>
						<label class="control-label col-sm-3 textAlignRight ">Volume
							Discount</label>
						<div class="col-sm-2">
							<input id="ddlManualDealVolDisc"
								class="form-control textAlignRight" placeholder=""
								name="ddlManualDealVolDisc"
								ng-model="fpdcRoleSelectionFrm.fpManualDealVolDisc | number:2"
								disabled></input>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignRight ">Penalty
							%</label>
						<div class="col-sm-2">
							<input id="ddlManualDealPen" class="form-control textAlignRight"
								placeholder="" name="ddlManualDealPen"
								ng-model="fpdcRoleSelectionFrm.fpManualDealPen" disabled></input>
						</div>
						<label class="control-label col-sm-3 textAlignRight ">PM(%)
							after Volume Discount %</label>
						<div class="col-sm-2">
							<input id="ddlManualDealGmAfterVolDis"
								class="form-control textAlignRight" placeholder=""
								name="ddlManualDealGmAfterVolDis"
								ng-model="fpdcRoleSelectionFrm.ManualDealGmAfterVolDis | number:2"
								disabled>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignRight ">Project
							Margin </label>
						<div class="col-sm-2">
							<input id="ddlManualDealProjectmargin"
								class="form-control textAlignRight" placeholder=""
								name="ddlManualDealProjectmargin"
								ng-model="fpdcRoleSelectionFrm.fpManualDealProjectmargin"
								disabled></input>
						</div>
						<label class="control-label col-sm-3 textAlignRight ">Project
							Margin % incl. Risk %</label>
						<div class="col-sm-2">
							<input id="ddlManualDealPMinclrisk"
								class="form-control textAlignRight" placeholder=""
								name="ddlManualDealPMinclrisk"
								ng-model="fpdcRoleSelectionFrm.ManualDealPMinclrisk | number:2"
								disabled>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-3 textAlignRight ">PM%
							incl. Risk and VD % </label>
						<div class="col-sm-2">
							<input id="ddlManualDealProjectmargininclRiskandVD"
								class="form-control textAlignRight" placeholder=""
								name="ddlManualDealProjectmargininclRiskandVD"
								ng-model="fpdcRoleSelectionFrm.fpManualDealProjectmargininclRiskandVD"
								disabled></input>
						</div>
					</div>


				</div>
			</div>

			<div class="divEmpty"></div>
		</div>
	</div>
</div>

<div class="divEmptyThrice"></div>
<div class="row" ng-show="isOldApprovercomment">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Approver Comments</label>
					</div>
				</div>
				<div class="panel-body">
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
						<div class="col-sm-10">
							<textarea style="resize: none" name="txtApproverComment"
								id="txtApproverComment" class="form-control" rows="3"
								ng-model="fpdcRoleSelectionFrm.approverCommentModel" required
								disabled></textarea>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<div class="row" ng-hide="rainbowApprovalPanel">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Rainbow Approval</label>

					</div>
				</div>
				<div class="panel-body">
					<div class="row marginBottom5px" ng-model="divRainbowApproval">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Is
							Rainbow Approval Required</label>
						<div class="col-sm-2">
							<select id="ddlRainbowApproval" class="form-control"
								ng-model="fpdcRoleSelectionFrm.rainbowApprovalRequired"
								placeholder="Please select" name="ddlRainbowApproval"
								ng-change="checkApprovalLevel(fpdcRoleSelectionFrm.rainbowApprovalRequired)"
								ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]"
								required>
								<option value="" selected disabled>Please select</option>
								<option value="1">Yes</option>
								<option value="2">No</option>
							</select>
						</div>
					</div>
					<div class="row marginBottom5px" ng-show="isApprovalType">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Approval
							Level</label>
						<div class="col-sm-2">
							<select id="ddlRainbowApprovalLevel" class="form-control"
								ng-model="fpdcRoleSelectionFrm.rainbowApprovalLevel"
								placeholder="Please select" name="ddlRainbowApprovalLevel"
								ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]"
								ng-options="rpc.id as rpc.name for rpc in rainbowApprovalLevel"
								required>
								<option value="" selected disabled>Please select</option>
								<!-- <option value="5">Level 1 Approval</option>
										<option value="6">Level 2 Approval</option> -->
							</select>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>
</div>

<div class="row" ng-show="isApprovercomment">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Approver Action</label>

					</div>
				</div>
				<div class="panel-body">
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Approver
							Action</label>
						<div class="col-sm-2">
							<select id="ddlApproverStatus" class="form-control"
								ng-model="fpdcRoleSelectionFrm.approvalStatus"
								placeholder="Please select" name="ddlApproverStatus"
								ng-change="getApprovalComments('<%=session.getAttribute("user")%>')"
								ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]"
								required>
								<%--                                           <select id="ddlApproverStatus" class="form-control" ng-model="fpdcRoleSelectionFrm.approvalStatus"  placeholder="Please select" name="ddlApproverStatus" ng-change="getApprovalComments('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')" ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]" required> --%>
								<option value="" selected disabled>Please select</option>
								<option value="3">Approve</option>
								<option value="4">Reject</option>
							</select>
						</div>
					</div>
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
						<div class="col-sm-10">
							<textarea style="resize: none" name="txtApproverComment"
								id="txtApproverComment" class="form-control" rows="3"
								ng-model="fpdcRoleSelectionFrm.approverCommentModel" required
								disabled></textarea>
						</div>
					</div>
					<div class="row marginBottom5px" ng-show="commentbox == true">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Enter
							your Comments</label>
						<div class="col-sm-10">
							<textarea style="resize: none" name="txtCurrentApproverComment"
								id="txtCurrentApproverComment" class="form-control" rows="3"
								ng-model="fpdcRoleSelectionFrm.currentApproverCommentModel"
								required></textarea>
						</div>
					</div>

					<!--  <input type="hidden"  name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" 
	                                			 ng-model="frmRateCardCreation.currentApproverCommentModel" disabled> -->

					<div class="row text-center">
						<div class="col-sm-12">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnAddSave"
								ng-click="saveFPMDApprovalData('<%=session.getAttribute("user")%>',fpdcRoleSelectionFrm.approvalStatus)">Save</button>
							<%-- 											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="saveFPMDApprovalData('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>',fpdcRoleSelectionFrm.approvalStatus)">Save</button>						 --%>
							<button type="button" class="btn btn-danger btnSpace"
								id="btnAddCancel">Cancel</button>
							<!-- <button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnNext" ng-click="Next()">Next</button> -->
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>
</div>
<div class="panel-body" ng-hide="AttachmentHidden">
	<div class="panel-body" ng-hide="AttachmentHide">
		<div class="col-sm-2 "></div>

		<label class="control-label col-sm-2 textAlignRight required-Field">
			Attachment </label>
		<div class="col-sm-3 ">
			<input type="file" class="form-control" name="PLAttachFilename"
				id="PLAttachFilename" ng-model="frmDeal.fuAttachFilenameModel"
				check-file-size="frmDeal.PLAttachFilenameModel" valid-file-rate-card
				ng-class="{true: 'ng-border'}[(upload) && frmDeal.PLAttachFilename.$invalid]">
		</div>
		<div class="col-sm-1">
			<button type="button" class="btn btn-primary btnSpace" id="btnUpload"
				ng-click="uploadAttachmentPLData(frmDeal);"
				ng-disabled="uploadplBtnDisable">Upload</button>
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
						ng-click="deleteFile(row.objectid);"
						ng-disabled="deleteBtnDisable"><label>Delete</label></a></td>
				</tr>
				<tr>
				</tr>
			</tbody>
		</table>
	</fieldset>
</div>

<div class="divEmptyThrice"></div>
<div class="row text-center">
	<div class="col-sm-12">
		<button type="button" class="btn btn-primary btnSpace"
			id="btnDetailsSubmitToGFT" ng-disabled="isSubmitToGFtHide"
			ng-click="updateManualDeal();">Submit To GFT</button>
		<!-- <button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-hide = "isSaveHide" ng-click="saveOrUpdateFpDealRole();">Save</button> -->
		<button type="button" class="btn btn-info btnSpace" id="btnClientPrev"
			ng-click="Prev()">Prev</button>
		<button type="button" class="btn btn-info" id="btnClientNext"
			ng-click="Next()">Next</button>
	</div>
</div>