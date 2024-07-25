package com.example.classversionnew;        //importing the package

import java.util.ArrayList;     //importing needed modules
import java.util.List;

class FoodQueue {       //creating queue class
    private int cashierCapacity;    //getting cashier size
    private List<Customer> cashier;

    public List<Customer> getCashier() {

        return cashier;
    }

    public FoodQueue(int cashierCapacity) {
        this.cashierCapacity = cashierCapacity;
        this.cashier = new ArrayList<>();

    }


    public void addCustomer(Customer customer) {        //adding customer

        cashier.add(customer);

    }

    public void removeCustomer(int customerIndex) {     //remove customer
        if(!cashier.isEmpty()) {
            if (customerIndex >= 0 && customerIndex < cashier.size()) {
                cashier.remove(customerIndex);

            } else {
                System.out.println("Invalid customer row.");
            }
        }else{
            System.out.println();
        }
    }

    public void removeServedCustomer() {        //remove served customer
        if (!cashier.isEmpty()) {
            cashier.remove(0);

        } else {
            System.out.println("Queue is empty. No customers to remove.");
        }
    }

    public List<Customer> getSortedCustomers() {    //sorting customers in alphabetical order
        List<Customer> sortedCustomers = new ArrayList<>(cashier);
        for (int i = 0; i < sortedCustomers.size()-1; i++) {
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


    public int getCashierCapacity() {       //getting cashier capacity

        return cashierCapacity;
    }


}