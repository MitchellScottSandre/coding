/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import java.util.ArrayList;

/**
 *
 * @author scott
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static int hasFiveSequentially(Card hand[]) {//check for Ace case
        ArrayList<Card> handList = new ArrayList<Card>();
        int usedNumbers[] = new int[13];//2,3,4,5,6,7,8,9,10,j,q,k,a
        for (int i = 0; i < hand.length; i++) {//was ----- WHAT ABOUT DOUBLES OF NUMBERS LIKE 2 3 3 4 4 5 6
            usedNumbers[hand[i].value - 2]++;//USED, increase to 1
            if (usedNumbers[hand[i].value - 2] == 1) {//handles NOT having doubles of numbers like 2 3 4 4 5 
                handList.add(hand[i]);
                if (hand[i].value == 14) {
                    Card c = new Card(1, hand[i].suit);
                    handList.add(0, c);//add to front..shifts elements to right
                }
            }
        }

        for (int i = 0; i < handList.size() - 4; i++) {//getting first card
            int val = handList.get(i).value;
            //ACE CASE
            int counter = 0;
            for (int j = 0; j < 4; j++) {
                if (handList.get(j + i + 1).value == val + 1 + j) {//get next sequential cards
                    counter++;
                    System.out.println("xx, i is " + i + ", j is " + j + ", val is " + val);
                } else {
                    break;
                }

                if (counter == 4) {
                    System.out.println("yes  sequentially");
                    return val;
                }
            }
        }
        // System.out.println("not 5 sequentially.");
        return -1;

    }

    public static boolean hasFiveSameSuit(Card hand[]) {
        int numClubs = 0;
        int numHearts = 0;
        int numSpades = 0;
        int numDiamonds = 0;
        for (int i = 0; i < 7; i++) {
            switch (hand[i].suit) {
                case 'c':
                    numClubs++;
                    break;
                case 'd':
                    numDiamonds++;
                    break;
                case 's':
                    numSpades++;
                    break;
                case 'h':
                    numHearts++;
            }
        }

        if (numClubs >= 5 || numHearts >= 5 || numSpades >= 5 || numDiamonds >= 5) {
            return true;
        }
        return false;
    }

    public static int determineHighestVal(Card hand[]) {
        int max = 0;
        for (int i = 0; i < 7; i++) {
            if (hand[i].value > max) {
                max = hand[i].value;
            }
        }
        return max;
    }

    public static int determineLowestVal(Card hand[]) {
        int min = 15;
        for (int i = 0; i < 7; i++) {
            if (hand[i].value < min) {
                min = hand[i].value;
            }
        }
        return min;
    }

    public static int fourOfAKind(Card hand[]) {
        for (int i = 0; i < 4; i++) {
            int val = hand[i].value;
            if (hand[i + 1].value == val && hand[i + 2].value == val && hand[i + 3].value == val) {
                return val;
            }
        }
        return -1;
    }

    public static int fullHouse(Card hand[]) {//return pair of 3 value
        for (int i = 0; i < 3; i++) {
            int thisValue = hand[i].value;
            if (thisValue == hand[i + 1].value && thisValue == hand[i + 2].value && hand[i + 3].value == hand[i + 4].value) {
                return thisValue;
            }
            if (thisValue == hand[i + 1].value && hand[i + 2].value == hand[i + 3].value && hand[i + 3].value == hand[i + 4].value) {
                return hand[i + 2].value;
            }
        }
        return -1;
    }

    public static int twoPair(Card hand[]) {
        int numPairs = 0;
        int higherVal = 0;
        for (int i = 0; i < 6; i++) {
            int val = hand[i].value;
            if (val == hand[i + 1].value) {
                higherVal = val;
                System.out.println("x");
                numPairs++;
                i++;
            }

        }
        if (numPairs == 2) {
            return higherVal;//will be higher value
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("\u2022 ");
        int y = 10;
        int x = 2;
        int kk = (int) (Math.random() * (y - x) + x);
        System.out.println("random val is: " + kk);
        Card c1 = new Card(14, 'c');
        Card c2 = new Card(2, 'c');
        Card c3 = new Card(3, 'c');
        Card c4 = new Card(4, 'c');
        Card c5 = new Card(5, 'c');
        Card c6 = new Card(6, 'c');
        Card c7 = new Card(4, 'c');

        Card[] hand = {c1, c2, c3, c4, c5, c6, c7};

        sortPlayerHandByCardValue(hand);
        System.out.println("have sequentially is " + hasFiveSequentially(hand));
        playerDisplayHand_thatIsUsedToDetermineWinner(hand);
//        System.out.println(hasFiveSameSuit(hand));
//        System.out.println(hasFiveSequentially(hand));
        System.out.println(hasFiveSequentially(hand));
//        System.out.println(fourOfAKind(hand));
//        System.out.println(fullHouse(hand));
//        System.out.println(twoPair(hand));
        System.out.println("----");
        if (hasStraightFlushOrRoyalFlush(hand) == 10) {
            System.out.println("royal flush");
        } else if (hasStraightFlushOrRoyalFlush(hand) > 0) {
            System.out.println("STRAIGHT FLUSH");
        }
    }

    public static int hasStraightFlushOrRoyalFlush(Card hand[]) {
        int x = hasFiveSequentially(hand);//x is lowest value of the 5 in sequential order
        if (x > 0 && hasFiveSameSuit(hand) == true) {//does have 5 sequentially and does have 5 same suit
            char suit = whatIsSuitOfFiveOfSameSuit(hand);
            int indexOfLowestValSequentially = indexOfValueWithSuit(x, suit, hand);
            if (x != -1 && indexOfLowestValSequentially != -1) {
                //has the lowest value of sequential order card WHICH HAS SAME suit has 5 cards suit
                for (int i = indexOfLowestValSequentially; i < 5; i++) {
                    if (hand[i].suit != suit) {
                        return 0;
                    }
                }
                return x;
            }
        } else {
            return 0;
        }

        return 0;
    }

    public static char whatIsSuitOfFiveOfSameSuit(Card hand[]) {
        int numClubs = 0;
        int numHearts = 0;
        int numSpades = 0;
        int numDiamonds = 0;
        for (int i = 0; i < 7; i++) {//was 7
            switch (hand[i].suit) {
                case 'c':
                    numClubs++;
                    break;
                case 'd':
                    numDiamonds++;
                    break;
                case 's':
                    numSpades++;
                    break;
                case 'h':
                    numHearts++;
            }
        }

        if (numClubs >= 5) {
            return 'c';
        }
        if (numDiamonds >= 5) {
            return 'd';
        }
        if (numSpades >= 5) {
            return 's';
        }
        if (numHearts >= 5) {
            return 'h';
        }
        return 'x';
    }

    public static int indexOfValueWithSuit(int val, char c, Card hand[]) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].value == val && hand[i].suit == c) {
                return i;
            }
        }
        return -1;
    }

    public static void sortPlayerHandByCardValue(Card hand[]) {
        for (int i = 1; i < 7; i++) {
            Card x = hand[i];
            int j = i - 1;
            while (j >= 0 && hand[j].value > x.value) {
                hand[j + 1] = hand[j];
                --j;
            }
            hand[j + 1] = x;
        }
    }

    public static void playerDisplayHand_thatIsUsedToDetermineWinner(Card hand[]) {

        for (int i = 0; i < 7; i++) {
            hand[i].cardToString();
            System.out.print(",  ");
        }
        System.out.println();
    }

}
