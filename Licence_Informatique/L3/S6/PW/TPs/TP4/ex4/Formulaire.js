const express = require('express');
const server = express();

// pour traiter le post
server.use(express.urlencoded({extended : true}));
// pour les patrons ejs et le render
server.set('view engine', 'ejs');

server.get('/', function(req,res) {
	res.render('form.ejs', {titre:"identifiez-vous"});
});

const comptes = [];

// retourne { nouveau : t ou f, ind : int}
function recherche(entree) {
	let i;
	for (i = 0 ; i < comptes.length ; i++) {
		if (comptes[i].nom == entree.nom) {
			return { nouveau : false , ind :i};
		}
	}
	return { nouveau : true , ind : i};
}

server.post("/", function(req, res) {

	let data = req.body;

	if (data.nom == '' || data.mdp == '') {
		res.render('register.ejs', { titre : "remplissez" } );
		console.log("formulaire");
	}

	let info = recherche( { nom : data.nom } );
	comptes[info.ind] = { nom : data.nom , mdp : data.mdp };

	if (info.nouveau) {
		res.render('register.ejs', { nom : data.nom });
		console.log("register");
	} else {
		res.render('login.ejs', { nom : data.nom });
		console.log("login");
	}
	
});

server.listen(8080);