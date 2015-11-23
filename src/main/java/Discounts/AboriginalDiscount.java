package Discounts;

import Interfaces.IDiscountStrategy;
import Models.Registration;

public class AboriginalDiscount implements IDiscountStrategy {
    @Override
    public int getTotal(Registration registration) {
        return (int) (registration.getTotalWithoutDiscount() * (1 - 0.6));
    }
}
