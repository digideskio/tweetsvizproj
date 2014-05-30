$(function() {

    initAnimation();
    homeChange();
    $("#infotips").tooltip();
    $("#resultcontent").hide();
    $("#searchfd").submit(function(event) {
        $("#resultcontent").show();
        $('html, body').animate({
            scrollTop: ($("#resultcontent").offset().top - 50)
        }, 2000);

        $('#resultcontent').addClass('animated fadeInRight');
        
        if ($('#compare').is(":checked")) {
        	$("#t-1").click();
        } else {
        	$("#t-2").click();
        }
        event.preventDefault();
    });
    $('#compare').click(function () {
        $("#keyword2").toggle(this.checked);
        if(this.checked) {
        	$("#searchfd").height(110);
        	
        } else {
        	$("#searchfd").height(55);
        }
    });
});

$(window).scroll(function() {
    scrollBanner();
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
