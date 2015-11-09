package Adapters;

import Interfaces.IExtraFreeCalculator;
import Utilities.BDTaxCalculator;

public class BDTaxAdapter implements IExtraFreeCalculator{

    public BDTaxAdapter(){}

    @Override
    public int getExtraAmount(int courseTotal) {
        return (int) BDTaxCalculator.calculateVATAmount(courseTotal);
    }
}
