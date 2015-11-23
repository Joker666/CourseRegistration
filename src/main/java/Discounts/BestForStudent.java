package Discounts;

import Interfaces.IDiscountStrategy;
import Models.Registration;

public class BestForStudent extends CompositeDiscount {
    @Override
    public int getTotal(Registration registration) {
        int least = super.getList().get(0).getTotal(registration);
        for(IDiscountStrategy discountStrategy:super.getList()){
            if(discountStrategy.getTotal(registration) < least) {
                least = discountStrategy.getTotal(registration);
            }
        }
        return least;
    }
}
