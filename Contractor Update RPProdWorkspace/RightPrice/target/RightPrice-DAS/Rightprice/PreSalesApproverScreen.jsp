<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
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
<script src="${contextPath}/resources/js/RightPrice/PresalesApproverScreen.js"></script>
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

<body ng-app="RightPriceApp" ng-controller="PreSalesApproverController"
ng-init="getPreSalesDataOnId()"
	ng-focus="customDatePicker()">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
			</div>
			<div>
				<form class="form-inline" role="form" name="frmApprovePreSales"
					id="frmApprovePreSales">
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
													class="form-control" ng-model="frmApprovePreSales.opportunityNumber" readOnly>
											</div>
											</div>
											<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Opportunity ID </label>
											<div class="col-sm-3">
											<input name="txtOpportunityNumber" type="text" id="txtOpportunityNumber"
													class="form-control" ng-model="frmApprovePreSales.opportunityId" readOnly>
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
													class="form-control" id="txtopportunityDescription" ng-model="frmApprovePreSales.opportunityDescription"  readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
												<label
												class="control-label col-sm-2 textAlignRight ">Deal Type </label>
											<div class="col-sm-3">
												<input name="txtDealType" type="text"
													class="form-control" id="txtDealType"  ng-model="frmApprovePreSales.dealType"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Deal Status</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="txtDealStatus"
													name="txtDealStatus"  ng-model="frmApprovePreSales.dealStatus"
													 readOnly>
												<!-- <div class="error-messages" ng-if="saved"
													ng-messages="frmApprovePreSales.dealStatus.$error">
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
													name="txtdealStartDate" ng-model="frmApprovePreSales.dealStartDate"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Deal End
												Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													placeholder="dd/mm/yyyy" id="txtdealCardEndDate"
													name="txtdealCardEndDate"
													ng-model="frmApprovePreSales.dealEndDate"
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
													ng-model="frmApprovePreSales.crmCustomerId" readOnly>
											</div>
												<label
												class="control-label col-sm-3 textAlignRight ">Customer Name</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCustomerName"
													name="txtCustomerName"
													ng-model="frmApprovePreSales.RateCardEndDate" readOnly>
											</div>
										</div> -->
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Customer Name</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCustomer"
													name="txtCustomer" ng-model="frmApprovePreSales.customer"
													 readOnly>
													 <!--  -->
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Finance Customer</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtFinanceCustomer"
													name="txtFinanceCustomer"   ng-model="frmApprovePreSales.financeCustomer"
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">IRIS Code</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtIrisCode"
													name="txtIrisCode" ng-model="frmApprovePreSales.irisCode"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Vertical</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtVertical"
													name="txtVertical" ng-model="frmApprovePreSales.vertical" 
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">GBU</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtGbu"
													name="txtGbu"  ng-model="frmApprovePreSales.gbuModel"
													 readOnly>
													 <!--  -->
											</div>
										<label
												class="control-label col-sm-3 textAlignRight required-Field">Presales WBS Number </label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtWbsNumber"
													name="txtWbsNumber"  ng-model="frmApprovePreSales.preSalesWBSNumber">
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Currency Code</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCurrencyCode"
													name="txtCurrencyCode" 
													value = "USD" ng-model="frmApprovePreSales.currencyCode"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Total TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtTotalTCV"
													name="txtTotalTCV"  ng-model="frmApprovePreSales.totalTCV" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight required-Field">B&PS TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSTCV"
													name="txtBPSTCV" ng-model="frmApprovePreSales.bnpsTCV" ng-change="calculatePercentageOfTCV(frmApprovePreSales.bnpsTCV,frmApprovePreSales.totalTCV)">
											</div>
											
												<label
												class="control-label col-sm-3 textAlignRight required-Field">B&PS BID Budget</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSBidBudget"
													name="txtBPSBidBudget" value = "80,000" ng-model="frmApprovePreSales.bnpsBidBudget" readOnly>
											</div>
										</div>
											<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Bid spend Start date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtBidStartDate"
														name="txtBidStartDate"
														ng-model="frmApprovePreSales.startdate"
														ng-class="{true: 'ng-border'}[saved && frmApprovePreSales.startdate.$invalid]"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Bid spend End date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtBidEndDate"
														name="txtBidEndDate"
														ng-model="frmApprovePreSales.enddate"
														ng-class="{true: 'ng-border'}[saved && frmApprovePreSales.Enddate.$invalid]"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">B&PS-BID Budget in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSBudgetEuro"
													name="txtBPSBudgetEuro" ng-model="frmApprovePreSales.bnpsBudgetEuro" readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">B&PS TCV in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSTCVEuro"
													name="txtBPSTCVEuro" ng-model="frmApprovePreSales.bnpsBPSTCVEuro" decimals="2"
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">% of B&PS TCV on Total TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPercentOfBPsTCV"
													name="txtPercentOfBPsTCV" ng-model="frmApprovePreSales.percentOfBpsTCV" readOnly>
											</div>
										<label
												class="control-label col-sm-3 textAlignRight ">Pre sales Cost against WBS</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPreSalesCostWBS"
													name="txtPreSalesCostWBS" ng-model="frmApprovePreSales.preSalesCostWBS" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">Pre sales Cost against WBS in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPreSalesCostinEuro"
													name="txtPreSalesCostinEuro" ng-model="frmApprovePreSales.preSalesCostinEuro" 
													 readOnly>
											</div>
										<label
												class="control-label col-sm-3 textAlignRight ">Balance</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtbalance"
													name="txtbalance" ng-model="frmApprovePreSales.balance" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Balance  in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBalanceinEuro"
													name="txtBalanceinEuro" ng-model="frmApprovePreSales.balanceInEuro"
													 readOnly> 
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
											<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" rows="3"
	                                			ng-model="frmApprovePreSales.currentApproverCommentModel" required>
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
			<div class="row">
					<div class="col-md-12">
					<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Level 1 Approval Details</label>
										</div>
									</div>
							`	<div class="panel-body">
								<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Approver Action</label>
                                        <div class="col-sm-2">
                                          <select id="ddlApproverStatus" class="form-control" ng-model="frmRateCardCreation.firstLevelApproval"  placeholder="Please select" name="ddlApproverStatus" ng-change="getApprovalComments('<%=session.getAttribute("username")%>')" ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]" required>
<%--                                           <select id="ddlApproverStatus" class="form-control" ng-model="frmRateCardCreation.firstLevelApproval"  placeholder="Please select" name="ddlApproverStatus" ng-change="getApprovalComments('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')" ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]" required> --%>
										<option value="" selected disabled>Please select</option>
										<option value="3">Approve</option>
										<option value="4">Reject</option>
										</select>
                                        </div>
                                     </div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">B&PS Approved BID Budget</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="txtBNPSApprovedBudget"
													name="txtBNPSApprovedBudget"
													ng-model="frmRateCard.bnpsApprovedBudget">
											</div>
											<label
												class="control-label col-sm-3 textAlignRight required-Field">First Level Approval Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtFirstLevelApprovalDate"
													name="txtFirstLevelApprovalDate"
													ng-model="frmRateCard.firstLevelApprovalDate">
											</div>
											</div>
										 <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtApproverComment" id="txtApproverComment" class="form-control" rows="3"
	                                			 ng-model="frmRateCardCreation.approverCommentModel" required></textarea>
	                             		</div>
                               		 </div>
									</div>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
					<div class="col-md-12">
					<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Level 2 Approval Details</label>
										</div>
									</div>
							`	<div class="panel-body">
									<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Approver Action</label>
                                        <div class="col-sm-2">
                                          <select id="ddlApproverStatus" class="form-control" ng-model="frmRateCardCreation.secondLevelApproval"  placeholder="Please select" name="ddlApproverStatus" ng-change="getApprovalComments('<%=session.getAttribute("username")%>')" ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]" required>
<%--                                           <select id="ddlApproverStatus" class="form-control" ng-model="frmRateCardCreation.secondLevelApproval"  placeholder="Please select" name="ddlApproverStatus" ng-change="getApprovalComments('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')" ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]" required> --%>
										<option value="" selected disabled>Please select</option>
										<option value="3">Approve</option>
										<option value="4">Reject</option>
										</select>
                                        </div>
                                     </div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">B&PS Approved BID Budget</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="txtBNPSApprovedBudget"
													name="txtBNPSApprovedBudget"
													ng-model="frmRateCard.bnpsApprovedBudget">
											</div>
											<label
												class="control-label col-sm-3 textAlignRight required-Field">Second Level Approval Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtFirstLevelApprovalDate"
													name="txtFirstLevelApprovalDate"
													ng-model="frmRateCard.firstLevelApprovalDate">
											</div>
											</div>
										 <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtApproverComment" id="txtApproverComment" class="form-control" rows="3"
	                                			 ng-model="frmRateCardCreation.approverCommentModel" required></textarea>
	                             		</div>
                               		 </div>
									</div>
						</div>
					</div>
				</div>
				</div>
				</form>
				
				<div class="row text-center">
								<div class="col-sm-12">
									<button type="button" class="btn btn-primary btnSpace"
										id="btnDetailsSave" ng-disabled="btnDisable"
										ng-click="saveRateCard(frmRateCard);">Submit</button>
									<button type="button" class="btn btn-primary btnSpace"
										id="btnDetailsCancel" ng-click="cancelRateCard(frmRateCard)">Cancel</button>

								</div>
							</div>
			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
