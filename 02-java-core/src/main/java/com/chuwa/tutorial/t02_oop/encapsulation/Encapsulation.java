package com.chuwa.tutorial.t02_oop.encapsulation;

/**
 * @author b1go
 * @date 6/7/22 11:26 PM
 */
public class Encapsulation {
    public static void main(String[] args) {
        User user = new User("Elon","12345"); //Creates a new user and stores the password and username

        user.login("Elon","12345"); //Grants access because credentials are valid

        user.login("Elon", "3456"); //Does not grant access because the credentials are invalid

        //educative.password = "3456"; //Uncommenting this line will give an error
        //Fields of User class cannot be accessed now
    }
}

// User Class
class User {

    // Private fields
    private String userName;
    private String password;

    //Parameterzied constructor to create a new users
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void login(String userName, String password) {
        //Check if the username and password match
        if (this.userName.toLowerCase().equals(userName.toLowerCase()) && this.password.equals(password)) {
            // .toLowrcase converts all the characters to lowercase & .equals checks if two strings match

            System.out.println("Access Granted against the username: "+this.userName +" and password: "+this.password);
        } else {
            System.out.println("Invalid Credentials!"); //Else invalid credentials
        }
    }
}
