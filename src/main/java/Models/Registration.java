package Models;

import Factories.CourseFactory;
import Interfaces.IExtraFreeCalculator;

import java.util.LinkedList;

public class Registration {
    LinkedList<Course> courseList;
    IExtraFreeCalculator iefc;

    public Registration(){
        courseList = new LinkedList<>();
    }

    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public int getTotal(){
        int total = 0;
        for(Course course:courseList){
            total += course.getSubTotal();
        }
        return total;
    }

    public int getExtraFeeAmount() {
        iefc = CourseFactory.getInstance().getExtraFeeCalculator();
        int total = this.getTotal();
        return iefc.getExtraAmount(total);
    }

    public int getGrandTotal() {
        return this.getTotal() + this.getExtraFeeAmount();
    }
}
