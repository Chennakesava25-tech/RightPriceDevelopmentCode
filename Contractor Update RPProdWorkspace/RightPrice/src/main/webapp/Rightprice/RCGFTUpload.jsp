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
<script
	src="${contextPath}/resources/js/RightPrice/RCGFTUploadController.js"></script>
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
<body ng-app="RightPriceApp" ng-controller="RateCardCreationController"
	ng-focus="customDatePicker()" >
<%-- 	ng-focus="customDatePicker()" ng-init="currentUser('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')"> --%>
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">GFT - Upload Rate Card Excel</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="frmRateCard"
					id="frmRateCard" novalidate>
					<fieldset ng-disabled="isViewRequest">
						<div id="includedRateCardStages" ng-if="autosidebar"
							ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
						<div id="includedRateCardStages" ng-if="manualsidebar"
							ng-include="'${contextPath}/Portal/RateCardManualCompletionStage.jsp'"></div>
						<div id="includedRateCardStages" ng-if="hybridsidebar"
							ng-include="'${contextPath}/Portal/RateCardHybridCompletionStage.jsp'"></div>
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
										<!--  <div class="panel-body" ng-hide = "RateCardDetailsHidden"> -->
										<div class="panel-body">
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-5 textAlignRight required-Field">Customer</label>
												<div class="col-sm-3">
													<select id="ddlRateCardCustomer" class="form-control"
														placeholder="Please select" name="ddlRateCardCustomer"
														ng-model="frmRateCard.customer"
														ng-change="resetFormDataOnCustomerChange();getRCId(frmRateCard.customer);"
														ng-options="cvi.customer.customerId as cvi.customer.customerName for cvi in customer| orderBy:'customer.customerName'" 
														required
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardCustomer.$invalid]">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardCustomer.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Customer</em>
													</div>
												</div>
											</div>
											<div class="divEmptyThrice"></div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Rate
													Card ID</label>
												<div class="col-sm-3">
													<select id="ddlRateCardID" class="form-control"
														placeholder="Please select" name="ddlRateCardID"
														ng-model="frmRateCard.rateCardId"
														ng-disabled="isRateCardIDdisabled"
														ng-options="rcd.rcId as rcd.rcId for rcd in rateCardId"
														ng-change="getRateCardDetailsFromRCId(frmRateCard.rateCardId);"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardID.$invalid]"
														ng-required="!isRateCardIDdisabled">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardID.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Rate Card ID</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight required-Field">Rate
													Card Name</label>
												<div class="col-sm-3">
													<input name="txtRateCardRateCardName" type="text"
														class="form-control" id="txtRateCardRateCardName"
														ng-maxlength="100" ng-pattern="/^[a-z\d\_\:\-'\s]+$/i"
														ng-disabled="true"
														ng-model="frmRateCard.ratecardname"
														
														>
												</div>
												</div>
												<div class="divEmptyThrice"></div>
												<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Country</label>
												<div class="col-sm-3">
													<select id="ddlCountry" class="form-control"
														placeholder="Please select" name="ddlCountry"
														ng-model="frmRateCard.ddlCountryModel"
														ng-class="{true: 'ng-border'}[onSearch && frmRateCard.ddlCountryModel.$invalid]"
														ng-options="con.countryId as con.countryName for con in countries | unique: 'countryId'"
														ng-change="getCity(frmRateCard.ddlCountryModel)"
														required>
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="onSearch"
														ng-messages="frmRateCard.ddlCountry.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Country</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight required-Field">City</label>
												<div class="col-sm-3">
													<select id="ddlCity" class="form-control"
														placeholder="Please select" name="ddlCity"
														ng-model="frmRateCard.ddlCityModel"
														ng-change="getFileData(frmRateCard.ddlCityModel)"
														ng-class="{true: 'ng-border'}[onSearch && frmRateCard.ddlCityModel.$invalid]"
														ng-options="ci.cityId as ci.cityName for ci in city"
														required>
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="onSearch"
														ng-messages="frmRateCard.ddlCity.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select city</em>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					<ng-form class="form-inline" role="form" name="frmRateCardManualUpload" id="frmRateCardManualUpload">
						<div class="row">
						<div class="col-sm-12">
							<div class="panel-group" >
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10 ">Upload Manual
										</div>
									</div>
									<div class="panel-body">
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2 "></div>
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Upload
												File name</label>
											<div class="col-sm-3 ">
												<input type="file" class="form-control"
													name="fuUploadFilename" id="fuUploadFilename"
													ng-model="frmRateCardManualUpload.fuUploadFilenameModel"
													valid-file-rate-card
													ng-class="{true: 'ng-border'}[(upload) && frmRateCardManualUpload.fuUploadFilename.$invalid]"
													>
													<!-- check-file-size="frmRateCardManualUpload.fuUploadFilenameModel" -->
												<div class="error-messages" ng-if="(upload)"
													ng-messages="frmRateCardManualUpload.fuUploadFilename.$error">
													<!-- <em class="error help-block has-error"
														ng-message="checkfilesize">File size is not valid for -->
														uploading!</em> <em class="error help-block has-error"
														ng-message="required">Please upload file</em> <em
														class="error help-block has-error" ng-message="extension">File
														format is not valid for uploading!</em>
												</div>
											</div>
											<div class="col-sm-1">
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
								<div>
								<fieldset ng-disabled="IsDisabled">
									<table
										class="	table clsTable table-striped table-bordered table-hover table-condensed "
										border="0" id="PolicyReportTbl">
										<thead>
											<tr>
												<th width="60%">File Name</th>
												<th width="15%">Date Of Upload [dd/mm/yyyy]</th>
												<th width="15%">Action</th>
											</tr>
										</thead>
										<tbody id="tBody">
											<tr id="ManualDealattachements"
												ng-repeat="row in versionAttachment |orderBy:'-updatedOn' ">
												<td width="60%"><a href="#" id="Attach_Download"
													class="control-label  textAlignLeft"
													ng-click="downloadFileWithFileName(row.dealAttachmentId);"><label>{{row.fileName}}</label></a></td>
												<td width="25%"><label>{{row.createdOn}}</label></td>
												<td width="15%"><a href="#" id="Attach_Delete"
													class="control-label  textAlignLeft"
													ng-click="deleteManualFile(row.dealAttachmentId);"><label>Delete</label></a>
												</td>

											</tr>
											<tr>
											</tr>
										</tbody>
									</table>
								</fieldset>
								</div>
								</div>
							</div>
							</div>
						</div>
					</div>
				</ng-form>
					<div class="row text-center">
						<div class="col-sm-12">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnDetailsSubmitToGFT" ng-show="showBtnSubmitTOGFt" ng-click="updateRateCard(frmRateCard.rateCardId);">Submit To GFT</button>
							<button type="button" class="btn btn-primary btnSpace"
								id="btnDetailsSave" ng-disabled="btnDisable"
								ng-click="saveRateCard(frmRateCard);">Save</button>
							<!-- <button type="button" class="btn btn-danger btnSpace"
								id="btnDetailsCancel">Cancel</button> -->
							<button type="button" class="btn btn-info " id="btnDetailsNext"
								ng-click="clicked()" ng-disabled="isNextbtnDisable" >Next</button>
						</div>
					</div>
				</form>
				<div class="divEmptyThrice"></div>
			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
