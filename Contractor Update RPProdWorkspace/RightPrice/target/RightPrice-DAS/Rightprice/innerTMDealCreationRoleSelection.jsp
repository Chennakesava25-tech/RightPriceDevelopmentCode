<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Select Roles</label>
										<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideSearch()"> &#9660;</a>
										</div> -->
									</div>
                                </div>
                                
                                <div class="panel-body">
                               
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
	                                	<label class="control-label col-sm-3 textAlignRight">Copy Roles from another Rate Card</label>
	                                     <div class="col-sm-6">
	                                      	<select id="ddlRateCard" class="form-control" placeholder="Please select" 
                       	   							name="ddlRateCard" ng-model="fpdcRoleSelectionFrm.rateCardModel"  ng-options="rate.rcId as rate.rcId+' - '+ rate.rcName for rate in rateCards" ng-change="getTandMRateCardDetails(fpdcRoleSelectionFrm.rateCardModel)" required>
													<option value="" selected disabled>Please select</option>
											</select>
											<div class="divEmptyThrice"></div>
											 <p style="color: red">
									<b>
									 only if your rate card number is > 8000
									</b>
								</p>
								<div class="divEmptyThrice"></div>
	                                 	</div>
									</div>
									<div class="row marginBottom5px">
	                                	<label class="control-label col-sm-3 textAlignRight">OR</label>	                                     
									</div>
									<div class="divEmpty"></div>
									 <div class="row marginBottom5px" ng-if="masterroleMaster == true">
	                                	<label class="control-label col-sm-3 textAlignRight">Master  Roles</label>
	                                     <div class="col-sm-6">
	                                      	<input name="txtSearch" type="text" class="form-control" id="txtSearch"
                                            	   ng-model="fpdcRoleSelectionFrm.searchModel" value="contractor" required >
	                                 	</div>
	                                 	<div class="col-sm-3">		
											<button type="button" class="btn btn-primary" id="btnSearch" ng-click="searchRateCardData(fpdcRoleSelectionFrm.searchModel)">Search</button>
										</div>
									</div>
	                               <!--  <div class="row marginBottom5px" ng-if="masterroleContract == true">
	                                	<label class="control-label col-sm-3 textAlignRight">Contractor  Roles</label>
	                                     <div class="col-sm-6">
	                                      	<input name="txtSearch" type="text" class="form-control" id="txtSearch"
                                            	   ng-model="fpdcRoleSelectionFrm.searchModel" value="contractor" required  ng-disabled="true">
	                                 	</div>
	                                 	<div class="col-sm-3">		
											<button type="button" class="btn btn-primary" id="btnSearch" ng-click="searchRateCardData()">Search</button>
										</div>
									</div> -->
									<div class="row marginBottom5px" ng-if="masterroleKPO == true">
	                                	<label class="control-label col-sm-3 textAlignRight">KPO  Roles</label>
	                                     <div class="col-sm-6">
	                                      	<input name="txtSearch" type="text" class="form-control" id="txtSearch"
                                            	   ng-model="fpdcRoleSelectionFrm.searchModel" value="KPO" required  ng-disabled="true">
	                                 	</div>
	                                 	<div class="col-sm-3">		
											<button type="button" class="btn btn-primary" id="btnSearch" ng-click="searchRateCardData(fpdcRoleSelectionFrm.searchModel)">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row">
	                                    <div class="col-sm-12">
											<div class="table-responsive">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection1">
													<thead>
														<tr ng-show='searchRoleDetailsResult.length > 0' >
															<!-- <th>Source</th> -->
															<th>Select
															<span><input type="checkbox"
																	name="{{'cbxItem'+'_'+($index+1)}}"
																	id="{{'cbxItem'+'_'+($index+1)}}"
																	class="margingRightChkBx "
																	ng-model="selectAllCheckBox"
																	ng-change=addAllCheckedItems(searchRoleDetailsResult,selectAllCheckBox)>
															</span>
															</th> 
															<th class="firstColLeftAlign"ng-click="myorder('masterRoleShortDescription')">Role Description </th>
															<th ng-click="myorder('syntelRoleName')">Syntel Role </th>
															<th ng-click="myorder('clientRole')">Client Role </th>
															<th ng-click="myorder('proficiencyLevelDescription')">Proficiency </th>
															<th ng-click="myorder('x0ProficiencyDescription')">X0 Proficiency </th>
															<th ng-click="myorder('bandGrade')">Syntel Band /Grade </th>
															<th ng-click="myorder('gcmCODE')">GCM Level</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection1" >
													 <tr ng-repeat="rateCard in searchRoleDetailsResult|orderBy:forder">
													     <td><input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
													      ng-model="rateCard.chkBoxStatus"
													      ng-change=addChkItem(rateCard,rateCard.chkBoxStatus,$index)>
													      
													      </td>
														 <td class="firstColLeftAlign tooltip1">{{rateCard.masterRoleShortDescription}}
														  <span class="tooltiptext">{{rateCard.masterRoleLongDescription}}</td>
														  <td>{{rateCard.syntelRoleName}} </td> 
														 <td>{{rateCard.clientRole}}</td> 
														  <td>{{rateCard.proficiencyLevelDescription}} </td>
														  <td>{{rateCard.x0ProficiencyDescription}} </td>
														  <td>{{rateCard.bandGrade}} </td>
														  <td>{{rateCard.gcmCODE}} </td> 
													</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                            		<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnAdd" ng-click="addSelectedRoles()">Add</button>	
										</div>
				
									</div>
									<div class="divEmptyThrice"></div>
											 <p style="color: red">
									<b>
									 customer role name is mandatory
									</b>
								</p>
									<div class="divEmptyThrice"></div>
									<div class="row">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection2">
													<thead>
														<tr ng-show='selectededRolesArr.length > 0' >	
															<th class="firstColLeftAlign tdMasterRoleCode"ng-click="myorder('masterRoleShortDescription')">Master Role Code</th>
															<th class="tdSyntelRole"ng-click="myorder('syntelRoleName')">Syntel Role</th>
															<th ng-click="myorder('proficiencyLevelDescription')" >Proficiency</th>
															<th ng-click="myorder('bandGrade')">Syntel Band /Grade</th>
															<th ng-click="myorder('gcmCODE')">GCM Level</th>
															<th class="tdCommonDDlWidth">X.O Skills</th>
															<th class="tdCommonDDlWidth">X.O Skills Element</th>
															<th class="tdCommonDDlWidth">X.O Knowledge</th>
															<th class="tdClientRolText">Customer Role</th>
															<th class="tdCommentText">Comments</th>
															<th>Remove</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection2">
														
														<tr ng-repeat="currentRole in selectededRolesArr |orderBy:forder track by $index">
															<td class="firstColLeftAlign tooltip1">{{currentRole.masterRoleShortDescription}}
																<span class="tooltiptext">{{currentRole.masterRoleLongDescription}}</span>
															</td>
															<td>{{currentRole.syntelRoleName}} </td>
															<td>{{currentRole.proficiencyLevelDescription}}</td>
															<td>{{currentRole.bandGrade}}</td>
															<td>{{currentRole.gcmCODE}}</td>
															<td>
																<select 
																name="ddlSkill" class="form-control" id="ddlSkill" placeholder="Please select"
																 ng-class="{true: 'ng-border'} [(update && currentRole.ddlSkill.$invalid)]"
																 ng-model="currentRole.xOSkillIndex" 
																ng-change="getXOSkillsElementMaster(currentRole.xOSkillIndex,$index);" 
																ng-options="skill.skillId as skill.skillName for skill in currentRole.xOSkill" required>
																	<option value="" selected disabled>Please select</option>
																</select>
																<div class="error-messages" ng-if="update" ng-messages="currentRole.ddlSkill.$error">
																     	<em class="error help-block has-error" ng-message="required">Please select skills Name</em>
																</div>
																
																
																
															</td> 
															<td>
																<select id="ddlSkillElement" class="form-control" placeholder="Please select"
																name="ddlSkillElement" ng-model="currentRole.xOSkillElementIndex" 
																ng-options="skillElement.skillElementId as skillElement.elementName for skillElement in currentRole.xOSkillElement"
																ng-change="checkData(currentRole.xOSkillElementIndex,$index);"  required>
																	<option value="" selected disabled>Please select</option>
																</select>
															</td> 
															<td>
																<select id="ddlKnowledge" class="form-control" placeholder="Please select"
																name="ddlKnowledge" ng-model="currentRole.xOKnowledgeIndex" 
																ng-options="knowledgeMaster.knowledgeId as knowledgeMaster.knowledgeName for knowledgeMaster in knowledge_name"
																ng-change="checkData();" required>
																	<option value="" selected disabled>Please select</option>
																</select>
															</td> 
															<td><input name="txtClientRoleTbl2Row1" type="text" class="form-control" id="txtClientRoleTbl2Row1" ng-model="currentRole.clientRole" required
															
															ng-class="{true: 'ng-border'} [(currentRole.txtClientRoleTbl2Row1.$invalid)]">
															<div class="error-messages" ng-if="txtClientRoleTbl2Row1" ng-messages="currentRole.txtClientRoleTbl2Row1.$error">
													     		<em class="error help-block has-error" ng-message="required">Please Enter role </em>
															</div>
															
															 </td>
															
															<td><input name="txtCommentsTbl2Row1" type="text" class="form-control" id="txtCommentsTbl2Row1" ng-model="currentRole.comments" required> </td>
															<td><button type="button" id="btnRemoveRow1" ng-click="deleteRow($index)">-</button></td>
														</tr>
													</tbody>
												<!-- 	<tbody id="tBodyRoleSelection2">
														<tr >
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Administrator </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row1" type="text" class="form-control" id="txtClientRoleTbl2Row1" 
																	   ng-model="txtClientRoleTbl2Row1Model" required> </td>
															<td><input name="txtCommentsTbl2Row1" type="text" class="form-control" id="txtCommentsTbl2Row1" 
																	   ng-model="txtCommentsTbl2Row1Model" required> </td>
															<td><button type="button" id="btnRemoveRow1">remove</button></td>
														</tr>
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Lead </td>
															<td> Medium </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row2" type="text" class="form-control" id="txtClientRoleTbl2Row2" 
																	   ng-model="txtClientRoleTbl2Row2Model" required> </td>
															<td><input name="txtCommentsTbl2Row2" type="text" class="form-control" id="txtCommentsTbl2Row2" 
																	   ng-model="txtCommentsTbl2Row2Model" required> </td>
															<td><button type="button" id="btnRemoveRow2">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														<tr>
															<td class="firstColLeftAlign tooltip1">Practice/SubPractice
															 <span class="tooltiptext">Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area</span>
															 </td>
															<td> Developer </td>
															<td> Low </td>
															<td> ACAC1-ACAC2 </td>
															<td></td>
															<td></td>
															<td></td>
															<td><input name="txtClientRoleTbl2Row3" type="text" class="form-control" id="txtClientRoleTbl2Row3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtCommentsTbl2Row3" type="text" class="form-control" id="txtCommentsTbl2Row3" 
																	   ng-model="txtCommentsTbl2Row3Model" required> </td>
															<td><button type="button" id="btnRemoveRow3">remove</button></td>
														</tr>	
														
													</tbody>
												 -->
												</table>
											</div>
										</div>
									</div>
                            		<div class="divEmptyThrice"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
	<!-- <div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle"  ng-click="showHideAddContractorRole()">
					<div class="row ">
						<label class="control-label col-sm-10 ">Add Contractor
							Role</label>
						<div class="col-sm-2 textAlignRight">
							<a href="#" class="DownArrowColor"> &#9660;</a>
						</div>
					</div>
				</div>
				<div class="panel-body"  ng-hide = "addContractorRoleHidden">
				<div class="row marginBottom5px">
                <label class="control-label col-sm-2 textAlignRight required-Field">Customer Role</label>
                <div class="col-sm-3 ">
                	<input name="txtContractorCustomerRole"	type="text" class="form-control" id="txtContractorCustomerRole" ng-model="fpdcRoleSelectionFrm.CustomerRoleModel" required>
				</div>
				 
				<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
                <div class="col-sm-3 ">   	
                   	<input name="txtContractorCustomerCmt"	type="text" class="form-control" id="txtContractorCustomerCmt" ng-model="fpdcRoleSelectionFrm.CustomerCmtModel" required>
				</div>
				<div class="col-sm-2 textAlignRight">		
				   <button type="button" class="btn btn-info btnSpace" id="btnDetailsAdd" ng-click="addContractorRoleRow()">Add</button>
				</div>
			</div>
			<div class="divEmptyThrice"></div>
					<div class="row marginBottom5px">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<div class="table-responsive">
								<table
									class="table clsTable table-striped table-bordered table-hover table-condensed "
									id="tblAddContractor">
									<thead>
										<tr>
											<th>Sr.No.</th>
											<th>Customer Role</th>
											<th>Comments</th>
											<th>Remove</th>
										</tr>
									</thead>
									<tbody id="tBodyAddContractor">
										<tr ng-repeat="contractorRole in contractorRoleArr">
											<td>{{$index + 1}}</td>
											<td>{{contractorRole.customerRole}}</td>
											<td>{{contractorRole.comments}}</td>
											<td><button type="button"
													id="btnAddContractorRemoveRow1" ng-click="removeCustomerRole($index)">Remove</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				  <div class="divEmptyThrice"></div>
				</div>
			</div>
		</div>
	</div> -->
</div>
<div class="divEmptyThrice"></div>
                            		<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-click="saveOrUpdateDealRole(fpdcRoleSelectionFrm);" ng-disabled= "isSubmitDisable" >Save</button>
											<!-- <button type="button" class="btn btn-danger btnSpace" id="btnCancel">Cancel</button> -->
										<button type="button" class="btn btn-primary btnSpace" ng-click="exportToExcel('#tableToExport')"> 
										<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
											<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									
									<div class="row">
	<div class="col-sm-12"  >
		<div class="table-responsive" id="tableToExport" style="display: none; " >
			<table border="1" width="750" 
				class="table clsTable table-striped table-bordered table-hover table-condensed "
				id="tblRateCardDetails">
				<tr>
					<td align="Center" bgcolor="#CCFFFF" colspan=6>T&M Deal
						Creation - Role Selection</td>
				</tr>
			</table>
			<table></table> 
			<table border="1" width="750"
								class="table clsTable table-striped table-bordered table-hover table-condensed "
								id="tblRateCardDetails">
								<tbody id="tBodyRateCardDetails">
									<tr ng-repeat="versionDetail in versionDetails">
										<th align="left" bgcolor="#CCFFFF">Deal Id</th>
										<td align="left">{{versionDetail.crmDealId}}</td>
										<th align="left" bgcolor="#CCFFFF">Deal Version (ID)</th>
										<td align="left">{{versionDetail.dealVersion}} ({{versionDetail.rpDealVersionId}})</td>
										<th align="left" bgcolor="#CCFFFF">Customer Name</th>
										<td align="left">{{versionDetail.dealcrmstagesdata2.customer.customerName}}</td>
									</tr>
									<tr ng-repeat="versionDetail in versionDetails">
										<th align="left" bgcolor="#CCFFFF">Deal Status</th>
										<td align="left">{{versionDetail.dealStatus}}</td>
										<th align="left" bgcolor="#CCFFFF">Project Type</th>
										<td align="left">{{versionDetail.projectType}}</td>
										<th align="left" bgcolor="#CCFFFF">Deal Description</th>
										<td align="left">{{versionDetail.dealcrmstagesdata2.dealDescription}}</td>
									</tr>
									<tr ng-repeat="dealDetailsData in dealDetails">
										<th align="left" bgcolor="#CCFFFF">Start Date
											(dd/mm/yyyy)</th>
										<td align="left">{{dealDetailsData.dealStartDate}}</td>
										<th align="left" bgcolor="#CCFFFF">End Date (dd/mm/yyyy)</th>
										<td align="left">{{dealDetailsData.dealEndDate}}</td>
										<th align="left" bgcolor="#CCFFFF">Percentage Close (%)</th>
										<td align="left">{{dealDetailsData.percentageClose}}</td>
									</tr>
									<tr ng-repeat="dealData in dealDetails">
										<th align="left" bgcolor="#CCFFFF">Deal TCV</th>
										<td align="left">{{dealData.dealTCV}}</td>
										<th align="left" bgcolor="#CCFFFF">Currency</th>
										<td align="left">{{dealData.currency}}</td>
										<th align="left" bgcolor="#CCFFFF">Penalty (%)</th>
										<td align="left">{{dealData.penaltyPercent}}</td>
									</tr>
								</tbody>
							</table>
							<table></table>
									
									<table  border="1" width="750"  white-space="nowrap"
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection2">
													<thead>
														<tr ng-show='selectededRolesArr.length  > 0'  bgcolor="#CCFFFF" >	
															<th class="firstColLeftAlign tdMasterRoleCode" align="Left"  ng-click="myorder('masterRoleShortDescription')"  >Master Role Code</th>
															<th class="tdSyntelRole" align="Left" ng-click="myorder('syntelRoleName')"  >Syntel Role</th>
															<th align="Left"  ng-click="myorder('proficiencyLevelDescription')"  >Proficiency</th>
															<th align="Left"  ng-click="myorder('bandGrade')" >Syntel Band/ Grade</th>
															<th align="Left"  ng-click="myorder('gcmCODE')" >GCM Level</th>
															<th class="tdCommonDDlWidth" align="Left" >X.O Skills</th>
															<th class="tdCommonDDlWidth" align="Left" >X.O Skills Element</th>
															<th class="tdCommonDDlWidth" align="Left" >X.O Knowledge</th>
															<th class="tdClientRolText" align="Left" >Customer Role</th>
															<th class="tdCommentText" align="Left" >Comments</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection2">
														
														<tr ng-repeat="currentRole in selectededRolesArr |orderBy:forder track by $index">
															<td class="firstColLeftAlign tooltip1">{{currentRole.masterRoleShortDescription}}
															</td>
															<td>{{currentRole.syntelRoleName}} </td>
															<td>{{currentRole.proficiencyLevelDescription}}</td>
															<td>{{currentRole.bandGrade}}</td>
															<td align="Right">{{currentRole.gcmCODE}} &#160; </td> 
															<td>
															<div ng-repeat="item in currentRole.xOSkill"
															  	 ng-if="item.skillId==currentRole.xOSkillIndex">
																 {{item.skillName}}</div>
															</td> 
															<td>
															<div ng-repeat="item in currentRole.xOSkillElement"
																 ng-if="item.skillElementId==currentRole.xOSkillElementIndex">
																 {{item.elementName }}</div> 	
														    </td> 
															<td>
															<div ng-repeat="item in knowledge_name"
																 ng-if="item.knowledgeId==currentRole.xOKnowledgeIndex">
																 {{item.knowledgeName}}</div>
															</td> 
															<td>{{currentRole.clientRole}}
															 </td>
															
															<td>{{currentRole.comments}}</td>
														</tr>
													</tbody>

												</table>
									</table>
		</div>
	</div>
</div>
