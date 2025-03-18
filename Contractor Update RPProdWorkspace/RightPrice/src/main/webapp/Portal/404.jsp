<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insurance Portal</title>
<meta charset="utf-8">
<meta name="csrf-token" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="../Support/CSS/SAPStyleSheet.css" rel="stylesheet" />
<script src="../Support/js/jquery.min.js"></script>
<script src="../Support/js/bootstrap.min.js"></script>
<script src="../Support/js/jquery.serializeJSON.min.js"></script>
<script>
	$(function() {
		$("#includedContent").load("/AdminPortal/UIPages/Portal/Header.jsp");
	});
</script>
</head>
<body>
	<div id="includedContent"></div>
	<div class="divEmptyThrice"></div>
	<div class="divEmptyThrice"></div>
	<div class="container-fluid bg-3 text-center">

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row marginBottom5px">
					<label class="control-label col-sm-12 text-center">Looks the file you are looking for is no longer available!</label>
				</div>
			</div>
		</div>
	</div>
</body>
</html>