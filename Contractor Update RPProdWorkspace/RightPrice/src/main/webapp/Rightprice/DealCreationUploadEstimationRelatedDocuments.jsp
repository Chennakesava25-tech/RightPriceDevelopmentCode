<div class="row">
	<ng-form name="attachmentForm" id="attachmentForm">
	<div class="col-sm-12">
		<div class="panel-group">
			<div class="panel panel-info ">
				<div class="panel-heading panelHeadingStyle">
					<div class="row ">
						<label class="control-label col-sm-10 ">Upload Estimation Related Documents</label>
					</div>
				</div>
				<div class="panel-body">
					<div class="row marginBottom5px">
						<label
							class="control-label col-sm-2 textAlignRight required-Field">Document Type</label>
						<div class="col-sm-3">
							<select id="ddlDocumentType" name="ddlDocumentType"
								ng-model="attachmentForm.selectedId" class="form-control"
								ng-options="dt.id as dt.name for dt in docTypes"
								ng-class="{true: 'ng-border'} [(onUpload && attachmentForm.ddlDocumentType.$invalid)]"
								required>
								<option value="" selected disabled>Please select</option>
							</select>
							<div class="error-messages" ng-if= "onUpload" ng-messages="attachmentForm.ddlDocumentType.$error">
								<em class="error help-block has-error" ng-message="required">Please select Document Type.</em>
							</div>
						</div>
						<!-- <ng-form name="thatForm"> -->
						<label class="control-label col-sm-3 textAlignRight required-Field">Upload File</label>
						<div class="col-sm-3 ">
							<input type="file" class="form-control" name="fuUploadFilename"
								id="fuUploadFilename" ng-model="fuUploadFilename" 
								ng-class="{true: 'ng-border'} [(onUpload && attachmentForm.fuUploadFilename.$invalid)]" 
								valid-file 
								ng-required="true"
								check-file-size="fuUploadFilename">
							<div class="error-messages" ng-if= "onUpload" ng-messages="attachmentForm.fuUploadFilename.$error">
								<em class="error help-block has-error" ng-message="required">Please upload file.</em>
								<em class="error help-block has-error" ng-message="checkfilesize">Please upload file size up to 3MB.</em>
							</div>
							<!-- <pre> {{attachmentForm.fuUploadFilename.$error}}</pre> -->
						</div>
						<!-- </ng-form> -->
					</div>
					<div class="row marginBottom5px">
						<label class="control-label col-sm-2 textAlignRight">Comments</label>
						<div class="col-sm-9 customError">
							<textarea name="txtComents" style="resize: none" rows="3"
								class="form-control" id="txtComents"
								ng-model="attachmentForm.comments" ng-disabled="IsDisabled"
								ng-maxlength="500"
								></textarea>
							<div class="error-messages" ng-if= "onUpload" ng-messages="attachmentForm.txtComents.$error">
								<!-- <em class="error help-block has-error" ng-message="required">Please select Comment.</em> -->
								<em class="error help-block" ng-message="maxlength">Comment cannot exceed 500 characters</em>
							</div>
						</div>
					</div>
					<div class="divEmptyThrice"></div>
					<div class="row text-center">
						<div class="col-sm-12">
							<!-- 	ng-disabled="isUploadDisabled" -->
							<button type="button" class="btn btn-primary btnSpace"
								id="btnUploadDocumentsUpload"
								ng-click="uploadClick(attachmentForm)"
								ng-disabled="isUploadDisabled"
								required>Upload</button>
						</div>
						<!-- ng-click="onUploadClick(attachmentForm.$valid,attachmentForm.selectedId,attachmentForm.fuUploadFilenameModel,attachmentForm.comments,attachmentForm)" -->
						
					</div>
					<div class="divEmptyThrice"></div>
					<div class="row marginBottom5px">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<div class="table-responsive">
								<table
									class="table clsTable table-striped table-bordered table-hover table-condensed "
									id="tblUploadDocuments">
									<thead>
										<tr>
											<th>Document Type</th>
											<th >Comments</th>
											<th >Date Of Upload [dd/mm/yyyy]</th>
											<th colspan="2">Action</th>
											
										</tr>
									</thead>
									<tbody id="tBodyUploadDocuments">
										<tr id="{{'trsubpracView'+'_'+($index+1)}}" ng-repeat="doc in documents">
												<td>{{doc.doctype}}</td>
												<td>{{doc.comments}}</td>
												<td width="25%"><label>{{doc.createdOn}}</label></td>
												<td><a href="#"
													   id="{{'trAtta'+'_'+($index+1)}}"
												       ng-click="downloadFile(doc.attachmentId);">Download</a></td>
												<td><button type="button"
													id="btnUploadDocumentsRemoveRow1"
													ng-click="onDeleteClick(doc.attachmentId)">Delete</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="divEmptyThrice"></div>
					<div class="row text-center">
						<div class="col-sm-12">
							<button type="button" class="btn btn-info btnSpace"
								id="btnClientPrev" ng-click="Prev()">Prev</button>
							<button type="button" class="btn btn-info" id="btnClientNext"
								 ng-click="Next()">Next</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</ng-form>
</div>
