package com.chuwa.tutorial.t02_oop.encapsulation;

/**
 * @author b1go
 * @date 6/7/22 11:31 PM
 */
public class EncapsulationBadSample {
    public static void main(String[] args) {
        User2 user = new User2("Elon","12345"); //Creates a new user and stores the password and username

        user.login("Elon","12345"); //Grants access because credentials are valid

        user.login("Elon", "3456"); //Does not grant access because the credentials are invalid

        user.password = "3456"; //OOPS SOMEONE ACCESSED THE PASSWORD FIELD

        user.login("Elon", "3456"); // GRANTS ACCESS BUT THIS SHOULD NOT HAVE HAPPENED!
    }
}

// User Class
class User2 {

    // Public Fields
    public String userName;
    public String password;

    // Parameterized Constructor to create new users
    public User2(String userName, String password) {
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
