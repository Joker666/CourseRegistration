package Models;

import Discounts.*;
import Enums.DiscountPolicy;
import Factories.CourseFactory;
import Interfaces.IDiscountStrategy;
import Interfaces.IExtraFreeCalculator;

import java.util.LinkedList;
import java.util.Objects;

public class Registration {
    LinkedList<Course> courseList;
    IExtraFreeCalculator iefc;
    IDiscountStrategy discountStrategy;
    DiscountPolicy _discountPolicy;

    public Registration(){
        courseList = new LinkedList<>();
    }

    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        _discountPolicy = discountPolicy;
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public int getTotal(){
        switch (_discountPolicy){
            case ACADEMICEXCELLENCE:
                discountStrategy = new AcademicExcellenceDiscount();
                break;
            case FREEDOMFIGHTER:
                discountStrategy = new FreedomFighterDiscount();
                break;
            case ABORIGINALGROUP:
                discountStrategy = new AboriginalDiscount();
//                CompositeDiscount compositeDiscount = new BestForNSU();
//                compositeDiscount.add(new AcademicExcellenceDiscount());
//                compositeDiscount.add(new FreedomFighterDiscount());
//                compositeDiscount.add(new AboriginalDiscount());
//                discountStrategy = compositeDiscount;
                break;
        }

        return discountStrategy.getTotal(this);
    }

    public int getTotalWithoutDiscount(){
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

    public Course getCourseFromRegisteredCoursesById(String id) {
        for(Course course:courseList){
            if(Objects.equals(course.getId(), id)){
                return course;
            }
        }
        return null;
    }

    public double getCGPA() {
        double totalGPA = 0;
        for(Course course:courseList){
            totalGPA += course.getGPA();
        }
        return totalGPA/courseList.size();
    }
}
