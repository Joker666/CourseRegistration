package Discounts;

import Interfaces.IDiscountStrategy;
import Models.Registration;

import java.util.ArrayList;
import java.util.List;

public class CompositeDiscount implements IDiscountStrategy {
    List<IDiscountStrategy> discountStrategies = new ArrayList<IDiscountStrategy>();;

    public int getTotal(Registration registration) {
        return registration.getTotalWithoutDiscount();
    }

    public void add(IDiscountStrategy discountStrategy) {
        discountStrategies.add(discountStrategy);
    }

    public List<IDiscountStrategy> getList() {
        return discountStrategies;
    }
}
