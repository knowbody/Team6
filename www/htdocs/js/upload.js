$(document).on('change', '.btn-file :file', function() {
	var input = $(this),
	numFiles = input.get(0).files ? input.get(0).files.length : 1,
	label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	input.trigger('fileselect', [numFiles, label]);
});
$(document).ready( function() {
	$('.btn-file :file').on('fileselect', function(event, numFiles, label) {
		var input = $(this).parents('.input-group').find(':text'),
		log = numFiles > 1 ? numFiles + ' files selected' : label;
		if( input.length ) {
		input.val(log);
		} else {
		if( log ) alert(log);
		}
	});
});		
$('#buttonUpload').click(function(){
	$("#uploadFormContent").css('opacity','0.5');
	$("#uploadFormLoading").show();
	var formData = new FormData();
	formData.append("volunteer", document.getElementById('fileInputVolunteer').files[0]);
	formData.append("serviceUser", document.getElementById('fileInputServiceUser').files[0]);
	formData.append("kitchen", document.getElementById('fileInputKitchen').files[0]);
	$.ajax({
		type: 'post',
		url: 'ajax/importData.php',
		data: formData,
		dataType: 'json',
		success: function(data){
			if(data.status == 0){
				$("#uploadFormLoading .status").hide();
				$("#uploadFormStatusOK").show();
			}else{
				$("#uploadFormLoading .status").hide();
				$("#uploadFormStatusError").show();
			}
		},
		processData: false,
		contentType: false
	});
});
