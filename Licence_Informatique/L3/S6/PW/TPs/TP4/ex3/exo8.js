const express = require("express");
const server = express();

const cours = [];
cours[0] = {
	id : 0,
	titre : "OCaml",
	descriptif: "De la programmation fonctionnelle miam miam miam",
	profs : ["Random-man","Machin","Truc"]
};
cours[1] = {
	id : 1,
	titre : "Système d'exploitation",
	descriptif: "Du C trop bien",
	profs : ["Random-man","Machin","Truc"]
};

server.set('view engine','ejs');

server.get('/cours/:number',function(req,res,next) {
	let n = parseInt(req.params.number);
	if(n < cours.length)
		res.render('cours.ejs',cours[n]);
	else
		res.status(500).end("error");
});

server.listen(8080);
