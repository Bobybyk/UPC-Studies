$(document).ready(function() {
	let m = $(magnette);
	m.css({color : "red"});
	m.mouseenter(
		function() {m.text("n'est pas");}
		);
	m.mouseleave(
		function() { m.text(" est "); }
		);
});