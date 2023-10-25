$(function() {

	function prefix(text) {
		$.get("http://localhost:8080/dictionary/search", {word : text}, function(data) {
			insert(data);
		});
	}

	$("#query").on('input', function(e) {
		const text = $("#query").val();
		prefix(text);
	});
});

function insert(data) {

	for (var i = 0 ; i < data.length ; i++) {
		var p = $('<p>' + data[i] + '</p>');
		$("#result").append(p);
	}
}