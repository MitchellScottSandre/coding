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
public class Deck {
        //just pass Card constructor int 1 -52? it mods it...
    public static Card[] cards = new Card[52];
    private static int index = 0;
    
    public Deck(boolean makeJustForValAndSuit){
        setIndex(0);
        makeJustInformationDeck();
        shuffle();
    }
    
    public Deck() throws SlickException{
       setIndex(0);
       makeDeck();
       shuffle();
    }
    
    public static void resetDeck(){
        shuffle();
        setIndex(0);
        for (int i = 0; i < cards.length; i++){
            cards[i].setIsFaceUp(false);
        }
    }
    
    
    public static Card getNextCard(){
        System.out.println("index is : " + index);
        index++;//moves on to next card..can't do this after return statement
        return cards[index - 1];//return card at index of the index when getNextCard was called
        
    }
    
    public static void makeJustInformationDeck(){
        boolean makeThisJustForValAndChar = true;
        for (int i = 0; i < 52; i++){
            Card temp = new Card(i, makeThisJustForValAndChar);
            cards[i] = temp;
        }
    }
    
    public static void makeDeck() throws SlickException{
        for (int i = 0; i < 52; i++){
            Card temp = new Card(i);
            cards[i] = temp;
        }
    }
    
    public static void shuffle(){
        System.out.println("Start shuffle...");
        //int temp[] = new int [52];//all set to 0
        Card newDeck[] = new Card[52];
        int counter = 0;
        int r;
        int counter2 = 0;
        while (counter < 52){
            counter2++;
            r =  (int) (Math.random() * 52);
            if (newDeck[r] == null){//empty
                newDeck[r] = cards[counter];
                counter++;
            }
        }
        System.out.println("counter2 is " + counter2);
        cards = newDeck;
        System.out.println("End shuffle...");
    }
    
    public static void displayDeckText(){
        for (int i = 0; i < 52; i ++){
            cards[i].cardToString();
        }
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        Deck.index = index;
    }
    
    
    
}
