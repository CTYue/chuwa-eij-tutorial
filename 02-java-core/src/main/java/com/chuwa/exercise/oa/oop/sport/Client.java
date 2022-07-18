package com.chuwa.exercise.oa.oop.sport;

/**
 * @author b1go
 * @date 6/16/22 12:58 AM
 */
public class Client {
    public static void main(String[] args) {
        Cricket cricket = new Cricket();
        Football football = new Football();

        cricket.retirePlayer(1);
        football.retirePlayer(2);

        int[] age = new int[] {11,2,3,4,5,6,7,8,9,10,11};
        cricket.calculateAvgAge(age);
        football.calculateAvgAge(age);

        football.playerTransfer(200, 2);
        football.playerTransfer(200, 9);
    }
}
