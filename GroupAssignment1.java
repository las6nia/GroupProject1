package groupProject;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean doneProcessing = false;
        int originalWithdrawalAmount;

        System.out.print("Enter your PIN please: "); // lra - removed the bottom asking question from mark's version and placed at top instead
        int PIN = input.nextInt();

        if (PIN < 1) { // lra - checking if the pin is less than 0
            System.out.println("\nThe program will terminate!");
            doneProcessing = true;
            input.close();
        	} 
        else { // lra - else it will continue to the loop
            // used Mark's body logic to get us started
            while (PIN >= 0 && doneProcessing ==false) {

                    boolean okayToProcess = true;

                    System.out.print("\nEnter the amount to be withdrawn: ");
                    int withdrawalAmount = input.nextInt(); // lra - getting the amount to withdraw
                    int surcharge = 0; // lra - surcharge to 0
                    int dispenseAmount = withdrawalAmount; // lra - dispense amount will equal the withdrawal amount *fix*

                    // Invalid amount part
                    if (withdrawalAmount <= 0 || withdrawalAmount > 500) {
                        System.out.printf("\nInvalid amount: %d.\n", withdrawalAmount);
                        okayToProcess = false;
                    }

                    // Determining if type 2 customer
                    else if (PIN % 2 == 0 && withdrawalAmount % 5 != 0) {
                        System.out.printf("\nYou are a type 2 customer, but the amount you requested was %d.\n",withdrawalAmount);
                        System.out.println("You will be charged $1 for changing your default setting.");
                        System.out.print("Do you want to be charged $1? (Y/N) ");
                        char response = input.next().toLowerCase().charAt(0);
                        if (response == 'y') { 
                            surcharge = 1; // Applying surcharge
                            dispenseAmount = withdrawalAmount - surcharge; // Adjust dispense amount for surcharge
                        } else {
                            okayToProcess = false; // If user declines surcharge, cancel the transaction
                        }
                    }
                    // If the okayToProcess is valid (true), continue to dispense
                    if (okayToProcess) { // lra - merged Lin's math logic using the remainders of previous leftover values
                        int tens = dispenseAmount / 10;
                        int remainder = dispenseAmount % 10;
                        int fives = remainder / 5;
                        int ones = remainder % 5;

                        if (remainder >= 5) {
                            fives = 1;
                            ones = remainder - 5;
                        } else {
                            ones = remainder;
                        }

                        System.out.printf("\nYou requested $%d\n", withdrawalAmount);

                        // Check if surcharge was applied (surcharge == 1 means user accepted)
                        if (PIN % 2 == 0 && withdrawalAmount % 5 != 0 && surcharge != 0) {
                            System.out.println("You have been charged $1.\n");
                            //System.out.printf("TESTING %d\n", surcharge); // lra - testing the surcharge
                        }

                        System.out.println("You are receiving the following bills:");
                        System.out.printf("\tNumber of $10 bills: %d\n", tens);
                        System.out.printf("\tNumber of $5 bills: %d\n", fives);
                        System.out.printf("\tNumber of $1 bills: %d\n", ones);

                        System.out.print("Enter your PIN please: ");
                        PIN = input.nextInt();

                        if (PIN < 1) { // If PIN is less than 1, reduce
                            System.out.println("\nThe program will terminate!");
                            break;
                        } else {
                            continue;
                        }
                    }
            }
        } //end of else while the pin > 0
    
    }//end of main
}//end of class
