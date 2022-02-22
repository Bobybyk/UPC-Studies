$(document).ready(function() {
	for (let i = 0 ; i < 9 ; i++) {
		add_post(random_text(), (i+1));
	}
})

function add_post(text, num) {
	let p = $("<p id=\"post " + num + "\"<h6>Post n°" + num + "</h6>" + text+"</p>");
	$("#posts").append(p);
	
	let l = $("<li class=\"new-item\"><a href=\"#post" + num "\"> Post n°" + num + "</a></li>");
	$("#liens").append(l);
}

function rand_text() {
	let rep = "bla";
	let p = 10 + Math.floor(Math.random() % 50);
	for (let i = 0 ; i < p ; i++) {
		rep += " bla ";
	}
	return p;
}