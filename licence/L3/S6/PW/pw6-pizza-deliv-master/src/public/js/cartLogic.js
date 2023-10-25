function addItem(e) {
    const name = e.parent().parent().parent().attr("id")
    const type = e.parent().parent().parent().parent().parent().attr("id")
    const price = parseInt(e.parent().find("#price").html())
    let desc = ""

    if(type === "appetizers") {
        desc = e.parent().find("#sauces").val()
    }

    $.post("/api/cart/add", { type: type, name: name, price: price, description: desc }, (data, status) => {
        if (data && status === 'success') {
            populateCart();
        }
    });
}

function clearCart() {
    $.get("/api/cart/clear", (data, status) => {
        if (data && status === 'success') {
            populateCart()
        }
    })
}

function populateCart() {
    $.get("/api/cart/get", (data, status) => {
        let total = 0

        if (data && status === "success") {
            $("#cart").empty()                                      //empty the cart display element

            let cart = $("<div></div>")
            for (let v of data) {
                cart.append("<div id=\"" + v.name + "\"><span>" + v.name + " "+v.price+ "€ x"+ v.quantity +" ("+ (v.price * v.quantity) +"€)</span><input class=\"btn btn-danger\" value=\"X\" type=\"button\" onClick=\"removeItem($(this))\"/></div>")
                total += parseInt(v.price * v.quantity)
            }
            $("#cart").append(cart)
            $("#cart").append($("<p>Le prix total: " + total + " €</p>"))
        }
    })
}

function saveCart() {
    const raw = ($("#delivery_date").val() + ":00").split("T")
    const delivery_time = raw[0] + " " + raw[1]

    $.post("/api/cart/save", { delivery_time: delivery_time }, (data, status) => {
        if (data && status === 'success') {
            $("#cart").empty()
        }
    })
}

function removeItem(e) {
    const toRemove = e.parent().attr("id")
    $.post("/api/cart/remove", { item: toRemove }, (data, status) => {
        if (data && status === "success") {
            populateCart()
        }
    })
}

$(document).ready(() => {
    populateCart()
})