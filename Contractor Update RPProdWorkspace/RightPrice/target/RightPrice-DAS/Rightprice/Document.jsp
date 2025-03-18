<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<title>Eviden  RightPrice Portal</title>

<meta charset="utf-8">

<meta name="csrf-token" content="${_csrf.token}" />

<meta name="_csrf_header" content="${_csrf.headerName}" />

<meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" />

<link href="${contextPath}/resources/css/SAPStyleSheet.css"
	rel="stylesheet" />

<link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />

<link href="${contextPath}/resources/css/AngularCSS.css"
	rel="stylesheet" />

<script src="${contextPath}/resources/js/jquery.min.js"></script>

<script src="${contextPath}/resources/js/bootstrap.js"></script>

<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>

<script src="${contextPath}/resources/js/jquery-ui.js"></script>

<link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />

<link href="${contextPath}/resources/css/bootstrap-dialog.css"
	rel="stylesheet" />

<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>

<script src="${contextPath}/resources/js/jquery.validate.js"></script>

<script src="${contextPath}/resources/js/jqueryValidations.js"></script>

<script src="${contextPath}/resources/js/additional-methods.js"></script>

<script src="${contextPath}/resources/js/jqueryValidations.js"></script>

<link
	href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet" />

<link href="${contextPath}/resources/css/sticky-footer-navbar.css"
	rel="stylesheet" />

<script
	src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>

<script src="${contextPath}/resources/js/angular.js"></script>

<script src="${contextPath}/resources/js/angular-ui.min.js"></script>

<script src="${contextPath}/resources/js/angular-messages.js"></script>

<script src="${contextPath}/resources/js/ngStorage.js"></script>

<script src="${contextPath}/resources/js/loader.js"></script>

<script src="${contextPath}/resources/js/RightPrice/ProcessDocuments.js"></script>

<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>

<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>

</head>

<body ng-app="RightPriceApp" ng-controller="processDocumentController">

	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>

	<fieldset ng-disabled="loading || showLoader">

		<div class="container">

			<div class="divEmpty"></div>

			<div class="row marginBottom5px">

				<div class="col-sm-12">

					<h3 class="text-left" id="PageHeading">Documents</h3>

				</div>

			</div>

			<div>

				<form class="form-inline" role="form" name="Master" id="Master">

					<div class="row">

						<div class="col-sm-12">

							<div class="panel-group">

								<div class="panel panel-info">

									<ng-form name="viewAllowancesForm" id="viewAllowancesForm">

									<div class="panel-heading panelHeadingStyle"
										ng-click="ShowHideSearch()">

										<div class="row ">

											<label class="control-label col-sm-10 ">Rate Card</label> <label
												class="DownArrowColor col-sm-2  textAlignRight">
												&#9660;</label>

										</div>

									</div>

									<div class="panel-body" ng-hide="SearchHidden">

										<b><h4 class="text-left" id="PageHeading">Process
												Documents</h4></b>

										<div class="row">

											<div class="col-sm-12  ">

												<div class="table-responsive">

													<table
														class="clsTable table-striped table-bordered table-hover table-condensed "
														id="tblViewVertical">

														<thead>

															<tr>

																<th width="75px">Sr No.</th>

																<th width="420px">Description</th>

																<th width="650px">Download</th>

															</tr>

														</thead>

														<tbody id="tBodyViewVertical">

															<tr>

																<td class="tdTextAlignLeft">1</td>

																<td class="tdTextAlignLeft">Automatic rate card-
																	Single Country & Single City</td>



																<td class="tdTextAlignLeft"><a
																	href="${contextPath}/resources/Documents/Rate card  Automatic Process - Single Country & Single City - RP 2.0.pptx"
																	target="_blank" class="anchorTrancColor"
																	download="Rate card  Automatic Process - Single Country & Single City - RP 2.0.pptx"><span><b>Rate
																				card Automatic Process - Single Country & Single
																				City - RP 2.0</b></span></a></td>





															</tr>

															<tr>

																<td class="tdTextAlignLeft">2</td>

																<td class="tdTextAlignLeft">Automatic rate card-
																	Mutli country & Multi City</td>

																<td class="tdTextAlignLeft"><a
																	href="${contextPath}/resources/Documents/Rate card  Automatic Process -  Multi Country &  Multi City - RP 2.0.pptx"
																	target="_blank" class="anchorTrancColor"
																	download="Rate card  Automatic Process -  Multi Country &  Multi City - RP 2.0.pptx"><span><b>Rate
																				card Automatic Process - Multi Country & Multi City
																				- RP 2.0</b></span></a></td>

															</tr>

															<tr>

																<td class="tdTextAlignLeft">3</td>

																<td class="tdTextAlignLeft">Automatic rate card-
																	System + Manual</td>

																<td class="tdTextAlignLeft"><a
																	href="${contextPath}/resources/Rate card  Automatic Process -  Hybrid  -RP 2.0.pptx"
																	target="_blank" class="anchorTrancColor"
																	download="Rate card  Automatic Process -  Hybrid  -RP 2.0.pptx"><span><b>Rate
																				card Automatic Process - Hybrid -RP 2.0.</b></span></a></td>

															</tr>

															<tr>

																<td class="tdTextAlignLeft">4</td>

																<td class="tdTextAlignLeft">Manual Rate card</td>

																<td class="tdTextAlignLeft"><a
																	href="${contextPath}/resources/Documents/Rate card  Manual Process -  RP 2.0.pptx"
																	target="_blank" class="anchorTrancColor"
																	download="Rate card  Manual Process -  RP 2.0.pptx"><span><b>Rate
																				card Manual Process - RP 2.0</b></span></a></td>

															</tr>

														</tbody>

													</table>



													<div class="divEmptyThrice"></div>

													<b><h4 class="text-left" id="PageHeading">Checklist</h4></b>

													<table
														class="clsTable table-striped table-bordered table-hover table-condensed "
														id="tblViewVertical">

														<thead>

															<tr>

																<th width="75px">Sr No.</th>

																<th width="420px">Description</th>

																<th width="650px">Download</th>



															</tr>

														</thead>

														<tbody>

															<tr>

																<td>1</td>

																<td>T&M Rate card Checklist</td>

																<td><a
																	href="${contextPath}/resources/Documents/TM Global Rate card Checklist-2019.xlsx"
																	target="_blank" class="anchorTrancColor"
																	download="TM Global Rate card Checklist-2019.xlsx"><span><b>TM
																				Global Rate card Checklist-2019 </b></span></a></td>

															</tr>

														</tbody>

													</table>



												</div>

											</div>

										</div>

									</div>
								</div>

								</ng-form>

							</div>

						</div>

					</div>
			</div>

			<div class="row">

				<div class="col-sm-12">

					<div class="panel-group">

						<div class="panel panel-info ">

							<ng-form name="addAllowancesForm" id="addAllowancesForm">

							<div class="panel-heading panelHeadingStyle"
								ng-click="ShowHideAdd()">

								<div class="row ">

									<label class="control-label col-sm-10 ">FP - Deal</label> <label
										class="DownArrowColor col-sm-2  textAlignRight">
										&#9660;</label>

								</div>

							</div>

							<div class="panel-body" ng-hide="AddHidden">

								<div class="row">

									<div class="col-sm-12  ">

										<b><h4 class="text-left" id="PageHeading">Process
												Documents</h4></b>

										<div class="table-responsive">

											<table
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblViewVertical">

												<thead>

													<tr>

														<th width="75px">Sr No.</th>

														<th width="420px">Description</th>

														<th width="650px">Download</th>

													</tr>

												</thead>

												<tbody id="tBodyViewVertical">

													<tr>

														<td class="tdTextAlignLeft">1</td>

														<td class="tdTextAlignLeft">Automatic Deal</td>

														<td class="tdTextAlignLeft"><a
															href="${contextPath}/resources/Documents/Fixed Price Deals - RP 2.0.pptx"
															target="_blank" class="anchorTrancColor"
															download="Fixed Price Deals - RP 2.0.pptx"><span><b>Fixed
																		Price Deals - RP 2.0</b></span></a></td>

													</tr>

													<tr>

														<td class="tdTextAlignLeft">2</td>

														<td class="tdTextAlignLeft">Manual Deal</td>

														<td class="tdTextAlignLeft"><b></td>

													</tr>

												</tbody>

											</table>

											<div class="divEmptyThrice"></div>

											<b><h4 class="text-left" id="PageHeading">Checklist</h4></b>

											<div class="table-responsive  ">

												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewVertical">

													<thead>

														<tr>

															<th width="75px">Sr No.</th>

															<th width="420px">Description</th>

															<th width="650px">Download</th>

														</tr>

													</thead>



													<tbody id="tBodyViewVertical">

														<tr>

															<td class="tdTextAlignLeft">1</td>

															<td class="tdTextAlignLeft">Checklist for US</td>

															<td class="tdTextAlignLeft"><a
																href="${contextPath}/resources/Documents/US FP Staffing Template 2019.xlsx"
																target="_blank" class="anchorTrancColor"
																download="US FP Staffing Template 2019.xlsx"><span><b>US
																			FP Staffing Template 2019</b></span></a></td>

														</tr>

														<tr>

															<td class="tdTextAlignLeft">2</td>

															<td class="tdTextAlignLeft">Checklist for UK</td>

															<td class="tdTextAlignLeft"><a
																href="${contextPath}/resources/Documents/UK_FP_2018.xlsx"
																target="_blank" class="anchorTrancColor"
																download="UK_FP_2018.xlsx"><span><b>UK FP
																			Staffing Template 2018</b></span></a></td>

														</tr>

														<tr>

															<td class="tdTextAlignLeft">3</td>

															<td class="tdTextAlignLeft">Checklist for Germany</td>

															<td class="tdTextAlignLeft"><a
																href="${contextPath}/resources/Documents/GERMANY FP Staffing Template 2019.xlsx"
																target="_blank" class="anchorTrancColor"
																download="GERMANY FP Staffing Template 2019.xlsx"><span><b>GERMANY
																			FP Staffing Template 2019</b></span></a></td>

														</tr>

														<tr>

															<td class="tdTextAlignLeft">4</td>

															<td class="tdTextAlignLeft">Checklist for Other
																Countries</td>

															<td class="tdTextAlignLeft"><a
																href="${contextPath}/resources/Documents/Other Countries FP Staffing Template-2019.xlsx"
																target="_blank" class="anchorTrancColor"
																download="Other Countries FP Staffing Template-2019.xlsx"><span><b>Other
																			Countries FP Staffing Template-2019</b></span></a></td>

														</tr>

													</tbody>

												</table>

											</div>

										</div>

									</div>

								</div>

							</div>

							</ng-form>

						</div>

					</div>

				</div>

			</div>

			<div class="row">

				<div class="col-sm-12">

					<div class="panel-group">

						<div class="panel panel-info ">

							<ng-form name="updateAllowancesForm" id="updateAllowancesForm">

							<div class="panel-heading panelHeadingStyle"
								ng-click="ShowHideUpdate()">

								<div class="row ">

									<label class="control-label col-sm-10 ">KPO - Rate card
										& Deal</label> <label class="DownArrowColor col-sm-2  textAlignRight">
										&#9660;</label>

								</div>

							</div>

							<div class="panel-body" ng-hide="UpdateHidden">

								<div class="row marginBottom5px">

									<div class="col-sm-12  ">

										<b><h4 class="text-left" id="PageHeading">Process
												Documents</h4></b>

										<div class="table-responsive">

											<table
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblViewVertical">

												<thead>

													<tr>

														<th width="75px">Sr No.</th>

														<th width="420px">Description</th>

														<th width="650px">Download</th>

													</tr>

												</thead>

												<tbody id="tBodyViewVertical">

													<tr>

														<td class="tdTextAlignLeft">1</td>

														<td class="tdTextAlignLeft">KPO Rate card workflow</td>

														<td class="tdTextAlignLeft"><a
															href="${contextPath}/resources/Documents/Rate card  KPO Process -  RP 2.0.pptx"
															target="_blank" class="anchorTrancColor"
															download="Rate card  KPO Process -  RP 2.0.pptx"><span><b>Rate
																		card KPO Process - RP 2.0</b></span></a></td>

													</tr>

													<tr>

														<td class="tdTextAlignLeft">2</td>

														<td class="tdTextAlignLeft">KPO Deal workflow</td>

														<td class="tdTextAlignLeft"></td>

													</tr>

												</tbody>

											</table>

											<div class="divEmptyThrice"></div>

											<b><h4 class="text-left" id="PageHeading">Checklist</h4></b>

											<div class="table-responsive  ">

												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewVertical">

													<thead>

														<tr>

															<th width="75px">Sr No.</th>

															<th width="420px">Description</th>

															<th width="650px">Download</th>

														</tr>

													</thead>



													<tbody id="tBodyViewVertical">

														<tr>

															<td class="tdTextAlignLeft">1</td>

															<td class="tdTextAlignLeft">KPO Checklist</td>

															<td class="tdTextAlignLeft"><a
																href="${contextPath}/resources/Documents/KPO Staffing Template.xls"
																target="_blank" class="anchorTrancColor"
																download="KPO Staffing Template.xls"><span><b>KPO
																			Staffing Template</b></span></a></td>

														</tr>

													</tbody>

												</table>

											</div>

										</div>

									</div>

								</div>

								<div class="divEmptyThrice"></div>

							</div>

							</ng-form>

						</div>

					</div>

				</div>

			</div>

			<div class="row">

				<div class="col-sm-12">

					<div class="panel-group">

						<div class="panel panel-info ">

							<ng-form name="uploadAllowance" id="uploadAllowance">

							<div class="panel-heading panelHeadingStyle"
								ng-click="ShowHideUpload()">

								<div class="row ">

									<label class="control-label col-sm-10 ">Other Documents</label>

									<label class="DownArrowColor col-sm-2  textAlignRight">
										&#9660;</label>

								</div>

							</div>

							<div class="panel-body" ng-hide="UploadHidden">



								<div class="row marginBottom5px">

									<div class="col-sm-12  ">

										<div class="table-responsive">

											<div class="table-responsive  ">

												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewVertical">

													<thead>

														<tr>

															<th width="75px">Sr No.</th>

															<th width="420px">Description</th>

															<th width="650px">Download</th>

														</tr>

													</thead>



													<tbody id="tBodyViewVertical">

														<tr>

															<td class="tdTextAlignLeft">1</td>

															<td class="tdTextAlignLeft">Standard Cost Rates (SCRs)</td>

															<td class="tdTextAlignLeft"><a
																href="${contextPath}/resources/Documents/(rc) (SCR) -RATE-CARD -3.o.xlsx"
																target="_blank" class="anchorTrancColor"
																download="Standard_Cost_Rates.xls"><span><b>Standard Cost Rates (SCRs)</b></span></a></td>

														</tr>

													</tbody>

												</table>

											</div>

										</div>

									</div>

								</div>

							</div>

							</ng-form>

						</div>

					</div>

				</div>

			</div>



			<div class="row">

				<div class="col-sm-12">

					<div class="panel-group">

						<div class="panel panel-info ">

							<ng-form name="uploadAllowance" id="uploadAllowance">

							<div class="panel-heading panelHeadingStyle"
								ng-click="ShowHideVideo()">

								<div class="row ">

									<label class="control-label col-sm-10 ">Training Videos</label>
									<label class="DownArrowColor col-sm-2  textAlignRight">
										&#9660;</label>

								</div>

							</div>

							<div class="panel-body" ng-hide="VideoHidden">



								<div class="row marginBottom5px">

									<div class="col-sm-12  ">

										<div class="table-responsive">

											<div class="table-responsive  ">

												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewVertical">

													<thead>

														<tr>

															<th width="75px">Sr No.</th>

															<th width="420px">Category</th>

															<th width="650px">Download</th>

														</tr>

													</thead>



													<tbody id="tBodyViewVertical">

														<tr>
															<td class="tdTextAlignLeft">1</td>

															<td class="tdTextAlignLeft">Rate Card </td>

															<td class="tdTextAlignLeft"><a
																ng-click="downloadVideos(0)" class="anchorTrancColor"
																download="MT-220104-AP-VB-RIGHTPRICE TRAINING T&M RATE CARDS.mp4"><span><b>MT-220104-AP-VB-RIGHTPRICE
																			TRAINING T&M RATE CARDS</b></span></a></td>
															
														</tr>
														<tr>
															<td class="tdTextAlignLeft">2</td>

															<td class="tdTextAlignLeft">T&M </td>

															<td class="tdTextAlignLeft"><a
																ng-click="downloadVideos(1)" class="anchorTrancColor"
																download="MT-220104-AP-VB-RIGHTPRICE TRAINING T&M DEAL.mp4"><span><b>MT-220104-AP-VB-RIGHTPRICE
																			TRAINING T&M DEAL</b></span></a></td>
															
															

														</tr>
														<tr>

															<td class="tdTextAlignLeft">3</td>

															<td class="tdTextAlignLeft">Fixed Price </td>

															<td class="tdTextAlignLeft"><a
																ng-click="downloadVideos(2)" class="anchorTrancColor"
																download="MT-220104-AP-VB-RIGHTPRICE TRAINING FIXED PRICE.mp4"><span><b>MT-220104-AP-VB-RIGHTPRICE
																			TRAINING FIXED PRICE</b></span></a></td>

														</tr>

													</tbody>

												</table>

											</div>

										</div>

									</div>

								</div>

							</div>

							</ng-form>

						</div>

					</div>

				</div>

			</div>

			</form>

		</div>

		</div>

	</fieldset>

	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>

	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>

	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>

</body>

</html>

