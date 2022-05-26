const cartQueries = require('../queries/cartQueries')
const randomId = require('../utils/id')

async function clear(session) {
    session.cart = { items: [] }
}

function addItem(session, elem) {
    if (session.cart === undefined)
        clear(session)

    const find_el = session.cart.items.find(e => e.name === elem.name)

    if (find_el) {
        find_el.quantity++
    } else {
        session.cart.items.push({ name: elem.name, type: elem.type, price: elem.price, quantity: 1 })
    }
}

function addCustomPizza(session, elem) {
    if (session.cart === undefined)
        clear(session)

    session.cart.items.push({ name: "Custom Pizza", type: elem.type, description: elem.description, price: elem.price, quantity: 1 })
}



function saveCart(session, delivery_time) {
    const order_id = randomId.generateId()

    const user_name = session.user === undefined ? "Anonymous" : session.user.user_name

    for (let e of session.cart.items) {
        if(e.type === "custom")
            cartQueries.insertOrder(order_id, e.name, user_name, delivery_time, e.price,e.description)
        else
            cartQueries.insertOrder(order_id, e.name, user_name, delivery_time, e.price,null)
    }

    clear(session)
}

function removeItem(session, item) {
    if (session.cart === undefined) {
        clear(session)
    }

    const name = item;

    const find_elem = session.cart.items.find(e => e.name === name)

    if (find_elem) {
        if (find_elem.quantity === 1) {
            session.cart.items.pop(find_elem)
        } else {
            find_elem.quantity--;
        }
    }
}

module.exports = {
    clear,
    addItem,
    saveCart,
    removeItem,
    addCustomPizza
}