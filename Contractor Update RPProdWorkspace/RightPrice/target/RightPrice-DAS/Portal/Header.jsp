<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<link rel="icon" href="${contextPath}/resources/Images/favicon.ico" type="image/x-icon" />
<script src="${contextPath}/resources/js/AccessControl.js"></script>
<script src="${contextPath}/resources/js/jquery.min.js"></script>
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

<script>

	$(document).ready(function() {
	var userRole =[];
	 userRole= "<%= session.getAttribute("roleUser")%>";
<%-- 	 userId= "<%=session.getAttribute("username")%>"; --%>
<%-- 	 userRole= "<%= org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities()%>"; --%>
	 accessControl(userRole);
// 	 getUserName(userId);
	   if(userRole.length == 14)
		 {
		   alert("checks")
		 	$('#parentHelpDiv').hide();
		 }
	});

	function setSubmitDisabled(){
		sessionStorage.removeItem("proxyArray");
		sessionStorage.removeItem("ngStorage-proxyupdaterId");
		sessionStorage.removeItem("ngStorage-proxyId");
		sessionStorage.removeItem("proxyIdVal");
/* 			var url = "/RightPrice-DAS/RightPrice-DAS/setSubmitDisabled";
		      $.ajax({
		            type : "GET",
		            url : url,
		            async: false,
		            contentType : "application/json; charset=utf-8",
		            dataType : "json",
		            success : function(data) {
						//alert("inside method success");
		            },
		            failure : function(errMsg) {
		            }
		      }); */
		}
		function SuggestionPageApp() 
		{
		var xhr = new XMLHttpRequest();
		$.support.cors = true;
		 var JSONObject= 
		  {
		  "userID":"<%=session.getAttribute("user")%>",
		  "appName": "RPP"
		  };
	$.ajax({
	    url: 'https://admin-uat.myatos-syntel.net/SuggestionPageApp/rest/empdetails/',
	    type: 'POST',
	    headers: {'Content-Type':'application/json'},
	    data: JSON.stringify(JSONObject),
	    dataType: "json",
	xhrFields: {
	withCredentials: true
	},
	crossDomain: true,
				success : function(response) {
	//					alert("Success");
					var obj = JSON.stringify(response);
					var json = JSON.parse(obj);
					console.log("Flag : "+json.userID);
					var base64key = "QmFyMTIzNDVCYXIxMjM0NQ==";
	    			var parsedBase64Key = CryptoJS.enc.Base64
	    					.parse(base64key);
	    			var ive = CryptoJS.enc.Utf8
	    					.parse('RandomInitVector');
	    			var encrypted = CryptoJS.AES.decrypt(
	    					json.userID, parsedBase64Key, {
	    						iv : ive
	    					});
	    			
	    			var decryptedText = encrypted
	    					.toString(CryptoJS.enc.Utf8);
	    			user=decryptedText;	
					 window.open("https://admin-uat.myatos-syntel.net/SuggestionPageApp/UserDetails?userid="+user+"&app="+json.appName+"");
					},
				failure : function(errMsg) {
				alert(errMsg);
			},
			});
	}

</script> 


<div class="bgColor" id="divHeader">
	<div class="container">
		<div class="divEmptyThrice"></div>
		<div class="row">
			<div class="col-sm-8 divPaddingLeftZero">
				<h1 class="marginTopAuto headerTeextcolor">Eviden RightPrice Portal </h1>
			</div>
			<div class="col-sm-2"></div>
			<div class="col-sm-2">
				<label class="headerWelcomeLable headerTeextcolor">Welcome:
				</label><label class="headerWelcomeLable headerTeextcolor" id="lblLoginUser"><%=session.getAttribute("firstname")%> <%=session.getAttribute("surname")%></label><%-- <%= org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%> --%>
				<c:url var="logoutUrl" value="/logout" />
				<form action="${logoutUrl}" method="post" class="respLogOut">
					<button class="btn btn-default" type="submit" onClick="setSubmitDisabled();" id="logoutBtn"><span class="glyphicon glyphicon-log-out"></span></button>
					<!-- <input type="submit" value="Log out" id="logoutBtn"/> --> 
					<input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<div class="row" id="navBarDiv">
			<div class="col-sm-12 divPaddingLeftZero" id="parentNavBarDiv">
				<nav class="navbar navbar-default nav-justified" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand navbarColor homeAnchorPadding" href="${contextPath}/welcome"><span><img src="${contextPath}/resources/Images/home_white.png" 
									class="img-rounded headerMenuItemsImgSize"></span> Home</a>
					</div>
					<div class="collapse navbar-collapse" id="navbar-collapse-1">
						<ul class="nav navbar-nav">	
							<li class="02 dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown">
								<img src="${contextPath}/resources/Images/dashboard_White.png" 
								class="img-rounded headerMenuItemsImgSize"> Dashboard <b class="caret"></b></a>
								<ul class="dropdown-menu">
								<li class="02_01 collapse">
									<a href="${contextPath}/MyDashBoard" > MyDashboard</a>
								</li>	
								<li class="02_02 collapse">
									<a href="${contextPath}/RiskManagersDashBoard">RiskManagersDashboard</a>
								</li>
								</ul>
							</li>
							<li class="03 dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown">
								<img src="${contextPath}/resources/Images/masters_White.png" 
									class="img-rounded headerMenuItemsImgSize">Masters <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li class="03_01 collapse">
										<a href="${contextPath}/MasterAllowances">Allowances</a>
									</li>									
									<li class="03_02 collapse">
										<a href="${contextPath}/MasterCampusHire">Campus Hire</a>
									</li>
									<li class="03_03 collapse">
										<a href="${contextPath}/MasterCity">City</a>
									</li>
									<li class="03_04 collapse">
										<a href="${contextPath}/MasterCommonCostParameters">Common Cost parameter</a>
									</li>
									<li class="03_05 collapse">
										<a href="${contextPath}/MasterCountryForex">Country Forex</a>
									</li>
									<li class="03_06 collapse">
										<a href="${contextPath}/MasterCountryVisa">Country Visa</a>
									</li>
									<%-- <li class="03_07 collapse">
										<a href="${contextPath}/MasterDeliveryTeam">Delivery Team</a>
									</li> --%>
									<li class="03_07 collapse">
										<a href="${contextPath}/MasterAssumptionsParameters">Assumption Parameters</a>
									</li>
									<%-- <li class="03_08 collapse">
										<a href="${contextPath}/MasterRightPriceAudit">Right Price - Audit</a>
									</li> --%>
									<li class="03_08 collapse">
										<a href="${contextPath}/MasterRightPriceRate">Right Price Rate</a>
									</li>
									<li class="03_09 collapse">
										<a href="${contextPath}/MasterRoles">Roles</a>
									</li>
									<li class="03_10 collapse">
										<a href="${contextPath}/MasterSalary">Salary</a>
									</li>
									<li class="03_11 collapse">
										<a href="${contextPath}/MastersDesignation">Designation</a>
									</li>
									<li class="03_12 collapse">
										<a href="${contextPath}/MastersLOB">LOB</a>
									</li>
									<li class="03_13 collapse">
										<a href="${contextPath}/MastersMiscellaneousCost">Shift Cost</a>
									</li>
									<li class="03_14 collapse">
										<a href="${contextPath}/MastersSubPractice">Sub Practice</a>
									</li>
									<%-- <li class="03_15 collapse">
										<a href="${contextPath}/MastersVertical">Vertical</a>
									</li> --%>
									<li class="03_15 collapse">
										<a href="${contextPath}/MasterTaxParameters">Tax Parameters</a>
									</li>
									<li class="03_16 collapse">
										<a href="${contextPath}/MasterRateCard">Rate Card</a>
									</li>
									<li class="03_17 collapse">
										<a href="${contextPath}/MasterCustomerUpdate">Customer Update</a>
									</li>
									<%-- <li>
										<a href="${contextPath}/MasterAtosRC">Atos Rate Cards</a>
									</li> --%>
									<li class="03_18 collapse">
										<a href="${contextPath}/MastersPractice">Practice</a>
									</li>
								</ul>
							</li>
							<%-- <li ><a href="/RightPrice-DAS/Lookup">
								<img src="${contextPath}/resources/Images/lookup_white.png" 
									class="img-rounded headerMenuItemsImgSize">Look ups</b></a>
							</li> --%>
							
							<li class="04 dropdown">
								<a href="${contextPath}/RateCardCreationDetails">
									<img src="${contextPath}/resources/Images/rateCard_white.png" 
									class="img-rounded headerMenuItemsImgSize">Rate Card</a>
							</li>
							<li class="05 dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown">
								<img src="${contextPath}/resources/Images/deal_white.png" 
									class="img-rounded headerMenuItemsImgSize">Deal <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li class="05_01 collapse">
										<a href="${contextPath}/TMDealCreationDetails">T&M</a>
									</li>
									<li class="05_02 collapse">
										<a href="${contextPath}/FPDealCreationDetails">Fixed Price</a>
									</li>
								</ul>
							</li>
							<li class="06 dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown">
								<img src="${contextPath}/resources/Images/masters_White.png" 
									class="img-rounded headerMenuItemsImgSize">Others <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li class="06_01 collapse">
										<a href="${contextPath}/MasterDeliveryTeam">Delivery Team</a>
									</li>
									<li class="06_02 collapse">
										<a href="${contextPath}/MastersVertical">Vertical</a>
									</li>
									<li class="06_03 collapse">
										<a href="${contextPath}/ResourceForcast">Resource Forecast</a>
									</li>
									
								</ul>
							</li>
							<li class="07 dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <img
									src="${contextPath}/resources/Images/dashboard_White.png"
									class="img-rounded headerMenuItemsImgSize">Access<b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li class="07_01 collapse"><a
										href="${contextPath}/RPAccessControl"> Customer Access </a></li>
									 <li class="07_02 collapse">
									 <a href="${contextPath}/RoleMngmt"> Application Access</a></li> 
								</ul></li>
							<li class="proxy_08">
								<a href="${contextPath}/DeligateUserAccess">
								<img src="${contextPath}/resources/Images/profileSelection.png" 
								class="img-rounded headerMenuItemsImgSize">Profile Selection</a>
							</li>
							
								<li class="09 dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown">
								<img src="${contextPath}/resources/Images/masters_White.png" 
									class="img-rounded headerMenuItemsImgSize">Reports <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<%-- <li><a href="${contextPath}/ActiveRateCard">Active Rate Card</a></li> --%>
									<li class="09_01 collapse"><a href="${contextPath}/MasterAtosRC">Eviden Rate Cards</a></li>
									<li class=""><a href="${contextPath}/GFTReqData">All Deal Details</a></li>
									<li class=""><a href="${contextPath}/Wonreports">Weekly FP won deals</a></li> 
								</ul>
							</li>
								<%-- <li class="09 dropdown">
								<a href="${contextPath}/RCGFTUpload">
								<img src="${contextPath}/resources/Images/dashboard_White.png" 
								class="img-rounded headerMenuItemsImgSize">Upload Rate Card Excel</a>
							</li> --%>
							
							<li class="10 dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown">
								<img src="${contextPath}/resources/Images/doc_white.png" 
									class="img-rounded headerMenuItemsImgSize">Documents <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li class="10_01 collapse"><a href="${contextPath}/Document">Process Documents </a></li>
								</ul>
							</li>
							
						<%-- 	<li class="11 dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown">
								<img src="${contextPath}/resources/Images/doc_white.png" 
									class="img-rounded headerMenuItemsImgSize">Pre Sales<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li class="11_01 collapse"><a href="${contextPath}/PreSalesPortal"> Add</a></li>
									<li class="11_02 collapse"><a href="${contextPath}/PreSalesView"> View</a></li>
									<li class="11_03 collapse"><a href="${contextPath}/PreSalesApprove"> Approve</a></li>
									<li class="11_04 collapse"><a href="${contextPath}/PreSalesApprovedReport"> Report</a></li>
								</ul>
							</li> --%>
						<%-- 	<li class="11 dropdown">
								<a href="https://rightprice.myatos-syntel.net/CommittedRevenue/">
									<img src="${contextPath}/resources/Images/rateCard_white.png" 
									class="img-rounded headerMenuItemsImgSize">Committed Revenue</a>
							</li> --%>
							
							<li class="12 dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <img
									src="${contextPath}/resources/Images/masters_White.png"
									class="img-rounded headerMenuItemsImgSize">Repository <b
									class="caret"></b></a>
								<ul class="dropdown-menu">
								<li class="12_01 collapse"><a
										href="${contextPath}/RateCardCreationDetails_New">Rate
											Cards </a></li>
									<li class="12_02 collapse"><a
										href="${contextPath}/DealCreationDetails_RP">Deals</a></li>
									<li class="12_03 collapse"><a
										href="${contextPath}/RPviewDetails">View</a></li>
								</ul>
								</li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
 	<div class="divEmptyThrice"></div>
	<!--				<div class="row">
					<div class="row marginBottom5px">
<marquee style="font-family:Book Antiqua; color: #FFFFFF ; font-size:18px; text-align:center; padding-top: 15px;
            padding-bottom: 15px;font-weight:bold; "

 bgcolor="#e35113" scrolldelay="200" 
   behavior=scroll>
For any issue and query related to RightPrice please create ticket on Pisa portal.You will find Link to Pisa Portal on 
PISA >Home 


</marquee>
</div>

</div> 
</div> -->

<div style="display: inline-flex; background:#2979ff none repeat scroll 0 0; height: 35px; width:100%; margin-bottom:2px;">
                <div style="width: 10%; font-size: 14px; color:#fff; background-color: #DD1274; font-weight:bold; padding: 8px 12px;">LATEST NEWS</div>
                <div style="font-size: 16px; padding: 6px 0px; width:90%; color:#fff; background-color: green; ">
					<marquee scrollamount="3" scrolldelay="1" onmouseover="this.stop()" onmouseout="this.start()" direction="left">
                    <ul>	
                    <li style="display: inline-block; color: #fff;">
							&nbsp;&nbsp;&nbsp;<span style="color:#fff; font-weight: bold;"><blink  class = 'blink'>1 .</blink></span>&nbsp;<span></span>&nbsp;&nbsp;
							<a style="color: #fff; font-weight: bold; text-decoration: none; font-size: 20px; line-height: 16px;/">
							<blink class = 'blink'>
							 RightPrice approver details are coming based upon RBU for industry IT only. &nbsp;&nbsp;</blink>
							<img src="${contextPath}/resources/Images/new.gif" style="vertical-align: baseline;" /> 	       
							</a>
						  </li> 	
						  					  <li style="display: inline-block; color: #fff;">
							&nbsp;&nbsp;&nbsp;<span style="color:#fff; font-weight: bold;">2 .</span>&nbsp;<span></span>&nbsp;&nbsp;
							<a style="color: #fff; font-weight: bold; text-decoration: none; font-size: 20px; line-height: 16px;/">Salesforce to RightPrice conditions has been updated. Please find the new conditions in RightPrice Home Page.  &nbsp;&nbsp;
							<img src="${contextPath}/resources/Images/new.gif" style="vertical-align: baseline;" /> 	       
							</a>
						  </li>		      
						<li style="display: inline-block; color: #fff;">        	        
					&nbsp;&nbsp;&nbsp;<span style="color:#fff; font-weight: bold;">3 .</span>&nbsp;<span></span>&nbsp;&nbsp;    
					<b>For any issue and query related to RightPrice please create ticket on PISA portal. You will find Link to PISA Portal on PISA Home ---->IT ---->Specialist Systems ---> RightPrice. Kindly write mail to @RightPrice2.0_Support. For prompt response
					</b>	 
					<img src="${contextPath}/resources/Images/new.gif" style="vertical-align: baseline;" /> 	       
					</li> 
										 
						  </ul>            

						  </marquee>
                </div>
            </div>

<div class="divEmpty"></div>
<div class="container">
	<div class="row" id="parentHelpDiv">
		<div class="col-sm-2 col-sm-offset-10">
			<a href="${contextPath}/resources/Documents/User_Manual_Right_Price_2.0.pdf" target="_blank">Help</a>
		</div>
		<!-- <div class="col-sm-2 col-sm-offset-10">
			<a href="javascript:SuggestionPageApp();">Suggestion</a>
		</div> -->
	</div>
</div>