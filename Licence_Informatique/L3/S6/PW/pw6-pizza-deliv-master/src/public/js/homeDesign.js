$(document).ready(function () {

	//to show or hide the pizza customization overlay
	$("#custom-pizza").on("mouseenter", function () {

		//$(this).animate({"box-shadow": "2px 2px 5px black"},"slow");
		$(this).css({ "box-shadow": "2px 2px 5px black" });				//TODO: It could be nice to animate this

	}).on("mouseleave", function () {

		$(this).removeAttr('style');

	}).on("click", function () {

		if ($(".persisting-overlay").css("display") === "none") {
			$(".persisting-overlay").show(500);
			$(this).width($(this).width() * 1.5);
		}
		else
			$(".persisting-overlay").hide(500);
	});

	//disable fake links
	$(".dummy-link").on("click", function (e) {
		e.preventDefault();
	});

	//TO animate the menu bar 
	$('#sidebarCollapse').on('click', function () {
		$('#sidebar').toggleClass('active');
		$(this).toggleClass('active');
	});

	//To trigger the overlays on the images

	$('.wrap').on("mouseenter", function () {

		let overlay = $(this).children('.overlay');
		if (overlay.length === 0)
			return false;

		overlay.css({ "opacity": ".75" });	//to adjust opacity of the overlay

		if ($(this).children('img').attr('src').includes("pizzas", 0)) {	//if the div hovered contains an image of a pizza
			overlay.animate({ "height": "550px" }, "fast");	//expand it
		}

	}).on("mouseleave", function () {
		$(this).children('.overlay').css({ "opacity": "0" });	//make it disappear
		$(this).children('.overlay').animate({ "height": $(this).children('.overlay').width() }, "fast");	//reset the height
	});


	// Show or hide login/register/disconnect
	if (isLoggedIn) {
		if (isAdmin) {
			$('#nav-admin').show()
		} else {
			$('#nav-admin').hide()
		}

		if (isDelivery || isAdmin) {
			$('#nav-delivery').show()
		} else {
			$('#nav-delivery').hide()
		}

		$('#login-login').hide()
		$('#login-register').hide()
		$('#login-logout').show()
	} else {
		$('#nav-admin').hide()
		$('#nav-delivery').hide()

		$('#login-register').show()
		$('#login-login').show()
		$('#login-logout').hide()
	}

	// Set date on field
	const now = new Date()
	const order = new Date(now.getTime() + 30 * 60000);

	order.setMinutes(order.getMinutes() - order.getTimezoneOffset());

	$('#delivery_date').val(order.toISOString().slice(0, 16));
});