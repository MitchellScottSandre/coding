/**
 * Created by scott on 2017-08-18.
 */
$(document).ready(function(){

    // ScrollSpy
    $('.scrollspy').scrollSpy({
        scrollOffset: $('#aboutMe .row-heading').height()
    });

    // Parralax
    $('.parallax').parallax();

    // Add rotating text
    var descriptions = ["Software Engineer", "Full Stack Developer", "Java Wizard", "Web Developer", "iOS Developer"];
    for (var i = 0; i < 40; i++){
        $('#rotatingText').append(
            '<li style="visibility: hidden;">' + descriptions[i % descriptions.length] + '</li>'
        );
    }

    // Title Textulate animations
    $('.tlt_1').textillate();
    $('.tlt_2').textillate({
        initialDelay: 3,
        minDisplayTime: 5,
        in: { effect: 'fadeInUp', sync: true },
        out: { effect: 'fadeOutUp', sync: true},
        loop: true
    });

    // Load Particle JS
    particlesJS("particles-js", particleData);

    // Toggle Nav Overlay
    $("#overlayToggler").on("click", function(){
        $(this).toggleClass('open');
        $('.tlt_4').textillate();
        $("body").toggleClass("bodyBlurred");

        if ($(this).attr("class").indexOf("open") != -1){
            $('#navOverlay').css({
                "display"        : "block"
            });

        } else {
            $('#navOverlay').css({
                "display": "none"
            });
        }
    });

    // Close the overlay when an a item is clicked
    $(".overlay-content a").on("click", function(){
        $("#overlayToggler").toggleClass('open');
        $('.tlt_4').textillate();
        $("body").toggleClass("bodyBlurred");

        if ($("#overlayToggler").attr("class").indexOf("open") != -1){
            $('#navOverlay').css({
                "display"        : "block"
            });

        } else {
            $('#navOverlay').css({
                "display": "none"
            });
        }
    });

    // ON SCROLL
    $(document).on('scroll', function (e) {
        // Navbar Transparency
        e.preventDefault();
        var o = $(document).scrollTop() / 500;
        if (o > 1.000) { o = 1;}
        var e = $('nav');
        $('nav').not('a').css('background-color', 'rgba(3, 169, 244,' + o + ')');
        $('.brand-logo').css('opacity', o);
        if (o == 0){
            $('.navLabel').css('color', 'black');
            $('#overlayToggler span').css('background', 'black');
        } else {
            $('.navLabel').css('color', 'white');
            $('#overlayToggler span').css('background', 'white');
        }

        // Check if at the bottom of the page, set Resume/Contact to active
        if($(window).scrollTop() + $(window).height() == $(document).height()) {
            $('.nav4').removeClass('active');
            $('.nav5').addClass('active');
        } else {
            $('.nav5').removeClass('active');
        }
    });

    // Add all the skills stars for skill cards
    $('.skillStars').each(function(){
        var num = parseInt($(this).attr("data-stars"));
        for (var i = 0; i < num; i++){
            $(this).append("<i class='material-icons'>star_border</i>");
        }
    });

    // Add colour to timeline circle on hover
    $('.cd-timeline-content').mouseenter(function(){
        $(this).addClass("z-depth-5");
        $(this).siblings('.cd-timeline-img').css('background-color', '#FF5252');
    });
    $('.cd-timeline-content').mouseleave(function(){
        $(this).removeClass("z-depth-5");
        $(this).siblings('.cd-timeline-img').css('background-color', 'white');
    });

    // Add depth affect to projectCards on hover
    $('.projectCard').mouseenter(function() {
        $(this).addClass("z-depth-5");
    });

    $('.projectCard').mouseleave(function() {
        $(this).removeClass("z-depth-5");
    });

    // W3 Schools Slide Animation
    $(window).scroll(function() {
        $(".slideanim").each(function(){
            var pos = $(this).offset().top;
            var windowHeight = $( window ).height();
            var winTop = $(window).scrollTop();
            if (pos < winTop + windowHeight * 0.95) {
                $(this).addClass("slide");
            }
        });
    });

    // Resume Row on Hover Animation
    $('#resumeRow').mouseenter(function() {
        $(this).find("#resumeText")
            .css('opacity', 1)
            .slideUp('fast')
            .animate(
                { opacity: 0 },
                { queue: false, duration: 'slow' }
            );
        $(this).find("#resumeIcon")
            .css('opacity', 0)
            .slideDown('fast')
            .animate(
                { opacity: 1 },
                { queue: false, duration: 'slow' }
            );
    });

    $('#resumeRow').mouseleave(function() {
        $(this).find("#resumeIcon")
            .css('opacity', 1)
            .slideUp('fast')
            .animate(
                { opacity: 0 },
                { queue: false, duration: 'slow' }
            );
        $(this).find("#resumeText")
            .css('opacity', 0)
            .slideDown('fast')
            .animate(
                { opacity: 1 },
                { queue: false, duration: 'slow' }
            );
    });

    // Set timeline line height on load and on change
    var timelineLineHeight = $('#timelineIcon_2').offset().top - $('#timelineIcon_1').offset().top;
    $('#cd-timeline').css('height', timelineLineHeight + "px");

    // Declare variables for bouncing ball canvas
    var canvas = $('#bouncingBallCanvas');
    var c = canvas.get(0).getContext("2d");
    c.width = Math.min($('#rocketBallCard').width() - 2, $('#bouncingBallCanvas').width() - 2);
    var container = {
        x: 0,
        y: 0,
        width: c.width,
        height: 150
    };

    // ON RESIZE
    $( window ).resize(function() {
        //Fix timeline line height
        var timelineLineHeight2 = $('#timelineIcon_2').offset().top - $('#timelineIcon_1').offset().top;
        $('#cd-timeline').css('height', timelineLineHeight2 + "px");

        //Canvas
        canvas = $('#bouncingBallCanvas');
        c = canvas.get(0).getContext("2d");
        c.width = Math.min($('#rocketBallCard').width() - 2, $('#bouncingBallCanvas').width() - 2);
        canvas.width(c.width);
        container = {
            x: 0,
            y: 0,
            width: c.width,
            height: 150
        };
    });

    var circle = {
        x: 50,
        y: 50,
        r: 10,
        vx: 4,
        vy: 4
    };

    var colours = ["#FFFFFF", "#03A9F4", "#FF5252"];
    var bounceCount = 0;
    function animate() {
        // Draw container background (erases last drawn circle);
        //draw the container
        c.fillStyle = "#9C27B0";
        c.fillRect(container.x, container.y, container.width, container.height);

        // Draw the circle
        c.fillStyle = colours[bounceCount % colours.length];
        c.beginPath();
        c.arc(circle.x, circle.y, circle.r, 0, Math.PI * 2, true);
        c.fill();

        //Bounce logic
        if (circle.x - circle.r + circle.vx < container.x || circle.x + circle.r + circle.vx> container.x + container.width) {
            circle.vx = -circle.vx;
            bounceCount++;
        }

        if (circle.y + circle.r + circle.vy > container.y + container.height || circle.y - circle.r + circle.vy < container.y) {
            circle.vy = -circle.vy;
            bounceCount++;
        }

        circle.x += circle.vx;
        circle.y += circle.vy;

        if (bounceCount > 10000){
            bounceCount = 0;
        }
        requestAnimationFrame(animate);
    }
    requestAnimationFrame(animate);

});

var particleData = {"particles": {
        "number": {
            "value": 265,
                "density": {
                "enable": true,
                    "value_area": 800
            }
        },
        "color": {
            "value": ["#f20b0b", "#03A9F4"]
        },
        "shape": {
            "type": "circle",
                "stroke": {
                "width": 0,
                    "color": "#000000"
            },
            "polygon": {
                "nb_sides": 5
            },
            "image": {
                "src": "img/github.svg",
                    "width": 100,
                    "height": 100
            }
        },
        "opacity": {
            "value": 0.3687847739990702,
                "random": false,
                "anim": {
                "enable": false,
                    "speed": 1,
                    "opacity_min": 0.1,
                    "sync": false
            }
        },
        "size": {
            "value": 6,
                "random": true,
                "anim": {
                "enable": false,
                    "speed": 40,
                    "size_min": 0.1,
                    "sync": false
            }
        },
        "line_linked": {
            "enable": true,
                "distance": 150,
                "color": "#bdbdbd",
                "opacity": 0.3,
                "width": 1
        },
        "move": {
            "enable": true,
                "speed": 1.5,
                "direction": "none",
                "random": false,
                "straight": false,
                "out_mode": "bounce",
                "bounce": false,
                "attract": {
                "enable": false,
                    "rotateX": 600,
                    "rotateY": 1200
            }
        }
    },
    "interactivity": {
        "detect_on": "canvas",
            "events": {
            "onhover": {
                "enable": true,
                    "mode": "repulse"
            },
            "onclick": {
                "enable": true,
                    "mode": "push"
            },
            "resize": true
        },
        "modes": {
            "grab": {
                "distance": 400,
                    "line_linked": {
                    "opacity": 1
                }
            },
            "bubble": {
                "distance": 400,
                    "size": 40,
                    "duration": 2,
                    "opacity": 8,
                    "speed": 3
            },
            "repulse": {
                "distance": 60,
                    "duration": 0.4
            },
            "push": {
                "particles_nb": 4
            },
            "remove": {
                "particles_nb": 2
            }
        }
    },
    "retina_detect": true
};
// var particleData = {"particles": {
//     "number": {
//         "value": 88,
//         "density": {
//             "enable": true,
//             "value_area": 700
//         }
//     },
//     "color": {
//         "value": ["#aa73ff", "#f8c210", "#83d238", "#33b1f8"]
//     },
//     "shape": {
//         "type": "circle",
//         "stroke": {
//             "width": 0,
//             "color": "#000000"
//         },
//         "polygon": {
//             "nb_sides": 15
//         }
//     },
//     "opacity": {
//         "value": 0.5,
//         "random": false,
//         "anim": {
//             "enable": false,
//             "speed": 1.5,
//             "opacity_min": 0.15,
//             "sync": false
//         }
//     },
//     "size": {
//         "value": 2.5,
//         "random": false,
//         "anim": {
//             "enable": true,
//             "speed": 2,
//             "size_min": 0.15,
//             "sync": false
//         }
//     },
//     "line_linked": {
//         "enable": true,
//         "distance": 110,
//         "color": "#33b1f8",
//         "opacity": 0.25,
//         "width": 1
//     },
//     "move": {
//         "enable": true,
//         "speed": 1.6,
//         "direction": "none",
//         "random": false,
//         "straight": false,
//         "out_mode": "out",
//         "bounce": false,
//         "attract": {
//             "enable": false,
//             "rotateX": 600,
//             "rotateY": 1200
//         }
//     }
// },
//     "interactivity": {
//         "detect_on": "canvas",
//         "events": {
//             "onhover": {
//                 "enable": false,
//                 "mode": "repulse"
//             },
//             "onclick": {
//                 "enable": false,
//                 "mode": "push"
//             },
//             "resize": true
//         },
//         "modes": {
//             "grab": {
//                 "distance": 400,
//                 "line_linked": {
//                     "opacity": 1
//                 }
//             },
//             "bubble": {
//                 "distance": 400,
//                 "size": 40,
//                 "duration": 2,
//                 "opacity": 8,
//                 "speed": 3
//             },
//             "repulse": {
//                 "distance": 200,
//                 "duration": 0.4
//             },
//             "push": {
//                 "particles_nb": 4
//             },
//             "remove": {
//                 "particles_nb": 2
//             }
//         }
//     },
//     "retina_detect": true
// };

