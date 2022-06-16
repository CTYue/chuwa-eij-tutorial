# Sport Inheritance
   Create two classes: Cricket and Football. Both classes should implement the interface Sport.

1. Class Cricket should have the variable int[ ] playerIDs. The 1-based index of a player is the player's
   ID. It should have the following methods:
   * Cricket( ): Initialize the empty array player/Ds to store 11 integer values. Assign the value 1 to each
   element in playerIDs. Print "A new cricket team has been formed".
   * void calculateAvgAge(intl] age). age contains the age of every player. Print the average age of the
   team with two digits after the decimal in the form "The average age of the team is (avgAge)".
   * void retirePlayer(int id). Assign -1 to playerlds[id]. Print " Player with id: (id} has retired". If the
   player has already retired, print "Player has already retired".
2. Class Football should have the variable intl playerIDs. The 1-based index of a player is the player's
   ID. It should have the following methods:
   * Football( ). Initialize the empty array playerIDs to store 11 integer values. Assign the value 1 to each
   element in playerIDs. Print "A new football team has been formed".
   * void calculateAvgAge(int[] age). age contains the age of every player. Print the average age of the
   team with two digits after the decimal in the form "The average age of the team is (avgAge)".
   * void retirePlayer(int id): Assign -1 to playerIDs[id]. Print "Player with id: (id} has retired". If the
   player has already retired, print "Player has already retired".
   * void player Transfer(int fee, int id): If the player is still on the team, playeriDs[id] = 1, print "Player with
   id: lid} has been transferred with a fee of {fee'". If the player has already retired, print "Player has
   already retired".
   The locked code stub provides the interface Sport and also validates the implementation of the Cricket
   and Football classes. Please use inheritance and encapsulation to minimize code repetition.