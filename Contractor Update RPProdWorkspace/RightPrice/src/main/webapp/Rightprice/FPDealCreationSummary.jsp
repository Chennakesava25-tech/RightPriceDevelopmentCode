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
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
    <script type="text/javascript">
    	
// 		var app = angular.module('FPDealCreationSummaryApp', []);
		app.controller("FPDealCreationSummaryController", ['$scope','$http','$window','$sessionStorage','WebServiceFactory','$filter', function($scope,$http,$window,$sessionStorage,WebServiceFactory,$filter,$index) {
			/*  $scope.DealCreationPFSummaryHidden = true;
			 
             $scope.ShowHideDealCreationPFSummary = function () {
                $scope.DealCreationPFSummaryHidden = $scope.DealCreationPFSummaryHidden ? false : true;
            }; */
			$scope.Prev = function() 
		    {   
		        window.location='FPDealCreationFinaliseDeal';
		    };
		    
		    var rpVrsId = $sessionStorage.rpDealVersionId;	
		    var getDealDetails = function(response) {				
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		console.log("Approver Data")
		  		console.log($scope.dealDetails);
		  		console.log($scope.dealDetails[0].dealId);
		  		console.log($scope.dealDetails[0].customerId);
		  		console.log($scope.dealDetails[0].dealStartDate);
		  		console.log($scope.dealDetails[0].dealEndDate);
		  		console.log($scope.dealDetails[0].dealDescription);
		  		console.log($scope.dealDetails[0].dealStatus)
		  		console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
		  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
		  		
		  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
		  		var date = new Date(dealEndDate.substring(0,10));
		  		console.log("End Date is......... " + date);
		  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.dealDetails[0].dealEndDate = dateENd;		  		
				console.log("dateEND     ...... "+dateENd)		  		
				var dealStartDate = $scope.dealDetails[0].dealStartDate;
				var date = new Date(dealStartDate.substring(0,10));
				var dateStart = $filter('date')(date,'dd/MM/yyyy');
				$scope.dealDetails[0].dealStartDate = dateStart;
				//alert('hi 66');
				$scope.dealDetails[0].rpVersionId = $sessionStorage.rpDealVersionId;	//arvind
				$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;	
				 var startDay = new Date(dateStart);
		          var endDay = new Date(dateENd);
		          var millisecondsPerDay = 1000 * 60 * 60 * 24;
		          var millisBetween =  endDay.getTime()-startDay.getTime() 
		          var days = millisBetween / millisecondsPerDay;
		          $scope.dealDetails[0].DealDuration = Math.floor(days);	
		  		console.log($scope.dealDetails[0].percentageClose);
		  		console.log($scope.dealDetails[0].currencyId);
		  		console.log($scope.dealDetails[0].dealDuration);
		  		console.log($scope.dealDetails[0].stageId);
			};
			WebServiceFactory.getDealDetails($sessionStorage.DealModel,rpVrsId).then(getDealDetails);
		   
		}]);
    </script>
</head>
<body ng-app="RightPriceApp" ng-controller="FPDealCreationSummaryController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">FP Deal Creation - Summary </h3>
            </div>
        </div>	
        <div>
        	<form class="form-inline" role="form" name="TeamDeal" id="TeamDeal">
            	<div id="includedT&PStages" ng-include="'${contextPath}/Portal/FPdealCompletionStage.jsp'"></div>
            	<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationInformationTable.jsp'"></div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Deal - Summary </label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideDealCreationPFSummary()"> &#9660;</a>
										</div> -->
									</div>
                                </div>
                                <div class="panel-body">
                                	<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Deal Id</label>
                                        <div class="col-sm-3">
                                        	<input name="txtDealId" type="text" class="form-control" id="txtDealId"
                                            	   ng-model="txtDealIdModel" required>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Estimated TCV</label>
			                            <div class="col-sm-3 ">
			                            	<input name="txtEstimatedTCV" type="text" class="form-control" id="txtEstimatedTCV"
                                            	   ng-model="txtEstimatedTCVModel" required>
										</div>
									</div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignRight required-Field">Estimated Revenue</label>
			                            <div class="col-sm-3 ">
			                            	<input name="txtEstimatedRevenue" type="text" class="form-control" id="txtEstimatedRevenue"
                                            	   ng-model="txtEstimatedRevenueModel" required>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px "><label class="control-label col-sm-10 redColor ">Note: Weightage should be based on utilization of ratecard</label></div>
                                    <div class="row">
                                    	<div class="col-sm-2"></div>
                                    	<div class="col-sm-8">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblUploadDocuments">
													<tbody id="tBodyUploadDocuments" >
														<tr>
															<th class="firstColLeftAlign">PM%</th>
															<td>52%</td>
															<td>28%</td>
															<td>20%</td>
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
											<label class="control-label col-sm-10 ">Team Deal Details</label>
											<!-- <div class="col-sm-2 textAlignRight">
																<a href="#" class="DownArrowColor" ng-click="ShowHideTeamDealDetails()"> &#9660;</a>
															</div> -->
										</div>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="row marginBottom5px">
												<label class="control-label col-sm-2 textAlignRight ">Business Unit Head</label>
												<div class="col-sm-3">
													<input name="txtBusinessUnitHead" type="text"
														class="form-control" id="txtBusinessUnitHead"
														ng-model="txtBusinessUnitHeadModel" required>
												</div>
												<div class="col-sm-1"></div>
												<label class="control-label col-sm-2 textAlignRight ">Sales SPOC</label>
												<div class="col-sm-3">
													<input name="txtSalesSPOC" type="text"
														class="form-control" id="txtSalesSPOC"
														ng-model="txtSalesSPOCModel" required>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="row marginBottom5px">
												<label class="control-label col-sm-2 textAlignRight ">Solution SPOC</label>
												<div class="col-sm-3">
													<input name="txtSolutionSPOC" type="text"
														class="form-control" id="txtSolutionSPOC"
														ng-model="txtSolutionSPOCModel" required>
												</div>
												<div class="col-sm-1"></div>
												<label class="control-label col-sm-2 textAlignRight ">Delivery Director</label>
												<div class="col-sm-3">
													<input name="txtDeliveryDirector" type="text"
														class="form-control" id="txtDeliveryDirector"
														ng-model="txtDeliveryDirectorModel" required>
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
											<label class="control-label col-sm-10">Approver Action</label>
											<!-- <div class="col-sm-2 textAlignRight">
												<label class="DownArrowColor textAlignRight"> &#9660;</label>
											</div> -->
										</div>
									</div>
									<div class="panel-body">
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
										<div class="row">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
											<div class="col-sm-10">
												<textarea style="resize: none" name="txtApproverComment"
													id="txtApproverComment" class="form-control" rows="3"
													ng-model="txtApproverCommentModel" required></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row text-center">
				   		<div class="col-sm-12">		
							<button type="button" class="btn btn-info  btnSpace" id="btnDealSummaryCalculate">Calculate</button>
							<button type="button" class="btn btn-primary btnSpace" id="btnDealSummarySave">Save</button>	
							<button type="button" class="btn btn-danger btnSpace" id="btnDealSummarySave">Cancel</button>
							<button type="button" class="btn btn-info btnSpace"id="btnClientPrev" ng-click="Prev()">Prev</button>
							<!-- <button type="button" class="btn btn-info" id="btnClientNext"ng-click="Next()">Next</button> -->
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
