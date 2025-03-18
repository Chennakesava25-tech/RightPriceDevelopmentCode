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
<script src="${contextPath}/resources/js/RightPrice/PresalesAdd.js"></script>
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
<body ng-app="RightPriceApp" ng-controller="PreSalesAddController""
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
				<form class="form-inline" role="form" name="frmPreSales"
					id="frmPreSales" novalidate>
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
													class="form-control" ng-model="frmPreSales.opportunityNumber" readOnly>
											</div>
											</div>
											<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Opportunity ID </label>
											<div class="col-sm-3">
												<select id="ddlOpportunityId" class="form-control"
													placeholder="Please select" name="ddlOpportunityId"
													ng-model="frmPreSales.opportunityId"
													ng-options="cd.crmDealId as cd.crmDealId for cd in opportunityArray| orderBy:'crmDealId'"
													ng-change="putOpportunityDealData(frmPreSales.opportunityId)"
													required
													ng-class="{true: 'ng-border'}[preSubmitError && frmPreSales.ddlOpportunityId.$invalid]">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="preSubmitError"
												ng-messages="frmPreSales.ddlOpportunityId.$error">
												<em class="error help-block has-error" ng-message="required">This
													Field is required!</em>
											</div>
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
													class="form-control" id="txtopportunityDescription" ng-model="frmPreSales.opportunityDescription"  readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
												<label
												class="control-label col-sm-2 textAlignRight ">Deal Type </label>
											<div class="col-sm-3">
												<input name="txtDealType" type="text"
													class="form-control" id="txtDealType"  ng-model="frmPreSales.dealType"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Deal Status</label>
											<div class="col-sm-3">
												<input type="text" class="form-control" id="txtDealStatus"
													name="txtDealStatus"  ng-model="frmPreSales.dealStatus"
													 readOnly>
												<!-- <div class="error-messages" ng-if="saved"
													ng-messages="frmPreSales.dealStatus.$error">
													<em class="error help-block has-error"
														ng-message="required">Please select Rate Card ID</em>
												</div> -->
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight "> Deal Start
												Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													placeholder="dd/mm/yyyy" id="txtdealStartDate"
													name="txtdealStartDate" ng-model="frmPreSales.dealStartDate"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight "> Deal End
												Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													placeholder="dd/mm/yyyy" id="txtdealCardEndDate"
													name="txtdealCardEndDate"
													ng-model="frmPreSales.dealEndDate"
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
													ng-model="frmPreSales.crmCustomerId" readOnly>
											</div>
												<label
												class="control-label col-sm-3 textAlignRight ">Customer Name</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCustomerName"
													name="txtCustomerName"
													ng-model="frmPreSales.RateCardEndDate" readOnly>
											</div>
										</div> -->
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Customer Name</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCustomer"
													name="txtCustomer" ng-model="frmPreSales.customer"
													 readOnly>
													 <!--  -->
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Finance Customer</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtFinanceCustomer"
													name="txtFinanceCustomer"   ng-model="frmPreSales.financeCustomer"
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">IRIS Code</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtIrisCode"
													name="txtIrisCode" ng-model="frmPreSales.irisCode"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Vertical</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtVertical"
													name="txtVertical" ng-model="frmPreSales.vertical" 
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">GBU</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtGbu"
													name="txtGbu"  ng-model="frmPreSales.gbuModel"
													 readOnly>
													 <!--  -->
											</div>
										<label
												class="control-label col-sm-3 textAlignRight required-Field">Presales WBS Number </label>
											<!-- <div class="col-sm-3">
												<input type="text" class="form-control"  id="txtWbsNumber"
													name="txtWbsNumber"  ng-model="frmPreSales.preSalesWBSNumber">
											</div> -->
											<div class="col-sm-3">
											<select id="ddlWbsNumber"
														class="form-control"  name="ddlWbsNumber"
														ng-model="frmPreSales.preSalesWBSNumber" 
														ng-options = "rcType.id as rcType.name for rcType in RateCardType">
														<option value="" selected>Please select</option>
													</select>
													</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Currency Code</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtCurrencyCode"
													name="txtCurrencyCode" 
													value = "USD" ng-model="frmPreSales.currencyCode"
													 readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Total TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtTotalTCV"
													name="txtTotalTCV"  ng-model="frmPreSales.totalTCV" readOnly>
											</div>
										</div>
										<!-- <div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Approved TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtApprovedTCV"
													name="txtApprovedTCV"  ng-model="frmPreSales.totalTCV" 
													 readOnly>
											</div>
										<label
												class="control-label col-sm-3 textAlignRight required-Field">Total BID Budget</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtTotalBIDBudget"
													name="txtTotalBIDBudget" ng-model="frmPreSales.totalBidBudget" ng-change ="calculateEuroTCV(frmPreSales.totalBidBudget)">
											</div>
										</div> -->
										<div class="row marginBottom5px">
											<!-- <label
												class="control-label col-sm-2 textAlignRight required-Field">Total Approved Budget</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtTotalApprovedBudget"
													name="txtTotalApprovedBudget"  ng-model="frmPreSales.totalApprovedBudget">
											</div> -->
										<label
												class="control-label col-sm-2 textAlignRight required-Field">B&PS TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSTCV"
													name="txtBPSTCV" ng-model="frmPreSales.bnpsTCV" ng-change="calculatePercentageOfTCV(frmPreSales.bnpsTCV,frmPreSales.totalTCV)"
													ng-class="{true: 'ng-border'}[preSubmitError && frmPreSales.txtBPSTCV.$invalid]">
													<div class="error-messages" ng-if="preSubmitError"
												ng-messages="frmPreSales.txtBPSTCV.$error">
												<em class="error help-block has-error" ng-message="required">This
													Field is required!</em>
											</div>
											</div>
											
												<label
												class="control-label col-sm-3 textAlignRight required-Field">B&PS BID Budget</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSBidBudget"
													name="txtBPSBidBudget" value = "80,000" ng-model="frmPreSales.bnpsBidBudget" readOnly>
											</div>		
											<!-- 	<div >
												<a style="cursor: pointer"
													ng-click="calculateBidBudget()"><strong><u>Add</u></strong></a>
											</div> -->
										</div>
											<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Bid spend Start date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtBidStartDate"
														name="txtBidStartDate"
														ng-model="frmPreSales.startdate"
														ng-class="{true: 'ng-border'}[preSubmitError && frmPreSales.txtBidStartDate.$invalid]"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="preSubmitError"
												ng-messages="frmPreSales.txtBidStartDate.$error">
												<em class="error help-block has-error" ng-message="required">This
													Field is required!</em>
											</div>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">Bid spend End date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtBidEndDate"
														name="txtBidEndDate"
														ng-model="frmPreSales.Enddate"
														ng-class="{true: 'ng-border'}[preSubmitError && frmPreSales.txtBidEndDate.$invalid]"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
														<div class="error-messages" ng-if="preSubmitError"
												ng-messages="frmPreSales.txtBidEndDate.$error">
												<em class="error help-block has-error" ng-message="required">This
													Field is required!</em>
											</div>
										</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">B&PS-BID Budget in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSBudgetEuro"
													name="txtBPSBudgetEuro" ng-model="frmPreSales.bnpsBudgetEuro" readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight ">B&PS TCV in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBPSTCVEuro"
													name="txtBPSTCVEuro" ng-model="frmPreSales.bnpsBPSTCVEuro" decimals="2"
													 readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">% of B&PS TCV on Total TCV</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPercentOfBPsTCV"
													name="txtPercentOfBPsTCV" ng-model="frmPreSales.percentOfBpsTCV" readOnly>
											</div>
										<label
												class="control-label col-sm-3 textAlignRight ">Pre sales Cost against WBS</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPreSalesCostWBS"
													name="txtPreSalesCostWBS" ng-model="frmPreSales.preSalesCostWBS" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight ">Pre sales Cost against WBS in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtPreSalesCostinEuro"
													name="txtPreSalesCostinEuro" ng-model="frmPreSales.preSalesCostinEuro" 
													 readOnly>
											</div>
										<label
												class="control-label col-sm-3 textAlignRight ">Balance</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtbalance"
													name="txtbalance" ng-model="frmPreSales.balance" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight ">Balance  in Euro</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"  id="txtBalanceinEuro"
													name="txtBalanceinEuro" ng-model="frmPreSales.balanceInEuro"
													 readOnly> 
											</div>
										</div>
										<div class="row marginBottom5px">
										<label
												class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
											<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" rows="3"
	                                			ng-model="frmPreSales.currentApproverCommentModel" ng-class="{true: 'ng-border'}[preSubmitError && frmPreSales.txtCurrentApproverComment.$invalid]" required>
	                                			  </textarea>
	                                			  <div class="error-messages" ng-if="preSubmitError"
												ng-messages="frmPreSales.txtCurrentApproverComment.$error">
												<em class="error help-block has-error" ng-message="required">This
													Field is required!</em>
	                             		</div>
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
										id="btnSave" ng-model = "frmPreSales.save" ng-disabled="btnSaveDisable"
										ng-click="saveBudgetDetails(frmPreSales);">Save</button>
									<button type="button" class="btn btn-primary btnSpace"
										id="btnSubmit" ng-disabled="btnSubmitDisable"
										ng-click="submitBudgetDetails(frmPreSales);">Submit</button>
									<button type="button" class="btn btn-primary btnSpace"
										id="btnDetailsCancel" ng-click="cancelRateCard(frmRateCard)">Cancel</button>

								</div>
							</div>
							
	<!-- Modal Creation for Budget -->
	<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="popupModal" role="dialog" ng-model = "popupModal">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">B&PS BID Budget</h4>
        </div>
        <div class="modal-body">
        <table class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblUpdateCountryDetails">
			<thead>
				<tr>
					<th>Nature of Exps</th>
					<th>Amount</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody id="tBodyUpdateCountryDetails" >
			<tr>
			<td>
				 <select id="ddlApproverStatus" class="form-control" ng-model="natureOfExps"  placeholder="Please select" 
				 name="ddlApproverStatus" ng-options="exp.id as exp.name for exp in expcost"
				 ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]" required>
										<option value="" selected>Please select</option>
										<!-- <option value="1">Employee Cost</option>
										<option value="2">Travel Cost</option>
										<option value="3">Purchase of Software</option>
										<option value="4">Consulting Service</option>
										<option value="5">R&D Expense</option>
										<option value="6">Others</option>
										<option value="7">Others 1</option>
										<option value="8">Others 2</option> -->
										
				</select>
			</td>
			<td>
				<input name="{{'txtAmount'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtAmount'+'_'+($index+1)}}" 
					ng-model="expAmount"  required>
			</td>
			<td>
			<button class="btn btn-info btnSpace" type="button" ng-click="addPerson(expAmount,grandAmount,expcost)">Add</button>
			</td>
		   </tr>
		    <tr ng-repeat="person in people">
          <td>
            {{ person.natureOfExps }}
          </td>
          <td>
            {{ person.expAmount }}
          </td>
          <td>
             <button class="btn btn-info btnSpace" type="button" ng-click="removePerson($index,person.expAmount,grandAmount)">Remove</button>
          </td>
        </tr>
        <tr>
         <td>
           <b>Grand Total</b> 
          </td>
          <td>
           <b><span ng-model="grandAmount">{{ grandAmount }}</span></b>
          </td>
        </tr>
		</tbody>
		</table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" ng-click="addGrandData()" data-dismiss="modal">Ok</button>
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
