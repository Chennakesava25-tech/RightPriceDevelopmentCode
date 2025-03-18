<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Eviden  RightPrice Portal</title>
    <meta charset="utf-8">
    <meta name="csrf-token" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Support/CSS/SAPStyleSheet.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Support/CSS/loader.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/Support/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery.serializeJSON.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery-ui.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/jquery-ui.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap-dialog.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap-dialog.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/ie10-viewport-bug-workaround.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/Support/CSS/sticky-footer-navbar.css" rel="stylesheet"/>
	<script src="${pageContext.request.contextPath}/Support/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${pageContext.request.contextPath}/Support/js/angular.js"></script>
	<script src="${pageContext.request.contextPath}/Support/js/jqueryValidations.js"></script>
    <script type="text/javascript">
    	
		var app = angular.module('DealCreationCostSummaryApp', []);
		app.controller("DealCreationCostSummaryController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
			 $scope.CostSummaryHidden = true;
             $scope.ShowHideCostSummary = function () {
                $scope.CostSummaryHidden = $scope.CostSummaryHidden ? false : true;
            };
		}]);
    </script>
</head>
<body ng-app="DealCreationCostSummaryApp" ng-controller="DealCreationCostSummaryController" >
    <div id="includedContent" ng-include="'/RightPricePortal/Portal/Header.jsp'"></div>
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">Deal Creation - Cost Summary</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="RateCard" id="RateCard">
            	<div id="includedT&PStages" ng-include="'/RightPricePortal/Portal/PFdealCompletionStage.jsp'"></div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 "> Cost Summary</label>
										<div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideCostSummary()"> &#9660;</a>
										</div>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "CostSummaryHidden">
                                	<div class="row marginBottom5px"><label class="control-label col-sm-10 ">Please select the onsite location to see the cost break-up for both Onsite and Offshore</label></div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignRight required-Field">Onsite Location</label>
                                        <div class="col-sm-3">
											<select class="form-control" id="ddlOnsiteLocation" name="ddlOnsiteLocation" placeholder="Please select"
												ng-model="ddlOnsiteLocationModel" required >
												<option value="" selected desaibled>Please select</option>
												<option value="1">UK</option>
												<option value="2">US</option>
											</select>
										</div>
										<label class="control-label col-sm-3 textAlignRight required-Field">City</label>
                                        <div class="col-sm-3">
											<select class="form-control" id="ddlCity" name="ddlCity" placeholder="Please select"
												ng-model="ddlCityModel" required >
												<option value="" selected desaibled>Please select</option>
												<option value="1">A</option>
												<option value="2">B</option>
											</select>
										</div>
									</div>										
									<div class="row marginBottom5px"><label class="control-label col-sm-10 redColor ">Note: All the values are in USD</label></div>
									<div class="row"><label class="control-label col-sm-10 "><h5><b>Cost Breakup</b></h5></label></div>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblCostBreakup">
													<thead>
														<tr>
															<th  class="width20per">Direct cost details</th>
															<th>May-17</th>
															<th>Jun-17</th>
															<th>Jul-17</th>
															<th>Aug-17</th>
															<th>Sep-17</th>
															<th>oct-17</th>
															<th>Nov-17</th>
															<th>Dec-17</th>
															<th>Total 2017</th>
															<th>Total</th>
														</tr>
													</thead>
													<tbody id="tBodyCostBreakup" >
														<tr>
															<th>US Local (INC)</th>
															<td>32645</td>
															<td>32645</td>
															<td>32645</td>
															<td>20762</td>
															<td>20762</td>
															<td>20762</td>
															<td>20762</td>
															<td>36539</td>
															<td>217519</td>
															<td>217519</td>
														</tr>	
														<tr>
															<th>Deputed L1</th>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
														</tr>
														<tr>
															<th>Deputed H1</th>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
														</tr>
														<tr>
															<th>Deputed B1</th>
															<td>5299</td>
															<td>2649</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
															<td>2896</td>
															<td>1448</td>
															<td>12293</td>
															<td>12293</td>
														</tr>
														<tr>
															<th>Offshore</th>
															<td>41427</td>
															<td>57248</td>
															<td>61066</td>
															<td>62880</td>
															<td>58223</td>
															<td>52565</td>
															<td>44279</td>
															<td>39017</td>
															<td>416704</td>
															<td>416704</td>
														</tr>
														<tr>
															<th>US Payroll Tax</th>
															<td>2713</td>
															<td>2713</td>
															<td>2713</td>
															<td>1725</td>
															<td>1725</td>
															<td>1725</td>
															<td>1725</td>
															<td>3036</td>
															<td>18076</td>
															<td>18076</td>
														</tr>
														<tr>
															<th>Health Insurance/Benefits</th>
															<td>2158</td>
															<td>2426</td>
															<td>2456</td>
															<td>2152</td>
															<td>2034</td>
															<td>1885</td>
															<td>1795</td>
															<td>2119</td>
															<td>17023</td>
															<td>17023</td>
														</tr>
														<tr>
															<th>Attrition related costs</th>
															<td>4009</td>
															<td>4361</td>
															<td>4356</td>
															<td>3505</td>
															<td>3366</td>
															<td>3197</td>
															<td>3081</td>
															<td>4094</td>
															<td>29969</td>
															<td>29969</td>
															
														</tr>
														<tr>
															<th>Relocation/Travel</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Travel/vacation</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Training & Recruitment Cost</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Onsite Direct Overheads</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Extra Shift working</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>CPC Charges</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Skill bay/ Contract</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Others</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Total Direct Cost</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Total Onsite Direct Cost</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Total Offshore Direct Cost</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<th>Billing Schedule for PM of 0%</th>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>						
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
					                	<label class="control-label col-sm-10 "><h5><b>Hourly Direct Cost</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblHourlyDirectCost">
													<thead>
														<tr>
															<th  class="width20per">Direct cost details</th>
															<th>May-17</th>
															<th>Jun-17</th>
															<th>Jul-17</th>
															<th>Aug-17</th>
															<th>Sep-17</th>
															<th>oct-17</th>
															<th>Nov-17</th>
															<th>Dec-17</th>
															<th>Total 2017</th>
															<th>Total</th>
														</tr>
													</thead>
													<tbody id="tBodyHourlyDirectCost" >
														<tr>
															<th>Offshore Direct cost / Hr</th>
															<td>85.68</td>
															<td>68.32</td>
															<td>78.76</td>
															<td>75.28</td>
															<td>75.28</td>
															<td>75.28</td>
															<td>65.93</td>
															<td>71.04</td>
															<td>74.86</td>
															<td>74.86</td>
														</tr>
														<tr>
															<th>Onsite Direct cost / Hr</th>
															<td>13.7</td>
															<td>14.38</td>
															<td>14.4</td>
															<td>14.53</td>
															<td>14.62</td>
															<td>14.81</td>
															<td>14.22</td>
															<td>14.5</td>
															<td>14.41</td>
															<td>14.41</td>
														</tr>
														<tr>
															<th>Total Direct Cost / Hr</th>
															<td>28.95</td>
															<td>21.97</td>
															<td>21.05</td>
															<td>18.79</td>
															<td>19.21</td>
															<td>19.89</td>
															<td>21.23</td>
															<td>25.52</td>
															<td>21.88</td>
															<td>21.88</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
					                	<label class="control-label col-sm-10 "><h5><b>Efforts(In hrs)</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblEfforts">
													<thead>
														<tr>
															<th  class="width20per">Direct cost details</th>
															<th>Percentage</th>
															<th>May-17</th>
															<th>Jun-17</th>
															<th>Jul-17</th>
															<th>Aug-17</th>
															<th>Sep-17</th>
															<th>oct-17</th>
															<th>Nov-17</th>
															<th>Dec-17</th>
															<th>Total 2017</th>
															<th>Total</th>
														</tr>
													</thead>
													<tbody id="tBodyEfforts" >
														<tr>
															<th>Onsite</th>
															<td>12.35</td>
															<td>866.8</td>
															<td>693.44</td>
															<td>520.08</td>
															<td>346.72</td>
															<td>346.72</td>
															<td>346.72</td>
															<td>520.08</td>
															<td>693.44</td>
															<td>4334</td>
															<td>4334</td>
														</tr>
														<tr>
															<th>Offshore</th>
															<td>87.65</td>
															<td>3223.5</td>
															<td>4236.6</td>
															<td>4512.9</td>
															<td>4605</td>
															<td>4236.6</td>
															<td>3776.1</td>
															<td>3315.6</td>
															<td>2864.31</td>
															<td>30770.61</td>
															<td>30770.61</td>
														</tr>
														<tr>
															<th>Total Efforts</th>
															<td>100</td>
															<td>4090.3</td>
															<td>4930.04</td>
															<td>5032.98</td>
															<td>4951.72</td>
															<td>4583.32</td>
															<td>4122.82</td>
															<td>3835.68</td>
															<td>3557.75</td>
															<td>35104.61</td>
															<td>35104.61</td>
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
	    	</form>
        </div>
    </div>
	<div id="footer" ng-include="'/RightPricePortal/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'/RightPricePortal/Portal/TopBottomNavigation.jsp'"></div>
</body>
</html>
