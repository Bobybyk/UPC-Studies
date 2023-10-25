const db = require("../database")

async function getPizzas() {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM recette where recette.name NOT IN (SELECT r.name FROM recette r INNER JOIN ingredient i ON (r.ingredient2 = i.name OR r.ingredient3 = i.name OR r.ingredient4 = i.name OR r.ingredient5 = i.name OR r.ingredient6 = i.name OR r.ingredient1 = i.name) WHERE NOT i.available);', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function getAppetizers() {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM entree', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function getDrinks() {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM boisson', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function getDesserts() {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM dessert', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function getIngredients() {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM ingredient ORDER BY name', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function removeAllIngredients() {
    return new Promise((resolve, reject) => {
        db.get().query('UPDATE ingredient SET available = false', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function enableIngredient(ingredient) {
    await db.get().query('UPDATE ingredient SET available = true WHERE name = \''+ingredient+'\'')
}

async function updateIngredientsAvailability(ingredients) {
    await removeAllIngredients()

    for(let e of Object.keys(ingredients)) {
        await enableIngredient(e)
    }
}

async function getSauces() {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM sauce', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

module.exports = {
    getPizzas, 
    getAppetizers,
    getDrinks,
    getDesserts,
    getIngredients,
    getSauces,
    updateIngredientsAvailability
}