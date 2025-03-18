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
<script src="${contextPath}/resources/js/RightPrice/RateCardCreationGMRateController.js"></script>
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
<body ng-app="RightPriceApp" ng-controller="RateCardCreationGMRateController"
	ng-focus="customDatePicker()">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
		<fieldset ng-disabled="loading || showLoader">
		<div class="container">
		<div class="divEmptyThrice"></div>
			<form class="form-inline" role="form" name="frmRateCardGMCal" id="frmRateCardGMCal">
			<div id="includedRateCardStages" ng-if="autosidebar"
				ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
			<div id="includedRateCardStages" ng-if="manualsidebar"
				ng-include="'${contextPath}/Portal/RateCardManualCompletionStage.jsp'"></div>
			<div id="includedRateCardStages" ng-if="hybridsidebar"
				ng-include="'${contextPath}/Portal/RateCardHybridCompletionStage.jsp'"></div>
            <div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/RateCardCreationInformationTable.jsp'"></div>
             <div class="row" ng-if="tableIT == true;">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 "> Manual Rate Card</label>
										<div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor"> &#9660;</a>
										</div>
									</div>
                                </div>
                          <div class="panel-body" ng-hide = "TowerDetailsHidden">
                         <div class="row marginBottom5px">
                 			<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
			                 	<div class="col-sm-3">
			             			<select id="ddlCountry" class="form-control" placeholder="Please select" name="ddlCountry"
			                         		ng-model="frmRateCardGMCal.ddlCountryModel" ng-options="con.countryId as con.countryName for con in country" 
			                         		ng-change="getCity(frmRateCardGMCal.ddlCountryModel)" 
			                         		ng-class="{true: 'ng-border'} [(onSearch || onUplaod && frmRateCardGMCal.ddlCountry.$invalid)]" required>
											<option value="" selected disabled>Please select</option>
									</select>
									<div class="error-messages" ng-if="(onSearch || onUplaod)" ng-messages="frmRateCardGMCal.ddlCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
									</div>
			                	</div>	
			                 	<label class="control-label col-sm-3 textAlignRight required-Field">City</label>
			                  	<div class="col-sm-3">
			                    	<select id="ddlCity" class="form-control" placeholder="Please select" name="ddlCity"
			                          		ng-model="frmRateCardGMCal.ddlCityModel" ng-options="ci.cityId as ci.cityName for ci in city"
			                          		ng-class="{true: 'ng-border'} [(onSearch || onUplaod && frmRateCardGMCal.ddlCity.$invalid)]" required>
											<option value="" selected disabled>Please select</option>
									</select>
									<div class="error-messages" ng-if="(onSearch || onUplaod)" ng-messages="frmRateCardGMCal.ddlCity.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select City.</em>
									</div>
			                	</div>	
							</div>
									<div class="divEmptyThrice"></div>
									 <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSearch" ng-click="searchData(frmRateCardGMCal);">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div>
									<label><i><font color="red">All rates are in {{newExchangeRate}}</font></i></label>
									</div>
									<div class="table-responsive">
												<table
													 class="table clsTable table-striped table-bordered table-hover table-condensed text-center ">
													 
													<!--  class="table tblDealCre table-borderless table-condensed text-center" -->
													<thead>
														<tr>
															<th class="thBorder tdWidthSrNoFac text-center">Sr.No.</th>
															<th class="thBorder tdWidthSrNoFac text-center">Year</th>
															<th class="thBorder tdWidthQtyFac text-center">Onsite Blended Rate</th>
															<th class="thBorder tdWidthQtyFac text-center">Onsite Blended Cost</th>
															<th class="thBorder tdWidthQtyFac text-center">Offshore Blended Rate</th>
															<th class="thBorder tdWidthQtyFac text-center">Offshore Blended Cost</th>
															<th class="colRightSection thBorder tdWidthRateFac text-center" ng-hide="isMarginHide">Margin Before Discount</th>
															<th class="colRightSection thBorder tdWidthMonthsFac text-center" ng-hide="isMarginHide">Margin After Discount</th>
														</tr>									                       
													</thead>
				                                    <tbody>
														<tr ng-repeat ="yearData in yearDetails">
															<td class="colSection textAlignCenter"><strong>{{$index + 1}}</strong></td>
															<td class="colSection width5per textAlignCenter"><strong>{{yearData.rcYear}}</strong></td>
															<td class="colRightSection"><input  name="{{'txtblendedRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtblendedRate'+'_'+($index+1)}}" ng-model="yearData.onsiteRateHour" decimals="2"  ng-disabled = "isOnsiteDisable"></td>
															<td class="colRightSection"><input type="text" class="form-control" name = "{{'txtBlendedCost'+'_'+($index+1)}}" id = "{{'txtBlendedCost'+'_'+($index+1)}}" ng-model = "yearData.onsiteCostHour"  decimals="2"  ng-disabled = "isOnsiteDisable" ng-blur="calculateMargin($index,yearData.onsiteRateHour,yearData.onsiteCostHour,yearData); calculateMarginAfterDisc($index,yearData.onsiteRateHour,yearData.onsiteCostHour,yearData);"></td>
															<td class="colRightSection"><input  ng-model="yearData.offshoreRateHour" name="{{'txtblendedRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtblendedRate'+'_'+($index+1)}}"  decimals="2"  ng-disabled = "isOffshoreDisable"></td>
															<td class="colRightSection"><input type="text" class="form-control" name = "{{'txtBlendedCost'+'_'+($index+1)}}" id = "{{'txtBlendedCost'+'_'+($index+1)}}" ng-model = "yearData.offshoreCostHour"  decimals="2"  ng-disabled = "isOffshoreDisable"></td>
															<td class="colSection" ng-hide="isMarginHide"><input type="text" class="form-control" name = "{{'txtMarginBeforeDiscount'+'_'+($index+1)}}" id = "{{'txtMarginBeforeDiscount'+'_'+($index+1)}}" ng-model = "yearData.gmBeforeDiscount" disabled></td>	
															<td class="colSection" ng-hide="isMarginHide"><input type="text" class="form-control" name = "{{'txtMarginAfterDiscount'+'_'+($index+1)}}" id = "{{'txtMarginAfterDiscount'+'_'+($index+1)}}" ng-model = "yearData.gmAfterDiscount" disabled></td>	
														</tr>
													</tbody> 
												</table>
										</div>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<div class="table-responsive">
												<table  class="table clsTable table-striped table-bordered table-hover table-condensed text-center ">
													<thead>
													 <!-- class="table tblDealCre table-borderless table-condensed text-center"> -->
														<tr>
															<th class="thBorder tdWidthSrNoFac text-center">Sr.No.</th>
															<th class="colRightSection thBorder tdWidthMonthsFac text-center">Right Price Onsite Blended Rate</th>
															<th class="colRightSection thBorder tdWidthMonthsFac text-center">Right Price Onsite Blended Cost</th>
															<th class="colRightSection thBorder tdWidthMonthsFac text-center">Right Price Offshore Blended Rate</th>
															<th class="colRightSection thBorder tdWidthMonthsFac text-center">Right Price Offshore Blended Cost</th>
														</tr>									                       
													</thead>
				                                    <tbody>
														<tr ng-repeat ="yearData in yearDetails" ng-show ="yearData.yearDetailHide">
															<td class="colSection textAlignCenter"><strong>{{$index + 1}}</strong></td>
															<td class="colRightSection"><input  ng-model="yearData.onsiteMasterBlendedRate" name="{{'txtmasterBlendedRate'+'_'+($index+1)}}" type="text" class="form-control colSection" id="{{'txtmasterBlendedRate'+'_'+($index+1)}}"  decimals="2" ng-disabled = "isOnsiteDisable"></td>
															<td class="colRightSection"><input  ng-model="yearData.onsiteMasterBlendedCost" name="{{'txtmasterBlendedCost'+'_'+($index+1)}}" type="text" class="form-control colSection" id="{{'txtmasterBlendedCost'+'_'+($index+1)}}"  decimals="2" ng-disabled = "isOnsiteDisable"></td>
															<td class="colRightSection"><input  ng-model="yearData.offshoreMasterBlendedRate" name="{{'txtmasterBlendedRate'+'_'+($index+1)}}" type="text" class="form-control colSection" id="{{'txtmasterBlendedRate'+'_'+($index+1)}}"  decimals="2" ng-disabled = "isOffshoreDisable"></td>
															<td class="colRightSection"><input  ng-model="yearData.offshoreMasterBlendedCost" name="{{'txtmasterBlendedCost'+'_'+($index+1)}}" type="text" class="form-control colSection" id="{{'txtmasterBlendedCost'+'_'+($index+1)}}"  decimals="2" ng-disabled = "isOffshoreDisable"></td>
														</tr>
													</tbody>  
												</table>
										</div>
										<div class="divEmptyThrice"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="divEmptyThrice"></div>
                </form>
                
                <div class="row" ng-if="tableKPO == true">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">KPO Manual Rate Card</label>
										
									</div>
                                </div>
                          <div class="panel-body" >
                         
							<div class="divEmptyThrice"></div>
									<div>
									<label><i><font color="red">All rates are in {{currencyName}}</font></i></label>
									</div>
									<div class="table-responsive">
												<table
													 class="table tblDealCre table-borderless table-condensed text-center">
													<thead>
														<tr>
															<th class="thBorder tdWidthQtyFac text-center">Blended Rate</th>
															<th class="thBorder tdWidthQtyFac text-center">Blended Cost</th>
															<th class="colRightSection thBorder tdWidthRateFac text-center">Margin Before Discount %</th>
															<th class="colRightSection thBorder tdWidthMonthsFac text-center">Margin After Discount %</th>
														</tr>									                       
													</thead>
				                                    <tbody>
														<tr ng-repeat ="yearData in yearDetails" ng-show ="yearData.yearDetailHide">
															<td class="colRightSection"><input  ng-model="yearData.onsiteRateHour" name="{{'txtblendedRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtblendedRate'+'_'+($index+1)}}"  decimals="2"></td>
															<td class="colRightSection"><input type="text" class="form-control" name = "{{'txtBlendedCost'+'_'+($index+1)}}" id = "{{'txtBlendedCost'+'_'+($index+1)}}" ng-model = "yearData.onsiteCostHour"  decimals="2"></td>
															<td class="colRightSection"><input type="text" class="form-control" name = "{{'txtMarginBeforeDiscount'+'_'+($index+1)}}" id = "{{'txtMarginBeforeDiscount'+'_'+($index+1)}}" ng-model = "yearData.gmBeforeDiscount"></td>	
															<td class="colRightSection"><input type="text"  name = "{{'txtMarginAfterDiscount'+'_'+($index+1)}}" id = "{{'txtMarginAfterDiscount'+'_'+($index+1)}}" ng-model = "yearData.gmAfterDiscount"></td> 
														</tr>
													</tbody> 
												</table>
										</div>
										<div class="divEmptyThrice"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="divEmptyThrice"></div>
                <ng-form class="form-inline" role="form" name="frmRateCardManualUpload" id="frmRateCardManualUpload">
                <div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle"
										ng-click="ShowHideUpload()">
										<div class="row ">
											<label class="control-label col-sm-10 ">Upload Manual
												Rate Card</label>
										</div>
									</div>
									<div class="panel-body" ng-hide="UploadHidden">
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
													ng-class="{true: 'ng-border'}[(onSave || onUplaod) && frmRateCardManualUpload.fuUploadFilename.$invalid]" required>
												<div class="error-messages" ng-if="(onSave || onUplaod)"
													ng-messages="frmRateCardManualUpload.fuUploadFilename.$error">
														<em class="error help-block has-error" ng-message="extension">File
														format is not valid for uploading!</em><em
														class="error help-block has-error" ng-message="required">Please insert File to Upload.</em>
												</div>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnUpload"
													ng-click="uploadData(frmRateCardManualUpload,frmRateCardGMCal);" ng-disabled="isDisabled" >Upload</button>
											</div>
										<!-- 	<div class="col-sm-1 divPaddingLeftZero">
												<div class="divDownloadImg">
														<a href="#" id="APPROVAL_ATTACHMENT_ID"
															class="anchorTrancColor"
															ng-click="downloadFile(manualRCData);"> <span model= "manualAttachementModel" id="manualAttachementFile"></span>
														</a>
												 </div>
										</div> -->
										</div>
										<div class="divEmptyThrice"></div>
										<table class="	table clsTable table-striped table-bordered table-hover table-condensed "
											border="0" id="RateCardManualTable">
								<thead>
									<tr>
										<th width="40%">File Name</th>
										<th width="10%">Date Of Upload (dd/mm/yyyy)</th>
										<th width="5%">Action</th>
									</tr>
								</thead>
								<tbody id="tBody">
									<tr id="Manualattachements" ng-repeat="row in versionAttachment|orderBy:'-updatedOn'">
										<td width="30%"><a href="#" id="Attach_Download"
											class="control-label  textAlignLeft" ng-click="downloadFileWithFileName(row.dealAttachmentId);"><label>{{row.fileName}}</label></a></td>
										<td width="20%"><label>{{row.createdOn}}</label></td>
										<td width="15%"><a href="#" id="Attach_Delete"
											class="control-label  textAlignLeft"
											ng-click="deleteManualFile(row.dealAttachmentId);"><label>Delete</label></a>
										</td>
									</tr>
									<tr>
									</tr>
								</tbody>
							</table>
									</div>
								</div>
							</div>
						</div>
					</div> 
			</ng-form>
				 <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-click="saveMarginDetails()" ng-disabled="isDisabled">Save</button>	
											<!-- <button type="button" class="btn btn-danger btnSpace" id="btnCancel">Cancel</button> -->
											<button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnnext" ng-click="Next()">Next</button>
										</div>
									</div>
			</div>
		</fieldset>
</body>
<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</html>