/**
 * pass keyword to server then return query results
 */
$(document).ready(function() {
	$('#search').click(function(event) {
		var keyword = $('#keyword').val();
		$.get('TweetsVizJSPServlet', {
			keyword : keyword
		}, function(responseText) {
			$('#results').text(responseText);
		});
	});
});
    
