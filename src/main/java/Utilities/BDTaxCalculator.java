package Utilities;

public class BDTaxCalculator {
    public float calculateVATAmount(int total) {
        return (float) Math.ceil(total * 0.15);
    }
}
