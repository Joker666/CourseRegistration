package Discounts;

import Interfaces.IDiscountStrategy;
import Models.Registration;

public class AcademicExcellenceDiscount implements IDiscountStrategy{

    @Override
    public int getTotal(Registration registration) {
        if(registration.getCGPA() >= 3.75 ) {
            return (int) (registration.getTotalWithoutDiscount() * 0.5);
        }
        return  registration.getTotalWithoutDiscount();
    }
}
