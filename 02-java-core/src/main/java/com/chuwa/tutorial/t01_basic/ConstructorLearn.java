package com.chuwa.tutorial.t01_basic;

class Date {

    private int day;
    private int month;
    private int year;
    private String event;

    // Default constructor
    public Date() {
        // We must define the default values for day, month, and year
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    // Parameterized constructor
    public Date(int day, int month, int year) {
        // The arguments are used as values
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Parameterized constructor
    public Date(int day, int month, int year, String event) {
        this(day, month, year); // calling the constructor
        this.event = event;
    }

    // A simple print function
    public void printDate() {
        System.out.println("Date: " + day + "/" + month + "/" + year + "  --> " + event);
    }

}

public class ConstructorLearn {
    public static void main(String args[]) {
        // Call the Date constructor to create its object;
        Date date = new Date(1, 1, 2019, "New Year"); // Object created with specified values! // Object created with default values!
        date.printDate();
    }
}
