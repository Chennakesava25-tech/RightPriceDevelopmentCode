<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/SAPStyleSheet.css" rel="stylesheet" />

   <%--  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head> --%>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
 	<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/SAPStyleSheet.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.js"></script>
	<script src="${contextPath}/resources/js/jquery.jcryption.3.0.1.js"></script>
	<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
	<script src="${contextPath}/resources/js/jquery-ui.js"></script>
	<link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/bootstrap-dialog.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
	<script src="${contextPath}/resources/js/jquery.validate.js"></script>
	<script src="${contextPath}/resources/js/jqueryValidations.js"></script>
	
	<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
	<script>
	
$(document).ready(function() {
		
		$.getScript("${contextPath}/resources/js/UserToken.js", function(data, textStatus, jqxhr) {
		});
		
		$("#buttonId").click(function() {
				validateForm();
		});
	
	function validateForm(){
	$('#formID').validate ({
		onfocusout : false,
			invalidHandler : function(form,validator) {
				var errors = validator.numberOfInvalids();
				if (errors) {
					validator.errorList[0].element.focus();
				}
			},
				rules : {
					username : {
						required : true,
						maxlength : 10
					},
					password : {
						required : true,
						maxlength : 25
					},		
				},
				messages : {
					username : {
						required : "Please Enter User ID.",
						maxlength : "Usen Name Can Not Exceed 10 Character."
					},
					password : {
						required : "Please Enter Password.",
						maxlength : "Please Enter Valid Password."
					},
				},
					onfocusout : function(element) {
			if ($(element).val()) {
				$(element).valid();
			}
		},
		errorElement : "em",
		errorPlacement : function(error, element) {
			error.addClass("help-block");
			element.parents(".col-sm-3, .customError").addClass("has-feedback");
			if (element.prop("type") === "checkbox") {
				error.insertAfter(element.parent("label"));
			} else {
				error.insertAfter(element);
			}
		},
		success : function(label,
				element) {
		},
		highlight : function(element,
				errorClass, validClass) {
			$(element).parents(".col-sm-3, .customError").addClass("has-error").removeClass("has-success");
		},
		unhighlight : function(element,
				errorClass, validClass) {
			$(element).parents(".col-sm-3, .customError").addClass("has-success").removeClass("has-error");
		}		
				}
			);
	};
	});
	
	
	</script>
	
	
</head>

<body background="${contextPath}/resources/Images/login.png" class="LoginFontSize">
 	<div class="container-fluid bg-3 text-center">
 	<div class="divEmptyThrice"></div>
	<div class="divEmptyThrice"></div>
	<div class="divEmpty"></div>	
    	<form class="form-inline" method="POST" action="${contextPath}/" class="form-signin">
    	<div class="row marginBottom5px">			
			<div class="col-sm-6">
				<h1><span class="textAlignRight"><font face="verdana" color="#04D5E4"><b>Eviden Right Price Portal</b></font></span></h1>
			</div>		
		</div>
		<div class="divEmptyThrice"></div>				
		<div class="divEmpty"></div>
        <div class="${error != null ? 'has-error' : ''} text-center">
        	<div class="row marginBottom5px">
        		<label class="control-label col-sm-1 textAlignRight">
					<font color="#04D5E4"><span class="glyphicon glyphicon-user spanIconAlighnTop"></span></font>
				</label>
				<div class="col-sm-3">
					<input name="username" id="username"  type="text" placeholder="User ID"
						 class="form-control" id="UserID" style="border: 0px" autofocus="true">
				</div>
        	</div>
            <div class="divEmptyThrice"></div>	
            <div class="row marginBottom5px">
				<label class="control-label col-sm-1 textAlignRight">
				<font color="#04D5E4"><span class="glyphicon glyphicon-lock spanIconAlighnTop"></span></font></label>
				<div class="col-sm-3">
					<input name="password" type="Password" placeholder="Password"
						class="form-control" id="password" style="border: 0px" onchange="javascript:validatePassword();">
					<div class="marginBottom5px"></div>
					<span>${error}</span>
            		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</div>
			</div>			
			<div class="divEmptyThrice"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-5">
					<button class="btn btn-info" name="buttonId" id="buttonId" type="submit" style="width: 40%">Log In</button>
				</div>
			</div>
			<div class="divEmpty"></div>
            <div class="row">
				<label class="col-sm-6"><font color="#04D5E4">* Please note that this site requires IE 10.0 or higher IE browser version to view correctly.</font></label>
			</div>
        </div>
    </form>
</div>
</body>
</html>
