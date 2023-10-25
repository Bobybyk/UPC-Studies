const http = require('http');
const fs = require('fs');
const server = http.createServer(
	function(req, res) {
		fs.readFile('test.txt', function(err, data) {
			if (err) {
				res.writeHead(500);
				res.write("error");
			} else {
				let body = data.toString('utf8');
				res.writeHead(200, {'content-length' : body.length, 'content-type' : 'text/plain'});
				res.write(body);
			}
			res.end();
		})
	});
server.listen(8080);