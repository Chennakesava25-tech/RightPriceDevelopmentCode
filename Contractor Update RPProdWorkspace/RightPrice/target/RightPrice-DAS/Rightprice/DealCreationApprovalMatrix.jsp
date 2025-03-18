
<div>
            <form class="form-inline" role="form" name="Master" id="Master">
				<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Approval Matrix for  FP Deal based on  PM%</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideFinalise()"> &#9660;</a>
										</div> -->
									</div>
	                            </div>
	                            <div class="panel-body">
									<div class="divEmptyThrice"></div>
									<div class="row">
									 <div class="col-sm-2"></div>
	                                    <div class="col-sm-8">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblApprovalMatrix">
													<thead>
														<tr>
															<th class="firstColLeftAlign">Approver Designation</th>
															<th>Name</th>
															<th>Status</th>
														</tr>
														</thead>
													<tbody id="tBodyApprovalMatrix" >
													<tr ng-repeat ="approver in approverLevels"  ng-switch on="$index">
														<td class="firstColLeftAlign">{{approver.level}} </td>
														<td class="firstColLeftAlign">{{approver.name}} </td>
														<td class="firstColLeftAlign">{{approver.status}} </td>
													</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									 <div class="panel-body">	
									<div class="divEmpty"></div>
									<div class="divEmptyThrice"></div>
									<div class="row">
	                                    <div class="col-sm-12">
	                                    <div>
											<label><i><font color="red">Note: All rates are in {{currency}}</font></i></label>
			  							</div>
											<div class="table-responsive" ng-show="isReadyToSubmitData">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection1">
													<thead>
														<tr>
															<!-- <th>Source</th> -->
															<th>Select</th> 
															<th class="firstColLeftAlign">Deal Versions</th>
															<th>Onsite %</th>
															<th class="firstColLeftAlign">Offshore %</th>
															<th>No of Towers</th>
															<th>Revenue</th>
															<th>Direct Cost</th>
															<th>Project Margin%</th>
															<th>Project specific Cost</th>
															<th>Project Margin</th>
															<th>PM% before discount</th>
															<th>PM% after discount</th>
															<th>PM% after discount and incl. Risk</th>
															<th>Final Approval</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection1" ng-if="dealRF==true">
													 <tr ng-repeat="dealver in dealverResult" ng-if="dealver.isNewDeal!=0 && dealver.isNewDeal!=1">
													     <td>
													     <input type="checkbox" ng-model="$parent.selectedObj" ng-value="dealver" ng-change="addVersionData(dealver)">
													       
													      </td>
													      <td class="firstColLeftAlign tooltip1" ng-model="selectedObj.rpDealVersionId" ng-show="rpdv">{{dealver.rpDealVersionId}}</td>
														  <td class="firstColLeftAlign tooltip1">{{dealver.dealVersion}}</td>
														  <td>{{dealver.expectedOnsitePercentage}}% </td>
														  <td>{{dealver.expectedOffshorePercentage}}%</td>
														  <td>{{dealver.noOfTowers}}</td>
														  <td>{{dealver.estimatedRevenue | number:0}}</td>
														  <td>{{dealver.directCost | number:0 }}</td>
														  <td>{{dealver.projectMarginPercentage*100| number:2 }}%</td>
														  <td>{{dealver.projectSpecificCost | number:0 }}</td>
														  <td>{{dealver.projectMargin | number:0}}</td>
														  <td>{{dealver.gmAfterProjectSpecificCost*100 | number:2}}%</td>
														  <td>{{dealver.gmAfterVolumeDiscount*100 | number:2 }}%</td>
														  <td>{{dealver.gmPercentage*100| number:2 }}%</td>
														  <td>{{dealver.finalApproval}}</td>
														 </tr>
													</tbody>
													<tbody id="tBodyRoleSelection1" ng-show="dealnotRF">
													 <tr ng-repeat="dealver in dealverResult" ng-show="dealver.isNewDeal!=2">
													     <td>
													      <input type="radio" ng-model="$parent.selectedObj" ng-value="dealver" ng-change="addVersionData(dealver)">
													      </td>
													      <td class="firstColLeftAlign tooltip1" ng-model="selectedObj.rpDealVersionId" ng-show="rpdv">{{dealver.rpDealVersionId}}</td>
														  <td class="firstColLeftAlign tooltip1">{{dealver.dealVersion}}</td>
														  <td>{{dealver.expectedOnsitePercentage}}% </td>
														  <td>{{dealver.expectedOffshorePercentage}}%</td>
														  <td>{{dealver.noOfTowers}}</td>
														  <td>{{dealver.estimatedRevenue | number:0}}</td>
														  <td>{{dealver.directCost | number:0 }}</td>
														  <td>{{dealver.projectMarginPercentage*100 | number:2 }}%</td>
														  <td>{{dealver.projectSpecificCost | number:0 }}</td>
														  <td>{{dealver.projectMargin | number:0}}</td>
														  <td>{{dealver.gmAfterProjectSpecificCost*100 | number:2}} %</td>
														  <td>{{dealver.gmAfterVolumeDiscount*100 | number:2 }} %</td>
														  <td>{{dealver.gmPercentage*100 | number:2 }}%</td>
														  <td>{{dealver.finalApproval}}</td>
														 </tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice"></div>
                            		<div class="row" ng-show="isFinalListDisabled">
	                                    <div class="col-sm-12">
	                                    <div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection1">
													<thead>
														<tr>
															<!-- <th>Source</th> -->
															<th>Select</th> 
															<th class="firstColLeftAlign">Deal Versions</th>
															<th>Onsite %</th>
															<th class="firstColLeftAlign">Offshore %</th>
															<th>No of Towers</th>
															<th>Revenue</th>
															<th>Direct Cost</th>
															<th>Project Margin%</th>
															<th>Project specific Cost</th>
															<th>Project Margin</th>
															<th>PM% before discount</th>
															<th>PM% after discount</th>
															<th>PM% after discount and incl. Risk</th>
															<th>Final Approval</th>
														</tr>
													</thead>
													
													<tbody id="tBodyRoleSelection1">
													 <tr ng-repeat="dealverfinal in dealverFinalResult">
													     <td>
													      <input type="radio" ng-model="$parent.selectedRFPObj" ng-value="dealverfinal" ng-change="addVersionData(dealver)">
													      </td>
													      <td class="firstColLeftAlign tooltip1" ng-model="selectedRFPObj.rpDealVersionId" ng-show="rpdv">{{dealverfinal.rpDealVersionId}}</td>
														  <td class="firstColLeftAlign tooltip1">{{dealverfinal.dealVersion}}</td>
														  <td>{{dealverfinal.expectedOnsitePercentage}}% </td>
														  <td>{{dealverfinal.expectedOffshorePercentage}}%</td>
														  <td>{{dealverfinal.noOfTowers}}</td>
														  <td>{{dealverfinal.estimatedRevenue | number:0}}</td>
														  <td>{{dealverfinal.directCost | number:0 }}</td>
														  <td>{{dealverfinal.projectMarginPercentage*100 | number:2 }}%</td>
														  <td>{{dealverfinal.projectSpecificCost | number:0 }}</td>
														  <td>{{dealverfinal.projectMargin | number:0}}</td>
														  <td>{{dealverfinal.gmAfterProjectSpecificCost*100 | number:2}} %</td>
														  <td>{{dealverfinal.gmAfterVolumeDiscount*100 | number:2 }} %</td>
														  <td>{{dealverfinal.gmPercentage*100 | number:2 }}%</td>
														  <td>{{dealverfinal.finalApproval}}</td>
														  </tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                                </div>
					        		<div class="divEmptyThrice"></div>
					        		
					        		<div class="divEmptyThrice" ></div>
 						<div class="row" ng-show="isReadyToSubmitData">
                    		<div class="col-sm-12">
                        		<div class="panel-group">
                            		<div class="panel panel-info ">
                               		 <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Comments</label>
									</div>
                                </div>
                                <div class="panel-body" >
								 <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtComment" id="txtComment" class="form-control" rows="3"
	                                			 ng-model="fpDealFinalize.approverCommentModel" required ></textarea>
	                             		</div>
                 					</div>
                               	
                                </div>
                                 </div>
                    </div>
                    </div>
                </div>
                <div class="row" ng-show="isFinalDisabled">
                    		<div class="col-sm-12">
                        		<div class="panel-group">
                            		<div class="panel panel-info ">
                               		 <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Comments</label>
									</div>
                                </div>
                                <div class="panel-body" >
								 <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtComment" id="txtComment" class="form-control" rows="3"
	                                			 ng-model="fpDealFinalize.approverCommentModel" required ></textarea>
	                             		</div>
                 					</div>
                               	
                                </div>
                                 </div>
                    </div>
                    </div>
                </div>
                            		<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSendForApproval" ng-click="sendForApproval(selectedObj.rpDealVersionId)" ng-disabled="isDisabled">Send For Approval</button>	
											<button type="button" class="btn btn-primary btnSpace" id="btnSendForApproval" ng-click="updateFpFinalApprovedStatus(selectedRFPObj.rpDealVersionId)" ng-show="isFinalDisabled">Final Version to be Approved</button>						
											<button type="button" class="btn btn-info btnSpace " id="btnPrev" ng-click="Prev()">Prev</button>
										</div>
									</div>
									
									
                            	</div>
                        	</div>  
						</div>
			       	</div>
				</div>
	          </form>
        </div>