import Controllers.RegistrationCourseController;
import Models.Course;
import spark.Filter;
import spark.Request;
import spark.Response;

import java.util.LinkedList;

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
            if(!courseId.isEmpty() && rcc.getCourse(courseId) != null) {
                if(rcc.getRegistration().getCourseFromRegisteredCoursesById(courseId) == null) {
                    rcc.addCourse(courseId);
                    return rcc.getCourse(courseId);
                } else {
                    throw new CourseAlreadyAddedException();
                }
            }

            throw new NotFoundException();
        }, json());



        exception(NotFoundException.class, (e, request, response) -> {
            response.status(404);
            response.body("Resource not found!");
        });

        exception(CourseAlreadyAddedException.class, (e, request, response) -> {
            response.status(404);
            response.body("Course Already Added!");
        });

    }

    private static void enableCORS(final String origin, final String methods, final String headers) {
        before(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                response.header("Access-Control-Allow-Origin", origin);
                response.header("Access-Control-Request-Method", methods);
                response.header("Access-Control-Allow-Headers", headers);
            }
        });
    }

    private static class NotFoundException extends Exception { }

    private static class CourseAlreadyAddedException extends Exception { }
}
