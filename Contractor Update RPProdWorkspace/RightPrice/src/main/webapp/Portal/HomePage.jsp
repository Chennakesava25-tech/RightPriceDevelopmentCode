<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>Eviden Right Price</title>
<meta charset="utf-8">
<meta name="csrf-token" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/SAPStyleSheet.css"
	rel="stylesheet" />
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
<link
	href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/sticky-footer-navbar.css"
	rel="stylesheet" />
<script
	src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
<script src="${contextPath}/resources/js/angular.js"></script>
<script src="${contextPath}/resources/js/AccessControl.js"></script>
<script type="text/javascript">
	$.ajaxSetup({
		headers : {
			'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
		}
	});
	$(document).ready(function() {
		var userRole =[];
		 <%-- userRole= "<%= org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities()%>"; --%>
		 userRole= "<%=session.getAttribute("userRole")%>";
		accessControl(userRole);
		
		$('#frmHomePage img').click(function(){
			 //alert($(this)[0].src);
			 var imgCurrent = $(this); 
			 (imgCurrent.parent('div').find('li')).each(function(){
			    //alert($(this).text()); //log every element found to console output
			    if($(this).css('display') == 'list-item')
			    {
			    	//alert("Right Choice: Parent " + $(this).parent().parent().id + "Child Visible item" + $(this).text());
			    	imgCurrent.attr("data-toggle", "dropdown");
			    }  
			}); 
		});  
	});
	var app = angular.module("myapp", []);
	app.controller("HomePageController", ['$scope','$http','$filter','$window', function($scope,$http,$filter,$window,$index) {
		console.log("inside controller");
		}]);
</script>
</head>
<body ng-app="myapp" ng-controller="HomePageController">
	<div>
		<div id="includedHeader"
			ng-include="'${contextPath}/Portal/Header.jsp'"></div>
		<div class="container">
			<div>
				<fieldset ng-disabled="loading || showLoader">
					<div class="container">
						<div class="divEmpty"></div>
						<div class="row marginBottom5px"></div>
						<div>
							<form>
								<div class="row">
									<div class="col-md-18">
										<div class="card">
											<div class="card-header panelHeadingStyle"
												style="font-size: 18px; padding-top: 16px; padding-bottom: 16px">
												<b>RP Note:</b>
											</div>

											<div class="card-body">
												<div>
													<div class="divEmptyThrice"></div>
													<label class="control-label col-sm-12 textAlignLeft"
														style="font-size: 18px">The Opportunity IDs in
														this portal are available for selection only if following
														values in Salesforce portal matches. </label>


													<div class="divEmptyThrice"></div>

													<div class="divEmptyThrice"></div>

													<ul style="list-style-type: square">
														<!-- <li class="control-label col-sm-10 textAlignLeft" style="font-size: 18px" > Contracting Branch (from Leading Profit Centre) should be<b> Syntel </b></li> -->

														<li class="control-label col-sm-10 textAlignLeft"
															style="font-size: 18px">Opportunity Phase should be
															other than <b> PROSPECTING</b>
														</li>
														<li class="control-label col-sm-10 textAlignLeft"
															style="font-size: 18px">Order Entry Value should be
															greater than <b> 0</b>
														</li>
														<li class="control-label col-sm-10 textAlignLeft"
															style="font-size: 18px">Relevant for pipeline
															should be<b> YES</b>
														</li>
														<li class="control-label col-sm-10 textAlignLeft"
															style="font-size: 18px">Opportunity Status should<b>
																not be equal to Error/Lost/Dropped/Unqualified.</b></li>

														<!-- 	<li class="control-label col-sm-10 textAlignLeft" style="font-size: 18px" >Item Portfolio should be <b>AMS, DAA, SAP, IOT, CXE</b></li> -->
														<li class="control-label col-sm-10 textAlignLeft"
															style="font-size: 18px">Closing Date should be
															greater than <b>One month from the Current date </b> <br>(Example:(CurrentDate:01/12/2022
															then closing date should be >2/11/2022))
														</li>
														<!--  <li class="control-label col-sm-10 textAlignLeft" style="font-size: 18px" > At the level Opportunity <b> Item Profit Center</b> should be <b>  Syntel</b> </li> -->
														<!-- <li class="control-label col-sm-10 textAlignLeft"
															style="font-size: 18px">Local product/service class
															Should be <b> SYNTEL</b>
														</li> -->
														<li class="control-label col-sm-10 textAlignLeft"
															style="font-size: 18px">At opportunity  level  Product  item 
															profit center should have below <b>  legal entity
																name/code </b>opportunity--->Product--->ItemProfit center
														</li>
													</ul>
													<div class="divEmptyThrice"></div>

												</div>

												<div class="divEmptyThrice"></div>

											</div>
										</div>
									</div>
							</form>
						</div>
					</div>
				</fieldset>
				<div class="divEmptyThrice"></div>
				<div class="panel-body">
					<div class="row marginBottom4px">
						<div class="col-sm-8">
							<div class="table-responsive  ">
								<table
									class="table clsTable table-striped table-bordered table-hover table-condensed "
									id="tblDashBoard">
									<thead>
										<tr>
											<th class="width10per">Sr.No</th>
											<th class="width10per">Legal Entity</th>
											<th class="width10per">Legal Entity Code or Nessie
												Company Code ending on</th>

										</tr>
									</thead>
									<tbody id="tBodyDashBoardr">

										<tr>
											<td>1</td>
											<td>Syntel Inc.(Eviden)</td>
											<td>099</td>
										</tr>
										<tr>
											<td>2</td>
											<td>StateStreet Syntel Ser Pvt Lt</td>
											<td>127</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Syntel Australia</td>
											<td>148</td>
										</tr>
										<tr>
											<td>4</td>
											<td>Syntel Canada Business Unit</td>
											<td>141</td>
										</tr>
										<tr>
											<td>5</td>
											<td>Syntel Europe Limited</td>
											<td>106</td>
										</tr>
										<tr>
											<td>6</td>
											<td>Syntel Global Pvt Ltd</td>
											<td>136</td>
										</tr>
										<tr>
											<td>7</td>
											<td>Syntel Hongkong Ltd</td>
											<td>147</td>
										</tr>
										<tr>
											<td>8</td>
											<td>Syntel Infotech, Inc</td>
											<td>131</td>
										</tr>
										<tr>
											<td>9</td>
											<td>Syntel Private Limited</td>
											<td>115</td>
										</tr>
										<td>10</td>
										<td>Syntel Service Private Limited</td>
										<td>122</td>
										<tr>
											<td>11</td>
											<td>Syntel Singapore</td>
											<td>130</td>

										</tr>

										<tr>
											<td>12</td>
											<td>Syntel Netherland</td>
											<td>109</td>




										</tr>
										<tr>
											<td>13</td>
											<td>SYNTEL LLC</td>
											<td>105</td>
										</tr>
										<tr>
											<td>14</td>
											<td>Syntel Delaware LLC</td>
											<td>125</td>
										</tr>
										<tr>
											<td>15</td>
											<td>Syntel Solutions Mexico S. de. R.L. de C.V.</td>
											<td>142</td>
										</tr>
										<tr>
											<td>16</td>
											<td>Syntel Spc Inc</td>
											<td>149</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<%-- 
	        <form class="form-inline" role="form" name="myForm" id="frmHomePage">

	        
	            <div class="row divHomepageFirstRow">
	            	<div class="col-sm-4 divParentImg">
	            		<div class="02 dropdown">
	            			<img src="${contextPath}/resources/Images/dashboard.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            				alt="Cinque Terre" data-toggle="dropdown">
	            				<ul class="dropdown-menu ulHomePage">
									<li><a href="${contextPath}/MyDashBoard">My Dashboard</a></li>
							    </ul>					    
						    <div class="divHomePageIconFont"><span class="homePageImgIconFont">Dashboard</span></div>   
						</div>
	            	</div>
	            	<div class="col-sm-4 divParentImg">
	            		<div class="04 dropdown">
	            			<img src="${contextPath}/resources/Images/rateCard.png"" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="dropdown">
	            			<ul class="dropdown-menu ulHomePage">
						         <li><a href="${contextPath}/RightPrice-DAS/RateCardCreationDetails">Create Rate Card</a></li>
						    </ul>
	            			<div class="divHomePageIconFont"><span class="homePageImgIconFont">Rate Card</span></div> 
	            		</div>
	            	</div>
	            	<div class="col-sm-4 divParentImg">	            		
						<div class="05 dropdown">
	            			<img src="${contextPath}/resources/Images/deal.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="dropdown">
	            			<ul class="dropdown-menu ulHomePage">
						        <li><a href="${contextPath}/RightPrice-DAS/TMDealCreationDetails">T&M</a></li>
						        <li><a href="${contextPath}/RightPrice-DAS/FPDealCreationDetails">Fix Price</a></li>
						    </ul>
	            			<div class="divHomePageIconFont"><span class="homePageImgIconFont">Deal</span></div> 
	            		</div>
	            	</div> 
	            	<div class="col-sm-4 divParentImg">	            		
						<div class="06 dropdown">
	            			<img src="${contextPath}/resources/Images/deal.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="dropdown">
	            			<ul class="dropdown-menu ulHomePage">
						        <li><a href="${contextPath}/RightPrice-DAS/RCGFTUpload.jsp">Upload Rate Card Excel</a></li>
						    </ul>
	            		</div>
	            	</div> 
	            		<div class="col-sm-4 divParentImg">
	            		<div class="08 dropdown">
	            			<img src="${contextPath}/resources/Images/rateCard.png"" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="dropdown">
	            			<ul class="dropdown-menu ulHomePage">
						         <li><a href="${contextPath}/RightPrice-DAS/ResourceForcast.jsp">Resource Forecast</a></li>
						    </ul>
						    <div class="divHomePageIconFont"><span class="homePageImgIconFont">Resource Forecast</span></div> 
	            		</div>
	            	</div>
	            	  	
	            </div>
	            <div class="row divHomepageSecRow">	            	
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">
	            			<img src="${contextPath}/resources/Images/masters.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            				alt="Cinque Terre" data-toggle="dropdown"  >						    
							    <ul class="dropdown-menu ulHomePage">
									<li><a href="${contextPath}/RightPrice-DAS/MasterTaxParameters.jsp">Master Tax Parameters</a></li>
							    </ul>
						    <div class="divHomePageIconFont"><span class="homePageImgIconFont">Masters</span></div>   
						</div>	            		
	            	</div>
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">
	            		</div>
	            	</div>
	            </div>
	            
	           <div class="row divHomepageSecRow">	            	
	            	<div class="col-sm-4 divParentImg">
	            		<div class="10 dropdown">
	            			<img src="${contextPath}/resources/Images/doc.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            				alt="Cinque Terre" data-toggle="dropdown"  >						    
							    <ul class="dropdown-menu ulHomePage">
									<li><a href="${contextPath}/RightPrice-DAS/Document">Process Documents</a></li>
							    </ul>
						    <div class="divHomePageIconFont"><span class="homePageImgIconFont">Documents</span></div>   
						</div>	            		
	            	</div>
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">
	            		</div>
	            	</div>
	            </div> 
	            
	       	</form> --%>
				</div>
			</div>
		</div>
		<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
</body>
</html>
