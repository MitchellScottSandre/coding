/**
 * Created by scott on 2017-08-18.
 */
$(document).ready(function(){

    // ScrollSpy
    $('.scrollspy').scrollSpy();

    // Parralax
    $('.parallax').parallax();

    // Scrollify
    // $.scrollify({
    //     section : ".scrollifyMe"
    // });

    // Title Textulate animations
    $('.tlt_1, .tlt_3').textillate();
    $('.tlt_2').textillate({
        initialDelay: 0,
        minDisplayTime: 5,
        in: { effect: 'fadeInUp', sync: true },
        out: { effect: 'fadeOutUp', sync: true},
        loop: true
    });
    // var tlt4 = $('.tlt_4').textillate({
    //     autoStart: false,
    //     initialDelay: 0
    // });

    // Load Particle JS
    particlesJS("particles-js", particleData);
    $('#descriptionWord').text("sdfsdf");

    // Toggle Nav Overlay
    $("#overlayToggler").on("click", function(){
        $(this).toggleClass('open');
        $('.tlt_4').textillate();
        // tlt4.textillate('start');
        $("body").toggleClass("bodyBlurred");

        if ($(this).attr("class").indexOf("open") != -1){
            $('#navOverlay').css({
                // "width"          :"100%",
                "display"        : "block"
            });

        } else {
            $('#navOverlay').css({
                "display": "none"
            });
        }
    });

    // Navbar Transparency
    $(document).on('scroll', function (e) {
        e.preventDefault();
        var o = $(document).scrollTop() / 500;
        // console.log(o);
        if (o > 1.000) { o = 1;}
        var e = $('nav');
        $('nav').not('a').css('background-color', 'rgba(3, 169, 244,' + o + ')');
        $('.brand-logo').css('opacity', o);
        if (o == 0){
            // $('nav').prop("class", "z-depth-0");
            $('.navLabel').css('color', 'black');
            $('#overlayToggler span').css('background', 'black');
        } else {
            // $('nav').prop("class", "z-depth-2");
            $('.navLabel').css('color', 'white');
            $('#overlayToggler span').css('background', 'white');
        }
        // $('a').css('color', 'rgb(' + colorHue + ',' + colorHue + ',' + colorHue + ')');
    });

    // Add all the skills stars for skill cards
    $('.skillStars').each(function(){
        var num = parseInt($(this).attr("data-stars"));
        for (var i = 0; i < num; i++){
            $(this).append("<i class='material-icons'>star_border</i>");
        }
    });

    $('.cd-timeline-content').mouseenter(function(){
        $(this).addClass("z-depth-5");
        $(this).siblings('.cd-timeline-img').css('background-color', '#FF5252');
    });
    $('.cd-timeline-content').mouseleave(function(){
        $(this).removeClass("z-depth-5");
        $(this).siblings('.cd-timeline-img').css('background-color', 'white');
    });
    // $('.cd-timeline-content').mouseover(function(){
    //
    // });
    // Show more div when .showMore is clicked
    $('.showMore').on("click", function(){
        $(this).closest('.showMoreSection').find('.more').slideToggle();
    });

    // Set timeline line height on load and on change
    var timelineLineHeight = $('#timelineIcon_2').offset().top - $('#timelineIcon_1').offset().top;
    handleExperienceDivSize();
    $('#cd-timeline').css('height', timelineLineHeight + "px");
    $( window ).resize(function() {
        handleExperienceDivSize();
        var timelineLineHeight2 = $('#timelineIcon_2').offset().top - $('#timelineIcon_1').offset().top;
        $('#cd-timeline').css('height', timelineLineHeight2 + "px");
    });

    function handleExperienceDivSize(){
        if ($(document).width() < 340){ //iPhone5
            $('#experience').css('height', '190vh');
        } else if ($(document).width() < 400){ // Iphone 6
            $('#experience').css('height', '110vh');
        } else if ($(document).width() < 450){ // Iphone 6 Plus
            $('#experience').css('height', '75vh');
        } else if ($(document).width() < 800){
            $('#experience').css('height', '95vh');
        } else if ($(document).width() < 1025){
            $('#experience').css('height', '72vh');
        } else {
            $('#experience').css('height', '153vh');
        }
    }


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

