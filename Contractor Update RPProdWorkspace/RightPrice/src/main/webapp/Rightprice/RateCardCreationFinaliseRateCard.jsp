<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/RateCardCreationFinaliseRateCard.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
	<script>
	$.ajaxSetup({
		headers : {
			'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
		}
	});
	</script>
</head>
<body ng-app="RightPriceApp"
	ng-controller="MapRolesToClientRoleController">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left">Rate Card Creation - Finalise Rate Card</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="frmRateCardCreationFin"
					id="frmRateCardCreationFin">
					<div id="includedRateCardStages" ng-if="autosidebar"
						ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
					<div id="includedRateCardStages" ng-if="manualsidebar"
						ng-include="'${contextPath}/Portal/RateCardManualCompletionStage.jsp'"></div>
					<div id="includedRateCardStages" ng-if="hybridsidebar"
						ng-include="'${contextPath}/Portal/RateCardHybridCompletionStage.jsp'"></div>
					<div id="includedRateCardStages"
						ng-include="'${contextPath}/Rightprice/RateCardCreationInformationTable.jsp'"></div>
					<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10 ">Approval
												Matrix for Rate Card based on PM%</label>
											<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideFinalise()"> &#9660;</a>
										</div> -->
										</div>
									</div>
									<div class="panel-body">
										<div class="divEmptyThrice"></div>
										<div class="row">
											<div class="col-sm-2"></div>
											<div class="col-sm-8">
												<div class="table-responsive  ">
													<table
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblApprovalMatrix">
														<thead>
															<tr>
																<th class="firstColLeftAlign">Approver Designation</th>
																<th>Name</th>
																<th>Status</th>
															</tr>
														</thead>
														<tbody id="tBodyApprovalMatrix">
															<tr ng-repeat="approver in approverLevels" ng-switch
																on="$index">
																<td class="firstColLeftAlign">{{approver.level}}</td>
																<td class="firstColLeftAlign">{{approver.name}}</td>
																<td class="firstColLeftAlign">{{approver.status}}</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="divEmptyThrice"></div>

										<div class="divEmptyThrice"></div>
										<div class="row" ng-show="commentBox == true">
											<div class="col-sm-12">
												<div class="panel-group">
													<div class="panel panel-info ">
														<div class="panel-heading panelHeadingStyle">
															<div class="row ">
																<label class="control-label col-sm-10 ">Comments</label>
															</div>
														</div>
														<div class="panel-body">
															<div class="row marginBottom5px">
																<label
																	class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
																<div class="col-sm-10">
																	<textarea style="resize: none" name="txtComment"
																		id="txtComment" class="form-control" rows="3"
																		ng-model="frmRateCardCreation.approverCommentModel"
																		required></textarea>
																</div>
															</div>

														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- <div class="row" ng-hide ="rainbowMatrixPanelHide">
											<div class="col-sm-12">
												<div class="panel-group">
													<div class="panel panel-info ">
														<div class="panel-heading panelHeadingStyle">
															<div class="row ">
																<div class="panel-heading panelHeadingStyle"
																	data-toggle="modal" data-target="#modalRCFinalize">
																	<label class="control-label col-sm-10 ">Rainbow
																		Approval</label> <label
																		class="glyphicon glyphicon-info-sign col-sm-2  textAlignRight"></label>
																</div>
															</div>
														</div>
														<div class="panel-body">
															<div class="row marginBottom5px"
																ng-model="divRainbowApproval">
																<label
																	class="control-label col-sm-3 textAlignRight required-Field">Is
																	Rainbow Approval Required</label>
																<div class="col-sm-3">
																	<select id="ddlRainbowApproval" class="form-control"
																		ng-model="frmRateCardCreationFin.rainbowApprovalRequired"
																		placeholder="Please select" name="ddlRainbowApproval"
																		ng-change="checkApprovalLevel(frmRateCardCreationFin.rainbowApprovalRequired)"
																		ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]"
																		ng-options="raf.id as raf.name for raf in rainbowApprovalFlag"
																		required>
																		<option value="" selected disabled>Please
																			select</option>
																		<option value="1">Yes</option>
																		<option value="2">No</option>
																	</select>
																</div>
																<div class="row marginBottom5px"
																	ng-show="RainbowLevelDD">
																	<label
																		class="control-label col-sm-2 textAlignRight required-Field">Approval
																		Level</label>
																	<div class="col-sm-2">
																		<select id="ddlRainbowApprovalLevel"
																			class="form-control"
																			ng-model="frmRateCardCreationFin.rainbowApprovalLevel"
																			placeholder="Please select"
																			name="ddlRainbowApprovalLevel"
																			ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]"
																			ng-options="rpc.id as rpc.name for rpc in rainbowApprovalLevel"
																			required>
																			<option value="" selected disabled>Please
																				select</option>
																			<option value="5">Level 1 Approval</option>
										<option value="6">Level 2 Approval</option>
																		</select>
																	</div>
																</div>
															</div>

														</div>

													</div>
												</div>

											</div>
										</div> -->


										<div class="divEmptyThrice"></div>
										<div class="row text-center">
											<div class="col-sm-12">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnSendForApproval" ng-click="sendForApproval()"
													ng-disabled="isDisabled">Send For Approval</button>
												<button type="button" class="btn btn-info btnSpace "
													id="btnPrev" ng-click="Prev()">Prev</button>
												<button type="button" class="btn btn-info btnSpace "
													id="btnRecycle" ng-click="recycledClick()"
													ng-show="isRecycleBtn == true">Recycle</button>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="divEmptyThrice"></div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel-group">
						<div class="panel panel-info ">
							<div class="panel-heading panelHeadingStyle">
								<div class="row ">
									<label class="control-label col-sm-10 ">Note:</label>
									<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideFinalise()"> &#9660;</a>
										</div> -->

								</div>
								<div ng-repeat="assum in ratecardAssumption">{{assum.approverComments}}</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div id="modalRCFinalize" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header bootstrap-dialog-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title" style="text-align-last: center">Approval
								Matrix</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-sm-12">
									<div class="table-responsive  ">
										<table
											class="table clsTable table-striped table-bordered table-hover table-condensed "
											id="tblViewVertical">
											<thead>
												<tr>
													<th scope="col" colspan="2">Levels</th>
													<th scope="col">Assisgnment Type</th>
													<th scope="col">FR, GE, UK,<br> BTN, CEE, NAO
													</th>
													<th scope="col">MEA, SAM, <br>APAC, IBERIA
													</th>
													<th scope="col">Final Joint Authorizers (FJAs)<</th>
												</tr>
											</thead>
											<tr>
												<td rowspan="2">1</td>
												<td rowspan="2">Atos Top Mgmt.</td>
												<td>FPD/FPM</td>
												<td>> &euro;100M</td>
												<td>> &euro; 100M</td>
												<td rowspan="2">Eric GRALL<br> Elie GIRARD
												</td>
											</tr>
											<tr>
												<td>T&M/Others</td>
												<td>>&euro;80M</td>
												<td>>&euro;80M</td>
											</tr>
											<tr>
												<td rowspan="2">2</td>
												<td rowspan="2">Atos B&PS Mgmt.</td>
												<td>FPD/FPM</td>
												<td>&euro;20M - &euro;100M</td>
												<td>&euro;10M - &euro;100M</td>
												<td rowspan="2">Sean NARAYANAN + Trustees</br> Darren
													PILCHER + Trustees
												</td>
											</tr>
											<tr>
												<td>T&M/Others</td>
												<td>&euro;20M - &euro;80M</td>
												<td>&euro;10M - &euro;80M</td>
											</tr>

											<tr>
												<td rowspan="2">3</td>
												<td rowspan="2">Atos/Syntel Mgmt.</td>
												<td>FPD/FPM</td>
												<td rowspan="2">$250K - &euro;20M</td>
												<td rowspan="2">$250K - &euro;10M</td>
												<td>Authorization Sub-matrix + QA team </br> (additional CEO
													approval for deals >$1M)
												</td>
											</tr>
											<tr>
												<td>FPD/FPM</td>
												<td>Authorization Sub-matrix</td>
											</tr>

											<tr>
												<td rowspan="2">4</td>
												<td rowspan="2">Atos/Syntel Market & Delivery</td>
												<td>FPD</td>
												<td rowspan="2">$100K - $250K</td>
												<td rowspan="2">$100K - $250K</td>
												<td>GM > 0: BUH, CDO, DH, QA</br> GM &#x2264; 0:
													Authorization Sub-matrix
												</td>
											</tr>
											<tr>
												<td>FPM</td>
												<td>GM > 0: BUH, CDO, DH </br> GM &#x2264;0: Authorization
													Sub-matrix

												</td>
											</tr>
											<tr>
												<td rowspan="2">5</td>
												<td rowspan="2">Atos/Syntel Delivery</td>
												<td>FPD</td>
												<td rowspan="2">$0 - $100K</td>
												<td rowspan="2">$0 - $100K</td>
												<td>GM > 0: CDO, DH, QA</br> GM &#x2264; 0: Authorization
													Sub-matrix
												</td>
											</tr>
											<tr>
												<td>FPM</td>
												<td>GM > 0: DH</br> GM &#x2264; < 0: Authorization
													Sub-matrix
												</td>
											</tr>
										</table>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice"></div>
									<div class="table-responsive">
										<table
											class="table clsTable table-striped table-bordered table-hover table-condensed "
											id="tblViewVertical">
											<thead>
												<tr>
													<th rowspan="2"
														style="border: none; background-color: white; border-left-style: hidden !important; border-top-style: hidden !important;"></th>
													<th class="textAlignCenter" rowspan="2"
														style="vertical-align: middle">Offshore Ratio</th>
													<th class="textAlignCenter" colspan="3">Final Joint Authorizers(FJAs)</th>
												</tr>
												<tr>
													<th class="textAlignCenter">DH</th>
													<th class="textAlignCenter">CDO,BUH</th>
													<th class="textAlignCenter">CDO,BUH,CEO</th>
												</tr>
											</thead>

											<tbody id="tBodyViewVertical">
												<tr>
													<td rowspan="2">FP Deals</td>
													<td>>= 60%</td>
													<td>GM >= 40%</td>
													<td>GM >= 35%</td>
													<td><35%</td>
												</tr>
												<tr>
													<td><60%</td>
													<td>GM >= 35%</td>
													<td>GM >= 28%</td>
													<td><28%</td>
												</tr>
												<tr>
													<td>T&M Rate Cards</td>
													<td>Any</td>
													<td>GM >= 35%</td>
													<td>GM >= 35%</td>
													<td><35%</td>
												</tr>
											</tbody>
										</table>
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
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
