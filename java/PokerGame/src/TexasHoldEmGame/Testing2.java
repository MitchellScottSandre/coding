/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import static TexasHoldEmGame.PokerGame.playersTableOrder;

/**
 *
 * @author scott
 */
public class Testing2 {

    /**
     * @param args the command line arguments
     */
    
    public static int numPlayers = 10;
    public static int chips[][] = { {5, 60}, {25, 50}, {100, 30},  {500, 0}, {1000, 0}, {5000, 0} };
    
    static void displayArray(int data[]){
        for (int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
    }
    public static int[] moneyToChips(int x) {
        //should be of dimension 6 rows by 2 columns .... each row is the index of chips that should be bet, the number beside it is how many
        //bet just 1 of that chip if you can
        System.out.println("money to chips. start val: " + x);
       
        int temp[] = new int[6];
        while (x > 0) {
            for (int i = 5; i >= 0; i--) {
                if (chips[i][1] > 0 && x >= chips[i][0]) {//has at least 1 chip, money is larger or equal to that chip value
                    temp[i]++;//so we want to bet that chip
                    x -= chips[i][0];
                    System.out.println("x is " + x);
                    i++;
                } 
            }
        }
        for (int i = 0; i < 6; i++) {
            // System.out.println("temp at " + i + ": " + temp[i]);
        }

        displayArray(temp);
        return temp;
    }
    
        public int getNextPlayerOnTableThatHasNotFolded(int z) {//passed it 1..4 players
        boolean foundNextPlayerWhoHasNotFolded = false;
        while (foundNextPlayerWhoHasNotFolded == false) {
            z++;//1 becomes 2 //4 becomes 5
            //z = z % numPlayers; //2 % 4 == 2 // 5 % 4 == 1
            z = Round.getNextPlayerOnTable(z, -1, -1);//z now becomes 4 (player 0) // 1 becomes 4 (player 0) INFINITE LOOP
            if (true == false) {//well, if USER has folded...then go back to top
                foundNextPlayerWhoHasNotFolded = true;
                return z;
            }
        }
        return 99;
    }

    public static void main(String[] args) {
        moneyToChips(80);
        numPlayers = 10;
       int p = getNextPlayerOnTable(5, -1, -1);
       System.out.println("1. 10 players... p is " + p);
       System.out.println("That person is " + playersTableOrder[p]);
       numPlayers = 7;
        p = getNextPlayerOnTable(5, -1, -1);
       System.out.println("1. 7 players... p is " + p);
       System.out.println("That person is " + playersTableOrder[p]);
       numPlayers = 4;
        p = getNextPlayerOnTable(5, -1, -1);
       System.out.println("1. 4 players... p is " + p);
       System.out.println("That person is " + playersTableOrder[p]);
       numPlayers = 3;
        p = getNextPlayerOnTable(5, -1, -1);
       System.out.println("1. 3 players... p is " + p);
       System.out.println("That person is " + playersTableOrder[p]);
       numPlayers = 4;
        p = getNextPlayerOnTable(2, -1, -1);
       System.out.println("1. 4 players... p is " + p);
       System.out.println("That person is " + playersTableOrder[p]);
          numPlayers = 4;
        p = getNextPlayerOnTable(5, -1, -1);
       System.out.println("1. 4 players... p is " + p);
       System.out.println("That person is " + playersTableOrder[p]);
    }
    
        public static int getNextPlayerOnTable(int indexOnTable, int otherIndex1, int otherIndex2) {//returns index of next player, index ON TABLE so player3 is index 0, user is index 4
        //System.out.println("CALLED GET NEXT PLAYER ON TABLE");
        indexOnTable = indexOnTable % 10;
        boolean foundNextPlayer = false;
        while (foundNextPlayer == false) {
            if (playersTableOrder[indexOnTable] < numPlayers) {//allowable player number
                //but that player can't already have a position
                if (indexOnTable != otherIndex1 && indexOnTable != otherIndex2) {
                    return indexOnTable;
                }
            }
            indexOnTable++;
            if (indexOnTable == 10) {
                indexOnTable = 0; //loop to beginning
            }
        }
        System.out.println("REACHED END of get next player...RETURNING -1 ");
        return -1;
    }

}
