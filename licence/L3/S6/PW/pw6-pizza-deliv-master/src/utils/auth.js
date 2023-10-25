const bcrypt = require('bcryptjs')

function passToHash(password) {
    const hash = bcrypt.hash(password, 10);
    return hash
}

function checkPassword(password, hash) {
    return bcrypt.compare(password, hash);
}

module.exports = {
    passToHash,
    checkPassword,
}