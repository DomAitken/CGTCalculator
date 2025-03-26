package com.taxcalc.cgtcalc;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CGTCalculator {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your figures below (Commas not required):");

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

        String source;
        do {
            System.out.println("Choose which means you used to obtain the funds. Please answer A, B, C, or D:");
            System.out.println("A) Crypto");
            System.out.println("B) Stocks and Shares");
            System.out.println("C) Real Estate");
            System.out.println("D) Other");
        
            source = sc.next().toUpperCase(); // Read input and convert to uppercase
        
            if (!source.equals("A") && !source.equals("B") && !source.equals("C") && !source.equals("D")) {
                System.out.println("Invalid input. Please enter A, B, C, or D.");
            }
        } while (!source.equals("A") && !source.equals("B") && !source.equals("C") && !source.equals("D"));

        highOrLow(source, sc);

        sc.close();

    }

    static void highOrLow(String source, Scanner sc) {
        String earnings;

        do {
            System.out.println("Is your overall household income (including capital gains) more than £50,270 per annum? Please answer A or B:");
            System.out.println("A) Yes");
            System.out.println("B) No");
            earnings = sc.next().toUpperCase();
            
            if (!earnings.equals("A") && !earnings.equals("B")) {
                System.out.println("Invalid input. Please retry.");
            }
        } while (!earnings.equals("A") && !earnings.equals("B"));

        BigDecimal taxRate = null;

        if (earnings.equals("A") && !source.equals("C")) {
            taxRate = globalVars.higherRate;
        } else if (earnings.equals("B") && !source.equals("C")) {
            taxRate = globalVars.basicRate;
        } else if (earnings.equals("A") && source.equals("C")) {
            taxRate = globalVars.higherRateProperty;
        } else if (earnings.equals("B") && source.equals("C")) {
            taxRate = globalVars.basicRateProperty;
        }

        if (taxRate == null) {
            System.out.println("Error calculating tax. Please check your inputs.");
            sc.close();
            return;
        }

        finalCalc(taxRate);

    }

    public static void finalCalc(BigDecimal taxRate){


        globalVars.taxedAmount = globalVars.taxableAmount.subtract(globalVars.taxableAmount.multiply(taxRate));

        System.out.println("Your profit after tax is: £"+globalVars.formatter.format(globalVars.taxedAmount));
    }
}
