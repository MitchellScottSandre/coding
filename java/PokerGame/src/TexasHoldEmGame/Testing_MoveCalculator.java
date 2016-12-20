/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import org.newdawn.slick.SlickException;

/**
 *
 * @author scott
 */
public class Testing_MoveCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        //public ComputerMoveCalculator(String name, int amountToCall, int playerMoney, int playerChips[][], Card playerOwnCards[], Card faceUpCards[], int turnCounter, int minRaise, int amountBetSoFarThisTurn, boolean canRaise) {
        boolean makeJustForValAndSuit = true;
        Deck d1 = new Deck(makeJustForValAndSuit);
        int z = (int) (Math.random() * 4);
        
        d1.shuffle();
        String name = "Scott";
        int amountToCall = 50;
        int playerMoney = 2000;
        Card aa = new Card(9, 's');
        Card bb = new Card(14, 's');
        Card cc = new Card(9, 's');
        Card dd = new Card(9, 'h');
        Card ee = new Card(3, 's');
        Card ff = new Card(6, 'c');
        Card gg = new Card(14, 'c');
        
        int playerChips[][] = {{5, 150}, {25, 10}, {100, 5}, {500, 1}, {1000, 0}, {5000, 0}}; 
        Card c1  = d1.getNextCard();
        Card c2 = d1.getNextCard();
        
        Card playerOwnCards[] = {aa, bb};
       //Card faceUpCards[] = new Card[5];
        Card faceUpCards[] = {cc, dd, ee, ff, gg};
        //important
        int numOfCardsInFaceUpCards = 0; //THIS RELATES AND MUST MATCH TURN COUNTER
        int turnCounter = 0;
        int amountBetSoFar = 0;
        int minRaise = 20;
        int lastRaiseAmount = 20 * 2;
        boolean canRaise = true;
        int pot = 500;
//        for (int i = 0; i < numOfCardsInFaceUpCards; i++){
//            faceUpCards[i] = d1.getNextCard();
//        }
        ComputerMoveCalculator decision = new ComputerMoveCalculator(z, name, amountToCall, playerMoney, playerChips, playerOwnCards, faceUpCards, turnCounter, minRaise, amountBetSoFar, canRaise, lastRaiseAmount, pot);
        System.out.println("Amount to call is: " + amountToCall);
        System.out.println("I should bet: " + decision.getDecision());
    
    }
    
}
