$document.ready(function() {
	$('.blockRouge').mouseover(function() {
		console.log("Vous entrez");
	});
	var nbUp = 0;
	var nbBas = 0;
	$('.blockRouge').mousedown(function() {
		$(this).css("border_color", "green");
		var nouveau = $("<p class='np'> up </p>");
		$('header').append(nouveau);
		nouveau.click(supprime);
		nbUp++;
		refreshBlock();
	}); // fin mouse down
}); // fin document ready

var supprime= function() {
	$(this).hide();
	if($(this).hasClass("np")) {
		nbUp--;
	} else {
		nbBas--;
	}
	refreshBlock();
}

function refreshBlock() {
	$(haut).text(nbUp);
	$(bas).text(nbBas);
}