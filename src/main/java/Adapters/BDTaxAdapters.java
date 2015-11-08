package Adapters;

import Interfaces.IExtraFreeCalculator;
import Utilities.BDTaxCalculator;

public class BDTaxAdapters implements IExtraFreeCalculator{

    @Override
    public int getExtraAmount(int courseTotal) {
        return (int) BDTaxCalculator.calculateVATAmount(courseTotal);
    }
}
