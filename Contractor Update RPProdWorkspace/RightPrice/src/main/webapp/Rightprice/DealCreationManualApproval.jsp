
<div>
            <form class="form-inline" role="form" name="Master" id="Master">
				<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Approval Matrix for  Manual Deal based on  PM%</label>
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
									
						<div class="divEmptyThrice" ></div>
 						<!-- <div class="row" ng-show="commentBox == true">
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
	                                			 ng-model="frmRateCardCreation.approverCommentModel" required ></textarea>
	                             		</div>
                 					</div>
                               	
                                </div>
                                 </div>
                    </div>
                    </div>
                </div> -->
									
									
                            		<div class="divEmptyThrice"></div>
                            		<div class="row text-center">
										<div class="col-sm-12">
											
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