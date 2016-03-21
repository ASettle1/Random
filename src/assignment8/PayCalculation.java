package assignment8;

/**
 * 
 * @author Aaron Settle
 */
public class PayCalculation {
    private static final double FEDERAL_EXEMPT = 55.77;
    private static final double STATE_EXEMPT = 38.46;
    private static final double SS_PERCENT = .0765;
    
    protected double calculateGrossPay(double wage, int hoursWorked){
        /** Calculate Gross Pay
         *  If the total amount of hours worked is less than 40, wage is gross pay is wage * hours.
         *  If hours worked exceeds 40, overtime of (wage * 1.5) is applied to all hours over 40 along with base pay of (wage * 40).
         */
        if(hoursWorked <= 40){
            return(wage * hoursWorked);
        }
        else{
            double base = (wage * 40);
            hoursWorked -= 40;
            return(base + ((wage * 1.5) * hoursWorked));
        }
    }
    
    protected double calculateFederalTax(double grossPay, int numExemptions){
        /** Calculate Federal Tax
        * 
        *@param     taxEligible is the amount of taxable income earned after federal exemptions are subtracted.
        *@return    Calculated amount of tax be paid based on income, federal exemption, and corresponding tax bracket.
        * 
        */
        double taxEligible = grossPay - (FEDERAL_EXEMPT * numExemptions);
        
        
        /**
         * Tax bracket calculation:
         * There are 6 total tax brackets from $0 to $5750+.
         * I could probably refactor this into a map if I had additional time.
         */
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
    
    protected double calculateStateTax(double grossPay, int numExemptions){
        /** Calculate State Tax
         * 
         *@return If pay gross pay exceeds exemption amount, 3% of gross pay after exemptions is taxed.
         */
        if(grossPay <= (STATE_EXEMPT * numExemptions)){
            return 0;
        }
        else{
            return(.03 * (grossPay - (STATE_EXEMPT * numExemptions)));
        }
    }
    
    protected double calculateSocialSecurity(double grossPay){
        /** Calculate Social Security
         * 
         *@return   Social Security withholding is taxed by a fixed 7.65% against gross pay.
         */
        return(SS_PERCENT * grossPay);
    }
}
