$(document).ready(function() {
	// les noeuds du document
	var nom = $("input[name=nom]");
	var prenom = $("input[name=prenom]");
	var civil = $("select[name=civil]");
	var complet = $("input[name=complet]");
	
	function nomComplet() {
		complet.val(civil.val() + " " + prenom.val() + " " + nom.val());
	};

	nom.keyup(nomComplet);
	//prenom.keyup(nomComplet):
	civil.change(nomComplet);

	function level() {
		$("output[name=niv]").val($("input[name=niveau]").val());
	};

	$("input[name=niveau]").mousemove(level);
});