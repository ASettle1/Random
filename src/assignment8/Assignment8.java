package assignment8;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment8 {
    
    public static boolean repeat = true;
    public static int weekCounter = 0;
    public static Scanner scan = new Scanner(System.in);
    PayCalculation pay = new PayCalculation();
    public int[] values = new int[4];
    
    public static void main(String[] args) {
        System.out.println("Would you like to calculate your pay for a week? Press Y (Yes) or N (No).");
        while(repeat){
                String input = scan.next();
                input = input.toUpperCase();
                switch (input) {
                    case "Y":
                        repeat = false;
                        break;
                    case "N":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please enter Y (Yes) to continue or N (No) to quit.");
                        break;
                }
        }
    }
    
    public static int validInt(String prompt, int lowerBound, int upperBound){
        for(;;){
            System.out.printf("%s (%d-%d)\n", prompt, lowerBound, upperBound);
            try
            {
                int input = scan.nextInt();
                if(input >= lowerBound && input <= upperBound){
                    return input;
                }
                else{
                    System.out.println("Input was not within bounds. Please try again.");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Input was not a number. Please try again.");
                scan.next();
            }
        }
    }
    
    public static double validDouble(String prompt, double lowerBound, double upperBound){
        for(;;){
            System.out.printf("%s (%.2f-%.2f)\n", prompt, lowerBound, upperBound);
            try
            {
                double input = scan.nextDouble();
                if(input >= lowerBound && input <= upperBound){
                    return input;
                }
                else{
                    System.out.println("Input was not within bounds. Please try again.");
                }
            }
            catch(InputMismatchException e){
               System.out.println("Input was not a number. Please try again.");
               scan.next();
            }
        }
    }
    
    public void barGraph(int numWeeks){
        String stars = "*";
        for(int i = 0; i < weekCounter; i++){
            stars += "*";
        }
        System.out.println("Amount of weeks calculated: " + stars);
    }
}
