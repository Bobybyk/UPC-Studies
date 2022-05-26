const cartQueries = require('../queries/cartQueries')

async function getNextDelivery() {
    const data = await cartQueries.getNextDelivery()
    return data
}

async function finishedDelivery(id) {
    const data = await cartQueries.removeOrder(id)
    return data
}

module.exports = {
    getNextDelivery,
    finishedDelivery
}