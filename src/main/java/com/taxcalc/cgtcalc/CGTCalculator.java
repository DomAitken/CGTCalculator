package com.taxcalc.cgtcalc;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
public class CGTCalculator {
    public static void main() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your figures below (Commas not required):");
        // Ask for figures. Different scanner needed for each new input?

        try {   
            System.out.println("Initial Investment: ");
            globalVars.initialInvestment = sc.nextBigDecimal();

            System.out.println("Final Sum: ");
            globalVars.finalSum = sc.nextBigDecimal();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a numerical value.");
        }

        globalVars.profit = globalVars.finalSum.subtract(globalVars.initialInvestment);
        globalVars.taxableAmount = globalVars.profit.subtract(globalVars.allowance); // £3,000 CGT allowance in the tax year 2025/26

        if (globalVars.profit.doubleValue() <= 0) {
            System.out.println("Your profit after tax is: £"+globalVars.formatter.format(globalVars.profit));
        }

        Scanner sc1 = new Scanner(System.in);
        String source = sc1.toString().toUpperCase();

        while(source != "A" && source != "B" && source != "C" && source != "D") {
            try {
                System.out.println("Choose which means you used to obtain the funds. Please answer A, B, C or D:");
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

        sc.close();
        sc1.close();
    }

    static void highOrLow(String source) {
        Scanner sc2 = new Scanner(System.in);
        String earnings = sc2.toString().toUpperCase();

        while(earnings != "A" || earnings != "B") {
            try {
                System.out.println("Do you earn more than £50,270 per annum? Please answer A or B:");
                System.out.println("A) Yes");
                System.out.println("B) No");
            } catch (Exception ex) {
                System.out.println("Invalid input. Please retry.");
                System.out.flush();
                highOrLow(source);
            }
        } 

        BigDecimal taxRate = null;

        if (earnings == "A" && source != "C") {
            taxRate = globalVars.higherRate;
        } else if (earnings == "B" && source != "C") {
            taxRate = globalVars.basicRate;
        } else if (earnings == "A" && source == "C") {
            taxRate = globalVars.higherRateProperty;
        } else if (earnings == "B" && source == "C") {
            taxRate = globalVars.basicRateProperty;
        }

        finalCalc(source, taxRate);

        sc2.close();
    }

    public static void finalCalc(String source, BigDecimal taxRate){
        highOrLow(source);

        globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(taxRate));

        System.out.println("Your profit after tax is: £"+globalVars.formatter.format(globalVars.taxedAmount));
    }

}
