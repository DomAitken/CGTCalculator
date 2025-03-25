package main.java.com.taxcalc.cgtcalc;

import java.math.BigDecimal;
import java.util.Scanner;
import main.java.com.taxcalc.cgtcalc.globalVars;

// Driver class
public class CGTCalculator {
    // main function
    public static void main() {

        // Take input from the user
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your figures below:");

        // Take the inputs
        globalVars.initialInvestment = sc.nextBigDecimal();
        globalVars.finalSum = sc.nextBigDecimal();
        globalVars.profit = globalVars.finalSum.subtract(globalVars.initialInvestment);
        globalVars.taxableAmount = globalVars.profit.subtract(globalVars.allowance); // £3,000 CGT allowance in the tax year 2025/26

        Scanner sc1 = new Scanner(System.in);
        String source = sc1.toString();

        while(source != "A" && source != "B" && source != "C" && source != "D") {
            try {
                System.out.println("Choose which means you used to obtain the funds:");
                System.out.println("A) Crypto");
                System.out.println("B) Stocks and Shares");
                System.out.println("C) Real Estate");
                System.out.println("D) Other");
            } catch (Exception ex) {
                System.out.println("Invalid input. Please retry.");
                System.out.flush();
            }
        }

        highOrLow(source);

    }

    static void highOrLow(String source) {
        Scanner sc2 = new Scanner(System.in);
        String taxRate = sc2.toString();

        while(taxRate != "A" || taxRate != "B") {
            try {
                System.out.println("Do you earn more than £50,270 per annum?");
                System.out.println("A) Yes");
                System.out.println("B) No");
            } catch (Exception ex) {
                System.out.println("Invalid input. Please retry.");
                System.out.flush();
                highOrLow(source);
            }
        }
    }

    public static void cryptoCalc(String source, String taxRate){
        highOrLow(source);

        if (taxRate == "A" || taxRate == "a"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.higherRateCrypto));
        }
        else if (taxRate == "B" || taxRate == "b"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.basicRateCrypto));
        }

        System.out.println("Your profit after tax is: £"+globalVars.taxedAmount);
    }

    public static void stocksCalc(String source, String taxRate){
        highOrLow(source);

        if (taxRate == "A" || taxRate == "a"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.higherRateStocks));
        }
        else if (taxRate == "B" || taxRate == "b"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.basicRateStocks));
        }

        System.out.println("Your profit after tax is: £"+globalVars.taxedAmount);
    }

    public static void propertyCalc(String source, String taxRate){
        highOrLow(source);

        if (taxRate == "A" || taxRate == "a"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.higherRateProperty));
        }
        else if (taxRate == "B" || taxRate == "b"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.basicRateProperty));
        }

        System.out.println("Your profit after tax is: £"+globalVars.taxedAmount);
    }

    public static void otherCalc(String source, String taxRate){
        highOrLow(source);

        if (taxRate == "A" || taxRate == "a"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.higherRateOther));
        }
        else if (taxRate == "B" || taxRate == "b"){
            globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(globalVars.basicRateOther));
        }

        System.out.println("Your profit after tax is: £"+globalVars.taxedAmount);
    }
}
