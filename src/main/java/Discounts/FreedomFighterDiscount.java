package Discounts;

import Interfaces.IDiscountStrategy;
import Models.Registration;

public class FreedomFighterDiscount implements IDiscountStrategy {
    @Override
    public int getTotal(Registration registration) {
        if(registration.getTotalWithoutDiscount() > 20000) {
            return registration.getTotalWithoutDiscount() - 20000;
        } else {
            return registration.getTotalWithoutDiscount();
        }
    }
}
