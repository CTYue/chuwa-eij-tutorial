package com.chuwa.exercise.oa.oop.sport;

import java.util.Arrays;

/**
 * @author b1go
 * @date 6/16/22 12:36 AM
 */
public class Cricket implements Sport {
    // 1-based
    private int[] playerIDs;

    public Cricket() {
        this.playerIDs = new int[11];
        Arrays.fill(playerIDs, 1);
        System.out.println("A new cricket team has been formed");
    }

    @Override
    public void retirePlayer(int id) {
        playerIDs[id - 1] = -1;
        System.out.println("Player has already retired");
    }
}
