//var app = angular	.module('RightPriceApp', ['ngMessages','ngStorage']);
	app.controller("RFOpportunityController", ['$scope','$location','$anchorScroll','$http','$filter','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$location,$anchorScroll,$http,$filter,$window,WebServiceFactory, $localStorage, $sessionStorage, $index) {
		 console.log("inside RFOpportunityController");
		 var contextPath = "/RightPrice-DAS";	
//		 $scope.rfDetail=[];
		 $scope.skill=[];
		 $scope.skillElement=[];
		 $scope.saved=false;
		 $scope.uploadHide=true;
		 $scope.showHide=true;
		 $scope.imgHide=true;
		 $scope.saveDownload;
		 window.onload=function(){
		$scope.stop=false;	 
		//	 $scope.claimaddRow();
			 $scope.rfDetail= $localStorage.rfData;
			 WebServiceFactory.getOldResourceData($localStorage.rfData.cRMDealId).then(getOldResourceData);
			 console.log("$scope.RFDetail");
			 console.log($scope.RFDetail);
		 }
		 var getOldResourceData=function(response){
			 if(response.data!=0){
				 $scope.imgHide=false;
				 $("#btnUpload_Download").text("Export to Excel");
				 $scope.address='${contextPath}/resources/Images/downloadexcel.png';
				 $scope.stop=true;
			  $scope.Data=response.data
			 console.log("data from old resource");
			 console.log(response.data);
			 $scope.RFData=response.data[0]						 //response.data[0]- resource Data
			 $scope.lobData=response.data[1] 					 //response.data[1]- Lob Data
			 console.log($scope.rows);
			 console.log($scope.lobData)
		/*	 $scope.dealData.push({
				 "lobCode":$scope.lobData[0].lobCode 
			 });*/
			 
			 WebServiceFactory.getXOSkillsRoles().then(getXOSkillsRoles);
			 WebServiceFactory.getKnowledgeNameRole().then(getKnowledgeNameRole);
			 WebServiceFactory.getGrade().then(getGrade);
			 WebServiceFactory.getSyntelRoles().then(getSyntelRoles);
			 WebServiceFactory.getLobData().then(getLobData);
			 
			 angular.forEach($scope.RFData,function(value,key){
				 //$scope.getXOSkillElementRoles(value.skill,value);
				 $scope.rows.push({
				 "location" :  value.location,
				 "skill":$scope.skill,
				 "skillName" : value.skill,
				 "skillElement":$scope.skillElement,
				 "elementName" : value.skillElement,
				 "knowledge" : $scope.knowledge,
				 "knowledgeName" : value.syntelXOKnowledge, 
				 "grade" : $scope.grade,
				 "description" : value.bandGrade,
				 "rols" : $scope.roles, 
				 "syntelRoleName": value.syntelRoleName,
				 "noResource" : value.noOfResource,
				 "startDate" : $filter('date')(value.startDate,'dd/MM/yyyy'),
				 "endDate" : $filter('date')(value.endDate,'dd/MM/yyyy')				 
				 })
			 });
			 $scope.dealData=[];
			 $scope.dealData.push({	
				 "lobCode": $scope.lobData[0].lob
				 });
			 $scope.forCast.lobCode=$scope.lobData[0].lob;
			 $scope.uploadHide=true;
			 $scope.showHide=false;
			 $scope.saveDownload="download";
			 
		 }	else{
			 $scope.imgHide=true;
			 $("#btnUpload_Download").text("Send/Publish to DeMS");
			 $scope.saveDownload="save";
			 $scope.uploadHide=false;
			 $scope.showHide=true;
			 $scope.stop=false;
			 $scope.claimaddRow();
			 $scope.uploadHide=false;
			 $("#btnUpload_Download").disable;
		 }
		 }
		
		 $scope.rows = [];
		 var getLobData=function(response){
			 $scope.dealData=response.data
			 console.log("data from lob");
			 console.log(response.data);
			 
		 }
		 WebServiceFactory.getLobData().then(getLobData);

		 $scope.claimaddRow = function() {
				$scope.rows.push({
			  });
			};

			$scope.claimdeleteRow = function(row) {
				if($scope.rows.length>1){
					  var index = $scope.rows.indexOf(row);
					  $scope.rows.splice(index, 1);
					    }
			};
			
		
			var getXOSkillsRoles=function(response){
				$scope.skill=response.data;
				console.log("data from Skills");
				 console.log(response.data);
			}
			WebServiceFactory.getXOSkillsRoles().then(getXOSkillsRoles);
		
			$scope.getXOSkillElementRoles=function(skillname,index){
				var skillId=skillname.skillId;
				
			var getXOSkillElementRoles=function(response){
				$scope.skillElement=response.data;
				console.log("data from SkillElement");
				 console.log(response.data);
				 angular.forEach($scope.rows,function(value,key){
	        			if(index == key && $scope.rows[key].skillname == skillname) {
	        				$scope.rows[key].skillElement = angular.copy($scope.skillElement);
	        			}
	        		});
				}
			WebServiceFactory.getXOSkillElementRoles(skillId).then(getXOSkillElementRoles);
			}
			
			var getKnowledgeNameRole = function(response){
				$scope.knowledge=response.data;
				console.log("data from Knowledge");
				 console.log(response.data);
			}
			WebServiceFactory.getKnowledgeNameRole().then(getKnowledgeNameRole);
			
			var getGrade = function(response){
				$scope.grade=response.data;
				console.log("data from grade");
				 console.log(response.data);
			}
			WebServiceFactory.getGrade().then(getGrade);
			
			var getSyntelRoles = function(response){
				$scope.roles=response.data;
				console.log("data from role");
				 console.log(response.data);
			}
			WebServiceFactory.getSyntelRoles().then(getSyntelRoles);
			
			
			$scope.saveResourceData=function(forCast,tableId){
				$scope.saved=true
				if($scope.showHide==true)									// if data is not available already  {show table is hidden} save the data 
				{
				if(forCast.$valid && $scope.saveDownload=="save"){
					$scope.saved=false;
					$scope.saveData();
				}
				}
				else
				{ 													// if data is available already  {show table is not hidden} save the data 
					$scope.saved=false;
					var exportHref=WebServiceFactory.DownloadRFExcel(tableId,'Resource_Forecast');
				}
			}
				
			
			  
            $scope.saveData=function(){
                $scope.flag=0;                                        // so that alert message for multiple upload should be displayed once only   
         console.log($scope.rows)
         var startDatearr= [];
         var endDatearr={};
         var lob=$scope.forCast.lobCode;
         var lobFlag=0;
         for (var i = 0; i < $scope.rows.length; i++) {
         var dealId= $localStorage.rfData.cRMDealId;
         var location=$scope.rows[i].location;
         var skill=$scope.rows[i].skillname.skillName;
         var skillId=$scope.rows[i].skillname.skillId;
         var skillElementId=$scope.rows[i].sElement.skillElementId;
         var skillElementName=$scope.rows[i].sElement.elementName;
         var grade=$scope.rows[i].grade.description;
         var gradeId=$scope.rows[i].grade.id;
         var syntelXOBandGrade = null;
         var knowledge=$scope.rows[i].knowledge.knowledgeName;
         var knowledgeId=$scope.rows[i].knowledge.knowledgeId;
         var noResources=$scope.rows[i].noResource;
         var roleId=$scope.rows[i].role.syntelRoleId;
         var roleName=$scope.rows[i].role.syntelRoleName;

         var startDate=$scope.rows[i].startDate;
         startDatearr=startDate.split("/");
         startDate=startDatearr[1]+"/"+startDatearr[0]+"/"+startDatearr[2];
         var endDate=$scope.rows[i].endDate;
         endDatearr=endDate.split("/");
         endDate=endDatearr[1]+"/"+endDatearr[0]+"/"+endDatearr[2];
   
         var marker={
                       "dealId" :dealId,
                       "location" :location,
                       "skillID" : skillId,
                       "skill" :skill,
                       "skillElementId" :skillElementId,
                       "skillElement" :skillElementName,
                       "bandGrade" :grade,
                       "bandGradeId" :gradeId,
                       "syntelXOBandGrade" :syntelXOBandGrade,
                       "syntelXOKnowledge" :knowledge,
                       "syntelXOKnowledgeID" :knowledgeId,
                       "noOfResource" :noResources,
                       "syntelRoleID" :roleId,
                       "syntelRoleName" :roleName,
                       "startDate" :startDate,
                       "endDate" :endDate
         }
   
         var save=function(response){
           if($scope.flag==0){
                       console.log(response);
                 if(response.status == 200) 
                 {

                        BootstrapDialog.show({
                       title : 'Opportunity Resource Forecasting',
                       type : BootstrapDialog.TYPE_PRIMERY,
                       message : "Data Inserted Successfully.",
                       closable : false,
                       buttons : [{
                              label : 'OK',
                              action : function(dialogRef) {
                                    dialogRef.close();
                                    $window.location.reload();
                                    
                              }
                       }]
                 });
                    
                 } 
                 else {
                        BootstrapDialog.show({
                       title : 'Opportunity Resource Forecasting',
                       type : BootstrapDialog.TYPE_DANGER,
                       message : "Currently facing techinical issue.",
                       closable : false,
                       buttons : [{
                              label : 'OK',
                              action : function(dialogRef) {
                                    dialogRef.close();
                                    
                              }
                       }]
                 });
                 }

           }
           $scope.flag+=1;
                }
         WebServiceFactory.saveResourceData(marker,lob,lobFlag).then(save);
         lobFlag++;
         }
   }
            
            $scope.prev=function() 
            {   
            	window.location="ResourceForcast";
            };
	}]);