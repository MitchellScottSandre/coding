/**
 * Created by scott on 2017-08-03.
 */
$(document).ready(function(){
    $("#welcomeMessage").fadeIn(1000);
    $("#dayTwoContent").hide();

    $('.navToggler').on('click', function(){
        var id = $(this).attr("href");
        if (!$(id).is(':visible') && id.indexOf("_mobile") < 0){
            var newHref = $(this).attr("href") + "_mobile";
            $(this).prop("href", newHref);
        } else if (!$(id).is(':visible') && id.indexOf("_mobile") >= 0){
            var newHref = $(this).attr("href").substring(0, id.indexOf("_mobile"));
            $(this).prop("href", newHref);
        }
        $('.navbar-collapse').collapse('hide');
    });

    $(".collapse").on("shown.bs.collapse", function(){
        $(this).siblings().find("span .fa").prop("class", "fa fa-caret-square-o-down fa-rotate-0");
    });

    $(".collapse").on("hidden.bs.collapse", function(){
        $(this).siblings().find("span .fa").prop("class", "fa fa-caret-square-o-down fa-rotate-270");
    });

    $('.collapse').collapse("hide");

    $('#donateBox').on('click', function(e){
        var txt = $(e.target).text().trim().toLowerCase();
        if (txt == "donate now"){
            return;
        }
        $(this).find('.more').slideToggle();
        if($(this).find('.showMoreText').text() == "Show More"){
            $(this).find('.showMoreText').text("Show Less");
        } else {
            $(this).find('.showMoreText').text("Show More");
        }
    });

    $('.card-block').on('click', function(e){
        //e.preventDefault();
        var txt = $(e.target).text().trim().toLowerCase();
        if (txt == "white oaks secondary school" || txt == "pinehurst conservation area" || txt == "western university"
            || txt == "the spoke" || txt == "cyclist sign-up" || txt == "volunteer sign-up"){
            return;
        }
        $(this).closest('.card-block').find('.more').slideToggle();
        if($(this).closest('.card-block').find('.showMoreText').text() == "Show More"){
            $(this).closest('.card-block').find('.showMoreText').text("Show Less");
        } else {
            $(this).closest('.card-block').find('.showMoreText').text("Show More");
        }
    });

    $("#dayOneSelector").on("click", function(){
        $(this).prop("class", "nav-link active");
        $("#dayTwoSelector").prop("class", "nav-link");
        $("#dayOneContent").show();
        $("#dayTwoContent").hide();
    });

    $("#dayTwoSelector").on("click", function(){
        $(this).prop("class", "nav-link active");
        $("#dayOneSelector").prop("class", "nav-link");
        $("#dayOneContent").hide();
        $("#dayTwoContent").show();
    });


    // Variables
    // About the Cause
    var aboutTheCause_description = "Ride for Craig is an annual fundraiser to honour the life of our friend, Craig. On September 9-10th, we will be biking 160km from Oakville to London to raise money for the Craig Sandre Soph Support Fund to provide students at Western University with mental health education and support.";
    var whyWeRide_1 = "First and foremost we are riding for Craig. Those who knew Craig knew him as a fun-loving, enthusiastic and warm friend with the biggest heart. He had an exceptional talent for making people laugh and feel at ease. We chose to have the event on September 10th weekend to celebrate Craig’s birthday. ";
    var whyWeRide_2 = "We are also riding to raise money for the Craig Sandre Soph Support Fund. This fund is dedicated towards supporting mental health for students at Western. The fund will be used to train Soph leaders at Western to recognize and help students who may be struggling with mental health issues. The goal is to foster awareness for mental health and to create a more connected, supportive and resilient community in order to help others in need. We hope you will support us in reaching our fundraising goal so that this fund can continue to support students in need for many years to come.";
    var whyWeRide_3 = "September 10th is also World Suicide Prevention Day. We’ve organized this ride as a way of honouring the many Canadians who have struggled, are struggling or will struggle with a mental illness or addiction. We know that mental illness affects 1 in 5 Canadians each year and every day 11 Canadians lose their lives to suicide. Despite these statistics, there is still not enough awareness or support for mental health. This is our way of fighting back and letting those who are struggling know they are not alone.";

    var whyWeRide_1_mobile = whyWeRide_1;
    var whyWeRide_2_mobile = whyWeRide_2;
    var whyWeRide_3_mobile = whyWeRide_3;

    // Get Involved
    var getInvolved_description = "Whether you knew Craig personally or not, we hope you will get involved to support this important cause and celebrate Craig’s birthday!";
    var cyclistText = "We know 160km sounds intimidating, but we promise it’s possible.";
    var volunteerText_1 = "To ensure a safe, fun and successful event, volunteers are needed (especially drivers)!";
    var volunteerText_2 = "Interested in helping out? Please fill out the form below!";
    var sponsorUsText = "Since all donations go directly to the Craig Sandre Soph Support Fund, we rely on sponsorships and donations to help us keep the cost of participating low for cyclists. In particular, we need:";
    var donateText = "Support our cyclists and student mental health by donating to the Craig Sandre Soph Support Fund.";

    var cyclistText_mobile = cyclistText;
    var volunteerText_1_mobile = volunteerText_1;
    var volunteerText_2_mobile = "Click below to register.";
    var sponsorUsText_mobile = "We rely on sponsorships and donations to help us keep the cost of participating low for cyclists. In particular we need:";
    var donateText_mobile = donateText;

    //What to Expect
    var preparation = "We suggest that you prepare for the ride by doing some training on your own. Practice rides or any cardio and endurance exercises will help you to prepare. That being said, don’t be intimidated by the length of the ride! Last year we went at a slow pace and everyone was able to complete it - biking 160km over two days is challenging, but doable!";
    var prepation_mobile = preparation;
    var day1_2 = "There will be a lunch break mid-day, and we expect to arrive at Pinehurst in the late afternoon or early evening";
    var day1_3 = "Once we arrive we will take a quick dip in the water to wash off, then set up our tents, prepare a campfire, and get ready for dinner";
    var day1_4 = "We’ll spend the evening relaxing, having fun, and getting some rest for Day Two";
    var day1_2_mobile = day1_2;
    var day1_3_mobile = day1_3;
    var day1_4_mobile = day1_4;


    var day2_0 = "Wake around 8:30AM to eat breakfast and pack up our campsite so that we can be on the road no later than 10:00AM";
    var day2_2 = "We’ll stop for another mid-day lunch break and before you know it we will arrive in London";
    var day2_0_mobile = day2_0;
    var day2_2_mobile = day2_2;

    var bring_0 = "$15 participation fee (payable by e-transfer or cash)";
    var bring_1 = "Water Bottle";
    var bring_2 = "Helmet and Road Bike";
    var bring_3 = "Comfortable, athletic clothing";
    var bring_4 = "Windbreaker or light jacket in case of rain";
    var bring_5 = "Extra snacks and beverages (meals and snacks will be provided)";
    var bring_6 = "Bike Shorts";
    var bring_7 = "Sleeping bag, toiletries, pajamas";

    var note_1 = "Drivers will be nearby on both days in case of bike damages or injury";
    var note_2 = "We will also take regular water and snack breaks every 10km or so";
    var note_3 = "Transportation will be arranged from London to Oakville on the Friday night and Monday morning";
    var note_4 = "Accommodations can also be arranged for Sunday night";

    var note_1_mobile = note_1;
    var note_2_mobile = note_2;
    var note_3_mobile = note_3;
    var note_4_mobile = note_4;

    var donateText_1 = "The Craig Sandre Sophs Support Fund has been established by the family of Craig Sandre, BA’16 (awarded posthumously).";
    var donateText_2 = "Donations to the fund will be used to support expenses related to offering mental health and wellness counselling services to Western Sophs throughout the academic year.";

    // We will wake around 8:30AM to eat breakfast and pack up our campsite so that we can be on the road no later than 10:00AM.
    //     We will spend most of the day biking just over 80km to Western University, but guess what? It’s all downhill on the second day! We’ll stop for another mid-day lunch break and before you know it we will arrive in London.
    //     To celebrate the ride we will be heading over to the Spoke, the campus bar and restaurant, and have a drink or two with dinner.
    // Set paragraph content
    $("#aboutTheCause_description").text(aboutTheCause_description);
    $("#whyWeRide_1").text(whyWeRide_1);
    $("#whyWeRide_2").text(whyWeRide_2);
    $("#whyWeRide_3").text(whyWeRide_3);
    $("#whyWeRide_1_mobile").text(whyWeRide_1_mobile);
    $("#whyWeRide_2_mobile").text(whyWeRide_2_mobile);
    $("#whyWeRide_3_mobile").text(whyWeRide_3_mobile);

    $('#getInvolved_description').text(getInvolved_description);
    //Broswer
    $('#cyclistText').text(cyclistText);
    $('#volunteerText_1').text(volunteerText_1);
    $('#volunteerText_2').text(volunteerText_2);
    $('#sponsorUsText').text(sponsorUsText);
    $('#donateText').text(donateText);
    //Mobile
    $('#cyclistText_mobile').text(cyclistText_mobile);
    $('#volunteerText_1_mobile').text(volunteerText_1_mobile);
    $('#volunteerText_2_mobile').text(volunteerText_2_mobile);
    $('#sponsorUsText_mobile').text(sponsorUsText_mobile);
    $('#donateText_mobile').text(donateText_mobile);

    $('#preparation').text(preparation);
    $('#prepation_mobile').text(prepation_mobile);
    $('#bring_0').text(bring_0);
    $('#bring_1').text(bring_1);
    $('#bring_2').text(bring_2);
    $('#bring_3').text(bring_3);
    $('#bring_4').text(bring_4);
    $('#bring_5').text(bring_5);
    $('#bring_6').text(bring_6);
    $('#bring_7').text(bring_7);
    $('#bring_0_mobile').text(bring_0);
    $('#bring_1_mobile').text(bring_1);
    $('#bring_2_mobile').text(bring_2);
    $('#bring_3_mobile').text(bring_3);
    $('#bring_4_mobile').text(bring_4);
    $('#bring_5_mobile').text(bring_5);
    $('#bring_6_mobile').text(bring_6);
    $('#bring_7_mobile').text(bring_7);

    $('#day1_2').text(day1_2);
    $('#day1_3').text(day1_3);
    $('#day1_4').text(day1_4);
    $('#day1_2_mobile').text(day1_2_mobile);
    $('#day1_3_mobile').text(day1_3_mobile);
    $('#day1_4_mobile').text(day1_4_mobile);
    $('#day2_0').text(day2_0);
    $('#day2_2').text(day2_2);
    $('#day2_0_mobile').text(day2_0_mobile);
    $('#day2_2_mobile').text(day2_2_mobile);

    $('#note_1').text(note_1);
    $('#note_2').text(note_2);
    $('#note_3').text(note_3);
    $('#note_4').text(note_4);
    $('#note_1_mobile').text(note_1_mobile);
    $('#note_2_mobile').text(note_2_mobile);
    $('#note_3_mobile').text(note_3_mobile);
    $('#note_4_mobile').text(note_4_mobile);

    $('#donateText_1').text(donateText_1);
    $('#donateText_2').text(donateText_2);




});