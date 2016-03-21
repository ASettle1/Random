package assignment8;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Aaron Settle
 */
public class Assignment8 {

    public static double totalGrossPay = 0;
    public static double totalNetPay = 0;
    public static boolean repeat = true;
    public static int weekCounter = 1;
    public static Scanner scan = new Scanner(System.in);
    private static double[] payValues = new double[5];
    private static String[] totalValues = new String[7];
    private static PayCalculation pay = new PayCalculation();

    public static void main(String[] args) {
        while (repeat) {
            System.out.println("Would you like to calculate your weekly pay? Press Y (Yes) or N (No).");
            String input = scan.next();
            input = input.toUpperCase();
            switch (input) {
                case "Y":
                    repeat = false;
                    /**
                     * @param payValues: Store values entered by user to be used for the next set
                     * of calculations include gross pay and net pay.
                     */
                    payValues[0] = validInt("Enter hours worked this week", 0, 60);
                    payValues[1] = validDouble("Enter hourly wage", 0.00, 30.00);
                    payValues[2] = validInt("Enter number of exemptions", 0, 5);
                    payValues[3] = pay.calculateGrossPay(payValues[1], (int) payValues[0]);
                    payValues[4] = (payValues[3] - (pay.calculateFederalTax(payValues[3], (int) payValues[2]) + pay.calculateStateTax(payValues[3], (int) payValues[2]) + pay.calculateSocialSecurity(payValues[3])));

                    /** 
                     * Store total amount of pay of both types based on all user inputs in a session.
                     */
                    totalGrossPay += payValues[3];
                    totalNetPay += payValues[4];
                    
                    /**
                     * @param totalValues - stores all calculation results to be displayed.
                     * Perform calculations on entered values and store the results in order of:
                     * Week Number, Hours, Gross Pay, Federal Tax, State Tax, Social Security, Net Pay
                     */
                    totalValues[0] = String.format("Week %d:", weekCounter);
                    totalValues[1] = String.format("You worked %.2f hours this week at $%.2f per hour.", payValues[0], payValues[1]);
                    totalValues[2] = String.format("Gross Pay: %.2f", payValues[3]);
                    totalValues[3] = String.format("Federal Tax: $%.2f", pay.calculateFederalTax(payValues[3], (int) payValues[2]));
                    totalValues[4] = String.format("State Tax: $%.2f", pay.calculateStateTax(payValues[3], (int) payValues[2]));
                    totalValues[5] = String.format("Social Security: $%.2f", pay.calculateSocialSecurity(payValues[3]));
                    totalValues[6] = String.format("Net Pay: $%.2f", payValues[4]);
                    weekCounter++;

                    for (String s : totalValues) {
                        System.out.println(s);
                    }

                    repeat = true;
                    break;
                case "N":
                    if (weekCounter > 1) {
                        System.out.printf("Total Gross pay: $%.2f\n", totalGrossPay);
                        System.out.printf("Total Net pay: $%.2f\n", totalNetPay);
                        barGraph(weekCounter);
                        System.exit(0);
                    } else {
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static int validInt(String prompt, int lowerBound, int upperBound) {
        for (;;) {
            System.out.printf("%s (%d-%d)\n", prompt, lowerBound, upperBound);
            try {
                int input = scan.nextInt();
                if (input >= lowerBound && input <= upperBound) {
                    return input;
                } else {
                    System.out.println("Input was not within bounds. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input was not a number. Please try again.");
                scan.next();
            }
        }
    }

    public static double validDouble(String prompt, double lowerBound, double upperBound) {
        for (;;) {
            System.out.printf("%s (%.2f-%.2f)\n", prompt, lowerBound, upperBound);
            try {
                double input = scan.nextDouble();
                if (input >= lowerBound && input <= upperBound) {
                    return input;
                } else {
                    System.out.println("Input was not within bounds. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input was not a number. Please try again.");
                scan.next();
            }
        }
    }

    public static void barGraph(int numWeeks) {
        String stars = "";
        for (int i = 1; i < numWeeks; i++) {
            stars += "*";
        }
        System.out.printf("%s : %s\n", "Amount of pay weeks calculated", stars);
    }
}
