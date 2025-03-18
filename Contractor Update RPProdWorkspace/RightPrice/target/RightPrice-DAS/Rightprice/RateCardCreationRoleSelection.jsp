<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
<link href="${contextPath}/resources/css/SAPStyleSheet.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/AngularCSS.css"
	rel="stylesheet" />
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"></script>
<link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/bootstrap-dialog.css"
	rel="stylesheet" />
<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
<script src="${contextPath}/resources/js/jquery.validate.js"></script>
<script src="${contextPath}/resources/js/jqueryValidations.js"></script>
<script src="${contextPath}/resources/js/additional-methods.js"></script>
<script src="${contextPath}/resources/js/jqueryValidations.js"></script>
<link
	href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/sticky-footer-navbar.css"
	rel="stylesheet" />
<script
	src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
<script src="${contextPath}/resources/js/angular.js"></script>
<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
<script src="${contextPath}/resources/js/angular-messages.js"></script>
<script src="${contextPath}/resources/js/ngStorage.js"></script>
<script src="${contextPath}/resources/js/loader.js"></script>
<script src="${contextPath}/resources/js/RightPrice/RoleSelection.js"></script>
<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
<script type="text/javascript">
	
</script>

</head>
<body ng-app="RightPriceApp" ng-controller="RoleSelectionController">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">Rate Card Creation -
						Role Selection</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="RateCardSelection"
					id="RateCardSelection">
					<div id="includedRateCardStages" ng-if="autosidebar"
							ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
						<div id="includedRateCardStages" ng-if="manualsidebar"
							ng-include="'${contextPath}/Portal/RateCardManualCompletionStage.jsp'"></div>
						<div id="includedRateCardStages" ng-if="hybridsidebar"
							ng-include="'${contextPath}/Portal/RateCardHybridCompletionStage.jsp'"></div>
					<div id="includedRateCardStages"
						ng-include="'${contextPath}/Rightprice/RateCardCreationInformationTable.jsp'"></div>
					<div class="row">

						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<ng-form name="roleSelectionForm" id="roleSelectionForm">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10 ">Select Roles</label>
											<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideSearch()"> &#9660;</a>
										</div> -->
										</div>
									</div>
									<div class="panel-body">
										<fieldset ng-disabled="isDisabled">
											<div class="divEmptyThrice"></div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-3 textAlignRight">Copy
													Roles from another Rate Card</label>
												<div class="col-sm-6">
													<select id="ddlRateCard" class="form-control"
														placeholder="Please select" name="ddlRateCard"
														ng-model="rateCardModel"
														ng-options="rate.rcId as rate.rcId+' - '+ rate.rcName for rate in rate_card"
														ng-change="getRateCardDetails(rateCardModel)">
														<option value="" selected disabled>Please select</option>
													</select>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-3 textAlignRight">OR</label>
											</div>
											<div class="divEmpty"></div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-3 textAlignRight">Search
													Roles from Role Master</label>
												<div class="col-sm-6">
													<input name="txtSearch" type="text" class="form-control"
														id="txtSearch" ng-model="searchModel">
												</div>
												<div class="col-sm-3">
													<button type="button" class="btn btn-primary"
														id="btnSearch" ng-click="searchRateCardData();">Search</button>
												</div>
											</div>
											<div class="divEmptyThrice"></div>
											<!-- <pre>{{searchRoleDetailsResult}}</pre> -->
											<div class="row">
												<div class="col-sm-12">
													<div class="table-responsive">
														<table
															class="table clsTable table-striped table-bordered table-hover table-condensed "
															id="tblRoleSelection1">
															<thead>
																<tr>
																	<th class="tdVetAlignMiddle">Select <span class="margin10px"></span> 
																	<input type="checkbox"
																	name="{{'cbxItem'+'_'+($index+1)}}"
																	id="{{'cbxItem'+'_'+($index+1)}}"
																	class="margingRightChkBx "
																	ng-model="selectAllCheckBox"
																	ng-change=addAllCheckedItems(searchRoleDetailsResult,selectAllCheckBox)>
																	</th>
																	<th class="tdVetAlignMiddle"ng-click="myorder('masterRoleShortDescription')">Role Code</th>
																	<th class="firstColLeftAlign tdVetAlignMiddle" ng-click="myorder('practiceName')">Practice</th>
																	<th class="tdVetAlignMiddle"ng-click="myorder('subPracticeName')">Sub Practice</th>
																	<th class="tdVetAlignMiddle"ng-click="myorder('syntelRoleName')">Syntel Role</th>
																	<th class="tdVetAlignMiddle"ng-click="myorder('proficiencyLevelDescription')" >Proficiency</th>
																	<th class="tdVetAlignMiddle"ng-click="myorder('x0ProficiencyDescription')">X0 Proficiency</th>
																	<th class="tdVetAlignMiddle"ng-click="myorder('bandGrade')">Syntel Band /Grade</th>
																	<th class="tdVetAlignMiddle"ng-click="myorder('gcmCODE')">GCM Level</th>
																</tr>
															</thead>
															<tbody id="tBodyRoleSelection1">
																<tr ng-repeat="rateCard in searchRoleDetailsResult|orderBy:forder">
														
																	<td><input type="checkbox"
																		name="{{'cbxItem'+'_'+($index+1)}}"
																		id="{{'cbxItem'+'_'+($index+1)}}"
																		class="margingRightChkBx"
																		ng-model="rateCard.chkBoxStatus"
																		ng-change=addChkItem(rateCard,rateCard.chkBoxStatus,$index)></td>
																	<td class="firstColLeftAlign">{{rateCard.masterRoleShortDescription}}
																	<td class="firstColLeftAlign tooltip1">{{rateCard.practiceName}}
																		<span class="tooltiptext">{{rateCard.masterRoleShortDescription}}</span>
																	</td>
																	<td class="firstColLeftAlign tooltip1">{{rateCard.subPracticeName}}
																		<span class="tooltiptext">{{rateCard.masterRoleLongDescription}}</span>
																	</td>
																	<td>{{rateCard.syntelRoleName}}</td>
																	<td>{{rateCard.proficiencyLevelDescription}}</td>
																	<td>{{rateCard.x0ProficiencyDescription}}</td>
																	<td>{{rateCard.bandGrade}}</td>
																	<td>{{rateCard.gcmCODE}}</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
											<div class="divEmptyThrice"></div>
											<div class="row text-center">
												<div class="col-sm-12">
													<button type="button" class="btn btn-primary btnSpace"
														id="btnAdd" ng-click="addSelectedRoles()">Add</button>
												</div>
											</div>
										<div class="divEmptyThrice"></div>
											<div class="row">
												<!-- <pre>{{selectededRolesArr}}</pre> -->
												<div class="col-sm-12">
													<div class="table-responsive">
											<!-- <button type="button" class="btn btn-primary btnSpace"
												ng-click="exportToExcel('#tableToExport')">
												<span class="glyphicon glyphicon-share"></span> Export to
												Excel
											</button> -->
													<%-- 	<button type="button" class="btn btn-primary btnSpace" ng-click="exportToExcel('#tableToExport')">
															 <img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button> --%>
													</div>
												</div>
											</div>
											
											
											<div class="divEmptyThrice"></div>
											<div class="row">
												<!-- <pre>{{selectededRolesArr}}</pre> -->
												<div class="col-sm-12">
													<div class="table-responsive">
														<table border="1"
															class="table clsTable table-striped table-bordered table-hover table-condensed"
															id="tblRoleSelection2">
															<thead>
																<tr>
																	<<!-- th class="firstColLeftAlign tdMasterRoleCode">Master
																		Role Code</th>
																	<th class="tdSyntelRole">Syntel Role</th>
																	<th>Proficiency</th>
																	<th>Syntel Band/ Grade</th>
																	<th>GCM Level</th>
																	<th class="tdCommonDDlWidth">X.O Skills</th>
																	<th class="tdCommonDDlWidth">X.O Skills Element</th>
																	<th class="tdCommonDDlWidth">X.O Knowledge</th>
																	<th class="tdClientRolText">Customer Role</th>
																	<th class="tdCommentText">Comments</th>
																	<th>Remove</th> -->
																	<th class="firstColLeftAlign tdMasterRoleCode" ng-click="myorder('masterRoleShortDescription')">Master
																		Role Code</th>
																	<th class="tdSyntelRole" ng-click="myorder('syntelRoleName')">Syntel Role</th>
																	<th ng-click="myorder('proficiencyLevelDescription')">Proficiency</th>
																	<th ng-click="myorder('bandGrade')"> Syntel Band/ Grade</th>
																	<th ng-click="myorder('gcmCODE')">GCM Level</th>
																	<th class="tdCommonDDlWidth" >X.O Skills</th>
																	<th class="tdCommonDDlWidth" >X.O Skills Element</th>
																	<th class="tdCommonDDlWidth">X.O Knowledge</th>
																	<th class="tdClientRolText">Customer Role</th>
																	<th class="tdCommentText">Comments</th>
																	<th>Remove</th>
																</tr>
																	
																</tr>
																
															</thead>

															<tbody id="tBodytblRoleSelection2">

																<tr id="{{'trroleSelection'+'_'+($index+1)}}"
															ng-repeat="currentRole in selectededRolesArr|orderBy:forder">
																	<td class="firstColLeftAlign tooltip1">{{currentRole.masterRoleShortDescription}}
																		<span class="tooltiptext">{{currentRole.masterRoleLongDescription}}</span>
																	</td>
																	<td>{{currentRole.syntelRoleName}}</td>
																	<td>{{currentRole.proficiencyLevelDescription}}</td>
																	<td>{{currentRole.bandGrade}}</td>
																	<td>{{currentRole.gcmCODE}}</td>
																	<!-- need this commented code in future -->
																	<td><select id="{{'ddlSkill'+'_'+($index+1)}}"
																		class="form-control" placeholder="Please select"
																		name="{{'ddlSkill'+'_'+($index+1)}}"
																		ng-model="currentRole.xOSkillIndex" required
																		ng-class="{true: 'ng-border'}[onSave && roleSelectionForm.{{'ddlSkill'+'_'+($index+1)}}.$invalid]"
																		ng-options="skill.skillId as skill.skillName for skill in currentRole.xOSkill |orderBy:'skillName'"
																		ng-change="getXOSkillsElementMaster(currentRole.xOSkillIndex,$index,currentRole);">
																			<option value="" selected disabled>Please
																				select</option>
																	</select>
																		<div class="error-messages" ng-if="onSave"
																			ng-messages="roleSelectionForm['ddlSkill'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please select skill id.</em>
																		</div></td>
																		
																	<!-- 	<td><select id="{{'ddlSkill'+'_'+($index+1)}}"
																		class="form-control" placeholder="Please select"
																		name="{{'ddlSkill'+'_'+($index+1)}}"
																		ng-model="currentRole.xOSkillIndex"
																		ng-options="skill.skillId as skill.skillName for skill in currentRole.xOSkill |orderBy:'skillName'"
																		ng-change="getXOSkillsElementMaster(currentRole.xOSkillIndex,$index,currentRole);">
																			<option value="" selected disabled>Please
																				select</option> -->
																	</select></td>
																	<td><select
																		id="{{'ddlSkillElement'+'_'+($index+1)}}"
																		class="form-control" placeholder="Please select"
																		name="{{'ddlSkillElement'+'_'+($index+1)}}"
																		ng-model="currentRole.xOSkillElementIndex" required
																		ng-class="{true: 'ng-border'}[onSave && roleSelectionForm.{{'ddlSkillElement'+'_'+($index+1)}}.$invalid]"
																		ng-options="skillElement.skillElementId as skillElement.elementName for skillElement in currentRole.xOSkillElement |orderBy:'elementName'"
																		ng-change="checkData(currentRole.xOSkillElementIndex,$index);">
																			<option value="" selected disabled>Please
																				select</option>
																	</select>
																		<div class="error-messages" ng-if="onSave"
																			ng-messages="roleSelectionForm['ddlSkillElement'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please select skill
																				element.</em>
																		</div></td>
																		<!-- <td><select
																		id="{{'ddlSkillElement'+'_'+($index+1)}}"
																		class="form-control" placeholder="Please select"
																		name="{{'ddlSkillElement'+'_'+($index+1)}}"
																		ng-model="currentRole.xOSkillElementIndex"
																		ng-options="skillElement.skillElementId as skillElement.elementName for skillElement in currentRole.xOSkillElement |orderBy:'elementName'"
																		ng-change="checkData(currentRole.xOSkillElementIndex,$index);">
																			<option value="" selected disabled>Please
																				select</option>
																	</select></td> -->
																	<td><select id="{{'ddlKnowledge'+'_'+($index+1)}}"
																		class="form-control" placeholder="Please select"
																		name="{{'ddlKnowledge'+'_'+($index+1)}}"
																		ng-model="currentRole.xOKnowledgeIndex" required
																		ng-class="{true: 'ng-border'}[onSave && roleSelectionForm.{{'ddlKnowledge'+'_'+($index+1)}}.$invalid]"
																		ng-options="knowledgeMaster.knowledgeId as knowledgeMaster.knowledgeName for knowledgeMaster in knowledge_name |orderBy:'knowledgeName'"
																		ng-change="checkData();">
																			<option value="" selected disabled>Please
																				select</option>
																	</select>
																		<div class="error-messages" ng-if="onSave"
																			ng-messages="roleSelectionForm['ddlKnowledge'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please select knowledge.</em>
																		</div></td>
																		
																		<!-- <td><select id="{{'ddlKnowledge'+'_'+($index+1)}}"
																		class="form-control" placeholder="Please select"
																		name="{{'ddlKnowledge'+'_'+($index+1)}}"
																		ng-model="currentRole.xOKnowledgeIndex"
																		ng-options="knowledgeMaster.knowledgeId as knowledgeMaster.knowledgeName for knowledgeMaster in knowledge_name |orderBy:'knowledgeName'"
																		ng-change="checkData();">
																			<option value="" selected disabled>Please
																				select</option>
																	</select></td> -->
																	<td><input
																		name="{{'txtClientRole'+'_'+($index+1)}}" type="text"
																		class="form-control"
																		id="{{'txtClientRole'+'_'+($index+1)}}"
																		ng-model="currentRole.clientRole"
																		ng-class="{true: 'ng-border'}[onSave && roleSelectionForm.{{'txtClientRole'+'_'+($index+1)}}.$invalid]"
																		required>
																		<div class="error-messages" ng-if="onSave"
																			ng-messages="roleSelectionForm['txtClientRole'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please enter customer
																				role.</em>
																		</div></td>
																		<!-- <td><input
																		name="{{'txtClientRole'+'_'+($index+1)}}" type="text"
																		class="form-control"
																		id="{{'txtClientRole'+'_'+($index+1)}}"
																		ng-model="currentRole.clientRole"></td> -->
																	<td><input name="txtCommentsTbl2Row1" type="text"
																		class="form-control" id="txtCommentsTbl2Row1"
																		ng-model="currentRole.comments"></td>
																	<td><button type="button" id="btnRemoveRow1"
																			ng-click="deleteRow(currentRole,$index)">-</button></td>
																</tr>
															</tbody>
														</table>
													</div>








				<div class="table-responsive" id="tableToExport" style="display:none" >
				<table border="1" 
				class="table clsTable table-striped table-bordered table-hover table-condensed "
				id="tblRateCardDetails">
				<tr>
					<td align="Center" bgcolor="#CCFFFF" colspan=6><Strong>Rate Card Creation - Role Selection</Strong></td>
				</tr>
				</table>
				<table></table>
				
				<table  border="1"
				class="table table-striped table-bordered table-hover table-condensed "
				id="tblRateCardDetails">
				<tbody id="tBodyRateCardDetails" >
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF">Rate Card Id</th>
						<td class="thWidth18Per" align="left">{{row.rcId}}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF">Name</th>
						<td class="thWidth18Per" align="left">{{row.rcName}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Approver Status</th>
						<td class="thWidth18Per" align="left">{{status}}</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF">Start Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per" align="left">{{row.rcStartDate }}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF">Expected End Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per" align="left">{{row.expectedRCEndDate | date:'dd/mmm/yyyy'}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Applicable Months</th>
						<td class="thWidth18Per" align="left">{{row.applicableMonths}}</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF">Customer</th>
						<td class="thWidth18Per" align="left">{{row.customerVerticalMapping.customer.customerName}}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF">Expected TCV</th>
						<td class="thWidth18Per" align="left">{{row.tvc}} - {{currencyName}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Discount</th>
						<td class="thWidth18Per" align="left">{{row.volumeDiscount}}%</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF">Onsite Utilization %</th>
						<td class="thWidth18Per" align="left">{{row.expectedOnsiteResourcePercentage}}%</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF">Offshore Utilization %</th>
						<td class="thWidth18Per" align="left">{{row.expectedOffshoreResourcePercentage}}%</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">PM% Post VR</th>
						<td class="thWidth18Per" align="left"><span ng-if="!row.calculatedGMPercentagePostDiscount == 0 || !row.calculatedGMPercentagePostDiscount == null">{{row.calculatedGMPercentagePostDiscount| number :2}}%</span>
						<span ng-if="row.calculatedGMPercentagePostDiscount == 0 || row.calculatedGMPercentagePostDiscount == null"> - </span></td>
					</tr>	
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF">Onsite hours/day</th>
						<td class="thWidth18Per" align="left">{{row.onsiteHoursPerDay}}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF">Offshore hours/day</th>
						<td class="thWidth18Per" align="left">{{row.offshoreHoursPerDay}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Billing Currency</th>
						<td class="thWidth18Per" align="left">{{currencyName}}</td>
					</tr>	
				</tbody>
			</table>
			<table></table>
			<table></table>
														<table border="1"
															class="table clsTable table-striped table-bordered table-hover table-condensed"
															id="tblRoleSelection2">
															<thead>
																<tr>
																	<th class="firstColLeftAlign tdMasterRoleCode" bgcolor="#CCFFFF">Master
																		Role Code</th>
																	<th class="tdSyntelRole" bgcolor="#CCFFFF">Practice</th>
																	<th class="tdSyntelRole" bgcolor="#CCFFFF">Sub Practice</th>
																	<th class="tdSyntelRole" bgcolor="#CCFFFF">Syntel Role</th>
																	<th bgcolor="#CCFFFF">Proficiency</th>
																	<th bgcolor="#CCFFFF">Syntel Band/ Grade</th>
																	<th class="tdVetAlignMiddle2" bgcolor="#CCFFFF">GCM Level</th>
																	<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Skills</th>
																	<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Skills Element</th>
																	<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Knowledge</th>
																	<th class="tdClientRolText" bgcolor="#CCFFFF">Customer Role</th>
																	<th class="tdCommentText" bgcolor="#CCFFFF">Comments</th>

																</tr>
															</thead>

															<tbody id="tBodytblRoleSelection2">

																<tr id="{{'trroleSelection'+'_'+($index+1)}}"
																	ng-repeat="currentRole in selectededRolesArr|orderBy:'roleId'">
																	<td><span>{{currentRole.masterRoleShortDescription}}</span></td>
																	<td>{{currentRole.practiceName}}</td>
																	<td>{{currentRole.subPracticeName}}</td>
																	<td>{{currentRole.syntelRoleName}}</td>
																	<td>{{currentRole.proficiencyLevelDescription}}</td>
																	<td>{{currentRole.bandGrade}}</td>
																	<td align="Right">{{currentRole.gcmCODE}}&#160;</td>
																	<td>
																	<div ng-repeat="item in currentRole.xOSkill"
																			ng-if="item.skillId==currentRole.xOSkillIndex">
																			{{item.skillName}}</div>

																	</td>
																	<td>
																	<div ng-repeat="item in currentRole.xOSkillElement"
																		ng-if="item.skillElementId==currentRole.xOSkillElementIndex">
																		{{item.elementName}}</div>
																	</td>
																	<td>
																	<div ng-repeat="item in knowledge_name"
																			ng-if="item.knowledgeId ==currentRole.xOKnowledgeIndex">
																			{{item.knowledgeName}}
																			</div>
																	</td>
																	<td>{{currentRole.clientRole}}</td>
																	<td>{{currentRole.comments}}</td>

																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</fieldset>
										<div class="divEmptyThrice"></div>
										<div class="row text-center">
											<div class="col-sm-12">
												<!-- <button type="button" class="btn btn-primary btnSpace" id="btnSaveCurrentVersion">Save to Current version</button>
											<button type="button" class="btn btn-primary btnSpace" id="btnAddNewVersion">Add As New Version</button> -->
											<button type="button" class="btn btn-primary btnSpace" ng-click="exportToExcel('#tableToExport')">
															 <img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
												<button type="button" class="btn btn-primary btnSpace"
													id="btnSave" ng-click="onSaveClick(roleSelectionForm);"
													ng-disabled="isSaveDisabled">Save</button>
												<!-- <button type="button" class="btn btn-danger btnSpace"
													id="btnCancel" ng-click="cancel();">Cancel</button> -->
												<button type="button" class="btn btn-info btnSpace"
													id="btnClientPrev" ng-click="Prev()">Prev</button>
												<button type="button" class="btn btn-info"
													id="btnClientNext" ng-click="Next()">Next</button>
											</div>
										</div>
									</div>
									</ng-form>
								</div>
							</div>
						</div>

					</div>

				</form>
			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
