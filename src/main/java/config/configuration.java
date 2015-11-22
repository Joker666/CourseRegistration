package config;

import Adapters.BDTaxAdapter;
import Utilities.DevelopmentFreeCalculator;

public class Configuration {

    public static BDTaxAdapter getBDTaxAdapter() {
        return new BDTaxAdapter();
    }

    public static DevelopmentFreeCalculator getDevelopmentFreeCalculator() {
        return new DevelopmentFreeCalculator();
    }
}
