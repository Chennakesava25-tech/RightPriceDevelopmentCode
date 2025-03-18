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
<script src="${contextPath}/resources/js/RightPrice/rpview.js"></script>

<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="RPviewController"
	ng-init="currentUser('<%=session.getAttribute("user")%>')">
<%-- 	ng-init="currentUser('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')"> --%>
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
	<div class="container">
		<div class="divEmpty"></div>
		<div class="row marginBottom5px">
			<div class="col-sm-12">
				<h3 class="text-left">View</h3>
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
										<label class="control-label col-sm-10 ">View Details</label>
									</div>
								</div>
								<div class="panel-body">
								
								<div class="row marginBottom5px">										
										<div  class="col-sm-5">
											<label class="control-label col-sm-12">
												<h5><b>Rate Card Details</b></h5>
											</label>
										</div>
										</div>
										<div class="row marginBottom3px">
										 <label
											class="control-label col-sm-2 textAlignRight ">Name of the Customer</label>
										<div class="col-sm-4">
											<Select id="ddlName" class="form-control"
												placeholder="Please select" name="ddlName"
												ng-change="getCustomerBasedRecords(frmDashboard.customerNameModel.customerId)"
												ng-model="frmDashboard.customerNameModel"
												ng-options="cvi.customer.customerName for cvi in customer| orderBy:'customer.customerName'"
												required>
												<option value=""  selected>Please select</option>
											</Select>
										</div>
										 <label class="control-label col-sm-2 textAlignRight ">Rate Card ID</label>
										<div class="col-sm-2">
									
                                           <input  ng-model="frmDashboard.searchRateCard" placeholder="Search" type="text" class="form-control ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength ng-valid-required"   >
    	                                </div>
    	                                <div class="col-sm-1">
											<button class="btn btn-link" type="button" ng-click="getDataOnSearchview(frmDashboard.searchRateCard)">
											<img src="${contextPath}/resources/Images/searchicon.png" alt="Snow"> Search</button><!--  ng-click="exportToExcel('#tableToExport')" -->
										</div>
									</div>
								 <div class="divEmptyTwice"></div>  
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
															<th class="textAlignCenter"> End Date <br>(dd/mm/yyyy)</th>
															<th class="textAlignCenter">Billing Currency</th>
															<th class="textAlignCenter">Estimated TCV</th>
															<th class="textAlignCenter">Country</th>
															<th class="textAlignCenter">City</th>
															<th class="textAlignCenter">Industry</th>
															
															</tr>
															<!-- <th class="textAlignCenter">Industry</th>

															<th class="textAlignCenter">Delivery SPOC</th> 
															<th class="textAlignCenter width5per" ng-hide="isDeleteShow">Delete</th>-->
															
														
													</thead>
													<tbody id="tBodyRateCardDetails">
												
														<tr ng-repeat="row in viewrcData ">
															<td class="rcIdTdMiddelCenter" 
															ng-click="showRateCardDetails_rc(row.rcId);">
															<a>{{row.rcId}}</a></td>
															<td class="rcTdText">{{row.customer_Name}}</td>
															<td class="rcTdText">{{row.rcName}}</td>
															<td class="rcTdTextRight">{{row.rcStartDate | date:"dd/MM/yyyy"}}</td>
															<td class="rcTdTextRight">{{row.rcEndDate | date : "dd/MM/yyyy"}}</td>
															 <td class="rcTdNumMiddel">{{row.currency_Code}}</td> 
															<!-- <td></td> -->
															<td class="rcTdNumMiddel">{{row.tvc | number:2}}</td>
															<td	class="rcTdTextMiddel">{{row.country_Name}}</td>
															<td class="rcTdTextMiddel">{{row.city_Name}}</td>
															<td ng-if="row.isItKpo == 1 && row.industryName == null" class="rcTdText">IT</td>
															<td ng-if="row.isItKpo == 0 && row.industryName == null" class="rcTdText">KPO</td>
															<td ng-if="row.isItKpo == 2 && row.industryName == null" class="rcTdText">JV KPO</td>
															
															<td ng-if="row.isItKpo == 1 && row.industryName != null" class="rcTdText">{{row.industryName}}_IT</td>
															<td ng-if="row.isItKpo == 0 && row.industryName != null" class="rcTdText">{{row.industryName}}_KPO</td>
															<td ng-if="row.isItKpo == 2 && row.industryName != null" class="rcTdText">{{row.industryName}}_JV KPO</td>
															
															<!-- <td ng-if="row.isItKpo == 1 && row.IndustryName !=  null" class="rcTdTextMiddel">{{row.IndustryName}}_IT</td>
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
															<td ng-if="row.isManualRc == '0' || row.isManualRc =='1' || row.isManualRc =='Null'" class="rcTdTextMiddel">NA</td> -->
															<%-- <td ng-hide="isDeleteShow">
															<button class="btn btn-link" type="button" ng-click="deleteRateCard(row.rcId)">
																<img src="${contextPath}/resources/Images/deleteIcon.png" alt="Snow"></button>
															</td> --%>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<%-- <div class="divEmptyTwice"></div>
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
									</div> --%>
									<div class="row marginBottom10px">
									<div  class="col-sm-3">
											<label class="control-label col-sm-12">
												<h5><b>Deal Details</b></h5>
											</label>
										</div>
										</div>
										<div class="row marginBottom10px">
										 <label
											class="control-label col-sm-2 textAlignRight ">Name of the Customer</label>
										<div class="col-sm-3">
											<Select id="ddlName1" class="form-control"
												placeholder="Please select1" name="ddlName1"
												ng-change="getCustomerBasedRecordsfordeals(frmDashboard.customerNameModel2.customerId)"
												ng-model="frmDashboard.customerNameModel2"
												ng-options="cvi.customer.customerName for cvi in customer| orderBy:'customer.customerName'"
												required>
												<option value="" selected>Please select</option>
											</Select>
										</div>
										 <label class="control-label col-sm-2 textAlignRight ">Deal ID</label>
										<div class="col-sm-3">
                                           <input  ng-model="frmDashboard.searchDeal" placeholder="Search" type="text" class="form-control ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength ng-valid-required"   >
                                       	  	<!-- ngIf: saved -->
                                        </div>
                                       
                                        <div class="col-sm-1">
											<button class="btn btn-link" type="button" ng-click="getDealDataOnSearchview(frmDashboard.searchDeal)">
											<img src="${contextPath}/resources/Images/searchicon.png" alt="Snow"> Search</button><!--  ng-click="exportToExcel('#tableToExport')" -->
										</div>
										
									</div>
									
									<div class="divEmptyTwice"></div> 
									<div class="divEmptyTwice"></div> 
									
									<div class="row">
										<div class="col-sm-12">
											<div class="table-responsive  ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblDealDetails">
													<thead>
														<tr>
															<th class="textAlignCenter width10per">Deal Id</th>
															
															
															<th class="textAlignCenter">Customer</th> 
															<th class="textAlignCenter">Deal Description</th>
															<th class="textAlignCenter">Start Date <br>(dd/mm/yyyy) </th>
															<th class="textAlignCenter">End Date <br>(dd/mm/yyyy) </th>
															<th class="textAlignCenter width4per">Billing Currency</th>
															<th class="textAlignCenter">Deal TCV</th>
															<th class="textAlignCenter">Country</th>
															<th class="textAlignCenter">City</th>
															<th class="textAlignCenter">Industry</th>
															<!-- <th class="textAlignCenter width10per"ng-hide="isDealDeleteShow">Delete</th> -->
														</tr>
													</thead>
													<tbody id="tBodyDealDetails">
														<tr ng-repeat="row in viewdealData track by $index">

															<td 
															class="rcIdTdMiddelCenter "
															ng-click="showdealDetails_d(row.dealId);"><a>{{row.dealId}}</a>
															
															</td>
															
															<td class="rcTdText">{{row.customer_Name}}</td>
															<td class="rcTdText">{{row.dealDescription}}</td> 
															<td class="rcTdTextRight">{{row.dealStartDate}}</td>
															<td class="rcTdTextRight">{{row.dealEndDate}}</td>
															
															<td class="rcTdText width4per">{{row.currency_Code}}</td>
															<td class="rcTdNumMiddel">{{row.dealTCV | number:2}}</td>
															<td class="rcTdNumMiddel">{{row.country_Name}}</td>
															<td class="rcTdNumMiddel">{{row.city_Name}}</td>
															<td ng-if="row.industry == 1 && row.industryName == null" class="rcTdText">IT</td>
															<td ng-if="row.industry == 0 && row.industryName == null" class="rcTdText">KPO</td>
															<td ng-if="row.industry == 2 && row.industryName == null" class="rcTdText">JV KPO</td>
															
															<td ng-if="row.industry == 1 && row.industryName != null" class="rcTdText">{{row.industryName}}_IT</td>
															<td ng-if="row.industry == 0 && row.industryName != null" class="rcTdText">{{row.industryName}}_KPO</td>
															<td ng-if="row.industry == 2 && row.industryName != null" class="rcTdText">{{row.industryName}}_JV KPO</td>
															
															<%-- <td ng-hide="isDealDeleteShow">
															<button class="btn btn-link" type="button" ng-click="deleteDealVersion(row.rpDealVersionId)">
																<img src="${contextPath}/resources/Images/deleteIcon.png" alt="Snow"></button>
															</td> --%>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<%-- <div class="row text-center">
										<div class="col-sm-12">
											<div class="table-responsive">
												<button type="button" class="btn btn-primary btnSpace" ng-click="download(frmDashboard)">
												<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
											</div>
										</div>
									</div> --%>
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
