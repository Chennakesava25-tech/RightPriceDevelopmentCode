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
<link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
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
<script src="${contextPath}/resources/js/angular-messages.js"></script>
<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
<script src="${contextPath}/resources/js/angular-messages.js"></script>
<script src="${contextPath}/resources/js/ngStorage.js"></script>
<script src="${contextPath}/resources/js/loader.js"></script>
<script src="${contextPath}/resources/js/RightPrice/RateCardContractorRole.js"></script>
<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
<script>
$(document).ready(function () {
	$('#tblRoleUtilization').on('click', 'a', function() {
	     $('#tblRoleUtilization').find('a').each(function() {
		 	$(this).removeClass('active');
		});
    	$(this).addClass('active');
	});
});

</script>
</head>
<body ng-app="RightPriceApp" ng-controller="RPContractorRole" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Rate Card Creation - Contractor Role</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="roleUtilizationAndRates" id="roleUtilizationAndRates" ng-submit="insertContractorRole();"  novalidate>
            	<div id="includedRateCardStages" ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
            	<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/RateCardCreationInformationTable.jsp'"></div>
				<div class="divEmptyThrice"></div>
				
				<div class="row marginBottom5px">
                 	<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                 	<div class="col-sm-3">
             			<select id="ddlCountry" class="form-control" placeholder="Please select" name="ddlCountry"
                         		ng-model="roleUtilizationAndRates.ddlCountryModel" ng-options="con.countryId as con.countryName for con in country" 
                         		ng-change="getCity(roleUtilizationAndRates.ddlCountryModel)" 
                         		ng-class="{true: 'ng-border'}[(onSearch && roleUtilizationAndRates.ddlCountryModel.$invalid)]" required>
								<option value="" selected disabled>Please select</option>
						</select>
						 <div class="error-messages" ng-if= "onSearch" ng-messages="roleUtilizationAndRates.ddlCountryModel.$error">
						 <em class="error help-block has-error" ng-message="required">Please select country</em>
						</div>
                	</div>	
                 	<label class="control-label col-sm-3 textAlignRight required-Field">City</label>
                  	<div class="col-sm-3">
                    	<select id="ddlCity" class="form-control" placeholder="Please select" name="ddlCity"
                          		ng-model="roleUtilizationAndRates.ddlCityModel" ng-options="ci.cityId as ci.cityName for ci in city" required>
								<option value="" selected disabled>Please select</option>
						</select>
                	</div>	
				</div>
				<div class="divEmptyThrice"></div>
				<div class="row text-center">
					<div class="col-sm-12">
						<button type="button" class="btn btn-primary btnSpace" id="btnSearch" ng-click="getRateUtilizationData(roleUtilizationAndRates.ddlCityModel);">Search</button>
					</div>
				</div>
				<br><br>
				<div class="divEmptyThrice"></div>
                <div class="row">
        			<div class="col-sm-12">
						<div class="table-responsive">
							<table 
								class="table tblRateCardCre table-striped table-hover table-borderless table-condensed "
								id="tblRoleUtilization">
								<thead class="">
									<tr>
										<!-- <th class="tdWidth5Per"></th>
										<th class="tdWidth5Per"></th>
										<th class="width12per"></th>
										<th class="width12per"></th>
										<th class="colRightSection" colspan="1"></th>
	                                    	<th class="colRightSection" colspan="{{colspanArray[$index]}}"  ng-repeat = "year in yearList"><a class="btn btn-default" href="#" tabindex="1" ng-click="yearData(year[0])">{{year[1] | limitTo:(year[1].length - (year[1].indexOf(","))-1)}}<br>{{year[1].substring(year[1].indexOf(","),year[1].length)| headerFilter : "," : " - "}}</a></th>
	                                   		<th class="colRightSection" colspan="5" ng-hide="yearList"<a class="btn btn-default" href="#" tabindex="2">Years</a>></th> -->
	                                   		<!-- <th class="colRightSection" colspan="4" ng-hide="yearList"><a class="btn btn-default" href="#" tabindex="3">2019</a></th>
	                                    	<th class="colRightSection" colspan="4" ng-hide="yearList"><a class="btn btn-default" href="#" tabindex="4">2020</a></th>
	                                    	<th class="colRightSection" colspan="2" ng-hide="yearList"><a class="btn btn-default" href="#" tabindex="5">2021</a></th> -->
									</tr>
									<tr class="sectionHeading">
									    <th colspan="3"class="headerBgColor tdRightBorder"></th>
									    <th colspan="2" class="headerBgColorDarkGray tdAlignCenter tdRightBorder">Utilization Onsite</th>
									    <th colspan="2"class="headerBgColor tdAlignCenter">Utilization Offshore</th>
									</tr>
								</thead>
								<tbody>
									<tr class="tlbHeader">
<!-- 										<th class="firstColLeftAlign rcRoleDesc ">Master Role Code</th>Role Utilization and Rates -->
										<th class="firstColLeftAlign rcRoleDesc ">Role Description</th><!-- Role Utilization and Rates -->
										<th class="tdCommonRightSec">Band Grade</th>
										<th class="tdCustRole tdRightBorder">Customer Role</th>
										<th class="tdCommonRightSec">Onsite Rate/Hour</th>
										<th class="tdCommonRightSec tdRightBorder">Onsite Cost/Hour</th>
										<th class="tdCommonRightSec">Offshore Rate/Hour</th>
										<th class="tdCommonRightSec">Offshore Cost/Hour</th>
									</tr>
								</tbody>
								<tbody id="tBodyRoleUtilization" >
									<tr ng-repeat="rateData in tableDetails">
<!-- 										<td class="grayBgColor colSection firstColLeftAlign tooltip1 tdWordBreak">{{rateData.RoleDesc}} -->
<!-- 										 <span class="tooltiptext">{{rateData.RoleDescLong}}</span> -->
<!-- 										</td> -->
										<td class="grayBgColor colSection firstColLeftAlign tooltip1 tdWordBreak">{{rateData.RoleDesc}}
										 <span class="tooltiptext">{{rateData.RoleDescLong}}</span>
										</td>
										<td class="grayBgColor colSection tdWordBreak">{{rateData.BandGrade}}</td>
										<td class="grayBgColor colSection tdRightBorder tdWordBreak">{{rateData.CustRole}}</td>
										
										<td class="colRightSection tableInputColor colSection"><input ng-model="rateData.onsiteRateHour" name="{{'txtOnsiteMasRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOnsiteMasRate'+'_'+($index+1)}}"  decimals="2"></td>
										
										<td class="colRightSection tableInputColor tdRightBorder colSection"><input ng-model="rateData.onsiteCostHour" name="{{'txtOnsiteMasterRateGM'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOnsiteMasterRateGM'+'_'+($index+1)}}"  decimals="2"></td>
										
										<td class="colRightSection tableInputColor colSection"><input ng-model="rateData.offshoreRateHour" name="{{'txtOffshoreMasRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreMasRate'+'_'+($index+1)}}" decimals = "2"></td>
										
										<td class="colRightSection tableInputColor"><input ng-model="rateData.offshoreCostHour" name="{{'txtOffshoreMasterRateGM'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreMasterRateGM'+'_'+($index+1)}}" decimals = "2"></td>
										
									</tr>
								</tbody>
								
							</table>
						</div>
					</div>
				</div>
				
				<div class="divEmptyThrice"></div>
				<div class="row text-center">
					<div class="col-sm-12">
					<!-- <button type="button" class="btn btn-primary btnSpace" id="btnCalculate">Calculate</button> -->
						<button type="submit" class="btn btn-primary btnSpace" id="btnSave" ng-disabled="isDisabled" >Save</button>	
						<button type="button" class="btn btn-danger btnSpace" id="btnCancel">Cancel</button>	
						<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
						<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
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
