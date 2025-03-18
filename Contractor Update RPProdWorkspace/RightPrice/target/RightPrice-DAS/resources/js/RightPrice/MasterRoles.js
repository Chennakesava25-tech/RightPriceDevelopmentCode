//var app = angular.module('RightPriceApp', ['ngMessages']);
		app.controller("MasterRolesController",['$scope','$location','$anchorScroll','$http','$window','WebServiceFactory', function($scope,$location,$anchorScroll,$http,$window,WebServiceFactory,$index){
			$(window).load(function(){
			     $('.loader').fadeOut();
			});
			
			$scope.numberRegex = /^(0|[1-9]\d*)(\.\d+)?$/;
			var contextPath = "/RightPrice-DAS";
			$scope.roleUpdateDetails = [];
			 $scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
			 $scope.onSave = false;
			 $scope.onUpdate = false;
			 $scope.onSearch = false;
			 $scope.onViewSearch = false;
			 $scope.downloadBtn = true;
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
                /*$scope.onSearch = false;
				$scope.viewMasterRoleForm.viewPracticeModel=null;
				$scope.viewMasterRoleForm.viewSubPracticeModel=null;*/
            };
             $scope.ShowHideAdd = function () {
                $scope.AddHidden = $scope.AddHidden ? false : true;
                $scope.onSave = false
				$scope.addMasterRoleForm.addMasterRoleCodeModel="";
                $scope.addMasterRoleForm.addShortDescModel="";
				$scope.addMasterRoleForm.addSkillsModel=null;
				$scope.addMasterRoleForm.addPracticeModel=null;
				$scope.addMasterRoleForm.addSubPracticeModel=null;
				$scope.addMasterRoleForm.addSyntelRoleModel=null;
				$scope.addMasterRoleForm.addProficiencyLevelModel=null;
				$scope.addMasterRoleForm.addDesignation1Model=null;
				$scope.addMasterRoleForm.addDesignation2Model=null;
				$scope.addMasterRoleForm.addDesignation3Model=null;
				$scope.addMasterRoleForm.addDesignation4Model=null;
				$scope.addMasterRoleForm.addDesignation1perModel="";
				$scope.addMasterRoleForm.addDesignation2perModel="";
				$scope.addMasterRoleForm.addDesignation3perModel="";
				$scope.addMasterRoleForm.addDesignation4perModel="";
				$scope.addMasterRoleForm.addSyntelX0SkillsModel=null;
				$scope.addMasterRoleForm.addSyntelX0SkillsElementModel=null;
				$scope.addMasterRoleForm.addSyntelX0KnowledgeAreaModel=null;
				$scope.addMasterRoleForm.addSyntelX0ProficiencyModel=null;
				$scope.addMasterRoleForm.addLongtDescModel="";
            };
             $scope.ShowHideUpdate = function () {
                $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
                $scope.onUpdate = false;
				$scope.updateMasterRoleForm.updateMasterRoleCodeModel=null;
				$scope.updateMasterRoleForm.updateShortDescModel="";
				$scope.updateMasterRoleForm.updateSkillsModel=null;
				$scope.updateMasterRoleForm.updatePracticeModel=null;
				$scope.updateMasterRoleForm.updateSubPracticeModel=null;
				$scope.updateMasterRoleForm.updateSyntelRoleModel=null;
				$scope.updateMasterRoleForm.updateProficiencyLevelModel=null;
				$scope.updateMasterRoleForm.updateDesignation1Model=null;
				$scope.updateMasterRoleForm.updateDesignation2Model=null;
				$scope.updateMasterRoleForm.updateDesignation3Model=null;
				$scope.updateMasterRoleForm.updateDesignation4Model=null;
				$scope.updateMasterRoleForm.updateDesignation1perModel="";
				$scope.updateMasterRoleForm.updateDesignation2perModel="";
				$scope.updateMasterRoleForm.updateDesignation3perModel="";
				$scope.updateMasterRoleForm.updateDesignation4perModel="";
				$scope.updateMasterRoleForm.updateSyntelX0SkillsModel=null;
				$scope.updateMasterRoleForm.updateSyntelX0SkillsElementModel=null;
				$scope.updateMasterRoleForm.updateSyntelX0KnowledgeAreaModel=null;
				$scope.updateMasterRoleForm.updateSyntelX0ProficiencyModel=null;
				$scope.updateMasterRoleForm.updateLongtDescModel="";
				$scope.updateMasterRoleForm.cbxUpdateIsActive = false;
            };
             $scope.ShowHideUpload = function () {
                $scope.UploadHidden = $scope.UploadHidden ? false : true;
            };
            $scope.moveTop = function()
			{
				$location.hash('PageHeading'); 
				$anchorScroll();
			};
			$scope.moveBottom = function()
			{
				$location.hash('includedFooter'); 
				$anchorScroll();
			};
		      
			
			
			
			$scope.onSaveClick = function(addMasterRoleForm) {
				$scope.onSave = true;
				if(addMasterRoleForm.$valid){
					$scope.onSave = false;
					$scope.addRole();
				}
			};
			
			$scope.onUpdateClick = function(updateMasterRoleForm) {
				$scope.onUpdate = true;
				if(updateMasterRoleForm.$valid){
					$scope.onUpdate = false;
					$scope.updateRole();
				}
			};
			
			/*$scope.onSearchClick = function(viewMasterRoleForm) {
				$scope.onSearch = true;
				if(viewMasterRoleForm.$valid){
					$scope.onSearch = false;
					$scope.viewRole();
				}
			};*/
			
			$scope.cancelClickOnAddPanal = function(addMasterRoleForm) {
				$scope.onSave = false
				$scope.addMasterRoleForm.addMasterRoleCodeModel="";
				$scope.addMasterRoleForm.addShortDescModel="";
				$scope.addMasterRoleForm.addSkillsModel=null;
				$scope.addMasterRoleForm.addPracticeModel=null;
				$scope.addMasterRoleForm.addSubPracticeModel=null;
				$scope.addMasterRoleForm.addSyntelRoleModel=null;
				$scope.addMasterRoleForm.addProficiencyLevelModel=null;
				$scope.addMasterRoleForm.addDesignation1Model=null;
				$scope.addMasterRoleForm.addDesignation2Model=null;
				$scope.addMasterRoleForm.addDesignation3Model=null;
				$scope.addMasterRoleForm.addDesignation4Model=null;
				$scope.addMasterRoleForm.addDesignation1perModel="";
				$scope.addMasterRoleForm.addDesignation2perModel="";
				$scope.addMasterRoleForm.addDesignation3perModel="";
				$scope.addMasterRoleForm.addDesignation4perModel="";
				$scope.addMasterRoleForm.addSyntelX0SkillsModel=null;
				$scope.addMasterRoleForm.addSyntelX0SkillsElementModel=null;
				$scope.addMasterRoleForm.addSyntelX0KnowledgeAreaModel=null;
				$scope.addMasterRoleForm.addSyntelX0ProficiencyModel=null;
				$scope.addMasterRoleForm.addLongtDescModel="";
			};
			
			$scope.cancelClickOnUpdatePanal = function(updateMasterRoleForm) {
				$scope.onUpdate = false;
				$scope.updateMasterRoleForm.updateMasterRoleCodeModel=null;
				$scope.updateMasterRoleForm.updateShortDescModel="";
				$scope.updateMasterRoleForm.updateSkillsModel=null;
				$scope.updateMasterRoleForm.updatePracticeModel=null;
				$scope.updateMasterRoleForm.updateSubPracticeModel=null;
				$scope.updateMasterRoleForm.updateSyntelRoleModel=null;
				$scope.updateMasterRoleForm.updateProficiencyLevelModel=null;
				$scope.updateMasterRoleForm.updateDesignation1Model=null;
				$scope.updateMasterRoleForm.updateDesignation2Model=null;
				$scope.updateMasterRoleForm.updateDesignation3Model=null;
				$scope.updateMasterRoleForm.updateDesignation4Model=null;
				$scope.updateMasterRoleForm.updateDesignation1perModel="";
				$scope.updateMasterRoleForm.updateDesignation2perModel="";
				$scope.updateMasterRoleForm.updateDesignation3perModel="";
				$scope.updateMasterRoleForm.updateDesignation4perModel="";
				$scope.updateMasterRoleForm.updateSyntelX0SkillsModel=null;
				$scope.updateMasterRoleForm.updateSyntelX0SkillsElementModel=null;
				$scope.updateMasterRoleForm.updateSyntelX0KnowledgeAreaModel=null;
				$scope.updateMasterRoleForm.updateSyntelX0ProficiencyModel=null;
				$scope.updateMasterRoleForm.updateLongtDescModel="";
				$scope.updateMasterRoleForm.cbxUpdateIsActive = false;
			};
			
			
			var getDesignation = function(response){
				$scope.designation = response.data;
				console.log($scope.designation);
			};
			WebServiceFactory.getDesignation().then(getDesignation);
			
			$scope.viewRole=function(){
				 $scope.onViewSearch=true;
				 var practiceId = 0;
				 var subPracticeId = 0;
				 if($scope.viewMasterRoleForm.viewPracticeModel != undefined && $scope.viewMasterRoleForm.viewPracticeModel != null)
					 practiceId = $scope.viewMasterRoleForm.viewPracticeModel;				 
				 
				 if($scope.viewMasterRoleForm.viewSubPracticeModel != undefined && $scope.viewMasterRoleForm.viewSubPracticeModel != null)
					 subPracticeId = $scope.viewMasterRoleForm.viewSubPracticeModel;
				 
				 var getRoles = function(response){
					$scope.rolesview = response.data;
					if(response.data != ""){
					$scope.downloadBtn = false;
					console.log("$scope.rolesview");
					console.log($scope.rolesview);
					}
					else{
						$scope.rolesview = [];
						$scope.downloadBtn = true;
						BootstrapDialog.show({
		    	        	title : 'Master Roles',
		    	        	type : BootstrapDialog.TYPE_DANGER,
		    	        	message : ' Data does not exists for the selected values.',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
	//	    	        			$window.location.reload();
		    	        		}
		    	        	}]
		    	        });
					}
				}
			WebServiceFactory.getRoles(practiceId,subPracticeId).then(getRoles);
			};
			/*$scope.viewRole=function(viewMasterRoleForm){
			var getRoles = function(response){
				$scope.roles = response.data;
				console.log(response.data);
				};
			
			WebServiceFactory.getRoles().then(getRoles);
			}*/
			
			var getUpdateMasterRoles = function(response){
				$scope.updateMRoles = response.data;
				console.log(response.data);
				};
				WebServiceFactory.getUpdateMasterRoles().then(getUpdateMasterRoles);
			
			$scope.getMasterRolesExcel = function() {
				var practiceId = 0;
				var subpracticeId = 0
				var description = "-";
				var subpracticeName = "-";
				
				if($scope.viewMasterRoleForm.viewPracticeModel != undefined && $scope.viewMasterRoleForm.viewPracticeModel != null)
				{
					practiceId = $scope.viewMasterRoleForm.viewPracticeModel;
					description  = $.grep($scope.practiceroles, function (practiceroles) {
		                 return practiceroles.practiceId == practiceId;
		             })[0].description;
				}
				 
				if($scope.viewMasterRoleForm.viewSubPracticeModel != undefined && $scope.viewMasterRoleForm.viewSubPracticeModel != null)
				{
					subpracticeId = $scope.viewMasterRoleForm.viewSubPracticeModel;
					subpracticeName  = $.grep($scope.subpracticeview, function (subpracticeview) {
						return subpracticeview.subpracticeId == subpracticeId;
	             	})[0].subpracticeName;
				}				 
				
				window.location= contextPath+"/RightPrice-DAS/downloadMasterRolesExcel/"+practiceId+"/"+description+"/"+subpracticeId+"/"+subpracticeName
			};
			
			
			
			
			
			var getSkills = function(response){
				$scope.skills = response.data;
				console.log("$scope.skills");
				console.log($scope.skills);
			};
			WebServiceFactory.getSkills().then(getSkills);
			
			var getPracticeRoles = function(response){
				$scope.practiceroles = response.data;
				console.log("$scope.practiceroles");
				console.log($scope.practiceroles);
			};
		
			WebServiceFactory.getPracticeRoles().then(getPracticeRoles);
			
			var getXOSkillsRoles = function(response){
				$scope.xoskills = response.data;
				console.log("$scope.xoskills");
				console.log($scope.xoskills);
			};
			WebServiceFactory.getXOSkillsRoles().then(getXOSkillsRoles);
			
			var getSyntelRoles = function(response){
				$scope.syntelroles = response.data;
			};
			WebServiceFactory.getSyntelRoles().then(getSyntelRoles);
			
			var getProficiency = function(response){
				$scope.proficiency = response.data;
			};
			WebServiceFactory.getProficiency().then(getProficiency);
			
			$scope.getSkillElementAdd = function(skillId){
			var getXOSkillElementRoles = function(response){
				$scope.xoskillelementAdd = response.data;
			};
			WebServiceFactory.getXOSkillElementRoles(skillId).then(getXOSkillElementRoles);
			}
			
			$scope.getSkillElementUpdate = function(skillId){
			var getXOSkillElementRoles = function(response){
				$scope.xoskillelementUpdate = response.data;
			};
			WebServiceFactory.getXOSkillElementRoles(skillId).then(getXOSkillElementRoles);
			}
			
			var getKnowledgeNameRole = function(response){
				$scope.knowledgename = response.data;
			};
			WebServiceFactory.getKnowledgeNameRole().then(getKnowledgeNameRole);
			
			$scope.getSubPracticeAdd = function(practiceId){
			var getSubPracticeRole = function(response){
				$scope.subpracticeAdd = response.data;
			};
			WebServiceFactory.getSubPracticeRole(practiceId).then(getSubPracticeRole);
			}
			
			$scope.getSubPracticeUpdate = function(practiceId){
				var getSubPracticeRole = function(response){
					$scope.subpracticeUpdate = response.data;
				};
				WebServiceFactory.getSubPracticeRole(practiceId).then(getSubPracticeRole);
				}
			
			$scope.getSubPracticeView = function(practiceId){
				var getSubPracticeRole = function(response){
					$scope.subpracticeview = response.data;
				};
				WebServiceFactory.getSubPracticeRole(practiceId).then(getSubPracticeRole);
				}
//			$scope.cityCategorizationRate=[];
			var getCountry = function(response) {
				$scope.country = response.data;
				/*angular.forEach($scope.country,function(value,key){
					$scope.addCityCatRateRow(value);
				});
				//for each country
			};
			
			$scope.addCityCatRateRow = function(value){
				$scope.cityCategorizationRate.push({
					"countryId":value.countryId,
					"countryName":value.countryName,
					"currencyId":value.currencyId,
					"currencyCode":value.currencyCode,
					"rateOffshore":0.0,
					"rateLow":0.0,
					"rateMedium":0.0,
					"rateHigh":0.0,
					"rateVHigh":0.0
				});*/
			};
			WebServiceFactory.getCountry().then(getCountry);
			
			
			$scope.getRoleDetails = function(masterRoleId){
				var getRolesDetails = function(response){
					$scope.rolesdetails = response.data;
					/*$scope.roleUpdateDetails = response.data;*/
					console.log($scope.rolesdetails);
					console.log("roleUpdateDetails");
					/*console.log($scope.roleUpdateDetails);*/
				};
				WebServiceFactory.getRolesDetails(masterRoleId).then(getRolesDetails);
				}
			
			$scope.getUpdateRoles = function(masterRoleId){
				var getUpdatesRolesDetails = function(response){
					$scope.roleUpdateDetails = response.data;
					console.log("roleUpdateDetails");
					console.log($scope.roleUpdateDetails);
					if($scope.roleUpdateDetails.length > 0)
						$scope.putroleDetails();
					/*else
						alert("Angularjs sys NOOOOOOOOOOOOOO");*/
				};
				WebServiceFactory.getRolesDetails(masterRoleId).then(getUpdatesRolesDetails);
				
			}
			
			 $scope.putLongtDesc = function(){
				 if($scope.addMasterRoleForm.addPracticeModel != undefined 
						 && $scope.addMasterRoleForm.addSubPracticeModel != undefined 
						 && $scope.addMasterRoleForm.addSyntelRoleModel != undefined 
						 && $scope.addMasterRoleForm.addProficiencyLevelModel != undefined){
					 $scope.addMasterRoleForm.addLongtDescModel = $scope.addMasterRoleForm.addPracticeModel.description +"/"+ 
					 $scope.addMasterRoleForm.addSubPracticeModel.subpracticeName + "/" + 
					 $scope.addMasterRoleForm.addSyntelRoleModel.syntelRoleName +"/" +
					 $scope.addMasterRoleForm.addProficiencyLevelModel.description;
				 }
			}
			
			
			 
			$scope.addRole = function()
			{
				var markers = 
				{
						"masterRoleName":$scope.addMasterRoleForm.addMasterRoleCodeModel,
						"masterRoleShortDescription":$scope.addMasterRoleForm.addShortDescModel,
						"skillsId":$scope.addMasterRoleForm.addSkillsModel.id,
						"skillDescription":$scope.addMasterRoleForm.addSkillsModel.description,
						"subPracticeId":$scope.addMasterRoleForm.addSubPracticeModel.subpracticeId,
						"syntelRoleId":$scope.addMasterRoleForm.addSyntelRoleModel.syntelRoleId,
						"syntelRoleDescription":$scope.addMasterRoleForm.addSyntelRoleModel.syntelRoleName,
						"proficiencyLevelId":$scope.addMasterRoleForm.addProficiencyLevelModel.id,
						"proficiencyLevelDescription":$scope.addMasterRoleForm.addProficiencyLevelModel.description,
						"designationId":$scope.addMasterRoleForm.addDesignation1Model,
						"designationId2":$scope.addMasterRoleForm.addDesignation2Model,
						"designationId3":$scope.addMasterRoleForm.addDesignation3Model,
						"designationId4":$scope.addMasterRoleForm.addDesignation4Model,
						"designation1Percent":$scope.addMasterRoleForm.addDesignation1perModel,
						"designation2Percent":$scope.addMasterRoleForm.addDesignation2perModel,
						"designation3Percent":$scope.addMasterRoleForm.addDesignation3perModel,
						"designation4Percent":$scope.addMasterRoleForm.addDesignation4perModel,
						"x0SkillId":$scope.addMasterRoleForm.addSyntelX0SkillsModel.skillId,
						"x0SkillDescription":$scope.addMasterRoleForm.addSyntelX0SkillsModel.skillName,
						"x0SkillElementId":$scope.addMasterRoleForm.addSyntelX0SkillsElementModel.skillElementId,
						"x0SkillElementDescription":$scope.addMasterRoleForm.addSyntelX0SkillsElementModel.elementName,
						"x0KnowledgeAreaId":$scope.addMasterRoleForm.addSyntelX0KnowledgeAreaModel.knowledgeId,
						"x0KnowledgeAreaDescription":$scope.addMasterRoleForm.addSyntelX0KnowledgeAreaModel.knowledgeName,
						"x0ProficiencyId":$scope.addMasterRoleForm.addSyntelX0ProficiencyModel.codeName,
						"x0ProficiencyDescription":$scope.addMasterRoleForm.addSyntelX0ProficiencyModel.description,
						"masterRoleLongDescription":$scope.addMasterRoleForm.addLongtDescModel,
//						"masterRate":$scope.cityCategorizationRate
				}
				console.log(markers);
				var addRole = function(response) 
				{
					var customMessage = response.data;
					console.log(response)
					
					if(response.status == 200){	
		    	        BootstrapDialog.show({
		    	        	title : 'Master Roles',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : 'Data Saved SucessFully',
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MasterRoles";
		    	        		}
		    	        	}]
		    	        });
		        	}
		    		else if(response.status == 203)
		    		{	
		    	        BootstrapDialog.show({
		    	        	title : 'Master Roles',
		    	        	type : BootstrapDialog.TYPE_PRIMARY,
		    	        	message : customMessage,
		    	        	closable : false,
		    	        	buttons : [{
		    	        		label : 'OK',
		    	        		action : function(dialogRef) {
		    	        			dialogRef.close();
		    	        			window.location = "MasterRoles";
		    	        		}
		    	        	}]
		    	        });
		        	}
		        	else 
		        	{
						BootstrapDialog.show({
						title : 'Master Roles',
						type : BootstrapDialog.TYPE_DANGER,
						message : "Currently We are facing technical issues, please try again later.",
						closable : false,
						buttons : [ {
							label : 'OK',
							action : function(dialogRef) {
								dialogRef.close();
								window.location = "MasterRoles";
							}
						} ]
						});
		        	}
				}
				WebServiceFactory.addRole(markers).then(addRole);
			};
		
		$scope.updateRole = function(){
			var checkboxvalIsActive = $scope.updateMasterRoleForm.cbxUpdateIsActive;
			var isActive = 0;
			if(checkboxvalIsActive) {
				isActive = 1;
			} else {
				isActive = 0;
			}
			var markers = {
					"masterRoleName":$("#ddlUpdateMasterRoleCode option:Selected").html(),
					"masterRoleShortDescription":$scope.updateMasterRoleForm.updateShortDescModel,
					"skillsId":$scope.updateMasterRoleForm.updateSkillsModel,
					"skillDescription":$("#ddlUpdateSkills option:Selected").html(),
					"subPracticeId":$scope.updateMasterRoleForm.updateSubPracticeModel,
					"syntelRoleId":$scope.updateMasterRoleForm.updateSyntelRoleModel,
					"syntelRoleDescription":$("#ddlUpdateSyntelRole option:Selected").html(),
					"proficiencyLevelId":$scope.updateMasterRoleForm.updateProficiencyLevelModel,
					"proficiencyLevelDescription":$("#ddlUpdateProficiencyLevel option:Selected").html(),
					"designationId":$scope.updateMasterRoleForm.updateDesignation1Model,
					"designationId2":$scope.updateMasterRoleForm.updateDesignation2Model,
					"designationId3":$scope.updateMasterRoleForm.updateDesignation3Model,
					"designationId4":$scope.updateMasterRoleForm.updateDesignation4Model,
					"designation1Percent":$scope.updateMasterRoleForm.updateDesignation1perModel,
					"designation2Percent":$scope.updateMasterRoleForm.updateDesignation2perModel,
					"designation3Percent":$scope.updateMasterRoleForm.updateDesignation3perModel,
					"designation4Percent":$scope.updateMasterRoleForm.updateDesignation4perModel,
					"x0SkillId":$scope.updateMasterRoleForm.updateSyntelX0SkillsModel,
					"x0SkillDescription":$("#ddlUpdateSyntelX0Skills option:Selected").html(),
					"x0SkillElementId":$scope.updateMasterRoleForm.updateSyntelX0SkillsElementModel,
					"x0SkillElementDescription":$("#ddlUpdateSyntelX0SkillsElement option:Selected").html(),
					"x0KnowledgeAreaId":$scope.updateMasterRoleForm.updateSyntelX0KnowledgeAreaModel,
					"x0KnowledgeAreaDescription":$("#ddlUpdateSyntelX0KnowledgeArea option:Selected").html(),
					"x0ProficiencyId":$scope.updateMasterRoleForm.updateSyntelX0ProficiencyModel,
					"x0ProficiencyDescription":$("#ddlUpdateSyntelX0Proficiency option:Selected").html(),
					"masterRoleLongDescription":$scope.updateMasterRoleForm.updateLongtDescModel,
					"isActive":isActive
			}
			console.log($scope.updateMasterRoleForm.updateShortDescModel);
			console.log($scope.updateMasterRoleForm.updateLongtDescModel);
			console.log(markers);
			var updateRole = function(response) {
	   	        BootstrapDialog.show({
	   	        	title : 'Master Roles',
	   	        	type : BootstrapDialog.TYPE_PRIMARY,
	   	        	message : 'Updated SucessFully',
	   	        	closable : false,
	   	        	buttons : [{
	   	        		label : 'OK',
	   	        		action : function(dialogRef) {
	   	        			dialogRef.close();
	   	        			window.location = "MasterRoles";
	   	        		}
	   	        	}]
	   	        });
			}
			WebServiceFactory.updateRole(markers).then(updateRole);
	};

	$scope.putroleDetails = function(){
		
		//$scope.getRoleDetails(masterRoleId);
		
		$scope.updateMasterRoleForm.updateShortDescModel = $scope.roleUpdateDetails[0].masterRoleShortDescription;
		$scope.updateMasterRoleForm.updateSkillsModel = $scope.roleUpdateDetails[0].skillsId;
		$scope.getSubPracticeUpdate($scope.roleUpdateDetails[0].masterSubPractice.practiceId);
		$scope.updateMasterRoleForm.updatePracticeModel = $scope.roleUpdateDetails[0].masterSubPractice.practiceId;
		$scope.updateMasterRoleForm.updateSubPracticeModel = $scope.roleUpdateDetails[0].subPracticeId;
		$scope.updateMasterRoleForm.updateSyntelRoleModel= $scope.roleUpdateDetails[0].syntelRoleId;	
		$scope.updateMasterRoleForm.updateProficiencyLevelModel= $scope.roleUpdateDetails[0].proficiencyLevelId;	
		$scope.updateMasterRoleForm.updateDesignation1Model= $scope.roleUpdateDetails[0].designationId;
		$scope.updateMasterRoleForm.updateDesignation2Model= $scope.roleUpdateDetails[0].designationId2;
		$scope.updateMasterRoleForm.updateDesignation3Model= $scope.roleUpdateDetails[0].designationId3;
		$scope.updateMasterRoleForm.updateDesignation4Model= $scope.roleUpdateDetails[0].designationId4;
		$scope.updateMasterRoleForm.updateDesignation1perModel= $scope.roleUpdateDetails[0].designation1Percent;
		$scope.updateMasterRoleForm.updateDesignation2perModel= $scope.roleUpdateDetails[0].designation2Percent;
		$scope.updateMasterRoleForm.updateDesignation3perModel= $scope.roleUpdateDetails[0].designation3Percent;
		$scope.updateMasterRoleForm.updateDesignation4perModel= $scope.roleUpdateDetails[0].designation4Percent;
		if($scope.roleUpdateDetails[0].x0SkillId != null)
		$scope.getSkillElementUpdate($scope.roleUpdateDetails[0].x0SkillId);	
		$scope.updateMasterRoleForm.updateSyntelX0SkillsModel= $scope.roleUpdateDetails[0].x0SkillId;
		$scope.updateMasterRoleForm.updateSyntelX0SkillsElementModel= $scope.roleUpdateDetails[0].x0SkillElementId;
		$scope.updateMasterRoleForm.updateSyntelX0KnowledgeAreaModel= $scope.roleUpdateDetails[0].x0KnowledgeAreaId;
		$scope.updateMasterRoleForm.updateSyntelX0ProficiencyModel= $scope.roleUpdateDetails[0].x0ProficiencyId;
		$scope.updateMasterRoleForm.updateLongtDescModel = $scope.roleUpdateDetails[0].masterRoleLongDescription
		if($scope.roleUpdateDetails[0].isActive != "InActive") {
			$scope.updateMasterRoleForm.cbxUpdateIsActive = true;
		} 
		else {
			$scope.updateMasterRoleForm.cbxUpdateIsActive = false;
		}
	};
	
	 $scope.putLongtDescUpdate = function(){
		if($("#ddlUpdatePractice option:Selected").html() != undefined 
				 && $("#ddlUpdateSubPractice option:Selected").html()!= undefined 
				 && $("#ddlUpdateSyntelRole option:Selected").html() != undefined 
				 && $("#ddlUpdateProficiencyLevel option:Selected").html() != undefined){
				 $scope.updateMasterRoleForm.updateLongtDescModel = $("#ddlUpdatePractice option:Selected").html() +"/"+ 
				 $("#ddlUpdateSubPractice option:Selected").html() +"/"+ 
				 $("#ddlUpdateSyntelRole option:Selected").html() +"/" +
				 $("#ddlUpdateProficiencyLevel option:Selected").html();
		}
	}
	
	/* $scope.putLongtDescUpdate = function(){
		 console.log("======");
		 console.log($scope.practicetextvalue);
		 console.log($scope.subpracticetextvalue);
		if($scope.practicetextvalue != undefined 
				 && $scope.subpracticetextvalue != undefined 
				 && $scope.syntelroletextvalue != undefined 
				 && $scope.proficiencytextvalue != undefined){
				 $scope.updateMasterRoleForm.updateLongtDescModel = $scope.practicetextvalue +"/"+ 
				 $scope.subpracticetextvalue + "/" + 
				 $scope.syntelroletextvalue +"/" +
				 $scope.proficiencytextvalue;
		}
	}
	 
	$scope.getPracticeTextValue = function(index){
		$scope.practicetextvalue = " ";
		angular.forEach($scope.practiceroles, function (value, key) {
    		if(value.practiceId == index){
    			$scope.practicetextvalue = value.description;
    		}
    	});
		alert($scope.practicetextvalue);
	};
	
	$scope.getSubPracticeTextValue = function(index){
		$scope.subpracticetextvalue != " ";
		angular.forEach($scope.subpracticeUpdate, function (value, key) {
    		if(value.subpracticeId == index){
    			$scope.subpracticetextvalue = value.subpracticeName;	
    		}
    	});
		alert($scope.subpracticetextvalue);
	};
	
	$scope.getSyntelRoleTextValue = function(index){
		$scope.syntelroletextvalue = " ";
		angular.forEach($scope.syntelroles, function (value, key) {
    		if(value.syntelRoleId == index){
    			$scope.syntelroletextvalue = value.syntelRoleName;
    		}
    	});
		alert($scope.syntelroletextvalue);
	};
	
	$scope.getProficiencyTextValue = function(index){
		$scope.proficiencytextvalue = " ";
		angular.forEach($scope.proficiency, function (value, key) {
    		if(value.codeName == index){
    			$scope.proficiencytextvalue = value.description;
    		}
    	});
		alert($scope.proficiencytextvalue);
	};*/

}]);