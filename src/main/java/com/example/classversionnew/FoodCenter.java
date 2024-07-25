package com.example.classversionnew;

//importing required modules
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class FoodCenter {      //creating class as FoodCenter for creating sll functions
    private static final int MAX_STOCK = 50;        //taking max stock as 50
    public static int currentStock;     //variable to take remaining stock

    public static FoodQueue cashier_1;      //assigning the cashier_1 variable for the FoodQueue Class
    public static FoodQueue cashier_2;      //assigning the cashier_2 variable for the FoodQueue Class
    public static FoodQueue cashier_3;      //assigning the cashier_3 variable for the FoodQueue Class
    public static WaitingList waiting_list;     //assigning the waiting list for circular queue
    private int incomeCashier_1 = 0;        //variable for getting income for cashier 1
    private int incomeCashier_2 = 0;        //variable for getting income for cashier 2
    private int incomeCashier_3 = 0;        //variable for getting income for cashier 3



    public FoodCenter() {
        this.currentStock = MAX_STOCK;
        this.cashier_1 = new FoodQueue(2); //assigning the cashier sizes for the cashier 1
        this.cashier_2 = new FoodQueue(3); //assigning the cashier sizes for the cashier 2
        this.cashier_3 = new FoodQueue(5); //assigning the cashier sizes for the cashier 3
        this.waiting_list = new WaitingList(10);    //assigning the size for the waiting list
    }

    public void viewAllCashiers() {     //printing all the cashiers vertically
        System.out.println("*******************");
        System.out.println("*    Cashiers     *");
        System.out.println("*******************");

        String[][] NewCashier= getCashierStatus(cashier_1);
        for (String[] cashier : NewCashier){
            for(String customer : cashier){
                System.out.print( " " + customer + "     ") ;
            }
            System.out.println(" ");
        }
    }


    private String[][] getCashierStatus(FoodQueue cashier) {      //getting the cashiers status and appending the "X" or "O" to 2D array
        String[][] que = new String[5][3];      //assigning size of 2D array


        for(int i = 0; i < 3; i++){
            for (int x = 0; x < 5; x++){
                try {
                    if (cashier.getCashier().get(x) != null){
                        que[x][i] = "0";    //appending 'X's for 2D array from cashiers

                    }

                } catch (Exception e){
                    if (x < cashier.getCashierCapacity()){
                        que[x][i] = "X";    //appending 'O's for 2D array from cashiers

                    }else{
                        que[x][i] = " ";
                    }

                }

            }
            if (cashier == cashier_1){
                cashier = cashier_2;

            }else {
                cashier = cashier_3;
            }
        }

        return que;
    }



    public void viewAllEmptyCashiers() {    //function for viewing all empty queues
        System.out.println("\nEmpty Queues:");

        if (cashier_1.getSortedCustomers().isEmpty()) {     //checking whether cashier 1 is empty
            System.out.println("Cashier 1");
        }
        if (cashier_2.getSortedCustomers().isEmpty()) {     //checking whether cashier 2 is empty
            System.out.println("Cashier 2");
        }
        if (cashier_3.getSortedCustomers().isEmpty()) {     //checking whether cashier 3 is empty
            System.out.println("Cashier 3");
        }
    }

    public void addCustomerToCashier(String firstName, String lastName, int burgersRequired) {      //function for adding customer to cashiers
        FoodQueue shortestCashier = getShortestCashier();       //checking for the shortest cashier
        Customer customer = new Customer(firstName, lastName, burgersRequired);
        if (shortestCashier != null) {  //checking whether shortest cashier is not null

            shortestCashier.addCustomer(customer);      //adding customer
            System.out.println("Successfully Added " + customer.getFirstName() + " to the shortest cashier." );

        } else {
            if (!waiting_list.isFull()){        //checking whether the waiting list is full
                waiting_list.enQueue(customer); //adding customer to waiting queue when cashiers are full
                System.out.println("\nAll the cashiers are full. New customers successfully Added to waiting List at position " + String.valueOf(waiting_list.getCashier().indexOf(customer) + 1));

            }else {     //checking whether the waiting list is full
                System.out.println("\nCannot add customer... the waiting list is also full...");
            }

        }
    }

    private FoodQueue getShortestCashier() {        //function for getting the shortest cashier
        int size1 = cashier_1.getSortedCustomers().size();
        int size2 = cashier_2.getSortedCustomers().size();
        int size3 = cashier_3.getSortedCustomers().size();


        if (size1 <= size2 && size1 <= size3 && size1 < 2 ) {       //checking whether the cashier 1 is shortest
            return cashier_1;
        }
        if (size2 <= size1 && size2 <= size3 && size2 < 3) {        //checking whether the cashier 2 is shortest
            return cashier_2;
        }
        if (size3 <= size1 && size3 <= size2 && size3 < 5) {        //checking whether the cashier 3 is shortest
            return cashier_3;
        }
        if(size2 == size3){
            return cashier_3;

        }else if(size1 == 2 && size2 == 3 && size3 < 5){
            return cashier_3;
        }
        return null;
    }

    public void removeCustomerFromCashier(int cashierIndex, int removeCustomer) {       //function for removing cashier

        if (cashierIndex == 1) {        //removing from cashier 1
            if(!cashier_1.getCashier().isEmpty()) {
                cashier_1.removeCustomer(removeCustomer - 1);
                System.out.println("\nSuccessfully removed customer " + removeCustomer + " from cashier 1.\n");
            }else {
                System.out.println("Cannot remove customer... Cashier 1 is Empty ");
            }

        } else if (cashierIndex == 2) { //removing from cashier 2
            if (!cashier_2.getCashier().isEmpty()) {
                cashier_2.removeCustomer(removeCustomer - 1);
                System.out.println("\nSuccessfully removed customer " + removeCustomer + " from cashier 2.\n");
            }else{
                System.out.println("Cannot remove customer... Cashier 2 is Empty ");
            }
        } else if (cashierIndex == 3) {     //removing from cashier 3
            if (!cashier_3.getCashier().isEmpty()) {
                cashier_3.removeCustomer(removeCustomer - 1);
                System.out.println("\nSuccessfully removed customer " + removeCustomer + " from cashier 3.\n");
            }else{
                System.out.println("Cannot remove customer... Cashier 3 is Empty");
            }
        } else {
            System.out.println("Invalid cashier number. \n");
        }
        if (!waiting_list.getCashier().isEmpty()) { //checking whether waiting list is empty
            Customer customer = waiting_list.deQueue();     //adding customer to waiting list
            if(customer!=null) {
                System.out.println(customer.getFirstName() + " moved from waiting list to cashier " + cashierIndex);
                addCustomerToCashier(customer.getFirstName(), customer.getLastName(), customer.burgersRequired);
            }
        }
    }

    public void removeServedCustomer(int cashierNumber) {       //function for remove served customer
        int burger_price = 650; //price per burger


        if(cashierNumber == 1) {        //removed served customer from cashier 1
            try {
                Customer customer = cashier_1.getCashier().get(0);
                cashier_1.removeServedCustomer();
                System.out.println("Successfully removed served customer " + customer.getFirstName() + " from cashier 1.\n");
                currentStock -= customer.getBurgersRequired();
                checkStock();
                incomeCashier_1 += customer.getBurgersRequired() * burger_price;
            }catch(IndexOutOfBoundsException e){
                System.out.println();
            }

        }else if(cashierNumber == 2) {      //removed served customer from cashier 2
            try {
                Customer customer = cashier_2.getCashier().get(0);
                cashier_2.removeServedCustomer();
                System.out.println("Successfully removed served customer " + customer.getFirstName() + " from cashier 2.\n");
                currentStock -= customer.getBurgersRequired();
                checkStock();
                incomeCashier_2 += customer.getBurgersRequired() * burger_price;
            }catch(IndexOutOfBoundsException e){
                System.out.println();
            }

        } else if (cashierNumber == 3) {    //removed served customer from cashier 3
            try {
                Customer customer = cashier_3.getCashier().get(0);
                cashier_3.removeServedCustomer();
                System.out.println("Successfully removed served customer " + customer.getFirstName() + " from cashier 3.\n");
                currentStock -= customer.getBurgersRequired();
                checkStock();
                incomeCashier_3 += customer.getBurgersRequired() * burger_price;
            }catch(IndexOutOfBoundsException e){
                System.out.println();
            }
        }
        if (!waiting_list.getCashier().isEmpty()) {     //adding customer from waiting list
            Customer customer = waiting_list.deQueue();
            if (customer != null) {
                System.out.println(customer.getFirstName() + " moved from waiting list to cashier " + cashierNumber);
                addCustomerToCashier(customer.getFirstName(), customer.getLastName(), customer.burgersRequired);
            }
        }
    }

    public void incomeOfTheCashiers(){      //function for displaying individual  income of cashiers
        System.out.println("Income of cashier 1 : " + "LKR " +  incomeCashier_1);
        System.out.println("Income of cashier 2 : " + "LKR " +  incomeCashier_2);
        System.out.println("Income of cashier 3 : " + "LKR " +  incomeCashier_3);
        System.out.println(" ");
    }

    public void viewCustomersSortedAlphabetically() {   //function for sorting people alphabeical order
        List<Customer> sortedCustomers = new ArrayList<>();
        sortedCustomers.addAll(cashier_1.getSortedCustomers());
        sortedCustomers.addAll(cashier_2.getSortedCustomers());
        sortedCustomers.addAll(cashier_3.getSortedCustomers());

        sortedCustomers = bubbleSort(sortedCustomers);

        System.out.println("Customers Sorted Alphabetically:");
        for (Customer customer : sortedCustomers) {
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " - " + customer.getBurgersRequired() + " burgers");
        }
    }
    public List<Customer> bubbleSort(List<Customer> sortedCustomers){       //sorting using bubble sort
        for (int i = 0; i < sortedCustomers.size()-1; i++){
            for (int j = 0; j < sortedCustomers.size()-1; j++){
                if(sortedCustomers.get(i).getFirstName().charAt(0)> sortedCustomers.get(i+1).getFirstName().charAt(0)) {
                    Customer temp = sortedCustomers.get(i);
                    sortedCustomers.set(i, sortedCustomers.get(i + 1));
                    sortedCustomers.set(i + 1, temp);
                }
            }
        }
        return sortedCustomers;
    }

    public void viewRemainingBurgersStock() {       //function to view remaining burgers in stock

        System.out.println("Remaining Burgers Stock: " + currentStock);
    }

    public void addBurgersToStock(int quantity) {   // function to add burgers to stock
        if (currentStock != 50){
            if (currentStock + quantity <= 50){     //making burger stock not to exceed 50
                currentStock += quantity;

                System.out.println("Successfully Added " + quantity + " burgers to the stock");
                System.out.println();

            }else {
                System.out.println("The amount of burgers you entered exceeds 50.");
                System.out.println();
            }


        }else{
            System.out.println("Burger stock is already full.");
            System.out.println();
        }
    }


    public void storeProgramData() {        //function for storing data into a text file

            try {
                FileWriter textFile = new FileWriter("Stored_Data.txt");    //opening file if available or creating new file

                textFile.write("Customers in cashier 1 :" + "\n");  //writing cashier 1 data into file
               for (int i = 0; i < 2; i++){
                   try {
                       Customer customer = cashier_1.getCashier().get(i);
                       textFile.write(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getBurgersRequired() + " ");
                       textFile.write("\n");

                   }catch (IndexOutOfBoundsException e){
                       textFile.write("Empty");
                       textFile.write("\n");
                   }
                }
                textFile.write("\n");

                textFile.write("Customers in cashier 2 :" + "\n");  //writing cashier 2 data into file
                for (int i = 0; i < 3; i++){
                    try{
                        Customer customer = cashier_2.getCashier().get(i);
                        textFile.write(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getBurgersRequired() + " ");
                        textFile.write("\n");

                    }catch (IndexOutOfBoundsException e){
                        textFile.write("Empty");
                        textFile.write("\n");
                    }
                }
                textFile.write("\n");

                textFile.write("Customers in cashier 3 :" + "\n");  //writing cashier 3 data into file
                for (int i = 0; i < 5; i++){
                    try {
                        Customer customer = cashier_3.getCashier().get(i);
                        textFile.write(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getBurgersRequired() + " ");
                        textFile.write("\n");

                    }catch (IndexOutOfBoundsException e){
                        textFile.write("Empty");
                        textFile.write("\n");
                    }
                }
                textFile.write("\n");

                textFile.write("Customers in waiting List:" + "\n");    //writing waiting list data into text file
                for (int i = 0; i < 10; i++){
                    try {
                        Customer customer = waiting_list.getCashier().get(i);
                        if(customer != null) {
                            textFile.write(customer.getFirstName() + " " + customer.getLastName() + " " + customer.getBurgersRequired());
                            textFile.write("\n");
                        }else{
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        textFile.write("Empty");
                        textFile.write("\n");

                    }
                }
                textFile.write("\n");

                textFile.write("Burgers in stock : " + "\n");   //writing amount of burgers in stock into text file
                textFile.write(String.valueOf(currentStock));

                textFile.close();
                System.out.println("Successfully wrote to the file.");
                System.out.println();
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
    }


    public void loadProgramData() {     //function for reading data from text file
        File file = new File("Stored_Data.txt");    //searching and opening file
        String name = "";
        String FirstName = "";
        String LastName = "";
        int burgerCount = 0;
        int count = 1;
        int customerCount = 0;

        try {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine()){
                if (readFile.nextLine().contains("Customers in cashier 1 :")){      //searching for the string and search next line for data
                    while (readFile.hasNext()){
                        name = readFile.next();
                        if (!name.equals("Empty")){
                            if (count == 1){
                                FirstName = name;

                                count++;

                            } else if (count == 2) {
                                LastName = name;

                                count++;

                            } else if (count == 3) {
                                burgerCount = Integer.parseInt(name);
                                Customer customer = new Customer(FirstName, LastName, burgerCount); //appending data into cashier 1
                                cashier_1.addCustomer(customer);

                                count = 1;

                                if (customerCount < 1){
                                    customerCount++;

                                }else {
                                    customerCount = 0;
                                    break;

                                }

                            }

                        }else{
                            break;
                        }
                    }


                }
            }
            readFile = new Scanner(file);
            while (readFile.hasNextLine()){
                if (readFile.nextLine().contains("Customers in cashier 2 :")){      //searching for the string and search next line for data
                    while (readFile.hasNext()){
                        name = readFile.next();
                        if (!name.equals("Empty")){
                            if (count == 1){
                                FirstName = name;
                                count++;

                            } else if (count == 2) {
                                LastName = name;
                                count++;

                            } else if (count == 3) {
                                burgerCount = Integer.parseInt(name);

                                Customer customer = new Customer(FirstName, LastName, burgerCount);     //adding data in text file to cashier 2
                                cashier_2.addCustomer(customer);

                                count = 1;

                                if (customerCount < 2){
                                    customerCount++;

                                }else {
                                    customerCount = 0;
                                    break;

                                }

                            }

                        }else{
                            break;
                        }
                    }


                }
            }
            readFile = new Scanner(file);
            while (readFile.hasNextLine()){
                if (readFile.nextLine().contains("Customers in cashier 3 :")){      //searching for the string and search next line for data
                    while (readFile.hasNext()){
                        name = readFile.next();
                        if (!name.equals("Empty")){
                            if (count == 1){
                                FirstName = name;
                                count++;

                            } else if (count == 2) {
                                LastName = name;
                                count++;

                            } else if (count == 3) {
                                burgerCount = Integer.parseInt(name);

                                Customer customer = new Customer(FirstName, LastName, burgerCount); //adding data in text to cashier 3
                                cashier_3.addCustomer(customer);

                                count = 1;

                                if (customerCount < 4){
                                    customerCount++;

                                }else {
                                    customerCount = 0;
                                    break;

                                }

                            }

                        }else{
                            break;
                        }
                    }


                }
            }
            readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                if (readFile.nextLine().contains("Customers in waiting List:")) {   //searching for the string and search next line for data
                    while (readFile.hasNext()) {
                        name = readFile.next();
                        if (!name.equals("Empty")) {
                            if (count == 1) {
                                FirstName = name;
                                count++;

                            } else if (count == 2) {
                                LastName = name;
                                count++;

                            } else if (count == 3) {
                                burgerCount = Integer.parseInt(name);

                                Customer customer = new Customer(FirstName, LastName, burgerCount); //adding data from text file to waiting list
                                waiting_list.enQueue(customer);

                                count = 1;

                                if (customerCount < 9) {
                                    customerCount++;

                                } else {
                                    customerCount = 0;
                                    break;

                                }

                            }

                        } else {
                            break;
                        }
                    }


                }
            }
            readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                if (readFile.nextLine().contains("Burgers in stock : ")) {      //searching for the string and search next line for data
                    burgerCount = Integer.parseInt(readFile.next());        //appending burger count in the text file into system
                    currentStock = burgerCount;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Program data loaded successfully.");
    }

    private void checkStock() {     //checking if the burger stock is less than 10
        if (currentStock <= 10) {
            System.out.println("Warning: Low stock. Remaining Burgers Stock: " + currentStock);
        }
    }
}