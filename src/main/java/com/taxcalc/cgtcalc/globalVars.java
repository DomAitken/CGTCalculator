package main.java.com.taxcalc.cgtcalc;

import java.math.BigDecimal;

public class globalVars {
    public static BigDecimal initialInvestment, finalSum, profit, taxableAmount, taxedAmount;
    public static BigDecimal allowance = new BigDecimal(3000);
    public static BigDecimal higherRateStocks, higherRateCrypto, higherRateOther = new BigDecimal(0.2);
    public static BigDecimal basicRateStocks, basicRateCrypto, basicRateOther = new BigDecimal(0.1);
    public static BigDecimal higherRateProperty = new BigDecimal(0.24);
    public static BigDecimal basicRateProperty = new BigDecimal(0.18);
}
