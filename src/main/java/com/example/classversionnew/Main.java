package com.example.classversionnew;

//import needed modules
import javafx.application.Application;
import java.util.Scanner;

//the main class
public class Main {
    public static void main(String[] args) {
        FoodCenter foodCenter = new FoodCenter();         // importing FoodCenter class as foodcenter
        System.out.println("\n Welcome to Foodies Fave Queue Management System !!! ");
        System.out.println("-----------------------------------------------------");
        MainMenu();

        //making an unlimited loop to run the main menu
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your option: ");//Asking the option from the user
            String option = scanner.nextLine();
            System.out.println(" ");

            switch (option) {
                case "100", "VFQ":      //option for viewing all cashiers

                    foodCenter.viewAllCashiers();
                    MainMenu();


                    break;

                case "101", "VEQ":      //option for viewing all empty queues

                    foodCenter.viewAllCashiers();
                    foodCenter.viewAllEmptyCashiers();
                    MainMenu();

                    break;

                case "102", "ACQ":      //option for adding customer

                    System.out.print("Enter customer's first name: ");      //asking the first name of the customer
                    String firstName = scanner.next();
                    System.out.print("Enter customer's last name: ");       //asking the last name of the customer
                    String lastName = scanner.next();
                    System.out.print("Enter the number of burgers required: ");     //asking the burgers required from the customer
                    int burgersRequired = scanner.nextInt();
                    System.out.println(" ");

                    foodCenter.addCustomerToCashier(firstName.substring(0,1).toUpperCase() + firstName.substring(1), lastName.substring(0,1).toUpperCase() + lastName.substring(1), burgersRequired );
                    MainMenu();

                    break;

                case "103", "RCQ":      //removing customer from the cashiers

                    System.out.print("Enter the cashier number (1, 2, or 3): ");        //asking the cashier to remover
                    int cashierIndex = scanner.nextInt();
                    System.out.print("Enter the customer row number to remove: ");      //asking the customer position to remove
                    int customerIndex = scanner.nextInt();

                    foodCenter.removeCustomerFromCashier(cashierIndex, customerIndex);
                    MainMenu();

                    break;

                case "104", "PCQ":      //removing a served customer from the cashier

                    System.out.print("Enter the cashier number you want to remove a served customer : ");  //getting the cashier number to remove served customer
                    int cashierNumber = scanner.nextInt();
                    foodCenter.removeServedCustomer(cashierNumber);
                    MainMenu();

                    break;



                case "105", "VCS":      //option for viewing customers sorted alphabetically

                    foodCenter.viewCustomersSortedAlphabetically();
                    MainMenu();

                    break;

                case "106", "SPD":      //option for saving the data into a text file

                    foodCenter.storeProgramData();
                    MainMenu();

                    break;

                case "107", "LPD":      //option for loading the data from the text file

                    foodCenter.loadProgramData();
                    MainMenu();

                    break;

                case "108", "STK":      //option to see the remaining burgers in stock

                    foodCenter.viewRemainingBurgersStock();
                    MainMenu();

                    break;

                case "109", "AFS":      //option to add the burgers to the stock

                    System.out.print("Enter the quantity of burgers to add to the stock: ");       //asking user how many burgers needed to added to stock
                    int quantity = scanner.nextInt();
                    foodCenter.addBurgersToStock(quantity);
                    MainMenu();

                    break;

                case "110", "IFQ":      //option to view individual income of the cashiers

                    foodCenter.incomeOfTheCashiers();
                    MainMenu();

                    break;

                case "112", "GUI":      //option to open graphical user interface

                    try {
                        Application.launch(HelloApplication.class, args);       //method to launch the GUI
                        System.out.println("Successfully Loaded GUI \n");

                    }catch (IllegalStateException e){
                        System.out.println("GUI only can be called once.\n");   //throwing an exception for the error of launching GUI only once
                    }

                    break;


                case "999", "EXT":      //option to exit from the system

                    System.out.println("Thank You for using Foodies Fave Queue Management System !!!");
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;

                default:        //default output for invalid options
                    System.out.println("Invalid Option... PLease enter again...\n");
            }
        }
    }

    private static void MainMenu() {    //printing the main menu

        System.out.println("\n%%%%%%%%%%%%%%%%%% Main Menu %%%%%%%%%%%%%%%%%%\n");
        System.out.println("100 or VFQ: View all Queues");
        System.out.println("101 or VEQ: View all Empty Queues");
        System.out.println("102 or ACQ: Add customer to a Queue");
        System.out.println("103 or RCQ: Remove a customer from a Queue (From a specific location)");
        System.out.println("104 or PCQ: Remove a served customer");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
        System.out.println("106 or SPD: Store Program Data into file");
        System.out.println("107 or LPD: Load Program Data from file");
        System.out.println("108 or STK: View Remaining burgers Stock");
        System.out.println("109 or AFS: Add burgers to Stock");
        System.out.println("110 or IFQ: View income of each cashier");
        System.out.println("112 or GUI: Open GUI");
        System.out.println("999 or EXT: Exit the Program" + "\n");


    }
}