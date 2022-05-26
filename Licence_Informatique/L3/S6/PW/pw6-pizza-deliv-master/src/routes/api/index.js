const express = require('express');
const router = express.Router();

const auth = require('../../middleware/auth')

const authRoutes = require('./auth')
const productsRoutes = require('./products')
const cartRoutes = require('./cart')
const deliveryRoutes = require('./delivery')

router.use("/auth", authRoutes)
router.use("/products", productsRoutes)
router.use("/cart", cartRoutes)
router.use("/delivery", auth.isDelivery, deliveryRoutes)

module.exports = router