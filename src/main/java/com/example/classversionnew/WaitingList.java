package com.example.classversionnew;

import java.util.ArrayList;
import java.util.*;

public class WaitingList {



    private final int size;
    private int front;
    private int rear;	//Variable declaration

    private final ArrayList< Customer > cashier = new ArrayList < Customer > ();	//Declaring Integer array list


    WaitingList (int size)	//Constructor
    {
        this.size = size;
        this.front = this.rear = -1;
    }

    public boolean isFull(){
        return (front == 0 && rear == size - 1) || (rear == (front - 1) % (size - 1));
    }
    public boolean isEmpty(){
        return (front == -1);
    }

    public void enQueue (Customer value)	//Insertion Function
    {
        if ((front == 0 && rear == size - 1) || (rear == (front - 1) % (size - 1)))	// Condition if queue is full
        {
            System.out.print ("Queue Full!");
        }
        else if (front == -1)	// Condition for empty queue.
        {
            front = 0;
            rear = 0;
            cashier.add (rear, value);
        }
        else if (rear == size - 1 && front != 0)
        {
            rear = 0;
            cashier.set (rear, value);
        }
        else
        {
            rear = (rear + 1);

// Adding a new element if

            if (front <= rear) { cashier.add (rear, value); } // Else updating old value
            else { cashier.set (rear, value);
            }
        }
    }
    public Customer deQueue ()
    //Dequeue Function
    {
        Customer temp;

        if (front == -1)
        //Checking for empty queue
        { System.out.print ("Queue Empty!");
            return null;
        }
        temp = cashier.get (front);
        cashier.set (front,null);
        if (front == rear)
        // For only one element
        {
            front = -1;
            rear = -1;
        }
        else if (front == size - 1) {
            front = 0;
        }
        else {
            front = front + 1;
        }
        return temp;
        // Returns dequeued element
    }


    public ArrayList<Customer> getCashier() {

        return cashier;
    }
}
