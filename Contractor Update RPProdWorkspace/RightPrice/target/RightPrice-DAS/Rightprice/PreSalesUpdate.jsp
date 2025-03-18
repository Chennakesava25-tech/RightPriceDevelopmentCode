<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Atos RightPrice Portal</title>
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
<script data-require="angular.js@1.2.9" data-semver="1.2.9" src="http://code.angularjs.org/1.2.9/angular.js"></script>
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
<script src="${contextPath}/resources/js/RightPrice/PresalesUpdate.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".customDate").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true
		});
	});
</script>
</head>
<body ng-app="RightPriceApp" ng-controller="PreSalesUpdateController"
ng-init="getPreSalesDataOnId()"
	ng-focus="customDatePicker()">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">Pre Sales Entry</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="frmUpdatePreSales"
					id="frmUpdatePreSales">
					<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Details</label>
										</div>
									</div>
									<div class="panel-body">
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight"> Request Number
												</label>
											<div class="col-sm-3">
												<input name="txtOpportunityNumber" type="text" id="txtOpportunityNumber"
													class="form-control" ng-model="frmUpdatePreSales.opportunityNumber" readOnly>
											</div>
											</div>
											<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Opportunity ID </label>
											<div class="col-sm-3">
											<input name="txtOpportunityNumber" type="text" id="txtOpportunityNumber"
													class="form-control" ng-model="frmUpdatePreSales.opportunityId" readOnly>
											</div>
											<!-- <div class="col-sm-1">
												<a style="cursor: pointer" ng-hide="isHide"
													ng-click="showRateCardDetails(rcd.rateCardId)"><strong><u>View</u></strong></a>
											</div> -->
											<label
												class="control-label col-sm-3 textAlignRight ">Opportunity Description
												</label>
											<div class="col-sm-3">
												<input name="txtopportunityDescription" type="text"
													class="form-control" id="txtopportunityDescription" ng-model="frmUpdatePreSales.opportunityDescription"  readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
												<label
												class="control-label col-sm-2 textAlignRight ">Deal Type </label>
											<div class="col-sm-3">
												<input name="txtDealType" type="text"
													class="form-control" id="txtDealType"  ng-model="frmUpdatePreSales.dealType"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Deal Status</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="txtDealStatus"
													name="txtDealStatus"  ng-model="frmUpdatePreSales.dealStatus"
													 readOnly>
												<!-- <div class="error-messages" ng-if="saved"
													ng-messages="frmUpdatePreSales.dealStatus.$error">
													<em class="error help-block has-error"
														ng-message="required">Please select Rate Card ID</em>
												</div> -->
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">Deal Start
												Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													placeholder="dd/mm/yyyy" id="txtdealStartDate"
													name="txtdealStartDate" ng-model="frmUpdatePreSales.dealStartDate"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Deal End
												Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													placeholder="dd/mm/yyyy" id="txtdealCardEndDate"
													name="txtdealCardEndDate"
													ng-model="frmUpdatePreSales.dealEndDate"
													 readOnly>
											</div>
										</div>
										<!-- <div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">CRM 
												Customer Id</label>
											<div class="col-sm-3">
												<input name="txtCustomerId" type="text"
													class="form-control" id="txtCustomerId"
													ng-model="frmUpdatePreSales.crmCustomerId" readOnly>
											</div>
												<label
												class="control-label col-sm-3 textAlignRight ">Customer Name</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCustomerName"
													name="txtCustomerName"
													ng-model="frmUpdatePreSales.RateCardEndDate" readOnly>
											</div>
										</div> -->
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Customer Name</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCustomer"
													name="txtCustomer" ng-model="frmUpdatePreSales.customer"
													 readOnly>
													 <!--  -->
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Finance Customer</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtFinanceCustomer"
													name="txtFinanceCustomer"   ng-model="frmUpdatePreSales.financeCustomer"
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">IRIS Code</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtIrisCode"
													name="txtIrisCode" ng-model="frmUpdatePreSales.irisCode"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Vertical</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtVertical"
													name="txtVertical" ng-model="frmUpdatePreSales.vertical" 
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">GBU</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtGbu"
													name="txtGbu"  ng-model="frmUpdatePreSales.gbuModel"
													 readOnly>
													 <!--  -->
											</div>
										<label
												class="control-label col-sm-3 textAlignRight required-Field">Presales WBS Number </label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtWbsNumber"
													name="txtWbsNumber"  ng-model="frmUpdatePreSales.preSalesWBSNumber">
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Currency Code</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCurrencyCode"
													name="txtCurrencyCode" 
													value = "USD" ng-model="frmUpdatePreSales.currencyCode"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Total TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtTotalTCV"
													name="txtTotalTCV"  ng-model="frmUpdatePreSales.totalTCV" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight required-Field">B&PS TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSTCV"
													name="txtBPSTCV" ng-model="frmUpdatePreSales.bnpsTCV" ng-change="calculatePercentageOfTCV(frmApprovePreSales.bnpsTCV,frmApprovePreSales.totalTCV)">
											</div>
											
												<label
												class="control-label col-sm-3 textAlignRight required-Field">B&PS BID Budget</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSBidBudget"
													name="txtBPSBidBudget" value = "80,000" ng-model="frmUpdatePreSales.bnpsBidBudget" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Bid spend Start date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtBidStartDate"
														name="txtBidStartDate"
														ng-model="frmUpdatePreSales.startdate"
														ng-class="{true: 'ng-border'}[saved && frmUpdatePreSales.startdate.$invalid]"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Bid spend End date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtBidEndDate"
														name="txtBidEndDate"
														ng-model="frmUpdatePreSales.enddate"
														ng-class="{true: 'ng-border'}[saved && frmUpdatePreSales.Enddate.$invalid]"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">B&PS-BID Budget in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSBudgetEuro"
													name="txtBPSBudgetEuro" ng-model="frmUpdatePreSales.bnpsBudgetEuro" readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">B&PS TCV in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSTCVEuro"
													name="txtBPSTCVEuro" ng-model="frmUpdatePreSales.bnpsBPSTCVEuro" decimals="2"
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">% of B&PS TCV on Total TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPercentOfBPsTCV"
													name="txtPercentOfBPsTCV" ng-model="frmUpdatePreSales.percentOfBpsTCV" readOnly>
											</div>
										<label
												class="control-label col-sm-3 textAlignRight ">Pre sales Cost against WBS</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPreSalesCostWBS"
													name="txtPreSalesCostWBS" ng-model="frmUpdatePreSales.preSalesCostWBS" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">Pre sales Cost against WBS in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPreSalesCostinEuro"
													name="txtPreSalesCostinEuro" ng-model="frmUpdatePreSales.preSalesCostinEuro" 
													 readOnly>
											</div>
										<label
												class="control-label col-sm-3 textAlignRight ">Balance</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtbalance"
													name="txtbalance" ng-model="frmUpdatePreSales.balance" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Balance  in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBalanceinEuro"
													name="txtBalanceinEuro" ng-model="frmUpdatePreSales.balanceInEuro"
													 readOnly> 
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
											<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" rows="3"
	                                			ng-model="frmUpdatePreSales.currentApproverCommentModel" required>
	                                			  </textarea>
	                             		</div>
										</div>
										<div class="row">
											<label
												class="control-label col-sm-2 textAlignRight">Upload
												File </label>
											<div class="col-sm-3">
												<input type="file" class="form-control"
													name="fuUploadFilename" id="fuUploadFilename"
													ng-model="frmRateCardManualUpload.fuUploadFilenameModel"
													check-file-size="frmRateCardManualUpload.fuUploadFilenameModel"
													valid-file-rate-card
													ng-class="{true: 'ng-border'}[(upload) && frmRateCardManualUpload.fuUploadFilename.$invalid]"
													>
												<div class="error-messages" ng-if="(upload)"
													ng-messages="frmRateCardManualUpload.fuUploadFilename.$error">
													<em class="error help-block has-error"
														ng-message="checkfilesize">File size is not valid for
														uploading!</em> <em class="error help-block has-error"
														ng-message="required">Please upload file</em> <em
														class="error help-block has-error" ng-message="extension">File
														format is not valid for uploading!</em>
												</div>
											</div>
											<div class="col-sm-2">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnUpload"
													ng-click="uploadData(frmRateCardManualUpload);" ng-disabled = "uploadBtnDisable">Upload</button>
											</div>
					       					<div class="col-sm-1 divPaddingLeftZero">
												<div class="divDownloadImg">
														<a href="#" id="APPROVAL_ATTACHMENT_ID"
															class="anchorTrancColor"
															ng-click="downloadFile(manualRCData);"> <span model= "manualAttachementModel" id="manualAttachementFile"></span>
														</a>
												 </div>
										</div>
										</div>
									</div>
								</div>
							</div>
							</div>
							</div>
							<div class="row text-center">
								<div class="col-sm-12">
								<button type="button" class="btn btn-primary btnSpace"
										id="btnSave" ng-model = "frmUpdatePreSales.save" ng-disabled="btnDisable"
										ng-click="saveBudgetDetails(frmUpdatePreSales);">Save</button>
									<button type="button" class="btn btn-primary btnSpace"
										id="btnSubmit" ng-disabled="btnDisable"
										ng-click="submitBudgetDetails(frmUpdatePreSales);">Submit</button>
									<button type="button" class="btn btn-primary btnSpace"
										id="btnDetailsCancel" ng-click="clearData()">Cancel</button>

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
