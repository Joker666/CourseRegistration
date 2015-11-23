import Controllers.RegistrationCourseController;
import Enums.DiscountPolicy;
import Utilities.JsonUtil;

import static Utilities.JsonUtil.json;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args){

        RegistrationCourseController rcc = new RegistrationCourseController();
        rcc.addCourse("CSE 326");
        rcc.addCourse("CSE 327");
        rcc.addCourse("CSE 323");

        enableCORS("*", "*", "*");
        get("/", (req, res) -> rcc.getRegistration().getCourseList(), json());

        get("/grandTotal/", (req, res) -> rcc.getRegistration().getGrandTotal(), json());

        get("/getTaxOrFee/", (req, res) -> rcc.getRegistration().getExtraFeeAmount(), json());

        post("/addCourse/:id", (request, response) -> {
            String courseId = request.params(":id");

            // Added course has to exist in courseFactory
            if(!courseId.isEmpty() && rcc.getCourse(courseId) != null) {

                // Cannot add duplicate course
                if(rcc.getRegistration().getCourseFromRegisteredCoursesById(courseId) == null) {

                    // Registration courses cannot exceed five
                    if(rcc.getRegistration().getCourseList().size() <= 4){
                        rcc.addCourse(courseId);
                        return rcc.getCourse(courseId);
                    } else {
                        throw new YouCanOnlyTakeFiveCourses();
                    }
                } else {
                    throw new CourseAlreadyAddedException();
                }
            }

            throw new NotFoundException();
        }, json());

        post("/calculateDiscount/", (request, response) -> {
            String req = request.body();
            String discountPolicy = req.split("=")[1];
            rcc.getRegistration().setDiscountPolicy(DiscountPolicy.valueOf(discountPolicy));

            return rcc.getRegistration().getTotalWithoutDiscount() - rcc.getRegistration().getTotal();
        });



        exception(NotFoundException.class, (e, request, response) -> {
            response.status(404);
            response.body("Resource not found!");
        });

        exception(YouCanOnlyTakeFiveCourses.class, (e, request, response) -> {
            response.status(404);
            response.body("You Can Only Take Five Courses!");
        });

        exception(CourseAlreadyAddedException.class, (e, request, response) -> {
            response.status(404);
            response.body("Course Already Added!");
        });

    }

    private static void enableCORS(final String origin, final String methods, final String headers) {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
        });
    }

    private static class NotFoundException extends Exception { }

    private static class CourseAlreadyAddedException extends Exception { }

    private static class YouCanOnlyTakeFiveCourses extends Exception {
    }
}
