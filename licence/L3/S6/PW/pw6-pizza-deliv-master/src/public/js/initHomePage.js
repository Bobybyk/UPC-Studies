function initDrinks() {
    $.get("/api/products/drinks", function (data, status) {
        if (data && data.success) {

            $("#no_drinks").hide()
            $("#drink_template").show()

            for (let e of data.drinks) {
                const el = $("#drink_template").clone(true)

                el.attr("id",e.name)

                el.find("#image").attr("src", "/images/drinks/" + e.name + ".png")
                el.find("#image").attr("title", e.name)
                el.find("#image").attr("id", e.name)

                el.find("#name").html(e.name)
                el.find("#price").html(e.price+"€")
                el.appendTo("#drinks_list")
            }

            $("#drink_template").hide()
        }
    });
}

function initDesserts() {
    $.get("/api/products/desserts", function (data, status) {
        if (data && data.success) {

            $("#no_desserts").hide()
            $("#dessert_template").show()

            for (let e of data.desserts) {
                const el = $("#dessert_template").clone(true)

                el.attr("id",e.name)

                el.find("#image").attr("src", "/images/desserts/" + e.name + ".png")
                el.find("#image").attr("title", e.name)
                el.find("#image").attr("id", e.name)

                el.find("#name").html(e.name)
                el.find("#price").html(e.price+"€")
                el.appendTo("#desserts_list")
            }

            $("#dessert_template").hide()
        }
    });
}

function initAppetizers() {

    $.get("/api/products/appetizers", function (data, status) {
        if (data && data.success) {

            $("#no_appetizers").hide()
            $("#appetizers_template").show()

            for (let e of data.appetizers) {
                const el = $("#appetizers_template").clone(true)

                el.attr("id",e.name)

                $.get("/api/products/sauces", (data,status) => {
                    if(data && data.success) {
                        for(let e of data.sauces) {
                            el.find("#sauces").append("<option value=\""+e.name+"\">"+e.name+"</option>")
                        }
                    }
                })

                el.find("#image").attr("src", "/images/appetizers/" + e.name + ".png")
                el.find("#image").attr("title", e.name)
                el.find("#image").attr("id", e.name)

                el.find("#name").html(e.name)
                el.find("#price").html(e.price+"€")
                el.appendTo("#appetizers_list")
            }

            $("#appetizers_template").hide()
        }
    });
}

function initPizzas() {

    $.get("/api/products/pizzas", function (data, status) {
        if (data && data.success) {

            $("#no_pizzas").hide()
            $("#pizzas_template").show()

            for (let e of data.pizzas) {
                const el = $("#pizzas_template").clone(true)

                el.attr("id",e.name)

                el.find("#image").attr("src", "/images/pizzas/" + e.name + ".png")
                el.find("#image").attr("title", e.name)
                el.find("#image").attr("id", e.name)

                el.find("#name").html(e.name)
                el.find("#price").html(e.price+"€")
                el.appendTo("#pizzas_list")
            }

            $("#pizzas_template").hide()
        }
    });
}

$(document).ready(() => {
    initDrinks();
    initDesserts();
    initAppetizers();
    initPizzas();
})