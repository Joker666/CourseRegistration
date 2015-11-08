$(document).ready(function(){
    var total = 0;
    $("#new").click(function(){
        $("input").val("");
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
                "<td>" + course.tuitionPerCredit + "</td>" +
                "<td>" + course.subTotal + "</td>" +
                "</tr>");
        });

        $("#total").html("Total : " + total);
    });

    $("#add").click(function(){
        var courseId = $("input").val();
        $.post( "http://localhost:4567/addCourse/" + courseId)
            .done(function( data ) {
                if(data != null) {
                    var c = JSON.parse(data);

                    total += c.subTotal;
                    $("#total").html("Total : " + total);

                    $("table").find('tbody')
                        .prepend("<tr>" +
                        "<td>" + c.id + "</td>" +
                        "<td>" + c.title + "</td>" +
                        "<td>" + c.credit + "</td>" +
                        "<td>" + c.tuitionPerCredit + "</td>" +
                        "<td>" + c.subTotal + "</td>" +
                        "</tr>");
                } else {
                    $("#add").after("<h3>Please Enter Valid Course Id</h3>");
                }
            });
    });
});
