const express = require('express');
const auth = express.Router();

const authController = require('../../../controllers/auth.controller')

auth.post('/register', async (req, res) => {
    const username = req.body.username
    const password = req.body.password
    const email = req.body.email

    const data = await authController.registerUser(username, password, email)

    if(data.success === true) {
        req.session.user = data.data[0]
    }

    res.json({ success: data.success })
})

auth.post('/login', async (req, res) => {
    const username = req.body.username
    const password = req.body.password

    const data = await authController.login(username, password)

    if(data.auth === true) {
        req.session.user = data.user
    }

    res.json({ success: data.auth })
})

auth.get('/logout', async (req, res) => {
    req.session.destroy()
    setTimeout(() => res.redirect('/'), 150)
})

module.exports = auth;