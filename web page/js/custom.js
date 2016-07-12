$('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 15
      });

$('#sponsors-web').hide();
$('select').material_select();
$('.timepicker').timepicker({ 'timeFormat': 'h:i A' });

$(document).ready(function() {
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

      $("#add-talks-btn").click(function() {
         var intId = $("#talks-list>div").length + 1;
         var newTalk = $("<div id=\"talk-"+ intId +"\"><div class=\"row  valign-wrapper\"><div class=\"col s2\"><label class=\"valign center-align\" for=\"talk-"+ intId +"\">Talk #1</label></div><div class=\"input-field col s5\"><input id=\"talk-"+ intId +"-name\" name=\"talk-"+ intId +"-name\" type=\"text\" class=\"validate\"><label for=\"talk-"+ intId +"-name\">Name</label></div><div class=\"input-field col s5\"><input id=\"venue-"+ intId +"\" name=\"venue-"+ intId +"\" type=\"text\" class=\"validate\"><label for=\"venue-"+ intId +"\">Venue</label></div></div><div class=\"row\" style=\"margin-bottom:45px;\"><div class=\"input-field col s10 offset-s2\"><textarea id=\"description-"+ intId +"\" name=\"description-"+ intId +"\" class=\"materialize-textarea\"></textarea><label for=\"description-"+ intId +"\">Description</label></div></div><div class=\"row\"><div class=\"col s4 offset-s2\"><label for=\"date-"+ intId +"\">Date</label><input id=\"date-"+ intId +"\" name=\"date-"+ intId +"\" type=\"date\" class=\"datepicker\"></div><div class=\"col s3\"><label for=\"start-time-"+ intId +"\">Start Time</label><input id=\"start-time-"+ intId +"\" name=\"start-time-"+ intId +"\" type=\"text\" autocomplete=\"on\" class=\"timepicker\"></div><div class=\"col s3\"><label for=\"end-time-"+ intId +"\">End Time</label><input id=\"end-time-"+ intId +"\" name=\"end-time-"+ intId +"\" type=\"text\" autocomplete=\"on\" class=\"timepicker\"></div></div><div class=\"row\"><div class=\"input-field col s10 offset-s2\"><input id=\"talk-"+ intId +"-img\" name=\"talk-"+ intId +"-img\" type=\"url\" class=\"validate\"><label data-error=\"URL Invalid\" for=\"talk-"+ intId +"-img\">Image Link</label></div></div></div><br><br>");
         
         $("#talks-list").append(newTalk);

         $('.datepicker').pickadate({
            selectMonths: true,
            selectYears: 15
         });

         $('.timepicker').timepicker({ 'timeFormat': 'h:i A' });
      });
});