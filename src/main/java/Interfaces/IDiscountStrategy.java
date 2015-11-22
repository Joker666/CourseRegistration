package Interfaces;

import Models.Registration;

public interface IDiscountStrategy {
    int getTotal(Registration registration);
}
