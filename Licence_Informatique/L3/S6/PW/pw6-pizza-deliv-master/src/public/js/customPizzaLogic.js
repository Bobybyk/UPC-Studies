const amountIngredients = 6;

function populateIngredients(ingredients) {
    let el = $('#ingredient-template-select')

    for (let ing of ingredients) {
        if(ing.available) {
            el.append($('<option>', {
                value: ing.name.toLowerCase(), 
                text: ing.name
            }));
        }
    }
}

function cloneTemplate(amount) {
    let el = $('#ingredient-template')

    for(let i = 0; i < amount; i++) {
        const newEl = el.clone(true)
        newEl.attr("id", "ingredient-" + i)
        newEl.find("#ingredient-template-select").attr("id", "ingredient-template-select-" + i)
        newEl.appendTo("#ingredient-list")
        newEl.show()
    }
}

function populateDropSelect() {
    $.get('/api/products/ingredients', (res) => {
        if (res.success) {
            populateIngredients(res.ingredients)
            cloneTemplate(amountIngredients)
        }
    })
}

function sendCustom() {
    let ingreRes = ""

    let price = 12;
    let count = 1

    for(let i = 0; i < amountIngredients; i++) {
        const current = $(`#ingredient-${i} option:selected`).val()

        if(ingreRes != "" && current != "") {
            ingreRes = ingreRes + ", "
            count++
        }

        ingreRes = ingreRes + current
    }

    if(count > 3) {
        price += (count-3) * 2
    }

    $.post('/api/cart/add-custom', {type: 'custom', price: price, description: ingreRes}, (res) => {
        populateCart()
    })
}

$(document).ready(() => {
    $('#ingredient-template').hide()
    populateDropSelect()
})