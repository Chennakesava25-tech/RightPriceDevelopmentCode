<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
 <script type="text/javascript">
  	
// var app = angular.module('MyDashBoardApp', []);
app.controller("MyDashBoardController",  ['$scope','$location','$anchorScroll','$http','$window', function($scope,$location,$anchorScroll,$http,$window,$index){
	$scope.moveTop = function()
	{
		$location.hash('PageHeading'); 
		$anchorScroll();
	};
	$scope.moveBottom = function()
	{
		$location.hash('includedFooter'); 
		$anchorScroll();
	};
}]);
  </script>
</head>
<body ng-app="RightPriceApp" ng-controller="MyDashBoardController" >
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
     <fieldset ng-disabled="loading || showLoader">
	    <div class="container">
	        <div class="divEmpty"></div>
	        <div class="row marginBottom5px">
	            <div class="col-sm-12">
	                <h3 class="text-left" id="PageHeading">RiskManagersDashBoard</h3>
	            </div>
	        </div>	
	        <div>
            <form class="form-inline" role="form" name="RateCard" id="RateCard">
				<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Deals - Pending Approvals</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideAddContractor()"> &#9660;</a>
										</div> -->
									</div>
	                            </div>
	                            <div class="panel-body">
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblPendingApproval">
													<thead>
														<tr>
															<th>View</th>
															<th>Customer</th>
															<th>Deal Id</th>
															<th>Deal Description</th>
															<th>Start Date</th>
															<th>End Date</th>
														</tr>
													</thead>
													<tbody id="tBodyPendingApproval" >
														<tr >
															<td><a href="#">Select</a></td>
															<td>Scripps Healthcare</td>
															<td>933502</td>
															<td>Scripps - PMO Business Analyst - Sept 2017</td>
															<td>07/11/2017</td>
															<td>07/11/2019</td>
														</tr>
														<tr>															
															<td><a href="#">Select</a></td>
															<td>Honda NA</td>
															<td>933406</td>
															<td>Honda T&M engagement</td>
															<td>07/01/2017</td>
															<td>07/21/2018</td>
														</tr>	
														<tr >
															<td><a href="#">Select</a></td>
															<td>Scripps Healthcare</td>
															<td>933217</td>
															<td>Scripps - KM EAD Testing - Dec'2017</td>
															<td>04/11/2017</td>
															<td>07/11/2018</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                        		</div>  
							</div>
			       		</div>
					</div>
				</div>	
	          	<div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">View Deal Details</label>
										<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideRateCardDetails()"> &#9660;</a>
										</div> -->
									</div>
                                </div>
                               <!--  <div class="panel-body" ng-hide = "RateCardDetailsHidden"> -->
                                <div class="panel-body">
	                                <fieldset disabled>
	                                	<div class="row marginBottom5px text-right">
	                                		<label class="control-label col-sm-2 textAlignRight ">Deal ID </label>
	                                    	<div class="col-sm-3"> 
	                                         	<input name="txtViewDetailsDealID" type="text" class="form-control" id="txtViewDetailsDealID"
	                                            	   ng-model="txtViewDetailsDealIDModel" required>
	                                       	</div>
											<label class="control-label col-sm-3 textAlignRight ">Deal Description</label>
								            <div class="col-sm-3">  
								              <input name="txtViewDetailsDealDescription" type="text" class="form-control" id="txtViewDetailsDealDescription"
	                                            	   ng-model="txtViewDetailsDealDescriptionModel" required>
	                                       </div>
										</div>	
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">Name of the Client</label>
	                                        <div class="col-sm-3">
	                                        <input name="txtViewDetailsClientName" type="text" class="form-control" id="txtViewDetailsClientName"
	                                            	   ng-model="txtViewDetailsClientNameModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Address</label>
	                                        <div class="col-sm-3">
	                                           	<input name="txtViewDetailsAddress" type="text" class="form-control" id="txtViewDetailsAddress"
	                                            	   ng-model="txtViewDetailsAddressModel" required>
	                                        </div>
										</div>
										<div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">Deal Status</label>
	                                        <div class="col-sm-3">
	                                           		<input name="txtViewDetailsDealStatus" type="text" class="form-control" id="txtViewDetailsDealStatus"
	                                            	   ng-model="txtViewDetailsDealStatusModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Sales Spoc</label>
	                                        <div class="col-sm-3">
	                                           	<input name="txtViewDetailsSalesSpoc" type="text" class="form-control" id="txtViewDetailsSalesSpoc"
	                                            	   ng-model="txtViewDetailsSalesSpocModel" required>
	                                        </div>
	                                    </div>
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">Deal TCV</label>
	                                        <div class="col-sm-3">
	                                            <input type="text" class="form-control" id="txtViewDetailsDealTCV"
													name="txtViewDetailsDealTCV" ng-model="txtViewDetailsDealTCVModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Deal Priority</label>
	                                        <div class="col-sm-3">
	                                            <input type="text" class="form-control" id="txtViewDetailsDealPriority"name="txtViewDetailsDealPriority"
	                                            	  ng-model="txtViewDetailsDealPriorityModel" required>
	                                        </div>
	                                    </div>
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">Deal Start Date</label>
	                                        <div class="col-sm-3">
	                                            <input type="text" class="form-control customDate"
													placeholder="dd/mm/yyyy" id="txViewDetailsDealStartDate"
													name="txViewDetailsDealStartDate" ng-model="txViewDetailsDealStartDateModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Deal End Date</label>
	                                        <div class="col-sm-3">
	                                            <input type="text" class="form-control customDate"
													placeholder="dd/mm/yyyy" id="txViewDetailsDealEndDate"
													name="txViewDetailsDealEndDate" ng-model="txViewDetailsDealEndDateModel" required>
	                                        </div>
	                                    </div>
	                                    <div class="row marginBottom5px">
	                                    	<label class="control-label col-sm-2 textAlignRight ">Duration</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsDuration" type="text" class="form-control" id="txtViewDetailsDuration"
	                                            	   ng-model="txtViewDetailsDurationModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Currency</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsDealCurrency" type="text" class="form-control" id="txtViewDetailsCurrency"
	                                            	   ng-model="txtViewDetailsCurrencyModel" required>
	                                        </div>
	                                        
										</div>
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">Percentage Close</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsPercentageClose" type="text" class="form-control" id="txtViewDetailsPercentageClose"
	                                            	   ng-model="txtViewDetailsPercentageCloseModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Stage</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsStage" type="text" class="form-control" id="txtViewDetailsStage"
	                                            	   ng-model="txtViewDetailsStageModel" required>
	                                        </div>
	                                    </div>
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">FP Type</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsFPType" type="text" class="form-control" id="txtViewDetailsFPType"
	                                            	   ng-model="txtViewDetailsFPTypeModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Deal Type</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsDealType" type="text" class="form-control" id="txtViewDetailsDealType"
	                                            	   ng-model="txtViewDetailsDealTypeModel" required>
	                                        </div>
	                                    </div> 
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">Onsite Hrs</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsOnsiteHrs" type="text" class="form-control" id="txtViewDetailsOnsiteHrs"
	                                            	   ng-model="txtViewDetailsOnsiteHrsModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Offshore Hrs(Client)</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsOffshoreHrs" type="text" class="form-control" id="txtViewDetailsOffshoreHrs"
	                                            	   ng-model="txtViewDetailsOffshoreHrsModel" required>
	                                        </div>
	                                    </div>
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight ">Whether the Deal is New or Renewal? </label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsNewOrRenewal" type="text" class="form-control" id="txtViewDetailsNewOrRenewal"
	                                            	   ng-model="txtViewDetailsNewOrRenewalModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Whether the Project is Agile <br> Based?</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsAgileBased" type="text" class="form-control" id="txtRateCardExpectedOffshoreResource"
	                                            	   ng-model="txtRateCardExpectedOffshoreResourceModel" required>
	                                        </div>
	                                    </div>
	                                    <div class="row">
	                                        <label class="control-label col-sm-2 textAlignRight ">Onsite Facility </label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsOnsiteFacility" type="text" class="form-control" id="txtViewDetailsOnsiteFacility"
	                                            	   ng-model="txtViewDetailsOnsiteFacilityModel" required>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight ">Is Bizops Involved</label>
	                                        <div class="col-sm-3">
	                                             <input name="txtViewDetailsIsBizopsInvolved" type="text" class="form-control" id="txtViewDetailsIsBizopsInvolved"
	                                            	   ng-model="txtViewDetailsIsBizopsInvolvedModel" required>
	                                        </div>
	                                    </div>
	                            	</fieldset>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Verify uploaded documents</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideAddContractor()"> &#9660;</a>
										</div> -->
									</div>
	                            </div>
	                            <div class="panel-body">
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblVerifyDocuments">
													<thead>
														<tr>
															<th>Document Name</th>
															<th>Download</th>
															<th>Verified OK</th>
														</tr>
													</thead>
													<tbody id="tBodyVerifyDocuments" >
														<tr >
															<td>Proposal Response doc </td>
															<td><a href="#">Select</a></td>
															<td><input type="checkbox" name="cbxVerifiedOKRow1" id="cbxVerifiedOKRow1" class="margingRightChkBx"></td>
														</tr>
														<tr>															
															<td>Effort Estimation sheet</td>
															<td><a href="#">Select</a></td>
															<td><input type="checkbox" name="cbxVerifiedOKRow2" id="cbxVerifiedOKRow2" class="margingRightChkBx"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmpty"></div>
									<div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Approver
											Action</label>
										<div class="col-sm-2">
											<select id="ddlApproverAction" class="form-control"
												placeholder="Please select" name="ddlApproverAction"
												ng-model="ddlApproverActionModel" required>
												<option value="" disabled="" selected="selected">Please
													select</option>
												<option value="3">Approve</option>
												<option value="1">Reject</option>
											</select>
										</div>
									</div>
									<div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
										<div class="col-sm-10">
											<textarea style="resize: none" name="txtApproverComment"
												id="txtApproverComment" class="form-control" rows="3"
												ng-model="txtApproverCommentModel" required></textarea>
										</div>
									</div>
									<div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnVerifyDocumentsSubmit">Submit</button>
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
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
