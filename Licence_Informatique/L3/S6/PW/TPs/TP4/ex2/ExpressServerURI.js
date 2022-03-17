const express = require('express');
const server = express();

server.get('/cours/:titre/descr', function(req, res) {
	res.get('vous avez demandé' + req.params.titre);
});

server.listen(8080);