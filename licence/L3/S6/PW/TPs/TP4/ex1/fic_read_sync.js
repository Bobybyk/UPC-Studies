const fs = require('fs');
console.log("Reading file...");
fs.readFile('./test.txt', function(err, data) {
	try {
		const date = fs.readFileSync('test.txt', 'utf8');
		console.log(data.toString('utf8'));
	} catch(e) {
		console.log(e);
	}
	console.log('...done');
});