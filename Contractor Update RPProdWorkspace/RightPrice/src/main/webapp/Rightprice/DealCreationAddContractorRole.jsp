<div class="row">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
			<ng-form name="dealCreationAddContractorRoleForm" id="dealCreationAddContractorRoleForm" >
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Add Contractor
							Role</label>
						<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideAddContractor()"> &#9660;</a>
										</div> -->
					</div>
				</div>
				<div class="panel-body">
					<div class="row marginBottom5px">
						<div class="col-sm-12">
							<div class="table-responsive  ">
								<table class="table clsTable table-striped table-bordered table-hover  " id="tblAddContractor">
									<thead>
										<tr>
											<th>Sr.No.</th>
											<th>Customer Role</th>
											<th>Onsite Cost/Hr</th>
											<th>Offshore Cost/Hr</th>
											<th>Onsite Rate</th>
											<th>Offshore Rate</th>
											<th>Comments</th>
											<th>Remove</th>
										</tr>
									</thead>
									<tbody id="tBodyAddContractor">
										<tr id="{{'traddContactorRole'+'_'+($index+1)}}" ng-repeat="row in addContactorRole">
											<!-- <td><input name="{{'txtSrNo'+'_'+($index+1)}}" ng-model="row.itemSrNo"  type="text" class="form-control" id="{{'txtSrNo'+'_'+($index+1)}}" value="{{$index+1}}" disabled></td> -->
											<td><input name="{{'txtcontactorRole'+'_'+($index+1)}}" type="text" class="form-control" value="{{$index+1}}" id="{{'txtSrNo'+'_'+($index+1)}}" disabled></td>
											<td><input name="{{'txtcontactorRole'+'_'+($index+1)}}" ng-model="row.contactorRole" type="text" class="form-control" id="txtcontactorRole"></td>
											<td><input name="{{'txtonsiteCost'+'_'+($index+1)}}" ng-model="row.onsiteCost" type="text" class="form-control" id="txtonsiteCost"></td>
											<td><input name="{{'txtoffshoreCost'+'_'+($index+1)}}" ng-model="row.offshoreCost" type="text" class="form-control" id="txtoffshoreCost"></td>
											<td><input name="{{'txtonsiteRate'+'_'+($index+1)}}" ng-model="row.onsiteRate" type="text" class="form-control" id="txtonsiteRate"></td>
											<td><input name="{{'txtoffshoreRate'+'_'+($index+1)}}" ng-model="row.offshoreRate" type="text" class="form-control" id="txtoffshoreRate"></td>
											<td><input name="{{'txtcomments'+'_'+($index+1)}}" ng-model="row.comments" type="text" class="form-control" id="txtcomments"></td>
											<td><button type="button" id="{{'btnAddContractorRemoveRow_'+'_'+($index+1)}}" ng-click="removeClick($index+1,row)">Remove</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="divEmptyThrice"></div>
					<div class="row text-center">
						<div class="col-sm-12">
							<button type="button" class="btn btn-info btnSpace" id="btnAddContractorAdd" ng-click="onAddClick()">Add</button>
							<button type="button" class="btn btn-primary btnSpace" id="btnAddContractorSave" ng-click="onSaveClick()">Save</button>
							<button type="button" class="btn btn-danger btnSpace" id="btnAddContractorCancel" ng-click="onCancelClick()">Cancel</button>
							<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
							<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
						</div>
					</div>

				</div>
				</ng-form>
			</div>
		</div>
	</div>
</div>
