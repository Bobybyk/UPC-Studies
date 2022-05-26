const express = require('express');
const manage = express.Router();

const auth = require("../../middleware/auth")

manage.get("/admin", auth.isAdmin, (req, res) => {
    res.render("ingredients.ejs")
})

manage.get("/orders", auth.isDelivery, (req, res) => {
    res.send("ok")
})

module.exports = manage;