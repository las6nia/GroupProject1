package groupProject;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean doneProcessing = false;
        int originalWithdrawalAmount;
        	//Get pin from the user
        System.out.print("Enter your PIN please: ");
        int PIN = input.nextInt();
        		//terminate program if a negative number right from the start
        if (PIN < 1) {
            System.out.println("\nThe program will terminate!");
            doneProcessing = true;
            input.close();
        } //Else, the loop will continue
        else {
            while (PIN >= 0 && doneProcessing == false) {
                boolean okayToProcess = true;
                
                //Amount to withdraw
                
                System.out.print("\nEnter the amount to be withdrawn: ");
                int withdrawalAmount = input.nextInt();
                int surcharge = 0;
                int dispenseAmount = withdrawalAmount;
                
                //If invalid amount between the ranges

                if (withdrawalAmount <= 0 || withdrawalAmount > 500) {
                    System.out.printf("\nInvalid amount: %d.\n", withdrawalAmount);
                    okayToProcess = false;
                    
                } 
                	//Else, check if type 2 customer
                
                else if (PIN % 2 == 0 && withdrawalAmount % 5 != 0) {
                    System.out.printf("\nYou are a type 2 customer, but the amount you requested was %d.\n", withdrawalAmount);
                    System.out.println("You will be charged $1 for changing your default setting.");
                    System.out.print("Do you want to be charged $1? (Y/N) ");
                    //Check for yes or no
                    char response = input.next().toLowerCase().charAt(0);
                    if (response == 'y') { 
                        surcharge = 1;
                        dispenseAmount = withdrawalAmount - surcharge; //surcharge fee 
                    } else {
                        okayToProcess = false;
                    }
                }
                		//Math logic
                if (okayToProcess) {
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
                    //verification if surcharge approved 
                    if (PIN % 2 == 0 && withdrawalAmount % 5 != 0 && surcharge != 0) {
                        System.out.println("You have been charged $1.\n");
                    }
                  
                    //Currency dispensing 
                    System.out.println("You are receiving the following bills:");
                    System.out.printf("\tNumber of $10 bills: %d\n", tens);
                    System.out.printf("\tNumber of $5 bills: %d\n", fives);
                    System.out.printf("\tNumber of $1 bills: %d\n", ones);

                    System.out.print("Enter your PIN please: ");
                    PIN = input.nextInt();
                    //If continue loop, else terminate and break out of all loops (break)
                    if (PIN < 1) {
                        System.out.println("\nThe program will terminate!");
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }
    }
}
