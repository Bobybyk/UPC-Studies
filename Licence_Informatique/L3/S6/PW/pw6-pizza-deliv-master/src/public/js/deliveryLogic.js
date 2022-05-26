let currentOrder = undefined;
let totalPrice = 0;

function finishOrder() {
    $.get(`/api/delivery/done?order_id=${currentOrder}`, (res) => {
        if(res.success)
            window.alert("Livraison terminée!")
        else
            window.alert("Une erreur est survenue.")

        location.reload()
    })
}

function addEl(e) {
    const el = $("#item_template").clone(true).show()

    el.attr("id", e.id)

    let name = e.order_type
    if (name === "Custom Pizza") {
        name = name + " : " + e.description
    }

    el.find("#user").html(e.user_name)
    el.find("#name").html(name)
    el.find("#price").html(e.price + " €")

    totalPrice = totalPrice + e.price

    el.appendTo("#orders_list")
}

function populateNextDelivery() {
    $.get('/api/delivery/next', (res) => {
        if(res.length === 0) {
            $("#no_orders").show()
        } else {
            $("#orders").show()

            currentOrder = res[0].order_id
            $("#order_n").html($("#order_n").html() + currentOrder)
        }
        
        for(let i of res) {
            addEl(i)
        }
    
        $("#total_price").html(totalPrice)
    })
}

$(document).ready(() => {
    $("#item_template").hide()
    $("#no_orders").hide()
    $("#orders").hide()
    populateNextDelivery()
})