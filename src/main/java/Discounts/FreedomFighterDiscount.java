package Discounts;

import Interfaces.IDiscountStrategy;
import Models.Registration;

public class FreedomFighterDiscount implements IDiscountStrategy {
    @Override
    public int getTotal(Registration registration) {
        return registration.getTotalWithoutDiscount() - 20000;
    }
}
