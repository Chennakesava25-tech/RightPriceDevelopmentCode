	<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Resource
												Forecast</label>
										</div>
									</div>
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive panel-body">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails" ng-hide="uploadHide">
													<tbody id="tBodyRateCardDetails">
														<tr>
															<th width="2%">Sr. No.</th>
															<th width="6%">Location</th>
															<th width="15%">Skill</th>
															<th width="13%">Skill Element</th>
															<th width="20%">Knowledge</th>
															<th width="8%">Syntel X.O. BG</th>
															<th width="10%">Syntel Roles</th>
															<th width="4%">No of Resources</th>
															<th width="11%">Start Date (dd/mm/yyyy)</th>
															<th width="11%">End Date (dd/mm/yyyy)</th>
															<th></th>
														</tr>
														<tr ng-repeat="row in rows"
															id="{{'ResourceData'+'_'+($index+1)}}">
															<td>{{($index+1)}}</td>
															<!-- location -->
															<td><select
																id="{{'ddlForecastLocation'+'_'+($index+1)}}"
																class="form-control" placeholder="Select"
																name="{{'ddlForecastLocation'+'_'+($index+1)}}"
																ng-model="row.location" required 
																ng-class="{true: 'ng-border'}[(saved && forCast['ddlForecastLocation'+'_'+($index+1)].$invalid)]">
																	<option value="" selected>Select</option>
																	<option value="1">Onsite</option>
																	<option value="2">Offshore</option>
															</select>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['ddlForecastLocation'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please select Location</em>
																</div></td>
															<!--Skils  -->
															<td><select
																id="{{'ddlForecastSkill'+'_'+($index+1)}}"
																class="form-control" placeholder="Select"
																name="{{'ddlForecastSkill'+'_'+($index+1)}}"
																ng-model="row.skillname" required
																ng-options="skil.skillName for skil in skill"
																ng-class="{true: 'ng-border'}[(saved && forCast['ddlForecastSkill'+'_'+($index+1)].$invalid)]"
																ng-change="getXOSkillElementRoles(row.skillname,$index)">
																	<option value="" selected disabled>Select</option>

															</select>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['ddlForecastSkill'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please select Skill</em>
																</div></td>
															<!-- Skill Element -->
															<td><select
																id="{{'ddlForecastSElement'+'_'+($index+1)}}"
																class="form-control" placeholder="select"
																name="{{'ddlForecastSElement'+'_'+($index+1)}}"
																ng-model="row.sElement" required 
																ng-options=" element.elementName for element in row.skillElement"
																ng-class="{true: 'ng-border'}[(saved && forCast['ddlForecastSElement'+'_'+($index+1)].$invalid)]">
																	<option value="" selected disabled>Select</option>
															</select>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['ddlForecastSElement'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please select Skill Element</em>
																</div></td>
															<!-- Knowledge -->
															<td><select
																id="{{'ddlForecastKnowledge'+'_'+($index+1)}}"
																class="form-control" placeholder="select"
																name="{{'ddlForecastKnowledge'+'_'+($index+1)}}"
																ng-model="row.knowledge" required
																ng-options="knowledge.knowledgeName for knowledge  in knowledge"
																ng-class="{true: 'ng-border'}[(saved && forCast['ddlForecastKnowledge'+'_'+($index+1)].$invalid)]">
																	<option value="" selected disabled>Select</option>
															</select>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['ddlForecastKnowledge'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please select Knowledge</em>
																</div></td>
															<!--SyntelX.o BG  -->
															<td><select id="{{'ddlForecastBG'+'_'+($index+1)}}"
																class="form-control" placeholder="select"
																name="{{'ddlForecastBG'+'_'+($index+1)}}"
																ng-model="row.grade" required 
																ng-options=" grade.description for grade in grade"
																ng-class="{true: 'ng-border'}[(saved && forCast['ddlForecastBG'+'_'+($index+1)].$invalid)]">
																	<option value="" selected disabled>Select</option>
															</select>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['ddlForecastBG'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please select Grade</em>
																</div></td>
															<!--Syntel Roles  -->
															<td><select
																id="{{'ddlForecastRole'+'_'+($index+1)}}"
																class="form-control" placeholder="select"
																name="{{'ddlForecastRole'+'_'+($index+1)}}"
																ng-model="row.role" required 
																ng-options=" role.syntelRoleName for role in roles"
																ng-class="{true: 'ng-border'}[(saved && forCast['ddlForecastRole'+'_'+($index+1)].$invalid)]">
																	<option value="" selected disabled>Select</option>
															</select>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['ddlForecastRole'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please select Role </em>
																</div></td>
															<!--No. of resources  -->
															<td><input type="text"
																name="{{'txtNoResource'+'_'+($index+1)}}"
																style="resize: none;" class="form-control"
																id="{{'txtNoResource'+'_'+($index+1)}}" required
																ng-model="row.noResource" ng-pattern="/^[0-9]{1,7}$/"
																ng-class="{true: 'ng-border'} [(saved &&  forCast['txtNoResource'+'_'+($index+1)].$invalid)]">
															</textarea>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['txtNoResource'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="pattern">Invalid no of Resources</em> <em
																		class="error help-block has-error"
																		ng-message="required">Please enter Number Of
																		Resources</em>

																</div></td>
															<!--Start date  -->
															<td>
																<input type="text" class="form-control customDate"
																name="{{'txtStartDate'+'_'+($index+1)}}"
																id="{{'txtStartDate'+'_'+($index+1)}}"
																ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i"
																required ng-model="row.startDate" 
																check-Deal-Start-Date
																databinding="[rfDetail.deal_Start_Date]"
																ng-class="{true: 'ng-border'} [(saved &&  forCast['txtStartDate'+'_'+($index+1)].$invalid && forCast['txtStartDate'+'_'+($index+1)].$invalid )]">
															</textarea>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['txtStartDate'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please enter Start Date</em> <em
																		class="error help-block has-error"
																		ng-message="pattern">Invalid Start date</em> 
																		<em	class="error help-block has-error"
																		ng-message="dateCompare">Start date should be less than End date</em>
																		<em	class="error help-block has-error"
																		ng-message="checkDealStartDate">Start Date should be greater than equal to Deal Start Date.</em>
																</div>
																</td>
															<!--End date  -->
															<td><input type="text"
																name="{{'txtEndDate'+'_'+($index+1)}}"
																class="form-control customDate"
																id="{{'txtEndDate'+'_'+($index+1)}}"
																ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i"
																required ng-model="row.endDate" 
																check-Deal-End-Date
																databinding="[rfDetail.deal_End_Date]"
																ng-class="{true: 'ng-border'} [(saved &&  forCast['txtEndDate'+'_'+($index+1)].$invalid && forCast['txtEndDate'+'_'+($index+1)].$invalid)]">
															</textarea>
																<div class="error-messages" ng-if="saved"
																	ng-messages="forCast['txtEndDate'+'_'+($index+1)].$error">
																	<em class="error help-block has-error"
																		ng-message="required">Please enter End Date</em> <em
																		class="error help-block has-error"
																		ng-message="pattern">Invalid End date</em> 
																		<em	class="error help-block has-error"
																		ng-message="checkDealEndDate">End Date should be less than equal to Deal End Date.</em>
																		<em	class="error help-block has-error"
																		ng-message="endDateCompare">End Date should be
																		Less than Start Date</em>
																</div></td>
															<!-- Delete -->
															<td><a href="#" ng-click="claimdeleteRow(row)">Delete
															</a></td>
														</tr>
														<tr ng-hide="lastRows">
															<td></td>
															<td><div class="col-sm-2">
																	<button type="button" class="btn btn-primary btnSpace"
																		id="btnDelete" ng-click="claimaddRow(row)">Add
																		new Row</button>
																</div></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>

									<!--   Table to display data   -->


									<div class="row marginBottom5px" >
										<div class="col-sm-12">
											<div class="table-responsive panel-body" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails" ng-hide="showHide">
													<tbody id="tBodyRateCardDetails">
														<tr>
															<th width="2%">Sr. No.</th>
															<th width="6%">Location</th>
															<th width="15%">Skill</th>
															<th width="13%">Skill Element</th>
															<th width="20%">Knowledge</th>
															<th width="8%">Syntel X.O. BG</th>
															<th width="10%">Syntel Roles</th>
															<th width="4%">No of Resources</th>
															<th width="11%">Start Date<br> (dd/mm/yyyy)</th>
															<th width="11%">End Date <br>(dd/mm/yyyy)</th>
														</tr>
														<tr ng-repeat="row in rows">
															<td>{{($index+1)}}</td>
															<td ng-if="(row.location==1 )">Onsite</td>
															<td ng-if="(row.location==2 )">Offshore</td>
															<td>{{row.skillName}}</td>
															<td>{{row.elementName}}</td>
															<td>{{row.knowledgeName}}</td>
															<td>{{row.description}}</td>
															<td>{{row.syntelRoleName}}</td>
															<td>{{row.noResource}}</td>
															<td>{{row.startDate}}</td>
															<td>{{row.endDate}}</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-info btnSpace"
												id="btnPrev"
												ng-click="prev();">
												Prev
											</button>
											<button type="button" class="btn btn-primary btnSpace"
												id="btnDetailsSave"
												ng-click="saveResourceData(forCast,'#tableToExport');">
												<img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow" ng-hide="imgHide">
												<span id="btnUpload_Download"> </span>
											</button>
										</div>
									</div>

				<!-- ============================== download functionality ============================ -->
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive panel-body" id="tableToExport"  style="display: none; " >
									<table border="1"   
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												<tr> <td colspan="10" align="Center" bgcolor="#CCFFFF"><Strong>Opportunity Resource Forecasting</Strong></td></tr>
												</tbody>
												</table>
									<table></table>
									<table border="1" 
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												<tr> <td colspan="4" align="Center" bgcolor="#CCFFFF"><b>Deal Details</b></td></tr>
												</tbody>
												</table>
									<table></table>
											<table border="1"  
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
													<tr>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Id</th>
														<td class="thWidth18Per"  align="left">{{rfDetail.cRMDealId}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Customer</th>
														<td class="thWidth18Per"  align="left">{{rfDetail.customerName}}</td>
													</tr>
													<tr>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Description</th>
														<td class="thWidth18Per"  align="left">{{rfDetail.deal_Description}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Type</th>
														<td class="thWidth18Per"  align="left">{{rfDetail.deal_Type_Id}}</td>
													</tr>
													<tr>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal Start Date (dd/mm/yyyy)</th>
														<td class="thWidth18Per"  align="left">{{rfDetail.deal_Start_Date}}</td>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Deal End Date (dd/mm/yyyy)</th>
														<td class="thWidth18Per"  align="left">{{rfDetail.deal_End_Date}}</td>
													</tr>
													<tr>
														<th class="thWidth15Per" align="left" bgcolor="#CCFFFF">Industry</th>
														<td class="thWidth18Per"  align="left">{{rfDetail.industry}}</td>
														<th></th>
														<td></td>
													</tr>
				
													</tbody>
												</table>
													<table></table>
													
												<table border="1" width="750"
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												<tr>
												<td align="left" bgcolor="#CCFFFF">LOB : </td>
												<td>
												<div ng-repeat="item in dealData"
													 ng-if="item.lobCode==forCast.lobCode">
													 {{item.lobCode}}</div>
												</td> 
												 </tr>
												</tbody>
												</table>
														<table></table>
													<table border="1" width="750"
												class="table clsTable table-striped table-bordered table-hover table-condensed "
												id="tblRateCardDetails">
												<tbody id="tBodyRateCardDetails">
												<tr> <td colspan="10" align="Center" bgcolor="#CCFFFF"> <b>Resource Forecast </b></td></tr>
												</tbody>
												</table>
												
													<table></table>
													
												<table border="1" width="750"
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails" ng-hide="showHide">
													<tbody id="tBodyRateCardDetails">
														<tr>
															<th width="2%" align="left" bgcolor="#CCFFFF">Sr. No.</th>
															<th width="6%" align="left" bgcolor="#CCFFFF">Location</th>
															<th width="15%" align="left" bgcolor="#CCFFFF">Skill</th>
															<th width="13%" align="left" bgcolor="#CCFFFF">Skill Element</th>
															<th width="20%" align="left" bgcolor="#CCFFFF">Knowledge</th>
															<th width="8%" align="left" bgcolor="#CCFFFF">Syntel X.O. BG</th>
															<th width="10%" align="left" bgcolor="#CCFFFF">Syntel Roles</th>
															<th width="4%" align="left" bgcolor="#CCFFFF">No of Resources</th>
															<th width="11%" align="left" bgcolor="#CCFFFF">Start Date<br> (dd/mm/yyyy)</th>
															<th width="11%" align="left" bgcolor="#CCFFFF">End Date <br>(dd/mm/yyyy)</th>
														</tr>
														<tr ng-repeat="row in rows" >
															<td>{{($index+1)}}</td>
															<td ng-if="(row.location==1 )">Onsite</td>
															<td ng-if="(row.location==2 )">Offshore</td>
															<td>{{row.skillName}}</td>
															<td>{{row.elementName}}</td>
															<td>{{row.knowledgeName}}</td>
															<td>{{row.description}}</td>
															<td>{{row.syntelRoleName}}</td>
															<td>{{row.noResource}}</td>
															<td>{{row.startDate}}</td>
															<td>{{row.endDate}}</td>
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
					</div>