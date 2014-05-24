$(function() {

    initAnimation();
    homeChange();
    
    $("#resultcontent").hide();
    $("#searchfd").submit(function(event) {
        $("#resultcontent").show();
        $('html, body').animate({
            scrollTop: ($("#resultcontent").offset().top - 50)
        }, 2000);

        $('#resultcontent').addClass('animated fadeInRight');
        event.preventDefault();
    });
});

$(window).scroll(function() {
    scrollBanner();
});

/**
 * pass keyword to server then return query results
 */
$(document).ready(function() {
	$('#search').click(function(event) {
		var keyword = $('#keyword').val();
		$.get('TweetsVizJSPServlet', {
			keyword : keyword
		}, function(responseText) {
			$('#tempresults').text(responseText);
		});
	});
});
    
function homeChange() {
    var windowWidth = $(window).width();
    if ($(window).width() > 780) {
        $('.insta-wrapper').hover(function() {
            $(this).find('h1').text('Course Project').addClass('green');
            $(this).find('h2').text('CS886 Affective Computing | 2014 May').addClass('green');
            $(this).find('.bike').addClass('green-bike');
        }, function() {
            $(this).find('h1').text('Tweets Visualization').removeClass('green');
            $(this).find('h2').text('a Tweets Sentiment Analysis toolkit').removeClass('green');
            $(this).find('.bike').removeClass('green-bike');
        });
    }
}

function checkViewport() {
    $(".animate:in-viewport(-10)").each(function() {
        $(this).addClass('do-anim');
    });
}

function initAnimation() {
    checkViewport();
    $(window).bind("scroll", function(event) {
        checkViewport();
    }); //end scroll
    $(window).resize(function() {
        checkViewport();
    });
}

function scrollBanner() {
    scrollPos = $(this).scrollTop();
    $('.insta-mosaic > span').css({
        'top': (-scrollPos / 3) + "px"
    });
}
