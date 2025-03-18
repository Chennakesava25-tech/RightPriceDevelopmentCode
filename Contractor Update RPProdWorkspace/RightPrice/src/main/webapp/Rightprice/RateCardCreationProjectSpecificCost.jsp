<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Eviden RightPrice Portal</title>
    <meta charset="utf-8">
    <meta name="csrf-token" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/SAPStyleSheet.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.js"></script>
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/bootstrap-dialog.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
	<script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
    <script type="text/javascript">
    	
		var app = angular.module('RateCardCreationPFContractorCostApp ', []);
		app.controller("RateCardCreationPFContractorCostController",  ['$scope','$location','$anchorScroll','$http','$window', function($scope,$location,$anchorScroll,$http,$window,$index) {
		
		 $scope.moveTop = function()
			{
				$location.hash('PageHeading'); 
				$anchorScroll();
			};
			$scope.moveBottom = function()
			{
				$location.hash('includedFooter'); 
				$anchorScroll();
			};	
			 $scope.Prev = function()
		    {   
		        window.location='/RightPricePortal/Rightprice/RateCardCreationRoleUtilizationAndRates.jsp';
		    }; 
		     $scope.Next = function()
		    {   
		        window.location='/RightPricePortal/Rightprice/RateCardCreationSummary.jsp';
		    }; 
		}]);
    </script>
</head>
<body ng-app="RateCardCreationPFContractorCostApp " ng-controller="RateCardCreationPFContractorCostController" >
    <div id="includedContent" ng-include="'/RightPricePortal/Portal/Header.jsp'"></div>
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Rate Card Creation - Project Specific Cost</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div id="includedT&PStages" ng-include="'/RightPricePortal/Portal/RateCardCompletionStage.jsp'"></div>
            <div id="includedRateCardStages" ng-include="'/RightPricePortal/Rightprice/RateCardCreationInformationTable.jsp'"></div>
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle ">
									<div class="row ">
										<label class="control-label col-sm-10 ">Rate Card Project Specific Cost</label>
									</div>
								</div>
								<div class="panel-body">
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
															<th class="width60per">Onsite Facility Cost</th>
															<td class="width40per">20</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<label class="control-label col-sm-10 "><h5>
												<b>Project Specific Cost</b>
											</h5></label>
									</div>
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive">
												<table
													class="table tblDealCre table-borderless table-condensed"
													id="tblProjectCost">
													<thead>
														<tr>
															<th class="width12per thBorder">Sr.No.</th>
															<th class="width12per thBorder">Particulars</th>
															<th class="width12per thBorder">Rate</th>
															<th class="padLeft colRightSection" colspan="3"><a
																class="btn btn-default ancBtnWidthSum" href="#"
																tabindex="1">Summary</a></th>
															<th class="colRightSection" colspan="2"><a
																id="anc2017" class="btn btn-default" href="#"
																tabindex="2">2017</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="3">2018</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="4">2019</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="5">2020</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="6">2021</a></th>
														</tr>
														<tr>
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
													</thead>
													<tbody id="tBodyProjectCost">
														<tr>
															<td class="colSection">1</td>
															<td class="colSection"></td>
															<td class="colSection"><input type="text"
																class="form-control" id="txtProjectCostRateRow1"
																name="txtProjectCostRateRow1"
																ng-model="txtProjectCostRateRow1Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtProjectCostJanRow1" name="txtProjectCostJanRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtProjectCostFebRow1" name="txtProjectCostFebRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostMarRow1" name="txtProjectCostMarRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostAprRow1" name="txtProjectCostAprRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostMayRow1" name="txtProjectCostMayRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostJunRow1" name="txtProjectCostJunRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostJulRow1" name="txtProjectCostJulRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostAugRow1" name="txtProjectCostAugRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostSepRow1" name="txtProjectCostSepRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostOctRow1" name="txtProjectCostOctRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostNovRow1" name="txtProjectCostNovRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostDecRow1" name="txtProjectCostDecRow1"
																value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostTotalRow1"
																name="txtProjectCostTotalRow1" value="24.00"></td>
														</tr>
														<tr>
															<td class="colSection">2</td>
															<td class="colSection"></td>
															<td class="colSection"><input type="text"
																class="form-control" id="txtProjectCostRateRow2"
																name="txtProjectCostRateRow2"
																ng-model="txtProjectCostRateRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtProjectCostJanRow2" name="txtProjectCostJanRow2"
																ng-model="txtProjectCostFebRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtProjectCostFebRow2" name="txtProjectCostFebRow2"
																ng-model="txtProjectCostJanRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostMarRow2" name="txtProjectCostMarRow2"
																ng-model="txtProjectCostMarRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostAprRow2" name="txtProjectCostAprRow2"
																ng-model="txtProjectCostAprRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostMayRow2" name="txtProjectCostMayRow2"
																ng-model="txtProjectCostMayRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostJunRow2" name="txtProjectCostJunRow2"
																ng-model="txtProjectCostJunRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostJulRow2" name="txtProjectCostJulRow2"
																ng-model="txtProjectCostJulRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostAugRow2" name="txtProjectCostAugRow2"
																ng-model="txtProjectCostAugRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostSepRow2" name="txtProjectCostSepRow2"
																ng-model="txtProjectCostSepRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostOctRow2" name="txtProjectCostOctRow2"
																ng-model="txtProjectCostOctRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostNovRow2" name="txtProjectCostNovRow2"
																ng-model="txtProjectCostNovRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostDecRow2" name="txtProjectCostDecRow2"
																ng-model="txtProjectCostDecRow2Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostTotalRow2"
																name="txtProjectCostTotalRow2"
																ng-model="txtProjectCostTotalRow2Model" required></td>
														</tr>
														<tr>
															<td class="colSection">3</td>
															<td class="colSection"></td>
															<td class="colSection"><input type="text"
																class="form-control" id="txtProjectCostRateRow3"
																name="txtProjectCostRateRow3"
																ng-model="txtProjectCostRateRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtProjectCostJanRow3" name="txtProjectCostJanRow3"
																ng-model="txtProjectCostFebRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtProjectCostFebRow3" name="txtProjectCostFebRow3"
																ng-model="txtProjectCostJanRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostMarRow3" name="txtProjectCostMarRow3"
																ng-model="txtProjectCostMarRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostAprRow3" name="txtProjectCostAprRow3"
																ng-model="txtProjectCostAprRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostMayRow3" name="txtProjectCostMayRow3"
																ng-model="txtProjectCostMayRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostJunRow3" name="txtProjectCostJunRow3"
																ng-model="txtProjectCostJunRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostJulRow3" name="txtProjectCostJulRow3"
																ng-model="txtProjectCostJulRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostAugRow3" name="txtProjectCostAugRow3"
																ng-model="txtProjectCostAugRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostSepRow3" name="txtProjectCostSepRow3"
																ng-model="txtProjectCostSepRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostOctRow3" name="txtProjectCostOctRow3"
																ng-model="txtProjectCostOctRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostNovRow3" name="txtProjectCostNovRow3"
																ng-model="txtProjectCostNovRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostDecRow3" name="txtProjectCostDecRow3"
																ng-model="txtProjectCostDecRow3Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control"
																id="txtProjectCostTotalRow3"
																name="txtProjectCostTotalRow3"
																ng-model="txtProjectCostTotalRow3Model" required></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<label class="control-label col-sm-10 "><h5>
												<b>Total Project Specific Cost and Onsite Facility Cost</b>
											</h5></label>
									</div>
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive">
												<table
													class="table tblDealCre table-borderless table-condensed"
													id="tblProject&OnsiteCost">
													<thead>
														<tr>
															<th class="width12per thBorder">Sr.No.</th>
															<th class="width12per thBorder">Particulars</th>
															<th class="width12per thBorder">Rate</th>
															<th class="padLeft colRightSection" colspan="3"><a
																class="btn btn-default ancBtnWidthSum" href="#"
																tabindex="1">Summary</a></th>
															<th class="colRightSection" colspan="2"><a
																id="anc2017" class="btn btn-default" href="#"
																tabindex="2">2017</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="3">2018</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="4">2019</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="5">2020</a></th>
															<th class="colRightSection" colspan="2"><a
																class="btn btn-default" href="#" tabindex="6">2021</a></th>
														</tr>
													</thead>
													<tbody id="tBodyProject&OnsiteCost">
														<tr>
															<td class="colSection">1</td>
															<td class="colSection"></td>
															<td class="colSection"><input type="text"
																class="form-control" id="txtRateRow1" name="txtRateRow1"
																ng-model="txtRateRow1Model" required></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtJanRow1" name="txtJanRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control colSection"
																id="txtFebRow1" name="txtFebRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtMarRow1"
																name="txtMarRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtAprRow1"
																name="txtAprRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtMayRow1"
																name="txtMayRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtJunRow1"
																name="txtJunRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtJulRow1"
																name="txtJulRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtAugRow1"
																name="txtAugRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtSepRow1"
																name="txtSepRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtOctRow1"
																name="txtOctRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtNovRow1"
																name="txtNovRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtDecRow1"
																name="txtDecRow1" value="2.00"></td>
															<td class="colRightSection width5per"><input
																type="text" class="form-control" id="txtTotalRow1"
																name="txtTotalRow1" value="24.00"></td>
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
	        </form>
        </div>
    </div>
	<div id="footer" ng-include="'/RightPricePortal/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'/RightPricePortal/Portal/TopBottomNavigation.jsp'"></div>
</body>
</html>
