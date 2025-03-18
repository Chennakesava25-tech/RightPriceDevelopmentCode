<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>\
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
	<script
	src="${contextPath}/resources/js/crypto-js.min.js"></script>
<script
	src="${contextPath}/resources/js/aes.js"></script>
	<script
	src="${contextPath}/resources/js/core.min.js"></script>
	<script
	src="${contextPath}/resources/js/cipher-core.min.js"></script>
	<script
	src="${contextPath}/resources/js/mode-cfb.min.js"></script>
	<script
	src="${contextPath}/resources/js/pad-pkcs7.min.js"></script>
<script
	src="${contextPath}/resources/js/pbkdf2.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/DealCreationSummaryController.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="DealCreationSummaryController">
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'" ng-init="getDealSummaryData('<%=session.getAttribute("username")%>');"></div>
<%--      <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'" ng-init="getDealSummaryData('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>');"></div> --%>
     <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">T&M Deal Creation - Summary </h3>
            </div>
        </div>	
        <div>
			<form class="form-inline" role="form" name="teamDeal" id="teamDeal">
				<div id="includedT&PStages"
					ng-include="'${contextPath}/Portal/T&MdealCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationT&MInformationTable.jsp'"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info ">
								<div class="panel-heading panelHeadingStyle">
									<div class="row ">
										<label class="control-label col-sm-10">Deal - Summary
										</label>
										</div>
								</div>
								<div class="panel-body">
									<!-- <div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Deal
											Id</label>
										<div class="col-sm-3">
											<input name="txtDealId" type="text" class="form-control"
												id="txtDealId" ng-model="teamDeal.txtDealIdModel" required disabled>
										</div>
										<label
											class="control-label col-sm-3 textAlignRight required-Field">Estimated
											TCV</label>
										<div class="col-sm-3 ">
											<input name="txtEstimatedTCV" type="text"
												class="form-control" id="txtEstimatedTCV"
												ng-model="teamDeal.txtEstimatedTCVModel" required disabled>
										</div>
									</div> -->
									<!-- <div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Estimated
											Revenue</label>
										<div class="col-sm-3 ">
											<input name="txtEstimatedRevenue" type="text"
												class="form-control" id="txtEstimatedRevenue"
												ng-model="teamDeal.txtEstimatedRevenueModel" required disabled>
										</div>
										<label
											class="control-label col-sm-3 textAlignRight required-Field">Send
											to DeMS</label>
										<div class="col-sm-3">
											<input type="checkbox" name="cbxSendToDeMs"
												id="cbxSendToDeMs" class="margingRightChkBx">
										</div>
									</div> -->
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
										<div class = "col-sm-12">
											<label><i><font color="red">All rates are in {{currencyName}}</font></i></label>
										</div>
									</div>
									<div class="row marginBottom5px">
										<div class="col-sm-1"></div>
										<div class="col-sm-10">
											<div class="table-responsive">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblParticulars">
													<tbody id="tBodyParticulars">
														<tr>
															<th class="firstColLeftAlign">Rate Card Name</th>
															
															<th class="firstColLeftAlign">Weightage %</th>
															<th class="firstColLeftAlign">Blended Rate </th>
															<th class="firstColLeftAlign">Blended Cost </th>
															<th class="firstColLeftAlign">PM% post VR</th>
															<th class="firstColLeftAlign">Onsite %</th>
															<th class="firstColLeftAlign">Offshore %</th>
														</tr>
														<tr ng-repeat = "ratecardsum in ratecardSummary">
															<td class="firstColLeftAlign">{{ratecardsum.rcName}}</td>
															<td class="firstColLeftAlign">{{ratecardsum.weightage}}</td>
															<td class="firstColLeftAlign">{{ratecardsum.bl_rate}}</td>
															<td class="firstColLeftAlign">{{ratecardsum.bl_cost}}</td>
															<td class="firstColLeftAlign">{{ratecardsum.gm_Per}}</td>
															<td class="firstColLeftAlign">{{ratecardsum.onsite_Per}}</td>
															<td class="firstColLeftAlign">{{ratecardsum.offshore_Per}}</td>
															
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
										<!-- <label class="control-label col-sm-12 textAlignLeft redColor">Note:
											Weightage should be based on utilization of ratecard</label> -->
									</div>
									<!-- <div class="row">
										<div class="col-sm-1"></div>
										<div class="col-sm-10">
											<div class="table-responsive">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblDealSummary">
													<tbody id="tBodyDealSummary">
														<tr>
															<th class="firstColLeftAlign">Rate Card Id</th>
															<th class="firstColLeftAlign">PM%</th>
															<th class="firstColLeftAlign">Weightage</th>
														</tr>
														<tr ng-repeat = "rateCardDetail in rateCardDetailArray">
															<td class="firstColLeftAlign">{{rateCardDetail.rcId}}</td>
															<td class="firstColLeftAlign">{{rateCardDetail.gmPercentage}}</td>
															<td class="firstColLeftAlign">{{rateCardDetail.weightage}}</td>
														</tr>
 													</tbody>
												</table>
											</div>
										</div>
									</div> -->
								<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
										
										
										
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Onsite
											 %</label>
										<div class="col-sm-3">
											<input name="txtOnsitePer" type="text" class="form-control"  ng-value="calc_total_onsitePer"
												id="txtOnsitePer" ng-model="teamDeal.txtOnsiteModel" readonly>
										</div>
										
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Offshore
											 %</label>
										<div class="col-sm-3">
											<input name="txtOffshorePer" type="text" class="form-control"  ng-value="calc_total_offshore"
												id="txtOffshorePer" ng-model="teamDeal.txtOffshoreModel" readonly>
										</div>
										
										
										
										<!-- <div class="col-sm-3 text-center">
											<button type="button" class="btn btn-info  btnSpace"
												id="btnDealSummaryCalculate">Calculate</button>
										</div> -->
									</div>
									<div class="row marginBottom5px">
									
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Final
											PM %</label>
										<div class="col-sm-3">
										
										
											<!-- <input name="txtFinalGMPer" type="text" class="form-control"  ng-value="myVar"
												id="txtFinalGMPer" ng-model="teamDeal.txtFinalGMPerModel" readonly> -->
											<input name="txtFinalGMPer" type="text" class="form-control" ng-if="ratecardLenght >= 2" ng-value="calc_total_GM | number: 2"
											id="txtFinalGMPer" ng-model="teamDeal.txtFinalGMPerModel" readonly>
											
											<input name="txtFinalGMPer" type="text" class="form-control" ng-if="ratecardLenght==1" 
											id="txtFinalGMPer" ng-model="teamDeal.txtFinalGMPerModel" readonly>
										</div>
									
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Send
											to DeMS</label>
										<div class="col-sm-3">
											<input type="checkbox" name="cbxSendToDeMs"
												id="cbxSendToDeMs" class="margingRightChkBx">
										</div>
									</div>
								<div class="divEmptyThrice"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
				
<div class="divEmptyThrice" ></div>
 <div class="row" ng-show="showaprovercoment == true">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Approver Comments</label>
									</div>
                                </div>
                                <div class="panel-body" >
								 <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="teamDeal.txtApproverComment" id="teamDeal.txtApproverComment" class="form-control" rows="3"
	                                			 ng-model="teamDeal.approverCommentModel" required disabled></textarea>
	                             		</div>
                               		 </div>
                               	
                                </div>
                                 </div>
                    </div>
                    </div>
                </div>
              
                <div class="row" ng-show="checkApprover ==true">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Approver Action</label>
										
									</div>
                                </div>
                                <div class="panel-body" >
									<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Approver Action</label>
                                        <div class="col-sm-2">
                                          <select id="teamDeal.ddlApproverAction" class="form-control" ng-model="teamDeal.ddlApproverAction"  placeholder="Please select" name="teamDeal.ddlApproverAction" ng-change="getDealApprovalComments('<%=session.getAttribute("user")%>',teamDeal.ddlApproverAction)" required>
<%--                                           <select id="teamDeal.ddlApproverAction" class="form-control" ng-model="teamDeal.ddlApproverAction"  placeholder="Please select" name="teamDeal.ddlApproverAction" ng-change="getDealApprovalComments('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>',teamDeal.ddlApproverAction)" required> --%>
										<option value="" selected disabled>Please select</option>
										<option value="3">Approve</option>
										<option value="4">Reject</option>
									</select>
                                        </div>
                                     </div>	
                                     <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="teamDeal.txtApproverComment" id="teamDeal.txtApproverComment" class="form-control" rows="3"
	                                			 ng-model="teamDeal.approverCommentModel" required disabled></textarea>
	                             		</div>
                               		 </div>
                               		 <div class="row marginBottom5px" ng-show="commentbox == true">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Enter your Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" rows="3"
	                                			 ng-model="teamDeal.currentApproverCommentModel" ng-class="{true: 'ng-border'}[teamDeal.txtCurrentApproverComment.$invalid]" required></textarea>
	                             		</div>
	                             		<div class="error-messages" ng-messages="teamDeal.txtCurrentApproverComment.$error">
										<em class="error help-block has-error" ng-message="required" style="margin-left: 100px;"><Strong>Please enter comments</Strong></em>
										</div>
                               		 </div>
                               		 
                               		<!--  <input type="hidden"  name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" 
	                                			 ng-model="frmRateCardCreation.currentApproverCommentModel" disabled> -->
                               		 
                               		<%--  <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="saveApprovalData('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')">Save</button>						
											<button type="button" class="btn btn-danger btnSpace" id="btnAddCancel">Cancel</button>
											<!-- <button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnNext" ng-click="Next()">Next</button> -->
										</div>
                           	 		</div> --%>
                                </div>
                                
                        </div>
                    </div>
                    
				</div>
                </div>
				
				<%-- <div class="row" ng-hide="checkApprover">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info ">
								<div class="panel-heading panelHeadingStyle"
									ng-click="ShowHideApproverAction()">
									<div class="row ">
										<label class="control-label col-sm-10">Approver Action</label>
										<div class="col-sm-2 textAlignRight">
											<label class="DownArrowColor textAlignRight"> &#9660;</label>
										</div>
									</div>
								</div>
								<div class="panel-body" ng-hide="ApproverActionHidden">
									<div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Approver
											Action</label>
										<div class="col-sm-2">
											<select id="teamDeal.ddlApproverAction" class="form-control"
												placeholder="Please select" name="teamDeal.ddlApproverAction"
												ng-model="teamDeal.ddlApproverActionModel" ng-change="getDealApprovalComments('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')" required>
												<option value="" disabled="" selected="selected">Please
													select</option>
												<option value="3">Approve</option>
												<option value="1">Reject</option>
											</select>
										</div>
									</div>
									<div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
										<div class="col-sm-10">
											<textarea style="resize: none" name="teamDeal.txtApproverComment"
												id="teamDeal.txtApproverComment" class="form-control" rows="3"
												ng-model="teamDeal.approverCommentModel" required></textarea>
										</div>
									</div>
									
									 <input type="hidden"  name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" 
	                                			 ng-model="teamDeal.currentApproverCommentModel" disabled>
								</div>
							</div>
						</div>
					</div>
				</div>
 --%>				<div class="row text-center">
					<div class="col-sm-12">
						<button type="button" class="btn btn-primary btnSpace"
							id="btnDealSummarySave" ng-model= "teamDeal.btnDealSummarySave" ng-click = "saveApprovalData('<%=session.getAttribute("user")%>')" ng-disabled= "isSubmitDisable" >Save</button>
<%-- 							id="btnDealSummarySave" ng-model= "teamDeal.btnDealSummarySave" ng-click = "saveApprovalData('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')" ng-disabled= "isSubmitDisable" >Save</button> --%>
						<!-- <button type="button" class="btn btn-danger btnSpace"
							id="btnDealSummarySave">Cancel</button> -->
						<button type="button" class="btn btn-info btnSpace"
							id="btnClientPrev" ng-click="Prev()">Prev</button>
						<button type="button" class="btn btn-info" id="btnClientNext"
							ng-click="Next()">Next</button>
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
