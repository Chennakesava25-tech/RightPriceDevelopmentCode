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
<script src="${contextPath}/resources/js/RightPrice/MastersRateCard.js"></script>
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
<body ng-app="RightPriceApp" ng-controller="MastersRateCardController"
ng-init="currentUser('<%=session.getAttribute("user")%>')"
<%-- ng-init="currentUser('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')" --%>
	ng-focus="customDatePicker()">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">Master - Rate Card</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="frmRateCard"
					id="frmRateCard">
					<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Rate Card
												Details</label>
										</div>
									</div>
									<div class="panel-body">
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Rate
												Card ID</label>
											<div class="col-sm-3">
												<select id="ddlRateCardID" class="form-control"
													placeholder="Please select" name="ddlRateCardID"
													ng-model="frmRateCard.rateCardId"
													ng-options="rcd.rcId as rcd.rcId for rcd in rateCardId| orderBy:'rcId'"
													ng-change="putRateCardDetails(frmRateCard.rateCardId);searchFile(frmRateCard.rateCardId)"
													required
													ng-class="{true: 'ng-border'}[(saved || upload) && frmRateCard.ddlRateCardID.$invalid]">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="(saved || upload)"
													ng-messages="frmRateCard.ddlRateCardID.$error">
													<em class="error help-block has-error"
														ng-message="required">Please select Rate Card ID</em>
												</div>
											</div>
											<div class="col-sm-1">
												<a style="cursor: pointer" ng-hide="isHide"
													ng-click="showRateCardDetails(rcd.rateCardId)"><strong><u>View</u></strong></a>
											</div>
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Rate
												Card Name</label>
											<div class="col-sm-3">
												<input name="txtRateCardRateCardName" type="text"
													class="form-control" id="txtRateCardRateCardName"
													ng-model="frmRateCard.RateCardRateCardName" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Customer</label>
											<div class="col-sm-3">
												<input name="txtRateCardCustomer" type="text"
													class="form-control" id="txtRateCardCustomer"
													ng-model="frmRateCard.RateCardCustomer" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Start
												Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													placeholder="dd/mm/yyyy" id="txtRateCardStartDate"
													name="txtRateCardStartDate"
													ng-model="frmRateCard.RateCardStartDate" readOnly>
											</div>
											<label
												class="control-label col-sm-3 textAlignRight required-Field">End
												Date</label>
											<div class="col-sm-3">
												<input type="text" class="form-control"
													placeholder="dd/mm/yyyy" id="txtRateCardEndDate"
													name="txtRateCardEndDate"
													ng-model="frmRateCard.RateCardEndDate" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Current
												Status</label>
											<div class="col-sm-3">
												<input name="txtRateCardCustomer" type="text"
													class="form-control" id="txtRateCardStatus"
													ng-model="frmRateCard.RateCardstatus" readOnly>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Action</label>
											<div class="col-sm-3">
												<select id="ddlRateCardAction" class="form-control"
													placeholder="Please select" name="ddlRateCardAction"
													ng-model="frmRateCard.action"
													ng-change="changeAction(frmRateCard.action)" required
													ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardAction.$invalid]">
													<option value="" selected disabled>Please select</option>
													<option value="1">Deactivate</option>
													<option value="0">Extend</option>
												</select>
												<div class="error-messages" ng-if="saved"
													ng-messages="frmRateCard.ddlRateCardAction.$error">
													<em class="error help-block has-error"
														ng-message="required">Please select Action</em>
												</div>
											</div>
											<div ng-hide="isDisabled">
												<label
													class="control-label col-sm-3 textAlignRight required-Field">Expected
													End Date</label>
												<div class="col-sm-3">
													<!-- check-expected-end-date-Master-Salery -->
													<!--  use above check for comparing end date -->
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardExpectedEndDate"
														name="txtRateCardExpectedEndDate"
														ng-model="frmRateCard.expectedenddate"
														ng-change="calApplicableYears(frmRateCard.startdate,frmRateCard.enddate);"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardEndDate.$invalid]"
														ng-required="!isDisabled" required
														check-expected-end-date 
														databinding="[frmRateCard.RateCardStartDate,frmRateCard.RateCardEndDate,frmRateCard.action]"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardExpectedEndDate.$error">
														<em class="error help-block has-error"
															ng-message="required">Expected End Date is required!</em>
														<em class="error help-block has-error"
															ng-message="pattern">Expected End Date is invalid!</em> <em
															class="error help-block has-error"
															ng-message="checkExpectedEndDate">Expected End Date
															should be greater than End Date.</em>
													</div>
												</div>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
											<div class="col-sm-3" style="width: 80%; height: 10%">
												<textarea style="resize: none" name="txtRateCardCmnt"
													type="text" class="form-control" id="txtRateCardCmnt"
													ng-model="frmRateCard.RateCardCmnt"
													ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardCmnt.$invalid]"
													ng-required="isTxtRequired">
													</textarea>
												<div class="error-messages" ng-if="saved"
													ng-messages="frmRateCard.txtRateCardCmnt.$error">
													<em class="error help-block has-error"
														ng-message="required">Comments are required!</em>
												</div>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Previous
												Comments</label>
											<div class="col-sm-3" style="width: 80%; height: 100%">
												<textarea name="txtRateCardPrvCmnt" type="text"
													class="form-control" id="txtRatePrvCardCmnt"
													ng-model="frmRateCard.RateCardPrvCmnt" readonly>
													</textarea>
											</div>
										</div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Upload
												File name</label>
											<div class="col-sm-3">
												<input type="file" class="form-control"
													name="fuUploadFilename" id="fuUploadFilename"
													ng-model="frmRateCard.fuUploadFilenameModel"
													valid-File
													ng-class="{true: 'ng-border'}[(onSave || onUplaod) && frmRateCard.fuUploadFilename.$invalid]"
													required>
												<div class="error-messages" ng-if="(onSave || onUplaod)"
													ng-messages="frmRateCard.fuUploadFilename.$error">
													<em class="error help-block has-error"
														ng-message="extension">File format is not valid for
														uploading!</em><em class="error help-block has-error"
														ng-message="required">Please insert File to Upload.</em>
												</div>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnUpload" ng-click="uploadData(frmRateCard,frmRateCard.fuUploadFilenameModel);"
													ng-disabled="uploadBtnDisable">Upload</button>
											</div>
										</div>
							
								<div style = "padding-left: 205px;padding-right: 52px" >
							<div class="row marginBottom5px">			
							<fieldset ng-disabled="IsDisabled" ng-hide="tableHide">
									<table
										class="	table clsTable table-striped table-bordered table-hover table-condensed "
										border="0" id="PolicyReportTbl">
										<thead>
											<tr>
												<th width="40%">File Name</th>
												<th width="15%">Date Of Upload [dd/mm/yyyy]</th>
												<th width="15%">Action</th>
											</tr>
										</thead>
										<tbody id="tBody">
											<tr id="ManualDealattachements"
												ng-repeat="row in MDAttachementData">
												<td width="40%"><a href="#" id="Attach_Download"
													class="control-label  textAlignLeft"
													ng-click="downloadFile(row.dealAttachmentId);"><label>{{row.fileName}}</label></a></td>
												<td width="25%"><label>{{row.createdOn}}</label></td>
												<td width="15%"><a href="#" id="Attach_Delete"
													class="control-label  textAlignLeft"
													ng-click="deleteFile(row.dealAttachmentId,frmRateCard.rateCardId);"><label>Delete</label></a>
												</td>

											</tr>
											<tr>
											</tr>


										</tbody>
									</table>
								</fieldset>
									</div>
									</div>
										
										<div class="divEmptyThrice"></div>
										<div class="row text-center">
											<div class="col-sm-12">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnDetailsSave" ng-disabled="btnDisable"
													ng-click="saveRateCard(frmRateCard);">Save</button>
												<button type="button" class="btn btn-danger btnSpace"
													id="btnDetailsCancel"
													ng-click="cancelRateCard(frmRateCard)">Cancel</button>

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
