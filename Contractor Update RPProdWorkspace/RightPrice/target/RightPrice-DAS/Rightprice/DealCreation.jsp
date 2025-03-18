<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Eviden RightPrice Portal</title>
    <meta charset="utf-8">
    <meta name="csrf-token" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Support/CSS/SAPStyleSheet.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Support/CSS/loader.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Support/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery.serializeJSON.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery-ui.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/jquery-ui.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap-dialog.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap-dialog.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/Support/CSS/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/Support/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${pageContext.request.contextPath}/Support/js/angular.js"></script>	
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/Support/js/jquery.freezeheader.js"></script> --%>
    <script type="text/javascript">
    	
		var app = angular.module('DealCreationApp', []);
		app.controller("DealCreationController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
			
		      
		}]); 
		/* $(document).ready(function () {
             //$("#table1").freezeHeader({ 'height': '300px' }); 
	    	//$("#tblDealCreation").freezeHeader(); 
	    	 	    	    
        }) */
		    
    </script>
</head>
<body  ng-app="DealCreationApp"  ng-controller="DealCreationController" onload="document.getElementById('anc2017').focus()">
    <div id="includedContent" ng-include="'/RightPricePortal/Portal/Header.jsp'"></div>
    <div class="container-fluid">
        <div class="divEmptyThrice"></div>
        <div class="row" >
            <div class="col-sm-2 col-sm-offset-10">
                <!-- <a href="../Support/Documents/User_Manual_Client_Visit_Approval.pdf" target="_blank">Help</a> -->
            </div>
        </div>
        
        
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">Deal Creation - <span class="text-danger"><strong>Staffing</strong></span></h3>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="panel-group">
					<div class="panel panel-info">
						<div class="panel-heading panelHeadingStyle ">
							<div class="row ">
								<label class="control-label col-sm-10">Staffing Details</label>								
							</div>
						</div>
						<div class="panel-body">
							<div class="row marginBottom5px">
								<div class="col-sm-11">
									<label class="control-label col-sm-1 textAlignRight">Version</label>
									<div class="col-sm-2">
		                            	<select id="ddlVersion" class="form-control" placeholder="Please select" name="ddlVersion">
											<option value="" selected disabled>Please select</option>
											<option value="1">1.0</option>
											<option value="2">2.0</option>
										</select>
	                            	</div>
	                            	<label class="control-label col-sm-1 textAlignRight">Country</label>
									<div class="col-sm-2">
		                            	<select id="ddlCountry" class="form-control" placeholder="Please select" name="ddlCountry">
											<option value="" selected disabled>Please select</option>
											<option value="1">India</option>
											<option value="2">US</option>
										</select>
	                            	</div>
	                            	<label class="control-label col-sm-1 textAlignRight">City</label>
									<div class="col-sm-2">
		                            	<select id="ddlCity" class="form-control" placeholder="Please select" name="ddlCity">
											<option value="" selected disabled>Please select</option>
											<option value="1">Pune</option>
											<option value="2">Mumbai</option>
										</select>
	                            	</div>
	                            	<label class="control-label col-sm-1 textAlignRight">Application</label>
									<div class="col-sm-2">
		                            	<select id="ddlAppln" class="form-control" placeholder="Please select" name="ddlAppln">
											<option value="" selected disabled>Please select</option>
											<option value="1">Client Visit</option>
											<option value="2">One Day Pass</option>
										</select>
	                            	</div>
                            	</div>
                            	<div class="col-sm-1">
                            		<button type="button" class="btn btn-primary" id="btnSubmitl">Submit</button>	
								</div>
							</div>
							
						</div>
					</div>
				</div>
            </div>
        </div>
        	
        <div>
            <form class="form-inline" role="form" name="DealCreation" id="DealCreation">
	            <div class="row">
					<div class="col-sm-12">
						<div class="table-responsive">
	                        <table id="" class="table tblDealCre table-borderless table-condensed">
	                             <thead>
	                              	<tr class="">
	                          			<th colspan="2" class="thWidth22Per thBorder">Role Description</th>
	                                    <th class="thWidth8Per thBorder">Band Grade</th>
	                                    <th class="thWidth15Per thBorder">Customer Role</th>
	                                    <th class="padLeft colRightSection" colspan="3"><a class="btn btn-default ancBtnWidthSum" href="#" tabindex="1">Summary</a></th>
	                                    <th class="colRightSection" colspan="2"><a id="anc2017" class="btn btn-default" href="#" tabindex="2" >2017</a></th>
	                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="3">2018</a></th>
	                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="4">2019</a></th>
	                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="5">2020</a></th>
	                                    <th class="colRightSection" colspan="2"><a class="btn btn-default" href="#" tabindex="6">2021</a></th>	                                   
	                              	</tr>	                              
	                              	 <tr>
	                          			<td colspan="4"></td>	                                    
	                                    <td class="thWidth4Per">Jan</td>
	                                    <td class="thWidth4Per">Feb</td>
	                                    <td class="thWidth4Per">Mar</td>
	                                    <td class="thWidth4Per">Apr</td>
	                                    <td class="thWidth4Per">May</td>
	                                    <td class="thWidth4Per">Jun</td>
	                                    <td class="thWidth4Per">Jul</td>
	                                    <td class="thWidth4Per">Aug</td>
	                                    <td class="thWidth4Per">Sep</td>
	                                    <td class="thWidth4Per">Oct</td>
	                                    <td class="thWidth4Per">Nov</td>
	                                    <td class="thWidth4Per">Dec</td>
	                                    <td class="thWidth4Per">Total</td>
                                    </tr>
                       			</thead>
	                              <tbody> 
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><strong>Onsite - Local(Contractor)</strong></td>	                                    
	                              	</tr>
	                              </tbody>
	                              <tbody>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"value="24.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="24.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><strong>Onsite - Local (Syntel, Inc)</strong></td>	                                    
	                              	</tr>
	                          	</tbody>
                              	<tbody> 		                              	
	                              	<tr>
	                          			<td  class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="12.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                           		</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="36.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><strong>Onsite - H1 / Extended L1</strong></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="8.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                           		</tbody>
                              	<tbody>	                             
                              		<tr class="rowSubTotal">	                          			
	                                    <td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="8.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><span class="text-white"><strong>Onsite - Short Term</strong></span></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">	                          			
	                                    <td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
								</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                              		<td colspan="17"><span class="text-white"><strong>Total Onsite</strong></span></td>
                              		</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                              		<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="24.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="8.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="12.00"></td>
	                              	</tr>
	                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
	                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="5.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="5.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="5.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="5.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="44.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                              		<td colspan="17"><span class="text-white"><strong>Total Offshore</strong></span></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                              		<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="2.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="24.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="12.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="36.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                              		<td colspan="17"><span class="text-white"><strong>Total Staffing</strong></span></td>
                              		</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                              		<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="4.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="48.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="3.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="20.00"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="1.00"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" value="0.00"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
								    <tr class="rowGrandTotal">
	                          			<td colspan="4"><strong>Grand TOTAL</strong></td>	                                    
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="8.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="8.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="8.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="8.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="6.00"></td>
	                                    <td class="thWidth4Per"><input type="text" class="form-control" value="80.00"></td>
	                              	</tr>
                              	</tbody>                              	
	                     	</table>
						</div>
					</div>
				</div>
				<div class="divEmptyThrice"></div>
				<div class="divEmptyThrice"></div>
				<div class="divEmptyThrice"></div>
				<!-- Summary -->
				<div class="row">
					<div class="col-sm-12">
						<div class="table-responsive">
	                        <table id="" class="table tblDealCre table-borderless table-condensed">
	                             <thead>
	                              	<tr class="">
	                          			<th colspan="2" class="thWidth22Per thBorder">Role Description</th>
	                                    <th class="thWidth8Per thBorder">Band Grade</th>
	                                    <th class="thWidth15Per thBorder">Customer Role</th>	                                    
	                                    <th class="padLeft text-center" colspan="2"><a class="btn btn-default" href="#" tabindex="1" >2017</a></th>
	                                    <th class="text-center" colspan="2"><a class="btn btn-default" href="#" tabindex="2">2018</a></th>
	                                    <th class="text-center" colspan="2"><a class="btn btn-default" href="#" tabindex="3">2019</a></th>
	                                    <th class="text-center" colspan="2"><a class="btn btn-default" href="#" tabindex="4">2020</a></th>
	                                    <th class="text-center" colspan="2"><a class="btn btn-default" href="#" tabindex="5">2021</a></th>
										<th class="text-center" colspan="3"><a class="btn btn-default btnTotalWidth" href="#" tabindex="6">Total</a></th>	                                    	                                   
	                              	</tr>
                       			</thead>
	                              <tbody> 
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><strong>Onsite - Local(Contractor)</strong></td>	                                    
	                              	</tr>
	                              </tbody>
	                              <tbody>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth9Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth11Per" colspan="2"><input type="text" class="form-control" value="12345"></td>	                                    
	                              	</tr>
                              	</tbody>
                              	 <tbody>
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><strong>Onsite - Local (Syntel, Inc)</strong></td>	                                    
	                              	</tr>
	                          	</tbody>
                              	<tbody> 		                              	
	                              	<tr>
	                          			<td  class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                                    
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                   <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                           		</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth9Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth11Per" colspan="2"><input type="text" class="form-control" value="12345"></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><strong>Onsite - H1 / Extended L1</strong></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                           		</tbody>
                              	<tbody>	                             
                              		<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth9Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth11Per" colspan="2"><input type="text" class="form-control" value="12345"></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                          			<td colspan="17"><span class="text-white"><strong>Onsite - Short Term</strong></span></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth9Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth11Per" colspan="2"><input type="text" class="form-control" value="12345"></td>	                                    
	                              	</tr>
								</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                              		<td colspan="17"><span class="text-white"><strong>Total Onsite</strong></span></td>
                              		</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                              		<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth9Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth11Per" colspan="2"><input type="text" class="form-control" value="12345"></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                              		<td colspan="17"><span class="text-white"><strong>Total Offshore</strong></span></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                              		<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="rowSubTotal">
	                          			<td colspan="4"><strong>SUB TOTAL</strong></td>	                                    
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth9Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth11Per" colspan="2"><input type="text" class="form-control" value="12345"></td>	                                    
	                              	</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr class="sectionHeading">
	                              		<td colspan="17"><span class="text-white"><strong>Total Staffing</strong></span></td>
                              		</tr>
                              	</tbody>
                              	<tbody>
	                              	<tr>
	                              		<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="Skillbay/Contract Hires-Onsite"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="IT/Domain/ConsultantDomain/Ju..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM1 (9 to 12 yrs) / None"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
	                              	<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="ECM/SharePoint/ArchitectShare..."></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value="B4 PM2 (10 to 14 yrs) / B4 PM3 (12 to 15 yrs)"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              		<tr>
	                          			<td class="thWidth22Per colSection" colspan="2"><input type="text" class="form-control" value="///"></td>
	                                    <td class="thWidth8Per colSection"><input type="text" class="form-control"></td>
	                                    <td class="thWidth15Per colSection"><input type="text" class="form-control" value=""></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth8Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth9Per" colspan="2"><input type="text" class="form-control"value="12345"></td>
	                                    <td class="colRightSectionCenter tdWidth11Per" colspan="3"><input type="text" class="form-control"value="12345"></td>
	                              	</tr>
                              	</tbody>
                              	<tbody>
								    <tr class="rowGrandTotal">
	                          			<td colspan="4"><strong>Grand TOTAL</strong></td>	                                    
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth8Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth9Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                                    <td class="tdWidth11Per" colspan="2"><input type="text" class="form-control" value="12345"></td>
	                              	</tr>
                              	</tbody>                             	
	                     	</table>
						</div>
					</div>
				</div>
				
				<!-- Summary -->
				<div class="divEmptyThrice"></div>
				<div class="divEmptyThrice"></div>
				<div class="divEmptyThrice"></div>
				<div class="row text-center">
					<div class="col-sm-12">
						<button type="button" class="btn btn-primary btnSpace" id="btnSave">Save</button>
						<button type="button" class="btn btn-danger" id="btnCancel">Cancel</button>	
					</div>
				</div>
          	</form>
        </div>
    </div>
	<div id="footer" ng-include="'/RightPricePortal/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'/RightPricePortal/Portal/TopBottomNavigation.jsp'"></div>
</body>
</html>
