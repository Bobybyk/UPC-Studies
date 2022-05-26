function initIngredients() {
    $.get("/api/products/ingredients",function(data,status) {
        if(data && data.success) {

            $("#ingredient_template").show();

            for(let e of data.ingredients) {
                const new_elem = $("#ingredient_template").clone()

                new_elem.find("#input").attr("name",e.name)
                if(e.available) {
                    new_elem.find("#input").prop("checked",true)
                }

                new_elem.find("#label").attr("for",e.name)
                new_elem.find("#label").html(e.name)

                new_elem.appendTo("#boxes")
            }

            $("#ingredient_template").hide();
        }
    });
}

$(document).ready(() => {
    initIngredients()
})