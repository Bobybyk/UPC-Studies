const express = require("express");
const server = express();

const bodyParser = require("body-parser");
const cookieParser = require('cookie-parser');
const json = require('express-json');
const cors = require("cors");
const favicon = require('serve-favicon');

const db = require('./database')
db.init(server)

server.use(cookieParser());

server.set('view engine','ejs');
server.use(express.static('public'));
server.use(express.urlencoded({extended: true}));

server.use(favicon(__dirname + '/public/images/favicon.ico'));

var corsOptions = {
	origin: "http://localhost:8080"
};

server.use(cors(corsOptions));
// parse requests of content-type
server.use(bodyParser.json());
// parse requests of content-type
server.use(bodyParser.urlencoded({ extended: true }));

const routes = require("./routes");
server.use("/", routes);

/*This function updates the database from the data collected on the admin page */
async function updateIngredientsAvailability(ingredientsAvailable) {
	const client = await pool.connect();

	await client.query('UPDATE ingredient SET available = false').catch(err => console.error(err));

	const text = 'UPDATE ingredient SET available = true WHERE name = $1::text';
	for (const [key, value] of Object.entries(ingredientsAvailable)) {
  	if(key != 'send') {
  		await client.query(text,[key]).catch(err => console.error(err));
  	}
	}

	client.release();

	return;
}

server.use(function(req,res,next) {
	res.status(500).end("Error");
});


server.listen(8080);