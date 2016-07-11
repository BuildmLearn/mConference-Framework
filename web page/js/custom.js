$('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
      });

$(document).ready(function() {
    $('select').material_select();
    $('#sponsors-web').hide();

   	$('#splash-bg').change(function(){
   		if($('#splash-bg').val() == 'image') {
   			$('#splash-bg-value').attr('type', 'url');
   			$('#splash-bg-value').prop('disabled', false);
   			$('#splash-bg-label').attr('data-error', 'URL Invalid');
   			$('#splash-bg-label').text('Image Link');
   		}
   		else if ($('#splash-bg').val() == 'colour') {
   			$('#splash-bg-value').attr('type', 'text');
   			$('#splash-bg-value').prop('disabled', false);
   			$('#splash-bg-label').removeAttr('data-error');
   			$('#splash-bg-label').text('Hex Code');
   		}
   		else {
   			$('#splash-bg-value').prop('disabled', true);
   			$('#splash-bg-value').val('');
   			$('#splash-bg-label').text('None');
   		}
   	});

   	$('#sponsors').change(function(){
   		if($('#sponsors').val() == 'individual') {
   			$('#sponsors-web').hide();
   			$('#sponsors-list').show();
   			$('#add-sponsors').show();
   		}
   		else {
   			$('#sponsors-web').show();
   			$('#sponsors-list').hide();
   			$('#add-sponsors').hide();
   		}
   	});

   	 $("#add-sponsors-btn").click(function() {
   	 	var intId = $("#sponsors-list>div").length + 1;
   	 	var newSponsor = $("<div id=\"sponsors-"+ intId +"\" class=\"row\"><div class=\"input-field col s5 offset-s2\"><input id=\"sponsor-"+ intId +"-name\" name=\"sponsor-"+ intId +"-name\" type=\"text\" class=\"validate\"><label for=\"sponsor-"+ intId +"-name\">Sponsor#"+ intId +" Name</label></div><div class=\"input-field col s5\"><input id=\"sponsor-"+ intId +"-link\" name=\"sponsor-"+ intId +"-link\" type=\"url\" class=\"validate\"><label data-error=\"URL Invalid\" for=\"sponsor-"+ intId +"-link\">Image Link</label></div></div>");

   	 	$("#sponsors-list").append(newSponsor);

   	 });
});