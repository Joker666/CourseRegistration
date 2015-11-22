package Adapters;

import Interfaces.IExtraFreeCalculator;
import Utilities.BDTaxCalculator;

public class BDTaxAdapter extends BDTaxCalculator implements IExtraFreeCalculator{

    public BDTaxAdapter(){}

    @Override
    public int getExtraAmount(int courseTotal) {
        return (int) super.calculateVATAmount(courseTotal);
    }
}
