const express = require('express');
const server = express();

server.use(function(req, res, next) {
	console.log('requête reçue' + Date.nom());
	next();
});

server.get('/', function(req, res) {
	res.send('slash');
});

server.get('/private', function(req, res) {
	res.send('private');
});

server.get('/pictures', function(req, res) {
	res.download('fichier.txt');
});

server.use(function(req, res) {
	res.send('erreur');
})

server.listen(8080);