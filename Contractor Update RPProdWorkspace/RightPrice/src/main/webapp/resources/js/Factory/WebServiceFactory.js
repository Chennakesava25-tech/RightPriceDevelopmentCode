app.factory('WebServiceFactory',['$http','$window','$location','$timeout', function($http,$window,$location,$timeout) {
	var factory={};
	var contextPath = "/RightPrice-DAS";
	 /*var uri='data:application/vnd.xls;base64,',
    template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table border="10">{table}</table></body></html>',
  	base64=function(s){return $window.btoa(unescape(encodeURIComponent(s)));},
    format=function(s,c){return s.replace(/{(\w+)}/g,
    		 function(m,p){return c[p];})};
    
//	var contextPath = "";
	
	var uri='data:application/vnd.ms-excel;base64,',
	    template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table border="10">{table}</table></body></html>',
	  	base64=function(s){return $window.btoa(unescape(encodeURIComponent(s)));},
	   format=function(s,c){return s.replace(/{(\w+)}/g,
	    		 function(m,p){return c[p];})};
	
	factory.tableToExcel=function(tableId,worksheetName){
	   
	         var table=$(tableId),
	             ctx={worksheet:worksheetName,table:table.html()},
	             href=uri+base64(format(template,ctx));
	             
	         return href;
	     };*/
	
	var uri='data:application/vnd.ms-excel;base64,',
	template='<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
	base64=function(s){return $window.btoa(unescape(encodeURIComponent(s)));},
	format=function(s,c){return s.replace(/{(\w+)}/g,function(m,p){return c[p];})};

	factory.tableToExcel= function (tableId, fileName) {

		 name = fileName + '.xls';
		 var table = document.querySelector(tableId),
			  /*ctx = {table: table.innerHTML};*/
		 ctx = {worksheet:fileName,table: table.innerHTML,scaleShowGridLines : table.innerHTML};
		 var browser = window.navigator.appVersion;

		//Workaround to enable the users to download the report in IE.
		if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
			(browser.indexOf('MSIE 10') !== -1)) {
			var builder = new window.MSBlobBuilder();
			builder.append(uri + format(template, ctx));
			var blob = builder.getBlob('data:application/vnd.ms-excel');
			window.navigator.msSaveOrOpenBlob(blob, name);
		} else {
			
			
			var blob = new Blob([format(template, ctx)], { type: 'application/vnd.ms-excel', endings: 'native' });

			var elem = window.document.createElement('a');
			//elem.href = uri + base64(format(template, ctx));
			elem.href = window.URL.createObjectURL(blob);
			elem.download = name;
			document.body.appendChild(elem);
			elem.click();
			document.body.removeChild(elem);
			
			}
		
		};
		
		factory.dealTableToExcel= function (dealTableId, fileName) {

			 name = fileName + '.xls';
			 var table = document.querySelector(dealTableId),
				  /*ctx = {table: table.innerHTML};*/
			 ctx = {worksheet:fileName,table: table.innerHTML};
			 var browser = window.navigator.appVersion;

			//Workaround to enable the users to download the report in IE.
			if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
				(browser.indexOf('MSIE 10') !== -1)) {
				var builder = new window.MSBlobBuilder();
				builder.append(uri + format(template, ctx));
				var blob = builder.getBlob('data:application/vnd.ms-excel');
				window.navigator.msSaveOrOpenBlob(blob, name);
			} else {
				
				var blob = new Blob([format(template, ctx)], { type: 'application/vnd.ms-excel', endings: 'native' });

				var elem = window.document.createElement('a');
				//elem.href = uri + base64(format(template, ctx));
				elem.href = window.URL.createObjectURL(blob);
				elem.download = name;
				document.body.appendChild(elem);
				elem.click();
				document.body.removeChild(elem);
				
				}
			
			};
		
		
		factory.exportToExcelSummary= function (tableId, fileName) {

			 name = fileName + '.xls';
			 var table = document.querySelector(tableId),
				  /*ctx = {table: table.innerHTML};*/
			 ctx = {worksheet:fileName,table: table.innerHTML};
			 var browser = window.navigator.appVersion;

			//Workaround to enable the users to download the report in IE.
			if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
				(browser.indexOf('MSIE 10') !== -1)) {
				var builder = new window.MSBlobBuilder();
				builder.append(uri + format(template, ctx));
				var blob = builder.getBlob('data:application/vnd.ms-excel');
				window.navigator.msSaveOrOpenBlob(blob, name);
			} else {
				
				
				var blob = new Blob([format(template, ctx)], { type: 'application/vnd.ms-excel', endings: 'native' });

				var elem = window.document.createElement('a');
				//elem.href = uri + base64(format(template, ctx));
				elem.href = window.URL.createObjectURL(blob);
				elem.download = name;
				document.body.appendChild(elem);
				elem.click();
				document.body.removeChild(elem);
				
				}
			
			};

			factory.exportToExcelDevelopmentQuestionnaire= function (tableId, fileName) {

				 name = fileName + '.xls';
				 var table = document.querySelector(tableId),
					  /*ctx = {table: table.innerHTML};*/
				 ctx = {worksheet:fileName,table: table.innerHTML};
				 var browser = window.navigator.appVersion;

				//Workaround to enable the users to download the report in IE.
				if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
					(browser.indexOf('MSIE 10') !== -1)) {
					var builder = new window.MSBlobBuilder();
					builder.append(uri + format(template, ctx));
					var blob = builder.getBlob('data:application/vnd.ms-excel');
					window.navigator.msSaveOrOpenBlob(blob, name);
				} else {
					
					
					var blob = new Blob([format(template, ctx)], { type: 'application/vnd.ms-excel', endings: 'native' });

					var elem = window.document.createElement('a');
					//elem.href = uri + base64(format(template, ctx));
					elem.href = window.URL.createObjectURL(blob);
					elem.download = name;
					document.body.appendChild(elem);
					elem.click();
					document.body.removeChild(elem);
					
					}
				
				};
		
		factory.tableToExcelRate= function (tableId, fileName,tabName) {

			 name = fileName + '.xls';
			 var table = document.querySelector(tableId),
				  ctx = {worksheet:tabName,table: table.innerHTML};
			 var browser = window.navigator.appVersion;

			//Workaround to enable the users to download the report in IE.
			if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
				(browser.indexOf('MSIE 10') !== -1)) {
				var builder = new window.MSBlobBuilder();
				builder.append(uri + format(template, ctx));
				var blob = builder.getBlob('data:application/vnd.ms-excel');
				window.navigator.msSaveOrOpenBlob(blob, name);
			} else {
				
				
				var blob = new Blob([format(template, ctx)], { type: 'application/vnd.ms-excel', endings: 'native' });

				var elem = window.document.createElement('a');
				//elem.href = uri + base64(format(template, ctx));
				elem.href = window.URL.createObjectURL(blob);
				elem.download = name;
				document.body.appendChild(elem);
				elem.click();
				document.body.removeChild(elem);
				
				}
			
			};
	factory.getCurrency = function() {
		console.log("inside getCurrency");
		 return $http({method : 'GET',cache:true,url:contextPath+"/RightPrice-DAS/getCurrency"
		});
	};
	
	factory.getExchangeRate = function() {
		console.log("inside getExchangeRate");
		 return $http({method : 'GET',cache:true,url:contextPath+"/RightPrice-DAS/getExchangeRate"
		});
	};
	
	factory.getrateCardId = function() {
		 return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getrateCardId"
		});
	};
	
	
	factory.getCountryDetail = function() {
		 return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCountryDetail"
		});
	};
	
	factory.getCountryData = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCountryData"
		});
	};
	
	/*//added by priya
	factory.tableToExcel=function(tableId,worksheetName){
    // 	alert('Inside Webfactory');
         var table=$(tableId),
             ctx={worksheet:worksheetName,table:table.html()},
             href=uri+base64(format(template,ctx));
             
         return href;
     };*/
     
    
    
	//end by priya
	
	
	/*Rate card creation details [PRASAD]*/
	
	factory.getCities = function(countryId) {
		console.log("Fetching cities for countryId : " + countryId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCity/"+countryId
		});
	};
	
	factory.getVisaTypes = function(countryId) {
		console.log("Fetching visa types for countryId : " + countryId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getAsumpVisaTypes/"+countryId
		});
	};
	
	factory.getCurrentUserDetails = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCurrentUserDetails"
		});
	};
	
	
	factory.getRateCards = function(customerVerticalMappingId) {
		console.log("Fetching getRateCards for customerVerticalMappingId : " + customerVerticalMappingId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCards/"+customerVerticalMappingId
		});
	};
	
	factory.getRateCardDetailsFromRCId = function(rateCardId){
		console.log(rateCardId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardDetailsFromRCId/"+rateCardId
		});
	};
	
	factory.saveRateCard = function(markers) {
		console.log("inside saveRateCard");
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/rateCardDetails",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	//save newratecard details
	factory.saveRateCardNew = function(markers) {
		console.log("inside saveRateCardNew");
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/RateCardDetails_New",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	
	/*Rate card creation details [PRASAD]*/ 
	/*Fixed Price Deal Creation Rate Card And Project Details [PRASAD]*/ 

	factory.saveFPDealCreationRCAndProjectDetails = function(markers) {
		console.log("inside FPDealCreationRCAndProjectDetails");
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/FPDealCreationRCAndProjectDetails",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	

	factory.saveTMDealCreationRCAndProjectDetails = function(markers) {
		console.log("inside TMDealCreationRCAndProjectDetails");
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/TMDealCreationRCAndProjectDetails",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getFPDealCreationRCAndProjectDetails = function(dealVersionId) {
		console.log("inside get FPDealCreationRCAndProjectDetails for deal version id : " + dealVersionId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/FPDealCreationRCAndProjectDetails/"+dealVersionId
		});
	};
	
	factory.getTMDealCreationRCAndProjectDetails = function(dealVersionId) {
		console.log("inside get TMDealCreationRCAndProjectDetails for deal version id : " + dealVersionId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/TMDealCreationRCAndProjectDetails/"+dealVersionId
		});
	};
	
	
	
	
	factory.saveTMDCRoleSelectionAndContractorRole = function(markers) {
		console.log("inside saveTMDCRoleSelectionAndContractorRole");
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/saveTMDCRoleSelectionAndContractorRole",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	//getPersistDealRoledDetails
	factory.getPersistDealRoledDetails = function(dealVersionId) {
		//alert("Inside PersistRateCardDetails........"+rcId);
		console.log("In web service factory .........");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFPDCRoleSelectionAndContractorRole/"+dealVersionId
		});
	};
	
	/*Fixed Price Deal Creation Role Selection And Add contractor Role [PRASAD]*/
	
	factory.getPractice = function() {
		console.log("inside getPractice");
		 return $http({method : 'GET',cache:true,url:contextPath+"/RightPrice-DAS/getPractice"
		});
	};
	
	factory.getSubPractice = function(pracId) {
		console.log("inside getSubPractice");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSubPractice/"+pracId
		});
	};
	
	factory.viewSubPracticeData = function(practiceId) {
		console.log("inside viewSubPracticeData");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/viewSubPracticeData/"+practiceId
		});
	};
	
	factory.viewcontractorRoleList = function() {
		console.log("inside viewcontractorRoleList");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/viewcontractorRoleList/"
		});
	};
	
	factory.getStaffingcontractorRoleList = function(rpVrsId,tower_id) {
		console.log("inside viewcontractorRoleList");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getStaffingcontractorRoleList/"+rpVrsId+"/"+tower_id
		});
	};
	
	factory.addSubPractice = function(markers) {
		console.log("inside viewSubPracticeData");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/insertSubPractice",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateSubPractice = function(markers) {
		console.log("inside updateSubPractice"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateSubPractice",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.addCountryDetails = function(markers) {
		console.log("inside addCountryDetails");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/addCountryDetails",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateCountryDetail = function(markers) {
		console.log("update addCountryDetails");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateCountryDetails",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	//getCustomer
	factory.getCustomerByVerticalGroupId = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCustomerByVerticalGroupId"
		});
	};
	
	factory.getCustomerForUser = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCustomerForUser"
		});
	};
	//getcountryList
	factory.getCountryList = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCountryList"
			});
		};
	factory.getGFTCustomer = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getGFTCustomer"
		});
	};
	
	factory.getEmpName = function(customerId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getEmpName/"+customerId
		});
	};
	
	factory.getCountry = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCountry"
		});
	};
	
	//arvind
	
	factory.getRateCardName = function(customerId,dealId,cityId,countryId,Industry_ID) {
		//alert('185 factory.getRateCardName'+customerId);
		
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardName/"+customerId+"/"+dealId+"/"+cityId+"/"+countryId+"/"+Industry_ID
		});
	};
	//end arvind
	
	factory.getCity = function(countryId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCity/"+countryId
		});
	};
	
	
	factory.getCityData = function(countryId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCityData/"+countryId
		});
	};
	
	factory.getCitycategorization = function() {
		
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCitycategorization"
		});
	};
	
/*Developed by AG5027026------------------------------------------*/
	
	factory.getCampusHireThresholdPercent = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCampusHireThresholdPercent"
		});
	};
		
	factory.updateThresholdPercent = function(markers) {
		console.log("update updateThresholdPercent");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateThresholdPercent",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
		
	factory.saveContractualTerms = function(markers) {
		console.log("save ContractualTerms");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/saveContractualTerms",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
		
	factory.getContractualTerms = function(versionId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getContractualTerms/"+versionId
		});
	};
		
	factory.getDesignation = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDesignation"
		});
	};
		
	factory.getBand = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getBand"
		});
	};
		
	factory.getGrade = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getGrade"
		});
	};
		
	factory.addDesignation = function(markers) {
		console.log("save Designation");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/addDesignation",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
		
	factory.updateDesignation = function(markers) {
		console.log("update Designation");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateDesignation",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getVertical = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVertical"
		});
	};
	
	factory.getAppCodeData = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getAppCodeData "
		});
	};
	
	
	factory.getCustomers = function(verticalId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCustomers/"+verticalId
		});
	};
	
	
	factory.getVerticalGroupID = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVerticalGroupID"
		});
	};
	factory.addVertical = function(markers) {
		console.log("update Vertical");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/addVertical",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateVertical = function(markers) {
		console.log("update Vertical");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateVertical",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.getSkills = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSkills"
		});
	};

	factory.getRoles = function(practiceId,subPracticeId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRoles/"+practiceId+"/"+subPracticeId
		});
	};
	
	factory.getAddRoles = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getAddRoles"
		});
	};
	
	factory.getUpdateMasterRoles = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getUpdateMasterRoles"
		});
	};
	
	factory.getPracticeRoles = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPracticeRoles"
		});
	};
	
	factory.getXOSkillsRoles = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getXOSkillsRoles"
		});
	};
	
	factory.getSyntelRoles = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSyntelRoles"
		});
	};
	
	factory.getProficiency = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getProficiency"
		});
	};
	
	factory.getXOSkillElementRoles = function(skillId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getXOSkillElementRoles/"+skillId
		});
	};
	
	factory.getKnowledgeNameRole = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getKnowledgeNameRole"
		});
	};
	
	factory.getSubPracticeRole = function(practiceId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSubPracticeRole/"+practiceId
		});
	};
	
	factory.addRole = function(markers) {
		console.log("Add Role");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/addRole",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateRole = function(markers) {
		console.log("Update Role");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateRole",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getRolesDetails = function(masterRoleId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRolesDetails/"+masterRoleId
		});
	};

	/*Developed by AG5027026------------------------------------------*/
	
	factory.getLobDetails = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getLobDetails"
		});
	};
	
	factory.getDeals = function(customerId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDeals/"+customerId
		});
	};
	
	factory.getDealsFrmCrmStages = function(customerId,dealTypeForTM) {
		//alert('387factory=====================');
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealsFrmCrmStages/"+customerId+"/"+dealTypeForTM
		});
	};
	factory.getOldDealId = function(customerIdForOlddeal,current_DealModel) {
		
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getOldDealId/"+customerIdForOlddeal+"/"+current_DealModel
		});
	};
	factory.getVersionDetails = function(crmDealId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVersionDetails/"+crmDealId
		});
	};
	
	factory.getVersionDetailsTM = function(crmDealId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVersionDetailsTM/"+crmDealId
		});
	};
	
	factory.getRiskAnswers = function(rpDealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRiskAnswers/"+rpDealVersionId
		});
	};
	
	factory.insertDealData = function(fd) {
		console.log("inside insert T&M Deal Data");
		
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/insertDealData", enctype :'multipart/form-data', data: fd,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	
	factory.insertFpDealData = function(fd) {
		console.log("inside insertData");
		
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/insertFpDealData", enctype :'multipart/form-data', data: fd,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	
	factory.insertDlCurrUTI = function(dlcrUT) {
				
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/insertDlCurrUTI", enctype :'multipart/form-data', data: dlcrUT,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	factory.insertDlLoc = function(dlLoc) {
			
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/insertDlLoc", enctype :'multipart/form-data', data: dlLoc,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	
	factory.updateDealData = function(fd) {
		console.log("inside update T&M deal data");
		
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/updateDealData", enctype :'multipart/form-data', data: fd,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	
	factory.updateFpDealData = function(fd) {
		console.log("inside update Fp deal data");
		
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/updateFpDealData", enctype :'multipart/form-data', data: fd,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	
	factory.getYoyIncrement = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getYoyIncrement/"+rcId
		});
	};
	
	factory.getSummary = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSummary/"+rcId
		});
	};
	factory.getSummaryOld = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSummaryOld/"+rcId
		});
	};
	factory.getSummaryOld = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSummaryOld/"+rcId
		});
	};
	
	factory.getSummaryCountry = function(rateCardId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSummaryCountry/"+rateCardId
		});
	};
	
	factory.getVerticalDetails = function(verticalId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVerticalDetails/"+verticalId
		});
	};
	
	factory.saveApprovalData = function(markers) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/saveApprovalData",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getLeadershipDetails = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getLeadershipDetails"
		});
	};
	
	factory.updateApprovalDataForRateCard = function(marker) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateApprovalDataForRateCard",
		    dataType: 'json',
		    data: JSON.stringify(marker),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.saveContractorRole = function(markers) {
		console.log("inside saveContractorRole:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/saveContractorRole",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.removeClick = function(roleid) {
		console.log("inside removeClick");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/removeClick/"+roleid
		});
	};
	
	factory.insertData = function(fd) {
		console.log("inside webfactory:"+fd);
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/saveuploadedFile", enctype :'multipart/form-data', data: fd,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	
	factory.getLobDetails = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getLobDetails"
		});
	};
	
	factory.getRateCardInfo = function(rcId) {
//		console.log("inside View Rate Card Info");
		return $http({method : 'GET',cache:true,url :  contextPath+"/RightPrice-DAS/getRateCardDetailsFromRCId/"+rcId
		});
	};
	
	factory.getApproverInfo = function(rcId,approvalFlag) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getApproverDesignation/"+rcId
		});
	};

	factory.getApproverName = function(rcId) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getApproverName/"+rcId
		});
	};
	
	factory.getLeaderApproverName = function() {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getLeaderApproverName"
		});
	};
	/*// approvel status update
	factory.updateApprovalStatus = function(marker) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateApprovalData",
		    dataType: 'json',
		    data: JSON.stringify(marker),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};*/
	
	factory.getDealDetails = function(crmDealId,rpVrsId) {
		/*console.log("inside get Approver Info");
		console.log("The CRM Deal Id is......... "+crmDealId);
		console.log("The RP Version Id is......... "+rpVrsId);*/
		//alert('factory.getDealDetails inside');
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDetails/"+crmDealId+"/"+rpVrsId
		});
	};
	
	factory.getRatecardSummaryData = function(rpVrsId)
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRatecardSummaryData/"+rpVrsId
		});	
		
	};
	
	factory.getStaffingPercentage = function(rpVrsId)
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getStaffingPercentage/"+rpVrsId
		});			
	};
	
	factory.getFinal_GM_Per = function(rpVrsId)
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFinal_GM_Per/"+rpVrsId
		});			
	};
	
	factory.getFinal_GM_Per_Sum = function(rpVrsId)
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFinal_GM_Per_Sum/"+rpVrsId
		});			
	};
	
	factory.updateRP_Deal_Table = function(rpVrsId,calc_total_GM_session,tCVModelValue)
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/updateRP_Deal_Table/"+rpVrsId+"/"+calc_total_GM_session+"/"+tCVModelValue
		});			
	};
	
	factory.getStaffingDone = function(rpVrsId)
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getStaffingDone/"+rpVrsId
		});			
	};
	factory.get_onloadCoun_City = function(rpVrsId)
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/get_onloadCoun_City/"+rpVrsId
		});			
	};
	
	factory.getUploadedDoc = function(versionId) {
		console.log("inside getUploadedDoc");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getUploadedDoc/"+versionId
		});
	};
	
	factory.downloadFileFormID = function(objectId) {
		return 	window.location=contextPath+"/RightPrice-DAS/downloadFileFormID/"+objectId;
	};
	
	factory.downloadFileWithFileName = function(dealAttachmentId) {
		return 	window.location=contextPath+"/RightPrice-DAS/downloadFileWithName/"+dealAttachmentId;
	};
	
	factory.updateActiveStatus = function(docId) {
		console.log("inside removeClick");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/updateActiveStatus/"+docId
		});
	};
	
	factory.uploadEstimationRelatedDoc = function(fd) {
		console.log("inside webfactory:"+fd);
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/saveuploadedFile", enctype :'multipart/form-data', data: fd,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};
	
	/* Role Selection Screen RITU ...... */
	
	//factory to get X0 skills based on subpractice id 
		factory.getXOSkillFromSubPracticeId = function(subPracticeId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getXOSkillFromSubPracticeId/"+subPracticeId
			});
		};
		
		factory.getXOSkillFromSubPracticeId2 = function(subPracticeId,masterRoleId,rcId_value) {
			
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getXOSkillFromSubPracticeId2/"+subPracticeId+"/"+masterRoleId+"/"+rcId_value    
			});
		};
	//save or update roles
		factory.saveOrUpdateRole = function(markers) {
			console.log("inside saveRateCard");
			console.log(markers);
			return $http({
			    method: 'POST',
			    url: contextPath+"/RightPrice-DAS/UpdateRateCardDetails",
			    dataType: 'json',
			    data: angular.toJson(markers),  
			    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			});
		};
		
	//factory to get Rate Card id
	factory.getRateCard = function() {
		
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCard"
		
		});
	};
	factory.getRateCardDeal = function(rpVrsId) {
		
		/* return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCard"*/
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCardDeal/"+rpVrsId
		});
	};

	
	//factory to get X0 skills 
	factory.getXOSkills = function() {
		
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getXOSkills"
		});
	};
	
	//factory to get X0 skills element master 
	factory.getXOSkillsElementMaster = function(skillId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getXOSkillsElementMaster/"+skillId
		});
	};
	
	//factory to get knowledge name
	factory.getKnowledgeName = function() {
		
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getKnowledgeName"
		});
	};
	factory.getKnowledgeName_saved = function(masterRoleId,rcId_value) {
		
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getKnowledgeName_saved/"+masterRoleId+"/"+rcId_value
		});
	};
	
	factory.getRoleSelectionDetails = function(rcId)
	{
		//console.log("In getRateCardDetails .........");
		return $http({method :'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRoleSelectionDetails/"+rcId
		});
	};
	
	factory.getPersistRateCardDetails = function(rcId) {
		//alert("Inside PersistRateCardDetails........"+rcId);
		console.log("In web service factory .........");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPersistRateCardDetails/"+rcId
		});
	};
	
	
	factory.searchRateCardData = function(searchRoles)
	{
		//alert("searchRoles " + searchRoles);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/searchRateCardData",
		    dataType: 'json',
		    data: searchRoles,  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	/* Role Selection Screen RITU ...... */
	/*Visa Screen sneha -----------------*/
	factory.getVisaTypeDetails = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getVisaTypeDetails"
		});
	};
	
	factory.addVisaDetails = function(marker) {
		console.log("inside addVisaDetails");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/addVisaDetails",
		    dataType: 'json',
		    data: JSON.stringify(marker),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateVisaDetails = function(markers) {
		console.log("update Visa Label");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateVisaDetails",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.viewVisaDetails = function(markers) {
		console.log("View Visa Label");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/viewVisaDetails",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getVisaLabel = function(countryId,visaTypeId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getVisaLabel/"+countryId+"/"+visaTypeId
		});
	};
	
	factory.getVisa = function() {
		console.log("inside getVisa");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVisa",
		});
	};
	
	factory.viewAllowances = function(markers) {
		console.log("inside viewAllowances"+markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/viewAllowances",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.checkAnnualAllownces = function(markers) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/checkAnnualAllownces",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.saveAnnualAllowances = function(markers) {
		console.log("inside saveAnnualAllowances:"+markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/saveAnnualAllowances",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.updateAnnualAllowances = function(markers) {
		console.log("inside updateAnnualAllowances:");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateAnnualAllowances",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getRateUtilization = function(cityId,rateCardId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRoleUtilization/"+cityId+"/"+rateCardId
		});
	};
	
	//factory to get employee details
    factory.updateRateUtilizationAndRates = function(marker) {
            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/updateRateUtilizationAndRates", dataType: 'json',data: angular.toJson(marker), 
                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
            });
    };

	factory.onViewParameterForm = function(markers) {
		console.log("inside onViewParameterForm:");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/onViewParameterForm",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.onTaxViewParameterForm = function(markers) {
		console.log("inside onViewParameterForm:");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onTaxViewParameterForm",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.onAssumptionsViewParameterForm = function(markers) {
		console.log("inside onViewParameterForm:");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onAssumptionsViewParameterForm",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getParamList = function() {
		 return $http({method : 'GET',cache:true,url:contextPath+"/RightPrice-DAS/getParamList"
		});
	};
	
	factory.getTaxParamList = function() {
		 return $http({method : 'GET',cache:true,url:contextPath+"/RightPrice-DAS/getTaxParamList"
		});
	};
	factory.getAssumptionsParamList = function() {
		return $http({method : 'GET',cache:true,url:contextPath+"/RightPrice-DAS/getAssumptionsParamList"
		});
	};
	
	factory.onAddCCPSearch = function(markers) {
		console.log("inside onAddCCPSearch:");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/onAddCCPSearch",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.onAddTPSearch = function(markers) {
		console.log("inside onAddTPSearch:");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onAddTPSearch",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.onAddAPSearch = function(countryId,visaTypeId,year) {
		console.log("Fetching all parameters w.r.t.country: "+countryId+",visa type : " + visaTypeId+" and year : "+ year);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/onAddAPSearch/"+countryId + "/"+ visaTypeId +"/"+ year
		});
	};
	
	factory.onUpdateTPSearch = function(countryId,visaTypeId,year) {
		console.log("Fetching all parameters w.r.t.country: "+countryId+",visa type : " + visaTypeId+" and year : "+ year);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/onUpdateTPSearch/"+countryId + "/"+ visaTypeId +"/"+ year
		});
	};
	
	factory.saveCommonCostParam = function(markers) {
		console.log("inside saveCommonCostParam:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/saveCommonCostParam",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.saveTaxParam = function(markers) {
		console.log("inside saveTaxParam:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/saveTaxParam",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.saveAssumptionsParam = function(markers) {
		console.log("inside saveAssumptionsParam:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/saveAssumptionsParam",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	/*factory.onUpdateCCPSearch = function(markers) {
		console.log("inside onUpdateCCPSearch:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onUpdateCCPSearch",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};*/

	factory.onUpdateCCPSearch = function(countryId,visaTypeId,year) {
		console.log("Fetching all parameters w.r.t.country: "+countryId+",visa type : " + visaTypeId+" and year : "+ year);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/onUpdateCCPSearch/"+countryId + "/"+ visaTypeId +"/"+ year
		});
	};
	
	factory.onUpdateAPSearch = function(markers) {
		console.log("inside onUpdateAPSearch:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onUpdateAPSearch",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateCommonCostParam = function(markers) {
		console.log("inside updateCommonCostParam:");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateCommonCostParam",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.updateTaxParam = function(markers) {
		console.log("inside updateTaxParam:");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateTaxParam",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.updateAssumptionsParam = function(markers) {
		console.log("inside updateAssumptionsParam");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateAssumptionsParam",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateAssumptionsParamValue = function(markers) {
		console.log("inside updateAssumptionsParam");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateAssumptionsParamValue",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.viewMiscellaneousCost = function(markers) {
		console.log("View Miscellaneous Cost ");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/viewMiscellaneousCost",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
/*	factory.addMiscellaneousCost = function(marker) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/addMiscellaneousCost",
		    dataType: 'json',
		    data: JSON.stringify(marker),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
*/	
	factory.getUpdateMiscellaneousCost = function(cityId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getUpdateMiscellaneousCost/"+cityId
		});
	};
	
	factory.updateMiscellaneousCost = function(markers) {
		console.log("inside updateMiscellaneousCost"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateMiscellaneousCost",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getRateUtilizationCountry = function(rateCardId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardUtilizationCountry/"+rateCardId
		});
	};
	
	factory.getRateCardManualUploadCountry = function(rateCardId) {
		return $http({method : 'GET', cache:true,url:contextPath+"/RightPrice-DAS/getRateCardManualUploadCountry/"+rateCardId
			});
	}
	
	factory.getRateUtilizationCity = function(rateCardId,countryId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateUtilizationCity/"+rateCardId+"/"+countryId
		});
	};
	
	factory.getRateCardUploadCity = function(rateCardId,countryId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardUploadCity/"+rateCardId+"/"+countryId
		});
	};
	
//my dashboard
	factory.getVerticalMemberData = function(user) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVerticalMemberData/"+user
		});
	};
	
	factory.getSummaryCountBasedOnVerticalId = function(fd) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/getSummaryCountBasedOnVerticalId",
		    dataType: 'json',
		    data: angular.toJson(fd),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getRateCardData = function(fd) {
		console.log("getRateCardData");
		console.log(fd);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/getRateCardData",
		    dataType: 'json',
		    data: angular.toJson(fd),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getCustomerBasedDealRecords = function(fd) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/getCustomerBasedDealRecords",
		    dataType: 'json',
		    data: angular.toJson(fd),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getDealCountBasedOnVerticalId = function(fd) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/getDealCountBasedOnVerticalId",
		    dataType: 'json',
		    data: angular.toJson(fd),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getDashboardDealData = function(fd) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/getDashboardDealData",
		    dataType: 'json',
		    data: angular.toJson(fd),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getGFTMemberDetails = function() {
		 return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getGFTMemberDetails"
		});
	};
	
	factory.getDescName = function(taxnAssmId) {
		console.log("inside getDescName ----------"+taxnAssmId);
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDescName/"+taxnAssmId
		});
	};
	
	factory.insertTaxnAssumtionParam = function(markers) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/insertTaxnAssumtionParam",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.updateTaxnAssumtionParam = function(markers) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateTaxnAssumtionParam",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.viewTaxParam = function(markers) {
		console.log("inside viewTaxParam----"+markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/viewTaxParam",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
//------------FP deal calculation details------------------------------

	factory.getCountryCityBasedOnDealVersion = function(rpDealVersionId) {
	//	alert(rpDealVersionId);
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCountryCityBasedOnDealVersion/"+rpDealVersionId
		});
	};
	
	factory.getTowerDetails = function(rpDealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getTowerDetails/"+rpDealVersionId
		});
	};
	
	factory.getSummaryCalculationData = function(cityId, towerId) {
	//	alert(cityId+"     "+towerId);
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSummaryCalculationData/"+cityId+"/"+towerId
		});
	};
	
	factory.getOtherCalculationData = function(cityId, towerId) {
		//alert(cityId+"     "+towerId);
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getOtherCalculationData/"+cityId+"/"+towerId
		});
	};
	
	factory.getFpDealStaffingData = function(rpDealVersionId,cityId,towerId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getFpDealStaffingData/"+rpDealVersionId+"/"+cityId+"/"+towerId
		});
	};
	
		
	factory.getTMDealStaffingData = function(cityId,towerId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getTMDealStaffingData/"+cityId+"/"+towerId
		});
	};
	
	factory.findTotalTransMonth=function(towerId,rpDealVersionId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/findTotalTransMonth/"+towerId+"/"+rpDealVersionId
		});
	};
	
    factory.updateFpDealStaffingDetails = function(marker) {
            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/updateFpDealStaffingDetails", dataType: 'json',data: angular.toJson(marker), 
                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
            });
    };
	factory.updateTMDealStaffingDetails = function(marker) {
		            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/updateTMDealStaffingDetails", dataType: 'json',data: angular.toJson(marker), 
		                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		            });
		    };

	factory.updateStaffingContractorRole = function(marker) {
	            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/updateStaffingContractorRole", dataType: 'json',data: angular.toJson(marker), 
	                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
	            });
	    };
 	/*factory.updateStaffingContractorRole = function(marker) {
	            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/updateStaffingContractorRole", dataType: 'json',data: angular.toJson(marker), 
	                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
	            });
	    };*/
	
	factory.getPageTrackerData = function(rcId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getPageTrackerData/"+rcId
		});
	};
	
	//arvind	
	/*factory.showRateCardTable1 = function(rpversionId) {
		//alert("servise called");
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/showRateCardTable1/"+rpversionId
		});
	};*/
	//end arvind
	factory.getPrevDataDeal_Version = function(versionId) //(versionId, crmDealId, customerId)
	{
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPrevDataDeal_Version/"+versionId //+"/"+crmDealId+"/"+customerId
			});
		};
		
	factory.getContry_City = function(versionId) //(versionId, crmDealId, customerId)
	{
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getContry_City/"+versionId //+"/"+crmDealId+"/"+customerId
			});
		};
	
	
	factory.viewSalary = function(markers) {
		console.log("inside viewSalary");
	            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/viewSalary", dataType: 'json',data: angular.toJson(markers), 
		                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
	       });
	};
	
	factory.addSalarySearch = function(markers) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/addSalarySearch",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.insertSalary = function(markers) {
		console.log("inside insertSalary:"+markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/insertSalary",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateSalarySearch = function(markers) {
		console.log("inside updateSalarySearch:");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateSalarySearch",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateSalary = function(markers) {
		console.log("inside updateSalary:");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateSalary",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.onViewMasterRate = function(markers) {
		console.log("inside onViewMasterRate"+markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/onViewMasterRate",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.onMasterRateSearch = function(markers) {
		console.log("inside onMasterRateSearch:");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/onMasterRateSearch",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.onUpdateMasterRateSearch = function(markers) {
		console.log("inside onUpdateMasterRateSearch:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onUpdateMasterRateSearch",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.onAddMasterRate = function(markers) {
		console.log("inside onAddMasterRate:"+markers);
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onAddMasterRate",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};

	factory.onUpdateMasterRate = function(markers) {
		console.log("inside onUpdateMasterRate:");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/onUpdateMasterRate",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	// Function for getting onsite Facility 
	
	factory.getOnsiteFacilityCost = function(countryId,cityId,towerId) {
		//alert("servise called");
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getOnsiteFacilityCost/"+countryId+"/"+cityId+"/"+towerId
		});
	};
	
	// Function for getting the data for Online Relocation
	factory.getRelocationTravelDetails = function(countryId,cityId,towerId) {
		//alert("servise called");
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRelocationDetails/"+countryId+"/"+cityId+"/"+towerId
		});
	};
	
	factory.getShiftWorkingDetails = function(countryId,cityId,towerId) {
		//alert("servise called");
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getShiftWorkingDetails/"+countryId+"/"+cityId+"/"+towerId
		});
	};
	
	factory.addRPAccessControlData = function(markers) {
		console.log("save Designation");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/addRPAccessControlData",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getDealSummaryData = function(dealId,rpVersionId) {
		console.log(rpVersionId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getDealDetailsFromId/"+dealId+"/"+rpVersionId
		});
	};
	
	factory.getTMFinalizeDealComment = function(rpVersionId) {
		console.log(rpVersionId);
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getTMFinalizeDealComment/"+rpVersionId
		});
	};
	
	factory.updateApprovalDataForDeal = function(markers) {
		console.log("inside onUpdateMasterRate:");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateApprovalDealData",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getSyntelPremium = function(cityId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getSyntelPremium/"+cityId
		});
	}
	
		/*	factory.getRateCardContractorRole = function(cityId,countryId ,masterRoleId,rateCardId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardContractorRole/"+cityId+"/"+countryId+"/"+masterRoleId+"/"+rateCardId
		});
	};*/

	factory.insertContractorRole = function(markers) {
		console.log("save Designation");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/insertContractorRole",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};

	factory.getContractor = function(cityId,rateCardId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getContractor/"+cityId+"/"+rateCardId
		});
	};
	factory.getMasterRateRoles = function(countryId) {
		console.log("inside getMasterRateRoles");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getMasterRateRoles/"+countryId
		});
	};

	factory.getCalculationDetails = function(cityId,rcId) {
		console.log("inside getMasterRateRoles");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/gerRateCardCalDetails/"+cityId+"/"+rcId
		});
	};
	factory.getRateCardDetail = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCardDetails/"+rcId
		});
	};
	
	factory.saveMasterRateCardDetails = function(markers) {
		console.log("inside saveRateCard");
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/saveMasterRCDetails",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	/*factory.saveMarginDetails = function(rateCardDetails) {
		return $http({method: 'POST',url: contextPath+"/RightPrice-DAS/saveCalculatedMargin", enctype :'multipart/form-data', data: rateCardDetails,  
		    headers: { 'Content-Type': undefined,'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		});
	};*/
	
	factory.updateRateCard = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/updateRateCardStatus/"+rcId
		});
	};
	
	factory.getCustomer = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCustomer"
		});
	};
	factory.updateCustomer = function(markers) {
		console.log("update Customer");
		return $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/updateCustomer",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.getFxRateComment = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFxRateComment/"+rcId
		});
	};
	
	factory.getSummaryCurrencyUti = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getSummaryCurrencyUti/"+rcId
		});
	};
	
	factory.getRateCardAssumtion = function(assumtion_id) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCardAssumtion/"+assumtion_id
		});
	};
	
	
	factory.getRateCardLocation = function(rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCardLocation/"+rcId
		});
	};
	
	factory.getDealApproverInfo = function(rpVrsId) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealApproverDesignation/"+rpVrsId
		});
	};
	
	factory.getTMDealApproverInfo = function(rpVrsId) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getTMDealApproverDesignation/"+rpVrsId
		});
	};
	
	/*factory.getDealApproverName = function(rpVrsId) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealApproverName/"+rpVrsId
		});
	};*/
	
	factory.getDealApproverName = function(rpVrsId,rbuName) {
		console.log("inside get Approver Info rbu",rbuName);
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealApproverName/"+rpVrsId+"/"+rbuName
		});
	}; //Akhilesh updated
	
	factory.getDealQuest = function() {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealQuest"
		});
	};
	
	
	factory.getVersionData = function(rpDealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVersionData/"+rpDealVersionId
		});
	};
	
	
	factory.getDevMainAnswers = function(rpDealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDevMainAnswers/"+rpDealVersionId
		});
	};
	
	
	factory.getDealTower = function(dealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealTower/"+dealVersionId
		});
	};
	
	factory.getFpDealRateCard = function(dealTowerId,dealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpDealRateCard/"+dealTowerId+"/"+dealVersionId
		});
	};
	
	factory.getDataOnSearch = function(rateCardID) {
	return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDataOnSearch/"+rateCardID
	});
};
/*factory.getDataOnSearch = function() {
	return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDataOnSearch"
	});
};*/
	
	factory.getAttachement = function(markers) {
		return  $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/getAttachementData",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};


	factory.getTMAttachementData = function(markers) {		
		return  $http({
			method: 'POST',
			url: contextPath+"/RightPrice-DAS/getTMAttachementData",
			dataType: 'json',
			data: JSON.stringify(markers),  
			headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
				
	};
	
	
	factory.changeActiveStatus = function(objectid) {
		console.log("inside DeleteClick");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/changeActiveStatus/"+objectid
		});
	};
	factory.deleteMasterRCAttachment = function(objectid) {
		console.log("inside DeleteClick");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/deleteMasterRCAttachment/"+objectid
		});
	};
	
	
	factory.saveFPDealRoleSelectionAndContractorRole = function(markers) {
		console.log("inside saveFPDealRoleSelectionAndContractorRole");
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/saveFPDealRoleSelectionAndContractorRole",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	
	factory.getPersistFpDealRoledDetails = function(dealVersionId,dealAutoTowerId) {
		//alert("Inside PersistRateCardDetails........"+rcId);
		console.log("In web service factory .........");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFPDealRoleSelectionAndContractorRole/"+dealVersionId+"/"+dealAutoTowerId
		});
	};
	
	/*factory.getRCDetails = function(cityId,customerId,startDate) {
		//alert("Inside PersistRateCardDetails........"+rcId);
		console.log("In web service factory .........");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealRCDetails/"+cityId+"/"+customerId+"/"+startDate
		});
	};*/
	
	factory.getDealRateCardName = function(customerId,deal_Id,currencyId,countryId,cityCategory,industryType) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getDealRateCardName/"+customerId+"/"+deal_Id+"/"+currencyId+"/"+countryId+"/"+cityCategory+"/"+industryType
		});
	};
	factory.deleteRateCard = function(dltRcId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/deleteRateCard/"+dltRcId
		});
	};
	
	factory.getUserRoles = function(usreName) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getUserRoles/"+usreName
		});
	};
	
	factory.getFpDealCostInputsData = function(towerId,costType,rpDealVersionId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getFpDealCostInputData/"+towerId+"/"+costType+"/"+rpDealVersionId
		});
	};
	
	factory.getFpDealCalculationData = function(towerId,rpDealVersionId) {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getFPCostCalCulationData/"+towerId+"/"+rpDealVersionId
		});
	};
	
	factory.getVersionAttachment = function(rpDealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVersionAttachment/"+rpDealVersionId
		});
	};
	
	factory.getDescription = function(cityId) {
			console.log("Fetching description for cityId : " + cityId);
			return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getDescription/"+cityId
			});
		};
		

		// updating the status of the Deal On submit to GFT.
		factory.updateManualDeal = function(versionId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/updateDealVersionStatus/"+versionId
			});
		};	
		
		// updating the status of the Deal On Ready to submit.
		factory.updateManualGFTDeal = function(versionId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/updateManualGFTDeal/"+versionId
			});
		};	

	/*factory.getApproverNames = function(rcId) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getApproverName/"+rcId
		});
	};*/
	
	factory.getApproverNames = function(rcId,rbutype) {
//		console.log("inside get Approver Info");
		
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getApproverName/"+rcId+"/"+rbutype
		});
	}; //manglam updated
	
	factory.getFPManualDealApproverInfo = function(rpVrsId) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFPManualDealApproverInfoDesignation/"+rpVrsId
		});
	};
	
	/*factory.getFPMDApproverNames = function(verticalId,rpVrsId) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFPMDApproverNames/"+verticalId+"/"+rpVrsId
		});
	};*/
	
	factory.getFPMDApproverNames = function(verticalId,rpVrsId,rbuName) {
//		console.log("inside get Approver Info");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFPMDApproverNames/"+verticalId+"/"+rpVrsId+"/"+rbuName
		});
	}; //Akhilesh Updated
	
	
	factory.saveFPMDApprovalData = function(markers) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/saveFPMDApprovalData",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.updateApprovalDataForFPMD = function(marker) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateApprovalDataForFPMD",
		    dataType: 'json',
		    data: JSON.stringify(marker),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};


	//=====================Ayyaz changes start
	factory.getDealData = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getDealData"
		});
	};
	
		factory.forecastData = function(markers) {
		console.log(markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/ForecastData",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	factory.getLobData = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getLobData"
		});
	};
	
	factory.getRolesData = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRolesData"
		});
	};
	
	factory.saveResourceData = function(markers,lob,lobFlag) {
        console.log(markers);
        return $http({
            method: 'POST',
            url: contextPath+"/RightPrice-DAS/saveResourceData/"+lob+"/"+lobFlag,
            dataType: 'json',
            data: angular.toJson(markers),  
            headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
        });
 };

	factory.getOldResourceData = function(cRMDealId) {
		console.log(" cRMDealId "+ cRMDealId);
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getOldResourceData/"+cRMDealId
			});
		};

	factory.DownloadRFExcel= function (tableId, fileName) {

			 name = fileName + '.xls';
			 var table = document.querySelector(tableId),
				  ctx = {table: table.innerHTML};
			 ctx = {worksheet:fileName,table: table.innerHTML};
			 var browser = window.navigator.appVersion;
			//Workaround to enable the users to download the report in IE.
			if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
				(browser.indexOf('MSIE 10') !== -1)) {
				var builder = new window.MSBlobBuilder();
				builder.append(uri + format(template, ctx));
				var blob = builder.getBlob('data:application/vnd.ms-excel');
				window.navigator.msSaveOrOpenBlob(blob, name);
			} else {
				
				
				var blob = new Blob([format(template, ctx)], { type: 'application/vnd.ms-excel', endings: 'native' });
				var elem = window.document.createElement('a');
				//elem.href = uri + base64(format(template, ctx));
				elem.href = window.URL.createObjectURL(blob);
				elem.download = name;
				document.body.appendChild(elem);
				elem.click();
				document.body.removeChild(elem);
				
				}
			
			};

	//================================================ changes for cost summery
			factory.getCostData = function(crmDealId,rpVrsId) {
				return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCostData/"+crmDealId+"/"+rpVrsId
				});
			};
			
			factory.downloadExcel= function (tableId, fileName) {

				 name = fileName + '.xls';
				 var table = document.querySelector(tableId),
					  ctx = {table: table.innerHTML};
				 ctx = {worksheet:fileName,table: table.innerHTML};
				 var browser = window.navigator.appVersion;

				//Workaround to enable the users to download the report in IE.
				if ((browser.indexOf('Trident') !== -1 && browser.indexOf('rv:11') !== -1) ||
					(browser.indexOf('MSIE 10') !== -1)) {
					var builder = new window.MSBlobBuilder();
					builder.append(uri + format(template, ctx));
					var blob = builder.getBlob('data:application/vnd.ms-excel');
					window.navigator.msSaveOrOpenBlob(blob, name);
				} else {
					
					
					var blob = new Blob([format(template, ctx)], { type: 'application/vnd.ms-excel', endings: 'native' });

					var elem = window.document.createElement('a');
					//elem.href = uri + base64(format(template, ctx));
					elem.href = window.URL.createObjectURL(blob);
					elem.download = name;
					document.body.appendChild(elem);
					elem.click();
					document.body.removeChild(elem);
					
					}
				
				};

				factory.updateBillingSchedule = function(markers,dealId,rpDealVersionId) {
					console.log(markers,dealId,rpDealVersionId);
					return $http({
					    method: 'POST',
					    url: contextPath+"/RightPrice-DAS/updateBillingSchedule/"+dealId+"/"+rpDealVersionId,
					    dataType: 'json',
					    data: angular.toJson(markers),  
					    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					});
				};
			
			
	//================================================ changes for cost summery ends here		
	
			
	//=====================Ayyaz changes ends
			
			
			factory.getWhatIfData = function(rpVrsId) {
				return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getWhatIfData/"+rpVrsId
				});
			};
			
			factory.getDealRateCardDetails = function(rateCardId) {
				return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardDetailsFromRCId/"+rateCardId
				});
			};
			
			
			factory.getWhatIfEffortData = function(rpVrsId) {
				return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getWhatIfEffortData/"+rpVrsId
				});
			};
			
			factory.updateFpDealWhatIfDetails = function(marker) {
				            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/updateFpDealWhatIfDetails", dataType: 'json',data: angular.toJson(marker), 
				                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
				            });
				    };
				
				/*factory.submitApproverData = function(marker) {
					return $http({
					    method: 'POST',
					    url: contextPath+"/RightPrice-DAS/updateFPApprover",
					    dataType: 'json',
					    data: angular.toJson($scope.selectedObj),  
					    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					});
				};*/
				
	factory.getDealDataOnSearch = function(crmDealID) 
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDataOnSearch/"+crmDealID
		});
	};
	
	/*factory.getDealDataOnSearch = function(dealVresionID) 
	{
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDataOnSearch/"+dealVresionID
		});
	};*/
				
	factory.deleteDealVersion = function(dltDealVersionId) 
	{
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/deleteDealVersion/"+dltDealVersionId
			});
	};
				
	factory.getFpDealTowerData = function() {
		 return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getFpDealTowerData"
		});
	};
	
	
	factory.getWhatIfCalculationData = function(rpVrsId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getWhatIfCalculationData/"+rpVrsId
		});
	};
	
	
	factory.getFpDealRoleDetails = function(dealVersionId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpDealRoleDetails/"+dealVersionId
		});
	};
	
	factory.downloadMyDashboardExcel = function(fd,rateCardStatus) {
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/downloadMyDashboardExcel/"+rateCardStatus,
		    dataType: 'json',
		    data:angular.toJson(fd),
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	factory.cityAttachement = function(cityId,rcId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCardManualAttachment/"+cityId+"/"+rcId
		});
	};
	
	factory.deleteManualFile = function(attachmentId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/changeFileActiveStatus/"+attachmentId
		});
	};
	
	factory.getWhatIfContractData = function(rpVrsId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getWhatIfContractData/"+rpVrsId
		});
	};
	
	factory.getApprovalMatrixWhatIf = function(rpVrsId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getApprovalMatrixWhatIf/"+rpVrsId
		});
	};

	factory.getUpdatedApprovalMatrix = function(rpVrsId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getUpdatedApprovalMatrix/"+rpVrsId
		});
	};
	
	
	factory.getFpVersionsReadyToSubmit = function(crmDealId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpVersionsReadyToSubmit/"+crmDealId
		});
	};
	
	factory.getYearCount = function(rpVrsId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getYearCount/"+rpVrsId
		});
	};
	
	factory.saveMasterAnnualAllowances = function(markers) {
		console.log("inside saveAnnualAllowances:"+markers);
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/saveMasterAnnualAllowances",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	
	factory.updateAllowanceShortTerm = function(markers) {
		console.log("inside updateAnnualAllowances:");
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/updateShortTermAllowances",
		    dataType: 'json',
		    data: JSON.stringify(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
	};
	
	
	factory.getFpRfpRfiFinalVersion = function(crmDealId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpRfpRfiFinalVersion/"+crmDealId
		});
	};
	factory.getWhatIfDataExcel = function(rpVrsId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getWhatIfDataExcel/"+rpVrsId
		});
	};
	
	factory.getFpDealCalculationMonthData = function(monthHeader) {
		return monthHeader;
	};
	
	factory.getFpVersionsWhatIfApproval = function(crmDealId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpVersionsWhatIfApproval/"+crmDealId
		});
	};
	
	factory.getFpToExchangeRates = function(rpVrsId,currencyId) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpToExchangeRates/"+rpVrsId+"/"+currencyId
		});
	};
	
	factory.getRateCardsBasedOnIndustry = function(customerVerticalId,industry) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRateCardOnIndustry/"+customerVerticalId+"/"+industry
		});
	};
	
	
	factory.getOldDealDetails = function(customerId,startDate) {
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getOldDealDetails/"+customerId+"/"+startDate
		});
	};
	

	factory.getUploadedEADoc = function(versionId) {
		console.log("inside getUploadedDoc");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getUploadedEADoc/"+versionId
		});
	};
	
	factory.deleteEADoc = function(attachmentId) {
		console.log("inside removeClick");
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/deleteEADoc/"+attachmentId
		});
	};
	
	factory.getCountryData = function(manualCountryId) {
		console.log("inside removeClick");
		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCountryByManualFlag/"+manualCountryId
		});
	};
	
	
	factory.getAttachment = function(rpDealVersionId,cityId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getAttachment/"+rpDealVersionId+"/"+cityId
		});
	};
	
	factory.getRiskManagersMemberDetail = function(user) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRiskManagersMemberDetail/"+user
		});
	};
	
	factory.getCountryForexData = function() {
		return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCountryForexData"
		});
	};
	
	factory.getDelicgateUserAccess = function(user) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDelicgateUserAccess/"+user
			});
	};
	
	factory.getVerticalApproverData = function() {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVerticalApproverData"
			});
	};
	
	factory.getDUHCustomerVerticalMapping = function(markers) {
		
		return $http({
		    method: 'POST',
		    url: contextPath+"/RightPrice-DAS/getDUHCustomerVerticalMapping",
		    dataType: 'json',
		    data: angular.toJson(markers),  
		    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
		// return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDUHCustomerVerticalMapping/"+verticalId
	};
	
	
	factory.getCustomerVerticalFP = function(customerId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getVerticalByCustId/"+customerId
			});
	};
	factory.getCrmDealDetails = function(crmDealId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCrmDealDetails/"+crmDealId
			});
	};
	factory.saveTcvTMDeal = function(tcv,rpVrsId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/saveTcvTMDeal/"+tcv+"/"+rpVrsId
		});
	};
	factory.getStaffingSubContractorPricing = function(rpVrsId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getStaffingSubContractorPricing/"+rpVrsId
		});
	};
	
	factory.getOldDealDetailsPricing = function(oldDealId) {
		 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getOldDealDetailsPricing/"+oldDealId
			});
	};
	
factory.getDealsForCust = function(customerId,dealTypeForFM) {
			//alert('387factory=====================');
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealsForCust/"+customerId+"/"+dealTypeForFM
			});
		};
		
factory.updateRCStatus = function(rcId) {
			return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/rcRecycleStatus/"+rcId
			});
		};

		/*factory.getDealStatusDetails = function(dealStatusValue) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealStatusData/"+dealStatusValue
			 });
		};*/
		
		factory.getDealStatusDetails = function(fd) {
			return $http({
			    method: 'POST',
			    url: contextPath+"/RightPrice-DAS/getDealStatusData",
			    dataType: 'json',
			    data: angular.toJson(fd),  
			    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			});
		};
		
		//factory to get employee details
		    factory.updateUtilizationData = function(marker) {
		            return $http({method : 'POST',cache:true,url : contextPath+"/RightPrice-DAS/updateRateUtilizationRates", dataType: 'json',data: angular.toJson(marker), 
		                   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') }
		            });
		    };
		
		factory.getFpDealRateCardOnVersion = function(dealVersionId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpDealRateCardOnVersion/"+dealVersionId
			});
		};
		
		factory.getRateCardsInfo = function(customerVerticalMappingId) {
			console.log("Fetching getRateCards for customerVerticalMappingId : " + customerVerticalMappingId);
			return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardInfo/"+customerVerticalMappingId
			});
		};
		
		factory.getRateGFTCountry = function(rateCardId) {
			return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateGFTCountry/"+rateCardId
			});
		};
		
		factory.getUserName = function(lanId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getUserName/"+lanId
			});
		};
		
		factory.getApproverData = function(rcId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getApproverData/"+rcId
				});
		};
		
		factory.getFpDealStaffingDataWhatIf = function(rpDealVersionId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFpDealStaffingDataWhatIf/"+rpDealVersionId
			});
		};
		
		factory.getAtosRcData = function() {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getAtosRcData"
				});
			};
		
		factory.addPracticeDetails = function(markers) {
			console.log("inside addPracticeDetails");
			return $http({
			    method: 'POST',
			    url: contextPath+"/RightPrice-DAS/addPracticeDetails",
			    dataType: 'json',
			    data: JSON.stringify(markers),  
			    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			});
		};
		
		factory.updatePracticeDetails = function(markers) {
			console.log("update PracticeDetails");
			return $http({
			    method: 'POST',
			    url: contextPath+"/RightPrice-DAS/updatePracticeDetails",
			    dataType: 'json',
			    data: JSON.stringify(markers),  
			    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
			});
		};
		
		factory.getActiveRCData = function() {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getActiveRCData"
				});
			};
		factory.getGFTWeeklyData = function() {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getGFTWeeklyData"
				});
			};
		
		factory.getRCComment = function(rcId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getRCComment/"+rcId
				});
			};

		factory.saveComment = function(rcId,comment,user) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/saveComment/"+rcId+"/"+comment
				});
			};
			
		factory.getFileredAtosRcData = function(country,startDate,endDate,action) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFileredAtosRcData/"+country+"/"+startDate+"/"+endDate+"/"+action
				});
			};
		factory.getCommentData= function() {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCommentData"
				});
			};	
			
		factory.getStatusData= function() {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getStatusData"
				});
			};	
		factory.getPreSalesData= function() {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPreSalesData"
				});
			};	
			
		factory.setSubmitEnabled = function() {
			 return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/setSubmitEnabled"
			});
		};
		
		factory.setSubmitDisabled = function() {
			 return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/setSubmitDisabled"
			});
		};
		
		factory.getPreSalesDataOnId= function(salesId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPreSalesDataOnId/"+salesId
				});
			};	
			
		factory.getPreSalesApprovedData= function(status) {
			return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPreSalesApprovedData/"+status
				});
		};	
			
		factory.getPreSalesApprovalData= function(status) {
			return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPreSalesApprovalData/"+status
				});
		};
		
		factory.getOpportunityDetails= function() {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getOpportunityDetails"
				});
			};	
				
		factory.putOpportunityDealData= function(dealId) {
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/putOpportunityDealData/"+dealId
				});
			};	
			
			factory.getCurrencyCode = function() {
				 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getCurrencyCode"
					});
			};

			factory.saveBudgetDetails = function(markers) {
				console.log("inside saveBudgetDetails");
				console.log(markers);
				return $http({
				    method: 'POST',
				    url: contextPath+"/RightPrice-DAS/saveBudgetDetails",
				    dataType: 'json',
				    data: angular.toJson(markers),  
				    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				});
			};
			
			factory.submitBudgetDetails = function(markers) {
				console.log("inside saveBudgetDetails");
				console.log(markers);
				return $http({
				    method: 'POST',
				    url: contextPath+"/RightPrice-DAS/submitBudgetDetails",
				    dataType: 'json',
				    data: angular.toJson(markers),  
				    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				});
			};
				factory.searchDataOnId= function(markers) {
					console.log("searchDataOnId");
					console.log(markers);
					return $http({
					    method: 'POST',
					    url: contextPath+"/RightPrice-DAS/searchDataOnId",
					    dataType: 'json',
					    data: angular.toJson(markers),  
					    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
					});
					};	
					
					factory.mapDataOnId= function(markers) {
						console.log("mapDataOnId");
						console.log(markers);
						return $http({
						    method: 'POST',
						    url: contextPath+"/RightPrice-DAS/mapDataOnId",
						    dataType: 'json',
						    data: angular.toJson(markers),  
						    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
						});
						};
					
						factory.getPresalesOpportunityDetails = function() {
							 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getPresalesOpportunityDetails"
								});
						};
						
						/*factory.getSyntelUserId = function(userId) {
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getSyntelUserId/"+userId
							});
						};*/
						factory.getUserRoleData = function(usreName) {
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getUserRoleData/"+usreName
							});
						};
						factory.getMasterAttachement = function(markers) {
							return  $http({
								method: 'POST',
								url: contextPath+"/RightPrice-DAS/getMasterAttachement",
								dataType: 'json',
								data: JSON.stringify(markers),  
								headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
							});
						};
						factory.getFpDealCostInputsDataUpdate = function(towerId,rpDealVersionId) {
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getFpDealCostInputDataUpdate/"+towerId+"/"+rpDealVersionId
							});
						};
						
						factory.getDisabledCountryData = function(country){
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getDisabledCountryData/"+country
							});
						}
						
						factory.getFPDealSFInfo = function(dealId){
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getFPDealSFInfo/"+dealId
							});
						}
						factory.CheckUserAccess = function(accId){
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/CheckUserAccess/"+accId
							});
						}	
						factory.checkCustomerMappingInFin = function(crmdealID){
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/checkCustomerMappingInFin/"+crmdealID
							});
						}	
						factory.getDealDetailsForTM_Selection = function(crmdealID){
							return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getDealDetailsForTM_Selection/"+crmdealID
							});
						}	
						
						factory.getDistDedCatId = function()
						{
							return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDistDedCatId"
							});	
							
						};
						
						factory.getMasterDeductionDataForm = function(markers) {
							console.log("inside saveRateCard");
							console.log(markers);
							return $http({
							    method: 'POST',
							    url: contextPath+"/RightPrice-DAS/getMasterDeductionDataForm",
							    dataType: 'json',
							    data: angular.toJson(markers),  
							    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
							});
						};
						
						factory.getDealDetailsForGFT = function()
						{
							return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDetailsForGFT"
							});	
							
						};
						
						
						factory.getviewdealdetails = function(customerId) {
							
							return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getviewdealdetails/"+customerId
							});
						};
						
						factory.getviewrcdetails = function(customerId) {
							
							return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getviewrcdetails/"+customerId
							});
						};
						
						factory.getRateCardDetailsFromRCIdGFT = function(rateCardId){
			                console.log(rateCardId);
			                 return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardDetailsFromRCIdGFT/"+rateCardId
			});
			};
			
			factory.getRateCardsNew = function(customerVerticalMappingId) {
			console.log("Fetching getRateCards for customerVerticalMappingId : " + customerVerticalMappingId);
			return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getRateCardsNew/"+customerVerticalMappingId
			});
		};
		
		/*factory.getFileData = function(rcId) {
	return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFileData/"+rcId
	});
	};*/

	factory.downloadFileWithFileNameRC = function(Id) {
			return 	window.location=contextPath+"/RightPrice-DAS/downloadFileWithNameRC/"+Id
		};

			factory.deleteRCFile = function(AttachmentId) {
	console.log("inside DeleteClick");
	return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/deleteRCFile/"+AttachmentId
	});
	};	
	
	factory.saveRateCardNew = function(markers) {
		console.log("inside saveRateCardNew");
		console.log(markers);
		return $http({
		   method: 'POST',
		   url: contextPath+"/RightPrice-DAS/RateCardDetails_New",
		   dataType: 'json',
		   data: angular.toJson(markers),  
		   headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
		});
		};
			
		//Deal screen
		
		
		
		factory.getDealDetailss = function(crmDealId) {
			/*console.log("inside get Approver Info");
			console.log("The CRM Deal Id is......... "+crmDealId);
			console.log("The RP Version Id is......... "+rpVrsId);*/
			//alert('factory.getDealDetails inside');
			return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDetailss/"+crmDealId
			});
		};  
		factory.getAllDealsFrmCrmStages = function(customerId,dealTypeForFM) {
			//alert('387factory=====================');
			 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getAllDealsFrmCrmStages/"+customerId+"/"+dealTypeForFM
			});
		};
		
		
			factory.getAllDealsForCust = function(customerId,dealTypeForFM) {
				//alert('387factory=====================');
				 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getAllDealsForCust/"+customerId+"/"+dealTypeForFM
				});
			};
			
				factory.getDealDetailsFor_Selection = function(crmDealId)
							{
								return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDetailsFor_Selection/"+crmDealId
								});	
								
							};
							
//			factory.getDealDetailsFor_Selection_2 = function(dealId)
//							{
//								return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDetailsFor_Selection_2/"+dealId
//								});	
//								
//							};
							
							
							
							factory.saveDealData = function(markers) {
								console.log("inside saveDeal webservice factory");
								console.log(markers);
								return $http({
								    method: 'POST',
								    url: contextPath+"/RightPrice-DAS/saveDealData",
								    dataType: 'json',
								    data: angular.toJson(markers) , 
								    headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
								});
							};
							
							  factory.getFileData = function(crmDealId) {
	                 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getFileData/"+crmDealId
	                 });
	            };

		

	        	factory.downloadDealFileWithFileName = function(dealAttachmentId) {
	        		return 	window.location=contextPath+"/RightPrice-DAS/downloadDealFileWithFileName/"+dealAttachmentId;
	        	};
	        	factory.deleteDealFile = function(dealAttachmentId) {
	        		console.log("inside DeleteClick");
	        		return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/deleteDealFile/"+dealAttachmentId
	        		});
	        	};
		

	        	factory.getDataOnSearchview = function(rcId) {
	        	return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDataOnSearchview/"+rcId
	        	});
	        };
	        
	        factory.getDealDataOnSearchview = function(dealId) {
	        	return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getDealDataOnSearchview/"+dealId
	        	});
	        };
	        
	        ///Application access
	        
	        factory.addRProleData = function(markers) {
				console.log("save Designation");
				return $http({
					method: 'POST',
					url: contextPath+"/RightPrice-DAS/addRProleData",
					dataType: 'json',
					data: JSON.stringify(markers),  
					headers: { 'Content-Type': 'application/json; charset=UTF-8','X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content') },
				});
			};
	        
			factory.downloadVideos = function(type) {
				return window.location=contextPath+"/RightPrice-DAS/downloadVideos/"+type;
				};
				factory.getXOSkillFromMasterRoleId = function(masterRoleId) {
					 return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getXOSkillFromMasterRoleId/"+masterRoleId
					});
				};
				factory.getCurrentCustomerUserDetailskpo = function(industry,vertical) {
					return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCurrentCustomerUserDetailskpo/"+industry+"/"+vertical
					});
				};
				
				/*factory.getCurrentCustomerUserDetails = function(verticalId) {
					return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCurrentCustomerUserDetails/"+verticalId
					});
				}; */
				
				factory.getCurrentCustomerUserDetails = function(verticalId,rbutype) {
					return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCurrentCustomerUserDetails/"+verticalId+"/"+rbutype
					});
				}; //manglam updated
				
				factory.getWonDealDetailsForGFT = function()
				{
					return $http({method : 'GET',cache:true,url : contextPath+"/RightPrice-DAS/getWonDealDetailsForGFT"
					});	
					
				};	
				factory.getPageTrckrData = function(rcId) {	
					return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getPageTrckrData/"+rcId
						});	
					};	        
   //manglam updated
							factory.getPLAttachementData = function(markers) {
								return $http({
									method : 'POST',
									url : contextPath
											+ "/RightPrice-DAS/getPLAttachementData",
									dataType : 'json',
									data : JSON.stringify(markers),
									headers : {
										'Content-Type' : 'application/json; charset=UTF-8',
										'X-CSRF-TOKEN' : $(
												'meta[name="csrf-token"]')
												.attr('content')
									},
								});
							}; 
							
							factory.getCurrentCustomerUserDetailsJVkpo = function(industry,verticalId) {
								return $http({method : 'GET',cache:true,url: contextPath+"/RightPrice-DAS/getCurrentCustomerUserDetailsJVkpo/"+industry+"/"+verticalId
								});
							};	//manglam updated

	        
return factory;
	
	
	
}]);