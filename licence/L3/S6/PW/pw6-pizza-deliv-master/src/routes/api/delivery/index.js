const express = require('express');
const router = express.Router();

const deliveryController = require('../../../controllers/delivery.controller')

router.get('/next', async (req, res) => {
    const data = await deliveryController.getNextDelivery()
    res.json(data)
})

router.get('/done', async (req, res) => {
    const id = req.query.order_id

    if (id === undefined) {
        res.json({ success: false })
    } else {
        const data = await deliveryController.finishedDelivery(id)
        res.json(data)
    }
})

module.exports = router