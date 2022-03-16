console.log("Reading file...");
const fs = require('fs');
fs.readFile('./test.txt', function(err, data) {
	if (err) throw err;
	console.log(data.toString('utf8'));
});