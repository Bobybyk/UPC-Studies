const db = require("../database")

async function insertOrder(order_id, order_type, user_name, order_date,price,description) {
    return new Promise((resolve, reject) => {
        const qer = 'INSERT INTO cart (order_id, order_type, user_name, order_date,price,description) VALUES (\'' 
        + order_id + '\', \'' + order_type + '\', \'' + user_name + '\', \'' + order_date + '\',\''+price+'\',\''+description+'\')'
         db.get().query(qer, (err, data) => {
            if (err) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function getNextDelivery() {
    return new Promise((resolve, reject) => {
    db.get().query('SELECT * FROM cart WHERE order_id = (SELECT order_id FROM cart ORDER BY order_date ASC LIMIT 1)', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function getOrders() {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM cart', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

async function getUserOrders(username) {
    return new Promise((resolve, reject) => {
        db.get().query('SELECT * FROM cart WHERE user_name = \'' + username + '\'', (err, data) => {
            if (data === undefined) {
                reject(new Error());
            } else {
                resolve(data.rows);
            }
        })
    })
}

function removeOrder(orderId) {
    return new Promise((resolve, reject) => {
        db.get().query('DELETE FROM cart WHERE order_id = \'' + orderId + '\'', (err, data) => {
            resolve({success: true})
        })
    })
}

module.exports = {
    insertOrder,
    getNextDelivery,
    getOrders,
    getUserOrders,
    removeOrder
}