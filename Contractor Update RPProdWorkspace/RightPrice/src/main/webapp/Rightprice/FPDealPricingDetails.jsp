                                                        
<div class="row">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Deal Details</label>
					</div>
				</div>
				<div class="panel-body">
						
						<div class="row marginBottom5px">						
							<label class="control-label col-sm-2 textAlignRight ">Revenue
								 </label>
							<div class="col-sm-3">
								<input name="txtDealRevenue" type="text" ng-model="frmDeal.revenueModel"
									class="form-control" id="txtDealRevenue" disabled>
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Vertical
								 </label>
							<div class="col-sm-3">
								<input name="txtDealDescription" type="text" ng-model="frmDeal.verticalNameModel"
									class="form-control" id="txtDealDescription" disabled>
							</div>
						</div>
						<fieldset ng-disabled="isDisabled">
						<fieldset disabled>
						
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Direct Cost
								</label>
							<div class="col-sm-3">
								<input name="txtDealDirectCost" type="text" class="form-control" ng-model="frmDeal.directcostModel | number : 2 "
									id="txtDealDirectCost">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Local HC
								</label>
							<div class="col-sm-3">
								<input name="txtDeallocalHC" type="text" class="form-control" ng-model="frmDeal.deallocalHCModel"
									id="txtDeallocalHC">
							</div>
							
						</div>
						<div class="row marginBottom5px">
						<label class="control-label col-sm-2 textAlignRight ">PM% (Project)
								</label>
							<div class="col-sm-3">
								<input name="txtDealMargin" type="text" class="form-control" ng-model="frmDeal.grossMarginModel | number : 2 "
									id="txtDealMargin">
							</div>
							
							<label class="control-label col-sm-3 textAlignRight ">Deputed HC
								 </label>
							<div class="col-sm-3">
								<input name="txtDealDeputedHC" type="text" class="form-control" ng-model="frmDeal.dealDeputedHCModel"
									id="txtDealDeputedHC">
							</div>
							
							
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">SGA
								</label>
							<div class="col-sm-3">
								<input name="txtDealSGA" type="text" class="form-control" ng-model="frmDeal.projectSGAModel | number : 2 "
									id="txtDealSGA">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Offshore HC
								</label>
							<div class="col-sm-3">
								<input name="txtDealOffshoreHC" type="text" class="form-control" ng-model="frmDeal.dealOffshoreHCModel"
									id="txtDealOffshoreHC">
							</div>
							
						</div>
						
						
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">OM %
								</label>
							<div class="col-sm-3">
								<input name="txtDealOffshoreHC" type="text" class="form-control" ng-model="frmDeal.gmOMPer | number : 2"
									id="txtDealOffshoreHC">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Subcontractor Offshore 
								</label>
							<div class="col-sm-3">
								<input name="txtDealSubcontractorOffshore" type="text" class="form-control" ng-model="frmDeal.dealSubcontractorOffshoreModel  | number : 2"
									id="txtDealSubcontractorOffshore">
							</div>
							
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Volume Discount
								</label>
							<div class="col-sm-3">
								<input name="txtDealSubcontractorOffshore" type="text" class="form-control" ng-model="frmDeal.volumeDiscountModel | number : 2 "
									id="txtDealSubcontractorOffshore">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Subcontractor Onsite
								 </label>
							<div class="col-sm-3">
								<input name="txtDealSubcontractorOnsiteHC" type="text" class="form-control" ng-model="frmDeal.dealSubcontractorOnsiteHCModel  | number : 2"
									id="txtDealSubcontractorOnsiteHC">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">OM % after Volume disocunt
								</label>
							<div class="col-sm-3">
								<input name="txtDealOnsitePer" type="text" class="form-control" ng-model="frmDeal.gmOMVD  | number : 2"
									id="txtDealOnsitePer">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Total HC
								 </label>
							<div class="col-sm-3">
								<input name="txtDealTotalHC" type="text" class="form-control" ng-model="frmDeal.dealTotalHCModel"
									id="txtDealTotalHC">
							</div>
							
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Onsite % 
								</label>
							<div class="col-sm-3">
								<input name="txtDealOnsitePer" type="text" class="form-control" ng-model="frmDeal.dealOnsitePerModel*100  | number : 2"
									id="txtDealOnsitePer">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Offshore %
								 </label>
							<div class="col-sm-3">
								<input name="txtDealTotalHC" type="text" class="form-control" ng-model="frmDeal.dealOffshorePerModel*100  | number : 2"
									id="txtDealTotalHC">
							</div>
							
						</div>
						<div class="row marginBottom5px">
						<label class="control-label col-sm-2 textAlignRight ">Offshore B2%
								 </label>
							<div class="col-sm-3">
								<input name="txtDealOffshoreB2Per"  type="text" class="form-control" ng-model="frmDeal.OffshoreB2PerModel  | number : 2"  
									id="txtDealOffshoreB2Per">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Offshore B2 Ap1 % 
								</label>
							<div class="col-sm-3">
								<input name="txtDealOffshoreB2Ap1Per"   type="text" class="form-control" ng-model="frmDeal.dealOffshoreB2Ap1PerModel  | number : 2" 
									id="txtDealOffshoreB2Ap1Per">
							</div>
							
						</div>
						
						
					</fieldset>	
				</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row" ng-hide='OldDealShow'>
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Old Deal ID details</label>
					</div>
				</div>
				<div class="panel-body">
						
						<fieldset ng-disabled="isDisabled">
						<fieldset disabled>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Deal ID
							</label>
							<div class="col-sm-3">
								<input name="txtoldDealId" type="text" class="form-control" ng-model="frmDeal.oldDealIdModel"
									id="txtoldDealId">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Deal Description
								 </label>
							<div class="col-sm-3">
								<input name="txtoldDealDescription" type="text" ng-model="frmDeal.oldDealDescriptionModel"
									class="form-control" id="txtoldDealDescription">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Version ID
								</label>
							<div class="col-sm-3">
								<input name="txtoldDealVersion" type="text"  class="form-control" ng-model="frmDeal.oldDealVersionModel"
									id="txtoldDealVersion">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Version Description
								</label>
							<div class="col-sm-3">
								<input name="txtoldDealVersionDesc" type="text" class="form-control" ng-model="frmDeal.oldDealVersionDescModel"
									id="txtoldDealVersionDesc">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Start Date
								</label>
							<div class="col-sm-3">
								<input name="txtoldDealStart" type="text" class="form-control" ng-model="frmDeal.oldDealStartModel"
									id="txtoldDealStart">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">End date
								 </label>
							<div class="col-sm-3">
								<input name="txtoldDealEnd" type="text" class="form-control" ng-model="frmDeal.oldDealEndModel"
									id="txtoldDealEnd">
							</div>
						</div>
						
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Revenue
								</label>
							<div class="col-sm-3">
								<input name="txtoldDealRev" type="text" class="form-control" ng-model="frmDeal.oldDealRevModel"
									id="txtoldDealRev">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Onsite:Offshore Mix
								 </label>
							<div class="col-sm-3">
								<input name="txtOldDealOnsiteOffshore" type="text" class="form-control" ng-model="frmDeal.OldDealOnsiteOffshoreModel"
									id="txtOldDealOnsiteOffshore">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">OM % after Volume disocunt
								</label>
							<div class="col-sm-3">
								<input name="txtoldDealOMPerVD" type="text" class="form-control" ng-model="frmDeal.oldDealOMPerVDModel"
									id="txtoldDealOMPerVD">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">B2 Percentage

								 </label>
							<div class="col-sm-3">
								<input name="txtoldDealB2Per" type="text" class="form-control" ng-model="frmDeal.oldDealB2PerModel"
									id="txtoldDealB2Per">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">B2-AP1 Percentage
								</label>
							<div class="col-sm-3">
								<input name="txtOldDealB2AP1" type="text" class="form-control" ng-model="frmDeal.OldDealB2AP1Model"
									id="txtOldDealB2AP1">
							</div>
							
						</div>
					</fieldset>	
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
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">MSA TCV Calculations</label>
					</div>
				</div>
				<div class="panel-body">
						
						<fieldset ng-disabled="isDisabled">
						<fieldset disabled>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Onsite Hrs per month
							</label>
							<div class="col-sm-3">
								<input name="txtAddress " type="text" class="form-control" ng-model="frmDeal.onsiteHrsPerDayModel"
									id="txtAddress">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Offshore Hrs per month
								 </label>
							<div class="col-sm-3">
								<input name="txtDealDescription" type="text" ng-model="frmDeal.offshoreHrsPerDayModel"
									class="form-control" id="txtDealDescription">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Onsite Hours
								</label>
							<div class="col-sm-3">
								<input name="txtDealStatus" type="text"  class="form-control" ng-model="frmDeal.onsiteHoursModel"
									id="txtDealStatus">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Offshore Hours
								</label>
							<div class="col-sm-3">
								<input name="txtSalesSpoc " type="text" class="form-control" ng-model="frmDeal.OffshoreHoursModel"
									id="txtSalesSpoc">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Total Hours
								</label>
							<div class="col-sm-3">
								<input name="txtDealTCV" type="text" class="form-control" ng-model="frmDeal.totalHoursModel"
									id="txtDealTCV">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Onsite TCV
								 </label>
							<div class="col-sm-3">
								<input name="txtDealPriority" type="text" class="form-control" ng-model="frmDeal.onsiteTCVmodel"
									id="txtDealPriority">
							</div>
						</div>
						
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Offshore TCV
								</label>
							<div class="col-sm-3">
								<input name="txtDealTCV" type="text" class="form-control" ng-model="frmDeal.offshoreTCVModel"
									id="txtDealTCV">
							</div>
							<label class="control-label col-sm-3 textAlignRight ">Total TCV
								 </label>
							<div class="col-sm-3">
								<input name="txtDealPriority" type="text" class="form-control" ng-model="frmDeal.totalTCVModel"
									id="txtDealPriority">
							</div>
						</div>
						<div class="row marginBottom5px">
							<label class="control-label col-sm-2 textAlignRight ">Discount/Premium
								</label>
							<div class="col-sm-3">
								<input name="txtDealTCV" type="text" class="form-control" ng-model="frmDeal.discountPricingModel"
									id="txtDealTCV">
							</div>
							
						</div>
					</fieldset>	
				</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>







<!------------------------------------------------------------------------------------- download functionality starts here  -------------------------------------------------------->

						<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive panel-body" id="tableToExport"  style="display: none; " >
									<!-- 	<table border="1"   
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												<tr> <td style="border:0" ></td><td colspan="6" align="Center" bgcolor="#CCFFFF"><Strong>FP Deal Creation - Pricing Details</Strong></td></tr>
												</tbody>
												</table>
									<table></table>

										<table border="1"  
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												
													<tr  ng-repeat ="versionDetail in versionDetails" >
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Id</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.crmDealId}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Version (ID)</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealVersion}} ({{versionDetail.rpDealVersionId}})</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Customer</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealcrmstagesdata2.customer.customerName}}</td>
													</tr>
													
													<tr ng-repeat ="versionDetail in versionDetails">
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Status	</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealStatus}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Project Type</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.projectType}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Description</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealcrmstagesdata2.dealDescription}}</td>
													</tr>
													<tr ng-repeat ="dealDetailsData in dealDetails">
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Start Date (dd/mm/yyyy)</th>
														<td class="thWidth18Per"  align="left">{{dealDetailsData.dealStartDate}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">End Date (dd/mm/yyyy)</th>
														<td class="thWidth18Per"  align="left">{{dealDetailsData.dealEndDate}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Percentage Close (%)</th>
														<td class="thWidth18Per"  align="left">{{dealDetailsData.percentageClose}}</td>
													</tr>
													<tr ng-repeat ="dealData in dealDetails">
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal TCV</th>
														<td class="thWidth18Per"  align="left">{{dealData.dealTCV}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Currency</th>
														<td class="thWidth18Per"  align="left">{{dealData.currency}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Penalty (%)</th>
														<td class="thWidth18Per"  align="left">{{dealData.penaltyPercent}}</td>
													</tr>
													</tbody>
												</table> -->
<!----------------------------------------------------------------------------------------------- deal details ----------------------------------------------------------------------->
										
											<table border="1"   
												class="table clsTable table-striped table-bordered table-hover table-condensed " id="tblRateCardDetails1">
												<tbody id="tblRateCardDetails1">
												<tr>
												<th style="width:50px" align="left" bgcolor="#D35400">Sr.No.</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Date</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">New/Renewal/RFP-RFI</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Vertical</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Customer</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Project Description</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Version Name</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Deal ID</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Proj.Type</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Capacity based</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Billing Currency</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Revenue</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Cost</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">PM % (Project)</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">SGA cost</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">OM%</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Volume Discount</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">OM % after Volume disocunt</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">YTD Sold PM %</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Start Dt</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">End Dt</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">LOB</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Local HC</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Deputed HC</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Offshore HC</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Subcontractor Onsite</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Subcontractor Offshore</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Total HC</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Onsite %</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Off B2 %</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Ap1%</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Old Deal ID	</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Old PID	</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Old sold margin</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Operating metrics of old deal</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Rate card ID</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">On Hrs per month</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Off Hrs per month</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Onsite Hrs</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Off Hrs</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Total Hrs</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Onsite TCV</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Off TCV</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Total TCV</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Additonal Disc</th>
												<th class="thWidth15Per" align="left" bgcolor="#D35400">Requestor Name</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">On Hrs per month</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Off Hrs per month</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Onsite Hrs</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Off Hrs</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Total Hrs</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Onsite TCV</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Off TCV</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Total TCV</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Additonal Disc</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">USER Name</th>
												<th class="thWidth15Per" align="left" bgcolor="#ABB2B9">Comments</th>
												</tr>
												<tr ng-repeat ="excelArray in downloadExcel" >
												<td>{{$index+1}}</td>
												<td>{{excelArray.date}}</td>
												<td>{{excelArray.isNewDeal}}</td>
												<td>{{excelArray.verticalName}}</td>
												<td>{{excelArray.customerName}}<!--customer  --></td>
												<td>{{excelArray.projDescription}}</td>
												<td>{{excelArray.versionName}}</td>
												<td>{{excelArray.dealId}}</td>
												<td>{{excelArray.proj_Type}}</td>
												<td>{{excelArray.capacityBased}}</td>
												<td>{{excelArray.blngCurrency}}</td>
												<td>{{excelArray.revenue}}</td>
												<td>{{excelArray.cost}}</td>
												<td>{{excelArray.gm | number : 2}}%</td>
												<td>{{excelArray.sgaCost}}</td>
												<td>{{excelArray.om | number : 2}}%</td>
												<td>{{excelArray.volumeDisc | number : 2}}%</td>
												<td>{{excelArray.omAfterVolume | number : 2}}%</td>
												<td><!-- {{excelArray.}} --><!--YTD Sold Margin  --></td>
												<td>{{excelArray.startDt}}</td>
												<td>{{excelArray.endDt}}</td>
												<td>{{excelArray.lobCode}}</td>
												<td>{{excelArray.localHc}}</td>
												<td>{{excelArray.deputedHc}}</td>
												<td>{{excelArray.offshoreHC}}</td>
												<td>{{excelArray.subConOnsiteHC}}</td>
												<td>{{excelArray.subConOffshoreHC}}</td>
												<td>{{excelArray.totalHc}}</td>
												<td>{{excelArray.onsitePer}}% <!--Onsite %  --></td>
												<td>{{excelArray.offshoreB2 | number : 2}}%</td>
												<td>{{excelArray.offshoreB2Ap1 | number : 2}}%</td>
												<td>{{excelArray.oldDealId}}</td>
												<td>{{excelArray.oldPid}}</td>
												<td>{{excelArray.oldSoldMargin}}%</td>
												<td>
												<span ng-if=excelArray.oldMatricsB2> B2: {{excelArray.oldMatricsB2}}%</span> <span ng-if="excelArray.oldMatricsB2ap1 !='null'  || excelArray.oldMatricsOnsitePer != 'null'">, </span>
												<span ng-if=excelArray.oldMatricsB2ap1> AP1: {{excelArray.oldMatricsB2ap1}}%</span> <span ng-if=excelArray.oldMatricsOnsitePer>, </span>
												<span ng-if=excelArray.oldMatricsOnsitePer> Onsite: {{excelArray.oldMatricsOnsitePer}}%</span>
												</td>
												<td>{{excelArray.ratecardId}}</td>
												<td>{{excelArray.onsiteHrsPm1}}</td>
												<td>{{excelArray.offshoreHrsPm1}}</td>
												<td>{{excelArray.onsiteHrs1}}</td>
												<td>{{excelArray.offhrs1}}</td>
												<td>{{excelArray.totalHrs1}}</td>
												<td>{{excelArray.onsiteTcv1}}</td>
												<td>{{excelArray.offshoreTcv1}}</td>
												<td>{{excelArray.totalTcv1}}</td>
												<td>{{excelArray.addDesc1}}%</td>
												<td>{{excelArray.requestorName}}</td>
												<td>{{excelArray.onsiteHrsPm2}}</td>
												<td>{{excelArray.offshoreHrsPm2}}</td>
												<td>{{excelArray.onsiteHrs2}}</td>
												<td>{{excelArray.offhrs2}}</td>
												<td>{{excelArray.totalHrs2}}</td>
												<td>{{excelArray.onsiteTcv2}}</td>
												<td>{{excelArray.offshoreTcv2}}</td>
												<td>{{excelArray.totalTcv2}}</td>
												<td>{{excelArray.addDesc2}}%</td>
												<td>{{excelArray.userName}}</td>
												<td>{{excelArray.comment}} </td>
												
												</tr>
																									
													</tbody>
												</table>


	
									
									
									
	
												</div>
										</div>
									</div>
<!---------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
<!------------------------------------------------------------------------------ below download functionality dumped --------------------------------------------------------->
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive panel-body"   style="display: none; " >
										<table border="1"   
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												<tr> <td style="border:0" ></td><td colspan="6" align="Center" bgcolor="#CCFFFF"><Strong>FP Deal Creation - Pricing Details</Strong></td></tr>
												</tbody>
												</table>
									<table></table>

										<table border="1"  
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												
													<tr  ng-repeat ="versionDetail in versionDetails" >
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Id</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.crmDealId}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Version (ID)</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealVersion}} ({{versionDetail.rpDealVersionId}})</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Customer</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealcrmstagesdata2.customer.customerName}}</td>
													</tr>
													
													<tr ng-repeat ="versionDetail in versionDetails">
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Status	</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealStatus}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Project Type</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.projectType}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Description</th>
														<td class="thWidth18Per"  align="left">{{versionDetail.dealcrmstagesdata2.dealDescription}}</td>
													</tr>
													<tr ng-repeat ="dealDetailsData in dealDetails">
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Start Date (dd/mm/yyyy)</th>
														<td class="thWidth18Per"  align="left">{{dealDetailsData.dealStartDate}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">End Date (dd/mm/yyyy)</th>
														<td class="thWidth18Per"  align="left">{{dealDetailsData.dealEndDate}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Percentage Close (%)</th>
														<td class="thWidth18Per"  align="left">{{dealDetailsData.percentageClose}}</td>
													</tr>
													<tr ng-repeat ="dealData in dealDetails">
													<td></td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal TCV</th>
														<td class="thWidth18Per"  align="left">{{dealData.dealTCV}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Currency</th>
														<td class="thWidth18Per"  align="left">{{dealData.currency}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Penalty (%)</th>
														<td class="thWidth18Per"  align="left">{{dealData.penaltyPercent}}</td>
													</tr>
													</tbody>
												</table>
													<table></table>
--------------------------------------------------------------------------------------------- deal details ---------------------------------------------------------------------
										
											<table class=" tblTdBordere table clsTable table-striped  table-hover table-condensed  " id="tblRateCardDetails1">
												<tbody id="tblRateCardDetails1">
												<tr><td style="border:none"></td><td style="border:1px solid" colspan="4" align="Center" bgcolor="#CCFFFF"><Strong>Deal Details</Strong></td></tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Revenue</th>
												<td style="border:1px solid" align="Right">{{frmDeal.revenueModel}}</td>
												<th style="border:1px solid" align="left">Vertical</th>
												<td style="border:1px solid"  align="left">{{frmDeal.verticalNameModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid"  align="left">Direct Cost</th>
												<td style="border:1px solid"  align="Right">{{frmDeal.directcostModel | number : 2}}</td>
												<th style="border:1px solid" align="left">Local HC</th>
												<td style="border:1px solid" align="Right">{{frmDeal.deallocalHCModel}}</td>
												</tr>
												<tr>
												<td style="border:none"></td>
												<th style="border:1px solid" align="left">PM% (Project)</th>
												<td style="border:1px solid" align="Right">{{frmDeal.grossMarginModel | number : 2 }}</td>
												<th style="border:1px solid" align="left">Deputed HC</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealDeputedHCModel}}</td>
												</tr>
												<tr>
												<td style="border:none"></td>
												<th style="border:1px solid" align="left">SGA</th>
												<td style="border:1px solid" align="Right">{{frmDeal.projectSGAModel | number : 2}}</td>
												<th style="border:1px solid" align="left">Offshore HC</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealOffshoreHCModel}}</td>
												</tr>
												<tr>
												<td style="border:none"></td>
												<th style="border:1px solid" align="left">OM %</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealOffshoreHCModel}}</td>
												<th style="border:1px solid" align="left">Subcontractor Offshore</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealSubcontractorOffshoreModel  | number : 2}}</td>
												</tr>
												<tr>
												<td style="border:none"></td>
												<th style="border:1px solid" align="left">Volume Discount</th>
												<td style="border:1px solid" align="Right">{{frmDeal.volumeDiscountModel | number : 2}}</td>
												<th style="border:1px solid" align="left">Subcontractor Onsite</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealSubcontractorOnsiteHCModel  | number : 2}}</td>
												</tr>
												<tr>
												<td style="border:none"></td>
												<th style="border:1px solid" align="left">OM % after Volume disocunt</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealOnsitePerModel*100  | number : 2}}</td>
												<th style="border:1px solid" align="left">Total HC</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealTotalHCModel}}</td>
												</tr>
												<tr>
												<td style="border:none"></td>
												<th style="border:1px solid" align="left">Onsite %</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealOnsitePerModel*100  | number : 2}}</td>
												<th style="border:1px solid" align="left">Offshore %</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealOffshorePerModel*100  | number : 2}}</td>
												</tr>
												<tr>
												<td style="border:none"></td>
												<th style="border:1px solid" align="left">Offshore B2%</th>
												<td style="border:1px solid" align="Right">{{frmDeal.OffshoreB2PerModel  | number : 2}}</td>
												<th style="border:1px solid" align="left">Offshore B2 AP1 %</th>
												<td style="border:1px solid" align="Right">{{frmDeal.dealOffshoreB2Ap1PerModel  | number : 2}}</td>
												</tr>
																									
													</tbody>
												</table>
--------------------------------------------------------------------------deal details end here -----------------------------------------------------------------------		
--------------------------------------------------------------------------Old deal detials  ---------------------------------------------------------------------------
												<div ng-if='OldDealShow==false'>
												<table ></table>
												</div>
												
												<div ng-if='OldDealShow ==false'>
											<table  
												class="table clsTable table-striped  table-hover table-condensed "
												id="tblRateCardDetails"  >
												<tbody id="tBodyRateCardDetails">
												<tr> <td></td><td colspan="4" align="Center" bgcolor="#CCFFFF" style="border:1px solid" ><Strong>Old Deal ID Details</Strong></td></tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Deal ID</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealIdModel}}</td>
												<th style="border:1px solid" align="left">Deal Description</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealDescriptionModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Version ID</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealVersionModel}}</td>
												<th style="border:1px solid" align="left">Version Description</th>
												<td style="border:1px solid" align="left">{{frmDeal.oldDealVersionDescModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Start Date</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealStartModel}}</td>
												<th style="border:1px solid" align="left">End date</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealEndModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Revenue</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealRevModel}}</td>
												<th style="border:1px solid" align="left">Onsite / Offshore Mix</th>
												<td style="border:1px solid" align="Right">{{OldDealOnsiteOffshoreModelexl}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">OM % after Volume Disocunt</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealOMPerVDModel}}</td>
												<th style="border:1px solid" align="left">B2 Percentage</th>
												<td style="border:1px solid" align="Right">{{frmDeal.oldDealB2PerModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">B2-AP1 Percentage</th>
												<td style="border:1px solid" align="Right">{{frmDeal.OldDealB2AP1Model}}</td>
												<th style="border:1px solid" align="left"></th>
												<td style="border:1px solid" align="Right"></td>
												</tr>
													</tbody>
												</table>
												</div>

--------------------------------------------------------------------------Old deal detials ends here-------------------------------------------------------------------	

	
--------------------------------------------------------------------------MSA  TCV detials  ---------------------------------------------------------------------------
												<table></table>
												<table   
												class="table clsTable table-striped  table-hover table-condensed "
												id="tblRateCardDetails"  >
												<tbody id="tBodyRateCardDetails">
												<tr> <td></td><td colspan="4" align="Center" bgcolor="#CCFFFF" style="border:1px solid"><Strong>MSA TCV Calculations</Strong></td></tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Onsite Hrs per month</th>
												<td style="border:1px solid" align="Right">{{frmDeal.onsiteHrsPerDayModel}}</td>
												<th style="border:1px solid" align="left">Offshore Hrs per month</th>
												<td style="border:1px solid" align="Right">{{frmDeal.offshoreHrsPerDayModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Onsite Hours</th>
												<td style="border:1px solid" align="Right">{{frmDeal.onsiteHoursModel}}</td>
												<th style="border:1px solid" align="left">Offshore Hours</th>
												<td style="border:1px solid" align="Right">{{frmDeal.OffshoreHoursModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Total Hours</th>
												<td style="border:1px solid" align="Right">{{frmDeal.totalHoursModel}}</td>
												<th style="border:1px solid" align="left">Onsite TCV</th>
												<td style="border:1px solid" align="Right">{{frmDeal.onsiteTCVmodel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Offshore TCV</th>
												<td style="border:1px solid" align="Right">{{frmDeal.offshoreTCVModel}}</td>
												<th style="border:1px solid" align="left">Total TCV</th>
												<td style="border:1px solid" align="Right">{{frmDeal.totalTCVModel}}</td>
												</tr>
												<tr>
												<td ></td>
												<th style="border:1px solid" align="left">Discount/Premium</th>
												<td style="border:1px solid" align="Right">{{frmDeal.discountPricingModel}}</td>
												<th style="border:1px solid" align="left"></th>
												<td style="border:1px solid" align="Right"></td>
												</tr>
												</tbody>
												</table>

--------------------------------------------------------------------------Old deal detials ends here-------------------------------------------------------------------	
									
									
									
	
												</div>
										</div>
									</div> 

<div class="row text-center">
	<div class="col-sm-12">	
	<fieldset ng-disabled="isDisabled"></fieldset><br>
		<button type="button" class="btn btn-info btnSpace " id="btnPrev" ng-click="Prev()">Prev</button>
		<button type="button" class="btn btn-primary btnSpace" ng-click="exportToExcel('#tableToExport')"> <!-- ng-click="downloadWhatIfExcel();">-->
		<img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
		<button type="button" class="btn btn-info" id="btnNext" ng-click="Next()">Next</button>
	</div>
</div>


	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    


