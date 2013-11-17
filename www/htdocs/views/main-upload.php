<content>
	<container>
		<div class="inner">
			<h1>Database upload</h1>
			<p>Use this form to upload today's version of the database. Three files are required:</p>
			<form onsubmit="return false;" id="uploadForm">
				<div id="uploadFormLoading">
					<div class="inner">
						<img src="<?php echo url('img/loader.gif'); ?>" class="status" id="uploadFormStatusLoading">
						<img src="<?php echo url('img/check_alt_32x32.png'); ?>" class="status" id="uploadFormStatusOK">
						<img src="<?php echo url('img/x_alt_32x32.png'); ?>" class="status" id="uploadFormStatusError">
						<p>Updating database</p>
					</div>
				</div>
				<div id="uploadFormContent">
					<div class="form-group">
						<label for="serviceUser">serviceUser.csv</label>
						<div class="input-group input-group-upload">
							<span class="input-group-btn">
								<span class="btn btn-primary btn-file">
									Browse… <input type="file" name="serviceUser" id="fileInputServiceUser">
								</span>
							</span>
							<input type="text" class="form-control" readonly="">
						</div>
					</div>

					<div class="form-group">
						<label for="volunteer">volunteer.csv</label>
						<div class="input-group input-group-upload">
							<span class="input-group-btn">
								<span class="btn btn-primary btn-file">
									Browse… <input type="file" name="volunteer" id="fileInputVolunteer">
								</span>
							</span>
							<input type="text" class="form-control" readonly="">
						</div>
					</div>

					<div class="form-group">
						<label for="kitchen">kitchen.csv</label>
						<div class="input-group input-group-upload">
							<span class="input-group-btn">
								<span class="btn btn-primary btn-file">
									Browse… <input type="file" name="kitchen" id="fileInputKitchen">
								</span>
							</span>
							<input type="text" class="form-control" readonly="">
						</div>
					</div>

					<div class="form-group" style="text-align:right;">
						<button class="btn btn-primary" id="buttonUpload">Upload</button>
					</div>
				</div>
			</form>
		</div>
	</container>
</content>
