package config;

import Adapters.BDTaxAdapter;
import Discounts.BestForNSU;
import Discounts.BestForStudent;
import Discounts.CompositeDiscount;
import Interfaces.IDiscountStrategy;
import Interfaces.IExtraFreeCalculator;
import Utilities.DevelopmentFreeCalculator;

public class Configuration {

    // Selected option

    public static IExtraFreeCalculator getExtraFee() {
        return getBDTaxAdapter();
    }

    public static CompositeDiscount getBest() {
        return getBestForStudent();
    }



    // All Options

    private static CompositeDiscount getBestForNSU() {
        return new BestForNSU();
    }

    private static CompositeDiscount getBestForStudent() {
        return new BestForStudent();
    }

    private static BDTaxAdapter getBDTaxAdapter() {
        return new BDTaxAdapter();
    }

    private static DevelopmentFreeCalculator getDevelopmentFreeCalculator() {
        return new DevelopmentFreeCalculator();
    }
}
