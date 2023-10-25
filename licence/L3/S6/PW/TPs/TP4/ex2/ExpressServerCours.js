const express = require('express');
const server = express();

const cours = [];
cours[0] = {titre:"prog web", descript:'la description du cours oui oui', prof:['a', 'b', 'c']};
cours[1] = {titre:"A", descript:'B', prof:['a', 'b', 'c', 'd']};
serv.set('view engine', 'ejs');
server.get('/cours/:number', function(req, res) {
	let n = parseInt(req.params.number);
	if (n < cours.length) {
		res.render('cours.ejs', cours[n]);
	} else {
		res.status(500).end("Erreur num");
	}
});

server.listen(8080);