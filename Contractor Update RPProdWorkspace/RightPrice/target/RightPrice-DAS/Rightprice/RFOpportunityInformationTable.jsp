
<div class="row"> 
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10">Deal Details</label>
					</div>
				</div>
				<div class="row marginBottom5px">
					<div class="col-sm-12">
						<div class="table-responsive panel-body">
							<table
								class="table clsTable table-striped table-bordered table-hover table-condensed "
								id="tblRateCardDetails">
								<tbody id="tBodyRateCardDetails">
									<tr>
										<th class="thWidth15Per">Deal Id</th>
										<td class="thWidth18Per">{{rfDetail.cRMDealId}}</td>
										<th class="thWidth15Per">Customer</th>
										<td class="thWidth18Per">{{rfDetail.customerName}}</td>
									</tr>
									<tr>
										<th class="thWidth15Per">Deal Description</th>
										<td class="thWidth18Per">{{rfDetail.deal_Description}}</td>
										<th class="thWidth15Per">Deal Type</th>
										<td class="thWidth18Per">{{rfDetail.deal_Type_Id}}</td>
									</tr>
									<tr>
										<th class="thWidth15Per">Deal Start Date (dd/mm/yyyy)</th>
										<td class="thWidth18Per">{{rfDetail.deal_Start_Date}}</td>
										<th class="thWidth15Per">Deal End Date (dd/mm/yyyy)</th>
										<td class="thWidth18Per">{{rfDetail.deal_End_Date}}</td>
									</tr>
									<tr>
										<th class="thWidth15Per">Industry</th>
										<td class="thWidth18Per">{{rfDetail.industry}}</td>
										<th></th>
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
