const productQueries = require('../queries/productQueries')

async function getPizzas() {
    const res = { success: true } 
    res.pizzas = await productQueries.getPizzas()

    return res;
}

async function getAppetizers() {
    const res = { success: true } 
    res.appetizers = await productQueries.getAppetizers()

    return res;
}

async function getDrinks() {
    const res = { success: true } 
    res.drinks = await productQueries.getDrinks()

    return res;
}

async function getDesserts() {
    const res = { success: true } 
    res.desserts = await productQueries.getDesserts()

    return res;
}

async function getIngredients() {
    const res = { success: true } 
    res.ingredients = await productQueries.getIngredients()

    return res;
}

async function updateIngredients(availableIngredients) {
    const res = {success: true}

    await productQueries.updateIngredientsAvailability(availableIngredients)

    return res
}

async function getSauces() {
    const res = {success: true}

    res.sauces = await productQueries.getSauces()

    return res
}

module.exports = {
    getPizzas, 
    getAppetizers, 
    getDrinks, 
    getDesserts, 
    getIngredients,
    updateIngredients,
    getSauces
}