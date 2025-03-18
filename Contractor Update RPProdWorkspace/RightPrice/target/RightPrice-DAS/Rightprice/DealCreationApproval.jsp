<div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Sent for Approval</label>
										
									</div>
                                </div>
                                <div class="panel-body">	
									<div class="divEmpty"></div>
									<div class="divEmptyThrice"></div>
									<div class="row">
	                                    <div class="col-sm-12">
											<div class="table-responsive">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection1">
													<thead>
														<tr>
															<!-- <th>Source</th> -->
															<th>Select</th> 
															<th class="firstColLeftAlign">Deal Versions</th>
															<th>Revenue</th>
															<th>Margin %</th>
															
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection1" >
													 <tr ng-repeat="dealver in dealverResult">
													     <td><input type="checkbox" name="{{'cbxItem'+'_'+($index+1)}}" id="{{'cbxItem'+'_'+($index+1)}}" class="margingRightChkBx"
													      ng-model="dealver.chkBoxStatus"></td>
														 <td class="firstColLeftAlign tooltip1">{{dealver.dealVersion}}
														  <td>{{dealver.estimatedRevenue}} </td>
														  <td>{{dealver.gmPercentage}} </td>
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
  
<div class="divEmptyThrice"></div>
                            		<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-click="saveOrUpdateFpDealRole();">Submit for Approval</button>
											<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
										</div>
									</div>