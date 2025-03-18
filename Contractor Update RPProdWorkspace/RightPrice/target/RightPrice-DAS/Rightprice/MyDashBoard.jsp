<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
<link href="${contextPath}/resources/css/SAPStyleSheet.css"	rel="stylesheet" />
<link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"></script>
<link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/bootstrap-dialog.css"	rel="stylesheet" />
<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
<link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css"	rel="stylesheet" />
<link href="${contextPath}/resources/css/sticky-footer-navbar.css"	rel="stylesheet" />
<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
<script src="${contextPath}/resources/js/angular.js"></script>
<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
<script src="${contextPath}/resources/js/angular-messages.js"></script>
<script src="${contextPath}/resources/js/ngStorage.js"></script>
<script src="${contextPath}/resources/js/loader.js"></script>
<script
	src="${contextPath}/resources/js/crypto-js.min.js"></script>
<script
	src="${contextPath}/resources/js/aes.js"></script>
	<script
	src="${contextPath}/resources/js/core.min.js"></script>
	<script
	src="${contextPath}/resources/js/cipher-core.min.js"></script>
	<script
	src="${contextPath}/resources/js/mode-cfb.min.js"></script>
	<script
	src="${contextPath}/resources/js/pad-pkcs7.min.js"></script>
<script
	src="${contextPath}/resources/js/pbkdf2.js"></script>
<script src="${contextPath}/resources/js/RightPrice/myDashboard.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>

<body ng-app="RightPriceApp" ng-controller="MyDashBoardController"
	ng-init="currentUser('<%=session.getAttribute("user")%>')">
<%-- 	ng-init="currentUser('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')"> --%>
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
		
	<fieldset ng-disabled="loading || showLoader">
	<div class="container">
		<div class="divEmpty"></div>
		<div class="row marginBottom5px">
			<div class="col-sm-12">
				<h3 class="text-left">My Dash Board</h3>
			</div>
		</div>
	
		
		<div>
			<form class="form-inline" role="form" name="frmDashboard"
				id="frmDashboard">
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info ">
								<div class="panel-heading panelHeadingStyle">
									<div class="row ">
										<label class="control-label col-sm-10 ">Dash Board</label>
									</div>
								</div>
								<div class="panel-body">
									<div class="row marginBottom5px">
										 <label
											class="control-label col-sm-2 textAlignRight ">Name of the Customer</label>
										<div class="col-sm-3">
											<Select id="ddlName" class="form-control"
												placeholder="Please select" name="ddlName"
												ng-change="getCustomerBasedRecords(frmDashboard.customerNameModel.customerId)"
												ng-model="frmDashboard.customerNameModel"
												ng-options="cvi.customer.customerName for cvi in customer| orderBy:'customer.customerName'"
												required>
												<option value="" selected>Please select</option>
											</Select>
										</div>
										 <label class="control-label col-sm-2 textAlignRight ">Rate Card ID</label>
										<div class="col-sm-3">
                                           <input  ng-model="frmDashboard.searchRateCard" placeholder="Search" type="text" class="form-control ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength ng-valid-required"   >
    	                                </div>
    	                                <div class="col-sm-2">
											<button class="btn btn-link" type="button" ng-click="getDataOnSearch(frmDashboard.searchRateCard)">
											<img src="${contextPath}/resources/Images/searchicon.png" alt="Snow"> Search</button><!--  ng-click="exportToExcel('#tableToExport')" -->
										</div>
									</div>
									<div class="row marginBottom5px">
										<div></div>
										<div class="col-sm-10 col-sm-offset-1">
											<div class="table-responsive  ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblDashBoard">
													<thead>
														<tr>
															<th class="width10per">Status</th>
															<th class="width10per">Draft</th>
															<th class="width10per">DH</th>
															<th class="width10per">RiskManagers</th>
															<th class="width10per">GFT</th>
															<th class="width10per">CDO</th>
															<th class="width10per">BUH</th>
															<th class="width10per">CEO</th>
															<th class="width10per">Level 1</th>
															<th class="width10per">Level 2</th>
															<th class="width10per">Approved</th>
															<th class="width10per">Won</th>
															<th class="width10per">Lost</th>
															<th class="width10per">Recycled</th>
															<th class="width10per">Deactivated</th>
															<th class="width10per">Expired</th>
														</tr>
													</thead>
													<tbody id="tBodyDashBoardr">
														<tr>
															<td>Rate Cards</td>
															<td><button type="button" id="btnRateCardsDraft"
																	ng-disabled="isLevelApproverDisabled"
																	ng-click="getRateCardData(null,1)">{{draftCount}}</button></td>
															<td><button type="button" id="btnRateCardsDuh"
																	ng-click="getRateCardData(4,2)">{{duhCount}}</button></td>
															<td><button type="button" id="btnRateCardsRiskManagers"
																	disabled>NA</button></td>
															<td><button type="button" id="btnRateCardsGft"
																	ng-click="getRateCardData(6,2)">{{gftCount}}</button></td>
															<td><button type="button" id="btnRateCardsCdo"
																	ng-click="getRateCardData(7,2)">{{cdoCount}}</button></td>
															<td><button type="button" id="btnRateCardsBuh"
																	ng-click="getRateCardData(8,2)">{{buhCount}}</button></td>
															<td><button type="button" id="btnRateCardsCeo"
																	ng-click="getRateCardData(10,2)">{{ceoCount}}</button></td>
															<td><button type="button" id="btnRateCardsLevel1"
																	ng-click="getRateCardData(11,2)">{{countLevel1}}</button></td>
															<td><button type="button" id="btnRateCardsLevel2"
																	ng-click="getRateCardData(12,2)">{{countLevel2}}</button></td>
															<td><button type="button" id="btnRateCardsCompleted"
																	ng-click="getRateCardData(null,3)">{{approvedCount}}</button></td>
															<td><button type="button" id="btnRateCardsWon"
																	disabled>NA</button></td>
															<td><button type="button" id="btnRateCardsLost"
																	disabled>NA</button></td>
															<td><button type="button" id="btnRateCardsExpired"
																	ng-click="getRateCardData(null,4)">{{recycledCount}}</button></td>
															<td><button type="button" id="btnRateCardsExpired"
																	ng-click="getRateCardData(null,5)" disabled>{{deactivatedCount}}</button></td>
															<td><button type="button" id="btnRateCardsExpired"
																	ng-click="getRateCardData(null,6)">{{expiredCount}}</button></td>
														</tr>
														<tr>
															<td>Deals</td>
															<td><button type="button" id="btnDealsDraft"
																	ng-disabled="isLevelApproverDisabled || btnDisable"
																	ng-click="getDashboardDealData(null,1)">{{dealDraftCount}}</button></td>
															<td><button type="button" id="btnDealsDuh"
																	ng-click="getDashboardDealData(4,2)">{{dealDuhCount}}</button></td>
															<td><button type="button" id="btnDealsRiskManagers"
																	ng-click="getDashboardDealData(5,2)">{{dealRiskManagersCount}}</button></td>
															<td><button type="button" id="btnDealsGFT"
																	ng-click="getDashboardDealData(6,2)">{{dealGftCount}}</button></td>
															<td><button type="button" id="btnDealsCDO"
																	ng-click="getDashboardDealData(7,2)">{{dealCdoCount}}</button></td>
															<td><button type="button" id="btnDealsBuh"
																	ng-click="getDashboardDealData(8,2)">{{dealBuhCount}}</button></td>
															<td><button type="button" id="btnDealsCEO"
																	ng-click="getDashboardDealData(10,2)">{{dealCeoCount}}</button></td>
															<td><button type="button" id="btnRateCardsLevel1"
																	ng-click="getDashboardDealData(11,2)">{{dealLevel1Count}}</button></td>
															<td><button type="button" id="btnRateCardsLevel2"
																	ng-click="getDashboardDealData(12,2)">{{dealLevel2Count}}</button></td>
															<td><button type="button" id="btnDealCompleted"
																	ng-click="getDashboardDealData(null,3)">{{dealApprovedCount}}</button></td>
															<td><button type="button" id="btnDealWon" 
																	ng-click="getDealStatusDetails(1)">{{dealWonCount}}</button></td>
															<td><button type="button" id="btnDealLost"
																	ng-click="getDealStatusDetails(2)">{{dealLostCount}}</button></td>
															<td><button type="button" id="btnDealRecycled"
																	ng-click="getDashboardDealData(null,4)">{{dealRecycledCount}}</button></td>
															<td><button type="button" id="btnDealDeactivate" 
																	ng-click="getDashboardDealData(null,5)">{{dealDeactivatedCount}}</button></td>
															<td><button type="button" id="btnDealExpired"
																	ng-click="getDashboardDealData(null,6)">{{dealExpiredCount}}</button></td>
<!-- 															<td><button type="button" id="btnDealDeactivate"
																	ng-click="getDealData(null,5)">{{dealDeactivatedCount}}</button></td>
															<td><button type="button" id="btnDealExpired"
																	ng-click="getDealData(null,6)">{{dealExpiredCount}}</button></td> -->
															<!-- <td><button type="button" id="btnDealRejected"
																	ng-disabled="isLevelApproverDisabled"
																	ng-click="getDealData(null,4)">{{dealRejectedCount}}</button></td> -->
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyTwice"></div>
									<div class="divEmptyTwice"></div>
									<div class="row marginBottom5px">										
										<div  class="col-sm-5">
											<label class="control-label col-sm-12">
												<h5><b>Rate Card Details- {{rateCardStatus}}</b></h5>
											</label>
										</div>
									</div>				
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive">
											
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails" border="1">
													<thead>
														<tr>
															<th class="textAlignCenter width10per">Rate Card Id</th>
															<th class="textAlignCenter width13per">Customer</th>
															<th class="textAlignCenter">RC Name</th>
															<th class="textAlignCenter">Start Date <br>(dd/mm/yyyy) </th>
															<!-- <th class="textAlignCenter">End Date <br>(dd/mm/yyyy) </th> -->
															<th class="textAlignCenter">Expected End Date <br>(dd/mm/yyyy)</th>
															<th class="textAlignCenter">Country</th>
															<th class="textAlignCenter">City</th>
															<th class="textAlignCenter">Expected TCV</th>
															<th class="textAlignCenter">Industry</th>
															<th class="textAlignCenter">Last Action Date</th>
															<th class="textAlignCenter">Delivery SPOC</th>
															<th class="textAlignCenter">Category</th>
															<th class="textAlignCenter width5per" ng-hide="isDeleteShow">Delete</th>
															
														</tr>
													</thead>
													<tbody id="tBodyRateCardDetails">
												
														<tr ng-repeat="row in rateCardData|filter: frmDashboard.searchRateCard | orderBy: row.updatedOn">
															<td class="rcIdTdMiddelCenter"
															 ng-click="showRateCardDetails(row.rcId, row.currentApproverId, row.currentApprovalStatus, row.currentApproverRoleId)"><a>{{row.rcId}}</a></td>
															<td class="rcTdText">{{row.customer.customerName}}</td>
															<td class="rcTdText">{{row.rcName}}</td>
															<td class="rcTdTextRight">{{row.rcStartDate | date:"dd/MM/yyyy"}}</td>
															<td class="rcTdTextRight">{{row.expectedRCEndDate | date : "dd/MM/yyyy"}}</td>
															<td	class="rcTdTextMiddel">{{row.baseCountryName}}</td>
															<td class="rcTdTextMiddel">{{row.baseCityName}}</td>
															<td class="rcTdNumMiddel">{{row.tvc}}</td>
															<td ng-if="row.isItKpo == 1 && row.IndustryName !=  null" class="rcTdTextMiddel">{{row.IndustryName}}_IT</td>
															<td ng-if="row.isItKpo == 0 && row.IndustryName != null" class="rcTdTextMiddel">{{row.IndustryName}}_KPO</td>
															<td ng-if="row.isItKpo == 2 && row.IndustryName != null" class="rcTdTextMiddel">{{row.IndustryName}}_JV KPO</td>
															
															<td ng-if="row.isItKpo == 1 && row.IndustryName == null" class="rcTdTextMiddel">IT</td>
															<td ng-if="row.isItKpo == 0 && row.IndustryName == null" class="rcTdTextMiddel">KPO</td>
															<td ng-if="row.isItKpo == 2 && row.IndustryName == null" class="rcTdTextMiddel">JV KPO</td>
															
															<td ng-if="row.currentApprovalStatus == 1 || row.currentApprovalStatus == 4" class="rcTdTextMiddelRight">{{row.createdOn | date:"dd/MM/yyyy"}}</td>
															<td ng-if="row.currentApprovalStatus != 1 && row.currentApprovalStatus != 4" class="rcTdTextMiddelRight">{{row.updatedOn | date:"dd/MM/yyyy"}}</td>
															<td ng-if="row.spocName != null " class="rcTdTextMiddel">{{row.spocName}}</td>
															<td ng-if="row.spocName == null " class="rcTdTextMiddel">-</td>
															<td ng-if="row.isManualRc == 'H' " class="rcTdTextMiddel">Hybrid</td>
															<td ng-if="row.isManualRc == 'A'" class="rcTdTextMiddel">Automatic</td>
															<td ng-if="row.isManualRc == 'M' " class="rcTdTextMiddel">Manual</td>
															<td ng-if="row.isManualRc == '0' || row.isManualRc =='1' || row.isManualRc =='Null'" class="rcTdTextMiddel">NA</td>
															<td ng-hide="isDeleteShow">
															<button class="btn btn-link" type="button" ng-click="deleteRateCard(row.rcId)">
																<img src="${contextPath}/resources/Images/deleteIcon.png" alt="Snow"></button>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyTwice"></div>
									<div class="divEmptyTwice"></div>
									<div class="row marginBottom5px">
										<div  class="col-sm-5">
										<label class="control-label col-sm-12"><h5>
											<b>Deal Details - {{dealStatus}}</b></h5></label>
										</div>
										<label class="control-label col-sm-2 textAlignRight ">Deal ID</label>
										<div class="col-sm-3">
                                           <input  ng-model="frmDashboard.searchDeal" placeholder="Search" type="text" class="form-control ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength ng-valid-required"   >
                                       	  	<!-- ngIf: saved -->
                                        </div>
                                        <div class="col-sm-2">
											<button class="btn btn-link" type="button" ng-click="getDealDataOnSearch(frmDashboard.searchDeal)">
											<img src="${contextPath}/resources/Images/searchicon.png" alt="Snow"> Search</button><!--  ng-click="exportToExcel('#tableToExport')" -->
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive  ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblDealDetails">
													<thead>
														<tr>
															<th class="textAlignCenter width10per">Deal Id</th>
															<!-- <th> Deal Version ID</th> -->
															<th class="textAlignCenter">Version Name</th>
															<th class="textAlignCenter">Customer</th> 
															<th class="textAlignCenter">Deal Description</th>
															<th class="textAlignCenter">Start Date <br>(dd/mm/yyyy) </th>
															<th class="textAlignCenter">End Date <br>(dd/mm/yyyy) </th>
															<th class="textAlignCenter width4per">Tower Count</th>
															<th class="textAlignCenter width4per">Billing Currency</th>
															<th class="textAlignCenter">Deal TCV</th>
															<th class="textAlignCenter">Industry</th>
															<th class="textAlignCenter">Category</th>
															<th class="textAlignCenter">Deal Type</th>
															<!-- <th >Project Type</th> -->
															<th class="textAlignCenter">Delivery SPOC</th>
															<th class="textAlignCenter width4per">Last Action Date</th>
															<th class="textAlignCenter width10per"ng-hide="isDealDeleteShow">Delete</th>
														</tr>
													</thead>
													<tbody id="tBodyDealDetails">
														<tr ng-repeat="row in dealData | filter:frmDashboard.searchDeal | orderBy: row.updatedOn">

															<td ng-click="showDealDetails(row.crmDealId, row.rpDealVersionId, row.currentApproverId, row.currentApprovalStatus, row.dealTypeId, row.isManualDeal)" 
															class="rcIdTdMiddelCenter "><a>{{row.crmDealId}}</a>
															
															</td>
															<td class="rcTdText">{{row.dealVersion}}</td>
															<td class="rcTdText">{{row.customer.customerName}}</td>
															<td class="rcTdText">{{row.dealcrmstagesdata2.dealDescription}}</td> 
															<td class="rcTdTextRight">{{row.dealStartDate}}</td>
															<td class="rcTdTextRight">{{row.dealEndDate}}</td>
															<td class="rcTdTextRight width4per">{{row.towerCount}}</td>
															<td class="rcTdText width4per">{{row.appCode.description}}</td>
															<td class="rcTdNumMiddel">{{row.dealTcv}}</td>
															<td ng-if="row.projectIndustry == 1 && row.IndustryName == null" class="rcTdText">IT</td>
															<td ng-if="row.projectIndustry == 0 && row.IndustryName == null" class="rcTdText">KPO</td>
															<td ng-if="row.projectIndustry == 2 && row.IndustryName == null" class="rcTdText">JV KPO</td>
															
															<td ng-if="row.projectIndustry == 1 && row.IndustryName != null" class="rcTdText">{{row.IndustryName}}_IT</td>
															<td ng-if="row.projectIndustry == 0 && row.IndustryName != null" class="rcTdText">{{row.IndustryName}}_KPO</td>
															<td ng-if="row.projectIndustry == 2 && row.IndustryName != null" class="rcTdText">{{row.IndustryName}}_JV KPO</td>
															
															<td ng-if="row.isNewDeal == 1" class="rcTdText">New</td>
															<td ng-if="row.isNewDeal == 0" class="rcTdText">Renewal</td>
															<td ng-if="row.isNewDeal == 2" class="rcTdText">RFP/RFI</td>
															<td ng-if="row.fpProjectTypeId==1 && row.isNewDeal == 1 && row.dealTypeId==1" class="rcTdText">FPD</td>
															<td ng-if="row.fpProjectTypeId==2 && row.isNewDeal == 1 && row.dealTypeId==1" class="rcTdText">FPD</td>
															<td ng-if="row.fpProjectTypeId==3 && row.isNewDeal == 1 && row.dealTypeId==1" class="rcTdText">FPM</td>
															<td ng-if="row.fpProjectTypeId==4 && row.isNewDeal == 1 && row.dealTypeId==1" class="rcTdText">FPM</td>
															<td ng-if="row.fpProjectTypeId==1 && row.isNewDeal == 0 && row.dealTypeId==1" class="rcTdText">FPD</td>
															<td ng-if="row.fpProjectTypeId==2 && row.isNewDeal == 0 && row.dealTypeId==1" class="rcTdText">FPD</td>
															<td ng-if="row.fpProjectTypeId==3 && row.isNewDeal == 0 && row.dealTypeId==1" class="rcTdText">FPM</td>
															<td ng-if="row.fpProjectTypeId==4 && row.isNewDeal == 0 && row.dealTypeId==1" class="rcTdText">FPM</td>
															<td ng-if="row.fpProjectTypeId==1 && row.isNewDeal == 2 && row.dealTypeId==1" class="rcTdText">RFP/RFI</td>
															<td ng-if="row.fpProjectTypeId==2 && row.isNewDeal == 2 && row.dealTypeId==1" class="rcTdText">RFP/RFI</td>
															<td ng-if="row.fpProjectTypeId==3 && row.isNewDeal == 2 && row.dealTypeId==1" class="rcTdText">RFP/RFI</td>
															<td ng-if="row.fpProjectTypeId==4 && row.isNewDeal == 2 && row.dealTypeId==1" class="rcTdText">RFP/RFI</td>
															<td ng-if="row.dealTypeId==2" class="rcTdText">T&M</td>
															<!-- <td ng-if="row.dealTypeId==1" class="rcTdText">FP</td>
															<td ng-if="row.dealTypeId==2" class="rcTdText">T&M</td> -->
															<!-- <td ng-if="row.fpProjectTypeId==0" class="rcTdText">NA</td>
															<td ng-if="row.fpProjectTypeId==1" class="rcTdText">Devel- FP</td>
															<td ng-if="row.fpProjectTypeId==2" class="rcTdText">Devel - Manage Cap</td>
															<td ng-if="row.fpProjectTypeId==3" class="rcTdText">Maint - FP</td>
															<td ng-if="row.fpProjectTypeId==4" class="rcTdText">Maint - Manage Cap</td> -->
															<td class="rcTdText">{{row.dealcrmstagesdata2.salesSpoc}}</td>
															<td ng-if="row.statusIndicator == 'Draft' || row.statusIndicator == 'Recycled'" class="rcTdTextMiddelRight width4per">{{row.createdDate | date:"dd/MM/yyyy"}}</td>
															<td ng-if="row.statusIndicator != 'Draft' && row.statusIndicator != 'Recycled'" class="rcTdTextMiddelRight width4per">{{row.updatedDate | date:"dd/MM/yyyy"}}</td>
															<td ng-hide="isDealDeleteShow">
															<button class="btn btn-link" type="button" ng-click="deleteDealVersion(row.rpDealVersionId)">
																<img src="${contextPath}/resources/Images/deleteIcon.png" alt="Snow"></button>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<div class="table-responsive">
												<button type="button" class="btn btn-primary btnSpace" ng-click="download(frmDashboard)">
												<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
											</div>
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
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
