/**
 * Created by scott on 2017-08-18.
 */
$(document).ready(function(){
    var overlayBackgroundBlur = "5px";

    console.log("hello");
    // $('.parallax').parallax();

    $("#overlayToggler").on("click", function(){
        $(this).toggleClass('open');
        $("body").toggleClass("bodyBlurred");

        if ($(this).attr("class").indexOf("open") != -1){
            $('#navOverlay').css({
                "width"          :"100%",
                "display"        : "block"
            });
        } else {
            $('#navOverlay').css({
                "width":"0",
                "display": "none"
            });
        }
    });

    // Show Side Nav
    // $('.button-collapse').sideNav({
    //     menuWidth: 300,
    //     edge: 'left',
    //     closeOnClick: true,
    //     draggable: true,
    //     onOpen: function () {},
    //     onClose: function () {}
    // });

    // ScrollSpy
    $('.scrollspy').scrollSpy({
        scrollOffset:100
    });

    // Load Particle JS
    //particlesJS("particles-js", particleData);


});

var particleData = {"particles": {
    "number": {
        "value": 88,
        "density": {
            "enable": true,
            "value_area": 700
        }
    },
    "color": {
        "value": ["#aa73ff", "#f8c210", "#83d238", "#33b1f8"]
    },
    "shape": {
        "type": "circle",
        "stroke": {
            "width": 0,
            "color": "#000000"
        },
        "polygon": {
            "nb_sides": 15
        }
    },
    "opacity": {
        "value": 0.5,
        "random": false,
        "anim": {
            "enable": false,
            "speed": 1.5,
            "opacity_min": 0.15,
            "sync": false
        }
    },
    "size": {
        "value": 2.5,
        "random": false,
        "anim": {
            "enable": true,
            "speed": 2,
            "size_min": 0.15,
            "sync": false
        }
    },
    "line_linked": {
        "enable": true,
        "distance": 110,
        "color": "#33b1f8",
        "opacity": 0.25,
        "width": 1
    },
    "move": {
        "enable": true,
        "speed": 1.6,
        "direction": "none",
        "random": false,
        "straight": false,
        "out_mode": "out",
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
                "enable": false,
                "mode": "repulse"
            },
            "onclick": {
                "enable": false,
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
                "distance": 200,
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

