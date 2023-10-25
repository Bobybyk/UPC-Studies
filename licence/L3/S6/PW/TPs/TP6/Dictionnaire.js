var dico = require('./Dico');
dico.insert("text");

const express
const server

server.use
server.use

serv.get('/dictionary', function(req, res) {
	res.json(dico.words());
});

server.get('/dictionary/search', function(req, res) {
	res.json(dico.prefixSearch(req.query.word));
});

server.post('/dictionary', function(req, res) {
	dico.insert(req.body.word);
});

server.listen(8080);