package Factories;

import Interfaces.IExtraFreeCalculator;
import Models.Course;

import java.util.LinkedList;
import java.util.Objects;

public class CourseFactory {
    LinkedList<Course> cList;
    IExtraFreeCalculator efCalculator;
    private static CourseFactory instance = null;

    public CourseFactory(){
        cList = new LinkedList<>();

        Course course1 = new Course();
        course1.setId("CSE 327");
        course1.setTitle("Soft Eng");
        course1.setCredit(3);
        course1.setTuitionPerCredit(1500);


        Course course2 = new Course();
        course2.setId("CSE 326");
        course2.setTitle("Compiler Construction");
        course2.setCredit(3);
        course2.setTuitionPerCredit(1500);


        Course course3 = new Course();
        course3.setId("CSE 323");
        course3.setTitle("Operating System");
        course3.setCredit(3);
        course3.setTuitionPerCredit(1500);


        Course course4 = new Course();
        course4.setId("CSE 325");
        course4.setTitle("Programming Language Principles");
        course4.setCredit(3);
        course4.setTuitionPerCredit(1500);


        cList.add(course1);
        cList.add(course2);
        cList.add(course3);
        cList.add(course4);
    }

    public Course getCourse(String id){
        for(Course course:cList){
            if(Objects.equals(course.getId(), id)){
                return course;
            }
        }
        return null;
    }

    public static synchronized CourseFactory getInstance() {
        if(instance == null) {
            instance = new CourseFactory();
        }
        return instance;
    }

    public IExtraFreeCalculator getExtraFeeCalculator(){
        if (efCalculator == null){
            String className = System.getProperty(IExtraFreeCalculator.class.getName());
            try {
                efCalculator = (IExtraFreeCalculator) Class.forName(className).newInstance();
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return efCalculator;
    }
}
