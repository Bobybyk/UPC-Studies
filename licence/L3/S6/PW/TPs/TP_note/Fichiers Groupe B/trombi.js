function texte (n) {
    let t = "";
    let lettres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789                  ";

    for (let i = 0; i < n; i++)
        t += lettres.charAt (Math.floor (Math.random() * lettres.length));

    return t;
}

$(document).ready (function () {
    $("#texte>p").html(texte(500));

    for (let i = 0; i < 91; i++) {
	let elphoto = '<div';
	if (i%10 == 0)
	    elphoto += ' id="ph' + i + '"';
	elphoto += ' class="col-6 col-sm-4 col-md-3 col-lg-2 text-center photo">';
	elphoto += '<img src=images/' + i + '.jpg /></div>';
        let el = $(elphoto);
	$("#photos").append(el);
    }

    let str = '';
    for (i = 0; i < 10; i++) {
	str += '<li class="nav-item"><a class="nav-link" href="#ph' + (10*i) + '">' + (10*i) + '</a></li>';
    }

    $("#liens").html (str);

});
