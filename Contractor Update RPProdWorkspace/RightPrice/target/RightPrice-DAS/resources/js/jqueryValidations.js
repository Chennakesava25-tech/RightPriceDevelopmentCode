function parseDMY(value) {
	var date = value.split("/");
	var d = parseInt(date[0], 10), m = parseInt(date[1], 10), y = parseInt(
			date[2], 10);
	return new Date(y, m - 1, d);
}

$(document).ready(function() {

	$(".customDate").datepicker({
		dateFormat : 'dd/mm/yy',
		changeMonth : true,
		changeYear : true
	});

$.validator
		.addMethod(
				"dateForamtVal",
				function(value, element) {
					return this.optional(element)
							|| /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i
									.test(value);
				}, 'Please enter Date in dd/mm/yyyy format.');

$.validator.addMethod("datelessThanToday", function(value, element) {	
	var theDate = parseDMY(value);
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	
	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	 today = dd+'/'+mm+'/'+yyyy;	
	
	var paramDate = parseDMY(today);
	if (today === "")
		return true;
	if (value === "")
		return true;
	if (!/Invalid|NaN/.test(theDate)) {
		return theDate <= paramDate;
	}
	return isNaN(value) && isNaN(today)
			|| (Number(value) > Number(today));
	
}, "Letters");

$.validator.addMethod("dateLessThan", function(value, element, params) {
	var theDate = parseDMY(value);
	var paramDate = parseDMY($(params).val());
	if ($(params).val() === "")
		return true;
	if (value === "")
		return true;
	if (!/Invalid|NaN/.test(theDate)) {
		return theDate <= paramDate;
	}

	return isNaN(value) && isNaN($(params).val())
			|| (Number(value) < Number($(params).val()));
}, 'Must be less than {0}.');

$.validator.addMethod("dateGreaterThan",

		function(value, element, params) {
			var theDate = parseDMY(value);
			var paramDate = parseDMY($(params).val());
			if ($(params).val() === "")
				return true;
			if (value === "")
				return true;
			if (!/Invalid|NaN/.test(theDate)) {
				return theDate >= paramDate;
			}
			return isNaN(value) && isNaN($(params).val())
					|| (Number(value) > Number($(params).val()));
		}, 'Must be greater than {0}.');

$.validator.addMethod("moblieFormat", function(value, element) {
	return this.optional(element) ||/^\+?(\d[.\- ]*){9,12}(e?xt?\d{1,5})?$/g.test(value);
}, "Please enter alphanumeric only.");

$.validator.addMethod("alphanumericSpace", function(value, element) {
	return this.optional(element) || /^[a-z0-9\:\_\.\,\-\s]+$/i.test(value);
}, "Please enter alphanumeric only.");

$.validator.addMethod('greater_than', function(value, element, param) {
	if ($(param).val() && $(element).val()) {
		return +$(element).val() > +$(param).val();
	} else
		return true;
});

$.validator.addMethod("maxDateVal",function(value, element, params) {
			var cvEndDate = parseDMY(value);
			var paramDate = parseDMY($(params).val());
			if ($(params).val() === "")
				return true;
			if (value === "")
				return true;
			
			var diff = ((cvEndDate - paramDate) / (1000 * 60 * 60 * 24))+ 1;
			
			if(diff<=30 || diff<=31)
				return true;
			else
				return false;
			
		}, 'Must be greater than {0}.');
});

$.validator.addMethod('time_Grater',function(endTime, startTime ){
	var startTime = document.getElementById('txtStartTime').value;
	var endTime = document.getElementById('txtRtdReturnTime').value;

	var travelDate=document.getElementById('txtTravelDate').value;
	var rtntravelDate=document.getElementById('txtRtdReturnDate').value;
	
	var sts = startTime.split(":");
	var ets = endTime.split(":");

	var stMin = (parseInt(sts[0]) * 60 + parseInt(sts[1]));
	var etMin = (parseInt(ets[0]) * 60 + parseInt(ets[1]));
	
	if( startTime==""){
		return true;
	}
	
	if(travelDate!=rtntravelDate){
		return true;
		}
	
	else{
		if( etMin > stMin) {
			return true;
		}
		else
			return false;
	}
	});

$.validator.addMethod('time_GraterThan',function(value, element, params){
	
	var fromTime = $(params).val();
	var toTime = value;
	
	var fromDate= document.getElementById(params.replace("Time", "Date").replace("#", "")).value; 
	fromDate = parseDMY(fromDate);
	var toDate= document.getElementById(params.replace("FromTime", "ToDate").replace("#", "")).value; 
	toDate = parseDMY(toDate);
	var sts = fromTime.split(":");
	var ets = toTime.split(":");

	var stMin = (parseInt(sts[0]) * 60 + parseInt(sts[1]));
	var etMin = (parseInt(ets[0]) * 60 + parseInt(ets[1]));
	
	
	if((new Date(fromDate).getTime()) !=  new Date(toDate).getTime())
	{	
		return true;
	}
	
	if ($(params).val() === "")
		return true;
	if (value === "")
		return true;
	
	if(toDate>= fromDate && etMin > stMin)
	{
		return true;
	}
	else
	{
		return false;
	}
});


$.validator.addMethod('DuplicateVal',function(value, element, params){
	var id=$(element).attr('id');
	var value = document.getElementById(id).value;
	console.log(value);
	if(value == ""){
		return true;
	}
	for(var i=1;i<=10;i++){
		if("txtRow"+i+"EmployeeId" != id){
			if(document.getElementById("txtRow"+i+"EmployeeId").value == value){
				return false;
			}else{
				continue;
			}
		}
	}
	return true;
	
});

$.validator.addMethod('timeGreaterThan',function(value, element, params){
	var fromTime = $(params).val();
	var toTime = value;
	var fromTimets = fromTime.split(":");
	var toTimets = toTime.split(":");
	var fromMin = (parseInt(fromTimets[0]) * 60 + parseInt(fromTimets[1]));
	var toMin = (parseInt(toTimets[0]) * 60 + parseInt(toTimets[1]));
	if( toMin > fromMin) 
	return true;
	else
	  return false;
});

$.validator.addMethod("alphanumericSpace", function(value, element) {
	return this.optional(element) || /^[a-z0-9\:\_\.\,\-\s]+$/i.test(value);
}, "Please enter alphanumeric only.");

