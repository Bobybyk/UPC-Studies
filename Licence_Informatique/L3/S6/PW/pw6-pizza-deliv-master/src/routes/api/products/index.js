const express = require('express');
const router = express.Router();

const productController = require("../../../controllers/product.controller")

router.get("/drinks", async (req, res) => {
    const ret = await productController.getDrinks()
    res.json(ret)
})

router.get("/desserts", async (req, res) => {
    const ret = await productController.getDesserts()
    res.json(ret)
})

router.get("/appetizers",async(req,res) => {
    const ret = await productController.getAppetizers()
    res.json(ret)
})

router.get("/pizzas",async(req,res) => {
    const ret = await productController.getPizzas()
    res.json(ret)
})

router.get("/ingredients",async(req,res) => {
    const ret = await productController.getIngredients()
    res.json(ret)
})

router.post("/update_ingredients",async (req,res) => {
    await productController.updateIngredients(req.body)
    res.redirect("/")
})

router.get("/sauces",async(req,res) => {
    const ret = await productController.getSauces()
    res.json(ret)
})

module.exports = router;