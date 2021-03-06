package Utilities;

import Interfaces.IExtraFreeCalculator;

public class DevelopmentFreeCalculator implements IExtraFreeCalculator{

    public DevelopmentFreeCalculator(){}

    @Override
    public int getExtraAmount(int courseTotal) {
        return (int) Math.ceil(courseTotal * 0.1);
    }
}
