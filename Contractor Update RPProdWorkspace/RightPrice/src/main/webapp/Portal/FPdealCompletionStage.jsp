<div  class="row">
	<div class="col-sm-12">
		<div id="slidePanel" class="slide-panel"> 
			<input type="checkbox" id="cbxDealSlidePanel" class="hideCbx">
			<label for="cbxDealSlidePanel" class="slide-panel-tab">
				<span class="glyphicon glyphicon-plus"></span>
			</label>
			<div class="slide-panel-content">
				<ul>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationDetails">FP Deal Creation - Deal details</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationRateCardAndProjectDetails">Tower Details</a></li>
					<%-- <li><a href="${contextPath}/RightPrice-DAS/FPDealCreationContractualTerms">Contractual Terms</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationTeamDetails">Team Details</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationApplicationDetails">Application Details</a></li> --%>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationRoleSelection">Role Selection</a></li>
					<%--  <li><a href="${contextPath}/RightPrice-DAS/FPDealCreationAddContractorRole">Add Contractor Role</a></li> --%>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationStaffing">Staffing</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationCostInputs">Cost Inputs (Travel / Relocation)</a></li>					
					<%-- <li><a href="${contextPath}/RightPrice-DAS/FPDealCreationProjectSpecificCost">Project Specific Cost</a></li> --%>
					<li ng-show="isDevelopment"><a href="${contextPath}/RightPrice-DAS/FPDealCreationUploadEstimationRelatedDocuments">Upload Estimation Related Documents</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationWhatIfApplicationwise">P&L Summary</a></li>
					<li ng-if="isFPDealGFT"><a href="${contextPath}/RightPrice-DAS/FPDealCreationCalculationDetails">Calculation Details</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationCostSummary">Cost Summary</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FpPricingDetails">Pricing Details</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationFinalizeDeal">Finalize Deal</a></li>
					<%-- <li><a href="${contextPath}/RightPrice-DAS/FPDealCreationYearlySummary">Yearly Summary</a></li> --%>
					<%-- <li><a href="${contextPath}/RightPrice-DAS/FPDealCreationFinaliseDeal">Finalise Deal</a></li>
					<li><a href="${contextPath}/RightPrice-DAS/FPDealCreationSummary">Summary</a></li> --%>
				</ul>
			</div><!-- close: slide-inner -->
		</div><!-- close: slide-panel-content -->
	</div>
</div>