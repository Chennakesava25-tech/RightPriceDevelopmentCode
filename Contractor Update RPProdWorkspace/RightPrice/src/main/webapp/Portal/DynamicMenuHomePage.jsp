<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<title>Insurance Portal</title>
<meta charset="utf-8">
<meta name="csrf-token" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="${pageContext.request.contextPath}/UIPages/Support/CSS/bootstrap.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/UIPages/Support/CSS/SAPStyleSheet.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/UIPages/Support/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/UIPages/Support/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/UIPages/Support/js/jquery.serializeJSON.min.js"></script>
<link href="${pageContext.request.contextPath}/UIPages/Support/CSS/ie10-viewport-bug-workaround.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/UIPages/Support/CSS/sticky-footer-navbar.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/UIPages/Support/js/ie10-viewport-bug-workaround.js"></script>
<script src="${pageContext.request.contextPath}/UIPages/Support/js/AccessControl.js"></script>
 <script type="text/javascript">
	$.ajaxSetup({
		headers : {
			'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
		}
	});
	 
	$(document).ready(function() {
		var userRole =[];
		 userRole= "<%= org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities()%>";
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
 
</script>
</head>
<body>
<div>
	<div id="divHomePage">
		<div class="container-fluid">
			<div class="divEmptyThrice"></div>
			<div class="row">
				<div class="col-sm-8 divPaddingLeftZero">
				</div>
				<div class="col-sm-2"></div>
				<div class="col-sm-2">
					<!-- <label class="headerWelcomeLable headerTeextcolor">Welcome:</label> -->
					<label class="headerWelcomeLable headerTeextcolor" id="lblLoginUser"><%= org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%></label>
					<c:url var="logoutUrl" value="/logout" />
					<form action="${logoutUrl}" method="post" class="respLogOut">
						<button class="btn btn-default btn-xs" style="margin-left:5px;" type="submit" id="logoutBtn"><span class="glyphicon glyphicon-log-out"></span></button>
						<input type="hidden"
							name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="bgColor" id="divHeader">
		<div class="container">
			<div class="row" id="navBarDiv">
				<div class="col-sm-12 divPaddingLeftZero" id="parentNavBarDiv">
					<nav class="navbar navbar-default nav-justified" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse"
								data-target="#navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand navbarColor homeAnchorPadding" href="${pageContext.request.contextPath}/UIPages/Portal/HomePage.jsp">
							<span><img src="${pageContext.request.contextPath}/UIPages/Support/Images/home_white.png" 
									class="img-rounded headerMenuItemsImgSize"></span> Home</a>
						</div>
						<div class="collapse navbar-collapse" id="navbar-collapse-1">
							<ul class="nav navbar-nav">							
								<li class="02 dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img src="${pageContext.request.contextPath}/UIPages/Support/Images/fleet_white.png" 
									class="img-rounded headerMenuItemsImgSize"> Fleet Management <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li class="dropdown dropdown-submenu 02_01 collapse"><a href="#"
											class="dropdown-toggle" data-toggle="dropdown">Master
												Entry</a>
											<ul class="dropdown-menu paddingLeft">
												<li class="dropdown dropdown-submenu 02_01_01 collapse"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown">Car
														Details</a>
													<ul class="dropdown-menu paddingLeft">
														<li class="02_01_01_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/CarDetailsAdd.jsp">Add</a></li>
														<li class="02_01_01_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/CarDetailsView.jsp">View</a></li>
													</ul>
												</li>
												<li class="02_01_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/CarDocuments.jsp">Car Documents</a></li>
												<li class="02_01_03 dropdown dropdown-submenu collapse"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown">Driver Details</a>
													<ul class="dropdown-menu paddingLeft">
														<li class="02_01_03_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/DriverDetailsAdd.jsp">Add</a></li>
														<li class="02_01_03_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/DriverDetailsView.jsp">View</a></li>
													</ul>
												</li>
												<li class="02_01_04 dropdown dropdown-submenu collapse">
													<a href="#" class="dropdown-toggle" data-toggle="dropdown">Toll Cards Details</a>
													<ul class="dropdown-menu paddingLeft">
														<li class="02_01_04_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/TollCardDetailsAdd.jsp">Add</a></li>
														<li class="02_01_04_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/TollCardDetailsView.jsp">View</a></li>
													</ul>
												</li>
											</ul>
										</li>
										<li class="02_02 dropdown dropdown-submenu collapse"><a href="#"
											class="dropdown-toggle" data-toggle="dropdown">Transactions</a>
											<ul class="dropdown-menu paddingLeft">
												<li class="02_02_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/CarServiceDetails.jsp">Car Service Details</a></li>
												<li class="02_02_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/CarDocumentDetails.jsp">Car Documents Details</a></li>
												<li class="02_02_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/FuelConsumption.jsp">Fuel Consumption</a></li>
												<li class="02_02_04 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/TollParkingAndExpenses.jsp">Toll and Parking Expenses</a></li>
												<li class="02_02_05 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/DriverSalary.jsp">Driver Salary</a></li>
												<li class="02_02_06 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/TollCardLoadAmount.jsp">Toll Card - Load Amount</a></li>
											</ul>
										</li>
										<li class="02_03 dropdown dropdown-submenu collapse"><a href="#"
											class="dropdown-toggle" data-toggle="dropdown">Reports </a>
											<ul class="dropdown-menu paddingLeft">
												<li class="02_03_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/FuelConsumptionReport.jsp">Fuel Consumption </a></li>
												<li class="02_03_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/TollAndParkingReport.jsp">Toll and Parking Expenses </a></li>
												<li class="02_03_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/Fleet/RandMdashboardReport.jsp">R & M Dashboard </a></li>
											</ul>
										</li>
										<li class="02_04 collapse">
											<a href="${pageContext.request.contextPath}/UIPages/Fleet/BatchUtility.jsp">Batch Upload </a>
										</li>
									</ul>
								</li>
								<li class="03 dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img src="${pageContext.request.contextPath}/UIPages/Support/Images/clientVisit_white.png" 
									class="img-rounded headerMenuItemsImgSize">Client Visit <b class="caret"></b></a>
									<ul class="dropdown-menu paddingLeft">
										<li class="03_01 collapse">
											<a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/RequestForm.jsp">Raise Request</a>
										</li>
										<li class="03_02 collapse">
											<a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/RequestView.jsp">View Request</a>
										</li>
										<li class="03_03 collapse">
											<a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/RequestApprove.jsp">Approve Request</a>
										</li>
										<li id="liCVAdmin" class="03_04 dropdown dropdown-submenu collapse">
											<a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin Activity</a>
											<ul class="dropdown-menu paddingLeft">
												<li class="03_04_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/CVAdmin/RequestViewAdmin.jsp">Manage Request</a></li>
												<li class="03_04_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/CVAdmin/MIS_Report.jsp">MIS Report</a></li>
											</ul>
										</li>	
									</ul>
								</li>
								<li class="04 dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img src="${pageContext.request.contextPath}/UIPages/Support/Images/localTravel_white.png" 
									class="img-rounded headerMenuItemsImgSize">Local Travel <b class="caret"></b></a>
									<ul class="dropdown-menu paddingLeft">
										<li class="04_01 collapse">
											<a href="${pageContext.request.contextPath}/UIPages/TravelRequest/RaiseTravelRequest.jsp">Raise Request</a>
										</li>
										<li class="04_02 collapse">
											<a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TravelRequestView.jsp">View Request</a>
										</li>
										<li class="04_03 collapse">
											<a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TravelRequestApprove.jsp">Approve Request</a>
										</li>
										<li class="04_04 collapse dropdown dropdown-submenu "><a href="#"
											class="dropdown-toggle" data-toggle="dropdown">Admin Activity</a>
											<ul class="dropdown-menu paddingLeft">
												<li class="04_04_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TRAdmin/TravelReportQueue.jsp">Travel Request Queue </a></li>
												<li class="04_04_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TRAdmin/CarAssignment.jsp">Car Assignment </a></li>
												<li class="04_04_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TRAdmin/ChangeCarAssignment.jsp">Change Car Assignment </a></li>
												<li class="04_04_04 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TRAdmin/CancelTravelRequest.jsp">Cancel Travel Request </a></li>
												<li class="04_04_05 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TRAdmin/CompleteTravelRequest.jsp">Complete Travel Request </a></li>
												<li class="04_04_06 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TRAdmin/TravelUtilizationReport.jsp">Utilization Report </a></li>
												<li class="04_04_07 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TRAdmin/RequestAccess.jsp">Request Access</a></li>
											</ul>
										</li>
									</ul>
								</li>
								
								<li class="05 dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img src="${pageContext.request.contextPath}/UIPages/Support/Images/penstand_white.png" 
									class="img-rounded headerMenuItemsImgSize">Stationery <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li class="05_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/RaiseStationaryRequest.jsp">Raise Requests</a></li>
										<li class="05_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/StationaryViewRequest.jsp">View Request</a></li>
										<li class="05_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/StationaryApproveRequest.jsp">Approve Requests</a></li>
										<li class="05_04 collapse dropdown dropdown-submenu"><a href="#"
											class="dropdown-toggle" data-toggle="dropdown">Admin Activity</a>
											<ul class="dropdown-menu paddingLeft">
												<li class="05_04_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/StationaryRequestQueue.jsp">Request Queue</a></li>
												<li class="05_04_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/StationaryPOEntryScreen.jsp">PO Entry</a></li>
												<li class="05_04_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/StationaryStockUpdateScreen.jsp">Stock Update</a></li>
												<li class="05_04_04 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/StationaryStockAdjustment.jsp">Stock Adjustment</a></li>
												<li class="05_04_05 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/StationaryInterLocationTransfer.jsp">Interlocation Transfer</a></li>
												<li class="05_04_06 collapse dropdown dropdown-submenu"><a href="#"
													class="dropdown-toggle" data-toggle="dropdown">Reports</a>
													<ul class="dropdown-menu paddingLeft">
														<li class="05_04_06_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/ConsumptionReport.jsp">Consumption</a></li>
														<li class="05_04_06_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/DemandSupplyStatusReport.jsp">Demand Supply Status</a></li>
														<li class="05_04_06_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/PendingOrderQueReport.jsp">Pending Orders Queue</a></li>
														<li class="05_04_06_04 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/AdminStnry/PODetailsReport.jsp">PO Details</a></li>
													</ul>
												</li>
											</ul>
										</li>
									</ul>
								</li>
								<li class="06 collapse"><a href="#"><img src="${pageContext.request.contextPath}/UIPages/Support/Images/guestHouse_white.png" 
									class="img-rounded headerMenuItemsImgSize">Guest House</a>
								</li>
								<li class="07 collapse"><a href="#"><img src="${pageContext.request.contextPath}/UIPages/Support/Images/gatePass_white.png" 
									class="img-rounded headerMenuItemsImgSize">Gate Pass </a>
								</li>
								<li class="08 dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img src="${pageContext.request.contextPath}/UIPages/Support/Images/gatePass_white.png" 
									class="img-rounded headerMenuItemsImgSize">Access Control <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/UIPages/AccessControl/RoleMgmt.jsp">Role Management</a></li>
										<li><a href="${pageContext.request.contextPath}/UIPages/AccessControl/UserRoleMgmt.jsp">User Role Management</a></li>
										<li><a href="${pageContext.request.contextPath}/UIPages/AccessControl/MenuControl.jsp">Menu Control</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div>
	        <form class="form-inline" role="form" name="myForm" id="frmHomePage">
	            <div class="row divHomepageFirstRow">	            	
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">
	            			<img  src="${pageContext.request.contextPath}/UIPages/Support/Images/client_visit.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            				alt="Cinque Terre" data-toggle="">
	            									    
							    <ul id="ulCV" class="dropdown-menu ulHomePage">
							        <li class="03_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/RequestForm.jsp">Raise Request</a></li>
							        <li class="03_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/RequestView.jsp">View Request</a></li>
							        <li class="03_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/ClientVisitRequest/RequestApprove.jsp">Approve Request</a></li>
							    </ul>
						    <div class="divHomePageIconFont"><span class="homePageImgIconFont">Client Visit</span></div>   
						</div>
	            	</div>
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">	            			
	            			<img src="${pageContext.request.contextPath}/UIPages/Support/Images/local_travel.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="">
	            			<ul class="dropdown-menu ulHomePage">
						        <li class=" 04_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/RaiseTravelRequest.jsp">Raise Request</a></li>
						        <li class="04_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TravelRequestView.jsp">View Request</a></li>
						        <li class="04_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/TravelRequest/TravelRequestApprove.jsp">Approve Request</a></li>
						    </ul> 
	            			<div class="divHomePageIconFont"><span class="homePageImgIconFont">Local Travel</span></div>
	            		</div>
	            	</div>
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">	            			
	            			<img src="${pageContext.request.contextPath}/UIPages/Support/Images/stationary.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="">
	            			<ul class="dropdown-menu ulHomePage">
						        <li class="05_01 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/RaiseStationaryRequest.jsp">Raise Requests</a></li>
								<li class="05_02 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/StationaryViewRequest.jsp">View Request</a></li>
								<li class="05_03 collapse"><a href="${pageContext.request.contextPath}/UIPages/Stationary/StationaryApproveRequest.jsp">Approve Requests</a></li>
						    </ul>
	            			<div class="divHomePageIconFont"><span class="homePageImgIconFont">Stationary</span></div> 
	            		</div>
	            	</div>	            	
	            </div>
	            <div class="row divHomepageSecRow">
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">
	            			<img src="${pageContext.request.contextPath}/UIPages/Support/Images/guest_house.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="">
	            			<ul class="dropdown-menu ulHomePage">
						        <li><a href="JavaScript:void(0);">This is under construction</a></li>
						    </ul>
	            			<div class="divHomePageIconFont"><span class="homePageImgIconFont">Guest House</span></div> 
	            		</div>
	            	</div>
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">
	            			<img src="${pageContext.request.contextPath}/UIPages/Support/Images/gate_pass.png" class="img-rounded homePageImgSize dropdown-toggle" 
	            			alt="Cinque Terre" data-toggle="">
	            			<ul class="dropdown-menu ulHomePage">
						        <li><a href="JavaScript:void(0);">This is under construction</a></li>
						    </ul>
	            			<div class="divHomePageIconFont"><span class="homePageImgIconFont">Gate Pass</span></div> 
	            		</div>
	            	</div>
	            	<div class="col-sm-4 divParentImg">
	            		<div class="dropdown">
	            			<!-- <img src="${pageContext.request.contextPath}/UIPages/Support/Images/Syntel_Img.png" class="img-rounded" alt="Cinque Terre" 
	            			width="200px" height="200px"> --> 
	            		</div>
	            	</div>
	            </div>
	       	</form>
	  	</div>
  	</div>
</div>
<div id="footer"></div>
</body>
</html>
