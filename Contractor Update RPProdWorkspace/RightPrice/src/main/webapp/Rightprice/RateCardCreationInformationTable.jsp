<div class="row marginBottom5px">
   <div class="col-sm-12">
		<div class="table-responsive" >
			<table 
				class="table table-striped table-bordered table-hover table-condensed "
				id="tblRateCardDetails">
				<tbody id="tBodyRateCardDetails" >
					<tr ng-repeat ="row in rateCardInfo">
						<th>Rate Card Id</th>
						<td class="thWidth18Per">{{row.rcId}}</td>
						<th class="thWidth18Per">Name</th>
						<td class="thWidth18Per">{{row.rcName}}</td>
						<th class="thWidth15Per">Approver Status</th>
						<td class="thWidth18Per">{{status}}</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th >Start Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per">{{row.rcStartDate }}</td>
						<th class="thWidth18Per">Expected End Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per">{{row.expectedRCEndDate | date:'dd/mmm/yyyy'}}</td>
						<th class="thWidth15Per">Applicable Months</th>
						<td class="thWidth18Per">{{row.applicableMonths}}</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th>Customer</th>
						<td class="thWidth18Per">{{row.customerVerticalMapping.customer.customerName}}</td>
						<th class="thWidth18Per">Expected TCV</th>
						<td class="thWidth18Per">{{row.tvc}} - [{{currencyName}}]</td>
						<th class="thWidth15Per">Discount</th>
						<td class="thWidth18Per">{{row.volumeDiscount}}%</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th>Onsite Utilization %</th>
						<td class="thWidth18Per">{{row.expectedOnsiteResourcePercentage}}%</td>
						<th class="thWidth18Per">Offshore Utilization %</th>
						<td class="thWidth18Per">{{row.expectedOffshoreResourcePercentage}}%</td>
						<th class="thWidth15Per">PM% Post VR and Risk</th>
						<td class="thWidth18Per"><span ng-if="!row.calculatedGMPercentagePostDiscount == 0 || !row.calculatedGMPercentagePostDiscount == null">{{row.calculatedGMPercentagePostDiscount| number :2}}%</span>
						<span ng-if="row.calculatedGMPercentagePostDiscount == 0 || row.calculatedGMPercentagePostDiscount == null"> - </span></td>
					</tr>	
					<tr ng-repeat ="row in rateCardInfo">
						<th>Onsite hours/day</th>
						<td class="thWidth18Per">{{row.onsiteHoursPerDay}}</td>
						<th class="thWidth18Per">Offshore hours/day</th>
						<td class="thWidth18Per">{{row.offshoreHoursPerDay}}</td>
						<th class="thWidth15Per">Billing Currency</th>
						<td class="thWidth18Per">{{currencyName}}</td>
					</tr>	
				</tbody>
			</table>
		</div>
	</div>
</div>