package Discounts;

import Interfaces.IDiscountStrategy;
import Models.Registration;

public class BestForNSU extends CompositeDiscount {
    @Override
    public int getTotal(Registration registration) {
        int highest = super.getList().get(0).getTotal(registration);
        for(IDiscountStrategy discountStrategy:super.getList()){
            if(discountStrategy.getTotal(registration) > highest) {
                highest = discountStrategy.getTotal(registration);
            }
        }
        return highest;
    }
}
