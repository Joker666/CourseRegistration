package Controllers;

import Factories.CourseFactory;
import Models.Course;
import Models.Registration;

public class RegistrationCourseController {

    Registration registration;

    public RegistrationCourseController(){
        makeNewRegistration();
    }

    public Course getCourse(String id){
        return CourseFactory.getInstance().getCourse(id);
    }

    public Registration getRegistration(){
        return registration;
    }

    public void addCourse(String id){
        registration.addCourse(CourseFactory.getInstance().getCourse(id));
    }

    public void makeNewRegistration(){
        registration = new Registration();
    }
}
