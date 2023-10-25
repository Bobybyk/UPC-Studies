const http = require('http');
const server = http.createServer(
	function(req, res) {
		res.write('bonjour');
		res.end();
	});
server.listen(8080);