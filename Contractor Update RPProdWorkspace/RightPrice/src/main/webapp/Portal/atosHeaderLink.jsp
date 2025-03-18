<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

 <link rel="icon" href="${contextPath}/resources/Images/favicon.ico"
	type="image/x-icon" />
	<script src="${contextPath}/resources/js/AccessControl.js"></script>

<script>

	$(document).ready(function() {
	var userRole =[];
	 userRole= "<%= org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities()%>";
	 accessControl(userRole);
	   if(userRole.length == 14)
		 {
		 	$('#parentHelpDiv').hide();
		 }
	 
	});

	function setSubmitDisabled(){
		var url = "/RightPrice-DAS/RightPrice-DAS/setSubmitDisabled";
	      $.ajax({
	            type : "GET",
	            url : url,
	            async: false,
	            contentType : "application/json; charset=utf-8",
	            dataType : "json",
	            success : function(data) {
					alert("inside method success");
	            },
	            failure : function(errMsg) {
	            }
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
				</label><label class="headerWelcomeLable headerTeextcolor" id="lblLoginUser"> <%= org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%></label>
				<c:url var="logoutUrl" value="/logout" />
				<form action="${logoutUrl}" method="post" class="respLogOut">
					<button class="btn btn-default" onClick="setSubmitDisabled();" type="submit" id="logoutBtn"><span class="glyphicon glyphicon-log-out"></span></button>
					<!-- <input type="submit" value="Log out" id="logoutBtn"/> --> 
					<input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
<div class="divEmptyThrice"></div>
	</div>
</div>
<div class="divEmpty"></div>
<%-- <div class="row" id="parentHelpDiv">
			<div class="col-sm-2 col-sm-offset-10">
				<a href="${contextPath}/resources/Documents/User_Manual_Right_Price_2.0.pdf" target="_blank">Help</a>
			</div>
		</div> --%>