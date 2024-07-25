package com.example.classversionnew;    //importing the package

class Customer {        //creating the customer class
    private String firstName;       //assigning variable for first name
    private String lastName;        //assigning variable for last name
    public int burgersRequired;     //assigning variable for burger count
    public Customer(String firstName, String lastName, int burgersRequired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.burgersRequired = burgersRequired;
    }

    public String getFirstName() {  //getter for the getting first name

        return firstName;
    }

    public String getLastName() {   //getter for getting last name

        return lastName;
    }

    public int getBurgersRequired() {   //getter for getting burgers needed

        return burgersRequired;
    }
}
