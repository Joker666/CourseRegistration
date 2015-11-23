$(document).ready(function(){
    var total = 0;
    var self = this;

    $("#new").click(function(){
        $("input").val("");
    });

    $("#calculateDiscount").click(function(){
        updateDiscount().then(function () {
            updateTaxOrFee().then(function() {
                updateGrandTotal().then(function () {
                    playSound();
                });
            });
        });
    });

    $.get( "http://localhost:4567/", function( data ) {
        var courses = JSON.parse(data);

        courses.forEach(function (course) {
            total += course.subTotal;
            $("table").find('tbody')
                .prepend("<tr>" +
                "<td>" + course.id + "</td>" +
                "<td>" + course.title + "</td>" +
                "<td>" + course.credit + "</td>" +
                "<td>" + course.GPA + "</td>" +
                "<td>" + course.tuitionPerCredit + "</td>" +
                "<td>" + course.subTotal + "</td>" +
                "</tr>");
        });

        $("#total").html("Total : " + total);
    });

    updateDiscount().then(function () {
        updateTaxOrFee().then(function() {
            updateGrandTotal();
        });
    });

    $("#add").click(function(){
        var courseId = $("input").val();
        $.post( "http://localhost:4567/addCourse/" + courseId)
            .then(function( data ) {
                if(data != null) {
                    var c = JSON.parse(data);

                    total += c.subTotal;
                    $("#total").html("Total : " + total);

                    $("table").find('tbody')
                        .prepend("<tr>" +
                        "<td>" + c.id + "</td>" +
                        "<td>" + c.title + "</td>" +
                        "<td>" + c.credit + "</td>" +
                        "<td>" + c.GPA + "</td>" +
                        "<td>" + c.tuitionPerCredit + "</td>" +
                        "<td>" + c.subTotal + "</td>" +
                        "</tr>");

                } else {
                    $("#add").after("<h3>Please Enter Valid Course Id</h3>");
                }
            }).then(function () {
                updateGrandTotal();
            }).then(function () {
                updateTaxOrFee();
            });
    });

    function playSound() {
        var sound = document.getElementById("audio");
        sound.play();
    }

    function updateDiscount() {
        return $.post( "http://localhost:4567/calculateDiscount/", { discountPolicy: $("select").val()}).then(function (data) {
            var discount = JSON.parse(data);
            $("#discount").html("Discount : " + discount);
        });
    }

    function updateGrandTotal() {
        return $.get( "http://localhost:4567/grandTotal/", function( data ) {
            var grandTotal = JSON.parse(data);
            $("#grandTotal").html("Grand Total : " + grandTotal);
        });
    }

    function updateTaxOrFee() {
        return $.get( "http://localhost:4567/getTaxOrFee/", function( data ) {
            var fee = JSON.parse(data);
            $("#fee").html("Development Fee/ BD Tax : " + fee);
        });
    }
});
