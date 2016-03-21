package assignment8;

/**
 * 
 * @author Aaron Settle
 */
public class PayCalculation {
    private static final double FEDERAL_EXEMPT = 55.77;
    private static final double STATE_EXEMPT = 38.46;
    private static final double SS_PERCENT = 7.65;
    
    private double calculateGrossPay(double wage, int hoursWorked){
        if(hoursWorked <= 40){
            return(wage * hoursWorked);
        }
        else{
            double base = (wage * 40);
            hoursWorked -= 40;
            return(base + ((wage * 1.5) * hoursWorked));
        }
    }
    
    private double calculateFederalTax(double grossPay, int numExemptions){
        //@param    taxEligible is your pay gross pay after accounting for exemptions
        //@return   Amount of federal tax withholding after calculating tax bracket and taxable income
        double taxEligible = grossPay - (FEDERAL_EXEMPT * numExemptions);
        
        
        //If I had more time, I could probably refactor this into a map
        if(taxEligible < 51.00){
            return 0;
        }
        else if((51 < taxEligible) && (552 > taxEligible)){
            return (taxEligible * 0.15);
        }
        else if((552 < taxEligible) && (1196 > taxEligible)){
            return(75.15 + (0.28 * (taxEligible - 552)));
        }
        else if((1196 < taxEligible) && (2662 > taxEligible)){
            return(255.47 + (0.31 * (taxEligible - 1196)));
        }
        else if((2662 < taxEligible) && (5750 > taxEligible)){
            return(709.93 + (0.36 * (taxEligible - 2662)));
        }
        else{
            return(1821.61 + (0.396 * (taxEligible - 5750)));
        }
    }
    
    private double calculateStateTax(double grossPay, int numExemptions){
        if(grossPay <= (STATE_EXEMPT * numExemptions)){
            return 0;
        }
        else{
            return(.03 * (grossPay - (STATE_EXEMPT * numExemptions)));
        }
    }
    
    private double calculateSocialSecurity(double grossPay){
        return(SS_PERCENT * grossPay);
    }
}
