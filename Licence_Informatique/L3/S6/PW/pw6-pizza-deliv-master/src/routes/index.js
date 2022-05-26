const express = require('express');
const router = express.Router();

const auth = require('../middleware/auth')

const apiRoutes = require('./api')
const manageRoutes = require('./manage')
const deliveryRoutes = require('./delivery')

router.use("/api", apiRoutes)
router.use("/manage", manageRoutes) 
router.use("/delivery", auth.isDelivery, deliveryRoutes)

router.get("/", (req, res) => {
    const data = { 
        isLoggedIn: req.session.user != undefined, 
        isAdmin: req.session.user ? req.session.user.user_role === "admin" : false,
        isDelivery: req.session.user ? req.session.user.user_role === "delivery" : false,
    }

    res.render("home.ejs", data);
});

module.exports = router;