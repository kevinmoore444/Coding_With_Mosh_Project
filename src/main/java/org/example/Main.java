package org.example;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
         printPaymentSchedule();


        //Display Payment Schedule - My Version
//        int principal = (int) readNumber("Principal:", 1000, 1_000_000);
//        //Enter Interest until break
//        float annualInterest = (float) readNumber("Enter Annual Interest Rate (0=30%): ", 1, 30);
//        //Enter Period until break
//        byte years = (byte) readNumber("Enter Period (0-30 years): ", 1, 30);
//        displayPaymentSchedule(principal, annualInterest, years);

    }

    //Read inputs to calculate mortgage
    public static double readNumber(String prompt, double min, double max){
        Scanner myScanner = new Scanner(System.in);
        double value;
        while(true){
            System.out.println(prompt);
            value = myScanner.nextFloat();
            if(value>= min && value <=max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    //Calculate mortgage method
    public static double calculateMortgage(int principal, float annualInterest, byte years){
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        double mortgage = principal
                * (monthlyInterest * Math.pow(1+monthlyInterest, numberOfPayments))
                / (Math.pow(1+monthlyInterest, numberOfPayments));
        return mortgage;
    }



    //Mosh Version
    public static double calculateBalance(int principal, float annualInterest, byte years, short numberOfPaymentsMade) {
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1+monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }



    //Calculate Remaining Balance - My version
    public static void displayPaymentSchedule(int principal, float annualInterest, byte years){
        //declare variables
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);
        short numberOfPaymentsMade = 0;
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        //Calculate balance
        while(true) {
            double balance = principal
                    * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1+monthlyInterest, numberOfPaymentsMade))
                    / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
            if(balance < 0)
                break;
            String formattedBalance = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println("After " + numberOfPaymentsMade + " payments the current balance is: " + formattedBalance);
            numberOfPaymentsMade++;
        }
    }

    public static void printPaymentSchedule(){
        //Enter Principal until break.
        int principal = (int) readNumber("Principal:", 1000, 1_000_000);
        //Enter Interest until break
        float annualInterest = (float) readNumber("Enter Annual Interest Rate (0=30%): ", 1, 30);
        //Enter Period until break
        byte years = (byte) readNumber("Enter Period (0-30 years): ", 1, 30);

        //Call calculate mortgage method
        double mortgage = calculateMortgage(principal, annualInterest, years);
        String formattedMortgage = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Monthly mortgage payment: " + formattedMortgage);
        System.out.println();
        System.out.println("Mortgage");
        System.out.println("-------");
        System.out.println("Monthly Payments " + formattedMortgage);

        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("-------");
        for(short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

}



