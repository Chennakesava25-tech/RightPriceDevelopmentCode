<div class="row marginBottom5px">
	<div class="col-sm-12">
		<div class="table-responsive" >
			<table 
				class="table clsTable table-striped table-bordered table-hover table-condensed "
				id="tblRateCardDetails">
				<tbody id="tBodyRateCardDetails" >
					<!-- <tr ng-repeat ="dealDetail in dealDetails" >
						<th class="thWidth15Per">Id</th>
						<td class="thWidth18Per">{{dealDetail.dealId}}</td>
						<th class="thWidth15Per">Customer Name</th>
						<td class="thWidth18Per">{{dealDetail.customerName}}</td>
						<th class="thWidth15Per">Deal Description </th>
						<td class="thWidth18Per">{{dealDetail.dealDescription}}</td>
					</tr>
					<tr ng-repeat ="dealDetailData in dealDetails">
						<th class="thWidth15Per">Deal Status</th>
						<td class="thWidth18Per">{{dealDetailData.dealStatus}}</td>
						<th class="thWidth15Per">Start Date</th>
						<td class="thWidth18Per">{{dealDetailData.dealStartDate}}</td>
						<th class="thWidth15Per">End Date</th>
						<td class="thWidth18Per">{{dealDetailData.dealEndDate}}</td>
					</tr>
					<tr ng-repeat ="dealDetailsData in dealDetails">
						<th class="thWidth15Per">FP Type </th>
						<td class="thWidth18Per">{{ dealDetailsData.fpType }}</td>
						<th class="thWidth15Per">Deal Type</th>
						<td class="thWidth18Per">{{ dealDetailsData.dealTypeId }}</td>
						<th class="thWidth15Per">Percentage Close</th>
						<td class="thWidth18Per">{{dealDetailsData.percentageClose}}</td>
					</tr>
					<tr ng-repeat ="dealData in dealDetails">
						<th class="thWidth15Per">Duration</th>
						<td class="thWidth18Per">{{dealDetailData.dealStartDate - dealDetailData.dealEndDate}}</td>
						<th class="thWidth15Per">Currency</th>
						<td class="thWidth18Per">{{dealData.currency}}</td>
						<th class="thWidth15Per">Stage</th>
						<td class="thWidth18Per">{{dealData.stages}}</td>
					</tr>	
					<tr  ng-repeat ="dealData in dealDetails">
						<th class="thWidth15Per">Penalty percentage </th>
						<td class="thWidth18Per">{{dealData.penaltyPercent}}</td>
						<th class="thWidth15Per">Deal Version</th>
						<td class="thWidth18Per">{{dealData.rpVersionId}}</td>
						<th class="thWidth15Per"></th>
						<td class="thWidth18Per"> </td>
					</tr> -->
					<!-- 
					<tr ng-repeat ="dealDetail in dealDetails" >
						<th class="thWidth15Per">Deal Id</th>
						<td class="thWidth18Per">{{dealDetail.dealId}}</td>
						<th class="thWidth15Per">Deal Version (ID)</th>
						<td class="thWidth18Per">{{dealDetail.dealVersion}} ({{dealDetail.rpVersionId}})</td>
					 	<td class="thWidth18Per">{{dealDetail.rpVersionId}}</td>
						<th class="thWidth15Per">Customer Name</th>
						<td class="thWidth18Per">{{dealDetail.customerName}}</td>
						
					</tr>
					<tr ng-repeat ="dealDetailData in dealDetails">
						<th class="thWidth15Per">Deal Status</th>
						<td class="thWidth18Per">{{dealDetailData.dealStatus}}</td>
						<th class="thWidth15Per">Project Type </th>
						<td class="thWidth18Per">{{ dealDetailData.fpType }}</td>						
						<th class="thWidth15Per">Deal Description </th>
						<td class="thWidth18Per">{{dealDetailData.dealDescription}}</td>
						
					</tr>
					<tr ng-repeat ="dealDetailsData in dealDetails">
						<th class="thWidth15Per">Start Date (DD/MM/YYYY)</th>
						<td class="thWidth18Per">{{dealDetailsData.dealStartDate}}</td>
						<th class="thWidth15Per">End Date (DD/MM/YYYY)</th>
						<td class="thWidth18Per">{{dealDetailsData.dealEndDate}}</td>
						<th class="thWidth15Per">Duration</th>
						<td class="thWidth18Per">{{dealDetailsData.DealDuration}}</td>
						<th class="thWidth15Per">Percentage Close</th>
						<td class="thWidth18Per">{{dealDetailsData.percentageClose}}</td>
					</tr>
					<tr ng-repeat ="dealData in dealDetails">
						<th class="thWidth15Per">Deal TCV</th>
						<td class="thWidth18Per">{{dealData.dealTCV}}</td>
						<th class="thWidth15Per">Currency</th>
						<td class="thWidth18Per">{{dealData.currency}}</td>
						<th class="thWidth15Per">Penalty(%) </th>
						<td class="thWidth18Per">{{dealData.penaltyPercent}}</td>
						<th class="thWidth15Per">Stage</th>
						<td class="thWidth18Per">{{dealData.stages}}</td>
						
						
					</tr>	 -->
					
					
						<tr ng-repeat ="versionDetail in versionDetails" >
						<th class="thWidth15Per">Deal Id</th>
						<td class="thWidth18Per">{{versionDetail.crmDealId}}</td>
						<th class="thWidth15Per">Deal Version (ID)</th>
						<td class="thWidth18Per">{{versionDetail.dealVersion}} ({{versionDetail.rpDealVersionId}})</td>
					<!--  	<td class="thWidth18Per">{{dealDetail.rpVersionId}}</td> -->
						<th class="thWidth15Per">Customer Name</th>
						<td class="thWidth18Per">{{versionDetail.dealcrmstagesdata2.customer.customerName}}</td>
						
					</tr>
					<tr ng-repeat ="versionDetail in versionDetails">
						<th class="thWidth15Per">Deal Status</th>
						<td class="thWidth18Per">{{versionDetail.dealStatus}}</td>
						<th class="thWidth15Per">Project Type </th>
						<td class="thWidth18Per">{{versionDetail.projectType}}</td>						
						<th class="thWidth15Per">Deal Description </th>
						<td class="thWidth18Per">{{versionDetail.dealcrmstagesdata2.dealDescription}}</td>
						
					</tr>
					<tr ng-repeat ="dealDetailsData in dealDetails">
						<th class="thWidth15Per">Start Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per">{{dealDetailsData.dealStartDate}}</td>
						<th class="thWidth15Per">End Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per">{{dealDetailsData.dealEndDate}}</td>
						<!-- <th class="thWidth15Per">Duration</th>
						<td class="thWidth18Per">{{dealDetailsData.DealDuration}}</td> -->
						<th class="thWidth15Per">Percentage Close (%)</th>
						<td class="thWidth18Per">{{dealDetailsData.percentageClose}}</td>
					</tr>
					<tr ng-repeat ="dealData in dealDetails">
						<th class="thWidth15Per">Deal TCV</th>
						<td class="thWidth18Per">{{dealData.dealTCV}}</td>
						<th class="thWidth15Per">Currency</th>
						<td class="thWidth18Per">{{dealData.currency}}</td>
						<th class="thWidth15Per">Penalty (%) </th>
						<td class="thWidth18Per">{{dealData.penaltyPercent}}</td>
						<!-- <th class="thWidth15Per">Stage</th>
						<td class="thWidth18Per">{{dealData.stages}}</td> -->
						
						
					</tr>	
					
				</tbody>
			</table>
		</div>
	</div>
</div>