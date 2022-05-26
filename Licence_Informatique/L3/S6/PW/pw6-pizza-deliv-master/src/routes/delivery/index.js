const express = require("express")
const router = express.Router()

router.get("/next", (req,res) => {
    res.render("order.ejs")
})

module.exports = router