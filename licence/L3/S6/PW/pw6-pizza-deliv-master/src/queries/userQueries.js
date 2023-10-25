const db = require("../database")

function getUsers() {
    return new Promise(async (resolve, reject) => {
        await db.get().query('SELECT * FROM user', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

function getUser(username) {
    return new Promise(async (resolve, reject) => {
        const res = await db.get().query('SELECT * FROM account WHERE user_name = \'' + username + '\'')
        resolve(res.rows)
    })
}

function insertUser(username, email, password, role) {
    return new Promise(async (resolve, reject) => {
        await db.get().query('INSERT INTO account (user_name, user_password, user_email, user_role) VALUES (\''
            + username + '\', \'' + password + '\', \'' + email + '\', \'' + role + '\')', (err, data) => {
                resolve(data)
            })
    })
}

module.exports = {
    getUsers,
    getUser,
    insertUser
}