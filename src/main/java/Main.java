import Controllers.RegistrationCourseController;
import Models.Course;

import java.util.LinkedList;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args){

        RegistrationCourseController rcc = new RegistrationCourseController();
        rcc.addCourse("CSE 326");
        rcc.addCourse("CSE 327");
        rcc.addCourse("CSE 323");

        int total = rcc.getRegistration().getTotal();
//        LinkedList<Course> courses = rcc.getRegistration().getCourseList();
//
//        String s = courses.getLast().getTitle();

        get("/", (req, res) -> total);
    }
}
