package Models;

import Discounts.*;
import Enums.DiscountPolicy;
import Factories.CourseFactory;
import Interfaces.IDiscountStrategy;
import Interfaces.IExtraFreeCalculator;
import config.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Registration {
    LinkedList<Course> courseList;
    IExtraFreeCalculator iefc;
    IDiscountStrategy discountStrategy;
    List<DiscountPolicy> _discountPolicies = new ArrayList<DiscountPolicy>();

    public Registration(){
        courseList = new LinkedList<>();
    }

    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }

    public void addDiscountPolicy(DiscountPolicy discountPolicy) {
        _discountPolicies.add(discountPolicy);
    }

    public void emptyDiscountPolicies() {
        _discountPolicies.clear();
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public int getTotal(){
        if(_discountPolicies.size() == 1) {
            switch (_discountPolicies.get(0)){
                case ACADEMICEXCELLENCE:
                    discountStrategy = new AcademicExcellenceDiscount();
                    break;
                case FREEDOMFIGHTER:
                    discountStrategy = new FreedomFighterDiscount();
                    break;
                case ABORIGINALGROUP:
                    discountStrategy = new AboriginalDiscount();
                    break;
            }
        } else {
            CompositeDiscount compositeDiscount = Configuration.getBest();

            for (DiscountPolicy discountPolicy:_discountPolicies) {
                switch (discountPolicy) {
                    case ACADEMICEXCELLENCE:
                        compositeDiscount.add(new AcademicExcellenceDiscount());
                        break;
                    case FREEDOMFIGHTER:
                        compositeDiscount.add(new FreedomFighterDiscount());
                        break;
                    case ABORIGINALGROUP:
                        compositeDiscount.add(new AboriginalDiscount());
                        break;
                }
            }
            discountStrategy = compositeDiscount;
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
