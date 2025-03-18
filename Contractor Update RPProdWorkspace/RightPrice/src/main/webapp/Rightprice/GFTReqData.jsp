<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
	<link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/GFTReqData.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$(".customDate").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true
		});
	});
</script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterAtosRCController" >
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
     <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div>
	      	<form class="form-inline" role="form" name="Master" id="Master" novalidate>
	      	
	         	<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Deal Details</label>
										</div>
									</div>
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive panel-body">
												<!-- class="clsTable table-striped table-bordered table-hover table-condensed " id ="tblViewVertical" --> 
				
											</div>
										</div>
									</div>

									<!--   Table to display data   -->
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace"
												id="btnDetailsSave" ng-disabled = 'dataCaptured'
												ng-click="exportRateCardData(Master,'#tableToExport');">
												<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow" > Export to Excel</button>
										</div>
									</div>
						<div class="divEmptyThrice"></div>
				<!-- ============================== download functionality ============================ -->
								</div>
							</div>
						</div>
					</div>
	         </form>
        </div>
			<div style="display:None"  id="tableToExport" >

				<table border="1"
					class="table clsTable table-striped table-bordered table-hover table-condensed "
					id="tblRateCardDetails2">
					<tbody id="tBodyRateCardDetails">
						<tr>
							<td colspan="20" align="Center" bgcolor="#CCFFFF"><Strong>Weekly Deal Report</Strong></td>
						</tr>
					</tbody>
				</table>

				<table></table>
				
												<table class ="table clsTable table-bordered table-striped table-hover table-condensed" border = "1" ng-hide="uploadHide">
													 <thead>
														<tr>
															<th align="left" bgcolor="#CCFFFF" >Vertical Name</th>
															<th align="left" bgcolor="#CCFFFF" >Customer Name</th>
															<th align="left" bgcolor="#CCFFFF" >Deal Id</th>
															<th  align="left" bgcolor="#CCFFFF">Rp Deal Version ID</th>
															<th align="left" bgcolor="#CCFFFF">Revenue</th>
															<th  align="left" bgcolor="#CCFFFF">Currency Name</th>
															<th align="left" bgcolor="#CCFFFF">Deal Type</th>
															<th align="left" bgcolor="#CCFFFF">Start Date<br>(mm/dd/yy)</th>
															<th align="left" bgcolor="#CCFFFF">End Date<br>(mm/dd/yy)</th>
															<th  align="left" bgcolor="#CCFFFF">Deal Status</th>
															<th align="left" bgcolor="#CCFFFF">Deal Stage</th>
															<th align="left" bgcolor="#CCFFFF">GM <br> pre - Discount</th>
															<th align="left" bgcolor="#CCFFFF">GM <br> post - Discount</th>
															<th align="left" bgcolor="#CCFFFF">Industry Type</th>
															<th align="left" bgcolor="#CCFFFF">Onsite Efforts</th>
															<th align="left" bgcolor="#CCFFFF">Offshore Efforts</th>
															<th align="left" bgcolor="#CCFFFF">AC <br> Percent</th>
															<th align="left" bgcolor="#CCFFFF">CH <br> Percent</th>
															<th align="left" bgcolor="#CCFFFF">ON <br> Percent</th>
															<th align="left" bgcolor="#CCFFFF">Off <br> Percent</th>	
															
														</tr>
														</thead>
													<tbody>
														<tr ng-repeat="row in DealData" id="{{'ResourceData'+'_'+($index+1)}}">
															<td>{{row.vertical_Name}}</td>
															<td>{{row.customer_name}}</td>
															<td>{{row.crm_DEAL_ID}}</td>
															<td>{{row.rp_Deal_Version_Id}}</td>
															<td>{{row.revenue}}</td>
															<td>{{row.currencyname}}</td>
															<td>{{row.deal_type}}</td>
															<td>{{row.deal_Start_Date}}</td>
															<td>{{row.deal_End_Date}}</td>
															<td>{{row.deal_status}}</td>
															<td>{{row.deal_Stage}}</td>
															<td>{{row.gm_Pre_Discount || 0}}</td>
															<td>{{row.gm_post_discount|| 0}}</td>
															<td>{{row.industry_Type}}</td>
															<td>{{row.onsite_efforts}}</td>
															<td>{{row.offshore_efforts}}</td>
															<td>{{row.ac_percent}}</td>
															<td>{{row.ch_percent}}</td>
															<td>{{row.on_percent}}</td>
															<td>{{row.off_percent}}</td>
														</tr>
													</tbody>
												</table>
				
			</div>
		</div>

								<div class="divEmptyThrice"></div>
     </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
