function fade(){ 

$('#envelope').fadeOut(400);
$('#fade').fadeOut(400);
$(this).css('display', 'none'); // делaем ему display: none;
$('#black-overlay').fadeOut(400);
$('#fade_back').fadeOut(400);
}
function fadeIn(){ 

$('.envelope').fadeIn(400);
$('#fade').fadeIn(400);
$('#fade_back').css('display', 'flex');
$('.black-overlay').css('display', 'flex');
$('.envelope').css('display', 'flex');
 // делaем ему display: none;
$('.black-overlay').fadeIn(400);
$('#fade_back').fadeIn(400);
body.style.overflow='hidden';
}
		