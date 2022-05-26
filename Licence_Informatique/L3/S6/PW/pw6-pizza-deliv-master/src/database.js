const pg = require('pg');
const session = require('express-session')
const pgSession = require('connect-pg-simple')(session)
const sessionPool = require('pg').Pool

let _db = null

//DATABASE CONFIG
const dbConfig = {
	user: 'test',
	host: 'localhost',
	database: 'test',
	password: 'test'
}

async function init(server) {
    const pool = new pg.Pool(dbConfig);
    const sessionDBaccess = new sessionPool(dbConfig)

    const sessionConfig = {
        store: new pgSession({
            pool: sessionDBaccess,
            tableName: 'SESSION'
        }),
        name: 'SID',
        secret: "secret",
        resave: false,
        saveUninitialized: true,
        cookie: {
            maxAge: 1000 * 60 * 60 * 24 * 7,
            aameSite: true,
            secure: false // ENABLE ONLY ON HTTPS
        }}

    server.use(session(sessionConfig))

    _db = await pool.connect()
}

function get() {
    return _db
}

module.exports = {
    init,
    get,
}