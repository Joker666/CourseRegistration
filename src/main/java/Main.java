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

        LinkedList<Course> courses = rcc.getRegistration().getCourseList();

        enableCORS("*", "*", "*");
        get("/", (req, res) -> courses, json());

        post("/addCourse/:id", (request, response) -> {
            String courseId = request.params(":id");
            if(!courseId.isEmpty() && rcc.getCourse(courseId) != null) {
                rcc.addCourse(courseId);
                return rcc.getCourse(courseId);
            }

            return null;
        }, json());
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
}
