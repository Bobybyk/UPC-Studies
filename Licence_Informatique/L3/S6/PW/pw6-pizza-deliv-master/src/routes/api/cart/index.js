const express = require('express')
const router = express.Router()

const cartController = require("../../../controllers/cart.controller")

router.get("/get",(req,res) => {
    let session = req.session

    let data = []

    if(session.cart)
        data = session.cart.items

    res.json(data)
})

router.post("/add", (req, res) => {
    let session = req.session
    cartController.addItem(session,req.body);
    res.sendStatus(200).end()
})

router.post("/add-custom",(req,res) => {
    let session = req.session
    cartController.addCustomPizza(session, req.body)
    res.sendStatus(200).end()
})

router.get("/clear",(req,res) => {
    cartController.clear(req.session);
    res.sendStatus(200).end();
})

router.post("/save",(req,res) => {
    cartController.saveCart(req.session, req.body.delivery_time);
    res.sendStatus(200).end();
})

router.post("/remove",(req,res) => {
    cartController.removeItem(req.session,req.body.item)
    res.sendStatus(200).end()
})

module.exports = router;