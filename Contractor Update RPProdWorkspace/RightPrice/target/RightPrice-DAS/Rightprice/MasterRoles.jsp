<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Eviden RightPrice Portal</title>
    <meta charset="utf-8">
<meta name="csrf-token" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
   <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/SAPStyleSheet.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.js"></script> 	
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/bootstrap-dialog.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
    <script src="${contextPath}/resources/js/jquery.validate.js"></script>
    <script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <script src="${contextPath}/resources/js/additional-methods.js"></script>
    <script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/MasterRoles.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterRolesController" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left"  id="PageHeading">Master - Roles</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
						<ng-form name="viewMasterRoleForm" id="viewMasterRoleForm" >
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">View Master Roles</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight ">Practice</label><!-- required-Field -->
                                        <div class="col-sm-3">
                                           <select id="ddlViewPractice" class="form-control" 
                                           		   ng-model="viewMasterRoleForm.viewPracticeModel" 
                                           		   placeholder="Please select" name="ddlViewPractice"
                                           		   ng-options="pra.practiceId as pra.description for pra in practiceroles| orderBy:'description'" 
                                           		   ng-change="getSubPracticeView(viewMasterRoleForm.viewPracticeModel);">
												<option value="" selected disabled>Please select</option>
											</select>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight ">Sub Practice</label><!-- required-Field -->
                                        <div class="col-sm-3">
                                           <select id="ddlViewSubPractice" class="form-control" 
                                                   ng-model="viewMasterRoleForm.viewSubPracticeModel"
                                                   placeholder="Please select" name="ddlViewSubPractice"
                                                   ng-options="subpra.subpracticeId as subpra.subpracticeName for subpra in subpracticeview| orderBy:'subpracticeName'" >
												<option value="" selected disabled>Please select</option>
											</select>
                                        </div>
									</div>
									
									 <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-click="viewRole();">Search</button><!-- ng-click="viewRole(viewMasterRoleForm.$valid); -->
											<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewMasterRoles" ng-click ="getMasterRolesExcel();" ng-disabled=downloadBtn>Download Master Roles</button>	
										</div>
									</div>
                                    <div class="divEmptyThrice"></div>
									<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover tableBottomMargin"
													id="tblViewMasterRoles">
													<thead>
														<tr align="char">
															<th>Master Role Code</th>
															<th>Short Description</th>
															<th>Practice</th>
															<th>Sub Practice</th>
															<th>Syntel Role</th>
															<th>Proficiency Level</th>
															<th>Details</th>
														</tr>
													</thead>
													<tbody id="tBody" >
														<tr id="{{'trMasterRolesView'+'_'+($index+1)}}" ng-repeat="row in rolesview">
															<td class="tdTextAlignLeft">{{(row.masterRoleName)}}</td>
															<td class="tdTextAlignLeft">{{(row.masterRoleShortDescription)}}</td>
															<td class="tdTextAlignLeft">{{(row.masterSubPractice.masterPractice.description)}}</td>
															<td class="tdTextAlignLeft">{{(row.masterSubPractice.subpracticeName)}}</td>
															<td class="tdTextAlignLeft">{{(row.masterSyntelRoles.syntelRoleName)}}</td>
															<td class="tdTextAlignLeft">{{(row.proficiencyLevelDescription)}}</td>
															<td><a href="" data-toggle="modal" data-target="#modalRDetials" ng-click="getRoleDetails(row.masterRoleId);">Details</a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="addMasterRoleForm" id="addMasterRoleForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Master Role</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                	<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Master Role Code</label>
                                        <div class="col-sm-3">
                                           <input type="text" id="txtAddMasterRoleCode" class="form-control" name="txtAddMasterRoleCode"
                                           		   ng-model="addMasterRoleForm.addMasterRoleCodeModel" 
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.txtAddMasterRoleCode.$invalid)]" required>
                                           	<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.txtAddMasterRoleCode.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Master Role Code!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Master Role Short Description</label>
                                        <div class="col-sm-3">
                                           <input type="text" id="txtAddShortDesc" class="form-control" name="txtAddShortDesc"
                                           		   ng-model="addMasterRoleForm.addShortDescModel" 
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.txtAddShortDesc.$invalid)]" required>
                                           	<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.txtAddShortDesc.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Role Short Description!</em>
									        </div>
                                        </div>                                        
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Skills</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddSkills" class="form-control" placeholder="Please select" name="ddlAddSkills"
                                           		   ng-model="addMasterRoleForm.addSkillsModel" ng-options="ski as ski.description for ski in skills| orderBy:'description'"
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddSkills.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddSkills.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Skills!</em>
									        </div>
                                        </div>
                                	</div>                                     
                                	<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Practice</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddPractice" class="form-control" placeholder="Please select" name="ddlAddPractice"
                                           		   ng-model="addMasterRoleForm.addPracticeModel" ng-options="pra as pra.description for pra in practiceroles| orderBy:'description'" 
                                           		   ng-change="getSubPracticeAdd(addMasterRoleForm.addPracticeModel.practiceId);putLongtDesc();"  
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddPractice.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Practice!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Sub Practice</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddSubPractice" class="form-control" placeholder="Please select" name="ddlAddSubPractice"
                                           		   ng-model="addMasterRoleForm.addSubPracticeModel" ng-options="subpra as subpra.subpracticeName for subpra in subpracticeAdd| orderBy:'subpracticeName'"
                                           		   ng-change="putLongtDesc();" ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddSubPractice.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddSubPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Sub Practice!</em>
									        </div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Syntel Role</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddSyntelRole" class="form-control" placeholder="Please select" name="ddlAddSyntelRole"
                                            		ng-model="addMasterRoleForm.addSyntelRoleModel" ng-options="synr as synr.syntelRoleName for synr in syntelroles| orderBy:'syntelRoleName'"
                                            		ng-change="putLongtDesc();" ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddSyntelRole.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddSyntelRole.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Syntel Role!</em>
									        </div>
                                        </div>
                                    	<label class="control-label col-sm-3 textAlignRight required-Field">Proficiency Level</label>
                                        <div class="col-sm-3">
                                        	<select id="ddlAddProficiencyLevel" class="form-control" placeholder="Please select" name="ddlAddProficiencyLevel"
                                        			ng-model="addMasterRoleForm.addProficiencyLevelModel" ng-options="Profi as Profi.description for Profi in proficiency| orderBy:'description'" 
                                        			ng-change="putLongtDesc();" ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddProficiencyLevel.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddProficiencyLevel.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Proficiency Level!</em>
									        </div>
                                        </div>
                                    </div>
                                	<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Designation 1</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddDesignation1" class="form-control" placeholder="Please select" name="ddlAddDesignation1"
                                            		ng-model="addMasterRoleForm.addDesignation1Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddDesignation1.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddDesignation1.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Designation 1%</label>
                                        <div class="col-sm-3">
											 <input type="text" id="txtAddDesignation1per" class="form-control" name="txtAddDesignation1per"
                                           		   ng-model="addMasterRoleForm.addDesignation1perModel" 
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.txtAddDesignation1per.$invalid)]" required 
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.txtAddDesignation1per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 1%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight ">Designation 2</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddDesignation2" class="form-control" placeholder="Please select" name="ddlAddDesignation2"
                                            		ng-model="addMasterRoleForm.addDesignation2Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddDesignation2.$invalid)]" >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddDesignation2.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight ">Designation 2%</label>
                                        <div class="col-sm-3">
                                            <input type="text" id="txtAddDesignation2per" class="form-control" name="txtAddDesignation2per"
                                           		   ng-model="addMasterRoleForm.addDesignation2perModel" 
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.txtAddDesignation2per.$invalid)]"
                                           		   ng-required="addMasterRoleForm.addDesignation2Model" 
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.txtAddDesignation2per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 2%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div> 
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight ">Designation 3</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddDesignation3" class="form-control" placeholder="Please select" name="ddlAddDesignation3"
                                            		ng-model="addMasterRoleForm.addDesignation3Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddDesignation3.$invalid)]" >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddDesignation3.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight ">Designation 3%</label>
                                        <div class="col-sm-3">
                                            <input type="text" id="txtAddDesignation3per" class="form-control" name="txtAddDesignation3per"
                                           		   ng-model="addMasterRoleForm.addDesignation3perModel"  
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.txtAddDesignation3per.$invalid)]" 
                                           		   ng-required="addMasterRoleForm.addDesignation3Model"
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.txtAddDesignation3per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 3%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight ">Designation 4</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddDesignation4" class="form-control" placeholder="Please select" name="ddlAddDesignation4"
                                            		ng-model="addMasterRoleForm.addDesignation4Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddDesignation4.$invalid)]" >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddDesignation4.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight ">Designation 4%</label>
                                        <div class="col-sm-3">
                                            <input type="text" id="txtAddDesignation4per" class="form-control" name="txtAddDesignation4per"
                                           		   ng-model="addMasterRoleForm.addDesignation4perModel"  
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.txtAddDesignation4per.$invalid)]" 
                                           		   ng-required="addMasterRoleForm.addDesignation4Model"
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.txtAddDesignation4per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 4%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
                                        </div>
                                    </div>  
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">X0 Skills</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddSyntelX0Skills" class="form-control" placeholder="Please select" name="ddlAddSyntelX0Skills"
                                            		ng-model="addMasterRoleForm.addSyntelX0SkillsModel" ng-options="xo as xo.skillName for xo in xoskills| orderBy:'skillName'"  ng-change="getSkillElementAdd(addMasterRoleForm.addSyntelX0SkillsModel.skillId);"
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddSyntelX0Skills.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddSyntelX0Skills.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Skills!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">X0 Skill Element</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddSyntelX0SkillsElement" class="form-control" placeholder="Please select" name="ddlAddSyntelX0SkillsElement"
                                            		ng-model="addMasterRoleForm.addSyntelX0SkillsElementModel" ng-options="xoe as xoe.elementName for xoe in xoskillelementAdd| orderBy:'elementName'"
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddSyntelX0SkillsElement.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddSyntelX0SkillsElement.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Skill Element!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">X0 Knowledge Area</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddSyntelX0KnowledgeArea" class="form-control" placeholder="Please select" name="ddlAddSyntelX0KnowledgeArea"
                                           		   ng-model="addMasterRoleForm.addSyntelX0KnowledgeAreaModel" ng-options="xok as xok.knowledgeName for xok in knowledgename| orderBy:'knowledgeName'"
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddSyntelX0KnowledgeArea.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddSyntelX0KnowledgeArea.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Knowledge Area!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">X0 Proficiency</label>
                                         <div class="col-sm-3">
                                        	<select id="ddlAddSyntelX0Proficiency" class="form-control" placeholder="Please select" name="ddlAddSyntelX0Proficiency"
                                        			ng-model="addMasterRoleForm.addSyntelX0ProficiencyModel" ng-options="Profi as Profi.description for Profi in proficiency| orderBy:'description'"
                                        			ng-class="{true: 'ng-border'}[(onSave && addMasterRoleForm.ddlAddSyntelX0Proficiency.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterRoleForm.ddlAddSyntelX0Proficiency.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Proficiency!</em>
									        </div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight">Master Role Long Description</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="txtAddLongtDesc" class="form-control" name="txtAddLongtDesc"ng-model="addMasterRoleForm.addLongtDescModel" readonly>
                                        </div>
                                	</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="onSaveClick(addMasterRoleForm);">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="cancelClickOnAddPanal(addMasterRoleForm);">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                 <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="updateMasterRoleForm" id="updateMasterRoleForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Master Role</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Master Role Code</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateMasterRoleCode" class="form-control" placeholder="Please select" name="ddlUpdateMasterRoleCode"
                                           		   ng-model="updateMasterRoleForm.updateMasterRoleCodeModel" ng-options="rc.masterRoleId as rc.masterRoleName for rc in updateMRoles| orderBy:'masterRoleName'"
                                           		   ng-change="getUpdateRoles(updateMasterRoleForm.updateMasterRoleCodeModel);"
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateMasterRoleCode.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateMasterRoleCode.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Master Role!</em>
									        </div>
                                        </div>
                                         <label class="control-label col-sm-3 textAlignRight required-Field">Master Role Short Description</label>
                                        <div class="col-sm-3">
                                           <input type="text" id="txtUpdateShortDesc" class="form-control" name="txtUpdateShortDesc"
                                           		   ng-model="updateMasterRoleForm.updateShortDescModel"  
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.txtUpdateShortDesc.$invalid)]" required>
                                           	<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.txtUpdateShortDesc.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Role Short Description!</em>
									        </div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Skills</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateSkills" class="form-control" placeholder="Please select" name="ddlUpdateSkills"
                                           			ng-model="updateMasterRoleForm.updateSkillsModel" ng-options="ski.codeName as ski.description for ski in skills| orderBy:'description'"
                                           			ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateSkills.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateSkills.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Skills!</em>
									        </div>
                                        </div>
                                	</div> 
                                	<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Practice</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdatePractice" class="form-control" placeholder="Please select" name="ddlUpdatePractice"
                                           		   ng-model="updateMasterRoleForm.updatePracticeModel" ng-options="prac.practiceId as prac.description for prac in practiceroles| orderBy:'description'" 
                                           		   ng-change="getSubPracticeUpdate(updateMasterRoleForm.updatePracticeModel); putLongtDescUpdate(); getPracticeTextValue(updateMasterRoleForm.updatePracticeModel);"
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdatePractice.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdatePractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Practice!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Sub Practice</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateSubPractice" class="form-control" placeholder="Please select" name="ddlUpdateSubPractice"
                                           		   ng-model="updateMasterRoleForm.updateSubPracticeModel" ng-options="subpra.subpracticeId as subpra.subpracticeName for subpra in subpracticeUpdate| orderBy:'subpracticeName'" ng-change="putLongtDescUpdate(); getSubPracticeTextValue(updateMasterRoleForm.updateSubPracticeModel);"
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateSubPractice.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateSubPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Sub Practice!</em>
									        </div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Syntel Role</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateSyntelRole" class="form-control" placeholder="Please select" name="ddlUpdateSyntelRole"
                                            		ng-model="updateMasterRoleForm.updateSyntelRoleModel" ng-options="synr.syntelRoleId as synr.syntelRoleName for synr in syntelroles| orderBy:'syntelRoleName'" ng-change="putLongtDescUpdate();getSyntelRoleTextValue(updateMasterRoleForm.updateSyntelRoleModel);"  
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateSyntelRole.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateSyntelRole.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Syntel Role!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Proficiency Level</label>
                                         <div class="col-sm-3">
                                        	<select id="ddlUpdateProficiencyLevel" class="form-control" placeholder="Please select" name="ddlUpdateProficiencyLevel"
                                        			ng-model="updateMasterRoleForm.updateProficiencyLevelModel" ng-options="Profi.codeName as Profi.description for Profi in proficiency| orderBy:'description'" ng-change="putLongtDescUpdate();getProficiencyTextValue(updateMasterRoleForm.updateProficiencyLevelModel);"
                                        			ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateProficiencyLevel.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateProficiencyLevel.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Proficiency Level!</em>
									        </div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Designation 1</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateDesignation1" class="form-control" placeholder="Please select" name="ddlUpdateDesignation1"
                                            		ng-model="updateMasterRoleForm.updateDesignation1Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'"
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateDesignation1.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateDesignation1.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Designation 1%</label>
                                        <div class="col-sm-3">
											<input type="text" id="txtUpdateDesignation1per" class="form-control" name="txtUpdateDesignation1per"
                                           		   ng-model="updateMasterRoleForm.updateDesignation1perModel"  
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.txtUpdateDesignation1per.$invalid)]" required
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.txtUpdateDesignation1per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 1%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight ">Designation 2</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateDesignation2" class="form-control" placeholder="Please select" name="ddlUpdateDesignation2"
                                            		ng-model="updateMasterRoleForm.updateDesignation2Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'"
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateDesignation2.$invalid)]"  >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateDesignation2.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight ">Designation 2%</label>
                                        <div class="col-sm-3">
                                           <input type="text" id="txtUpdateDesignation2per" class="form-control" name="txtUpdateDesignation2per"
                                           		   ng-model="updateMasterRoleForm.updateDesignation2perModel"  
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.txtUpdateDesignation2per.$invalid)]"
                                           		   ng-required="updateMasterRoleForm.updateDesignation2Model" 
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.txtUpdateDesignation2per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 2%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight ">Designation 3</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateDesignation3" class="form-control" placeholder="Please select" name="ddlUpdateDesignation3"
                                            		ng-model="updateMasterRoleForm.updateDesignation3Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'"
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateDesignation3.$invalid)]" >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateDesignation3.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>									        	
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight ">Designation 3%</label>
                                        <div class="col-sm-3">
                                            <input type="text" id="txtUpdateDesignation3per" class="form-control" name="txtUpdateDesignation3per"
                                           		   ng-model="updateMasterRoleForm.updateDesignation3perModel"  
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.txtUpdateDesignation3per.$invalid)]" 
                                           		   ng-required="updateMasterRoleForm.updateDesignation3Model"
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.txtUpdateDesignation3per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 3%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight ">Designation 4</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateDesignation4" class="form-control" placeholder="Please select" name="ddlUpdateDesignation4"
                                            		ng-model="updateMasterRoleForm.updateDesignation4Model" ng-options="desi.empDesignationId as desi.empDesignationDescription for desi in designation| orderBy:'empDesignationDescription'"
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateDesignation4.$invalid)]" >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateDesignation4.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Designation!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight ">Designation 4%</label>
                                        <div class="col-sm-3">
                                            <input type="text" id="txtUpdateDesignation4per" class="form-control" name="txtUpdateDesignation4per"
                                           		   ng-model="updateMasterRoleForm.updateDesignation4perModel"  
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.txtUpdateDesignation4per.$invalid)]" 
                                           		   ng-required="updateMasterRoleForm.updateDesignation4Model"
                                           		   ng-pattern="numberRegex" checklthundredpercente>
                                           	<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.txtUpdateDesignation4per.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Designation 4%!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">X0 Skills</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateSyntelX0Skills" class="form-control" placeholder="Please select" 
                                            		name="ddlUpdateSyntelX0Skills" ng-model="updateMasterRoleForm.updateSyntelX0SkillsModel" ng-options="xo.skillId as xo.skillName for xo in xoskills| orderBy:'skillName'" ng-change="getSkillElementUpdate(updateMasterRoleForm.updateSyntelX0SkillsModel);"
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateSyntelX0Skills.$invalid)]"  required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateSyntelX0Skills.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Skills!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">X0 Skill Element</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateSyntelX0SkillsElement" class="form-control" placeholder="Please select" 
                                            		name="ddlUpdateSyntelX0SkillsElement" ng-model="updateMasterRoleForm.updateSyntelX0SkillsElementModel" ng-options="xoe.skillElementId as xoe.elementName for xoe in xoskillelementUpdate| orderBy:'elementName'"
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateSyntelX0SkillsElement.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateSyntelX0SkillsElement.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Skill Element!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">X0 Knowledge Area</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateSyntelX0KnowledgeArea" class="form-control" placeholder="Please select" 
                                           		   name="ddlUpdateSyntelX0KnowledgeArea" ng-model="updateMasterRoleForm.updateSyntelX0KnowledgeAreaModel" ng-options="xok.knowledgeId as xok.knowledgeName for xok in knowledgename| orderBy:'knowledgeName'"
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateSyntelX0KnowledgeArea.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateSyntelX0KnowledgeArea.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Knowledge Area!</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">X0 Proficiency</label>
                                         <div class="col-sm-3">
                                        	<select id="ddlUpdateSyntelX0Proficiency" class="form-control" placeholder="Please select"
                                        			name="ddlUpdateSyntelX0Proficiency" ng-model="updateMasterRoleForm.updateSyntelX0ProficiencyModel" ng-options="Profi.id as Profi.description for Profi in proficiency| orderBy:'description'"
                                        			ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.ddlUpdateSyntelX0Proficiency.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.ddlUpdateSyntelX0Proficiency.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select X0 Proficiency!</em>
									        </div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight">Master Role Long Description</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="txtUpdateLongtDesc" class="form-control" name="txtUpdateLongtDesc"
                                           		   ng-model="updateMasterRoleForm.updateLongtDescModel" readonly  
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterRoleForm.txtUpdateLongtDesc.$invalid)]">
                                           	<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterRoleForm.txtUpdateLongtDesc.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Master Role Long Description!</em>
									        </div>
                                        </div>
                                	</div>
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Is Active</label>
                                         <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxUpdateIsActive" id="cbxUpdateIsActive" class="margingRightChkBx" ng-model="updateMasterRoleForm.cbxUpdateIsActive">
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="onUpdateClick(updateMasterRoleForm);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="cancelClickOnUpdatePanal(updateMasterRoleForm)">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
		<!-- 		<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Upload Designation</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "UploadHidden">
		                             <div class="row">
		                             	<div class="col-sm-2 "></div>
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File name</label>
			                            <div class="col-sm-3 ">
			                            	<input type="file" class="form-control" name="fuUploadFilename"
												id="fuUploadFilename" ng-model="fuUploadFilenameModel" required>
										</div>
										<div class="col-sm-1">
											<button type="button" class="btn btn-primary btnSpace" id="btnUpload">Upload</button>						
										</div>
									</div>
                            	</div>
                        	</div>  
						</div>
			       	</div>
				</div> -->
	         </form>
        </div>
    </div>
    <div id="modalRDetials" class="modal fade" role="dialog">
   		<div class="modal-dialog">
   		Modal content
       		<div class="modal-content">
	            <div class="modal-header bootstrap-dialog-header">
	                <button type="button" class="close" data-dismiss="modal">&times;</button>
	                <h4 class="modal-title" style="text-align-last: center">Role Details</h4>
	            </div>
	            <div class="modal-body">
	            	<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<table class="tblRDetails table clsTable tblBottomMargin">
									<tbody>
											<tr align="char" ng-repeat="row in rolesdetails">
												<td>Master Role Code</td>
												<td >{{(row.masterRoleName)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Short Description</td>
												<td>{{(row.masterRoleShortDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Skills</td>
												<td>{{(row.skillDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Practice</td>
												<td>{{(row.masterSubPractice.masterPractice.description)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Sub Practice</td>
												<td>{{(row.masterSubPractice.subpracticeName)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Syntel Role</th>
												<td>{{(row.masterSyntelRoles.syntelRoleName)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Proficiency Level</td>
												<td>{{(row.proficiencyLevelDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 1</td>
												<td>{{(row.designation1.empDesignationDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 1%</td>
												<td>{{(row.designation1Percent)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 2</td>
												<td>{{(row.designation2.empDesignationDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 2%</td>
												<td>{{(row.designation2Percent)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 3</td>
												<td>{{(row.designation3.empDesignationDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 3%</td>
												<td>{{(row.designation3Percent)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 4</td>
												<td>{{(row.designation4.empDesignationDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Designation 4%</td>
												<td>{{(row.designation4Percent)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>X0 Skills</td>
												<td>{{(row.x0SkillDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>X0 Skill Element</td>
												<td>{{(row.x0SkillElementDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>X0 Knowledge Area</td>
												<td>{{(row.x0KnowledgeAreaDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>X0 Proficiency</td>
												<td>{{(row.x0ProficiencyDescription)}}</td>
											</tr>
											<tr ng-repeat="row in rolesdetails">
												<td>Long Description</td>
												<td>{{(row.masterRoleLongDescription)}}</td>
											</tr>
									</tbody>												
								</table>
							</div>
						</div>
					</div>
	            </div>
            	<div class="modal-footer">		            	
                	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            	</div>
       		</div>
   		</div>
	</div>
	 </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
