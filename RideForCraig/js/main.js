/**
 * Created by scott on 2017-08-03.
 */
$(document).ready(function(){

    $("#collapseOne, #collapseTwo, #collapseThree").on("show.bs.collapse", function(){
        $(this).siblings().find("span .fa").prop("class", "fa fa-caret-square-o-down fa-rotate-0");
    });

    $("#collapseOne, #collapseTwo, #collapseThree").on("hide.bs.collapse", function(){
        $(this).siblings().find("span .fa").prop("class", "fa fa-caret-square-o-down fa-rotate-270");
    });

    $('.card-block').on('click', function(e){
        e.preventDefault();
        $(this).closest('.card-block').find('.more').slideToggle();
        if($(this).closest('.card-block').find('.text-info').text() == "Show More"){
            $(this).closest('.card-block').find('.text-info').text("Show Less");
        } else {
            $(this).closest('.card-block').find('.text-info').text("Show More");
        }
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
    var volunteerText_2_mobile = volunteerText_2;
    var sponsorUsText_mobile = sponsorUsText;
    var donateText_mobile = donateText;

    // OnLoad Page Actions
    $("#welcomeMessage").fadeIn(1000);
    $('#collapseOne').collapse("hide");
    $('#collapseTwo').collapse("hide");
    $('#collapseThree').collapse("hide");

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
    //Mobiles
    $('#cyclistText_mobile').text(cyclistText_mobile);
    $('#volunteerText_1_mobile').text(volunteerText_1_mobile);
    $('#volunteerText_2_mobile').text(volunteerText_2_mobile);
    $('#sponsorUsText_mobile').text(sponsorUsText_mobile);
    $('#donateText_mobile').text(donateText_mobile);

});