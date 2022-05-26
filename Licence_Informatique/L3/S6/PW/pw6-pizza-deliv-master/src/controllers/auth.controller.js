const userQueries = require('../queries/userQueries')
const authUtils = require('../utils/auth')

async function registerUser(username, password, email) {
    const hash = await authUtils.passToHash(password)
    const res = await userQueries.insertUser(username, email, hash, "user")

    const success = res != undefined
    let user = undefined

    if (success)
        user = await userQueries.getUser(username)    

    return { success: success, data: user }
}

async function login(username, password) {
    const res = {}
    let user = await userQueries.getUser(username)

    if(user.length != 1) {
        return { success: false }
    }

    res.user = user[0]
    res.auth = await authUtils.checkPassword(password, res.user.user_password)

    return res
}

module.exports = {
    registerUser,
    login,
}