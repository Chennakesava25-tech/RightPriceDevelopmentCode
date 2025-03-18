<div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                              <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Select Roles</label>
									</div>
                                </div>
                                <div class="panel-body">	
                           		<div class="row marginBottom5px">
									<label class="control-label col-sm-1 textAlignRight required-Field">Tower</label>
                                       <div class="col-sm-2">
                                          <select id="ddlDealRoleDetailsTower" class="form-control" placeholder="Please select" name="ddlDealRoleDetailsTower"
                                          		ng-model="fpdcRoleSelectionFrm.dealRoleDetailsTowerModel" 
                                          		ng-options="to.dealTowerId as to.towerName for to in towerdetails"
                                          		ng-change="getTowerCountryCity(fpdcRoleSelectionFrm.dealRoleDetailsTowerModel)"
                                          		required>
											<option value="" selected disabled>Please select</option>
										</select>
                                	</div>
                                	<label class="control-label col-sm-1 textAlignRight required-Field">Country</label>
                                       <div class="col-sm-2 ">
                                          <input id="ddlDealRoleDetailsCountry" class="form-control" placeholder="" name="ddlDealRoleDetailsCountry"
                                          		ng-model="fpdcRoleSelectionFrm.dealRoleDetailsCountryModel" 
                                          		disabled>	
                                       </div>
                                       <label class="control-label col-sm-1 textAlignRight required-Field">City</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealRoleDetailsCity" class="form-control" placeholder="" name="ddlDealRoleDetailsCity"
                                          		ng-model="fpdcRoleSelectionFrm.dealRoleDetailsCityModel" 
                                          		disabled>
                                       </div>
                                       <label class="control-label col-sm-1 required-Field">City Categorization</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealRoleDetailsCityCat" class="form-control" name="ddlDealRoleDetailsCityCat"
                                          		ng-model="fpdcRoleSelectionFrm.dealRoleDetailsCategorizationModel" 
                                          		disabled>
                                       </div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
	                                	<label class="control-label col-sm-3 textAlignRight">Copy Roles from another Rate Card</label>
	                                     <div class="col-sm-6">
	                                      	<select id="ddlDealRateCard" class="form-control" placeholder="Please select" 
                       	   							name="ddlDealRateCard" ng-model="fpdcRoleSelectionFrm.rateCardModel"  ng-options="rate.rcId as rate.rcId+' - '+ rate.rcName for rate in fpdealratecards" ng-change="getFpRateCardDetails(fpdcRoleSelectionFrm.rateCardModel)" required>
													<option value="" selected disabled>Please select</option>
											</select>
	                                 	</div>
									</div>
									
									<div class="divEmpty"></div>
	                                
									<div class="divEmptyThrice"></div>
									<div class="row" ng-hide="Roleheader">
	                                    <div class="col-sm-12">
											<div class="table-responsive">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection1">
													<thead>
														<tr>
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
															<th class="firstColLeftAlign" ng-click="myorder('masterRoleShortDescription')">Role Description </th>
															<th ng-click="myorder('syntelRoleName')">Syntel Role </th>
															<th ng-click="myorder('clientRole')">Client Role </th>
															<th ng-click="myorder('proficiencyLevelDescription')">Proficiency </th>
															<th ng-click="myorder('x0ProficiencyDescription')">X0 Proficiency </th>
															<th ng-click="myorder('bandGrade')"> Syntel Band/Grade </th>
															<th ng-click="myorder('gcmCODE')">GCM Level</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection1" >
													 <tr ng-repeat="rateCard in searchRoleDetailsResult |orderBy:forder">
													     <td><input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
													      ng-model="rateCard.chkBoxStatus"
													      ng-change=addChkItem(rateCard,rateCard.chkBoxStatus,$index)></td>
														 <td class="firstColLeftAlign tooltip1">{{rateCard.masterRoleShortDescription}}
														  <span class="tooltiptext">{{rateCard.masterRoleLongDescription}}</td>
														  <td>{{rateCard.syntelRoleName}}</td> 
														  <td>{{rateCard.clientRole}}</td> 
														  <td>{{rateCard.proficiencyLevelDescription}} </td>
														  <td>{{rateCard.x0ProficiencyDescription}} </td>
														  <td>{{rateCard.bandGrade}}</td>
														  <td>{{rateCard.gcmCODE}}</td>  
													</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                            		<div class="divEmptyThrice"></div>
									<div class="row text-center" ng-hide="Roleheader">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnAdd" ng-click="addSelectedRoles()">Add</button>	
										</div>
									</div>
									
									
									
									
									<div class="divEmptyThrice"></div>
									<div class="row" ng-hide="rcRoleheader">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection2">
													<thead>
														<tr>	
														<th ng-click="myorder('rcId')" ng-click="myorder('rcId')" >RateCard Id</th>
															<th class="firstColLeftAlign tdMasterRoleCode"  ng-click="myorder('masterRoleShortDescription')">Master Role Code</th>
															<th class="tdSyntelRole"  ng-click="myorder('syntelRoleName')">Syntel Role</th>
															<th  ng-click="myorder('proficiencyLevelDescription')">Proficiency</th>
															<th ng-click="myorder('bandGrade')">Syntel Band/ Grade</th>
															<th ng-click="myorder('gcmCODE')">GCM Level</th>
															<th class="tdCommonDDlWidth">X.O Skills</th>
															<th class="tdCommonDDlWidth">X.O Skills Element</th>
															<th class="tdCommonDDlWidth">X.O Knowledge</th>
															<th class="tdCommonDDlWidth">Client Role</th>
															<th>Remove</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection2">
														
														<tr ng-repeat="currentRole in selectededRolesArr |orderBy:forder track by $index" ng-if="currentRole.rcId>0">
															<td>{{currentRole.rcId}} </td>
															<td class="firstColLeftAlign tooltip1">{{currentRole.masterRoleShortDescription}}
																<span class="tooltiptext">{{currentRole.masterRoleLongDescription}}</span>
															</td>
															<td>{{currentRole.syntelRoleName}} </td>
															<td>{{currentRole.proficiencyLevelDescription}}</td>
															<td>{{currentRole.bandGrade}}</td>
															<td>{{currentRole.gcmCODE}}</td>
															<td>
																<select name="{{'ddlSkill'+'_'+($index+1)}}" type="text" class="form-control"
																id="{{'ddlSkill'+'_'+($index+1)}}"  placeholder="Please select"
																ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['ddlSkill'+'_'+($index+1)].$invalid)]"
																ng-model="currentRole.xOSkillIndex" 
																ng-change="getXOSkillsElementMaster(currentRole.xOSkillIndex,$index);"
																ng-options="skill.skillId as skill.skillName for skill in currentRole.xOSkill" required >
																<option value="" selected disabled>Please select</option>
																</select>
																<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['ddlSkill'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please select Skill</em>
																</div>
															</td> 
															<td>
																<select id="{{'ddlSkillElement'+'_'+($index+1)}}"  type="text" class="form-control" 
																name="{{'ddlSkillElement'+'_'+($index+1)}}" placeholder="Please select"
																ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['ddlSkillElement'+'_'+($index+1)].$invalid)]"
																ng-model="currentRole.xOSkillElementIndex" 
																ng-options="skillElement.skillElementId as skillElement.elementName for skillElement in currentRole.xOSkillElement" required >
																<option value="" selected disabled>Please select</option>
																</select>
																<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['ddlSkillElement'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please select Skill Element </em>
																</div>
															</td> 
															<td>
																<select name="{{'ddlKnowledge'+'_'+($index+1)}}"  type="text" class="form-control" 
																id="{{'ddlKnowledge'+'_'+($index+1)}}" placeholder="Please select"
																ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['ddlKnowledge'+'_'+($index+1)].$invalid)]"
																ng-model="currentRole.xOKnowledgeIndex" 
																ng-options="knowledgeMaster.knowledgeId as knowledgeMaster.knowledgeName for knowledgeMaster in knowledge_name"
																ng-change="checkData();" required>
																	<option value="" selected disabled>Please select Knowledge</option>
																</select>
																<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['ddlKnowledge'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please select Skill Knowledge </em>
																</div>
															</td> 
															<td>
															<input name="{{'txtClientRoleTbl2Row1'+'_'+($index+1)}}" type="text" class="form-control" 
															id="{{'txtClientRoleTbl2Row1'+'_'+($index+1)}}"
															ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['txtClientRoleTbl2Row1'+'_'+($index+1)].$invalid)]"
															ng-model="currentRole.clientRole" required >
															<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['txtClientRoleTbl2Row1'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please enter Client Role</em>
															</div>
															 </td>
															<!-- <td><input name="txtCommentsTbl2Row1" type="text" class="form-control" id="txtCommentsTbl2Row1" ng-model="currentRole.comments" required > </td> -->
															<td><button type="button" id="btnRemoveRow1" ng-click="deleteRow($index)">-</button></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                            		<div class="divEmptyThrice"></div>
                                </div>
                                
                       			 <div class="row marginBottom5px">
									<label class="control-label col-sm-3 textAlignRight">
									Search Roles from Role Master</label> <input
									type="checkbox" name="cbxMasterRole" id="cbxMasterRole" ng-model="fpdcRoleSelectionFrm.masterRolechkbox"
							class="margingRightChkBx" ng-change="checkMasterRole(fpdcRoleSelectionFrm.masterRolechkbox)">
							
					           </div>
                                
                                <div class="panel-body" >
									<div class="divEmptyThrice"></div>
									<div class="divEmpty"></div>
									<div class="row marginBottom5px " ng-if="masterroleKPO == true">
	                                	<label class="control-label col-sm-3 textAlignRight">Search Contractor Roles</label>
	                                     <div class="col-sm-6 ">
	                                     <div class = "tooltip1">
	                                      	<input name="txtSearch" type="text" class="form-control" id="txtSearch"
                                            	   ng-model="fpdcRoleSelectionFrm.searchModel" ng-init="fpdcRoleSelectionFrm.searchModel='KPO'"  disabled>
										<span class="tooltiptext" style="width:450px"> "Search Roles from Role Master" option shall only be available for Industry Type IT  </span>
										</div>
	                                 	</div>
	                                 	<div class="col-sm-3">		
											<button type="button" class="btn btn-primary" id="btnSearch" ng-click="searchRateCardData()">Search</button>
										</div>
									</div>
	                                <div class="row marginBottom5px " ng-if="masterroleManage == true">
	                                	<label class="control-label col-sm-3 textAlignRight">Search Contractor Roles</label>
	                                     <div class="col-sm-6 tooltip1">
	                                      	<input name="txtSearch" type="text" class="form-control" id="txtSearch"
                                            	   ng-model="fpdcRoleSelectionFrm.searchModel" ng-init="fpdcRoleSelectionFrm.searchModel='Contractor'"  disabled>
										<span class="tooltiptext" style="width:450px"> "Search Roles from Role Master" option shall only be available for Project Type - Development - Fixed Price OR Maintenance - Fixed Price </span>
	                                 	</div>
	                                 	<div class="col-sm-3">		
											<button type="button" class="btn btn-primary" id="btnSearch" ng-click="searchRateCardData()">Search</button>
										</div>
									</div>
									 <div class="row marginBottom5px" ng-if="masterroleFixed == true">
	                                	<label class="control-label col-sm-3 textAlignRight">Search Roles</label>
	                                     <div class="col-sm-6">
	                                      	<input name="txtSearch" type="text" class="form-control" id="txtSearch"
                                            	   ng-model="fpdcRoleSelectionFrm.searchModel"   >
	                                 	</div>
	                                 	<div class="col-sm-3">		
											<button type="button" class="btn btn-primary" id="btnSearch" ng-click="searchRateCardData()">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row" ng-hide="RoleMasterheader">
	                                    <div class="col-sm-12">
											<div class="table-responsive">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection1">
													<thead>
														<tr>
															<!-- <th>Source</th> -->
															<th>Select
															<span><input type="checkbox"
																	name="{{'cbxItem'+'_'+($index+1)}}"
																	id="{{'cbxItem'+'_'+($index+1)}}"
																	class="margingRightChkBx "
																	ng-model="selectAllCheckBoxChange"
																	ng-change=addAllCheckedItemsChange(searchRoleDetailsResultChange,selectAllCheckBoxChange)>
															</span></th> 
															<th class="firstColLeftAlign"ng-click="myorder('masterRoleShortDescription')">Role Description </th>
															<th ng-click="myorder('syntelRoleName')">Syntel Role </th>
															<th ng-click="myorder('proficiencyLevelDescription')">Proficiency </th>
															<th ng-click="myorder('proficiencyLevelDescription')">X0 Proficiency </th>
															<th ng-click="myorder('bandGrade')">Syntel Band /Grade </th>
															<th ng-click="myorder('gcmCODE')">GCM Level</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection1" >
													 <tr ng-repeat="rateCardRole in searchRoleDetailsResultChange|orderBy:forder">
													     <td><input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
													      ng-model="rateCardRole.chkBoxStatusChange" ng-change=addChkItem(rateCardRole,rateCardRole.chkBoxStatusChange,$index)></td>
														 <td class="firstColLeftAlign tooltip1">{{rateCardRole.masterRoleShortDescription}}
														  <span class="tooltiptext">{{rateCardRole.masterRoleLongDescription}}</td>
														  <td>{{rateCardRole.syntelRoleName}} </td> 
														  <td>{{rateCardRole.proficiencyLevelDescription}} </td>
														  <td>{{rateCardRole.proficiencyLevelDescription}} </td>
														  <td>{{rateCardRole.bandGrade}} </td>
														  <td>{{rateCardRole.gcmCODE}} </td> 														   
													</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                            		<div class="divEmptyThrice"></div>
									<div class="row text-center"  ng-hide="RoleMasterheader">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnAdd" ng-click="addSelectedRolesChange()">Add</button>	
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row" ng-hide="rcRoleMasterheader">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection2">
													<thead>
														<tr>	
															<th class="firstColLeftAlign tdMasterRoleCode"ng-click="myorder('masterRoleShortDescription')">Master Role Code</th>
															<th class="tdSyntelRole"ng-click="myorder('syntelRoleName')">Syntel Role</th>
															<th ng-click="myorder('proficiencyLevelDescription')">Proficiency</th>
															<th ng-click="myorder('bandGrade')" >Syntel Band/Grade</th>
															<th ng-click="myorder('gcmCODE')">GCM Level</th>
															<th class="tdCommonDDlWidth">X.O Skills</th>
															<th class="tdCommonDDlWidth">X.O Skills Element</th>
															<th class="tdCommonDDlWidth">X.O Knowledge</th>
															<th class="tdClientRolText">Client Role</th>
															<th>Remove</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection2">
														
														<tr ng-repeat="currentRole in selectededRolesArr|orderBy:forder track by $index" ng-if="currentRole.rcId==0">
															<td class="firstColLeftAlign tooltip1">{{currentRole.masterRoleShortDescription}}
																<span class="tooltiptext">{{currentRole.masterRoleLongDescription}}</span>
															</td>
															<td>{{currentRole.syntelRoleName}} </td>
															<td>{{currentRole.proficiencyLevelDescription}}</td>
															<td>{{currentRole.bandGrade}}</td>
															<td>{{currentRole.gcmCODE}}</td>
															<td>
																<select  name="{{'skillsMasterType'+'_'+($index+1)}}" type="text" class="form-control"
																id="{{'skillsMasterType'+'_'+($index+1)}}"  placeholder="Please select"
																ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['skillsMasterType'+'_'+($index+1)].$invalid)]"
																ng-model="currentRole.xOSkillIndex" 
																ng-options="skill.skillId as skill.skillName for skill in currentRole.xOSkill"
																ng-change="getXOSkillsElementMaster(currentRole.xOSkillIndex,$index);" required>
																	<option value="" selected disabled>Please select </option>
																</select>
																<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['skillsMasterType'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please select Skill </em>
																</div>
															</td> 
															<td>
																<select name="{{'mstrddlSkillElement'+'_'+($index+1)}}" type="text" class="form-control"
																id="{{'mstrddlSkillElement'+'_'+($index+1)}}"  placeholder="Please select"
																ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['mstrddlSkillElement'+'_'+($index+1)].$invalid)]"
																ng-model="currentRole.xOSkillElementIndex" 
																ng-options="skillElement.skillElementId as skillElement.elementName for skillElement in currentRole.xOSkillElement"
																ng-change="checkData(currentRole.xOSkillElementIndex,$index);" required>
																<option value="" selected disabled>Please select </option>
																</select>
																<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['mstrddlSkillElement'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please select Skill Element </em>
																</div>
															</td> 
															<td>
																<select name="{{'mstrddlKnowledge'+'_'+($index+1)}}" type="text" class="form-control"
																id="{{'mstrddlKnowledge'+'_'+($index+1)}}"  placeholder="Please select"
																ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['mstrddlKnowledge'+'_'+($index+1)].$invalid)]"
																ng-model="currentRole.xOKnowledgeIndex" 
																ng-options="knowledgeMaster.knowledgeId as knowledgeMaster.knowledgeName for knowledgeMaster in knowledge_name"
																ng-change="checkData();" required>
																<option value="" selected disabled>Please select</option>
																</select>
																<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['mstrddlKnowledge'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please select Knowledge</em>
																</div>
															</td> 
														
															
															
															<td><input name="{{'mstrtxtClientRoleTbl2Row1'+'_'+($index+1)}}" type="text" class="form-control" 
																id="{{'mstrtxtClientRoleTbl2Row1'+'_'+($index+1)}}" 
																ng-class="{true: 'ng-border'} [(onSave && fpdcRoleSelectionFrm['mstrtxtClientRoleTbl2Row1'+'_'+($index+1)].$invalid)]"
																ng-model="currentRole.clientRole " required>
																<div class="error-messages" ng-if="onSave"
																ng-messages="fpdcRoleSelectionFrm['mstrtxtClientRoleTbl2Row1'+'_'+($index+1)].$error">
																<em class="error help-block has-error"
																ng-message="required">Please enter Client Role </em>
																</div>
															</td>
<!-- 															<td><input name="txtCommentsTbl2Row1" type="text" class="form-control" id="txtCommentsTbl2Row1" ng-model="currentRole.comments" required> </td>
 -->															<td><button type="button" id="btnRemoveRow1" ng-click="deleteRow($index)">-</button></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									
		
                                </div>
                            		<div class="divEmptyThrice"></div>
                            		   
  <!-------------------------------------------------------------------Export to Excel    ----------------------------------------->   
  
                            		<div class="table-responsive" id="tableToExport" style="display:none" >
					        					<table border="1" width="750" 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails">
													<tr>
														<td align="Center" bgcolor="#CCFFFF" colspan=9>FP Deal Creation - Role Selection</td>
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
												
												<table border="1"
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection2">
													<thead>
														<tr>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Rate Card ID</th>	
															<th class="firstColLeftAlign tdMasterRoleCode" bgcolor="#CCFFFF">Master Role Code</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Syntel Role</th>
															<th bgcolor="#CCFFFF">Proficiency</th>
															<th bgcolor="#CCFFFF">Syntel Band/ Grade</th>
															<th bgcolor="#CCFFFF">GCM Level</th>
															<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Skills</th>
															<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Skills Element</th>
															<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Knowledge</th>
															<th class="tdClientRolText" bgcolor="#CCFFFF">Client Role</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection2">
														<tr ng-repeat="currentRole in exportRCData track by $index">
															<td> {{currentRole.rcId}}
															<td class="firstColLeftAlign tooltip1">{{currentRole.masterRoleShortDescription}}
																<span class="tooltiptext">{{currentRole.masterRoleLongDescription}}</span>
															</td>
															<td>{{currentRole.syntelRoleName}} </td>
															<td>{{currentRole.proficiencyLevelDescription}}</td>
															<td>{{currentRole.bandGrade}}</td>
															<td align="Right">{{currentRole.gcmCODE}}&#160;</td> 
															<td><div ng-repeat="item in currentRole.xOSkill"
															  	 ng-if="item.skillId==currentRole.xOSkillIndex">
																 {{item.skillName}}</div></td>
															<td><div ng-repeat="item in currentRole.xOSkillElement"
																 ng-if="item.skillElementId==currentRole.xOSkillElementIndex">
																 {{item.elementName }}</div> </td> 
															<td><div ng-repeat="item in knowledge_name"
																 ng-if="item.knowledgeId==currentRole.xOKnowledgeIndex">
																 {{item.knowledgeName}}</div></td> 
															<td>{{currentRole.clientRole}}
															</td>
														</tr>
													</tbody>
												</table>
																								
												<table></table>
												
												<table border="1"
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection2">
													<thead>
														<tr>	
															<th></th>
															<th class="firstColLeftAlign tdMasterRoleCode" bgcolor="#CCFFFF">Master Role Code</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Syntel Role</th>
															<th bgcolor="#CCFFFF">Proficiency</th>
															<th bgcolor="#CCFFFF">Syntel Band/ Grade</th>
															<th bgcolor="#CCFFFF">GCM Level</th>
															<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Skills</th>
															<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Skills Element</th>
															<th class="tdCommonDDlWidth" bgcolor="#CCFFFF">X.O Knowledge</th>
															<th class="tdClientRolText" bgcolor="#CCFFFF">Client Role</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection2">
														
														<tr ng-repeat="currentRole in exportMastersData track by $index" ng-if="currentRole.rcId==0">
															<td></td>
															<td class="firstColLeftAlign tooltip1">{{currentRole.masterRoleShortDescription}}
																<span class="tooltiptext">{{currentRole.masterRoleLongDescription}}</span>
															</td>
															<td>{{currentRole.syntelRoleName}} </td>
															<td>{{currentRole.proficiencyLevelDescription}}</td>
															<td>{{currentRole.bandGrade}}</td>
															<td align="Right">{{currentRole.gcmCODE}}&#160;</td>
															<td><div ng-repeat="item in currentRole.xOSkill"
															  	 ng-if="item.skillId==currentRole.xOSkillIndex">
																 {{item.skillName}}</div></td>
															<td><div ng-repeat="item in currentRole.xOSkillElement"
																 ng-if="item.skillElementId==currentRole.xOSkillElementIndex">
																 {{item.elementName }}</div> </td> 
															<td><div ng-repeat="item in knowledge_name"
																 ng-if="item.knowledgeId==currentRole.xOKnowledgeIndex">
																 {{item.knowledgeName}}</div></td> 
															<td>{{currentRole.clientRole}}
															</td>
														</tr>
													</tbody>
												</table>
											</div>
                            		   
      <!-------------------------------------------------------------------Export to Excel end   ----------------------------------------->                     		   
                            		   
                            		   <div class="row">
	<!-- <div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Add Contractor
							Role</label>
						
					</div>
				</div>
				<div class="panel-body" >
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
                            </div>
                        </div>
                    </div>
                </div>
  
									<div class="divEmptyThrice"></div>
                            		<div class="row text-center">
										<div class="col-sm-12">
										    <button class="btn btn-primary btnSpace" type="button" ng-click="exportToExcel('#tableToExport');">
      										     <img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
											<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-disabled = "isSaveDisabled" ng-click="save(fpdcRoleSelectionFrm);">Save</button>
<!-- 											<button type="button" class="btn btn-danger btnSpace" id="btnCancel">Cancel</button>
 -->											<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
										</div>
									</div>