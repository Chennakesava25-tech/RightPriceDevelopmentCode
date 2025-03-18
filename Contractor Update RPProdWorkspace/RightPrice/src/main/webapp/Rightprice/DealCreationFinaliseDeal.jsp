<div class="row">
   <div class="col-sm-12">
  		<div class="panel-group">
            <div class="panel panel-info ">
                         <div class="panel-heading panelHeadingStyle">
                             <div class="row ">
						<label class="control-label col-sm-10 ">Approval Matrix for Deal based on  PM%</label>
						<!-- <div class="col-sm-2 textAlignRight">
							<a href="#" class="DownArrowColor" ng-click="ShowHideFinalise()"> &#9660;</a>
						</div> -->
					</div>
                         </div>
                         <div class="panel-body">
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
											<!-- <td class="firstColLeftAlign">{{approver.level}} </td>
											<td class="firstColLeftAlign">{{approver.name}} </td>
											<td class="firstColLeftAlign">{{approver.status}} </td> -->
											<td class="firstColLeftAlign"><input name="txtCurrency" type="text" class="form-control"  ng-model="teamDeal.lvl" id="txtCurrency" ng-readonly="true">
											</td>
											<td><input name="txtCurrency" type="text" class="form-control"  ng-model="teamDeal.buh" id="txtCurrency" ng-readonly="true">
											</td>
											<td><input name="txtCurrency" type="text" class="form-control"  ng-model="teamDeal.status" id="txtCurrency" ng-readonly="true">
											</td>
											
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="divEmptyThrice" ></div>
 						<div class="row" >
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
	                                			 ng-model="teamDeal.approverCommentModel" ng-disable="isAppRej" ng-class="{true: 'ng-border'}[saved && teamDeal.txtComment.$invalid]" required ng-readonly="flag"></textarea>
	                             		</div>
	                             		<div class="error-messages" ng-if="saved" ng-messages="teamDeal.txtComment.$error">
										<em class="error help-block has-error" ng-message="required" style="margin-left: 100px;"><Strong>Please enter comments</Strong></em>
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
							<button type="button" class="btn btn-primary btnSpace" id="btnSendForApproval" ng-model = "btnSendForApproval" ng-click="updateDealValidation(teamDeal)" ng-disabled= "isSubmitDisable">Send For Approval</button>
							<button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
							<!-- <button type="button" class="btn btn-info btnSpace " id="btnRecycle" ng-click="recycleDealValidation(teamDeal)" ng-disabled= "isRecycleBtnDisable" >Recycle</button> -->
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
	
		
	</div>
</div>
								
                            	