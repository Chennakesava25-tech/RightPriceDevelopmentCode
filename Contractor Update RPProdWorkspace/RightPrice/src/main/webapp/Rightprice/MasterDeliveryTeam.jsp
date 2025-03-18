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
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/MasterDeliveryTeamController.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterDeliveryTeamController" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Delivery Team</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<ng-form name="deliveryTeamForm" id="deliveryTeamForm" >
									<div class="panel-heading panelHeadingStyle"  ng-click="ShowHideView()">
										<div class="row ">
											<label class="control-label col-sm-10">View Verticals Team</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
									</div>
									<div class="panel-body" ng-hide = "ViewHidden">
										<div class="row marginBottom5px">
											<div class="col-sm-3"></div>
	                                        <label class="control-label col-sm-1 textAlignRight required-Field">Vertical</label>
	                                        <div class="col-sm-3">
												<select id="ddlViewVertical" class="form-control" placeholder="Please select" name="ddlViewVertical" 
	                                           		ng-model="deliveryTeamForm.ddlViewVerticalModel" ng-options="vs.verticalName for vs in vertical"
	                                           		ng-class="{true: 'ng-border'} [(onSearch && deliveryTeamForm.ddlViewVertical.$invalid)]" required>
													<option value="" selected >Please select</option>
												</select>
												<div class="error-messages" ng-if= "onSearch" ng-messages="deliveryTeamForm.ddlViewVertical.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Vertical.</em>
										        </div>
	                                        </div>
	                                        <div class="col-sm-4">		
												<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch"  ng-click="searchData(deliveryTeamForm);">Search</button>												
											</div>
										</div>                                   	
										<div class="divEmptyThrice"></div>
	                                 	<div class="row">
	                                   		<div class="col-sm-12">
	                                   			<div class="table-responsive ">
													<table
														class="table clsTable table-striped table-bordered table-hover"
														id="tblViewVerticalsTeam">
														<thead>
															<tr>
																<th>Employee Id</th>
																<th>Employee LAN Id</th>
																<th>Name</th>
																<th>Designation</th>
															</tr>
														</thead>
														<tbody id="tBodyViewVerticalsTeam" >
															<tr >
															<tr ng-repeat ="vertical in viewVerticalData">
															<td>{{vertical.EMP_ID}}</td>
															<td>{{vertical.USERID}}</td>
															<td>{{vertical.EMP_NAME}}</td>
															<td>{{vertical.EMP_DESC}}</td>
															</tr>		
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</ng-form>
							</div>
						</div>
					</div>
				</div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="addTeamDetailForm" id="addTeamDetailForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideTeamMembers()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Allocate Team Members to Vertical</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "TeamMembersHidden">
                                	<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Vertical</label>
                                        <div class="col-sm-3">
                                         	<select id="ddlTeamMembersVertical" class="form-control" placeholder="Please select" name="ddlTeamMembersVertical" 
	                                        	ng-model="addTeamDetailForm.ddlTeamMembersVerticalModel" ng-options="vs.verticalId as vs.verticalName for vs in vertical"
	                                           	ng-class="{true: 'ng-border'} [(onAdd && addTeamDetailForm.ddlTeamMembersVertical.$invalid)]" required
	                                           	ng-change="getCustomers(addTeamDetailForm.ddlTeamMembersVerticalModel);">
												<option value="" selected >Please select</option>
											</select>
											<div class="error-messages" ng-if= "onAdd" ng-messages="addTeamDetailForm.ddlTeamMembersVertical.$error">
										    	<em class="error help-block has-error" ng-message="required">Please select Vertical.</em>
									        </div>
                                        </div>
                                   	 	<label class="control-label col-sm-3 textAlignRight required-Field">Customers</label>
                                        <div class="col-sm-3">
                                        	<select id="ddlCustomers" class="form-control" placeholder="Please select" name="ddlCustomers" 
	                                        	ng-model="addTeamDetailForm.ddlCustomersModel" ng-options="cs.customer.customerId as cs.customer.customerName for cs in customers"
	                                           	ng-class="{true: 'ng-border'} [(onAdd && addTeamDetailForm.ddlCustomers.$invalid)]" required>
												<option value="" selected >Please select</option>
											</select>
											<div class="error-messages" ng-if="onAdd" ng-messages="addTeamDetailForm.ddlCustomers.$error">
										    	<em class="error help-block has-error" ng-message="required">Please select Vertical.</em>
									        </div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">                                   		
                                   	 	<label class="control-label col-sm-2 textAlignRight required-Field">Employee ID</label>
                                        <div class="col-sm-3">
                                        	<input name="txtTeamMembersEmpId" type="text" class="form-control" id="txtTeamMembersEmpId"
                                        		ng-model="addTeamDetailForm.txtTeamMembersEmpIdModel" 
                                           		ng-class="{true: 'ng-border'} [(onAdd && addTeamDetailForm.txtTeamMembersEmpId.$invalid)]"required>
                                       		<div class="error-messages" ng-if= "onAdd" ng-messages="addTeamDetailForm.txtTeamMembersEmpId.$error">
										    	<em class="error help-block has-error" ng-message="required">Please enter Employee ID.</em>
									        </div>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnTeamMembersAdd" ng-click="getUserDetails(addTeamDetailForm);">Add</button>						
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="addTeamDetailForm.tblTeamMembers">
													<thead>
														<tr>
															<th>Select</th>
															<th>Employee Id</th>
															<th>Employee LAN Id</th>
															<th>Name</th>
															<th>Designation</th>
														</tr>
													</thead>
													<tbody id="tBodyTeamMembers" >
														<tr ng-repeat ="emp in selectedEmp">
														<td ng-model="addTeamDetailForm.chklabel[emp.EMP_ID]"><input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
														  ng-model="addTeamDetailForm.chkItem[$index+1]"  ng-change="addChkItem(emp,addTeamDetailForm.chkItem[$index+1],$index)" ng-checked="false"></td>
														<td>{{emp.EMP_ID}}</td>
														<td>{{emp.USERID}}</td>
														<td>{{emp.EMP_NAME}}</td>
														<td>{{emp.EMP_DESC}}</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>													
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnTeamMembersSave" ng-click ="saveEmpDetails();">Save</button>						
											<button type="button" class="btn btn-danger" id="btnTeamMembersCancel" ng-click = "clearForm(addTeamDetailForm)">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                            </ng-form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="updateTeamDetailForm" id="updateTeamDetailForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideDealocateTeamMembers()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Deallocate Team Members to Vertical</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "DealocateTeamMembersHidden">
                                	<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-1 col-sm-offset-3 textAlignRight required-Field">Vertical</label>
                                        <div class="col-sm-3">
                                         	<select id="ddlUpdateTeamMembersVertical" class="form-control" placeholder="Please select" name="ddlUpdateTeamMembersVertical" 
	                                        	ng-model="updateTeamDetailForm.ddlUpdateTeamMembersVerticalModel" ng-options="vs.verticalId as vs.verticalName for vs in vertical"
	                                           	ng-class="{true: 'ng-border'} [(onUpdateSearch && addTeamDetailForm.ddlTeamMembersVertical.$invalid)]" required>
	                                           	<!-- ng-change="getCustomers(updateTeamDetailForm.ddlUpdateTeamMembersVerticalModel);"> -->
												<option value="" selected >Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdateSearch" ng-messages="updateTeamDetailForm.ddlUpdateTeamMembersVertical.$error">
										    	<em class="error help-block has-error" ng-message="required">Please select Vertical.</em>
									        </div>
                                        </div>
                                        <div class="col-sm-4">
                                        	<button type="button" class="btn btn-primary btnSpace" id="btnUpdateTeamMemberssearch" ng-click="getTeamMemberUserDetails(updateTeamDetailForm);">Search</button>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
									<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="updateTeamDetailForm.tblUpdateTeamMembers">
													<thead>
														<tr>
															<th>Select</th>
															<th>Employee Id</th>
															<th>Employee LAN Id</th>
															<th>Name</th>
															<th>Designation</th>
														</tr>
													</thead>
													<tbody id="tBodyTeamMembers" >
														<tr ng-repeat ="emp in viewUpdateVerticalData">
														<td ng-model="updateTeamDetailForm.chklabel[emp.EMP_ID]">
															<input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
														  	ng-model="updateTeamDetailForm.chkItem[$index+1]" ng-change="deallocateChkItem(emp,updateTeamDetailForm.chkItem[$index+1],$index)" ng-checked="false">
													  	</td>
														<td>{{emp.EMP_ID}}</td>
														<td>{{emp.USERID}}</td>
														<td>{{emp.EMP_NAME}}</td>
														<td>{{emp.EMP_DESC}}</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>													
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnTeamMembersDeallocate" ng-click ="deallocateEmp();">Save</button>						
											<button type="button" class="btn btn-danger" id="btnTeamMembersCancel" ng-click = "clearDeallocateTMForm(updateTeamDetailForm)">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                            </ng-form>
                        </div>
                    </div>
                </div>
                 <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="addRiskManagersMemberForm" id="addRiskManagersMemberForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideRiskManagersMembers()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Allocate RiskManagers members to Vertical</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "RiskManagersMembersHidden">
                               		<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Vertical</label>
                                   		<div class="col-sm-3">
	                                   		<select id="ddlRiskManagersMembersVertical" class="form-control" placeholder="Please select" name="ddlRiskManagersMembersVertical" 
		                                        	ng-model="addRiskManagersMemberForm.ddlRiskManagersMembersVerticalModel" ng-options="vs.verticalId as vs.verticalName for vs in vertical"
		                                           	ng-class="{true: 'ng-border'} [(onRiskManagersAdd && addRiskManagersMemberForm.ddlRiskManagersMembersVertical.$invalid)]" required
		                                           	ng-change="getCustomers(addRiskManagersMemberForm.ddlRiskManagersMembersVerticalModel);"> 
													<option value="" selected >Please select</option>
											</select>
											<div class="error-messages" ng-if= "onRiskManagersAdd" ng-messages="addRiskManagersMemberForm.ddlRiskManagersMembersVertical.$error">
										    	<em class="error help-block has-error" ng-message="required">Please select Vertical.</em>
									        </div>
								        </div>
								        <label class="control-label col-sm-3 textAlignRight required-Field">Customers</label>
                                        <div class="col-sm-3">
                                        	<select id="ddlRiskManagersCustomers" class="form-control" placeholder="Please select" name="ddlRiskManagersCustomers" 
	                                        	ng-model="addRiskManagersMemberForm.ddlRiskManagersCustomersModel" ng-options="cs.customer.customerId as cs.customer.customerName for cs in customers"
	                                           	ng-class="{true: 'ng-border'} [(onRiskManagersAdd && addRiskManagersMemberForm.ddlRiskManagersCustomers.$invalid)]" required>
												<option value="" selected >Please select</option>
											</select>
											<div class="error-messages" ng-if="onRiskManagersAdd" ng-messages="addRiskManagersMemberForm.ddlRiskManagersCustomers.$error">
										    	<em class="error help-block has-error" ng-message="required">Please select Vertical.</em>
									        </div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Employee ID</label>
                                        <div class="col-sm-3">
                                           <input name="txtRiskManagersMembersEmpId" type="text" class="form-control" id="txtRiskManagersMembersEmpId"
                                           		  ng-model="addRiskManagersMemberForm.txtRiskManagersMembersEmpIdModel" required
                                           		  ng-class="{true: 'ng-border'} [(onRiskManagersAdd && addRiskManagersMemberForm.txtRiskManagersMembersEmpId.$invalid)]" required>
                                       		<div class="error-messages" ng-if= "onRiskManagersAdd" ng-messages="addRiskManagersMemberForm.txtRiskManagersMembersEmpId.$error">
										    	<em class="error help-block has-error" ng-message="required">Please enter Employee ID.</em>
									        </div>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnRiskManagersMembersAdd" ng-click="getRiskManagersUserDetails(addRiskManagersMemberForm);">Add</button>						
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="addRiskManagersMemberForm.tblTeamMembers">
													<thead>
														<tr>
															<th>Select</th>
															<th>Employee Id</th>
															<th>Employee LAN Id</th>
															<th>Name</th>
															<th>Designation</th>
														</tr>
													</thead>
													<tbody id="tBodyRiskManagersMembers" >
														<!-- <tr >
														</tr>	 -->	
														<tr ng-repeat ="emp in selectedRiskManagersEmp">
														<td ng-model="addRiskManagersMemberForm.chklabel[emp.EMP_ID]"><input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
														  ng-model="addRiskManagersMemberForm.chkItem[$index+1]"  ng-change="addChkRiskManagersItem(emp,addRiskManagersMemberForm.chkItem[$index+1],$index)" ng-checked="false"></td>
														<td>{{emp.EMP_ID}}</td>
														<td>{{emp.USERID}}</td>
														<td>{{emp.EMP_NAME}}</td>
														<td>{{emp.EMP_DESC}}</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>													
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="addRiskManagersMemberFormSave" ng-click ="saveRiskManagersEmpDetails(emp)">Save</button>						
											<button type="button" class="btn btn-danger" id="addRiskManagersMemberFormCancel" ng-click = "clearRiskManagersForm(addRiskManagersMemberForm)">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                         </ng-form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="deallocateRiskManagersMemberForm" id="deallocateRiskManagersMemberForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideDeallocateQM()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Deallocate RiskManagers members to Vertical</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "DeallocateQMHidden">
                               		<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-1 col-sm-offset-3 textAlignRight required-Field">Vertical</label>
                                   		<div class="col-sm-3">
	                                   		<select id="ddlDeallocateQMVertical" class="form-control" placeholder="Please select" name="ddlDeallocateQMVertical" 
		                                        	ng-model="deallocateRiskManagersMemberForm.ddlDeallocateQMVerticalModel" ng-options="vs.verticalId as vs.verticalName for vs in vertical"
		                                           	ng-class="{true: 'ng-border'} [(onRiskManagersDeallocate && deallocateRiskManagersMemberForm.ddlDeallocateQMVertical.$invalid)]" required>
		                                           	<!-- ng-change="getCustomers(addRiskManagersMemberForm.ddlRiskManagersMembersVerticalModel);">  -->
													<option value="" selected >Please select</option>
											</select>
											<div class="error-messages" ng-if= "onRiskManagersDeallocate" ng-messages="deallocateRiskManagersMemberForm.ddlDeallocateQMVertical.$error">
										    	<em class="error help-block has-error" ng-message="required">Please select Vertical.</em>
									        </div>
								        </div>
								        <div class="col-sm-4">
                                        	<button type="button" class="btn btn-primary" id="btnUpdateQMsearch" ng-click="getQMUserDetails(deallocateRiskManagersMemberForm);">Search</button>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
									<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed">
													<thead>
														<tr>
															<th>Select</th>
															<th>Employee Id</th>
															<th>Employee LAN Id</th>
															<th>Name</th>
															<th>Designation</th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat ="emp in viewQMVerticalData">
														<td ng-model="deallocateRiskManagersMemberForm.chklabel[emp.EMP_ID]">
															<input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
														  	ng-model="deallocateRiskManagersMemberForm.chkItem[$index+1]" ng-change="deallocateQMChkItem(emp,deallocateRiskManagersMemberForm.chkItem[$index+1],$index)" ng-checked="false">
													  	</td>
														<td>{{emp.EMP_ID}}</td>
														<td>{{emp.USERID}}</td>
														<td>{{emp.EMP_NAME}}</td>
														<td>{{emp.EMP_DESC}}</td>
														</tr>	
													</tbody>													
												</table>
											</div>
										</div>
									</div>													
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnQMDeallocate" ng-click ="deallocateQMEmp();">Save</button>						
											<button type="button" class="btn btn-danger" id="btnQMCancel" ng-click = "clearDeallocateQMForm(deallocateRiskManagersMemberForm)">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                         </ng-form>
                        </div>
                    </div>
                </div>
	         </form>
        </div>
    </div>
     </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
